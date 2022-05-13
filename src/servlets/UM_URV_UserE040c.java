// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import signgate.crypto.util.CertUtil;
import signgate.crypto.util.SignUtil;
import secu.lib.CertInfo;
import secu.lib.EnvelopedMessage;
import java.util.StringTokenizer;
import signgate.crypto.util.Base64Util;
import common.util.CommonMessage;
import common.Log;
import common.UseCookie;
import common.Trx;
import secu.lib.MessageDigest;
import LOGIN.UM_Auth_Check;
import beans.UM_URB_CERT;
import secu.lib.Secu;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_URV_UserE040c extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        final Secu secu = new Secu(1);
        final UM_URB_CERT urc = new UM_URB_CERT();
        UM_Auth_Check uac = null;
        try {
            uac = new UM_Auth_Check(req, res);
        }
        catch (Exception ex) {}
        Trx trx = null;
        Connection con = null;
        String saup = "";
        final String emallDN = "";
        String flag = "";
        String oper = "";
        String del = "";
        String DN1 = "";
        String DN2 = "";
        String CERT1 = "";
        String CERT2 = "";
        String ENC_RANDNUM = "";
        String ENC_KEY = "";
        String SIGN_LOGIN = "";
        final String randNo = "";
        String id = "";
        String d01 = "";
        String radio = "";
        String passwd = "";
        final String Scode = "";
        flag = ((req.getParameter("flag") == null) ? "" : req.getParameter("flag"));
        oper = ((req.getParameter("oper") == null) ? "" : req.getParameter("oper"));
        del = ((req.getParameter("del") == null) ? "" : req.getParameter("del"));
        DN1 = ((req.getParameter("DN1") == null) ? "" : req.getParameter("DN1"));
        DN2 = ((req.getParameter("DN2") == null) ? "" : req.getParameter("DN2"));
        CERT2 = ((req.getParameter("CERT2") == null) ? "" : req.getParameter("CERT2"));
        ENC_RANDNUM = ((req.getParameter("ENC_IDENT_INFO") == null) ? "" : req.getParameter("ENC_IDENT_INFO"));
        ENC_KEY = ((req.getParameter("ENC_KEY_INFO") == null) ? "" : req.getParameter("ENC_KEY_INFO"));
        SIGN_LOGIN = ((req.getParameter("SIGN_LOGIN_INFO") == null) ? "" : req.getParameter("SIGN_LOGIN_INFO"));
        id = ((req.getParameter("id") == null) ? "" : req.getParameter("id"));
        d01 = ((req.getParameter("d01") == null) ? "" : req.getParameter("d01"));
        radio = ((req.getParameter("radio") == null) ? "" : req.getParameter("radio"));
        passwd = ((req.getParameter("passwd") == null) ? "" : req.getParameter("passwd"));
        final String strMData1 = null;
        final MessageDigest md = new MessageDigest(secu);
        passwd = md.create(passwd);
        if (flag.equals("password")) {
            try {
                trx = new Trx(this);
                con = trx.getConnection();
                if (urc.isPasswordOk(id, passwd, oper, con) == 0) {
                    res.sendRedirect("/RA/UM_URJ_UserP010i.jsp?flag=fail&id=" + id + "&oper=" + oper + "&del=" + del + "&test=" + passwd);
                }
                else {
                    if (!oper.equals("c") && !oper.equals("x") && !oper.equals("d")) {
                        oper = "g";
                    }
                    new UseCookie(this).SetCookie(res, id, oper, del, "OK");
                    res.sendRedirect("/RA/UM_URJ_UserP010i.jsp?id=" + id + "&oper=" + oper + "&del=" + del + "&isFirst=N");
                }
                return;
            }
            catch (Exception exm) {
                Log.debug("UM_URV_UserE040c.java flag[" + flag + "] : " + exm.toString());
                CommonMessage.printMsg("", "", exm.getMessage(), null, res);
            }
            finally {
                try {
                    if (con != null) {
                        trx.close();
                    }
                }
                catch (Exception ex2) {}
            }
        }
        if (flag.equals("d")) {
            try {
                trx = new Trx(this);
                con = trx.getConnection();
                con.setAutoCommit(false);
                urc.insertCertLog(radio, con);
                urc.deleteCert(radio, con);
                con.commit();
                con.setAutoCommit(true);
                CommonMessage.printMsg("", "5", "Đã xóa xong.", "", res);
            }
            catch (Exception exm) {
                try {
                    if (con != null) {
                        con.rollback();
                    }
                }
                catch (Exception ex3) {}
                try {
                    if (con != null) {
                        con.setAutoCommit(true);
                    }
                }
                catch (Exception ex4) {}
                Log.debug("UM_URV_UserE040c.java flag[" + flag + "] : " + exm.toString());
                CommonMessage.printMsg("", "", exm.getMessage(), null, res);
            }
            finally {
                try {
                    if (con != null) {
                        trx.close();
                    }
                }
                catch (Exception ex5) {}
            }
            try {
                if (con != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
        else if (flag.equals("a")) {
            if (DN1.equals("")) {
                Log.debug("UM_URV_UserE040c.java Master code[" + d01 + "] Không có tên tài khoản của chứng nhận số và chữ ký điện tử của Client.");
                CommonMessage.printMsg("", "", "Không có tên tài khoản chứng nhận số dùng chữ ký số của client.", null, res);
                return;
            }
            if (DN2.equals("")) {
                Log.debug("UM_URV_UserE040c.java Mã Master[" + d01 + "] Không có tên tài khoản chứng nhận số mã hóa của Client.");
                CommonMessage.printMsg("", "", "Không có tên duy nhất Chứng nhận số dùng chữ ký số của client.", null, res);
                return;
            }
            if (CERT2.equals("")) {
                Log.debug("UM_URV_UserE040c.java Mã Master [" + d01 + "] Không có tên tài khoản chứng nhận số mã hóa của Client.");
                CommonMessage.printMsg("", "", "Không có Chứng nhận số dùng để mã hóa  client.", null, res);
                return;
            }
            if (ENC_RANDNUM.equals("")) {
                Log.debug("UM_URV_UserE040c.java Mã Master [" + d01 + "] Không có tên tài khoản chứng nhận số mã hóa của Client.");
                CommonMessage.printMsg("", "", "Không có thông tin xác nhận nhân thân của client.", null, res);
                return;
            }
            if (ENC_KEY.equals("")) {
                Log.debug("UM_URV_UserE040c.java Mã Master  [" + d01 + "] Không có tên tài khoản chứng nhận số mã hóa của Client.");
                CommonMessage.printMsg("", "", "Không có thông tin khóa của client.", null, res);
                return;
            }
            if (SIGN_LOGIN.equals("")) {
                Log.debug("UM_URV_UserE040c.java Mã Master  [" + d01 + "]  Không có tên tài khoản chứng nhận số mã hóa của Client.");
                CommonMessage.printMsg("", "", "Không có Thông tin đăng ký Chứng nhận số của client.", null, res);
                return;
            }
            String message = "";
            String strDTM = null;
            String strSignedMessage = null;
            String strOriginalMessage = null;
            try {
                trx = new Trx(this);
                con = trx.getConnection();
                con.setAutoCommit(false);
                String CertFromDate;
                String CertToDate;
                while (true) {
                    Label_1433: {
                        if (oper.equals("c") || oper.equals("x") || oper.equals("d")) {
                            break Label_1433;
                        }
                        saup = urc.getSaupjaNumber(d01, con);
                        if (saup != null) {
                            break Label_1433;
                        }
                        message = "Số đăng ký kinh doanh không được đăng ký trong hệ thống G2B do đó không kiểm tra được tính xác thực.";
                        CommonMessage.printMsg("", "", message, null, res);
                        con.setAutoCommit(true);
                        try {}
                        catch (Exception ex7) {}
                        return;
                    }
                    final String strstr = new String(Base64Util.decode(SIGN_LOGIN));
                    final StringTokenizer st = new StringTokenizer(strstr, "|");
                    CERT1 = st.nextToken();
                    strDTM = st.nextToken();
                    strSignedMessage = st.nextToken();
                    strOriginalMessage = String.valueOf(CERT1) + strDTM;
                    DN1 = new String(Base64Util.decode(DN1));
                    String strRandNo = null;
                    try {
                        final EnvelopedMessage se = new EnvelopedMessage(secu);
                        if (ENC_KEY == null || ENC_RANDNUM == null) {
                            throw new NullPointerException("Dữ liệu mã hóa đối tượng giải mã là null. Không thể là null.");
                        }
                        se.decryptInit(1, 1, ENC_KEY);
                        strRandNo = se.decryptUpdate(ENC_RANDNUM);
                        se.decryptFinal();
                    }
                    catch (Exception e) {
                        throw new Exception("Lỗi giải mã dòng ký tự đã được mã hóa.");
                    }
                    if (strRandNo == null) {
                        throw new Exception("Lỗi giải mã không có hiệu lực. Không thể là null.");
                    }
                    final CertInfo cu = new CertInfo(secu, CERT1);
                    CertFromDate = cu.getNotBefore();
                    CertToDate = cu.getNotAfter();
                    final String[] OID = { "1.2.410.200005.1.1.5", "1.2.410.200004.5.2.1.1", "1.2.410.200004.5.3.1.1", "1.2.410.200004.5.3.1.2", "1.2.410.200004.5.3.1.5", "1.2.410.200004.5.1.1.7", "1.2.410.200004.5.1.1.12", "1.2.410.200004.5.4.1.2", "1.2.410.200012.1.1.3", "1.2.410.200012.1.1.5", "1.2.410.100001.2.1.1", "1.2.410.100001.2.1.3", "1.2.410.200004.5.2.1.6.84", "1.2.410.200005.1.1.6.3", "1.2.410.200004.5.1.1.12.901", "1.2.410.200004.5.4.1.12", "1.2.704.100001.5.1.1.2" };
                    final String[] OID2 = { "1.2.410.200004.5.2.1.6.84", "1.2.410.200005.1.1.6.3", "1.2.410.200004.5.1.1.12.901", "1.2.410.200004.5.4.1.12", "1.2.704.100001.5.1.1.2" };
                    final boolean bChkOIDValid = cu.isValidPolicyOid(OID);
                    boolean isbChkOID = false;
                    isbChkOID = cu.isValidPolicyOid(OID2);
                    if (!isbChkOID) {
                        Log.debug("UM_URV_UserB010c.java Mã Master [" + d01 + "] ,Chứng nhận số [" + DN1 + "] không được chấp nhận. Xin vui lòng chắc chắn Chứng nhận số để sử dụng là Chứng nhận số dự phòng hay không.");
                        CommonMessage.printMsg("", "", "Đây là chứng nhận số không được phép dùng. Hãy sử dụng sau khi kiểm tra xem có phải là Chứng nhận số dự phòng hay không.", null, res);
                        con.setAutoCommit(true);
                        continue;
                    }
                    if (!bChkOIDValid) {
                        Log.debug("UM_URV_UserE040c.java Mã Master [" + d01 + "] ,Chứng nhận số [" + DN1 + "] không được chấp nhận. Xin vui lòng chắc chắn Chứng nhận số để sử dụng là Chứng nhận số dự phòng hay không.");
                        CommonMessage.printMsg("", "", "Đây là chứng nhận số không được phép dùng 2. Hãy sử dụng sau khi kiểm tra xem có phải là chứng nhận số dự phòng hay không.", null, res);
                        con.setAutoCommit(true);
                        continue;
                    }
                    if (!cu.isValid()) {
                        Log.debug("UM_URV_UserE040c.java Mã Master [" + d01 + "] ,Chứng nhận số [" + DN1 + "] xác nhận thất bại");
                        CommonMessage.printMsg("", "", "Không kiểm tra được hiệu lực của chứng nhận số. Có thể đây là chứng nhận số đã xóa hoặc đã hết hiệu lực sử dụng.", null, res);
                        con.setAutoCommit(true);
                        continue;
                    }
                    Label_2294: {
                        try {
                            if (strRandNo.compareTo("GPKI") == 0) {
                                if (!oper.equals("c") && !oper.equals("x") && !oper.equals("d")) {
                                    break Label_2294;
                                }
                                Log.debug("UM_URV_UserE040c.java Master code [" + d01 + "] ,Chứng nhận số [" + DN1 + "] là chứng nhận số không được phép sử dụng.Hãy kiểm tra lại cấp độ của chứng nhận số.");
                                CommonMessage.printMsg("", "", "Đây là chứng nhận số không được phép dùng. Hãy thực hiện sau khi kiểm tra cấp độ của chứng nhận số.<br>Không thể đăng ký chứng nhận số GPKI cho nhà thầu.", null, res);
                                con.setAutoCommit(true);
                            }
                            else {
                                final String dnName = DN1.substring(3, DN1.indexOf(","));
                                SignUtil sign = null;
                                sign = new SignUtil();
                                sign.verifyInit(CertUtil.pemToDer(CERT1));
                                sign.verifyUpdate(strOriginalMessage.getBytes());
                                if (sign.verifyFinal(Base64Util.decode(strSignedMessage))) {
                                    break Label_2294;
                                }
                                Log.debug("UM_URV_UserE040c.java Master code[" + d01 + "] ,Chứng nhận số[" + DN1 + "] không kiểm tra được chữ ký điện tử NPKI");
                                CommonMessage.printMsg("", "", "Chữ ký số NPKI xác nhận không thành công", null, res);
                                con.setAutoCommit(true);
                            }
                        }
                        catch (Exception e2) {
                            Log.debug("UM_URV_UserE040c.java Master Code[" + d01 + "] ,Chứng nhận số[" + DN1 + "] bị lỗi kiểm tra chữ ký điện tử và không xác minh được nhân thân");
                            CommonMessage.printMsg("", "", "Lỗi kiểm tra chữ ký số và xác minh nhân thân", null, res);
                            con.setAutoCommit(true);
                        }
                        continue;
                    }
                    if (urc.isDataExistOnCert(DN1, con) != 0) {
                        Log.debug("UM_URV_UserE040c.java Master code[" + d01 + "] ,Chứng nhận số[" + DN1 + "] đã được đăng ký");
                        CommonMessage.printMsg("", "", "Chứng nhận số này đã được đăng ký. Không cần đăng ký nữa.", null, res);
                        con.setAutoCommit(true);
                        continue;
                    }
                    break;
                }
                urc.insertUseCert(id, DN1, DN2, CERT1, CERT2, CertFromDate, CertToDate, con);
                urc.insertCertLog(id, String.valueOf(emallDN) + " emall삭제", con);
                con.commit();
                con.setAutoCommit(true);
                CommonMessage.printMsg("", "5", "Đăng ký xong.", "", res);
            }
            catch (Exception exm2) {
                try {
                    if (con != null) {
                        con.rollback();
                    }
                }
                catch (Exception ex8) {}
                try {
                    if (con != null) {
                        con.setAutoCommit(true);
                    }
                }
                catch (Exception ex9) {}
                Log.debug("UM_URV_UserE040c.java flag[" + flag + "] : " + exm2.toString());
                CommonMessage.printMsg("", "", exm2.getMessage(), null, res);
            }
            finally {
                try {
                    if (con != null) {
                        trx.close();
                    }
                }
                catch (Exception ex10) {}
            }
            try {
                if (con != null) {
                    trx.close();
                }
            }
            catch (Exception ex11) {}
        }
    }
}
