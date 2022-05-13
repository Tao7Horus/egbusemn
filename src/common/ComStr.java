// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.text.NumberFormat;
import java.util.Locale;
import java.net.URLEncoder;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.io.UnsupportedEncodingException;

public class ComStr
{
    public static String checkNull(final String s) {
        return checkNull(s, "");
    }
    
    public static String checkNull(final String s, final String s1) {
        if (s == null) {
            return s1;
        }
        if (s.trim().length() == 0) {
            return s1;
        }
        if (s.equalsIgnoreCase("null")) {
            return s1;
        }
        return s;
    }
    
    public static byte[] getStringToByte(final String s) {
        try {
            return s.getBytes("KSC5601");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception) {
            return null;
        }
    }
    
    public static String getByteToString(final byte[] abyte0) {
        try {
            return new String(abyte0, "KSC5601");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception) {
            return null;
        }
    }
    
    public static String getKSC5601To8859_1(final String s) {
        if (s == null) {
            return "";
        }
        try {
            return new String(s.getBytes("KSC5601"), "ISO-8859-1");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception) {
            return "";
        }
    }
    
    public static String get8859_1ToKSC5601(final String s) {
        if (s == null) {
            return "";
        }
        try {
            return new String(s.getBytes("ISO-8859-1"), "KSC5601");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception) {
            return "";
        }
    }
    
    public static Vector getStringToVector(final String s, final String s1) {
        final Vector vector = new Vector();
        try {
            final StringTokenizer stringtokenizer = new StringTokenizer(s, s1);
            while (stringtokenizer.hasMoreTokens()) {
                vector.addElement(stringtokenizer.nextToken());
            }
            return vector;
        }
        catch (Exception exception) {
            return null;
        }
    }
    
    public static Vector getStringToVector(final String s, final char c) {
        return getStringToVector(s, c, false);
    }
    
    public static Vector getStringToVector(final String s, final char c, final boolean flag) {
        try {
            final Vector vector = new Vector();
            final ComTokenizer comtokenizer = new ComTokenizer(s, c, flag);
            while (comtokenizer.hasMoreTokens()) {
                vector.addElement(comtokenizer.nextToken());
            }
            return vector;
        }
        catch (Exception exception) {
            return null;
        }
    }
    
    public static Vector getVectorToStringTokenizer(final StringTokenizer stringtokenizer) throws NoSuchElementException {
        try {
            final Vector vector = new Vector();
            if (stringtokenizer != null) {
                int i = 0;
                while (stringtokenizer.hasMoreTokens()) {
                    vector.addElement(new String(stringtokenizer.nextToken()));
                    ++i;
                }
                return vector;
            }
            return null;
        }
        catch (NoSuchElementException nosuchelementexception) {
            throw nosuchelementexception;
        }
    }
    
    public static String[] getStringArrayToStringTokenizer(final StringTokenizer stringtokenizer) throws NoSuchElementException {
        try {
            final String[] as = new String[stringtokenizer.countTokens()];
            if (stringtokenizer != null) {
                int i = 0;
                while (stringtokenizer.hasMoreTokens()) {
                    as[i] = stringtokenizer.nextToken();
                    ++i;
                }
                return as;
            }
            return null;
        }
        catch (NoSuchElementException nosuchelementexception) {
            throw nosuchelementexception;
        }
    }
    
    public static String[] getStringToStringArray(final String s, final String s1) throws NoSuchElementException {
        try {
            return getStringToStringArray(s, s1, "");
        }
        catch (NoSuchElementException nosuchelementexception) {
            throw nosuchelementexception;
        }
    }
    
    public static String[] getStringToStringArray(final String s, final String s1, final String s2) throws NoSuchElementException {
        try {
            if (s == null) {
                final String[] as = { s2 };
                return as;
            }
            if (s.indexOf(s1) == -1) {
                final String[] as2 = { s.trim() };
                return as2;
            }
            final StringTokenizer stringtokenizer = new StringTokenizer(s, s1, false);
            final String[] as3 = new String[stringtokenizer.countTokens()];
            int i = 0;
            while (stringtokenizer.hasMoreTokens()) {
                as3[i] = stringtokenizer.nextToken();
                ++i;
            }
            return as3;
        }
        catch (NoSuchElementException nosuchelementexception) {
            throw nosuchelementexception;
        }
    }
    
    public static String[] getStringToStringArray(final String s, final char c) throws NoSuchElementException {
        try {
            if (s == null) {
                final String[] as = { "" };
                return as;
            }
            if (s.indexOf(c) == -1) {
                final String[] as2 = { s.trim() };
                return as2;
            }
            final ComTokenizer comtokenizer = new ComTokenizer(s, c, false);
            final String[] as3 = new String[comtokenizer.countTokens()];
            int i = 0;
            while (comtokenizer.hasMoreTokens()) {
                as3[i] = comtokenizer.nextToken();
                ++i;
            }
            return as3;
        }
        catch (NoSuchElementException nosuchelementexception) {
            throw nosuchelementexception;
        }
    }
    
    public static String LPAD(final String s, final int i, final char c) {
        String s2 = s;
        for (int j = i - s2.getBytes().length - 1; j >= 0; --j) {
            s2 = String.valueOf(c) + s2;
        }
        return s2;
    }
    
    public static String removeToken(final String s, final String s1) throws NoSuchElementException {
        try {
            final StringTokenizer stringtokenizer = new StringTokenizer(s, s1);
            String s2 = "";
            while (stringtokenizer.hasMoreTokens()) {
                s2 = String.valueOf(s2) + stringtokenizer.nextToken();
            }
            return s2;
        }
        catch (NoSuchElementException nosuchelementexception) {
            throw nosuchelementexception;
        }
    }
    
    public static String RPAD(final String s, final int i, final char c) {
        String s2 = s;
        for (int j = i - s2.getBytes().length - 1; j >= 0; --j) {
            s2 = String.valueOf(s2) + c;
        }
        return s2;
    }
    
    public static String replace(final String s, final String s1, final String s2) {
        if (s == null) {
            return null;
        }
        StringBuffer stringbuffer = new StringBuffer("");
        final int i = s.length();
        final int j = s1.length();
        final boolean flag = false;
        int l = 0;
        try {
            int k;
            while ((k = s.indexOf(s1, l)) >= 0) {
                stringbuffer.append(s.substring(l, k));
                stringbuffer.append(s2);
                l = k + j;
            }
            if (l < i) {
                stringbuffer.append(s.substring(l, i));
            }
            final String s3 = stringbuffer.toString();
            return s3;
        }
        catch (Exception exception) {
            return s;
        }
        finally {
            stringbuffer = null;
        }
    }
    
    public static String singleQuoteReplace(final String s) {
        return replace(s, "'", "''");
    }
    
    public static String split(final String s, final int i) {
        return split(s, i, "..");
    }
    
    public static String split(final String s, final int i, final String s1) {
        final String s2 = "";
        String s3 = "";
        int j = 0;
        for (int k = 0; k < s.length(); ++k) {
            final String s4 = s.substring(k, k + 1);
            if (URLEncoder.encode(s4.toString()).length() > 4) {
                j += 2;
            }
            else {
                ++j;
            }
            s3 = String.valueOf(s3) + s4;
            if (j >= i) {
                s3 = String.valueOf(s3) + s1;
                break;
            }
        }
        return s3;
    }
    
    public static String[] convertVectorToArray(final Vector vector) {
        if (vector == null) {
            return null;
        }
        final String[] as = new String[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            as[i] = vector.elementAt(i);
        }
        return as;
    }
    
    public static String divideComma(final String s, final String s1, final int i) {
        String s2 = "";
        String s3 = "";
        final String s4 = "";
        int j = 0;
        float f = 0.0f;
        final Object obj = null;
        final Vector vector = new Vector();
        try {
            f = Float.parseFloat(s);
        }
        catch (Exception exception) {
            return s;
        }
        final StringTokenizer stringtokenizer = new StringTokenizer(s, ".");
        while (stringtokenizer.hasMoreTokens()) {
            final String s5 = stringtokenizer.nextToken();
            vector.addElement(s5);
        }
        if (vector.size() > 1) {
            s3 = vector.elementAt(0);
            final String s6 = (String)vector.elementAt(1);
            for (int k = s3.length() - 1; k >= 0; --k) {
                s2 = String.valueOf(s3.charAt(k)) + s2;
                if (++j == i && k != 0) {
                    s2 = String.valueOf(s1) + s2;
                    j = 0;
                }
            }
            return String.valueOf(s2) + "." + s6;
        }
        s3 = vector.elementAt(0);
        for (int l = s3.length() - 1; l >= 0; --l) {
            s2 = String.valueOf(s3.charAt(l)) + s2;
            if (++j == i && l != 0) {
                s2 = String.valueOf(s1) + s2;
                j = 0;
            }
        }
        return s2;
    }
    
    public static String getStringToNumberFormat(final String s, final String s1) {
        if (s == null) {
            return s1;
        }
        long l = 0L;
        try {
            l = Long.parseLong(s);
        }
        catch (NumberFormatException numberformatexception) {
            return s;
        }
        return getStringToNumberFormat(l);
    }
    
    public static String getStringToNumberFormat(final String s) {
        return getStringToNumberFormat(s, "");
    }
    
    public static String getStringToNumberFormat(final int i) {
        return getStringToNumberFormat((long)new Integer(i));
    }
    
    public static String getStringToNumberFormat(final long l) {
        final NumberFormat numberformat = NumberFormat.getInstance(Locale.ENGLISH);
        return numberformat.format(l);
    }
    
    public static String getStringToNumberFormat(final String s, final int i) throws NumberFormatException {
        try {
            return getStringToNumberFormat(Long.parseLong(s), Long.parseLong(new StringBuffer().append(i).toString()));
        }
        catch (NumberFormatException numberformatexception) {
            throw numberformatexception;
        }
    }
    
    public static String getStringToNumberFormat(final String s, final long l) throws NumberFormatException {
        try {
            return getStringToNumberFormat(Long.parseLong(s), l);
        }
        catch (NumberFormatException numberformatexception) {
            throw numberformatexception;
        }
    }
    
    public static String getStringToNumberFormat(final int i, final int j) throws NumberFormatException {
        try {
            return getStringToNumberFormat(Long.parseLong(new StringBuffer().append(i).toString()), Long.parseLong(new StringBuffer().append(j).toString()));
        }
        catch (NumberFormatException numberformatexception) {
            throw numberformatexception;
        }
    }
    
    public static String getStringToNumberFormat(final int i, final long l) throws NumberFormatException {
        try {
            return getStringToNumberFormat(Long.parseLong(new StringBuffer().append(i).toString()), l);
        }
        catch (NumberFormatException numberformatexception) {
            throw numberformatexception;
        }
    }
    
    public static String getStringToNumberFormat(final long l, final int i) throws NumberFormatException {
        try {
            return getStringToNumberFormat(l, Long.parseLong(new StringBuffer().append(i).toString()));
        }
        catch (NumberFormatException numberformatexception) {
            throw numberformatexception;
        }
    }
    
    public static String getStringToNumberFormat(final long l, final long l1) throws NumberFormatException {
        long l2 = 0L;
        try {
            l2 = (long)(Object)new Double(Double.parseDouble("1e" + l1));
        }
        catch (NumberFormatException numberformatexception) {
            throw numberformatexception;
        }
        if (1000000000000000000L < l2) {
            throw new NumberFormatException(l2 + " is larger than 10e7 , must be smaller then 10e7");
        }
        if (10L <= l / l2) {
            return new StringBuffer().append(l).toString();
        }
        String s = new StringBuffer().append(l).toString();
        long l3;
        for (l3 = 10L; l3 <= l; l3 *= 10L) {}
        for (long l4 = 10L; l4 <= l2 / l3; l4 *= 10L) {
            s = "0" + s;
        }
        return s;
    }
    
    public static String[] StrToArray(final String s, final String s1, final int i) {
        final String[] as = new String[i];
        String s2 = s;
        int j = 0;
        while (s2.indexOf(s1) != -1) {
            final int k = s2.indexOf(s1);
            as[j] = s2.substring(0, k).trim();
            s2 = s2.substring(k + 1, s2.length());
            if (++j == i) {
                return as;
            }
        }
        return as;
    }
    
    public static String trim(final String s) {
        try {
            return s.trim();
        }
        catch (NullPointerException nullpointerexception) {
            return null;
        }
    }
    
    public static boolean larger(final String s, final String s1) {
        return larger(s, s1, false);
    }
    
    public static boolean larger(final String s, final String s1, final boolean flag) {
        if (s == null) {
            return s1 == null && flag;
        }
        if (s1 == null) {
            return false;
        }
        final int i = s.compareTo(s1);
        return i >= 0 && (i != 0 || flag);
    }
    
    public static boolean smaller(final String s, final String s1) {
        return larger(s1, s, false);
    }
    
    public static boolean smaller(final String s, final String s1, final boolean flag) {
        return larger(s1, s, flag);
    }
}
