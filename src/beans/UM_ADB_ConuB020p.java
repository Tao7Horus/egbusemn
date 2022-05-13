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
import entity.UM_RAE_ConuB010b;

public class UM_ADB_ConuB020p
{
    public UM_RAE_ConuB010b[] saupNo_list(final int n, final int n2, final String s, final String s2) {
        Connection connection = null;
        Trx trx = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAE_ConuB010b>();
        try {
            sb.append("SELECT\t사업자등록번호, 상호명,대표자명, ROWNUM N \t\t\t\t\t\t\t\t\n");
            sb.append("\t FROM\t사용_전자문서상태\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tROWNUM between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")\t\n");
            sb.append(" AND 상호명 LIKE '%" + s + "%' AND 사업자등록번호 LIKE '" + s2 + "%'   \n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_ConuB010b um_RAE_ConuB010b = new UM_RAE_ConuB010b();
                um_RAE_ConuB010b.setSaupNo(executeQuery.getString(1));
                um_RAE_ConuB010b.setSangho(executeQuery.getString(2));
                um_RAE_ConuB010b.setCeoName(executeQuery.getString(3));
                vector.addElement(um_RAE_ConuB010b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConuB020p.select_user block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConuB020p.select_user block Exception : " + ex2.toString());
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
        final UM_RAE_ConuB010b[] array = new UM_RAE_ConuB010b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count(final String s, final String s2) {
        Connection connection = null;
        Trx trx = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        int int1 = 0;
        try {
            sb.append(" SELECT\tcount(사업자등록번호)  FROM\t사용_전자문서상태\t\t\t\n");
            sb.append(" WHERE 상호명 LIKE '%" + s + "%' AND 사업자등록번호 LIKE '" + s2 + "%'");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConuB020p.Max_count block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConuB020p.Max_count block Exception : " + ex2.toString());
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
