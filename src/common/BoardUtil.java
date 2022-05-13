// 
// Decompiled by Procyon v0.5.30
// 

package common;

public class BoardUtil
{
    public String indexList(final int current_page, final int total_page, final String list_url) {
        String strList = "";
        final int pagenumber = 10;
        final int startpage = (current_page - 1) / pagenumber * pagenumber + 1;
        int endpage = (startpage - 1 + pagenumber) / pagenumber * pagenumber;
        if (total_page <= endpage) {
            endpage = total_page;
        }
        if (current_page > pagenumber) {
            final int curpage = startpage - 1;
            strList = String.valueOf(strList) + "<a href='" + list_url + "&page=" + curpage + "'>";
        }
        strList = String.valueOf(strList) + "<span class=page10>[ 이전10개 ]</span>" + "</a>";
        for (int curpage = startpage; curpage <= endpage; ++curpage) {
            if (curpage == current_page) {
                strList = String.valueOf(strList) + "<span class=pagelink>[" + current_page + "]</span>";
            }
            else {
                strList = String.valueOf(strList) + "<a href='" + list_url + "&page=" + curpage + "'><span class=pagelink>[" + curpage + "]</span></a>";
            }
        }
        if (total_page > endpage) {
            final int curpage = endpage + 1;
            strList = String.valueOf(strList) + "<a href='" + list_url + "&page=" + curpage + "'>";
        }
        strList = String.valueOf(strList) + "<span class=page10>[ 다음10개 ]</span>" + "</a>";
        return strList;
    }
    
    public String toKorean(final String str) {
        return str;
    }
    
    public String checkNull(final String str) {
        String strTmp;
        if (str == null) {
            strTmp = "";
        }
        else {
            strTmp = str;
        }
        return strTmp;
    }
    
    public String nl2br(final String comment) {
        final int length = comment.length();
        final StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; ++i) {
            String comp = comment.substring(i, i + 1);
            if ("\r".compareTo(comp) == 0) {
                comp = comment.substring(++i, i + 1);
                if ("\n".compareTo(comp) == 0) {
                    buffer.append("<BR>\r");
                }
                else {
                    buffer.append("\r");
                }
            }
            buffer.append(comp);
        }
        return buffer.toString();
    }
}
