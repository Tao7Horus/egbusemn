// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import common.util.UM_COB_GTQ904;
import common.util.UM_COE_GTQ903;

public class UM_IngamControl
{
    public UM_COE_GTQ903[] getIngamData(final String saupjaNumber) throws Exception {
        final String sql = " select BIZ_REG_NO, IMG_MN, to_char(REG_DT,'dd/mm/yyyy hh24:mi'), \t   to_char(UPDATE_DT,'dd/mm/yyyy hh24:mi'), \t   to_char(DELETE_DT,'dd/mm/yyyy hh24:mi'),USE_YN, SERIAL_NO from UM_SEAL_MAST where BIZ_REG_NO='" + saupjaNumber + "'" + " and SEAL_CLS='B'" + " order by USE_YN DESC";
        return new UM_COB_GTQ904().getList(this, sql, null);
    }
    
    public UM_COE_GTQ903[] getIngamData(final String saupjaNumber, final String gubunUse) throws Exception {
        final String sql = " select BIZ_REG_NO, IMG_MN, to_char(REG_DT,'yyyy/mm/dd hh24:mi'), \t   to_char(UPDATE_DT,'yyyy/mm/dd hh24:mi'), \t   to_char(DELETE_DT,'yyyy/mm/dd hh24:mi'),USE_YN, SERIAL_NO from UM_SEAL_MAST where BIZ_REG_NO='" + saupjaNumber + "'" + " and SEAL_CLS='B'" + " and USE_YN='" + gubunUse + "'";
        return new UM_COB_GTQ904().getList(this, sql, null);
    }
    
    public void updateIngamData(final String saupjaNumber, final String imageName, final String no, final String gubun, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " UPDATE UM_SEAL_MAST  SET DELETE_DT = SYSDATE, USE_YN = 'N' WHERE BIZ_REG_NO = ? AND SERIAL_NO =? AND SEAL_CLS=? AND IMG_MN=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupjaNumber);
            pstmt.setString(2, no);
            pstmt.setString(3, gubun);
            pstmt.setString(4, imageName);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Số con dấu xóa không phải là 1");
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
    
    public String getMaxNumber(final String saupjaNumber, final Connection conn) throws Exception {
        System.out.println("\n============>ngocanh = " + saupjaNumber);
        String result = "";
        ResultSet rest = null;
        PreparedStatement pstmt = null;
        try {
            final int start = saupjaNumber.length() + "-B-".length() + 1;
            final String sql = " select to_char(nvl(max(substr(IMG_MN, " + start + ", 10)),'000')+1,'fm000') from UM_SEAL_MAST" + " where BIZ_REG_NO=?" + " and SEAL_CLS='B'";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupjaNumber);
            rest = pstmt.executeQuery();
            if (rest.next()) {
                result = rest.getString(1);
                if (rest.wasNull()) {
                    throw new Exception("Không thể có được con dấu.(Null)");
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
    
    public void insertIngam(final String saupjaNumber, final String maxNumber, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO UM_SEAL_MAST (BIZ_REG_NO, SERIAL_NO, SEAL_CLS, IMG_MN, REG_DT,USE_YN)  VALUES (?, to_char('" + maxNumber + "','fm999'), 'B', ?, SYSDATE,'Y')";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupjaNumber);
            pstmt.setString(2, String.valueOf(saupjaNumber) + "-B-" + maxNumber);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("số con dấu đăng ký không phải là 1");
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
    
    public UM_COE_GTQ903[] getEDICode(final String saupjaNumber) throws Exception {
        final String sql = " select BIZ_REG_NO, BIZ_NM from UM_SUPPLIER_ENTER_MAST where BIZ_REG_NO='" + saupjaNumber + "' ";
        return new UM_COB_GTQ904().getList(this, sql, null);
    }
}
