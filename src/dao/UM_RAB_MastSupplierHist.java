// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import common.ComDbQuery;
import entity.UM_ADJ_GovuA020b;
import common.CommEntity;
import common.util.CommUtil;
import java.sql.SQLException;
import common.Trx;
import java.util.Vector;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class UM_RAB_MastSupplierHist
{
    public String getChodalReceptionNumber(final Connection con) throws Exception {
        String result = "";
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        final String sql = "SELECT to_char(sysdate,'yyyymmddHHMMSS')||lpad((substr(max(RECV_NO), 5, 10)+1), 2, '0') FROM UM_SUPPLIER_ENTER_MAST_HIST";
        try {
            pstmt = con.prepareStatement(sql);
            rest = pstmt.executeQuery();
            if (!rest.next()) {
                throw new Exception("Không tồn tại mã số tiếp nhận");
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
        return result;
    }
    
    public OneRowEntity selectDetail(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, to_char(COMMENCEMENT_DT, 'dd/MM/yyyy') COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, to_char(REG_DT, 'dd/MM/yyyy') REG_DT, UPDATE_DT, DOC_NO, to_char(DOC_CREAT_DT, 'dd/mm/yy') DOC_CREAT_DT, VER");
            sql.append(" from UM_SUPPLIER_ENTER_MAST_HIST");
            sql.append(" where BIZ_REG_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { BizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + BizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public OneRowEntity selectNewest(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, to_char(COMMENCEMENT_DT, 'dd/MM/yyyy') COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, to_char(REG_DT, 'dd/MM/yyyy') REG_DT, UPDATE_DT, DOC_NO, to_char(DOC_CREAT_DT, 'dd/mm/yy') DOC_CREAT_DT, VER");
            sql.append(" from UM_SUPPLIER_ENTER_MAST_HIST t");
            sql.append(" where BIZ_REG_NO = ?  ");
            sql.append(" and t1.VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO= t.BIZ_REG_NO AND IDENT_NO=t.IDENT_NO)");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { BizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + BizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public void deleteCANewest(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE UM_REC_PUB_INSTITU_CERT_HIST a ");
            sql.append(" where INSTITU_CD=?   ");
            sql.append(" AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.INSTITU_CD ) ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, BizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + BizRegNo + ") : " + e.toString());
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
    
    public void deleteNewest(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE UM_SUPPLIER_ENTER_MAST_HIST a ");
            sql.append(" where BIZ_REG_NO=?  ");
            sql.append(" AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = a.BIZ_REG_NO ) ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, BizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + BizRegNo + ") : " + e.toString());
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
    
    public void save(final String bizRegNo, final String bizName, final String bizEnName, final String commDate, final String addr, final String phoneNo, final String employeeCount, final String fax, final String nationality, final String area, final String website, final String capital, final String bizCls, final String bizCls1, final String permitBranch, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_SUPPLIER_ENTER_MAST_HIST INSERTINTO USEMN.UM_SUPPLIER_ENTER_MAST_HIST  (BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS, MAST_INDUSTRY_CD_STD, CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL, EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECAIL_GOODS_YN, REG_VALID_DT, REG_DT, UPDATE_DT, REPR_BIZ_APPROVE_YN, MANAGER_ID, PERMIT_BRANCH, TRANS_CONFIRM, CONFIRM_DT, INITIAL_INSTITU_NM, BID_ATTEND_QUALIFY_YN, GROUP_CD, TEST_OPTION_YN, CONTR_RELA_YN, HEADQUARTER_YN, DOC_NO, DOC_CREAT_DT, RECV_NO, HIDDEN_YN, TEMP_NAME, VER ) ");
            sql.append(" values (?, ?, ?, to_date(?,'dd/MM/yyyy'), ?, ?, to_number(?), ?, ?, ?, ?, to_number(?), ?, ?, SYSDATE, ?, SYSDATE, ");
            sql.append(" (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?)  ) ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizName);
            pstmt.setString(3, bizEnName);
            pstmt.setString(4, commDate);
            pstmt.setString(5, addr);
            pstmt.setString(6, phoneNo);
            pstmt.setString(7, employeeCount);
            pstmt.setString(8, fax);
            pstmt.setString(9, nationality);
            pstmt.setString(10, area);
            pstmt.setString(11, website);
            pstmt.setString(12, capital);
            pstmt.setString(13, bizCls);
            pstmt.setString(14, bizCls1);
            pstmt.setString(15, permitBranch);
            pstmt.setString(16, bizRegNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
        }
        catch (Exception e) {
            Log.errors(String.valueOf(this.getClass().getName()) + ".save(" + bizRegNo + ", " + bizName + ", " + commDate + ") : " + e.toString());
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
    
    public void save(final String bizRegNo, final String bizName, final String bizEnName, final String commDate, final String addr, final String phoneNo, final String employeeCount, final String fax, final String nationality, final String website, final String capital, final String bizCls, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERTINTO USEMN.UM_SUPPLIER_ENTER_MAST_HIST ");
            sql.append(" (BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, BIZ_CLS, CAPITAL, EMPLOYEE_COUNT, ADDR, PHONE_NO, REG_DT, UPDATE_DT, VER )  ");
            sql.append(" VALUES ( ?,?,?,?,?,?,?,?,?,?,?,SYSDATE,(SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = '" + bizRegNo + "') ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, nationality);
            pstmt.setString(3, bizName);
            pstmt.setString(4, bizEnName);
            pstmt.setString(5, commDate);
            pstmt.setString(6, bizCls);
            pstmt.setString(7, capital);
            pstmt.setString(8, employeeCount);
            pstmt.setString(9, addr);
            pstmt.setString(10, phoneNo);
            pstmt.setString(11, "");
            Log.debug(String.valueOf(bizName) + "_" + bizEnName + "_" + commDate + "_" + addr + "_" + phoneNo + "_" + employeeCount + "_" + fax + "_" + nationality + "_" + website + "_" + capital + "_" + bizCls + "_" + bizRegNo + "_");
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(getStackTrace((Throwable)e));
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
    
    public void modify(final String bizRegNo, final String bizName, final String bizEnName, final String commDate, final String addr, final String phoneNo, final String employeeCount, final String fax, final String nationality, final String website, final String capital, final String bizCls, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_SUPPLIER_ENTER_MAST_HIST set BIZ_NM=?, BIZ_EN_NM=?, COMMENCEMENT_DT = to_date(?,'dd/MM/yyyy'), ADDR= ?, PHONE_NO=?, EMPLOYEE_COUNT=to_number(?), FAX=?, NATIONALITY=?, WEBSITE=?, CAPITAL=to_number(?), BIZ_CLS=? , ");
            sql.append(" VER=(SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), '1'+MAX(VER) )  FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?  ) ");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizName);
            pstmt.setString(2, bizEnName);
            pstmt.setString(3, commDate);
            pstmt.setString(4, addr);
            pstmt.setString(5, phoneNo);
            pstmt.setString(6, employeeCount);
            pstmt.setString(7, fax);
            pstmt.setString(8, nationality);
            pstmt.setString(9, website);
            pstmt.setString(10, capital);
            pstmt.setString(11, bizCls);
            pstmt.setString(12, bizRegNo);
            pstmt.setString(13, bizRegNo);
            Log.debug(String.valueOf(bizName) + "_" + bizEnName + "_" + commDate + "_" + addr + "_" + phoneNo + "_" + employeeCount + "_" + fax + "_" + nationality + "_" + website + "_" + capital + "_" + bizCls + "_" + bizRegNo + "_");
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(getStackTrace((Throwable)e));
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
    
    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
    
    public void save(final String bizRegNo, final String userId, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_SUPPLIER_ENTER_MAST_HIST (BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, REG_DT, PERMIT_BRANCH, MANAGER_ID, UPDATE_DT, VER) ");
            sql.append(" SELECT BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, REG_DT, PERMIT_BRANCH, '" + userId + "',  SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?) FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO= ?  ");
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
            sql.append(" INSERT INTO UM_SUPPLIER_ENTER_MAST_HIST (BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, REG_DT, PERMIT_BRANCH, MANAGER_ID, UPDATE_DT, VER, USER_ID, SUB_USER_ID) ");
            sql.append(" SELECT BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, REG_DT, PERMIT_BRANCH, '" + userId + "',  SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?), '" + userId + "' USER_ID, '" + sUId + "' SUB_USER_ID FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO= ?  ");
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
    
    public void save(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_SUPPLIER_ENTER_MAST_HIST (BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, BIZ_CLS_YEAR, ZIP_CD,  COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, REG_DT, PERMIT_BRANCH, MANAGER_ID, UPDATE_DT, VER) ");
            sql.append(" SELECT BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, BIZ_CLS_YEAR , ZIP_CD,  COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, REG_DT, PERMIT_BRANCH, MANAGER_ID,  SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?) FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO= ?  ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(getStackTrace((Throwable)e));
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
    
    public void save(final String bizRegNo, final boolean approveYn, final String approveId, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_SUPPLIER_ENTER_MAST_HIST (BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, REG_DT, PERMIT_BRANCH, MANAGER_ID, UPDATE_DT, VER, APPROVE_YN, APPROVE_DT, APPROVE_ID) ");
            sql.append(" SELECT BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, REG_DT, PERMIT_BRANCH, MANAGER_ID,  SYSDATE, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00') FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?) ,     ");
            sql.append(" '" + (approveYn ? "Y" : "N") + "',  ");
            sql.append(" SYSDATE, ? ");
            sql.append("  FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO= ?   ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, approveId);
            pstmt.setString(3, bizRegNo);
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
    
    public void saveCA(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_REC_PUB_INSTITU_CERT_HIST (RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX, CHRGR_MN, CHRGR_DEPART, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID, NICKNAME, RECV_DT, RECV_CONTENT, CERT_INSTITU, WEBSITE, REG_YN, REJECTED_RSON, APPROVE_REQ_CD, PERMIT_BRANCH, CERT_NM, ISSUE_YN, PERMIT_CD, PERMIT_USER_ID, PERMIT_DT, PASSWORD_MOD_YN, PASSWORD_MOD_ID, PASSWORD, SMS_RECV_YN, CERT_CLS, TEMP_NAME, VER) ");
            sql.append(" SELECT   RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX, CHRGR_MN, CHRGR_DEPART, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID, NICKNAME, RECV_DT, RECV_CONTENT, CERT_INSTITU, WEBSITE, REG_YN, REJECTED_RSON, APPROVE_REQ_CD, PERMIT_BRANCH, CERT_NM, ISSUE_YN, PERMIT_CD, PERMIT_USER_ID, PERMIT_DT, PASSWORD_MOD_YN, PASSWORD_MOD_ID, PASSWORD, SMS_RECV_YN, CERT_CLS, TEMP_NAME, (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO= ? )  ");
            sql.append("  FROM UM_REC_PUB_INSTITU_CERT a WHERE a.INSTITU_CD= ?   ");
            sql.append("  AND RECV_NO=(SELECT MAX(RECV_NO) FROM UM_REC_PUB_INSTITU_CERT WHERE INSTITU_CD= a.INSTITU_CD  ) ");
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
    
    public void saveCAHist(final String receiptNo, final String sUId, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_REC_PUB_INSTITU_CERT_HIST (RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX, CHRGR_MN, CHRGR_DEPART, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID, NICKNAME, RECV_DT, RECV_CONTENT, CERT_INSTITU, WEBSITE, REG_YN, REJECTED_RSON, APPROVE_REQ_CD, PERMIT_BRANCH, CERT_NM, ISSUE_YN, PERMIT_CD, PERMIT_USER_ID, PERMIT_DT, PASSWORD_MOD_YN, PASSWORD_MOD_ID, PASSWORD, SMS_RECV_YN, CERT_CLS, TEMP_NAME, VER, SUB_USER_ID) ");
            sql.append(" SELECT   RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX, CHRGR_MN, CHRGR_DEPART, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID, NICKNAME, RECV_DT, RECV_CONTENT, CERT_INSTITU, WEBSITE, REG_YN, REJECTED_RSON, APPROVE_REQ_CD, PERMIT_BRANCH, CERT_NM, ISSUE_YN, PERMIT_CD, PERMIT_USER_ID, PERMIT_DT, PASSWORD_MOD_YN, PASSWORD_MOD_ID, PASSWORD, SMS_RECV_YN, CERT_CLS, TEMP_NAME, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00') FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE RECV_NO= ? ), '" + sUId + "'  ");
            sql.append("  FROM UM_REC_PUB_INSTITU_CERT a WHERE    ");
            sql.append("  RECV_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, receiptNo);
            pstmt.setString(2, receiptNo);
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
    
    public void MasterUpdateTemp(final String saupNo, final String eSangho, final int employeeNo, final String tel, final String fax, final String homepage, final String procID, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = "INSERT INTO UM_SUPPLIER_ENTER_MAST_HIST ( BIZ_REG_NO, UPDATE_DT, NATIONALITY, BIZ_NM, BIZ_EN_NM,  COMMENCEMENT_DT, ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS, MAST_INDUSTRY_CD_STD, CORP_REG_NO,  BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL, EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD,  AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX, WEBSITE,  REG_VALID_DT, REG_DT,  REPR_BIZ_APPROVE_YN, MANAGER_ID,  PERMIT_BRANCH, VER, APPROVE_YN )   SELECT BIZ_REG_NO, SYSDATE, NATIONALITY, BIZ_NM, ? , COMMENCEMENT_DT,  ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS, MAST_INDUSTRY_CD_STD, CORP_REG_NO, BIZ_CLS1, BIZ_CLS2,  BIZ_CLS_YEAR, CAPITAL, ?, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR,  DETAIL_ADDR, ? , ? , ? ,  REG_VALID_DT, REG_DT, REPR_BIZ_APPROVE_YN, ? ,  PERMIT_BRANCH,  (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?),  'N'  FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO = ?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, eSangho);
            psmt.setInt(2, employeeNo);
            psmt.setString(3, tel);
            psmt.setString(4, fax);
            psmt.setString(5, homepage);
            psmt.setString(6, procID);
            psmt.setString(7, saupNo);
            psmt.setString(8, saupNo);
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
                catch (Exception e) {
                    Log.errors(this, e, "");
                }
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception e) {
                Log.errors(this, e, "");
            }
        }
    }
    
    public void MasterUpdateCTSInfoTemp(final String saupNo, final String ident, final String identNo, final Connection con) throws Exception {
        String query = null;
        PreparedStatement psmt = null;
        try {
            if (con == null) {
                throw new Exception("Connection is null");
            }
            query = "INSERT INTO UM_REC_PUB_INSTITU_CERT_HIST ( RECV_NO, INSTITU_CD, INSTITU_FULL_NM,  INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX,  CHRGR_MN, CHRGR_DEPART, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID,  NICKNAME, RECV_DT, RECV_CONTENT, CERT_INSTITU, WEBSITE, REG_YN, REJECTED_RSON, APPROVE_REQ_CD,  PERMIT_BRANCH, CERT_NM, ISSUE_YN, PERMIT_CD, PERMIT_USER_ID, PERMIT_DT,  PASSWORD_MOD_YN, PASSWORD_MOD_ID, PASSWORD, SMS_RECV_YN, CERT_CLS, TEMP_NAME, VER )   SELECT RECV_NO, INSTITU_CD, INSTITU_FULL_NM,  INSTITU_EN_NM, ?, ?, ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX, CHRGR_MN, CHRGR_DEPART, CHRGR_IDENT_NO,  CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID, NICKNAME, RECV_DT, RECV_CONTENT,  CERT_INSTITU, WEBSITE, REG_YN, REJECTED_RSON, APPROVE_REQ_CD, PERMIT_BRANCH, CERT_NM,  ISSUE_YN, PERMIT_CD, PERMIT_USER_ID, PERMIT_DT, PASSWORD_MOD_YN, PASSWORD_MOD_ID, PASSWORD, SMS_RECV_YN, CERT_CLS, TEMP_NAME,  (SELECT NVL(MAX(VER), 0) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ? )   FROM UM_REC_PUB_INSTITU_CERT a WHERE a.INSTITU_CD = ? AND RECV_NO = (SELECT MAX(RECV_NO) FROM UM_REC_PUB_INSTITU_CERT where INSTITU_CD=a.INSTITU_CD )";
            psmt = con.prepareStatement(query);
            psmt.setString(1, ident);
            psmt.setString(2, identNo);
            psmt.setString(3, saupNo);
            psmt.setString(4, saupNo);
            Log.errors(String.valueOf(ident) + " - " + identNo + " - " + saupNo);
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
                catch (Exception e) {
                    Log.errors(this, e, "");
                }
            }
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception e) {
                Log.errors(this, e, "");
            }
        }
    }
    
    public int getCountList(final String upcheNm, final String Start, final String End, final String instituCd, final String status) {
        Connection con = null;
        Trx trx = null;
        final OneRowEntity ett = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final Vector params = new Vector();
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        String sql = " SELECT  COUNT(*) FROM  (\t\t\tSELECT DISTINCT a.BIZ_REG_NO, a.BIZ_NM, a.ZIP_CD, a.UPDATE_DT, a.MANAGER_ID, a.APPROVE_YN, ROWNUM N \t\t\tFROM  UM_SUPPLIER_ENTER_MAST_HIST a\tWHERE VER = (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO=a.BIZ_REG_NO)";
        if (!instituCd.equals("") && instituCd != null) {
            sql = String.valueOf(sql) + " AND lower(a.BIZ_REG_NO) like  ? ";
            params.add("%" + instituCd.toLowerCase().trim() + "%");
        }
        if (upcheNm.length() >= 1) {
            sql = String.valueOf(sql) + " AND\t\tlower(a.BIZ_NM) like ?   \t\t\n";
            params.add("%" + upcheNm.toLowerCase().trim() + "%");
        }
        if (Start != "" && End != "") {
            sql = String.valueOf(sql) + " AND\t\tUPDATE_DT BETWEEN TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
            sql = String.valueOf(sql) + " AND TO_DATE( ? ,   'DD/MM/YYYY HH24:MI:SS')\t\n";
            params.add(StartTime);
            params.add(EndTime);
        }
        else if (Start != "" && End == "") {
            sql = String.valueOf(sql) + " AND\t\tUPDATE_DT > TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
            params.add(StartTime);
        }
        else if (Start == "" && End != "") {
            sql = String.valueOf(sql) + " AND\t\tUPDATE_DT < TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
            params.add(EndTime);
        }
        if ("Y".equals(status) || "N".equals(status)) {
            sql = String.valueOf(sql) + " AND\t\ta.APPROVE_YN = ?   \n";
            params.add(status);
        }
        sql = String.valueOf(sql) + " ) ";
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); ++i) {
                psmt.setString(i + 1, params.get(i).toString());
            }
            rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, new StringBuffer().append(sqle.toString()).toString());
        }
        catch (Exception exc) {
            Log.errors(this, exc, new StringBuffer().append(exc.toString()).toString());
        }
        finally {
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex) {}
            }
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex2) {}
        }
        return 0;
    }
    
    public OneRowEntity[] getList(final int page_no, final String upcheNm, final String Start, final String End, final String instituCd, final String status) {
        Connection con = null;
        Trx trx = null;
        OneRowEntity ett = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final Vector vec = new Vector();
        final Vector params = new Vector();
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        String sql = " SELECT  BIZ_REG_NO, BIZ_NM, ZIP_CD, UPDATE_DT, MANAGER_ID, APPROVE_YN, N FROM  (\t\t\tSELECT DISTINCT a.BIZ_REG_NO, a.BIZ_NM, a.ZIP_CD, a.UPDATE_DT, a.MANAGER_ID, a.APPROVE_YN, ROWNUM N \t\t\tFROM  UM_SUPPLIER_ENTER_MAST_HIST a\tWHERE VER = (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO=a.BIZ_REG_NO)";
        if (!instituCd.equals("") && instituCd != null) {
            sql = String.valueOf(sql) + " AND lower(a.BIZ_REG_NO) like  ? ";
            params.add("%" + instituCd.toLowerCase().trim() + "%");
        }
        if (upcheNm.length() >= 1) {
            sql = String.valueOf(sql) + " AND\t\tlower(a.BIZ_NM) like ?   \t\t\n";
            params.add("%" + upcheNm.toLowerCase().trim() + "%");
        }
        if (Start != "" && End != "") {
            sql = String.valueOf(sql) + " AND\t\tUPDATE_DT BETWEEN TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
            sql = String.valueOf(sql) + " AND TO_DATE( ? ,   'DD/MM/YYYY HH24:MI:SS')\t\n";
            params.add(StartTime);
            params.add(EndTime);
        }
        else if (Start != "" && End == "") {
            sql = String.valueOf(sql) + " AND\t\tUPDATE_DT > TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
            params.add(StartTime);
        }
        else if (Start == "" && End != "") {
            sql = String.valueOf(sql) + " AND\t\tUPDATE_DT < TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
            params.add(EndTime);
        }
        if ("Y".equals(status) || "N".equals(status)) {
            sql = String.valueOf(sql) + " AND\t\ta.APPROVE_YN = ?   \n";
            params.add(status);
        }
        sql = String.valueOf(sql) + " ORDER BY UPDATE_DT DESC ";
        sql = String.valueOf(sql) + " )  ";
        sql = String.valueOf(sql) + "WHERE N BETWEEN (((" + page_no + " - 1) * 10)+1) AND (" + page_no + " * 10) ";
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); ++i) {
                psmt.setString(i + 1, params.get(i).toString());
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                ett = new OneRowEntity();
                ett.dataArraySetting(6);
                ett.data[0] = CommUtil.retSpace(rs.getString(1));
                ett.data[1] = CommUtil.retSpace(rs.getString(2));
                ett.data[2] = CommUtil.retSpace(rs.getString(3));
                ett.data[3] = CommUtil.retSpace(rs.getString(4));
                ett.data[4] = CommUtil.retSpace(rs.getString(5));
                ett.data[5] = CommUtil.retSpace(rs.getString(6));
                vec.add(ett);
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, new StringBuffer().append(sqle.toString()).toString());
        }
        catch (Exception exc) {
            Log.errors(this, exc, new StringBuffer().append(exc.toString()).toString());
        }
        finally {
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex) {}
            }
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex2) {}
        }
        final OneRowEntity[] ettList = new OneRowEntity[vec.size()];
        vec.copyInto(ettList);
        return ettList;
    }
    
    public static String getMaxVer(final String tableName, final String codeType, final String masterCode, final Connection conn) throws Exception {
        final String sql = " SELECT MAX(VER) FROM " + tableName + " WHERE " + codeType + "= ? ";
        PreparedStatement pstmt = null;
        ResultSet res = null;
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, masterCode);
        res = pstmt.executeQuery();
        if (res.next()) {
            return res.getString(1);
        }
        return "00";
    }
    
    public void insertUserHist_By_Admin(final Connection conn, final String USER_ID, final String USER_CHANGE, final String mast_cd) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO USEMN.UM_USER_HIST (USER_ID,INPUT_DT,CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO ,CHRGR_MOBILE ,CHRGR_FAX ,CHRGR_EMAIL,MAST_CD ,VER)  SELECT USER_ID , SYSDATE, CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO,  CHRGR_MOBILE ,CHRGR_FAX ,CHRGR_EMAIL,MAST_CD ,  (SELECT MAX(VER)  FROM USEMN.UM_USER_HIST WHERE MAST_CD = ? )   FROM USEMN.UM_USER WHERE USER_ID = ? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, USER_CHANGE);
            pstmt.setString(2, mast_cd);
            pstmt.setString(3, USER_ID);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
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
    
    public void updateUserTemp(final Connection conn, final String USER_ID, final String USER_CHRGR_NM, final String USER_CHRGR_DEPART, final String USER_CHRGR_PHONE_NO, final String USER_CHRGR_MOBILE, final String USER_CHRGR_FAX, final String USER_CHRGR_EMAIL, final String MAST_CD) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO UM_USER_HIST (USER_ID,CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO ,CHRGR_MOBILE ,CHRGR_FAX ,CHRGR_EMAIL,MAST_CD, INPUT_DT, VER )  VALUES(?,?,?,?,?,?,?,?, SYSDATE, (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?))  ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, USER_ID);
            pstmt.setString(2, USER_CHRGR_NM);
            pstmt.setString(3, USER_CHRGR_DEPART);
            pstmt.setString(4, USER_CHRGR_PHONE_NO);
            pstmt.setString(5, USER_CHRGR_MOBILE);
            pstmt.setString(6, USER_CHRGR_FAX);
            pstmt.setString(7, USER_CHRGR_EMAIL);
            pstmt.setString(8, MAST_CD);
            pstmt.setString(9, MAST_CD);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
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
    
    public void insertUserHist(final String MAST_CD, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO UM_USER_HIST (USER_ID,CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO,  CHRGR_MOBILE ,CHRGR_FAX ,CHRGR_EMAIL,MAST_CD, INPUT_DT, VER )  SELECT USER_ID,CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO,  CHRGR_MOBILE ,CHRGR_FAX ,CHRGR_EMAIL,MAST_CD, SYSDATE,  (SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ?) FROM UM_USER WHERE MAST_CD= ? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, MAST_CD);
            pstmt.setString(2, MAST_CD);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
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
    
    public void updateUSER(final Connection conn, final String USER_ID, final String USER_CHRGR_NM, final String USER_CHRGR_DEPART, final String USER_CHRGR_PHONE_NO, final String USER_CHRGR_MOBILE, final String USER_CHRGR_FAX, final String USER_CHRGR_EMAIL) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " UPDATE USEMN.UM_USER SET CHRGR_NM = ? ,CHRGR_DEPART = ? ,CHRGR_PHONE_NO = ? ,CHRGR_MOBILE = ? ,CHRGR_FAX = ? ,CHRGR_EMAIL =?   WHERE USER_ID = ?  ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, USER_CHRGR_NM);
            pstmt.setString(2, USER_CHRGR_DEPART);
            pstmt.setString(3, USER_CHRGR_PHONE_NO);
            pstmt.setString(4, USER_CHRGR_MOBILE);
            pstmt.setString(5, USER_CHRGR_FAX);
            pstmt.setString(6, USER_CHRGR_EMAIL);
            pstmt.setString(7, USER_ID);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
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
    
    public void deleteUserNewest(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE UM_USER_HIST ");
            sql.append(" where MAST_CD= ?  ");
            sql.append(" AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ? ) ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, BizRegNo);
            pstmt.setString(2, BizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + BizRegNo + ") : " + e.toString());
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
    
    public void updateUSER(final CommEntity[] userEntity, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        if (userEntity == null) {
            throw new Exception("Không tìm thấy thông tin người phụ trách dự thầu");
        }
        final String sql = " UPDATE USEMN.UM_USER SET CHRGR_NM = ? ,CHRGR_DEPART = ? ,CHRGR_PHONE_NO = ? ,CHRGR_MOBILE = ? ,CHRGR_FAX = ? ,CHRGR_EMAIL =?   WHERE USER_ID = ?  ";
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < userEntity.length; ++i) {
                pstmt.setString(1, userEntity[i].data[0]);
                pstmt.setString(2, userEntity[i].data[1]);
                pstmt.setString(3, userEntity[i].data[2]);
                pstmt.setString(4, userEntity[i].data[3]);
                pstmt.setString(5, userEntity[i].data[4]);
                pstmt.setString(6, userEntity[i].data[5]);
                pstmt.setString(7, userEntity[i].data[6]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
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
    
    public void updateCA(final String masterCode, final UM_ADJ_GovuA020b caInfo, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        if (caInfo == null) {
            throw new Exception("Không tìm thấy thông tin đăng ký CTS");
        }
        final String sql = " UPDATE UM_REC_PUB_INSTITU_CERT SET REPR_NM = ? ,REPR_IDENT_NO = ?  WHERE INSTITU_CD = ? AND RECV_NO = (SELECT MAX(RECV_NO) FROM UM_REC_PUB_INSTITU_CERT WHERE INSTITU_CD= ? )  ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, caInfo.getIDENT());
            pstmt.setString(2, caInfo.getIDENTJUMIN());
            pstmt.setString(3, masterCode);
            pstmt.setString(4, masterCode);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
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
    
    public CommEntity[] getListUser_NM(final String code) throws Exception {
        Trx trx = null;
        Connection conn = null;
        final String[] parameter = { code };
        final String sql = "SELECT  CHRGR_NM , CHRGR_DEPART, CHRGR_PHONE_NO ,CHRGR_MOBILE, CHRGR_FAX ,CHRGR_EMAIL, USER_ID \tFROM  UM_USER_HIST  a        WHERE   MAST_CD= ? AND  VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO=a.MAST_CD)        ";
        try {
            trx = new Trx(this);
            conn = trx.getConnection();
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exf) {
            Log.errors(this, exf, "");
            throw exf;
        }
        finally {
            if (trx != null) {
                trx.close();
            }
        }
    }
    
    public UM_ADJ_GovuA020b select_CTSInfo(final String instituCd) throws Exception, SQLException {
        if (instituCd == null || instituCd.equals("")) {
            Log.debug("instituCd í null ");
            return new UM_ADJ_GovuA020b();
        }
        Connection con = null;
        Trx trx = null;
        final UM_ADJ_GovuA020b ettcode = new UM_ADJ_GovuA020b();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT  REPR_NM, REPR_IDENT_NO ").append("   FROM  UM_REC_PUB_INSTITU_CERT_HIST a ").append(" WHERE  INSTITU_CD= ? AND  VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO=a.INSTITU_CD)      ");
            sql = sb.toString();
            Log.debug(sql);
            psmt = con.prepareStatement(sql);
            psmt.setString(1, instituCd);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ettcode.setIDENT(rs.getString(1));
                ettcode.setIDENTJUMIN(rs.getString(2));
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "UM_RAB_GovuA010p.select_goma block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.errors(this, exc, "UM_RAB_GovuA010p.select_goma block Exception : " + exc.toString());
            throw exc;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ettcode;
    }
}
