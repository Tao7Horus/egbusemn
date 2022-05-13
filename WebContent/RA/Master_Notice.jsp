<%--
	프로그램 ID : Master_Notice.jsp

	운영자(ID/PASS) 로그인 DB로 변경처리에 따른 공지사항
	
	2008.01.10	신경운	1. 변경된 내용과 이후 처리방향 공지
--%>
<%@page language="java" %>
<%@page contentType="text/html; charset=EUC-KR" %>
<%@page import="java.io.*, java.util.*" %>

<%@ include file = "/jsp/common/fnUmCommon.jsp" %>
<%
    try {

%>
<html>
<head>
<title>로그인 DB로 변경처리에 따른 공지사항</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" type="text/css" href="/css/UM.css">
<link rel="stylesheet" type="text/css" href="http://www.g2b.go.kr/new_main/html/css/popup.css">
<script type="text/javascript" src="http://www.g2b.go.kr/new_main/html/js/popup.js"></script>
</script>
</head>
<body>


<table border=0 cellspacing=0 cellpadding=0 height=100% width=100%>
<tr valign=bottom height=57>
<td colspan=3>

<table width=100% cellpadding=0 cellspacing=0 border=0 bgcolor=white>
<tr height=51 valign=bottom>
<td rowspan=2 width=30></td>
<td width=5><img src='/img/pop_tit_dot.gif'></td>
<td width=* class='HEADLINE' background='/img/pop_tit_t.jpg' style='background-position:right; background-repeat:no-repeat;'>&nbsp; 로그인 DB로 변경처리에 따른 공지사항</td>
</tr><tr height=6>
<td></td>
<td background='/img/pop_tit_b.jpg' style='background-position:right; background-repeat:no-repeat;'></td>
</tr>
</table>

</td></tr>
<tr height=1><td colspan=3 background='/img/pop_tit_bg.gif'></td></tr>
<tr height=25><td colspan=3></td>
</tr><tr valign=top>
<td width=30></td><td width=*>
<center>
<table width="90%" border="0" cellspacing="0" cellpadding="0">
  <tr>
	<td></td>
	<td>
		<b><font color="red">
		운영자(ID/PASS) 로그인 권한처리가 DB작업으로 변경되었습니다.</font></b>  <br><br>
		<font color="496882">
		 로그인 후 사번에 해당하는 좌측메뉴가 프로그램에서 처리 되던 부분이 <br>
		 DB처리로 변경되었습니다.(수시 변경 가능) <br>
			로그인 이후 <br>
		 기존과 다른 좌측메뉴가 보이거나 추가로 권한을 요청하실 경우 <br>
		 ITSM 에 요청하시거나 042-478-4427 로 연락주시면 바로 처리해 드리겠습니다. <br>
		</font><br>
	</td>
  </tr>
</table>
</center>
</td><td width=28></td></tr>
<tr height=10><td colspan=3></td></tr>
<tr height=*><td colspan=3></td></tr>
<tr><td colspan=3 height=45 width=*>
		<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
		  <tr>
			<td>
				<div align=center>
				<a href="JavaScript:window.close();"><img src='/img/bu_close.gif' align='absmiddle' border=0></a>
				</div>
			</td>
        </table>

<table width=100% cellpadding=0 cellspacing=0 border=0 background='/img/pop_bottom_back.gif' height=45>
	<tr>
		<td background='/img/pop_bottom.gif' style='background-position:right; background-repeat:no-repeat;'></td>
	</tr>
</table>

</td></tr></table>


</body></html>

<%
} catch(Exception e){
	out.println("CertNotice.jsp error:"+e);
}
%>
