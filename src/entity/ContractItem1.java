// 
// Decompiled by Procyon v0.5.30
// 

package entity;

public class ContractItem1
{
    private String No;
    private String name;
    private String dateSign;
    private String value;
    private String prince;
    private String percent;
    private String dateComplete;
    private String projectName;
    private String contact;
    
    public ContractItem1(final String no, final String name, final String dateSign, final String value, final String prince, final String percent, final String dateComplete, final String projectName, final String contact) {
        this.No = no;
        this.name = name;
        this.dateSign = dateSign;
        this.value = value;
        this.prince = prince;
        this.percent = percent;
        this.dateComplete = dateComplete;
        this.projectName = projectName;
        this.contact = contact;
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
    
    public String getDateSign() {
        return this.dateSign;
    }
    
    public void setDateSign(final String dateSign) {
        this.dateSign = dateSign;
    }
    
    public String getPrince() {
        return this.prince;
    }
    
    public void setPrince(final String prince) {
        this.prince = prince;
    }
    
    public String getPercent() {
        return this.percent;
    }
    
    public void setPercent(final String percent) {
        this.percent = percent;
    }
    
    public String getDateComplete() {
        return this.dateComplete;
    }
    
    public void setDateComplete(final String dateComplete) {
        this.dateComplete = dateComplete;
    }
    
    public String getProjectName() {
        return this.projectName;
    }
    
    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }
    
    public String getContact() {
        return this.contact;
    }
    
    public void setContact(final String contact) {
        this.contact = contact;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
}
