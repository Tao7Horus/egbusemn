// 
// Decompiled by Procyon v0.5.30
// 

package exms.mime;

public class DAttachInfo
{
    public String SEQ;
    public String FileName;
    public long fileSize;
    public String DocName;
    public String SavedPath;
    public String encoding;
    public String Contents;
    
    public DAttachInfo() {
    }
    
    public DAttachInfo(final String s, final String s1, final String s2, final long l, final String s3) {
        this.SEQ = s;
        this.FileName = s2;
        this.fileSize = l;
        this.DocName = s1;
        this.SavedPath = s3;
    }
}
