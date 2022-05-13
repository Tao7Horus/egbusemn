// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.http.HttpServletRequest;

public class EP_COB_GTQ809
{
    public static String getNextPageIndexes(final String s, final int n, final int n2, final int n3) {
        final String s2 = "";
        if (n <= n2) {
            return s2;
        }
        final int n4 = ((n3 % 10 == 0) ? (n3 - 1) : n3) / 10 * 10 + 1;
        final int n5 = n / n2 + ((n % n2 != 0) ? 1 : 0);
        final int n6 = (n5 - n4 >= 10) ? (n4 + 9) : n5;
        final String string = s2 + "<table width=100% border=0 cellpadding=2 cellspacing=2 class=tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr><tr><td align=center class=redc>";
        String s3;
        if (n3 > 10) {
            s3 = string + "<a href='" + s + "&page_no=" + (n4 - 1) + "'><IMG ALIGN=absmiddle SRC=/img/bu_backarw.gif BORDER=0 alt='10Trước'></a>";
        }
        else if (n3 > 1) {
            s3 = string + "<a href='" + s + "&page_no=" + (n3 - 1) + "'>" + "<IMG ALIGN=absmiddle SRC=/img/bu_backarwn.gif BORDER=0 alt='10Trước'>" + "</a>";
        }
        else {
            s3 = string + "<IMG ALIGN=absmiddle SRC=/img/bu_backarwn.gif BORDER=0 alt='10Trước'>";
        }
        for (int i = n4; i <= n6; ++i) {
            if (n3 == i) {
                s3 = s3 + "[" + i + "]";
            }
            else {
                s3 = s3 + "<a href='" + s + "&page_no=" + i + "'>[" + i + "]</a>";
            }
        }
        String s4;
        if (n6 != n5) {
            s4 = s3 + "<a href='" + s + "&page_no=" + (n6 + 1) + "'><IMG ALIGN=absmiddle SRC=/img/bu_nextarw.gif BORDER=0 alt='10 Sau'></a><a href='" + s + "&page_no=" + (n6 + 1) + "'></a>";
        }
        else if (n3 < n6) {
            s4 = s3 + "<a href='" + s + "&page_no=" + (n3 + 1) + "'>" + "<IMG ALIGN=absmiddle SRC=/img/bu_nextarwn.gif BORDER=0 alt='10 Sau'>" + "</a>";
        }
        else {
            s4 = s3 + "<IMG ALIGN=absmiddle SRC=/img/bu_nextarwn.gif BORDER=0 alt='10 Sau'>";
        }
        return s4 + "</td></tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr></table>";
    }
    
    public static String getNextPageIndexes(final String s, final int n, final int n2, final String s2, final int n3) {
        final String s3 = "";
        if (n <= n2) {
            return s3;
        }
        final int n4 = ((n3 % 10 == 0) ? (n3 - 1) : n3) / 10 * 10 + 1;
        final int n5 = n / n2 + ((n % n2 != 0) ? 1 : 0);
        final int n6 = (n5 - n4 >= 10) ? (n4 + 9) : n5;
        final String string = s3 + "<table width=100% border=0 cellpadding=2 cellspacing=2 class=tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr><tr><td align=center class=redc>";
        String s4;
        if (n3 > 10) {
            s4 = string + "<a href='" + s + "&" + s2 + "=" + (n4 - 1) + "'><IMG ALIGN=absmiddle SRC=/img/bu_backarw.gif BORDER=0 alt='10Trước'></a>";
        }
        else if (n3 > 1) {
            s4 = string + "<a href='" + s + "&page_no=" + (n3 - 1) + "'>" + "<IMG ALIGN=absmiddle SRC=/img/bu_backarwn.gif BORDER=0 alt='10Trước'>" + "</a>";
        }
        else {
            s4 = string + "<IMG ALIGN=absmiddle SRC=/img/bu_backarwn.gif BORDER=0 alt='10Trước'>";
        }
        for (int i = n4; i <= n6; ++i) {
            if (n3 == i) {
                s4 = s4 + "[" + i + "]";
            }
            else {
                s4 = s4 + "<a href='" + s + "&" + s2 + "=" + i + "'>[" + i + "]</a>";
            }
        }
        String s5;
        if (n6 != n5) {
            s5 = s4 + "<a href='" + s + "&" + s2 + "=" + (n6 + 1) + "'><IMG ALIGN=absmiddle SRC=/img/bu_nextarw.gif BORDER=0 alt='10 Sau'></a><a href='" + s + "&" + s2 + "=" + (n6 + 1) + "'></a>";
        }
        else if (n3 < n6) {
            s5 = s4 + "<a href='" + s + "&page_no=" + (n3 + 1) + "'>" + "<IMG ALIGN=absmiddle SRC=/img/bu_nextarwn.gif BORDER=0 alt='10 Sau'>" + "</a>";
        }
        else {
            s5 = s4 + "<IMG ALIGN=absmiddle SRC=/img/bu_nextarwn.gif BORDER=0 alt='10 Sau'>";
        }
        return s5 + "</td></tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr></table>";
    }
    
    public static String getNextPageIndexes(final HttpServletRequest httpServletRequest, String requestURI, final int n, final int n2, final int n3) {
        final String s = "";
        if (requestURI == null) {
            requestURI = httpServletRequest.getRequestURI();
        }
        final String url = getUrl(httpServletRequest, requestURI);
        final String substring = url.substring(0, url.lastIndexOf("&"));
        if (n <= n2) {
            return s;
        }
        final int n4 = ((n3 % 10 == 0) ? (n3 - 1) : n3) / 10 * 10 + 1;
        final int n5 = n / n2 + ((n % n2 != 0) ? 1 : 0);
        final int n6 = (n5 - n4 >= 10) ? (n4 + 9) : n5;
        final String string = s + "<table width=100% border=0 cellpadding=2 cellspacing=2 class=tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr><tr><td align=center class=redc>";
        String s2;
        if (n3 > 10) {
            s2 = string + "<a href='" + substring + "&page_no=" + (n4 - 1) + "'><img align=absmiddle src=/img/bu_backarw.gif border=0 alt='10Trước'></a>";
        }
        else if (n3 > 1) {
            s2 = string + "<img align=absmiddle src=/img/bu_backarwn.gif border=0 alt='10Trước'>";
        }
        else {
            s2 = string + "<img align=absmiddle src=/img/bu_backarwn.gif border=0 alt='10Trước'>";
        }
        for (int i = n4; i <= n6; ++i) {
            if (n3 == i) {
                s2 = s2 + "[" + i + "]";
            }
            else {
                s2 = s2 + "<a href='" + substring + "&page_no=" + i + "'>[" + i + "]</a>";
            }
        }
        String s3;
        if (n6 != n5) {
            s3 = s2 + "<a href='" + substring + "&page_no=" + (n6 + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0 alt='10 Sau'></a><a href='" + substring + "&page_no=" + (n6 + 1) + "'></a>";
        }
        else if (n3 < n6) {
            s3 = s2 + "<img align=absmiddle src=/img/bu_nextarwn.gif border=0 alt='10 Sau'>";
        }
        else {
            s3 = s2 + "<img align=absmiddle src=/img/bu_nextarwn.gif border=0 alt='10 Sau'>";
        }
        return s3 + "</td></tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr></table>";
    }
    
    public static String getNextPageIndexes(final HttpServletRequest httpServletRequest, String requestURI, final int n, final int n2, final int n3, final String s) {
        final String s2 = "";
        if (requestURI == null) {
            requestURI = httpServletRequest.getRequestURI();
        }
        final String url = getUrl(httpServletRequest, requestURI);
        final String substring = url.substring(0, url.lastIndexOf("&"));
        if (n <= n2) {
            return s2;
        }
        final int n4 = ((n3 % 10 == 0) ? (n3 - 1) : n3) / 10 * 10 + 1;
        final int n5 = n / n2 + ((n % n2 != 0) ? 1 : 0);
        final int n6 = (n5 - n4 >= 10) ? (n4 + 9) : n5;
        final String string = s2 + "<table width=100% border=0 cellpadding=2 cellspacing=2 class=tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr><tr><td align=center class=redc>";
        String s3;
        if (n3 > 10) {
            if ("BP".equals(s)) {
                s3 = string + "<a href='" + substring + "&pageNoBP=" + (n4 - 1) + "'>";
            }
            else if ("PQ".equals(s)) {
                s3 = string + "<a href='" + substring + "&pageNoPQ=" + (n4 - 1) + "'>";
            }
            else if ("BID".equals(s)) {
                s3 = string + "<a href='" + substring + "&pageNoBID=" + (n4 - 1) + "'>";
            }
            else if ("Result".equals(s)) {
                s3 = string + "<a href='" + substring + "&pageNoResult=" + (n4 - 1) + "'>";
            }
            else if ("FAQ".equals(s)) {
                s3 = string + "<a href='" + substring + "&pageNoFAQ=" + (n4 - 1) + "'>";
            }
            else if ("AQ".equals(s)) {
                s3 = string + "<a href='" + substring + "&pageNoAQ=" + (n4 - 1) + "'>";
            }
            else if ("NTP".equals(s)) {
                s3 = string + "<a href='" + substring + "&pageNoNTP=" + (n4 - 1) + "'>";
            }
            else if ("NTG".equals(s)) {
                s3 = string + "<a href='" + substring + "&pageNoNTG=" + (n4 - 1) + "'>";
            }
            else {
                s3 = string + "<a href='" + substring + "&pageNoDOC=" + (n4 - 1) + "'>";
            }
        }
        else if (n3 > 1) {
            s3 = string + "<img align=absmiddle src=/img/bu_backarwn.gif border=0 alt='10Trước'>";
        }
        else {
            s3 = string + "<img align=absmiddle src=/img/bu_backarwn.gif border=0 alt='10Trước'>";
        }
        for (int i = n4; i <= n6; ++i) {
            if (n3 == i) {
                s3 = s3 + "[" + i + "]";
            }
            else if ("BP".equals(s)) {
                s3 = s3 + "<a href='" + substring + "&pageNoBP=" + i + "'>[" + i + "]</a>";
            }
            else if ("PQ".equals(s)) {
                s3 = s3 + "<a href='" + substring + "&pageNoPQ=" + i + "'>[" + i + "]</a>";
            }
            else if ("BID".equals(s)) {
                s3 = s3 + "<a href='" + substring + "&pageNoBID=" + i + "'>[" + i + "]</a>";
            }
            else if ("Result".equals(s)) {
                s3 = s3 + "<a href='" + substring + "&pageNoResult=" + i + "'>[" + i + "]</a>";
            }
            else if ("FAQ".equals(s)) {
                s3 = s3 + "<a href='" + substring + "&pageNoFAQ=" + i + "'>[" + i + "]</a>";
            }
            else if ("AQ".equals(s)) {
                s3 = s3 + "<a href='" + substring + "&pageNoAQ=" + i + "'>[" + i + "]</a>";
            }
            else if ("NTP".equals(s)) {
                s3 = s3 + "<a href='" + substring + "&pageNoNTP=" + i + "'>[" + i + "]</a>";
            }
            else if ("NTG".equals(s)) {
                s3 = s3 + "<a href='" + substring + "&pageNoNTG=" + i + "'>[" + i + "]</a>";
            }
            else {
                s3 = s3 + "<a href='" + substring + "&pageNoDOC=" + i + "'>[" + i + "]</a>";
            }
        }
        String s4;
        if (n6 != n5) {
            if ("BP".equals(s)) {
                s4 = s3 + "<a href='" + substring + "&pageNoBP=" + (n6 + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0 alt='10 Sau'></a><a href='" + substring + "&pageNoBP=" + (n6 + 1) + "'></a>";
            }
            else if ("PQ".equals(s)) {
                s4 = s3 + "<a href='" + substring + "&pageNoPQ=" + (n6 + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0 alt='10 Sau'></a><a href='" + substring + "&pageNoPQ=" + (n6 + 1) + "'></a>";
            }
            else if ("BID".equals(s)) {
                s4 = s3 + "<a href='" + substring + "&pageNoBID=" + (n6 + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0 alt='10 Sau'></a><a href='" + substring + "&pageNoBID=" + (n6 + 1) + "'></a>";
            }
            else if ("Result".equals(s)) {
                s4 = s3 + "<a href='" + substring + "&pageNoResult=" + (n6 + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0 alt='10 Sau'></a><a href='" + substring + "&pageNoResult=" + (n6 + 1) + "'></a>";
            }
            else if ("FAQ".equals(s)) {
                s4 = s3 + "<a href='" + substring + "&pageNoFAQ=" + (n6 + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0 alt='10 Sau'></a><a href='" + substring + "&pageNoFAQ=" + (n6 + 1) + "'></a>";
            }
            else if ("AQ".equals(s)) {
                s4 = s3 + "<a href='" + substring + "&pageNoAQ=" + (n6 + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0 alt='10 Sau'></a><a href='" + substring + "&pageNoAQ=" + (n6 + 1) + "'></a>";
            }
            else if ("NTP".equals(s)) {
                s4 = s3 + "<a href='" + substring + "&pageNoNTP=" + (n6 + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0 alt='10 Sau'></a><a href='" + substring + "&pageNoNTP=" + (n6 + 1) + "'></a>";
            }
            else if ("NTG".equals(s)) {
                s4 = s3 + "<a href='" + substring + "&pageNoNTG=" + (n6 + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0 alt='10 Sau'></a><a href='" + substring + "&pageNoNTG=" + (n6 + 1) + "'></a>";
            }
            else {
                s4 = s3 + "<a href='" + substring + "&pageNoDOC=" + (n6 + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0 alt='10 Sau'></a><a href='" + substring + "&pageNoDOC=" + (n6 + 1) + "'></a>";
            }
        }
        else if (n3 < n6) {
            s4 = s3 + "<img align=absmiddle src=/img/bu_nextarwn.gif border=0 alt='10 Sau'>";
        }
        else {
            s4 = s3 + "<img align=absmiddle src=/img/bu_nextarwn.gif border=0 alt='10 Sau'>";
        }
        return s4 + "</td></tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr></table>";
    }
    
    public static String getNextPageIndexes(final HttpServletRequest httpServletRequest, String requestURI, final int n, final int n2, final int n3, final Hashtable hashtable) {
        if (hashtable == null) {
            return getNextPageIndexes(httpServletRequest, requestURI, n, n2, n3);
        }
        final String s = "";
        if (requestURI == null) {
            requestURI = httpServletRequest.getRequestURI();
        }
        String s2 = getUrl(httpServletRequest, requestURI);
        if (hashtable != null) {
            s2 += getHashString(hashtable);
        }
        final String substring = s2.substring(0, s2.lastIndexOf("&"));
        if (n <= n2) {
            return s;
        }
        final int n4 = ((n3 % 10 == 0) ? (n3 - 1) : n3) / 10 * 10 + 1;
        final int n5 = n / n2 + ((n % n2 != 0) ? 1 : 0);
        final int n6 = (n5 - n4 >= 10) ? (n4 + 9) : n5;
        final String string = s + "<table width=100% border=0 cellpadding=2 cellspacing=2 class=tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr><tr><td align=center class=redc>";
        String s3;
        if (n3 > 10) {
            s3 = string + "<a href='" + substring + "&page_no=" + (n4 - 1) + "'><img align=absmiddle src=/img/bu_backarw.gif border=0 alt='10Trước'></a>";
        }
        else if (n3 > 1) {
            s3 = string + "<img align=absmiddle src=/img/bu_backarwn.gif border=0 alt='10Trước'>";
        }
        else {
            s3 = string + "<img align=absmiddle src=/img/bu_backarwn.gif border=0 alt='10Trước'>";
        }
        for (int i = n4; i <= n6; ++i) {
            if (n3 == i) {
                s3 = s3 + "[" + i + "]";
            }
            else {
                s3 = s3 + "<a href='" + substring + "&page_no=" + i + "'>[" + i + "]</a>";
            }
        }
        String s4;
        if (n6 != n5) {
            s4 = s3 + "<a href='" + substring + "&page_no=" + (n6 + 1) + "'><img align=absmiddle src=/img/bu_nextarw.gif border=0 alt='10 Sau'></a><a href='" + substring + "&page_no=" + (n6 + 1) + "'></a>";
        }
        else if (n3 < n6) {
            s4 = s3 + "<img align=absmiddle src=/img/bu_nextarwn.gif border=0 alt='10 Sau'>";
        }
        else {
            s4 = s3 + "<img align=absmiddle src=/img/bu_nextarwn.gif border=0 alt='10 Sau'>";
        }
        return s4 + "</td></tr><tr><td align=center background=/img/dotlines.gif height=1></td></tr></table>";
    }
    
    public static String getUrl(final HttpServletRequest httpServletRequest, final String s) {
        String s2 = s + "?";
        if (httpServletRequest.getMethod().equalsIgnoreCase("GET")) {
            String s3 = s2 + httpServletRequest.getQueryString();
            final String retNull = EP_COB_GTQ805.retNull(httpServletRequest.getParameter("page_no"));
            if (retNull != null) {
                s3 = EP_COB_GTQ805.replace(s3, "&page_no=" + retNull, "");
            }
            s2 = s3 + "&";
        }
        else {
            final Enumeration parameterNames = httpServletRequest.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                final String s4 = (String)parameterNames.nextElement();
                if (s4.equals("page_no")) {
                    continue;
                }
                final String[] parameterValues = httpServletRequest.getParameterValues(s4);
                for (int i = 0; i < parameterValues.length; ++i) {
                    s2 = s2 + s4 + "=" + parameterValues[i] + "&";
                }
            }
        }
        return s2;
    }
    
    public static String getHashString(final Hashtable hashtable) {
        if (hashtable == null) {
            return "";
        }
        if (hashtable.isEmpty()) {
            return "";
        }
        final Enumeration<String> keys = hashtable.keys();
        String string = "";
        while (keys.hasMoreElements()) {
            final String s = (String)keys.nextElement();
            string = string + s + "=" + hashtable.get((Object)s) + "&";
        }
        return EP_COB_GTQ805.retSpace(string);
    }
    
    public static String getUrl(final HttpServletRequest httpServletRequest, final String s, final String s2) {
        return getUrl(httpServletRequest, s) + "&page_no=" + s2;
    }
}
