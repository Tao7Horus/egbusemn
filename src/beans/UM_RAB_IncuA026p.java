// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_URE_UserB020b;
import entity.UM_GOE_ConiC100b;
import java.util.Vector;
import entity.UM_GOE_ConiC040b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_ICE_InciA040b;

public class UM_RAB_IncuA026p
{
    public UM_ICE_InciA040b select_cop(final String s) {
        Trx trx = null;
        Connection connection = null;
        UM_ICE_InciA040b um_ICE_InciA040b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append("\tSELECT  A.상호명 업체명, A.사업자등록번호, B.대표자명, NULL 업체형태부호, A.자본금, \n");
            sb.append("\t        NULL 법인구분, TO_CHAR (A.법인설립일자, 'YYYY-MM-DD') 법인설립일, \n");
            sb.append("\t\t\tA.우편번호 우편코드, A.주소 ||' '|| A.나머지주소 본사주소, A.전화번호 본사전화번호,\tA.FAX번호 본사팩스번호, A.종업원수,\tA.홈페이지 \n");
            sb.append("\t  FROM\t사용_조달업체마스터 A, 사용_대표자 B \n");
            sb.append("  WHERE\tA.사업자등록번호 = B.사업자등록번호 \n");
            sb.append("    AND  B.대표대표자여부 = 'Y' \n");
            sb.append("    AND  A.사업자등록번호 = '" + s + "' \n");
            sb.append("\t UNION ALL \n");
            sb.append(" SELECT\t업체명, 사업자등록번호, 대표자명, 업체형태부호, 자본금, \n");
            sb.append("         법인구분,\tTO_CHAR (법인설립일, 'YYYY-MM-DD') 법인설립일, \n");
            sb.append(" \t\t우편코드,\t\t    본사주소,\t\t    본사전화번호,\t본사팩스번호,\t종업원수,\t홈페이지 \n");
            sb.append("\t  FROM\t사용_생산업체 \n");
            sb.append("\t WHERE\t사업자등록번호 = '" + s + "' \n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                um_ICE_InciA040b = new UM_ICE_InciA040b();
                um_ICE_InciA040b.setupcheNm(executeQuery.getString(1));
                um_ICE_InciA040b.setsaupNo(executeQuery.getString(2));
                um_ICE_InciA040b.setdaepyoNm(executeQuery.getString(3));
                um_ICE_InciA040b.setupcheGubun(executeQuery.getString(4));
                um_ICE_InciA040b.setmoney(executeQuery.getString(5));
                um_ICE_InciA040b.setbukinGubun(executeQuery.getString(6));
                um_ICE_InciA040b.setestablish(executeQuery.getString(7));
                um_ICE_InciA040b.setpostCode(executeQuery.getString(8));
                um_ICE_InciA040b.setbonsaAdd(executeQuery.getString(9));
                um_ICE_InciA040b.setbonsaTel(executeQuery.getString(10));
                um_ICE_InciA040b.setbonsaFax(executeQuery.getString(11));
                um_ICE_InciA040b.setworker(executeQuery.getString(12));
                um_ICE_InciA040b.sethomepage(executeQuery.getString(13));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_IncuA026p.select_cop('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA026p.select_cop('" + s + "'):" + ex2.toString());
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
        return um_ICE_InciA040b;
    }
    
    public UM_GOE_ConiC040b[] select_fact(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC040b>();
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(" SELECT 일련번호, 공장명, 공장주소, 공장나머지주소, 공장전화번호,        공장FAX번호, 공장우편번호, 공장임대여부,        to_char(공장임대시작일자, 'yyyy-mm-dd'), to_char(공장임대종료일자, 'yyyy-mm-dd')  FROM 사용_공장정보  WHERE 사업자등록번호 = ?  ORDER BY 일련번호 asc ");
            prepareStatement.setString(1, s);
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
            Log.debug("UM_RAB_IncuA026p.select_fact('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA026p.select_fact('" + s + "'):" + ex2.toString());
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
    
    public UM_GOE_ConiC100b[] select_bujeong(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC100b>();
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(" SELECT DISTINCT         aa.공공기관명,         TO_CHAR(aa.입력일자, 'YYYY-MM-DD') 게재일,         TO_CHAR(aa.제재연월일, 'YYYY-MM-DD') 제재시작일,         TO_CHAR(aa.만료연월일, 'YYYY-MM-DD') 제재만료일  FROM        (SELECT 사업자등록번호, 공공기관명, 담당자전화번호, 입력일자, 제재연월일, 만료연월일, 제재기간,               제재횟수, 정지구분, 재개구분, 해제구분        FROM 사용_부정당업자        WHERE (정지구분 = 'N' OR (정지구분 = 'Y' AND 재개구분 = 'Y'))          AND 해제구분 = 'N'          AND NVL(TO_CHAR(재개일자, 'yyyy-mm-dd'), '1800-01-01') <= TO_CHAR(sysdate, 'yyyy-mm-dd')) aa,       (SELECT a.사업자등록번호, a.제재횟수        FROM 사용_부정당대표자 a,             (SELECT 대표자주민번호 FROM 사용_대표자 WHERE 사업자등록번호 = ?) b        WHERE a.주민등록번호 = b.대표자주민번호) bb  WHERE aa.사업자등록번호 = bb.사업자등록번호    AND aa.제재횟수 = bb.제재횟수    AND aa.제재연월일 > sysdate - 365  ORDER BY 제재시작일 DESC ");
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiC100b um_GOE_ConiC100b = new UM_GOE_ConiC100b();
                um_GOE_ConiC100b.setPunishmentOrgCode(executeQuery.getString(1));
                um_GOE_ConiC100b.setToday(executeQuery.getString(2));
                um_GOE_ConiC100b.setPunishmentStart(executeQuery.getString(3));
                um_GOE_ConiC100b.setPunishmentEnd(executeQuery.getString(4));
                vector.addElement(um_GOE_ConiC100b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_IncuA026p.select_bujeong('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA026p.select_bujeong('" + s + "'):" + ex2.toString());
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
        final UM_GOE_ConiC100b[] array = new UM_GOE_ConiC100b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_URE_UserB020b[] select_user(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_URE_UserB020b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append("\tSELECT 담당자명, 담당자부서명, 담당자전화번호, 담당자이동전화번호, 담당자팩스번호, 담당자메일주소 \n");
            sb.append(" FROM 사용_사용자 \n");
            sb.append(" WHERE 마스터코드 = '" + s + "' \n");
            sb.append("   AND 사용자구분 = 'c' \n");
            sb.append("   AND 승인여부 = '2' \n");
            sb.append(" ORDER BY 대표수신자여부 DESC \n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_URE_UserB020b um_URE_UserB020b = new UM_URE_UserB020b();
                um_URE_UserB020b.setUserName(executeQuery.getString(1));
                um_URE_UserB020b.setUserPost(executeQuery.getString(2));
                um_URE_UserB020b.setUserTelephone(executeQuery.getString(3));
                um_URE_UserB020b.setUserMobilePhone(executeQuery.getString(4));
                um_URE_UserB020b.setUserFax(executeQuery.getString(5));
                um_URE_UserB020b.setUserEmail(executeQuery.getString(6));
                vector.addElement(um_URE_UserB020b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_IncuA026p.select_user('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA026p.select_user('" + s + "'):" + ex2.toString());
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
        final UM_URE_UserB020b[] array = new UM_URE_UserB020b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int user_count(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String string = " SELECT COUNT(*) FROM 사용_사용자  WHERE 사용자구분 = 'c'    AND 승인여부 = '2'    AND 마스터코드 = '" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_IncuA026p.user_count('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA026p.user_count('" + s + "'):" + ex2.toString());
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
