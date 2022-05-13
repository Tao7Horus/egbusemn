// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.ResultSetMetaData;
import common.util.CommMath;
import java.sql.ResultSet;
import common.util.CommServer;
import common.WebUtil;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_GovrR070 extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        throw new Error("Unresolved compilation problems: \n\tThe type CommUtil is ambiguous\n\tThe type CommonMessage is ambiguous\n\tThe type CommonMessage is ambiguous\n");
    }
    
    private void headHtml(final PrintWriter out, final String desformat, final String start, final String end) throws Exception {
        String startYYYY = "";
        String startMM = "";
        String startDD = "";
        String endYYYY = "";
        String endMM = "";
        String endDD = "";
        startYYYY = start.substring(0, 4);
        startMM = start.substring(4, 6);
        startDD = start.substring(6, 8);
        endYYYY = end.substring(0, 4);
        endMM = end.substring(4, 6);
        endDD = end.substring(6, 8);
        out.println("<html>");
        out.println("<head>");
        out.println("<title>수요기관지청별현황조회</title>");
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
        out.println("  <td bgcolor='#EDEDED' height='40' style='border:.5pt\tsolid windowtext;verticla-align:center;' colspan=20>");
        out.println("    <p align='center'><font size='5' face='굴림체'><b>수요기관지청별현황조회</b></font></p></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=95% align=center>");
        out.println("  <tr>");
        out.println("    <td colspan=10><font size=2><b>기간</b> : " + startYYYY + "-" + startMM + "-" + startDD + " ~ " + endYYYY + "-" + endMM + "-" + endDD + "</font></td><td colspan=10 align=right><b>날짜</b> : " + new CommServer().getCurrDate(0, 0) + "</font></td>");
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
        final double[] sum = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=95% align=center>");
        out.println("  <tr bgcolor='#EDEDED'>");
        for (int i = 1; i <= count; ++i) {
            out.println("    <td class=retdac>" + rsmd.getColumnName(i) + "</td>");
        }
        out.println("  </tr>");
        int indexCount = 1;
        while (rest.next()) {
            out.println("<tr>");
            for (int j = 1; j <= count; ++j) {
                if (j == 1) {
                    out.println("    <td class=retdb>" + rest.getString(j) + "</td>");
                }
                else {
                    final double[] array = sum;
                    final int n = j - 2;
                    array[n] += rest.getDouble(j);
                    out.println("    <td class=retdbr>" + rest.getString(j) + "</td>");
                }
            }
            out.println("</tr>");
            ++indexCount;
        }
        if (indexCount != 1) {
            out.println("<tr>");
            out.println("    <td class=retdb>계</td>");
            for (int k = 0; k < count - 1; ++k) {
                out.println("    <td class=retdbr>" + CommMath.MakeComma(sum[k]) + "</td>");
            }
        }
        if (indexCount == 1) {
            out.println("  <tr>");
            out.println("  <td colspan=20 class=retdbc>조회된 결과가 없습니다.</td>");
            out.println("  </tr>");
        }
        out.println("</table>");
        out.println("<br>");
    }
}
