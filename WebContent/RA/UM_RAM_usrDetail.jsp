<%--
/************************************************************************/
/*                                                                      */
/*    Program ID    :     UM_RAM_usrDetail.jsp							*/
/*                                                                      */
/*    description   :     공공기관마스터에 해당하는 사용자리스트정보	*/
/*                                                                      */
/************************************************************************/
/*  최초생성         2003.12.09          민 정							*/
/************************************************************************/
--%>

<%@page language="java" %>
<%@page contentType="text/html; charset=EUC-KR" %>
<%@ page errorPage="/jsp/common/jspToError.jsp?type=0&url=&message=" %>

<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, common.*, common.util.*, LOGIN.*" %>

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

<jsp:useBean id="ctl"	class="beans.UM_RAM_RcvCodeList" scope="page" />
<jsp:useBean id="comUtil" class="common.util.CommUtil" scope="page" />
<jsp:useBean id="comWeb" class="common.WebUtil" scope="page" />
<jsp:useBean id="comSer" class="common.util.CommServer" scope="page" />
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />
<%
    // 권한체크
    //UM_Auth_Check uac=new UM_Auth_Check(request, response);
    //uac.checkCookie("k",null,null);

	String govCode = "";	//공공기관코드
    govCode = comUtil.retSpace(request.getParameter("govCode"));
   
    int totalcnt = 0;
	UM_RAM_UsrInfo[] ettlist = null;
	totalcnt = ctl.Usrcount(govCode);

	if (totalcnt > 0){
        ettlist = ctl.usrDetail(govCode);
	}
%>
<html> 
<head>
<title>송신대상사용자리스트</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" type="text/css" href="/css/UM.css">
<script language="javascript" src="/js/UM.js"></script>

<base target="ViewContent">
</head>
				
<body background="/img/bg01.gif" text="#3C3C3C" topmargin="0" leftmargin="0" marginwidth="0" marginheight="0">
<table width="822" border=0 cellpadding="0" cellspacing="0" >
<tr>
<td colspan="4" height="10"></td>
</tr>
	
<!---------시작 ------->
	<tr valign=top>
	  <td width="38" height="140"></td>		
	  <td width="760" height="140">		
       <table width=100% cellspacing="1" cellpadding="2" class="tr" border="0">
		<tr valign=top>
                <td  height="2" class="line" colspan="7" width="754"></td>
                 </tr>
			<tr>
				<td class="tdac" width="32">번호</td>
				<td class="tdac" width="122">사용자 ID</td>
				<td class="tdac" width="73">사용자명</td>
				<td class="tdac" width="111">부서명</td>
				<td class="tdac" width="119">전화번호</td>
				<td class="tdac" width="118">팩스번호</td>
				<td class="tdac" width="149">담당자메일주소</td>
			</tr>
			<tr height=1><td colspan="7" class="line" width="754"></td></tr>
   <% if ( totalcnt > 0 ) {
        for ( int i=0; i < totalcnt; i++) { %>						
			<tr>
				<td class=tdcc width="32"><%=i+1%></td>
				<td class=tdcc width="122"><%=ettlist[i].getusrID()%></td>
				<td class=tdcc width="73"><%=ettlist[i].getusrName()%></td>
				<td class=tdcc width="111"><%=ettlist[i].getpartName()%></td>
				<td class=tdcc width="119"><%=ettlist[i].getTelNumber()%></td>
				<td class=tdcc width="118"><%=ettlist[i].getFAXNumber()%></td>
				<td class=tdcc width="149"><%=ettlist[i].getmailAdd()%></td>
			</tr>
   <%    } 
      }else {          %>
			<tr>
			   <td class="tdbc" colspan="7">조회결과가 없습니다.</td>	
		    </tr>
   <% }                 %>
			<tr valign=center>
               <td  height="2" class="line" colspan="7" width="754"></td>
            </tr>
		</table>			
		</td>
        <td width="28" height="140"></td>
    </tr>
	<tr height=23>
		<td colspan=3 width="795" height="17"></td>
            <td width="27" height="17"></td>
	</tr>
    <tr> 
      <td colspan="3" height="50"><%@ include file="/jsp/common/Footer.jsp" %></td>
    </tr>

</table>
</html>
