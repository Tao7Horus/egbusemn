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
import entity.UM_RAE_OrgLawEntity;

public class UM_RAB_OrgLawBean
{
    public UM_RAE_OrgLawEntity[] orgLawFullList() {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAE_OrgLawEntity>();
        try {
            sb.append("select 코드, 코드명 from SYN_공동코드 where 코드구분 = 'GGL' order by 코드");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_OrgLawEntity um_RAE_OrgLawEntity = new UM_RAE_OrgLawEntity();
                um_RAE_OrgLawEntity.setcode(executeQuery.getString(1));
                um_RAE_OrgLawEntity.setcodeName(executeQuery.getString(2));
                vector.addElement(um_RAE_OrgLawEntity);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_OrgLawBean.orgLawFullList():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_OrgLawBean.orgLawFullList():" + ex2.toString());
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
        final UM_RAE_OrgLawEntity[] array = new UM_RAE_OrgLawEntity[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_RAE_OrgLawEntity[] orgLawFirstList() {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAE_OrgLawEntity>();
        try {
            sb.append("select 코드, 코드명 from SYN_공동코드 where 코드구분 = 'GGL' and length(코드) = 1 order by 코드");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_OrgLawEntity um_RAE_OrgLawEntity = new UM_RAE_OrgLawEntity();
                um_RAE_OrgLawEntity.setcode(executeQuery.getString(1));
                um_RAE_OrgLawEntity.setcodeName(executeQuery.getString(2));
                vector.addElement(um_RAE_OrgLawEntity);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_OrgLawBean.orgLawFirstList():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_OrgLawBean.orgLawFirstList():" + ex2.toString());
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
        final UM_RAE_OrgLawEntity[] array = new UM_RAE_OrgLawEntity[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_RAE_OrgLawEntity[] orgLawSecondList(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAE_OrgLawEntity>();
        try {
            sb.append("select 코드, 코드명 from SYN_공동코드 ");
            sb.append("where 코드구분 = 'GGL' and length(코드) = 2 and 코드 like '" + s + "%' order by 코드");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_OrgLawEntity um_RAE_OrgLawEntity = new UM_RAE_OrgLawEntity();
                um_RAE_OrgLawEntity.setcode(executeQuery.getString(1));
                um_RAE_OrgLawEntity.setcodeName(executeQuery.getString(2));
                vector.addElement(um_RAE_OrgLawEntity);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_OrgLawBean.orgLawSecondList():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_OrgLawBean.orgLawSecondList():" + ex2.toString());
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
        final UM_RAE_OrgLawEntity[] array = new UM_RAE_OrgLawEntity[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_RAE_OrgLawEntity[] orgLawThirdList(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAE_OrgLawEntity>();
        try {
            sb.append("select 코드, 코드명 from SYN_공동코드 ");
            sb.append("where 코드구분 = 'GGL' and length(코드) = 3 and 코드 like '" + s + "%' order by 코드");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_OrgLawEntity um_RAE_OrgLawEntity = new UM_RAE_OrgLawEntity();
                um_RAE_OrgLawEntity.setcode(executeQuery.getString(1));
                um_RAE_OrgLawEntity.setcodeName(executeQuery.getString(2));
                vector.addElement(um_RAE_OrgLawEntity);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_OrgLawBean.orgLawThirdList():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_OrgLawBean.orgLawThirdList():" + ex2.toString());
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
        final UM_RAE_OrgLawEntity[] array = new UM_RAE_OrgLawEntity[vector.size()];
        vector.copyInto(array);
        return array;
    }
}
