// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import entity.UM_ADV_GovuA030b;
import entity.UM_ADJ_GovuA020b;
import common.CommEntity;
import entity.UM_ADE_HistA050b;
import java.sql.Connection;
import common.Log;
import java.sql.SQLException;
import common.util.CommonMessage;
import beans.UM_RAB_GovuA010p;
import common.Trx;
import LOGIN.UM_Auth_Check;
import common.util.CommUtil;
import beans.UM_ADB_GONGHISTORY;
import beans.UM_ADB_FUNDINFO;
import beans.UM_ADB_GONGINFO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_GigwanApprove extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        Trx resource = null;
        Connection conn = null;
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        UM_Auth_Check uac = null;
        final UM_ADB_GONGINFO uagInfo = new UM_ADB_GONGINFO();
        final UM_ADB_FUNDINFO gongFund = new UM_ADB_FUNDINFO();
        final UM_ADB_GONGHISTORY gongHistory = new UM_ADB_GONGHISTORY();
        UM_ADE_HistA050b ett = null;
        String acctCode = null;
        final CommUtil com = new CommUtil();
        final String masterCode = com.retNull(req.getParameter("masterCode"));
        String UserID = null;
        CommEntity[] userEntity = null;
        try {
            uac = new UM_Auth_Check(req, res);
            UserID = uac.getID();
            resource = new Trx(this);
            conn = resource.getConnection();
            ett = gongHistory.getDetail(masterCode);
            final UM_RAB_GovuA010p govuA = new UM_RAB_GovuA010p();
            final UM_ADJ_GovuA020b caInfo = gongHistory.select_CTSInfo(masterCode);
            final UM_ADV_GovuA030b ett_old_NM = govuA.getUser_NM(ett.getManagerId());
            userEntity = govuA.getListUser_NMHist(masterCode);
            final UM_ADB_GONGHISTORY updateHis = new UM_ADB_GONGHISTORY();
            if ("Y".equals(ett.getApprove_yn())) {
                CommonMessage.printMsg(null, "1", "Bản sửa đổi đã được phê duyệt trước đó.", "", res);
                return;
            }
            acctCode = gongHistory.getAccDetail(masterCode);
            conn.setAutoCommit(false);
            gongHistory.deleteAcct(masterCode);
            gongHistory.deleteCANewest(masterCode, conn);
            updateHis.deleteUSER_Hist(conn, masterCode);
            gongHistory.delete(masterCode);
            gongHistory.insertGongHistory(UserID, masterCode, "Y", conn);
            gongHistory.saveCA(masterCode, conn);
            gongHistory.insertAcctHist(masterCode, conn);
            updateHis.insertUserHist(masterCode, conn);
            uagInfo.updateGongInfo(ett, conn);
            gongFund.updateFundCode(masterCode, acctCode, conn);
            updateHis.updateUSER(userEntity, conn);
            gongHistory.updateCA(masterCode, caInfo, conn);
            conn.commit();
            conn.setAutoCommit(true);
            CommonMessage.printMsg(null, "1", "Đã phê duyệt thành công.", "/AD/UM_ADJ_GovrP020y.jsp", res);
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
            Log.errors(this, ex, "");
            CommonMessage.printMsg(null, "Không phê duyệt được.", ex.getMessage(), null, res);
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
        try {
            if (conn != null) {
                resource.close();
            }
        }
        catch (Exception ex6) {}
    }
}
