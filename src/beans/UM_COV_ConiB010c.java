// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_GOE_ConiC110b;
import entity.UM_GOE_ConiC070b;
import entity.UM_GOE_ConiC060b;
import entity.UM_GOE_ConiC050b;
import entity.UM_GOE_ConiC040b;
import entity.UM_GOE_ConiC030b;
import java.util.Vector;
import entity.UM_GOE_ConiC020b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_GOE_ConiC010b;

public class UM_COV_ConiB010c
{
    public UM_GOE_ConiC010b select_user(final String s) {
        Trx trx = null;
        Connection connection = null;
        UM_GOE_ConiC010b um_GOE_ConiC010b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            string = "select distinct BIZ_REG_NO,NATIONALITY,BIZ_NM,BIZ_EN_NM,\t\t\t\t\t\t\t\t\t            COMMENCEMENT_DT, ESTABLISH_DT,BIZ_CLS, PRODUCT_CLS,\tMAST_INDUSTRY_CD_STD,\t\t\t            CORP_REG_NO,BIZ_CLS1,BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL,\t\t\t            EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD,AREA_CD, ADDR, DETAIL_ADDR,\t\t\t            PHONE_NO, FAX, WEBSITE, SPECIAL_GOODS_YN,REG_VALID_DT,\t\t\t\t\t            REG_DT, UPDATE_DT, REPR_BIZ_APPROVE_YN,MANAGER_ID, CONFIRM_DT, HEADQUARTER_YN\t\t\t  FROM  UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE BIZ_REG_NO='" + s + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_GOE_ConiC010b = new UM_GOE_ConiC010b();
                um_GOE_ConiC010b.setSaupNo(executeQuery.getString(1));
                um_GOE_ConiC010b.setNationality(executeQuery.getString(2));
                um_GOE_ConiC010b.setSangho(executeQuery.getString(3));
                um_GOE_ConiC010b.setESangho(executeQuery.getString(4));
                um_GOE_ConiC010b.setOpenDate(executeQuery.getString(5));
                um_GOE_ConiC010b.setBubinOpenDate(executeQuery.getString(6));
                um_GOE_ConiC010b.setJobGubun(executeQuery.getString(7));
                um_GOE_ConiC010b.setMakeGubun(executeQuery.getString(8));
                um_GOE_ConiC010b.setDpUpjongCode(executeQuery.getString(9));
                um_GOE_ConiC010b.setBubinNo(executeQuery.getString(10));
                um_GOE_ConiC010b.setComGubun1(executeQuery.getString(11));
                um_GOE_ConiC010b.setComGubun2(executeQuery.getString(12));
                um_GOE_ConiC010b.setComGubunYear(executeQuery.getString(13));
                um_GOE_ConiC010b.setJabon(executeQuery.getLong(14));
                um_GOE_ConiC010b.setEmployeeNo(executeQuery.getInt(15));
                um_GOE_ConiC010b.setAccountDate(executeQuery.getString(16));
                um_GOE_ConiC010b.setZipCode(executeQuery.getString(17));
                um_GOE_ConiC010b.setLocalCode(executeQuery.getString(18));
                um_GOE_ConiC010b.setAddr(executeQuery.getString(19));
                um_GOE_ConiC010b.setRestAddr(executeQuery.getString(20));
                um_GOE_ConiC010b.setTel(executeQuery.getString(21));
                um_GOE_ConiC010b.setFax(executeQuery.getString(22));
                um_GOE_ConiC010b.setHomepage(executeQuery.getString(23));
                um_GOE_ConiC010b.setExceptYN(executeQuery.getString(24));
                um_GOE_ConiC010b.setRegistOkDate(executeQuery.getString(25));
                um_GOE_ConiC010b.setRegistDate(executeQuery.getString(26));
                um_GOE_ConiC010b.setRenewDate(executeQuery.getString(27));
                um_GOE_ConiC010b.setDpOkYN(executeQuery.getString(28));
                um_GOE_ConiC010b.setChurijaId(executeQuery.getString(29));
                um_GOE_ConiC010b.setconfirmDate(executeQuery.getString(30));
                um_GOE_ConiC010b.setBubinYN(executeQuery.getString(31));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.select_user() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.select_user() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return um_GOE_ConiC010b;
    }
    
    public UM_GOE_ConiC020b[] select_MS(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC020b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            string = "  SELECT distinct LICENSE_CD,LICENSE_NO, LICENSE_ISSUED_DT, LICENSE_EXPIRY_DT,           CONST_ABIL_EVAL_AMT,EVAL_STD_YEAR,MAST_LICENSE_YN,                        TO_CHAR(REG_DT, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(UPDATE_DT, 'YYYY-MM-DD HH24:MI:SS')   FROM UM_LICENSE_FACTS   \t                                   \t WHERE BIZ_REG_NO='" + s + "' order by MAST_LICENSE_YN desc  ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiC020b um_GOE_ConiC020b = new UM_GOE_ConiC020b();
                um_GOE_ConiC020b.setLicenseCode(executeQuery.getString(1));
                um_GOE_ConiC020b.setLicenseNo(executeQuery.getString(2));
                um_GOE_ConiC020b.setLicenseBeginDate(executeQuery.getString(3));
                um_GOE_ConiC020b.setLicenseEndDate(executeQuery.getString(4));
                um_GOE_ConiC020b.setSigongAccount(executeQuery.getLong(5));
                um_GOE_ConiC020b.setPeonggaYear(executeQuery.getString(6));
                um_GOE_ConiC020b.setDpLicenseYN(executeQuery.getString(7));
                um_GOE_ConiC020b.setInsertDate(executeQuery.getString(8));
                um_GOE_ConiC020b.setUpdateDate(executeQuery.getString(9));
                vector.addElement(um_GOE_ConiC020b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.select_MS() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.select_MS() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiC020b[] array = new UM_GOE_ConiC020b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        String string = "";
        try {
            string = "  SELECT count(LICENSE_CD)            FROM  UM_LICENSE_FACTS                WHERE BIZ_REG_NO='" + s + "' ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.Max_count() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.Max_count() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public UM_GOE_ConiC030b[] select_DP(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC030b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            string = "  SELECT DISTINCT  REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE, REPR_CLS     FROM  UM_REPR           WHERE BIZ_REG_NO='" + s + "' order by MAST_REPR_YN desc ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiC030b um_GOE_ConiC030b = new UM_GOE_ConiC030b();
                um_GOE_ConiC030b.setCeoJuminNo(executeQuery.getString(1));
                um_GOE_ConiC030b.setCeoName(executeQuery.getString(2));
                um_GOE_ConiC030b.setCeoMail(executeQuery.getString(3));
                um_GOE_ConiC030b.setCeoYN(executeQuery.getString(4));
                um_GOE_ConiC030b.setCeohphone(executeQuery.getString(5));
                um_GOE_ConiC030b.setCeoyuhyung(executeQuery.getString(6));
                vector.addElement(um_GOE_ConiC030b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.select_DP() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.select_DP() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiC030b[] array = new UM_GOE_ConiC030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count3(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        int int1 = 0;
        try {
            string = "  SELECT  count(REPR_IDENT_NO)      FROM   UM_REPR               WHERE  BIZ_REG_NO='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.Max_count3() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.Max_count3() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public UM_GOE_ConiC040b[] select_GJ(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC040b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            string = "  SELECT DISTINCT  SERIAL_NO,FACTORY_NM,ADDR,ADDR2,FACTORY_PHONE,FACTORY_FAX,FACTORY_ZIP_CD,          FACTORY_RENT_YN, to_char(FACTORY_RENT_START_DT, 'yyyy-mm-dd'), to_char(FACTORY_RENT_END_DT, 'yyyy-mm-dd')   FROM   UM_FACTORY_INFO    WHERE  BIZ_REG_NO='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiC040b um_GOE_ConiC040b = new UM_GOE_ConiC040b();
                um_GOE_ConiC040b.setIlrunNo(executeQuery.getInt(1));
                um_GOE_ConiC040b.setFactoryName(executeQuery.getString(2));
                um_GOE_ConiC040b.setFactoryAddr(executeQuery.getString(3));
                um_GOE_ConiC040b.setFactoryRestAddr(executeQuery.getString(4));
                um_GOE_ConiC040b.setFactoryTel(executeQuery.getString(5));
                um_GOE_ConiC040b.setFactoryFax(executeQuery.getString(6));
                um_GOE_ConiC040b.setFactoryZipCode(executeQuery.getString(7));
                um_GOE_ConiC040b.setFactoryleaseYN(executeQuery.getString(8));
                um_GOE_ConiC040b.setFactoryleaseSdate(executeQuery.getString(9));
                um_GOE_ConiC040b.setFactoryleaseEdate(executeQuery.getString(10));
                vector.addElement(um_GOE_ConiC040b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.select_GJ() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.select_GJ() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiC040b[] array = new UM_GOE_ConiC040b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count4(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        int int1 = 0;
        try {
            string = " SELECT count(SERIAL_NO)  FROM  UM_FACTORY_INFO\t     WHERE BIZ_REG_NO='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.Max_count4() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.Max_count4() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public UM_GOE_ConiC050b[] select_ID(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC050b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            string = "  SELECT DISTINCT  IDENT_NO,NM,DEPART,POSITION, PHONE_NO,EMAIL,MOBILE,FAX, BIDDING_AGENT_YN     FROM  UM_BID_AGENT                                      WHERE BIZ_REG_NO='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiC050b um_GOE_ConiC050b = new UM_GOE_ConiC050b();
                um_GOE_ConiC050b.setJuminNo(executeQuery.getString(1));
                um_GOE_ConiC050b.setName(executeQuery.getString(2));
                um_GOE_ConiC050b.setJobPart(executeQuery.getString(3));
                um_GOE_ConiC050b.setDutyName(executeQuery.getString(4));
                um_GOE_ConiC050b.setTel(executeQuery.getString(5));
                um_GOE_ConiC050b.setMail(executeQuery.getString(6));
                um_GOE_ConiC050b.setHandphone(executeQuery.getString(7));
                um_GOE_ConiC050b.setFax(executeQuery.getString(8));
                um_GOE_ConiC050b.setIpchalYN(executeQuery.getString(9));
                vector.addElement(um_GOE_ConiC050b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.select_ID() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.select_ID() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiC050b[] array = new UM_GOE_ConiC050b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count5(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        String string = "";
        try {
            string = "  SELECT count(IDENT_NO)       FROM  UM_BID_AGENT \t\t   WHERE BIZ_REG_NO='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.Max_count5() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.Max_count5() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public UM_GOE_ConiC060b[] select_SUP(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC060b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            string = "   SELECT /*+ ordered  use_nl(a b) */\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t               DISTINCT  a.GOOD_CLS_NO, a.PERMIT_NO, a.INCOME_3YEARS, a.DIRECT_PRODUCTION_YN, a.MAST_GOODS_YN, b.분류명,\t\t\t               TO_CHAR(a.REG_DT, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(a.UPDATE_DT, 'YYYY-MM-DD HH24:MI:SS')\t\t  FROM UM_SUPPLIER_ENTER_ITEMS a, SYN_VIEW_물품분류매핑 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE  a.GOOD_CLS_NO=b.물품분류\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND    a.BIZ_REG_NO='" + s + "' AND a.DIRECT_PRODUCTION_YN='N'  order by a.MAST_GOODS_YN desc\t\t\t\t\t\t\t\t\t\t";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiC060b um_GOE_ConiC060b = new UM_GOE_ConiC060b();
                um_GOE_ConiC060b.setGoodsNo(executeQuery.getString(1));
                um_GOE_ConiC060b.setFormNo(executeQuery.getString(2));
                um_GOE_ConiC060b.setThreeSale(executeQuery.getLong(3));
                um_GOE_ConiC060b.setMakeYN(executeQuery.getString(4));
                um_GOE_ConiC060b.setDpGoodsYN(executeQuery.getString(5));
                um_GOE_ConiC060b.setGoodsName(executeQuery.getString(6));
                um_GOE_ConiC060b.setInsertDate(executeQuery.getString(7));
                um_GOE_ConiC060b.setUpdateDate(executeQuery.getString(8));
                vector.addElement(um_GOE_ConiC060b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.select_SUP() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.select_SUP() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiC060b[] array = new UM_GOE_ConiC060b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_countSUP(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        String string = "";
        try {
            string = "  SELECT  /*+ ordered  use_nl(a b) */   count(distinct a.GOOD_CLS_NO)\t\t\t\t\t\t\t\t\t\t  FROM UM_SUPPLIER_ENTER_ITEMS a, SYN_VIEW_물품분류매핑 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE  a.GOOD_CLS_NO=b.물품분류\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND    a.BIZ_REG_NO='" + s + "' AND a.DIRECT_PRODUCTION_YN='N'  order by a.MAST_GOODS_YN desc\t\t\t";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.Max_countSUP() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.Max_countSUP() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public UM_GOE_ConiC060b[] select_JP(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC060b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            string = "  SELECT\t/*+ ordered  use_nl(a b) */\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t               DISTINCT  a.GOOD_CLS_NO , a.PERMIT_NO, a.PERMIT_INSTITU, a.PERMIT_DT,\t\t\t\t\t\t\t\t\t\t\t\t               a.INCOME_3YEARS , a.DIRECT_PRODUCTION_YN, a.MAST_GOODS_YN, b.분류명,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t               TO_CHAR(a.REG_DT, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(a.UPDATE_DT, 'YYYY-MM-DD HH24:MI:SS'),\t\t               a.DIRECT_PRODUCTION_DOC , TO_CHAR(a.AVAIL_PERIOD_START_DT, 'YYYY-MM-DD HH24:MI:SS'),\t\t\t\t\t\t\t\t\t\t               TO_CHAR(a.AVAIL_PERIOD_END_DT, 'YYYY-MM-DD HH24:MI:SS'), a.INDUSTRY_CLS_CD, a.ISSUE_INSTITU, a.DOC_NM\t\t\t   FROM UM_SUPPLIER_ENTER_ITEMS a, SYN_VIEW_물품분류매핑 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   WHERE a.GOOD_CLS_NO=b.물품분류\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   and a.BIZ_REG_NO='" + s + "' AND a.DIRECT_PRODUCTION_YN='Y' order by a.MAST_GOODS_YN desc\t\t\t\t\t\t\t\t\t\t\t";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiC060b um_GOE_ConiC060b = new UM_GOE_ConiC060b();
                um_GOE_ConiC060b.setGoodsNo(executeQuery.getString(1));
                um_GOE_ConiC060b.setFormNo(executeQuery.getString(2));
                um_GOE_ConiC060b.setFormOrg(executeQuery.getString(3));
                um_GOE_ConiC060b.setFormDate(executeQuery.getString(4));
                um_GOE_ConiC060b.setThreeSale(executeQuery.getLong(5));
                um_GOE_ConiC060b.setMakeYN(executeQuery.getString(6));
                um_GOE_ConiC060b.setDpGoodsYN(executeQuery.getString(7));
                um_GOE_ConiC060b.setGoodsName(executeQuery.getString(8));
                um_GOE_ConiC060b.setInsertDate(executeQuery.getString(9));
                um_GOE_ConiC060b.setUpdateDate(executeQuery.getString(10));
                um_GOE_ConiC060b.setDirectPaper(executeQuery.getString(11));
                um_GOE_ConiC060b.setValidStartDate(executeQuery.getString(12));
                um_GOE_ConiC060b.setValidEndDate(executeQuery.getString(13));
                um_GOE_ConiC060b.setStandCode(executeQuery.getString(14));
                um_GOE_ConiC060b.setPublicOrg(executeQuery.getString(15));
                um_GOE_ConiC060b.setCertiName(executeQuery.getString(16));
                vector.addElement(um_GOE_ConiC060b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.select_JP() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.select_JP() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiC060b[] array = new UM_GOE_ConiC060b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count6(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        String string = "";
        try {
            string = "  SELECT  /*+ ordered  use_nl(a b) */  count(distinct a.GOOD_CLS_NO)\t\t\t\t\t\t\t\t\t   FROM UM_SUPPLIER_ENTER_ITEMS a, SYN_VIEW_물품분류매핑 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   WHERE  a.GOOD_CLS_NO=b.물품분류\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND  a.BIZ_REG_NO='" + s + "' AND a.DIRECT_PRODUCTION_YN='Y' order by a.MAST_GOODS_YN desc\t\t";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.Max_count6() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.Max_count6() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public UM_GOE_ConiC070b[] select_state(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC070b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            string = "   SELECT  TO_CHAR(RAISED_DT,'YYYY-MM-DD') RAISED_DT,  STATE_CLS,   REMARK,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   TO_CHAR(START_DT,'YYYY-MM-DD') START_DT, TO_CHAR(END_DT,'YYYY-MM-DD') END_DT, MANAGER_ID\t\t   FROM  UM_ENTER_STATE\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   WHERE BIZ_REG_NO='" + s + "' order by RAISED_DT asc\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiC070b um_GOE_ConiC070b = new UM_GOE_ConiC070b();
                um_GOE_ConiC070b.setHappenDate(executeQuery.getString(1));
                um_GOE_ConiC070b.setStateGubun(executeQuery.getString(2));
                um_GOE_ConiC070b.setNote(executeQuery.getString(3));
                um_GOE_ConiC070b.setStartDate(executeQuery.getString(4));
                um_GOE_ConiC070b.setEndDate(executeQuery.getString(5));
                um_GOE_ConiC070b.setchurijaId(executeQuery.getString(6));
                vector.addElement(um_GOE_ConiC070b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.select_state() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.select_state() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiC070b[] array = new UM_GOE_ConiC070b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_statecount(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        String string = "";
        try {
            string = " select  count(*)                 FROM  UM_ENTER_STATE\t\t          WHERE BIZ_REG_NO='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_COV_ConiB010c.Max_statecount() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_COV_ConiB010c.Max_statecount() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public UM_GOE_ConiC110b[] select_BR(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC110b>();
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("  SELECT a.BIZ_REG_NO, BIZ_NM, ZIP_CD, AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX,\t\t\t\t   \t\t    REPR_NM, REPR_IDENT_NO, DECODE(c.STATE_CLS,'07','Y') STATE_CLS\t\t\t\t\t\t\t\t\t\t\t     FROM  UM_SUPPLIER_ENTER_MAST a, UM_REPR b, UM_ENTER_STATE c\t\t\t\t\t\t\t\t\t\t\t\t\t     WHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.BIZ_REG_NO = c.BIZ_REG_NO(+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND c.STATE_CLS(+) = '07'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND b.MAST_REPR_YN = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t       FROM UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t        WHERE BIZ_REG_NO  =?)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND HEADQUARTER_YN = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     ORDER BY a.BIZ_REG_NO ASC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiC110b um_GOE_ConiC110b = new UM_GOE_ConiC110b();
                um_GOE_ConiC110b.setSaupNo(executeQuery.getString(1));
                um_GOE_ConiC110b.setSangho(executeQuery.getString(2));
                um_GOE_ConiC110b.setZipCode(executeQuery.getString(3));
                um_GOE_ConiC110b.setLocalCode(executeQuery.getString(4));
                um_GOE_ConiC110b.setAddr(executeQuery.getString(5));
                um_GOE_ConiC110b.setRestAddr(executeQuery.getString(6));
                um_GOE_ConiC110b.setTel(executeQuery.getString(7));
                um_GOE_ConiC110b.setFax(executeQuery.getString(8));
                um_GOE_ConiC110b.setCeoName(executeQuery.getString(9));
                um_GOE_ConiC110b.setCeoJuminNo(executeQuery.getString(10));
                um_GOE_ConiC110b.setCancelYN(executeQuery.getString(11));
                vector.addElement(um_GOE_ConiC110b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_UserU010p.select_BR('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_UserU010p.select_BR('" + s + "'):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_GOE_ConiC110b[] array = new UM_GOE_ConiC110b[vector.size()];
        vector.copyInto(array);
        return array;
    }
}
