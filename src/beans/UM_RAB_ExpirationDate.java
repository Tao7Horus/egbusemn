// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class UM_RAB_ExpirationDate
{
    int currentMode;
    String saupNo;
    String licenseCode;
    public final int MODE_MAIN = 100;
    
    public UM_RAB_ExpirationDate() {
        this.saupNo = "";
        this.licenseCode = "";
    }
    
    public void setSaupNo(final String saupNo) {
        this.saupNo = saupNo;
    }
    
    public void setLicenseCode(final String licenseCode) {
        this.licenseCode = licenseCode;
    }
    
    public void setCurrentMode(final int currentMode) {
        this.currentMode = currentMode;
    }
    
    public String[] getMainData() {
        this.setCurrentMode(100);
        String[] array = null;
        final String[][] list = this.getList();
        if (list != null && list.length != 0) {
            array = list[0];
        }
        return array;
    }
    
    public String[][] getList() {
        Trx trx = null;
        PreparedStatement queryCondition = null;
        ResultSet executeQuery = null;
        String[][] array = (String[][])null;
        try {
            trx = new Trx(this, "usemn");
            queryCondition = this.getPsmt(this.getQuery(), trx.getConnection());
            queryCondition = this.setQueryCondition(queryCondition);
            executeQuery = queryCondition.executeQuery();
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
            Log.debug("UM_RAB_ExpirationDate.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_ExpirationDate.select_user block Exception : ");
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
            if (queryCondition != null) {
                try {
                    queryCondition.close();
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
        String s = "";
        switch (this.currentMode) {
            case 100: {
                s = " SELECT 면허코드, 면허번호, TO_CHAR(면허취득일자, 'YYYY-MM-DD'), TO_CHAR(면허만료일자, 'YYYY-MM-DD'), TO_CHAR(면허만료일자, 'YYYYMMDD')  FROM 사용_소프트웨어산업협회  WHERE 사업자등록번호 = ?    AND 면허코드 = ?    AND 수신일자 = (SELECT MAX(수신일자) FROM 사용_소프트웨어산업협회                    WHERE 사업자등록번호 = ?                      AND 면허코드 = ?) ";
                break;
            }
        }
        return s;
    }
    
    public PreparedStatement setQueryCondition(final PreparedStatement preparedStatement) throws SQLException {
        switch (this.currentMode) {
            case 100: {
                preparedStatement.setString(1, this.saupNo);
                preparedStatement.setString(2, this.licenseCode);
                preparedStatement.setString(3, this.saupNo);
                preparedStatement.setString(4, this.licenseCode);
                break;
            }
        }
        return preparedStatement;
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
}
