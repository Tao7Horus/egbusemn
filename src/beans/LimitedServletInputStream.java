// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.io.IOException;
import javax.servlet.ServletInputStream;

public class LimitedServletInputStream extends ServletInputStream
{
    private ServletInputStream in;
    private int totalExpected;
    private int totalRead;
    
    public LimitedServletInputStream(final ServletInputStream in, final int totalExpected) {
        this.totalRead = 0;
        this.in = in;
        this.totalExpected = totalExpected;
    }
    
    public int readLine(final byte[] array, final int n, final int n2) throws IOException {
        final int n3 = this.totalExpected - this.totalRead;
        if (n3 <= 0) {
            return -1;
        }
        final int line = this.in.readLine(array, n, Math.min(n3, n2));
        if (line > 0) {
            this.totalRead += line;
        }
        return line;
    }
    
    public int read() throws IOException {
        if (this.totalRead >= this.totalExpected) {
            return -1;
        }
        return this.in.read();
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        final int n3 = this.totalExpected - this.totalRead;
        if (n3 <= 0) {
            return -1;
        }
        final int read = this.in.read(array, n, Math.min(n3, n2));
        if (read > 0) {
            this.totalRead += read;
        }
        return read;
    }
}
