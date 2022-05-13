// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.util.Vector;
import entity.UM_FOE_CommInfo2;
import java.sql.Connection;

public class UM_FOB_Forn061
{
    public UM_FOE_CommInfo2[] getList(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final Vector vector = new Vector<UM_FOE_CommInfo2>(1, 1);
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
                final UM_FOE_CommInfo2 um_FOE_CommInfo2 = new UM_FOE_CommInfo2();
                um_FOE_CommInfo2.dataArraySetting(columnCount);
                for (int i = 1; i <= columnCount; ++i) {
                    um_FOE_CommInfo2.data[i - 1] = executeQuery.getString(i);
                }
                vector.addElement(um_FOE_CommInfo2);
            }
            final UM_FOE_CommInfo2[] array = new UM_FOE_CommInfo2[vector.size()];
            vector.copyInto(array);
            return array;
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn061.getList():caller");
            Log.debug("UM_FOB_Forn061.getList():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn061.getList():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
    }
    
    public int getCount(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            if (!executeQuery.next()) {
                throw new Exception("ResultSet Null 리턴");
            }
            final int int1 = executeQuery.getInt(1);
            if (executeQuery.wasNull()) {
                throw new Exception("Null 리턴됨");
            }
            return int1;
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn061.getList():caller");
            Log.debug("UM_FOB_Forn061.getList():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn061.getCount():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
    }
}
