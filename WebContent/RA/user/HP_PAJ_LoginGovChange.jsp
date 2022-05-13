<%--
/******************************************************************************/
/*                                                                            */
/*  프로그램 ID    :   HP_PAJ_LoginGovChange.jsp                              */
/*                                                                            */
/*  프로그램 설명  :   공공기관담당자정보수정화면                             */
/*                                                                            */
/******************************************************************************/
--%>

<%@ page language="java" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="user.*" %>
<%@ page import="beans.*" %>
<%@ page import="entity.*" %>
<%@ page import="g2b.sso.*" %>
<%@ page import="secu.lib.*" %>

<%@ include file = "/jsp/common/fnUmCommon.jsp" %>

<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />

<%@ page contentType="text/html; charset=EUC-KR" %>

<%
	HP_PAE_UserInfo govEB = new HP_PAE_UserInfo();
	govEB = (HP_PAE_UserInfo)request.getAttribute("govEB");
%>

<html>
<head>
<title>공공기관담당자수정</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" type="text/css" href="/css/UM.css">
<script language="javascript" src="/js/UM.js"></script>
<script language="javascript" src="/js/user.js"></script>
<script language="javascript" src="/js/govuser.js"></script>
<SCRIPT LANGUAGE="JavaScript">

	function f_init()
	{
		userInput.usertel1.value = "<%=govEB.getUsertel1()%>";
		userInput.userfax1.value = "<%=govEB.getUserfax1()%>";
		userInput.usermobile1.value = "<%=govEB.getUsermobile1()%>"
		userInput.preference.value = "<%=govEB.getPreference()%>"
	}

	//우편번호
	function f_FindPostNum()
	{
		newwin = window.open("/jsp/RA/user/HP_PAJ_SearchPostNm.jsp?GUBUN=1","PostCheck","width=550,height=550, scrollbars=no");
		newwin.focus();
	}

	// 비밀번호 재확인 입력
/*	function f_pwConfirm()
	{
		if (userInput.userpasswd.value == userInput.userpasswdc.value)
		{
			return true;
		} else {
			alert("비밀번호란과 비밀번호 확인란의 비밀번호가 서로 틀립니다!");
			userInput.userpasswd.value="";
			userInput.userpasswdc.value="";
			userInput.userpasswd.focus();
			return false;
		}
	}*/

	// 탈퇴/삭제
	function f_delete(chk)
	{
		var msg;
		if (chk == '1') {
			msg = "현재 보고 계신 이용자를 삭제 하시겠습니까?";
			document.userInput.gubun.value = "mod";
		} else {
			msg = "정말 탈퇴하시겠습니까?";
			document.userInput.gubun.value = "I";		
		}

		if (confirm(msg)) {
			document.userInput.action = '/servlet/user.HP_PAV_GovDel';
			document.userInput.method = "get";
			document.userInput.submit();
		}
		else 
			return false;
	}

	// 비밀번호 변경화면으로 이동
	function PasswordChange()
	{
		var userID = userInput.userid.value;
		location.href("http://www.g2b.go.kr:8070/jsp/RA/user/HP_PAJ_PasswordChange.jsp?type=LoginGovChange&userID="+userID+"");
	}

</SCRIPT>
</head>
<body background="/img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="f_init();">
<form method="post" name="userInput" action="/servlet/user.HP_PAV_LoginGovChange">
<input type=hidden name=gubun value=<%=request.getParameter("gubun")%>>
<!---------------------- 타이틀 테이블 시작 ------------------------>
  <table width="823" border="0" cellpadding="0" cellspacing="0" height="100%"  background="/img/bg_sub.gif">
    <tr valign="bottom" height="57">
      <td rowspan="2"><img src="/img/sub_title_01.jpg"></td>
      <td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE"> &nbsp; 공공기관담당자수정</td>
    </tr>
    <tr height="7"><td colspan="2"><img src="/img/sub_title_03.jpg"></td></tr>
    <tr height="21">
      <td colspan="3"></td>
    </tr>
    <tr valign="top">
      <td width="35"></td>
      <td width="760">
        <table width="100%" class="fontb">
          <tr>
            <td>[아이디정보]</td>
            <!--td align=right><font color=red>비밀번호는 영문자 및 숫자로 4자리 이상 12자리 이하로 입력하세요.</font></td-->
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
            <tr>
                <td height="4" colspan="6" class="line"></td>
            </tr>
          <tr>
            <td class="tdar" width="20%">아이디</td>
            <td class="tdb" width="80%"><%=govEB.getUserid()%>
				<input type="hidden" name="userid" size="15" maxlength="12" value="<%=govEB.getUserid()%>">
				<input type="hidden" name="idExist" value="1">
            </td>
            <!--td class="tdar" width="114">비밀번호</td>
            <td class="tdb" width="114"><input type="password" class="read"	name="userpasswd" size="15" value="<%=govEB.getUserpasswd()%>" maxlength=12></td>
            <td class="tdar" width="114">비밀번호확인</td>
            <td class="tdb" width="120"><input type="password" class="read"	name="userpasswdc" size="15" onBlur="f_pwConfirm()" value="<%=govEB.getUserpasswd()%>"  maxlength=12></td-->
          </tr>
        		<tr>
        			<td height="2" colspan="6" class="line"></td>
        		</tr>
        </table>
        <br>

		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tr">
			<tr>
				<td class="fontb">[담당자정보]</td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
			<tr>
				<td height="4" class="line" colspan="4"></td>
			</tr>
			<tr>
				<td class="tdar" width="20%"><span class="red">*</span>&nbsp;성명</td>
				<td class="tdb" width="30%"><%=govEB.getUsername()%>
					<input type="hidden" name="username" size="20" maxlength="20" value="<%=govEB.getUsername()%>">
				</td>
				<td class="tdar" width="20%">
					<span class="red">*</span>&nbsp;주민등록번호
				</td>
				<td class="tdb" width="30%">
					<%  String reg1 = govEB.getRegistno1();
					       String reg2 = govEB.getRegistno2();
						   String regTotal = reg1+reg2; 
					%>
                     <%=insert_minus_jumin_star(regTotal) %>
					<input type="hidden" name="registno1" size="8" maxlength="6" value="<%=govEB.getRegistno1()%>">
					<input type="hidden" name="registno2" size="10" maxlength="7" value="<%=govEB.getRegistno2()%>">
				</td>
			</tr>
			<tr>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;전화번호</td>
				<td class="tdb"	width="30%"><%=List.getCode("TFH", "usertel1", "GUL", "")%>
					<!--select name="usertel1">
						<option value="02" selected>02</option>
						<option value="031">031</option>
						<option value="032">032</option>
						<option value="033">033</option>
						<option value="041">041</option>
						<option value="042">042</option>
						<option value="043">043</option>
						<option value="051">051</option>
						<option value="052">052</option>
						<option value="053">053</option>
						<option value="054">054</option>
						<option value="055">055</option>
						<option value="061">061</option>
						<option value="062">062</option>
						<option value="063">063</option>
						<option value="064">064</option>
					</select-->
					-&nbsp;
					<input class="read" name="usertel2" maxlength="4" value="<%=govEB.getUsertel2()%>" size="6" maxlength="4">
					-&nbsp;
					<input class="read" name="usertel3" maxlength="4" value="<%=govEB.getUsertel3()%>" size="6" maxlength="4">
				</td>
				<td class="tdar" width="20%"><span class="red">*</span>&nbsp;팩스번호</td>
				<td class="tdb" width="30%"><%=List.getCode("TFH", "userfax1", "GUM", "")%>
					<!--select name="userfax1">
						<option value="02" selected>02</option>
						<option value="031">031</option>
						<option value="032">032</option>
						<option value="033">033</option>
						<option value="041">041</option>
						<option value="042">042</option>
						<option value="043">043</option>
						<option value="051">051</option>
						<option value="052">052</option>
						<option value="053">053</option>
						<option value="054">054</option>
						<option value="055">055</option>
						<option value="061">061</option>
						<option value="062">062</option>
						<option value="063">063</option>
						<option value="064">064</option>
					</select-->
					-&nbsp;
					<input class="read" name="userfax2" maxlength="4" value="<%=govEB.getUserfax2()%>" size="6" maxlength="4">
					-&nbsp;
					<input class="read" name="userfax3" maxlength="4" value="<%=govEB.getUserfax3()%>" size="6" maxlength="4">
				</td>
			</tr>
			<tr>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;핸드폰번호</td>
				<td class="tdb"	width="30%"><%=List.getCode("TFH", "usermobile1", "GUN", "")%>
					<!--select name="usermobile1">
						<option value="010" selected>010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select-->
					-&nbsp;
					<input class="read" name="usermobile2" maxlength="4" value="<%=govEB.getUsermobile2()%>" size="6">
					-&nbsp;
					<input class="read" name="usermobile3" maxlength="4" value="<%=govEB.getUsermobile3()%>" size="6">
				</td>
				<td class="tdar"	width="20%"	height="23"><span class="red">*</span>&nbsp;우편번호</td>
				<td class="tdb"	width="30%"	height="23">
					<input class="readonly" name="postno1" size="5" value="<%=govEB.getPostno1()%>" maxlength="3" ReadOnly>
					&nbsp;-&nbsp;
					<input class="readonly" name="postno2" size="5" value="<%=govEB.getPostno2()%>" maxlength="3" ReadOnly>
					<a href="JavaScript:f_FindPostNum()">
						<img src = "/html/ra/user/img/bt_search.gif" align = "absmiddle" border=0 width="56" height="20"></a>
				</td>
			</tr>
			<tr>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;주소</td>
				<td class="tdb"	colspan="3">
					<input class="readonly" name="address1"	size="45" value="<%=govEB.getAddress1()%>" maxlength="25" ReadOnly>
					<input class="read" name="address2"	size="45" value="<%=govEB.getAddress2()%>" maxlength="25">
				</td>
			</tr>
			<tr>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;메일주소</td>
				<td class="tdb" colspan="3">
					<input class="read" name="usermail" maxlength="35" size="35" value="<%=govEB.getUsermail()%>">
				</td>
			</tr>
			<tr>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;부서명</td>
				<td class="tdb"	width="30%">
					<input class="read" name="partname" maxlength="8" value="<%=govEB.getPartname()%>">
				</td>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;직책</td>
				<td class="tdb"	width="30%">
					<input class="read" name="position" maxlength="30" value="<%=govEB.getPosition()%>">
				</td>
			</tr>
			<tr>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;선호채널</td>
				<td class="tdb" colspan="3">
				    <select name="preference">
				    	<option value="EMAIL" selected>EMAIL</option>
				    	<option value="PDA">PDA</option>
				    	<option value="SMS">SMS</option>
				    	<option value="FAX">FAX</option>
				    	<option value="ETC">없음</option>
				    </select>
				</td>
			</tr>
			<tr>
				<td class="tdar"	width="20%">&nbsp;등록일자</td>
				<td class="tdb"	width="30%">
					<%=govEB.getRegistdate1()%>
				</td>
				<td class="tdar"	width="20%">&nbsp;변경일자</td>
				<td class="tdb"	width="30%">
					<%=govEB.getModifydate()%>
				</td>
			</tr>			
			<tr>
				<td height="2"  class="line" colspan="4"></td>
			</tr>
		</table>

					<br>
					<table width="100%" cellpadding="2" cellspacing="2">
						<tr>
<%
	if (request.getParameter("gubun") != null && request.getParameter("gubun").equals("mod"))	
	{
%>
							<td align="center"> 
								<img src="/img/bu_change.gif" align="absmiddle" onClick="f_submit()" width="56" height="20" style="cursor:hand">
								<img src="/img/bu_delete.gif" align="absmiddle" onClick="f_delete('1')" width="56" height="20" style="cursor:hand">
								<img src="/img/bu_close.gif" align="absmiddle" onClick="JavaScript:window.close();" width="56" height="20" style="cursor:hand">
							</td>
							<input type="hidden" name="gubun" value="<%=request.getParameter("gubun")%>">
<%
	} else {
%>
							<td align="center"> 
								<img src="/img/bu_change.gif" align="absmiddle" onClick="f_submit()" width="56" height="20" style="cursor:hand">
								<img src="/img/bt_secession.gif" align="absmiddle" onClick="f_delete('2')" width="56" height="20" style="cursor:hand">
								<img src="/img/bu_reset.gif" align="absmiddle" onClick="javascript:location.reload();" width="56" height="20" style="cursor:hand">
								<img src="/img/passwordchange.gif" align="absmiddle" onClick="javascript:PasswordChange();"style="cursor:hand">
							</td>
							<input type="hidden" name="gubun" value="">
<%
	}
%>
						</tr>
					</table>
				

</form>
<OBJECT ID="SG_G2B" CLASSID="CLSID:C72FE09E-C288-4BC2-8427-49C739916F20" width="0" height="0"></OBJECT>
      </td>
      <td width="28"></td>
    </tr>
    <tr height="35"><td colspan="3"></td></tr>
    <tr height="*"><td colspan="3"></td></tr>
    <tr>
      <td colspan="3" height="50"><%@ include file="/jsp/common/Footer.jsp" %></td>
    </tr>
  </table>
</body>
</html>
