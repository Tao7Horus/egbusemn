<%--
/******************************************************************************************/
/*                                                                                        */
/*    Program ID    :     UM_RAJ_IncuA040s.jsp                                            */
/*                                                                                        */
/*    description   :     목록화요청아이디검색화면	    								  */
/*                                                                                        */
/******************************************************************************************/
/*  최초생성         2002.06.12          오 창 렬                                         */
/******************************************************************************************/
	2003.04.14	김영진	1.프로그램 정리
--%>

<%@ page contentType="text/html; charset=EUC-KR" %>
<%@page import="common.*" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*" %>
<jsp:useBean id="ctl" class="beans.UM_RAB_IncuA050c" scope="page" />
<%
	//Parmeter 선언 부분--------------------------------------
	ParameterParser psr = new ParameterParser(request);
	// -------------------------------------------------------
	String userId       = psr.getStringParameter("userId", "");
	String ID_Confirm   = "" ;
	String Del          = "";
    String flag         = "";
    
    flag = userId.substring(0,1);
	UM_RAV_IncuA060b  ett = null;
	
	ett=ctl.select_id(userId);
    
    ID_Confirm = ett.getS01();
    
    if (flag.equals("A") || flag.equals("B") || flag.equals("c") || flag.equals("C") || flag.equals("D") || flag.equals("E")) {
        ID_Confirm = "1";
    }
%>
<html>
<head>
<title>아이디검색결과</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" type="text/css" href="/css/UM.css">

<SCRIPT LANGUAGE="JavaScript">
	function fClick(Del){
		parent.opener.userIDconfirm(Del);
		parent.close();
	}
</SCRIPT>

</head>
<body background=/img/pop_bg.gif topmargin=0 leftmargin=0 rightmargin=0 style=background-position:left; background-repeat:repeat-y; bgcolor=white>
 <table border=0 cellspacing=0 cellpadding=0 height=100% width=100%>
    <tr valign=bottom height=57>
       <td colspan=3>
         <table width=100% cellpadding=0 cellspacing=0 border=0 bgcolor=white>
           <tr height=51 valign=bottom>
             <td rowspan=2 width=30></td>
             <td width=5><img src='/img/pop_tit_dot.gif'></td>
             <td width=* class='HEADLINE' background='/img/pop_tit_t.jpg' style='background-position:right; background-repeat:no-repeat;'>&nbsp;아이디검색결과</td>
           </tr><tr height=6>
       <td></td>
      <td background='/img/pop_tit_b.jpg' style='background-position:right; background-repeat:no-repeat;'></td>
    </tr>
</table>
		</td></tr>
<tr height=1><td colspan=3 background='/img/pop_tit_bg.gif'></td></tr>
<tr height=25><td colspan=3></td>
</tr><tr valign=top>
<td width=30></td><td width=*>
	<table width="80%" align=center>
		<tr>
			<td class="tdbc">
				<%	if (ID_Confirm.equals("0")) {%>
					<%=userId%>는(은) 사용 <b><font color="#ff0033">가능한</font></b> 아이디입니다.	
				<%	} else {%>
					<%=userId%>는(은) 사용 <b><font color="#ff0033">불가능한</font></b> 아이디입니다.	
				<%	}%>
			</td>
		</tr>
	</table>
<br> 
	<table width="80%" align="center">
		<tr> 
			<td><p align=center><a href ="javascript:fClick(<%=ID_Confirm%>);"><img src="/img/bu_ok.gif" align="absmiddle"  border=0></a></p></td>
		</tr>
	</table>

</td><td width=28></td></tr>
<tr height=10><td colspan=3></td></tr>
<tr height=*><td colspan=3></td></tr>
<tr><td colspan=3 height=45 width=*>

<table width=100% cellpadding=0 cellspacing=0 border=0 background='/img/pop_bottom_back.gif' height=45>
<tr>
<td background='/img/pop_bottom.gif' style='background-position:right; background-repeat:no-repeat;'></td>
</tr></table>

</td></tr></table>
</body>
</html>
