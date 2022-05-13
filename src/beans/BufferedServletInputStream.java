// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.io.IOException;
import javax.servlet.ServletInputStream;

public class BufferedServletInputStream extends ServletInputStream
{
    private ServletInputStream in;
    private byte[] buf;
    private int count;
    private int pos;
    
    public BufferedServletInputStream(final ServletInputStream in) {
        this.buf = new byte[65536];
        this.in = in;
    }
    
    private void fill() throws IOException {
        final int read = this.in.read(this.buf, 0, this.buf.length);
        if (read > 0) {
            this.pos = 0;
            this.count = read;
        }
    }
    
    public int readLine(final byte[] array, final int n, final int n2) throws IOException {
        final int n3 = 0;
        if (n2 == 0) {
            return 0;
        }
        int n4 = this.count - this.pos;
        if (n4 <= 0) {
            this.fill();
            n4 = this.count - this.pos;
            if (n4 <= 0) {
                return -1;
            }
        }
        int min = Math.min(n2, n4);
        int n5 = findeol(this.buf, this.pos, min);
        if (n5 != -1) {
            min = n5;
        }
        System.arraycopy(this.buf, this.pos, array, n, min);
        this.pos += min;
        int n6;
        int min2;
        for (n6 = n3 + min; n6 < n2 && n5 == -1; n6 += min2) {
            this.fill();
            final int n7 = this.count - this.pos;
            if (n7 <= 0) {
                return n6;
            }
            min2 = Math.min(n2 - n6, n7);
            n5 = findeol(this.buf, this.pos, min2);
            if (n5 != -1) {
                min2 = n5;
            }
            System.arraycopy(this.buf, this.pos, array, n + n6, min2);
            this.pos += min2;
        }
        return n6;
    }
    
    private static int findeol(final byte[] array, final int n, final int n2) {
        final int n3 = n + n2;
        int i = n;
        while (i < n3) {
            if (array[i++] == 10) {
                return i - n;
            }
        }
        return -1;
    }
    
    public int read() throws IOException {
        if (this.count <= this.pos) {
            this.fill();
            if (this.count <= this.pos) {
                return -1;
            }
        }
        return this.buf[this.pos++] & 0xFF;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        int i;
        int min;
        for (i = 0; i < n2; i += min) {
            int n3 = this.count - this.pos;
            if (n3 <= 0) {
                this.fill();
                n3 = this.count - this.pos;
                if (n3 <= 0) {
                    if (i > 0) {
                        return i;
                    }
                    return -1;
                }
            }
            min = Math.min(n2 - i, n3);
            System.arraycopy(this.buf, this.pos, array, n + i, min);
            this.pos += min;
        }
        return i;
    }
}
