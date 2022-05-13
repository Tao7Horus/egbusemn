// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.util.Vector;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;

public abstract class AbstractDbWrap implements DbWrap
{
    protected Connection conn;
    protected final boolean debug = true;
    
    public AbstractDbWrap(final Connection conn) {
        this.conn = conn;
    }
    
    public Entity insert(final Entity entity) throws SQLException {
        final String SQL = this.getInsertSQL(entity);
        System.out.println("> SQL insert : " + SQL);
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            this.conn.setAutoCommit(false);
            stmt.executeUpdate(SQL);
            return this.select(entity);
        }
        catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Insert fail");
        }
        finally {
            stmt.close();
        }
    }
    
    public Entity select(final Entity entity) throws SQLException {
        final String SQL = this.getSelectSQL(entity);
        System.out.println("> SQL select : " + SQL);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                return this.parseResultSet(rs);
            }
            return null;
        }
        catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Query fail");
        }
        finally {
            stmt.close();
        }
    }
    
    public Entity update(final Entity entity) throws SQLException {
        final String SQL = this.getUpdateSQL(entity);
        System.out.println("> SQL update : " + SQL);
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            this.conn.setAutoCommit(false);
            stmt.executeUpdate(SQL);
            return this.select(entity);
        }
        catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Update fail");
        }
        finally {
            stmt.close();
        }
    }
    
    public void delete(final Entity entity) throws SQLException {
        final String SQL = this.getDeleteSQL(entity);
        System.out.println("> SQL delete : " + SQL);
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            this.conn.setAutoCommit(false);
            stmt.executeUpdate(SQL);
        }
        catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Delete fail");
        }
        finally {
            stmt.close();
        }
        stmt.close();
    }
    
    public Vector selectByCondition(final String where_cond, final String where_rownum) throws SQLException {
        final String SQL = this.getQueryListSQL(where_cond, where_rownum);
        System.out.println("> SQL selectByCondition1 : " + SQL);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(SQL);
            return this.parseResultSets(rs);
        }
        catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Query fail");
        }
        finally {
            rs.close();
            stmt.close();
        }
    }
    
    public Vector selectByCondition(final String where) throws SQLException {
        final String SQL = this.getQuerySQL(where);
        System.out.println("> SQL selectByCondition2 : " + SQL);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(SQL);
            return this.parseResultSets(rs);
        }
        catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Query fail");
        }
        finally {
            rs.close();
            stmt.close();
        }
    }
    
    public int selectCountByCondition(final String where) throws SQLException {
        final String SQL = this.getQuerySQLForCount(where);
        System.out.println("> SQL selectCountByCondition : " + SQL);
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = this.conn.createStatement();
            rs = stmt.executeQuery(SQL);
            return this.parseResultCount(rs);
        }
        catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Query fail");
        }
        finally {
            rs.close();
            stmt.close();
        }
    }
    
    protected abstract Entity parseResultSet(final ResultSet p0) throws SQLException;
    
    protected abstract Vector parseResultSets(final ResultSet p0) throws SQLException;
    
    protected abstract int parseResultCount(final ResultSet p0) throws SQLException;
    
    protected abstract String getInsertSQL(final Entity p0) throws SQLException;
    
    protected abstract String getSelectSQL(final Entity p0) throws SQLException;
    
    protected abstract String getUpdateSQL(final Entity p0) throws SQLException;
    
    protected abstract String getDeleteSQL(final Entity p0) throws SQLException;
    
    protected abstract String getQuerySQL(final String p0) throws SQLException;
    
    protected abstract String getQueryListSQL(final String p0, final String p1) throws SQLException;
    
    protected abstract String getQuerySQLForCount(final String p0) throws SQLException;
}
