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
import entity.UM_RAE_ConuB010t;

public class UM_ADB_ConrA010t
{
    public UM_RAE_ConuB010t[] select_comlist(final int n, final int n2, String s, String s2, String s3, String s4, String replace, final String s5, final String s6, final String s7, final String s8, final String s9) {
        Connection connection = null;
        Trx trx = null;
        final Vector vector = new Vector<UM_RAE_ConuB010t>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        replace = ComStr.replace(replace, "-", "");
        s = ((s == null) ? "" : s);
        s2 = ((s2 == null) ? "" : s2);
        s3 = ((s3 == null) ? "" : s3);
        s4 = ((s4 == null) ? "" : s4);
        String s10 = " SELECT BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, ZIP_CD, ADDR,\t\t\t\t\t\t\t\t\t\t\t        DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_ST, PROCESS_RSON, XML_DOC_LOCATE, TRANS_NO, EMAIL, ROWNUM N  FROM\t\t\t\t\t\t        ( SELECT BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, ZIP_CD, ADDR,\t\t\t\t\t\t\t\t\t                 DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_ST, PROCESS_RSON, XML_DOC_LOCATE, TRANS_NO, EMAIL, ROWNUM N\t\t\t\t\t            FROM (SELECT a.BIZ_REG_NO, a.MOD_CLS, a.REG_DT, a.COM_NM, a.REPR_NM, a.ZIP_CD, a.ADDR,\t\t\t\t     \t\t               a.DETAIL_ADDR, a.PHONE_NO, a.WEBSITE, a.PROCESS_ST, a.PROCESS_RSON, a.XML_DOC_LOCATE, a.TRANS_NO, a.EMAIL\t\t\t                  FROM UM_EDOC_STATE a, UM_REC_SUPPLIER_ENTER_MAST b                  \t\t\t\t\t\t\t\t\t\t                   WHERE a.BIZ_REG_NO = b.BIZ_REG_NO(+) and a.MOD_CLS = '1'     \t\t\t\t\t\t\t\t\t \t\t\t\t\t   AND b.BID_ATTEND_QUALIFY_YN IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        if (s.length() == 0 && s2.length() == 0 && s3.length() == 0 && s4.length() == 0) {
            s10 += " \t                   AND a.PROCESS_ST NOT IN('1','3','4')      \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        }
        if (!s9.equals("XX")) {
            s10 = s10 + " \t\t             AND a.PERMIT_BRANCH = '" + s9 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        }
        if (0 < replace.length()) {
            s10 = s10 + " AND a.BIZ_REG_NO like '%" + replace + "%'";
        }
        if (0 < s5.length()) {
            s10 = s10 + " AND a.COM_NM like '%" + s5 + "%'";
        }
        if (0 < s6.length() && 0 < s7.length()) {
            s10 = s10 + " AND a.REG_DT>='" + s6 + "'" + " AND a.REG_DT<='" + s7 + "'";
        }
        if (s.length() > 0 || s2.length() > 0 || s3.length() > 0 || s4.length() > 0) {
            final String string = s10 + " AND ( a.PROCESS_ST IN (";
            String s11;
            if (0 < s.length()) {
                s11 = string + s;
            }
            else {
                s11 = string + "7";
            }
            String s12;
            if (0 < s2.length()) {
                s12 = s11 + "," + s2;
            }
            else {
                s12 = s11 + ",7";
            }
            String s13;
            if (0 < s3.length()) {
                s13 = s12 + "," + s3;
            }
            else {
                s13 = s12 + ",7";
            }
            String s14;
            if (0 < s4.length()) {
                s14 = s13 + "," + s4;
            }
            else {
                s14 = s13 + ",7";
            }
            s10 = s14 + "))";
        }
        final String string2 = s10 + " order by a.REG_DT desc ) WHERE ROWNUM <= (" + n + " * " + n2 + ") ) sub WHERE N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_ConuB010t um_RAE_ConuB010t = new UM_RAE_ConuB010t();
                um_RAE_ConuB010t.setSaupNo(executeQuery.getString(1));
                um_RAE_ConuB010t.setSinchungGubun(executeQuery.getString(2));
                um_RAE_ConuB010t.setSinchungDate(executeQuery.getString(3));
                um_RAE_ConuB010t.setSangho(executeQuery.getString(4));
                um_RAE_ConuB010t.setCeoName(executeQuery.getString(5));
                um_RAE_ConuB010t.setZipCode(executeQuery.getString(6));
                um_RAE_ConuB010t.setAddr(executeQuery.getString(7));
                um_RAE_ConuB010t.setRestAddr(executeQuery.getString(8));
                um_RAE_ConuB010t.setTel(executeQuery.getString(9));
                um_RAE_ConuB010t.setHomepage(executeQuery.getString(10));
                um_RAE_ConuB010t.setProcessState(executeQuery.getString(11));
                um_RAE_ConuB010t.setChuriReason(executeQuery.getString(12));
                um_RAE_ConuB010t.setXmlPosition(executeQuery.getString(13));
                um_RAE_ConuB010t.setTransNo(executeQuery.getString(14));
                um_RAE_ConuB010t.setMail(executeQuery.getString(15));
                vector.addElement(um_RAE_ConuB010t);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrA010t.java's select_userlist block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrA010t.select_userlist block Exception : " + ex2.toString());
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
            final UM_RAE_ConuB010t[] array = new UM_RAE_ConuB010t[vector.size()];
            vector.copyInto(array);
            return array;
        }
        return null;
    }
    
    public UM_RAE_ConuB010t[] getResultList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        Connection connection = null;
        Trx trx = null;
        ResultSet set = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_RAE_ConuB010t>();
        final int n3 = (n - 1) * n2 + 1;
        final int n4 = n * n2;
        try {
            String s7;
            if ("D".equals(s5) || "C".equals(s5)) {
                String s6 = "select * from (select t1.BIZ_REG_NO, t1.COM_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t1.REPR_NM, t1.PROCESS_ST, t1.ADDR, t1.PHONE_NO, ROWNUM N from UM_EDOC_STATE t1 where PROCESS_ST in ('3', '4') ";
                if (s != null && s.length() != 0) {
                    s6 = s6 + " and t1.BIZ_REG_NO like '%" + s + "%'";
                }
                if (s2 != null && s2.length() != 0) {
                    s6 = s6 + " and t1.BIZ_NM like '%" + s2 + "%'";
                }
                if ("D".equals(s5)) {
                    s6 += " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(s5)) {
                    s6 += " and  t1.PROCESS_ST ='4' ";
                }
                if (s3 != null && s3.length() != 0 && s4 != null && s4.length() != 0) {
                    s6 = s6 + " and t1.REG_DT between to_date('" + s3 + "', 'dd/mm/yyyy') and to_date('" + s4 + "', 'dd/mm/yyyy')";
                }
                else if (s3 != null && s3.length() != 0 && (s4 == null || s4.length() == 0)) {
                    s6 = s6 + " and t1.REG_DT > to_date('" + s3 + "', 'dd/mm/yyyy')";
                }
                else if ((s3 == null || s4.length() == 0) && s4 != null && s4.length() != 0) {
                    s6 = s6 + " and t1.REG_DT < to_date('" + s4 + "', 'dd/mm/yyyy')";
                }
                s7 = s6 + " order by t1.REG_DT ASC" + ") where N between " + n3 + " and " + n4;
                System.out.println(s7);
                Log.debug("11111111111111" + s7);
            }
            else {
                String s8 = "select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, REG_YN, RECV_NO, ADDR, PHONE_NO , ROWNUM N from (  select t1.BIZ_REG_NO, t1.BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.REG_YN, t1.RECV_NO,t1.ADDR, t1.PHONE_NO from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2 where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y' ";
                if (s != null && s.length() != 0) {
                    s8 = s8 + " and t1.BIZ_REG_NO like '%" + s + "%'";
                }
                if (s2 != null && s2.length() != 0) {
                    s8 = s8 + " and t1.BIZ_NM like '%" + s2 + "%'";
                }
                if (s5 == null || s5.length() == 0) {
                    s8 += " and  t1.REG_YN ='N' ";
                }
                else if (s5 != null && s5.length() != 0 && !"A".equals(s5)) {
                    s8 = s8 + " and  t1.REG_YN ='" + s5 + "' ";
                }
                if (s3 != null && s3.length() != 0 && s4 != null && s4.length() != 0) {
                    s8 = s8 + " and t1.REG_DT between to_date('" + s3 + "', 'dd/mm/yyyy') and to_date('" + s4 + "', 'dd/mm/yyyy')";
                }
                else if (s3 != null && s3.length() != 0 && (s4 == null || s4.length() == 0)) {
                    s8 = s8 + " and t1.REG_DT > to_date('" + s3 + "', 'dd/mm/yyyy')";
                }
                else if ((s3 == null || s4.length() == 0) && s4 != null && s4.length() != 0) {
                    s8 = s8 + " and t1.REG_DT < to_date('" + s4 + "', 'dd/mm/yyyy')";
                }
                s7 = s8 + " order by t1.REG_DT ASC" + ")) t3 where t3.N between " + n3 + " and " + n4;
                Log.debug("222222222222" + s7);
            }
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s7);
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (set.next()) {
                final UM_RAE_ConuB010t um_RAE_ConuB010t = new UM_RAE_ConuB010t();
                um_RAE_ConuB010t.setSaupNo(set.getString(1));
                um_RAE_ConuB010t.setSangho(set.getString(2));
                um_RAE_ConuB010t.setSinchungDate(set.getString(3));
                um_RAE_ConuB010t.setCeoName(set.getString(4));
                um_RAE_ConuB010t.setProcessState(set.getString(5));
                if (!"D".equals(s5) && !"C".equals(s5)) {
                    um_RAE_ConuB010t.setApprovalOrgCode(set.getString(6));
                }
                um_RAE_ConuB010t.setAddr(set.getString(7));
                um_RAE_ConuB010t.setTel(set.getString(8));
                vector.addElement(um_RAE_ConuB010t);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrA010t.Max_count block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrA010t.Max_count block Exception : " + ex2.toString());
        }
        finally {
            if (set != null) {
                try {
                    set.close();
                }
                catch (Exception ex4) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex5) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                }
            }
        }
        if (0 < vector.size()) {
            final UM_RAE_ConuB010t[] array = new UM_RAE_ConuB010t[vector.size()];
            vector.copyInto(array);
            return array;
        }
        return null;
    }
    
    public int max_count(final String s, final String s2, final String s3, final String s4, final String s5) {
        Connection connection = null;
        Trx trx = null;
        ResultSet set = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            String s6;
            if ("D".equals(s5) || "C".equals(s5)) {
                s6 = "select count(*) from UM_EDOC_STATE t1 where t1.PROCESS_ST in ('3', '4') ";
                if (s != null && s.length() != 0) {
                    s6 = s6 + " and t1.BIZ_REG_NO like '%" + s + "%'";
                }
                if (s2 != null && s2.length() != 0) {
                    s6 = s6 + " and t1.BIZ_NM like '%" + s2 + "%'";
                }
                if ("D".equals(s5)) {
                    s6 += " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(s5)) {
                    s6 += " and  t1.PROCESS_ST ='4' ";
                }
                if (s3 != null && s3.length() != 0 && s4 != null && s4.length() != 0) {
                    s6 = s6 + " and t1.REG_DT between to_date('" + s3 + "', 'dd/mm/yyyy') and to_date('" + s4 + "', 'dd/mm/yyyy')";
                }
                else if (s3 != null && s3.length() != 0 && (s4 == null || s4.length() == 0)) {
                    s6 = s6 + " and t1.REG_DT > to_date('" + s3 + "', 'dd/mm/yyyy')";
                }
                else if ((s3 == null || s4.length() == 0) && s4 != null && s4.length() != 0) {
                    s6 = s6 + " and t1.REG_DT < to_date('" + s4 + "', 'dd/mm/yyyy')";
                }
            }
            else {
                s6 = "select count(*)  from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2 where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y' ";
                if (s != null && s.length() != 0) {
                    s6 = s6 + " and t1.BIZ_REG_NO like '%" + s + "%'";
                }
                if (s2 != null && s2.length() != 0) {
                    s6 = s6 + " and t1.BIZ_NM like '%" + s2 + "%'";
                }
                if (s5 == null || s5.length() == 0) {
                    s6 += " and  t1.REG_YN ='N' ";
                }
                else if (s5 != null && s5.length() != 0 && !"A".equals(s5)) {
                    s6 = s6 + " and  t1.REG_YN ='" + s5 + "' ";
                }
                if (s3 != null && s3.length() != 0 && s4 != null && s4.length() != 0) {
                    s6 = s6 + " and t1.REG_DT between to_date('" + s3 + "', 'dd/mm/yyyy') and to_date('" + s4 + "', 'dd/mm/yyyy')";
                }
                else if (s3 != null && s3.length() != 0 && (s4 == null || s4.length() == 0)) {
                    s6 = s6 + " and t1.REG_DT > to_date('" + s3 + "', 'dd/mm/yyyy')";
                }
                else if ((s3 == null || s4.length() == 0) && s4 != null && s4.length() != 0) {
                    s6 = s6 + " and t1.REG_DT < to_date('" + s4 + "', 'dd/mm/yyyy')";
                }
            }
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s6);
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (set.next()) {
                int1 = set.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrA010t.Max_count block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrA010t.Max_count block Exception : " + ex2.toString());
        }
        finally {
            if (set != null) {
                try {
                    set.close();
                }
                catch (Exception ex4) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex5) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                }
            }
        }
        return int1;
    }
    
    public int Max_count(String s, String s2, String s3, String s4, String replace, final String s5, final String s6, final String s7, final String s8, final String s9) {
        Connection connection = null;
        Trx trx = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        s = ((s == null) ? "" : s);
        s2 = ((s2 == null) ? "" : s2);
        s3 = ((s3 == null) ? "" : s3);
        s4 = ((s4 == null) ? "" : s4);
        try {
            String s10 = "SELECT count(*) FROM  ( SELECT BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, ZIP_CD, ADDR,          DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_DT, PROCESS_RSON, XML_DOC_LOCATE, TRANS_NO             FROM (SELECT a.BIZ_REG_NO, a.MOD_CLS, a.REG_DT, a.COM_NM, a.REPR_NM, a.ZIP_CD, a.ADDR,      \t\t               a.DETAIL_ADDR, a.PHONE_NO, a.WEBSITE, a.PROCESS_DT, a.PROCESS_RSON, a.XML_DOC_LOCATE, a.TRANS_NO, a.EMAIL \t                  FROM UM_EDOC_STATE a, UM_REC_SUPPLIER_ENTER_MAST b                    WHERE a.BIZ_REG_NO = b.BIZ_REG_NO(+) and a.MOD_CLS = '1'  \t\t\t\t\t   AND b.BID_ATTEND_QUALIFY_YN IS NULL ";
            if (s.length() == 0 && s2.length() == 0 && s3.length() == 0 && s4.length() == 0) {
                s10 += " \t         AND a.PROCESS_ST NOT IN('1','3', '4')\t\t\t\t\t\t\t\t\t\t\t\t\t";
            }
            if (!s9.equals("XX")) {
                s10 = s10 + " \t\t     AND a.PERMIT_BRANCH='" + s9 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
            }
            if (0 < replace.length()) {
                s10 = s10 + " AND a.BIZ_REG_NO like '%" + replace + "%'";
            }
            if (0 < s5.length()) {
                s10 = s10 + " AND a.COM_NM like '%" + s5 + "%'";
            }
            if (0 < s6.length() && 0 < s7.length()) {
                s10 = s10 + " AND a.REG_DT>='" + s6 + "'" + " AND a.REG_DT<='" + s7 + "'";
            }
            if (s.length() > 0 || s2.length() > 0 || s3.length() > 0 || s4.length() > 0) {
                final String string = s10 + " AND ( a.PROCESS_ST IN (";
                String s11;
                if (0 < s.length()) {
                    s11 = string + s;
                }
                else {
                    s11 = string + "7";
                }
                String s12;
                if (0 < s2.length()) {
                    s12 = s11 + "," + s2;
                }
                else {
                    s12 = s11 + ",7";
                }
                String s13;
                if (0 < s3.length()) {
                    s13 = s12 + "," + s3;
                }
                else {
                    s13 = s12 + ",7";
                }
                String s14;
                if (0 < s4.length()) {
                    s14 = s13 + "," + s4;
                }
                else {
                    s14 = s13 + ",7";
                }
                s10 = s14 + "))";
            }
            final String string2 = s10 + "))";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrA010t.Max_count block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrA010t.Max_count block Exception : " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex4) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex5) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {
                    ex3.printStackTrace();
                }
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
            final String string2 = "SELECT PERMIT_BRANCH FROM UM_USER WHERE USER_ID = '" + s + "'";
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
            Log.debug("UM_ADB_ConrA010t.Jichung block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrA010t.Jichung block Exception : " + ex2.toString());
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
