// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import entity.UM_CL_Regi_001l;
import java.sql.Connection;
import common.Log;
import common.util.CommonMessage;
import common.Trx;
import beans.UM_CL_Regi_001p;
import common.util.CommUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_CL_Regi003p extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        final String cdCls = CommUtil.retSpace(req.getParameter("cdCls"));
        final String cd = CommUtil.retSpace(req.getParameter("cd"));
        final UM_CL_Regi_001p regi001p = new UM_CL_Regi_001p();
        final CommUtil cu = new CommUtil();
        Trx trx = null;
        Connection conn = null;
        try {
            trx = new Trx(this);
            conn = trx.getConnection();
            conn.setAutoCommit(false);
            final UM_CL_Regi_001l item = regi001p.selectCode(cdCls, cd);
            if (item.getCd() != null) {
                CommonMessage.printMsg(null, "7", "Mã số đã tồn tại!", null, res);
                return;
            }
            regi001p.insertCode(req, conn);
            conn.commit();
            conn.setAutoCommit(true);
            res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/CL/CL_COD_Lst.jsp?cdCls=" + cdCls);
        }
        catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            }
            catch (Exception ex) {}
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            }
            catch (Exception ex2) {}
            Log.debug("UM_EY_Regi_001p : " + e.toString());
            CommonMessage.printMsg(null, "", e.getMessage(), null, res);
        }
        finally {
            try {
                if (conn != null) {
                    trx.close();
                }
            }
            catch (Exception ex3) {}
        }
        try {
            if (conn != null) {
                trx.close();
            }
        }
        catch (Exception ex4) {}
    }
}
