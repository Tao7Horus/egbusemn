<%@ page language="java" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="user.*" %>

<%
	HP_PAB_CompUserSB compSB = new HP_PAB_CompUserSB();


	String gubun = "";
	String egubun = "";				// 업체구분
	String businessno1 = new String();
	String businessno2 = new String();
	String businessno3 = new String();

	Vector compinfo = new Vector();

	if (request.getParameter("gubun")!=null || !request.getParameter("gubun").equals(""))
	{
		gubun = request.getParameter("gubun");
	}


%>
<html>
<head>
<title>사업자등록번호 검색</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" type="text/css" href="/css/UM.css">
<script language="JavaScript">
function f_onlyNumber() {
	if( !( (event.keyCode>47 && event.keyCode<58) ||
           (event.keyCode>34 && event.keyCode<41) ||
           (event.keyCode>95 && event.keyCode<106) ||
           (event.keyCode == 8) || (event.keyCode == 13) ||
           (event.keyCode == 46) || (event.keyCode == 9) ||
		   (event.keyCode == 3) || (event.keyCode == 16) ||
		   (event.keyCode == 94) || (event.keyCode == 67) ||
		   (event.keyCode == 86) || (event.keyCode == 118) ||
		   (event.keyCode == 99)
		) )
    {
		event.returnValue = false;
    }
}

function f_isNull()
{
	if (search.businessno1.value==null || search.businessno1.value=="") {
		alert("사업자등록번호 첫번째란이 비었습니다.");
		search.businessno1.focus();
		return true;
	}
	if (search.businessno2.value==null || search.businessno2.value=="") {
		alert("사업자등록번호 두번째란이 비었습니다.");
		search.businessno2.focus();
		return true;
	}
	if (search.businessno3.value==null || search.businessno3.value=="") {
		alert("사업자등록번호 세번째란이 비었습니다.");
		search.businessno3.focus();
		return true;
	}
	return false;
}

function f_submit()
{
	if (!f_isNull())
		search.submit();
}

function f_submit2(businessno1, businessno2, businessno3, enterprise, egubun, entdivide)
{
	window.opener.userInput.businessno1.value = businessno1;
	window.opener.userInput.businessno2.value = businessno2;
	window.opener.userInput.businessno3.value = businessno3;
	window.opener.userInput.enterprise.value = enterprise;
	window.opener.userInput.entdivide1.value = egubun;
	window.opener.userInput.entdivide.value = entdivide;
	window.close();
}
</script>
</head>

<body background="/img/pop_bg.gif" topmargin="0" leftmargin="0" rightmargin="0" style="background-position:left; background-repeat:repeat-y; bgcolor=white">
	<table border=0 cellspacing=0 cellpadding=0 height=100% width=100%>
		<tr valign="bottom" height="57">
			<td colspan="3">
				<table width=100% cellpadding=0 cellspacing=0 border=0 bgcolor=white>
					<tr height=51 valign=bottom>
						<td rowspan=2 width=30></td>
						<td width=5><img src='/img/pop_tit_dot.gif'></td>
						<td width=* class='HEADLINE' background='/img/pop_tit_t.jpg' style='background-position:right; background-repeat:no-repeat;'>&nbsp;업체정보 검색</td>
					</tr>
					<tr height=6>
						<td></td>
						<td background='/img/pop_tit_b.jpg' style='background-position:right; background-repeat:no-repeat;'></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height=1>
			<td colspan=3 background='/img/pop_tit_bg.gif'></td>
		</tr>
		<tr height=25>
			<td colspan=3></td>
		</tr>
		<tr valign=top>
			<td width=30></td>
			<td width=*>
<form name="search" method="post" action="/jsp/RA/user/HP_PAJ_SearchCorpInfo.jsp">
				<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
					<tr>
						<td height="4" colspan="2" class="line"></td>
					</tr>
					<tr>
						<td class="tdar"  width="30%">사업자등록번호</td>
						<td class="tdb">
<%
	if (gubun.equals("0"))
	{
		businessno1 = request.getParameter("businessno1");
		businessno2 = request.getParameter("businessno2");
		businessno3 = request.getParameter("businessno3");
%>
							<input class="read" type="text" name="businessno1" size="4" maxlength="3" value="<%=businessno1%>">
								&nbsp;-&nbsp;
							<input class="read" type="text" name="businessno2" size="3" maxlength="2" onKeyDown="f_onlyNumber(this)" value="<%=businessno2%>">
								&nbsp;-&nbsp;
							<input class="read" type="text" name="businessno3" size="6" maxlength="5" onKeyDown="f_onlyNumber(this)" value="<%=businessno3%>">
							<input type="text" class="exreadc" name="temp" readonly size="1"><!--Enter 방지용 -->
							<img src="/img/bu_refer.gif" style="cursor:hand" align = "absmiddle" onClick="f_submit()">
<%
	} else {
%>
							<input class="read" type="text" name="businessno1" size="4" maxlength="3">
								&nbsp;-&nbsp;
							<input class="read" type="text" name="businessno2" size="3" maxlength="2" onKeyDown="f_onlyNumber(this)">
								&nbsp;-&nbsp;
							<input class="read" type="text" name="businessno3" size="6" maxlength="5" onKeyDown="f_onlyNumber(this)">
							<input type="text" class="exreadc" name="temp" readonly size="1"><!--Enter 방지용 -->
							<img src="/img/bu_refer.gif" style="cursor:hand" align = "absmiddle" onClick="f_submit()">
<%
	}
%>
						</td>
					</tr>
					<tr>
						<td height="2" colspan="2" class="line"></td>
					</tr>
					<input type="hidden" name="gubun" value="0">
				</table>
</form>

<%
	if (gubun.equals("0"))
	{
%>
				<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
					<tr>
						<td height="4" colspan="4" class="line"></td>
					</tr>
					<tr height="20">
						<td class="tdac" width="130"><p>사업자등록번호</p></td>
						<td class="tdac" width="200"><p>업체명</p></td>
						<td class="tdac" width="100"><p>업체구분</p></td>
					</tr>
					<tr>
						<td height="2" colspan="4" class="line"></td>
					</tr>
<form name="information" method="post" action="">
					<!-- 업체 정보 출력 부분 -->
<%
		compinfo = compSB.findEnterpriseInfo(businessno1+businessno2+businessno3);

		for (int i = 0; i < compinfo.size(); i++ ) {
			HP_PAE_CompInfoEB compEB = new HP_PAE_CompInfoEB();
			compEB = (HP_PAE_CompInfoEB)compinfo.get(i);

			if (compEB.getEntdivide().equals("1")) {
				egubun = "원자재구매업체";
			} else {
				egubun = "기타업체";
			}

%>
					<tr>
						<td class="tdbc" align="center">
							<a href="JavaScript:f_submit2('<%=compEB.getBusinessno1()%>', '<%=compEB.getBusinessno2()%>', '<%=compEB.getBusinessno3()%>', '<%=compEB.getEnterprise()%>', '<%=egubun%>', '<%=compEB.getEntdivide()%>')"><%=compEB.getBusinessno1()%> - <%=compEB.getBusinessno2()%> -  <%=compEB.getBusinessno3()%></a>
						</td>
						<td class="tdb" align="left">&nbsp;<%=compEB.getEnterprise()%></td>
						<td class="tdb" align="center">&nbsp;<%=egubun%></td>
					</tr>
<%
		}
%>
					<!-- 업체 정보 출력 부분 -->
</form>
					<tr>
						<td height="2" colspan="4" class="line"></td>
					</tr>
				</table>
<%
	}
%>
			</td>
			<td width=28></td>
		</tr>
		<tr height=10>
			<td colspan=3>
				<table width="100%" cellpadding="2" cellspacing="2">
					<tr>
						<td align="center">
							<img src="/img/bu_close.gif" align="absmiddle" onClick="JavaScript:window.close();" width="56" height="20" style="cursor:hand">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height=*>
			<td colspan=3></td>
		</tr>
		<tr>
			<td colspan=3 height=45 width="*">
				<table width=100% cellpadding=0 cellspacing=0 border=0 background='/img/pop_bottom_back.gif' height=45>
					<tr>
						<td background='/img/pop_bottom.gif' style='background-position:right; background-repeat:no-repeat;'></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
