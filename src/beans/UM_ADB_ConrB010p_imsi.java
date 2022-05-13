// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import common.ComStr;
import java.util.Vector;
import entity.UM_RAE_ConuB010b;

public class UM_ADB_ConrB010p_imsi
{
    public UM_RAE_ConuB010b[] select_comlist(final int n, final int n2, String s, String s2, String s3, String replace, final String s4, final String s5, final String s6, final String s7) {
        final Vector vector = new Vector<UM_RAE_ConuB010b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        Connection connection = null;
        Trx trx = null;
        replace = ComStr.replace(replace, "-", "");
        s = ((s == null) ? "" : s);
        s2 = ((s2 == null) ? "" : s2);
        s3 = ((s3 == null) ? "" : s3);
        String s8 = " SELECT 사업자등록번호, 신청_변경구분, 신청일자, 상호명, 대표자명, 우편번호, 주소,         나머지주소, 전화번호, 홈페이지, 진행상태, 처리사유, XML문서위치, 전송번호, ROWNUM N  FROM  ( SELECT 사업자등록번호, 신청_변경구분, 신청일자, 상호명, 대표자명, 우편번호, 주소,                           나머지주소, 전화번호, 홈페이지, 진행상태, 처리사유, XML문서위치, 전송번호, ROWNUM N            FROM (SELECT 사업자등록번호, 신청_변경구분, 신청일자, 상호명, 대표자명, 우편번호, 주소,                  \t\t 나머지주소, 전화번호, 홈페이지, 진행상태, 처리사유, XML문서위치, 전송번호               \t    FROM   사용_전자문서상태                     WHERE 신청_변경구분 = '2'      \t    AND 진행상태 IN('1')      ";
        if (0 < replace.length()) {
            s8 = s8 + " AND 사업자등록번호 like '%" + replace + "%'";
        }
        if (0 < s4.length()) {
            s8 = s8 + " AND 상호명 like '%" + s4 + "%'";
        }
        if (0 < s5.length() && 0 < s6.length()) {
            s8 = s8 + " AND 신청일자>='" + s5 + "'" + " AND 신청일자<='" + s6 + "'";
        }
        if (s.length() > 0 || s2.length() > 0 || s3.length() > 0) {
            final String string = s8 + " AND ( 진행상태 IN (";
            String s9;
            if (0 < s.length()) {
                s9 = string + s;
            }
            else {
                s9 = string + "7";
            }
            String s10;
            if (0 < s2.length()) {
                s10 = s9 + "," + s2;
            }
            else {
                s10 = s9 + ",7";
            }
            String s11;
            if (0 < s3.length()) {
                s11 = s10 + "," + s3;
            }
            else {
                s11 = s10 + ",7";
            }
            s8 = s11 + "))";
        }
        final String string2 = s8 + " order by 신청일자 desc ) WHERE ROWNUM <= (" + n + " * " + n2 + ") ) sub WHERE N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_ConuB010b um_RAE_ConuB010b = new UM_RAE_ConuB010b();
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
                vector.addElement(um_RAE_ConuB010b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrB010p_imsi.select_userlist block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrB010p_imsi.select_userlist block Exception : " + ex2.toString());
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
        if (0 < vector.size()) {
            final UM_RAE_ConuB010b[] array = new UM_RAE_ConuB010b[vector.size()];
            vector.copyInto(array);
            return array;
        }
        return null;
    }
    
    public int Max_count(String s, String s2, String s3, String replace, final String s4, final String s5, final String s6, final String s7) {
        Connection connection = null;
        Trx trx = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        s = ((s == null) ? "" : s);
        s2 = ((s2 == null) ? "" : s2);
        s3 = ((s3 == null) ? "" : s3);
        try {
            String s8 = "SELECT count(*) FROM   ( SELECT 사업자등록번호, 신청_변경구분, 신청일자, 상호명, 대표자명, 우편번호, 주소,                              나머지주소, 전화번호, 홈페이지, 진행상태, 처리사유, XML문서위치, 전송번호, ROWNUM N                  FROM (SELECT 사업자등록번호, 신청_변경구분, 신청일자, 상호명, 대표자명, 우편번호, 주소,                      \t\t 나머지주소, 전화번호, 홈페이지, 진행상태, 처리사유, XML문서위치, 전송번호                    \t    FROM   사용_전자문서상태                       WHERE 신청_변경구분 = '2'      \t    AND 진행상태 IN('1')      ";
            if (0 < replace.length()) {
                s8 = s8 + " AND 사업자등록번호 like '%" + replace + "%'";
            }
            if (0 < s4.length()) {
                s8 = s8 + " AND 상호명 like '%" + s4 + "%'";
            }
            if (0 < s5.length() && 0 < s6.length()) {
                s8 = s8 + " AND 신청일자>='" + s5 + "'" + " AND 신청일자<='" + s6 + "'";
            }
            if (s.length() > 0 || s2.length() > 0 || s3.length() > 0) {
                final String string = s8 + " AND ( 진행상태 IN (";
                String s9;
                if (0 < s.length()) {
                    s9 = string + s;
                }
                else {
                    s9 = string + "7";
                }
                String s10;
                if (0 < s2.length()) {
                    s10 = s9 + "," + s2;
                }
                else {
                    s10 = s9 + ",7";
                }
                String s11;
                if (0 < s3.length()) {
                    s11 = s10 + "," + s3;
                }
                else {
                    s11 = s10 + ",7";
                }
                s8 = s11 + "))";
            }
            final String string2 = s8 + "))";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrB010p_imsi.Max_count block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrB010p_imsi.Max_count block Exception : " + ex2.toString());
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
