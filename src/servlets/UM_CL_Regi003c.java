// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import common.util.CommonMessage;
import common.Log;
import common.Trx;
import beans.UM_CL_Regi_001p;
import common.util.CommUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_CL_Regi003c extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        final CommUtil cu = new CommUtil();
        final String accNumberList = CommUtil.retSpace(req.getParameter("accNumberList"));
        final String cdCls = CommUtil.retSpace(req.getParameter("cdCls"));
        final UM_CL_Regi_001p regi001p = new UM_CL_Regi_001p();
        Trx trx = null;
        Connection conn = null;
        try {
            trx = new Trx(this);
            conn = trx.getConnection();
            conn.setAutoCommit(false);
            regi001p.delCodeList(accNumberList, cdCls, conn);
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
            Log.debug("UM_EY_Regi_001c : " + e.toString());
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
