// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import common.Log;
import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import common.util.CommonMessage;
import java.sql.SQLException;
import beans.UM_ADB_GONGHISTORY;
import beans.UM_RAB_GovuA010p;
import common.Trx;
import LOGIN.UM_Auth_Check;
import entity.UM_RAE_GovuA010b;
import common.util.CommUtil;
import beans.UM_ADB_FUNDINFO;
import beans.UM_ADB_GONGINFO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADJ_GovrB020y extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        Trx resource = null;
        Connection conn = null;
        UM_Auth_Check uac = null;
        final UM_ADB_GONGINFO gongInfo = new UM_ADB_GONGINFO();
        final UM_ADB_FUNDINFO gongFund = new UM_ADB_FUNDINFO();
        final CommUtil com = new CommUtil();
        final String mastercode = CommUtil.retSpace(req.getParameter("g2bCode"));
        String userID = "";
        UM_RAE_GovuA010b ett = new UM_RAE_GovuA010b();
        try {
            uac = new UM_Auth_Check(req, res);
            userID = uac.getID();
            resource = new Trx(this);
            conn = resource.getConnection();
            conn.setAutoCommit(false);
            final UM_RAE_GovuA010b ettcode = new UM_RAB_GovuA010p().select_gmaster(mastercode);
            final UM_ADB_GONGHISTORY gongHistory = new UM_ADB_GONGHISTORY();
            ett = this.updateEtt(ett, req);
            gongInfo.updateGongInfo(conn, ett);
            gongHistory.insertFullHist(conn, ett.getg2bCode());
            gongFund.updateFundCode(ett, conn);
            gongHistory.insertAcctHist(ett.getg2bCode(), conn);
            conn.commit();
            res.sendRedirect("/RA/UM_RAJ_GovuA010y.jsp?g2bCode=" + mastercode);
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
    
    public UM_RAE_GovuA010b updateEtt(final UM_RAE_GovuA010b ett, final HttpServletRequest req) throws Exception {
        final CommUtil com = new CommUtil();
        final String goNameFull = CommUtil.retSpace(req.getParameter("goNameFull"));
        final String taskmaster = CommUtil.retSpace(req.getParameter("taskmaster"));
        final String masterPost = CommUtil.retSpace(req.getParameter("masterPost"));
        final String personTel = CommUtil.retSpace(req.getParameter("personTel"));
        final String personFax = CommUtil.retSpace(req.getParameter("personFax"));
        final String personPhone = CommUtil.retSpace(req.getParameter("personPhone"));
        final String masterMail = CommUtil.retSpace(req.getParameter("masterMail"));
        final String postNo = CommUtil.retSpace(req.getParameter("postNo"));
        final String ADDR = CommUtil.retSpace(req.getParameter("ADDR"));
        final String comTel = CommUtil.retSpace(req.getParameter("comTel"));
        final String comFax = CommUtil.retSpace(req.getParameter("comFax"));
        final String g2bCode = CommUtil.retSpace(req.getParameter("g2bCode"));
        final String goNameShort = CommUtil.retSpace(req.getParameter("goNameShort"));
        final String goNameEn = CommUtil.retSpace(req.getParameter("goNameEn"));
        final String saupNo = CommUtil.retSpace(req.getParameter("saupNo"));
        final String homepage = CommUtil.retSpace(req.getParameter("homepage"));
        final String insType = CommUtil.retSpace(req.getParameter("insType"));
        final String buConditon = CommUtil.retSpace(req.getParameter("buConditon"));
        final String buType = CommUtil.retSpace(req.getParameter("buType"));
        final String permitBranch = CommUtil.retSpace(req.getParameter("permitBranch"));
        final String idCharge = CommUtil.retSpace(req.getParameter("idCharge"));
        final String departmentNo = CommUtil.retSpace(req.getParameter("departmentNo"));
        final String groupNo = CommUtil.retSpace(req.getParameter("groupNo"));
        final String mathCode = CommUtil.retSpace(req.getParameter("mathCode"));
        try {
            ett.setbuType(buType);
            ett.setmasterFax(personFax);
            ett.setgoNameFull(goNameFull);
            ett.setmasterhp(personPhone);
            ett.setInsType(insType);
            ett.setsaupNo(saupNo);
            ett.settelNum(comTel);
            ett.setZIPCODE(postNo);
            ett.setmasterPost(masterPost);
            ett.settaskmaster(taskmaster);
            ett.setADDR(ADDR);
            ett.setg2bCode(g2bCode);
            ett.setgoNameEn(goNameEn);
            ett.setgoNameShort(goNameShort);
            ett.sethomepage(homepage);
            ett.setbuConditon(buConditon);
            ett.setfaxNum(comFax);
            ett.setmasterTel(personTel);
            ett.setDepId(Integer.parseInt(departmentNo));
            ett.setGroupNo(Integer.parseInt(groupNo));
            ett.setperOff(permitBranch);
            ett.setmasterMail(masterMail);
            ett.setmathCode(mathCode);
            ett.setcreditName(idCharge);
        }
        catch (Exception ex) {
            Log.errors(this, ex, "");
            throw ex;
        }
        return ett;
    }
}
