// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Vector;
import entity.ReprItem;
import common.ComDbQuery;
import common.CommEntity;
import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;

public class UM_CEO
{
    public void updateAllMainCeoFlag(final String saupNo, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " update UM_REPR set MAST_REPR_YN='N' WHERE BIZ_REG_NO = ? and MAST_REPR_YN='Y' ";
            psmt = con.prepareStatement(query);
            psmt.setString(1, saupNo);
            psmt.executeUpdate();
            psmt.clearParameters();
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".updateAllMainCeoFlag() saupNo[" + saupNo + "]:" + exc.toString());
            throw exc;
        }
        finally {
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void updateMainCeoInfo(final String saupNo, final String juminNumber, final String ceoMail, final String ceoPhone, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " update UM_REPR set REPR_EMAIL=?, MAST_REPR_YN='Y', UPDATE_DT=SYSDATE, REPR_MOBILE=? WHERE BIZ_REG_NO = ? and REPR_IDENT_NO=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, ceoMail);
            psmt.setString(2, ceoPhone);
            psmt.setString(3, saupNo);
            psmt.setString(4, juminNumber);
            if (psmt.executeUpdate() != 1) {
                throw new Exception("수정 중에 오류가 발생했습니다.[수정갯수 1 이 아님]");
            }
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".updateMainCeoInfo() saupNo[" + saupNo + "],juminNumber[" + juminNumber + "]:" + exc.toString());
            throw exc;
        }
        finally {
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void updateCeoInfo(final String saupNo, final String juminNumber, final String ceoMail, final String ceoPhone, final String ceoYn, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " update UM_REPR set REPR_EMAIL=?, MAST_REPR_YN= ?, UPDATE_DT=SYSDATE, REPR_MOBILE=? WHERE BIZ_REG_NO = ? and REPR_IDENT_NO=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, ceoMail);
            psmt.setString(2, ceoYn);
            psmt.setString(3, ceoPhone);
            psmt.setString(4, saupNo);
            psmt.setString(5, juminNumber);
            if (psmt.executeUpdate() != 1) {
                throw new Exception("수정 중에 오류가 발생했습니다.[수정갯수 1 이 아님]");
            }
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".updateMainCeoInfo() saupNo[" + saupNo + "],juminNumber[" + juminNumber + "]:" + exc.toString());
            throw exc;
        }
        finally {
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void updateCeoInfo(final String saupNo, final String juminNumber, final String ceoMail, final String ceoPhone, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " update  UM_REPR set REPR_EMAIL=?, UPDATE_DT=SYSDATE,REPR_MOBILE=? WHERE BIZ_REG_NO = ? and REPR_IDENT_NO=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, ceoMail);
            psmt.setString(2, ceoPhone);
            psmt.setString(3, saupNo);
            psmt.setString(4, juminNumber);
            final int res = psmt.executeUpdate();
            if (res != 1) {
                throw new Exception("Có một sửa lỗi [Không sửa đổi số 1.");
            }
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".updateCeoInfo() saupNo[" + saupNo + "],juminNumber[" + juminNumber + "]:" + exc.toString());
            throw exc;
        }
        finally {
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public CommEntity[] getChangeBeforeData(final String saupjaNumber, final String juminNumber, final Connection conn) throws Exception {
        final String sql = " select A.REPR_NM, A.REPR_IDENT_NO, B.REPR_NM, B.REPR_IDENT_NO from  (select REPR_NM, REPR_IDENT_NO  from UM_REPR  where BIZ_REG_NO=? and MAST_REPR_YN='Y'  and rownum=1 ) A, (select REPR_NM, REPR_IDENT_NO from UM_REPR  where BIZ_REG_NO=? and REPR_IDENT_NO=?) B ";
        final String[] parameter = { saupjaNumber, saupjaNumber, juminNumber };
        try {
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exf) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getChangeBeforeData() saupNo[" + saupjaNumber + "],juminNumber[" + juminNumber + "]:" + exf.toString());
            throw exf;
        }
    }
    
    public ReprItem[] selectUpdate(final String bizRegNo, final Connection conn) throws Exception {
        final String sql = " SELECT REPR_NM, REPR_IDENT_NO, REPR_MOBILE, REPR_EMAIL, MAST_REPR_YN FROM  UM_REPR_HIST a WHERE BIZ_REG_NO = ? AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO) ";
        PreparedStatement psmt = null;
        ResultSet res = null;
        ReprItem item = null;
        final Vector vec = new Vector();
        ReprItem[] results = (ReprItem[])null;
        final String[] parameters = { bizRegNo };
        try {
            psmt = conn.prepareStatement(sql);
            for (int i = 0; i < parameters.length; ++i) {
                psmt.setString(i + 1, parameters[i]);
            }
            res = psmt.executeQuery();
            while (res.next()) {
                item = new ReprItem(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5));
                vec.add(item);
                item = null;
            }
            results = new ReprItem[vec.size()];
            vec.copyInto(results);
            return results;
        }
        catch (Exception exf) {
            Log.errors(this, exf, "");
            throw exf;
        }
    }
    
    public void delete(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_REPR ");
            sql.append(" where BIZ_REG_NO = ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void save(final String bizRegNo, final ReprItem[] items, final Connection conn) throws Exception {
        if (items.length < 1) {
            return;
        }
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            ReprItem item = null;
            sql.append("INSERT ALL ");
            final List params = new ArrayList();
            for (int i = 0; i < items.length; ++i) {
                item = items[i];
                sql.append(" INTO UM_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_MOBILE, MAST_REPR_YN, REG_DT, UPDATE_DT) ");
                sql.append(" VALUES (?, ?, ?, ?, ?, ?, (SELECT REG_DT FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO= ?),  SYSDATE) ");
                params.add(bizRegNo);
                params.add(item.getReprIdentNo());
                params.add(item.getReprName());
                params.add(item.getReprEmail());
                params.add(item.getReprMobile());
                params.add(item.getReprIsmain());
                params.add(bizRegNo);
            }
            sql.append(" SELECT * FROM dual ");
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); ++i) {
                pstmt.setString(i + 1, (params.get(i) == null) ? "" : params.get(i).toString());
            }
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".save(" + bizRegNo + ")\n\n" + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
    }
}
