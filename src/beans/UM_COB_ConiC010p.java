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

public class UM_COB_ConiC010p
{
    String saupNo;
    String procID;
    String nation;
    int currentMode;
    public final int MODE_MAIN = 100;
    public final int MODE_ID = 200;
    public final int MODE_JP = 300;
    public final int MODE_GP = 400;
    public final int MODE_MS = 500;
    public final int MODE_DP = 600;
    public final int MODE_GJ = 700;
    
    public void setSaupNo(final String saupNo) {
        this.saupNo = saupNo;
    }
    
    public void setProcID(final String procID) {
        this.procID = procID;
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
    
    public String[][] getIDData() {
        this.setCurrentMode(200);
        return this.getList();
    }
    
    public String[][] getJPData() {
        this.setCurrentMode(300);
        return this.getList();
    }
    
    public String[][] getGPData() {
        this.setCurrentMode(400);
        return this.getList();
    }
    
    public String[][] getMSData() {
        this.setCurrentMode(500);
        return this.getList();
    }
    
    public String[][] getDPData() {
        this.setCurrentMode(600);
        return this.getList();
    }
    
    public String[][] getGJData() {
        this.setCurrentMode(700);
        return this.getList();
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
            Log.debug("UM_COV_ConiC010c.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiC010c.select_user block Exception : ");
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
                s = " SELECT BIZ_REG_NO, NATIONALITY,  BIZ_NM, BIZ_EN_NM, TO_CHAR(COMMENCEMENT_DT, 'YYYY-MM-DD'),  TO_CHAR(ESTABLISH_DT, 'YYYY-MM-DD'),  BIZ_CLS,  PRODUCT_CLS,  CORP_REG_NO, BIZ_CLS1,  BIZ_CLS2,  BIZ_CLS_YEAR,  CAPITAL,  EMPLOYEE_COUNT,  LAST_SETTLE_DT, ZIP_CD,  AREA_CD,  ADDR,  DETAIL_ADDR,  PHONE_NO,  FAX,  WEBSITE,  SPECIAL_GOODS_YN,  TO_CHAR(CREATE_DT, 'YYYY-MM-DD'),  TO_CHAR(UPDATE_DT, 'YYYY-MM-DD'),  REPR_BIZ_APPROVE_YN,  MANAGER_ID,  PERMIT_BRANCH " + this.getQueryCondition();
                break;
            }
            case 200: {
                s = " SELECT  IDENT_NO, NM,  DEPART, POSITION, PHONE_NO,  E_MAIL,  MOBILE,  FAX,  BIDDING_AGENT_YN " + this.getQueryCondition();
                break;
            }
            case 400: {
                s = " SELECT  DISTINCT  B.분류명,  A.GOOD_CLS_NO,   A.INCOME_3YEARS, A.MAST_GOODS_YN " + this.getQueryCondition();
                break;
            }
            case 300: {
                s = " SELECT  DISTINCT  A.GOOD_CLS_NO,  A.PERMIT_NO,  A.PERMIT_INSTITU,  TO_CHAR(A.PERMIT_DT, 'YYYY-MM-DD'),  A.INCOME_3YEARS,  A.MAST_GOODS_YN,  B.분류명,  A.DIRECT_PRODUCTION_DOC,  TO_CHAR(A.AVAIL_PERIOD_START_DT,'YYYYMMDD'),  TO_CHAR(A.AVAIL_PERIOD_END_DT,'YYYYMMDD'),  A.INDUSTRY_CLS_CD,  A.ISSUE_INSTITU,  A.DOC_NM " + this.getQueryCondition();
                break;
            }
            case 500: {
                s = " SELECT  A.LICENSE_CD,  B.코드명,  A.LICENSE_NO,  TO_CHAR(A.LICENSE_ISSUED_DT, 'YYYY-MM-DD'),  TO_CHAR(A.LICENSE_EXPIRY_DT, 'YYYY-MM-DD'),  A.CONST_ABIL_EVAL_AMT,  A.EVAL_STD_YEAR,  A.MAST_LICENSE_YN " + this.getQueryCondition();
                break;
            }
            case 600: {
                s = " SELECT  REPR_IDENT_NO, REPR_NM,  REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE,  REPR_CLS " + this.getQueryCondition();
                break;
            }
            case 700: {
                s = " SELECT  FACTORY_NM,  FACTORY_ZIP_CD,  FACTORY_ADDR,  FACTORY_ADDR2,  FACTORY_PHONE_NO,  FACTORY_FAX,  SERIAL_NO,  FACTORY_RENT_YN,  to_char(FACTORY_RENT_START_DT, 'yyyy-mm-dd'),  to_char(FACTORY_RENT_END_DT, 'yyyy-mm-dd') " + this.getQueryCondition();
                break;
            }
        }
        return s;
    }
    
    public String getQueryCondition() {
        String s = "";
        switch (this.currentMode) {
            case 100: {
                s = " FROM UM_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = ? ";
                break;
            }
            case 200: {
                s = " FROM UM_BID_AGENT  WHERE BIZ_REG_NO = ? ";
                break;
            }
            case 400: {
                s = " FROM UM_SUPPLIER_ENTER_ITEMS A, SYN_VIEW_물품분류매핑 B  WHERE A.BIZ_REG_NO = ?  AND A.GOOD_CLS_NO = B.물품분류  AND A. DIRECT_PRODUCTION_YN = 'N'  AND A. RESERVED_ITEM_OPTION is null ";
                break;
            }
            case 300: {
                s = " FROM UM_SUPPLIER_ENTER_ITEMS A, SYN_VIEW_물품분류매핑 B  WHERE A.BIZ_REG_NO = ?  AND A.GOOD_CLS_NO = B.물품분류  AND A. DIRECT_PRODUCTION_YN = 'Y'  AND A. RESERVED_ITEM_OPTION is null ";
                break;
            }
            case 500: {
                s = " FROM UM_LICENSE_FACTS A, syn_공동코드 B  WHERE A.BIZ_REG_NO = ?    AND B.코드CLS = 'GU9'    AND A.LICENSE_CD = B.코드 ";
                break;
            }
            case 600: {
                s = " FROM UM_REPR  WHERE BIZ_REG_NO = ? ";
                break;
            }
            case 700: {
                s = " FROM UM_FACTORY_INFO  WHERE BIZ_REG_NO = ? ";
                break;
            }
        }
        return s;
    }
    
    public PreparedStatement setQueryCondition(final PreparedStatement preparedStatement) throws SQLException {
        switch (this.currentMode) {
            case 100: {
                preparedStatement.setString(1, this.saupNo);
                break;
            }
            case 200: {
                preparedStatement.setString(1, this.saupNo);
                break;
            }
            case 300: {
                preparedStatement.setString(1, this.saupNo);
                break;
            }
            case 400: {
                preparedStatement.setString(1, this.saupNo);
                break;
            }
            case 500: {
                preparedStatement.setString(1, this.saupNo);
                break;
            }
            case 600: {
                preparedStatement.setString(1, this.saupNo);
                break;
            }
            case 700: {
                preparedStatement.setString(1, this.saupNo);
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
