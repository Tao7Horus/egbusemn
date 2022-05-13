// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log
{
    public static final int DEFAULT = 0;
    public static final int ANTI = 1;
    public static final int CON = 2;
    public static final int DOC_VEND = 3;
    public static final int HYUP = 4;
    public static final int LOGIN = 5;
    public static final String[][] LOG_SET;
    
    private static void debug(final int n, final String s, final String s2) {
        DataOutputStream dataOutputStream = null;
        final Date date = new Date();
        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("VST"));
            final String format = simpleDateFormat.format(date);
            final String substring = format.substring(0, 8);
            final String substring2 = format.substring(0, 6);
            final String string = s2 + "[" + format + "] " + s + "\n";
            final String string2 = Log.LOG_SET[n][1] + substring + ".out";
            final File file = new File(Log.LOG_SET[n][0] + substring2);
            if (!file.exists()) {
                file.mkdir();
            }
            final String s3 = new String(string.getBytes("UTF-8"), "8859_1");
            dataOutputStream = new DataOutputStream(new FileOutputStream(Log.LOG_SET[n][0] + substring2 + "/" + string2, true));
            dataOutputStream.writeBytes(s3);
        }
        catch (UnsupportedEncodingException ex) {
            System.out.println("Log.java :" + ex.toString());
        }
        catch (FileNotFoundException ex2) {
            System.out.println("Log.java :" + ex2.toString());
        }
        catch (IOException ex3) {
            System.out.println("Log.java :" + ex3.toString());
        }
        catch (Exception ex4) {
            System.out.println("Log.java :" + ex4.toString());
        }
        finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            }
            catch (Exception ex5) {}
        }
    }
    
    public static void errors(final String s) {
        debug(0, s, "E");
    }
    
    public static void info(final String s) {
        debug(0, s, "I");
    }
    
    public static void warning(final String s) {
        debug(0, s, "W");
    }
    
    public static void debug(final String s) {
        debug(0, s, "D");
    }
    
    public static void info(final int n, final String s) {
        debug(n, s, "I");
    }
    
    public static void warning(final int n, final String s) {
        debug(n, s, "W");
    }
    
    public static void debug(final int n, final String s) {
        debug(n, s, "D");
    }
    
    public static void errors(final int n, final String s) {
        debug(n, s, "E");
    }
    
    public static void errors(final Object o, final Exception ex, final String s) {
        try {
            final StackTraceElement[] stackTrace = ex.getStackTrace();
            final String string = o.getClass().toString();
            for (int i = 0; i < stackTrace.length; ++i) {
                if (string.endsWith(stackTrace[i].getClassName())) {
                    errors("[C:" + stackTrace[i].getClassName() + "]" + "[M:" + stackTrace[i].getMethodName() + "]" + "[L:" + stackTrace[i].getLineNumber() + "]" + "[E:" + ex.toString() + "] " + s);
                }
            }
        }
        catch (Exception ex2) {
            System.out.println("Log.java parseExceptionString(): " + ex2.toString());
        }
    }
    
    private static void parseExceptionString(final String s) {
        try {
            int n = 0;
            s.length();
            int index;
            while ((index = s.indexOf(10, n)) >= n) {
                String s2 = s.substring(n, index);
                if (s2.charAt(s2.length() - 1) == 'r') {
                    s2 = s2.substring(0, s2.length() - 1);
                }
                int i;
                for (i = 0; i < s2.length(); ++i) {
                    final char char1 = s2.charAt(i);
                    if (char1 != ' ' && char1 != '\t') {
                        break;
                    }
                }
                info(s2.substring(i));
                n = index + 1;
            }
        }
        catch (Exception ex) {
            System.out.println("Log.java parseExceptionString(): " + ex.toString());
        }
    }
    
    public static void erorrPosition(final Exception ex) {
        final StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter((Writer)stringWriter));
        parseExceptionString(stringWriter.toString());
    }
    
    static {
        LOG_SET = new String[][] { { "/data/logs/USEMN/", "usemn" }, { "/data/logs/USEMN/", "connection" }, { "/data/logs/USEMN/", "cookie" }, { "/data/logs/USEMN/", "login" }, { "/data/logs/USEMN/", "cert" }, { "/data/logs/USEMN/", "signLogin" } };
    }
}
