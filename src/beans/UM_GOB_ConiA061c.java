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
import entity.UM_GOE_ConiA061b;

public class UM_GOB_ConiA061c
{
    public UM_GOE_ConiA061b[] sangho_list(final int n, final int n2, final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiA061b>();
        String string = "";
        try {
            string = "  SELECT BIZ_REG_NO, CORP_REG_NO, BIZ_NM, ZIP_CD, ADDR, DETAIL_ADDR, REPR_IDENT_NO, REPR_NM, N   FROM\t\t(SELECT A.BIZ_REG_NO, A.CORP_REG_NO, A.BIZ_NM, A.ZIP_CD, A.ADDR, A.DETAIL_ADDR, B.REPR_IDENT_NO, B.REPR_NM, ROWNUM N \t\t   FROM UM_SUPPLIER_ENTER_MAST A, UM_REPR B \t\t  WHERE A.BIZ_REG_NO = B.BIZ_REG_NO \t\t\tAND A.BIZ_REG_NO LIKE '%'||'" + s + "'||'%') " + " WHERE N BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")" + "   AND\tROWNUM < = (" + n + " * " + n2 + ")";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            Log.debug("UM_GOB_ConiA061c.sangho_list() : " + string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA061b um_GOE_ConiA061b = new UM_GOE_ConiA061b();
                um_GOE_ConiA061b.setsaupNo(executeQuery.getString(1));
                um_GOE_ConiA061b.setregistrationNo(executeQuery.getString(2));
                um_GOE_ConiA061b.setsangho(executeQuery.getString(3));
                um_GOE_ConiA061b.setpostCode(executeQuery.getString(4));
                um_GOE_ConiA061b.setaddress1(executeQuery.getString(5));
                um_GOE_ConiA061b.setaddress2(executeQuery.getString(6));
                um_GOE_ConiA061b.setceoJuminNo(executeQuery.getString(7));
                um_GOE_ConiA061b.setceoNm(executeQuery.getString(8));
                vector.addElement(um_GOE_ConiA061b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiA061c.sangho_list() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiA061c.sangho_list() : sql : " + string + "::: " + ex2.toString());
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
        final UM_GOE_ConiA061b[] array = new UM_GOE_ConiA061b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_GOE_ConiA061b[] saup_list(final int n, final int n2, final String s, final String s2) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiA061b>();
        String string = "";
        try {
            string = "  SELECT BIZ_REG_NO, BIZ_NM, N\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n     FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n    (SELECT A.BIZ_REG_NO, A.BIZ_NM,\tROWNUM N\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n        FROM UM_SUPPLIER_ENTER_MAST A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  \t WHERE A.BIZ_REG_NO = '" + s + "')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" + "   WHERE N BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")       \n" + "       AND\tROWNUM < = (" + n + " * " + n2 + ")\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA061b um_GOE_ConiA061b = new UM_GOE_ConiA061b();
                um_GOE_ConiA061b.setsaupNo(executeQuery.getString(1));
                um_GOE_ConiA061b.setsangho(executeQuery.getString(2));
                vector.addElement(um_GOE_ConiA061b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiA061c.saup_list() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiA061c.saup_list() : sql : " + string + "::: " + ex2.toString());
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
        final UM_GOE_ConiA061b[] array = new UM_GOE_ConiA061b[vector.size()];
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
            string = " SELECT COUNT(*)   FROM UM_SUPPLIER_ENTER_MAST A  WHERE A.BIZ_REG_NO like '%'||'" + s + "'||'%'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiA061c.Max_count() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiA061c.Max_count() : sql : " + string + "::: " + ex2.toString());
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
    
    public UM_GOE_ConiA061b[] License_list(final int n, final int n2, final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final Vector vector = new Vector<UM_GOE_ConiA061b>();
        String string = "";
        try {
            string = "  SELECT LICENSE_NO, N  FROM                                    \n    (SELECT LICENSE_NO, ROWNUM N FROM UM_LICENSE_FACTS A           \n  \tWHERE A.BIZ_REG_NO = '" + s + "'                 \n" + "  \t  AND ROWNUM <= (" + n + " * " + n2 + "))            \n" + "  WHERE N BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")                                       \n" + "    AND\tROWNUM < = (" + n + " * " + n2 + ")              \n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiA061b um_GOE_ConiA061b = new UM_GOE_ConiA061b();
                um_GOE_ConiA061b.setlicenseNo(executeQuery.getString(1));
                vector.addElement(um_GOE_ConiA061b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiA061c.License_list() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiA061c.License_list() : sql : " + string + "::: " + ex2.toString());
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
        final UM_GOE_ConiA061b[] array = new UM_GOE_ConiA061b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_Licensecount(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        String string = "";
        try {
            string = " SELECT COUNT(*) FROM UM_LICENSE_FACTS                   \n  WHERE BIZ_REG_NO = '" + s + "'               \n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiA061c.Max_Licensecount() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiA061c.Max_Licensecount() : sql : " + string + "::: " + ex2.toString());
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
