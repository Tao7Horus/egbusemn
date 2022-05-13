// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_ADJ_GovuA020b;
import signgate.crypto.util.Debug;
import common.CommEntity;
import entity.UM_ADE_HistA050b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.util.CommUtil;
import common.Trx;
import java.util.Vector;
import common.OneRowEntity;

public class UM_ADB_GONGHISTORY
{
    public OneRowEntity[] getList(final int page_no, final String upcheNm, final String Start, final String End, final String area, final String instituCd, final String status) {
        Connection con = null;
        Trx trx = null;
        OneRowEntity ett = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final Vector vec = new Vector();
        final Vector params = new Vector();
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        String sql = " SELECT  INSTITU_CD, INSTITU_FULL_NM, ZIP_CD, MOD_DT, MANAGER_ID, APPROVE_YN, UPDATE_DT, N FROM  (\t\t\tSELECT DISTINCT a.INSTITU_CD, a.INSTITU_FULL_NM, a.ZIP_CD, TO_CHAR(MOD_DT, 'DD-MM-YYYY HH24:MI:SS') MOD_DT, a.MANAGER_ID, a.APPROVE_YN,  (SELECT TO_CHAR(UPDATE_DT, 'DD-MM-YYYY HH24:MI:SS') UPDATE_DT FROM UM_PUB_INSTITU_MAST WHERE a.INSTITU_CD=INSTITU_CD) UPDATE_DT, ROWNUM N \t\t\tFROM  UM_PUB_INSTITU_HIST a\tWHERE VER = (SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD=a.INSTITU_CD)";
        if (!instituCd.equals("") && instituCd != null) {
            sql = String.valueOf(sql) + " AND lower(a.INSTITU_CD) like  ? ";
            params.add("%" + instituCd.toLowerCase().trim() + "%");
        }
        if (upcheNm.length() >= 1) {
            sql = String.valueOf(sql) + " AND\t\tlower(a.INSTITU_FULL_NM) like ?   \t\t\n";
            params.add("%" + upcheNm.toLowerCase().trim() + "%");
        }
        if (Start != "" && End != "") {
            sql = String.valueOf(sql) + " AND\t\tMOD_DT BETWEEN TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
            sql = String.valueOf(sql) + " AND TO_DATE( ? ,   'DD/MM/YYYY HH24:MI:SS')\t\n";
            params.add(StartTime);
            params.add(EndTime);
        }
        else if (Start != "" && End == "") {
            sql = String.valueOf(sql) + " AND\t\tMOD_DT > TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
            params.add(StartTime);
        }
        else if (Start == "" && End != "") {
            sql = String.valueOf(sql) + " AND\t\tMOD_DT < TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
            params.add(EndTime);
        }
        if ("Y".equals(status) || "N".equals(status)) {
            sql = String.valueOf(sql) + " AND\t\ta.APPROVE_YN = ?   \n";
            params.add(status);
        }
        sql = String.valueOf(sql) + " ORDER BY MOD_DT ASC ";
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
                ett.dataArraySetting(7);
                ett.data[0] = CommUtil.retSpace(rs.getString(1));
                ett.data[1] = CommUtil.retSpace(rs.getString(2));
                ett.data[2] = CommUtil.retSpace(rs.getString(3));
                ett.data[3] = CommUtil.retSpace(rs.getString(4));
                ett.data[4] = CommUtil.retSpace(rs.getString(5));
                ett.data[5] = CommUtil.retSpace(rs.getString(6));
                ett.data[6] = CommUtil.retSpace(rs.getString(7));
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
    
    public void delete(final String code) throws Exception {
        Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        final String sql = "DELETE FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD= ?  AND VER=(SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ?) ";
        final String sql2 = "DELETE FROM UM_PUB_INSTITU_ACCT_CD_HIST WHERE INSTITU_CD= ?  AND VER=(SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ?) ";
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, code);
            psmt.setString(2, code);
            psmt.executeUpdate();
            psmt.clearParameters();
            psmt = con.prepareStatement(sql2);
            psmt.setString(1, code);
            psmt.setString(2, code);
            psmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public void deleteAcct(final String code) throws Exception {
        Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        final String sql2 = "DELETE FROM UM_PUB_INSTITU_ACCT_CD_HIST WHERE INSTITU_CD= ?  AND VER=(SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ?) ";
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql2);
            psmt.setString(1, code);
            psmt.setString(2, code);
            psmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public int getCountList(final String upcheNm, final String Start, final String End, final String area, final String instituCd, final String status) {
        Connection con = null;
        Trx trx = null;
        final OneRowEntity ett = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final Vector params = new Vector();
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        String sql = " SELECT  COUNT(*) FROM  (\t\t\tSELECT DISTINCT a.INSTITU_CD, a.INSTITU_FULL_NM, a.ZIP_CD, a.MOD_DT, a.MANAGER_ID, a.APPROVE_YN, ROWNUM N \t\t\tFROM  UM_PUB_INSTITU_HIST a\tWHERE VER = (SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD=a.INSTITU_CD)";
        if (!instituCd.equals("") && instituCd != null) {
            sql = String.valueOf(sql) + " AND lower(a.INSTITU_CD) like  ? ";
            params.add("%" + instituCd.toLowerCase().trim() + "%");
        }
        if (upcheNm.length() >= 1) {
            sql = String.valueOf(sql) + " AND\t\tlower(a.INSTITU_FULL_NM) like ?   \t\t\n";
            params.add("%" + upcheNm.toLowerCase().trim() + "%");
        }
        if (Start != "" && End != "") {
            sql = String.valueOf(sql) + " AND\t\tMOD_DT BETWEEN TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
            sql = String.valueOf(sql) + " AND TO_DATE( ? ,   'DD/MM/YYYY HH24:MI:SS')\t\n";
            params.add(StartTime);
            params.add(EndTime);
        }
        else if (Start != "" && End == "") {
            sql = String.valueOf(sql) + " AND\t\tMOD_DT > TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
            params.add(StartTime);
        }
        else if (Start == "" && End != "") {
            sql = String.valueOf(sql) + " AND\t\tMOD_DT < TO_DATE( ? , 'DD/MM/YYYY HH24:MI:SS')     \n";
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
    
    public void insertFullHist(final Connection conn, final String g2bCode) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO UM_PUB_INSTITU_HIST(SPECIAL_MNG_INSTITU, INSTITU_FULL_NM, CHRGR_NM, CHRGR_DEPART,CHRGR_PHONE_NO,CHRGR_FAX,CHRGR_EMAIL, ZIP_CD, ADDR, DETAIL_ADDR, INSTITU_PHONE_NO,FAX,GOODS_MNG_NM, REG_DT, INSTITU_CD, INSTITU_SHORT_NM, INSTITU_EN_NM, BIZ_REG_NO,WEBSITE, INSTITU_TYPE, MOBILE,RECV_NO, MANAGER_ID, INSTITU_CLS, AREA_CD, LOCAL_INSTITU,E_AUTHENT_YN, DELETE_YN, INSTITU_CONDITION, CREDITOR_NM, PERMIT_BRANCH,  GROUP_NO, DEPARTMENT_NO, TEST_OPTION_YN, MOD_DT, VER )  SELECT SPECIAL_MNG_AGENT, INSTITU_FULL_NM, CHRGR_NM, CHRGR_DEPART,CHRGR_PHONE_NO,CHRGR_FAX,CHRGR_EMAIL, ZIP_CD, ADDR, DETAIL_ADDR, INSTITU_PHONE_NO,FAX,GOODS_MNG_NM, REG_DT, INSTITU_CD, INSTITU_SHORT_NM, INSTITU_EN_NM, BIZ_REG_NO,WEBSITE, INSTITU_TYPE, MOBILE,RECV_NO, MANAGER_ID, INSTITU_CLS, AREA_CD, LOCAL_INSTITU,E_AUTHENT_YN, DELETE_YN, INSTITU_CONDITION, CREDITOR_NM, PERMIT_BRANCH,  GROUP_NO, DEPARTMENT_NO, TEST_OPTION_YN, SYSDATE,  (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')   FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ? ) FROM  UM_PUB_INSTITU_MAST WHERE  INSTITU_CD =? ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, g2bCode);
            pstmt.setString(2, g2bCode);
            pstmt.executeUpdate();
        }
        catch (Exception ex) {
            Log.errors(this, ex, "");
            throw new Exception(ex.getMessage());
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
    
    public void insertAcctHist(final String mCode, final String FundCode, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        final String sql1 = " INSERT INTO UM_PUB_INSTITU_ACCT_CD_HIST (INSTITU_CD, INSTITU_ACCT_CD, UPDATE_DT, VER  ) values(?,  ?,  SYSDATE, (SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ? ) )";
        try {
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, mCode);
            pstmt.setString(2, FundCode);
            pstmt.setString(3, mCode);
            pstmt.executeUpdate();
            pstmt.clearParameters();
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
    
    public void insertAcctHist(final String g2bCode, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO UM_PUB_INSTITU_ACCT_CD_HIST (INSTITU_CD, INSTITU_ACCT_CD, UPDATE_DT, VER)  SELECT INSTITU_CD, INSTITU_ACCT_CD, SYSDATE, (SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ? ) FROM UM_PUB_INSTITU_ACCT_CD WHERE INSTITU_CD= ?  ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, g2bCode);
            pstmt.setString(2, g2bCode);
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
    
    public UM_ADE_HistA050b getDetail(final String code) {
        Trx trx = null;
        Connection con = null;
        UM_ADE_HistA050b ettall = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "SELECT a.INSTITU_CD, a.INSTITU_SHORT_NM, a.INSTITU_EN_NM, a.BIZ_REG_NO,        a.INSTITU_CONDITION,             a.CHRGR_NM,        a.CHRGR_DEPART,    a.CHRGR_PHONE_NO,        a.CHRGR_FAX, a.CHRGR_EMAIL,  a.CREDITOR_NM,        a.INSTITU_CLS,        a.ZIP_CD,              a.ADDR,           a.DETAIL_ADDR,      a.MOBILE,        a.FAX,        a.GOODS_MNG_NM,          a.WEBSITE,   a.MANAGER_ID,              a.MOD_DT,        a.AREA_CD,        a.INSTITU_TYPE, a.PERMIT_BRANCH, a. INSTITU_PHONE_NO, a.APPROVE_YN,   \t\t\ta.DEPARTMENT_NO, a.GROUP_NO, a.MANAGER_ID   FROM UM_PUB_INSTITU_HIST a  WHERE a.INSTITU_CD = ?     AND a.VER=(SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE a.VER=VER AND INSTITU_CD= a.INSTITU_CD ) ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, code);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ettall = new UM_ADE_HistA050b();
                ettall.setg2bCode(rs.getString(1));
                ettall.setgoNameShort(rs.getString(2));
                ettall.setgoNameEn(rs.getString(3));
                ettall.setsaupNo(rs.getString(4));
                ettall.setbuConditon(rs.getString(5));
                ettall.settaskmaster(rs.getString(6));
                ettall.setmasterPost(rs.getString(7));
                ettall.setmasterTel(rs.getString(8));
                ettall.setmasterFax(rs.getString(9));
                ettall.setmasterMail(rs.getString(10));
                ettall.setcreditName(rs.getString(11));
                ettall.setrelation(rs.getString(12));
                ettall.setZIPCODE(rs.getString(13));
                ettall.setADDR(rs.getString(14));
                ettall.setaddress2(rs.getString(15));
                ettall.settelNum(rs.getString(16));
                ettall.setfaxNum(rs.getString(17));
                ettall.setgoodsMaster(rs.getString(18));
                ettall.sethomepage(rs.getString(19));
                ettall.setmasterID(rs.getString(20));
                ettall.setmoDate(rs.getString(21));
                ettall.setlocaCode(rs.getString(22));
                ettall.setchgType(rs.getString(23));
                ettall.setpermitCode(rs.getString(24));
                ettall.setInstituPhone(rs.getString(25));
                ettall.setApprove_yn(rs.getString(26));
                ettall.setDepId(rs.getString(27));
                ettall.setGroupNo(rs.getString(28));
                ettall.setManagerId(rs.getString(29));
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "");
        }
        catch (Exception exc) {
            Log.errors(this, exc, "");
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
        return ettall;
    }
    
    public String getAccDetail(final String code) throws Exception {
        Trx trx = null;
        Connection con = null;
        final UM_ADE_HistA050b ettall = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = " SELECT INSTITU_ACCT_CD FROM UM_PUB_INSTITU_ACCT_CD_HIST a WHERE INSTITU_CD = ?   AND VER=(SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD=a.INSTITU_CD) ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, code);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
        return "";
    }
    
    public void insertGongHistory(final String goNameShort, final String goNameEn, final String saupjaNumber, final String upTae, final String upJong, final String damdangName, final String damdangBuseo, final String damdangTel, final String damdangFax, final String damdangMail, final String bondholder, final String sogwanGubun, final String zipCode, final String address, final String extraAddress, final String telephone, final String faxNumber, final String homepage, final String regionCode, final String mulpumPerson, final String mostUperCode, final String mCode, final String userID, final String personPhone, final String idCharge, final int departmentNo, final int groupNo, final String goNameFul, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n INSERT INTO UM_PUB_INSTITU_HIST (\n         INSTITU_SHORT_NM, INSTITU_EN_NM, BIZ_REG_NO,\n         INSTITU_CONDITION, INSTITU_TYPE, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO,\n         CHRGR_FAX, CHRGR_EMAIL, CREDITOR_NM, INSTITU_CLS, ZIP_CD,\n         ADDR, DETAIL_ADDR, INSTITU_PHONE_NO, FAX, WEBSITE,\n         AREA_CD, GOODS_MNG_NM, MANAGER_ID, MOBILE, PERMIT_BRANCH, DEPARTMENT_NO, GROUP_NO, INSTITU_FULL_NM, INSTITU_CD, MOD_DT, APPROVE_YN, VER )  \n VALUES ( ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? , ?, ? ,?, SYSDATE, 'N', ";
        sql = String.valueOf(sql) + " (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ? ) ) ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goNameShort);
            pstmt.setString(2, goNameEn);
            pstmt.setString(3, saupjaNumber);
            pstmt.setString(4, upTae);
            pstmt.setString(5, upJong);
            pstmt.setString(6, damdangName);
            pstmt.setString(7, damdangBuseo);
            pstmt.setString(8, damdangTel);
            pstmt.setString(9, damdangFax);
            pstmt.setString(10, damdangMail);
            pstmt.setString(11, idCharge);
            pstmt.setString(12, sogwanGubun);
            pstmt.setString(13, zipCode);
            pstmt.setString(14, address);
            pstmt.setString(15, extraAddress);
            pstmt.setString(16, telephone);
            pstmt.setString(17, faxNumber);
            pstmt.setString(18, homepage);
            pstmt.setString(19, "0");
            pstmt.setString(20, mulpumPerson);
            pstmt.setString(21, userID);
            pstmt.setString(22, personPhone);
            pstmt.setString(23, bondholder);
            pstmt.setInt(24, departmentNo);
            pstmt.setInt(25, groupNo);
            pstmt.setString(26, goNameFul);
            pstmt.setString(27, mCode);
            pstmt.setString(28, mCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Không update thành công dữ liệu bên mời thầu.");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.updateGongInfo():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
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
    
    public void insertGongHistory(final String goNameShort, final String goNameEn, final String saupjaNumber, final String orgDiv1, final String orgDiv2, final String orgDiv3, final String buConditon, final String buType, final String taskmaster, final String masterPost, final String personTel, final String personFax, final String masterMail, final String bondholder, final String sogwanGubun, final String zipCode, final String address, final String extraAddress, final String telephone, final String faxNumber, final String homepage, final String regionCode, final String mulpumPerson, final String mCode, final String spOffice, final String eCitation, final String province, final String permitCode, final String branchOffi, final String trDistance, final String goNameFul, final String mostUperCode, final String userID, final String law, final String personPhone, final String idCharge, final int departmentNo, final int groupNo, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = "\n INSERT INTO UM_PUB_INSTITU_HIST ( \n   INSTITU_SHORT_NM, INSTITU_EN_NM, BIZ_REG_NO,\n   INSTITU_CONDITION, INSTITU_TYPE, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO,\n   CHRGR_FAX, CHRGR_EMAIL, CREDITOR_NM, INSTITU_CLS, ZIP_CD,\n   ADDR, DETAIL_ADDR, INSTITU_PHONE_NO, FAX, WEBSITE,\n   AREA_CD, GOODS_MNG_NM, SPECIAL_MNG_AGENT, E_AUTHENT_YN, LOCAL_INSTITU,\n\tAPPROVAL_CD, LOCAL_INSTITU_DIV, TRANS_DIST,\n   INSTITU_CLS_DIV, MANAGER_ID, MOBILE, PERMIT_BRANCH, DEPARTMENT_NO, GROUP_NO, INSTITU_FULL_NM, INSTITU_CD, MOD_DT, APPROVE_YN, VER ) \n VALUES ( ?, ?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, SYSDATE, 'N', ";
        sql = String.valueOf(sql) + " (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ? ) )";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goNameShort);
            pstmt.setString(2, goNameEn);
            pstmt.setString(3, saupjaNumber);
            pstmt.setString(4, buConditon);
            pstmt.setString(5, buType);
            pstmt.setString(6, taskmaster);
            pstmt.setString(7, masterPost);
            pstmt.setString(8, personTel);
            pstmt.setString(9, personFax);
            pstmt.setString(10, masterMail);
            pstmt.setString(11, idCharge);
            pstmt.setString(12, sogwanGubun);
            pstmt.setString(13, zipCode);
            pstmt.setString(14, address);
            pstmt.setString(15, extraAddress);
            pstmt.setString(16, telephone);
            pstmt.setString(17, faxNumber);
            pstmt.setString(18, homepage);
            pstmt.setString(19, "0");
            pstmt.setString(20, mulpumPerson);
            pstmt.setString(21, spOffice);
            pstmt.setString(22, eCitation);
            pstmt.setString(23, zipCode);
            pstmt.setString(24, permitCode);
            pstmt.setString(25, branchOffi);
            pstmt.setString(26, trDistance);
            pstmt.setString(27, "01");
            pstmt.setString(28, userID);
            pstmt.setString(29, personPhone);
            pstmt.setString(30, bondholder);
            pstmt.setInt(31, departmentNo);
            pstmt.setInt(32, groupNo);
            pstmt.setString(33, goNameFul);
            pstmt.setString(34, mCode);
            pstmt.setString(35, mCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Không cập nhật thông tin bên mời thầu thành công.");
            }
        }
        catch (Exception ex) {
            Log.debug("UM_ADB_GONGINFO.updateGongInfo():SQL : " + sql + ex.getMessage());
            throw new Exception(ex.getMessage());
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
    
    public void insertGongHistory(final String UserID, final String mCode, final String approveYn, final Connection conn) {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = " insert into UM_PUB_INSTITU_HIST (INSTITU_CD, MOD_DT, REG_DT, INSTITU_EN_NM, INSTITU_FULL_NM, SPECIAL_MNG_INSTITU, BIZ_REG_NO, MANAGER_ID,  DETAIL_ADDR, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_FAX, CHRGR_EMAIL, ADDR, GOODS_MNG_NM, WEBSITE, VER, APPROVE_YN, APPROVE_DT, APPROVE_ID )  select INSTITU_CD, UPDATE_DT, REG_DT, INSTITU_EN_NM, INSTITU_FULL_NM, SPECIAL_MNG_AGENT, BIZ_REG_NO,  MANAGER_ID, DETAIL_ADDR, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_FAX, CHRGR_EMAIL, ADDR, GOODS_MNG_NM, WEBSITE,  (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')   FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ? ), '" + approveYn + "', SYSDATE,  '" + UserID + "' " + " from UM_PUB_INSTITU_MAST" + " where INSTITU_CD=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mCode);
            pstmt.setString(2, mCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Đồng bộ dữ liệu cập nhật không thành công");
            }
        }
        catch (Exception ex) {
            Log.errors(this, ex, "");
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
    
    public void insertUserHist_By_Admin(final Connection conn, final String USER_ID, final String USER_CHANGE, final String mast_cd) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO USEMN.UM_USER_HIST (USER_ID,INPUT_DT,CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO ,CHRGR_MOBILE ,CHRGR_FAX ,CHRGR_EMAIL,MAST_CD ,USER_CHANGE,VER)  SELECT USER_ID , SYSDATE, CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO,  CHRGR_MOBILE ,CHRGR_FAX ,CHRGR_EMAIL,MAST_CD , ?,  (SELECT MAX(VER)  FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD= ? )FROM USEMN.UM_USER WHERE USER_ID = ? ";
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
    
    public void insertUserHist(final Connection conn, final String USER_ID, final String USER_CHRGR_NM, final String USER_CHRGR_DEPART, final String USER_CHRGR_PHONE_NO, final String USER_CHRGR_MOBILE, final String USER_CHRGR_FAX, final String USER_CHRGR_EMAIL, final String MAST_CD, final String USER_CHANGE) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO USEMN.UM_USER_HIST (USER_ID,INPUT_DT,CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO ,CHRGR_MOBILE ,CHRGR_FAX ,CHRGR_EMAIL,MAST_CD ,USER_CHANGE,VER)  VALUES( ?,SYSDATE,?,?,?,?,?,?,?,?,(SELECT MAX(VER)  FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD= ? )  ) ";
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
            pstmt.setString(9, USER_CHANGE);
            pstmt.setString(10, MAST_CD);
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
        final String sql = " INSERT INTO UM_USER_HIST (USER_ID,CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO,  CHRGR_MOBILE ,CHRGR_FAX ,CHRGR_EMAIL,MAST_CD, INPUT_DT, VER )  SELECT USER_ID,CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO,  CHRGR_MOBILE ,CHRGR_FAX ,CHRGR_EMAIL,MAST_CD, SYSDATE,  (SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ?) FROM UM_USER WHERE MAST_CD= ? ";
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
    
    public void deleteUSER_Hist(final Connection conn, final String Mast_Cd) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " DELETE FROM USEMN.UM_USER_HIST  WHERE MAST_CD = ?  AND VER=(SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ?)  ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Mast_Cd);
            pstmt.setString(2, Mast_Cd);
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
    
    public void insertGongHistoryAdmin(final String UserID, final String mCode, final Connection conn) throws Exception {
        String sql = null;
        PreparedStatement pstmt = null;
        sql = " insert into UM_PUB_INSTITU_HIST (INSTITU_CD, MOD_DT, INSTITU_EN_NM, INSTITU_FULL_NM, SPECIAL_MNG_INSTITU, BIZ_REG_NO, MANAGER_ID,  DETAIL_ADDR, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_FAX, CHRGR_EMAIL, ADDR, GOODS_MNG_NM, WEBSITE, VER )  select INSTITU_CD, sysdate, INSTITU_EN_NM, INSTITU_FULL_NM, SPECIAL_MNG_AGENT, BIZ_REG_NO, '" + UserID + "', DETAIL_ADDR, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_FAX," + " CHRGR_EMAIL, ADDR, GOODS_MNG_NM, WEBSITE, " + " (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  " + " FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = ? ) " + " from UM_PUB_INSTITU_MAST" + " where INSTITU_CD=?";
        try {
            pstmt = conn.prepareStatement(sql);
            Log.debug(sql);
            pstmt.setString(1, mCode);
            pstmt.setString(2, mCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Đồng bộ dữ liệu cập nhật không thành công");
            }
            conn.commit();
        }
        catch (Exception ex) {
            Log.errors(this, ex, "");
            throw new Exception("Đồng bộ dữ liệu cập nhật không thành công");
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
            sb.append("\tSELECT  REPR_NM, REPR_IDENT_NO ").append("   FROM  UM_REC_PUB_INSTITU_CERT_HIST a ").append(" WHERE  INSTITU_CD= ? AND  VER=(SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD=a.INSTITU_CD)      ");
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
    
    public void deleteCANewest(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE UM_REC_PUB_INSTITU_CERT_HIST a ");
            sql.append(" where INSTITU_CD=?  ");
            sql.append(" AND VER=(SELECT MAX(VER) FROM UM_PUB_INSTITU_HIST WHERE INSTITU_CD = a.INSTITU_CD ) ");
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
    
    public void saveCA(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_REC_PUB_INSTITU_CERT_HIST (RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX, CHRGR_MN, CHRGR_DEPART, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID, NICKNAME, RECV_DT, RECV_CONTENT, CERT_INSTITU, WEBSITE, REG_YN, REJECTED_RSON, APPROVE_REQ_CD, PERMIT_BRANCH, CERT_NM, ISSUE_YN, PERMIT_CD, PERMIT_USER_ID, PERMIT_DT, PASSWORD_MOD_YN, PASSWORD_MOD_ID, PASSWORD, SMS_RECV_YN, CERT_CLS, TEMP_NAME, VER) ");
            sql.append(" SELECT   RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX, CHRGR_MN, CHRGR_DEPART, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID, NICKNAME, RECV_DT, RECV_CONTENT, CERT_INSTITU, WEBSITE, REG_YN, REJECTED_RSON, APPROVE_REQ_CD, PERMIT_BRANCH, CERT_NM, ISSUE_YN, PERMIT_CD, PERMIT_USER_ID, PERMIT_DT, PASSWORD_MOD_YN, PASSWORD_MOD_ID, PASSWORD, SMS_RECV_YN, CERT_CLS, TEMP_NAME, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_REC_PUB_INSTITU_CERT_HIST WHERE INSTITU_CD = ? )  ");
            sql.append("  FROM UM_REC_PUB_INSTITU_CERT WHERE INSTITU_CD= ?   ");
            sql.append("  AND RECV_NO = (SELECT MAX(RECV_NO) FROM UM_REC_PUB_INSTITU_CERT WHERE INSTITU_CD= ? )  ");
            Debug.log(sql.toString());
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizRegNo);
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
    
    public void saveCA(final String bizRegNo, final UM_ADJ_GovuA020b caInfo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" INSERT INTO UM_REC_PUB_INSTITU_CERT_HIST (RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX, CHRGR_MN, CHRGR_DEPART, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID, NICKNAME, RECV_DT, RECV_CONTENT, CERT_INSTITU, WEBSITE, REG_YN, REJECTED_RSON, APPROVE_REQ_CD, PERMIT_BRANCH, CERT_NM, ISSUE_YN, PERMIT_CD, PERMIT_USER_ID, PERMIT_DT, PASSWORD_MOD_YN, PASSWORD_MOD_ID, PASSWORD, SMS_RECV_YN, CERT_CLS, TEMP_NAME, VER) ");
            sql.append(" SELECT   RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_EN_NM, ? , ? , ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX, CHRGR_MN, CHRGR_DEPART, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID, NICKNAME, RECV_DT, RECV_CONTENT, CERT_INSTITU, WEBSITE, REG_YN, REJECTED_RSON, APPROVE_REQ_CD, PERMIT_BRANCH, CERT_NM, ISSUE_YN, PERMIT_CD, PERMIT_USER_ID, PERMIT_DT, PASSWORD_MOD_YN, PASSWORD_MOD_ID, PASSWORD, SMS_RECV_YN, CERT_CLS, TEMP_NAME, (SELECT DECODE(SIGN(MAX(VER)-9), -1 ,'0'||('1'+MAX(VER)), 0, '1'+MAX(VER), 1, '1'+MAX(VER), '00')  FROM UM_REC_PUB_INSTITU_CERT_HIST WHERE INSTITU_CD = ?  )  ");
            sql.append("  FROM UM_REC_PUB_INSTITU_CERT WHERE INSTITU_CD= ?   ");
            sql.append("  AND RECV_NO = (SELECT MAX(RECV_NO) FROM UM_REC_PUB_INSTITU_CERT WHERE INSTITU_CD= ?  ) ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, caInfo.getIDENT());
            pstmt.setString(2, caInfo.getIDENTJUMIN());
            pstmt.setString(3, bizRegNo);
            pstmt.setString(4, bizRegNo);
            pstmt.setString(5, bizRegNo);
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
    
    public void updateCA(final String masterCode, final UM_ADJ_GovuA020b caInfo, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        if (caInfo == null) {
            throw new Exception("Không tìm thấy thông tin đăng ký CTS");
        }
        final String sql = " UPDATE UM_REC_PUB_INSTITU_CERT SET REPR_NM = ? ,REPR_IDENT_NO = ?  WHERE INSTITU_CD = ? AND RECV_NO = (SELECT MAX(RECV_NO) FROM UM_REC_PUB_INSTITU_CERT WHERE INSTITU_CD = ? )  ";
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
}
