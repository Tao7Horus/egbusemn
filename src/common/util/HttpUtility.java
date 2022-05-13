// 
// Decompiled by Procyon v0.5.30
// 

package common.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class HttpUtility
{
    public static String getIP(final HttpServletRequest httpServletRequest) throws ServletException, IOException {
        String s = httpServletRequest.getHeader("x-forwarded-for");
        if (s == null) {
            s = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (s == null) {
            s = httpServletRequest.getRemoteAddr();
        }
        if (s == null) {
            s = "Không tra cứu được IP";
        }
        return s;
    }
}
