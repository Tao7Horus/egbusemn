// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.UnsupportedEncodingException;
import java.io.ByteArrayOutputStream;
import common.util.HttpUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;

public class UseCookie
{
    public static final String domain = ".muasamcong.mpi.gov.vn";
    private String caller;
    private static char[] alphabet;
    private static byte[] codes;
    
    static {
        UseCookie.alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
        UseCookie.codes = new byte[256];
        for (int i = 0; i < 256; ++i) {
            UseCookie.codes[i] = -1;
        }
        for (int i = 65; i <= 90; ++i) {
            UseCookie.codes[i] = (byte)(i - 65);
        }
        for (int i = 97; i <= 122; ++i) {
            UseCookie.codes[i] = (byte)(26 + i - 97);
        }
        for (int i = 48; i <= 57; ++i) {
            UseCookie.codes[i] = (byte)(52 + i - 48);
        }
        UseCookie.codes[43] = 62;
        UseCookie.codes[47] = 63;
    }
    
    public UseCookie(final Object caller_obj) throws Exception {
        this.caller = "unknown";
        if (caller_obj != null) {
            this.caller = caller_obj.getClass().getName();
        }
    }
    
    public void SetCookie(final HttpServletResponse res, final String ID, final String sosokGubun, final String deleteGubun, final String okGubun) throws Exception {
        try {
            final Cookie c_ID = new Cookie("usemnID", URLEncoder.encode(new String(encode(ID.getBytes()))));
            final Cookie c_sosokGubun = new Cookie("usemnSosok", URLEncoder.encode(new String(encode(sosokGubun.getBytes()))));
            final Cookie c_deleteGubun = new Cookie("usemnDeleteGubun", URLEncoder.encode(new String(encode(deleteGubun.getBytes()))));
            final Cookie c_okGubun = new Cookie("usemnOkGubun", URLEncoder.encode(new String(encode(okGubun.getBytes()))));
            c_ID.setDomain(".muasamcong.mpi.gov.vn");
            c_ID.setPath("/");
            c_ID.setMaxAge(-1);
            c_sosokGubun.setDomain(".muasamcong.mpi.gov.vn");
            c_sosokGubun.setPath("/");
            c_sosokGubun.setMaxAge(-1);
            c_deleteGubun.setDomain(".muasamcong.mpi.gov.vn");
            c_deleteGubun.setPath("/");
            c_deleteGubun.setMaxAge(-1);
            c_okGubun.setDomain(".muasamcong.mpi.gov.vn");
            c_okGubun.setPath("/");
            c_okGubun.setMaxAge(-1);
            res.addCookie(c_ID);
            res.addCookie(c_sosokGubun);
            res.addCookie(c_deleteGubun);
            res.addCookie(c_okGubun);
        }
        catch (Exception ex) {
            Log.debug("Gcookie.SetCookie('" + ID + "','" + sosokGubun + "','" + deleteGubun + "','" + okGubun + "') : caller:" + this.caller + " : " + ex.toString());
            throw new Exception(ex.getMessage());
        }
    }
    
    public boolean isPasswordOk(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        boolean result = true;
        final String cookieSet = this.GetCookie(req, res, "usemnOkGubun");
        if (cookieSet == null || !cookieSet.equals("OK")) {
            result = false;
        }
        return result;
    }
    
    public void isCookieNullSet(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
        this.SetCookie(res, "", "", "", "");
    }
    
    public String GetCookie(final HttpServletRequest req, final HttpServletResponse res, final String param) throws Exception {
        String retstr = null;
        final String value = null;
        String retval = null;
        final Cookie[] cookies = req.getCookies();
        try {
            for (int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equals(param)) {
                    retstr = cookies[i].getValue();
                    retval = this.PS_Decode(this.urldecode(retstr));
                    break;
                }
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(HttpUtility.getIP(req)) + ":Gcookie.GetCookie() : caller:" + this.caller + " : " + e.toString());
            throw new Exception(e.getMessage());
        }
        if (retval == null) {
            return retval;
        }
        return retval.trim();
    }
    
    public String urldecode(final String s) {
        final ByteArrayOutputStream out = new ByteArrayOutputStream(s.length());
        for (int i = 0; i < s.length(); ++i) {
            final int c = s.charAt(i);
            if (c == 43) {
                out.write(32);
            }
            else if (c == 37) {
                final int c2 = Character.digit(s.charAt(++i), 16);
                final int c3 = Character.digit(s.charAt(++i), 16);
                out.write(c2 * 16 + c3);
            }
            else {
                out.write(c);
            }
        }
        return out.toString();
    }
    
    public static char[] encode(final byte[] data) {
        final char[] out = new char[(data.length + 2) / 3 * 4];
        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
            boolean quad = false;
            boolean trip = false;
            int val = 0xFF & data[i];
            val <<= 8;
            if (i + 1 < data.length) {
                val |= (0xFF & data[i + 1]);
                trip = true;
            }
            val <<= 8;
            if (i + 2 < data.length) {
                val |= (0xFF & data[i + 2]);
                quad = true;
            }
            out[index + 3] = UseCookie.alphabet[quad ? (val & 0x3F) : 64];
            val >>= 6;
            out[index + 2] = UseCookie.alphabet[trip ? (val & 0x3F) : 64];
            val >>= 6;
            out[index + 1] = UseCookie.alphabet[val & 0x3F];
            val >>= 6;
            out[index + 0] = UseCookie.alphabet[val & 0x3F];
        }
        return out;
    }
    
    public static byte[] decode(final char[] data) {
        int len = (data.length + 3) / 4 * 3;
        if (data.length > 0 && data[len - 1] == '=') {
            --len;
        }
        if (data.length > 0 && data[len - 2] == '=') {
            --len;
        }
        final byte[] out = new byte[len];
        int shift = 0;
        int accum = 0;
        int index = 0;
        for (int ix = 0; ix < data.length; ++ix) {
            final int value = UseCookie.codes[data[ix] & 'ÿ'];
            if (value >= 0) {
                accum <<= 6;
                shift += 6;
                accum |= value;
                if (shift >= 8) {
                    shift -= 8;
                    out[index++] = (byte)(accum >> shift & 0xFF);
                }
            }
        }
        if (index != out.length) {
            throw new Error("miscalculated data length!");
        }
        return out;
    }
    
    public String PS_Decode(final String str) {
        final StringBuffer sb = new StringBuffer();
        String returnStr = "";
        try {
            if (str.length() < 1) {
                return returnStr;
            }
            final int bytelen = str.length() / 4 * 3;
            final byte[] arraybyte = new byte[bytelen];
            final byte[] hanchar = new byte[2];
            int j = 0;
            for (int i = 0; i < str.length(); i += 4) {
                byte tmp1 = this.BchngValue(str.charAt(i));
                byte tmp2 = this.BchngValue(str.charAt(i + 1));
                byte tmp3;
                if (str.charAt(i + 2) == '=') {
                    tmp3 = 0;
                }
                else {
                    tmp3 = this.BchngValue(str.charAt(i + 2));
                }
                byte tmp4;
                if (str.charAt(i + 3) == '=') {
                    tmp4 = 0;
                }
                else {
                    tmp4 = this.BchngValue(str.charAt(i + 3));
                }
                byte chr = tmp1;
                chr = (byte)(tmp1 << 2);
                tmp1 = tmp2;
                tmp1 &= 0x30;
                tmp1 >>= 4;
                chr |= tmp1;
                arraybyte[j] = chr;
                tmp2 <<= 4;
                tmp1 = tmp3;
                chr = (byte)((byte)(tmp3 & 0x3C) >> 2);
                chr |= tmp2;
                arraybyte[j + 1] = chr;
                tmp1 = (byte)((byte)(tmp1 & 0x3) << 6);
                chr = (byte)(tmp1 | tmp4);
                arraybyte[j + 2] = chr;
                j += 3;
            }
            for (int k = 0; k < bytelen; ++k) {
                if (arraybyte[k] >= 0 && arraybyte[k] <= 127) {
                    sb.append((char)arraybyte[k]);
                }
                else if (arraybyte[k] >= -128 && arraybyte[k] <= -1) {
                    hanchar[0] = arraybyte[k];
                    hanchar[1] = arraybyte[k + 1];
                    try {
                        sb.append(new String(hanchar, "KSC5601"));
                    }
                    catch (UnsupportedEncodingException ex) {}
                    ++k;
                }
            }
            returnStr = new String(sb.toString());
            return returnStr;
        }
        catch (Exception e) {
            System.out.println("Error In Decode:");
            return str;
        }
    }
    
    private byte BchngValue(final char chr) {
        byte temp = (byte)chr;
        if (temp >= 65 && temp <= 90) {
            temp -= 65;
        }
        else if (temp >= 97 && temp <= 122) {
            temp -= 71;
        }
        else if (temp >= 48 && temp <= 57) {
            temp += 4;
        }
        else if (temp == 43) {
            temp += 19;
        }
        else if (temp == 45) {
            temp += 18;
        }
        return temp;
    }
}
