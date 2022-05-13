// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import secu.lib.MessageDigest;
import secu.lib.Secu;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class UM_RAB_GovuA000
{
    public String getSelect() throws Exception {
        String string = "";
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final String s = " select password from um_user";
        try {
            trx = new Trx(this);
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s.toString());
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                string = string + executeQuery.getString(1) + "<br>";
            }
        }
        catch (SQLException ex) {
            Log.errors("resmReceiveSearch.getResmReceiveList block SQLException : ");
            Log.errors(this, ex, "Block Exception : " + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new Exception("Exception Error : " + ex.toString());
        }
        catch (Exception ex2) {
            Log.errors("resmReceiveSearch.getResmReceiveList block Exception : ");
            Log.errors(this, ex2, "Block Exception : " + ex2.toString());
            throw new Exception("Exception Error : " + ex2.toString());
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
        return string;
    }
    
    public String showPass(String create) {
        create = new MessageDigest(new Secu(1)).create(create);
        return create;
    }
}
