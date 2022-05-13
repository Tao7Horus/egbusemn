// 
// Decompiled by Procyon v0.5.30
// 

package exms.util;

import java.io.File;
import java.util.Enumeration;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Hashtable;

public class InitConfig
{
    private String filename;
    private Hashtable configHash;
    public int port;
    
    public InitConfig(final String s) throws IOException {
        this.filename = null;
        this.port = 0;
        Properties properties = null;
        final Object obj = null;
        FileInputStream fileinputstream = null;
        final Object obj2 = null;
        final Object obj3 = null;
        this.filename = s;
        this.configHash = new Hashtable(20);
        try {
            fileinputstream = new FileInputStream(s);
        }
        catch (FileNotFoundException filenotfoundexception) {
            throw new IOException(filenotfoundexception.getMessage());
        }
        properties = new Properties();
        properties.load(fileinputstream);
        fileinputstream.close();
        final Enumeration enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            final String s2 = (String)enumeration.nextElement();
            final String s3 = (String)((Hashtable<K, String>)properties).get(s2);
            if (s3 != null) {
                this.configHash.put(s2, s3);
            }
        }
        this.CheckConfigValue();
        this.CheckDefaultPath();
    }
    
    public synchronized String getValue(final String s) {
        if (this.configHash.get(s) == null) {
            return null;
        }
        return this.configHash.get(s);
    }
    
    public synchronized String getValue(final String s, final String s1) {
        if (this.configHash.get(s) == null) {
            return s1;
        }
        return this.configHash.get(s);
    }
    
    public synchronized Enumeration getNames() {
        return this.configHash.keys();
    }
    
    public synchronized void setValue(final String s, final String s1) {
        if (s == null || s.equals("") || s1 == null || s1.equals("")) {
            return;
        }
        this.configHash.put(s, s1);
    }
    
    private void CheckConfigValue() throws IOException {
        if (this.getValue("APP_HOME_DIR") == null) {
            throw new IOException("cannot find Root Directory in con_file!!");
        }
    }
    
    private void CheckDefaultPath() throws IOException {
        this.checkDirValue(this.getValue("APP_HOME_DIR"));
    }
    
    private void checkDirValue(final String s) throws IOException {
        final File file = new File(s);
        if ((!file.exists() || !file.isDirectory()) && !file.mkdirs()) {
            throw new IOException("Cannot Make Basic Directoy" + file.isDirectory() + file.mkdirs());
        }
    }
}
