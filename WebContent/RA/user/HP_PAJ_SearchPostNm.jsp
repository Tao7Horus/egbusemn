<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page errorPage="/jsp/common/jspToError.jsp?type=0&url=&message=" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, common.*, common.util.*" %>
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />
<jsp:useBean id="ctl" class="beans.UM_ADB_GovrF020p" scope="page" />
<jsp:useBean id="comUtil" class="common.util.CommUtil" scope="page" />

<%

	int totalCount = 0;
	int totalPage = 0;

	String addr = request.getParameter("addr") == null ? "" :  request.getParameter("addr");
	String postNm= request.getParameter("postNm") == null ? "" :  request.getParameter("postNm");
	String npage = request.getParameter("npage") == null ? "1" : request.getParameter("npage");
	String size = request.getParameter("size") == null ? "10" : request.getParameter("size");	

	String formname = request.getParameter("formname") == null ? "userInput" : request.getParameter("formname");	
	String post1 = request.getParameter("post1") == null ? "postno1" : request.getParameter("post1");	
	String post2 = request.getParameter("post2") == null ? "postno2" : request.getParameter("post2");	
	String addrname = request.getParameter("addrname") == null ? "address1" : request.getParameter("addrname");	
	String hpost = request.getParameter("hpost") == null ? "" : request.getParameter("hpost");		

	int size2 = Integer.parseInt(size);

	UM_ADE_GovrF030b[] ett = null;

	totalCount = ctl.Max_count(addr,postNm);

	// 검색
	if(totalCount !=0){
		totalCount = ctl.Max_count(addr,postNm);
		if(totalCount>0){
			ett = ctl.post_list(Integer.parseInt(npage), Integer.parseInt(size), addr, postNm);
		}
	}

	if ((totalCount%size2) == 0)
			totalPage = totalCount/size2;
		else 
			totalPage = totalCount/size2 +1;
%>
<html>
<head>
<title>우편번호검색</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" type="text/css" href="/css/UM.css">
<SCRIPT LANGUAGE="JavaScript">
function search(){
	if(document.postnm.addr.value==null || document.postnm.addr.value.length < 2) {
		alert("검색하실 주소를 입력하세요.");
		return;
	}
	document.postnm.target="_self";
	document.postnm.method="post";
	document.postnm.action="/jsp/RA/user/HP_PAJ_SearchPostNm.jsp";
	document.postnm.submit();
	return;
}

function search_enter() {
	if(event.keyCode ==13) {
		search();			
	}
}

function f_pageMove(chk)
{
	postnm.npage.value = chk;
	postnm.submit();
}

function f_inputPostNm(postnm, address)
{
	window.opener.<%=formname%>.<%=post1%>.value=(postnm.toString()).substring(0,3);
	window.opener.<%=formname%>.<%=post2%>.value=(postnm.toString()).substring(3);
<%
	if( !"".equals(addrname) ) {
%>	
	window.opener.<%=formname%>.<%=addrname%>.value=address;
<%
	}
%>		
	
<%
	if( !"".equals(hpost) ) {
%>	
	window.opener.<%=formname%>.<%=hpost%>.value=postnm.toString();
<%
	}
%>	
	window.close();
}
</SCRIPT>		
</head>

<body background=/img/pop_bg.gif topmargin=0 leftmargin=0 rightmargin=0 style=background-position:left; background-repeat:repeat-y; bgcolor=white onKeyDown="javascript:search_enter()">
	<table border=0 cellspacing=0 cellpadding=0 height=100% width=100%>
		<tr valign=bottom height=57>
			<td colspan=3>
				<table width=100% cellpadding=0 cellspacing=0 border=0 bgcolor=white>
					<tr height=51 valign=bottom>
						<td rowspan=2 width=30></td>
						<td width=5><img src='/img/pop_tit_dot.gif'></td>
						<td width=* class='HEADLINE' background='/img/pop_tit_t.jpg' style='background-position:right; background-repeat:no-repeat;'>&nbsp;우편번호 검색</td>
					</tr>
					<tr height=6>
						<td></td>
						<td background='/img/pop_tit_b.jpg' style='background-position:right; background-repeat:no-repeat;'></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr height=1><td colspan=3 background='/img/pop_tit_bg.gif'></td></tr>
		<tr height=25><td colspan=3></td>
		</tr>
		<tr valign=top>
			<td width=30></td><td width=*><center>
<form name="postnm">
				<table	border=0 cellpadding=2 cellspacing=1 width=100%>
					<tr height=4><td class=line></td></tr>
					<tr class="tdb"  align="center">
						<td align="center">찾고자하는 동, 읍, 면 이름을 입력하세요</td> 
					</tr>
					<tr class="tdb">
						<td align="center">	(예:  역삼동, 역삼1동, 단양읍)</td>
					</tr>
					<tr><td height="2" class="LINE"></td></tr>
				</table>
				<br>
				<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
					<tr><td height="2" class="LINE" colspan="2"></td></tr>
					<tr>
						<td class="tdar" width="15%">주소</td>
						<td class="tdb">

							<input type="text" name="addr" class="read" size=30 value="<%=addr%>">

							<a href="javascript:search();"><img src="/img/bu_refer.gif" align="absmiddle" border="0"></a>
						</td>			
					</tr>
					<tr><td height="1" class="LINE" colspan="2"></td></tr>
				</table>	
	<input type="hidden" name="size" value="10">
	<input type="hidden" name="npage" value="1">
	<input type="hidden" name="formname" value="<%=formname%>">
	<input type="hidden" name="post1" value="<%=post1%>">
	<input type="hidden" name="post2" value="<%=post2%>">
	<input type="hidden" name="addrname" value="<%=addrname%>">
	<input type="hidden" name="hpost" value="<%=hpost%>">
</form>
				<table width="100%">
					<tr height="25"> 
						<td class="tdac" align="center" width="70" >우편번호</td>
						<td class="tdac" align="center" width="340">주 소</td>
					</tr>

<!-- 우편번호 조회 결과 -->
<%	if (totalCount==0){ %>
    <tr>
        <td class="tdbc" colspan="2">조회결과가 없습니다.</td>	
    </tr>
<%	}	%>

<%
		if(totalCount > 0){
			for(int i = 0; i < ett.length; i++) { %>
				<tr class="<%=((i%2)==1) ? "tdcc" : "tdbc"%>"	> 
					<td><%=ComStr.divideComma(ett[i].getS01(), "-", 3)%></td>
					<td align="left">
						<a href="JavaScript:f_inputPostNm('<%=ett[i].getS01()%>', '<%=ett[i].getS02()%>')">&nbsp;&nbsp;<%=ett[i].getS02()%>&nbsp;<%=ett[i].getS04()%></a>		
					</td>
				</tr>
<%		
			}	
		} 
%>
<!-- 우편번호 조회 결과 -->

					<tr> 
					<td height="2" class="LINE" colspan="2"></td>
					</tr>
				</table>
				<br/>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td align="center"><div align="" class="fontp">
<%
		int tpage = Integer.parseInt(npage)/10;
		
		if (tpage != 0) out.print("<a href='JavaScript:f_pageMove(" + (tpage+1) + ")'>이전</a>");  

		for (int i = (1+ tpage); i <= (tpage+10); i++) {
			if (totalPage >= i)
				out.print(" [<a href='JavaScript:f_pageMove(" + i + ")'>" + i + "</a>] ");
		}
		
		
		if (((totalCount/size2)+1)/10 > Integer.parseInt(npage)/10) out.print("<a href='JavaScript:f_pageMove(" + (tpage+11) + ")'>다음");
%>
						</div></td>
					</tr>
				</table>
			</td>
			<td width=28></td>
		</tr>
		<tr height=10><td colspan=3></td></tr>
		<tr height=*><td colspan=3></td></tr>
		<tr>	
			<td colspan=3 height=45 width=*>

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
