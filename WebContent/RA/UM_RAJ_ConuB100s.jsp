<%--
/****************************************************************************/
/*																									*/
/*    Program ID    :     UM_RAJ_ConuB100s.jsp								*/
/*																									*/
/*    description   :     경쟁입찰참가자격등록증									*/
/*																									*/
/****************************************************************************/
/*  최초생성  2002.06.14  오창렬																						*/
/*  수      정  2004.10.01  윤태우	false2 추가 ,입찰참가자격이 없는 업체도 등록증을				*/
/*													출력할수 없도록 수정													*/
/*  수	  정  2004.11.23  윤태우  사업자등록번호란에 숫자만 들어가도록하는 스크립트 제거		*/
/*  수	  정  2008.11.20  신경운  법인 지사의 경우 경쟁입찰참가자격등록증 출력할수 없게처리 */
/*                                             false3 추가																		*/
/****************************************************************************/
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, g2b.sso.*, common.*, common.util.*, LOGIN.*" %>
<jsp:useBean id="ctl" class="beans.UM_RAB_ConuB100c" scope="page" />

<%
            try {

                String ID = "";
                String masterCode = "";
                String useGubun = "";
                String reqGubun = "";     // edi에서 호출한 경우 "EDI"
                String IP = "";

                reqGubun = request.getParameter("reqGubun") == null ? "" : request.getParameter("reqGubun");


                // IP = HttpUtility.getIP(request);

                //  if (!reqGubun.equals("EDI") || !(IP.startsWith("211.42.85.") || IP.startsWith("10.139.7."))) {

                SSO sso = new SSO(pageContext);
                sso.checkLogin();

                ID = sso.getID();
                masterCode = sso.getMCode();
                useGubun = sso.getGubun();

                // 조달업체 승인자
                UM_Auth_Check uac = new UM_Auth_Check(request, response);
                //  uac.checkCookie("ka",null,null);

                // }

                // ParameterParser psr = new ParameterParser(request);

                String saup_No = request.getParameter("saup_No") == null ? "" : request.getParameter("saup_No");
                //String ss           = psr.getStringParameter("ss",          "");
                // String flag         = psr.getStringParameter("flag",        "");

//	int branchgubun = ctl.getBranchGubun(saup_No);

%>

<html>
    <head>
        <title>Đơn xin đăng ký tư cách đấu thầu cạnh tranh</title>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
        <link href="http://muasamcong.mpi.gov.vn/css/TA.css" rel="stylesheet" type="text/css" />
        <script language="javascript" src="/calendar/js/calendar.js"></script>

        <SCRIPT LANGUAGE="JavaScript">
            <!--
            function isNumber(obj)
            {
                if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;
            }
            function print2() {
                var saup_No = document.fm.saup_No.value;
         
        
                if (saup_No.length < 1) {
                    alert("Hãy nhập số ĐKKD!");
                } else {
                    window.open("UM_RAJ_ConuA020i.jsp?bizRegNo=" + saup_No, "newWIn", "width=750, height=1000, top=10,left=10,scrollbars=yes, toolbar=yes, directories=yes");
                    // window.open("","id_confirm", "width=850,height=700 top=50 left=50, scrollbars=yes,resizable=yes");
                    //  document.fm.action='/jsp/AD/RexViewer.jsp?titleName=경쟁입찰참가자격 등록증 출력';
                    //  document.fm.action='/UM_RAJ_ConuB100s_print.jsp&saup_No=' + saup_No;
                    //   document.fm.target='id_confirm';
                    //    document.form1.submit();
                }
            }
   
            //-->
        </SCRIPT>
    </head>
    <body>
        <form name="fm" method="post" action="">
            <input type ="hidden" name="url" value="">

            <!----------------------------------------------------- 타이틀 테이블 시작 ----------------------------------------------------->
            <div class="col-750 clearfix last">
                <h1 class="pageTitle"><i class="icon-title"></i>Đơn đăng ký nhà thầu</h1>
                <table width="100%" border="0">
                    <tr>
                        <td height="4" class="LINE" colspan="3"></td>
                    </tr>
                    <tr class="tr">
                        <td width="20%" class='tdar'>Số ĐKKD</td>
                        <td width="60%" class="tdb" height="24">
                            <input class="read" name="saup_No" size="20" value="<%=saup_No%>"  onKeypress="isNumber(this);"  maxlength="13" >
                        </td>
                        <td width="20%" class="tdb" height="24">
                            <!--<img src="/img/bu_print.gif" align="absmiddle" style='cursor:hand' border=0 onclick="print1()">-->
                            <!-- <img src="/img/bu_print.gif" align="absmiddle" style='cursor:hand' border=0 onclick=" print2()"> -->
                            <input type="button" class="commonbutton" value="Xem" onclick="javascript:print2();" style="cursor:hand"/>
                        </td>

                    </tr>
                    <tr>
                        <td height="1" class="LINE" colspan="3"></td>
                    </tr>
                </table>
                <br>

                <div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
            </div>

            <!---------------------- 네비게이션 바 끝 ------------------------>
        </form>

    </body>
</html>

<%
            } catch (Exception e) {
                out.println("main error:" + e);
            }
%>
