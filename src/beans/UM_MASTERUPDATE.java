// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;

public class UM_MASTERUPDATE
{
    public void MasterUpdate(final String saupNo, final String eSangho, final int employeeNo, final String tel, final String fax, final String homepage, final String procID, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " UPDATE UM_SUPPLIER_ENTER_MAST SET  BIZ_EN_NM = ?, EMPLOYEE_COUNT = ?, PHONE_NO = ?, FAX = ?, WEBSITE = ?, UPDATE_DT = SYSDATE, MANAGER_ID = ?  WHERE BIZ_REG_NO = ? ";
            psmt = con.prepareStatement(query);
            psmt.setString(1, eSangho);
            psmt.setInt(2, employeeNo);
            psmt.setString(3, tel);
            psmt.setString(4, fax);
            psmt.setString(5, homepage);
            psmt.setString(6, procID);
            psmt.setString(7, saupNo);
            psmt.executeUpdate();
        }
        catch (Exception exc) {
            Log.errors(this, exc, "");
            throw exc;
        }
        finally {
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception e) {
                    Log.errors(this, e, "");
                }
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception e) {
                Log.errors(this, e, "");
            }
        }
    }
    
    public void masterUpdateChangeDate(final String saupNo, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " UPDATE UM_SUPPLIER_ENTER_MAST SET UPDATE_DT = SYSDATE  WHERE BIZ_REG_NO = ? ";
            psmt = con.prepareStatement(query);
            psmt.setString(1, saupNo);
            psmt.executeUpdate();
        }
        catch (Exception exc) {
            Log.errors(this, exc, "");
            Log.debug(String.valueOf(this.getClass().getName()) + ".masterUpdateChangeDate() saupNo[" + saupNo + "]:" + exc.toString());
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
