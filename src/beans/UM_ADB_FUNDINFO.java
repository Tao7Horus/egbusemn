// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.StringTokenizer;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.Trx;
import entity.UM_ADE_GovuA040b;
import common.util.UM_COB_GTQ904;
import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;
import entity.UM_RAE_GovuA010b;

public class UM_ADB_FUNDINFO
{
    public void updateFundCode(final UM_RAE_GovuA010b ett, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " UPDATE UM_PUB_INSTITU_ACCT_CD SET INSTITU_ACCT_CD= ? WHERE INSTITU_CD= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ett.getsaupNo());
            pstmt.setString(2, ett.getg2bCode());
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void updateGonginfo(final String mCode, final String UserID, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = " update UM_PUB_INSTITU_MAST     set UPDATE_DT = sysdate, MANAGER_ID = ?   where INSTITU_CD = ? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, UserID);
            pstmt.setString(2, mCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("UM_PUB_INSTITU_MAST Cập nhật số 1 hoặc 2");
            }
        }
        catch (Exception e) {
            Log.debug("[" + this.getClass().getName() + ".updateGonginfo] " + e.toString());
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void deleteFundCode(final String mCode, final String fundCode, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = " delete from UM_PUB_INSTITU_ACCT_CD where INSTITU_CD=? and  INSTITU_ACCT_CD=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mCode);
            pstmt.setString(2, fundCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("UM_PUB_INSTITU_ACCT_CD Cập nhật số 1 hoặc 2");
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void updateFundCode(final String mCode, final String beforeFundCode, final String afterFundCode, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = " update UM_PUB_INSTITU_ACCT_CD set  INSTITU_ACCT_CD=? where INSTITU_CD=? and INSTITU_ACCT_CD=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, afterFundCode);
            pstmt.setString(2, mCode);
            pstmt.setString(3, beforeFundCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("UM_PUB_INSTITU_ACCT_CD update Cập nhật số 1 hoặc 2");
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void insertFundCode(final String mCode, final String FundCode, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "insert into UM_PUB_INSTITU_ACCT_CD (INSTITU_CD, INSTITU_ACCT_CD) values(?,  ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mCode);
            pstmt.setString(2, FundCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("UM_PUB_INSTITU_ACCT_CD insert Số 1 hoặc 2");
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void updateFundCode(final String mCode, final String FundCode, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " delete from UM_PUB_INSTITU_ACCT_CD where INSTITU_CD = ? ";
        final String sql2 = " insert into UM_PUB_INSTITU_ACCT_CD (INSTITU_CD, INSTITU_ACCT_CD) values(?,  ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mCode);
            pstmt.executeUpdate();
            pstmt.clearParameters();
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, mCode);
            pstmt.setString(2, FundCode);
            pstmt.executeUpdate();
            pstmt.clearParameters();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public boolean isMasterCreditCodeExist(final String mCode, final Connection conn) throws Exception {
        boolean result = false;
        final String sql = "select count(*) from UM_PUB_INSTITU_ACCT_CD where INSTITU_CD='" + mCode + "'";
        final int count = new UM_COB_GTQ904().getCount(this, sql, conn);
        if (count > 0) {
            result = true;
        }
        return result;
    }
    
    public UM_ADE_GovuA040b selectFundCod(final String mCode) throws Exception {
        if (mCode == null || mCode == "") {
            return new UM_ADE_GovuA040b();
        }
        Trx trx = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final UM_ADE_GovuA040b ett = new UM_ADE_GovuA040b();
        try {
            final String sql = "select INSTITU_ACCT_CD from UM_PUB_INSTITU_ACCT_CD where INSTITU_CD='" + mCode + "'";
            trx = new Trx(this);
            conn = trx.getConnection();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ett.setmathCode(rs.getString(1));
            }
        }
        catch (SQLException ex) {}
        catch (Exception ex2) {}
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex3) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex4) {}
            }
            if (conn != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex6) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex7) {}
        }
        if (conn != null) {
            try {
                trx.close();
            }
            catch (Exception ex8) {}
        }
        return ett;
    }
    
    public void insertMultiFundCod(final String mCode, final String mathCode, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = " insert into UM_PUB_INSTITU_ACCT_CD (INSTITU_CD, INSTITU_ACCT_CD) values(?,  ?)";
        try {
            final StringTokenizer st = new StringTokenizer(mathCode, ",");
            pstmt = conn.prepareStatement(sql);
            while (st.hasMoreTokens()) {
                pstmt.setString(1, mCode);
                pstmt.setString(2, st.nextToken());
                pstmt.executeUpdate();
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
}
