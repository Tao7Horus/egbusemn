<%@page language="java" %> 
<%@page contentType="text/html; charset=UTF-8" %> 
<%@ page errorPage="../jsp/common/jspToError.jsp?type=1&url=&message=" %> 
<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*,common.*,g2b.sso.*, common.util.*, LOGIN.*, java.text.SimpleDateFormat" %> 
<%@ include file = "../jsp/common/fnUmCommon.jsp" %>

<jsp:useBean id="ZipCode"     class="common.ZipCode" scope="page" />
<jsp:useBean id="ctl1"      class="beans.UM_RAB_GovuA010p" scope="page" /> 
<jsp:useBean id="License"   class="beans.UM_ADB_GovrA010c" scope="page" /> 
<jsp:useBean id="comUtil"   class="common.util.CommUtil" scope="page" /> 
<jsp:useBean id="nationalCtrl" class="beans.UM_RAB_Local" scope="page" />
 
<%
	SSO sso=new SSO(pageContext);	// SSO 생성
	// 권한체크 
    String recept       = comUtil.retSpace(request.getParameter("recept"));   		// 수요기관등록 신청 접수번호 
    String LicenseCode  = comUtil.retSpace(request.getParameter("LicenseCode"));   	// 수요기관인증서 신청 접수번호 
    String urlHistory	= comUtil.retSpace(request.getParameter("urlHistory"));   	// 목록 url history [뒤로가기 버튼에서 사용함] 
    String FirstUperCode = "0000000"; //G2B고도화사업 
  		 
		// 사용_접수수요기관마스터 테이블 정보 
    UM_RAE_GovuA010b ettcode=ctl1.select_goma(recept); 
    String masterCode=ettcode.getg2bCode(); // 수요기관코드 
    // 승인여부 
    String approval = ettcode.getenYn(); 
     
    UM_RAE_GovuA010b ettcode2 = null; 

	UM_ADE_GovuA040b ett2  = new UM_ADE_GovuA040b(); 
	if("Y".equals(approval)){         // 승인시 데이터 가지고 오기 
		ettcode2 = ctl1.select_gmaster(masterCode);  // 사용_수요기관마스터  
	  ett2     = ctl1.selectMath(masterCode);    // 사용_수요기관회계코드 
	} 
	 
	String dependCode   = "";        //수요기관인증서 신청 요청인가코드 
		 
	UM_ADJ_GovuA020b ettLicense = License.select_GovernmentList_Confirm(LicenseCode); 
	if (ettLicense != null) {
   		dependCode=ettLicense.getdependCode(); 
	}
    // 사용_접수수요기관회계코드 테이블정보 
    //UM_ADE_GovuA040b[] ett=ctl1.select_math(recept); 
        
    boolean isMakedCode=false; 

    //사용_행정표준정보 테이블정보 
    UM_GOJ_GovuA010b ettall=null; 
	
	String userid = sso.getID();

	// 현재날짜 구하기
	Date currentdate;
	String frmDate = null;

	request.setAttribute("Cur", new Date());
	currentdate=(Date)request.getAttribute("Cur"); 
	frmDate = new SimpleDateFormat("yyyyMMddHHmmss").format(currentdate);

	Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmdd");
	String currentDate = dateFormat.format(calendar.getTime());      // 현재일자
	
	String zipName = ZipCode.getZipType(ettcode.getZIPCODE());
    String insType = ZipCode.getInsType(ettcode.getInsType());
    String dependType = ZipCode.getDependType(ettcode.getperOff());
    
    UM_ADE_GovuA040b ett = ctl1.selectMath(recept);
%> 

<html> 
<head> 
<title>Phê duyệt đăng ký bên mời thầu</title>	 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<link rel="stylesheet" type="text/css" href="../css/TA.css"> 
<Script language="Javascript" src="../js/UM.js"></SCRIPT> 
<Script language="Javascript" src="../js/tel_check.js"></SCRIPT> 
 
</head>
 
<body>

<form name="useForm"> 

<div class="wrappertable">
<div class="wrapperhead"><span class="HEADLINENEW">Thông tin cơ bản về cơ quan đăng ký </span></div>
<div class="contenttable">

<!---------------------- 마스터정보 타이틀 테이블 시작--> 
	<table width="100%" cellpadding="2" cellspacing="1">
          <tr> 
            <td class="fontb"><!--[기관정보]-->[Thông tin cơ quan]</td>
          </tr>
        </table>
	<!---------------------- 마스터정보 타이틀 테이블 끝 ------------------------>
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
		<tr> 
				<td height="4" colspan="4" class="line"></td>
		</tr> 
    	<tr> 
			<td class="tdar" width="20%"><!-- 접수번호--> Số tiếp nhận</td>
			<td class="tdb"  width="30%"><font color = "red"><%=ettcode.getrecept()%></font>   
      		</td>
			<td class="tdar" width="20%"><!-- 수요기관코드--> Mã cơ quan</td>
			<td class="tdb"  width="30%"><%=ettcode.getg2bCode()%></td>	
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 수요기관명(전체)--> Tên cơ quan (đầy đủ)</td>
			<td class="tdb"  colspan="3"><%=ettcode.getgoNameFull() %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 수요기관명(약어)--> Tên cơ quan (viết tắt)</td>
			<td class="tdb"  colspan="3"><%=(ettcode.getgoNameShort()==null?"":ettcode.getgoNameShort()) %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 수요기관명(영문)--> Tên cơ quan (tiếng Anh)</td>
			<td class="tdb"  colspan="3"><%=ettcode.getgoNameEn() %></td>
		</tr>
		<!-- 기관유형(대, 중, 소) 추가 - Added in 2004.02.24 -->

		<tr>
			<td class="tdar" width="20%"><!-- 사업자등록번호--> Số ĐKKD</td>
			<td class="tdb"  width="30%" colspan="3"><%=ettcode.getsaupNo() %></td>
		</tr>
		<tr>
			<td class="tdar" width="20%"><!-- 소관구분-->
				Phân loại trực thuộc</td>
			<td   class="tdb">  
           		<%=dependType %>       
       		</td>
       		<td class="tdar" width="20%"><!-- 기관유형-->
				Loại hình cơ quan</td>
			<td class="tdb" >
				<%=insType %>
			</td>
		</tr>
		<tr>
			<td width="20%" class="tdar"><!-- 우편번호-->
			Tỉnh/Thành phố</td>
			<td width="30%" class="tdb" colspan="3">
				<%
					HashMap provinces = nationalCtrl.getAllHash("GU7");
					String codeName = "";
					Object a = provinces.get(ettcode.getZIPCODE());
					if (a != null)
						codeName = a.toString();
				%>
				<%=codeName%>
			</td>
		</tr>
		<tr>
			<td class="tdar" width="20%"><!-- 주소-->
				Địa chỉ</td>
			<td class="tdb" colspan="3"> <%=ettcode.getADDR() %> </td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 대표 전화번호--> Số điện thoại cơ quan</td>
			<td class="tdb" > <%=ettcode.gettelNum() %></td>
			<td class="tdar"><!-- 대표 팩스번호--> Số Fax cơ quan</td>
			<td class="tdb"> <%=ettcode.getfaxNum() %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 홈페이지-->Trang web</td>
			<td class="tdb"  colspan="3"> <%=(ettcode.gethomepage()==null?"":ettcode.gethomepage()) %></td>
		</tr>
		
		<tr> 
			<td class="tdar" width="20%"><!-- 업태-->Hình thái kinh doanh</td>
			<td class="tdb"  width="30%"> <%=(ettcode.getbuConditon()==null?"":ettcode.getbuConditon()) %></td>
			<td class="tdar" width="20%"><!-- 업종-->Ngành nghề</td>
			<td class="tdb"  width="30%"> <%=(ettcode.getbuType()==null?"":ettcode.getbuType()) %></td>
		</tr>
        <tr> 
			<td class="tdar" width="20%"><!-- 수요기관회계코드--> Mã số thuế</td>
			<td class="tdb"  colspan="3">
				<%=ett.getmathCode() %>
            </td>
		</tr>
        <tr> 
            <td height="2" colspan="4" class="line"></td>
        </tr> 
    </table>
<br>
    <table width="100%" cellpadding="2" cellspacing="1">
		<tr> 
			<td class="fontb"><!-- [담당자정보]-->[Thông tin người phụ trách]</td>
		</tr>
    </table>
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
        <tr> 
            <td height="4" colspan="4" class="line"></td>
        </tr>  		
        <tr> 
			<td class="tdar" width="20%"><!-- 담당자명--> Tên người phụ trách</td>
			<td class="tdb" width="30%"> <%=ettcode.gettaskmaster() %></td>
			<td class="tdar" width="20%"><!-- 담당자 부서명--> Số CMND</td>
			<td class="tdb" width="30%"> <%=ettcode.getcreditName() %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 담당자 부서명--> Phòng/Ban</td>
			<td class="tdb" width="30%"> <%=ettcode.getmasterPost() %></td>
			<td class="tdar" width="20%"><!-- 담당자 전화번호--> Số điện thoại</td>
			<td class="tdb" width="30%"> <%=ettcode.getmasterTel() %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 담당자 팩스번호--> Số Fax</td>
			<td class="tdb" width="30%"> <%=(ettcode.getmasterFax()==null?"":ettcode.getmasterFax())%></td>
			<td class="tdar" width="20%"><!-- 담당자 핸드폰번호--> Số di động</td>
			<td class="tdb" width="30%"> <%=(ettcode.getmasterhp()==null?"":ettcode.getmasterhp()) %></td> 
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 담당자 메일주소--> Địa chỉ email</td>
			<td class="tdb"  colspan="3"> <%=ettcode.getmasterMail() %></td>       
		</tr>		
        <tr> 
            <td height="2" colspan="4" class="line"></td>
        </tr> 
	</table>
	
<!---------------------- 마스터정보 타이틀 테이블 시작 ------------------------>
<!---------------------- 마스터정보 타이틀 테이블 끝 ------------------------>

	<br>
	<table width="100%" cellpadding="2" cellspacing="1">
		<tr> 
			<td class="fontb"><!--[인증서 신청여부]-->[Đăng ký chứng nhận số]</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
        <tr> 
            <td height="4" colspan="4" class="line"></td>
        </tr>
                
        <tr> 
			<td class="tdar" width="20%"><!-- 대표--> Tên người đại diện</td>
			<td class="tdb"  width="30%"> <%=ettLicense.getIDENT() %></td>
			<td class="tdar" width="20%"><!-- 대표자주민번호--> Số CMND</td>
			<td class="tdb"  width="30%"> <%=ettLicense.getIDENTJUMIN() %></td>
		</tr><!-- 2007.03.23 나라장터 내 주민등록번호 표시체계 개선 -->
        <tr> 
			<td class="tdar" width="20%"><!-- 인증서요청ID--> ID yêu cầu chứng nhận số</td>
			<td class="tdb"  width="30%" colspan="3"> <%=ettLicense.getUSRID() %></td>
		</tr>
		<tr >
            <td class="tdar" width="20%">Mã phê duyệt đăng ký<!-- 시 요청인가코드 "--></td>
            <td class="tdb" width="30%" colspan="3"><font color = "red"> <%=ettLicense.getdependCode() %></font>
            <!-- "은 필수입력 항목이니 숙지하셔야 합니다-->
            <!-- 대표자주민번호 뒷 5자리는 숨김처리하였습니다--></td>
        </tr>

        <tr> 
            <td height="2" colspan="4" class="line"></td>
        </tr> 	
	</table>

<input type="hidden" name="CLICK" value="">	
<input type="hidden" name="LicenseCode"  value="<%=LicenseCode%>"> 
<input type="hidden" name="CNAME"  value="<%=ettcode.getgoNameFull()%>">	
<input type="hidden" name="TELNO"  value="<%=ettcode.gettelNum()%>">	
<input type="hidden" name="HP_NO"  value="<%=ettcode.getmasterhp()%>">	
<input type="hidden" name="FAXNO"  value="<%=ettcode.getfaxNum()%>">	
<input type="hidden" name="ZIPNO"  value="<%=ettcode.getZIPCODE()%>">	
<input type="hidden" name="ADDRS"  value="<%=ettcode.getADDR()%>">	
<input type="hidden" name="COMPN"  value="<%=ettcode.getgoNameFull()%>">	
<input type="hidden" name="USRID"  value="<%=ettLicense.getUSRID()%>">	
<input type="hidden" name="NICKN"  value="hawk">	
<input type="hidden" name="COMNO"  value="<%=ettcode.getsaupNo()%>">	
<input type="hidden" name="g2bCode"  value="<%=ettcode.getg2bCode()%>">	
<input type="hidden" name="recept"  value="<%=recept%>">	
<input type="hidden" name="dependCode"  value="<%=ettLicense.getdependCode()%>">	
<input type="hidden" name="saupNo"  value="<%=ettcode.getsaupNo()%>">	

</form> 
 
<%  if ("Y".equals(approval)) {          %> 
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr>  
            <td height="4" colspan="4" class="line"></td> 
        </tr> 
        <tr> 
			<td width="100%" class="tdar" align="center"><font color = "red"><!-- 상기 수요기관에 대하여 수요기관등록 등록을 승인하셨습니다 -->Đã phê duyệt bên mời thầu trên.</font></td>             
		</tr> 
		<tr>  
            <td height="2" colspan="4" class="line"></td> 
        </tr>  
    </table> 
<%  } else if ("D".equals(approval)) {   %> 
	<table width="100%" cellpadding="2" cellspacing="1"> 
		<tr>  
			<td class="fontb"><!-- [반려사유] -->[Lý do từ chối] 
		</tr> 
	</table> 
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr>  
            <td height="4" colspan="4" class="line"></td> 
        </tr> 
        <tr> 
			<td width="20%" class="tdar"><textarea name="back" rows="2" cols="100" wrap="hard" readonly><%=ettcode.getback() %></textarea></td>             
		</tr> 
		<tr>  
            <td height="2" colspan="4" class="line"></td> 
        </tr>  
    </table> 
<br> 
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr>  
            <td height="4" colspan="4" class="line"></td> 
        </tr> 
        <tr> 
			<td width="20%" class="tdar" align="center"><font color = "red"><!-- 상기 수요기관에 대하여 수요기관등록 등록을 승인하셨습니다 -->Đã từ chối bên mời thầu trên.</font></td>             
		</tr> 
		<tr>  
            <td height="2" colspan="4" class="line"></td> 
        </tr>  
    </table> 
	<%  } else if ("E".equals(approval)) {   %> 
	<table width="100%" cellpadding="2" cellspacing="1"> 
		<tr>  
			<td class="fontb"><!-- [보류사유] -->[Lý do bảo lưu] 
		</tr> 
	</table> 
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr>  
            <td height="4" colspan="4" class="line"></td> 
        </tr> 
        <tr> 
			<td width="20%" class="tdar"><textarea name="back" rows="2" cols="100" wrap="hard" readonly><%=ettcode.getback() %></textarea></td>             
		</tr> 
		<tr>  
            <td height="2" colspan="4" class="line"></td> 
        </tr>  
    </table>
	
	<br>

	<table width="100%" cellpadding="2" cellspacing="1">
	<form name="denyForm">
	<tr>  
			<td class="fontb"><!-- [반려사유] -->[Lý do trả về] 
		</tr> 
	</table> 
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr>  
            <td height="4" colspan="4" class="line"></td> 
        </tr> 
        <tr> 
			<td width="20%" class="tdar"><textarea class="read" name="restoration"	wrap="hard"	value="" onkeyup="maxlength()" cols="100" rows="2"></textarea></td>
		</tr> 
		<tr>  
            <td height="2" colspan="4" class="line"></td> 
        </tr>  
    </table>
<input type=hidden name="recept" value="<%=recept%>"> 
<input type=hidden name="LicenseCode" value="<%=LicenseCode%>"> 
</form>
<form name="reloadForm"> 
<input type=hidden name="recept" value="<%=recept%>"> 
<input type=hidden name="LicenseCode" value="<%=LicenseCode%>"> 
<input type=hidden name="urlHistory" value="<%=urlHistory%>">
</form> 

<br> 
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr>  
            <td height="4" colspan="4" class="line"></td> 
        </tr> 
        <tr> 
			<td width="20%" class="tdar" align="center"><font color = "red"><!-- 상기 수요기관에 대하여 수요기관등록 등록을 승인하셨습니다 -->Đang bảo lưu bên mời thầu trên.</font></td>             
		</tr> 
		<tr>  
            <td height="2" colspan="4" class="line"></td> 
        </tr>  
    </table> 
<% } else if ("N".equals(approval)) {   %> 
<form name="denyForm"> 
	<table width="100%" cellpadding="2" cellspacing="1"> 
		<tr>  
			<td class="fontb"><!-- [보류 및 반려사유] -->[Lý do trả về hoặc bảo lưu] 
		</tr> 
	</table> 
	  
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr>  
            <td height="4" colspan="4" class="line"></td> 
        </tr> 
        <tr> 
			<td width="20%" class="tdar"><textarea class="read" name="restoration"	wrap="hard"	value="" onkeyup="maxlength()" cols="100" rows="2"></textarea></td>             
		</tr> 
		<tr>  
            <td height="2" colspan="4" class="line"></td> 
        </tr>  
    </table> 
<input type=hidden name="recept" value="<%=recept%>"> 
<input type=hidden name="LicenseCode" value="<%=LicenseCode%>"> 
</form> 
<form name="reloadForm"> 
<input type=hidden name="recept" value="<%=recept%>"> 
<input type=hidden name="LicenseCode" value="<%=LicenseCode%>"> 
<input type=hidden name="urlHistory" value="<%=urlHistory%>"> 
</form> 
<% } %> 
<br> 
<% if ("N".equals(approval)) {   %> 
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr align="center">  
            <td height="24"> 
                <input type="button" id="approve" class="commonbutton" value="Phê duyệt" onclick="javascript:js_submit();">
				<input type="button" id="backUp" class="commonbutton" value="Bảo lưu (chưa nộp tài liệu)" onclick="javascript:js_delay();">
				<input type="button" id="denied" class="commonbutton" value="Từ chối" onclick="javascript:js_deny();">
				<input type="button" id="print" class="commonbutton" value="In" onclick="javascript:js_print();">
				<input type="button" class="commonbutton" value="Quay lại" onclick="javascript:history.back();">
            </td> 
		</tr> 
	</table> 

	<% } else if ("E".equals(approval)) {   %> 
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr align="center">  
            <td height="24"> 
                <input type="button" id="approve" class="commonbutton" value="Phê duyệt" onclick="javascript:js_submit();">
				<input type="button" id="denied" class="commonbutton" value="Từ chối" onclick="javascript:js_deny();">
				<input type="button" id="print" class="commonbutton" value="In" onclick="javascript:js_print();">
				<input type="button" class="commonbutton" value="Quay lại" onclick="javascript:history.back();">
            </td> 
		</tr> 
	</table> 
<% } else { %> 
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr align="center">  
            <td height="24"> 
				<input type="button" id="print" class="commonbutton" value="In" onclick="javascript:js_print();">
				<input type="button" class="commonbutton" value="Quay lại" onclick="javascript:history.back();">
            </td> 
        </tr> 
    </table> 
<% }  %> 
 	</div>
	<div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
	</div>
 
<SCRIPT LANGUAGE="JavaScript"> 

    function js_submit() {  
		if(confirm("Bạn có chắc chắn phê duyệt bên mời thầu?")){ 
        	window.open("",'approveWin','status=yes,resizable=yes,scrollbars=no,toolbar=no,width=400,height=300'); 
		    document.useForm.target="approveWin"; 
        	document.useForm.method='post'; 
          	document.useForm.action='/servlet/servlets/UM_ADJ_GovrB020c'; 
        	document.useForm.submit(); 

        	disableButton(); 
        	return; 
        }
    } 
     
    // 신청-반려처리 
    function js_deny() { 

        if(document.denyForm.restoration.value =='') { 
        	//alert('반려사유는 필수입력항목 입니다'); 
        	alert('Hãy nhập lý do từ chối.'); 
            document.denyForm.restoration.focus(); 
            return; 
        } 
         
        if (document.denyForm.restoration.value.length > 150) { 
        	//alert("보류 및 반려사유의 내용이 너무 큽니다. 150자 이내로 줄여 주십시오!"); 
            alert("Thông tin lý do chỉ tối đa 150 ký tự!"); 
            document.denyForm.restoration.focus();
			return; 
        } 
         
        if (confirm("Bạn có chắc chắn từ chối không phê duyệt bên mời thầu?")) { 
 
            window.open("",'denyWin','status=yes,resizable=yes,scrollbars=no,toolbar=no,width=400,height=300'); 
			document.denyForm.target="denyWin"; 
            document.denyForm.method='post'; 
            document.denyForm.action='/servlet/servlets/UM_ADV_GovrB021c'; 
            document.denyForm.submit(); 

            disableButton();
            return; 
        } 
    } 
    
	// 신청 - 보류처리 
	function js_delay() {

        if(document.denyForm.restoration.value =='') { 
        	//alert('반려사유는 필수입력항목 입니다'); 
        	alert('Hãy nhập lý do bảo lưu.'); 
            document.denyForm.restoration.focus(); 
            return; 
        } 
         
        if (document.denyForm.restoration.value.length > 150) { 
        	//alert("보류 및 반려사유의 내용이 너무 큽니다. 150자 이내로 줄여 주십시오!"); 
            alert("Thông tin lý do chỉ tối đa 150 ký tự!"); 
            document.denyForm.restoration.focus();
			return; 
        } 
         
        if (confirm("Bạn có chắc chắn bảo lưu thông tin bên mời thầu?")) { 
 
            window.open("",'denyWin','status=yes,resizable=yes,scrollbars=no,toolbar=no,width=400,height=300'); 
			document.denyForm.target="denyWin"; 
            document.denyForm.method='post'; 
            document.denyForm.action='/servlet/servlets/UM_ADV_GovrB022c'; 
            document.denyForm.submit(); 

            var backUp = document.getElementById('backUp');
            backUp.disabled = true;
            return; 
        } 
    } 

	function disableButton() {
		var approve = document.getElementById('approve');
        var backUp = document.getElementById('backUp');
        var denied = document.getElementById('denied');
        approve.disabled = true;
        denied.disabled = true;
        if (backUp)
        	backUp.disabled = true;
        
	}
	
    function maxlength(){	 
		if (document.denyForm.restoration.value.length > 150) { 
            //alert("보류 및 반려사유의 내용이 너무 큽니다. 150자 이내로 줄여 주십시오!"); 
            alert("Thông tin lý do chỉ tối đa 150 ký tự!"); 
            document.denyForm.restoration.focus();
			return; 
        } 
	} 
	 
	function openerFunction(){ 
		document.reloadForm.target='_self'; 
		document.reloadForm.method='post'; 
        document.reloadForm.action='UM_RAJ_GovrA010u.jsp'; 
        document.reloadForm.submit(); 
        return; 
	 
	} 
	
	function js_print() {
		var regUrl="../RA/UM_RAJ_AdminReport.jsp?code=<%=recept%>"
		window.open(regUrl,'regSuyo','toolbar=yes,location=no,status=yes,menubar=yes,scrollbars=yes,resizable=no,width=750,height=700');
	}
</Script>
</body>
</html>
