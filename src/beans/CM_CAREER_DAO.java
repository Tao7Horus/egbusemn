// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Vector;
import entity.CM_CAREER_ENTITY;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Trx;
import common.Log;

public class CM_CAREER_DAO
{
    public int max_count(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        Connection connection = null;
        Trx trx = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            String s9 = "select count(*)  from SYN_PUB_CODE  where CD_CLS = 'GZ8' ";
            if (s != null && !"".equals(s) && s.length() != 0) {
                s9 = s9 + " and lower(CD_NM) like '%" + s.toLowerCase().trim() + "%'";
            }
            if (s2 != null && !"".equals(s2) && s2.length() != 0) {
                s9 = s9 + " and lower(CD) like '%" + s2.toLowerCase().trim() + "%'";
            }
            if (s3 != null && !"".equals(s3) && s3.length() != 0) {
                s9 = s9 + " and lower(SUMMARY) like '%" + s3.toLowerCase().trim() + "%'";
            }
            if (s8 != null && s8.length() != 0) {
                s9 = s9 + " and length(CD) = " + s8;
            }
            if (s4 != null && !"".equals(s4) && !"".equals(s4)) {
                s9 = s9 + " and INPUT_DT > to_date('" + s4 + " 00:00:00', 'DD/MM/YYYY HH24:MI:SS') ";
            }
            if (s5 != null && !"".equals(s5) && !"".equals(s5)) {
                s9 = s9 + " and INPUT_DT < to_date('" + s5 + " 23:59:59', 'DD/MM/YYYY HH24:MI:SS') ";
            }
            if (s6 != null && !"".equals(s6) && !"".equals(s6)) {
                s9 = s9 + " and UPDATE_DT > to_date('" + s6 + " 00:00:00', 'DD/MM/YYYY HH24:MI:SS') ";
            }
            if (s7 != null && !"".equals(s7) && !"".equals(s7)) {
                s9 = s9 + " and UPDATE_DT < to_date('" + s7 + " 23:59:59', 'DD/MM/YYYY HH24:MI:SS') ";
            }
            Log.debug("SQL: " + s9);
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s9);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("CM_CONNECT_DAO.Max_count block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("CM_CONNECT_DAO.Max_count block Exception : " + ex2.toString());
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
    
    public CM_CAREER_ENTITY[] getResultList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        Connection connection = null;
        Trx trx = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<CM_CAREER_ENTITY>();
        final int n3 = (n - 1) * n2 + 1;
        final int n4 = n * n2;
        try {
            String s9 = " select CD, CD_NM, SUMMARY, INPUT_DT , UPDATE_DT  from ( select CD, CD_NM, SUMMARY, INPUT_DT , UPDATE_DT, ROWNUM N from SYN_PUB_CODE  where CD_CLS = 'GZ8' ";
            if (s != null && s.length() != 0) {
                s9 = s9 + " and lower(CD_NM) like '%" + s.toLowerCase().trim() + "%'";
            }
            if (s2 != null && s2.length() != 0) {
                s9 = s9 + " and lower(CD) like '%" + s2.toLowerCase().trim() + "%'";
            }
            if (s3 != null && s3.length() != 0) {
                s9 = s9 + " and lower(SUMMARY) like '%" + s3.toLowerCase().trim() + "%'";
            }
            if (s8 != null && s8.length() != 0) {
                s9 = s9 + " and length(CD) = " + s8;
            }
            if (s4 != null && !"".equals(s4)) {
                s9 = s9 + " and INPUT_DT > to_date('" + s4 + " 00:00:00', 'DD/MM/YYYY HH24:MI:SS') ";
            }
            if (s5 != null && !"".equals(s5)) {
                s9 = s9 + " and INPUT_DT < to_date('" + s5 + " 23:59:59', 'DD/MM/YYYY HH24:MI:SS') ";
            }
            if (s6 != null && !"".equals(s6)) {
                s9 = s9 + " and UPDATE_DT > to_date('" + s6 + " 00:00:00', 'DD/MM/YYYY HH24:MI:SS') ";
            }
            if (s7 != null && !"".equals(s7)) {
                s9 = s9 + " and UPDATE_DT < to_date('" + s7 + " 23:59:59', 'DD/MM/YYYY HH24:MI:SS') ";
            }
            final String string = s9 + " order by CD asc " + " ) t3 where t3.N between " + n3 + " and " + n4;
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final CM_CAREER_ENTITY cm_CAREER_ENTITY = new CM_CAREER_ENTITY();
                cm_CAREER_ENTITY.setCd(executeQuery.getString(1));
                cm_CAREER_ENTITY.setCdNm(executeQuery.getString(2));
                cm_CAREER_ENTITY.setSummary(executeQuery.getString(3));
                if (executeQuery.getString(4) != null) {
                    cm_CAREER_ENTITY.setInputDt(executeQuery.getDate(4));
                }
                if (executeQuery.getString(5) != null) {
                    cm_CAREER_ENTITY.setUpdateDt(executeQuery.getDate(5));
                }
                vector.addElement(cm_CAREER_ENTITY);
            }
        }
        catch (SQLException ex) {
            Log.debug("CM_CONNECT_DAO.LIST block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("CM_CONNECT_DAO.LIST block Exception : " + ex2.toString());
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
        if (0 < vector.size()) {
            final CM_CAREER_ENTITY[] array = new CM_CAREER_ENTITY[vector.size()];
            vector.copyInto(array);
            return array;
        }
        return null;
    }
    
    public String insert(final String s, final String s2, final String s3, final HttpServletRequest httpServletRequest, final Connection connection) throws Exception {
        String s4 = "";
        PreparedStatement prepareStatement = null;
        final int max_count = this.max_count("", s, "", "", "", "", "", "");
        Log.debug("cd: " + s);
        Log.debug("scount: " + max_count);
        if (max_count == 0) {
            String string = " insert into SYN_PUB_CODE (CD, CD_CLS, CD_NM, SUMMARY, INPUT_DT) \n";
            if (!"".equals(s) && !"".equals(s2)) {
                string = string + " values ('" + s + "', 'GZ8', '" + s2 + "', '" + s3 + "', sysdate" + ") \n";
            }
            try {
                prepareStatement = connection.prepareStatement(string);
                prepareStatement.executeUpdate();
                s4 = "Thêm mới thành công.";
                return s4;
            }
            finally {
                try {
                    if (prepareStatement != null) {
                        prepareStatement.close();
                    }
                }
                catch (Exception ex) {}
            }
        }
        s4 = "Đã tồn tại Mã ngành nghề này.";
        return s4;
    }
    
    public void update(final String s, final String s2, final String s3, final HttpServletRequest httpServletRequest, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String string = " update SYN_PUB_CODE set CD_NM ='" + s2 + "', SUMMARY = '" + s3 + "', UPDATE_DT = sysdate where CD = '" + s + "'";
        try {
            prepareStatement = connection.prepareStatement(string);
            prepareStatement.executeUpdate();
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void delete(final String s, final HttpServletRequest httpServletRequest, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        String string = "";
        if (!"".equals(s) && s != null) {
            string = string + " delete from SYN_PUB_CODE where CD = '" + s + "' and CD_CLS = 'GZ8' \n";
        }
        try {
            prepareStatement = connection.prepareStatement(string);
            prepareStatement.executeUpdate();
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex) {}
        }
    }
}
