// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.UnsupportedEncodingException;
import java.io.ByteArrayOutputStream;
import javax.servlet.http.Cookie;
import java.net.URLEncoder;
import GT.EP_COB_GTQ906;
import GT.EP_COB_GTQ805;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

public class Gcookie
{
    public static final String site = "muasamcong";
    private static final String domain = ".mpi.gov.vn";
    private static final String IP = "IP";
    private String caller;
    private static final String UNIQUE_KEY = "G2B_EP";
    public static final String DEL1 = "^";
    public static final String DEL2 = "@#$%^&*";
    public static final String SPACE = " ";
    public static final int XML_GONGGO_NUM = 0;
    public static final int XML_GONGGO_CHA = 1;
    public static final int XML_GUBUN = 2;
    public static final int XML_REBID_NO = 3;
    public static final int XML_UPMU_GUBUN = 4;
    public static final int XML_DOC_TYPE = 5;
    public static final int YEGA_GONGGO_NUM = 6;
    public static final int YEGA_GONGGO_CHA = 7;
    public static final int YEGA_GUBUN = 8;
    public static final int YEGA_REBID_NO = 10;
    public static final int PRE_FOREIGN_IP = 9;
    private String[] decCookieArray;
    private String[] encCookieArray;
    private static char[] alphabet;
    private static byte[] codes;
    
    public Gcookie(final Object o) throws Exception {
        this.caller = "unknown";
        this.decCookieArray = new String[] { "", "", "", "", "", "", "", "", "", "", "" };
        this.encCookieArray = new String[] { null, null };
        if (o != null) {
            this.caller = o.getClass().getName();
        }
    }
    
    public Gcookie(final Object o, final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws Exception {
        this.caller = "unknown";
        this.decCookieArray = new String[] { "", "", "", "", "", "", "", "", "", "", "" };
        this.encCookieArray = new String[] { null, null };
        if (o != null) {
            this.caller = o.getClass().getName();
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.GetCookie(httpServletRequest, httpServletResponse, "G2B_EP"), "@#$%^&*");
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            this.encCookieArray[n] = stringTokenizer.nextToken();
            ++n;
        }
        if (EP_COB_GTQ805.retNull(this.encCookieArray[0]) == null) {
            Log.debug("Gcookie(" + this.caller + ",req,res),IP[" + EP_COB_GTQ906.getIP(httpServletRequest) + "] : Encryption key null");
            throw new Exception("There is not a encryption key.");
        }
        if (EP_COB_GTQ805.retNull(this.encCookieArray[1]) == null) {
            Log.debug("Gcookie(" + this.caller + ",req,res),IP[" + EP_COB_GTQ906.getIP(httpServletRequest) + "] : Encryption cookie null");
            throw new Exception("There is not a encryption cookie.");
        }
        final String decrypt = new SecuControl().decrypt(this.encCookieArray[0], this.encCookieArray[1]);
        int n2 = 0;
        final StringTokenizer stringTokenizer2 = new StringTokenizer(decrypt, "^");
        while (stringTokenizer2.hasMoreTokens()) {
            this.decCookieArray[n2] = stringTokenizer2.nextToken();
            ++n2;
        }
    }
    
    public String getCookieByNum(final int n) throws Exception {
        if (n < 0 || n > 10) {
            throw new Exception("index scope escaping, [0~10]");
        }
        return EP_COB_GTQ805.retNull(this.decCookieArray[n]);
    }
    
    public void SetCookie(final HttpServletResponse httpServletResponse, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) throws Exception {
        try {
            final String[] encrypt = new SecuControl().encrypt(s + "^" + s2 + "^" + s3 + "^" + s4 + "^" + s5 + "^" + s6 + "^" + " " + "^" + " " + "^" + " " + "^" + " " + "^" + " ");
            final Cookie cookie = new Cookie("G2B_EP", URLEncoder.encode(new String(encode((encrypt[0] + "@#$%^&*" + encrypt[1]).getBytes()))));
            cookie.setDomain(".mpi.gov.vn");
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            httpServletResponse.addCookie(cookie);
        }
        catch (Exception ex) {
            Log.debug("Gcookie.SetCookie() : caller:" + this.caller + " : " + ex.toString());
            throw ex;
        }
    }
    
    public void SetCookie(final HttpServletResponse httpServletResponse, final String s, final String s2, final String s3) throws Exception {
        try {
            final String[] encrypt = new SecuControl().encrypt(" ^ ^ ^ ^ ^ ^" + s + "^" + s2 + "^" + s3 + "^" + " " + "^" + " ");
            final Cookie cookie = new Cookie("G2B_EP", URLEncoder.encode(new String(encode((encrypt[0] + "@#$%^&*" + encrypt[1]).getBytes()))));
            cookie.setDomain(".mpi.gov.vn");
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            httpServletResponse.addCookie(cookie);
        }
        catch (Exception ex) {
            Log.debug("Gcookie.SetCookie('" + s + "','" + s2 + "','" + s3 + "') : caller:" + this.caller + " : " + ex.toString());
            throw ex;
        }
    }
    
    public void SetCookie(final HttpServletResponse httpServletResponse, final String s, final String s2, final String s3, final String s4) throws Exception {
        try {
            final String[] encrypt = new SecuControl().encrypt(" ^ ^ ^ ^ ^ ^" + s + "^" + s2 + "^" + s3 + "^" + " " + "^" + s4);
            final Cookie cookie = new Cookie("G2B_EP", URLEncoder.encode(new String(encode((encrypt[0] + "@#$%^&*" + encrypt[1]).getBytes()))));
            cookie.setDomain(".mpi.gov.vn");
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            httpServletResponse.addCookie(cookie);
        }
        catch (Exception ex) {
            Log.debug("Gcookie.SetCookie('" + s + "','" + s2 + "','" + s3 + "') : caller:" + this.caller + " : " + ex.toString());
            throw ex;
        }
    }
    
    public void SetCookie(final HttpServletResponse httpServletResponse, final HttpServletRequest httpServletRequest, final String s) throws Exception {
        try {
            final String[] encrypt = new SecuControl().encrypt(" ^ ^ ^ ^ ^ ^ ^ ^ ^" + EP_COB_GTQ906.getIP(httpServletRequest) + "^" + " ");
            final Cookie cookie = new Cookie("G2B_EP", URLEncoder.encode(new String(encode((encrypt[0] + "@#$%^&*" + encrypt[1]).getBytes()))));
            cookie.setDomain(".mpi.gov.vn");
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            httpServletResponse.addCookie(cookie);
        }
        catch (Exception ex) {
            Log.debug(EP_COB_GTQ906.getIP(httpServletRequest) + ":Gcookie.SetCookie() : caller:" + this.caller + " : " + ex.toString());
            throw new Exception(ex.getMessage());
        }
    }
    
    public void SetCookie(final HttpServletResponse httpServletResponse) {
        try {
            final Cookie cookie = new Cookie("site", URLEncoder.encode(new String(encode("muasamcong".getBytes()))));
            cookie.setDomain(".mpi.gov.vn");
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            httpServletResponse.addCookie(cookie);
        }
        catch (Exception ex) {
            Log.debug("Gcookie.SetCookie() : caller:" + this.caller + " : " + ex.toString());
        }
    }
    
    public void SetCookie(final HttpServletResponse httpServletResponse, final HttpServletRequest httpServletRequest) throws Exception {
        try {
            final Cookie cookie = new Cookie("site", URLEncoder.encode(new String(encode("muasamcong".getBytes()))));
            cookie.setDomain(".mpi.gov.vn");
            cookie.setPath("/");
            cookie.setMaxAge(-1);
            httpServletResponse.addCookie(cookie);
        }
        catch (Exception ex) {
            Log.debug(EP_COB_GTQ906.getIP(httpServletRequest) + ":Gcookie.SetCookie() : caller:" + this.caller + " : " + ex.toString());
            throw new Exception(ex.getMessage());
        }
    }
    
    public String GetCookie(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final String s) throws Exception {
        String ps_Decode = null;
        final Cookie[] cookies = httpServletRequest.getCookies();
        try {
            for (int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equals(s)) {
                    ps_Decode = this.PS_Decode(this.urldecode(cookies[i].getValue()));
                    break;
                }
            }
        }
        catch (Exception ex) {
            Log.errors(this, ex, "Caller:" + this.caller + ", IP:" + EP_COB_GTQ906.getIP(httpServletRequest));
            throw new Exception(ex.getMessage());
        }
        if (ps_Decode == null) {
            return ps_Decode;
        }
        return ps_Decode.trim();
    }
    
    public String urldecode(final String s) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(s.length());
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '+') {
                byteArrayOutputStream.write(32);
            }
            else if (char1 == '%') {
                byteArrayOutputStream.write(Character.digit(s.charAt(++i), 16) * 16 + Character.digit(s.charAt(++i), 16));
            }
            else {
                byteArrayOutputStream.write(char1);
            }
        }
        return byteArrayOutputStream.toString();
    }
    
    public static char[] encode(final byte[] array) {
        final char[] array2 = new char[(array.length + 2) / 3 * 4];
        for (int i = 0, n = 0; i < array.length; i += 3, n += 4) {
            boolean b = false;
            boolean b2 = false;
            int n2 = (0xFF & array[i]) << 8;
            if (i + 1 < array.length) {
                n2 |= (0xFF & array[i + 1]);
                b2 = true;
            }
            int n3 = n2 << 8;
            if (i + 2 < array.length) {
                n3 |= (0xFF & array[i + 2]);
                b = true;
            }
            array2[n + 3] = Gcookie.alphabet[b ? (n3 & 0x3F) : 64];
            final int n4 = n3 >> 6;
            array2[n + 2] = Gcookie.alphabet[b2 ? (n4 & 0x3F) : 64];
            final int n5 = n4 >> 6;
            array2[n + 1] = Gcookie.alphabet[n5 & 0x3F];
            array2[n + 0] = Gcookie.alphabet[n5 >> 6 & 0x3F];
        }
        return array2;
    }
    
    public static byte[] decode(final char[] array) {
        int n = (array.length + 3) / 4 * 3;
        if (array.length > 0 && array[n - 1] == '=') {
            --n;
        }
        if (array.length > 0 && array[n - 2] == '=') {
            --n;
        }
        final byte[] array2 = new byte[n];
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < array.length; ++i) {
            final byte b = Gcookie.codes[array[i] & 'ÿ'];
            if (b >= 0) {
                final int n5 = n3 << 6;
                n2 += 6;
                n3 = (n5 | b);
                if (n2 >= 8) {
                    n2 -= 8;
                    array2[n4++] = (byte)(n3 >> n2 & 0xFF);
                }
            }
        }
        if (n4 != array2.length) {
            throw new Error("miscalculated data length!");
        }
        return array2;
    }
    
    public String PS_Decode(final String s) {
        final StringBuffer sb = new StringBuffer();
        final String s2 = "";
        try {
            if (s.length() < 1) {
                return s2;
            }
            final int n = s.length() / 4 * 3;
            final byte[] array = new byte[n];
            final byte[] array2 = new byte[2];
            int n2 = 0;
            for (int i = 0; i < s.length(); i += 4) {
                final byte bchngValue = this.BchngValue(s.charAt(i));
                final byte bchngValue2 = this.BchngValue(s.charAt(i + 1));
                byte bchngValue3;
                if (s.charAt(i + 2) == '=') {
                    bchngValue3 = 0;
                }
                else {
                    bchngValue3 = this.BchngValue(s.charAt(i + 2));
                }
                byte bchngValue4;
                if (s.charAt(i + 3) == '=') {
                    bchngValue4 = 0;
                }
                else {
                    bchngValue4 = this.BchngValue(s.charAt(i + 3));
                }
                array[n2] = (byte)((byte)(bchngValue << 2) | (byte)((byte)(bchngValue2 & 0x30) >> 4));
                final byte b = (byte)(bchngValue2 << 4);
                final byte b2 = bchngValue3;
                array[n2 + 1] = (byte)(b | (byte)((byte)(bchngValue3 & 0x3C) >> 2));
                array[n2 + 2] = (byte)((byte)((byte)(b2 & 0x3) << 6) | bchngValue4);
                n2 += 3;
            }
            for (int j = 0; j < n; ++j) {
                if (array[j] >= 0 && array[j] <= 127) {
                    sb.append((char)array[j]);
                }
                else if (array[j] >= -128 && array[j] <= -1) {
                    array2[0] = array[j];
                    array2[1] = array[j + 1];
                    try {
                        sb.append(new String(array2, "KSC5601"));
                    }
                    catch (UnsupportedEncodingException ex) {}
                    ++j;
                }
            }
            return new String(sb.toString());
        }
        catch (Exception ex2) {
            System.out.println("Error In Decode:");
            return s;
        }
    }
    
    private byte BchngValue(final char c) {
        byte b = (byte)c;
        if (b >= 65 && b <= 90) {
            b -= 65;
        }
        else if (b >= 97 && b <= 122) {
            b -= 71;
        }
        else if (b >= 48 && b <= 57) {
            b += 4;
        }
        else if (b == 43) {
            b += 19;
        }
        else if (b == 45) {
            b += 18;
        }
        return b;
    }
    
    static {
        Gcookie.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
        Gcookie.codes = new byte[256];
        for (int i = 0; i < 256; ++i) {
            Gcookie.codes[i] = -1;
        }
        for (int j = 65; j <= 90; ++j) {
            Gcookie.codes[j] = (byte)(j - 65);
        }
        for (int k = 97; k <= 122; ++k) {
            Gcookie.codes[k] = (byte)(26 + k - 97);
        }
        for (int l = 48; l <= 57; ++l) {
            Gcookie.codes[l] = (byte)(52 + l - 48);
        }
        Gcookie.codes[43] = 62;
        Gcookie.codes[47] = 63;
    }
}
