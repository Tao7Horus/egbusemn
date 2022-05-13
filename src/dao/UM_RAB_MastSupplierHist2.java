// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.Trx;
import common.Log;
import java.sql.Connection;

public class UM_RAB_MastSupplierHist2
{
    public UM_RAB_MastSupplierHist2_Model getSupIDFormUmSupp2(final String RECV_NO, Connection conn) {
        final StringBuffer sql = new StringBuffer();
        Log.debug("RECV_NO: " + RECV_NO);
        final UM_RAB_MastSupplierHist2_Model model = new UM_RAB_MastSupplierHist2_Model();
        sql.append(" select PERMIT_USER_ID, SUB_USER_ID ");
        sql.append(" from  UM_REC_PUB_INSTITU_CERT_HIST ");
        sql.append(" where RECV_NO = ? ");
        Log.debug("sql: " + sql.toString());
        Trx trx = null;
        PreparedStatement ps = null;

        try {            
            ResultSet rs = null;
            trx = new Trx(this);
            conn = trx.getConnection();
            Log.debug("43243: " + conn);
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, RECV_NO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Log.debug("rs.getString(1): " + rs.getString(1));
                Log.debug("rs.getString(2): " + rs.getString(2));
                model.setStr1(rs.getString(1));
                model.setStr2(rs.getString(2));
            }
        }
        catch (SQLException e) {
            Log.debug("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        finally {
        	if (ps != null) {
        		try {
        			ps.close();
        		} catch (Exception e) {
        			
        		}
        	}

        	if (conn != null) {
        		try {
        			trx.close();
        		} catch (Exception e) {
        			
        		}
        	}        	
        }
        Log.debug("1: " + model.getStr1());
        Log.debug("2: " + model.getStr2());
        return model;
    }
    
    public UM_RAB_MastSupplierHist2_Model getSupIDFormUmSupp(final String bizRegNo, Connection conn) {
        final StringBuffer sql = new StringBuffer();
        Log.debug("bizRegNo: " + bizRegNo);
        final UM_RAB_MastSupplierHist2_Model model = new UM_RAB_MastSupplierHist2_Model();
        sql.append(" select USER_ID, SUB_USER_ID ");
        sql.append(" from  UM_SUPPLIER_ENTER_MAST_HIST ");
        sql.append(" where BIZ_REG_NO = ? ");
        Log.debug("sql: " + sql.toString());
        Trx trx = null;
        PreparedStatement ps = null;

        try {            
            ResultSet rs = null;
            trx = new Trx(this);
            conn = trx.getConnection();
            Log.debug("43243: " + conn);
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, bizRegNo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Log.debug("rs.getString(1): " + rs.getString(1));
                Log.debug("rs.getString(2): " + rs.getString(2));
                model.setStr1(rs.getString(1));
                model.setStr2(rs.getString(2));
            }
        }
        catch (SQLException e) {
            Log.debug("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        } finally {
        	if (ps != null) {
        		try {
        			ps.close();
        		} catch (Exception e) {
        			
        		}
        	}
        	
        	if (conn != null) {
        		try {
        			trx.close();
        		} catch (Exception e) {
        			
        		}
        	}
        }
        Log.debug("1: " + model.getStr1());
        Log.debug("2: " + model.getStr2());
        return model;
    }
    
    public UM_RAB_MastSupplierHist2_Model getFulNameSupID(final String userID, final String supID, Connection conn) {
        final StringBuffer sql = new StringBuffer();
        final UM_RAB_MastSupplierHist2_Model model = new UM_RAB_MastSupplierHist2_Model();
        sql.append(" select USER_ID, SUB_USER_FULLNAME, SUB_USER_NAME ");
        sql.append(" from  UM_SUB_USER ");
        sql.append(" where SUB_USER_ID = ? and USER_ID = ? ");
        Trx trx = null;
        PreparedStatement ps = null;
        try {
            
            trx = new Trx(this);
            conn = trx.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, supID);
            ps.setString(2, userID);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.setStr1(rs.getString(1));
                model.setStr2(rs.getString(2));
                model.setStr3(rs.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        } finally {
        	if (ps != null) {
        		try {
        			ps.close();
        		} catch (Exception e) {
        			
        		}
        	}
        	 
        	if (conn != null) {
        		try {
        			trx.close();
        		} catch (Exception e) {
        			
        		}
        	}
        }
        return model;
    }
    
    public UM_RAB_MastSupplierHist2_Model getFulNameUserID(final String userID, Connection conn) {
        final StringBuffer sql = new StringBuffer();
        final UM_RAB_MastSupplierHist2_Model model = new UM_RAB_MastSupplierHist2_Model();
        sql.append(" select CHRGR_NM, CHRGR_DEPART ");
        sql.append(" from  UM_USER ");
        sql.append(" where USER_ID = ? ");
        Trx trx = null;
        PreparedStatement ps = null;
        try {
            
            trx = new Trx(this);
            conn = trx.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, userID);
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.setStr1(rs.getString(1));
                model.setStr2(rs.getString(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        finally {
        	if (ps != null) {        		
        		try {
					ps.close();
				} catch (SQLException e) {
					
				}
        	}
        	
        	if (conn != null) {
        		trx.close();
        	}
        }
        return model;
    }
}
