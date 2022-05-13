<%@page contentType = "text/html; charset=EUC-KR"
        errorPage   = "/jsp/common/jspToError.jsp?type=0&url=&message="
        language    = "java"
        info        = ""
%>
<%--
 ***************************************************************************************************
    1.시	스 템 명 : 운영자관리															
    2.프로그램 ID :
    3.프로그램 명 : 
    4.작	 성	  일 : 
    5.작	 성	  자 : 
    6.개		  요 :
 ***************************************************************************************************
    일자            버전        작성자	변경사항
 *************************************************************************************************** 

 *************************************************************************************************** 
--%>
<%@page import="g2b.sso.*"%>
<%@page import="java.util.*, java.net.URLDecoder, java.sql.*"%>
<%@page import="common.Trx, common.Log, common.ComStr, common.LoginLog"%>
<%@page import="java.io.*, java.lang.*,servlets.*,servlets.ASO " %>
<html>
    <head>
        <title>Untitled Document</title>
        <meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
    </head>
    <frameset rows="100,*" frameborder="NO" border="0" framespacing="0">
        <frame src="http://www.g2b.go.kr:8070/html/ra/top.htm" name="topFrame" scrolling="NO" noresize >
        <frameset cols="176,*" frameborder="NO" border="0" framespacing="0"> 
            <frame src="/jsp/RA/new_Master_LeftMenu.jsp" name="leftFrame" scrolling="NO" noresize>       
            <frame src="/html/ra/main.htm" name="main"><!--메인화면-->            
        </frameset>
    </frameset>
</html>
