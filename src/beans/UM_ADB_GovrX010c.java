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
import entity.UM_GOE_ConiX020b;

public class UM_ADB_GovrX010c
{
    public UM_GOE_ConiX020b[] UserInfo_list(final int n, final int n2, final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiX020b>();
        Object[] array = null;
        try {
            final String string = "  Select a.명, a.사용자ID, a.마스터코드, a.담당자명, a.승인여부,  N                                   \n    From                                                                                              \n        ( Select a.공공기관명_전체 명, b.사용자ID, b.마스터코드, b.담당자명,  b.승인여부, ROWNUM N    \n            From 사용_공공기관마스터 a, 사용_사용자 b                                                 \n           Where a.공공기관코드 = b.마스터코드                                                        \n             And a.공공기관코드 = '" + s + "'                                                       \n" + "           UNION ALL                                                                                  \n" + "          Select a.상호명 명, b.사용자ID, b.마스터코드, b.담당자명,  b.승인여부, ROWNUM N             \n" + "            From 사용_조달업체마스터 a, 사용_사용자 b                                                 \n" + "           Where a.사업자등록번호 = b.마스터코드                                                      \n" + "             And a.사업자등록번호 = '" + s + "' ) A                                                 \n" + "  WHERE N BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")               \n" + "    AND\tROWNUM < = (" + n + " * " + n2 + ")                                                      \n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_GOE_ConiX020b um_GOE_ConiX020b = new UM_GOE_ConiX020b();
                um_GOE_ConiX020b.setg2bnm(executeQuery.getString(1));
                um_GOE_ConiX020b.setUSER_ID(executeQuery.getString(2));
                um_GOE_ConiX020b.setg2bcode(executeQuery.getString(3));
                um_GOE_ConiX020b.setceoNm(executeQuery.getString(4));
                um_GOE_ConiX020b.setYN(executeQuery.getString(5));
                vector.addElement(um_GOE_ConiX020b);
            }
            array = new UM_GOE_ConiX020b[vector.size()];
            vector.copyInto(array);
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrX010c.UserInfo_list(3'" + s + "',4'" + s2 + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrX010c.UserInfo_list(3'" + s + "',4'" + s2 + "'):" + ex2.toString());
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
        return (UM_GOE_ConiX020b[])array;
    }
    
    public int UserInfo_Count(final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String string = "SELECT COUNT(*)                                                                                   \n  FROM                                                                                            \n    (   Select a.공공기관명_전체 명, b.사용자ID, b.마스터코드, b.담당자명,  b.승인여부, ROWNUM N  \n          From 사용_공공기관마스터 a, 사용_사용자 b                                               \n         Where a.공공기관코드 = b.마스터코드                                                      \n           And a.공공기관코드 = '" + s + "'                                                     \n" + "         union all                                                                                \n" + "        Select a.상호명 명, b.사용자ID, b.마스터코드, b.담당자명,  b.승인여부, ROWNUM N           \n" + "          From 사용_조달업체마스터 a, 사용_사용자 b                                               \n" + "         Where a.사업자등록번호 = b.마스터코드                                                    \n" + "           And a.사업자등록번호 = '" + s + "' )                                                 \n" + "    A                                                                                             \n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrX010c.UserInfo_Count('" + s + "','" + s2 + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrX010c.UserInfo_Count('" + s + "','" + s2 + "'):" + ex2.toString());
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
    
    public UM_GOE_ConiX020b UserInfo_Confirm(final String s) {
        Trx trx = null;
        Connection connection = null;
        UM_GOE_ConiX020b um_GOE_ConiX020b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "  Select a.명, a.사용자ID, a.마스터코드, a.담당자명, a.승인여부,  N                                   \n    From                                                                                              \n        ( Select a.공공기관명_전체 명, b.사용자ID, b.마스터코드, b.담당자명,  b.승인여부, ROWNUM N    \n            From 사용_공공기관마스터 a, 사용_사용자 b                                                 \n           Where a.공공기관코드 = b.마스터코드                                                        \n             And b.사용자ID = '" + s + "'                                                           \n" + "           UNION ALL                                                                                  \n" + "          Select a.상호명 명, b.사용자ID, b.마스터코드, b.담당자명,  b.승인여부, ROWNUM N             \n" + "            From 사용_조달업체마스터 a, 사용_사용자 b                                                 \n" + "           Where a.사업자등록번호 = b.마스터코드                                                      \n" + "             And b.사용자ID = '" + s + "' ) A                                                       \n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                um_GOE_ConiX020b = new UM_GOE_ConiX020b();
                um_GOE_ConiX020b.setg2bnm(executeQuery.getString(1));
                um_GOE_ConiX020b.setUSER_ID(executeQuery.getString(2));
                um_GOE_ConiX020b.setg2bcode(executeQuery.getString(3));
                um_GOE_ConiX020b.setceoNm(executeQuery.getString(4));
                um_GOE_ConiX020b.setYN(executeQuery.getString(5));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrX010c.UserInfo_Confirm('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrX010c.UserInfo_Confirm('" + s + "'):" + ex2.toString());
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
        return um_GOE_ConiX020b;
    }
    
    public UM_GOE_ConiX020b[] UserInfo_DNlist(final String s) {
        Trx trx = null;
        Connection connection = null;
        Object[] array = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiX020b>();
        try {
            final String string = "  Select 사용자ID, 전자서명인증서고유명, to_char(등록일시,'yyyy-mm-dd'), to_char(최종로그인일시,'yyyy-mm-dd')     \n    From 사용_인증서정보                                              \n   WHERE 사용자ID = '" + s + "'                                     \n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiX020b um_GOE_ConiX020b = new UM_GOE_ConiX020b();
                um_GOE_ConiX020b.setUSER_ID(executeQuery.getString(1));
                um_GOE_ConiX020b.setDN(executeQuery.getString(2));
                um_GOE_ConiX020b.setregistration(executeQuery.getString(3));
                um_GOE_ConiX020b.setLastLogin(executeQuery.getString(4));
                vector.addElement(um_GOE_ConiX020b);
            }
            array = new UM_GOE_ConiX020b[vector.size()];
            vector.copyInto(array);
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrX010c.UserInfo_DNlist('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrX010c.UserInfo_DNlist('" + s + "'):" + ex2.toString());
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
        return (UM_GOE_ConiX020b[])array;
    }
    
    public int UserInfo_DNCount(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String string = "SELECT COUNT(*)                             \n    From 사용_인증서정보                    \n   WHERE 사용자ID = '" + s + "'           \n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_GovrX010c.UserInfo_DNCount('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_GovrX010c.UserInfo_DNCount('" + s + "'):" + ex2.toString());
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
