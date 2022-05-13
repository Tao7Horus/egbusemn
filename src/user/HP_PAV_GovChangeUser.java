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

public class HP_PAV_GovChangeUser extends HttpServlet
{
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String userid = new String();
        userid = request.getParameter("userid");
        final String url = "/RA/user/HP_PAJ_GovChange.jsp?userid=" + userid;
        final HP_PAB_CompUserSB govSB = new HP_PAB_CompUserSB();
        HP_PAE_UserInfo govEB = new HP_PAE_UserInfo();
        govEB = govSB.getUserEB(userid);
        request.setAttribute("govEB", (Object)govEB);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
}
