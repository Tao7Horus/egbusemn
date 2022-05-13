// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;
import common.util.UM_COB_GTQ904;
import common.util.UM_COE_GTQ903;

public class UM_ADB_ConrG020c
{
    public UM_COE_GTQ903[] getUSData(final String saupjaNumber) throws Exception {
        final String sql = " SELECT \tTO_CHAR(A.RAISED_DT, 'YYYY-MM-DD'),TO_CHAR(A.START_DT, 'YYYY-MM-DD'),TO_CHAR(A.END_DT, 'YYYY-MM-DD'), \t\tB.CD_NM, A.REMARK, A.STATE_CLS, B.CD, A.REMARK, TO_CHAR(A.PROCESS_DT, 'YYYY-MM-DD HH24:MI:SS')  FROM UM_ENTER_STATE A, SYN_PUB_CODE B  WHERE A.BIZ_REG_NO = '" + saupjaNumber + "' " + " AND B.CD_CLS = 'J11' " + " AND A.STATE_CLS = B.CD";
        return new UM_COB_GTQ904().getList(this, sql, null);
    }
    
    public String getSqlDataOne(final String saupjaNumber) throws Exception {
        final String sql = "select CORP_REG_NO from UM_SUPPLIER_ENTER_MAST  where BIZ_REG_NO='" + saupjaNumber + "'";
        return new UM_COB_GTQ904().getSqlDataOne(this, sql, null);
    }
    
    public void deleteUpcheStateTable(final String saupjaNumber, final String upcheState, final Connection conn) throws Exception {
        final String sql = "delete UM_ENTER_STATE where BIZ_REG_NO=? and STATE_CLS=?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupjaNumber);
            pstmt.setString(2, upcheState);
            if (pstmt.executeUpdate() != 1) {
                Log.debug("UM_ADB_ConrG020c.deleteUpcheStateTable('" + saupjaNumber + "','" + upcheState + "') Xóa số 1 hoặc 2");
                throw new Exception("Xóa số 1 hoặc 2");
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
    
    public int getCount(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = " SELECT COUNT(*) FROM UM_ENTER_STATE                 \t WHERE BIZ_REG_NO='" + saupjaNumber + "'\t\t" + "   AND STATE_CLS = '07' AND REMARK = 'ADMIN'         ";
        return new UM_COB_GTQ904().getCount(this, sql, conn);
    }
    
    public void updateUpcheTable(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = " update UM_SUPPLIER_ENTER_MAST set UPDATE_DT=sysdate where BIZ_REG_NO=? ";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupjaNumber);
            if (pstmt.executeUpdate() != 1) {
                Log.debug("UM_ADB_ConrG020c.updateUpcheTable() BIZ_REG_NO[" + saupjaNumber + "] update Số 1 hoặc 2");
                throw new Exception("update Số 1 hoặc 2");
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
    
    public void updateUpcheStateTable(final String saupjaNumber, final String beforeUpcheState, final String afterUpcheState, final String startDate, final String endDate, final String etc, final String procID, final Connection conn) throws Exception {
        final String sql = "update UM_ENTER_STATE set START_DT=?, END_DT=?, STATE_CLS=?, REMARK=?, REMARK=?, PROCESS_DT=sysdate where BIZ_REG_NO=? and STATE_CLS=? ";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, startDate);
            pstmt.setString(2, endDate);
            pstmt.setString(3, afterUpcheState);
            pstmt.setString(4, etc);
            pstmt.setString(5, procID);
            pstmt.setString(6, saupjaNumber);
            pstmt.setString(7, beforeUpcheState);
            if (pstmt.executeUpdate() != 1) {
                Log.debug("UM_ADB_ConrG020c.updateUpcheStateTable() BIZ_REG_NO[" + saupjaNumber + "], 신규상태구분[" + afterUpcheState + "] update Số 1 hoặc 2");
                throw new Exception("update Số 1 hoặc 2");
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
    
    public int getCount(final String saupjaNumber, final String upcheState, final Connection conn) throws Exception {
        final String sql = "select count(*) from UM_ENTER_STATE where BIZ_REG_NO='" + saupjaNumber + "' and STATE_CLS='" + upcheState + "'";
        return new UM_COB_GTQ904().getCount(this, sql, conn);
    }
    
    public void insertUpcheStateTable(final String saupjaNumber, final String occDate, final String startDate, final String endDate, final String UpcheState, final String etc, final String procID, final Connection conn) throws Exception {
        final String sql = " insert into UM_ENTER_STATE(BIZ_REG_NO, RAISED_DT, START_DT, END_DT, STATE_CLS, REMARK, REMARK, PROCESS_DT) values(?, ?, ?, ?, ?, ?, ?, sysdate) ";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupjaNumber);
            pstmt.setString(2, occDate);
            pstmt.setString(3, startDate);
            pstmt.setString(4, endDate);
            pstmt.setString(5, UpcheState);
            pstmt.setString(6, etc);
            pstmt.setString(7, procID);
            if (pstmt.executeUpdate() != 1) {
                Log.debug("UM_ADB_ConrG020c.insertUpcheStateTable() BIZ_REG_NO[" + saupjaNumber + "], STATE_CLS[" + UpcheState + "] insert Số 1 hoặc 2");
                throw new Exception("insert Số 1 hoặc 2");
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
}
