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
import entity.UM_RAE_OrgDivEntity;

public class UM_RAB_OrgDivBean
{
    public UM_RAE_OrgDivEntity[] orgDivFullList() {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAE_OrgDivEntity>();
        try {
            sb.append("select 코드, 코드명 from SYN_공동코드 where 코드구분 = 'J51' order by 코드");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_OrgDivEntity um_RAE_OrgDivEntity = new UM_RAE_OrgDivEntity();
                um_RAE_OrgDivEntity.setcode(executeQuery.getString(1));
                um_RAE_OrgDivEntity.setcodeName(executeQuery.getString(2));
                vector.addElement(um_RAE_OrgDivEntity);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_OrgDivBean.orgDivFullList():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_OrgDivBean.orgDivFullList():" + ex2.toString());
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
        final UM_RAE_OrgDivEntity[] array = new UM_RAE_OrgDivEntity[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_RAE_OrgDivEntity[] orgDivFirstList() {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAE_OrgDivEntity>();
        try {
            sb.append("select 코드, 코드명 from SYN_공동코드 where 코드구분 = 'J51' and length(코드) = 2 order by 코드");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_OrgDivEntity um_RAE_OrgDivEntity = new UM_RAE_OrgDivEntity();
                um_RAE_OrgDivEntity.setcode(executeQuery.getString(1));
                um_RAE_OrgDivEntity.setcodeName(executeQuery.getString(2));
                vector.addElement(um_RAE_OrgDivEntity);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_OrgDivBean.orgDivFirstList():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_OrgDivBean.orgDivFirstList():" + ex2.toString());
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
        final UM_RAE_OrgDivEntity[] array = new UM_RAE_OrgDivEntity[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_RAE_OrgDivEntity[] orgDivSecondList(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAE_OrgDivEntity>();
        try {
            sb.append("select 코드, 코드명 from SYN_공동코드 ");
            sb.append("where 코드구분 = 'J51' and length(코드) = 4 and 코드 like '" + s + "%' order by 코드");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_OrgDivEntity um_RAE_OrgDivEntity = new UM_RAE_OrgDivEntity();
                um_RAE_OrgDivEntity.setcode(executeQuery.getString(1));
                um_RAE_OrgDivEntity.setcodeName(executeQuery.getString(2));
                vector.addElement(um_RAE_OrgDivEntity);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_OrgDivBean.orgDivSecondList():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_OrgDivBean.orgDivSecondList():" + ex2.toString());
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
        final UM_RAE_OrgDivEntity[] array = new UM_RAE_OrgDivEntity[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_RAE_OrgDivEntity[] orgDivThirdList(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAE_OrgDivEntity>();
        try {
            sb.append("select 코드, 코드명 from SYN_공동코드 ");
            sb.append("where 코드구분 = 'J51' and length(코드) = 6 and 코드 like '" + s + "%' order by 코드");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_OrgDivEntity um_RAE_OrgDivEntity = new UM_RAE_OrgDivEntity();
                um_RAE_OrgDivEntity.setcode(executeQuery.getString(1));
                um_RAE_OrgDivEntity.setcodeName(executeQuery.getString(2));
                vector.addElement(um_RAE_OrgDivEntity);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_OrgDivBean.orgDivThirdList():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_OrgDivBean.orgDivThirdList():" + ex2.toString());
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
        final UM_RAE_OrgDivEntity[] array = new UM_RAE_OrgDivEntity[vector.size()];
        vector.copyInto(array);
        return array;
    }
}
