// 
// Decompiled by Procyon v0.5.30
// 

package common;

public class CommonFooter
{
    public static String getBottonHtml() {
        final StringBuffer sbuffer = new StringBuffer(1024);
        sbuffer.append("         </td>                                                                                                                                                                                                                                                                                                                                                                                                                                     \r\n");
        sbuffer.append("         <td width='28'></td>                                                                                                                                                                                                                                                                                                                                                                                                                      \r\n");
        sbuffer.append("     </tr>                                                                                                                                                                                                                                                                                                                                                                                                                                         \r\n");
        sbuffer.append("     <tr height='35'><td colspan='3'></td></tr>                                                                                                                                                                                                                                                                                                                                                                                                    \r\n");
        sbuffer.append("     <tr height='*'><td colspan='3'></td></tr>                                                                                                                                                                                                                                                                                                                                                                                                     \r\n");
        sbuffer.append("     <script language='javascript' src='http://www.g2b.go.kr/main/js/copyright.js'></script>                                                                                                                                                                                                                                                                                                                                                       \r\n");
        sbuffer.append("     </tr>                                                                                                                                                                                                                                                                                                                                                                                                                                         \r\n");
        sbuffer.append(" </table>                                                                                                                                                                                                                                                                                                                                                                                                                                          \r\n");
        return sbuffer.toString();
    }
    
    public static String getOpenHtml() {
        final StringBuffer sbuffer = new StringBuffer(1024);
        sbuffer.append("</td><td width=28></td></tr>");
        sbuffer.append("\n<tr height=10><td colspan=3></td></tr>");
        sbuffer.append("\n<tr height=*><td colspan=3></td></tr>");
        sbuffer.append("\n<tr><td colspan=3 height=45 width=*>");
        sbuffer.append("\n");
        sbuffer.append("\n<table width=100% cellpadding=0 cellspacing=0 border=0 background='/img/pop_bottom_back.gif' height=45>");
        sbuffer.append("\n<tr>");
        sbuffer.append("\n<td background='/img/pop_bottom.gif' style='background-position:right; background-repeat:no-repeat;'></td>");
        sbuffer.append("\n</tr></table>");
        sbuffer.append("\n");
        sbuffer.append("\n</td></tr></table>");
        return sbuffer.toString();
    }
}
