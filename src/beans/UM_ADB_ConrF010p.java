// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.util.Vector;
import entity.UM_ADE_ConrE020b;

public class UM_ADB_ConrF010p
{
    public UM_ADE_ConrE020b[] select_comlist(final int pagenum, final int PAGEMAX, final String saupNo) {
        Connection con = null;
        Trx trx = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_ADE_ConrE020b[] ettlist = (UM_ADE_ConrE020b[])null;
        final String sql = "SELECT BIZ_REG_NO,UPDATE_DT,SERIAL_NO, TABLE_CLS, CHANGE_CLS, BEFORE_MOD_CONTENT, AFTER_MOD_CONTENT, PROPERTY_NM, CHRG_NM,  N, BIZ_NM FROM (  SELECT BIZ_REG_NO,UPDATE_DT,SERIAL_NO, TABLE_CLS,                      \n         CHANGE_CLS, BEFORE_MOD_CONTENT, AFTER_MOD_CONTENT, PROPERTY_NM, CHRG_NM,ROWNUM  N, BIZ_NM          \n   FROM (select distinct a.BIZ_REG_NO,a.UPDATE_DT,a.SERIAL_NO, a.TABLE_CLS,        \n       a.CHANGE_CLS, a.BEFORE_MOD_CONTENT, a.AFTER_MOD_CONTENT, a.PROPERTY_NM, b.CHRG_NM, ROWNUM  N,        \n    (SELECT BIZ_NM FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO='" + saupNo + "') BIZ_NM   \n " + "    FROM   UM_BID_QUALIFY_FACTS_HIST a, UM_USER b                                         " + "    WHERE a.처리자ID=b.사용자ID                                                        " + "    AND   a.BIZ_REG_NO='" + saupNo + "' ORDER BY UPDATE_DT DESC ) " + "   WHERE ROWNUM <= (" + pagenum + " * " + PAGEMAX + ") ORDER BY UPDATE_DT DESC ) " + "   WHERE\tN between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                final UM_ADE_ConrE020b ett = new UM_ADE_ConrE020b();
                ett.setSaupNo(rs.getString(1));
                ett.setRenewDate(rs.getString(2));
                ett.setIlrunNo(rs.getString(3));
                ett.setTableGubun(rs.getString(4));
                ett.setChangeGubun(rs.getString(5));
                ett.setChangeBContents(rs.getString(6));
                ett.setChangeAContents(rs.getString(7));
                ett.setPropertyName(rs.getString(8));
                ett.setChurijaId(rs.getString(9));
                ett.setSangho(rs.getString(11));
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrF010p.select_userlist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrF010p.select_userlist block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        if (vec.size() > 0) {
            ettlist = new UM_ADE_ConrE020b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public int Max_count(final String saupNo) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "SELECT count(*)                                                           \nFROM (select distinct a.BIZ_REG_NO,a.UPDATE_DT,a.SERIAL_NO, a.TABLE_CLS,          \n a.CHANGE_CLS, a.BEFORE_MOD_CONTENT, a.AFTER_MOD_CONTENT, a.PROPERTY_NM, b.CHRGR_NM,                        \n (SELECT BIZ_NM FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO='" + saupNo + "') BIZ_NM \n" + " from   UM_BID_QUALIFY_FACTS_HIST a, UM_USER b                                   \n" + " WHERE  a.MANAGER_ID=b.USER_ID                                               \n" + " AND    a.BIZ_REG_NO=  '" + saupNo + "') sub ";
            Log.debug(sql);
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrF010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrF010p.Max_count block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count;
    }
}
