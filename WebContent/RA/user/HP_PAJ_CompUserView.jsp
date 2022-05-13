<%--
/****************************************************************************/
/*                                                                          */
/*    Program ID    :     HP_PAJ_CompUserView.jsp							*/
/*                                                                          */
/*    description   :     비축업체이용자조회화면							*/
/*                                                                          */
/****************************************************************************/
--%>

<%@ page language="java" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="user.*" %>
<%@ page import="servlets.ASO"%>

<%@ include file = "/jsp/common/fnUmCommon.jsp" %>

<%@ page contentType="text/html;charset=euc-kr" %>

<%
	String msg = null;
	ASO aso = null;

	try {
		aso = new ASO(request,response);
	} catch ( Exception e ) {
		e.printStackTrace();
		msg = "접근 권한이 없거나 로그인 정보를 확인할 수 없습니다.";
	}

	if ( aso != null && !(aso.getBUSEO().equals("060") || aso.getBUSEO().equals("064")) ) { // 정보기획팀, 정보관리팀
		msg = "업무에 접근할 수 있는 부서가 아닙니다.";
	}

	if ( msg != null ) {
		out.println("<html>");
		out.println("<script language=javascript>");
		out.println("   alert('"+msg+"');");
		out.println("   top.location.href='http://www.g2b.go.kr:8070/jsp/RA/master_login.jsp';");
		out.println("</script>");
		out.println("</html>");
	}
%>

<%
	String gubun = request.getParameter("gubun") == null ? "" :  request.getParameter("gubun");
	String enterprise = request.getParameter("enterprise") == null ? "" :  request.getParameter("enterprise");
	String businessno1 = request.getParameter("businessno1") == null ? "" : request.getParameter("businessno1");
	String businessno2 = request.getParameter("businessno2") == null ? "" : request.getParameter("businessno2");
	String businessno3 = request.getParameter("businessno3") == null ? "" : request.getParameter("businessno3");
	String entdivide = request.getParameter("entdivide") == null ? "1" : request.getParameter("entdivide");
	String npage = request.getParameter("npage") == null ? "1" : request.getParameter("npage");
	String size = request.getParameter("size") == null ? "10" : request.getParameter("size");

	int size2 = Integer.parseInt(size);

	int totalPage = 0;

	HP_PAE_UserList userList;
%>

<html>
<head>
<title>비축업체 이용자 조회</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="/css/EP.css" type="text/css">
<script language="javascript" src="/js/userview.js"></script>
<script language="JavaScript">
<!--
function f_init()
{
	userview.npage.value = "<%=npage%>";
	userview.enterprise.value = "<%=enterprise%>";
	userview.businessno1.value = "<%=businessno1%>";
	userview.businessno2.value = "<%=businessno2%>";
	userview.businessno3.value = "<%=businessno3%>";
}

function f_pageMove(chk)
{
	userview.npage.value = chk;
	movePage.npage.value = chk;
	movePage.submit();
}

function f_delete()
{
	if (confirm("정말 선택된 아이디를 삭제하시겠습니까?"))
	{
		var submitStr = "";
		for(i = 0; i < document.search.checkbox.length; i++)
		{
			if (document.search.checkbox[i].checked == true)
				submitStr += document.search.checkbox[i].value + ":";
		}
		document.search.deleteID.value = submitStr;
		document.search.submit();
	}
}

function f_modifyUser(id)
{
	url = "/servlet/user.HP_PAV_CompChange?userid=" + id + "&gubun=mod";
	launchCenter(url, "비축업체이용자정보", 800, 820);
}


function f_check()
{

}

function f_submit()
{
	userview.npage.value = "1"
	userview.submit();
}
//-->
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" topmargin="0" leftmargin="0" background="/img/bg01.gif" onLoad="f_init()">
	<table width="800" border="0" cellpadding="0" cellspacing="0" height="100%"  background="/img/bg_sub.gif">
<form name="userview" method="post" action="/servlet/user.HP_PAV_CompUserView">
	<input type="hidden" name="gubun" value="1">
	<input type="hidden" name="size" value="10">
	<input type="hidden" name="npage" value="1">
		<tr valign="bottom" height="57">
			<td rowspan="2" valign="top"><img src="/img/sub_title_01.jpg" width="35" height="64"></td>
			<td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE">
				&nbsp;&nbsp;비축업체 이용자 조회</td>
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
		<!----- 테이블 시작------>
				<br>
				<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
					<tr>
						<td height="4" class="line" colspan="4"></td>
					</tr>
					<tr>
						<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;업체명</td>
						<td class="tdb"	width="30%">
							<input class="read" name="enterprise" maxlength="20">
						</td>
						<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;사업자등록번호</td>
						<td class="tdb"	width="30%">
							<input class="read" name="businessno1" maxlength="3" size="4">
							-
							<input class="read" name="businessno2" maxlength="2" size="3" onKeyDown="f_onlyNumber()">
							-
							<input class="read" name="businessno3" maxlength="5" size="6" onKeyDown="f_onlyNumber()">
						</td>
					</tr>
					<tr>
						<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;업체구분</td>
						<td class="tdb" colspan="3">
							<input type="radio" name="entdivide" value="1" <%=entdivide.equals("1") ? "checked" : ""%>>
							비축업체
							<input type="radio" name="entdivide" value="2" <%=entdivide.equals("2") ? "checked" : ""%>>
							기타업체
						</td>
					</tr>
					<tr>
						<td height="2"  class="line" colspan="4"></td>
					</tr>
					<tr>
						<td colspan="4"><div align="right"><img border="0" src="/img/bu_search.gif"  onClick="f_submit()" style="cursor:hand"></div></td>
					</tr>
				</table>
</form>
<%
	int count;

	if ((String)request.getAttribute("count") == null)
	{
		count = 0;
	} else {
		count = Integer.parseInt((String)request.getAttribute("count"));
	}

	if (gubun.equals("1") )
	{

		userList = (HP_PAE_UserList)request.getAttribute("userList");

		if ((count%size2) == 0)
			totalPage = count/size2;
		else
			totalPage = count/size2 +1;
%>
<!-- 전체 조회 결과 -->
					<br>
					<table width="100%" border="0" cellspacing="1" cellpadding="2">
						<tr>
							<td>
<%
		if (count != 0) {
%>
								<div align="" class="fontp"> 총 문서 <%=count%>개 : [<img src="/img/page.gif" width="15" height="13">Page <%=npage%>/<%=totalPage%>]</div>
<%
		}
%>
							</td>
							<td>
								<div align="right"><img name="Image19" border="0" src="/img/bu_delete.gif" onClick="f_delete()" style="cursor:hand" width="56" height="20"></div>
							</td>
						</tr>
						<tr>
							<td height="5"> </td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tdbc" bordercolor="#FFFFFF">
<form name="search" method="post" action="/servlet/user.HP_PAV_UserDel">
						<tr>
							<td height="4" class="line" colspan="8"></td>
						</tr>
						<tr class="tdar">
							<td class="tdar"	width="5%">
								<div align="center"><input type="checkbox" name="allbox" onClick="change_stat()"></div>
							</td>
							<td class="tdar"	width="11%">
								<div align="center">이용자ID</div>
							</td>
							<td class="tdar"	width="16%">
								<div align="center">업체명</div>
							</td>
							<td class="tdar" width="11%">
								<div align="center">업체구분</div>
							</td>
							<td class="tdar"	width="16%">
								<div align="center">사업자등록번호</div>
							</td>
							<td class="tdar"	width="14%">
								<div align="center">본사전화번호</div>
							</td>
							<td class="tdar"	width="13%">
								<div align="center">신청자</div>
							</td>
							<td class="tdar"	width="12%">
								<div align="center">신청일자</div>
							</td>
						</tr>

<!--     유저 조회결과    -->
<%
		if (count == 0)
		{
%>
						<tr>
							<td colspan="8" class="tdb">검색된 결과가 없습니다.</td>
						</tr>
<%
		} else {
			for (int i = 0; i < userList.getTotalCount(); i++) {
				userList.next();
				HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
				userEB = userList.getUserEB();
%>
						<tr>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>"	width="5%">
								<div align="center"><input type="checkbox" name="checkbox" value="<%=userEB.getUserid()%>"></div>
							</td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>"	width="11%">
								<div align="center"><a href="JavaScript:f_modifyUser('<%=userEB.getUserid()%>')"><%=userEB.getUserid()%></a></div>
							</td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>"	width="16%"><%=userEB.getEnterprise()%></td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>"	width="11%">
								<div align="center"><%=userEB.getEntdivide().equals("1") ? "비축업체" : "기타업체"%></div>
							</td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>"	width="16%">
								<div align="center">
<%
				if (userEB.getBusinessno1() != null && userEB.getBusinessno2() != null && userEB.getBusinessno3() != null)
				{
					out.println(userEB.getBusinessno1() + "-" + userEB.getBusinessno2() + "-" + userEB.getBusinessno3());
				}
%>
								</div>
							</td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>"	width="14%">
								<div align="center">
<%
				if (userEB.getCorptel1() != null && userEB.getCorptel2() != null && userEB.getCorptel3() != null)
				{
					out.println(userEB.getCorptel1() + "-" + userEB.getCorptel2() + "-" + userEB.getCorptel3());
				}
%>
								</div>
							</td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>"	width="13%">
								<div align="center"><%=userEB.getUsername()%></div>
							</td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>"	width="12%">
								<div align="center"><%=userEB.getRegistdate().substring(0, 4)%>/<%=userEB.getRegistdate().substring(4, 6)%>/<%=userEB.getRegistdate().substring(6, 8)%></div>
							</td>
						</tr>
<%
			}
		}
%>
<!--     조회결과    -->

						<tr>
							<td height="2"  class="line" colspan="8"></td>
						</tr>
	<input type="hidden" name="deleteID">
	<input type="hidden" name="deletegubun" value="list">
	<input type="hidden" name="deletegubun2" value="company">
</form>
					</table>
					<br/>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
<form name="movePage" method="post" action="/servlet/user.HP_PAV_CompUserView">
	<input type="hidden" name="gubun" value="<%=gubun%>">
	<input type="hidden" name="enterprise" value="<%=enterprise%>">
	<input type="hidden" name="businessno1" value="<%=businessno1%>">
	<input type="hidden" name="businessno2" value="<%=businessno2%>">
	<input type="hidden" name="businessno3" value="<%=businessno3%>">
	<input type="hidden" name="entdivide" value="<%=entdivide%>">
	<input type="hidden" name="npage" value="<%=npage%>">
	<input type="hidden" name="size" value="<%=size%>">
</form>
						<tr>
							<td align="center"><div align="" class="fontp">
<%
		int tpage = Integer.parseInt(npage)/10;

		if (tpage != 0) out.print("<a href='JavaScript:f_pageMove(" + (tpage+1) + ")'>이전");

		for (int i = (1+ tpage); i <= (tpage+10); i++) {
			if (totalPage >= i)
				out.print(" [<a href='JavaScript:f_pageMove(" + i + ")'>" + i + "</a>] ");
		}


		if (((count/size2)+1)/10 > Integer.parseInt(npage)/10) out.print("<a href='JavaScript:f_pageMove(" + (tpage+11) + ")'>다음");
%>
							</div></td>
						</tr>
					</table>
			<!----- 테이블 끝------>
<%
	}
%>
				<br>
			</td>
		</tr>
		<tr>
			<td colspan="3" height="50">
				<%@ include file="/jsp/common/Footer.jsp" %>
			</td>
		</tr>
	</table>
</body>
</html>
