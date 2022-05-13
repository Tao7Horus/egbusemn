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
import java.util.Vector;
import common.ComStr;
import common.Trx;
import javax.servlet.ServletRequest;
import com.oreilly.servlet.ParameterParser;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_GOV_Print01_rex extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/xml;charset=euc-kr");
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
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sb.append(" SELECT\tA.문서번호, A.공공기관명,\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n");
            sb.append("     \tA.담당자명, A.담당자전화번호,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n");
            sb.append(" \t\tTO_CHAR(A.입력일자, 'YYYY. MM. DD') 입력일자, A.상호명,\t\t\t\t\t \n");
            sb.append(" \t\tA.사업자등록번호, A.우편번호,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n");
            sb.append(" \t\tA.주소 ||' '|| A.나머지주소 주소, A.법인등록번호,\t\t\t\t\t\t\t\t\t \n");
            sb.append(" \t\tA.영업종목, A.면허등록번호,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n");
            sb.append(" \t\tA.제재근거1 ||' '|| A.제재근거2 제재근거,\t\t\t\t\t\t\t\t\t\t\t \n");
            sb.append(" \t\tTO_CHAR(A.제재연월일, 'YYYY. MM. DD') 제재연월일,                          \n");
            sb.append(" \t\tTO_CHAR(A.만료연월일, 'YYYY. MM. DD') 만료연월일,   A.제재기간,\t     \n");
            sb.append(" \t\tTO_CHAR(A.해약연월일, 'YYYY. MM. DD') 해약연월일,   A.기타              \n");
            sb.append(" FROM\t사용_부정당업자\tA                                 \t\t\t\t\t\t\t\t\t     \n");
            sb.append(" WHERE\tA.사업자등록번호 = '" + ComStr.replace(unpairNo, "-", "") + "'\t\t \n");
            sb.append(" AND\t\tA.제재횟수 = " + punishmentCount + "\t\t\t\t\t\t\t\t\t\t\t \n");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            final Vector v = new Vector();
            fileWrite = "<?xml version='1.0' encoding='EUC-KR'?>";
            fileWrite = String.valueOf(fileWrite) + "<main>";
            fileWrite = String.valueOf(fileWrite) + "<row>";
            if (rs != null && rs.next()) {
                fileWrite = String.valueOf(fileWrite) + "<문서번호><![CDATA[" + ComStr.checkNull(rs.getString(1), "") + "]]></문서번호>" + "<공공기관명><![CDATA[" + ComStr.checkNull(rs.getString(2), "") + "]]></공공기관명>" + "<담당자명><![CDATA[" + ComStr.checkNull(rs.getString(3), "") + "]]></담당자명>" + "<담당자전화번호><![CDATA[" + ComStr.checkNull(rs.getString(4), "") + "]]></담당자전화번호>" + "<입력일자><![CDATA[" + ComStr.checkNull(rs.getString(5), "") + "]]></입력일자>" + "<상호명><![CDATA[" + ComStr.checkNull(rs.getString(6), "") + "]]></상호명>" + "<사업자등록번호><![CDATA[" + ComStr.checkNull(rs.getString(7), "") + "]]></사업자등록번호>" + "<우편번호><![CDATA[" + ComStr.checkNull(rs.getString(8), "") + "]]></우편번호>" + "<주소><![CDATA[" + ComStr.checkNull(rs.getString(9), "") + "]]></주소>" + "<법인등록번호><![CDATA[" + ComStr.checkNull(rs.getString(10), "") + "]]></법인등록번호>" + "<영업종목><![CDATA[" + ComStr.checkNull(rs.getString(11), "") + "]]></영업종목>" + "<면허등록번호><![CDATA[" + ComStr.checkNull(rs.getString(12), "") + "]]></면허등록번호>" + "<제재근거><![CDATA[" + ComStr.checkNull(rs.getString(13), "") + "]]></제재근거>" + "<제재연월일><![CDATA[" + ComStr.checkNull(rs.getString(14), "") + "]]></제재연월일>" + "<만료연월일><![CDATA[" + ComStr.checkNull(rs.getString(15), "") + "]]></만료연월일>" + "<제재기간><![CDATA[" + ComStr.checkNull(rs.getString(16), "") + "개월" + "]]></제재기간>" + "<해약연월일><![CDATA[" + ComStr.checkNull(rs.getString(17), "") + "]]></해약연월일>" + "<기타><![CDATA[" + ComStr.checkNull(rs.getString(18), "") + "]]></기타>";
            }
            sb.setLength(0);
            sb.append(" SELECT\tA.순번, A.대표자명, SUBSTR(A.주민등록번호,1,8)||'*****' 주민등록번호,\t\t\n");
            sb.append(" \t\t\t\tA.대표자주소, A.대표대표자여부\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" FROM\t사용_부정당대표자 A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.사업자등록번호 = '" + ComStr.replace(unpairNo, "-", "") + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" AND\t\tA.제재횟수 = " + punishmentCount + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sql2 = sb.toString();
            psmt2 = con.prepareStatement(sql2);
            rs2 = psmt2.executeQuery();
            psmt2.clearParameters();
            if (rs2 != null) {
                fileWrite = String.valueOf(fileWrite) + "<sub1>";
                while (rs2.next()) {
                    final String JuminNo = rs2.getString(3);
                    final String JuminNo_1 = JuminNo.substring(0, 6);
                    final String JuminNo_2 = JuminNo.substring(6, JuminNo.length());
                    fileWrite = String.valueOf(fileWrite) + "<row><순번><![CDATA[" + count + "]]></순번>" + "<대표자명><![CDATA[" + ComStr.checkNull(rs2.getString(2), "") + "]]></대표자명>" + "<대표자주민번호><![CDATA[" + JuminNo_1 + "-" + JuminNo_2 + "]]></대표자주민번호>" + "<대표자주소><![CDATA[" + ComStr.checkNull(rs2.getString(4), "") + "]]></대표자주소>" + "</row>";
                    ++count;
                }
                fileWrite = String.valueOf(fileWrite) + "</sub1>";
            }
            psmt.close();
            psmt2.close();
            rs.close();
            rs2.close();
            con.close();
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
        fileWrite = String.valueOf(fileWrite) + "</row>";
        fileWrite = String.valueOf(fileWrite) + "</main>";
        out.println(fileWrite);
    }
}
