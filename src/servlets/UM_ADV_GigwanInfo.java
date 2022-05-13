// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import entity.UM_RAE_GovuA010b;
import java.sql.Connection;
import common.Log;
import java.sql.SQLException;
import common.util.CommonMessage;
import beans.UM_RAB_GovuA010p;
import common.Trx;
import LOGIN.UM_Auth_Check;
import entity.UM_ADJ_GovuA020b;
import common.util.CommUtil;
import beans.UM_ADB_GONGHISTORY;
import beans.UM_ADB_FUNDINFO;
import beans.UM_ADB_GONGINFO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_GigwanInfo extends HttpServlet
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
        final CommUtil com = new CommUtil();
        final String masterCode = com.retNull(req.getParameter("masterCode"));
        final String goNameFul = com.retNull(req.getParameter("goNameFull"));
        final String goNameShort = CommUtil.retSpace(req.getParameter("goNameShort"));
        final String goNameEn = CommUtil.retSpace(req.getParameter("goNameEn"));
        final String saupNo = CommUtil.retSpace(req.getParameter("saupNo"));
        final String orgDiv1 = CommUtil.retSpace(req.getParameter("orgDiv1"));
        final String orgDiv2 = CommUtil.retSpace(req.getParameter("orgDiv2"));
        final String orgDiv3 = CommUtil.retSpace(req.getParameter("orgDiv3"));
        final String law = "a";
        final String buConditon = CommUtil.retSpace(req.getParameter("buConditon"));
        final String buType = CommUtil.retSpace(req.getParameter("buType"));
        final String taskmaster = CommUtil.retSpace(req.getParameter("taskmaster"));
        final String masterPost = CommUtil.retSpace(req.getParameter("masterPost"));
        final String personTel = CommUtil.retSpace(req.getParameter("personTel"));
        final String personFax = CommUtil.retSpace(req.getParameter("personFax"));
        final String masterMail = CommUtil.retSpace(req.getParameter("masterMail"));
        final String bondholder = CommUtil.retSpace(req.getParameter("permitBranch"));
        final String sogwanGubun = CommUtil.retSpace(req.getParameter("insType"));
        final String zipCode = CommUtil.retSpace(req.getParameter("postNo"));
        final String address = CommUtil.retSpace(req.getParameter("ADDR"));
        final String extraAddress = CommUtil.retSpace(req.getParameter("address2"));
        final String telephone = CommUtil.retSpace(req.getParameter("comTel"));
        final String faxNumber = CommUtil.retSpace(req.getParameter("comFax"));
        final String homepage = CommUtil.retSpace(req.getParameter("homepage"));
        final String regionCode = CommUtil.retSpace(req.getParameter("regionCode"));
        final String mulpumPerson = CommUtil.retSpace(req.getParameter("creditName"));
        final String spOffice = com.retNull(req.getParameter("spOffice"));
        final String eCitation = com.retNull(req.getParameter("eCitation"));
        final String trDistance = CommUtil.retSpace(req.getParameter("trDistance"));
        final String province = com.retNull(req.getParameter("province"));
        final String branchOffi = com.retNull(req.getParameter("branchOffi"));
        final String permitCode = com.retNull(req.getParameter("permitCode"));
        final String personPhone = com.retNull(req.getParameter("personPhone"));
        final String mostUperCode = CommUtil.retSpace(req.getParameter("creditName"));
        final String mathCode = CommUtil.retSpace(req.getParameter("mathCode"));
        final String idCharge = CommUtil.retSpace(req.getParameter("idCharge"));
        final int groupNo = Integer.parseInt(req.getParameter("groupNo"));
        final int departmentNo = Integer.parseInt(req.getParameter("departmentNo"));
        final String isHaveUserId = CommUtil.retSpace(req.getParameter("isHaveUserId"));
        final String USER_CHRGR_NM = CommUtil.retSpace(req.getParameter("USER_CHRGR_NM"));
        final String USER_CHRGR_DEPART = CommUtil.retSpace(req.getParameter("USER_CHRGR_DEPART"));
        final String USER_CHRGR_PHONE_NO = CommUtil.retSpace(req.getParameter("USER_CHRGR_PHONE_NO"));
        final String USER_CHRGR_MOBILE = CommUtil.retSpace(req.getParameter("USER_CHRGR_MOBILE"));
        final String USER_CHRGR_FAX = CommUtil.retSpace(req.getParameter("USER_CHRGR_FAX"));
        final String USER_CHRGR_EMAIL = CommUtil.retSpace(req.getParameter("USER_CHRGR_EMAIL"));
        final String USER_ID = CommUtil.retSpace(req.getParameter("USER_ID"));
        String UserID = null;
        final UM_ADJ_GovuA020b caInfo = new UM_ADJ_GovuA020b();
        caInfo.setIDENT(CommUtil.retSpace(req.getParameter("caReprName")));
        caInfo.setIDENTJUMIN(CommUtil.retSpace(req.getParameter("caReprIdentNo")));
        String mCode = "";
        boolean isKigwanApprove = false;
        try {
            uac = new UM_Auth_Check(req, res);
            if (uac.getGubun().equals("k")) {
                isKigwanApprove = true;
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
            UserID = uac.getID();
            if (isKigwanApprove && uagInfo.isG2BSupplyCode(mCode) && goNameFul == null) {
                throw new Exception("Tên cơ quan đầy đủ là null.");
            }
            resource = new Trx(this);
            conn = resource.getConnection();
            conn.setAutoCommit(false);
            if (isKigwanApprove) {
                final UM_RAB_GovuA010p UM_RAB = new UM_RAB_GovuA010p();
                final UM_RAE_GovuA010b ettcode = UM_RAB.select_gmaster(masterCode);
                if (!ettcode.getgoNameFull().equalsIgnoreCase(goNameFul)) {
                    uagInfo.updateRecPubInstituMastFullName(masterCode, goNameFul, conn);
                    uagInfo.updateRecPubInstituCertFullName(masterCode, goNameFul, conn);
                }
                uagInfo.updateGongInfoUpdateDTOnly(masterCode, conn);
                gongHistory.insertGongHistory(UserID, masterCode, "Y", conn);
                gongHistory.saveCA(masterCode, conn);
                gongHistory.insertAcctHist(masterCode, conn);
                uagInfo.updateGongInfo(goNameShort, goNameEn, saupNo, orgDiv1, orgDiv2, orgDiv3, buConditon, buType, taskmaster, masterPost, personTel, personFax, masterMail, bondholder, sogwanGubun, zipCode, address, extraAddress, telephone, faxNumber, homepage, regionCode, mulpumPerson, mCode, spOffice, eCitation, province, permitCode, branchOffi, trDistance, goNameFul, mostUperCode, UserID, law, personPhone, idCharge, departmentNo, groupNo, conn);
                gongFund.updateFundCode(mCode, mathCode, conn);
                gongHistory.updateCA(masterCode, caInfo, conn);
                if ("1".equals(isHaveUserId)) {
                    gongHistory.insertUserHist_By_Admin(conn, USER_ID, UserID, masterCode);
                    gongHistory.insertAcctHist(masterCode, conn);
                    gongHistory.updateUSER(conn, USER_ID, USER_CHRGR_NM, USER_CHRGR_DEPART, USER_CHRGR_PHONE_NO, USER_CHRGR_MOBILE, USER_CHRGR_FAX, USER_CHRGR_EMAIL);
                }
            }
            else {
                final UM_RAB_GovuA010p UM_RAB = new UM_RAB_GovuA010p();
                final UM_RAE_GovuA010b ettcode = UM_RAB.select_gmaster(masterCode);
                if (!ettcode.getgoNameFull().equalsIgnoreCase(goNameFul)) {
                    uagInfo.updateRecPubInstituMastFullName(masterCode, goNameFul, conn);
                    uagInfo.updateRecPubInstituCertFullName(masterCode, goNameFul, conn);
                }
                gongHistory.insertGongHistory(goNameShort, goNameEn, saupNo, buConditon, buType, taskmaster, masterPost, personTel, personFax, masterMail, bondholder, sogwanGubun, zipCode, address, extraAddress, telephone, faxNumber, homepage, regionCode, mulpumPerson, mostUperCode, masterCode, UserID, personPhone, idCharge, departmentNo, groupNo, goNameFul, conn);
                uagInfo.updateGongInfo(goNameShort, goNameEn, saupNo, orgDiv1, orgDiv2, orgDiv3, buConditon, buType, taskmaster, masterPost, personTel, personFax, masterMail, bondholder, sogwanGubun, zipCode, address, extraAddress, telephone, faxNumber, homepage, regionCode, mulpumPerson, mCode, spOffice, eCitation, province, permitCode, branchOffi, trDistance, goNameFul, mostUperCode, UserID, law, personPhone, idCharge, departmentNo, groupNo, conn);
                gongHistory.saveCA(masterCode, caInfo, conn);
                gongHistory.insertAcctHist(masterCode, mathCode, conn);
                if ("1".equals(isHaveUserId)) {
                    gongHistory.insertUserHist(conn, USER_ID, USER_CHRGR_NM, USER_CHRGR_DEPART, USER_CHRGR_PHONE_NO, USER_CHRGR_MOBILE, USER_CHRGR_FAX, USER_CHRGR_EMAIL, masterCode, UserID);
                }
            }
            conn.commit();
            conn.setAutoCommit(true);
            CommonMessage.printMsg(null, "7", "Đã cập nhật sửa đổi thông tin bên mời thầu thành công.", null, res);
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
            Log.debug("UM_ADV_GigwanInfo.java Mã [" + mCode + "]:" + ex.toString());
            CommonMessage.printMsg(null, "Không cập nhật được thông tin.", ex.getMessage(), null, res);
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
