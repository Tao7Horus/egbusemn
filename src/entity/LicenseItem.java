// 
// Decompiled by Procyon v0.5.30
// 

package entity;

public class LicenseItem
{
    private String licenseCode;
    private String issueInstitu;
    private String licenseIssuedDate;
    private String licenseExpiryDate;
    
    public LicenseItem(final String licenseCode, final String issueInstitu, final String licenseIssuedDate, final String licenseExpiryDate) {
        this.licenseCode = licenseCode;
        this.issueInstitu = issueInstitu;
        this.licenseIssuedDate = licenseIssuedDate;
        this.licenseExpiryDate = licenseExpiryDate;
    }
    
    public String getIssueInstitu() {
        return this.issueInstitu;
    }
    
    public void setIssueInstitu(final String issueInstitu) {
        this.issueInstitu = issueInstitu;
    }
    
    public String getLicenseCode() {
        return this.licenseCode;
    }
    
    public void setLicenseCode(final String licenseCode) {
        this.licenseCode = licenseCode;
    }
    
    public String getLicenseExpiryDate() {
        return this.licenseExpiryDate;
    }
    
    public void setLicenseExpiryDate(final String licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }
    
    public String getLicenseIssuedDate() {
        return this.licenseIssuedDate;
    }
    
    public void setLicenseIssuedDate(final String licenseIssuedDate) {
        this.licenseIssuedDate = licenseIssuedDate;
    }
}
