// 
// Decompiled by Procyon v0.5.30
// 

package entity;

public class ContractItem
{
    private String No;
    private String name;
    private String partnerName;
    private String value;
    
    public ContractItem(final String no, final String name, final String partnerName, final String value) {
        this.No = no;
        this.name = name;
        this.partnerName = partnerName;
        this.value = value;
    }
    
    public String getNo() {
        return this.No;
    }
    
    public void setNo(final String no) {
        this.No = no;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getPartnerName() {
        return this.partnerName;
    }
    
    public void setPartnerName(final String partnerName) {
        this.partnerName = partnerName;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
}
