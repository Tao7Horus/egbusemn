// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import common.Log;
import entity.UM_ADE_GroupE010b;
import dao.UM_ADB_GroupDbDAO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_GroupListDAO extends HttpServlet
{
    private static final String ACTION_SAVE = "save";
    private static final String ACTION_DELETE = "delete";
    private static final String ACTION_UPDATE = "update";
    private static final String ACTION_SEARCH = "search";
    private static final String DATE_FORMAT = "yyyy/MM/dd";
    
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        final UM_ADB_GroupDbDAO um_ADB_GroupDbDAO = new UM_ADB_GroupDbDAO();
        final UM_ADE_GroupE010b um_ADE_GroupE010b = new UM_ADE_GroupE010b();
        final String parameter = httpServletRequest.getParameter("groupName");
        final String parameter2 = httpServletRequest.getParameter("description");
        final String parameter3 = httpServletRequest.getParameter("cmd");
        if (parameter3 != null && parameter3.length() != 0 && parameter3.equals("save")) {
            try {
                um_ADE_GroupE010b.setGroup_name(parameter);
                um_ADE_GroupE010b.setDescription(parameter2);
                um_ADB_GroupDbDAO.insertGroup(um_ADE_GroupE010b);
                httpServletResponse.sendRedirect("/AD/UM_ADJ_GroupList.jsp");
            }
            catch (Exception ex) {
                Log.debug("Loi GroupListDAO :" + ex.toString());
            }
        }
        else if (parameter3 != null && parameter3.length() != 0 && parameter3.equals("update")) {
            try {
                um_ADE_GroupE010b.setGroup_id(Integer.parseInt(httpServletRequest.getParameter("groupId")));
                um_ADE_GroupE010b.setGroup_name(parameter);
                um_ADE_GroupE010b.setDescription(parameter2);
                um_ADB_GroupDbDAO.updateGroup(um_ADE_GroupE010b);
                httpServletResponse.sendRedirect("/AD/UM_ADJ_GroupList.jsp");
            }
            catch (Exception ex2) {
                Log.debug("Loi GroupListDAO :" + ex2.toString());
            }
        }
        else if (parameter3 != null && parameter3.length() != 0 && parameter3.equals("delete")) {
            try {
                for (int n = 0; httpServletRequest.getParameterValues("arrListId") != null && httpServletRequest.getParameterValues("arrListId").length >= n + 1; ++n) {
                    final int int1 = Integer.parseInt(httpServletRequest.getParameterValues("arrListId")[n]);
                    final UM_ADE_GroupE010b um_ADE_GroupE010b2 = new UM_ADE_GroupE010b();
                    um_ADE_GroupE010b2.setGroup_id(int1);
                    um_ADB_GroupDbDAO.deleteGroup(um_ADE_GroupE010b2);
                }
                httpServletResponse.sendRedirect("/AD/UM_ADJ_GroupList.jsp");
            }
            catch (Exception ex3) {
                Log.debug("Loi GroupListDAO :" + ex3.toString());
            }
        }
        else if (parameter3 != null && parameter3.length() != 0 && parameter3.equals("search")) {
            try {
                um_ADB_GroupDbDAO.getGroupByName(parameter, parameter2);
                httpServletResponse.sendRedirect("/AD/UM_ADJ_GroupList.jsp");
            }
            catch (Exception ex4) {
                Log.debug("Loi GroupListDAO :" + ex4.toString());
            }
        }
    }
}
