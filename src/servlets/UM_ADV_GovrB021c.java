// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import common.Log;
import java.sql.SQLException;
import common.util.CommonMessage;
import common.Trx;
import LOGIN.UM_Auth_Check;
import beans.UM_ADB_GONGCERTRECEIVE;
import beans.UM_ADB_GONGRECEIVE;
import common.util.CommUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_GovrB021c extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        Trx resource = null;
        Connection conn = null;
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        UM_Auth_Check uac = null;
        final CommUtil comutil = new CommUtil();
        final UM_ADB_GONGRECEIVE gongReceive = new UM_ADB_GONGRECEIVE();
        final UM_ADB_GONGCERTRECEIVE gongCertReceive = new UM_ADB_GONGCERTRECEIVE();
        final String restoration = CommUtil.retSpace(req.getParameter("restoration"));
        final String recept = CommUtil.retSpace(req.getParameter("recept"));
        final String LicenseCode = CommUtil.retSpace(req.getParameter("LicenseCode"));
        final String LicenseGubun = CommUtil.retSpace(req.getParameter("LicenseGubun"));
        String userID = "";
        String regStatus = null;
        final String masterCode = comutil.retNull(req.getParameter("masterCode"));
        while (true) {
            try {
                uac = new UM_Auth_Check(req, res);
                uac.checkCookie("k", null, null);
                userID = uac.getID();
                resource = new Trx(this);
                conn = resource.getConnection();
                conn.setAutoCommit(false);
                if (recept == null || recept == "") {
                    throw new Exception("Số tiếp nhận là null.");
                }
                regStatus = gongReceive.getRegStatus(recept, conn);
                if (!regStatus.equals("N") && !regStatus.equals("E")) {
                    conn.setAutoCommit(true);
                    CommonMessage.printMsg(null, "7", "Đã được chấp nhận xử lý.", null, res);
                    try {}
                    catch (Exception ex2) {}
                    return;
                }
                gongReceive.updateReceiveGonggonMaster(restoration, recept, "D", userID, conn);
                if (LicenseGubun.equals("Y")) {
                    regStatus = gongCertReceive.getRegStatus(LicenseCode, conn);
                    if (!regStatus.equals("N") && !regStatus.equals("E")) {
                        final String message = "Các ứng dụng cho các chứng chỉ được chấp thuận.<br>Xin vui lòng cập nhật các màn hình.<br>Xin vui lòng liên hệ với quản trị viên.";
                        throw new Exception(message);
                    }
                    gongCertReceive.updateReceiveGonggonCert(restoration, LicenseCode, "D", userID, conn);
                }
                gongCertReceive.updateReceiveGonggonCert("Từ chối", recept, "D", userID, conn);
                conn.commit();
                conn.setAutoCommit(true);
                CommonMessage.printMsg(null, "7", "Đã xử lý từ chối thông tin bên mời thầu thành công!", null, res);
            }
            catch (Exception ex) {
                try {
                    if (conn != null) {
                        conn.rollback();
                    }
                }
                catch (SQLException ex3) {}
                try {
                    if (conn != null) {
                        conn.setAutoCommit(true);
                    }
                }
                catch (SQLException ex4) {}
                Log.debug("UM_ADV_GovrB021c.java số tiếp nhận bên mời thầu [" + recept + "], số tiếp nhận chứng chỉ số [" + LicenseCode + "], loại giấy chứng nhận [" + LicenseGubun + "], UserID[" + userID + "]:" + ex.toString());
                CommonMessage.printMsg(null, "", ex.getMessage(), null, res);
                continue;
            }
            finally {
                try {
                    if (conn != null) {
                        resource.close();
                    }
                }
                catch (Exception ex5) {}
            }
            break;
        }
        try {
            if (conn != null) {
                resource.close();
            }
        }
        catch (Exception ex6) {}
    }
}
