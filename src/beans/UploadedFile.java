// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.io.File;

class UploadedFile
{
    private String dir;
    private String filename;
    private String orgFilename;
    private String type;
    
    UploadedFile(final String s, final String s2, final String s3) {
        this(s, s2, null, s3);
    }
    
    UploadedFile(final String dir, final String filename, final String orgFilename, final String type) {
        this.dir = dir;
        this.filename = filename;
        this.orgFilename = orgFilename;
        this.type = type;
    }
    
    public String getContentType() {
        return this.type;
    }
    
    public String getFilesystemName() {
        return this.filename;
    }
    
    public String getOrginalFileName() {
        return this.orgFilename;
    }
    
    public File getFile() {
        if (this.dir == null || this.filename == null) {
            return null;
        }
        return new File(this.dir + File.separator + this.filename);
    }
}
