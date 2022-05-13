// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

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

public class UM_ADJ_GovrB050c extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        final UM_ADB_GovrB030c uagb030 = new UM_ADB_GovrB030c();
        final CommUtil cu = new CommUtil();
        final String flag = CommUtil.retSpace(req.getParameter("flag"));
        String license = CommUtil.retSpace(req.getParameter("license"));
        final String recept = CommUtil.retSpace(req.getParameter("recept"));
        final String departmentNo = CommUtil.retSpace(req.getParameter("departmentNo"));
        final String groupNo = CommUtil.retSpace(req.getParameter("groupNo"));
        if (license.equals("")) {
            license = "N";
        }
        if (flag.equals("Cookie")) {
            try {
                res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/RA/UM_RAJ_GovuC011l.jsp?ApprovalCode=" + uagb030.getApprovalCode(CommUtil.retSpace(req.getParameter("ID"))));
            }
            catch (Exception ef) {
                CommonMessage.printMsg(null, "", ef.getMessage(), null, res);
            }
            return;
        }
        Trx trx = null;
        Connection con = null;
        final String g2bCode = cu.retNull(req.getParameter("g2bCode"));
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            con.setAutoCommit(false);
            uagb030.updateGigwanMaster(recept, g2bCode, license, "", req, con);
            uagb030.updateGigwanCreditTable(recept, req, con);
            uagb030.updateGigwanSertTable(recept, g2bCode, req, con);
            con.commit();
            con.setAutoCommit(true);
            res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/RA/UM_RAJ_GovuA020s.jsp?code=" + recept + "&recept1=" + recept + "&g2bCode=" + g2bCode + "&departmentNo=" + departmentNo + "&groupNo=" + groupNo);
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
            Log.debug("UM_ADJ_GovrB030c : " + e.toString());
            Log.errors(this, e, "UM_ADJ_GovrB030c : " + e.toString());
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
}
