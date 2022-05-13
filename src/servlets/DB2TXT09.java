// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.ResultSet;
import beans.Common_ComBo;
import common.WebUtil;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DB2TXT09 extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        throw new Error("Unresolved compilation problems: \n\tThe type CommUtil is ambiguous\n\tThe type CommonMessage is ambiguous\n\tThe type CommonMessage is ambiguous\n");
    }
    
    private void headHtml(final PrintWriter out, final String jichung, final String start, final String end, final String desformat) throws Exception {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>조달업체 마스터정보</title>");
        out.println("<meta http-equiv=Content-Type content=\"text/html; charset=euc-kr\">");
        if (!desformat.equalsIgnoreCase("EXCEL")) {
            out.println("<LINK REL=STYLESHEET TYPE=text/css HREF=/css/EP.css>");
        }
        else {
            final WebUtil cssStype = new WebUtil();
            out.println(cssStype.cssStype());
        }
        out.println("</head>");
        out.println("<body bgcolor=white text=black>");
        out.println("<table\tx:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width='100%' align='center'>");
        out.println("<tr>");
        out.println("  <td bgcolor='#EDEDED' height='40' style='border:.5pt\tsolid windowtext;verticla-align:center;' colspan=22>");
        out.println("    <p align='center'><font size='5' face='굴림체'><b>조달업체 마스터정보 조회</b></font></p></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=100% align=center>");
        out.println("  <tr>");
        if (jichung.equals("all")) {
            out.println("    <td colspan=22><font size=2><b>승인지청</b> : 전체</font></td>");
        }
        else {
            out.println("    <td colspan=22><font size=2><b>승인지청</b> : " + new Common_ComBo().getCodeName("J03", jichung) + "</font></td>");
        }
        out.println("  <tr>");
        out.println("    <td colspan=22><font size=2><b>조회기간</b> : " + start + " ~ " + end + "</font></td>");
        out.println("  </tr>");
        out.println("</table>");
        out.println("<br>");
    }
    
    private void endHtml(final PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
    
    private String isNull(String str) {
        if (str == null) {
            str = "";
        }
        return str.trim();
    }
    
    private void displayResult(final PrintWriter out, final ResultSet rest, final String desformat) throws Exception {
        final Common_ComBo common_combo = new Common_ComBo();
        if (!desformat.equalsIgnoreCase("EXCEL")) {
            out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=1800 align=center>");
            out.println("  <tr bgcolor='#EDEDED'>");
            out.println("    <td class=retdac rowspan=2><b>번호</b></td>");
            out.println("    <td class=retdac><b>상호명</b></td>");
            out.println("    <td class=retdac><b>사업자등록번호</b></td>");
            out.println("    <td class=retdac><b>국적</b></td>");
            out.println("    <td class=retdac><b>영문상호명</b></td>");
            out.println("    <td class=retdac><b>개업일자</b></td>");
            out.println("    <td class=retdac><b>법인설립일자</b></td>");
            out.println("    <td class=retdac><b>법인등록번호</b></td>");
            out.println("    <td class=retdac><b>업무구분</b></td>");
            out.println("    <td class=retdac><b>제조구분</b></td>");
            out.println("    <td class=retdac><b>기업구분1</b></td>");
            out.println("    <td class=retdac><b>기업구분2</b></td>");
            out.println("  </tr>");
            out.println("  <tr>");
            out.println("    <td class=retdac><b>자본금</b></td>");
            out.println("    <td class=retdac><b>종업원수</b></td>");
            out.println("    <td class=retdac><b>우편번호</b></td>");
            out.println("    <td class=retdac><b>지역코드</b></td>");
            out.println("    <td class=retdac colspan=2><b>주소</b></td>");
            out.println("    <td class=retdac><b>전화번호</b></td>");
            out.println("    <td class=retdac><b>FAX번호</b></td>");
            out.println("    <td class=retdac><b>등록일자</b></td>");
            out.println("    <td class=retdac><b>갱신일자</b></td>");
            out.println("    <td class=retdac><b>승인지청</b></td>");
            out.println("  </tr>");
            int i = 1;
            while (rest.next()) {
                out.println("<tr>");
                out.println("    <td class=retdbc rowspan=2>" + i + "</td>");
                out.println("    <td class=retdb>" + this.isNull(rest.getString(1)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(2)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(common_combo.getCodeName("J02", rest.getString(3))) + "</td>");
                out.println("    <td class=retdb>" + this.isNull(rest.getString(4)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(5)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(6)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(7)) + "</td>");
                out.println("    <td class=retdb>" + this.isNull(rest.getString(8)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(9)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(10)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(11)) + "</td>");
                out.println("  </tr>");
                out.println("  <tr>");
                out.println("    <td class=retdbr>" + this.isNull(rest.getString(12)) + "</td>");
                out.println("    <td class=retdbr>" + this.isNull(rest.getString(13)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(14)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(15)) + "</td>");
                out.println("    <td class=retdb colspan=2>" + this.isNull(rest.getString(16)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(17)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(18)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(19)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(20)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(common_combo.getCodeName("GUJ", rest.getString(21))) + "</td>");
                out.println("</tr>");
                ++i;
            }
            if (i == 1) {
                out.println("  <tr>");
                out.println("  <td colspan=12 class=retdbc>조회된 결과가 없습니다.</td>");
                out.println("  </tr>");
            }
        }
        else {
            out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=100% align=center>");
            out.println("  <tr bgcolor='#EDEDED'>");
            out.println("    <td class=retdac><b>번호</b></td>");
            out.println("    <td class=retdac><b>상호명</b></td>");
            out.println("    <td class=retdac><b>사업자등록번호</b></td>");
            out.println("    <td class=retdac><b>국적</b></td>");
            out.println("    <td class=retdac><b>영문상호명</b></td>");
            out.println("    <td class=retdac><b>개업일자</b></td>");
            out.println("    <td class=retdac><b>법인설립일자</b></td>");
            out.println("    <td class=retdac><b>법인등록번호</b></td>");
            out.println("    <td class=retdac><b>업무구분</b></td>");
            out.println("    <td class=retdac><b>제조구분</b></td>");
            out.println("    <td class=retdac><b>기업구분1</b></td>");
            out.println("    <td class=retdac><b>기업구분2</b></td>");
            out.println("    <td class=retdac><b>자본금</b></td>");
            out.println("    <td class=retdac><b>종업원수</b></td>");
            out.println("    <td class=retdac><b>우편번호</b></td>");
            out.println("    <td class=retdac><b>지역코드</b></td>");
            out.println("    <td class=retdac><b>주소</b></td>");
            out.println("    <td class=retdac><b>전화번호</b></td>");
            out.println("    <td class=retdac><b>FAX번호</b></td>");
            out.println("    <td class=retdac><b>등록일자</b></td>");
            out.println("    <td class=retdac><b>갱신일자</b></td>");
            out.println("    <td class=retdac><b>승인지청</b></td>");
            out.println("  </tr>");
            int i = 1;
            while (rest.next()) {
                out.println("<tr>");
                out.println("    <td class=retdbc>" + i + "</td>");
                out.println("    <td class=retdb>" + this.isNull(rest.getString(1)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(2)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(common_combo.getCodeName("J02", rest.getString(3))) + "</td>");
                out.println("    <td class=retdb>" + this.isNull(rest.getString(4)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(5)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(6)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(7)) + "</td>");
                out.println("    <td class=retdb>" + this.isNull(rest.getString(8)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(9)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(10)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(11)) + "</td>");
                out.println("    <td class=retdbr>" + this.isNull(rest.getString(12)) + "</td>");
                out.println("    <td class=retdbr>" + this.isNull(rest.getString(13)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(14)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(15)) + "</td>");
                out.println("    <td class=retdb>" + this.isNull(rest.getString(16)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(17)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(18)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(19)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(rest.getString(20)) + "</td>");
                out.println("    <td class=retdbc>" + this.isNull(common_combo.getCodeName("GUJ", rest.getString(21))) + "</td>");
                out.println("</tr>");
                ++i;
            }
            if (i == 1) {
                out.println("  <tr>");
                out.println("  <td colspan=22 class=retdbc>조회된 결과가 없습니다.</td>");
                out.println("  </tr>");
            }
        }
        out.println("</table>");
        out.println("<br>");
    }
}
