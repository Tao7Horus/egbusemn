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

public class UM_GOB_ConiE010c
{
    public String SoGwanGubun(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        try {
            final String string2 = " SELECT INSTITU_CLS FROM UM_PUB_INSTITU_MAST WHERE INSTITU_CD = '" + s + "'\n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiE010c.SoGwanGubun('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiE010c.SoGwanGubun('" + s + "'):" + ex2.toString());
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
    
    public int JiBangGogiupGubun(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String string = " SELECT COUNT(*) FROM UM_LOCAL_PUB_ENTER WHERE IS_USED = 'Y' AND INSTITU_CD = '" + s + "'\n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiE010c.JiBangGogiupGubun('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiE010c.JiBangGogiupGubun('" + s + "'):" + ex2.toString());
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
