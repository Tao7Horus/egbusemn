// 
// Decompiled by Procyon v0.5.30
// 

package exms;

import javax.servlet.http.Cookie;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;

public class Fn
{
    public static String rplc(final String str, final String n1, final String n2) {
        int itmp = 0;
        if (str == null) {
            return "";
        }
        String tmp = str;
        final StringBuffer sb = new StringBuffer();
        sb.append("");
        while (tmp.indexOf(n1) > -1) {
            itmp = tmp.indexOf(n1);
            sb.append(tmp.substring(0, itmp));
            sb.append(n2);
            tmp = tmp.substring(itmp + n1.length());
        }
        sb.append(tmp);
        return sb.toString();
    }
    
    public static String crop(final String str, final int i) {
        if (str == null) {
            return "";
        }
        String tmp = str;
        if (tmp.length() > i) {
            tmp = String.valueOf(tmp.substring(0, i)) + "...";
        }
        return tmp;
    }
    
    public static String wwwlink(final String str) {
        if (str == null) {
            return "";
        }
        String tmp = str;
        int itmp = 0;
        int wend = 0;
        final StringBuffer sb = new StringBuffer();
        sb.append("");
        while (tmp.indexOf("http://") > -1) {
            itmp = tmp.indexOf("http://");
            wend = tmp.indexOf(" ", itmp);
            if (wend > tmp.indexOf("\r", itmp)) {
                wend = tmp.indexOf("\r", itmp);
            }
            if (wend > tmp.indexOf("<", itmp)) {
                wend = tmp.indexOf("<", itmp);
            }
            else if (wend == -1) {
                wend = tmp.length();
            }
            sb.append(tmp.substring(0, itmp));
            if (itmp > 3 && tmp.substring(itmp - 3, itmp).indexOf("=") > -1) {
                wend = tmp.indexOf("</a>", itmp) + 3;
                if (wend == 2) {
                    wend = tmp.indexOf(">", itmp);
                }
                sb.append(tmp.substring(itmp, wend));
            }
            else {
                sb.append("<a href=\"" + tmp.substring(itmp, wend) + "\" target=\"_top\" class=\"ok-2\">");
                sb.append(tmp.substring(itmp, wend));
                sb.append("</a>");
            }
            tmp = tmp.substring(wend);
        }
        sb.append(tmp);
        tmp = sb.toString();
        sb.setLength(0);
        while (tmp.indexOf("www.") > -1) {
            itmp = tmp.indexOf("www.");
            wend = tmp.indexOf(" ", itmp);
            if (wend > tmp.indexOf("\r", itmp)) {
                wend = tmp.indexOf("\r", itmp);
            }
            if (wend > tmp.indexOf("<", itmp)) {
                wend = tmp.indexOf("<", itmp);
            }
            if (wend == -1) {
                wend = tmp.length();
            }
            sb.append(tmp.substring(0, itmp));
            if (itmp > 10 && tmp.substring(itmp - 10, itmp).indexOf("=") > -1) {
                wend = tmp.indexOf("</a>", itmp) + 3;
                if (wend == 2) {
                    wend = tmp.indexOf(">", itmp);
                }
                sb.append(tmp.substring(itmp, wend));
            }
            else {
                sb.append("<a href=\"http://" + tmp.substring(itmp, wend) + "\" target=\"_top\" class=\"ok-2\">");
                sb.append(tmp.substring(itmp, wend));
                sb.append("</a>");
            }
            tmp = tmp.substring(wend);
        }
        sb.append(tmp);
        return sb.toString();
    }
    
    public static void setCookie(final HttpServletResponse response, final String name, String value, final int iMinute) {
        value = URLEncoder.encode(value);
        final Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60 * iMinute);
        response.addCookie(cookie);
    }
    
    public static void setCookie(final HttpServletResponse response, final String name, String value) {
        value = URLEncoder.encode(value);
        final Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(1296000);
        response.addCookie(cookie);
    }
}
