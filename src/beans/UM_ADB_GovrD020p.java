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
import entity.UM_ADV_GovuA030b;

public class UM_ADB_GovrD020p
{
    public UM_ADV_GovuA030b select_id(final String s) {
        Connection connection = null;
        Trx trx = null;
        UM_ADV_GovuA030b um_ADV_GovuA030b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "SELECT count(*) FROM 사용_공공기관마스터 WHERE 공공기관명_약어 ='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ADV_GovuA030b = new UM_ADV_GovuA030b();
                um_ADV_GovuA030b.setgoNameShort(executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrD020p.select_user block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrD020p.select_user block Exception : " + ex2.toString());
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
        return um_ADV_GovuA030b;
    }
    
    public UM_ADV_GovuA030b select_g2bCode(final String s) {
        Connection connection = null;
        Trx trx = null;
        UM_ADV_GovuA030b um_ADV_GovuA030b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "SELECT count(*) FROM 사용_공공기관마스터 WHERE 공공기관코드 ='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ADV_GovuA030b = new UM_ADV_GovuA030b();
                um_ADV_GovuA030b.setg2bCode(executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrD020p.select_user block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrD020p.select_user block Exception : " + ex2.toString());
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
        return um_ADV_GovuA030b;
    }
    
    public UM_ADV_GovuA030b select_saupNo(final String s) {
        Connection connection = null;
        Trx trx = null;
        UM_ADV_GovuA030b um_ADV_GovuA030b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "SELECT count(*) FROM 사용_공공기관마스터 WHERE 사업자등록번호 ='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ADV_GovuA030b = new UM_ADV_GovuA030b();
                um_ADV_GovuA030b.setsaupNo(executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrD020p.select_user block SQLException : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrD020p.select_user block Exception : " + ex2.toString());
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
        return um_ADV_GovuA030b;
    }
}
