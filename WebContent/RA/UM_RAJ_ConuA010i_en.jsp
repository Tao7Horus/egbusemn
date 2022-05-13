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
	/* Composer : Nguyễn Viết Trọng 16.05.2009                      		*/
	/***************************************************************************/
%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.io.*,java.util.*,common.*, java.sql.*"%>
<jsp:useBean id="nationalCtrl" class="beans.NationalRefDAO" scope="page" />
<jsp:useBean id="clsList"   class="dao.UM_RAB_ListStdCls" scope="page" />							<!-- Danh mục phân loại nghành nghề -->
<jsp:useBean id="recBidAgentDAO"   class="dao.UM_RAB_RecBidAgent" scope="page" />					<!-- Thao tác bảng người đại diện dự thầu -->
<jsp:useBean id="recEnterStdClsDAO"   class="dao.UM_RAB_RecEnterStdCls" scope="page" />				<!-- Thao tác bảng mã nghành nghề -->
<jsp:useBean id="recPubInstituCertDAO"   class="dao.UM_RAB_RecPubInstituCert" scope="page" />		<!-- Thao tác bảng đăng ký CA -->
<jsp:useBean id="recReprDAO"   class="dao.UM_RAB_RecRepr" scope="page" />							<!-- Thao tác bảng người phụ trách -->
<jsp:useBean id="recSupplierEnterMastDAO"   class="dao.UM_RAB_RecSupplier" scope="page" />	<!-- Thao tác bảng nhà thầu -->
<jsp:useBean id="recCtr"   class="dao.UM_RAB_RecContract" scope="page" />	
<jsp:useBean id="recFina"   class="dao.UM_RAB_RecFinanYear" scope="page" />	

<jsp:useBean id="mastBidAgentDAO" class="dao.UM_RAB_MastBidAgent" scope="page" />
<jsp:useBean id="mastEnterStdClsDAO" class="dao.UM_RAB_MastEnterStdCls" scope="page" />
<jsp:useBean id="mastReprDAO" class="dao.UM_RAB_MastRepr" scope="page" />
<jsp:useBean id="mastSupplierEnterMastDAO" class="dao.UM_RAB_MastSupplier" scope="page" />
<jsp:useBean id="ctrDao"   class="dao.UM_RAB_Contract" scope="page" />	
<jsp:useBean id="finaDao"   class="dao.UM_RAB_FinanYear" scope="page" />
<jsp:useBean id="funcCtrl" class="beans.FuncRefDAO" scope="page" />

<%
// Mode =0	Đăng ký mới
// Mode=1	Sửa trước khi được phê duyệt
// Mode=2 Sửa sau khi được phê duyệt
	String mode = request.getParameter("mode");
	String bizRegNo = request.getParameter("bizRegNo");
	String recvNo = request.getParameter("recvNo");
	String step           = request.getParameter("step");
	if(step==null|| step.length()==0)step="0";
	if(mode==null|| mode.length()==0)mode="0";
	Trx tr = null;
	Connection conn = null;
	CommEntity [] reprList = null;
	CommEntity [] stdClsList = null;
 	CommEntity[] ctrList = null;
    CommEntity[] finanList = null;
    CommEntity[] reportList = null;
    
	CommEntity bidAgent = null;
	OneRowEntity recSupplierEnterMast = null;
	OneRowEntity recPubInstituCert = null;
	String appReqCode = "";
	boolean existData=("1".equals(mode) || "5".equals(step));
	try {
		tr = new Trx(this);
		conn = tr.getConnection();
		if ("5".equals(step)){
			//Lấy thông tin [Thông tin người phụ trách]
			reprList = mastReprDAO.getList(bizRegNo, conn);
			//Lấy thông tin [Thông tin mã ngành nghề]
			stdClsList = mastEnterStdClsDAO.getList(bizRegNo, conn);
			ctrList = ctrDao.getListSign(bizRegNo, conn);
			finanList = finaDao.getListBalance(bizRegNo, conn);
			
			reportList = finaDao.getListReport(bizRegNo, conn);
			//Lấy thông tin [Người đại diện dự thầu]
			try{
				bidAgent = mastBidAgentDAO.getList(bizRegNo, conn)[0];
			}catch(ArrayIndexOutOfBoundsException e){
				throw new Exception("Not found Agent Information.");
			}
			//Lấy thông tin [Thông tin chung]
			recSupplierEnterMast = mastSupplierEnterMastDAO.selectDetail(bizRegNo, conn);
			//Lấy thông tin [Đăng ký chứng thư số]
// 			recvNo = recSupplierEnterMast.getDataFromName("RECV_NO");
			recPubInstituCert = recPubInstituCertDAO.selectDetail(recvNo, conn);
		}else if("1".equals(mode)) {

			//Lấy thông tin [Thông tin người phụ trách]
			reprList = recReprDAO.getList(bizRegNo, conn);

			//Lấy thông tin [Thông tin mã ngành nghề]
			stdClsList = recEnterStdClsDAO.getList(bizRegNo, conn);
			ctrList = recCtr.getListSign(bizRegNo, conn);
			finanList = recFina.getListBalance(bizRegNo, conn);
			reportList = recFina.getListReport(bizRegNo, conn);
			
			//Lấy thông tin [Người đại diện dự thầu]
			bidAgent = recBidAgentDAO.getList(bizRegNo, conn)[0];

			//Lấy thông tin [Thông tin chung]
			recSupplierEnterMast = recSupplierEnterMastDAO.getDetail(bizRegNo, recvNo, conn);

			//Lấy thông tin [Đăng ký chứng thư số]
			recvNo = recSupplierEnterMast.getDataFromName("RECV_NO");
			recPubInstituCert = recPubInstituCertDAO.selectDetail(recvNo, conn);
		}
	} catch(Exception ex){
		out.println(ex.getMessage());
		if (conn != null)
			try {
				conn.rollback();
			} catch (Exception exm1) {
			}
		if (conn != null)
			try {
				conn.setAutoCommit(true);
			} catch (Exception exm1) {
			}
			return;
	} finally {
		if (conn != null)
		try {
			tr.close();
		} catch (Exception exf) {
			exf.printStackTrace();
		}
	}
	
	String bizRegGen=funcCtrl.getNextBizReg("eGP");

%>
<html>
<head>
<title>Bidder registration</title>
<meta http-equiv='Content-Type' Content='text/html; charset=utf-8'>
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
	.nonevondieule{
		display:none;
	}
</style>
<script language="javascript" src="/calendar/js/calendar.js"></script>
<script src="http://muasamcong.mpi.gov.vn:8070/js/utils.js" language="javascript"></script>
<script src="http://muasamcong.mpi.gov.vn:8070/js/SupplierRegister.js" language="javascript"></script>
<script src="http://muasamcong.mpi.gov.vn:8070/js/tel_check.js" language="javascript"></script>
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
	
	var bizRegGen = '<%=bizRegGen%>';
	
	function addKeyValue(form, key, value){
		var input = document.createElement('input');
	    input.type = 'hidden';
	    input.name = key;
	    input.value = value;
	    form.appendChild(input);
	}
	function register(mode){
		
		if (!validateAll()) {
			return;
		}
		var action_url='/servlet/servlets/UM_RAV_ConuA010i_en';
		var form=document.stdFinanFrm;
		
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
// 	    var html = '<body></body>';
// 	    iframe.src = 'data:text/html;charset=utf-8,' + encodeURI(html);
	 	// Add to document...
	    form.parentNode.appendChild(iframe);
	    
		var sCLS = getSupplierCls();
		if (sCLS == "") {
			alert("Must choice Subject matter.");
			document.comInfoFrm.cbProduction.focus();
			return;
		}

		
		//comInfoFrm form 
        addKeyValue(form, "bizRegNo", '<%=bizRegGen%>');
        addKeyValue(form, "bizName", document.comInfoFrm.bizEnName.value);
//         addKeyValue(form, "bizName", encodeURI(document.comInfoFrm.bizName.value));
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
		
		
		addKeyValue(form, "liceS", document.CARegisterFrm.liceS.value);
		addKeyValue(form, "caReprName",  document.CARegisterFrm.caReprName.value);
		addKeyValue(form, "caReprIdentNo", document.CARegisterFrm.caReprIdentNo.value);
		addKeyValue(form, "caUserId",document.CARegisterFrm.caUserId.value);
		addKeyValue(form, "permitBranch", document.CARegisterFrm.permitBranch.value);
		addKeyValue(form, "choiceCA", getRdbValue(document.CARegisterFrm.choiceCA));

        //add by kien bui duc 
        //25/08/2014
        //reprFrm form
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
        		addKeyValue(form, "stdYEAR2[" + m + "]", finanList[m].stdYEAR2);
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
		
		//step=0 -> data will be store in rec table
		
		addKeyValue(form, "step", <%=step%>);
		if (mode == 1) {
			addKeyValue(form, "mode", 1);
		 	addKeyValue(form, "key", "'"+ bizRegGen +"'");
		 	addKeyValue(form, "prevRecvNo", '<%=recvNo%>');	
		}
	 	//int len=form.stdFinanYear.length
	 	
	    window.frames['regis_iframe'].name = "regis_iframe"; 
	    window.frames['regis_iframe'].document.write("<html><head><meta http-equiv='Content-Type' Content='text/html; charset=utf-8'></head><body></body></html>");
	    
	    var iframeId = document.getElementById("regis_iframe");	    
	 	var content="";
	  //  Add event...
		    var eventHandler = function () {
	            if (iframeId.detachEvent) iframeId.detachEvent("onload", eventHandler);
	            else iframeId.removeEventListener("load", eventHandler, false);
	            
            	if (iframeId.contentWindow) {
            		content = iframeId.contentWindow.document.body.innerHTML;
           		} 
	            //alert('mode='+mode+' & content='+content);
	            if (content.length != 0) {
	            	if(mode!=0){
	            		alert('Update Successful!');	
	            	}else{
	            		alert('Register success!');	
	            	}
	            	if(mode==2){
	            		window.location.href = "/RA/UM_RAJ_ConuA010i.jsp?bizRegNo=" + bizRegGen + "&recvNo=" + <%=recvNo%> + "&step=5&mode=2";
	            		return;
	            	}
		         	window.location.href = "/RA/UM_RAJ_ConuA040i_en.jsp?bizRegNo=" + bizRegGen+ "&recvCode=" + content + "&choiceCA=" + getRdbValue(document.CARegisterFrm.choiceCA);
		            window.open("/RA/UM_RAJ_ConuA020i_en.jsp?bizRegNo=" + bizRegGen + "&recvCode=" + content + "&choiceCA=" + getRdbValue(document.CARegisterFrm.choiceCA), "newWIn", "width=700, height=1000, top=10,left=10,scrollbars=yes, toolbar=yes, directories=yes");
	         	
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
		            
	            } else {
	         		alert('Registry fail!\nPlease, check info!');
	         	}
	            
	        }
	 
		    if (iframeId.addEventListener) iframeId.addEventListener("load", eventHandler, true);
		    if (iframeId.attachEvent) iframeId.attachEvent("onload", eventHandler);
	 

	    form.setAttribute("action", action_url);
	    form.setAttribute("acceptCharset", "UTF-8");
	    
	    form.submit();
	    
	    
	}
	
	
    function suyoSearch2 ( a, b, c){
            s = window.open("/servlet/dao/EP_COV_GTQ952?isUse=Y&suyoCode=&suyoName=&a="+a+"+&b="+b+"&formName="+c ,"s" , "width=500 , height=555 , scrollbars=yes , ");
    }
        
	function onlyEng(objtext1) {
		var inText = objtext1.value;
		var ret;
		for (var i = 0; i < inText.length; i++) {
			ret = inText.charCodeAt(i);
			if ((ret > 122) || (ret < 48) || (ret > 57 && ret < 65) || (ret > 90 && ret < 97)) {
				alert("Latin character and not special character.");
				objtext1.value = "";
				objtext1.focus();
				return false;
			}
		}
		return true;
	}
	/*------------------------Validate field------------------------*/

	function getCurrentDate() {//format: dd/mm/yyyy
		var now = new Date();
		var month = now.getMonth() + 1;
		var day = now.getDate();
		var year = now.getFullYear();
		if (day < 10) day = "0" + day;
		if (month < 10) month = "0" + month;
		return day + "/" + month + "/" + year;
	}

	function FieldChecker(fld, fmt, cnd, dt) {
	    var rtnVal = FieldCheckerInJS(fld, fmt, cnd, dt);
	    if (rtnVal == -1)
	    {
	        alert("Too long!");
	    }
	    else if (rtnVal == -2)
	    {
	        alert("You cannot input special char. ::~`@#$^_\|<>{}[]");
	    }
	    else if (rtnVal == -3)
	    {
	        alert("Data uncorrect.");
	    }
	    else if (rtnVal == -4)
	    {
	        alert("Not validation.");
	    }
	    if (rtnVal < 0) {
	        fld.focus();
	    }
	    return(rtnVal);
	}

	function checkFixSize(fld, fmt) {
	    var rtnVal = checkFixSizeInJS(fld, fmt);
	    if (rtnVal == -1)
	    {
	        alert("Very long.[Size : " + fmt+ "]" );
	        fld.focus();
	    }
	    return(rtnVal);
	}

	function isNumberKey(evt) {
		//bizRegNo
		document.comInfoFrm.bizRegNo.value = document.comInfoFrm.bizRegNo.value.replace(/\D/g,'');
		
	    var charCode = (evt.which) ? evt.which : event.keyCode;
	    if (charCode > 31 && (charCode < 48 || charCode > 57))
	    	return false;
	    return true;
	}

	function isCMND(evt) {
		//bizRegNo
	    var charCode = (evt.which) ? evt.which : event.keyCode
	    if (charCode < 48 ||  (charCode > 57 && charCode < 65) || (charCode > 90 && charCode < 97) || charCode > 122) 
	         return false;
	    return true;
	}
	
	function checkEmail(email) {
    	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    	if (email.value && email.value.length != 0) {
	    	if (!filter.test(email.value)) {
		    	alert('Email address not valid!');
		    	email.focus();
		    	return false;
	    	}
    	}
    	return true;
   	}

//   	function checkStdCls() {
//
//   		//stdCLSFrm constAbilEvalAmt evalStdYear
//   		if(document.stdCLSFrm.evalStdYear.value.length != 0 && document.stdCLSFrm.constAbilEvalAmt.value.length == 0) {
//   			alert("Hãy nhập số tiền bảo lãnh!");
//   			document.stdCLSFrm.constAbilEvalAmt.focus();
//   			return false;
//   		} else if (document.stdCLSFrm.evalStdYear.value.length == 0 && document.stdCLSFrm.constAbilEvalAmt.value.length != 0) {
//   			alert("Hãy nhập năm đăng ký tiền bảo lãnh!");
//   			document.stdCLSFrm.evalStdYear.focus();
//   			return false;
//   		}
//   		return true;
//   	}

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
	   	 			if (isAddingRow) alert("Lost something!");
	   	 			return false;
   	 			}
   	 		}
   	 	}
   	 	return true;
   	 }

   	 function validateAll() {
   		 var i=0;
   		 var j=0;
   	 	var unComInfReq = new Array("0000", "documentRegDate", "bizEnName", "fax", "homePage");
   	 	var unBidAgentReq = new Array("bizPosition", "bizDepart", "bizPhoneNo", "bizFax");

   	 	if(!validateFrm(document.comInfoFrm, unComInfReq, false)) {
   	 		alert("Company information not blank!");
   	 		return false;
   	 	}

   	 	if( ceoList.length == 0) {
			alert("Must add manager information!");
   	 		document.reprFrm.reprName.focus();
   	 		return false;
   	 	} else {
   	 		var c = 0;
   	 		for (i = 0; i < ceoList.length; i++) {
   	 			if(ceoList[i].ceoIsMaster == "Y") c++;
   	 		}
   	 		if(c == 0) {
   	 			alert("Must have Highest legal representative!");
   	 			document.reprFrm.reprName.focus();
   	 			return false;
   	 		}
   	 	}

   	 	if(stdCLSList.length == 0) {
   	 		alert("Business information not blank!");
			document.stdCLSFrm.stdClsName.focus();
   	 		return false;
   	 	}

   	 	if(!validateFrm(document.bizAgentFrm, unBidAgentReq, false)) {
   	 		alert("Agent information not blank!");
   	 		return false;
   	 	}

    	 	if(!validateFrm(document.CARegisterFrm, null, false)) {
    	 		alert("CA information not blank!");
    	 		return false;
    	 	}

    	 	if (CARegisterFrm.caUserId.value.length != 8) {
    	 		alert("ID must be 8 character.");
    	 		return false;
    	 	}
   	 	
   	 	// Check stdCls
   	 	for (i=0; i<stdCLSList.length; i++){
   	 		for(j=i+1; j<stdCLSList.length; j++){
   	 			if(stdCLSList[i].stdClsName===stdCLSList[j].stdClsName){
   	 				alert("Business information not duplicate. Please check again!");
   	 				document.stdCLSFrm.stdClsName.focus();
   	 				return false;
   	 			}
   	 		}
   	 	}
   	 	// Check Contract
	   	 for (i=0; i<ctrList.length; i++){
		 		for(j=i+1; j<ctrList.length; j++){
		 			if(ctrList[i].CtrNo===ctrList[j].CtrNo){
		 				alert("Number of contract not duplicate. Please check again!");
		 				document.stdCtrFrm.ctrNo.focus();
		 				return false;
		 			}
		 		}
		 	}
   	 	// Check Finan
   	 	for (i=0; i<finanList.length; i++){
	 		for(j=i+1; j<finanList.length; j++){
	 			if(finanList[i].stdYEAR2===finanList[j].stdYEAR2){
	 				alert("Year of financy not duplicate.");
	 				document.stdFinanFrm.stdYEAR2.focus();
	 				return false;
	 			}
	 		}
	 	} 
	 	// check report reportList
	 	for (i=0; i<reportList.length; i++){
	 		for(j=i+1; j<reportList.length; j++){
	 			if(reportList[i].stdYEAR===reportList[j].stdYEAR){
	 				alert("Year of report not duplicate.");
	 				document.stdFinanFrm1.stdYEAR.focus();
	 				return false;
	 			}
	 		}
	 	} 
   	 	
   	 	return true;
   	 }



	function compareDate(date1, date2)
	{
		var year1 = date1.substring(6);
		var month1 = date1.substring(3, 5);
		var day1 = date1.substring(0, 2);
		var dt1 = parseInt(year1 + month1 + day1);
		if (date2 != null)
		{
			var year2 = date2.substring(6);
			var month2 = date2.substring(3, 5);
			var day2 = date2.substring(0, 2);
			var dt2 = parseInt(year2 + month2 + day2);

			var sub = dt2 - dt1;
			if (sub > 0)
			{
				return true;
			}
		}
		alert("Check date time!");
		return false;
	}
	/*------------------------End validate field------------------------*/

	
	function addOnLoad() {
		<% if(!existData) {%>
       var newwin = window.open("http://muasamcong.mpi.gov.vn/main/info_mem_go2_pop.html","ifmg1","width=823,height=550, scrollbars=yes");
//Comment by thanhtv 2010.05.05
       newwin.focus();
       <%}%>
// End comment

		<% if(existData) {%>
			var item;
			<%for (int i = 0; i < reprList.length; i++) {%>
				item = new CeoItem('<%= reprList[i].data[1]%>', '<%= reprList[i].data[2]%>', '<%= reprList[i].data[3]%>', '<%= reprList[i].data[4]%>', '<%= reprList[i].data[5]%>');
				addCeoItem(item);
			<%}%>
			<%for (int i = 0; i < stdClsList.length; i++) {
				String stdClsCode = stdClsList[i].data[0];
				//List stdClsName = nationalCtrl.getNameByCD("GU9", stdClsCode);
			%>
				item = new StdCLSItem('<%=stdClsList[i].data[1]%>', '<%= stdClsCode%>', '<%= stdClsList[i].data[2]%>');
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
// Add by thanhtv 2012.04.24
			document.comInfoFrm.bizRegNo.disabled  = 'false';
			document.comInfoFrm.chkbizRegNo.disabled = 'false';
// The End Add			
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
			
			
			document.CARegisterFrm.caReprName.value = '<%=recPubInstituCert.getDataFromName("REPR_NM")%>';
			document.CARegisterFrm.caReprIdentNo.value = '<%=recPubInstituCert.getDataFromName("REPR_IDENT_NO")%>';
			document.CARegisterFrm.caUserId.value = '<%=recPubInstituCert.getDataFromName("USER_ID")%>';
<%-- 			document.CARegisterFrm.permitBranch.value =  '<%=recSupplierEnterMast.getDataFromName("PERMIT_BRANCH")%>'; --%>
		<% } %>
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
		//this.licenseCode = code;
		//this.issueInstitu = issueInst;
		//this.licenseIssuedDate = liDate;
		//this.licenseExpiryDate = leDate;

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
		this.ceoIsMaster = master;

		this.addToCeoList = function() {
			ceoList.push(this);
		};
		this.removeFromCeoList = function(index) {
			ceoList = ceoList.slice(0, index - 1).concat(ceoList.slice(index, ceoList.length));
		};
	}

	function StdCLSItem(name, code, regNo, regDate, abilEvalAmt , abilEvalYear, mast, expDate) {
		this.stdClsName = name;
		this.stdClsCode = code;
		this.addToStdCLSList = function() {
			stdCLSList.push(this);
		};
		this.removeFromStdCLSList = function(index) {
			stdCLSList = stdCLSList.slice(0, index - 1).concat(stdCLSList.slice(index, stdCLSList.length));
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
    function FinanItem(stdYEAR2, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL){
    	this.stdYEAR2=stdYEAR2;
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
    //function ContractItem(No, name, partnerName, value){
    //	this.CtrNo=No;
   // 	this.CtrName=name;
    //	this.partnerName=partnerName;
   // 	this.CtrValue=value;
   // 	this.addToCtrList = function() {
	//		ctrList.push(this);
	//	};
	//	this.removeFromCtrList = function(index) {
	//		ctrList = ctrList.slice(0, index - 1).concat(ctrList.slice(index, ctrList.length));
	//	};
   // }

	function setMouseEffect(row, index) {
		var rowColor = '#EAF1F7';
		row.style.textAlign = 'center';
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

	function addCeoItem(itemObj) {
		if (ceoList && ceoList.length != 0) {
			for (var i = 0; i < ceoList.length; i ++) {
				if (ceoList[i].ceoIsMaster == "Y" && itemObj.ceoIsMaster == "Y") {
					alert("Have only Highest legal representative!");
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
		cell1.innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + len + ", document.reprFrm);' style='cursor: hand;' value='" + len + " Update'>";
		var cell2 = row.insertCell(1);
		cell2.innerHTML = itemObj.ceoName;
		var cell3 = row.insertCell(2);
		cell3.innerHTML = itemObj.ceoID;
		var cell4 = row.insertCell(3);
		cell4.innerHTML = itemObj.ceoPhone;
		var cell5 = row.insertCell(4);
		cell5.innerHTML = itemObj.ceoEmail;

		var cell6 = row.insertCell(5);
		var imt = "";
		if (itemObj.ceoIsMaster == "Y") imt = "Yes";
		else imt = "No";
		cell6.innerHTML = imt;
		reset(document.reprFrm);
	}

	function addStdCLSItem(itemObj) {

		itemObj.addToStdCLSList();
		var len = stdCLSList.length;

		var tblScreen = document.getElementById('tblStdCls');
		var row = tblScreen.insertRow(len + 2);
		setMouseEffect(row, len);
		var cell1 = row.insertCell(0);
		cell1.innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + len + ", document.stdCLSFrm);' style='cursor: hand;' value='" + len + " Update'>";
		var cell2 = row.insertCell(1);
                itemObj.stdClsName = itemObj.stdClsName.replace(/^\s+/,'');
                itemObj.stdClsName = itemObj.stdClsName.replace(/\s+$/,'');
		cell2.innerHTML = itemObj.stdClsName;
//		var cell3 = row.insertCell(2);
//		cell3.innerHTML = itemObj.groupName;

		reset(document.stdCLSFrm);
	}
	
	
	function addFinanItem(itemObj) {
		//alert('addFinanItem');
		itemObj.addToFinanList();
		var len = finanList.length;

		var tblScreen = document.getElementById('tblStdFinan');
		var row = tblScreen.insertRow(len + 2);
		setMouseEffect(row, len);
		var cell1 = row.insertCell(0);
		cell1.innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + len + ", document.stdFinanFrm);' style='cursor: hand;' value='" + len + " Update'/>";
		//stdYEAR, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL
		var cell2 = row.insertCell(1);
        itemObj.stdYEAR2 = itemObj.stdYEAR2.replace(/^\s+/,'');
		cell2.innerHTML = itemObj.stdYEAR2;
		var cell3 = row.insertCell(2);
        itemObj.stdASSETS_TOTAL = itemObj.stdASSETS_TOTAL.replace(/^\s+/,'');
		cell3.innerHTML = itemObj.stdASSETS_TOTAL;
		var cell4 = row.insertCell(3);
        itemObj.stdDEBT_TOTAL = itemObj.stdDEBT_TOTAL.replace(/^\s+/,'');
		cell4.innerHTML = itemObj.stdDEBT_TOTAL;
		var cell5 = row.insertCell(4);
        itemObj.stdASSET_VALUE = itemObj.stdASSET_VALUE.replace(/^\s+/,'');
		cell5.innerHTML = itemObj.stdASSET_VALUE;
		
		var cell6 = row.insertCell(5);
        itemObj.stdSHORT_ASSETS = itemObj.stdSHORT_ASSETS.replace(/^\s+/,'');
		cell6.innerHTML = itemObj.stdSHORT_ASSETS;
		var cell7 = row.insertCell(6);
        itemObj.stdSHORT_DEBT = itemObj.stdSHORT_DEBT.replace(/^\s+/,'');
		cell7.innerHTML = itemObj.stdSHORT_DEBT;
		var cell8 = row.insertCell(7);
        itemObj.stdCAPITAL = itemObj.stdCAPITAL.replace(/^\s+/,'');
		cell8.innerHTML = itemObj.stdCAPITAL;
		
		
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
		cell1.innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + len + ", document.stdFinanFrm1);' style='cursor: hand;' value='" + len + " Update'/>";
		//stdYEAR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER - stdFinanFrm1
		var cell2 = row.insertCell(1);
        itemObj.stdYEAR = itemObj.stdYEAR.replace(/^\s+/,'');
		cell2.innerHTML = itemObj.stdYEAR;
		var cell3 = row.insertCell(2);
        itemObj.stdREVENUE_TOTAL = itemObj.stdREVENUE_TOTAL.replace(/^\s+/,'');
		cell3.innerHTML = itemObj.stdREVENUE_TOTAL;
		var cell4 = row.insertCell(3);
        itemObj.stdAVERAGE_REVENUE = itemObj.stdAVERAGE_REVENUE.replace(/^\s+/,'');
		cell4.innerHTML = itemObj.stdAVERAGE_REVENUE;
		var cell5 = row.insertCell(4);
        itemObj.stdPROFIT_BEFORE = itemObj.stdPROFIT_BEFORE.replace(/^\s+/,'');
		cell5.innerHTML = itemObj.stdPROFIT_BEFORE;
		var cell6 = row.insertCell(5);
        itemObj.stdPROFIT_AFTER = itemObj.stdPROFIT_AFTER.replace(/^\s+/,'');
		cell6.innerHTML = itemObj.stdPROFIT_AFTER;
		
		
		
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
		cell1.innerHTML = "<input type='button' class='docbutt' onclick='loadItem(" + len + ", document.stdCtrFrm);' style='cursor: hand;' value='" + len + " Update'>";
		//No, name, stdDateSign, ctrValue, stdPriceValue, stdPerCtrValue, stdDateComplete, stdProjectName, stdContactValue
		var cell2 = row.insertCell(1);
        itemObj.CtrNo = itemObj.CtrNo.replace(/^\s+/,'');
		cell2.innerHTML = itemObj.CtrNo;
		var cell3 = row.insertCell(2);
        itemObj.CtrName = itemObj.CtrName.replace(/^\s+/,'');
		cell3.innerHTML = itemObj.CtrName;
		var cell4 = row.insertCell(3);
        itemObj.stdDateSign = itemObj.stdDateSign.replace(/^\s+/,'');
		cell4.innerHTML = itemObj.stdDateSign;
		var cell5 = row.insertCell(4);
        itemObj.CtrValue = itemObj.CtrValue.replace(/^\s+/,'');
		cell5.innerHTML = itemObj.CtrValue;
		
		var cell6 = row.insertCell(5);
        itemObj.stdPriceValue = itemObj.stdPriceValue.replace(/^\s+/,'');
		cell6.innerHTML = itemObj.stdPriceValue;
		var cell7 = row.insertCell(6);
        itemObj.stdPerCtrValue = itemObj.stdPerCtrValue.replace(/^\s+/,'');
		cell7.innerHTML = itemObj.stdPerCtrValue;
		var cell8 = row.insertCell(7);
        itemObj.stdDateComplete = itemObj.stdDateComplete.replace(/^\s+/,'');
		cell8.innerHTML = itemObj.stdDateComplete;
		var cell9 = row.insertCell(8);
        itemObj.stdProjectName = itemObj.stdProjectName.replace(/^\s+/,'');
		cell9.innerHTML = itemObj.stdProjectName;
		var cell10 = row.insertCell(9);
        itemObj.stdContactValue = itemObj.stdContactValue.replace(/^\s+/,'');
		cell10.innerHTML = itemObj.stdContactValue;
		
		reset(document.stdCtrFrm);
	}

	function addItem(itemObj) {
		//alert('addItem');
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
		//alert('loadItem');
		objFrm.btn_Reset.style.display = "none";
		objFrm.btn_Add.style.display = "none";
		objFrm.btn_Update.style.display = "block";
		objFrm.btn_Delete.style.display = "block";
		var i;
		var item;
		if (objFrm.name == 'reprFrm') {
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
			i = parseInt(index) - 1;
			currStdCLSIndex = i;
			item = stdCLSList[i];

			objFrm.stdClsName.value = item.stdClsName;
			objFrm.stdClsCode.value = item.stdClsCode;
			objFrm.groupName.value = item.groupName;
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
			objFrm.stdYEAR2.value = item.stdYEAR2;
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

	function reset(objFrm) {
		objFrm.reset();
	}
// add by thanhtv 2010.05.19
	function isNumberKeyTel(evt)
	{
	   var charCode = (evt.which) ? evt.which : event.keyCode
	   if(charCode==41 || charCode==40) return true;
	   if(charCode==59 || charCode==32 ) return true;
	   if(charCode==88 || charCode==120) return true;
	   if (charCode > 31 && (charCode < 48 || charCode > 57))
	      return false;

	   return true;
	}
// the end.

	function resetAll() {
		for (var i = 0; i < document.forms.length; i++) {
			document.forms[i].reset();
		}
		document.comInfoFrm.bizRegNo.focus();
	}

	function update(objFrm) {
		if (objFrm.name == 'reprFrm') {
			if (ceoList.length != 0) {
				for (var i = 0; i < ceoList.length; i ++) {
					if (currCeoIndex != i && ceoList[i].ceoIsMaster == "Y" && getRdbValue(objFrm.reprIsmain) == "Y") {
						alert("Must only highest legal representative!");
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
			if (getRdbValue(objFrm.reprIsmain) == "Y") imt = "Yes";
			else imt = "No";
			currentRow.cells[5].innerHTML = imt;

		}
                else if (objFrm.name == 'stdCLSFrm') {
                            
//			if(checkStdCls()) {
				tbl = document.getElementById('tblStdCls');
				currentRow = tbl.rows[currStdCLSIndex + 3];
                                
				stdCLSList[currStdCLSIndex].stdClsName = currentRow.cells[1].innerHTML = objFrm.stdClsName.value;
				//stdCLSList[currStdCLSIndex].stdClsCode = currentRow.cells[1].innerHTML = objFrm.stdClsCode.value;
//				stdCLSList[currStdCLSIndex].groupName = currentRow.cells[2].innerHTML = objFrm.groupName.value;
//				stdCLSList[currStdCLSIndex].stdClsNo = currentRow.cells[3].innerHTML = objFrm.stdClsNo.value;
//				stdCLSList[currStdCLSIndex].stdClsIssuedDate = currentRow.cells[4].innerHTML = objFrm.stdClsIssuedDate.value;
//				stdCLSList[currStdCLSIndex].constAbilEvalAmt = currentRow.cells[5].innerHTML = objFrm.constAbilEvalAmt.value;
//				stdCLSList[currStdCLSIndex].evalStdYear = currentRow.cells[6].innerHTML = objFrm.evalStdYear.value;
//				stdCLSList[currStdCLSIndex].mastStdClsYn = currentRow.cells[7].innerHTML = getRdbValue(objFrm.mastStdClsYn);

//				stdCLSList[currStdCLSIndex].stdClsExpiryDate = currentRow.cells[8].innerHTML = objFrm.stdClsExpiryDate.value;
//				stdCLSList[currStdCLSIndex].mastStdClsYn = getRdbValue(objFrm.mastStdClsYn);
				var imt = "";
//				if (getRdbValue(objFrm.mastStdClsYn) == "Y") imt = "Có";
//				else imt = "Không";
//	 			currentRow.cells[7].innerHTML = imt;
// 			} else {fl = 1;}
		}else if (objFrm.name == 'stdCtrFrm') {
			alert("1");
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
			finanList[currFinanIndex].stdYEAR2 = currentRow.cells[1].innerHTML = objFrm.stdYEAR2.value;
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
			reset(objFrm);
			objFrm.btn_Reset.style.display = "block";
			objFrm.btn_Add.style.display = "block";
			objFrm.btn_Update.style.display = "none";
			objFrm.btn_Delete.style.display = "none";
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

		if (objFrm.name == 'reprFrm') {
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

	function back() {
		window.location.href = "UM_RAJ_ConuA050s_en.jsp?bizRegNo=" + '<%=bizRegNo%>' + "&recvNo=" + '<%=recvNo%>';
	}

   //ajax -> post all form
   var http_request = false;

   function makePOSTRequest(url, parameters, flag) {

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
         alert('No created XMLHTTP!');
         return false;
      }
      
      http_request.open('POST', url, true);      
      if (flag == 1) {
      	  <% if (existData) {%>
      	  	http_request.onreadystatechange = function() {
      	  		if (http_request.readyState == 4) {
			         if (http_request.status == 200) {
			         	alert("Update Successful!");
			         }
			      }
      	  	}
	      <% } else { %>
	      		http_request.onreadystatechange = readyRegister;
	      <% } %>
      } else if (flag == 2) {
       	  http_request.onreadystatechange = getAreaContents;
      } else if (flag == 3) {
      	http_request.onreadystatechange = alertDupBizNo;
      }
      http_request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      http_request.setRequestHeader("Content-length", parameters.length);
      http_request.setRequestHeader("Connection", "close");
      
      http_request.send(parameters);
   }

   function readyRegister() {
      //add cert institu
      
	   if (http_request.readyState == 4) {
         if (http_request.status == 200) {
         	var recvCode = http_request.responseText;
         	//if (recvCode == "Y"){    
         	if (recvCode && recvCode.length != 0) {
	         	
         		alert('Register successful!');
	         	window.location.href = "/RA/UM_RAJ_ConuA040i_en.jsp?bizRegNo=" + encodeURI(document.comInfoFrm.bizRegNo.value) + "&recvCode=" + recvCode + "&choiceCA=" + encodeURI(getRdbValue(document.CARegisterFrm.choiceCA));
	            window.open("/RA/UM_RAJ_ConuA020i_en.jsp?bizRegNo=" + encodeURI(document.comInfoFrm.bizRegNo.value) + "&recvCode=" + recvCode + "&choiceCA=" + encodeURI(getRdbValue(document.CARegisterFrm.choiceCA)), "newWIn", "width=700, height=1000, top=10,left=10,scrollbars=yes, toolbar=yes, directories=yes");
         	} else {
         		alert('Register fail!\nPlease check information register!');
         	}
         } else {
            alert('You do not send register letter.');
         }
      }
   }

   function alertDupBizNo() {
	  if (http_request.readyState == 4) {
         if (http_request.status == 200) {
         	var msg = http_request.responseText;
                
         	if (msg == "Y") {
         		alert("Register number correct.");
         	} else {
         		document.comInfoFrm.bizRegNo.focus();
         		document.comInfoFrm.bizRegNo.value = "";
         		alert("Register number uncorrect, please check!");
       		}
         }
      }
   }

   function getAreaContents() {
   	  if (http_request.readyState == 4) {
         if (http_request.status == 200) {
         	var list = http_request.responseText;
         	alert(list);
         }
      }
   }

/*    function getAreaByNational(nationalCode) {
   		var params = "nationalCode=" + nationalCode;
		makePOSTRequest('/RA/UM_RAJ_ConuA010i.jsp', params, 2);
   } */
  /*  function checkDuplicate(val) 
   {
		var param = "bizRegNo=" + val;
		param += "&checkBizNo=1";
		makePOSTRequest('/servlet/servlets/UM_RAV_ConuA010i', param , 3);
   } */


	function make(mode) {
		if (!validateAll()) {
			return;
		}
		var sCLS = getSupplierCls();
		if (sCLS == "") {
			alert("Must choice Subject matter");
			document.comInfoFrm.cbProduction.focus();
			return;
		}
		//comInfoFrm form 
        var postStr = "bizRegNo=" + encodeURI(document.comInfoFrm.bizRegNo.value);
        postStr += "&bizName=" + encodeURI(document.comInfoFrm.bizName.value);
        postStr += "&bizEnName=" + encodeURI(document.comInfoFrm.bizEnName.value);
        postStr += "&commDate=" + encodeURI(document.comInfoFrm.commDate.value);
        postStr += "&addr=" + encodeURI(document.comInfoFrm.addr.value);
        postStr += "&phoneNo=" + encodeURI(document.comInfoFrm.phoneNo.value);
        postStr += "&homePage=" + encodeURI(document.comInfoFrm.homePage.value);
        postStr += "&capital=" + encodeURI(document.comInfoFrm.capital.value.replace(/\./g, ''));
        postStr += "&emplCount=" + encodeURI(document.comInfoFrm.emplCount.value.replace(/\./g, ''));
        postStr += "&fax=" + encodeURI(document.comInfoFrm.fax.value);
        postStr += "&bizCls=" + encodeURI(document.comInfoFrm.bizCls.value);
        postStr += "&national=" + encodeURI(document.comInfoFrm.national.value);
        
     
        postStr += "&docRegDt=" + encodeURI(document.comInfoFrm.documentRegDate.value);
        postStr += "&supplierCls=" + sCLS;
        postStr += "&docRegDt=" + encodeURI(document.comInfoFrm.documentRegDate.value);
        //bizAgentFrm form
        postStr += "&bizAgentName=" + encodeURI(document.bizAgentFrm.bizAgentName.value);
        postStr += "&bizIdentNo=" + encodeURI(document.bizAgentFrm.bizIdentNo.value);
        postStr += "&bizPosition=" + encodeURI(document.bizAgentFrm.bizPosition.value);
        postStr += "&bizDepart=" + encodeURI(document.bizAgentFrm.bizDepart.value);
        postStr += "&bizAgentEmail=" + encodeURI(document.bizAgentFrm.bizAgentEmail.value);
        postStr += "&bizPhoneNo=" + encodeURI(document.bizAgentFrm.bizPhoneNo.value);
        postStr += "&bizMobile=" + encodeURI(document.bizAgentFrm.bizMobile.value);
        //postStr += "&test_option_yn=" + encodeURI(document.processInfo.test_option_yn.value);

        //CARegisterFrm form
        postStr += "&liceS=" + encodeURI(document.CARegisterFrm.liceS.value);
        postStr += "&caReprName=" + encodeURI(document.CARegisterFrm.caReprName.value);
        postStr += "&caReprIdentNo=" + encodeURI(document.CARegisterFrm.caReprIdentNo.value);
        postStr += "&caUserId=" + encodeURI(document.CARegisterFrm.caUserId.value);
        postStr += "&permitBranch=" + encodeURI(document.CARegisterFrm.permitBranch.value);


        //25/08/2014
        postStr += "&choiceCA=" + encodeURI(getRdbValue(document.CARegisterFrm.choiceCA));
        //reprFrm form
        if (ceoList.length != 0) {
        	postStr += "&reprCount=" + ceoList.length;
        	for(var i = 0; i < ceoList.length; i++) {
	        	postStr += "&reprName[" + i + "]=" + ceoList[i].ceoName;
	        	postStr += "&reprIdentNo[" + i + "]=" + ceoList[i].ceoID;
	        	postStr += "&reprMobile[" + i + "]=" + ceoList[i].ceoPhone;
	        	postStr += "&reprEmail[" + i + "]=" + ceoList[i].ceoEmail;
	        	//postStr += "&reprType[" + i + "]=" + ceoList[i].ceoType;
	        	postStr += "&reprIsmain[" + i + "]=" + ceoList[i].ceoIsMaster;
        	}
        }

        
        if (licenseList.length != 0) {
        	postStr += "&licenseCount=" + licenseList.length;
        	for(var j = 0; j < licenseList.length; j++) {
	        	postStr += "&licenseCode[" + j + "]=" + licenseList[j].licenseCode;
	        	postStr += "&issueInstitu[" + j + "]=" + licenseList[j].issueInstitu;
	        	postStr += "&licenseIssuedDate[" + j + "]=" + licenseList[j].licenseIssuedDate;
	        	postStr += "&licenseExpiryDate[" + j + "]=" + licenseList[j].licenseExpiryDate;
        	}
		}
		

        if (stdCLSList.length != 0) {
        	postStr += "&stdClsCount=" + stdCLSList.length;
        	for(var k = 0; k < stdCLSList.length; k++) {
	        	postStr += "&stdClsName[" + k + "]=" + stdCLSList[k].stdClsName;
	        	postStr += "&stdClsCode[" + k + "]=" + stdCLSList[k].stdClsCode;	        		        		        		        	
        	}
		}
		// Post Contract
		if (ctrList.length > 0) {
        	postStr += "&ctrCount=" + ctrList.length;
        	for(var k = 0; k < ctrList.length; k++) {
	        	//No, name, stdDateSign, value, stdPriceValue, stdPerCtrValue, stdDateComplete, stdProjectName, stdContactValue
        		
        		postStr += "&ctrNo[" + k + "]=" + ctrList[k].CtrNo;
	        	postStr += "&ctrName[" + k + "]=" + ctrList[k].CtrName;
	        	postStr += "&stdDateSign[" + k + "]=" + ctrList[k].stdDateSign;
	        	postStr += "&ctrValue[" + k + "]=" + ctrList[k].CtrValue;
	        	
	        	postStr += "&stdPriceValue[" + k + "]=" + ctrList[k].stdPriceValue;
	        	postStr += "&stdPerCtrValue[" + k + "]=" + ctrList[k].stdPerCtrValue;
	        	postStr += "&stdDateComplete[" + k + "]=" + ctrList[k].stdDateComplete;
	        	postStr += "&stdProjectName[" + k + "]=" + ctrList[k].stdProjectName;
	        	postStr += "&stdContactValue[" + k + "]=" + ctrList[k].stdContactValue;
        	}
		}
		
		// Post Finan
		if (finanList.length > 0) {
        	postStr += "&ctrFinan=" + finanList.length;
        	for(var k = 0; k < finanList.length; k++) {
        		//stdYEAR, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL
    			
        		postStr += "&stdYEAR2[" + k + "]=" + finanList[k].stdYEAR2;
	        	postStr += "&stdASSETS_TOTAL[" + k + "]=" + finanList[k].stdASSETS_TOTAL;
	        	postStr += "&stdDEBT_TOTAL[" + k + "]=" + finanList[k].stdDEBT_TOTAL;
	        	postStr += "&stdASSET_VALUE[" + k + "]=" + finanList[k].stdASSET_VALUE;
	        	
	        	postStr += "&stdSHORT_ASSETS[" + k + "]=" + finanList[k].stdSHORT_ASSETS;
	        	postStr += "&stdSHORT_DEBT[" + k + "]=" + finanList[k].stdSHORT_DEBT;
	        	postStr += "&stdCAPITAL[" + k + "]=" + finanList[k].stdCAPITAL;
	        	
        	}
		}
		//stdYEAR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER - stdFinanFrm1
    	// Post Report
		if (reportList.length > 0) {
        	postStr += "&ctrReport=" + reportList.length;
        	for(var k = 0; k < reportList.length; k++) {
        		//stdYEAR, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL
    			
        		postStr += "&stdYEAR[" + k + "]=" + reportList[k].stdYEAR;
	        	postStr += "&stdREVENUE_TOTAL[" + k + "]=" + reportList[k].stdREVENUE_TOTAL;
	        	postStr += "&stdAVERAGE_REVENUE[" + k + "]=" + reportList[k].stdAVERAGE_REVENUE;
	        	postStr += "&stdPROFIT_BEFORE[" + k + "]=" + reportList[k].stdPROFIT_BEFORE;
	        	postStr += "&stdPROFIT_AFTER[" + k + "]=" + reportList[k].stdPROFIT_AFTER;
	        	
        	}
		}
		
		//step=0 -> data will be store in rec table
		var flagParam = "&step=0";
		postStr += flagParam;
		if (mode == 1) {
			postStr += "&mode=1";
			postStr += "&key=" + '<%=bizRegNo%>';
			postStr += "&prevRecvNo=" + '<%=recvNo%>';
		}
            
		//makePOSTRequest('/servlet/servlets/UM_RAV_ConuA010i_en', postStr, 1);
		// Post Finan
		
    	
		
// 		fd.append('file', document.stdFinanFrm.stdFinanFile);
// 		fd.append("bizRegNo",encodeURI(document.comInfoFrm.bizRegNo.value));
// 		fd.append('stdFinanFile', document.stdFinanFrm.stdFinanFile);
// 		makePOSTRequest('/servlet/servlets/UM_RAV_ConuA010i', fd, 0);
	}
	

    function btnAdd()
	{
    	var name=""
       	 for (var i=0; i<stdCLSList.length; i++){
       		 name=document.stdCLSFrm.stdClsName.value;
	 			if(name !='' && name===stdCLSList[i].stdClsName){
	 				alert("Business name not duplicate");
	 				document.stdCLSFrm.stdClsName.focus();
	 				return;
	 			}
 		 }
		//alert(document.stdCLSFrm.stdClsIssuedDate.value);
		//if (validateFrm(document.stdCLSFrm, new Array('constAbilEvalAmt', 'evalStdYear'), true))
		if (document.stdCLSFrm.groupName.value == ""){
                        alert("You must choice business group");
			document.stdCLSFrm.groupName.focus();
			return;
                } else if (document.stdCLSFrm.stdClsName.value == ""){
			alert("Business name not blank!");
			document.stdCLSFrm.stdClsName.focus();
			return;
		} else {
//			if (document.comInfoFrm.commDate.value == "")
//			{
//				alert("Bạn chưa nhập ngày đăng ký kinh doanh");
//				return;
//			}
			//document.stdCLSFrm.stdClsNo.value = document.stdCLSFrm.stdClsCode.value;
			//document.stdCLSFrm.stdClsIssuedDate.value = document.comInfoFrm.commDate.value;
			//document.stdCLSFrm.stdClsExpiryDate.value = "09/11/2050";

//			if (checkStdCls())
//			{
				addItem(new StdCLSItem(document.stdCLSFrm.stdClsName.value, document.stdCLSFrm.stdClsCode.value, document.stdCLSFrm.groupName.value));
			//}
		}
		//apply();
	};
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
    		
             	addItem(new ContractItem(No, name, stdDateSign, ctrValue, stdPriceValue, stdPerCtrValue, stdDateComplete, stdProjectName, stdContactValue));
    	}
        
        function btnFinanAdd(){
        	//stdYEAR, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL
        	//alert('btnFinanAdd');
        	var stdYEAR2=document.stdFinanFrm.stdYEAR2.value;
    		var stdASSETS_TOTAL=document.stdFinanFrm.stdASSETS_TOTAL.value;
    		var stdDEBT_TOTAL = document.stdFinanFrm.stdDEBT_TOTAL.value;
    		var stdASSET_VALUE=document.stdFinanFrm.stdASSET_VALUE.value;
    		var stdSHORT_ASSETS=document.stdFinanFrm.stdSHORT_ASSETS.value;
    		var stdSHORT_DEBT=document.stdFinanFrm.stdSHORT_DEBT.value;
    		var stdCAPITAL=document.stdFinanFrm.stdCAPITAL.value;
    			addItem(new FinanItem(stdYEAR2, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL));
        		
        }
        function btnFinanAdd1(){
        	//stdYEAR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER - stdFinanFrm1
        	//alert('btnFinanAdd1');
        	var stdYEAR = document.stdFinanFrm1.stdYEAR.value;
    		var stdREVENUE_TOTAL = document.stdFinanFrm1.stdREVENUE_TOTAL.value;
    		var stdAVERAGE_REVENUE = document.stdFinanFrm1.stdAVERAGE_REVENUE.value;
    		var stdPROFIT_BEFORE = document.stdFinanFrm1.stdPROFIT_BEFORE.value;
    		var stdPROFIT_AFTER = document.stdFinanFrm1.stdPROFIT_AFTER.value;
    		
    		
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
        
        function dispCapital(){
        //	var e = document.getElementById("ddlViewBy");
        //	var strUser = e.options[e.selectedIndex].value;
        	var strCap = document.comInfoFrm.bizCls.value
	        if(strCap=='01'){
	        	
	        	var theLblCapital = document.getElementById("lblCapital");
	        	theLblCapital.className="nonevondieule";
	        	var theipCapital = document.getElementById("ipCapital");
	        	theipCapital.className="nonevondieule";
	        	document.comInfoFrm.capital.value = 0;
	        	
	        }else{
	        	var theLblCapital = document.getElementById("lblCapital");
	        	theLblCapital.className="";
	        	var theipCapital = document.getElementById("ipCapital");
	        	theipCapital.className="";
	        	document.comInfoFrm.capital.value = "";
	        }
        }
        	
        
</script>

</head>

<body onload="document.comInfoFrm.documentRegDate.value = getCurrentDate(); addOnLoad();">
<div class="col-750 clearfix last">
	<h1 class="pageTitle"><i class="icon-title"></i>Bidder registration</h1>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td><jsp:include page="/indexFlow2.jsp?gubun=2&req=11" /></td>
	</tr>
</table>
<br>
<form name="comInfoFrm">
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr" style="display:none;">
	<tr>
		<td height="4" class="LINE" colspan="4" />
	</tr>
	<tr >
		<td width="20%" class="tdar">Document ID </td>
		<td width="30%" class="tdb"><!-- input type="text" name="documentID" id="documentID" value="" size="25" maxlength="20" onblur="FieldChecker(this, '20', 'M', 's');" --></td>
		<td width="20%" class="tdar"> Issued date</td>
		<td width="30%" class="tdb">
			<input class="readonly" readonly="readonly" type="text" name="documentRegDate" id="documentRegDate" size="25" maxlength="25" >
		</td>
	</tr>
	<tr>
		<td height="2" class="LINE" colspan="4" />
	</tr>
</table>
<br>
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
	<tr>
		<th align="left" colspan="4">[Organization Information]</th>
	</tr>
	<tr>
		<td height="4" class="LINE" colspan="4" />
	</tr>
	<tr>
		<td class="tdar">Company ID</td>
		<td colspan="3" class="tdb">(Company ID is automatically generated by the system for internal use in the procurement network system.)
			<input type="hidden" id="bizRegNo" name="bizRegNo" value="<%=bizRegGen %>" size="13" maxlength="13" class="readonly" readonly="readonly">
			<input type="hidden" class="read" type="text" name="commDate" size="25" maxlength="25" value="26/05/2016" title="Date format dd/mm/yyyy">
		</td>
	</tr>
	<tr>
		<td class="tdar"><font color="red">*</font>Organization Name</td>
		<td class="tdb" colspan="3"><input type="text" name="bizEnName" value="" size="110" maxlength="250" onkeypress="return onlyAlphabet(event, this);" onblur="if (isValidatedInputObj(this)) {FieldChecker(this, '250', 'M', 's');}"/></td>
	</tr>
	<!-- tr>
		<td width="20%" class="tdar">&nbsp;Tên tiếng Anh</td>
		<td class="tdb" colspan="3"><input type="text" name="bizEnName" value="" size="120" maxlength="150" onkeypress="return onlyAlphabet(event, this);" onblur="if (isValidatedInputObj(this)) {FieldChecker(this, '150', 'M', 's');}"/></td>
	</tr -->
	<tr>
		<td class="tdar"><font color="red">*</font> Subject matter </td>
		<td colspan="3" class="tdb">
			<% if(existData) { %>
			<%
				String supplierCls = recSupplierEnterMast.getDataFromName("BIZ_CLS");

				if(supplierCls.indexOf('1') != -1) {
			%>
				<input type="checkbox" value="1" name="cbProduction" checked="checked"> Goods
			<% } else { %>
				<input type="checkbox" value="1" name="cbProduction"> Goods
			<% } %>
			<% if(supplierCls.indexOf('3') != -1) { %>
				<input type="checkbox" value="3" name="cbConstruction" checked="checked">Civil Works
			<% } else { %>
				<input type="checkbox" value="3" name="cbConstruction">Civil Works
			<% } %>
			<% if(supplierCls.indexOf('5') != -1) { %>
				<input type="checkbox" value="5" name="cbConsultant" checked="checked"> Consulting services
			<% } else { %>
				<input type="checkbox" value="5" name="cbConsultant"> Consulting services
			<% } %>
			<% if(supplierCls.indexOf('7') != -1) { %>
				<input type="checkbox" value="7" name="cbNonConsultant" checked="checked"> Non-consulting services
			<% } else { %>
				<input type="checkbox" value="7" name="cbNonConsultant"> Non-consulting services
			<% } %>
		
			<% } else {%>
			<input type="checkbox" value="1" name="cbProduction"> Goods
			<input type="checkbox" value="3" name="cbConstruction">Civil Works
			<input type="checkbox" value="5" name="cbConsultant"> Consulting services
			<input type="checkbox" value="7" name="cbNonConsultant">Non-consulting services
			<% } %>
			<input name="bizCls" type="hidden" value="08"/>
		</td>
		
	</tr>
	<tr>
	<tr>
		<td class="tdar" style="width:184px!importal;"><font color="red">*</font> Number of employees</td>
		<td colspan="3" class="tdb"><input type="text" name="emplCount"  size="15" maxlength="10" onkeypress="return isNumberKey(event);" onFocus="this.value = setOrginalNumber(this.value, '.');"  onBlur="if (FieldChecker(this, '10', 'C', 'n') > 0) {this.value=setNumFormat(this.value, '#.###.###.###.##0');}"/>
		<!-- td width="20%" class="tdar"><div class="nonevondieule" name="lblCapital" id="lblCapital"><font color="red">*</font> Charter capital</div></td>
		<td width="30%" class="tdb"><div class="nonevondieule" name="ipCapital" id="ipCapital"--><input type="hidden" value="0" name="capital" size="0" maxlength="15" style="text-align: right;"/></td> <!-- VND</div></td -->
	</tr>
	<tr>
		<td width="20%" class="tdar"><font color="red">*</font> Telephone</td>
		<td width="30%" class="tdb"><input type="text" name="phoneNo" value="" size="25" maxlength="25" onblur="FieldChecker(this, '25', 'C', 'n');" onkeypress="return isNumberKey(event);"/></td>
		<td width="25%" class="tdar"> Fax </td>
		<td width="25%" class="tdb"><input type="text" name="fax" size="25" maxlength="25" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '25', 'C', 'n');"/></td>
	</tr>

	<tr>
		<td class="tdar"><font color="red">*</font> Address</td>
		<td colspan="3" class="tdb"><input type="text" name="addr" size="110" maxlength="250" onblur="FieldChecker(this, '250', 'M', 's');" /> </td>
	</tr>
	<tr>
		<td class="tdar"> Website</td>
		<td colspan="3" class="tdb"><input type="text" name="homePage" value="" size="25" maxlength="100" onblur="FieldChecker(this, '100', 'C', 's');"/></td>
	</tr>
	<tr>
		<td class="tdar"><font color="red">*</font> Country</td>
		<td colspan="3" class="tdb">
			<!-- <input type="text" id="national" name="national" readonly="readonly">  -->
			<%
				List nationals = nationalCtrl.getList("I99");
			%>
			<select name="national" title="">
				<%
				String item = "";
				for(int i = 0; i < nationals.size(); i++) {
					item = nationals.get(i).toString();
					String naCode = item.split("#")[1];
					String naName = item.split("#")[0];
				%>
			 <%if (!naCode.equals("1151")){%>	<option value="<%=naCode %>"><%=naName%></option><%}%>
				<%
				}
				%>
			</select>	
		</td>
<!-- 		<td width="20%" class="tdar"></td> -->
<!-- 		<td width="20%" class="tdb">		</td> -->
		
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
		<th align="left" colspan="2">[Manager Information]</th>
		<th align="right" colspan="2">
			<input type="button" class="commonbutton" value="Add" name="btn_Add" style="float: right; display: block;" onclick="if (validateFrm(this.form,null,true)) { var reprIsmain = getRdbValue(document.reprFrm.reprIsmain); addItem(new CeoItem(this.form.reprName.value, this.form.reprIdentNo.value, this.form.reprMobile.value, this.form.reprEmail.value, reprIsmain));}">
			<input type="button" class="commonbutton" value="Reset" name="btn_Reset" style="display: block;" onclick="reset(this.form);">
			<input type="button" class="commonbutton" style="display: none; float: right;" value="Delete" name="btn_Delete" onclick="del(this.form);">
			<input type="button" class="commonbutton" style="display: none" value="Update" name="btn_Update" onclick="update(this.form);">
		</th>
	</tr>
	<tr>
		<td height="4" class="LINE" colspan="4" />
	</tr>
	<tr>
		<td class="tdar" width="20%"><font color="red">*</font> Manager Name</td>
		<td class="tdb" width="30%"><input type="text" name="reprName" size="25" maxlength="100" onblur="FieldChecker(this, '100', 'M', 's');"/></td>
		<td class="tdar" width="20%"><font color="red">*</font> Passport, ID Card</td>
<!-- 		
		<td class="tdb" width="30%"><input type="text" name="reprIdentNo" size="15" maxlength="9" onkeypress="return isNumberKey(event);" onblur="if (FieldChecker(this, '10', 'C', 'n') > 0) checkFixSize(this, 9);"/></td>
 -->
 		<td class="tdb" width="30%"><input type="text" name="reprIdentNo" size="24" maxlength="50" onkeypress="return isCMND(event);"/></td>
 				
	</tr>
	<tr>
		<td class="tdar" width="20%"><font color="red">*</font> Telephone</td>
		<td class="tdb" width="30%"><input type="text" name="reprMobile" size="25" maxlength="50" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '50', 'C', 'n');"/></td>

		<td class="tdar" width="20%"><font color="red">*</font> Email</td>
		<td width="30%" class="tdb"><input type="text" name="reprEmail" size="24" maxlength="100" onblur="return checkEmail(this);"/></td>
	</tr>
	<tr>
		<td class="tdar" width="20%"><font color="red">*</font> Highest legal representative</td>
		<td class="tdb" width="30%" colspan="3">
			Yes <input type="radio" name="reprIsmain" value="Y" checked="checked"/>
			No <input type="radio" name="reprIsmain" value="N" />
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
		<td class="tdac" width="5%">No</td>
		<td class="tdac" width="20%">Manager Name</td>
		<td class="tdac" width="10%">Passport, ID Card</td>
		<td class="tdac" width="10%">Phone</td>
		<td class="tdac" width="25%">Email</td>
		<td class="tdac" width="15%">Highest legal Representative</td>
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
		<th align="left" colspan="2">[Business Information]</th>
		<th align="right" colspan="2">

			<input type="button" class="commonbutton" value="Add" name="btn_Add" style="float: right; display: block;" onclick="btnAdd();">

			<input type="button" class="commonbutton" value="Reset" name="btn_Reset" style="display: block;" onclick="reset(this.form);">
			<input type="button" class="commonbutton" style="display: none; float: right;" value="Delete" name="btn_Delete" onclick="del(this.form);">
			<input type="button" class="commonbutton" style="display: none" value="Update" name="btn_Update" onclick="update(this.form);">
		</th>
	</tr>
	<tr>
		<td height="4" class="LINE" colspan="4" />
	</tr>
        <tr style = "display:none">
            <td class="tdar" width="25%"><font color="red" >*</font> Business Group</td>
            <td class="tdb"  width="75%" colspan="3" >
                <span class="fontbl style13">
                    <input name="groupName" type="text" value="Building" size="30" maxlength="50" readonly/>
                    <input type="hidden" name="stdClsCode" value="4100"/>
                </span>
                <input name="btnSearchInstitu" type="button" class="commonbutton"  value="T&igrave;m"  onClick="javascript:suyoSearch2('stdClsCode','groupName','stdCLSFrm');"/>
                    <%--= recEnterStdClsDAO.getSelectNganhnghe_AddCode("stdClsCode")--%>
            </td>
        </tr>
        <tr>
            <td class="tdar" width="25%" ><font color="red">*</font> Business Name</td>
            <td class="tdb" width="75%" colspan="3">
                <input type="text" name="stdClsName" maxlength="2000" style="width: 100%" onblur="FieldChecker(this, '2000', 'M', 's');"/>
            </td>
        </tr>

</table>
<table width="100%" id="tblStdCls">
	<tr height="1">
		<td colspan="9" class="line"></td>
	</tr>
	<tr>
		<td class="tdac" width="5%">No.</td>
		<td class="tdac" width="20%">Business name</td>
		<td class="tdac" width="10%" style="display: none">Business Group</td>
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

<br>


<form name='tofile'>
<!-- 					<input type='hidden' name='fileType'/> -->
					<input type='hidden' name='fileName'/>
				</form>
<br>
<form name="bizAgentFrm">
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
	<tr>
		<th align="left" colspan="4">[Agent Information]</th>
	</tr>
	<tr>
		<td height="4" class="LINE" colspan="4" />
	</tr>
	<tr>
		<td class="tdar" width="20%"> Position</td>
		<td class="tdb" width="30%"><input type="text" name="bizPosition" value="" size="25" maxlength="40" onblur="FieldChecker(this, '40', 'M', 's');"/></td>
		<td class="tdar" width="20%"> Department</td>
		<td class="tdb" width="30%"><input type="text" name="bizDepart" size="25" maxlength="200" onblur="FieldChecker(this, '200', 'M', 's');" /></td>
	</tr>
	<tr>
		<td class="tdar" width="20%"><font color="red">*</font> Agent Name</td>
		<td class="tdb" width="30%"><input type="text" name="bizAgentName" size="25" maxlength="100" onblur="FieldChecker(this, '100', 'M', 's');" /></td>
		<td class="tdar" width="25%"><font color="red">*</font> Passport, ID Card Number</td>
<!-- 		
		<td class="tdb" width="30%"><input type="text" name="bizIdentNo" size="15" maxlength="9" onkeypress="return isNumberKey(event);" onblur="if (FieldChecker(this, '10', 'C', 'n') > 0) checkFixSize(this, 9);"/></td>
 -->		
 		<td class="tdb" width="25%"><input type="text" name="bizIdentNo" size="25" maxlength="50" onkeypress="return isCMND(event);" /></td>
 
	</tr>
	<tr>
		<td class="tdar" width="20%"><font color="red">*</font> Email</td>
		<td class="tdb" width="30%"><input type="text" name="bizAgentEmail" value="" size="25" maxlength="100" onblur="return checkEmail(this);"/></td>
		<td class="tdar" width="20%"><font color="red">*</font> Mobile</td>
		<td class="tdb" width="30%"><input type="text" name="bizMobile" size="25" maxlength="25" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '25', 'C', 'n');"  /></td>
	</tr>
	<tr>
		<td class="tdar" width="20%">Telephone</td>
		<td class="tdb" width="30%"><input type="text" name="bizPhoneNo" value="" size="25" maxlength="25" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '25', 'C', 'n');" /></td>
		<td class="tdar" width="20%">Fax</td>
		<td class="tdb" width="30%"><input type="text" name="bizFax" size="25" maxlength="25" onkeypress="return isNumberKey(event);" onblur="FieldChecker(this, '25', 'C', 'n');" /></td>
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
		<th align="left" colspan="4">[Legal Representative Information]</th>
	</tr>
	<tr>
		<td height="4" class="LINE" colspan="4"></td>
	</tr>
	<tr style="display:none">
		<td class="tdar" width="20%" height="25"><font color="red">*</font> Agency for digital certificates</td>
		<td class="tdb" width="30%"><input type="radio" name="liceS" value="mpi" checked="checked" readonly="readonly">Cục Quản lý Đấu thầu - Bộ Kế hoạch và Đầu tư</td>
		<td class="tdar" width="20%" height="25"><font color="red">*</font> Branch approved</td>
		<td class="tdb"  width="30%">
			<input type="hidden" value="00" name="permitBranch">
			<!-- <input type="text" name="permitBranchName" value="Cục Quản lý Đấu thầu" readonly="readonly" class="readonly" size="30"> -->
			<select name="permitBranchName">
				<option value="00">Cục Quản lý Đấu thầu</option>
			</select>
		</td>
	</tr>
	<tr>
		<td class="tdar" width="20%"><font color="red">*</font>Legal Representative</td>
		<td class="tdb" width="30%"><input type="text" name="caReprName" value="" class="read" size="25" maxlength="100" onblur="FieldChecker(this, '100', 'M', 's');" ></td>
		<td class="tdar" width="25%"><font color="red">*</font> Passport, ID Card Number</td>
<!-- Add by thanhtv 2012.11.01 		
		<td class="tdb" width="30%"><input type="text" name="caReprIdentNo" class="read" size="15" maxlength="9" onkeypress="return isNumberKey(event);" onblur="if (FieldChecker(this, '10', 'C', 'n') > 0) checkFixSize(this, 9);"></td>
 -->
 		<td class="tdb" width="25%"><input type="text" name="caReprIdentNo" class="read" size="25" maxlength="50" onkeypress="return isCMND(event);" ></td>		
	</tr>
	<tr style = "display:none">
		<td class="tdar" width="20%"><font color="red">*</font> CA code</td>
		<td class="tdb" colspan="3" width="80%">
		<input type="text" name="caUserId" value="A123456a" class="read" size="25" maxlength="8" onkeyup="onlyEng(this);">
		<font color="red"> * Code required for certification, not related to the procurement system (8 characters)</font>
		</td>
	</tr>
	<tr>
		<td height="2" class="LINE" colspan="4"></td>
	</tr>
</table>


<!-- Add by thanhtv 2010.05.10 -->
<br>
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr" style="display:none">
	<tr>
		<th align="left" colspan="4">[CA issue and management Agency]</th>
	</tr>
	<tr>
		<td height="4" class="LINE" colspan="4"></td>
	</tr>
<!-- 	<tr> -->
<!--        <td class="tdar" width="20%">Tên cơ quan</td> -->
<!--        <td class="tdb"  colspan="2"> Trung tâm VDC - CA </td>     -->
<!--  	<td class="tdb"  width="30%"> -->
<!--            <input  type="radio" name="choiceCA" class="read" checked="true" value="6"> -->
<!--        </td> -->
<!--    </tr> -->
   <tr>
       <td class="tdar" width="20%"></td>
       <td class="tdb"  colspan="2">Public Procurement Agency - Ministry of Planning and Investment.  </td>
       <td class="tdb"  width="30%">
           <input  type="radio" checked="checked" name="choiceCA" class="read" value="5" style="display:none;">
       </td>
    </tr>
	<tr>
		<td height="2" class="LINE" colspan="4"></td>
	</tr>
</table>
</form>

<br>
<form name="stdFinanFrm" method="post" enctype="multipart/form-data"  acceptcharset="UTF-8"  target="regis_iframe"></form>
<br>
<table width="823" border="0" cellspacing="1" cellpadding="2" class="tr">
	<tr>
		<td align="center" height="24">
		<% if (existData) { %>
			<input type="button" class="commonbutton" value="Update" onclick="register(<%=mode%>);"/>
			<input type="button" class="commonbutton" value="Back" onclick="back();"/>
		<% } else { %>
			<input type="button" class="commonbutton" value="Register" onclick="register(0);"/>
			<input type="button" class="commonbutton" value="Reset" onclick="resetAll();"/>
		<% } %>
		</td>
	</tr>
</table>
<br>

<div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright_en.js"></script></div>
</div>
</body>
</html>