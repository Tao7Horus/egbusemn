// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import common.Trx;
import entity.UM_URB_MyPageInfo;
import java.util.Vector;
import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;

public class UM_URB_MyPage
{
    public void insertMypage(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        try {
            Log.debug("[UM_URB_MyPage] user_id : '" + s);
            Log.debug("[UM_URB_MyPage] d01 : '" + s2);
            prepareStatement = connection.prepareStatement(" INSERT INTO syn_포탈_마이페이지  (사용자ID, 소속구분, 등록일) VALUES ('" + s + "', '" + s2 + "', SYSDATE )");
            prepareStatement.executeUpdate();
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void insertPageset(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        try {
            prepareStatement = connection.prepareStatement(" INSERT INTO syn_포탈_페이지선택  (사용자ID, 선택코드) VALUES ('" + s + "', '" + s2 + "')");
            prepareStatement.executeUpdate();
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public Vector getMysetSelect(final String s, final String s2) throws Exception {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final Vector<UM_URB_MyPageInfo> vector = new Vector<UM_URB_MyPageInfo>();
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(" SELECT NVL(b.사용자ID,NULL) 사용자ID, a.선택코드 선택코드, a.코드명 코드명" + "   FROM syn_포탈_페이지 a," + "       ( SELECT a.사용자ID 사용자ID, a.선택코드 선택코드" + "           FROM syn_포탈_페이지선택 a, syn_포탈_페이지 b" + "          WHERE a.선택코드 = b.선택코드" + "            AND a.사용자ID = '" + s + "' " + "            AND a.선택코드 LIKE '" + s2 + "%' ) b" + "  WHERE a.선택코드 = b.선택코드(+)" + "    AND a.선택코드 NOT IN ('BA05','PA03') " + "    AND a.선택코드 LIKE '" + s2 + "%'");
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_URB_MyPageInfo um_URB_MyPageInfo = new UM_URB_MyPageInfo();
                if (executeQuery.getString("사용자ID") != null) {
                    um_URB_MyPageInfo.user_id = executeQuery.getString("사용자ID");
                }
                else {
                    um_URB_MyPageInfo.user_id = "";
                }
                um_URB_MyPageInfo.select_code = executeQuery.getString("선택코드");
                um_URB_MyPageInfo.code_name = executeQuery.getString("코드명");
                vector.addElement(um_URB_MyPageInfo);
            }
        }
        catch (Exception ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                }
                catch (Exception ex2) {}
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                }
                catch (Exception ex3) {}
            }
            Log.debug(this.getClass().getName() + ".getMysetSelect() user_id[" + s + "],code_div[" + s2 + "]" + ex.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex4) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex5) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex6) {}
            }
        }
        return vector;
    }
    
    public boolean isMypage(final String s, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        boolean b = false;
        try {
            prepareStatement = connection.prepareStatement(" SELECT count(*)    FROM syn_포탈_마이페이지   WHERE 사용자ID = '" + s + "' ");
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next() && executeQuery.getInt(1) > 0) {
                b = true;
            }
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex2) {}
            }
        }
        return b;
    }
    
    public int getMyMessageExist(final String s, final String s2) throws Exception {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        int int1 = 0;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(" SELECT count(*) cnt    FROM syn_포탈_페이지선택   WHERE 사용자ID = '" + s + "' " + "    AND 선택코드 LIKE '" + s2 + "%' ");
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt("cnt");
            }
        }
        catch (Exception ex) {
            if (connection != null) {
                try {
                    connection.rollback();
                }
                catch (Exception ex2) {}
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                }
                catch (Exception ex3) {}
            }
            Log.debug(this.getClass().getName() + ".getMyMessageExist() user_id[" + s + "],code[" + s2 + "]" + ex.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex4) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex5) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex6) {}
            }
        }
        return int1;
    }
    
    public void deletePageset(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        String s3 = "";
        try {
            if (s2.equals("g")) {
                s3 = " DELETE FROM syn_포탈_페이지선택  WHERE 사용자ID = '" + s + "'" + "   AND (선택코드 like 'E%' or 선택코드 like 'PD%')  ";
            }
            else if (s2.equals("c")) {
                s3 = " DELETE FROM syn_포탈_페이지선택  WHERE 사용자ID = '" + s + "'" + "   AND (선택코드 like 'E%' or 선택코드 like 'BD%')  ";
            }
            prepareStatement = connection.prepareStatement(s3);
            prepareStatement.executeUpdate();
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex) {}
            }
        }
    }
}
