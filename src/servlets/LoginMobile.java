// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.io.PrintWriter;
import common.Trx;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class LoginMobile extends HttpServlet
{
    private static String sql;
    
    static {
        LoginMobile.sql = "SELECT 아이디, 비밀번호 FROM 사용_부가사용자 WHERE 아이디 = ?";
    }
    
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        res.setHeader("Cache-Control", "no-cache");
        final PrintWriter out = res.getWriter();
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String result = "None";
        final String id = (req.getParameter("id") == null) ? "" : req.getParameter("id");
        final String password = (req.getParameter("password") == null) ? "" : req.getParameter("password");
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(LoginMobile.sql);
            psmt.setString(1, id);
            rs = psmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(2).equals(password)) {
                    result = "Success";
                }
                else {
                    result = "Fail";
                }
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
            result = "Fail";
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
        out.println(result);
    }
}
