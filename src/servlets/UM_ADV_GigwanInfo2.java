// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.util.CommonMessage;
import common.Trx;
import LOGIN.UM_Auth_Check;
import common.util.CommUtil;
import beans.UM_ADB_OrgStandInfo;
import beans.UM_ADB_GONGHISTORY;
import beans.UM_ADB_GONGINFO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_GigwanInfo2 extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        Trx resource = null;
        Connection conn = null;
        UM_Auth_Check uac = null;
        final UM_ADB_GONGINFO uagInfo = new UM_ADB_GONGINFO();
        final UM_ADB_GONGHISTORY gongHistory = new UM_ADB_GONGHISTORY();
        final UM_ADB_OrgStandInfo orgInfo = new UM_ADB_OrgStandInfo();
        final CommUtil com = new CommUtil();
        final String masterCode = com.retNull(req.getParameter("masterCode"));
        String UserID = null;
        final String synSogwanGubun = "";
        final String synRegionCode = "";
        String mCode = "";
        boolean isKigwanApprove = false;
        try {
            uac = new UM_Auth_Check(req, res);
            if (uac.getGubun().equals("k")) {
                if (masterCode == null) {
                    mCode = uac.getMCode();
                }
                else {
                    isKigwanApprove = true;
                    mCode = masterCode;
                }
            }
            else {
                mCode = uac.getMCode();
            }
            UserID = uac.getID();
            resource = new Trx(this, "usemn");
            conn = resource.getConnection();
            conn.setAutoCommit(false);
            final String suyoCode = null;
            if (isKigwanApprove) {
                gongHistory.insertGongHistory(UserID, mCode, "Y", conn);
            }
            else {
                gongHistory.insertGongHistory(UserID, mCode, "Y", conn);
            }
            uagInfo.deleteGongInfo(mCode, UserID, conn);
            conn.commit();
            conn.setAutoCommit(true);
            CommonMessage.printMsg(null, "8", "Đã xóa thành công.", "", res);
        }
        catch (Exception ex) {
            Log.errors(this, ex, "");
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
