// 
// Decompiled by Procyon v0.5.30
// 

package entity;

public class UserItem
{
    private String name;
    private String saupNo;
    private String mobile;
    private String email;
    private String depart;
    private String phoneNo;
    private String fax;
    private String mastcd;
    private String usercls;
    private String firstregdt;
    private String permityn;
    private String institu_cd;
    private String password;
    
    public String getUsercls() {
		return usercls;
	}

	public void setUsercls(String usercls) {
		this.usercls = usercls;
	}

	public String getFirstregdt() {
		return firstregdt.substring(0,firstregdt.lastIndexOf("."));
	}

	public void setFirstregdt(String firstregdt) {
		this.firstregdt = firstregdt;
	}

	public String getPermityn() {
		return permityn;
	}

	public void setPermityn(String permityn) {
		this.permityn = permityn;
	}

	public String getInstitu_cd() {
		return institu_cd;
	}

	public void setInstitu_cd(String institu_cd) {
		this.institu_cd = institu_cd;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserItem(){	
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSaupNo() {
		return saupNo;
	}
	public void setSaupNo(String saupNo) {
		this.saupNo = saupNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getMastcd() {
		return mastcd;
	}
	public void setMastcd(String mastcd) {
		this.mastcd = mastcd;
	}
	
     
}
