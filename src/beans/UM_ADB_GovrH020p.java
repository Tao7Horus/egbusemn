// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import common.Log;
import common.Trx;
import entity.UM_ADV_GovuA030b;

public class UM_ADB_GovrH020p
{
    public UM_ADV_GovuA030b code_list(final String s) {
        Trx trx = null;
        Connection connection = null;
        UM_ADV_GovuA030b um_ADV_GovuA030b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            String s2;
            if (s.equals("Z")) {
                s2 = "SELECT 'Z'||lpad((substr(max(공공기관코드), 2, 7)+1), 6, '0') \n    FROM   \n   사용_공공기관마스터  where 공공기관코드 like 'Z0%' ";
            }
            else if (s.equals("ZD")) {
                s2 = "SELECT 'ZD'||lpad((substr(max(공공기관코드), 3, 7)+1), 5, '0')\n from 사용_공공기관마스터  where 공공기관코드 like 'ZD%'";
            }
            else if (s.equals("ZI")) {
                s2 = "SELECT 'ZI'||lpad((substr(max(공공기관코드), 3, 7)+1), 5, '0') \n   FROM   \n  사용_공공기관마스터  where 공공기관코드 like 'ZI%' ";
            }
            else {
                s2 = "select count(*) from 사용_공공기관마스터 where 공공기관코드=''";
            }
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ADV_GovuA030b = new UM_ADV_GovuA030b();
                um_ADV_GovuA030b.setg2bCode(executeQuery.getString(1));
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GovrH020p.code_list('" + s + "'):" + ex.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex2) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex3) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        return um_ADV_GovuA030b;
    }
}
