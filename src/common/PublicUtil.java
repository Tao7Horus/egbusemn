// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.io.UnsupportedEncodingException;

public class PublicUtil
{
    public static String[] retNullParameterCheck(final String[] s) throws Exception {
        if (s == null) {
            return null;
        }
        for (int i = 0, n = s.length; i < n; ++i) {
            if (s[i] == null || s[i].equals("")) {
                for (int j = 0, m = s.length; j < m; ++j) {
                    Log.debug(String.valueOf(j) + ":" + s[j]);
                }
                throw new Exception("인자값에 대한 점검이 필요합니다.");
            }
        }
        return s;
    }
    
    public static String retNull(final String s) {
        try {
            if (s == null || s.equals("")) {
                return null;
            }
        }
        catch (Exception e) {
            Log.debug("PublicUtil.retNull(" + s + "):" + e.toString());
        }
        return s.trim();
    }
    
    public static String[] retNulls(final String[] s) {
        if (s == null) {
            return null;
        }
        try {
            for (int i = 0; i < s.length; ++i) {
                if (s[i] == null || s[i].equals("")) {
                    s[i] = null;
                }
            }
        }
        catch (Exception e) {
            Log.debug("PublicUtil.retNulls():" + e.toString());
        }
        return s;
    }
    
    public static String retSpace(final String s) {
        try {
            if (s == null || s.equals("") || s.equals(null) || s == "") {
                return "";
            }
        }
        catch (Exception e) {
            Log.debug("PublicUtil.retNulls():" + e.toString());
        }
        return s.trim();
    }
    
    public static String replace(final String strOriginal, final String fromStr, final String toStr) {
        if (strOriginal == null || strOriginal.length() == 0 || fromStr == null || fromStr.length() == 0 || toStr == null || toStr.length() == 0) {
            return null;
        }
        final StringBuffer dest = new StringBuffer("");
        final int len = fromStr.length();
        final int srclen = strOriginal.length();
        int pos;
        int oldpos;
        for (pos = 0, oldpos = 0; (pos = strOriginal.indexOf(fromStr, oldpos)) >= 0; oldpos = pos + len) {
            dest.append(strOriginal.substring(oldpos, pos));
            dest.append(toStr);
        }
        if (oldpos < srclen) {
            dest.append(strOriginal.substring(oldpos, srclen));
        }
        return dest.toString();
    }
    
    public static String ascToksc(final String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return new String(str.getBytes("8859_1"), "KSC5601");
    }
    
    public static String ascToksc_upside(final String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return new String(str.getBytes("KSC5601"), "8859_1");
    }
}
