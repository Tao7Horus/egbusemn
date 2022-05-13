// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import LOGIN.UM_Auth_Check;
import common.Log;
import java.sql.SQLException;
import common.util.CommonMessage;
import common.Trx;
import beans.UM_IngamControl;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_deleteIngam extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final String imageName = req.getParameter("imageName");
        final String no = req.getParameter("no");
        final String saupNo = req.getParameter("saupNo");
        final UM_IngamControl umic = new UM_IngamControl();
        final UM_Auth_Check cookieCheck = null;
        Trx resource = null;
        Connection conn = null;
        try {
            resource = new Trx(this, "usemn");
            conn = resource.getConnection();
            conn.setAutoCommit(false);
            umic.updateIngamData(saupNo, imageName, no, "B", conn);
            conn.commit();
            conn.setAutoCommit(true);
            CommonMessage.printMsg("", "5", "Đã xử lý xóa.", "", res);
        }
        catch (Exception ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            }
            catch (SQLException ex2) {}
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            }
            catch (SQLException ex3) {}
            Log.debug("[UM_deleteIngam.java] Số đăng ký kinh doanh [" + saupNo + "], số[" + no + "],ảnh [" + imageName + "], ID người dùng[" + cookieCheck.getID() + "] :" + ex.toString());
            CommonMessage.printMsg(null, "", ex.getMessage(), null, res);
            return;
        }
        finally {
            try {
                if (conn != null) {
                    resource.close();
                }
            }
            catch (Exception ex4) {}
        }
        try {
            if (conn != null) {
                resource.close();
            }
        }
        catch (Exception ex5) {}
    }
}
