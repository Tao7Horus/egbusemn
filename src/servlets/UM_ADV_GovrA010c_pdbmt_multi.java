package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LOGIN.UM_Auth_Check;
import Mail.MailServer;
import beans.UM_ADB_GONGRECEIVE;
import beans.UM_ADB_GovrA010c;
import beans.UM_URB_CERT;
import beans.UM_URB_CERT010;
import common.Log;
import common.Trx;
import common.util.CommUtil;
import common.util.CommonMessage;
import entity.UM_ADJ_GovuA020b;
import g2b.sso.SSO;
import secu.lib.IssueCertKICA;
import secu.lib.Secu;


public class UM_ADV_GovrA010c_pdbmt_multi extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3669099033903376498L;
	private final static String FW_IP = "10.212.134";
    private final static String FW_IP_SEC = "10.64.1";

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Trx trx = null;
        Connection con = null;
        CoQuanBenMoiThau[] lstCoQuan = null;
        UM_Auth_Check uac = null;
		Log.debug(this.getClass().getName() + " begin");
		try {
			
			// kiểm tra ip được quyền phê duyệt ko
//	    	String currentIP = request.getRemoteAddr();
//	    	if ((currentIP.indexOf(FW_IP) < 0) && (currentIP.indexOf(FW_IP_SEC) < 0)) {
//	    		Log.errors("Truy cap trai phep phe duyet them" + currentIP.indexOf(FW_IP));
//				return;
//	    	} else {
//	    		Log.info("Thuc hien phe duyet");
//	    	}  
			
			final UM_URB_CERT urc11 = new UM_URB_CERT();
			final UM_URB_CERT010 urc010 = new UM_URB_CERT010();
			final UM_ADB_GovrA010c ctl = new UM_ADB_GovrA010c();
			final UM_ADB_GONGRECEIVE gongReceive = new UM_ADB_GONGRECEIVE();
			
			SSO sso = new SSO(request, response);
			if(!sso.isLogin()) {
				throw new Exception("Bạn cần đăng nhập vào tài khoản có quyền phê duyệt để thực hiện chức năng này !");
			}
			
			//Kiểm tra quyền của CTS phê duyệt: cls = k => CTS quản trị bên mời thầu
        	String userCls = urc11.getUserClsFromUserId(sso.getID(), con);
    		if(sso.getID() == null || !"k".equals(userCls)) {
    			throw new Exception("Chứng thư số của bạn không có quyền thực hiện chức năng này !");
    		}
			
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			
			// Khởi tạo list cơ quan cần phê duyệt 
        	String[] selectedIndex = request.getParameterValues("cb_selectedIndex[]");
        	if(selectedIndex == null || (selectedIndex != null && selectedIndex.length == 0)) {
        		throw new Exception("Chưa có cơ quan nào được chọn để phê duyệt !");
        	}
        	lstCoQuan = new CoQuanBenMoiThau[selectedIndex.length];
        	for (int i = 0; i < selectedIndex.length; i++) {
				int index = Integer.parseInt(selectedIndex[i]);
				String g2BCode= CommUtil.retSpace(request.getParameter("G2BCODE_" + selectedIndex[i]));
				String recept= CommUtil.retSpace(request.getParameter("RECEPT_" + selectedIndex[i]));
				String dependCode= CommUtil.retSpace(request.getParameter("dependCode_" + selectedIndex[i]));
				String comNo= CommUtil.retSpace(request.getParameter("COMNO_" + selectedIndex[i]));
				String testOptionYN = "Y"; // mặc định chỉ cho phê duyệt CTS thử nghiệm
				Log.debug("Cơ quan: index = " + index + "; g2BCode = " + g2BCode + "; COMNO = " + comNo +"; recept = " + recept + "; dependCode = " + dependCode + ";  ");
				CoQuanBenMoiThau coQuan = new CoQuanBenMoiThau(index, g2BCode, recept, dependCode, testOptionYN, comNo);
				lstCoQuan[i] = coQuan;
			}
        	
        	uac = new UM_Auth_Check(request, response);
			
        	trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            con.setAutoCommit(false);
            
            for (int i = 0; i < lstCoQuan.length; i++) {
				
            	try {
            		
            		///validate begin
	            	if("".equals(lstCoQuan[i].getG2BCode())) {
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Lỗi truyền dữ liệu: Không tìm thấy mã cơ quan.");
	            		continue;
	            	}
	            	
	            	if("".equals(lstCoQuan[i].getRecept())) {
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Lỗi truyền dữ liệu: Không tìm thấy mã tiếp nhận đăng ký.");
	            		continue;
	            	}
	            	
	            	if("".equals(lstCoQuan[i].getDependCode())) {
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Lỗi truyền dữ liệu: Không tìm thấy mã phê duyệt đăng ký CTS.");
	            		continue;
	            	}
	            	if("".equals(lstCoQuan[i].getComNo())) {
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Lỗi truyền dữ liệu: Không tìm thấy số ĐKKD.");
	            		continue;
	            	}
	            	//validate end
	            	
	            	//Kiểm tra mã cơ quan đã được đăng ký chưa
	            	if (urc010.isDataExistOnGigwanMaster(lstCoQuan[i].getG2BCode(), lstCoQuan[i].getComNo(), con) == 0) { 
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Không thể thực hiện phê duyệt chứng nhận số cho các cơ quan chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.");
                        continue;
		        	}
	            	
	            	String regStatus = gongReceive.getRegCertStatus(lstCoQuan[i].getRecept(), con);
	            	if (!regStatus.equals("N") && !regStatus.equals("E")) { 
	            		lstCoQuan[i].setHavingErrors(true);
	            		lstCoQuan[i].setErrorString("Cơ quan này đã được chấp nhận hoặc xử lý.");
	            		continue;
	            	}
	            	
	            	//khởi tạo các object tạo CTS
	            	Log.debug("$$$$$$$$$$$$$$$$$ UM_ADV_GovrA010c_pdnt Thuc hien sinh Ma phe duyet Chung thu so trong Buoc 3 recvNo = " + lstCoQuan[i].getRecept());
                    Secu secu2 = null;
            		secu2 = new Secu(Secu.SE);
            		IssueCertKICA iCK = new IssueCertKICA(secu2);
            		iCK.setCaInfo("ppca.mpi.gov.vn", 4501); // (CA_IP, CA_PORT)
            		iCK.setMpiCompanyPolicy(); //set certificate policy for company, institu
            		
            		//Khởi tạo dữ liệu tạo CTS
            		UM_ADJ_GovuA020b ettcode  = ctl.Approval_Confirm2(lstCoQuan[i].getG2BCode(), lstCoQuan[i].getDependCode(), lstCoQuan[i].getRecept());
            		String strTELNO = CommUtil.retSpace(ettcode.getTEL());
            		String strHP_NO = CommUtil.retSpace(ettcode.getHP());
            		String strFAXNO = CommUtil.retSpace(ettcode.getFAX());
            		String USRID = CommUtil.retSpace(ettcode.getUSRID());
            		String EMAIL = CommUtil.retSpace(ettcode.getEMAIL());
            		String COMNO = CommUtil.retSpace(ettcode.getCOMNO());
            		String MYNAME = CommUtil.retSpace(ettcode.getMYNAME());
            		String CNAME = CommUtil.retSpace(ettcode.getCNAME());
            		if (strTELNO.length() > 15) strTELNO = strTELNO.substring(0,15);
            		if (strHP_NO.length() > 15) strHP_NO = strHP_NO.substring(0,15);
            		if (strFAXNO.length() > 15) strFAXNO = strFAXNO.substring(0,15);
            		String[] res ;
            		
            		// hieund23 31/07/2017 thêm tiền tố DT- vào tên CTS để phân biệt dùng cho hệ thống đào tạo hay hệ thống thật
            		String strNAME = CommUtil.getCERT_PREFIX() + CNAME;
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
            		
            		//Khởi tạo CTS mới
            		res = iCK.regNewUser(strNAME, USRID, COMNO, EMAIL, strTELNO, strHP_NO, strFAXNO, "00", "ADDRESS", "COMPN", "SILVER");
            		if (res == null) {
            			throw new RuntimeException("Phê duyệt chứng thư số không thành công, không khởi tạo được chứng thư số mới.");
                    }
            		String dn = res[0];
            		String refername = res[1];
            		String refercode = res[2];
            		
            		//
            		if (!refername.equals("") || !refercode.equals("")) {
            			//Cập nhật trạng thái xử lý REG_YN = 'Y' vào bảng UM_REC_PUB_INSTITU_CERT
                        urc010.updateReception("Y", "", lstCoQuan[i].getRecept(), lstCoQuan[i].getG2BCode(), lstCoQuan[i].getDependCode(), uac.getID(), con);
                        
                        //Cập nhật nội dung CTS vừa tạo vào bảng UM_REC_PUB_INSTITU_CERT
                        urc010.updateReceptionCert(dn, refername, refercode, COMNO, lstCoQuan[i].getG2BCode(), lstCoQuan[i].getDependCode(), con);
                        lstCoQuan[i].setReferCode(refercode);
                        lstCoQuan[i].setReferName(refername);
                        Log.debug("Đăng ký CTS thành công cho cơ quan có mã: " + lstCoQuan[i].getG2BCode() + "; Mã tiếp nhận CTS: " + lstCoQuan[i].getRecept() + "; Tên CTS: " + dn);
		            } else {
		            	throw new RuntimeException("Phê duyệt chứng thư số không thành công, không khởi tạo được chứng thư số mới.");
		            }
            		
            		con.commit();
                    con.setAutoCommit(true);
                    
                    //Gửi mail thông báo về mã phê duyệt CTS  
                    Log.info("PHE DUYET DANG KY THEM MT THANH CONG ---  GUI EMAIL START >>> ");                	                  
                    (new MailServer()).sendRegistrationEmailToMTStep2(EMAIL, MYNAME, CNAME, refercode, refername);                    
                	Log.info("PHE DUYET DANG KY THEM MT THANH CONG ---  GUI EMAIL END >>> ");
            		
            	} catch(Exception e) {
            		try {
                        if (con != null) {
                            con.rollback();
                        }
                    }
                    catch (SQLException ex3) {
                    	Log.debug("UM_ADV_GovrA010c_pdbmt_multi.java Mã cơ quan ["+ lstCoQuan[i].getG2BCode() + "], Số tiếp nhận [" + lstCoQuan[i].getRecept() + "], Mã phê duyệt đăng ký [" + lstCoQuan[i].getDependCode() +"] :" + ex3.toString());
                        lstCoQuan[i].setHavingErrors(true);
                		lstCoQuan[i].setErrorString(ex3.toString());
                        continue;
                    }
                    try {
                        if (con != null) {
                            con.setAutoCommit(true);
                        }
                    }
                    catch (SQLException ex4) {
                    	Log.debug("UM_ADV_GovrA010c_pdbmt_multi.java Mã cơ quan ["+ lstCoQuan[i].getG2BCode() + "], Số tiếp nhận [" + lstCoQuan[i].getRecept() + "], Mã phê duyệt đăng ký [" + lstCoQuan[i].getDependCode() +"] :" + ex4.toString());
                        lstCoQuan[i].setHavingErrors(true);
                		lstCoQuan[i].setErrorString(ex4.toString());
                        continue;
                    }
                    Log.debug("UM_ADV_GovrA010c_pdbmt_multi.java Mã cơ quan ["+ lstCoQuan[i].getG2BCode() + "], Số tiếp nhận [" + lstCoQuan[i].getRecept() + "], Mã phê duyệt đăng ký [" + lstCoQuan[i].getDependCode() +"] :" + e.toString());
                    lstCoQuan[i].setHavingErrors(true);
            		lstCoQuan[i].setErrorString(e.toString());
                    continue;
            	} finally {
            		
            	}
			}
            
            // Gửi thông báo về màn hình phê duyệt 
            String notificationHeader = "Đã xử lý " + lstCoQuan.length + " trường hợp: <br/>";
            String notificationContent = "";
            int successCount = 0;
            for (int j = 0; j < lstCoQuan.length; j++) {
				if(lstCoQuan[j].havingErrors) {
					notificationContent = notificationContent + "- Cơ quan số " + lstCoQuan[j].getIndex() + " mã " + lstCoQuan[j].getG2BCode() + ": Thất bại => " + lstCoQuan[j].getErrorString() + "<br/>";
				} else {
					notificationContent = notificationContent + "- Cơ quan số " + lstCoQuan[j].getIndex() + " mã " + lstCoQuan[j].getG2BCode() + ": Thành công ! Mã nhận CTS: " + lstCoQuan[j].getReferCode()  + " - " + lstCoQuan[j].getReferName() + "<br/>";
					successCount++;
				}
			}
            String notificationFooter = "=> Thành công: " + successCount + " trường hợp; Thất bại: " + (lstCoQuan.length - successCount) + " trường hợp." ; 
            CommonMessage.printMsg(null, "5", notificationHeader + notificationContent + notificationFooter, "", response);
        	
		} catch(Exception ex ) {
			CommonMessage.printMsg(null, "", ex.getMessage(), null, response);
		} finally {
        	if(con != null) {
        		trx.close();
        	}
        }
	}
	
	private String stringReplace(String str)
    {

        String str_imsi = "";

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
	
	private class CoQuanBenMoiThau {
		
		private int index;
		private String g2BCode;
		private String recept;
		private String dependCode;
		private String comNo;
		private String testOptionYN;
		private String errorString;
		private boolean havingErrors;
		private String referName;
		private String referCode;
		
		protected CoQuanBenMoiThau() {
			super();
		}

		protected CoQuanBenMoiThau(int index, String g2bCode, String recept, String dependCode, String testOptionYN, String comNo) {
			super();
			this.index = index;
			this.g2BCode = g2bCode;
			this.recept = recept;
			this.dependCode = dependCode;
			this.comNo = comNo;
			this.testOptionYN = testOptionYN;
			this.errorString = "";
			this.havingErrors = false;
			this.referCode = "";
			this.referName = "";
		}
		

		protected int getIndex() {
			return index;
		}

		protected void setIndex(int index) {
			this.index = index;
		}

		protected String getG2BCode() {
			return g2BCode;
		}

		protected void setG2BCode(String g2bCode) {
			g2BCode = g2bCode;
		}

		protected String getRecept() {
			return recept;
		}

		protected void setRecept(String recept) {
			this.recept = recept;
		}

		protected String getDependCode() {
			return dependCode;
		}

		protected void setDependCode(String dependCode) {
			this.dependCode = dependCode;
		}

		protected String getComNo() {
			return comNo;
		}

		protected void setComNo(String comNo) {
			this.comNo = comNo;
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

	
}
