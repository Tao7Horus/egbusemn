
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
<%@ page language="java" import="java.io.*,java.util.*,common.*,common.util.*,java.sql.*,g2b.sso.*,LOGIN.*"%>
<jsp:useBean id="nationalCtrl" class="beans.NationalRefDAO" scope="page" />
<%-- <jsp:useBean id="clsListEnter"   class="dao.UM_RAB_ListEnterCls" scope="page" /> 					<!-- Danh mục phân loại doanh nghiệp --> --%>
<jsp:useBean id="clsList"   class="dao.UM_RAB_ListStdCls" scope="page" />							<!-- Danh mục phân loại nghành nghề -->
<jsp:useBean id="rabBidAgentDAO"   class="dao.UM_RAB_MastBidAgent" scope="page" />					<!-- Thao tác bảng người đại diện dự thầu -->
<jsp:useBean id="rabEnterStdClsDAO"   class="dao.UM_RAB_MastEnterStdCls" scope="page" />				<!-- Thao tác bảng mã nghành nghề -->
<jsp:useBean id="rabPubInstituCertDAO"   class="dao.UM_RAB_RecPubInstituCert" scope="page" />		<!-- Thao tác bảng đăng ký CA -->
<jsp:useBean id="rabReprDAO"   class="dao.UM_RAB_MastRepr" scope="page" />							<!-- Thao tác bảng người phụ trách -->
<jsp:useBean id="rabSupplierEnterMastDAO"   class="dao.UM_RAB_MastSupplier" scope="page" />	<!-- Thao tác bảng nhà thầu -->
<jsp:useBean id="rabCtr"   class="dao.UM_RAB_Contract" scope="page" />	
<jsp:useBean id="rabFina"   class="dao.UM_RAB_FinanYear" scope="page" />	
<jsp:useBean id="UM_FuncRef" class="beans.FuncRefDAO" scope="page" />


<%            String ID = "";
            String masterCode = "";
            String useGubun = "";
            String bizRegNo = request.getParameter("bizRegNo");
            String status = request.getParameter("status");

            SSO sso = new SSO(pageContext);

            sso.checkLogin();
            if (!sso.isLogin()) {
                return;
            }

            ID = sso.getID();
            masterCode = sso.getMCode();
            useGubun = sso.getGubun();

            // 공공기관 ,업체승인자,목록화요청자 사용
//             UM_Auth_Check uac = new UM_Auth_Check(request, response);
//             uac.checkCookie("gazmj", null, null);

            Trx tr = null;
            Connection conn = null;
            CommEntity[] licenseList = null;
            CommEntity[] reprList = null;
            CommEntity[] stdClsList = null;
            
            CommEntity[] ctrList = null;
            CommEntity[] finanList = null;
            CommEntity[] reportList = null;
            CommEntity[] recPubInstituCertList = null;
            
            CommEntity bidAgent = null;
            OneRowEntity recSupplierEnterMast = null;
            OneRowEntity recPubInstituCert = null;
            OneRowEntity userEntity = null;
            String recvNo = "";
            try {
                tr = new Trx(this);
                conn = tr.getConnection();
                
                
                //Lấy thông tin [Thông tin người phụ trách]
                reprList = rabReprDAO.getList(bizRegNo, conn);
              
                //Lấy thông tin [Thông tin mã ngành nghề]
                stdClsList = rabEnterStdClsDAO.getList(bizRegNo, conn);
                
                ctrList = rabCtr.getListSign(bizRegNo, conn);
    			finanList = rabFina.getListBalance(bizRegNo, conn);
    			reportList = rabFina.getListReport(bizRegNo, conn);
    			
                //Lấy thông tin [Người đại diện dự thầu]
                bidAgent = rabBidAgentDAO.getList(bizRegNo, conn)[0];
              
                //Lấy thông tin [Thông tin chung]
                recSupplierEnterMast = rabSupplierEnterMastDAO.selectDetail(bizRegNo, conn);
               
                //Lấy thông tin [Đăng ký chứng thư số]
                recvNo = recSupplierEnterMast.getDataFromName("RECV_NO");
                
                recPubInstituCert = rabPubInstituCertDAO.selectDetail(recvNo, conn);
                recPubInstituCertList = UM_FuncRef.getRecPubInstituCertList(bizRegNo, conn);
                Log.debug(recvNo + " " + recPubInstituCert.columnCount);
                Log.debug("A031i d:" +  recvNo );
                userEntity = UM_FuncRef.getUser_NMByBRN(bizRegNo);
              
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
        <title>Đăng kí nhà thầu</title>
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
   	 			
             // Check Contract
       	   	 for (i=0; i<ctrList.length; i++){
       		 		for(j=i+1; j<ctrList.length; j++){
       		 			if(ctrList[i].CtrNo===ctrList[j].CtrNo){
       		 				alert("Số hợp đồng không được trùng nhau. Vui lòng sửa lại.");
       		 				document.stdCtrFrm.ctrNo.focus();
       		 				return false;
       		 			}
       		 		}
       		 	}
          	 	// Check Finan
          	 	for (i=0; i<finanList.length-1; i++){
          	 		var stdYEAR=document.stdFinanFrm.stdYEAR[i];
            		
            		for(j=i+1; j<finanList.length; j++){
            			var stdYEAR2=document.stdFinanFrm.stdYEAR[j];
       	     		if(stdYEAR2.value===stdYEAR.value){
       	     			alert('Năm tài chính phải khác nhau');
       	     			stdYEAR2.focus();
       	     			return false;
       	     		}
       	     	}
         	 	}
                
                if(!validateFrm(document.bizAgentFrm, unBidAgentReq, false)) {
                    alert("Hãy điền đầy đủ thông tin người đại diện trước khi gửi đăng ký!");
                    return false;
                }

                if(!validateFrm(document.UserRegisterFrm, null, false)) {
                    alert("Hãy điền đầy đủ thông tin người được giao phụ trách dự thầu trước khi gửi đăng ký!");
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
              for (int i = 0; i < ctrList.length; i++) {
     		%>      
                 item = new ContractItem('<%=ctrList[i].data[0]%>', '<%=ctrList[i].data[1]%>', '<%=ctrList[i].data[2]%>', '<%=ctrList[i].data[3]%>', '<%=ctrList[i].data[4]%>', '<%=ctrList[i].data[5]%>', '<%=ctrList[i].data[6]%>', '<%=ctrList[i].data[7]%>', '<%=ctrList[i].data[8]%>')
                 addCtrItem(item);
            <%}
             for (int i = 0; i < finanList.length; i++) {
      		%>      
                  item = new FinanItem('<%=finanList[i].data[0]%>', '<%=finanList[i].data[1]%>', '<%=finanList[i].data[2]%>', '<%=finanList[i].data[3]%>', '<%=finanList[i].data[4]%>', '<%=finanList[i].data[5]%>', '<%=finanList[i].data[6]%>')
                  addFinanItem(item);
           <%}
            for (int i = 0; i < reportList.length; i++) {
      		%>      
                  item = new ReportItem('<%=reportList[i].data[0]%>', '<%=reportList[i].data[1]%>', '<%=reportList[i].data[2]%>', '<%=reportList[i].data[3]%>', '<%=reportList[i].data[4]%>')
                  addReportItem(item);
              	<%}%>
                    
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
                    <% if(userEntity.columnCount>0) {%>
                    document.UserRegisterFrm.useName.value = '<%=userEntity.getDataFromName("CHRGR_NM")%>';
                    document.UserRegisterFrm.departName.value = '<%=userEntity.getDataFromName("CHRGR_DEPART")%>';
                    document.UserRegisterFrm.usePhone.value = '<%=userEntity.getDataFromName("CHRGR_PHONE_NO")%>';
                    document.UserRegisterFrm.useFax.value = '<%=userEntity.getDataFromName("CHRGR_FAX")%>';
                    document.UserRegisterFrm.useMobile.value = '<%=userEntity.getDataFromName("CHRGR_MOBILE")%>';
                    document.UserRegisterFrm.useEmail.value = '<%=userEntity.getDataFromName("CHRGR_EMAIL")%>';
                    document.UserRegisterFrm.useId.value = '<%=userEntity.getDataFromName("USER_ID")%>';
           <%}%>
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
                function FinanItem(stdYEAR, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL){
                	this.stdYEAR=stdYEAR;
                	this.stdASSETS_TOTAL=stdASSETS_TOTAL;
                	this.stdDEBT_TOTAL=stdDEBT_TOTAL;
                	this.stdASSET_VALUE=stdASSET_VALUE;
                	this.stdSHORT_ASSETS=stdSHORT_ASSETS;
                	this.stdSHORT_DEBT=stdSHORT_DEBT;
                	this.stdCAPITAL=stdCAPITAL;
                	
                	this.addToFinanList = function() {
                		finanList.push(this);
            		};
            		this.removeFromFinanList = function(index) {
            			finanList = finanList.slice(0, index - 1).concat(finanList.slice(index, finanList.length));
            		};
                }
                function ReportItem(stdYEAR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER){
                	//stdYEAR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER - stdFinanFrm1
            		this.stdYEAR=stdYEAR;
                	this.stdREVENUE_TOTAL=stdREVENUE_TOTAL;
                	this.stdAVERAGE_REVENUE=stdAVERAGE_REVENUE;
                	this.stdPROFIT_BEFORE=stdPROFIT_BEFORE;
                	this.stdPROFIT_AFTER=stdPROFIT_AFTER;
                	
                	this.addToReportList = function() {
                		reportList.push(this);
            		};
            		this.removeFromReportList = function(index) {
            			reportList = reportList.slice(0, index - 1).concat(reportList.slice(index, reportList.length));
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
                                    //objFrm.reprType.value = item.ceoType;
                                    //objFrm.reprIsmain.value = item.ceoIsMaster;
                                    if ("Y" == item.ceoIsMaster) objFrm.reprIsmain[0].checked = "checked";
                                    else if ("N" == item.ceoIsMaster) objFrm.reprIsmain[1].checked = "checked";
                                } else if (objFrm.name == 'stdCLSFrm') {
                                    i = parseInt(index) - 1;
                                    currStdCLSIndex = i;
                                    item = stdCLSList[i];
			 							
                                    objFrm.stdClsName.value = item.stdClsName;
                                    objFrm.stdClsCode.value = item.stdClsCode;
// Comment by thanhtv 2012.09.11                                    
                                    //objFrm.groupName.value = item.groupName;
// The End Comment                                    
//                                    objFrm.stdClsNo.value = item.stdClsNo;
//                                    objFrm.stdClsIssuedDate.value = item.stdClsIssuedDate;
//                                    objFrm.constAbilEvalAmt.value = item.constAbilEvalAmt;
//                                    objFrm.evalStdYear.value = item.evalStdYear;
//                                    objFrm.stdClsExpiryDate.value = item.stdClsExpiryDate;
                                    //objFrm.mastStdClsYn.value = item.mastStdClsYn;
//                                    if ("Y" == item.mastStdClsYn) objFrm.mastStdClsYn[0].checked = "checked";
//                                    else if ("N" == item.mastStdClsYn) objFrm.mastStdClsYn[1].checked = "checked";
                                } else if (objFrm.name == 'stdCtrFrm') {
                        			i = parseInt(index) - 1;
                        			currCtrIndex = i;
                        			item = ctrList[i];

                        			objFrm.stdCtrNo.value = item.CtrNo;
                        			objFrm.stdCtrName.value = item.CtrName;
                        			objFrm.stdDateSign.value = item.stdDateSign;
                        			objFrm.stdCtrValue.value = item.CtrValue;
                        			
                        			objFrm.stdPriceValue.value = item.stdPriceValue;
                        			objFrm.stdPerCtrValue.value = item.stdPerCtrValue;
                        			objFrm.stdDateComplete.value = item.stdDateComplete;
                        			objFrm.stdProjectName.value = item.stdProjectName;
                        			objFrm.stdContactValue.value = item.stdContactValue;
                        			
                        		}else if (objFrm.name == 'stdFinanFrm') {
                        			i = parseInt(index) - 1;
                        			currFinanIndex = i;
                        			item = finanList[i];
                        			//stdYEAR, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL
                        			objFrm.stdYEAR.value = item.stdYEAR;
                        			objFrm.stdASSETS_TOTAL.value = item.stdASSETS_TOTAL;
                        			objFrm.stdDEBT_TOTAL.value = item.stdDEBT_TOTAL;
                        			objFrm.stdASSET_VALUE.value = item.stdASSET_VALUE;
                        			
                        			objFrm.stdSHORT_ASSETS.value = item.stdSHORT_ASSETS;
                        			objFrm.stdSHORT_DEBT.value = item.stdSHORT_DEBT;
                        			objFrm.stdCAPITAL.value = item.stdCAPITAL;
                        		}
                        		else if (objFrm.name == 'stdFinanFrm1') {
                        			i = parseInt(index) - 1;
                        			currFinanIndex1 = i;
                        			item = reportList[i];
                        			//stdYEAR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER - stdFinanFrm1
                        			objFrm.stdYEAR.value = item.stdYEAR;
                        			objFrm.stdREVENUE_TOTAL.value = item.stdREVENUE_TOTAL;
                        			objFrm.stdAVERAGE_REVENUE.value = item.stdAVERAGE_REVENUE;
                        			objFrm.stdPROFIT_BEFORE.value = item.stdPROFIT_BEFORE;
                        			objFrm.stdPROFIT_AFTER.value = item.stdPROFIT_AFTER;
                        			
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
                                    if(checkStdCls()) {
                                        tbl = document.getElementById('tblStdCls');
                                        currentRow = tbl.rows[currStdCLSIndex + 3];
							
                                        stdCLSList[currStdCLSIndex].stdClsName = currentRow.cells[1].innerHTML = objFrm.stdClsName.value;
                                        /*
                                        stdCLSList[currStdCLSIndex].stdClsCode = currentRow.cells[2].innerHTML = objFrm.stdClsCode.value;
                                        stdCLSList[currStdCLSIndex].stdClsNo = currentRow.cells[3].innerHTML = objFrm.stdClsNo.value;
                                        stdCLSList[currStdCLSIndex].stdClsIssuedDate = currentRow.cells[4].innerHTML = objFrm.stdClsIssuedDate.value;
                                        stdCLSList[currStdCLSIndex].evalStdYear = currentRow.cells[6].innerHTML = objFrm.evalStdYear.value;
	 
                                        stdCLSList[currStdCLSIndex].stdClsExpiryDate = currentRow.cells[8].innerHTML = objFrm.stdClsExpiryDate.value;
                                        stdCLSList[currStdCLSIndex].mastStdClsYn = getRdbValue(objFrm.mastStdClsYn);
                                        
                                        var imt = "";
                                        if (getRdbValue(objFrm.mastStdClsYn) == "Y") imt = "Có";
                                        else imt = "Không";
                                        currentRow.cells[7].innerHTML = imt;
                                        */
                                    }
                                }else if (objFrm.name == 'stdCtrFrm') {
                        			tbl = document.getElementById('tblstdCtr');
                        			currentRow = tbl.rows[currCtrIndex + 3];
                        			ctrList[currCtrIndex].CtrNo = currentRow.cells[1].innerHTML = objFrm.stdCtrNo.value;
                        			ctrList[currCtrIndex].CtrName = currentRow.cells[2].innerHTML = objFrm.stdCtrName.value;
                        			ctrList[currCtrIndex].stdDateSign = currentRow.cells[3].innerHTML = objFrm.stdDateSign.value;
                        			ctrList[currCtrIndex].CtrValue = currentRow.cells[4].innerHTML=  objFrm.stdCtrValue.value;
                        			ctrList[currCtrIndex].stdPriceValue = currentRow.cells[5].innerHTML=  objFrm.stdPriceValue.value;
                        			ctrList[currCtrIndex].stdPerCtrValue = currentRow.cells[6].innerHTML=  objFrm.stdPerCtrValue.value;
                        			ctrList[currCtrIndex].stdDateComplete = currentRow.cells[7].innerHTML=  objFrm.stdDateComplete.value;
                        			ctrList[currCtrIndex].stdProjectName = currentRow.cells[8].innerHTML=  objFrm.stdProjectName.value;
                        			ctrList[currCtrIndex].stdContactValue = currentRow.cells[9].innerHTML=  objFrm.stdContactValue.value;
                        		}else if (objFrm.name == 'stdFinanFrm') {
                        			tbl = document.getElementById('tblStdFinan');
                        			currentRow = tbl.rows[currFinanIndex + 3];
                        			//stdYEAR, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL
                        			finanList[currFinanIndex].stdYEAR = currentRow.cells[1].innerHTML = objFrm.stdYEAR.value;
                        			finanList[currFinanIndex].stdASSETS_TOTAL = currentRow.cells[2].innerHTML = objFrm.stdASSETS_TOTAL.value;
                        			finanList[currFinanIndex].stdDEBT_TOTAL = currentRow.cells[3].innerHTML = objFrm.stdDEBT_TOTAL.value;
                        			finanList[currFinanIndex].stdASSET_VALUE = currentRow.cells[4].innerHTML=  objFrm.stdASSET_VALUE.value;
                        			finanList[currFinanIndex].stdSHORT_ASSETS = currentRow.cells[5].innerHTML=  objFrm.stdSHORT_ASSETS.value;
                        			finanList[currFinanIndex].stdSHORT_DEBT = currentRow.cells[6].innerHTML=  objFrm.stdSHORT_DEBT.value;
                        			finanList[currFinanIndex].stdCAPITAL = currentRow.cells[7].innerHTML=  objFrm.stdCAPITAL.value;
                        		}else if (objFrm.name == 'stdFinanFrm1') {
                        			tbl = document.getElementById('tblStdFinan1');
                        			currentRow = tbl.rows[currFinanIndex1 + 3];
                        			//stdYEAR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER - stdFinanFrm1
                        			reportList[currFinanIndex1].stdYEAR = currentRow.cells[1].innerHTML = objFrm.stdYEAR.value;
                        			reportList[currFinanIndex1].stdREVENUE_TOTAL = currentRow.cells[2].innerHTML = objFrm.stdREVENUE_TOTAL.value;
                        			reportList[currFinanIndex1].stdAVERAGE_REVENUE = currentRow.cells[3].innerHTML = objFrm.stdAVERAGE_REVENUE.value;
                        			reportList[currFinanIndex1].stdPROFIT_BEFORE = currentRow.cells[4].innerHTML=  objFrm.stdPROFIT_BEFORE.value;
                        			reportList[currFinanIndex1].stdPROFIT_AFTER = currentRow.cells[5].innerHTML=  objFrm.stdPROFIT_AFTER.value;
                        			
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
                        		}else if (objFrm.name == 'stdFinanFrm') {
                        			tbl = document.getElementById('tblStdFinan');
                        			tbl.deleteRow(currFinanIndex + 3);
                        			len =finanList.length;
                        			finanList[currFinanIndex].removeFromFinanList(currFinanIndex + 1);
                        			activeIndex = currFinanIndex;
                        			activeForm = 'document.stdFinanFrm';
                        		}else if (objFrm.name == 'stdFinanFrm1') {
                        			tbl = document.getElementById('tblStdFinan1');
                        			tbl.deleteRow(currFinanIndex1 + 3);
                        			len =reportList.length;
                        			reportList[currFinanIndex1].removeFromReportList(currFinanIndex1 + 1);
                        			activeIndex = currFinanIndex1;
                        			activeForm = 'document.stdFinanFrm1';
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
                                if(document.comInfoFrm.cbNonConsultant.checked == true) clsStr += document.comInfoFrm.cbNonConsultant.value;
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

                        		var action_url='/servlet/servlets/UM_COV_ConiC010c';
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
								 <% if(userEntity.columnCount>0) {%>
								addKeyValue(form, "userName", document.UserRegisterFrm.useName.value); 
								addKeyValue(form, "userDepart",document.UserRegisterFrm.departName.value);
								addKeyValue(form, "userPhoneNo",document.UserRegisterFrm.usePhone.value);
								addKeyValue(form, "userFax",document.UserRegisterFrm.useFax.value);
								addKeyValue(form, "userMobile",document.UserRegisterFrm.useMobile.value);
								addKeyValue(form, "userEmail",document.UserRegisterFrm.useEmail.value);
								addKeyValue(form, "userId",document.UserRegisterFrm.useId.value);
								<% } %>
								
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
						    		 	addKeyValue(form, "stdClsCode[" + k + "]", 4100);        		        		        		        	
						        	}
								}
						     // Post Contract
								if (ctrList.length > 0) {
						        	addKeyValue(form, "ctrCount", ctrList.length);
						        	for(var m = 0; m < ctrList.length; m++) {
						        		//No, name, stdDateSign, value, stdPriceValue, stdPerCtrValue, stdDateComplete, stdProjectName, stdContactValue
						        		addKeyValue(form, "ctrNo[" + m + "]", ctrList[m].CtrNo);
						    		 	addKeyValue(form, "ctrName[" + m + "]", ctrList[m].CtrName);
						    		 	addKeyValue(form, "stdDateSign[" + m + "]", ctrList[m].stdDateSign);
						    		 	addKeyValue(form, "ctrValue[" + m + "]", ctrList[m].CtrValue);
										
						    		 	addKeyValue(form, "stdPriceValue[" + m + "]", ctrList[m].stdPriceValue);
						    		 	addKeyValue(form, "stdPerCtrValue[" + m + "]", ctrList[m].stdPerCtrValue);
						    		 	addKeyValue(form, "stdDateComplete[" + m + "]", ctrList[m].stdDateComplete);
						    		 	addKeyValue(form, "stdProjectName[" + m + "]", ctrList[m].stdProjectName);
						    		 	addKeyValue(form, "stdContactValue[" + m + "]", ctrList[m].stdContactValue);
						        	}
								}
								// Post Finan
								if (finanList.length > 0) {
						        	addKeyValue(form, "ctrFinan", finanList.length);
						        	for(var m = 0; m < finanList.length; m++) {
						        		addKeyValue(form, "stdYEAR[" + m + "]", finanList[m].stdYEAR);
						    		 	addKeyValue(form, "stdASSETS_TOTAL[" + m + "]", finanList[m].stdASSETS_TOTAL);
						    		 	addKeyValue(form, "stdDEBT_TOTAL[" + m + "]", finanList[m].stdDEBT_TOTAL);
						    		 	addKeyValue(form, "stdASSET_VALUE[" + m + "]", finanList[m].stdASSET_VALUE);
										addKeyValue(form, "stdSHORT_ASSETS[" + m + "]", finanList[m].stdSHORT_ASSETS);
						    		 	addKeyValue(form, "stdSHORT_DEBT[" + m + "]", finanList[m].stdSHORT_DEBT);
						    		 	addKeyValue(form, "stdCAPITAL[" + m + "]", finanList[m].stdCAPITAL);
						    		}
								}
								if (reportList.length > 0) {
						        	addKeyValue(form, "ctrReport", reportList.length);
						        	for(var m = 0; m < reportList.length; m++) {
						        		//stdYEAR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER - stdFinanFrm1
						        		addKeyValue(form, "stdYEAR[" + m + "]", reportList[m].stdYEAR);
						    		 	addKeyValue(form, "stdREVENUE_TOTAL[" + m + "]", reportList[m].stdREVENUE_TOTAL);
						    		 	addKeyValue(form, "stdAVERAGE_REVENUE[" + m + "]", reportList[m].stdAVERAGE_REVENUE);
						    		 	addKeyValue(form, "stdPROFIT_BEFORE[" + m + "]", reportList[m].stdPROFIT_BEFORE);
										addKeyValue(form, "stdPROFIT_AFTER[" + m + "]", reportList[m].stdPROFIT_AFTER);
						    	
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
                            function btnCtrAdd()
                        	{
                        		//alert(document.stdCLSFrm.stdClsIssuedDate.value);
                        		//if (validateFrm(document.stdCLSFrm, new Array('constAbilEvalAmt', 'evalStdYear'), true))
                        		var No=document.stdCtrFrm.stdCtrNo.value;
                        		var name=document.stdCtrFrm.stdCtrName.value;
                        		var stdDateSign = document.stdCtrFrm.stdDateSign.value;
                        		var ctrValue=document.stdCtrFrm.stdCtrValue.value;
                        		
                        		var stdPriceValue=document.stdCtrFrm.stdPriceValue.value;
                        		var stdPerCtrValue=document.stdCtrFrm.stdPerCtrValue.value;
                        		var stdDateComplete=document.stdCtrFrm.stdDateComplete.value;
                        		var stdProjectName=document.stdCtrFrm.stdProjectName.value;
                        		var stdContactValue=document.stdCtrFrm.stdContactValue.value;
                        		
                                 if (No ==''){
                              	   alert("Bạn chưa nhập tên và số hợp đồng");
                                     document.stdCtrFrm.stdCtrNo.focus();   
                                     return;
                                 }
                                 if (name ==''){
                              	   alert("Bạn chưa nhập tên hợp đồng");
                                     document.stdCtrFrm.stdCtrName.focus();   
                                     return;
                                 }
                                 if (stdDateSign ==''){
                                	   alert("Bạn chưa nhập ngày ký");
                                       document.stdCtrFrm.stdDateSign.focus();   
                                       return;
                                   }
                                 if (ctrValue ==''){
                              	   alert("Bạn chưa nhập giá trị hợp đồng");
                                     document.stdCtrFrm.stdCtrValue.focus();   
                                     return;
                                 }
                                 if (stdPriceValue ==''){
                               	   alert("Bạn chưa nhập giá trị hợp đồng");
                                   document.stdCtrFrm.stdPriceValue.focus();   
                                   return;
                                 }
                                 if (stdPerCtrValue ==''){
                                 	   alert("Bạn chưa nhập giá trị hợp đồng");
                                     document.stdCtrFrm.stdPerCtrValue.focus();   
                                     return;
                                   }
                                 if (stdDateComplete ==''){
                                 	   alert("Bạn chưa nhập giá trị hợp đồng");
                                     document.stdCtrFrm.stdDateComplete.focus();   
                                     return;
                                   }
                                 if (stdProjectName ==''){
                                 	   alert("Bạn chưa nhập giá trị hợp đồng");
                                     document.stdCtrFrm.stdProjectName.focus();   
                                     return;
                                   }
                                 if (stdContactValue ==''){
                                 	   alert("Bạn chưa nhập giá trị hợp đồng");
                                     document.stdCtrFrm.stdContactValue.focus();   
                                     return;
                                   }
                                 for (var i=0; i<ctrList.length; i++){
                    	 			if(No===ctrList[i].CtrNo){
                    	 				alert("Hợp đồng đã tồn tại");
                    	 				document.stdCtrFrm.stdCtrNo.focus();
                    	 				return;
                    	 			}
                     		 	}
                        		addItem(new ContractItem(No, name, stdDateSign, ctrValue, stdPriceValue, stdPerCtrValue, stdDateComplete, stdProjectName, stdContactValue));
                        	}
                            function btnFinanAdd(){
                            	//stdYEAR, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL
                            	//alert('btnFinanAdd');
                            	var stdYEAR=document.stdFinanFrm.stdYEAR.value;
                        		var stdASSETS_TOTAL=document.stdFinanFrm.stdASSETS_TOTAL.value;
                        		var stdDEBT_TOTAL = document.stdFinanFrm.stdDEBT_TOTAL.value;
                        		var stdASSET_VALUE=document.stdFinanFrm.stdASSET_VALUE.value;
                        		var stdSHORT_ASSETS=document.stdFinanFrm.stdSHORT_ASSETS.value;
                        		var stdSHORT_DEBT=document.stdFinanFrm.stdSHORT_DEBT.value;
                        		var stdCAPITAL=document.stdFinanFrm.stdCAPITAL.value;
                        		if (stdYEAR ==''){
                                	   alert("Bạn chưa nhập năm tài chính");
                                    document.stdFinanFrm.stdYEAR.focus();   
                                    return;
                                 }
                        		if (stdASSETS_TOTAL ==''){
                                	   alert("Bạn chưa nhập tổng tài sản");
                                    document.stdFinanFrm.stdASSETS_TOTAL.focus();   
                                    return;
                                 }
                        		if (stdDEBT_TOTAL ==''){
                                	   alert("Bạn chưa nhập tổng nợ");
                                    document.stdFinanFrm.stdDEBT_TOTAL.focus();   
                                    return;
                                 }
                        		if (stdASSET_VALUE ==''){
                                	   alert("Bạn chưa nhập giá trị tài sản ròng");
                                    document.stdFinanFrm.stdASSET_VALUE.focus();   
                                    return;
                                 }
                        		if (stdSHORT_ASSETS ==''){
                                	   alert("Bạn chưa nhập tài sản ngắn hạn");
                                    document.stdFinanFrm.stdSHORT_ASSETS.focus();   
                                    return;
                                 }
                        		if (stdSHORT_DEBT ==''){
                                	   alert("Bạn chưa nhập nợ ngắn hạn");
                                    document.stdFinanFrm.stdSHORT_DEBT.focus();   
                                    return;
                                 }
                        		if (stdCAPITAL ==''){
                                	   alert("Bạn chưa nhập vốn lưu động");
                                    document.stdFinanFrm.stdCAPITAL.focus();   
                                    return;
                                 }
                        		addItem(new FinanItem(stdYEAR, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL));
                            		
                            }
                            function btnFinanAdd1(){
                            	//stdYEAR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER - stdFinanFrm1
                            	//alert('btnFinanAdd1');
                            	var stdYEAR = document.stdFinanFrm1.stdYEAR.value;
                        		var stdREVENUE_TOTAL = document.stdFinanFrm1.stdREVENUE_TOTAL.value;
                        		var stdAVERAGE_REVENUE = document.stdFinanFrm1.stdAVERAGE_REVENUE.value;
                        		var stdPROFIT_BEFORE = document.stdFinanFrm1.stdPROFIT_BEFORE.value;
                        		var stdPROFIT_AFTER = document.stdFinanFrm1.stdPROFIT_AFTER.value;
                        		
                        		if (stdYEAR ==''){
                                	   alert("Bạn chưa nhập năm tài chính");
                                    document.stdFinanFrm1.stdYEAR.focus();   
                                    return;
                                 }
                        		if (stdREVENUE_TOTAL ==''){
                                	   alert("Bạn chưa nhập tổng doanh thu");
                                    document.stdFinanFrm1.stdREVENUE_TOTAL.focus();   
                                    return;
                                 }
                        		if (stdAVERAGE_REVENUE ==''){
                                	   alert("Bạn chưa nhập daonh thu bình quân hàng năm");
                                    document.stdFinanFrm1.stdAVERAGE_REVENUE.focus();   
                                    return;
                                 }
                        		if (stdPROFIT_BEFORE ==''){
                                	   alert("Bạn chưa nhập lợi nhuận trước thuế");
                                    document.stdFinanFrm1.stdPROFIT_BEFORE.focus();   
                                    return;
                                 }
                        		if (stdPROFIT_AFTER ==''){
                                	   alert("Bạn chưa nhập lợi nhuận sau thuế");
                                    document.stdFinanFrm1.stdPROFIT_AFTER.focus();   
                                    return;
                                 }
                        		
                        		addItem(new ReportItem(stdYEAR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER));
                            		
                            }
                            
                            function delFinanItem(obj){
                            	document.getElementById("tblStdFinan").deleteRow(obj.parentNode.parentNode.rowIndex);
                            	currFinanIndex--;
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
            <h1 class="pageTitle"><i class="icon-title"></i>Chỉnh sửa thông tin nhà thầu</h1>
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
                            <td colspan="3" class="tdb"><input type="text" name="bizName"   maxlength="200" style="width:100%" onkeypress="return onlyAlphabet(event, this);" onblur="if (isValidatedInputObj(this)) {FieldChecker(this, '250', 'M', 's');}"/></td>
                        </tr>
                        <tr>
                            <td width="20%" class="tdar"> Tên tiếng Anh</td>
                            <td colspan="3" class="tdb"><input type="text" name="bizEnName"  maxlength="150" style="width:100%" onkeypress="return onlyAlphabet(event, this);" onblur="if (isValidatedInputObj(this)) {FieldChecker(this, '150', 'M', 's');}"/></td>
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
                                <% if (supplierCls.indexOf('5') != -1) {%>
                                <input type="checkbox" value="5" name="cbConsultant" checked="checked"> Tư vấn
                                <% } else {%>
                                <input type="checkbox" value="5" name="cbConsultant"> Tư vấn
                                <% }%>
                                 <% if (supplierCls.indexOf('7') != -1) {%>
                                <input type="checkbox" value="7" name="cbNonConsultant" checked="checked"> Phi tư vấn
                                <% } else {%>
                                <input type="checkbox" value="7" name="cbNonConsultant"> Phi tư vấn
                                <% }%>
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
                                    <option value="07">Công ty 100% vốn nước ngoài</option>
									<option value="08">Khác</option>
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
                            <td colspan="3" class="tdb"><input type="text" name="addr" size="100" maxlength="1000"  style="width:100%"/> </td>
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
                            <td class="tdb" width="30%"><input type="text" name="reprIdentNo" value="" size="15" maxlength="15"  /></td>
                        </tr>
                        <tr>
                            <td class="tdar" width="20%"><font color="red">*</font> Số điện thoại</td>
                            <td class="tdb" width="30%"><input type="text" name="reprMobile" value="" size="25" maxlength="20" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '20', 'C', 'n');"/></td>

                            <td class="tdar" width="20%"><font color="red">*</font> Địa chỉ email</td>
                            <td width="30%" class="tdb"><input type="text" name="reprEmail" value="" size="24" maxlength="50" onblur="return checkEmail(this);"/></td>
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
                <form name="stdCtrFrm" >
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
	<tr>
		<th align="left" colspan="2">[Hợp đồng tương tự do nhà thầu thực hiện]</th>
		<th align="right" colspan="2">

			<input type="button" class="commonbutton" value="Thêm mới" name="btn_Add" style="float: right; display: block;" onclick="btnCtrAdd();">

			<input type="button" class="commonbutton" value="Làm lại" name="btn_Reset" style="display: block;" onclick="reset(this.form);">
			<input type="button" class="commonbutton" style="display: none; float: right;" value="Xóa" name="btn_Delete" onclick="del(this.form);">
			<input type="button" class="commonbutton" style="display: none" value="Cập nhật" name="btn_Update" onclick="update(this.form);">
		</th>
	</tr>
	<tr>
		<td height="4" class="LINE" colspan="4" />
	</tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Tên và số hợp đồng</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdCtrNo" maxlength="200" style="width: 100%" onblur="FieldChecker(this, '200', 'M', 's');"/>
        </td>
    </tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Tên thành viên liên danh</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdCtrName" maxlength="200" style="width: 100%" onblur="FieldChecker(this, '200', 'M', 's');"/>
        </td>
    </tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Ngày ký hợp đồng</td>
        <td class="tdb" width="20%" colspan="3">
            <input style="width: 15%" onblur="if(!isDate(this.value)){this.focus();};" class="read" type="text" name="stdDateSign" maxlength="25" title="Ngày ký theo định dạng dd/mm/yyyy">
			<a href="javascript: Calendar_D(document.stdCtrFrm.stdDateSign);"><img src="../img/calendar.gif" border="0"></a>
        </td>
    </tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Liên danh</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdCtrValue" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
	<tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Giá<br/>hợp đồng</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdPriceValue" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Tỷ lệ liên danh</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdPerCtrValue" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
     <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Ngày hoàn thành</td>
        <td class="tdb" width="20%" colspan="3">
            <input style="width: 15%" onblur="if(!isDate(this.value)){this.focus();};" class="read" type="text" name="stdDateComplete" maxlength="25" title="Ngày ĐKKD theo định dạng dd/mm/yyyy">
			<a href="javascript: Calendar_D(document.stdCtrFrm.stdDateComplete);"><img src="../img/calendar.gif" border="0"></a>
        </td>
    </tr>
     <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Tên dự án chủ đầu tư</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdProjectName" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
     <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Địa chỉ điện thoại/Fax/Email</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdContactValue" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
</table>
<table width="100%" id="tblStdCtr">
	<tr height="1">
		<td colspan="10" class="line"></td>
	</tr>
	<tr align="center">
		<td class="tdac" width="5%">STT</td>
		<td class="tdac" width="10%">Tên và số<br/>hợp đồng</td>
		<td class="tdac" width="15%">Tên thành viên<br/>liên danh</td>
		<td class="tdac" width="10%">Ngày ký hợp đồng</td>
		<td class="tdac" width="10%">Liên danh</td>
		<td class="tdac" width="10%">Giá<br/>hợp đồng</td>
		<td class="tdac" width="10%">Tỷ lệ<br/>liên danh</td>
		<td class="tdac" width="10%">Ngày<br/>hoàn thành</td>
		<td class="tdac" width="10%">Tên dự án<br/>chủ đầu tư</td>
		<td class="tdac" width="10%">Địa chỉ<br/>điện thoại<br/>/Fax/Email</td>
	</tr>
	<tr height="1">
		<td colspan="10" class="line"></td>
	</tr>
</table>
<table width="100%" cellspacing="1" cellpadding="2" class="tr" style="display:none;">
	<tr>
		<td height="2" class="LINE" colspan="10" />
	</tr>
</table>
</form>

<form name="stdFinanFrm" method="post" enctype="multipart/form-data"  acceptcharset="UTF-8"  target="regis_iframe">
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
	<tr>
		<th align="left" colspan="2">[Bảng cân đối kế toán]</th>
		<th align="right" colspan="2">
			<input type="button" class="commonbutton" value="Thêm mới" name="btn_Add" style="float: right; display: block;" onclick="btnFinanAdd();">
			
			<input type="button" class="commonbutton" value="Làm lại" name="btn_Reset" style="display: block;" onclick="reset(this.form);">
			<input type="button" class="commonbutton" style="display: none; float: right;" value="Xóa" name="btn_Delete" onclick="del(this.form);">
			<input type="button" class="commonbutton" style="display: none" value="Cập nhật" name="btn_Update" onclick="update(this.form);">
			
		</th>
	</tr>
	<tr>
		<td height="4" class="LINE" colspan="4" />
	</tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Năm</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdYEAR" maxlength="4" style="width: 100%" onblur="FieldChecker(this, '4', 'M', 's');"/>
        </td>
    </tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Tổng tài sản</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdASSETS_TOTAL" maxlength="200" style="width: 100%" onblur="FieldChecker(this, '200', 'M', 's');"/>
        </td>
    </tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Tổng nợ</td>
        <td class="tdb" width="75%" colspan="3">
        	<input type="text" name="stdDEBT_TOTAL" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Giá trị tài sản ròng</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdASSET_VALUE" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
	<tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Tài sản ngắn hạn</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdSHORT_ASSETS" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Nợ ngắn hạn</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdSHORT_DEBT" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
  	<tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Vốn lưu động</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdCAPITAL" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
</table>
<table width="100%" id="tblStdFinan" >
	<tr height="1">
		<td colspan="8" class="line"></td>
	</tr>
	<tr align="center">
		<td class="tdac" width="5%">STT</td>
		<td class="tdac" width="10%">Năm</td>
		<td class="tdac" width="15%">Tổng tài sản</td>
		<td class="tdac" width="15%" >Tổng nợ</td>
		<td class="tdac" width="15%" >Giá trị tài sản ròng</td>
		<td class="tdac" width="15%" >Tài sản ngắn hạn</td>
		<td class="tdac" width="10%" >Nợ ngắn hạn</td>
		<td class="tdac" width="15%" >Vốn lưu động</td>
	</tr>
	<tr height="1">
		<td colspan="8" class="line"></td>
	</tr>
</table>
<table width="100%" cellspacing="1" cellpadding="2" class="tr" style="display:none;">
	<tr>
		<td height="2" class="LINE" colspan="8" />
	</tr>
</table>
</form>

<form name="stdFinanFrm1" >
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
	<tr>
		<th align="left" colspan="2">[Báo cáo kết quả kinh doanh]</th>
		<th align="right" colspan="2">
			<input type="button" class="commonbutton" value="Thêm mới" name="btn_Add" style="float: right; display: block;" onclick="btnFinanAdd1();">
			
			<input type="button" class="commonbutton" value="Làm lại" name="btn_Reset" style="display: block;" onclick="reset(this.form);">
			<input type="button" class="commonbutton" style="display: none; float: right;" value="Xóa" name="btn_Delete" onclick="del(this.form);">
			<input type="button" class="commonbutton" style="display: none" value="Cập nhật" name="btn_Update" onclick="update(this.form);">
			
		</th>
	</tr>
	<tr>
		<td height="4" class="LINE" colspan="4" />
	</tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Năm</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdYEAR" maxlength="4" style="width: 100%" onblur="FieldChecker(this, '4', 'M', 's');"/>
        </td>
    </tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Tổng doanh thu</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdREVENUE_TOTAL" maxlength="200" style="width: 100%" onblur="FieldChecker(this, '200', 'M', 's');"/>
        </td>
    </tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Doanh thu bình quân hàng năm</td>
        <td class="tdb" width="75%" colspan="3">
        	<input type="text" name="stdAVERAGE_REVENUE" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
    <tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Lợi nhuận trước thuế</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdPROFIT_BEFORE" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
	<tr>
        <td class="tdar" width="25%" ><font color="red">*</font>Lợi nhuận sau thuế</td>
        <td class="tdb" width="75%" colspan="3">
            <input type="text" name="stdPROFIT_AFTER" maxlength="100" style="width: 100%" onblur="FieldChecker(this, '100', 'M', 's');"/>
        </td>
    </tr>
   
</table>
<table width="100%" id="tblStdFinan1" >
	<tr height="4">
		<td colspan="6" class=""></td>
	</tr>
	<tr height="1">
		<td colspan="6" class="line"></td>
	</tr>
	<tr align="center">
		<td class="tdac" width="5%">STT</td>
		<td class="tdac" width="10%">Năm</td>
		<td class="tdac" width="15%">Tổng doanh thu</td>
		<td class="tdac" width="40%">Doanh thu bình quân hằng năm từ hoạt động<br/>sản xuất kinh doanh</td>
		<td class="tdac" width="15%" >Lợi nhuận<br/>trước thuế</td>
		<td class="tdac" width="15%" >Lợi nhuận<br/>sau thuế</td>
	</tr>
	<tr height="1">
		<td colspan="6" class="line"></td>
	</tr>
</table>
<table width="100%" cellspacing="1" cellpadding="2" class="tr" style="display:none;">
	<tr>
		<td height="2" class="LINE" colspan="4" />
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
                            <td class="tdb" width="30%"><input type="text" name="bizAgentEmail" value="" size="25" maxlength="50" onblur="return checkEmail(this);"/></td>
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
                  <% if(userEntity.columnCount>0) {%>
                <form name="UserRegisterFrm">
                	<input type='hidden' name='useId'/>
                <!---------------------- 인증서정보 타이틀 테이블 시작 ------------------------>
       
        <table width="100%" cellpadding="2" cellspacing="1">
          <tr> 
            <td class="fontb">[Thông tin người được giao phụ trách dự thầu] <!-- 인증서정보 --></td>
          </tr>
        </table>
        <!---------------------- 인증서정보 타이틀 테이블 끝 ------------------------>
        <!---------------------- 인증서정보 테이블 시작 ------------------------>
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
          <tr> 
            <td height="4" colspan="4" class="line"></td>
          </tr>
          <tr>
          	<td class="tdar" width="22%"><p><font color="Red">*</font> Người phụ trách <!-- 담당자명 --></p></td>
            <td class="tdb" width="28%"><input type="text" name="useName" onKeyUp="sc_check(this);" maxlength="50" class="read"></td>
            <td class="tdar" width="22%"><p><font color="Red">*</font> Phòng/Ban <!-- 담당부서 --></p></td>
            <td class="tdb" width="28%"><input type="text" name="departName" onKeyUp="sc_check(this);" maxlength="25" class="read"></td>
            
          </tr><!-- 특수문자 체크 2007-12-13 -->
		  
		  <tr>
			<td class="tdar"> <p><font color="Red">*</font> Số điện thoại  <!-- 담당자전화번호 --></p>  </td>
            <td class="tdb"><input type="text" name="usePhone" maxlength="20" class="read"  onkeypress="return isNumberKey(event)">
			</td>
            <td class="tdar"> <p>&nbsp;&nbsp;Số Fax  <!-- 담당자팩스번호 --></p>  </td>
            <td class="tdb"><input type="text" name="useFax" maxlength="20" class="read" onkeypress="return isNumberKey(event)">
			</td>
        </tr>
        <tr>
            <td class="tdar">
            <p><font color="Red">*</font> Số di động <!-- 이동전화번호 --></p>
            </td>
            <td class="tdb">
				<input type="text" name="useMobile" maxlength="20" class="read" onkeypress="return isNumberKey(event)">
			</td>

            <td class="tdar"><p><font color="Red">*</font> Địa chỉ email<!-- 메일주소 --></p></td>
            <td class="tdb"><input type="text" name="useEmail" maxlength="50" size = "30" class="read"></td>
          </tr>
                </table>
                </form>
                <% } %>
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
                            <td class="tdb" width="30%"><input type="text" name="caReprName" id="caReprName" value=""  size="25" maxlength="60" onblur="FieldChecker(this, '60', 'M', 's');" ></td>
                            <td class="tdar" width="20%"><font color="red">*</font> Số CMND</td>
                            <td class="tdb" width="30%"><input type="text" name="caReprIdentNo" id="caReprIdentNo"  size="15" maxlength="15" ></td>
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
                                Cục Quản lý Đấu thầu - Bộ Kế hoạch và Đầu tư</td>
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
                            <input type="button" class="commonbutton" value="Chỉnh sửa" onclick="make();" name="btn_Accept" id = "btn_Accept" />
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