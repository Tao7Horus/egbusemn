// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import common.OneRowEntity;
import common.util.HttpUtility;
import common.Trx;
import beans.UM_URB_UserE040p;
import java.sql.Connection;
import LOGIN.UM_Auth_Check;
import g2b.sso.SSO;
import java.io.IOException;
import javax.servlet.ServletException;
import common.Log;
import common.util.CommonMessage;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_URV_UserE050c extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final String flag = ComStr.checkNull(req.getParameter("flag"));
        try {
            if (flag.equals("m")) {
                this.processModify(req, res);
                return;
            }
            if (flag.equals("d")) {
                this.processdelete(req, res);
                return;
            }
            if (flag.equals("r")) {
                this.processRecovery(req, res);
                return;
            }
            CommonMessage.printMessage("", "4", "Cần phải kiểm tra Parameter.flag:" + flag, "", res, req);
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + "flag[" + flag + "]:" + exf.toString());
            CommonMessage.printMessage("", "4", exf.getMessage(), "", res, req);
        }
    }
    
    private void processModify(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        Trx trx = null;
        Connection con = null;
        final String keyID = ComStr.checkNull(req.getParameter("newKeyID"));
        final SSO sso = new SSO(req, res);
        if (!sso.isLogin()) {
            return;
        }
        final UM_Auth_Check uac = new UM_Auth_Check(req, res);
        try {
            if (!uac.isSosokEquals(keyID, uac.getID(), null)) {
                CommonMessage.printMessage("", "4", "Nếu không phải nhà thầu hoặc bên mời thầu thì không có quyền sử dụng", "", res, req);
                Log.debug(String.valueOf(this.getClass().getName()) + " keyID[" + keyID + "], ID đăng nhập [" + uac.getID() + "] không có quyền sử dụng");
                return;
            }
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + " keyID[" + keyID + "]:" + ex.toString());
            return;
        }
        final UM_URB_UserE040p urc = new UM_URB_UserE040p();
        final String masterCode = uac.getMCode();
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            con.setAutoCommit(false);
            urc.updateKeyIDYN(con, masterCode, keyID);
            urc.updateModifyDate(keyID, con);
            con.commit();
            con.setAutoCommit(true);
            CommonMessage.printMsg("", "5", "Đã thực hiện.", "/RA/UM_URJ_UserE030l.jsp", res);
        }
        catch (Exception exm) {
            if (con != null) {
                try {
                    con.rollback();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                }
                catch (Exception ex3) {}
            }
            Log.debug(String.valueOf(this.getClass().getName()) + " .processModify()  keyID[" + keyID + "] :" + exm.toString());
            throw exm;
        }
        finally {
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex5) {}
        }
    }
    
    private void processdelete(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        Trx trx = null;
        Connection con = null;
        final String userID = ComStr.checkNull(req.getParameter("userID"));
        final String deleteReason = ComStr.checkNull(req.getParameter("deleteReason"));
        final SSO sso = new SSO(req, res);
        if (!sso.isLogin()) {
            return;
        }
        final UM_Auth_Check uac = new UM_Auth_Check(req, res);
        try {
            if (!uac.isSosokEquals(userID, uac.getID(), null)) {
                CommonMessage.printMessage("", "4", "Nếu không phải nhà thầu hoặc bên mời thầu thì không có quyền sử dụng", "", res, req);
                Log.debug(String.valueOf(this.getClass().getName()) + " userID[" + userID + "] , ID đăng nhập [" + uac.getID() + "] không có quyền sử dụng");
                return;
            }
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + " userID[" + userID + "]:" + ex.toString());
            return;
        }
        final UM_URB_UserE040p urc = new UM_URB_UserE040p();
        final String masterCode = uac.getMCode();
        final String IP = HttpUtility.getIP(req);
        final String gubun = uac.getGubun();
        final String id = uac.getID();
        final String churiGubun = "D";
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            con.setAutoCommit(false);
            urc.updateApprovalYN(con, masterCode, userID, "9");
            urc.updateModifyDate(userID, con);
            urc.insertHistory(userID, churiGubun, gubun, masterCode, id, IP, deleteReason, con);
            con.commit();
            con.setAutoCommit(true);
            CommonMessage.printMsg("", "5", "Đã xử lý.", "/RA/UM_URJ_UserE030l.jsp", res);
        }
        catch (Exception exm) {
            if (con != null) {
                try {
                    con.rollback();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                }
                catch (Exception ex3) {}
            }
            Log.debug(String.valueOf(this.getClass().getName()) + " .processdelete()  userID[" + userID + "] :" + exm.toString());
            throw exm;
        }
        finally {
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex5) {}
        }
    }
    
    private void processRecovery(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        Trx trx = null;
        Connection con = null;
        final String userID = ComStr.checkNull(req.getParameter("userID"));
        final SSO sso = new SSO(req, res);
        if (!sso.isLogin()) {
            return;
        }
        final UM_Auth_Check uac = new UM_Auth_Check(req, res);
        try {
            if (!uac.isSosokEquals(userID, uac.getID(), null)) {
                CommonMessage.printMessage("", "4", "Nếu không phải nhà thầu hoặc bên mời thầu thì không có quyền sử dụng", "", res, req);
                Log.debug(String.valueOf(this.getClass().getName()) + " userID[" + userID + "] , ID đăng nhập [" + uac.getID() + "] không có quyền sử dụng");
                return;
            }
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + " userID[" + userID + "]:" + ex.toString());
            return;
        }
        final UM_URB_UserE040p urc = new UM_URB_UserE040p();
        OneRowEntity grpEntity = null;
        String grp = null;
        final String masterCode = uac.getMCode();
        final String IP = HttpUtility.getIP(req);
        final String gubun = uac.getGubun();
        final String id = uac.getID();
        final String churiGubun = "R";
        final String deleteReason = "";
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            con.setAutoCommit(false);
            urc.updateApprovalYN(con, masterCode, userID, "2");
            urc.updateModifyDate(userID, con);
            grpEntity = urc.getusr_grp(userID, con);
            grp = ComStr.checkNull(grpEntity.getDataFromName("user_grp"));
            urc.insertHistory(userID, churiGubun, gubun, masterCode, id, IP, deleteReason, con);
            con.commit();
            con.setAutoCommit(true);
            CommonMessage.printMsg("", "5", "Đã xử lý.", "/RA/UM_URJ_UserE030l.jsp", res);
        }
        catch (Exception exm) {
            if (con != null) {
                try {
                    con.rollback();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                }
                catch (Exception ex3) {}
            }
            Log.debug(String.valueOf(this.getClass().getName()) + " .processRecovery()  userID[" + userID + "] :" + exm.toString());
            throw exm;
        }
        finally {
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex5) {}
        }
    }
}
