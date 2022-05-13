// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import common.Trx;
import common.util.CommonMessage;
import common.util.CommUtil;
import beans.CM_CONNECT_DAO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class CM_CONNECT_SERVL extends HttpServlet
{
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        final CM_CONNECT_DAO cm_CONNECT_DAO = new CM_CONNECT_DAO();
        final CommUtil commUtil = new CommUtil();
        final String retSpace = CommUtil.retSpace(httpServletRequest.getParameter("flag"));
        final String retSpace2 = CommUtil.retSpace(httpServletRequest.getParameter("actions"));
        final String retSpace3 = CommUtil.retSpace(httpServletRequest.getParameter("name"));
        final String retSpace4 = CommUtil.retSpace(httpServletRequest.getParameter("url"));
        final String retSpace5 = CommUtil.retSpace(httpServletRequest.getParameter("description"));
        if (retSpace.equals("Cookie")) {
            try {
                httpServletResponse.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/CM_CONNECT.jsp");
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
            if ("INSERT".equals(retSpace2)) {
                cm_CONNECT_DAO.insert(retSpace3, retSpace4, retSpace5, httpServletRequest, connection);
            }
            else if ("DELETE".equals(retSpace2)) {
                for (int n = 0; httpServletRequest.getParameterValues("arrListId") != null && httpServletRequest.getParameterValues("arrListId").length >= n + 1; ++n) {
                    cm_CONNECT_DAO.delete(CommUtil.retSpace(httpServletRequest.getParameterValues("arrListId")[n]), httpServletRequest, connection);
                }
            }
            else if ("UPDATE".equals(retSpace2)) {
                int int1 = 0;
                if (httpServletRequest.getParameter("connectId") != null && !"null".equals(httpServletRequest.getParameter("connectId"))) {
                    int1 = Integer.parseInt(CommUtil.retSpace(httpServletRequest.getParameter("connectId")));
                }
                cm_CONNECT_DAO.update(int1, retSpace3, retSpace4, retSpace5, httpServletRequest, connection);
            }
            connection.commit();
            connection.setAutoCommit(true);
            httpServletResponse.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/CM_CONNECT.jsp?name=" + retSpace3 + "&url=" + retSpace4 + "&description=" + retSpace5);
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
