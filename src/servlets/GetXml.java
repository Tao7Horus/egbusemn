// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;

public class GetXml
{
    private String sUrl;
    public StringBuffer err;
    
    public GetXml(final String url) throws MalformedURLException {
        this.sUrl = new String(url);
        this.err = new StringBuffer(1024);
    }
    
    public String getError() {
        return this.err.toString();
    }
    
    public String getXmlTemplete(final String path) {
        URL Url = null;
        HttpURLConnection u = null;
        InputStream in = null;
        try {
            Url = new URL(String.valueOf(this.sUrl) + path);
            u = (HttpURLConnection)Url.openConnection();
            u.setDoInput(true);
            in = u.getInputStream();
            return this.getResponse(in);
        }
        catch (IOException e) {
            e.printStackTrace();
            this.err.append("IOException is occured! (" + e.getMessage() + ")");
            return "false";
        }
    }
    
    public String getXmlFrom_eXcelon(final String Unikey) {
        URL Url = null;
        HttpURLConnection u = null;
        InputStream in = null;
        final String path = "/" + Unikey.substring(1, 5) + "/" + Unikey.substring(5, 7) + "/" + Unikey.substring(7, 9) + "/" + Unikey;
        this.sUrl = String.valueOf(this.sUrl) + path;
        try {
            Url = new URL(this.sUrl);
            u = (HttpURLConnection)Url.openConnection();
            u.setDoInput(true);
            in = u.getInputStream();
            return this.getResponse(in);
        }
        catch (IOException e) {
            e.printStackTrace();
            this.err.append("IOException is occured! (" + e.getMessage() + ")");
            return "false";
        }
    }
    
    public String getXml(final String Unikey) {
        URL Url = null;
        HttpURLConnection u = null;
        InputStream in = null;
        this.sUrl = String.valueOf(this.sUrl) + "?unikey=" + Unikey + "&index=0";
        try {
            Url = new URL(this.sUrl);
            u = (HttpURLConnection)Url.openConnection();
            u.setDoInput(true);
            in = u.getInputStream();
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
        while ((line = reader.readLine()) != null) {
            buff.append(String.valueOf(line) + "\n");
        }
        if (reader != null) {
            reader.close();
        }
        this.err = buff;
        return buff.toString();
    }
}
