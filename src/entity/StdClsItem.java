// 
// Decompiled by Procyon v0.5.30
// 

package entity;

public class StdClsItem
{
    private String stdClsCode;
    private String stdClsNo;
    private String stdClsIssuedDate;
    private String constAbilEvalAmt;
    private String evalStdYear;
    private String mastStdClsYn;
    private String stdClsExpiryDate;
    private String issueInsitue;
    private String stdClsName;
    private String groupName;
    
    public StdClsItem(final String stdClsCode, final String stdClsNo, final String stdClsIssuedDate, final String constAbilEvalAmt, final String evalStdYear, final String mastStdClsYn, final String stdClsExpiryDate, final String issueInstitue) {
        this.stdClsCode = stdClsCode;
        this.stdClsNo = stdClsNo;
        this.stdClsIssuedDate = stdClsIssuedDate;
        this.constAbilEvalAmt = constAbilEvalAmt;
        this.evalStdYear = evalStdYear;
        this.mastStdClsYn = mastStdClsYn;
        this.stdClsExpiryDate = stdClsExpiryDate;
        this.issueInsitue = issueInstitue;
    }
    
    public StdClsItem(final String stdClsCode, final String stdClsNo, final String stdClsIssuedDate, final String constAbilEvalAmt, final String evalStdYear, final String mastStdClsYn, final String stdClsExpiryDate, final String issueInstitue, final String stdClsName) {
        this.stdClsCode = stdClsCode;
        this.stdClsNo = stdClsNo;
        this.stdClsIssuedDate = stdClsIssuedDate;
        this.constAbilEvalAmt = constAbilEvalAmt;
        this.evalStdYear = evalStdYear;
        this.mastStdClsYn = mastStdClsYn;
        this.stdClsExpiryDate = stdClsExpiryDate;
        this.issueInsitue = issueInstitue;
        this.stdClsName = stdClsName;
    }
    
    public StdClsItem(final String stdClsCode, final String stdClsNo, final String stdClsIssuedDate, final String constAbilEvalAmt, final String evalStdYear, final String mastStdClsYn, final String stdClsExpiryDate, final String issueInstitue, final String stdClsName, final String groupName) {
        this.stdClsCode = stdClsCode;
        this.stdClsNo = stdClsNo;
        this.stdClsIssuedDate = stdClsIssuedDate;
        this.constAbilEvalAmt = constAbilEvalAmt;
        this.evalStdYear = evalStdYear;
        this.mastStdClsYn = mastStdClsYn;
        this.stdClsExpiryDate = stdClsExpiryDate;
        this.issueInsitue = issueInstitue;
        this.stdClsName = stdClsName;
        this.groupName = groupName;
    }
    
    public StdClsItem(final String stdClsCode, final String stdClsName) {
        this.stdClsCode = stdClsCode;
        this.stdClsName = stdClsName;
    }
    
    public String getGroupName() {
        return this.groupName;
    }
    
    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }
    
    public String getStdClsName() {
        return this.stdClsName;
    }
    
    public void setStdClsName(final String stdClsName) {
        this.stdClsName = stdClsName;
    }
    
    public String getIssueInsitue() {
        return this.issueInsitue;
    }
    
    public void setIssueInsitue(final String issueInsitue) {
        this.issueInsitue = issueInsitue;
    }
    
    public String getConstAbilEvalAmt() {
        return this.constAbilEvalAmt;
    }
    
    public void setConstAbilEvalAmt(final String constAbilEvalAmt) {
        this.constAbilEvalAmt = constAbilEvalAmt;
    }
    
    public String getEvalStdYear() {
        return this.evalStdYear;
    }
    
    public void setEvalStdYear(final String evalStdYear) {
        this.evalStdYear = evalStdYear;
    }
    
    public String getMastStdClsYn() {
        return this.mastStdClsYn;
    }
    
    public void setMastStdClsYn(final String mastStdClsYn) {
        this.mastStdClsYn = mastStdClsYn;
    }
    
    public String getStdClsCode() {
        return this.stdClsCode;
    }
    
    public void setStdClsCode(final String stdClsCode) {
        this.stdClsCode = stdClsCode;
    }
    
    public String getStdClsExpiryDate() {
        return this.stdClsExpiryDate;
    }
    
    public void setStdClsExpiryDate(final String stdClsExpiryDate) {
        this.stdClsExpiryDate = stdClsExpiryDate;
    }
    
    public String getStdClsIssuedDate() {
        return this.stdClsIssuedDate;
    }
    
    public void setStdClsIssuedDate(final String stdClsIssuedDate) {
        this.stdClsIssuedDate = stdClsIssuedDate;
    }
    
    public String getStdClsNo() {
        return this.stdClsNo;
    }
    
    public void setStdClsNo(final String stdClsNo) {
        this.stdClsNo = stdClsNo;
    }
}
