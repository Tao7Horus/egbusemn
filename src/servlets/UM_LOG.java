// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import common.Log;
import common.util.HttpUtility;
import common.Trx;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_LOG extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        String query = null;
        String ID = "";
        String masterCode = "";
        String useGubun = "";
        String gubun = "";
        String saupNo = "";
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            ID = ((req.getParameter("ID") == null) ? "" : req.getParameter("ID"));
            masterCode = ((req.getParameter("masterCode") == null) ? "" : req.getParameter("masterCode"));
            useGubun = ((req.getParameter("useGubun") == null) ? "" : req.getParameter("useGubun"));
            gubun = ((req.getParameter("gubun") == null) ? "" : req.getParameter("gubun"));
            saupNo = ((req.getParameter("saup_No") == null) ? "" : req.getParameter("saup_No"));
            query = " INSERT INTO 사용_업체정보_코참비즈조회LOG (  사용자ID, 조회일자, 마스터코드, 사용자구분, 구분  ) VALUES (  ?, sysdate, ?, ?, ?  ) ";
            psmt = con.prepareStatement(query);
            psmt.setString(1, ID);
            psmt.setString(2, masterCode);
            psmt.setString(3, useGubun);
            psmt.setString(4, gubun);
            psmt.executeUpdate();
            psmt.clearParameters();
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + " :경고성으로 무시 : ID[" + ID + "] masterCode[" + masterCode + "] useGubun[" + useGubun + "] gubun [" + gubun + "], IP[" + HttpUtility.getIP(req) + "]" + exc.toString());
        }
        finally {
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex2) {}
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex3) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex4) {}
        }
        res.sendRedirect("/jsp/GO/UM_GOJ_ConiC020s.jsp?saupNo=" + saupNo + "&reqGubun=EDI");
    }
}
