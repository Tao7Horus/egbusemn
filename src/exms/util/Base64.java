// 
// Decompiled by Procyon v0.5.30
// 

package exms.util;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileInputStream;

public class Base64
{
    static char[] Base64Map;
    private static byte[] codes;
    
    static {
        Base64.Base64Map = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
        Base64.codes = new byte[256];
        for (int i = 65; i <= 90; ++i) {
            Base64.codes[i] = (byte)(i - 65);
        }
        for (int i = 97; i <= 122; ++i) {
            Base64.codes[i] = (byte)(26 + i - 97);
        }
        for (int i = 48; i <= 57; ++i) {
            Base64.codes[i] = (byte)(52 + i - 48);
        }
        Base64.codes[43] = 62;
        Base64.codes[47] = 63;
    }
    
    public static char[] encode(final byte[] data) {
        return encode(data, 0, data.length);
    }
    
    public static char[] encode(final byte[] data, final int offset, final int len) {
        final char[] encodedChars = new char[(len + 2) / 3 * 4];
        boolean threeChars = false;
        boolean fourChars = false;
        for (int i = 0, index = 0; i < len; i += 3, index += 4) {
            threeChars = false;
            fourChars = false;
            int val = 0xFF & data[i];
            val <<= 8;
            if (i + 1 < len) {
                val |= (0xFF & data[i + 1]);
                threeChars = true;
            }
            val <<= 8;
            if (i + 2 < len) {
                val |= (0xFF & data[i + 2]);
                fourChars = true;
            }
            encodedChars[index + 3] = Base64.Base64Map[fourChars ? (val & 0x3F) : 64];
            val >>= 6;
            encodedChars[index + 2] = Base64.Base64Map[threeChars ? (val & 0x3F) : 64];
            val >>= 6;
            encodedChars[index + 1] = Base64.Base64Map[val & 0x3F];
            val >>= 6;
            encodedChars[index + 0] = Base64.Base64Map[val & 0x3F];
        }
        return encodedChars;
    }
    
    public static byte[] decode(final char[] data) {
        return decode(data, 0, data.length);
    }
    
    public static byte[] decode(final char[] data, final int offset, final int len) {
        int tempLen = len;
        for (int idx = 0; idx < len; ++idx) {
            if (data[idx] > 'ÿ' || Base64.codes[data[idx]] < 0) {
                --tempLen;
            }
        }
        int len2 = tempLen / 4 * 3;
        if (tempLen % 4 == 3) {
            len2 += 2;
        }
        if (tempLen % 4 == 2) {
            ++len2;
        }
        final byte[] out = new byte[len2];
        int shift = 0;
        int accum = 0;
        int index = 0;
        for (int ix = 0; ix < len; ++ix) {
            final int value = (data[ix] > 'ÿ') ? -1 : Base64.codes[data[ix]];
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
        return out;
    }
    
    public static void encodeFile(final String sourceFile, final String targetFile) throws IOException {
        final byte[] buf = new byte[129];
        final FileInputStream fin = new FileInputStream(sourceFile);
        final FileWriter fout = new FileWriter(targetFile);
        int readed;
        while ((readed = fin.read(buf)) != -1) {
            final char[] chars = encode(buf, 0, readed);
            fout.write(chars, 0, chars.length);
        }
        fin.close();
        fout.flush();
        fout.close();
    }
    
    public static void decodeFile(final String sourceFile, final String targetFile) throws IOException {
        final char[] buf = new char[128];
        final FileReader fin = new FileReader(sourceFile);
        final FileOutputStream fout = new FileOutputStream(targetFile);
        int readed;
        while ((readed = fin.read(buf, 0, 128)) != -1) {
            final byte[] bytes = decode(buf, 0, readed);
            fout.write(bytes, 0, bytes.length);
        }
        fin.close();
        fout.flush();
        fout.close();
    }
}
