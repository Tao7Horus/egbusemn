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
import beans.UM_URB_GovuA030c1;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADJ_UpdateBuyerInfo extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        final UM_URB_GovuA030c1 um_URB_GovuA030c1 = new UM_URB_GovuA030c1();
        final CommUtil commUtil = new CommUtil();
        final String retSpace = CommUtil.retSpace(req.getParameter("flag"));
        final String retSpace2 = CommUtil.retSpace(req.getParameter("department"));
        final String retSpace3 = CommUtil.retSpace(req.getParameter("group"));
        if (retSpace.equals("Cookie")) {
            try {
                httpServletResponse.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/UM_ADJ_ConuL020l10.jsp");
            }
            catch (Exception ex) {
                CommonMessage.printMsg(null, "", ex.getMessage(), null, httpServletResponse);
            }
            return;
        }
        Trx trx = null;
        Connection connection = null;
        try {
            trx = new Trx(this);
            connection = trx.getConnection();
            connection.setAutoCommit(false);
            for (int n = 0; req.getParameterValues("arrListId") != null && req.getParameterValues("arrListId").length >= n + 1; ++n) {
                final String instituCd = req.getParameterValues("arrListId")[n];
                um_URB_GovuA030c1.updateInfo(instituCd, Integer.parseInt(CommUtil.retSpace(req.getParameter("groupNo" + instituCd))), Integer.parseInt(CommUtil.retSpace(req.getParameter("departmentNo" + instituCd))), req, connection);
            }
            connection.commit();
            connection.setAutoCommit(true);
            httpServletResponse.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/UM_ADJ_ConuL020l10.jsp?department=" + retSpace2 + "&group=" + retSpace3);
        }
        catch (Exception ex2) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            }
            catch (Exception ex3) {}
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            }
            catch (Exception ex4) {}
            Log.debug("UM_ADJ_UpdateBuyerInfo : " + ex2.toString());
            CommonMessage.printMsg(null, "", ex2.getMessage(), null, httpServletResponse);
        }
        finally {
            try {
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex5) {}
        }
    }
}
