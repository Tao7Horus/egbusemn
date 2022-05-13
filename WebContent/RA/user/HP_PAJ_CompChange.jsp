<%--
/******************************************************************************/
/*                                                                            */
/*  프로그램 ID    :   HP_PAJ_CompChange.jsp                                  */
/*                                                                            */
/*  프로그램 설명  :   비축업체이용자정보수정화면                             */
/*                                                                            */
/******************************************************************************/
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
	HP_PAE_UserInfo compEB = new HP_PAE_UserInfo();

	compEB = (HP_PAE_UserInfo)request.getAttribute("compEB");
%>

<html>
<head>
<title>비축업체이용자 정보수정</title>
<link rel="stylesheet" type="text/css" href="/css/UM.css">
<script language="javascript" src="/js/user.js"></script>
<script language="javascript" src="/js/compuser.js"></script>
<script language="JavaScript">

	function f_searchcorp()
	{
		url="/jsp/RA/user/HP_PAJ_SearchCorpInfo.jsp?gubun=";
		launchCenter(url, "업체정보검색", 350, 450);
	}

	// 우편번호 검색
	function f_FindPostNum()
	{
		url = "/jsp/RA/user/HP_PAJ_SearchPostNm.jsp?GUBUN=1";
		launchCenter(url, "우편번호검색", 550, 550);
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

	// 초기화
	function f_init()
	{
		userInput.usertel1.value = "<%=compEB.getUsertel1()%>";
		userInput.userfax1.value = "<%=compEB.getUserfax1()%>";
		userInput.usermobile1.value = "<%=compEB.getUsermobile1()%>";
		userInput.corptel1.value = "<%=compEB.getCorptel1()%>";
		userInput.preference.value = "<%=compEB.getPreference()%>";
		userInput.div1.value = "<%=compEB.getDiv1()%>";
		userInput.div2.value = "<%=compEB.getDiv2()%>";
		userInput.div3.value = "<%=compEB.getDiv3()%>";
		userInput.div4.value = "<%=compEB.getDiv4()%>";
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
		location.href("http://www.g2b.go.kr:8070/jsp/RA/user/HP_PAJ_PasswordChange.jsp?type=CompChange&userID="+userID+"");
	}

</script>

</head>
<body background="/img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onLoad="f_init()">
	<form method="post" name="userInput" action="/servlet/user.HP_PAV_CompChange">
	<table width="823" border="0" cellpadding="0" cellspacing="0" height="100%"  background="/img/bg_sub.gif">
		<tr valign="bottom" height="57"> 
			<td rowspan="2"><img src="/img/sub_title_01.jpg"></td>
			<td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE"> &nbsp; 비축업체이용자 정보수정</td>
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
								<input class="readonly" name="userid" size="15" maxlength="12" value="<%=compEB.getUserid()%>" ReadOnly>
							</td>
							<input type="hidden" name="idExist" value="1">
							<input type="hidden" name="refid" value="refid">
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
								<input type="password" class="read" name="userpasswd" size="15" maxlength="12" value="<%=compEB.getUserpasswd()%>">
							</td>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;비밀번호확인</td>
							<td class="tdb"	width="30%">
								<input type="password" class="read" name="userpasswdc"	size="15" maxlength="12" onBlur="f_pwConfirm()" value="<%=compEB.getUserpasswd()%>">
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
							<td class="tdb" width="30%"><input type="text" class="readonly" name="username" size="20"  maxlength="20" value="<%=compEB.getUsername()%>" ReadOnly></td>
							<td class="tdar" width="20%"><span class="red">*</span>&nbsp;주민등록번호</td>
							<td class="tdb" width="30%">
	 					    <% String reg1 = compEB.getRegistno1();
							      String reg2 = compEB.getRegistno2();
								  String regTotal = reg1 + reg2 ;%>
                            <%=insert_minus_jumin_star(regTotal)%>
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
								<input class="read" name="usertel2" maxlength="4" value="<%=compEB.getUsertel2()%>" size="6">
								-&nbsp;
								<input class="read" name="usertel3" maxlength="4" value="<%=compEB.getUsertel3()%>" size="6">
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
								<input class="read" name="userfax2" maxlength="4" value="<%=compEB.getUserfax2()%>" size="6">
								-&nbsp;
								<input class="read" name="userfax3" maxlength="4" value="<%=compEB.getUserfax3()%>" size="6">
							</td>
						</tr>
						<tr>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;핸드폰번호</td>
							<td class="tdb"	width="30%"><%=List.getCode("TFH", "usermobile1", "GUN", "")%>
								<!--select name="usermobile1">
									<option value="010">010</option>
									<option value="011">011</option>
									<option value="016">016</option>
									<option value="017">017</option>
									<option value="018">018</option>
									<option value="019">019</option>
									<option value="0130">0130</option-->
								</select>
								-&nbsp;
								<input class="read" name="usermobile2" maxlength="4" value="<%=compEB.getUsermobile2()%>" size="6">
								-&nbsp;
								<input class="read" name="usermobile3" maxlength="4" value="<%=compEB.getUsermobile3()%>" size="6">
							</td>
							<td class="tdar"	width="20%"	height="23"><span class="red">*</span>&nbsp;우편번호</td>
							<td class="tdb"	width="30%"	height="23">
								<input class="readonly" name="postno1" size="5" value="<%=compEB.getPostno1()%>" maxlength="3" ReadOnly>
								&nbsp;-&nbsp;
								<input class="readonly" name="postno2" size="5" value="<%=compEB.getPostno2()%>" maxlength="3" ReadOnly>
								<a href="JavaScript:f_FindPostNum()">
									<img src = "/img/bt_search.gif" align = "absmiddle" border=0 width="56" height="20">
								</a>
							</td>
						</tr>
						<tr>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;주소</td>
							<td class="tdb"		colspan="3">
								<input class="readonly" name="address1"	size="45" value="<%=compEB.getAddress1()%>" maxlength="25" ReadOnly>
								<input class="read" name="address2"	size="45" value="<%=compEB.getAddress2()%>" maxlength="25">
							</td>
						</tr>
						<tr>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;메일주소</td>
							<td class="tdb">
								<input class="read" name="usermail" maxlength="35" size="35" value="<%=compEB.getUsermail()%>">
							</td>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;선호채널</td>
							<td class="tdb">
								<select name="preference">
									<option value="EMAIL">EMAIL</option>
									<option value="PDA">PDA</option>
									<option value="SMS">SMS</option>
									<option value="FAX">FAX</option>
									<option value="ETC">없음</option>
								</select>
							</td>
						</tr>
						<tr>
							<td height="2"  class="line" colspan="4"></td>
						</tr>
					</table>
					<br>
					
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tr">
						<tr> 
							<td class="fontb">[ 업체정보 ]</td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
						<tr> 
							<td height="4" class="line" colspan="4"></td>
						</tr>
						<tr>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;사업자등록번호</td>
							<td class="tdb"	width="30%">
								<input class="readonly" type="text" name="businessno1" size="4" maxlength="3" readOnly value="<%=compEB.getBusinessno1()%>">
								&nbsp;-&nbsp;
								<input class="readonly" type="text" name="businessno2" size="3" maxlength="2" readOnly value="<%=compEB.getBusinessno2()%>">
								&nbsp;-&nbsp;
								<input class="readonly" type="text" name="businessno3" size="6" maxlength="5" readOnly value="<%=compEB.getBusinessno3()%>">
							</td>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;상호명</td>
							<td class="tdb"	width="30%">
								<input class="readonly" name="enterprise" maxlength="20" readOnly value="<%=compEB.getEnterprise()%>">
							</td>
						</tr>
						<tr>							
							<td class="tdar"	width="13%"><span class="red">*</span>&nbsp;업체구분</td>
<%
	String ent_gubun = "";
	if (compEB.getEntdivide() != null) {
		switch(Integer.parseInt(compEB.getEntdivide())) {
			case 1:
				ent_gubun = "방출업체";
				break;
			case 2:
		//				ent_gubun = "해외업체";		// 분류 코드에 따른 차후 변경 필요~~~
		//		break;
			default:
				ent_gubun = "해외업체";
				break;
		}
	}
%>
							<td class="tdb"> 
								<input class="readonly" type="text" name="entdivide1" maxlength="20"   value="<%=ent_gubun%>" ReadOnly>
								<input type="hidden" name="entdivide" value="<%=compEB.getEntdivide()%>">
							</td>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;본사전화번호</td>
							<td class="tdb"><%=List.getCode("TFH", "corptel1", "GUL", "")%>
								<!--select name="corptel1">
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
								<input class="read" name="corptel2" maxlength="4" value="<%=compEB.getCorptel2()%>" size="6">
								-&nbsp;
								<input class="read" name="corptel3" maxlength="4" value="<%=compEB.getCorptel3()%>" size="6">
							</td>
						</tr>
						<tr> 
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;부서명</td>
							<td class="tdb"	width="30%"> 
								<input class="read" name="partname" maxlength="8" value="<%=compEB.getPartname()%>">
							</td>
							<td class="tdar"	width="20%">&nbsp;부서장</td>
							<td class="tdb"	width="30%"> 
								<input class="read" name="partchief" maxlength="8" value="<%=compEB.getPartchief()%>" size="20">
							</td>
						</tr>
						<tr> 
							<td class="tdar"	width="20%">&nbsp;직책</td>
							<td class="tdb" colspan="3"> 
								<input class="read" name="position" value="<%=compEB.getPosition()%>" maxlength="30" >
							</td>							
						</tr>
						<tr> 
							<td height="2"  class="line" colspan="4"></td>
						</tr>
					</table>
					<br>

					<table width="100%" class="fontb">
						<tr>
							<td>[ 주요수요품명 ]</td>
						</tr>
					</table>
					
					<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
						<tr>
							<td height="4" class="line" colspan="4"></td>
						</tr>
						<tr>
							<td class="tdar"	width="20%">
								&nbsp;
								<select name="div1">
									<option value="0" selected></option>
									<option value="1">알루미늄</option>
									<option value="2">전기동</option>
									<option value="3">아연</option>
									<option value="4">연괴</option>
									<option value="5">주석</option>
									<option value="6">니켈</option>
									<option value="7">희소금속류</option>
									<option value="8">임산물류</option>
								</select>
							</td>
							<td class="tdb"	width="30%">
								연간소요량&nbsp;<input type="text" class="read" name="good1" size="15" maxlength="12" value="<%=compEB.getGood1()==null ? "" : compEB.getGood1()%>"> 톤
							</td>
							<td class="tdar"	width="20%">
								&nbsp;
								<select name="div2">
									<option value="0" selected></option>
									<option value="1">알루미늄</option>
									<option value="2">전기동</option>
									<option value="3">아연</option>
									<option value="4">연괴</option>
									<option value="5">주석</option>
									<option value="6">니켈</option>
									<option value="7">희소금속류</option>
									<option value="8">임산물류</option>
								</select>
							</td>
							<td class="tdb"	width="30%">
								연간소요량&nbsp;<input type="text" class="read" name="good2"	size="15" maxlength="12" value="<%=compEB.getGood2()==null ? "" : compEB.getGood2()%>"> 톤
							</td>
						</tr>
						<tr>
							<td class="tdar"	width="20%" >
								&nbsp;
								<select name="div3">
									<option value="0" selected></option>
									<option value="1">알루미늄</option>
									<option value="2">전기동</option>
									<option value="3">아연</option>
									<option value="4">연괴</option>
									<option value="5">주석</option>
									<option value="6">니켈</option>
									<option value="7">희소금속류</option>
									<option value="8">임산물류</option>
								</select>
							</td>
							<td class="tdb"	width="30%">
								연간소요량&nbsp;<input type="text" class="read" name="good3" size="15" maxlength="12" value="<%=compEB.getGood3()==null ? "" : compEB.getGood3()%>"> 톤
							</td>
							<td class="tdar"	width="20%">
								&nbsp;
								<select name="div4">
									<option value="0" selected></option>
									<option value="1">알루미늄</option>
									<option value="2">전기동</option>
									<option value="3">아연</option>
									<option value="4">연괴</option>
									<option value="5">주석</option>
									<option value="6">니켈</option>
									<option value="7">희소금속류</option>
									<option value="8">임산물류</option>
								</select>
							</td>
							<td class="tdb"	width="30%">
								연간소요량&nbsp;<input type="text" class="read" name="good4"	size="15" maxlength="12" value="<%=compEB.getGood4()==null ? "" : compEB.getGood4()%>"> 톤
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
								<img src="/img/bu_delete.gif" align="absmiddle" onClick="f_delete(1)" width="56" height="20" style="cursor:hand">
								<img src="/img/bu_close.gif" align="absmiddle" onClick="JavaScript:window.close();" width="56" height="20" style="cursor:hand">
							</td>
							<input type="hidden" name="gubun" value="<%=request.getParameter("gubun")%>">
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
