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

public class UM_ADB_ConrA050s
{
    public String[][] getMainData(final String s) {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String[][] array = (String[][])null;
        try {
            trx = new Trx(this, "usemn");
            prepareStatement = trx.getConnection().prepareStatement("  SELECT distinct 면허코드, b.코드명, 면허번호, 시공능력평가액,  대표면허여부,           TO_CHAR(면허취득일자, 'YYYY-MM-DD'), TO_CHAR(면허만료일자, 'YYYY-MM-DD'), 평가액기준년도,                                      TO_CHAR(등록일자, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(갱신일자, 'YYYY-MM-DD HH24:MI:SS'),          TO_CHAR(협회갱신일자, 'YYYY-MM-DD HH24:MI:SS')     FROM 사용_면허사항 a, syn_공동코드 b                   WHERE a.사업자등록번호 = ?      AND b.코드구분 = 'GU9'      AND a.면허코드 = b.코드  ORDER BY a.면허코드 ", 1004, 1007);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
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
            Log.debug("UM_ADB_ConrA050s.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrA050s.select_user block Exception : ");
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
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
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
