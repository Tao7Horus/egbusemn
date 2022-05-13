<%--
/**
 *  Báo cáo Danh sách gói thầu là đấu thầu điện tử mà nhà thầu đã tham gia
 *  @author: HUNGNT
 *  @version: 
 *  @since: 10/07/2011
 */
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="/jsp/common/jspToError.jsp?type=0&url=&message=" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, g2b.sso.*, common.*, LOGIN.*" %>
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />
<jsp:useBean id="UM_RAB_RecSupp" class="dao.UM_RAB_RecSupplier"  scope="page" />
<%
            SSO sso = new SSO(pageContext);
            sso.checkLogin();
            if (!sso.isLogin()) {
                return;
            }

            String saup_No = request.getParameter("saup_No") == null ? "" : request.getParameter("saup_No");

%>
<html>
    <head>       
        <title>Danh sách gói thầu là đấu thầu điện tử mà nhà thầu đã tham gia</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="../../css/TA.css">
        <link rel="stylesheet" type="text/css" href="http://muasamcong.mpi.gov.vn/css/footer.css">

        <script language="javascript" src="../js/UM.js"></script>
        <script language="javascript" src="../calendar/js/calendar.js"></script>
        <script language="javascript" src="http://muasamcong.mpi.gov.vn:8070/report/rexscript/rexpert.js"></script>
        <script language="javascript" src="http://muasamcong.mpi.gov.vn:8070/report/rexscript/rexpert_properties.js"></script>
        <script language="javascript" src="http://muasamcong.mpi.gov.vn:8070/js/rexplugin.js"></script>
        <script language="javascript">
            function suyoSearch2 ( a, b, c){
                s = window.open("/servlet/dao/EP_COV_GTQ951?isUse=Y&suyoCode=&suyoName=&a="+a+"+&b="+b+"&formName="+c ,"s" , "width=500 , height=555 , scrollbars=yes , ");
            }
            function print2() {
                var bizRegNo     = document.fm.saup_No.value;
                var instituName     = document.fm.instituName.value;
                var currentTime = new Date();

                if (instituName == '' && bizRegNo == ''){
                    alert('Số ĐKKD không được để trống!');
                    document.forms[0].saup_No.select();
                    document.forms[0].saup_No.foucs();
                    return false;
                } 
                var month = currentTime.getMonth() + 1;
                var day = currentTime.getDate();
                var year = currentTime.getFullYear();
                var date = day + "/" + month + "/" + year;
                var oRptMainParam;
                var countArr = "2";
                
                oRptMainParam = rex_GetgoDictionay();
                oRptMainParam.put("rex_rptname", "Danhsachgoithau");
                oRptMainParam.put("rex_datatype", "CSV");  // XML, CSV
                oRptMainParam.put("rex_userservice", "DEVL");
                //chuẩn bị dữ liệu add vào param
				
                oRptMainParam.put("bizRegNo", bizRegNo.toLowerCase().trim());
                oRptMainParam.put("instituName", instituName.toLowerCase().trim());
                oRptMainParam.put("dateParam", date);
                oRptMainParam.put("sumRecord", countArr);

                rex_gfRexRptOpen("popup", oRptMainParam);

            }

        </script>
    </head>
    <body>
        <div class="col-750 clearfix last">
            <h1 class="pageTitle"><i class="icon-title"></i>Danh sách gói thầu là đấu thầu điện tử mà nhà thầu đã tham gia</h1>

            <form name="fm">
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                    <tr><td height="4" colspan="4" class="line"></td></tr>
                    <tr>
                        <td width="15%" class="tdar">Số ĐKKD</td>
                        <td width="20%" class="tdb" height="24">
                            <input class="read" name="saup_No" id="saup_No" size="20" value="<%=saup_No%>"  onKeypress="isNumber(this);"  maxlength="13" >
                        </td>
                        <td width="20%" class="tda">Tên công ty</td>
                        <td width="40%" class="tdb" height="24">
                            <span class="fontbl style13">
                                <input name="instituName" type="text" id="instituName" value="" size="30" maxlength="50"/>
                                <input type="hidden" name="instituCode">
                            </span>
                            <input name="btnSearchInstitu" type="button" class="commonbutton"  value="T&igrave;m"  onClick="javascript:suyoSearch2('saup_No','instituName','fm');"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tdb" colspan="4" style="text-align: center">
                            <input type="button" class="commonbutton" value="Xem" onclick="javascript:print2();" style="cursor:hand">
                        </td>
                    </tr>
                    <tr><td height="2" colspan="4" class="line"></td></tr>
                </table>
                <br><br>
            </form>
            <div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
        </div>
    </body>
</html>
