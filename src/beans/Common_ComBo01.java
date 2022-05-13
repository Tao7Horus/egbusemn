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

public class Common_ComBo01
{
    public String getCode(final String s, final String s2, final String s3, final String s4) throws Exception {
        Connection connection = null;
        Trx trx = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append("SELECT 코드, 코드명 FROM SYN_공동코드                                                    \n");
            sb.append(" WHERE 코드구분 = 'GUJ'                                                                  \n");
            sb.append(" AND 코드 IN('83','32','38','24','23','21','12','87','81','22','35','39','33','25','00') \n");
            sb.append(" ORDER BY 코드 ASC                                                                       \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            executeQuery = prepareStatement.executeQuery();
            return this.format(executeQuery, s, s2, s3, s4);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("SQLException : Common_ComBo01.java's getCode(opt=" + s + "/name=" + s2 + "/code=" + s3 + "/code_nm" + s4 + "/) : message = " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("Exception : Common_ComBo01.java's getCode(opt=" + s + "/name=" + s2 + "/code=" + s3 + "/code_nm" + s4 + "/) : message = " + ex2.getMessage());
            throw new Exception(ex2.getMessage());
        }
        finally {
            sb.setLength(0);
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
    
    public String getCode2(final String s, final String s2, final String s3, final String s4) throws Exception {
        Connection connection = null;
        Trx trx = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append("SELECT 코드, 코드명 FROM SYN_공동코드                                                    \n");
            sb.append(" WHERE 코드구분 = 'J03'                                                                  \n");
            sb.append(" AND 사용여부 ='Y'                                                                       \n");
            sb.append(" AND 코드 IN('83','32','38','24','23','21','12','87','81','22','35','39','33','25','00') \n");
            sb.append(" ORDER BY 코드 ASC                                                                       \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            executeQuery = prepareStatement.executeQuery();
            return this.format(executeQuery, s, s2, s3, s4);
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("SQLException : Common_ComBo01.java's getCode2(opt=" + s + "/name=" + s2 + "/code=" + s3 + "/code_nm" + s4 + "/) : message = " + ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("Exception : Common_ComBo01.java's getCode2(opt=" + s + "/name=" + s2 + "/code=" + s3 + "/code_nm" + s4 + "/) : message = " + ex2.getMessage());
            throw new Exception(ex2.getMessage());
        }
        finally {
            sb.setLength(0);
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
    
    private String format(final ResultSet set, final String s, final String s2, final String s3, final String s4) throws SQLException {
        final StringBuffer sb = new StringBuffer();
        if (s.equals("M")) {
            sb.append("<select type=select name='" + s2 + "'>");
            while (set.next()) {
                final String string = set.getString("코드");
                final String string2 = set.getString("코드명");
                if (s4.equals(string)) {
                    sb.append("<option value='" + string + "' selected>").append(string2).append("</option>");
                }
                else {
                    sb.append("<option value='" + string + "'>").append(string2).append("</option>");
                }
            }
            sb.append("</select>");
        }
        else if (s.equals("S")) {
            sb.append("<select type=select name='" + s2 + "'>");
            while (set.next()) {
                sb.append("<option value='" + set.getString("코드") + "'>").append(set.getString("코드명")).append("</option>");
            }
            sb.append("</select>");
        }
        else if (s.equals("T")) {
            while (set.next()) {
                final String string3 = set.getString("코드");
                final String string4 = set.getString("코드명");
                if (s4.equals(string3)) {
                    sb.append(string4);
                }
            }
        }
        final String string5 = sb.toString();
        sb.setLength(0);
        return string5;
    }
}
