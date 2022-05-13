// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.sql.PreparedStatement;
import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class UM_RAB_RecEnterCls
{
    public OneRowEntity selectDetail(final String bizRezNo, final String regisDate, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, ENTER_CLS, REG_DT, REG_USER_ID, MOD_DT, MOD_USER_ID, USE_YN ");
            sql.append(" from UM_REC_ENTER_CLS t1 ");
            sql.append(" where t1.BIZ_REG_NO = ? ");
            sql.append(" \tand t1.REG_DT = to_date(?,'yyyy/mm/dd') ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { bizRezNo, regisDate }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + bizRezNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public void delete(final String bizRezNo, final String regisDate, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_REC_ENTER_CLS ");
            sql.append(" where BIZ_REG_NO= ? ");
            sql.append(" \tand REG_DT= ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRezNo);
            pstmt.setString(2, regisDate);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Xóa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + bizRezNo + ") : " + e.toString());
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
    
    public void save(final String bizRezNo, final String enterCls, final String regDate, final String regUserId, final String modDate, final String modUserId, final String useYn, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_REC_ENTER_CLS (BIZ_REG_NO, ENTER_CLS, REG_DT, REG_USER_ID, MOD_DT, MOD_USER_ID, USE_YN) ");
            sql.append(" values (?, ?, ?, ?, ?, ?, ? )");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRezNo);
            pstmt.setString(2, enterCls);
            pstmt.setString(3, regDate);
            pstmt.setString(4, regUserId);
            pstmt.setString(5, modDate);
            pstmt.setString(6, modUserId);
            pstmt.setString(7, useYn);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + bizRezNo + ", " + enterCls + ", " + regDate + ") : " + e.toString());
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
    
    public void modify(final String bizRezNo, final String enterCls, final String regDate, final String regUserId, final String modDate, final String modUserId, final String useYn, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_REC_ENTER_CLS set ENTER_CLS=?, MOD_DT=?, MOD_USER_ID= ?, USE_YN= ? ");
            sql.append(" where BIZ_REG_NO= ? ");
            sql.append(" and REG_DT= to_date(?,'yyyy/mm/dd')");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, enterCls);
            pstmt.setString(2, modDate);
            pstmt.setString(3, modUserId);
            pstmt.setString(4, useYn);
            pstmt.setString(5, bizRezNo);
            pstmt.setString(6, regDate);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRezNo + ", " + regDate + ", " + enterCls + ", " + regUserId + ") : " + e.toString());
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
