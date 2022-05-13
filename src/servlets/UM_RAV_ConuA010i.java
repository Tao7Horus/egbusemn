// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import LOGIN.UM_Auth_Check;
import Mail.MailServer;
import beans.UM_URB_CERT010;
import common.Log;
import common.Trx;
import common.util.CommUtil;
import dao.UM_RAB_Contract;
import dao.UM_RAB_ContractHist;
import dao.UM_RAB_FinanYear;
import dao.UM_RAB_FinanYearHist;
import dao.UM_RAB_MastBidAgent;
import dao.UM_RAB_MastBidAgentHist;
import dao.UM_RAB_MastEnterStdCls;
import dao.UM_RAB_MastEnterStdCls2;
import dao.UM_RAB_MastEnterStdClsHist;
import dao.UM_RAB_MastRepr;
import dao.UM_RAB_MastReprHist;
import dao.UM_RAB_MastSupplier;
import dao.UM_RAB_MastSupplierHist;
import dao.UM_RAB_RecBidAgent;
import dao.UM_RAB_RecContract;
import dao.UM_RAB_RecEnterStdCls;
import dao.UM_RAB_RecFinanYear;
import dao.UM_RAB_RecPubInstituCert;
import dao.UM_RAB_RecRepr;
import dao.UM_RAB_RecSupplier;
import entity.ContractItem1;
import entity.FinalItem;
import entity.FinanYearItem;
import entity.ReportItem;
import entity.ReprItem;
import entity.StdClsItem;
import g2b.sso.SSO;
import secu.lib.IssueCertKICA;
import secu.lib.Secu;

public class UM_RAV_ConuA010i extends HttpServlet
{
    private static final String DETINATION_FILE_PATH = "/e-doc/USEMN/FINAN/";
    private static final String DETINATION_FILE_PATH_TMP = "/e-doc/USEMN/tmp/FINAN/";
    private static final String LICENSE_FILE_PATH = "/home/weblogic/bea/weblogic81/server/lib/DEXTUploadJ";
    private static final long serialVersionUID = -8709532016850972377L;
    private List finanList;
    private Hashtable requestTable;
    
    public void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        Trx tr = null;
        Connection conn = null;
        try {
            request.setCharacterEncoding("UTF-8");
            this.setParamsFromrequestMultiTablepartForm(request, response);
            if (this.requestTable == null) {
                Log.debug("requestTablepart is null");
                return;
            }
        }
        catch (FileUploadException e3) {
            Log.errors(this, (Exception)e3, "requestTablepart error");
        }
        final String checkBiz = CommUtil.retSpace(request.getParameter("checkBizNo"));
        if ("1".equals(checkBiz)) {
            final UM_RAB_RecSupplier RecSupplier = new UM_RAB_RecSupplier();
            final String bizRegNo = CommUtil.retSpace(request.getParameter("bizRegNo"));
            try {
                tr = new Trx(this);
                conn = tr.getConnection();
                final int maxCount = RecSupplier.maxCount(bizRegNo, conn);
                if (maxCount == 0) {
                    response.getWriter().write("Y");
                }
                else {
                    response.getWriter().write("N");
                }
            }
            catch (Exception ex2) {
            	
            }
            finally {
            	if (conn != null) {
            		try {
            			tr.close();
            		} catch (Exception e) {
            			
            		}
            	}
            }
        }
        else {
            final String step = this.getRequestValue(this.requestTable.get((Object)"step"));
            final String mode = this.getRequestValue(this.requestTable.get((Object)"mode"));
            final String key = this.getRequestValue(this.requestTable.get((Object)"key"));
            final String test_option_yn = this.getRequestValue(this.requestTable.get((Object)"test_option_yn"));
            String bizRegNo2 = this.getRequestValue(this.requestTable.get((Object)"bizRegNo"));
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
            final String docNo = this.getRequestValue(this.requestTable.get((Object)"docNo"));
            final String cert_institu = this.getRequestValue(this.requestTable.get((Object)"choiceCA"));
            final String reprCountStr = this.getRequestValue(this.requestTable.get((Object)"reprCount"));
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
                stdYEAR = this.getRequestValue(this.requestTable.get((Object)("stdYEAR2[" + l + "]")));
                Log.debug("nam: " + stdYEAR);
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
            final String bizAgentName = this.getRequestValue(this.requestTable.get((Object)"bizAgentName"));
            final String bizIdentNo = this.getRequestValue(this.requestTable.get((Object)"bizIdentNo"));
            final String bizPosition = this.getRequestValue(this.requestTable.get((Object)"bizPosition"));
            final String bizDepart = this.getRequestValue(this.requestTable.get((Object)"bizDepart"));
            final String bizAgentEmail = this.getRequestValue(this.requestTable.get((Object)"bizAgentEmail"));
            final String bizPhoneNo = this.getRequestValue(this.requestTable.get((Object)"bizPhoneNo"));
            final String bizMobile = this.getRequestValue(this.requestTable.get((Object)"bizMobile"));
            final String bizFax = this.getRequestValue(this.requestTable.get((Object)"bizFax"));
            final String caReprName = this.getRequestValue(this.requestTable.get((Object)"caReprName"));
            final String caReprIdentNo = this.getRequestValue(this.requestTable.get((Object)"caReprIdentNo"));
            final String caUserId = this.getRequestValue(this.requestTable.get((Object)"caUserId"));
            final String perBranch = this.getRequestValue(this.requestTable.get((Object)"permitBranch"));
            String recvNo = null;
            String refername = null;
            String refercode = null;
            
            PreparedStatement pstmt = null;
            PreparedStatement pstmt2 = null;
            PreparedStatement pstmt3 = null;
            
            try {
                tr = new Trx(this);
                conn = tr.getConnection();
                final UM_RAB_RecPubInstituCert recPubInstituCertDAO = new UM_RAB_RecPubInstituCert();
                final UM_RAB_RecSupplier recSupplierEnterMastDao = new UM_RAB_RecSupplier();
                final UM_RAB_RecRepr recReprDao = new UM_RAB_RecRepr();
                final UM_RAB_RecEnterStdCls recEnterStdClsDAO = new UM_RAB_RecEnterStdCls();
                final UM_RAB_RecBidAgent recBidAgentDAO = new UM_RAB_RecBidAgent();
                final UM_RAB_RecContract recContractDAO = new UM_RAB_RecContract();
                final UM_RAB_RecFinanYear recFinanYearDAO = new UM_RAB_RecFinanYear();
                recvNo = recSupplierEnterMastDao.getChodalReceptionNumber(conn);
                final String appReqCode = this.getRequestValue(this.requestTable.get((Object)"appReqCode"));
				String prevAppReqNoToEmail = "";
                if ("0".equals(step)) {
                    if ("1".equals(mode)) {
                        final String prevRecvNo = this.getRequestValue(this.requestTable.get((Object)"prevRecvNo"));
                        final String prevAppReqNo = this.getRequestValue(this.requestTable.get((Object)"prevAppReqNo"));
						prevAppReqNoToEmail = prevAppReqNo;
                        conn.setAutoCommit(false);
                        recSupplierEnterMastDao.delete(key, conn);
                        recSupplierEnterMastDao.save(bizRegNo2, bizName, bizEnName, commDate, addr, phoneNo, emplCount, fax, national, province, homePage, capital, supplierCls, bizCls, prevRecvNo, perBranch, docNo, conn);
                        recEnterStdClsDAO.delete(key, conn);
                        recEnterStdClsDAO.save(bizRegNo2, stdClsList, conn);
                        recReprDao.delete(key, conn);
                        recReprDao.save(bizRegNo2, reprList, conn);
                        recBidAgentDAO.delete(key, conn);
                        recBidAgentDAO.save(bizIdentNo, bizRegNo2, bizAgentName, bizDepart, bizPosition, bizPhoneNo, bizAgentEmail, bizMobile, bizFax, conn);
                        Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i mode = Edit");
                        recContractDAO.delete(bizRegNo2, conn);
                        recContractDAO.insertContract(bizRegNo2, ctrList, conn);
                        recFinanYearDAO.deleteBalance(bizRegNo2, conn);
                        recFinanYearDAO.insertBalance(bizRegNo2, finanList, conn);
                        recFinanYearDAO.deleteReport(bizRegNo2, conn);
                        recFinanYearDAO.insertReport(bizRegNo2, reportList, conn);
                        String sql = "delete from UM_EDOC_STATE where BIZ_REG_NO=?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, key);
                        if (pstmt.executeUpdate() != 1) {
                            throw new Exception("[Lỗi đăng ký]");
                        }
                        pstmt.clearParameters();                        
                        sql = "insert into UM_EDOC_STATE (BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, PROCESS_ST) values (?, '1', SYSDATE, ?, ?, '0')";
                        pstmt2 = conn.prepareStatement(sql);
                        pstmt2.setString(1, bizRegNo2);
                        pstmt2.setString(2, bizName);
                        pstmt2.setString(3, reprName);
                        if (pstmt2.executeUpdate() != 1) {
                            throw new Exception("[Lỗi đăng ký]");
                        }
                        recPubInstituCertDAO.modify3(bizRegNo2, prevRecvNo, prevAppReqNo, "N", bizName, bizEnName, caReprName, caReprIdentNo, caUserId, bizDepart, bizIdentNo, bizAgentName, bizPhoneNo, bizMobile, bizAgentEmail, bizFax, addr, homePage, conn);
                        final SSO sso = new SSO(request, response);
                        if (sso.isLogin()) {
                            Log.debug("Quan ly nha thau chinh sua");
                            if (sso.getGubun().equals("a")) {
                                this.updateHistory(bizRegNo2, bizIdentNo, conn, request, response);
                            }
                        }
                        response.getWriter().write(prevRecvNo);
                    }
                    else {
                        final Date now = new Date();
                        final Calendar cal = Calendar.getInstance();
                        cal.setTime(now);
                        final int year = cal.get(1);
                        final int month = cal.get(2) + 1;
                        final int day = cal.get(5);
                        final String fileDbName = "";
                        conn.setAutoCommit(false);
                        recSupplierEnterMastDao.save(bizRegNo2, bizName, bizEnName, commDate, addr, phoneNo, emplCount, fax, national, province, homePage, capital, supplierCls, bizCls, recvNo, perBranch, docNo, conn);
                        recBidAgentDAO.save(bizIdentNo, bizRegNo2, bizAgentName, bizDepart, bizPosition, bizPhoneNo, bizAgentEmail, bizMobile, bizFax, conn);
                        recReprDao.save(bizRegNo2, reprList, conn);
                        recEnterStdClsDAO.save(bizRegNo2, stdClsList, conn);
                        recPubInstituCertDAO.save3(recvNo, bizRegNo2, bizName, bizEnName, caReprName, caReprIdentNo, caUserId, bizIdentNo, bizAgentName, bizDepart, bizPhoneNo, bizMobile, bizAgentEmail, bizFax, addr, homePage, this.requestTable, conn, cert_institu);
                        Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i mode = Addnew");
                        recContractDAO.insertContract(bizRegNo2, ctrList, conn);
                        recFinanYearDAO.insertBalance(bizRegNo2, finanList, conn);
                        Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i done = insertBalance");
                        recFinanYearDAO.insertReport(bizRegNo2, reportList, conn);
                        Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i done = insertReport");
                        final String sql2 = "insert into UM_EDOC_STATE (BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, PROCESS_ST) values (?, '1', SYSDATE, ?, ?, '0')";
                        pstmt2 = conn.prepareStatement(sql2);
                        pstmt2.setString(1, bizRegNo2);
                        pstmt2.setString(2, bizName);
                        pstmt2.setString(3, reprName);
                        if (pstmt2.executeUpdate() != 1) {
                            throw new Exception("[Lỗi đăng ký]");
                        }
                        Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i  response.getWriter().write(recvNo)");
                        response.getWriter().write(recvNo);
                    }
                }
                else if ("1".equals(step)) {//TODO 20180118 tach luong phe duyet ra class rieng de quan ly
                	Log.errors("20180118 Bao mat thong tin - he thong khong thuc hien trong chuc nang nay");
//                	/**
//                     * FIS 20161025 - Thuc hien sinh Ma phe quyet Chung thu so trong Buoc 3
//                     * Start
//                    */
//                    Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i Thuc hien sinh Ma phe quyet Chung thu so trong Buoc 3 recvNo = " + recvNo);
//                    
//                    Secu secu = null;
//            		secu = new Secu(Secu.SE);
//            		            		
//            		String strZIPNO = this.getRequestValue(this.requestTable.get("ZIPNO"));            		
//            		String ret[] = { null, null, null };
//
//            		IssueCertKICA iCK = new IssueCertKICA(secu);
//            		
//            		iCK.setCaInfo("ppca.mpi.gov.vn", 4501); // (CA_IP, CA_PORT)
//            		
//            		iCK.setMpiCompanyPolicy(); //set certificate policy for company, institu
//            		String strTELNO = bizPhoneNo;
//            		String strHP_NO = bizMobile;
//            		String strFAXNO = bizFax;
//            		
//            		if (strTELNO.length() > 15) strTELNO = strTELNO.substring(0,15);
//            		if (strHP_NO.length() > 15) strHP_NO = strHP_NO.substring(0,15);
//            		if (strFAXNO.length() > 15) strFAXNO = strFAXNO.substring(0,15);
//
//            		String strNAME = bizName.replaceAll("\\s+", " ");
//
//            		while (strNAME.length() > 20) { 
//            			int spaceIdx = strNAME.lastIndexOf(" ");
//            			
//            			if (spaceIdx < 0) {//truong hop khong tim duoc ky tu space hoac ten van dai hon 20 
//            				strNAME = strNAME.substring(0, 20).trim();
//            			} else {
//            				strNAME = strNAME.substring(0, strNAME.lastIndexOf(" ")).trim();
//            			}
//                    }
//
//            		String[] res;
//            		strNAME = CommUtil.stringReplace(strNAME);
//            		//dung tieng viet khong dau cho CTS
//            		strNAME = CommUtil.removeAccent(strNAME);
//            		//register user to ca
//            		res = iCK.regNewUser(strNAME, caUserId, bizRegNo2, bizAgentEmail, strTELNO, strHP_NO, strFAXNO, "00", "ADDRESS", "COMPN", "hawk");	               	
//            		//res = iCK.regNewUser(bizName, caUserId, bizRegNo2, bizAgentEmail, bizPhoneNo, bizMobile, bizFax, strZIPNO, addr, bizName, "hawk");
//
//            		if (res == null) {
//            			throw new RuntimeException("Phê duyệt chứng thư số không thành công, không khởi tạo được chứng thư số mới.");
//            		}
//
//            		UM_URB_CERT010 urc010 = new UM_URB_CERT010();
//
//            		String dn = res[0];
//            		refername = res[1];
//            		refercode = res[2];
//
//                    if (!refername.equals("") || !refercode.equals("")) {
//                        urc010.updateReceptionCert(dn, refername, refercode, bizRegNo2, bizRegNo2, appReqCode, conn);                           
//                    } else {
//                    	throw new RuntimeException("Phê duyệt chứng thư số không thành công, không khởi tạo được chứng thư số mới.");
//                    }
//
//	               	/**
//                     * FIS 20161025 - Thuc hien sinh Ma phe quyet Chung thu so trong Buoc 3
//                     * End
//                    */
//                    final UM_Auth_Check uac = new UM_Auth_Check(request, response);
//                    final UM_RAB_MastEnterStdCls mastEnterStdClsDAO = new UM_RAB_MastEnterStdCls();
//                    final UM_RAB_MastEnterStdCls2 mastEnterStdClsDAO2 = new UM_RAB_MastEnterStdCls2();
//                    final UM_RAB_MastSupplier mastSupplierEnterMastDAO = new UM_RAB_MastSupplier();
//                    final UM_RAB_MastRepr mastReprDAO = new UM_RAB_MastRepr();
//                    final UM_RAB_Contract contractDAO = new UM_RAB_Contract();
//                    final UM_RAB_FinanYear finanDAO = new UM_RAB_FinanYear();
//                    final UM_RAB_MastBidAgent mastBidAgentDAO = new UM_RAB_MastBidAgent();
//                    final UM_RAB_MastEnterStdClsHist mastEnterStdClsDAOHist = new UM_RAB_MastEnterStdClsHist();
//                    final UM_RAB_MastSupplierHist mastSupplierEnterMastDAOHist = new UM_RAB_MastSupplierHist();
//                    final UM_RAB_MastReprHist mastReprDAOHist = new UM_RAB_MastReprHist();
//                    final UM_RAB_ContractHist contractDAOHist = new UM_RAB_ContractHist();
//                    final UM_RAB_FinanYearHist finanDAOHist = new UM_RAB_FinanYearHist();
//                    final UM_RAB_MastBidAgentHist mastBidAgentDAOHist = new UM_RAB_MastBidAgentHist();
//                    recvNo = CommUtil.retSpace(request.getParameter("recvNo"));
//                    mastSupplierEnterMastDAO.save(bizRegNo2, bizName, bizEnName, commDate, addr, phoneNo, emplCount, fax, national, province, homePage, capital, supplierCls, bizCls, perBranch, docNo, recvNo, test_option_yn, conn);
//                    //Gap loi doi voi hon 500 danh muc cong viec vi vay chuyen sang class2
//                    //mastEnterStdClsDAO.save(bizRegNo2, stdClsList, conn);
//                    mastEnterStdClsDAO2.save(bizRegNo2, stdClsList, conn);
//                    mastReprDAO.save(bizRegNo2, reprList, conn);
//                    mastBidAgentDAO.save(bizIdentNo, bizRegNo2, bizAgentName, bizDepart, bizPosition, bizPhoneNo, bizAgentEmail, bizMobile, bizFax, conn);
//                    Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i Thuc hien phe duyet bizRegNo2 = " + bizRegNo2);
//                    contractDAO.insertContract(bizRegNo2, ctrList, conn);
//                    finanDAO.insertBalance(bizRegNo2, finanList, conn);
//                    finanDAO.insertReport(bizRegNo2, reportList, conn);
//                    recPubInstituCertDAO.modify(bizRegNo2, recvNo, appReqCode, "Y", bizName, bizEnName, caReprName, caReprIdentNo, caUserId, bizAgentName, bizIdentNo, bizDepart, bizPhoneNo, bizMobile, bizAgentEmail, bizFax, addr, homePage, conn);
//                    recSupplierEnterMastDao.modify(bizRegNo2, bizName, bizEnName, commDate, addr, phoneNo, emplCount, fax, national, province, homePage, capital, supplierCls, bizCls, recvNo, perBranch, docNo, conn);
//                    recEnterStdClsDAO.delete(bizRegNo2, conn);
//                    recEnterStdClsDAO.saved(bizRegNo2, stdClsList, conn);
//                    recReprDao.delete(bizRegNo2, conn);
//                    recReprDao.save(bizRegNo2, reprList, conn);
//                    recBidAgentDAO.delete(bizRegNo2, conn);
//                    recBidAgentDAO.save(bizIdentNo, bizRegNo2, bizAgentName, bizDepart, bizPosition, bizPhoneNo, bizAgentEmail, bizMobile, bizFax, conn);
//                    final String regDt = recSupplierEnterMastDao.getDetail2(bizRegNo2, recvNo, conn).getDataFromName("REG_DT");
//                    final String sql3 = "update UM_EDOC_STATE set PROCESS_ST = '1' where BIZ_REG_NO = '" + bizRegNo2 + "' and to_char(REG_DT, 'dd/mm/yyyy HH:MI:SS') = '" + regDt + "'";
//                    pstmt3 = conn.prepareStatement(sql3);
//                    pstmt3.executeUpdate();
//                    this.updateHistory(bizRegNo2, bizIdentNo, conn, request, response);
//                    response.getWriter().write(recvNo);
                }
                else if ("2".equals(step)) {
                	SSO sso = new SSO(request, response);	
                	String ID		= sso.getID();
                		 
                	if (sso.isLogin()) { 
                		if (ID != null && "a".equals(sso.getGubun())) {                			
                		} 	else{
                			Log.errors("Truy cap trai phep phe duyet");
                			return;
                		}
                	} else {
                		Log.errors("Truy cap trai phep phe duyet");
                		return;
                	}
                    Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i mode = Bao luu");
                    conn.setAutoCommit(false);
                    recvNo = CommUtil.retSpace(request.getParameter("recvNo"));
                    final String rRson = CommUtil.retSpace(request.getParameter("processRson"));
                    recPubInstituCertDAO.modify(bizRegNo2, recvNo, appReqCode, "E", conn);
                    final String regDt2 = recSupplierEnterMastDao.getDetail2(bizRegNo2, recvNo, conn).getDataFromName("REG_DT");
                    String sql = "update UM_EDOC_STATE set PROCESS_ST = '2' where BIZ_REG_NO = '" + bizRegNo2 + "' and to_char(REG_DT, 'dd/mm/yyyy HH:MI:SS') = '" + regDt2 + "'";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();
                    pstmt.clearParameters();
                    sql = "update UM_REC_SUPPLIER_ENTER_MAST set REG_YN = 'E', REJECTED_RSON = '" + rRson + "' where BIZ_REG_NO = '" + bizRegNo2 + "'";
                    pstmt2 = conn.prepareStatement(sql);
                    pstmt2.executeUpdate();
                    pstmt2.clearParameters();
                    sql = "update UM_REC_PUB_INSTITU_CERT set REG_YN = 'E', REJECTED_RSON = '" + rRson + "' where BIZ_REG_NO = '" + bizRegNo2 + "'";
                    pstmt3 = conn.prepareStatement(sql);
                    pstmt3.executeUpdate();
                    pstmt3.clearParameters();
                    
                    (new MailServer()).sendRegistrationEmailToNhaThauStep1Pedding(bizAgentEmail, bizAgentName,bizName, bizRegNo2, rRson);
                    
                }
                else if ("3".equals(step)) {
                	SSO sso = new SSO(request, response);	
                	String ID		= sso.getID();
                		 
                	if (sso.isLogin()) { 
                		if (ID != null && "a".equals(sso.getGubun())) {
                		} 	else{
                			Log.errors("Truy cap trai phep phe duyet");
                			return;
                		}
                	} else {
                		Log.errors("Truy cap trai phep phe duyet");
                		return;
                	}
                    Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i mode = Tu choi");
                    conn.setAutoCommit(false);
                    recvNo = CommUtil.retSpace(request.getParameter("recvNo"));
                    final String regDt3 = recSupplierEnterMastDao.getDetail2(bizRegNo2, recvNo, conn).getDataFromName("REG_DT");
                    final String sql4 = "update UM_EDOC_STATE set PROCESS_ST = '3' where BIZ_REG_NO = '" + bizRegNo2 + "' and to_char(REG_DT, 'dd/mm/yyyy HH:MI:SS') = '" + regDt3 + "'";
                    System.out.println(sql4);
                    pstmt = conn.prepareStatement(sql4);
                    pstmt.executeUpdate();
                    pstmt.clearParameters();
                    recPubInstituCertDAO.delete(recvNo, conn);
                    recReprDao.delete(bizRegNo2, conn);
                    recEnterStdClsDAO.delete(bizRegNo2, conn);
                    recBidAgentDAO.delete(bizRegNo2, conn);
                    recSupplierEnterMastDao.delete(bizRegNo2, conn);
                }
                else if ("4".equals(step)) {
                    Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i mode = Delete");
                    conn.setAutoCommit(false);
                    bizRegNo2 = this.getRequestValue(this.requestTable.get((Object)"comcode"));
                    recvNo = this.getRequestValue(this.requestTable.get((Object)"recvNo"));
                    final SSO sso2 = new SSO(request, response);
                    if (sso2.isLogin()) {
                        Log.debug("Quan ly nha thau xoa");
                        if (sso2.getGubun().equals("a")) {
                            this.updateHistory(bizRegNo2, bizIdentNo, conn, request, response);
                        }
                    }
                    final String regDt2 = recSupplierEnterMastDao.getDetail2(bizRegNo2, recvNo, conn).getDataFromName("REG_DT");
                    final String sql = "DELETE FROM UM_EDOC_STATE where BIZ_REG_NO = '" + bizRegNo2 + "' and to_char(REG_DT, 'dd/mm/yyyy HH:MI:SS') = '" + regDt2 + "'";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();
                    pstmt.clearParameters();
                    recFinanYearDAO.delete(bizRegNo2, conn);
                    recContractDAO.delete(bizRegNo2, conn);
                    recPubInstituCertDAO.delete(recvNo, conn);
                    recEnterStdClsDAO.delete(bizRegNo2, conn);
                    recReprDao.delete(bizRegNo2, conn);
                    recBidAgentDAO.delete(bizRegNo2, conn);
                    recSupplierEnterMastDao.delete(bizRegNo2, conn);
                    response.sendRedirect("/RA/UM_RAJ_ConuA050s.jsp?deltedOK=true");
                }
                else if ("5".equals(step)) {
                    Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i mode = Edit sau phe nhu con tete");
                    final UM_Auth_Check uac = new UM_Auth_Check(request, response);
                    final UM_RAB_MastEnterStdCls mastEnterStdClsDAO = new UM_RAB_MastEnterStdCls();
                    final UM_RAB_MastSupplier mastSupplierEnterMastDAO = new UM_RAB_MastSupplier();
                    final UM_RAB_MastRepr mastReprDAO = new UM_RAB_MastRepr();
                    final UM_RAB_Contract contractDAO = new UM_RAB_Contract();
                    final UM_RAB_FinanYear finanDAO = new UM_RAB_FinanYear();
                    final UM_RAB_MastBidAgent mastBidAgentDAO = new UM_RAB_MastBidAgent();
                    final UM_RAB_MastEnterStdClsHist mastEnterStdClsDAOHist = new UM_RAB_MastEnterStdClsHist();
                    final UM_RAB_MastSupplierHist mastSupplierEnterMastDAOHist = new UM_RAB_MastSupplierHist();
                    final UM_RAB_MastReprHist mastReprDAOHist = new UM_RAB_MastReprHist();
                    final UM_RAB_ContractHist contractDAOHist = new UM_RAB_ContractHist();
                    final UM_RAB_FinanYearHist finanDAOHist = new UM_RAB_FinanYearHist();
                    final UM_RAB_MastBidAgentHist mastBidAgentDAOHist = new UM_RAB_MastBidAgentHist();
                    recvNo = CommUtil.retSpace(request.getParameter("recvNo"));
                    mastSupplierEnterMastDAO.update(bizRegNo2, bizName, bizEnName, commDate, addr, phoneNo, emplCount, fax, national, province, homePage, capital, supplierCls, bizCls, perBranch, docNo, recvNo, test_option_yn, conn);
                    mastEnterStdClsDAO.deleteAll(bizRegNo2, conn);
                    mastEnterStdClsDAO.save(bizRegNo2, stdClsList, conn);
                    mastReprDAO.deleteAll(bizRegNo2, conn);
                    mastReprDAO.saveAgain(bizRegNo2, reprList, conn);
                    mastBidAgentDAO.deleteAll(bizRegNo2, conn);
                    mastBidAgentDAO.saveAgain(bizIdentNo, bizRegNo2, bizAgentName, bizDepart, bizPosition, bizPhoneNo, bizAgentEmail, bizMobile, bizFax, conn);
                    contractDAO.delete(bizRegNo2, conn);
                    contractDAO.insertContract(bizRegNo2, ctrList, conn);
                    finanDAO.insertBalanceFinal(bizRegNo2, finanList, conn);
                    finanDAO.insertReportFinal(bizRegNo2, reportList, conn);
                    this.updateHistory(bizRegNo2, bizIdentNo, conn, request, response);
                    response.getWriter().write(bizRegNo2);
                }
                conn.commit();
				Log.info(this.getClass().getName() + " >>>> MODE = " + mode + "  >>> step = " + step);
                if ("0".equals(step) && !"1".equals(mode)) {
                	Log.info("DANG KY THANH CONG ---  GUI EMAIL START >>> ");
                	try {
                		String approveReqCD = recPubInstituCertDAO.selectDetail(recvNo, conn).getDataFromName("APPROVE_REQ_CD");
                    	new MailServer().sendRegistrationEmailToNhaThauStep1(bizAgentEmail, bizAgentName, bizName, bizRegNo2, approveReqCD);
                    	Log.info("DANG KY THANH CONG ---  GUI EMAIL END >>> ");
                	} catch(Exception e){
                		Log.info("KHONG LAY DUOC APPROVE_REQ_CD >> EXCEPTION >> " + e.getMessage());
                		Log.info("KHONG GUI EMAIL END >>> ");
                	}
                }
                
//                if ("1".equals(step)) {TODO 20180118 Ko thuc hien chuc nang phe duyet trong class nay 
//                	
//                	Log.info("PHE DUYET DANG KY NT THANH CONG ---  GUI EMAIL START >>> ");                	
//                    (new MailServer()).sendRegistrationEmailToNhaThauStep2(bizAgentEmail, bizAgentName, bizName, refercode, refername);
//                	Log.info("PHE DUYET DANG KY NT THANH CONG ---  GUI EMAIL END >>> ");
//                }
            }
            catch (Exception ex) {
                final StackTraceElement[] elements = ex.getStackTrace();
                final String callerClass = this.getClass().toString();
                for (int i2 = 0; i2 < elements.length; ++i2) {
                    if (callerClass.endsWith(elements[i2].getClassName())) {
                        Log.debug("[C:" + elements[i2].getClassName() + "]" + "[M:" + elements[i2].getMethodName() + "]" + "[L:" + elements[i2].getLineNumber() + "]" + "[E:" + ex.toString() + "] ");
                    }
                }
                if (conn != null) {
                    try {
                        conn.rollback();
                    }
                    catch (Exception ex3) {}
                }
                if (conn != null) {
                    try {
                        conn.setAutoCommit(true);
                    }
                    catch (Exception ex4) {}
                }
                return;
            }
            finally {
            	if (pstmt != null) {
            		try {
            			pstmt.close();
            		} catch (Exception e) {
            			
            		}
            	}
            	
            	if (pstmt2 != null) {
            		try {
            			pstmt3.close();
            		} catch (Exception e) {
            			
            		}
            	}
            	
            	if (pstmt3 != null) {
            		try {
            			pstmt3.close();
            		} catch (Exception e) {
            			
            		}
            	}
            	
                if (conn != null) {
                    try {
                        tr.close();
                    }
                    catch (Exception exf) {
                        exf.printStackTrace();
                    }
                }
            }            
        }
    }
    
    private void updateHistory(final String bizRegNo, final String bizIdentNo, final Connection conn, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UM_Auth_Check uac = new UM_Auth_Check(request, response);
        final UM_RAB_MastEnterStdClsHist mastEnterStdClsDAOHist = new UM_RAB_MastEnterStdClsHist();
        final UM_RAB_MastSupplierHist mastSupplierEnterMastDAOHist = new UM_RAB_MastSupplierHist();
        final UM_RAB_MastReprHist mastReprDAOHist = new UM_RAB_MastReprHist();
        final UM_RAB_ContractHist contractDAOHist = new UM_RAB_ContractHist();
        final UM_RAB_FinanYearHist finanDAOHist = new UM_RAB_FinanYearHist();
        final UM_RAB_MastBidAgentHist mastBidAgentDAOHist = new UM_RAB_MastBidAgentHist();
        final String userId = uac.getID();
        final String sUId = this.getSubUserId(request);
        mastSupplierEnterMastDAOHist.saveHist(bizRegNo, userId, sUId, conn);
        mastEnterStdClsDAOHist.saveHist(bizRegNo, userId, sUId, conn);
        mastReprDAOHist.saveHist(bizRegNo, userId, sUId, conn);
        mastBidAgentDAOHist.saveHist(bizIdentNo, userId, sUId, conn);
        contractDAOHist.saveHistContract(bizRegNo, userId, sUId, conn);
    }
    
    public void setParamsFromrequestMultiTablepartForm(final HttpServletRequest req, final HttpServletResponse res) throws FileUploadException {
        this.requestTable = new Hashtable();
        if (req.getContentType() != null && req.getContentType().toLowerCase().indexOf("multipart/form-data") > -1) {
            final List items = new ServletFileUpload((FileItemFactory)new DiskFileItemFactory()).parseRequest(req);
            final Iterator itr = items.iterator();
            boolean flag = false;
            String year = "";
            this.finanList = new ArrayList();
            while (itr.hasNext()) {
                try {
                    final DiskFileItem item = (DiskFileItem)itr.next();
                    if ("stdFinanYear".equals(item.getFieldName())) {
                        year = item.getString();
                        if ("".equals(year)) {
                            continue;
                        }
                        flag = true;
                    }
                    else if ("stdFinanFile".equals(item.getFieldName())) {
                        if (!flag) {
                            continue;
                        }
                        this.finanList.add(new FinanYearItem(year, item));
                    }
                    else {
                        this.requestTable.put(item.getFieldName(), item.getString("UTF-8"));
                    }
                }
                catch (Exception ex) {}
            }
        }
        else {
            final Map map = req.getParameterMap();
            final Iterator entries = map.entrySet().iterator();
            String name = "";
            String value = "";
            while (entries.hasNext()) {
                final Map.Entry ent = (Map.Entry)entries.next();
                name = (String)ent.getKey();
                value = req.getParameter(name);
                this.requestTable.put(name, value);
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
    
    private String getSubUserId(final HttpServletRequest request) {
        String ret = "";
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            String cookieName = null;
            for (int i = cookies.length - 1; i >= 0; --i) {
                cookieName = cookies[i].getName();
                if (cookieName.equals("_SUID")) {
                    ret = URLDecoder.decode(cookies[i].getValue());
                    return ret;
                }
            }
        }
        return "";
    }
}
