// 
// Decompiled by Procyon v0.5.30
// 

package LOGIN;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import common.OneRowEntity;
import java.sql.Connection;
import java.net.URLEncoder;
import common.LoginLog;
import secu.lib.SignAndEnvMessage;
import common.Trx;
import common.CommEntity;
import secu.lib.P7SignAndEnvMessage;
import signgate.crypto.util.Base64Util;
import signgate.crypto.util.GpkiCmsUtil;
import secu.lib.CertInfo;
import secu.lib.EnvelopedMessage;
import common.PublicUtil;
import secu.lib.Secu;
import common.Log;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class LoginCertManager extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Log.errors(5, "1");
        final boolean isTestOnlyOk = false;
        String tempSql1 = "";
        String tempSql2 = "";
        String CERTTF = "";
        final Secu secu = new Secu(2);
        final CertLocal certlocal = new CertLocal();
        final String USER_IP = CertLocal.getIP(req);
        final String loginType = PublicUtil.retSpace(req.getParameter("login_type"));
        if (!loginType.equalsIgnoreCase("C")) {
            Log.errors(5, String.valueOf(this.getClass().getName()) + ". IP[" + USER_IP + "],loginType[" + loginType + "], kiểu login không phải là C");
            LoginMessage.printMessage("002", "001", "LoginType[" + loginType + "]", "4", null, req, res);
            return;
        }
        Log.errors(5, "2");
        final String strUserSignCert = PublicUtil.retSpace(req.getParameter("sign_cert"));
        final String encKey = PublicUtil.retSpace(req.getParameter("enc_key"));
        final String encData = PublicUtil.retSpace(req.getParameter("enc_data"));
        final String encRandkey = PublicUtil.retSpace(req.getParameter("enc_randKey"));
        if (strUserSignCert.equals("")) {
            Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],strUserSignCert[" + strUserSignCert + "], Không có Chứng thư số dùng chữ ký số của client");
            LoginMessage.printMessage("002", "002", "", "4", null, req, res);
            return;
        }
        if (encKey.equals("")) {
            Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],encKey[" + encKey + "], Không có khóa session.");
            LoginMessage.printMessage("002", "003", "", "4", null, req, res);
            return;
        }
        if (encData.equals("")) {
            Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],encData[" + encData + "], Không có dữ liệu được mã hóa");
            LoginMessage.printMessage("002", "004", "", "4", null, req, res);
            return;
        }
        if (encRandkey.equals("")) {
            Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],encRandkey[" + encRandkey + "], Không có thông tin xác nhận nhân thân.");
            LoginMessage.printMessage("002", "005", "", "4", null, req, res);
            return;
        }
        String personid = PublicUtil.retSpace(req.getParameter("personid"));
        personid = "testpersonid";
        String SiteGubun = PublicUtil.retSpace(req.getParameter("SiteGubun"));
        SiteGubun = "testgubun";
        String loginOk = "http://muasamcong.mpi.gov.vn:8070/jsp/common/LoginCertSave.jsp?SiteGubun=" + SiteGubun;
        String strOrg = null;
        String strRandkey = null;
        try {
            final EnvelopedMessage se = new EnvelopedMessage(secu);
            if (encKey == null || encData == null) {
                Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],encKey[" + encKey + "], encData[" + encData + "], Đối tượng giải mã dữ liệu mã hóa là NULL");
                LoginMessage.printMessage("002", "006", "", "4", null, req, res);
                return;
            }
            se.decryptInit(1, 1, encKey);
            strOrg = se.decryptUpdate(encData);
            strRandkey = se.decryptUpdate(encRandkey);
            se.decryptFinal();
        }
        catch (Exception e) {
            Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],lỗi giải mã dòng ký tự đã được mã hóa : " + e.toString());
            LoginMessage.printMessage("002", "000", e.getMessage(), "4", null, req, res);
            return;
        }
        if (strOrg == null) {
            Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],strOrg[" + strOrg + "] lỗi giải mã không có hợp lệ.");
            LoginMessage.printMessage("002", "000", "Đây là lỗi giải mã không hợp lệ. Không thể là null", "4", null, req, res);
            return;
        }
        CertInfo cu = null;
        try {
            cu = new CertInfo(secu, strUserSignCert);
            final String[] OID = secu.getCertOID();
            final boolean bChkOIDValid = cu.isValidPolicyOid(OID);
            if (!bChkOIDValid) {
                Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],bChkOIDValid ==false Chứng thư số không được phép dùng.");
                LoginMessage.printMessage("002", "007", cu.getErrorMsg(), "4", null, req, res);
                return;
            }
            if (!cu.isValid()) {
                Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],isValid ==false  kiểm tra Chứng thư số thất bại.");
                LoginMessage.printMessage("002", "008", cu.getErrorMsg(), "4", null, req, res);
                return;
            }
        }
        catch (Exception e2) {
            Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],lỗi kiểm tra chính sách Chứng thư số : " + e2.toString());
            LoginMessage.printMessage("002", "000", "lỗi kiểm tra chính sách Chứng thư số<br>" + e2.getMessage(), "4", null, req, res);
            return;
        }
        String userDN = null;
        String userID = null;
        
        if (strRandkey.compareTo("GPKI") == 0) {
            try {
            	// Kiểm tra chữ ký P7
                final GpkiCmsUtil gpkicms = new GpkiCmsUtil();
                String transStrOrg = null;
                try {
                    transStrOrg = strOrg.substring(strOrg.indexOf("\n") + 1, strOrg.lastIndexOf("-----END PKCS7-----")).trim();
                }
                catch (Exception e3) {
                    Log.errors(5, String.valueOf(this.getClass().getName()) + "Lỗi khi kiểm tra.GPKI P7  : IP[" + USER_IP + "], strOrg[" + strOrg + "]:" + e3.toString());
                    LoginMessage.printMessage("002", "000", "Lỗi khi kiểm tra GPKI P7 <br>" + e3.getMessage(), "4", null, req, res);
                    return;
                }
                if (!gpkicms.verifySigned(Base64Util.decode(transStrOrg))) {
                    Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],Chứng thư số không có giá trị, kiểm tra GPKI P7 ");
                    LoginMessage.printMessage("002", "009", cu.getErrorMsg(), "4", null, req, res);
                    return;
                }
                final String secondStrOrg = gpkicms.getOrgData();
                if (secondStrOrg == null) {
                    Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],secondStrOrg[" + secondStrOrg + "], lỗi khi kiểm tra GPKI P7 ");
                    LoginMessage.printMessage("002", "009", cu.getErrorMsg(), "4", null, req, res);
                    return;
                }
                userDN = certlocal.getDN(secondStrOrg);
                Log.debug(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "]");
            }
            catch (Exception e4) {
                Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "], lỗi khi kiểm tra GPKI P7:" + e4.toString());
                LoginMessage.printMessage("002", "000", "lỗi kiểm tra GPKI P7SignAndEnvMessage <br>" + e4.getMessage(), "4", null, req, res);
                return;
            }
        } else {
        	// NPKI Cert
	        try {
	            final P7SignAndEnvMessage p7 = new P7SignAndEnvMessage(secu);
	            final String thirdStrOrg2 = p7.verify(1, 1, strOrg);
	            if (thirdStrOrg2 == null) {
	                Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],thirdStrOrg2[" + thirdStrOrg2 + "] lỗi chữ ký số NPKI P7 ");
	                LoginMessage.printMessage("002", "009", cu.getErrorMsg(), "4", null, req, res);
	                return;
	            }
	            userDN = certlocal.getDN(thirdStrOrg2);
	        }
	        catch (Exception e4) {
	            Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "], lỗi khi kiểm tra NPKI P7 :" + e4.toString());
	            LoginMessage.printMessage("002", "000", "Lỗi kiểm tra NPKI P7SignAndEnvMessage <br>" + e4.getMessage(), "4", null, req, res);
	            return;
	        }
        }
        // 인증서 고유명을 얻지 못함
     	// Không nhận được tên duy nhất Chứng thư số
        if (userDN == null || userDN.equals("")) {
            Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "] DN 명 얻기 실패");
            LoginMessage.printMessage("002", "010", "", "4", null, req, res);
            return;
        }
        // DB 관련 변수
     	// Biến số liên quan đến DB
        Trx resource = null;
        Connection conn = null;
        CommEntity[] userInfoEntity = (CommEntity[])null;
        CommEntity[] userHiddenEntity = (CommEntity[])null;
        try {
            resource = new Trx((Object)this, "USEMN");
            conn = resource.getConnection();
            //kiem tra them serial number
            tempSql1 = "";//" AND (U2.SERIAL_NO = '" + cu.getSerialNumber() + "' OR U2.SERIAL_NO IS NULL)";
            tempSql2 = "";//" AND (U5.SERIAL_NO = '" + cu.getSerialNumber() + "' OR U5.SERIAL_NO IS NULL)";
            userInfoEntity = certlocal.getCertUserInfo(userDN, tempSql1, tempSql2, conn);
            
            if (userInfoEntity.length == 0) {
                Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "] khi login bằng Chứng thư số chưa được đăng ký");
                LoginMessage.printMessage("002", "011", "Tên Chứng thư số:[" + userDN + "]", "4", null, req, res);
                //thoat chuong trinh
            } 
            else {
                
			    userHiddenEntity = certlocal.getCertUserHidden(userDN, tempSql1, tempSql2, conn);
			    if (userHiddenEntity.length == 0) {
			        Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "] khi login bằng Chứng thư số đã bị ẩn dữ liệu");
			        LoginMessage.printMessage("003", "028", "Tên Chứng thư số:[" + userDN + "]", "4", null, req, res);
			        //thoat chuong trinh
			    } 
			    else {
			    
				    // 승인여부 2 인지 아닌지 체크
				 	// check xem tùy chọn phê duyệt là 2 hay không
				    final String approveStatus = PublicUtil.retSpace(userInfoEntity[0].data[12]);
				    if (!approveStatus.equals("2")) {
				        Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "] tùy chọn phê duyệt Chứng thư số là 9");
				        LoginMessage.printMessage("002", "012", "tên Chứng thư số:[" + userDN + "]", "4", null, req, res);
				        //thoat chuong trinh
				    }
				    else {
				                
					    String saup_no = "";
					    String test_yn = "";
					    String[] values = (String[])null;
					    final String comment = null;
					    final String domain = "muasamcong.mpi.gov.vn";
					    final int maxAge = -1;
					    final String path = "/";
					    final boolean secure = false;
					    final int version = 0;
					    int seq = 0;
					    values = new String[CertLocal.certvalue.length];
					    userID = userInfoEntity[0].data[0];
					    values[seq++] = userInfoEntity[0].data[0];
					    values[seq++] = userInfoEntity[0].data[1];
					    values[seq++] = userInfoEntity[0].data[2];
					    values[seq++] = userInfoEntity[0].data[3];
					    values[seq++] = userInfoEntity[0].data[4];
					    values[seq++] = userInfoEntity[0].data[5];
					    values[seq++] = userInfoEntity[0].data[6];
					    values[seq++] = userInfoEntity[0].data[7];
					    values[seq++] = userInfoEntity[0].data[8];
					    values[seq++] = userInfoEntity[0].data[9];
					    values[seq++] = new Long(System.currentTimeMillis()).toString();
					    values[seq++] = USER_IP;
					    saup_no = PublicUtil.retSpace(userInfoEntity[0].data[10]);
					    test_yn = PublicUtil.retSpace(userInfoEntity[0].data[11]);
					    
					    if (strRandkey.compareTo("GPKI") != 0) {
					        final String dnName = userDN.substring(3, userDN.indexOf(","));
					        // dnName은 신원확인과정에서 Null 값을 허용하기때문에 빼고 처리 - 20071213 - 반영
					        //  Vì dnName cho phép giá trị Null trong quá trình kiểm tra nhân thân nên bỏ
					        if (!test_yn.equals("Y") && values[1].equals("c") && !dnName.equals("G2B doanh nghiệp 154") && !cu.ValidateUserID(saup_no, "", strRandkey)) {
					            CERTTF = "N";// 신원확인실패가 발생하면 N 을 넘김 nếu xác nhận thất bại thì chuyển N
					            Log.errors(4, "[LoginCertManager.java]xác nhận thất bại : [userID:" + userID + "] [số ĐKKD:" + saup_no + "] [userDN:" + userDN + "] [strRandkey:" + strRandkey + "]");
					            
					            // 로그인시 신원확인 모듈적용에 따른 인증기관별 메세지 표기
								//Hiển thi mess theo từng cơ quan chứng nhận tùy theo sự áp dụng module xác nhận nhân thân khi login
					            if (userDN.indexOf("o=NCASign") != -1) {
					                if (strRandkey.compareTo("") == 0) {
					                    LoginMessage.printMessage("002", "014", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);                            
					                } else {
					                	LoginMessage.printMessage("002", "015", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);                        	
					                }					                
					            } else if (userDN.indexOf("o=TradeSign") != -1) {
					                if (strRandkey.compareTo("") == 0) {
					                    LoginMessage.printMessage("002", "016", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);                            
					                } else {
					                	LoginMessage.printMessage("002", "017", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);                        	
					                }					                
					            } else if (userDN.indexOf("o=CrossCert") != -1) {
					                if (strRandkey.compareTo("") == 0) {
					                    LoginMessage.printMessage("002", "018", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);                            
					                } else {
					                	LoginMessage.printMessage("002", "019", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);                        	
					                }
					            } else if (userDN.indexOf("o=yessign") != -1) {
					                if (strRandkey.compareTo("") == 0) {
					                    LoginMessage.printMessage("002", "020", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);                            
					                } else {
					                	LoginMessage.printMessage("002", "021", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);
					                }
					            }
					            else if (userDN.indexOf("o=SignKorea") != -1) {
					                if (strRandkey.compareTo("") == 0) {
					                    LoginMessage.printMessage("002", "022", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);                            
					                } else {
					                	LoginMessage.printMessage("002", "023", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);
					                }
					            }
					            else if (userDN.indexOf("o=KICA") != -1 || userDN.indexOf("o=kica") != -1) {
					                if (strRandkey.compareTo("") == 0) {
					                    LoginMessage.printMessage("002", "024", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);                            
					                } else {
					                	LoginMessage.printMessage("002", "025", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);
					                }
					            }
					            else {
						            if (strRandkey.compareTo("") == 0) {
						                LoginMessage.printMessage("002", "026", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);                        
						            } else {
						            	LoginMessage.printMessage("002", "027", "[Tên Chứng thư số] : [" + userDN + "]<br>[số ĐKKD] : [" + saup_no + "]", "4", null, req, res);
						            }
					            }
					            //thoat khoi chuong trinh
					            return;
					        }
					    }
					    
					    /********************************************************************************************/
					    final String strToken = certlocal.getStringToken(values, loginType);
					    String loginKey = null;
					    String loginToken = null;
					    String loginSign = null;
                    
                        try {
                            final SignAndEnvMessage se2 = new SignAndEnvMessage(secu);
                            final String pemKmCert = secu.getPemKmCertInfo(1);
                            loginKey = se2.encryptInit(1, 0, 1, pemKmCert.trim(), encKey.trim());
                            if (loginKey == null) {
                                Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "] không mã hóa được chứng nhận người dùng [encryptInit]");
                                LoginMessage.printMessage("002", "000", " không mã hóa được chứng nhận người dùng[encryptInit]", "4", null, req, res);
                            }
                            else {
                                loginToken = se2.encryptUpdate(strToken);
                                if (loginToken == null) {
                                    Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "]  không mã hóa được chứng nhận người dùng[encryptUpdate]");
                                    LoginMessage.printMessage("002", "000", " không mã hóa được chứng nhận người dùng[encryptUpdate]", "4", null, req, res);
                                }
                                else {
                                    loginSign = se2.encryptFinal();
                                    if (loginSign != null) {
                                      
                                    } else {
	                                    Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "]  không mã hóa được chứng nhận người dùng[encryptFinal]");
	                                    LoginMessage.printMessage("002", "000", " không mã hóa được chứng nhận người dùng[encryptFinal]", "4", null, req, res);
	                                    //thoat khoi chuong trinh
	                                    return;
                                    }
                                }
                            }
                        }
                        catch (Exception e5) {
                            e5.printStackTrace();
                            Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "] không mã hóa được:" + e5.toString());
                            LoginMessage.printMessage("002", "000", " không mã hóa được chứng nhận người dùng:" + e5.getMessage(), "4", null, req, res);
                            //thoat khoi chuong trinh
                            return;
                        }
                        catch (Throwable e6) {
                            e6.printStackTrace();
                            Log.errors(5, String.valueOf(this.getClass().getName()) + ".IP[" + USER_IP + "],userDN[" + userDN + "] không mã hóa được:" + e6.toString());
                            LoginMessage.printMessage("002", "000", " không mã hóa được chứng nhận người dùng:" + e6.getMessage(), "4", null, req, res);
                            //thoat khoi chuong trinh
                            return;
                        }                        
                    
	                    if (userInfoEntity[0].data[14].toString().equals("1")) {
	                        LoginMessage.printMessage("001", "028", "", "4", null, req, res);
	                        //thoat khoi chuong trinh
	                    }
	                    else {
	                        certlocal.setCookies(res, comment, domain, maxAge, path, secure, version, new String[] { loginToken, loginKey, loginSign }, true);
	                        certlocal.updateLastLoginTime(cu.getNotBefore(), cu.getNotAfter(), userDN, cu.getSerialNumber(), conn);
	                        String filename = "";
	                        filename = "testfile";
	                        CERTTF = "Y";
	                        loginOk = String.valueOf(loginOk) + "&filename=" + filename + "&CERTTF=" + CERTTF;
	                        LoginLog.write("<login thành công:" + USER_IP + "> login_type[" + loginType + "],userDN[" + userDN + "],userID[" + userID + "]");
	                        OneRowEntity policyEntity = null;
	                        policyEntity = certlocal.getPaymentPolicy(conn);
	                        boolean matchType = false;
	                        if (policyEntity.getDataFromName("REG_PAYMENT_TYPE").toString().equals("1")) {
	                            if (userInfoEntity[0].data[1].toString().toLowerCase().equals("g")) {
	                                matchType = true;
	                            }
	                        }
	                        else if (policyEntity.getDataFromName("REG_PAYMENT_TYPE").toString().equals("2")) {
	                        	Log.info("ThachPN - REG_PAYMENT_TYPE=" + policyEntity.getDataFromName("REG_PAYMENT_TYPE") + ", test_yn=" + test_yn);
	                            if (userInfoEntity[0].data[1].toString().toLowerCase().equals("c") && !"Y".equals(test_yn)) {//Khong kiem tra payment voi nha thau thu nghiem
	                            	Log.info("ThachPN - Update matchType");
	                                matchType = true;
	                            }
	                        }
	                        else if (policyEntity.getDataFromName("REG_PAYMENT_TYPE").toString().equals("3")) {
	                            if (userInfoEntity[0].data[1].toString().toLowerCase().equals("g")) {
	                                matchType = true;
	                            }
	                            if (userInfoEntity[0].data[1].toString().toLowerCase().equals("c")) {
	                                matchType = true;
	                            }
	                        }
	                        final String onPm = policyEntity.getDataFromName("ONLINE_PAYMENT").toString();
	                        final int hasSubUser = certlocal.getSubUserCount(conn, userID);
	                        final String password2 = PublicUtil.retSpace(userInfoEntity[0].data[13]);
	                        String pwUrl = "";
	                        String pmUrl = "";
	                        String suUrl = "";
	                        String cInfo = "";
	                        cInfo = cInfo.concat(PublicUtil.retSpace(comment)).concat("$");
	                        cInfo = cInfo.concat(PublicUtil.retSpace(domain)).concat("$");
	                        cInfo = cInfo.concat(Integer.toString(maxAge)).concat("$");
	                        cInfo = cInfo.concat(PublicUtil.retSpace(path)).concat("$");
	                        cInfo = cInfo.concat(Boolean.toString(secure)).concat("$");
	                        cInfo = cInfo.concat(Integer.toString(version));
	                        Log.info("ThachPN - matchType=" + matchType);
	                        //18/08/2016 - Yeu cau tu Cuc QLDT van cho Login binh thuong neu nhu chua dong phi 
	                        if (matchType) {
	                            try {
	                                OneRowEntity recvnoEntity = null;
	                                OneRowEntity pmEntity = null;
	                                if (!policyEntity.getDataFromName("EXTEND_PAYMENT_TYPE").toString().equals("0")) {
	                                    recvnoEntity = certlocal.getRecvNo(userInfoEntity[0].data[2].toString(), userDN, conn);
	                                    pmEntity = certlocal.getPaymentItemListByBizRegNo(recvnoEntity.getDataFromName("BIZ_REG_NO").toString(), policyEntity.getDataFromName("EFFECT_MONTHS").toString(), policyEntity.getDataFromName("EXTEND_REMIND_MSG_DAYS").toString(), policyEntity.getDataFromName("EXTEND_START_DT").toString(), conn);
	                                    final String pmDate = pmEntity.getDataFromName("PM_DATE").toString();
	                                    final String pmToPaid = pmEntity.getDataFromName("TO_PAID").toString();
	                                    final String pmToMsg = pmEntity.getDataFromName("TO_MSG").toString();
	                                    String remindMsg = policyEntity.getDataFromName("EXTEND_REMIND_MSG").toString();
	                                    String paymentMsg = policyEntity.getDataFromName("EXTEND_PAYMENT_MSG").toString();
	                                    remindMsg = remindMsg.replaceAll("<NGAY_GIA_HAN>", pmDate);
	                                    paymentMsg = paymentMsg.replaceAll("<NGAY_GIA_HAN>", pmDate);
	                                    if (policyEntity.getDataFromName("EXTEND_PAYMENT_TYPE").toString().equals("1")) {
	                                        if (Integer.parseInt(pmToPaid) <= 0) {
	                                            pmUrl = "http://muasamcong.mpi.gov.vn:8070/Payment.jsp?dispmsg=" + URLEncoder.encode(paymentMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=3&isMsg=1&initForm=1&cInfo=null&recNo=" + recvnoEntity.getDataFromName("RECV_NO").toString() + "&BizRegNo=" + recvnoEntity.getDataFromName("BIZ_REG_NO").toString() + "&onPm=" + onPm;
	                                            if (!password2.equals("")) {
	                                                pwUrl = "http://muasamcong.mpi.gov.vn:8070/Password2.jsp?dispmsg=" + URLEncoder.encode(paymentMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=3&isMsg=1&initForm=1&cInfo=" + cInfo + "&recNo=" + recvnoEntity.getDataFromName("RECV_NO").toString() + "&BizRegNo=" + recvnoEntity.getDataFromName("BIZ_REG_NO").toString() + "&matchType=1&p2=" + password2;
	                                            }
	                                            if (hasSubUser > 0) {
	                                                suUrl = "http://muasamcong.mpi.gov.vn:8070/SubUserLogin.jsp?dispmsg=" + URLEncoder.encode(paymentMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=3&isMsg=1&initForm=1&cInfo=" + cInfo + "&recNo=" + recvnoEntity.getDataFromName("RECV_NO").toString() + "&BizRegNo=" + recvnoEntity.getDataFromName("BIZ_REG_NO").toString() + "&matchType=1&p2=" + password2 + "&uid=" + userID;
	                                            }
	                                        }
	                                        else if (Integer.parseInt(pmToMsg) <= 0) {
	                                            pmUrl = "http://muasamcong.mpi.gov.vn:8070/Payment.jsp?dispmsg=" + URLEncoder.encode(remindMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=1&isMsg=1&initForm=1&cInfo=null&recNo=null&BizRegNo=null&onPm=" + onPm;
	                                            if (!password2.equals("")) {
	                                                pwUrl = "http://muasamcong.mpi.gov.vn:8070/Password2.jsp?dispmsg=" + URLEncoder.encode(remindMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=1&isMsg=1&initForm=1&cInfo=" + cInfo + "&recNo=null&BizRegNo=null&matchType=1&p2=" + password2;
	                                            }
	                                            if (hasSubUser > 0) {
	                                                suUrl = "http://muasamcong.mpi.gov.vn:8070/SubUserLogin.jsp?dispmsg=" + URLEncoder.encode(remindMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=1&isMsg=1&initForm=1&cInfo=" + cInfo + "&recNo=null&BizRegNo=null&matchType=1&p2=" + password2 + "&uid=" + userID;
	                                            }
	                                        }
	                                    }
	                                    else if (Integer.parseInt(pmToPaid) <= 0) {
	                                        pmUrl = "http://muasamcong.mpi.gov.vn:8070/Payment.jsp?dispmsg=" + URLEncoder.encode(paymentMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=3&isMsg=0&initForm=1&cInfo=" + cInfo + "&recNo=" + recvnoEntity.getDataFromName("RECV_NO").toString() + "&BizRegNo=" + recvnoEntity.getDataFromName("BIZ_REG_NO").toString() + "&onPm=" + onPm;
	                                        if (!password2.equals("")) {
	                                            pwUrl = "http://muasamcong.mpi.gov.vn:8070/Password2.jsp?dispmsg=" + URLEncoder.encode(paymentMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=3&isMsg=0&initForm=1&cInfo=" + cInfo + "&recNo=" + recvnoEntity.getDataFromName("RECV_NO").toString() + "&BizRegNo=" + recvnoEntity.getDataFromName("BIZ_REG_NO").toString() + "&matchType=1&p2=" + password2;
	                                        }
	                                        if (hasSubUser > 0) {
	                                            suUrl = "http://muasamcong.mpi.gov.vn:8070/SubUserLogin.jsp?dispmsg=" + URLEncoder.encode(paymentMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=3&isMsg=0&initForm=1&cInfo=" + cInfo + "&recNo=" + recvnoEntity.getDataFromName("RECV_NO").toString() + "&BizRegNo=" + recvnoEntity.getDataFromName("BIZ_REG_NO").toString() + "&matchType=1&p2=" + password2 + "&uid=" + userID;
	                                        }
	                                    }
	                                    else if (Integer.parseInt(pmToMsg) <= 0) {
	                                        pmUrl = "http://muasamcong.mpi.gov.vn:8070/Payment.jsp?dispmsg=" + URLEncoder.encode(paymentMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=1&isMsg=1&initForm=1&cInfo=null&recNo=null&BizRegNo=null&onPm=" + onPm;
	                                        if (!password2.equals("")) {
	                                            pwUrl = "http://muasamcong.mpi.gov.vn:8070/Password2.jsp?dispmsg=" + URLEncoder.encode(paymentMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=1&isMsg=1&initForm=1&cInfo=" + cInfo + "&recNo=null&BizRegNo=null&matchType=1&p2=" + password2;
	                                        }
	                                        if (hasSubUser > 0) {
	                                            suUrl = "http://muasamcong.mpi.gov.vn:8070/SubUserLogin.jsp?dispmsg=" + URLEncoder.encode(paymentMsg) + "&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=1&isMsg=1&initForm=1&cInfo=" + cInfo + "&recNo=null&BizRegNo=null&matchType=1&p2=" + password2 + "&uid=" + userID;
	                                        }
	                                    }
	                                }
	                                else {
	                                    if (!password2.equals("")) {
	                                        pwUrl = "http://muasamcong.mpi.gov.vn:8070/Password2.jsp?dispmsg=null&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=1&isMsg=1&initForm=1&cInfo=" + cInfo + "&recNo=null&BizRegNo=null&matchType=0&p2=" + password2;
	                                    }
	                                    if (hasSubUser > 0) {
	                                        suUrl = "http://muasamcong.mpi.gov.vn:8070/SubUserLogin.jsp?dispmsg=null&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=1&isMsg=1&initForm=1&cInfo=" + cInfo + "&recNo=null&BizRegNo=null&matchType=0&p2=" + password2 + "&uid=" + userID;
	                                    }
	                                }
	                            }
	                            catch (Exception ex) {
	                                Log.debug(ex.toString());
	                                Log.errors(5, String.valueOf(this.getClass().getName()) + ". DN[" + userDN + "],IP[" + USER_IP + "]:" + ex.toString());
	                            }
	                        }
	                        else {
	                            if (!password2.equals("")) {
	                                pwUrl = "http://muasamcong.mpi.gov.vn:8070/Password2.jsp?dispmsg=null&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=1&isMsg=1&initForm=1&cInfo=" + cInfo + "&recNo=null&BizRegNo=null&matchType=0&p2=" + password2;
	                            }
	                            if (hasSubUser > 0) {
	                                suUrl = "http://muasamcong.mpi.gov.vn:8070/SubUserLogin.jsp?dispmsg=null&filename=" + filename + "&CERTTF=" + CERTTF + "&pmStyle=1&isMsg=1&initForm=1&cInfo=" + cInfo + "&recNo=null&BizRegNo=null&matchType=0&p2=" + password2 + "&uid=" + userID;
	                            }
	                        }
	                        if (!pwUrl.equals("") || !suUrl.equals("") || !pmUrl.equals("")) {
	                            certlocal.setTempCookies(res, comment, domain, maxAge, path, secure, version, new String[] { loginToken, loginKey, loginSign }, true);
	                        }
	                        
	                        Log.info("ThachPN - pwUrl =" + pwUrl);
	                        
	                        if (!pwUrl.equals("")) {
	                            res.sendRedirect(pwUrl);
	                        }
	                        Log.info("ThachPN - suUrl =" + suUrl);
	                        if (!suUrl.equals("")) {
	                            res.sendRedirect(suUrl);
	                        }
	                        
	                        // ThachPN - Bỏ qua kiểm tra đóng phí
	                        /** 
	                        if (!pmUrl.equals("")) {
	                            res.sendRedirect(pmUrl);
	                        }
	                        */
	                        final StringBuffer strJava = new StringBuffer();
	                        strJava.append("<script language='javascript'>");
	                        strJava.append("function run() {");
	                        strJava.append("if(opener!=null) {");
	                        strJava.append("\topener.top.tops.location.reload(true);");
	                        strJava.append("\tlocation.href='" + loginOk + "';");
	                        strJava.append("} else {");
	                        strJava.append("\ttop.tops.location.reload(true);");
	                        strJava.append("\ttop.main.location='" + loginOk + "';");
	                        strJava.append("\t }");
	                        strJava.append("\t return;");
	                        strJava.append("\t }");
	                        strJava.append("\t run();");
	                        strJava.append("</script>");
	                        final PrintWriter out = res.getWriter();
	                        out.print(strJava);
	                    }
	                }
	            }
            }
        }
        catch (Exception exm) {
            Log.errors(5, String.valueOf(this.getClass().getName()) + ". DN[" + userDN + "],IP[" + USER_IP + "]:" + exm.toString());
            LoginMessage.printMessage("002", "000", exm.getMessage(), "4", null, req, res);
        }
        catch (Throwable exm2) {
            Log.errors(5, String.valueOf(this.getClass().getName()) + ". DN[" + userDN + "],IP[" + USER_IP + "]:" + exm2.toString());
            LoginMessage.printMessage("002", "000", exm2.getMessage(), "4", null, req, res);
        }
        finally {
            if (conn != null) {
                try {
                    resource.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
}
