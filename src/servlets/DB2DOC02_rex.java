// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DB2DOC02_rex extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/xml;charset=euc-kr");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        String fileWrite = "";
        boolean flg = false;
        final PrintWriter out = res.getWriter();
        final String fileName = ComStr.checkNull(req.getParameter("fileName"), "");
        String saupNo = ComStr.checkNull(req.getParameter("saupNo"), "");
        String subDay = ComStr.checkNull(req.getParameter("subDay"), "");
        final String COUNT = "";
        String foreigncheck = "";
        while (true) {
            if (subDay != "") {
                subDay = ComStr.replace(subDay, "-", "");
                final String subDay_1 = subDay.substring(0, 4);
                final String subDay_2 = subDay.substring(4, 6);
                final String subDay_3 = subDay.substring(6, 8);
                subDay = String.valueOf(subDay_1) + "-" + subDay_2 + "-" + subDay_3;
                try {
                    trx = new Trx(this, "usemn");
                    con = trx.getConnection();
                    sb.append("\n\tSELECT U2.신청일자,  U1.갱신일자, \tU1.업무구분, ");
                    sb.append("\n          U1.상호명,  \t U1.영문상호명, U1.사업자등록번호,");
                    sb.append("\n\t\t   U1.개업일자,  U1.주소 || ' ' || U1.나머지주소 주소, ");
                    sb.append("\n\t\t   U1.전화번호,  U1.FAX번호,   U1.법인설립일자, U1.법인등록번호,");
                    sb.append("\n\t\t   U1.자본금,    U1.종업원수,   U1.기업구분2 ");
                    sb.append("\n\tFROM  사용_조달업체마스터  U1, 사용_전자문서상태  U2");
                    sb.append("\n\tWHERE  U1.사업자등록번호= U2.사업자등록번호\t");
                    sb.append("\n\tAND    U2.신청_변경구분='1'  ");
                    sb.append("\n\tAND    U1.사업자등록번호=? ");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    fileWrite = "<?xml version='1.0' encoding='EUC-KR'?>";
                    fileWrite = String.valueOf(fileWrite) + "<main>";
                    fileWrite = String.valueOf(fileWrite) + "<row>";
                    while (rs.next()) {
                        flg = true;
                        saupNo = rs.getString(6);
                        final String saupNo_1 = saupNo.substring(0, 3);
                        final String saupNo_2 = saupNo.substring(3, 5);
                        final String saupNo_3 = saupNo.substring(5, saupNo.length());
                        final String bubinNo = ComStr.checkNull(rs.getString(12), "");
                        String bubinNo_1 = "";
                        String bubinNo_2 = "";
                        if (bubinNo != null && bubinNo != "") {
                            bubinNo_1 = bubinNo.substring(0, 6);
                            bubinNo_2 = bubinNo.substring(6, bubinNo.length());
                        }
                        else {
                            bubinNo_1 = "";
                            bubinNo_2 = "";
                        }
                        final String Kiupgubun = rs.getString(3);
                        final String Kiupgubun_1 = Kiupgubun.substring(0, 1);
                        final String Kiupgubun_2 = Kiupgubun.substring(1, 2);
                        final String Kiupgubun_3 = Kiupgubun.substring(2, 3);
                        final String Kiupgubun_4 = foreigncheck = Kiupgubun.substring(4, 5);
                        String gubun_1 = "";
                        String gubun_2 = "";
                        if (Kiupgubun != null && Kiupgubun_1.equals("1") && Kiupgubun_2.equals("1") && Kiupgubun_3.equals("1")) {
                            gubun_1 = "∨^∨^∨";
                        }
                        else if (Kiupgubun != null && Kiupgubun_1.equals("1") && Kiupgubun_2.equals("1")) {
                            gubun_1 = "∨^∨^";
                        }
                        else if (Kiupgubun != null && Kiupgubun_2.equals("1") && Kiupgubun_3.equals("1")) {
                            gubun_1 = "^∨^∨";
                        }
                        else if (Kiupgubun != null && Kiupgubun_1.equals("1") && Kiupgubun_3.equals("1")) {
                            gubun_1 = "∨^^∨";
                        }
                        else if (Kiupgubun != null && Kiupgubun_1.equals("1")) {
                            gubun_1 = "∨^^";
                        }
                        else if (Kiupgubun != null && Kiupgubun_2.equals("1")) {
                            gubun_1 = "^∨^";
                        }
                        else if (Kiupgubun != null && Kiupgubun_3.equals("1")) {
                            gubun_1 = "^^∨";
                        }
                        else {
                            gubun_1 = "^^";
                        }
                        if (Kiupgubun != null && Kiupgubun_4.equals("1")) {
                            gubun_2 = "∨^";
                        }
                        else {
                            gubun_2 = "^";
                        }
                        final String Kiupgubun2 = ComStr.checkNull(rs.getString(15), "");
                        String gubun_3 = "";
                        if (Kiupgubun2.equals("1")) {
                            gubun_3 = "V^^^^^";
                        }
                        else if (Kiupgubun2.equals("2")) {
                            gubun_3 = "^V^^^^";
                        }
                        else if (Kiupgubun2.equals("3")) {
                            gubun_3 = "^^V^^^";
                        }
                        else if (Kiupgubun2.equals("4")) {
                            gubun_3 = "^^^V^^";
                        }
                        else if (Kiupgubun2.equals("5")) {
                            gubun_3 = "^^^^V^";
                        }
                        else if (Kiupgubun2.equals("6")) {
                            gubun_3 = "^^^^^V";
                        }
                        else if (Kiupgubun2.equals("")) {
                            gubun_3 = "^^^^^";
                        }
                        String shinchungDay = ComStr.checkNull(rs.getString(1), "");
                        if (shinchungDay != "") {
                            shinchungDay = shinchungDay.substring(0, 10);
                        }
                        String renewDay = ComStr.checkNull(rs.getString(2), "");
                        if (renewDay != "") {
                            renewDay = renewDay.substring(0, 10);
                        }
                        String openDay = ComStr.checkNull(rs.getString(7), "");
                        if (openDay != "") {
                            openDay = openDay.substring(0, 10);
                        }
                        String bubinOpen = ComStr.checkNull(rs.getString(11), "");
                        if (bubinOpen != "") {
                            bubinOpen = bubinOpen.substring(0, 10);
                        }
                        fileWrite = String.valueOf(fileWrite) + "<인터넷접수일><![CDATA[" + ComStr.checkNull(shinchungDay, "") + "]]></인터넷접수일>" + "<증빙서류접수일><![CDATA[" + ComStr.checkNull(subDay, "") + "]]></증빙서류접수일>" + "<승인일시><![CDATA[" + ComStr.checkNull(renewDay, "") + "]]></승인일시>" + "<업무구분><![CDATA[" + gubun_1 + "]]></업무구분>" + "<외자구분><![CDATA[" + gubun_2 + "]]></외자구분>" + "<상호명><![CDATA[" + ComStr.checkNull(rs.getString(4), "") + "]]></상호명>" + "<영문상호명><![CDATA[" + ComStr.checkNull(rs.getString(5), "") + "]]></영문상호명>" + "<사업자등록번호><![CDATA[" + saupNo_1 + "-" + saupNo_2 + "-" + saupNo_3 + "]]></사업자등록번호>" + "<개업일자><![CDATA[" + ComStr.checkNull(openDay, "") + "]]></개업일자>" + "<주소><![CDATA[" + ComStr.checkNull(rs.getString(8), "") + "]]></주소>" + "<전화번호><![CDATA[" + ComStr.checkNull(rs.getString(9), "") + "]]></전화번호>" + "<팩스번호><![CDATA[" + ComStr.checkNull(rs.getString(10), "") + "]]></팩스번호>" + "<법인설립일자><![CDATA[" + ComStr.checkNull(bubinOpen, "") + "]]></법인설립일자>" + "<법인등록번호><![CDATA[" + ComStr.checkNull(rs.getString(12), "") + "]]></법인등록번호>" + "<자본금><![CDATA[" + ComStr.checkNull(rs.getString(13), "") + "]]></자본금>" + "<종업원수><![CDATA[" + ComStr.checkNull(rs.getString(14), "") + "]]></종업원수>" + "<기업구분2><![CDATA[" + gubun_3 + "]]></기업구분2>";
                    }
                    rs.close();
                    psmt.close();
                    sb.setLength(0);
                    sql = "";
                    sb.append("\n SELECT  대표자명, SUBSTR(대표자주민번호,1,6)||'*******', 대표자메일주소,\t");
                    sb.append("\n 대표자휴대폰, decode(대표자유형,'1','각자대표','2','공동대표',대표자유형) ");
                    sb.append("\n FROM  사용_대표자\t\t");
                    sb.append("\n WHERE 사업자등록번호 = ?             ");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    fileWrite = String.valueOf(fileWrite) + "<sub1>";
                    if (rs != null) {
                        while (rs.next()) {
                            final String JuminNo = ComStr.checkNull(rs.getString(2), "");
                            String JuminNo_1 = "";
                            String JuminNo_2 = "";
                            if (JuminNo != null && JuminNo.length() == 13) {
                                JuminNo_1 = JuminNo.substring(0, 6);
                                JuminNo_2 = JuminNo.substring(6, JuminNo.length());
                            }
                            else {
                                JuminNo_1 = "";
                                JuminNo_2 = "";
                            }
                            fileWrite = String.valueOf(fileWrite) + "<row><대표자명><![CDATA[" + ComStr.checkNull(rs.getString(1), "") + "]]></대표자명>" + "<대표자주민번호><![CDATA[" + JuminNo_1 + "-" + JuminNo_2 + "]]></대표자주민번호>" + "<대표자메일주소><![CDATA[" + ComStr.checkNull(rs.getString(3), "") + "]]></대표자메일주소>" + "<대표자휴대폰><![CDATA[" + ComStr.checkNull(rs.getString(4), "") + "]]></대표자휴대폰>" + "<대표자유형><![CDATA[" + ComStr.checkNull(rs.getString(5), "") + "]]></대표자유형>" + "</row>";
                        }
                        rs.close();
                    }
                    psmt.close();
                    fileWrite = String.valueOf(fileWrite) + "</sub1>";
                    sb.setLength(0);
                    sql = "";
                    sb.append("\n SELECT  공장주소, 공장전화번호, 공장FAX번호,\t");
                    sb.append("\n         decode(공장임대여부, null, '', 'Y', '임대', 'N', '자가'),     ");
                    sb.append("\n         decode(공장임대여부, 'Y', to_char(공장임대시작일자, 'yyyy.mm.dd')||' ~ '||TO_CHAR(공장임대종료일자, 'yyyy.mm.dd'), '') ");
                    sb.append("\n FROM  사용_공장정보\t                        ");
                    sb.append("\n WHERE 사업자등록번호 = ?                    ");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    fileWrite = String.valueOf(fileWrite) + "<sub2>";
                    if (rs != null) {
                        while (rs.next()) {
                            fileWrite = String.valueOf(fileWrite) + "<row><공장주소><![CDATA[" + ComStr.checkNull(rs.getString(1), "") + "]]></공장주소>" + "<공장전화번호><![CDATA[" + ComStr.checkNull(rs.getString(2), "") + "]]></공장전화번호>" + "<공장팩스번호><![CDATA[" + ComStr.checkNull(rs.getString(3), "") + "]]></공장팩스번호>" + "<공장임대여부><![CDATA[" + ComStr.checkNull(rs.getString(4), "") + "]]></공장임대여부>" + "<공장임대기간><![CDATA[" + ComStr.checkNull(rs.getString(5), "") + "]]></공장임대기간>" + "</row>";
                        }
                        rs.close();
                    }
                    psmt.close();
                    fileWrite = String.valueOf(fileWrite) + "</sub2>";
                    sb.setLength(0);
                    sql = "";
                    sb.append("\n SELECT  distinct A.물품분류번호, B.분류명, A.제조여부,  A.형식승인번호,   ");
                    sb.append("\n     DECODE(직접생산증명서류,'1','직접생산증명서','2','공장등록증','3','건축물관리대장')                    ");
                    sb.append("\n FROM    사용_조달품목 A, SYN_VIEW_물품분류매핑 B              ");
                    sb.append("\n WHERE   A.사업자등록번호 = ?                                 ");
                    sb.append("\n AND     A.물품분류번호 = B.물품분류                           ");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    fileWrite = String.valueOf(fileWrite) + "<sub3>";
                    if (rs != null) {
                        while (rs.next()) {
                            fileWrite = String.valueOf(fileWrite) + "<row><물품분류번호><![CDATA[" + ComStr.checkNull(rs.getString(1), "") + "]]></물품분류번호>" + "<분류명><![CDATA[" + ComStr.checkNull(rs.getString(2), "") + "]]></분류명>" + "<제조여부><![CDATA[" + ComStr.checkNull(rs.getString(3), "") + "]]></제조여부>" + "<형식승인번호><![CDATA[" + ComStr.checkNull(rs.getString(4), "") + "]]></형식승인번호>" + "<직접생산증명서류><![CDATA[" + ComStr.checkNull(rs.getString(5), "") + "]]></직접생산증명서류>" + "</row>";
                        }
                        rs.close();
                    }
                    if (foreigncheck.equals("1")) {
                        fileWrite = String.valueOf(fileWrite) + "<row><물품분류번호><![CDATA[99999999]]></물품분류번호><분류명><![CDATA[외자물품]]></분류명><제조여부><![CDATA[]]></제조여부><형식승인번호><![CDATA[]]></형식승인번호></row>";
                    }
                    psmt.close();
                    fileWrite = String.valueOf(fileWrite) + "</sub3>";
                    sb.setLength(0);
                    sql = "";
                    sb.append("\n SELECT B.코드명, A.면허번호, TO_CHAR(A.면허취득일자, 'YYYY. MM. DD') 면허취득일자, TO_CHAR(A.면허만료일자, 'YYYY. MM. DD') 면허만료일자, A.시공능력평가액   ");
                    sb.append("\n FROM  사용_면허사항 A, syn_공동코드 B                                                            ");
                    sb.append("\n WHERE B.코드구분='GU9'                                                                         ");
                    sb.append("\n AND B.코드 = A.면허코드                                                                         ");
                    sb.append("\n AND A.사업자등록번호 = ?                                                                        ");
                    sb.append("\n ORDER BY B.코드명                                                                              ");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    fileWrite = String.valueOf(fileWrite) + "<sub4>";
                    if (rs != null) {
                        while (rs.next()) {
                            fileWrite = String.valueOf(fileWrite) + "<row><코드명><![CDATA[" + ComStr.checkNull(rs.getString(1), "") + "]]></코드명>" + "<면허번호><![CDATA[" + ComStr.checkNull(rs.getString(2), "") + "]]></면허번호>" + "<면허취득일자><![CDATA[" + ComStr.checkNull(rs.getString(3), "") + "]]></면허취득일자>" + "<면허만료일자><![CDATA[" + ComStr.checkNull(rs.getString(4), "") + "]]></면허만료일자>" + "<시공능력평가액><![CDATA[" + ComStr.divideComma(ComStr.checkNull(rs.getString(5)), ",", 3) + "]]></시공능력평가액>" + "</row>";
                        }
                        rs.close();
                    }
                    psmt.close();
                    fileWrite = String.valueOf(fileWrite) + "</sub4>";
                    sb.setLength(0);
                    sql = "";
                    sb.append("\n SELECT  직책명,  성명, SUBSTR(주민등록번호,1,6)||'*******'\t");
                    sb.append("\n FROM  사용_입찰대리인\t\t");
                    sb.append("\n WHERE 사업자등록번호 = ?             ");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    fileWrite = String.valueOf(fileWrite) + "<sub5>";
                    if (rs != null) {
                        while (rs.next()) {
                            final String JuminNo = ComStr.checkNull(rs.getString(3), "");
                            String JuminNo_1 = "";
                            String JuminNo_2 = "";
                            if (JuminNo != null && JuminNo.length() == 13) {
                                JuminNo_1 = JuminNo.substring(0, 6);
                                JuminNo_2 = JuminNo.substring(6, JuminNo.length());
                            }
                            else {
                                JuminNo_1 = "";
                                JuminNo_2 = "";
                            }
                            fileWrite = String.valueOf(fileWrite) + "<row><직책명><![CDATA[" + ComStr.checkNull(rs.getString(1), "") + "]]></직책명>" + "<성명><![CDATA[" + ComStr.checkNull(rs.getString(2), "") + "]]></성명>" + "<주민등록번호><![CDATA[" + JuminNo_1 + "-" + JuminNo_2 + "]]></주민등록번호>" + "</row>";
                        }
                        rs.close();
                    }
                    psmt.close();
                    fileWrite = String.valueOf(fileWrite) + "</sub5>";
                }
                catch (SQLException e) {
                    Log.errors(this.getClass().getName() + " SQLException 발생함 \n" + sql + " \n" + "사업자등록번호=" + saupNo + " \n" + e);
                }
                catch (Exception e2) {
                    Log.errors(this.getClass().getName() + " Exception 발생함 \r\n" + e2);
                    Log.errors(this.getClass().getName() + " Exception 발생함 \n" + "사업자등록번호=" + saupNo + " \n" + e2);
                }
                finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (Exception ex) {}
                    }
                    if (psmt != null) {
                        try {
                            psmt.close();
                        }
                        catch (Exception ex2) {}
                    }
                    if (con != null) {
                        try {
                            con.close();
                        }
                        catch (Exception ex3) {}
                    }
                    if (trx != null) {
                        try {
                            trx.close();
                        }
                        catch (Exception ex4) {}
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex5) {}
                }
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (Exception ex6) {}
                }
                if (con != null) {
                    try {
                        con.close();
                    }
                    catch (Exception ex7) {}
                }
                if (trx != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex8) {}
                }
                if (!flg) {
                    fileWrite = "";
                }
                fileWrite = String.valueOf(fileWrite) + "</row>";
                fileWrite = String.valueOf(fileWrite) + "</main>";
                out.println(fileWrite);
                return;
            }
            continue;
        }
    }
}
