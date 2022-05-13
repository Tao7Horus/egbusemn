<%@ page language="java" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="user.*" %>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>

<%@ page contentType="text/html;charset=euc-kr" %>
<%
	String title = "";
	String path = "/html/ra/user/";
	String filename = "";

	// 구분자(gubun)			1 : 일반이용자		2 : 비축물자이용자
	String gubun = request.getParameter("gubun")==null? "" : request.getParameter("gubun");

	if (gubun.equals("1")) {
		title = "일반이용자 이용약관";
		filename = "gUseAgree.html";
	} else {
		title = "비축물자이용자 이용약관";
		filename = "eUseAgree.html";
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title><%=title%></title>
<link rel="stylesheet" type="text/css" href="/css/UM.css">
</head>

<body background="/img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<table width="823" border="0" cellpadding="0" cellspacing="0" height="100%"  background="/img/bg_sub.gif">
		<tr valign="bottom" height="57">
			<td rowspan="2"><img src="/img/sub_title_01.jpg"></td>
			<td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE"> &nbsp; <%=title%></td>
		</tr>
		<tr height="7">
			<td colspan="2"><img src="/img/sub_title_03.jpg"></td>
		</tr>
		<tr height="21">
			<td colspan="3"></td>
		</tr>
		<tr valign="top">
			<td width="35"></td>
			<td width="760" align="center" height="360">
			<!---------------------- 약관을 보여주는 테이블 ------------------------>
				<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
					<tr>
						<td align="center" width="100%">
							<iframe name="body" width="100%" height="100%" align="right" src="<%=path + filename%>" style="border-collapse:collapse" frameborder="0" scrolling="auto"></iframe>
						</td>
					</tr>
				</table>
			</td>
			<td width="28"></td>
		</tr>
		<tr>
			<td width="35" height="30"></td>
			<td align="center"> <a href="/jsp/RA/user/HP_PAJ_NameCheck.jsp?gubun=<%=gubun%>"><img src="/img/bu_accept.gif" border="0" align="absmiddle" width="60" height="22"></a>&nbsp;&nbsp;
			<a href="javascript:history.back(1);" onfocus="blur()"><img src="/img/bu_not_accept.gif" border="0" align="absmiddle" width="72" height="22"></a></td>
			<td width="28"></td>
		</tr>
		<tr height="35"><td colspan="3"></td></tr>
		<tr height="*"><td colspan="3"></td></tr>
		<tr>
			<td colspan="3" height="50">
				<%@ include file="/jsp/common/Footer.jsp" %>
			</td>
		</tr>
	</table>
</body>
</html>
