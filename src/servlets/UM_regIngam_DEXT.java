// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.File;
import common.Log;
import common.Trx;
import common.util.CommonMessage;
import beans.FileUpManager_DEXT;
import beans.UM_IngamControl;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_regIngam_DEXT extends HttpServlet
{
    private static final long serialVersionUID = 8465688754787731758L;
    
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s = "/data1/AppData/apps/usemn/seal";
        FileUpManager_DEXT fileUpManager_DEXT = null;
        final UM_IngamControl um_IngamControl = new UM_IngamControl();
        Trx trx = null;
        Connection connection = null;
        String s2 = "";
        String message = "";
        try {
            try {
                fileUpManager_DEXT = new FileUpManager_DEXT(httpServletRequest, s, 30, httpServletResponse);
            }
            catch (Exception ex) {
                s2 = "b";
                message = ex.getMessage();
            }
            if (s2.equals("b")) {
                CommonMessage.printMsg(null, "", "Kích thước file tối đa là 30kb.<br> Hãy kiểm tra và đăng ký lại." + message, null, httpServletResponse);
                return;
            }
            final String parameter = httpServletRequest.getParameter("saupNo");
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            connection.setAutoCommit(false);
            final String maxNumber = um_IngamControl.getMaxNumber(parameter, connection);
            final String string = parameter + "-B-" + maxNumber;
            Log.debug("ingamName : " + string);
            if (!fileUpManager_DEXT.renameTo("inGam", string)) {
                connection.commit();
                connection.setAutoCommit(true);
                CommonMessage.printMsg(null, "", "Đăng ký con dấu không thành công.<br>Xin vui lòng thử lại.", null, httpServletResponse);
                return;
            }
            try {
                um_IngamControl.insertIngam(parameter, maxNumber, connection);
            }
            catch (Exception ex2) {
                try {
                    new File(s + "\\" + string).delete();
                }
                catch (Exception ex4) {}
                throw ex2;
            }
            connection.commit();
            connection.setAutoCommit(true);
            CommonMessage.printMsg("", "5", "Đăng ký thành công.", "", httpServletResponse);
        }
        catch (Exception ex3) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            }
            catch (SQLException ex5) {}
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            }
            catch (SQLException ex6) {}
            Log.debug(ex3.getMessage());
            CommonMessage.printMsg(null, "", ex3.getMessage(), null, httpServletResponse);
        }
        finally {
            try {
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex7) {}
        }
    }
}
