// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class RegADBean
{
    public void addFile(String revc, String bizregNo, String filename, String strDesc, String filetype) {
      
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this);
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("INSERT INTO \"ENTRYBID\".\"UM_REC_PUB_SUP_File_STD\" (RECV_NO, BIZ_REG_NO, FILE_NAME, INPUT_DATE, \"DESC\", FILETYPE) VALUES (?,?,?, sysdate,?,?)");
            prepareStatement.setString(1, revc.trim());
            prepareStatement.setString(2, bizregNo.trim());
            prepareStatement.setString(3, filename.trim());
            prepareStatement.setString(4, strDesc.trim());
            prepareStatement.setString(5, filetype.trim());
            executeQuery = prepareStatement.executeQuery();
          
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
       
    }
    
    public String getFilename(String revc) {
        
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet rs = null;
        try {
            trx = new Trx(this);
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("SELECT FILE_NAME FROM  \"ENTRYBID\".\"UM_REC_PUB_SUP_File_STD\" WHERE RECV_NO = ? AND rownum = 1 ORDER BY INPUT_DATE DESC");
            prepareStatement.setString(1, revc.trim());         
            rs = prepareStatement.executeQuery();
            while (rs.next()) {
				return rs.getString(1); 
			}
        }
        catch (SQLException ex) {
            Log.debug(ex.toString());
        }
        catch (Exception ex2) {
            Log.debug(ex2.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
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
       return "";
    }
      
}
