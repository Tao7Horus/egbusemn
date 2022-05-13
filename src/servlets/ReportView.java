// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class ReportView extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        final PrintWriter out = res.getWriter();
        final String fileName = (req.getParameter("fileName") == null) ? "" : req.getParameter("fileName");
        final String transNo = (req.getParameter("transNo") == null) ? "" : req.getParameter("transNo");
        out.println("<HTML>");
        out.println("<HEAD>");
        out.println("<META http-equiv=\"Content-Type\" content=\"text/html; charset=euc-kr\">");
        out.println("<TITLE>Report</TITLE>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/UM.css\">");
        out.println("</HEAD>");
        out.println("<SCRIPT language=\"VBscript\">");
        out.println("   sub window_onload()");
        out.println("       printStr = top.frames[0].G2B.GetDataFromUrl(\"http://www.g2b.go.kr:8070/servlet/servlets.DB2TXT?transNo=" + transNo + "&?fileName=" + fileName + "\")");
        out.println("       top.frames[0].G2B.SaveInfoTxt \"C:\\G2B\\prt\\report.txt\", printStr");
        out.println("       Rdviewer.FileOpen \"http://www.g2b.go.kr:8070/mrd/" + fileName + ".mrd\", \"/rf [c:\\G2B\\prt\\report.txt]\"");
        out.println("   end sub");
        out.println("</SCRIPT>");
        out.println("<body background=\"/img/bg01.gif\" text=\"#3C3C3C\" leftmargin=\"0\" topmargin=\"0\" marginwidth=\"0\" marginheight=\"0\">");
        out.println("<table width=\"823\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\"  background=\"/img/bg_sub.gif\">");
        out.println("    <tr valign=\"bottom\" height=\"57\"> ");
        out.println("      <td rowspan=\"2\"><img src=\"/img/sub_title_01.jpg\"></td>");
        out.println("      <td colspan=\"2\" background=\"/img/sub_title_02.jpg\" width=\"788\" height=\"57\" class=\"HEADLINE\"> ");
        out.println("        &nbsp; 시행문 출력</td>");
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
        out.println("<object classid=\"clsid:DAD381B2-4672-4936-B369-6178F9D970EE\" id=\"G2B\" codebase=\"http://www.g2b.go.kr:8076/xmldoc/control/G2B.cab#version=1,0,0,25\" width=\"0%\" height=\"0%\">");
        out.println("  <param name=\"DefaultPath\" value=\"C:\\G2B\">");
        out.println("</object>");
        out.println("<OBJECT classid=\"clsid:7D9A42C6-4114-11D4-Af7B-00A024C32DBA\" id=\"Rdviewer\" codebase=\"http://www.ebiz.go.kr:8085/xmldoc/control/rdviewer25.cab#version=2,5,0,348\" WIDTH=\"750\" HEIGHT=\"1100\">");
        out.println("</OBJECT>");
        out.println("      </td>");
        out.println("      <td width=\"28\"></td>");
        out.println("    </tr>");
        out.println("    <tr height=\"35\"><td colspan=\"3\"></td></tr>");
        out.println("    <tr height=\"*\"><td colspan=\"3\"></td></tr>");
        out.println("    <tr> ");
        out.println("      <td colspan=\"3\" height=\"50\"><%@ include file=\"/jsp/common/Footer.jsp\" %></td>");
        out.println("    </tr>");
        out.println("  </table> ");
        out.println("</BODY>");
        out.println("</HTML>");
    }
}
