// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.Driver;

public class CopyOfTrx
{
    private static Driver MyDriver;
    private static final String JdbcClass = "weblogic.jdbc.pool.Driver";
    private static final String DefaultJdbcURL = "jdbc:weblogic:pool:bidPool";
    private boolean initialized;
    private Connection conn;
    private static Object lock;
    private long STARTTIME;
    private static int used_conn_count;
    private static boolean DEBUG_MODE;
    private long WARNNING_MAX_ELAPSED_TIME;
    private String caller;
    
    static {
        CopyOfTrx.MyDriver = null;
        CopyOfTrx.lock = new Object();
        CopyOfTrx.used_conn_count = 0;
        CopyOfTrx.DEBUG_MODE = true;
    }
    
    public CopyOfTrx(final Object caller_obj) throws SQLException, Exception {
        this.initialized = false;
        this.conn = null;
        this.STARTTIME = 0L;
        this.WARNNING_MAX_ELAPSED_TIME = 5000L;
        this.caller = "unknown";
        if (!this.initialized) {
            CopyOfTrx.MyDriver = (Driver)Class.forName("weblogic.jdbc.pool.Driver").newInstance();
            this.initialized = true;
        }
        if (caller_obj != null) {
            this.caller = caller_obj.getClass().getName();
        }
        this.conn = CopyOfTrx.MyDriver.connect("jdbc:weblogic:pool:bidPool", null);
    }
    
    public CopyOfTrx(final Object caller_obj, final String poolName) throws SQLException, Exception {
        this.initialized = false;
        this.conn = null;
        this.STARTTIME = 0L;
        this.WARNNING_MAX_ELAPSED_TIME = 5000L;
        this.caller = "unknown";
        final String JdbcURL = "jdbc:weblogic:pool:" + poolName + "Pool";
        if (!this.initialized) {
            CopyOfTrx.MyDriver = (Driver)Class.forName("weblogic.jdbc.pool.Driver").newInstance();
            this.initialized = true;
        }
        if (caller_obj != null) {
            this.caller = caller_obj.getClass().getName();
        }
        this.conn = CopyOfTrx.MyDriver.connect(JdbcURL, null);
    }
    
    public Connection getConnection() throws SQLException {
        if (this.conn == null) {
            throw new SQLException("Connection is NOT available!!");
        }
        synchronized (CopyOfTrx.lock) {
            ++CopyOfTrx.used_conn_count;
        }
        // monitorexit(CopyOfTrx.lock)
        this.STARTTIME = System.currentTimeMillis();
        return this.conn;
    }
    
    public void close() {
        if (this.conn == null) {
            return;
        }
        if (this.conn != null) {
            try {
                if (!this.conn.getAutoCommit()) {
                    Log.debug(1, "Connection auto comit false : \t" + this.caller);
                    try {
                        this.conn.setAutoCommit(true);
                    }
                    catch (Exception ex) {}
                }
            }
            catch (Exception ex2) {}
            try {
                this.conn.close();
            }
            catch (Exception ex3) {}
            this.conn = null;
        }
        int count = 0;
        synchronized (CopyOfTrx.lock) {
            count = --CopyOfTrx.used_conn_count;
        }
        // monitorexit(CopyOfTrx.lock)
        if (CopyOfTrx.DEBUG_MODE) {
            final long endtime = System.currentTimeMillis();
            if (endtime - this.STARTTIME > this.WARNNING_MAX_ELAPSED_TIME) {
                Log.debug(1, "POOL WARNING : # of pool are " + Integer.toString(count) + ":" + Long.toString(endtime - this.STARTTIME) + "\t" + this.caller + "\t" + "uses pool too long!!");
            }
        }
    }
    
    public void finalize() {
        try {
            if (this.conn != null) {
                Log.debug(1, "POOL ERROR : connect was not closed by " + this.caller + ":" + "\t" + "# of pool are " + Integer.toString(CopyOfTrx.used_conn_count));
                this.close();
            }
        }
        catch (Exception ex) {}
    }
}
