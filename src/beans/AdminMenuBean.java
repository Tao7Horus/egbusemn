// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import common.Log;
import java.util.ArrayList;
import common.Trx;

public class AdminMenuBean
{
    String sqlToQuery;
    
    public AdminMenuBean() {
        this.sqlToQuery = "\n SELECT   V1.메뉴순번     \n         ,T1.MENU_CODE ,T1.MENU_NAME ,T1.MENU_URL ,T1.REG_DATE ,T1.IS_USED   \n         ,T1.REG_USER  ,T1.PARENT_MENU_CODE ,T1.MOVE_TARGET \n FROM    사용_운영자_메뉴항목 T1  \n    ,   (   SELECT    MENU_CODE  \n                    , CASE WHEN U_ORDER IS NOT NULL THEN U_ORDER \n                           WHEN G_ORDER IS NOT NULL THEN G_ORDER  \n                          WHEN UR_ORDER IS NOT NULL THEN UR_ORDER \n                           WHEN GR_ORDER IS NOT NULL THEN  GR_ORDER  \n                          ELSE NULL \n                      END \"메뉴순번\" \n            FROM (  SELECT MENU_CODE  \n                   ,      MIN(GR_ORDER) AS GR_ORDER  \n                   ,      MIN(UR_ORDER) AS UR_ORDER \n                   ,      MIN(G_ORDER)  AS G_ORDER \n                   ,      MIN(U_ORDER)  AS U_ORDER \n                    FROM (  SELECT 'GR' as category ,MENU_CODE , 메뉴순번 AS GR_ORDER , NULL AS UR_ORDER ,NULL AS G_ORDER ,NULL AS U_ORDER   \n                            FROM   사용_운영자_메뉴권한    \n                            WHERE  PRIVS_TYPE = 'D'    \n                            AND    ID IN (Select MENU_CODE    \n                                          FROM   사용_운영자_메뉴권한 T1   \n                                          WHERE  T1.PRIVS_TYPE = 'GR'   \n                                          AND    T1.ID         =  ?   \n                                          AND    T1.IS_USED    = 'Y')   \n                            AND    IS_USED = 'Y'   \n                            UNION    \n                           SELECT 'UR' as category ,MENU_CODE , NULL AS GR_ORDER , 메뉴순번 AS UR_ORDER ,NULL AS G_ORDER ,NULL AS U_ORDER   \n                            FROM   사용_운영자_메뉴권한    \n                            WHERE  PRIVS_TYPE = 'D'    \n                            AND    ID IN (Select MENU_CODE    \n                                          FROM   사용_운영자_메뉴권한 T1  \n                                          WHERE  T1.PRIVS_TYPE = 'UR'   \n                                          AND    T1.ID         =  ?    \n                                          AND    T1.IS_USED    = 'Y')   \n                            AND    IS_USED = 'Y'  \n                            UNION    \n                           SELECT 'G' as category ,MENU_CODE ,NULL AS GR_ORDER , NULL AS UR_ORDER , 메뉴순번 AS G_ORDER ,NULL AS U_ORDER    \n                            FROM   사용_운영자_메뉴권한   \n                            WHERE  PRIVS_TYPE = 'G'   \n                            AND    ID         =  ?    \n                            AND    IS_USED    = 'Y'   \n                            UNION    \n                            SELECT 'U' as category ,MENU_CODE ,NULL AS GR_ORDER , NULL AS UR_ORDER , NULL AS G_ORDER ,메뉴순번 AS U_ORDER    \n                            FROM   사용_운영자_메뉴권한   \n                            WHERE  PRIVS_TYPE = 'U'   \n                            AND    ID         =  ?  \n                            AND    IS_USED    = 'Y'  ) \n                   GROUP BY MENU_CODE )) V1 \n WHERE   T1.MENU_CODE = V1.MENU_CODE  \n ORDER BY V1.메뉴순번   ";
    }
    
    public AdminMenuBean.MenuItem[] getMenuItem(final String s, final String s2) throws NullPointerException, SQLException, Exception {
        if (s == null) {
            throw new NullPointerException("userID로 null이 넘어왔습니다.");
        }
        if (s2 == null) {
            throw new NullPointerException("depCode로 null이 넘어왔습니다.");
        }
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(this.sqlToQuery);
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s);
            prepareStatement.setString(3, s2);
            prepareStatement.setString(4, s);
            executeQuery = prepareStatement.executeQuery();
            final ArrayList list = new ArrayList<AdminMenuBean.MenuItem>();
            while (executeQuery.next()) {
                list.add(this.getFromResultSet(executeQuery));
            }
            final AdminMenuBean.MenuItem[] array = new AdminMenuBean.MenuItem[list.size()];
            list.toArray(array);
            return array;
        }
        catch (SQLException ex) {
            Log.debug(this.getClass().getName() + ".getMenuItem(String):" + ex.toString() + "usedSql:" + this.sqlToQuery + "sqlState" + ex.getSQLState());
            throw ex;
        }
        catch (Exception ex2) {
            final StringWriter stringWriter = new StringWriter();
            ex2.printStackTrace(new PrintWriter((Writer)stringWriter));
            stringWriter.close();
            Log.debug(this.getClass().getName() + ".getMenuItem(String):" + ex2.toString() + stringWriter.toString());
            throw ex2;
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
    
    private AdminMenuBean.MenuItem getFromResultSet(final ResultSet set) throws SQLException {
        final AdminMenuBean.MenuItem menuItem = new AdminMenuBean.MenuItem();
        menuItem.setMenuID(set.getString("MENU_CODE"));
        menuItem.setMenuName(set.getString("MENU_NAME"));
        menuItem.setMenuUrl(set.getString("MENU_URL"));
        menuItem.setRegDate(set.getTimestamp("REG_DATE"));
        menuItem.setUsed(set.getString("IS_USED") != null && set.getString("IS_USED").equals("Y"));
        menuItem.setRegUser(set.getString("REG_USER"));
        menuItem.setParentMenuCode(set.getString("PARENT_MENU_CODE"));
        menuItem.setMoveTarget(set.getString("MOVE_TARGET"));
        return menuItem;
    }
}
