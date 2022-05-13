// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.text.DecimalFormat;
import java.io.InputStream;
import java.net.NoRouteToHostException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

public class NameCheck
{
    public int ErrCode;
    private String ChkName;
    private String Jumin;
    private String SiteCode;
    private String EncJumin;
    private int TimeOut;
    final short PROC_OK = 0;
    final short DATA_ERR = 21;
    
    public NameCheck() {
        this.ErrCode = 0;
        this.ChkName = "";
        this.Jumin = "";
        this.SiteCode = "";
        this.EncJumin = "";
        this.TimeOut = 0;
    }
    
    public String getEnc() {
        return this.EncJumin;
    }
    
    public void setChkName(final String s) {
        this.ChkName = s;
    }
    
    public String setJumin(final String s) {
        this.Jumin = s.trim();
        return String.valueOf(this.getEncJumin(this.Jumin, 21));
    }
    
    public void setSiteCode(final String s) {
        this.SiteCode = s;
    }
    
    public void setTimeOut(final int i) {
        this.TimeOut = i;
    }
    
    public String getRtn() {
        return this.getNameCheck();
    }
    
    private int getRandom() {
        return Math.abs((int)(Object)new Long(System.currentTimeMillis()));
    }
    
    private String getNameCheck() {
        String s = "";
        Socket socket = null;
        InputStream inputstream = null;
        PrintWriter printwriter = null;
        try {
            final int i = this.getRandom();
            final String s2 = "http://203.234.219.72/cnm.asp";
            final URL url = new URL(s2);
            final String s3 = url.getHost();
            final int j = 81 + i % 5;
            final String s4 = url.getFile();
            socket = new Socket(s3, j);
            socket.setSoTimeout(this.TimeOut);
            printwriter = new PrintWriter(socket.getOutputStream(), false);
            inputstream = socket.getInputStream();
            final StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append(String.valueOf(URLEncoder.encode("a3")) + "=" + URLEncoder.encode(this.ChkName) + "&");
            stringbuffer.append(String.valueOf(URLEncoder.encode("a2")) + "=" + URLEncoder.encode(this.EncJumin) + "&");
            stringbuffer.append(String.valueOf(URLEncoder.encode("a1")) + "=" + URLEncoder.encode(this.SiteCode));
            final int k = stringbuffer.toString().length();
            final StringBuffer stringbuffer2 = new StringBuffer();
            stringbuffer2.append("POST " + s4 + " HTTP/1.1\n");
            stringbuffer2.append("Accept: */*\n");
            stringbuffer2.append("Connection: close\n");
            stringbuffer2.append("Host: wtname.creditbank.co.kr\n");
            stringbuffer2.append("Content-Type: application/x-www-form-urlencoded\n");
            stringbuffer2.append("Content-Length: " + k + "\r\n");
            stringbuffer2.append("\r\n");
            stringbuffer2.append(stringbuffer.toString());
            printwriter.print(stringbuffer2.toString());
            printwriter.flush();
            stringbuffer2.setLength(0);
            final String s5 = "";
            int l = 0;
            for (boolean flag = true; flag && l != -1; flag = ((l = inputstream.read()) != 114 || (l = inputstream.read()) != 101 || (l = inputstream.read()) != 115 || (l = inputstream.read()) != 117 || (l = inputstream.read()) != 108 || (l = inputstream.read()) != 116 || (l = inputstream.read()) != 61)) {}
            final byte[] abyte0 = new byte[2];
            inputstream.read(abyte0);
            printwriter.close();
            inputstream.close();
            socket.close();
            socket = null;
            inputstream = null;
            printwriter = null;
            s = new String(abyte0, "KSC5601").toString();
        }
        catch (MalformedURLException malformedurlexception) {
            if (printwriter != null) {
                try {
                    printwriter.close();
                    printwriter = null;
                }
                catch (Exception ex) {}
            }
            if (inputstream != null) {
                try {
                    inputstream.close();
                    inputstream = null;
                }
                catch (Exception ex2) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                    socket = null;
                }
                catch (Exception ex3) {}
            }
            s = "62";
        }
        catch (NoRouteToHostException noroutetohostexception) {
            if (printwriter != null) {
                try {
                    printwriter.close();
                    printwriter = null;
                }
                catch (Exception ex4) {}
            }
            if (inputstream != null) {
                try {
                    inputstream.close();
                    inputstream = null;
                }
                catch (Exception ex5) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                    socket = null;
                }
                catch (Exception ex6) {}
            }
            s = "61";
        }
        catch (Exception exception6) {
            if (printwriter != null) {
                try {
                    printwriter.close();
                    printwriter = null;
                }
                catch (Exception ex7) {}
            }
            if (inputstream != null) {
                try {
                    inputstream.close();
                    inputstream = null;
                }
                catch (Exception ex8) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                    socket = null;
                }
                catch (Exception ex9) {}
            }
            s = "63";
        }
        finally {
            if (printwriter != null) {
                try {
                    printwriter.close();
                    printwriter = null;
                }
                catch (Exception ex10) {}
            }
            if (inputstream != null) {
                try {
                    inputstream.close();
                    inputstream = null;
                }
                catch (Exception ex11) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                    socket = null;
                }
                catch (Exception ex12) {}
            }
        }
        if (printwriter != null) {
            try {
                printwriter.close();
                printwriter = null;
            }
            catch (Exception ex13) {}
        }
        if (inputstream != null) {
            try {
                inputstream.close();
                inputstream = null;
            }
            catch (Exception ex14) {}
        }
        if (socket != null) {
            try {
                socket.close();
                socket = null;
            }
            catch (Exception ex15) {}
        }
        return s;
    }
    
    private int getEncJumin(final String s, final int i) {
        final String s2 = "13814175622071120141181061768611993108841416921423107181672510714175411266712670119411737212225184002090820525212741182820947153241426022005196831631213938161862274312787181662007815703134602165910388182131264812368213511080911151159881253313998114131777122809215401592122930118692301418370102821668712210148061538513870120181727420355200961795413534192821169714960142231124510693129551063218404145651690617787165521359419983207241159515423148221137115237203671177021155195251257514999190251531020044";
        int j = 0;
        int k = 0;
        int l = 0;
        int i2 = 0;
        int j2 = 0;
        final String s3 = "";
        final String s4 = "";
        String s5 = s;
        final String s6 = "00";
        String s7 = "";
        final String s8 = "";
        String s9 = "";
        s5.trim();
        if (i != s5.length()) {
            return 21;
        }
        j = i - i / 3 * 3;
        if (j == 2) {
            s5 = String.valueOf(s5) + "00";
        }
        else if (j == 1) {
            s5 = String.valueOf(s5) + "0";
        }
        k = (int)(Math.random() * 100.0);
        s9 = s2.substring(k * 5, k * 5 + 5);
        l = Integer.valueOf(s9);
        i2 = s5.length() / 3 / 2;
        DecimalFormat decimalformat = null;
        decimalformat = new DecimalFormat("00");
        s7 = decimalformat.format(k);
        decimalformat = new DecimalFormat("00000");
        for (j2 = 0; j2 < i2; ++j2) {
            String s10 = s5.substring(j2 * 2 * 3, j2 * 2 * 3 + 3);
            final String s11 = decimalformat.format(new Integer(s10) + l);
            s10 = s5.substring((j2 * 2 + 1) * 3, (j2 * 2 + 1) * 3 + 3);
            final String s12 = decimalformat.format(new Integer(s10) + l);
            s7 = String.valueOf(s7) + s12;
            s7 = String.valueOf(s7) + s11;
        }
        if (i2 * 2 < s5.length() / 3) {
            final String s13 = s5.substring(j2 * 2 * 3, j2 * 2 * 3 + 3);
            final String s14 = decimalformat.format(new Integer(s13) + l);
            s7 = String.valueOf(s7) + s14;
        }
        this.EncJumin = s7;
        return 0;
    }
}
