// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_URE_UserE050b;

public class UM_URB_UserE030p
{
    public UM_URE_UserE050b select_DN(final String id) {
        Trx trx = null;
        Connection con = null;
        UM_URE_UserE050b ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            final String sql = " select CERT_NM,         to_char(AVAIL_PERIOD_START_DT, 'dd/mm/yyyy hh24:mi:ss'),         to_char(AVAIL_PERIOD_END_DT, 'dd/mm/yyyy hh24:mi:ss')  from UM_CERT_INFO where USER_ID='" + id + "'";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_URE_UserE050b();
                ett.setSignDn(rs.getString(1));
                ett.setCertFromDate(rs.getString(2));
                ett.setCertToDate(rs.getString(3));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserE030p.select_DN('" + id + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserE030p.select_DN('" + id + "'):" + exc.toString());
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
        return ett;
    }
    
    public UM_URE_UserE050b select_guser(final String id) {
        Trx trx = null;
        Connection con = null;
        UM_URE_UserE050b ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            final String sql = "select a.MAST_CD, b.INSTITU_FULL_NM, a.CHRGR_DEPART, a.CHRGR_NM from UM_USER a, UM_PUB_INSTITU_MAST b where a.MAST_CD = b.INSTITU_CD and USER_ID='" + id + "'";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_URE_UserE050b();
                ett.setCode(rs.getString(1));
                ett.setName(rs.getString(2));
                ett.setUserPost(rs.getString(3));
                ett.setUserName(rs.getString(4));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserE030p.select_guser('" + id + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserE030p.select_guser('" + id + "'):" + exc.toString());
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
        return ett;
    }
    
    public UM_URE_UserE050b select_cuser(final String id) {
        Trx trx = null;
        Connection con = null;
        UM_URE_UserE050b ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            final String sql = "select a.MAST_CD, b.BIZ_NM, a.CHRGR_DEPART, a.CHRGR_NM from UM_USER a, UM_SUPPLIER_ENTER_MAST b where a.MAST_CD = b.BIZ_REG_NO and USER_ID='" + id + "'";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_URE_UserE050b();
                ett.setCode(rs.getString(1));
                ett.setName(rs.getString(2));
                ett.setUserPost(rs.getString(3));
                ett.setUserName(rs.getString(4));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserE030p.select_cuser('" + id + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserE030p.select_cuser('" + id + "'):" + exc.toString());
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
        return ett;
    }
    
    public UM_URE_UserE050b[] select_certlist(final String id) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_URE_UserE050b[] ettlist = (UM_URE_UserE050b[])null;
        try {
            final String sql = "select USER_ID, TURN, CERT_NM, ENC_CERT_NM, to_char(REG_DT, 'dd/mm/yyyy hh24:mi:ss'), to_char(UPDATE_DT, 'dd/mm/yyyy hh24:mi:ss'), to_char(LAST_LOGIN_DT, 'dd/mm/yyyy hh24:mi:ss'), to_char(AVAIL_PERIOD_START_DT, 'dd/mm/yyyy hh24:mi:ss'), to_char(AVAIL_PERIOD_END_DT, 'dd/mm/yyyy hh24:mi:ss') from UM_CERT_INFO where USER_ID='" + id + "' order by REG_DT desc";
            System.out.println(sql);
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            UM_URE_UserE050b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_URE_UserE050b();
                et.setUserId(rs.getString(1));
                et.setRegCount(rs.getInt(2));
                et.setSignDn(rs.getString(3));
                et.setCodeDn(rs.getString(4));
                et.setRegDate(rs.getString(5));
                et.setAdjDate(rs.getString(6));
                et.setLastLoginTime(rs.getString(7));
                et.setCertFromDate(rs.getString(8));
                et.setCertToDate(rs.getString(9));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserE030p.select_certlist('" + id + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserE030p.select_certlist('" + id + "'):" + exc.toString());
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
        ettlist = new UM_URE_UserE050b[vec.size()];
        System.out.println(vec.size());
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public int Max_count(final String id) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "select count(*) from UM_CERT_INFO where USER_ID='" + id + "'";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserE030p.Max_count('" + id + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserE030p.Max_count('" + id + "'):" + exc.toString());
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
