// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class UM_ADB_ConrA010s
{
    int currentMode;
    String SaupNo;
    String BubinNo;
    public final int MODE_MAIN = 100;
    public final int MODE_MAIN1 = 200;
    public final int MODE_MAIN2 = 300;
    public final int MODE_MAIN3 = 400;
    public final int MODE_MAIN4 = 500;
    public final int MODE_MAIN5 = 600;
    
    public UM_ADB_ConrA010s() {
        this.SaupNo = "";
        this.BubinNo = "";
    }
    
    public void setSaupNo(final String SaupNo) {
        this.SaupNo = SaupNo;
    }
    
    public void setBubinNo(final String BubinNo) {
        this.BubinNo = BubinNo;
    }
    
    public void setCurrentMode(final int currentMode) {
        this.currentMode = currentMode;
    }
    
    public String[][] getMainData() {
        this.setCurrentMode(100);
        final String[][] mainData = this.getList();
        return mainData;
    }
    
    public String[][] getMain1Data() {
        this.setCurrentMode(200);
        final String[][] main1Data = this.getList();
        return main1Data;
    }
    
    public String[][] getMain2Data() {
        this.setCurrentMode(300);
        final String[][] main2Data = this.getList();
        return main2Data;
    }
    
    public String[][] getMain3Data() {
        this.setCurrentMode(400);
        final String[][] main3Data = this.getList();
        return main3Data;
    }
    
    public String[][] getMain4Data() {
        this.setCurrentMode(500);
        final String[][] main4Data = this.getList();
        return main4Data;
    }
    
    public String[][] getMain5Data() {
        this.setCurrentMode(600);
        final String[][] main5Data = this.getList();
        return main5Data;
    }
    
    public String[][] getList() {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String[][] returnList = (String[][])null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            final String query = this.getQuery();
            psmt = this.getPsmt(query, con);
            psmt = this.setQueryCondition(psmt);
            rs = psmt.executeQuery();
            final ResultSetMetaData rsmd = rs.getMetaData();
            returnList = new String[this.getRowCount(rs)][this.getColumnCount(rs)];
            int i = 0;
            while (rs.next()) {
                for (int j = 0; j < this.getColumnCount(rs); ++j) {
                    returnList[i][j] = ((rs.getString(j + 1) == null) ? "" : rs.getString(j + 1));
                }
                ++i;
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010s.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010s.select_user block Exception : ");
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
            if (trx != null) {
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
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return returnList;
    }
    
    public String getQuery() {
        String query = "";
        switch (this.currentMode) {
            case 100: {
                query = " SELECT /*+ ordered use_nl(a b )*/         a.BIZ_REG_NO, a.BIZ_NM, b.REPR_NM, b.REPR_IDENT_NO, a.ADDR||' '||a.DETAIL_ADDR, a.PHONE_NO  FROM UM_SUPPLIER_ENTER_MAST a, UM_REPR b  WHERE a.CORP_REG_NO = ?    AND a.BIZ_REG_NO = b.BIZ_REG_NO    AND b.MAST_REPR_YN = 'Y'  ORDER BY a.BIZ_REG_NO ";
                break;
            }
            case 200: {
                query = "  SELECT\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t                  a.BIZ_REG_NO, a.BIZ_NM, b.REPR_NM, b.REPR_IDENT_NO, a.ADDR||' '||a.DETAIL_ADDR, a.PHONE_NO\t\t\t\tFROM UM_SUPPLIER_ENTER_MAST a, UM_REPR b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND b.REPR_IDENT_NO IN (SELECT REPR_IDENT_NO FROM UM_REPR WHERE BIZ_REG_NO = ?)\t\t\t\t\tORDER BY a.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                break;
            }
            case 300: {
                query = "  SELECT\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  a.BIZ_REG_NO, a.BIZ_NM, b.REPR_NM, b.REPR_IDENT_NO, a.ADDR||' '||a.DETAIL_ADDR, a.PHONE_NO\t\t\t\tFROM UM_SUPPLIER_ENTER_MAST a, UM_REPR b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND b.REPR_IDENT_NO IN (SELECT REPR_IDENT_NO FROM UM_REC_REPR WHERE BIZ_REG_NO = ?)\t\t\t\tORDER BY a.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                break;
            }
            case 400: {
                query = " SELECT b.CD_NM  FROM UM_ENTER_STATE a, SYN_PUB_CODE b  WHERE a.BIZ_REG_NO = ?    AND b.CD_CLS = 'J11'    AND a.STATE_CLS = b.CD ";
                break;
            }
            case 500: {
                query = " SELECT REPR_IDENT_NO  FROM UM_REC_REPR  WHERE BIZ_REG_NO = ? ";
                break;
            }
            case 600: {
                query = " SELECT REPR_IDENT_NO  FROM UM_REPR  WHERE BIZ_REG_NO = ? ";
                break;
            }
        }
        return query;
    }
    
    public PreparedStatement setQueryCondition(final PreparedStatement psmt) throws SQLException {
        switch (this.currentMode) {
            case 100: {
                psmt.setString(1, this.BubinNo);
                break;
            }
            case 200: {
                psmt.setString(1, this.SaupNo);
                break;
            }
            case 300: {
                psmt.setString(1, this.SaupNo);
                break;
            }
            case 400: {
                psmt.setString(1, this.SaupNo);
                break;
            }
            case 500: {
                psmt.setString(1, this.SaupNo);
                break;
            }
            case 600: {
                psmt.setString(1, this.SaupNo);
                break;
            }
        }
        return psmt;
    }
    
    public PreparedStatement getPsmt(final String query, final Connection con) throws SQLException {
        PreparedStatement psmt = null;
        psmt = con.prepareStatement(query, 1004, 1007);
        return psmt;
    }
    
    public int getRowCount(final ResultSet rs) throws SQLException {
        int rowNum = 0;
        if (rs == null) {
            return 0;
        }
        if (rs.last()) {
            rowNum = rs.getRow();
        }
        rs.beforeFirst();
        return rowNum;
    }
    
    public int getColumnCount(final ResultSet rs) throws SQLException {
        int columnCount = 0;
        if (rs == null) {
            return 0;
        }
        final ResultSetMetaData rsmd = rs.getMetaData();
        columnCount = rsmd.getColumnCount();
        return columnCount;
    }
}
