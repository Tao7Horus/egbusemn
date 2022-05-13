package servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LOGIN.UM_Auth_Check;
import Mail.MailServer;
import beans.UM_ADB_FUNDINFO;
import beans.UM_ADB_GONGCERTRECEIVE;
import beans.UM_ADB_GONGINFO;
import beans.UM_ADB_GONGRECEIVE;
import beans.UM_ADB_GovrA010c;
import beans.UM_RAB_GovuA010p;
import beans.UM_URB_CERT010;
import common.Log;
import common.Trx;
import common.util.CommUtil;
import common.util.CommonMessage;
import dao.UM_RAB_MastSupplierHist;
import entity.UM_ADJ_GovuA020b;
import entity.UM_RAE_GovuA010b;
import secu.lib.IssueCertKICA;
import secu.lib.Secu;

public class UM_ADJ_GovrB060c extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Trx resource = null;
        Connection conn = null;
        UM_Auth_Check uac = null;
        final UM_ADB_GONGRECEIVE gongReceive = new UM_ADB_GONGRECEIVE();
        final UM_ADB_GONGCERTRECEIVE gongCertReceive = new UM_ADB_GONGCERTRECEIVE();
        final UM_ADB_GONGINFO gongInfo = new UM_ADB_GONGINFO();
        final UM_ADB_FUNDINFO gongFund = new UM_ADB_FUNDINFO();
        CoQuanBenMoiThau[] lstCoQuan = null;
        String userID = "";
        String regStatus = null;
        
        try {
        	
        	// Khởi tạo list cơ quan cần phê duyệt 
        	String[] selectedIndex = request.getParameterValues("cb_selectedIndex[]");
        	if(selectedIndex == null || (selectedIndex != null && selectedIndex.length == 0)) {
        		throw new Exception("Chưa có cơ quan nào được chọn để phê duyệt !");
        	}
        	lstCoQuan = new CoQuanBenMoiThau[selectedIndex.length];
        	for (int i = 0; i < selectedIndex.length; i++) {
				int index = Integer.parseInt(selectedIndex[i]);
				String masterCode= CommUtil.retSpace(request.getParameter("masterCode_" + selectedIndex[i]));
				String recept= CommUtil.retSpace(request.getParameter("recept_" + selectedIndex[i]));
				String licenseCode= CommUtil.retSpace(request.getParameter("LicenseCode_" + selectedIndex[i]));
				String groupNo= CommUtil.retSpace(request.getParameter("groupNo_" + selectedIndex[i]));
				String departmentNo= CommUtil.retSpace(request.getParameter("departmentNo_" + selectedIndex[i]));
				String testOptionYN = "Y"; // mặc định chỉ cho phê duyệt CTS thử nghiệm
				Log.debug("Cơ quan: index = " + index + "; masterCode = " + masterCode + "; recept = " + recept + "; licenseCode = " + licenseCode + "; groupNo = " + groupNo + "; departmentNo = " + departmentNo);
				CoQuanBenMoiThau coQuan = new CoQuanBenMoiThau(index, masterCode, recept, licenseCode, groupNo, departmentNo, testOptionYN);
				lstCoQuan[i] = coQuan;
			}
        	
        	uac = new UM_Auth_Check(request, response);
            userID = uac.getID();
            resource = new Trx(this);
            conn = resource.getConnection();
            
            // Tiến hành validate, phê duyệt cơ quan trong list
            for (int i = 0; i < lstCoQuan.length; i++) {
            	try {
	            	conn.setAutoCommit(false);
	            	
	            	///validate begin
	            	if("".equals(lstCoQuan[i].getMasterCode())) {
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Lỗi truyền dữ liệu: Không tìm thấy mã cơ quan.");
	            		continue;
	            	}
	            	
	            	if("".equals(lstCoQuan[i].getRecept())) {
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Lỗi truyền dữ liệu: Không tìm thấy mã tiếp nhận.");
	            		continue;
	            	}
	            	
	            	if("".equals(lstCoQuan[i].getLicenseCode())) {
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Lỗi truyền dữ liệu: Không tìm thấy mã tiếp nhận CTS.");
	            		continue;
	            	}
	            	
	            	if(gongInfo.isMasterCodeExist(lstCoQuan[i].getMasterCode(), conn)) {
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Mã chủ đầu tư đã tồn tại.");
	            		continue;
	            	}
	            	
	            	if(gongFund.isMasterCreditCodeExist(lstCoQuan[i].getMasterCode(), conn)) {
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Mã kế toán đã tồn tại.");
	            		continue;
	            	}
	            	
	            	regStatus = gongReceive.getRegStatus(lstCoQuan[i].getRecept(), conn);
	            	if (!regStatus.equals("N") && !regStatus.equals("E")) { 
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Cơ quan này đã được chấp nhận hoặc xử lý.");
	            		continue;
	            	}
	            	///validate end
	            	
	            	// Cập nhật trạng thái xử lý vào bảng UM_REC_PUB_INSTITU_MAST REG_YN = 'Y'
	            	gongReceive.updateReceiveGonggonMaster("", lstCoQuan[i].getRecept(), "Y", userID, conn);
	            	
	            	// Cập nhật trạng thái xử lý CTS vào bảng UM_REC_PUB_INSTITU_CERT REG_YN = 'Y'
	            	gongCertReceive.updateReceiveGonggonCert("", lstCoQuan[i].getLicenseCode(), "Y", userID, conn);
	            	
	            	// Khởi tạo object tạo, mã hóa chứng thư số
	            	Secu secu = null;
	        		secu = new Secu(Secu.SE); // SE = 20
	        		IssueCertKICA iCK = new IssueCertKICA(secu);
	        		iCK.setCaInfo("ppca.mpi.gov.vn", 4501); // (CA_IP, CA_PORT)
	    			iCK.setMpiCompanyPolicy(); //set certificae policy for company, institue
	    			
	    			// Lấy thông tin đăng ký CTS trong bảng UM_REC_PUB_INSTITU_CERT
	            	UM_ADJ_GovuA020b ettLicense = new UM_ADB_GovrA010c().select_GovernmentList_Confirm(lstCoQuan[i].getLicenseCode());
	        		String[] res = null;
	        		String g2bCode  = CommUtil.retSpace(ettLicense.getG2BCODE());
	        		String strTELNO = CommUtil.retSpace(ettLicense.getTEL());
	    			String strHP_NO = CommUtil.retSpace(ettLicense.getHP());
	    			String strFAXNO = CommUtil.retSpace(ettLicense.getFAX());
	    			String strNAME  = CommUtil.retSpace(ettLicense.getCNAME());
	    			String strEMAIL = CommUtil.retSpace(ettLicense.getEMAIL());
	    			String strCOMNO = CommUtil.retSpace(ettLicense.getCOMNO());
	    			String strUSRID = CommUtil.retSpace(ettLicense.getUSRID()); 
	    			String strNICKN = "SILVER";
	    			if (strTELNO.length() > 15) strTELNO = strTELNO.substring(0,15);
	    			if (strHP_NO.length() > 15) strHP_NO = strHP_NO.substring(0,15);
	    			if (strFAXNO.length() > 15) strFAXNO = strFAXNO.substring(0,15);
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
	    			// Bỏ hết các ký tự đặc biệt khỏi tên đăng ký CTS
	    			strNAME = stringReplace(strNAME);
	    			// Sử dụng tiếng Việt không dấu cho tên đăng ký CTS
	    			strNAME = CommUtil.removeAccent(strNAME);
	    			
	    			// Khởi tạo đăng ký chứng thư số mới 
	    			res = iCK.regNewUser(strNAME, strUSRID, strCOMNO, strEMAIL, strTELNO, strHP_NO, strFAXNO, "00", "ADDRESS", "COMPN", strNICKN); //strZIPNO Cuc dau thau	
	    			if(res == null){
	    				throw new Exception("# Thông báo - Đăng ký xác thực người dùng bị lỗi ");
	    			} else {
	    				UM_URB_CERT010 urc010 = new UM_URB_CERT010();
	    				String dn = res[0];
	    				String refername = res[1];
	    				String refercode = res[2];
	    				//Cập nhật thông tin đăng ký CTS trên bảng UM_REC_PUB_INSTITU_CERT => CERT_NM = tên CTS
	    				urc010.updateReceptionCert(dn, refername, refercode, strCOMNO, g2bCode, ettLicense.getdependCode(), conn);
	    				lstCoQuan[i].setReferName(refername);
	    				lstCoQuan[i].setReferCode(refercode);
	    				Log.debug("Đăng ký CTS thành công cho cơ quan có mã: " + lstCoQuan[i].getMasterCode() + "; Mã tiếp nhận CTS: " + lstCoQuan[i].getRecept() + "; Tên CTS: " + dn);
	    			}
	    			
	    			// Lấy ra thông tin đăng ký chung của BMT từ bảng UM_REC_PUB_INSTITU_MAST
	    			final UM_RAE_GovuA010b ettcode = new UM_RAB_GovuA010p().select_goma(lstCoQuan[i].getRecept(), lstCoQuan[i].getGroupNo(), lstCoQuan[i].getDepartmentNo());
	    			final UM_RAB_MastSupplierHist mastSupplierEnterMastDAOHist = new UM_RAB_MastSupplierHist();
	                final String sUId = this.getSubUserId(request);
	                
	                // Cập nhật thông tin lịch sử đăng ký CTS trong bảng UM_REC_PUB_INSTITU_CERT_HIST theo sub_user
	                mastSupplierEnterMastDAOHist.saveCAHist(lstCoQuan[i].getLicenseCode(), sUId, conn);
	                
	                // Chèn thông tin chung về cơ quan mới được phê duyệt trong bảng UM_PUB_INSTITU_MAST
	                gongInfo.insertGongInfoNew(ettcode, "", conn, userID, lstCoQuan[i].getTestOptionYN());
	                
	                // Chèn thông tin mã số thuế của cơ quan được phê duyệt vào bảng UM_PUB_INSTITU_ACCT_CD
	                gongFund.insertMultiFundCod(ettcode.getg2bCode(), ettcode.getsaupNo(), conn);
	                conn.commit();
	                conn.setAutoCommit(true);
	                Log.debug("Xử lý thành công cho cơ quan có mã: " + lstCoQuan[i].getMasterCode() + "; Tên cơ quan: " + ettcode.getgoNameFull() + "; Mã tiếp nhận CTS: " + lstCoQuan[i].getRecept()); 
	                
	                // Gửi mail cho cơ quan được phê duyệt thành công
	                Log.debug(">>>>>>>>>>>>>>>>>>>>>>>>Send email");
	                MailServer mail = new MailServer();
		            mail.sendRegistrationEmailToMTStep2(strEMAIL, ettLicense.getMYNAME(), strNAME, res[2], res[1]);
		            
            	} catch(Exception e) {
            		try {
                        if (conn != null) {
                            conn.rollback();
                        }
                    }
                    catch (SQLException ex3) {
                    	Log.debug("UM_ADJ_GovrB060c.java Số tiếp nhận [" + lstCoQuan[i].getRecept() + "], Số tiếp nhận chứng nhận số [" + lstCoQuan[i].getLicenseCode() + "], loại[], UserID[" + userID + "]:" + ex3.toString());
                        lstCoQuan[i].setHavingErrors(true);
                		lstCoQuan[i].setErrorString(ex3.toString());
                        continue;
                    }
                    try {
                        if (conn != null) {
                            conn.setAutoCommit(true);
                        }
                    }
                    catch (SQLException ex4) {
                    	Log.debug("UM_ADJ_GovrB060c.java Số tiếp nhận [" + lstCoQuan[i].getRecept() + "], Số tiếp nhận chứng nhận số [" + lstCoQuan[i].getLicenseCode() + "], loại[], UserID[" + userID + "]:" + ex4.toString());
                        lstCoQuan[i].setHavingErrors(true);
                		lstCoQuan[i].setErrorString(ex4.toString());
                        continue;
                    }
                    Log.debug("UM_ADJ_GovrB060c.java Số tiếp nhận [" + lstCoQuan[i].getRecept() + "], Số tiếp nhận chứng nhận số [" + lstCoQuan[i].getLicenseCode() + "], loại[], UserID[" + userID + "]:" + e.toString());
                    lstCoQuan[i].setHavingErrors(true);
            		lstCoQuan[i].setErrorString(e.toString());
                    continue;
            	}
			}
            
            // Gửi thông báo về màn hình phê duyệt 
            String notificationHeader = "Đã xử lý " + lstCoQuan.length + " trường hợp: <br/>";
            String notificationContent = "";
            int successCount = 0;
            for (int j = 0; j < lstCoQuan.length; j++) {
				if(lstCoQuan[j].havingErrors) {
					notificationContent = notificationContent + "- Cơ quan số " + lstCoQuan[j].getIndex() + " mã " + lstCoQuan[j].getMasterCode() + ": Thất bại => " + lstCoQuan[j].getErrorString() + "<br/>";
				} else {
					notificationContent = notificationContent + "- Cơ quan số " + lstCoQuan[j].getIndex() + " mã " + lstCoQuan[j].getMasterCode() + ": Thành công ! Mã nhận CTS: " + lstCoQuan[j].getReferCode()  + " - " + lstCoQuan[j].getReferName() + "<br/>";
					successCount++;
				}
			}
            String notificationFooter = "=> Thành công: " + successCount + " trường hợp; Thất bại: " + (lstCoQuan.length - successCount) + " trường hợp." ; 
            CommonMessage.printMsg(null, "5", notificationHeader + notificationContent + notificationFooter, "", response);
        	
        } catch (Exception ex) {
        	CommonMessage.printMsg(null, "", ex.getMessage(), null, response);
        } finally {
        	if(conn != null) {
        		resource.close();
        	}
        }
	}
	
	private class CoQuanBenMoiThau {
		private int index;
		private String masterCode;
		private String recept;
		private String licenseCode;
		private String groupNo;
		private String departmentNo;
		private String testOptionYN;
		private String errorString;
		private boolean havingErrors;
		private String referName;
		private String referCode;
		
		protected CoQuanBenMoiThau(int index, String masterCode, String recept, String licenseCode, String groupNo,
				String departmentNo, String testOptionYN) {
			super();
			this.index = index;
			this.masterCode = masterCode;
			this.recept = recept;
			this.licenseCode = licenseCode;
			this.groupNo = groupNo;
			this.departmentNo = departmentNo;
			this.testOptionYN = testOptionYN;
			this.havingErrors = false;
			this.errorString = "";
			this.referCode = "";
			this.referName = "";
		}
		
		protected CoQuanBenMoiThau() { }

		protected int getIndex() {
			return index;
		}

		protected void setIndex(int index) {
			this.index = index;
		}

		protected String getMasterCode() {
			return masterCode;
		}

		protected void setMasterCode(String masterCode) {
			this.masterCode = masterCode;
		}

		protected String getRecept() {
			return recept;
		}

		protected void setRecept(String recept) {
			this.recept = recept;
		}

		protected String getLicenseCode() {
			return licenseCode;
		}

		protected void setLicenseCode(String licenseCode) {
			this.licenseCode = licenseCode;
		}

		protected String getGroupNo() {
			return groupNo;
		}

		protected void setGroupNo(String groupNo) {
			this.groupNo = groupNo;
		}

		protected String getDepartmentNo() {
			return departmentNo;
		}

		protected void setDepartmentNo(String departmentNo) {
			this.departmentNo = departmentNo;
		}

		protected String getTestOptionYN() {
			return testOptionYN;
		}

		protected void setTestOptionYN(String testOptionYN) {
			this.testOptionYN = testOptionYN;
		}

		protected String getErrorString() {
			return errorString;
		}

		protected void setErrorString(String errorString) {
			this.errorString = errorString;
		}

		protected boolean isHavingErrors() {
			return havingErrors;
		}

		protected void setHavingErrors(boolean havingErrors) {
			this.havingErrors = havingErrors;
		}

		protected String getReferName() {
			return referName;
		}

		protected void setReferName(String referName) {
			this.referName = referName;
		}

		protected String getReferCode() {
			return referCode;
		}

		protected void setReferCode(String referCode) {
			this.referCode = referCode;
		}
		

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
