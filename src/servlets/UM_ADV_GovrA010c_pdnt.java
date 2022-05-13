// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.ParameterParser;

import LOGIN.UM_Auth_Check;
import Mail.MailServer;
import beans.UM_URB_CERT;
import beans.UM_URB_CERT010;
import common.CommDbQuery;
import common.Log;
import common.OneRowEntity;
import common.Trx;
import common.util.CommUtil;
import common.util.CommonMessage;
import g2b.sso.SSO;
import secu.lib.IssueCertKICA;
import secu.lib.MessageDigest;
import secu.lib.Secu;

public class UM_ADV_GovrA010c_pdnt extends HttpServlet
{
	private final static String FW_IP = "10.212.134";
    private final static String FW_IP_SEC = "10.64.1";
	
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
    	
    	String currentIP = req.getRemoteAddr();
//    	if ((currentIP.indexOf(FW_IP) < 0) && (currentIP.indexOf(FW_IP_SEC) < 0)) {
//    		Log.errors("Truy cap trai phep phe duyet them" + currentIP.indexOf(FW_IP));
//			return;
//    	} else {
//    		Log.info("Thuc hien phe duyet");
//    	}  
    	SSO sso = new SSO(req, res);	
    	String ID = sso.getID();

        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");
        Trx trx = null;
        Connection con = null;
        final ParameterParser psr = new ParameterParser((ServletRequest)req);
        final UM_URB_CERT010 urc010 = new UM_URB_CERT010();
        final CommUtil com = new CommUtil();
        final String G2BCODE = psr.getStringParameter("G2BCODE", "");
        String CNAME = psr.getStringParameter("CNAME", "");
        final String IDENT = psr.getStringParameter("IDENT", "");
        final String KNAME = psr.getStringParameter("KNAME", "");
        final String IDENTJUMIN = psr.getStringParameter("IDENTJUMIN", "");
        final String ZIPNO = psr.getStringParameter("ZIPNO", "");
        final String COMNO = psr.getStringParameter("COMNO", "");
        final String ADDRS = psr.getStringParameter("ADDRS", "");
        final String ADDRESS2 = psr.getStringParameter("ADDRESS2", "");
        final String FAX = psr.getStringParameter("FAX", "");
        final String MYNAME = psr.getStringParameter("MYNAME", "");
        final String OFFICEDE = psr.getStringParameter("OFFICEDE", "");
        String JUMIN = psr.getStringParameter("real_jumin_chk_yn1", "");
        if ("".equals(JUMIN)) {
            JUMIN = psr.getStringParameter("JUMIN", "");
        }
        final String TEL = psr.getStringParameter("TEL", "");
        final String EMAIL = psr.getStringParameter("EMAIL", "");
        final String HP = psr.getStringParameter("HP", "");
        final String smsCheck = psr.getStringParameter("smsCheck", "");
        final String USRID = psr.getStringParameter("USRID", "");
        final String CERT_ORG = psr.getStringParameter("CERT_ORG", "");
        final String HOME = psr.getStringParameter("HOME", "");
        final String STR = psr.getStringParameter("STR", "");
        final String STR2 = psr.getStringParameter("STR1", "");
        String recept = psr.getStringParameter("recept", "");
        final String back = psr.getStringParameter("back", "");
        final String dependCode = psr.getStringParameter("dependCode", "");
        final String flag = psr.getStringParameter("flag", "");
        final String approval = psr.getStringParameter("approval", "");
        Log.debug("flag " + flag);
        Log.debug("approval " + approval);
        final String branchOffi1 = psr.getStringParameter("branchOffi1", "");
        final String passwdmodOK = psr.getStringParameter("passwdmodOK", "");
        final String gID = psr.getStringParameter("gID", "");
        final String i07 = psr.getStringParameter("i07", "");
        String i8 = "";
        final String count = "";
        String approvalCode = "";
        final String dn = psr.getStringParameter("dn", "");
        final String refername = psr.getStringParameter("refername", "");
        final String refercode = psr.getStringParameter("refercode", "");
        final String comNo = psr.getStringParameter("comNo", "");
        String g2bCode = psr.getStringParameter("g2bCode", "");
        if (g2bCode.equals("")) {
            g2bCode = psr.getStringParameter("g2bCode", "");
        }
        final String cert_cls = psr.getStringParameter("CERT_CLS", "");
        Log.debug("cert_cls " + cert_cls);
        final Secu secu = new Secu(1);
        final String strMData1 = null;
        final MessageDigest md = new MessageDigest(secu);
        if ("Y".equals(passwdmodOK)) {
            i8 = md.create(i07);
        }
        final String HP2 = com.StandardPhonNumber(HP);
        if (flag.equals("Insert")) {            
        }
        else if (flag.equals("approval")) {
            UM_Auth_Check uac = null;
            final UM_URB_CERT urc11 = new UM_URB_CERT();
            
            try {
                uac = new UM_Auth_Check(req, res);
                final String getID = uac.getID();
                Log.debug("1");
                trx = new Trx(this, "USEMN");
                con = trx.getConnection();
                con.setAutoCommit(false);
                
                if (sso.isLogin()) { 
            		//lay quyen truy cap tu co so du lieu
                    String testYnSql = "select user_cls FROM USEMN.UM_USER U3 where U3.USER_ID = ?";
                    CommDbQuery commDbQuery = new CommDbQuery();
                    String[] parameter = new String[1];
                    parameter[0] = ID;
                    OneRowEntity oneRow = commDbQuery.getOneRowList(this, testYnSql, parameter, con);
                    
                    if (ID != null && ("a".equals(oneRow.getDataFromName("user_cls")) || "k".equals(oneRow.getDataFromName("user_cls")))) {//vi dung chung phe duyet CTS them NT va MT nen kiem tra ca 2
            			/*if ("A000000000004".equals(ID)) {//TODO
            				Log.errors("Account cam phe duyet");
            				return;
            			}*/
            		} else{
            			Log.errors("Truy cap trai phep phe duyet");
            			return;
            		}
            	} else {
            		Log.errors("Truy cap trai phep phe duyet");
            		return;
            	}
                
                if (approval.equals("admit")) {
                	
                	boolean isBuyer = false;
                	
                    if (cert_cls.toLowerCase().equals("s")) {
                        if (urc010.isDataExistOnEnterMaster(COMNO, con) == 0) {
                            final String message = "Không thể thực hiện phê duyệt chứng nhận số cho các Doanh nghiệp chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.";
                            CommonMessage.printMsg("", "", message, null, res);
                            return;
                        }
                    } else {
	                	if (urc010.isDataExistOnGigwanMaster(G2BCODE, COMNO, con) == 0) {                    
	                                final String message = "Không thể thực hiện phê duyệt chứng nhận số cho các cơ quan chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.";
	                                CommonMessage.printMsg("", "", message, null, res);
	                        return;
                    	} else {
                    		isBuyer = true;
	                	}
                    }
                    
                    /**
                     * FIS 20161025 - Thuc hien sinh Ma phe quyet Chung thu so trong Buoc 3
                     * Start
                    */
                    Log.debug("$$$$$$$$$$$$$$$$$ UM_ADV_GovrA010c_pdnt Thuc hien sinh Ma phe duyet Chung thu so trong Buoc 3 recvNo = " + recept);
                    
                    Secu secu2 = null;
            		secu2 = new Secu(Secu.SE);
            		            		
            		String strZIPNO = ZIPNO;            		
            		String ret[] = { null, null, null };

            		IssueCertKICA iCK = new IssueCertKICA(secu2);
            		
            		iCK.setCaInfo("ppca.mpi.gov.vn", 4501); // (CA_IP, CA_PORT)
            		
            		iCK.setMpiCompanyPolicy(); //set certificate policy for company, institu
            		String strTELNO = TEL;
            		String strHP_NO = HP;
            		String strFAXNO = FAX;
            		
            		if (strTELNO.length() > 15) strTELNO = strTELNO.substring(0,15);
            		if (strHP_NO.length() > 15) strHP_NO = strHP_NO.substring(0,15);
            		if (strFAXNO.length() > 15) strFAXNO = strFAXNO.substring(0,15);

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

            		String[] res2;
            		//register user to ca
            		strNAME = stringReplace(strNAME);
            		//su dung tieng viet khong dau cho cts
            		strNAME = CommUtil.removeAccent(strNAME);
            		Log.debug(this.getClass().getName() + ": strNAME=" + strNAME + "; strTELNO=" + strTELNO + "; strHP_NO=" + strHP_NO + "; strFAXNO=" + strFAXNO);
            		if (isBuyer) {
            			//Ben moi thau 
            			//res = iCK.regNewUser(strNAME, strUSRID, strCOMNO, strEMAIL, strTELNO, strHP_NO, strFAXNO, "00", "ADDRESS", "COMPN", strNICKN);
            			res2 = iCK.regNewUser(strNAME, USRID, COMNO, EMAIL, strTELNO, strHP_NO, strFAXNO, "00", "ADDRESS", "COMPN", "SILVER");            			
            		} else {
            			//Neu la ben NT
            		//res = iCK.regNewUser(bizName, caUserId, bizRegNo2, bizAgentEmail, bizPhoneNo, bizMobile, bizFax, strZIPNO, addr, bizName, "hawk");
            			res2 = iCK.regNewUser(strNAME, USRID, COMNO, EMAIL, strTELNO, strHP_NO, strFAXNO, "00", "ADDRESS", "COMPN", "hawk");
            		}

            		if (res2 == null) {
            			throw new RuntimeException("Phê duyệt chứng thư số không thành công, không khởi tạo được chứng thư số mới.");
                    }
            		
            		String dn2 = res2[0];
            		String refername2 = res2[1];
            		String refercode2 = res2[2];

                    if (!refername2.equals("") || !refername2.equals("")) {
                                Log.debug("2");
                                urc010.updateReception("Y", "", recept, G2BCODE, dependCode, getID, con);
                        urc010.updateReceptionCert(dn2, refername2, refercode2, COMNO, G2BCODE, dependCode, con);                           
                    } else {
                    	throw new RuntimeException("Phê duyệt chứng thư số không thành công, không khởi tạo được chứng thư số mới.");
                    }

	               	/**
                     * FIS 20161025 - Thuc hien sinh Ma phe quyet Chung thu so trong Buoc 3
                     * End
                    */
//                    Log.debug("2"); chuuyen len truoc khi thay doi dependCode
//                    urc010.updateReception("Y", "", recept, G2BCODE, dependCode, getID, con);
                    Log.debug("3");
                    if (!cert_cls.toLowerCase().equals("s")) {
                        urc010.updateKNAME(G2BCODE, KNAME, con);
                    }
                    Log.debug("4");
                    if ("Y".equals(passwdmodOK)) {
                        urc11.updateUseUserPasswd(i07, gID, con);
                    }
                    Log.debug("5");
                    con.commit();
                    con.setAutoCommit(true);
                    
                    /**
                     * Thuc hien gui mail                      
                     */
                    if (isBuyer) {
                    	Log.info("PHE DUYET DANG KY THEM MT THANH CONG ---  GUI EMAIL START >>> ");                	
	                	//Gửi mail thông báo về mã phê duyệt CTS                    
	                    (new MailServer()).sendRegistrationEmailToMTStep2(EMAIL, MYNAME, CNAME, refercode2, refername2);                    
	                	Log.info("PHE DUYET DANG KY THEM MT THANH CONG ---  GUI EMAIL END >>> ");
                    } else {
	                    Log.info("PHE DUYET DANG KY THEM NT THANH CONG ---  GUI EMAIL START >>> ");                	
	                	//Gửi mail thông báo về mã phê duyệt CTS
	                    (new MailServer()).sendRegistrationEmailToNhaThauStep2(EMAIL, MYNAME, CNAME, refercode2, refername2);
	                	Log.info("PHE DUYET DANG KY THEM NT THANH CONG ---  GUI EMAIL END >>> ");
                    }
                    /**
                     * Ket thuc gui mail
                     */
                    Log.debug("6");
                    CommonMessage.printMsg("", "5", "Xử lý phê duyệt xong.", "", res);
                } else if (approval.equals("deny")) {
                    if (cert_cls.toLowerCase().equals("s")) {
                        if (urc010.isDataExistOnEnterMaster(COMNO, con) == 0) {
                            final String message = "Không thể thực hiện phê duyệt chứng nhận số cho các Doanh nghiệp chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.";
                            CommonMessage.printMsg("", "", message, null, res);
                            return;
                        }
                    } else {
                    	if (urc010.isDataExistOnGigwanMaster(G2BCODE, COMNO, con) == 0) {
                    
                                    final String message = "Không thể thực hiện phê duyệt chứng nhận số cho các cơ quan chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.";
                                    CommonMessage.printMsg("", "", message, null, res);
	                        return;
                    	}
                    }
                    urc010.updateReception("D", back, recept, G2BCODE, dependCode, getID, con);
                    con.commit();
                    con.setAutoCommit(true);
                    CommonMessage.printMsg("", "5", "Đã xử lý trả về.", "", res);
                    
                } else if (approval.equals("modifyCERT")) {
                    urc010.updateCERT_ORG(CERT_ORG, recept, G2BCODE, dependCode, con);
                    con.commit();
                    con.setAutoCommit(true);
                    CommonMessage.printMsg("", "5", "Cơ quan cấp chứng nhận đã được sửa.", "", res);
                }
            } catch (Exception exm2) {
                try {
                    if (con != null) {
                        con.rollback();
                    }
                }
                catch (Exception ex6) {}
                try {
                    if (con != null) {
                        con.setAutoCommit(true);
                    }
                }
                catch (Exception ex7) {}
                Log.debug("UM_ADV_GovrA010c_pdnt.java flag[" + flag + "] : " + exm2.toString());
                Log.errors(this, exm2, "UM_ADV_GovrA010c_pdnt.java flag[" + flag + "] : " + exm2.toString());
                CommonMessage.printMsg("", "", exm2.getMessage(), null, res);
            } finally {
                try {
                    if (con != null) {
                        trx.close();
                    }
                }
                catch (Exception ex8) {}
            }
        }
        else if (flag.equals("DNSave")) {
            
        }
        else if (flag.equals("Submit")) {
           
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
}
