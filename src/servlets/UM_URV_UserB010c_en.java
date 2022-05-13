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
                        CommonMessage.printMsg("", "4", "Đây là số ĐKKD đã đăng ký từ trước.<br>Vui lòng kiểm tra lại.", "", res);
                        return;
                    }
                    if (oper.equals("x") && um_URB_CERT.isDataExistOnBichukUpcheMaster(trim, conn) == 0) {
                        CommonMessage.printMsg("", "4", "Đây là số ĐKKD chưa được đăng ký làm nhà thầu dự phòng.<br>Hãy kiểm tra lại.", "", res);
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
                        throw new NullPointerException("Dữ liệu mã hóa đối tượng giải mã là null. Không thể là null.");
                    }
                    envelopedMessage.decryptInit(1, 1, s5);
                    decryptUpdate = envelopedMessage.decryptUpdate(s4);
                    envelopedMessage.decryptFinal();
                }
                catch (Exception ex9) {
                    throw new Exception("Lỗi phần giải mã ký tự.");
                }
                if (decryptUpdate == null) {
                    throw new Exception("Dữ liệu mã hóa đối tượng giải mã là null. Không thể là null.");
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
                    Log.debug("UM_URV_UserB010c.java Mã master [" + trim + "] ,Chứng nhận số [" + s3 + "] không được phép sử dụng. Hãy sử dụng sau khi kiểm tra xem có phải là chứng nhận số dự phòng hay không.");
                    CommonMessage.printMsg("", "", "Đây là chứng nhận số không được phép dùng. Hãy sử dụng sau khi kiểm tra xem có phải là chứng nhận số dự phòng hay không.", null, res);
                    conn.setAutoCommit(true);
                    return;
                }
                if (!validPolicyOid) {
                    Log.debug("UM_URV_UserB010c.java Mã master [" + trim + "] ,Chứng nhận số [" + s3 + "] không được phép dùng. Hãy sử dụng sau khi kiểm tra.");
                    CommonMessage.printMsg("", "", "Đây là chứng nhận số không được phép dùng 2. Hãy sử dụng sau khi kiểm tra.", null, res);
                    conn.setAutoCommit(true);
                    return;
                }
                if (!certInfo.isValid()) {
                    Log.debug("UM_URV_UserB010c.java Mã Master [" + trim + "] , Kiểm tra hiệu lực Chứng chỉ số [" + s3 + "] không thành công");
                    CommonMessage.printMsg("", "", "Không kiểm tra được hiệu lực của chứng nhận số. Có thể đây là chứng nhận số đã xóa hoặc đã hết hiệu lực sử dụng.", null, res);
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
                            Log.debug("UM_URV_UserB010c.java Mã Master [" + trim + "] ,Giấy chứng nhận [" + s3 + "] Chữ ký điện tử NPKI xác nhận không thành công");
                            CommonMessage.printMsg("", "", "NPKI Chữ ký điện tử xác nhận không thành công", null, res);
                            conn.setAutoCommit(true);
                            return;
                        }
                    }
                }
                catch (Exception ex10) {
                    Log.debug("UM_URV_UserB010c.java Mã Master [" + trim + "] ,Giấy chứng nhận [" + s3 + "] Xác minh Chữ ký điện tử lỗi");
                    CommonMessage.printMsg("", "", "Xác minh Chữ ký điện tử lỗi", null, res);
                    conn.setAutoCommit(true);
                    return;
                }
                CommonMessage.printMsg("", "4", "Dong` 566", "", res);
                if (um_URB_CERT.isDataExistOnCert(s3, conn) != 0) {
                    Log.debug("UM_URV_UserA010c.java Mã Master [" + trim + "] ,Chứng nhận số [" + s3 + "] cấp giấy chứng nhận");
                    CommonMessage.printMsg("", "4", "Việc cấp giấy chứng nhận đã được đăng ký. Bạn không cần phải đăng ký.", "", res);
                    conn.setAutoCommit(true);
                    return;
                }
                if (um_URB_CERT.isDataExistOnCert(s3, conn) != 0) {
                    CommonMessage.printMsg("", "4", "Giấy chứng nhận đã được đăng ký.", "", res);
                    conn.setAutoCommit(true);
                    return;
                }
                System.out.println("dong` 603");
                if (um_URB_CERT.isDataExistOnSamePerson(trim, i02, conn) != 0) {
                    CommonMessage.printMsg("", "4", "Bạn đã đăng nhập bằng chứng nhận số với tên người phụ trách trùng với người đã có từ trước trong hệ thống.<br>Cùng một người phụ trách thì không thể đăng ký mới nữa.<BR>Chúng tôi đề nghị bạn hãy đăng ký bổ sung chứng nhận số bằng cách nhấn nút 'Đăng ký bổ sung chứng nhận số' ở màn hình danh mục người dùng..<br>Nếu nhất thiết phải đăng nhập dưới cùng một tên người phụ trách<BR>hãy tư vấn ở Call Center.", "", res);
                    conn.setAutoCommit(true);
                    return;
                }
                if (oper.equals("d") && um_URB_CERT.isDataExistOnUserCertD(trim, conn) == 0) {
                    CommonMessage.printMsg("", "4", "Trường hợp là người dùng đầu tiên của hệ thống mua sắm công điện tử thì không thể đăng ký làm người gửi nhận thông tin tiến độ thi công.<br>Đừng chọn vào tùy chọn gửi nhận thông báo về tiến độ thi công mà hãy đăng ký chứng nhận số <BR>sau đó đăng ký thành người dùng khác.<br>Nếu có thắc mắc cần giải đáp, vui lòng gọi đến trung tâm tư vấn số 1588-0800.", "", res);
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
