// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

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

public class UM_ADV_GovrR020 extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        throw new Error("Unresolved compilation problems: \n\tThe type CommUtil is ambiguous\n\tThe type CommonMessage is ambiguous\n\tThe type CommonMessage is ambiguous\n");
    }
    
    private void headHtml(final PrintWriter out, final String desformat, final String province) throws Exception {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>수요기관삭제리스트</title>");
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
        out.println("  <td bgcolor='#EDEDED' height='40' style='border:.5pt\tsolid windowtext;verticla-align:center;' colspan=9>");
        out.println("    <p align='center'><font size='5' face='굴림체'><b>수요기관삭제리스트</b></font></p></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=95% align=center>");
        out.println("  <tr>");
        out.println("    <td colspan=9 align=right><font size=2><b>관할지청</b>:" + new Common_ComBo().getCodeName("J03", province) + "</font></td>");
        out.println("  <tr>");
        out.println("    <td colspan=9 align=right><font size=2><b>날짜</b>:" + new CommServer().getCurrDate(0, 0) + "</font></td>");
        out.println("  </tr>");
        out.println("</table>");
        out.println("<br>");
    }
    
    private void endHtml(final PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
    
    private void displayResult(final PrintWriter out, final ResultSet rest) throws Exception {
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=95% align=center>");
        out.println("  <tr bgcolor='#EDEDED'>");
        out.println("    <td class=retdac>번호</td>");
        out.println("    <td class=retdac>수요기관코드</td>");
        out.println("    <td class=retdac>수요기관명</td>");
        out.println("    <td class=retdac>우편번호</td>");
        out.println("    <td class=retdac>주소</td>");
        out.println("    <td class=retdac>전화번호</td>");
        out.println("    <td class=retdac>등록일자</td>");
        out.println("    <td class=retdac>수정일자</td>");
        out.println("    <td class=retdac>소관구분</td>");
        out.println("  </tr>");
        int i = 1;
        while (rest.next()) {
            out.println("<tr>");
            out.println("    <td class=retdbc>" + rest.getString(1) + "</td>");
            out.println("    <td class=retdbc>" + rest.getString(2) + "</td>");
            out.println("    <td class=retdb>" + rest.getString(3) + "</td>");
            out.println("    <td class=retdbc>" + rest.getString(4) + "</td>");
            out.println("    <td class=retdb>" + rest.getString(5) + "</td>");
            out.println("    <td class=retdb>" + rest.getString(6) + "</td>");
            out.println("    <td class=retdbc>" + rest.getString(7) + "</td>");
            out.println("    <td class=retdbc>" + rest.getString(8) + "</td>");
            out.println("    <td class=retdb>" + rest.getString(9) + "</td>");
            out.println("</tr>");
            ++i;
        }
        if (i == 1) {
            out.println("  <tr>");
            out.println("  <td colspan=9 class=retdbc>조회된 결과가 없습니다.</td>");
            out.println("  </tr>");
        }
        out.println("</table>");
        out.println("<br>");
    }
}
