// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import common.CommDbQuery;
import java.util.Vector;
import entity.ReportItem;
import entity.FinalItem;
import common.CommEntity;
import java.sql.PreparedStatement;
import common.Log;
import entity.FinanYearItem;
import java.sql.Connection;
import java.util.List;

public class UM_RAB_RecFinanYear
{
    public void save(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            FinanYearItem item = null;
            for (int i = 0; i < finanYears.size(); ++i) {
                sql.append("insert ");
                item = finanYears.get(i);
                if ("".equals(item.getYear()) || "".equals(item.getAttachFile())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                sql.append(" into UM_REC_FINAN_STATE (BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, UPDATE_DT)");
                sql.append(" values(?, ?, ?, ?, SYSDATE)");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, item.getYear());
                pstmt.setString(3, item.getAttachFile());
                pstmt.setString(4, item.getOrgFile());
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
            final CommEntity[] oldFinanYears = this.getListBalance(bizRegNo, conn);
            Log.debug("oldFinanYears" + oldFinanYears.length);
            this.delete(bizRegNo, conn);
            Log.debug("finanYears: " + finanYears.size());
            for (int i = 0; i < finanYears.size(); ++i) {
                sql.append("insert ");
                item = finanYears.get(i);
                if ("".equals(item.getYear())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                if (item.getAttachFile() == null || "".equals(item.getAttachFile()) || "".equals(item.getOrgFile()) || item.getOrgFile() == null) {
                    for (int j = 0; j < oldFinanYears.length; ++j) {
                        Log.debug("oldFinanYears[j].data[0]:" + oldFinanYears[j].data[0]);
                        if (item.getYear().equals(oldFinanYears[j].data[0])) {
                            item.setAttachFile(oldFinanYears[j].data[1]);
                            item.setOrgFile(oldFinanYears[j].data[2]);
                            Log.debug("setOrgFile:" + item.getOrgFile());
                            break;
                        }
                    }
                }
                sql.append(" INTO UM_REC_FINAN_STATE (BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, UPDATE_DT)");
                sql.append(" values(?, ?, ?, ?, SYSDATE)");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, item.getYear());
                pstmt.setString(3, item.getAttachFile());
                pstmt.setString(4, item.getOrgFile());
                Log.debug(String.valueOf(bizRegNo) + "---" + item.getYear() + "---" + item.getAttachFile() + "---" + item.getOrgFile());
                pstmt.executeUpdate();
                sql = new StringBuffer();
            }
        }
        catch (Exception e) {
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".update(" + bizRegNo + ")\n\n" + e.toString());
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
    
    public void deleteBalance(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" delete from UM_REC_FINAN_BALANCE  ");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".deleteBalance(" + bizRegNo + ") : " + e.toString());
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
    
    public void insertBalance(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        final PreparedStatement pstmt2 = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            FinalItem item = null;
            final CommEntity[] oldFinanYears = this.getListBalance(bizRegNo, conn);
            Log.debug("oldFinanYears" + oldFinanYears.length);
            this.delete(bizRegNo, conn);
            Log.debug("finanYears: " + finanYears.size());
            for (int i = 0; i < finanYears.size(); ++i) {
                sql.append("insert ");
                item = finanYears.get(i);
                if ("".equals(item.getYear())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                if (item.getAss_total() == null || "".equals(item.getAss_total()) || "".equals(item.getAss_value()) || item.getAss_value() == null) {
                    for (int j = 0; j < oldFinanYears.length; ++j) {
                        Log.debug("oldFinanYears[j].data[0]:" + oldFinanYears[j].data[0]);
                        if (item.getYear().equals(oldFinanYears[j].data[0])) {
                            item.setAss_total(oldFinanYears[j].data[1]);
                            item.setDept_total(oldFinanYears[j].data[2]);
                            item.setAss_value(oldFinanYears[j].data[3]);
                            item.setShort_ass(oldFinanYears[j].data[4]);
                            item.setShort_dept(oldFinanYears[j].data[5]);
                            item.setCapital(oldFinanYears[j].data[6]);
                            break;
                        }
                    }
                }
                sql.append(" INTO UM_REC_FINAN_BALANCE (BIZ_REG_NO, YEAR, ASSETS_TOTAL, DEBT_TOTAL, ASSET_VALUE, SHORT_ASSETS, SHORT_DEBT, CAPITAL)");
                sql.append(" values(?, ?, ?, ?, ?, ?, ?, ?)");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, item.getYear());
                pstmt.setString(3, item.getAss_total());
                pstmt.setString(4, item.getDept_total());
                pstmt.setString(5, item.getAss_value());
                pstmt.setString(6, item.getShort_ass());
                pstmt.setString(7, item.getShort_dept());
                pstmt.setString(8, item.getCapital());
                pstmt.executeUpdate();
                sql = new StringBuffer();
            }
        }
        catch (Exception e) {
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".insertBalance(" + bizRegNo + ")\n\n" + e.toString());
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
    
    public void deleteReport(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" delete from UM_REC_FINAN_REPORT  ");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".deleteReport(" + bizRegNo + ") : " + e.toString());
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
    
    public void insertReport(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        final PreparedStatement pstmt2 = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            ReportItem item = null;
            final CommEntity[] oldFinanYears = this.getListReport(bizRegNo, conn);
            Log.debug("oldFinanYears" + oldFinanYears.length);
            this.delete(bizRegNo, conn);
            Log.debug("finanYears: " + finanYears.size());
            for (int i = 0; i < finanYears.size(); ++i) {
                sql.append("insert ");
                item = finanYears.get(i);
                if ("".equals(item.getYear())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                if (item.getREVENUE_TOTAL() == null || "".equals(item.getREVENUE_TOTAL()) || "".equals(item.getAVERAGE_REVENUE()) || item.getAVERAGE_REVENUE() == null) {
                    for (int j = 0; j < oldFinanYears.length; ++j) {
                        Log.debug("oldFinanYears[j].data[0]:" + oldFinanYears[j].data[0]);
                        if (item.getYear().equals(oldFinanYears[j].data[0])) {
                            item.setREVENUE_TOTAL(oldFinanYears[j].data[1]);
                            item.setAVERAGE_REVENUE(oldFinanYears[j].data[2]);
                            item.setPROFIT_BEFORE(oldFinanYears[j].data[3]);
                            item.setPROFIT_AFTER(oldFinanYears[j].data[4]);
                            break;
                        }
                    }
                }
                sql.append(" INTO UM_REC_FINAN_REPORT (BIZ_REG_NO, YEAR, REVENUE_TOTAL, AVERAGE_REVENUE, PROFIT_BEFORE, PROFIT_AFTER )");
                sql.append(" values(?, ?, ?, ?, ?, ?)");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, item.getYear());
                pstmt.setString(3, item.getREVENUE_TOTAL());
                pstmt.setString(4, item.getAVERAGE_REVENUE());
                pstmt.setString(5, item.getPROFIT_BEFORE());
                pstmt.setString(6, item.getPROFIT_AFTER());
                pstmt.executeUpdate();
                sql = new StringBuffer();
            }
        }
        catch (Exception e) {
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".insertReport(" + bizRegNo + ")\n\n" + e.toString());
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
            sql.append(" DELETE FROM UM_REC_FINAN_STATE ");
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
            sql.append(" select YEAR, ATTACH_FILE, ORG_FILE, UPDATE_DT ");
            sql.append(" from UM_REC_FINAN_STATE ");
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
            throw e;
        }
    }
}
