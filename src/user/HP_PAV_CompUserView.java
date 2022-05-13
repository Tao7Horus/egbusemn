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

public class HP_PAV_CompUserView extends HttpServlet
{
    public void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s = "/jsp/RA/user/HP_PAJ_CompUserView.jsp?gubun=1&entdivide=1";
        final HP_PAB_CompUserSB hp_PAB_CompUserSB = new HP_PAB_CompUserSB();
        final int compUserListCount = hp_PAB_CompUserSB.getCompUserListCount("", "", "1");
        final HP_PAE_UserList compUserListEB = hp_PAB_CompUserSB.getCompUserListEB("", "", "1", 10, 1);
        httpServletRequest.setAttribute("count", (Object)String.valueOf(compUserListCount));
        httpServletRequest.setAttribute("userList", (Object)compUserListEB);
        httpServletRequest.getRequestDispatcher(s).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s = "/jsp/RA/user/HP_PAJ_CompUserView.jsp";
        final HP_PAB_CompUserSB hp_PAB_CompUserSB = new HP_PAB_CompUserSB();
        final String s2 = (httpServletRequest.getParameter("gubun") == null) ? "" : httpServletRequest.getParameter("gubun");
        final String s3 = (httpServletRequest.getParameter("enterprise") == null) ? "" : httpServletRequest.getParameter("enterprise");
        final String s4 = (httpServletRequest.getParameter("businessno1") == null) ? "" : httpServletRequest.getParameter("businessno1");
        final String s5 = (httpServletRequest.getParameter("businessno2") == null) ? "" : httpServletRequest.getParameter("businessno2");
        final String s6 = (httpServletRequest.getParameter("businessno3") == null) ? "" : httpServletRequest.getParameter("businessno3");
        final String s7 = (httpServletRequest.getParameter("entdivide") == null) ? "" : httpServletRequest.getParameter("entdivide");
        final String s8 = (httpServletRequest.getParameter("npage") == null) ? "1" : httpServletRequest.getParameter("npage");
        final String s9 = (httpServletRequest.getParameter("size") == null) ? "10" : httpServletRequest.getParameter("size");
        if (s2.equals("0")) {
            httpServletRequest.getRequestDispatcher("/jsp/RA/user/HP_PAJ_CompUserView.jsp?gubun=0").forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
        }
        final int compUserListCount = hp_PAB_CompUserSB.getCompUserListCount(s3, s4 + s5 + s6, s7);
        final HP_PAE_UserList compUserListEB = hp_PAB_CompUserSB.getCompUserListEB(s3, s4 + s5 + s6, s7, Integer.parseInt(s9), (Integer.parseInt(s8) - 1) * Integer.parseInt(s9) + 1);
        httpServletRequest.setAttribute("gubun", (Object)s2);
        httpServletRequest.setAttribute("enterprise", (Object)s3);
        httpServletRequest.setAttribute("businessno1", (Object)s4);
        httpServletRequest.setAttribute("businessno2", (Object)s5);
        httpServletRequest.setAttribute("businessno3", (Object)s6);
        httpServletRequest.setAttribute("entdivide", (Object)s7);
        httpServletRequest.setAttribute("count", (Object)String.valueOf(compUserListCount));
        httpServletRequest.setAttribute("userList", (Object)compUserListEB);
        httpServletRequest.getRequestDispatcher(s).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
}
