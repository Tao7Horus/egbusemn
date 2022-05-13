<%--
/******************************************************************************/
/*                                                                            */
/*  프로그램 ID    :   HP_PAJ_FindIDPW.jsp                                    */
/*                                                                            */
/*  프로그램 설명  :   아이디/비밀번호 찾기 화면                              */
/*                                                                            */
/******************************************************************************/
		2007.05.31	이광현	이용자부가서비스 변경신청 절차개선 (비밀번호 찾기 추가)

--%>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>
<%@ page contentType="text/html;charset=euc-kr" %>
<html>
<head>
<title>아이디/비밀번호 찾기</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">

<link rel="stylesheet" href="/css/EP.css" type="text/css">
<link rel="stylesheet" href="/css/button.css" type="text/css">
<script language="JavaScript">
<!--
	// 2007.05.31		이용자부가서비스 변경신청 절차개선 (메일주소 유효성체크 추가)
	function f_submit()
	{
		if (findIDPW.username.value=="" || findIDPW.username.value==null) {
			alert("이용자 이름이 입력되어 있지 않습니다.");
			findIDPW.username.focus();
			return;
		}

		if (findIDPW.registno1.value=="" || findIDPW.registno1.value==null) {
			alert("주민등록번호 앞자리가 입력되어 있지 않습니다.");
			findIDPW.registno1.focus();
			return;
		}

		if (findIDPW.registno2.value=="" || findIDPW.registno2.value==null) {
			alert("주민등록번호 뒷자리가 입력되어 있지 않습니다.");
			findIDPW.registno2.focus();
			return;
		}		
		if (findIDPW.searchgubun[1].checked == true) {
			if (findIDPW.usermail.value.indexOf('@') < 0 ) { 
				alert('메일주소를 정확히 입력해 주세요'); 
				findIDPW.usermail.select();
				findIDPW.usermail.focus();
				return;
			} else if(findIDPW.usermail.value.charAt(findIDPW.usermail.value.indexOf('@')-1) == '@' ) {
				alert('메일주소를 정확히 입력해 주세요');
				findIDPW.usermail.select();
				findIDPW.usermail.focus();
				return;
			} else if((findIDPW.usermail.value.charAt(findIDPW.usermail.value.indexOf('@')+1) == '@' ) || (findIDPW.usermail.value.charAt(findIDPW.usermail.value.indexOf('@')+1) == '')) {
				alert('메일주소를 정확히 입력해 주세요');
				findIDPW.usermail.select();
				findIDPW.usermail.focus();
				return;
			}
		}
		findIDPW.submit();
	}

	// 2007.05.31	이용자부가서비스 변경신청 절차개선 (비밀번호 찾기 추가)
	function jsf_onclick(vv) 
    { 
		if(vv == 'pw') 
        { 
			document.all.pwappend.style.display = ""; 
			document.all.pwmsg.style.display = "";
			document.all.idmsg.style.display = "none";
        } 
        else 
        { 
			document.all.pwappend.style.display ="none";
			document.all.pwmsg.style.display ="none";
			document.all.idmsg.style.display ="";
        } 
    }

//-->
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" topmargin="0" leftmargin="0" background="/img/bg01.gif">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" background="/img/bg_sub.gif">
		<tr valign="bottom" height="57"> 
			<td rowspan=2 valign="top"><img src="/img/sub_title_01.jpg" width="35" height="64"></td>
			<td colspan=2 background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE">&nbsp;&nbsp; 아이디/비밀번호 찾기</td>
		</tr>
		<tr height="7"> 
			<td colspan="2" valign="top"><img src="/img/sub_title_03.jpg" width="788" height="7"></td>
		</tr>
	</table>

	<form name="findIDPW" method="post" action="/servlet/user.HP_PAV_FindIDPW">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" height="100%" cellpadding="0" cellspacing="0">
		<tr valign="top"> 
			<td align="center" colspan=2> 

		<table border = "1" bordercolor="black" bordercolordark="black" bordercolorlight="black" cellpadding="0" cellspacing="0">
		<tr><td>
				<table width="350" border="0" cellspacing="0" cellpadding="0" bgcolor="#E7E7E7">
					<tr>
					    <td class="text" height="6" width="20"></td>
						<td class="text" height="6" width="336"></td>
					</tr> 
					<tr>
						<td class="txt1" height="30" width="20"></td>
						<td class="fontb" height="30" width="336">이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름:
							<input type="text" name="username">
						</td>
					</tr>
					<tr>
						<td class="txt1" height="30" width="20"></td>
						<td class="fontb" height="30" width="336">주민등록번호:
							<input type="text" name="registno1" size="8" maxlength="6"> - <input type="password" name="registno2" size="9" maxlength="7"><!-- 2007.05.31	주민등록번호 뒷자리 *표로 보이도록 처리 -->
						</td>
					</tr>
				</table>

				<!-- 2007.05.31	이용자부가서비스 변경신청 절차개선 (비밀번호 찾기 추가) start -->
				<div id = "pwappend" style="display:none;">
				<table width="350" border="0" cellspacing="0" cellpadding="0" bgcolor="#E7E7E7">
					<tr>
						<td class="txt1" height="30" width="20"></td>
						<td class="fontb" height="30" width="336">메 &nbsp;일 &nbsp;주 &nbsp;소:
							<input type="text" name="usermail" size="25" maxlength="35">
						</td>
					</tr>
				</table>
				</div>
				<!-- 2007.05.31	이용자부가서비스 변경신청 절차개선 (비밀번호 찾기 추가) end -->

				<table width="350" border="0" cellspacing="0" cellpadding="0" bgcolor="#E7E7E7">
					<tr>
						<td class="txt1" height="10" colspan="2"></td>
					</tr>
					<tr>
						<td class="txt1" height="30" width="20"></td>
						<td class="fontp" height="30" width="336">
							<input type="radio" name="searchgubun" value="id" onClick="jsf_onclick(this.value)" checked>
							아이디찾기
							<input type="radio" name="searchgubun" value="pw" onClick="jsf_onclick(this.value)" >
							비밀번호찾기
							<!-- <br><br>
							☞ 비밀번호는 암호화되어 있어 확인할 수 없습니다.<br>&nbsp;&nbsp;&nbsp;
							비밀번호 분실 시에는 조달청 콜센터(1588-0800)로<br>&nbsp;&nbsp;&nbsp;
                            문의하시기 바랍니다. -->
						</td>
					</tr>
					<tr>
						<td class="text" height="8"></td>
						<td class="text" height="8"></td>
					</tr>
				</table>

				<!-- 2007.05.31	이용자부가서비스 변경신청 절차개선 (비밀번호 찾기 추가) start -->
				<div id = "idmsg" style="display:visible;">
				<table width="350" border="0" cellspacing="0" cellpadding="0" bgcolor="#E7E7E7">
					<tr>
						<td class="txt1" height="30" width="20"></td>
						<td class="fontp" height="35" width="336">
						☞ 아이디를 잊으셨다면 이름, 주민등록번호를 입력하여<br>&nbsp;&nbsp;&nbsp;
						   확인 버튼을 누르세요.<br>&nbsp;&nbsp;&nbsp;
						   등록된 내용과 일치할 경우 아이디를 보여드립니다.<br><br>
						</td>
					</tr>
				</table>
				</div>
				<div id = "pwmsg" style="display:none;">
				<table width="350" border="0" cellspacing="0" cellpadding="0" bgcolor="#E7E7E7">
					<tr>
						<td class="txt1" height="30" width="20"></td>
						<td class="fontp" height="35" width="336">
						☞ 임시비밀번호는 입력하신 메일주소로 발송됩니다.<br>&nbsp;&nbsp;&nbsp;
						   입력한 정보가 등록된 내용과 일치할 경우 로그인할 수 <br>&nbsp;&nbsp;&nbsp;
						   있는 임시비밀번호가 메일주소로 발송됩니다.<br><br>
						</td>
					</tr>
				</table>
				</div>
				<!-- 2007.05.31	이용자부가서비스 변경신청 절차개선 (비밀번호 찾기 추가) end -->
		</td></tr></table>
				<br>
				<table width="100%" cellpadding="2" cellspacing="2">
					<tr>
						<td width="52%" align="right">
							<a href ="JavaScript:f_submit();"><img src="/img/bt_ok.gif" align="absmiddle"  border=0 width="56" height="20"></a>
						</td>
						<td width="5" align="left">&nbsp;</td>
						<td width="48%" align="left">
							<a href="#" onClick="JavaScript:window.close()"><img name="Image3" border="0" src="/img/bu_cancel.gif" align="absmiddle" width="56" height="20"></a>
						</td>

					</tr>
				</table>
				<br>
			</td>
		</tr>
		<tr> 
			<td colspan="2" height="50"> 
			</td>
		</tr>
	</table>
	</form>
</body>
</html>

