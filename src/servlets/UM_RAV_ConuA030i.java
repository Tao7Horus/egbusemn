// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import common.Trx;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_RAV_ConuA030i extends HttpServlet
{
    private static final long serialVersionUID = -8709532016850972377L;
    
    public void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String bizRegNo = request.getParameter("bizRegNo");
        if (bizRegNo != null && bizRegNo.length() != 0) {
            String sql = "select a.REPR_IDENT_NO, b.STD_CLS_CD, c.LICENSE_CD, d.IDENT_NO, e.REPR_IDENT_NO\n";
            sql = String.valueOf(sql) + "from UM_REC_REPR a, UM_REC_ENTER_STD_CLS b, UM_REC_LICENSE_FACTS c, UM_REC_BID_AGENT d, UM_REC_PUB_INSTITU_CERT e\n";
            sql = String.valueOf(sql) + "where a.BIZ_REG_NO = '" + bizRegNo + "'" + " and  b.BIZ_REG_NO = '" + bizRegNo + "'" + " and  c.BIZ_REG_NO = '" + bizRegNo + "'" + " and  d.BIZ_REG_NO = '" + bizRegNo + "'" + " and  e.BIZ_REG_NO = '" + bizRegNo + "'";
            Trx tr = null;
            Connection conn = null;
            ResultSet rs = null;
            try {
                tr = new Trx(this);
                conn = tr.getConnection();
                final PreparedStatement pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                String params = "";
                while (rs.next()) {
                    params = "bizRegNo=" + bizRegNo;
                    params = String.valueOf(params) + "&reprIdentNo=" + rs.getString(1);
                    params = String.valueOf(params) + "&stdClsCode=" + rs.getString(2);
                    params = String.valueOf(params) + "&bizRegNo=" + rs.getString(3);
                    params = String.valueOf(params) + "&bizIdentNo=" + rs.getString(4);
                    params = String.valueOf(params) + "&caReprIdentNo=" + rs.getString(5);
                }
                System.out.println("\n\n============> params ==============>\n" + params);
                response.sendRedirect("/RA/UM_RAJ_ConuA030i.jsp?" + params);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
