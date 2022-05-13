// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.io.PrintWriter;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class EP_COV_GTQ952 extends HttpServlet
{
    static final int PAGE_SIZE = 10;
    
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        final PrintWriter writer = httpServletResponse.getWriter();
        final String retSpace = EP_COB_GTQ805.retSpace(httpServletRequest.getParameter("pubInstitutionCode"));
        final String replace = EP_COB_GTQ805.replace(EP_COB_GTQ805.retSpace(httpServletRequest.getParameter("pubInstitutionName")), '\'', "''");
        boolean b = false;
        if (retSpace.length() > 1 || replace.length() > 1) {
            b = true;
        }
        final String retNull = EP_COB_GTQ805.retNull(httpServletRequest.getParameter("a"));
        final String retSpace2 = EP_COB_GTQ805.retSpace(httpServletRequest.getParameter("b"));
        final String retNull2 = EP_COB_GTQ805.retNull(httpServletRequest.getParameter("formName"));
        if (retNull == null || retNull2 == null) {
            writer.println("Sử dụng chương trình không chính xác. Hãy xem lại tài liệu 'Hướng dẫn người sử dụng', và làm theo.");
            return;
        }
        String retNull3 = EP_COB_GTQ805.retNull(httpServletRequest.getParameter("page_no"));
        if (retNull3 == null) {
            retNull3 = "1";
        }
        String retNull4 = EP_COB_GTQ805.retNull(httpServletRequest.getParameter("isUse"));
        if (retNull4 == null) {
            retNull4 = "N";
        }
        int int1 = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        Trx trx = null;
        final String s = " select count(*) from SYN_PUB_CODE where CD_CLS='GZ8' and length(CD) = 4 and lower(CD) like '%'||?||'%' and lower(CD_NM) like '%'||?||'%'";
        final String s2 = " select t, CD, CD_NM  from( select CD, CD_NM, rownum t from ( select CD, CD_NM from SYN_PUB_CODE where CD_CLS='GZ8'  and length(CD) = 4 and lower(CD) like '%'||?||'%' and lower(CD_NM) like '%'||?||'%' order by CD_NM desc) ) where t > ? and t < ? ";
        try {
            this.initHtml(writer, retSpace, replace, retNull, retSpace2, retNull2, retNull4);
            if (b) {
                trx = new Trx(this);
                connection = trx.getConnection();
                preparedStatement = connection.prepareStatement(s);
                preparedStatement.setString(1, retSpace.toLowerCase().trim());
                preparedStatement.setString(2, replace.toLowerCase().trim());
                set = preparedStatement.executeQuery();
                if (set.next()) {
                    int1 = set.getInt(1);
                }
                if (set != null) {
                    set.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (int1 > 0) {
                    preparedStatement = connection.prepareStatement(s2);
                    preparedStatement.setString(1, retSpace.toLowerCase().trim());
                    preparedStatement.setString(2, replace.toLowerCase().trim());
                    preparedStatement.setString(3, Integer.toString(10 * (Integer.parseInt(retNull3) - 1)));
                    preparedStatement.setString(4, Integer.toString(10 * Integer.parseInt(retNull3) + 1));
                    set = preparedStatement.executeQuery();
                    this.dataHtml(set, writer, int1, retNull4);
                    writer.println(EP_COB_GTQ809.getNextPageIndexes(httpServletRequest, (String)null, int1, 10, Integer.parseInt(retNull3)));
                }
                else {
                    this.noDataHtml(writer);
                }
            }
            this.endHtml(writer);
        }
        catch (SQLException ex) {
            Log.debug("Public Institution Search[EP_COV_GTQ951.java] INSTITU_CD[" + retSpace + "], INSTITU_FULL_NM[" + replace + "] : " + ex.toString());
            EP_COV_GTQ802.printMessage("025", "", "<br>" + ex.getMessage(), null, httpServletResponse);
        }
        catch (Exception ex2) {
            Log.debug("Public Institution Search[EP_COV_GTQ951.java] INSTITU_CD[" + retSpace + "], INSTITU_FULL_NM[" + replace + "] : " + ex2.toString());
            EP_COV_GTQ802.printMessage("025", "", "<br>" + ex2.getMessage(), null, httpServletResponse);
        }
        finally {
            try {
                if (set != null) {
                    set.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex5) {}
        }
    }
    
    private void initHtml(final PrintWriter printWriter, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        printWriter.println("<html>");
        printWriter.println("<head>");
        printWriter.println("<title>Tìm kiếm ngành nghề</title>");
        printWriter.println("<link rel='stylesheet' type='text/css' href='/css/TA.css'> ");
        printWriter.println("<link rel='stylesheet' type='text/css' href='/css/EP.css'> ");
        printWriter.println("<script language='javascript' src='/js/EP.js'></script>");
        printWriter.println("<script language='javascript'>");
        printWriter.println("\tfunction search() {");
        printWriter.println("\t\tif((document.ebid.pubInstitutionCode.value.length < 2 &&  document.ebid.pubInstitutionName.value.length <\t2)){");
        printWriter.println("\t\t\talert('Hãy nhập ít nhất 2 ký tự cho mã ngành nghề hoặc cho tên ngành nghề.');");
        printWriter.println("\t\t\treturn;");
        printWriter.println("\t\t}");
        printWriter.println("\t\tvar sp_char = \"!@#$%^&*()_-+|/;:,.?`~{}[]'\";");
        printWriter.println(" \t\tvar va1=document.ebid.pubInstitutionCode.value;");
        printWriter.println(" \t\tvar va2=document.ebid.pubInstitutionName.value;");
        printWriter.println(" \t\tfor(var i=0;i< va1.length;i++){");
        printWriter.println(" \t\t\tif(sp_char.indexOf(va1.charAt(i)) != -1){");
        printWriter.println(" \t\t\t\talert(\"Hãy bỏ ký tự đặc biệt ra khỏi mã ngành nghề đã nhập.[!@#$%^&*()_-+|/;:,.?`~{}[]]\");");
        printWriter.println("\t\t\t\treturn;");
        printWriter.println(" \t\t\t}");
        printWriter.println(" \t\t}");
        printWriter.println(" \t\tfor(var i=0;i< va2.length;i++){");
        printWriter.println(" \t\t\tif(sp_char.indexOf(va2.charAt(i)) != -1){");
        printWriter.println(" \t\t\t\talert(\"Hãy bỏ ký tự đặc biệt ra khỏi tên ngành nghề đã nhập.[!@#$%^&*()_-+|/;:,.?`~{}[]]\");");
        printWriter.println("\t\t\t\treturn;");
        printWriter.println(" \t\t\t}");
        printWriter.println(" \t\t}");
        printWriter.println("\t\tdocument.ebid.method='post';");
        printWriter.println("\t\tdocument.ebid.action\t='/servlet/dao/EP_COV_GTQ952';\t");
        printWriter.println("\t\tdocument.ebid.submit();");
        printWriter.println("\t\treturn;");
        printWriter.println("\t}");
        printWriter.println("function toOpener(arg1, arg2){");
        printWriter.println("\topener.document." + s5 + "." + s3 + ".value=arg1;");
        if (!s4.equals("")) {
            printWriter.println("\topener.document." + s5 + "." + s4 + ".value=arg2;");
        }
        printWriter.println("\tself.close();");
        printWriter.println("\treturn;");
        printWriter.println("}");
        printWriter.println("</script>");
        printWriter.println("</head>");
        printWriter.println("<body background='/img/pop_bg.gif' topmargin=0 leftmargin=0 style='background-position:left; background-repeat:repeat-y; bgcolor=white'>");
        printWriter.println(" <table border=0 cellspacing=0 cellpadding=0 height=100% width=100%>");
        printWriter.println("    <tr valign=bottom height=51>");
        printWriter.println("       <td colspan=3>");
        printWriter.println("       <table width=100% cellpadding=0 cellspacing=0 border=0 bgcolor=white> \t\t\t");
        printWriter.println("           <tr height=51 valign=bottom>\t\t\t\t\t\t\t\t\t\t\t\t");
        printWriter.println("               <td rowspan=2 width=30></td>                                            ");
        printWriter.println("               <td width=5><img src='/img/pop_tit_dot.gif'></td>                       ");
        printWriter.println("               <td width=* style='padding-top:2.0pt' class='HEADLINE'  background='/img/pop_tit2.gif'   style='background-position:right; background-repeat:no-repeat;'>&nbsp;Tìm kiếm ngành nghề</td>    ");
        printWriter.println("           </tr> \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        printWriter.println("           </table> \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        printWriter.println("\t\t</td></tr>");
        printWriter.println("<tr height=1><td colspan=3 background='/img/pop_tit_bg.gif'></td></tr>");
        printWriter.println("<tr height=25><td colspan=3></td>");
        printWriter.println("</tr><tr valign=top>");
        printWriter.println("<td width=30></td><td width=*>");
        printWriter.println("<center>");
        printWriter.println("<form name='ebid'>");
        printWriter.println("<table\tborder=0 cellpadding=2 cellspacing=1 width=100%>");
        printWriter.println("<tr height=4><td colspan=4 class=line></td></tr>");
        printWriter.println("<tr>");
        printWriter.println("<td class='tda1'>Tên ngành nghề</td>");
        printWriter.println("<td class='tdb1' colspan='2'><input type=text name=pubInstitutionName value='" + s2.trim() + "' size=50\tmaxlength=50 onkeypress=\"javascript:if(event.keyCode == 13) search();\" class='read'></td>");
        printWriter.println("</tr>");
        printWriter.println("<tr>");
        printWriter.println("\t<td\tclass='tda1'>Mã ngành nghề</td>");
        printWriter.println("\t<td\tclass='tdb1' colspan='2'><input type=text name=pubInstitutionCode size=50 value='" + s.trim() + "' maxlength=13 onkeypress=\"javascript:if(event.keyCode == 13) search();\" class='read'></td>");
        printWriter.println("</tr>");
        printWriter.println("<tr height=2><td colspan=4 class=line></td></tr>");
        printWriter.println("<tr align = 'center'>");
        printWriter.println("\t<td\tclass='' colspan='4'><input class='commonbutton1' type='button' name='btSearch' value='Tìm kiếm' onclick=\"javascript:search();\" align='absmiddle' border='0' alt='Tìm kiếm'></td>");
        printWriter.println("</tr>");
        printWriter.println("</table>");
        printWriter.println("<input\ttype=hidden\tname=a value='" + s3 + "'>");
        printWriter.println("<input\ttype=hidden\tname=b value='" + s4 + "'>");
        printWriter.println("<input\ttype=hidden\tname=formName value='" + s5 + "'>");
        printWriter.println("<input\ttype=hidden\tname=isUse value='" + s6 + "'>");
        printWriter.println("</form>");
    }
    
    private void endHtml(final PrintWriter printWriter) {
        printWriter.println(EP_COB_GTQ990.getOpenHtml());
        printWriter.println("</body>");
        printWriter.println("</html>");
    }
    
    private void noDataHtml(final PrintWriter printWriter) {
        printWriter.println("<table\tclass=tr border=0 cellpadding=2\tcellspacing=1 align=center width=100%>");
        printWriter.println("<tr>");
        printWriter.println("\t<td\talign=right\tcolspan=10 class='page'><img src='/img/page.gif' align='absmiddle'>[Kết quả tìm kiếm : 0]</td>");
        printWriter.println("</tr>");
        printWriter.println("<tr height=4><td colspan=4 class=line></td></tr>");
        printWriter.println("<tr>");
        printWriter.println("<td class=tdac>STT</td>");
        printWriter.println("<td class=tdac>Mã ngành nghề</td>");
        printWriter.println("<td class=tdac>Tên ngành nghề</td>");
        printWriter.println("</tr>");
        printWriter.println("<tr>");
        printWriter.println("<td class=tdbc\tcolspan=3><font\tcolor=red>Tìm kiếm không có dữ liệu.</font></td>");
        printWriter.println("</tr>");
        printWriter.println("<tr height=2><td colspan=4 class=line></td></tr>");
        printWriter.println("</table>");
    }
    
    private void dataHtml(final ResultSet set, final PrintWriter printWriter, final int n, final String s) throws SQLException {
        printWriter.println("<table\tclass=tr border=0 cellpadding=2\tcellspacing=1 align=center width=100%>");
        printWriter.println("<tr>");
        printWriter.println("\t<td\talign=right\tcolspan=10 class='page'><img src='/img/page.gif' align='absmiddle'>[Kết quả tìm kiếm: " + Integer.toString(n) + "]</td>");
        printWriter.println("</tr>");
        printWriter.println("<tr height=4><td colspan=4 class=line></td></tr>");
        printWriter.println("<tr>");
        printWriter.println("<td class=tdac>STT</td>");
        printWriter.println("<td class=tdac>Mã ngành nghề</td>");
        printWriter.println("<td class=tdac>Tên ngành nghề</td>");
        printWriter.println("</tr>");
        printWriter.println("<tr height=1><td colspan=4 class=line></td></tr>");
        while (set.next()) {
            printWriter.println("<tr>");
            printWriter.println("<td class=tdbc>" + set.getString(1) + "</td>");
            printWriter.println("<td class=tdbc><a href=\"javascript:toOpener('" + set.getString(2) + "','" + set.getString(3) + "')\">" + set.getString(2) + "</td>");
            printWriter.println("<td class=tdb>" + set.getString(3) + "</td>");
            printWriter.println("</tr>");
        }
        printWriter.println("<tr height=2><td colspan=4 class=line></td></tr>");
        printWriter.println("</table>");
    }
}
