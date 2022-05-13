// 
// Decompiled by Procyon v0.5.30
// 

package exms.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.io.FileOutputStream;

public class U_JUploadAppLog
{
    private int saveday;
    private String filename;
    private String logdir;
    private String homedir;
    private FileOutputStream fouts;
    private HashMap logHash;
    
    public U_JUploadAppLog(final String dir1, final String dir2, final String file) {
        this.saveday = 0;
        this.filename = null;
        this.logdir = null;
        this.homedir = null;
        this.fouts = null;
        this.logHash = null;
        this.logHash = new HashMap(1024);
        this.homedir = dir1;
        this.logdir = dir2;
        this.filename = file;
    }
    
    public synchronized void log(final String key, final String s) {
        if (this.logHash.get(key) == null) {
            this.logHash.put(key, new StringBuffer(2048));
        }
        ((StringBuffer)this.logHash.get(key)).append(String.valueOf(s) + "\n");
    }
    
    private void write(final String str) throws IOException {
        this.fouts.write(str.getBytes());
    }
    
    private void writeln(final String str) throws IOException {
        this.fouts.write((String.valueOf(str) + "\n").getBytes());
    }
    
    public synchronized void save(final String sendcode) {
        if (this.logHash.get(sendcode) == null) {
            return;
        }
        try {
            this.fileopen();
            this.writeln(String.valueOf(this.getDateString("yyyy-MM-dd hh:mm:ss")) + " SENDCODE=" + sendcode + "\t---------->");
            this.writeln(((StringBuffer)this.logHash.get(sendcode)).toString());
            this.writeln(" ");
            this.fouts.flush();
        }
        catch (IOException e) {
            try {
                if (this.fouts != null) {
                    this.fouts.close();
                }
                this.fouts = null;
            }
            catch (Exception ex) {}
            System.err.println("Can't write logfile (filename = " + this.filename);
            e.printStackTrace(System.err);
            return;
        }
        finally {
            this.logHash.remove(sendcode);
        }
        this.logHash.remove(sendcode);
    }
    
    public synchronized void close() {
        synchronized (this.fouts) {
            try {
                this.fouts.close();
            }
            catch (IOException e) {
                System.err.println("Can't close logfile (filename = " + this.filename);
                e.printStackTrace(System.err);
            }
        }
        // monitorexit(this.fouts)
    }
    
    private void fileopen() throws IOException {
        if (this.saveday != this.getCurrentDay()) {
            final File logDir = new File(String.valueOf(this.homedir) + this.getDateString("yyyy'/'MM'/'dd'/'") + this.logdir);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            if (this.fouts != null) {
                this.fouts.close();
            }
            this.fouts = new FileOutputStream(String.valueOf(this.homedir) + this.getDateString("yyyy'/'MM'/'dd'/'") + this.logdir + File.separator + this.filename + ".log", true);
            this.saveday = this.getCurrentDay();
        }
    }
    
    private int getCurrentDay() throws IOException {
        final SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            return Integer.parseInt(df.format(new Date()));
        }
        catch (NumberFormatException e) {
            throw new IOException("Can't get current Day, System Error!!!");
        }
    }
    
    private String getDateString(final String format) {
        final SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }
}
