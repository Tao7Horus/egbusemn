<%--
/******************************************************************************************/
/*                                                                                        */
/*    Program ID    :     HP_PAJ_IDOverlap.jsp                                            */
/*                                                                                        */
/*    description   :     비축물자이용자 아이디검색화면    								  */
/*                                                                                        */
/******************************************************************************************/
	2007.08.31	이광현	1.프로그램 정리 (미반환 JDBC 에 대한 개선)
/************************************************************************/
/***************************************************************************/
/* Program ID         : HP_PAJ_IDOverlap.jsp                        */
/* Program Explanation: Kiểm tra Id người dùng                      */
/* Program Summary  :   
/* Relation Program   : */ 
/*                     */
/* Table              : )                 */
/***************************************************************************/
/* Customizing Composer : MR. MINH 16.06.2009                       */
/***************************************************************************/

--%>

<%@ page language="java" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="user.*" %>
<%
	String userid = new String();
	String exist = new String();
	boolean isExistID = false;

	exist = request.getParameter("exist");			
	exist =request.getParameter("exist")==null?"0":request.getParameter("exist");		// 0 : 초기값(사용불가), 1 : 사용가능
	userid =request.getParameter("userid")==null?"":request.getParameter("userid");		// 검색하려는 아이디

	if (!userid.equals("")) {
		HP_PAB_UserSB userSB = new HP_PAB_UserSB();
		isExistID = userSB.isExistID(userid);
	}
%>
<html>
<head>
<!-- 아이디검색결과 -->
<title>Tìm kiếm tên người dùng </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../../css/TA.css">
<script language="javascript" src="../../js/UM.js"></script>
<script language="javascript" src="../../js/user.js"></script>
<script language="javascript" src="../../js/govuser.js"></script>
<SCRIPT LANGUAGE="JavaScript">
function f_idExist()
{
	if (idExist.userid.value == null || idExist.userid.value == "")
	{
		//alert("검색하실 아이디가 입력되어 있지 않습니다.");
		alert("Hãy nhập ID bạn muốn kiểm tra.");
		idExist.userid.focus();
		return false;
	}
	idExist.submit();
}

function f_confirm()
{
	var id;
	if (idExist.userid.value != null) id = idExist.userid.value;
	window.opener.userInput.userid.value = id;
	window.opener.userInput.idExist.value="1";
	window.close();
}

</SCRIPT>

<link rel="stylesheet" href="../../css/EP.css" type="text/css">
</head>
<body background=../../img/pop_bg.gif topmargin=0 leftmargin=0 rightmargin=0 style=background-position:left; background-repeat:repeat-y; bgcolor=white>
	<table border=0 cellspacing=0 cellpadding=0 height=350 width=450>
		<tr valign=bottom height=57>
			<td colspan=3>
				<table width="100%" cellpadding=0 cellspacing=0 border=0 bgcolor=white>
					<tr height="51" valign=bottom>
						<td rowspan=2 width=30></td>
						<td width=5><img src='../../img/pop_tit_dot.gif'></td>
						<td width=* class='HEADLINE' background='../../img/pop_tit_t.jpg' style='background-position:right; background-repeat:no-repeat;'>&nbsp;<!-- 아이디확인 -->Kiểm tra ID</td>
					</tr>
					<tr height=6>
						<td></td>
						<td background='../../img/pop_tit_b.jpg' style='background-position:right; background-repeat:no-repeat;'></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height=1><td colspan=3 background='../../img/pop_tit_bg.gif'></td></tr>
		<tr height=25><td colspan=3></td>
		</tr>
		<tr valign=top>
			<td width=30></td><td width=*>
			<form name="idExist" method="post" action="/RA/user/HP_PAJ_IDOverlap.jsp">
<%
	
	if (userid == null || userid.equals(""))
	{
		userid = "";
%>
				<table width="80%" align=center bordercolor="#CCCCCC" bgcolor="#E7E7E7">
					<tr>
						<td class="txt2"> 
							<div align="center"><!-- 사용하고자 하시는 아이디를 입력해 주세요 -->	Vui lòng nhập các ID bạn muốn sử dụng!</div>
							<p align="center"> <!-- 아이디검색 -->Tìm kiếm ID : 
							<input class="read" name="userid" size="15" maxlength="12"  onBlur="javascript:if(this.value != '') idcheck1();">
							<input type="hidden" name="exist" value="0">
							<img src = "../../img/bu_refer.gif" align = "absmiddle" style="cursor:hand" onClick="f_idExist()"> 
						</td>
					</tr>
				</table><br> 
<%
	} else {

		if (!isExistID)
		{
%>
				<table width="80%" align=center bordercolor="#CCCCCC" bgcolor="#E7E7E7">
					<tr>
						<td class="txt2"> 
							<div align="center"><%=userid%><b><!-- 아이디입니다 --> là ID<font color="#ff0033"> <!-- 사용하실 수 있는 --> bạn có thể sử dụng.</font></b> </div>
							<p align="center"> <!-- 아이디검색 -->Tìm kiếm ID : 
							<input class="read" name="userid" size="15" value="<%=userid%>" maxlength="12"  onBlur="javascript:if(this.value != '') idcheck1();">	
							<input type="hidden" name="exist" value="1">
							<img src = "../../img/bu_refer.gif" align = "absmiddle" style="cursor:hand" onClick="f_idExist()"> 
						</td>
					</tr>
				</table>
				<br> 
				<table width="80%" align="center">
					<tr> 
						<td align=center>
							<img src="../../img/bt_ok.gif" align="absmiddle"  width="56" height="20" onClick="f_confirm()" style="cursor:hand">
						</td>
					</tr>
				</table>
<%
		} else {
%>
				<table width="80%" align=center bordercolor="#CCCCCC" bgcolor="#E7E7E7">
					<tr>
						<td class="txt2"> 
							<div align="center"><%=userid%><b><!-- 아이디입니다 --> là ID<font color="#ff0033"><!-- 이미 사용중인 --> đã có người sử dụng.</font></b></div>
							<p align="center"> <!-- 아이디검색 -->Tìm kiếm ID : 
							<input class="read" name="userid" size="15" value="<%=userid%>" maxlength="12"  onBlur="javascript:if(this.value != '') idcheck1();">
							<input type="hidden" name="exist" value="0">
							<img src = "../../img/bu_refer.gif" align = "absmiddle" style="cursor:hand" onClick="f_idExist()"> 
						</td>
					</tr>
				</table><br> 
<%
		}
	}
%>						
				</form>
			</td>
			<td width=28></td>
		</tr>
		<tr height=10><td colspan=3></td></tr>
		<tr height="*"><td colspan=3></td></tr>
		<tr>
			<td colspan="3" height="45" width="*">
				<table width="100%" cellpadding=0 cellspacing=0 border=0 background='../../img/pop_bottom_back.gif' height=45>
					<tr>
						<td background='../../img/pop_bottom.gif' style='background-position:right; background-repeat:no-repeat;'></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
