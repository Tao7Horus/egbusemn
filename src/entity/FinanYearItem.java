// 
// Decompiled by Procyon v0.5.30
// 

package entity;

import org.apache.commons.fileupload.disk.DiskFileItem;

public class FinanYearItem
{
    private String year;
    private DiskFileItem file;
    private String attachFile;
    private String orgFile;
    
    public String getAttachFile() {
        return this.attachFile;
    }
    
    public void setAttachFile(final String attachFile) {
        this.attachFile = attachFile;
    }
    
    public String getOrgFile() {
        return this.orgFile;
    }
    
    public void setOrgFile(final String orgFile) {
        this.orgFile = orgFile;
    }
    
    public FinanYearItem() {
    }
    
    public FinanYearItem(final String year, final DiskFileItem file) {
        this.year = year;
        this.file = file;
    }
    
    public String getYear() {
        return this.year;
    }
    
    public void setYear(final String year) {
        this.year = year;
    }
    
    public DiskFileItem getFile() {
        return this.file;
    }
    
    public void setFile(final DiskFileItem file) {
        this.file = file;
    }
}
