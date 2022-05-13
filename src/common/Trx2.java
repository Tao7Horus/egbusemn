// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;

public class Trx2
{
    private static Driver MyDriver;
    private static final String JdbcClass = "weblogic.jdbc.pool.Driver";
    private static final String DefaultJdbcURL = "jdbc:weblogic:pool:usemnPool2";
    private boolean initialized;
    private Connection conn;
    private static Object lock;
    private long STARTTIME;
    private static int used_conn_count;
    private static boolean DEBUG_MODE;
    private long WARNNING_MAX_ELAPSED_TIME;
    private String caller;
    
    public Trx2(final Object o) throws SQLException, Exception {
        this.initialized = false;
        this.conn = null;
        this.STARTTIME = 0L;
        this.WARNNING_MAX_ELAPSED_TIME = 5000L;
        this.caller = "unknown";
        if (!this.initialized) {
            Trx2.MyDriver = (Driver)Class.forName(JdbcClass).newInstance();
            this.initialized = true;
        }
        if (o != null) {
            this.caller = o.getClass().getName();
        }
        this.conn = Trx2.MyDriver.connect(DefaultJdbcURL, null);
    }
    
    public Trx2(final Object o, final String s) throws SQLException, Exception {
        this.initialized = false;
        this.conn = null;
        this.STARTTIME = 0L;
        this.WARNNING_MAX_ELAPSED_TIME = 5000L;
        this.caller = "unknown";
        final String string = "jdbc:weblogic:pool:" + s.toLowerCase() + "Pool2";
        if (!this.initialized) {
            Trx2.MyDriver = (Driver)Class.forName(JdbcClass).newInstance();
            this.initialized = true;
        }
        if (o != null) {
            this.caller = o.getClass().getName();
        }
        this.conn = Trx2.MyDriver.connect(string, null);
    }
    
    public Connection getConnection() throws SQLException {
        if (this.conn == null) {
            throw new SQLException("Connection is NOT available!!");
        }
        synchronized (Trx2.lock) {
            ++Trx2.used_conn_count;
        }
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
        int n = 0;
        synchronized (Trx2.lock) {
            n = --Trx2.used_conn_count;
        }
        if (Trx2.DEBUG_MODE) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.STARTTIME > this.WARNNING_MAX_ELAPSED_TIME) {
                Log.debug(1, "POOL WARNING : # of pool are " + Integer.toString(n) + ":" + Long.toString(currentTimeMillis - this.STARTTIME) + "\t" + this.caller + "\t" + "uses pool too long!!");
            }
        }
    }
    
    public void finalize() {
        try {
            if (this.conn != null) {
                Log.debug(1, "POOL ERROR : connect was not closed by " + this.caller + ":" + "\t" + "# of pool are " + Integer.toString(Trx2.used_conn_count));
                this.close();
            }
        }
        catch (Exception ex) {}
    }
    
    static {
        Trx2.MyDriver = null;
        Trx2.lock = new Object();
        Trx2.used_conn_count = 0;
        Trx2.DEBUG_MODE = true;
    }
}
