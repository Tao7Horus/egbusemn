// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

public class UM_KIJ_201s extends HttpServlet
{
    private final String errorPage = "/jsp/error.jsp";
    public final String jspPath = "/jsp/RA/";
    private String nextPage;
    private UM_KIJ_201w worker;
    private UM_KIJ_101w worker1;
    
    public UM_KIJ_201s() {
        this.worker = null;
        this.worker1 = null;
    }
    
    public void init(final ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        this.worker = new UM_KIJ_201w();
    }
    
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        this.worker1 = new UM_KIJ_101w();
        String parameter = httpServletRequest.getParameter("actionStr");
        if (parameter == null || parameter.equals("")) {
            parameter = "start";
        }
        if (parameter.equals("start")) {
            this.setNextPage("/jsp/RA/index.jsp");
        }
        else if (parameter.equals("selectCodeList")) {
            this.worker1.selectCodeGbn(httpServletRequest);
            this.worker.selectCodeList(httpServletRequest);
            this.setNextPage("/jsp/RA/UM_KIJ_201s.jsp");
        }
        else if (parameter.equals("addCode")) {
            this.worker.addCode(httpServletRequest);
            this.setNextPage("/jsp/RA/UM_KIJ_201i.jsp");
        }
        else if (parameter.equals("updateCode")) {
            this.worker.updateCode(httpServletRequest);
            this.setNextPage("/jsp/RA/UM_KIJ_201u.jsp");
        }
        else if (parameter.equals("deleteCode")) {
            this.worker.deleteCode(httpServletRequest);
            this.worker1.selectCodeGbn(httpServletRequest);
            this.worker.selectCodeList(httpServletRequest);
            this.setNextPage("/jsp/RA/UM_KIJ_201s.jsp");
        }
        this.openNextPage(this.nextPage, httpServletRequest, httpServletResponse);
    }
    
    protected void openNextPage(String s, final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (s == null) {
            s = "/jsp/error.jsp";
        }
        this.getServletContext().getRequestDispatcher(s).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    protected void setNextPage(final String nextPage) {
        this.nextPage = nextPage;
    }
}
