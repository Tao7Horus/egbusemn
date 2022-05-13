// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class UM_ADB_GONGCERTRECEIVE
{
    public void updateReceiveGonggonCert(final String denyReason, final String receptNumber, final String type, final String userID, final Connection conn) throws Exception {
        if (receptNumber == null) {
            throw new Exception("Không có thông tin cập nhật.");
        }
        String sql = null;
        PreparedStatement pstmt = null;
        sql = " UPDATE UM_REC_PUB_INSTITU_CERT SET REG_YN=?,REJECTED_RSON=?, PERMIT_USER_ID=?, PERMIT_DT=sysdate WHERE RECV_NO = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, type);
            pstmt.setString(2, denyReason);
            pstmt.setString(3, userID);
            pstmt.setString(4, receptNumber);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("UM_REC_PUB_INSTITU_CERT update Quá nhiều dữ liệu [dữ liệu xác minh cần thiết]");
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public String getRegStatus(final String receptNumber, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        String result = "";
        sql = " select nvl(REG_YN,'N') from UM_REC_PUB_INSTITU_CERT where RECV_NO=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, receptNumber);
            rest = pstmt.executeQuery();
            if (rest.next()) {
                result = rest.getString(1);
                if (rest.wasNull()) {
                    throw new Exception("Thủ tục đăng ký cho cơ quan nhà nước hiện không có.");
                }
            }
        }
        finally {
            try {
                if (rest != null) {
                    rest.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (rest != null) {
                rest.close();
            }
        }
        catch (Exception ex3) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex4) {}
        return result;
    }
}
