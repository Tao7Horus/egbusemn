// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import javax.servlet.ServletInputStream;

public class ParamPart extends Part
{
    private byte[] value;
    
    ParamPart(final String s, final ServletInputStream servletInputStream, final String s2) throws IOException {
        super(s);
        final PartInputStream partInputStream = new PartInputStream(servletInputStream, s2);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        final byte[] array = new byte[128];
        int read;
        while ((read = partInputStream.read(array)) != -1) {
            byteArrayOutputStream.write(array, 0, read);
        }
        partInputStream.close();
        byteArrayOutputStream.close();
        this.value = byteArrayOutputStream.toByteArray();
    }
    
    public byte[] getValue() {
        return this.value;
    }
    
    public String getStringValue() throws UnsupportedEncodingException {
        return this.getStringValue("ISO-8859-1");
    }
    
    public String getStringValue(final String s) throws UnsupportedEncodingException {
        return new String(this.value, s);
    }
    
    public boolean isParam() {
        return true;
    }
}
