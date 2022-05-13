// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class CheckEncoding extends HttpServlet
{
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html; charset=utf-8");
        final PrintWriter writer = httpServletResponse.getWriter();
        httpServletRequest.setCharacterEncoding("utf-8");
        final String parameter = httpServletRequest.getParameter("str");
        Log.debug(parameter);
        Log.debug(URLDecoder.decode(parameter, "UTF-8"));
        writer.println(parameter);
    }
}
