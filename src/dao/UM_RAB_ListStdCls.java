// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.sql.PreparedStatement;
import common.CommEntity;
import java.util.Vector;
import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class UM_RAB_ListStdCls
{
    public OneRowEntity selectDetail(final String clsCode, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select STD_CLS_CD, STD_CLS_NAME, STD_CLS_DES ");
            sql.append(" from UM_LIST_STD_CLS t1 ");
            sql.append(" where t1.STD_CLS_CD = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { clsCode }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + clsCode + ") : " + e.toString());
            throw e;
        }
    }
    
    public int getListCount(final String stdClsCode, final String stdClsName, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        parameterV.add("%" + stdClsCode + "%");
        parameterV.add("%" + stdClsName + "%");
        try {
            sql.append(" select count(*) ");
            sql.append(" from UM_LIST_STD_CLS t1 ");
            sql.append(" where t1.STD_CLS_CD like ? ");
            sql.append(" \tand t1.STD_CLS_NAME like ? ");
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getCount(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getListCount(" + stdClsCode + ", " + stdClsName + ") : " + e.toString());
            throw e;
        }
    }
    
    public CommEntity[] getList(final String stdClsCode, final String stdClsName, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select STD_CLS_CD, STD_CLS_NAME, STD_CLS_DES ");
            sql.append(" from UM_LIST_STD_CLS t1 ");
            sql.append(" where t1.STD_CLS_CD like ? ");
            sql.append(" \tand t1.STD_CLS_NAME like ? ");
            parameterV.add("%" + stdClsCode + "%");
            parameterV.add("%" + stdClsName + "%");
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getList() : " + e.toString());
            throw e;
        }
    }
    
    public CommEntity[] getList(final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select STD_CLS_CD, STD_CLS_NAME, STD_CLS_DES ");
            sql.append(" from UM_LIST_STD_CLS ");
            return new CommDbQuery().getList(this, sql.toString(), conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getList() : " + e.toString());
            throw e;
        }
    }
    
    public void delete(final String clsCode, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_LIST_STD_CLS ");
            sql.append(" where STD_CLS_CD= ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, clsCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[X??a b??? l???i]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + clsCode + ") : " + e.toString());
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
    
    public void save(final String clsCode, final String clsName, final String clsDes, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_LIST_STD_CLS (STD_CLS_CD, STD_CLS_NAME, STD_CLS_DES) ");
            sql.append(" values (?, ?, ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, clsCode);
            pstmt.setString(2, clsName);
            pstmt.setString(3, clsDes);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[L???i ????ng k??]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + clsCode + ", " + clsName + ", " + clsDes + ") : " + e.toString());
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
    
    public void modify(final String clsCode, final String clsName, final String clsDes, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_LIST_STD_CLS set STD_CLS_NAME=?, STD_CLS_DES=? ");
            sql.append(" where STD_CLS_CD=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, clsName);
            pstmt.setString(2, clsDes);
            pstmt.setString(3, clsCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[S???a b??? l???i]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + clsCode + ", " + clsName + ", " + clsDes + ") : " + e.toString());
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
