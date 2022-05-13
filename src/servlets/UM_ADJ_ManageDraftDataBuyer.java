// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import common.Trx;
import common.util.CommonMessage;
import common.Log;
import common.util.CommUtil;
import beans.UM_ADB_GovrB030c;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADJ_ManageDraftDataBuyer extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        final UM_ADB_GovrB030c uagb030 = new UM_ADB_GovrB030c();
        final CommUtil cu = new CommUtil();
        final String flag = CommUtil.retSpace(req.getParameter("flag"));
        final String draftData = CommUtil.retSpace(req.getParameter("draftData"));
        final String functionHiden = CommUtil.retSpace(req.getParameter("functionHiden"));
        Log.debug("....functionHiden " + functionHiden);
        if ("01".equals(functionHiden)) {
            try {
                uagb030.hiddenAllCTS_um_pub_institu_mast();
                res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/UM_ADJ_ConuL020l9.jsp?draftData=" + draftData);
                return;
            }
            catch (Exception ex) {
                CommonMessage.printMsg(null, "", ex.getMessage(), null, res);
            }
        }
        if (flag.equals("Cookie")) {
            try {
                res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/UM_ADJ_ConuL020l9.jsp");
            }
            catch (Exception ef) {
                CommonMessage.printMsg(null, "", ef.getMessage(), null, res);
            }
            return;
        }
        Trx trx = null;
        Connection con = null;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            con.setAutoCommit(false);
            for (int i = 0; req.getParameterValues("arrListId") != null && req.getParameterValues("arrListId").length >= i + 1; ++i) {
                final String instituCD = req.getParameterValues("arrListId")[i];
                if ("N".equals(draftData)) {
	                uagb030.updateDraftDataBuyer(instituCD, draftData, req, con);
	                con.commit();
	                con.setAutoCommit(true);
	                uagb030.updateDraftRecDataBuyer(instituCD, draftData, req, con);
	                con.commit();
	                con.setAutoCommit(true);
                }
            }
            res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/UM_ADJ_ConuL020l9.jsp?draftData=" + draftData);
        }
        catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            }
            catch (Exception ex2) {}
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                }
            }
            catch (Exception ex3) {}
            Log.debug("UM_ADJ_GovrB030c : " + e.toString());
            CommonMessage.printMsg(null, "", e.getMessage(), null, res);
        }
        finally {
            try {
                if (con != null) {
                    trx.close();
                }
            }
            catch (Exception ex4) {}
        }
        try {
            if (con != null) {
                trx.close();
            }
        }
        catch (Exception ex5) {}
    }
}
