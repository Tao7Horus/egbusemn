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

public class UM_RAB_MastBidAgent
{
    public OneRowEntity selectDetail(final String IdentNo, final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX ");
            sql.append(" from UM_REC_BID_AGENT t1 ");
            sql.append(" where t1.IDENT_NO = ? ");
            sql.append(" and t1.BIZ_REG_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { IdentNo, bizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + IdentNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public int getListCount(final String BizRecNo, final String name, final String depart, final String catno, final String phoneNo, final String email, final String mobile, final String fax, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" SELECT COUNT(*) ");
            sql.append(" FROM UM_BID_AGENT t1 ");
            sql.append(" WHERE  BIZ_REG_NO= ?");
            sql.append(" \tAND t1.NM = ? ");
            sql.append(" \tAND t1.DEPART= ? ");
            sql.append(" \tAND t1.POSITION= ? ");
            sql.append(" \tAND t1.PHONE_NO= ? ");
            sql.append(" \tAND t1.EMAIL= ? ");
            sql.append(" \tAND t1.MOBILE= ? ");
            sql.append(" \tAND t1.FAX= ? ");
            parameterV.add(BizRecNo);
            parameterV.add(name);
            parameterV.add(depart);
            parameterV.add(catno);
            parameterV.add(phoneNo);
            parameterV.add(email);
            parameterV.add(mobile);
            parameterV.add(fax);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getCount(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getListCount(" + BizRecNo + ", " + name + ", " + depart + " ) : " + e.toString());
            throw e;
        }
    }
    
    public int getListCount(final String BizRecNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" SELECT COUNT(*) ");
            sql.append(" FROM UM_BID_AGENT t1 ");
            sql.append(" WHERE  BIZ_REG_NO= ?");
            parameterV.add(BizRecNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getCount(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getListCount(" + BizRecNo + " ) : " + e.toString());
            throw e;
        }
    }
    
    public CommEntity[] getList(final String bizRecNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select  NM, IDENT_NO, POSITION, DEPART, EMAIL, PHONE_NO, MOBILE, FAX ");
            sql.append(" from UM_BID_AGENT t1 ");
            sql.append(" where BIZ_REG_NO= ? ");
            parameterV.add(bizRecNo);
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
    
    public void delete(final String identNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_BID_AGENT ");
            sql.append(" where IDENT_NO= ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, identNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Xóa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + identNo + ") : " + e.toString());
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
    
    public void deleteAll(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_BID_AGENT ");
            sql.append(" where BIZ_REG_NO= ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + bizRegNo + ") : " + e.toString());
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
            sql.append(" insert into UM_BID_AGENT (IDENT_NO, BIZ_REG_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT) ");
            sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE)");
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
    
    public void saveFromRec(final String bizRezNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append("INSERT INTO UM_BID_AGENT ");
            sql.append("    (IDENT_NO, BIZ_REG_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT) ");
            sql.append("SELECT ");
            sql.append("    IDENT_NO, BIZ_REG_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, SYSDATE, SYSDATE ");
            sql.append("FROM UM_REC_BID_AGENT WHERE BIZ_REG_NO = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRezNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
        	Log.debug(String.valueOf(this.getClass().getName()) + ".saveFromRec(" + bizRezNo + ") : " + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {
                	Log.debug(String.valueOf(this.getClass().getName()) + ".saveFromRec(" + bizRezNo + ") : " + ex.toString());
                    throw ex;
                }
            }
        }
    }
    
    public void saveAgain(final String identNo, final String bizRezNo, final String name, final String depart, final String position, final String phoneNo, final String email, final String mobile, final String fax, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_BID_AGENT (IDENT_NO, BIZ_REG_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT) ");
            sql.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT REG_DT FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO = ?), SYSDATE)");
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
    
    public void modify(final String identNo, final String bizRezNo, final String name, final String depart, final String position, final String phoneNo, final String email, final String mobile, final String fax, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_BID_AGENT set BIZ_REG_NO=?, NM=?, DEPART=?, POSITION= ?, PHONE_NO= ?, EMAIL= ?, MOBILE= ?, FAX= ? ");
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
}
