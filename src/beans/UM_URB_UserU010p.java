// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_GOE_ConiC070b;
import entity.UM_GOE_ConiC060b;
import entity.UM_GOE_ConiC050b;
import entity.UM_GOE_ConiC040b;
import entity.UM_GOE_ConiC110b;
import entity.UM_GOE_ConiC030b;
import java.util.Vector;
import entity.UM_GOE_ConiC020b;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_GOE_ConiC010b;

public class UM_URB_UserU010p
{
    public UM_GOE_ConiC010b select_main(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        UM_GOE_ConiC010b ett = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "  SELECT BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   TO_CHAR(COMMENCEMENT_DT, 'dd/MM/yyyy'), TO_CHAR(ESTABLISH_DT, 'dd/MM/yyyy'), BIZ_CLS, PRODUCT_CLS,\t\t\t\t   CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECAIL_GOODS_YN,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   REG_VALID_DT, TO_CHAR(REG_DT,'dd/MM/yyyy'), TO_CHAR(UPDATE_DT,'dd/MM/yyyy'), REPR_BIZ_APPROVE_YN, MANAGER_ID,BID_ATTEND_QUALIFY_YN, HEADQUARTER_YN\t\t\t\t\t\t  FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO=?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                ett = new UM_GOE_ConiC010b();
                ett.setSaupNo(rs.getString(1));
                ett.setNationality(rs.getString(2));
                ett.setSangho(rs.getString(3));
                ett.setESangho(rs.getString(4));
                ett.setOpenDate(rs.getString(5));
                ett.setBubinOpenDate(rs.getString(6));
                ett.setJobGubun(rs.getString(7));
                ett.setMakeGubun(rs.getString(8));
                ett.setBubinNo(rs.getString(9));
                ett.setComGubun1(rs.getString(10));
                ett.setComGubun2(rs.getString(11));
                ett.setComGubunYear(rs.getString(12));
                ett.setJabon(rs.getLong(13));
                ett.setEmployeeNo(rs.getInt(14));
                ett.setAccountDate(rs.getString(15));
                ett.setZipCode(rs.getString(16));
                ett.setLocalCode(rs.getString(17));
                ett.setAddr(rs.getString(18));
                ett.setRestAddr(rs.getString(19));
                ett.setTel(rs.getString(20));
                ett.setFax(rs.getString(21));
                ett.setHomepage(rs.getString(22));
                ett.setExceptYN(rs.getString(23));
                ett.setRegistOkDate(rs.getString(24));
                ett.setRegistDate(rs.getString(25));
                ett.setRenewDate(rs.getString(26));
                ett.setDpOkYN(rs.getString(27));
                ett.setChurijaId(rs.getString(28));
                ett.setQualification(rs.getString(29));
                ett.setBubinYN(rs.getString(30));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.select_main('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.select_main(''):" + exc.toString());
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
    
    public UM_GOE_ConiC010b ValidTerm_main(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        UM_GOE_ConiC010b ett = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "SELECT /* MONTHS_BETWEEN(SYSDATE, NVL(UPDATE_DT,CREATE_DT)), */ TO_CHAR(NVL(UPDATE_DT,REG_DT)  ,'yyyy-mm-dd') CHECK_DATE, TO_CHAR(NVL(UPDATE_DT,REG_DT) + INTERVAL '3' YEAR(1) ,'yyyy-mm-dd') EFFECTIVE_DATE FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO=? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                ett = new UM_GOE_ConiC010b();
                ett.setRegistDate(rs.getString(1));
                ett.setRenewDate(rs.getString(2));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.ValidTerm_main('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.ValidTerm_main('" + saupNo + "'):" + exc.toString());
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
    
    public UM_GOE_ConiC020b[] select_MS(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC020b[] ettlist2 = (UM_GOE_ConiC020b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "SELECT  a.STD_CLS_CD, a.STD_CLS_NAME, b.CD_NM FROM um_enter_std_cls a, SYN_PUB_CODE b WHERE a.BIZ_REG_NO=?   and b.CD_CLS='GZ8'  and a.STD_CLS_CD=b.CD  ORDER BY a.STD_CLS_NAME DESC ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC020b ett2 = null;
                ett2 = new UM_GOE_ConiC020b();
                ett2.setLicenseCode(rs.getString(1));
                ett2.setLicenseName(rs.getString(2));
                ett2.setLicenseNo(rs.getString(3));
                vec.addElement(ett2);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.select_MS('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.select_MS('" + saupNo + "'):" + exc.toString());
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
        ettlist2 = new UM_GOE_ConiC020b[vec.size()];
        vec.copyInto(ettlist2);
        return ettlist2;
    }
    
    public UM_GOE_ConiC020b[] select_MS01(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC020b[] ettlist2 = (UM_GOE_ConiC020b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "SELECT  STD_CLS_NAME, STD_CLS_CD, STD_CLS_NO, to_char(ISSUED_DT,'dd/MM/yyyy'), to_char(STD_CLS_EXPIRY_DT,'dd/MM/yyyy'), CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR, MAST_STD_CLS_YN, TO_CHAR(REG_DT, 'dd/MM/yyyy'), TO_CHAR(UPDATE_DT, 'dd/MM/yyyy')FROM UM_ENTER_STD_CLS WHERE BIZ_REG_NO=? ORDER BY MAST_STD_CLS_YN DESC ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC020b ett2 = null;
                ett2 = new UM_GOE_ConiC020b();
                ett2.setLicenseName(rs.getString(1));
                ett2.setLicenseCode(rs.getString(2));
                ett2.setLicenseNo(rs.getString(3));
                ett2.setLicenseBeginDate(rs.getString(4));
                ett2.setLicenseEndDate(rs.getString(5));
                ett2.setSigongAccount(rs.getLong(6));
                ett2.setPeonggaYear(rs.getString(7));
                ett2.setDpLicenseYN(rs.getString(8));
                ett2.setInsertDate(rs.getString(9));
                ett2.setUpdateDate(rs.getString(10));
                vec.addElement(ett2);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.select_MS('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.select_MS('" + saupNo + "'):" + exc.toString());
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
        ettlist2 = new UM_GOE_ConiC020b[vec.size()];
        vec.copyInto(ettlist2);
        return ettlist2;
    }
    
    public UM_GOE_ConiC020b[] ValidTerm_MS(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC020b[] ettlist2 = (UM_GOE_ConiC020b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "SELECT c.CD_NM, a.STD_CLS_CD, TO_CHAR(a.ISSUED_DT,'YYYY-MM-DD HH24:MI:SS') CHECK_DATE , TO_CHAR(a.STD_CLS_EXPIRY_DT,'YYYY-MM-DD HH24:MI:SS') EFFECTIVE_DATE FROM um_enter_std_cls a, UM_LICENSE_EXPIRED b, SYN_PUB_CODE c WHERE a.BIZ_REG_NO=?  AND a.STD_CLS_CD = b.LICENSE_CD  AND a.STD_CLS_CD = c.CD AND c.CD_CLS='GU9'  ORDER BY a.STD_CLS_EXPIRY_DT ASC ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC020b ett2 = null;
                ett2 = new UM_GOE_ConiC020b();
                ett2.setLicenseName(rs.getString(1));
                ett2.setLicenseCode(rs.getString(2));
                ett2.setLicenseBeginDate(rs.getString(3));
                ett2.setLicenseEndDate(rs.getString(4));
                vec.addElement(ett2);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.ValidTerm_MS('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.ValidTerm_MS('" + saupNo + "'):" + exc.toString());
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
        ettlist2 = new UM_GOE_ConiC020b[vec.size()];
        vec.copyInto(ettlist2);
        return ettlist2;
    }
    
    public UM_GOE_ConiC030b[] select_DP(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC030b[] ettlist3 = (UM_GOE_ConiC030b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "SELECT REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE, REPR_CLS FROM UM_REPR WHERE BIZ_REG_NO=? order by MAST_REPR_YN desc";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC030b ett3 = null;
                ett3 = new UM_GOE_ConiC030b();
                ett3.setCeoJuminNo(rs.getString(1));
                ett3.setCeoName(rs.getString(2));
                ett3.setCeoMail(rs.getString(3));
                ett3.setCeoYN(rs.getString(4));
                ett3.setCeohphone(rs.getString(5));
                ett3.setCeoyuhyung(rs.getString(6));
                vec.addElement(ett3);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.select_DP('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.select_DP('" + saupNo + "'):" + exc.toString());
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
        ettlist3 = new UM_GOE_ConiC030b[vec.size()];
        vec.copyInto(ettlist3);
        return ettlist3;
    }
    
    public UM_GOE_ConiC110b[] select_BR(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC110b[] ettlist8 = (UM_GOE_ConiC110b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "  SELECT a.BIZ_REG_NO, BIZ_NM, ZIP_CD, AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX,\t\t\t\t   \t\t    REPR_NM, REPR_IDENT_NO, DECODE(c.STATE_CLS,'07','Y') STATE_CLS\t\t\t\t\t\t\t\t\t\t\t     FROM  UM_SUPPLIER_ENTER_MAST a, UM_REPR b, UM_ENTER_STATE c\t\t\t\t\t\t\t\t\t\t\t\t\t     WHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.BIZ_REG_NO = c.BIZ_REG_NO(+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND c.STATE_CLS(+) = '07'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND b.MAST_REPR_YN = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t       FROM UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t        WHERE BIZ_REG_NO  =?)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND HEADQUARTER_YN = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     ORDER BY a.BIZ_REG_NO ASC\t";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC110b ett8 = null;
                ett8 = new UM_GOE_ConiC110b();
                ett8.setSaupNo(rs.getString(1));
                ett8.setSangho(rs.getString(2));
                ett8.setZipCode(rs.getString(3));
                ett8.setLocalCode(rs.getString(4));
                ett8.setAddr(rs.getString(5));
                ett8.setRestAddr(rs.getString(6));
                ett8.setTel(rs.getString(7));
                ett8.setFax(rs.getString(8));
                ett8.setCeoName(rs.getString(9));
                ett8.setCeoJuminNo(rs.getString(10));
                ett8.setCancelYN(rs.getString(11));
                vec.addElement(ett8);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.select_BR('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.select_BR('" + saupNo + "'):" + exc.toString());
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
        ettlist8 = new UM_GOE_ConiC110b[vec.size()];
        vec.copyInto(ettlist8);
        return ettlist8;
    }
    
    public UM_GOE_ConiC040b[] select_GJ(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC040b[] ettlist4 = (UM_GOE_ConiC040b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "SELECT SERIAL_NO, FACTORY_NM, FACTORY_ADDR, FACTORY_ADDR2, FACTORY_PHONE_NO,        FACTORY_FAX, FACTORY_ZIP_CD, FACTORY_RENT_YN,        to_char(FACTORY_RENT_START_DT, 'yyyy-mm-dd'), to_char(FACTORY_RENT_END_DT, 'yyyy-mm-dd') FROM UM_FACTORY_INFO WHERE BIZ_REG_NO=? order by SERIAL_NO asc";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC040b ett4 = null;
                ett4 = new UM_GOE_ConiC040b();
                ett4.setIlrunNo(rs.getInt(1));
                ett4.setFactoryName(rs.getString(2));
                ett4.setFactoryAddr(rs.getString(3));
                ett4.setFactoryRestAddr(rs.getString(4));
                ett4.setFactoryTel(rs.getString(5));
                ett4.setFactoryFax(rs.getString(6));
                ett4.setFactoryZipCode(rs.getString(7));
                ett4.setFactoryleaseYN(rs.getString(8));
                ett4.setFactoryleaseSdate(rs.getString(9));
                ett4.setFactoryleaseEdate(rs.getString(10));
                vec.addElement(ett4);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.select_GJ('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.select_GJ('" + saupNo + "'):" + exc.toString());
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
        ettlist4 = new UM_GOE_ConiC040b[vec.size()];
        vec.copyInto(ettlist4);
        return ettlist4;
    }
    
    public UM_GOE_ConiC040b[] ValidTerm_GJ(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC040b[] ettlist4 = (UM_GOE_ConiC040b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "SELECT SERIAL_NO, FACTORY_NM, FACTORY_ADDR, FACTORY_ADDR2, FACTORY_ZIP_CD,        TO_CHAR(FACTORY_RENT_START_DT ,'yyyy-mm-dd') CHECK_DATE , TO_CHAR(FACTORY_RENT_END_DT ,'yyyy-mm-dd') EFFECTIVE_DATE FROM UM_FACTORY_INFO WHERE FACTORY_RENT_YN = 'Y' AND BIZ_REG_NO=? ORDER BY FACTORY_RENT_END_DT ASC";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC040b ett4 = null;
                ett4 = new UM_GOE_ConiC040b();
                ett4.setIlrunNo(rs.getInt(1));
                ett4.setFactoryName(rs.getString(2));
                ett4.setFactoryAddr(rs.getString(3));
                ett4.setFactoryRestAddr(rs.getString(4));
                ett4.setFactoryZipCode(rs.getString(5));
                ett4.setFactoryleaseSdate(rs.getString(6));
                ett4.setFactoryleaseEdate(rs.getString(7));
                vec.addElement(ett4);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.ValidTerm_GJ('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.ValidTerm_GJ('" + saupNo + "'):" + exc.toString());
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
        ettlist4 = new UM_GOE_ConiC040b[vec.size()];
        vec.copyInto(ettlist4);
        return ettlist4;
    }
    
    public UM_GOE_ConiC050b[] select_ID(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC050b[] ettlist5 = (UM_GOE_ConiC050b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "SELECT IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, BIDDING_AGENT_YN  FROM UM_BID_AGENT WHERE BIZ_REG_NO=?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC050b ett5 = null;
                ett5 = new UM_GOE_ConiC050b();
                ett5.setJuminNo(rs.getString(1));
                ett5.setName(rs.getString(2));
                ett5.setJobPart(rs.getString(3));
                ett5.setDutyName(rs.getString(4));
                ett5.setTel(rs.getString(5));
                ett5.setMail(rs.getString(6));
                ett5.setHandphone(rs.getString(7));
                ett5.setFax(rs.getString(8));
                ett5.setIpchalYN(rs.getString(9));
                vec.addElement(ett5);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.select_ID('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.select_ID('" + saupNo + "'):" + exc.toString());
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
        ettlist5 = new UM_GOE_ConiC050b[vec.size()];
        vec.copyInto(ettlist5);
        return ettlist5;
    }
    
    public UM_GOE_ConiC050b[] select_IDHist(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC050b[] ettlist5 = (UM_GOE_ConiC050b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "SELECT IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, BIDDING_AGENT_YN  FROM UM_BID_AGENT_HIST a WHERE BIZ_REG_NO=? AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO=a.BIZ_REG_NO) ";
            pstm = con.prepareStatement(sql);
            Log.debug(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC050b ett5 = null;
                ett5 = new UM_GOE_ConiC050b();
                ett5.setJuminNo(rs.getString(1));
                ett5.setName(rs.getString(2));
                ett5.setJobPart(rs.getString(3));
                ett5.setDutyName(rs.getString(4));
                ett5.setTel(rs.getString(5));
                ett5.setMail(rs.getString(6));
                ett5.setHandphone(rs.getString(7));
                ett5.setFax(rs.getString(8));
                ett5.setIpchalYN(rs.getString(9));
                vec.addElement(ett5);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.select_ID('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.select_ID('" + saupNo + "'):" + exc.toString());
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
        ettlist5 = new UM_GOE_ConiC050b[vec.size()];
        vec.copyInto(ettlist5);
        return ettlist5;
    }
    
    public UM_GOE_ConiC060b[] select_MU(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC060b[] ettlist6 = (UM_GOE_ConiC060b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        final String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC060b ett6 = null;
                ett6 = new UM_GOE_ConiC060b();
                ett6.setGoodsNo(rs.getString(1));
                ett6.setFormNo(rs.getString(2));
                ett6.setFormOrg(rs.getString(3));
                ett6.setFormDate(rs.getString(4));
                ett6.setThreeSale(rs.getLong(5));
                ett6.setMakeYN(rs.getString(6));
                ett6.setDpGoodsYN(rs.getString(7));
                ett6.setGoodsName(rs.getString(8));
                ett6.setInsertDate(rs.getString(9));
                ett6.setUpdateDate(rs.getString(10));
                ett6.setDirectPaper(rs.getString(11));
                ett6.setValidStartDate(rs.getString(12));
                ett6.setValidEndDate(rs.getString(13));
                ett6.setStandCode(rs.getString(14));
                ett6.setPublicOrg(rs.getString(15));
                ett6.setCertiName(rs.getString(16));
                vec.addElement(ett6);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.select_MU('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.select_MU('" + saupNo + "'):" + exc.toString());
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
        ettlist6 = new UM_GOE_ConiC060b[vec.size()];
        vec.copyInto(ettlist6);
        return ettlist6;
    }
    
    public UM_GOE_ConiC060b[] ValidTerm_MU(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC060b[] ettlist6 = (UM_GOE_ConiC060b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        final String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC060b ett6 = null;
                ett6 = new UM_GOE_ConiC060b();
                ett6.setGoodsNo(rs.getString(1));
                ett6.setDpGoodsYN(rs.getString(2));
                ett6.setGoodsName(rs.getString(3));
                ett6.setInsertDate(rs.getString(4));
                ett6.setUpdateDate(rs.getString(5));
                ett6.setDirectPaper(rs.getString(6));
                ett6.setValidStartDate(rs.getString(7));
                ett6.setValidEndDate(rs.getString(8));
                vec.addElement(ett6);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.ValidTerm_MU('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.ValidTerm_MU('" + saupNo + "'):" + exc.toString());
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
        ettlist6 = new UM_GOE_ConiC060b[vec.size()];
        vec.copyInto(ettlist6);
        return ettlist6;
    }
    
    public UM_GOE_ConiC070b[] select_stat(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC070b[] ettlist7 = (UM_GOE_ConiC070b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "\tSELECT TO_CHAR(RAISED_DT,'YYYY-MM-DD') RAISED_DT, STATE_CLS, REMARK, TO_CHAR(START_DT,'YYYY-MM-DD') START_DT,\t\t\t\t\tTO_CHAR(END_DT,'YYYY-MM-DD') END_DT, MANAGER_ID\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFROM UM_ENTER_STATE\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE BIZ_REG_NO=? order by RAISED_DT asc";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC070b ett7 = null;
                ett7 = new UM_GOE_ConiC070b();
                ett7.setHappenDate(rs.getString(1));
                ett7.setStateGubun(rs.getString(2));
                ett7.setNote(rs.getString(3));
                ett7.setStartDate(rs.getString(4));
                ett7.setEndDate(rs.getString(5));
                ett7.setchurijaId(rs.getString(6));
                vec.addElement(ett7);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.select_stat('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.select_stat('" + saupNo + "'):" + exc.toString());
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
        ettlist7 = new UM_GOE_ConiC070b[vec.size()];
        vec.copyInto(ettlist7);
        return ettlist7;
    }
    
    public int isUpdate_main(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        int isUpdate_Cnt = 0;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "  SELECT /*+ index(IDX_UM_SUPPLIER_ENTER_MAST_05)*/\t\t\t\t\t\t\t\t\t               COUNT(DISTINCT a.BIZ_REG_NO) BIZ_RENEW\t\t\t\t\t   FROM UM_SUPPLIER_ENTER_MAST a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   WHERE  TO_CHAR(UPDATE_DT,'yyyymmdd') <= '20070427'\t\t\t\t\t\t\t   AND NOT EXISTS (SELECT 'N' FROM UM_ENTER_STATE b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\tAND b.STATE_CLS IN ('02','03','04','07','13','14'))\t\t\t\t   AND a.BID_ATTEND_QUALIFY_YN IS NULL AND a.BIZ_REG_NO NOT LIKE 'F%'\t\t\t   AND a.BIZ_REG_NO=?\t";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                isUpdate_Cnt = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.isUpdate_main('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.isUpdate_main('" + saupNo + "'):" + exc.toString());
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
        return isUpdate_Cnt;
    }
    
    public String getIpchalYN_ID(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        String IpchalYN = "";
        int IpchalYN_Cnt = 0;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "SELECT distinct NVL(a.BIDDING_AGENT_YN, 'N')  RENEW_REGISTRATION_YN FROM UM_BID_AGENT a WHERE  a.BIZ_REG_NO=? ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                IpchalYN = rs.getString(1);
                ++IpchalYN_Cnt;
            }
            if (IpchalYN_Cnt > 1) {
                IpchalYN = "N";
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.getIpchalYN_ID('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.getIpchalYN_ID('" + saupNo + "'):" + exc.toString());
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
        return IpchalYN;
    }
    
    public int getBranchGubun(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        int branchgubun = 0;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "  SELECT COUNT(*) FROM UM_SUPPLIER_ENTER_MAST      WHERE BIZ_REG_NO = ?\t\t\t\t\t\t\t\t   AND HEADQUARTER_YN = 'N'\t\t\t\t\t\t\t\t\t\t\t";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                branchgubun = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("getBranchGubun('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("getBranchGubun('" + saupNo + "'):" + exc.toString());
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
        return branchgubun;
    }
}
