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
import entity.UM_ADE_DepartmentE010b;

public class UM_ADB_DepartmentDbDAO
{
    public UM_ADE_DepartmentE010b[] getAllList() {
        final String s = "SELECT DEPARTMENT_NO, dep_name, dep_description FROM UM_DEPARTMENT";
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        UM_ADE_DepartmentE010b[] array = null;
        final Vector vector = new Vector<UM_ADE_DepartmentE010b>(1, 1);
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this);
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            executeQuery.getMetaData().getColumnCount();
            while (executeQuery.next()) {
                final UM_ADE_DepartmentE010b um_ADE_DepartmentE010b = new UM_ADE_DepartmentE010b();
                um_ADE_DepartmentE010b.setId(executeQuery.getInt(1));
                um_ADE_DepartmentE010b.setName(executeQuery.getString(2));
                um_ADE_DepartmentE010b.setDescription(executeQuery.getString(3));
                vector.addElement(um_ADE_DepartmentE010b);
            }
            array = new UM_ADE_DepartmentE010b[vector.size()];
            vector.copyInto(array);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".getList():caller->" + this.getClass().getName() + ":" + ex.toString());
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
            prepareStatement = connection.prepareStatement("select DEPARTMENT_NO, DEP_NAME from UM_DEPARTMENT order by DEP_NAME ASC");
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
}
