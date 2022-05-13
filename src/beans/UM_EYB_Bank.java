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
import entity.UM_EY_Regi_001l;

public class UM_EYB_Bank
{
    public UM_EY_Regi_001l[] bankList(final int pagenum, final int PAGEMAX, final String codeName, final String code) throws Exception {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        UM_EY_Regi_001l[] ett = (UM_EY_Regi_001l[])null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final Vector vec = new Vector();
        int inx = 1;
        try {
            sb.append(" SELECT CD_CLS, CD, CD_NM, CD_NM2, N FROM (");
            sb.append("\t\tSELECT CD_CLS, CD, CD_NM, CD_NM2, ROWNUM N");
            sb.append("\t\tFROM SYN_PUB_CODE a");
            sb.append("\t\tWHERE a.CD_CLS = 'F11'");
            if (!code.equals("")) {
                sb.append(" AND a.CD LIKE '%'||?||'%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND lower(a.CD_NM) LIKE '%'||?||'%'   \n");
            }
            sb.append(" ORDER BY a.CD, a.CD_NM) WHERE ROWNUM <= (" + pagenum + " * " + PAGEMAX + ") \n");
            sb.append(" AND N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")  \n");
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
            UM_EY_Regi_001l ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_EY_Regi_001l();
                ett_list.setContentCls(rs.getString(1));
                ett_list.setBankCd(rs.getString(2));
                ett_list.setBankName(rs.getString(3));
                ett_list.setRemark(rs.getString(4));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_EYB_Bank.bankList(3[" + code + "],4[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_EYB_Bank.bankList(3[" + code + "],4[" + codeName + "]):" + exc.toString());
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
        ett = new UM_EY_Regi_001l[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public int bankCount(final String codeName, final String code) throws Exception {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        int inx = 1;
        try {
            sb.append(" SELECT count(CD) FROM \n");
            sb.append(" SYN_PUB_CODE \n");
            sb.append("  WHERE CD_CLS = 'F11' \n");
            if (!code.equals("")) {
                sb.append(" AND CD LIKE '%'||?||'%'  \n");
            }
            if (!codeName.equals("")) {
                sb.append(" AND lower(CD_NM) LIKE '%'||?||'%'  \n");
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
            Log.debug("UM_EYB_Bank.bankCount([" + code + "],[" + codeName + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_EYB_Bank.bankCount([" + code + "],[" + codeName + "]):" + exc.toString());
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
