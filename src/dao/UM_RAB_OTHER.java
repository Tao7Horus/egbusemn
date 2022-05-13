// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import common.Log;
import common.CommDbQuery;
import java.util.Vector;
import common.CommEntity;
import java.sql.Connection;

public class UM_RAB_OTHER
{
    public CommEntity[] getListBalance(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select YEAR, ASSETS_TOTAL, DEBT_TOTAL, ASSET_VALUE, SHORT_ASSETS, SHORT_DEBT, CAPITAL ");
            sql.append(" from UM_REC_FINAN_BALANCE ");
            sql.append(" where BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getListBalance(" + bizRegNo + ") : " + e.toString());
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public CommEntity[] getListReport(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select YEAR, REVENUE_TOTAL, AVERAGE_REVENUE, PROFIT_BEFORE, PROFIT_AFTER ");
            sql.append(" from UM_REC_FINAN_REPORT ");
            sql.append(" where BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getListReport(" + bizRegNo + ") : " + e.toString());
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public CommEntity[] getListContract(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select CONTRACT_NO, CONTRACT_NAME, CONTRACT_VALUE, CONTRACT_PARTNER, UPDATE_DT, to_char(DATE_SIGN, 'yyyy/mm/dd') , CONTRACT_PRINCE, CONTRACT_PERCENT, to_char(DATE_COMPLETE, 'yyyy/mm/dd'), PROJECT_NAME, CONTRACT_CONTACT ");
            sql.append(" from UM_REC_CONTRACT_INFO ");
            sql.append(" where BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getListContract(" + bizRegNo + ") : " + e.toString());
            Log.errors(this, e, "");
            throw e;
        }
    }
}
