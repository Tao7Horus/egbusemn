// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.FileWriter;
import java.io.File;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.io.PrintWriter;

public class LoginLog
{
    private static PrintWriter log;
    
    public static void write(final String message) {
        final String logDir = "/data/logs/USEMN/";
        final String logFile = "sso.log";
        final String date = "";
        String yearmonth = "";
        String day = "";
        String _logFile = "";
        SimpleDateFormat formatter = null;
        String realTime = null;
        final Date currentDate = new Date();
        try {
            formatter = new SimpleDateFormat("HH:mm:ss ddMMyyyy", Locale.FRENCH);
            formatter.setTimeZone(TimeZone.getTimeZone("JST"));
            realTime = formatter.format(currentDate);
        }
        catch (Exception exm) {
            System.out.println("LoginLog ngày tháng: " + exm.toString());
        }
        try {
            yearmonth = realTime.substring(11, 17);
            day = realTime.substring(9, 11);
        }
        catch (Exception e) {
            System.out.println("LoginLog ngày tháng realTime[" + realTime + "]:  " + e.toString());
        }
        _logFile = logDir;
        try {
            if (realTime.length() > 0) {
                _logFile = String.valueOf(_logFile) + File.separator + yearmonth;
                final File newDir = new File(_logFile);
                if (!newDir.exists()) {
                    newDir.mkdir();
                }
                _logFile = String.valueOf(_logFile) + File.separator + day;
                final File newDir2 = new File(_logFile);
                if (!newDir2.exists()) {
                    newDir2.mkdir();
                }
            }
            _logFile = String.valueOf(new File(_logFile).getAbsolutePath()) + File.separator + logFile;
        }
        catch (Exception e) {
            _logFile = String.valueOf(_logFile) + logFile;
        }
        try {
            LoginLog.log = new PrintWriter(new FileWriter(_logFile, true), true);
        }
        catch (IOException e2) {
            System.err.println("LoginLog không thể mở file, " + _logFile + "!!");
            LoginLog.log = new PrintWriter(System.err);
        }
        try {
            LoginLog.log.println("[" + realTime + "]:" + message);
        }
        catch (Exception e) {
            System.out.println("LoginLog:" + e.toString());
        }
    }
}
