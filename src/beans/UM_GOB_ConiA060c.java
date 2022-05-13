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
import java.util.Vector;
import entity.UM_GOE_ConiA060b;

public class UM_GOB_ConiA060c
{
    public UM_GOE_ConiA060b[] sangho_list(final int n, final int n2, final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiA060b>();
        String string = "";
        try {
            string = "select 사업자등록번호, 상호명, 주소, 대표자주민번호, 대표자명 from (SELECT A.사업자등록번호, A.상호명, (A.주소||' '||A.나머지주소) 주소, B.대표자주민번호, B.대표자명, ROWNUM N from 사용_조달업체마스터 A, 사용_대표자 B where A.사업자등록번호 = B.사업자등록번호 AND A.상호명 LIKE '%" + s + "%' AND A.사업자등록번호 LIKE '" + s2 + "%') " + "where N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA060b um_GOE_ConiA060b = new UM_GOE_ConiA060b();
                um_GOE_ConiA060b.setsaupNo(executeQuery.getString(1));
                um_GOE_ConiA060b.setsangho(executeQuery.getString(2));
                um_GOE_ConiA060b.setaddr(executeQuery.getString(3));
                um_GOE_ConiA060b.setceoJuminNo(executeQuery.getString(4));
                um_GOE_ConiA060b.setceoName(executeQuery.getString(5));
                vector.addElement(um_GOE_ConiA060b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiA060c.sangho_list() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiA060c.sangho_list() : sql : " + string + "::: " + ex2.toString());
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
        final UM_GOE_ConiA060b[] array = new UM_GOE_ConiA060b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count(final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        String string = "";
        try {
            string = "SELECT count(*) from 사용_조달업체마스터 A, 사용_대표자 B where A.사업자등록번호 = B.사업자등록번호 AND A.상호명 LIKE '%" + s + "%' AND A.사업자등록번호 LIKE '" + s2 + "%'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiA060c.Max_count() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiA060c.Max_count() : sql : " + string + "::: " + ex2.toString());
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
