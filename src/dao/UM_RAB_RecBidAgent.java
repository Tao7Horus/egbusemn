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

public class UM_RAB_RecBidAgent
{
    public OneRowEntity selectDetail(final String s, final String s2, final Connection conn) throws Exception {
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" select BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX ");
            sb.append(" from UM_REC_BID_AGENT t1 ");
            sb.append(" where t1.IDENT_NO = ? ");
            sb.append(" and t1.BIZ_REG_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sb.toString(), new String[] { s, s2 }, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".selectDetail(" + s + ") : " + ex.toString());
            throw ex;
        }
    }
    
    public int getListCount(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final Connection conn) throws Exception {
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<String>();
        try {
            sb.append(" SELECT COUNT(*) ");
            sb.append(" FROM UM_REC_BID_AGENT t1 ");
            sb.append(" WHERE  BIZ_REG_NO= ?");
            sb.append(" \tAND t1.NM = ? ");
            sb.append(" \tAND t1.DEPART= ? ");
            sb.append(" \tAND t1.POSITION= ? ");
            sb.append(" \tAND t1.PHONE_NO= ? ");
            sb.append(" \tAND t1.EMAIL= ? ");
            sb.append(" \tAND t1.MOBILE= ? ");
            sb.append(" \tAND t1.FAX= ? ");
            vector.add(s);
            vector.add(s2);
            vector.add(s3);
            vector.add(s4);
            vector.add(s5);
            vector.add(s6);
            vector.add(s7);
            vector.add(s8);
            final String[] parameter = new String[vector.size()];
            vector.copyInto(parameter);
            return new CommDbQuery().getCount(this, sb.toString(), parameter, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".getListCount(" + s + ", " + s2 + ", " + s3 + " ) : " + ex.toString());
            throw ex;
        }
    }
    
    public int getListCount(final String s, final Connection conn) throws Exception {
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<String>();
        try {
            sb.append(" SELECT COUNT(*) ");
            sb.append(" FROM UM_REC_BID_AGENT t1 ");
            sb.append(" WHERE  BIZ_REG_NO= ?");
            vector.add(s);
            final String[] parameter = new String[vector.size()];
            vector.copyInto(parameter);
            return new CommDbQuery().getCount(this, sb.toString(), parameter, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".getListCount(" + s + " ) : " + ex.toString());
            throw ex;
        }
    }
    
    public CommEntity[] getList(final String s, final Connection conn) throws Exception {
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<String>();
        try {
            sb.append(" select  NM, IDENT_NO, POSITION, DEPART, EMAIL, PHONE_NO, MOBILE, FAX ");
            sb.append(" from UM_REC_BID_AGENT t1 ");
            sb.append(" where BIZ_REG_NO= ? ");
            vector.add(s);
            final String[] parameter = new String[vector.size()];
            vector.copyInto(parameter);
            return new CommDbQuery().getList(this, sb.toString(), parameter, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".getList(" + s + ") : " + ex.toString());
            throw ex;
        }
    }
    
    public void delete(final String s, final Connection connection) throws Exception {
        final StringBuffer sb = new StringBuffer();
        PreparedStatement prepareStatement = null;
        try {
            if (connection == null) {
                throw new Exception("[Connection is null]");
            }
            sb.append(" DELETE FROM UM_REC_BID_AGENT ");
            sb.append(" where BIZ_REG_NO = '" + s + "'");
            System.out.println(sb);
            prepareStatement = connection.prepareStatement(sb.toString());
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("[Xóa bị lỗi]");
            }
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".delete(" + s + ") : " + ex.toString());
            throw ex;
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public void save(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final Connection connection) throws Exception {
        final StringBuffer sb = new StringBuffer();
        PreparedStatement prepareStatement = null;
        try {
            if (connection == null) {
                throw new Exception("[Connection is null]");
            }
            sb.append(" insert into UM_REC_BID_AGENT (IDENT_NO, BIZ_REG_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX) ");
            sb.append(" values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, s3);
            prepareStatement.setString(4, s4);
            prepareStatement.setString(5, s5);
            prepareStatement.setString(6, s6);
            prepareStatement.setString(7, s7);
            prepareStatement.setString(8, s8);
            prepareStatement.setString(9, s9);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".save(" + s2 + ", " + s3 + ", " + s4 + ") : " + ex.toString());
            throw ex;
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public void modify(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final Connection connection) throws Exception {
        final StringBuffer sb = new StringBuffer();
        PreparedStatement prepareStatement = null;
        try {
            if (connection == null) {
                throw new Exception("[Connection is null]");
            }
            sb.append(" update UM_REC_BID_AGENT set BIZ_REG_NO=?, NM=?, DEPART=?, POSITION= ?, PHONE_NO= ?, EMAIL= ?, MOBILE= ?, FAX= ? ");
            sb.append(" where IDENT_NO=? ");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s3);
            prepareStatement.setString(3, s4);
            prepareStatement.setString(4, s5);
            prepareStatement.setString(5, s6);
            prepareStatement.setString(6, s7);
            prepareStatement.setString(7, s8);
            prepareStatement.setString(8, s9);
            prepareStatement.setString(9, s);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".modify(" + s + ", " + s2 + ", " + s3 + ", " + s4 + ", " + ") : " + ex.toString());
            throw ex;
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
}
