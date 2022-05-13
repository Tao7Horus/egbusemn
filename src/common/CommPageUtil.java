// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.util.Enumeration;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;

public class CommPageUtil
{
    public static int totalPage(final int totalRow, final int page_size) {
        return totalRow / page_size + ((totalRow % page_size != 0) ? 1 : 0);
    }
    
    public static String getNextPageIndexes(final HttpServletRequest req, String className, final int totalRow, final int page_size, final int page_no) {
        String result = "";
        if (className == null) {
            className = req.getRequestURI();
        }
        final String url = getUrl(req, className);
        if (totalRow <= page_size) {
            return result;
        }
        final int startPage = ((page_no % 10 == 0) ? (page_no - 1) : page_no) / 10 * 10 + 1;
        final int totalPage = totalRow / page_size + ((totalRow % page_size != 0) ? 1 : 0);
        final int endPage = (totalPage - startPage >= 10) ? (startPage + 9) : totalPage;
        result = String.valueOf(result) + "<table width=100% border=0 cellpadding=2 cellspacing=2><tr class=tr><td align=center class=redc>";
        if (startPage > 1) {
            result = String.valueOf(result) + "<a href='" + url + "'><img align=absmiddle src='../../img/bts_start.gif' border=0 alt='Trang đầu'></a><a href='" + url + "&page_no=" + (endPage + 1) + "'></a>";
        }
        else {
            result = String.valueOf(result) + "<img align=absmiddle src='../../img/bts_start.gif' border=0 alt='Trang đầu'></a><a href='" + url + "&page_no=" + (endPage + 1) + "'></a>";
        }
        if (page_no > 10) {
            result = String.valueOf(result) + "&nbsp;<a href='" + url + "&page_no=" + (startPage - 1) + "'><img align=absmiddle src='../../img/bts_prev.gif' border=0 alt='Trang trước'/></a>";
        }
        else if (page_no > 1) {
            result = String.valueOf(result) + "&nbsp;<img align=absmiddle src='../../img/bts_prev.gif' border=0 alt='Trang trước'/>&nbsp;";
        }
        else {
            result = String.valueOf(result) + "&nbsp;<img align=absmiddle src='../../img/bts_prev.gif' border=0 alt='Trang trước'/>&nbsp;";
        }
        for (int i = startPage; i <= endPage; ++i) {
            if (page_no == i) {
                result = String.valueOf(result) + "[" + i + "]";
            }
            else {
                result = String.valueOf(result) + "<a href='" + url + "&page_no=" + i + "'>[" + i + "]</a>";
            }
        }
        if (endPage != totalPage) {
            result = String.valueOf(result) + "&nbsp;<a href='" + url + "&page_no=" + (endPage + 1) + "'><img align=absmiddle src='../../img/bts_next.gif' border=0 alt='Trang tiếp'></a>";
        }
        else if (page_no < endPage) {
            result = String.valueOf(result) + "&nbsp;<img align=absmiddle src='../../img/bts_next.gif' border=0 alt='Trang tiếp'/>";
        }
        else {
            result = String.valueOf(result) + "&nbsp;<img align=absmiddle src='../../img/bts_next.gif' border=0 alt='Trang tiếp'/>";
        }
        if (totalPage != page_no) {
            result = String.valueOf(result) + "&nbsp;<a href='" + url + "&page_no=" + totalPage + "'><img align=absmiddle src='../../img/bts_end.gif' border=0 alt='Trang cuối'></a>";
        }
        else {
            result = String.valueOf(result) + "&nbsp;<img align=absmiddle src='../../img/bts_end.gif' border=0 alt='Trang cuối'>";
        }
        result = String.valueOf(result) + "</td></tr></table>";
        return result;
    }
    
    public static String getUrl(final HttpServletRequest req, final String className) {
        String result = String.valueOf(className) + "?";
        final Enumeration names = req.getParameterNames();
        String temp = null;
        while (names.hasMoreElements()) {
            temp = names.nextElement();
            if (temp.equals("page_no")) {
                continue;
            }
            result = String.valueOf(result) + temp + "=" + URLEncoder.encode(req.getParameter(temp)) + "&";
        }
        return result;
    }
    
    public static String getUrl(final HttpServletRequest req, final String className, final String page_no) {
        return String.valueOf(getUrl(req, className)) + "&page_no=" + page_no;
    }
}
