// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.util.ArrayList;
import entity.ReprItem;
import java.util.List;
import java.sql.PreparedStatement;
import common.CommEntity;
import java.util.Vector;
import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class UM_RAB_MastRepr
{
    public OneRowEntity selectDetail(final String reprIdentNo, final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE, REPR_CLS ");
            sql.append(" from UM_REPR t1 ");
            sql.append(" where t1.REPR_IDENT_NO = ? ");
            sql.append(" and t1.BIZ_REG_NO = ? ");
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
            sql.append(" from UM_REPR t1 ");
            sql.append(" where t1.BIZ_REG_NO = ? ");
            sql.append(" and rownum = 1 ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { bizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public int getListCount(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select count(*) ");
            sql.append(" from UM_REPR t1 ");
            sql.append(" where BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getCount(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getListCount(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public CommEntity[] getList(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select BIZ_REG_NO, REPR_NM, REPR_IDENT_NO, REPR_MOBILE, REPR_EMAIL, MAST_REPR_YN");
            sql.append(" from UM_REPR t1 ");
            sql.append(" where  BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public void delete(final String reprIdentNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" delete from UM_REPR  ");
            sql.append(" where REPR_IDENT_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, reprIdentNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Xóa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + reprIdentNo + ") : " + e.toString());
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
    
    public void deleteAll(final String bizRegNno, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" delete from UM_REPR  ");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNno);
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
        if (reprList.size() <= 0) {
            return;
        }
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append("insert all");
            ReprItem item = null;
            for (int i = 0; i < reprList.size(); ++i) {
                item = (ReprItem)reprList.get(i);
                sql.append(" into UM_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE, REG_DT)");
                sql.append(" values('" + bizRegNo + "', '" + item.getReprIdentNo() + "', '" + item.getReprName() + "', '" + item.getReprEmail() + "', '" + item.getReprIsmain() + "', '" + item.getReprMobile() + "', SYSDATE)");
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
    
    public void saveFromRec(final String bizRegNo, final Connection conn) throws Exception {

        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        sql.append("INSERT INTO UM_REPR ");
        sql.append("    (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE, REG_DT) ");
        sql.append("SELECT ");
        sql.append("    BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE, SYSDATE ");
        sql.append("FROM UM_REC_REPR WHERE BIZ_REG_NO = ?");
        
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
        	Log.debug(String.valueOf(this.getClass().getName()) + ".saveFromRec(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {
                	Log.debug(String.valueOf(this.getClass().getName()) + ".saveFromRec(" + bizRegNo + ") : " + ex.toString());
                    throw ex;
                }
            }
        }
    }
    
    public void saveAgain(final String bizRegNo, final List reprList, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append("insert all");
            ReprItem item = null;
            final List params = new ArrayList();
            for (int i = 0; i < reprList.size(); ++i) {
                item = (ReprItem)reprList.get(i);
                sql.append(" into UM_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE, UPDATE_DT, REG_DT )");
                sql.append(" values(?, ?, ?, ?, ?, ?, SYSDATE, (SELECT REG_DT FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO = ?) ) ");
                params.add(bizRegNo);
                params.add(item.getReprIdentNo());
                params.add(item.getReprName());
                params.add(item.getReprEmail());
                params.add(item.getReprIsmain());
                params.add(item.getReprMobile());
                params.add(bizRegNo);
            }
            sql.append(" SELECT * FROM dual");
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); ++i) {
                pstmt.setString(i + 1, params.get(i).toString());
            }
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
    
    public void update(final String bizRegNo, final List reprList, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" UPDATE UM_REPR SET REPR_IDENT_NO = ?, REPR_NM = ?, REPR_EMAIL= ?, MAST_REPR_YN = ?, REPR_MOBILE= ?, UPDATE_DT = SYSDATE WHERE BIZ_REG_NO = ?");
            pstmt = conn.prepareStatement(sql.toString());
            ReprItem item = null;
            for (int i = 0; i < reprList.size(); ++i) {
                item = (ReprItem)reprList.get(i);
                Log.debug(String.valueOf(item.getReprIdentNo()) + "-------");
                Log.debug(String.valueOf(item.getReprName()) + "-------");
                pstmt.setString(1, item.getReprIdentNo());
                pstmt.setString(2, item.getReprName());
                pstmt.setString(3, item.getReprEmail());
                pstmt.setString(4, item.getReprIsmain());
                pstmt.setString(5, item.getReprMobile());
                pstmt.setString(6, bizRegNo);
                pstmt.executeUpdate();
                pstmt.clearParameters();
            }
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
            sql.append(" insert into UM_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE, REPR_CLS) ");
            sql.append(" values (?, ?, ?, ?, ?, ?, ?)");
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
            sql.append(" update UM_REPR set BIZ_REG_NO=?, REPR_NM=?, REPR_EMAIL=?, REPR_MOBILE=?, REPR_CLS= ?, REPR_ISMAIN=?");
            sql.append(" where REPR_IDENT_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, mobile);
            pstmt.setString(5, type);
            pstmt.setString(6, isMain);
            pstmt.setString(7, idenNo);
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
}
