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
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_ConrD010c extends HttpServlet
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
        System.out.println("0");
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
        final UM_ADV_ConrD010c entity = new UM_ADV_ConrD010c();
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
        String forYN = "";
        System.out.println("3");
        Label_15406: {
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
                forYN = ((req.getParameter("forYN") == null) ? "" : req.getParameter("forYN"));
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
                final String[] formFac = this.getParams(req, "formFac", "");
                final String[] formDate = this.getParams(req, "formDate", "");
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
                    System.out.println("GPbGPbGPbGPb==" + ggoodsNo[i2] + "===" + ggoodsNo[i2]);
                }
                String upmu = "";
                String upmu2 = "0";
                String upmu3 = "0";
                String upmu4 = "0";
                String upmu5 = "0";
                for (int i3 = 1; i3 < gmTotalCount; ++i3) {
                    if (gmDpLicenseYN[i3].equals("Y") && !gmStatus[i3].equals("D") && !gmStatus[i3].equals("N")) {
                        DaeLice = gmDpLicenseYN[i3];
                    }
                    query1 = " SELECT ?????????2 FROM SYN_????????????  WHERE ???????????? = 'GU9' AND ?????? = ? ";
                    System.out.println("query1 => " + query1);
                    psmt1 = con.prepareStatement(query1);
                    psmt1.setString(1, gmLicenseCode[i3].trim());
                    rs = psmt1.executeQuery();
                    String licenseCode = "";
                    if (rs.next()) {
                        licenseCode = rs.getString(1);
                    }
                    if (licenseCode.equals("??????") || licenseCode.equals("5")) {
                        upmu3 = "1";
                    }
                    if (licenseCode.equals("??????") || licenseCode.equals("5")) {
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
                System.out.println("12");
                System.out.println("====================???????????????????????????=====================");
                String DaeCeo2 = "";
                String DaeCeo3 = "";
                String DaeNo1 = "";
                String DaeNo2 = "";
                String DaeMail = "";
                dpquery1 = " INSERT INTO ??????_????????? (  ?????????????????????, ?????????????????????, ????????????, ?????????????????????, ?????????????????????  ) VALUES (  ?, ?, ?, ?, ?  ) ";
                dppsmt1 = con.prepareStatement(dpquery1);
                dpquery2 = " UPDATE ??????_?????????  SET ????????????????????? = ?,     ????????????????????? = ?,     ???????????? = ?,     ????????????????????? = ?,     ????????????????????? = ?  WHERE ????????????????????? = ?    AND ????????????????????? = ? ";
                dppsmt2 = con.prepareStatement(dpquery2);
                dpquery3 = " DELETE FROM ??????_?????????  WHERE ????????????????????? = ?    AND ????????????????????? = ? ";
                dppsmt3 = con.prepareStatement(dpquery3);
                for (int i4 = 1; i4 < dpTotalCount; ++i4) {
                    if (ceoYN[i4].equals("Y") && !dpStatus[i4].equals("D") && !dpStatus[i4].equals("N")) {
                        DaeCeo = ceoName[i4];
                        DaeCeo2 = ceoName[i4];
                        DaeNo1 = ceoJuminNo[i4];
                    }
                    else {
                        DaeCeo3 = ceoName[i4];
                        DaeNo2 = ceoJuminNo[i4];
                    }
                    if (!ceoMail[i4].equals("")) {
                        DaeMail = ceoMail[i4];
                    }
                    System.out.println("FOR ??????" + dpStatus[i4]);
                    if (dpStatus[i4].equals("I")) {
                        System.out.println("FOR ??????IIIII");
                        dppsmt1.setString(1, saupNo);
                        dppsmt1.setString(2, ComStr.replace(ceoJuminNo[i4], "-", ""));
                        dppsmt1.setString(3, ceoName[i4].trim());
                        dppsmt1.setString(4, ceoMail[i4].trim());
                        dppsmt1.setString(5, ceoYN[i4].trim());
                        dppsmt1.executeUpdate();
                        dppsmt1.clearParameters();
                        this.icInsert(saupNo, ilno, "5", "I", "", ComStr.replace(ceoJuminNo[i4], "-", ""), "?????????????????????", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "5", "I", "", ceoName[i4].trim(), "????????????", procID, con);
                        ++ilno;
                        upCheck = "Y";
                    }
                    else if (dpStatus[i4].equals("F")) {
                        System.out.println("FOR ??????FFFFFF");
                        for (int j2 = 0; j2 < DPb.length; ++j2) {
                            System.out.println("FOR ??????FFF===FOR" + DPb.length + "==" + ceoJuminNo[i4] + "===" + DPb[j2][1]);
                            if (ComStr.replace(ceoJuminNo[i4], "-", "").equals(DPb[j2][1])) {
                                System.out.println("FOR ??????FFF===FOR  ?????????");
                                if (!ceoName[i4].equals(DPb[j2][0]) || !ceoMail[i4].equals(DPb[j2][2]) || !ceoYN[i4].equals(DPb[j2][3])) {
                                    System.out.println("FOR ??????FFF===FOR  ????????????");
                                    dppsmt2.setString(1, saupNo);
                                    dppsmt2.setString(2, ComStr.replace(ceoJuminNo[i4], "-", ""));
                                    dppsmt2.setString(3, ceoName[i4].trim());
                                    dppsmt2.setString(4, ceoMail[i4].trim());
                                    dppsmt2.setString(5, ceoYN[i4].trim());
                                    dppsmt2.setString(6, saupNo);
                                    dppsmt2.setString(7, ComStr.replace(ceoJuminNo[i4], "-", ""));
                                    dppsmt2.executeUpdate();
                                    dppsmt2.clearParameters();
                                    System.out.println("FOR ??????FFF===FOR  ????????????==YYYYYY");
                                    upCheck = "Y";
                                    System.out.println("FOR ??????FFF===FOR  ????????????==YYYYYY" + ceoName[i4] + "===" + DPb[j2][0]);
                                    if (!ceoName[i4].equals(DPb[j2][0])) {
                                        this.icInsert(saupNo, ilno, "5", "U", DPb[j2][0], ceoName[i4].trim(), "????????????", procID, con);
                                        ++ilno;
                                    }
                                    if (ceoYN[i4].equals("Y") && DPb[j2][3].equals("Y")) {
                                        DaeCeo = ceoName[i4];
                                        break;
                                    }
                                    if (ceoYN[i4].equals("Y")) {
                                        DupCheck = "Y";
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if (dpStatus[i4].equals("D")) {
                        System.out.println("FOR ??????DDDDDDD1111====" + dpquery3 + "==" + ceoJuminNo[i4]);
                        dppsmt3.setString(1, saupNo);
                        dppsmt3.setString(2, ComStr.replace(ceoJuminNo[i4], "-", ""));
                        dppsmt3.executeUpdate();
                        System.out.println("FOR ??????DDDDDDD====" + dpquery3);
                        dppsmt3.clearParameters();
                        this.icInsert(saupNo, ilno, "5", "D", ComStr.replace(ceoJuminNo[i4], "-", ""), "", "?????????????????????", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "5", "D", ceoName[i4].trim(), "", "????????????", procID, con);
                        ++ilno;
                    }
                }
                System.out.println("====================?????????????????? ????????????=====================");
                String DaeLiCode = "";
                String DaeLiDate = "";
                gmquery1 = " INSERT INTO ??????_???????????? (  ?????????????????????, ????????????, ????????????, ??????????????????,  ?????????????????????, ?????????????????????, ??????????????????  ) VALUES (  ?, ?, ?, ?,  ?, ?, ?  ) ";
                gmpsmt1 = con.prepareStatement(gmquery1);
                gmquery2 = " UPDATE ??????_????????????  SET ???????????? = ?,     ?????????????????? = ?,     ????????????????????? = ?,     ????????????????????? = ?,     ?????????????????? = ?  WHERE ????????????????????? = ?    AND ???????????? = ? ";
                gmpsmt2 = con.prepareStatement(gmquery2);
                gmquery3 = " DELETE FROM ??????_????????????  WHERE ????????????????????? = ?    AND ???????????? = ? ";
                gmpsmt3 = con.prepareStatement(gmquery3);
                if (gmTotalCount == 1 || gmTotalCount == 0) {
                    DaeLice = "";
                }
                for (int i5 = 1; i5 < gmTotalCount; ++i5) {
                    if (gmDpLicenseYN[i5].equals("Y") && !gmStatus[i5].equals("D") && !gmStatus[i5].equals("N")) {
                        DaeLice = gmLicenseCode[i5];
                        DaeLiCode = gmLicenseCode[i5];
                        DaeLiDate = gmLicenseBeginDate[i5];
                    }
                    System.out.println("FOR ??????" + gmStatus[i5]);
                    if (gmStatus[i5].equals("I")) {
                        System.out.println("FOR ??????IIIII");
                        gmpsmt1.setString(1, saupNo);
                        gmpsmt1.setString(2, gmLicenseCode[i5].trim());
                        gmpsmt1.setString(3, gmLicenseNo[i5].trim());
                        gmpsmt1.setString(4, gmLicenseBeginDate[i5].trim());
                        gmpsmt1.setLong(5, Long.parseLong(ComStr.replace(gmSigongAccount[i5], ",", "")));
                        gmpsmt1.setString(6, gmPeonggaYear[i5].trim());
                        gmpsmt1.setString(7, gmDpLicenseYN[i5].trim());
                        System.out.println("FOR ??????IIIII==" + gmquery1);
                        gmpsmt1.executeUpdate();
                        gmpsmt1.clearParameters();
                        this.icInsert(saupNo, ilno, "4", "I", "", gmLicenseCode[i5].trim(), "????????????", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "4", "I", "", gmLicenseBeginDate[i5].trim(), "??????????????????", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "4", "I", "", ComStr.replace(gmSigongAccount[i5], ",", ""), "?????????????????????", procID, con);
                        ++ilno;
                        upCheck = "Y";
                    }
                    else if (gmStatus[i5].equals("F")) {
                        System.out.println("FOR ??????FFFFFF");
                        for (int j3 = 0; j3 < GMb.length; ++j3) {
                            if (gmLicenseCode[i5].trim().equals(GMb[j3][0])) {
                                System.out.println("FOR ??????FFF===FOR  ?????????");
                                if (!gmLicenseNo[i5].equals(GMb[j3][2]) || !gmLicenseBeginDate[i5].equals(GMb[j3][3]) || !ComStr.replace(gmSigongAccount[i5], ",", "").equals(GMb[j3][4]) || !gmPeonggaYear[i5].equals(GMb[j3][5]) || !gmDpLicenseYN[i5].equals(GMb[j3][6])) {
                                    System.out.println("FOR ??????FFF===FOR  ?????????????????????");
                                    gmpsmt2.setString(1, gmLicenseNo[i5]);
                                    gmpsmt2.setString(2, gmLicenseBeginDate[i5]);
                                    gmpsmt2.setString(3, gmLicenseEndDate[i5]);
                                    gmpsmt2.setLong(4, Long.parseLong(ComStr.replace(gmSigongAccount[i5], ",", "")));
                                    gmpsmt2.setString(5, gmPeonggaYear[i5]);
                                    gmpsmt2.setString(6, gmDpLicenseYN[i5]);
                                    gmpsmt2.setString(7, saupNo);
                                    gmpsmt2.setString(8, gmLicenseCode[i5]);
                                    gmpsmt2.executeUpdate();
                                    System.out.println("FOR ??????FFF===FOR  ????????????==?????????222");
                                    gmpsmt2.clearParameters();
                                    System.out.println("FOR ??????FFF===FOR  ????????????==?????????333");
                                    System.out.println("FOR ??????FFF===FOR  ????????????==YYYYYY");
                                    upCheck = "Y";
                                    System.out.println("FOR ??????FFF===FOR  ????????????==YYYYYY" + gmLicenseNo[i5] + "===" + GMb[j3][0]);
                                    if (!gmLicenseEndDate[i5].equals(GMb[j3][4])) {
                                        this.icInsert(saupNo, ilno, "4", "U", GMb[j3][4], gmLicenseBeginDate[i5], "??????????????????", procID, con);
                                        ++ilno;
                                    }
                                    if (!gmSigongAccount[i5].equals(GMb[j3][5])) {
                                        this.icInsert(saupNo, ilno, "4", "U", GMb[j3][5], gmSigongAccount[i5], "?????????????????????", procID, con);
                                        ++ilno;
                                    }
                                    if (gmDpLicenseYN[i5].equals("Y") && GMb[j3][7].equals("Y")) {
                                        DaeLice = gmDpLicenseYN[i5];
                                        break;
                                    }
                                    if (gmDpLicenseYN[i5].equals("Y")) {
                                        DupCheck = "Y";
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if (gmStatus[i5].equals("D")) {
                        System.out.println("FOR ??????DDDDDDD1111====");
                        gmpsmt3.setString(1, saupNo);
                        gmpsmt3.setString(2, gmLicenseCode[i5]);
                        gmpsmt3.executeUpdate();
                        System.out.println("FOR ??????DDDDDDD====" + gmquery3);
                        gmpsmt3.clearParameters();
                        this.icInsert(saupNo, ilno, "4", "D", gmLicenseCode[i5], "", "????????????", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "4", "D", gmLicenseBeginDate[i5], "", "??????????????????", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "4", "D", ComStr.replace(gmSigongAccount[i5], ",", ""), "", "?????????????????????", procID, con);
                        ++ilno;
                    }
                }
                System.out.println("===?????????????????? ????????????=========");
                System.out.println("====================?????????????????? ???=====================");
                String gj_ZipCode = "";
                String gj_RestAddr = "";
                String gj_Addr = "";
                String gj_Tel = "";
                String gj_Fax = "";
                System.out.println("====================???????????? ????????????=====================");
                gjquery1 = " INSERT INTO ??????_???????????? (  ?????????????????????, ????????????, ?????????, ??????????????????, ????????????,  ?????????????????????, ??????????????????, ??????FAX??????  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?  ) ";
                gjpsmt1 = con.prepareStatement(gjquery1);
                gjquery2 = " DELETE FROM ??????_????????????  WHERE ????????????????????? = ? ";
                gjpsmt2 = con.prepareStatement(gjquery2);
                System.out.println("?????? ?????? ?????? ??????====" + gjpsmt2);
                gjpsmt2.setString(1, saupNo);
                gjpsmt2.executeUpdate();
                gjpsmt2.clearParameters();
                System.out.println("?????? ?????? ?????? ??????====" + gjpsmt2);
                for (int i6 = 1; i6 < gjTotalCount; ++i6) {
                    System.out.println("FOR ??????====" + gjStatus[i6]);
                    if (gjStatus[i6].equals("F") || gjStatus[i6].equals("I") || gjStatus[i6].equals("U")) {
                        System.out.println("FOR ??????====????????????==" + factoryName[i6].trim());
                        gjpsmt1.setString(1, saupNo);
                        gjpsmt1.setInt(2, i6);
                        gjpsmt1.setString(3, factoryName[i6].trim());
                        gjpsmt1.setString(4, ComStr.replace(factoryZipCode[i6], "-", ""));
                        gjpsmt1.setString(5, factoryAddr[i6].trim());
                        gjpsmt1.setString(6, factoryRestAddr[i6].trim());
                        gjpsmt1.setString(7, factoryTel[i6].trim());
                        gjpsmt1.setString(8, factoryFax[i6].trim());
                        gjpsmt1.executeUpdate();
                        gjpsmt1.clearParameters();
                        System.out.println("FOR ???====????????????==" + factoryAddr[i6].trim());
                        gj_ZipCode = ComStr.replace(factoryZipCode[i6], "-", "");
                        gj_RestAddr = factoryAddr[i6].trim();
                        gj_Addr = factoryAddr[i6].trim();
                        gj_Tel = factoryTel[i6].trim();
                        gj_Fax = factoryFax[i6].trim();
                    }
                }
                System.out.println("====================???????????? ???=====================");
                System.out.println("====================??????????????? ????????????=====================");
                idquery1 = " INSERT INTO ??????_??????????????? (  ?????????????????????, ??????????????????, ??????, ??????, ?????????,  ????????????, E_MAIL, ?????????, FAX  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?, ?  ) ";
                idpsmt1 = con.prepareStatement(idquery1);
                idquery2 = " DELETE FROM ??????_???????????????  WHERE ????????????????????? = ? ";
                idpsmt2 = con.prepareStatement(idquery2);
                System.out.println("?????? ?????? ?????? ??????====" + idpsmt2);
                idpsmt2.setString(1, saupNo);
                idpsmt2.executeUpdate();
                idpsmt2.clearParameters();
                System.out.println("?????? ?????? ?????? ??????====" + idpsmt2);
                for (int i6 = 1; i6 < idTotalCount; ++i6) {
                    System.out.println("FOR ??????====" + idStatus[i6]);
                    if (idStatus[i6].equals("F") || idStatus[i6].equals("I") || idStatus[i6].equals("U")) {
                        System.out.println("FOR ??????====????????????==" + ComStr.replace(juminNo[i6], "-", ""));
                        idpsmt1.setString(1, saupNo);
                        idpsmt1.setString(2, ComStr.replace(juminNo[i6], "-", ""));
                        idpsmt1.setString(3, name[i6].trim());
                        idpsmt1.setString(4, jobPart[i6].trim());
                        idpsmt1.setString(5, dutyName[i6].trim());
                        idpsmt1.setString(6, idtel[i6].trim());
                        idpsmt1.setString(7, mail[i6].trim());
                        idpsmt1.setString(8, handphone[i6].trim());
                        idpsmt1.setString(9, idfax[i6].trim());
                        idpsmt1.executeUpdate();
                        idpsmt1.clearParameters();
                        System.out.println("FOR ???====????????????==" + name[i6].trim());
                    }
                }
                System.out.println("====================?????? ?????????????????? ????????????=====================");
                jpquery1 = " INSERT INTO ??????_???????????? (  ?????????????????????, ??????????????????, ??????????????????, ??????3??????_?????????, ????????????,  ??????????????????  ) VALUES (  ?, ?, ?, ?, ?,  ?  ) ";
                jppsmt1 = con.prepareStatement(jpquery1);
                jpquery2 = " UPDATE ??????_????????????  SET ?????????????????? = ?,     ??????3??????_????????? = ?,     ???????????? = ?,     ?????????????????? = ?  WHERE ????????????????????? = ?    AND ?????????????????? = ? ";
                jppsmt2 = con.prepareStatement(jpquery2);
                jpquery3 = " DELETE FROM ??????_????????????  WHERE ????????????????????? = ?    AND ?????????????????? = ? ";
                jppsmt3 = con.prepareStatement(jpquery3);
                if ((jpTotalCount == 1 || jpTotalCount == 0) && (gpTotalCount == 1 || gpTotalCount == 0)) {
                    DaeGood = "";
                }
                for (int i6 = 1; i6 < jpTotalCount; ++i6) {
                    if (dpGoodsYN[i6].equals("Y") && !jpStatus[i6].equals("D") && !jpStatus[i6].equals("N")) {
                        DaeGood = goodsNo[i6];
                    }
                    System.out.println("FOR ??????" + jpStatus[i6]);
                    if (jpStatus[i6].equals("I")) {
                        System.out.println("FOR ??????IIIII");
                        jppsmt1.setString(1, saupNo);
                        jppsmt1.setString(2, goodsNo[i6].trim());
                        jppsmt1.setString(3, formNo[i6].trim());
                        jppsmt1.setString(4, ComStr.replace(threeSale[i6], ",", ""));
                        jppsmt1.setString(5, makeYN[i6].trim());
                        jppsmt1.setString(6, dpGoodsYN[i6].trim());
                        System.out.println("FOR ??????IIIII==" + jpquery1);
                        jppsmt1.executeUpdate();
                        jppsmt1.clearParameters();
                        this.icInsert(saupNo, ilno, "3", "I", "", goodsNo[i6].trim(), "??????????????????", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "3", "I", "", makeYN[i6].trim(), "????????????", procID, con);
                        ++ilno;
                        upCheck = "Y";
                    }
                    else if (jpStatus[i6].equals("F")) {
                        System.out.println("FOR ??????FFFFFF");
                        for (int j4 = 0; j4 < JPb.length; ++j4) {
                            if (goodsNo[i6].trim().equals(JPb[j4][0])) {
                                System.out.println("FOR ??????FFF===FOR  ?????????");
                                if (!formNo[i6].trim().equals(JPb[j4][1]) || !ComStr.replace(threeSale[i6], ",", "").equals(JPb[j4][2]) || !makeYN[i6].trim().equals(JPb[j4][3]) || !dpGoodsYN[i6].trim().equals(JPb[j4][4])) {
                                    System.out.println("FOR ??????FFF===FOR  ?????????????????????");
                                    jppsmt2.setString(1, formNo[i6].trim());
                                    jppsmt2.setString(2, ComStr.replace(threeSale[i6], ",", ""));
                                    jppsmt2.setString(3, makeYN[i6].trim());
                                    jppsmt2.setString(4, dpGoodsYN[i6].trim());
                                    jppsmt2.setString(5, saupNo);
                                    jppsmt2.setString(6, goodsNo[i6]);
                                    jppsmt2.executeUpdate();
                                    System.out.println("FOR ??????FFF===FOR  ????????????==?????????222");
                                    jppsmt2.clearParameters();
                                    System.out.println("FOR ??????FFF===FOR  ????????????==?????????333");
                                    System.out.println("FOR ??????FFF===FOR  ????????????==YYYYYY");
                                    upCheck = "Y";
                                    System.out.println("FOR ??????FFF===FOR  ????????????==YYYYYY" + goodsNo[i6] + "===" + JPb[j4][0]);
                                    if (!goodsNo[i6].equals(JPb[j4][0])) {
                                        this.icInsert(saupNo, ilno, "3", "U", JPb[j4][0], goodsNo[i6], "??????????????????", procID, con);
                                        ++ilno;
                                    }
                                    if (!makeYN[i6].equals(JPb[j4][3])) {
                                        this.icInsert(saupNo, ilno, "3", "U", JPb[j4][3], makeYN[i6], "????????????", procID, con);
                                        ++ilno;
                                    }
                                    if (dpGoodsYN[i6].equals("Y") && JPb[j4][0].equals("Y")) {
                                        DaeGood = goodsNo[i6];
                                        break;
                                    }
                                    if (dpGoodsYN[i6].equals("Y")) {
                                        DupCheck = "Y";
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if (jpStatus[i6].equals("D")) {
                        System.out.println("FOR ??????DDDDDDD1111====");
                        jppsmt3.setString(1, saupNo);
                        jppsmt3.setString(2, goodsNo[i6]);
                        jppsmt3.executeUpdate();
                        jppsmt3.clearParameters();
                        this.icInsert(saupNo, ilno, "3", "D", goodsNo[i6], "", "??????????????????", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "3", "D", makeYN[i6], "", "????????????", procID, con);
                        ++ilno;
                    }
                }
                System.out.println("===?????? ???????????? ????????????=========");
                con.commit();
                con.setAutoCommit(true);
                System.out.println("====================?????? ???????????? ???=====================");
                System.out.println("====================?????? ?????????????????? ????????????=====================");
                gpquery1 = " INSERT INTO ??????_???????????? (  ?????????????????????, ??????????????????, ??????????????????, ??????3??????_?????????, ????????????,  ??????????????????  ) VALUES (  ?, ?, ?, ?, ?,  ?  ) ";
                gppsmt1 = con.prepareStatement(gpquery1);
                gpquery2 = " UPDATE ??????_????????????  SET ?????????????????? = ?,     ??????3??????_????????? = ?,     ???????????? = ?,     ?????????????????? = ?  WHERE ????????????????????? = ?    AND ?????????????????? = ? ";
                gppsmt2 = con.prepareStatement(gpquery2);
                gpquery3 = " DELETE FROM ??????_????????????  WHERE ????????????????????? = ?    AND ?????????????????? = ? ";
                gppsmt3 = con.prepareStatement(gpquery3);
                for (int i6 = 1; i6 < gpTotalCount; ++i6) {
                    if (gdpGoodsYN[i6].equals("Y") && !gpStatus[i6].equals("D") && !gpStatus[i6].equals("N")) {
                        DaeGood = ggoodsNo[i6];
                    }
                    System.out.println("FOR ??????" + gpStatus[i6]);
                    if (gpStatus[i6].equals("I")) {
                        System.out.println("FOR ??????IIIII");
                        gppsmt1.setString(1, saupNo);
                        gppsmt1.setString(2, ggoodsNo[i6].trim());
                        gppsmt1.setString(3, gformNo[i6].trim());
                        gppsmt1.setString(4, ComStr.replace(gthreeSale[i6], ",", ""));
                        gppsmt1.setString(5, gmakeYN[i6].trim());
                        gppsmt1.setString(6, gdpGoodsYN[i6].trim());
                        System.out.println("FOR ??????IIIII==" + gpquery1);
                        gppsmt1.executeUpdate();
                        gppsmt1.clearParameters();
                        this.icInsert(saupNo, ilno, "3", "I", "", ggoodsNo[i6].trim(), "??????????????????", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "3", "I", "", gmakeYN[i6].trim(), "????????????", procID, con);
                        ++ilno;
                        upCheck = "Y";
                    }
                    else if (gpStatus[i6].equals("F")) {
                        System.out.println("FOR ??????FFFFFF");
                        for (int j4 = 0; j4 < GPb.length; ++j4) {
                            if (ggoodsNo[i6].trim().equals(GPb[j4][0])) {
                                System.out.println("FOR ??????FFF===FOR  ?????????");
                                if (!gformNo[i6].trim().equals(GPb[j4][1]) || !ComStr.replace(gthreeSale[i6], ",", "").equals(GPb[j4][2]) || !gmakeYN[i6].trim().equals(GPb[j4][3]) || !gdpGoodsYN[i6].trim().equals(GPb[j4][4])) {
                                    System.out.println("FOR ??????FFF===FOR  ?????????????????????");
                                    gppsmt2.setString(1, gformNo[i6].trim());
                                    gppsmt2.setString(2, ComStr.replace(gthreeSale[i6], ",", ""));
                                    gppsmt2.setString(3, gmakeYN[i6].trim());
                                    gppsmt2.setString(4, gdpGoodsYN[i6].trim());
                                    gppsmt2.setString(5, saupNo);
                                    gppsmt2.setString(6, ggoodsNo[i6]);
                                    gppsmt2.executeUpdate();
                                    System.out.println("FOR ??????FFF===FOR  ????????????==?????????222");
                                    gppsmt2.clearParameters();
                                    System.out.println("FOR ??????FFF===FOR  ????????????==?????????333");
                                    System.out.println("FOR ??????FFF===FOR  ????????????==YYYYYY");
                                    upCheck = "Y";
                                    System.out.println("FOR ??????FFF===FOR  ????????????==YYYYYY" + ggoodsNo[i6] + "===" + GPb[j4][0]);
                                    if (!ggoodsNo[i6].equals(GPb[j4][0])) {
                                        this.icInsert(saupNo, ilno, "3", "U", GPb[j4][0], ggoodsNo[i6], "??????????????????", procID, con);
                                        ++ilno;
                                    }
                                    if (!gmakeYN[i6].equals(GPb[j4][3])) {
                                        this.icInsert(saupNo, ilno, "3", "U", GPb[j4][3], gmakeYN[i6], "????????????", procID, con);
                                        ++ilno;
                                    }
                                    if (gdpGoodsYN[i6].equals("Y") && GPb[j4][0].equals("Y")) {
                                        DaeGood = ggoodsNo[i6];
                                        break;
                                    }
                                    if (gdpGoodsYN[i6].equals("Y")) {
                                        DupCheck = "Y";
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    else if (gpStatus[i6].equals("D")) {
                        System.out.println("FOR ??????DDDDDDD1111====");
                        gppsmt3.setString(1, saupNo);
                        gppsmt3.setString(2, ggoodsNo[i6]);
                        gppsmt3.executeUpdate();
                        gppsmt3.clearParameters();
                        this.icInsert(saupNo, ilno, "3", "D", ggoodsNo[i6], "", "??????????????????", procID, con);
                        ++ilno;
                        this.icInsert(saupNo, ilno, "3", "D", gmakeYN[i6], "", "????????????", procID, con);
                        ++ilno;
                    }
                }
                System.out.println("===?????? ???????????? ????????????=========");
                con.commit();
                con.setAutoCommit(true);
                System.out.println("====================?????? ???????????? ???=====================");
                System.out.println("====================???????????? ????????????=====================");
                query1 = " SELECT ???????????? FROM ??????_????????? WHERE ?????????ID = ? ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, procID);
                rs = psmt1.executeQuery();
                String jicheong = "";
                if (rs.next()) {
                    jicheong = rs.getString(1);
                }
                psmt1.clearParameters();
                query2 = " UPDATE ??????_?????????????????????  SET ?????? = ?,     ????????? = ?,     ??????????????? = ?,     ???????????? = ?,     ?????????????????? = ?,     ???????????? = ?,     ???????????? = ?,     ?????????????????? = ?,     ????????????2 = ?,     ???????????????????????? = ?,     ????????? = ?,     ???????????? = ?,     ?????????????????? = ?,     ???????????? = ?,     ???????????? = ?,     ?????? = ?,     ??????????????? = ?,     ???????????? = ?,     FAX?????? = ?,     ???????????? = ?,     ?????????????????? = ?,     ?????????????????? = ADD_MONTHS(SYSDATE, 24),     ???????????? = SYSDATE,     ?????????ID = ?,      ???????????? = ?  WHERE ????????????????????? = ? ";
                psmt2 = con.prepareStatement(query2);
                System.out.println("UPdate=====" + query2);
                query3 = " INSERT INTO ??????_??????????????????????????? (  ?????????????????????, ????????????, ??????, ?????????, ???????????????,  ????????????, ??????????????????, ????????????, ????????????, ??????????????????_??????,  ??????????????????, ????????????1, ????????????2, ????????????????????????, ?????????,  ????????????, ??????????????????, ????????????, ????????????, ??????,  ???????????????, ????????????, FAX??????, ????????????, ??????????????????,  ????????????????????????, ??????????????????, ??????????????????,  ??????????????????, ?????????ID, ??????????????????, ????????????,????????????  ) VALUES (  ?, SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ? , ?, ?, ADD_MONTHS(SYSDATE, 24), TO_DATE(SUBSTR(?,1,10),'YYYY-MM-DD'), ?  ) ";
                psmt3 = con.prepareStatement(query3);
                System.out.println("Insert =====" + query3);
                System.out.println("??????DATA ????????? ??????" + dbb.getJabon());
                if (upCheck.equals("Y") || DupCheck.equals("Y") || !((dbb.getNationality() == null) ? "" : dbb.getNationality()).equals(nationality) || !((dbb.getSangho() == null) ? "" : dbb.getSangho()).equals(sangho) || (!((dbb.getESangho() == null) ? "" : dbb.getESangho()).equals(eSangho) || !dbb.getOpenDate().equals(openDate) || !dbb.getBubinOpenDate().equals(bubinOpenDate)) || !((dbb.getJobGubun() == null) ? "" : dbb.getJobGubun()).equals(jobGubun) || !((dbb.getMakeGubun() == null) ? "" : dbb.getMakeGubun()).equals(makeGubun) || !((dbb.getBubinNo() == null) ? "" : dbb.getBubinNo()).equals(bubinNo) || !((dbb.getComGubun2() == null) ? "" : dbb.getComGubun2()).equals(comGubun2) || (!((dbb.getComGubunYear() == null) ? "" : dbb.getComGubunYear()).equals(comGubunYear) || dbb.getJabon() != Long.parseLong(ComStr.replace(jabon, ",", "")) || dbb.getEmployeeNo() != Integer.parseInt(ComStr.replace(employeeNo, ",", ""))) || !((dbb.getAccountDate() == null) ? "" : dbb.getAccountDate()).equals(accountDate) || !((dbb.getZipCode() == null) ? "" : dbb.getZipCode()).equals(zipCode) || !((dbb.getLocalCode() == null) ? "" : dbb.getLocalCode()).equals(localCode) || !((dbb.getAddr() == null) ? "" : dbb.getAddr()).equals(addr) || !((dbb.getRestAddr() == null) ? "" : dbb.getRestAddr()).equals(restAddr) || !((dbb.getTel() == null) ? "" : dbb.getTel()).equals(tel) || !((dbb.getFax() == null) ? "" : dbb.getFax()).equals(fax) || !((dbb.getHomepage() == null) ? "" : dbb.getHomepage()).equals(homepage) || !((dbb.getExceptYN() == null) ? "" : dbb.getExceptYN()).equals(exceptYN)) {
                    System.out.println("???????????????????????????====" + sangho);
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
                    psmt2.setString(24, jicheong);
                    psmt2.setString(25, saupNo);
                    psmt2.executeUpdate();
                    psmt2.clearParameters();
                    System.out.println("?????????????????????OK====" + sangho);
                    System.out.println("??????????????????????????? ????????????====" + sangho);
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
                    System.out.println("??????????????????????????? ??????OK====" + sangho);
                    if (!sangho.equals(dbb.getSangho())) {
                        this.icInsert(saupNo, ilno, "1", "U", dbb.getSangho(), sangho, "?????????", procID, con);
                        ++ilno;
                    }
                    if (!jobGubun.equals(dbb.getJobGubun())) {
                        this.icInsert(saupNo, ilno, "1", "U", dbb.getJobGubun(), jobGubun, "????????????", procID, con);
                        ++ilno;
                    }
                    if (!addr.equals(dbb.getAddr())) {
                        this.icInsert(saupNo, ilno, "1", "U", dbb.getAddr(), addr, "??????", procID, con);
                        ++ilno;
                    }
                }
                con.commit();
                con.setAutoCommit(true);
                System.out.println("============== ?????? EDI ?????? ????????? ?????? ===================");
                System.out.println("============== ?????? EDI ?????? ????????? ?????? ===================");
                System.out.println("============== ?????? EDI ?????? ????????? ?????? ===================");
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
                query1 = " SELECT A.???????????? FROM syn_?????? A, syn_?????????????????? B  WHERE A.???????????? = B.???????????? AND B.G2B?????? = ? ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, localCode);
                rs = psmt1.executeQuery();
                if (rs.next()) {
                    jicheongCode = rs.getString(1);
                }
                psmt1.clearParameters();
                System.out.println(String.valueOf(localCode) + "<==???????????? ==>" + jicheongCode);
                if (dbb.getJobGubun().substring(0, 1).equals("0") && jobGubun.substring(0, 1).equals("1")) {
                    query1 = " SELECT MAX(??????????????????) FROM SYN_????????????  WHERE ?????????????????? LIKE '" + jicheongCode + "1%' " + " OR ?????????????????? LIKE '" + jicheongCode + "20%' ORDER BY ?????????????????? DESC ";
                    psmt1 = con.prepareStatement(query1);
                    rs = psmt1.executeQuery();
                    if (rs.next()) {
                        Code1 = rs.getString(1);
                    }
                    cCode1 = Integer.parseInt(Code1) + 1;
                    ls_NaUp = Integer.toString(cCode1);
                    psmt1.clearParameters();
                    System.out.println(String.valueOf(jicheongCode) + "<==???????????????????????? ??????==>" + ls_NaUp);
                }
                if (dbb.getJobGubun().substring(1, 2).equals("0") && jobGubun.substring(1, 2).equals("1")) {
                    query1 = " SELECT MAX(??????????????????) FROM SYN_????????????  WHERE ?????????????????? LIKE '" + jicheongCode + "31%' " + " OR ?????????????????? LIKE '" + jicheongCode + "32%'  " + " OR ?????????????????? LIKE '" + jicheongCode + "33%'  " + " OR ?????????????????? LIKE '" + jicheongCode + "34%' ORDER BY ?????????????????? DESC ";
                    psmt1 = con.prepareStatement(query1);
                    rs = psmt1.executeQuery();
                    if (rs.next()) {
                        Code3 = rs.getString(1);
                    }
                    cCode3 = Integer.parseInt(Code3) + 1;
                    ls_GoUp = Integer.toString(cCode3);
                    psmt1.clearParameters();
                    System.out.println(String.valueOf(jicheongCode) + "<==??????????????????(??????) ??????==>" + ls_GoUp);
                }
                if (dbb.getJobGubun().substring(2, 3).equals("0") && jobGubun.substring(2, 3).equals("1")) {
                    query1 = " SELECT MAX(??????????????????) FROM SYN_????????????  WHERE ?????????????????? LIKE '" + jicheongCode + "35%' ORDER BY ?????????????????? DESC ";
                    psmt1 = con.prepareStatement(query1);
                    rs = psmt1.executeQuery();
                    if (rs.next()) {
                        Code3_1 = rs.getString(1);
                    }
                    cCode3_1 = Integer.parseInt(Code3_1) + 1;
                    ls_YoUp = Integer.toString(cCode3_1);
                    psmt1.clearParameters();
                    System.out.println(String.valueOf(jicheongCode) + "<==??????????????????(??????) ??????==>" + ls_YoUp);
                }
                if (dbb.getJobGubun().substring(4, 5).equals("0") && jobGubun.substring(4, 5).equals("1")) {
                    query1 = " SELECT MAX(??????????????????) FROM SYN_????????????  WHERE ?????????????????? LIKE '" + jicheongCode + "2%' ORDER BY ?????????????????? DESC ";
                    psmt1 = con.prepareStatement(query1);
                    rs = psmt1.executeQuery();
                    if (rs.next()) {
                        Code2 = rs.getString(1);
                    }
                    cCode2 = Integer.parseInt(Code2) + 1;
                    ls_OiUp = Integer.toString(cCode2);
                    psmt1.clearParameters();
                    System.out.println(String.valueOf(jicheongCode) + "<==???????????????????????? ??????==>" + ls_OiUp);
                }
                query1 = " SELECT ???????????? FROM SYN_??????????????????  WHERE G2B?????? = ? ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, localCode);
                rs = psmt1.executeQuery();
                ls_local = "";
                if (rs.next()) {
                    ls_local = rs.getString(1);
                }
                psmt1.clearParameters();
                System.out.println(String.valueOf(jicheongCode) + "<==??????????????? ????????????==>" + ls_local);
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
                    System.out.println("?????????????????? ?????????????????? ??????===" + ls_NaUp2 + "==" + ls_OiUp2 + "==" + ls_GoUp2 + "==" + ls_YoUp2 + "==" + ls_yn);
                    if (!ls_yn.equals("N")) {
                        System.out.println("???????????? ??????EDI ??????==================" + ls_NaUp2 + "================================");
                        if (dbb.getJobGubun().substring(0, 1).equals("0") && jobGubun.substring(0, 1).equals("0")) {
                            System.out.println("??????, ?????? ???????????? ?????????.(XX)");
                        }
                        else if (dbb.getJobGubun().substring(0, 1).equals("0") && jobGubun.substring(0, 1).equals("1")) {
                            System.out.println("?????? ????????????==X, ?????? ???????????? == 0 --- (X O)");
                            this.cont_MainNo_I(saupNo, ls_NaUp, "1", "I", sangho, DaeCeo2, DaeNo1, ls_local, zipCode, restAddr, addr, tel, fax, openDate, bubinOpenDate, comGubun1, registOkDate, nationality, jabon, employeeNo, homepage, DaeMail, bubinNo, gj_ZipCode, gj_RestAddr, gj_Addr, gj_Tel, gj_Fax, eSangho, DaeLiCode, con);
                            this.cont_JP(ls_NaUp, "I", jpTotalCount, goodsNo, threeSale, formNo, con);
                            this.cont_ID(ls_NaUp, "I", idTotalCount, dutyName, name, juminNo, con);
                        }
                        else if (dbb.getJobGubun().substring(0, 1).equals("1") && jobGubun.substring(0, 1).equals("0")) {
                            System.out.println("?????? ????????????==0, ?????? ???????????? == X --- (O X)");
                            this.cont_MainNo_I(saupNo, ls_NaUp2, "1", "D", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", con);
                        }
                        else if (dbb.getJobGubun().substring(0, 1).equals("1") && jobGubun.substring(0, 1).equals("1")) {
                            System.out.println("?????? ????????????==0, ?????? ???????????? == O --- (O O)");
                            this.cont_MainNo_I(saupNo, ls_NaUp2, "1", "U", sangho, DaeCeo2, DaeNo1, ls_local, zipCode, restAddr, addr, tel, fax, openDate, bubinOpenDate, comGubun1, registOkDate, nationality, jabon, employeeNo, homepage, DaeMail, bubinNo, gj_ZipCode, gj_RestAddr, gj_Addr, gj_Tel, gj_Fax, eSangho, DaeLiCode, con);
                            this.cont_JP(ls_NaUp2, "U", jpTotalCount, goodsNo, threeSale, formNo, con);
                            this.cont_ID(ls_NaUp2, "U", idTotalCount, dutyName, name, juminNo, con);
                        }
                        System.out.println("???????????? ??????EDI ??????==================" + ls_OiUp2 + "================================");
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
                        System.out.println("???????????? ??????EDI ??????==================" + ls_GoUp2 + "================================");
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
                        System.out.println("???????????? ??????EDI ??????==================" + ls_YoUp2 + "================================");
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
                        break Label_15406;
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
                    Log.debug("UM_ADV_ConrD010c.doPost block SQLException : Transaction Rollback?????? SQLException ?????????");
                    Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                    e.printStackTrace();
                }
                Log.debug("UM_ADV_ConrD010c.doPost block SQLException : ");
                Log.debug("Exception?????? ?????? : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
            }
            catch (Exception exc2) {
                try {
                    con.rollback();
                    con.setAutoCommit(true);
                }
                catch (SQLException e) {
                    Log.debug("UM_ADV_ConrD010c.doPost block Exception : Transaction Rollback?????? Exception ?????????");
                    Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                    e.printStackTrace();
                }
                Log.debug("UM_ADV_ConrD010c.doPost block Exception : ");
                Log.debug("Exception?????? ?????? : " + exc2.toString());
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
            System.out.println("syn_?????????????????? ??????(select_EDI_No)-------------");
            ls_query = " Select decode(?????????????????????, null, ' ', ?????????????????????),         decode(????????????????????????, null, ' ', ????????????????????????),         decode(????????????????????????, null, ' ', ????????????????????????),          decode(????????????????????????, null, ' ', ????????????????????????),          decode(????????????????????????, null, ' ', ????????????????????????),          decode(????????????, null, 'Y', ????????????)   From syn_??????????????????  Where ????????????????????? = ? ";
            edi_psmt = con.prepareStatement(ls_query);
            edi_psmt.setString(1, saupNo);
            System.out.println("syn_??????????????????==" + ls_query);
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
            System.out.println("syn_??????????????????==?????????????????????==" + ls_upcha1);
            System.out.println("syn_??????????????????==????????????????????????==" + ls_upcha2);
            System.out.println("syn_??????????????????==????????????????????????==" + ls_upcha3);
            System.out.println("syn_??????????????????==????????????????????????==" + ls_upcha4);
            System.out.println("syn_??????????????????==????????????????????????==" + ls_upcha5);
            System.out.println("syn_??????????????????==????????????==" + ls_yn);
            System.out.println("syn_?????????????????? ???======================");
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
                Log.debug("UM_ADV_ConrD010c.doPost block SQLException : Transaction Rollback?????? SQLException ?????????");
                Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrD010c.doPost block SQLException : ");
            Log.debug("Exception?????? ?????? : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
        }
        catch (Exception exc2) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrD010c.doPost block Exception : Transaction Rollback?????? Exception ?????????");
                Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrD010c.doPost block Exception : ");
            Log.debug("Exception?????? ?????? : " + exc2.toString());
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
        final String ls_JP_I = " INSERT INTO SYN_???????????? (  ??????????????????, ??????????????????, ?????????, ??????????????????, ????????????, ???????????????  ) VALUES (  ?, ?, ?, ?, ?, '970251'  ) ";
        final String ls_JP_D = " DELETE FROM SYN_????????????  WHERE ?????????????????? = ? AND ???????????? = ?";
        try {
            System.out.println("?????? EDI ???????????? ??????(cont_JP)-------------");
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
            System.out.println("?????? EDI ???????????? ??????(cont_JP)-------------");
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrD010c.select_user block SQLException : ");
            Log.debug("Exception?????? ?????? : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrD010c.select_user block Exception : ");
            Log.debug("Exception?????? ?????? : " + exc.toString());
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
        final String ls_ID_I = " INSERT INTO SYN_??????????????? (  ??????????????????, ?????????, ??????, ??????????????????, ???????????????  ) VALUES (  ?, ?, ?, ?, '970251'  ) ";
        final String ls_ID_D = " DELETE FROM SYN_???????????????  WHERE ?????????????????? = ? ";
        try {
            System.out.println("?????? EDI ??????????????? ??????(cont_ID)-------------");
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
            System.out.println("?????? EDI ??????????????? ??????(cont_ID)-------------");
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrD010c.select_user block SQLException : ");
            Log.debug("Exception?????? ?????? : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrD010c.select_user block Exception : ");
            Log.debug("Exception?????? ?????? : " + exc.toString());
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
        final String ls_MS_I = " INSERT INTO SYN_???????????? (  ??????????????????, ????????????, ??????????????????, ???????????????, ???????????????, ????????????  ) VALUES (  ?, ?, ?, ?, '970251', SYSDATE  ) ";
        final String ls_MS_D = " DELETE FROM SYN_????????????  WHERE ?????????????????? = ? ";
        try {
            System.out.println("?????? EDI ?????? ??????(cont_MS)-------------");
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
            System.out.println("?????? EDI ?????? ??????(cont_MS)-------------");
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrD010c.select_user block SQLException : ");
            Log.debug("Exception?????? ?????? : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrD010c.select_user block Exception : ");
            Log.debug("Exception?????? ?????? : " + exc.toString());
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
        final String ls_Main_I = " INSERT INTO SYN_???????????? (  ????????????, ??????????????????, ?????????, ?????????????????????,  ????????????, ????????????, ?????????????????????, ??????????????????, ??????????????????,  ??????????????????, ????????????, ??????????????????, FAX??????, ????????????,  ????????????, ??????????????????, ????????????, ??????????????????, ????????????,  ????????????, ????????????, ?????????, ????????????, ????????????,  ?????????????????????, ???????????????  ) VALUES (  ?, ?, ?, ?,  SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ADD_MONTHS(SYSDATE, 24), SYSDATE,  SYSDATE, ?, ?, ?, ?,  ?, '970251'  ) ";
        final String ls_Main_U = " UPDATE SYN_???????????? SET  ????????? = ?, ????????????????????? = ?,  ???????????? = ?, ????????????????????? = ?, ?????????????????? = ?, ?????????????????? = ?,  ?????????????????? = ?, ???????????? = ?, ?????????????????? = ?, FAX?????? = ?, ???????????? = ?,  ???????????? = ?, ?????????????????? = ?, ???????????? = ?, ?????????????????? = ADD_MONTHS(SYSDATE, 24), ???????????? = SYSDATE,  ???????????? = ?, ????????? = ?, ???????????? = ?, ???????????? = ?,  ????????????????????? = ?  WHERE ?????????????????? = ? ";
        final String ls_Main_D = " UPDATE SYN_????????????  SET ???????????? = SYSDATE, \t\t???????????? = '07', \t\t???????????? = SYSDATE  WHERE ?????????????????? = ? ";
        final String ls_Naeja_I = " INSERT INTO SYN_???????????? (  ??????????????????, ????????????, ????????????, ??????_??????, ??????,  ????????????, FAX??????, ????????????, ??????????????????, ???????????????  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, SYSDATE, ?, '970251'  ) ";
        final String ls_Naeja_U = " UPDATE SYN_???????????? SET  ???????????? = ?, ???????????? = ?, ??????_?????? = ?, ?????? = ?,  ???????????? = ?, FAX?????? = ?, ???????????? = SYSDATE, ?????????????????? = ?  WHERE ?????????????????? = ? ";
        final String ls_For_I = " INSERT INTO SYN_?????????????????? (  ??????????????????, ???????????????, ????????????, ??????????????????, ???????????????  ) VALUES (  ?, ?, ?, ?, '970251'  ) ";
        final String ls_For_U = " UPDATE SYN_?????????????????? SET  ??????????????? = ?, ???????????? = ?, ?????????????????? = ?  WHERE ?????????????????? = ? ";
        final String ls_Sisl_I = " INSERT INTO SYN_???????????? (  ??????????????????, ?????????, ????????????, ??????????????????, ??????????????????, ???????????????  ) VALUES (  ?, ?, ?, ?, ?, '970251'  ) ";
        final String ls_Sisl_U = " UPDATE SYN_???????????? SET  ????????? = ?, ???????????? = ?, ?????????????????? = ?, ?????????????????? = ?  WHERE ?????????????????? = ? ";
        final String ls_MaNae_U = " UPDATE SYN_?????????????????? SET  ???????????????????????? = ?  WHERE ????????????????????? = ? ";
        final String ls_MaFor_U = " UPDATE SYN_?????????????????? SET  ???????????????????????? = ?  WHERE ????????????????????? = ? ";
        final String ls_MaGo_U = " UPDATE SYN_?????????????????? SET  ???????????????????????? = ?  WHERE ????????????????????? = ? ";
        final String ls_MaYo_U = " UPDATE SYN_?????????????????? SET  ???????????????????????? = ?  WHERE ????????????????????? = ? ";
        String ls_bubchk = "";
        System.out.println("?????? EDI ???????????? ??????(cont_MainNo_I) ??????-------------------------------------------");
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
                System.out.println("???????????? ??????--" + ls_Main_I);
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
                System.out.println("???????????? ??????--???");
            }
            if (up_chk.equals("U")) {
                System.out.println("???????????? ??????--" + ls_Main_U);
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
                System.out.println("?????????--" + jabon);
                System.out.println("?????????--" + Long.parseLong(ComStr.replace(jabon, ",", "")));
                System.out.println("????????????--" + employeeNo);
                ps_Main_U.setString(15, nationality);
                ps_Main_U.setLong(16, Long.parseLong(ComStr.replace(jabon, ",", "")));
                ps_Main_U.setInt(17, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                ps_Main_U.setString(18, homepage);
                ps_Main_U.setString(19, DaeMail);
                ps_Main_U.setString(20, ediNo);
                ps_Main_U.executeUpdate();
                ps_Main_U.clearParameters();
                System.out.println("???????????? ??????--???");
            }
            if (up_chk.equals("D")) {
                System.out.println("???????????? ??????--" + ls_Main_D);
                ps_Main_D = con.prepareStatement(ls_Main_D);
                ps_Main_D.setString(1, ediNo);
                ps_Main_D.executeUpdate();
                ps_Main_D.clearParameters();
                System.out.println("???????????? ??????--???");
            }
            if (up_chk.equals("I") && giup_chk.equals("1")) {
                System.out.println("???????????? ??????--" + ls_Naeja_I);
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
                System.out.println("???????????? ??????--???");
            }
            if (up_chk.equals("U") && giup_chk.equals("1")) {
                System.out.println("???????????? ??????--" + ls_Naeja_U);
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
                System.out.println("???????????? ??????--???");
            }
            if (up_chk.equals("I") && giup_chk.equals("2")) {
                System.out.println("???????????? ?????? --" + ls_For_I);
                ps_For_I = con.prepareStatement(ls_For_I);
                ps_For_I.setString(1, ediNo);
                ps_For_I.setString(2, eSangho);
                ps_For_I.setString(3, nationality);
                ps_For_I.setString(4, bubinNo);
                ps_For_I.executeUpdate();
                ps_For_I.clearParameters();
                System.out.println("???????????? ?????? --???");
            }
            if (up_chk.equals("U") && giup_chk.equals("2")) {
                System.out.println("???????????? ?????? --" + ls_For_U);
                ps_For_U = con.prepareStatement(ls_For_U);
                ps_For_U.setString(1, eSangho);
                ps_For_U.setString(2, nationality);
                ps_For_U.setString(3, bubinNo);
                ps_For_U.setString(4, ediNo);
                ps_For_U.executeUpdate();
                ps_For_U.clearParameters();
                System.out.println("???????????? ?????? --???");
            }
            if (up_chk.equals("I") && giup_chk.equals("3")) {
                System.out.println("???????????? ?????? --" + ls_Sisl_I);
                ps_Sisl_I = con.prepareStatement(ls_Sisl_I);
                ps_Sisl_I.setString(1, ediNo);
                ps_Sisl_I.setLong(2, Long.parseLong(ComStr.replace(jabon, ",", "")));
                ps_Sisl_I.setInt(3, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                ps_Sisl_I.setString(4, DaeLiCode);
                ps_Sisl_I.setString(5, bubinNo);
                ps_Sisl_I.executeUpdate();
                ps_Sisl_I.clearParameters();
                System.out.println("???????????? ?????? --???");
            }
            if (up_chk.equals("U") && giup_chk.equals("3")) {
                System.out.println("???????????? ?????? --" + ls_Sisl_U);
                ps_Sisl_U = con.prepareStatement(ls_Sisl_U);
                ps_Sisl_U.setLong(1, Long.parseLong(ComStr.replace(jabon, ",", "")));
                ps_Sisl_U.setInt(2, Integer.parseInt(ComStr.replace(employeeNo, ",", "")));
                ps_Sisl_U.setString(3, DaeLiCode);
                ps_Sisl_U.setString(4, bubinNo);
                ps_Sisl_U.setString(5, ediNo);
                ps_Sisl_U.executeUpdate();
                ps_Sisl_U.clearParameters();
                System.out.println("???????????? ?????? --???");
            }
            if (up_chk.equals("I") && giup_chk.equals("1")) {
                System.out.println("?????????????????? (???????????? ??????) --" + ls_MaNae_U);
                ps_MaNae_U = con.prepareStatement(ls_MaNae_U);
                ps_MaNae_U.setString(1, ediNo);
                ps_MaNae_U.setString(2, saupNo);
                ps_MaNae_U.executeUpdate();
                ps_MaNae_U.clearParameters();
                System.out.println("?????????????????? (???????????? ??????) --???");
            }
            if (up_chk.equals("D") && giup_chk.equals("1")) {
                System.out.println("?????????????????? (???????????? ??????) --" + ls_MaNae_U);
                ps_MaNae_U = con.prepareStatement(ls_MaNae_U);
                ps_MaNae_U.setString(1, null);
                ps_MaNae_U.setString(2, saupNo);
                ps_MaNae_U.executeUpdate();
                ps_MaNae_U.clearParameters();
                System.out.println("?????????????????? (???????????? ??????) --???");
            }
            if (up_chk.equals("I") && giup_chk.equals("2")) {
                System.out.println("?????????????????? (???????????? ??????) --" + ls_MaFor_U);
                ps_MaFor_U = con.prepareStatement(ls_MaFor_U);
                ps_MaFor_U.setString(1, ediNo);
                ps_MaFor_U.setString(2, saupNo);
                ps_MaFor_U.executeUpdate();
                ps_MaFor_U.clearParameters();
                System.out.println("?????????????????? (???????????? ??????) --???");
            }
            if (up_chk.equals("D") && giup_chk.equals("2")) {
                System.out.println("?????????????????? (???????????? ??????) --" + ls_MaFor_U);
                ps_MaFor_U = con.prepareStatement(ls_MaFor_U);
                ps_MaFor_U.setString(1, null);
                ps_MaFor_U.setString(2, saupNo);
                ps_MaFor_U.executeUpdate();
                ps_MaFor_U.clearParameters();
                System.out.println("?????????????????? (???????????? ??????) --???");
            }
            if (up_chk.equals("I") && giup_chk.equals("3") && (ediNo == null || ediNo.substring(0, 2).equals("35"))) {
                System.out.println("?????????????????? (???????????? ??????) --" + ls_MaGo_U);
                ps_MaGo_U = con.prepareStatement(ls_MaGo_U);
                ps_MaGo_U.setString(1, ediNo);
                ps_MaGo_U.setString(2, saupNo);
                ps_MaGo_U.executeUpdate();
                ps_MaGo_U.clearParameters();
                System.out.println("?????????????????? (???????????? ??????) --???");
            }
            if (up_chk.equals("D") && giup_chk.equals("3") && (ediNo == null || ediNo.substring(0, 2).equals("35"))) {
                System.out.println("?????????????????? (???????????? ??????) --" + ls_MaGo_U);
                ps_MaGo_U = con.prepareStatement(ls_MaGo_U);
                ps_MaGo_U.setString(1, null);
                ps_MaGo_U.setString(2, saupNo);
                ps_MaGo_U.executeUpdate();
                ps_MaGo_U.clearParameters();
                System.out.println("?????????????????? (???????????? ??????) --???");
            }
            if (up_chk.equals("I") && giup_chk.equals("3") && (ediNo == null || !ediNo.substring(0, 2).equals("35"))) {
                System.out.println("?????????????????? (???????????? ??????) --" + ls_MaGo_U);
                ps_MaGo_U = con.prepareStatement(ls_MaGo_U);
                ps_MaGo_U.setString(1, ediNo);
                ps_MaGo_U.setString(2, saupNo);
                ps_MaGo_U.executeUpdate();
                ps_MaGo_U.clearParameters();
                System.out.println("?????????????????? (???????????? ??????) --???");
            }
            if (up_chk.equals("D") && giup_chk.equals("3") && (ediNo == null || !ediNo.substring(0, 2).equals("35"))) {
                System.out.println("?????????????????? (???????????? ??????) --" + ls_MaYo_U);
                ps_MaYo_U = con.prepareStatement(ls_MaYo_U);
                ps_MaYo_U.setString(1, null);
                ps_MaYo_U.setString(2, saupNo);
                ps_MaYo_U.executeUpdate();
                ps_MaYo_U.clearParameters();
                System.out.println("?????????????????? (???????????? ??????) --???");
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
            System.out.println("?????? EDI ???????????? ??????(cont_MainNo_I) ??????-------------------------------------------???");
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADV_ConrD010c.select_user block SQLException : ");
            Log.debug("Exception?????? ?????? : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrD010c.select_user block Exception : ");
            Log.debug("Exception?????? ?????? : " + exc.toString());
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
            System.out.println("??????_?????????????????????????????? ??????");
            icquery = " INSERT INTO ??????_???????????????????????? (  ?????????????????????, ????????????, ????????????, ???????????????, ????????????, ???????????????, ???????????????, ?????????, ?????????ID  ) VALUES (  ?, sysdate, ?, ?, ?, ?, ?, ?, ?  ) ";
            icpsmt = con.prepareStatement(icquery);
            icpsmt.setString(1, saupNo);
            icpsmt.setInt(2, ilno);
            icpsmt.setString(3, tabGubun);
            icpsmt.setString(4, gubun);
            icpsmt.setString(5, beComment);
            icpsmt.setString(6, afComment);
            icpsmt.setString(7, item);
            icpsmt.setString(8, procID);
            System.out.println("??????_?????????????????????????????? Up");
            icpsmt.executeUpdate();
            icpsmt.clearParameters();
            System.out.println("??????_?????????????????????????????? ??????");
        }
        catch (SQLException exc) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrD010c.doPost block SQLException : Transaction Rollback?????? SQLException ?????????");
                Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrD010c.doPost block SQLException : ");
            Log.debug("Exception?????? ?????? : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
        }
        catch (Exception exc2) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrD010c.doPost block Exception : Transaction Rollback?????? Exception ?????????");
                Log.debug("Exception?????? ?????? : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrD010c.doPost block Exception : ");
            Log.debug("Exception?????? ?????? : " + exc2.toString());
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
            query4 = " SELECT  ?????????????????????, ??????, ?????????, ???????????????,  TO_CHAR(????????????, 'YYYY-MM-DD'), TO_CHAR(??????????????????, 'YYYY-MM-DD'), ????????????, ????????????, ??????????????????_??????,  ??????????????????, ????????????1, ????????????2, ????????????????????????, ?????????,  ????????????, ??????????????????, ????????????, ????????????, ??????,  ???????????????, ????????????, FAX??????, ????????????, ??????????????????,  ??????????????????, ????????????, ????????????, ??????????????????, ?????????ID  FROM ??????_?????????????????????  WHERE ????????????????????? = '" + saupNo + "' ";
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
            Log.debug("UM_ADV_ConrD010c.select_user block SQLException : ");
            Log.debug("Exception?????? ?????? : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrD010c.select_user block Exception : ");
            Log.debug("Exception?????? ?????? : " + exc.toString());
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
            Log.debug("Exception?????? ?????? : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrC010c.select_user block Exception : ");
            Log.debug("Exception?????? ?????? : " + exc.toString());
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
                query = " SELECT  ????????????,  ?????????????????????,  ?????????????????????,  ????????????????????? " + this.getQueryCondition();
                break;
            }
            case 200: {
                query = " SELECT  A.????????????,  B.?????????,  A.????????????,  TO_CHAR(A.??????????????????, 'YYYY-MM-DD'),  A.?????????????????????,  A.?????????????????????,  A.?????????????????? " + this.getQueryCondition();
                break;
            }
            case 400: {
                query = " SELECT  ?????????,  ??????????????????,  ????????????,  ?????????????????????,  ??????????????????,  ??????FAX?????? " + this.getQueryCondition();
                break;
            }
            case 500: {
                query = " SELECT  ??????????????????,  ??????,  ??????,  ?????????,  ????????????,  E_MAIL,  ?????????,  FAX " + this.getQueryCondition();
                break;
            }
            case 700: {
                query = " SELECT  B.CATE_NAME,  A.??????????????????,  A.??????3??????_?????????,  A.?????????????????? " + this.getQueryCondition();
                break;
            }
            case 600: {
                query = " SELECT  B.CATE_NAME,  A.??????????????????,  A.??????????????????,  A.??????????????????,  TO_CHAR(A.???????????????, 'YYYY-MM-DD'),  A.??????3??????_?????????,  A.?????????????????? " + this.getQueryCondition();
                break;
            }
        }
        return query;
    }
    
    public String getQueryCondition() {
        String queryCondition = "";
        switch (this.currentMode) {
            case 100: {
                queryCondition = " FROM ??????_?????????  WHERE ????????????????????? = ? ";
                break;
            }
            case 200: {
                queryCondition = " FROM ??????_???????????? A, SYN_???????????? B  WHERE A.????????????????????? = ?  AND B.???????????? = 'GU9'  AND A.???????????? = B.?????? ";
                break;
            }
            case 400: {
                queryCondition = " FROM ??????_????????????  WHERE ????????????????????? = ? ";
                break;
            }
            case 500: {
                queryCondition = " FROM ??????_???????????????  WHERE ????????????????????? = ? ";
                break;
            }
            case 600: {
                queryCondition = " FROM ??????_???????????? A, xzcate010m@dbl_usemn_c_mokmn B  WHERE A.????????????????????? = ?  AND A.?????????????????? = B.CATE_ID  AND A.???????????? = 'Y' ";
                break;
            }
            case 700: {
                queryCondition = " FROM ??????_???????????? A, xzcate010m@dbl_usemn_c_mokmn B  WHERE A.????????????????????? = ?  AND A.?????????????????? = B.CATE_ID  AND A.???????????? = 'N' ";
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
