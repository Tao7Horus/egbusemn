// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.PrintWriter;
import common.ExplorerControl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

public class CommonMessage extends HttpServlet
{
    public static void printAlertMessage(final String message, final boolean hasAfterAction, final String actionType, final String url, final HttpServletResponse res, final HttpServletRequest req) throws ServletException, IOException {
        String afterActionUrl = null;
        if (hasAfterAction) {
            if (actionType.equals("0")) {
                afterActionUrl = "self.close();";
            }
            else if (actionType.equals("1")) {
                afterActionUrl = "opener.location=\"" + url + "\"; self.close();";
            }
            else if (actionType.equals("2")) {
                afterActionUrl = "opener.parent.location=\"" + url + "\"; self.close();";
            }
            else if (actionType.equals("3")) {
                afterActionUrl = "window.location=\"" + url + "\";";
            }
            else if (actionType.equals("4")) {
                afterActionUrl = "history.back();";
            }
            else if (actionType.equals("5")) {
                afterActionUrl = "window.opener.location.reload(); self.close();";
            }
            else if (actionType.equals("6")) {
                afterActionUrl = "history.back(); opener.location=\"" + url + "\";";
            }
            else if (actionType.equals("7")) {
                afterActionUrl = "opener.openerFunction(); self.close();";
            }
        }
        printAlertMessage(message, afterActionUrl, res);
    }
    
    public static void printMessage(String message, final String actloc, String dispmsg, final String url, final HttpServletResponse res, final HttpServletRequest req) throws ServletException, IOException {
        final String m001 = "Dữ liệu bị trùng lặp";
        final String m2 = "Không có dữ liệu phù hợp";
        final String m3 = "Dữ liệu đã được đăng ký";
        final String m4 = "Dữ liệu đã được thay đổi";
        final String m5 = "Dữ liêu đã bị xóa bỏ";
        final String m6 = "Không xóa được";
        final String m7 = "Không được đăng ký";
        final String m8 = "Đã bị xóa";
        final String m9 = "Yêu cầu thay đổi bị hoãn";
        final String m10 = "Dữ liệu này đã bị xóa";
        final String m11 = "Dữ liệu này đã được tạo.";
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
        String browerType = null;
        if (req == null) {
            browerType = ExplorerControl.TYPE_PC;
        }
        else {
            browerType = ExplorerControl.getBrowerType(req);
        }
        if (dispmsg == null || dispmsg.equals("")) {
            dispmsg = message;
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
    
    private static void printHtml(final String message, final String dispmsg, final String toUrl, final HttpServletResponse res) throws ServletException, IOException {
        if (!res.containsHeader("Content-Type")) {
            res.setContentType("text/html; charset=utf-8");
        }
        final PrintWriter out = res.getWriter();
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        out.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
        out.println("<head>");
        out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        out.println("<title>Message</title>");
        out.println("<link href='http://muasamcong.mpi.gov.vn/css/message.css' rel='stylesheet' type='text/css' />");
        out.println("<link href='http://muasamcong.mpi.gov.vn/css/TA.css' rel='stylesheet' type='text/css' />");
        out.println("<script language=javascript>");
        out.println("\tfunction toClose(){");
        out.println("document.ebidSearch.method = \"POST\";");
        out.println("document.ebidSearch.action = " + toUrl + ";");
        out.println("document.ebidSearch.submit();");
        out.println("return;");
        out.println("\t}");
        out.println("</script>");
        out.println("</head>");
        out.println(" ");
        out.println("<body>");
        out.println("<div class='col-750 clearfix last'>");
        out.println(" ");
        out.println("<div class='title'>");
        out.println("<div id='title_message'> ");
        out.println("<div id='title_name'>");
        out.println(message);
        out.println("</div>");
        out.println("</div>");
        out.println("</div>");
        out.println(" ");
        out.println("<div id='message_content' align='center'>");
        out.println(dispmsg);
        out.println("<br />");
        out.println("<br />");
        out.println("<input type='button' class='commonbutton' value='Đóng' onclick=\"history.go(-2);\" />");
        out.println("<form name=\"ebidSearch\" ></form>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private static void printMobileHtml(final String message, final String dispmsg, final String toUrl, final HttpServletResponse res) throws ServletException, IOException {
        if (!res.containsHeader("Content-Type")) {
            res.setContentType("text/html; charset=utf-8");
        }
        final PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Thông báo</title>");
        out.println("<meta http-equiv='content-type' content='text/html; charset=utf-8'>");
        out.println("<script language='javascript'>");
        out.println("   function toClose(){");
        out.println(toUrl);
        out.println("   }");
        out.println("</script>");
        out.println("</head>");
        out.println("<body link='#996600' vlink='#996600' alink='#996600' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0'>");
        out.println("<table border='0' cellpadding='0' cellspacing='0' width='228' bgcolor='#194980'>");
        out.println("   <tr>");
        out.println("       <td align='left'><font color='#ffffff'  >Thông báo</font></td>");
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
        out.println("               <a href='javascript:toClose();'>Đóng</a>");
        out.println("           </div>");
        out.println("       </td>");
        out.println("   </tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private static void printAlertMessage(final String message, final String afterActionUrl, final HttpServletResponse res) throws ServletException, IOException {
        if (!res.containsHeader("Content-Type")) {
            res.setContentType("text/html; charset=utf-8");
        }
        final PrintWriter out = res.getWriter();
        out.println("<script language='javascript'>");
        out.println("\talert('" + message + "');");
        out.println(afterActionUrl);
        out.println("</script>");
    }
}
