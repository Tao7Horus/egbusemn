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
import entity.UM_ADE_GovrJ030b;

public class UM_ADB_GovrJ020p
{
    public UM_ADE_GovrJ030b code_organ(final String s) throws Exception, SQLException {
        Trx trx = null;
        Connection connection = null;
        UM_ADE_GovrJ030b um_ADE_GovrJ030b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "select 코드명_대 from syn_기관유형코드  where  기관유형_대 = '" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ADE_GovrJ030b = new UM_ADE_GovrJ030b();
                um_ADE_GovrJ030b.sets03(executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrJ020p.code_organ('" + s + "'):" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrJ020p.code_organ('" + s + "'):" + ex2.toString());
            throw new Exception(ex2.getMessage());
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
        return um_ADE_GovrJ030b;
    }
    
    public UM_ADE_GovrJ030b code_organ1(final String s, final String s2) throws Exception, SQLException {
        Trx trx = null;
        Connection connection = null;
        UM_ADE_GovrJ030b um_ADE_GovrJ030b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "select 코드명_중 from syn_기관유형코드 where  기관유형_대 = '" + s + "' and 기관유형_중 = '" + s2 + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ADE_GovrJ030b = new UM_ADE_GovrJ030b();
                um_ADE_GovrJ030b.sets05(executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrJ020p.code_organ1('" + s + "','" + s2 + "'):" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrJ020p.code_organ1('" + s + "','" + s2 + "'):" + ex2.toString());
            throw new Exception(ex2.getMessage());
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
        return um_ADE_GovrJ030b;
    }
    
    public UM_ADE_GovrJ030b code_organ2(final String s, final String s2, final String s3) throws Exception, SQLException {
        Trx trx = null;
        Connection connection = null;
        UM_ADE_GovrJ030b um_ADE_GovrJ030b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "select 코드명_중 from syn_기관유형코드 where  기관유형_대 = '" + s + "' and 기관유형_중 = '" + s2 + "' and 기관유형_중 = '" + s3 + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ADE_GovrJ030b = new UM_ADE_GovrJ030b();
                um_ADE_GovrJ030b.sets07(executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrJ020p.code_organ1('" + s + "','" + s2 + "','" + s3 + "'):" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrJ020p.code_organ1('" + s + "','" + s2 + "','" + s3 + "'):" + ex2.toString());
            throw new Exception(ex2.getMessage());
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
        return um_ADE_GovrJ030b;
    }
    
    public int Max_code(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String string = "\n SELECT count(코드명_대) FROM syn_기관유형코드 \n WHERE 기관유형_대 = '" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrJ020p.Max_code('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrJ020p.Max_code('" + s + "'):" + ex2.toString());
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
    
    public int Max_code1(final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String string = "\n SELECT count(코드명_중) FROM syn_기관유형코드 \n WHERE 기관유형_대 = '" + s + "' and 기관유형_중 = '" + s2 + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrJ020p.Max_code1('" + s + "','" + s2 + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrJ020p.Max_code1('" + s + "','" + s2 + "'):" + ex2.toString());
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
    
    public int Max_code2(final String s, final String s2, final String s3) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String string = "\n SELECT count(코드명_대) FROM syn_기관유형코드 \n WHERE 기관유형_대 = '" + s + "' and 기관유형_중 = '" + s2 + "' and 기관유형_중 = '" + s3 + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrJ020p.Max_code1('" + s + "','" + s2 + "','" + s3 + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrJ020p.Max_code1('" + s + "','" + s2 + "','" + s3 + "'):" + ex2.toString());
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
