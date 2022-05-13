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
import common.ComStr;
import java.util.Vector;
import entity.UM_GOE_ConiC080b;

public class UM_ADB_ConrH010l
{
    public UM_GOE_ConiC080b[] getUpcheInfo(final int n, final int n2, String replace, final String s, String replace2, final String s2, String replace3, final String s3) throws Exception {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC080b>(1, 1);
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int n3 = 0;
        replace2 = ComStr.replace(replace2, "-", "");
        replace = ComStr.replace(replace, "-", "");
        replace3 = ComStr.replace(replace3, "-", "");
        try {
            String s4 = " select  사업자등록번호, 상호명, 주소,전화번호,나머지주소, N from ( select a.사업자등록번호, a.상호명, a.주소,a.나머지주소, a.전화번호, rownum N from 사용_조달업체마스터 a where 입찰참가자격여부 is null ";
            if (s.length() > 0) {
                s4 += " and 상호명 like '%'||?||'%'";
            }
            if (replace.length() > 0) {
                s4 = s4 + " and 사업자등록번호 like '" + replace + "%'";
            }
            if (replace2.length() > 0) {
                s4 = s4 + " and 법인등록번호 like '" + replace2 + "%'";
            }
            if (s2.length() > 0 || replace3.length() > 0) {
                String s5 = s4 + " and 사업자등록번호 in ( select 사업자등록번호 \t \t\t\t\t   \t   from 사용_대표자  \t\t\t\t\t   \t   where a.사업자등록번호=사업자등록번호 ";
                if (s2.length() > 0) {
                    s5 += " and 대표자명 like ?||'%'";
                }
                if (replace3.length() > 0) {
                    s5 = s5 + " and 대표자주민번호 like '" + replace3 + "%'";
                }
                s4 = s5 + " )";
            }
            if (s3.equals("B")) {
                s4 += "and substr(사업자등록번호, 4, 1) = '8' ";
            }
            if (s3.equals("P")) {
                s4 += "and substr(사업자등록번호, 4, 1) <> '8' ";
            }
            final String string = s4 + " and rownum <= (" + n + " * " + n2 + ") ) sub where N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            if (s.length() > 0) {
                ++n3;
                prepareStatement.setString(n3, s);
            }
            if (s2.length() > 0) {
                ++n3;
                prepareStatement.setString(n3, s2);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiC080b um_GOE_ConiC080b = new UM_GOE_ConiC080b();
                um_GOE_ConiC080b.ett1.setSaupNo(executeQuery.getString(1));
                um_GOE_ConiC080b.ett1.setSangho(executeQuery.getString(2));
                um_GOE_ConiC080b.ett1.setAddr(executeQuery.getString(3));
                um_GOE_ConiC080b.ett1.setTel(executeQuery.getString(4));
                um_GOE_ConiC080b.ett1.setRestAddr(executeQuery.getString(5));
                vector.addElement(um_GOE_ConiC080b);
            }
            UM_GOE_ConiC080b[] array;
            if (vector.size() > 0) {
                array = new UM_GOE_ConiC080b[vector.size()];
                vector.copyInto(array);
            }
            else {
                array = null;
            }
            return array;
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrH010l.getUpcheInfo() : " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrH010l.getUpcheInfo() : " + ex2.toString());
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
    }
    
    public int getUpcheInfoCount(String replace, final String s, String replace2, final String s2, String replace3, final String s3) throws Exception {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector(1, 1);
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int n = 0;
        int int1 = 0;
        replace2 = ComStr.replace(replace2, "-", "");
        replace = ComStr.replace(replace, "-", "");
        replace3 = ComStr.replace(replace3, "-", "");
        try {
            String s4 = "\n select  count(*) from 사용_조달업체마스터 a where 입찰참가자격여부 is null ";
            if (s.length() > 0) {
                s4 += " and 상호명 like '%'||?||'%'";
            }
            if (replace.length() > 0) {
                s4 = s4 + " and 사업자등록번호 like '" + replace + "%'";
            }
            if (replace2.length() > 0) {
                s4 = s4 + " and 법인등록번호 like '" + replace2 + "%'";
            }
            if (s2.length() > 0 || replace3.length() > 0) {
                String s5 = s4 + " and 사업자등록번호 in ( select 사업자등록번호 \t \t\t\t\t   \t   from 사용_대표자  \t\t\t\t\t   \t   where a.사업자등록번호=사업자등록번호 ";
                if (s2.length() > 0) {
                    s5 += " and 대표자명 like ?||'%'";
                }
                if (replace3.length() > 0) {
                    s5 = s5 + " and 대표자주민번호 like '" + replace3 + "%'";
                }
                s4 = s5 + " )";
            }
            if (s3.equals("B")) {
                s4 += "and substr(사업자등록번호, 4, 1) = '8' ";
            }
            if (s3.equals("P")) {
                s4 += "and substr(사업자등록번호, 4, 1) <> '8' ";
            }
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s4);
            if (s.length() > 0) {
                ++n;
                prepareStatement.setString(n, s);
            }
            if (s2.length() > 0) {
                ++n;
                prepareStatement.setString(n, s2);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
            return int1;
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_ConrH010l.getUpcheInfoCount() : " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_ConrH010l.getUpcheInfoCount() : " + ex2.toString());
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
    }
}
