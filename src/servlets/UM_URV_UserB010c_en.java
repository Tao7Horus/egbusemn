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
import common.Log;
import common.Trx;
import secu.lib.MessageDigest;
import beans.UM_URB_MyPage;
import beans.UM_URB_CERT;
import common.util.CommonMessage;
import LOGIN.UM_Auth_Check;
import secu.lib.Secu;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_URV_UserB010c_en extends HttpServlet
{
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        final Secu secu = new Secu(1);
        final String s = (httpServletRequest.getParameter("flag") == null) ? "" : httpServletRequest.getParameter("flag");
        UM_Auth_Check um_Auth_Check = null;
        if (s.equals("m")) {
            try {
                um_Auth_Check = new UM_Auth_Check(httpServletRequest, res);
            }
            catch (Exception ex) {
                CommonMessage.printMsg("", "", ex.getMessage(), null, res);
                return;
            }
        }
        System.out.println("dong`92");
        final UM_URB_CERT um_URB_CERT = new UM_URB_CERT();
        final UM_URB_MyPage um_URB_MyPage = new UM_URB_MyPage();
        Trx trx = null;
        Connection conn = null;
        final String grp = null;
        final String oper = (httpServletRequest.getParameter("oper") == null) ? "" : httpServletRequest.getParameter("oper");
        final String s2 = (httpServletRequest.getParameter("del") == null) ? "" : httpServletRequest.getParameter("del");
        String s3 = (httpServletRequest.getParameter("DN1") == null) ? "" : httpServletRequest.getParameter("DN1");
        final String dn2 = (httpServletRequest.getParameter("DN2") == null) ? "" : httpServletRequest.getParameter("DN2");
        final String cert2 = (httpServletRequest.getParameter("CERT2") == null) ? "" : httpServletRequest.getParameter("CERT2");
        final String s4 = (httpServletRequest.getParameter("ENC_IDENT_INFO") == null) ? "" : httpServletRequest.getParameter("ENC_IDENT_INFO");
        final String s5 = (httpServletRequest.getParameter("ENC_KEY_INFO") == null) ? "" : httpServletRequest.getParameter("ENC_KEY_INFO");
        final String s6 = (httpServletRequest.getParameter("SIGN_LOGIN_INFO") == null) ? "" : httpServletRequest.getParameter("SIGN_LOGIN_INFO");
        final String s7 = (httpServletRequest.getParameter("id") == null) ? "" : httpServletRequest.getParameter("id");
        final String s8 = (httpServletRequest.getParameter("d01") == null) ? "" : httpServletRequest.getParameter("d01");
        final String s9 = (httpServletRequest.getParameter("d02") == null) ? "" : httpServletRequest.getParameter("d02");
        final String s10 = (httpServletRequest.getParameter("d03") == null) ? "" : httpServletRequest.getParameter("d03");
        final String trim = ((httpServletRequest.getParameter("d04") == null) ? "" : httpServletRequest.getParameter("d04")).trim();
        final String s11 = (httpServletRequest.getParameter("d05") == null) ? "" : httpServletRequest.getParameter("d05");
        final String s12 = (httpServletRequest.getParameter("d06") == null) ? "" : httpServletRequest.getParameter("d06");
        final String s13 = (httpServletRequest.getParameter("d07") == null) ? "" : httpServletRequest.getParameter("d07");
        final String s14 = (httpServletRequest.getParameter("d08") == null) ? "" : httpServletRequest.getParameter("d08");
        final String s15 = (httpServletRequest.getParameter("i01") == null) ? "" : httpServletRequest.getParameter("i01");
        final String i02 = (httpServletRequest.getParameter("i02") == null) ? "" : httpServletRequest.getParameter("i02");
        final String s16 = (httpServletRequest.getParameter("i03") == null) ? "" : httpServletRequest.getParameter("i03");
        final String s17 = (httpServletRequest.getParameter("i04") == null) ? "" : httpServletRequest.getParameter("i04");
        final String s18 = (httpServletRequest.getParameter("i05") == null) ? "" : httpServletRequest.getParameter("i05");
        final String s19 = (httpServletRequest.getParameter("i06") == null) ? "" : httpServletRequest.getParameter("i06");
        final String s20 = (httpServletRequest.getParameter("i07") == null) ? "" : httpServletRequest.getParameter("i07");
        final String i3 = (httpServletRequest.getParameter("i08") == null) ? "N" : httpServletRequest.getParameter("i08");
        final String s21 = (httpServletRequest.getParameter("i09") == null) ? "" : httpServletRequest.getParameter("i09");
        final String s22 = (httpServletRequest.getParameter("user_gubun") == null) ? "" : httpServletRequest.getParameter("user_gubun");
        final String create = new MessageDigest(secu).create(s20);
        System.out.println("dong` 188");
        if (s.equals("m")) {
            try {
                um_Auth_Check.checkCookie("cx", null, null);
                final String id = um_Auth_Check.getID();
                trx = new Trx(this, "USEMN");
                conn = trx.getConnection();
                conn.setAutoCommit(false);
                um_URB_CERT.updateUseUser(s15, i02, s16, s17, s18, s19, create, s21, id, conn);
                um_URB_CERT.updateModifyDate(id, s3, conn);
                conn.commit();
                conn.setAutoCommit(true);
                CommonMessage.printMsg("004", "1", "", "/CO/UM_COJ_ConiC010s.jsp", res);
                return;
            }
            catch (Exception ex2) {
                try {
                    if (conn != null) {
                        conn.rollback();
                    }
                }
                catch (Exception ex5) {}
                try {
                    if (conn != null) {
                        conn.setAutoCommit(true);
                    }
                }
                catch (Exception ex6) {}
                Log.debug("UM_URV_UserB010c.java flag[" + s + "] : " + ex2.toString());
                CommonMessage.printMsg("", "", ex2.getMessage(), null, res);
                return;
            }
            finally {
                try {
                    if (conn != null) {
                        trx.close();
                    }
                }
                catch (Exception ex7) {}
            }
        }
        Label_1702: {
            if (s.equals("s")) {
                if (!oper.equals("c") && !oper.equals("x")) {
                    if (!oper.equals("d")) {
                        break Label_1702;
                    }
                }
                try {
                    trx = new Trx(this, "USEMN");
                    conn = trx.getConnection();
                    if ((oper.equals("c") || oper.equals("x") || oper.equals("")) && um_URB_CERT.isDataExistOnJodalUpcheMaster(trim, conn) == 0) {
                        CommonMessage.printMsg("", "4", "????y l?? s??? ??KKD ???? ????ng k?? t??? tr?????c.<br>Vui l??ng ki???m tra l???i.", "", res);
                        return;
                    }
                    if (oper.equals("x") && um_URB_CERT.isDataExistOnBichukUpcheMaster(trim, conn) == 0) {
                        CommonMessage.printMsg("", "4", "????y l?? s??? ??KKD ch??a ???????c ????ng k?? l??m nh?? th???u d??? ph??ng.<br>H??y ki???m tra l???i.", "", res);
                        res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/FO/BichukError.jsp?errType=0");
                        return;
                    }
                    if (um_URB_CERT.isDataExistOnUserApproveTwo(trim, conn) == 0) {
                        res.sendRedirect("/common/terms_comp_en.jsp?code=" + trim + "&oper=" + oper);
                    }
                    else {
                        res.sendRedirect("/RA/UM_URJ_UserD010l.jsp?oper=" + oper + "&code=" + trim + "&del=" + s2);
                    }
                    return;
                }
                catch (Exception ex3) {
                    Log.debug("UM_URV_UserB010c.java flag[" + s + "] : " + ex3.toString());
                    CommonMessage.printMsg("", "", ex3.getMessage(), null, res);
                    return;
                }
                finally {
                    try {
                        if (conn != null) {
                            trx.close();
                        }
                    }
                    catch (Exception ex8) {}
                }
            }
        }
        if (s.equals("i")) {
            try {
                trx = new Trx(this, "USEMN");
                conn = trx.getConnection();
                conn.setAutoCommit(false);
                final StringTokenizer stringTokenizer = new StringTokenizer(new String(Base64Util.decode(s6)), "|");
                final String nextToken = stringTokenizer.nextToken();
                final String nextToken2 = stringTokenizer.nextToken();
                final String nextToken3 = stringTokenizer.nextToken();
                final String string = nextToken + nextToken2;
                String decryptUpdate;
                try {
                    final EnvelopedMessage envelopedMessage = new EnvelopedMessage(secu);
                    if (s5 == null || s4 == null) {
                        throw new NullPointerException("D??? li???u m?? h??a ?????i t?????ng gi???i m?? l?? null. Kh??ng th??? l?? null.");
                    }
                    envelopedMessage.decryptInit(1, 1, s5);
                    decryptUpdate = envelopedMessage.decryptUpdate(s4);
                    envelopedMessage.decryptFinal();
                }
                catch (Exception ex9) {
                    throw new Exception("L???i ph???n gi???i m?? k?? t???.");
                }
                if (decryptUpdate == null) {
                    throw new Exception("D??? li???u m?? h??a ?????i t?????ng gi???i m?? l?? null. Kh??ng th??? l?? null.");
                }
                final CertInfo certInfo = new CertInfo(secu, nextToken);
                final String notBefore = certInfo.getNotBefore();
                final String notAfter = certInfo.getNotAfter();
                final String[] array = { "1.2.410.200005.1.1.5", "1.2.410.200004.5.2.1.1", "1.2.410.200004.5.3.1.1", "1.2.410.200004.5.3.1.2", "1.2.410.200004.5.3.1.5", "1.2.410.200004.5.1.1.7", "1.2.410.200004.5.1.1.12", "1.2.410.200004.5.4.1.2", "1.2.410.200012.1.1.3", "1.2.410.200012.1.1.5", "1.2.410.200004.5.2.1.6.84", "1.2.410.200005.1.1.6.3", "1.2.410.200004.5.1.1.12.901", "1.2.410.200004.5.4.1.12", "1.2.704.100001.5.1.1.2" };
                final String[] array2 = { "1.2.410.200004.5.2.1.6.84", "1.2.410.200005.1.1.6.3", "1.2.410.200004.5.1.1.12.901", "1.2.410.200004.5.4.1.12", "1.2.704.100001.5.1.1.2" };
                System.out.println("dong` 425");
                final boolean validPolicyOid = certInfo.isValidPolicyOid(array);
                boolean validPolicyOid2 = false;
                if (oper.equals("c")) {
                    validPolicyOid2 = certInfo.isValidPolicyOid(array2);
                }
                if (!validPolicyOid2) {
                    Log.debug("UM_URV_UserB010c.java M?? master [" + trim + "] ,Ch???ng nh???n s??? [" + s3 + "] kh??ng ???????c ph??p s??? d???ng. H??y s??? d???ng sau khi ki???m tra xem c?? ph???i l?? ch???ng nh???n s??? d??? ph??ng hay kh??ng.");
                    CommonMessage.printMsg("", "", "????y l?? ch???ng nh???n s??? kh??ng ???????c ph??p d??ng. H??y s??? d???ng sau khi ki???m tra xem c?? ph???i l?? ch???ng nh???n s??? d??? ph??ng hay kh??ng.", null, res);
                    conn.setAutoCommit(true);
                    return;
                }
                if (!validPolicyOid) {
                    Log.debug("UM_URV_UserB010c.java M?? master [" + trim + "] ,Ch???ng nh???n s??? [" + s3 + "] kh??ng ???????c ph??p d??ng. H??y s??? d???ng sau khi ki???m tra.");
                    CommonMessage.printMsg("", "", "????y l?? ch???ng nh???n s??? kh??ng ???????c ph??p d??ng 2. H??y s??? d???ng sau khi ki???m tra.", null, res);
                    conn.setAutoCommit(true);
                    return;
                }
                if (!certInfo.isValid()) {
                    Log.debug("UM_URV_UserB010c.java M?? Master [" + trim + "] , Ki???m tra hi???u l???c Ch???ng ch??? s??? [" + s3 + "] kh??ng th??nh c??ng");
                    CommonMessage.printMsg("", "", "Kh??ng ki???m tra ???????c hi???u l???c c???a ch???ng nh???n s???. C?? th??? ????y l?? ch???ng nh???n s??? ???? x??a ho???c ???? h???t hi???u l???c s??? d???ng.", null, res);
                    conn.setAutoCommit(true);
                    return;
                }
                try {
                    if (decryptUpdate.compareTo("GPKI") != 0) {
                        s3 = new String(Base64Util.decode(s3));
                        s3.substring(3, s3.indexOf(","));
                        System.out.println("dong` 539");
                        final SignUtil signUtil = new SignUtil();
                        signUtil.verifyInit(CertUtil.pemToDer(nextToken));
                        signUtil.verifyUpdate(string.getBytes());
                        if (!signUtil.verifyFinal(Base64Util.decode(nextToken3))) {
                            Log.debug("UM_URV_UserB010c.java M?? Master [" + trim + "] ,Gi???y ch???ng nh???n [" + s3 + "] Ch??? k?? ??i???n t??? NPKI x??c nh???n kh??ng th??nh c??ng");
                            CommonMessage.printMsg("", "", "NPKI Ch??? k?? ??i???n t??? x??c nh???n kh??ng th??nh c??ng", null, res);
                            conn.setAutoCommit(true);
                            return;
                        }
                    }
                }
                catch (Exception ex10) {
                    Log.debug("UM_URV_UserB010c.java M?? Master [" + trim + "] ,Gi???y ch???ng nh???n [" + s3 + "] X??c minh Ch??? k?? ??i???n t??? l???i");
                    CommonMessage.printMsg("", "", "X??c minh Ch??? k?? ??i???n t??? l???i", null, res);
                    conn.setAutoCommit(true);
                    return;
                }
                CommonMessage.printMsg("", "4", "Dong` 566", "", res);
                if (um_URB_CERT.isDataExistOnCert(s3, conn) != 0) {
                    Log.debug("UM_URV_UserA010c.java M?? Master [" + trim + "] ,Ch???ng nh???n s??? [" + s3 + "] c???p gi???y ch???ng nh???n");
                    CommonMessage.printMsg("", "4", "Vi???c c???p gi???y ch???ng nh???n ???? ???????c ????ng k??. B???n kh??ng c???n ph???i ????ng k??.", "", res);
                    conn.setAutoCommit(true);
                    return;
                }
                if (um_URB_CERT.isDataExistOnCert(s3, conn) != 0) {
                    CommonMessage.printMsg("", "4", "Gi???y ch???ng nh???n ???? ???????c ????ng k??.", "", res);
                    conn.setAutoCommit(true);
                    return;
                }
                System.out.println("dong` 603");
                if (um_URB_CERT.isDataExistOnSamePerson(trim, i02, conn) != 0) {
                    CommonMessage.printMsg("", "4", "B???n ???? ????ng nh???p b???ng ch???ng nh???n s??? v???i t??n ng?????i ph??? tr??ch tr??ng v???i ng?????i ???? c?? t??? tr?????c trong h??? th???ng.<br>C??ng m???t ng?????i ph??? tr??ch th?? kh??ng th??? ????ng k?? m???i n???a.<BR>Ch??ng t??i ????? ngh??? b???n h??y ????ng k?? b??? sung ch???ng nh???n s??? b???ng c??ch nh???n n??t '????ng k?? b??? sung ch???ng nh???n s???' ??? m??n h??nh danh m???c ng?????i d??ng..<br>N???u nh???t thi???t ph???i ????ng nh???p d?????i c??ng m???t t??n ng?????i ph??? tr??ch<BR>h??y t?? v???n ??? Call Center.", "", res);
                    conn.setAutoCommit(true);
                    return;
                }
                if (oper.equals("d") && um_URB_CERT.isDataExistOnUserCertD(trim, conn) == 0) {
                    CommonMessage.printMsg("", "4", "Tr?????ng h???p l?? ng?????i d??ng ?????u ti??n c???a h??? th???ng mua s???m c??ng ??i???n t??? th?? kh??ng th??? ????ng k?? l??m ng?????i g???i nh???n th??ng tin ti???n ????? thi c??ng.<br>?????ng ch???n v??o t??y ch???n g???i nh???n th??ng b??o v??? ti???n ????? thi c??ng m?? h??y ????ng k?? ch???ng nh???n s??? <BR>sau ???? ????ng k?? th??nh ng?????i d??ng kh??c.<br>N???u c?? th???c m???c c???n gi???i ????p, vui l??ng g???i ?????n trung t??m t?? v???n s??? 1588-0800.", "", res);
                    conn.setAutoCommit(true);
                    return;
                }
                final String userUpcheID = um_URB_CERT.getUserUpcheID(trim, conn);
                if (i3.equals("Y")) {
                    um_URB_CERT.updateUserZzangReceive(trim, conn);
                }
                um_URB_CERT.insertUseUser(userUpcheID, oper, trim, create, i02, s15, s16, s18, s17, s19, i3, s21, grp, conn);
                um_URB_CERT.insertUseCert(userUpcheID, s3, dn2, nextToken, cert2, notBefore, notAfter, conn);
                conn.commit();
                conn.setAutoCommit(true);
                res.sendRedirect("/RA/UM_RAJ_ConuC030s_en.jsp?id=" + userUpcheID + "&oper=" + oper);
            }
            catch (Exception ex4) {
                try {
                    if (conn != null) {
                        conn.rollback();
                    }
                }
                catch (Exception ex11) {}
                try {
                    if (conn != null) {
                        conn.setAutoCommit(true);
                    }
                }
                catch (Exception ex12) {}
                Log.debug("UM_URV_UserB010c.java flag[" + s + "] : " + ex4.toString());
                CommonMessage.printMsg("", "", ex4.getMessage(), null, res);
            }
            finally {
                try {
                    if (conn != null) {
                        trx.close();
                    }
                }
                catch (Exception ex13) {}
            }
        }
    }
}
