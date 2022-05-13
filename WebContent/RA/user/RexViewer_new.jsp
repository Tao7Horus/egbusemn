 <!--/***********************************************************************/
/*                                                                          */
/*    Program ID    :     RexViwer_02.jsp                                    */
/*                                                                          */
/*    description   :     시행문출력화면                                    */
/*                                                                          */
/****************************************************************************/
/*  최초생성         2002.08.07          배 수 진                           */
/*  최초생성         2002.08.19          오 창 렬                           */
/***************************************************************************/
/* Program ID         : RexViwer_02.jsp                        */
/* Program Explanation: Hiển thị report                      */
/* Program Summary  :   
/* Relation Program   : */ 
/*                     */
/* Table              : )                 */
/***************************************************************************/
/* Customizing Composer : MR. MINH 13.06.2009                       */
/***************************************************************************/

/**************************************************************************-->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*" %>
<%@ page import="common.*" %>
<jsp:useBean id="ctl" class="beans.UM_RAV_ConuB010c" scope="page" />

<%
	com.oreilly.servlet.ParameterParser psr = new com.oreilly.servlet.ParameterParser(request);

	String fileName			= psr.getStringParameter("fileName",			"");
    String titleName		= psr.getStringParameter("titleName",			"");
%>

<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<TITLE>Report</TITLE>
<link rel="stylesheet" type="text/css" href="../css/UM.css">
<link rel="stylesheet" type="text/css" href="http://muasamcong.mpi.gov.vn/css/footer.css">

</HEAD>
	<SCRIPT language="javaScript">

	function fnPreView()
	{

		try{
		    oReport = RexCtl.OpenReport("http://muasamcong.mpi.gov.vn:8070/mrd/HelloWorld.rex");
		    alert("oReport : "+oReport) ;
		}catch(e) {
			alert("Reporting Program is not installed.["+e.message+"]");
			window.open("http://www.g2b.go.kr/report/rex/help/rex_notice.html","notice","left=50, top=50, width=850, height=600, scrollbars=yes");
			return;
		}
		
			
	    if(oReport == null || oReport == "")
	    {
	        alert("can't open report file");
	        return;
	    }
	    else {

             alert("else") ;
             return
	    }
	    RexCtl.Run();
	    RexCtl.Zoom("wholepage");
	}

</SCRIPT>
<script src="http://muasamcong.mpi.gov.vn:8070/js/rexplugin.js"></script>
<body background="../img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="fnPreView();">
    <table width="823" border="0" cellpadding="0" cellspacing="0" height="100%"  background="../img/bg_sub.gif">
        <tr valign="bottom" height="57"> 
            <td rowspan="2"><img src="../img/sub_title_01.jpg">                                                                                   </td>
            <td colspan="2" background="../img/sub_title_02.jpg" width="788" height="57" class="HEADLINE">&nbsp; <!-- 경쟁입찰참가자격 등록 및 변경신청 업체명부 --> Danh sách nhà thầu xin đăng ký/thay đổi tư cách tham gia đấu thầu cạnh tranh </td>
        </tr>
        <tr height="7"> 
            <td colspan="2"><img src="../img/sub_title_03.jpg">    </td>
        </tr>
        <tr height="21"> 
            <td colspan="3" align="right">&nbsp;</td>
        </tr>
        <tr valign="top"> 
            <td width="35">    </td>
            <td width="760">
                <script src="http://muasamcong.mpi.gov.vn:8070/js/rexplugin.js"></script>
            </td>
            <td width="28"></td>
        </tr>
        <tr height="35">
            <td colspan="3"  align="center"></td>
        </tr>
        <tr height="*">
            <td colspan="3"><br></td>
        </tr>
        <tr> 
		   <td colspan="3" height="50"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></td> 
	    </tr>
    </table> 
</BODY>
</HTML>
