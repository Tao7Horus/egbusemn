
<%
            /***************************************************************************/
            /* Program ID         	: UM_RAJ_ConuA010i.jsp                       	   */
            /* Program Explanation	: Màn hình đăng ký nhà thầu                        */
            /* Program Summary  	: Nhập đơn xin đăng ký nhà thầu
            /* Relation Program  	: UM_RAV_ConuA010i // control đăng ký tư cách nhà thầu
            /* Table              	: UM_REC_SUPPLIER_ENTER_MAST, UM_REC_REPR,
            /*						  UM_REC_BID_AGENT, UM_REC_LICENSE_FACTS,
            /*						  UM_ENTER_CLS, UM_REC_ENTER_STD_CLS,
            /*						  UM_REC_PUB_INSTITU_CERT	 				          		       */
            /***************************************************************************/
            /* Composer : Nguyễn Viết Trọng 16.05.2009                      			*/
            /* Composer : Nguyễn Thanh Hưng 04.08.2011 sua thong tin Trang phe duyet nha thau                      			*/
            /***************************************************************************/
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.io.*,java.util.*,common.*,common.util.*,java.sql.*, g2b.sso.*, LOGIN.*"%>
<jsp:useBean id="nationalCtrl" class="beans.NationalRefDAO" scope="page" />
<%-- <jsp:useBean id="clsListEnter"   class="dao.UM_RAB_ListEnterCls" scope="page" /> 					<!-- Danh mục phân loại doanh nghiệp --> --%>
<jsp:useBean id="clsList"   class="dao.UM_RAB_ListStdCls" scope="page" />							<!-- Danh mục phân loại nghành nghề -->
<jsp:useBean id="rabBidAgentDAO"   class="dao.UM_RAB_MastBidAgent" scope="page" />					<!-- Thao tác bảng người đại diện dự thầu -->
<jsp:useBean id="rabEnterStdClsDAO"   class="dao.UM_RAB_MastEnterStdCls" scope="page" />				<!-- Thao tác bảng mã nghành nghề -->
<jsp:useBean id="rabPubInstituCertDAO"   class="dao.UM_RAB_RecPubInstituCert" scope="page" />		<!-- Thao tác bảng đăng ký CA -->
<jsp:useBean id="rabReprDAO"   class="dao.UM_RAB_MastRepr" scope="page" />							<!-- Thao tác bảng người phụ trách -->
<jsp:useBean id="rabSupplierEnterMastDAO"   class="dao.UM_RAB_MastSupplier" scope="page" />	<!-- Thao tác bảng nhà thầu -->


<%          
		SSO sso = new SSO(pageContext);
		sso.checkLogin();
		String gubun=sso.getGubun();
		
		if (sso.getGubun().equals("c")) {
		} 	else{
			out.println("<script>alert('Bạn không có quyền truy cập trang này');  main.location.href='http://muasamcong.mpi.gov.vn:8070/loginNew.jsp'</script>");
			return;
		}
		// 권한체크
		UM_Auth_Check uac = new UM_Auth_Check(request, response);
		uac.checkCookie("c",null,null);
            String bizRegNo =  uac.getMCode();
       //     String status = request.getParameter("status");

            Trx tr = null;
            Connection conn = null;
            CommEntity[] licenseList = null;
            CommEntity[] reprList = null;
            CommEntity[] stdClsList = null;
            
            CommEntity[] ctrList = null;
            CommEntity[] finanList = null;
            CommEntity[] reportList = null;
            
            CommEntity bidAgent = null;
            OneRowEntity recSupplierEnterMast = null;
            OneRowEntity recPubInstituCert = null;
            String recvNo ="";
            try {
                tr = new Trx(this);
                conn = tr.getConnection();
             
                
                
                //Lấy thông tin [Thông tin người phụ trách]
                reprList = rabReprDAO.getList(bizRegNo, conn);
                Log.debug(" step 1 ");
                //Lấy thông tin [Thông tin mã ngành nghề]
                stdClsList = rabEnterStdClsDAO.getList(bizRegNo, conn);
                Log.debug(" step 2 ");
              
                //Lấy thông tin [Người đại diện dự thầu]
                try{
    				bidAgent = rabBidAgentDAO.getList(bizRegNo, conn)[0];
    			}catch(ArrayIndexOutOfBoundsException e){
    				Log.debug(" step 6 errr " + e.getMessage());
    				throw new Exception("Không tìm thấy thông tin người đại diện");
    			}
                Log.debug(" step 6 ");
                //Lấy thông tin [Thông tin chung]
                recSupplierEnterMast = rabSupplierEnterMastDAO.selectDetail(bizRegNo, conn);
                Log.debug(" step 7 ");
                recvNo = recSupplierEnterMast.getDataFromName("RECV_NO");
                Log.debug(" step 7 REC " + recvNo);
                recPubInstituCert = rabPubInstituCertDAO.selectDetail(recvNo, conn);
                Log.debug(" NT SUA succees ");
               
            } catch (Exception ex) {
                ex.printStackTrace();
                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (Exception exm1) {
                    }
                }
                if (conn != null) {
                    try {
                        conn.setAutoCommit(true);
                    } catch (Exception exm1) {
                    }
                }
                return;
            } finally {
                if (conn != null) {
                    try {
                        tr.close();
                    } catch (Exception exf) {
                        exf.printStackTrace();
                    }
                }
            }

%>


<%@page import="common.util.CommonMessage"%><html>
    <head>
        <title>Tra cứu/Chỉnh sửa thông tin cơ bản nhà thầu</title>
        <meta http-equiv='Content-Type' Content='text/html; charset=utf-8'>
        <link rel="stylesheet" type="text/css" href="../css/UM.css">
        <link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="http://muasamcong.mpi.gov.vn/css/TA.css">        
        <style type="text/css">
            .tdar	  {
                font-size: 8pt;
                color: #3C3C3C;
                background-color: #CCDEF6;
                line-height: 12pt;
                font-weight: bold;
                text-indent: 4pt;
                text-align: left;
            }

            .readonly {
                font-size: 9pt;
                color: #666666;
                background-color:#E6E6E6;
            }

            .docbutt {
                color:#00866B;
                cursor:hand;
                font-size:9pt;
                background-color:#F0FBF9;
                border:1 solid #56D6B0;
                width:50px; height: 22px;
            }

	
.shortTitle1{
	  width: 100%;
	  display: inline-block;
	  white-space: nowrap !important;
	  overflow: hidden !important;
	  text-overflow: ellipsis !important;
	  -o-text-overflow: ellipsis !important;
	  
}

</style>
        <script language="javascript" src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
        <script language="javascript" src="/calendar/js/calendar.js"></script>
        <script src="http://muasamcong.mpi.gov.vn:8070/js/utils.js" language="javascript"></script>
        <script src="http://muasamcong.mpi.gov.vn:8070/js/SupplierRegister.js" language="javascript"></script>
        <script type="text/javascript">
        var licenseList = new Array();
    	var ceoList = new Array();
    	var stdCLSList = new Array();
    	var ctrList=new Array();
    	var finanList=new Array();
    	var reportList=new Array();
    	
    	var currLicenseIndex = -1;
    	var currCeoIndex = -1;
    	var currStdCLSIndex = -1;
    	var currCtrIndex = -1;
    	var currFinanIndex = -1;
    	var currFinanIndex1 = -1;
    
            function FieldChecker(fld, fmt, cnd, dt) {
                var rtnVal = FieldCheckerInJS(fld, fmt, cnd, dt);
                if (rtnVal == -1)
                {
                    alert("Độ dài quá mức cho phép");
                }
                else if (rtnVal == -2)
                {
                    alert("Không thể sử dụng được các ký tự như sau. ::~`@#$^_\|<>{}[]");
                }
                else if (rtnVal == -3)
                {
                    alert("Không phải dòng ký tự có hiệu lực.");
                }
                else if (rtnVal == -4)
                {
                    alert("Không phù hợp với hình thức.");
                }
                if (rtnVal < 0) {
                    fld.focus();
                }
                return(rtnVal);
            }

            function suyoSearch2 ( a, b, c){
                s = window.open("/servlet/dao/EP_COV_GTQ952?isUse=Y&suyoCode=&suyoName=&a="+a+"+&b="+b+"&formName="+c ,"s" , "width=500 , height=555 , scrollbars=yes , ");
            }
	
            function checkFixSize(fld, fmt) {
                var rtnVal = checkFixSizeInJS(fld, fmt);
                if (rtnVal == -1)
                {
                    alert("Độ dài không phù hợp.[Độ dài : " + fmt+ "]" );
                    fld.focus();
                }
                return(rtnVal);
            }
	
            function validateFrm(frm, unReq) {
                var len = frm.elements.length;
                var val;
                var name;
                for (var i = 0; i < len; i++) {
                    val = frm.elements[i].value;
                    name = frm.elements[i].name;
                    var flag = true;
                    if (!val || val.length == 0) {
                        if (unReq && unReq.length != 0) {
                            for (var j = 0; j < unReq.length; j++) {
                                if (unReq[j] == name) {
                                    flag = false;
                                    break;
                                }
                            }
                        }
                        if (flag) {
                            frm.elements[i].focus();
                            alert("Hãy nhập đầy đủ thông tin trước khi chọn thêm mới!");
                            return false;
                        }
                    }
                }
                return true;
            }
   	 
            function validateAll() {
                var unComInfReq = new Array("documentID", "documentRegDate", "bizEnName", "fax", "homePage");
                var unBidAgentReq = new Array("bizPosition", "bizDepart", "bizPhoneNo", "bizFax");
   	 	
                if(!validateFrm(document.comInfoFrm, unComInfReq, false)) {
                    alert("Hãy điền đầy đủ thông tin nhà thầu trước khi gửi đăng ký!");
                    return false;
                }
   	 	
                if( ceoList.length == 0) {
                    alert("Thông tin người lãnh đạo chưa được thêm mới!");
                    document.reprFrm.reprName.focus();
                    return false;
                } else {
                    var c = 0;
                    for (var i = 0; i < ceoList.length; i++) {
                        if(ceoList[i].ceoIsMaster == "Y") c++;
                    }
                    if(c == 0) {
                        alert("Phải có người lãnh đạo Đại diện hợp pháp cao nhất!");
                        document.reprFrm.reprName.focus();
                        return false;
                    }
                }
   	 	
                if(stdCLSList.length == 0) {
                    alert("Thông tin ngành nghề chưa được thêm mới!");
                    document.stdCLSFrm.stdClsName.focus();
                    return false;
                }
   	 		                
                if(!validateFrm(document.bizAgentFrm, unBidAgentReq, false)) {
                    alert("Hãy điền đầy đủ thông tin người đại diện trước khi gửi đăng ký!");
                    return false;
                }
   	 	
                if(!validateFrm(document.CARegisterFrm, null, false)) {
                    alert("Hãy điền đầy đủ thông tin đăng ký CA trước khi gửi đăng ký!");
                    return false;
                }
   	 	
                if(document.getElementById('acceptRule').checked == false) {
                    alert("Bạn phải chấp thuận các thỏa ước sử dụng trước khi đăng ký!");
                    return false;
                }
   	 	
                return true;
            }
   	 
            function validateFrm(frm, unReq, isAddingRow) {
                var len = frm.elements.length;
                var val;
                var name;
                for (var i = 0; i < len; i++) {
                    val = frm.elements[i].value;
                    name = frm.elements[i].name;
                    var flag = true;
                    if (!val || val.length == 0) {
                        if (unReq && unReq.length != 0) {
                            for (var j = 0; j < unReq.length; j++) {
                                if (unReq[j] == name) {
                                    flag = false;
                                    break;
                                }
                            }
                        }
                        if (flag) {
                            frm.elements[i].focus();
                            if (isAddingRow) alert("Hãy điền đầy đủ thông tin trước khi thêm mới!");
                            return false;
                        }
                    }
                }
                return true;
            }
   	 
            function validateAll() {
                var unComInfReq = new Array("documentID", "documentRegDate", "bizEnName", "fax", "homePage");
                var unBidAgentReq = new Array("bizFax");
   	 	
                if(!validateFrm(document.comInfoFrm, unComInfReq, false)) {
                    alert("Hãy điền đầy đủ thông tin nhà thầu trước khi gửi đăng ký!");
                    return false;
                }
   	 	
                if( ceoList.length == 0) {
                    alert("Thông tin người lãnh đạo chưa được thêm mới!");
                    document.reprFrm.reprName.focus();
                    return false;
                }
   	 	
                if(stdCLSList.length == 0) {
                    alert("Thông tin ngành nghề chưa được thêm mới!");
                    document.stdCLSFrm.stdClsName.focus();
                    return false;
                }
   	 	
                if(!validateFrm(document.bizAgentFrm, unBidAgentReq, false)) {
                    alert("Hãy điền đầy đủ thông tin người đại diện trước khi gửi đăng ký!");
                    return false;
                }
   	 	
                if(!validateFrm(document.CARegisterFrm, null, false)) {
                    alert("Hãy điền đầy đủ thông tin đăng ký CA trước khi gửi đăng ký!");
                    return false;
                }
                /*
                        if (document.CARegisterFrm.caReprName.value != document.bizAgentFrm.bizAgentName.value || document.CARegisterFrm.caReprIdentNo.value != document.bizAgentFrm.bizIdentNo.value) {
                                alert("Thông tin người đại diện trong mục Người đại diện dự thầu \nphải khớp với thông tin trong mục Đăng ký chứng thư số.");
                                return false;
                        }
                 */
                return true;
            }
	
            /*------------------------End validate field------------------------*/
	        	
            function addOnLoad() {
                // Edit hungnt 04082011
                // sua thong tin nganh nghe nha thau
                var item;
                
            <%for (int i = 0; i < reprList.length; i++) {%>
                    item = new CeoItem('<%= reprList[i].data[1]%>', '<%= reprList[i].data[2]%>', '<%= reprList[i].data[3]%>', '<%= reprList[i].data[4]%>', '<%= reprList[i].data[5]%>');
                    addCeoItem(item);
            <%}
              for (int i = 0; i < stdClsList.length; i++) {
                   String stdClsCode = stdClsList[i].data[0];
    		%>      
    			item = new StdCLSItem('<%= stdClsList[i].data[1]%>', '<%= stdClsCode%>', '<%= stdClsList[i].data[2]%>');
                addStdCLSItem(item);
            
            <%}
            
      		%>
                    
                    document.bizAgentFrm.bizAgentName.value = '<%=bidAgent.data[0]%>';
                    document.bizAgentFrm.bizIdentNo.value = '<%=bidAgent.data[1]%>';
                    document.bizAgentFrm.bizPosition.value = '<%=(bidAgent.data[2] == null ? "" : bidAgent.data[2])%>';
                    document.bizAgentFrm.bizDepart.value = '<%=(bidAgent.data[3] == null ? "" : bidAgent.data[3])%>';
                    document.bizAgentFrm.bizAgentEmail.value = '<%=bidAgent.data[4]%>';
                    document.bizAgentFrm.bizPhoneNo.value = '<%=(bidAgent.data[5] == null ? "" : bidAgent.data[5])%>';
                    document.bizAgentFrm.bizMobile.value = '<%=bidAgent.data[6]%>';
                    document.bizAgentFrm.bizFax.value = '<%=(bidAgent.data[7] == null ? "" : bidAgent.data[7])%>';
		
                    document.comInfoFrm.bizRegNo.value = '<%=recSupplierEnterMast.getDataFromName("BIZ_REG_NO")%>';
                    document.comInfoFrm.bizName.value = '<%=recSupplierEnterMast.getDataFromName("BIZ_NM")%>';
                    document.comInfoFrm.bizEnName.value = '<%=recSupplierEnterMast.getDataFromName("BIZ_EN_NM")%>';
                    document.comInfoFrm.commDate.value = '<%=recSupplierEnterMast.getDataFromName("COMMENCEMENT_DT")%>';
                    document.comInfoFrm.addr.value = '<%=recSupplierEnterMast.getDataFromName("ADDR")%>';
                    document.comInfoFrm.phoneNo.value = '<%=recSupplierEnterMast.getDataFromName("PHONE_NO")%>';
                    document.comInfoFrm.homePage.value = '<%=recSupplierEnterMast.getDataFromName("WEBSITE")%>';
                    document.comInfoFrm.capital.value = '<%=recSupplierEnterMast.getDataFromName("CAPITAL")%>';
                    document.comInfoFrm.emplCount.value = '<%=recSupplierEnterMast.getDataFromName("EMPLOYEE_COUNT")%>';
                    document.comInfoFrm.fax.value = '<%=recSupplierEnterMast.getDataFromName("FAX")%>';
                    document.comInfoFrm.bizCls.value = '<%=recSupplierEnterMast.getDataFromName("BIZ_CLS1")%>';
                    document.comInfoFrm.national.value = '<%=recSupplierEnterMast.getDataFromName("NATIONALITY")%>';
                    document.comInfoFrm.area.value = '<%=recSupplierEnterMast.getDataFromName("AREA_CD")%>';
                    document.comInfoFrm.documentID.value = '<%=recSupplierEnterMast.getDataFromName("DOC_NO")%>';
		
                    document.CARegisterFrm.caReprName.value = '<%=recPubInstituCert.getDataFromName("REPR_NM")%>';
                    document.CARegisterFrm.caReprIdentNo.value = '<%=recPubInstituCert.getDataFromName("REPR_IDENT_NO")%>';
                }
	
                function getRdbValue(groupRdb) {
                    var val = 0;
                    var len = groupRdb.length;
                    for(var i = 0; i < len; i++ ) {
                        if(groupRdb[i].checked == true ) {
                            val = groupRdb[i].value;
                            break;
                        }
                    }
                    return val;
                }
	
                function LicenseItem(code, issueInst, liDate, leDate) {
                    this.licenseCode = code;
                    this.issueInstitu = issueInst;
                    this.licenseIssuedDate = liDate;
                    this.licenseExpiryDate = leDate;
		
                    this.addToLicenseList = function() {
                        licenseList.push(this);
                    };
		
                    this.removeFromLicenseList = function(index) {
                        licenseList = licenseList.slice(0, index - 1).concat(licenseList.slice(index, licenseList.length));
                    };
                }
	
                function CeoItem(name, id, phone, email, master) {
                    this.ceoName = name;
                    this.ceoID = id;
                    this.ceoPhone = phone;
                    this.ceoEmail = email;
                    //this.ceoType = type;
                    this.ceoIsMaster = master;
		
                    this.addToCeoList = function() {
                        ceoList.push(this);
                    };
                    this.removeFromCeoList = function(index) {
                        ceoList = ceoList.slice(0, index - 1).concat(ceoList.slice(index, ceoList.length));
                    };
                }
				
                function ContractItem(No, name, stdDateSign, value, stdPriceValue, stdPerCtrValue, stdDateComplete, stdProjectName, stdContactValue){
                	this.CtrNo=No;
                	this.CtrName=name;
                	this.stdDateSign=stdDateSign;
                	this.CtrValue=value;
                	this.stdPriceValue=stdPriceValue;
                	this.stdPerCtrValue=stdPerCtrValue;
                	this.stdDateComplete=stdDateComplete;
                	this.stdProjectName=stdProjectName;
                	this.stdContactValue=stdContactValue;
                	
                	this.addToCtrList = function() {
            			ctrList.push(this);
            		};
            		this.removeFromCtrList = function(index) {
            			ctrList = ctrList.slice(0, index - 1).concat(ctrList.slice(index, ctrList.length));
            		};
                }
              

                function StdCLSItem(name, code, groupName) {
                    this.stdClsName = name;
                    this.stdClsCode = code;
                    this.groupName = groupName;

                    this.addToStdCLSList = function() {
                        stdCLSList.push(this);
                    };
                    this.removeFromStdCLSList = function(index) {
                        stdCLSList = stdCLSList.slice(0, index - 1).concat(stdCLSList.slice(index, stdCLSList.length));
                    };
                }

	
                function setMouseEffect(row, index) {
                    var rowColor = '#EAF1F7';
                    if (index % 2 == 0) {
                        rowColor = '#CCDEF6';
                        row.onmouseout = function() {
                            row.style.backgroundColor = '#CCDEF6';
                        };
                    } else {
                        row.onmouseout = function() {
                            row.style.backgroundColor = '#EAF1F7';
                        };
                    }
                    row.style.backgroundColor = rowColor;
                    row.onmouseover = function() {
                        row.style.backgroundColor = '#589DDA';
                    };
                }
	
	
                function addLicenseItem(itemObj) {
                    itemObj.addToLicenseList();
                    var len = licenseList.length;
                    var tblScreen = document.getElementById('tblLicenseFactsList');
                    var row = tblScreen.insertRow(len + 2);
                    setMouseEffect(row, len);
                    var cell1 = row.insertCell(0);
                    cell1.innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + len + ", document.licenseInstitueFrm);' style='cursor: hand;' value='" + len + " Sửa'>";
                    var cell2 = row.insertCell(1);
                    cell2.innerHTML = itemObj.licenseCode;
                    var cell3 = row.insertCell(2);
                    cell3.innerHTML = itemObj.issueInstitu;
                    var cell4 = row.insertCell(3);
                    cell4.innerHTML = itemObj.licenseIssuedDate;
                    var cell5 = row.insertCell(4);
                    cell5.innerHTML = itemObj.licenseExpiryDate;
                    reset(document.licenseInstitueFrm);
                }
	
                function addCeoItem(itemObj) {
                    if (ceoList.length != 0) {
                        for (var i = 0; i < ceoList.length; i ++) {
                            if (ceoList[i].ceoIsMaster == "Y" && itemObj.ceoIsMaster == "Y") {
                                alert("Chỉ được phép có một người lãnh đạo Đại diện hợp pháp cao nhất!");
                                return;
                            }
                        }
                    }
                    itemObj.addToCeoList();
                    var len = ceoList.length;
                    var tblScreen = document.getElementById('tblCeo');
                    var row = tblScreen.insertRow(len + 2);
                    setMouseEffect(row, len);
                    var cell1 = row.insertCell(0);
                    cell1.innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + len + ", document.reprFrm);' style='cursor: hand;' value='" + len + " Sửa'>";
                    var cell2 = row.insertCell(1);
                    cell2.innerHTML = itemObj.ceoName;
                    var cell3 = row.insertCell(2);
                    cell3.innerHTML = itemObj.ceoID;
                    var cell4 = row.insertCell(3);
                    cell4.innerHTML = itemObj.ceoPhone;
                    var cell5 = row.insertCell(4);
                    cell5.innerHTML = itemObj.ceoEmail;
			
                    /*
                        var cell6 = row.insertCell(5);
                        var type = "";
                        if (itemObj.ceoType == 0) type = "Chính thức";
                        else  type = "Không chính thức";
                        //cell6.innerHTML = itemObj.ceoType;
                        cell6.innerHTML = type;
                     */
		
		
                    var cell6 = row.insertCell(5);
                    var imt = "";
                    if (itemObj.ceoIsMaster == "Y") imt = "Có";
                    else imt = "Không";
                    cell6.innerHTML = imt;
                    reset(document.reprFrm);
                }

                // Edit hungnt 04082011
                // sua thong tin hien thi thong tin nganh nghe
                function addStdCLSItem(itemObj) {
                    itemObj.addToStdCLSList();
                    var len = stdCLSList.length;
                    var tblScreen = document.getElementById('tblStdCls');
                    var row = tblScreen.insertRow(len + 2);
                    setMouseEffect(row, len);
                    var cell1 = row.insertCell(0);
                    cell1.innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + len + ", document.stdCLSFrm);' style='cursor: hand;' value='" + len + " Sửa'>";
                    var cell2 = row.insertCell(1);
                    
                    cell2.innerHTML = itemObj.stdClsName;	

                 }
	
                            function addItem(itemObj) {
                                if(itemObj instanceof LicenseItem) {
                                    addLicenseItem(itemObj);
                                } else if (itemObj instanceof CeoItem) {
                                    addCeoItem(itemObj);
                                } else if (itemObj instanceof StdCLSItem) {
                                    addStdCLSItem(itemObj);
                                }else if (itemObj instanceof ContractItem) {
                        			addCtrItem(itemObj);
                        		}else if (itemObj instanceof FinanItem) {
                        			addFinanItem(itemObj);
                        		}else if (itemObj instanceof ReportItem) {
                        			addReportItem(itemObj);
                        		}
                            }
	
                            function loadItem(index, objFrm) {
                                objFrm.btn_Reset.style.display = "none";
                                objFrm.btn_Add.style.display = "none";
                                objFrm.btn_Update.style.display = "block";
                                objFrm.btn_Delete.style.display = "block";
                                var i;
                                var item;
                                if (objFrm.name == 'licenseInstitueFrm') {
                                    i = parseInt(index) - 1;
                                    currLicenseIndex = i;
                                    item = licenseList[i];
			 							
                                    objFrm.licenseCode.value = item.licenseCode;
                                    objFrm.issueInstitu.value = item.issueInstitu;
                                    objFrm.licenseIssuedDate.value = item.licenseIssuedDate;
                                    objFrm.licenseExpiryDate.value = item.licenseExpiryDate;
                                } else if (objFrm.name == 'reprFrm') {
                                    i = parseInt(index) - 1;
                                    currCeoIndex = i;
                                    item = ceoList[i];
			 							
                                    objFrm.reprName.value = item.ceoName;
                                    objFrm.reprIdentNo.value = item.ceoID;
                                    objFrm.reprMobile.value = item.ceoPhone;
                                    objFrm.reprEmail.value = item.ceoEmail;
                                    if ("Y" == item.ceoIsMaster) objFrm.reprIsmain[0].checked = "checked";
                                    else if ("N" == item.ceoIsMaster) objFrm.reprIsmain[1].checked = "checked";
                                } else if (objFrm.name == 'stdCLSFrm') {
                        			tbl = document.getElementById('tblStdCls');
                        			tbl.deleteRow(currStdCLSIndex + 3);
                        			len = stdCLSList.length;
                        			stdCLSList[currStdCLSIndex].removeFromStdCLSList(currStdCLSIndex + 1);
                        			activeIndex = currStdCLSIndex;
                        			activeForm = 'document.stdCLSFrm';
                        		}
                            }
                            function addFinanItem(itemObj) {
                        		//alert('addFinanItem');
                        		itemObj.addToFinanList();
                        		var len = finanList.length;

                        		var tblScreen = document.getElementById('tblStdFinan');
                        		var row = tblScreen.insertRow(len + 2);
                        		setMouseEffect(row, len);
                        		var cell1 = row.insertCell(0);
                        		cell1.innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + len + ", document.stdFinanFrm);' style='cursor: hand;' value='" + len + " Sửa'/>";
                        		//stdYEAR, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL
                        		var cell2 = row.insertCell(1);
                                itemObj.stdYEAR = itemObj.stdYEAR.replace(/^\s+/,'');
                        		cell2.innerHTML = "<div class='shortTitle'>"+itemObj.stdYEAR+"</div>";
                        		var cell3 = row.insertCell(2);
                                itemObj.stdASSETS_TOTAL = itemObj.stdASSETS_TOTAL.replace(/^\s+/,'');
                        		cell3.innerHTML = "<div class='shortTitle'>"+itemObj.stdASSETS_TOTAL+"</div>";
                        		var cell4 = row.insertCell(3);
                                itemObj.stdDEBT_TOTAL = itemObj.stdDEBT_TOTAL.replace(/^\s+/,'');
                        		cell4.innerHTML = "<div class='shortTitle'>"+itemObj.stdDEBT_TOTAL+"</div>";
                        		var cell5 = row.insertCell(4);
                                itemObj.stdASSET_VALUE = itemObj.stdASSET_VALUE.replace(/^\s+/,'');
                        		cell5.innerHTML = "<div class='shortTitle'>"+itemObj.stdASSET_VALUE+"</div>";
                        		
                        		var cell6 = row.insertCell(5);
                                itemObj.stdSHORT_ASSETS = itemObj.stdSHORT_ASSETS.replace(/^\s+/,'');
                        		cell6.innerHTML = "<div class='shortTitle'>"+itemObj.stdSHORT_ASSETS+"</div>";
                        		var cell7 = row.insertCell(6);
                                itemObj.stdSHORT_DEBT = itemObj.stdSHORT_DEBT.replace(/^\s+/,'');
                        		cell7.innerHTML = "<div class='shortTitle'>"+itemObj.stdSHORT_DEBT+"</div>";
                        		var cell8 = row.insertCell(7);
                                itemObj.stdCAPITAL = itemObj.stdCAPITAL.replace(/^\s+/,'');
                        		cell8.innerHTML = "<div class='shortTitle'>"+itemObj.stdCAPITAL+"</div>";
                        		
                        		
                        		reset(document.stdFinanFrm);
                        	}
                        	function addReportItem(itemObj) {
                        		//alert('addReportItem');
                        		itemObj.addToReportList();
                        		var len = reportList.length;

                        		var tblScreen = document.getElementById('tblStdFinan1');
                        		var row = tblScreen.insertRow(len + 2);
                        		setMouseEffect(row, len);
                        		var cell1 = row.insertCell(0);
                        		cell1.innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + len + ", document.stdFinanFrm1);' style='cursor: hand;' value='" + len + " Sửa'/>";
                        		//stdYEAR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER - stdFinanFrm1
                        		var cell2 = row.insertCell(1);
                                itemObj.stdYEAR = itemObj.stdYEAR.replace(/^\s+/,'');
                        		cell2.innerHTML = "<div class='shortTitle'>"+itemObj.stdYEAR+"</div>";
                        		var cell3 = row.insertCell(2);
                                itemObj.stdREVENUE_TOTAL = itemObj.stdREVENUE_TOTAL.replace(/^\s+/,'');
                        		cell3.innerHTML = "<div class='shortTitle'>"+itemObj.stdREVENUE_TOTAL+"</div>";
                        		var cell4 = row.insertCell(3);
                                itemObj.stdAVERAGE_REVENUE = itemObj.stdAVERAGE_REVENUE.replace(/^\s+/,'');
                        		cell4.innerHTML = "<div class='shortTitle'>"+itemObj.stdAVERAGE_REVENUE+"</div>";
                        		var cell5 = row.insertCell(4);
                                itemObj.stdPROFIT_BEFORE = itemObj.stdPROFIT_BEFORE.replace(/^\s+/,'');
                        		cell5.innerHTML = "<div class='shortTitle'>"+itemObj.stdPROFIT_BEFORE+"</div>";
                        		var cell6 = row.insertCell(5);
                                itemObj.stdPROFIT_AFTER = itemObj.stdPROFIT_AFTER.replace(/^\s+/,'');
                        		cell6.innerHTML = "<div class='shortTitle'>"+itemObj.stdPROFIT_AFTER+"</div>";
                        		
                        		
                        		
                        		reset(document.stdFinanFrm1);
                        	}
                        	function addCtrItem(itemObj) {
                        		//alert('addCtrItem');
                        		itemObj.addToCtrList();
                        		var len = ctrList.length;

                        		var tblScreen = document.getElementById('tblStdCtr');
                        		var row = tblScreen.insertRow(len + 2);
                        		setMouseEffect(row, len);
                        		var cell1 = row.insertCell(0);
                        		cell1.innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + len + ", document.stdCtrFrm);' style='cursor: hand;' value='" + len + " Sửa'>";
                        		//No, name, stdDateSign, ctrValue, stdPriceValue, stdPerCtrValue, stdDateComplete, stdProjectName, stdContactValue
                        		var cell2 = row.insertCell(1);
                                itemObj.CtrNo = itemObj.CtrNo.replace(/^\s+/,'');
                        		cell2.innerHTML = "<div class='shortTitle'>"+itemObj.CtrNo+"</div>";
                        		var cell3 = row.insertCell(2);
                                itemObj.CtrName = itemObj.CtrName.replace(/^\s+/,'');
                        		cell3.innerHTML = "<div class='shortTitle'>"+itemObj.CtrName+"</div>";
                        		var cell4 = row.insertCell(3);
                                itemObj.stdDateSign = itemObj.stdDateSign.replace(/^\s+/,'');
                        		cell4.innerHTML = "<div class='shortTitle'>"+itemObj.stdDateSign+"</div>";
                        		var cell5 = row.insertCell(4);
                                itemObj.CtrValue = itemObj.CtrValue.replace(/^\s+/,'');
                        		cell5.innerHTML = "<div class='shortTitle'>"+itemObj.CtrValue+"</div>";
                        		
                        		var cell6 = row.insertCell(5);
                                itemObj.stdPriceValue = itemObj.stdPriceValue.replace(/^\s+/,'');
                        		cell6.innerHTML = "<div class='shortTitle'>"+itemObj.stdPriceValue+"</div>";
                        		var cell7 = row.insertCell(6);
                                itemObj.stdPerCtrValue = itemObj.stdPerCtrValue.replace(/^\s+/,'');
                        		cell7.innerHTML = "<div class='shortTitle'>"+itemObj.stdPerCtrValue+"</div>";
                        		var cell8 = row.insertCell(7);
                                itemObj.stdDateComplete = itemObj.stdDateComplete.replace(/^\s+/,'');
                        		cell8.innerHTML = "<div class='shortTitle'>"+itemObj.stdDateComplete+"</div>";
                        		var cell9 = row.insertCell(8);
                                itemObj.stdProjectName = itemObj.stdProjectName.replace(/^\s+/,'');
                        		cell9.innerHTML = "<div class='shortTitle'>"+itemObj.stdProjectName+"</div>";
                        		var cell10 = row.insertCell(9);
                                itemObj.stdContactValue = itemObj.stdContactValue.replace(/^\s+/,'');
                        		cell10.innerHTML = "<div class='shortTitle'>"+itemObj.stdContactValue+"</div>";
                        		
                        		reset(document.stdCtrFrm);
                        	}
	
                            function reset(objFrm) {
                                objFrm.reset();
                            }
							function checkStdCls(){
								return true;
							}
                            function update(objFrm) {
                                if (objFrm.name == 'reprFrm') {
                                    if (ceoList.length != 0) {
                                        for (var i = 0; i < ceoList.length; i ++) {
                                            if (currCeoIndex != i && ceoList[i].ceoIsMaster == "Y" && getRdbValue(objFrm.reprIsmain) == "Y") {
                                                alert("Chỉ được phép có một người lãnh đạo Đại diện hợp pháp cao nhất!");
                                                return;
                                            }
                                        }
                                    }
                                }
		
                                var tbl;
                                var currentRow;
                                var fl = 0;
                                if (objFrm.name == 'reprFrm') {
                                    tbl = document.getElementById('tblCeo');
                                    currentRow = tbl.rows[currCeoIndex + 3];
						
                                    ceoList[currCeoIndex].ceoName = currentRow.cells[1].innerHTML = objFrm.reprName.value;
                                    ceoList[currCeoIndex].ceoID = currentRow.cells[2].innerHTML = objFrm.reprIdentNo.value;
                                    ceoList[currCeoIndex].ceoPhone = currentRow.cells[3].innerHTML = objFrm.reprMobile.value;
                                    ceoList[currCeoIndex].ceoEmail = currentRow.cells[4].innerHTML=  objFrm.reprEmail.value;

                                    ceoList[currCeoIndex].ceoIsMaster = getRdbValue(objFrm.reprIsmain);
                                    var imt = "";
                                    if (getRdbValue(objFrm.reprIsmain) == "Y") imt = "Có";
                                    else imt = "Không";
                                    currentRow.cells[5].innerHTML = imt;
                                } else if (objFrm.name == 'stdCLSFrm') {
                        			tbl = document.getElementById('tblStdCls');
                        			tbl.deleteRow(currStdCLSIndex + 3);
                        			len = stdCLSList.length;
                        			stdCLSList[currStdCLSIndex].removeFromStdCLSList(currStdCLSIndex + 1);
                        			activeIndex = currStdCLSIndex;
                        			activeForm = 'document.stdCLSFrm';
                        		}
                                if (fl == 0) {
                                    objFrm.btn_Reset.style.display = "block";
                                    objFrm.btn_Add.style.display = "block";
                                    objFrm.btn_Update.style.display = "none";
                                    objFrm.btn_Delete.style.display = "none";
                                    reset(objFrm);
                                }
                            }
	
                            function del(objFrm) {
                                var tbl;
                                var len;
                                var activeForm;
                                var activeIndex;
                                objFrm.btn_Reset.style.display = "block";
                                objFrm.btn_Add.style.display = "block";
                                objFrm.btn_Update.style.display = "none";
                                objFrm.btn_Delete.style.display = "none";
		
                                if (objFrm.name == 'licenseInstitueFrm') {
                                    tbl = document.getElementById('tblLicenseFactsList');
                                    tbl.deleteRow(currLicenseIndex + 3);
                                    len = licenseList.length;
                                    licenseList[currLicenseIndex].removeFromLicenseList(currLicenseIndex + 1);
                                    activeIndex = currLicenseIndex;
                                    activeForm = 'document.licenseInstitueFrm';
                                } else if (objFrm.name == 'reprFrm') {
                                    tbl = document.getElementById('tblCeo');
                                    tbl.deleteRow(currCeoIndex + 3);
                                    len = ceoList.length;
                                    ceoList[currCeoIndex].removeFromCeoList(currCeoIndex + 1);
                                    activeIndex = currCeoIndex;
                                    activeForm = 'document.reprFrm';
                                } else if (objFrm.name == 'stdCLSFrm') {
                                    tbl = document.getElementById('tblStdCls');
                                    tbl.deleteRow(currStdCLSIndex + 3);
                                    len = stdCLSList.length;
                                    stdCLSList[currStdCLSIndex].removeFromStdCLSList(currStdCLSIndex + 1);
			
                                    activeIndex = currStdCLSIndex;
                                    activeForm = 'document.stdCLSFrm';
                                } else if (objFrm.name == 'stdCtrFrm') {
                        			tbl = document.getElementById('tblStdCtr');
                        			tbl.deleteRow(currCtrIndex + 3);
                        			len =ctrList.length;
                        			ctrList[currCtrIndex].removeFromCtrList(currCtrIndex + 1);
                        			activeIndex = currCtrIndex;
                        			activeForm = 'document.stdCtrFrm';
                        		}
                                if(activeIndex < len - 1) {
                                    var newIndex = -1;
                                    var newRow;
			
                                    for (var i = activeIndex; i < len - 1; i++) {
                                        newIndex = i + 1;
                                        tbl.rows[i + 3].cells[0].innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + newIndex + ", " + activeForm + ");' style='cursor: hand;' value='" + newIndex  + " Sửa'>";
                                    }
                                }
                                reset(objFrm);
                            }
	
                            function getSupplierCls() {
                                var clsStr = "";
                                if(document.comInfoFrm.cbProduction.checked == true) clsStr += document.comInfoFrm.cbProduction.value;
                                if(document.comInfoFrm.cbConstruction.checked == true) clsStr += document.comInfoFrm.cbConstruction.value;
                                if(document.comInfoFrm.cbConsultant.checked == true) clsStr += document.comInfoFrm.cbConsultant.value;
                                if(document.comInfoFrm.noneCbConsultant.checked == true) clsStr += document.comInfoFrm.noneCbConsultant.value;
                                return clsStr;
                            }

                            //ajax -> post all form
                            var http_request = false;
                            var message = "";
                            var status = -1;
                            function makePOSTRequest(url, parameters) {
                                http_request = false;
                                if (window.XMLHttpRequest) { // Mozilla, Safari,...
                                    http_request = new XMLHttpRequest();
                                    if (http_request.overrideMimeType) {
                                        http_request.overrideMimeType('text/html');
                                    }
                                } else if (window.ActiveXObject) { // IE
                                    try {
                                        http_request = new ActiveXObject("Msxml2.XMLHTTP");
                                    } catch (e) {
                                        try {
                                            http_request = new ActiveXObject("Microsoft.XMLHTTP");
                                        } catch (e) {}
                                    }
                                }
                                if (!http_request) {
                                    alert('Cannot create XMLHTTP instance');
                                    return false;
                                }
      
                                http_request.open('POST', url, true);
                                http_request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                                http_request.setRequestHeader("Content-length", parameters.length);
                                http_request.setRequestHeader("Connection", "close");
                                http_request.send(parameters);
                            }
                            
                        	function addKeyValue(form, key, value){
                        		$('<input>').attr({
                        		    type: 'hidden',
                        		    value: value,
                        		    name: key
                        		}).appendTo(form);
                        	}


                            function make() {

                        		var action_url='/servlet/servlets/UM_COV_NT_UpdateInfo';
                        		var form = $("<form></form>");
                        		var oldFrame=document.getElementById("regis_iframe");
                        		try{
                        			if((typeof(oldFrame) !== 'undefined') && (oldFrame !== null)){
                        				document.removeChild(oldFrame);
                        			}
                        		}catch(e){}
                        		
                        		var iframe = document.createElement("iframe");
                        		
                        		iframe.setAttribute("id", "regis_iframe");
                        	    iframe.setAttribute("name", "regis_iframe");
                        	    iframe.setAttribute("width", "0");
                        	    iframe.setAttribute("height", "0");
                        	    iframe.setAttribute("border", "0");
                        	    iframe.setAttribute("style", "width: 0; height: 0; border: none;");
                        	    document.body.appendChild(iframe);

                        	    
                                var sCLS = getSupplierCls();
                                //comInfoFrm form
                                
						        addKeyValue(form, "bizRegNo", document.comInfoFrm.bizRegNo.value);
						        addKeyValue(form, "bizName", document.comInfoFrm.bizName.value);
						        addKeyValue(form, "bizEnName", document.comInfoFrm.bizEnName.value);
						        addKeyValue(form, "commDate", document.comInfoFrm.commDate.value);
						        addKeyValue(form, "addr", document.comInfoFrm.addr.value);
						        addKeyValue(form, "phoneNo", document.comInfoFrm.phoneNo.value);
						        addKeyValue(form, "homePage", document.comInfoFrm.homePage.value);
						       	addKeyValue(form, "capital", document.comInfoFrm.capital.value.replace('/\./g', ''));
								addKeyValue(form, "emplCount", document.comInfoFrm.emplCount.value.replace('/\./g', ''));
								addKeyValue(form, "fax",  document.comInfoFrm.fax.value);
								addKeyValue(form, "bizCls",  document.comInfoFrm.bizCls.value);
								addKeyValue(form, "national",  document.comInfoFrm.national.value);
								addKeyValue(form, "area", document.comInfoFrm.area.value);
								addKeyValue(form, "docNo",  document.comInfoFrm.documentID.value);
								addKeyValue(form, "docRegDt",  document.comInfoFrm.documentRegDate.value);
								addKeyValue(form, "supplierCls", sCLS);
								addKeyValue(form, "docRegDt",  document.comInfoFrm.documentRegDate.value);
								addKeyValue(form, "bizAgentName", document.bizAgentFrm.bizAgentName.value);
								addKeyValue(form, "bizIdentNo", document.bizAgentFrm.bizIdentNo.value);
								addKeyValue(form, "bizPosition", document.bizAgentFrm.bizPosition.value);
								
								addKeyValue(form, "bizDepart", document.bizAgentFrm.bizDepart.value);
								addKeyValue(form, "bizAgentEmail", document.bizAgentFrm.bizAgentEmail.value);
								addKeyValue(form, "bizPhoneNo", document.bizAgentFrm.bizPhoneNo.value);
								addKeyValue(form, "bizMobile",document.bizAgentFrm.bizMobile.value);
								addKeyValue(form, "bizFax",document.bizAgentFrm.bizFax.value);
								
								addKeyValue(form, "caReprName",document.CARegisterFrm.caReprName.value);
								addKeyValue(form, "caReprIdentNo",document.CARegisterFrm.caReprIdentNo.value);

								if (ceoList.length != 0) {
						        	addKeyValue(form, "reprCount", ceoList.length);
						        	for(var i = 0; i < ceoList.length; i++) {
						        		addKeyValue(form, "reprName[" + i + "]", ceoList[i].ceoName);
										addKeyValue(form, "reprIdentNo[" + i + "]", ceoList[i].ceoID);
										addKeyValue(form, "reprMobile[" + i + "]", ceoList[i].ceoPhone);
										addKeyValue(form, "reprEmail[" + i + "]", ceoList[i].ceoEmail);
										addKeyValue(form, "reprIsmain[" + i + "]", ceoList[i].ceoIsMaster);

						        	}
						        }

						        
						        if (licenseList.length != 0) {
						        	addKeyValue(form, "licenseCount", licenseList.length);
						        	for(var j = 0; j < licenseList.length; j++) {
						        		addKeyValue(form, "licenseCode[" + j + "]", licenseList[j].licenseCode);
						        		addKeyValue(form, "issueInstitu[" + j + "]", licenseList[j].issueInstitu);
						        		addKeyValue(form, "licenseIssuedDate[" + j + "]",  licenseList[j].licenseIssuedDate);
						        		addKeyValue(form, "licenseExpiryDate[" + j + "]", licenseList[j].licenseExpiryDate);
							        	
						        	}
								}
								

						        if (stdCLSList.length != 0) {
						        	addKeyValue(form, "stdClsCount", stdCLSList.length);
						        	for(var k = 0; k < stdCLSList.length; k++) {
						        		addKeyValue(form, "stdClsName[" + k + "]",  stdCLSList[k].stdClsName);
						    		 	addKeyValue(form, "stdClsCode[" + k + "]", stdCLSList[k].stdClsCode);        		        		        		        	
						        	}
								}
						  								
								addKeyValue(form, "step", 1);
								addKeyValue(form, "recvNo", '<%=recvNo%>');


							    window.frames['regis_iframe'].name = "regis_iframe"; 
							    window.frames['regis_iframe'].document.write("<html><head><meta http-equiv='Content-Type' Content='text/html; charset=utf-8'></head><body></body></html>");
							    var iframeId = document.getElementById("regis_iframe");	  
							 	var content="";
								  //  Add event...
							    var eventHandler = function () {
						            if (iframeId.detachEvent) iframeId.detachEvent("onload", eventHandler);
						            else iframeId.removeEventListener("load", eventHandler, false);
						            // Message from server...
						            	if (iframeId.contentWindow) {
						                	content = iframeId.contentWindow.document.body.innerHTML;
						           		 } 
						            if (content.length != 0) {
					            		alert('Cập nhật thành công!');	
							        } else {
						         		alert('Bạn đã cập nhật thất bại!!');
						         	}
						            try{
						    			if((typeof(iframeId) !== 'undefined') && (iframeId !== null)){
						    				document.removeChild(iframeId);
						    			}
						    		}catch(e){}
						            
						            // Del the iframe...
						            setTimeout(function(){
						            	if(typeof document.getElementById('regis_iframe') != "undefined")
						        			iframeId.parentNode.removeChild(iframeId);
						            }, 250);
						        }
						 
							    if (iframeId.addEventListener) iframeId.addEventListener("load", eventHandler, true);
							    if (iframeId.attachEvent) iframeId.attachEvent("onload", eventHandler);
							 

							    form.attr('method','post');
							    form.attr('action',action_url);
							    form.attr('enctype',"multipart/form-data");
							    form.attr("acceptCharset", "UTF-8");
							    
							    form.appendTo('#hiddenAreaPost').submit();						    
							 
				            	
					         	//window.location.href = "/CO/UM_COJ_ConiB010s.jsp";
					          				  
                            }

                            function btnAdd()
                            {
                                if (document.stdCLSFrm.stdClsName.value == "")
                                {
                                    alert("Bạn chưa nhập tên ngành nghề");
                                    document.stdCLSFrm.stdClsName.focus();
                                    return;
                                } 
                                
                                addItem(new StdCLSItem(document.stdCLSFrm.stdClsName.value, document.stdCLSFrm.stdClsCode.value, document.stdCLSFrm.groupName.value));
                            }
                          
                            function toFile(arg2){
                           		//document.tofile.fileType.value=arg1;
                           		document.tofile.fileName.value=arg2;
                           		document.tofile.method='POST';
                           		document.tofile.action='/servlet/servlets/EP_COV_GTQ953';
                           		document.tofile.submit();
                            }
	
        </script>

    </head>

    <body background="../img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="addOnLoad();">
        <div class="col-750 clearfix last">
            <h1 class="pageTitle"><i class="icon-title"></i>Tra cứu/Chỉnh sửa thông tin cơ bản nhà thầu</h1>
            <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">     
			<tr> 
				<td height="4" class="line"></td>
			</tr>
			<tr> 
				<td class="tdb"> 
				☞ Chương trình chỉ cho phép thay đổi một số thông tin.<br>
				 ☞ Khi bạn sửa xong những thông tin, bạn phải nhấn vào nút Cập nhật, thì các thông tin đó mới được sửa.    . 		</td>
			</tr>
			<tr> 
				<td height="2" class="line"></td>
			</tr>		  
		</table>

		<br>
            
                <form name="comInfoFrm">
					<table width="100%" border="0" style="display:none;">
						<tr>
							<td height="4" class="LINE" colspan="4" />
						</tr>
						<tr>
							<td width="20%" class="tdar"> Số văn bản</td>
							<td width="30%" class="tdb"><input type="text" name="documentID" id="documentID" value="" size="25" maxlength="20" onblur="FieldChecker(this, '20', 'M', 's');"></td>
							<td width="20%" class="tdar"> Ngày soạn thảo</td>
							<td width="30%" class="tdb">
								<input onblur="if(!isDate(this.value)){this.focus();};" class="readonly" readonly="readonly" type="text" name="documentRegDate" id="documentRegDate" size="25" maxlength="25" >
							</td>
						</tr>
						<tr>
							<td height="2" class="LINE" colspan="4" />
						</tr>
					</table>
					<br>
                    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <th align="left" colspan="4">[Thông tin chung]</th>
                        </tr>
                        <tr>
                            <td height="4" class="LINE" colspan="4" />
                        </tr>
                        
				        
                        <tr>
                            <td width="20%" class="tdar"><font color="red">*</font> Số ĐKKD</td>
                            <td width="30%" class="tdb"><input type="text" name="bizRegNo" value="" size="15" maxlength="13" readonly="readonly" class="readonly"/></td>
                            <td width="20%" class="tdar"><font color="red">*</font> Ngày ĐKKD</td>
                            <td width="30%" class="tdb">
                                <input onblur="if(!isDate(this.value)){this.focus();};" class="read" type="text" name="commDate" size="24" maxlength="25" value="">
                                <a href="javascript: Calendar_D(document.comInfoFrm.commDate);"><img src="../img/calendar.gif" border="0"></a>
                            </td>
                        </tr>
                        <tr>
                            <td width="20%" class="tdar"><font color="red">*</font> Tên nhà thầu </td>
                            <td colspan="3" class="tdb"><input type="text" name="bizName"  size="50" maxlength="200" onkeypress="return onlyAlphabet(event, this);" onblur="if (isValidatedInputObj(this)) {FieldChecker(this, '250', 'M', 's');}"/></td>
                        </tr>
                        <tr>
                            <td width="20%" class="tdar"> Tên tiếng Anh</td>
                            <td colspan="3" class="tdb"><input type="text" name="bizEnName" size="50" maxlength="150" onkeypress="return onlyAlphabet(event, this);" onblur="if (isValidatedInputObj(this)) {FieldChecker(this, '150', 'M', 's');}"/></td>
                        </tr>
                        <tr>
                            <td width="20%" class="tdar"><font color="red">*</font> Lĩnh vực kinh doanh</td>
                            <td width="30%" class="tdb">
                                <%
                                            String supplierCls = recSupplierEnterMast.getDataFromName("BIZ_CLS");

                                            if (supplierCls.indexOf('1') != -1) {
                                %>
                                <input type="checkbox" value="1" name="cbProduction" checked="checked"> Hàng hóa
                                <% } else {%>
                                <input type="checkbox" value="1" name="cbProduction"> Hàng hóa
                                <% }%>
                                <% if (supplierCls.indexOf('3') != -1) {%>
                                <input type="checkbox" value="3" name="cbConstruction" checked="checked"> Xây lắp
                                <% } else {%>
                                <input type="checkbox" value="3" name="cbConstruction"> Xây lắp
                                <% }%>
                                <br>
                                <% if (supplierCls.indexOf('5') != -1) {%>
                                <input type="checkbox" value="5" name="cbConsultant" checked="checked"> Tư vấn
                                <% } else {%>
                                <input type="checkbox" value="5" name="cbConsultant"> Tư vấn
                                 <% } %>
                                 <% if(supplierCls.indexOf('7') != -1) { %>
									<input type="checkbox" value="7" name="noneCbConsultant" checked="checked"> Phi tư vấn
						
								<% } else { %>
									<input type="checkbox" value="7" name="noneCbConsultant"> Phi tư vấn
								<% } %>
								
                            </td>
                            <td width="20%" class="tdar"><font color="red">*</font> Phân loại doanh nghiệp</td>
                            <td width="32%" class="tdb">
                                <select name="bizCls" title="">
                                    <option value="01">Doanh nghiệp Tư nhân</option>
                                    <option value="02">Công ty TNHH</option>
                                    <option value="03">Công ty Cổ phần</option>
                                    <option value="04">Công ty Hợp danh</option>
                                    <option value="05">Hợp tác xă</option>
                                    <option value="06">Công ty Liên doanh</option>
                                    <option value="07">Công ty 100% vốn nước</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td width="20%" class="tdar"><font color="red">*</font> Số nhân viên</td>
                            <td width="30%" class="tdb"><input type="text" name="emplCount" value="" size="15" maxlength="10" onFocus="this.value = setOrginalNumber(this.value, '.');"  onBlur="if (FieldChecker(this, '10', 'C', 'n') > 0) {this.value=setNumFormat(this.value, '#.###.###.###.##0');}"/></td>
                            <td width="20%" class="tdar"><font color="red">*</font> Vốn điều lệ</td>
                            <td width="30%" class="tdb"><input type="text" name="capital" value="" size="15" maxlength="15" onkeypress="return isNumberKey(event);" onFocus="this.value = setOrginalNumber(this.value, '.');"  onBlur="if (FieldChecker(this, '15', 'C', 'n') > 0) {this.value=setNumFormat(this.value, '#.###.###.###.###.##0');}"/></td>
                        </tr>
                        <tr>
                            <td width="20%" class="tdar"><font color="red">*</font> Số điện thoại</td>
                            <td width="30%" class="tdb"><input type="text" name="phoneNo" value="" size="25" maxlength="20" /></td>
                            <td width="20%" class="tdar"> Số Fax</td>
                            <td width="30%" class="tdb"><input type="text" name="fax" value="" size="25" maxlength="20" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '20', 'C', 'n');"/></td>
                        </tr>
                        <tr>
                            <td width="20%" class="tdar"><font color="red">*</font> Địa chỉ</td>
                            <td colspan="3" class="tdb"><input type="text" name="addr" size="75" maxlength="100" /> </td>
                        </tr>
                        <tr>
                            <td width="20%" class="tdar"> Trang web</td>
                            <td colspan="3" class="tdb"><input type="text" name="homePage" size="50" maxlength="50" onblur="FieldChecker(this, '50', 'C', 's');"/></td>
                        </tr>
                        <tr>
                            <td width="20%" class="tdar"><font color="red">*</font> Tỉnh / thành phố</td>
                            <td width="30%" class="tdb">
                                <%
                                            List provinces = nationalCtrl.getList("GU7");
                                %>
                                <select name="area" title="">
                                    <%
                                                String pItem = "";
                                                for (int i = 0; i < provinces.size(); i++) {
                                                    pItem = provinces.get(i).toString();
                                                    String naCode = pItem.split("#")[1];
                                                    String naName = pItem.split("#")[0];
                                    %>
                                    <option value="<%=naCode%>" <%if (naCode.equals("01")) {%>selected<%}%>><%=naName%></option>
                                    <%
                                                }
                                    %>

                                </select>
                            </td>
                            <td width="20%" class="tdar"><font color="red">*</font> Quốc gia</td>
                            <td width="30%" class="tdb">
                                <!-- <input type="text" id="national" name="national" readonly="readonly">  -->
                                <%
                                            List nationals = nationalCtrl.getList("I99");
                                %>
                                <select name="national" title="">
                                    <%
                                                String item = "";
                                                for (int i = 0; i < nationals.size(); i++) {
                                                    item = nationals.get(i).toString();
                                                    String naCode = item.split("#")[1];
                                                    String naName = item.split("#")[0];
                                    %>
                                    <option value="<%=naCode%>" <%if (naCode.equals("1151")) {%>selected<%}%>><%=naName%></option>
                                    <%
                                                }
                                    %>
                                </select>
                                <input type="hidden" id="nationalCode" name="nationalCode">
                            </td>
                        </tr>
                        <tr>
                            <td height="2" class="LINE" colspan="4" />
                        </tr>
                        
                        
                    </table>
                </form>
                <br>

                <form name="reprFrm">
                    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <th align="left" colspan="2">[Thông tin lãnh đạo cơ quan / doanh nghiệp]</th>
                            <th align="right" colspan="2">
                                <input type="button" class="commonbutton" value="Thêm mới" name="btn_Add" style="float: right; display: block;" onclick="if (validateFrm(this.form,null,true)) { var reprIsmain = getRdbValue(document.reprFrm.reprIsmain); addItem(new CeoItem(this.form.reprName.value, this.form.reprIdentNo.value, this.form.reprMobile.value, this.form.reprEmail.value, reprIsmain));}">
                                <input type="button" class="commonbutton" value="Làm lại" name="btn_Reset" style="display: block;" onclick="reset(this.form);">
                                <input type="button" class="commonbutton" style="display: none; float: right;" value="Xóa" name="btn_Delete" onclick="del(this.form);">
                                <input type="button" class="commonbutton" style="display: none" value="Cập nhật" name="btn_Update" onclick="update(this.form);">
                            </th>
                        </tr>
                        <tr>
                            <td height="4" class="LINE" colspan="4" />
                        </tr>
                        <tr>
                            <td class="tdar" width="20%"><font color="red">*</font> Tên người lãnh đạo</td>
                            <td class="tdb" width="30%"><input type="text" name="reprName" value="" value="" size="25" maxlength="60" onblur="FieldChecker(this, '60', 'M', 's');"/></td>
                            <td class="tdar" width="20%"><font color="red">*</font> Số CMND</td>
                            <td class="tdb" width="30%"><input type="text" name="reprIdentNo" value="" size="15" maxlength="15" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '16', 'C', 'n');"/></td>
                        </tr>
                        <tr>
                            <td class="tdar" width="20%"><font color="red">*</font> Số điện thoại</td>
                            <td class="tdb" width="30%"><input type="text" name="reprMobile" value="" size="25" maxlength="20" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '20', 'C', 'n');"/></td>

                            <td class="tdar" width="20%"><font color="red">*</font> Địa chỉ email</td>
                            <td width="30%" class="tdb"><input type="text" name="reprEmail" value="" size="24" maxlength="25" onblur="return checkEmail(this);"/></td>
                        </tr>
                        <tr>
                            <td class="tdar" width="20%"><font color="red">*</font> Kiểu người lãnh đạo</td>
                            <!--
		<td class="tdb" width="30%">
			<select name="reprType">				
				<option  value="0">Chính thức</option>
				<option  value="1">Không chính thức</option>				
			</select>
		</td>
		 -->
                            <td class="tdar" width="20%"><font color="red">*</font> Đại diện hợp pháp cao nhất</td>
                            <td class="tdb" width="30%" colspan="3">
			Có <input type="radio" name="reprIsmain" value="Y" checked="checked"/> 
			Không <input type="radio" name="reprIsmain" value="N" />
                            </td>

                        </tr>
                        <tr>
                            <td height="2" class="LINE" colspan="4" />
                        </tr>
                    </table>
                    <table width="100%" id="tblCeo">
                        <tr height="1">
                            <td colspan="6" class="line"></td>
                        </tr>
                        <tr>
                            <td class="tdac" width="5%">STT</td>
                            <td class="tdac" width="20%">Tên người lãnh đạo</td>
                            <td class="tdac" width="10%">Số CMND</td>
                            <td class="tdac" width="10%">Số điện thoại</td>
                            <td class="tdac" width="25%">Địa chỉ email</td>
                            <!-- <td class="tdac" width="15%">Kiểu người lãnh đạo</td>  -->
                            <td class="tdac" width="15%">Đại diện hợp pháp cao nhất</td>
                        </tr>
                        <tr height="1">
                            <td colspan="6" class="line"></td>
                        </tr>
                    </table>
                    <table width="100%" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <td height="2" class="LINE" colspan="4" />
                        </tr>
                    </table>
                </form>
                <br>

                <form name="stdCLSFrm">
                    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <th align="left" colspan="2">[Thông tin ngành nghề]</th>
                            <th align="right" colspan="2">
                                <input type="button" class="commonbutton" value="Thêm mới" name="btn_Add" style="float: right; display: block;" onclick="btnAdd();">
                                <input type="button" class="commonbutton" value="Làm lại" name="btn_Reset" style="display: block;" onclick="reset(this.form);">
                                <input type="button" class="commonbutton" style="display: none; float: right;" value="Xóa" name="btn_Delete" onclick="del(this.form);">
                                <input type="button" class="commonbutton" style="display: none;" value="Cập nhật" name="btn_Update" onclick="update(this.form);">
                            </th>
                        </tr>
                        <tr>
                            <td height="4" class="LINE" colspan="4" />
                        </tr>
                        <tr style="display: none">
                            <%-- Add hungnt 04082011 --%>
                            <%-- sua thong tin nganh nghe hien thi --%>
                            <td class="tdar" width="25%"><font color="red" >*</font> Nhóm ngành nghề</td>
                            <td class="tdb"  width="75%" colspan="3" >
                                <span class="fontbl style13">
                                    <input name="groupName" type="text" value="" size="30" maxlength="50" readonly/>
                                    <input type="hidden" name="stdClsCode" value=""/>
                                </span>
                                <input name="btnSearchInstitu" type="button" class="commonbutton"  value="T&igrave;m"  onClick="javascript:suyoSearch2('stdClsCode','groupName','stdCLSFrm');"/>
                                <%--= recEnterStdClsDAO.getSelectNganhnghe_AddCode("stdClsCode")--%>
                            </td>
                        </tr>
                        <tr>
                            <td class="tdar" width="25%" ><font color="red">*</font> Tên ngành nghề</td>
                            <td class="tdb" width="75%" colspan="3">
                                <input type="text" name="stdClsName" maxlength="500" style="width: 100%" onblur="FieldChecker(this, '500', 'M', 's');"/>
                            </td>
                        </tr>
                        <tr style="display: none">
                            <td class="tdar" width="20%"><font color="red">*</font> Tên ngành nghề</td>
                            <td class="tdb" width="30%">
                                <input type="text" name="stdClsName1" size="25" maxlength="25" readonly="readonly" class="readonly"/>
                                <input type="button" class="commonbutton" value="Tìm" onClick='window.open("/RA/UM_RAJ_ConuA050l.jsp?codeComp=stdCLSFrm.stdClsCode&nameComp=stdCLSFrm.stdClsName","LicenseFind","width=840, height=500, top=100,left=200,scrollbars=yes")'>
                            </td>

                            <td class="tdar" width="20%"><font color="red">*</font> Mã ngành nghề</td>
                            <td width="30%" class="tdb"><input type="text" name="stdClsCode" size="15" maxlength="30" readonly="readonly" class="readonly"></td>
                        </tr>
                        <tr style="display: none">
                            <td class="tdar" width="20%"><font color="red">*</font> Số đăng ký</td>
                            <td class="tdb" width="30%"><input type="text" name="stdClsNo" value="" size="25" maxlength="20" onkeypress="return onlyAlphabet(event, this);" onblur="if (isValidatedInputObj(this)) {FieldChecker(this, '5', 'M', 's');}"></td>

                            <td class="tdar" width="20%"><font color="red">*</font> Ngày đăng ký</td>
                            <td class="tdb" width="30%">
                                <input type="text" name="stdClsIssuedDate" value="" size="24" maxlength="25">
                                <a href="javascript:Calendar_D(document.stdCLSFrm.stdClsIssuedDate);"><img src="../img/calendar.gif" border="0"></a>
                            </td>
                        </tr>
                        <tr style="display: none">
                            <td class="tdar" width="20%">Số tiền bảo lãnh</td>
                            <td class="tdb" width="30%"><input type="text" name="constAbilEvalAmt" value="" size="25" maxlength="15" onkeypress="return isNumberKey(event);" onFocus="this.value = setOrginalNumber(this.value, '.');"  onBlur="if (FieldChecker(this, '15', 'C', 'n') > 0) {this.value=setNumFormat(this.value, '#.###.###.###.###.##0');}" /></td>

                            <td class="tdar" width="20%">Năm đăng ký số tiền bảo lãnh (Chỉ nhập năm)</td>
                            <td class="tdb" width="30%"><input type="text" name="evalStdYear" value="" size="4" maxlength="4" onkeypress="return isNumberKey(event);" onblur="checkFixSize(this, 4);"></td>
                        </tr>

                        <tr style="display: none">
                            <td class="tdar" width="20%"><font color="red">*</font> Là ngành nghề chính</td>
                            <td class="tdb" width="30%">
			Có <input type="radio" name="mastStdClsYn" value="Y" checked="checked"> 
			Không <input type="radio" name="mastStdClsYn" value="N">
                            </td>

                            <td class="tdar" width="20%"><font color="red">*</font> Ngày hết hiệu lực</td>
                            <td width="30%" class="tdb">
                                <input type="text" name="stdClsExpiryDate" value="" size="24" maxlength="30">
                                <a href="javascript:Calendar_D(document.stdCLSFrm.stdClsExpiryDate);"><img src="../img/calendar.gif" border="0"></a>
                            </td>
                        </tr>
                    </table>
                    <table width="100%" id="tblStdCls">
                        <tr height="1">
                            <td colspan="9" class="line"></td>
                        </tr>
                        <tr>
                            <td class="tdac" width="5%">STT</td>
                            <td class="tdac" width="15%">Tên Ngành nghề</td>
                            <td class="tdac" width="10%" style="display: none">Nhóm ngành nghề</td>
                        </tr>
                        <tr height="1">
                            <td colspan="9" class="line"></td>
                        </tr>
                    </table>
                    <table width="100%" id="tblStdCls" style="display: none">
                        <tr height="1">
                            <td colspan="9" class="line"></td>
                        </tr>
                        <tr>
                            <td class="tdac" width="5%">STT</td>
                            <td class="tdac" width="15%">Tên Ngành nghề</td>
                            <td class="tdac" width="10%">Mã ngành nghề</td>
                            <td class="tdac" width="10%">Số đăng ký</td>
                            <td class="tdac" width="15%">Ngày đăng ký</td>
                            <td class="tdac" width="10%">Số tiền bảo lãnh</td>
                            <td class="tdac" width="10%">Năm đăng ký tiền bảo lãnh</td>
                            <td class="tdac" width="10%">Là ngành nghề chính</td>
                            <td class="tdac" width="15%">Ngày hết hiệu lực</td>
                        </tr>
                        <tr height="1">
                            <td colspan="9" class="line"></td>
                        </tr>
                    </table>
                    <table width="100%" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <td height="2" class="LINE" colspan="4" />
                        </tr>
                    </table>
                </form>
                <br />
               
<table width="100%" cellspacing="1" cellpadding="2" class="tr" style="display:none;">
	<tr>
		<td height="2" class="LINE" colspan="8" />
	</tr>
</table>
</form>


				<form name='tofile'>
<!-- 					<input type='hidden' name='fileType'/> -->
					<input type='hidden' name='fileName'/>
				</form>
				<br>
                
                <br>

                <form name="bizAgentFrm">
                    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <th align="left" colspan="4">[Người phụ trách - đại diện dự thầu]</th>
                        </tr>
                        <tr>
                            <td height="4" class="LINE" colspan="4" />
                        </tr>
                        <tr>
                            <td class="tdar" width="20%"> Chức vụ</td>
                            <td class="tdb" width="30%"><input type="text" name="bizPosition" value="" size="25" maxlength="40" onblur="FieldChecker(this, '40', 'M', 's');"/></td>
                            <td class="tdar" width="20%"> Phòng  / Ban</td>
                            <td class="tdb" width="30%"><input type="text" name="bizDepart" size="25" maxlength="40" onblur="FieldChecker(this, '40', 'M', 's');" /></td>
                        </tr>
                        <tr>
                            <td class="tdar" width="20%"><font color="red">*</font> Họ tên</td>
                            <td class="tdb" width="30%"><input type="text" name="bizAgentName" value="" value="" size="25" maxlength="60" onblur="FieldChecker(this, '60', 'M', 's');" /></td>
                            <td class="tdar" width="20%"><font color="red">*</font> Số CMND</td>
                            <td class="tdb" width="30%"><input type="text" name="bizIdentNo" size="15" maxlength="15" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '16', 'C', 'n');" /></td>
                        </tr>
                        <tr>
                            <td class="tdar" width="20%"><font color="red">*</font> Địa chỉ email</td>
                            <td class="tdb" width="30%"><input type="text" name="bizAgentEmail" value="" size="25" maxlength="25" onblur="return checkEmail(this);"/></td>
                            <td class="tdar" width="20%"><font color="red">*</font> Số di động</td>
                            <td class="tdb" width="30%"><input type="text" name="bizMobile" size="25" maxlength="20" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '20', 'C', 'n');" /></td>
                        </tr>

                        <tr>
                            <td class="tdar" width="20%"> Số điện thoại</td>
                            <td class="tdb" width="30%"><input type="text" name="bizPhoneNo" value="" size="25" maxlength="20" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '20', 'C', 'n');" /></td>
                            <td class="tdar" width="20%">Số Fax</td>
                            <td class="tdb" width="30%"><input type="text" name="bizFax" size="25" maxlength="20" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '20', 'C', 'n');"/></td>
                        </tr>

                        <tr>
                            <td height="2" class="LINE" colspan="4" />
                        </tr>
                    </table>
                </form>
                <br>
                <form name="CARegisterFrm">
                    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <th align="left" colspan="4">[Đăng ký chứng thư số]</th>
                        </tr>
                        <tr>
                            <td height="4" class="LINE" colspan="4"></td>
                        </tr>                        
                        <tr>
                            <td class="tdar" width="20%"><font color="red">*</font> Tên người đại diện</td>
                            <td class="tdb" width="30%"><input type="text" name="caReprName" value="" class="readonly" size="25" maxlength="60" onblur="FieldChecker(this, '60', 'M', 's');" ></td>
                            <td class="tdar" width="20%"><font color="red">*</font> Số CMND</td>
                            <td class="tdb" width="30%"><input type="text" name="caReprIdentNo" class="readonly" size="15" maxlength="15" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '16', 'C', 'n');"></td>
                        </tr>
                        <tr>
                            <td height="2" class="LINE" colspan="4"></td>
                        </tr>
                    </table>
                    <br>
                    <br>
                    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <th align="left" colspan="4">[Cơ quan cấp phát và quản lý chứng thư số]</th>
                        </tr>
                        <tr>
                            <td height="4" class="LINE" colspan="4"></td>
                        </tr>
                        <tr>
                            <td class="tdar" width="20%" height="25"><font color="red">*</font> Tên cơ quan</td>
                            <td class="tdb"  width="*" colspan="3"><input type="radio" name="liceS" value="" checked="checked" readonly="readonly" style="display: none">
                                Cục Quản lý Đấu thầu - Bộ Kế hoạch và Đầu tư   <input  type="radio" type="hidden" checked="checked" name="choiceCA" class="read" value="5"></td>
                        </tr>
                        <tr style="display: none">
                            <td class="tdar" width="20%" height="25"><font color="red">*</font> Chi nhánh phê duyệt</td>
                            <td class="tdb"  width="30%">
                                <input type="hidden" name="permitBranch" value="00">
                                <input type="text" name="permitBranchName" value="Bộ Kế hoạch và Đầu tư" readonly="readonly" class="readonly" size="30">
                            </td>
                        </tr>
                        <tr>
                            <td height="2" class="LINE" colspan="4"></td>
                        </tr>
                    </table>
                </form>
                <br>
                <br>
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                    <tr>
                        <td align="center" height="24">
                            <input type="button" class="commonbutton" value="Cập nhật" onclick="make();" name="btn_Accept" id = "btn_Accept" />
                            <input type="button" class="commonbutton" value="Quay lại" onclick="history.go(-1);" name="btn_Back" id="btn_Back" />
                        </td>
                    </tr>
                </table>
                <br>
            
            <div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
        </div>
		<div style="display:none;" id="hiddenAreaPost">
		
		</div>
    </body>
</html>