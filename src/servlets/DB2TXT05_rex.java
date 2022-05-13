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
import java.util.Calendar;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DB2TXT05_rex extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/xml;charset=euc-kr");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final PreparedStatement psmt2 = null;
        ResultSet rs1 = null;
        String sql = null;
        final String sql2 = null;
        String fileWrite = null;
        final PrintWriter out = res.getWriter();
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        final String goodsName = req.getParameter("goodsName");
        final String goodsCode = req.getParameter("goodsCode");
        final String local = req.getParameter("local");
        final String manu = req.getParameter("manu");
        final String fileName = "물품등록업체(대표물품, 지역별) 통계조회";
        final String total = ComStr.checkNull(req.getParameter("total"), "");
        final Calendar cal = Calendar.getInstance();
        final String yearNow = Integer.toString(cal.get(1));
        final String monthNow = Integer.toString(cal.get(2) + 1);
        final String dateNow = Integer.toString(cal.get(5));
        final int attach = 0;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            if (start.equals("")) {
                start = "1900-01-01";
            }
            if (end.equals("")) {
                end = String.valueOf(yearNow) + "-" + monthNow + "-" + dateNow;
            }
            final String startTime = String.valueOf(start) + "00:00:00";
            final String endTime = String.valueOf(end) + "23:59:59";
            sql = " SELECT count(*)                                                                   \n   FROM 사용_조달업체마스터 a, 사용_대표자 b, 사용_조달품목 c                              \n   WHERE a.사업자등록번호 = b.사업자등록번호 and b.사업자등록번호 = c.사업자등록번호         \n   and a.업무구분 like '1%'                                                           \n   and a.테스트여부 is null                                                           \n   and b.대표대표자여부 = 'Y'                                                          \n   and c.물품분류번호 = ?                                                              \n   and c.제조여부 = '" + manu + "'                                                              \n  " + " and a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss') AND  \n  " + "\t\t\t   \t\t   TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')       \n  " + (total.equals("0") ? "" : "AND substr(a.지역코드, 1, 2) = ? ");
            psmt = con.prepareStatement(sql);
            psmt.setString(1, goodsCode);
            if (!total.equals("0")) {
                psmt.setString(2, local);
            }
            rs1 = psmt.executeQuery();
            int cnt = 0;
            if (rs1.next()) {
                cnt = rs1.getInt(1);
            }
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex) {}
            }
            if (rs1 != null) {
                try {
                    rs1.close();
                }
                catch (Exception ex2) {}
            }
            sql = "\tSELECT DECODE(substr(a.지역코드, 1, 2), 11, '서울', 26, '부산', 27, '대구', 28, '인천', 29, '광주', 30, '대전', 31, '울산',\t\n \t\t   41, '경기', 42, '강원', 43, '충북', 44, '충남', 45, '전북', 46, '전남', 47, '경북', 48, '경남', 49, '제주') 지역, \t\t\n \t\t   a.상호명, a.사업자등록번호, b.대표자명, b.대표자주민번호, a.주소||' '||a.나머지주소 주소, a.전화번호, a.FAX번호,\t\t\t\t\t\t\t\t\t\n \t\tDECODE(a.기업구분1, 1,'대기업', 2,'중소기업') 기업구분1,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\tDECODE(a.기업구분2, 1,'창업벤처',2,'원호기업', 3,'조합', 4,'공익단체', 5,'새마을공장', 6, '문화상품') 기업구분2,\t\t\t\t\n \t\tDECODE(c.제조여부,'Y','제조','N','공급') 제조여부\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \tFROM 사용_조달업체마스터 a, 사용_대표자 b, 사용_조달품목 c\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \tWHERE a.사업자등록번호 = b.사업자등록번호 and b.사업자등록번호 = c.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\n      and a.업무구분 like '1%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n      and a.테스트여부 is null                                                           \n   \t\t  and c.제조여부 = '" + manu + "'                           \n " + "\t\t  and b.대표대표자여부 = 'Y'\t\t\t\t\n " + "\t\t  and c.물품분류번호 = '" + goodsCode + "'\t\t\n " + "\t\t  and a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\t\t\t\n " + (total.equals("0") ? "order by 지역코드" : ("AND substr(a.지역코드, 1, 2) = '" + local + "' "));
            psmt = con.prepareStatement(sql);
            rs1 = psmt.executeQuery();
            psmt.clearParameters();
            String chk = "";
            if (total.equals("0")) {
                chk = "V^";
            }
            else if (total.equals("1")) {
                chk = "^V";
            }
            fileWrite = "<?xml version='1.0' encoding='EUC-KR'?>";
            fileWrite = String.valueOf(fileWrite) + "<main>";
            fileWrite = String.valueOf(fileWrite) + "<row>";
            fileWrite = String.valueOf(fileWrite) + "<검사><![CDATA[" + chk + "]]></검사>";
            fileWrite = String.valueOf(fileWrite) + "<시작일자><![CDATA[" + start.substring(0, 10) + "]]></시작일자>";
            fileWrite = String.valueOf(fileWrite) + "<종료일자><![CDATA[" + end.substring(0, 10) + "]]></종료일자>";
            if (manu.equals("Y")) {
                fileWrite = String.valueOf(fileWrite) + "<물품명><![CDATA[" + goodsName + " - (제조부분)" + "]]></물품명>";
            }
            else {
                fileWrite = String.valueOf(fileWrite) + "<물품명><![CDATA[" + goodsName + " - (공급부분)" + "]]></물품명>";
            }
            if (rs1 != null) {
                fileWrite = String.valueOf(fileWrite) + "<sub1>";
                while (rs1.next()) {
                    fileWrite = String.valueOf(fileWrite) + "<row><지역><![CDATA[" + ComStr.checkNull(rs1.getString(1)) + "]]></지역>" + "<상호><![CDATA[" + ComStr.checkNull(rs1.getString(2)) + "]]></상호>" + "<사업자등록번호><![CDATA[" + ComStr.checkNull(rs1.getString(3)) + "]]></사업자등록번호>" + "<대표자><![CDATA[" + ComStr.checkNull(rs1.getString(4)) + "]]></대표자>" + "<주민번호><![CDATA[" + ComStr.checkNull(rs1.getString(5)) + "]]></주민번호>" + "<주소><![CDATA[" + ComStr.checkNull(rs1.getString(6)) + "]]></주소>" + "<전화번호><![CDATA[" + ComStr.checkNull(rs1.getString(7)) + "]]></전화번호>" + "<팩스번호><![CDATA[" + ComStr.checkNull(rs1.getString(8)) + "]]></팩스번호>" + "<기업구분1><![CDATA[" + ComStr.checkNull(rs1.getString(9)) + "]]></기업구분1>" + "<기업구분2><![CDATA[" + ComStr.checkNull(rs1.getString(10)) + "]]></기업구분2>" + "<제조여부><![CDATA[" + ComStr.checkNull(rs1.getString(11)) + "]]></제조여부>" + "</row>";
                }
                if (!rs1.next()) {
                    fileWrite = String.valueOf(fileWrite) + "<row>";
                    fileWrite = String.valueOf(fileWrite) + "<지역></지역>";
                    fileWrite = String.valueOf(fileWrite) + "<상호></상호>";
                    fileWrite = String.valueOf(fileWrite) + "<사업자등록번호></사업자등록번호>";
                    fileWrite = String.valueOf(fileWrite) + "<대표자></대표자>";
                    fileWrite = String.valueOf(fileWrite) + "<주민번호></주민번호>";
                    fileWrite = String.valueOf(fileWrite) + "<주소></주소>";
                    fileWrite = String.valueOf(fileWrite) + "<전화번호></전화번호>";
                    fileWrite = String.valueOf(fileWrite) + "<팩스번호></팩스번호>";
                    fileWrite = String.valueOf(fileWrite) + "<기업구분1></기업구분1>";
                    fileWrite = String.valueOf(fileWrite) + "<기업구분2></기업구분2>";
                    fileWrite = String.valueOf(fileWrite) + "<제조여부></제조여부>";
                    fileWrite = String.valueOf(fileWrite) + "</row>";
                }
                fileWrite = String.valueOf(fileWrite) + "</sub1>";
            }
        }
        catch (SQLException e) {
            Log.errors(this.getClass().getName() + " SQLException 발생함 \n" + sql + " " + e);
        }
        catch (Exception e2) {
            Log.errors(this.getClass().getName() + " Exception 발생함 " + e2);
        }
        finally {
            if (rs1 != null) {
                try {
                    rs1.close();
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
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        if (rs1 != null) {
            try {
                rs1.close();
            }
            catch (Exception ex6) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex7) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex8) {}
        }
        fileWrite = String.valueOf(fileWrite) + "</row>";
        fileWrite = String.valueOf(fileWrite) + "</main>";
        out.println(fileWrite);
    }
}
