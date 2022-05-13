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

public class DB2TXT07 extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final ResultSet rs2 = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        String fileWrite = "";
        final PrintWriter out = res.getWriter();
        String start = ComStr.checkNull(req.getParameter("start"), "");
        String end = ComStr.checkNull(req.getParameter("end"), "");
        final String total = ComStr.checkNull(req.getParameter("total"), "");
        final String fileName = ComStr.checkNull(req.getParameter("fileName"), "");
        final String local = req.getParameter("local");
        final Calendar cal = Calendar.getInstance();
        final String yearNow = Integer.toString(cal.get(1));
        final String monthNow = Integer.toString(cal.get(2) + 1);
        final String dateNow = Integer.toString(cal.get(5));
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            if (start.equals("")) {
                start = "1900-01-01";
            }
            if (end.equals("")) {
                end = String.valueOf(yearNow) + "-" + monthNow + "-" + dateNow;
            }
            sql = " select decode(substr(a.지역코드, 1, 2), 11, '서울', 26, '부산', 27, \n   \t'대구', 28, '인천', 29, '광주', 30, '대전', 31, '울산',41, '경기', \n  \t42, '강원', 43, '충북', 44, '충남', 45, '전북', 46,'전남', 47, '경북', \n \t48, '경남', 49, '제주') 지역,\t\n      a.상호명, a.사업자등록번호, b.대표자명, b.대표자주민번호, a.주소||' '||a.나머지주소 주소, a.전화번호, a.FAX번호, decode(a.기업구분1, 1, '대기업', 2, '중소기업') \n     from 사용_조달업체마스터 a, 사용_대표자 b  \n    where a.사업자등록번호 = b.사업자등록번호 \n   and a.업무구분 like '____1'\t\t \n   and a.테스트여부 is null\t\t\t\t \n   and b.대표대표자여부 = 'Y'\t\t\t\t \n   and a.등록일자 between  to_date(?, 'yyyy-mm-dd') and to_date(?,'yyyy-mm-dd')\t \n " + (total.equals("0") ? "order by 지역코드" : "AND substr(a.지역코드, 1, 2) = ? ");
            psmt = con.prepareStatement(sql);
            if (!total.equals("0")) {
                psmt.setString(1, start);
                psmt.setString(2, end);
                psmt.setString(3, local);
            }
            else {
                psmt.setString(1, start);
                psmt.setString(2, end);
            }
            rs = psmt.executeQuery();
            psmt.clearParameters();
            String chk = "";
            if (total.equals("0")) {
                chk = "V^";
            }
            else if (total.equals("1")) {
                chk = "^V";
            }
            fileWrite = String.valueOf(chk) + "^";
            fileWrite = String.valueOf(fileWrite) + start + "^";
            fileWrite = String.valueOf(fileWrite) + end + "^ \r\n";
            if (rs != null) {
                while (rs.next()) {
                    fileWrite = String.valueOf(fileWrite) + ComStr.checkNull(rs.getString(1)) + "^" + ComStr.checkNull(rs.getString(2)) + "^" + ComStr.checkNull(rs.getString(3)) + "^" + ComStr.checkNull(rs.getString(4)) + "^" + ComStr.checkNull(rs.getString(5)) + "^" + ComStr.checkNull(rs.getString(6)) + "^" + ComStr.checkNull(rs.getString(7)) + "^" + ComStr.checkNull(rs.getString(8)) + "^" + ComStr.checkNull(rs.getString(9)) + "^ \r\n";
                }
            }
            fileWrite = String.valueOf(fileWrite) + "//EOR//\r\n";
            rs.close();
        }
        catch (SQLException e) {
            Log.errors(this.getClass().getName() + " SQLException 발생함 \n" + sql + " " + e);
        }
        catch (Exception e2) {
            Log.errors(this.getClass().getName() + " Exception 발생함 " + e2);
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
        out.println(fileWrite);
    }
}
