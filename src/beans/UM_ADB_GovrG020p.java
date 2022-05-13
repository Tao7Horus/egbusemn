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
import entity.UM_ADE_GovrG030b;

public class UM_ADB_GovrG020p
{
    public UM_ADE_GovrG030b[] code_list() {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_ADE_GovrG030b>();
        try {
            final String s = "SELECT\t코드구분, 코드, 코드명 FROM  SYN_공동코드 where 코드구분='J34' and 사용여부='Y'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_ADE_GovrG030b um_ADE_GovrG030b = new UM_ADE_GovrG030b();
                um_ADE_GovrG030b.setS01(executeQuery.getString(1));
                um_ADE_GovrG030b.setS02(executeQuery.getString(2));
                um_ADE_GovrG030b.setS03(executeQuery.getString(3));
                vector.addElement(um_ADE_GovrG030b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrG020p.code_list():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrG020p.code_list():" + ex2.toString());
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
        final UM_ADE_GovrG030b[] array = new UM_ADE_GovrG030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count() {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        int int1 = 0;
        try {
            final String s = "SELECT\tcount(*)  FROM\tSYN_공동코드 where 코드구분='J34' and 사용여부='Y'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrG020p.Max_count():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrG020p.Max_count():" + ex2.toString());
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
