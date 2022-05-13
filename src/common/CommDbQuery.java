// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.Reader;
import common.util.CommUtil;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.Connection;

public class CommDbQuery
{
    private static final long WARNNING_MAX_ELAPSED_TIME = 10000L;
    
    public CommEntity[] getList(final Object caller_obj, final String sql, Connection conn) throws Exception {
        Trx resource = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        CommEntity oneEntity = null;
        CommEntity[] multi = (CommEntity[])null;
        final Vector v = new Vector(1, 1);
        boolean flag = false;
        ResultSetMetaData rsmd = null;
        try {
            if (conn == null) {
                resource = new Trx(this);
                conn = resource.getConnection();
                flag = true;
            }
            pstmt = conn.prepareStatement(sql);
            rest = pstmt.executeQuery();
            rsmd = rest.getMetaData();
            final int count = rsmd.getColumnCount();
            while (rest.next()) {
                oneEntity = new CommEntity();
                oneEntity.dataArraySetting(count);
                for (int i = 1; i <= count; ++i) {
                    oneEntity.data[i - 1] = rest.getString(i);
                }
                v.addElement(oneEntity);
                oneEntity = null;
            }
            multi = new CommEntity[v.size()];
            v.copyInto(multi);
            return multi;
        }
        catch (Exception ex) {
            Log.debug("CommDbQuery.getList():caller->" + caller_obj.getClass().getName() + ":" + ex.toString());
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
    
    public CommEntity[] getList(final Object caller_obj, final String sql, final String[] parameter, Connection conn) throws Exception {
        Trx resource = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        CommEntity oneEntity = null;
        CommEntity[] multi = (CommEntity[])null;
        final Vector v = new Vector(1, 1);
        boolean flag = false;
        ResultSetMetaData rsmd = null;
        try {
            if (conn == null) {
                resource = new Trx(this);
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
                oneEntity = new CommEntity();
                oneEntity.dataArraySetting(count);
                for (int j = 1; j <= count; ++j) {
                    oneEntity.data[j - 1] = rest.getString(j);
                }
                v.addElement(oneEntity);
                oneEntity = null;
            }
            multi = new CommEntity[v.size()];
            v.copyInto(multi);
            return multi;
        }
        catch (Exception ex) {
            Log.debug("CommDbQuery.getList():caller->" + caller_obj.getClass().getName() + ":" + ex.toString());
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
                resource = new Trx(this);
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
            Log.debug("CommDbQuery.getCount():caller->" + caller_obj.getClass().getName() + ":" + ex.toString());
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
                resource = new Trx(this);
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
            Log.debug("CommDbQuery.getCount():caller->" + caller_obj.getClass().getName() + ":" + ex.toString());
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
    
    public OneRowEntity getOneRowList(final Object caller_obj, final String sql, Connection conn) throws Exception {
        final long startTime = System.currentTimeMillis();
        Trx resource = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        boolean flag = false;
        final OneRowEntity oneEntity = new OneRowEntity();
        ResultSetMetaData rsmd = null;
        try {
            if (conn == null) {
                resource = new Trx(caller_obj);
                conn = resource.getConnection();
                flag = true;
            }
            pstmt = conn.prepareStatement(sql);
            rest = pstmt.executeQuery();
            rsmd = rest.getMetaData();
            final int count = rsmd.getColumnCount();
            oneEntity.hashMappingSetting(rsmd);
            if (rest.next()) {
                oneEntity.dataArraySetting(count);
                for (int i = 1; i <= count; ++i) {
                    if (rsmd.getColumnType(i) == 2005) {
                        final StringBuffer output = new StringBuffer();
                        final Reader input = rest.getCharacterStream(i);
                        final char[] buffer = new char[1024];
                        int byteRead;
                        while ((byteRead = input.read(buffer, 0, 1024)) != -1) {
                            output.append(buffer, 0, byteRead);
                        }
                        try {
                            if (input != null) {
                                input.close();
                            }
                        }
                        catch (Exception ex2) {}
                        oneEntity.data[i - 1] = output.toString();
                    }
                    else {
                        oneEntity.data[i - 1] = CommUtil.retSpace(rest.getString(i));
                    }
                }
            }
            return oneEntity;
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getOneRowList():caller->" + caller_obj.getClass().getName() + ":" + ex.toString());
            Log.debug("SQL liên quan:" + sql);
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (rest != null) {
                    rest.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (conn != null && flag) {
                    resource.close();
                }
            }
            catch (Exception ex5) {}
            final long endTime = System.currentTimeMillis();
            if (endTime - startTime > 10000L) {
                Log.debug("SQL WARNING :" + Long.toString(endTime - startTime) + "\t" + caller_obj.getClass().getName() + "\t" + " time long!!");
                Log.debug("SQL liên quan" + sql);
            }
        }
    }
    
    public OneRowEntity getOneRowList(final Object caller_obj, final String sql, final String[] parameter, Connection conn) throws Exception {
        final long startTime = System.currentTimeMillis();
        Trx resource = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        final OneRowEntity oneEntity = new OneRowEntity();
        boolean flag = false;
        ResultSetMetaData rsmd = null;
        try {
            if (conn == null) {
                resource = new Trx(caller_obj);
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
            oneEntity.hashMappingSetting(rsmd);
            if (rest.next()) {
                oneEntity.dataArraySetting(count);
                for (int j = 1; j <= count; ++j) {
                    if (rsmd.getColumnType(j) == 2005) {
                        final StringBuffer output = new StringBuffer();
                        final Reader input = rest.getCharacterStream(j);
                        final char[] buffer = new char[1024];
                        int byteRead;
                        while ((byteRead = input.read(buffer, 0, 1024)) != -1) {
                            output.append(buffer, 0, byteRead);
                        }
                        try {
                            if (input != null) {
                                input.close();
                            }
                        }
                        catch (Exception ex2) {}
                        oneEntity.data[j - 1] = output.toString();
                    }
                    else {
                        oneEntity.data[j - 1] = CommUtil.retSpace(rest.getString(j));
                    }
                }
            }
            return oneEntity;
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getOneRowList():caller->" + caller_obj.getClass().getName() + ":" + ex.toString());
            Log.debug("SQL : " + sql);
            if (parameter != null && parameter.length > 0) {
                String parameterLog = "";
                for (int k = 0, n2 = parameter.length; k < n2; ++k) {
                    parameterLog = String.valueOf(parameterLog) + "[" + (k + 1) + "," + parameter[k] + "] ";
                }
                Log.debug("parameter:" + parameterLog);
            }
            throw new Exception(ex.getMessage());
        }
        finally {
            try {
                if (rest != null) {
                    rest.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (conn != null && flag) {
                    resource.close();
                }
            }
            catch (Exception ex5) {}
            final long endTime = System.currentTimeMillis();
            if (endTime - startTime > 10000L) {
                Log.debug("SQL WARNING :" + Long.toString(endTime - startTime) + "\t" + caller_obj.getClass().getName() + "\t" + " time long!!");
                Log.debug("관련SQL:" + sql);
                if (parameter != null && parameter.length > 0) {
                    String parameterLog2 = "";
                    for (int l = 0, n3 = parameter.length; l < n3; ++l) {
                        parameterLog2 = String.valueOf(parameterLog2) + "[" + (l + 1) + "," + parameter[l] + "] ";
                    }
                    Log.debug("parameter:" + parameterLog2);
                }
            }
        }
    }
}
