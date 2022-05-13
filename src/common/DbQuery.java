// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.Connection;

public class DbQuery
{
    public CommonEntity[] getList(final Object caller_obj, final String sql, Connection conn) throws Exception {
        Trx resource = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        CommonEntity oneEntity = null;
        CommonEntity[] multi = (CommonEntity[])null;
        final Vector v = new Vector(1, 1);
        boolean flag = false;
        ResultSetMetaData rsmd = null;
        try {
            if (conn == null) {
                resource = new Trx(caller_obj, "vend");
                conn = resource.getConnection();
                flag = true;
            }
            pstmt = conn.prepareStatement(sql);
            rest = pstmt.executeQuery();
            rsmd = rest.getMetaData();
            final int count = rsmd.getColumnCount();
            while (rest.next()) {
                oneEntity = new CommonEntity();
                oneEntity.dataArraySetting(count);
                for (int i = 1; i <= count; ++i) {
                    oneEntity.data[i - 1] = rest.getString(i);
                }
                v.addElement(oneEntity);
                oneEntity = null;
            }
            multi = new CommonEntity[v.size()];
            v.copyInto(multi);
            return multi;
        }
        catch (Exception ex) {
            Log.debug("DbQuery.getList():caller->" + caller_obj.getClass().getName() + ":" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (rest != null) {
                    rest.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (conn != null && flag) {
                    resource.close();
                }
            }
            catch (Exception ex4) {}
        }
    }
    
    public CommonEntity[] getList(final Object caller_obj, final String sql, final String[] parameter, Connection conn) throws Exception {
        Trx resource = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        CommonEntity oneEntity = null;
        CommonEntity[] multi = (CommonEntity[])null;
        final Vector v = new Vector(1, 1);
        boolean flag = false;
        ResultSetMetaData rsmd = null;
        try {
            if (conn == null) {
                resource = new Trx(caller_obj, "vend");
                conn = resource.getConnection();
                flag = true;
            }
            pstmt = conn.prepareStatement(sql);
            if (parameter != null && parameter.length > 0) {
                for (int i = 0, n = parameter.length; i < n; ++i) {
                    pstmt.setString(i + 1, parameter[i]);
                }
            }
            rest = pstmt.executeQuery();
            rsmd = rest.getMetaData();
            final int count = rsmd.getColumnCount();
            while (rest.next()) {
                oneEntity = new CommonEntity();
                oneEntity.dataArraySetting(count);
                for (int j = 1; j <= count; ++j) {
                    oneEntity.data[j - 1] = rest.getString(j);
                }
                v.addElement(oneEntity);
                oneEntity = null;
            }
            multi = new CommonEntity[v.size()];
            v.copyInto(multi);
            return multi;
        }
        catch (Exception ex) {
            Log.debug("DbQuery.getList():caller->" + caller_obj.getClass().getName() + ":" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (rest != null) {
                    rest.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (conn != null && flag) {
                    resource.close();
                }
            }
            catch (Exception ex4) {}
        }
    }
    
    public int getCount(final Object caller_obj, final String sql, Connection conn) throws Exception {
        Trx resource = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        boolean flag = false;
        int result = 0;
        try {
            if (conn == null) {
                resource = new Trx(caller_obj, "vend");
                conn = resource.getConnection();
                flag = true;
            }
            pstmt = conn.prepareStatement(sql);
            rest = pstmt.executeQuery();
            if (!rest.next()) {
                throw new Exception("ResultSet Null 리턴");
            }
            result = rest.getInt(1);
            if (rest.wasNull()) {
                throw new Exception("Null 리턴됨");
            }
            return result;
        }
        catch (Exception ex) {
            Log.debug("DbQuery.getCount():caller->" + caller_obj.getClass().getName() + ":" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (rest != null) {
                    rest.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (conn != null && flag) {
                    resource.close();
                }
            }
            catch (Exception ex4) {}
        }
    }
    
    public int getCount(final Object caller_obj, final String sql, final String[] parameter, Connection conn) throws Exception {
        Trx resource = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        boolean flag = false;
        int result = 0;
        try {
            if (conn == null) {
                resource = new Trx(caller_obj, "vend");
                conn = resource.getConnection();
                flag = true;
            }
            pstmt = conn.prepareStatement(sql);
            if (parameter != null && parameter.length > 0) {
                for (int i = 0, n = parameter.length; i < n; ++i) {
                    pstmt.setString(i + 1, parameter[i]);
                }
            }
            rest = pstmt.executeQuery();
            if (!rest.next()) {
                throw new Exception("ResultSet Null 리턴");
            }
            result = rest.getInt(1);
            if (rest.wasNull()) {
                throw new Exception("Null 리턴됨");
            }
            return result;
        }
        catch (Exception ex) {
            Log.debug("DbQuery.getCount():caller->" + caller_obj.getClass().getName() + ":" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (rest != null) {
                    rest.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (conn != null && flag) {
                    resource.close();
                }
            }
            catch (Exception ex4) {}
        }
    }
}
