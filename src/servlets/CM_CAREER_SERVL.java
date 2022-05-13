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
import beans.CM_CAREER_DAO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class CM_CAREER_SERVL extends HttpServlet
{
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        final CM_CAREER_DAO cm_CAREER_DAO = new CM_CAREER_DAO();
        final CommUtil commUtil = new CommUtil();
        final String retSpace = CommUtil.retSpace(httpServletRequest.getParameter("flag"));
        final String retSpace2 = CommUtil.retSpace(httpServletRequest.getParameter("actions"));
        final String retSpace3 = CommUtil.retSpace(httpServletRequest.getParameter("cd"));
        final String retSpace4 = CommUtil.retSpace(httpServletRequest.getParameter("cdNm"));
        final String retSpace5 = CommUtil.retSpace(httpServletRequest.getParameter("summary"));
        final String retSpace6 = CommUtil.retSpace(httpServletRequest.getParameter("clasify"));
        String insert = "";
        if (retSpace.equals("Cookie")) {
            try {
                httpServletResponse.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/CM_CAREER.jsp");
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
                insert = cm_CAREER_DAO.insert(retSpace3, retSpace4, retSpace5, httpServletRequest, connection);
            }
            else if ("DELETE".equals(retSpace2)) {
                for (int n = 0; httpServletRequest.getParameterValues("arrListId") != null && httpServletRequest.getParameterValues("arrListId").length >= n + 1; ++n) {
                    cm_CAREER_DAO.delete(CommUtil.retSpace(httpServletRequest.getParameterValues("arrListId")[n]), httpServletRequest, connection);
                }
            }
            else if ("UPDATE".equals(retSpace2)) {
                cm_CAREER_DAO.update(retSpace3, retSpace4, retSpace5, httpServletRequest, connection);
            }
            connection.commit();
            connection.setAutoCommit(true);
            httpServletResponse.sendRedirect("http://muasamcong.mpi.gov.vn:8070/AD/CM_CAREER.jsp?cd=" + retSpace3 + "&cdNm=" + retSpace4 + "&summary=" + retSpace5 + "&clasify=" + retSpace6 + "&actions=" + retSpace2 + "&notice=" + insert);
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
