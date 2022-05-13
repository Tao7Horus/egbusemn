// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class EP_MDB_GGA557
{
    public boolean checkPermissionByURL(final String userID, final String menu_url) throws Exception {
        final boolean getPerByMenu = this.getPerByMenuUrl(menu_url);
        final boolean getPerByMenu_UseID = this.getPerByUserID_MenuUrl(userID, menu_url);
        final boolean result = getPerByMenu && !getPerByMenu_UseID;
        return result;
    }
    
    public boolean getPerByUserID_MenuUrl(final String userID, final String menu_url) throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final StringBuffer sql = new StringBuffer("");
        final String urlReplace = menu_url.replaceAll(":80/", "/");
        sql.append(" SELECT USER_ID FROM BID.BID_UM_USER_MENU WHERE USER_ID = ? AND MENU_ID IN(  SELECT MENU_ID FROM PORTAL.PT_MENU WHERE TRIM(REPLACE(REPLACE(URL,CHR(10),''),CHR(13),'')) = ? OR TRIM(REPLACE(REPLACE(URL,CHR(10),''),CHR(13),'')) = ? ) ");
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, userID);
            pstmt.setString(2, menu_url);
            pstmt.setString(3, urlReplace);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "EP_MDB_GGA557.permistion Exception");
        }
        catch (Exception exc) {
            Log.errors(this, exc, "EP_MDB_GGA557.permistion Exception");
            throw exc;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
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
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return false;
    }
    
    public boolean getPerByMenuUrl(final String menu_url) throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final StringBuffer sql = new StringBuffer("");
        final String urlReplace = menu_url.replaceAll(":80/", "/");
        sql.append(" SELECT USER_ID FROM BID.BID_UM_USER_MENU WHERE MENU_ID IN(  SELECT MENU_ID FROM PORTAL.PT_MENU WHERE TRIM(REPLACE(REPLACE(URL,CHR(10),''),CHR(13),'')) = ? OR TRIM(REPLACE(REPLACE(URL,CHR(10),''),CHR(13),'')) = ? ) ");
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            pstmt.setString(1, menu_url);
            pstmt.setString(2, urlReplace);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "EP_MDB_GGA557.permistion Exception");
        }
        catch (Exception exc) {
            Log.errors(this, exc, "EP_MDB_GGA557.permistion Exception");
            throw exc;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
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
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return false;
    }
}
