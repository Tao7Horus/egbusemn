<%--
/******************************************************************************/
/*                                                                            */
/*  프로그램 ID    :   HP_PAJ_PasswordChange.jsp                              */
/*                                                                            */
/*  프로그램 설명  :   비밀번호변경화면         // Màn hình thay đổi mật khẩu                              */
/*                                                                            */
/******************************************************************************/
/************************************************************************/
/***************************************************************************/
/* Program ID         : HP_PAJ_PasswordChange.jsp                        */
/* Program Explanation: Màn hình thay đổi mật khẩu                        */
/* Program Summary  :   
/* Relation Program   : */ 
/*                     */
/* Table              : )                 */
/***************************************************************************/
/* Customizing Composer : MR. MINH 24.06.2009                       */
/***************************************************************************/

--%>

<%@ page language="java" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="user.*" %>
<%@ page import="g2b.sso.*" %>
<%@ include file = "../../jsp/common/fnUmCommon.jsp" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<%
	SSO sso=new SSO(pageContext);
	sso.checkLogin3();
	if(!sso.isLogin3()){
		return;
	}

	String type   = "";
	String userID = "";

	type   = request.getParameter("type")  == null?"":request.getParameter("type");
	userID = request.getParameter("userID")== null?"":request.getParameter("userID");
	
	if (!type.equals("GovChange"))
	{
		userID = sso.getPersonID();
	}

	if (userID.equals("") || userID == null) {
		
		//throw new Exception("로그인 아이디 정보가 없습니다.<br>다시 로그인 후 사용해 주십시오.");
		throw new Exception("Không có thông tin ID login.<br>Hãy login lại rồi sử dụng sau.");
	}
%>

<html>
<head>
<!-- 비밀번호변경  --> 
<title> Thay đổi mật khẩu</title>
<link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../../css/TA.css">
<script language="javascript" src="../../js/user.js"></script>
<script language="JavaScript">

	// 초기화                     Khởi tạo
	function f_init()
	{
		userInput.userpasswd.focus();
	}

	// 비밀번호 재확인 입력  / Nhập lại mật khẩu
	function f_PasswordChange()
	{
		if (userInput.userpasswd.value == "")
		{
			//alert("현재 비밀번호를 입력하세요.");
			alert("Hãy nhập mật khẩu hiện dùng.");
			userInput.userpasswd.focus();
			return;
		}

		if (userInput.newuserpasswd.value == "")
		{
			//alert("현재 비밀번호를 입력하세요.");
			alert("Hãy nhập mật khẩu hiện dùng.");
			userInput.newuserpasswd.focus();
			return;
		}

		if (userInput.newuserpasswd.value.length < 4 || userInput.newuserpasswd.value.length > 12)
		{
			//alert("새 비밀번호를 4자리 이상 12자리 이하로 입력하세요.");
			alert("Hãy nhập mật khẩu mới từ 4 đến 12 ký tự.");
			userInput.newuserpasswd.value = "";
			userInput.newuserpasswd.focus();
			return;
		}

		if (userInput.newuserpasswdc.value == "")
		{
			//alert("새 비밀번호 확인을 입력하세요.");
			alert("Hãy nhập lại mật khẩu mới.");
			userInput.newuserpasswdc.focus();
			return;
		}

		if (userInput.newuserpasswd.value != userInput.newuserpasswdc.value)
		{
			//alert("새 비밀번호란과 새 비밀번호 확인란의 비밀번호가 서로 틀립니다.");
			alert("Mật khẩu mới và nhập lại mật khẩu mới không đồng nhất.");
			userInput.newuserpasswd.value = "";
			userInput.newuserpasswdc.value = "";
			userInput.newuserpasswd.focus();
			return false;
		}

		if (!f_checkID(userInput.newuserpasswd.value))
		{
			//alert("비밀번호는 영문자 및 숫자로 만드셔야 합니다.");
			alert("Mật khẩu phải là số và chữ cái tiếng Anh.");
			userInput.newuserpasswd.value = "";
			userInput.newuserpasswdc.value = "";
			userInput.newuserpasswd.focus();
			return;
		}

		//if (confirm("비밀번호를 변경하시겠습니까?"))
		if (confirm("Bạn có muốn thay đổi mật khẩu không?"))
		{
			document.userInput.action = "/servlet/user.HP_PAV_PasswordChange";
			document.userInput.method = "post";
			document.userInput.target = "_self";
			document.userInput.submit();
		}
	}

</script>

</head>
<body>
<form method="post" name="userInput">
<div class="col-750 clearfix last">
	<h1 class="pageTitle"><i class="icon-title"></i>Thay đổi mật khẩu</h1>

				<!---------------------- 조회조건 테이블 시작 ------------------------>

					<table width="100%" class="fontb">
						<tr>
							<td><!-- [ 비밀번호 변경 -->[Thay đổi mật khẩu] </td>
						</tr>
					</table>

					<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
						<tr>
							<td height="4" class="line" colspan="4"></td>
						</tr>
						<tr>
							<td class="tdar" width="20%"><!-- 현재 비밀번호 --> Mật khẩu hiện dùng</td>
							<td class="tdb"	width="80%">
								<input type="password" class="read" name="userpasswd" size="20" maxlength="12" value="">
							</td>
						</tr>
						<tr>
							<td class="tdar" width="20%"><!-- 새 비밀번호 --> Mật khẩu mới</td>
							<td class="tdb"	width="80%">
								<input type="password" class="read" name="newuserpasswd" size="20" maxlength="12" value="">
								<!-- 비밀번호는 영문자 및 숫자 4자리 이상 12자리 이하로 만드셔야 합니다 -->Mật khẩu phải bằng số và chữ cái tiếng Anh từ 4 đến 12 ký tự.
							</td>
						</tr>
						<tr>
							<td class="tdar" width="20%"><!-- 새 비밀번호 확인 -->Nhập lại mật khẩu</td>
							<td class="tdb"	width="80%">
								<input type="password" class="read" name="newuserpasswdc" size="20" maxlength="12" value="">
							</td>
						</tr>
						<tr>
							<td height="2"  class="line" colspan="4"></td>
						</tr>
					</table>
					<br>
					<table width="100%" cellpadding="2" cellspacing="2">
						<tr>
							<td align="center">
								<img src="/img/bu_change.gif" align="absmiddle" onClick="f_PasswordChange()" style="cursor:hand">
							</td>
						</tr>
					</table>
	
	
	<div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
	</div>

<input type="hidden" name="userID" value="<%=userID %>">
<input type="hidden" name="type" value="<%=type %>">
</form>
</table>
</body>
</html>
