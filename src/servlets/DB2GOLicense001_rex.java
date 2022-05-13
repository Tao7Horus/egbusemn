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

public class DB2GOLicense001_rex extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/xml;charset=euc-kr");
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
            sb.append("\n SELECT    접수번호,       공공기관코드,   공공기관명_전체,    공공기관명_영문,        대표자,         ");
            sb.append("\n           대표자주민번호, 우편번호,       주소,               나머지주소,             사업자등록번호, ");
            sb.append("\n           팩스번호,\t    담당자명,       담당자주민번호,     담당자부서명,           담당자전화번호, ");
            sb.append("\n           담당자메일주소, 사용자ID,       공인인증기관,       전자서명인증서고유명,   인가코드,      접수내용   ");
            sb.append("\n   FROM  사용_접수공공기관인증서                                                                       ");
            sb.append("\n  WHERE 접수번호 = '" + recept + "'                                                                        ");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            fileWrite = "<?xml version='1.0' encoding='EUC-KR'?>";
            fileWrite = String.valueOf(fileWrite) + "<main>";
            if (rs != null && rs.next()) {
                final String DJuminNo = rs.getString(6);
                final String DJuminNo_1 = DJuminNo.substring(0, 6);
                final String DJuminNo_2 = DJuminNo.substring(6, DJuminNo.length());
                final String ZipCode = rs.getString(7);
                final String ZipCode_1 = ZipCode.substring(0, 3);
                final String ZipCode_2 = ZipCode.substring(3, ZipCode.length());
                final String SaupNumber = rs.getString(10);
                final String SaupNumber_1 = SaupNumber.substring(0, 3);
                final String SaupNumber_2 = SaupNumber.substring(3, 5);
                final String SaupNumber_3 = SaupNumber.substring(5, SaupNumber.length());
                final String JuminNo = rs.getString(13);
                final String JuminNo_1 = JuminNo.substring(0, 6);
                final String JuminNo_2 = JuminNo.substring(6, JuminNo.length());
                final String License = rs.getString(18);
                String LicenseNm = "";
                if (License.equals("0")) {
                    LicenseNm = "한국정보인증 : http://www.signgate.com/ra/g2b";
                }
                else if (License.equals("1")) {
                    LicenseNm = "한국정보사회진흥원 : http://sign.nia.or.kr";
                }
                else if (License.equals("2")) {
                    LicenseNm = "(주)코스콤 공인인증센터(한국증권전산) : http://www.signkorea.com";
                }
                else if (License.equals("4")) {
                    LicenseNm = "한국전자인증 : http://www.naraca.co.kr";
                }
                fileWrite = String.valueOf(fileWrite) + "<row><접수번호><![CDATA[" + rs.getString(1) + "]]></접수번호>" + "<공공기관코드><![CDATA[" + rs.getString(2) + "]]></공공기관코드>" + "<공공기관명_전체><![CDATA[" + rs.getString(3) + "]]></공공기관명_전체>" + "<공공기관명_영문><![CDATA[" + ComStr.checkNull(rs.getString(4)) + "]]></공공기관명_영문>" + "<대표자><![CDATA[" + rs.getString(5) + "]]></대표자>" + "<대표자주민번호><![CDATA[" + DJuminNo_1 + "-" + DJuminNo_2 + "]]></대표자주민번호>" + "<우편번호><![CDATA[" + ZipCode_1 + "-" + ZipCode_2 + "]]></우편번호>" + "<주소><![CDATA[" + rs.getString(8) + "]]></주소>" + "<나머지주소><![CDATA[" + rs.getString(9) + "]]></나머지주소>" + "<사업자등록번호><![CDATA[" + SaupNumber_1 + "-" + SaupNumber_2 + "-" + SaupNumber_3 + "]]></사업자등록번호>" + "<팩스번호><![CDATA[" + ComStr.checkNull(rs.getString(11)) + "]]></팩스번호>" + "<담당자명><![CDATA[" + rs.getString(12) + "]]></담당자명>" + "<담당자주민번호><![CDATA[" + JuminNo_1 + "-" + JuminNo_2 + "]]></담당자주민번호>" + "<담당자부서명><![CDATA[" + rs.getString(14) + "]]></담당자부서명>" + "<담당자전화번호><![CDATA[" + rs.getString(15) + "]]></담당자전화번호>" + "<담당자메일주소><![CDATA[" + rs.getString(16) + "]]></담당자메일주소>" + "<사용자ID><![CDATA[" + ComStr.checkNull(rs.getString(17)) + "]]></사용자ID>" + "<공인인증기관><![CDATA[" + LicenseNm + "]]></공인인증기관>" + "<전자서명인증서고유명><![CDATA[" + ComStr.checkNull(rs.getString(19)) + "]]></전자서명인증서고유명>" + "<접수내용><![CDATA[" + ComStr.checkNull(rs.getString(21)) + "]]></접수내용>" + "<인가코드><![CDATA[" + ComStr.checkNull(rs.getString(20)) + "]]></인가코드>" + "</row>";
            }
            rs.close();
        }
        catch (SQLException e) {
            Log.errors(this.getClass().getName() + " SQLException 발생함 \n" + sql + " \r\n" + e);
        }
        catch (Exception e2) {
            Log.errors(this.getClass().getName() + " Exception 발생함 \r\n" + e2);
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
        fileWrite = String.valueOf(fileWrite) + "</main>";
        out.println(fileWrite);
    }
}
