// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import common.util.CommServer;
import beans.Common_ComBo;
import common.WebUtil;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_GovrR090 extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        throw new Error("Unresolved compilation problems: \n\tThe type CommUtil is ambiguous\n\tThe type CommonMessage is ambiguous\n\tThe type CommonMessage is ambiguous\n");
    }
    
    private void headHtml(final PrintWriter out, final String desformat, final String province) throws Exception {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>기관별집계표</title>");
        out.println("<meta http-equiv=Content-Type content=\"text/html; charset=euc-kr\">");
        if (!desformat.equalsIgnoreCase("EXCEL")) {
            out.println("<LINK REL=STYLESHEET TYPE=text/css HREF=/css/EP.css>");
        }
        else {
            final WebUtil cssStype = new WebUtil();
            out.println(cssStype.cssStype());
        }
        out.println("</head>");
        out.println("<body bgcolor=white text=black>");
        out.println("<table\tx:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width='300' align='center'>");
        out.println("<tr>");
        out.println("  <td bgcolor='#EDEDED' height='40' style='border:.5pt\tsolid windowtext;verticla-align:center;' colspan=6>");
        out.println("    <p align='center'><font size='5' face='굴림체'><b>기관별집계표</b></font></p></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=95% align=center>");
        if (province != null) {
            out.println("  <tr>");
            out.println("    <td colspan=6 align=right><font size=2><b>관할지청</b>:" + new Common_ComBo().getCodeName("J03", province) + "</font></td>");
            out.println("  </tr>");
        }
        out.println("  <tr>");
        out.println("    <td colspan=6 align=right><font size=2><b>날짜</b>:" + new CommServer().getCurrDate(0, 0) + "</font></td>");
        out.println("  </tr>");
        out.println("</table>");
        out.println("<br>");
    }
    
    private void endHtml(final PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
    
    private void displayResult(final PrintWriter out, final ResultSet rest) throws Exception {
        final ResultSetMetaData rsmd = rest.getMetaData();
        final int count = rsmd.getColumnCount();
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=95% align=center>");
        out.println("  <tr bgcolor='#EDEDED'>");
        for (int i = 1; i <= count; ++i) {
            out.println("    <td class=retdac>" + rsmd.getColumnName(i) + "</td>");
        }
        out.println("  </tr>");
        int i = 1;
        String gigwanNameTemp = "";
        while (rest.next()) {
            out.println("<tr>");
            if (gigwanNameTemp.equals(rest.getString(1))) {
                out.println("    <td class=retdb></td>");
            }
            else {
                out.println("    <td class=retdb>" + rest.getString(1) + "</td>");
            }
            out.println("    <td class=retdb>" + rest.getString(2) + "</td>");
            out.println("    <td class=retdbr>" + rest.getString(3) + "</td>");
            out.println("    <td class=retdbr>" + rest.getString(4) + "</td>");
            out.println("    <td class=retdbr>" + rest.getString(5) + "</td>");
            out.println("    <td class=retdbr>" + rest.getString(6) + "</td>");
            out.println("</tr>");
            gigwanNameTemp = rest.getString(1);
            ++i;
        }
        if (i == 1) {
            out.println("  <tr>");
            out.println("  <td colspan=6 class=retdbc>조회된 결과가 없습니다.</td>");
            out.println("  </tr>");
        }
        out.println("</table>");
        out.println("<br>");
    }
}
