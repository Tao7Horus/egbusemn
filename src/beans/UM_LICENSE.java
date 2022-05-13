// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;

public class UM_LICENSE
{
    public void update_MS_default(final String saupNo, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " update um_enter_std_cls set MAST_STD_CLS_YN='N' where BIZ_REG_NO=? AND MAST_STD_CLS_YN='Y'";
            psmt = con.prepareStatement(query);
            psmt.setString(1, saupNo);
            psmt.executeUpdate();
            psmt.clearParameters();
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".update_MS_default() saupNo[" + saupNo + "]:" + exc.toString());
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
    
    public void update_MS_ZZang(final String saupNo, final String code, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " update um_enter_std_cls set MAST_STD_CLS_YN='Y', UPDATE_DT=sysdate where BIZ_REG_NO=? and STD_CLS_CD=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, saupNo);
            psmt.setString(2, code);
            psmt.executeUpdate();
            psmt.clearParameters();
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".update_MS_ZZang() saupNo[" + saupNo + "], code[" + code + "]:" + exc.toString());
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
    
    public void delete_MS_item(final String saupNo, final String[] code, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " delete um_enter_std_cls where BIZ_REG_NO=? and STD_CLS_CD =?";
            psmt = con.prepareStatement(query);
            for (int i = 0, n = code.length; i < n; ++i) {
                psmt.setString(1, saupNo);
                psmt.setString(2, code[i]);
                psmt.executeUpdate();
                psmt.clearParameters();
            }
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete_MS_item() saupNo[" + saupNo + "]:" + exc.toString());
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
