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
import entity.UM_RAE_ConuA010b;

public class UM_FOB_FornA011p
{
    public UM_RAE_ConuA010b[] Mulpum_List(final int n, final int n2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAE_ConuA010b>();
        try {
            sb.append(" SELECT 분류명, 물품분류번호, ROWNUM N \n");
            sb.append(" FROM (SELECT 분류명, 물품분류번호, ROWNUM N FROM 사용_비축품목 WHERE 사용여부 = 'Y' \n");
            sb.append(" ) sub \n");
            sb.append(" WHERE N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")  \n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_ConuA010b um_RAE_ConuA010b = new UM_RAE_ConuA010b();
                um_RAE_ConuA010b.setCodeName(executeQuery.getString(1));
                um_RAE_ConuA010b.setCode(executeQuery.getString(2));
                vector.addElement(um_RAE_ConuA010b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_FornA011p.Mulpum_List():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_FornA011p.Mulpum_List():" + ex2.toString());
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
        final UM_RAE_ConuA010b[] array = new UM_RAE_ConuA010b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Mulpum_Count() {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        int int1 = 0;
        try {
            sb.append(" SELECT COUNT(물품분류번호) \n");
            sb.append(" FROM (SELECT 분류명, 물품분류번호, ROWNUM N FROM 사용_비축품목 WHERE 사용여부 = 'Y' \n");
            sb.append(" ) sub \n");
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
            Log.debug("UM_FOB_FornA011p.Mulpum_Count():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_FornA011p.Mulpum_Count():" + ex2.toString());
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
