// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.util.StringTokenizer;
import signgate.crypto.util.InvalidBase64Exception;

public class Base64Util
{
    public static String encode(final String s) {
        return encode(s.getBytes(), true);
    }
    
    public static String encode(final String s, final boolean b) {
        return encode(s.getBytes(), b);
    }
    
    public static String encode(final byte[] array) {
        return encode(array, true);
    }
    
    public static String encode(final byte[] array, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        if (b) {
            for (int i = 0; i < array.length; i += 3) {
                if (i % 48 == 0 && i != 0) {
                    sb.append("\n");
                }
                sb.append(encodedBlock(array, i));
            }
        }
        else {
            for (int j = 0; j < array.length; j += 3) {
                sb.append(encodedBlock(array, j));
            }
        }
        return sb.toString();
    }
    
    protected static char[] encodedBlock(final byte[] array, final int n) {
        int n2 = 0;
        final int n3 = array.length - n - 1;
        for (int n4 = (n3 < 2) ? n3 : 2, i = 0; i <= n4; ++i) {
            final byte b = array[n + i];
            n2 += ((b >= 0) ? b : (b + 256)) << 8 * (2 - i);
        }
        final char[] array2 = new char[4];
        for (int j = 0; j < 4; ++j) {
            array2[j] = getChar(n2 >>> 6 * (3 - j) & 0x3F);
        }
        if (n3 < 1) {
            array2[2] = '=';
        }
        if (n3 < 2) {
            array2[3] = '=';
        }
        return array2;
    }
    
    protected static char getChar(final int n) {
        if (n >= 0 && n <= 25) {
            return (char)(65 + n);
        }
        if (n >= 26 && n <= 51) {
            return (char)(97 + (n - 26));
        }
        if (n >= 52 && n <= 61) {
            return (char)(48 + (n - 52));
        }
        if (n == 62) {
            return '+';
        }
        return (n != 63) ? '?' : '/';
    }
    
    public static byte[] decode(final String s) throws InvalidBase64Exception {
        int n = 0;
        final String delCRLF = delCRLF(reform(s));
        if (delCRLF.length() % 4 != 0) {
            throw new InvalidBase64Exception("Data is not Base64 encoding type.(Data length error)");
        }
        if (!isBase64(delCRLF.getBytes())) {
            throw new InvalidBase64Exception("Data is not Base64 encoding type.(String set error)");
        }
        for (int n2 = delCRLF.length() - 1; delCRLF.charAt(n2) == '='; --n2) {
            ++n;
        }
        final byte[] array = new byte[delCRLF.length() * 6 / 8 - n];
        int n3 = 0;
        for (int i = 0; i < delCRLF.length(); i += 4) {
            final int n4 = (getValue(delCRLF.charAt(i)) << 18) + (getValue(delCRLF.charAt(i + 1)) << 12) + (getValue(delCRLF.charAt(i + 2)) << 6) + getValue(delCRLF.charAt(i + 3));
            for (int n5 = 0; n5 < 3 && n3 + n5 < array.length; ++n5) {
                array[n3 + n5] = (byte)(n4 >> 8 * (2 - n5) & 0xFF);
            }
            n3 += 3;
        }
        return array;
    }
    
    protected static int getValue(final char c) {
        if (c >= 'A' && c <= 'Z') {
            return c - 'A';
        }
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + '\u001a';
        }
        if (c >= '0' && c <= '9') {
            return c - '0' + '4';
        }
        if (c == '+') {
            return 62;
        }
        if (c == '/') {
            return 63;
        }
        return (c != '=') ? -1 : 0;
    }
    
    public static boolean isBase64(final byte[] array) {
        for (int i = 0; i < array.length; ++i) {
            final char c = (char)array[i];
            if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < '0' || c > '9') && c != '+' && c != '/' && c != '=') {
                return false;
            }
        }
        return true;
    }
    
    protected static String reform(final String s) {
        final StringBuffer sb = new StringBuffer();
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        while (stringTokenizer.hasMoreTokens()) {
            sb.append(stringTokenizer.nextToken());
        }
        return sb.toString();
    }
    
    protected static String delCRLF(final String s) {
        final StringBuffer sb = new StringBuffer();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n\r");
        while (stringTokenizer.hasMoreTokens()) {
            sb.append(stringTokenizer.nextToken());
        }
        return sb.toString();
    }
}
