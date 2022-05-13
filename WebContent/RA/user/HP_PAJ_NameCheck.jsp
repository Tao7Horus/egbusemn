<%@ page language="java" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="user.*" %>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>

<%@ page contentType="text/html;charset=euc-kr" %>

<%
	String url = "";

	// 구분자(gubun)			1 : 일반이용자		2 : 비축물자이용자
	String gubun = request.getParameter("gubun")==null? "" : request.getParameter("gubun");

	if (gubun.equals("1")) {
		url = "/jsp/RA/user/HP_PAJ_GeneralInsert.jsp";
	} else {
		url = "/jsp/RA/user/HP_PAJ_CompInsert.jsp";
	}

	// 한국신용평가 실명인증 위해 추가
	//HttpSession s = request.getSession(true);				// 2007-10-11
	//s.putValue("NmChkSec","98u9iuhuyg87");				// 2007-10-11
%>

<html>
<head>
<title>실명확인</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="/css/EP.css" type="text/css">
<link rel="stylesheet" href="/css/button.css" type="text/css">
<script language="JavaScript" src="/js/user.js"></script>
<script language="JavaScript">

// 잘못 입력되어 있는 란 체크
function f_check()
{
	if (namecheck.username.value == "")
	{
		alert("이름이 입력되어 있지 않습니다.");
		namecheck.username.focus();
		return false;
	}
	if (!f_isKorean(namecheck.username.value))
	{
		alert("이름은 한글 이외에는 입력하실수 없습니다.");
		namecheck.username.focus();
		return false;
	}
	if (namecheck.registno1.value == "")
	{
		alert("주민등록번호 앞자리가 입력되어 있지 않습니다.");
		namecheck.registno1.focus();
		return false;
	}
	if (f_lengthConfirm(namecheck.registno1.value, 6))
	{
		alert("주민등록번호 앞자리가 제대로 입력되어 있지 않습니다.");
		namecheck.registno1.focus();
		return false;
	}
	if (!f_isNumber(namecheck.registno1.value))
	{
		alert("주민등록번호 앞자리에 숫자가 아닌 다른 것이 포함되어 있습니다.");
		namecheck.registno1.focus();
		return false;
	}
	if (namecheck.registno2.value == "")
	{
		alert("주민등록번호 뒷자리가 입력되어 있지 않습니다.");
		namecheck.registno2.focus();
		return false;
	}
	if (f_lengthConfirm(namecheck.registno2.value, 7))
	{
		alert("주민등록번호 뒷자리가 제대로 입력되어 있지 않습니다.");
		namecheck.registno2.focus();
		return false;
	}
	if (!f_isNumber(namecheck.registno2.value))
	{
		alert("주민등록번호 뒷자리에 숫자가 아닌 다른 것이 포함되어 있습니다.");
		namecheck.registno2.focus();
		return false;
	}
/* 주민번호 정합성 체크 막음 - 태광호
	if (!f_CheckRegNo(namecheck.registno1.value + namecheck.registno2.value))
	{
		alert("올바르지 않은 주민등록 번호가 입력 되었습니다.\n확인 후 다시 입력 바랍니다.");
		namecheck.registno1.value = "";
		namecheck.registno2.value = "";
		namecheck.registno1.focus();
		return false;
	}
*/

	return true;
}

function f_next()
{
	size = namecheck.registno1.value.length;
	if (size == 6)
		namecheck.registno2.focus();
}

function f_init()
{

}

// submit
function f_submit() {
	if (f_check()) namecheck.submit();
	return false;
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" topmargin="0" leftmargin="0" background="/img/bg01.gif">
	<table width="823" border="0" cellpadding="0" cellspacing="0" background="/img/bg_sub.gif" height="100%">
		<tr valign="bottom" height="57">
			<td rowspan="2"><img src="/img/sub_title_01.jpg" width="35" height="64"></td>
			<td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE">&nbsp;&nbsp; 실명인증</td>
		</tr>
		<tr height="7">
			<td colspan="2"><img src="/img/sub_title_03.jpg" width="788" height="7"></td>
		</tr>
		<tr height="21">
			<td colspan="3"></td>
		</tr>
		<tr valign="top">
			<td width="35"></td>
			<td width="760">			
			
				<table width="100%" border="0" cellpadding="2" cellspacing="0" class="tr">
					<tr>
						
          <td  height="200" valign="bottom"aling="left" style="padding-left:15;padding-top:30"> 
		  <table width="695" border="0" cellspacing="0" cellpadding="0">
              <tr> 
                <td width="49" rowspan="3" valign="bottom"><img src="/img/_e_left1.gif" width="49" height="48"></td>
                <td width="91" rowspan="3" valign="bottom"><img src="/img/_e_left2.gif" width="91" height="130"></td>
                <td height="30" colspan="3" style="padding-left:10"><img src="/img/_e_title.gif" width="134" height="16"></td>
              </tr>
              <tr> 
                <td width="40" rowspan="2"><img src="/img/_e_left3.gif" width="40" height="139"></td>
                <td width="506" height="70"  bgcolor="E6E6E7" style="padding-left:30;padding-top:25"><img src="/img/_e_text1.gif"  height="31"></td>
                <td width="8" rowspan="2"><img src="/img/_e_right.gif" width="8" height="139"></td>
              </tr>
              <tr> 
                <td width="506" height="69" bgcolor="E6E6E7"style="padding-left:30;padding-top:11" valign="top"><img src="/img/_e_text2.gif" width="416" height="49"></td>
              </tr>
			  
			  <tr>
			  <td colspan="2"></td>
			  <td colspan="3" style="padding-top:40">
			  <!----체크---->
			      <table width="554" border="0" cellspacing="0" cellpadding="0">
                    <tr> <form name="namecheck" method="post" action="/servlet/user.HP_PAV_NameCheck" onsubmit="return f_submit()">
                      <td height="30" colspan="2" style="padding-left:10"><img src="/img/_e_tt1.gif" width="324" height="14"></td>
                    </tr>
                    <tr> 
                      <td height="1" colspan="2" bgcolor="589DDA" ></td>
                    </tr>
                    <tr> 
                      <td width="85" height="27" bgcolor="CCDEF6" style="padding-left:15"><img src="/img/_e_id.gif" width="27" height="17"></td>
                      <td width="477"style="padding-left:15" >
						<input type="text" name="username" value="" maxlength="20" size="21">
						<img src="/img/name_text.gif"/>
					  </td>
                    </tr>
                    <tr> 
                      <td height="1" colspan="2" bgcolor="589DDA" ></td>
                    </tr>
                    <tr> 
                      <td width="85" height="27" bgcolor="CCDEF6" style="padding-left:15"><img src="/img/_e_pass.gif" width="63" height="17"></td>
                      <td style="padding-left:15" ><input type="text" name="registno1" maxlength="6" size="8" onKeyDown="f_onlyNumber();" onKeyUp="f_next()">
										- 
										<input type="password" name="registno2" maxlength="7" size="9" onKeyDown="f_onlyNumber();"></td>
                    </tr>
                      <tr> 
                       <td height="1"colspan="2" bgcolor="589DDA" ></td>
                    </tr>
		    <tr>
		       <td colspan="2" align="left" height="35" style="padding-left:113">
			 <img src="/img/bt_ok.gif" border="0" align="absmiddle" width="56" height="20" onClick="f_submit()" style="cursor:hand">
			 <!--
			 <input type=image src="/img/bt_ok.gif" align="absmiddle" width=56, height=20>
			 -->
			 <a href="javascript:history.back();" onFocus="blur()"><img src="/img/bu_back.gif" border="0" align="absmiddle" width="56" height="20" style="cursor:hand"></a></td></tr>               
                  </table>

			  			  
			  <!---체크 --->			  
			  </td>
			  </tr>
			  	<input type="hidden" name="url" value="<%=url%>">
			  </form>
            </table>           
          </td>
					</tr>
					
					<tr>
						<td align="center" height="100"></td>
					</tr>
				</table>
			</td>
			<td width="28"></td>
		</tr>
		<tr height="35">
			<td colspan="3"></td>
		</tr>
		<tr height="*">
			<td colspan="3"></td>
		</tr>
		<tr>
			<td colspan="3" height="50">
				<%@ include file="/jsp/common/Footer.jsp" %>
			</td>
		</tr>
	</table>
</body>
</html>
