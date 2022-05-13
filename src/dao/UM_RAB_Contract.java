// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.sql.ResultSet;
import common.Trx;
import common.CommDbQuery;
import java.util.Vector;
import common.CommEntity;
import entity.ContractItem1;
import java.sql.PreparedStatement;
import common.Log;
import entity.ContractItem;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;

public class UM_RAB_Contract
{
    public void save(final String bizRegNo, final List ctrList, final Connection conn) throws Exception {
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
                item = (ContractItem)ctrList.get(i);
                sql.append(" INTO UM_CONTRACT_INFO (BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, CONTRACT_VALUE, CONTRACT_PARTNER, UPDATE_DT) ");
                sql.append(" VALUES (?, ?, ?, ?, ?, SYSDATE) ");
                params.add(bizRegNo);
                params.add(item.getNo());
                params.add(item.getName());
                params.add(item.getValue());
                params.add(item.getPartnerName());
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
    
    public void insertContract(final String bizRegNo, final List ctrList, final Connection conn) throws Exception {
        if (ctrList.size() < 1) {
            return;
        }
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null ]");
            }
            ContractItem1 item = null;
            sql.append("INSERT ALL ");
            final List params = new ArrayList();
            for (int i = 0; i < ctrList.size(); ++i) {
                item = (ContractItem1)ctrList.get(i);
                sql.append(" INTO UM_CONTRACT_INFO (BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, DATE_SIGN, CONTRACT_VALUE, CONTRACT_PRINCE, CONTRACT_PERCENT, DATE_COMPLETE, PROJECT_NAME, CONTRACT_CONTACT) ");
                sql.append(" VALUES (?, ?, ?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?) ");
                params.add(bizRegNo);
                params.add(item.getNo());
                params.add(item.getName());
                params.add(item.getDateSign());
                params.add(item.getValue());
                params.add(item.getPrince());
                params.add(item.getPercent());
                params.add(item.getDateComplete());
                params.add(item.getProjectName());
                params.add(item.getContact());
            }
            sql.append(" SELECT * FROM dual ");
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); ++i) {
                pstmt.setString(i + 1, params.get(i).toString());
            }
            pstmt.executeUpdate();
        }
        catch (Exception e) {
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
    
    public void insertContractFromRec(final String bizRegNo, final Connection conn) throws Exception {

        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null ]");
            }
            sql.append("INSERT INTO UM_CONTRACT_INFO ");
            sql.append("    (BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, DATE_SIGN, CONTRACT_VALUE, CONTRACT_PRINCE, CONTRACT_PERCENT, DATE_COMPLETE, PROJECT_NAME, CONTRACT_CONTACT) ");
            sql.append("SELECT ");
            sql.append("    BIZ_REG_NO, CONTRACT_NO, CONTRACT_NAME, DATE_SIGN, CONTRACT_VALUE, CONTRACT_PRINCE, CONTRACT_PERCENT, DATE_COMPLETE, PROJECT_NAME, CONTRACT_CONTACT ");
            sql.append("FROM UM_REC_CONTRACT_INFO WHERE BIZ_REG_NO = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".insertContractFromRec(" + bizRegNo + ")\n\n" + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {
                	Log.debug(String.valueOf(this.getClass().getName()) + ".insertContractFromRec(" + bizRegNo + ") : " + ex.toString());
                    throw ex;
                }
            }
        }
    }
    
    public void save(final String bizRegNo, final ContractItem[] ctrList, final Connection conn) throws Exception {
        if (ctrList.length < 1) {
            return;
        }
        final List ls = new ArrayList();
        for (int i = 0; i < ctrList.length; ++i) {
            ls.add(ctrList[i]);
        }
        this.save(bizRegNo, ls, conn);
    }
    
    public void delete(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_CONTRACT_INFO ");
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
            sql.append(" select CONTRACT_NO, CONTRACT_NAME, CONTRACT_PARTNER, CONTRACT_VALUE ");
            sql.append(" from UM_CONTRACT_INFO ");
            sql.append(" where BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getList(" + bizRegNo + ") : " + e.toString());
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public CommEntity[] getListSign(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select CONTRACT_NO, CONTRACT_NAME, TO_CHAR(DATE_SIGN,'DD/MM/YYYY') DATE_SIGN, CONTRACT_VALUE, CONTRACT_PRINCE, CONTRACT_PERCENT, TO_CHAR(DATE_COMPLETE,'DD/MM/YYYY') DATE_COMPLETE, PROJECT_NAME, CONTRACT_CONTACT ");
            sql.append(" from UM_CONTRACT_INFO ");
            sql.append(" where BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getListSign(" + bizRegNo + ") : " + e.toString());
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public CommEntity[] getList(final String bizRegNo) throws Exception {
        Trx tr = null;
        Connection conn = null;
        try {
            tr = new Trx(this);
            conn = tr.getConnection();
            final StringBuffer sql = new StringBuffer();
            final Vector parameterV = new Vector();
            sql.append(" select CONTRACT_NO, CONTRACT_NAME, CONTRACT_PARTNER, CONTRACT_VALUE ");
            sql.append(" from UM_CONTRACT_INFO ");
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
        finally {
            if (tr != null) {
                tr.close();
            }
        }
    }
    
    public CommEntity[] getListSign(final String bizRegNo) throws Exception {
        Trx tr = null;
        Connection conn = null;
        try {
            tr = new Trx(this);
            conn = tr.getConnection();
            final StringBuffer sql = new StringBuffer();
            final Vector parameterV = new Vector();
            sql.append(" select CONTRACT_NO, CONTRACT_NAME, DATE_SIGN, CONTRACT_VALUE, CONTRACT_PRINCE, CONTRACT_PERCENT, DATE_COMPLETE, PROJECT_NAME, CONTRACT_CONTACT ");
            sql.append(" from UM_CONTRACT_INFO ");
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
        finally {
            if (tr != null) {
                tr.close();
            }
        }
    }
    
    public ContractItem[] selectUpdate(final String bizRegNo, final Connection conn) throws Exception {
        final String sql = " SELECT CONTRACT_NO, CONTRACT_NAME, CONTRACT_PARTNER, CONTRACT_VALUE FROM UM_CONTRACT_INFO_HIST a WHERE BIZ_REG_NO = ? AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO) ";
        PreparedStatement psmt = null;
        ResultSet res = null;
        ContractItem item = null;
        final Vector vec = new Vector();
        ContractItem[] results = null;
        final String[] parameters = { bizRegNo };
        try {
            psmt = conn.prepareStatement(sql);
            for (int i = 0; i < parameters.length; ++i) {
                psmt.setString(i + 1, parameters[i]);
            }
            res = psmt.executeQuery();
            while (res.next()) {
                item = new ContractItem(res.getString(1), res.getString(2), res.getString(3), res.getString(4));
                vec.add(item);
                item = null;
            }
            results = new ContractItem[vec.size()];
            vec.copyInto(results);
            return results;
        }
        catch (Exception exf) {
            Log.errors(this, exf, "");
            throw exf;
        }
    }
}
