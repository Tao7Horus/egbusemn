// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import common.Log;
import common.Trx;
import common.util.CommonMessage;
import common.util.CommUtil;
import beans.UM_ADB_GovrB030c;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_RAV_GovuA010c extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final UM_ADB_GovrB030c uagb030 = new UM_ADB_GovrB030c();
        final CommUtil cu = new CommUtil();
        final String flag = CommUtil.retSpace(req.getParameter("flag"));
        final String newCode = cu.retNull(req.getParameter("newCode"));
        final String recept = cu.retNull(req.getParameter("recept"));
        String g2bCode = cu.retNull(req.getParameter("g2bCode"));
        String new_g2bCode = "";
        String recept2 = cu.retNull(req.getParameter("recept1"));
        String new_license = CommUtil.retSpace(req.getParameter("license"));
        final String old_license = cu.retNull(req.getParameter("LicenseGubun"));
        final String saupNo = CommUtil.retSpace(req.getParameter("saupNo"));
        final String goNameFull = CommUtil.retSpace(req.getParameter("goNameFull"));
        String msg = "";
        if (new_license.equals("")) {
            new_license = "N";
        }
        if (flag.equals("Cookie")) {
            try {
                res.sendRedirect("/jsp/RA/UM_RAJ_GovuC011l.jsp?ApprovalCode=" + uagb030.getApprovalCode(CommUtil.retSpace(req.getParameter("ID"))));
            }
            catch (Exception ef) {
                CommonMessage.printMsg(null, "", ef.getMessage(), null, res);
            }
            return;
        }
        Trx trx = null;
        Connection con = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            con.setAutoCommit(false);
            if (newCode != null) {
                msg = "공공기관코드 생성";
                new_g2bCode = uagb030.getG2BCode(newCode, con);
                if (!g2bCode.substring(0, 2).equals(new_g2bCode.substring(0, 2))) {
                    g2bCode = new_g2bCode;
                }
                Log.debug(String.valueOf(this.getClass().getName()) + " 접수번호[" + recept + "] 공공기관코드 [" + g2bCode + "]");
            }
            if (old_license.equals("N") && new_license.equals("Y")) {
                msg = "사용_접수공공기관인증서 접수번호 생성";
                recept2 = uagb030.getCertRecetionNumber(con);
                Log.debug(String.valueOf(this.getClass().getName()) + " 접수번호[" + recept + "] 인증서 접수번호 [" + recept2 + "]");
            }
            msg = "사용_접수공공기관마스터 update";
            uagb030.updateGigwanMaster(recept, g2bCode, new_license, new_license.equals("N") ? "" : recept2, req, con);
            msg = "사용_접수공공기관회계코드 update";
            uagb030.updateGigwanCreditTable(recept, req, con);
            if (old_license.equals("N") && new_license.equals("Y")) {
                msg = "사용_접수공공기관인증서 insert";
                uagb030.insertGigwanSertTable(recept, recept2, g2bCode, req, con);
            }
            else if (old_license.equals("Y") && new_license.equals("N")) {
                msg = "사용_접수공공기관인증서 delete";
                uagb030.deleteGigwanSertTable(recept2, g2bCode, req, con);
            }
            else if (old_license.equals("Y") && new_license.equals("Y")) {
                msg = "사용_접수공공기관인증서 update";
                uagb030.updateGigwanSertTable(recept2, g2bCode, req, con);
            }
            con.commit();
            con.setAutoCommit(true);
            res.sendRedirect("/jsp/RA/UM_RAJ_GovuA020s.jsp?code=" + recept + "&recept1=" + recept2 + "&g2bCode=" + g2bCode);
        }
        catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            }
            catch (Exception ex) {}
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                }
            }
            catch (Exception ex2) {}
            Log.debug(String.valueOf(this.getClass().getName()) + " 접수번호[" + recept + "] [" + msg + "] " + e.toString());
            CommonMessage.printMsg(null, "", e.getMessage(), null, res);
        }
        finally {
            try {
                if (con != null) {
                    trx.close();
                }
            }
            catch (Exception ex3) {}
        }
        try {
            if (con != null) {
                trx.close();
            }
        }
        catch (Exception ex4) {}
    }
    
    private static void printHtml(final String dispmsg, final String toUrl, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=euc-kr");
        final PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Message</title>");
        out.println("<link rel=stylesheet type=text/css href=/css/UM.css>");
        out.println("<meta http-equiv=Content-Type content=text/html; charset=euc-kr>");
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
