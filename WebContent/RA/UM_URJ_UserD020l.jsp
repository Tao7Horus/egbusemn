 <!--/************************************************************************/
/*                                                                          */
/*    Program ID    :     UM_URJ_UserD020l.jsp								*/
/*                                                                          */
/*    description   :     사용자 목록 조회 화면								*/
/*                                                                          */
/****************************************************************************/
/*  최초생성         2002.06.21          배 수 진                           */
/**************************************************************************-->
<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*" %>
<jsp:useBean id="ctl" class="beans.UM_URB_UserA030p" scope="page" />

<%
UM_URE_UserA020b[] ettlist = null; // Entity Bean

int pagenum = Integer.parseInt(request.getParameter("pagenum")==null?"1":request.getParameter("pagenum"));	// 페이지번호
String a = (request.getParameter("a")==null?"":request.getParameter("a")).trim();	// '사용자ID'값을 출력할 opener 페이지의 첫번째 inputbox 이름
String b = (request.getParameter("b")==null?"":request.getParameter("b")).trim();	// '공공기관명'값을 출력할 opener 페이지의 두번째 inputbox 이름
String c = (request.getParameter("c")==null?"":request.getParameter("c")).trim();	// '부서명'값을 출력할 opener 페이지의 세번째 inputbox 이름
String d = (request.getParameter("d")==null?"":request.getParameter("d")).trim();	// '담당자명'값을 출력할 opener 페이지의 네번째 inputbox 이름
String form = (request.getParameter("form")==null?"":request.getParameter("form")).trim();	// 선택값을 출력할 opener 페이지의 form 이름
String gov = (request.getParameter("gov")==null?"":request.getParameter("gov")).trim();	// 공공기관명 검색 조건
String buseo = (request.getParameter("buseo")==null?"":request.getParameter("buseo")).trim();	// 담당부서 검색 조건
String name = (request.getParameter("name")==null?"":request.getParameter("name")).trim();	//담당자명 검색 조건

int PAGEMAX = 10;	// 한페이지에 출력되는 목록 수
int INDEXMAX = 10;	// 한번에 출력되는 인덱스의 수
int maxcnt = ctl.Max_count(gov, buseo, name);	// 검색조건으로 조회된 자료의 수
int maxrow = 0;	// 한페이지에 출력될 목록 수 (마지막 페이지를 위하여)
int maxindex = 0; // 한번에 출력되는 인덱스의 수 (마지막 인덱스 그룹을 위하여)

if(pagenum == (int)Math.ceil((double)maxcnt / PAGEMAX))
{
	if(maxcnt%PAGEMAX == 0)
	{
		maxrow = PAGEMAX;
	}else{
		maxrow = (maxcnt%PAGEMAX);
	}
}else{
	maxrow = PAGEMAX;
}

if((((int)Math.ceil((double)pagenum-1)/INDEXMAX)+1) == (int)Math.ceil((double)((double)maxcnt / PAGEMAX)/INDEXMAX))
{
	if((int)Math.ceil((double)maxcnt / PAGEMAX)%INDEXMAX == 0)
	{
		maxindex = INDEXMAX;
	}else{
		maxindex = (int)Math.ceil((double)maxcnt / PAGEMAX)%INDEXMAX;
	}
}else{
	maxindex = INDEXMAX;
}

ettlist = ctl.select_userlist(pagenum, PAGEMAX, gov, buseo, name);	// 조회 Bean 호출
%>
<html>
<!--
<%="총페이지수 = " + (int)Math.ceil((double)maxcnt / PAGEMAX)%>
<%="현재페이지번호 = " + pagenum%>
<%="총자료수 = " + maxcnt%>
<%="현재페이지 자료수 = " + maxrow%>
<%="총인덱스수 = " + (int)Math.ceil((double)((double)maxcnt / PAGEMAX)/INDEXMAX)%>
<%="현재인덱스번호 = " + (((int)Math.ceil((double)pagenum-1)/INDEXMAX)+1)%>
<%="현재인덱스 페이지수 = " + maxindex%>
-->
<head>
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<title>공공기관 사용자 검색</title>
<link rel="stylesheet" type="text/css" href="/css/UM.css">
<script language=javascript>
<!--
// 조회 SCRIPT
function search() {
	document.forms[0].pagenum.value="1";
	document.forms[0].submit();
}

// 부모창의 INPUT BOX에 ID값 입력하는 SCRIPT
function addParent(str1,str2,str3,str4){
	<%if (!a.equals("")){%> opener.document.<%=form%>.<%=a%>.value = str1;<%}%>
	<%if (!b.equals("")){%> opener.document.<%=form%>.<%=b%>.value = str2;<%}%>
	<%if (!c.equals("")){%> opener.document.<%=form%>.<%=c%>.value = str3;<%}%>
	<%if (!d.equals("")){%> opener.document.<%=form%>.<%=d%>.value = str4;<%}%>
	window.close();
}
-->
</script>
</head>

<body bgcolor="#FFFFFF" leftmargin="10" topmargin="10" marginwidth="0" marginheight="0">

<!---------------------- 타이틀 테이블 시작 ------------------------>
<table width="100%" border="0" cellpadding="0">
    <tr> 
        <td class="titleb">공공기관 사용자 검색</td>
    </tr>
</table>
<table width="100%" cellpadding="2" cellspacing="2">
    <tr> 
        <td align="center" background="/img/dotlines02.gif" height="1"></td>
    </tr>
    <tr> 
        <td align="center" background="/img/dotlines02.gif" height="1"></td>
    </tr>
</table>
<!---------------------- 타이틀 테이블 끝 ------------------------>

<br>

<form name="fm" method="post" action="UM_URJ_UserD020l.jsp">
<input type="hidden" name="pagenum" value="<%=pagenum%>">
<input type="hidden" name="a" value="<%=a%>">
<input type="hidden" name="b" value="<%=b%>">
<input type="hidden" name="c" value="<%=c%>">
<input type="hidden" name="d" value="<%=d%>">
<input type="hidden" name="form" value="<%=form%>">
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
	<tr>
		<td class="tdar" width="15%">
			<p>공공기관명</p>
		</td>
		<td class="tdb" width="35%"><input type="text" name="gov" value="<%=gov%>" maxlength="25" class="read"></td>
	</tr>
	<tr>
		<td class="tdar" width="15%">
			<p>담당부서</p>
		</td>
		<td class="tdb" width="35%"><input type="text" name="buseo" value="<%=buseo%>" maxlength="25" class="read"></td>
	</tr>
	<tr>
		<td class="tdar" width="15%">
            <p>담당자명</p>
		</td>
		<td class="tdb" width="35%">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr> 
					<td class="tdb"> 
						<input type="text" name="name" value="<%=name%>" maxlength="10" class="read">
					</td>
					<td>
						<a href="javascript:search();" onfocus="blur()"><img src="/img/bu_refer.gif" align="right"  border=0></a>
					</td>
		        </tr>
			</table>
		</td>
    </tr>
</table>	

<br>

<table width="100%" border="0" cellpadding="2" cellspacing="1" class="tr">
	<tr> 
		<td class="page">[ <img src="/img/page.gif" align="absmiddle"> Page <%=pagenum%>/<%=(int)Math.ceil((double)maxcnt / PAGEMAX)%> ]</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
    <tr>
        <td class="tdac" width="25%">            
            <p>사용자ID</p>
		</td>	
        <td class="tdac" width="25%">
            <p>공공기관명</p>
        </td>
        <td class="tdac" width="25%">
            <p>부서명</p>
        </td>
        <td class="tdac" width="25%">
			<p>담당자명</p>
		</td>
    </tr>
<%	
// 조회결과가 없을경우 메세지 출력후 종료
if (maxcnt==0){
%>
    <tr>
        <td class="tdbc" colspan="4">
			<p>조회결과가 없습니다. 조회항목을 점검하세요.</p>
		</td>	
    </tr>
</table>	
<%
return;
}%>

<%
// 조회결과가 있을경우 리스트 출력
	for(int i=0;i<=maxrow-1;i++){ %>
    <tr>
        <td class="<%=((i%2)==1)?"tdcc":"tdbc"%>">
			<p><a href="javascript:addParent('<%=ettlist[i].getUserId()%>','<%=ettlist[i].getGovCode()%>','<%=ettlist[i].getUserPost()%>','<%=ettlist[i].getUserName()%>')" onfocus="blur()"><%=ettlist[i].getUserId()%></a></p>
		</td>	
        <td class="<%=((i%2)==1)?"tdcc":"tdbc"%>">
			<p><%=ettlist[i].getGovCode()%></p>
		</td>
        <td class="<%=((i%2)==1)?"tdcc":"tdbc"%>">
            <p><%=ettlist[i].getUserPost()%></p>
        </td>
        <td class="<%=((i%2)==1)?"tdcc":"tdbc"%>">
			<p><%=ettlist[i].getUserName()%></p>
		</td>
    </tr>
<% } %>
</table>
<!---------------------- 네비게이션 바 시작 ------------------------>
<table width="100%" cellpadding="2" cellspacing="2">
    <tr> 
		<td align="center" background="/img/dotlines.gif" height="1"></td>
    </tr>
    <tr> 
		<td align="center" class="redc">
			<a href="UM_URJ_UserD020l.jsp?pagenum=1&gov=<%=gov%>&buseo=<%=buseo%>&name=<%=name%>&a=<%=a%>&b=<%=b%>&c=<%=c%>&d=<%=d%>&form=<%=form%>" title="처음" onfocus="blur()">&lt;&lt;</a>&nbsp;


<%
// 처음 페이지면 이전 버튼의 링크를 걸지 않음
if ((pagenum-1)/INDEXMAX > 0){
%>
			&nbsp;<a href="UM_URJ_UserD020l.jsp?pagenum=<%=((int)Math.ceil((double)(pagenum-1))/INDEXMAX)*INDEXMAX+1-INDEXMAX%>&gov=<%=gov%>&buseo=<%=buseo%>&name=<%=name%>&a=<%=a%>&b=<%=b%>&c=<%=c%>&d=<%=d%>&form=<%=form%>" onfocus="blur()"><%}%><img src="/img/bu_backarw.gif" align="absmiddle" border="0"><%if (pagenum-1 != 0){%></a>&nbsp; 
<% } %>

<%for(int j=((int)Math.ceil((double)(pagenum-1))/INDEXMAX)*INDEXMAX+1  ;  j<=maxindex-1+((int)Math.ceil((double)(pagenum-1))/INDEXMAX)*INDEXMAX+1  ;  j++){
		if (pagenum == j){%>
			[<%=j%>]
		<% }if (pagenum != j){%>
			<a href="UM_URJ_UserD020l.jsp?pagenum=<%=j%>&gov=<%=gov%>&buseo=<%=buseo%>&name=<%=name%>&a=<%=a%>&b=<%=b%>&c=<%=c%>&d=<%=d%>&form=<%=form%>" title="<%=j%>" onfocus="blur()">[<%=j%>]</a> 
<% }} %>

<%
// 마지막 페이지면 다음 버튼의 링크를 걸지 않음
if ((pagenum-1)/INDEXMAX < (int)Math.ceil((double)maxcnt / PAGEMAX)/INDEXMAX){
%>
			&nbsp;<a href="UM_URJ_UserD020l.jsp?pagenum=<%=((int)Math.ceil((double)(pagenum-1))/INDEXMAX)*INDEXMAX+1+INDEXMAX%>&gov=<%=gov%>&buseo=<%=buseo%>&name=<%=name%>&a=<%=a%>&b=<%=b%>&c=<%=c%>&d=<%=d%>&form=<%=form%>" onfocus="blur()"><%}%><img src="/img/bu_nextarw.gif" align="absmiddle" border="0"><%if (pagenum != (int)Math.ceil((double)maxcnt / PAGEMAX)){%></a>&nbsp;
<% } %>
			<a href="UM_URJ_UserD020l.jsp?pagenum=<%=(int)Math.ceil((double)maxcnt / PAGEMAX)%>&gov=<%=gov%>&buseo=<%=buseo%>&name=<%=name%>&a=<%=a%>&b=<%=b%>&c=<%=c%>&d=<%=d%>&form=<%=form%>" title="끝" onfocus="blur()">&gt;&gt;</a> 
    </tr>
    <tr> 
		<td align="center" background="/img/dotlines.gif" height="1"></td>
    </tr>
</table>
<!---------------------- 네비게이션 바 끝 ------------------------>
</form>
</body>
</html>
