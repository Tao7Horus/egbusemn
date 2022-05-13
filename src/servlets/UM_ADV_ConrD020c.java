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
import java.util.StringTokenizer;
import java.net.URLEncoder;
import common.ComStr;
import common.Trx;
import g2b.sso.SSO;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_ConrD020c extends HttpServlet
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
    public final int MODE_DP1 = 1100;
    public final int MODE_GM1 = 1200;
    public final int MODE_GJ1 = 1400;
    public final int MODE_ID1 = 1500;
    public final int MODE_JP1 = 1600;
    public final int MODE_GP1 = 1700;
    
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
        System.out.println("0");
        final SSO sso = new SSO(req, res);
        sso.checkLogin();
        System.out.println("1");
        String upCheck = "N";
        String DupCheck = "N";
        String DaeCeo = null;
        String DaeLice = null;
        String DaeGood = null;
        String procID = null;
        int ilno = 1;
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
        procID = "C234567890123";
        final UM_ADV_ConrD020c entity = new UM_ADV_ConrD020c();
        System.out.println("2");
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
        String eSimpleSangho = "";
        String openDate = "";
        String bubinOpenDate = "";
        String jobGubun = "";
        String jobGubun_1 = "";
        String jobGubun_2 = "";
        String jobGubun_3 = "";
        String jobGubun_4 = "";
        String jobGubun_5 = "";
        String makeGubun = "";
        String dpUpjongCode = "";
        String dpUpjongCodeName = "";
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
        System.out.println("3");
        Label_14902: {
            try {
                trx = new Trx(this, "usemn");
                con = trx.getConnection();
                con.setAutoCommit(false);
                flag = ((req.getParameter("flag") == null) ? "" : req.getParameter("flag"));
                saupNo_0 = ((req.getParameter("saupNo") == null) ? "" : req.getParameter("saupNo"));
                saupNo_2 = saupNo_0.substring(0, 3);
                saupNo_3 = saupNo_0.substring(4, 6);
                saupNo_4 = saupNo_0.substring(7, 12);
                saupNo = String.valueOf(saupNo_2) + saupNo_3 + saupNo_4;
                System.out.println("4");
                nationality = ((req.getParameter("nationality") == null) ? "" : req.getParameter("nationality"));
                nation = ((req.getParameter("nation") == null) ? "" : req.getParameter("nation"));
                sangho = ((req.getParameter("sangho") == null) ? "" : req.getParameter("sangho"));
                eSangho = ((req.getParameter("eSangho") == null) ? "" : req.getParameter("eSangho"));
                eSimpleSangho = ((req.getParameter("eSimpleSangho") == null) ? "" : req.getParameter("eSimpleSangho"));
                openDate = ((req.getParameter("openDate") == null) ? "" : req.getParameter("openDate"));
                bubinOpenDate = ((req.getParameter("bubinOpenDate") == null) ? "" : req.getParameter("bubinOpenDate"));
                jobGubun_1 = ((req.getParameter("hjobGubun_1") == null) ? "" : req.getParameter("hjobGubun_1"));
                jobGubun_2 = ((req.getParameter("hjobGubun_2") == null) ? "" : req.getParameter("hjobGubun_2"));
                jobGubun_3 = ((req.getParameter("hjobGubun_3") == null) ? "" : req.getParameter("hjobGubun_3"));
                jobGubun_4 = ((req.getParameter("jobGubun_4") == null) ? "0" : req.getParameter("jobGubun_4"));
                jobGubun_5 = ((req.getParameter("jobGubun_5") == null) ? "0" : req.getParameter("jobGubun_5"));
                jobGubun = String.valueOf(jobGubun_1) + jobGubun_2 + jobGubun_3 + jobGubun_4 + jobGubun_5;
                makeGubun = ((req.getParameter("hmakeGubun") == null) ? "" : req.getParameter("hmakeGubun"));
                dpUpjongCode = ((req.getParameter("dpUpjongCode") == null) ? "" : req.getParameter("dpUpjongCode"));
                dpUpjongCodeName = ((req.getParameter("dpUpjongCodeName") == null) ? "" : req.getParameter("dpUpjongCodeName"));
                bubinNo = ((req.getParameter("bubinNo") == null) ? "" : req.getParameter("bubinNo"));
                comGubun2 = ((req.getParameter("comGubun2") == null) ? "" : req.getParameter("comGubun2"));
                comGubunYear = ((req.getParameter("comGubunYear") == null) ? "" : req.getParameter("comGubunYear"));
                jabon = ((req.getParameter("jabon") == null) ? "" : req.getParameter("jabon"));
                employeeNo = ((req.getParameter("employeeNo") == null) ? "" : req.getParameter("employeeNo"));
                accountDate = ((req.getParameter("accountDate") == null) ? "" : req.getParameter("accountDate"));
                zipCode2 = ((req.getParameter("zipCode") == null) ? "" : req.getParameter("zipCode"));
                zipCode3 = zipCode2.substring(0, 3);
                zipCode4 = zipCode2.substring(4, 7);
                zipCode = String.valueOf(zipCode3) + zipCode4;
                localCode = ((req.getParameter("localCode") == null) ? "" : req.getParameter("localCode"));
                localCodeName = ((req.getParameter("localCodeName") == null) ? "" : req.getParameter("localCodeName"));
                addr = ((req.getParameter("addr") == null) ? "" : req.getParameter("addr"));
                restAddr = ((req.getParameter("restAddr") == null) ? "" : req.getParameter("restAddr"));
                tel_1 = ((req.getParameter("tel_1") == null) ? "" : req.getParameter("tel_1"));
                tel_2 = ((req.getParameter("tel_2") == null) ? "" : req.getParameter("tel_2"));
                tel = String.valueOf(tel_1) + "-" + tel_2;
                fax_1 = ((req.getParameter("fax_1") == null) ? "" : req.getParameter("fax_1"));
                fax_2 = ((req.getParameter("fax_2") == null) ? "" : req.getParameter("fax_2"));
                System.out.println("5");
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
                UM_GOE_ConiC010b dbb = null;
                dbb = this.select_main(saupNo);
                this.setSaupNo(saupNo);
                final String[] ceoJuminNo = this.getParams(req, "ceoJuminNo", "");
                final String[] ceoName = this.getParams(req, "ceoName", "");
                final String[] ceoMail = this.getParams(req, "ceoMail", "");
                final String[] ceoYN = this.getParams(req, "hceoYN", "");
                final String[] dpStatus = this.getParams(req, "dpStatus", "");
                final int dpTotalCount = (req.getParameter("dpTotalCount") == null || req.getParameter("dpTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("dpTotalCount"));
                final String[][] DPb = this.getDPData();
                System.out.println("6");
                for (int i = 1; i < ceoName.length; ++i) {
                    System.out.println("aaaaaaa==" + ceoName[i]);
                }
                final String[] gmLicenseCode = this.getParams(req, "gmLicenseCode", "");
                final String[] gmLicenseNo = this.getParams(req, "gmLicenseNo", "");
                final String[] gmLicenseBeginDate = this.getParams(req, "gmLicenseBeginDate", "");
                final String[] gmLicenseEndDate = this.getParams(req, "gmLicenseEndDate", "");
                final String[] gmSigongAccount = this.getParams(req, "gmSigongAccount", "");
                final String[] gmPeonggaYear = this.getParams(req, "gmPeonggaYear", "");
                final String[] gmDpLicenseYN = this.getParams(req, "hgmDpLicenseYN", "");
                final String[] gmStatus = this.getParams(req, "gmStatus", "");
                final int gmTotalCount = (req.getParameter("gmTotalCount") == null || req.getParameter("gmTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("gmTotalCount"));
                final String[][] GMb = this.getGMData();
                System.out.println("7");
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
                System.out.println("8");
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
                System.out.println("9");
                for (int l = 1; l < juminNo.length; ++l) {
                    System.out.println("IDbIDbIDbIDb==" + juminNo[l] + "===" + name[l]);
                }
                final String[] goodsNo = this.getParams(req, "goodsNo", "");
                final String[] formNo = this.getParams(req, "formNo", "");
                final String[] threeSale = this.getParams(req, "threeSale", "");
                final String[] makeYN = this.getParams(req, "hmakeYN", "");
                final String[] dpGoodsYN = this.getParams(req, "hdpGoodsYN", "");
                final String[] jpStatus = this.getParams(req, "jpStatus", "");
                final int jpTotalCount = (req.getParameter("jpTotalCount") == null || req.getParameter("jpTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("jpTotalCount"));
                final String[][] JPb = this.getJPData();
                System.out.println("10");
                for (int m = 1; m < goodsNo.length; ++m) {
                    System.out.println("JPbJPbJPbJPb==" + goodsNo[m] + "===" + formNo[m]);
                }
                final String[] ggoodsNo = this.getParams(req, "ggoodsNo", "");
                final String[] gformNo = this.getParams(req, "gformNo", "");
                final String[] gthreeSale = this.getParams(req, "gthreeSale", "");
                final String[] gmakeYN = this.getParams(req, "ghmakeYN", "");
                final String[] gdpGoodsYN = this.getParams(req, "ghdpGoodsYN", "");
                final String[] gpStatus = this.getParams(req, "gpStatus", "");
                final int gpTotalCount = (req.getParameter("gpTotalCount") == null || req.getParameter("gpTotalCount").equals("")) ? 0 : Integer.parseInt(req.getParameter("gpTotalCount"));
                final String[][] GPb = this.getGPData();
                System.out.println("11");
                for (int i2 = 1; i2 < ggoodsNo.length; ++i2) {
                    System.out.println("GPbGPbGPbGPb==" + ggoodsNo[i2] + "===" + gformNo[i2]);
                }
                System.out.println("12");
                System.out.println("====================대표자정보수정시작=====================");
                String DaeCeo2 = "";
                String DaeCeo3 = "";
                String DaeNo1 = "";
                String DaeNo2 = "";
                String DaeMail = "";
                dpquery1 = " INSERT INTO 사용_대표자 (  사업자등록번호, 대표자주민번호, 대표자명, 대표자메일주소, 대표대표자여부  ) VALUES (  ?, ?, ?, ?, ?  ) ";
                dppsmt1 = con.prepareStatement(dpquery1);
                dpquery2 = " UPDATE 사용_대표자  SET 사업자등록번호 = ?,     대표자주민번호 = ?,     대표자명 = ?,     대표자메일주소 = ?,     대표대표자여부 = ?  WHERE 사업자등록번호 = ?    AND 대표자주민번호 = ? ";
                dppsmt2 = con.prepareStatement(dpquery2);
                dpquery3 = " DELETE FROM 사용_대표자  WHERE 사업자등록번호 = ?    AND 대표자주민번호 = ? ";
                dppsmt3 = con.prepareStatement(dpquery3);
                for (int i3 = 1; i3 < dpTotalCount; ++i3) {
                    if (ceoYN[i3].equals("Y") && !dpStatus[i3].equals("D") && !dpStatus[i3].equals("N")) {
                        DaeCeo = ceoName[i3];
                        DaeCeo2 = ceoName[i3];
                        DaeNo1 = ceoJuminNo[i3];
                    }
                    else {
                        DaeCeo3 = ceoName[i3];
                        DaeNo2 = ceoJuminNo[i3];
                    }
                    if (!ceoMail[i3].equals("")) {
                        DaeMail = ceoMail[i3];
                    }
                    System.out.println("FOR 시작" + dpStatus[i3]);
                    if (dpStatus[i3].equals("I")) {
                        System.out.println("FOR 시작IIIII");
                        dppsmt1.setString(1, saupNo);
                        dppsmt1.setString(2, ComStr.replace(ceoJuminNo[i3], "-", ""));
                        dppsmt1.setString(3, ceoName[i3].trim());
                        dppsmt1.setString(4, ceoMail[i3].trim());
                        dppsmt1.setString(5, ceoYN[i3].trim());
                        dppsmt1.executeUpdate();
                        dppsmt1.clearParameters();
                        this.icInsert(saupNo, ilno, "5", "I", "", ComStr.replace(ceoJuminNo[i3], "-", ""), "대표자주민번호", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "5", "I", "", ceoName[i3].trim(), "대표자명", procID, con);
                        ++ilno;
                        upCheck = "Y";
                    }
                    else if (dpStatus[i3].equals("F")) {
                        System.out.println("FOR 시작FFFFFF");
                        for (int j2 = 0; j2 < DPb.length; ++j2) {
                            System.out.println("FOR 시작FFF===FOR" + DPb.length + "==" + ceoJuminNo[i3] + "===" + DPb[j2][1]);
                            if (ComStr.replace(ceoJuminNo[i3], "-", "").equals(DPb[j2][1])) {
                                System.out.println("FOR 시작FFF===FOR  키일치");
                                if (!ceoName[i3].equals(DPb[j2][0]) || !ceoMail[i3].equals(DPb[j2][2]) || !ceoYN[i3].equals(DPb[j2][3])) {
                                    System.out.println("FOR 시작FFF===FOR  자료변경");
                                    dppsmt2.setString(1, saupNo);
                                    dppsmt2.setString(2, ComStr.replace(ceoJuminNo[i3], "-", ""));
                                    dppsmt2.setString(3, ceoName[i3].trim());
                                    dppsmt2.setString(4, ceoMail[i3].trim());
                                    dppsmt2.setString(5, ceoYN[i3].trim());
                                    dppsmt2.setString(6, saupNo);
                                    dppsmt2.setString(7, ComStr.replace(ceoJuminNo[i3], "-", ""));
                                    dppsmt2.executeUpdate();
                                    dppsmt2.clearParameters();
                                    System.out.println("FOR 시작FFF===FOR  자료변경==YYYYYY");
                                    upCheck = "Y";
                                    System.out.println("FOR 시작FFF===FOR  자료변경==YYYYYY" + ceoName[i3] + "===" + DPb[j2][0]);
                                    if (!ceoName[i3].equals(DPb[j2][0])) {
                                        this.icInsert(saupNo, ilno, "5", "U", DPb[j2][0], ceoName[i3].trim(), "대표자명", procID, con);
                                        ++ilno;
                                    }
                                    if (ceoYN[i3].equals("Y") && DPb[j2][3].equals("Y")) {
                                        DaeCeo = ceoName[i3];
                                        break;
                                    }
                                    if (ceoYN[i3].equals("Y")) {
                                        DupCheck = "Y";
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if (dpStatus[i3].equals("D")) {
                        System.out.println("FOR 시작DDDDDDD1111====" + dpquery3 + "==" + ceoJuminNo[i3]);
                        dppsmt3.setString(1, saupNo);
                        dppsmt3.setString(2, ComStr.replace(ceoJuminNo[i3], "-", ""));
                        dppsmt3.executeUpdate();
                        System.out.println("FOR 시작DDDDDDD====" + dpquery3);
                        dppsmt3.clearParameters();
                        this.icInsert(saupNo, ilno, "5", "D", ComStr.replace(ceoJuminNo[i3], "-", ""), "", "대표자주민번호", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "5", "D", ceoName[i3].trim(), "", "대표자명", procID, con);
                        ++ilno;
                    }
                }
                System.out.println("====================면허사항정보 수정시작=====================");
                String DaeLiCode = "";
                String DaeLiDate = "";
                gmquery1 = " INSERT INTO 사용_면허사항 (  사업자등록번호, 면허코드, 면허번호, 면허취득일자, 면허만료일자,  시공능력평가액, 평가액기준년도, 대표면허여부  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?  ) ";
                gmpsmt1 = con.prepareStatement(gmquery1);
                gmquery2 = " UPDATE 사용_면허사항  SET 면허번호 = ?,     면허취득일자 = ?,     면허만료일자 = ?,     시공능력평가액 = ?,     평가액기준년도 = ?,     대표면허여부 = ?  WHERE 사업자등록번호 = ?    AND 면허코드 = ? ";
                gmpsmt2 = con.prepareStatement(gmquery2);
                gmquery3 = " DELETE FROM 사용_면허사항  WHERE 사업자등록번호 = ?    AND 면허코드 = ? ";
                gmpsmt3 = con.prepareStatement(gmquery3);
                if (gmTotalCount == 1 || gmTotalCount == 0) {
                    DaeLice = "";
                }
                for (int i4 = 1; i4 < gmTotalCount; ++i4) {
                    if (gmDpLicenseYN[i4].equals("Y") && !gmStatus[i4].equals("D") && !gmStatus[i4].equals("N")) {
                        DaeLice = gmLicenseCode[i4];
                        DaeLiCode = gmLicenseCode[i4];
                        DaeLiDate = gmLicenseBeginDate[i4];
                    }
                    System.out.println("FOR 시작" + gmStatus[i4]);
                    if (gmStatus[i4].equals("I")) {
                        System.out.println("FOR 시작IIIII");
                        gmpsmt1.setString(1, saupNo);
                        gmpsmt1.setString(2, gmLicenseCode[i4].trim());
                        gmpsmt1.setString(3, gmLicenseNo[i4].trim());
                        gmpsmt1.setString(4, gmLicenseBeginDate[i4].trim());
                        gmpsmt1.setString(5, gmLicenseEndDate[i4].trim());
                        gmpsmt1.setLong(6, Long.parseLong(ComStr.replace(gmSigongAccount[i4], ",", "")));
                        gmpsmt1.setString(7, gmPeonggaYear[i4].trim());
                        gmpsmt1.setString(8, gmDpLicenseYN[i4].trim());
                        System.out.println("FOR 시작IIIII==" + gmquery1);
                        gmpsmt1.executeUpdate();
                        gmpsmt1.clearParameters();
                        this.icInsert(saupNo, ilno, "4", "I", "", gmLicenseCode[i4].trim(), "면허코드", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "4", "I", "", gmLicenseEndDate[i4].trim(), "면허만료일자", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "4", "I", "", ComStr.replace(gmSigongAccount[i4], ",", ""), "시공능력평가액", procID, con);
                        ++ilno;
                        upCheck = "Y";
                    }
                    else if (gmStatus[i4].equals("F")) {
                        System.out.println("FOR 시작FFFFFF");
                        for (int j3 = 0; j3 < GMb.length; ++j3) {
                            if (gmLicenseCode[i4].trim().equals(GMb[j3][0])) {
                                System.out.println("FOR 시작FFF===FOR  키일치");
                                if (!gmLicenseNo[i4].equals(GMb[j3][2]) || !gmLicenseBeginDate[i4].equals(GMb[j3][3]) || !gmLicenseEndDate[i4].equals(GMb[j3][4]) || !ComStr.replace(gmSigongAccount[i4], ",", "").equals(GMb[j3][5]) || !gmPeonggaYear[i4].equals(GMb[j3][6]) || !gmDpLicenseYN[i4].equals(GMb[j3][7])) {
                                    System.out.println("FOR 시작FFF===FOR  수정하러들어옴");
                                    gmpsmt2.setString(1, gmLicenseNo[i4]);
                                    gmpsmt2.setString(2, gmLicenseBeginDate[i4]);
                                    gmpsmt2.setString(3, gmLicenseEndDate[i4]);
                                    gmpsmt2.setLong(4, Long.parseLong(ComStr.replace(gmSigongAccount[i4], ",", "")));
                                    gmpsmt2.setString(5, gmPeonggaYear[i4]);
                                    gmpsmt2.setString(6, gmDpLicenseYN[i4]);
                                    gmpsmt2.setString(7, saupNo);
                                    gmpsmt2.setString(8, gmLicenseCode[i4]);
                                    gmpsmt2.executeUpdate();
                                    System.out.println("FOR 시작FFF===FOR  자료변경==수정중222");
                                    gmpsmt2.clearParameters();
                                    System.out.println("FOR 시작FFF===FOR  자료변경==수정중333");
                                    System.out.println("FOR 시작FFF===FOR  자료변경==YYYYYY");
                                    upCheck = "Y";
                                    System.out.println("FOR 시작FFF===FOR  자료변경==YYYYYY" + gmLicenseNo[i4] + "===" + GMb[j3][0]);
                                    if (!gmLicenseEndDate[i4].equals(GMb[j3][4])) {
                                        this.icInsert(saupNo, ilno, "4", "U", GMb[j3][4], gmLicenseEndDate[i4], "면허만료일자", procID, con);
                                        ++ilno;
                                    }
                                    if (!gmSigongAccount[i4].equals(GMb[j3][5])) {
                                        this.icInsert(saupNo, ilno, "4", "U", GMb[j3][5], gmSigongAccount[i4], "시공능력평가액", procID, con);
                                        ++ilno;
                                    }
                                    if (gmDpLicenseYN[i4].equals("Y") && GMb[j3][7].equals("Y")) {
                                        DaeLice = gmDpLicenseYN[i4];
                                        break;
                                    }
                                    if (gmDpLicenseYN[i4].equals("Y")) {
                                        DupCheck = "Y";
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if (gmStatus[i4].equals("D")) {
                        System.out.println("FOR 시작DDDDDDD1111====");
                        gmpsmt3.setString(1, saupNo);
                        gmpsmt3.setString(2, gmLicenseCode[i4]);
                        gmpsmt3.executeUpdate();
                        System.out.println("FOR 시작DDDDDDD====" + gmquery3);
                        gmpsmt3.clearParameters();
                        this.icInsert(saupNo, ilno, "4", "D", gmLicenseCode[i4], "", "면허코드", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "4", "D", gmLicenseEndDate[i4], "", "면허만료일자", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "4", "D", ComStr.replace(gmSigongAccount[i4], ",", ""), "", "시공능력평가액", procID, con);
                        ++ilno;
                    }
                }
                System.out.println("===면허사항정보 수정종료=========");
                System.out.println("====================면허사항정보 끝=====================");
                String gj_ZipCode = "";
                String gj_RestAddr = "";
                String gj_Addr = "";
                String gj_Tel = "";
                String gj_Fax = "";
                System.out.println("====================공장정보 수정시작=====================");
                gjquery1 = " INSERT INTO 사용_공장정보 (  사업자등록번호, 일련번호, 공장명, 공장우편번호, 공장주소,  공장나머지주소, 공장전화번호, 공장FAX번호  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?  ) ";
                gjpsmt1 = con.prepareStatement(gjquery1);
                gjquery2 = " DELETE FROM 사용_공장정보  WHERE 사업자등록번호 = ? ";
                gjpsmt2 = con.prepareStatement(gjquery2);
                System.out.println("기존 자료 삭제 시작====" + gjpsmt2);
                gjpsmt2.setString(1, saupNo);
                gjpsmt2.executeUpdate();
                gjpsmt2.clearParameters();
                System.out.println("기존 자료 삭제 종료====" + gjpsmt2);
                for (int i5 = 1; i5 < gjTotalCount; ++i5) {
                    System.out.println("FOR 시작====" + gjStatus[i5]);
                    if (gjStatus[i5].equals("F") || gjStatus[i5].equals("I") || gjStatus[i5].equals("U")) {
                        System.out.println("FOR 시작====등록시작==" + factoryName[i5].trim());
                        gjpsmt1.setString(1, saupNo);
                        gjpsmt1.setInt(2, i5);
                        gjpsmt1.setString(3, factoryName[i5].trim());
                        gjpsmt1.setString(4, ComStr.replace(factoryZipCode[i5], "-", ""));
                        gjpsmt1.setString(5, factoryAddr[i5].trim());
                        gjpsmt1.setString(6, factoryRestAddr[i5].trim());
                        gjpsmt1.setString(7, factoryTel[i5].trim());
                        gjpsmt1.setString(8, factoryFax[i5].trim());
                        gjpsmt1.executeUpdate();
                        gjpsmt1.clearParameters();
                        System.out.println("FOR 끝====등록종료==" + factoryAddr[i5].trim());
                        gj_ZipCode = ComStr.replace(factoryZipCode[i5], "-", "");
                        gj_RestAddr = factoryAddr[i5].trim();
                        gj_Addr = factoryAddr[i5].trim();
                        gj_Tel = factoryTel[i5].trim();
                        gj_Fax = factoryFax[i5].trim();
                    }
                }
                System.out.println("====================공장정보 끝=====================");
                System.out.println("====================입찰대리인 수정시작=====================");
                idquery1 = " INSERT INTO 사용_입찰대리인 (  사업자등록번호, 주민등록번호, 성명, 부서, 직책명,  전화번호, E_MAIL, 휴대폰, FAX  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?, ?  ) ";
                idpsmt1 = con.prepareStatement(idquery1);
                idquery2 = " DELETE FROM 사용_입찰대리인  WHERE 사업자등록번호 = ? ";
                idpsmt2 = con.prepareStatement(idquery2);
                System.out.println("기존 자료 삭제 시작====" + idpsmt2);
                idpsmt2.setString(1, saupNo);
                idpsmt2.executeUpdate();
                idpsmt2.clearParameters();
                System.out.println("기존 자료 삭제 종료====" + idpsmt2);
                for (int i5 = 1; i5 < idTotalCount; ++i5) {
                    System.out.println("FOR 시작====" + idStatus[i5]);
                    if (idStatus[i5].equals("F") || idStatus[i5].equals("I") || idStatus[i5].equals("U")) {
                        System.out.println("FOR 시작====등록시작==" + ComStr.replace(juminNo[i5], "-", ""));
                        idpsmt1.setString(1, saupNo);
                        idpsmt1.setString(2, ComStr.replace(juminNo[i5], "-", ""));
                        idpsmt1.setString(3, name[i5].trim());
                        idpsmt1.setString(4, jobPart[i5].trim());
                        idpsmt1.setString(5, dutyName[i5].trim());
                        idpsmt1.setString(6, idtel[i5].trim());
                        idpsmt1.setString(7, mail[i5].trim());
                        idpsmt1.setString(8, handphone[i5].trim());
                        idpsmt1.setString(9, idfax[i5].trim());
                        idpsmt1.executeUpdate();
                        idpsmt1.clearParameters();
                        System.out.println("FOR 끝====등록종료==" + name[i5].trim());
                    }
                }
                System.out.println("====================제조 조달품목정보 수정시작=====================");
                jpquery1 = " INSERT INTO 사용_조달품목 (  사업자등록번호, 물품분류번호, 형식승인번호, 최근3년간_매출액, 제조여부,  대표물품여부  ) VALUES (  ?, ?, ?, ?, ?,  ?  ) ";
                jppsmt1 = con.prepareStatement(jpquery1);
                jpquery2 = " UPDATE 사용_조달품목  SET 형식승인번호 = ?,     최근3년간_매출액 = ?,     제조여부 = ?,     대표물품여부 = ?  WHERE 사업자등록번호 = ?    AND 물품분류번호 = ? ";
                jppsmt2 = con.prepareStatement(jpquery2);
                jpquery3 = " DELETE FROM 사용_조달품목  WHERE 사업자등록번호 = ?    AND 물품분류번호 = ? ";
                jppsmt3 = con.prepareStatement(jpquery3);
                if ((jpTotalCount == 1 || jpTotalCount == 0) && (gpTotalCount == 1 || gpTotalCount == 0)) {
                    DaeGood = "";
                }
                for (int i5 = 1; i5 < jpTotalCount; ++i5) {
                    if (dpGoodsYN[i5].equals("Y") && !jpStatus[i5].equals("D") && !jpStatus[i5].equals("N")) {
                        DaeGood = goodsNo[i5];
                    }
                    System.out.println("FOR 시작" + jpStatus[i5]);
                    if (jpStatus[i5].equals("I")) {
                        System.out.println("FOR 시작IIIII");
                        jppsmt1.setString(1, saupNo);
                        jppsmt1.setString(2, goodsNo[i5].trim());
                        jppsmt1.setString(3, formNo[i5].trim());
                        jppsmt1.setString(4, ComStr.replace(threeSale[i5], ",", ""));
                        jppsmt1.setString(5, makeYN[i5].trim());
                        jppsmt1.setString(6, dpGoodsYN[i5].trim());
                        System.out.println("FOR 시작IIIII==" + jpquery1);
                        jppsmt1.executeUpdate();
                        jppsmt1.clearParameters();
                        this.icInsert(saupNo, ilno, "3", "I", "", goodsNo[i5].trim(), "물품분류번호", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "3", "I", "", makeYN[i5].trim(), "제조여부", procID, con);
                        ++ilno;
                        upCheck = "Y";
                    }
                    else if (jpStatus[i5].equals("F")) {
                        System.out.println("FOR 시작FFFFFF");
                        for (int j4 = 0; j4 < JPb.length; ++j4) {
                            if (goodsNo[i5].trim().equals(JPb[j4][0])) {
                                System.out.println("FOR 시작FFF===FOR  키일치");
                                if (!formNo[i5].trim().equals(JPb[j4][1]) || !ComStr.replace(threeSale[i5], ",", "").equals(JPb[j4][2]) || !makeYN[i5].trim().equals(JPb[j4][3]) || !dpGoodsYN[i5].trim().equals(JPb[j4][4])) {
                                    System.out.println("FOR 시작FFF===FOR  수정하러들어옴");
                                    jppsmt2.setString(1, formNo[i5].trim());
                                    jppsmt2.setString(2, ComStr.replace(threeSale[i5], ",", ""));
                                    jppsmt2.setString(3, makeYN[i5].trim());
                                    jppsmt2.setString(4, dpGoodsYN[i5].trim());
                                    jppsmt2.setString(5, saupNo);
                                    jppsmt2.setString(6, goodsNo[i5]);
                                    jppsmt2.executeUpdate();
                                    System.out.println("FOR 시작FFF===FOR  자료변경==수정중222");
                                    jppsmt2.clearParameters();
                                    System.out.println("FOR 시작FFF===FOR  자료변경==수정중333");
                                    System.out.println("FOR 시작FFF===FOR  자료변경==YYYYYY");
                                    upCheck = "Y";
                                    System.out.println("FOR 시작FFF===FOR  자료변경==YYYYYY" + goodsNo[i5] + "===" + JPb[j4][0]);
                                    if (!goodsNo[i5].equals(JPb[j4][0])) {
                                        this.icInsert(saupNo, ilno, "3", "U", JPb[j4][0], goodsNo[i5], "물품분류번호", procID, con);
                                        ++ilno;
                                    }
                                    if (!makeYN[i5].equals(JPb[j4][3])) {
                                        this.icInsert(saupNo, ilno, "3", "U", JPb[j4][3], makeYN[i5], "제조여부", procID, con);
                                        ++ilno;
                                    }
                                    if (dpGoodsYN[i5].equals("Y") && JPb[j4][0].equals("Y")) {
                                        DaeGood = goodsNo[i5];
                                        break;
                                    }
                                    if (dpGoodsYN[i5].equals("Y")) {
                                        DupCheck = "Y";
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if (jpStatus[i5].equals("D")) {
                        System.out.println("FOR 시작DDDDDDD1111====");
                        jppsmt3.setString(1, saupNo);
                        jppsmt3.setString(2, goodsNo[i5]);
                        jppsmt3.executeUpdate();
                        jppsmt3.clearParameters();
                        this.icInsert(saupNo, ilno, "3", "D", goodsNo[i5], "", "물품분류번호", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "3", "D", makeYN[i5], "", "제조여부", procID, con);
                        ++ilno;
                    }
                }
                System.out.println("===제조 조달품목 수정종료=========");
                con.commit();
                con.setAutoCommit(true);
                System.out.println("====================제조 조달품목 끝=====================");
                System.out.println("====================공급 조달품목정보 수정시작=====================");
                gpquery1 = " INSERT INTO 사용_조달품목 (  사업자등록번호, 물품분류번호, 형식승인번호, 최근3년간_매출액, 제조여부,  대표물품여부  ) VALUES (  ?, ?, ?, ?, ?,  ?  ) ";
                gppsmt1 = con.prepareStatement(gpquery1);
                gpquery2 = " UPDATE 사용_조달품목  SET 형식승인번호 = ?,     최근3년간_매출액 = ?,     제조여부 = ?,     대표물품여부 = ?  WHERE 사업자등록번호 = ?    AND 물품분류번호 = ? ";
                gppsmt2 = con.prepareStatement(gpquery2);
                gpquery3 = " DELETE FROM 사용_조달품목  WHERE 사업자등록번호 = ?    AND 물품분류번호 = ? ";
                gppsmt3 = con.prepareStatement(gpquery3);
                for (int i5 = 1; i5 < gpTotalCount; ++i5) {
                    if (gdpGoodsYN[i5].equals("Y") && !gpStatus[i5].equals("D") && !gpStatus[i5].equals("N")) {
                        DaeGood = ggoodsNo[i5];
                    }
                    System.out.println("FOR 시작" + gpStatus[i5]);
                    if (gpStatus[i5].equals("I")) {
                        System.out.println("FOR 시작IIIII");
                        gppsmt1.setString(1, saupNo);
                        gppsmt1.setString(2, ggoodsNo[i5].trim());
                        gppsmt1.setString(3, gformNo[i5].trim());
                        gppsmt1.setString(4, ComStr.replace(gthreeSale[i5], ",", ""));
                        gppsmt1.setString(5, gmakeYN[i5].trim());
                        gppsmt1.setString(6, gdpGoodsYN[i5].trim());
                        System.out.println("FOR 시작IIIII==" + gpquery1);
                        gppsmt1.executeUpdate();
                        gppsmt1.clearParameters();
                        this.icInsert(saupNo, ilno, "3", "I", "", ggoodsNo[i5].trim(), "물품분류번호", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "3", "I", "", gmakeYN[i5].trim(), "제조여부", procID, con);
                        ++ilno;
                        upCheck = "Y";
                    }
                    else if (gpStatus[i5].equals("F")) {
                        System.out.println("FOR 시작FFFFFF");
                        for (int j4 = 0; j4 < GPb.length; ++j4) {
                            if (ggoodsNo[i5].trim().equals(GPb[j4][0])) {
                                System.out.println("FOR 시작FFF===FOR  키일치");
                                if (!gformNo[i5].trim().equals(GPb[j4][1]) || !ComStr.replace(gthreeSale[i5], ",", "").equals(GPb[j4][2]) || !gmakeYN[i5].trim().equals(GPb[j4][3]) || !gdpGoodsYN[i5].trim().equals(GPb[j4][4])) {
                                    System.out.println("FOR 시작FFF===FOR  수정하러들어옴");
                                    gppsmt2.setString(1, gformNo[i5].trim());
                                    gppsmt2.setString(2, ComStr.replace(gthreeSale[i5], ",", ""));
                                    gppsmt2.setString(3, gmakeYN[i5].trim());
                                    gppsmt2.setString(4, gdpGoodsYN[i5].trim());
                                    gppsmt2.setString(5, saupNo);
                                    gppsmt2.setString(6, ggoodsNo[i5]);
                                    gppsmt2.executeUpdate();
                                    System.out.println("FOR 시작FFF===FOR  자료변경==수정중222");
                                    gppsmt2.clearParameters();
                                    System.out.println("FOR 시작FFF===FOR  자료변경==수정중333");
                                    System.out.println("FOR 시작FFF===FOR  자료변경==YYYYYY");
                                    upCheck = "Y";
                                    System.out.println("FOR 시작FFF===FOR  자료변경==YYYYYY" + ggoodsNo[i5] + "===" + GPb[j4][0]);
                                    if (!ggoodsNo[i5].equals(GPb[j4][0])) {
                                        this.icInsert(saupNo, ilno, "3", "U", GPb[j4][0], ggoodsNo[i5], "물품분류번호", procID, con);
                                        ++ilno;
                                    }
                                    if (!gmakeYN[i5].equals(GPb[j4][3])) {
                                        this.icInsert(saupNo, ilno, "3", "U", GPb[j4][3], gmakeYN[i5], "제조여부", procID, con);
                                        ++ilno;
                                    }
                                    if (gdpGoodsYN[i5].equals("Y") && GPb[j4][0].equals("Y")) {
                                        DaeGood = ggoodsNo[i5];
                                        break;
                                    }
                                    if (gdpGoodsYN[i5].equals("Y")) {
                                        DupCheck = "Y";
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if (gpStatus[i5].equals("D")) {
                        System.out.println("FOR 시작DDDDDDD1111====");
                        gppsmt3.setString(1, saupNo);
                        gppsmt3.setString(2, ggoodsNo[i5]);
                        gppsmt3.executeUpdate();
                        gppsmt3.clearParameters();
                        this.icInsert(saupNo, ilno, "3", "D", ggoodsNo[i5], "", "물품분류번호", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "3", "D", gmakeYN[i5], "", "제조여부", procID, con);
                        ++ilno;
                    }
                }
                System.out.println("===공급 조달품목 수정종료=========");
                con.commit();
                con.setAutoCommit(true);
                System.out.println("====================공급 조달품목 끝=====================");
                System.out.println("====================일반정보 수정시작=====================");
                query2 = " UPDATE 사용_조달업체마스터  SET 국적 = ?,     상호명 = ?,     영문상호명 = ?,     개업일자 = ?,     법인설립일자 = ?,     업무구분 = ?,     제조구분 = ?,     대표업종코드_표준 = ?,     법인등록번호 = ?,     기업구분2 = ?,     기업구분해당년도 = ?,     자본금 = ?,     종업원수 = ?,     최근결산년월 = ?,     우편번호 = ?,     지역코드 = ?,     주소 = ?,     나머지주소 = ?,     전화번호 = ?,     FAX번호 = ?,     홈페이지 = ?,     특례해당여부 = ?,     등록유효일자 = ADD_MONTHS(SYSDATE, 24),     갱신일자 = SYSDATE,     처리자ID = ?  WHERE 사업자등록번호 = ? ";
                psmt2 = con.prepareStatement(query2);
                System.out.println("UPdate=====" + query2);
                query3 = " INSERT INTO 사용_조달업체마스터이력 (  사업자등록번호, 갱신일자, 국적, 상호명, 영문상호명,  개업일자, 법인설립일자, 업무구분, 제조구분, 대표업종코드_표준,  법인등록번호, 기업구분1, 기업구분2, 기업구분해당년도, 자본금,  종업원수, 최근결산년월, 우편번호, 지역코드, 주소,  나머지주소, 전화번호, FAX번호, 홈페이지, 특례해당여부,  대표물품분류번호, 대표면허코드, 대표대표자명,  대표인증여부, 처리자ID, 등록유효일자, 등록일자  ) VALUES (  ?, SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ? , ?, ?, ADD_MONTHS(SYSDATE, 24), TO_DATE(SUBSTR(?,1,10),'YYYY-MM-DD')  ) ";
                psmt3 = con.prepareStatement(query3);
                System.out.println("Insert =====" + query3);
                System.out.println("수정DATA 있는지 검토" + dbb.getJabon());
                if (upCheck.equals("Y") || DupCheck.equals("Y") || !((dbb.getNationality() == null) ? "" : dbb.getNationality()).equals(nationality) || !((dbb.getSangho() == null) ? "" : dbb.getSangho()).equals(sangho) || (!((dbb.getESangho() == null) ? "" : dbb.getESangho()).equals(eSangho) || !dbb.getOpenDate().equals(openDate) || !dbb.getBubinOpenDate().equals(bubinOpenDate)) || !((dbb.getJobGubun() == null) ? "" : dbb.getJobGubun()).equals(jobGubun) || !((dbb.getMakeGubun() == null) ? "" : dbb.getMakeGubun()).equals(makeGubun) || !((dbb.getDpUpjongCode() == null) ? "" : dbb.getDpUpjongCode()).equals(dpUpjongCode) || !((dbb.getBubinNo() == null) ? "" : dbb.getBubinNo()).equals(bubinNo) || !((dbb.getComGubun2() == null) ? "" : dbb.getComGubun2()).equals(comGubun2) || (!((dbb.getComGubunYear() == null) ? "" : dbb.getComGubunYear()).equals(comGubunYear) || dbb.getJabon() != Long.parseLong(ComStr.replace(jabon, ",", "")) || dbb.getEmployeeNo() != Integer.parseInt(ComStr.replace(employeeNo, ",", ""))) || !((dbb.getAccountDate() == null) ? "" : dbb.getAccountDate()).equals(accountDate) || !((dbb.getZipCode() == null) ? "" : dbb.getZipCode()).equals(zipCode) || !((dbb.getLocalCode() == null) ? "" : dbb.getLocalCode()).equals(localCode) || !((dbb.getAddr() == null) ? "" : dbb.getAddr()).equals(addr) || !((dbb.getRestAddr() == null) ? "" : dbb.getRestAddr()).equals(restAddr) || !((dbb.getTel() == null) ? "" : dbb.getTel()).equals(tel) || !((dbb.getFax() == null) ? "" : dbb.getFax()).equals(fax) || !((dbb.getHomepage() == null) ? "" : dbb.getHomepage()).equals(homepage) || !((dbb.getExceptYN() == null) ? "" : dbb.getExceptYN()).equals(exceptYN)) {
                    System.out.println("업체마스터수정시작====" + sangho);
                    psmt2.setString(1, nationality);
                    psmt2.setString(2, sangho);
                    psmt2.setString(3, eSangho);
                    psmt2.setString(4, openDate);
                    psmt2.setString(5, bubinOpenDate);
                    psmt2.setString(6, jobGubun);
                    psmt2.setString(7, makeGubun);
                    psmt2.setString(8, dpUpjongCode);
                    psmt2.setString(9, bubinNo);
                    psmt2.setString(10, comGubun2);
                    psmt2.setString(11, comGubunYear);
                    psmt2.setLong(12, Long.parseLong(ComStr.replace(jabon, ",", "")));
                    psmt2.setInt(13, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                    psmt2.setString(14, accountDate);
                    psmt2.setString(15, zipCode);
                    psmt2.setString(16, localCode);
                    psmt2.setString(17, addr);
                    psmt2.setString(18, restAddr);
                    psmt2.setString(19, tel);
                    psmt2.setString(20, fax);
                    psmt2.setString(21, homepage);
                    psmt2.setString(22, exceptYN);
                    psmt2.setString(23, procID);
                    psmt2.setString(24, saupNo);
                    psmt2.executeUpdate();
                    psmt2.clearParameters();
                    System.out.println("업체마스터수정OK====" + sangho);
                    System.out.println("업체마스터이력정보 등록시작====" + sangho);
                    System.out.println(String.valueOf(saupNo) + "===" + nationality + "===" + dbb.getRegistDate() + "===" + openDate + "===" + accountDate + "===" + bubinOpenDate);
                    psmt3.setString(1, saupNo);
                    psmt3.setString(2, nationality);
                    psmt3.setString(3, sangho);
                    psmt3.setString(4, eSangho);
                    psmt3.setString(5, openDate);
                    psmt3.setString(6, bubinOpenDate);
                    psmt3.setString(7, jobGubun);
                    psmt3.setString(8, makeGubun);
                    psmt3.setString(9, dpUpjongCode);
                    psmt3.setString(10, bubinNo);
                    psmt3.setString(11, dbb.getComGubun1());
                    psmt3.setString(12, comGubun2);
                    psmt3.setString(13, comGubunYear);
                    psmt3.setLong(14, Long.parseLong(ComStr.replace(jabon, ",", "")));
                    psmt3.setInt(15, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                    psmt3.setString(16, accountDate);
                    psmt3.setString(17, zipCode);
                    psmt3.setString(18, localCode);
                    psmt3.setString(19, addr);
                    psmt3.setString(20, restAddr);
                    psmt3.setString(21, tel);
                    psmt3.setString(22, fax);
                    psmt3.setString(23, homepage);
                    psmt3.setString(24, exceptYN);
                    psmt3.setString(25, DaeGood);
                    psmt3.setString(26, DaeLice);
                    psmt3.setString(27, DaeCeo);
                    psmt3.setString(28, dbb.getDpOkYN());
                    psmt3.setString(29, procID);
                    psmt3.setString(30, dbb.getRegistDate());
                    psmt3.executeUpdate();
                    psmt3.clearParameters();
                    System.out.println("업체마스터이력정보 등록OK====" + sangho);
                    if (!sangho.equals(dbb.getSangho())) {
                        this.icInsert(saupNo, ilno, "1", "U", dbb.getSangho(), sangho, "상호명", procID, con);
                        ++ilno;
                    }
                    if (!jobGubun.equals(dbb.getJobGubun())) {
                        this.icInsert(saupNo, ilno, "1", "U", dbb.getJobGubun(), jobGubun, "업무구분", procID, con);
                        ++ilno;
                    }
                    if (!addr.equals(dbb.getAddr())) {
                        this.icInsert(saupNo, ilno, "1", "U", dbb.getAddr(), addr, "주소", procID, con);
                        ++ilno;
                    }
                }
                con.commit();
                con.setAutoCommit(true);
                System.out.println("============== 조달 EDI 자료 동기화 시작 ===================");
                System.out.println("============== 조달 EDI 자료 동기화 시작 ===================");
                System.out.println("============== 조달 EDI 자료 동기화 시작 ===================");
                String ls_NaUp = "";
                String ls_OiUp = "";
                String ls_GoUp = "";
                String ls_YoUp = "";
                String ls_NaUp2 = "";
                String ls_OiUp2 = "";
                String ls_GoUp2 = "";
                String ls_YoUp2 = "";
                String ls_local = "";
                String Code1 = "";
                String Code2 = "";
                String Code3 = "";
                String Code3_1 = "";
                final String comCode1 = "";
                final String comCode2 = "";
                final String comCode3 = "";
                final String comCode3_1 = "";
                String jicheongCode = "";
                int cCode1 = 0;
                int cCode2 = 0;
                int cCode3 = 0;
                int cCode3_1 = 0;
                query1 = " SELECT A.지청구분 FROM syn_지청 A, syn_매핑코드지역 B  WHERE A.지역코드 = B.기존코드 AND B.G2B코드 = ? ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, localCode);
                rs = psmt1.executeQuery();
                if (rs.next()) {
                    jicheongCode = rs.getString(1);
                }
                psmt1.clearParameters();
                System.out.println(String.valueOf(localCode) + "<==지청코드 ==>" + jicheongCode);
                if (dbb.getJobGubun().substring(0, 1).equals("0") && jobGubun.substring(0, 1).equals("1")) {
                    query1 = " SELECT MAX(등록업체코드) FROM SYN_등록업체  WHERE 등록업체코드 LIKE '" + jicheongCode + "1%' " + " OR 등록업체코드 LIKE '" + jicheongCode + "20%' ORDER BY 등록업체코드 DESC ";
                    psmt1 = con.prepareStatement(query1);
                    rs = psmt1.executeQuery();
                    if (rs.next()) {
                        Code1 = rs.getString(1);
                    }
                    cCode1 = Integer.parseInt(Code1) + 1;
                    ls_NaUp = Integer.toString(cCode1);
                    psmt1.clearParameters();
                    System.out.println(String.valueOf(jicheongCode) + "<==내자등록업체코드 신규==>" + ls_NaUp);
                }
                if (dbb.getJobGubun().substring(1, 2).equals("0") && jobGubun.substring(1, 2).equals("1")) {
                    query1 = " SELECT MAX(등록업체코드) FROM SYN_등록업체  WHERE 등록업체코드 LIKE '" + jicheongCode + "31%' " + " OR 등록업체코드 LIKE '" + jicheongCode + "32%'  " + " OR 등록업체코드 LIKE '" + jicheongCode + "33%'  " + " OR 등록업체코드 LIKE '" + jicheongCode + "34%' ORDER BY 등록업체코드 DESC ";
                    psmt1 = con.prepareStatement(query1);
                    rs = psmt1.executeQuery();
                    if (rs.next()) {
                        Code3 = rs.getString(1);
                    }
                    cCode3 = Integer.parseInt(Code3) + 1;
                    ls_GoUp = Integer.toString(cCode3);
                    psmt1.clearParameters();
                    System.out.println(String.valueOf(jicheongCode) + "<==시설등록업체(공사) 신규==>" + ls_GoUp);
                }
                if (dbb.getJobGubun().substring(2, 3).equals("0") && jobGubun.substring(2, 3).equals("1")) {
                    query1 = " SELECT MAX(등록업체코드) FROM SYN_등록업체  WHERE 등록업체코드 LIKE '" + jicheongCode + "35%' ORDER BY 등록업체코드 DESC ";
                    psmt1 = con.prepareStatement(query1);
                    rs = psmt1.executeQuery();
                    if (rs.next()) {
                        Code3_1 = rs.getString(1);
                    }
                    cCode3_1 = Integer.parseInt(Code3_1) + 1;
                    ls_YoUp = Integer.toString(cCode3_1);
                    psmt1.clearParameters();
                    System.out.println(String.valueOf(jicheongCode) + "<==시설등록업체(용역) 신규==>" + ls_YoUp);
                }
                if (dbb.getJobGubun().substring(4, 5).equals("0") && jobGubun.substring(4, 5).equals("1")) {
                    query1 = " SELECT MAX(등록업체코드) FROM SYN_등록업체  WHERE 등록업체코드 LIKE '" + jicheongCode + "2%' ORDER BY 등록업체코드 DESC ";
                    psmt1 = con.prepareStatement(query1);
                    rs = psmt1.executeQuery();
                    if (rs.next()) {
                        Code2 = rs.getString(1);
                    }
                    cCode2 = Integer.parseInt(Code2) + 1;
                    ls_OiUp = Integer.toString(cCode2);
                    psmt1.clearParameters();
                    System.out.println(String.valueOf(jicheongCode) + "<==외자등록업체코드 신규==>" + ls_OiUp);
                }
                query1 = " SELECT 기존코드 FROM SYN_매핑코드지역  WHERE G2B코드 = ? ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, localCode);
                rs = psmt1.executeQuery();
                ls_local = "";
                if (rs.next()) {
                    ls_local = rs.getString(1);
                }
                psmt1.clearParameters();
                System.out.println(String.valueOf(jicheongCode) + "<==매핑코드의 기존코드==>" + ls_local);
                System.out.println(String.valueOf(dbb.getJobGubun()) + "===" + jobGubun);
                System.out.println(jobGubun);
                System.out.println("subString===" + dbb.getJobGubun().substring(0, 1));
                System.out.println("subString===" + dbb.getJobGubun().substring(1, 2));
                System.out.println("subString===" + dbb.getJobGubun().substring(2, 3));
                System.out.println("subString===" + dbb.getJobGubun().substring(3, 4));
                System.out.println("subString===" + dbb.getJobGubun().substring(4, 5));
                final String ls_buf = this.select_EDI_No(saupNo, con);
                String ls_yn = "";
                if (ls_buf.equals("NO")) {
                    con.commit();
                    con.setAutoCommit(true);
                    res.sendRedirect("/jsp/AD/UM_ADJ_ConrC020s.jsp?saupNo=" + saupNo + "&nation=" + URLEncoder.encode(nation) + "&dpUpjongCodeName=" + URLEncoder.encode(dpUpjongCodeName) + "&localCodeName=" + URLEncoder.encode(localCodeName));
                }
                else {
                    final StringTokenizer buf = new StringTokenizer(ls_buf, "|");
                    ls_NaUp2 = buf.nextToken();
                    ls_OiUp2 = buf.nextToken();
                    ls_GoUp2 = buf.nextToken();
                    ls_YoUp2 = buf.nextToken();
                    ls_yn = buf.nextToken();
                    System.out.println("매핑테이블의 등록업체코드 정보===" + ls_NaUp2 + "==" + ls_OiUp2 + "==" + ls_GoUp2 + "==" + ls_YoUp2 + "==" + ls_yn);
                    if (!ls_yn.equals("N")) {
                        System.out.println("내자업체 조달EDI 작업==================" + ls_NaUp2 + "================================");
                        if (dbb.getJobGubun().substring(0, 1).equals("0") && jobGubun.substring(0, 1).equals("0")) {
                            System.out.println("기존, 변경 내자업체 아니다.(XX)");
                        }
                        else if (dbb.getJobGubun().substring(0, 1).equals("0") && jobGubun.substring(0, 1).equals("1")) {
                            System.out.println("기존 내자업체==X, 변경 내자업체 == 0 --- (X O)");
                            this.cont_MainNo_I(saupNo, ls_NaUp, "1", "I", sangho, DaeCeo2, DaeNo1, ls_local, zipCode, restAddr, addr, tel, fax, openDate, bubinOpenDate, comGubun1, registOkDate, nationality, jabon, employeeNo, homepage, DaeMail, bubinNo, gj_ZipCode, gj_RestAddr, gj_Addr, gj_Tel, gj_Fax, eSangho, DaeLiCode, con);
                            this.cont_JP(ls_NaUp, "I", jpTotalCount, goodsNo, threeSale, formNo, con);
                            this.cont_ID(ls_NaUp, "I", idTotalCount, dutyName, name, juminNo, con);
                        }
                        else if (dbb.getJobGubun().substring(0, 1).equals("1") && jobGubun.substring(0, 1).equals("0")) {
                            System.out.println("기존 내자업체==0, 변경 내자업체 == X --- (O X)");
                            this.cont_MainNo_I(saupNo, ls_NaUp2, "1", "D", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", con);
                        }
                        else if (dbb.getJobGubun().substring(0, 1).equals("1") && jobGubun.substring(0, 1).equals("1")) {
                            System.out.println("기존 내자업체==0, 변경 내자업체 == O --- (O O)");
                            this.cont_MainNo_I(saupNo, ls_NaUp2, "1", "U", sangho, DaeCeo2, DaeNo1, ls_local, zipCode, restAddr, addr, tel, fax, openDate, bubinOpenDate, comGubun1, registOkDate, nationality, jabon, employeeNo, homepage, DaeMail, bubinNo, gj_ZipCode, gj_RestAddr, gj_Addr, gj_Tel, gj_Fax, eSangho, DaeLiCode, con);
                            this.cont_JP(ls_NaUp2, "U", jpTotalCount, goodsNo, threeSale, formNo, con);
                            this.cont_ID(ls_NaUp2, "U", idTotalCount, dutyName, name, juminNo, con);
                        }
                        System.out.println("외자업체 조달EDI 작업==================" + ls_OiUp2 + "================================");
                        if (!dbb.getJobGubun().substring(4, 5).equals("0") || !jobGubun.substring(4, 5).equals("0")) {
                            if (dbb.getJobGubun().substring(4, 5).equals("0") && jobGubun.substring(4, 5).equals("1")) {
                                this.cont_MainNo_I(saupNo, ls_OiUp, "2", "I", sangho, DaeCeo2, DaeNo1, ls_local, zipCode, restAddr, addr, tel, fax, openDate, bubinOpenDate, comGubun1, registOkDate, nationality, jabon, employeeNo, homepage, DaeMail, bubinNo, gj_ZipCode, gj_RestAddr, gj_Addr, gj_Tel, gj_Fax, eSangho, DaeLiCode, con);
                                this.cont_ID(ls_OiUp, "I", idTotalCount, dutyName, name, juminNo, con);
                            }
                            else if (dbb.getJobGubun().substring(4, 5).equals("1") && jobGubun.substring(4, 5).equals("0")) {
                                this.cont_MainNo_I(saupNo, ls_OiUp2, "2", "D", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", con);
                            }
                            else if (dbb.getJobGubun().substring(4, 5).equals("1") && jobGubun.substring(4, 5).equals("1")) {
                                this.cont_MainNo_I(saupNo, ls_OiUp2, "2", "U", sangho, DaeCeo2, DaeNo1, ls_local, zipCode, restAddr, addr, tel, fax, openDate, bubinOpenDate, comGubun1, registOkDate, nationality, jabon, employeeNo, homepage, DaeMail, bubinNo, gj_ZipCode, gj_RestAddr, gj_Addr, gj_Tel, gj_Fax, eSangho, DaeLiCode, con);
                                this.cont_ID(ls_OiUp2, "U", idTotalCount, dutyName, name, juminNo, con);
                            }
                        }
                        System.out.println("공사업체 조달EDI 작업==================" + ls_GoUp2 + "================================");
                        if (!dbb.getJobGubun().substring(1, 2).equals("0") || !jobGubun.substring(1, 2).equals("0")) {
                            if (dbb.getJobGubun().substring(1, 2).equals("0") && jobGubun.substring(1, 2).equals("1")) {
                                this.cont_MainNo_I(saupNo, ls_GoUp, "3", "I", sangho, DaeCeo2, DaeNo1, ls_local, zipCode, restAddr, addr, tel, fax, openDate, bubinOpenDate, comGubun1, registOkDate, nationality, jabon, employeeNo, homepage, DaeMail, bubinNo, gj_ZipCode, gj_RestAddr, gj_Addr, gj_Tel, gj_Fax, eSangho, DaeLiCode, con);
                                this.cont_MS(ls_GoUp, "I", gmTotalCount, gmLicenseCode, gmLicenseBeginDate, gmSigongAccount, gmLicenseEndDate, con);
                                this.cont_ID(ls_GoUp, "I", idTotalCount, dutyName, name, juminNo, con);
                            }
                            else if (dbb.getJobGubun().substring(1, 2).equals("1") && jobGubun.substring(1, 2).equals("0")) {
                                this.cont_MainNo_I(saupNo, ls_GoUp2, "3", "D", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", con);
                            }
                            else if (dbb.getJobGubun().substring(1, 2).equals("1") && jobGubun.substring(1, 2).equals("1")) {
                                this.cont_MainNo_I(saupNo, ls_GoUp2, "3", "U", sangho, DaeCeo2, DaeNo1, ls_local, zipCode, restAddr, addr, tel, fax, openDate, bubinOpenDate, comGubun1, registOkDate, nationality, jabon, employeeNo, homepage, DaeMail, bubinNo, gj_ZipCode, gj_RestAddr, gj_Addr, gj_Tel, gj_Fax, eSangho, DaeLiCode, con);
                                this.cont_MS(ls_GoUp2, "U", gmTotalCount, gmLicenseCode, gmLicenseBeginDate, gmSigongAccount, gmLicenseEndDate, con);
                                this.cont_ID(ls_GoUp2, "U", idTotalCount, dutyName, name, juminNo, con);
                            }
                        }
                        System.out.println("용역업체 조달EDI 작업==================" + ls_YoUp2 + "================================");
                        if (!dbb.getJobGubun().substring(2, 3).equals("0") || !jobGubun.substring(2, 3).equals("0")) {
                            if (dbb.getJobGubun().substring(2, 3).equals("0") && jobGubun.substring(2, 3).equals("1")) {
                                this.cont_MainNo_I(saupNo, ls_YoUp, "3", "I", sangho, DaeCeo2, DaeNo1, ls_local, zipCode, restAddr, addr, tel, fax, openDate, bubinOpenDate, comGubun1, registOkDate, nationality, jabon, employeeNo, homepage, DaeMail, bubinNo, gj_ZipCode, gj_RestAddr, gj_Addr, gj_Tel, gj_Fax, eSangho, DaeLiCode, con);
                                this.cont_MS(ls_YoUp, "I", gmTotalCount, gmLicenseCode, gmLicenseBeginDate, gmSigongAccount, gmLicenseEndDate, con);
                                this.cont_ID(ls_YoUp, "I", idTotalCount, dutyName, name, juminNo, con);
                            }
                            else if (dbb.getJobGubun().substring(2, 3).equals("1") && jobGubun.substring(2, 3).equals("0")) {
                                this.cont_MainNo_I(saupNo, ls_YoUp2, "3", "D", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", con);
                            }
                            else if (dbb.getJobGubun().substring(2, 3).equals("1") && jobGubun.substring(2, 3).equals("1")) {
                                this.cont_MainNo_I(saupNo, ls_YoUp2, "3", "U", sangho, DaeCeo2, DaeNo1, ls_local, zipCode, restAddr, addr, tel, fax, openDate, bubinOpenDate, comGubun1, registOkDate, nationality, jabon, employeeNo, homepage, DaeMail, bubinNo, gj_ZipCode, gj_RestAddr, gj_Addr, gj_Tel, gj_Fax, eSangho, DaeLiCode, con);
                                this.cont_MS(ls_YoUp2, "U", gmTotalCount, gmLicenseCode, gmLicenseBeginDate, gmSigongAccount, gmLicenseEndDate, con);
                                this.cont_ID(ls_YoUp2, "U", idTotalCount, dutyName, name, juminNo, con);
                            }
                        }
                        res.sendRedirect("/jsp/AD/UM_ADJ_ConrC020s.jsp?saupNo=" + saupNo + "&nation=" + URLEncoder.encode(nation) + "&dpUpjongCodeName=" + URLEncoder.encode(dpUpjongCodeName) + "&localCodeName=" + URLEncoder.encode(localCodeName));
                        break Label_14902;
                    }
                    con.commit();
                    con.setAutoCommit(true);
                    res.sendRedirect("/jsp/AD/UM_ADJ_ConrC020s.jsp?saupNo=" + saupNo + "&nation=" + URLEncoder.encode(nation) + "&dpUpjongCodeName=" + URLEncoder.encode(dpUpjongCodeName) + "&localCodeName=" + URLEncoder.encode(localCodeName));
                }
                return;
            }
            catch (SQLException exc) {
                try {
                    con.rollback();
                    con.setAutoCommit(true);
                }
                catch (SQLException e) {
                    Log.debug("UM_ADV_ConrD020c.doPost block SQLException : Transaction Rollback간에 SQLException 발생함");
                    Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                    e.printStackTrace();
                }
                Log.debug("UM_ADV_ConrD020c.doPost block SQLException : ");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
            }
            catch (Exception exc2) {
                try {
                    con.rollback();
                    con.setAutoCommit(true);
                }
                catch (SQLException e) {
                    Log.debug("UM_ADV_ConrD020c.doPost block Exception : Transaction Rollback간에 Exception 발생함");
                    Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                    e.printStackTrace();
                }
                Log.debug("UM_ADV_ConrD020c.doPost block Exception : ");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                exc2.printStackTrace();
            }
            finally {
                if (psmt1 != null) {
                    try {
                        psmt1.close();
                    }
                    catch (Exception ex) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex2) {}
                }
                if (psmt3 != null) {
                    try {
                        psmt3.close();
                    }
                    catch (Exception ex3) {}
                }
                if (dppsmt1 != null) {
                    try {
                        dppsmt1.close();
                    }
                    catch (Exception ex4) {}
                }
                if (dppsmt2 != null) {
                    try {
                        dppsmt2.close();
                    }
                    catch (Exception ex5) {}
                }
                if (dppsmt3 != null) {
                    try {
                        dppsmt3.close();
                    }
                    catch (Exception ex6) {}
                }
                if (gmpsmt1 != null) {
                    try {
                        gmpsmt1.close();
                    }
                    catch (Exception ex7) {}
                }
                if (gmpsmt2 != null) {
                    try {
                        gmpsmt2.close();
                    }
                    catch (Exception ex8) {}
                }
                if (gmpsmt3 != null) {
                    try {
                        gmpsmt3.close();
                    }
                    catch (Exception ex9) {}
                }
                if (gjpsmt1 != null) {
                    try {
                        gjpsmt1.close();
                    }
                    catch (Exception ex10) {}
                }
                if (gjpsmt2 != null) {
                    try {
                        gjpsmt2.close();
                    }
                    catch (Exception ex11) {}
                }
                if (gjpsmt3 != null) {
                    try {
                        gjpsmt3.close();
                    }
                    catch (Exception ex12) {}
                }
                if (idpsmt1 != null) {
                    try {
                        idpsmt1.close();
                    }
                    catch (Exception ex13) {}
                }
                if (idpsmt2 != null) {
                    try {
                        idpsmt2.close();
                    }
                    catch (Exception ex14) {}
                }
                if (idpsmt3 != null) {
                    try {
                        idpsmt3.close();
                    }
                    catch (Exception ex15) {}
                }
                if (jppsmt1 != null) {
                    try {
                        jppsmt1.close();
                    }
                    catch (Exception ex16) {}
                }
                if (jppsmt2 != null) {
                    try {
                        jppsmt2.close();
                    }
                    catch (Exception ex17) {}
                }
                if (jppsmt3 != null) {
                    try {
                        jppsmt3.close();
                    }
                    catch (Exception ex18) {}
                }
                if (trx != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex19) {}
                }
            }
        }
        if (psmt1 != null) {
            try {
                psmt1.close();
            }
            catch (Exception ex20) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex21) {}
        }
        if (psmt3 != null) {
            try {
                psmt3.close();
            }
            catch (Exception ex22) {}
        }
        if (dppsmt1 != null) {
            try {
                dppsmt1.close();
            }
            catch (Exception ex23) {}
        }
        if (dppsmt2 != null) {
            try {
                dppsmt2.close();
            }
            catch (Exception ex24) {}
        }
        if (dppsmt3 != null) {
            try {
                dppsmt3.close();
            }
            catch (Exception ex25) {}
        }
        if (gmpsmt1 != null) {
            try {
                gmpsmt1.close();
            }
            catch (Exception ex26) {}
        }
        if (gmpsmt2 != null) {
            try {
                gmpsmt2.close();
            }
            catch (Exception ex27) {}
        }
        if (gmpsmt3 != null) {
            try {
                gmpsmt3.close();
            }
            catch (Exception ex28) {}
        }
        if (gjpsmt1 != null) {
            try {
                gjpsmt1.close();
            }
            catch (Exception ex29) {}
        }
        if (gjpsmt2 != null) {
            try {
                gjpsmt2.close();
            }
            catch (Exception ex30) {}
        }
        if (gjpsmt3 != null) {
            try {
                gjpsmt3.close();
            }
            catch (Exception ex31) {}
        }
        if (idpsmt1 != null) {
            try {
                idpsmt1.close();
            }
            catch (Exception ex32) {}
        }
        if (idpsmt2 != null) {
            try {
                idpsmt2.close();
            }
            catch (Exception ex33) {}
        }
        if (idpsmt3 != null) {
            try {
                idpsmt3.close();
            }
            catch (Exception ex34) {}
        }
        if (jppsmt1 != null) {
            try {
                jppsmt1.close();
            }
            catch (Exception ex35) {}
        }
        if (jppsmt2 != null) {
            try {
                jppsmt2.close();
            }
            catch (Exception ex36) {}
        }
        if (jppsmt3 != null) {
            try {
                jppsmt3.close();
            }
            catch (Exception ex37) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex38) {}
        }
    }
    
    public String select_EDI_No(final String saupNo, final Connection con) {
        String ls_query = null;
        String ls_upcha1 = "NO";
        String ls_upcha2 = "";
        String ls_upcha3 = "";
        String ls_upcha4 = "";
        String ls_upcha5 = "";
        String ls_yn = "";
        ResultSet rs = null;
        PreparedStatement edi_psmt = null;
        try {
            System.out.println("syn_매핑코드업체 시작(select_EDI_No)-------------");
            ls_query = " Select decode(사업자등록번호, null, ' ', 사업자등록번호),         decode(물품등록업체코드, null, ' ', 물품등록업체코드),         decode(외자등록업체코드, null, ' ', 외자등록업체코드),          decode(공사등록업체코드, null, ' ', 공사등록업체코드),          decode(용역등록업체코드, null, ' ', 용역등록업체코드),          decode(사용여부, null, 'Y', 사용여부)   From syn_매핑코드업체  Where 사업자등록번호 = ? ";
            edi_psmt = con.prepareStatement(ls_query);
            edi_psmt.setString(1, saupNo);
            System.out.println("syn_매핑코드업체==" + ls_query);
            rs = edi_psmt.executeQuery();
            edi_psmt.clearParameters();
            while (rs.next()) {
                ls_upcha1 = rs.getString(1);
                ls_upcha2 = rs.getString(2);
                ls_upcha3 = rs.getString(3);
                ls_upcha4 = rs.getString(4);
                ls_upcha5 = rs.getString(5);
                ls_yn = rs.getString(5);
            }
            rs.close();
            edi_psmt.close();
            System.out.println("syn_매핑코드업체==사업자등록번호==" + ls_upcha1);
            System.out.println("syn_매핑코드업체==물품등록업체코드==" + ls_upcha2);
            System.out.println("syn_매핑코드업체==외자등록업체코드==" + ls_upcha3);
            System.out.println("syn_매핑코드업체==공사등록업체코드==" + ls_upcha4);
            System.out.println("syn_매핑코드업체==용역등록업체코드==" + ls_upcha5);
            System.out.println("syn_매핑코드업체==사용여부==" + ls_yn);
            System.out.println("syn_매핑코드업체 끝======================");
            if (ls_upcha1.equals("NO")) {
                return ls_upcha1;
            }
            return String.valueOf(ls_upcha2) + '|' + ls_upcha3 + '|' + ls_upcha4 + '|' + ls_upcha5 + '|' + ls_yn;
        }
        catch (SQLException exc) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrD020c.doPost block SQLException : Transaction Rollback간에 SQLException 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrD020c.doPost block SQLException : ");
            Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
        }
        catch (Exception exc2) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrD020c.doPost block Exception : Transaction Rollback간에 Exception 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrD020c.doPost block Exception : ");
            Log.debug("Exception발생 사유 : " + exc2.toString());
            exc2.printStackTrace();
            goto Label_0577;
        }
        finally {
            if (edi_psmt != null) {
                try {
                    edi_psmt.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void cont_JP(final String ediNo, final String up_chk, final int jpTotalCount, final String[] goodsNo, final String[] threeSale, final String[] formNo, final Connection con) {
        PreparedStatement ps_JP_I = null;
        PreparedStatement ps_JP_D = null;
        final String ls_JP_I = " INSERT INTO SYN_조달품목 (  등록업체코드, 물품분류번호, 매출액, 형식승인번호, 공장구분, 입력자사번  ) VALUES (  ?, ?, ?, ?, ?, '970251'  ) ";
        final String ls_JP_D = " DELETE FROM SYN_조달품목  WHERE 등록업체코드 = ? AND 공장구분 = ?";
        try {
            System.out.println("조달 EDI 조달품목 시작(cont_JP)-------------");
            if (ediNo == null || ediNo.equals("") || ediNo.equals(" ")) {
                return;
            }
            if (up_chk.equals("U")) {
                ps_JP_D = con.prepareStatement(ls_JP_D);
                ps_JP_D.setString(1, ediNo);
                ps_JP_D.executeUpdate();
                ps_JP_D.clearParameters();
            }
            ps_JP_I = con.prepareStatement(ls_JP_I);
            for (int i = 1; i < jpTotalCount; ++i) {
                ps_JP_I.setString(1, ediNo);
                ps_JP_I.setString(2, goodsNo[i]);
                ps_JP_I.setString(3, ComStr.replace(threeSale[i], ",", ""));
                ps_JP_I.setString(4, formNo[i]);
                ps_JP_I.executeUpdate();
                ps_JP_I.clearParameters();
            }
            System.out.println("조달 EDI 조달품목 종료(cont_JP)-------------");
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
            if (ps_JP_I != null) {
                try {
                    ps_JP_I.close();
                }
                catch (Exception ex) {}
            }
            if (ps_JP_D != null) {
                try {
                    ps_JP_D.close();
                }
                catch (Exception ex2) {}
            }
        }
        if (ps_JP_I != null) {
            try {
                ps_JP_I.close();
            }
            catch (Exception ex3) {}
        }
        if (ps_JP_D != null) {
            try {
                ps_JP_D.close();
            }
            catch (Exception ex4) {}
        }
    }
    
    public void cont_ID(final String ediNo, final String up_chk, final int idTotalCount, final String[] dutyName, final String[] name, final String[] juminNo, final Connection con) {
        PreparedStatement ps_ID_I = null;
        PreparedStatement ps_ID_D = null;
        final String ls_ID_I = " INSERT INTO SYN_입찰대리인 (  등록업체코드, 직책명, 성명, 주민등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, '970251'  ) ";
        final String ls_ID_D = " DELETE FROM SYN_입찰대리인  WHERE 등록업체코드 = ? ";
        try {
            System.out.println("조달 EDI 입찰대리인 시작(cont_ID)-------------");
            if (ediNo == null || ediNo.equals("") || ediNo.equals(" ")) {
                return;
            }
            if (up_chk.equals("U")) {
                ps_ID_D = con.prepareStatement(ls_ID_D);
                ps_ID_D.setString(1, ediNo);
                ps_ID_D.executeUpdate();
                ps_ID_D.clearParameters();
            }
            ps_ID_I = con.prepareStatement(ls_ID_I);
            for (int i = 1; i < idTotalCount; ++i) {
                ps_ID_I.setString(1, ediNo);
                ps_ID_I.setString(2, dutyName[i]);
                ps_ID_I.setString(3, name[i]);
                ps_ID_I.setString(4, ComStr.replace(juminNo[i], "-", ""));
                ps_ID_I.executeUpdate();
                ps_ID_I.clearParameters();
            }
            System.out.println("조달 EDI 입찰대리인 종료(cont_ID)-------------");
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
            if (ps_ID_I != null) {
                try {
                    ps_ID_I.close();
                }
                catch (Exception ex) {}
            }
            if (ps_ID_D != null) {
                try {
                    ps_ID_D.close();
                }
                catch (Exception ex2) {}
            }
        }
        if (ps_ID_I != null) {
            try {
                ps_ID_I.close();
            }
            catch (Exception ex3) {}
        }
        if (ps_ID_D != null) {
            try {
                ps_ID_D.close();
            }
            catch (Exception ex4) {}
        }
    }
    
    public void cont_MS(final String ediNo, final String up_chk, final int gmTotalCount, final String[] gmLicenseCode, final String[] gmLicenseBeginDate, final String[] gmSigongAccount, final String[] gmLicenseEndDate, final Connection con) {
        PreparedStatement ps_MS_I = null;
        PreparedStatement ps_MS_D = null;
        final String ls_MS_I = " INSERT INTO SYN_면허사항 (  등록업체코드, 면허번호, 면허취득일자, 도급한도액, 입력자사번, 입력일자  ) VALUES (  ?, ?, ?, ?, '970251', SYSDATE  ) ";
        final String ls_MS_D = " DELETE FROM SYN_면허사항  WHERE 등록업체코드 = ? ";
        try {
            System.out.println("조달 EDI 면허 시작(cont_MS)-------------");
            if (ediNo == null || ediNo.equals("") || ediNo.equals(" ")) {
                return;
            }
            if (up_chk.equals("U")) {
                ps_MS_D = con.prepareStatement(ls_MS_D);
                ps_MS_D.setString(1, ediNo);
                ps_MS_D.executeUpdate();
                ps_MS_D.clearParameters();
            }
            ps_MS_I = con.prepareStatement(ls_MS_I);
            for (int i = 1; i < gmTotalCount; ++i) {
                ps_MS_I.setString(1, ediNo);
                ps_MS_I.setString(2, gmLicenseCode[i]);
                ps_MS_I.setString(3, gmLicenseBeginDate[i]);
                ps_MS_I.setLong(4, Long.parseLong(ComStr.replace(gmSigongAccount[i], ",", "")));
                ps_MS_I.setString(5, gmLicenseEndDate[i]);
                ps_MS_I.executeUpdate();
                ps_MS_I.clearParameters();
            }
            System.out.println("조달 EDI 면허 종료(cont_MS)-------------");
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
            if (ps_MS_I != null) {
                try {
                    ps_MS_I.close();
                }
                catch (Exception ex) {}
            }
            if (ps_MS_D != null) {
                try {
                    ps_MS_D.close();
                }
                catch (Exception ex2) {}
            }
        }
        if (ps_MS_I != null) {
            try {
                ps_MS_I.close();
            }
            catch (Exception ex3) {}
        }
        if (ps_MS_D != null) {
            try {
                ps_MS_D.close();
            }
            catch (Exception ex4) {}
        }
    }
    
    public void cont_MainNo_I(final String saupNo, final String ediNo, final String giup_chk, final String up_chk, final String sangho, final String DaeCeo1, final String DaeNo1, final String localCode, final String zipCode, final String restAddr, final String addr, final String tel, final String fax, final String openDate, final String bubinOpenDate, final String comGubun1, final String registOkDate, final String nationality, final String jabon, final String employeeNo, final String homepage, final String DaeMail, final String bubinNo, final String gj_ZipCode, final String gj_RestAddr, final String gj_Addr, final String gj_Tel, final String gj_Fax, final String eSangho, final String DaeLiCode, final Connection con) {
        final Trx trx = null;
        PreparedStatement ps_Main_I = null;
        PreparedStatement ps_Main_U = null;
        PreparedStatement ps_Main_D = null;
        PreparedStatement ps_Naeja_I = null;
        PreparedStatement ps_Naeja_U = null;
        PreparedStatement ps_For_I = null;
        PreparedStatement ps_For_U = null;
        PreparedStatement ps_Sisl_I = null;
        PreparedStatement ps_Sisl_U = null;
        PreparedStatement ps_MaNae_U = null;
        PreparedStatement ps_MaFor_U = null;
        PreparedStatement ps_MaGo_U = null;
        PreparedStatement ps_MaYo_U = null;
        final String ls_Main_I = " INSERT INTO SYN_등록업체 (  사업구분, 등록업체코드, 상호명, 사업자등록번호,  등록일자, 대표자명, 대표자주민번호, 본사지역코드, 본사우편번호,  본사통반번지, 본사주소, 본사전화번호, FAX번호, 설립일자,  법인구분, 법인설립일자, 업체구분, 등록유효일자, 수정일자,  입력일자, 국가코드, 자본금, 종업원수, 홈페이지,  대표자메일주소, 입력자사번  ) VALUES (  ?, ?, ?, ?,  SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ADD_MONTHS(SYSDATE, 24), SYSDATE,  SYSDATE, ?, ?, ?, ?,  ?, '970251'  ) ";
        final String ls_Main_U = " UPDATE SYN_등록업체 SET  상호명 = ?, 사업자등록번호 = ?,  대표자명 = ?, 대표자주민번호 = ?, 본사지역코드 = ?, 본사우편번호 = ?,  본사통반번지 = ?, 본사주소 = ?, 본사전화번호 = ?, FAX번호 = ?, 설립일자 = ?,  법인구분 = ?, 법인설립일자 = ?, 업체구분 = ?, 등록유효일자 = ADD_MONTHS(SYSDATE, 24), 수정일자 = SYSDATE,  국가코드 = ?, 자본금 = ?, 종업원수 = ?, 홈페이지 = ?,  대표자메일주소 = ?  WHERE 등록업체코드 = ? ";
        final String ls_Main_D = " UPDATE SYN_등록업체  SET 수정일자 = SYSDATE, \t\t취소구분 = '07', \t\t취소일자 = SYSDATE  WHERE 등록업체코드 = ? ";
        final String ls_Naeja_I = " INSERT INTO SYN_내자업체 (  등록업체코드, 지역코드, 우편번호, 통반_번지, 주소,  전화번호, FAX번호, 입력일자, 법인등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, SYSDATE, ?, '970251'  ) ";
        final String ls_Naeja_U = " UPDATE SYN_내자업체 SET  지역코드 = ?, 우편번호 = ?, 통반_번지 = ?, 주소 = ?,  전화번호 = ?, FAX번호 = ?, 입력일자 = SYSDATE, 법인등록번호 = ?  WHERE 등록업체코드 = ? ";
        final String ls_For_I = " INSERT INTO SYN_외자상사업체 (  등록업체코드, 영문상호명, 국가코드, 법인등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, '970251'  ) ";
        final String ls_For_U = " UPDATE SYN_외자상사업체 SET  영문상호명 = ?, 국가코드 = ?, 법인등록번호 = ?  WHERE 등록업체코드 = ? ";
        final String ls_Sisl_I = " INSERT INTO SYN_시설업체 (  등록업체코드, 자본금, 종업원수, 대표면허번호, 법인등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, ?, '970251'  ) ";
        final String ls_Sisl_U = " UPDATE SYN_시설업체 SET  자본금 = ?, 종업원수 = ?, 대표면허번호 = ?, 법인등록번호 = ?  WHERE 등록업체코드 = ? ";
        final String ls_MaNae_U = " UPDATE SYN_매핑코드업체 SET  물품등록업체코드 = ?  WHERE 사업자등록번호 = ? ";
        final String ls_MaFor_U = " UPDATE SYN_매핑코드업체 SET  외자등록업체코드 = ?  WHERE 사업자등록번호 = ? ";
        final String ls_MaGo_U = " UPDATE SYN_매핑코드업체 SET  공사등록업체코드 = ?  WHERE 사업자등록번호 = ? ";
        final String ls_MaYo_U = " UPDATE SYN_매핑코드업체 SET  용역등록업체코드 = ?  WHERE 사업자등록번호 = ? ";
        String ls_bubchk = "";
        System.out.println("조달 EDI 등록업체 관리(cont_MainNo_I) 시작-------------------------------------------");
        try {
            if (ediNo == null || ediNo.equals("") || ediNo.equals(" ")) {
                return;
            }
            if (bubinNo != null) {
                ls_bubchk = "1";
            }
            else {
                ls_bubchk = "0";
            }
            if (up_chk.equals("I")) {
                System.out.println("등록업체 등록--" + ls_Main_I);
                ps_Main_I = con.prepareStatement(ls_Main_I);
                ps_Main_I.setString(1, giup_chk);
                ps_Main_I.setString(2, ediNo);
                ps_Main_I.setString(3, sangho);
                ps_Main_I.setString(4, saupNo);
                ps_Main_I.setString(5, DaeCeo1);
                ps_Main_I.setString(6, DaeNo1);
                ps_Main_I.setString(7, localCode);
                ps_Main_I.setString(8, zipCode);
                ps_Main_I.setString(9, restAddr);
                ps_Main_I.setString(10, addr);
                ps_Main_I.setString(11, tel);
                ps_Main_I.setString(12, fax);
                ps_Main_I.setString(13, openDate);
                ps_Main_I.setString(14, ls_bubchk);
                ps_Main_I.setString(15, bubinOpenDate);
                ps_Main_I.setString(16, comGubun1);
                ps_Main_I.setString(17, nationality);
                ps_Main_I.setLong(18, Long.parseLong(ComStr.replace(jabon, ",", "")));
                ps_Main_I.setInt(19, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                ps_Main_I.setString(20, homepage);
                ps_Main_I.setString(21, DaeMail);
                ps_Main_I.executeUpdate();
                ps_Main_I.clearParameters();
                System.out.println("등록업체 등록--끝");
            }
            if (up_chk.equals("U")) {
                System.out.println("등록업체 수정--" + ls_Main_U);
                ps_Main_U = con.prepareStatement(ls_Main_U);
                System.out.println("sangho--" + sangho);
                System.out.println("saupNo--" + saupNo);
                System.out.println("DaeCeo1--" + DaeCeo1);
                System.out.println("DaeNo1--" + DaeNo1);
                System.out.println("localCode--" + localCode);
                System.out.println("zipCode--" + zipCode);
                System.out.println("restAddr--" + restAddr);
                System.out.println("addr--" + addr);
                System.out.println("tel--" + tel);
                System.out.println("fax--" + fax);
                System.out.println("openDate--" + openDate);
                System.out.println("ls_bubchk--" + ls_bubchk);
                System.out.println("bubinOpenDate--" + bubinOpenDate);
                System.out.println("comGubun1--" + comGubun1);
                System.out.println("jabon--" + jabon);
                System.out.println("employeeNo--" + employeeNo);
                System.out.println("homepage--" + homepage);
                System.out.println("DaeMail--" + DaeMail);
                System.out.println("ediNo--" + ediNo);
                ps_Main_U.setString(1, sangho);
                ps_Main_U.setString(2, saupNo);
                ps_Main_U.setString(3, DaeCeo1);
                ps_Main_U.setString(4, ComStr.replace(DaeNo1, "-", ""));
                ps_Main_U.setString(5, localCode);
                ps_Main_U.setString(6, zipCode);
                ps_Main_U.setString(7, restAddr);
                ps_Main_U.setString(8, addr);
                ps_Main_U.setString(9, tel);
                ps_Main_U.setString(10, fax);
                ps_Main_U.setString(11, openDate);
                ps_Main_U.setString(12, ls_bubchk);
                ps_Main_U.setString(13, bubinOpenDate);
                ps_Main_U.setString(14, comGubun1);
                System.out.println("자본금--" + jabon);
                System.out.println("자본금--" + Long.parseLong(ComStr.replace(jabon, ",", "")));
                System.out.println("종업수수--" + employeeNo);
                ps_Main_U.setString(15, nationality);
                ps_Main_U.setLong(16, Long.parseLong(ComStr.replace(jabon, ",", "")));
                ps_Main_U.setInt(17, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                ps_Main_U.setString(18, homepage);
                ps_Main_U.setString(19, DaeMail);
                ps_Main_U.setString(20, ediNo);
                ps_Main_U.executeUpdate();
                ps_Main_U.clearParameters();
                System.out.println("등록업체 수정--끝");
            }
            if (up_chk.equals("D")) {
                System.out.println("등록업체 삭제--" + ls_Main_D);
                ps_Main_D = con.prepareStatement(ls_Main_D);
                ps_Main_D.setString(1, ediNo);
                ps_Main_D.executeUpdate();
                ps_Main_D.clearParameters();
                System.out.println("등록업체 삭제--끝");
            }
            if (up_chk.equals("I") && giup_chk.equals("1")) {
                System.out.println("내자업체 등록--" + ls_Naeja_I);
                ps_Naeja_I = con.prepareStatement(ls_Naeja_I);
                ps_Naeja_I.setString(1, ediNo);
                ps_Naeja_I.setString(2, localCode);
                ps_Naeja_I.setString(3, gj_ZipCode);
                ps_Naeja_I.setString(4, gj_RestAddr);
                ps_Naeja_I.setString(5, gj_Addr);
                ps_Naeja_I.setString(6, gj_Tel);
                ps_Naeja_I.setString(7, gj_Fax);
                ps_Naeja_I.setString(8, bubinNo);
                ps_Naeja_I.executeUpdate();
                ps_Naeja_I.clearParameters();
                System.out.println("내자업체 등록--끝");
            }
            if (up_chk.equals("U") && giup_chk.equals("1")) {
                System.out.println("내자업체 수정--" + ls_Naeja_U);
                ps_Naeja_U = con.prepareStatement(ls_Naeja_U);
                ps_Naeja_U.setString(1, localCode);
                ps_Naeja_U.setString(2, gj_ZipCode);
                ps_Naeja_U.setString(3, gj_RestAddr);
                ps_Naeja_U.setString(4, gj_Addr);
                ps_Naeja_U.setString(5, gj_Tel);
                ps_Naeja_U.setString(6, gj_Fax);
                ps_Naeja_U.setString(7, bubinNo);
                ps_Naeja_U.setString(8, ediNo);
                ps_Naeja_U.executeUpdate();
                ps_Naeja_U.clearParameters();
                System.out.println("내자업체 수정--끝");
            }
            if (up_chk.equals("I") && giup_chk.equals("2")) {
                System.out.println("외자업체 등록 --" + ls_For_I);
                ps_For_I = con.prepareStatement(ls_For_I);
                ps_For_I.setString(1, ediNo);
                ps_For_I.setString(2, eSangho);
                ps_For_I.setString(3, nationality);
                ps_For_I.setString(4, bubinNo);
                ps_For_I.executeUpdate();
                ps_For_I.clearParameters();
                System.out.println("외자업체 등록 --끝");
            }
            if (up_chk.equals("U") && giup_chk.equals("2")) {
                System.out.println("외자업체 수정 --" + ls_For_U);
                ps_For_U = con.prepareStatement(ls_For_U);
                ps_For_U.setString(1, eSangho);
                ps_For_U.setString(2, nationality);
                ps_For_U.setString(3, bubinNo);
                ps_For_U.setString(4, ediNo);
                ps_For_U.executeUpdate();
                ps_For_U.clearParameters();
                System.out.println("외자업체 수정 --끝");
            }
            if (up_chk.equals("I") && giup_chk.equals("3")) {
                System.out.println("시설업체 등록 --" + ls_Sisl_I);
                ps_Sisl_I = con.prepareStatement(ls_Sisl_I);
                ps_Sisl_I.setString(1, ediNo);
                ps_Sisl_I.setLong(2, Long.parseLong(ComStr.replace(jabon, ",", "")));
                ps_Sisl_I.setInt(3, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                ps_Sisl_I.setString(4, DaeLiCode);
                ps_Sisl_I.setString(5, bubinNo);
                ps_Sisl_I.executeUpdate();
                ps_Sisl_I.clearParameters();
                System.out.println("시설업체 등록 --끝");
            }
            if (up_chk.equals("U") && giup_chk.equals("3")) {
                System.out.println("시설업체 수정 --" + ls_Sisl_U);
                ps_Sisl_U = con.prepareStatement(ls_Sisl_U);
                ps_Sisl_U.setLong(1, Long.parseLong(ComStr.replace(jabon, ",", "")));
                ps_Sisl_U.setInt(2, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                ps_Sisl_U.setString(3, DaeLiCode);
                ps_Sisl_U.setString(4, bubinNo);
                ps_Sisl_U.setString(5, ediNo);
                ps_Sisl_U.executeUpdate();
                ps_Sisl_U.clearParameters();
                System.out.println("시설업체 수정 --끝");
            }
            if (up_chk.equals("I") && giup_chk.equals("1")) {
                System.out.println("매핑코드업체 (내자업체 등록) --" + ls_MaNae_U);
                ps_MaNae_U = con.prepareStatement(ls_MaNae_U);
                ps_MaNae_U.setString(1, ediNo);
                ps_MaNae_U.setString(2, saupNo);
                ps_MaNae_U.executeUpdate();
                ps_MaNae_U.clearParameters();
                System.out.println("매핑코드업체 (내자업체 등록) --끝");
            }
            if (up_chk.equals("D") && giup_chk.equals("1")) {
                System.out.println("매핑코드업체 (내자업체 삭제) --" + ls_MaNae_U);
                ps_MaNae_U = con.prepareStatement(ls_MaNae_U);
                ps_MaNae_U.setString(1, null);
                ps_MaNae_U.setString(2, saupNo);
                ps_MaNae_U.executeUpdate();
                ps_MaNae_U.clearParameters();
                System.out.println("매핑코드업체 (내자업체 삭제) --끝");
            }
            if (up_chk.equals("I") && giup_chk.equals("2")) {
                System.out.println("매핑코드업체 (외자업체 등록) --" + ls_MaFor_U);
                ps_MaFor_U = con.prepareStatement(ls_MaFor_U);
                ps_MaFor_U.setString(1, ediNo);
                ps_MaFor_U.setString(2, saupNo);
                ps_MaFor_U.executeUpdate();
                ps_MaFor_U.clearParameters();
                System.out.println("매핑코드업체 (외자업체 등록) --끝");
            }
            if (up_chk.equals("D") && giup_chk.equals("2")) {
                System.out.println("매핑코드업체 (외자업체 삭제) --" + ls_MaFor_U);
                ps_MaFor_U = con.prepareStatement(ls_MaFor_U);
                ps_MaFor_U.setString(1, null);
                ps_MaFor_U.setString(2, saupNo);
                ps_MaFor_U.executeUpdate();
                ps_MaFor_U.clearParameters();
                System.out.println("매핑코드업체 (외자업체 삭제) --끝");
            }
            if (up_chk.equals("I") && giup_chk.equals("3") && (ediNo == null || ediNo.substring(0, 2).equals("35"))) {
                System.out.println("매핑코드업체 (공사업체 등록) --" + ls_MaGo_U);
                ps_MaGo_U = con.prepareStatement(ls_MaGo_U);
                ps_MaGo_U.setString(1, ediNo);
                ps_MaGo_U.setString(2, saupNo);
                ps_MaGo_U.executeUpdate();
                ps_MaGo_U.clearParameters();
                System.out.println("매핑코드업체 (공사업체 등록) --끝");
            }
            if (up_chk.equals("D") && giup_chk.equals("3") && (ediNo == null || ediNo.substring(0, 2).equals("35"))) {
                System.out.println("매핑코드업체 (공사업체 삭제) --" + ls_MaGo_U);
                ps_MaGo_U = con.prepareStatement(ls_MaGo_U);
                ps_MaGo_U.setString(1, null);
                ps_MaGo_U.setString(2, saupNo);
                ps_MaGo_U.executeUpdate();
                ps_MaGo_U.clearParameters();
                System.out.println("매핑코드업체 (공사업체 삭제) --끝");
            }
            if (up_chk.equals("I") && giup_chk.equals("3") && (ediNo == null || !ediNo.substring(0, 2).equals("35"))) {
                System.out.println("매핑코드업체 (용역업체 등록) --" + ls_MaGo_U);
                ps_MaGo_U = con.prepareStatement(ls_MaGo_U);
                ps_MaGo_U.setString(1, ediNo);
                ps_MaGo_U.setString(2, saupNo);
                ps_MaGo_U.executeUpdate();
                ps_MaGo_U.clearParameters();
                System.out.println("매핑코드업체 (용역업체 등록) --끝");
            }
            if (up_chk.equals("D") && giup_chk.equals("3") && (ediNo == null || !ediNo.substring(0, 2).equals("35"))) {
                System.out.println("매핑코드업체 (용역업체 삭제) --" + ls_MaYo_U);
                ps_MaYo_U = con.prepareStatement(ls_MaYo_U);
                ps_MaYo_U.setString(1, null);
                ps_MaYo_U.setString(2, saupNo);
                ps_MaYo_U.executeUpdate();
                ps_MaYo_U.clearParameters();
                System.out.println("매핑코드업체 (용역업체 삭제) --끝");
            }
            if (ps_Main_I != null) {
                try {
                    ps_Main_I.close();
                }
                catch (Exception ex) {}
            }
            if (ps_Main_U != null) {
                try {
                    ps_Main_U.close();
                }
                catch (Exception ex2) {}
            }
            if (ps_Main_D != null) {
                try {
                    ps_Main_D.close();
                }
                catch (Exception ex3) {}
            }
            if (ps_Naeja_I != null) {
                try {
                    ps_Naeja_I.close();
                }
                catch (Exception ex4) {}
            }
            if (ps_Naeja_U != null) {
                try {
                    ps_Naeja_U.close();
                }
                catch (Exception ex5) {}
            }
            if (ps_For_I != null) {
                try {
                    ps_For_I.close();
                }
                catch (Exception ex6) {}
            }
            if (ps_For_U != null) {
                try {
                    ps_For_U.close();
                }
                catch (Exception ex7) {}
            }
            if (ps_Sisl_I != null) {
                try {
                    ps_Sisl_I.close();
                }
                catch (Exception ex8) {}
            }
            if (ps_Sisl_U != null) {
                try {
                    ps_Sisl_U.close();
                }
                catch (Exception ex9) {}
            }
            if (ps_MaNae_U != null) {
                try {
                    ps_MaNae_U.close();
                }
                catch (Exception ex10) {}
            }
            if (ps_MaFor_U != null) {
                try {
                    ps_MaFor_U.close();
                }
                catch (Exception ex11) {}
            }
            if (ps_MaGo_U != null) {
                try {
                    ps_MaGo_U.close();
                }
                catch (Exception ex12) {}
            }
            if (ps_MaYo_U != null) {
                try {
                    ps_MaYo_U.close();
                }
                catch (Exception ex13) {}
            }
            System.out.println("조달 EDI 등록업체 관리(cont_MainNo_I) 종료-------------------------------------------끝");
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
            if (ps_Main_I != null) {
                try {
                    ps_Main_I.close();
                }
                catch (Exception ex14) {}
            }
            if (ps_Main_U != null) {
                try {
                    ps_Main_U.close();
                }
                catch (Exception ex15) {}
            }
            if (ps_Main_D != null) {
                try {
                    ps_Main_D.close();
                }
                catch (Exception ex16) {}
            }
            if (ps_Naeja_I != null) {
                try {
                    ps_Naeja_I.close();
                }
                catch (Exception ex17) {}
            }
            if (ps_Naeja_U != null) {
                try {
                    ps_Naeja_U.close();
                }
                catch (Exception ex18) {}
            }
            if (ps_For_I != null) {
                try {
                    ps_For_I.close();
                }
                catch (Exception ex19) {}
            }
            if (ps_For_U != null) {
                try {
                    ps_For_U.close();
                }
                catch (Exception ex20) {}
            }
            if (ps_Sisl_I != null) {
                try {
                    ps_Sisl_I.close();
                }
                catch (Exception ex21) {}
            }
            if (ps_Sisl_U != null) {
                try {
                    ps_Sisl_U.close();
                }
                catch (Exception ex22) {}
            }
            if (ps_MaNae_U != null) {
                try {
                    ps_MaNae_U.close();
                }
                catch (Exception ex23) {}
            }
            if (ps_MaFor_U != null) {
                try {
                    ps_MaFor_U.close();
                }
                catch (Exception ex24) {}
            }
            if (ps_MaGo_U != null) {
                try {
                    ps_MaGo_U.close();
                }
                catch (Exception ex25) {}
            }
            if (ps_MaYo_U != null) {
                try {
                    ps_MaYo_U.close();
                }
                catch (Exception ex26) {}
            }
        }
        if (ps_Main_I != null) {
            try {
                ps_Main_I.close();
            }
            catch (Exception ex27) {}
        }
        if (ps_Main_U != null) {
            try {
                ps_Main_U.close();
            }
            catch (Exception ex28) {}
        }
        if (ps_Main_D != null) {
            try {
                ps_Main_D.close();
            }
            catch (Exception ex29) {}
        }
        if (ps_Naeja_I != null) {
            try {
                ps_Naeja_I.close();
            }
            catch (Exception ex30) {}
        }
        if (ps_Naeja_U != null) {
            try {
                ps_Naeja_U.close();
            }
            catch (Exception ex31) {}
        }
        if (ps_For_I != null) {
            try {
                ps_For_I.close();
            }
            catch (Exception ex32) {}
        }
        if (ps_For_U != null) {
            try {
                ps_For_U.close();
            }
            catch (Exception ex33) {}
        }
        if (ps_Sisl_I != null) {
            try {
                ps_Sisl_I.close();
            }
            catch (Exception ex34) {}
        }
        if (ps_Sisl_U != null) {
            try {
                ps_Sisl_U.close();
            }
            catch (Exception ex35) {}
        }
        if (ps_MaNae_U != null) {
            try {
                ps_MaNae_U.close();
            }
            catch (Exception ex36) {}
        }
        if (ps_MaFor_U != null) {
            try {
                ps_MaFor_U.close();
            }
            catch (Exception ex37) {}
        }
        if (ps_MaGo_U != null) {
            try {
                ps_MaGo_U.close();
            }
            catch (Exception ex38) {}
        }
        if (ps_MaYo_U != null) {
            try {
                ps_MaYo_U.close();
            }
            catch (Exception ex39) {}
        }
    }
    
    public void icInsert(final String saupNo, final int ilno, final String tabGubun, final String gubun, final String beComment, final String afComment, final String item, final String procID, final Connection con) {
        String icquery = null;
        PreparedStatement icpsmt = null;
        try {
            System.out.println("사용_입찰자격사항이력정보 시작");
            icquery = " INSERT INTO 사용_입찰자격사항이력 (  사업자등록번호, 갱신일자, 일련번호, 테이블구분, 변경구분, 변경전내용, 변경후내용, 속성명, 처리자ID  ) VALUES (  ?, sysdate, ?, ?, ?, ?, ?, ?, ?  ) ";
            icpsmt = con.prepareStatement(icquery);
            icpsmt.setString(1, saupNo);
            icpsmt.setInt(2, ilno);
            icpsmt.setString(3, tabGubun);
            icpsmt.setString(4, gubun);
            icpsmt.setString(5, beComment);
            icpsmt.setString(6, afComment);
            icpsmt.setString(7, item);
            icpsmt.setString(8, procID);
            System.out.println("사용_입찰자격사항이력정보 Up");
            icpsmt.executeUpdate();
            icpsmt.clearParameters();
            System.out.println("사용_입찰자격사항이력정보 완료");
        }
        catch (SQLException exc) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrD020c.doPost block SQLException : Transaction Rollback간에 SQLException 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrD020c.doPost block SQLException : ");
            Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
        }
        catch (Exception exc2) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrD020c.doPost block Exception : Transaction Rollback간에 Exception 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrD020c.doPost block Exception : ");
            Log.debug("Exception발생 사유 : " + exc2.toString());
            exc2.printStackTrace();
        }
        finally {
            if (icpsmt != null) {
                try {
                    icpsmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (icpsmt != null) {
            try {
                icpsmt.close();
            }
            catch (Exception ex2) {}
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
            query4 = " SELECT  사업자등록번호, 국적, 상호명, 영문상호명,  TO_CHAR(개업일자, 'YYYY-MM-DD'), TO_CHAR(법인설립일자, 'YYYY-MM-DD'), 업무구분, 제조구분, 대표업종코드_표준,  법인등록번호, 기업구분1, 기업구분2, 기업구분해당년도, 자본금,  종업원수, 최근결산년월, 우편번호, 지역코드, 주소,  나머지주소, 전화번호, FAX번호, 홈페이지, 특례해당여부,  등록유효일자, 등록일자, 갱신일자, 대표인증여부, 처리자ID  FROM 사용_조달업체마스터  WHERE 사업자등록번호 = '" + saupNo + "' ";
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
                ett.setDpUpjongCode(rs.getString(9));
                ett.setBubinNo(rs.getString(10));
                ett.setComGubun1(rs.getString(11));
                ett.setComGubun2(rs.getString(12));
                ett.setComGubunYear(rs.getString(13));
                ett.setJabon(rs.getLong(14));
                ett.setEmployeeNo(rs.getInt(15));
                ett.setAccountDate(rs.getString(16));
                ett.setZipCode(rs.getString(17));
                ett.setLocalCode(rs.getString(18));
                ett.setAddr(rs.getString(19));
                ett.setRestAddr(rs.getString(20));
                ett.setTel(rs.getString(21));
                ett.setFax(rs.getString(22));
                ett.setHomepage(rs.getString(23));
                ett.setExceptYN(rs.getString(24));
                ett.setRegistOkDate(rs.getString(25));
                ett.setRegistDate(rs.getString(26));
                ett.setRenewDate(rs.getString(27));
                ett.setDpOkYN(rs.getString(28));
                ett.setChurijaId(rs.getString(29));
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
                query = " SELECT  대표자명,  대표자주민번호,  대표자메일주소,  대표대표자여부 " + this.getQueryCondition();
                break;
            }
            case 1100: {
                query = " SELECT  대표자명,  대표자주민번호,  대표자메일주소,  대표대표자여부 " + this.getQueryCondition();
                break;
            }
            case 200: {
                query = " SELECT  A.면허코드,  B.코드명,  A.면허번호,  TO_CHAR(A.면허취득일자, 'YYYY-MM-DD'),  TO_CHAR(A.면허만료일자, 'YYYY-MM-DD'),  A.시공능력평가액,  A.평가액기준년도,  A.대표면허여부 " + this.getQueryCondition();
                break;
            }
            case 1200: {
                query = " SELECT  A.면허코드,  B.코드명,  A.면허번호,  TO_CHAR(A.면허취득일자, 'YYYY-MM-DD'),  TO_CHAR(A.면허만료일자, 'YYYY-MM-DD'),  A.시공능력평가액,  A.평가액기준년도,  A.대표면허여부 " + this.getQueryCondition();
                break;
            }
            case 400: {
                query = " SELECT  공장명,  공장우편번호,  공장주소,  공장나머지주소,  공장전화번호,  공장FAX번호 " + this.getQueryCondition();
                break;
            }
            case 1400: {
                query = " SELECT  공장명,  공장우편번호,  공장주소,  공장나머지주소,  공장전화번호,  공장FAX번호 " + this.getQueryCondition();
                break;
            }
            case 500: {
                query = " SELECT  주민등록번호,  성명,  부서,  직책명,  전화번호,  E_MAIL,  휴대폰,  FAX " + this.getQueryCondition();
                break;
            }
            case 1500: {
                query = " SELECT  주민등록번호,  성명,  부서,  직책명,  전화번호,  E_MAIL,  휴대폰,  FAX " + this.getQueryCondition();
                break;
            }
            case 600: {
                query = " SELECT  물품분류번호,  형식승인번호,  최근3년간_매출액,  제조여부,  대표물품여부 " + this.getQueryCondition();
                break;
            }
            case 1600: {
                query = " SELECT  물품분류번호,  형식승인번호,  최근3년간_매출액,  제조여부,  대표물품여부 " + this.getQueryCondition();
                break;
            }
            case 700: {
                query = " SELECT  물품분류번호,  형식승인번호,  최근3년간_매출액,  제조여부,  대표물품여부 " + this.getQueryCondition();
                break;
            }
            case 1700: {
                query = " SELECT  물품분류번호,  형식승인번호,  최근3년간_매출액,  제조여부,  대표물품여부 " + this.getQueryCondition();
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
            case 600: {
                queryCondition = " FROM 사용_조달품목  WHERE 사업자등록번호 = ?    AND 제조여부 = 'Y' ";
                break;
            }
            case 1600: {
                queryCondition = " FROM 사용_접수조달품목  WHERE 사업자등록번호 = ?    AND 제조여부 = 'Y' ";
                break;
            }
            case 700: {
                queryCondition = " FROM 사용_조달품목  WHERE 사업자등록번호 = ?    AND 제조여부 = 'N' ";
                break;
            }
            case 1700: {
                queryCondition = " FROM 사용_접수조달품목  WHERE 사업자등록번호 = ?    AND 제조여부 = 'N' ";
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
}
