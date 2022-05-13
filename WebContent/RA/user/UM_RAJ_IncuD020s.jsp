<%--
/******************************************************************************************/
/*                                                                                        */
/*    Program ID    :     UM_RAJ_IncuD020s.jsp                                            */
/*                                                                                        */
/*    description   :     목록화요청사용자등록확인화면									  */
/*                                                                                        */
/******************************************************************************************/
/*		최초생성         2002.06.12          오 창 렬                                     */
/******************************************************************************************/
	
	2003.04.15	김영진	프로그램 정리
--%>

<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*" %>
<%@page import="common.*" %>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>
<jsp:useBean id="ctl" class="beans.UM_RAB_IncuA025p" scope="page" />
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />

<%
try{
	ParameterParser psr = new ParameterParser(request);

	String userId = psr.getStringParameter("userId",		"");
	String dt1;
	String dt2;
	String dt3;
	UM_ICE_InciA040b  ett = null;
	ett=ctl.select_coperation(userId);

%>

<html>

<head>
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<title>목록화요청사용자정보</title>
<link rel="stylesheet" type="text/css" href="/css/UM.css">
</head>
    <SCRIPT LANGUAGE="JavaScript">
        function js_submit() {
            document.forms[0].action='/jsp/RA/UM_RAJ_Mail020s.jsp';
            document.forms[0].target='_self';
            document.forms[0].submit();
            return;
        }
    
    </SCRIPT>

<body background="/img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<!---------------------- 타이틀 테이블 시작 ------------------------>
<form  name="fm" method="POST">
<table width="800" border="0" cellpadding="0" cellspacing="0" height="100%"  background="/img/bg_sub.gif">
    <tr valign="bottom" height="57"> 
      <td rowspan="2"><img src="/img/sub_title_01.jpg"></td>
      <td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE"> 
        &nbsp;&nbsp;목록화요청(공공기관) 사용자정보</td>
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
	

<table width="100%" class="fontb">
	<tr>
		<td>[ 아이디정보 ]																						</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
	<tr> 
		<td height="4" class="line"	colspan=4></td>
	</tr>          
	<tr>
		<td class="tdar"	width="20%">아이디&nbsp;															</td>
		<td class="tdb"		colspan=3> &nbsp;<%=ett.getuserId()%>												</td>
	</tr>
	<tr> 
		<td height="2"  class="line"	colspan=4></td>
	</tr>		  
</table>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tr">
	<tr> 
		<td class="fontb">[ 기본정보 ]																			</td>
	</tr>
</table>
<!---------------------- 마스터정보 타이틀 테이블 끝 ------------------------>

<!---------------------- 마스터정보 테이블 시작 ------------------------>
<table width="100%" border="0" cellspacing="1" cellpadding="0" class="tr">
	<tr> 
		<td height="4" class="line"	colspan=4></td>
	</tr>          
	<tr> 
		<td class="tdar"><p>기관명&nbsp;</p>																	</td>
		<td class="tdb"		width="30%">&nbsp;<%=ett.getupcheNm()%>												</td>
		<td class="tdar"	width="20%"><p>사업자등록번호&nbsp;</p>												</td>
		<td class="tdb"		width="30%">&nbsp;<%=insert_minus_saupno(ett.getsaupNo())%>							</td>
	</tr>
	<tr> 
		<td height="2"  class="line"	colspan=4></td>
	</tr>		  
</table>
<!---------------------- 마스터정보 테이블 끝 ------------------------>

<br>
<!---------------------- 사용자정보 타이틀 테이블 시작 ------------------------>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tr">
  <tr> 
	<td class="fontb">[ 사용자정보 ]</td>
  </tr>
</table>
<!---------------------- 사용자정보 타이틀 테이블 끝 ------------------------>

<!---------------------- 사용자정보 테이블 시작 ------------------------>
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
	<tr> 
		<td height="4" class="line"	colspan=4></td>
	</tr>          
  <tr> 
    <td class="tdar"	width="20%"><p>등록일자&nbsp;</p>														</td>
    <td class="tdb"		colspan="3"><%=ett.getinsertDay()%>														</td>
  </tr>
  <tr> 
    <td class="tdar"	width="20%"><p>성명&nbsp;</p>																			</td>
    <td class="tdb" 	width="30%">&nbsp;<%=ett.getname()%>																	</td>
    <td class="tdar"	width="20%"><p>주민등록번호&nbsp;</p>																	</td>
    <td class="tdb" 	width="30%">&nbsp;<%=insert_minus_jumin_star(ett.getjuminNo())%>											</td>
  </tr>
	<tr> 
		<td height="2"  class="line"	colspan=4></td>
	</tr>		  
</table>
<!---------------------- 사용자정보 테이블 끝 ------------------------>

<br>
<table width="100%" cellpadding="2" cellspacing="2">
    <tr> 
	<tr> 
		<td height="4" class="line"></td>
	</tr>          
		<td class="tdb" align="left"> 
		  <b>위와같이 등록신청이 완료되었습니다. </b><br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▷ 목록화관련업체에 등록한 사용자는 ID/PW방식으로 G2B시스템에 접근합니다.<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▷ ID/PW방식으로 G2B시스템에 접근한 사용자는 목록 카달로그시스템에서만 작업할 수 있습니다.<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▷ 입찰 및 투찰을 하기 위하여는 인증서에의한 업체기본사항을 재등록한후, 인증서를 등록합니다.<br>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▷ 인증서로 등록한후에는 ID/PW방식을 사용할 수 없습니다. 
		</td>
    </tr>
	<tr> 
		<td height="2"  class="line"></td>
	</tr>		  
</table>


	<input type="hidden" name="saupNo" value="<%=ett.getsaupNo()%>">
	<input type="hidden" name="userId" value="<%=ett.getuserId()%>">
	<input type="hidden" name="password" value="<%=ett.getpassword()%>">
	<input type="hidden" name="name" value="<%=ett.getname()%>">
	<input type="hidden" name="mail1" value="<%=ett.getmail()%>">
<!---------------------- 버튼 테이블 시작 ------------------------>
<table width="100%" cellpadding="2" cellspacing="2"> 
  <tr> 
	<td align="center"><a href="#"><img src="/img/bu_ok.gif" border="0" align="absmiddle" onClick='js_submit();'></a></td>
 </tr>
</table>
      </td>
    </tr>
    <tr> 
      <td colspan="3" height="50"><%@ include file="/jsp/common/Footer.jsp" %></td>
    </tr>
  </table>

<!---------------------- 버튼 테이블 끝 ------------------------>
</form>
</body>

</html>
<%}catch(Exception e){
	out.println("error : "+e.toString());
}
%>
