// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import common.ComStr;
import entity.UM_RAE_ConuB010b;

public class UM_FOB_Forn080
{
    public UM_RAE_ConuB010b select_user(String replace, final String s) {
        Trx trx = null;
        Connection connection = null;
        UM_RAE_ConuB010b um_RAE_ConuB010b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String string = " select a.사업자등록번호, a.신청_변경구분, to_char(a.신청일자,'YYYY-MM-DD:hh24:mi:dd') 신청일자,         a.상호명, a.대표자명, a.우편번호,         a.주소, a.나머지주소, a.전화번호,         a.홈페이지, a.진행상태, a.처리사유,         a.승인지청, to_char(a.처리일자,'YYYY-MM-DD:hh24:mi:dd') 처리일자,         (SELECT 코드명 FROM SYN_공동코드 where 코드구분 ='GUJ' AND 코드=승인지청) 지청명  from 사용_전자문서상태 a, 사용_접수업체구분 b  where a.사업자등록번호 = '" + replace + "' " + "   and a.사업자등록번호 = b.사업자등록번호 " + "   and a.상호명 = '" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_RAE_ConuB010b = new UM_RAE_ConuB010b();
                um_RAE_ConuB010b.setSaupNo(executeQuery.getString(1));
                um_RAE_ConuB010b.setSinchungGubun(executeQuery.getString(2));
                um_RAE_ConuB010b.setSinchungDate(executeQuery.getString(3));
                um_RAE_ConuB010b.setSangho(executeQuery.getString(4));
                um_RAE_ConuB010b.setCeoName(executeQuery.getString(5));
                um_RAE_ConuB010b.setZipCode(executeQuery.getString(6));
                um_RAE_ConuB010b.setAddr(executeQuery.getString(7));
                um_RAE_ConuB010b.setRestAddr(executeQuery.getString(8));
                um_RAE_ConuB010b.setTel(executeQuery.getString(9));
                um_RAE_ConuB010b.setHomepage(executeQuery.getString(10));
                um_RAE_ConuB010b.setProcessState(executeQuery.getString(11));
                um_RAE_ConuB010b.setChuriReason(executeQuery.getString(12));
                um_RAE_ConuB010b.setApprovalOrgCode(executeQuery.getString(13));
                um_RAE_ConuB010b.setChuriDate(executeQuery.getString(14));
                um_RAE_ConuB010b.setApprovalOrgName(executeQuery.getString(15));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn080 .select_user('" + replace + "','" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn080 .select_user('" + replace + "','" + s + "'):" + ex2.toString());
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
        return um_RAE_ConuB010b;
    }
    
    public int Max_count(String replace, final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String string = " select count(*)  from 사용_전자문서상태 a, 사용_접수업체구분 b  where a.사업자등록번호 = '" + replace + "' " + "   and a.상호명 = '" + s + "' " + "   and a.사업자등록번호 = b.사업자등록번호 ";
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
            Log.debug("UM_FOB_Forn080 .Max_count('" + replace + "','" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn080 .Max_count('" + replace + "','" + s + "'):" + ex2.toString());
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
    
    public UM_RAE_ConuB010b select_ModiUser(String replace) {
        Trx trx = null;
        Connection connection = null;
        UM_RAE_ConuB010b um_RAE_ConuB010b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String string = " select a.사업자등록번호, a.신청_변경구분, to_char(a.신청일자,'YYYY-MM-DD:hh24:mi:dd') 신청일자,         a.상호명, a.대표자명, a.우편번호,         a.주소, a.나머지주소, a.전화번호,         a.홈페이지, a.진행상태, a.처리사유,         a.승인지청, to_char(a.처리일자,'YYYY-MM-DD:hh24:mi:dd') 처리일자,         (SELECT 코드명 FROM SYN_공동코드 where 코드구분 ='GUJ' AND 코드=승인지청) 지청명  from 사용_전자문서상태 a, 사용_접수업체구분 b  where a.사업자등록번호 = '" + replace + "' " + "   and a.사업자등록번호 = b.사업자등록번호 ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_RAE_ConuB010b = new UM_RAE_ConuB010b();
                um_RAE_ConuB010b.setSaupNo(executeQuery.getString(1));
                um_RAE_ConuB010b.setSinchungGubun(executeQuery.getString(2));
                um_RAE_ConuB010b.setSinchungDate(executeQuery.getString(3));
                um_RAE_ConuB010b.setSangho(executeQuery.getString(4));
                um_RAE_ConuB010b.setCeoName(executeQuery.getString(5));
                um_RAE_ConuB010b.setZipCode(executeQuery.getString(6));
                um_RAE_ConuB010b.setAddr(executeQuery.getString(7));
                um_RAE_ConuB010b.setRestAddr(executeQuery.getString(8));
                um_RAE_ConuB010b.setTel(executeQuery.getString(9));
                um_RAE_ConuB010b.setHomepage(executeQuery.getString(10));
                um_RAE_ConuB010b.setProcessState(executeQuery.getString(11));
                um_RAE_ConuB010b.setChuriReason(executeQuery.getString(12));
                um_RAE_ConuB010b.setApprovalOrgCode(executeQuery.getString(15));
                um_RAE_ConuB010b.setChuriDate(executeQuery.getString(16));
                um_RAE_ConuB010b.setApprovalOrgName(executeQuery.getString(17));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn080 .select_ModiUser('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn080 .select_ModiUser('" + replace + "'):" + ex2.toString());
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
        return um_RAE_ConuB010b;
    }
    
    public UM_RAE_ConuB010b select_user(String replace) {
        Trx trx = null;
        Connection connection = null;
        UM_RAE_ConuB010b um_RAE_ConuB010b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String string = " select 사업자등록번호, 신청_변경구분, 신청일자, 상호명, 대표자명, 우편번호, 주소, 나머지주소, 전화번호, 홈페이지, 진행상태, 처리사유, XML문서위치, 전송번호, 승인지청 from 사용_전자문서상태    where 사업자등록번호='" + replace + "' ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_RAE_ConuB010b = new UM_RAE_ConuB010b();
                um_RAE_ConuB010b.setSaupNo(executeQuery.getString(1));
                um_RAE_ConuB010b.setSinchungGubun(executeQuery.getString(2));
                um_RAE_ConuB010b.setSinchungDate(executeQuery.getString(3));
                um_RAE_ConuB010b.setSangho(executeQuery.getString(4));
                um_RAE_ConuB010b.setCeoName(executeQuery.getString(5));
                um_RAE_ConuB010b.setZipCode(executeQuery.getString(6));
                um_RAE_ConuB010b.setAddr(executeQuery.getString(7));
                um_RAE_ConuB010b.setRestAddr(executeQuery.getString(8));
                um_RAE_ConuB010b.setTel(executeQuery.getString(9));
                um_RAE_ConuB010b.setHomepage(executeQuery.getString(10));
                um_RAE_ConuB010b.setProcessState(executeQuery.getString(11));
                um_RAE_ConuB010b.setChuriReason(executeQuery.getString(12));
                um_RAE_ConuB010b.setXmlPosition(executeQuery.getString(13));
                um_RAE_ConuB010b.setTransNo(executeQuery.getString(14));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn080 .select_user('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn080 .select_user('" + replace + "'):" + ex2.toString());
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
        return um_RAE_ConuB010b;
    }
    
    public int Max_count(String replace) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            final String string = " select count(*) from 사용_전자문서상태  where 사업자등록번호='" + replace + "' ";
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
            Log.debug("UM_FOB_Forn080 .Max_count('" + replace + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn080 .Max_count('" + replace + "'):" + ex2.toString());
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
