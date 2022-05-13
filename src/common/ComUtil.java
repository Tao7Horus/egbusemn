// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.UnsupportedEncodingException;

public class ComUtil
{
    public static String[] retNullParameterCheck(final String[] array) throws Exception {
        if (array == null) {
            return null;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null || array[i].equals("")) {
                for (int j = 0; j < array.length; ++j) {
                    Log.debug(j + ":" + array[j]);
                }
                throw new Exception("Require Input parameter value");
            }
        }
        return array;
    }
    
    public static String retNull(final String s) {
        try {
            if (s == null || s.equals("")) {
                return null;
            }
        }
        catch (Exception ex) {
            Log.debug("ComUtil.retNull(" + s + "):" + ex.toString());
        }
        return s.trim();
    }
    
    public static String[] retNulls(final String[] array) {
        if (array == null) {
            return null;
        }
        try {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == null || array[i].equals("")) {
                    array[i] = null;
                }
            }
        }
        catch (Exception ex) {
            Log.debug("ComUtil.retNulls():" + ex.toString());
        }
        return array;
    }
    
    public static String retSpace(final String s) {
        try {
            if (s == null || s.equals("") || s.equals(null) || s == "") {
                return "";
            }
        }
        catch (Exception ex) {
            Log.debug("ComUtil.retNulls():" + ex.toString());
        }
        return s.trim();
    }
    
    public static String replace(final String s, final String s2, final String s3) {
        if (s == null || s.length() == 0 || s2 == null || s2.length() == 0 || s3 == null) {
            return s;
        }
        final StringBuffer sb = new StringBuffer("");
        final int length = s2.length();
        final int length2 = s.length();
        int n;
        int index;
        for (n = 0; (index = s.indexOf(s2, n)) >= 0; n = index + length) {
            sb.append(s.substring(n, index));
            sb.append(s3);
        }
        if (n < length2) {
            sb.append(s.substring(n, length2));
        }
        return sb.toString();
    }
    
    public static String encodeXML(final String s) {
        String s2 = retSpace(s);
        if (s2.length() == 0) {
            return s;
        }
        final String[] array = { "&", "<", ">" };
        final String[] array2 = { "&amp;", "&lt;", "&gt;" };
        for (int i = 0; i < array.length; ++i) {
            s2 = replace(s2, array[i], array2[i]);
        }
        return s2;
    }
    
    public static String ascToksc(final String s) throws UnsupportedEncodingException {
        if (s == null) {
            return null;
        }
        return new String(s.getBytes("8859_1"), "KSC5601");
    }
    
    public static String ascToksc_upside(final String s) throws UnsupportedEncodingException {
        if (s == null) {
            return null;
        }
        return new String(s.getBytes("KSC5601"), "8859_1");
    }
    
    public static String convertCurrency(String s) {
        s = replace(s, ".", "#");
        s = replace(s, ",", ".");
        s = replace(s, "#", ",");
        return s;
    }
}
