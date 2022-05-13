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

public class HP_PAV_CompUserRecognition extends HttpServlet
{
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s = new String();
        final String s2 = "/servlet/user.HP_PAV_CompUserView";
        new HP_PAB_CompUserSB().recognitionUser((httpServletRequest.getParameter("recognitionID") == null) ? "" : httpServletRequest.getParameter("recognitionID"), (httpServletRequest.getParameter("recognYN") == null) ? "" : httpServletRequest.getParameter("recognYN"));
        httpServletRequest.getRequestDispatcher(s2).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
}
