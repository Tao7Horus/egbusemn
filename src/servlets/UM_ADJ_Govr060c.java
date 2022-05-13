// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Trx;
import common.Log;
import entity.UM_ADJ_GovuA020b;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADJ_Govr060c extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        PreparedStatement psmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        String sql = null;
        final String sql2 = null;
        final String sql3 = null;
        final StringBuffer sb = new StringBuffer();
        final UM_ADJ_GovuA020b entity = new UM_ADJ_GovuA020b();
        String G2BCODE = "";
        String CNAME = "";
        String IDENT = "";
        String KNAME = "";
        String IDENTJUMIN = "";
        String ZIPNO = "";
        String COMNO = "";
        String ADDRS = "";
        String ADDRESS2 = "";
        String FAX = "";
        String MYNAME = "";
        String OFFICEDE = "";
        String JUMIN = "";
        String TEL = "";
        String EMAIL = "";
        String HP = "";
        String USRID = "";
        String CERT_ORG = "";
        String STR = "";
        String STR2 = "";
        String HOME = "";
        String recept = "";
        G2BCODE = ((req.getParameter("G2BCODE") == null) ? "" : req.getParameter("G2BCODE"));
        CNAME = ((req.getParameter("CNAME") == null) ? "" : req.getParameter("CNAME"));
        IDENT = ((req.getParameter("IDENT") == null) ? "" : req.getParameter("IDENT"));
        KNAME = ((req.getParameter("KNAME") == null) ? "" : req.getParameter("KNAME"));
        IDENTJUMIN = ((req.getParameter("IDENTJUMIN") == null) ? "" : req.getParameter("IDENTJUMIN"));
        ZIPNO = ((req.getParameter("ZIPNO") == null) ? "" : req.getParameter("ZIPNO"));
        COMNO = ((req.getParameter("COMNO") == null) ? "" : req.getParameter("COMNO"));
        ADDRS = ((req.getParameter("ADDRS") == null) ? "" : req.getParameter("ADDRS"));
        ADDRESS2 = ((req.getParameter("ADDRESS2") == null) ? "" : req.getParameter("ADDRESS2"));
        FAX = ((req.getParameter("FAX") == null) ? "" : req.getParameter("FAX"));
        MYNAME = ((req.getParameter("MYNAME") == null) ? "" : req.getParameter("MYNAME"));
        OFFICEDE = ((req.getParameter("OFFICEDE") == null) ? "" : req.getParameter("OFFICEDE"));
        JUMIN = ((req.getParameter("JUMIN") == null) ? "" : req.getParameter("JUMIN"));
        TEL = ((req.getParameter("TEL") == null) ? "" : req.getParameter("TEL"));
        EMAIL = ((req.getParameter("EMAIL") == null) ? "" : req.getParameter("EMAIL"));
        HP = ((req.getParameter("HP") == null) ? "" : req.getParameter("HP"));
        USRID = ((req.getParameter("USRID") == null) ? "" : req.getParameter("USRID"));
        CERT_ORG = ((req.getParameter("CERT_ORG") == null) ? "" : req.getParameter("CERT_ORG"));
        STR = ((req.getParameter("CNAME") == null) ? "" : req.getParameter("CNAME"));
        STR2 = ((req.getParameter("STR1") == null) ? "" : req.getParameter("STR1"));
        HOME = ((req.getParameter("HOME") == null) ? "" : req.getParameter("HOME"));
        recept = ((req.getParameter("recept") == null) ? "" : req.getParameter("recept"));
        Log.debug("CCC");
        try {
            try {
                trx = new Trx(this, "usemn");
            }
            catch (Exception ex) {}
            con = trx.getConnection();
            con.setAutoCommit(false);
            Log.debug("AAAA");
            String count = "";
            final String code = "";
            sql = "select count(*) from 사용_접수공공기관인증서 ";
            System.out.println("sql" + sql);
            psmt2 = con.prepareStatement(sql);
            rs2 = psmt2.executeQuery();
            if (rs2.next()) {
                count = rs2.getString(1);
                psmt2.clearParameters();
                System.out.println("count==" + count);
            }
            rs2.close();
            psmt2.close();
            if (count.equals("0")) {
                recept = "2002000001";
            }
            else {
                sql = "SELECT to_char(sysdate,'yyyy')||lpad((substr(max(접수번호), 5, 10)+1), 6, '0') \n FROM   사용_접수공공기관인증서 ";
                psmt = con.prepareStatement(sql);
                rs = psmt.executeQuery();
                if (rs.next()) {
                    recept = rs.getString(1);
                    System.out.println("recept==" + recept);
                    psmt.clearParameters();
                }
            }
            sql = "insert into 사용_접수공공기관인증서(접수번호,공공기관코드,공공기관명_전체,공공기관명_영문,대표자,대표자주민번호,우편번호,주소,나머지주소,사업자등록번호,팩스번호,담당자명, 담당자부서명, 담당자주민번호,담당자전화번호,담당자메일주소,핸드폰,사용자ID,닉네임,공인인증기관,접수일자,홈페이지) values(?,?, ?, ?, ?, ?, ?, ?, ?,?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, sysdate,?)";
            psmt = con.prepareStatement(sql);
            Log.debug(sql);
            psmt.setString(1, recept);
            psmt.setString(2, G2BCODE);
            psmt.setString(3, CNAME);
            psmt.setString(4, KNAME);
            psmt.setString(5, IDENT);
            psmt.setString(6, IDENTJUMIN);
            psmt.setString(7, ZIPNO);
            psmt.setString(8, ADDRS);
            psmt.setString(9, ADDRESS2);
            psmt.setString(10, COMNO);
            psmt.setString(11, FAX);
            psmt.setString(12, MYNAME);
            psmt.setString(13, OFFICEDE);
            psmt.setString(14, JUMIN);
            psmt.setString(15, TEL);
            psmt.setString(16, EMAIL);
            psmt.setString(17, HP);
            psmt.setString(18, USRID);
            psmt.setString(19, STR);
            psmt.setString(20, CERT_ORG);
            psmt.setString(21, HOME);
            Log.debug("DDDDD");
            psmt.executeUpdate();
            Log.debug("ㄸㄸㄸㄸㄸ");
            psmt.clearParameters();
            Log.debug("EEEEE");
            con.commit();
            con.setAutoCommit(true);
            res.sendRedirect("/jsp/RA/UM_RAJ_GovrL010s.jsp?code=" + G2BCODE);
        }
        catch (SQLException e) {
            try {
                con.rollback();
            }
            catch (SQLException ex2) {}
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex3) {}
            }
            Log.debug("UM_ADJ_Govr060c SQLException : Transaction Rollback간에 SQLException 발생함");
            Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
            e.printStackTrace();
            return;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (SQLException ex4) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (SQLException ex5) {}
            }
            if (psmt2 != null) {
                try {
                    psmt.close();
                }
                catch (SQLException ex6) {}
            }
            if (con != null) {
                try {
                    psmt.close();
                }
                catch (SQLException ex7) {}
            }
            trx.close();
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException ex8) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (SQLException ex9) {}
        }
        if (psmt2 != null) {
            try {
                psmt.close();
            }
            catch (SQLException ex10) {}
        }
        if (con != null) {
            try {
                psmt.close();
            }
            catch (SQLException ex11) {}
        }
        trx.close();
    }
}
