// 
// Decompiled by Procyon v0.5.30
// 

package exms;

import java.io.UnsupportedEncodingException;

public class Kr
{
    public static String a2k(final String str) {
        String rtn = null;
        try {
            rtn = ((str == null) ? "" : new String(str.getBytes("8859_1"), "euc-kr"));
        }
        catch (UnsupportedEncodingException ex) {}
        return rtn;
    }
    
    public static String k2a(final String str) {
        String rtn = null;
        try {
            rtn = ((str == null) ? "" : new String(str.getBytes("euc-kr"), "8859_1"));
        }
        catch (UnsupportedEncodingException ex) {}
        return rtn;
    }
    
    public static String nchk(final String str) {
        return (str == null) ? "" : str;
    }
}
