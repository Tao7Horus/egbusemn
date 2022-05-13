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

public class UM_ADV_GovrR060 extends HttpServlet
{
    public static final String[] sogwanGubun;
    
    static {
        sogwanGubun = new String[] { "", "01 Cơ quan nhà nước", "02 Cơ quan nhà nước ở địa phương", "03 Cơ quan giáo dục", "05 Cơ quan có đầu tư của nhà nước", "07 Cơ quan tự do" };
    }
    
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        throw new Error("Unresolved compilation problems: \n\tThe type CommUtil is ambiguous\n\tThe type CommonMessage is ambiguous\n\tThe type CommonMessage is ambiguous\n");
    }
    
    private void headHtml(final PrintWriter out, final String desformat) throws Exception {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Tra cứu tình trạng theo từng vùng bên mời thầu</title>");
        out.println("<meta http-equiv=Content-Type content=\"text/html; charset=utf_8\">");
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
        out.println("  <td bgcolor='#EDEDED' height='40' style='border:.5pt\tsolid windowtext;verticla-align:center;' colspan=19>");
        out.println("    <p align='center'><font size='5' face='굴림체'><b>Tra cứu tình trạng theo từng vùng bên mời thầu</b></font></p></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=95% align=center>");
        out.println("  <tr>");
        out.println("    <td colspan=19 align=right><font size=2><b>Ngày</b>:" + new CommServer().getCurrDate(0, 0) + "</font></td>");
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
        final double[] sum = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=95% align=center>");
        out.println("  <tr bgcolor='#EDEDED'>");
        out.println("    <td class=retdac>Phân loại</td>");
        for (int i = 1; i <= count; ++i) {
            out.println("    <td class=retdac>" + rsmd.getColumnName(i) + "</td>");
        }
        out.println("  </tr>");
        int indexCount = 1;
        while (rest.next()) {
            out.println("<tr>");
            out.println("    <td class=retdb>" + UM_ADV_GovrR060.sogwanGubun[indexCount] + "</td>");
            for (int j = 1; j <= count; ++j) {
                out.println("    <td class=retdbr>" + rest.getString(j) + "</td>");
                final double[] array = sum;
                final int n = j - 1;
                array[n] += rest.getDouble(j);
            }
            out.println("</tr>");
            ++indexCount;
        }
        if (indexCount != 1) {
            out.println("<tr>");
            out.println("    <td class=retdb>Tổng số</td>");
            for (int k = 0; k < count; ++k) {
                out.println("    <td class=retdbr>" + CommMath.MakeComma(sum[k]) + "</td>");
            }
        }
        if (indexCount == 1) {
            out.println("  <tr>");
            out.println("  <td colspan=19 class=retdbc>Không có kết quả tìm kiếm.</td>");
            out.println("  </tr>");
        }
        out.println("</table>");
        out.println("<br>");
    }
}
