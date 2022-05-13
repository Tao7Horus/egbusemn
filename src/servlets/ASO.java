// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ASO extends ASOBase
{
    private static final String ASOLoginURL = "http://www.g2b.go.kr:8070/jsp/common/loginPage.jsp";
    private static final String ASOLogoutURL = "http://www.g2b.go.kr:8070/jsp/common/logout.jsp";
    
    public ASO(final HttpServletRequest httpservletrequest, final HttpServletResponse httpservletresponse) {
        super(httpservletrequest, httpservletresponse, "http://www.g2b.go.kr:8070/jsp/common/loginPage.jsp", "http://www.g2b.go.kr:8070/jsp/common/logout.jsp");
    }
}
