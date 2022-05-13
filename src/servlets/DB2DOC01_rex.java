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

public class DB2DOC01_rex extends HttpServlet
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
        final PrintWriter out = res.getWriter();
        final String fileName = ComStr.checkNull(req.getParameter("fileName"), "");
        String saupNo = ComStr.checkNull(req.getParameter("saupNo"), "");
        String subDay = ComStr.checkNull(req.getParameter("subDay"), "");
        final String COUNT = "";
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
                    sb.append("\n\tAND    U2.신청_변경구분='2'  ");
                    sb.append("\n\tAND    U1.사업자등록번호=? ");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    while (rs.next()) {
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
                        final String Kiupgubun_4 = Kiupgubun.substring(4, 5);
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
                        fileWrite = "<?xml version='1.0' encoding='EUC-KR'?>";
                        fileWrite = String.valueOf(fileWrite) + "<main>";
                        fileWrite = String.valueOf(fileWrite) + "<row>";
                        fileWrite = String.valueOf(fileWrite) + "<인터넷접수일><![CDATA[" + ComStr.checkNull(shinchungDay, "") + "]]></인터넷접수일>" + "<증빙서류접수일><![CDATA[" + ComStr.checkNull(subDay, "") + "]]></증빙서류접수일>" + "<승인일시><![CDATA[" + ComStr.checkNull(renewDay, "") + "]]></승인일시>" + "<업무구분><![CDATA[" + gubun_1 + "]]></업무구분>" + "<외자구분><![CDATA[" + gubun_2 + "]]></외자구분>" + "<상호명><![CDATA[" + ComStr.checkNull(rs.getString(4), "") + "]]></상호명>" + "<영문상호명><![CDATA[" + ComStr.checkNull(rs.getString(5), "") + "]]></영문상호명>" + "<사업자등록번호><![CDATA[" + saupNo_1 + "-" + saupNo_2 + "-" + saupNo_3 + "]]></사업자등록번호>" + "<개업일자><![CDATA[" + ComStr.checkNull(openDay, "") + "]]></개업일자>" + "<주소><![CDATA[" + ComStr.checkNull(rs.getString(8), "") + "]]></주소>" + "<전화번호><![CDATA[" + ComStr.checkNull(rs.getString(9), "") + "]]></전화번호>" + "<팩스번호><![CDATA[" + ComStr.checkNull(rs.getString(10), "") + "]]></팩스번호>" + "<법인설립일자><![CDATA[" + ComStr.checkNull(bubinOpen, "") + "]]></법인설립일자>" + "<법인등록번호><![CDATA[" + ComStr.checkNull(rs.getString(12), "") + "]]></법인등록번호>" + "<자본금><![CDATA[" + ComStr.checkNull(rs.getString(13), "") + "]]></자본금>" + "<종업원수><![CDATA[" + ComStr.checkNull(rs.getString(14), "") + "]]></종업원수>" + "<기업구분2><![CDATA[" + gubun_3 + "]]></기업구분2>";
                    }
                    rs.close();
                    psmt.close();
                    sb.setLength(0);
                    sql = "";
                    sb.append("\n  SELECT * FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ");
                    sb.append("\n  (SELECT DECODE(A.변경구분, 'I', '추가', 'U', '변경', 'D', '삭제') 변경구분, A.속성명,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ");
                    sb.append("\n\t\t\t\t  DECODE(A.속성명, '면허코드', (SELECT 코드명 FROM syn_공동코드 WHERE 코드구분='GU9' AND 코드= A.변경전내용),                   ");
                    sb.append("\n\t\t\t\t\t\t\t\t\t\t\t  '물품분류번호', (SELECT distinct 분류명 FROM syn_view_물품분류매핑 WHERE  물품분류 = A.변경전내용),\t\t  ");
                    sb.append("\n\t\t\t\t\t\t\t\t\t\t\t  '입찰대리인',DECODE(변경전내용, NULL, '', SUBSTR(변경전내용,1,12)||'*******'),\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n\t\t\t\t\t\t\t\t\t\t\t  '대표자',DECODE(변경전내용, NULL, '', SUBSTR(변경전내용,1,12)||'*******'), A.변경전내용) 변경전내용,\t\t\t  ");
                    sb.append("\n  DECODE(A.속성명, '면허코드', (SELECT 코드명 FROM syn_공동코드 WHERE 코드구분='GU9' AND 코드= A.변경후내용),\t\t\t\t\t\t\t\t  ");
                    sb.append("\n\t\t\t\t\t\t\t\t'물품분류번호', (SELECT distinct 분류명 FROM syn_view_물품분류매핑 WHERE  물품분류 = A.변경후내용),\t\t\t\t\t\t  ");
                    sb.append("\n\t\t\t\t\t            '입찰대리인', DECODE(변경후내용, NULL, '', SUBSTR(변경후내용,1,12)||'*******'),\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n\t\t\t\t\t\t\t\t'대표자',DECODE(변경후내용, NULL, '', SUBSTR(변경후내용,1,12)||'*******'), A.변경후내용) 변경후내용\t\t\t\t\t\t\t  ");
                    sb.append("\n        FROM 사용_입찰자격사항이력 A,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n             (SELECT MAX(처리일자) UDATE FROM 사용_전자문서상태\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n\t\t\t \t\t WHERE  처리일자  IS NOT NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n\t\t\t\t\t AND   진행상태 ='1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n\t\t\t\t\t AND  신청_변경구분 ='2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n\t\t\t\t\t AND  사업자등록번호 = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n\t\t\t\t\t) B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n        WHERE A.사업자등록번호 = ? AND A.갱신일자 between B.UDATE  and B.UDATE + 5/(24*60*60))\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n\tWHERE 변경전내용 IS NOT NULL or 변경후내용 IS NOT NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    psmt.setString(2, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    fileWrite = String.valueOf(fileWrite) + "<sub1>";
                    if (rs != null) {
                        while (rs.next()) {
                            fileWrite = String.valueOf(fileWrite) + "<row><변경구분><![CDATA[" + ComStr.checkNull(rs.getString(1), "") + "]]></변경구분>" + "<속성명><![CDATA[" + ComStr.checkNull(rs.getString(2), "") + "]]></속성명>" + "<변경전내용><![CDATA[" + ComStr.checkNull(rs.getString(3), "") + "]]></변경전내용>" + "<변경후내용><![CDATA[" + ComStr.checkNull(rs.getString(4), "") + "]]></변경후내용>" + "</row>";
                        }
                        rs.close();
                    }
                    if (psmt != null) {
                        try {
                            psmt.close();
                        }
                        catch (Exception ex) {}
                    }
                    String beforegubun = "";
                    String aftergubun = "";
                    sql = "";
                    sb.setLength(0);
                    sb.append(" SELECT SUBSTR(변경전내용,5) ,SUBSTR(변경후내용,5)  FROM 사용_입찰자격사항이력 ");
                    sb.append(" WHERE 사업자등록번호 = ? ");
                    sb.append(" AND TO_CHAR(갱신일자, 'YYYY-MM-DD') = TO_CHAR(sysdate, 'YYYY-MM-DD') ");
                    sb.append(" AND 속성명 ='업무구분' ");
                    sb.append(" ORDER BY 갱신일자 asc ");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    if (rs != null) {
                        while (rs.next()) {
                            beforegubun = ComStr.checkNull(rs.getString(1), "");
                            aftergubun = ComStr.checkNull(rs.getString(2), "");
                        }
                        rs.close();
                    }
                    if (psmt != null) {
                        try {
                            psmt.close();
                        }
                        catch (Exception ex2) {}
                    }
                    if (beforegubun.equals("0") && aftergubun.equals("1")) {
                        fileWrite = String.valueOf(fileWrite) + "<row><변경구분><![CDATA[추가]]></변경구분><속성명><![CDATA[공급물품]]></속성명><변경전내용><![CDATA[]]></변경전내용><변경후내용><![CDATA[외자물품]]></변경후내용></row>";
                    }
                    else if (beforegubun.equals("1") && aftergubun.equals("0")) {
                        fileWrite = String.valueOf(fileWrite) + "<row><변경구분><![CDATA[삭제]]></변경구분><속성명><![CDATA[공급물품]]></속성명><변경전내용><![CDATA[외자물품]]></변경전내용><변경후내용><![CDATA[]]></변경후내용></row>";
                    }
                    psmt.close();
                    fileWrite = String.valueOf(fileWrite) + "</sub1>";
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
                        catch (Exception ex3) {}
                    }
                    if (psmt != null) {
                        try {
                            psmt.close();
                        }
                        catch (Exception ex4) {}
                    }
                    if (con != null) {
                        try {
                            con.close();
                        }
                        catch (Exception ex5) {}
                    }
                    if (trx != null) {
                        try {
                            trx.close();
                        }
                        catch (Exception ex6) {}
                    }
                }
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex7) {}
                }
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (Exception ex8) {}
                }
                if (con != null) {
                    try {
                        con.close();
                    }
                    catch (Exception ex9) {}
                }
                if (trx != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex10) {}
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
