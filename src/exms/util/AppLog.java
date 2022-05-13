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

public class AppLog
{
    private int saveday;
    private String savedate;
    private String filename;
    private String logdir;
    private FileOutputStream fouts;
    private HashMap logHash;
    
    public AppLog(final String dir, final String file) {
        this.saveday = 0;
        this.savedate = "";
        this.filename = null;
        this.logdir = null;
        this.fouts = null;
        this.logHash = null;
        this.logHash = new HashMap(1024);
        this.logdir = dir;
        this.filename = file;
        this.savedate = this.getDateString("yyyyMMdd");
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
            }
            catch (Exception ex) {}
            System.err.println("Can't write logfile (filename = " + this.filename);
            e.printStackTrace(System.err);
            return;
        }
        finally {
            try {
                this.fouts.close();
            }
            catch (Exception ex2) {}
            this.fouts = null;
            this.logHash.remove(sendcode);
        }
        try {
            this.fouts.close();
        }
        catch (Exception ex3) {}
        this.fouts = null;
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
    
    public boolean makeDir(final String path) {
        int idx = 1;
        String part = null;
        try {
            do {
                idx = path.indexOf(File.separator, idx + 1);
                if (idx < 0) {
                    part = path;
                }
                else {
                    part = path.substring(0, idx);
                }
                final File fd = new File(part);
                if ((!fd.exists() || !fd.isDirectory()) && !fd.mkdirs()) {
                    return false;
                }
            } while (idx >= 0);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            return false;
        }
        finally {
            final File fd = null;
        }
    }
    
    private void fileopen() throws IOException {
        if (this.saveday == 0 || this.fouts == null) {
            if (!new File(this.logdir).exists()) {
                new File(this.logdir).mkdirs();
            }
            final int filedate = this.getDay(new File(String.valueOf(this.logdir) + File.separator + this.filename + ".log").lastModified());
            if (filedate != this.getCurrentDay()) {
                this.backupLogFile();
            }
            this.fouts = new FileOutputStream(String.valueOf(this.logdir) + File.separator + this.filename + ".log", true);
            this.saveday = this.getCurrentDay();
        }
        else if (this.saveday != this.getCurrentDay()) {
            this.fouts.close();
            this.backupLogFile();
            this.fouts = new FileOutputStream(String.valueOf(this.logdir) + File.separator + this.filename + ".log", true);
            this.savedate = this.getDateString("yyyyMMdd");
            this.saveday = this.getCurrentDay();
        }
    }
    
    private void backupLogFile() throws IOException {
        final File fd = new File(String.valueOf(this.logdir) + File.separator + this.filename + ".log");
        if (!fd.exists()) {
            return;
        }
        final String dateString = new SimpleDateFormat("yyyyMMdd").format(new Date(fd.lastModified()));
        final String savepath = String.valueOf(this.logdir) + File.separator + dateString.substring(0, 4) + File.separator + dateString.substring(4, 6);
        if (!this.makeDir(savepath)) {
            throw new IOException("Can't make directory of " + savepath);
        }
        if (!fd.renameTo(new File(String.valueOf(savepath) + File.separator + dateString + ".log"))) {
            throw new IOException("Can't move logfile to " + savepath + File.separator + dateString + ".log");
        }
    }
    
    private int getDay(final long time) throws IOException {
        final SimpleDateFormat df = new SimpleDateFormat("dd");
        try {
            return Integer.parseInt(df.format(new Date(time)));
        }
        catch (NumberFormatException e) {
            throw new IOException("Can't get Day, System Error!!!");
        }
    }
    
    private int getCurrentDay() throws IOException {
        final SimpleDateFormat df = new SimpleDateFormat("dd");
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
