// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import java.util.Vector;
import common.CommEntity;
import common.Log;
import common.ComDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class UM_URB_UserE040p
{
    public OneRowEntity getKeyID(final String Mastercode, final Connection conn) throws Exception {
        final String sql = " select  USER_ID, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO  from  UM_USER  where  MAST_CD =?   and  MAST_RECV_YN = 'Y' ";
        try {
            final String[] parameter = { Mastercode };
            return new ComDbQuery().getOneRowList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getKeyID() :Mastercode[" + Mastercode + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public OneRowEntity getusr_grp(final String userid, final Connection conn) throws Exception {
        final String sql = "select USER_GRP from UM_USER where USER_ID = ? ";
        try {
            final String[] parameter = { userid };
            return new ComDbQuery().getOneRowList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getusr_grp() :userid[" + userid + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] select_idlist(final String masterCode, final int page_no, final int page_size, final Connection conn) throws Exception {
        final Vector Vec = new Vector(1, 1);
        final StringBuffer sb = new StringBuffer();
        sb.append("select  MAST_RECV_YN, USER_ID, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, FIRST_REG_DT, PERMIT_YN,").append("        FA_YN, PA_YN, t ").append("  from  (select MAST_RECV_YN, USER_ID, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, FIRST_REG_DT, PERMIT_YN, ").append("                FA_YN, PA_YN, rownum t ").append("          from (select ").append("\t\t\t\t\t\tMAST_RECV_YN, USER_ID, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, FIRST_REG_DT, PERMIT_YN,").append("\t\t\t\t\t\tFA_YN, PA_YN, rownum t").append("\t\t\t\t\t\tfrom  UM_USER").append("\t\t\t\t\t\twhere  MAST_CD =? ").append("                   \torder by MAST_RECV_YN desc, PERMIT_YN, USER_ID").append("\t\t\t\t)").append("\t\t)").append(" where t between ? and ?");
        Vec.addElement(masterCode);
        Vec.addElement(Integer.toString(page_size * (page_no - 1) + 1));
        Vec.addElement(Integer.toString(page_size * page_no));
        final String[] parameter = new String[Vec.size()];
        Vec.copyInto(parameter);
        try {
            return new ComDbQuery().getList(this, "usemn", sb.toString(), parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .select_idlist() :masterCode[" + masterCode + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] gethistoryinfoList(final String userID, final Connection conn) throws Exception {
        final Vector Vec = new Vector(1, 1);
        final StringBuffer sb = new StringBuffer();
        sb.append("select\ta.USER_ID, to_char(a.PROCESSION_DT,'dd/mm/yyyy hh24:mi') PROCESSION_DT , b.CHRGR_NM||'('|| b.CHRGR_DEPART||')' CHRGR_PHONE_NO, ").append("\t\t\tdecode(a.PROCESSION_CLS, 'D','Xóa','R','Khôi phục','')  PROCESSION_CLS, replace(a.MANAGER_POSSESSION,chr(13),'<br>') as MANAGER_POSSESSION  ").append("from  UM_USER_ID_CHANGE_INFO a, UM_USER b \t").append("where\ta.USER_ID =? \t").append("and\ta.MANAGER_ID = b.USER_ID ").append("order by PROCESSION_DT desc");
        Vec.addElement(userID);
        final String[] parameter = new String[Vec.size()];
        Vec.copyInto(parameter);
        try {
            return new ComDbQuery().getList(this, "usemn", sb.toString(), parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .gethistoryinfoList() :userID[" + userID + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public int Max_count(final String masterCode, final Connection conn) throws Exception {
        final String sql = " select  count(*)    from  UM_USER   where  MAST_CD =?  ";
        final String[] parameter = { masterCode };
        try {
            return new ComDbQuery().getCount(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .Max_count() :masterCode[" + masterCode + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public void updateKeyIDYN(final Connection conn, final String masterCode, final String keyID) throws Exception {
        String sql = null;
        String sql2 = null;
        PreparedStatement pstmt = null;
        sql = " update UM_USER     set MAST_RECV_YN = 'N'   where MAST_CD = ? ";
        sql2 = " update UM_USER     set MAST_RECV_YN = 'Y'   where MAST_CD = ?     and USER_ID   = ? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, masterCode);
            if (pstmt.executeUpdate() < 1) {
                throw new Exception("Không cập nhật được Người dùng");
            }
            pstmt.clearParameters();
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, masterCode);
            pstmt.setString(2, keyID);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Số cập nhật người dùng không phải là 1");
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex3) {}
    }
    
    public void updateApprovalYN(final Connection conn, final String masterCode, final String userID, final String ApprovalYN) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = " update UM_USER     set PERMIT_YN = ?   where MAST_CD = ?     and USER_ID   = ? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ApprovalYN);
            pstmt.setString(2, masterCode);
            pstmt.setString(3, userID);
            if (pstmt.executeUpdate() == 0) {
                throw new Exception("Số cập nhật người dùng không phải là 1");
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
    
    public void updateModifyDate(final String id, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = " update UM_CERT_INFO set    UPDATE_DT = sysdate,         MOD_REQ_YN = 'Y'  where  USER_ID = ? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
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
    
    public void insertHistory(final String userID, final String churiGubun, final String gubun, final String masterCode, final String id, final String IP, final String deleteReason, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "  insert into UM_USER_ID_CHANGE_INFO (USER_ID, PROCESSION_DT,  PROCESSION_CLS, USER_CLS, MAST_CD, MANAGER_ID, MANAGER_IP, MANAGER_POSSESSION)  values(?,sysdate,?,?,?,?,?,?) ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.setString(2, churiGubun);
            pstmt.setString(3, gubun);
            pstmt.setString(4, masterCode);
            pstmt.setString(5, id);
            pstmt.setString(6, IP);
            pstmt.setString(7, deleteReason);
            if (pstmt.executeUpdate() == 0) {
                throw new Exception("Thông tin cập nhật ID người dùng không phải là 1");
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
    
    public int tableDataCnt(final String id, final Connection conn) throws Exception {
        final String sql = " select  count(*)  from  UM_USER_ID_CHANGE_INFO  where  USER_ID =? ";
        final String[] parameter = { id };
        try {
            return new ComDbQuery().getCount(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .tableDataCnt() :id[" + id + "]:" + exm.toString());
            throw exm;
        }
    }
}
