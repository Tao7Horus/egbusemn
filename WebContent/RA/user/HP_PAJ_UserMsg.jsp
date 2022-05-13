<%--
/******************************************************************************/
/*																													 */
/*  프로그램 ID    :   HP_PAJ_UserMsg.jsp														 */
/*																													 */
/*  프로그램 설명  :   실명인증분기화면															 */
/*																													 */
/******************************************************************************/
/*	  수  정	 20070628  신 경 운  실명인증 메세지 세분화 작업 및 프로그램 정리  	 */
/******************************************************************************/
/***************************************************************************/
/* Program ID         : HP_PAJ_UserMsg.jsp	                        */
/* Program Explanation: Hiển thị thông báo kết quả thêm người dùng                     */
/* Program Summary  :   
/* Relation Program   : */ 
/*                     */
/* Table              :                  */
/***************************************************************************/
/* Customizing Composer : MR. MINH 17.06.2009                       */
/***************************************************************************/

--%>
<%@ page language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="user.*" %>
<%@ page import="g2b.sso.SSO" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file = "../../jsp/common/fnUmCommon.jsp" %>

<%
	String gubun = (String)request.getAttribute("gubun");
	String title = new String();
	String msg = (String)request.getAttribute("msg");
	String pass =	(String)request.getAttribute("pass");

	if (gubun.equals("c_insert")) 	{
		//title = "비축물자이용자등록";
		title = "Đăng ký người dùng hàng hóa dự phòng";
	} else if (gubun.equals("c_modify") || gubun.equals("p_modify")) {
		//title = "비축물자이용자정보 수정";
		title = "Sửa thông tin người dùng hàng hóa dự phòng";
	}else if (gubun.equals("e_insert")) {
		//title = "일반이용자등록";
		title = "Đăng ký người dùng";
	} else if (gubun.equals("e_modify") || gubun.equals("m_modify")) {
		//title = "이용자정보수정";
		title = "Sửa thông tin người dùng";
	} else if (gubun.equals("l_delete")) {
		//title = "이용자 삭제";
		title = "Xóa người dùng";
	} else if (gubun.equals("namecheck")) {
		//title = "실명인증";
		title = "Chứng thực nhân thân";
	} else if (gubun.equals("f_find")) {
		//title = "아이디/비밀번호 찾기";
		title = "Tìm kiếm ID/Pass";
	} else if (gubun.equals("g_insert")) {
		//title = "공공기관담당자등록";
		title = "Đăng ký người đại diện";
	} else if (gubun.equals("g_modify")) {
		//title = "공공기관담당자수정";
		title = "Sửa thông tin người đại diện";
	} else if(gubun.equals("g_delete")) {
		//title = "공공기관담당자해지";
		title = "Xóa người đại diện";
	} else if(gubun.equals("pw_change")) {
		//title = "비밀번호 변경";
		title = "Thay đổi mật khẩu";
	}
%>

<html>
<head>
<title><%=title%></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="../../css/TA.css" type="text/css">
<link rel="stylesheet" href="../../css/button.css" type="text/css">
<script language="JavaScript">
<!--
	function movepage() {
		alert("opener" + opener);
		if ( opener == null ) history.go(-1);
		else self.close();
	}
//-->
</script>
</head>

<body>
<div class="col-750 clearfix last">
	<h1 class="pageTitle"><i class="icon-title"></i><%=title%></h1>

	
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td align="center"> <br>
				<table width="350" cellpadding="0" cellspacing="0" border="1" bordercolor="#CCCCCC">
					<tr>
						<td class="fontp" bgcolor="#EAEAEA">
							<div align="center">
								<br>
								<%=msg%>
								<br>
							</div>
						</td>
					</tr>
				</table>
				<br>
				<table width="100%" cellpadding="2" cellspacing="2">
					<tr>
						<td align="center">
<%
	if (gubun.equals("m_modify") || gubun.equals("p_modify")) {
%>
							<a href="#" onclick="javascript:movepage()"><img border="0" src="../../img/bt_ok.gif" width="56" height="20"></a>
<%
	} else {
%>
							<a href=<%=pass%>><img border="0" src="../../img/bt_ok.gif" width="56" height="20"></a>
<%
	}
%>
						</td>
					</tr>
				</table>
			</div>
</body>
</html>

