// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_FOE_CommInfo;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.util.Vector;
import entity.UM_FOE_CommInfo2;
import java.sql.Connection;

public class UM_FOB_Forn062
{
    public UM_FOE_CommInfo2[] getList(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final Vector vector = new Vector<UM_FOE_CommInfo2>(1, 1);
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            final int columnCount = executeQuery.getMetaData().getColumnCount();
            while (executeQuery.next()) {
                final UM_FOE_CommInfo2 um_FOE_CommInfo2 = new UM_FOE_CommInfo2();
                um_FOE_CommInfo2.dataArraySetting(columnCount);
                for (int i = 1; i <= columnCount; ++i) {
                    um_FOE_CommInfo2.data[i - 1] = executeQuery.getString(i);
                }
                vector.addElement(um_FOE_CommInfo2);
            }
            final UM_FOE_CommInfo2[] array = new UM_FOE_CommInfo2[vector.size()];
            vector.copyInto(array);
            return array;
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn061.getList():caller");
            Log.debug("UM_FOB_Forn061.getList():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn061.getList():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
    }
    
    public int getCount(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            if (!executeQuery.next()) {
                throw new Exception("ResultSet Null 리턴");
            }
            final int int1 = executeQuery.getInt(1);
            if (executeQuery.wasNull()) {
                throw new Exception("Null 리턴됨");
            }
            return int1;
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn061.getList():caller");
            Log.debug("UM_FOB_Forn061.getList():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn061.getCount():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
    }
    
    public UM_FOE_CommInfo getFornUpche(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        UM_FOE_CommInfo um_FOE_CommInfo = null;
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                um_FOE_CommInfo = new UM_FOE_CommInfo();
                um_FOE_CommInfo.setString1(executeQuery.getString(1));
                um_FOE_CommInfo.setString2(executeQuery.getString(2));
                um_FOE_CommInfo.setString3(executeQuery.getString(3));
                um_FOE_CommInfo.setString4(executeQuery.getString(4));
                um_FOE_CommInfo.setString5(executeQuery.getString(5));
                um_FOE_CommInfo.setString6(executeQuery.getString(6));
                um_FOE_CommInfo.setString7(executeQuery.getString(7));
                um_FOE_CommInfo.setString8(executeQuery.getString(8));
                um_FOE_CommInfo.setString9(executeQuery.getString(9));
                um_FOE_CommInfo.setString10(executeQuery.getString(10));
                um_FOE_CommInfo.setString11(executeQuery.getString(11));
                um_FOE_CommInfo.setString12(executeQuery.getString(12));
                um_FOE_CommInfo.setString13(executeQuery.getString(13));
                um_FOE_CommInfo.setString14(executeQuery.getString(14));
                um_FOE_CommInfo.setString15(executeQuery.getString(15));
                um_FOE_CommInfo.setString16(executeQuery.getString(16));
                um_FOE_CommInfo.setString17(executeQuery.getString(17));
                um_FOE_CommInfo.setString18(executeQuery.getString(18));
                um_FOE_CommInfo.setString19(executeQuery.getString(19));
                um_FOE_CommInfo.setString20(executeQuery.getString(20));
                um_FOE_CommInfo.setString21(executeQuery.getString(21));
                um_FOE_CommInfo.setString22(executeQuery.getString(22));
                um_FOE_CommInfo.setString23(executeQuery.getString(23));
                um_FOE_CommInfo.setString24(executeQuery.getString(24));
                um_FOE_CommInfo.setString25(executeQuery.getString(25));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn062.getFornUpche():caller");
            Log.debug("UM_FOB_Forn062.getFornUpche():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn062.getFornUpche():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
        return um_FOE_CommInfo;
    }
    
    public UM_FOE_CommInfo getFornUpcheE(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        UM_FOE_CommInfo um_FOE_CommInfo = null;
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                um_FOE_CommInfo = new UM_FOE_CommInfo();
                um_FOE_CommInfo.setString1(executeQuery.getString(1));
                um_FOE_CommInfo.setString2(executeQuery.getString(2));
                um_FOE_CommInfo.setString3(executeQuery.getString(3));
                um_FOE_CommInfo.setString4(executeQuery.getString(4));
                um_FOE_CommInfo.setString5(executeQuery.getString(5));
                um_FOE_CommInfo.setString6(executeQuery.getString(6));
                um_FOE_CommInfo.setString7(executeQuery.getString(7));
                um_FOE_CommInfo.setString8(executeQuery.getString(8));
                um_FOE_CommInfo.setString9(executeQuery.getString(9));
                um_FOE_CommInfo.setString10(executeQuery.getString(10));
                um_FOE_CommInfo.setString11(executeQuery.getString(11));
                um_FOE_CommInfo.setString12(executeQuery.getString(12));
                um_FOE_CommInfo.setString13(executeQuery.getString(13));
                um_FOE_CommInfo.setString14(executeQuery.getString(14));
                um_FOE_CommInfo.setString15(executeQuery.getString(15));
                um_FOE_CommInfo.setString16(executeQuery.getString(16));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn062.getFornUpche():caller");
            Log.debug("UM_FOB_Forn062.getFornUpche():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn062.getFornUpche():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
        return um_FOE_CommInfo;
    }
    
    public UM_FOE_CommInfo[] getDaepyo(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        UM_FOE_CommInfo[] array = null;
        final Vector vector = new Vector<UM_FOE_CommInfo>();
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_FOE_CommInfo um_FOE_CommInfo = new UM_FOE_CommInfo();
                um_FOE_CommInfo.setString1(executeQuery.getString(1));
                um_FOE_CommInfo.setString2(executeQuery.getString(2));
                um_FOE_CommInfo.setString3(executeQuery.getString(3));
                um_FOE_CommInfo.setString4(executeQuery.getString(4));
                vector.addElement(um_FOE_CommInfo);
            }
            array = new UM_FOE_CommInfo[vector.size()];
            vector.copyInto(array);
            vector.removeAllElements();
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn062.getDaepyo():caller");
            Log.debug("UM_FOB_Forn062.getDaepyo():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn062.getDaepyo():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
        return array;
    }
    
    public UM_FOE_CommInfo[] getMulpum(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        UM_FOE_CommInfo[] array = null;
        final Vector vector = new Vector<UM_FOE_CommInfo>();
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_FOE_CommInfo um_FOE_CommInfo = new UM_FOE_CommInfo();
                um_FOE_CommInfo.setString1(executeQuery.getString(1));
                um_FOE_CommInfo.setString2(executeQuery.getString(2));
                um_FOE_CommInfo.setString3(executeQuery.getString(3));
                um_FOE_CommInfo.setString4(executeQuery.getString(4));
                vector.addElement(um_FOE_CommInfo);
            }
            array = new UM_FOE_CommInfo[vector.size()];
            vector.copyInto(array);
            vector.removeAllElements();
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn062.getMulpum():caller");
            Log.debug("UM_FOB_Forn062.getMulpum():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn062.getMulpum():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
        return array;
    }
    
    public UM_FOE_CommInfo[] getMulpumBInfo(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        UM_FOE_CommInfo[] array = null;
        final Vector vector = new Vector<UM_FOE_CommInfo>();
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_FOE_CommInfo um_FOE_CommInfo = new UM_FOE_CommInfo();
                um_FOE_CommInfo.setString1(executeQuery.getString(1));
                um_FOE_CommInfo.setString2(executeQuery.getString(2));
                um_FOE_CommInfo.setString3(executeQuery.getString(3));
                vector.addElement(um_FOE_CommInfo);
            }
            array = new UM_FOE_CommInfo[vector.size()];
            vector.copyInto(array);
            vector.removeAllElements();
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn062.getMulpumBInfo():caller");
            Log.debug("UM_FOB_Forn062.getMulpumBInfo():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn062.getMugetMulpumBInfolpum():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
        return array;
    }
    
    public UM_FOE_CommInfo[] getBichukMulpumInfo(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        UM_FOE_CommInfo[] array = null;
        final Vector vector = new Vector<UM_FOE_CommInfo>();
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_FOE_CommInfo um_FOE_CommInfo = new UM_FOE_CommInfo();
                um_FOE_CommInfo.setString1(executeQuery.getString(1));
                um_FOE_CommInfo.setString2(executeQuery.getString(2));
                um_FOE_CommInfo.setString3(executeQuery.getString(3));
                vector.addElement(um_FOE_CommInfo);
            }
            array = new UM_FOE_CommInfo[vector.size()];
            vector.copyInto(array);
            vector.removeAllElements();
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn062.getBichukMulpumInfo():caller");
            Log.debug("UM_FOB_Forn062.getBichukMulpumInfo():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn062.getBichukMulpumInfo():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
        return array;
    }
    
    public UM_FOE_CommInfo[] getBichukMulpumInfo_U(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        UM_FOE_CommInfo[] array = null;
        final Vector vector = new Vector<UM_FOE_CommInfo>();
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_FOE_CommInfo um_FOE_CommInfo = new UM_FOE_CommInfo();
                um_FOE_CommInfo.setString1(executeQuery.getString(1));
                um_FOE_CommInfo.setString2(executeQuery.getString(2));
                um_FOE_CommInfo.setString3(executeQuery.getString(3));
                vector.addElement(um_FOE_CommInfo);
            }
            array = new UM_FOE_CommInfo[vector.size()];
            vector.copyInto(array);
            vector.removeAllElements();
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn062.getBichukMulpumInfo_U():caller");
            Log.debug("UM_FOB_Forn062.getBichukMulpumInfo_U():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn062.getBichukMulpumInfo_U():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
        return array;
    }
    
    public UM_FOE_CommInfo getDocInfo(final Object o, final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        UM_FOE_CommInfo um_FOE_CommInfo = null;
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                um_FOE_CommInfo = new UM_FOE_CommInfo();
                um_FOE_CommInfo.setString1(executeQuery.getString(1));
                um_FOE_CommInfo.setString2(executeQuery.getString(2));
                um_FOE_CommInfo.setString3(executeQuery.getString(3));
                um_FOE_CommInfo.setString3(executeQuery.getString(4));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn062.getDocInfo():caller");
            Log.debug("UM_FOB_Forn062.getDocInfo():caller" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            throw new SQLException(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn062.getDocInfo():caller->" + o.getClass().getName() + ":" + ex2.toString());
            throw new Exception(ex2.getMessage());
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null && b) {
                    connection.close();
                }
            }
            catch (Exception ex5) {}
            try {
                if (trx != null) {
                    trx.close();
                }
            }
            catch (Exception ex6) {}
        }
        return um_FOE_CommInfo;
    }
}
