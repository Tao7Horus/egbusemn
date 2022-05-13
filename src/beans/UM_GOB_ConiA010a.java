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
import java.util.Vector;
import entity.UM_GOE_ConiA010a;

public class UM_GOB_ConiA010a
{
    public UM_GOE_ConiA010a[] list(final int n, final int n2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        final Vector vector = new Vector<UM_GOE_ConiA010a>();
        try {
            sb.append("\tSELECT 코드, 코드명, 코드명2, 적요 FROM\tSYN_공동코드\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t         WHERE 코드구분 = 'GUG'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t         ORDER BY 적요\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA010a um_GOE_ConiA010a = new UM_GOE_ConiA010a();
                um_GOE_ConiA010a.setcode(executeQuery.getString(1));
                um_GOE_ConiA010a.setcodevalue(executeQuery.getString(2));
                um_GOE_ConiA010a.setcodevalue1(executeQuery.getString(3));
                vector.addElement(um_GOE_ConiA010a);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiA010a.list() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiA010a.list() : sql : " + string + "::: " + ex2.toString());
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
        final UM_GOE_ConiA010a[] array = new UM_GOE_ConiA010a[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count() {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        int int1 = 0;
        try {
            sb.append("\tSELECT COUNT(*) FROM SYN_공동코드\t\t\t\t\n");
            sb.append("\t WHERE 코드구분 = 'GUG'\t\t\t\t\t\t\n");
            string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiA010a.Max_count() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiA010a.Max_count() : sql : " + string + "::: " + ex2.toString());
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
