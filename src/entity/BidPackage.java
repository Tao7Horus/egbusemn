package entity;

public class BidPackage {
	private int stt; // Số TBMT
	private String bidNo; // Số TBMT
	private String InstituFullNm; // Bên mời thầu
	private String BidNm; // Tên gói thầu
	private String bidOpen; // Thời điểm mở thầu
	private String bidPublic; // Thời điểm đăng tải
	private String bizNm;// Tên nhà thầu
	private String num;// Tên nhà thầu
	private String mst;// Max số thuế
	private String bidMethod;// Max số thuế
	
	private String phoneNo;
	private String reprMobile;
	private String reprNM;
	private String reprEmail;
	private String nm;
	private String mobile;
	private String email;
	private String paymentStatus;
	
	public BidPackage(int stt,String bidNo, String instituFullNm, String bidNm, String bidOpen, String bidPublic, String bizNm,
			String num,String mst,String bidMethod) {
		this.stt = stt;
		this.bidNo = bidNo;
		InstituFullNm = instituFullNm;
		BidNm = bidNm;
		this.bidOpen = bidOpen;
		this.bidPublic = bidPublic;
		this.bizNm = bizNm;
		this.num = num;
		this.mst = mst;
		this.bidMethod = bidMethod;
	}
	
	public BidPackage() {
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public String getBidMethod() {
		return bidMethod;
	}

	public void setBidMethod(String bidMethod) {
		this.bidMethod = bidMethod;
	}

	public String getMst() {
		return mst;
	}

	public void setMst(String mst) {
		this.mst = mst;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}


	public String getBidNo() {
		return bidNo;
	}

	public void setBidNo(String bidNo) {
		this.bidNo = bidNo;
	}

	public String getInstituFullNm() {
		return InstituFullNm;
	}

	public void setInstituFullNm(String instituFullNm) {
		InstituFullNm = instituFullNm;
	}

	public String getBidNm() {
		return BidNm;
	}

	public void setBidNm(String bidNm) {
		BidNm = bidNm;
	}

	public String getBidOpen() {
		return bidOpen;
	}

	public void setBidOpen(String bidOpen) {
		this.bidOpen = bidOpen;
	}

	public String getBidPublic() {
		return bidPublic;
	}

	public void setBidPublic(String bidPublic) {
		this.bidPublic = bidPublic;
	}

	public String getBizNm() {
		return bizNm;
	}

	public void setBizNm(String bizNm) {
		this.bizNm = bizNm;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getReprMobile() {
		return reprMobile;
	}

	public void setReprMobile(String reprMobile) {
		this.reprMobile = reprMobile;
	}

	public String getReprNM() {
		return reprNM;
	}

	public void setReprNM(String reprNM) {
		this.reprNM = reprNM;
	}

	public String getReprEmail() {
		return reprEmail;
	}

	public void setReprEmail(String reprEmail) {
		this.reprEmail = reprEmail;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
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

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}
