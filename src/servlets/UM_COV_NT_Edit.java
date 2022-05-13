// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.util.Set;
import java.sql.Connection;
import entity.ReportItem;
import entity.FinalItem;
import entity.ContractItem1;
import entity.StdClsItem;
import entity.UM_ADJ_GovuA020b;
import entity.UserItem;
import common.ComUtil;
import entity.ReprItem;
import common.Trx;
import dao.UM_RAB_ContractHist;
import dao.UM_RAB_RecFinanYear;
import dao.UM_RAB_Contract;
import dao.UM_RAB_MastBidAgent;
import dao.UM_RAB_MastEnterStdCls;
import dao.UM_RAB_MastRepr;
import dao.UM_RAB_MastSupplier;
import dao.UM_RAB_MastEnterStdClsHist;
import dao.UM_RAB_FinanYearHist;
import dao.UM_RAB_MastBidAgentHist;
import dao.UM_RAB_MastReprHist;
import dao.UM_RAB_MastSupplierHist;
import java.io.File;
import org.apache.commons.fileupload.FileUploadException;
import java.util.Iterator;
import java.util.Map;
import entity.DPItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import common.util.HttpUtility;
import common.Log;
import LOGIN.UM_Auth_Check;
import beans.UM_USER;
import g2b.sso.SSO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.servlet.http.HttpServlet;

public class UM_COV_NT_Edit extends HttpServlet
{
    private Hashtable requestTable;
    private ArrayList finanList;
    private List DPList;
    
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            this.setParamsFromrequestMultiTablepartForm(req, res);
            final SSO sso = new SSO(req, res);
            sso.checkLogin();
            if (!sso.isLogin()) {
                return;
            }
            final UM_Auth_Check uac = new UM_Auth_Check(req, res);
            this.updateProcess(uac);
            CommonMessage.printMessage("", "1", "Cập nhật thành công.", "/GO/UM_GOJ_ConiC031l.jsp", res, req);
        }
        catch (Exception exm) {
            Log.errors(this, exm, "");
            CommonMessage.printMessage("IP[" + HttpUtility.getIP(req) + "]", "", "Đã có lỗi xảy ra trong quá tình chỉnh sửa. Vui lòng thử lại.", null, res, req);
        }
    }
    
    public void setParamsFromrequestMultiTablepartForm(final HttpServletRequest req, final HttpServletResponse res) throws FileUploadException {
        this.requestTable = new Hashtable();
        if (req.getContentType() != null && req.getContentType().toLowerCase().indexOf("multipart/form-data") > -1) {
            final Map mapRequest = req.getParameterMap();
            Iterator iter = mapRequest.keySet().iterator();
            String name;
            for (;iter.hasNext();) {
            	name = (String) iter.next();
                final String[] item = (String[])mapRequest.get(name);
                this.requestTable.put(name, item[0]);
            }
            final List items = new ServletFileUpload((FileItemFactory)new DiskFileItemFactory()).parseRequest(req);
            final Iterator itr3 = items.iterator();
            boolean flagFinan = false;
            boolean flagDP = false;
            String year = "";
            this.DPList = new ArrayList();
            DPItem dpItem = null;
            String ceoYN = "";
            this.finanList = new ArrayList();
            while (itr3.hasNext()) {
                try {
                    final DiskFileItem item2 = (DiskFileItem)itr3.next();
                    if ("stdFinanYear".equals(item2.getFieldName())) {
                        year = item2.getString();
                        if ("".equals(year)) {
                            continue;
                        }
                        flagFinan = true;
                    }
                    else if ("reprIdentNo".equals(item2.getFieldName())) {
                        dpItem = new DPItem();
                        dpItem.setCeojuminNumber(item2.getString());
                        flagDP = true;
                    }
                    else if ("reprName".equals(item2.getFieldName()) && flagDP) {
                        dpItem.setCeoName(item2.getString());
                    }
                    else if ("reprEmail".equals(item2.getFieldName()) && flagDP) {
                        dpItem.setCeoMail(item2.getString());
                    }
                    else if ("reprMobile".equals(item2.getFieldName()) && flagDP) {
                        dpItem.setCeoPhone(item2.getString());
                        this.DPList.add(dpItem);
                        dpItem = null;
                        flagDP = false;
                    }
                    else if ("reprIsmain".equals(item2.getFieldName())) {
                        ceoYN = item2.getString();
                    }
                    else {
                        this.requestTable.put(item2.getFieldName(), item2.getString("UTF-8"));
                    }
                }
                catch (Exception ex) {}
            }
        }
    }
    
    private String getRequestValue(final Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
    
    private boolean uploadFile(final String path, final DiskFileItem dki) throws Exception {
        try {
            final File file = new File(path);
            dki.write(file);
            return true;
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    private void updateProcess(final UM_Auth_Check uac) throws Exception {
        Log.debug("Vao day");
        final UM_RAB_MastSupplierHist mastSupplierHist = new UM_RAB_MastSupplierHist();
        final UM_RAB_MastReprHist mastReprHist = new UM_RAB_MastReprHist();
        final UM_RAB_MastBidAgentHist agentHist = new UM_RAB_MastBidAgentHist();
        final UM_RAB_FinanYearHist finanYearHist = new UM_RAB_FinanYearHist();
        final UM_RAB_MastEnterStdClsHist mastEnterStdClsDAOHist = new UM_RAB_MastEnterStdClsHist();
        final UM_RAB_MastSupplier mastSupplierEnterMastDao = new UM_RAB_MastSupplier();
        final UM_RAB_MastRepr mastReprDao = new UM_RAB_MastRepr();
        final UM_RAB_MastEnterStdCls mastEnterStdClsDAO = new UM_RAB_MastEnterStdCls();
        final UM_RAB_MastBidAgent mastBidAgentDAO = new UM_RAB_MastBidAgent();
        final UM_RAB_Contract contractDAO = new UM_RAB_Contract();
        final UM_RAB_RecFinanYear recFinanYearDAO = new UM_RAB_RecFinanYear();
        final UM_RAB_ContractHist contractDAOHist = new UM_RAB_ContractHist();
        final UM_USER userDAOHist = new UM_USER();
        Trx tr = null;
        Connection conn = null;
        try {
            tr = new Trx(this, "usemn");
            conn = tr.getConnection();
            conn.setAutoCommit(false);
            final Set keys = this.requestTable.keySet();
            final Iterator itr = keys.iterator();
            String temp = "";
            while (itr.hasNext()) {
                final String keyName = (String)itr.next();
                temp = String.valueOf(temp) + "_" + keyName + "=" + this.requestTable.get((Object)keyName);
            }
            final String bizRegNo = this.getRequestValue(this.requestTable.get((Object)"bizRegNo"));
            final String bizName = this.getRequestValue(this.requestTable.get((Object)"bizName"));
            final String bizEnName = this.getRequestValue(this.requestTable.get((Object)"bizEnName"));
            final String commDate = this.getRequestValue(this.requestTable.get((Object)"commDate"));
            final String addr = this.getRequestValue(this.requestTable.get((Object)"addr"));
            final String phoneNo = this.getRequestValue(this.requestTable.get((Object)"phoneNo"));
            final String homePage = this.getRequestValue(this.requestTable.get((Object)"homePage"));
            String capital = this.getRequestValue(this.requestTable.get((Object)"capital"));
            String emplCount = this.getRequestValue(this.requestTable.get((Object)"emplCount"));
            final String fax = this.getRequestValue(this.requestTable.get((Object)"fax"));
            final String bizCls = this.getRequestValue(this.requestTable.get((Object)"bizCls"));
            final String national = this.getRequestValue(this.requestTable.get((Object)"national"));
            final String province = this.getRequestValue(this.requestTable.get((Object)"area"));
            final String supplierCls = this.getRequestValue(this.requestTable.get((Object)"supplierCls"));
            Log.debug("supplierCls: " + supplierCls);
            final String docNo = this.getRequestValue(this.requestTable.get((Object)"docNo"));
            final String reprCountStr = this.getRequestValue(this.requestTable.get((Object)"reprCount"));
            final String prevRecvNo = this.getRequestValue(this.requestTable.get((Object)"prevRecvNo"));
            final String perBranch = this.getRequestValue(this.requestTable.get((Object)"permitBranch"));
            int reprCount = 0;
            if (reprCountStr != null && reprCountStr.length() != 0) {
                reprCount = Integer.parseInt(reprCountStr);
            }
            capital = capital.replaceAll("\\.", "");
            emplCount = emplCount.replaceAll("\\.", "");
            final List reprList = new ArrayList();
            ReprItem reprItem = null;
            String reprName = "";
            String reprIdentNo = "";
            String reprMobile = "";
            String reprIsmain = "";
            String reprEmail = "";
            for (int i = 0; i < reprCount; ++i) {
                reprName = this.getRequestValue(this.requestTable.get((Object)("reprName[" + i + "]")));
                reprIdentNo = this.getRequestValue(this.requestTable.get((Object)("reprIdentNo[" + i + "]")));
                reprMobile = this.getRequestValue(this.requestTable.get((Object)("reprMobile[" + i + "]")));
                reprIsmain = this.getRequestValue(this.requestTable.get((Object)("reprIsmain[" + i + "]")));
                reprEmail = this.getRequestValue(this.requestTable.get((Object)("reprEmail[" + i + "]")));
                reprItem = new ReprItem(reprName, reprIdentNo, reprMobile, reprEmail, reprIsmain);
                reprList.add(reprItem);
            }
            String employeeNo = ComUtil.replace(this.getRequestValue(this.requestTable.get((Object)"employeeNo")), ",", "");
            if (employeeNo.equals("")) {
                employeeNo = "0";
            }
            final String stdClsCountStr = this.getRequestValue(this.requestTable.get((Object)"stdClsCount"));
            int stdClsCount = 0;
            if (stdClsCountStr != null && stdClsCountStr.length() != 0) {
                stdClsCount = Integer.parseInt(stdClsCountStr);
            }
            final List stdClsList = new ArrayList();
            StdClsItem stdClsItem = null;
            String stdClsCode = "";
            String mastStdClsYn = "";
            String stdClsNo = "";
            String stdClsName = "";
            String stdClsIssuedDate = "";
            String constAbilEvalAmt = "";
            String evalStdYear = "";
            String stdClsExpiryDate = "";
            for (int j = 0; j < stdClsCount; ++j) {
                stdClsCode = this.getRequestValue(this.requestTable.get((Object)("stdClsCode[" + j + "]")));
                stdClsName = this.getRequestValue(this.requestTable.get((Object)("stdClsName[" + j + "]")));
                mastStdClsYn = this.getRequestValue(this.requestTable.get((Object)("mastStdClsYn[" + j + "]")));
                stdClsNo = this.getRequestValue(this.requestTable.get((Object)("stdClsNo[" + j + "]")));
                stdClsIssuedDate = this.getRequestValue(this.requestTable.get((Object)("stdClsIssuedDate[" + j + "]")));
                constAbilEvalAmt = this.getRequestValue(this.requestTable.get((Object)("constAbilEvalAmt[" + j + "]")));
                evalStdYear = this.getRequestValue(this.requestTable.get((Object)("evalStdYear[" + j + "]")));
                stdClsExpiryDate = this.getRequestValue(this.requestTable.get((Object)("stdClsExpiryDate[" + j + "]")));
                stdClsItem = new StdClsItem(stdClsCode, stdClsNo, stdClsIssuedDate, constAbilEvalAmt, evalStdYear, mastStdClsYn, stdClsExpiryDate, "", stdClsName);
                stdClsList.add(stdClsItem);
            }
            final String ctrCountStr = this.getRequestValue(this.requestTable.get((Object)"ctrCount"));
            int ctrCount = 0;
            final List ctrList = new ArrayList();
            ContractItem1 ctrItem = null;
            if (!"".equals(ctrCountStr)) {
                ctrCount = Integer.parseInt(ctrCountStr);
            }
            String ctrNo = "";
            String ctrName = "";
            String dateSign = "";
            String ctrValue = "";
            String prince = "";
            String percent = "";
            String dateComplete = "";
            String projectName = "";
            String contact = "";
            for (int k = 0; k < ctrCount; ++k) {
                ctrNo = this.getRequestValue(this.requestTable.get((Object)("ctrNo[" + k + "]")));
                ctrName = this.getRequestValue(this.requestTable.get((Object)("ctrName[" + k + "]")));
                dateSign = this.getRequestValue(this.requestTable.get((Object)("stdDateSign[" + k + "]")));
                ctrValue = this.getRequestValue(this.requestTable.get((Object)("ctrValue[" + k + "]")));
                prince = this.getRequestValue(this.requestTable.get((Object)("stdPriceValue[" + k + "]")));
                percent = this.getRequestValue(this.requestTable.get((Object)("stdPerCtrValue[" + k + "]")));
                dateComplete = this.getRequestValue(this.requestTable.get((Object)("stdDateComplete[" + k + "]")));
                projectName = this.getRequestValue(this.requestTable.get((Object)("stdProjectName[" + k + "]")));
                contact = this.getRequestValue(this.requestTable.get((Object)("stdContactValue[" + k + "]")));
                ctrItem = new ContractItem1(ctrNo, ctrName, dateSign, ctrValue, prince, percent, dateComplete, projectName, contact);
                ctrList.add(ctrItem);
            }
            final String ctrFinanStr = this.getRequestValue(this.requestTable.get((Object)"ctrFinan"));
            int ctrFinan = 0;
            final List finanList = new ArrayList();
            FinalItem finanItem = null;
            if (!"".equals(ctrFinanStr)) {
                ctrFinan = Integer.parseInt(ctrFinanStr);
            }
            String stdYEAR = "";
            String stdASSETS_TOTAL = "";
            String stdDEBT_TOTAL = "";
            String stdASSET_VALUE = "";
            String stdSHORT_ASSETS = "";
            String stdSHORT_DEBT = "";
            String stdCAPITAL = "";
            for (int l = 0; l < ctrFinan; ++l) {
                stdYEAR = this.getRequestValue(this.requestTable.get((Object)("stdYEAR[" + l + "]")));
                stdASSETS_TOTAL = this.getRequestValue(this.requestTable.get((Object)("stdASSETS_TOTAL[" + l + "]")));
                stdDEBT_TOTAL = this.getRequestValue(this.requestTable.get((Object)("stdDEBT_TOTAL[" + l + "]")));
                stdASSET_VALUE = this.getRequestValue(this.requestTable.get((Object)("stdASSET_VALUE[" + l + "]")));
                stdSHORT_ASSETS = this.getRequestValue(this.requestTable.get((Object)("stdSHORT_ASSETS[" + l + "]")));
                stdSHORT_DEBT = this.getRequestValue(this.requestTable.get((Object)("stdSHORT_DEBT[" + l + "]")));
                stdCAPITAL = this.getRequestValue(this.requestTable.get((Object)("stdCAPITAL[" + l + "]")));
                finanItem = new FinalItem(stdYEAR, stdASSETS_TOTAL, stdDEBT_TOTAL, stdASSET_VALUE, stdSHORT_ASSETS, stdSHORT_DEBT, stdCAPITAL);
                finanList.add(finanItem);
            }
            final String ctrReportStr = this.getRequestValue(this.requestTable.get((Object)"ctrReport"));
            int ctrReport = 0;
            final List reportList = new ArrayList();
            ReportItem reportItem = null;
            if (!"".equals(ctrReportStr)) {
                ctrReport = Integer.parseInt(ctrReportStr);
            }
            String stdYEARR = "";
            String stdREVENUE_TOTAL = "";
            String stdAVERAGE_REVENUE = "";
            String stdPROFIT_BEFORE = "";
            String stdPROFIT_AFTER = "";
            for (int m = 0; m < ctrReport; ++m) {
                stdYEARR = this.getRequestValue(this.requestTable.get((Object)("stdYEAR[" + m + "]")));
                stdREVENUE_TOTAL = this.getRequestValue(this.requestTable.get((Object)("stdREVENUE_TOTAL[" + m + "]")));
                stdAVERAGE_REVENUE = this.getRequestValue(this.requestTable.get((Object)("stdAVERAGE_REVENUE[" + m + "]")));
                stdPROFIT_BEFORE = this.getRequestValue(this.requestTable.get((Object)("stdPROFIT_BEFORE[" + m + "]")));
                stdPROFIT_AFTER = this.getRequestValue(this.requestTable.get((Object)("stdPROFIT_AFTER[" + m + "]")));
                reportItem = new ReportItem(stdYEARR, stdREVENUE_TOTAL, stdAVERAGE_REVENUE, stdPROFIT_BEFORE, stdPROFIT_AFTER);
                reportList.add(reportItem);
            }
            final String identCts = this.getRequestValue(this.requestTable.get((Object)"caReprName"));
            final String identNoCts = this.getRequestValue(this.requestTable.get((Object)"caReprIdentNo"));
            final String bizAgentName = this.getRequestValue(this.requestTable.get((Object)"bizAgentName"));
            final String bizDepart = this.getRequestValue(this.requestTable.get((Object)"bizDepart"));
            final String bizPhoneNo = this.getRequestValue(this.requestTable.get((Object)"bizPhoneNo"));
            final String bizFax = this.getRequestValue(this.requestTable.get((Object)"bizFax"));
            final String bizMobile = this.getRequestValue(this.requestTable.get((Object)"bizMobile"));
            final String bizAgentEmail = this.getRequestValue(this.requestTable.get((Object)"bizAgentEmail"));
            final String bizPosition = this.getRequestValue(this.requestTable.get((Object)"bizPosition"));
            final String bizIdentNo = this.getRequestValue(this.requestTable.get((Object)"bizIdentNo"));
            mastEnterStdClsDAO.deleteAll(bizRegNo, conn);
            mastEnterStdClsDAO.save(bizRegNo, stdClsList, conn);
            mastReprHist.save(bizRegNo, conn);
            mastSupplierHist.save(bizRegNo, conn);
            mastEnterStdClsDAOHist.save(bizRegNo, conn);
            mastSupplierHist.saveCA(bizRegNo, conn);
            //ThachPN-20160426 - Thay doi nguoi dai dien chung thu so
            UM_ADJ_GovuA020b caInfo = new UM_ADJ_GovuA020b();
            caInfo.setIDENT(identCts);
            caInfo.setIDENTJUMIN(identNoCts);
            mastSupplierHist.updateCA(bizRegNo, caInfo, conn);
            agentHist.save(bizRegNo, conn);
            final String UserID = uac.getID();
            //ThachPN-20160516 - Thay doi phan loai cong ty
            //mastSupplierEnterMastDao.modifyWithManagerID(bizRegNo, bizName, bizEnName, commDate, addr, phoneNo, emplCount, fax, national, homePage, capital, supplierCls, conn, UserID);
            mastSupplierEnterMastDao.modifyWithManagerID2(bizRegNo, bizName, bizEnName, commDate, addr, phoneNo, emplCount, fax, national, homePage, capital, supplierCls, conn, UserID, bizCls, province);
       
            mastReprDao.deleteAll(bizRegNo, conn);
            mastReprDao.save(bizRegNo, reprList, conn);
            mastBidAgentDAO.deleteAll(bizRegNo, conn);
            mastBidAgentDAO.save(bizIdentNo, bizRegNo, bizAgentName, bizDepart, bizPosition, bizPhoneNo, bizAgentEmail, bizMobile, bizFax, conn);
            contractDAOHist.saveHistContract(bizRegNo, "", "", conn);
            contractDAO.delete(bizRegNo, conn);
            contractDAO.insertContract(bizRegNo, ctrList, conn);
            recFinanYearDAO.deleteBalance(bizRegNo, conn);
            recFinanYearDAO.insertBalance(bizRegNo, finanList, conn);
            recFinanYearDAO.deleteReport(bizRegNo, conn);
            recFinanYearDAO.insertReport(bizRegNo, reportList, conn);
			
			final String userName = this.getRequestValue(this.requestTable.get((Object)"userName"));
            Log.debug("123 " +userName);
            if(userName.length()>0){
	            final String userDepart = this.getRequestValue(this.requestTable.get((Object)"userDepart"));
	            final String userPhoneNo = this.getRequestValue(this.requestTable.get((Object)"userPhoneNo"));
	            final String userFax = this.getRequestValue(this.requestTable.get((Object)"userFax"));
	            final String userMobile = this.getRequestValue(this.requestTable.get((Object)"userMobile"));
	            final String userEmail = this.getRequestValue(this.requestTable.get((Object)"userEmail"));
	            final String userId = this.getRequestValue(this.requestTable.get((Object)"userId"));
	            final UserItem[] userItem = userDAOHist.getHist(userId, conn);
	         //   userDAOHist.saveHist(bizRegNo, userItem, conn);
	            userDAOHist.updateIDInfo(userId, userName, userDepart, userEmail, userPhoneNo, userFax, userMobile, conn);
            }
            conn.commit();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            if (conn != null) {
                try {
                    conn.rollback();
                }
                catch (Exception ex) {}
            }
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                }
                catch (Exception ex2) {}
            }
            throw e;
        }
        finally {
            if (conn != null) {
                try {
                    tr.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (conn != null) {
            try {
                tr.close();
            }
            catch (Exception ex4) {}
        }
    }
}
