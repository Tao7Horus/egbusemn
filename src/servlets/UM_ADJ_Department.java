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
import beans.UM_ADB_ConrDepartmentDAO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADJ_Department extends HttpServlet
{
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        final UM_ADB_ConrDepartmentDAO um_ADB_ConrDepartmentDAO = new UM_ADB_ConrDepartmentDAO();
        final CommUtil commUtil = new CommUtil();
        final String retSpace = CommUtil.retSpace(httpServletRequest.getParameter("flag"));
        final String retSpace2 = CommUtil.retSpace(httpServletRequest.getParameter("actions"));
        String retSpace3 = CommUtil.retSpace(httpServletRequest.getParameter("depName"));
        String retSpace4 = CommUtil.retSpace(httpServletRequest.getParameter("depDescription"));
        if (retSpace.equals("Cookie")) {
            try {
                httpServletResponse.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/UM_ADJ_ConrDepartment.jsp");
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
                um_ADB_ConrDepartmentDAO.insert(retSpace3, retSpace4, httpServletRequest, connection);
                retSpace3 = "";
                retSpace4 = "";
            }
            else if ("DELETE".equals(retSpace2)) {
                for (int n = 0; httpServletRequest.getParameterValues("arrListId") != null && httpServletRequest.getParameterValues("arrListId").length >= n + 1; ++n) {
                    um_ADB_ConrDepartmentDAO.delete(CommUtil.retSpace(httpServletRequest.getParameterValues("arrListId")[n]), httpServletRequest, connection);
                }
            }
            else if ("UPDATE".equals(retSpace2)) {
                int int1 = 0;
                if (httpServletRequest.getParameter("depId") != null && !"null".equals(httpServletRequest.getParameter("depId"))) {
                    int1 = Integer.parseInt(CommUtil.retSpace(httpServletRequest.getParameter("depId")));
                }
                um_ADB_ConrDepartmentDAO.update(int1, retSpace3, retSpace4, httpServletRequest, connection);
                retSpace3 = "";
                retSpace4 = "";
            }
            connection.commit();
            connection.setAutoCommit(true);
            httpServletResponse.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/UM_ADJ_ConrDepartment.jsp?depName=" + retSpace3 + "&depDescription=" + retSpace4);
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
