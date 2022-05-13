// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Vector;
import entity.AgentItem;
import entity.UserItem;

import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;

public class UM_USER
{
//    public void delete_User(final String saupNo, final String[] code, final Connection con) throws Exception {
//        String query = null;
//        PreparedStatement psmt = null;
//        try {
//            if (con == null) {
//                throw new Exception("Connection is null");
//            }
//            query = " delete 사용_입찰대리인 where 사업자등록번호=? and 주민등록번호=?";
//            psmt = con.prepareStatement(query);
//            for (int i = 0, n = code.length; i < n; ++i) {
//                psmt.setString(1, saupNo);
//                psmt.setString(2, code[i]);
//                psmt.executeUpdate();
//                psmt.clearParameters();
//            }
//        }
//        catch (Exception exc) {
//            Log.debug(String.valueOf(this.getClass().getName()) + ".delete_Agent() saupNo[" + saupNo + "]:" + exc.toString());
//            throw exc;
//        }
//        finally {
//            if (psmt != null) {
//                try {
//                    psmt.close();
//                }
//                catch (Exception ex) {}
//            }
//        }
//        if (psmt != null) {
//            try {
//                psmt.close();
//            }
//            catch (Exception ex2) {}
//        }
//    }
//    
    public void updateIDInfo(final String saupNo, final String uName, final String uDepart, final String uMail, final String uPhone, final String uFax, final String uMobile, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
          
            query = " update UM_USER set CHRGR_NM=? , CHRGR_DEPART=?, CHRGR_PHONE_NO=?, CHRGR_EMAIL=?, CHRGR_MOBILE=?, CHRGR_FAX=?, UPDATE_DT=sysdate  where USER_ID=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, uName);
            psmt.setString(2, uDepart);
            psmt.setString(3, uPhone);
            psmt.setString(4, uMail);
            psmt.setString(5, uMobile);
            psmt.setString(6, uFax);
            psmt.setString(7, saupNo);
            
           Log.debug(query + " " + uName + "," + uDepart + "," + uMail + "," + uPhone + "," + uFax + "," + uMobile + "," + saupNo );
            if (psmt.executeUpdate() < 1) {
                throw new Exception("Chỉnh sửa người đại diện bị lỗi.user_id[" + saupNo + "]" );
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

    
    public void saveHist(final String bizRegNo, final UserItem[] items, final Connection conn) throws Exception {
      if (items.length < 1) {
          return;
      }
      final StringBuffer sql = new StringBuffer();
      PreparedStatement pstmt = null;
      try {
          if (conn == null) {
              throw new Exception("[Connection is null]");
          }
          UserItem item = null;
          sql.append("INSERT ALL ");
          final List params = new ArrayList();
          for (int i = 0; i < items.length; ++i) {
              item = items[i];
              sql.append(" INTO UM_USER_HIST (USER_ID, CHRGR_NM, CHRGR_DEPART,  CHRGR_PHONE_NO, CHRGR_EMAIL, CHRGR_MOBILE, CHRGR_FAX, FIRST_REG_DT , INPUT_DT, MAST_CD,  VER, USER_CLS,  PERMIT_YN, INSTITU_CD, PASSWORD, STATE_CLS, BIZ_REG_NO ) ");
              sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, TO_DATE(?,'YYYY-MM-DD HH24:MI:SS'), SYSDATE, ?,(SELECT MAX(VER) FROM USEMN.UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?), ?, ?, ?, ?, ?, ?) ");
              params.add(item.getSaupNo());
              params.add(item.getName());
              params.add(item.getDepart());
              params.add(item.getPhoneNo());
              params.add(item.getEmail());

              params.add(item.getMobile());
              params.add(item.getFax());
              params.add(item.getFirstregdt());
              params.add(item.getMastcd());
        
              params.add(bizRegNo);
              params.add(item.getUsercls());
             
              params.add(item.getPermityn());
              params.add(item.getInstitu_cd());
              params.add(item.getPassword());
              params.add(item.getUsercls());
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
          Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".save UM_USER_HIST \n\n" + e.toString());
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
    
    public UserItem[] getHist(final String user_id, final Connection conn) throws Exception {
      final String sql = " SELECT USER_ID, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_EMAIL, CHRGR_MOBILE, CHRGR_FAX, MAST_CD, USER_CLS, FIRST_REG_DT, PERMIT_YN, INSTITU_CD, PASSWORD  FROM UM_USER a WHERE USER_ID = ? ";
      PreparedStatement psmt = null;
      ResultSet res = null;
      UserItem item = null;
      final Vector vec = new Vector();
      UserItem[] results = (UserItem[])null;
      final String[] parameters = { user_id };
      try {
          psmt = conn.prepareStatement(sql);
          for (int i = 0; i < parameters.length; ++i) {
              psmt.setString(i + 1, parameters[i]);
          }
          res = psmt.executeQuery();
          while (res.next()) {
              item = new UserItem();
              item.setSaupNo(res.getString(1));
              item.setName(res.getString(2));
              item.setDepart(res.getString(3));
              item.setPhoneNo(res.getString(4));
              item.setEmail(res.getString(5));
              item.setMobile(res.getString(6));
              item.setFax(res.getString(7));
              item.setMastcd(res.getString(8));
              item.setUsercls(res.getString(9));
              item.setFirstregdt(res.getString(10));
              item.setPermityn(res.getString(11));
              item.setInstitu_cd(res.getString(12));
              item.setPassword(res.getString(13));
              
              vec.add(item);
              item = null;
          }
          results = new UserItem[vec.size()];
          vec.copyInto(results);
          return results;
      }
      catch (Exception exf) {
          Log.errors(this, exf, "");
          throw exf;
      }
  }

//    public AgentItem[] selectUpdate(final String bizRegNo, final Connection conn) throws Exception {
//        final String sql = " SELECT IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX FROM UM_BID_AGENT_HIST a WHERE BIZ_REG_NO = ? AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO) ";
//        PreparedStatement psmt = null;
//        ResultSet res = null;
//        AgentItem item = null;
//        final Vector vec = new Vector();
//        AgentItem[] results = (AgentItem[])null;
//        final String[] parameters = { bizRegNo };
//        try {
//            psmt = conn.prepareStatement(sql);
//            for (int i = 0; i < parameters.length; ++i) {
//                psmt.setString(i + 1, parameters[i]);
//            }
//            res = psmt.executeQuery();
//            while (res.next()) {
//                item = new AgentItem();
//                item.setIdentNo(res.getString(1));
//                item.setName(res.getString(2));
//                item.setDepart(res.getString(3));
//                item.setPosition(res.getString(4));
//                item.setPhoneNo(res.getString(5));
//                item.setEmail(res.getString(6));
//                item.setMobile(res.getString(7));
//                item.setFax(res.getString(8));
//                vec.add(item);
//                item = null;
//            }
//            results = new AgentItem[vec.size()];
//            vec.copyInto(results);
//            return results;
//        }
//        catch (Exception exf) {
//            Log.errors(this, exf, "");
//            throw exf;
//        }
//    }
//    
//    public void delete(final String bizRegNo, final Connection conn) throws Exception {
//        final StringBuffer sql = new StringBuffer();
//        PreparedStatement pstmt = null;
//        try {
//            if (conn == null) {
//                throw new Exception("[Connection is null]");
//            }
//            sql.append(" DELETE FROM UM_BID_AGENT ");
//            sql.append(" where BIZ_REG_NO = ? ");
//            pstmt = conn.prepareStatement(sql.toString());
//            pstmt.setString(1, bizRegNo);
//            pstmt.executeUpdate();
//        }
//        catch (Exception e) {
//            Log.errors(this, e, "");
//            throw e;
//        }
//        finally {
//            if (pstmt != null) {
//                try {
//                    pstmt.close();
//                }
//                catch (Exception ex) {}
//            }
//        }
//    }
//    
//    public void save(final String bizRegNo, final AgentItem[] items, final Connection conn) throws Exception {
//        if (items.length < 1) {
//            return;
//        }
//        final StringBuffer sql = new StringBuffer();
//        PreparedStatement pstmt = null;
//        try {
//            if (conn == null) {
//                throw new Exception("[Connection is null]");
//            }
//            AgentItem item = null;
//            sql.append("INSERT ALL ");
//            final List params = new ArrayList();
//            for (int i = 0; i < items.length; ++i) {
//                item = items[i];
//                sql.append(" INTO UM_BID_AGENT (BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION, PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT) ");
//                sql.append(" VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, (SELECT REG_DT FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO= ?),  SYSDATE) ");
//                params.add(bizRegNo);
//                params.add(item.getIdentNo());
//                params.add(item.getName());
//                params.add(item.getDepart());
//                params.add(item.getPosition());
//                params.add(item.getPhoneNo());
//                params.add(item.getEmail());
//                params.add(item.getMobile());
//                params.add(item.getFax());
//                params.add(bizRegNo);
//            }
//            sql.append(" SELECT * FROM dual ");
//            pstmt = conn.prepareStatement(sql.toString());
//            for (int i = 0; i < params.size(); ++i) {
//                pstmt.setString(i + 1, (params.get(i) == null) ? "" : params.get(i).toString());
//            }
//            pstmt.executeUpdate();
//        }
//        catch (Exception e) {
//            Log.errors(this, e, String.valueOf(this.getClass().getName()) + ".save(" + bizRegNo + ")\n\n" + e.toString());
//            throw e;
//        }
//        finally {
//            if (pstmt != null) {
//                try {
//                    pstmt.close();
//                }
//                catch (Exception ex) {}
//            }
//        }
//        if (pstmt != null) {
//            try {
//                pstmt.close();
//            }
//            catch (Exception ex2) {}
//        }
//    }
    
}
