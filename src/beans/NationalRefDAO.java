// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.util.HashMap;

public class NationalRefDAO
{
    public HashMap getAll(final String s) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "USEMN");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("select CD, CD_NM2 from SYN_PUB_CODE WHERE CD_CLS = '" + s + "' order by NLSSORT(CD_NM2,'NLS_SORT=vietnamese') ASC");
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                hashMap.put(executeQuery.getString(1), executeQuery.getString(2));
            }
        }
        catch (SQLException ex) {
            Log.debug(ex.toString());
        }
        catch (Exception ex2) {
            Log.debug(ex2.toString());
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
        return hashMap;
    }
    
    public List getList(final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "USEMN");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("select CD, CD_NM2 from SYN_PUB_CODE WHERE CD_CLS = '" + s + "' order by NLSSORT(CD_NM2,'NLS_SORT=vietnamese') ASC");
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                list.add(executeQuery.getString(2) + "#" + executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            Log.debug(ex.toString());
        }
        catch (Exception ex2) {
            Log.debug(ex2.toString());
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
        return list;
    }
    
    public List getNameByCD(final String s, final String s2) {
        final ArrayList list = new ArrayList();
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "USEMN");
            connection = trx.getConnection();
            String s3 = "CD_NM2";
            if ("GU9".equals(s)) {
                s3 = "CD_NM";
            }
            prepareStatement = connection.prepareStatement("select " + s3 + " from SYN_PUB_CODE WHERE CD_CLS = '" + s + "' and CD ='" + s2.trim() + "' order by NLSSORT(" + s3 + ",'NLS_SORT=vietnamese') ASC");
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                list.add(executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            Log.debug(ex.toString());
        }
        catch (Exception ex2) {
            Log.debug(ex2.toString());
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
        Collections.sort((List<Comparable>)list);
        return list;
    }
    
    public String getGoodNameBy(final String s) {
        final String s2 = "";
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "USEMN");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("select NM_ITEM from UM_ITEMS where CD_ITEM='" + s + "'");
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                return executeQuery.getString(1);
            }
            return s2;
        }
        catch (SQLException ex) {
            Log.debug(ex.toString());
        }
        catch (Exception ex2) {
            Log.debug(ex2.toString());
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
        return s2;
    }
}
