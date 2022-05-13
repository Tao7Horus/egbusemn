// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.StringTokenizer;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import common.util.CommUtil;
import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import common.Log;
import common.Trx;

public class UM_ADB_GovrB030c
{
    public String getExistType(final String saupNo, final String goNameFull, final String recept) throws Exception {
        Trx trx = null;
        Connection con = null;
        final String result = "";
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        String existType = "0";
        int cnt = 0;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            String sql = " select count(*) from UM_REC_PUB_INSTITU_MAST where RECV_NO <> ? and BIZ_REG_NO = ? and REG_YN in ('N', 'Y') ";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, recept);
            pstmt.setString(2, saupNo);
            rest = pstmt.executeQuery();
            pstmt.clearParameters();
            if (rest.next()) {
                cnt = rest.getInt(1);
            }
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
            if (cnt > 0) {
                existType = "1";
            }
            else {
                sql = " select count(*) from UM_PUB_INSTITU_MAST where BIZ_REG_NO = ? and INSTITU_CD not like 'ZZ%' and DELETE_YN = 'N' ";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, saupNo);
                rest = pstmt.executeQuery();
                pstmt.clearParameters();
                if (rest.next()) {
                    cnt = rest.getInt(1);
                    if (cnt > 0) {
                        existType = "2";
                    }
                    else {
                        existType = "0";
                    }
                }
            }
        }
        catch (Exception e) {
            Log.debug("UM_ADJ_GovrB030c.getExistType Sá»‘ Ä‘Äƒng kÃ½ kinh doanh  [" + saupNo + "] TÃªn cÆ¡ quan [" + goNameFull + "] " + e.toString());
        }
        finally {
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
            try {
                if (con != null) {
                    trx.close();
                }
            }
            catch (Exception ex5) {}
        }
        try {
            if (rest != null) {
                rest.close();
            }
        }
        catch (Exception ex6) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex7) {}
        try {
            if (con != null) {
                trx.close();
            }
        }
        catch (Exception ex8) {}
        return existType;
    }
    
    public String getExistType(final Connection con, final String recept, final String saupNo, final String goNameFull) throws Exception {
        final String result = "";
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        String existType = "0";
        int cnt = 0;
        try {
            String sql = " select count(*) from UM_REC_PUB_INSTITU_MAST where RECV_NO <> ? and BIZ_REG_NO = ? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, recept);
            pstmt.setString(2, saupNo);
            rest = pstmt.executeQuery();
            pstmt.clearParameters();
            if (rest.next()) {
                cnt = rest.getInt(1);
            }
            if (rest != null) {
                try {
                    rest.close();
                }
                catch (Exception ex) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex2) {}
            }
            if (cnt > 0) {
                existType = "1";
            }
            else {
                sql = " select count(*) from UM_PUB_INSTITU_MAST where BIZ_REG_NO = ? ";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, saupNo);
                rest = pstmt.executeQuery();
                pstmt.clearParameters();
                if (rest.next()) {
                    cnt = rest.getInt(1);
                    if (cnt > 0) {
                        existType = "2";
                    }
                    else {
                        existType = "0";
                    }
                }
            }
        }
        finally {
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
        }
        try {
            if (rest != null) {
                rest.close();
            }
        }
        catch (Exception ex5) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex6) {}
        return existType;
    }
    
    public String getChodalReceptionNumber(final Connection con) throws Exception {
        String result = "";
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        final String sql = "SELECT to_char(sysdate,'yyyy')||lpad((substr(max(RECV_NO), 5,6)+1), 6, '0') FROM UM_REC_PUB_INSTITU_CERT";
        try {
            pstmt = con.prepareStatement(sql);
            rest = pstmt.executeQuery();
            if (!rest.next()) {
                throw new Exception("RECV_NO Null");
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
    
    public String getInstituNumber(final Connection con) throws Exception {
        String result = "";
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        final String sql = "SELECT 'Z'||lpad((substr(max(INSTITU_CD), 2,6)+1), 6, '0') FROM UM_REC_PUB_INSTITU_MAST  where INSTITU_CD like 'Z%'";
        try {
            pstmt = con.prepareStatement(sql);
            rest = pstmt.executeQuery();
            if (!rest.next()) {
                throw new Exception("INSTITU_CD khÃ´ng tá»“n táº¡i");
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
    
    public String getCertRecetionNumber(final Connection con) throws Exception {
        String result = "";
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        final String sql = "SELECT to_char(sysdate,'yyyy')||lpad((substr(max(RECV_NO), 5, 10)+1), 6, '0') FROM   UM_REC_PUB_INSTITU_CERT";
        try {
            pstmt = con.prepareStatement(sql);
            rest = pstmt.executeQuery();
            if (!rest.next()) {
                throw new Exception("Ä�Äƒng kÃ½ chá»©ng nháº¥n sá»‘ cÃ³ RECV_NO Null");
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
    
    public String getG2BCode(final String newCode, final Connection con) throws Exception {
        String result = "";
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        String sql = null;
        try {
            if (newCode.equals("1")) {
                sql = "SELECT 'ZD'||lpad((substr(max(INSTITU_CD), 3, 7)+1), 5, '0') FROM UM_REC_PUB_INSTITU_MAST  where INSTITU_CD like 'ZD%'";
            }
            else if (newCode.equals("2")) {
                sql = "SELECT 'ZI'||lpad((substr(max(INSTITU_CD), 3, 7)+1), 5, '0') FROM UM_REC_PUB_INSTITU_MAST  WHERE INSTITU_CD like 'ZI%'";
            }
            else if (newCode.equals("3")) {
                sql = "SELECT 'ZP'||lpad((substr(max(INSTITU_CD), 3, 7)+1), 5, '0') FROM UM_REC_PUB_INSTITU_MAST  where INSTITU_CD like 'ZP%'";
            }
            else if (newCode.equals("4")) {
                sql = "SELECT 'ZR'||lpad((substr(max(INSTITU_CD), 3, 7)+1), 5, '0') FROM UM_REC_PUB_INSTITU_MAST  where INSTITU_CD like 'ZR%'";
            }
            else if (newCode.equals("5")) {
                sql = "SELECT 'ZC'||lpad((substr(max(INSTITU_CD), 3, 7)+1), 5, '0') FROM UM_REC_PUB_INSTITU_MAST  where INSTITU_CD like 'ZC%'";
            }
            else {
                if (!newCode.equals("6")) {
                    throw new Exception("KhÃ´ng cÃ³ code trong dá»¯ liá»‡u.");
                }
                sql = "SELECT 'Z0'||lpad((substr(max(INSTITU_CD), 3, 7)+1), 5, '0') FROM UM_REC_PUB_INSTITU_MAST  where INSTITU_CD like 'Z0%'";
            }
            pstmt = con.prepareStatement(sql);
            rest = pstmt.executeQuery();
            if (!rest.next()) {
                throw new Exception("MÃ£ cÆ¡ quan má»›i lÃ  Null");
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
    
    public void insertGigwanMaster(final String instituCode, final String saupNo, final String recept, final String license, final String recept1, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO UM_REC_PUB_INSTITU_MAST(RECV_NO, INSTITU_FULL_NM, CHRGR_NM, CHRGR_DEPART,CHRGR_PHONE_NO,CHRGR_FAX,CHRGR_EMAIL, ZIP_CD, ADDR, DETAIL_ADDR, PHONE_NO,FAX,GOODS_MNG_NM, RECV_DT, INSTITU_CD, INSTITU_SHORT_NM, INSTITU_EN_NM, BIZ_REG_NO,WEBSITE, INSTITU_TYPE, MOBILE, BIZ_CONDITION, BIZ_SALE_CAT, PERMIT_BRANCH, CERT_RECV_NO, REG_YN, CREDITOR_NM, DEPARTMENT_NO, GROUP_NO ) VALUES(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, sysdate,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ? )";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, recept);
            pstmt.setString(2, CommUtil.retSpace(req.getParameter("goNameFull")));
            pstmt.setString(3, CommUtil.retSpace(req.getParameter("taskmaster")));
            pstmt.setString(4, CommUtil.retSpace(req.getParameter("masterPost")));
            pstmt.setString(5, CommUtil.retSpace(req.getParameter("personTel")));
            pstmt.setString(6, CommUtil.retSpace(req.getParameter("personFax")));
            pstmt.setString(7, CommUtil.retSpace(req.getParameter("masterMail")));
            pstmt.setString(8, CommUtil.retSpace(req.getParameter("postNo")));
            pstmt.setString(9, CommUtil.retSpace(req.getParameter("ADDR")));
            pstmt.setString(10, "");
            pstmt.setString(11, CommUtil.retSpace(req.getParameter("comTel")));
            pstmt.setString(12, CommUtil.retSpace(req.getParameter("comFax")));
            pstmt.setString(13, CommUtil.retSpace(req.getParameter("creditName")));
            if (CommUtil.retSpace(req.getParameter("g2bCode")) != "") {
                pstmt.setString(14, CommUtil.retSpace(req.getParameter("g2bCode")));
            }
            else {
                pstmt.setString(14, instituCode);
            }
            pstmt.setString(15, CommUtil.retSpace(req.getParameter("goNameShort")));
            pstmt.setString(16, CommUtil.retSpace(req.getParameter("goNameEn")));
            if (CommUtil.retSpace(req.getParameter("saupNo")) != "") {
                pstmt.setString(17, CommUtil.retSpace(req.getParameter("saupNo")));
            }
            else {
                pstmt.setString(17, saupNo);
            }
            pstmt.setString(18, CommUtil.retSpace(req.getParameter("homepage")));
            pstmt.setString(19, CommUtil.retSpace(req.getParameter("insType")));
            pstmt.setString(20, CommUtil.retSpace(req.getParameter("personPhone")));
            pstmt.setString(21, CommUtil.retSpace(req.getParameter("buConditon")));
            pstmt.setString(22, CommUtil.retSpace(req.getParameter("buType")));
            pstmt.setString(23, CommUtil.retSpace(req.getParameter("permitBranch")));
            pstmt.setString(24, recept);
            pstmt.setString(25, "N");
            pstmt.setString(26, CommUtil.retSpace(req.getParameter("idCharge")));
            pstmt.setInt(27, Integer.parseInt(req.getParameter("departmentNo")));
            pstmt.setInt(28, Integer.parseInt(req.getParameter("groupNo")));
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Cáº\u00adp nháº\u00adt dá»¯ liá»‡u lá»—i.");
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public void insertGigwanMasterCoQuan(final String instituCode, final String saupNo, final String recept, final String license, final String recept1, final HttpServletRequest req, final Connection con, final String coquan) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO UM_REC_PUB_INSTITU_MAST(RECV_NO, INSTITU_FULL_NM, CHRGR_NM, CHRGR_DEPART,CHRGR_PHONE_NO,CHRGR_FAX,CHRGR_EMAIL, ZIP_CD, ADDR, DETAIL_ADDR, PHONE_NO,FAX,GOODS_MNG_NM, RECV_DT, INSTITU_CD, INSTITU_SHORT_NM, INSTITU_EN_NM, BIZ_REG_NO,WEBSITE, INSTITU_TYPE, MOBILE, BIZ_CONDITION, BIZ_SALE_CAT, PERMIT_BRANCH, CERT_RECV_NO, REG_YN, CREDITOR_NM, DEPARTMENT_NO, GROUP_NO, ORG_NO ) VALUES(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, sysdate,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ? )";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, recept);
            pstmt.setString(2, CommUtil.retSpace(req.getParameter("goNameFull")));
            pstmt.setString(3, CommUtil.retSpace(req.getParameter("taskmaster")));
            pstmt.setString(4, CommUtil.retSpace(req.getParameter("masterPost")));
            pstmt.setString(5, CommUtil.retSpace(req.getParameter("personTel")));
            pstmt.setString(6, CommUtil.retSpace(req.getParameter("personFax")));
            pstmt.setString(7, CommUtil.retSpace(req.getParameter("masterMail")));
            pstmt.setString(8, CommUtil.retSpace(req.getParameter("postNo")));
            pstmt.setString(9, CommUtil.retSpace(req.getParameter("ADDR")));
            pstmt.setString(10, "");
            pstmt.setString(11, CommUtil.retSpace(req.getParameter("comTel")));
            pstmt.setString(12, CommUtil.retSpace(req.getParameter("comFax")));
            pstmt.setString(13, CommUtil.retSpace(req.getParameter("creditName")));
            if (CommUtil.retSpace(req.getParameter("g2bCode")) != "") {
                pstmt.setString(14, CommUtil.retSpace(req.getParameter("g2bCode")));
            }
            else {
                pstmt.setString(14, instituCode);
            }
            pstmt.setString(15, CommUtil.retSpace(req.getParameter("goNameShort")));
            pstmt.setString(16, CommUtil.retSpace(req.getParameter("goNameEn")));
            if (CommUtil.retSpace(req.getParameter("saupNo")) != "") {
                pstmt.setString(17, CommUtil.retSpace(req.getParameter("saupNo")));
            }
            else {
                pstmt.setString(17, saupNo);
            }
            pstmt.setString(18, CommUtil.retSpace(req.getParameter("homepage")));
            pstmt.setString(19, CommUtil.retSpace(req.getParameter("insType")));
            pstmt.setString(20, CommUtil.retSpace(req.getParameter("personPhone")));
            pstmt.setString(21, CommUtil.retSpace(req.getParameter("buConditon")));
            pstmt.setString(22, CommUtil.retSpace(req.getParameter("buType")));
            pstmt.setString(23, CommUtil.retSpace(req.getParameter("permitBranch")));
            pstmt.setString(24, recept);
            pstmt.setString(25, "N");
            pstmt.setString(26, CommUtil.retSpace(req.getParameter("idCharge")));
            pstmt.setInt(27, Integer.parseInt(req.getParameter("departmentNo")));
            pstmt.setInt(28, Integer.parseInt(req.getParameter("groupNo")));
            pstmt.setString(29, coquan);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("Cáº\u00adp nháº\u00adt dá»¯ liá»‡u lá»—i.");
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public void insertGigwanMasterBackup(final String recept, final String g2bCode, final String license, final String recept1, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO UM_REC_PUB_INSTITU_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n     RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_SHORT_NM, INSTITU_EN_NM, BIZ_REG_NO,\t\t\t\n     BIZ_CONDITION, BIZ_SALE_CAT, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_FAX,\t\t\t\t\t\t\t\t\t\t\t\n     CHRGR_EMAIL, CREDITOR_NM, ZIP_CD, ADDR, DETAIL_ADDR, PHONE_NO,\t\t\t\t\t\t\t\t\t\t\t\t\t\n     FAX, GOODS_MNG_NM, WEBSITE, RECV_DT, CERT_INSTITU, APP_REQ_YN,\t\t\t\t\t\t\t\t\t\t\n     PERMIT_BRANCH, REG_YN, CERT_RECV_NO, INSTITU_TYPE, INSTITU_CLS, INSTITU_CLS_M,\t\t\t\t\t\t\t\t\t\t\t\n     INSTITU_CLS_S, MOBILE, SMS_RECV_YN                                                                            \n )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n VALUES\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n     ?, ?, ?, ?, ?, ?,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n     ?, ?, ?, ?, ?, ?,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n     ?, ?, ?, ?, ?, ?,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n     ?, ?, ?, ?, ?, ?,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n     ?, ?, ?, ?, ?, ?, ?, ?, ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n )\n";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, recept);
            pstmt.setString(2, g2bCode);
            pstmt.setString(3, CommUtil.retSpace(req.getParameter("goNameFull")));
            pstmt.setString(4, CommUtil.retSpace(req.getParameter("goNameShort")));
            pstmt.setString(5, CommUtil.retSpace(req.getParameter("goNameEn")));
            pstmt.setString(6, CommUtil.retSpace(req.getParameter("saupNo")));
            pstmt.setString(7, CommUtil.retSpace(req.getParameter("buConditon")));
            pstmt.setString(8, CommUtil.retSpace(req.getParameter("buType")));
            pstmt.setString(9, "Nguyen Van A");
            pstmt.setString(10, "Truong phong");
            pstmt.setString(11, "01213141424");
            pstmt.setString(12, "436243y643");
            pstmt.setString(13, "anv@yahoo.com");
            pstmt.setString(14, CommUtil.retSpace(req.getParameter("creditName")));
            pstmt.setString(15, "3");
            pstmt.setString(16, "445-Doi Can");
            pstmt.setString(17, "Hoang Van Thu");
            pstmt.setString(18, "51351351325");
            pstmt.setString(19, "26456666456");
            pstmt.setString(20, "goods master");
            pstmt.setString(21, CommUtil.retSpace(req.getParameter("homepage")));
            pstmt.setString(22, "2009-05-12");
            System.out.println("license:" + license);
            pstmt.setString(23, "Y");
            pstmt.setString(24, CommUtil.retSpace(req.getParameter("perOff")));
            pstmt.setString(25, "N");
            pstmt.setString(26, recept1);
            if (CommUtil.retSpace(req.getParameter("relation")).equals("51") || CommUtil.retSpace(req.getParameter("relation")).equals("52") || CommUtil.retSpace(req.getParameter("relation")).equals("53")) {
                pstmt.setString(27, "05");
            }
            else if (CommUtil.retSpace(req.getParameter("relation")).equals("71") || CommUtil.retSpace(req.getParameter("relation")).equals("72")) {
                pstmt.setString(27, "07");
            }
            else {
                pstmt.setString(27, CommUtil.retSpace(req.getParameter("relation")));
            }
            pstmt.setString(28, CommUtil.retSpace(req.getParameter("orgDiv1")));
            pstmt.setString(29, CommUtil.retSpace(req.getParameter("orgDiv2")));
            pstmt.setString(30, CommUtil.retSpace(req.getParameter("orgDiv3")));
            String HP = req.getParameter("HP");
            if (HP == null || HP.equals("")) {
                HP = " ";
            }
            pstmt.setString(31, HP);
            pstmt.setString(32, CommUtil.retSpace(req.getParameter("smsCheck")));
            pstmt.setString(33, "");
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("ê¸°ê´€ì‹ ì²\u00ad insert ê°¯ìˆ˜ 1 ì�´ ì•„ë‹˜.");
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
    
    public void insertGigwanCreditTable(final String recept, final String mathCode, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = "insert into UM_REC_PUB_INSTITU_ACCT_CD   values(?,  ?)";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            if (CommUtil.retSpace(req.getParameter("mathCode")) != "") {
                pstmt.setString(1, CommUtil.retSpace(req.getParameter("mathCode")));
            }
            else {
                pstmt.setString(1, mathCode);
            }
            pstmt.setString(2, recept);
            pstmt.executeUpdate();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public void insertGigwanSertTable(final String recept, final String saupNo, final String approvedCode, final String g2bCode, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO UM_REC_PUB_INSTITU_CERT(RECV_NO, INSTITU_FULL_NM, CHRGR_MN, CHRGR_DEPART,CHRGR_PHONE_NO,CHRGR_EMAIL, ZIP_CD, ADDR, DETAIL_ADDR, FAX,RECV_DT, INSTITU_CD, INSTITU_EN_NM, BIZ_REG_NO,WEBSITE, MOBILE, PERMIT_BRANCH,REPR_NM, REPR_IDENT_NO,CHRGR_IDENT_NO, APPROVE_REQ_CD, USER_ID, CERT_CLS, CERT_INSTITU, REG_YN) VALUES(?, ?, ?, ?, ?, ?,?, ?, ?, ?, sysdate, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ? )";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, recept);
            pstmt.setString(2, CommUtil.retSpace(req.getParameter("goNameFull")));
            pstmt.setString(3, CommUtil.retSpace(req.getParameter("taskmaster")));
            pstmt.setString(4, CommUtil.retSpace(req.getParameter("masterPost")));
            pstmt.setString(5, CommUtil.retSpace(req.getParameter("personTel")));
            pstmt.setString(6, CommUtil.retSpace(req.getParameter("masterMail")));
            pstmt.setString(7, CommUtil.retSpace(req.getParameter("postNo")));
            pstmt.setString(8, CommUtil.retSpace(req.getParameter("ADDR")));
            pstmt.setString(9, CommUtil.retSpace(req.getParameter("address2")));
            pstmt.setString(10, CommUtil.retSpace(req.getParameter("comFax")));
            if (CommUtil.retSpace(req.getParameter("g2bCode")) != "") {
                pstmt.setString(11, CommUtil.retSpace(req.getParameter("g2bCode")));
            }
            else {
                pstmt.setString(11, g2bCode);
            }
            pstmt.setString(12, CommUtil.retSpace(req.getParameter("goNameEn")));
            if (CommUtil.retSpace(req.getParameter("saupNo")) != "") {
                pstmt.setString(13, CommUtil.retSpace(req.getParameter("saupNo")));
            }
            else {
                pstmt.setString(13, saupNo);
            }
            pstmt.setString(14, CommUtil.retSpace(req.getParameter("homepage")));
            pstmt.setString(15, CommUtil.retSpace(req.getParameter("comTel")));
            pstmt.setString(16, CommUtil.retSpace(req.getParameter("permitBranch")));
            pstmt.setString(17, CommUtil.retSpace(req.getParameter("IDENT")));
            pstmt.setString(18, CommUtil.retSpace(req.getParameter("registno1")));
            pstmt.setString(19, CommUtil.retSpace(req.getParameter("idCharge")));
            pstmt.setString(20, approvedCode);
            pstmt.setString(21, CommUtil.retSpace(req.getParameter("USRID")));
            pstmt.setString(22, "B");
            pstmt.setString(23, cu.retNull(req.getParameter("choiceCA")));
            pstmt.setString(24, "N");
            pstmt.executeUpdate();
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public void updateDraftData(final String bizRegNo, final String draftData, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        String sql = " update UM_SUPPLIER_ENTER_MAST\t\t\t\n";
        if ("Y".equals(draftData)) {
            sql = String.valueOf(sql) + "    set HIDDEN_YN    = 'N'\t\t\t\t\t\t\n";
        }
        else {
            sql = String.valueOf(sql) + "    set HIDDEN_YN    = 'Y'\t\t\t\t\t\t\n";
        }
        sql = String.valueOf(sql) + "         where BIZ_REG_NO = ?\t\t\t\t\t\t";
        final CommUtil cu = new CommUtil();
        Log.debug("sql: " + sql);
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, CommUtil.retSpace(bizRegNo));
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
    
    public void updateDraftRecData(final String bizRegNo, final String draftData, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        String sql = " update UM_REC_SUPPLIER_ENTER_MAST\t\t\t\n";
        if ("Y".equals(draftData)) {
            sql = String.valueOf(sql) + "    set HIDDEN_YN    = 'N'\t\t\t\t\t\t\n";
        }
        else {
            sql = String.valueOf(sql) + "    set HIDDEN_YN    = 'Y'\t\t\t\t\t\t\n";
        }
        sql = String.valueOf(sql) + "         where BIZ_REG_NO = ?\t\t\t\t\t\t";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, CommUtil.retSpace(bizRegNo));
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
    
    public void updateDraftDataBuyer(final String instituCD, final String draftData, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        String sql = " update UM_PUB_INSTITU_MAST\t\t\t\n";
        if ("Y".equals(draftData)) {
            sql = String.valueOf(sql) + "    set HIDDEN_YN    = 'N'\t\t\t\t\t\t\n";
        }
        else {
            sql = String.valueOf(sql) + "    set HIDDEN_YN    = 'Y'\t\t\t\t\t\t\n";
        }
        sql = String.valueOf(sql) + "         where INSTITU_CD = ?\t\t\t\t\t\t";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, CommUtil.retSpace(instituCD));
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
    
    public void updateDraftRecDataBuyer(final String instituCD, final String draftData, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        String sql = " update UM_REC_PUB_INSTITU_MAST\t\t\t\n";
        if ("Y".equals(draftData)) {
            sql = String.valueOf(sql) + "    set HIDDEN_YN    = 'N'\t\t\t\t\t\t\n";
        }
        else {
            sql = String.valueOf(sql) + "    set HIDDEN_YN    = 'Y'\t\t\t\t\t\t\n";
        }
        sql = String.valueOf(sql) + "         where INSTITU_CD = ?\t\t\t\t\t\t";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, CommUtil.retSpace(instituCD));
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
    
    public void insertGigwanSertTableBackup(final String recept1, final String g2bCode, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " INSERT INTO UM_REC_PUB_INSTITU_CERT\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n  RECV_NO,         INSTITU_CD,   INSTITU_FULL_NM,    INSTITU_EN_NM,    REPR_NM,     REPR_IDENT_NO, ZIP_CD,\t\t\t\t \n  ADDR,             DETAIL_ADDR,     BIZ_REG_NO,     FAX,           CHRGR_NM,   CHRGR_DEPART,   CHRGR_IDENT_NO,\t\t\t\t \n  CHRGR_PHONE_NO,   CHRGR_EMAIL, MOBILE,             USER_ID,           NICKNAME,     CERT_INSTITU,   RECV_DT,\t\t\t\t\t\t \n  WEBSITE,         APPROVE_REQ_CD,   PERMIT_BRANCH,           REG_YN,\t SMS_RECV_YN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n VALUES\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n   ?, ?, ?, ?, ?, ?, ?,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n   ?, ?, ?, ?, ?, ?, ?,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n   ?, ?, ?, ?, ?, ?, sysdate,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n   ?, ?, ?, ?, ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, recept1);
            pstmt.setString(2, g2bCode);
            pstmt.setString(3, CommUtil.retSpace(req.getParameter("goNameFull")));
            pstmt.setString(4, CommUtil.retSpace(req.getParameter("goNameEn")));
            pstmt.setString(5, CommUtil.retSpace(req.getParameter("IDENT")));
            pstmt.setString(6, CommUtil.retSpace(req.getParameter("IDENTJUMIN")));
            pstmt.setString(7, cu.replace(req.getParameter("ZIPCODE"), "-", ""));
            pstmt.setString(8, CommUtil.retSpace(req.getParameter("ADDR")));
            pstmt.setString(9, CommUtil.retSpace(req.getParameter("address2")));
            pstmt.setString(10, CommUtil.retSpace(req.getParameter("saupNo")));
            pstmt.setString(11, String.valueOf(CommUtil.retSpace(req.getParameter("fax_1"))) + "-" + CommUtil.retSpace(req.getParameter("fax_2")));
            pstmt.setString(12, CommUtil.retSpace(req.getParameter("taskmaster")));
            pstmt.setString(13, CommUtil.retSpace(req.getParameter("masterPost")));
            pstmt.setString(14, CommUtil.retSpace(req.getParameter("JUMIN")));
            pstmt.setString(15, String.valueOf(CommUtil.retSpace(req.getParameter("mTel_1"))) + "-" + CommUtil.retSpace(req.getParameter("mTel_2")));
            pstmt.setString(16, CommUtil.retSpace(req.getParameter("masterMail")));
            String HP = req.getParameter("HP");
            if (HP == null || HP.equals("")) {
                HP = " ";
            }
            pstmt.setString(17, HP);
            pstmt.setString(18, CommUtil.retSpace(req.getParameter("USRID")));
            pstmt.setString(19, "Hawk");
            pstmt.setString(20, CommUtil.retSpace(req.getParameter("lic")));
            pstmt.setString(21, CommUtil.retSpace(req.getParameter("homepage")));
            pstmt.setString(22, String.valueOf(recept1.substring(2, recept1.length())) + CommUtil.retSpace(req.getParameter("USRID")));
            pstmt.setString(23, CommUtil.retSpace(req.getParameter("perOff")));
            pstmt.setString(24, "N");
            pstmt.setString(25, CommUtil.retSpace(req.getParameter("smsCheck")));
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
    
    public String getApprovalCode(final String id) throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        String result = "";
        final String sql = "SELECT PERMIT_BRANCH FROM UM_USER WHERE USER_ID = ?";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rest = pstmt.executeQuery();
            if (rest.next()) {
                result = rest.getString(1);
            }
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
            try {
                if (con != null) {
                    trx.close();
                }
            }
            catch (Exception ex3) {}
        }
        try {
            if (rest != null) {
                rest.close();
            }
        }
        catch (Exception ex4) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex5) {}
        try {
            if (con != null) {
                trx.close();
            }
        }
        catch (Exception ex6) {}
        return result;
    }
    
    public String getUserCoQuan(final String recept) throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        String result = "";
        final String sql = "SELECT ORG_NO FROM UM_REC_PUB_INSTITU_MAST WHERE RECV_NO = ?";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, recept);
            rest = pstmt.executeQuery();
            if (rest.next()) {
                result = rest.getString(1);
            }
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
            try {
                if (con != null) {
                    trx.close();
                }
            }
            catch (Exception ex3) {}
        }
        try {
            if (rest != null) {
                rest.close();
            }
        }
        catch (Exception ex4) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex5) {}
        try {
            if (con != null) {
                trx.close();
            }
        }
        catch (Exception ex6) {}
        return result;
    }
    
    public String getUserCoQuanCert(final String uid, final Connection con) throws Exception {
        final Trx trx = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = "";
        final Statement stm = null;
        final String sql = "select a.org_no from usemn.UM_REC_PUB_INSTITU_MAST a, usemn.UM_USER b where a.institu_cd = B.MAST_CD and b.user_id = '" + uid + "'";
        try {
            pstmt = con.prepareStatement(sql);
            Log.debug("-----sql:" + sql);
            rs = pstmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    result = rs.getString(1);
                }
            }
            else {
                Log.debug("-----$$$$$$$$$$$$$$$$$$ rs null ");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Log.debug("-----ExceptionExceptionExceptionException:" + ex.getMessage());
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex3) {}
        }
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception ex4) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex5) {}
        return result;
    }
    
    public List getMenuCoQuan(final Connection con) throws Exception {
        final List list = new ArrayList();
        final Trx trx = null;
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        final String result = "";
        final String sql = "select menu_id from portal.PT_MENu where menu_id like '00122%' or menu_id like '00035%'";
        try {
            pstmt = con.prepareStatement(sql);
            rest = pstmt.executeQuery();
            if (rest != null) {
                while (rest.next()) {
                    list.add(rest.getString(1));
                }
            }
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
        return list;
    }
    
    public boolean checkExistsMenuId(final String userID, final String menuId, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer("");
        sql.append("select count(*) FROM bid.BID_UM_USER_MENU a WHERE USER_ID = ? AND MENU_ID = ?  ");
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, userID);
            pstmt.setString(2, menuId);
            final ResultSet rs = pstmt.executeQuery();
            long count = 0L;
            while (rs.next()) {
                count = rs.getLong(1);
            }
            return count == 0L;
        }
        catch (SQLException sqle) {
            Log.errors("EP_LPB_PLI001.isInsertBidWorkingDate block SQLException : ");
            Log.errors(this, sqle, "Lý do phát sinh Exception : ");
            throw new Exception("Exception Error : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.errors("EP_LPB_PLI001.isInsertBidWorkingDate block Exception : ");
            Log.errors(this, exc, "Lý do phát sinh Exception : ");
            throw new Exception("Exception Error : " + exc.toString());
        }
        finally {
            try {
                pstmt.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public String insertPermission(final String USER_ID, final String MENU_ID, final String GROUP_ID, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        final CallableStatement cstmt = null;
        StringBuffer sql2 = null;
        try {
            conn.setAutoCommit(false);
            long id = 0L;
            final ResultSet rs = conn.createStatement().executeQuery("SELECT bid.BID_UM_ID_PERMSISSION.nextval FROM DUAL");
            while (rs.next()) {
                id = rs.getLong(1);
            }
            sql2 = new StringBuffer(" insert into BID.BID_UM_USER_MENU (");
            sql2.append(" ID, ");
            sql2.append(" USER_ID, ");
            sql2.append(" MENU_ID, ");
            sql2.append(" GROUP_ID) ");
            sql2.append(" values (?,?,?,?)");
            pstmt = conn.prepareStatement(sql2.toString());
            pstmt.setLong(1, id);
            pstmt.setString(2, USER_ID);
            pstmt.setString(3, MENU_ID);
            pstmt.setString(4, GROUP_ID);
            pstmt.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
            return String.valueOf(id);
        }
        catch (SQLException sqle) {
            Log.errors("EP_LPB_PLI001.insertBidWorkingDate block SQLException : ");
            Log.errors(this, sqle, "Lý do phát sinh Exception : ");
            throw new Exception("Exception Error : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.errors("EP_LPB_PLI001.insertBidWorkingDate block Exception : ");
            Log.errors(this, exc, "Lý do phát sinh Exception : ");
            throw new Exception("Exception Error : " + exc.toString());
        }
        finally {
            try {
                pstmt.close();
            }
            catch (Exception ex) {}
        }
    }
    
    public void updateGigwanMaster(final String recept, final String g2bCode, final String license, final String recept1, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " update UM_REC_PUB_INSTITU_MAST\t\t\t\n    set SMS_RECV_YN    = ?,\t\t\t\t\t\t\n        INSTITU_FULL_NM = ?,\t\t\t\t\t\t\n        CHRGR_NM = ?,\t\t\t\t\t\t\n        CHRGR_DEPART = ?,\t\t\t\t\t\t\n        CHRGR_PHONE_NO  = ?,\t\t\t\t\t\t\n        CHRGR_FAX            = ?,\t\t\t\t\t\t\t\n        CHRGR_EMAIL            = ?,\t\t\t\t\t\t\t\n        ZIP_CD        = ?,\t\t\t\t\t\t\t\n        ADDR    = ?,\t\t\t\t\t\t\n        DETAIL_ADDR  = ?,\t\t\t\t\t\t\n        PHONE_NO  = ?,\t\t\t\t\t\t\n        FAX  = ?,\t\t\t\t\t\t\n        GOODS_MNG_NM        = ?,\t\t\t\t\t\t\t\n        RECV_DT        = sysdate,\t\t\t\t\t\t\t\n        INSTITU_CD            = ?,\t\t\t\t\t\t\t\n        INSTITU_SHORT_NM      = ?,\t\t\t\t\t\t\t\n        INSTITU_EN_NM        = ?,\t\t\t\t\t\t\t\n        BIZ_REG_NO        = ?,\t\t\t\t\t\t\t\n        WEBSITE    = ?,\t\t\t\t\t\t\n        INSTITU_TYPE    = ?,\t\t\t\t\t\t\n        MOBILE        = ?,\t\t\t\t\t\n        BIZ_CONDITION    = ?,\t\t\t\t\t\t\n        BIZ_SALE_CAT        = ?,\t\t\t\t\t\t\t\n        PERMIT_BRANCH        = ?,\t\t\t\t\t\t\t\n        CREDITOR_NM        = ?,\t\t\t\t\t\t\t\n        REG_YN  = ?,\t\t\t\t\t\t\n        GROUP_NO  = ?,\t\t\t\t\t\t\n        DEPARTMENT_NO  = ?\t\t\t\t\t\t\n         where RECV_NO = ?\t\t\t\t\t\t";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "N");
            pstmt.setString(2, CommUtil.retSpace(req.getParameter("goNameFull")));
            pstmt.setString(3, CommUtil.retSpace(req.getParameter("taskmaster")));
            pstmt.setString(4, CommUtil.retSpace(req.getParameter("masterPost")));
            pstmt.setString(5, CommUtil.retSpace(req.getParameter("personTel")));
            pstmt.setString(6, CommUtil.retSpace(req.getParameter("personFax")));
            pstmt.setString(7, CommUtil.retSpace(req.getParameter("masterMail")));
            pstmt.setString(8, CommUtil.retSpace(req.getParameter("postNo")));
            pstmt.setString(9, CommUtil.retSpace(req.getParameter("ADDR")));
            pstmt.setString(10, CommUtil.retSpace(req.getParameter("address2")));
            pstmt.setString(11, CommUtil.retSpace(req.getParameter("comTel")));
            pstmt.setString(12, CommUtil.retSpace(req.getParameter("comFax")));
            pstmt.setString(13, CommUtil.retSpace(req.getParameter("creditName")));
            pstmt.setString(14, CommUtil.retSpace(req.getParameter("g2bCode")));
            pstmt.setString(15, CommUtil.retSpace(req.getParameter("goNameShort")));
            pstmt.setString(16, CommUtil.retSpace(req.getParameter("goNameEn")));
            pstmt.setString(17, CommUtil.retSpace(req.getParameter("saupNo")));
            pstmt.setString(18, CommUtil.retSpace(req.getParameter("homepage")));
            pstmt.setString(19, CommUtil.retSpace(req.getParameter("insType")));
            pstmt.setString(20, CommUtil.retSpace(req.getParameter("personPhone")));
            pstmt.setString(21, CommUtil.retSpace(req.getParameter("buConditon")));
            pstmt.setString(22, CommUtil.retSpace(req.getParameter("buType")));
            pstmt.setString(23, CommUtil.retSpace(req.getParameter("permitBranch")));
            pstmt.setString(24, CommUtil.retSpace(req.getParameter("idCharge")));
            pstmt.setString(25, "N");
            pstmt.setInt(26, Integer.parseInt(req.getParameter("groupNo")));
            pstmt.setInt(27, Integer.parseInt(req.getParameter("departmentNo")));
            pstmt.setString(28, recept);
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
    
    public void updateGigwanMasterBackup(final String recept, final String g2bCode, final String license, final String recept1, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " update UM_REC_PUB_INSTITU_MAST\t\t\t\n    set INSTITU_CD    = ?,\t\t\t\t\t\t\n        INSTITU_FULL_NM = ?,\t\t\t\t\t\t\n        INSTITU_SHORT_NM = ?,\t\t\t\t\t\t\n        INSTITU_EN_NM = ?,\t\t\t\t\t\t\n        BIZ_REG_NO  = ?,\t\t\t\t\t\t\n        BIZ_CONDITION            = ?,\t\t\t\t\t\t\t\n        BIZ_SALE_CAT            = ?,\t\t\t\t\t\t\t\n        CHRGR_NM        = ?,\t\t\t\t\t\t\t\n        CHRGR_DEPART    = ?,\t\t\t\t\t\t\n        CHRGR_PHONE_NO  = ?,\t\t\t\t\t\t\n        CHRGR_FAX  = ?,\t\t\t\t\t\t\n        CHRGR_EMAIL  = ?,\t\t\t\t\t\t\n        CREDITOR_NM        = ?,\t\t\t\t\t\t\t\n        ZIP_CD        = ?,\t\t\t\t\t\t\t\n        ADDR            = ?,\t\t\t\t\t\t\t\n        DETAIL_ADDR      = ?,\t\t\t\t\t\t\t\n        PHONE_NO        = ?,\t\t\t\t\t\t\t\n        FAX        = ?,\t\t\t\t\t\t\t\n        GOODS_MNG_NM    = ?,\t\t\t\t\t\t\n        WEBSITE    = ?,\t\t\t\t\t\t\n        RECV_DT        = sysdate,\t\t\t\t\t\n        CERT_INSTITU    = ?,\t\t\t\t\t\t\n        APP_REQ_YN        = ?,\t\t\t\t\t\t\t\n        PERMIT_BRANCH        = ?,\t\t\t\t\t\t\t\n        REG_YN        = ?,\t\t\t\t\t\t\t\n        CERT_RECV_NO  = ?,\t\t\t\t\t\t\n        INSTITU_TYPE        = ?,\t\t\t\t\t\t\t\n        INSTITU_CLS        = ?,\t\t\t\t\t\t\t\n        INSTITU_CLS_M     = ?,\t\t\t\t\t\t\n        INSTITU_CLS_S     = ?,\t\t\t\t\t\t\n        MOBILE     = ?,\t\t\t\t\t\t\t\t\n        SMS_RECV_YN     = ?\t\t\t\t\t\t\n         where RECV_NO = ?\t\t\t\t\t\t";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, g2bCode);
            pstmt.setString(2, CommUtil.retSpace(req.getParameter("goNameFull")));
            pstmt.setString(3, CommUtil.retSpace(req.getParameter("goNameShort")));
            pstmt.setString(4, CommUtil.retSpace(req.getParameter("goNameEn")));
            pstmt.setString(5, CommUtil.retSpace(req.getParameter("saupNo")));
            pstmt.setString(6, CommUtil.retSpace(req.getParameter("buConditon")));
            pstmt.setString(7, CommUtil.retSpace(req.getParameter("buType")));
            pstmt.setString(8, CommUtil.retSpace(req.getParameter("taskmaster")));
            pstmt.setString(9, CommUtil.retSpace(req.getParameter("masterPost")));
            pstmt.setString(10, String.valueOf(CommUtil.retSpace(req.getParameter("mTel_1"))) + "-" + CommUtil.retSpace(req.getParameter("mTel_2")));
            pstmt.setString(11, String.valueOf(CommUtil.retSpace(req.getParameter("mFax_1"))) + "-" + CommUtil.retSpace(req.getParameter("mFax_2")));
            pstmt.setString(12, CommUtil.retSpace(req.getParameter("masterMail")));
            pstmt.setString(13, CommUtil.retSpace(req.getParameter("creditName")));
            pstmt.setString(14, cu.replace(req.getParameter("ZIPCODE"), "-", ""));
            pstmt.setString(15, CommUtil.retSpace(req.getParameter("ADDR")));
            pstmt.setString(16, CommUtil.retSpace(req.getParameter("address2")));
            pstmt.setString(17, String.valueOf(CommUtil.retSpace(req.getParameter("tel_1"))) + "-" + CommUtil.retSpace(req.getParameter("tel_2")));
            pstmt.setString(18, String.valueOf(CommUtil.retSpace(req.getParameter("fax_1"))) + "-" + CommUtil.retSpace(req.getParameter("fax_2")));
            pstmt.setString(19, CommUtil.retSpace(req.getParameter("goodsMaster")));
            pstmt.setString(20, CommUtil.retSpace(req.getParameter("homepage")));
            pstmt.setString(21, CommUtil.retSpace(req.getParameter("lic")));
            pstmt.setString(22, license);
            pstmt.setString(23, CommUtil.retSpace(req.getParameter("perOff")));
            pstmt.setString(24, "N");
            pstmt.setString(25, recept1);
            if (CommUtil.retSpace(req.getParameter("relation")).equals("51") || CommUtil.retSpace(req.getParameter("relation")).equals("52") || CommUtil.retSpace(req.getParameter("relation")).equals("53")) {
                pstmt.setString(26, "05");
            }
            else if (CommUtil.retSpace(req.getParameter("relation")).equals("71") || CommUtil.retSpace(req.getParameter("relation")).equals("72")) {
                pstmt.setString(26, "07");
            }
            else {
                pstmt.setString(26, CommUtil.retSpace(req.getParameter("relation")));
            }
            pstmt.setString(27, CommUtil.retSpace(req.getParameter("orgDiv1")));
            pstmt.setString(28, CommUtil.retSpace(req.getParameter("orgDiv2")));
            pstmt.setString(29, CommUtil.retSpace(req.getParameter("orgDiv3")));
            String HP = req.getParameter("HP");
            if (HP == null || HP.equals("")) {
                HP = " ";
            }
            pstmt.setString(30, HP);
            pstmt.setString(31, CommUtil.retSpace(req.getParameter("smsCheck")));
            pstmt.setString(33, recept);
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
    
    public void updateGigwanCreditTable(final String recept, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " delete from UM_REC_PUB_INSTITU_ACCT_CD where RECV_NO = ? ";
        final String sql2 = " insert into UM_REC_PUB_INSTITU_ACCT_CD (RECV_NO, INSTITU_ACCT_CD) values(?,  ?)";
        String code = null;
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, recept);
            pstmt.executeUpdate();
            pstmt.clearParameters();
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
            pstmt = con.prepareStatement(sql2);
            final StringTokenizer st = new StringTokenizer(CommUtil.retSpace(req.getParameter("mathCode")), ",");
            while (st.hasMoreTokens()) {
                code = st.nextToken();
                pstmt.setString(1, recept);
                pstmt.setString(2, code);
                pstmt.executeUpdate();
                pstmt.clearParameters();
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
    
    public void deleteGigwanSertTable(final String recept1, final String g2bCode, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " delete UM_REC_PUB_INSTITU_CERT where RECV_NO = ? ";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, recept1);
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
    
    public void updateGigwanSertTable(final String recept1, final String g2bCode, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " update UM_REC_PUB_INSTITU_CERT\t\t\n    set SMS_RECV_YN    = ?,\t\t\t\t\t\n        INSTITU_FULL_NM = ?,\t\t\t\t\t\n        CHRGR_MN = ?,\t\t\t\t\t\n        CHRGR_DEPART          = ?,\t\t\t\t\t\t\t\n        CHRGR_PHONE_NO  = ?,\t\t\t\t\t\n        CHRGR_EMAIL        = ?,\t\t\t\t\t\t\n        ZIP_CD            = ?,\t\t\t\t\t\t\t\n        ADDR      = ?,\t\t\t\t\t\t\n        DETAIL_ADDR  = ?,\t\t\t\t\t\n        FAX        = ?,\t\t\t\t\t\t\n        RECV_DT        = sysdate,\t\t\t\t\t\t\n        INSTITU_CD    = ?,\t\t\t\t\t\t\n        INSTITU_EN_NM  = ?,\t\t\t\t\t\n        BIZ_REG_NO  = ?,\t\t\t\t\t\n        WEBSITE  = ?,\t\t\t\t\t\n        MOBILE          = ?,\t\t\t\t\t\t\t\n        PERMIT_BRANCH        = ?,\t\t\t\t\t\t\n        REPR_NM          = ?,\t\t\t\t\t\t\t\n        REPR_IDENT_NO    = ?,\t\t\t\t\t\t\n        CHRGR_IDENT_NO        = ?,\t\t\t\t\n        USER_ID        = ?,\t\t\t\t\t\t\n        CERT_CLS        = ?,\t\t\t\t\t\t\n        CERT_INSTITU        = ?,\t\t\t\t\t\t\n        REG_YN        = ?\t\t\t\t\t\n  where RECV_NO = ? ";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "N");
            pstmt.setString(2, CommUtil.retSpace(req.getParameter("goNameFull")));
            pstmt.setString(3, CommUtil.retSpace(req.getParameter("taskmaster")));
            pstmt.setString(4, CommUtil.retSpace(req.getParameter("masterPost")));
            pstmt.setString(5, CommUtil.retSpace(req.getParameter("personTel")));
            pstmt.setString(6, CommUtil.retSpace(req.getParameter("masterMail")));
            pstmt.setString(7, CommUtil.retSpace(req.getParameter("postNo")));
            pstmt.setString(8, CommUtil.retSpace(req.getParameter("ADDR")));
            pstmt.setString(9, CommUtil.retSpace(req.getParameter("address2")));
            pstmt.setString(10, CommUtil.retSpace(req.getParameter("comFax")));
            pstmt.setString(11, CommUtil.retSpace(req.getParameter("g2bCode")));
            pstmt.setString(12, CommUtil.retSpace(req.getParameter("goNameEn")));
            pstmt.setString(13, CommUtil.retSpace(req.getParameter("saupNo")));
            pstmt.setString(14, CommUtil.retSpace(req.getParameter("homepage")));
            pstmt.setString(15, CommUtil.retSpace(req.getParameter("comTel")));
            pstmt.setString(16, CommUtil.retSpace(req.getParameter("permitBranch")));
            pstmt.setString(17, CommUtil.retSpace(req.getParameter("IDENT")));
            pstmt.setString(18, CommUtil.retSpace(req.getParameter("registno1")));
            pstmt.setString(19, CommUtil.retSpace(req.getParameter("idCharge")));
            pstmt.setString(20, CommUtil.retSpace(req.getParameter("USRID")));
            pstmt.setString(21, "B");
            pstmt.setString(22, CommUtil.retSpace(req.getParameter("choiceCA")));
            pstmt.setString(23, "N");
            pstmt.setString(24, recept1);
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
    
    public void updateGigwanSertTableBackup(final String recept1, final String g2bCode, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " update UM_REC_PUB_INSTITU_CERT\t\t\n    set INSTITU_CD    = ?,\t\t\t\t\t\n        INSTITU_FULL_NM = ?,\t\t\t\t\t\n        INSTITU_EN_NM = ?,\t\t\t\t\t\n        REPR_NM          = ?,\t\t\t\t\t\t\t\n        REPR_IDENT_NO  = ?,\t\t\t\t\t\n        ZIP_CD        = ?,\t\t\t\t\t\t\n        ADDR            = ?,\t\t\t\t\t\t\t\n        DETAIL_ADDR      = ?,\t\t\t\t\t\t\n        BIZ_REG_NO  = ?,\t\t\t\t\t\n        FAX        = ?,\t\t\t\t\t\t\n        CHRGR_NM        = ?,\t\t\t\t\t\t\n        CHRGR_DEPART    = ?,\t\t\t\t\t\t\n        CHRGR_IDENT_NO  = ?,\t\t\t\t\t\n        CHRGR_PHONE_NO  = ?,\t\t\t\t\t\n        CHRGR_EMAIL  = ?,\t\t\t\t\t\n        MOBILE          = ?,\t\t\t\t\t\t\t\n        USER_ID        = ?,\t\t\t\t\t\t\n        NICKNAME          = ?,\t\t\t\t\t\t\t\n        CERT_INSTITU    = ?,\t\t\t\t\t\t\n        RECV_DT        = sysdate,\t\t\t\t\n        WEBSITE        = ?,\t\t\t\t\t\t\n        APPROVE_REQ_CD    = ?,\t\t\t\t\t\t\n        PERMIT_BRANCH        = ?,\t\t\t\t\t\t\n        REG_YN        = ?,\t\t\t\t\t\t\n        SMS_RECV_YN        = ?\t\t\t\t\t\n  where RECV_NO = ? ";
        final CommUtil cu = new CommUtil();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, g2bCode);
            pstmt.setString(2, CommUtil.retSpace(req.getParameter("goNameFull")));
            pstmt.setString(3, CommUtil.retSpace(req.getParameter("goNameEn")));
            pstmt.setString(4, CommUtil.retSpace(req.getParameter("IDENT")));
            pstmt.setString(5, CommUtil.retSpace(req.getParameter("IDENTJUMIN")));
            pstmt.setString(6, cu.replace(req.getParameter("ZIPCODE"), "-", ""));
            pstmt.setString(7, CommUtil.retSpace(req.getParameter("ADDR")));
            pstmt.setString(8, CommUtil.retSpace(req.getParameter("address2")));
            pstmt.setString(9, CommUtil.retSpace(req.getParameter("saupNo")));
            pstmt.setString(10, String.valueOf(CommUtil.retSpace(req.getParameter("fax_1"))) + "-" + CommUtil.retSpace(req.getParameter("fax_2")));
            pstmt.setString(11, CommUtil.retSpace(req.getParameter("taskmaster")));
            pstmt.setString(12, CommUtil.retSpace(req.getParameter("masterPost")));
            pstmt.setString(13, CommUtil.retSpace(req.getParameter("JUMIN")));
            pstmt.setString(14, String.valueOf(CommUtil.retSpace(req.getParameter("mTel_1"))) + "-" + CommUtil.retSpace(req.getParameter("mTel_2")));
            pstmt.setString(15, CommUtil.retSpace(req.getParameter("masterMail")));
            String HP = req.getParameter("HP");
            if (HP == null || HP.equals("")) {
                HP = " ";
            }
            pstmt.setString(16, HP);
            pstmt.setString(17, CommUtil.retSpace(req.getParameter("USRID")));
            pstmt.setString(18, "Hawk");
            pstmt.setString(19, CommUtil.retSpace(req.getParameter("lic")));
            pstmt.setString(20, CommUtil.retSpace(req.getParameter("homepage")));
            pstmt.setString(21, String.valueOf(recept1.substring(2, recept1.length())) + CommUtil.retSpace(req.getParameter("USRID")));
            pstmt.setString(22, CommUtil.retSpace(req.getParameter("perOff")));
            pstmt.setString(23, "N");
            pstmt.setString(24, CommUtil.retSpace(req.getParameter("smsCheck")));
            pstmt.setString(25, recept1);
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
    
    public void hiddenAllCTS_um_pub_institu_mast() throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        final int count = 0;
        sql = "  update um_pub_institu_mast set hidden_yn = 'Y' where test_option_yn = 'Y' and hidden_yn = 'N' and institu_cd !='Z001114'";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.errors(this, exc, exc.toString());
            throw exc;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
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
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
    }
    
    public void hiddenAllCTS_um_rec_pub_institu_mast() throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        final int count = 0;
        sql = "  update usemn.um_rec_pub_institu_mast set hidden_yn = 'Y' where hidden_yn = 'N' and institu_cd !='Z001114'";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.errors(this, exc, exc.toString());
            throw exc;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
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
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
    }
    
    public void hiddenAllCTS_um_supplier_enter_mast() throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        final int count = 0;
        sql = "   update um_supplier_enter_mast set hidden_yn = 'Y' where test_option_yn = 'Y' and hidden_yn = 'N' and biz_reg_no !='34666333425' and biz_reg_no !='3355245767675' ";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.errors(this, exc, exc.toString());
            throw exc;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
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
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
    }
    
    public int hiddenAllCTS_um_rec_supplier_enter_mast() throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        int count = 0;
        sql = "  update usemn.um_rec_pub_institu_mast set hidden_yn = 'Y' where hidden_yn = 'N' and institu_cd !='Z001114'";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.errors(this, exc, exc.toString());
            throw exc;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
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
    }
    
    public static void main(final String[] asd) {
    }
}
