// 
// Decompiled by Procyon v0.5.30
// 

package common;

public class ZipCode
{
    public static final String ZERO = "0";
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String SIX = "6";
    public static final String LON = "Lớn";
    public static final String VUA = "Vừa";
    public static final String NHO = "Nhỏ";
    public static final String CQ_TW = "Cơ quan Trung ương";
    public static final String CQ_DP = "Cơ quan Địa phương";
    public static final String DN_NN = "Doanh nghiệp Nhà nước";
    public static final String DN_DP = "Doanh nghiệp Địa phương";
    public static final String TC_HCSN = "Tổ chức hành chính sự nghiệp";
    public static final String TC_PLN = "Tổ chức phi lợi nhuận";
    public static final String OTHERS = "Khác";
    public static final String HA_NOI = "Hà Nội";
    public static final String TP_HO_CHI_MINH = "Tp Hồ Chí Minh";
    public static final String HAI_PHONG = "Hải Phòng";
    
    public String getInsType(final String s) {
        if ("0".equals(s)) {
            return "Lớn";
        }
        if ("1".equals(s)) {
            return "Vừa";
        }
        if ("2".equals(s)) {
            return "Nhỏ";
        }
        return "Lớn";
    }
    
    public String getDependType(final String s) {
        if ("1".equals(s)) {
            return "Cơ quan Trung ương";
        }
        if ("2".equals(s)) {
            return "Cơ quan Địa phương";
        }
        if ("3".equals(s)) {
            return "Doanh nghiệp Nhà nước";
        }
        if ("4".equals(s)) {
            return "Doanh nghiệp Địa phương";
        }
        if ("5".equals(s)) {
            return "Tổ chức hành chính sự nghiệp";
        }
        if ("6".equals(s)) {
            return "Tổ chức phi lợi nhuận";
        }
        if ("0".equals(s)) {
            return "Khác";
        }
        return "Khác";
    }
    
    public String getZipType(final String s) {
        if ("0".equals(s)) {
            return "Hà Nội";
        }
        if ("1".equals(s)) {
            return "Hải Phòng";
        }
        if ("2".equals(s)) {
            return "Tp Hồ Chí Minh";
        }
        return "Hà Nội";
    }
}
