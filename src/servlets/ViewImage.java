// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import common.Log;
import common.util.UM_COE_GTQ903;
import beans.UM_IngamControl;
import LOGIN.UM_Auth_Check;
import common.util.CommonMessage;
import common.util.HttpUtility;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class ViewImage extends HttpServlet
{
    private static final long serialVersionUID = -3627206193457273210L;
    private static final String rootPath = "http://muasamcong.mpi.gov.vn/";
    
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        if (req.getProtocol().compareTo("HTTP/1.0") == 0) {
            res.setHeader("Pragma", "no-cache");
        }
        else if (req.getProtocol().compareTo("HTTP/1.1") == 0) {
            res.setHeader("Cache-Control", "no-cache");
        }
        res.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = res.getWriter();
        final String type = req.getParameter("type");
        final String saupNo = req.getParameter("saupNo");
        final String imageName = req.getParameter("imageName");
        String flag = req.getParameter("flag");
        if (flag == null || flag.equals("")) {
            flag = "NOEDI";
        }
        UM_Auth_Check cookieCheck = null;
        final String IP = HttpUtility.getIP(req);
        boolean isEdiTrue = false;
        if (flag.equals("rehtaftibbarmai")) {
            isEdiTrue = true;
        }
        if (isEdiTrue) {
            if (!IP.startsWith("211.42.85") && !IP.startsWith("10.139.7")) {
                CommonMessage.printMsg("", "", "Người dùng không phải là EDI.", null, res);
                return;
            }
        }
        else {
            try {
                cookieCheck = new UM_Auth_Check(req, res);
                cookieCheck.checkCookie("cga", saupNo, null);
            }
            catch (Exception e) {
                CommonMessage.printMsg("", "", e.getMessage(), null, res);
                return;
            }
        }
        final UM_IngamControl UMincontrol = new UM_IngamControl();
        UM_COE_GTQ903[] ucg903 = (UM_COE_GTQ903[])null;
        int total = 0;
        try {
            if (type.equals("one")) {
                total = 1;
            }
            else if (type.equals("total")) {
                ucg903 = UMincontrol.getIngamData(saupNo, "Y");
                total = ucg903.length;
            }
            this.initHtml(out);
            this.printHtml(out, ucg903, type, total, imageName, saupNo, isEdiTrue);
            this.endHtml(out);
        }
        catch (Exception ex) {
            Log.debug("viewImage.java Số đăng ký kinh doanh[" + saupNo + "], type[" + type + "],imageName[" + imageName + "]:" + ex.toString());
            CommonMessage.printMsg("", "", ex.getMessage(), null, res);
        }
    }
    
    private void printHtml(final PrintWriter out, final UM_COE_GTQ903[] ucg903, final String type, final int total, final String imageName, final String saupNo, final boolean isEdiTrue) throws Exception {
        final UM_IngamControl UMincontrol = new UM_IngamControl();
        final UM_COE_GTQ903[] ucg903_EdiCode = UMincontrol.getEDICode(saupNo);
        out.println("<table width=100% border=0 cellspacing=1 cellpadding=2>");
        out.println("\t<tr height=4><td colspan=4 class=line></td></tr>");
        out.println("\t<tr>");
        out.println("\t\t<td class='tda' width=20%>Tên nhà thầu</td>");
        out.println("\t\t<td class='tdb'>" + ucg903_EdiCode[0].data[1] + "</td>");
        out.println("\t</tr>");
        out.println("\t<tr>");
        out.println("\t\t<td class='tda' width=15%>Số đăng ký kinh doanh</td>");
        out.println("\t\t<td class='tdb'>" + ucg903_EdiCode[0].data[0] + "</td>");
        out.println("\t</tr>");
        out.println("\t<tr height=2><td colspan=4 class=line></td></tr>");
        out.println("</table>");
        out.println("<br>");
        if (total == 0) {
            out.println("<table width=100% border=0 cellspacing=1 cellpadding=2>");
            out.println("\t<tr height=4><td colspan=4 class=line></td></tr>");
            out.println("\t<tr>");
            out.println("\t\t<td class='tdbc'>Dữ liệu không tồn tại.</td>");
            out.println("\t</tr>");
            out.println("</table>");
            out.println("<br>");
            return;
        }
        out.println("<table width=100% border=0 cellspacing=1 cellpadding=0>");
        if (type.equals("total")) {
            for (int j = 0; j < total; ++j) {
                if (j % 4 == 0) {
                    out.println("<tr>");
                    out.println("\t<td align=center><img oncontextmenu='return false' src='/servlet/servlets/ImageViewer?imageName=" + ucg903[j].data[1] + ".jpg" + "'><font size=2 color=blue><br>" + ucg903[j].data[1] + "</font></td>");
                }
                else {
                    out.println("\t<td align=center><img oncontextmenu='return false' src='/servlet/servlets/ImageViewer?imageName=" + ucg903[j].data[1] + ".jpg" + "'><font size=2 color=blue><br>" + ucg903[j].data[1] + "</font></td>");
                    if (j != total && j % 4 == 3) {
                        out.println("</tr>");
                    }
                }
                if (j + 1 == total) {
                    out.println("</tr>");
                }
            }
        }
        else if (type.equals("one")) {
            out.println("<tr>");
            out.println("\t<td align=center><img oncontextmenu='return false' src='/servlet/servlets/ImageViewer?imageName=" + imageName + ".jpg" + "'><font size=2 color=blue><br>" + imageName + "</font></td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<br>");
    }
    
    private void initHtml(final PrintWriter out) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Xem con dấu</title>");
        out.println("<link rel=STYLESHEET type=text/css href='http://muasamcong.mpi.gov.vn/css/TA.css'>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        out.println("<script language='javascript'>");
        out.println("</script>");
        out.println("</head>");
        out.println("<body background='http://muasamcong.mpi.gov.vn//img/bg01.gif' text=#3C3C3C topmargin=0 leftmargin=0 marginwidth=0 marginheight=0> ");
        out.println("<table width=823 border=0 cellpadding=0 cellspacing=0 height=100%  background='http://muasamcong.mpi.gov.vn//img/bg_sub.gif'> ");
        out.println("<tr valign=bottom height=57> ");
        out.println("<td rowspan=2><img src='http://muasamcong.mpi.gov.vn//img/sub_title_01.jpg'></td> ");
        out.println("<td colspan=2 background='http://muasamcong.mpi.gov.vn//img/sub_title_02.jpg' width=788 height=57 class='HEADLINE'>&nbsp;&nbsp;Con dấu của nhà thầu</td> ");
        out.println("</tr> ");
        out.println("<tr height=7> ");
        out.println("<td colspan=2><img src='http://muasamcong.mpi.gov.vn//img/sub_title_03.jpg'></td> ");
        out.println("</tr>");
        out.println("<tr height=21> ");
        out.println("<td colspan=3></td>");
        out.println("</tr> ");
        out.println("<tr valign=top> ");
        out.println("<td width=35></td> ");
        out.println("<td width=760> ");
    }
    
    private void endHtml(final PrintWriter out) {
        out.println("<table width=100% border=0 cellspacing=1 cellpadding=2 class=tr>");
        out.println("    <tr> ");
        out.println("    \t<td align=center> ");
        out.println("\t\t\t<input type='button' value='Đóng' onclick='javascript:self.close();' class='commonbutton'/>");
        out.println("      \t</td>");
        out.println("    </tr>");
        out.println("</table>");
        out.println("</td><td width=28></td></tr>");
        out.println("<tr height=10><td colspan=3></td></tr>");
        out.println("<tr height=*><td colspan=3></td></tr>");
        out.println("<tr><td colspan=3 height=45 width=*>");
        out.println("<table width=100% cellpadding=0 cellspacing=0 border=0 background='http://muasamcong.mpi.gov.vn//img/pop_bottom_back.gif' height=45>");
        out.println("<tr>");
        out.println("<td background='http://muasamcong.mpi.gov.vn//img/pop_bottom.gif' style='background-position:right; background-repeat:no-repeat;'></td>");
        out.println("</tr></table>");
        out.println("</td></tr></table>");
        out.println("</body>");
        out.println("</html>");
    }
}
