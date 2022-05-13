// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import entity.UM_ADJ_GovuA020b;
import common.CommEntity;
import entity.AgentItem;
import entity.ReprItem;
import entity.FinanYearItem;
import entity.ContractItem;
import common.OneRowEntity;
import java.sql.Connection;
import java.sql.SQLException;
import common.util.CommonMessage;
import common.Log;
import common.Trx;
import LOGIN.UM_Auth_Check;
import common.util.CommUtil;
import dao.UM_RAB_FinanYearHist;
import dao.UM_RAB_ContractHist;
import dao.UM_RAB_MastBidAgentHist;
import dao.UM_RAB_MastReprHist;
import dao.UM_RAB_MastSupplierHist;
import beans.UM_CEO;
import dao.UM_RAB_MastSupplier;
import beans.UM_AGENT;
import dao.UM_RAB_FinanYear;
import dao.UM_RAB_Contract;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_SupplierApprove extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final UM_RAB_Contract contractDAO = new UM_RAB_Contract();
        final UM_RAB_FinanYear finanDao = new UM_RAB_FinanYear();
        final UM_AGENT umagent = new UM_AGENT();
        final UM_RAB_MastSupplier master = new UM_RAB_MastSupplier();
        final UM_CEO umceo = new UM_CEO();
        final UM_RAB_MastSupplierHist mastSupplierHist = new UM_RAB_MastSupplierHist();
        final UM_RAB_MastReprHist mastReprHist = new UM_RAB_MastReprHist();
        final UM_RAB_MastBidAgentHist agentHist = new UM_RAB_MastBidAgentHist();
        final UM_RAB_ContractHist contractHist = new UM_RAB_ContractHist();
        final UM_RAB_FinanYearHist finanYearHist = new UM_RAB_FinanYearHist();
        Trx resource = null;
        Connection conn = null;
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        UM_Auth_Check uac = null;
        final CommUtil com = new CommUtil();
        final String masterCode = com.retNull(req.getParameter("saupNo"));
        String UserID = null;
        OneRowEntity masterData = null;
        ContractItem[] contracData = null;
        FinanYearItem[] finaData = null;
        ReprItem[] reprData = null;
        AgentItem[] agentData = null;
        CommEntity[] userEntity = null;
        UM_ADJ_GovuA020b caInfo = null;
        try {
            uac = new UM_Auth_Check(req, res);
            UserID = uac.getID();
            resource = new Trx(this);
            conn = resource.getConnection();
            masterData = master.selectUpdate(masterCode, conn);
            finaData = finanDao.selectUpdate(masterCode, conn);
            contracData = contractDAO.selectUpdate(masterCode, conn);
            reprData = umceo.selectUpdate(masterCode, conn);
            agentData = umagent.selectUpdate(masterCode, conn);
            userEntity = mastSupplierHist.getListUser_NM(masterCode);
            caInfo = mastSupplierHist.select_CTSInfo(masterCode);
            Log.debug("Name: " + caInfo.getIDENT());
            Log.debug("No: " + caInfo.getIDENTJUMIN());
            conn.setAutoCommit(false);
            mastReprHist.deleteNewest(masterCode, conn);
            agentHist.deleteNewest(masterCode, conn);
            contractHist.deleteNewest(masterCode, conn);
            finanYearHist.deleteNewest(masterCode, conn);
            mastSupplierHist.deleteCANewest(masterCode, conn);
            mastSupplierHist.deleteUserNewest(masterCode, conn);
            mastSupplierHist.deleteNewest(masterCode, conn);
            mastSupplierHist.save(masterCode, true, UserID, conn);
            mastSupplierHist.saveCA(masterCode, conn);
            mastSupplierHist.insertUserHist(masterCode, conn);
            mastReprHist.save(masterCode, conn);
            agentHist.save(masterCode, conn);
            contractHist.save(masterCode, conn);
            finanYearHist.save(masterCode, conn);
            master.MasterUpdate(masterCode, masterData.data[0], masterData.data[1], masterData.data[2], masterData.data[3], masterData.data[4], masterData.data[5], masterData.data[6], conn);
            mastSupplierHist.updateUSER(userEntity, conn);
            mastSupplierHist.updateCA(masterCode, caInfo, conn);
            contractDAO.delete(masterCode, conn);
            contractDAO.save(masterCode, contracData, conn);
            finanDao.save(masterCode, finaData, conn);
            umagent.delete(masterCode, conn);
            umagent.save(masterCode, agentData, conn);
            umceo.delete(masterCode, conn);
            umceo.save(masterCode, reprData, conn);
            conn.commit();
            CommonMessage.printMsg(null, "1", "Đã phê duyệt thành công.", "/RA/UM_ADJ_GovrQ021l.jsp", res);
        }
        catch (Exception ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            }
            catch (SQLException ex2) {}
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
            catch (Exception ex3) {}
        }
        try {
            if (conn != null) {
                resource.close();
            }
        }
        catch (Exception ex4) {}
    }
    
    private void test(final OneRowEntity masterData, final ContractItem[] contracData, final FinanYearItem[] finaData, final ReprItem[] reprData, final AgentItem[] agentData, final CommEntity[] userEntity) {
        Log.debug("------------------------TEST-------------------------");
        Log.debug("-----test Contract-----");
        for (int i = 0; i < contracData.length; ++i) {
            Log.debug("-------   " + i);
            Log.debug(contracData[i].getNo());
            Log.debug(contracData[i].getName());
            Log.debug(contracData[i].getPartnerName());
            Log.debug(contracData[i].getValue());
        }
        Log.debug("-----test Fian-----");
        for (int i = 0; i < finaData.length; ++i) {
            Log.debug("-------   " + i);
            Log.debug(finaData[i].getYear());
            Log.debug(finaData[i].getAttachFile());
            Log.debug(finaData[i].getOrgFile());
        }
        Log.debug("-----test repr-----");
        for (int i = 0; i < reprData.length; ++i) {
            Log.debug("-------   " + i);
            Log.debug(reprData[i].getReprIdentNo());
            Log.debug(reprData[i].getReprName());
            Log.debug(reprData[i].getReprEmail());
            Log.debug(reprData[i].getReprMobile());
            Log.debug(reprData[i].getReprIsmain());
        }
        Log.debug("-----test agent-----");
        for (int i = 0; i < agentData.length; ++i) {
            Log.debug("-------   " + i);
            Log.debug(agentData[i].getIdentNo());
            Log.debug(agentData[i].getName());
            Log.debug(agentData[i].getEmail());
            Log.debug(agentData[i].getFax());
            Log.debug(agentData[i].getMobile());
            Log.debug(agentData[i].getDepart());
            Log.debug(agentData[i].getPosition());
            Log.debug(agentData[i].getPhoneNo());
        }
        Log.debug("-----test masterData-----");
        for (int i = 0; i < masterData.data.length; ++i) {
            Log.debug(masterData.data[i]);
        }
        Log.debug("-----test user-----");
        for (int i = 0; i < agentData.length; ++i) {
            Log.debug("-------   " + i);
            Log.debug(userEntity[i].data[0]);
            Log.debug(userEntity[i].data[1]);
            Log.debug(userEntity[i].data[2]);
            Log.debug(userEntity[i].data[3]);
            Log.debug(userEntity[i].data[4]);
            Log.debug(userEntity[i].data[5]);
            Log.debug(userEntity[i].data[6]);
        }
    }
}
