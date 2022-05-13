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

public class DB2GOLicense extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        String fileWrite = null;
        final PrintWriter out = res.getWriter();
        final String fileName = ComStr.checkNull(req.getParameter("fileName"), "");
        final String recept = ComStr.checkNull(req.getParameter("recept"), "");
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sb.setLength(0);
            sql = "";
            sb.append("\n SELECT    RECV_NO,       INSTITU_CD,   INSTITU_FULL_NM,    INSTITU_EN_NM,        REPR_NM,         ").append("\n           REPR_IDENT_NO, ZIP_CD,       ADDR,               DETAIL_ADDR,             BIZ_REG_NO, ").append("\n           FAX,\t    CHRGR_MN,       CHRGR_IDENT_NO,     CHRGR_DEPART,           CHRGR_PHONE_NO, ").append("\n           CHRGR_EMAIL, USER_ID,       CERT_INSTITU,       CERT_NM,   PERMIT_CD,      RECV_CONTENT   ").append("\n   FROM  UM_REC_PUB_INSTITU_CERT                                                                       ").append("\n  WHERE RECV_NO = '" + recept + "'                                                                        ");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            if (rs.next()) {
                final String DJuminNo = rs.getString(6);
                final String DJuminNo_1 = DJuminNo.substring(0, 6);
                final String DJuminNo_2 = DJuminNo.substring(6, DJuminNo.length());
                final String ZipCode = rs.getString(7);
                final String SaupNumber = rs.getString(10);
                final String JuminNo = rs.getString(13);
                final String License = rs.getString(18);
                String LicenseNm = "";
                if (License.equals("0")) {
                    LicenseNm = "한국정보인증 : http://www.signgate.com";
                }
                else if (License.equals("1")) {
                    LicenseNm = "한국정보사회진흥원 : http://sign.nia.or.kr";
                }
                else if (License.equals("2")) {
                    LicenseNm = "(주)코스콤 공인인증센터(한국증권전산) : http://www.signkorea.com";
                }
                else if (License.equals("5")) {
                    LicenseNm = "MPI Bộ Kế hoạch và Đầu tư : http://mpi.gov.vn";
                }
                fileWrite = String.valueOf(rs.getString(1)) + "^" + rs.getString(2) + "^" + rs.getString(3) + "^" + ComStr.checkNull(rs.getString(4)) + "^" + rs.getString(5) + "^" + DJuminNo_1 + "-" + DJuminNo_2 + "^" + ZipCode + "^" + rs.getString(8) + rs.getString(9) + "^" + SaupNumber + "^" + ComStr.checkNull(rs.getString(11)) + "^" + rs.getString(12) + "^" + JuminNo + "^" + rs.getString(14) + "^" + rs.getString(15) + "^" + rs.getString(16) + "^" + ComStr.checkNull(rs.getString(17)) + "^" + LicenseNm + "^" + rs.getString(19) + "^" + ComStr.checkNull(rs.getString(20)) + "-" + ComStr.checkNull(rs.getString(21)) + "^\r\n";
            }
        }
        catch (SQLException e) {
            Log.errors("DB2GOLicense.java : " + e.toString());
        }
        catch (Exception e2) {
            Log.errors("DB2GOLicense.java : " + e2.toString());
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
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
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
