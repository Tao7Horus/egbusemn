// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_GOE_ConiC060b;
import java.util.Vector;
import entity.UM_GOE_ConiC050b;
import entity.UM_GOE_ConiC010b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import common.ComStr;

public class UM_RAV_ConuK010c
{
    public int Max_count_Saup_total(String replace, final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String s2 = "\tSELECT COUNT(DISTINCT  a.사업자등록번호)                                                \tFROM 사용_조달업체마스터 a , 사용_대표자 b\t\t\t\t\t\t\t\t\t\t\t\t  \tWHERE a.사업자등록번호=b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t  \tAND  a.사업자등록번호 = ?\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND  b.대표자주민번호 = ? ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, replace);
            prepareStatement.setString(2, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Saup_total('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Saup_total('" + replace + "'):" + ex2.toString());
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
    
    public int Max_count_Saup_total(String replace) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String s = "\tSELECT COUNT(DISTINCT  a.사업자등록번호)                                                \tFROM 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \tWHERE  a.사업자등록번호 = ?\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t  ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s);
            prepareStatement.setString(1, replace);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Saup_total('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Saup_total('" + replace + "'):" + ex2.toString());
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
    
    public int Max_count_Saup(String replace, final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String s2 = "\tSELECT COUNT(DISTINCT  a.사업자등록번호)                                                \tFROM 사용_조달업체마스터 a , 사용_대표자 b\t\t\t\t\t\t\t\t\t\t\t\t  \tWHERE a.사업자등록번호=b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t  \tAND a.테스트여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \tAND a.입찰참가자격여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \tAND a.사업자등록번호 NOT LIKE 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \tAND (TO_CHAR(a.등록일자,'yyyymmdd') < '20070427' OR a.등록일자 IS NULL)\t  \tAND (TO_CHAR(a.갱신일자,'yyyymmdd') < '20070427' OR a.갱신일자 IS NULL)\t  \tAND  a.사업자등록번호 = ?\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND  b.대표자주민번호 = ? ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, replace);
            prepareStatement.setString(2, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAV_ConuK010c .Max_count_saup('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAV_ConuK010c .Max_count_saup('" + replace + "'):" + ex2.toString());
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
    
    public int Max_count_Saup(String replace) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String s = "\tSELECT COUNT(DISTINCT  a.사업자등록번호)                                                \tFROM 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \tWHERE a.테스트여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \tAND a.입찰참가자격여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \tAND a.사업자등록번호 NOT LIKE 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \tAND (TO_CHAR(a.등록일자,'yyyymmdd') < '20070427' OR a.등록일자 IS NULL)\t  \tAND (TO_CHAR(a.갱신일자,'yyyymmdd') < '20070427' OR a.갱신일자 IS NULL)\t  \tAND  a.사업자등록번호 = ?\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t  ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s);
            prepareStatement.setString(1, replace);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAV_ConuK010c .Max_count_saup('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAV_ConuK010c .Max_count_saup('" + replace + "'):" + ex2.toString());
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
    
    public int Max_count_Daeri(String replace, final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String s2 = "  SELECT   COUNT(DISTINCT  a.사업자등록번호)\t\t\t\t\t\t\t\t\t\t\t\t     FROM 사용_조달업체마스터 a , 사용_입찰대리인 b , 사용_대표자 c\t\t\t\t     WHERE a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t     AND b.사업자등록번호 = c.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.테스트여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.입찰참가자격여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.사업자등록번호 NOT LIKE 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND b.입찰대리인확인여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND  a.사업자등록번호 = ?\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND  c.대표자주민번호 = ? ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, replace);
            prepareStatement.setString(2, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Daeri('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Daeri('" + replace + "'):" + ex2.toString());
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
    
    public int Max_count_Daeri(String replace) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String s = "  SELECT   COUNT(DISTINCT  a.사업자등록번호)\t\t\t\t\t\t\t\t\t\t\t\t     FROM 사용_조달업체마스터 a , 사용_입찰대리인 b\t\t\t\t\t\t\t\t\t\t     WHERE a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t     AND a.테스트여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.입찰참가자격여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.사업자등록번호 NOT LIKE 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND b.입찰대리인확인여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.사업자등록번호 = ?\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s);
            prepareStatement.setString(1, replace);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Daeri('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Daeri('" + replace + "'):" + ex2.toString());
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
    
    public int Max_count_Jejo(String replace, final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String s2 = "  SELECT   COUNT(DISTINCT  a.사업자등록번호)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM 사용_조달업체마스터 a , 사용_대표자 b,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  (SELECT 사업자등록번호,직접생산증명서류 FROM 사용_조달품목 WHERE  제조여부 = 'Y' ) c    WHERE a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND b.사업자등록번호 = c.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND a.테스트여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND a.입찰참가자격여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND a.사업자등록번호 NOT LIKE 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND c.직접생산증명서류 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND  a.사업자등록번호 = ?\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t      AND  b.대표자주민번호 = ? ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, replace);
            prepareStatement.setString(2, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Jejo('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Jejo('" + replace + "'):" + ex2.toString());
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
    
    public int Max_count_Jejo(String replace) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String s = "  SELECT   COUNT(DISTINCT  a.사업자등록번호)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM 사용_조달업체마스터 a ,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  (SELECT 사업자등록번호,직접생산증명서류 FROM 사용_조달품목 WHERE  제조여부 = 'Y' ) c    WHERE a.사업자등록번호 = c.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND a.테스트여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND a.입찰참가자격여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND a.사업자등록번호 NOT LIKE 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND c.직접생산증명서류 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND  a.사업자등록번호 = ?\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t    ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s);
            prepareStatement.setString(1, replace);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Jejo('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Jejo('" + replace + "'):" + ex2.toString());
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
    
    public int Max_count_Jejo1(String replace, final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String s2 = "  SELECT   COUNT(DISTINCT  a.사업자등록번호)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   FROM 사용_조달업체마스터 a , 사용_대표자 b, 사용_조달품목 c\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t       WHERE a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t       AND b.사업자등록번호 = c.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND a.테스트여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND a.입찰참가자격여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND a.사업자등록번호 NOT LIKE 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t       AND SUBSTR(물품분류번호, 1, 2)  IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')     AND  a.사업자등록번호 = ?\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND  b.대표자주민번호 = ? ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, replace);
            prepareStatement.setString(2, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Jejo1('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Jejo1('" + replace + "'):" + ex2.toString());
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
    
    public int Max_count_Jejo1(String replace) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String s = "  SELECT   COUNT(DISTINCT  a.사업자등록번호)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   FROM 사용_조달업체마스터 a , 사용_조달품목 c\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   WHERE a.사업자등록번호 = c.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t       AND a.테스트여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND a.입찰참가자격여부 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   AND a.사업자등록번호 NOT LIKE 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t       AND SUBSTR(물품분류번호, 1, 2)  IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')     AND  a.사업자등록번호 = ?\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s);
            prepareStatement.setString(1, replace);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Jejo1('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAV_ConuK010c .Max_count_Jejo1('" + replace + "'):" + ex2.toString());
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
    
    public UM_GOE_ConiC010b select_main(final String s) {
        Trx trx = null;
        Connection connection = null;
        UM_GOE_ConiC010b um_GOE_ConiC010b = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("SELECT 사업자등록번호, 국적, 상호명, 영문상호명, TO_CHAR(개업일자, 'YYYY-MM-DD'), TO_CHAR(법인설립일자, 'YYYY-MM-DD'), 업무구분, 제조구분, 법인등록번호, 기업구분1, 기업구분2, 기업구분해당년도, 자본금, 종업원수, 최근결산년월, 우편번호, 지역코드, 주소, 나머지주소, 전화번호, FAX번호, 홈페이지, 특례해당여부, 등록유효일자, 등록일자, 갱신일자, 대표인증여부, 처리자ID,입찰참가자격여부 FROM 사용_조달업체마스터 WHERE 사업자등록번호=? ");
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                um_GOE_ConiC010b = new UM_GOE_ConiC010b();
                um_GOE_ConiC010b.setSaupNo(executeQuery.getString(1));
                um_GOE_ConiC010b.setNationality(executeQuery.getString(2));
                um_GOE_ConiC010b.setSangho(executeQuery.getString(3));
                um_GOE_ConiC010b.setESangho(executeQuery.getString(4));
                um_GOE_ConiC010b.setOpenDate(executeQuery.getString(5));
                um_GOE_ConiC010b.setBubinOpenDate(executeQuery.getString(6));
                um_GOE_ConiC010b.setJobGubun(executeQuery.getString(7));
                um_GOE_ConiC010b.setMakeGubun(executeQuery.getString(8));
                um_GOE_ConiC010b.setBubinNo(executeQuery.getString(9));
                um_GOE_ConiC010b.setComGubun1(executeQuery.getString(10));
                um_GOE_ConiC010b.setComGubun2(executeQuery.getString(11));
                um_GOE_ConiC010b.setComGubunYear(executeQuery.getString(12));
                um_GOE_ConiC010b.setJabon(executeQuery.getLong(13));
                um_GOE_ConiC010b.setEmployeeNo(executeQuery.getInt(14));
                um_GOE_ConiC010b.setAccountDate(executeQuery.getString(15));
                um_GOE_ConiC010b.setZipCode(executeQuery.getString(16));
                um_GOE_ConiC010b.setLocalCode(executeQuery.getString(17));
                um_GOE_ConiC010b.setAddr(executeQuery.getString(18));
                um_GOE_ConiC010b.setRestAddr(executeQuery.getString(19));
                um_GOE_ConiC010b.setTel(executeQuery.getString(20));
                um_GOE_ConiC010b.setFax(executeQuery.getString(21));
                um_GOE_ConiC010b.setHomepage(executeQuery.getString(22));
                um_GOE_ConiC010b.setExceptYN(executeQuery.getString(23));
                um_GOE_ConiC010b.setRegistOkDate(executeQuery.getString(24));
                um_GOE_ConiC010b.setRegistDate(executeQuery.getString(25));
                um_GOE_ConiC010b.setRenewDate(executeQuery.getString(26));
                um_GOE_ConiC010b.setDpOkYN(executeQuery.getString(27));
                um_GOE_ConiC010b.setChurijaId(executeQuery.getString(28));
                um_GOE_ConiC010b.setQualification(executeQuery.getString(29));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_UserU010p.select_main('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_UserU010p.select_main('" + s + "'):" + ex2.toString());
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
    
    public UM_GOE_ConiC050b[] select_ID(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC050b>();
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("SELECT 주민등록번호, 성명, 부서, 직책명, 전화번호,\t\t\t\t\tE_MAIL, 휴대폰, FAX, 입찰대리인확인여부\t\t\t\t\t\t\t\t\tFROM 사용_입찰대리인 WHERE 사업자등록번호=?\t\t\t\t\t\tAND (입찰대리인확인여부 is null or 입찰대리인확인여부 = 'N')\t\t");
            prepareStatement.setString(1, s);
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
            Log.debug("UM_URB_UserU010p.select_ID('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_UserU010p.select_ID('" + s + "'):" + ex2.toString());
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
    
    public UM_GOE_ConiC060b[] select_MU(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC060b>();
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(" SELECT /*+ ordered use_nl(a b ) */ DISTINCT a.물품분류번호 , a.형식승인번호, a.형식승인기관,                                               a.형식승인일, a.최근3년간_매출액, a.제조여부, a.대표물품여부, b.분류명\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t FROM 사용_조달품목 a, SYN_VIEW_물품분류매핑 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t WHERE a.물품분류번호=b.물품분류\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t AND a.사업자등록번호=?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t AND SUBSTR(a.물품분류번호, 1, 2)  IN ('70','71','72','73','76','77','78','80','81','82','83','84','85','86','90','91','92','93','94','95')\t\t ORDER BY a.대표물품여부 DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            prepareStatement.setString(1, s);
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
                vector.addElement(um_GOE_ConiC060b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_UserU010p.select_MU('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_UserU010p.select_MU('" + s + "'):" + ex2.toString());
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
    
    public UM_GOE_ConiC060b[] select_MU1(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC060b>();
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("  SELECT /*+ ordered use_nl(a b ) */ DISTINCT a.물품분류번호 , a.형식승인번호, a.형식승인기관,         a.형식승인일, a.최근3년간_매출액, a.제조여부, a.대표물품여부, b.분류명, a.직접생산증명서류,\t\t\t  TO_CHAR(a.유효기간시작일자,'yyyy-mm-dd'), TO_CHAR(a.유효기간종료일자,'yyyy-mm-dd'),\t\t\t\t  a.표준산업분류코드, a.발급기관, a.증서명\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM 사용_조달품목 a, SYN_VIEW_물품분류매핑 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE a.물품분류번호=b.물품분류\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND a.사업자등록번호=?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND a.제조여부 = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND a.직접생산증명서류 IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ORDER BY a.대표물품여부 DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            prepareStatement.setString(1, s);
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
                um_GOE_ConiC060b.setDirectPaper(executeQuery.getString(9));
                um_GOE_ConiC060b.setValidStartDate(executeQuery.getString(10));
                um_GOE_ConiC060b.setValidEndDate(executeQuery.getString(11));
                um_GOE_ConiC060b.setStandCode(executeQuery.getString(12));
                um_GOE_ConiC060b.setPublicOrg(executeQuery.getString(13));
                um_GOE_ConiC060b.setCertiName(executeQuery.getString(14));
                vector.addElement(um_GOE_ConiC060b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAV_ConuK010c.select_MU1('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAV_ConuK010c.select_MU1('" + s + "'):" + ex2.toString());
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
}
