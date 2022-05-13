// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.io.File;

class FileUpInfo_DEXT
{
    private long fileSize;
    private String dir;
    private String fileSysname;
    private String filename;
    private String orgFilename;
    private String contentType;
    
    FileUpInfo_DEXT(final String s, final String s2, final String s3, final long n, final String s4) {
        this(s, s2, s3, null, n, s4);
    }
    
    FileUpInfo_DEXT(final String dir, final String fileSysname, final String filename, final String orgFilename, final long fileSize, final String contentType) {
        this.fileSize = 0L;
        this.dir = dir;
        this.fileSysname = fileSysname;
        this.filename = filename;
        this.orgFilename = orgFilename;
        this.fileSize = fileSize;
        this.contentType = contentType;
    }
    
    public String getDirPath() {
        return this.dir;
    }
    
    public String getFilesystemPath() {
        return this.fileSysname;
    }
    
    public String getFilesystemName() {
        return this.filename;
    }
    
    public long getFileSize() {
        return this.fileSize;
    }
    
    public String getContentType() {
        return this.contentType;
    }
    
    public String getOrginalFileName() {
        return this.orgFilename;
    }
    
    public File getFile() {
        if (this.fileSysname == null) {
            return null;
        }
        return new File(this.fileSysname);
    }
}
