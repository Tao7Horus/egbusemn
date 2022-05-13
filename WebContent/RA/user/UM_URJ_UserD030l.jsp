<%--
/****************************************************************************/
/*                                                                          */
/*    Program ID    :     UM_URJ_UserD030l.jsp								*/
/*                                                                          */
/*    description   :      공공기관사용자검색화면                                           */
							Màn hình tra cứu thông tin cơ bản chủ đầu tư    */
/*                                                                          */
/****************************************************************************/
/*  최초생성         2002.06.21          배 수 진                           */
/****************************************************************************/
/***************************************************************************/
/* Program ID         : UM_URJ_UserD030l.jsp                        */
/* Program Explanation: Màn hình tra cứu thông tin cơ bản chủ đầu tư        */
/* Program Summary    : Màn hình tra cứu thông tin cơ bản chủ đầu tư		*/
/* Relation Program   : */ 
/*                      */
/* Table              :                  */
/***************************************************************************/
/* Customizing Composer : MR. SonDN 27.05.2009               		        */
/***************************************************************************/

--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%//@ page errorPage="/jsp/common/jspToError.jsp?type=0&url=&message=001" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, servlets.ASO" %>
<%@ page import="g2b.sso.*" %>
<jsp:useBean id="ctl" class="beans.UM_URB_UserA030p" scope="page" />

<%
	SSO sso = new SSO(pageContext);
	String msg = null;

	ASO aso = null;
	

	sso.checkLogin();
	if ( !sso.isLogin()) {
	    return;
	}

	//try {
	//	aso = new ASO(request,response);
	//} catch ( Exception e ) {
	//	e.printStackTrace();
	//	msg = "Không có quyền kết nối hoặc không thể xem được thông tin login"; //msg = "접근 권한이 없거나 로그인 정보를 확인할 수 없습니다.";
	//}
	if (!"A000000000002".equals(sso.getID())  ) {
		 msg = "Không có quyền kết nối hoặc không thể xem được thông tin login ... " + sso.getID();
	}
	if ( msg != null ) {
		out.println("<html>");
		out.println("<script language=javascript>");
		out.println("   alert('"+msg+"');");
		out.println("   top.location.href='http://muasamcong.mpi.gov.vn:8070/loginNew.jsp';");
		out.println("</script>");
		out.println("</html>");
	}
%>

<%
	UM_URE_UserA020b[] ettlist = null; // Entity Bean

	int pagenum = Integer.parseInt(request.getParameter("pagenum")==null?"1":request.getParameter("pagenum"));	// 페이지번호
	String gov = (request.getParameter("gov")==null?"":request.getParameter("gov")).trim();	// 공공기관명 검색 조건
	String buseo = (request.getParameter("buseo")==null?"":request.getParameter("buseo")).trim();	// 담당부서 검색 조건
	String name = (request.getParameter("name")==null?"":request.getParameter("name")).trim();	//담당자명 검색 조건
	String gubun = (request.getParameter("gubun")==null?"all":request.getParameter("gubun")).trim();	//담당자명 검색 조건

	int PAGEMAX = 10;	// 한페이지에 출력되는 목록 수
	int INDEXMAX = 10;	// 한번에 출력되는 인덱스의 수
	int maxcnt = ctl.Max_count(gov, buseo, name, gubun);	// 검색조건으로 조회된 자료의 수
	int maxrow = 0;	// 한페이지에 출력될 목록 수 (마지막 페이지를 위하여)
	int maxindex = 0; // 한번에 출력되는 인덱스의 수 (마지막 인덱스 그룹을 위하여)

if (maxcnt>0){
	if(pagenum == (int)Math.ceil((double)maxcnt / PAGEMAX))	{
		if(maxcnt%PAGEMAX == 0)		{
			maxrow = PAGEMAX;
		}else{
			maxrow = (maxcnt%PAGEMAX);
		}
	}else{
		maxrow = PAGEMAX;
	}

	if((((int)Math.ceil((double)pagenum-1)/INDEXMAX)+1) == (int)Math.ceil((double)((double)maxcnt / PAGEMAX)/INDEXMAX))	{
		if((int)Math.ceil((double)maxcnt / PAGEMAX)%INDEXMAX == 0)
		{
			maxindex = INDEXMAX;
		}else{
			maxindex = (int)Math.ceil((double)maxcnt / PAGEMAX)%INDEXMAX;
		}
	}else{
		maxindex = INDEXMAX;
	}

	ettlist = ctl.select_userlist(pagenum, PAGEMAX, gov, buseo, name, gubun);	// 조회 Bean 호출
}
%>

<html>

<%="<!-- 총페이지수  -->Số trang= " + (int)Math.ceil((double)maxcnt / PAGEMAX)%>
<%="<!-- 현재페이지번호 -->Trang số = " + pagenum%>
<%="<!-- 총자료수 -->Số lượng = " + maxcnt%>
<%="<!-- 현재페이지 자료수  --> Số record= " + maxrow%>
<%="<!-- 총인덱스수  -->Trang= " + (int)Math.ceil((double)((double)maxcnt / PAGEMAX)/INDEXMAX)%>
<%="<!-- 현재인덱스번호 --> = " + (((int)Math.ceil((double)pagenum-1)/INDEXMAX)+1)%>
<%="<!-- 현재인덱스 페이지수 --> Chỉ mục = " + maxindex%>

<head>
<title><!--공공기관 사용자 검색  -->Tìm kiếm người dùng chủ đầu tư</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/css/UM.css">
<script language="javascript">
<!--
// 조회 SCRIPT
function search() {
	document.forms[0].pagenum.value="1";
	document.forms[0].submit();
}
-->
</script>
</head>
<body>
<form name="fm" method="post" action="UM_URJ_UserD030l.jsp">
<input type="hidden" name="pagenum" value="<%=pagenum%>">
<div class="col-750 clearfix last">
	<h1 class="pageTitle"><i class="icon-title"></i>Tìm kiếm người dùng chủ đầu tư</h1>

 
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
          <tr> 
            <td height="4" colspan="4" class="line"></td>
          </tr>
		  <tr>
            <td class="tdar" width="20%">
            <p><!-- 공공기관명 -->Cơ quan công</p>
            </td>
            <td class="tdb" width="30%"><input type="text" name="gov" value="<%=gov%>" maxlength="25" class="read"></td>
            <td class="tdar" width="20%">
            <p><!--사용자ID  -->ID người dùng</p>
            </td>
            <td class="tdb"> 
            <input type="text" name="name" value="<%=name%>" maxlength="13" class="read">
            </td>
          </tr>
          <tr>
            <td class="tdar" width="20%">
            <p><!--담당부서 -->Phòng ban phụ trách</p>
            </td>
            <td class="tdb" width="30%">
            <input type="text" name="buseo" value="<%=buseo%>" maxlength="25" class="read">
            </td>
            <td class="tdar" width="20%">
            <p><!-- 사용자구분 -->Phân loai người dùng</p>
            </td>
            <td class="tdb" width="30%">
              <table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr> 
                  <td class="tdb" width="30%">
                    <select name="gubun">
						<option <%=gubun.equals("all")?"selected ":""%>value="all">공공기관 관련자 전체<!--Chi nhánh busan  --></option>
						<option value="g" <%=gubun.equals("g")?"selected":""%>>(g)공공기관<!--Chi nhánh keum  --></option>
						<option value="a" <%=gubun.equals("a")?"selected":""%>>(a)업체승인자<!--Chi nhánh tekuem  --></option>
						<option value="k" <%=gubun.equals("k")?"selected":""%>>(k)기관승인자<!--Chi nhánh jeju  --></option>
						<option value="h" <%=gubun.equals("h")?"selected":""%>>(h)보증사<!--Chi nhánh bundoc  --></option>
						<option value="m" <%=gubun.equals("m")?"selected":""%>>(m)실적관리<!--Chi nhánh keru  --></option>
						<option value="p" <%=gubun.equals("p")?"selected":""%>>(p)포탈관리자<!--Chi nhánh Jouru  --></option>
						<option value="s" <%=gubun.equals("s")?"selected":""%>>(s)고객서비스<!--Chi nhánh kangghou  --></option>
						<option value="t" <%=gubun.equals("t")?"selected":""%>>(t)통합공고<!--Chi nhánh mihsou  --></option>
						<option value="y" <%=gubun.equals("y")?"selected":""%>>(y)협회<!--Chi nhánh saokeu  --></option>
                    </select>
                  </td>
                  <td>
                    <a href="javascript:search();" onfocus="blur()"><img src="/img/bu_refer.gif" align="right"  border=0></a>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
          <tr> 
            <td height="2" colspan="5" class="line"></td>
          </tr>			
          </table>
          <table width="100%" border="0" cellpadding="2" cellspacing="1" class="tr">
            <tr> 
              <td class="page">[ <img src="/img/page.gif" align="absmiddle"> Page <%=pagenum%>/<%=(int)Math.ceil((double)maxcnt / PAGEMAX)%> ]</td>
            </tr>
          </table>
          <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
            <tr> 
              <td height="4" colspan="4" class="line"></td>
            </tr>
            <tr>
              <td class="tdac" width="25%">            
              <p><!-- 사용자ID -->ID người dùng</p>
            </td>	
            <td class="tdac" width="25%">
            <p><!--공공기관명  -->Tên cơ quan công</p>
            </td>
            <td class="tdac" width="25%">
            <p><!--부서명  -->Tên phòng ban</p>
            </td>
            <td class="tdac" width="25%">
            <p><!-- 담당자명 -->Tên người phụ trách</p>
            </td>
          </tr>
<%	
// 조회결과가 없을경우 메세지 출력후 종료
if (maxcnt==0){
%>
          <tr>
            <td class="tdbc" colspan="4">
            <p><!-- 조회결과가 없습니다. 조회항목을 점검하세요. -->Không có kết quả tra cứu. Hãy kiểm tra lại hạng mục tra cứu.</p>
            </td>	
          </tr>
          <tr> 
            <td height="2" colspan="5" class="line"></td>
          </tr>
        </table>	
<%
return;
}%>

<%
// 조회결과가 있을경우 리스트 출력
	for(int i=0;i<=ettlist.length-1;i++){ %>
    <tr class="<%=((i%2)==1)?"tdcc":"tdbc"%>">
        <td>
			<p><a href="/AR/UM_ARJ_AdmrA010m.jsp?id=<%=ettlist[i].getUserId()%>" onfocus="blur()"><%=ettlist[i].getUserId()%></a></p>
		</td>	
        <td  >
			<p><%=ettlist[i].getGovName()%></p>
		</td>
        <td >
            <p><%=ettlist[i].getUserPost()%></p>
        </td>
        <td >
			<p><%=ettlist[i].getUserName()%></p>
		</td>
    </tr>
<% } %>
          <tr> 
            <td height="2" colspan="5" class="line"></td>
          </tr>
</table>
        <!----------------------  페이지 표시 테이블 시작 ------------------------>
        <table width="100%" border="0" cellpadding="2" cellspacing="0" class="tr">
          <tr> 
            <td align="center" height="15"></td>
          </tr>
          <tr> 
            <td height="5" align="center">
<%
if (pagenum==1){
%>
			<img src='/img/bu_start_gray.gif' border=0 alt='시작' align=absmiddle></a>&nbsp;
<% }else { %>
			<a href="UM_URJ_UserD030l.jsp?pagenum=1&gov=<%=gov%>&buseo=<%=buseo%>&name=<%=name%>&gubun=<%=gubun%>" title="처음" onfocus="blur()"><img src='/img/bu_start_gray.gif' border=0 alt='시작' align=absmiddle></a>&nbsp;
<% } %>
<%
// 처음 페이지면 이전 버튼의 링크를 걸지 않음
if ((pagenum-1)/INDEXMAX > 0){
%>
			&nbsp;<a href="UM_URJ_UserD030l.jsp?pagenum=<%=((int)Math.ceil((double)(pagenum-1))/INDEXMAX)*INDEXMAX+1-INDEXMAX%>&gov=<%=gov%>&buseo=<%=buseo%>&name=<%=name%>&gubun=<%=gubun%>" onfocus="blur()"><%}%>&nbsp;<!-- [이전 10개]  -->[10 trang trước]... <%if (pagenum-1 != 0){%></a>&nbsp; 
<% } %>

<%for(int j=((int)Math.ceil((double)(pagenum-1))/INDEXMAX)*INDEXMAX+1  ;  j<=maxindex-1+((int)Math.ceil((double)(pagenum-1))/INDEXMAX)*INDEXMAX+1  ;  j++){
		if (pagenum == j){%>
			<span class="pagelink">[<%=j%>]</span>
		<% }if (pagenum != j){%>
			<a href="UM_URJ_UserD030l.jsp?pagenum=<%=j%>&gov=<%=gov%>&buseo=<%=buseo%>&name=<%=name%>&gubun=<%=gubun%>" title="<%=j%>" class="page01" onfocus="blur()">[<%=j%>]</a> 
<% }} %>

<%
// 마지막 페이지면 다음 버튼의 링크를 걸지 않음
if ((pagenum-1)/INDEXMAX < (int)Math.ceil((double)maxcnt / PAGEMAX)/INDEXMAX){
%>
			&nbsp;<a href="UM_URJ_UserD030l.jsp?pagenum=<%=((int)Math.ceil((double)(pagenum-1))/INDEXMAX)*INDEXMAX+1+INDEXMAX%>&gov=<%=gov%>&buseo=<%=buseo%>&name=<%=name%>&gubun=<%=gubun%>" onfocus="blur()" onfocus="blur()"><%}%>... <!-- [다음 10개]  -->[10 trang sau]&nbsp;<%if (pagenum != (int)Math.ceil((double)maxcnt / PAGEMAX)){%></a>&nbsp;
<% } %>
<%
if (pagenum==(int)Math.ceil((double)maxcnt / PAGEMAX)){
%>
			<img src='/img/bu_finish.gif' border=0 alt='끝' align=absmiddle>
<% }else { %>
			<a href="UM_URJ_UserD030l.jsp?pagenum=<%=(int)Math.ceil((double)maxcnt / PAGEMAX)%>&gov=<%=gov%>&buseo=<%=buseo%>&name=<%=name%>&gubun=<%=gubun%>" title="끝" onfocus="blur()"><img src='/img/bu_finish.gif' border=0 alt='끝' align=absmiddle></a>
<% } %>
    </tr>
        </table> 
        <!----------------------  페이지 표시 테이블 끝 ------------------------>
     <div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
</div>
</form>
</body>

</html> 
