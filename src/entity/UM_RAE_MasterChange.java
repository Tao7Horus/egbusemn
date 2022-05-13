// 
// Decompiled by Procyon v0.5.30
// 

package entity;

public class UM_RAE_MasterChange
{
    private String userID;
    private String userName;
    private String deptName;
    private String telNo;
    private String faxNo;
    private String mailAddr;
    private String nextCode;
    private String status;
    
    public void setuserID(final String userID) {
        this.userID = userID;
    }
    
    public void setuserName(final String userName) {
        this.userName = userName;
    }
    
    public void setdeptName(final String deptName) {
        this.deptName = deptName;
    }
    
    public void settelNo(final String telNo) {
        this.telNo = telNo;
    }
    
    public void setfaxNo(final String faxNo) {
        this.faxNo = faxNo;
    }
    
    public void setmailAddr(final String mailAddr) {
        this.mailAddr = mailAddr;
    }
    
    public void setnextCode(final String nextCode) {
        this.nextCode = nextCode;
    }
    
    public void setstatus(final String status) {
        this.status = status;
    }
    
    public String getuserID() {
        return this.userID;
    }
    
    public String getuserName() {
        return this.userName;
    }
    
    public String getdeptName() {
        return this.deptName;
    }
    
    public String gettelNo() {
        return this.telNo;
    }
    
    public String getfaxNo() {
        return this.faxNo;
    }
    
    public String getmailAddr() {
        return this.mailAddr;
    }
    
    public String getnextCode() {
        return this.nextCode;
    }
    
    public String getstatus() {
        return this.status;
    }
}
