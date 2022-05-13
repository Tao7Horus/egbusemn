// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.ResultSetMetaData;
import org.w3c.dom.Text;
import org.w3c.dom.Element;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
import oracle.xml.parser.v2.XMLDOMException;
import org.w3c.dom.Node;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;
import entity.UM_GOE_ConiC010b;
import oracle.xml.parser.v2.XMLDocument;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import common.ComStr;
import common.Log;
import oracle.xml.parser.v2.NSResolver;
import java.util.Vector;
import oracle.xml.parser.v2.XMLElement;
import entity.UM_GOE_ConiC110b;
import entity.UM_GOE_ConiC050b;
import entity.UM_GOE_ConiC030b;
import entity.UM_GOE_ConiC020b;
import beans.UM_URB_UserU010p;
import java.io.Reader;
import java.io.StringReader;
import common.Trx;
import oracle.xml.parser.v2.DOMParser;

public class UM_ADV_ConrB010c
{
    UM_ADV_ConrA020c um_adv_conra020c;
    private String[] serial_no1;
    private String[] repr_id;
    private String[] repr_nm;
    private String[] reprMail;
    private String[] reprYn;
    private String[] reprPhone;
    private String[] reprType;
    public String[] serialNo;
    public String[] identifier;
    public String[] branhName;
    public String[] areaLimit;
    public String[] 지사등록취소여부;
    public String[] postCode;
    public String[] addr1;
    public String[] addr2;
    public String[] branhtel;
    public String[] faxNum;
    private String[] ceoId;
    private String[] ceoName;
    private String[] serialNo4;
    private String[] agentId;
    private String[] agentName;
    private String[] department;
    private String[] position;
    private String[] telephone;
    private String[] E_MAIL;
    private String[] mobile;
    private String[] FAX;
    private String[] checkAgent;
    private String[] serialNo3;
    private String[] licenseNum;
    private String[] licenseCd;
    private String[] licenseNm;
    private String[] licenseStartDate;
    private String[] licenseEnDate;
    private String[] licenseAmount;
    private String[] licenseYear;
    private String[] licenseYN;
    private String[] licenseFormat;
    int currentMode;
    String saupNo;
    String transNo;
    public final int MODE_MAIN = 100;
    public final int MODE_DP = 200;
    public final int MODE_GM = 300;
    public final int MODE_JP = 400;
    public final int MODE_JP1 = 500;
    public final int MODE_JM = 800;
    public final int MODE_DDP = 900;
    public final int MODE_US = 1000;
    public final int MODE_DP1 = 1100;
    public final int MODE_IP = 1200;
    public final int MODE_BR = 1300;
    
    public UM_ADV_ConrB010c() {
        this.um_adv_conra020c = new UM_ADV_ConrA020c();
        this.serial_no1 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.repr_id = new String[] { "cc:Organization.CEO.Identifier", "Identifier.Content" };
        this.repr_nm = new String[] { "Organization.CEO.Name", "Text.Content" };
        this.reprMail = new String[] { "Email.Address.Text", "Text.Content" };
        this.reprYn = new String[] { "Representation.Ceo.Indicator", "Indicator.Content" };
        this.reprPhone = new String[] { "cc:PCS.Number.Text", "Text.Content" };
        this.reprType = new String[] { "cc:CEO.Type.Code", "Code.Content" };
        this.serialNo = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.identifier = new String[] { "cc:Organization.Identifier", "Identifier.Content" };
        this.branhName = new String[] { "cc:Company.Name", "Text.Content" };
        this.areaLimit = new String[] { "cc:Area.Limtation.Code", "Code.Content" };
        this.지사등록취소여부 = new String[] { "cc:Registration.Cancel.Indicator", "Indicator.Content" };
        this.postCode = new String[] { "cc:PostCode.Identifier", "Identifier.Content" };
        this.addr1 = new String[] { "cc:Address.Line1.Text", "Text.Content" };
        this.addr2 = new String[] { "cc:Address.Line2.Text", "Text.Content" };
        this.branhtel = new String[] { "cc:Telephone.Number.Text", "Text.Content" };
        this.faxNum = new String[] { "cc:Fax.Number.Text", "Text.Content" };
        this.ceoId = new String[] { "cc:Organization.CEO.Identifier", "Identifier.Content" };
        this.ceoName = new String[] { "cc:Organization.CEO.Name", "Text.Content" };
        this.serialNo4 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.agentId = new String[] { "cc:Person.Identifier", "Identifier.Content" };
        this.agentName = new String[] { "cc:Employee.Name", "Text.Content" };
        this.department = new String[] { "cc:Department.Name", "Text.Content" };
        this.position = new String[] { "Employee.Title.Name", "Text.Content" };
        this.telephone = new String[] { "cc:Telephone.Number.Text", "Text.Content" };
        this.E_MAIL = new String[] { "cc:Email.Address.Text", "Text.Content" };
        this.mobile = new String[] { "PCS.Number.Text", "Text.Content" };
        this.FAX = new String[] { "cc:Fax.Number.Text", "Text.Content" };
        this.checkAgent = new String[] { "cc:BiddingAgent.Confirmation.Indicator", "Indicator.Content" };
        this.serialNo3 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.licenseNum = new String[] { "License.Number.Text", "Text.Content" };
        this.licenseCd = new String[] { "License.Code", "Code.Content" };
        this.licenseNm = new String[] { "License.Code", "Code.Name" };
        this.licenseStartDate = new String[] { "License.ValidityStart.Date", "DateTime.Content" };
        this.licenseEnDate = new String[] { "cc:License.ValidityEnd.Date", "DateTime.Content" };
        this.licenseAmount = new String[] { "Construcation.AbliltyPar.Amount", "Amount.Content" };
        this.licenseYear = new String[] { "ParRank.AmountStandardYear.Date", "DateTime.Content" };
        this.licenseYN = new String[] { "Representation.License.Indicator", "Indicator.Content" };
        this.licenseFormat = new String[] { "Representation.License.Indicator", "Indicator.Format.Text" };
    }
    
    public int saveXML2DB(final String xmlDoc, final String status, final String transNo, final String procID) throws SQLException, SAXException, IOException {
        int result = 10;
        int ilno = 1;
        boolean aflag = false;
        boolean bflag = false;
        final boolean xflag = false;
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        PreparedStatement psmt2 = null;
        PreparedStatement psmt3 = null;
        final PreparedStatement psmt4 = null;
        PreparedStatement dppsmt1 = null;
        PreparedStatement mspsmt1 = null;
        PreparedStatement gjpsmt1 = null;
        PreparedStatement idpsmt1 = null;
        PreparedStatement jppsmt1 = null;
        PreparedStatement jmpsmt1 = null;
        PreparedStatement mipsmt1 = null;
        ResultSet rs = null;
        String SQL = null;
        String query1 = null;
        String query2 = null;
        String dpquery1 = null;
        String msquery1 = null;
        String gjquery1 = null;
        String idquery1 = null;
        String jpquery1 = null;
        String jmquery1 = null;
        String miquery1 = null;
        String status_cancel = "";
        final String Upjongcode = "";
        final String Sipyongvalue = "";
        final String Sipyongyear = "";
        XMLDocument dsource = null;
        final DOMParser parser = new DOMParser();
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            con.setAutoCommit(false);
            final StringReader _Message = new StringReader(xmlDoc);
            parser.parse((Reader)_Message);
            dsource = parser.getDocument();
            System.out.println("========= Doc thong tin chung nha thau tu XML========");
            GetValues(dsource, this.um_adv_conra020c.doc_no);
            GetValues(dsource, this.um_adv_conra020c.biz_reg_no);
            GetValues(dsource, this.um_adv_conra020c.nationality);
            GetValues(dsource, this.um_adv_conra020c.nationality_nm);
            GetValues(dsource, this.um_adv_conra020c.biz_nm);
            GetValues(dsource, this.um_adv_conra020c.biz_en_nm);
            GetValues(dsource, this.um_adv_conra020c.commentcement_dt);
            GetValues(dsource, this.um_adv_conra020c.establish_dt);
            System.out.println("um_adv_conra020c.nationality====" + this.um_adv_conra020c.nationality[0]);
            System.out.println("um_adv_conra020c.nationality_nm====" + this.um_adv_conra020c.nationality_nm[0]);
            if (this.um_adv_conra020c.nationality.equals("")) {
                this.um_adv_conra020c.nationality[0] = this.um_adv_conra020c.nationality_nm[0];
            }
            String dd = "";
            String mm = "";
            String yyyy = "";
            if (this.um_adv_conra020c.commentcement_dt[0].indexOf("/") > 0) {
                dd = this.um_adv_conra020c.commentcement_dt[0].substring(0, 2);
                mm = this.um_adv_conra020c.commentcement_dt[0].substring(3, 5);
                yyyy = this.um_adv_conra020c.commentcement_dt[0].substring(6, 10);
                this.um_adv_conra020c.commentcement_dt[0] = String.valueOf(dd) + mm + yyyy;
            }
            if (this.um_adv_conra020c.establish_dt[0].indexOf("/") > 0) {
                dd = this.um_adv_conra020c.establish_dt[0].substring(0, 2);
                mm = this.um_adv_conra020c.establish_dt[0].substring(3, 5);
                yyyy = this.um_adv_conra020c.establish_dt[0].substring(6, 10);
                this.um_adv_conra020c.establish_dt[0] = String.valueOf(dd) + mm + yyyy;
            }
            GetValues(dsource, this.um_adv_conra020c.biz_cls);
            GetValues(dsource, this.um_adv_conra020c.product_cls);
            GetValues(dsource, this.um_adv_conra020c.corp_reg_no);
            GetValues(dsource, this.um_adv_conra020c.biz_cls_1);
            GetValues(dsource, this.um_adv_conra020c.biz_cls_2);
            GetValues(dsource, this.um_adv_conra020c.biz_cls_year);
            GetValues(dsource, this.um_adv_conra020c.capital);
            GetValues(dsource, this.um_adv_conra020c.employee_count_);
            GetValues(dsource, this.um_adv_conra020c.lat_settle_dt);
            GetValues(dsource, this.um_adv_conra020c.zip_cd);
            GetValues(dsource, this.um_adv_conra020c.area_cd);
            GetValues(dsource, this.um_adv_conra020c.addr);
            GetValues(dsource, this.um_adv_conra020c.addr_detail);
            GetValues(dsource, this.um_adv_conra020c.phone_no);
            GetValues(dsource, this.um_adv_conra020c.fax);
            GetValues(dsource, this.um_adv_conra020c.homepage);
            GetValues(dsource, this.um_adv_conra020c.special_good_yn);
            GetValues(dsource, this.um_adv_conra020c.contr_rela_yn);
            final UM_URB_UserU010p ctl = new UM_URB_UserU010p();
            UM_GOE_ConiC010b ett = null;
            UM_GOE_ConiC020b[] NganhNgheDB = (UM_GOE_ConiC020b[])null;
            UM_GOE_ConiC030b[] LanhDaoDB = (UM_GOE_ConiC030b[])null;
            UM_GOE_ConiC050b[] DaiDienDB = (UM_GOE_ConiC050b[])null;
            UM_GOE_ConiC110b[] ett2 = (UM_GOE_ConiC110b[])null;
            ett = ctl.select_main(this.um_adv_conra020c.biz_reg_no[0]);
            NganhNgheDB = ctl.select_MS(this.um_adv_conra020c.biz_reg_no[0]);
            LanhDaoDB = ctl.select_DP(this.um_adv_conra020c.biz_reg_no[0]);
            DaiDienDB = ctl.select_ID(this.um_adv_conra020c.biz_reg_no[0]);
            ett2 = ctl.select_BR(this.um_adv_conra020c.biz_reg_no[0]);
            XMLElement xemt = null;
            xemt = (XMLElement)dsource.getDocumentElement();
            if (status.equals("1")) {
                status_cancel = this.getJMCancelCheck(transNo, con);
                if (status_cancel.equals("4")) {
                    result = 66;
                    return result;
                }
                final Vector msv = new Vector(1, 1);
                UM_ADV_ConrA030c[] NganhNgheXML = (UM_ADV_ConrA030c[])null;
                final NodeList msNodeList = dsource.selectNodes("/gb:Gsacid/LicenList/LicenItem", (NSResolver)xemt);
                final int NganhNgheXmlCount = msNodeList.getLength();
                for (int i = 0; i < NganhNgheXmlCount - 1; ++i) {
                    final UM_ADV_ConrA030c ms = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no3, i);
                        GetValues(dsource, this.um_adv_conra020c.license_cd, i);
                        GetValues(dsource, this.um_adv_conra020c.license_nm, i);
                        GetValues(dsource, this.um_adv_conra020c.license_no, i);
                        GetValues(dsource, this.um_adv_conra020c.license_issued_dt, i);
                        GetValues(dsource, this.um_adv_conra020c.license_expiry_dt, i);
                        if (this.um_adv_conra020c.license_issued_dt[0].indexOf("/") > 0) {
                            dd = this.um_adv_conra020c.license_issued_dt[0].substring(0, 2);
                            mm = this.um_adv_conra020c.license_issued_dt[0].substring(3, 5);
                            yyyy = this.um_adv_conra020c.license_issued_dt[0].substring(6, 10);
                            this.um_adv_conra020c.license_issued_dt[0] = String.valueOf(dd) + mm + yyyy;
                        }
                        if (this.um_adv_conra020c.license_expiry_dt[0].indexOf("/") > 0) {
                            dd = this.um_adv_conra020c.license_expiry_dt[0].substring(0, 2);
                            mm = this.um_adv_conra020c.license_expiry_dt[0].substring(3, 5);
                            yyyy = this.um_adv_conra020c.license_expiry_dt[0].substring(6, 10);
                            this.um_adv_conra020c.license_expiry_dt[0] = String.valueOf(dd) + mm + yyyy;
                        }
                        GetValues(dsource, this.um_adv_conra020c.const_abil_eval_amt, i);
                        GetValues(dsource, this.um_adv_conra020c.eval_std_year, i);
                        GetValues(dsource, this.um_adv_conra020c.mast_license_yn, i);
                        ms.serial_3 = this.um_adv_conra020c.serial_no3[0];
                        ms.licencse_cd = this.um_adv_conra020c.license_cd[0];
                        ms.license_nm = this.um_adv_conra020c.license_nm[0];
                        ms.license_no = this.um_adv_conra020c.license_no[0];
                        ms.issued_dt = this.um_adv_conra020c.license_issued_dt[0];
                        ms.expiry_dt = this.um_adv_conra020c.license_expiry_dt[0];
                        ms.const_abil_eval_amt = this.um_adv_conra020c.const_abil_eval_amt[0];
                        ms.eval_std_year = this.um_adv_conra020c.eval_std_year[0];
                        ms.mast_yn = this.um_adv_conra020c.mast_license_yn[0];
                        msv.addElement(ms);
                    }
                    catch (NullPointerException ane) {
                        msv.addElement(ms);
                        break;
                    }
                }
                NganhNgheXML = new UM_ADV_ConrA030c[msv.size()];
                msv.copyInto(NganhNgheXML);
                final String upmu = this.um_adv_conra020c.biz_cls[0];
                final String jejoGubun = "";
                query1 = " SELECT PERMIT_BRANCH FROM UM_USER WHERE USER_ID=?";
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex2) {}
                }
                psmt2 = con.prepareStatement(query1);
                psmt2.setString(1, isStrNull(procID));
                rs = psmt2.executeQuery();
                String permit_branch = "";
                if (rs.next()) {
                    permit_branch = rs.getString(1);
                }
                psmt2.clearParameters();
                query2 = " UPDATE UM_SUPPLIER_ENTER_MAST SET  DOC_NO='" + this.um_adv_conra020c.doc_no[0] + "',NATIONALITY = '" + this.um_adv_conra020c.nationality[0] + "', BIZ_NM = '" + isStrNull(this.um_adv_conra020c.biz_nm[0]) + "' , BIZ_EN_NM = '" + isStrNull(this.um_adv_conra020c.biz_en_nm[0]) + "' , " + " COMMENCEMENT_DT = to_date('" + isStrNull(this.um_adv_conra020c.commentcement_dt[0]) + "','DDMMYYYY') , ESTABLISH_DT = to_date('" + this.um_adv_conra020c.commentcement_dt[0] + "','DDMMYYYY'), BIZ_CLS = '" + this.um_adv_conra020c.biz_cls[0] + "', PRODUCT_CLS = '1', " + " CORP_REG_NO = '" + isStrNull(this.um_adv_conra020c.corp_reg_no[0]) + "' , BIZ_CLS1 = '" + isStrNull(this.um_adv_conra020c.biz_cls_1[0]) + "', BIZ_CLS_YEAR = '0000', CAPITAL = '" + this.um_adv_conra020c.capital[0] + "' , " + " EMPLOYEE_COUNT = '" + this.um_adv_conra020c.employee_count_[0] + "' , LAST_SETTLE_DT = '0000-00', " + " ZIP_CD = '" + isStrNull(this.um_adv_conra020c.zip_cd[0]) + "' , AREA_CD = '" + isStrNull(this.um_adv_conra020c.area_cd[0]) + "' , ADDR = '" + isStrNull(this.um_adv_conra020c.addr[0]) + "' , " + " DETAIL_ADDR = '" + isStrNull(this.um_adv_conra020c.addr_detail[0]) + "' , PHONE_NO = '" + isStrNull(this.um_adv_conra020c.phone_no[0]) + "' , FAX = '" + isStrNull(this.um_adv_conra020c.fax[0]) + "', WEBSITE = '" + isStrNull(this.um_adv_conra020c.homepage[0]) + "' , SPECAIL_GOODS_YN = '" + isStrNull(this.um_adv_conra020c.special_good_yn[0]) + "', " + " UPDATE_DT = SYSDATE, MANAGER_ID = '" + isStrNull(procID) + "', PERMIT_BRANCH = '" + isStrNull(permit_branch) + "', BID_ATTEND_QUALIFY_YN = null ,CONTR_RELA_YN='" + isStrNull(this.um_adv_conra020c.contr_rela_yn[0]) + "'" + " WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                if (psmt3 != null) {
                    try {
                        psmt3.close();
                    }
                    catch (Exception ex3) {}
                }
                psmt3 = con.prepareStatement(query2);
                System.out.println(query2);
                psmt3.executeUpdate();
                psmt3.clearParameters();
                System.out.println("=================== Cap nhat xong UM_SUPPLIER_ENTER_MAST ");
                this.um_adv_conra020c.doc_no[0] = isStrNull(this.um_adv_conra020c.doc_no[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.doc_no);
                this.um_adv_conra020c.nationality[0] = isStrNull(this.um_adv_conra020c.nationality[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.nationality);
                this.replacePutValues(dsource, this.um_adv_conra020c.doc_no);
                this.um_adv_conra020c.biz_nm[0] = isStrNull(this.um_adv_conra020c.biz_nm[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_nm);
                this.um_adv_conra020c.biz_en_nm[0] = isStrNull(this.um_adv_conra020c.biz_en_nm[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_en_nm);
                this.um_adv_conra020c.commentcement_dt[0] = isStrNull(this.um_adv_conra020c.commentcement_dt[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.commentcement_dt);
                this.um_adv_conra020c.establish_dt[0] = isStrNull(this.um_adv_conra020c.establish_dt[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.establish_dt);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_cls);
                this.um_adv_conra020c.product_cls[0] = isStrNull(jejoGubun);
                this.replacePutValues(dsource, this.um_adv_conra020c.product_cls);
                this.um_adv_conra020c.corp_reg_no[0] = isStrNull(this.um_adv_conra020c.corp_reg_no[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.corp_reg_no);
                this.um_adv_conra020c.biz_cls_1[0] = isStrNull(this.um_adv_conra020c.biz_cls_1[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_cls_1);
                this.um_adv_conra020c.biz_cls_2[0] = isStrNull(this.um_adv_conra020c.biz_cls_2[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_cls_2);
                this.um_adv_conra020c.biz_cls_year[0] = isStrNull(this.um_adv_conra020c.biz_cls_year[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_cls_year);
                if ("".equals(this.um_adv_conra020c.capital[0])) {
                    this.um_adv_conra020c.capital[0] = "0";
                }
                else {
                    this.um_adv_conra020c.capital[0] = this.um_adv_conra020c.capital[0];
                }
                this.replacePutValues(dsource, this.um_adv_conra020c.capital);
                this.um_adv_conra020c.employee_count_[0] = isStrNull(this.um_adv_conra020c.employee_count_[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.employee_count_);
                this.um_adv_conra020c.lat_settle_dt[0] = isStrNull(this.um_adv_conra020c.lat_settle_dt[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.lat_settle_dt);
                this.um_adv_conra020c.zip_cd[0] = isStrNull(this.um_adv_conra020c.zip_cd[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.zip_cd);
                this.um_adv_conra020c.area_cd[0] = isStrNull(this.um_adv_conra020c.area_cd[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.area_cd);
                this.um_adv_conra020c.addr[0] = isStrNull(this.um_adv_conra020c.addr[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.addr);
                this.um_adv_conra020c.addr_detail[0] = isStrNull(this.um_adv_conra020c.addr_detail[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.addr_detail);
                this.um_adv_conra020c.phone_no[0] = isStrNull(this.um_adv_conra020c.phone_no[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.phone_no);
                this.um_adv_conra020c.fax[0] = isStrNull(this.um_adv_conra020c.fax[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.fax);
                this.um_adv_conra020c.homepage[0] = isStrNull(this.um_adv_conra020c.homepage[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.homepage);
                this.um_adv_conra020c.special_good_yn[0] = isStrNull(this.um_adv_conra020c.special_good_yn[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.special_good_yn);
                this.um_adv_conra020c.permit_branch[0] = permit_branch;
                this.replacePutValues(dsource, this.um_adv_conra020c.permit_branch);
                this.um_adv_conra020c.contr_rela_yn[0] = isStrNull(this.um_adv_conra020c.contr_rela_yn[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.contr_rela_yn);
                final Vector dpv = new Vector(1, 1);
                UM_ADV_ConrA030c[] LanhDaoXML = (UM_ADV_ConrA030c[])null;
                final NodeList dpNodeList = dsource.selectNodes("/gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem", (NSResolver)xemt);
                final int LanhDaoXmlCount = dpNodeList.getLength();
                System.out.println("=====> So nguoi lanh dao: " + LanhDaoXmlCount);
                int dpi = -1;
                for (int j = 0; j < LanhDaoXmlCount - 1; ++j) {
                    final UM_ADV_ConrA030c dp = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no_1, j);
                        GetValues(dsource, this.um_adv_conra020c.rerp_ident_no, j);
                        GetValues(dsource, this.um_adv_conra020c.repr_nm, j);
                        GetValues(dsource, this.um_adv_conra020c.repr_mail, j);
                        GetValues(dsource, this.um_adv_conra020c.mast_repr_yn, j);
                        GetValues(dsource, this.um_adv_conra020c.repr_mobile, j);
                        GetValues(dsource, this.um_adv_conra020c.repr_cls, j);
                        dp.serial_no1 = this.um_adv_conra020c.serial_no_1[0];
                        dp.repr_ident_no = this.um_adv_conra020c.rerp_ident_no[0];
                        dp.repr_nm = this.um_adv_conra020c.repr_nm[0];
                        dp.repr_email = this.um_adv_conra020c.repr_mail[0];
                        dp.mast_repr_yn = this.um_adv_conra020c.mast_repr_yn[0];
                        dp.repr_mobile = this.um_adv_conra020c.repr_mobile[0];
                        dp.repr_cls = this.um_adv_conra020c.repr_cls[0];
                        if ("Y".equals(dp.mast_repr_yn)) {
                            dpi = j;
                        }
                        dpv.addElement(dp);
                    }
                    catch (NullPointerException ane2) {
                        dpv.addElement(dp);
                        break;
                    }
                }
                LanhDaoXML = new UM_ADV_ConrA030c[dpv.size()];
                dpv.copyInto(LanhDaoXML);
                final Vector dpv2 = new Vector(1, 1);
                UM_ADV_ConrA030c[] ChiNhanhXML = (UM_ADV_ConrA030c[])null;
                final NodeList brNodeList = dsource.selectNodes("/gb:Gsacid/BranchList/BranchItem", (NSResolver)xemt);
                final int ChiNhanhXMLCount = brNodeList.getLength();
                final int bri = -1;
                for (int k = 0; k < ChiNhanhXMLCount - 1; ++k) {
                    final UM_ADV_ConrA030c br = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.branch_serial_no, k);
                        GetValues(dsource, this.um_adv_conra020c.branch_biz_reg_no, k);
                        GetValues(dsource, this.um_adv_conra020c.branch_biz_nm, k);
                        GetValues(dsource, this.um_adv_conra020c.branch_ared_cd, k);
                        GetValues(dsource, this.um_adv_conra020c.branch_area_nm, k);
                        GetValues(dsource, this.um_adv_conra020c.branch_zip_cd, k);
                        GetValues(dsource, this.um_adv_conra020c.branch_add, k);
                        GetValues(dsource, this.um_adv_conra020c.branch_add2, k);
                        GetValues(dsource, this.um_adv_conra020c.branch_phone, k);
                        GetValues(dsource, this.um_adv_conra020c.branch_fax, k);
                        GetValues(dsource, this.um_adv_conra020c.branch_repr_ident_no, k);
                        GetValues(dsource, this.um_adv_conra020c.branch_rept_nm, k);
                        br.branch_serial_no = this.um_adv_conra020c.branch_serial_no[0];
                        br.branch_biz_reg_no = this.um_adv_conra020c.branch_biz_reg_no[0];
                        br.branch_biz_nm = this.um_adv_conra020c.branch_biz_nm[0];
                        br.branch_area_cd = this.um_adv_conra020c.branch_ared_cd[0];
                        br.branch_area_nm = this.um_adv_conra020c.branch_area_nm[0];
                        br.branch_zip_cd = this.um_adv_conra020c.branch_zip_cd[0];
                        br.branch_add = this.um_adv_conra020c.branch_add[0];
                        br.branch_add2 = this.um_adv_conra020c.branch_add2[0];
                        br.branch_phone = this.um_adv_conra020c.branch_phone[0];
                        br.branch_fax = this.um_adv_conra020c.branch_fax[0];
                        br.branch_repr_ident_no = this.um_adv_conra020c.branch_repr_ident_no[0];
                        br.branch_repr_nm = this.um_adv_conra020c.branch_rept_nm[0];
                        dpv2.addElement(br);
                    }
                    catch (NullPointerException ane3) {
                        dpv2.addElement(br);
                        break;
                    }
                }
                ChiNhanhXML = new UM_ADV_ConrA030c[dpv2.size()];
                dpv2.copyInto(ChiNhanhXML);
                final Vector dpv3 = new Vector(1, 1);
                UM_ADV_ConrA030c[] DaiDienXML = (UM_ADV_ConrA030c[])null;
                final NodeList idNodeList = dsource.selectNodes("/gb:Gsacid/RepList/RepItem", (NSResolver)xemt);
                final int DaiDienXmlCount = idNodeList.getLength();
                for (int l = 0; l < DaiDienXmlCount - 1; ++l) {
                    final UM_ADV_ConrA030c dp2 = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no4, l);
                        GetValues(dsource, this.um_adv_conra020c.ident_no, l);
                        GetValues(dsource, this.um_adv_conra020c.nm, l);
                        GetValues(dsource, this.um_adv_conra020c.depart, l);
                        GetValues(dsource, this.um_adv_conra020c.position, l);
                        GetValues(dsource, this.um_adv_conra020c.agent_phone, l);
                        GetValues(dsource, this.um_adv_conra020c.E_MAIL, l);
                        GetValues(dsource, this.um_adv_conra020c.mobile, l);
                        GetValues(dsource, this.um_adv_conra020c.FAX, l);
                        GetValues(dsource, this.um_adv_conra020c.agent_yn, l);
                        dp2.serialNo4 = this.um_adv_conra020c.serial_no4[0];
                        dp2.identNo = this.um_adv_conra020c.ident_no[0];
                        dp2.agentNm = this.um_adv_conra020c.nm[0];
                        dp2.depart = this.um_adv_conra020c.depart[0];
                        dp2.position = this.um_adv_conra020c.position[0];
                        dp2.agent_phone = this.um_adv_conra020c.agent_phone[0];
                        dp2.E_MAIL = this.um_adv_conra020c.E_MAIL[0];
                        dp2.mobile = this.um_adv_conra020c.mobile[0];
                        dp2.FAX = this.um_adv_conra020c.FAX[0];
                        dp2.agent_yn = this.um_adv_conra020c.agent_yn[0];
                        dpv3.addElement(dp2);
                    }
                    catch (NullPointerException ane4) {
                        dpv3.addElement(dp2);
                        break;
                    }
                }
                DaiDienXML = new UM_ADV_ConrA030c[dpv3.size()];
                dpv3.copyInto(DaiDienXML);
                final Vector idv = new Vector(1, 1);
                final UM_ADV_ConrA030c[] idArr = (UM_ADV_ConrA030c[])null;
                if (LanhDaoDB.length > 0) {
                    for (int m = 0; m < LanhDaoDB.length; ++m) {
                        for (int j2 = 0; j2 < LanhDaoXmlCount - 1; ++j2) {
                            if (LanhDaoDB[m].getCeoJuminNo().equals(LanhDaoXML[j2].repr_ident_no)) {
                                aflag = true;
                                if (!isStrNull((LanhDaoDB[m].getCeoName() == null) ? "" : LanhDaoDB[m].getCeoName()).equals(isStrNull(LanhDaoXML[j2].repr_nm))) {
                                    bflag = true;
                                }
                                if (!isStrNull((LanhDaoDB[m].getCeoMail() == null) ? "" : LanhDaoDB[m].getCeoMail()).equals(isStrNull(LanhDaoXML[j2].repr_email))) {
                                    bflag = true;
                                }
                                if (!isStrNull((LanhDaoDB[m].getCeoYN() == null) ? "" : LanhDaoDB[m].getCeoYN()).equals(isStrNull(LanhDaoXML[j2].mast_repr_yn))) {
                                    bflag = true;
                                }
                                if (!isStrNull((LanhDaoDB[m].getCeohphone() == null) ? "" : LanhDaoDB[m].getCeohphone()).equals(isStrNull(LanhDaoXML[j2].repr_mobile))) {
                                    bflag = true;
                                }
                                if (!isStrNull((LanhDaoDB[m].getCeoyuhyung() == null) ? "" : LanhDaoDB[m].getCeoyuhyung()).equals(isStrNull(LanhDaoXML[j2].repr_cls))) {
                                    bflag = true;
                                }
                                if (bflag) {
                                    dpquery1 = "UPDATE UM_REPR SET REPR_NM='" + isStrNull(LanhDaoXML[j2].repr_nm) + "', REPR_EMAIL='" + isStrNull(LanhDaoXML[j2].repr_email) + "', MAST_REPR_YN='" + isStrNull(LanhDaoXML[j2].mast_repr_yn) + "', UPDATE_DT=SYSDATE, REPR_MOBILE='" + isStrNull(LanhDaoXML[j2].repr_mobile) + "', REPR_CLS='" + isStrNull(LanhDaoXML[j2].repr_cls) + "'" + "WHERE BIZ_REG_NO= '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' and REPR_IDENT_NO= '" + isStrNull(LanhDaoDB[m].getCeoJuminNo()) + "'";
                                    if (dppsmt1 != null) {
                                        try {
                                            dppsmt1.close();
                                        }
                                        catch (Exception ex4) {}
                                    }
                                    System.out.println("\n" + dpquery1);
                                    dppsmt1 = con.prepareStatement(dpquery1);
                                    dppsmt1.executeQuery();
                                    bflag = false;
                                    this.um_adv_conra020c.serial_no_1[0] = isStrNull(LanhDaoXML[j2].serial_no1);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.serial_no_1, this.serial_no1, j2, 0, "U");
                                    this.um_adv_conra020c.repr_nm[0] = isStrNull(LanhDaoXML[j2].repr_nm);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_nm, this.repr_nm, j2, 0, "U");
                                    this.um_adv_conra020c.repr_mail[0] = isStrNull(LanhDaoXML[j2].repr_email);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mail, this.reprMail, j2, 0, "U");
                                    this.um_adv_conra020c.mast_repr_yn[0] = isStrNull(LanhDaoXML[j2].mast_repr_yn);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.mast_repr_yn, this.reprYn, j2, 0, "U");
                                    this.um_adv_conra020c.repr_mobile[0] = isStrNull(LanhDaoXML[j2].repr_mobile);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mobile, this.reprPhone, j2, 0, "U");
                                    this.um_adv_conra020c.repr_cls[0] = isStrNull(LanhDaoXML[j2].repr_cls);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_cls, this.reprType, j2, 0, "U");
                                    this.um_adv_conra020c.rerp_ident_no[0] = isStrNull(LanhDaoXML[j2].repr_ident_no);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.rerp_ident_no, this.repr_id, j2, 0, "U");
                                }
                            }
                        }
                        if (!aflag) {
                            dpquery1 = "DELETE FROM UM_REPR WHERE BIZ_REG_NO='" + this.um_adv_conra020c.biz_reg_no[0] + "' and REPR_IDENT_NO='" + LanhDaoDB[m].getCeoJuminNo() + "'";
                            if (dppsmt1 != null) {
                                try {
                                    dppsmt1.close();
                                }
                                catch (Exception ex5) {}
                            }
                            System.out.println("\n" + dpquery1);
                            dppsmt1 = con.prepareStatement(dpquery1);
                            dppsmt1.executeQuery();
                            this.um_adv_conra020c.serial_no_1[0] = Integer.toString(m + 1);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.serial_no_1, this.serial_no1, m, 0, "D");
                        }
                        aflag = false;
                    }
                }
                if (LanhDaoXmlCount - 1 > 0) {
                    for (int m = 0; m < LanhDaoXmlCount - 1; ++m) {
                        for (int j2 = 0; j2 < LanhDaoDB.length; ++j2) {
                            if (LanhDaoXML[m].repr_ident_no.equals(LanhDaoDB[j2].getCeoJuminNo())) {
                                aflag = true;
                            }
                        }
                        if (!aflag) {
                            dpquery1 = "INSERT INTO UM_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REG_DT, UPDATE_DT, REPR_MOBILE, REPR_CLS) VALUES ('" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "', '" + isStrNull(LanhDaoXML[m].repr_ident_no) + "','" + isStrNull(LanhDaoXML[m].repr_nm) + "','" + isStrNull(LanhDaoXML[m].repr_email) + "','" + isStrNull(LanhDaoXML[m].mast_repr_yn) + "', SYSDATE, SYSDATE,'" + isStrNull(LanhDaoXML[m].repr_mobile) + "','" + isStrNull(LanhDaoXML[m].repr_cls) + "')";
                            if (dppsmt1 != null) {
                                try {
                                    dppsmt1.close();
                                }
                                catch (Exception ex6) {}
                            }
                            System.out.println("\n" + dpquery1);
                            dppsmt1 = con.prepareStatement(dpquery1);
                            dppsmt1.executeQuery();
                            this.um_adv_conra020c.serial_no_1[0] = isStrNull(LanhDaoXML[m].serial_no1);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.serial_no_1, this.serial_no1, m, 0, "I");
                            this.um_adv_conra020c.repr_nm[0] = isStrNull(LanhDaoXML[m].repr_nm);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_nm, this.repr_nm, m, 0, "I");
                            this.um_adv_conra020c.repr_mail[0] = isStrNull(LanhDaoXML[m].repr_email);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mail, this.reprMail, m, 0, "I");
                            this.um_adv_conra020c.mast_repr_yn[0] = isStrNull(LanhDaoXML[m].mast_repr_yn);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.mast_repr_yn, this.reprYn, m, 0, "I");
                            this.um_adv_conra020c.repr_mobile[0] = isStrNull(LanhDaoXML[m].repr_mobile);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mobile, this.reprPhone, m, 0, "I");
                            this.um_adv_conra020c.repr_cls[0] = isStrNull(LanhDaoXML[m].repr_cls);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_cls, this.reprType, m, 0, "I");
                            this.um_adv_conra020c.rerp_ident_no[0] = isStrNull(LanhDaoXML[m].repr_ident_no);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.rerp_ident_no, this.repr_id, m, 0, "I");
                        }
                        aflag = false;
                    }
                }
                if (ett2.length > 0) {
                    for (int m = 0; m < ett2.length; ++m) {
                        for (int j2 = 0; j2 < ChiNhanhXMLCount - 1; ++j2) {
                            if (ett2[m].getSaupNo().equals(ChiNhanhXML[j2].branch_biz_reg_no)) {
                                aflag = true;
                                if (!isStrNull((ett2[m].getCeoName() == null) ? "" : ett2[m].getCeoName()).equals(isStrNull(ChiNhanhXML[j2].branch_repr_nm))) {
                                    bflag = true;
                                }
                                if (bflag) {
                                    dpquery1 = "  UPDATE UM_REPR SET   REPR_NM='" + isStrNull(ChiNhanhXML[j2].branch_repr_nm) + "', UPDATE_DT=SYSDATE, REPR_IDENT_NO='" + isStrNull(ChiNhanhXML[j2].branch_repr_ident_no) + "'" + "  WHERE BIZ_REG_NO='" + isStrNull(ChiNhanhXML[j2].branch_biz_reg_no) + "'";
                                    if (dppsmt1 != null) {
                                        try {
                                            dppsmt1.close();
                                        }
                                        catch (Exception ex7) {}
                                    }
                                    System.out.println("\n" + dpquery1);
                                    dppsmt1 = con.prepareStatement(dpquery1);
                                    dppsmt1.executeQuery();
                                    dpquery1 = "DELETE UM_ENTER_STATE WHERE BIZ_REG_NO='" + isStrNull(ChiNhanhXML[j2].branch_biz_reg_no) + "' and STATE_CLS='07'";
                                    if (dppsmt1 != null) {
                                        try {
                                            dppsmt1.close();
                                        }
                                        catch (Exception ex8) {}
                                    }
                                    System.out.println("\n" + dpquery1);
                                    dppsmt1 = con.prepareStatement(dpquery1);
                                    dppsmt1.executeUpdate();
                                    bflag = false;
                                    this.um_adv_conra020c.branch_serial_no[0] = isStrNull(ChiNhanhXML[j2].branch_serial_no);
                                    this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_serial_no, this.serialNo, j2, 0, "U");
                                    this.um_adv_conra020c.branch_rept_nm[0] = isStrNull(ChiNhanhXML[j2].branch_repr_nm);
                                    this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_rept_nm, this.ceoName, j2, 0, "U");
                                    this.um_adv_conra020c.branch_repr_ident_no[0] = isStrNull(ChiNhanhXML[j2].branch_repr_ident_no);
                                    this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_repr_ident_no, this.ceoId, j2, 0, "U");
                                }
                            }
                        }
                        for (int j2 = 0; j2 < ChiNhanhXMLCount - 1; ++j2) {
                            if (ett2[m].getSaupNo().equals(ChiNhanhXML[j2].branch_biz_reg_no)) {
                                aflag = true;
                                if (!isStrNull((ett2[m].getSangho() == null) ? "" : ett2[m].getSangho()).equals(isStrNull(ChiNhanhXML[j2].branch_biz_nm))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett2[m].getLocalCode() == null) ? "" : ett2[m].getLocalCode()).equals(isStrNull(ChiNhanhXML[j2].branch_area_cd))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett2[m].getZipCode() == null) ? "" : ett2[m].getZipCode()).equals(isStrNull(ChiNhanhXML[j2].branch_zip_cd))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett2[m].getAddr() == null) ? "" : ett2[m].getAddr()).equals(isStrNull(ChiNhanhXML[j2].branch_add))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett2[m].getRestAddr() == null) ? "" : ett2[m].getRestAddr()).equals(isStrNull(ChiNhanhXML[j2].branch_add2))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett2[m].getTel() == null) ? "" : ett2[m].getTel()).equals(isStrNull(ChiNhanhXML[j2].branch_phone))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett2[m].getFax() == null) ? "" : ett2[m].getFax()).equals(isStrNull(ChiNhanhXML[j2].branch_fax))) {
                                    bflag = true;
                                }
                                if (bflag) {
                                    Log.debug("지사업체 업데이트 : " + isStrNull(ChiNhanhXML[j2].branch_biz_reg_no));
                                    query2 = " UPDATE UM_SUPPLIER_ENTER_MAST SET  BIZ_NM = '" + isStrNull(ChiNhanhXML[j2].branch_biz_nm) + "', ZIP_CD = '" + isStrNull(ChiNhanhXML[j2].branch_zip_cd) + "', AREA_CD = '" + isStrNull(ChiNhanhXML[j2].branch_area_cd) + "', ADDR = '" + isStrNull(ChiNhanhXML[j2].branch_add) + "', DETAIL_ADDR = '" + isStrNull(ChiNhanhXML[j2].branch_add2) + "', " + " PHONE_NO = '" + isStrNull(ChiNhanhXML[j2].branch_phone) + "', FAX = '" + isStrNull(ChiNhanhXML[j2].branch_fax) + "', " + " UPDATE_DT = SYSDATE, MANAGER_ID = '" + isStrNull(procID) + "', PERMIT_BRANCH = '" + isStrNull(permit_branch) + "', BID_ATTEND_QUALIFY_YN = null " + " WHERE BIZ_REG_NO = '" + isStrNull(ChiNhanhXML[j2].branch_biz_reg_no) + "'";
                                    if (psmt3 != null) {
                                        try {
                                            psmt3.close();
                                        }
                                        catch (Exception ex9) {}
                                    }
                                    System.out.println("\n" + query2);
                                    psmt3 = con.prepareStatement(query2);
                                    psmt3.executeUpdate();
                                    psmt3.clearParameters();
                                    dpquery1 = "DELETE UM_ENTER_STATE WHERE BIZ_REG_NO='" + isStrNull(ChiNhanhXML[j2].branch_biz_reg_no) + "' and STATE_CLS='07'";
                                    if (dppsmt1 != null) {
                                        try {
                                            dppsmt1.close();
                                        }
                                        catch (Exception ex10) {}
                                    }
                                    System.out.println("\n" + dpquery1);
                                    dppsmt1 = con.prepareStatement(dpquery1);
                                    dppsmt1.executeUpdate();
                                    bflag = false;
                                    this.um_adv_conra020c.branch_serial_no[0] = isStrNull(ChiNhanhXML[j2].branch_serial_no);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_serial_no, this.serialNo, j2, 0, "U");
                                    this.um_adv_conra020c.branch_biz_nm[0] = isStrNull(ChiNhanhXML[j2].branch_biz_nm);
                                    this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_biz_nm, this.branhName, j2, 0, "U");
                                    this.um_adv_conra020c.branch_zip_cd[0] = isStrNull(ChiNhanhXML[j2].branch_zip_cd);
                                    this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_zip_cd, this.postCode, j2, 0, "U");
                                    this.um_adv_conra020c.branch_ared_cd[0] = isStrNull(LanhDaoXML[j2].branch_area_cd);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_ared_cd, this.areaLimit, j2, 0, "U");
                                    this.um_adv_conra020c.branch_add[0] = isStrNull(ChiNhanhXML[j2].branch_add);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_add, this.addr1, j2, 0, "U");
                                    this.um_adv_conra020c.branch_add2[0] = isStrNull(ChiNhanhXML[j2].branch_add2);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_add2, this.addr2, j2, 0, "U");
                                    this.um_adv_conra020c.branch_phone[0] = isStrNull(ChiNhanhXML[j2].branch_phone);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_phone, this.branhtel, j2, 0, "U");
                                    this.um_adv_conra020c.branch_fax[0] = isStrNull(ChiNhanhXML[j2].branch_fax);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_fax, this.faxNum, j2, 0, "U");
                                }
                            }
                        }
                        if (!aflag) {
                            Log.debug("등록취소 인서트  : " + isStrNull(ett2[m].getSaupNo()));
                            dpquery1 = "INSERT INTO UM_ENTER_STATE (BIZ_REG_NO, RAISED_DT, START_DT, STATE_CLS, REMARK, MANAGER_ID, PROCESS_DT) VALUES ('" + isStrNull(ett2[m].getSaupNo()) + "', SYSDATE, SYSDATE,  '07', ' ', 'APPROVAL', SYSDATE)";
                            if (dppsmt1 != null) {
                                try {
                                    dppsmt1.close();
                                }
                                catch (Exception ex11) {}
                            }
                            System.out.println("\n" + dpquery1);
                            dppsmt1 = con.prepareStatement(dpquery1);
                            dppsmt1.executeUpdate();
                        }
                        aflag = false;
                    }
                }
                if (ChiNhanhXMLCount - 1 > 0) {
                    for (int m = 0; m < ChiNhanhXMLCount - 1; ++m) {
                        for (int j2 = 0; j2 < ett2.length; ++j2) {
                            if (ChiNhanhXML[m].branch_biz_reg_no.equals(ett2[j2].getSaupNo())) {
                                aflag = true;
                            }
                        }
                        if (!aflag) {
                            dpquery1 = "INSERT INTO UM_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, MAST_REPR_YN, REG_DT, UPDATE_DT) VALUES ('" + isStrNull(ChiNhanhXML[m].branch_biz_reg_no) + "','" + isStrNull(ChiNhanhXML[m].branch_repr_ident_no) + "','" + isStrNull(ChiNhanhXML[m].branch_repr_nm) + "','Y', SYSDATE, SYSDATE)";
                            if (dppsmt1 != null) {
                                try {
                                    dppsmt1.close();
                                }
                                catch (Exception ex12) {}
                            }
                            System.out.println("\n" + dpquery1);
                            dppsmt1 = con.prepareStatement(dpquery1);
                            dppsmt1.executeQuery();
                            this.um_adv_conra020c.branch_serial_no[0] = isStrNull(ChiNhanhXML[m].branch_serial_no);
                            final int seq_num = isIntNull(ChiNhanhXML[m].branch_serial_no);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_serial_no, this.serialNo, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_rept_nm[0] = isStrNull(ChiNhanhXML[m].branch_repr_nm);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_rept_nm, this.ceoName, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_repr_ident_no[0] = isStrNull(ChiNhanhXML[m].branch_repr_ident_no);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_repr_ident_no, this.ceoId, seq_num, 0, "I");
                        }
                        aflag = false;
                    }
                }
                if (ChiNhanhXMLCount - 1 > 0) {
                    for (int m = 0; m < ChiNhanhXMLCount - 1; ++m) {
                        for (int j2 = 0; j2 < ett2.length; ++j2) {
                            if (ChiNhanhXML[m].branch_biz_reg_no.equals(ett2[j2].getSaupNo())) {
                                aflag = true;
                            }
                        }
                        if (!aflag) {
                            Log.debug("지사업체 인서트 : " + isStrNull(ChiNhanhXML[m].branch_biz_reg_no));
                            query2 = " INSERT INTO UM_SUPPLIER_ENTER_MAST (  BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM,  COMMENCEMENT_DT, ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS,  CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL,  EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR,  DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECIAL_GOODS_YN,  REG_DT, UPDATE_DT, REPR_BIZ_APPROVE_YN, MANAGER_ID,  PERMIT_BRANCH, INITIAL_INTITU_NM,HEADQUARTER_YN  ) VALUES ( '" + isStrNull(ChiNhanhXML[m].branch_biz_reg_no) + "','" + isStrNull(this.um_adv_conra020c.nationality[0]) + "','" + isStrNull(ChiNhanhXML[m].branch_biz_nm) + "','" + isStrNull(this.um_adv_conra020c.biz_en_nm[0]) + "'," + " to_date('" + this.um_adv_conra020c.commentcement_dt[0] + "','DDMMYYYY'), to_date('" + this.um_adv_conra020c.commentcement_dt[0] + "','DDMMYYYY'),'" + isStrNull(this.um_adv_conra020c.biz_cls[0]) + "','" + isStrNull(jejoGubun) + "'," + "'" + isStrNull(this.um_adv_conra020c.corp_reg_no[0]) + "','" + isStrNull(this.um_adv_conra020c.biz_cls_1[0]) + "','" + isStrNull(this.um_adv_conra020c.biz_cls_2[0]) + "','0000','" + this.um_adv_conra020c.capital[0] + "'," + "'" + this.um_adv_conra020c.employee_count_[0] + "','0000-00','" + isStrNull(ChiNhanhXML[m].branch_zip_cd) + "','" + isStrNull(ChiNhanhXML[m].branch_area_cd) + "','" + isStrNull(ChiNhanhXML[m].branch_add) + "'," + "'" + isStrNull(ChiNhanhXML[m].branch_add2) + "','" + isStrNull(ChiNhanhXML[m].branch_phone) + "','" + isStrNull(ChiNhanhXML[m].branch_fax) + "','" + isStrNull(this.um_adv_conra020c.homepage[0]) + "','" + isStrNull(this.um_adv_conra020c.special_good_yn[0]) + "'," + " SYSDATE, SYSDATE, 'Y','" + isStrNull(procID) + "'," + "'" + isStrNull(permit_branch) + "','-------','N'" + " ) ";
                            psmt3 = con.prepareStatement(query2);
                            System.out.println("\n" + query2);
                            psmt3.executeUpdate();
                            psmt3.clearParameters();
                            this.um_adv_conra020c.branch_serial_no[0] = isStrNull(ChiNhanhXML[m].branch_serial_no);
                            final int seq_num = isIntNull(ChiNhanhXML[m].branch_serial_no);
                            this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_serial_no, this.serialNo, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_biz_nm[0] = isStrNull(ChiNhanhXML[m].branch_biz_nm);
                            this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_biz_nm, this.branhName, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_zip_cd[0] = isStrNull(ChiNhanhXML[m].branch_zip_cd);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_zip_cd, this.postCode, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_ared_cd[0] = isStrNull(ChiNhanhXML[m].branch_area_cd);
                            this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_ared_cd, this.areaLimit, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_add[0] = isStrNull(ChiNhanhXML[m].branch_add);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_add, this.addr1, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_add2[0] = isStrNull(ChiNhanhXML[m].branch_add2);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_add2, this.addr2, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_phone[0] = isStrNull(ChiNhanhXML[m].branch_phone);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_phone, this.branhtel, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_fax[0] = isStrNull(ChiNhanhXML[m].branch_fax);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_fax, this.faxNum, seq_num, 0, "I");
                        }
                        aflag = false;
                    }
                }
                Log.debug("지사정보 변경 끝");
                if (DaiDienDB.length > 0) {
                    for (int m = 0; m < DaiDienDB.length; ++m) {
                        for (int j2 = 0; j2 < DaiDienXmlCount - 1; ++j2) {
                            if (DaiDienDB[m].getJuminNo().equals(DaiDienXML[j2].identNo)) {
                                aflag = true;
                                if (!isStrNull((DaiDienDB[m].getJobPart() == null) ? "" : DaiDienDB[m].getJobPart()).equals(isStrNull(DaiDienXML[j2].depart))) {
                                    bflag = true;
                                }
                                if (!isStrNull((DaiDienDB[m].getDutyName() == null) ? "" : DaiDienDB[m].getDutyName()).equals(isStrNull(DaiDienXML[j2].position))) {
                                    bflag = true;
                                }
                                if (!isStrNull((DaiDienDB[m].getName() == null) ? "" : DaiDienDB[m].getName()).equals(isStrNull(DaiDienXML[j2].agentNm))) {
                                    bflag = true;
                                }
                                if (!isStrNull((DaiDienDB[m].getTel() == null) ? "" : DaiDienDB[m].getTel()).equals(isStrNull(DaiDienXML[j2].agent_phone))) {
                                    bflag = true;
                                }
                                if (!isStrNull((DaiDienDB[m].getFax() == null) ? "" : DaiDienDB[m].getFax()).equals(isStrNull(DaiDienXML[j2].FAX))) {
                                    bflag = true;
                                }
                                if (!isStrNull((DaiDienDB[m].getMail() == null) ? "" : DaiDienDB[m].getMail()).equals(isStrNull(DaiDienXML[j2].E_MAIL))) {
                                    bflag = true;
                                }
                                if (!isStrNull((DaiDienDB[m].getHandphone() == null) ? "" : DaiDienDB[m].getHandphone()).equals(isStrNull(DaiDienXML[j2].mobile))) {
                                    bflag = true;
                                }
                                if (!isStrNull((DaiDienDB[m].getIpchalYN() == null) ? "" : DaiDienDB[m].getIpchalYN()).equals(isStrNull(DaiDienXML[j2].agent_yn))) {
                                    bflag = true;
                                }
                                if (bflag) {
                                    idquery1 = "UPDATE UM_BID_AGENT SET DEPART='" + isStrNull(DaiDienXML[j2].depart) + "', POSITION='" + isStrNull(DaiDienXML[j2].position) + "', NM='" + isStrNull(DaiDienXML[j2].agentNm) + "', PHONE_NO='" + isStrNull(DaiDienXML[j2].agent_phone) + "', FAX='" + isStrNull(DaiDienXML[j2].FAX) + "'," + "EMAIL='" + isStrNull(DaiDienXML[j2].E_MAIL) + "', MOBILE='" + isStrNull(DaiDienXML[j2].mobile) + "',UPDATE_DT=SYSDATE, BIDDING_AGENT_YN='" + isStrNull(DaiDienXML[j2].agent_yn) + "' " + "WHERE BIZ_REG_NO='" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' and IDENT_NO='" + isStrNull(DaiDienDB[m].getJuminNo()) + "'";
                                    if (idpsmt1 != null) {
                                        try {
                                            idpsmt1.close();
                                        }
                                        catch (Exception ex13) {}
                                    }
                                    System.out.println("\n" + idquery1);
                                    idpsmt1 = con.prepareStatement(idquery1);
                                    idpsmt1.executeQuery();
                                    idpsmt1.clearParameters();
                                    bflag = false;
                                    this.um_adv_conra020c.serial_no4[0] = isStrNull(DaiDienXML[j2].serialNo4);
                                    this.um_adv_conra020c.depart[0] = isStrNull(DaiDienXML[j2].depart);
                                    this.um_adv_conra020c.position[0] = isStrNull(DaiDienXML[j2].position);
                                    this.um_adv_conra020c.nm[0] = isStrNull(DaiDienXML[j2].agentNm);
                                    this.um_adv_conra020c.agent_phone[0] = isStrNull(DaiDienXML[j2].agent_phone);
                                    this.um_adv_conra020c.ident_no[0] = isStrNull(DaiDienDB[m].getJuminNo());
                                    this.um_adv_conra020c.E_MAIL[0] = isStrNull(DaiDienXML[j2].E_MAIL);
                                    this.um_adv_conra020c.FAX[0] = isStrNull(DaiDienXML[j2].FAX);
                                    this.um_adv_conra020c.mobile[0] = isStrNull(DaiDienXML[j2].mobile);
                                    this.um_adv_conra020c.agent_yn[0] = isStrNull(DaiDienXML[j2].agent_yn);
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.serial_no4, this.serialNo4, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.depart, this.department, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.position, this.position, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.nm, this.agentName, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.agent_phone, this.telephone, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.ident_no, this.agentId, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.E_MAIL, this.E_MAIL, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.FAX, this.FAX, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.mobile, this.mobile, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.agent_yn, this.checkAgent, j2, 0, "U");
                                }
                            }
                        }
                        if (!aflag) {
                            idquery1 = "DELETE FROM UM_BID_AGENT WHERE BIZ_REG_NO='" + this.um_adv_conra020c.biz_reg_no[0] + "' and IDENT_NO='" + DaiDienDB[m].getJuminNo() + "'";
                            if (idpsmt1 != null) {
                                try {
                                    idpsmt1.close();
                                }
                                catch (Exception ex14) {}
                            }
                            idpsmt1 = con.prepareStatement(idquery1);
                            System.out.println("\n" + idquery1);
                            idpsmt1.executeQuery();
                            idpsmt1.clearParameters();
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.serial_no4, this.serialNo4, m, 0, "D");
                        }
                        aflag = false;
                    }
                }
                if (DaiDienXmlCount - 1 > 0) {
                    for (int m = 0; m < DaiDienXmlCount - 1; ++m) {
                        for (int j2 = 0; j2 < DaiDienDB.length; ++j2) {
                            if (DaiDienXML[m].identNo.equals(DaiDienDB[j2].getJuminNo())) {
                                aflag = true;
                            }
                        }
                        if (!aflag) {
                            idquery1 = " INSERT INTO UM_BID_AGENT (  BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION,  PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT, BIDDING_AGENT_YN  ) VALUES ( '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "','" + isStrNull(DaiDienXML[m].identNo) + "','" + isStrNull(DaiDienXML[m].agentNm) + "','" + isStrNull(DaiDienXML[m].depart) + "','" + isStrNull(DaiDienXML[m].position) + "'," + "'" + isStrNull(DaiDienXML[m].agent_phone) + "','" + isStrNull(DaiDienXML[m].E_MAIL) + "','" + isStrNull(DaiDienXML[m].mobile) + "','" + isStrNull(DaiDienXML[m].FAX) + "', SYSDATE, SYSDATE,'" + isStrNull(DaiDienXML[m].agent_yn) + "'" + " ) ";
                            if (idpsmt1 != null) {
                                try {
                                    idpsmt1.close();
                                }
                                catch (Exception ex15) {}
                            }
                            System.out.println("\n" + idquery1);
                            idpsmt1 = con.prepareStatement(idquery1);
                            idpsmt1.executeUpdate();
                            idpsmt1.clearParameters();
                            this.um_adv_conra020c.serial_no4[0] = isStrNull(DaiDienXML[m].serialNo4);
                            this.um_adv_conra020c.depart[0] = isStrNull(DaiDienXML[m].depart);
                            this.um_adv_conra020c.position[0] = isStrNull(DaiDienXML[m].position);
                            this.um_adv_conra020c.nm[0] = isStrNull(DaiDienXML[m].agentNm);
                            this.um_adv_conra020c.agent_phone[0] = isStrNull(DaiDienXML[m].agent_phone);
                            this.um_adv_conra020c.ident_no[0] = isStrNull(DaiDienXML[m].identNo);
                            this.um_adv_conra020c.E_MAIL[0] = isStrNull(DaiDienXML[m].E_MAIL);
                            this.um_adv_conra020c.FAX[0] = isStrNull(DaiDienXML[m].FAX);
                            this.um_adv_conra020c.mobile[0] = isStrNull(DaiDienXML[m].mobile);
                            this.um_adv_conra020c.agent_yn[0] = isStrNull(DaiDienXML[m].agent_yn);
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.serial_no4, this.serialNo4, m, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.depart, this.department, m, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.position, this.position, m, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.nm, this.agentName, m, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.agent_phone, this.telephone, m, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.ident_no, this.agentId, m, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.E_MAIL, this.E_MAIL, m, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.FAX, this.FAX, m, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.mobile, this.mobile, m, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.agent_yn, this.checkAgent, m, 0, "I");
                        }
                        aflag = false;
                    }
                }
                System.out.println("=============msCount = " + NganhNgheXmlCount);
                System.out.println("==========msArr " + NganhNgheXML.length);
                System.out.println("==========ett2 " + NganhNgheDB.length);
                if (NganhNgheDB.length > 0) {
                    for (int m = 0; m < NganhNgheDB.length; ++m) {
                        for (int j2 = 0; j2 < NganhNgheXmlCount - 1; ++j2) {
                            if (NganhNgheDB[m].getLicenseCode().equals(NganhNgheXML[j2].licencse_cd)) {
                                aflag = true;
                                if (!isStrNull((NganhNgheDB[m].getLicenseNo() == null) ? "" : NganhNgheDB[m].getLicenseNo()).equals(isStrNull(NganhNgheXML[j2].license_no))) {
                                    bflag = true;
                                }
                                if (!isStrNull((NganhNgheDB[m].getLicenseBeginDate() == null) ? "" : (String.valueOf(NganhNgheDB[m].getLicenseBeginDate().substring(0, 4)) + NganhNgheDB[m].getLicenseBeginDate().substring(5, 7) + NganhNgheDB[m].getLicenseBeginDate().substring(8, 10))).equals(isStrNull(NganhNgheXML[j2].issued_dt))) {
                                    bflag = true;
                                }
                                if (!isStrNull((NganhNgheDB[m].getLicenseEndDate() == null) ? "" : (String.valueOf(NganhNgheDB[m].getLicenseEndDate().substring(0, 4)) + NganhNgheDB[m].getLicenseEndDate().substring(5, 7) + NganhNgheDB[m].getLicenseEndDate().substring(8, 10))).equals(isStrNull(NganhNgheXML[j2].expiry_dt))) {
                                    bflag = true;
                                }
                                if (NganhNgheDB[m].getSigongAccount() != Long.parseLong(isStrNull(ComStr.replace(NganhNgheXML[j2].const_abil_eval_amt, ",", "")).equals("") ? "0" : isStrNull(ComStr.replace(NganhNgheXML[j2].const_abil_eval_amt, ",", "")))) {
                                    bflag = true;
                                }
                                if (!isStrNull((NganhNgheDB[m].getPeonggaYear() == null) ? "0" : NganhNgheDB[m].getPeonggaYear()).equals(isStrNull(NganhNgheXML[j2].eval_std_year))) {
                                    bflag = true;
                                }
                                if (!isStrNull((NganhNgheDB[m].getDpLicenseYN() == null) ? "" : NganhNgheDB[m].getDpLicenseYN()).equals(isStrNull(NganhNgheXML[j2].mast_yn))) {
                                    bflag = true;
                                }
                                if (bflag) {
                                    msquery1 = "UPDATE um_enter_std_cls SET STD_CLS_NO='" + isStrNull(NganhNgheXML[j2].license_no) + "', ISSUED_DT= to_date('" + NganhNgheXML[j2].issued_dt + "','DDMMYYYY') , STD_CLS_EXPIRY_DT=to_date('" + NganhNgheXML[j2].expiry_dt + "','DDMMYYYY')," + " CONST_ABIL_EVAL_AMT='" + NganhNgheXML[j2].const_abil_eval_amt + "', EVAL_STD_YEAR='" + isStrNull(NganhNgheXML[j2].eval_std_year) + "',ASSOC_UPDATE_DT=sysdate, " + "MAST_STD_CLS_YN='" + isStrNull(NganhNgheXML[j2].mast_yn) + "', UPDATE_DT=SYSDATE WHERE BIZ_REG_NO='" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' AND STD_CLS_CD='" + NganhNgheDB[m].getLicenseCode() + "'";
                                    if (mspsmt1 != null) {
                                        try {
                                            mspsmt1.close();
                                        }
                                        catch (Exception ex16) {}
                                    }
                                    System.out.println("\n" + msquery1);
                                    mspsmt1 = con.prepareStatement(msquery1);
                                    mspsmt1.executeUpdate();
                                    bflag = false;
                                    this.um_adv_conra020c.serial_no3[0] = isStrNull(NganhNgheXML[j2].serial_3);
                                    final int seq_num2 = isIntNull(NganhNgheXML[j2].serial_3);
                                    this.um_adv_conra020c.license_no[0] = isStrNull(NganhNgheXML[j2].license_no);
                                    this.um_adv_conra020c.license_cd[0] = isStrNull(NganhNgheDB[m].getLicenseCode());
                                    this.um_adv_conra020c.license_nm[0] = isStrNull(NganhNgheXML[j2].license_nm);
                                    this.um_adv_conra020c.license_issued_dt[0] = isStrNull(NganhNgheXML[j2].issued_dt);
                                    this.um_adv_conra020c.license_expiry_dt[0] = isStrNull(NganhNgheXML[j2].expiry_dt);
                                    if (Sipyongvalue != null || !Sipyongvalue.equals("")) {
                                        this.um_adv_conra020c.const_abil_eval_amt[0] = (Upjongcode.equals(isStrNull(NganhNgheXML[j2].licencse_cd)) ? Sipyongvalue : ComStr.replace(NganhNgheXML[j2].const_abil_eval_amt, ",", ""));
                                        this.um_adv_conra020c.eval_std_year[0] = (Upjongcode.equals(isStrNull(NganhNgheXML[j2].licencse_cd)) ? Sipyongyear : isStrNull(NganhNgheXML[j2].eval_std_year));
                                    }
                                    else {
                                        this.um_adv_conra020c.const_abil_eval_amt[0] = ComStr.replace(NganhNgheXML[j2].const_abil_eval_amt, ",", "");
                                        this.um_adv_conra020c.eval_std_year[0] = isStrNull(NganhNgheXML[j2].eval_std_year);
                                    }
                                    this.um_adv_conra020c.mast_license_yn[0] = isStrNull(NganhNgheXML[j2].mast_yn);
                                    this.um_adv_conra020c.mast_license_fm[0] = "";
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.serial_no3, this.serialNo3, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_no, this.licenseNum, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_cd, this.licenseCd, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_nm, this.licenseNm, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_issued_dt, this.licenseStartDate, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_expiry_dt, this.licenseEnDate, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.const_abil_eval_amt, this.licenseAmount, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.eval_std_year, this.licenseYear, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_yn, this.licenseYN, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_fm, this.licenseFormat, j2, 0, "U");
                                }
                            }
                        }
                        if (!aflag) {
                            msquery1 = "DELETE FROM um_enter_std_cls WHERE BIZ_REG_NO=? AND STD_CLS_CD=?";
                            if (mspsmt1 != null) {
                                try {
                                    mspsmt1.close();
                                }
                                catch (Exception ex17) {}
                            }
                            System.out.println("\n" + msquery1);
                            mspsmt1 = con.prepareStatement(msquery1);
                            mspsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                            mspsmt1.setString(2, NganhNgheDB[m].getLicenseCode());
                            mspsmt1.executeUpdate();
                            this.um_adv_conra020c.serial_no3[0] = Integer.toString(m + 1);
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.serial_no3, this.serialNo3, m, 0, "D");
                        }
                        aflag = false;
                    }
                }
                if (NganhNgheXmlCount - 1 > 0) {
                    for (int m = 0; m < NganhNgheXmlCount - 1; ++m) {
                        for (int j2 = 0; j2 < NganhNgheDB.length; ++j2) {
                            if (NganhNgheXML[m].licencse_cd.equals(NganhNgheDB[j2].getLicenseCode())) {
                                aflag = true;
                            }
                        }
                        if (!aflag) {
                            msquery1 = "INSERT INTO um_enter_std_cls (SERIAL_NO, BIZ_REG_NO, STD_CLS_CD, STD_CLS_NO, ISSUED_DT, STD_CLS_EXPIRY_DT, CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR, ASSOC_UPDATE_DT, MAST_STD_CLS_YN, REG_DT, UPDATE_DT) VALUES ('" + NganhNgheXML[m].serial_3 + "','" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "','" + isStrNull(NganhNgheXML[m].licencse_cd) + "','" + isStrNull(NganhNgheXML[m].license_no) + "'," + " to_date('" + NganhNgheXML[m].issued_dt + "','DDMMYYYY'),to_date('" + NganhNgheXML[m].expiry_dt + "','DDMMYYYY'),'" + NganhNgheXML[m].const_abil_eval_amt + "'," + "'" + isStrNull(NganhNgheXML[m].eval_std_year) + "',to_date('','yyyy-mm-dd hh24:mi:ss'),'" + isStrNull(NganhNgheXML[m].mast_yn) + "',SYSDATE, SYSDATE) ";
                            if (mspsmt1 != null) {
                                try {
                                    mspsmt1.close();
                                }
                                catch (Exception ex18) {}
                            }
                            System.out.println("\n" + msquery1);
                            mspsmt1 = con.prepareStatement(msquery1);
                            mspsmt1.executeUpdate();
                            this.um_adv_conra020c.serial_no3[0] = isStrNull(NganhNgheXML[m].serial_3);
                            this.um_adv_conra020c.license_no[0] = isStrNull(NganhNgheXML[m].license_no);
                            this.um_adv_conra020c.license_cd[0] = isStrNull(NganhNgheXML[m].licencse_cd);
                            this.um_adv_conra020c.license_nm[0] = isStrNull(NganhNgheXML[m].license_nm);
                            this.um_adv_conra020c.license_issued_dt[0] = isStrNull(NganhNgheXML[m].issued_dt);
                            this.um_adv_conra020c.license_expiry_dt[0] = isStrNull(NganhNgheXML[m].expiry_dt);
                            if (Sipyongvalue != null || !Sipyongvalue.equals("")) {
                                this.um_adv_conra020c.const_abil_eval_amt[0] = (Upjongcode.equals(isStrNull(NganhNgheXML[m].licencse_cd)) ? Sipyongvalue : ComStr.replace(NganhNgheXML[m].const_abil_eval_amt, ",", ""));
                                this.um_adv_conra020c.eval_std_year[0] = (Upjongcode.equals(isStrNull(NganhNgheXML[m].licencse_cd)) ? Sipyongyear : isStrNull(NganhNgheXML[m].eval_std_year));
                            }
                            else {
                                this.um_adv_conra020c.const_abil_eval_amt[0] = ComStr.replace(NganhNgheXML[m].const_abil_eval_amt, ",", "");
                                this.um_adv_conra020c.eval_std_year[0] = isStrNull(NganhNgheXML[m].eval_std_year);
                            }
                            this.um_adv_conra020c.mast_license_yn[0] = isStrNull(NganhNgheXML[m].mast_yn);
                            this.um_adv_conra020c.mast_license_fm[0] = "";
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.serial_no3, this.serialNo3, m, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_no, this.licenseNum, m, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_cd, this.licenseCd, m, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_nm, this.licenseNm, m, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_issued_dt, this.licenseStartDate, m, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_expiry_dt, this.licenseEnDate, m, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.const_abil_eval_amt, this.licenseAmount, m, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.eval_std_year, this.licenseYear, m, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_yn, this.licenseYN, m, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_fm, this.licenseFormat, m, 0, "I");
                        }
                        aflag = false;
                    }
                }
                Log.debug("Changed the end of license");
                final String[] MData = this.getMainData();
                miquery1 = " INSERT INTO UM_SUPPLIER_ENTER_MAST_HIST (  BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM,  COMMENCEMENT_DT, ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS,  CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL,  EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR,  DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECIAL_GOODS_YN,  REG_DT, UPDATE_DT, REPR_BIZ_APPROVE_YN, MANAGER_ID, MAST_GOOD_CLS_NO, MAST_REPR_NM, PERMIT_BRANCH  ) VALUES ( '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "','" + isStrNull(this.um_adv_conra020c.nationality[0]) + "','" + isStrNull(this.um_adv_conra020c.biz_nm[0]) + "','" + isStrNull(this.um_adv_conra020c.biz_en_nm[0]) + "'," + " to_date('" + isStrNull(this.um_adv_conra020c.commentcement_dt[0]) + "','DDMMYYYY'), to_date('" + isStrNull(this.um_adv_conra020c.commentcement_dt[0]) + "','DDMMYYYY'), '" + this.um_adv_conra020c.biz_cls[0] + "', '1', " + "'" + isStrNull(this.um_adv_conra020c.corp_reg_no[0]) + "','" + isStrNull(MData[2]) + "',' " + isStrNull(this.um_adv_conra020c.biz_cls_2[0]) + "','0000','" + this.um_adv_conra020c.capital[0] + "'," + "'" + this.um_adv_conra020c.employee_count_[0] + "','0000-00','" + isStrNull(this.um_adv_conra020c.zip_cd[0]) + "','" + isStrNull(this.um_adv_conra020c.area_cd[0]) + "','" + isStrNull(this.um_adv_conra020c.addr[0]) + "'," + "'" + isStrNull(this.um_adv_conra020c.addr_detail[0]) + "','" + isStrNull(this.um_adv_conra020c.phone_no[0]) + "','" + isStrNull(this.um_adv_conra020c.fax[0]) + "','" + isStrNull(this.um_adv_conra020c.homepage[0]) + "','" + isStrNull(this.um_adv_conra020c.special_good_yn[0]) + "'," + " to_date('" + isStrNull(MData[4]) + "','yyyy-mm-dd'), SYSDATE, 'Y','" + isStrNull(procID) + "'," + "' ', ' ', '" + isStrNull(permit_branch) + "'" + " ) ";
                if (mipsmt1 != null) {
                    try {
                        mipsmt1.close();
                    }
                    catch (Exception ex19) {}
                }
                System.out.println("\n" + miquery1);
                mipsmt1 = con.prepareStatement(miquery1);
                mipsmt1.executeUpdate();
                mipsmt1.clearParameters();
                Log.debug("마스터변경이력 변경 끝");
                GetValues(dsource, this.um_adv_conra020c.progress_st);
                GetValues(dsource, this.um_adv_conra020c.manage_reason);
                GetValues(dsource, this.um_adv_conra020c.biz_nm);
                GetValues(dsource, this.um_adv_conra020c.repr_nm);
                GetValues(dsource, this.um_adv_conra020c.zip_cd);
                GetValues(dsource, this.um_adv_conra020c.addr);
                GetValues(dsource, this.um_adv_conra020c.addr_detail);
                GetValues(dsource, this.um_adv_conra020c.phone_no);
                GetValues(dsource, this.um_adv_conra020c.homepage);
                GetValues(dsource, this.um_adv_conra020c.permit_branch);
                GetValues(dsource, this.um_adv_conra020c.transmit_no);
                if (this.um_adv_conra020c.repr_nm.length > 0) {
                    Log.debug("um_adv_conra020c.대표자명.length::" + this.um_adv_conra020c.repr_nm.length + "::");
                    for (int i2 = 0; i2 < this.um_adv_conra020c.repr_nm.length; ++i2) {
                        Log.debug("um_adv_conra020c.대표자명[" + i2 + "]" + isStrNull(this.um_adv_conra020c.repr_nm[i2]) + "::");
                    }
                }
                jmquery1 = " UPDATE UM_EDOC_STATE SET  PROCESS_ST = '" + isStrNull(status) + "',PROCESS_RSON = '" + isStrNull(this.um_adv_conra020c.manage_reason[0]) + "', COM_NM = '" + isStrNull(this.um_adv_conra020c.biz_nm[0]) + "', REPR_NM = '" + isStrNull(this.um_adv_conra020c.repr_nm[0]) + "', ZIP_CD = '" + isStrNull(this.um_adv_conra020c.zip_cd[0]) + "'," + " ADDR = '" + isStrNull(this.um_adv_conra020c.addr[0]) + "', DETAIL_ADDR = '" + isStrNull(this.um_adv_conra020c.addr_detail[0]) + "', PHONE_NO = '" + isStrNull(this.um_adv_conra020c.phone_no[0]) + "', WEBSITE = '" + isStrNull(this.um_adv_conra020c.homepage[0]) + "', PERMIT_BRANCH = '" + permit_branch + "', " + " PROCESS_DT = SYSDATE " + " WHERE TRANS_NO = '" + transNo + "' " + " AND MOD_CLS = '2' ";
                if (jmpsmt1 != null) {
                    try {
                        jmpsmt1.close();
                    }
                    catch (Exception ex20) {}
                }
                System.out.println("\n" + jmquery1);
                jmpsmt1 = con.prepareStatement(jmquery1);
                jmpsmt1.executeUpdate();
                jmpsmt1.clearParameters();
                this.um_adv_conra020c.progress_st[0] = isStrNull(status);
                this.replacePutValues(dsource, this.um_adv_conra020c.progress_st);
                this.um_adv_conra020c.manage_reason[0] = isStrNull(this.um_adv_conra020c.manage_reason[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.manage_reason);
                this.um_adv_conra020c.biz_nm[0] = isStrNull(this.um_adv_conra020c.biz_nm[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_nm);
                this.um_adv_conra020c.repr_nm[0] = isStrNull(this.um_adv_conra020c.repr_nm[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.repr_nm);
                this.um_adv_conra020c.zip_cd[0] = isStrNull(this.um_adv_conra020c.zip_cd[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.zip_cd);
                this.um_adv_conra020c.addr[0] = isStrNull(this.um_adv_conra020c.addr[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.addr);
                this.um_adv_conra020c.addr_detail[0] = isStrNull(this.um_adv_conra020c.addr_detail[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.addr_detail);
                this.um_adv_conra020c.phone_no[0] = isStrNull(this.um_adv_conra020c.phone_no[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.phone_no);
                this.um_adv_conra020c.homepage[0] = isStrNull(this.um_adv_conra020c.homepage[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.homepage);
                this.um_adv_conra020c.permit_branch[0] = isStrNull(permit_branch);
                this.replacePutValues(dsource, this.um_adv_conra020c.permit_branch);
                final String[] MainData = this.getMainData();
                final String[][] DPData = this.getDPData();
                final String[][] DDPData = this.getDDPData();
                final String[][] GMData = this.getGMData();
                System.out.println("======== getGMData=: " + GMData.length);
                final String[][] IPData = this.getIPData();
                final String[][] BRData = this.getBRData();
                if (!((MainData[0] == null) ? "" : MainData[0]).equals(this.um_adv_conra020c.biz_nm[0])) {
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", MainData[0], this.um_adv_conra020c.biz_nm[0], "Tên nhà thầu", procID, con);
                    ++ilno;
                }
                if (!((MainData[1] == null) ? "" : MainData[1]).equals(upmu)) {
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", MainData[1], upmu, "Phân loại nghiệp vụ", procID, con);
                    ++ilno;
                }
                if (!((MainData[2] == null) ? "" : MainData[2]).equals(this.um_adv_conra020c.biz_cls_1[0])) {
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", MainData[2], this.um_adv_conra020c.biz_cls_1[0], "Phân loại doanh nghiệp", procID, con);
                    ++ilno;
                }
                if (!((MainData[3] == null) ? "" : MainData[3]).equals(this.um_adv_conra020c.addr[0]) || !((MainData[5] == null) ? "" : MainData[5]).equals(this.um_adv_conra020c.addr_detail[0])) {
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", String.valueOf(MainData[3]) + " " + MainData[5], String.valueOf(this.um_adv_conra020c.addr[0]) + " " + this.um_adv_conra020c.addr_detail[0], "Địa chỉ", procID, con);
                    ++ilno;
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", MainData[7], this.um_adv_conra020c.area_cd[0], "Mã tỉnh thành", procID, con);
                    ++ilno;
                }
                if (!((MainData[6] == null) ? "" : MainData[6]).equals(this.um_adv_conra020c.capital[0])) {
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", MainData[6], this.um_adv_conra020c.capital[0], "Vốn", procID, con);
                    ++ilno;
                }
                String beComment1 = "";
                String beComment2 = "";
                String beComment3 = "";
                String beComment4 = "";
                for (int i3 = 0; i3 < LanhDaoXmlCount - 1; ++i3) {
                    int x = 0;
                    int x2 = 0;
                    final int x3 = 0;
                    for (int j3 = 0; j3 < DPData.length; ++j3) {
                        if (LanhDaoXML[i3].repr_ident_no.equals(DPData[j3][0])) {
                            x = 1;
                            if (!LanhDaoXML[i3].repr_nm.equals(DPData[j3][1])) {
                                beComment1 = DPData[j3][1];
                                beComment2 = DPData[j3][0];
                                x2 = 1;
                                break;
                            }
                        }
                    }
                    if (x == 0 && !LanhDaoXML[i3].repr_ident_no.equals("")) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "5", "I", "", String.valueOf(LanhDaoXML[i3].repr_nm) + " : " + LanhDaoXML[i3].repr_ident_no, "Người đại diện", procID, con);
                        ++ilno;
                    }
                    else if (x == 1 && x2 == 1) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "5", "U", String.valueOf(beComment1) + " : " + beComment2, String.valueOf(LanhDaoXML[i3].repr_nm) + " : " + LanhDaoXML[i3].repr_ident_no, "Người đại diện", procID, con);
                        ++ilno;
                    }
                }
                for (int i3 = 0; i3 < DPData.length; ++i3) {
                    int x = 0;
                    for (int j4 = 0; j4 < LanhDaoXmlCount - 1; ++j4) {
                        if (LanhDaoXML[j4].repr_ident_no.equals(DPData[i3][0])) {
                            x = 1;
                            break;
                        }
                    }
                    if (x == 0) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "5", "D", String.valueOf(DPData[i3][1]) + " : " + DPData[i3][0], "", "Lãnh đạo", procID, con);
                        ++ilno;
                    }
                }
                for (int i3 = 0; i3 < LanhDaoXmlCount - 1; ++i3) {
                    int x = 0;
                    int x2 = 0;
                    for (int j5 = 0; j5 < DDPData.length; ++j5) {
                        if (LanhDaoXML[i3].repr_ident_no.equals(DDPData[j5][0])) {
                            x = 1;
                            if (!LanhDaoXML[i3].mast_repr_yn.equals(DDPData[j5][2])) {
                                beComment1 = DDPData[j5][1];
                                x2 = 1;
                                break;
                            }
                        }
                    }
                    if (x == 1 && x2 == 1) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "5", "U", beComment1, LanhDaoXML[dpi].repr_nm, "Là giám đốc?", procID, con);
                        ++ilno;
                    }
                }
                for (int i3 = 0; i3 < ChiNhanhXMLCount - 1; ++i3) {
                    int x = 0;
                    int x2 = 0;
                    final int x3 = 0;
                    for (int j3 = 0; j3 < BRData.length; ++j3) {
                        if (ChiNhanhXML[i3].branch_biz_reg_no.equals(BRData[j3][0])) {
                            x = 1;
                            if (!ChiNhanhXML[i3].branch_repr_nm.equals(BRData[j3][8])) {
                                beComment1 = BRData[j3][8];
                                x2 = 1;
                                break;
                            }
                        }
                    }
                    if (x == 0 && !ChiNhanhXML[i3].branch_biz_reg_no.equals("")) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "I", "", String.valueOf(ChiNhanhXML[i3].branch_biz_reg_no) + " : " + ChiNhanhXML[i3].branch_repr_nm + " : " + ChiNhanhXML[i3].branch_repr_ident_no, "Chi nhánh", procID, con);
                        ++ilno;
                    }
                    else if (x == 1 && x2 == 1) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", beComment1, String.valueOf(ChiNhanhXML[i3].branch_repr_nm) + " : " + ChiNhanhXML[i3].branch_repr_ident_no, "Chi nhánh", procID, con);
                        ++ilno;
                    }
                }
                for (int i3 = 0; i3 < BRData.length; ++i3) {
                    int x = 0;
                    for (int j4 = 0; j4 < ChiNhanhXMLCount - 1; ++j4) {
                        if (ChiNhanhXML[j4].branch_biz_reg_no.equals(BRData[i3][0])) {
                            x = 1;
                            break;
                        }
                    }
                    if (x == 0) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "D", BRData[i3][0], "", "Chi nhánh", procID, con);
                        ++ilno;
                    }
                }
                for (int i3 = 0; i3 < DaiDienXmlCount - 1; ++i3) {
                    int x = 0;
                    int x2 = 0;
                    final int x3 = 0;
                    for (int j3 = 0; j3 < IPData.length; ++j3) {
                        if (DaiDienXML[i3].identNo.equals(IPData[j3][0])) {
                            x = 1;
                            if (!DaiDienXML[i3].agentNm.equals(IPData[j3][1])) {
                                beComment1 = IPData[j3][1];
                                beComment2 = IPData[j3][0];
                                x2 = 1;
                                break;
                            }
                        }
                    }
                    if (x == 0 && !DaiDienXML[i3].identNo.equals("")) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "6", "I", "", String.valueOf(DaiDienXML[i3].agentNm) + " : " + DaiDienXML[i3].identNo, "Người đại diện dự thầu", procID, con);
                        ++ilno;
                    }
                    else if (x == 1 && x2 == 1) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "6", "U", String.valueOf(beComment1) + " : " + beComment2, String.valueOf(DaiDienXML[i3].agentNm) + " : " + DaiDienXML[i3].identNo, "Người đại diện dự thầu", procID, con);
                        ++ilno;
                    }
                }
                for (int i3 = 0; i3 < IPData.length; ++i3) {
                    int x = 0;
                    for (int j4 = 0; j4 < DaiDienXmlCount - 1; ++j4) {
                        if (DaiDienXML[j4].identNo.equals(IPData[i3][0])) {
                            x = 1;
                            break;
                        }
                    }
                    if (x == 0) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "6", "D", String.valueOf(IPData[i3][1]) + " : " + IPData[i3][0], "", "Người đại diện dự thầu", procID, con);
                        ++ilno;
                    }
                }
                System.out.println("Insert License information");
                boolean flg = false;
                final String flg2 = "";
                for (int i4 = 0; i4 < NganhNgheXmlCount - 1; ++i4) {
                    int x4 = 0;
                    int x5 = 0;
                    int x6 = 0;
                    flg = false;
                    for (int j6 = 0; j6 < GMData.length; ++j6) {
                        if (NganhNgheXML[i4].licencse_cd.equals(GMData[j6][0])) {
                            x4 = 1;
                            if (!GMData[j6][1].equals(ComStr.replace(NganhNgheXML[i4].const_abil_eval_amt, ",", ""))) {
                                beComment1 = GMData[j6][1];
                                beComment2 = GMData[j6][0];
                                beComment3 = GMData[j6][2];
                                beComment4 = GMData[j6][3];
                                x5 = 1;
                            }
                            if (!GMData[j6][3].equals(NganhNgheXML[i4].expiry_dt)) {
                                beComment1 = GMData[j6][1];
                                beComment2 = GMData[j6][0];
                                beComment3 = GMData[j6][2];
                                beComment4 = GMData[j6][3];
                                x6 = 1;
                            }
                            if (x5 == 1) {
                                break;
                            }
                            if (x6 == 1) {
                                break;
                            }
                        }
                    }
                    flg = this.getIsOKUpJong(NganhNgheXML[i4].licencse_cd, con);
                    if (x4 == 0 && !NganhNgheXML[i4].licencse_cd.equals("")) {
                        if (!NganhNgheXML[i4].const_abil_eval_amt.equals("") && !NganhNgheXML[i4].expiry_dt.equals("")) {
                            if (flg) {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", String.valueOf(NganhNgheXML[i4].license_nm) + "(Số tiền bảo lãnh thi công : " + ComStr.replace(NganhNgheXML[i4].const_abil_eval_amt, ",", "") + ", Ngày hết hạn sử dụng : " + NganhNgheXML[i4].expiry_dt + ")", "Phân loại ngành nghề", procID, con);
                                ++ilno;
                            }
                            else {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", String.valueOf(NganhNgheXML[i4].license_nm) + "(Số tiền bảo lãnh thi công : " + ComStr.replace(NganhNgheXML[i4].const_abil_eval_amt, ",", "") + ")", "Phân loại ngành nghề", procID, con);
                                ++ilno;
                            }
                        }
                        else if (NganhNgheXML[i4].const_abil_eval_amt.equals("") && !NganhNgheXML[i4].expiry_dt.equals("")) {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", String.valueOf(NganhNgheXML[i4].license_nm) + "(Ngày hết hạn sử dụng : " + NganhNgheXML[i4].expiry_dt + ")", "Phân loại ngành nghề", procID, con);
                            ++ilno;
                        }
                        else if (!NganhNgheXML[i4].const_abil_eval_amt.equals("") && NganhNgheXML[i4].expiry_dt.equals("")) {
                            if (flg2 != null && !flg2.equals("")) {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", String.valueOf(NganhNgheXML[i4].license_nm) + "(Số tiền bảo lãnh thi công : " + flg2 + " 협회자료갱신" + ")", "Phân loại ngành nghề", procID, con);
                                ++ilno;
                            }
                            else {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", String.valueOf(NganhNgheXML[i4].license_nm) + "(Số tiền bảo lãnh thi công : " + ComStr.replace(NganhNgheXML[i4].const_abil_eval_amt, ",", "") + ")", "Phân loại ngành nghề", procID, con);
                                ++ilno;
                            }
                        }
                        else {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", NganhNgheXML[i4].license_nm, "Phân loại ngành nghề", procID, con);
                            ++ilno;
                        }
                    }
                    else if (x4 == 1 && x5 == 1 && x6 == 1) {
                        if (flg) {
                            if (flg2 != null && !flg2.equals("")) {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(Số tiền bảo lãnh thi công: " + beComment1 + ", 유효기간만료일자 : " + beComment4 + ")", String.valueOf(NganhNgheXML[i4].license_nm) + "(Số tiền bảo lãnh thi công : " + flg2 + "협회자료갱신" + ", 유효기간만료일자 : " + NganhNgheXML[i4].expiry_dt + ")", "Phân loại ngành nghề", procID, con);
                                ++ilno;
                            }
                            else {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(Số tiền bảo lãnh thi công: " + beComment1 + ", 유효기간만료일자 : " + beComment4 + ")", String.valueOf(NganhNgheXML[i4].license_nm) + "(Số tiền bảo lãnh thi công : " + ComStr.replace(NganhNgheXML[i4].const_abil_eval_amt, ",", "") + ", 유효기간만료일자 : " + NganhNgheXML[i4].expiry_dt + ")", "Phân loại ngành nghề", procID, con);
                                ++ilno;
                            }
                        }
                        else if (flg2 != null && !flg2.equals("")) {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(Số tiền bảo lãnh thi công: " + beComment1 + ")", String.valueOf(NganhNgheXML[i4].license_nm) + "(Số tiền bảo lãnh thi công : " + flg2 + "협회자료갱신" + ")", "Phân loại ngành nghề", procID, con);
                            ++ilno;
                        }
                        else {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(Số tiền bảo lãnh thi công: " + beComment1 + ")", String.valueOf(NganhNgheXML[i4].license_nm) + "(Số tiền bảo lãnh thi công : " + ComStr.replace(NganhNgheXML[i4].const_abil_eval_amt, ",", "") + ")", "Phân loại ngành nghề", procID, con);
                            ++ilno;
                        }
                    }
                    else if (x4 == 1 && x5 == 1 && x6 == 0) {
                        if (flg2 != null && !flg2.equals("")) {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(Số tiền bảo lãnh thi công: " + beComment1 + ")", String.valueOf(NganhNgheXML[i4].license_nm) + "(Số tiền bảo lãnh thi công : " + flg2 + "협회자료갱신" + ")", "Phân loại ngành nghề", procID, con);
                            ++ilno;
                        }
                        else {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(Số tiền bảo lãnh thi công: " + beComment1 + ")", String.valueOf(NganhNgheXML[i4].license_nm) + "(Số tiền bảo lãnh thi công : " + ComStr.replace(NganhNgheXML[i4].const_abil_eval_amt, ",", "") + ")", "Phân loại ngành nghề", procID, con);
                            ++ilno;
                        }
                    }
                    else if (x4 == 1 && x5 == 0 && x6 == 1) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(Ngày hết hạn : " + beComment4 + ")", String.valueOf(NganhNgheXML[i4].license_nm) + "(Ngày hết hạn sử dụng : " + NganhNgheXML[i4].expiry_dt + ")", "Phân loại ngành nghề", procID, con);
                        ++ilno;
                    }
                }
                for (int i4 = 0; i4 < GMData.length; ++i4) {
                    int x4 = 0;
                    for (int j3 = 0; j3 < NganhNgheXmlCount - 1; ++j3) {
                        if (NganhNgheXML[j3].licencse_cd.equals(GMData[i4][0])) {
                            x4 = 1;
                            break;
                        }
                    }
                    if (x4 == 0) {
                        if (flg) {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "D", String.valueOf(GMData[i4][2]) + "( Số tiền bảo lãnh thi công:" + GMData[i4][1] + ", Ngày hết hạn sử dụng: " + GMData[i4][3] + ")", "", "Phân loại ngành nghề", procID, con);
                            ++ilno;
                        }
                        else {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "D", String.valueOf(GMData[i4][2]) + "( Số tiền bảo lãnh thi công:" + GMData[i4][1] + ")", "", "Phân loại ngành nghề", procID, con);
                            ++ilno;
                        }
                    }
                }
                SQL = " SELECT COUNT(*) FROM UM_ENTER_STATE                           WHERE BIZ_REG_NO= ?\t\t\t\t\t\t\t\t\t\t\t\t AND STATE_CLS = '07' AND MANAGER_ID = 'ADMIN'                     ";
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex21) {}
                }
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (Exception ex22) {}
                }
                int stcount = 0;
                psmt = con.prepareStatement(SQL);
                psmt.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                rs = psmt.executeQuery();
                while (rs.next()) {
                    stcount = rs.getInt(1);
                }
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex23) {}
                }
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (Exception ex24) {}
                }
                if (stcount != 0) {
                    SQL = " DELETE FROM UM_ENTER_STATE\t\t\t\t\t\t                     WHERE BIZ_REG_NO= ?\t\t\t\t\t\t\t\t\t\t\t\t AND STATE_CLS = '07' AND MANAGER_ID = 'ADMIN'                     ";
                    if (psmt != null) {
                        try {
                            psmt.close();
                        }
                        catch (Exception ex25) {}
                    }
                    System.out.println("\n" + SQL);
                    psmt = con.prepareStatement(SQL);
                    psmt.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    psmt.executeUpdate();
                }
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex26) {}
                }
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (Exception ex27) {}
                }
                query1 = "\t SELECT BIZ_REG_NO FROM UM_REC_SUPPLIER_ENTER_MAST        WHERE CORP_REG_NO IN (SELECT CORP_REG_NO\t\t\t\t\t   \t\t\t\t\t\t\t\t\tFROM UM_REC_SUPPLIER_ENTER_MAST\t\t   \t\t\t\t\t\t\t\t\tWHERE BIZ_REG_NO  =?)\t\t       AND BID_ATTEND_QUALIFY_YN = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ";
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex28) {}
                }
                psmt2 = con.prepareStatement(query1);
                psmt2.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                rs = psmt2.executeQuery();
                String branchsaupNo = "";
                while (rs.next()) {
                    branchsaupNo = rs.getString(1);
                    query2 = " DELETE FROM UM_SUPPLIER_ENTER_ITEMS  WHERE BIZ_REG_NO = '" + branchsaupNo + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex29) {}
                    }
                    gjpsmt1 = con.prepareStatement(query2);
                    gjpsmt1.clearParameters();
                    dpquery1 = " INSERT INTO UM_SUPPLIER_ENTER_ITEMS(BIZ_REG_NO,GOOD_CLS_NO, PERMIT_NO, PERMIT_INSTITU, INCOME_3YEARS,\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t DIRECT_PRODUCTION_YN, MAST_GOODS_YN, REG_DT, UPDATE_DT, RESERVED_ITEM_OPTION, HAVE_RESERVED_GOODS,\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t  \t\t\t\t DIRECT_PRODUCTION_DOC, AVAIL_PERIOD_START_DT, AVAIL_PERIOD_END_DT, INDUSTRY_CLS_CD, ISSUE_INSTITU, DOC_NM)\t\t\t\t\tSELECT  '" + branchsaupNo + "',GOOD_CLS_NO, PERMIT_NO, PERMIT_INSTITU, INCOME_3YEARS,DIRECT_PRODUCTION_YN, MAST_GOODS_YN, REG_DT,\t\t" + " \t\t\t\t    UPDATE_DT, RESERVED_ITEM_OPTION, HAVE_RESERVED_GOODS,DIRECT_PRODUCTION_DOC, AVAIL_PERIOD_START_DT, AVAIL_PERIOD_END_DT,\t\t\t\t\t\t\t\t" + " \t\t\t\t    INDUSTRY_CLS_CD, ISSUE_INSTITU, DOC_NM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t\tFROM  UM_SUPPLIER_ENTER_ITEMS WHERE BIZ_REG_NO = ?\t";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex30) {}
                    }
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    dppsmt1.clearParameters();
                    query2 = " DELETE FROM um_enter_std_cls  WHERE BIZ_REG_NO = '" + branchsaupNo + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex31) {}
                    }
                    gjpsmt1 = con.prepareStatement(query2);
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                    dpquery1 = " INSERT INTO um_enter_std_cls(BIZ_REG_NO, STD_CLS_CD, STD_CLS_NO, ISSUED_DT, STD_CLS_EXPIRY_DT, CONST_ABIL_EVAL_AMT,\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t EVAL_STD_YEAR,MAST_STD_CLS_YN,REG_DT,UPDATE_DT,ASSOC_UPDATE_DT)\t\t\t\t\t \t\tSELECT  '" + branchsaupNo + "', STD_CLS_CD, STD_CLS_NO, ISSUED_DT, STD_CLS_EXPIRY_DT, CONST_ABIL_EVAL_AMT,\t\t\t\t\t\t\t\t\t\t\t\t" + " \t\t\t\t\tEVAL_STD_YEAR,MAST_STD_CLS_YN,REG_DT,UPDATE_DT,ASSOC_UPDATE_DT\t\t\t\t\t\t\t\t\t" + " \t\tFROM um_enter_std_cls WHERE BIZ_REG_NO = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex32) {}
                    }
                    System.out.println("\n" + dpquery1);
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    query2 = " DELETE FROM UM_FACTORY_INFO  WHERE BIZ_REG_NO = '" + branchsaupNo + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex33) {}
                    }
                    gjpsmt1 = con.prepareStatement(query2);
                    gjpsmt1.clearParameters();
                    dpquery1 = " INSERT INTO UM_FACTORY_INFO(BIZ_REG_NO,SERIAL_NO,FACTORY_NM,FACTORY_ZIP_CD,FACTORY_ADDR,FACTORY_ADDR2,FACTORY_PHONE_NO,\t\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t FACTORY_FAX,REG_DT,UPDATE_DT,FACTORY_RENT_YN,FACTORY_RENT_START_DT,FACTORY_RENT_END_DT) \t\t\t\t\t\t\t \t\tSELECT\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t'" + branchsaupNo + "',SERIAL_NO,FACTORY_NM,FACTORY_ZIP_CD,FACTORY_ADDR,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + " \t\tFACTORY_ADDR2,FACTORY_PHONE_NO,FACTORY_FAX,REG_DT,UPDATE_DT,FACTORY_RENT_YN,FACTORY_RENT_START_DT,FACTORY_RENT_END_DT\t\t\t\t\t\t\t" + " \t\tFROM UM_FACTORY_INFO WHERE BIZ_REG_NO = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex34) {}
                    }
                    System.out.println("\n" + dpquery1);
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    dppsmt1.clearParameters();
                    query2 = " DELETE FROM UM_BID_AGENT  WHERE BIZ_REG_NO = '" + branchsaupNo + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex35) {}
                    }
                    gjpsmt1 = con.prepareStatement(query2);
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                    dpquery1 = " INSERT INTO UM_BID_AGENT(BIZ_REG_NO,IDENT_NO,NM,DEPART,POSITION,PHONE_NO,\t\t\t\t\t\t\t\t\t\t \t  EMAIL,MOBILE,FAX,REG_DT,UPDATE_DT,BIDDING_AGENT_YN)\t\t\t\t\t\t\t\t\t\t\t\t\t \t\tSELECT\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t'" + branchsaupNo + "',IDENT_NO,NM,DEPART,POSITION,PHONE_NO,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + " \t\tEMAIL,MOBILE,FAX,REG_DT,UPDATE_DT,BIDDING_AGENT_YN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + " \t\tFROM UM_BID_AGENT WHERE BIZ_REG_NO =?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex36) {}
                    }
                    System.out.println("\n" + dpquery1);
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                }
                query1 = "\t SELECT BIZ_REG_NO FROM UM_REC_SUPPLIER_ENTER_MAST        WHERE CORP_REG_NO IN (SELECT CORP_REG_NO\t\t\t\t\t   \t\t\t\t\t\t\t\t\tFROM UM_REC_SUPPLIER_ENTER_MAST\t\t   \t\t\t\t\t\t\t\t\tWHERE BIZ_REG_NO  =?)\t\t       AND BID_ATTEND_QUALIFY_YN = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ";
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex37) {}
                }
                psmt2 = con.prepareStatement(query1);
                psmt2.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                rs = psmt2.executeQuery();
                while (rs.next()) {
                    branchsaupNo = rs.getString(1);
                    dpquery1 = " DELETE FROM UM_REC_REPR  WHERE BIZ_REG_NO = '" + branchsaupNo + "' ";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex38) {}
                    }
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    query2 = " DELETE FROM UM_REC_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = '" + branchsaupNo + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex39) {}
                    }
                    gjpsmt1 = con.prepareStatement(query2);
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                }
                query1 = "SELECT COUNT(*) FROM UM_REC_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO=? ";
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex40) {}
                }
                psmt2 = con.prepareStatement(query1);
                psmt2.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                rs = psmt2.executeQuery();
                String mainsaupNoCheck = "0";
                if (rs.next()) {
                    mainsaupNoCheck = rs.getString(1);
                }
                psmt2.clearParameters();
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex41) {}
                }
                if (!mainsaupNoCheck.equals("0")) {
                    dpquery1 = " DELETE FROM UM_REC_REPR  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex42) {}
                    }
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    gjquery1 = " DELETE FROM UM_REC_FACTORY_INFO  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex43) {}
                    }
                    gjpsmt1 = con.prepareStatement(gjquery1);
                    gjpsmt1.clearParameters();
                    jpquery1 = " DELETE FROM UM_REC_SUPPLIER_ENTER_ITEM  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (jppsmt1 != null) {
                        try {
                            jppsmt1.close();
                        }
                        catch (Exception ex44) {}
                    }
                    jppsmt1 = con.prepareStatement(jpquery1);
                    jppsmt1.clearParameters();
                    msquery1 = " DELETE FROM um_rec_enter_std_cls  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (mspsmt1 != null) {
                        try {
                            mspsmt1.close();
                        }
                        catch (Exception ex45) {}
                    }
                    mspsmt1 = con.prepareStatement(msquery1);
                    mspsmt1.executeUpdate();
                    mspsmt1.clearParameters();
                    idquery1 = " DELETE FROM UM_REC_BID_AGENT  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (idpsmt1 != null) {
                        try {
                            idpsmt1.close();
                        }
                        catch (Exception ex46) {}
                    }
                    idpsmt1 = con.prepareStatement(idquery1);
                    idpsmt1.executeUpdate();
                    idpsmt1.clearParameters();
                    query1 = " DELETE FROM UM_REC_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (psmt2 != null) {
                        try {
                            psmt2.close();
                        }
                        catch (Exception ex47) {}
                    }
                    psmt2 = con.prepareStatement(query1);
                    psmt2.executeUpdate();
                    psmt2.clearParameters();
                }
                final String year = transNo.substring(1, 5);
                final String month = transNo.substring(5, 7);
                final String day = transNo.substring(7, 9);
                final String newDocFilename = "/e-doc/PORTAL/NOTICE/" + year + "/" + month + "/" + day + "/attach/Work/" + transNo + ".0";
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final PrintWriter p = new PrintWriter(baos);
                dsource.print(p);
                String res = baos.toString();
                res = "<?xml version = '1.0' encoding = 'UTF-8'?>" + res.substring(res.indexOf("?>") + 2);
                p.close();
                baos.close();
                final int writeCnt = this.writefile(newDocFilename, res);
                if (writeCnt == 0) {
                    Log.debug("[UM_ADV_ConrB010c] Work화일(" + newDocFilename + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt);
                }
                else {
                    Log.errors("[UM_ADV_ConrB010c] Work화일(" + newDocFilename + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt);
                }
            }
            else if (status.equals("2")) {
                status_cancel = this.getJMCancelCheck(transNo, con);
                if (status_cancel.equals("4")) {
                    result = 66;
                    return result;
                }
                GetValues(dsource, this.um_adv_conra020c.progress_st);
                GetValues(dsource, this.um_adv_conra020c.manage_reason);
                GetValues(dsource, this.um_adv_conra020c.biz_nm);
                GetValues(dsource, this.um_adv_conra020c.repr_nm);
                GetValues(dsource, this.um_adv_conra020c.zip_cd);
                GetValues(dsource, this.um_adv_conra020c.addr);
                GetValues(dsource, this.um_adv_conra020c.addr_detail);
                GetValues(dsource, this.um_adv_conra020c.phone_no);
                GetValues(dsource, this.um_adv_conra020c.homepage);
                GetValues(dsource, this.um_adv_conra020c.permit_branch);
                GetValues(dsource, this.um_adv_conra020c.transmit_no);
                final String query_1 = " SELECT PERMIT_BRANCH FROM UM_USER WHERE USER_ID=?";
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex48) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex49) {}
                }
                psmt2 = con.prepareStatement(query_1);
                psmt2.setString(1, isStrNull(procID));
                rs = psmt2.executeQuery();
                String jicheong = "";
                if (rs.next()) {
                    jicheong = rs.getString(1);
                }
                psmt2.clearParameters();
                jmquery1 = " UPDATE UM_EDOC_STATE SET  PROCESS_ST = '" + isStrNull(status) + "',PROCESS_RSON = '" + isStrNull(this.um_adv_conra020c.manage_reason[0]) + "', COM_NM = '" + isStrNull(this.um_adv_conra020c.biz_nm[0]) + "', REPR_NM = '" + isStrNull(this.um_adv_conra020c.repr_nm[0]) + "', ZIP_CD = '" + isStrNull(this.um_adv_conra020c.zip_cd[0]) + "'," + " ADDR = '" + isStrNull(this.um_adv_conra020c.addr[0]) + "', DETAIL_ADDR = '" + isStrNull(this.um_adv_conra020c.addr_detail[0]) + "', PHONE_NO = '" + isStrNull(this.um_adv_conra020c.phone_no[0]) + "', WEBSITE = '" + isStrNull(this.um_adv_conra020c.homepage[0]) + "', PERMIT_BRANCH = '" + jicheong + "', " + " PROCESS_DT = SYSDATE " + " WHERE TRANS_NO = '" + transNo + "' " + " AND MOD_CLS = '2' ";
                if (jmpsmt1 != null) {
                    try {
                        jmpsmt1.close();
                    }
                    catch (Exception ex50) {}
                }
                jmpsmt1 = con.prepareStatement(jmquery1);
                jmpsmt1.executeUpdate();
                jmpsmt1.clearParameters();
                this.um_adv_conra020c.progress_st[0] = isStrNull(status);
                this.replacePutValues(dsource, this.um_adv_conra020c.progress_st);
                this.um_adv_conra020c.manage_reason[0] = isStrNull(this.um_adv_conra020c.manage_reason[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.manage_reason);
                this.um_adv_conra020c.biz_nm[0] = isStrNull(this.um_adv_conra020c.biz_nm[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_nm);
                this.um_adv_conra020c.repr_nm[0] = isStrNull(this.um_adv_conra020c.repr_nm[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.repr_nm);
                this.um_adv_conra020c.zip_cd[0] = isStrNull(this.um_adv_conra020c.zip_cd[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.zip_cd);
                this.um_adv_conra020c.addr[0] = isStrNull(this.um_adv_conra020c.addr[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.addr);
                this.um_adv_conra020c.addr_detail[0] = isStrNull(this.um_adv_conra020c.addr_detail[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.addr_detail);
                this.um_adv_conra020c.phone_no[0] = isStrNull(this.um_adv_conra020c.phone_no[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.phone_no);
                this.um_adv_conra020c.homepage[0] = isStrNull(this.um_adv_conra020c.homepage[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.homepage);
                this.um_adv_conra020c.permit_branch[0] = jicheong;
                this.replacePutValues(dsource, this.um_adv_conra020c.permit_branch);
                final String year2 = transNo.substring(1, 5);
                final String month2 = transNo.substring(5, 7);
                final String day2 = transNo.substring(7, 9);
                final String newDocFilename2 = "/e-doc/PORTAL/NOTICE/" + year2 + "/" + month2 + "/" + day2 + "/attach/Work/" + transNo + ".0";
                final ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                final PrintWriter p2 = new PrintWriter(baos2);
                dsource.print(p2);
                String res2 = baos2.toString();
                res2 = "<?xml version = '1.0' encoding = 'UTF-8'?>" + res2.substring(res2.indexOf("?>") + 2);
                p2.close();
                baos2.close();
                final int writeCnt2 = this.writefile(newDocFilename2, res2);
                if (writeCnt2 == 0) {
                    Log.debug("[UM_ADV_ConrB010c] Work화일(" + newDocFilename2 + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt2);
                }
                else {
                    Log.errors("[UM_ADV_ConrB010c] Work화일(" + newDocFilename2 + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt2);
                }
            }
            else if (status.equals("3")) {
                status_cancel = this.getJMCancelCheck(transNo, con);
                if (status_cancel.equals("4")) {
                    result = 66;
                    return result;
                }
                GetValues(dsource, this.um_adv_conra020c.progress_st);
                GetValues(dsource, this.um_adv_conra020c.manage_reason);
                GetValues(dsource, this.um_adv_conra020c.biz_nm);
                GetValues(dsource, this.um_adv_conra020c.repr_nm);
                GetValues(dsource, this.um_adv_conra020c.zip_cd);
                GetValues(dsource, this.um_adv_conra020c.addr);
                GetValues(dsource, this.um_adv_conra020c.addr_detail);
                GetValues(dsource, this.um_adv_conra020c.phone_no);
                GetValues(dsource, this.um_adv_conra020c.homepage);
                GetValues(dsource, this.um_adv_conra020c.permit_branch);
                GetValues(dsource, this.um_adv_conra020c.transmit_no);
                final String query_1 = " SELECT PERMIT_BRANCH FROM UM_USER WHERE USER_ID=?";
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex51) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex52) {}
                }
                psmt2 = con.prepareStatement(query_1);
                psmt2.setString(1, isStrNull(procID));
                rs = psmt2.executeQuery();
                String jicheong = "";
                if (rs.next()) {
                    jicheong = rs.getString(1);
                }
                psmt2.clearParameters();
                jmquery1 = " UPDATE UM_EDOC_STATE SET  PROCESS_ST = '" + isStrNull(status) + "',PROCESS_RSON = '" + isStrNull(this.um_adv_conra020c.manage_reason[0]) + "', COM_NM = '" + isStrNull(this.um_adv_conra020c.biz_nm[0]) + "', REPR_NM = '" + isStrNull(this.um_adv_conra020c.repr_nm[0]) + "', ZIP_CD = '" + isStrNull(this.um_adv_conra020c.zip_cd[0]) + "'," + " ADDR = '" + isStrNull(this.um_adv_conra020c.addr[0]) + "', DETAIL_ADDR = '" + isStrNull(this.um_adv_conra020c.addr_detail[0]) + "', PHONE_NO = '" + isStrNull(this.um_adv_conra020c.phone_no[0]) + "', WEBSITE = '" + isStrNull(this.um_adv_conra020c.homepage[0]) + "', PERMIT_BRANCH = '" + jicheong + "', " + " PROCESS_DT = SYSDATE " + " WHERE TRANS_NO = '" + transNo + "' " + " AND MOD_CLS = '2' ";
                if (jmpsmt1 != null) {
                    try {
                        jmpsmt1.close();
                    }
                    catch (Exception ex53) {}
                }
                jmpsmt1 = con.prepareStatement(jmquery1);
                jmpsmt1.executeUpdate();
                jmpsmt1.clearParameters();
                this.um_adv_conra020c.progress_st[0] = isStrNull(status);
                this.replacePutValues(dsource, this.um_adv_conra020c.progress_st);
                this.um_adv_conra020c.manage_reason[0] = isStrNull(this.um_adv_conra020c.manage_reason[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.manage_reason);
                this.um_adv_conra020c.biz_nm[0] = isStrNull(this.um_adv_conra020c.biz_nm[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_nm);
                this.um_adv_conra020c.repr_nm[0] = isStrNull(this.um_adv_conra020c.repr_nm[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.repr_nm);
                this.um_adv_conra020c.zip_cd[0] = isStrNull(this.um_adv_conra020c.zip_cd[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.zip_cd);
                this.um_adv_conra020c.addr[0] = isStrNull(this.um_adv_conra020c.addr[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.addr);
                this.um_adv_conra020c.addr_detail[0] = isStrNull(this.um_adv_conra020c.addr_detail[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.addr_detail);
                this.um_adv_conra020c.phone_no[0] = isStrNull(this.um_adv_conra020c.phone_no[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.phone_no);
                this.um_adv_conra020c.homepage[0] = isStrNull(this.um_adv_conra020c.homepage[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.homepage);
                this.um_adv_conra020c.permit_branch[0] = jicheong;
                this.replacePutValues(dsource, this.um_adv_conra020c.permit_branch);
                query1 = "SELECT COUNT(*) FROM UM_REC_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO=? ";
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
                psmt2 = con.prepareStatement(query1);
                psmt2.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                rs = psmt2.executeQuery();
                String mainsaupNoCheck2 = "0";
                if (rs.next()) {
                    mainsaupNoCheck2 = rs.getString(1);
                }
                psmt2.clearParameters();
                if (!mainsaupNoCheck2.equals("0")) {
                    dpquery1 = " DELETE FROM UM_REC_REPR  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex56) {}
                    }
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    gjquery1 = " DELETE FROM UM_REC_FACTORY_INFO  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex57) {}
                    }
                    gjpsmt1 = con.prepareStatement(gjquery1);
                    gjpsmt1.clearParameters();
                    jpquery1 = " DELETE FROM UM_REC_SUPPLIER_ENTER_ITEM  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (jppsmt1 != null) {
                        try {
                            jppsmt1.close();
                        }
                        catch (Exception ex58) {}
                    }
                    jppsmt1 = con.prepareStatement(jpquery1);
                    jppsmt1.clearParameters();
                    msquery1 = " DELETE FROM um_rec_enter_std_cls  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (mspsmt1 != null) {
                        try {
                            mspsmt1.close();
                        }
                        catch (Exception ex59) {}
                    }
                    mspsmt1 = con.prepareStatement(msquery1);
                    mspsmt1.executeUpdate();
                    mspsmt1.clearParameters();
                    idquery1 = " DELETE FROM UM_REC_BID_AGENT  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (idpsmt1 != null) {
                        try {
                            idpsmt1.close();
                        }
                        catch (Exception ex60) {}
                    }
                    idpsmt1 = con.prepareStatement(idquery1);
                    idpsmt1.executeUpdate();
                    idpsmt1.clearParameters();
                    query1 = " DELETE FROM UM_REC_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (psmt2 != null) {
                        try {
                            psmt2.close();
                        }
                        catch (Exception ex61) {}
                    }
                    psmt2 = con.prepareStatement(query1);
                    psmt2.executeUpdate();
                    psmt2.clearParameters();
                }
                final String year3 = transNo.substring(1, 5);
                final String month3 = transNo.substring(5, 7);
                final String day3 = transNo.substring(7, 9);
                final String newDocFilename3 = "/e-doc/PORTAL/NOTICE/" + year3 + "/" + month3 + "/" + day3 + "/attach/Work/" + transNo + ".0";
                final ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
                final PrintWriter p3 = new PrintWriter(baos3);
                dsource.print(p3);
                String res3 = baos3.toString();
                res3 = "<?xml version = '1.0' encoding = 'UTF-8'?>" + res3.substring(res3.indexOf("?>") + 2);
                p3.close();
                baos3.close();
                final int writeCnt3 = this.writefile(newDocFilename3, res3);
                if (writeCnt3 == 0) {
                    Log.debug("[UM_ADV_ConrB010c] Work화일(" + newDocFilename3 + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt3);
                }
                else {
                    Log.errors("[UM_ADV_ConrB010c] Work화일(" + newDocFilename3 + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt3);
                }
            }
            con.commit();
            con.setAutoCommit(true);
            System.out.println("Thuc hien phe duyet/bao luu/ tu choi thanh cong");
        }
        catch (SQLException exc) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrB010c.doPost block SQLException : Transaction Rollback간에 SQLException 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
                result = 99;
            }
            Log.debug("UM_ADV_ConrB010c.doPost block SQLException : ");
            Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
            result = 99;
        }
        catch (Exception exc2) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrB010c.doPost block Exception : Transaction Rollback간에 Exception 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
                result = 99;
            }
            Log.debug("UM_ADV_ConrB010c.doPost block Exception : ");
            Log.debug("Exception발생 사유 : " + exc2.toString());
            exc2.printStackTrace();
            result = 99;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex62) {}
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex63) {}
            }
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex64) {}
            }
            if (dppsmt1 != null) {
                try {
                    dppsmt1.close();
                }
                catch (Exception ex65) {}
            }
            if (mspsmt1 != null) {
                try {
                    mspsmt1.close();
                }
                catch (Exception ex66) {}
            }
            if (gjpsmt1 != null) {
                try {
                    gjpsmt1.close();
                }
                catch (Exception ex67) {}
            }
            if (idpsmt1 != null) {
                try {
                    idpsmt1.close();
                }
                catch (Exception ex68) {}
            }
            if (jppsmt1 != null) {
                try {
                    jppsmt1.close();
                }
                catch (Exception ex69) {}
            }
            if (jmpsmt1 != null) {
                try {
                    jmpsmt1.close();
                }
                catch (Exception ex70) {}
            }
            if (mipsmt1 != null) {
                try {
                    mipsmt1.close();
                }
                catch (Exception ex71) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex72) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex73) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex74) {}
        }
        if (psmt3 != null) {
            try {
                psmt3.close();
            }
            catch (Exception ex75) {}
        }
        if (dppsmt1 != null) {
            try {
                dppsmt1.close();
            }
            catch (Exception ex76) {}
        }
        if (mspsmt1 != null) {
            try {
                mspsmt1.close();
            }
            catch (Exception ex77) {}
        }
        if (gjpsmt1 != null) {
            try {
                gjpsmt1.close();
            }
            catch (Exception ex78) {}
        }
        if (idpsmt1 != null) {
            try {
                idpsmt1.close();
            }
            catch (Exception ex79) {}
        }
        if (jppsmt1 != null) {
            try {
                jppsmt1.close();
            }
            catch (Exception ex80) {}
        }
        if (jmpsmt1 != null) {
            try {
                jmpsmt1.close();
            }
            catch (Exception ex81) {}
        }
        if (mipsmt1 != null) {
            try {
                mipsmt1.close();
            }
            catch (Exception ex82) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex83) {}
        }
        return result;
    }
    
    public static void GetValues(final XMLDocument doc, final String[] node) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            final NodeList tmpNodeList = doc.selectNodes(node[1].trim(), (NSResolver)xemt);
            node[0] = tmpNodeList.item(0).getFirstChild().getNodeValue().trim();
        }
        catch (NullPointerException ne) {
            node[0] = "";
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrB010c.GetValues(1) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void GetValues(final XMLDocument doc, final String[] node, final int index) throws Exception {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            final NodeList tmpNodeList = doc.selectNodes(node[1].trim(), (NSResolver)xemt);
            node[0] = tmpNodeList.item(index).getFirstChild().getNodeValue().trim();
        }
        catch (NullPointerException ne) {
            node[0] = "";
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrB010c.GetValues(1,2,3) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static int isIntNull(final String value) {
        return (value.length() == 0) ? 0 : Integer.parseInt(value.trim());
    }
    
    public static String isStrNull(final String value) {
        return (value.length() == 0) ? "" : value.trim();
    }
    
    public void replacePutValues(final XMLDocument doc, final String[] node) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            final NodeList eltNodeList = doc.selectNodes(node[1].trim(), (NSResolver)xemt);
            final NodeList tmpNodeList = eltNodeList.item(0).getChildNodes();
            eltNodeList.item(0).replaceChild(doc.createTextNode(node[0]), tmpNodeList.item(0));
        }
        catch (XMLDOMException xe) {
            xe.toString();
        }
        catch (NullPointerException ne) {}
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void replacePutValues(final XMLDocument doc, final String[] node, final int index) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            final NodeList eltNodeList = doc.selectNodes(node[1].trim(), (NSResolver)xemt);
            final NodeList tmpNodeList = eltNodeList.item(index).getChildNodes();
            eltNodeList.item(index).replaceChild(doc.createTextNode(node[0]), tmpNodeList.item(index));
        }
        catch (XMLDOMException xe) {
            xe.toString();
        }
        catch (NullPointerException ne) {}
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void PutValues(final XMLDocument doc, final String[] node) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            final NodeList tmpNodeList = doc.selectNodes(node[1].trim(), (NSResolver)xemt);
            tmpNodeList.item(0).appendChild(doc.createTextNode(node[0]).cloneNode(true));
        }
        catch (NullPointerException ne) {}
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.PutValues() Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    private void PutValues(final XMLDocument doc, final String[] node, final int index) throws Exception {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            final NodeList tmpNodeList = doc.selectNodes(node[1].trim(), (NSResolver)xemt);
            tmpNodeList.item(index).appendChild(doc.createTextNode(node[0]).cloneNode(true));
        }
        catch (NullPointerException ne) {}
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrB010c.PutValues(1,2,3) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    private int writefile(final String workDocFilename, final String message) {
        try {
            PrintWriter out = null;
            final File file = new File(workDocFilename);
            if (file.exists()) {
                file.mkdirs();
            }
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(workDocFilename, false), "UTF-8"));
            out.println(message);
            out.flush();
            out.close();
            return 0;
        }
        catch (Exception e) {
            return -1;
        }
    }
    
    public void dpReplacePutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first, final String IUD) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (IUD == "D") {
                this.removeNodes(nextNode);
            }
            if (IUD == "U") {
                this.replacePutValues(doc, node);
            }
            if (IUD == "I") {
                if (index == 0) {
                    findex = 0;
                }
                else {
                    findex = index;
                }
                if (first == 1) {
                    addEle = doc.createElement("CeoItem");
                    final Node ageNode = doc.selectNodes("/gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem", (NSResolver)xemt).item(index);
                    ageNode.getParentNode().insertBefore(addEle, ageNode);
                }
                addEle = doc.createElement(nodeNameType[0]);
                tmpNodeList = doc.selectNodes("/gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
                addEle = doc.createElement(nodeNameType[1]);
                addText = doc.createTextNode(node[0]);
                addEle.appendChild(addText);
            }
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrB010c.dpReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("[" + IUD + "],[index : " + index + "] Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public void brReplacePutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first, final String IUD) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (IUD == "D") {
                this.removeNodes(nextNode);
            }
            if (IUD == "U") {
                this.replacePutValues(doc, node);
            }
            if (IUD == "I") {
                if (index == 0) {
                    findex = 0;
                }
                else {
                    findex = index;
                }
                if (first == 1) {
                    addEle = doc.createElement("BranchItem");
                    final Node ageNode = doc.selectNodes("/gb:Gsacid/BranchList/BranchItem", (NSResolver)xemt).item(index);
                    ageNode.getParentNode().insertBefore(addEle, ageNode);
                }
                addEle = doc.createElement(nodeNameType[0]);
                tmpNodeList = doc.selectNodes("/gb:Gsacid/BranchList/BranchItem", (NSResolver)xemt);
                if (nodeNameType[0] == "cc:Organization.Identifier") {
                    addEle = doc.createElement("BranchOffice.Details");
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                    addEle = doc.createElement(nodeNameType[0]);
                    nextNode = nextNode.appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Company.Name") {
                    addEle = doc.createElement("cc:Company.Name");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/BranchList/BranchItem/BranchOffice.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Area.Limtation.Code") {
                    addEle = doc.createElement("cc:Area.Limtation.Code");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/BranchList/BranchItem/BranchOffice.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Registration.Cancel.Indicator") {
                    addEle = doc.createElement("cc:Registration.Cancel.Indicator");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/BranchList/BranchItem/BranchOffice.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else {
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                addEle = doc.createElement(nodeNameType[1]);
                addText = doc.createTextNode(node[0]);
                addEle.appendChild(addText);
                nextNode.appendChild(addEle);
            }
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.brReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("Exception 발생사유 : " + nodeNameType[0]);
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public void brReplacePutValues1(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first, final String IUD) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (IUD == "D") {
                this.removeNodes(nextNode);
            }
            if (IUD == "U") {
                this.replacePutValues(doc, node);
            }
            if (IUD == "I") {
                if (index == 0) {
                    findex = 0;
                }
                else {
                    findex = index;
                }
                if (first == 1) {
                    addEle = doc.createElement("BranchItem");
                    final Node ageNode = doc.selectNodes("/gb:Gsacid/BranchList/BranchItem", (NSResolver)xemt).item(index);
                    ageNode.getParentNode().insertBefore(addEle, ageNode);
                }
                addEle = doc.createElement(nodeNameType[0]);
                tmpNodeList = doc.selectNodes("/gb:Gsacid/BranchList/BranchItem/BranchOffice.Details", (NSResolver)xemt);
                if (nodeNameType[0] == "cc:PostCode.Identifier") {
                    addEle = doc.createElement("Address.Details");
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                    addEle = doc.createElement(nodeNameType[0]);
                    nextNode = nextNode.appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Address.Line1.Text") {
                    addEle = doc.createElement("cc:Address.Line1.Text");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/Address.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Address.Line2.Text") {
                    addEle = doc.createElement("cc:Address.Line2.Text");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/Address.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Telephone.Number.Text") {
                    addEle = doc.createElement("Contact.Details");
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                    addEle = doc.createElement(nodeNameType[0]);
                    nextNode = nextNode.appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Fax.Number.Text") {
                    addEle = doc.createElement("cc:Fax.Number.Text");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/Contact.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Organization.CEO.Name") {
                    addEle = doc.createElement("CEO.Details");
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                    addEle = doc.createElement(nodeNameType[0]);
                    nextNode = nextNode.appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Organization.CEO.Identifier") {
                    addEle = doc.createElement("cc:Organization.CEO.Identifier");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/BranchList/BranchItem/BranchOffice.Details/CEO.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else {
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                addEle = doc.createElement(nodeNameType[1]);
                addText = doc.createTextNode(node[0]);
                addEle.appendChild(addText);
                nextNode.appendChild(addEle);
            }
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.brReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("Exception 발생사유 : " + nodeNameType[0]);
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public void idReplacePutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first, final String IUD) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (IUD == "D") {
                this.removeNodes(nextNode);
            }
            if (IUD == "U") {
                this.replacePutValues(doc, node, index);
            }
            if (IUD == "I") {
                if (index == 0) {
                    findex = 0;
                }
                else {
                    findex = index;
                }
                if (first == 1) {
                    addEle = doc.createElement("RepItem");
                    final Node ageNode = doc.selectNodes("/gb:Gsacid/RepList/RepItem", (NSResolver)xemt).item(index);
                    ageNode.getParentNode().insertBefore(addEle, ageNode);
                }
                addEle = doc.createElement(nodeNameType[0]);
                tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem", (NSResolver)xemt);
                if (nodeNameType[0] == "cc:Department.Name") {
                    addEle = doc.createElement("Employee.Details");
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                    addEle = doc.createElement(nodeNameType[0]);
                    nextNode = nextNode.appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Employee.Name") {
                    addEle = doc.createElement("cc:Employee.Name");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Telephone.Number.Text") {
                    addEle = doc.createElement("cc:Telephone.Number.Text");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Fax.Number.Text") {
                    addEle = doc.createElement("cc:Fax.Number.Text");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Email.Address.Text") {
                    addEle = doc.createElement("cc:Email.Address.Text");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "Employee.Title.Name") {
                    addEle = doc.createElement("Employee.Title.Name");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Person.Identifier") {
                    addEle = doc.createElement("cc:Person.Identifier");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "PCS.Number.Text") {
                    addEle = doc.createElement("PCS.Number.Text");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:BiddingAgent.Confirmation.Indicator") {
                    addEle = doc.createElement("cc:BiddingAgent.Confirmation.Indicator");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/RepList/RepItem/Employee.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else {
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                addEle = doc.createElement(nodeNameType[1]);
                addText = doc.createTextNode(node[0]);
                addEle.appendChild(addText);
            }
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrB010c.idReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("[" + IUD + "],[index : " + index + "] Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public void licenseReplacePutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first, final String IUD) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (IUD == "D") {
                this.removeNodes(nextNode);
            }
            if (IUD == "U") {
                this.replacePutValues(doc, node, index);
            }
            if (IUD == "I") {
                if (index == 0) {
                    findex = 0;
                }
                else {
                    findex = index;
                }
                if (first == 1) {
                    addEle = doc.createElement("LicenItem");
                    final Node ageNode = doc.selectNodes("/gb:Gsacid/LicenList/LicenItem", (NSResolver)xemt).item(index);
                    ageNode.getParentNode().insertBefore(addEle, ageNode);
                }
                addEle = doc.createElement(nodeNameType[0]);
                tmpNodeList = doc.selectNodes("/gb:Gsacid/LicenList/LicenItem", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
                addEle = doc.createElement(nodeNameType[1]);
                addText = doc.createTextNode(node[0]);
                addEle.appendChild(addText);
            }
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrB010c.licenseReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public void facReplacePutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first, final String IUD) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (IUD == "D") {
                this.removeNodes(nextNode);
            }
            if (IUD == "U") {
                this.replacePutValues(doc, node);
            }
            if (IUD == "I") {
                if (index == 0) {
                    findex = 0;
                }
                else {
                    findex = index;
                }
                if (first == 1) {
                    addEle = doc.createElement("FacItem");
                    final Node ageNode = doc.selectNodes("/gb:Gsacid/FacList/FacItem", (NSResolver)xemt).item(index);
                    ageNode.getParentNode().insertBefore(addEle, ageNode);
                }
                addEle = doc.createElement(nodeNameType[0]);
                tmpNodeList = doc.selectNodes("/gb:Gsacid/FacList/FacItem", (NSResolver)xemt);
                if (nodeNameType[0] == "cc:PostCode.Identifier") {
                    addEle = doc.createElement("Address.Details");
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                    addEle = doc.createElement(nodeNameType[0]);
                    nextNode = nextNode.appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Address.Line1.Text") {
                    addEle = doc.createElement("cc:Address.Line1.Text");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/FacList/FacItem/Address.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Address.Line2.Text") {
                    addEle = doc.createElement("cc:Address.Line2.Text");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/FacList/FacItem/Address.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Telephone.Number.Text") {
                    addEle = doc.createElement("Contact.Details");
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                    addEle = doc.createElement(nodeNameType[0]);
                    nextNode = nextNode.appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Fax.Number.Text") {
                    addEle = doc.createElement("cc:Fax.Number.Text");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/FacList/FacItem/Contact.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else {
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                addEle = doc.createElement(nodeNameType[1]);
                addText = doc.createTextNode(node[0]);
                addEle.appendChild(addText);
            }
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrB010c.facReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public void jpReplacePutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first, final String IUD) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (IUD == "D") {
                this.removeNodes(nextNode);
            }
            if (IUD == "U") {
                this.replacePutValues(doc, node);
            }
            if (IUD == "I") {
                if (index == 0) {
                    findex = 0;
                }
                else {
                    findex = index;
                }
                if (first == 1) {
                    addEle = doc.createElement("SupItem");
                    final Node ageNode = doc.selectNodes("/gb:Gsacid/SupList/SupItem", (NSResolver)xemt).item(index);
                    ageNode.getParentNode().insertBefore(addEle, ageNode);
                }
                addEle = doc.createElement(nodeNameType[0]);
                tmpNodeList = doc.selectNodes("/gb:Gsacid/SupList/SupItem", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
                addEle = doc.createElement(nodeNameType[1]);
                addText = doc.createTextNode(node[0]);
                addEle.appendChild(addText);
            }
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrB010c.jpReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public void maReplacePutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first, final String IUD) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (IUD == "D") {
                this.removeNodes(nextNode);
            }
            if (IUD == "U") {
                this.replacePutValues(doc, node);
            }
            if (IUD == "I") {
                if (index == 0) {
                    findex = 0;
                }
                else {
                    findex = index;
                }
                if (first == 1) {
                    addEle = doc.createElement("ManItem");
                    final Node ageNode = doc.selectNodes("/gb:Gsacid/ManList/ManItem", (NSResolver)xemt).item(index);
                    ageNode.getParentNode().insertBefore(addEle, ageNode);
                }
                addEle = doc.createElement(nodeNameType[0]);
                tmpNodeList = doc.selectNodes("/gb:Gsacid/ManList/ManItem", (NSResolver)xemt);
                nextNode = tmpNodeList.item(findex).appendChild(addEle);
                addEle = doc.createElement(nodeNameType[1]);
                addText = doc.createTextNode(node[0]);
                addEle.appendChild(addText);
            }
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrB010c.maReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public void maReplacePutValues1(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first, final String IUD) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
            if (IUD == "D") {
                this.removeNodes(nextNode);
            }
            if (IUD == "U") {
                this.replacePutValues(doc, node);
            }
            if (IUD == "I") {
                if (index == 0) {
                    findex = 0;
                }
                else {
                    findex = index;
                }
                if (first == 1) {
                    addEle = doc.createElement("ManItem");
                    final Node ageNode = doc.selectNodes("/gb:Gsacid/ManList/ManItem", (NSResolver)xemt).item(index);
                    ageNode.getParentNode().insertBefore(addEle, ageNode);
                }
                addEle = doc.createElement(nodeNameType[0]);
                tmpNodeList = doc.selectNodes("/gb:Gsacid/ManList/ManItem", (NSResolver)xemt);
                if (nodeNameType[0] == "cc:Production.Certification.Code") {
                    addEle = doc.createElement("Manufacturing.Details");
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                    addEle = doc.createElement(nodeNameType[0]);
                    nextNode = nextNode.appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Validity.Start.Date") {
                    addEle = doc.createElement("cc:Validity.Start.Date");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/ManList/ManItem/Manufacturing.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Validity.End.Date") {
                    addEle = doc.createElement("cc:Validity.End.Date");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/ManList/ManItem/Manufacturing.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Organization.Name") {
                    addEle = doc.createElement("cc:Organization.Name");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/ManList/ManItem/Manufacturing.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Certificate.Name") {
                    addEle = doc.createElement("cc:Certificate.Name");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/ManList/ManItem/Manufacturing.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:Certification.Type.Text") {
                    addEle = doc.createElement("cc:Certification.Type.Text");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/ManList/ManItem/Manufacturing.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else if (nodeNameType[0] == "cc:StandardIndustry.Classification.Code") {
                    addEle = doc.createElement("cc:StandardIndustry.Classification.Code");
                    tmpNodeList = doc.selectNodes("/gb:Gsacid/ManList/ManItem/Manufacturing.Details", (NSResolver)xemt);
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                else {
                    nextNode = tmpNodeList.item(findex).appendChild(addEle);
                }
                addEle = doc.createElement(nodeNameType[1]);
                addText = doc.createTextNode(node[0]);
                addEle.appendChild(addText);
            }
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrB010c.maReplacePutValues1(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public void removeNodes(final Node node) {
        if (node == null) {
            return;
        }
        if (node.hasChildNodes()) {
            final NodeList children = node.getChildNodes();
            if (children != null) {
                final int len = children.getLength();
                for (int i = len - 1; i >= 0; --i) {
                    this.removeNodes(children.item(i));
                }
            }
        }
        final Node parent = node.getParentNode();
        if (parent == null) {
            return;
        }
        parent.removeChild(node);
    }
    
    private boolean getIsOKUpJong(final String UpJongCode, final Connection con) {
        String ckUpJongquery = null;
        PreparedStatement ckUpJongpsmt = null;
        ResultSet rs = null;
        boolean returnValue = false;
        int Cnt = 0;
        try {
            ckUpJongquery = " SELECT COUNT(*) FROM UM_LICENSE_EXPIRED   WHERE LICENSE_EXPIRY_YN ='Y'          AND LICENSE_CD = ? ";
            ckUpJongpsmt = con.prepareStatement(ckUpJongquery);
            ckUpJongpsmt.setString(1, UpJongCode);
            rs = ckUpJongpsmt.executeQuery();
            ckUpJongpsmt.clearParameters();
            while (rs.next()) {
                Cnt = rs.getInt(1);
            }
            if (Cnt > 0) {
                returnValue = true;
            }
        }
        catch (SQLException exc) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrB010c.ckUpJong() block SQLException : Transaction Rollback간에 SQLException 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrB010c.ckUpJong() block SQLException : ");
            Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
        }
        catch (Exception exc2) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrB010c.ckUpJong() block Exception : Transaction Rollback간에 Exception 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrB010c.ckUpJong() block Exception : ");
            Log.debug("Exception발생 사유 : " + exc2.toString());
            exc2.printStackTrace();
        }
        finally {
            if (ckUpJongpsmt != null) {
                try {
                    ckUpJongpsmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (ckUpJongpsmt != null) {
            try {
                ckUpJongpsmt.close();
            }
            catch (Exception ex2) {}
        }
        return returnValue;
    }
    
    private String getIsOKSipyong(final String SaupNoCode, final String UpJongCode, final Connection con) {
        final String query1 = null;
        PreparedStatement psmt1 = null;
        ResultSet rs = null;
        String returnValue = "";
        final int Cnt = 0;
        try {
            psmt1 = con.prepareStatement(query1);
            psmt1.setString(1, SaupNoCode);
            psmt1.setString(2, UpJongCode);
            rs = psmt1.executeQuery();
            psmt1.clearParameters();
            while (rs.next()) {
                returnValue = rs.getString(1);
            }
        }
        catch (SQLException exc) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrB010c.ckUpJong() block SQLException : Transaction Rollback간에 SQLException 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrB010c.ckUpJong() block SQLException : ");
            Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
        }
        catch (Exception exc2) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrB010c.ckUpJong() block Exception : Transaction Rollback간에 Exception 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrB010c.ckUpJong() block Exception : ");
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
        }
        if (psmt1 != null) {
            try {
                psmt1.close();
            }
            catch (Exception ex2) {}
        }
        return returnValue;
    }
    
    public String getJMCancelCheck(final String transNo, Connection con) {
        String JMCancelquery = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String returnValue = "";
        boolean isCon = false;
        Trx trx = null;
        try {
            if (con == null) {
                trx = new Trx(this, "usemn");
                con = trx.getConnection();
                isCon = true;
            }
            JMCancelquery = " SELECT PROCESS_ST FROM UM_EDOC_STATE  WHERE TRANS_NO = ?        ";
            psmt = con.prepareStatement(JMCancelquery);
            psmt.setString(1, transNo);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                returnValue = rs.getString(1);
            }
        }
        catch (SQLException exc) {
            Log.debug("UM_ADV_ConrB010c.getJMCancelCheck() block SQLException : ");
            Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
        }
        catch (Exception exc2) {
            Log.debug("UM_ADV_ConrB010c.getJMCancelCheck() block Exception : ");
            Log.debug("Exception발생 사유 : " + exc2.toString());
            exc2.printStackTrace();
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
            if (isCon && trx != null) {
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
        if (isCon && trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return returnValue;
    }
    
    public void icInsert(final String saupNo, final int ilno, final String tabGubun, final String gubun, final String beComment, final String afComment, final String item, final String procID, final Connection con) {
        String icquery = null;
        PreparedStatement icpsmt = null;
        try {
            icquery = " INSERT INTO UM_BID_QUALIFY_FACTS_HIST (  BIZ_REG_NO, UPDATE_DT, SERIAL_NO, TABLE_CLS, CHANGE_CLS,  BEFORE_MOD_CONTENT, AFTER_MOD_CONTENT, PROPERTY_NM, MANAGER_ID  ) VALUES ( '" + saupNo + "', SYSDATE, '" + ilno + "','" + tabGubun + "','" + gubun + "', " + "'" + beComment + "','" + afComment + "','" + item + "','" + procID + "'" + " ) ";
            icpsmt = con.prepareStatement(icquery);
            System.out.println(" \n" + icquery);
            icpsmt.executeUpdate();
            icpsmt.clearParameters();
        }
        catch (SQLException exc) {
            try {
                System.out.println("\n=================== Loi nay: \n" + icquery);
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrD010c.doPost block SQLException : Transaction Rollback간에 SQLException 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrD010c.doPost block SQLException : ");
            Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
        }
        catch (Exception exc2) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrD010c.doPost block Exception : Transaction Rollback간에 Exception 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
            }
            Log.debug("UM_ADV_ConrD010c.doPost block Exception : ");
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
    
    public void setSaupNo(final String saupNo) {
        this.saupNo = saupNo;
    }
    
    public void setTransNo(final String transNo) {
        this.transNo = transNo;
    }
    
    public void setCurrentMode(final int currentMode) {
        this.currentMode = currentMode;
    }
    
    public String[] getMainData() {
        this.setCurrentMode(100);
        String[] mainData = (String[])null;
        final String[][] returnList = this.getList();
        if (returnList != null && returnList.length != 0) {
            mainData = returnList[0];
        }
        return mainData;
    }
    
    public String[] getJMData() {
        this.setCurrentMode(800);
        String[] jmData = (String[])null;
        final String[][] returnList = this.getList();
        if (returnList != null && returnList.length != 0) {
            jmData = returnList[0];
        }
        return jmData;
    }
    
    public String[][] getDPData() {
        this.setCurrentMode(200);
        final String[][] dpData = this.getList();
        return dpData;
    }
    
    public String[][] getGMData() {
        this.setCurrentMode(300);
        final String[][] gmData = this.getList();
        return gmData;
    }
    
    public String[][] getJPData() {
        this.setCurrentMode(400);
        final String[][] jpData = this.getList();
        return jpData;
    }
    
    public String[][] getJP1Data() {
        this.setCurrentMode(500);
        final String[][] jp1Data = this.getList();
        return jp1Data;
    }
    
    public String[][] getDDPData() {
        this.setCurrentMode(900);
        final String[][] ddpData = this.getList();
        return ddpData;
    }
    
    public String[][] getUSData() {
        this.setCurrentMode(1000);
        final String[][] usData = this.getList();
        return usData;
    }
    
    public String[][] getDP1Data() {
        this.setCurrentMode(1100);
        final String[][] dp1Data = this.getList();
        return dp1Data;
    }
    
    public String[][] getIPData() {
        this.setCurrentMode(1200);
        final String[][] ipData = this.getList();
        return ipData;
    }
    
    public String[][] getBRData() {
        this.setCurrentMode(1300);
        final String[][] brData = this.getList();
        return brData;
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
            Log.debug("UM_ADV_ConrD010c.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            sqle.printStackTrace();
        }
        catch (Exception exc) {
            Log.debug("UM_ADV_ConrD010c.select_user block Exception : ");
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
                query = " SELECT  BIZ_NM,  BIZ_CLS,  BIZ_CLS1,  ADDR,  TO_CHAR(REG_DT, 'YYYY-MM-DD'),  DETAIL_ADDR,  CAPITAL,  AREA_CD " + this.getQueryCondition();
                break;
            }
            case 800: {
                query = " SELECT  EMAIL " + this.getQueryCondition();
                break;
            }
            case 200: {
                query = " SELECT  REPR_IDENT_NO,  REPR_NM " + this.getQueryCondition();
                break;
            }
            case 300: {
                query = " SELECT  STD_CLS_CD,  CONST_ABIL_EVAL_AMT,  CD_NM ,   TO_CHAR(STD_CLS_EXPIRY_DT, 'DDMMYYYY'),  EVAL_STD_YEAR" + this.getQueryCondition();
                break;
            }
            case 400: {
                query = " SELECT  GOOD_CLS_NO,  DIRECT_PRODUCTION_YN " + this.getQueryCondition();
                break;
            }
            case 500: {
                query = " SELECT  GOOD_CLS_NO,  DIRECT_PRODUCTION_YN,  DIRECT_PRODUCTION_DOC,  TO_CHAR(AVAIL_PERIOD_START_DT, 'DDMMYYYY'),  TO_CHAR(AVAIL_PERIOD_END_DT, 'DDMMYYYY'),  INDUSTRY_CLS_CD, ISSUE_INSTITU, DOC_NM " + this.getQueryCondition();
                break;
            }
            case 900: {
                query = " SELECT  REPR_IDENT_NO,  REPR_NM,  MAST_REPR_YN " + this.getQueryCondition();
                break;
            }
            case 1000: {
                query = " SELECT  b.CD_NM " + this.getQueryCondition();
                break;
            }
            case 1100: {
                query = " SELECT  REPR_IDENT_NO,  REPR_NM " + this.getQueryCondition();
                break;
            }
            case 1200: {
                query = " SELECT  IDENT_NO,  NM " + this.getQueryCondition();
                break;
            }
            case 1300: {
                query = " SELECT\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t a.BIZ_REG_NO, BIZ_NM, ZIP_CD, AREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX,\t\t\t REPR_NM, REPR_IDENT_NO, DECODE(c.STATE_CLS,'07','Y') STATE_CLS\t\t\t\t\t\t\t\t\t" + this.getQueryCondition();
                break;
            }
        }
        return query;
    }
    
    public String getQueryCondition() {
        String queryCondition = "";
        switch (this.currentMode) {
            case 100: {
                queryCondition = " FROM UM_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                break;
            }
            case 800: {
                queryCondition = " FROM UM_EDOC_STATE  WHERE TRANS_NO = '" + this.transNo + "' ";
                break;
            }
            case 200: {
                queryCondition = " FROM UM_REPR  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                break;
            }
            case 300: {
                queryCondition = " FROM um_enter_std_cls A, SYN_PUB_CODE B  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' " + " AND STD_CLS_CD = CD " + " AND CD_CLS = 'GU9' ";
                break;
            }
            case 400: {
                queryCondition = " FROM UM_SUPPLIER_ENTER_ITEMS  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' " + " AND DIRECT_PRODUCTION_YN = 'N' ";
                break;
            }
            case 500: {
                queryCondition = " FROM UM_SUPPLIER_ENTER_ITEMS  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' " + " AND DIRECT_PRODUCTION_YN = 'Y' ";
                break;
            }
            case 900: {
                queryCondition = " FROM UM_REPR  WHERE MAST_REPR_YN = 'Y'  AND BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                break;
            }
            case 1000: {
                queryCondition = " FROM UM_ENTER_STATE a, SYN_PUB_CODE b  WHERE a.BIZ_REG_NO = '" + this.saupNo + "' " + "   AND b.CD_CLS = 'J11' " + "   AND a.STATE_CLS = b.CD " + "   AND a.STATE_CLS IN ('02', '07') ";
                break;
            }
            case 1100: {
                queryCondition = " FROM UM_REC_REPR  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                break;
            }
            case 1200: {
                queryCondition = " FROM UM_BID_AGENT  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                break;
            }
            case 1300: {
                queryCondition = " FROM  UM_SUPPLIER_ENTER_MAST a, UM_REPR b, UM_ENTER_STATE c\t\t\t\t\t\t\t\t\t\t\t\t\t     WHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.BIZ_REG_NO = c.BIZ_REG_NO(+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND c.STATE_CLS(+) = '07'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND b.MAST_REPR_YN = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t       FROM UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t        WHERE BIZ_REG_NO  ='" + this.um_adv_conra020c.biz_reg_no[0] + "')\t" + "     AND HEADQUARTER_YN = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "     ORDER BY a.BIZ_REG_NO ASC\t\t";
                break;
            }
        }
        return queryCondition;
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
