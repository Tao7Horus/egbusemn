// 
// Decompiled by Procyon v0.5.30
// 

package dao;

public class EP_COB_GTQ990
{
    private static final String GONGI_CODE_GUBUN = "Z45";
    public static final String TUCHAL_GUBUN = "5-1";
    
    public static String getOpenHtml() {
        final StringBuffer sb = new StringBuffer(1024);
        sb.append("</td></tr>");
        sb.append("\n<tr height=10><td colspan=3></td></tr>");
        sb.append("\n<tr height=*><td colspan=3></td></tr>");
        sb.append("\n<tr><td colspan=3 height=45 width=*>");
        sb.append("\n");
        sb.append("\n<table width=100% cellpadding=0 cellspacing=0 border=0 background='/img/pop_bottom_back.gif' height=45>");
        sb.append("\n<tr>");
        sb.append("\n<td background='/img/pop_bottom.gif' style='background-position:right; background-repeat:no-repeat;'></td>");
        sb.append("\n</tr></table>");
        return sb.toString();
    }
}
