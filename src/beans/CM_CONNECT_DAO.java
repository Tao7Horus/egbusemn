// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import javax.servlet.http.HttpServletRequest;
import java.util.Vector;
import entity.CM_CONNECT_ENTITY;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class CM_CONNECT_DAO
{
    public int max_count(final String s, final String s2, final String s3) {
        Connection connection = null;
        Trx trx = null;
        ResultSet set = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            String s4 = "select count(*)  from CM_CONNECT_TB  where 1=1 ";
            if (s != null && s.length() != 0) {
                s4 = s4 + " and lower(CONNECT_NAME) = '" + s.toLowerCase().trim() + "'";
            }
            if (s2 != null && s2.length() != 0) {
                s4 = s4 + " and lower(CONNECT_URL) like '%" + s2.toLowerCase().trim() + "%'";
            }
            if (!"".equals(s3) && s3 != null) {
                s4 = s4 + " and  lower(CONNECT_DESCRIPTION) like '%" + s3.toLowerCase().trim() + "%' ";
            }
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s4);
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (set.next()) {
                int1 = set.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("CM_CONNECT_DAO.Max_count block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("CM_CONNECT_DAO.Max_count block Exception : " + ex2.toString());
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
    
    public CM_CONNECT_ENTITY[] getResultList(final int n, final int n2, final String s, final String s2, final String s3) {
        Connection connection = null;
        Trx trx = null;
        ResultSet set = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<CM_CONNECT_ENTITY>();
        final int n3 = (n - 1) * n2 + 1;
        final int n4 = n * n2;
        try {
            String s4 = " select CONNECT_ID, CONNECT_NAME, CONNECT_URL , CONNECT_DESCRIPTION  from (select CONNECT_ID, CONNECT_NAME, CONNECT_URL , CONNECT_DESCRIPTION, ROWNUM N from CM_CONNECT_TB  where 1=1 ";
            if (s != null && s.length() != 0) {
                s4 = s4 + " and lower(CONNECT_NAME) like '%" + s.toLowerCase().trim() + "%'";
            }
            if (s2 != null && s2.length() != 0) {
                s4 = s4 + " and lower(CONNECT_URL) like '%" + s2.toLowerCase().trim() + "%'";
            }
            if (!"".equals(s3) && s3 != null) {
                s4 = s4 + " and lower(CONNECT_DESCRIPTION) like '%" + s3.toLowerCase().trim() + "%' ";
            }
            final String string = s4 + " ) t3 where t3.N between " + n3 + " and " + n4;
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            set = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (set.next()) {
                final CM_CONNECT_ENTITY cm_CONNECT_ENTITY = new CM_CONNECT_ENTITY();
                cm_CONNECT_ENTITY.setId(Integer.parseInt(set.getString(1)));
                cm_CONNECT_ENTITY.setName(set.getString(2));
                cm_CONNECT_ENTITY.setUrl(set.getString(3));
                cm_CONNECT_ENTITY.setDescription(set.getString(4));
                vector.addElement(cm_CONNECT_ENTITY);
            }
        }
        catch (SQLException ex) {
            Log.debug("CM_CONNECT_DAO.Max_count block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("CM_CONNECT_DAO.Max_count block Exception : " + ex2.toString());
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
            final CM_CONNECT_ENTITY[] array = new CM_CONNECT_ENTITY[vector.size()];
            vector.copyInto(array);
            return array;
        }
        return null;
    }
    
    public void insert(final String s, final String s2, final String s3, final HttpServletRequest httpServletRequest, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        String string = " insert into CM_CONNECT_TB (CONNECT_ID, CONNECT_NAME, CONNECT_URL, CONNECT_DESCRIPTION) \n";
        if (!"".equals(s) && !"".equals(s2)) {
            string = string + " values (seq_cm_connect_tb.nextval, '" + s + "', '" + s2 + "', '" + s3 + "') \n";
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
    
    public void update(final int n, final String s, final String s2, final String s3, final HttpServletRequest httpServletRequest, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String string = " update CM_CONNECT_TB set CONNECT_NAME = '" + s + "', CONNECT_URL ='" + s2 + "', CONNECT_DESCRIPTION = '" + s3 + "' where CONNECT_ID = " + n;
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
        final int int1 = Integer.parseInt(s);
        String string = "";
        if (!"".equals(s) && s != null) {
            string = string + " delete from CM_CONNECT_TB where CONNECT_ID = " + int1 + " \n";
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
