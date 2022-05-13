package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import common.Log;

public class CapNhatTTDangKyRecSupp
{    
    
    public void capnhatTTDangKy(final String bizRegNo, final String bizName, final String bizEnName, final String addr, final String phoneNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_REC_SUPPLIER_ENTER_MAST set BIZ_NM=?, BIZ_EN_NM=?, ADDR= ?, PHONE_NO = ? ");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizName);
            pstmt.setString(2, bizEnName);            
            pstmt.setString(3, addr);
            pstmt.setString(4, phoneNo);            
            pstmt.setString(5, bizRegNo);
                        
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
    }
}
