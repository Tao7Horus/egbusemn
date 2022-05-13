// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.ComStr;
import common.Trx;
import entity.UM_RAE_ConuB010b;

public class UM_RAB_ConuB100c
{
    public UM_RAE_ConuB010b select_user(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        UM_RAE_ConuB010b ett = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            sb.append(" SELECT\tBIZ_REG_NO,\tBIZ_NM              \n");
            sb.append(" FROM\tUM_SUPPLIER_ENTER_MAST \t\t\t\t\n");
            sb.append(" WHERE\tBIZ_REG_NO = ?\t\t\n");
            sql = sb.toString();
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, ComStr.replace(saupNo, "-", ""));
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett = new UM_RAE_ConuB010b();
                ett.setSaupNo(rs.getString(1));
                ett.setupcheNm(rs.getString(2));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_ConuB100c.select_user('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_ConuB100c.select_user('" + saupNo + "'):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
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
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ett;
    }
    
    public int getBranchGubun(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        int branchgubun = 0;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "  SELECT COUNT(*) FROM UM_SUPPLIER_ENTER_MAST      WHERE BIZ_REG_NO = ?\t\t\t\t\t\t\t\t   AND HEADQUARTER_YN = 'N'\t\t\t\t\t\t\t\t\t\t\t";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, ComStr.replace(saupNo, "-", ""));
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                branchgubun = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("getBranchGubun('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("getBranchGubun('" + saupNo + "'):" + exc.toString());
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
        return branchgubun;
    }
}
