// 
// Decompiled by Procyon v0.5.30
// 

package beans;

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

public class UM_FOB_Forn071
{
    public UM_GOE_ConiC010b select_user(final String s) {
        Trx trx = null;
        Connection connection = null;
        UM_GOE_ConiC010b um_GOE_ConiC010b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            string = "select distinct 사업자등록번호,국적,상호명,영문상호명,                                개업일자, 법인설립일자,업무구분, 제조구분,\t대표업종코드_표준,                법인등록번호,기업구분1,기업구분2, 기업구분해당년도, 자본금,                 종업원수, 최근결산년월, 우편번호,지역코드, 주소, 나머지주소,                전화번호, FAX번호, 홈페이지, 특례해당여부,등록유효일자,                    등록일자, 갱신일자, 대표인증여부,처리자ID, 확인날자     FROM  사용_조달업체마스터       WHERE 사업자등록번호='" + s + "' ";
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
            string = "  SELECT distinct 면허코드,면허번호, 면허취득일자, 면허만료일자,           시공능력평가액,평가액기준년도,대표면허여부,                        TO_CHAR(등록일자, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(갱신일자, 'YYYY-MM-DD HH24:MI:SS')   FROM 사용_면허사항   \t                                   \t WHERE 사업자등록번호='" + s + "' order by 대표면허여부 desc  ";
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
            string = "  SELECT count(면허코드)            FROM  사용_면허사항                WHERE 사업자등록번호='" + s + "' ";
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
            string = "  SELECT DISTINCT  대표자주민번호, 대표자명, 대표자메일주소, 대표대표자여부     FROM  사용_대표자           WHERE 사업자등록번호='" + s + "' order by 대표대표자여부 desc ";
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
            string = "  SELECT  count(대표자주민번호)      FROM   사용_대표자               WHERE  사업자등록번호='" + s + "'";
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
            string = "  SELECT DISTINCT  일련번호,공장명,공장주소,공장나머지주소,공장전화번호,공장FAX번호,공장우편번호    FROM  사용_공장정보    WHERE 사업자등록번호='" + s + "'";
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
            string = " SELECT count(일련번호)  FROM  사용_공장정보\t     WHERE 사업자등록번호='" + s + "'";
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
            string = "  SELECT DISTINCT  주민등록번호,성명,부서,직책명, 전화번호,E_MAIL,휴대폰,FAX     FROM  사용_입찰대리인                                      WHERE 사업자등록번호='" + s + "'";
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
            string = "  SELECT count(주민등록번호)       FROM  사용_입찰대리인 \t\t   WHERE 사업자등록번호='" + s + "'";
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
            string = "  SELECT  DISTINCT  a.물품분류번호, a.형식승인번호, a.최근3년간_매출액, a.제조여부, a.대표물품여부, b.분류명, TO_CHAR(a.등록일자, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(a.갱신일자, 'YYYY-MM-DD HH24:MI:SS')   FROM 사용_조달품목 a, SYN_VIEW_물품분류매핑 b                                                           WHERE  a.물품분류번호=b.물품분류                                                                       AND    a.사업자등록번호='" + s + "' AND a.제조여부='N'  order by a.대표물품여부 desc ";
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
            string = "  SELECT  count(distinct a.물품분류번호)   FROM 사용_조달품목 a, SYN_VIEW_물품분류매핑 b    WHERE  a.물품분류번호=b.물품분류                 AND    a.사업자등록번호='" + s + "' AND a.제조여부='N'  order by a.대표물품여부 desc ";
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
            string = "  SELECT  DISTINCT  a.물품분류번호 , a.형식승인번호, a.형식승인기관, a.형식승인일,                      a.최근3년간_매출액 , a.제조여부, a.대표물품여부, b.분류명, TO_CHAR(a.등록일자, 'YYYY-MM-DD HH24:MI:SS'), TO_CHAR(a.갱신일자, 'YYYY-MM-DD HH24:MI:SS')    FROM 사용_조달품목 a, SYN_VIEW_물품분류매핑 b                                   WHERE a.물품분류번호=b.물품분류                                                and a.사업자등록번호='" + s + "' AND a.제조여부='Y' order by a.대표물품여부 desc";
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
            string = "  SELECT  count(distinct a.물품분류번호)    FROM 사용_조달품목 a, SYN_VIEW_물품분류매핑 b                   WHERE  a.물품분류번호=b.물품분류                               AND  a.사업자등록번호='" + s + "' AND a.제조여부='Y' order by a.대표물품여부 desc";
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
            string = "  SELECT  TO_CHAR(발생일자,'YYYY-MM-DD') 발생일자,  상태구분,   비고,      TO_CHAR(시작일자,'YYYY-MM-DD') 시작일자, TO_CHAR(종료일자,'YYYY-MM-DD') 종료일자     FROM  사용_업체상태\t                                     WHERE 사업자등록번호='" + s + "' order by 발생일자 asc";
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
            string = " select  count(*)                 FROM  사용_업체상태\t\t          WHERE 사업자등록번호='" + s + "'";
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
}
