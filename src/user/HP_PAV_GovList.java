// 
// Decompiled by Procyon v0.5.30
// 

package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class HP_PAV_GovList extends HttpServlet
{
    public void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/jsp/RA/user/HP_PAJ_GovList.jsp?gubun=0").forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s = "/jsp/RA/user/HP_PAJ_GovList.jsp";
        final HP_PAB_GovUserSB hp_PAB_GovUserSB = new HP_PAB_GovUserSB();
        final String s2 = (httpServletRequest.getParameter("refid") == null) ? "" : httpServletRequest.getParameter("refid");
        final String s3 = (httpServletRequest.getParameter("npage") == null) ? "1" : httpServletRequest.getParameter("npage");
        final String s4 = (httpServletRequest.getParameter("size") == null) ? "10" : httpServletRequest.getParameter("size");
        final int govUserListCount = hp_PAB_GovUserSB.getGovUserListCount(s2);
        final HP_PAE_UserList govUserListEB = hp_PAB_GovUserSB.getGovUserListEB(s2, Integer.parseInt(s4), (Integer.parseInt(s3) - 1) * Integer.parseInt(s4) + 1);
        httpServletRequest.setAttribute("count", (Object)String.valueOf(govUserListCount));
        httpServletRequest.setAttribute("userList", (Object)govUserListEB);
        httpServletRequest.getRequestDispatcher(s).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
}
