// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import signgate.crypto.util.CertUtil;
import signgate.crypto.util.SignUtil;
import common.Log;
import secu.lib.CertInfo;
import secu.lib.EnvelopedMessage;
import java.util.StringTokenizer;
import common.Trx;
import signgate.crypto.util.Base64Util;
import java.util.Hashtable;
import secu.lib.MessageDigest;
import java.text.DecimalFormat;
import common.util.CommonMessage;
import LOGIN.UM_Auth_Check;
import beans.UM_URB_MyPage;
import beans.UM_URB_CERT;
import secu.lib.Secu;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_URV_UserA010c01 extends HttpServlet
{
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        httpServletRequest.setCharacterEncoding("UTF-8");
        final Secu secu = new Secu(1);
        final UM_URB_CERT um_URB_CERT = new UM_URB_CERT();
        final UM_URB_MyPage um_URB_MyPage = new UM_URB_MyPage();
        UM_Auth_Check um_Auth_Check = null;
        final String s = (httpServletRequest.getParameter("flag") == null) ? "" : httpServletRequest.getParameter("flag");
        if (!s.equals("s") && !s.equals("i")) {
            try {
                um_Auth_Check = new UM_Auth_Check(httpServletRequest, res);
            }
            catch (Exception ex) {
                CommonMessage.printMsg("", "", ex.getMessage(), null, res);
                return;
            }
        }
        Trx trx = null;
        Connection connection = null;
        final Statement statement = null;
        final Statement statement2 = null;
        final ResultSet set = null;
        final String grp = null;
        final DecimalFormat decimalFormat = new DecimalFormat("00000");
        final String s2 = (httpServletRequest.getParameter("oper") == null) ? "" : httpServletRequest.getParameter("oper");
        final String s3 = (httpServletRequest.getParameter("del") == null) ? "" : httpServletRequest.getParameter("del");
        String dn = (httpServletRequest.getParameter("DN1") == null) ? "" : httpServletRequest.getParameter("DN1");
        final String dn2 = (httpServletRequest.getParameter("DN2") == null) ? "" : httpServletRequest.getParameter("DN2");
        final String cert2 = (httpServletRequest.getParameter("CERT2") == null) ? "" : httpServletRequest.getParameter("CERT2");
        final String s4 = (httpServletRequest.getParameter("ENC_IDENT_INFO") == null) ? "" : httpServletRequest.getParameter("ENC_IDENT_INFO");
        final String s5 = (httpServletRequest.getParameter("ENC_KEY_INFO") == null) ? "" : httpServletRequest.getParameter("ENC_KEY_INFO");
        final String s6 = (httpServletRequest.getParameter("SIGN_LOGIN_INFO") == null) ? "" : httpServletRequest.getParameter("SIGN_LOGIN_INFO");
        final String id = (httpServletRequest.getParameter("id") == null) ? "" : httpServletRequest.getParameter("id");
        final String s7 = (httpServletRequest.getParameter("gubun") == null) ? "" : httpServletRequest.getParameter("gubun");
        final String s8 = (httpServletRequest.getParameter("gubun2") == null) ? "" : httpServletRequest.getParameter("gubun2");
        final String s9 = (httpServletRequest.getParameter("code") == null) ? "" : httpServletRequest.getParameter("code");
        final String mCode = (httpServletRequest.getParameter("d01") == null) ? "" : httpServletRequest.getParameter("d01");
        final String s10 = (httpServletRequest.getParameter("d02") == null) ? "" : httpServletRequest.getParameter("d02");
        final String s11 = (httpServletRequest.getParameter("d03") == null) ? "" : httpServletRequest.getParameter("d03");
        final String s12 = (httpServletRequest.getParameter("d04") == null) ? "" : httpServletRequest.getParameter("d04");
        final String s13 = (httpServletRequest.getParameter("d05") == null) ? "" : httpServletRequest.getParameter("d05");
        final String s14 = (httpServletRequest.getParameter("i01") == null) ? "" : httpServletRequest.getParameter("i01");
        final String i02 = (httpServletRequest.getParameter("i02") == null) ? "" : httpServletRequest.getParameter("i02");
        final String s15 = (httpServletRequest.getParameter("i03") == null) ? "" : httpServletRequest.getParameter("i03");
        final String s16 = (httpServletRequest.getParameter("i04") == null) ? "" : httpServletRequest.getParameter("i04");
        final String s17 = (httpServletRequest.getParameter("i05") == null) ? "" : httpServletRequest.getParameter("i05");
        final String s18 = (httpServletRequest.getParameter("i06") == null) ? "" : httpServletRequest.getParameter("i06");
        final String s19 = (httpServletRequest.getParameter("i07") == null) ? "" : httpServletRequest.getParameter("i07");
        final String i3 = (httpServletRequest.getParameter("i08") == null) ? "N" : httpServletRequest.getParameter("i08");
        final String s20 = (httpServletRequest.getParameter("i09") == null) ? "" : httpServletRequest.getParameter("i09");
        final String s21 = (httpServletRequest.getParameter("i101") == null) ? "" : httpServletRequest.getParameter("i101");
        final String s22 = (httpServletRequest.getParameter("i102") == null) ? "" : httpServletRequest.getParameter("i102");
        final String s23 = (httpServletRequest.getParameter("i103") == null) ? "" : httpServletRequest.getParameter("i103");
        final String s24 = (httpServletRequest.getParameter("i104") == null) ? "" : httpServletRequest.getParameter("i104");
        final String s25 = (httpServletRequest.getParameter("sabun") == null) ? "" : httpServletRequest.getParameter("sabun");
        final String s26 = (httpServletRequest.getParameter("check1") == null) ? "" : httpServletRequest.getParameter("check1");
        final String s27 = (httpServletRequest.getParameter("check2") == null) ? "" : httpServletRequest.getParameter("check2");
        final String s28 = (httpServletRequest.getParameter("user_gubun") == null) ? "" : httpServletRequest.getParameter("user_gubun");
        final String s29 = (httpServletRequest.getParameter("upmuGubun") == null) ? "" : httpServletRequest.getParameter("upmuGubun");
        final String create = new MessageDigest(secu).create(s19);
        final Hashtable hashtable = new Hashtable();
        try {
            dn = new String(Base64Util.decode(dn));
        }
        catch (Exception ex6) {}
        if (s.equals("i")) {
            try {
                trx = new Trx(this, "USEMN");
                connection = trx.getConnection();
                connection.setAutoCommit(false);
                if (um_URB_CERT.getSaupjaNumber(mCode, connection) == null) {
                    CommonMessage.printMsg("", "4", "Ch??a ????ng k?? s??? ????ng k?? kinh doanh tr??n h??? th???ng<br> do ???? kh??ng th???c hi???n ???????c vi???c x??c nh???nNg?????i nh???n: C???c tr?????ng C???c ?????u th???u ", "", res);
                    connection.setAutoCommit(true);
                    return;
                }
                hashtable.put("1", "saup Query");
                final StringTokenizer stringTokenizer = new StringTokenizer(new String(Base64Util.decode(s6)), "|");
                final String nextToken = stringTokenizer.nextToken();
                final String nextToken2 = stringTokenizer.nextToken();
                final String nextToken3 = stringTokenizer.nextToken();
                final String string = nextToken + nextToken2;
                String decryptUpdate;
                try {
                    final EnvelopedMessage envelopedMessage = new EnvelopedMessage(secu);
                    if (s5 == null || s4 == null) {
                        throw new NullPointerException("?????i t?????ng gi???i m?? l?? d??? li???u null m?? h??a. Kh??ng th??? l?? null.");
                    }
                    envelopedMessage.decryptInit(1, 1, s5);
                    decryptUpdate = envelopedMessage.decryptUpdate(s4);
                    envelopedMessage.decryptFinal();
                }
                catch (Exception ex7) {
                    throw new Exception("L???i ph???n gi???i m?? k?? t???.");
                }
                if (decryptUpdate == null) {
                    throw new Exception("L???i gi???i m?? kh??ng c?? hi???u l???c, Kh??ng th??? l?? null.");
                }
                final CertInfo certInfo = new CertInfo(secu, nextToken);
                certInfo.getCertDN();
                final String notBefore = certInfo.getNotBefore();
                final String notAfter = certInfo.getNotAfter();
                final boolean validPolicyOid = certInfo.isValidPolicyOid(new String[] { "1.2.410.200005.1.1.5", "1.2.410.200004.5.2.1.1", "1.2.410.200004.5.3.1.1", "1.2.410.200004.5.3.1.2", "1.2.410.200004.5.3.1.5", "1.2.410.200004.5.1.1.7", "1.2.410.200004.5.1.1.12", "1.2.410.200004.5.4.1.2", "1.2.410.200012.1.1.3", "1.2.410.200012.1.1.5", "1.2.410.100001.2.1.1", "1.2.410.100001.2.1.3", "1.2.704.100001.5.1.1.2" });
                hashtable.put("2", "Ki???m tra Ch???ng nh???n s???");
                if (!validPolicyOid) {
                    Log.debug("UM_URV_UserA010c01.java M?? master [" + mCode + "] ,Ki???m tra quy ?????nh Ch???ng nh???n s??? [" + dn + "] th???t b???i:" + certInfo.getErrorMsg());
                    CommonMessage.printMsg("", "4", "????y l?? ch???ng nh???n s??? kh??ng ???????c ph??p d??ng. H??y ki???m tra l???i tr?????c khi th???c hi???n.", "", res);
                    connection.setAutoCommit(true);
                    return;
                }
                hashtable.put("3", "Ki???m tra t??nh hi???u l???c");
                if (!certInfo.isValid()) {
                    Log.debug("UM_URV_UserA010c01.java M?? master[" + mCode + "] ,Ki???m tra t??nh hi???u l???c Ch???ng nh???n s???[" + dn + "]  th???t b???i");
                    CommonMessage.printMsg("", "4", "Kh??ng ki???m tra ???????c hi???u l???c c???a ch???ng nh???n s???.C?? th??? ????y l?? ch???ng nh???n s??? ???? x??a ho???c ???? h???t hi???u l???c s??? d???ng.", "", res);
                    connection.setAutoCommit(true);
                    return;
                }
                try {
                    if (decryptUpdate.compareTo("GPKI") != 0) {
                        dn.substring(3, dn.indexOf(","));
                        final SignUtil signUtil = new SignUtil();
                        signUtil.verifyInit(CertUtil.pemToDer(nextToken));
                        signUtil.verifyUpdate(string.getBytes());
                        if (!signUtil.verifyFinal(Base64Util.decode(nextToken3))) {
                            Log.debug("UM_URV_UserA010c01.java M?? Master [" + mCode + "] ,Ch???ng nh???n s???  [" + dn + "] kh??ng x??c minh ???????c ch??? k?? ??i???n t??? NPKI");
                            CommonMessage.printMsg("", "", "Kh??ng ki???m tra ???????c ch??? k?? s??? NPKI", null, res);
                            connection.setAutoCommit(true);
                            return;
                        }
                    }
                }
                catch (Exception ex8) {
                    Log.debug("UM_URV_UserA010c01.java M?? Master [" + mCode + "] ,Ch???ng nh???n s??? [" + dn + "] l???i x??c minh nh??n th??n v?? ch??? k?? ??i???n t???");
                    CommonMessage.printMsg("", "", "L???i x??c minh nh??n th??n v?? ch??? k?? ??i???n t???", null, res);
                    connection.setAutoCommit(true);
                    return;
                }
                hashtable.put("5", "Ki???m tra ch???ng nh???n s??? ????ng nh???p");
                if (um_URB_CERT.isDataExistOnCert(dn, connection) != 0) {
                    Log.debug("UM_URV_UserA010c01.java M?? Master[" + mCode + "] ,Ch???ng nh???n s???[" + dn + "] ???? ????ng k?? tr?????c");
                    CommonMessage.printMsg("", "4", "Ch???ng nh???n s??? n??y ???? ???????c ????ng k??. Kh??ng c???n ????ng k?? n???a.", "", res);
                    connection.setAutoCommit(true);
                    return;
                }
                hashtable.put("6", "Ki???m tra t??n ng?????i ph??? tr??ch");
                if (um_URB_CERT.isDataExistOnSamePerson(mCode, i02, connection) != 0) {
                    CommonMessage.printMsg("", "4", "B???n ???? ????ng nh???p b???ng ch???ng nh???n s??? v???i t??n ng?????i ph??? tr??ch tr??ng v???i ng?????i ???? c?? t??? tr?????c trong h??? th???ng.<br>C??ng m???t ng?????i ph??? tr??ch th?? kh??ng th??? ????ng k?? m???i n???a.<BR>Ch??ng t??i ????? ngh??? b???n h??y ????ng k?? b??? sung ch???ng nh???n s??? b???ng c??ch nh???n n??t ????ng k?? b??? sung ch???ng nh???n s??? ??? m??n h??nh danh m???c ng?????i d??ng..<br>N???u nh???t thi???t ph???i ????ng nh???p d?????i c??ng m???t t??n ng?????i ph??? tr??ch<BR>h??y t?? v???n ??? Call Center.", "", res);
                    connection.setAutoCommit(true);
                    return;
                }
                new StringBuffer().append(" select max( USER_ID ) USER_ID   from UM_USER where MAST_CD='").append(mCode).append("' and   USER_ID like '%").append(mCode).append("%'").toString();
                final String userID = um_URB_CERT.getUserID(mCode, connection);
                hashtable.put("10", userID);
                if (i3.equals("Y")) {
                    um_URB_CERT.updateUserZzangReceive(mCode, connection);
                }
                hashtable.put("11", i3);
                um_URB_CERT.insertUseUser(userID, mCode, create, i02, s14, s15, s17, s16, s18, s23, s21, s22, i3, s20, s24, s26, s27, grp, s29, connection);
                hashtable.put("12", "Ch??n b???ng ng?????i d??ng");
                um_URB_CERT.insertUseCert(userID, dn, dn2, nextToken, cert2, notBefore, notAfter, connection);
                hashtable.put("13", "insert Th??ng tin Ch???ng nh???n s???");
                connection.commit();
                connection.setAutoCommit(true);
                res.sendRedirect("/RA/UM_RAJ_GovuB030s01.jsp?id=" + userID);
                return;
            }
            catch (Exception ex2) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                }
                catch (Exception ex9) {}
                try {
                    if (connection != null) {
                        connection.setAutoCommit(true);
                    }
                }
                catch (Exception ex10) {}
                Log.debug("UM_URV_UserA010c01.java flag[" + s + "] :" + hashtable.toString() + ": " + ex2.toString());
                CommonMessage.printMsg("", "4", ex2.getMessage(), "", res);
                return;
            }
            finally {
                if (set != null) {
                    try {
                        set.close();
                    }
                    catch (Exception ex11) {}
                }
                if (statement != null) {
                    try {
                        statement.close();
                    }
                    catch (Exception ex12) {}
                }
                if (statement2 != null) {
                    try {
                        statement2.close();
                    }
                    catch (Exception ex13) {}
                }
                try {
                    if (connection != null) {
                        trx.close();
                    }
                }
                catch (Exception ex14) {}
            }
        }
        if (s.equals("m")) {
            try {
                final String id2 = um_Auth_Check.getID();
                trx = new Trx(this, "USEMN");
                connection = trx.getConnection();
                connection.setAutoCommit(false);
                um_URB_CERT.updateUseUser(s14, i02, s15, s16, s17, s18, create, s20, s26, s27, s21, s22, s23, s24, id2, s29, connection);
                um_URB_CERT.updateModifyDate(id2, dn, connection);
                connection.commit();
                connection.setAutoCommit(true);
                CommonMessage.printMsg("004", "1", "", "/GO/UM_GOJ_GoviB010s.jsp", res);
                return;
            }
            catch (Exception ex3) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                }
                catch (Exception ex15) {}
                try {
                    if (connection != null) {
                        connection.setAutoCommit(true);
                    }
                }
                catch (Exception ex16) {}
                Log.debug("UM_URV_UserA010c01.java flag[" + s + "] : " + ex3.toString());
                CommonMessage.printMsg("", "", ex3.getMessage(), null, res);
                return;
            }
            finally {
                try {
                    if (connection != null) {
                        trx.close();
                    }
                }
                catch (Exception ex17) {}
            }
        }
        if (s.equals("s")) {
            if (!s2.equals("c")) {
                return;
            }
            try {
                trx = new Trx(this, "USEMN");
                connection = trx.getConnection();
                if (um_URB_CERT.isDataExistOnGigwanMaster(mCode, connection) == 0) {
                    CommonMessage.printMsg("", "4", "M?? Doanh nghi???p nh???p ch??a ???????c ????ng k??.", "", res);
                    return;
                }
                if (um_URB_CERT.isDataExistOnUserApproveTwo(mCode, connection) == 0) {
                    res.sendRedirect("/common/terms_gov01.jsp?code=" + mCode);
                    return;
                }
                res.sendRedirect("/RA/UM_URJ_UserD010l.jsp?oper=g&code=" + mCode + "&del=" + s3);
                return;
            }
            catch (Exception ex4) {
                Log.debug("UM_URV_UserA010c01.java flag[" + s + "] : " + ex4.toString());
                CommonMessage.printMsg("", "4", ex4.getMessage(), "", res);
                return;
            }
            finally {
                try {
                    if (connection != null) {
                        trx.close();
                    }
                }
                catch (Exception ex18) {}
            }
        }
        if (s.equals("passwdmod")) {
            try {
                um_Auth_Check.checkModifyPasswd();
                trx = new Trx(this, "USEMN");
                connection = trx.getConnection();
                connection.setAutoCommit(false);
                um_URB_CERT.updateUseUserPasswd(create, id, connection);
                connection.commit();
                connection.setAutoCommit(true);
                CommonMessage.printMsg("", "", "Password ???? ???????c s???a.", null, res);
            }
            catch (Exception ex5) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                }
                catch (Exception ex19) {}
                try {
                    if (connection != null) {
                        connection.setAutoCommit(true);
                    }
                }
                catch (Exception ex20) {}
                Log.debug("UM_URV_UserA010c01.java flag[" + s + "] : " + ex5.toString());
                CommonMessage.printMsg("", "", ex5.getMessage(), null, res);
            }
            finally {
                try {
                    if (connection != null) {
                        trx.close();
                    }
                }
                catch (Exception ex21) {}
            }
        }
    }
}
