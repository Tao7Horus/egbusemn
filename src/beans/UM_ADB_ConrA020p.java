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
import java.util.Vector;
import java.util.Date;
import java.text.SimpleDateFormat;
import entity.UM_RAE_ConuB010b;

public class UM_ADB_ConrA020p
{
    public UM_RAE_ConuB010b[] select_comlist(final int n, final int n2, String replace, final String s, final String s2, final String s3, final String s4, final String s5) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = new Date();
        date.setTime(System.currentTimeMillis() - 2851200000L);
        final String format = simpleDateFormat.format(date);
        Connection connection = null;
        Trx trx = null;
        final Vector vector = new Vector<UM_RAE_ConuB010b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        replace = ComStr.replace(replace, "-", "");
        String s6 = " SELECT BIZ_REG_NO, MOD_CLS, REG_DT, BIZ_NM, REPR_NM, ZIP_CD, ADDR,                   DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_DT, PROCESS_RSON, XML문서위치, TRANS_NO, MAIL, ROWNUM N  FROM    ( SELECT BIZ_REG_NO, MOD_CLS, REG_DT, BIZ_NM, REPR_NM, ZIP_CD, ADDR,                          DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_DT, PROCESS_RSON, XML문서위치, TRANS_NO, MAIL, ROWNUM N    FROM (SELECT a.BIZ_REG_NO, a.MOD_CLS, a.REG_DT, a.BIZ_NM, a.REPR_NM, a.ZIP_CD, a.ADDR,                \t\t a.DETAIL_ADDR, a.PHONE_NO, a.WEBSITE, a.PROCESS_DT, a.PROCESS_RSON, a.XML문서위치, a.TRANS_NO, a.MAIL        \t    FROM   UM_EDOC_STATE a, UM_REC_SUPPLIER_ENTER_MAST b  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t        WHERE a.BIZ_REG_NO = b.BIZ_REG_NO and a.MOD_CLS = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t       AND b.BID_ATTEND_QUALIFY_YN IS NULL\t\t\t\t\t\t\t\t\t\t\t\t  \t      AND a.PROCESS_DT = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t  AND a.PERMIT_BRANCH = '" + s5 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + " \t      AND TO_CHAR(a.PROCESS_DT, 'YYYY-MM-DD') < '" + format + "'\t\t\t\t\t\t\t\t\t\t";
        if (0 < replace.length()) {
            s6 = s6 + " AND a.BIZ_REG_NO like '%" + replace + "%'";
        }
        if (0 < s.length()) {
            s6 = s6 + " AND a.BIZ_NM like '%" + s + "%'";
        }
        if (0 < s2.length() && 0 < s3.length()) {
            s6 = s6 + " AND a.REG_DT>='" + s2 + "'" + " AND a.REG_DT<='" + s3 + "'" + "))";
        }
        final String string = s6 + " order by REG_DT desc ) WHERE ROWNUM <= (" + n + " * " + n2 + ") ) sub WHERE N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
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
                um_RAE_ConuB010b.setMail(executeQuery.getString(15));
                vector.addElement(um_RAE_ConuB010b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URV_UserD020p.select_userlist block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URV_UserD020p.select_userlist block Exception : " + ex2.toString());
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
    
    public int Max_count(String replace, final String s, final String s2, final String s3, final String s4, final String s5) {
        Connection connection = null;
        Trx trx = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = new Date();
        date.setTime(System.currentTimeMillis() - 2851200000L);
        final String format = simpleDateFormat.format(date);
        try {
            String s6 = "SELECT count(*) FROM   ( SELECT BIZ_REG_NO, MOD_CLS, REG_DT, BIZ_NM, REPR_NM, ZIP_CD, ADDR,                        DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_DT, PROCESS_RSON, XML문서위치, TRANS_NO, ROWNUM N            FROM (SELECT a.BIZ_REG_NO, a.MOD_CLS, a.REG_DT, a.BIZ_NM, a.REPR_NM, a.ZIP_CD, a.ADDR,                \t\t a.DETAIL_ADDR, a.PHONE_NO, a.WEBSITE, a.PROCESS_DT, a.PROCESS_RSON, a.XML문서위치, a.TRANS_NO              \t    FROM   UM_EDOC_STATE\ta, UM_REC_SUPPLIER_ENTER_MAST b  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t        WHERE a.BIZ_REG_NO = b.BIZ_REG_NO and a.MOD_CLS = '1'\t\t\t\t\t \t      AND b.BID_ATTEND_QUALIFY_YN IS NULL\t\t\t\t\t\t\t\t\t\t\t\t  \t      AND a.PROCESS_DT = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t  AND a.PERMIT_BRANCH = '" + s5 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + " \t      AND TO_CHAR(a.PROCESS_DT, 'YYYY-MM-DD') < '" + format + "'\t\t\t\t\t\t\t\t\t\t";
            if (0 < replace.length()) {
                s6 = s6 + " AND a.BIZ_REG_NO like '%" + replace + "%'";
            }
            if (0 < s.length()) {
                s6 = s6 + " AND a.BIZ_NM like '%" + s + "%'";
            }
            if (0 < s2.length() && 0 < s3.length()) {
                s6 = s6 + " AND a.REG_DT>='" + s2 + "'" + " AND a.REG_DT<='" + s3 + "'" + "))";
            }
            final String string = s6 + "))";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrA020p.Max_count block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrA020p.Max_count block Exception : " + ex2.toString());
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
    
    public String getJichung(final String s) {
        Connection connection = null;
        Trx trx = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            final String string2 = "SELECT PERMIT_BRANCH FROM UM_USER WHERE 사용자ID = '" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrA010p.Jichung block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrA010p.Jichung block Exception : " + ex2.toString());
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
        return string;
    }
}
