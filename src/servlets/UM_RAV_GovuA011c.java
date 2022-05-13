// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import common.util.CommonMessage;
import common.Log;
import java.sql.SQLException;
import common.Trx;
import beans.UM_RAB_GovuA020c;
import common.util.CommUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_RAV_GovuA011c extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        Trx trx = null;
        Connection conn = null;
        final CommUtil com = new CommUtil();
        final String recept = com.retNull(req.getParameter("recept"));
        final String g2bCode = CommUtil.retSpace(req.getParameter("g2bCode"));
        final String flag = CommUtil.retSpace(req.getParameter("actFlag"));
        String result = "";
        String toUrl = "";
        String msg = "";
        final UM_RAB_GovuA020c ctl = new UM_RAB_GovuA020c();
        try {
            trx = new Trx(this);
            conn = trx.getConnection();
            result = ctl.getRegStatus(conn, recept);
            if ("N".equals(result)) {
                conn.setAutoCommit(false);
                result = ctl.cancelReg(conn, recept);
                if ("0".equals(result)) {
                    msg = "Đã hủy bỏ đăng ký thông tin bên mời thầu thành công.";
                }
                else {
                    msg = "Lỗi hủy bỏ đăng ký.";
                }
                toUrl = "http://muasamcong.mpi.gov.vn:8070/RA/UM_RAJ_GovuA010s.jsp?recept=" + recept + "&g2bCode=" + g2bCode + "&flag=S";
                conn.commit();
                conn.setAutoCommit(true);
            }
            else if ("Y".equals(result)) {
                msg = "Đã được phê duyệt.";
                toUrl = "http://muasamcong.mpi.gov.vn:8070/RA/UM_RAJ_GovuA010s.jsp?recept=" + recept + "&g2bCode=" + g2bCode + "&flag=S";
            }
            else if ("D".equals(result)) {
                msg = "Đã được phê duyệt.";
                toUrl = "http://muasamcong.mpi.gov.vn:8070/RA/UM_RAJ_GovuA010s.jsp?recept=" + recept + "&g2bCode=" + g2bCode + "&flag=S";
            }
            else {
                msg = "Tình trạng nộp đơn đăng ký không được.";
                toUrl = "http://muasamcong.mpi.gov.vn:8070/RA/UM_RAJ_GovuA010s.jsp?recept=" + recept + "&g2bCode=" + g2bCode + "&flag=S";
            }
            printHtml(msg, toUrl, res);
        }
        catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            }
            catch (SQLException ex) {}
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            }
            catch (SQLException ex2) {}
            Log.debug(String.valueOf(this.getClass().getName()) + ".cancelReg() Số tiếp nhận [" + recept + "] " + e.toString());
            CommonMessage.printMsg(null, "", e.getMessage(), null, res);
            return;
        }
        finally {
            if (conn != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (conn != null) {
            try {
                trx.close();
            }
            catch (Exception ex4) {}
        }
    }
    
    private static void printHtml(final String dispmsg, final String toUrl, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=UTF-8");
        final PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Message</title>");
        out.println("<link rel=stylesheet type=text/css href=/css/UM.css>");
        out.println("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>");
        out.println("<script language=javascript>");
        out.println("   function toClose(){");
        out.println("       alert(\"" + dispmsg + "\");");
        out.println("       location.href = \"" + toUrl + "\";");
        out.println("       return;");
        out.println("   }");
        out.println("</script>");
        out.println("</head>");
        out.println("<body bgcolor=#FFFFFF text=#000000 topmargin=0 leftmargin=0 marginwidth=0 marginheight=0>");
        out.println("  <script language=javascript>toClose()</script>");
        out.println("</body>");
        out.println("</html>");
    }
}
