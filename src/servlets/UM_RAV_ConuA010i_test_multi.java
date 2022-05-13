package servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LOGIN.UM_Auth_Check;
import Mail.MailServer;
import common.CommEntity;
import common.Log;
import common.OneRowEntity;
import common.Trx;
import common.util.CommUtil;
import common.util.CommonMessage;
import dao.UM_RAB_ListStdCls;
import dao.UM_RAB_MastBidAgent;
import dao.UM_RAB_MastBidAgentHist;
import dao.UM_RAB_MastEnterStdCls2;
import dao.UM_RAB_MastEnterStdClsHist;
import dao.UM_RAB_MastRepr;
import dao.UM_RAB_MastReprHist;
import dao.UM_RAB_MastSupplier;
import dao.UM_RAB_MastSupplierHist;
import dao.UM_RAB_RecBidAgent;
import dao.UM_RAB_RecEnterStdCls;
import dao.UM_RAB_RecPubInstituCert;
import dao.UM_RAB_RecRepr;
import dao.UM_RAB_RecSupplier;
import entity.UM_URV_UserC020b;
import dao.UM_RAB_RecContract;
import dao.UM_RAB_Contract;
import dao.UM_RAB_ContractHist;
import dao.UM_RAB_FinanYear;
import beans.UM_ADB_GovrA010c;
import beans.UM_URB_CERT010;
import g2b.sso.SSO;
import secu.lib.IssueCertKICA;
import secu.lib.Secu;


public class UM_RAV_ConuA010i_test_multi extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9024488581763029751L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Trx tr = null;
        Connection conn = null;
		
		try {
			// Lấy thông tin tài khoản đăng nhập
			SSO sso = new SSO(request, response);	
	    	String ID		= sso.getID();
	    	
	    	// Log lại IP truy cập
	    	String currentIP = request.getRemoteAddr();
	    	String ip2 = request.getHeader("x-forwarded-for");
	    	Log.debug("currentIP=" + currentIP + ";x-forwarded-for=" + ip2);
	    	
	    	// Kiểm tra quyền của tài khoản  
	    	if (sso.isLogin()) { 
	    		if (ID != null && "a".equals(sso.getGubun())) {
	    			/*if ("A000000000004".equals(ID)) {//TODO
	    				Log.errors("Account cam phe duyet");
	    				return;
	    			}*/
	    		} else{
	    			Log.errors("Truy cap trai phep phe duyet");
	    			throw new Exception("Phê duyệt thất bại: Tài khoản không có quyền phê duyệt");
	    		}
	    	} else {
	    		Log.errors("Truy cap trai phep phe duyet");
    			throw new Exception("Phê duyệt thất bại: Tài khoản không có quyền phê duyệt");
	    	}
	    	
	    	// Khởi tạo list cơ quan cần phê duyệt 
	    	String[] selectedIndex = request.getParameterValues("cb_selectedIndex[]");
	    	if(selectedIndex == null || (selectedIndex != null && selectedIndex.length == 0)) {
	    		Log.errors("Phê duyệt thất bại: Chưa có nhà thầu nào được chọn để phê duyệt !");
	    		CommonMessage.printMsg(null, "", "Phê duyệt thất bại: Chưa có nhà thầu nào được chọn để phê duyệt !", null, response);
	    		return;
	    	}
	    	CoQuanNhaThau[] lstNhaThau = new CoQuanNhaThau[selectedIndex.length];
	    	for (int i = 0; i < selectedIndex.length; i++) {
				int index = Integer.parseInt(selectedIndex[i]);
				String bizRegNo= CommUtil.retSpace(request.getParameter("bizRegNo_" + selectedIndex[i]));
				String status= CommUtil.retSpace(request.getParameter("status_" + selectedIndex[i]));
				String recvNo= CommUtil.retSpace(request.getParameter("recvNo_" + selectedIndex[i]));
				String testOptionYN = "Y"; // mặc định chỉ cho phê duyệt CTS thử nghiệm
				Log.debug("Nhà thầu: index = " + index + "; bizRegNo = " + bizRegNo + "; recvNo = " + recvNo + "; status = " + status );
				CoQuanNhaThau coQuan = new CoQuanNhaThau(index, bizRegNo, status, recvNo, testOptionYN);
				lstNhaThau[i] = coQuan;
			}
	    	
	    	// Khởi tạo các object DAO
	    	//UM_RAB_ListStdCls clsList = new UM_RAB_ListStdCls(); // Danh mục phân loại nghành nghề
	    	UM_RAB_RecBidAgent recBidAgentDAO = new UM_RAB_RecBidAgent(); // Thao tác bảng người đại diện dự thầu
	    	//UM_RAB_RecEnterStdCls recEnterStdClsDAO = new UM_RAB_RecEnterStdCls(); // Thao tác bảng mã nghành nghề
	    	UM_RAB_RecPubInstituCert recPubInstituCertDAO = new UM_RAB_RecPubInstituCert(); // Thao tác bảng đăng ký CA
	    	//UM_RAB_RecRepr recReprDAO = new UM_RAB_RecRepr(); // Thao tác bảng người phụ trách
	    	UM_RAB_RecSupplier recSupplierEnterMastDAO = new UM_RAB_RecSupplier(); // Thao tác bảng nhà thầu
	    	//UM_RAB_RecContract recCtr = new UM_RAB_RecContract();
	    	//UM_RAB_FinanYear recFina = new UM_RAB_FinanYear();
	    	UM_ADB_GovrA010c ctl = new UM_ADB_GovrA010c();
	    	
	    	
	    	// Tiến hành phê duyệt
	    	for (int i = 0; i < lstNhaThau.length; i++) {
	    		
	    		try {
	    			
	    			//validate 
	    			if("".equals(lstNhaThau[i].getBizRegNo())) {
	    				lstNhaThau[i].setHavingErrors(true);
	    				lstNhaThau[i].setErrorString("Lỗi truyền dữ liệu: Không tìm thấy số ĐKKD.");
	            		continue;
	            	}
	    			
	    			tr = new Trx(this);
	                conn = tr.getConnection();
	                conn.setAutoCommit(false);
	                
	                //Kiểm tra thông tin thanh toán phí đăng ký
	                String paid             = "";
	    		    String showPayment      = "";
	                UM_URV_UserC020b ettPP = ctl.getCurrentPaymentPolicy();
	    		    if (ettPP != null){
	    			    if ((ettPP.getS01().equals("2")||ettPP.getS01().equals("3"))&&(Integer.parseInt(ettPP.getS06())>0)) {
	    			    	showPayment = "1";
	    			    }
	    			    else {
	    			    	paid = "1";
	    			    }
	    		    }
				    if (showPayment.equals("1")) {
				    	paid         = ctl.checkPaymentBizRegNo(lstNhaThau[i].getBizRegNo(),"1");//Trường hợp tìm kiếm bằng số đăng ký kinh doanh
				    }
				    Log.debug("Check payment: bizRegNo = " + lstNhaThau[i].getBizRegNo() + " => paid = " + paid);
				    if(!"1".equals(paid)) {
				    	lstNhaThau[i].setHavingErrors(true);
				    	lstNhaThau[i].setErrorString("Nhà thầu chưa thanh toán phí đăng ký !");
				    	continue;
				    }
	                
	                //Lấy thông tin [Thông tin người phụ trách]
	                //CommEntity[] reprList = recReprDAO.getList(lstNhaThau[i].getBizRegNo(), conn);
	              
	                //Lấy thông tin [Thông tin mã ngành nghề]
	                //CommEntity[] stdClsList = recEnterStdClsDAO.getList(lstNhaThau[i].getBizRegNo(), conn);
	               	
	                //Lấy thông tin [Thông tin hợp đồng của nhà thầu]
	                //CommEntity[] ctrList = recCtr.getListSign(lstNhaThau[i].getBizRegNo(), conn);
	                
	                //Lấy thông tin [Thông tin bảng cân đối kế toán]
	                //CommEntity[] finaList = recFina.getListBalance(lstNhaThau[i].getBizRegNo(), conn);
	                
	                //Lấy thông tin [Thông tin báo cáo tài chính]
	                //CommEntity[] reportList = recFina.getListReport(lstNhaThau[i].getBizRegNo(), conn);
	    			
	                //Lấy thông tin [Người đại diện dự thầu]
	                CommEntity bidAgent = recBidAgentDAO.getList(lstNhaThau[i].getBizRegNo(), conn)[0];
	              
	                //Lấy thông tin [Thông tin chung]
	                OneRowEntity recSupplierEnterMast = recSupplierEnterMastDAO.selectDetail2(lstNhaThau[i].getBizRegNo(), conn);
	               
	                //Lấy thông tin [Mã tiếp nhận chứng thư số]
	                String oldRecvNo = CommUtil.retSpace(recSupplierEnterMast.getDataFromName("RECV_NO"));
	                
	                //Lấy thông tin [Thông tin đăng ký chứng thư số]
	                OneRowEntity recPubInstituCert = recPubInstituCertDAO.selectDetail(oldRecvNo, conn);
	                
	                //Lấy thông tin [Mã phê duyệt đăng ký CTS]
	                final String appReqCode = CommUtil.retSpace(recPubInstituCert.getDataFromName("APPROVE_REQ_CD"));
	                
	                //Tạo mã số phê duyệt CTS mới trong bước 3 
	                String recvNo = recSupplierEnterMastDAO.getChodalReceptionNumber(conn);
	                
	                //Khởi tạo object tạo CTS 
	                Log.debug("$$$$$$$$$$$$$$$$$ UM_RAV_ConuA010i_pdnt Thuc hien sinh Ma phe duyet Chung thu so trong Buoc 3 recvNo = " + recvNo);
                    Secu secu = null;
            		secu = new Secu(Secu.SE);
            		IssueCertKICA iCK = new IssueCertKICA(secu);
            		iCK.setCaInfo("ppca.mpi.gov.vn", 4501); // (CA_IP, CA_PORT)
            		iCK.setMpiCompanyPolicy(); //set certificate policy for company, institu
            		
            		//Chuẩn bị các thông tin khởi tạo CTS mới
            		String strTELNO = CommUtil.retSpace(bidAgent.data[5]);
            		String strHP_NO = CommUtil.retSpace(bidAgent.data[6]);
            		String strFAXNO = CommUtil.retSpace(bidAgent.data[7]);
            		if (strTELNO.length() > 15) strTELNO = strTELNO.substring(0,15);
            		if (strHP_NO.length() > 15) strHP_NO = strHP_NO.substring(0,15);
            		if (strFAXNO.length() > 15) strFAXNO = strFAXNO.substring(0,15);
            		
            		// Thêm tiền tố DT vào tên CTS để phân biệt dùng cho server đào tạo và thực 
            		String strNAME = CommUtil.getCERT_PREFIX() + CommUtil.retSpace(recSupplierEnterMast.getDataFromName("BIZ_NM")).replaceAll("\\s+", " ");
            		while (strNAME.length() > 20) { 
            			int spaceIdx = strNAME.lastIndexOf(" ");
            			if (spaceIdx < 0) {//truong hop khong tim duoc ky tu space hoac ten van dai hon 20 
            				strNAME = strNAME.substring(0, 20).trim();
            			} else {
            				strNAME = strNAME.substring(0, strNAME.lastIndexOf(" ")).trim();
            			}
                    }
            		strNAME = CommUtil.stringReplace(strNAME); //Loại bỏ các ký tự đặc biệt
            		strNAME = CommUtil.removeAccent(strNAME); //Dùng tiếng Việt không dấu cho CTS
            		
            		// Khởi chạy hàm tạo CTS mới
            		String[] res = iCK.regNewUser(strNAME, 
            						CommUtil.retSpace(recPubInstituCert.getDataFromName("USER_ID")), 
            						CommUtil.retSpace(recSupplierEnterMast.getDataFromName("BIZ_REG_NO")), 
            						CommUtil.retSpace(bidAgent.data[4]), 
            						strTELNO, strHP_NO, strFAXNO, "00", "ADDRESS", "COMPN", "hawk");
	                if(res == null) {
	                	throw new Exception("Lỗi không khởi tạo được chứng thư số mới.");
	                }
	                
	                UM_URB_CERT010 urc010 = new UM_URB_CERT010();
            		String dn = res[0];
            		String refername = res[1];
            		String refercode = res[2];

                    if (!refername.equals("") || !refercode.equals("")) {
                    	//Cập nhật tên (CERT_NM) và thông tin CTS vừa tạo vào bảng UM_REC_PUB_INSTITU_CERT
                        urc010.updateReceptionCert(dn, refername, refercode, 
                        		CommUtil.retSpace(recSupplierEnterMast.getDataFromName("BIZ_REG_NO")), 
                        		CommUtil.retSpace(recSupplierEnterMast.getDataFromName("BIZ_REG_NO")), 
                        		appReqCode, conn);    
                        lstNhaThau[i].setReferCode(refercode);
                        lstNhaThau[i].setReferName(refername);
                    } else {
                    	throw new Exception("Lỗi không khởi tạo được chứng thư số mới.");
                    }
                    
                    //Tiến hành cập nhật các thông tin của nhà thầu sau khi đã phê duyệt thành công
                    /****************************************************************************/
                    
                    // Khởi tạo các object DAO
                    final UM_RAB_MastEnterStdCls2 mastEnterStdClsDAO2 = new UM_RAB_MastEnterStdCls2();
                    final UM_RAB_MastSupplier mastSupplierEnterMastDAO = new UM_RAB_MastSupplier();
                    final UM_RAB_MastRepr mastReprDAO = new UM_RAB_MastRepr();
                    final UM_RAB_Contract contractDAO = new UM_RAB_Contract();
                    final UM_RAB_FinanYear finanDAO = new UM_RAB_FinanYear();
                    final UM_RAB_MastBidAgent mastBidAgentDAO = new UM_RAB_MastBidAgent();
                    
                    //Chèn thông tin đăng ký chung của nhà thầu vừa phê duyệt vào bảng UM_SUPPLIER_ENTER_MAST
                    mastSupplierEnterMastDAO.saveFromRec(lstNhaThau[i].getBizRegNo(), lstNhaThau[i].getTestOptionYN(), conn);
	                
                    //Chèn thông tin danh sách ngành nghề kinh doanh của nhà thầu vừa phê duyệt vào bảng UM_ENTER_STD_CLS
                    mastEnterStdClsDAO2.saveFromRec(lstNhaThau[i].getBizRegNo(), conn);
                    
                    //Chèn thông tin người đại diện của nhà thầu vừa phê duyệt vào bảng UM_REPR;
                    mastReprDAO.saveFromRec(lstNhaThau[i].getBizRegNo(), conn);
                    
                    //Chèn thông tin người đại diện, phụ trách của nhà thầu vừa phê duyệt vào bảng UM_BID_AGENT;
                    mastBidAgentDAO.saveFromRec(lstNhaThau[i].getBizRegNo(), conn);
                    
                    //Chèn thông tin các hợp đồng thực hiện của nhà thầu vừa phê duyệt vào bảng UM_CONTRACT_INFO;
                    contractDAO.insertContractFromRec(lstNhaThau[i].getBizRegNo(), conn);
                    
                    //Chèn thông tin bảng cân đối kế toán của nhà thầu vừa phê duyệt vào bảng UM_FINAN_BALANCE;
                    finanDAO.insertBalanceFromRec(lstNhaThau[i].getBizRegNo(), conn);
                    
                    //Chèn thông tin báo cáo tài chính của nhà thầu vừa phê duyệt vào bảng UM_FINAN_REPORT;
                    finanDAO.insertReportFromRec(lstNhaThau[i].getBizRegNo(), conn);
                    
                    //Cập nhật thông tin đăng ký, trạng thái xử lý REG_YN vào bảng UM_REC_PUB_INSTITU_CERT;
                    recPubInstituCertDAO.modify(lstNhaThau[i].getBizRegNo(), oldRecvNo, appReqCode, lstNhaThau[i].getTestOptionYN(), 
                    		CommUtil.retSpace(recSupplierEnterMast.getDataFromName("BIZ_NM")), 
                    		CommUtil.retSpace(recSupplierEnterMast.getDataFromName("BIZ_EN_NM")), 
                    		CommUtil.retSpace(recPubInstituCert.getDataFromName("REPR_NM")), 
                    		CommUtil.retSpace(recPubInstituCert.getDataFromName("REPR_IDENT_NO")), 
                    		CommUtil.retSpace(recPubInstituCert.getDataFromName("USER_ID")), 
                    		CommUtil.retSpace(bidAgent.data[0]), 
                    		CommUtil.retSpace(bidAgent.data[1]), 
                    		CommUtil.retSpace(bidAgent.data[3]), 
                    		CommUtil.retSpace(bidAgent.data[5]), 
                    		CommUtil.retSpace(bidAgent.data[6]), 
                    		CommUtil.retSpace(bidAgent.data[4]), 
                    		CommUtil.retSpace(bidAgent.data[7]), 
                    		CommUtil.retSpace(recSupplierEnterMast.getDataFromName("ADDR")), 
                    		CommUtil.retSpace(recSupplierEnterMast.getDataFromName("WEBSITE")), 
                    		conn);
                    
                    //Cập nhật thông tin đăng ký vào bảng UM_REC_SUPPLIER_ENTER_MAST;
                    recSupplierEnterMastDAO.modifyQuick(lstNhaThau[i].getBizRegNo(), conn);
                    
                    //Cập nhật ngày phê duyệt vào bảng UM_EDOC_STATE;
                    recSupplierEnterMastDAO.updateEDocState(lstNhaThau[i].getBizRegNo(), CommUtil.retSpace(recSupplierEnterMast.getDataFromName("REG_DT")), conn);
                    
                    //Cập nhật thông tin vào các bảng lịch sử thay đổi: UM_SUPPLIER_ENTER_MAST_HIST; UM_ENTER_STD_CLS_HIST; UM_REPR_HIST; UM_BID_AGENT_HIST; UM_CONTRACT_INFO_HIST
                    this.updateHistory(lstNhaThau[i].getBizRegNo(), CommUtil.retSpace(bidAgent.data[1]), conn, request, response);
                    
                    conn.commit();
                    Log.info("PHE DUYET DANG KY NT THANH CONG ---  GUI EMAIL START >>> ");
                	//Gửi mail thông báo về mã phê duyệt CTS
                    (new MailServer()).sendRegistrationEmailToNhaThauStep2(CommUtil.retSpace(bidAgent.data[4]), 
                    		CommUtil.retSpace(bidAgent.data[0]), 
                    		CommUtil.retSpace(recSupplierEnterMast.getDataFromName("BIZ_NM")), refercode, refername);
                	Log.info("PHE DUYET DANG KY NT THANH CONG ---  GUI EMAIL END >>> ");
                	
                    
		    	} catch(Exception e) {
		    		try {
                        if (conn != null) {
                            conn.rollback();
                        }
                    }
                    catch (SQLException ex3) {
                    	Log.debug("UM_RAV_ConuA010i_test_multi.java Số tiếp nhận [" + lstNhaThau[i].getRecvNo() + "], Số ĐKKD [" + lstNhaThau[i].getBizRegNo() + "] :" + ex3.toString());
                        lstNhaThau[i].setHavingErrors(true);
                        lstNhaThau[i].setErrorString(ex3.toString());
                        continue;
                    }
                    try {
                        if (conn != null) {
                            conn.setAutoCommit(true);
                        }
                    }
                    catch (SQLException ex4) {
                    	Log.debug("UM_RAV_ConuA010i_test_multi.java Số tiếp nhận [" + lstNhaThau[i].getRecvNo() + "], Số ĐKKD [" + lstNhaThau[i].getBizRegNo() + "] :" + ex4.toString());
                        lstNhaThau[i].setHavingErrors(true);
                        lstNhaThau[i].setErrorString(ex4.toString());
                        continue;
                    }
                    Log.debug("UM_RAV_ConuA010i_test_multi.java Số tiếp nhận [" + lstNhaThau[i].getRecvNo() + "], Số ĐKKD [" + lstNhaThau[i].getBizRegNo() + "] :" + e.toString());
                    lstNhaThau[i].setHavingErrors(true);
                    lstNhaThau[i].setErrorString(e.toString());
                    continue;
		    	} finally {
		    		
		    	}
			}
	    	
	    	// Gửi thông báo về màn hình phê duyệt 
            String notificationHeader = "Đã xử lý " + lstNhaThau.length + " trường hợp: <br/>";
            String notificationContent = "";
            int successCount = 0;
            for (int j = 0; j < lstNhaThau.length; j++) {
				if(lstNhaThau[j].havingErrors) {
					notificationContent = notificationContent + "- Nhà thầu số " + lstNhaThau[j].getIndex() + " mã " + lstNhaThau[j].getBizRegNo() + ": Thất bại => " + lstNhaThau[j].getErrorString() + "<br/>";
				} else {
					notificationContent = notificationContent + "- Nhà thầu số " + lstNhaThau[j].getIndex() + " số ĐKKD " + lstNhaThau[j].getBizRegNo() + ": Thành công ! Mã nhận CTS: " + lstNhaThau[j].getReferCode()  + " - " + lstNhaThau[j].getReferName() + "<br/>";
					successCount++;
				}
			}
            String notificationFooter = "=> Thành công: " + successCount + " trường hợp; Thất bại: " + (lstNhaThau.length - successCount) + " trường hợp." ; 
            CommonMessage.printMsg(null, "5", notificationHeader + notificationContent + notificationFooter, "", response);
	    	
        
		} catch(Exception ex) {
			CommonMessage.printMsg(null, "", ex.getMessage(), null, response);
		} finally {
        	if(conn != null) {
        		tr.close();
        	}
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
	
	private void updateHistory(final String bizRegNo, final String bizIdentNo, final Connection conn, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final UM_Auth_Check uac = new UM_Auth_Check(request, response);
        final UM_RAB_MastEnterStdClsHist mastEnterStdClsDAOHist = new UM_RAB_MastEnterStdClsHist();
        final UM_RAB_MastSupplierHist mastSupplierEnterMastDAOHist = new UM_RAB_MastSupplierHist();
        final UM_RAB_MastReprHist mastReprDAOHist = new UM_RAB_MastReprHist();
        final UM_RAB_ContractHist contractDAOHist = new UM_RAB_ContractHist();
        final UM_RAB_MastBidAgentHist mastBidAgentDAOHist = new UM_RAB_MastBidAgentHist();
        final String userId = uac.getID();
        final String sUId = this.getSubUserId(request);
        mastSupplierEnterMastDAOHist.saveHist(bizRegNo, userId, sUId, conn);
        mastEnterStdClsDAOHist.saveHist(bizRegNo, userId, sUId, conn);
        mastReprDAOHist.saveHist(bizRegNo, userId, sUId, conn);
        mastBidAgentDAOHist.saveHist(bizIdentNo, userId, sUId, conn);
        contractDAOHist.saveHistContract(bizRegNo, userId, sUId, conn);
    }

	
	private class CoQuanNhaThau {
		
		private int index;
		private String bizRegNo;
		private String status;
		private String recvNo;
		private String testOptionYN;
		private String errorString;
		private boolean havingErrors;
		private String referName;
		private String referCode;
		
		public CoQuanNhaThau(int index, String bizRegNo, String status, String recvNo, String testOptionYN) {
			super();
			this.index = index;
			this.bizRegNo = bizRegNo;
			this.status = status;
			this.recvNo = recvNo;
			this.testOptionYN = testOptionYN;
			this.errorString = "";
			this.havingErrors = false;
			this.referCode = "";
			this.referName = "";
		}

		public CoQuanNhaThau() {
			super();
		}

		protected int getIndex() {
			return index;
		}

		protected void setIndex(int index) {
			this.index = index;
		}

		protected String getBizRegNo() {
			return bizRegNo;
		}

		protected void setBizRegNo(String bizRegNo) {
			this.bizRegNo = bizRegNo;
		}

		protected String getStatus() {
			return status;
		}

		protected void setStatus(String status) {
			this.status = status;
		}

		protected String getRecvNo() {
			return recvNo;
		}

		protected void setRecvNo(String recvNo) {
			this.recvNo = recvNo;
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
