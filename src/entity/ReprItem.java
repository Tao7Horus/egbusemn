// 
// Decompiled by Procyon v0.5.30
// 

package entity;

public class ReprItem
{
    private String reprName;
    private String reprIdentNo;
    private String reprMobile;
    private String reprEmail;
    private String reprIsmain;
    
    public ReprItem(final String reprName, final String reprIdentNo, final String reprMobile, final String reprEmail, final String reprIsmain) {
        this.reprName = reprName;
        this.reprIdentNo = reprIdentNo;
        this.reprMobile = reprMobile;
        this.reprEmail = reprEmail;
        this.reprIsmain = reprIsmain;
    }
    
    public String getReprEmail() {
        return this.reprEmail;
    }
    
    public void setReprEmail(final String reprEmail) {
        this.reprEmail = reprEmail;
    }
    
    public String getReprIdentNo() {
        return this.reprIdentNo;
    }
    
    public void setReprIdentNo(final String reprIdentNo) {
        this.reprIdentNo = reprIdentNo;
    }
    
    public String getReprIsmain() {
        return this.reprIsmain;
    }
    
    public void setReprIsmain(final String reprIsmain) {
        this.reprIsmain = reprIsmain;
    }
    
    public String getReprMobile() {
        return this.reprMobile;
    }
    
    public void setReprMobile(final String reprMobile) {
        this.reprMobile = reprMobile;
    }
    
    public String getReprName() {
        return this.reprName;
    }
    
    public void setReprName(final String reprName) {
        this.reprName = reprName;
    }
}
