<%--
/******************************************************************************/
/*                                                                            */
/*  프로그램 ID    :   HP_PAJ_GeneralChange.jsp                               */
/*                                                                            */
/*  프로그램 설명  :   일반이용자정보수정화면                                 */
/*                                                                            */
/******************************************************************************/
		2007.05.31	이광현	이용자부가서비스 변경신청 절차개선 (폼,js,처리부분 완전 변경)

--%>

<%@ page language="java" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="user.*" %>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>

<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />

<%@ page contentType="text/html;charset=euc-kr" %>

<%
	String gubun = request.getParameter("gubun") == null ? "" : request.getParameter("gubun");

	HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();

	userEB = (HP_PAE_UserInfo)request.getAttribute("userEB");
	String preference = (userEB.getPreference() != null) ? userEB.getPreference() : "";
%>

<html>
<head>
<title>일반이용자 정보 수정</title>
<link rel="stylesheet" type="text/css" href="/css/UM.css">
<script language="javascript" src="/js/user.js"></script>
<script language="javascript" src="/js/generaluser.js"></script>
<script language="JavaScript">

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

	// 우편번호 검색
	function f_FindPostNum()
	{
		url = "/jsp/RA/user/HP_PAJ_SearchPostNm.jsp?GUBUN=1";
		launchCenter(url, "우편번호검색", 550, 550);
	}

	// 초기화
	function f_init()
	{
		userInput.usertel1.value = "<%=userEB.getUsertel1()%>";
		userInput.userfax1.value = "<%=userEB.getUserfax1()%>";
		userInput.usermobile1.value = "<%=userEB.getUsermobile1()%>";
		userInput.job.value = "<%=userEB.getJob()%>";
	}

	// 탈퇴/삭제
	function f_delete(chk)
	{
		var msg;
		if (chk == 1)
			msg = "현재 보고 계신 이용자를 삭제 하시겠습니까?";
		else
			msg = "정말 탈퇴하시겠습니까?";

		if (confirm(msg)) {
			document.userInput.action = "/servlet/user.HP_PAV_UserDel";
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
		location.href("http://www.g2b.go.kr:8070/jsp/RA/user/HP_PAJ_PasswordChange.jsp?type=GeneralChange&userID="+userID+"");
	}

</script>

</head>
<body background="/img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="f_init()">
<form method="post" name="userInput" action="/servlet/user.HP_PAV_GeneralChange">
	<table width="823" border="0" cellpadding="0" cellspacing="0" height="100%"  background="/img/bg_sub.gif">
		<tr valign="bottom" height="57">
			<td rowspan="2"><img src="/img/sub_title_01.jpg"></td>
			<td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE"> &nbsp; 일반이용자 정보 수정</td>
		</tr>
		<tr height="7">
			<td colspan="2"><img src="/img/sub_title_03.jpg"></td>
		</tr>
		<tr height="21">
			<td colspan="3"></td>
		</tr>
		<tr valign="top">
			<td width="35"></td>
			<td width="760">
				<!---------------------- 조회조건 테이블 시작 ------------------------>

					<table width="100%" class="fontb">
						<tr>
							<td>[ 아이디정보 ]</td>
						</tr>
					</table>

					<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
						<tr>
							<td height="4" class="LINE" colspan="2"></td>
						</tr>
						<tr>
							<td class="tdar" width="20%"><span class="red">*</span>&nbsp;아이디</td>
							<td class="tdb">
								<%=userEB.getUserid()%>
								<input type="hidden" name="userid" value="<%=userEB.getUserid()%>">
							</td>
							<input type="hidden" name="idExist" value="1">
						</tr>
						<tr>
							<td height="2"  class="line" colspan="2"></td>
						</tr>
					</table>
					<br>
					<!--table width="100%" class="fontb">
						<tr>
							<td>[ 비밀번호정보 ]</td>
						</tr>
					</table>

					<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
						<tr>
							<td height="4" class="line" colspan="4"></td>
						</tr>
						<tr>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;비밀번호</td>
							<td class="tdb"	width="30%">
								<input type="password" class="read" name="userpasswd" size="15" maxlength="12" value="<%=userEB.getUserpasswd()%>">
							</td>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;비밀번호확인</td>
							<td class="tdb"	width="30%">
								<input type="password" class="read" name="userpasswdc"	size="15" maxlength="12" onBlur="f_pwConfirm()" value="<%=userEB.getUserpasswd()%>">
							</td>
						</tr>
						<tr>
							<td height="2"  class="line" colspan="4"></td>
						</tr>
						<tr>
							<td colspan="2"> 비밀번호는 12자리까지 입니다.</td>
						</tr>
					</table>
					<br-->

					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tr">
						<tr>
							<td class="fontb">[ 이용자정보 ]</td>
						</tr>
					</table>

					<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
						<tr>
							<td height="4" class="line" colspan="4"></td>
						</tr>
						<tr>
							<td class="tdar" width="20%"><span class="red">*</span>&nbsp;성명</td>
							<td class="tdb" width="30%"><%=userEB.getUsername()%></td>
							<input type="hidden" name="username" value="<%=userEB.getUsername()%>">
							<td class="tdar" width="20%"><span class="red">*</span>&nbsp;주민등록번호</td>
							<td class="tdb" width="30%"><%=userEB.getRegistno1()%>&nbsp;-&nbsp;<%=userEB.getRegistno2()%></td>
							<input  type="hidden" name="registno1" value="<%=userEB.getRegistno1()%>">
							<input type="hidden" name="registno2" value="<%=userEB.getRegistno2()%>">
						</tr>
						<tr>
							<td class="tdar"	width="20%">&nbsp;전화번호</td>
							<td class="tdb"	width="30%"><%=List.getCode("TFH", "usertel1", "GUL", "")%>
								<!--select name="usertel1">
									<option value="">선택</option>
									<option value="02">02</option>
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
								<input class="read" name="usertel2" maxlength="4" size="8" maxlength="4" value="<%=userEB.getUsertel2()%>" onKeyDown="f_onlyNumber()">
								-&nbsp;
								<input class="read" name="usertel3" maxlength="4" size="8" maxlength="4" value="<%=userEB.getUsertel3()%>" onKeyDown="f_onlyNumber()">
							</td>
							<td class="tdar" width="20%">&nbsp;팩스번호</td>
							<td class="tdb" width="30%"><%=List.getCode("TFH", "userfax1", "GUM", "")%>
								<!--select name="userfax1">
									<option value="">선택</option>
									<option value="02">02</option>
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
								<input class="read" name="userfax2" maxlength="4" size="8" maxlength="4" value="<%=userEB.getUserfax2()%>" onKeyDown="f_onlyNumber()">
								-&nbsp;
								<input class="read" name="userfax3" maxlength="4" size="8" maxlength="4" value="<%=userEB.getUserfax3()%>" onKeyDown="f_onlyNumber()">
							</td>
						</tr>
						<tr>
							<td class="tdar"	width="20%">&nbsp;핸드폰번호</td>
							<td class="tdb"		width="30%"><%=List.getCode("TFH", "usermobile1", "GUN", "")%>
								<!--select name="usermobile1">
								        <option value="">선택</option>
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
									<option value="018">018</option>
									<option value="019">019</option>
									<option value="0130">0130</option-->
								</select>
								-&nbsp;
								<input class="read" name="usermobile2" maxlength="4" size="8" value="<%=userEB.getUsermobile2()%>" onKeyDown="f_onlyNumber()">
								-&nbsp;
								<input class="read" name="usermobile3" maxlength="4" size="8" value="<%=userEB.getUsermobile3()%>" onKeyDown="f_onlyNumber()">
							</td>
							<td class="tdar"	width="20%"	height="23"><span class="red">*</span>&nbsp;우편번호</td>
							<td class="tdb"	width="30%"	height="23">
								<input class="readonly" name="postno1" size="5" value="<%=userEB.getPostno1()%>" maxlength="3" ReadOnly>
								&nbsp;-&nbsp;
								<input class="readonly" name="postno2" size="5" value="<%=userEB.getPostno2()%>" maxlength="3" ReadOnly>
								<a href="JavaScript:f_FindPostNum()">
									<img src = "/html/ra/user/img/bt_search.gif" align = "absmiddle" border=0 width="56" height="20">
								</a>
							</td>
						</tr>
						<tr>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;주소</td>
							<td class="tdb"	colspan="3">
								<input class="readonly" name="address1"	size="45" value="<%=userEB.getAddress1()%>" maxlength="25" ReadOnly>
								<input class="read" name="address2"	size="45" value="<%=userEB.getAddress2()%>" maxlength="25">
							</td>
						</tr>
						<tr>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;메일주소</td>
							<td class="tdb">
								<input class="read" name="usermail" maxlength="35" size="35" value="<%=userEB.getUsermail()%>">
							</td>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;선호채널</td>
							<td class="tdb">
								<select name="preference">
									<option value="EMAIL"<%=preference.equals("EMAIL")?" selected":"" %>>EMAIL</option>
									<option value="PDA"<%=preference.equals("PDA")?" selected":"" %>>PDA</option>
									<option value="SMS"<%=preference.equals("SMS")?" selected":"" %>>SMS</option>
									<option value="FAX"<%=preference.equals("FAX")?" selected":"" %>>FAX</option>
									<option value="ETC"<%=preference.equals("ETC")?" selected":"" %>>없음</option>
								</select>
							</td>
						</tr>

						<tr>
							<td class="tdar"	width="20%">&nbsp;직업</td>
							<td class="tdb" colspan="3">
								<select name="job">
								        <option value="">선택</option>
									<option value="0">회사원</option>
									<option value="1">공무원</option>
									<option value="2">자영업</option>
									<option value="3">전문직</option>
									<option value="4">학생</option>
									<option value="5">주부</option>
									<option value="6">군인</option>
									<option value="7">무직</option>
									<option value="8">기타</option>
								</select>
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
	if (!gubun.equals("") && gubun.equals("mod"))
	{
%>
							<td align="center">
								<img src="/img/bu_change.gif" align="absmiddle" onClick="f_submit()" width="56" height="20" style="cursor:hand">
								<img src="/img/bu_delete.gif" align="absmiddle" onClick="f_delete(1)" width="56" height="20" style="cursor:hand">
								<img src="/img/bu_close.gif" align="absmiddle" onClick="javascript:window.close();" width="56" height="20" style="cursor:hand">
							</td>
							<input type="hidden" name="gubun" value="<%=gubun%>">
<%
	} else {
%>
							<td align="center">
								<img src="/img/bu_change.gif" align="absmiddle" onClick="f_submit()" width="56" height="20" style="cursor:hand">
								<img src="/img/bt_secession.gif" align="absmiddle" onClick="f_delete(2)" width="56" height="20" style="cursor:hand">
								<img src="/img/bu_reset.gif" align="absmiddle" onClick="javascript:location.reload();" width="56" height="20" style="cursor:hand">
								<img src="/img/passwordchange.gif" align="absmiddle" onClick="javascript:PasswordChange();"style="cursor:hand">
							</td>
							<input type="hidden" name="gubun" value="">
<%
	}
%>
						</tr>
					</table>
				<br>

			</td>
			<td width="28"></td>
		</tr>
		<tr height="35"><td colspan="3">&nbsp;</td></tr>
		<tr height="*"><td colspan="3">&nbsp;</td></tr>
		<tr>
			<td colspan="3" height="50">
				<%@ include file="/jsp/common/Footer.jsp" %>
			</td>
		</tr>
</form>
	</table>
</body>
</html>
