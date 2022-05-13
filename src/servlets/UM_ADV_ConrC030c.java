// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.Log;
import entity.UM_GOE_ConiC010b;
import common.Trx;
import java.sql.Connection;

public class UM_ADV_ConrC030c
{
    Connection con;
    Trx trx;
    
    public UM_GOE_ConiC010b select_sangho(final String sangho) {
        UM_GOE_ConiC010b ett = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        String sql = null;
        try {
            sql = "SELECT count(*) FROM 사용_조달업체마스터 WHERE 상호명 ='" + sangho + "'";
            this.trx = new Trx(this, "usemn");
            this.con = this.trx.getConnection();
            psmt = this.con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett = new UM_GOE_ConiC010b();
                ett.setSangho(rs.getString(1));
            }
            rs.close();
            psmt.close();
            this.con.close();
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrC020c.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrC020c.select_user block Exception : ");
            Log.debug("Exception발생 사유 : " + exc.toString());
            exc.printStackTrace();
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
            if (this.trx != null) {
                try {
                    this.trx.close();
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
        if (this.trx != null) {
            try {
                this.trx.close();
            }
            catch (Exception ex6) {}
        }
        return ett;
    }
    
    public UM_GOE_ConiC010b select_saupNo(String saupNo) {
        UM_GOE_ConiC010b ett1 = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        String sql = null;
        String saupNo2 = "";
        String saupNo3 = "";
        String saupNo4 = "";
        try {
            saupNo2 = saupNo.substring(0, 3);
            saupNo3 = saupNo.substring(4, 6);
            saupNo4 = saupNo.substring(7, 12);
            saupNo = String.valueOf(saupNo2) + saupNo3 + saupNo4;
            sql = "SELECT count(*) FROM 사용_조달업체마스터 WHERE 사업자등록번호 ='" + saupNo + "'";
            this.trx = new Trx(this, "usemn");
            this.con = this.trx.getConnection();
            psmt = this.con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett1 = new UM_GOE_ConiC010b();
                ett1.setSaupNo(rs.getString(1));
            }
            rs.close();
            psmt.close();
            this.con.close();
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrC020c.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrC020c.select_user block Exception : ");
            Log.debug("Exception발생 사유 : " + exc.toString());
            exc.printStackTrace();
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
            if (this.trx != null) {
                try {
                    this.trx.close();
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
        if (this.trx != null) {
            try {
                this.trx.close();
            }
            catch (Exception ex6) {}
        }
        return ett1;
    }
    
    public UM_GOE_ConiC010b select_bubinNo(String bubinNo) {
        UM_GOE_ConiC010b ett2 = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        String sql = null;
        String bubinNo2 = "";
        String bubinNo3 = "";
        try {
            bubinNo2 = bubinNo.substring(0, 6);
            bubinNo3 = bubinNo.substring(7, 13);
            bubinNo = String.valueOf(bubinNo2) + bubinNo3;
            sql = "SELECT count(*) FROM 사용_조달업체마스터 WHERE 법인등록번호 ='" + bubinNo + "'";
            this.trx = new Trx(this, "usemn");
            this.con = this.trx.getConnection();
            psmt = this.con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett2 = new UM_GOE_ConiC010b();
                ett2.setBubinNo(rs.getString(1));
            }
            rs.close();
            psmt.close();
            this.con.close();
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrC020c.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrC020c.select_user block Exception : ");
            Log.debug("Exception발생 사유 : " + exc.toString());
            exc.printStackTrace();
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
            if (this.trx != null) {
                try {
                    this.trx.close();
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
        if (this.trx != null) {
            try {
                this.trx.close();
            }
            catch (Exception ex6) {}
        }
        return ett2;
    }
}
