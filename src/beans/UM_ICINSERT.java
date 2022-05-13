// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;

public class UM_ICINSERT
{
    private void IcInsert(final String saupNo, final int ilno, final String tabGubun, final String gubun, final String beComment, final String afComment, final String item, final String procID, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " INSERT INTO UM_BID_QUALIFY_FACTS_HIST (  BIZ_REG_NO, UPDATE_DT, SERIAL_NO, TABLE_CLS, CHANGE_CLS, BEFORE_MOD_CONTENT, AFTER_MOD_CONTENT, PROPERTY_NM, MANAGER_ID  ) VALUES (  ?, sysdate, ?, ?, ?, ?, ?, ?, ?  ) ";
            psmt = con.prepareStatement(query);
            psmt.setString(1, saupNo);
            psmt.setInt(2, ilno);
            psmt.setString(3, tabGubun);
            psmt.setString(4, gubun);
            psmt.setString(5, beComment);
            psmt.setString(6, afComment);
            psmt.setString(7, item);
            psmt.setString(8, procID);
            psmt.executeUpdate();
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".IcInsert() 사업자[" + saupNo + "], 테이블구분[" + tabGubun + "]:" + exc.toString());
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
    
    public void insertDPHistory(final String saupNo, final int seq, final String gubun, final String beComment, final String afComment, final String item, final String procID, final Connection conn) throws Exception {
        this.IcInsert(saupNo, seq, "5", gubun, beComment, afComment, item, procID, conn);
    }
    
    public void deleteJPHistory(final String saupNo, final String[] code, final String procID, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " INSERT INTO UM_BID_QUALIFY_FACTS_HIST (  BIZ_REG_NO, UPDATE_DT, SERIAL_NO, TABLE_CLS, CHANGE_CLS, BEFORE_MOD_CONTENT, PROPERTY_NM, MANAGER_ID  ) VALUES (  ?, sysdate, ?, '3', 'D', gt_query('',?,'1')||'('||?||')', 'sản xuất hàng hóa', ? )";
            psmt = con.prepareStatement(query);
            for (int i = 0, n = code.length; i < n; ++i) {
                psmt.setString(1, saupNo);
                psmt.setInt(2, i + 1);
                psmt.setString(3, code[i]);
                psmt.setString(4, code[i]);
                psmt.setString(5, procID);
                psmt.executeUpdate();
                psmt.clearParameters();
            }
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".deleteJPHistory() Kinh doanh[" + saupNo + "], ID[" + procID + "]:" + exc.toString());
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
    
    public void deleteGPHistory(final String saupNo, final String[] code, final String procID, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " INSERT INTO UM_BID_QUALIFY_FACTS_HIST (  BIZ_REG_NO, UPDATE_DT, SERIAL_NO, TABLE_CLS, CHANGE_CLS, BEFORE_MOD_CONTENT, PROPERTY_NM, MANAGER_ID  ) VALUES (  ?, sysdate, ?, '3', 'D', gt_query('',?,'1')||'('||?||')', 'cung cấp sản phẩm', ? )";
            psmt = con.prepareStatement(query);
            for (int i = 0, n = code.length; i < n; ++i) {
                psmt.setString(1, saupNo);
                psmt.setInt(2, i + 1);
                psmt.setString(3, code[i]);
                psmt.setString(4, code[i]);
                psmt.setString(5, procID);
                psmt.executeUpdate();
                psmt.clearParameters();
            }
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + "deleteGPHistory 사업자[" + saupNo + "], ID[" + procID + "]:" + exc.toString());
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
    
    public void insertGPHistory(final String saupNo, final String code, final String procID, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " INSERT INTO UM_BID_QUALIFY_FACTS_HIST (  BIZ_REG_NO, UPDATE_DT, SERIAL_NO, TABLE_CLS, CHANGE_CLS, AFTER_MOD_CONTENT, PROPERTY_NM, MANAGER_ID  ) VALUES (  ?, sysdate, ?, '3', 'I', gt_query('',?,'1')||'('||?||')', 'cung cấp sản phẩm', ? )";
            psmt = con.prepareStatement(query);
            psmt.setString(1, saupNo);
            psmt.setInt(2, 1);
            psmt.setString(3, code);
            psmt.setString(4, code);
            psmt.setString(5, procID);
            psmt.executeUpdate();
            psmt.clearParameters();
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + " insertGPHistory kinh doanh[" + saupNo + "], ID[" + procID + "]:" + exc.toString());
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
    
    public void deleteMSHistory(final String saupNo, final String[] code, final String procID, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " INSERT INTO UM_BID_QUALIFY_FACTS_HIST (  BIZ_REG_NO, UPDATE_DT, SERIAL_NO, TABLE_CLS, CHANGE_CLS, BEFORE_MOD_CONTENT, PROPERTY_NM, MANAGER_ID  ) VALUES (  ?, sysdate, ?, '3', 'D', gt_query('GU9',?,'2')||'('||?||')', 'ngành dịch vụ.04', ? )";
            psmt = con.prepareStatement(query);
            for (int i = 0, n = code.length; i < n; ++i) {
                psmt.setString(1, saupNo);
                psmt.setInt(2, i + 1);
                psmt.setString(3, code[i]);
                psmt.setString(4, code[i]);
                psmt.setString(5, procID);
                psmt.executeUpdate();
                psmt.clearParameters();
            }
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".deleteMSHistory() kinh doanh[" + saupNo + "], ID[" + procID + "]:" + exc.toString());
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
