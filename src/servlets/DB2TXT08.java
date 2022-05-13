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

public class DB2TXT08 extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
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
        final String goodName = req.getParameter("goodName");
        String goodCode = req.getParameter("goodCode");
        String local = req.getParameter("local");
        final String fileName = "등록업체(기업구분, 지역별) 통계조회";
        String total = ComStr.checkNull(req.getParameter("total"), "");
        String startTime = String.valueOf(start) + "00:00:00";
        String endTime = String.valueOf(end) + "23:59:59";
        final int attach = 0;
        goodCode = "20121508";
        start = "2001-01-01";
        end = "2002-10-01";
        startTime = "2001-01-01 00:00:00";
        endTime = "2002-10-01 00:00:00";
        startTime = "";
        local = "11";
        total = "1";
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "\t SELECT rownum cnt , U1.사업자등록번호, U1.상호명\t\t\n \t      , U2.대표자명, U2.주민등록번호, U2.대표자주소\t\t\n \t      , U1.제재연월일, U1.만료연월일\t\t\t\t\t\n \t      , U3.코드명 제재사유\t\t\t\t\t\t\t\n \t      , U4.코드명 제재기관\t\t\t\t\t\t\t\n \t   FROM syn_공동코드 U4\t\t\t\t\t\t\t\t\n \t      , syn_공동코드 U3\t\t\t\t\t\t\t\t\n \t      , 사용_부정당대표자 U2\t\t\t\t\t\t\t\n \t      , 사용_부정당업자 U1\t\t\t\t\t\t\t\n \t WHERE U1.사업자등록번호  = U2.사업자등록번호\t\t\t\n \t    AND U1.제재횟수       = U2.제재횟수\t\t\t\t\n \t    AND U3.코드구분       = 'GUG'\t\t\t\t\t\t\n \t    AND U1.제재근거2      = U3.코드\t\t\t\t\t\n \t    AND ( (U4.코드구분    = 'GUI') OR\t\t\t\t\t\n \t          (U4.코드구분    = 'GUD') OR\t\t\t\t\t\n \t          (U4.코드구분    = 'GUE'))\t\t\t\t\t\n \t    AND U1.제재기관코드= U4.코드\t\t\t\t\t\t\n " + (startTime.equals("") ? "" : ("   AND U1.입력일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')")) + "\n " + (total.equals("0") ? "" : ("AND U1.제재기관코드= '" + local + "' ")) + " \n ";
            psmt = con.prepareStatement(sql);
            rs1 = psmt.executeQuery();
            psmt.clearParameters();
            String chk = "";
            if (total == "0") {
                chk = "V^";
            }
            else if (total == "1") {
                chk = "^V";
            }
            fileWrite = String.valueOf(chk) + "^";
            fileWrite = String.valueOf(fileWrite) + start + "^";
            fileWrite = String.valueOf(fileWrite) + end + "^\r\n";
            if (rs1 != null) {
                while (rs1.next()) {
                    fileWrite = String.valueOf(fileWrite) + ComStr.checkNull(rs1.getString(1)) + "^" + ComStr.checkNull(rs1.getString(2)) + "^" + ComStr.checkNull(rs1.getString(3)) + "^" + ComStr.checkNull(rs1.getString(4)) + "^" + ComStr.checkNull(rs1.getString(5)) + "^" + ComStr.checkNull(rs1.getString(6)) + "^" + ComStr.checkNull(rs1.getString(7)) + "^" + ComStr.checkNull(rs1.getString(8)) + "^" + ComStr.checkNull(rs1.getString(9)) + "^" + ComStr.checkNull(rs1.getString(10)) + "^ \r\n";
                }
            }
            fileWrite = String.valueOf(fileWrite) + "//EOR//\r\n";
            rs1.close();
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
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs1 != null) {
            try {
                rs1.close();
            }
            catch (Exception ex4) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        out.println(fileWrite);
    }
}
