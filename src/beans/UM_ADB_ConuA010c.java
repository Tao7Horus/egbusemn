// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Vector;
import entity.UM_ADV_ConuA030b;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class UM_ADB_ConuA010c
{
    public int select_EnterpriseList_count(final String BizRegNo, final String epNameFull) {
        Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        final String sql = null;
        int count = 0;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("SELECT COUNT(*) FROM UM_SUPPLIER_ENTER_MAST                                            ");
            sb.append("  WHERE 1=1 ");
            sb.append(" AND lower(BIZ_REG_NO) LIKE ?||'%' ");
            if (!epNameFull.equals("")) {
                sb.append(" AND lower(BIZ_NM) LIKE '%'||?||'%' ");
            }
            psmt = con.prepareStatement(sb.toString());
            psmt.setString(1, BizRegNo.toLowerCase());
            if (!epNameFull.equals("")) {
                psmt.setString(2, epNameFull.toLowerCase());
            }
            rs = psmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.Max_count block Exception : " + exc.toString());
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
        return count;
    }
    
    public UM_ADV_ConuA030b[] select_EnterpriseList(final int pagenum, final int PAGEMAX, final String BizRegNo, final String epNameFull) {
        Connection con = null;
        Trx trx = null;
        UM_ADV_ConuA030b[] ettlist = (UM_ADV_ConuA030b[])null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        final String sql = null;
        final Vector vec = new Vector();
        try {
            sb.append("\tSELECT biz_reg_no, nationality, biz_nm, biz_en_nm, zip_cd, addr, detail_addr,phone_no, N    \n");
            sb.append("   FROM (SELECT biz_reg_no, nationality, biz_nm, biz_en_nm, zip_cd, addr, detail_addr,phone_no,            \n");
            sb.append("                  ROWNUM N                                                 \n");
            sb.append("           FROM UM_SUPPLIER_ENTER_MAST                                                                      \n");
            sb.append("          WHERE 1=1 ");
            sb.append(" AND  lower(BIZ_REG_NO) LIKE ?||'%'  ");
            if (!epNameFull.equals("")) {
                sb.append("  AND lower(BIZ_NM) LIKE '%'||?||'%'  ");
            }
            sb.append("          ORDER BY BIZ_REG_NO ASC                                                                      \n");
            sb.append("        )                                                                                                \n");
            sb.append(" WHERE N BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")                   \n");
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sb.toString());
            psmt.setString(1, BizRegNo);
            if (!epNameFull.equals("")) {
                psmt.setString(2, epNameFull.toLowerCase());
            }
            UM_ADV_ConuA030b ett = null;
            rs = psmt.executeQuery();
            while (rs.next()) {
                ett = new UM_ADV_ConuA030b();
                ett.setBiz_reg_no(rs.getString(1));
                ett.setNationality(rs.getString(2));
                ett.setBiz_nm(rs.getString(3));
                ett.setBiz_en_nm(rs.getString(4));
                ett.setZip_cd(rs.getString(5));
                ett.setAddr(rs.getString(6));
                ett.setDetail_addr(rs.getString(7));
                ett.setPhone_no(rs.getString(7));
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.select_golist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.select_golist block Exception : " + exc.toString());
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
        ettlist = new UM_ADV_ConuA030b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
}
