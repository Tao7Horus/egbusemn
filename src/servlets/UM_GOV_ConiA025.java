// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.SQLException;
import common.Log;
import oracle.xml.parser.v2.DOMParser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import common.Trx;
import java.sql.Connection;

public class UM_GOV_ConiA025
{
    Connection con;
    Trx trx;
    ResultSet rs;
    PreparedStatement psmt;
    StringBuffer sb;
    String sql;
    DOMParser parser;
    
    public UM_GOV_ConiA025() {
        this.rs = null;
        this.psmt = null;
        this.sb = new StringBuffer();
        this.sql = null;
        this.parser = new DOMParser();
    }
    
    public UM_GOV_ConiA021c getBuyer(final String s, final String s2) {
        final UM_GOV_ConiA021c um_GOV_ConiA021c = new UM_GOV_ConiA021c();
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        sb.append(" SELECT\tA.INSTITU_FULL_NM,\tB.CHRGR_NM,\tB.CHRGR_PHONE_NO\t\t\n");
        sb.append(" FROM\tUM_PUB_INSTITU_MAST A, UM_USER  B\t\t\t\t\t\n");
        sb.append(" WHERE\tA.INSTITU_CD = B.MAST_CD\t\t\t\t\t\t\t\n");
        sb.append("   AND\tA.INSTITU_CD = '" + s + "'\t\t\t\t\t\t\t\n");
        sb.append("   AND\tB.USER_ID = '" + s2 + "'\t\t\t        \t\t\t\t\n");
        final String string = sb.toString();
        try {
            this.trx = new Trx(this, "usemn");
            this.con = this.trx.getConnection();
            this.psmt = this.con.prepareStatement(string);
            executeQuery = this.psmt.executeQuery();
            this.psmt.clearParameters();
            if (executeQuery.next()) {
                um_GOV_ConiA021c.INPUT_UM_ID[0] = s2;
                um_GOV_ConiA021c.INSTITU_NM[0] = executeQuery.getString(1);
                um_GOV_ConiA021c.INSTITU_DIRECTOR_NM[0] = executeQuery.getString(1);
                um_GOV_ConiA021c.CHRG_NM[0] = executeQuery.getString(2);
                um_GOV_ConiA021c.CHRG_PHONE_NO[0] = executeQuery.getString(3);
                um_GOV_ConiA021c.INSTITU_CD[0] = s;
            }
            executeQuery.close();
            this.psmt.close();
            this.con.close();
        }
        catch (SQLException ex) {
            Log.debug("RcvDfisan.select_user block SQLException : ");
            Log.debug("Loi xay ra : " + ex.toString() + ex.getErrorCode() + ex.getSQLState());
        }
        catch (Exception ex2) {
            Log.errors("Loi xay ra : " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (this.psmt != null) {
                try {
                    this.psmt.close();
                }
                catch (Exception ex4) {}
            }
            if (this.trx != null) {
                try {
                    this.trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return um_GOV_ConiA021c;
    }
}
