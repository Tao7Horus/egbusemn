// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import g2b.sso.SSO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class SSOLoginServlets extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter out = response.getWriter();
        final SSO sso = new SSO(request, response);
        sso.checkLogin();
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<body>");
        out.println("<h2> G2B 사용자 정보 확인</h2>");
        out.println("<hr>");
        out.println("사용자 ID:" + sso.getID() + "<br>");
        out.println("사용자 구분:" + sso.getGubun() + "<br>");
        out.println("사용자 마스터코드:" + sso.getMCode() + "<br>");
        out.println("사용자 담당자명:" + sso.getName() + "<br>");
        out.println("사용자 마스터코드명:" + sso.getMName() + "<br>");
        out.println("</body>");
        out.println("</html>");
    }
}
