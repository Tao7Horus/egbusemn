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
import entity.UM_ADE_ConrE010b;

public class UM_ADB_ConrE010p
{
    public UM_ADE_ConrE010b[] select_comlist(final int pagenum, final int PAGEMAX, final String saupNo) {
        Connection con = null;
        Trx trx = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_ADE_ConrE010b[] ettlist = (UM_ADE_ConrE010b[])null;
        final String sql = " SELECT BIZ_REG_NO,UPDATE_DT, BIZ_NM, MANAGER_ID, N FROM  (SELECT BIZ_REG_NO,UPDATE_DT,BIZ_NM, MANAGER_ID, CD,  ROWNUM  N   FROM (SELECT distinct a.BIZ_REG_NO, a.UPDATE_DT,a.BIZ_NM, MANAGER_ID,  a.ADDR, a.DETAIL_ADDR, a.PHONE_NO,a.BIZ_CLS,a.AREA_CD,  c.CD, ROWNUM  N  FROM UM_SUPPLIER_ENTER_MAST_HIST a, UM_USER b, SYN_PUB_CODE c WHERE a.MANAGER_ID = b.USER_ID  and c.CD_CLS(+) = 'J03' and c.CD(+) = b.PERMIT_BRANCH AND  a.BIZ_REG_NO =  '" + saupNo + "' ORDER BY UPDATE_DT DESC )      " + "          WHERE  ROWNUM <= (" + pagenum + " * " + PAGEMAX + ") order by UPDATE_DT desc )        " + " WHERE\tN between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) " + " AND (" + pagenum + " * " + PAGEMAX + ") ";
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                final UM_ADE_ConrE010b ett = new UM_ADE_ConrE010b();
                ett.setSaupNo(rs.getString(1));
                ett.setRenewDate(rs.getString(2));
                ett.setSangho(rs.getString(3));
                ett.setChurijaId(rs.getString(4));
                ett.setChurijich(rs.getString(5));
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrE010p.select_userlist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrE010p.select_userlist block Exception : " + exc.toString());
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
            ettlist = new UM_ADE_ConrE010b[vec.size()];
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
            final String sql = "SELECT count(UPDATE_DT) FROM (SELECT distinct * FROM   UM_SUPPLIER_ENTER_MAST_HIST a, UM_USER b WHERE  a.MANAGER_ID = b.USER_ID AND  a.BIZ_REG_NO =  '" + saupNo + "') sub";
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
        catch (Exception sqle) {
            Log.errors(this, sqle, "");
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
