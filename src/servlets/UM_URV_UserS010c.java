// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.io.PrintWriter;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_URV_UserS010c extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        final PrintWriter out = res.getWriter();
        Trx trx = null;
        Connection con = null;
        final Connection con2 = null;
        final Connection con3 = null;
        final Connection con4 = null;
        PreparedStatement psmt1 = null;
        PreparedStatement psmt2 = null;
        PreparedStatement psmt3 = null;
        PreparedStatement psmt4 = null;
        PreparedStatement psmt5 = null;
        PreparedStatement psmt6 = null;
        PreparedStatement psmt7 = null;
        PreparedStatement psmt8 = null;
        final PreparedStatement psmt9 = null;
        final PreparedStatement psmt10 = null;
        ResultSet rs = null;
        String sql = null;
        boolean b = false;
        final String date1 = null;
        final String date2 = null;
        String year1 = null;
        String year2 = null;
        String month1 = null;
        String month2 = null;
        String day1 = null;
        String day2 = null;
        final String hour1 = null;
        final String hour2 = null;
        final String min1 = null;
        final String min2 = null;
        String txtDate1 = "";
        String txtDate2 = "";
        String cnew = "";
        String cmod = "";
        String gnew = "";
        String gmod = "";
        String cunew = "";
        String cumod = "";
        String gunew = "";
        String gumod = "";
        String tcnew = "";
        String tcmod = "";
        String tgnew = "";
        String tgmod = "";
        String tcunew = "";
        final String tcumod = "";
        String tgunew = "";
        final String tgumod = "";
        String creg = "";
        String tcreg = "";
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            con.setAutoCommit(false);
            year1 = req.getParameter("SYear");
            if (year1 == null || year1 == "") {
                year1 = "";
            }
            else if (year1 != null || year1 != "") {
                txtDate1 = year1;
            }
            month1 = req.getParameter("SMonth");
            if (month1 == null || month1 == "") {
                month1 = "";
            }
            else if (month1 != null || month1 != "") {
                txtDate1 = String.valueOf(txtDate1) + month1;
            }
            day1 = req.getParameter("SDay");
            if (day1 == null || day1 == "") {
                day1 = "";
            }
            else if (day1 != null || day1 != "") {
                txtDate1 = String.valueOf(txtDate1) + day1;
            }
            year2 = req.getParameter("EYear");
            if (year2 == null || year2 == "") {
                year2 = "";
            }
            else if (year2 != null || year2 != "") {
                txtDate2 = year2;
            }
            month2 = req.getParameter("EMonth");
            if (month2 == null || month2 == "") {
                month2 = "";
            }
            else if (month2 != null || month2 != "") {
                txtDate2 = String.valueOf(txtDate2) + month2;
            }
            day2 = req.getParameter("EDay");
            if (day2 == null || day2 == "") {
                day2 = "";
            }
            else if (day2 != null || day2 != "") {
                txtDate2 = String.valueOf(txtDate2) + day2;
            }
            sql = "select count(*) from ??????_?????????????????? where ??????_???????????? = '1'   and to_char(????????????, 'YYYYMMDD')       between ? and ?";
            psmt1 = con.prepareStatement(sql);
            psmt1.setString(1, txtDate1);
            psmt1.setString(2, txtDate2);
            rs = psmt1.executeQuery();
            if (rs.next()) {
                cnew = rs.getString(1);
            }
            if (psmt1 != null) {
                try {
                    psmt1.close();
                }
                catch (Exception ex) {}
            }
            sql = "select count(*) from ??????_?????????????????? where ??????_???????????? = '1' ";
            psmt1 = con.prepareStatement(sql);
            rs = psmt1.executeQuery();
            if (rs.next()) {
                tcnew = rs.getString(1);
            }
            if (psmt1 != null) {
                try {
                    psmt1.close();
                }
                catch (Exception ex2) {}
            }
            sql = "select count(*) from ??????_?????????????????? where ??????_???????????? = '2'   and to_char(????????????, 'YYYYMMDD')       between ? and ?";
            psmt2 = con.prepareStatement(sql);
            psmt2.setString(1, txtDate1);
            psmt2.setString(2, txtDate2);
            rs = psmt2.executeQuery();
            if (rs.next()) {
                cmod = rs.getString(1);
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex3) {}
            }
            sql = "select count(*) from ??????_?????????????????? where ??????_???????????? = '2' ";
            psmt2 = con.prepareStatement(sql);
            rs = psmt2.executeQuery();
            if (rs.next()) {
                tcmod = rs.getString(1);
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex4) {}
            }
            sql = "select count(*) from ((select ????????????????????? from ??????_?????????????????????  where to_char(????????????, 'YYYYMMDD')       between ? and ?) minus (select ????????????????????? from ??????_???????????? where ????????????='07'))";
            psmt2 = con.prepareStatement(sql);
            psmt2.setString(1, txtDate1);
            psmt2.setString(2, txtDate2);
            rs = psmt2.executeQuery();
            if (rs.next()) {
                creg = rs.getString(1);
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex5) {}
            }
            sql = "select count(*) from ((select ????????????????????? from ??????_?????????????????????) minus (select ????????????????????? from ??????_???????????? where ????????????='07'))";
            psmt2 = con.prepareStatement(sql);
            rs = psmt2.executeQuery();
            if (rs.next()) {
                tcreg = rs.getString(1);
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex6) {}
            }
            sql = "select count(*) from ??????_????????????????????? where to_char(????????????,'YYYYMMDD')       between ? and ?";
            psmt3 = con.prepareStatement(sql);
            psmt3.setString(1, txtDate1);
            psmt3.setString(2, txtDate2);
            rs = psmt3.executeQuery();
            if (rs.next()) {
                gnew = rs.getString(1);
            }
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex7) {}
            }
            sql = "select count(*) from ((select ?????????????????? from ??????_?????????????????????) minus (select ?????????????????? from ??????_????????????????????? where ?????????????????? like 'ZY%'))";
            psmt3 = con.prepareStatement(sql);
            rs = psmt3.executeQuery();
            if (rs.next()) {
                tgnew = rs.getString(1);
            }
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex8) {}
            }
            sql = "select count(*) from ??????_????????????????????? where to_char(????????????,'YYYYMMDD')       between ? and ?";
            psmt4 = con.prepareStatement(sql);
            psmt4.setString(1, txtDate1);
            psmt4.setString(2, txtDate2);
            rs = psmt4.executeQuery();
            if (rs.next()) {
                gmod = rs.getString(1);
            }
            if (psmt4 != null) {
                try {
                    psmt4.close();
                }
                catch (Exception ex9) {}
            }
            sql = "select count(*) from ??????_????????????????????? ";
            psmt4 = con.prepareStatement(sql);
            rs = psmt4.executeQuery();
            if (rs.next()) {
                tgmod = rs.getString(1);
            }
            if (psmt4 != null) {
                try {
                    psmt4.close();
                }
                catch (Exception ex10) {}
            }
            sql = "select count(*) from ??????_????????? where ???????????????='c'   and ????????????='2'   and to_char(??????????????????,'YYYYMMDD')        between ? and ?";
            psmt5 = con.prepareStatement(sql);
            psmt5.setString(1, txtDate1);
            psmt5.setString(2, txtDate2);
            rs = psmt5.executeQuery();
            if (rs.next()) {
                cunew = rs.getString(1);
            }
            if (psmt5 != null) {
                try {
                    psmt5.close();
                }
                catch (Exception ex11) {}
            }
            sql = "select count(*) from ??????_????????? where ???????????????='c'   and ????????????='2' ";
            psmt5 = con.prepareStatement(sql);
            rs = psmt5.executeQuery();
            if (rs.next()) {
                tcunew = rs.getString(1);
            }
            if (psmt5 != null) {
                try {
                    psmt5.close();
                }
                catch (Exception ex12) {}
            }
            sql = "select count(distinct a.?????????ID) from ??????_????????? a, ??????_??????????????? b where a.?????????ID=b.?????????ID   and a.???????????????='c'   and a.????????????='2'   and to_char(b.????????????,'YYYYMMDD')       between ? and ? ";
            psmt6 = con.prepareStatement(sql);
            psmt6.setString(1, txtDate1);
            psmt6.setString(2, txtDate2);
            rs = psmt6.executeQuery();
            if (rs.next()) {
                cumod = rs.getString(1);
            }
            if (psmt6 != null) {
                try {
                    psmt6.close();
                }
                catch (Exception ex13) {}
            }
            sql = "select count(*) from ??????_????????? where ???????????????='g'   and ????????????='2'   and to_char(??????????????????,'YYYYMMDD')       between ? and ?";
            psmt7 = con.prepareStatement(sql);
            psmt7.setString(1, txtDate1);
            psmt7.setString(2, txtDate2);
            rs = psmt7.executeQuery();
            if (rs.next()) {
                gunew = rs.getString(1);
            }
            if (psmt7 != null) {
                try {
                    psmt7.close();
                }
                catch (Exception ex14) {}
            }
            sql = "select count(*) from ??????_????????? where ???????????????='g'   and ????????????='2' ";
            psmt7 = con.prepareStatement(sql);
            rs = psmt7.executeQuery();
            if (rs.next()) {
                tgunew = rs.getString(1);
            }
            if (psmt7 != null) {
                try {
                    psmt7.close();
                }
                catch (Exception ex15) {}
            }
            sql = "select count(distinct a.?????????ID) from ??????_????????? a, ??????_??????????????? b where a.?????????ID=b.?????????ID   and a.????????????='2'   and a.???????????????='g'   and to_char(b.????????????,'YYYYMMDD')       between ? and ?";
            psmt8 = con.prepareStatement(sql);
            psmt8.setString(1, txtDate1);
            psmt8.setString(2, txtDate2);
            rs = psmt8.executeQuery();
            if (rs.next()) {
                gumod = rs.getString(1);
            }
            if (psmt8 != null) {
                try {
                    psmt8.close();
                }
                catch (Exception ex16) {}
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<META HTTP-EQUIV=\"Pragma\" CONTENT=\"No-Cache\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/UM.css\">");
            out.println("<title>Untitled Document</title>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=euc-kr\">");
            out.println("<script language=\"JavaScript\" src=\"/js/stat2.js\"></script>");
            out.println("<script Language=JavaScript>");
            out.println("<!--");
            out.println("function search(){");
            out.println("var a = grpmgrview();");
            out.println("if (a==0) {document.forms[0].submit()}");
            out.println("}");
            out.println("//-->");
            out.println("</script>");
            out.println("</head>");
            out.println("<body background=\"/img/bg01.gif\" text=\"#3C3C3C\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
            out.println("<FORM method=\"post\" name=\"fm\" action=\"/servlet/servlets.UM_URV_UserS010c\">");
            out.println("<table width=\"823\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\"  background=\"/img/bg_sub.gif\">");
            out.println("    <tr valign=\"bottom\" height=\"57\"> ");
            out.println("      <td rowspan=\"2\"><img src=\"/img/sub_title_01.jpg\"></td>");
            out.println("      <td colspan=\"2\" background=\"/img/sub_title_02.jpg\" width=\"788\" height=\"57\" class=\"HEADLINE\"> ");
            out.println("        &nbsp; ??????????????? ??????</td>");
            out.println("    </tr>");
            out.println("    <tr height=\"7\"> ");
            out.println("      <td colspan=\"2\"><img src=\"/img/sub_title_03.jpg\"></td>");
            out.println("    </tr>");
            out.println("    <tr height=\"21\"> ");
            out.println("      <td colspan=\"3\"></td>");
            out.println("    </tr>");
            out.println("    <tr valign=\"top\"> ");
            out.println("      <td width=\"35\"></td>");
            out.println("      <td width=\"760\">");
            out.println("<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"2\" class=\"tr\">");
            out.println("    <tr> ");
            out.println("        <td height=\"4\" class=\"line\"></td>");
            out.println("    </tr>          ");
            out.println("    <tr> ");
            out.println("        <td class=\"tdb\" align=\"center\">");
            final ComboDate startdate = new ComboDate();
            b = startdate.GetCombo("S", "DAY", 0);
            if (b) {
                for (int i = 0; i < startdate.ComboYear.size(); ++i) {
                    out.println(" " + startdate.ComboYear.elementAt(i) + " ");
                }
                out.println(" <font face=\"??????\" size=\"2\"> ");
                out.println("??? </font>");
                for (int i = 0; i < startdate.ComboMonth.size(); ++i) {
                    out.println(" " + startdate.ComboMonth.elementAt(i) + " ");
                }
                out.println(" <font face=\"??????\" size=\"2\"> ");
                out.println("??? </font>");
                for (int i = 0; i < startdate.ComboDay.size(); ++i) {
                    out.println(" " + startdate.ComboDay.elementAt(i) + " ");
                }
                out.println(" <font face=\"??????\" size=\"2\"> ");
                out.println("??? </font>&nbsp;&nbsp;~&nbsp;&nbsp;");
            }
            b = startdate.GetCombo("E", "DAY", 0);
            if (b) {
                for (int i = 0; i < startdate.ComboYear.size(); ++i) {
                    out.println(" " + startdate.ComboYear.elementAt(i) + " ");
                }
                out.println(" <font face=\"??????\" size=\"2\"> ");
                out.println("??? </font>");
                for (int i = 0; i < startdate.ComboMonth.size(); ++i) {
                    out.println(" " + startdate.ComboMonth.elementAt(i));
                }
                out.println(" <font face=\"??????\" size=\"2\"> ");
                out.println("??? </font>");
                for (int i = 0; i < startdate.ComboDay.size(); ++i) {
                    out.println(" " + startdate.ComboDay.elementAt(i) + " ");
                }
                out.println(" <font face=\"??????\" size=\"2\"> ");
                out.println("??? </font>");
            }
            out.println("<A href=javascript:search() onfocus=\"blur()\"><img src=\"/img/bu_ok.gif\" border=\"0\" align=\"absmiddle\"></A>");
            out.println("        </td>");
            out.println("    </tr>");
            out.println("    <tr> ");
            out.println("        <td height=\"2\"  class=\"line\"></td>");
            out.println("    </tr>");
            out.println("</table>");
            out.println("         <br> ");
            out.println("        <table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"2\" class=\"tr\">");
            out.println("         <tr> ");
            out.println("            <td height=\"4\" colspan=\"4\" class=\"line\"></td>");
            out.println("          </tr>");
            out.println("         <tr>");
            out.println("            <td class=\"tdac\" width=\"50%\" colspan=\"2\">");
            out.println("                ????????????");
            out.println("            </td>");
            out.println("            <td class=\"tdac\" width=\"50%\" colspan=\"2\">");
            out.println("                ????????????");
            out.println("            </td>");
            out.println("        </tr>");
            out.println("         <tr>");
            out.println("            <td class=\"tdbr\" width=\"25%\">");
            out.println("            <p>????????????/??????????????????</p>");
            out.println("            </td>");
            out.println("            <td class=\"tdbr\" width=\"25%\">" + cnew + " / " + tcnew + " ???");
            out.println("            </td>");
            out.println("            <td class=\"tdbr\" width=\"25%\">");
            out.println("            <p>????????????/????????????</p>");
            out.println("            </td>");
            out.println("            <td class=\"tdbr\" width=\"25%\">" + gnew + " / " + tgnew + " ???");
            out.println("            </td>");
            out.println("        </tr>");
            out.println("         <tr>");
            out.println("            <td class=\"tdcr\" width=\"25%\">");
            out.println("            <p>????????????/??????????????????</p>");
            out.println("            </td>");
            out.println("            <td class=\"tdcr\" width=\"25%\">" + cmod + " / " + tcmod + " ???");
            out.println("            </td>");
            out.println("            <td class=\"tdcr\" width=\"25%\">");
            out.println("            <p>????????????/????????????</p>");
            out.println("            </td>");
            out.println("            <td class=\"tdcr\" width=\"25%\">" + gmod + " / " + tgmod + " ???");
            out.println("            </td>");
            out.println("        </tr>");
            out.println("         <tr>");
            out.println("            <td class=\"tdbr\" width=\"25%\">");
            out.println("            <p>????????????/????????????</p>");
            out.println("            </td>");
            out.println("            <td class=\"tdbr\" width=\"25%\">" + creg + " / " + tcreg + " ???");
            out.println("            </td>");
            out.println("            <td class=\"tdbr\" width=\"25%\">");
            out.println("            </td>");
            out.println("            <td class=\"tdbr\" width=\"25%\">");
            out.println("            </td>");
            out.println("        </tr>");
            out.println("          <tr> ");
            out.println("            <td height=\"2\" colspan=\"4\" class=\"line\"></td>");
            out.println("          </tr>");
            out.println("        </table>");
            out.println("         <br> ");
            out.println("        <table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"2\" class=\"tr\">");
            out.println("         <tr> ");
            out.println("            <td height=\"4\" colspan=\"4\" class=\"line\"></td>");
            out.println("          </tr>");
            out.println("         <tr>");
            out.println("            <td class=\"tdac\" width=\"50%\" colspan=\"2\">");
            out.println("                ???????????? ?????????");
            out.println("            </td>");
            out.println("            <td class=\"tdac\" width=\"50%\" colspan=\"2\">");
            out.println("                ???????????? ?????????");
            out.println("            </td>");
            out.println("        </tr>");
            out.println("         <tr>");
            out.println("            <td class=\"tdbr\" width=\"25%\">");
            out.println("            <p>???????????????/?????????????????????</p>");
            out.println("            </td>");
            out.println("            <td class=\"tdbr\" width=\"25%\">" + cunew + " / " + tcunew + " ???");
            out.println("            </td>");
            out.println("            <td class=\"tdbr\" width=\"25%\">");
            out.println("            <p>???????????????/?????????????????????</p>");
            out.println("            </td>");
            out.println("            <td class=\"tdbr\" width=\"25%\">" + gunew + " / " + tgunew + " ???");
            out.println("            </td>");
            out.println("        </tr>");
            out.println("         <tr>");
            out.println("            <td class=\"tdcr\" width=\"25%\">");
            out.println("            <p>???????????????/?????????????????????</p>");
            out.println("            </td>");
            out.println("            <td class=\"tdcr\" width=\"25%\">" + cumod + " / " + tcunew + " ???");
            out.println("            </td>");
            out.println("            <td class=\"tdcr\" width=\"25%\">");
            out.println("            <p>???????????????/?????????????????????</p>");
            out.println("            </td>");
            out.println("            <td class=\"tdcr\" width=\"25%\">" + gumod + " / " + tgunew + " ???");
            out.println("            </td>");
            out.println("        </tr>");
            out.println("          <tr> ");
            out.println("            <td height=\"2\" colspan=\"4\" class=\"line\"></td>");
            out.println("          </tr>");
            out.println("        </table>");
            out.println("      </td>");
            out.println("      <td width=\"28\"></td>");
            out.println("    </tr>");
            out.println("  </table> ");
            out.println("      </td>");
            out.println("      <td width=\"28\"></td>");
            out.println("    </tr>");
            out.println("  </table> ");
            out.println("<script Language=JavaScript>");
            out.println("<!--");
            out.println("   var P = document.forms[0];");
            if (year1 != "") {
                out.println("   P.SYear.value=\"" + year1 + "\"");
            }
            if (month1 != "") {
                out.println("   P.SMonth.value=\"" + month1 + "\"");
            }
            if (day1 != "") {
                out.println("   P.SDay.value=\"" + day1 + "\"");
            }
            if (year2 != "") {
                out.println("   P.EYear.value=\"" + year2 + "\"");
            }
            if (month2 != "") {
                out.println("   P.EMonth.value=\"" + month2 + "\"");
            }
            if (day2 != "") {
                out.println("   P.EDay.value=\"" + day2 + "\"");
            }
            out.println("//-->");
            out.println("</script>");
            out.println("</FORM>");
            out.println("</body>");
            out.println("</html>");
        }
        catch (SQLException exc) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_URV_UserS010c SQLException : Transaction Rollback?????? SQLException ?????????");
                Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_URV_UserS010c SQLException : ");
            Log.debug("Exception?????? ?????? : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
        }
        catch (Exception exc2) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_URV_UserS010c Exception : Transaction Rollback?????? Exception ?????????");
                Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_URV_UserS010c Exception : ");
            Log.debug("Exception?????? ?????? : " + exc2.toString());
            exc2.printStackTrace();
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex17) {}
            }
            if (psmt1 != null) {
                try {
                    psmt1.close();
                }
                catch (Exception ex18) {}
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex19) {}
            }
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex20) {}
            }
            if (psmt4 != null) {
                try {
                    psmt4.close();
                }
                catch (Exception ex21) {}
            }
            if (psmt5 != null) {
                try {
                    psmt5.close();
                }
                catch (Exception ex22) {}
            }
            if (psmt6 != null) {
                try {
                    psmt6.close();
                }
                catch (Exception ex23) {}
            }
            if (psmt7 != null) {
                try {
                    psmt7.close();
                }
                catch (Exception ex24) {}
            }
            if (psmt8 != null) {
                try {
                    psmt8.close();
                }
                catch (Exception ex25) {}
            }
            if (psmt9 != null) {
                try {
                    psmt9.close();
                }
                catch (Exception ex26) {}
            }
            if (psmt10 != null) {
                try {
                    psmt10.close();
                }
                catch (Exception ex27) {}
            }
            if (con != null) {
                try {
                    con.close();
                }
                catch (Exception ex28) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex29) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex30) {}
        }
        if (psmt1 != null) {
            try {
                psmt1.close();
            }
            catch (Exception ex31) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex32) {}
        }
        if (psmt3 != null) {
            try {
                psmt3.close();
            }
            catch (Exception ex33) {}
        }
        if (psmt4 != null) {
            try {
                psmt4.close();
            }
            catch (Exception ex34) {}
        }
        if (psmt5 != null) {
            try {
                psmt5.close();
            }
            catch (Exception ex35) {}
        }
        if (psmt6 != null) {
            try {
                psmt6.close();
            }
            catch (Exception ex36) {}
        }
        if (psmt7 != null) {
            try {
                psmt7.close();
            }
            catch (Exception ex37) {}
        }
        if (psmt8 != null) {
            try {
                psmt8.close();
            }
            catch (Exception ex38) {}
        }
        if (psmt9 != null) {
            try {
                psmt9.close();
            }
            catch (Exception ex39) {}
        }
        if (psmt10 != null) {
            try {
                psmt10.close();
            }
            catch (Exception ex40) {}
        }
        if (con != null) {
            try {
                con.close();
            }
            catch (Exception ex41) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex42) {}
        }
    }
}
