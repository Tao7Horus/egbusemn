// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.StringTokenizer;
import common.Log;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import common.Trx;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import javax.servlet.http.HttpServlet;

public class UM_ADV_GovrB050c extends HttpServlet
{
    public void copy(final FileInputStream in, final FileOutputStream out) throws IOException {
        synchronized (in) {
            // monitorenter(out)
            try {
                final byte[] buffer = new byte[in.available()];
                while (true) {
                    final int byteRead = in.read(buffer);
                    if (byteRead == -1) {
                        break;
                    }
                    out.write(buffer, 0, byteRead);
                }
            }
            // monitorexit(out)
            finally {}
        }
    }
    
    public String getXMLFileName(final String fileDateFormat) {
        final Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA);
        SimpleDateFormat dateFormat = null;
        final SimpleDateFormat timeFormat = null;
        try {
            dateFormat = new SimpleDateFormat(fileDateFormat);
        }
        catch (IllegalArgumentException e) {
            dateFormat = new SimpleDateFormat("yyyyMMdd");
        }
        return dateFormat.format(calendar.getTime());
    }
    
    public String getXMLFileName() {
        return this.getXMLFileName("yyyyMMdd");
    }
    
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final ResultSet rs = null;
        String sql = null;
        String sql2 = null;
        String sql3 = null;
        String sql4 = null;
        String sql5 = null;
        String sql6 = null;
        final StringBuffer sb = new StringBuffer();
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            con.setAutoCommit(false);
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        String d = "";
        final String fi = "";
        d = this.getXMLFileName();
        final String filename = "/devl/usemn/usemnapp/mrd/08000120020822.rec.txt";
        String thisLine = "";
        String Del = "";
        String Del2 = "";
        String Del3 = "";
        String Del4 = "";
        String Del5 = "";
        String Del6 = "";
        String Del7 = "";
        String Del8 = "";
        String Del9 = "";
        String Del10 = "";
        String Del11 = "";
        String Del12 = "";
        String Del13 = "";
        String Del14 = "";
        final String Del15 = "";
        String rDel = "";
        String rDel2 = "";
        String rDel3 = "";
        String rDel4 = "";
        String rDel5 = "";
        String rDel6 = "";
        String rDel7 = "";
        String rDel8 = "";
        String rDel9 = "";
        String rDel10 = "";
        String rDel11 = "";
        String rDel12 = "";
        String rDel13 = "";
        String rDel14 = "";
        String rDel15 = "";
        final String rDel16 = "";
        String gcode3 = "";
        String gcode4 = "";
        String gcode5 = "";
        final String gcode6 = "";
        final String gcode7 = "";
        String gcode8 = "";
        String gcode9 = "";
        String gcode10 = "";
        String gcode11 = "";
        String gcode12 = "";
        final String gcode13 = "";
        final String gcode14 = "";
        final String b_NULL = "";
        final BufferedReader myInput = new BufferedReader(new FileReader(filename));
        try {
            while ((thisLine = myInput.readLine()) != null) {
                Log.debug("start...");
                String tempLine = String.valueOf(thisLine) + "\r\n";
                final StringTokenizer str = new StringTokenizer(thisLine, "\t", true);
                Del = str.nextToken();
                Del2 = str.nextToken();
                Del3 = str.nextToken();
                Del4 = str.nextToken();
                Del5 = str.nextToken();
                Del6 = str.nextToken();
                Del7 = str.nextToken();
                Del8 = str.nextToken();
                Del9 = str.nextToken();
                Del10 = str.nextToken();
                Del11 = str.nextToken();
                Del12 = str.nextToken();
                Del13 = str.nextToken();
                Del14 = str.nextToken();
                rDel = str.nextToken();
                rDel2 = str.nextToken();
                rDel3 = str.nextToken();
                rDel4 = str.nextToken();
                rDel5 = str.nextToken();
                rDel6 = str.nextToken();
                rDel7 = str.nextToken();
                if (rDel7.equals("\t")) {
                    rDel7 = b_NULL;
                }
                else {
                    rDel8 = str.nextToken();
                }
                rDel9 = str.nextToken();
                if (rDel9.equals("\t")) {
                    rDel10 = b_NULL;
                }
                else {
                    rDel10 = str.nextToken();
                }
                rDel11 = str.nextToken();
                if (rDel11.equals("\t")) {
                    rDel12 = b_NULL;
                }
                else {
                    rDel12 = str.nextToken();
                }
                rDel13 = str.nextToken();
                if (rDel13.equals("\t")) {
                    rDel14 = b_NULL;
                }
                else {
                    rDel14 = str.nextToken();
                }
                rDel15 = str.nextToken();
                try {
                    if (Del5.equals("01")) {
                        sql = "insert into 사용_행정표준정보(행정표준기관코드, 기관장직급명, 기관장직위, 차수, 서열, 차상위기관코드, 최상위기관코드, 기관유형_대,기관유형_중, 기관유형_소, 생성일자, 폐지구분, 변경일자, 전체명) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '0', '', ?)";
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, Del7);
                        psmt.setString(2, rDel11);
                        psmt.setString(3, rDel13);
                        psmt.setString(4, Del11);
                        psmt.setString(5, Del13);
                        psmt.setString(6, rDel);
                        psmt.setString(7, rDel3);
                        psmt.setString(8, rDel5);
                        psmt.setString(9, rDel7);
                        psmt.setString(10, rDel9);
                        psmt.setString(11, Del3);
                        psmt.setString(12, Del9);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    else if (Del5.equals("02")) {
                        sql = "update 사용_공공기관마스터 set 삭제여부 = 'Y', 갱신일자 ='" + Del3 + "' where 공공기관코드 = '" + Del7 + "' ";
                        psmt = con.prepareStatement(sql);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        sql2 = "update 사용_행정표준정보 set 폐지구분 = '1' , 폐지일자 ='" + Del3 + "' where 행정표준기관코드 = '" + Del7 + "' ";
                        psmt = con.prepareStatement(sql2);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    else if (Del5.equals("03")) {
                        final String code = rDel15.substring(0, 7);
                        sql3 = "insert into 사용_공공기관마스터(공공기관코드,공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호,  소관구분, 업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호,   담당자메일주소,  우편번호,  주소, 나머지주소, 전화번호,  팩스번호,  홈페이지주소,    전수요기관코드,  물품관리관명,  양허코드,  관할지청,특정관리기관,지청구분,  운송거리, 전자인증여부,처리자ID, 지역코드,등록일자,삭제여부) \tSELECT '" + Del7 + "', 공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호," + "소관구분, 업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호, " + "\t담당자메일주소, 우편번호,  주소, 나머지주소, 전화번호,  팩스번호,  홈페이지주소, " + "\t전수요기관코드,  물품관리관명, 양허코드,  관할지청,특정관리기관, " + "\t지청구분,  운송거리, 전자인증여부,처리자ID, 지역코드,등록일자,삭제여부 " + "FROM 사용_공공기관마스터 " + "WHERE 공공기관코드 = '" + code + "'";
                        psmt = con.prepareStatement(sql3);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        sql2 = "insert into 사용_행정표준정보(행정표준기관코드, 기관장직급명, 기관장직위, 차수, 서열, 차상위기관코드, 최상위기관코드, 기관유형_대,기관유형_중, 기관유형_소, 생성일자, 폐지구분, 변경일자, 전체명) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '0', '', ?)";
                        psmt = con.prepareStatement(sql2);
                        System.out.println(sql2);
                        psmt.setString(1, Del7);
                        psmt.setString(2, rDel11);
                        psmt.setString(3, rDel13);
                        psmt.setString(4, Del11);
                        psmt.setString(5, Del13);
                        psmt.setString(6, rDel);
                        psmt.setString(7, rDel3);
                        psmt.setString(8, rDel5);
                        psmt.setString(9, rDel7);
                        psmt.setString(10, rDel9);
                        psmt.setString(11, Del3);
                        psmt.setString(12, Del9);
                        System.out.println("Del8==" + Del9);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        gcode11 = rDel15.substring(0, 7);
                        sql4 = "update syn_매핑코드기관 set 공공기관코드 =? where 공공기관코드 = ? ";
                        psmt = con.prepareStatement(sql4);
                        psmt.setString(1, Del7);
                        psmt.setString(2, gcode11);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        gcode12 = rDel15.substring(0, 7);
                        sql5 = "update 사용_사용자 set 마스터코드 = ? where 마스터코드 = ?";
                        psmt = con.prepareStatement(sql5);
                        psmt.setString(1, Del7);
                        psmt.setString(2, gcode12);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        sql6 = "insert into 사용_공공기관코드이력 (공공기관코드, 변경일자, 변경공공기관코드, 변경유형구분) values(?, ?, ?, ?)";
                        psmt = con.prepareStatement(sql6);
                        psmt.setString(1, code);
                        psmt.setString(2, Del3);
                        psmt.setString(3, Del7);
                        psmt.setString(4, Del5);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    else if (Del5.equals("04")) {
                        sql = "insert into 사용_행정표준정보(행정표준기관코드, 기관장직급명, 기관장직위, 차수, 서열, 차상위기관코드, 최상위기관코드, 기관유형_대,기관유형_중, 기관유형_소, 생성일자, 폐지구분, 변경일자, 전체명) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '0', '', ?)";
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, Del7);
                        psmt.setString(2, rDel11);
                        psmt.setString(3, rDel13);
                        psmt.setString(4, Del11);
                        psmt.setString(5, Del13);
                        psmt.setString(6, rDel);
                        psmt.setString(7, rDel3);
                        psmt.setString(8, rDel5);
                        psmt.setString(9, rDel7);
                        psmt.setString(10, rDel9);
                        psmt.setString(11, Del3);
                        psmt.setString(12, Del9);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    else if (Del5.equals("05")) {
                        final String code = rDel15.substring(0, 7);
                        sql = "insert into 사용_행정표준정보(행정표준기관코드, 기관장직급명, 기관장직위, 차수, 서열, 차상위기관코드, 최상위기관코드, 기관유형_대,기관유형_중, 기관유형_소, 생성일자, 폐지구분, 변경일자, 전체명) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '0', '', ?)";
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, Del7);
                        psmt.setString(2, rDel11);
                        psmt.setString(3, rDel13);
                        psmt.setString(4, Del11);
                        psmt.setString(5, Del13);
                        psmt.setString(6, rDel);
                        psmt.setString(7, rDel3);
                        psmt.setString(8, rDel5);
                        psmt.setString(9, rDel7);
                        psmt.setString(10, rDel9);
                        psmt.setString(11, Del3);
                        psmt.setString(12, Del9);
                        System.out.println("Del8==" + Del9);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        gcode9 = rDel15.substring(0, 7);
                        sql3 = "insert into 사용_공공기관마스터(공공기관코드,공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호,  소관구분, 업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호,   담당자메일주소,  우편번호,  주소, 나머지주소, 전화번호,  팩스번호,  홈페이지주소,    전수요기관코드,  물품관리관명,  양허코드,  관할지청,특정관리기관,지청구분,  운송거리, 전자인증여부,처리자ID, 지역코드,등록일자,삭제여부) \tSELECT '" + Del7 + "', 공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호," + "소관구분, 업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호, " + "\t담당자메일주소, 우편번호,  주소, 나머지주소, 전화번호,  팩스번호,  홈페이지주소, " + "\t전수요기관코드,  물품관리관명, 양허코드,  관할지청,특정관리기관, " + "\t지청구분,  운송거리, 전자인증여부,처리자ID, 지역코드,등록일자,삭제여부 " + "FROM 사용_공공기관마스터 " + "WHERE 공공기관코드 = '" + code + "'";
                        psmt = con.prepareStatement(sql3);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        sql4 = "update syn_매핑코드기관 set 공공기관코드 = ? where 공공기관코드 = ? ";
                        psmt = con.prepareStatement(sql4);
                        psmt.setString(1, Del7);
                        psmt.setString(2, gcode9);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        gcode10 = rDel15.substring(0, 7);
                        sql5 = "update 사용_사용자 set 마스터코드 =? where 마스터코드 = ? ";
                        psmt = con.prepareStatement(sql5);
                        psmt.setString(1, Del7);
                        psmt.setString(2, gcode10);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        sql6 = "insert into 사용_공공기관코드이력 (공공기관코드, 변경일자, 변경공공기관코드, 변경유형구분) values(?, ?, ?, ?)";
                        psmt = con.prepareStatement(sql6);
                        psmt.setString(1, gcode10);
                        psmt.setString(2, Del3);
                        psmt.setString(3, Del7);
                        psmt.setString(4, Del5);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    else if (Del5.equals("06")) {
                        sql3 = "insert into 사용_행정표준정보(행정표준기관코드, 기관장직급명, 기관장직위, 차수, 서열, 차상위기관코드, 최상위기관코드, 기관유형_대,기관유형_중, 기관유형_소, 생성일자, 폐지구분, 변경일자, 전체명) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '0', '', ?)";
                        psmt = con.prepareStatement(sql3);
                        psmt.setString(1, Del7);
                        psmt.setString(2, rDel11);
                        psmt.setString(3, rDel13);
                        psmt.setString(4, Del11);
                        psmt.setString(5, Del13);
                        psmt.setString(6, rDel);
                        psmt.setString(7, rDel3);
                        psmt.setString(8, rDel5);
                        psmt.setString(9, rDel7);
                        psmt.setString(10, rDel9);
                        psmt.setString(11, Del3);
                        psmt.setString(12, Del9);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        gcode8 = rDel15.substring(0, 7);
                        sql = "insert into 사용_공공기관마스터(공공기관코드,공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호,  소관구분, 업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호,   담당자메일주소,  우편번호,  주소, 나머지주소, 전화번호,  팩스번호,  홈페이지주소,    전수요기관코드,  물품관리관명,  양허코드,  관할지청,특정관리기관,지청구분,  운송거리, 전자인증여부,처리자ID, 지역코드,등록일자,삭제여부) \tSELECT '" + Del7 + "', 공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호," + "소관구분, 업태, 업종, 담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호, " + "\t담당자메일주소, 우편번호,  주소, 나머지주소, 전화번호,  팩스번호,  홈페이지주소, " + "\t전수요기관코드,  물품관리관명, 양허코드,  관할지청,특정관리기관, " + "\t지청구분,  운송거리, 전자인증여부,처리자ID, 지역코드,등록일자,삭제여부 " + "FROM 사용_공공기관마스터 " + "WHERE 공공기관코드 = '" + gcode8 + "'";
                        psmt = con.prepareStatement(sql);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        psmt.close();
                        final StringTokenizer str2 = new StringTokenizer(rDel15, ",");
                        sql6 = "insert into 사용_공공기관코드이력 (공공기관코드, 변경일자, 변경공공기관코드, 변경유형구분) values(?, ?, ?, ?)";
                        psmt = con.prepareStatement(sql6);
                        while (str2.hasMoreTokens()) {
                            gcode3 = str2.nextToken();
                            psmt.setString(1, gcode3.substring(0, 7));
                            psmt.setString(2, Del3);
                            psmt.setString(3, Del7);
                            psmt.setString(4, Del5);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                        psmt.close();
                        final StringTokenizer str3 = new StringTokenizer(rDel15, ",");
                        sql4 = "update syn_매핑코드기관 set 공공기관코드 = ? where 공공기관코드 = ? ";
                        psmt = con.prepareStatement(sql4);
                        while (str3.hasMoreTokens()) {
                            gcode4 = str3.nextToken().substring(0, 7);
                            psmt.setString(1, Del7);
                            psmt.setString(2, gcode4);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                        psmt.close();
                        final StringTokenizer str4 = new StringTokenizer(rDel15, ",");
                        sql5 = "update 사용_사용자 set 마스터코드 = ? where 마스터코드 = ? ";
                        psmt = con.prepareStatement(sql5);
                        while (str4.hasMoreTokens()) {
                            gcode5 = str4.nextToken().substring(0, 7);
                            psmt.setString(1, Del7);
                            psmt.setString(2, gcode5.substring(0, 7));
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                        psmt.close();
                        con.commit();
                        con.setAutoCommit(true);
                    }
                    else if (Del5.equals("07")) {
                        String gcode15 = "";
                        String gcode16 = "";
                        PreparedStatement psmt2 = null;
                        PreparedStatement psmt3 = null;
                        final StringTokenizer str5 = new StringTokenizer(rDel15, ",");
                        try {
                            sql5 = "update 사용_사용자 set 마스터코드 = ? where 마스터코드 = ? ";
                            psmt = con.prepareStatement(sql5);
                            sql4 = "update syn_매핑코드기관 set 공공기관코드 = ? where 공공기관코드 = ? ";
                            psmt2 = con.prepareStatement(sql4);
                            sql6 = "insert into 사용_공공기관코드이력 (공공기관코드, 변경일자, 변경공공기관코드, 변경유형구분) values(?, ?, ?, ?)";
                            psmt3 = con.prepareStatement(sql6);
                            while (str5.hasMoreTokens()) {
                                gcode15 = str5.nextToken();
                                gcode16 = str5.nextToken();
                                psmt.setString(1, Del7);
                                psmt.setString(2, gcode16.substring(0, 7));
                                psmt.executeUpdate();
                                psmt.clearParameters();
                                psmt2.setString(1, Del7);
                                psmt2.setString(2, gcode16.substring(0, 7));
                                psmt2.executeUpdate();
                                psmt2.clearParameters();
                                psmt3.setString(1, gcode16.substring(0, 7));
                                psmt3.setString(2, Del3);
                                psmt3.setString(3, Del7);
                                psmt3.setString(4, Del5);
                                psmt3.executeUpdate();
                                psmt3.clearParameters();
                                con.commit();
                                con.setAutoCommit(true);
                            }
                        }
                        finally {
                            if (psmt2 != null) {
                                try {
                                    psmt2.close();
                                }
                                catch (SQLException ex) {}
                            }
                            if (psmt3 != null) {
                                try {
                                    psmt3.close();
                                }
                                catch (SQLException ex2) {}
                            }
                        }
                        if (psmt2 != null) {
                            try {
                                psmt2.close();
                            }
                            catch (SQLException ex3) {}
                        }
                        if (psmt3 == null) {
                            continue;
                        }
                        try {
                            psmt3.close();
                        }
                        catch (SQLException ex4) {}
                    }
                    else {
                        sql = "update 사용_행정표준정보 set 기관장직급명=?, 기관장직위=?, 차수=?, 서열=?, 차상위기관코드=?, 최상위기관코드=?, 기관유형_대=?,기관유형_중=?, 기관유형_소=?,변경일자=?, 전체명=?where 행정표준기관코드= ?";
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, rDel11);
                        psmt.setString(2, rDel13);
                        psmt.setString(3, Del11);
                        psmt.setString(4, Del13);
                        psmt.setString(5, rDel);
                        psmt.setString(6, rDel3);
                        psmt.setString(7, rDel5);
                        psmt.setString(8, rDel7);
                        psmt.setString(9, rDel9);
                        psmt.setString(10, Del3);
                        psmt.setString(11, Del9);
                        psmt.setString(12, Del7);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        con.commit();
                        con.setAutoCommit(true);
                    }
                }
                catch (Exception e2) {
                    final String file = "/devl/usemn/usemnapp/mrd/goCodeErr.txt";
                    tempLine = String.valueOf(thisLine) + "\r\n";
                    final FileWriter fw = new FileWriter(file, true);
                    final BufferedWriter out = new BufferedWriter(fw);
                    out.write(tempLine, 0, tempLine.length());
                    out.close();
                    System.out.println("FFFFFFFFFFF");
                    try {
                        con.rollback();
                        con.setAutoCommit(true);
                        e2.printStackTrace();
                        System.out.println("GGGGGGGGGGG");
                    }
                    catch (SQLException exc) {
                        Log.debug("UM_ADJ_GovrB050c Exception : Transaction Rollback간에 Exception 발생함");
                        Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                        exc.printStackTrace();
                        System.out.println("EEEEEEEE");
                    }
                    Log.debug("UM_ADJ_GovrB050c Exception : ");
                    Log.debug("Exception발생 사유 : " + e2.toString());
                    e2.printStackTrace();
                }
            }
        }
        catch (Exception e3) {
            Log.debug("");
            e3.printStackTrace();
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (SQLException ex5) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (SQLException ex6) {}
            }
            if (con != null) {
                try {
                    con.close();
                }
                catch (SQLException ex7) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex8) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException ex9) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (SQLException ex10) {}
        }
        if (con != null) {
            try {
                con.close();
            }
            catch (SQLException ex11) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex12) {}
        }
    }
}
