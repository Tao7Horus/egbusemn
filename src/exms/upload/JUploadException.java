// 
// Decompiled by Procyon v0.5.30
// 

package exms.upload;

public class JUploadException extends Exception
{
    private int seq;
    
    public JUploadException(final String s, final int i) {
        super(s);
        this.seq = 0;
        this.seq = i;
    }
    
    public int getSeq() {
        return this.seq;
    }
    
    public void setSeq(final int i) {
        this.seq = i;
    }
}
