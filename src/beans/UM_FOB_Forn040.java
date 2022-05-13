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
import entity.UM_RAV_IncuA060b;

public class UM_FOB_Forn040
{
    public UM_RAV_IncuA060b select_id(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        UM_RAV_IncuA060b um_RAV_IncuA060b = null;
        try {
            final String string = "SELECT count(*) FROM 사용_조달업체마스터 WHERE 사업자등록번호 ='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_RAV_IncuA060b = new UM_RAV_IncuA060b();
                um_RAV_IncuA060b.setS01(executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn040.select_id('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn040.select_id('" + s + "'):" + ex2.toString());
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
        return um_RAV_IncuA060b;
    }
    
    public UM_RAV_IncuA060b select_id1(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        UM_RAV_IncuA060b um_RAV_IncuA060b = null;
        try {
            final String string = " SELECT count(*) FROM 사용_전자문서상태  WHERE 사업자등록번호 ='" + s + "' " + "   AND 신청_변경구분 = '1' " + "   AND 진행상태 IN ('0','2') ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_RAV_IncuA060b = new UM_RAV_IncuA060b();
                um_RAV_IncuA060b.setS01(executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn040.select_id('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn040.select_id('" + s + "'):" + ex2.toString());
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
        return um_RAV_IncuA060b;
    }
}
