// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;

public class NoticeCookie
{
    public static final String gongjiName = "illegalbid";
    public static final String gongjiValue = "done";
    
    public void setCookie(final HttpServletResponse httpServletResponse, final int n) {
        try {
            final Cookie cookie = new Cookie("illegalbid", URLEncoder.encode("done"));
            if (n != -1) {
                cookie.setMaxAge(n * 24 * 60 * 60);
            }
            httpServletResponse.addCookie(cookie);
        }
        catch (Exception ex) {
            Log.errors("투찰 공지사항 setCookies Error : \n" + ex);
        }
    }
    
    public String getCookie(final HttpServletRequest httpServletRequest) {
        String decode = "";
        final Cookie[] cookies = httpServletRequest.getCookies();
        try {
            for (int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equals("illegalbid")) {
                    decode = URLDecoder.decode(cookies[i].getValue());
                    break;
                }
            }
        }
        catch (Exception ex) {
            Log.errors("투찰 공지사항 GetCookie Error : \n" + ex);
        }
        return decode;
    }
}
