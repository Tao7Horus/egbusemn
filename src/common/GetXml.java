// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class GetXml
{
    private String sUrl;
    public StringBuffer err;
    
    public GetXml(final String s) throws MalformedURLException {
        this.sUrl = new String(s);
        this.err = new StringBuffer(1024);
    }
    
    public String getError() {
        return this.err.toString();
    }
    
    public String getXmlTemplate(final String s) {
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.sUrl + s).openConnection();
            httpURLConnection.setDoInput(true);
            return this.getResponse(httpURLConnection.getInputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
            this.err.append("IOException is occured! (" + ex.getMessage() + ")");
            return "false";
        }
    }
    
    public String getXmlFrom_eXcelon(final String s) {
        this.sUrl += "/" + s.substring(1, 5) + "/" + s.substring(5, 7) + "/" + s.substring(7, 9) + "/" + s;
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.sUrl).openConnection();
            httpURLConnection.setDoInput(true);
            return this.getResponse(httpURLConnection.getInputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
            this.err.append("IOException is occured! (" + ex.getMessage() + ")");
            return "false";
        }
    }
    
    public String getXml(final String s) {
        this.sUrl = this.sUrl + "?unikey=" + s + "&index=0";
        try {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.sUrl).openConnection();
            httpURLConnection.setDoInput(true);
            return this.getResponse(httpURLConnection.getInputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
            this.err.append("IOException is occured! (" + ex.getMessage() + ")");
            return "false";
        }
    }
    
    private String getResponse(final InputStream inputStream) throws IOException {
        final StringBuffer err = new StringBuffer(4096);
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            err.append(line + "\n");
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        this.err = err;
        return err.toString();
    }
}
