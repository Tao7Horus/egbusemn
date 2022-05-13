// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import common.CommDbQuery;
import java.util.Vector;
import common.CommEntity;
import entity.ContractItem1;
import java.sql.PreparedStatement;
import common.Log;
import entity.ContractItem;
import java.sql.Connection;
import java.util.List;

public class UM_RAB_RecContract
{
    public void save(final String bizRegNo, final List ctrList, final Connection conn) throws Exception {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            ContractItem item = null;
            for (int i = 0; i < ctrList.size(); ++i) {
                item = ctrList.get(i);
                sql.append("insert ");
                sql.append(" into UM_REC_CONTRACT_INFO (BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, CONTRACT_VALUE, CONTRACT_PARTNER, UPDATE_DT)");
                sql.append(" values(?, ?, ?, ?, ?, SYSDATE)");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, item.getNo());
                pstmt.setString(3, item.getName());
                pstmt.setString(4, item.getValue());
                pstmt.setString(5, item.getPartnerName());
                pstmt.executeUpdate();
                sql = new StringBuffer();
            }
        }
        catch (Exception e) {
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".save(" + bizRegNo + ")\n\n" + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void insertContract(final String bizRegNo, final List ctrList, final Connection conn) throws Exception {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            ContractItem1 item = null;
            for (int i = 0; i < ctrList.size(); ++i) {
                item = ctrList.get(i);
                sql.append("insert ");
                sql.append(" into UM_REC_CONTRACT_INFO (BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, DATE_SIGN, CONTRACT_VALUE, CONTRACT_PRINCE, CONTRACT_PERCENT, DATE_COMPLETE, PROJECT_NAME, CONTRACT_CONTACT)");
                sql.append(" values(?, ?, ?, TO_DATE('" + item.getDateSign() + "', 'DD/MM/YYYY') , ?, ?, ?, TO_DATE('" + item.getDateComplete() + "', 'DD/MM/YYYY'), ?, ?)");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, item.getNo());
                pstmt.setString(3, item.getName());
                pstmt.setString(4, item.getValue());
                pstmt.setString(5, item.getPrince());
                pstmt.setString(6, item.getPercent());
                pstmt.setString(7, item.getProjectName());
                pstmt.setString(8, item.getContact());
                Log.debug("test dk nha thau" + sql.toString());
                pstmt.executeUpdate();
                sql = new StringBuffer();
            }
        }
        catch (Exception e) {
            Log.debug("Exception in insertContract(" + bizRegNo + ")\n\n" + e.getMessage());
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".insertContract(" + bizRegNo + ")\n\n" + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void delete(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_REC_CONTRACT_INFO ");
            sql.append(" where BIZ_REG_NO = ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public CommEntity[] getList(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select CONTRACT_NO, CONTRACT_NAME, CONTRACT_PARTNER, CONTRACT_VALUE");
            sql.append(" from UM_REC_CONTRACT_INFO ");
            sql.append(" where BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getList(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public CommEntity[] getListSign(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select CONTRACT_NO, CONTRACT_NAME, TO_CHAR(DATE_SIGN, 'DD/MM/YYYY') DATE_SIGN, CONTRACT_VALUE, CONTRACT_PRINCE, CONTRACT_PERCENT, TO_CHAR(DATE_COMPLETE, 'DD/MM/YYYY') DATE_COMPLETE, PROJECT_NAME, CONTRACT_CONTACT ");
            sql.append(" from UM_REC_CONTRACT_INFO ");
            sql.append(" where BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getListSign(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
    }
}
