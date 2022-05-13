// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.util.ArrayList;
import common.Trx;
import common.CommDbQuery;
import java.util.Vector;
import common.CommEntity;
import java.sql.PreparedStatement;
import common.Log;
import entity.FinanYearItem;
import java.sql.Connection;
import java.util.List;

public class UM_RAB_FinanYearHist
{
    public void save(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        if (finanYears == null || finanYears.size() < 1) {
            return;
        }
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            FinanYearItem item = null;
            for (int i = 0; i < finanYears.size(); ++i) {
                sql.append("INSERT ");
                item = finanYears.get(i);
                if ("".equals(item.getYear()) || "".equals(item.getAttachFile())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                sql.append(" INTO UM_FINAN_STATE_HIST (BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, UPDATE_DT, VER)");
                sql.append(" SELECT BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, SYSDATE, (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ? ) FROM UM_FINAN_STATE WHERE BIZ_REG_NO=? AND YEAR=? ");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, bizRegNo);
                pstmt.setString(3, item.getYear());
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
    
    public void update(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        final PreparedStatement pstmt2 = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            FinanYearItem item = null;
            final CommEntity[] oldFinanYears = this.getList(bizRegNo, conn);
            for (int i = 0; i < finanYears.size(); ++i) {
                sql.append("INSERT ");
                item = finanYears.get(i);
                if ("".equals(item.getYear())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                if (item.getAttachFile() == null || "".equals(item.getAttachFile()) || "".equals(item.getOrgFile()) || item.getOrgFile() == null) {
                    for (int j = 0; j < oldFinanYears.length; ++j) {
                        if (item.getYear().equals(oldFinanYears[j].data[0])) {
                            item.setAttachFile(oldFinanYears[j].data[1]);
                            item.setOrgFile(oldFinanYears[j].data[2]);
                            break;
                        }
                    }
                }
                sql.append(" INTO UM_FINAN_STATE_HIST (BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, UPDATE_DT, VER )");
                sql.append(" values(?, ?, ?, ?, SYSDATE, (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ? ))");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, item.getYear());
                pstmt.setString(3, item.getAttachFile());
                pstmt.setString(4, item.getOrgFile());
                pstmt.setString(5, bizRegNo);
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
    
    public CommEntity[] getList(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select YEAR, ATTACH_FILE, ORG_FILE ");
            sql.append(" from UM_FINAN_STATE_HIST ");
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
    
    public CommEntity[] getMainList(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select YEAR, ATTACH_FILE, ORG_FILE ");
            sql.append(" from UM_FINAN_STATE ");
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
    
    public void save(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_FINAN_STATE_HIST (BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, UPDATE_DT, VER) ");
            sql.append(" SELECT BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, SYSDATE, (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ? ) FROM UM_FINAN_STATE WHERE BIZ_REG_NO= ?  ");
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
    
    public void saveHist(final String bizRegNo, final String userId, final String sUId, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_FINAN_STATE_HIST (BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, UPDATE_DT, VER, USER_ID, SUB_USER_ID) ");
            sql.append(" SELECT BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, SYSDATE, (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ? ), '" + userId + "' USER_ID, '" + sUId + "' SUB_USER_ID FROM UM_FINAN_STATE WHERE BIZ_REG_NO= ?  ");
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
    
    public CommEntity[] getListHist(final String bizRegNo, final String date) throws Exception {
        Trx tr = null;
        Connection conn = null;
        try {
            tr = new Trx(this);
            conn = tr.getConnection();
            final StringBuffer sql = new StringBuffer();
            final Vector parameterV = new Vector();
            sql.append(" select YEAR, ATTACH_FILE, ORG_FILE ");
            sql.append(" from UM_FINAN_STATE_HIST a JOIN UM_SUPPLIER_ENTER_MAST_HIST b ");
            sql.append(" ON b.UPDATE_DT = To_Date(substr( ?  ,1,19), 'yyyy-mm-dd hh24:mi:ss') AND a.VER=b.VER ");
            sql.append(" where a.BIZ_REG_NO = ? ");
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
    
    public CommEntity[] getListHist(final String bizRegNo) throws Exception {
        Trx tr = null;
        Connection conn = null;
        try {
            tr = new Trx(this);
            conn = tr.getConnection();
            final StringBuffer sql = new StringBuffer();
            final Vector parameterV = new Vector();
            sql.append(" select YEAR, ATTACH_FILE, ORG_FILE ");
            sql.append(" from UM_FINAN_STATE_HIST a ");
            sql.append(" WHERE BIZ_REG_NO = ? AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO ) ");
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
    
    public void deleteNewest(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE UM_FINAN_STATE_HIST a ");
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
    
    public void updateTemp(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        final PreparedStatement pstmt2 = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            FinanYearItem item = null;
            final CommEntity[] oldFinanYears = this.getMainList(bizRegNo, conn);
            sql.append("INSERT ALL ");
            final List params = new ArrayList();
            for (int i = 0; i < finanYears.size(); ++i) {
                item = finanYears.get(i);
                if ("".equals(item.getYear())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                if (item.getAttachFile() == null || "".equals(item.getAttachFile()) || "".equals(item.getOrgFile()) || item.getOrgFile() == null) {
                    for (int j = 0; j < oldFinanYears.length; ++j) {
                        if (item.getYear().equals(oldFinanYears[j].data[0])) {
                            item.setAttachFile(oldFinanYears[j].data[1]);
                            item.setOrgFile(oldFinanYears[j].data[2]);
                            break;
                        }
                    }
                }
                sql.append(" INTO UM_FINAN_STATE_HIST (BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, UPDATE_DT, VER)");
                sql.append(" values(?, ?, ?, ?, SYSDATE, (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO= ? ) )");
                params.add(bizRegNo);
                params.add(item.getYear());
                params.add(item.getAttachFile());
                params.add(item.getOrgFile());
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
