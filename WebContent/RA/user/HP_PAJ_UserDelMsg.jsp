<%@ page language="java" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="user.*" %>
<%@ page import="g2b.sso.SSO" %>
<%@ page contentType="text/html;charset=euc-kr" %>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>

<%
	String gubun = (String)request.getAttribute("gubun");
	String title = new String();
	String msg = (String)request.getAttribute("msg");
	String pass =	(String)request.getAttribute("pass");

	if (gubun.equals("c_insert")) 	{
		title = "비축물자이용자등록";
	} else if (gubun.equals("c_modify") || gubun.equals("p_modify")) {
		title = "비축물자이용자정보 수정";
	}else if (gubun.equals("e_insert")) {
		title = "일반이용자등록";
	} else if (gubun.equals("e_modify") || gubun.equals("m_modify")) {
		title = "이용자정보수정";
	} else if (gubun.equals("l_delete")) {
		title = "이용자 삭제";
	} else if (gubun.equals("namecheck")) {
		title = "실명인증";
	} else if (gubun.equals("f_find")) {
		title = "아이디/비밀번호 찾기";
	} else if (gubun.equals("g_insert")) {
		title = "공공기관담당자등록";
	}
%>

<html>
<head>
<title><%=title%></title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">

<link rel="stylesheet" href="/css/EP.css" type="text/css">
<link rel="stylesheet" href="/css/button.css" type="text/css">
<script language="JavaScript">
<!--
	function movepage() {
		if ( opener == null ) history.go(-1);
		else self.close();
	}
//-->
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" topmargin="0" leftmargin="0" background="/img/bg01.gif">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" background="/img/bg_sub.gif">
		<tr valign="bottom" height="57">
			<td rowspan="2" valign="top"><img src="/img/sub_title_01.jpg" width="35" height="64"></td>
			<td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE">&nbsp;&nbsp; <%=title%></td>
		</tr>
		<tr height="7">
			<td colspan="2" valign="top"><img src="/img/sub_title_03.jpg" width="788" height="7"></td>
		</tr>
		<tr height="21">
			<td colspan="2"></td>
		</tr>
	</table>
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
							<a href="#" onclick="javascript:movepage()"><img border="0" src="/img/bt_ok.gif" width="56" height="20"></a>
<%
	} else {
%>
							<a href=<%=pass%>><img border="0" src="/img/bt_ok.gif" width="56" height="20"></a>
<%
	}
%>
						</td>
					</tr>
				</table>
				<br>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="50">
			</td>
		</tr>
	</table>
</body>
</html>

