// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import common.ComStr;
import java.sql.ResultSetMetaData;
import entity.UM_GOE_ConiC011b;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_GOE_ConiC010b;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_ConrC020c extends HttpServlet
{
    String saupNo;
    String nation;
    int currentMode;
    public final int MODE_DP = 100;
    public final int MODE_GM = 200;
    public final int MODE_GJ = 400;
    public final int MODE_ID = 500;
    public final int MODE_JP = 600;
    public final int MODE_GP = 700;
    public final int MODE_BR = 800;
    public final int MODE_DP1 = 1100;
    public final int MODE_GM1 = 1200;
    public final int MODE_GJ1 = 1400;
    public final int MODE_ID1 = 1500;
    public final int MODE_JP1 = 1600;
    public final int MODE_GP1 = 1700;
    public final int MODE_BR1 = 1800;
    
    public String[] getParams(final HttpServletRequest req, final String param, final String init) throws UnsupportedEncodingException {
        String[] res = (String[])null;
        res = req.getParameterValues(param);
        if (res != null) {
            for (int i = 0; i < res.length; ++i) {
                if (res[i] == null || res[i].equals("null") || res[i].equals("")) {
                    res[i] = init;
                }
                else {
                    res[i] = new String(res[i]);
                }
            }
        }
        return res;
    }
    
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
    }
    
    public UM_GOE_ConiC010b select_main(final String saupNo) {
        UM_GOE_ConiC010b ett = null;
        final UM_GOE_ConiC010b[] dpett = (UM_GOE_ConiC010b[])null;
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt4 = null;
        final PreparedStatement dppsmt4 = null;
        ResultSet rs = null;
        String query4 = null;
        final String dpquery4 = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            query4 = " SELECT  사업자등록번호, 국적, 상호명, 영문상호명,  TO_CHAR(개업일자, 'YYYY-MM-DD'), TO_CHAR(법인설립일자, 'YYYY-MM-DD'), 업무구분, 제조구분, 대표업종코드_표준,  법인등록번호, 기업구분1, 기업구분2, 기업구분해당년도, 자본금,  종업원수, 최근결산년월, 우편번호, 지역코드, 주소,  나머지주소, 전화번호, FAX번호, 홈페이지, 특례해당여부,  등록유효일자, 등록일자, 갱신일자, 대표인증여부, 처리자ID  FROM 사용_조달업체마스터  WHERE 사업자등록번호 = '" + saupNo + "' ";
            psmt4 = con.prepareStatement(query4);
            rs = psmt4.executeQuery();
            psmt4.clearParameters();
            while (rs.next()) {
                ett = new UM_GOE_ConiC010b();
                ett.setSaupNo(isStrNull(rs.getString(1)));
                ett.setNationality(isStrNull(rs.getString(2)));
                ett.setSangho(isStrNull(rs.getString(3)));
                ett.setESangho(isStrNull(rs.getString(4)));
                ett.setOpenDate(isStrNull(rs.getString(5)));
                ett.setBubinOpenDate(isStrNull(rs.getString(6)));
                ett.setJobGubun(isStrNull(rs.getString(7)));
                ett.setMakeGubun(isStrNull(rs.getString(8)));
                ett.setDpUpjongCode(isStrNull(rs.getString(9)));
                ett.setBubinNo(isStrNull(rs.getString(10)));
                ett.setComGubun1(isStrNull(rs.getString(11)));
                ett.setComGubun2(isStrNull(rs.getString(12)));
                ett.setComGubunYear(isStrNull(rs.getString(13)));
                ett.setJabon(rs.getLong(14));
                ett.setEmployeeNo(rs.getInt(15));
                ett.setAccountDate(isStrNull(rs.getString(16)));
                ett.setZipCode(isStrNull(rs.getString(17)));
                ett.setLocalCode(isStrNull(rs.getString(18)));
                ett.setAddr(isStrNull(rs.getString(19)));
                ett.setRestAddr(isStrNull(rs.getString(20)));
                ett.setTel(isStrNull(rs.getString(21)));
                ett.setFax(isStrNull(rs.getString(22)));
                ett.setHomepage(isStrNull(rs.getString(23)));
                ett.setExceptYN(isStrNull(rs.getString(24)));
                ett.setRegistOkDate(isStrNull(rs.getString(25)));
                ett.setRegistDate(isStrNull(rs.getString(26)));
                ett.setRenewDate(isStrNull(rs.getString(27)));
                ett.setDpOkYN(isStrNull(rs.getString(28)));
                ett.setChurijaId(isStrNull(rs.getString(29)));
            }
            rs.close();
            psmt4.close();
            con.close();
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrD020c.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrD020c.select_user block Exception : ");
            Log.debug("Exception발생 사유 : " + exc.toString());
            exc.printStackTrace();
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt4 != null) {
                try {
                    psmt4.close();
                }
                catch (Exception ex2) {}
            }
            if (trx != null) {
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
        if (psmt4 != null) {
            try {
                psmt4.close();
            }
            catch (Exception ex5) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ett;
    }
    
    public UM_GOE_ConiC011b select_main1(final String saupNo) {
        UM_GOE_ConiC011b ett = null;
        final UM_GOE_ConiC011b[] dpett = (UM_GOE_ConiC011b[])null;
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt4 = null;
        final PreparedStatement dppsmt4 = null;
        ResultSet rs = null;
        String query4 = null;
        final String dpquery4 = null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            query4 = " SELECT  사업자등록번호, 국적, 상호명, 영문상호명,  TO_CHAR(개업일자, 'YYYY-MM-DD'), TO_CHAR(법인설립일자, 'YYYY-MM-DD'), 업무구분, 제조구분, 대표업종코드_표준,  법인등록번호, 기업구분1, 기업구분2, 기업구분해당년도, 자본금,  종업원수, 최근결산년월, 우편번호, 지역코드, 주소,  나머지주소, 전화번호, FAX번호, 홈페이지, 특례해당여부,  등록유효일자, 등록일자, 갱신일자, 대표인증여부, 처리자ID  FROM 사용_접수조달업체마스터  WHERE 사업자등록번호 = '" + saupNo + "' ";
            psmt4 = con.prepareStatement(query4);
            rs = psmt4.executeQuery();
            psmt4.clearParameters();
            while (rs.next()) {
                ett = new UM_GOE_ConiC011b();
                ett.setSaupNo(isStrNull(rs.getString(1)));
                ett.setNationality(isStrNull(rs.getString(2)));
                ett.setSangho(isStrNull(rs.getString(3)));
                ett.setESangho(isStrNull(rs.getString(4)));
                ett.setOpenDate(isStrNull(rs.getString(5)));
                ett.setBubinOpenDate(isStrNull(rs.getString(6)));
                ett.setJobGubun(isStrNull(rs.getString(7)));
                ett.setMakeGubun(isStrNull(rs.getString(8)));
                ett.setDpUpjongCode(isStrNull(rs.getString(9)));
                ett.setBubinNo(isStrNull(rs.getString(10)));
                ett.setComGubun1(isStrNull(rs.getString(11)));
                ett.setComGubun2(isStrNull(rs.getString(12)));
                ett.setComGubunYear(isStrNull(rs.getString(13)));
                ett.setJabon(rs.getLong(14));
                ett.setEmployeeNo(rs.getInt(15));
                ett.setAccountDate(isStrNull(rs.getString(16)));
                ett.setZipCode(isStrNull(rs.getString(17)));
                ett.setLocalCode(isStrNull(rs.getString(18)));
                ett.setAddr(isStrNull(rs.getString(19)));
                ett.setRestAddr(isStrNull(rs.getString(20)));
                ett.setTel(isStrNull(rs.getString(21)));
                ett.setFax(isStrNull(rs.getString(22)));
                ett.setHomepage(isStrNull(rs.getString(23)));
                ett.setExceptYN(isStrNull(rs.getString(24)));
                ett.setRegistOkDate(isStrNull(rs.getString(25)));
                ett.setRegistDate(isStrNull(rs.getString(26)));
                ett.setRenewDate(isStrNull(rs.getString(27)));
                ett.setDpOkYN(isStrNull(rs.getString(28)));
                ett.setChurijaId(isStrNull(rs.getString(29)));
            }
            rs.close();
            psmt4.close();
            con.close();
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrD020c.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrD020c.select_user block Exception : ");
            Log.debug("Exception발생 사유 : " + exc.toString());
            exc.printStackTrace();
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (psmt4 != null) {
                try {
                    psmt4.close();
                }
                catch (Exception ex2) {}
            }
            if (trx != null) {
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
        if (psmt4 != null) {
            try {
                psmt4.close();
            }
            catch (Exception ex5) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ett;
    }
    
    public static int isIntNull(final String value) {
        return (value == null) ? 0 : Integer.parseInt(value.trim());
    }
    
    public static String isStrNull(final String value) {
        return (value == null) ? "NULL" : value.trim();
    }
    
    public void setSaupNo(final String saupNo) {
        this.saupNo = saupNo;
    }
    
    public void setCurrentMode(final int currentMode) {
        this.currentMode = currentMode;
    }
    
    public String[][] getDPData() {
        this.setCurrentMode(100);
        final String[][] dpData = this.getList();
        return dpData;
    }
    
    public String[][] getDP1Data() {
        this.setCurrentMode(1100);
        final String[][] dp1Data = this.getList();
        return dp1Data;
    }
    
    public String[][] getGMData() {
        this.setCurrentMode(200);
        final String[][] gmData = this.getList();
        return gmData;
    }
    
    public String[][] getGM1Data() {
        this.setCurrentMode(1200);
        final String[][] gm1Data = this.getList();
        return gm1Data;
    }
    
    public String[][] getGJData() {
        this.setCurrentMode(400);
        final String[][] gjData = this.getList();
        return gjData;
    }
    
    public String[][] getGJ1Data() {
        this.setCurrentMode(1400);
        final String[][] gj1Data = this.getList();
        return gj1Data;
    }
    
    public String[][] getIDData() {
        this.setCurrentMode(500);
        final String[][] idData = this.getList();
        return idData;
    }
    
    public String[][] getID1Data() {
        this.setCurrentMode(1500);
        final String[][] id1Data = this.getList();
        return id1Data;
    }
    
    public String[][] getBRData() {
        this.setCurrentMode(800);
        final String[][] brData = this.getList();
        return brData;
    }
    
    public String[][] getBR1Data() {
        this.setCurrentMode(1800);
        final String[][] br1Data = this.getList();
        return br1Data;
    }
    
    public String[][] getJPData() {
        this.setCurrentMode(600);
        final String[][] jpData = this.getList();
        return jpData;
    }
    
    public String[][] getJP1Data() {
        this.setCurrentMode(1600);
        final String[][] jp1Data = this.getList();
        return jp1Data;
    }
    
    public String[][] getGPData() {
        this.setCurrentMode(700);
        final String[][] gpData = this.getList();
        return gpData;
    }
    
    public String[][] getGP1Data() {
        this.setCurrentMode(1700);
        final String[][] gp1Data = this.getList();
        return gp1Data;
    }
    
    public String[][] getList() {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String[][] returnList = (String[][])null;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            final String query = this.getQuery();
            psmt = this.getPsmt(query, con);
            psmt = this.setQueryCondition(psmt);
            rs = psmt.executeQuery();
            final ResultSetMetaData rsmd = rs.getMetaData();
            returnList = new String[this.getRowCount(rs)][this.getColumnCount(rs)];
            int i = 0;
            while (rs.next()) {
                for (int j = 0; j < this.getColumnCount(rs); ++j) {
                    returnList[i][j] = ((rs.getString(j + 1) == null) ? "" : rs.getString(j + 1));
                }
                ++i;
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrD020c.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrD020c.select_user block Exception : ");
            Log.debug("Exception발생 사유 : " + exc.toString());
            exc.printStackTrace();
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
            if (trx != null) {
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
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return returnList;
    }
    
    public String getQuery() {
        String query = "";
        switch (this.currentMode) {
            case 100: {
                query = " SELECT  대표자명,  대표자주민번호,  대표자메일주소,  대표대표자여부,  대표자휴대폰 " + this.getQueryCondition();
                break;
            }
            case 1100: {
                query = " SELECT  대표자명,  대표자주민번호,  대표자메일주소,  대표대표자여부,  대표자휴대폰 " + this.getQueryCondition();
                break;
            }
            case 200: {
                query = " SELECT  A.면허코드,  B.코드명,  A.면허번호,  TO_CHAR(A.면허취득일자, 'YYYY-MM-DD'),  TO_CHAR(A.면허만료일자, 'YYYY-MM-DD'),  A.시공능력평가액,  A.평가액기준년도,  A.대표면허여부 " + this.getQueryCondition();
                break;
            }
            case 1200: {
                query = " SELECT  A.면허코드,  B.코드명,  A.면허번호,  TO_CHAR(A.면허취득일자, 'YYYY-MM-DD'),  TO_CHAR(A.면허만료일자, 'YYYY-MM-DD'),  A.시공능력평가액,  A.평가액기준년도,  A.대표면허여부  " + this.getQueryCondition();
                break;
            }
            case 400: {
                query = " SELECT  공장명,  공장우편번호,  공장주소,  공장나머지주소,  공장전화번호,  공장FAX번호 , 일련번호,  공장임대여부,  to_char(공장임대시작일자, 'yyyy-mm-dd'),  to_char(공장임대종료일자, 'yyyy-mm-dd') " + this.getQueryCondition();
                break;
            }
            case 1400: {
                query = " SELECT  공장명,  공장우편번호,  공장주소,  공장나머지주소,  공장전화번호,  공장FAX번호 , 일련번호,  공장임대여부,  to_char(공장임대시작일자, 'yyyy-mm-dd'),  to_char(공장임대종료일자, 'yyyy-mm-dd') " + this.getQueryCondition();
                break;
            }
            case 500: {
                query = " SELECT  주민등록번호,  성명,  부서,  직책명,  전화번호,  E_MAIL,  휴대폰,  FAX,  입찰대리인확인여부 " + this.getQueryCondition();
                break;
            }
            case 1500: {
                query = " SELECT  주민등록번호,  성명,  부서,  직책명,  전화번호,  E_MAIL,  휴대폰,  FAX,  입찰대리인확인여부 " + this.getQueryCondition();
                break;
            }
            case 800: {
                query = "\t\tSELECT a.사업자등록번호, 상호명, 우편번호, 지역코드, 주소, 나머지주소, 전화번호, FAX번호,\t\t\t\t   \t\t\t\t대표자명, 대표자주민번호, DECODE(c.상태구분,'07','Y') 상태구분\t\t\t\t\t\t\t\t\t\t\t" + this.getQueryCondition();
                break;
            }
            case 1800: {
                query = "\t\tSELECT a.사업자등록번호, 상호명, 우편번호, 지역코드, 주소, 나머지주소, 전화번호, FAX번호,\t\t\t\t   \t\t\t\t대표자명, 대표자주민번호, DECODE(c.상태구분,'07','Y') 상태구분\t\t\t\t\t\t\t\t\t\t\t" + this.getQueryCondition();
                break;
            }
            case 600: {
                query = " SELECT DISTINCT  A.물품분류번호,  A.형식승인번호,  A.최근3년간_매출액,  A.제조여부,  A.대표물품여부,  B.분류명,  A.직접생산증명서류,  TO_CHAR(A.유효기간시작일자,'YYYYMMDD'),  TO_CHAR(A.유효기간종료일자,'YYYYMMDD'),  A.표준산업분류코드,  A.발급기관,  A.증서명 " + this.getQueryCondition();
                break;
            }
            case 1600: {
                query = " SELECT DISTINCT  A.물품분류번호,  A.형식승인번호,  A.최근3년간_매출액,  A.제조여부,  A.대표물품여부,  B.분류명,  A.직접생산증명서류,  TO_CHAR(A.유효기간시작일자,'YYYYMMDD'),  TO_CHAR(A.유효기간종료일자,'YYYYMMDD'),  A.표준산업분류코드,  A.발급기관,  A.증서명 " + this.getQueryCondition();
                break;
            }
            case 700: {
                query = " SELECT DISTINCT  A.물품분류번호,  A.형식승인번호,  A.최근3년간_매출액,  A.제조여부,  A.대표물품여부,  B.분류명 " + this.getQueryCondition();
                break;
            }
            case 1700: {
                query = " SELECT DISTINCT  A.물품분류번호,  A.형식승인번호,  A.최근3년간_매출액,  A.제조여부,  A.대표물품여부,  B.분류명 " + this.getQueryCondition();
                break;
            }
        }
        return query;
    }
    
    public String getQueryCondition() {
        String queryCondition = "";
        switch (this.currentMode) {
            case 100: {
                queryCondition = " FROM 사용_대표자  WHERE 사업자등록번호 = ? ";
                break;
            }
            case 1100: {
                queryCondition = " FROM 사용_접수대표자  WHERE 사업자등록번호 = ? ";
                break;
            }
            case 200: {
                queryCondition = " FROM 사용_면허사항 A, SYN_공동코드 B  WHERE A.사업자등록번호 = ?  AND B.코드구분 = 'GU9'  AND A.면허코드 = B.코드 ";
                break;
            }
            case 1200: {
                queryCondition = " FROM 사용_접수면허사항 A, SYN_공동코드 B  WHERE A.사업자등록번호 = ?  AND B.코드구분 = 'GU9'  AND A.면허코드 = B.코드 ";
                break;
            }
            case 400: {
                queryCondition = " FROM 사용_공장정보  WHERE 사업자등록번호 = ? ";
                break;
            }
            case 1400: {
                queryCondition = " FROM 사용_접수공장정보  WHERE 사업자등록번호 = ? ";
                break;
            }
            case 500: {
                queryCondition = " FROM 사용_입찰대리인  WHERE 사업자등록번호 = ? ";
                break;
            }
            case 1500: {
                queryCondition = " FROM 사용_접수입찰대리인  WHERE 사업자등록번호 = ? ";
                break;
            }
            case 800: {
                queryCondition = " FROM  사용_조달업체마스터 a, 사용_대표자 b, 사용_업체상태 c\t\t\t     WHERE a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t     AND a.사업자등록번호 = c.사업자등록번호(+)\t\t\t\t\t\t\t     AND c.상태구분(+) = '07'\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND b.대표대표자여부 = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.법인등록번호 IN (  SELECT 법인등록번호\t\t\t\t\t\t   \t\t\t\t\t\t\t\t       FROM 사용_조달업체마스터\t\t\t\t\t   \t\t\t\t\t\t\t\t        WHERE 사업자등록번호  =?)\t\t\t\t\t     AND 본사구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     ORDER BY a.사업자등록번호 ASC\t\t\t\t\t\t\t\t\t\t\t";
                break;
            }
            case 1800: {
                queryCondition = " FROM  사용_접수조달업체마스터 a, 사용_접수대표자 b, 사용_업체상태 c\t\t\t     WHERE a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t     AND a.사업자등록번호 = c.사업자등록번호(+)\t\t\t\t\t\t\t\t\t\t     AND c.상태구분(+) = '07'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND b.대표대표자여부 = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.법인등록번호 IN (  SELECT 법인등록번호\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t       FROM 사용_접수조달업체마스터\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t        WHERE 사업자등록번호  =?)\t\t\t\t\t\t\t\t     AND 본사구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     ORDER BY a.사업자등록번호 ASC\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                break;
            }
            case 600: {
                queryCondition = " FROM 사용_조달품목 A, SYN_VIEW_물품분류매핑 B  WHERE A.사업자등록번호 = ?  AND A.물품분류번호 = B.물품분류    AND A.제조여부 = 'Y' ";
                break;
            }
            case 1600: {
                queryCondition = " FROM 사용_접수조달품목 A, SYN_VIEW_물품분류매핑 B  WHERE A.사업자등록번호 = ?  AND A.물품분류번호 = B.물품분류    AND A.제조여부 = 'Y' ";
                break;
            }
            case 700: {
                queryCondition = " FROM 사용_조달품목 A, SYN_VIEW_물품분류매핑 B  WHERE A.사업자등록번호 = ?  AND A.물품분류번호 = B.물품분류    AND A.제조여부 = 'N' ";
                break;
            }
            case 1700: {
                queryCondition = " FROM 사용_접수조달품목 A, SYN_VIEW_물품분류매핑 B  WHERE A.사업자등록번호 = ?  AND A.물품분류번호 = B.물품분류    AND A.제조여부 = 'N' ";
                break;
            }
        }
        return queryCondition;
    }
    
    public PreparedStatement setQueryCondition(final PreparedStatement psmt) throws SQLException {
        switch (this.currentMode) {
            case 100: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 200: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 400: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 500: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 800: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 600: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 700: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 1100: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 1200: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 1400: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 1500: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 1800: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 1600: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 1700: {
                psmt.setString(1, this.saupNo);
                break;
            }
        }
        return psmt;
    }
    
    public void setParameters(final HttpServletRequest req, final HttpServletResponse res) {
        this.saupNo = ((req.getParameter("saupNo") == null) ? "-1" : req.getParameter("saupNo"));
    }
    
    public PreparedStatement getPsmt(final String query, final Connection con) throws SQLException {
        PreparedStatement psmt = null;
        psmt = con.prepareStatement(query, 1004, 1007);
        return psmt;
    }
    
    public int getRowCount(final ResultSet rs) throws SQLException {
        int rowNum = 0;
        if (rs == null) {
            return 0;
        }
        if (rs.last()) {
            rowNum = rs.getRow();
        }
        rs.beforeFirst();
        return rowNum;
    }
    
    public int getColumnCount(final ResultSet rs) throws SQLException {
        int columnCount = 0;
        if (rs == null) {
            return 0;
        }
        final ResultSetMetaData rsmd = rs.getMetaData();
        columnCount = rsmd.getColumnCount();
        return columnCount;
    }
    
    public int saupstatValues(String saupNo) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        saupNo = ComStr.replace(saupNo, "-", "");
        try {
            final String sql = " SELECT COUNT(*) FROM UM_ENTER_STATE                           WHERE BIZ_REG_NO='" + saupNo + "'\t\t\t\t\t\t\t   " + " AND STATE_CLS = '07' AND MANAGER_ID = 'ADMIN'                     ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_LicenseEndDate.java .saupstatValues('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_LicenseEndDate.java .saupstatValues('" + saupNo + "'):" + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count;
    }
}
