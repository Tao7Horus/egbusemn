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
import entity.UM_ADJ_GovuA020b;

public class UM_RAB_GovuL010p
{
    public UM_ADJ_GovuA020b select_goma(final String s) throws Exception, SQLException {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        UM_ADJ_GovuA020b um_ADJ_GovuA020b = null;
        try {
            final String string = "select 접수번호,공공기관코드,공공기관명_전체,공공기관명_영문,대표자,대표자주민번호,우편번호,주소,나머지주소,사업자등록번호,팩스번호,담당자명, 담당자부서명, 담당자주민번호,담당자전화번호,담당자메일주소,핸드폰,사용자ID,닉네임,공인인증기관,홈페이지\tfrom 사용_접수공공기관인증서\twhere 접수번호='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ADJ_GovuA020b = new UM_ADJ_GovuA020b();
                um_ADJ_GovuA020b.setrecept(executeQuery.getString(1));
                um_ADJ_GovuA020b.setG2BCODE(executeQuery.getString(2));
                um_ADJ_GovuA020b.setCNAME(executeQuery.getString(3));
                um_ADJ_GovuA020b.setKNAME(executeQuery.getString(4));
                um_ADJ_GovuA020b.setIDENT(executeQuery.getString(5));
                um_ADJ_GovuA020b.setIDENTJUMIN(executeQuery.getString(6));
                um_ADJ_GovuA020b.setZIPNO(executeQuery.getString(7));
                um_ADJ_GovuA020b.setADDRS(executeQuery.getString(8));
                um_ADJ_GovuA020b.setADDRESS2(executeQuery.getString(9));
                um_ADJ_GovuA020b.setCOMNO(executeQuery.getString(10));
                um_ADJ_GovuA020b.setFAX(executeQuery.getString(11));
                um_ADJ_GovuA020b.setMYNAME(executeQuery.getString(12));
                um_ADJ_GovuA020b.setOFFICEDE(executeQuery.getString(13));
                um_ADJ_GovuA020b.setJUMIN(executeQuery.getString(14));
                um_ADJ_GovuA020b.setTEL(executeQuery.getString(15));
                um_ADJ_GovuA020b.setEMAIL(executeQuery.getString(16));
                um_ADJ_GovuA020b.setHP(executeQuery.getString(17));
                um_ADJ_GovuA020b.setUSRID(executeQuery.getString(18));
                um_ADJ_GovuA020b.setSTR(executeQuery.getString(19));
                um_ADJ_GovuA020b.setCERT_ORG(executeQuery.getString(20));
                um_ADJ_GovuA020b.setHOME(executeQuery.getString(21));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_GovuL010p.select_goma('" + s + "'):" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_GovuL010p.select_goma('" + s + "'):" + ex2.toString());
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
        return um_ADJ_GovuA020b;
    }
    
    public int Max_count(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String s2 = "\n SELECT count(*) FROM 사용_접수공공기관인증서 WHERE 접수번호=?";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_GovuL010p.Max_count('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_GovuL010p.Max_count('" + s + "'):" + ex2.toString());
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
