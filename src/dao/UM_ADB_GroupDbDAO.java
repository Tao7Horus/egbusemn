// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import common.Log;
import common.Trx;
import java.util.Vector;
import entity.UM_ADE_GroupE010b;

public class UM_ADB_GroupDbDAO
{
    public static final String CONNECTION_USER_EXMS = "exms";
    public static final String CONNECTION_USER_PFORN = "Pforn";
    public static final String CONNECTION_USER_PORTAL = "portal";
    public static final String CONNECTION_USER_ODS = "ods";
    public static final String CONNECTION_USER_HPAGE = "hpage";
    
    public UM_ADE_GroupE010b[] getAllList() {
        final String s = "SELECT * FROM UM_PUB_GROUP";
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        UM_ADE_GroupE010b[] array = null;
        final Vector vector = new Vector<UM_ADE_GroupE010b>(1, 1);
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "USEMN");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            executeQuery.getMetaData().getColumnCount();
            while (executeQuery.next()) {
                final UM_ADE_GroupE010b um_ADE_GroupE010b = new UM_ADE_GroupE010b();
                um_ADE_GroupE010b.setGroup_name(executeQuery.getString(1));
                um_ADE_GroupE010b.setDescription(executeQuery.getString(2));
                um_ADE_GroupE010b.setGroup_id(executeQuery.getInt(3));
                vector.addElement(um_ADE_GroupE010b);
            }
            array = new UM_ADE_GroupE010b[vector.size()];
            vector.copyInto(array);
        }
        catch (Exception ex) {
            Log.debug("Loi getAlllist :" + ex.toString());
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
        return array;
    }
    
    public HashMap getAllHash() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "USEMN");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("select GROUP_NO, GROUP_NAME from UM_PUB_GROUP order by GROUP_NAME ASC");
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
    
    public void insertGroup(final UM_ADE_GroupE010b um_ADE_GroupE010b) {
        final StringBuffer sb = new StringBuffer();
        PreparedStatement prepareStatement = null;
        Connection connection = null;
        try {
            if (connection == null) {
                connection = new Trx(this, "USEMN").getConnection();
                connection.setAutoCommit(false);
            }
            sb.append("Insert into UM_PUB_GROUP values(?,?,seq_um_pub_group.nextval)");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, um_ADE_GroupE010b.getGroup_name());
            prepareStatement.setString(2, um_ADE_GroupE010b.getDescription());
            prepareStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception ex) {
            Log.debug("Loi insert1 !" + ex.toString());
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public void updateGroup(final UM_ADE_GroupE010b um_ADE_GroupE010b) {
        final StringBuffer sb = new StringBuffer();
        PreparedStatement prepareStatement = null;
        Connection connection = null;
        try {
            if (connection == null) {
                connection = new Trx(this, "USEMN").getConnection();
            }
            sb.append(" update um_pub_group set group_name = ?, group_description = ?");
            sb.append(" where group_no = ? ");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, um_ADE_GroupE010b.getGroup_name());
            prepareStatement.setString(2, um_ADE_GroupE010b.getDescription());
            prepareStatement.setInt(3, um_ADE_GroupE010b.getGroup_id());
            if (prepareStatement.executeUpdate() != 1) {
                Log.debug("Loi update !");
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception ex) {
            Log.debug("Loi update !");
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public void deleteGroup(final UM_ADE_GroupE010b um_ADE_GroupE010b) {
        final StringBuffer sb = new StringBuffer();
        PreparedStatement prepareStatement = null;
        Connection connection = null;
        try {
            if (connection == null) {
                connection = new Trx(this, "USEMN").getConnection();
            }
            sb.append(" delete from um_pub_group");
            sb.append(" where GROUP_NO = ? ");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setInt(1, um_ADE_GroupE010b.getGroup_id());
            if (prepareStatement.executeUpdate() != 1) {
                Log.debug("Loi DELETE");
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception ex) {
            Log.debug("Loi DELETE");
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public UM_ADE_GroupE010b getGroupById(final int n) {
        final String s = "SELECT * FROM UM_PUB_GROUP WHERE GROUP_NO = ?";
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        UM_ADE_GroupE010b um_ADE_GroupE010b = null;
        try {
            if (connection == null) {
                trx = new Trx(this, "USEMN");
                connection = trx.getConnection();
            }
            prepareStatement = connection.prepareStatement(s);
            prepareStatement.setInt(1, n);
            executeQuery = prepareStatement.executeQuery();
            executeQuery.getMetaData().getColumnCount();
            while (executeQuery.next()) {
                um_ADE_GroupE010b = new UM_ADE_GroupE010b();
                um_ADE_GroupE010b.setGroup_name(executeQuery.getString(1));
                um_ADE_GroupE010b.setDescription(executeQuery.getString(2));
                um_ADE_GroupE010b.setGroup_id(executeQuery.getInt(3));
            }
        }
        catch (Exception ex) {
            Log.debug("Loi khi getGroupId : " + ex.toString());
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
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex4) {}
        }
        return um_ADE_GroupE010b;
    }
    
    public UM_ADE_GroupE010b[] getGroupByName(final String s, final String s2, final int n, final int n2) {
        final StringBuffer sb = new StringBuffer();
        sb.append("SELECT GROUP_NAME,DESCRIPTION,GROUP_NO           \n");
        sb.append("FROM (                                                \n");
        sb.append("SELECT GROUP_NAME, GROUP_DESCRIPTION DESCRIPTION, GROUP_NO, ROWNUM NUM FROM UM_PUB_GROUP WHERE 1=1       \n");
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        Object[] array = null;
        final Vector vector = new Vector<UM_ADE_GroupE010b>(1, 1);
        int n3 = 0;
        if (!s.equals("")) {
            sb.append("AND lower(trim(GROUP_NAME)) like '%'||?||'%'  \n");
        }
        if (!s2.equals("")) {
            sb.append("AND lower(trim(GROUP_DESCRIPTION)) like '%'||?||'%'  \n");
        }
        sb.append("    ) A                                     \n");
        sb.append("WHERE A.NUM  BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")\t  \n");
        try {
            if (connection == null) {
                trx = new Trx(this);
                connection = trx.getConnection();
            }
            prepareStatement = connection.prepareStatement(sb.toString());
            if (!s.equals("")) {
                ++n3;
                prepareStatement.setString(n3, s.toLowerCase().trim());
            }
            if (!s2.equals("")) {
                ++n3;
                prepareStatement.setString(n3, s2.toLowerCase().trim());
            }
            executeQuery = prepareStatement.executeQuery();
            executeQuery.getMetaData().getColumnCount();
            Log.debug("getGroupByName:" + sb.toString());
            while (executeQuery.next()) {
                final UM_ADE_GroupE010b um_ADE_GroupE010b = new UM_ADE_GroupE010b();
                um_ADE_GroupE010b.setGroup_name(executeQuery.getString(1));
                um_ADE_GroupE010b.setDescription(executeQuery.getString(2));
                um_ADE_GroupE010b.setGroup_id(executeQuery.getInt(3));
                vector.addElement(um_ADE_GroupE010b);
            }
            array = new UM_ADE_GroupE010b[vector.size()];
            vector.copyInto(array);
        }
        catch (Exception ex) {
            Log.debug("Loi getGroupByName :" + ex.toString());
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
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex4) {}
        }
        return (UM_ADE_GroupE010b[])array;
    }
    
    public int countGroupByName(final String s, final String s2) {
        final StringBuffer sb = new StringBuffer();
        sb.append("SELECT COUNT(*) FROM UM_PUB_GROUP ");
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        int int1 = 0;
        try {
            if (connection == null) {
                trx = new Trx(this);
                connection = trx.getConnection();
            }
            prepareStatement = connection.prepareStatement(sb.toString());
            executeQuery = prepareStatement.executeQuery();
            executeQuery.getMetaData();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (Exception ex) {
            Log.debug("Loi getGroupByName :" + ex.toString());
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
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex4) {}
        }
        return int1;
    }
}
