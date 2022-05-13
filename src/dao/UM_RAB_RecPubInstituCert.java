// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.util.Hashtable;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public class UM_RAB_RecPubInstituCert
{
    public String getCertRecetionNumber(final Connection con, final HttpServletRequest req) throws Exception {
        String result = "";
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        final String sql = "SELECT to_char(sysdate,'yyyy')||lpad((substr(max(RECV_NO), 5, 10)+1), 6, '0') FROM UM_REC_PUB_INSTITU_CERT";
        try {
            pstmt = con.prepareStatement(sql);
            rest = pstmt.executeQuery();
            if (!rest.next()) {
                throw new Exception("Đăng ký chứng nhấn số có RECV_NO Null");
            }
            result = rest.getString(1);
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
        final String approvedCode = String.valueOf(result.substring(2, result.length())) + req.getParameter("caUserId");
        return approvedCode;
    }
    
    public String getCertRecetionNumber2(final Connection con, final Hashtable hast) throws Exception {
        String result = "";
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        final String sql = "SELECT to_char(sysdate,'yyyy')||lpad((substr(max(RECV_NO), 5, 10)+1), 6, '0') FROM UM_REC_PUB_INSTITU_CERT";
        try {
            pstmt = con.prepareStatement(sql);
            rest = pstmt.executeQuery();
            if (!rest.next()) {
                throw new Exception("Đăng ký chứng nhấn số có RECV_NO Null");
            }
            result = rest.getString(1);
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
        final String approvedCode = String.valueOf(result.substring(2, result.length())) + ((hast.get("caUserId") == null) ? "" : hast.get("caUserId").toString());
        return approvedCode;
    }
    
    public OneRowEntity selectDetail(final String recvNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, ADDR, APPROVE_REQ_CD, USER_ID, ZIP_CD ");
            sql.append(" from UM_REC_PUB_INSTITU_CERT ");
            sql.append(" where RECV_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { recvNo }, conn);
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public OneRowEntity getAppReqCd(final String recvNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, ADDR, APPROVE_REQ_CD, USER_ID, ISSUE_YN");
            sql.append(" from UM_REC_PUB_INSTITU_CERT ");
            sql.append(" where RECV_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { recvNo }, conn);
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + recvNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public void delete(final String recvNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_REC_PUB_INSTITU_CERT ");
            sql.append(" where RECV_NO= ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, recvNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Xóa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + recvNo + ") : " + e.toString());
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
    
    public void save(final String recvNo, final String bizRegNo, final String instituFullName, final String instituEnName, final String reprName, final String reprIdentNo, final String userID, final String chrgrDepart, final String chrgrPhone, final String chrgrMobile, final String chrgrEmail, final String chrgrFax, final String addr, final String website, final HttpServletRequest req, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_REC_PUB_INSTITU_CERT (RECV_NO, BIZ_REG_NO, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, USER_ID, APPROVE_REQ_CD, CERT_INSTITU, CERT_CLS, INSTITU_CD, REG_YN, PERMIT_BRANCH, CHRGR_MN, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, FAX, ADDR, DETAIL_ADDR, WEBSITE, CHRGR_DEPART )");
            sql.append(" values (?, ?, ?, ?, ?, ?, ? , ?, '5', 'S', ?, 'N', '00', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, recvNo);
            pstmt.setString(2, bizRegNo);
            pstmt.setString(3, instituFullName);
            pstmt.setString(4, instituEnName);
            pstmt.setString(5, reprName);
            pstmt.setString(6, reprIdentNo);
            pstmt.setString(7, userID);
            pstmt.setString(8, this.getCertRecetionNumber(conn, req));
            pstmt.setString(9, bizRegNo);
            pstmt.setString(10, reprName);
            pstmt.setString(11, reprIdentNo);
            pstmt.setString(12, chrgrPhone);
            pstmt.setString(13, chrgrEmail);
            pstmt.setString(14, chrgrMobile);
            pstmt.setString(15, chrgrFax);
            pstmt.setString(16, addr);
            pstmt.setString(17, addr);
            pstmt.setString(18, website);
            pstmt.setString(19, chrgrDepart);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + recvNo + ", " + bizRegNo + ", " + instituFullName + ") : " + e.toString());
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
    
    public void save2(final String recvNo, final String bizRegNo, final String instituFullName, final String instituEnName, final String reprName, final String reprIdentNo, final String userID, final String chrgrIdent, final String chrgrName, final String chrgrDepart, final String chrgrPhone, final String chrgrMobile, final String chrgrEmail, final String chrgrFax, final String addr, final String website, final HttpServletRequest req, final Connection conn, final String cert_institu) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_REC_PUB_INSTITU_CERT (RECV_NO, BIZ_REG_NO, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, USER_ID, APPROVE_REQ_CD, CERT_INSTITU, CERT_CLS, INSTITU_CD, REG_YN, PERMIT_BRANCH, CHRGR_MN, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, FAX, ADDR, DETAIL_ADDR, WEBSITE, CHRGR_DEPART )");
            sql.append(" values (?, ?, ?, ?, ?, ?, ? , ?, ?, 'S', ?, 'N', '00', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, recvNo);
            pstmt.setString(2, bizRegNo);
            pstmt.setString(3, instituFullName);
            pstmt.setString(4, instituEnName);
            pstmt.setString(5, reprName);
            pstmt.setString(6, reprIdentNo);
            pstmt.setString(7, userID);
            pstmt.setString(8, this.getCertRecetionNumber(conn, req));
            pstmt.setString(9, cert_institu);
            pstmt.setString(10, bizRegNo);
            pstmt.setString(11, chrgrName);
            pstmt.setString(12, chrgrIdent);
            pstmt.setString(13, chrgrPhone);
            pstmt.setString(14, chrgrEmail);
            pstmt.setString(15, chrgrMobile);
            pstmt.setString(16, chrgrFax);
            pstmt.setString(17, addr);
            pstmt.setString(18, addr);
            pstmt.setString(19, website);
            pstmt.setString(20, chrgrDepart);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + recvNo + ", " + bizRegNo + ", " + instituFullName + ") : " + e.toString());
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
    
    public void save3(final String recvNo, final String bizRegNo, final String instituFullName, final String instituEnName, final String reprName, final String reprIdentNo, final String userID, final String chrgrIdent, final String chrgrName, final String chrgrDepart, final String chrgrPhone, final String chrgrMobile, final String chrgrEmail, final String chrgrFax, final String addr, final String website, final Hashtable hast, final Connection conn, final String cert_institu) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_REC_PUB_INSTITU_CERT (RECV_NO, BIZ_REG_NO, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, USER_ID, APPROVE_REQ_CD, CERT_INSTITU, CERT_CLS, INSTITU_CD, REG_YN, PERMIT_BRANCH, CHRGR_MN, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, FAX, ADDR, DETAIL_ADDR, WEBSITE, CHRGR_DEPART )");
            sql.append(" values (?, ?, ?, ?, ?, ?, ? , ?, ?, 'S', ?, 'N', '00', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, recvNo);
            pstmt.setString(2, bizRegNo);
            pstmt.setString(3, instituFullName);
            pstmt.setString(4, instituEnName);
            pstmt.setString(5, reprName);
            pstmt.setString(6, reprIdentNo);
            pstmt.setString(7, userID);
            pstmt.setString(8, this.getCertRecetionNumber2(conn, hast));
            pstmt.setString(9, cert_institu);
            pstmt.setString(10, bizRegNo);
            pstmt.setString(11, chrgrName);
            pstmt.setString(12, chrgrIdent);
            pstmt.setString(13, chrgrPhone);
            pstmt.setString(14, chrgrEmail);
            pstmt.setString(15, chrgrMobile);
            pstmt.setString(16, chrgrFax);
            pstmt.setString(17, addr);
            pstmt.setString(18, addr);
            pstmt.setString(19, website);
            pstmt.setString(20, chrgrDepart);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + recvNo + ", " + bizRegNo + ", " + instituFullName + ") : " + e.toString());
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
    
    public void modify(final String bizRezNo, final String recvNo, final String appReqCode, final String regYN, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            String sql = " update UM_REC_PUB_INSTITU_CERT set REG_YN = '" + regYN + "', RECV_NO = '" + recvNo + "' ";
            sql = String.valueOf(sql) + " where BIZ_REG_NO = '" + bizRezNo + "'";
            pstmt = conn.prepareStatement(sql);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRezNo + ")");
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
    
    public void modify(final String bizRezNo, final String recvNo, final String regYN, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            String sql = " update UM_REC_PUB_INSTITU_CERT set REG_YN = '" + regYN + "' ";
            sql = String.valueOf(sql) + " where BIZ_REG_NO = '" + bizRezNo + "' and RECV_NO = '" + recvNo + "' ";
            pstmt = conn.prepareStatement(sql);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRezNo + ")");
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
    
    public void modify(final String bizRezNo, final String recvNo, final String appReqCode, final String regYN, final String bizName, final String bizEnName, final String caReprName, final String caReprIdentNo, final String caUserId, final String chrgrName, final String chrgrIdent, final String bizDepart, final String bizPhoneNo, final String bizMobile, final String bizAgentEmail, final String bizFax, final String addr, final String homePage, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            String sql = " update UM_REC_PUB_INSTITU_CERT set REG_YN = '" + regYN + "', INSTITU_FULL_NM = '" + bizName + "', INSTITU_EN_NM = '" + bizEnName + "', " + "REPR_NM = '" + caReprName + "', REPR_IDENT_NO = '" + caReprIdentNo + "' " + ", USER_ID = '" + caUserId + "', CERT_INSTITU = '5', " + "CERT_CLS = 'S', INSTITU_CD = '" + bizRezNo + "' " + ", PERMIT_BRANCH = '00', CHRGR_MN = '" + chrgrName + "', " + "CHRGR_IDENT_NO = '" + chrgrIdent + "', CHRGR_PHONE_NO = '" + bizPhoneNo + "' " + ", CHRGR_EMAIL = '" + bizAgentEmail + "', MOBILE = '" + bizMobile + "', " + "FAX = '" + bizFax + "', ADDR = '" + addr + "' " + ", WEBSITE = '" + homePage + "', CHRGR_DEPART = '" + bizDepart + "'";
            sql = String.valueOf(sql) + " where BIZ_REG_NO = '" + bizRezNo + "' and RECV_NO = '" + recvNo + "'";
            pstmt = conn.prepareStatement(sql);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRezNo + ")");
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
    
    public void modify2(final String bizRezNo, final String recvNo, final String appReqCode, final String regYN, final String bizName, final String bizEnName, final String caReprName, final String caReprIdentNo, final String caUserId, final String bizDepart, final String bizPhoneNo, final String bizMobile, final String bizAgentEmail, final String bizFax, final String addr, final String homePage, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            String sql = " update UM_REC_PUB_INSTITU_CERT set REG_YN = '" + regYN + "', INSTITU_FULL_NM = '" + bizName + "', INSTITU_EN_NM = '" + bizEnName + "', " + "REPR_NM = '" + caReprName + "', REPR_IDENT_NO = '" + caReprIdentNo + "' " + ", USER_ID = '" + caUserId + "', CERT_INSTITU = '5', " + "CERT_CLS = 'S', INSTITU_CD = '" + bizRezNo + "' " + ", PERMIT_BRANCH = '00', CHRGR_MN = '" + caReprName + "', " + "CHRGR_IDENT_NO = '" + caReprIdentNo + "', CHRGR_PHONE_NO = '" + bizPhoneNo + "' " + ", CHRGR_EMAIL = '" + bizAgentEmail + "', MOBILE = '" + bizMobile + "', " + "FAX = '" + bizFax + "', ADDR = '" + addr + "' " + ", WEBSITE = '" + homePage + "', CHRGR_DEPART = '" + bizDepart + "'" + ", BIZ_REG_NO = '" + bizRezNo + "'";
            sql = String.valueOf(sql) + " where RECV_NO = '" + recvNo + "'";
            pstmt = conn.prepareStatement(sql);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRezNo + ")");
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
    
    public void modify3(final String bizRezNo, final String recvNo, final String appReqCode, final String regYN, final String bizName, final String bizEnName, final String caReprName, final String caReprIdentNo, final String caUserId, final String bizDepart, final String bizChrgrIdent, final String bizChrgrName, final String bizPhoneNo, final String bizMobile, final String bizAgentEmail, final String bizFax, final String addr, final String homePage, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            String sql = " update UM_REC_PUB_INSTITU_CERT set REG_YN = '" + regYN + "', INSTITU_FULL_NM = '" + bizName + "', INSTITU_EN_NM = '" + bizEnName + "', " + "REPR_NM = '" + caReprName + "', REPR_IDENT_NO = '" + caReprIdentNo + "' " + ", USER_ID = '" + caUserId + "', CERT_INSTITU = '5', " + "CERT_CLS = 'S', INSTITU_CD = '" + bizRezNo + "' " + ", PERMIT_BRANCH = '00', CHRGR_MN = '" + bizChrgrName + "', " + "CHRGR_IDENT_NO = '" + bizChrgrIdent + "', CHRGR_PHONE_NO = '" + bizPhoneNo + "' " + ", CHRGR_EMAIL = '" + bizAgentEmail + "', MOBILE = '" + bizMobile + "', " + "FAX = '" + bizFax + "', ADDR = '" + addr + "' " + ", WEBSITE = '" + homePage + "', CHRGR_DEPART = '" + bizDepart + "'" + ", BIZ_REG_NO = '" + bizRezNo + "'";
            sql = String.valueOf(sql) + " where RECV_NO = '" + recvNo + "'";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRezNo + ")");
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
    
    public void modify4(final String bizRezNo, final String recvNo, final String appReqCode, final String regYN, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            String sql = " update UM_REC_PUB_INSTITU_CERT set REG_YN = '" + regYN + "'  ";
            sql = String.valueOf(sql) + " where BIZ_REG_NO = '" + bizRezNo + "'";
            pstmt = conn.prepareStatement(sql);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRezNo + ")");
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
    
    public void modify5(final String bizRezNo, final String addr, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            String sql = " update UM_REC_PUB_INSTITU_CERT set ADDR = '" + addr + "'  ";
            sql = String.valueOf(sql) + " where INSTITU_CD = '" + bizRezNo + "'";
            pstmt = conn.prepareStatement(sql);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRezNo + ")");
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
