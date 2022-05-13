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
import entity.UM_RAE_ConuA010b;

public class UM_RAB_Industry
{
    public UM_RAE_ConuA010b[] Industry_list(final int pagenum, final int PAGEMAX, final String codeName, final String code) throws Exception {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        UM_RAE_ConuA010b[] ett = (UM_RAE_ConuA010b[])null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final Vector vec = new Vector();
        int inx = 1;
        try {
            sb.append(" SELECT N, CD_CLS, CD, CD_NM  \n");
            sb.append("\t \tFROM (SELECT ROWNUM N, a.CD_CLS, a.CD, a.CD_NM  \n");
            sb.append(" FROM SYN_PUB_CODE a \n");
            sb.append("  WHERE a.CD_CLS = 'GZ8' \n");
            if (!code.equals("")) {
                sb.append(" AND a.CD LIKE ? || '%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND lower(a.CD_NM) LIKE ? || '%'   \n");
            }
            sb.append(" ORDER BY a.CD, a.CD_NM) \n");
            sb.append(" WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")  \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (!code.equals("")) {
                psmt.setString(inx, code);
                ++inx;
            }
            if (!codeName.equals("")) {
                psmt.setString(inx, codeName.toLowerCase());
                ++inx;
            }
            UM_RAE_ConuA010b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_RAE_ConuA010b();
                ett_list.setCode(rs.getString(3));
                ett_list.setCodeName(rs.getString(4));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.License_list(3[" + code + "],4[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.License_list(3[" + code + "],4[" + codeName + "]):" + exc.toString());
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
        ett = new UM_RAE_ConuA010b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public int IndustryCount(final String codeName, final String code) throws Exception {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        int inx = 1;
        try {
            sb.append(" select count(CD) from SYN_PUB_CODE a where CD_CLS = 'GZ8' ");
            if (!code.equals("")) {
                sb.append(" AND a.CD LIKE ? || '%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND lower(a.CD_NM) LIKE ? || '%'   \n");
            }
            sql = sb.toString();
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (!code.equals("")) {
                psmt.setString(inx, code);
                ++inx;
            }
            if (!codeName.equals("")) {
                psmt.setString(inx, codeName.toLowerCase());
                ++inx;
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuA010p.Max_count([" + code + "],[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuA010p.Max_count([" + code + "],[" + codeName + "]):" + exc.toString());
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
}
