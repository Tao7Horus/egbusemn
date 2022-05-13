// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import common.Log;
import java.sql.SQLException;
import common.Trx;
import LOGIN.UM_Auth_Check;
import common.util.CommonMessage;
import beans.UM_ADB_GOMAPPING;
import beans.UM_ADB_FUNDINFO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_FundCode extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        Trx resource = null;
        Connection conn = null;
        UM_Auth_Check uac = null;
        final UM_ADB_FUNDINFO uagFundInfo = new UM_ADB_FUNDINFO();
        final UM_ADB_GOMAPPING mapping = new UM_ADB_GOMAPPING();
        final String flag = req.getParameter("flag");
        final String fCode = req.getParameter("fCode");
        final String bCode = req.getParameter("bCode");
        final String addFundCode = req.getParameter("addFundCode");
        String masterCode = req.getParameter("masterCode");
        if (masterCode == null || masterCode.equals("")) {
            masterCode = null;
        }
        String mCode = null;
        final String suyoCode = null;
        String UserID = null;
        if (flag == null || flag.equals("")) {
            CommonMessage.printMsg(null, "", "Đã không vượt qua được yêu cầu.", null, res);
            return;
        }
        try {
            uac = new UM_Auth_Check(req, res);
            UserID = uac.getID();
            if (uac.getGubun().equals("k")) {
                if (masterCode == null) {
                    mCode = uac.getMCode();
                }
                else {
                    mCode = masterCode;
                }
            }
            else {
                mCode = uac.getMCode();
            }
            resource = new Trx(this);
            conn = resource.getConnection();
            conn.setAutoCommit(false);
            if (flag.equals("e")) {
                uagFundInfo.deleteFundCode(mCode, bCode, conn);
                uagFundInfo.updateGonginfo(mCode, UserID, conn);
            }
            else if (flag.equals("g")) {
                uagFundInfo.updateFundCode(mCode, bCode, fCode, conn);
                uagFundInfo.updateGonginfo(mCode, UserID, conn);
            }
            else if (flag.equals("h")) {
                uac.checkCookie("kg", null, null);
                uagFundInfo.insertFundCode(mCode, addFundCode, conn);
                uagFundInfo.updateGonginfo(mCode, UserID, conn);
            }
            conn.commit();
            conn.setAutoCommit(true);
            CommonMessage.printMsg(null, "7", "Đã được xử lý.", "", res);
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
            Log.debug("UM_ADV_FundCode.java flag[" + flag + "], Mã cơ quan [" + suyoCode + "], Mã [" + mCode + "] 로긴ID[" + uac.getID() + "], IP[" + uac.getIP() + "], Mã số kế toán[" + bCode + "]:" + ex.toString());
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
