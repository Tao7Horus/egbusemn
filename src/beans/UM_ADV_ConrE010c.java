// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Vector;
import entity.UM_GOE_ConiC070b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_ADE_ConrE010b;

public class UM_ADV_ConrE010c
{
    public UM_ADE_ConrE010b select_user(final String saupNo, final String renewDate) {
        Trx trx = null;
        Connection con = null;
        UM_ADE_ConrE010b ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            final String sql = "SELECT distinct  BIZ_REG_NO, UPDATE_DT, NATIONALITY, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS,  MAST_INDUSTRY_CD_STD, CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL, EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX, WEBSITE,  REG_VALID_DT, REG_DT, REPR_BIZ_APPROVE_YN, MANAGER_ID  FROM UM_SUPPLIER_ENTER_MAST_HIST where BIZ_REG_NO= ?    AND UPDATE_DT = To_Date(substr( ? ,1,19), 'yyyy-mm-dd hh24:mi:ss') ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            Log.debug("Sql=====>" + saupNo);
            Log.debug("Sql====>" + renewDate);
            Log.debug("Sql" + sql);
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            pstm.setString(2, renewDate);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_ADE_ConrE010b();
                ett.setSaupNo(rs.getString(1));
                ett.setRenewDate(rs.getString(2));
                ett.setNationality(rs.getString(3));
                ett.setSangho(rs.getString(4));
                ett.setESangho(rs.getString(5));
                ett.setOpenDate(rs.getString(6));
                ett.setBubinOpenDate(rs.getString(7));
                ett.setJobGubun(rs.getString(8));
                ett.setMakeGubun(rs.getString(9));
                ett.setDpUpjongCode(rs.getString(10));
                ett.setBubinNo(rs.getString(11));
                ett.setComGubun1(rs.getString(12));
                ett.setComGubun2(rs.getString(13));
                ett.setComGubunYear(rs.getString(14));
                ett.setJabon(rs.getLong(15));
                ett.setEmployeeNo(rs.getInt(16));
                ett.setAccountDate(rs.getString(17));
                ett.setZipCode(rs.getString(18));
                ett.setLocalCode(rs.getString(19));
                ett.setAddr(rs.getString(20));
                ett.setRestAddr(rs.getString(21));
                ett.setTel(rs.getString(22));
                ett.setFax(rs.getString(23));
                ett.setHomepage(rs.getString(24));
                ett.setRegistOkDate(rs.getString(25));
                ett.setRegistDate(rs.getString(26));
                ett.setDpOkYN(rs.getString(27));
                ett.setChurijaId(rs.getString(28));
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "");
            Log.debug("UM_ADV_ConrE010c.select_user('" + saupNo + "','" + renewDate + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.errors(this, exc, "");
            Log.debug("UM_ADV_ConrE010c.select_user('" + saupNo + "','" + renewDate + "'):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ett;
    }
    
    public UM_ADE_ConrE010b select_user(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        UM_ADE_ConrE010b ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            final String sql = "SELECT distinct  BIZ_REG_NO, UPDATE_DT, NATIONALITY, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS,  MAST_INDUSTRY_CD_STD, CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL, EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECIAL_GOODS_YN, MAST_GOOD_CLS_NO, MAST_LICENSE_NO, MAST_REPR_NM, REG_VALID_DT, REG_DT, REPR_BIZ_APPROVE_YN, MANAGER_ID, APPROVE_YN  FROM UM_SUPPLIER_ENTER_MAST_HIST a where BIZ_REG_NO= ?    AND VER = (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO= a.BIZ_REG_NO) ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ett = new UM_ADE_ConrE010b();
                ett.setSaupNo(rs.getString(1));
                ett.setRenewDate(rs.getString(2));
                ett.setNationality(rs.getString(3));
                ett.setSangho(rs.getString(4));
                ett.setESangho(rs.getString(5));
                ett.setOpenDate(rs.getString(6));
                ett.setBubinOpenDate(rs.getString(7));
                ett.setJobGubun(rs.getString(8));
                ett.setMakeGubun(rs.getString(9));
                ett.setDpUpjongCode(rs.getString(10));
                ett.setBubinNo(rs.getString(11));
                ett.setComGubun1(rs.getString(12));
                ett.setComGubun2(rs.getString(13));
                ett.setComGubunYear(rs.getString(14));
                ett.setJabon(rs.getLong(15));
                ett.setEmployeeNo(rs.getInt(16));
                ett.setAccountDate(rs.getString(17));
                ett.setZipCode(rs.getString(18));
                ett.setLocalCode(rs.getString(19));
                ett.setAddr(rs.getString(20));
                ett.setRestAddr(rs.getString(21));
                ett.setTel(rs.getString(22));
                ett.setFax(rs.getString(23));
                ett.setHomepage(rs.getString(24));
                ett.setExceptYN(rs.getString(25));
                ett.setDpGoodsNo(rs.getString(26));
                ett.setDpLicenseCode(rs.getString(27));
                ett.setDpCeoName(rs.getString(28));
                ett.setRegistOkDate(rs.getString(29));
                ett.setRegistDate(rs.getString(30));
                ett.setDpOkYN(rs.getString(31));
                ett.setChurijaId(rs.getString(32));
                ett.setApproveYn(rs.getString(33));
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "");
        }
        catch (Exception exc) {
            Log.errors(this, exc, "");
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ett;
    }
    
    public UM_GOE_ConiC070b[] select_state(final String mCode) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC070b[] settlist = (UM_GOE_ConiC070b[])null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            final String sql = " SELECT  TO_CHAR(RAISED_DT,'YYYY-MM-DD') RAISED_DT,  STATE_CLS,   REMARK,      TO_CHAR(START_DT,'YYYY-MM-DD') START_DT, TO_CHAR(END_DT,'YYYY-MM-DD') END_DT     FROM  UM_ENTER_STATE  WHERE BIZ_REG_NO= ? order by RAISED_DT asc ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, mCode);
            UM_GOE_ConiC070b sett = null;
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                sett = new UM_GOE_ConiC070b();
                sett.setHappenDate(rs.getString(1));
                sett.setStateGubun(rs.getString(2));
                sett.setNote(rs.getString(3));
                sett.setStartDate(rs.getString(4));
                sett.setEndDate(rs.getString(5));
                vec.addElement(sett);
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "");
            Log.debug("UM_ADV_ConrE010c.select_state('" + mCode + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.errors(this, exc, "");
            Log.debug("UM_ADV_ConrE010c.select_state('" + mCode + "'):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        settlist = new UM_GOE_ConiC070b[vec.size()];
        vec.copyInto(settlist);
        return settlist;
    }
    
    public int Max_statecount(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int statecount = 0;
        try {
            final String sql = "select  count(*)  FROM  UM_ENTER_STATE  WHERE BIZ_REG_NO= ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                statecount = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrE010c.Max_statecount('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrE010c.Max_statecount('" + saupNo + "'):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return statecount;
    }
    
    private String retSpace(final String str) {
        return (str == null) ? "" : str.trim();
    }
}
