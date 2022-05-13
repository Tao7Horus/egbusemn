// 
// Decompiled by Procyon v0.5.30
// 

package common.util;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class CommServer
{
    public String getCurrDate(final int nBefore, final int format) throws Exception {
        Trx resource = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String result = null;
        String sql = null;
        Label_0291: {
            try {
                if (format == 0) {
                    sql = "select to_char(sysdate +" + nBefore + ", 'yyyy-mm-dd') from dual";
                }
                resource = new Trx(this, "usemn");
                conn = resource.getConnection();
                stmt = conn.createStatement();
                rset = stmt.executeQuery(sql);
                if (rset.next()) {
                    result = rset.getString(1);
                    break Label_0291;
                }
                throw new Exception("ResultSet 이 Null 임");
            }
            catch (SQLException exc) {
                Log.debug("CommServer.getCurrDate('" + Integer.toString(nBefore) + "','" + Integer.toString(format) + "'):" + exc.toString());
                throw new Exception(exc.getMessage());
            }
            catch (Exception exc2) {
                Log.debug("CommServer.getCurrDate('" + Integer.toString(nBefore) + "','" + Integer.toString(format) + "'):" + exc2.toString());
                throw new Exception(exc2.getMessage());
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                if (conn != null) {
                    try {
                        resource.close();
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        if (rset != null) {
            try {
                rset.close();
            }
            catch (Exception ex4) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        if (conn != null) {
            try {
                resource.close();
            }
            catch (Exception ex6) {}
        }
        return result;
    }
}
