// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import entity.GoodItem;
import java.util.List;
import java.sql.PreparedStatement;
import common.Log;
import common.CommDbQuery;
import java.util.Vector;
import common.CommEntity;
import java.sql.Connection;

public class UM_RAB_RecGood
{
    public CommEntity[] getList(final String bizRegNo, final Connection conn) throws Exception {
        final Vector parameterV = new Vector();
        try {
            final String sql = "select CD_ITEM, MAST_YN_ITEM, UPDATE_DT from UM_REC_SUPPLIER_ITEMS where BIZ_REG_NO = ?";
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
            sql.append(" DELETE FROM UM_REC_SUPPLIER_ITEMS ");
            sql.append(" where BIZ_REG_NO = ? ");
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
    
    public void save(final String bizRezNo, final List goodList, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append("insert all ");
            GoodItem item = null;
            for (int i = 0; i < goodList.size(); ++i) {
                item = goodList.get(i);
                sql.append(" into UM_REC_SUPPLIER_ITEMS (BIZ_REG_NO, CD_ITEM, MAST_YN_ITEM, UPDATE_DT)");
                sql.append(" values('" + bizRezNo + "', '" + item.getGoodCode() + "', '" + item.getMastGoodYn() + "', sysdate)");
            }
            sql.append(" SELECT * FROM dual");
            System.out.println(sql.toString());
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + bizRezNo + ")\n\n" + e.toString());
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
    
    public void modify(final String bizRezNo, final String goodCode, final String mastGoodYn, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_REC_SUPPLIER_ITEMS set CD_ITEM=?, MAST_YN_ITEM=?, UPDATE_DT=sysdate");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, goodCode);
            pstmt.setString(2, mastGoodYn);
            pstmt.setString(2, bizRezNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRezNo + ", " + e.toString());
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
