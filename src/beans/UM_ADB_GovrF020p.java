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
import entity.UM_ADE_GovrF030b;

public class UM_ADB_GovrF020p
{
    public UM_ADE_GovrF030b[] post_list(final int n, final int n2, final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_ADE_GovrF030b>();
        try {
            final String string = "SELECT\t우편번호, 읍면동명, 거리, 번지, N \n    FROM (SELECT 우편번호, 읍면동명, 거리, 번지, ROWNUM N FROM SYN_우편번호 \n    where 읍면동명 LIKE '%'||?||'%' and  우편번호 LIKE ?||'%')\n    WHERE N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_ADE_GovrF030b um_ADE_GovrF030b = new UM_ADE_GovrF030b();
                um_ADE_GovrF030b.setS01(executeQuery.getString(1));
                um_ADE_GovrF030b.setS02(executeQuery.getString(2));
                um_ADE_GovrF030b.setS03(executeQuery.getString(3));
                um_ADE_GovrF030b.setS04(executeQuery.getString(4));
                vector.addElement(um_ADE_GovrF030b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrF020p.post_list(3'" + s + "',4'" + s2 + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrF020p.post_list(3'" + s + "',4'" + s2 + "'):" + ex2.toString());
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
        final UM_ADE_GovrF030b[] array = new UM_ADE_GovrF030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count(final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        int int1 = 0;
        try {
            sb.append("SELECT\tcount(*)  FROM\tSYN_우편번호\n");
            if (s.length() > 1 || s2.length() >= 1) {
                sb.append("\t  WHERE\t읍면동명 LIKE '%'||?||'%' and 우편번호 LIKE ?||'%' \n");
            }
            else {
                sb.append("\t  WHERE\t읍면동명 = ''\t and 우편번호 = ''\n");
            }
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            if (s.length() > 1 || s2.length() >= 1) {
                prepareStatement.setString(1, s);
                prepareStatement.setString(2, s2);
            }
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrF020p.Max_count('" + s + "','" + s2 + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrF020p.Max_count('" + s + "','" + s2 + "'):" + ex2.toString());
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
