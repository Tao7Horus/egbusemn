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
	sso.checkLogin();
	if(!sso.isLogin()){
		return;
	}

	String refid = sso.getID();
	String gubun = sso.getGubun();

	if(gubun==null || !gubun.equals("g")){
		//throw new Exception("프로그램 사용 권한이 없습니다.");
		throw new Exception("Không có quyền sử dụng chương trình.");
	}
%>

<%
	String personid = request.getParameter("personid");
	HP_PAE_UserInfo userinfo = new HP_PAB_UserSB().getUserEB(personid);
%>
<html>
<head>
<!-- 수요기관 담당자 선택 -->
<title>Chọn người đại diện chủ đầu tư</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/TA.css" type="text/css">
<script language="JavaScript" src="../../js/personinfo.js"></script>
<script language="JavaScript">
function f_select() {
    savePersonInfo(document.info);
    self.close();
	opener.window.location.reload();	// 수요기관담당자 해지기능 개선 2007-11-22
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" topmargin="0" leftmargin="0" background="../../img/bg01.gif">
<table width="350" border="0" cellpadding="0" cellspacing="0" height="100%"  background="../../img/bg_sub.gif">
<tr valign="top">
  <td width="100%" align=center>
    <table width="95%" cellpadding="2" cellspacing="2">
    <tr>
      <td height="1" class="line"></td>
    </tr>
    <tr> 
      <td class="tdb" align="left">
        <!-- 님을 선택하셨습니다 -->ID <%=userinfo.getUsername()%> <!-- 담당자 --> đã chọn.<br>
        <!-- 선택하신 정보는 PC에 저장되며, 이후 결재자정보에 지속적으로 반영됩니다 -->Thông tin chọn đã được lưu vào PC, thông tin người xác nhận sẽ được tiếp tục.<br>
        <br>
        <!-- 선택한 담당자 정보를 저장하시겠습니까 -->Bạn có lưu thông tin của người đại diện đã lựa chọn không?
      </td>
    </tr>
    <tr>
      <td height="1" class="line"></td>
    </tr>
    <tr>
      <td height="10"></td>
    </tr>
    <tr> 
      <td class="tdb" align="center">
        <br>
        <a href="#" onclick="javascript:f_select()"><img src="/img/bu_confirm.gif" width=56 height=20 border=0></a>
        <a href="#" onclick="javascript:self.close()"><img src="/img/bu_cancel.gif" width=56 height=20 border=0></a>
      </td>
    </tr>
    </table>
  </td>
</tr>
</table>
<form name=info>
	<input type=hidden name="personID" value="<%=userinfo.getUserid()%>">
	<input type=hidden name="personPW" value="<%=userinfo.getUserpasswd()%>">
	<input type=hidden name="personName" value="<%=userinfo.getUsername()%>">
	<input type=hidden name="personMail" value="<%=userinfo.getUsermail()%>">
	<input type=hidden name="partName" value="<%=userinfo.getPartname()%>">
	<input type=hidden name="position" value="<%=userinfo.getPosition()%>">
</form>
</body>
</html>
