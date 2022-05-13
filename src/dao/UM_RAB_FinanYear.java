// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.sql.ResultSet;
import common.Trx;
import common.CommDbQuery;
import java.util.Vector;
import entity.ReportItem;
import common.CommEntity;
import java.util.ArrayList;
import entity.FinalItem;
import java.sql.PreparedStatement;
import common.Log;
import entity.FinanYearItem;
import java.sql.Connection;
import java.util.List;

public class UM_RAB_FinanYear
{
    public void save1(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            FinanYearItem item = null;
            for (int i = 0; i < finanYears.size(); ++i) {
                sql.append("INSERT ");
                item = (FinanYearItem)finanYears.get(i);
                if ("".equals(item.getYear()) || "".equals(item.getAttachFile())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                sql.append(" INTO UM_FINAN_STATE (BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, UPDATE_DT)");
                sql.append(" SELECT BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, SYSDATE FROM UM_REC_FINAN_STATE WHERE BIZ_REG_NO=? AND YEAR=? ");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, item.getYear());
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
    
    public void insertBalance(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            FinalItem item = null;
            for (int i = 0; i < finanYears.size(); ++i) {
                sql.append("INSERT ");
                item = (FinalItem)finanYears.get(i);
                if ("".equals(item.getYear()) || "".equals(item.getAss_total())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                sql.append(" INTO UM_FINAN_BALANCE (BIZ_REG_NO, YEAR, ASSETS_TOTAL, DEBT_TOTAL, ASSET_VALUE, SHORT_ASSETS, SHORT_DEBT, CAPITAL) ");
                sql.append(" SELECT BIZ_REG_NO, YEAR, ASSETS_TOTAL, DEBT_TOTAL, ASSET_VALUE, SHORT_ASSETS, SHORT_DEBT, CAPITAL FROM UM_FINAN_BALANCE WHERE BIZ_REG_NO=? AND YEAR=? ");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, item.getYear());
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
    
    public void insertBalanceFromRec(final String bizRegNo, final Connection conn) throws Exception {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
	        sql.append(" INSERT INTO UM_FINAN_BALANCE (BIZ_REG_NO, YEAR, ASSETS_TOTAL, DEBT_TOTAL, ASSET_VALUE, SHORT_ASSETS, SHORT_DEBT, CAPITAL) ");
	        sql.append(" SELECT BIZ_REG_NO, YEAR, ASSETS_TOTAL, DEBT_TOTAL, ASSET_VALUE, SHORT_ASSETS, SHORT_DEBT, CAPITAL FROM UM_REC_FINAN_BALANCE WHERE BIZ_REG_NO=? ");
	        pstmt = conn.prepareStatement(sql.toString());
	        pstmt.setString(1, bizRegNo);
	        pstmt.executeUpdate();        
        }
        catch (Exception e) {
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".insertBalanceFromRec(" + bizRegNo + ")\n\n" + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {
                	Log.errors(this, ex, String.valueOf(this.getClass().getName()) + ".insertBalanceFromRec(" + bizRegNo + ")\n\n" + ex.toString());
                    throw ex;
                }
            }
        }
    }
    
    public void insertReport(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            FinalItem item = null;
            for (int i = 0; i < finanYears.size(); ++i) {
                sql.append("INSERT ");
                item = (FinalItem)finanYears.get(i);
                if ("".equals(item.getYear()) || "".equals(item.getAss_total())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                sql.append(" INTO UM_FINAN_REPORT (BIZ_REG_NO, YEAR, REVENUE_TOTAL, AVERAGE_REVENUE, PROFIT_BEFORE, PROFIT_AFTER) ");
                sql.append(" SELECT BIZ_REG_NO, YEAR, REVENUE_TOTAL, AVERAGE_REVENUE, PROFIT_BEFORE, PROFIT_AFTER FROM UM_FINAN_REPORT WHERE BIZ_REG_NO=? AND YEAR=? ");
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.setString(1, bizRegNo);
                pstmt.setString(2, item.getYear());
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
    
    public void insertReportFromRec(final String bizRegNo, final Connection conn) throws Exception {
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_FINAN_REPORT (BIZ_REG_NO, YEAR, REVENUE_TOTAL, AVERAGE_REVENUE, PROFIT_BEFORE, PROFIT_AFTER) ");
            sql.append(" SELECT BIZ_REG_NO, YEAR, REVENUE_TOTAL, AVERAGE_REVENUE, PROFIT_BEFORE, PROFIT_AFTER FROM UM_REC_FINAN_REPORT WHERE BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
                
        }
        catch (Exception e) {
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".insertReportFromRec(" + bizRegNo + ")\n\n" + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {
                	Log.errors(this, ex, String.valueOf(this.getClass().getName()) + ".insertReportFromRec(" + bizRegNo + ")\n\n" + ex.toString());
                    throw ex;
                }
            }
        }
    }
    
    public void save(final String bizRegNo, final FinanYearItem[] finanList, final Connection conn) throws Exception {
        if (finanList.length < 1) {
            return;
        }
        final List ls = new ArrayList();
        for (int i = 0; i < finanList.length; ++i) {
            ls.add(finanList[i]);
        }
        this.update(bizRegNo, ls, conn);
    }
    
    public void update(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        final PreparedStatement pstmt2 = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            FinanYearItem item = null;
            final CommEntity[] oldFinanYears = this.getList(bizRegNo, conn);
            this.delete(bizRegNo, conn);
            sql.append("INSERT ALL ");
            final List params = new ArrayList();
            for (int i = 0; i < finanYears.size(); ++i) {
                item = (FinanYearItem)finanYears.get(i);
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
                sql.append(" INTO UM_FINAN_STATE (BIZ_REG_NO, YEAR, ATTACH_FILE, ORG_FILE, UPDATE_DT)");
                sql.append(" values(?, ?, ?, ?, SYSDATE)");
                params.add(bizRegNo);
                params.add(item.getYear());
                params.add(item.getAttachFile());
                params.add(item.getOrgFile());
            }
            sql.append(" SELECT * FROM dual ");
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); ++i) {
                pstmt.setString(i + 1, (params.get(i) == null) ? "" : params.get(i).toString());
            }
            pstmt.executeUpdate();
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
    
    public void insertBalanceFinal(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        final PreparedStatement pstmt2 = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            FinalItem item = null;
            final CommEntity[] oldFinanYears = this.getListBalance(bizRegNo, conn);
            this.delete(bizRegNo, conn);
            sql.append("INSERT ALL ");
            final List params = new ArrayList();
            for (int i = 0; i < finanYears.size(); ++i) {
                item = (FinalItem)finanYears.get(i);
                if ("".equals(item.getYear())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                if (item.getAss_total() == null || "".equals(item.getAss_total()) || "".equals(item.getAss_value()) || item.getAss_value() == null) {
                    for (int j = 0; j < oldFinanYears.length; ++j) {
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
                sql.append(" INTO UM_FINAN_BALANCE (BIZ_REG_NO, YEAR, ASSETS_TOTAL, DEBT_TOTAL, ASSET_VALUE, SHORT_ASSETS, SHORT_DEBT, CAPITAL)");
                sql.append(" values(?, ?, ?, ?, ?, ?, ?, ?)");
                params.add(bizRegNo);
                params.add(item.getYear());
                params.add(item.getAss_total());
                params.add(item.getDept_total());
                params.add(item.getAss_value());
                params.add(item.getShort_ass());
                params.add(item.getShort_dept());
                params.add(item.getCapital());
            }
            sql.append(" SELECT * FROM dual ");
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); ++i) {
                pstmt.setString(i + 1, (params.get(i) == null) ? "" : params.get(i).toString());
            }
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".insertBalanceFinal(" + bizRegNo + ")\n\n" + e.toString());
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
    
    public void insertReportFinal(final String bizRegNo, final List finanYears, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        final PreparedStatement pstmt2 = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            ReportItem item = null;
            final CommEntity[] oldFinanYears = this.getListReport(bizRegNo, conn);
            this.delete(bizRegNo, conn);
            sql.append("INSERT ALL ");
            final List params = new ArrayList();
            for (int i = 0; i < finanYears.size(); ++i) {
                item = (ReportItem)finanYears.get(i);
                if ("".equals(item.getYear())) {
                    throw new Exception("Finan does not exist in " + i);
                }
                if (item.getREVENUE_TOTAL() == null || "".equals(item.getREVENUE_TOTAL()) || "".equals(item.getAVERAGE_REVENUE()) || item.getAVERAGE_REVENUE() == null) {
                    for (int j = 0; j < oldFinanYears.length; ++j) {
                        if (item.getYear().equals(oldFinanYears[j].data[0])) {
                            item.setREVENUE_TOTAL(oldFinanYears[j].data[1]);
                            item.setAVERAGE_REVENUE(oldFinanYears[j].data[2]);
                            item.setPROFIT_BEFORE(oldFinanYears[j].data[3]);
                            item.setPROFIT_AFTER(oldFinanYears[j].data[4]);
                            break;
                        }
                    }
                }
                sql.append(" INTO UM_FINAN_REPORT (BIZ_REG_NO, YEAR, REVENUE_TOTAL, AVERAGE_REVENUE, PROFIT_BEFORE, PROFIT_AFTER)");
                sql.append(" values(?, ?, ?, ?, ?, ?)");
                params.add(bizRegNo);
                params.add(item.getYear());
                params.add(item.getREVENUE_TOTAL());
                params.add(item.getAVERAGE_REVENUE());
                params.add(item.getPROFIT_BEFORE());
                params.add(item.getPROFIT_AFTER());
            }
            sql.append(" SELECT * FROM dual ");
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); ++i) {
                pstmt.setString(i + 1, (params.get(i) == null) ? "" : params.get(i).toString());
            }
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".insertReportFinal(" + bizRegNo + ")\n\n" + e.toString());
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
            sql.append(" DELETE FROM UM_FINAN_STATE ");
            sql.append(" where BIZ_REG_NO = ? ");
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
    
    public CommEntity[] getList(final String bizRegNo, final Connection conn) throws Exception {
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
        finally {
            if (tr != null) {
                tr.close();
            }
        }
    }
    
    public FinanYearItem[] selectUpdate(final String bizRegNo, final Connection conn) throws Exception {
        final String sql = " SELECT YEAR, ATTACH_FILE, ORG_FILE FROM UM_FINAN_STATE_HIST a WHERE BIZ_REG_NO = ? AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO) ";
        PreparedStatement psmt = null;
        ResultSet res = null;
        FinanYearItem item = null;
        final Vector vec = new Vector();
        FinanYearItem[] results = (FinanYearItem[])null;
        final String[] parameters = { bizRegNo };
        try {
            psmt = conn.prepareStatement(sql);
            for (int i = 0; i < parameters.length; ++i) {
                psmt.setString(i + 1, parameters[i]);
            }
            res = psmt.executeQuery();
            while (res.next()) {
                item = new FinanYearItem();
                item.setYear(res.getString(1));
                item.setAttachFile(res.getString(2));
                item.setOrgFile(res.getString(3));
                vec.add(item);
                item = null;
            }
            results = new FinanYearItem[vec.size()];
            vec.copyInto(results);
            return results;
        }
        catch (Exception exf) {
            Log.errors(this, exf, "");
            throw exf;
        }
    }
}
