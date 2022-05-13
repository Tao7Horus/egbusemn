// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import entity.ReprItem;
import java.util.List;
import java.sql.PreparedStatement;
import common.CommEntity;
import java.util.Vector;
import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class UM_RAB_RecRepr
{
    public OneRowEntity selectDetail(final String reprIdentNo, final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE, REPR_CLS ");
            sql.append(" from UM_REC_REPR t1 ");
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
            sql.append(" from UM_REC_REPR t1 ");
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
            sql.append(" from UM_REC_REPR t1 ");
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
            sql.append(" select BIZ_REG_NO, REPR_NM, REPR_IDENT_NO, REPR_MOBILE, REPR_EMAIL, REPR_ISMAIN");
            sql.append(" from UM_REC_REPR t1 ");
            sql.append(" where  BIZ_REG_NO = ? order by REPR_ISMAIN desc");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getList(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public void delete(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" delete from UM_REC_REPR  ");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + bizRegNo + ") : " + e.toString());
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
                sql.append(" into UM_REC_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE)");
                sql.append(" values('" + bizRegNo + "', '" + item.getReprIdentNo() + "', '" + item.getReprName() + "', '" + item.getReprEmail() + "', '" + item.getReprIsmain() + "', '" + item.getReprMobile() + "')");
            }
            sql.append(" SELECT * FROM dual");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + bizRegNo + ")\n\n" + e.toString());
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
            sql.append(" insert into UM_REC_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE, REPR_CLS) ");
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
            sql.append(" update UM_REC_REPR set BIZ_REG_NO=?, REPR_NM=?, REPR_EMAIL=?, REPR_MOBILE=?, REPR_CLS= ?, REPR_ISMAIN=?");
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
