// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import javax.crypto.NoSuchPaddingException;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.ComUtil;
import common.Trx;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ARV_UserA0012 extends HttpServlet
{
    final String secretKey = "muasamcongmpigov";
    
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt1 = null;
        final PreparedStatement psmt2 = null;
        final PreparedStatement psmt3 = null;
        final PreparedStatement psmt4 = null;
        PreparedStatement psmt5 = null;
        final PreparedStatement psmt6 = null;
        final PreparedStatement psmt7 = null;
        ResultSet rs = null;
        String sql = null;
        String id = "";
        String gubun = "";
        String gubun2 = "";
        String code = "";
        String sabun = "";
        String check1 = "";
        String check2 = "";
        String flag = "";
        String password2 = "";
        String isLock = "";
        String testOptionYn = "";
        String grp = null;
        String grpcount = null;
        final int GRPMAX = 200;
        final DecimalFormat formatcount = new DecimalFormat("00000");
        final int temp = 0;
        flag = ((req.getParameter("flag") == null) ? "" : req.getParameter("flag"));
        id = ((req.getParameter("id") == null) ? "" : req.getParameter("id"));
        gubun = ((req.getParameter("gubun") == null) ? "" : req.getParameter("gubun"));
        gubun2 = ((req.getParameter("gubun2") == null) ? "" : req.getParameter("gubun2"));
        code = ((req.getParameter("code") == null) ? "" : req.getParameter("code"));
        sabun = ((req.getParameter("sabun") == null) ? "" : req.getParameter("sabun"));
        check1 = ((req.getParameter("check1") == null) ? "" : req.getParameter("check1"));
        check2 = ((req.getParameter("check2") == null) ? "" : req.getParameter("check2"));
        password2 = ((req.getParameter("password2") == null) ? "" : req.getParameter("password2"));
        isLock = ((req.getParameter("isLock") == null) ? "" : req.getParameter("isLock"));
        testOptionYn = ((req.getParameter("testOptionYn") == null) ? "" : req.getParameter("testOptionYn"));
        flag = (flag.equals("null") ? "" : flag);
        code = (code.equals("null") ? "" : code);
        id = (id.equals("null") ? "" : id);
        gubun = (gubun.equals("null") ? "" : gubun);
        gubun2 = (gubun2.equals("null") ? "" : gubun2);
        code = (code.equals("null") ? "" : code);
        sabun = (sabun.equals("null") ? "" : sabun);
        password2 = (password2.equals("null") ? "" : password2);
        isLock = (isLock.equals("null") ? "" : isLock);
        testOptionYn = (testOptionYn.equals("null") ? "" : testOptionYn);
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            con.setAutoCommit(false);
            sql = "select user_grp, count(*) from UM_USER where USER_CLS = '" + gubun + "' and user_grp like '" + gubun + "%'" + "group by user_grp " + "order by user_grp desc ";
            psmt1 = con.prepareStatement(sql);
            rs = psmt1.executeQuery();
            if (rs.next()) {
                grp = rs.getString(1);
                grpcount = rs.getString(2);
            }
            sql = "update UM_USER set USER_CLS=?, PERMIT_BRANCH=?, user_grp=?, is_lock=?, TEST_OPTION_YN=? where USER_ID=?";
            psmt5 = con.prepareStatement(sql);
            psmt5.setString(1, ComUtil.retSpace(gubun));
            psmt5.setString(2, ComUtil.retSpace(code));
            psmt5.setString(3, ComUtil.retSpace(grp));
            psmt5.setString(4, ComUtil.retSpace(isLock));
            psmt5.setString(5, ComUtil.retSpace(testOptionYn));
            psmt5.setString(6, ComUtil.retSpace(id));
            psmt5.executeUpdate();
            final String[] subUserName = req.getParameter("v_subUserName").split("\\,", -1);
            final String[] isActive = req.getParameter("v_isActive").split("\\,", -1);
            final String[] password3 = req.getParameter("v_password").split("\\,", -1);
            final String[] subUserFullname = req.getParameter("v_subUserFullname").split("\\,", -1);
            final String[] subUserPos = req.getParameter("v_subUserPos").split("\\,", -1);
            final String[] subUserDept = req.getParameter("v_subUserDept").split("\\,", -1);
            final String[] subUserAddr = req.getParameter("v_subUserAddr").split("\\,", -1);
            final String[] subUserTel = req.getParameter("v_subUserTel").split("\\,", -1);
            final String[] subUserEmail = req.getParameter("v_subUserEmail").split("\\,", -1);
            final String[] subUserId = req.getParameter("v_subUserId").split("\\,", -1);
            final String delStr = "";
            con.commit();
            con.setAutoCommit(true);
            Log.debug("<Thay đổi Quyền hạn> Nhân viên " + sabun + " có mã: " + id + " đã được thay đổi từ " + gubun2 + " thành  " + gubun + ". Tùy chọn CQ Kế toán =" + check1 + " tùy chọn CQ chi trả =" + check2);
            res.sendRedirect("/RA/UM_URJ_UserD030l.jsp");
        }
        catch (SQLException sqlex) {
            try {
                if (con != null) {
                    con.rollback();
                }
            }
            catch (Exception ex2) {}
            try {
                if (con != null) {
                    con.rollback();
                }
            }
            catch (Exception ex3) {}
            Log.debug("UM_ARV_UserA001.java:" + sqlex.toString());
        }
        catch (Exception ex) {
            try {
                if (con != null) {
                    con.rollback();
                }
            }
            catch (Exception ex4) {}
            try {
                if (con != null) {
                    con.rollback();
                }
            }
            catch (Exception ex5) {}
            Log.debug("UM_ARV_UserA001.java:" + ex.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex6) {}
            }
            if (psmt1 != null) {
                try {
                    psmt1.close();
                }
                catch (Exception ex7) {}
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex8) {}
            }
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex9) {}
            }
            if (psmt4 != null) {
                try {
                    psmt4.close();
                }
                catch (Exception ex10) {}
            }
            if (psmt5 != null) {
                try {
                    psmt5.close();
                }
                catch (Exception ex11) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex12) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex13) {}
        }
        if (psmt1 != null) {
            try {
                psmt1.close();
            }
            catch (Exception ex14) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex15) {}
        }
        if (psmt3 != null) {
            try {
                psmt3.close();
            }
            catch (Exception ex16) {}
        }
        if (psmt4 != null) {
            try {
                psmt4.close();
            }
            catch (Exception ex17) {}
        }
        if (psmt5 != null) {
            try {
                psmt5.close();
            }
            catch (Exception ex18) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex19) {}
        }
    }
    
    private String md5(final String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        final byte[] messageDigest = md.digest(input.getBytes("UTF-8"));
        return this.convertByteArrayToHexString(messageDigest);
    }
    
    private Cipher initCipher(final int mode, final String secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
        final SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        final Cipher cipher = Cipher.getInstance("AES");
        cipher.init(mode, skeySpec);
        return cipher;
    }
    
    public String encrypt(final String dataToEncrypt) {
        String encryptedData = null;
        try {
            final Cipher cipher = this.initCipher(1, "muasamcongmpigov");
            final byte[] encryptedByteArray = cipher.doFinal(dataToEncrypt.getBytes());
            encryptedData = new BASE64Encoder().encode(encryptedByteArray);
        }
        catch (Exception e) {
            Log.debug("Problem encrypting the data" + e.getMessage());
        }
        return encryptedData;
    }
    
    public String decrypt(final String encryptedData) {
        String decryptedData = null;
        try {
            if (encryptedData == null || encryptedData.equals("") || encryptedData.equals("null")) {
                return "";
            }
            final Cipher cipher = this.initCipher(2, "muasamcongmpigov");
            final byte[] encryptedByteArray = new BASE64Decoder().decodeBuffer(encryptedData);
            final byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);
            decryptedData = new String(decryptedByteArray, "UTF8");
        }
        catch (Exception e) {
            Log.debug("Problem decrypting the data " + e.getMessage());
        }
        return decryptedData;
    }
    
    private String convertByteArrayToHexString(final byte[] arrayBytes) {
        try {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arrayBytes.length; ++i) {
                final String hex = Integer.toHexString(0xFF & arrayBytes[i]);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            return sb.toString();
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public String getSubUserSeq() {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String ret = "";
        try {
            final StringBuffer sb = new StringBuffer();
            sb.append(" select USEMN.SEQ_SUB_USER.NEXTVAL FROM DUAL ");
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sb.toString());
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ret = rs.getString(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ARV_UserA001.getSubUserSeq:" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ARV_UserA001.getSubUserSeq:" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ret;
    }
}
