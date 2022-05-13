// 
// Decompiled by Procyon v0.5.30
// 

package LOGIN;

import java.sql.PreparedStatement;
import java.sql.Connection;
import common.Trx;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import common.Log;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class LoginControl
{
    public static String[] certvalue;
    public static String[] idvalue;
    
    public String[] getIDPWD(final String s) throws Exception {
        final String[] array = new String[2];
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "&");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith("id=")) {
                    array[0] = URLDecoder.decode(nextToken.substring(3));
                }
                else {
                    if (!nextToken.startsWith("pwd=")) {
                        continue;
                    }
                    array[1] = URLDecoder.decode(nextToken.substring(4));
                }
            }
        }
        catch (Exception ex) {
            Log.errors("<로그인> getIPPWD error 발생" + ex.toString());
            throw ex;
        }
        return array;
    }
    
    public String getDN(final String s) throws Exception {
        try {
            if (s.startsWith("dn=")) {
                return URLDecoder.decode(s.substring(3));
            }
            return null;
        }
        catch (Exception ex) {
            Log.errors("<로그인> getDN error 발생" + ex.toString());
            throw ex;
        }
    }
    
    public String getStringToken(final String[] array, final String s) {
        if (array == null) {
            return null;
        }
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HHmmss");
        final GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA);
        gregorianCalendar.add(10, 3);
        final StringBuffer sb = new StringBuffer();
        sb.append("expire=" + URLEncoder.encode(simpleDateFormat.format(gregorianCalendar.getTime())) + URLEncoder.encode(simpleDateFormat2.format(gregorianCalendar.getTime())));
        if (s.equals("I")) {
            if (array.length == LoginControl.idvalue.length) {
                for (int i = 0; i < LoginControl.idvalue.length; ++i) {
                    if (array[i] != null) {
                        sb.append("&" + LoginControl.idvalue[i] + "=" + URLEncoder.encode(array[i]));
                    }
                }
                return sb.toString() + "&ltype=I";
            }
        }
        else if (s.equals("C") && array.length == LoginControl.certvalue.length) {
            for (int j = 0; j < LoginControl.certvalue.length; ++j) {
                if (array[j] != null) {
                    sb.append("&" + LoginControl.certvalue[j] + "=" + URLEncoder.encode(array[j]));
                }
            }
            return sb.toString() + "&ltype=C";
        }
        return null;
    }
    
    public void setCookies(final HttpServletResponse httpServletResponse, final String comment, final String domain, final int maxAge, final String path, final boolean b, final int version, final String[] array, final boolean b2) throws Exception {
        try {
            if (array == null) {
                throw new NullPointerException("Can't be null");
            }
            final Cookie[] array2 = new Cookie[array.length];
            array2[0] = new Cookie("_SLT", b2 ? URLEncoder.encode(array[0]) : array[0]);
            array2[1] = new Cookie("_SLTK", b2 ? URLEncoder.encode(array[1]) : array[1]);
            array2[2] = new Cookie("_SLTS", b2 ? URLEncoder.encode(array[2]) : array[2]);
            for (int n = array2.length - 1; 0 <= n; --n) {
                if (comment != null) {
                    array2[n].setComment(comment);
                }
                if (domain != null) {
                    array2[n].setDomain(domain);
                }
                if (maxAge != -1) {
                    array2[n].setMaxAge(maxAge);
                }
                if (path != null) {
                    array2[n].setPath(path);
                }
                if (b) {
                    array2[n].setSecure(true);
                }
                if (version != 0) {
                    array2[n].setVersion(version);
                }
                httpServletResponse.addCookie(array2[n]);
            }
        }
        catch (Exception ex) {
            Log.errors("<로그인> setCookies Error : \n" + ex.toString());
            throw ex;
        }
    }
    
    public static String getIP(final HttpServletRequest httpServletRequest) throws ServletException, IOException {
        String s = httpServletRequest.getHeader("x-forwarded-for");
        if (s == null) {
            s = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (s == null) {
            s = httpServletRequest.getRemoteAddr();
        }
        if (s == null) {
            s = "IP조회못함";
        }
        return s;
    }
    
    public void updateCentInfo(final String s, final String s2, final String s3, final String s4) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        final String s5 = " UPDATE\t사용_인증서정보 set 최종로그인일시 = sysdate, 유효기간시작일시 = to_date(?, 'yyyymmddhh24miss'), 유효기간만료일시 = to_date(?, 'yyyymmddhh24miss') WHERE\t사용자ID = ? AND\t\t차수 = ?";
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            connection.setAutoCommit(false);
            prepareStatement = connection.prepareStatement(s5);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, s3);
            prepareStatement.setString(4, s4);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("update 갯수 1 이 아님");
            }
            connection.commit();
            connection.setAutoCommit(true);
        }
        catch (Exception ex) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            }
            catch (Exception ex2) {}
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            }
            catch (Exception ex3) {}
            Log.errors(this.getClass().getName() + ".updateCentInfo() userID[" + s3 + "],USEQ[" + s4 + "]:" + ex.toString());
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex5) {}
        }
    }
    
    public String getRetPage(final HttpServletRequest httpServletRequest) {
        String decode = null;
        try {
            final Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies != null) {
                for (int n = cookies.length - 1; 0 <= n; --n) {
                    if (cookies[n].getName().equals("_TEMP_RET_PAGE")) {
                        decode = URLDecoder.decode(cookies[n].getValue());
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {}
        return decode;
    }
    
    static {
        LoginControl.certvalue = new String[] { "id", "gubun", "mcode", "name", "mname", "telnum", "email", "certseq", "bOffi", "sgcode", "lognum", "ip", "personID", "personName", "ptel", "pemail" };
        LoginControl.idvalue = new String[] { "id", "gtype", "mcode", "name", "mname", "telnum", "email", "bOffi", "sgcode", "lognum", "ip", "personID", "personPW", "personName", "personMail", "personGubun" };
    }
}
