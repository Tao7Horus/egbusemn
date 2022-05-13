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
                msg = "접근 권한이 없거나 부서 정보를 확인할 수 없습니다.";
	}

        if ( aso != null && !(aso.getBUSEO().equals("060") || aso.getBUSEO().equals("064")) ) { // 정보기획과, 정보관리과
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
	String gubun = request.getParameter("gubun") == null ? "" : request.getParameter("gubun") ;
	String usergubun = request.getParameter("usergubun") == null ? "" :  request.getParameter("usergubun");
	String searchgubun = request.getParameter("searchgubun") == null ? "" :  request.getParameter("searchgubun");
	String searchword = request.getParameter("searchword") == null ? "" :  request.getParameter("searchword");
	String npage = request.getParameter("npage") == null ? "1" : request.getParameter("npage");
	String size = request.getParameter("size") == null ? "10" : request.getParameter("size");

	int size2 = Integer.parseInt(size);

	int totalPage = 0;

	HP_PAE_UserList userList;
%>
<html>
<head>
<title>이용자 조회</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="/css/EP.css" type="text/css">
<script language="javascript" src="/js/userview.js"></script>
<script language="JavaScript">
function f_init()
{
	userview.npage.value = "<%=npage%>";
	userview.usergubun.value = "<%=usergubun%>";
	userview.searchgubun.value = "<%=searchgubun.equals("") ? "id" : searchgubun%>";
	userview.searchword.value = "<%=searchword%>";
}

function f_pageMove(chk)
{
	userview.npage.value = chk;
	movePage.npage.value = chk;
	movePage.submit();
}

function f_delete()
{
	if (confirm("선택한 이용자 정보를 삭제하시겠습니까?"))
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

function f_isNumber(str)
{
        var src = new String(str);
        var tar = true;
        var i, len=src.length;

        for (i=0; i < len; i++) {
                if ((src.charAt(i) < '0') || (src.charAt(i) > '9')) {
                        return false;
                }
        }
        return true;
}

function f_check()
{
	//if (userview.usergubun.value == "") {
	//	alert("이용자 구분을 선택하지 않으셨습니다.");
	//	userview.usergubun.focus();
	//	return false;
	//}
//	if (userview.searchword.value == null || userview.searchword.value == "") {
//		alert("검색어를 입력하지 않으셨습니다.");
//		userview.searchword.focus();
//		return false;
//	}
	if (userview.searchgubun.value == 'regdate' || userview.searchgubun.value == 'moddate') {
		if (userview.searchword.value.length != 8) {
			alert("날짜 입력은 아래의 양식으로 입력바랍니다.\n ex) 20040801");
			userview.searchword.focus();
			return;
		}
		if (f_isNumber(userview.searchgubun.value)) {
			alert("날짜의 입력은 숫자만 가능합니다.");
			userview.searchword.focus();
			return;
		}
	}
	if (userview.searchgubun.value == null || userview.searchgubun.value == "") {
		alert("검색 구분을 선택하지 않으셨습니다.");
		userview.searchgubun.focus();
		return false;
	}
	return true;
}


function f_modifyUser(id,chk)
{
	if (chk == 'A') {
		url = "/servlet/user.HP_PAV_GeneralChange?userid=" + id + "&gubun=mod";
		launchCenter(url, "이용자정보", 500, 820);
	} else if (chk == 'B') {
		url = "/servlet/user.HP_PAV_CompChange?userid=" + id + "&gubun=mod";
		launchCenter(url, "이용자정보", 710, 820);
	} else if (chk == 'C') {
		url = "/servlet/user.HP_PAV_LoginGovChange?userid=" + id + "&gubun=mod";
		launchCenter(url, "이용자정보", 650, 820);
	} else {
		url = "/servlet/user.HP_PAV_GeneralChange?userid=" + id + "&gubun=mod";
		launchCenter(url, "이용자정보", 500, 820);
	}
}

function f_submit()
{
	if (f_check()) {
		userview.npage.value = "1";
		userview.submit();
	}
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" topmargin="0" leftmargin="0" background="/img/bg01.gif" onLoad="f_init()">
	<table width="800" border="0" cellpadding="0" cellspacing="0" height="100%"  background="/img/bg_sub.gif">
<form name="userview" method="post" action="/servlet/user.HP_PAV_UserView">
	<input type="hidden" name="gubun" value="1">
	<input type="hidden" name="size" value="10">
	<input type="hidden" name="npage" value="1">
		<tr valign="bottom" height="57">
			<td rowspan="2" valign="top"><img src="/img/sub_title_01.jpg" width="35" height="64"></td>
			<td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE">
				&nbsp;&nbsp;이용자조회</td>
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
							<td height="4" class="line" colspan="2"></td>
						</tr>
						<tr>
							<td class="tdar" width="13%"><span class="red">*</span>&nbsp;이용자구분</td>
<%
	// 이용자 구분값이 없을 때
	if (usergubun == null || usergubun.equals(""))
	{
%>
							<td class="tdb">
								<input type="radio" name="usergubun" value="C">
								공공기관
								<input type="radio" name="usergubun" value="B">
								비축물자업체
								<input type="radio" name="usergubun" value="A">
								일반
								<input type="radio" name="usergubun" value="T" checked>
								전체
							</td>
<%
	} else {
%>
							<td class="tdb">
								<input type="radio" name="usergubun" value="C" <%=usergubun.equals("C") ? "checked" : ""%>>
								공공기관
								<input type="radio" name="usergubun" value="B" <%=usergubun.equals("B") ? "checked" : ""%>>
								비축물자업체
								<input type="radio" name="usergubun" value="A"  <%=usergubun.equals("A") ? "checked" : ""%>>
								일반
								<input type="radio" name="usergubun" value="T" <%=usergubun.equals("T") ? "checked" : ""%>>
								전체
							</td>
<%
	}
%>
						</tr>
						<tr>
							<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;검색구분</td>
							<td class="tdb">
								<select type=select name='searchgubun'>
									<option value='id' >이용자ID</option>
									<option value='name'>성명</option>
									<option value='business'>사업자등록번호</option>
									<option value='regdate'>등록일자</option>
									<option value='moddate'>변경일자</option>
								</select>
								<input class="read" name="searchword" maxlength="20" value="" size="30">
							</td>
						</tr>
						<tr>
							<td height="2"  class="line" colspan="2"></td>
						</tr>
						<tr>
							<td colspan="4"><div align="right"><img border="0" src="/img/bu_search.gif"  onClick="f_submit()" style="cursor:hand"></div></td>
						</tr>
					</table>
</form>
<%
	if (gubun.equals("1"))
	{
		int count = Integer.parseInt((String)request.getAttribute("count"));
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
								<div align="" class="fontp"> 총 문서 <%=count%>개 : [<img src="/img/page.gif" width="15" height="13">Page <%=npage%>/<%=totalPage%>]
								</div>
<%
		}
%>
							</td>
							<td><div align="right"><img name="Image19" border="0" src="/img/bu_delete.gif" onClick="f_delete()" style="cursor:hand"></div></td>
						</tr>
						<tr>
							<td height="5"></td>
						</tr>
					</table>
					<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tdbc" bordercolor="#FFFFFF">
<form name="search" method="post" action="/servlet/user.HP_PAV_UserDel">
						<tr>
							<td height="4" class="line" colspan="9"></td>
						</tr>
						<tr class="tdar">
							<td class="tdar"	width="3%">
								<div align="center"><input type="checkbox" name="allbox" onClick="change_stat()"></div>
							</td>
<!--
							<td class="tdar"	width="6%">
								<div align="center">번호</div>
							</td>
-->
							<td class="tdar"	width="12%">
								<div align="center">이용자ID</div>
							</td>
							<td class="tdar"	width="10%">
								<div align="center">성명</div>
							</td>
							<td class="tdar"	width="12%">
								<div align="center">구분</div>
							</td>
							<td class="tdar"	width="13%">
								<div align="center">사업자등록번호</div>
							</td>
							<td class="tdar"	width="15%">
								<div align="center">주민등록번호</div>
							</td>
							<td class="tdar"	width="11%">
								<div align="center">등록일자 </div>
							</td>
							<td class="tdar"	width="11%">
								<div align="center">수정일자 </div>
							</td>
							<td class="tdar"	width="6%">
								<div align="center">삭제</div>
							</td>
						</tr>

<!--     유저 조회결과    -->
<%
		if (count == 0)
		{
%>
						<tr>
							<td colspan="9" class="tdb">검색된 결과가 없습니다.</td>
						</tr>
<%
		} else {
			for (int i = 0; i < userList.getTotalCount(); i++) {
				userList.next();
				HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
				userEB = userList.getUserEB();

				String user = new String();
				if (userEB.getUsergubun().equals("A"))
					user = "일반";
				else if (userEB.getUsergubun().equals("B"))
					user = "비축물자업체";
				else
					user = "공공기관";
%>
						<tr>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>">
								<div align="center"><input type="checkbox" name="checkbox" value="<%=userEB.getUserid()%>"></div>
							</td>
<!--
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>">
								<div align="center"><%=(i+1)+(Integer.parseInt(npage)-1)*size2%></div>
							</td>
-->
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>"><a href="JavaScript:f_modifyUser('<%=userEB.getUserid()%>', '<%=userEB.getUsergubun()%>')" style="cursor:hand"><%=userEB.getUserid()%></div></td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>">
								<div align="center"><%=userEB.getUsername()%></div>
							</td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>">
								<div align="center"><%=user%></div>
							</td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>" align="center">
<%
				if (userEB.getBusinessno1() != null && userEB.getBusinessno2() != null && userEB.getBusinessno3() != null)
				{
					out.println(userEB.getBusinessno1() + "-" + userEB.getBusinessno2() + "-" + userEB.getBusinessno3());
				}
%>
							</td>

							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>">
								<div align="center"><%=userEB.getRegistno1()%>-XXXXXXX</div>
							</td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>">
								<div align="center"><%=userEB.getRegistdate()%></div>
							</td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>">
<%
							if (userEB.getModifydate() != null && ((userEB.getModifydate()).length() > 8)) {
%>
								<div align="center"><%=userEB.getModifydate()%></div>
<%
							} else {
%>
								<div align="center"></div>
<%
							}
%>
							</td>
							<td class="tdb<%=(i%2) == 0 ? "" : "c"%>">
								<div align="center"><%=userEB.getDelete() == null ? "N" : userEB.getDelete()%></div>
							</td>
						</tr>
<%
			}
		}
%>
<!--     조회결과    -->

						<tr>
							<td height="2"  class="line" colspan="9"></td>
						</tr>
	<input type="hidden" name="deleteID">
	<input type="hidden" name="deletegubun" value="list">
	<input type="hidden" name="deletegubun2" value="common">
</form>
					</table>
					<br/>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
<form name="movePage" method="post" action="/servlet/user.HP_PAV_UserView">
	<input type="hidden" name="gubun" value="<%=gubun%>">
	<input type="hidden" name="usergubun" value="<%=usergubun%>">
	<input type="hidden" name="searchgubun" value="<%=searchgubun%>">
	<input type="hidden" name="searchword" value="<%=searchword%>">
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
