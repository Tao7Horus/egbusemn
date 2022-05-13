// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.ComStr;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class UM_LicenseEndDate
{
    String saupNo;
    String procID;
    String nation;
    int currentMode;
    public final int MODE_MS = 100;
    
    public void setCurrentMode(final int currentMode) {
        this.currentMode = currentMode;
    }
    
    public String[][] getMSData() {
        this.setCurrentMode(100);
        return this.getList();
    }
    
    public String[][] getList() {
        Trx trx = null;
        PreparedStatement psmt = null;
        ResultSet executeQuery = null;
        String[][] array = (String[][])null;
        try {
            trx = new Trx(this, "usemn");
            psmt = this.getPsmt(this.getQuery(), trx.getConnection());
            executeQuery = psmt.executeQuery();
            executeQuery.getMetaData();
            array = new String[this.getRowCount(executeQuery)][this.getColumnCount(executeQuery)];
            int n = 0;
            while (executeQuery.next()) {
                for (int i = 0; i < this.getColumnCount(executeQuery); ++i) {
                    array[n][i] = ((executeQuery.getString(i + 1) == null) ? "" : executeQuery.getString(i + 1));
                }
                ++n;
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_LicenseEndDate.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            Log.debug("UM_LicenseEndDate.select_user block Exception : ");
            Log.debug("Exception발생 사유 : " + ex2.toString());
            ex2.printStackTrace();
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex4) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return array;
    }
    
    public String getQuery() {
        String string = "";
        switch (this.currentMode) {
            case 100: {
                string = " SELECT  distinct 면허코드 " + this.getQueryCondition();
                break;
            }
        }
        return string;
    }
    
    public String getQueryCondition() {
        String s = "";
        switch (this.currentMode) {
            case 100: {
                s = " FROM 사용_면허만료일자  WHERE 면허만료일자사용여부 = 'Y' ";
                break;
            }
        }
        return s;
    }
    
    public PreparedStatement getPsmt(final String s, final Connection connection) throws SQLException {
        return connection.prepareStatement(s, 1004, 1007);
    }
    
    public int getRowCount(final ResultSet set) throws SQLException {
        int row = 0;
        if (set == null) {
            return 0;
        }
        if (set.last()) {
            row = set.getRow();
        }
        set.beforeFirst();
        return row;
    }
    
    public int getColumnCount(final ResultSet set) throws SQLException {
        if (set == null) {
            return 0;
        }
        return set.getMetaData().getColumnCount();
    }
    
    public int saupstatValues(String replace) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String string = " SELECT COUNT(*) FROM 사용_업체상태                           WHERE 사업자등록번호='" + replace + "'\t\t\t\t\t\t\t   " + " AND 상태구분 = '07' AND 처리자ID = 'ADMIN'                     ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_LicenseEndDate.java .saupstatValues('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_LicenseEndDate.java .saupstatValues('" + replace + "'):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public String renewValues(String replace) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        replace = ComStr.replace(replace, "-", "");
        try {
            final String string2 = " SELECT 갱신일자 FROM 사용_조달업체마스터                           WHERE 사업자등록번호='" + replace + "'\t\t\t\t\t\t\t   ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_LicenseEndDate.java .renewValues('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_LicenseEndDate.java .renewValues('" + replace + "'):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return string;
    }
}
