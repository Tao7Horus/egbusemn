// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

public class CommonMessage extends HttpServlet
{
    public static void printMessage(String message, final String actloc, final String dispmsg, final String url, final HttpServletResponse res, final HttpServletRequest req) throws ServletException, IOException {
        final String m001 = "Tài liệu trùng.";
        final String m2 = "Không có tài liệu.";
        final String m3 = "Đã đăng ký xong tài liệu.";
        final String m4 = "Đã sửa xong tài liệu.";
        final String m5 = "Đã xóa xong tài liệu.";
        final String m6 = "Không sửa được tài liệu.";
        final String m7 = "Không đăng ký được tài liệu.";
        final String m8 = "Không xóa được tài liệu.";
        final String m9 = "Hủy chỉnh sửa.";
        final String m10 = "Tài liệu đã sử dụng.";
        final String m11 = "Khởi tạo tài liệu tương ứng.";
        final String m12 = "Hoàn thiện xử lý thẩm tra tư cách.";
        final String m13 = "Đã hủy tài liệu.";
        final String m14 = "Không phải bộ phận có thể kết nối.";
        if (message == null) {
            message = " ";
        }
        String toUrl = "";
        if (url == null) {
            toUrl = "self.close(); return;";
        }
        else if (actloc.equals("1")) {
            toUrl = "opener.location.href=\"" + url + "\"; self.close(); return;";
        }
        else if (actloc.equals("2")) {
            toUrl = "opener.parent.location.href=\"" + url + "\"; self.close(); return;";
        }
        else if (actloc.equals("3")) {
            toUrl = "location.href=\"" + url + "\"; return;";
        }
        else if (actloc.equals("4")) {
            toUrl = "history.back(); return;";
        }
        else if (actloc.equals("5")) {
            toUrl = "window.opener.location.reload(); self.close(); return;";
        }
        else if (actloc.equals("6")) {
            toUrl = "history.back(); opener.location.href=\"" + url + "\"; return;";
        }
        else if (actloc.equals("7")) {
            toUrl = "opener.openerFunction(); self.close(); return;";
        }
        else if (actloc.equals("8")) {
            toUrl = "opener.openerFunction2(); self.close(); return;";
        }
        if (message.equals("001")) {
            message = m001;
        }
        else if (message.equals("002")) {
            message = m2;
        }
        else if (message.equals("003")) {
            message = m3;
        }
        else if (message.equals("004")) {
            message = m4;
        }
        else if (message.equals("005")) {
            message = m5;
        }
        else if (message.equals("006")) {
            message = m6;
        }
        else if (message.equals("007")) {
            message = m7;
        }
        else if (message.equals("008")) {
            message = m8;
        }
        else if (message.equals("009")) {
            message = m9;
        }
        else if (message.equals("010")) {
            message = m10;
        }
        else if (message.equals("011")) {
            message = m11;
        }
        else if (message.equals("012")) {
            message = m12;
        }
        else if (message.equals("013")) {
            message = m13;
        }
        else if (message.equals("014")) {
            message = m14;
        }
        String browerType = null;
        if (req == null) {
            browerType = ExplorerControl.TYPE_PC;
        }
        else {
            browerType = ExplorerControl.getBrowerType(req);
        }
        printCase(browerType, message, dispmsg, toUrl, res);
    }
    
    public static void printMsg(final String message, final String actloc, final String dispmsg, final String url, final HttpServletResponse res) throws ServletException, IOException {
        printMessage(message, actloc, dispmsg, url, res, null);
    }
    
    private static void printCase(final String type, final String message, final String dispmsg, final String toUrl, final HttpServletResponse res) throws ServletException, IOException {
        if (type.equals(ExplorerControl.TYPE_PC)) {
            printHtml(message, dispmsg, toUrl, res);
        }
        else if (type.equals(ExplorerControl.TYPE_SYNC) || type.equals(ExplorerControl.TYPE_PDA)) {
            printMobileHtml(message, dispmsg, toUrl, res);
        }
        else {
            printHtml(message, dispmsg, toUrl, res);
        }
    }
    
    private static void printHtml(String message, final String dispmsg, final String toUrl, final HttpServletResponse res) throws ServletException, IOException {
        if (!res.containsHeader("Content-Type")) {
            res.setContentType("text/html; charset=UTF-8");
        }
        final PrintWriter out = res.getWriter();
        if (message == null || message.length() == 0) {
            message = "Thông báo";
        }
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        out.println("<title>Message</title>");
        out.println("<link href=\"http://muasamcong.mpi.gov.vn/css/message.css\" rel=\"stylesheet\" type=\"text/css\" />");
        out.println("<script language=javascript>");
        out.println("\tfunction toClose(){");
        out.println(toUrl);
        out.println("\t}");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"messagebox\">");
        out.println("<div class=\"title\">");
        out.println("<div id=\"title_message\"> ");
        out.println("<div id=\"title_name\">");
        out.println("<b>" + message + "</b></div></div>");
        out.println("</div>");
        out.println("<div id=\"message_content\">" + dispmsg);
        out.println("<div align=center><a href=\"javascript:toClose();\"><br><img src=../../img/bu_close.gif align=absmiddle border=0></div ></a>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private static void printMobileHtml(final String message, final String dispmsg, final String toUrl, final HttpServletResponse res) throws ServletException, IOException {
        if (!res.containsHeader("Content-Type")) {
            res.setContentType("text/html; charset=UTF-8");
        }
        final PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Message</title>");
        out.println("<meta http-equiv='content-type' content='text/html; charset=UTF-8'>");
        out.println("<script language='javascript'>");
        out.println("   function toClose(){");
        out.println(toUrl);
        out.println("   }");
        out.println("</script>");
        out.println("</head>");
        out.println("<body link='#996600' vlink='#996600' alink='#996600' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0'>");
        out.println("<table border='0' cellpadding='0' cellspacing='0' width='228' bgcolor='#194980'>");
        out.println("   <tr>");
        out.println("       <td align='left'><font color='#ffffff'  >메세지</font></td>");
        out.println("       <td width='28' align='left'><a href='http://pda.g2b.go.kr' target='_self'><img src='http://pda.g2b.go.kr:8087/imgs/but_home.gif' alt='홈 이동' border='0'></a></td>");
        out.println("   </tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<table width='228' border='0' cellspacing='1' cellpadding='0'>");
        out.println("   <tr>");
        out.println("   <td align=center><b><font color='#3300CC' style='font-size: 11px;'>" + message + "</font></b> </td>");
        out.println("   </tr>");
        out.println("   <tr>");
        out.println("    <td align=center><font color='#3E168F' >");
        out.println(dispmsg);
        out.println("       </font><br><br> ");
        out.println("   </td>");
        out.println("   </tr>");
        out.println("   <tr>");
        out.println("       <td>");
        out.println("           <div align=center>");
        out.println("               <a href='javascript:toClose();'>닫기</a>");
        out.println("           </div>");
        out.println("       </td>");
        out.println("   </tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<table width='228' border='0' cellpadding='0' cellspacing='0'>");
        out.println("   <tr>");
        out.println("       <td width='133'><img src='http://pda.g2b.go.kr:8087/imgs/dot.gif' width='133' height='11' border='0'></td>");
        out.println("       <td width='95' rowspan='2'><img src='http://pda.g2b.go.kr:8087/imgs/logo_bottom.gif' width='95' height='14' border='0'></td>");
        out.println("   </tr>");
        out.println("   <tr>");
        out.println("       <td bgcolor='#3C9BD3'><img src='http://pda.g2b.go.kr:8087/imgs/dot.gif' width='1' height='3' border='0'></td>");
        out.println("   </tr>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
