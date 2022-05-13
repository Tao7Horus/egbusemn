// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.StringTokenizer;
import common.Log;
import java.sql.SQLException;
import common.Trx;
import entity.UM_ADV_GovuA030b;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADJ_GovrB040c extends HttpServlet
{
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
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final PreparedStatement psmt2 = null;
        final PreparedStatement psmt3 = null;
        final PreparedStatement psmt4 = null;
        final PreparedStatement psmt5 = null;
        final PreparedStatement psmt6 = null;
        final PreparedStatement psmt7 = null;
        ResultSet rs = null;
        String sql = null;
        String sql2 = null;
        String sql3 = null;
        String sql4 = null;
        final StringBuffer sb = new StringBuffer();
        final UM_ADV_GovuA030b entity = new UM_ADV_GovuA030b();
        String flag = "";
        String g2bCode = "";
        final String orDivision = "";
        String goNameFull = "";
        String goNameShort = "";
        String goNameEn = "";
        String saupNo = "";
        String buConditon = "";
        String buType = "";
        String taskmaster = "";
        String masterPost = "";
        String masterTel = "";
        String masterFax = "";
        String masterMail = "";
        String creditName = "";
        String relation = "";
        String ZIPCODE = "";
        String ADDR = "";
        String address2 = "";
        String telNum = "";
        String faxNum = "";
        String organCode = "";
        String goodsMaster = "";
        String permitCode = "";
        String province = "";
        String eCitation = "";
        String spOffice = "";
        String branchOffi = "";
        String trDistance = "";
        String homepage = "";
        String createDate = "";
        String masterID = "";
        String delete = "";
        String moDate = "";
        String locaCode = "";
        String mTel_1 = "";
        String mTel_2 = "";
        String tel_1 = "";
        String tel_2 = "";
        String mFax_1 = "";
        String mFax_2 = "";
        String fax_1 = "";
        String fax_2 = "";
        String saupNo_0 = "";
        String saupNo_2 = "";
        String saupNo_3 = "";
        String saupNo_4 = "";
        String organ = "";
        String mathCode = "";
        final String s02 = "";
        String Del = "";
        String codename = "";
        String codd = "";
        String i02 = "";
        String i3 = "";
        String i4 = "";
        String i5 = "";
        String i6 = "";
        String i7 = "";
        String relate = "";
        String ediCode = "";
        String ediCode2 = "";
        String ediCode3 = "";
        String ediCode4 = "";
        final String ediCode5 = "";
        final String ediCode6 = "";
        final String ediCode7 = "";
        final String ediCode8 = "";
        final String ediCode9 = "";
        final String ediCode10 = "";
        final String ediCode11 = "";
        String ediData = "";
        String license = "";
        String newCode = "";
        final String newCode2 = "";
        final String newCode3 = "";
        String masterTel2 = "";
        String lic = "";
        String masterFax2 = "";
        String telNum2 = "";
        String faxNum2 = "";
        String modify = "";
        String gcode = "";
        final long lstr = 0L;
        long lstr2 = 0L;
        long lstr3 = 0L;
        long lstr4 = 0L;
        final long lstr5 = 0L;
        final long lstr6 = 0L;
        final long lstr7 = 0L;
        final long lstr8 = 0L;
        final long lstr9 = 0L;
        final long lstr10 = 0L;
        final int result = 10;

            try {
                trx = new Trx(this, "usemn");
                con = trx.getConnection();
                con.setAutoCommit(false);
                flag = ((req.getParameter("flag") == null) ? "" : req.getParameter("flag"));
                g2bCode = ((req.getParameter("g2bCode") == null) ? "" : req.getParameter("g2bCode"));
                goNameFull = ((req.getParameter("goNameFull") == null) ? "" : req.getParameter("goNameFull"));
                goNameShort = ((req.getParameter("goNameShort") == null) ? "" : req.getParameter("goNameShort"));
                goNameEn = ((req.getParameter("goNameEn") == null) ? "" : req.getParameter("goNameEn"));
                buConditon = ((req.getParameter("buConditon") == null) ? "" : req.getParameter("buConditon"));
                buType = ((req.getParameter("buType") == null) ? "" : req.getParameter("buType"));
                taskmaster = ((req.getParameter("taskmaster") == null) ? "" : req.getParameter("taskmaster"));
                masterPost = ((req.getParameter("masterPost") == null) ? "" : req.getParameter("masterPost"));
                masterTel = ((req.getParameter("masterTel") == null) ? "" : req.getParameter("masterTel"));
                masterFax = ((req.getParameter("masterFax") == null) ? "" : req.getParameter("masterFax"));
                masterMail = ((req.getParameter("masterMail") == null) ? "" : req.getParameter("masterMail"));
                creditName = ((req.getParameter("creditName") == null) ? "" : req.getParameter("creditName"));
                relation = ((req.getParameter("relation") == null) ? "" : req.getParameter("relation"));
                ZIPCODE = ((req.getParameter("ZIPCODE") == null) ? "" : req.getParameter("ZIPCODE"));
                ADDR = ((req.getParameter("ADDR") == null) ? "" : req.getParameter("ADDR"));
                address2 = ((req.getParameter("address2") == null) ? "" : req.getParameter("address2"));
                telNum = ((req.getParameter("telNum") == null) ? "" : req.getParameter("telNum"));
                faxNum = ((req.getParameter("faxNum") == null) ? "" : req.getParameter("faxNum"));
                organCode = ((req.getParameter("organCode") == null) ? "" : req.getParameter("organCode"));
                goodsMaster = ((req.getParameter("goodsMaster") == null) ? "" : req.getParameter("goodsMaster"));
                permitCode = ((req.getParameter("permitCode") == null) ? "" : req.getParameter("permitCode"));
                province = ((req.getParameter("province") == null) ? "" : req.getParameter("province"));
                eCitation = ((req.getParameter("eCitation") == null) ? "" : req.getParameter("eCitation"));
                spOffice = ((req.getParameter("spOffice") == null) ? "" : req.getParameter("spOffice"));
                branchOffi = ((req.getParameter("branchOffi") == null) ? "" : req.getParameter("branchOffi"));
                trDistance = ((req.getParameter("trDistance") == null) ? "" : req.getParameter("trDistance"));
                homepage = ((req.getParameter("homepage") == null) ? "" : req.getParameter("homepage"));
                createDate = ((req.getParameter("createDate") == null) ? "" : req.getParameter("createDate"));
                masterID = ((req.getParameter("masterID") == null) ? "" : req.getParameter("masterID"));
                delete = ((req.getParameter("delete") == null) ? "" : req.getParameter("delete"));
                moDate = ((req.getParameter("moDate") == null) ? "" : req.getParameter("moDate"));
                codename = ((req.getParameter("codename") == null) ? "" : req.getParameter("codename"));
                codd = ((req.getParameter("codd") == null) ? "" : req.getParameter("codd"));
                locaCode = ((req.getParameter("locaCode") == null) ? "" : req.getParameter("locaCode"));
                saupNo_0 = ((req.getParameter("saupNo") == null) ? "" : req.getParameter("saupNo"));
                license = ((req.getParameter("license") == null) ? "" : req.getParameter("license"));
                newCode = ((req.getParameter("newCode") == null) ? "" : req.getParameter("newCode"));
                lic = ((req.getParameter("lic") == null) ? "" : req.getParameter("lic"));
                masterTel2 = ((req.getParameter("masterTel") == null) ? "" : req.getParameter("masterTel"));
                masterFax2 = ((req.getParameter("masterFax") == null) ? "" : req.getParameter("masterFax"));
                telNum2 = ((req.getParameter("telNum") == null) ? "" : req.getParameter("telNum"));
                faxNum2 = ((req.getParameter("faxNum") == null) ? "" : req.getParameter("faxNum"));
                modify = ((req.getParameter("modify") == null) ? "" : req.getParameter("modify"));
                if (saupNo_0.length() == 12) {
                    saupNo_2 = saupNo_0.substring(0, 3);
                    saupNo_3 = saupNo_0.substring(4, 6);
                    saupNo_4 = saupNo_0.substring(7, 12);
                    saupNo = String.valueOf(saupNo_2) + saupNo_3 + saupNo_4;
                }
                else {
                    saupNo = "";
                }
                tel_1 = ((req.getParameter("tel_1") == null) ? "" : req.getParameter("tel_1"));
                tel_2 = ((req.getParameter("tel_2") == null) ? "" : req.getParameter("tel_2"));
                telNum = String.valueOf(tel_1) + "-" + tel_2;
                fax_1 = ((req.getParameter("fax_1") == null) ? "" : req.getParameter("fax_1"));
                fax_2 = ((req.getParameter("fax_2") == null) ? "" : req.getParameter("fax_2"));
                faxNum = String.valueOf(fax_1) + "-" + fax_2;
                mTel_1 = ((req.getParameter("mTel_1") == null) ? "" : req.getParameter("mTel_1"));
                mTel_2 = ((req.getParameter("mTel_2") == null) ? "" : req.getParameter("mTel_2"));
                masterTel = String.valueOf(mTel_1) + "-" + mTel_2;
                mFax_1 = ((req.getParameter("mFax_1") == null) ? "" : req.getParameter("mFax_1"));
                mFax_2 = ((req.getParameter("mFax_2") == null) ? "" : req.getParameter("mFax_2"));
                masterFax = String.valueOf(mFax_1) + "-" + mFax_2;
                mathCode = ((req.getParameter("mathCode") == null) ? "" : req.getParameter("mathCode"));
                i02 = ((req.getParameter("i02") == null) ? "" : req.getParameter("i02"));
                i3 = ((req.getParameter("i03") == null) ? "" : req.getParameter("i03"));
                i4 = ((req.getParameter("i04") == null) ? "" : req.getParameter("i04"));
                i5 = ((req.getParameter("i05") == null) ? "" : req.getParameter("i05"));
                i6 = ((req.getParameter("i06") == null) ? "" : req.getParameter("i06"));
                i7 = ((req.getParameter("i07") == null) ? "" : req.getParameter("i07"));
                String count = "0";
                final String code = "";
                final String[] mathCode2 = this.getParams(req, "mathCode", "");
                final String[] mathCode3 = this.getParams(req, "mathCode2", "");
                final String Relation = relation.substring(1, 2);
                final int gjTotalCount = (req.getParameter("gjTotalCount") == null || req.getParameter("gjTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("gjTotalCount"));
//                while (true) {
         
                        if (!flag.equals("i")) {
                            
                        }
                        sb.append("select count(*) from 사용_공공기관마스터 where 공공기관코드 = ?");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, g2bCode);
                        rs = psmt.executeQuery();
                        if (rs.next()) {
                            count = rs.getString(1);
                            psmt.clearParameters();
                        }
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
                        if (count.equals("1")) {
                            res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=1");
                        }
                        else {
                            sb.setLength(0);
                            sb.append("select count(*) from 사용_공공기관회계코드 where 공공기관코드 = ? and 공공기관회계코드 = ? ");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, g2bCode);
                            psmt.setString(2, mathCode);
                            rs = psmt.executeQuery();
                            if (rs.next()) {
                                count = rs.getString(1);
                                psmt.clearParameters();
                            }
                            if (rs != null) {
                                try {
                                    rs.close();
                                }
                                catch (Exception ex3) {}
                            }
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex4) {}
                            }
                            if (!count.equals("1")) {
                                try {
                                    sb.setLength(0);
                                    if (newCode.equals("1")) {
                                        sql = "SELECT 'Z'||lpad((substr(max(공공기관코드), 2, 7)+1), 6, '0') \n    FROM   \n   사용_공공기관마스터  where 공공기관코드 like 'Z0%' ";
                                        psmt = con.prepareStatement(sql);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            g2bCode = rs.getString(1);
                                            psmt.clearParameters();
                                        }
                                    }
                                    else if (newCode.equals("2")) {
                                        sql = "SELECT 'ZD'||lpad((substr(max(공공기관코드), 3, 7)+1), 5, '0')\n from 사용_공공기관마스터  where 공공기관코드 like 'ZD%'";
                                        psmt = con.prepareStatement(sql);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            g2bCode = rs.getString(1);
                                            psmt.clearParameters();
                                        }
                                    }
                                    else if (newCode.equals("3")) {
                                        sql = "SELECT 'ZI'||lpad((substr(max(공공기관코드), 3, 7)+1), 5, '0') \n   FROM   \n  사용_공공기관마스터  where 공공기관코드 like 'ZI%' ";
                                        psmt = con.prepareStatement(sql);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            g2bCode = rs.getString(1);
                                            psmt.clearParameters();
                                        }
                                    }
                                    else if (newCode.equals("4")) {
                                        sql = "SELECT 'ZP'||lpad((substr(max(공공기관코드), 3, 7)+1), 5, '0') \n   FROM   \n  사용_공공기관마스터  where 공공기관코드 like 'ZP%' ";
                                        psmt = con.prepareStatement(sql);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            g2bCode = rs.getString(1);
                                            psmt.clearParameters();
                                        }
                                    }
                                    else if (newCode.equals("5")) {
                                        sql = "SELECT 'ZR'||lpad((substr(max(공공기관코드), 3, 7)+1), 5, '0') \n   FROM   \n  사용_공공기관마스터  where 공공기관코드 like 'ZR%' ";
                                        psmt = con.prepareStatement(sql);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            g2bCode = rs.getString(1);
                                            psmt.clearParameters();
                                        }
                                    }
                                    else if (newCode.equals("6")) {
                                        sql = "SELECT 'ZC'||lpad((substr(max(공공기관코드), 3, 7)+1), 5, '0') \n   FROM   \n  사용_공공기관마스터  where 공공기관코드 like 'ZC%' ";
                                        psmt = con.prepareStatement(sql);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            g2bCode = rs.getString(1);
                                            psmt.clearParameters();
                                        }
                                    }
                                    sql = "insert into 사용_공공기관마스터 (공공기관코드,공공기관명_전체,공공기관명_약어,공공기관명_영문,사업자등록번호,업태, 업종,담당자명,담당자부서명, 담당자전화번호,담당자팩스번호,담당자메일주소,채권자명,소관구분,우편번호,주소,나머지주소,전화번호,팩스번호,전수요기관코드,물품관리관명,양허코드,관할지청,전자인증여부,특정관리기관,지청구분,운송거리,홈페이지주소,등록일자,처리자ID, 삭제여부, 갱신일자, 지역코드) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?, ?, to_date(sysdate), ?, 'N',?,?)";
                                    try {
                                        psmt = con.prepareStatement(sql);
                                        psmt.setString(1, g2bCode);
                                        psmt.setString(2, goNameFull);
                                        psmt.setString(3, goNameShort);
                                        psmt.setString(4, goNameEn);
                                        psmt.setString(5, saupNo);
                                        psmt.setString(6, buConditon);
                                        psmt.setString(7, buType);
                                        psmt.setString(8, taskmaster);
                                        psmt.setString(9, masterPost);
                                        psmt.setString(10, masterTel);
                                        psmt.setString(11, masterFax);
                                        psmt.setString(12, masterMail);
                                        psmt.setString(13, creditName);
                                        psmt.setString(14, relation);
                                        psmt.setString(15, ZIPCODE);
                                        psmt.setString(16, ADDR);
                                        psmt.setString(17, address2);
                                        psmt.setString(18, telNum);
                                        psmt.setString(19, faxNum);
                                        psmt.setString(20, organCode);
                                        psmt.setString(21, goodsMaster);
                                        psmt.setString(22, permitCode);
                                        psmt.setString(23, province);
                                        psmt.setString(24, eCitation);
                                        psmt.setString(25, spOffice);
                                        psmt.setString(26, branchOffi);
                                        psmt.setString(27, trDistance);
                                        psmt.setString(28, homepage);
                                        psmt.setString(29, masterID);
                                        psmt.setString(30, moDate);
                                        psmt.setString(31, locaCode);
                                        psmt.executeUpdate();
                                        psmt.clearParameters();
                                    }
                                    catch (SQLException e) {
                                        try {
                                            con.rollback();
                                        }
                                        catch (SQLException ex5) {}
                                        if (psmt != null) {
                                            try {
                                                psmt.close();
                                            }
                                            catch (Exception ex6) {}
                                        }
                                        Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                                        Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                                        e.printStackTrace();
                                    }
                                    if (g2bCode.substring(0, 1).equals("B")) {
                                        sb.setLength(0);
                                        sb.append("select 기관유형_대 from 사용_행정표준정보 where 행정표준기관코드 = ?");
                                        sql = sb.toString();
                                        psmt = con.prepareStatement(sql);
                                        psmt.setString(1, g2bCode);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            gcode = rs.getString(1);
                                            psmt.clearParameters();
                                            rs.close();
                                        }
                                        if (gcode.equals("07")) {
                                            sql2 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '5%'";
                                            psmt = con.prepareStatement(sql2);
                                            rs = psmt.executeQuery();
                                            if (rs.next()) {
                                                ediCode = rs.getString(1);
                                                psmt.clearParameters();
                                                psmt.close();
                                            }
                                        }
                                        else {
                                            sql2 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '8%'";
                                            psmt = con.prepareStatement(sql2);
                                            rs = psmt.executeQuery();
                                            if (rs.next()) {
                                                ediCode = rs.getString(1);
                                                psmt.clearParameters();
                                                psmt.close();
                                            }
                                        }
                                    }
                                    if (newCode.equals("1") || newCode.equals("6")) {
                                        sql2 = "select max(수요기관코드) from syn_수요기관 where 수요기관코드 like '6%'";
                                        psmt = con.prepareStatement(sql2);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            ediCode2 = rs.getString(1);
                                            lstr2 = Long.parseLong(ediCode2);
                                            psmt.clearParameters();
                                            psmt.close();
                                        }
                                        sql3 = "select max(수요기관코드) from syn_수요기관 where 수요기관코드 like '7%'";
                                        psmt = con.prepareStatement(sql3);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            ediCode3 = rs.getString(1);
                                            lstr3 = Long.parseLong(ediCode3);
                                            psmt.clearParameters();
                                            psmt.close();
                                        }
                                        sql4 = "select max(수요기관코드) from syn_수요기관 where 수요기관코드 like '8%'";
                                        psmt = con.prepareStatement(sql4);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            ediCode4 = rs.getString(1);
                                            lstr4 = Long.parseLong(ediCode4);
                                            psmt.clearParameters();
                                            psmt.close();
                                        }
                                        if (lstr2 < 699999999L) {
                                            sql4 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '6%'";
                                            psmt = con.prepareStatement(sql4);
                                            rs = psmt.executeQuery();
                                            if (rs.next()) {
                                                ediData = rs.getString(1);
                                                psmt.clearParameters();
                                                psmt.close();
                                            }
                                        }
                                        else if (lstr3 < 799999999L) {
                                            sql4 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '7%'";
                                            psmt = con.prepareStatement(sql4);
                                            rs = psmt.executeQuery();
                                            if (rs.next()) {
                                                ediData = rs.getString(1);
                                                psmt.clearParameters();
                                                psmt.close();
                                            }
                                        }
                                        else if (lstr4 < 899999999L) {
                                            sql4 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '8%'";
                                            psmt = con.prepareStatement(sql4);
                                            rs = psmt.executeQuery();
                                            if (rs.next()) {
                                                ediData = rs.getString(1);
                                                psmt.clearParameters();
                                                psmt.close();
                                            }
                                        }
                                    }
                                    else if (newCode.equals("2")) {
                                        sql4 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '124%'";
                                        psmt = con.prepareStatement(sql4);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            ediData = rs.getString(1);
                                            psmt.clearParameters();
                                            psmt.close();
                                        }
                                    }
                                    else if (newCode.equals("3")) {
                                        sql4 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '103%'";
                                        psmt = con.prepareStatement(sql4);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            ediData = rs.getString(1);
                                            psmt.clearParameters();
                                            psmt.close();
                                        }
                                    }
                                    else if (newCode.equals("4")) {
                                        sql4 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '152%'";
                                        psmt = con.prepareStatement(sql4);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            ediData = rs.getString(1);
                                            psmt.clearParameters();
                                            psmt.close();
                                        }
                                    }
                                    else if (newCode.equals("5")) {
                                        sql4 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '233%'";
                                        psmt = con.prepareStatement(sql4);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            ediData = rs.getString(1);
                                            psmt.clearParameters();
                                            psmt.close();
                                        }
                                    }
                                    else {
                                        sql2 = "select 최상위기관코드 from 사용_행정표준정보 where 행정표준기관코드='" + g2bCode + "'";
                                        psmt = con.prepareStatement(sql2);
                                        rs = psmt.executeQuery();
                                        if (rs.next()) {
                                            organ = rs.getString(1);
                                            psmt.clearParameters();
                                        }
                                        sb.setLength(0);
                                        if (organ != "") {
                                            sql3 = "select 수요기관코드 from syn_매핑코드기관 where 공공기관코드='" + organ + "'";
                                            psmt = con.prepareStatement(sql3);
                                            rs = psmt.executeQuery();
                                            if (rs.next()) {
                                                ediCode = rs.getString(1);
                                                psmt.clearParameters();
                                            }
                                            ediCode2 = ediCode.substring(0, 3);
                                            sql4 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '" + ediCode2 + "%'";
                                            psmt = con.prepareStatement(sql4);
                                            rs = psmt.executeQuery();
                                            if (rs.next()) {
                                                ediData = rs.getString(1);
                                                psmt.clearParameters();
                                            }
                                        }
                                        else {
                                            sql4 = "select max(수요기관코드)+1 from syn_수요기관 where 수요기관코드 like '9%'";
                                            psmt = con.prepareStatement(sql4);
                                            rs = psmt.executeQuery();
                                            if (rs.next()) {
                                                ediData = rs.getString(1);
                                                psmt.clearParameters();
                                            }
                                        }
                                    }
                                    if (relation.equals("03") || relation.equals("01") || relation.equals("02") || relation.equals("06")) {
                                        relate = "1";
                                    }
                                    else if (relation.equals("04")) {
                                        relate = "2";
                                    }
                                    else if (relation.equals("05")) {
                                        relate = "3";
                                    }
                                    else {
                                        relate = "7";
                                    }
                                    sql = "INSERT INTO SYN_수요기관 (                                                                           \n 수요기관코드,   지청구분,   수요기관명, 영문기관명, 우편번호,       수요기관주소,   전화번호,       \n 물품관리관명,   소관구분,   입력일자,   지역코드,   삭제여부,       수정일자,       양허코드,       \n FAX번호,        운송거리,   업태,       종목,       사업자등록번호, 관할지청,       특정관리기관)   \n VALUES                                                                                              \n (?, ?, ?, ?, ?, ?, ?,  \n  ?, ?, sysdate, ?, ?, sysdate, ?,  \n  ?, ?, ?, ?, ?, ?, ?)  \n";
                                    try {
                                        psmt = con.prepareStatement(sql);
                                        psmt.setString(1, ediData);
                                        psmt.setString(2, branchOffi);
                                        psmt.setString(3, goNameFull);
                                        psmt.setString(4, goNameEn);
                                        psmt.setString(5, ZIPCODE);
                                        psmt.setString(6, String.valueOf(ADDR) + " " + address2);
                                        psmt.setString(7, telNum);
                                        psmt.setString(8, goodsMaster);
                                        psmt.setString(9, Relation);
                                        psmt.setString(10, locaCode.substring(0, 2));
                                        psmt.setString(11, "N");
                                        psmt.setString(12, permitCode);
                                        psmt.setString(13, faxNum);
                                        psmt.setString(14, trDistance);
                                        psmt.setString(15, buConditon);
                                        psmt.setString(16, buType);
                                        psmt.setString(17, saupNo);
                                        psmt.setString(18, province);
                                        psmt.setString(19, spOffice);
                                        psmt.executeUpdate();
                                        psmt.clearParameters();
                                    }
                                    catch (SQLException e) {
                                        try {
                                            con.rollback();
                                        }
                                        catch (SQLException ex7) {}
                                        if (psmt != null) {
                                            try {
                                                psmt.close();
                                            }
                                            catch (Exception ex8) {}
                                        }
                                        Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                                        Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                                        e.printStackTrace();
                                        
                                    }
                                    try {
                                        sql2 = "insert into SYN_매핑코드기관 values(?,  ?,'Y')";
                                        psmt = con.prepareStatement(sql2);
                                        psmt.setString(1, g2bCode);
                                        psmt.setString(2, ediData);
                                        psmt.executeUpdate();
                                        psmt.clearParameters();
                                    }
                                    catch (SQLException e) {
                                        try {
                                            con.rollback();
                                        }
                                        catch (SQLException ex9) {}
                                        if (psmt != null) {
                                            try {
                                                psmt.close();
                                            }
                                            catch (Exception ex10) {}
                                        }
                                        Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                                        Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                                        e.printStackTrace();
                                       
                                    }
                                    sb.setLength(0);
                                    try {
                                        final StringTokenizer st = new StringTokenizer(mathCode, ",");
                                        while (st.hasMoreTokens()) {
                                            Del = st.nextToken();
                                            sql2 = "insert into 사용_공공기관회계코드   values(?,  ?)";
                                            psmt = con.prepareStatement(sql2);
                                            psmt.setString(1, g2bCode);
                                            psmt.setString(2, Del);
                                            psmt.executeUpdate();
                                            psmt.clearParameters();
                                        }
                                    }
                                    catch (SQLException e) {
                                        try {
                                            con.rollback();
                                        }
                                        catch (SQLException ex11) {}
                                        if (psmt != null) {
                                            try {
                                                psmt.close();
                                            }
                                            catch (Exception ex12) {}
                                        }
                                        Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                                        Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                                        e.printStackTrace();
                                      
                                    }
                                    sb.setLength(0);
                                    try {
                                        final StringTokenizer st = new StringTokenizer(mathCode, ",");
                                        while (st.hasMoreTokens()) {
                                            Del = st.nextToken();
                                            sql2 = "insert into 수요기관회계코드@DBL_USEMN_P_CUST  values(?,  ?)";
                                            psmt = con.prepareStatement(sql2);
                                            psmt.setString(1, ediData);
                                            psmt.setString(2, Del);
                                            psmt.executeUpdate();
                                            psmt.clearParameters();
                                        }
                                    }
                                    catch (SQLException e) {
                                        try {
                                            con.rollback();
                                        }
                                        catch (SQLException ex13) {}
                                        if (psmt != null) {
                                            try {
                                                psmt.close();
                                            }
                                            catch (Exception ex14) {}
                                        }
                                        Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                                        Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                                        e.printStackTrace();
                                       
                                    }
                                    con.commit();
                                    con.setAutoCommit(true);
                                    if (license.equals("Y") && lic.equals("1")) {
                                        res.sendRedirect("/jsp/AD/UM_ADJ_RegUserKICA.jsp?g2bCode=" + g2bCode);
                                    }
                                    else if (license.equals("Y") && lic.equals("2")) {
                                        res.sendRedirect("/jsp/AD/UM_ADJ_RegUserYn.jsp?g2bCode=" + g2bCode);
                                    }
                                    else if (license.equals("Y") && lic.equals("3")) {
                                        res.sendRedirect("/jsp/AD/UM_ADJ_RegUserSK.jsp?g2bCode=" + g2bCode);
                                    }
                                    res.sendRedirect("/jsp/AD/UM_ADJ_GovrA020s.jsp?g2bCode=" + g2bCode);
                                   
                                }
                                catch (SQLException e) {
                                    try {
                                        con.rollback();
                                    }
                                    catch (SQLException ex15) {}
                                    if (psmt != null) {
                                        try {
                                            psmt.close();
                                        }
                                        catch (Exception ex16) {}
                                    }
                                    Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                                    Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                                    e.printStackTrace();
                                   
                                }
                                catch (Exception e2) {
                                    Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                                    Log.debug("Exception발생 사유 : " + e2.toString());
                                    e2.printStackTrace();
                                   
                                }
                          
                            }
                            res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=2");
                        }
//                        return;
//                    }
                    if (flag.equals("m")) {
                        try {
                            try {
                                sql = " INSERT INTO 사용_공공기관이력 (공공기관코드, 변경일자, 공공기관명_전체, 공공기관명_영문, 사업자등록번호, 나머지주소, 처리자ID, 특정관리기관) VALUES (?, sysdate,?,?,?,?,?, ?)";
                                psmt = con.prepareStatement(sql);
                                psmt.setString(1, g2bCode);
                                psmt.setString(2, i02);
                                psmt.setString(3, i3);
                                psmt.setString(4, i4);
                                psmt.setString(5, i5);
                                psmt.setString(6, i6);
                                psmt.setString(7, i7);
                                psmt.executeUpdate();
                                psmt.clearParameters();
                            }
                            catch (SQLException e) {
                                try {
                                    con.rollback();
                                }
                                catch (SQLException ex17) {}
                                if (psmt != null) {
                                    try {
                                        psmt.close();
                                    }
                                    catch (Exception ex18) {}
                                }
                                Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                                e.printStackTrace();
                                res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=4");
                              
                            }
                            try {
                                sql = "UPDATE 사용_공공기관마스터 SET                                                                                   \n         공공기관코드=?,     공공기관명_전체=?,      공공기관명_약어=?,  공공기관명_영문=?,  사업자등록번호=?,   \n         업태=?,             업종=?,                 담당자명=?,         담당자부서명=?,     담당자전화번호=?,   \n         담당자팩스번호=?,   담당자메일주소=?,       채권자명=?,         소관구분=?,         우편번호=?,         \n         주소=?,             나머지주소=?,           전화번호=?,         팩스번호=?,         홈페이지주소=?,     \n         처리자ID=?,         갱신일자= to_date(sysdate),                 지역코드=?,         물품관리관명=?,     \n         특정관리기관=?                                                                                          \nWHERE 공공기관코드= ?";
                                psmt = con.prepareStatement(sql);
                                psmt.setString(1, g2bCode);
                                psmt.setString(2, goNameFull);
                                psmt.setString(3, goNameShort);
                                psmt.setString(4, goNameEn);
                                psmt.setString(5, saupNo);
                                psmt.setString(6, buConditon);
                                psmt.setString(7, buType);
                                psmt.setString(8, taskmaster);
                                psmt.setString(9, masterPost);
                                psmt.setString(10, masterTel2);
                                psmt.setString(11, masterFax2);
                                psmt.setString(12, masterMail);
                                psmt.setString(13, creditName);
                                psmt.setString(14, relation);
                                psmt.setString(15, ZIPCODE);
                                psmt.setString(16, ADDR);
                                psmt.setString(17, address2);
                                psmt.setString(18, telNum2);
                                psmt.setString(19, faxNum2);
                                psmt.setString(20, homepage);
                                psmt.setString(21, masterID);
                                psmt.setString(22, locaCode);
                                psmt.setString(23, goodsMaster);
                                psmt.setString(24, i7);
                                psmt.setString(25, g2bCode);
                                psmt.executeUpdate();
                                psmt.clearParameters();
                                if (psmt != null) {
                                    try {
                                        psmt.close();
                                    }
                                    catch (Exception ex19) {}
                                }
                            }
                            catch (SQLException e) {
                                try {
                                    con.rollback();
                                }
                                catch (SQLException ex20) {}
                                if (psmt != null) {
                                    try {
                                        psmt.close();
                                    }
                                    catch (Exception ex21) {}
                                }
                                Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                                e.printStackTrace();
                      
                            }
                            try {
                                sql = "select 수요기관코드 from SYN_매핑코드기관 where 공공기관코드 = '" + g2bCode + "' ";
                                psmt = con.prepareStatement(sql);
                                rs = psmt.executeQuery();
                                if (rs.next()) {
                                    ediCode = rs.getString(1);
                                    psmt.clearParameters();
                                }
                                sb.setLength(0);
                                if (relation.equals("01")) {
                                    relate = "1";
                                }
                                else if (relation.equals("02")) {
                                    relate = "2";
                                }
                                else if (relation.equals("03")) {
                                    relate = "3";
                                }
                                else if (relation.equals("05")) {
                                    relate = "5";
                                }
                                else {
                                    relate = "7";
                                }
                                sql = "update SYN_수요기관 set  지청구분=?, 수요기관명=?,  영문기관명=?, 우편번호=?, 수요기관주소=?, 전화번호=?,물품관리관명=?, 소관구분=?, 지역코드=?, 삭제여부='N', 수정일자=sysdate, 양허코드=?, FAX번호=?, 운송거리=?, 업태=?, 종목=?, 사업자등록번호=?,관할지청=?,특정관리기관=?  where 수요기관코드= '" + ediCode + "' ";
                                psmt = con.prepareStatement(sql);
                                psmt = con.prepareStatement(sql);
                                psmt.setString(1, branchOffi);
                                psmt.setString(2, goNameFull);
                                psmt.setString(3, goNameEn);
                                psmt.setString(4, ZIPCODE);
                                psmt.setString(5, String.valueOf(ADDR) + " " + address2);
                                psmt.setString(6, telNum2);
                                psmt.setString(7, goodsMaster);
                                psmt.setString(8, Relation);
                                psmt.setString(9, locaCode.substring(0, 2));
                                psmt.setString(10, permitCode);
                                psmt.setString(11, faxNum2);
                                psmt.setString(12, trDistance);
                                psmt.setString(13, buConditon);
                                psmt.setString(14, buType);
                                psmt.setString(15, saupNo);
                                psmt.setString(16, province);
                                psmt.setString(17, spOffice);
                                psmt.executeUpdate();
                                psmt.clearParameters();
                            }
                            catch (SQLException e) {
                                try {
                                    con.rollback();
                                }
                                catch (SQLException ex22) {}
                                if (psmt != null) {
                                    try {
                                        psmt.close();
                                    }
                                    catch (Exception ex23) {}
                                }
                                Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                                e.printStackTrace();
                                res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=4");
                            }
                            con.commit();
                            con.setAutoCommit(true);
                            if (modify.equals("Y")) {
                                res.sendRedirect("/jsp/GO/UM_GOJ_GoviA030s.jsp?mCode=" + g2bCode);
                               
                            }
                            res.sendRedirect("/jsp/AD/UM_ADJ_GovrB030s.jsp?code=" + g2bCode);
                        
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex24) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex25) {}
                            }
                            Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                            Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                            
                        }
                    }
                    if (flag.equals("d")) {
                        try {
                            sql = "select 수요기관코드 from SYN_매핑코드기관 where 공공기관코드 = '" + g2bCode + "' ";
                            psmt = con.prepareStatement(sql);
                            rs = psmt.executeQuery();
                            if (rs.next()) {
                                ediCode = rs.getString(1);
                                psmt.clearParameters();
                            }
                            sql = "update 사용_공공기관마스터 set 삭제여부='Y' where 공공기관코드=? ";
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, g2bCode);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            sql2 = "update syn_수요기관 set 삭제여부='Y' where 수요기관코드=? ";
                            psmt = con.prepareStatement(sql2);
                            psmt.setString(1, ediCode);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex26) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex27) {}
                            }
                            Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                            Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                            res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=4");
                            
                        }
                        con.commit();
                        con.setAutoCommit(true);
                        res.sendRedirect("/jsp/AD/UM_ADJ_GovrB010l.jsp");
                        
                    }
                    if (flag.equals("e")) {
                        try {
                            sql = "delete from 사용_공공기관회계코드 where 공공기관코드=? and  공공기관회계코드=? ";
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, g2bCode);
                            psmt.setString(2, codd);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex28) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex29) {}
                            }
                            Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                            Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                            res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=4");
                        }
                        try {
                            sql2 = "select 수요기관코드 from SYN_매핑코드기관 where 공공기관코드 = '" + g2bCode + "' ";
                            psmt = con.prepareStatement(sql2);
                            rs = psmt.executeQuery();
                            if (rs.next()) {
                                ediCode = rs.getString(1);
                                psmt.clearParameters();
                            }
                            sql = "delete from SYN_수요기관회계코드 where 수요기관코드=? and  수요기관회계코드=? ";
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, ediCode);
                            psmt.setString(2, codd);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex30) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex31) {}
                            }
                            Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                            Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                            res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=4");
                       
                        }
                        con.commit();
                        con.setAutoCommit(true);
                        res.sendRedirect("/jsp/AD/UM_ADJ_GovrB010m.jsp?code=" + g2bCode);
                        
                    }
                    if (flag.equals("g")) {
                        sb.setLength(0);
                        try {
                            sql = "update 사용_공공기관회계코드 set  공공기관회계코드=? where 공공기관코드=? and 공공기관회계코드=? ";
                            psmt = con.prepareStatement(sql);
                            for (int j = 0; j < gjTotalCount; ++j) {
                                psmt.setString(1, mathCode2[j]);
                                psmt.setString(2, g2bCode);
                                psmt.setString(3, mathCode3[j]);
                                psmt.executeUpdate();
                                psmt.clearParameters();
                            }
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex32) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex33) {}
                            }
                            Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                            Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                            res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=4");
                        }
                        sb.setLength(0);
                        try {
                            sql2 = "select 수요기관코드 from SYN_매핑코드기관 where 공공기관코드 = '" + g2bCode + "' ";
                            psmt = con.prepareStatement(sql2);
                            rs = psmt.executeQuery();
                            if (rs.next()) {
                                ediCode = rs.getString(1);
                                psmt.clearParameters();
                            }
                            sql = "update SYN_수요기관회계코드 set  수요기관회계코드=? where 수요기관코드=? and 수요기관회계코드=? ";
                            psmt = con.prepareStatement(sql);
                            for (int j = 0; j < gjTotalCount; ++j) {
                                psmt.setString(1, mathCode2[j]);
                                psmt.setString(2, ediCode);
                                psmt.setString(3, mathCode3[j]);
                                psmt.executeUpdate();
                                psmt.clearParameters();
                            }
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex34) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex35) {}
                            }
                            Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                            Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                            res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=4");
                        }
                        con.commit();
                        con.setAutoCommit(true);
                        res.sendRedirect("/jsp/AD/UM_ADJ_GovrB010m.jsp?code=" + g2bCode);
                        
                    }
                    if (flag.equals("h")) {
                        sb.setLength(0);
                        final StringTokenizer str = new StringTokenizer(codename, ",");
                        while (str.hasMoreTokens()) {
                            Del = str.nextToken();
                            sb.append("select count(*) from 사용_공공기관회계코드 where 공공기관코드 = ? and 공공기관회계코드 = ? ");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, g2bCode);
                            psmt.setString(2, Del);
                            rs = psmt.executeQuery();
                            if (rs.next()) {
                                count = rs.getString(1);
                                psmt.clearParameters();
                            }
                            if (rs != null) {
                                try {
                                    rs.close();
                                }
                                catch (Exception ex36) {}
                            }
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex37) {}
                            }
                            if (count.equals("1")) {
                                res.sendRedirect("/html/error.htm");
                            }
                            sb.setLength(0);
                        }
                        try {
                            final StringTokenizer st2 = new StringTokenizer(codename, ",");
                            while (st2.hasMoreTokens()) {
                                Del = st2.nextToken();
                                sql2 = "insert into 사용_공공기관회계코드   values(?,  ?)";
                                psmt = con.prepareStatement(sql2);
                                psmt.setString(1, g2bCode);
                                psmt.setString(2, Del);
                                psmt.executeUpdate();
                                psmt.clearParameters();
                            }
                        }
                        catch (SQLException e3) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex38) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex39) {}
                            }
                            Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                            Log.debug("Exception발생 사유 : " + e3.toString() + e3.getErrorCode() + e3.getSQLState());
                            e3.printStackTrace();
                            res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=4");
                        }
                        try {
                            sql = "select 수요기관코드 from SYN_매핑코드기관 where 공공기관코드 = '" + g2bCode + "' ";
                            psmt = con.prepareStatement(sql);
                            rs = psmt.executeQuery();
                            if (rs.next()) {
                                ediCode = rs.getString(1);
                                psmt.clearParameters();
                            }
                            final StringTokenizer str2 = new StringTokenizer(codename, ",");
                            while (str2.hasMoreTokens()) {
                                Del = str2.nextToken();
                                sql2 = "insert into SYN_수요기관회계코드 values(?,  ?)";
                                psmt = con.prepareStatement(sql2);
                                psmt.setString(1, ediCode);
                                psmt.setString(2, Del);
                                psmt.executeUpdate();
                                psmt.clearParameters();
                            }
                        }
                        catch (SQLException e3) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex40) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex41) {}
                            }
                            Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                            Log.debug("Exception발생 사유 : " + e3.toString() + e3.getErrorCode() + e3.getSQLState());
                            e3.printStackTrace();
                            res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=4");
                        }
                        con.commit();
                        con.setAutoCommit(true);
                        res.sendRedirect("/jsp/AD/UM_ADJ_GovrB010m.jsp?code=" + g2bCode);
                        
                    }
                    if (flag.equals("s")) {
                        try {
                            sb.append("select count(*) from 사용_공공기관이력 where 공공기관코드 = ? and 변경일자 =sysdate");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, g2bCode);
                            rs = psmt.executeQuery();
                            if (rs.next()) {
                                count = rs.getString(1);
                                psmt.clearParameters();
                            }
                            if (rs != null) {
                                try {
                                    rs.close();
                                }
                                catch (Exception ex42) {}
                            }
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex43) {}
                            }
                            if (count.equals("1")) {
                                res.sendRedirect("/html/error.htm");
                            }
                            sql = "insert into 사용_공공기관이력   values(?, sysdate,?,?,?,?,?, ?)";
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, g2bCode);
                            psmt.setString(2, i02);
                            psmt.setString(3, i3);
                            psmt.setString(4, i4);
                            psmt.setString(5, i5);
                            psmt.setString(6, i6);
                            psmt.setString(7, i7);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            sql = "update 사용_공공기관마스터 set 사업자등록번호 = ? where 공공기관코드 = ? ";
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, saupNo);
                            psmt.setString(2, g2bCode);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                        catch (SQLException e) {
                            try {
                                con.rollback();
                            }
                            catch (SQLException ex44) {}
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex45) {}
                            }
                            Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                            Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                            e.printStackTrace();
                            res.sendRedirect("/jsp/AD/UM_ADJ_error.jsp?code=4");
                            
                        }
                        con.commit();
                        con.setAutoCommit(true);
                        res.sendRedirect("/jsp/GO/UM_GOJ_GoviA030s.jsp?mCode=" + g2bCode);
                    }
              
                }
//            }
            catch (SQLException exc) {
                try {
                    con.rollback();
                    con.setAutoCommit(true);
                }
                catch (SQLException e4) {
                    Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                    Log.debug("Exception발생 사유 : " + e4.toString() + e4.getErrorCode() + e4.getSQLState());
                    e4.printStackTrace();
                }
                Log.debug("UM_ADJ_GovrB040c SQLException : ");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
            }
            catch (Exception exc2) {
                try {
                    con.rollback();
                    con.setAutoCommit(true);
                }
                catch (SQLException e4) {
                    Log.debug("UM_ADJ_GovrB040c Exception : Transaction Rollback간에 Exception 발생함");
                    Log.debug("Exception발생 사유 : " + e4.toString() + e4.getErrorCode() + e4.getSQLState());
                    e4.printStackTrace();
                }
                Log.debug("UM_ADJ_GovrB040c Exception : ");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                exc2.printStackTrace();
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex46) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex47) {}
                }
                if (psmt3 != null) {
                    try {
                        psmt3.close();
                    }
                    catch (Exception ex48) {}
                }
                if (psmt4 != null) {
                    try {
                        psmt4.close();
                    }
                    catch (Exception ex49) {}
                }
                if (psmt5 != null) {
                    try {
                        psmt5.close();
                    }
                    catch (Exception ex50) {}
                }
                if (psmt6 != null) {
                    try {
                        psmt6.close();
                    }
                    catch (Exception ex51) {}
                }
                if (psmt7 != null) {
                    try {
                        psmt7.close();
                    }
                    catch (Exception ex52) {}
                }
                if (trx != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex53) {}
                }
            }
        
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex54) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex55) {}
        }
        if (psmt3 != null) {
            try {
                psmt3.close();
            }
            catch (Exception ex56) {}
        }
        if (psmt4 != null) {
            try {
                psmt4.close();
            }
            catch (Exception ex57) {}
        }
        if (psmt5 != null) {
            try {
                psmt5.close();
            }
            catch (Exception ex58) {}
        }
        if (psmt6 != null) {
            try {
                psmt6.close();
            }
            catch (Exception ex59) {}
        }
        if (psmt7 != null) {
            try {
                psmt7.close();
            }
            catch (Exception ex60) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex61) {}
        }
    }
}
