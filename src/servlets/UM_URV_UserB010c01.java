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

public class UM_URV_UserB010c01 extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        final Secu secu = new Secu(1);
        final String flag = (req.getParameter("flag") == null) ? "" : req.getParameter("flag");
        UM_Auth_Check uac = null;
        if (flag.equals("m")) {
            try {
                uac = new UM_Auth_Check(req, res);
            }
            catch (Exception exi) {
                CommonMessage.printMsg("", "", exi.getMessage(), null, res);
                return;
            }
        }
        System.out.println("dong`92");
        final UM_URB_CERT urc = new UM_URB_CERT();
        final UM_URB_MyPage myPage = new UM_URB_MyPage();
        Trx trx = null;
        final Trx emallTrx = null;
        Connection con = null;
        final Connection emallCon = null;
        String oper = "";
        String del = "";
        String DN1 = "";
        String DN2 = "";
        String CERT1 = "";
        String CERT2 = "";
        final String randNo = "";
        String ENC_RANDNUM = "";
        String ENC_KEY = "";
        String SIGN_LOGIN = "";
        String d01 = "";
        String d2 = "";
        String d3 = "";
        String d4 = "";
        String d5 = "";
        String d6 = "";
        String d7 = "";
        String d8 = "";
        String i01 = "";
        String i2 = "";
        String i3 = "";
        String i4 = "";
        String i5 = "";
        String i6 = "";
        String i7 = "";
        String i8 = "";
        String i9 = "";
        String i10 = "";
        String id = "";
        final String mainCheck = "";
        final String mcode = "";
        String user_gubun = "";
        final String grp = null;
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
        d2 = ((req.getParameter("d02") == null) ? "" : req.getParameter("d02"));
        d3 = ((req.getParameter("d03") == null) ? "" : req.getParameter("d03"));
        d4 = ((req.getParameter("d04") == null) ? "" : req.getParameter("d04"));
        d5 = ((req.getParameter("d05") == null) ? "" : req.getParameter("d05"));
        d6 = ((req.getParameter("d06") == null) ? "" : req.getParameter("d06"));
        d7 = ((req.getParameter("d07") == null) ? "" : req.getParameter("d07"));
        d8 = ((req.getParameter("d08") == null) ? "" : req.getParameter("d08"));
        i01 = ((req.getParameter("i01") == null) ? "" : req.getParameter("i01"));
        i2 = ((req.getParameter("i02") == null) ? "" : req.getParameter("i02"));
        i3 = ((req.getParameter("i03") == null) ? "" : req.getParameter("i03"));
        i4 = ((req.getParameter("i04") == null) ? "" : req.getParameter("i04"));
        i5 = ((req.getParameter("i05") == null) ? "" : req.getParameter("i05"));
        i6 = ((req.getParameter("i06") == null) ? "" : req.getParameter("i06"));
        i7 = ((req.getParameter("i07") == null) ? "" : req.getParameter("i07"));
        i9 = ((req.getParameter("i08") == null) ? "N" : req.getParameter("i08"));
        i10 = ((req.getParameter("i09") == null) ? "" : req.getParameter("i09"));
        user_gubun = ((req.getParameter("user_gubun") == null) ? "" : req.getParameter("user_gubun"));
        final String strMData1 = null;
        final MessageDigest md = new MessageDigest(secu);
        i8 = md.create(i7);
        System.out.println("dong` 188");
        if (flag.equals("m")) {
            try {
                uac.checkCookie("cx", null, null);
                id = uac.getID();
                trx = new Trx(this, "USEMN");
                con = trx.getConnection();
                con.setAutoCommit(false);
                urc.updateUseUser(i01, i2, i3, i4, i5, i6, i8, i10, id, con);
                urc.updateModifyDate(id, DN1, con);
                con.commit();
                con.setAutoCommit(true);
                CommonMessage.printMsg("004", "1", "", "/CO/UM_COJ_ConiC010s.jsp", res);
                return;
            }
            catch (Exception exm) {
                try {
                    if (con != null) {
                        con.rollback();
                    }
                }
                catch (Exception ex) {}
                try {
                    if (con != null) {
                        con.setAutoCommit(true);
                    }
                }
                catch (Exception ex2) {}
                Log.debug("UM_URV_UserB010c01.java flag[" + flag + "] : " + exm.toString());
                CommonMessage.printMsg("", "", exm.getMessage(), null, res);
            }
            finally {
                try {
                    if (con != null) {
                        trx.close();
                    }
                }
                catch (Exception ex3) {}
            }
        }
        Label_1618: {
            if (flag.equals("s")) {
                if (!oper.equals("c") && !oper.equals("x")) {
                    if (!oper.equals("d")) {
                        break Label_1618;
                    }
                }
                Label_1600: {
                    try {
                        trx = new Trx(this, "USEMN");
                        con = trx.getConnection();
                        if ((oper.equals("c") || oper.equals("x") || oper.equals("")) && urc.isDataExistOnJodalUpcheMaster(d4, con) == 0) {
                            CommonMessage.printMsg("", "4", "Đây là số ĐKKD đã đăng ký từ trước.<br>Vui lòng kiểm tra lại.", "", res);
                        }
                        else if (oper.equals("x") && urc.isDataExistOnBichukUpcheMaster(d4, con) == 0) {
                            CommonMessage.printMsg("", "4", "Đây là số ĐKKD chưa được đăng ký làm nhà thầu dự phòng.<br>Hãy kiểm tra lại.", "", res);
                            res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/FO/BichukError.jsp?errType=0");
                        }
                        else {
                            if (urc.isDataExistOnUserApproveTwo(d4, con) == 0) {
                                res.sendRedirect("/common/terms_comp01.jsp?code=" + d4 + "&oper=" + oper);
                                break Label_1600;
                            }
                            res.sendRedirect("/RA/UM_URJ_UserD010l.jsp?oper=" + oper + "&code=" + d4 + "&del=" + del);
                            break Label_1600;
                        }
                        return;
                    }
                    catch (Exception exm) {
                        Log.debug("UM_URV_UserB010c01.java flag[" + flag + "] : " + exm.toString());
                        CommonMessage.printMsg("", "", exm.getMessage(), null, res);
                    }
                    finally {
                        try {
                            if (con != null) {
                                trx.close();
                            }
                        }
                        catch (Exception ex4) {}
                    }
                    try {
                        if (con != null) {
                            trx.close();
                        }
                    }
                    catch (Exception ex5) {}
                }
                return;
            }
        }
        if (flag.equals("i")) {
            String message = "";
            String strDTM = null;
            String strSignedMessage = null;
            String strOriginalMessage = null;
            Label_2974: {
                try {
                    trx = new Trx(this, "USEMN");
                    con = trx.getConnection();
                    con.setAutoCommit(false);
                    final String strstr = new String(Base64Util.decode(SIGN_LOGIN));
                    final StringTokenizer st = new StringTokenizer(strstr, "|");
                    CERT1 = st.nextToken();
                    strDTM = st.nextToken();
                    strSignedMessage = st.nextToken();
                    strOriginalMessage = String.valueOf(CERT1) + strDTM;
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
                        throw new Exception("Lỗi phần giải mã ký tự.");
                    }
                    if (strRandNo == null) {
                        throw new Exception("Dữ liệu mã hóa đối tượng giải mã là null. Không thể là null.");
                    }
                    final CertInfo cu = new CertInfo(secu, CERT1);
                    final String CertFromDate = cu.getNotBefore();
                    final String CertToDate = cu.getNotAfter();
                    final String[] OID = { "1.2.410.200005.1.1.5", "1.2.410.200004.5.2.1.1", "1.2.410.200004.5.3.1.1", "1.2.410.200004.5.3.1.2", "1.2.410.200004.5.3.1.5", "1.2.410.200004.5.1.1.7", "1.2.410.200004.5.1.1.12", "1.2.410.200004.5.4.1.2", "1.2.410.200012.1.1.3", "1.2.410.200012.1.1.5", "1.2.410.200004.5.2.1.6.84", "1.2.410.200005.1.1.6.3", "1.2.410.200004.5.1.1.12.901", "1.2.410.200004.5.4.1.12", "1.2.704.100001.5.1.1.2" };
                    final String[] OID2 = { "1.2.410.200004.5.2.1.6.84", "1.2.410.200005.1.1.6.3", "1.2.410.200004.5.1.1.12.901", "1.2.410.200004.5.4.1.12", "1.2.704.100001.5.1.1.2" };
                    System.out.println("dong` 425");
                    final boolean bChkOIDValid = cu.isValidPolicyOid(OID);
                    boolean isbChkOID = false;
                    if (oper.equals("c")) {
                        isbChkOID = cu.isValidPolicyOid(OID2);
                    }
                    Label_2100: {
                        if (!isbChkOID) {
                            Log.debug("UM_URV_UserB010c01.java Mã master [" + d4 + "] ,Chứng nhận số [" + DN1 + "] không được phép sử dụng. Hãy sử dụng sau khi kiểm tra xem có phải là chứng nhận số dự phòng hay không.");
                            CommonMessage.printMsg("", "", "Đây là chứng nhận số không được phép dùng. Hãy sử dụng sau khi kiểm tra xem có phải là chứng nhận số dự phòng hay không.", null, res);
                            con.setAutoCommit(true);
                        }
                        else if (!bChkOIDValid) {
                            Log.debug("UM_URV_UserB010c01.java Mã master [" + d4 + "] ,Chứng nhận số [" + DN1 + "] không được phép dùng. Hãy sử dụng sau khi kiểm tra.");
                            CommonMessage.printMsg("", "", "Đây là chứng nhận số không được phép dùng 2. Hãy sử dụng sau khi kiểm tra.", null, res);
                            con.setAutoCommit(true);
                        }
                        else if (!cu.isValid()) {
                            Log.debug("UM_URV_UserB010c01.java Mã Master [" + d4 + "] , Kiểm tra hiệu lực Chứng chỉ số [" + DN1 + "] không thành công");
                            CommonMessage.printMsg("", "", "Không kiểm tra được hiệu lực của chứng nhận số. Có thể đây là chứng nhận số đã xóa hoặc đã hết hiệu lực sử dụng.", null, res);
                            con.setAutoCommit(true);
                        }
                        else {
                            Label_2470: {
                                try {
                                    if (strRandNo.compareTo("GPKI") == 0) {
                                        break Label_2470;
                                    }
                                    DN1 = new String(Base64Util.decode(DN1));
                                    final String dnName = DN1.substring(3, DN1.indexOf(","));
                                    System.out.println("dong` 539");
                                    SignUtil sign = null;
                                    sign = new SignUtil();
                                    sign.verifyInit(CertUtil.pemToDer(CERT1));
                                    sign.verifyUpdate(strOriginalMessage.getBytes());
                                    if (sign.verifyFinal(Base64Util.decode(strSignedMessage))) {
                                        break Label_2470;
                                    }
                                    Log.debug("UM_URV_UserB010c01.java Mã Master [" + d4 + "] ,Giấy chứng nhận [" + DN1 + "] Chữ ký điện tử NPKI xác nhận không thành công");
                                    CommonMessage.printMsg("", "", "NPKI Chữ ký điện tử xác nhận không thành công", null, res);
                                    con.setAutoCommit(true);
                                }
                                catch (Exception e2) {
                                    Log.debug("UM_URV_UserB010c01.java Mã Master [" + d4 + "] ,Giấy chứng nhận [" + DN1 + "] Xác minh Chữ ký điện tử lỗi");
                                    CommonMessage.printMsg("", "", "Xác minh Chữ ký điện tử lỗi", null, res);
                                    con.setAutoCommit(true);
                                }
                                break Label_2100;
                            }
                            CommonMessage.printMsg("", "4", "Dong` 566", "", res);
                            if (urc.isDataExistOnCert(DN1, con) != 0) {
                                Log.debug("UM_URV_UserA010c.java Mã Master [" + d4 + "] ,Chứng nhận số [" + DN1 + "] cấp giấy chứng nhận");
                                CommonMessage.printMsg("", "4", "Việc cấp giấy chứng nhận đã được đăng ký. Bạn không cần phải đăng ký.", "", res);
                                con.setAutoCommit(true);
                            }
                            else if (urc.isDataExistOnCert(DN1, con) != 0) {
                                CommonMessage.printMsg("", "4", "Giấy chứng nhận đã được đăng ký.", "", res);
                                con.setAutoCommit(true);
                            }
                            else {
                                System.out.println("dong` 603");
                                if (urc.isDataExistOnSamePerson(d4, i2, con) != 0) {
                                    message = "Bạn đã đăng nhập bằng chứng nhận số với tên người phụ trách trùng với người đã có từ trước trong hệ thống.<br>Cùng một người phụ trách thì không thể đăng ký mới nữa.<BR>Chúng tôi đề nghị bạn hãy đăng ký bổ sung chứng nhận số bằng cách nhấn nút 'Đăng ký bổ sung chứng nhận số' ở màn hình danh mục người dùng..<br>Nếu nhất thiết phải đăng nhập dưới cùng một tên người phụ trách<BR>hãy tư vấn ở Call Center.";
                                    CommonMessage.printMsg("", "4", message, "", res);
                                    con.setAutoCommit(true);
                                }
                                else {
                                    if (!oper.equals("d") || urc.isDataExistOnUserCertD(d4, con) != 0) {
                                        id = urc.getUserUpcheID(d4, con);
                                        Log.debug("Id :" + id);
                                        if (i9.equals("Y")) {
                                            urc.updateUserZzangReceive(d4, con);
                                        }
                                        urc.insertUseUser(id, oper, d4, i8, i2, i01, i3, i5, i4, i6, i9, i10, grp, con);
                                        urc.insertUseCert(id, DN1, DN2, CERT1, CERT2, CertFromDate, CertToDate, con);
                                        con.commit();
                                        con.setAutoCommit(true);
                                        res.sendRedirect("/RA/UM_RAJ_ConuC030s01.jsp?id=" + id + "&oper=" + oper);
                                        break Label_2974;
                                    }
                                    message = "Trường hợp là người dùng đầu tiên của hệ thống mua sắm công điện tử thì không thể đăng ký làm người gửi nhận thông tin tiến độ thi công.<br>Đừng chọn vào tùy chọn gửi nhận thông báo về tiến độ thi công mà hãy đăng ký chứng nhận số <BR>sau đó đăng ký thành người dùng khác.<br>Nếu có thắc mắc cần giải đáp, vui lòng gọi đến trung tâm tư vấn số 1588-0800.";
                                    CommonMessage.printMsg("", "4", message, "", res);
                                    con.setAutoCommit(true);
                                }
                            }
                        }
                        try {}
                        catch (Exception ex6) {}
                    }
                    return;
                }
                catch (Exception exm2) {
                    try {
                        if (con != null) {
                            con.rollback();
                        }
                    }
                    catch (Exception ex7) {}
                    try {
                        if (con != null) {
                            con.setAutoCommit(true);
                        }
                    }
                    catch (Exception ex8) {}
                    Log.debug("UM_URV_UserB010c01.java flag[" + flag + "] : " + exm2.toString());
                    CommonMessage.printMsg("", "", exm2.getMessage(), null, res);
                }
                finally {
                    try {
                        if (con != null) {
                            trx.close();
                        }
                    }
                    catch (Exception ex9) {}
                }
                try {
                    if (con != null) {
                        trx.close();
                    }
                }
                catch (Exception ex10) {}
            }
        }
    }
}
