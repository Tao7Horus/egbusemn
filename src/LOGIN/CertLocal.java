// 
// Decompiled by Procyon v0.5.30
// 

package LOGIN;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ComDbQuery;
import common.CommEntity;
import common.Log;
import common.OneRowEntity;
import common.Trx;
import common.util.CommUtil;

public class CertLocal
{
    public static String[] certvalue;
    public static String[] idvalue;
    
    static {
        CertLocal.certvalue = new String[] { "id", "gubun", "mcode", "name", "mname", "telnum", "email", "certseq", "bOffi", "sgcode", "lognum", "ip", "personID", "personName", "ptel", "pemail" };
        CertLocal.idvalue = new String[] { "id", "gtype", "mcode", "name", "mname", "telnum", "email", "bOffi", "sgcode", "lognum", "ip", "personID", "personPW", "personName", "personMail", "personGubun" };
    }
    
    public String[] getIDPWD(final String origin) throws Exception {
        final String[] bank = new String[2];
        try {
            final StringTokenizer st = new StringTokenizer(origin, "&");
            while (st.hasMoreTokens()) {
                final String tmp = st.nextToken();
                if (tmp.startsWith("id=")) {
                    bank[0] = URLDecoder.decode(tmp.substring(3));
                }
                else {
                    if (!tmp.startsWith("pwd=")) {
                        continue;
                    }
                    bank[1] = URLDecoder.decode(tmp.substring(4));
                }
            }
        }
        catch (Exception e) {
            Log.errors(String.valueOf(this.getClass().getName()) + ".getIDPWD() :origin[" + origin + "]:" + e.toString());
            throw e;
        }
        return bank;
    }
    
    public String getDN(final String origin) throws Exception {
        try {
            if (origin.startsWith("dn=")) {
                return URLDecoder.decode(origin.substring(3));
            }
            return null;
        }
        catch (Exception e) {
            Log.errors(String.valueOf(this.getClass().getName()) + ".getDN() :origin[" + origin + "]:" + e.toString());
            throw e;
        }
    }
    
    public String getStringToken(final String[] values, final String loginType) {
        if (values == null) {
            return null;
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
        final GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"), Locale.US);
        calendar.add(10, 3);
        final StringBuffer strToken = new StringBuffer();
        strToken.append("expire=" + URLEncoder.encode(dateFormat.format(calendar.getTime())) + URLEncoder.encode(timeFormat.format(calendar.getTime())));
        if (loginType.equals("I")) {
            if (values.length == CertLocal.idvalue.length) {
                for (int i = 0; i < CertLocal.idvalue.length; ++i) {
                    if (values[i] != null) {
                        strToken.append("&" + CertLocal.idvalue[i] + "=" + URLEncoder.encode(values[i]));
                    }
                }
                return String.valueOf(strToken.toString()) + "&ltype=I";
            }
        }
        else if (loginType.equals("C") && values.length == CertLocal.certvalue.length) {
            for (int i = 0; i < CertLocal.certvalue.length; ++i) {
                if (values[i] != null) {
                    strToken.append("&" + CertLocal.certvalue[i] + "=" + URLEncoder.encode(values[i]));
                }
            }
            return String.valueOf(strToken.toString()) + "&ltype=C";
        }
        return null;
    }
    
    public void setCookies(final HttpServletResponse res, final String comment, final String domain, final int maxAge, final String path, final boolean secure, final int version, final String[] values, final boolean encoding) throws Exception {
        try {
            if (values == null) {
                throw new NullPointerException("Can't be null");
            }
            final Cookie[] cookies = new Cookie[values.length];
            cookies[0] = new Cookie("_SLT", encoding ? URLEncoder.encode(values[0]) : values[0]);
            cookies[1] = new Cookie("_SLTK", encoding ? URLEncoder.encode(values[1]) : values[1]);
            cookies[2] = new Cookie("_SLTS", encoding ? URLEncoder.encode(values[2]) : values[2]);
            for (int i = cookies.length - 1; i >= 0; --i) {
                if (comment != null) {
                    cookies[i].setComment(comment);
                }
                if (domain != null) {
                    cookies[i].setDomain(domain);
                }
                if (maxAge != -1) {
                    cookies[i].setMaxAge(maxAge);
                }
                if (path != null) {
                    cookies[i].setPath(path);
                }
                if (secure) {
                    cookies[i].setSecure(true);
                }
                if (version != 0) {
                    cookies[i].setVersion(version);
                }
                res.addCookie(cookies[i]);
            }
        }
        catch (Exception e) {
            Log.errors(String.valueOf(this.getClass().getName()) + ".setCookies():" + e.toString());
            throw e;
        }
    }
    
    public void setCookiesSubUser(final HttpServletResponse res, final String comment, final String domain, final int maxAge, final String path, final boolean secure, final int version, final String[] values, final boolean encoding) throws Exception {
        try {
            if (values == null) {
                throw new NullPointerException("Can't be null");
            }
            final Cookie[] cookies = new Cookie[values.length];
            cookies[0] = new Cookie("_SLT", encoding ? URLEncoder.encode(values[0]) : values[0]);
            cookies[1] = new Cookie("_SLTK", encoding ? URLEncoder.encode(values[1]) : values[1]);
            cookies[2] = new Cookie("_SLTS", encoding ? URLEncoder.encode(values[2]) : values[2]);
            cookies[3] = new Cookie("_SUID", encoding ? URLEncoder.encode(values[3]) : values[3]);
            for (int i = cookies.length - 1; i >= 0; --i) {
                if (comment != null) {
                    cookies[i].setComment(comment);
                }
                if (domain != null) {
                    cookies[i].setDomain(domain);
                }
                if (maxAge != -1) {
                    cookies[i].setMaxAge(maxAge);
                }
                if (path != null) {
                    cookies[i].setPath(path);
                }
                if (secure) {
                    cookies[i].setSecure(true);
                }
                if (version != 0) {
                    cookies[i].setVersion(version);
                }
                res.addCookie(cookies[i]);
            }
        }
        catch (Exception e) {
            Log.errors(String.valueOf(this.getClass().getName()) + ".setCookies():" + e.toString());
            throw e;
        }
    }
    
    public static String getIP(final HttpServletRequest req) throws ServletException, IOException {
        String IP = null;
        IP = req.getHeader("x-forwarded-for");
        if (IP == null) {
            IP = req.getHeader("WL-Proxy-Client-IP");
        }
        if (IP == null) {
            IP = req.getRemoteAddr();
        }
        if (IP == null) {
            IP = "IP조회못함";
        }
        return IP;
    }
    
    public CommEntity[] getCertUserInfo(final String DN, final String tempSql1, final String tempSql2, final Connection conn) throws Exception {
        final StringBuffer strbuff = new StringBuffer(1024);
        strbuff.append("\n  SELECT S1.id, S1.gubun, S1.mcode, S1.name, U3.INSTITU_FULL_NM mname ");
        strbuff.append("\n        , S1.phone, S1.mail, S1.seq, U3.LOCAL_INSTITU_DIV bOffi, U3.OLD_INSTITU_CD sgcode, U3.INSTITU_REGNO saup_no, nvl(U3.TEST_OPTION_YN, 'N') test_yn, PERMIT_YN, PASSWORD2, IS_LOCK ");
        strbuff.append("\n   FROM UM_PUB_INSTITU_MAST U3 ");
        strbuff.append("\n      , ( SELECT U1.USER_ID id, U1.USER_CLS gubun ");
        strbuff.append("\n               , U1.MAST_CD mcode, U1.CHRGR_NM name ");
        strbuff.append("\n               , U1.CHRGR_PHONE_NO phone, U1.CHRGR_EMAIL mail ");
//        strbuff.append("\n               , U2.TURN seq, nvl(U1.TEST_OPTION_YN, 'N') test_yn, U1.PERMIT_YN, U1.PASSWORD2, U1.IS_LOCK  ");//20160407 - Move TEST_OPTION_YN to UM_SUPPLIER_ENTER_MAST
        strbuff.append("\n               , U2.TURN seq, U1.PERMIT_YN, U1.PASSWORD2, U1.IS_LOCK  ");
        strbuff.append("\n            FROM UM_USER U1 ");
        strbuff.append("\n               , UM_CERT_INFO U2 ");
        strbuff.append("\n           WHERE U1.USER_ID = U2.USER_ID " + tempSql1);
        strbuff.append("\n             AND U2.CERT_NM = ?) S1 ");
        strbuff.append("\n  WHERE S1.mcode = U3.INSTITU_CD ");
        strbuff.append("\n  UNION ALL ");
        strbuff.append("\n SELECT S2.id, S2.gubun, S2.mcode, S2.name, U6.BIZ_NM mname ");
        strbuff.append("\n       , S2.phone, S2.mail, S2.seq, null bOffi, null sgcode, U6.BIZ_REG_NO saup_no, nvl(U6.TEST_OPTION_YN, 'N') test_yn, PERMIT_YN, PASSWORD2, IS_LOCK  ");
        strbuff.append("\n   FROM UM_SUPPLIER_ENTER_MAST U6 ");
        strbuff.append("\n      , ( SELECT U4.USER_ID id, U4.USER_CLS gubun ");
        strbuff.append("\n               , U4.MAST_CD mcode, U4.CHRGR_NM name ");
        strbuff.append("\n               , U4.CHRGR_PHONE_NO phone, U4.CHRGR_EMAIL mail ");
        //strbuff.append("\n               , U5.TURN seq, nvl(U4.TEST_OPTION_YN, 'N') test_yn, U4.PERMIT_YN, U4.PASSWORD2, U4.IS_LOCK "); //20160407 - Move TEST_OPTION_YN to UM_SUPPLIER_ENTER_MAST
        strbuff.append("\n               , U5.TURN seq, U4.PERMIT_YN, U4.PASSWORD2, U4.IS_LOCK ");
        strbuff.append("\n            FROM UM_USER U4 ");
        strbuff.append("\n               , UM_CERT_INFO U5 ");
        strbuff.append("\n           WHERE U4.USER_ID = U5.USER_ID " + tempSql2);
        strbuff.append("\n             AND U5.CERT_NM = ?) S2 ");
        strbuff.append("\n  WHERE S2.mcode = U6.BIZ_REG_NO ");
        final String[] parameter = { DN, DN };
        return new ComDbQuery().getList((Object)this, "USEMN", strbuff.toString(), parameter, conn);
    }
    
    public CommEntity[] getCertUserHidden(final String DN, final String tempSql1, final String tempSql2, final Connection conn) throws Exception {
        final StringBuffer strbuff = new StringBuffer(1024);
        strbuff.append("\n  SELECT S1.id, S1.gubun, S1.mcode, S1.name, U3.INSTITU_FULL_NM mname ");
        strbuff.append("\n        , S1.phone, S1.mail, S1.seq, U3.LOCAL_INSTITU_DIV bOffi, U3.OLD_INSTITU_CD sgcode, U3.INSTITU_REGNO saup_no, test_yn, PERMIT_YN, PASSWORD2, IS_LOCK ");
        strbuff.append("\n   FROM UM_PUB_INSTITU_MAST U3 ");
        strbuff.append("\n      , ( SELECT U1.USER_ID id, U1.USER_CLS gubun ");
        strbuff.append("\n               , U1.MAST_CD mcode, U1.CHRGR_NM name ");
        strbuff.append("\n               , U1.CHRGR_PHONE_NO phone, U1.CHRGR_EMAIL mail ");
        strbuff.append("\n               , U2.TURN seq, nvl(U1.TEST_OPTION_YN, 'N') test_yn, U1.PERMIT_YN, U1.PASSWORD2, U1.IS_LOCK  ");
        strbuff.append("\n            FROM UM_USER U1 ");
        strbuff.append("\n               , UM_CERT_INFO U2 ");
        strbuff.append("\n           WHERE U1.USER_ID = U2.USER_ID " + tempSql1);
        strbuff.append("\n             AND U2.CERT_NM = ?) S1 ");
        strbuff.append("\n  WHERE S1.mcode = U3.INSTITU_CD AND U3.HIDDEN_YN = 'N' ");
        strbuff.append("\n  UNION ALL ");
        strbuff.append("\n SELECT S2.id, S2.gubun, S2.mcode, S2.name, U6.BIZ_NM mname ");
        strbuff.append("\n       , S2.phone, S2.mail, S2.seq, null bOffi, null sgcode, U6.BIZ_REG_NO saup_no, test_yn, PERMIT_YN, PASSWORD2, IS_LOCK  ");
        strbuff.append("\n   FROM UM_SUPPLIER_ENTER_MAST U6 ");
        strbuff.append("\n      , ( SELECT U4.USER_ID id, U4.USER_CLS gubun ");
        strbuff.append("\n               , U4.MAST_CD mcode, U4.CHRGR_NM name ");
        strbuff.append("\n               , U4.CHRGR_PHONE_NO phone, U4.CHRGR_EMAIL mail ");
        strbuff.append("\n               , U5.TURN seq, nvl(U4.TEST_OPTION_YN, 'N') test_yn, U4.PERMIT_YN, U4.PASSWORD2, U4.IS_LOCK ");
        strbuff.append("\n            FROM UM_USER U4 ");
        strbuff.append("\n               , UM_CERT_INFO U5 ");
        strbuff.append("\n           WHERE U4.USER_ID = U5.USER_ID " + tempSql2);
        strbuff.append("\n             AND U5.CERT_NM = ?) S2 ");
        strbuff.append("\n  WHERE S2.mcode = U6.BIZ_REG_NO AND U6.HIDDEN_YN = 'N' ");
        final String[] parameter = { DN, DN };
        return new ComDbQuery().getList((Object)this, "USEMN", strbuff.toString(), parameter, conn);
    }
    
    public CommEntity[] getAddUserInfo(final String personid, final Connection conn) throws Exception {
        final StringBuffer pri_sql = new StringBuffer();
        pri_sql.append(" SELECT 성명 name ,                 \n ");
        pri_sql.append("        전화번호 tel,               \n ");
        pri_sql.append("        메일주소 email              \n ");
        pri_sql.append("   FROM 사용_부가사용자             \n ");
        pri_sql.append("  WHERE 아이디 = ?");
        final String[] parameter = { personid };
        return new ComDbQuery().getList((Object)this, "usemn", pri_sql.toString(), parameter, conn);
    }
    
    public String getFileName(final Connection conn) {
        try {
            final String sql = "SELECT 공지파일명 FROM 사용_장애공지 where 공지여부='Y'";
            final CommEntity[] entity = new ComDbQuery().getList((Object)this, "usemn", sql, (String[])null, conn);
            if (entity.length == 0) {
                return "";
            }
            return entity[0].data[0];
        }
        catch (Exception exf) {
            Log.debug(5, String.valueOf(this.getClass().getName()) + ".getFileName():" + exf.toString());
            return "";
        }
    }
    
    public void updateLastLoginTime(final String validTimeStart, final String validTimeEnd, final String DN, String serialNo, final Connection conn) {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("Connection is null");
            }
            final String sql = " UPDATE UM_CERT_INFO SET LAST_LOGIN_DT=sysdate, \tAVAIL_PERIOD_START_DT = to_date(?, 'yyyymmddhh24miss'), \tAVAIL_PERIOD_END_DT = to_date(?, 'yyyymmddhh24miss') WHERE CERT_NM=?\t";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, validTimeStart);
            pstmt.setString(2, validTimeEnd);
            pstmt.setString(3, DN);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("update 갯수 1이 아님");
            }
            
            pstmt.close();
            
            final String sql2 = " UPDATE UM_CERT_INFO SET SERIAL_NO = ? WHERE CERT_NM=? AND SERIAL_NO is NULL";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, serialNo);
            pstmt.setString(2, DN);
            pstmt.executeUpdate();
        }
        catch (Exception exm) {
            Log.debug(5, String.valueOf(this.getClass().getName()) + ".updateLastLoginTime():validTimeStart[" + validTimeStart + "],validTimeEnd[" + validTimeEnd + "],DN[" + DN + "]:" + exm.toString());
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
    
    public OneRowEntity getInfo(final String userID, final String gubun) throws Exception {
        if (gubun.equals("g")) {
            return this.getInfoGigwan(userID);
        }
        if (gubun.equals("c")) {
            return this.getInfoUpche(userID);
        }
        return null;
    }
    
    private OneRowEntity getInfoGigwan(final String userID) throws Exception {
        final String sql = " select a.MAST_CD 코드,b.INSTITU_FULL_NM 이름, a.USER_ID, a.USER_CLS,        a.CHRGR_NM,a.CHRGR_DEPART,a.CHRGR_PHONE_NO,a.CHRGR_FAX,        b.ZIP_CD, b.ADDR, b.DETAIL_ADDR,        nvl(a.MINISTRY_LINK_YN,'N') MINISTRY_LINK_YN, a.MINISTRY_ID, nvl(a.CITIS_LINK_YN,'N') CITIS_LINK_YN, a.CITIS_ID, a.CITIS_SERVER_ID,        nvl(a.PAY_LINK_YN,'N') PAY_LINK_YN, a.PAY_ID, a.PAY_SYSTEM_ID, b.OLD_INSTITU_CD, b.LOCAL_INSTITU from UM_USER a, UM_PUB_INSTITU_MAST b where a.USER_ID=? and a.MAST_CD= b.INSTITU_CD";
        final String[] parameter = { userID };
        return new ComDbQuery().getOneRowList((Object)this, "usemn", sql, parameter, (Connection)null);
    }
    
    private OneRowEntity getInfoUpche(final String userID) throws Exception {
        final String sql = " select a.MAST_CD 코드,c.BIZ_NM 이름, a.USER_ID, a.USER_CLS,        a.CHRGR_NM,a.CHRGR_DEPART,a.CHRGR_PHONE_NO,a.CHRGR_FAX,        c.ZIP_CD, c.ADDR, c.DETAIL_ADDR,        nvl(a.MINISTRY_LINK_YN,'N') MINISTRY_LINK_YN, a.MINISTRY_ID, nvl(a.CITIS_LINK_YN,'N') CITIS_LINK_YN, a.CITIS_ID, a.CITIS_ID,        nvl(a.PAY_LINK_YN,'N') PAY_LINK_YN, a.PAY_ID, a.PAY_SYSTEM_ID, d.REPR_NM from UM_USER a, UM_SUPPLIER_ENTER_MAST c, UM_REPR d where a.USER_ID=? and a.MAST_CD = c.BIZ_REG_NO and a.MAST_CD = d.BIZ_REG_NO and d.MAST_REPR_YN='Y'";
        final String[] parameter = { userID };
        return new ComDbQuery().getOneRowList((Object)this, "usemn", sql, parameter, (Connection)null);
    }
    
    public void setCookies(final HttpServletResponse res, final String comment, final String domain, final int maxAge, final String path, final boolean secure, final int version, final String name, final String value) throws Exception {
        try {
            if (name == null) {
                throw new NullPointerException("Can't be null");
            }
            if (value == null) {
                return;
            }
            Cookie SSO_ret_Page = null;
            SSO_ret_Page = new Cookie(name, URLEncoder.encode(value));
            if (comment != null) {
                SSO_ret_Page.setComment(comment);
            }
            if (domain != null) {
                SSO_ret_Page.setDomain(domain);
            }
            if (maxAge != -1) {
                SSO_ret_Page.setMaxAge(maxAge);
            }
            if (path != null) {
                SSO_ret_Page.setPath(path);
            }
            if (secure) {
                SSO_ret_Page.setSecure(true);
            }
            if (version != 0) {
                SSO_ret_Page.setVersion(version);
            }
            res.addCookie(SSO_ret_Page);
        }
        catch (Exception e) {
            throw e;
        }
    }
    
    public boolean getCookies(final HttpServletRequest req, final String key) {
        try {
            final Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (int i = cookies.length - 1; i >= 0; --i) {
                    if (cookies[i].getName().equals(key)) {
                        return true;
                    }
                }
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public CommEntity[] getPassUserInfo(final String userID, final Connection conn) throws Exception {
        final StringBuffer strbuff = new StringBuffer(1024);
        strbuff.append("\n\tSELECT\tU1.id");
        strbuff.append("\n\t\t\t\t,\tDECODE (U2.폐지일자, null, 'N', 'Y') gtype\t");
        strbuff.append("\n\t\t\t\t, \tU1.사업자등록번호 mcode\t ");
        strbuff.append("\n\t\t\t\t,\tU1.성명 name\t");
        strbuff.append("\n\t\t\t\t,\tU2.업체명 mname\t ");
        strbuff.append("\n   \t\t\t,\tU1.전화번호 telnum\t");
        strbuff.append("\n \t\t\t\t,\tU1.전자메일 mail ");
        strbuff.append("\n\t\t \t\t,\tnull bOffi ");
        strbuff.append("\n\t\t \t\t, \tnull sgcode ");
        strbuff.append("\n \t\t\t\t,\tU1.id personID ");
        strbuff.append("\n \t\t\t\t,\tpassword personPW ");
        strbuff.append("\n \t\t\t\t,\tU1.성명 personName ");
        strbuff.append("\n \t\t\t\t,\tU1.전자메일 personMail ");
        strbuff.append("\n \t\t\t\t,\tnull personGubun ");
        strbuff.append("\n \t\t\t\t,\tnvl(U1.로그인실패횟수,0) ");
        strbuff.append("\n \t\t\t\t,\t'사용_생산업체사용자' tabletype ");
        strbuff.append("\n\tFROM\t\t사용_생산업체사용자 U1 ");
        strbuff.append("\n \t\t\t\t,\t사용_생산업체 U2  ");
        strbuff.append("\n\tWHERE\tU1.사업자등록번호 = U2.사업자등록번호  ");
        strbuff.append("\n\tAND\t\t\tU1.id = ?  ");
        strbuff.append("\n\tAND\t\t\tU2.폐지일자 is null ");
        strbuff.append("\n\tUNION ALL\t");
        strbuff.append("\n\tSELECT    null id\t");
        strbuff.append("\n \t\t\t\t,\tnull gtype\t");
        strbuff.append("\n\t\t\t\t,\tnull mcode\t");
        strbuff.append("\n\t\t\t\t,\tnull name\t ");
        strbuff.append("\n\t\t\t\t,\tnull mname\t");
        strbuff.append("\n\t\t\t\t,\tnull telnum\t");
        strbuff.append("\n\t\t\t\t,\tnull mail\t");
        strbuff.append("\n\t\t\t\t,\tnull bOffi\t");
        strbuff.append("\n\t \t\t\t, \tnull sgcode\t");
        strbuff.append("\n\t\t\t\t,\t아이디 personID\t");
        strbuff.append("\n\t\t\t\t,\t비밀번호 personPW\t");
        strbuff.append("\n\t\t\t\t,\t성명 personName\t ");
        strbuff.append("\n\t\t\t\t,\t메일주소 personMail\t ");
        strbuff.append("\n\t\t\t\t,\t이용자구분 personGubun\t");
        strbuff.append("\n\t\t\t\t,\tnvl(로그인실패횟수,0)\t");
        strbuff.append("\n \t\t\t\t,\t'사용_부가사용자' tabletype ");
        strbuff.append("\n\tFROM\t\t사용_부가사용자\t");
        strbuff.append("\n\tWHERE\t\t아이디 = ?\t ");
        strbuff.append("\n\tAND\t\t\t삭제여부 = 'N'\t");
        final String[] parameter = { userID, userID };
        return new ComDbQuery().getList((Object)this, "usemn", strbuff.toString(), parameter, conn);
    }
    
    public void updateFailLogin(final String userID, final String tableName, final Connection conn) {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("Connection is null");
            }
            String sql = null;
            if (tableName.equals("사용_부가사용자")) {
                sql = " UPDATE 사용_부가사용자 SET 로그인실패횟수=nvl(로그인실패횟수,0)+1 WHERE 아이디 = ?";
            }
            else {
                if (!tableName.equals("사용_생산업체사용자")) {
                    throw new Exception("정의되지 않은 테이블명입니다.[" + tableName + "]");
                }
                sql = " UPDATE 사용_생산업체사용자 SET 로그인실패횟수=nvl(로그인실패횟수,0)+1 WHERE ID = ?";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.executeUpdate();
        }
        catch (Exception exm) {
            Log.debug(5, String.valueOf(this.getClass().getName()) + ".updateFailLogin():userID[" + userID + "], tableName[" + tableName + "]:" + exm.toString());
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
    
    public void initialPass(final String userID, final String tableName, final Connection conn) {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("Connection is null");
            }
            String sql = null;
            if (tableName.equals("사용_부가사용자")) {
                sql = " UPDATE 사용_부가사용자 SET 로그인실패횟수=0 WHERE 아이디 = ?";
            }
            else {
                if (!tableName.equals("사용_생산업체사용자")) {
                    throw new Exception("정의되지 않은 테이블명입니다.[" + tableName + "]");
                }
                sql = " UPDATE 사용_생산업체사용자 SET 로그인실패횟수=0 WHERE ID = ?";
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);
            pstmt.executeUpdate();
        }
        catch (Exception exm) {
            Log.debug(5, String.valueOf(this.getClass().getName()) + ".initialPass():userID[" + userID + "], tableName[" + tableName + "]:" + exm.toString());
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
    
    public OneRowEntity getUserDNInfo(final String userID, final String cert_seq, final Connection conn) throws Exception {
        OneRowEntity result = null;
        try {
            final String[] parameters = { userID, cert_seq };
            CommUtil.retNullParameterCheck(parameters);
            final String sql = " select a.USER_ID,a.TURN,to_char(a.LAST_LOGIN_DT,'yyyymmddhh24miss') as LAST_LOGIN_DT, a.CERT_NM,b.CHRGR_NM, b.CHRGR_DEPART, b.MAST_CD as BIZ_REG_NO, d.BIZ_NM,nvl(b.TEST_OPTION_YN,'N') as TEST_OPTION_YN,c.REPR_NM,c.REPR_IDENT_NO  from UM_CERT_INFO a,  UM_USER b,UM_REPR c, UM_SUPPLIER_ENTER_MAST d  where a.USER_ID=? and a.TURN=? and a.USER_ID=b.USER_ID and c.BIZ_REG_NO=b.MAST_CD and c.MAST_REPR_YN='Y' and b.MAST_CD=d.BIZ_REG_NO";
            result = new ComDbQuery().getOneRowList((Object)this, "usemn", sql, parameters, conn);
            if (!result.DataExist) {
                result = null;
            }
            return result;
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getUserDNInfo() : userID[" + userID + "],cert_seq[" + cert_seq + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public OneRowEntity getPaymentPolicy(final Connection conn) throws Exception {
        OneRowEntity result = null;
        try {
            final String[] parameters = new String[0];
            final String sql = " select REG_PAYMENT_TYPE, EXTEND_REMIND_MSG, EXTEND_PAYMENT_MSG, EXTEND_PAYMENT_TYPE, USE_YN, REG_START_DT, TO_CHAR(EXTEND_START_DT,'DD/MM/YYYY') EXTEND_START_DT, EFFECT_MONTHS, EXTEND_REMIND_MSG_DAYS, ONLINE_PAYMENT  from BID.BID_PAYMENT_CONFIG A  where A.USE_YN='Y'";
            result = new ComDbQuery().getOneRowList((Object)this, "usemn", sql, parameters, conn);
            if (!result.DataExist) {
                result = null;
            }
            return result;
        }
        catch (Exception exm) {
            throw exm;
        }
    }
    
    public int getSubUserCount(final Connection conn, final String userId) throws Exception {
        OneRowEntity result = null;
        try {
            final String[] parameters = new String[1];
            final String sql = " select count(*) rc from USEMN.UM_SUB_USER A  where A.IS_ACTIVE=1 and A.USER_ID = ? ";
            parameters[0] = userId;
            result = new ComDbQuery().getOneRowList((Object)this, "usemn", sql, parameters, conn);
            if (!result.DataExist) {
                return 0;
            }
            return Integer.parseInt(result.getDataFromName("RC"));
        }
        catch (Exception exm) {
            throw exm;
        }
    }
    
    public String checkSubUserLogin(final String userId, final String usrName, final String pwd) throws Exception {
        OneRowEntity result = null;
        Trx resource = null;
        Connection conn = null;
        try {
            resource = new Trx((Object)this, "USEMN");
            conn = resource.getConnection();
            final String[] parameters = new String[3];
            Log.debug("Login sub user userId=" + userId +", SUB_USER_NAME=" + usrName);
            final String sql = " select SUB_USER_ID from USEMN.UM_SUB_USER A  where A.IS_ACTIVE=1 and USER_ID = ? and A.SUB_USER_NAME = ? and PASSWORD = ? ";
            parameters[0] = userId;
            parameters[1] = usrName;
            parameters[2] = pwd;
            result = new ComDbQuery().getOneRowList((Object)this, "usemn", sql, parameters, conn);
            if (!result.DataExist) {
                return "";
            }
            Log.debug("result.DataExist");
            return result.getDataFromName("SUB_USER_ID");
        }
        catch (Exception exm) {
            throw exm;
        } finally {
        	if (resource != null) {
        		resource.close();
        	}
        }
    }
    
    public OneRowEntity getPaymentItemListByRecvNo(final String recvNo, final String effectMonths, final String remindDays, final String extendStartDt, final Connection conn) throws Exception {
        OneRowEntity result = null;
        try {
            final String[] parameters = new String[0];
            String sql = "SELECT DECODE(PM_DATE, NULL, '" + extendStartDt + "', TO_CHAR(ADD_MONTHS(PM_DATE, " + effectMonths + "),'DD/MM/YYYY')) PM_DATE,  \n";
            sql = String.valueOf(sql) + " ROUND(DECODE(PM_DATE, NULL, TO_DATE('" + extendStartDt + "','DD/MM/YYYY'), ADD_MONTHS(PM_DATE, " + effectMonths + "))-SYSDATE) TO_PAID,   \n";
            sql = String.valueOf(sql) + " ROUND(DECODE(PM_DATE, NULL, TO_DATE('" + extendStartDt + "','DD/MM/YYYY'), ADD_MONTHS(PM_DATE, " + effectMonths + "))-SYSDATE)-" + remindDays + " TO_MSG    \n";
            sql = String.valueOf(sql) + " FROM  \n";
            sql = String.valueOf(sql) + " ( \n";
            sql = String.valueOf(sql) + " SELECT MAX(X.PM_DATE) PM_DATE FROM BID.BID_PAYMENT_TABLE X, BID.BID_PAYMENT_DETAIL Y \n";
            sql = String.valueOf(sql) + " WHERE X.PM_TABLE_ID=Y.PM_TABLE_ID AND X.RECV_NO ='" + recvNo + "' AND Y.PM_ITEM_ID IN (1,2) AND X.PM_FINISH_YN='Y' \n";
            sql = String.valueOf(sql) + " ) \n";
            result = new ComDbQuery().getOneRowList((Object)this, "usemn", sql, parameters, conn);
            if (!result.DataExist) {
                result = null;
            }
            return result;
        }
        catch (Exception exm) {
            throw exm;
        }
    }
    
    public OneRowEntity getPaymentItemListByBizRegNo(final String bizRegNo, final String effectMonths, final String remindDays, final String extendStartDt, final Connection conn) throws Exception {
        OneRowEntity result = null;
        try {
            final String[] parameters = new String[0];
            String sql = "SELECT DECODE(PM_DATE, NULL, '" + extendStartDt + "', TO_CHAR(ADD_MONTHS(PM_DATE, " + effectMonths + "),'DD/MM/YYYY')) PM_DATE,  \n";
            sql = String.valueOf(sql) + " ROUND(DECODE(PM_DATE, NULL, TO_DATE('" + extendStartDt + "','DD/MM/YYYY'), ADD_MONTHS(PM_DATE, " + effectMonths + "))-SYSDATE) TO_PAID,   \n";
            sql = String.valueOf(sql) + " ROUND(DECODE(PM_DATE, NULL, TO_DATE('" + extendStartDt + "','DD/MM/YYYY'), ADD_MONTHS(PM_DATE, " + effectMonths + "))-SYSDATE)-" + remindDays + " TO_MSG    \n";
            sql = String.valueOf(sql) + " FROM  \n";
            sql = String.valueOf(sql) + " ( \n";
            sql = String.valueOf(sql) + " SELECT MAX(X.PM_DATE) PM_DATE FROM BID.BID_PAYMENT_TABLE X, BID.BID_PAYMENT_DETAIL Y \n";
            sql = String.valueOf(sql) + " WHERE X.PM_TABLE_ID=Y.PM_TABLE_ID AND X.BIZ_REG_NO ='" + bizRegNo + "' AND Y.PM_ITEM_ID IN (1,2) AND X.PM_FINISH_YN='Y' \n";
            sql = String.valueOf(sql) + " ) \n";
            result = new ComDbQuery().getOneRowList((Object)this, "usemn", sql, parameters, conn);
            if (!result.DataExist) {
                result = null;
            }
            return result;
        }
        catch (Exception exm) {
            throw exm;
        }
    }
    
    public OneRowEntity getRecvNo(final String mCode, final String userDN, final Connection conn) throws Exception {
        OneRowEntity result = null;
        try {
            final String[] parameters = new String[0];
            String sql = "SELECT RECV_NO, BIZ_REG_NO  \n";
            sql = String.valueOf(sql) + " FROM UM_REC_PUB_INSTITU_CERT A  \n";
            sql = String.valueOf(sql) + " where A.INSTITU_CD='" + mCode + "'  \n";
            sql = String.valueOf(sql) + " and A.CERT_NM='" + userDN + "'  \n";
            result = new ComDbQuery().getOneRowList((Object)this, "usemn", sql, parameters, conn);
            if (!result.DataExist) {
                result = null;
            }
            return result;
        }
        catch (Exception exm) {
            throw exm;
        }
    }
    
    public void setTempCookies(final HttpServletResponse res, final String comment, final String domain, final int maxAge, final String path, final boolean secure, final int version, final String[] values, final boolean encoding) throws Exception {
        try {
            if (values == null) {
                throw new NullPointerException("Can't be null");
            }
            final Cookie[] cookies = new Cookie[values.length];
            cookies[0] = new Cookie("_SLT_TEMP", encoding ? URLEncoder.encode(values[0]) : values[0]);
            cookies[1] = new Cookie("_SLTK_TEMP", encoding ? URLEncoder.encode(values[1]) : values[1]);
            cookies[2] = new Cookie("_SLTS_TEMP", encoding ? URLEncoder.encode(values[2]) : values[2]);
            for (int i = cookies.length - 1; i >= 0; --i) {
                if (comment != null) {
                    cookies[i].setComment(comment);
                }
                if (domain != null) {
                    cookies[i].setDomain(domain);
                }
                if (maxAge != -1) {
                    cookies[i].setMaxAge(maxAge);
                }
                if (path != null) {
                    cookies[i].setPath(path);
                }
                if (secure) {
                    cookies[i].setSecure(true);
                }
                if (version != 0) {
                    cookies[i].setVersion(version);
                }
                res.addCookie(cookies[i]);
            }
        }
        catch (Exception e) {
            Log.errors(String.valueOf(this.getClass().getName()) + ".setTempCookies():" + e.toString());
            throw e;
        }
    }
}
