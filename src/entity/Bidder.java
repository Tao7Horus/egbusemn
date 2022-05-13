package entity;

public class Bidder {
	private String BizRegNo; //Số DKKD	
	private String bizNm;    //Tên nhà thầu
	private String addr;     //Địa chỉ
	private String phoneNo;  //SĐT
	private String num;      // Số gói thầu tham gia
	
	public Bidder(String bizRegNo, String bizNm, String addr, String phoneNo, String num) {
		BizRegNo = bizRegNo;
		this.bizNm = bizNm;
		this.addr = addr;
		this.phoneNo = phoneNo;
		this.num = num;
	}
	public Bidder() {
	}
	public String getBizRegNo() {
		return BizRegNo;
	}
	public void setBizRegNo(String bizRegNo) {
		BizRegNo = bizRegNo;
	}
	public String getBizNm() {
		return bizNm;
	}
	public void setBizNm(String bizNm) {
		this.bizNm = bizNm;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	
}
