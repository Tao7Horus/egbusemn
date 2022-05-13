// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletInputStream;
import java.io.FilterInputStream;

public class PartInputStream extends FilterInputStream
{
    private String boundary;
    private byte[] buf;
    private int count;
    private int pos;
    private boolean eof;
    
    PartInputStream(final ServletInputStream servletInputStream, final String boundary) throws IOException {
        super((InputStream)servletInputStream);
        this.buf = new byte[65536];
        this.boundary = boundary;
    }
    
    private void fill() throws IOException {
        if (this.eof) {
            return;
        }
        if (this.count > 0) {
            if (this.count - this.pos != 2) {
                throw new IllegalStateException("fill() detected illegal buffer state");
            }
            System.arraycopy(this.buf, this.pos, this.buf, 0, this.count - this.pos);
            this.count -= this.pos;
            this.pos = 0;
        }
        while (this.count < this.buf.length - this.boundary.length()) {
            final int line = ((ServletInputStream)this.in).readLine(this.buf, this.count, this.buf.length - this.count);
            if (line == -1) {
                throw new IOException("unexpected end of part");
            }
            if (line >= this.boundary.length()) {
                this.eof = true;
                for (int i = 0; i < this.boundary.length(); ++i) {
                    if (this.boundary.charAt(i) != this.buf[this.count + i]) {
                        this.eof = false;
                        break;
                    }
                }
                if (this.eof) {
                    break;
                }
            }
            this.count += line;
        }
    }
    
    public int read() throws IOException {
        if (this.count - this.pos <= 2) {
            this.fill();
            if (this.count - this.pos <= 2) {
                return -1;
            }
        }
        return this.buf[this.pos++] & 0xFF;
    }
    
    public int read(final byte[] array) throws IOException {
        return this.read(array, 0, array.length);
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        final int n3 = 0;
        if (n2 == 0) {
            return 0;
        }
        int n4 = this.count - this.pos - 2;
        if (n4 <= 0) {
            this.fill();
            n4 = this.count - this.pos - 2;
            if (n4 <= 0) {
                return -1;
            }
        }
        final int min = Math.min(n2, n4);
        System.arraycopy(this.buf, this.pos, array, n, min);
        this.pos += min;
        int i;
        int min2;
        for (i = n3 + min; i < n2; i += min2) {
            this.fill();
            final int n5 = this.count - this.pos - 2;
            if (n5 <= 0) {
                return i;
            }
            min2 = Math.min(n2 - i, n5);
            System.arraycopy(this.buf, this.pos, array, n + i, min2);
            this.pos += min2;
        }
        return i;
    }
    
    public int available() throws IOException {
        final int n = this.count - this.pos - 2 + this.in.available();
        return (n < 0) ? 0 : n;
    }
    
    public void close() throws IOException {
        if (!this.eof) {
            while (this.read(this.buf, 0, this.buf.length) != -1) {}
        }
    }
}
