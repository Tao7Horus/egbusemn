// 
// Decompiled by Procyon v0.5.30
// 

package common.util;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import common.Log;
import common.Trx;
import java.util.Vector;
import java.sql.Connection;

public class UM_COB_GTQ904
{
    public UM_COE_GTQ903[] getList(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final Vector vector = new Vector<UM_COE_GTQ903>(1, 1);
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            final int columnCount = executeQuery.getMetaData().getColumnCount();
            while (executeQuery.next()) {
                final UM_COE_GTQ903 um_COE_GTQ903 = new UM_COE_GTQ903();
                um_COE_GTQ903.dataArraySetting(columnCount);
                for (int i = 1; i <= columnCount; ++i) {
                    um_COE_GTQ903.data[i - 1] = executeQuery.getString(i);
                }
                vector.addElement(um_COE_GTQ903);
            }
            final UM_COE_GTQ903[] array = new UM_COE_GTQ903[vector.size()];
            vector.copyInto(array);
            return array;
        }
        catch (Exception ex) {
            Log.debug("[UM_COB_GTQ904.getList()] caller[" + o.getClass().getName() + "] sql[" + s + "] : " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (connection != null && b) {
                    trx.close();
                }
            }
            catch (Exception ex4) {}
        }
    }
    
    public int getCount(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        boolean b = false;
        int int1 = 0;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
            return int1;
        }
        catch (Exception ex) {
            Log.debug("[UM_COB_GTQ904.getCount()] caller[" + o.getClass().getName() + "] sql[" + s + "] : " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (connection != null && b) {
                    trx.close();
                }
            }
            catch (Exception ex4) {}
        }
    }
    
    public String getSqlDataOne(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        String string = null;
        Statement statement = null;
        ResultSet executeQuery = null;
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            statement = connection.createStatement();
            executeQuery = statement.executeQuery(s);
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
                if (executeQuery.wasNull()) {
                    string = null;
                }
            }
        }
        catch (Exception ex) {
            Log.debug("[UM_COB_GTQ904.getSqlDataOne()] caller[" + o.getClass().getName() + "] sql[" + s + "] : " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (connection != null && b) {
                    trx.close();
                }
            }
            catch (Exception ex4) {}
        }
        return string;
    }
}
