// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.util.Enumeration;
import java.net.URLEncoder;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.io.DataOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

public class PostCGI1
{
    private Hashtable parm;
    private URL target;
    private String _Authorization;
    public StringBuffer err;
    
    public PostCGI1(final String url, final String Id, final String Password) throws MalformedURLException {
        this.target = new URL(url);
        this.parm = new Hashtable(10);
        this._Authorization = new String("Basic " + new String(Base64.encode((String.valueOf(Id) + ":" + Password).getBytes())));
        this.err = new StringBuffer(1024);
    }
    
    public PostCGI1(final String url, final String Id) throws MalformedURLException {
        this(url, Id, "NONE");
    }
    
    public PostCGI1(final String url) throws MalformedURLException {
        this(url, "NONE", "NONE");
    }
    
    public int appendParm(final String name, final String value) {
        if (name == null || value == null || name.length() < 1) {
            this.err.append("name,value,length is null or length is small than 1");
            return -1;
        }
        this.parm.put(name, value);
        return 1;
    }
    
    public String getError() {
        return this.err.toString();
    }
    
    public String postData(final String checkString) {
        try {
            final URLConnection u = this.target.openConnection();
            u.setRequestProperty("Authorization", this._Authorization);
            u.setDoOutput(true);
            u.setDoInput(true);
            final DataOutputStream out = new DataOutputStream(u.getOutputStream());
            out.writeBytes(this.mergeParm());
            out.flush();
            out.close();
            final InputStream in = u.getInputStream();
            return this.getResponse(in);
        }
        catch (IOException e) {
            e.printStackTrace();
            this.err.append("IOException is occured! (" + e.getMessage() + ")");
            return "false";
        }
    }
    
    private String getResponse(final InputStream in) throws IOException {
        final StringBuffer buff = new StringBuffer(4096);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        buff.append(line);
        while ((line = reader.readLine()) != null) {
            buff.append(line);
        }
        if (reader != null) {
            reader.close();
        }
        this.err = buff;
        return buff.toString();
    }
    
    private String mergeParm() {
        final StringBuffer buff = new StringBuffer(4096);
        if (this.parm.isEmpty()) {
            this.err.append("has no parameter!!");
            return "";
        }
        final Enumeration e = this.parm.keys();
        while (e.hasMoreElements()) {
            final String name = (String)e.nextElement();
            final String value = (String)this.parm.get(name);
            buff.append(String.valueOf(name) + "=" + URLEncoder.encode(value) + "&");
        }
        String tmp = buff.toString();
        tmp = tmp.substring(0, buff.length() - 1);
        return tmp;
    }
}
