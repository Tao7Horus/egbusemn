// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class UM_RAB_GovuA020c
{
    public String cancelReg(final Connection conn, final String receptNumber) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = "-1";
        String cnReceptID = "";
        try {
            sql = " select nvl(CERT_RECV_NO, '') from UM_REC_PUB_INSTITU_MAST where RECV_NO = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, receptNumber);
            rs = pstmt.executeQuery();
            pstmt.clearParameters();
            if (rs.next()) {
                cnReceptID = ((rs.getString(1) == null) ? "" : rs.getString(1));
            }
            sql = " UPDATE UM_REC_PUB_INSTITU_MAST SET REG_YN='C', REJECTED_RSON = 'Đã hủy đăng ký', PERMIT_DT=sysdate WHERE RECV_NO = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, receptNumber);
            pstmt.executeUpdate();
            pstmt.clearParameters();
            if (!cnReceptID.equals("")) {
                sql = " UPDATE UM_REC_PUB_INSTITU_CERT SET REG_YN='C', REJECTED_RSON = 'Đã hủy đăng ký', PERMIT_DT=sysdate WHERE RECV_NO = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, cnReceptID);
                pstmt.executeUpdate();
            }
            result = "0";
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex2) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex4) {}
        }
        return result;
    }
    
    public String getRegStatus(final Connection conn, final String receptNumber) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = "";
        sql = " select nvl(REG_YN,'N') from UM_REC_PUB_INSTITU_MAST where RECV_NO = ? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, receptNumber);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
                if (rs.wasNull()) {
                    result = "";
                }
            }
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex2) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex4) {}
        }
        return result;
    }
}
