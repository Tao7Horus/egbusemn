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
                CommonMessage.printMsg("", "5", "???? x??a xong.", "", res);
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
                Log.debug("UM_URV_UserE040c.java Master code[" + d01 + "] Kh??ng c?? t??n t??i kho???n c???a ch???ng nh???n s??? v?? ch??? k?? ??i???n t??? c???a Client.");
                CommonMessage.printMsg("", "", "Kh??ng c?? t??n t??i kho???n ch???ng nh???n s??? d??ng ch??? k?? s??? c???a client.", null, res);
                return;
            }
            if (DN2.equals("")) {
                Log.debug("UM_URV_UserE040c.java M?? Master[" + d01 + "] Kh??ng c?? t??n t??i kho???n ch???ng nh???n s??? m?? h??a c???a Client.");
                CommonMessage.printMsg("", "", "Kh??ng c?? t??n duy nh???t Ch???ng nh???n s??? d??ng ch??? k?? s??? c???a client.", null, res);
                return;
            }
            if (CERT2.equals("")) {
                Log.debug("UM_URV_UserE040c.java M?? Master [" + d01 + "] Kh??ng c?? t??n t??i kho???n ch???ng nh???n s??? m?? h??a c???a Client.");
                CommonMessage.printMsg("", "", "Kh??ng c?? Ch???ng nh???n s??? d??ng ????? m?? h??a  client.", null, res);
                return;
            }
            if (ENC_RANDNUM.equals("")) {
                Log.debug("UM_URV_UserE040c.java M?? Master [" + d01 + "] Kh??ng c?? t??n t??i kho???n ch???ng nh???n s??? m?? h??a c???a Client.");
                CommonMessage.printMsg("", "", "Kh??ng c?? th??ng tin x??c nh???n nh??n th??n c???a client.", null, res);
                return;
            }
            if (ENC_KEY.equals("")) {
                Log.debug("UM_URV_UserE040c.java M?? Master  [" + d01 + "] Kh??ng c?? t??n t??i kho???n ch???ng nh???n s??? m?? h??a c???a Client.");
                CommonMessage.printMsg("", "", "Kh??ng c?? th??ng tin kh??a c???a client.", null, res);
                return;
            }
            if (SIGN_LOGIN.equals("")) {
                Log.debug("UM_URV_UserE040c.java M?? Master  [" + d01 + "]  Kh??ng c?? t??n t??i kho???n ch???ng nh???n s??? m?? h??a c???a Client.");
                CommonMessage.printMsg("", "", "Kh??ng c?? Th??ng tin ????ng k?? Ch???ng nh???n s??? c???a client.", null, res);
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
                        message = "S??? ????ng k?? kinh doanh kh??ng ???????c ????ng k?? trong h??? th???ng G2B do ???? kh??ng ki???m tra ???????c t??nh x??c th???c.";
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
                            throw new NullPointerException("D??? li???u m?? h??a ?????i t?????ng gi???i m?? l?? null. Kh??ng th??? l?? null.");
                        }
                        se.decryptInit(1, 1, ENC_KEY);
                        strRandNo = se.decryptUpdate(ENC_RANDNUM);
                        se.decryptFinal();
                    }
                    catch (Exception e) {
                        throw new Exception("L???i gi???i m?? d??ng k?? t??? ???? ???????c m?? h??a.");
                    }
                    if (strRandNo == null) {
                        throw new Exception("L???i gi???i m?? kh??ng c?? hi???u l???c. Kh??ng th??? l?? null.");
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
                        Log.debug("UM_URV_UserB010c.java M?? Master [" + d01 + "] ,Ch???ng nh???n s??? [" + DN1 + "] kh??ng ???????c ch???p nh???n. Xin vui l??ng ch???c ch???n Ch???ng nh???n s??? ????? s??? d???ng l?? Ch???ng nh???n s??? d??? ph??ng hay kh??ng.");
                        CommonMessage.printMsg("", "", "????y l?? ch???ng nh???n s??? kh??ng ???????c ph??p d??ng. H??y s??? d???ng sau khi ki???m tra xem c?? ph???i l?? Ch???ng nh???n s??? d??? ph??ng hay kh??ng.", null, res);
                        con.setAutoCommit(true);
                        continue;
                    }
                    if (!bChkOIDValid) {
                        Log.debug("UM_URV_UserE040c.java M?? Master [" + d01 + "] ,Ch???ng nh???n s??? [" + DN1 + "] kh??ng ???????c ch???p nh???n. Xin vui l??ng ch???c ch???n Ch???ng nh???n s??? ????? s??? d???ng l?? Ch???ng nh???n s??? d??? ph??ng hay kh??ng.");
                        CommonMessage.printMsg("", "", "????y l?? ch???ng nh???n s??? kh??ng ???????c ph??p d??ng 2. H??y s??? d???ng sau khi ki???m tra xem c?? ph???i l?? ch???ng nh???n s??? d??? ph??ng hay kh??ng.", null, res);
                        con.setAutoCommit(true);
                        continue;
                    }
                    if (!cu.isValid()) {
                        Log.debug("UM_URV_UserE040c.java M?? Master [" + d01 + "] ,Ch???ng nh???n s??? [" + DN1 + "] x??c nh???n th???t b???i");
                        CommonMessage.printMsg("", "", "Kh??ng ki???m tra ???????c hi???u l???c c???a ch???ng nh???n s???. C?? th??? ????y l?? ch???ng nh???n s??? ???? x??a ho???c ???? h???t hi???u l???c s??? d???ng.", null, res);
                        con.setAutoCommit(true);
                        continue;
                    }
                    Label_2294: {
                        try {
                            if (strRandNo.compareTo("GPKI") == 0) {
                                if (!oper.equals("c") && !oper.equals("x") && !oper.equals("d")) {
                                    break Label_2294;
                                }
                                Log.debug("UM_URV_UserE040c.java Master code [" + d01 + "] ,Ch???ng nh???n s??? [" + DN1 + "] l?? ch???ng nh???n s??? kh??ng ???????c ph??p s??? d???ng.H??y ki???m tra l???i c???p ????? c???a ch???ng nh???n s???.");
                                CommonMessage.printMsg("", "", "????y l?? ch???ng nh???n s??? kh??ng ???????c ph??p d??ng. H??y th???c hi???n sau khi ki???m tra c???p ????? c???a ch???ng nh???n s???.<br>Kh??ng th??? ????ng k?? ch???ng nh???n s??? GPKI cho nh?? th???u.", null, res);
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
                                Log.debug("UM_URV_UserE040c.java Master code[" + d01 + "] ,Ch???ng nh???n s???[" + DN1 + "] kh??ng ki???m tra ???????c ch??? k?? ??i???n t??? NPKI");
                                CommonMessage.printMsg("", "", "Ch??? k?? s??? NPKI x??c nh???n kh??ng th??nh c??ng", null, res);
                                con.setAutoCommit(true);
                            }
                        }
                        catch (Exception e2) {
                            Log.debug("UM_URV_UserE040c.java Master Code[" + d01 + "] ,Ch???ng nh???n s???[" + DN1 + "] b??? l???i ki???m tra ch??? k?? ??i???n t??? v?? kh??ng x??c minh ???????c nh??n th??n");
                            CommonMessage.printMsg("", "", "L???i ki???m tra ch??? k?? s??? v?? x??c minh nh??n th??n", null, res);
                            con.setAutoCommit(true);
                        }
                        continue;
                    }
                    if (urc.isDataExistOnCert(DN1, con) != 0) {
                        Log.debug("UM_URV_UserE040c.java Master code[" + d01 + "] ,Ch???ng nh???n s???[" + DN1 + "] ???? ???????c ????ng k??");
                        CommonMessage.printMsg("", "", "Ch???ng nh???n s??? n??y ???? ???????c ????ng k??. Kh??ng c???n ????ng k?? n???a.", null, res);
                        con.setAutoCommit(true);
                        continue;
                    }
                    break;
                }
                urc.insertUseCert(id, DN1, DN2, CERT1, CERT2, CertFromDate, CertToDate, con);
                urc.insertCertLog(id, String.valueOf(emallDN) + " emall??????", con);
                con.commit();
                con.setAutoCommit(true);
                CommonMessage.printMsg("", "5", "????ng k?? xong.", "", res);
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
