// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import entity.ReprItem;
import java.util.List;
import java.sql.PreparedStatement;
import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class UM_RAB_MastReprHist
{
    public OneRowEntity selectDetail(final String reprIdentNo, final String bizRegNo, final String ver, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE, REPR_CLS ");
            sql.append(" from UM_REPR_HIST t1 ");
            sql.append(" where t1.REPR_IDENT_NO = ? ");
            sql.append(" and t1.BIZ_REG_NO = ? ");
            sql.append(" and t1.VER= ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { reprIdentNo, bizRegNo, ver }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + reprIdentNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public OneRowEntity selectNewest(final String reprIdentNo, final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE, REPR_CLS ");
            sql.append(" from UM_REPR_HIST t1 ");
            sql.append(" where t1.REPR_IDENT_NO = ? ");
            sql.append(" and t1.BIZ_REG_NO = ? ");
            sql.append(" and t1.VER=(SELECT MAX(VER) FROM UM_REPR_HIST WHERE BIZ_REG_NO= t1.BIZ_REG_NO AND REPR_IDENT_NO=t1.REPR_IDENT_NO) ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { reprIdentNo, bizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + reprIdentNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public OneRowEntity selectDetail(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE, REPR_CLS ");
            sql.append(" from UM_REPR_HIST t1 ");
            sql.append(" where t1.BIZ_REG_NO = ? ");
            sql.append(" and rownum = 1 ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { bizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public OneRowEntity selectNewest(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE, REPR_CLS ");
            sql.append(" from UM_REPR_HIST t1 ");
            sql.append(" where t1.BIZ_REG_NO = ? ");
            sql.append(" and t1.VER=(SELECT MAX(VER) FROM UM_REPR_HIST WHERE BIZ_REG_NO= t1.BIZ_REG_NO and REPR_IDENT_NO=t1.REPR_IDENT_NO) ");
            sql.append(" and rownum = 1 ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { bizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public void deleteNewest(final String bigRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE UM_REPR_HIST a  ");
            sql.append(" where BIZ_REG_NO=? ");
            sql.append(" AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO ) ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bigRegNo);
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
    
    public void save(final String bizRegNo, final List reprList, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append("insert all");
            ReprItem item = null;
            for (int i = 0; i < reprList.size(); ++i) {
                item = reprList.get(i);
                sql.append(" into UM_REPR_HIST (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE, REG_DT, UPDATE_DT, VER)");
                sql.append(" values('" + bizRegNo + "', '" + item.getReprIdentNo() + "', '" + item.getReprName() + "', '" + item.getReprEmail() + "', '" + item.getReprIsmain() + "', '" + item.getReprMobile() + "', SYSDATE, SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_REPR_HIST WHERE BIZ_REG_NO = '" + bizRegNo + "' ) )");
            }
            sql.append(" SELECT * FROM dual");
            pstmt = conn.prepareStatement(sql.toString());
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
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void save(final String bizRegNo, final String name, final String idenNo, final String mobile, final String email, final String isMain, final String type, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_REPR_HIST (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE, REPR_CLS, VER) ");
            sql.append(" values (?, ?, ?, ?, ?, ?, ?, '00')");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, idenNo);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.setString(5, isMain);
            pstmt.setString(6, mobile);
            pstmt.setString(7, type);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + idenNo + ", " + name + ", " + bizRegNo + ") : " + e.toString());
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
    
    public void modify(final String bizRegNo, final String name, final String idenNo, final String mobile, final String email, final String isMain, final String type, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_REPR_HIST set BIZ_REG_NO=?, REPR_NM=?, REPR_EMAIL=?, REPR_MOBILE=?, REPR_CLS= ?, REPR_ISMAIN=?, ");
            sql.append(" VER=(SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), '1'+MAX(VER) )  FROM UM_REPR_HIST WHERE REPR_IDENT_NO = ? ) ");
            sql.append(" where REPR_IDENT_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, mobile);
            pstmt.setString(5, type);
            pstmt.setString(6, isMain);
            pstmt.setString(7, idenNo);
            pstmt.setString(8, idenNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + idenNo + ", " + bizRegNo + ", " + name + ", " + isMain + ", " + ") : " + e.toString());
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
    
    public void save(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_REPR_HIST (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE, REG_DT, UPDATE_DT, VER) ");
            sql.append(" SELECT BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE, REG_DT, SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00') FROM UM_REPR_HIST WHERE BIZ_REG_NO = ? ) FROM UM_REPR WHERE BIZ_REG_NO= ?  ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizRegNo);
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
    
    public void saveHist(final String bizRegNo, final String userId, final String sUId, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_REPR_HIST (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE, REG_DT, UPDATE_DT, VER, USER_ID, SUB_USER_ID) ");
            sql.append(" SELECT BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE, REG_DT, SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00') FROM UM_REPR_HIST WHERE BIZ_REG_NO = ? ), '" + userId + "' USER_ID, '" + sUId + "' SUB_USER_ID FROM UM_REPR WHERE BIZ_REG_NO= ?  ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizRegNo);
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
    
    public void updateTemp(final String saupNo, final String juminNumber, final String ceoName, final String ceoMail, final String ceoPhone, final String ceoYn, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " INSERT INTO UM_REPR_HIST (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_MOBILE, MAST_REPR_YN, REG_DT, UPDATE_DT, REPR_CLS, VER )  SELECT BIZ_REG_NO, REPR_IDENT_NO, ?, ? , ? , ? , REG_DT, SYSDATE, REPR_CLS,  (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO= ?) FROM UM_REPR WHERE  REPR_IDENT_NO = ?  AND BIZ_REG_NO = ?    ";
            psmt = con.prepareStatement(query);
            psmt.setString(1, ceoName);
            psmt.setString(2, ceoMail);
            psmt.setString(3, ceoPhone);
            psmt.setString(4, ceoYn);
            psmt.setString(5, saupNo);
            psmt.setString(6, juminNumber);
            psmt.setString(7, saupNo);
            psmt.executeUpdate();
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
}
