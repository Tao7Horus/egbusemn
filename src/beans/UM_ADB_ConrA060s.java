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

public class UM_ADB_ConrA060s
{
    public String[][] getMainData(final String s) {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String[][] array = (String[][])null;
        try {
            trx = new Trx(this, "usemn");
            prepareStatement = trx.getConnection().prepareStatement("   SELECT a.상호명, a.사업자등록번호, a.제재연월일 제재시작일자, a.만료연월일 제재만료일자, a.입력일자 게재일자,\t\t\t\t\t\t\t\t b.대표자명, b.주민등록번호 대표자주민등록번호, a.공공기관명 제재기관, a.공공기관코드\t\t\t\t\t\t\t\t\t\t\tFROM 사용_부정당업자 a, 사용_부정당대표자 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND (a.정지구분 = 'N' OR (a.정지구분 = 'Y' AND a.재개구분 = 'Y'))\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.해제구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.제재횟수 = b.제재횟수\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.공공기관코드 <> 'ZY00480'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND TO_CHAR(a.제재연월일, 'yyyy-mm-dd') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND TO_CHAR(a.만료연월일, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND NVL(TO_CHAR(a.재개일자, 'yyyy-mm-dd'), '1800-01-01') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd')\t\t\t\t\t\t\t\tAND b.주민등록번호 IN ( SELECT 대표자주민번호 FROM 사용_접수대표자 WHERE 사업자등록번호 = ?)\t\t\t\t\t\t\t\t\tUNION\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSELECT a.상호명, a.사업자등록번호, a.제재연월일 제재시작일자, a.만료연월일 제재만료일자, a.입력일자 게재일자,\t\t\t\t\t\t\t\t b.대표자명, b.주민등록번호 대표자주민등록번호, a.공공기관명 제재기관, a.공공기관코드\t\t\t\t\t\t\t\t\t\t\tFROM 사용_부정당업자 a, 사용_부정당대표자 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND (a.정지구분 = 'N' OR (a.정지구분 = 'Y' AND a.재개구분 = 'Y'))\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.해제구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.제재횟수 = b.제재횟수\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.공공기관코드 <> 'ZY00480'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND TO_CHAR(a.제재연월일, 'yyyy-mm-dd') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND TO_CHAR(a.만료연월일, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND NVL(TO_CHAR(a.재개일자, 'yyyy-mm-dd'), '1800-01-01') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd')\t\t\t\t\t\t\t\tAND b.주민등록번호 IN ( SELECT 주민등록번호 FROM 사용_접수입찰대리인 WHERE 사업자등록번호 = ?)\t\t\t\t\t", 1004, 1007);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s);
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
            Log.debug("UM_ADB_ConrA060s.getMainData block SQLException : ");
            Log.debug("Exception발생 사유 : " + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrA060s.getMainData block Exception : ");
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
