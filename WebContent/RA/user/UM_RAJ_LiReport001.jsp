<%
/************************************************************************/
/*                                                                      */
/*    Program ID    :     UM_RAJ_LiReport001.jsp						*/
/*                                                           			*/
/*    description   :     공공기관공인인증서발급정보        
Thông tin cấp GCN công cho CQNN            */
/*                                                                      */
/************************************************************************/
/*  최초생성         2002.08.21          오 창 렬                       */
/************************************************************************/
	
//	2003.04.26	김영진	프로그램수정
//	2009.05.12 SONNQ

/***************************************************************************/
/* Program ID         : UM_RAJ_LiReport001.jsp                        */
/* Program Explanation: Thông tin cấp GCN công cho CQNN                     */
/* Program Summary    : Thông tin cấp GCN công cho CQNN	*/
/* Relation Program   : */ 
/*                      */
/* Table              :                  */
/***************************************************************************/
/* Customizing Composer : MR. SonDN 18.04.2009                       */
/***************************************************************************/

%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="/jsp/common/jspToError.jsp?type=0&url=&message=001" %>

<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, common.*" %>
<%@page import="com.oreilly.servlet.*" %>

<%
	com.oreilly.servlet.ParameterParser psr = new com.oreilly.servlet.ParameterParser(request);

	String G2BCODE		=   psr.getStringParameter("G2BCODE",				"");
	String fileName		=   psr.getStringParameter("fileName",		        	"");
%>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<TITLE>Report</TITLE>
<link rel="stylesheet" type="text/css" href="../css/UM.css">
<link rel="stylesheet" type="text/css" href="../css/TA.css">
</HEAD>
<script language="VBscript">
	sub window_onload()
   	    Rdviewer.FileOpen "http://www.g2b.go.kr:8070/mrd/license001.mrd", "/rf [C:\G2B\prt\license.txt]"
	end sub
</script> 

<script language="JavaScript">
    function openHelpWin(){
		var theURL="http://www.g2b.go.kr:8070/html/help/installCert.htm";
		window.open(theURL,'helpWin','toolbar=yes,location=no,status=yes,menubar=yes,scrollbars=yes,resizable=no,width=600,height=400');
		return;
	}
</script> 

<body background="../img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="javascript:openHelpWin();">
    <table width="823" border="0" cellpadding="0" cellspacing="0" height="100%"  background="../img/bg_sub.gif">
        <tr valign="bottom" height="57"> 
            <td rowspan="2"><img src="../img/sub_title_01.jpg">                                                                   </td>
            <td colspan="2" background="../img/sub_title_02.jpg" width="788" height="57" class="HEADLINE">
            	&nbsp;<!--공공기관 공인인증서비스 신청 -->      Đăng ký dịch vụ chứng nhận công </td>
        </tr>
        <tr height="7"> 
            <td colspan="2"><img src="../img/sub_title_03.jpg">																	</td>
        </tr>
        <tr height="21"> 
            <td colspan="3">																									</td>
        </tr>
        <tr valign="top"> 
            <td width="35">																										</td>
            <td width="760">
				<object classid="clsid:7D9A42C6-4114-11D4-AF7B-00A024C32DBA" id="Rdviewer" codebase="http://www.ebiz.go.kr:8085/xmldoc/control/rdviewer25.cab#version=2,5,0,348" width="760" height="1100"></object>
            </td>
            <td width="28">											                                                           </td>
        </tr>
        <tr height="35">
            <td colspan="3">		    				                                                                       </td>
        </tr>
        <tr height="*">
            <td colspan="3">	    														                                   </td>
        </tr>
        <tr> 
            <td colspan="3" height="50"><%//@ include file="/jsp/common/Footer.jsp" %></td>
        </tr>
    </table> 
</BODY>
</HTML>
