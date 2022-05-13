// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.util.Vector;
import entity.UM_EY_Regi_001l;
import java.sql.PreparedStatement;
import common.util.CommUtil;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;

public class UM_EY_Regi_001p
{
    public void insertBank(final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO um_bank_info(institu_cd, institu_full_nm, bank_nm, bank_cd, owner_account_nm, account_number, order_number_account, content_cls, reg_dt, remark, institu_short_nm, institu_chief_nm, institu_address, institu_chrgr_nm, chrgr_post, chrgr_phone, CHRGR_EMAIL, PURPOSE) VALUES(?, ?, ?, ?, ?, ?,?, ?, sysdate, ?,?, ?, ?, ?, ?, ?,?, ? )";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, CommUtil.retSpace(req.getParameter("g2bCode")));
            pstmt.setString(2, CommUtil.retSpace(req.getParameter("instituFullName")));
            pstmt.setString(3, CommUtil.retSpace(req.getParameter("bankName")));
            pstmt.setString(4, CommUtil.retSpace(req.getParameter("bankCd")));
            pstmt.setString(5, CommUtil.retSpace(req.getParameter("accName")));
            pstmt.setString(6, CommUtil.retSpace(req.getParameter("accNumber")));
            pstmt.setString(7, CommUtil.retSpace(req.getParameter("orderNumber")));
            pstmt.setString(8, CommUtil.retSpace(req.getParameter("contentCls")));
            pstmt.setString(9, CommUtil.retSpace(req.getParameter("remark")));
            pstmt.setString(10, CommUtil.retSpace(req.getParameter("instituShortName")));
            pstmt.setString(11, CommUtil.retSpace(req.getParameter("chiefName")));
            pstmt.setString(12, CommUtil.retSpace(req.getParameter("address")));
            pstmt.setString(13, CommUtil.retSpace(req.getParameter("taskMater")));
            pstmt.setString(14, CommUtil.retSpace(req.getParameter("post")));
            pstmt.setString(15, CommUtil.retSpace(req.getParameter("phone")));
            pstmt.setString(16, CommUtil.retSpace(req.getParameter("email")));
            pstmt.setString(17, CommUtil.retSpace(req.getParameter("purpose")));
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Cập nhật dữ liệu lỗi.");
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public UM_EY_Regi_001l[] selectBankList(final int pagenum, final int PAGEMAX, final String code) {
        Connection con = null;
        Trx trx = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_EY_Regi_001l[] bankList = (UM_EY_Regi_001l[])null;
        UM_EY_Regi_001l item = null;
        final String sql = "SELECT institu_cd, institu_full_nm, bank_nm, bank_cd, owner_account_nm, account_number, order_number_account, content_cls, to_char(reg_dt,'DD-MM-YYYY') reg_dt, confirm_dt, answer_results_dt, remark, PURPOSE, NUM FROM ( \tSELECT a.*, ROWNUM NUM FROM ( SELECT institu_cd, institu_full_nm, bank_nm, bank_cd,owner_account_nm, account_number, order_number_account,content_cls, reg_dt, confirm_dt, answer_results_dt, remark, PURPOSE FROM um_bank_info WHERE \tinstitu_cd = '" + code + "') a" + ") " + " WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                item = new UM_EY_Regi_001l();
                item.setG2bCode(rs.getString(1));
                item.setInstituFullName(rs.getString(2));
                item.setBankName(rs.getString(3));
                item.setBankCd(rs.getString(4));
                item.setAccName(rs.getString(5));
                item.setAccNumber(rs.getString(6));
                item.setOrderNumber(rs.getString(7));
                item.setContentCls(rs.getString(8));
                item.setRegisDate(rs.getString(9));
                item.setConformDate(rs.getString(10));
                item.setAnswerDate(rs.getString(11));
                item.setRemark(rs.getString(12));
                item.setPurpose(rs.getString(13));
                vec.addElement(item);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_EY_Regi_001p.select_userlist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_EY_Regi_001p.select_userlist block Exception : " + exc.toString());
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
        if (vec.size() > 0) {
            bankList = new UM_EY_Regi_001l[vec.size()];
            vec.copyInto(bankList);
            return bankList;
        }
        return null;
    }
    
    public int bankCount(final String code) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(*) FROM um_bank_info \n WHERE INSTITU_CD = ? ";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_EY_Regi_001p.bankCount('" + code + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_EY_Regi_001p.bankCount('" + code + "'):" + exc.toString());
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
        return count;
    }
    
    public String getInstituCd(final String id) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String instituCd = "";
        try {
            final String sql = "SELECT MAST_CD FROM UM_USER WHERE USER_ID ='" + id + "'";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                instituCd = rs.getString(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_EY_Regi_001p.getInstituCd('" + id + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_EY_Regi_001p.getInstituCd('" + id + "'):" + exc.toString());
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
        return instituCd;
    }
    
    public UM_EY_Regi_001l selectBank(final String accNumber, final String code) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final UM_EY_Regi_001l item = new UM_EY_Regi_001l();
        final String sql = "SELECT institu_cd, institu_full_nm, bank_nm, bank_cd, owner_account_nm, account_number, order_number_account, content_cls, reg_dt, confirm_dt, answer_results_dt, remark, institu_short_nm, institu_chief_nm, institu_address, institu_chrgr_nm, chrgr_post, chrgr_phone, CHRGR_EMAIL, PURPOSE FROM um_bank_info WHERE \tinstitu_cd = '" + code + "'" + "AND account_number = '" + accNumber + "'";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                item.setG2bCode(rs.getString(1));
                item.setInstituFullName(rs.getString(2));
                item.setBankName(rs.getString(3));
                item.setBankCd(rs.getString(4));
                item.setAccName(rs.getString(5));
                item.setAccNumber(rs.getString(6));
                item.setOrderNumber(rs.getString(7));
                item.setContentCls(rs.getString(8));
                item.setRegisDate(rs.getString(9));
                item.setConformDate(rs.getString(10));
                item.setAnswerDate(rs.getString(11));
                item.setRemark(rs.getString(12));
                item.setInstituShortName(rs.getString(13));
                item.setChiefName(rs.getString(14));
                item.setAddress(rs.getString(15));
                item.setTaskMater(rs.getString(16));
                item.setPost(rs.getString(17));
                item.setPhone(rs.getString(18));
                item.setEmail(rs.getString(19));
                item.setPurpose(rs.getString(20));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_EY_Regi_001p.select_userlist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_EY_Regi_001p.select_userlist block Exception : " + exc.toString());
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
        return item;
    }
    
    public void delBankList(final String accNumberList, final String code, final Connection conn) {
        final ResultSet rs = null;
        PreparedStatement pstm = null;
        final String sql = "DELETE  FROM um_bank_info WHERE  account_number in(" + accNumberList + ")";
        try {
            pstm = conn.prepareStatement(sql);
            if (pstm.executeUpdate() != 1) {
                throw new Exception("Xử lý xóa dữ liệu lỗi.");
            }
            pstm.clearParameters();
        }
        catch (SQLException sqle) {
            Log.debug("UM_EY_Regi_001p.select_userlist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_EY_Regi_001p.select_userlist block Exception : " + exc.toString());
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
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
    }
}
