// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import javax.servlet.ServletInputStream;

public class FilePart extends Part
{
    private String fileName;
    private String filePath;
    private String contentType;
    private PartInputStream partInput;
    
    FilePart(final String s, final ServletInputStream servletInputStream, final String s2, final String contentType, final String fileName, final String filePath) throws IOException {
        super(s);
        this.fileName = fileName;
        this.filePath = filePath;
        this.contentType = contentType;
        this.partInput = new PartInputStream(servletInputStream, s2);
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }
    
    public String getFilePath() {
        return this.filePath;
    }
    
    public String getContentType() {
        return this.contentType;
    }
    
    public InputStream getInputStream() {
        return this.partInput;
    }
    
    public long writeTo(final File file) throws IOException {
        long write = 0L;
        OutputStream outputStream = null;
        try {
            if (this.fileName != null) {
                File file2;
                if (file.isDirectory()) {
                    file2 = new File(file, this.fileName);
                }
                else {
                    file2 = file;
                }
                outputStream = new BufferedOutputStream(new FileOutputStream(file2));
                write = this.write(outputStream);
            }
        }
        finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return write;
    }
    
    public long writeTo(final OutputStream outputStream) throws IOException {
        long write = 0L;
        if (this.fileName != null) {
            write = this.write(outputStream);
        }
        return write;
    }
    
    long write(OutputStream outputStream) throws IOException {
        if (this.contentType.equals("application/x-macbinary")) {
            outputStream = new MacBinaryDecoderOutputStream(outputStream);
        }
        long n = 0L;
        final byte[] array = new byte[8192];
        int read;
        while ((read = this.partInput.read(array)) != -1) {
            outputStream.write(array, 0, read);
            n += read;
        }
        return n;
    }
    
    public boolean isFile() {
        return true;
    }
}
