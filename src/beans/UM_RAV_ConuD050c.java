// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_GOE_ConiC110b;
import entity.UM_GOE_ConiC060b;
import entity.UM_GOE_ConiC050b;
import entity.UM_GOE_ConiC030b;
import java.util.Vector;
import entity.UM_GOE_ConiC020b;
import entity.UM_GOE_ConiC010b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import common.ComStr;

public class UM_RAV_ConuD050c
{
    public String getDocStatus(String saupNo) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String docStatus = null;
        saupNo = ComStr.replace(saupNo, "-", "");
        try {
            final String sql = " select PROCESS_ST from UM_EDOC_STATE  where BIZ_REG_NO='" + saupNo + "' " + " and   REG_DT = (select max(REG_DT) from UM_EDOC_STATE " + "                    where BIZ_REG_NO='" + saupNo + "' ) ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                docStatus = rs.getString(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuB010c .getDocStatus('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuB010c .getDocStatus('" + saupNo + "'):" + exc.toString());
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
        return docStatus;
    }
    
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
            sql = "SELECT BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM, TO_CHAR(COMMENCEMENT_DT, 'dd/MM/yyyy'), TO_CHAR(ESTABLISH_DT, 'dd/MM/yyyy'), BIZ_CLS, PRODUCT_CLS, CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL, EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECIAL_GOODS_YN, REG_VALID_DT, REG_DT, UPDATE_DT, REPR_BIZ_APPROVE_YN, MANAGER_ID,BID_ATTEND_QUALIFY_YN, REG_YN FROM UM_REC_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO=? ";
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
                ett.setRegYN(rs.getString(30));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserU010p.select_main('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserU010p.select_main('" + saupNo + "'):" + exc.toString());
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
            sql = "SELECT  b.CD_NM, a.STD_CLS_CD , a.STD_CLS_NO , TO_CHAR(a.STD_CLS_ISSUED_DT, 'dd/MM/yyyy'), TO_CHAR(a.STD_CLS_EXPIRY_DT, 'dd/MM/yyyy'), a.CONST_ABIL_EVAL_AMT, a.EVAL_STD_YEAR, a.MAST_STD_CLS_YN, TO_CHAR(a.STD_CLS_ISSUED_DT, 'dd/MM/yyyy'), TO_CHAR(a.STD_CLS_EXPIRY_DT, 'dd/MM/yyyy') FROM UM_REC_ENTER_STD_CLS a, syn_PUB_CODE b WHERE a.BIZ_REG_NO=? and b.CD_CLS='GU9' and a.STD_CLS_CD=b.CD  ORDER BY MAST_STD_CLS_YN DESC ";
            System.out.println(sql);
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
            sql = "SELECT REPR_IDENT_NO, REPR_NM, REPR_EMAIL, REPR_ISMAIN, REPR_MOBILE, REPR_CLS FROM UM_REC_REPR WHERE BIZ_REG_NO=? order by REPR_ISMAIN desc";
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
            sql = "SELECT IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX FROM UM_REC_BID_AGENT WHERE BIZ_REG_NO=?";
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
    
    public UM_GOE_ConiC060b[] select_MU1(final String saupNo) {
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
                ett6.setDirectPaper(rs.getString(9));
                ett6.setValidStartDate(rs.getString(10));
                ett6.setValidEndDate(rs.getString(11));
                ett6.setStandCode(rs.getString(12));
                ett6.setPublicOrg(rs.getString(13));
                ett6.setCertiName(rs.getString(14));
                vec.addElement(ett6);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuD050c.select_MU1('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuD050c.select_MU1('" + saupNo + "'):" + exc.toString());
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
            sql = "  SELECT a.BIZ_REG_NO, BIZ_NM, ZIP_CD, AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX,\t\t\t\t   \t\t    REPR_NM, REPR_IDENT_NO, DECODE(c.STATE_CLS,'07','Y') STATE_CLS\t\t\t\t\t\t\t\t\t\t\t     FROM  UM_REC_SUPPLIER_ENTER_MAST a, UM_REC_REPR b, UM_ENTER_STATE c\t\t\t\t\t\t\t\t\t\t     WHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.BIZ_REG_NO = c.BIZ_REG_NO(+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND c.STATE_CLS(+) = '07'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND b.REPR_ISMAIN   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t       FROM UM_REC_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t        WHERE BIZ_REG_NO  =?)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND BID_ATTEND_QUALIFY_YN = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     ORDER BY a.BIZ_REG_NO ASC\t\t\t";
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
        Log.debug("ettlist8 길이:" + ettlist8.length);
        return ettlist8;
    }
    
    public UM_GOE_ConiC060b[] select_MU2(final String saupNo) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_GOE_ConiC060b[] ettlist6 = (UM_GOE_ConiC060b[])null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = "SELECT CD_NM FROM SYN_PUB_CODE WHERE CD=?  ";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                UM_GOE_ConiC060b ett6 = null;
                ett6 = new UM_GOE_ConiC060b();
                ett6.setCertiName(rs.getString(1));
                vec.addElement(ett6);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuD050c.select_MU1('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuD050c.select_MU1('" + saupNo + "'):" + exc.toString());
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
}
