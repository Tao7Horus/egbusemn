// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;

public class MacBinaryDecoderOutputStream extends FilterOutputStream
{
    private int bytesFiltered;
    private int dataForkLength;
    
    public MacBinaryDecoderOutputStream(final OutputStream outputStream) {
        super(outputStream);
        this.bytesFiltered = 0;
        this.dataForkLength = 0;
    }
    
    public void write(final int n) throws IOException {
        if (this.bytesFiltered <= 86 && this.bytesFiltered >= 83) {
            this.dataForkLength |= (n & 0xFF) << (86 - this.bytesFiltered) * 8;
        }
        else if (this.bytesFiltered < 128 + this.dataForkLength && this.bytesFiltered >= 128) {
            this.out.write(n);
        }
        ++this.bytesFiltered;
    }
    
    public void write(final byte[] array) throws IOException {
        this.write(array, 0, array.length);
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        if (this.bytesFiltered >= 128 + this.dataForkLength) {
            this.bytesFiltered += n2;
        }
        else if (this.bytesFiltered >= 128 && this.bytesFiltered + n2 <= 128 + this.dataForkLength) {
            this.out.write(array, n, n2);
            this.bytesFiltered += n2;
        }
        else {
            for (int i = 0; i < n2; ++i) {
                this.write(array[n + i]);
            }
        }
    }
}
