<%--
/****************************************************************************/
/*                                                                          */
/*    Program ID    :     UM_RAJ_ConuC020i.jsp                              */
/*                                                                          */
/*    description   :     조달업체 인증서등록 화면		                    */
 /*                       Màn hình đăng ký GCN DN					*/
/*                                                                          */
/****************************************************************************/
/*  최초생성		2002.06.15			배 수 진                            */
/****************************************************************************/
/*  수    정		2004-05-10			하 광 규                            */
/****************************************************************************/
	
	2003.04.24	김영진	프로그램 수정
	2004.10.01	설원식	비축업체추가
	2004.10.18	윤태우  입찰참가자격이 없는 비축업체 인경우 대표자정보가 없음을 
	                    알리고 비축담당자 연락처를 안내해줌.
    2007.04.02  성해리  시설/공사진행통보서작성용 사용자 추가
    2007.07.06  이광현  공사관리진행통보서 사용인증서 등록 관련 제어로직 추가
    2007.11.01  이광현  보안모듈 설치여부 확인 로직 추가 & 지역번호에 080 추가
	2007.12.13	이광현	담당자명 등록시 유효성 체크 강화
	2009.05.12 SONNQ
	
/***************************************************************************/
/* Program ID         : UM_RAJ_ConuC020i.jsp                       */
/* Program Explanation: Màn hình đăng ký GCN DN     */
/* Program Summary    : Màn hình đăng ký GCN DN		*/
/* Relation Program   : */ 
/*                      */
/* Table              :                  */
/***************************************************************************/
/* Customizing Composer : MR. SonDN 27.05.2009                       */
/* Customizing Composer : MR. HungNT 16.08.2011                       */
/***************************************************************************/
	
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%//@ page errorPage="/common/jspToError.jsp?type=0&url=&message=001" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*" %>
<%@ page import="secu.lib.*"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.net.URLDecoder"%>

<jsp:useBean id="ctl"     class="beans.UM_URB_UserB030p" scope="page" />
<jsp:useBean id="ctl2"    class="beans.UM_URB_CERT" scope="page" />
<jsp:useBean id="webutil" class="common.WebUtil" scope="page" />
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />

<%!
/** 설명 : secu = new Secu(Secu.SE); //-> 업무팀에 해당하는 Secu.??것으로하세요.
 *  Constructor Parameter로 업무 주기능 ID를 주어서 로그를 구분을 출력한다.
 *  로그 위치 : /data/logs/SECU
 *
 *  Secu.ZZ =  0; //전자정부조달(KONEPS) 공통 -> 상위의 메인로그(logfilePath)를 따른다.
 *  Secu.UM =  1; //사용자 등록 관리 시스템
 *  Secu.LG =  2; //사용자 로그인 처리
 *  Secu.EV =  3; //전자조달처리(조달업체) 시스템
 *  Secu.EP =  4; //전자조달처리(전자입찰) 시스템
 *  Secu.EI =  5; //전자조달처리(물품구매) 시스템
 *  Secu.EF =  6; //전자조달처리(시설용역) 시스템
 *  Secu.LS =  7; //업체실적 및 기술인력조회 시스템
 *  Secu.EY =  8; //전자지불
 *  Secu.EA =  9; //전자보증시스템
 *  Secu.PT = 10; //나라장터포탈
 *  Secu.ED = 11; //전자문서유통시스템
 *  Secu.CS = 12; //고객서비스 시스템
 *  Secu.TA = 13; //통합공고시스템
 *  Secu.IM = 14; //목록관리 시스템
 *  Secu.IC = 15; //목록 카탈로그
 *  Secu.SE = 20; //문서보안- SOAP&XML 처리 등의 로그
 */
Secu secu = null;
public void jspInit(){ secu = new Secu(Secu.LG);} //-> 업무팀에 해당하는 Secu.??것으로하세요.
public void jspDestroy() {}
%>

<%
    // 인증서 선택창 상단 이미지 URL (2006.11.20)
    String logo_url = "http://muasamcong.mpi.gov.vn:8070/img/login/logo_n.bmp";

    String code = request.getParameter("code");
	String oper = request.getParameter("oper");

	UM_URE_UserB020b ett = null;		// entity class
  
	int count = ctl.Max_count(code);                                 
	int isExistCnt = ctl2.isDataExistOnNoBichukUpcheMaster(code);   // 입찰참가자격이 없는 비축업체인지를 판단함.
	ett = ctl.select_master(code);                                  // 대표자정보 및 비축업체정보 가지고 오기 .

	
	if (ett==null) {
		if(isExistCnt == 1){ // 비축담당자연락처 안내
		    	//response.sendRedirect("http://muasamcong.mpi.gov.vn:8070/html/BichukError1.htm");
                response.sendRedirect("http://muasamcong.mpi.gov.vn:8070/FO/BichukError.jsp?errType=NguoiDaiDien&userUpmu=DuPhong");
		}else{
				response.sendRedirect("/common/mPage.jsp?msg=5");
		}
		return;
	}
%>

<html>
<head>
<title>Đăng ký người sử dụng Chứng thư số</title><!-- 조달업체 인증서등록 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
<link href="http://muasamcong.mpi.gov.vn/css/TA.css" rel="stylesheet" type="text/css" /> 
<script language="javascript" src="/js/UM.js"></script>
<script language="javascript" src="/js/tel_check.js"></script>
<SCRIPT LANGUAGE="JavaScript"> 

function checkEmail(mail) {
    	var email = document.getElementById(mail);
    	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    	if (!filter.test(email.value)) {
	    	alert('Hãy nhập chính xác địa chỉ email!');
	    	email.select();
	    	email.focus();
	    	return false;
    	}
    	return true;
   	}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}
// 2007-11-01 보안모듈 설치여부 확인 로직 추가
var  binstall = false;

function ModuleInstallCheck()
{
    try
    {
		if ( SG_G2B.object == null ) 
		{
			return false;
		} 
		else 
		{
			return true;
		}
    }
    catch( e )
    {
		return false;
    }
}

//Enter Key가 입력되었을 경우
var cert = "<%=secu.getKmCertInfo(Secu.USR)%>";

//입력항목 체크/등록 스크립트 kiểm tra các trường thông tin nhập vào
function regist() {

	//document.forms[0].i03.value = telTypeMake(document.forms[0].usertel1.value,document.forms[0].usertel2.value,document.forms[0].usertel3.value);
	//document.forms[0].i04.value = telTypeMake(document.forms[0].userfax1.value,document.forms[0].userfax2.value,document.forms[0].userfax3.value);
	//document.forms[0].i05.value = telTypeMake(document.forms[0].usermobile1.value,document.forms[0].usermobile2.value,document.forms[0].usermobile3.value);


/*	if(!korean(document.forms[0].i01.value) || document.forms[0].i01.value ==''){
		alert('담당부서명은 필수입력항목이며 \n공백이 없이 한글로만 입력하셔야 합니다.');
		document.forms[0].i01.select();
		document.forms[0].i01.focus();
		return;
	}
	if(!korean(document.forms[0].i02.value) || document.forms[0].i02.value ==''){
		alert('담당자명은 필수입력항목이며 \n공백이 없이 한글로만 입력하셔야 합니다.');
		document.forms[0].i02.select();
		document.forms[0].i02.focus();
		return;
	}*/

	// blank Trim처리,  2자이상 25자까지	2007-12-13
	if(Trim(document.forms[0].i01.value) == ''){
		//alert('담당부서명은 필수 입력 항목입니다.');
		alert('Tên phòng ban phụ trách là hạng mục cần nhập. ');
		document.forms[0].i01.select();
		document.forms[0].i01.focus();
		return;
	}
	if(Trim(document.forms[0].i01.value).length < 2 || Trim(document.forms[0].i01.value).length >= 25){
	//	alert('담당부서명은 2자 이상이며 최대 25자까지입니다.');
		alert('Tên phòng ban từ 2 đến 25 ký tự.'); 
		document.forms[0].i01.focus();
		return;
	}

	// blank Trim처리, 한글 2자이상 10자까지	2007-12-13
	if(Trim(document.forms[0].i02.value) == ''){
		//alert('담당자명은 필수 입력 항목입니다.');
		alert('Họ tên là hạng mục cần nhập');
		document.forms[0].i02.select();
		document.forms[0].i02.focus();
		return;
	}
	if(document.forms[0].i02.value.length < 5 || document.forms[0].i02.value.length >= 50){
		//alert('담당자명은 2자 이상이며 최대 10자까지입니다.'); 
		alert('Họ tên từ 5 đến 50 ký tự');
		document.forms[0].i02.focus();
		return;
	}
	//if(!korean(document.forms[0].i02.value)) {
	////	alert('담당자명은 한글입력만 가능합니다.');
	//	alert('Chỉ nhập tên người phụ trách bằng tiếng Hàn.');
	//	document.forms[0].i02.focus();
	//	return;
	//}

	if(document.forms[0].i03.value ==''){
		alert('Hãy nhập số điện thoại');//alert('값을 입력해 주십시오');
		document.forms[0].i03.select();
		document.forms[0].i03.focus();
		return;
	}else{
		//if(!(isNum(document.forms[0].i03,'담당자전화번호는 숫자만 입력하실수 있습니다.')))
		if(!(isNum(document.forms[0].i03,'Số điện thoại chỉ nhập bằng số.')))
			return;
	}
	if(document.forms[0].i04.value ==''){
		//alert('Hãy nhập số Fax'); 
		//document.forms[0].i04.select();
		//document.forms[0].i04.focus();return;
	}else{
		if(!(isNum(document.forms[0].i04,'Số Fax chỉ nhập bằng số.')))
		//if(!(isNum(document.forms[0].i04,'담당자팩스번호는 숫자만 입력하실수 있습니다.')))
			return;
	}
	if(document.forms[0].i05.value ==''){
		alert('Hãy nhập số di động'); 
		document.forms[0].i05.select();
		document.forms[0].i05.focus();return;
	}else{
		if(!(isNum(document.forms[0].i05,'Số di động phải nhập bằng số.')))//if(!(isNum(document.forms[0].i05,'담당자이동전화번호는 숫자만 입력하실수 있습니다.')))
			return;
	}/**/

	//if(document.forms[0].usertel1.value ==''){
	//	//alert('담당자전화번호를 입력해 주십시오');
	//	alert('Nhập số điện thoại của người phụ trách');
	//	document.forms[0].usertel1.focus();
	//	return;
	//}else{
	//	//if(!(isNum(document.forms[0].usertel1,'담당자전화번호는 숫자만 입력하실수 있습니다.')))
	//	if(!(isNum(document.forms[0].usertel1,'Số  điện thoại người phụ trách chỉ có thể nhập số.')))
	//		return;
	//}
	//if(document.forms[0].usertel2.value ==''){
	////	alert('담당자전화번호를 입력해 주십시오');
	//	alert('Nhập số điện thoại của người phụ trách');
	//	document.forms[0].usertel2.select();
	//	document.forms[0].usertel2.focus();
	//	return;
	//}else{
	//	//if(!(isNum(document.forms[0].usertel2,'담당자전화번호는 숫자만 입력하실수 있습니다.')))
	//	if(!(isNum(document.forms[0].usertel2,'Số  điện thoại người phụ trách chỉ có thể nhập số.')))
	//		return;
	//}
	//if(document.forms[0].usertel3.value ==''){
	////	alert('담당자전화번호를 입력해 주십시오');
	//	alert('Nhập số điện thoại của người phụ trách');
	//	document.forms[0].usertel3.select();
	//	document.forms[0].usertel3.focus();
	//	return;
	//}else{
	//	//if(!(isNum(document.forms[0].usertel3,'담당자전화번호는 숫자만 입력하실수 있습니다.')))
	//	if(!(isNum(document.forms[0].usertel3,'Số  điện thoại người phụ trách chỉ có thể nhập số.')))
	//	return;
	//}

	//if(document.forms[0].userfax1.value ==''){
	//	//alert('담당자팩스번호를 입력해 주십시오');
	//	alert('Nhập số Fax của người phụ trách');
	//	document.forms[0].userfax1.focus();
	//	return;
	//}else{
	//	//	if(!(isNum(document.forms[0].userfax1,'담당자팩스번호는 숫자만 입력하실수 있습니다.')))
	//	if(!(isNum(document.forms[0].userfax1,'Số fax người phụ trách chỉ có thể nhập bằng số.')))
	//		return;
	//}
	//if(document.forms[0].userfax2.value ==''){
	////	alert('담당자팩스번호를 입력해 주십시오');
	//	alert('Nhập số Fax của người phụ trách ');
	//	document.forms[0].userfax2.select();
	//	document.forms[0].userfax2.focus();
	//	return;
	//}else{
	//	//	if(!(isNum(document.forms[0].userfax2,'담당자팩스번호는 숫자만 입력하실수 있습니다.')))
	//	if(!(isNum(document.forms[0].userfax2,'Số fax người phụ trách chỉ có thể nhập bằng số.')))
	//		return;
	//}
	//if(document.forms[0].userfax3.value ==''){
	//	//alert('담당자팩스번호를 입력해 주십시오');
	//	alert('Nhập số Fax của người phụ trách');
	//	document.forms[0].userfax3.select();
	//	document.forms[0].userfax3.focus();
	//	return;
	//}else{
	////	if(!(isNum(document.forms[0].userfax3,'담당자팩스번호는 숫자만 입력하실수 있습니다.')))
	//if(!(isNum(document.forms[0].userfax3,'Số fax người phụ trách chỉ có thể nhập bằng số.')))
	//			return;
	//}

	//if(document.forms[0].usermobile1.value ==''){
	//	//alert('담당자이동전화번호를 입력해 주십시오');
	//	alert('Nhập số di động của người phụ trách');
	//	document.forms[0].usermobile1.focus();
	//	return;
	//}else{
	//	//if(!(isNum(document.forms[0].usermobile1,'담당자이동전화번호는 숫자만 입력하실수 있습니다.')))
	//	if(!(isNum(document.forms[0].usermobile1,'Số di động của người đại diện chỉ có thể nhập bằng số.')))
	//		return;
	//}
	//if(document.forms[0].usermobile2.value ==''){
	////	alert('담당자이동전화번호를 입력해 주십시오');
	//	alert('Nhập số di động của người phụ trách');
	//	document.forms[0].usermobile2.select();
	//	document.forms[0].usermobile2.focus();
	//	return;
	//}else{
	//	//if(!(isNum(document.forms[0].usermobile2,'담당자이동전화번호는 숫자만 입력하실수 있습니다.')))
	//	if(!(isNum(document.forms[0].usermobile2,'Số di động của người đại diện chỉ có thể nhập bằng số.')))
	//		return;
	//}
	//if(document.forms[0].usermobile3.value ==''){
	//	//alert('담당자이동전화번호를 입력해 주십시오');
	//	alert('Nhập số di động của người phụ trách');
	//	document.forms[0].usermobile3.select();
	//	document.forms[0].usermobile3.focus();
	//	return;
	//}else{
	//	//if(!(isNum(document.forms[0].usermobile3,'담당자이동전화번호는 숫자만 입력하실수 있습니다.')))
	//	if(!(isNum(document.forms[0].usermobile3,'Số di động của người đại diện chỉ có thể nhập bằng số.')))
	//	return;
	//}

	if(document.forms[0].i06.value =='') {
	//	alert('메일주소는 필수입력항목 입니다.');
	alert('Địa chỉ mail là hạng mục phải nhập .');
		document.forms[0].i06.select();
		document.forms[0].i06.focus();
		return;
	}else{
		if (!checkEmail('i06')) {  
			return;
		} 
	}
	if(document.forms[0].i07.value =='' || document.forms[0].i07.value.length < 4 || document.forms[0].i07.value.length > 13){
	//	alert('이용자관리비밀번호는 필수입력항목이며 영문이나 숫자 4자리 이상 13자리 이하로 입력하셔야 합니다.');
		alert('Mật khẩu của người dùng là hạng mục phải nhập, hãy nhập bằng chữ Latin hoặc số từ 4 đến 13 kí tự.');
		document.forms[0].i07.select();
		document.forms[0].i07.focus();
		return;
	}

	var pass = document.forms[0].i07.value;
	var tel = document.forms[0].i03.value;
    var code = document.forms[0].d04.value;
	var token = pass.substr(0,1);
	var cmp = "";
	for (i=0 ; i<pass.length ; i++) {
		cmp = cmp + token;
	}

	if (pass==code) {
	//	alert("이용자관리비밀번호로 사업자등록번호를 쓰실 수 없습니다.");
		alert("Không thể dùng số ĐKKD làm mật khẩu.");
		document.forms[0].i07.select();
		return;
	} else if (pass==tel) {
		//alert("이용자관리비밀번호는 전화번호와 동일하게 쓰실수 없습니다.");
		alert("Không thể dùng số điện thoại làm mật khẩu.");
		document.forms[0].i07.select();
		return;
	} else if (pass==cmp) {
		//alert("이용자관리비밀번호는 동일한 문자로만 쓰실수 없습니다.");
		alert("Không thể chỉ dùng kí tự  giống nhau làm mật khẩu.");
		document.forms[0].i07.select();
		return;
	} else if ("01234567890".indexOf(pass)!=-1) {
		//alert("이용자관리비밀번호는 연속되는 일련번호로 쓰실수 없습니다.");
		alert("Không thể dùng chuỗi chữ số liên tục làm mật khẩu.");
		document.forms[0].i07.select();
		return;
	} else if ("09876543210".indexOf(pass)!=-1) {
	//	alert("이용자관리비밀번호는 연속되는 일련번호로 쓰실수 없습니다.");
		alert("Không thể dùng chuỗi chữ số liên tục làm mật khẩu.");
		document.forms[0].i07.select();
		return;
	} else if ("abcdefghijklmnopqrstuvwxyz".indexOf(pass)!=-1) {
	//	alert("이용자관리비밀번호는 연속되는 알파벳으로 쓰실수 없습니다.");
		alert("Không thể dùng chữ cái abc liên tục làm mật khẩu.");
		document.forms[0].i07.select();
		return;
	} else if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(pass)!=-1) {
		//alert("이용자관리비밀번호는 연속되는 알파벳으로 쓰실수 없습니다.");
		alert("Không thể dùng chữ cái abc liên tục làm mật khẩu.");
		document.forms[0].i07.select();
		return;
	}

	if(document.forms[0].i07.value != document.forms[0].i17.value) {
	//	alert("입력한 이용자관리비밀번호가 서로 틀립니다.")
		alert("Số mật khẩu quản lý người dùng đã nhập không khớp nhau.");
		document.forms[0].i07.select();
		document.forms[0].i07.focus();
		return;
	}

    if (document.forms[0].constChk != null) {
        if (document.forms[0].constChk.checked == true) {
           // if (confirm("공사진행통보서 송수신 여부에 체크시 투찰 및 조달업체업무 대부분을 사용할 수 없습니다.\r\n\r\n" +
           //       "일반 조달업체 업무를 이용하시려면 공사진행통보서 송수신 여부에 체크를 하지 마십시오.\r\n\r\n" +
           //       "계속 진행 하시려면 [확인]을 내용을 수정하시려면 [취소] 버튼을 클릭하십시오.")
                  if (confirm("không thể sử dụng hầu hết các nghiệp vụ của nhà thầu và dự thầu nếu chọn tùy chọn gửi nhận thông báo tiến hành xây lắp.\r\n\r\n" 
                          + "Muốn sử dụng nghiệp vụ nhà thầu cơ bản, không chọn tùy chọn gửi nhận thông báo tiến hành xây lắp.\r\n\r\n" 
                          + " Nếu định tiến hành liên tục chọn  'Xác nhận' nếu định sửa nội dung nhấn nút 'Hủy bỏ'")
               ) {
                 document.forms[0].oper.value = "d";

				 if (document.forms[0].i08.checked==true){			// 2007-07-06 추가
					 document.forms[0].i08.checked=false;			// 2007-07-06 추가
					// alert('공사진행통보서 송수신 여부에 체크하면 대표수신자가 될 수 없습니다.\r\n체크를 해제합니다!!');		// 2007-07-06 추가
					 alert('Nếu check tùy chọn gửi thông báo tiến hành thi công thì không thể thành người đại diện nhận!!');		// 2007-07-06 추가
				 }		// 2007-07-06 추가
             } else {
                 document.forms[0].constChk.focus();
                 return;
             }
        }
    }

    /*
    // 인증서 관련 START
	var res = SG_G2B.SG_GenLoginInfo( '' );
	var res1 = SG_G2B.SG_GetUserDN("S");
	var res2 = SG_G2B.SG_GetUserDN("E");
	var res3 = SG_G2B.SG_GetCertString("S");
	var res4 = SG_G2B.SG_GetCertString("E");
	if (res=="") { 
		alert("인증서 로딩 실패");
		return;
	}
    //	alert( "로그인 성공 - 서명용 DN정보\r\r" + res1 + "\r- 암호화용 DN정보\r\r" + res2 + "\r- 서몀용인증서\r\r" + res3 + "\r- 암호화용인증서\r\r" + res4 );
	document.forms[0].DN1.value = "";
	document.forms[0].DN2.value = "";
	document.forms[0].CERT1.value = "";
	document.forms[0].CERT2.value = "";
	document.forms[0].DN1.value = res1;
	document.forms[0].DN2.value = res2;
	document.forms[0].CERT1.value = res3;
	document.forms[0].CERT2.value = res4;
    // 인증서 관련 END

	var res2 = SG_G2B.SG_GetRANDKey();
	if ( res2 == "" ) {
		alert("신원확인이 적용되지 않은 인증서입니다.\r\r"+
		      "해당 공인인증기관 홈페이지에서 신원확인이 적용된 인증서로 갱신하시기 바랍니다.");
		return;
	}
    //	alert( "신원확인 No - [" + res2 +"]");
	document.forms[0].IDENT_INFO.value = res2;
    */

	//**************************************************************//
	// GPKI 인증서 사용가능하도록 (2006.11.20)
	//**************************************************************//

	// 인증서 선택창 상단 이미지 로고 설정 
    res     = SG_G2B.SG_SetCertDlgImage("<%=logo_url%>");

	// 서버인증서 유효성 검증
	var res     = SG_G2B.SG_CertValidate ("S", cert);

	// 서버인증서로 암호화된 대칭키 생성
    var enc_key = SG_G2B.SG_GenSessionKey(cert);

    if (isNullValue(enc_key)){
       // alert("암호화된 대칭키를 얻는데 실패했습니다.");
       alert("Không lấy được key đồi xứng mã hóa.");
        return;
    }

	// 인증서 등록정보 생성
    var sign_data = SG_G2B.SG_GenLoginInfo('');
	if (sign_data=="") { 
	//	alert("인증서 정보를 읽을 수 없습니다.");
		alert("Không thể đọc được thông tin Chứng thư số");
		return;
	}

	var res1 = SG_G2B.SG_GetUserDN("S");
	var res2 = SG_G2B.SG_GetUserDN("E");
	var res3 = SG_G2B.SG_GetCertString("S");
	var res4 = SG_G2B.SG_GetCertString("E");


	var oper = "<%=oper%>";
    if (oper != "x") {

			// 기관용으로 발급된 인증서를 조달업체용으로 등록하지 못하도록
			//if (res1.indexOf("테스트") == -1) {
			if (res1.indexOf("Test") == -1) {
				//if(res1.indexOf("ou=G2B") != -1 || res1.indexOf("ou=조달청") != -1) {
				if(res1.indexOf("ou=G2B") != -1 || res1.indexOf("ou=Cục đấu thầu") != -1) {
					//alert("수요기관용으로 발급된 인증서를 조달업체용으로 등록할 수 없습니다.\n\n"+
						//  "공인인증기관에서 전자거래범용 인증서를 발급받아 사용하십시오.");
						alert("Không thể đăng ký Chứng thư số cấp cho cơ quan làm Chứng thư số của nhà thầu.\n\n"+
						 "Phải đăng kí xin cấp Chứng thư số tại cơ quan Chứng thư số rồi mới được sử dụng.");
					return;
				}
			}
		} 


	/********************************************************************************************
	* 신원확인 검사를 위해 사용자의 인증서에서 신원확인값을 얻어내는 함수
	* SG_GenLoginInfoUseCert, SG_LoadKeyCert 와 같이 인증서를 선택하는 과정이 선행되어야 한다.
	********************************************************************************************/
	var randKey = "";
	var isGcc = SG_G2B.SG_IsGccCert(res3);
	if (isGcc)	// GPKI Cert 는 신원확인용 랜덤값을 얻을수 없다... 따라서 "GPKI" 라는 플래그를 넣어서 서버로 전송한다.
	{
		randKey = "GPKI";
	}
	else		// NPKI Cert
	{		
		randKey = SG_G2B.SG_GetRANDKey();
		if ( randKey == "" )
		{
			SG_G2B.SG_UnLoadKeyCert();
			//alert("신원확인이 적용되지 않은 인증서입니다.\r\r"+
				//  "해당 공인인증기관 홈페이지에서 신원확인이 적용된 인증서로 갱신하시기 바랍니다.");
				alert("Chứng thư số không xác nhận nhân thân được .\r\r"+
			 "Hãy gia hạn thành Chứng thư số dùng để xác nhận nhân thân tại trang web của cơ quan cấp chứng nhận tương ứng.");
			return;
		}
	}

	// 사용자가 선택한 인증서의 소유자임을 확인하기 위하여 필요한 값은 중요한 데이터이므로 암호화하여 전달하여야 한다.
	var enc_randKey = SG_G2B.SG_EncryptData(randKey);
	if ( enc_randKey == "" )
	{
		SG_G2B.SG_UnLoadKeyCert();
		//alert( "신원확인을 위한 정보 대칭키암호화 실패." )
		alert( "Không mã hóa khóa đối xứng được thông tin để xác nhận nhân thân ." );
		return;
	}	
	/********************************************************************************************/


	document.forms[0].DN1.value = "";
	document.forms[0].DN2.value = "";
	document.forms[0].CERT1.value = "";
	document.forms[0].CERT2.value = "";
	document.forms[0].ENC_IDENT_INFO.value  = "";
	document.forms[0].ENC_KEY_INFO.value    = "";
	document.forms[0].SIGN_LOGIN_INFO.value	= "";
 

	//전송할 폼데이터
	document.forms[0].DN1.value				= res1;			// 전자서명인증서고유명
	document.forms[0].DN2.value				= res2;			// 암호화인증서고유명
	document.forms[0].CERT2.value			= res4;			// 암호화인증서
	document.forms[0].ENC_IDENT_INFO.value  = enc_randKey;	// 대칭키로 암호화된 신원확인정보
	document.forms[0].ENC_KEY_INFO.value    = enc_key;		// 서버인증서로 암호화된 대칭키
	document.forms[0].SIGN_LOGIN_INFO.value	= sign_data;	// 로그인인증서전자서명데이터
    //document.forms[0].IDENT_INFO.value = res2;

	var result = SG_G2B.SG_UnLoadKeyCert();

 	if (!confirm('Bạn chắc chắn đăng ký mới người sử dụng này không?')==true) return;
	document.forms[0].target="_self";
	document.forms[0].method="post";
	document.forms[0].action="/servlet/servlets/UM_URV_UserB010c01"
	document.forms[0].submit();
	return;
}

//입력항목 내용 삭제 스크립트
function clear() {
	document.forms[0].reset();
	document.forms[0].i01.focus();
}

//대표수신자여부 체크시 경고 스크립트
function message(flag) {
	if (flag=="1") {
		if (document.forms[0].i08.checked==true){
		//	alert('이ID가 대표수신자 역할을 합니다. 기존의 대표수신자는 대표기능을 상실합니다.');
		alert('ID này có vai trò là người nhận đại diện. Chức năng đại diện cũ sẽ bị mất.');
		}
	} else if (flag=="0") {
		if (document.forms[0].constChk != null && document.forms[0].constChk.checked == true) {	// 2007-07-06 추가
			document.forms[0].i08.checked=false;			// 2007-07-06 추가
			//alert('공사진행통보서 송수신 여부에 체크하면 대표수신자가 될 수 없습니다.');		// 2007-07-06 추가
		alert('Nếu check tùy chọn gửi thông báo tiến hành thi công thì không thể thành người đại diện nhận.');		// 2007-07-06 추가
		} else {		// 2007-07-06 추가
		document.forms[0].i08.checked=true;
	//	alert('처음 등록하는 사용자는 대표수신자여부를 필수적으로 선택해야 합니다.');
		alert('Người dùng đăng kí đầu tiên bắt buộc phải chọn tùy chọn người nhận đại diện.');
		}				// 2007-07-06 추가
	}
	return;
}

	// 2007-11-01
	function Init()
	{
		/*if( ModuleInstallCheck() == false )
		{
			//alert( " [보안모듈이 설치되지 않았습니다..고객지원 - 자료실 - 일반자료실 - 36번(윈도우 비스타인 경우 58번) \n 파일을 설치 해 주시고 다시 시도해주시기 바랍니다]");
			alert( " [ Chưa cài đặt module bảo mật… Hỗ trợ khách hàng- Tư liệu- phòng tài liệu chung- số 36 (window vista chọn số 58)]");
			binstall = false;
			return false;
		}	
		binstall = true;
		return true;
*/
	}

	// 페이지 onload될때 스크립트 실행		2007-11-01
	function window::onload(){ Init() } 

    // 특수문자 체크	2007-12-13
	function sc_check(val) { 
 
        var mikExp = /[$\\@\\\#%\^\&\*\{\}\`\'\|]/; 
        var strPass = val.value; 
        var strLength = strPass.length; 
        var lchar = val.value.charAt((strLength) - 1); 
        if(lchar.search(mikExp) != -1) { 
            var tst = val.value.substring(0, (strLength) - 1); 
            //alert ('제한되어 있는 특수 문자는 다음과 같으며\n'
             alert ('Các kí tự đặc biệt bị giới hạn như sau\n'  
                  +' $ \ @ # % ^ & * ` ~ | \n' 
               //   +' 특수문자는 사용할수 없습니다.'); 
             +' Không được sử dụng các kí tự đặc biệt.'); 
            val.value = tst; 
        } 
    }

 </SCRIPT>
</head>
<body >
<script src="http://muasamcong.mpi.gov.vn/activex/kica.js"></script>
<div class="col-750 clearfix last">
	<h1 class="pageTitle"><i class="icon-title"></i>
		<% if (oper.equals("x")) { %>
		Đăng kí Chứng thư số nhà thầu dùng hàng hóa dự phòng			<!-- 비축물자이용업체 인증서등록 -->
		<% } else if (oper.equals("d")) { %>
			Đăng kí Chứng thư số nhận thông báo tiến hành xây lắp <!-- 공사진행통보서송수신 인증서등록 -->
		<% } else { %>
			Đăng ký người sử dụng Chứng thư số<!-- 조달업체 인증서등록 -->
		<% } %>
	</h1>

<table width="100%" border="0" cellspacing="0" cellpadding="0">    
	<tr><td><jsp:include page="/indexFlow.jsp?gubun=2&req=14" /></td></tr>
</table>

<form name="fm">
<input type="hidden" name="flag"            value="i">
<input type="hidden" name="DN1"             value="">
<input type="hidden" name="DN2"             value="">
<input type="hidden" name="CERT1"           value="" >
<input type="hidden" name="CERT2"           value="" >
<input type="hidden" name="ENC_IDENT_INFO"  value="" >
<input type="hidden" name="ENC_KEY_INFO"    value="" >
<input type="hidden" name="SIGN_LOGIN_INFO" value="" >
<input type="hidden" name="IDENT_INFO"      value="" >
<input type="hidden" name="oper"            value = "<%=oper%>">
<input type="hidden" name="user_gubun"      value="c">
 
        <!---------------------- 기본정보 타이틀 테이블 시작 ------------------------>
        <table width="100%" cellpadding="2" cellspacing="1">
          <tr> 
            <td class="fontb">[Thông tin cơ quan]<!-- 기본정보 --></td>
          </tr>
        </table>
        <!---------------------- 기본정보 타이틀 테이블 끝 ------------------------>
        <!---------------------- 기본정보 테이블 시작 ------------------------>
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
          <tr> 
            <td height="4" colspan="4" class="line"></td>
          </tr>
          <tr>
            <td class="tdar" width="22%"><p>Tên nhà thầu <!--  상호명 --></p></td>
            <td class="tdb" width="28%"><input type="hidden" name="d01" value="<%=ett.getCompName()%>"><%=ett.getCompName()%></td>
            <td class="tdar" width="22%"><p>Phân loại kinh doanh <!-- 사업구분 --></p></td>
            <td class="tdb" width="28%"><input type="hidden" name="d02" value="<%=ett.getCompClass()%>">
			<%//=
			//	ett.getCompClass().substring(0,1).equals("1")?"물품":""
				//	ett.getCompClass().substring(0,1).equals("1")?"Hàng hóa":""
				%>
			<%//=
				//ett.getCompClass().substring(1,2).equals("1")?"공사":""
				//	ett.getCompClass().substring(1,2).equals("1")?"Thi công":""
				%>
			<%//=
			//	ett.getCompClass().substring(2,3).equals("1")?"용역":""
				//	ett.getCompClass().substring(2,3).equals("1")?"Dịch vụ":""
				%>
			<%//=
				//ett.getCompClass().substring(3,4).equals("1")?"일반용역":""
				//	ett.getCompClass().substring(3,4).equals("1")?"Dịch vụ thông thường":""
				%>
			<%//=
				//ett.getCompClass().substring(4,5).equals("1")?"외자":""
				//	ett.getCompClass().substring(4,5).equals("1")?"Hàng hóa ngoại nhập":""
				%>
				<%=ett.getCompClass().indexOf("1")>-1?"Hàng hóa":""%>
				<%=ett.getCompClass().indexOf("3")>-1?"Xây lắp":""%>
				<%=ett.getCompClass().indexOf("5")>-1?"Tư vấn":""%>
            </td>
          </tr>
          <tr>
            <td class="tdar"><p>Người đại diện <!-- 대표자 --></p></td>
            <td class="tdb" width="28%"><input type="hidden" name="d03" value="<%=ett.getCompRepresentative()%>"><%=ett.getCompRepresentative()%></td>
            <td class="tdar" width="22%"><p>Số ĐKKD <!-- 사업자등록번호 --></p></td>
<%
	String tempcode = ett.getCompCode();
%>
            <td class="tdb" width="28%"><input type="hidden" name="d04" value="<%=ett.getCompCode()%>"><%=ett.getCompCode()%></td>
          </tr>
          <tr>
            <td class="tdar"><p>Số điện thoại <!-- 전화번호 --></p></td>
            <td class="tdb"><input type="hidden" name="d05" value="<%=ett.getCompTelephone()%>"><%=ett.getCompTelephone()%></td>
            <td class="tdar"><p>Trang web <!-- 홈페이지 --></p></td>
            <td class="tdb"><input type="hidden" name="d06" value="<%=ett.getCompHomepage()%>"><%=ett.getCompHomepage()==null?"":ett.getCompHomepage()%></td>
          </tr>
          <tr>
            <td class="tdar"><p>Địa chỉ  <!-- 본사주소 --></p></td>
            <td class="tdb" colspan="3"><input type="hidden" name="d07" value="<%=ett.getCompAddress()%>"><%=ett.getCompAddress()==null?"":ett.getCompAddress()%></td>
          </tr>
          <tr> 
            <td height="2" colspan="4" class="line"></td>
          </tr>
        </table>
        <!---------------------- 기본정보 테이블 끝 ---------------------------->
        <br> 
        <!---------------------- 인증서정보 타이틀 테이블 시작 ------------------------>
        <table width="100%" cellpadding="2" cellspacing="1">
          <tr> 
            <td class="fontb">[Thông tin người sử dụng CTS Nhà thầu] <!-- 인증서정보 --></td>
          </tr>
        </table>
        <!---------------------- 인증서정보 타이틀 테이블 끝 ------------------------>
        <!---------------------- 인증서정보 테이블 시작 ------------------------>
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
          <tr> 
            <td height="4" colspan="4" class="line"></td>
          </tr>
          <tr>
          	<td class="tdar" width="22%"><p><font color="Red">*</font> Họ tên <!-- 담당자명 --></p></td>
            <td class="tdb" width="28%"><input type="text" name="i02" onKeyUp="sc_check(this);" maxlength="50" class="read"></td>
            <td class="tdar" width="22%"><p><font color="Red">*</font> Phòng/Ban <!-- 담당부서 --></p></td>
            <td class="tdb" width="28%"><input type="text" name="i01" onKeyUp="sc_check(this);" maxlength="25" class="read"></td>
            
          </tr><!-- 특수문자 체크 2007-12-13 -->
		  
		  <tr>
			<td class="tdar"> <p><font color="Red">*</font> Số điện thoại  <!-- 담당자전화번호 --></p>  </td>
            <td class="tdb"><%//=List.getCode("TFH", "usertel1", "GUL", "")%>
				<%/*-&nbsp;
				<input class="read" name="usertel2" maxlength="4" value="" size="6" maxlength="4">
				-&nbsp;
				<input class="read" name="usertel3" maxlength="4" value="" size="6" maxlength="4">
				<input type="hidden" name="i03" maxlength="20" class="read"><!-- 2007-11-01 -->*/%>
				
				<input type="text" name="i03" maxlength="20" class="read"  onkeypress="return isNumberKey(event)">
			</td>
            <td class="tdar"> <p>&nbsp;&nbsp;Số Fax  <!-- 담당자팩스번호 --></p>  </td>
            <td class="tdb"><%//=List.getCode("TFH", "userfax1", "GUM", "")%>	
				<%/*-&nbsp;
				<input class="read" name="userfax2" maxlength="4" value="" size="6" maxlength="4">
				-&nbsp;
				<input class="read" name="userfax3" maxlength="4" value="" size="6" maxlength="4">
				<input type="hidden" name="i04" maxlength="20" class="read"><!-- 2007-11-01 -->*/%>
				<input type="text" name="i04" maxlength="20" class="read" onkeypress="return isNumberKey(event)">
			</td>
        </tr>
        <tr>
            <td class="tdar">
            <p><font color="Red">*</font> Số di động <!-- 이동전화번호 --></p>
            </td>
            <td class="tdb"><%//=List.getCode("TFH", "usermobile1", "GUN", "")%>
				<%/*-&nbsp;
				<input class="read" name="usermobile2" maxlength="4" value="" size="6">
				-&nbsp;
				<input class="read" name="usermobile3" maxlength="4" value="" size="6">
				<input type="hidden" name="i05" maxlength="12" class="read"><!-- 2007-11-01 -->*/%>
				
				<input type="text" name="i05" maxlength="20" class="read" onkeypress="return isNumberKey(event)">
			</td>



            <td class="tdar"><p><font color="Red">*</font> Địa chỉ email<!-- 메일주소 --></p></td>
            <td class="tdb"><input type="text" name="i06" maxlength="50" size = "30" class="read"></td>
          </tr>
          <tr style="display: none">
            <td class="tdar"><p><font color="Red">*</font>Mật khẩu <!-- 이용자관리비밀번호 --></p></td>
            <td class="tdb" colspan="3"><input type="password" name="i07" maxlength="13" value="A123456a" class="read">Từ 4 đến 13 kí tự số và chữ tiếng Anh<!-- 영문이나 숫자 4자리 이상 13자리 이하 --></td>
          </tr>          
          <tr style="display: none">
            <td class="tdar"><p><font color="Red">*</font> Xác nhận mật khẩu<!-- 이용자관리비밀번호확인 --></p></td>
            <td class="tdb" colspan="3"><input type="password" name="i17" maxlength="13" value="A123456a" class="read"></td>
          </tr>          
          <tr style="display: none">
            <td class="tdar"><p> &nbsp;&nbsp;Tùy chọn người nhận đại diện<!-- 대표수신자여부 --></p></td>
            <td class="tdb" colspan=3><input type="checkbox" id="i08" name="i08" value="Y" <%=count==0?"checked onclick=\"message(0);\"":"onclick=\"message(1);\""%>><label for="i08"> Là người nhận đại diện văn bản điện tử<!-- 전자문서 대표수신지가 됩니다 -->.</label></td>
          </tr>
<%    if (!oper.equals("x")) { %>
          <tr style="display: none;">
            <td class="tdar"><p>
            	Thông báo tiến hành xây lắp<!-- 공사진행통보서 --><br>
            	Tùy chọn gửi nhận<!-- 송수신 여부 --></p>
            </td>
            <td class="tdb" colspan=3><input type="checkbox" name="constChk" value=""><font color="red"  ><b>
			            ※ Khi check vào hạng mục thông báo tiến hành xây lắp thì không thể tham gia dự thầu <!-- 공사진행통보서 항목 체크시 투찰할 수 없습니다 -->.</b></font><br>
			            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue" >
			     Khi check thì không thể thực hiện thầu bằng Chứng thư số đã đăng kí và hầu hết các nghiệp vụ của nhà thầu
			            <!-- 체크시 등록된 인증서로는 투찰 및 조달업체업무 대부분을 사용할 수 없고 -->, <br>
			            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            chỉ có thể sử dụng trong nghiệp vụ gửi nhận thông báo tiến hành quản lý xây lắp trong nghiệp vụ của nhà thầu
            <!-- 조달업체업무의 공사관리진행통보서송수신 업무에만 사용할 수 있습니다-->.</font><br>
            </td><!-- 2007.07.06 문구변경 -->
          </tr>
<%    } %>
          <tr> 
            <td height="2" colspan="4" class="line"></td>
          </tr>
        </table>
        <!---------------------- 통보수단 선택 테이블 끝 ---------------------------->


        <br>
        <!---------------------- 조회목록 테이블 시작 ------------------------>
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr" style="display: none">
          <tr> 
            <td height="4" class="line"></td>
          </tr>          
		  <tr> 
            <td class="tdb">  
            	 <!--▷Nhập thông tin người sử dụng. Phải đăng kí Chứng thư số được cấp thành Chứng thư số người dùng trên Koneps
            	 이용자 정보를 입력하고, 발급받은 인증서를 나라장터 이용자 인증서로 등록합니다 . <br>-->
  				▷ Nhập mật khẩu người dùng không nên giống với mật khẩu Chứng thư số. Mật khẩu này sẽ được sử dụng để quản lý Chứng thư số (khi đăng ký bổ sung Chứng thư số).
  				<!--  비밀번호는 인증서의 비밀번호가 아닌 별도의 이용자 관리 비밀번호를 입력하여야 하며, 동 비밀번호는 인증서의 추가 등 향후 인증서 관리에 사용됩니다. <br> -->
  				<!-- ▷ Trường hợp cùng một cơ quan nhưng nhiều user  đăng kí nhiều Chứng thư số nếu chọn tùy chọn người nhận đại diện thì sẽ gửi dự toán (báo giá) đến Chứng thư số được chỉ định là người đại diện nhận
  				 동일 업체에 복수 이용자가 복수 인증서를 등록하여 있는 경우 대표수신자 여부를 선택하면, 견적요청서 발송 등에 있어 대표수신자로 지정된 인증서로 발송됩니다. -->
			</td>
          </tr>
          <tr> 
            <td height="2"  class="line"></td>
          </tr>		  
        </table>
        <!---------------------- 버튼 테이블 시작 ------------------------>
        
<p align="center">
			<input  type="button" class="commonbutton" value="Đăng ký" onclick="javascript: javascript: regist();">
           	<input  type="button" class="commonbutton" value="Làm lại"   onclick="javascript:if (confirm('Thông tin nhập sẽ bị xóa hết! Bạn có chắc chắn không?')==true) document.forms[0].reset();" >
           	<input  type="button" class="commonbutton" value="Quay lại" onclick="javascript: window.location ='UM_RAJ_ConuC010i.jsp';">
           	<!-- <a href="javascript:regist();"><img src="/img/bu_entry.gif" border="0" align="absmiddle"></a> 
              <a href="javascript:clear();"><img src="/img/bu_newset.gif" border="0" align="absmiddle"></a>
              <a href="http://muasamcong.mpi.gov.vn:8070" target="_top"><img src="/img/bu_cancel.gif" border="0" align="absmiddle"></a> -->
          
</p>
<!-- <OBJECT ID="SG_G2B" CLASSID="CLSID:C72FE09E-C288-4BC2-8427-49C739916F20"></OBJECT> -->

        <!----------------------  버튼 테이블 끝 ------------------------>
      
    </form>
     
       <div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
     </div>  
</body>
</html>
