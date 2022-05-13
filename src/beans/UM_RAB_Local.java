// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.util.TreeMap;

public class UM_RAB_Local
{
    public TreeMap getAll(final String cdCls) {
        final TreeMap list = new TreeMap();
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            final String sql = "select CD, CD_NM2 from SYN_PUB_CODE WHERE CD_CLS = '" + cdCls + "' order by CD_NM2 ASC";
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                list.put(rs.getString(2), rs.getString(1));
            }
        }
        catch (SQLException sqle) {
            Log.debug(sqle.toString());
        }
        catch (Exception exc) {
            Log.debug(exc.toString());
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
        return list;
    }
    
    public HashMap getAllHash(final String cdCls) {
        final HashMap list = new HashMap();
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            final String sql = "select CD, CD_NM2 from SYN_PUB_CODE WHERE CD_CLS = '" + cdCls + "' order by CD_NM2 ASC";
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                list.put(rs.getString(1), rs.getString(2));
            }
        }
        catch (SQLException sqle) {
            Log.debug(sqle.toString());
        }
        catch (Exception exc) {
            Log.debug(exc.toString());
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
        return list;
    }
    
    public List getNameByCD(final String cdCls, final String code) {
        final List list = new ArrayList();
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            String nameCol = "CD_NM2";
            if ("GZ8".equals(cdCls)) {
                nameCol = "CD_NM";
            }
            final String sql = "select " + nameCol + " from SYN_PUB_CODE WHERE CD_CLS = '" + cdCls + "' and CD ='" + code.trim() + "' order by " + nameCol + " ASC";
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        }
        catch (SQLException sqle) {
            Log.debug(sqle.toString());
        }
        catch (Exception exc) {
            Log.debug(exc.toString());
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
        return list;
    }
}
