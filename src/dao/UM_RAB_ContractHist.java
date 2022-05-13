// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.util.ArrayList;
import common.CommDbQuery;
import java.util.Vector;
import common.Trx;
import common.CommEntity;
import java.sql.PreparedStatement;
import common.Log;
import entity.ContractItem;
import java.sql.Connection;
import java.util.List;

public class UM_RAB_ContractHist
{
    public void save(final String bizRegNo, final List ctrList, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        sql.append("insert ");
        sql.append(" into UM_CONTRACT_INFO_HIST (BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, CONTRACT_VALUE, CONTRACT_PARTNER, UPDATE_DT, VER)");
        sql.append(" values(?, ?, ?, ?, ?, SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?) )");
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            ContractItem item = null;
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < ctrList.size(); ++i) {
                item = ctrList.get(i);
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, item.getNo());
                pstmt.setString(3, item.getName());
                pstmt.setString(4, item.getValue());
                pstmt.setString(5, item.getPartnerName());
                pstmt.setString(6, bizRegNo);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
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
    
    public void deleteNewest(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE UM_CONTRACT_INFO_HIST a ");
            sql.append(" where BIZ_REG_NO = ? ");
            sql.append(" AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO ) ");
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
    
    public void save(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_CONTRACT_INFO_HIST (BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, CONTRACT_VALUE, CONTRACT_PARTNER, UPDATE_DT, VER) ");
            sql.append(" SELECT BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, CONTRACT_VALUE, CONTRACT_PARTNER, SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00') FROM UM_CONTRACT_INFO_HIST WHERE BIZ_REG_NO = ? ) FROM UM_CONTRACT_INFO WHERE BIZ_REG_NO= ?  ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
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
    
    public void save1(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_CONTRACT_INFO_HIST (BIZ_REG_NO,CONTRACT_NO,CONTRACT_NAME,CONTRACT_VALUE,CONTRACT_PARTNER,UPDATE_DT,VER,USER_ID,SUB_USER_ID,DATE_SIGN,CONTRACT_PRINCE,CONTRACT_PERCENT,DATE_COMPLETE,PROJECT_NAME,CONTRACT_CONTACT) ");
            sql.append(" SELECT BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, CONTRACT_VALUE,'' CONTRACT_PARTNER, SYSDATE, '' VER, '' USER_ID, '' SUB_USER_ID, DATE_SIGN,CONTRACT_PRINCE, CONTRACT_PERCENT, DATE_COMPLETE, PROJECT_NAME, CONTRACT_CONTACT  FROM UM_CONTRACT_INFO WHERE BIZ_REG_NO= ?  ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
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
    
    public void saveHist(final String bizRegNo, final String userId, final String sUId, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_CONTRACT_INFO_HIST (BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, CONTRACT_VALUE, CONTRACT_PARTNER, UPDATE_DT, VER, USER_ID, SUB_USER_ID) ");
            sql.append(" SELECT BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, CONTRACT_VALUE, CONTRACT_PARTNER, SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00') FROM UM_CONTRACT_INFO_HIST WHERE BIZ_REG_NO = ? ), '" + userId + "' USER_ID, '" + sUId + "' SUB_USER_ID FROM UM_CONTRACT_INFO WHERE BIZ_REG_NO= ?  ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
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
    
    public void saveHistContract(final String bizRegNo, final String userId, final String sUId, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_CONTRACT_INFO_HIST (BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, DATE_SIGN, CONTRACT_VALUE, CONTRACT_PRINCE, CONTRACT_PERCENT, DATE_COMPLETE, PROJECT_NAME, CONTRACT_CONTACT, USER_ID, SUB_USER_ID) ");
            sql.append(" SELECT BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, DATE_SIGN, CONTRACT_VALUE, CONTRACT_PRINCE, CONTRACT_PERCENT, DATE_COMPLETE, PROJECT_NAME, CONTRACT_CONTACT, '" + userId + "' USER_ID, '" + sUId + "' SUB_USER_ID FROM UM_CONTRACT_INFO WHERE BIZ_REG_NO= ?  ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
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
    
    public CommEntity[] getHistList(final String bizRegNo, final String date) throws Exception {
        Trx tr = null;
        Connection conn = null;
        try {
            tr = new Trx(this);
            conn = tr.getConnection();
            final StringBuffer sql = new StringBuffer();
            final Vector parameterV = new Vector();
            sql.append(" SELECT CONTRACT_NO, CONTRACT_NAME, CONTRACT_PARTNER, CONTRACT_VALUE ");
            sql.append(" FROM USEMN.UM_CONTRACT_INFO_HIST a  JOIN USEMN.UM_SUPPLIER_ENTER_MAST_HIST b");
            sql.append(" ON b.UPDATE_DT = To_Date(substr( ? ,1,19), 'yyyy-mm-dd hh24:mi:ss') AND a.VER=b.VER ");
            sql.append(" WHERE a.BIZ_REG_NO = ? ");
            parameterV.add(date);
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getList(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
        finally {
            if (tr != null) {
                tr.close();
            }
        }
    }
    
    public CommEntity[] getHistList(final String bizRegNo) throws Exception {
        Trx tr = null;
        Connection conn = null;
        try {
            tr = new Trx(this);
            conn = tr.getConnection();
            final StringBuffer sql = new StringBuffer();
            final Vector parameterV = new Vector();
            sql.append(" SELECT CONTRACT_NO, CONTRACT_NAME, CONTRACT_PARTNER, CONTRACT_VALUE ");
            sql.append(" FROM UM_CONTRACT_INFO_HIST a ");
            sql.append(" WHERE BIZ_REG_NO= ? AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO ) ");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getList(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
        finally {
            if (tr != null) {
                tr.close();
            }
        }
    }
    
    public void saveTemp(final String bizRegNo, final List ctrList, final Connection conn) throws Exception {
        if (ctrList.size() < 1) {
            return;
        }
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            ContractItem item = null;
            sql.append("INSERT ALL ");
            final List params = new ArrayList();
            for (int i = 0; i < ctrList.size(); ++i) {
                item = ctrList.get(i);
                sql.append(" INTO UM_CONTRACT_INFO_HIST (BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, CONTRACT_VALUE, CONTRACT_PARTNER, UPDATE_DT, VER) ");
                sql.append(" VALUES (?, ?, ?, ?, ?, SYSDATE, (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO= ? ) ) ");
                params.add(bizRegNo);
                params.add(item.getNo());
                params.add(item.getName());
                params.add(item.getValue());
                params.add(item.getPartnerName());
                params.add(bizRegNo);
            }
            sql.append(" SELECT * FROM dual ");
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); ++i) {
                pstmt.setString(i + 1, params.get(i).toString());
            }
            pstmt.executeUpdate();
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
}
