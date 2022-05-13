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
import java.util.Vector;
import common.ComStr;
import common.Trx;
import common.Log;
import javax.servlet.ServletRequest;
import com.oreilly.servlet.ParameterParser;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_GOV_Print01 extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        PreparedStatement psmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        String sql2 = null;
        String fileWrite = "";
        final PrintWriter out = res.getWriter();
        final ParameterParser psr = new ParameterParser((ServletRequest)req);
        final String unpairNo = psr.getStringParameter("unpairNo", "");
        final String punishmentCount = psr.getStringParameter("punishmentCount", "");
        int count = 1;
        Log.debug("unpairNo=" + unpairNo + ", punishmentCount=" + punishmentCount);
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sb.append(" SELECT\tA.문서번호,                                         A.공공기관명,                                      \n");
            sb.append("     \tA.담당자명,                                         A.담당자전화번호,                                  \n");
            sb.append(" \t\tTO_CHAR(A.입력일자, 'YYYY. MM. DD') 입력일자,       A.상호명,                                          \n");
            sb.append(" \t\tA.사업자등록번호,                                   A.우편번호,                                        \n");
            sb.append(" \t\tA.주소 ||' '|| A.나머지주소 주소,                   A.법인등록번호,                                    \n");
            sb.append(" \t\tA.영업종목,                                         A.면허등록번호,                                    \n");
            sb.append(" \t\tA.제재근거1 ||' '|| A.제재근거2 제재근거,                                                              \n");
            sb.append(" \t\tTO_CHAR(A.제재연월일, 'YYYY. MM. DD') 제재연월일,                                                      \n");
            sb.append(" \t\tTO_CHAR(A.만료연월일, 'YYYY. MM. DD') 만료연월일,   A.제재기간,\t                                       \n");
            sb.append(" \t\tTO_CHAR(A.해약연월일, 'YYYY. MM. DD') 해약연월일,   A.기타                                             \n");
            sb.append(" FROM\t사용_부정당업자\tA                                 \t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append(" WHERE\tA.사업자등록번호 = '" + ComStr.replace(unpairNo, "-", "") + "'\t\t\t\t\t\t\t\t\t\t\t   \n");
            sb.append(" AND\t\tA.제재횟수 = " + punishmentCount + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \n");
            sql = sb.toString();
            Log.debug("sql" + sql);
            System.out.println("sql" + sql);
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            final Vector v = new Vector();
            if (rs != null && rs.next()) {
                fileWrite = String.valueOf(fileWrite) + ComStr.checkNull(rs.getString(1), "") + "|" + ComStr.checkNull(rs.getString(2), "") + "|" + ComStr.checkNull(rs.getString(3), "") + "|" + ComStr.checkNull(rs.getString(4), "") + "|" + ComStr.checkNull(rs.getString(5), "") + "|" + ComStr.checkNull(rs.getString(6), "") + "|" + ComStr.checkNull(rs.getString(7), "") + "|" + ComStr.checkNull(rs.getString(8), "") + "|" + ComStr.checkNull(rs.getString(9), "") + "|" + ComStr.checkNull(rs.getString(10), "") + "|" + ComStr.checkNull(rs.getString(11), "") + "|" + ComStr.checkNull(rs.getString(12), "") + "|" + ComStr.checkNull(rs.getString(13), "") + "|" + ComStr.checkNull(rs.getString(14), "") + "|" + ComStr.checkNull(rs.getString(15), "") + "|" + ComStr.checkNull(rs.getString(16), "") + "개월|" + ComStr.checkNull(rs.getString(17), "") + "|" + ComStr.checkNull(rs.getString(18), "") + "|\r\n";
            }
            sb.setLength(0);
            sb.append(" SELECT\tA.순번, A.대표자명, A.주민등록번호, A.대표자주소, A.대표대표자여부\t\t\n");
            sb.append(" FROM\t사용_부정당대표자 A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.사업자등록번호 = '" + ComStr.replace(unpairNo, "-", "") + "'\t\t\t\t\n");
            sb.append(" AND\t\tA.제재횟수 = " + punishmentCount + "\t\t\t\t\t\t\t\t\t\t\n");
            sql2 = sb.toString();
            psmt2 = con.prepareStatement(sql2);
            rs2 = psmt2.executeQuery();
            psmt2.clearParameters();
            if (rs2 != null) {
                while (rs2.next()) {
                    final String JuminNo = rs2.getString(3);
                    final String JuminNo_1 = JuminNo.substring(0, 6);
                    final String JuminNo_2 = JuminNo.substring(6, JuminNo.length());
                    fileWrite = String.valueOf(fileWrite) + count + "|" + ComStr.checkNull(rs2.getString(2), "") + "|" + JuminNo_1 + "-" + JuminNo_2 + "|" + ComStr.checkNull(rs2.getString(4), "") + "|\r\n";
                    ++count;
                }
            }
            System.out.println("fileWrite" + fileWrite);
            psmt.close();
            psmt2.close();
            rs.close();
            rs2.close();
            con.close();
            Log.debug("fileWrite" + fileWrite);
        }
        catch (SQLException e) {
            Log.errors(this.getClass().getName() + " SQLException 발생함 \n" + sql + " \n" + "사업자등록번호=" + unpairNo + " \n" + e);
        }
        catch (Exception e2) {
            Log.errors(this.getClass().getName() + " Exception 발생함 \r\n" + e2);
            Log.errors(this.getClass().getName() + " Exception 발생함 \n" + "사업자등록번호=" + unpairNo + " \n" + e2);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex2) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex3) {}
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
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
        if (rs2 != null) {
            try {
                rs2.close();
            }
            catch (Exception ex8) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex9) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex10) {}
        }
        if (con != null) {
            try {
                con.close();
            }
            catch (Exception ex11) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex12) {}
        }
        out.println(fileWrite);
    }
}
