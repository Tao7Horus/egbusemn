// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import beans.UM_ADB_GovrB030c;
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

public class UM_URV_UserA010c extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        final Secu secu = new Secu(1);
        final UM_URB_CERT urc = new UM_URB_CERT();
        final UM_URB_MyPage myPage = new UM_URB_MyPage();
        UM_Auth_Check uac = null;
        final String flag = (req.getParameter("flag") == null) ? "" : req.getParameter("flag");
        if (!flag.equals("s") && !flag.equals("i")) {
            try {
                uac = new UM_Auth_Check(req, res);
            }
            catch (Exception em) {
                CommonMessage.printMsg("", "", em.getMessage(), null, res);
                return;
            }
        }
        Trx trx = null;
        final Trx emallTrx = null;
        Connection con = null;
        final Connection emallCon = null;
        final PreparedStatement psmt1 = null;
        final PreparedStatement psmt2 = null;
        final ResultSet rs = null;
        String sql = null;
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
        String d01 = "";
        String d2 = "";
        String d3 = "";
        String d4 = "";
        String d5 = "";
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
        String i11 = "";
        String i12 = "";
        String i13 = "";
        String i14 = "";
        String id = "";
        String gubun = "";
        String gubun2 = "";
        String code = "";
        String saup = "";
        String test_op_yn = "";
        String sabun = "";
        String check1 = "";
        String check2 = "";
        String upmuGubun = "";
        String user_gubun = "";
        final String grp = null;
        final String grpcount = null;
        final int GRPMAX = 200;
        final DecimalFormat formatcount = new DecimalFormat("00000");
        final int temp = 0;
        oper = ((req.getParameter("oper") == null) ? "" : req.getParameter("oper"));
        del = ((req.getParameter("del") == null) ? "" : req.getParameter("del"));
        DN1 = ((req.getParameter("DN1") == null) ? "" : req.getParameter("DN1"));
        DN2 = ((req.getParameter("DN2") == null) ? "" : req.getParameter("DN2"));
        CERT2 = ((req.getParameter("CERT2") == null) ? "" : req.getParameter("CERT2"));
        ENC_RANDNUM = ((req.getParameter("ENC_IDENT_INFO") == null) ? "" : req.getParameter("ENC_IDENT_INFO"));
        ENC_KEY = ((req.getParameter("ENC_KEY_INFO") == null) ? "" : req.getParameter("ENC_KEY_INFO"));
        SIGN_LOGIN = ((req.getParameter("SIGN_LOGIN_INFO") == null) ? "" : req.getParameter("SIGN_LOGIN_INFO"));
        id = ((req.getParameter("id") == null) ? "" : req.getParameter("id"));
        gubun = ((req.getParameter("gubun") == null) ? "" : req.getParameter("gubun"));
        gubun2 = ((req.getParameter("gubun2") == null) ? "" : req.getParameter("gubun2"));
        code = ((req.getParameter("code") == null) ? "" : req.getParameter("code"));
        d01 = ((req.getParameter("d01") == null) ? "" : req.getParameter("d01"));
        d2 = ((req.getParameter("d02") == null) ? "" : req.getParameter("d02"));
        d3 = ((req.getParameter("d03") == null) ? "" : req.getParameter("d03"));
        d4 = ((req.getParameter("d04") == null) ? "" : req.getParameter("d04"));
        d5 = ((req.getParameter("d05") == null) ? "" : req.getParameter("d05"));
        i01 = ((req.getParameter("i01") == null) ? "" : req.getParameter("i01"));
        i2 = ((req.getParameter("i02") == null) ? "" : req.getParameter("i02"));
        i3 = ((req.getParameter("i03") == null) ? "" : req.getParameter("i03"));
        i4 = ((req.getParameter("i04") == null) ? "" : req.getParameter("i04"));
        i5 = ((req.getParameter("i05") == null) ? "" : req.getParameter("i05"));
        i6 = ((req.getParameter("i06") == null) ? "" : req.getParameter("i06"));
        i7 = ((req.getParameter("i07") == null) ? "" : req.getParameter("i07"));
        i9 = ((req.getParameter("i08") == null) ? "N" : req.getParameter("i08"));
        i10 = ((req.getParameter("i09") == null) ? "" : req.getParameter("i09"));
        i11 = ((req.getParameter("i101") == null) ? "" : req.getParameter("i101"));
        i12 = ((req.getParameter("i102") == null) ? "" : req.getParameter("i102"));
        i13 = ((req.getParameter("i103") == null) ? "" : req.getParameter("i103"));
        i14 = ((req.getParameter("i104") == null) ? "" : req.getParameter("i104"));
        sabun = ((req.getParameter("sabun") == null) ? "" : req.getParameter("sabun"));
        check1 = ((req.getParameter("check1") == null) ? "" : req.getParameter("check1"));
        check2 = ((req.getParameter("check2") == null) ? "" : req.getParameter("check2"));
        user_gubun = ((req.getParameter("user_gubun") == null) ? "" : req.getParameter("user_gubun"));
        upmuGubun = ((req.getParameter("upmuGubun") == null) ? "" : req.getParameter("upmuGubun"));
        final String strMData1 = null;
        final MessageDigest md = new MessageDigest(secu);
        i8 = md.create(i7);
        final Hashtable hashLog = new Hashtable();
        try {
            DN1 = new String(Base64Util.decode(DN1));
        }
        catch (Exception ex) {}
        if (flag.equals("i")) {
            Label_2819: {
                try {
                    trx = new Trx(this, "USEMN");
                    con = trx.getConnection();
                    con.setAutoCommit(false);
                    String message = "";
                    saup = urc.getSaupjaNumber(d01, con);
                    test_op_yn = urc.getTestOP(saup, con);
                    Label_1384: {
                        if (saup == null) {
                            message = "Ch??a ????ng k?? s??? ????ng k?? kinh doanh tr??n h??? th???ng<br> do ???? kh??ng th???c hi???n ???????c vi???c x??c nh???nNg?????i nh???n: C???c tr?????ng C???c ?????u th???u ";
                            CommonMessage.printMsg("", "4", message, "", res);
                            con.setAutoCommit(true);
                        }
                        else {
                            hashLog.put("1", "saup Query");
                            String strDTM = null;
                            String strSignedMessage = null;
                            String strOriginalMessage = null;
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
                                    throw new NullPointerException("?????i t?????ng gi???i m?? l?? d??? li???u null m?? h??a. Kh??ng th??? l?? null.");
                                }
                                se.decryptInit(1, 1, ENC_KEY);
                                strRandNo = se.decryptUpdate(ENC_RANDNUM);
                                se.decryptFinal();
                            }
                            catch (Exception e) {
                                throw new Exception("L???i ph???n gi???i m?? k?? t???.");
                            }
                            if (strRandNo == null) {
                                throw new Exception("L???i gi???i m?? kh??ng c?? hi???u l???c, Kh??ng th??? l?? null.");
                            }
                            final CertInfo cu = new CertInfo(secu, CERT1);
                            final String strDN = cu.getCertDN();
                            final String CertFromDate = cu.getNotBefore();
                            final String CertToDate = cu.getNotAfter();
                            final String[] OID = { "1.2.410.200005.1.1.5", "1.2.410.200004.5.2.1.1", "1.2.410.200004.5.3.1.1", "1.2.410.200004.5.3.1.2", "1.2.410.200004.5.3.1.5", "1.2.410.200004.5.1.1.7", "1.2.410.200004.5.1.1.12", "1.2.410.200004.5.4.1.2", "1.2.410.200012.1.1.3", "1.2.410.200012.1.1.5", "1.2.410.100001.2.1.1", "1.2.410.100001.2.1.3", "1.2.704.100001.5.1.1.2" };
                            final boolean bChkOIDValid = cu.isValidPolicyOid(OID);
                            hashLog.put("2", "Ki???m tra Ch???ng nh???n s???");
                            if (!bChkOIDValid) {
                                Log.debug("UM_URV_UserA010c.java M?? master [" + d01 + "] ,Ki???m tra quy ?????nh Ch???ng nh???n s??? [" + DN1 + "] th???t b???i:" + cu.getErrorMsg());
                                CommonMessage.printMsg("", "4", "????y l?? ch???ng nh???n s??? kh??ng ???????c ph??p d??ng. H??y ki???m tra l???i tr?????c khi th???c hi???n.", "", res);
                                con.setAutoCommit(true);
                            }
                            else {
                                hashLog.put("3", "Ki???m tra t??nh hi???u l???c");
                                if (!cu.isValid()) {
                                    Log.debug("UM_URV_UserA010c.java M?? master[" + d01 + "] ,Ki???m tra t??nh hi???u l???c Ch???ng nh???n s???[" + DN1 + "]  th???t b???i");
                                    CommonMessage.printMsg("", "4", "Kh??ng ki???m tra ???????c hi???u l???c c???a ch???ng nh???n s???.C?? th??? ????y l?? ch???ng nh???n s??? ???? x??a ho???c ???? h???t hi???u l???c s??? d???ng.", "", res);
                                    con.setAutoCommit(true);
                                }
                                else {
                                    Label_2127: {
                                        try {
                                            if (strRandNo.compareTo("GPKI") == 0) {
                                                break Label_2127;
                                            }
                                            final String dnName = DN1.substring(3, DN1.indexOf(","));
                                            SignUtil sign = null;
                                            sign = new SignUtil();
                                            sign.verifyInit(CertUtil.pemToDer(CERT1));
                                            sign.verifyUpdate(strOriginalMessage.getBytes());
                                            if (sign.verifyFinal(Base64Util.decode(strSignedMessage))) {
                                                break Label_2127;
                                            }
                                            Log.debug("UM_URV_UserA010c.java M?? Master [" + d01 + "] ,Ch???ng nh???n s???  [" + DN1 + "] kh??ng x??c minh ???????c ch??? k?? ??i???n t??? NPKI");
                                            CommonMessage.printMsg("", "", "Kh??ng ki???m tra ???????c ch??? k?? s??? NPKI", null, res);
                                            con.setAutoCommit(true);
                                        }
                                        catch (Exception e2) {
                                            Log.debug("UM_URV_UserA010c.java M?? Master [" + d01 + "] ,Ch???ng nh???n s??? [" + DN1 + "] l???i x??c minh nh??n th??n v?? ch??? k?? ??i???n t???");
                                            CommonMessage.printMsg("", "", "L???i x??c minh nh??n th??n v?? ch??? k?? ??i???n t???", null, res);
                                            con.setAutoCommit(true);
                                        }
                                        break Label_1384;
                                    }
                                    hashLog.put("5", "Ki???m tra ch???ng nh???n s??? ????ng nh???p");
                                    if (urc.isDataExistOnCert(DN1, con) != 0) {
                                        Log.debug("UM_URV_UserA010c.java M?? Master[" + d01 + "] ,Ch???ng nh???n s???[" + DN1 + "] ???? ????ng k?? tr?????c");
                                        CommonMessage.printMsg("", "4", "Ch???ng nh???n s??? n??y ???? ???????c ????ng k??. Kh??ng c???n ????ng k?? n???a.", "", res);
                                        con.setAutoCommit(true);
                                    }
                                    else {
                                        hashLog.put("6", "Ki???m tra t??n ng?????i ph??? tr??ch");
                                        if (urc.isDataExistOnSamePerson(d01, i2, con) == 0) {
                                            sql = " select USER_GRP, count(*)  from UM_USER  where USER_CLS = 'g' and USER_GRP like 'g%' group by USER_GRP  order by USER_GRP desc ";
                                            sql = " select max( USER_ID ) USER_ID   from UM_USER where MAST_CD='" + d01 + "' and   USER_ID like '%" + d01 + "%'";
                                            id = urc.getUserID(d01, con);
                                            hashLog.put("10", id);
                                            if (i9.equals("Y")) {
                                                urc.updateUserZzangReceive(d01, con);
                                            }
                                            hashLog.put("11", i9);
                                            urc.insertUseUser_NEW(test_op_yn, id, d01, i8, i2, i01, i3, i5, i4, i6, i13, i11, i12, i9, i10, i14, check1, check2, grp, upmuGubun, con);
                                            hashLog.put("12", "Ch??n b???ng ng?????i d??ng");
                                            urc.insertUseCert(id, DN1, DN2, CERT1, CERT2, CertFromDate, CertToDate, con);
                                            hashLog.put("13", "insert Th??ng tin Ch???ng nh???n s???");
                                            final UM_ADB_GovrB030c uagb030 = new UM_ADB_GovrB030c();
                                            final String coquan = uagb030.getUserCoQuanCert(id, con);
                                            List list = null;
                                            if (coquan != null && "1".equals(coquan)) {
                                                list = uagb030.getMenuCoQuan(con);
                                                for (int j = 0; j < list.size(); ++j) {
                                                    final String s = (String)list.get(j);
                                                }
                                            }
                                            con.commit();
                                            con.setAutoCommit(true);
                                            res.sendRedirect("/RA/UM_RAJ_GovuB030s.jsp?id=" + id);
                                            break Label_2819;
                                        }
                                        message = "B???n ???? ????ng nh???p b???ng ch???ng nh???n s??? v???i t??n ng?????i ph??? tr??ch tr??ng v???i ng?????i ???? c?? t??? tr?????c trong h??? th???ng.<br>C??ng m???t ng?????i ph??? tr??ch th?? kh??ng th??? ????ng k?? m???i n???a.<BR>Ch??ng t??i ????? ngh??? b???n h??y ????ng k?? b??? sung ch???ng nh???n s??? b???ng c??ch nh???n n??t ????ng k?? b??? sung ch???ng nh???n s??? ??? m??n h??nh danh m???c ng?????i d??ng..<br>N???u nh???t thi???t ph???i ????ng nh???p d?????i c??ng m???t t??n ng?????i ph??? tr??ch<BR>h??y t?? v???n ??? Call Center.";
                                        CommonMessage.printMsg("", "4", message, "", res);
                                        con.setAutoCommit(true);
                                    }
                                }
                            }
                        }
                    }
                    return;
                }
                catch (Exception exm) {
                    try {
                        if (con != null) {
                            con.rollback();
                        }
                    }
                    catch (Exception ex2) {}
                    try {
                        if (con != null) {
                            con.setAutoCommit(true);
                        }
                    }
                    catch (Exception ex3) {}
                    Log.debug("UM_URV_UserA010c.java flag[" + flag + "] :" + hashLog.toString() + ": " + exm.toString());
                    CommonMessage.printMsg("", "4", exm.getMessage(), "", res);
                }
                finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (Exception ex4) {}
                    }
                    if (psmt1 != null) {
                        try {
                            psmt1.close();
                        }
                        catch (Exception ex5) {}
                    }
                    if (psmt2 != null) {
                        try {
                            psmt2.close();
                        }
                        catch (Exception ex6) {}
                    }
                    try {
                        if (con != null) {
                            trx.close();
                        }
                    }
                    catch (Exception ex7) {}
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex8) {}
            }
            if (psmt1 != null) {
                try {
                    psmt1.close();
                }
                catch (Exception ex9) {}
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
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
        else {
            if (flag.equals("m")) {
                try {
                    id = uac.getID();
                    trx = new Trx(this, "USEMN");
                    con = trx.getConnection();
                    con.setAutoCommit(false);
                    urc.updateUseUser(i01, i2, i3, i4, i5, i6, i8, i10, check1, check2, i11, i12, i13, i14, id, upmuGubun, con);
                    urc.updateModifyDate(id, DN1, con);
                    con.commit();
                    con.setAutoCommit(true);
                    CommonMessage.printMsg("004", "1", "", "/GO/UM_GOJ_GoviB010s.jsp", res);
                    return;
                }
                catch (Exception exm) {
                    try {
                        if (con != null) {
                            con.rollback();
                        }
                    }
                    catch (Exception ex12) {}
                    try {
                        if (con != null) {
                            con.setAutoCommit(true);
                        }
                    }
                    catch (Exception ex13) {}
                    Log.debug("UM_URV_UserA010c.java flag[" + flag + "] : " + exm.toString());
                    CommonMessage.printMsg("", "", exm.getMessage(), null, res);
                }
                finally {
                    try {
                        if (con != null) {
                            trx.close();
                        }
                    }
                    catch (Exception ex14) {}
                }
            }
            if (flag.equals("s")) {
                if (!oper.equals("c")) {
                    return;
                }
                try {
                    trx = new Trx(this, "USEMN");
                    con = trx.getConnection();
                    if (urc.isDataExistOnGigwanMaster(d01, con) == 0) {
                        CommonMessage.printMsg("", "4", "M?? Doanh nghi???p nh???p ch??a ???????c ????ng k??.", "", res);
                    }
                    else if (urc.isDataExistOnUserApproveTwo(d01, con) == 0) {
                        res.sendRedirect("/common/terms_gov.jsp?code=" + d01);
                    }
                    else {
                        res.sendRedirect("/RA/UM_URJ_UserD010l.jsp?oper=g&code=" + d01 + "&del=" + del);
                    }
                    return;
                }
                catch (Exception exm) {
                    Log.debug("UM_URV_UserA010c.java flag[" + flag + "] : " + exm.toString());
                    CommonMessage.printMsg("", "4", exm.getMessage(), "", res);
                }
                finally {
                    try {
                        if (con != null) {
                            trx.close();
                        }
                    }
                    catch (Exception ex15) {}
                }
            }
            if (flag.equals("passwdmod")) {
                try {
                    uac.checkModifyPasswd();
                    trx = new Trx(this, "USEMN");
                    con = trx.getConnection();
                    con.setAutoCommit(false);
                    urc.updateUseUserPasswd(i8, id, con);
                    con.commit();
                    con.setAutoCommit(true);
                    CommonMessage.printMsg("", "", "Password ???? ???????c s???a.", null, res);
                }
                catch (Exception exm) {
                    try {
                        if (con != null) {
                            con.rollback();
                        }
                    }
                    catch (Exception ex16) {}
                    try {
                        if (con != null) {
                            con.setAutoCommit(true);
                        }
                    }
                    catch (Exception ex17) {}
                    Log.debug("UM_URV_UserA010c.java flag[" + flag + "] : " + exm.toString());
                    CommonMessage.printMsg("", "", exm.getMessage(), null, res);
                }
                finally {
                    try {
                        if (con != null) {
                            trx.close();
                        }
                    }
                    catch (Exception ex18) {}
                }
            }
        }
    }
}
