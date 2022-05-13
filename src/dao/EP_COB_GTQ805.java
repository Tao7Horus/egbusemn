// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.io.UnsupportedEncodingException;
import common.Log;

public class EP_COB_GTQ805
{
    public static String retNull(final String s) {
        try {
            if (s == null || s.equals("")) {
                return null;
            }
        }
        catch (Exception ex) {
            Log.debug("EP_COB_GTQ805.retNull(" + s + "):" + ex.toString());
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
            Log.debug("EP_COB_GTQ805.retNulls():" + ex.toString());
        }
        return array;
    }
    
    public static String retSpace(final String s) {
        try {
            if (s == null || s.equals("")) {
                return "";
            }
        }
        catch (Exception ex) {
            Log.debug("EP_COB_GTQ805.retNulls():" + ex.toString());
        }
        return s.trim();
    }
    
    public static String replace(final String s, final String s2, final String s3) {
        final StringBuffer sb = new StringBuffer(s);
        replace(sb, s2, s3);
        return sb.toString();
    }
    
    public static int replace(final StringBuffer sb, final String s, final String s2) {
        if (sb == null) {
            return 0;
        }
        if (s == null) {
            return 0;
        }
        if (s2 == null) {
            return 0;
        }
        int n = 0;
        while (true) {
            final int index = new String(sb).indexOf(s);
            final int n2 = index + s.length();
            if (index == -1) {
                break;
            }
            ++n;
            sb.replace(index, n2, s2);
        }
        return n;
    }
    
    public static String replace(final String s, final char c, final String s2) {
        String s3 = "";
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                s3 += s2;
            }
            else {
                s3 += s.charAt(i);
            }
        }
        return s3;
    }
    
    public String[] toUTF8(final String[] array) throws UnsupportedEncodingException {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == null || array[i].equals("null") || array[i].equals("")) {
                    array[i] = "";
                }
                else {
                    array[i] = new String(array[i].getBytes("UTF-8"), "UTF-8");
                }
            }
        }
        return array;
    }
    
    public static String utf8Toutf8(final String s) throws UnsupportedEncodingException {
        if (s == null) {
            return null;
        }
        return new String(s.getBytes("UTF-8"), "UTF-8");
    }
    
    public static String ascToksc(final String s) throws UnsupportedEncodingException {
        return utf8Toutf8(s);
    }
    
    public static String eucToeuc(final String s) throws UnsupportedEncodingException {
        return utf8Toutf8(s);
    }
}
