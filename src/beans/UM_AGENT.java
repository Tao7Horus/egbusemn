// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Vector;
import entity.AgentItem;
import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;

public class UM_AGENT
{
    public void delete_Agent(final String saupNo, final String[] code, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " delete 사용_입찰대리인 where 사업자등록번호=? and 주민등록번호=?";
            psmt = con.prepareStatement(query);
            for (int i = 0, n = code.length; i < n; ++i) {
                psmt.setString(1, saupNo);
                psmt.setString(2, code[i]);
                psmt.executeUpdate();
                psmt.clearParameters();
            }
        }
        catch (Exception exc) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete_Agent() saupNo[" + saupNo + "]:" + exc.toString());
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
    
    public void updateIDInfo(final String saupNo, final String juminNumber, final String idTel, final String idMail, final String idPhone, final String idFax, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = " update UM_BID_AGENT set PHONE_NO=?, EMAIL=?, MOBILE=?, FAX=?, UPDATE_DT=sysdate  where BIZ_REG_NO=? and IDENT_NO=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, idTel);
            psmt.setString(2, idMail);
            psmt.setString(3, idPhone);
            psmt.setString(4, idFax);
            psmt.setString(5, saupNo);
            psmt.setString(6, juminNumber);
            if (psmt.executeUpdate() != 1) {
                throw new Exception("Chỉnh sửa người đại diện dự thầu bị lỗi.saupNo[" + saupNo + "] IDENT_NO[" + juminNumber + "]");
            }
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
    
    public AgentItem[] selectUpdate(final String bizRegNo, final Connection conn) throws Exception {
        final String sql = " SELECT IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX FROM UM_BID_AGENT_HIST a WHERE BIZ_REG_NO = ? AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO) ";
        PreparedStatement psmt = null;
        ResultSet res = null;
        AgentItem item = null;
        final Vector vec = new Vector();
        AgentItem[] results = (AgentItem[])null;
        final String[] parameters = { bizRegNo };
        try {
            psmt = conn.prepareStatement(sql);
            for (int i = 0; i < parameters.length; ++i) {
                psmt.setString(i + 1, parameters[i]);
            }
            res = psmt.executeQuery();
            while (res.next()) {
                item = new AgentItem();
                item.setIdentNo(res.getString(1));
                item.setName(res.getString(2));
                item.setDepart(res.getString(3));
                item.setPosition(res.getString(4));
                item.setPhoneNo(res.getString(5));
                item.setEmail(res.getString(6));
                item.setMobile(res.getString(7));
                item.setFax(res.getString(8));
                vec.add(item);
                item = null;
            }
            results = new AgentItem[vec.size()];
            vec.copyInto(results);
            return results;
        }
        catch (Exception exf) {
            Log.errors(this, exf, "");
            throw exf;
        }
    }
    
    public void delete(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_BID_AGENT ");
            sql.append(" where BIZ_REG_NO = ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
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
    
    public void save(final String bizRegNo, final AgentItem[] items, final Connection conn) throws Exception {
        if (items.length < 1) {
            return;
        }
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            AgentItem item = null;
            sql.append("INSERT ALL ");
            final List params = new ArrayList();
            for (int i = 0; i < items.length; ++i) {
                item = items[i];
                sql.append(" INTO UM_BID_AGENT (BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT) ");
                sql.append(" VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, (SELECT REG_DT FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO= ?),  SYSDATE) ");
                params.add(bizRegNo);
                params.add(item.getIdentNo());
                params.add(item.getName());
                params.add(item.getDepart());
                params.add(item.getPosition());
                params.add(item.getPhoneNo());
                params.add(item.getEmail());
                params.add(item.getMobile());
                params.add(item.getFax());
                params.add(bizRegNo);
            }
            sql.append(" SELECT * FROM dual ");
            pstmt = conn.prepareStatement(sql.toString());
            for (int i = 0; i < params.size(); ++i) {
                pstmt.setString(i + 1, (params.get(i) == null) ? "" : params.get(i).toString());
            }
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".save(" + bizRegNo + ")\n\n" + e.toString());
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
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
    }
}
