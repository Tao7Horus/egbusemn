// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.Connection;
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

public class Report_Code_03 extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        throw new Error("Unresolved compilation problems: \n\tThe type CommUtil is ambiguous\n\tThe type CommonMessage is ambiguous\n");
    }
    
    private void headHtml(final PrintWriter out, final String desformat, final String local) throws Exception {
        final String local2 = String.valueOf(local) + "000";
        out.println("<html>");
        out.println("<head>");
        out.println("<title>기업구분별 등록업체 현황 </title>");
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
        out.println("  <td bgcolor='#EDEDED' height='40' style='border:.5pt\tsolid windowtext;verticla-align:center;' colspan=10>");
        out.println("    <p align='center'><font size='5' face='굴림체'><b>기업구분별 등록업체현황</b></font></p></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=95% align=center>");
        out.println("  <tr>");
        out.println("    <td colspan=10 align=right><font size=2><b>지 역</b> : " + new Common_ComBo().getCodeName("GU7", local2) + "</font></td>");
        out.println("  <tr>");
        out.println("    <td colspan=10 align=right><font size=2><b>날 짜</b> : " + new CommServer().getCurrDate(0, 0) + "</font></td>");
        out.println("  </tr>");
        out.println("</table>");
        out.println("<br>");
    }
    
    private void endHtml(final PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
    
    private void displayResult(final PrintWriter out, final ResultSet rest, final Connection conn) throws Exception {
        throw new Error("Unresolved compilation problem: \n\tThe type CommUtil is ambiguous\n");
    }
}
