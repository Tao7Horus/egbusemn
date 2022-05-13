// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.ResultSetMetaData;
import java.io.IOException;
import javax.servlet.ServletException;
import entity.UM_GOE_ConiC010b;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import java.net.URLEncoder;
import common.ComStr;
import common.Trx;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_ConrC011c extends HttpServlet
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
        String upCheck = "N";
        final String DupCheck = "N";
        String DaeCeo = null;
        String DaeJumin = null;
        String DaeMail = null;
        String DaeLice = null;
        String DaeGood = null;
        String procID = null;
        final int ilno = 1;
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt1 = null;
        PreparedStatement psmt2 = null;
        PreparedStatement psmt3 = null;
        PreparedStatement dppsmt1 = null;
        PreparedStatement dppsmt2 = null;
        PreparedStatement dppsmt3 = null;
        PreparedStatement gmpsmt1 = null;
        PreparedStatement gmpsmt2 = null;
        PreparedStatement gmpsmt3 = null;
        PreparedStatement gjpsmt1 = null;
        PreparedStatement gjpsmt2 = null;
        final PreparedStatement gjpsmt3 = null;
        PreparedStatement idpsmt1 = null;
        PreparedStatement idpsmt2 = null;
        final PreparedStatement idpsmt3 = null;
        PreparedStatement jppsmt1 = null;
        PreparedStatement jppsmt2 = null;
        PreparedStatement jppsmt3 = null;
        PreparedStatement gppsmt1 = null;
        PreparedStatement gppsmt2 = null;
        PreparedStatement gppsmt3 = null;
        ResultSet rs = null;
        String query1 = null;
        String query2 = null;
        String query3 = null;
        String dpquery1 = null;
        String dpquery2 = null;
        String dpquery3 = null;
        String gmquery1 = null;
        String gmquery2 = null;
        String gmquery3 = null;
        String gjquery1 = null;
        String gjquery2 = null;
        final String gjquery3 = null;
        String idquery1 = null;
        String idquery2 = null;
        final String idquery3 = null;
        String jpquery1 = null;
        String jpquery2 = null;
        String jpquery3 = null;
        String gpquery1 = null;
        String gpquery2 = null;
        String gpquery3 = null;
        System.out.println("===========================service 시작===================================");
        procID = "C234567890123";
        final UM_ADV_ConrC011c entity = new UM_ADV_ConrC011c();
        String flag = "";
        String saupNo = "";
        String saupNo_0 = "";
        String saupNo_2 = "";
        String saupNo_3 = "";
        String saupNo_4 = "";
        String nationality = "";
        String nation = "";
        String sangho = "";
        String eSangho = "";
        String openDate = "";
        String bubinOpenDate = "";
        final String jobGubun_1 = "";
        final String jobGubun_2 = "";
        final String jobGubun_3 = "";
        final String jobGubun_4 = "";
        final String jobGubun_5 = "";
        String makeGubun = "";
        String bubinNo = "";
        final String comGubun1 = "";
        String comGubun2 = "";
        String comGubunYear = "";
        String jabon = "";
        String employeeNo = "";
        String accountDate = "";
        String zipCode = "";
        String zipCode2 = "";
        String zipCode3 = "";
        String zipCode4 = "";
        String localCode = "";
        String localCodeName = "";
        String addr = "";
        String restAddr = "";
        String tel = "";
        String tel_1 = "";
        String tel_2 = "";
        String fax = "";
        String fax_1 = "";
        String fax_2 = "";
        String homepage = "";
        String exceptYN = "";
        String registOkDate = "";
        String registDate = "";
        String renewDate = "";
        String dpOkYN = "";
        String churijaId = "";
        String forYN = "";
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            con.setAutoCommit(false);
            System.out.println("11111111 시작====>");
            flag = ((req.getParameter("flag") == null) ? "" : req.getParameter("flag"));
            saupNo_0 = ((req.getParameter("saupNo") == null) ? "" : req.getParameter("saupNo"));
            saupNo_2 = saupNo_0.substring(0, 3);
            saupNo_3 = saupNo_0.substring(4, 6);
            saupNo_4 = saupNo_0.substring(7, 12);
            saupNo = String.valueOf(saupNo_2) + saupNo_3 + saupNo_4;
            System.out.println("1-1 시작====>");
            nationality = ((req.getParameter("nationality") == null) ? "" : req.getParameter("nationality"));
            nation = ((req.getParameter("nation") == null) ? "" : req.getParameter("nation"));
            sangho = ((req.getParameter("sangho") == null) ? "" : req.getParameter("sangho"));
            eSangho = ((req.getParameter("eSangho") == null) ? "" : req.getParameter("eSangho"));
            openDate = ((req.getParameter("openDate") == null) ? "" : req.getParameter("openDate"));
            bubinOpenDate = ((req.getParameter("bubinOpenDate") == null) ? "" : req.getParameter("bubinOpenDate"));
            System.out.println("1-2 시작====>");
            System.out.println("1-3 시작====>");
            makeGubun = ((req.getParameter("hmakeGubun") == null) ? "" : req.getParameter("hmakeGubun"));
            bubinNo = ((req.getParameter("bubinNo") == null) ? "" : req.getParameter("bubinNo"));
            comGubun2 = ((req.getParameter("comGubun2") == null) ? "" : req.getParameter("comGubun2"));
            comGubunYear = ((req.getParameter("comGubunYear") == null) ? "" : req.getParameter("comGubunYear"));
            jabon = ((req.getParameter("jabon") == null) ? "" : req.getParameter("jabon"));
            employeeNo = ((req.getParameter("employeeNo") == null) ? "" : req.getParameter("employeeNo"));
            accountDate = ((req.getParameter("accountDate") == null) ? "" : req.getParameter("accountDate"));
            System.out.println("1-4 시작====>");
            zipCode2 = ((req.getParameter("zipCode") == null) ? "" : req.getParameter("zipCode"));
            System.out.println("zipCode0====>" + zipCode2);
            System.out.println("zipCode0====>" + zipCode2.length());
            zipCode3 = zipCode2.substring(0, 3);
            System.out.println("zipCode1====>" + zipCode3);
            zipCode4 = zipCode2.substring(4, 7);
            System.out.println("zipCode2====>" + zipCode4);
            zipCode = String.valueOf(zipCode3) + zipCode4;
            System.out.println("zipCode====>" + zipCode);
            System.out.println("1-5 시작====>");
            localCode = ((req.getParameter("localCode") == null) ? "" : req.getParameter("localCode"));
            localCodeName = ((req.getParameter("localCodeName") == null) ? "" : req.getParameter("localCodeName"));
            addr = ((req.getParameter("addr") == null) ? "" : req.getParameter("addr"));
            restAddr = ((req.getParameter("restAddr") == null) ? "" : req.getParameter("restAddr"));
            System.out.println("1-6 시작====>");
            tel_1 = ((req.getParameter("tel_1") == null) ? "" : req.getParameter("tel_1"));
            tel_2 = ((req.getParameter("tel_2") == null) ? "" : req.getParameter("tel_2"));
            tel = String.valueOf(tel_1) + "-" + tel_2;
            System.out.println("1-7 시작====>");
            fax_1 = ((req.getParameter("fax_1") == null) ? "" : req.getParameter("fax_1"));
            fax_2 = ((req.getParameter("fax_2") == null) ? "" : req.getParameter("fax_2"));
            System.out.println("2222 시작====>");
            if (!fax_2.equals("")) {
                fax = String.valueOf(fax_1) + "-" + fax_2;
            }
            else {
                fax = String.valueOf(fax_1) + fax_2;
            }
            homepage = ((req.getParameter("homepage") == null) ? "" : req.getParameter("homepage"));
            exceptYN = ((req.getParameter("exceptYN") == null) ? "N" : req.getParameter("exceptYN"));
            registOkDate = ((req.getParameter("registOkDate") == null) ? "" : req.getParameter("registOkDate"));
            registDate = ((req.getParameter("registDate") == null) ? "" : req.getParameter("registDate"));
            renewDate = ((req.getParameter("renewDate") == null) ? "" : req.getParameter("renewDate"));
            dpOkYN = ((req.getParameter("dpOkYN") == null) ? "" : req.getParameter("dpOkYN"));
            churijaId = ((req.getParameter("churijaId") == null) ? "" : req.getParameter("churijaId"));
            forYN = ((req.getParameter("forYN") == null) ? "" : req.getParameter("forYN"));
            System.out.println("333 시작====>");
            UM_GOE_ConiC010b dbb = null;
            dbb = this.select_main(saupNo);
            System.out.println("444 시작====>");
            this.setSaupNo(saupNo);
            final String[] ceoJuminNo = this.getParams(req, "ceoJuminNo", "");
            final String[] ceoName = this.getParams(req, "ceoName", "");
            final String[] ceoMail = this.getParams(req, "ceoMail", "");
            final String[] ceoYN = this.getParams(req, "hceoYN", "");
            final String[] dpStatus = this.getParams(req, "dpStatus", "");
            final int dpTotalCount = (req.getParameter("dpTotalCount") == null || req.getParameter("dpTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("dpTotalCount"));
            System.out.println("555 시작====>");
            final String[][] DPb = this.getDPData();
            for (int i = 1; i < ceoName.length; ++i) {
                System.out.println("ceoName.length==" + ceoName.length);
            }
            final String[] gmLicenseCode = this.getParams(req, "gmLicenseCode", "");
            final String[] gmLicenseNo = this.getParams(req, "gmLicenseNo", "");
            final String[] gmLicenseBeginDate = this.getParams(req, "gmLicenseBeginDate", "");
            final String[] gmSigongAccount = this.getParams(req, "gmSigongAccount", "");
            final String[] gmPeonggaYear = this.getParams(req, "gmPeonggaYear", "");
            final String[] gmDpLicenseYN = this.getParams(req, "hgmDpLicenseYN", "");
            final String[] gmStatus = this.getParams(req, "gmStatus", "");
            final int gmTotalCount = (req.getParameter("gmTotalCount") == null || req.getParameter("gmTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("gmTotalCount"));
            final String[][] GMb = this.getGMData();
            for (int j = 1; j < gmLicenseNo.length; ++j) {
                System.out.println("GMbGMbGMb==" + gmLicenseNo[j] + "===" + gmStatus[j]);
            }
            final String[] ilrunNo = this.getParams(req, "ilrunNo", "");
            final String[] factoryName = this.getParams(req, "factoryName", "");
            final String[] factoryZipCode = this.getParams(req, "factoryZipCode", "");
            final String[] factoryAddr = this.getParams(req, "factoryAddr", "");
            final String[] factoryRestAddr = this.getParams(req, "factoryRestAddr", "");
            final String[] factoryTel = this.getParams(req, "factoryTel", "");
            final String[] factoryFax = this.getParams(req, "factoryFax", "");
            final String[] gjStatus = this.getParams(req, "gjStatus", "");
            final int gjTotalCount = (req.getParameter("gjTotalCount") == null || req.getParameter("gjTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("gjTotalCount"));
            for (int k = 1; k < factoryName.length; ++k) {
                System.out.println("GJbGJbGJb==" + factoryName[k] + "===" + factoryAddr[k]);
            }
            final String[] juminNo = this.getParams(req, "juminNo", "");
            final String[] name = this.getParams(req, "name", "");
            final String[] jobPart = this.getParams(req, "jobPart", "");
            final String[] dutyName = this.getParams(req, "dutyName", "");
            final String[] idtel = this.getParams(req, "idtel", "");
            final String[] mail = this.getParams(req, "mail", "");
            final String[] handphone = this.getParams(req, "handphone", "");
            final String[] idfax = this.getParams(req, "idfax", "");
            final String[] idStatus = this.getParams(req, "idStatus", "");
            final int idTotalCount = (req.getParameter("idTotalCount") == null || req.getParameter("idTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("idTotalCount"));
            for (int l = 1; l < juminNo.length; ++l) {
                System.out.println("IDbIDbIDbIDb==" + juminNo[l] + "===" + name[l]);
            }
            final String[] goodsNo = this.getParams(req, "goodsNo", "");
            final String[] formNo = this.getParams(req, "formNo", "");
            final String[] formFac = this.getParams(req, "formFac", "");
            final String[] formDate = this.getParams(req, "formDate", "");
            final String[] threeSale = this.getParams(req, "threeSale", "");
            final String[] makeYN = this.getParams(req, "hmakeYN", "");
            final String[] dpGoodsYN = this.getParams(req, "hdpGoodsYN", "");
            final String[] jpStatus = this.getParams(req, "jpStatus", "");
            final int jpTotalCount = (req.getParameter("jpTotalCount") == null || req.getParameter("jpTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("jpTotalCount"));
            final String[][] JPb = this.getJPData();
            for (int m = 1; m < goodsNo.length; ++m) {
                System.out.println("JPbJPbJPbJPb==" + goodsNo[m] + "===" + formNo[m]);
            }
            final String[] ggoodsNo = this.getParams(req, "ggoodsNo", "");
            final String[] gthreeSale = this.getParams(req, "gthreeSale", "");
            final String[] gmakeYN = this.getParams(req, "ghmakeYN", "");
            final String[] gdpGoodsYN = this.getParams(req, "ghdpGoodsYN", "");
            final String[] gpStatus = this.getParams(req, "gpStatus", "");
            final int gpTotalCount = (req.getParameter("gpTotalCount") == null || req.getParameter("gpTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("gpTotalCount"));
            final String[][] GPb = this.getGPData();
            for (int i2 = 1; i2 < ggoodsNo.length; ++i2) {}
            String upmu = "";
            String upmu2 = "0";
            String upmu3 = "0";
            String upmu4 = "0";
            String upmu5 = "0";
            for (int i3 = 1; i3 < gmTotalCount; ++i3) {
                if (gmDpLicenseYN[i3].equals("Y") && !gmStatus[i3].equals("D") && !gmStatus[i3].equals("N")) {
                    DaeLice = gmDpLicenseYN[i3];
                }
                query1 = " SELECT 코드명2 FROM SYN_공동코드  WHERE 코드구분 = 'GU9' AND 코드 = ? ";
                System.out.println("query1 => " + query1);
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, gmLicenseCode[i3].trim());
                rs = psmt1.executeQuery();
                String licenseCode = "";
                if (rs.next()) {
                    licenseCode = rs.getString(1);
                }
                if (licenseCode.equals("공사") || licenseCode.equals("5")) {
                    upmu3 = "1";
                }
                if (licenseCode.equals("용역") || licenseCode.equals("5")) {
                    upmu4 = "1";
                }
                psmt1.clearParameters();
            }
            if (goodsNo.length > 1 || ggoodsNo.length > 1) {
                upmu2 = "1";
            }
            if (forYN.equals("1")) {
                upmu5 = "1";
            }
            else {
                upmu5 = "0";
            }
            upmu = String.valueOf(upmu2) + upmu3 + upmu4 + "0" + upmu5;
            String jejoGubun = "";
            if (factoryName.length > 1) {
                jejoGubun = "02";
            }
            else {
                jejoGubun = "03";
            }
            System.out.println("upmu => " + upmu);
            System.out.println("upmu1 => " + upmu2);
            System.out.println("upmu2 => " + upmu3);
            System.out.println("upmu3 => " + upmu4);
            System.out.println("upmu4 => " + upmu5);
            System.out.println("jejoGubun => " + jejoGubun);
            System.out.println("====================일반정보 수정시작=====================");
            query1 = "SELECT COUNT(*) FROM 사용_조달업체마스터 WHERE 사업자등록번호=? ";
            psmt1 = con.prepareStatement(query1);
            psmt1.setString(1, saupNo);
            System.out.println("사업번호==>" + saupNo);
            rs = psmt1.executeQuery();
            String mainCheck = "0";
            if (rs.next()) {
                mainCheck = rs.getString(1);
            }
            psmt1.clearParameters();
            if (psmt1 != null) {
                try {
                    psmt1.close();
                }
                catch (Exception ex) {}
            }
            if (!mainCheck.equals("0")) {
                con.setAutoCommit(true);
                res.sendRedirect("/html/UM_ADJ_ConrC010i_error.htm");
                return;
            }
            query1 = " SELECT 승인지청 FROM 사용_사용자 WHERE 사용자ID = ? ";
            psmt1 = con.prepareStatement(query1);
            psmt1.setString(1, procID);
            rs = psmt1.executeQuery();
            String jicheong = "";
            if (rs.next()) {
                jicheong = rs.getString(1);
            }
            psmt1.clearParameters();
            query2 = " INSERT INTO 사용_조달업체마스터 (  사업자등록번호, 국적, 상호명, 영문상호명,  개업일자, 법인설립일자, 업무구분, 제조구분,  법인등록번호, 기업구분1, 기업구분2, 기업구분해당년도, 자본금,  종업원수, 최근결산년월, 우편번호, 지역코드, 주소,  나머지주소, 전화번호, FAX번호, 홈페이지, 특례해당여부,  등록유효일자, 등록일자, 갱신일자, 대표인증여부, 처리자ID,  승인지청  ) VALUES (  ?, ?, ?, ?,  ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ADD_MONTHS(SYSDATE, 24), SYSDATE, SYSDATE, 'N', 'aaa',  ?  ) ";
            psmt2 = con.prepareStatement(query2);
            psmt2.setString(1, saupNo);
            psmt2.setString(2, nationality);
            psmt2.setString(3, sangho);
            psmt2.setString(4, eSangho);
            psmt2.setString(5, openDate);
            psmt2.setString(6, bubinOpenDate);
            psmt2.setString(7, upmu);
            psmt2.setString(8, jejoGubun);
            psmt2.setString(9, ComStr.replace(bubinNo, "-", ""));
            psmt2.setString(10, "2");
            psmt2.setString(11, comGubun2);
            psmt2.setString(12, "2002");
            psmt2.setLong(13, Long.parseLong(ComStr.replace(jabon, ",", "")));
            psmt2.setInt(14, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
            psmt2.setString(15, "200202");
            psmt2.setString(16, zipCode);
            psmt2.setString(17, localCode);
            psmt2.setString(18, addr);
            psmt2.setString(19, restAddr);
            psmt2.setString(20, tel);
            psmt2.setString(21, fax);
            psmt2.setString(22, homepage);
            psmt2.setString(23, exceptYN);
            psmt2.setString(24, jicheong);
            psmt2.executeUpdate();
            psmt2.clearParameters();
            System.out.println("저장==>" + saupNo);
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex2) {}
            }
            query3 = " SELECT 사업자등록번호 FROM 사용_조달업체마스터 WHERE 사업자등록번호=? ";
            psmt3 = con.prepareStatement(query3);
            psmt3.setString(1, saupNo);
            rs = psmt3.executeQuery();
            while (rs.next()) {
                saupNo = rs.getString(1);
            }
            psmt3.clearParameters();
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex3) {}
            }
            System.out.println("====================대표자정보수정시작=====================");
            dpquery1 = " INSERT INTO 사용_대표자 (  사업자등록번호, 대표자주민번호, 대표자명, 대표자메일주소, 대표대표자여부  ) VALUES (  ?, ?, ?, ?, ?  ) ";
            dppsmt1 = con.prepareStatement(dpquery1);
            dpquery2 = " UPDATE 사용_대표자  SET 사업자등록번호 = ?,     대표자주민번호 = ?,     대표자명 = ?,     대표자메일주소 = ?,     대표대표자여부 = ?  WHERE 사업자등록번호 = ?    AND 대표자주민번호 = ? ";
            dppsmt2 = con.prepareStatement(dpquery2);
            dpquery3 = " DELETE FROM 사용_대표자  WHERE 사업자등록번호 = ?    AND 대표자주민번호 = ? ";
            dppsmt3 = con.prepareStatement(dpquery3);
            for (int i4 = 1; i4 < dpTotalCount; ++i4) {
                System.out.println("FOR 시작" + dpStatus[i4]);
                if (ceoYN[i4].equals("Y") && !dpStatus[i4].equals("D") && !dpStatus[i4].equals("N")) {
                    DaeCeo = ceoName[i4];
                }
                if (dpStatus[i4].equals("I")) {
                    System.out.println("FOR 시작IIIII");
                    System.out.println("사업번호==>" + saupNo);
                    dppsmt1.setString(1, saupNo);
                    dppsmt1.setString(2, ComStr.replace(ceoJuminNo[i4], "-", ""));
                    dppsmt1.setString(3, ceoName[i4].trim());
                    dppsmt1.setString(4, ceoMail[i4].trim());
                    dppsmt1.setString(5, ceoYN[i4].trim());
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                }
            }
            System.out.println("====================면허사항정보 수정시작=====================");
            gmquery1 = " INSERT INTO 사용_면허사항 (  사업자등록번호, 면허코드, 면허번호, 면허취득일자,  시공능력평가액, 평가액기준년도, 대표면허여부  ) VALUES (  ?, ?, ?, ?,  ?, ?, ?  ) ";
            gmpsmt1 = con.prepareStatement(gmquery1);
            gmquery2 = " UPDATE 사용_면허사항  SET 면허번호 = ?,     면허취득일자 = ?,     시공능력평가액 = ?,     평가액기준년도 = ?,     대표면허여부 = ?  WHERE 사업자등록번호 = ?    AND 면허코드 = ? ";
            gmpsmt2 = con.prepareStatement(gmquery2);
            gmquery3 = " DELETE FROM 사용_면허사항  WHERE 사업자등록번호 = ?    AND 면허코드 = ? ";
            gmpsmt3 = con.prepareStatement(gmquery3);
            if (gmTotalCount == 1 || gmTotalCount == 0) {
                DaeLice = "";
            }
            for (int i4 = 1; i4 < gmTotalCount; ++i4) {
                System.out.println("FOR 시작" + gmStatus[i4]);
                if (gmDpLicenseYN[i4].equals("Y") && !gmStatus[i4].equals("D") && !gmStatus[i4].equals("N")) {
                    DaeLice = gmDpLicenseYN[i4];
                }
                if (gmStatus[i4].equals("I")) {
                    System.out.println("FOR 시작IIIII");
                    System.out.println("FOR 시작  saupNo==" + saupNo);
                    gmpsmt1.setString(1, saupNo);
                    System.out.println("FOR 시작  gmLicenseCode==" + gmLicenseCode[i4].trim());
                    gmpsmt1.setString(2, gmLicenseCode[i4].trim());
                    System.out.println("FOR 시작  gmLicenseNo==" + gmLicenseNo[i4].trim());
                    gmpsmt1.setString(3, gmLicenseNo[i4].trim());
                    System.out.println("FOR 시작  gmLicenseBeginDate==" + gmLicenseBeginDate[i4].trim());
                    gmpsmt1.setString(4, gmLicenseBeginDate[i4].trim());
                    System.out.println("FOR 시작  gmSigongAccount==" + gmSigongAccount[i4]);
                    gmpsmt1.setLong(5, Long.parseLong(ComStr.replace(gmSigongAccount[i4], ",", "")));
                    System.out.println("FOR 시작  gmPeonggaYear==" + gmPeonggaYear[i4].trim());
                    gmpsmt1.setString(6, gmPeonggaYear[i4].trim());
                    System.out.println("FOR 시작  gmDpLicenseYN==" + gmDpLicenseYN[i4].trim());
                    gmpsmt1.setString(7, gmDpLicenseYN[i4].trim());
                    System.out.println("FOR 시작IIIII==" + gmquery1);
                    gmpsmt1.executeUpdate();
                    gmpsmt1.clearParameters();
                }
            }
            System.out.println("===면허사항정보 수정종료=========");
            System.out.println("====================면허사항정보 끝=====================");
            System.out.println("====================공장정보 수정시작=====================");
            gjquery1 = " INSERT INTO 사용_공장정보 (  사업자등록번호, 일련번호, 공장명, 공장우편번호, 공장주소,  공장나머지주소, 공장전화번호, 공장FAX번호  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?  ) ";
            gjpsmt1 = con.prepareStatement(gjquery1);
            gjquery2 = " DELETE FROM 사용_공장정보  WHERE 사업자등록번호 = ? ";
            gjpsmt2 = con.prepareStatement(gjquery2);
            for (int i4 = 1; i4 < gjTotalCount; ++i4) {
                System.out.println("FOR 시작====" + gjStatus[i4]);
                if (gjStatus[i4].equals("I")) {
                    System.out.println("FOR 시작====등록시작==" + factoryName[i4].trim());
                    gjpsmt1.setString(1, saupNo);
                    gjpsmt1.setInt(2, i4);
                    gjpsmt1.setString(3, factoryName[i4].trim());
                    gjpsmt1.setString(4, ComStr.replace(factoryZipCode[i4], "-", ""));
                    gjpsmt1.setString(5, factoryAddr[i4].trim());
                    gjpsmt1.setString(6, factoryRestAddr[i4].trim());
                    gjpsmt1.setString(7, factoryTel[i4].trim());
                    gjpsmt1.setString(8, factoryFax[i4].trim());
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                    System.out.println("FOR 끝====등록종료==" + factoryAddr[i4].trim());
                }
            }
            System.out.println("====================공장정보 끝=====================");
            System.out.println("====================입찰대리인 수정시작=====================");
            idquery1 = " INSERT INTO 사용_입찰대리인 (  사업자등록번호, 주민등록번호, 성명, 부서, 직책명,  전화번호, E_MAIL, 휴대폰, FAX  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?, ?  ) ";
            idpsmt1 = con.prepareStatement(idquery1);
            idquery2 = " DELETE FROM 사용_입찰대리인  WHERE 사업자등록번호 = ? ";
            idpsmt2 = con.prepareStatement(idquery2);
            for (int i4 = 1; i4 < idTotalCount; ++i4) {
                System.out.println("FOR 시작====" + idStatus[i4]);
                if (idStatus[i4].equals("I")) {
                    System.out.println("FOR 시작====등록시작==" + ComStr.replace(juminNo[i4], "-", ""));
                    idpsmt1.setString(1, saupNo);
                    idpsmt1.setString(2, ComStr.replace(juminNo[i4], "-", ""));
                    idpsmt1.setString(3, name[i4].trim());
                    idpsmt1.setString(4, jobPart[i4].trim());
                    idpsmt1.setString(5, dutyName[i4].trim());
                    idpsmt1.setString(6, idtel[i4].trim());
                    idpsmt1.setString(7, mail[i4].trim());
                    idpsmt1.setString(8, handphone[i4].trim());
                    idpsmt1.setString(9, idfax[i4].trim());
                    idpsmt1.executeUpdate();
                    idpsmt1.clearParameters();
                    System.out.println("FOR 끝====등록종료==" + name[i4].trim());
                }
            }
            System.out.println("====================제조 조달품목정보 수정시작=====================");
            jpquery1 = " INSERT INTO 사용_조달품목 (  사업자등록번호, 물품분류번호, 형식승인번호, 형식승인기관, 형식승인일,  최근3년간_매출액, 제조여부, 대표물품여부  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?  ) ";
            jppsmt1 = con.prepareStatement(jpquery1);
            jpquery2 = " UPDATE 사용_조달품목  SET 형식승인번호 = ?,     형식승인기관 = ?,     형식승인일 = ?,     최근3년간_매출액 = ?,     제조여부 = ?,     대표물품여부 = ?  WHERE 사업자등록번호 = ?    AND 물품분류번호 = ? ";
            jppsmt2 = con.prepareStatement(jpquery2);
            jpquery3 = " DELETE FROM 사용_조달품목  WHERE 사업자등록번호 = ?    AND 물품분류번호 = ? ";
            jppsmt3 = con.prepareStatement(jpquery3);
            if ((jpTotalCount == 1 || jpTotalCount == 0) && (gpTotalCount == 1 || gpTotalCount == 0)) {
                DaeGood = "";
            }
            for (int i4 = 1; i4 < jpTotalCount; ++i4) {
                if (dpGoodsYN[i4].equals("Y") && !jpStatus[i4].equals("D") && !jpStatus[i4].equals("N")) {
                    DaeGood = goodsNo[i4];
                }
                System.out.println("FOR 시작" + jpStatus[i4]);
                if (jpStatus[i4].equals("I")) {
                    System.out.println("FOR 시작IIIII");
                    jppsmt1.setString(1, saupNo);
                    jppsmt1.setString(2, goodsNo[i4].trim());
                    jppsmt1.setString(3, formNo[i4].trim());
                    jppsmt1.setString(4, formFac[i4].trim());
                    jppsmt1.setString(5, formDate[i4].trim());
                    jppsmt1.setString(6, ComStr.replace(threeSale[i4], ",", ""));
                    jppsmt1.setString(7, makeYN[i4].trim());
                    jppsmt1.setString(8, dpGoodsYN[i4].trim());
                    System.out.println("FOR 시작IIIII==" + jpquery1);
                    jppsmt1.executeUpdate();
                    jppsmt1.clearParameters();
                    upCheck = "Y";
                }
            }
            System.out.println("===제조 조달품목 수정종료=========");
            System.out.println("====================제조 조달품목 끝=====================");
            System.out.println("====================공급 조달품목정보 수정시작=====================");
            gpquery1 = " INSERT INTO 사용_조달품목 (  사업자등록번호, 물품분류번호, 최근3년간_매출액, 제조여부, 대표물품여부  ) VALUES (  ?, ?, ?, ?, ?  ) ";
            gppsmt1 = con.prepareStatement(gpquery1);
            gpquery2 = " UPDATE 사용_조달품목  SET 최근3년간_매출액 = ?,     제조여부 = ?,     대표물품여부 = ?  WHERE 사업자등록번호 = ?    AND 물품분류번호 = ? ";
            gppsmt2 = con.prepareStatement(gpquery2);
            gpquery3 = " DELETE FROM 사용_조달품목  WHERE 사업자등록번호 = ?    AND 물품분류번호 = ? ";
            gppsmt3 = con.prepareStatement(gpquery3);
            for (int i4 = 1; i4 < gpTotalCount; ++i4) {
                if (gdpGoodsYN[i4].equals("Y") && !gpStatus[i4].equals("D") && !gpStatus[i4].equals("N")) {
                    DaeGood = ggoodsNo[i4];
                }
                System.out.println("FOR 시작" + gpStatus[i4]);
                if (gpStatus[i4].equals("I")) {
                    System.out.println("FOR 시작IIIII");
                    gppsmt1.setString(1, saupNo);
                    gppsmt1.setString(2, ggoodsNo[i4].trim());
                    gppsmt1.setString(3, ComStr.replace(gthreeSale[i4], ",", ""));
                    gppsmt1.setString(4, gmakeYN[i4].trim());
                    gppsmt1.setString(5, gdpGoodsYN[i4].trim());
                    System.out.println("FOR 시작IIIII==" + gpquery1);
                    gppsmt1.executeUpdate();
                    gppsmt1.clearParameters();
                    upCheck = "Y";
                }
            }
            System.out.println("===공급 조달품목 수정종료=========");
            System.out.println("====================공급 조달품목 끝=====================");
            System.out.println("====================일반정보 이력 수정시작=====================");
            query3 = " INSERT INTO 사용_조달업체마스터이력 (  사업자등록번호, 갱신일자, 국적, 상호명, 영문상호명,  개업일자, 법인설립일자, 업무구분, 제조구분,  법인등록번호, 기업구분1, 기업구분2, 기업구분해당년도, 자본금,  종업원수, 최근결산년월, 우편번호, 지역코드, 주소,  나머지주소, 전화번호, FAX번호, 홈페이지, 특례해당여부,  대표물품분류번호, 대표면허코드, 대표대표자명,  대표인증여부, 처리자ID, 등록유효일자, 등록일자  ) VALUES (  ?, SYSDATE, ?, ?, ?,  ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ? , 'N', ?, ADD_MONTHS(SYSDATE, 24), SYSDATE  ) ";
            psmt3 = con.prepareStatement(query3);
            System.out.println("Insert =====" + query3);
            System.out.println("222===" + DaeGood);
            System.out.println("333===" + DaeLice);
            System.out.println("444===" + DaeCeo);
            psmt3.setString(1, saupNo);
            psmt3.setString(2, nationality);
            psmt3.setString(3, sangho);
            psmt3.setString(4, eSangho);
            psmt3.setString(5, openDate);
            psmt3.setString(6, bubinOpenDate);
            psmt3.setString(7, upmu);
            psmt3.setString(8, jejoGubun);
            psmt3.setString(9, ComStr.replace(bubinNo, "-", ""));
            psmt3.setString(10, "2");
            psmt3.setString(11, comGubun2);
            psmt3.setString(12, "2002");
            psmt3.setLong(13, Long.parseLong(ComStr.replace(jabon, ",", "")));
            psmt3.setInt(14, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
            psmt3.setString(15, "200202");
            psmt3.setString(16, zipCode);
            psmt3.setString(17, localCode);
            psmt3.setString(18, addr);
            psmt3.setString(19, restAddr);
            psmt3.setString(20, tel);
            psmt3.setString(21, fax);
            psmt3.setString(22, homepage);
            psmt3.setString(23, exceptYN);
            psmt3.setString(24, DaeGood);
            psmt3.setString(25, DaeLice);
            psmt3.setString(26, DaeCeo);
            psmt3.setString(27, procID);
            psmt3.executeUpdate();
            psmt3.clearParameters();
            System.out.println("업체마스터이력정보 등록OK====" + sangho);
            for (int i4 = 1; i4 < gmTotalCount; ++i4) {
                if (gmDpLicenseYN[i4].equals("Y") && !gmStatus[i4].equals("D") && !gmStatus[i4].equals("N")) {
                    DaeLice = gmDpLicenseYN[i4];
                }
            }
            if (goodsNo.length > 1 || ggoodsNo.length > 1) {
                upmu2 = "1";
            }
            if (forYN.equals("1")) {
                upmu5 = "1";
            }
            else {
                upmu5 = "0";
            }
            upmu = String.valueOf(upmu2) + upmu3 + upmu4 + "0" + upmu5;
            System.out.println(" upmu => " + upmu);
            if (factoryName.length > 1) {
                jejoGubun = "02";
            }
            else {
                jejoGubun = "03";
            }
            String Code1 = "";
            String Code2 = "";
            String Code3 = "";
            String Code3_1 = "";
            String comCode1 = "";
            String comCode2 = "";
            String comCode3 = "";
            String comCode3_1 = "";
            String jicheongCode = "";
            int cCode1 = 0;
            int cCode2 = 0;
            int cCode3 = 0;
            int cCode3_1 = 0;
            query1 = " SELECT A.지청구분 FROM SYN_지청 A, SYN_매핑코드지역 B  WHERE A.지역코드 = B.기존코드 AND B.G2B코드 = ? ";
            psmt1 = con.prepareStatement(query1);
            psmt1.setString(1, localCode);
            rs = psmt1.executeQuery();
            if (rs.next()) {
                jicheongCode = rs.getString(1);
            }
            psmt1.clearParameters();
            if (upmu2.equals("1")) {
                query1 = " SELECT MAX(등록업체코드) FROM SYN_등록업체  WHERE 등록업체코드 LIKE '" + jicheongCode + "1%' " + " OR 등록업체코드 LIKE '" + jicheongCode + "20%' ORDER BY 등록업체코드 DESC ";
                psmt1 = con.prepareStatement(query1);
                rs = psmt1.executeQuery();
                if (rs.next()) {
                    Code1 = rs.getString(1);
                }
                cCode1 = Integer.parseInt(Code1) + 1;
                comCode1 = Integer.toString(cCode1);
                psmt1.clearParameters();
            }
            if (upmu3.equals("1")) {
                query1 = " SELECT MAX(등록업체코드) FROM SYN_등록업체  WHERE 등록업체코드 LIKE '" + jicheongCode + "31%' " + " OR 등록업체코드 LIKE '" + jicheongCode + "32%' " + " OR 등록업체코드 LIKE '" + jicheongCode + "33%' " + " OR 등록업체코드 LIKE '" + jicheongCode + "34%' ORDER BY 등록업체코드 DESC ";
                psmt1 = con.prepareStatement(query1);
                rs = psmt1.executeQuery();
                if (rs.next()) {
                    Code3 = rs.getString(1);
                }
                cCode3 = Integer.parseInt(Code3) + 1;
                comCode3 = Integer.toString(cCode3);
                psmt1.clearParameters();
            }
            if (upmu4.equals("1")) {
                query1 = " SELECT MAX(등록업체코드) FROM SYN_등록업체  WHERE 등록업체코드 LIKE '" + jicheongCode + "35%' ORDER BY 등록업체코드 DESC ";
                psmt1 = con.prepareStatement(query1);
                rs = psmt1.executeQuery();
                if (rs.next()) {
                    Code3_1 = rs.getString(1);
                }
                cCode3_1 = Integer.parseInt(Code3_1) + 1;
                comCode3_1 = Integer.toString(cCode3_1);
                psmt1.clearParameters();
            }
            if (upmu5.equals("1")) {
                query1 = " SELECT MAX(등록업체코드) FROM SYN_등록업체  WHERE 등록업체코드 LIKE '" + jicheongCode + "2%' ORDER BY 등록업체코드 DESC ";
                psmt1 = con.prepareStatement(query1);
                rs = psmt1.executeQuery();
                if (rs.next()) {
                    Code2 = rs.getString(1);
                }
                cCode2 = Integer.parseInt(Code2) + 1;
                comCode2 = Integer.toString(cCode2);
                psmt1.clearParameters();
            }
            query1 = " INSERT INTO SYN_매핑코드업체 (  사업자등록번호, 물품등록업체코드, 공사등록업체코드, 용역등록업체코드, 외자등록업체코드,  사용여부  ) VALUES (  ?, ?, ?, ?, ?,  'Y'  ) ";
            psmt1 = con.prepareStatement(query1);
            psmt1.setString(1, saupNo);
            if (comCode1.equals("0")) {
                psmt1.setString(2, " ");
            }
            else {
                psmt1.setString(2, comCode1);
            }
            if (comCode3.equals("0")) {
                psmt1.setString(3, " ");
            }
            else {
                psmt1.setString(3, comCode3);
            }
            if (comCode3_1.equals("0")) {
                psmt1.setString(4, " ");
            }
            else {
                psmt1.setString(4, comCode3_1);
            }
            if (comCode2.equals("0")) {
                psmt1.setString(5, " ");
            }
            else {
                psmt1.setString(5, comCode2);
            }
            psmt1.executeUpdate();
            psmt1.clearParameters();
            System.out.println("comCode1 => " + comCode1 + "comCode2 => " + comCode2 + "comCode3 => " + comCode3 + "comCode3_1 => " + comCode3_1);
            query1 = " SELECT 기존코드 FROM SYN_매핑코드지역  WHERE G2B코드 = ? ";
            psmt1 = con.prepareStatement(query1);
            psmt1.setString(1, localCode);
            rs = psmt1.executeQuery();
            String lcCode = "";
            if (rs.next()) {
                lcCode = rs.getString(1);
            }
            psmt1.clearParameters();
            System.out.println("매핑지역코드 구함 => " + lcCode);
            for (int i5 = 1; i5 < dpTotalCount; ++i5) {
                if (ceoYN[i5].equals("Y") && !dpStatus[i5].equals("D") && !dpStatus[i5].equals("N")) {
                    DaeCeo = ceoName[i5];
                    DaeJumin = ceoJuminNo[i5];
                    DaeMail = ceoMail[i5];
                }
            }
            System.out.println("DaeCeo => " + DaeCeo + "DaeJumin => " + DaeJumin + "DaeMail => " + DaeMail);
            if (upmu2.equals("1")) {
                query1 = " INSERT INTO SYN_등록업체 (  사업구분, 등록업체코드, 상호명, 사업자등록번호,  등록일자, 대표자명, 대표자주민번호, 본사지역코드, 본사우편번호,  본사통반번지, 본사주소, 본사전화번호, FAX번호, 설립일자,  법인구분, 법인설립일자, 업체구분, 등록유효일자, 수정일자,  입력일자, 국가코드, 자본금, 종업원수, 홈페이지,  대표자메일주소, 입력자사번  ) VALUES (  '1', ?, ?, ?,  SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ADD_MONTHS(SYSDATE, 24), SYSDATE,  SYSDATE, ?, ?, ?, ?,  ?, '970251'  ) ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, comCode1);
                psmt1.setString(2, sangho);
                psmt1.setString(3, saupNo);
                psmt1.setString(4, DaeCeo);
                psmt1.setString(5, ComStr.replace(DaeJumin, "-", ""));
                psmt1.setString(6, lcCode);
                psmt1.setString(7, zipCode);
                psmt1.setString(8, restAddr);
                psmt1.setString(9, addr);
                psmt1.setString(10, tel);
                psmt1.setString(11, fax);
                psmt1.setString(12, openDate);
                if (bubinNo.equals("")) {
                    psmt1.setString(13, "0");
                }
                else {
                    psmt1.setString(13, "1");
                }
                psmt1.setString(14, bubinOpenDate);
                String comGubun3 = "";
                if (comGubun1.equals("대기업")) {
                    comGubun3 = "11";
                }
                else {
                    comGubun3 = "23";
                }
                psmt1.setString(15, comGubun3);
                psmt1.setString(16, nationality);
                psmt1.setLong(17, Long.parseLong(ComStr.replace(jabon, ",", "")));
                psmt1.setInt(18, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                psmt1.setString(19, homepage);
                psmt1.setString(20, DaeMail);
                psmt1.executeUpdate();
                psmt1.clearParameters();
                System.out.println("aaaaaaaaaaaaaaaaaa");
                query1 = " INSERT INTO SYN_내자업체 (  등록업체코드, 지역코드, 우편번호, 통반_번지, 주소,  전화번호, FAX번호, 입력일자, 법인등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, SYSDATE, ?, '970251'  ) ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, comCode1);
                psmt1.setString(2, lcCode);
                psmt1.setString(3, ComStr.replace(factoryZipCode[0], "-", ""));
                psmt1.setString(4, factoryRestAddr[0].trim());
                psmt1.setString(5, factoryAddr[0].trim());
                psmt1.setString(6, factoryTel[0].trim());
                psmt1.setString(7, factoryFax[0].trim());
                psmt1.setString(8, ComStr.replace(bubinNo, "-", ""));
                psmt1.executeUpdate();
                psmt1.clearParameters();
                System.out.println("bbbbbbbbbbbbbbbbb");
                for (int i6 = 1; i6 < gpTotalCount; ++i6) {
                    query1 = " INSERT INTO SYN_조달품목 (  등록업체코드, 물품분류번호, 매출액, 형식승인번호, 공장구분, 입력자사번  ) VALUES (  ?, ?, ?, ?, ?, '970251'  ) ";
                    psmt1 = con.prepareStatement(query1);
                    psmt1.setString(1, comCode1);
                    psmt1.setString(2, " ");
                    psmt1.setString(3, ComStr.replace(gthreeSale[i6], ",", ""));
                    psmt1.setString(4, " ");
                    psmt1.executeUpdate();
                    psmt1.clearParameters();
                }
                System.out.println("ccccccccccccccccc");
                for (int i6 = 1; i6 < jpTotalCount; ++i6) {
                    query1 = " INSERT INTO SYN_조달품목 (  등록업체코드, 물품분류번호, 매출액, 형식승인번호, 공장구분, 입력자사번  ) VALUES (  ?, ?, ?, ?, ?, '970251'  ) ";
                    psmt1 = con.prepareStatement(query1);
                    psmt1.setString(1, comCode1);
                    psmt1.setString(2, " ");
                    psmt1.setString(3, ComStr.replace(threeSale[i6], ",", ""));
                    psmt1.setString(4, formNo[i6].trim());
                    psmt1.executeUpdate();
                    psmt1.clearParameters();
                }
                System.out.println("dddddddddddddddddd");
                for (int i6 = 1; i6 < idTotalCount; ++i6) {
                    if (idStatus[i6].equals("I")) {
                        query1 = " INSERT INTO SYN_입찰대리인 (  등록업체코드, 직책명, 성명, 주민등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, '970251'  ) ";
                        psmt1 = con.prepareStatement(query1);
                        psmt1.setString(1, comCode1);
                        psmt1.setString(2, dutyName[i6].trim());
                        psmt1.setString(3, name[i6].trim());
                        psmt1.setString(4, ComStr.replace(juminNo[i6], "-", ""));
                        psmt1.executeUpdate();
                        psmt1.clearParameters();
                    }
                }
                System.out.println("eeeeeeeeeeeeeeeeeeee");
            }
            for (int i5 = 1; i5 < gmTotalCount; ++i5) {
                if (gmDpLicenseYN[i5].equals("Y") && !gmStatus[i5].equals("D") && !gmStatus[i5].equals("N")) {
                    DaeLice = gmDpLicenseYN[i5];
                }
            }
            int gmc = -1;
            int ymc = -1;
            int msi = -1;
            for (int i7 = 1; i7 < gmTotalCount; ++i7) {
                query1 = " SELECT 코드명2 FROM SYN_공동코드  WHERE 코드구분 = 'GU9' AND 코드 = ? ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, gmLicenseCode[i7].trim());
                rs = psmt1.executeQuery();
                String licenseCode2 = "";
                if (rs.next()) {
                    licenseCode2 = rs.getString(1);
                }
                if ("공사".equals(licenseCode2)) {
                    gmc = i7;
                }
                if ("용역".equals(licenseCode2)) {
                    ymc = i7;
                }
                if ("Y".equals(gmDpLicenseYN[i7])) {
                    msi = i7;
                }
            }
            query1 = " SELECT 기존코드 FROM SYN_매핑코드면허  WHERE G2B코드 = ? ";
            psmt1 = con.prepareStatement(query1);
            psmt1.setString(1, gmLicenseCode[msi]);
            rs = psmt1.executeQuery();
            String licenseCode3 = "";
            if (rs.next()) {
                licenseCode3 = rs.getString(1);
            }
            if (upmu3.equals("1")) {
                query1 = " INSERT INTO SYN_등록업체 (  사업구분, 등록업체코드, 상호명, 사업자등록번호,  등록일자, 대표자명, 대표자주민번호, 본사지역코드, 본사우편번호,  본사통반번지, 본사주소, 본사전화번호, FAX번호, 설립일자,  법인구분, 법인설립일자, 업체구분, 등록유효일자, 수정일자,  입력일자, 국가코드, 자본금, 종업원수, 홈페이지,  대표자메일주소, 입력자사번  ) VALUES (  '3', ?, ?, ?,  SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ADD_MONTHS(SYSDATE, 24), SYSDATE,  SYSDATE, ?, ?, ?, ?,  ?, '970251'  ) ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, comCode3);
                psmt1.setString(2, sangho);
                psmt1.setString(3, saupNo);
                psmt1.setString(4, DaeCeo);
                psmt1.setString(5, ComStr.replace(DaeJumin, "-", ""));
                psmt1.setString(6, lcCode);
                psmt1.setString(7, zipCode);
                psmt1.setString(8, restAddr);
                psmt1.setString(9, addr);
                psmt1.setString(10, tel);
                psmt1.setString(11, fax);
                psmt1.setString(12, openDate);
                if (bubinNo.equals("")) {
                    psmt1.setString(13, "0");
                }
                else {
                    psmt1.setString(13, "1");
                }
                psmt1.setString(14, bubinOpenDate);
                String comGubun4 = "";
                if (comGubun1.equals("대기업")) {
                    comGubun4 = "11";
                }
                else {
                    comGubun4 = "23";
                }
                psmt1.setString(15, comGubun4);
                psmt1.setString(16, nationality);
                psmt1.setLong(17, Long.parseLong(ComStr.replace(jabon, ",", "")));
                psmt1.setInt(18, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                psmt1.setString(19, homepage);
                psmt1.setString(20, DaeMail);
                psmt1.executeUpdate();
                psmt1.clearParameters();
                System.out.println("ffffffffffffffffffff");
                query1 = " INSERT INTO SYN_시설업체 (  등록업체코드, 자본금, 종업원수, 대표면허번호, 법인등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, ?, '970251'  ) ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, comCode3);
                psmt1.setLong(2, Long.parseLong(ComStr.replace(jabon, ",", "")));
                psmt1.setInt(3, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                if (msi != -1) {
                    psmt1.setString(4, licenseCode3);
                }
                else {
                    psmt1.setString(4, " ");
                }
                psmt1.setString(5, ComStr.replace(bubinNo, "-", ""));
                psmt1.executeUpdate();
                psmt1.clearParameters();
                System.out.println("gggggggggggggggggggggggg");
                for (int i8 = 1; i8 < gmTotalCount; ++i8) {
                    query1 = " SELECT 기존코드 FROM SYN_매핑코드면허  WHERE G2B코드 = ? ";
                    psmt1 = con.prepareStatement(query1);
                    psmt1.setString(1, gmLicenseCode[gmc].trim());
                    rs = psmt1.executeQuery();
                    if (rs.next()) {
                        licenseCode3 = rs.getString(1);
                    }
                    psmt1.clearParameters();
                    query1 = " INSERT INTO SYN_면허사항 (  등록업체코드, 면허번호, 면허취득일자, 도급한도액, 입력자사번, 입력일자  ) VALUES (  ?, ?, ?, ?, '970251', SYSDATE  ) ";
                    psmt1 = con.prepareStatement(query1);
                    psmt1.setString(1, comCode3);
                    psmt1.setString(2, licenseCode3);
                    psmt1.setString(3, gmLicenseBeginDate[gmc].trim());
                    psmt1.setLong(4, Long.parseLong(ComStr.replace(gmSigongAccount[gmc], ",", "")));
                    psmt1.executeUpdate();
                    psmt1.clearParameters();
                }
                System.out.println("hhhhhhhhhhhhhhhhhhhh");
                for (int i8 = 1; i8 < idTotalCount; ++i8) {
                    if (idStatus[i8].equals("I")) {
                        query1 = " INSERT INTO SYN_입찰대리인 (  등록업체코드, 직책명, 성명, 주민등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, '970251'  ) ";
                        psmt1 = con.prepareStatement(query1);
                        psmt1.setString(1, comCode3);
                        psmt1.setString(2, dutyName[i8].trim());
                        psmt1.setString(3, name[i8].trim());
                        psmt1.setString(4, ComStr.replace(juminNo[i8], "-", ""));
                        psmt1.executeUpdate();
                        psmt1.clearParameters();
                    }
                }
                System.out.println("iiiiiiiiiiiiiiiiiiiii");
            }
            if (upmu4.equals("1")) {
                query1 = " INSERT INTO SYN_등록업체 (  사업구분, 등록업체코드, 상호명, 사업자등록번호,  등록일자, 대표자명, 대표자주민번호, 본사지역코드, 본사우편번호,  본사통반번지, 본사주소, 본사전화번호, FAX번호, 설립일자,  법인구분, 법인설립일자, 업체구분, 등록유효일자, 수정일자,  입력일자, 국가코드, 자본금, 종업원수, 홈페이지,  대표자메일주소, 입력자사번  ) VALUES (  '3', ?, ?, ?,  SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ADD_MONTHS(SYSDATE, 24), SYSDATE,  SYSDATE, ?, ?, ?, ?,  ?, '970251'  ) ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, comCode3_1);
                psmt1.setString(2, sangho);
                psmt1.setString(3, saupNo);
                psmt1.setString(4, DaeCeo);
                psmt1.setString(5, ComStr.replace(DaeJumin, "-", ""));
                psmt1.setString(6, lcCode);
                psmt1.setString(7, zipCode);
                psmt1.setString(8, restAddr);
                psmt1.setString(9, addr);
                psmt1.setString(10, tel);
                psmt1.setString(11, fax);
                psmt1.setString(12, openDate);
                if (bubinNo.equals("")) {
                    psmt1.setString(13, "0");
                }
                else {
                    psmt1.setString(13, "1");
                }
                psmt1.setString(14, bubinOpenDate);
                String comGubun4 = "";
                if (comGubun1.equals("대기업")) {
                    comGubun4 = "11";
                }
                else {
                    comGubun4 = "23";
                }
                psmt1.setString(15, comGubun4);
                psmt1.setString(16, nationality);
                psmt1.setLong(17, Long.parseLong(ComStr.replace(jabon, ",", "")));
                psmt1.setInt(18, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                psmt1.setString(19, homepage);
                psmt1.setString(20, DaeMail);
                psmt1.executeUpdate();
                psmt1.clearParameters();
                System.out.println("jjjjjjjjjjjjjjjjjjjjjjjj");
                query1 = " INSERT INTO SYN_시설업체 (  등록업체코드, 자본금, 종업원수, 대표면허번호, 법인등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, ?, '970251'  ) ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, comCode3_1);
                psmt1.setLong(2, Long.parseLong(ComStr.replace(jabon, ",", "")));
                psmt1.setInt(3, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                if (msi != -1) {
                    psmt1.setString(4, licenseCode3);
                }
                else {
                    psmt1.setString(4, " ");
                }
                psmt1.setString(5, ComStr.replace(bubinNo, "-", ""));
                psmt1.executeUpdate();
                psmt1.clearParameters();
                System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk");
                for (int i8 = 1; i8 < gmTotalCount; ++i8) {
                    query1 = " SELECT 기존코드 FROM SYN_매핑코드면허  WHERE G2B코드 = ? ";
                    psmt1 = con.prepareStatement(query1);
                    psmt1.setString(1, gmLicenseCode[ymc].trim());
                    rs = psmt1.executeQuery();
                    if (rs.next()) {
                        licenseCode3 = rs.getString(1);
                    }
                    psmt1.clearParameters();
                    System.out.println("licenseCode => " + licenseCode3);
                    query1 = " INSERT INTO SYN_면허사항 (  등록업체코드, 면허번호, 면허취득일자, 도급한도액, 입력자사번, 입력일자  ) VALUES (  ?, ?, ?, ?, '970251', SYSDATE  ) ";
                    psmt1 = con.prepareStatement(query1);
                    psmt1.setString(1, comCode3_1);
                    psmt1.setString(2, licenseCode3);
                    psmt1.setString(3, gmLicenseBeginDate[ymc].trim());
                    psmt1.setLong(4, Long.parseLong(ComStr.replace(gmSigongAccount[ymc], ",", "")));
                    System.out.println("comCode3_1 => " + comCode3_1);
                    System.out.println("gmLicenseCode[ymc] => " + gmLicenseCode[ymc]);
                    System.out.println("gmLicenseBeginDate[ymc] => " + gmLicenseBeginDate[ymc]);
                    System.out.println("gmSigongAccount[ymc] => " + gmSigongAccount[ymc]);
                    psmt1.executeUpdate();
                    psmt1.clearParameters();
                }
                System.out.println("lllllllllllllllllllllll");
                for (int i8 = 1; i8 < idTotalCount; ++i8) {
                    if (idStatus[i8].equals("I")) {
                        query1 = " INSERT INTO SYN_입찰대리인 (  등록업체코드, 직책명, 성명, 주민등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, '970251'  ) ";
                        psmt1 = con.prepareStatement(query1);
                        psmt1.setString(1, comCode3_1);
                        psmt1.setString(2, dutyName[i8].trim());
                        psmt1.setString(3, name[i8].trim());
                        psmt1.setString(4, ComStr.replace(juminNo[i8], "-", ""));
                        psmt1.executeUpdate();
                        psmt1.clearParameters();
                    }
                }
                System.out.println("mmmmmmmmmmmmmmmmmmmmm");
            }
            if (upmu5.equals("1")) {
                query1 = " INSERT INTO SYN_등록업체 (  사업구분, 등록업체코드, 상호명, 사업자등록번호,  등록일자, 대표자명, 대표자주민번호, 본사지역코드, 본사우편번호,  본사통반번지, 본사주소, 본사전화번호, FAX번호, 설립일자,  법인구분, 법인설립일자, 업체구분, 등록유효일자, 수정일자,  입력일자, 국가코드, 자본금, 종업원수, 홈페이지,  대표자메일주소, 입력자사번  ) VALUES (  '1', ?, ?, ?,  SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ADD_MONTHS(SYSDATE, 24), SYSDATE,  SYSDATE, ?, ?, ?, ?,  ?, '970251'  ) ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, comCode2);
                psmt1.setString(2, sangho);
                psmt1.setString(3, saupNo);
                psmt1.setString(4, DaeCeo);
                psmt1.setString(5, ComStr.replace(DaeJumin, "-", ""));
                psmt1.setString(6, lcCode);
                psmt1.setString(7, zipCode);
                psmt1.setString(8, restAddr);
                psmt1.setString(9, addr);
                psmt1.setString(10, tel);
                psmt1.setString(11, fax);
                psmt1.setString(12, openDate);
                if (bubinNo.equals("")) {
                    psmt1.setString(13, "0");
                }
                else {
                    psmt1.setString(13, "1");
                }
                psmt1.setString(14, bubinOpenDate);
                String comGubun4 = "";
                if (comGubun1.equals("대기업")) {
                    comGubun4 = "11";
                }
                else {
                    comGubun4 = "23";
                }
                psmt1.setString(15, comGubun4);
                psmt1.setString(16, nationality);
                psmt1.setLong(17, Long.parseLong(ComStr.replace(jabon, ",", "")));
                psmt1.setInt(18, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                psmt1.setString(19, homepage);
                psmt1.setString(20, DaeMail);
                psmt1.executeUpdate();
                psmt1.clearParameters();
                System.out.println("nnnnnnnnnnnnnnnnnnnn");
                query1 = " INSERT INTO SYN_외자상사업체 (  등록업체코드, 영문상호명, 국가코드, 법인등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, '970251'  ) ";
                System.out.println("외자상사업체");
                psmt1 = con.prepareStatement(query1);
                System.out.println("외자상사업체1");
                psmt1.setString(1, comCode2);
                psmt1.setString(2, eSangho);
                psmt1.setString(3, nationality);
                psmt1.setString(4, ComStr.replace(bubinNo, "-", ""));
                System.out.println("comCode2 => " + comCode2);
                System.out.println("nationality => " + nationality);
                System.out.println("bubinNo => " + bubinNo);
                psmt1.executeUpdate();
                System.out.println("외자상사업체2");
                psmt1.clearParameters();
                System.out.println("oooooooooooooooooooo");
                for (int i8 = 1; i8 < idTotalCount; ++i8) {
                    if (idStatus[i8].equals("I")) {
                        query1 = " INSERT INTO SYN_입찰대리인 (  등록업체코드, 직책명, 성명, 주민등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, '970251'  ) ";
                        psmt1 = con.prepareStatement(query1);
                        psmt1.setString(1, comCode2);
                        psmt1.setString(2, dutyName[i8].trim());
                        psmt1.setString(3, name[i8].trim());
                        psmt1.setString(4, ComStr.replace(juminNo[i8], "-", ""));
                        psmt1.executeUpdate();
                        psmt1.clearParameters();
                    }
                }
                System.out.println("pppppppppppppppppppp");
            }
            con.commit();
            con.setAutoCommit(true);
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
            res.sendRedirect("/jsp/AD/UM_ADJ_ConrC020s.jsp?saupNo=" + saupNo + "&nation=" + URLEncoder.encode(nation) + "&localCodeName=" + URLEncoder.encode(localCodeName));
        }
        catch (SQLException exc) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrC010c.doPost block SQLException : Transaction Rollback간에 SQLException 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrC010c.doPost block SQLException : ");
            Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
        }
        catch (Exception exc2) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrC010c.doPost block Exception : Transaction Rollback간에 Exception 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrC010c.doPost block Exception : ");
            Log.debug("Exception발생 사유 : " + exc2.toString());
            exc2.printStackTrace();
        }
        finally {
            if (psmt1 != null) {
                try {
                    psmt1.close();
                }
                catch (Exception ex4) {}
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex5) {}
            }
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex6) {}
            }
            if (dppsmt1 != null) {
                try {
                    dppsmt1.close();
                }
                catch (Exception ex7) {}
            }
            if (dppsmt2 != null) {
                try {
                    dppsmt2.close();
                }
                catch (Exception ex8) {}
            }
            if (dppsmt3 != null) {
                try {
                    dppsmt3.close();
                }
                catch (Exception ex9) {}
            }
            if (gmpsmt1 != null) {
                try {
                    gmpsmt1.close();
                }
                catch (Exception ex10) {}
            }
            if (gmpsmt2 != null) {
                try {
                    gmpsmt2.close();
                }
                catch (Exception ex11) {}
            }
            if (gmpsmt3 != null) {
                try {
                    gmpsmt3.close();
                }
                catch (Exception ex12) {}
            }
            if (gjpsmt1 != null) {
                try {
                    gjpsmt1.close();
                }
                catch (Exception ex13) {}
            }
            if (gjpsmt2 != null) {
                try {
                    gjpsmt2.close();
                }
                catch (Exception ex14) {}
            }
            if (gjpsmt3 != null) {
                try {
                    gjpsmt3.close();
                }
                catch (Exception ex15) {}
            }
            if (idpsmt1 != null) {
                try {
                    idpsmt1.close();
                }
                catch (Exception ex16) {}
            }
            if (idpsmt2 != null) {
                try {
                    idpsmt2.close();
                }
                catch (Exception ex17) {}
            }
            if (idpsmt3 != null) {
                try {
                    idpsmt3.close();
                }
                catch (Exception ex18) {}
            }
            if (jppsmt1 != null) {
                try {
                    jppsmt1.close();
                }
                catch (Exception ex19) {}
            }
            if (jppsmt2 != null) {
                try {
                    jppsmt2.close();
                }
                catch (Exception ex20) {}
            }
            if (jppsmt3 != null) {
                try {
                    jppsmt3.close();
                }
                catch (Exception ex21) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex22) {}
            }
        }
        if (psmt1 != null) {
            try {
                psmt1.close();
            }
            catch (Exception ex23) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex24) {}
        }
        if (psmt3 != null) {
            try {
                psmt3.close();
            }
            catch (Exception ex25) {}
        }
        if (dppsmt1 != null) {
            try {
                dppsmt1.close();
            }
            catch (Exception ex26) {}
        }
        if (dppsmt2 != null) {
            try {
                dppsmt2.close();
            }
            catch (Exception ex27) {}
        }
        if (dppsmt3 != null) {
            try {
                dppsmt3.close();
            }
            catch (Exception ex28) {}
        }
        if (gmpsmt1 != null) {
            try {
                gmpsmt1.close();
            }
            catch (Exception ex29) {}
        }
        if (gmpsmt2 != null) {
            try {
                gmpsmt2.close();
            }
            catch (Exception ex30) {}
        }
        if (gmpsmt3 != null) {
            try {
                gmpsmt3.close();
            }
            catch (Exception ex31) {}
        }
        if (gjpsmt1 != null) {
            try {
                gjpsmt1.close();
            }
            catch (Exception ex32) {}
        }
        if (gjpsmt2 != null) {
            try {
                gjpsmt2.close();
            }
            catch (Exception ex33) {}
        }
        if (gjpsmt3 != null) {
            try {
                gjpsmt3.close();
            }
            catch (Exception ex34) {}
        }
        if (idpsmt1 != null) {
            try {
                idpsmt1.close();
            }
            catch (Exception ex35) {}
        }
        if (idpsmt2 != null) {
            try {
                idpsmt2.close();
            }
            catch (Exception ex36) {}
        }
        if (idpsmt3 != null) {
            try {
                idpsmt3.close();
            }
            catch (Exception ex37) {}
        }
        if (jppsmt1 != null) {
            try {
                jppsmt1.close();
            }
            catch (Exception ex38) {}
        }
        if (jppsmt2 != null) {
            try {
                jppsmt2.close();
            }
            catch (Exception ex39) {}
        }
        if (jppsmt3 != null) {
            try {
                jppsmt3.close();
            }
            catch (Exception ex40) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex41) {}
        }
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
            query4 = " SELECT  사업자등록번호, 국적, 상호명, 영문상호명,  TO_CHAR(개업일자, 'YYYY-MM-DD'), TO_CHAR(법인설립일자, 'YYYY-MM-DD'), 업무구분, 제조구분,  법인등록번호, 기업구분1, 기업구분2, 기업구분해당년도, 자본금,  종업원수, 최근결산년월, 우편번호, 지역코드, 주소,  나머지주소, 전화번호, FAX번호, 홈페이지, 특례해당여부,  등록유효일자, 등록일자, 갱신일자, 대표인증여부, 처리자ID  FROM 사용_조달업체마스터  WHERE 사업자등록번호 = '" + saupNo + "' ";
            psmt4 = con.prepareStatement(query4);
            rs = psmt4.executeQuery();
            psmt4.clearParameters();
            while (rs.next()) {
                ett = new UM_GOE_ConiC010b();
                ett.setSaupNo(rs.getString(1));
                ett.setNationality(rs.getString(2));
                ett.setSangho(rs.getString(3));
                ett.setESangho(rs.getString(4));
                ett.setOpenDate(rs.getString(5));
                ett.setBubinOpenDate(rs.getString(6));
                ett.setJobGubun(rs.getString(7));
                ett.setMakeGubun(rs.getString(8));
                ett.setBubinNo(rs.getString(9));
                ett.setComGubun1(rs.getString(10));
                ett.setComGubun2(rs.getString(11));
                ett.setComGubunYear(rs.getString(12));
                ett.setJabon(rs.getLong(13));
                ett.setEmployeeNo(rs.getInt(14));
                ett.setAccountDate(rs.getString(15));
                ett.setZipCode(rs.getString(16));
                ett.setLocalCode(rs.getString(17));
                ett.setAddr(rs.getString(18));
                ett.setRestAddr(rs.getString(19));
                ett.setTel(rs.getString(20));
                ett.setFax(rs.getString(21));
                ett.setHomepage(rs.getString(22));
                ett.setExceptYN(rs.getString(23));
                ett.setRegistOkDate(rs.getString(24));
                ett.setRegistDate(rs.getString(25));
                ett.setRenewDate(rs.getString(26));
                ett.setDpOkYN(rs.getString(27));
                ett.setChurijaId(rs.getString(28));
            }
            rs.close();
            psmt4.close();
            con.close();
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrC010c.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrC010c.select_user block Exception : ");
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
    
    public String[][] getGMData() {
        this.setCurrentMode(200);
        final String[][] gmData = this.getList();
        return gmData;
    }
    
    public String[][] getGJData() {
        this.setCurrentMode(400);
        final String[][] gjData = this.getList();
        return gjData;
    }
    
    public String[][] getIDData() {
        this.setCurrentMode(500);
        final String[][] idData = this.getList();
        return idData;
    }
    
    public String[][] getJPData() {
        this.setCurrentMode(600);
        final String[][] jpData = this.getList();
        return jpData;
    }
    
    public String[][] getGPData() {
        this.setCurrentMode(700);
        final String[][] gpData = this.getList();
        return gpData;
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
            Log.debug("UM_ADV_ConrC010c.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrC010c.select_user block Exception : ");
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
                query = " SELECT  대표자명,  대표자주민번호,  대표자메일주소,  대표대표자여부 " + this.getQueryCondition();
                break;
            }
            case 200: {
                query = " SELECT  A.면허코드,  B.코드명,  A.면허번호,  TO_CHAR(A.면허취득일자, 'YYYY-MM-DD'),  A.시공능력평가액,  A.평가액기준년도,  A.대표면허여부 " + this.getQueryCondition();
                break;
            }
            case 400: {
                query = " SELECT  공장명,  공장우편번호,  공장주소,  공장나머지주소,  공장전화번호,  공장FAX번호 " + this.getQueryCondition();
                break;
            }
            case 500: {
                query = " SELECT  주민등록번호,  성명,  부서,  직책명,  전화번호,  E_MAIL,  휴대폰,  FAX " + this.getQueryCondition();
                break;
            }
            case 700: {
                query = " SELECT  B.CATE_NAME,  A.물품분류번호,  A.최근3년간_매출액,  A.대표물품여부 " + this.getQueryCondition();
                break;
            }
            case 600: {
                query = " SELECT  B.CATE_NAME,  A.물품분류번호,  A.형식승인번호,  A.형식승인기관,  TO_CHAR(A.형식승인일, 'YYYY-MM-DD'),  A.최근3년간_매출액,  A.대표물품여부 " + this.getQueryCondition();
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
            case 200: {
                queryCondition = " FROM 사용_면허사항 A, SYN_공동코드 B  WHERE A.사업자등록번호 = ?  AND B.코드구분 = 'GU9'  AND A.면허코드 = B.코드 ";
                break;
            }
            case 400: {
                queryCondition = " FROM 사용_공장정보  WHERE 사업자등록번호 = ? ";
                break;
            }
            case 500: {
                queryCondition = " FROM 사용_입찰대리인  WHERE 사업자등록번호 = ? ";
                break;
            }
            case 700: {
                queryCondition = " FROM 사용_조달품목 A, xzcate010m@dbl_usemn_c_mokmn B  WHERE A.사업자등록번호 = ?  AND A.물품분류번호 = B.CATE_ID  AND A.제조여부 = 'N' ";
                break;
            }
            case 600: {
                queryCondition = " FROM 사용_조달품목 A, xzcate010m@dbl_usemn_c_mokmn B  WHERE A.사업자등록번호 = ?  AND A.물품분류번호 = B.CATE_ID  AND A.제조여부 = 'Y' ";
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
            case 600: {
                psmt.setString(1, this.saupNo);
                break;
            }
            case 700: {
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
}
