// 
// Decompiled by Procyon v0.5.30
// 
 
package servlets;

import javax.servlet.http.Cookie;
import java.net.URLDecoder;
import java.io.IOException;
import javax.servlet.ServletException;

import entity.UM_ADJ_GovuA020b;
import entity.UM_RAE_GovuA010b;
import secu.lib.IssueCertKICA;
import secu.lib.Secu;

import java.sql.Connection;
import common.Log;
import java.sql.SQLException;
import dao.UM_RAB_MastSupplierHist;
import common.util.CommonMessage;
import beans.UM_RAB_GovuA010p;
import beans.UM_URB_CERT010;
import common.Trx;
import LOGIN.UM_Auth_Check;
import Mail.MailServer;
import beans.UM_ADB_FUNDINFO;
import beans.UM_ADB_GONGINFO;
import beans.UM_ADB_GONGCERTRECEIVE;
import beans.UM_ADB_GONGRECEIVE;
import beans.UM_ADB_GovrA010c;
import common.util.CommUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADJ_GovrB020c extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse response) throws ServletException, IOException {
        Trx resource = null;
        Connection conn = null;
        UM_Auth_Check uac = null;
        final CommUtil comutil = new CommUtil();
        final UM_ADB_GONGRECEIVE gongReceive = new UM_ADB_GONGRECEIVE();
        final UM_ADB_GONGCERTRECEIVE gongCertReceive = new UM_ADB_GONGCERTRECEIVE();
        final UM_ADB_GONGINFO gongInfo = new UM_ADB_GONGINFO();
        final UM_ADB_FUNDINFO gongFund = new UM_ADB_FUNDINFO();
        final CommUtil com = new CommUtil();
        final String relation = CommUtil.retSpace(req.getParameter("relation1"));
        final String recept = CommUtil.retSpace(req.getParameter("recept"));
        final String LicenseCode = CommUtil.retSpace(req.getParameter("LicenseCode"));
        final String LicenseGubun = CommUtil.retSpace(req.getParameter("LicenseGubun"));
        final String groupNo = CommUtil.retSpace(req.getParameter("groupNo"));
        final String departmentNo = CommUtil.retSpace(req.getParameter("departmentNo"));
        final String mathCode = CommUtil.retSpace(req.getParameter("mathCode"));
        final String test_option_yn = CommUtil.retSpace(req.getParameter("test_option_yn"));
        final String dependCode = CommUtil.retSpace(req.getParameter("dependCode"));
       
        String userID = "";
        String regStatus = null;
            try {
            	uac = new UM_Auth_Check(req, response);
                userID = uac.getID();
                resource = new Trx(this);
                conn = resource.getConnection();
                conn.setAutoCommit(false);
                final UM_RAE_GovuA010b ettcode = new UM_RAB_GovuA010p().select_goma(recept, groupNo, departmentNo);
                if (gongInfo.isMasterCodeExist(ettcode.getg2bCode(), conn)) {
                    throw new Exception("Mã chủ đầu tư đã tồn tại.");
                }
                if (gongFund.isMasterCreditCodeExist(ettcode.getg2bCode(), conn)) {
                    throw new Exception("Mã kế toán đã tồn tại.");
                }
                regStatus = gongReceive.getRegStatus(recept, conn);
                if (!regStatus.equals("N") && !regStatus.equals("E")) {
                    conn.setAutoCommit(true);
                    CommonMessage.printMsg(null, "7", "Đã được chấp nhận hoặc xử lý.", "", response);
                    try {}
                    catch (Exception ex2) {}
                    return;
                }
                gongReceive.updateReceiveGonggonMaster("", recept, "Y", userID, conn);
                if (LicenseGubun.equals("Y")) {
                    regStatus = gongCertReceive.getRegStatus(LicenseCode, conn);
                    if (!regStatus.equals("N") && !regStatus.equals("E")) {
                        final String message = "Chủ đầu tư đã bị từ chối hoặc đang bảo lưu.";
                        throw new Exception(message);
                    }
                    gongCertReceive.updateReceiveGonggonCert("", LicenseCode, "Y", userID, conn);
                    final UM_RAB_MastSupplierHist mastSupplierEnterMastDAOHist = new UM_RAB_MastSupplierHist();
                    final String sUId = this.getSubUserId(req);
                    mastSupplierEnterMastDAOHist.saveCAHist(LicenseCode, sUId, conn);
                }
                else if ("".equals(LicenseGubun)) {
                    gongCertReceive.updateReceiveGonggonCert("", LicenseCode, "Y", userID, conn);
                }
            
            // Tao Ma phe duyet CTS ngay khi thuc hien phe duyet
            UM_ADJ_GovuA020b ettLicense = new UM_ADB_GovrA010c().select_GovernmentList_Confirm(LicenseCode);
            Secu secu = null;
    		secu = new Secu(Secu.SE); // SE = 20
    		String g2bCode = ettLicense.getG2BCODE();

    		IssueCertKICA iCK = new IssueCertKICA(secu);

    		String[] res = null;

			iCK.setCaInfo("ppca.mpi.gov.vn", 4501); // (CA_IP, CA_PORT)

			iCK.setMpiCompanyPolicy(); //set certificae policy for company, institue
			String strTELNO = ettLicense.getTEL();
			String strHP_NO = ettLicense.getHP();
			String strFAXNO = ettLicense.getFAX();
			String strNAME = ettLicense.getCNAME();
			String strEMAIL = ettLicense.getEMAIL();
			String strCOMNO = ettLicense.getCOMNO();
			String strUSRID = ettLicense.getUSRID(); 
			String strNICKN = "SILVER";
			if (strTELNO.length() > 15) strTELNO = strTELNO.substring(0,15);
			if (strHP_NO.length() > 15) strHP_NO = strHP_NO.substring(0,15);
			if (strFAXNO.length() > 15) strFAXNO = strFAXNO.substring(0,15);

			// hieund23 31/07/2018 thêm tiền tố DT- vào tên của CTS để phân biệt dùng cho hệ thống đào tạo và hệ thống thực
			strNAME = CommUtil.getCERT_PREFIX() + strNAME ;
			strNAME = strNAME.replaceAll("\\s+", " ");
			while (strNAME.length() > 20) {
				int spaceIdx = strNAME.lastIndexOf(" ");
    			
    			if (spaceIdx < 0) {//truong hop khong tim duoc ky tu space hoac ten van dai hon 20
    				strNAME = strNAME.substring(0, 20).trim();
    			} else {
    				strNAME = strNAME.substring(0, strNAME.lastIndexOf(" ")).trim();
    			}
			}

			strNAME = stringReplace(strNAME);
			//su dung tieng viet khong dau cho cts
			strNAME = CommUtil.removeAccent(strNAME);
			res = iCK.regNewUser(strNAME, strUSRID, strCOMNO, strEMAIL, strTELNO, strHP_NO, strFAXNO, "00", "ADDRESS", "COMPN", strNICKN); //strZIPNO Cuc dau thau	

			if(res == null){
				//Log.debug("접수번호["+recept+"] [인증기관접속시] 한국정보인증 사용자 등록대행 실패 - " + iCK.getErrMsg());					
				throw new Exception("# Thông báo - Đăng ký xác thực người dùng bị lỗi ");
			} else {
				UM_URB_CERT010 urc010 = new UM_URB_CERT010();
				String dn = res[0];
				String refername = res[1];
				String refercode = res[2];
				urc010.updateReceptionCert(dn, refername, refercode, strCOMNO, g2bCode, dependCode, conn);
			}

            // Ket thuc tao ma phe duyet CTS            
                final UM_RAB_MastSupplierHist mastSupplierEnterMastDAOHist = new UM_RAB_MastSupplierHist();
                final String sUId = this.getSubUserId(req);
                mastSupplierEnterMastDAOHist.saveCAHist(LicenseCode, sUId, conn);
                gongInfo.insertGongInfoNew(ettcode, relation, conn, userID, test_option_yn);
                gongFund.insertMultiFundCod(ettcode.getg2bCode(), ettcode.getsaupNo(), conn);
                conn.commit();
                conn.setAutoCommit(true);
                Log.debug(">>>>>>>>>>>>>>>>>>>>>>>>Send email");
                MailServer mail = new MailServer();
            //mail.sendRegistrationEmailToMTStep1Approval(ettcode.getmasterMail(), ettcode.gettaskmaster(), ettcode.getgoNameFull(), ettcode.getsaupNo(), dependCode, ettcode.getg2bCode());
            mail.sendRegistrationEmailToMTStep2(strEMAIL, ettLicense.getMYNAME(), strNAME, res[2], res[1]);
           
            CommonMessage.printMsg(null, "7", "Đã xử lý thành công.", "", response);
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
                Log.debug("UM_ADJ_GovrB020c.java Số tiếp nhận [" + recept + "], Số tiếp nhận chứng nhận số [" + LicenseCode + "], loại[" + LicenseGubun + "], UserID[" + userID + "]:" + ex.toString());
            CommonMessage.printMsg(null, "", ex.getMessage(), null, response);
        
       } finally {
                try {
                    if (conn != null) {
                        resource.close();
                    }
                }
                catch (Exception ex5) {}
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
    
    private String stringReplace(String str)
    {

        String str_imsi = "";

        //\ / : ? " < > | * $ _ + , ' ` = ; #
        //! % ( ) - @ ^  { } ~
        		
        /*String[] filter_word = { "", "\\.", "\\?", "\\/", "\\~", "\\!", "\\@",
                "\\#", "\\$", "\\%", "\\^", "\\&", "\\*", "\\(", "\\)", "\\_",
                "\\+", "\\=", "\\|", "\\\\", "\\}", "\\]", "\\{", "\\[",
                "\\\"", "\\'", "\\:", "\\;", "\\<", "\\,", "\\>", "\\.", "\\?",
                "\\/" };*/
        
        String[] filter_word = { "\\?", "\\/", "\\#", "\\$", 
                                "\\*", "\\_", "\\+", "\\=", 
                                "\\|", "\\\\", "\\}", "\\{", "\\\"", 
                                "\\'", "\\:", "\\;", "\\<", 
                                "\\,", "\\>", "\\?", "\\/",
                                "\\`","\\[", "\\]"};
        
        for (int i = 0; i < filter_word.length; i++)
        {
            str_imsi = str.replaceAll(filter_word[i], "");
            str = str_imsi;
        }
        return str;
    }
}
