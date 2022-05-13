// 
// Decompiled by Procyon v0.5.30
// 

package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class HP_PAV_UserView extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String url = "/jsp/RA/user/HP_PAJ_UserView.jsp?gubun=0";
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String url = "/jsp/RA/user/HP_PAJ_UserView.jsp";
        final HP_PAB_UserSB userSB = new HP_PAB_UserSB();
        int count = 0;
        final String gubun = (request.getParameter("gubun") == null) ? "" : request.getParameter("gubun");
        final String usergubun = (request.getParameter("usergubun") == null) ? "" : request.getParameter("usergubun");
        final String searchgubun = (request.getParameter("searchgubun") == null) ? "" : request.getParameter("searchgubun");
        final String searchword = (request.getParameter("searchword") == null) ? "" : request.getParameter("searchword");
        final String npage = (request.getParameter("npage") == null) ? "1" : request.getParameter("npage");
        final String size = (request.getParameter("size") == null) ? "10" : request.getParameter("size");
        if (gubun.equals("0")) {
            final RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/RA/user/HP_PAJ_UserView.jsp?gubun=0");
            dispatcher.forward((ServletRequest)request, (ServletResponse)response);
        }
        count = userSB.getUserListCount(usergubun, searchgubun, searchword);
        final HP_PAE_UserList userList = userSB.getUserListEB(usergubun, searchgubun, searchword, Integer.parseInt(size), (Integer.parseInt(npage) - 1) * Integer.parseInt(size) + 1);
        request.setAttribute("gubun", (Object)gubun);
        request.setAttribute("usergubun", (Object)usergubun);
        request.setAttribute("searchgubun", (Object)searchgubun);
        request.setAttribute("searchword", (Object)searchword);
        request.setAttribute("count", (Object)String.valueOf(count));
        request.setAttribute("userList", (Object)userList);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
}
