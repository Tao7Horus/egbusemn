// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.sql.PreparedStatement;
import common.CommEntity;
import java.util.Vector;
import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class UM_RAB_MastBidAgentHist
{
    public OneRowEntity selectNewest(final String IdentNo, final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, VER ");
            sql.append(" from UM_BID_AGENT_HIST t1 ");
            sql.append(" where t1.IDENT_NO = ? ");
            sql.append(" and t1.BIZ_REG_NO = ? ");
            sql.append(" and t1.VER=(SELECT MAX(VER) FROM UM_BID_AGENT_HIST WHERE BIZ_REG_NO= t1.BIZ_REG_NO AND IDENT_NO=t1.IDENT_NO) ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { IdentNo, bizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + IdentNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public OneRowEntity selectDetail(final String IdentNo, final String bizRegNo, final String ver, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, VER ");
            sql.append(" from UM_BID_AGENT_HIST t1 ");
            sql.append(" where t1.IDENT_NO = ? ");
            sql.append(" and t1.BIZ_REG_NO = ? ");
            sql.append(" and t1.VER= ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { IdentNo, bizRegNo, ver }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + IdentNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public int getListCount(final String BizRecNo, final String ver, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" SELECT COUNT(*) ");
            sql.append(" FROM UM_BID_AGENT_HIST t1 ");
            sql.append(" WHERE  BIZ_REG_NO= ? AND VER= ?");
            parameterV.add(BizRecNo);
            parameterV.add(ver);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getCount(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getListCount(" + BizRecNo + " ) : " + e.toString());
            throw e;
        }
    }
    
    public CommEntity[] getList(final String bizRecNo, final String ver, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select  NM, IDENT_NO, POSITION, DEPART, EMAIL, PHONE_NO, MOBILE, FAX, VER ");
            sql.append(" from UM_BID_AGENT_HIST t1 ");
            sql.append(" where BIZ_REG_NO= ? AND VER= ? ");
            parameterV.add(bizRecNo);
            parameterV.add(ver);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            final CommEntity[] ett = new CommDbQuery().getList(this, sql.toString(), parameter, conn);
            return ett;
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getList(" + bizRecNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public void deleteNewest(final String bigRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE UM_BID_AGENT_HIST a ");
            sql.append(" where BIZ_REG_NO= ? ");
            sql.append(" AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO ) ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bigRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + bigRegNo + ") : " + e.toString());
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
    
    public void save(final String identNo, final String bizRezNo, final String name, final String depart, final String position, final String phoneNo, final String email, final String mobile, final String fax, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_BID_AGENT_HIST (IDENT_NO, BIZ_REG_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT, VER) ");
            sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, (SELECT NVL(MAX(VER), 0) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ? ) )");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, identNo);
            pstmt.setString(2, bizRezNo);
            pstmt.setString(3, name);
            pstmt.setString(4, depart);
            pstmt.setString(5, position);
            pstmt.setString(6, phoneNo);
            pstmt.setString(7, email);
            pstmt.setString(8, mobile);
            pstmt.setString(9, fax);
            pstmt.setString(10, bizRezNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
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
    
    public void modify(final String identNo, final String bizRezNo, final String name, final String depart, final String position, final String phoneNo, final String email, final String mobile, final String fax, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_BID_AGENT_HIST set BIZ_REG_NO=?, NM=?, DEPART=?, POSITION= ?, PHONE_NO= ?, EMAIL= ?, MOBILE= ?, FAX= ?,  ");
            sql.append(" VER=(SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), '1'+MAX(VER) )  FROM UM_BID_AGENT_HIST WHERE IDENT_NO = ?  ) ");
            sql.append(" where IDENT_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRezNo);
            pstmt.setString(2, name);
            pstmt.setString(3, depart);
            pstmt.setString(4, position);
            pstmt.setString(5, phoneNo);
            pstmt.setString(6, email);
            pstmt.setString(7, mobile);
            pstmt.setString(8, fax);
            pstmt.setString(9, identNo);
            pstmt.setString(10, identNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + identNo + ", " + bizRezNo + ", " + name + ", " + depart + ", " + ") : " + e.toString());
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
    
    public void save(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_BID_AGENT_HIST (IDENT_NO, BIZ_REG_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT, VER) ");
            sql.append(" SELECT IDENT_NO, BIZ_REG_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00') FROM UM_BID_AGENT_HIST WHERE BIZ_REG_NO = ? ) FROM UM_BID_AGENT WHERE BIZ_REG_NO= ?  ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizRegNo);
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
    
    public void saveHist(final String bizRegNo, final String userId, final String sUId, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_BID_AGENT_HIST (IDENT_NO, BIZ_REG_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT, VER, USER_ID, SUB_USER_ID) ");
            sql.append(" SELECT IDENT_NO, BIZ_REG_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00') FROM UM_BID_AGENT_HIST WHERE BIZ_REG_NO = ? ), '" + userId + "' USER_ID, '" + sUId + "' SUB_USER_ID FROM UM_BID_AGENT WHERE BIZ_REG_NO= ?  ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizRegNo);
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
    
    public void updateTemp(final String saupNo, final String juminNumber, final String idTel, final String idMail, final String idPhone, final String idFax, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " INSERT INTO UM_BID_AGENT_HIST ( BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION, PHONE_NO,  EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT, BIDDING_AGENT_YN, VER )  SELECT BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION, ?,  ?, ?, ?, REG_DT, SYSDATE, BIDDING_AGENT_YN,  (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?)  FROM UM_BID_AGENT WHERE BIZ_REG_NO = ? AND IDENT_NO = ? ";
            psmt = con.prepareStatement(query);
            psmt.setString(1, idTel);
            psmt.setString(2, idMail);
            psmt.setString(3, idPhone);
            psmt.setString(4, idFax);
            psmt.setString(5, saupNo);
            psmt.setString(6, saupNo);
            psmt.setString(7, juminNumber);
            psmt.executeUpdate();
        }
        catch (Exception exc) {
            Log.errors(this, exc, "");
            throw exc;
        }
        finally {
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex2) {}
        }
    }
}
