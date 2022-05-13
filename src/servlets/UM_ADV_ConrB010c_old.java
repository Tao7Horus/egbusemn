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
import entity.UM_GOE_ConiC040b;
import entity.UM_GOE_ConiC030b;
import entity.UM_GOE_ConiC020b;
import beans.UM_URB_UserU010p;
import java.io.Reader;
import java.io.StringReader;
import common.Trx;
import oracle.xml.parser.v2.DOMParser;

public class UM_ADV_ConrB010c_old
{
    UM_ADV_ConrA020c um_adv_conra020c;
    private String[] 일련번호1;
    private String[] 대표자주민번호;
    private String[] 대표자명;
    private String[] 대표자메일주소;
    private String[] 대표대표자여부;
    private String[] 대표자휴대폰;
    private String[] 대표자유형;
    public String[] 지사일련번호;
    public String[] 지사사업자등록번호;
    public String[] 지사상호명;
    public String[] 지사지역코드;
    public String[] 지사등록취소여부;
    public String[] 지사우편번호;
    public String[] 지사주소;
    public String[] 지사나머지주소;
    public String[] 지사전화번호;
    public String[] 지사FAX번호;
    private String[] 지사대표자주민번호;
    private String[] 지사대표자명;
    private String[] 일련번호4;
    private String[] 주민등록번호;
    private String[] 성명;
    private String[] 부서;
    private String[] 직책명;
    private String[] 입찰대리인전화번호;
    private String[] E_MAIL;
    private String[] 휴대폰;
    private String[] FAX;
    private String[] 입찰대리인확인여부;
    private String[] 일련번호;
    private String[] 공장명;
    private String[] 공장우편번호;
    private String[] 공장주소;
    private String[] 공장나머지주소;
    private String[] 공장전화번호;
    private String[] 공장FAX번호;
    private String[] 공장임대여부;
    private String[] 공장임대시작일자;
    private String[] 공장임대종료일자;
    public String[] 일련번호2;
    public String[] 물품명;
    public String[] 추가;
    public String[] 물품분류번호;
    public String[] 최근3년간_매출액;
    public String[] 제조여부;
    public String[] 제조포멧;
    public String[] 대표물품여부;
    public String[] 대표물품포멧;
    public String[] 일련번호5;
    public String[] 물품명1;
    public String[] 추가1;
    public String[] 물품분류번호1;
    public String[] 최근3년간_매출액1;
    public String[] 형식승인번호1;
    public String[] 형식승인기관1;
    public String[] 형식승인일1;
    public String[] 제조여부1;
    public String[] 제조포멧1;
    public String[] 대표물품여부1;
    public String[] 대표물품포멧1;
    public String[] 직접생산증명서류;
    public String[] 유효기간시작일자;
    public String[] 유효기간종료일자;
    public String[] 표준산업분류코드;
    public String[] 발급기관;
    public String[] 증서명;
    private String[] 일련번호3;
    private String[] 면허번호;
    private String[] 면허코드;
    private String[] 면허코드명;
    private String[] 면허취득일자;
    private String[] 면허만료일자;
    private String[] 시공능력평가액;
    private String[] 평가액기준년도;
    private String[] 대표면허여부;
    private String[] 대표면허포멧;
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
    
    public UM_ADV_ConrB010c_old() {
        this.um_adv_conra020c = new UM_ADV_ConrA020c();
        this.일련번호1 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.대표자주민번호 = new String[] { "cc:Organization.CEO.Identifier", "Identifier.Content" };
        this.대표자명 = new String[] { "Organization.CEO.Name", "Text.Content" };
        this.대표자메일주소 = new String[] { "Email.Address.Text", "Text.Content" };
        this.대표대표자여부 = new String[] { "Representation.Ceo.Indicator", "Indicator.Content" };
        this.대표자휴대폰 = new String[] { "cc:PCS.Number.Text", "Text.Content" };
        this.대표자유형 = new String[] { "cc:CEO.Type.Code", "Code.Content" };
        this.지사일련번호 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.지사사업자등록번호 = new String[] { "cc:Organization.Identifier", "Identifier.Content" };
        this.지사상호명 = new String[] { "cc:Company.Name", "Text.Content" };
        this.지사지역코드 = new String[] { "cc:Area.Limtation.Code", "Code.Content" };
        this.지사등록취소여부 = new String[] { "cc:Registration.Cancel.Indicator", "Indicator.Content" };
        this.지사우편번호 = new String[] { "cc:PostCode.Identifier", "Identifier.Content" };
        this.지사주소 = new String[] { "cc:Address.Line1.Text", "Text.Content" };
        this.지사나머지주소 = new String[] { "cc:Address.Line2.Text", "Text.Content" };
        this.지사전화번호 = new String[] { "cc:Telephone.Number.Text", "Text.Content" };
        this.지사FAX번호 = new String[] { "cc:Fax.Number.Text", "Text.Content" };
        this.지사대표자주민번호 = new String[] { "cc:Organization.CEO.Identifier", "Identifier.Content" };
        this.지사대표자명 = new String[] { "cc:Organization.CEO.Name", "Text.Content" };
        this.일련번호4 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.주민등록번호 = new String[] { "cc:Person.Identifier", "Identifier.Content" };
        this.성명 = new String[] { "cc:Employee.Name", "Text.Content" };
        this.부서 = new String[] { "cc:Department.Name", "Text.Content" };
        this.직책명 = new String[] { "Employee.Title.Name", "Text.Content" };
        this.입찰대리인전화번호 = new String[] { "cc:Telephone.Number.Text", "Text.Content" };
        this.E_MAIL = new String[] { "cc:Email.Address.Text", "Text.Content" };
        this.휴대폰 = new String[] { "PCS.Number.Text", "Text.Content" };
        this.FAX = new String[] { "cc:Fax.Number.Text", "Text.Content" };
        this.입찰대리인확인여부 = new String[] { "cc:BiddingAgent.Confirmation.Indicator", "Indicator.Content" };
        this.일련번호 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.공장명 = new String[] { "Factory.Name", "Text.Content" };
        this.공장우편번호 = new String[] { "cc:PostCode.Identifier", "Identifier.Content" };
        this.공장주소 = new String[] { "cc:Address.Line1.Text", "Text.Content" };
        this.공장나머지주소 = new String[] { "cc:Address.Line2.Text", "Text.Content" };
        this.공장전화번호 = new String[] { "cc:Telephone.Number.Text", "Text.Content" };
        this.공장FAX번호 = new String[] { "cc:Fax.Number.Text", "Text.Content" };
        this.공장임대여부 = new String[] { "cc:Own.Lease.Indicator", "Indicator.Content" };
        this.공장임대시작일자 = new String[] { "cc:Lease.Start.Date", "DateTime.Content" };
        this.공장임대종료일자 = new String[] { "cc:Lease.Complete.Date", "DateTime.Content" };
        this.일련번호2 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.물품명 = new String[] { "cc:Item.Name", "Text.Content" };
        this.추가 = new String[] { "cc:Item.Additional.Identifier", "Identifier.Content" };
        this.물품분류번호 = new String[] { "cc:Item.ClassificationNumber.Identifier", "Identifier.Content" };
        this.최근3년간_매출액 = new String[] { "Sale.Recent.Amount", "Amount.Content" };
        this.제조여부 = new String[] { "Manufacturing.Indicator", "Indicator.Content" };
        this.제조포멧 = new String[] { "Manufacturing.Indicator", "Indicator.Format.Text" };
        this.대표물품여부 = new String[] { "Representation.Item.Indicator", "Indicator.Content" };
        this.대표물품포멧 = new String[] { "Representation.Item.Indicator", "Indicator.Format.Text" };
        this.일련번호5 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.물품명1 = new String[] { "cc:Item.Name", "Text.Content" };
        this.추가1 = new String[] { "cc:Item.Additional.Identifier", "Identifier.Content" };
        this.물품분류번호1 = new String[] { "cc:Item.ClassificationNumber.Identifier", "Identifier.Content" };
        this.최근3년간_매출액1 = new String[] { "Sale.Recent.Amount", "Amount.Content" };
        this.형식승인번호1 = new String[] { "Form.Approval.Identifier", "Identifier.Content" };
        this.형식승인기관1 = new String[] { "Form.ApprovalOrg.Name", "Text.Content" };
        this.형식승인일1 = new String[] { "Form.Approval.Date", "DateTime.Content" };
        this.제조여부1 = new String[] { "Manufacturing.Indicator", "Indicator.Content" };
        this.제조포멧1 = new String[] { "Manufacturing.Indicator", "Indicator.Format.Text" };
        this.대표물품여부1 = new String[] { "Representation.Item.Indicator", "Indicator.Content" };
        this.대표물품포멧1 = new String[] { "Representation.Item.Indicator", "Indicator.Format.Text" };
        this.직접생산증명서류 = new String[] { "cc:Production.Certification.Code", "Code.Content" };
        this.유효기간시작일자 = new String[] { "cc:Validity.Start.Date", "DateTime.Content" };
        this.유효기간종료일자 = new String[] { "cc:Validity.End.Date", "DateTime.Content" };
        this.표준산업분류코드 = new String[] { "cc:StandardIndustry.Classification.Code", "Code.Content" };
        this.발급기관 = new String[] { "cc:Organization.Name", "Text.Content" };
        this.증서명 = new String[] { "cc:Certificate.Name", "Text.Content" };
        this.일련번호3 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.면허번호 = new String[] { "License.Number.Text", "Text.Content" };
        this.면허코드 = new String[] { "License.Code", "Code.Content" };
        this.면허코드명 = new String[] { "License.Code", "Code.Name" };
        this.면허취득일자 = new String[] { "License.ValidityStart.Date", "DateTime.Content" };
        this.면허만료일자 = new String[] { "cc:License.ValidityEnd.Date", "DateTime.Content" };
        this.시공능력평가액 = new String[] { "Construcation.AbliltyPar.Amount", "Amount.Content" };
        this.평가액기준년도 = new String[] { "ParRank.AmountStandardYear.Date", "DateTime.Content" };
        this.대표면허여부 = new String[] { "Representation.License.Indicator", "Indicator.Content" };
        this.대표면허포멧 = new String[] { "Representation.License.Indicator", "Indicator.Format.Text" };
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
        final String query3 = null;
        String dpquery1 = null;
        final String dpquery2 = null;
        String msquery1 = null;
        final String msquery2 = null;
        String gjquery1 = null;
        final String gjquery2 = null;
        String idquery1 = null;
        final String idquery2 = null;
        String jpquery1 = null;
        final String jpquery2 = null;
        String jmquery1 = null;
        String miquery1 = null;
        final String bstatus = "N";
        String bsdate = "";
        final String bsdate2 = "";
        final String bsdate3 = "";
        final String bsdateTmp = "";
        String bedate = "";
        final String bedate2 = "";
        final String bedate3 = "";
        final String bedateTmp = "";
        String bcount = "";
        final String bcount2 = "";
        final String bcount3 = "";
        final String bcountTmp = "";
        String bsaup = "";
        final String bsaup2 = "";
        final String bsaupTmp = "";
        final String bstatus_sdate = "";
        final String bstatus_edate = "";
        String status_cancel = "";
        final String ymd = transNo.substring(1, 11);
        final String Upjongcode = "";
        final String Sipyongvalue = "";
        final String Sipyongyear = "";
        final String Sipyongyear2 = "";
        XMLDocument dsource = null;
        final DOMParser parser = new DOMParser();
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            con.setAutoCommit(false);
            final StringReader _Message = new StringReader(xmlDoc);
            parser.parse((Reader)_Message);
            dsource = parser.getDocument();
            GetValues(dsource, this.um_adv_conra020c.biz_reg_no);
            GetValues(dsource, this.um_adv_conra020c.nationality);
            GetValues(dsource, this.um_adv_conra020c.biz_nm);
            GetValues(dsource, this.um_adv_conra020c.biz_en_nm);
            GetValues(dsource, this.um_adv_conra020c.commentcement_dt);
            GetValues(dsource, this.um_adv_conra020c.establish_dt);
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
            UM_GOE_ConiC020b[] ett2 = (UM_GOE_ConiC020b[])null;
            UM_GOE_ConiC030b[] ett3 = (UM_GOE_ConiC030b[])null;
            UM_GOE_ConiC040b[] ett4 = (UM_GOE_ConiC040b[])null;
            UM_GOE_ConiC050b[] ett5 = (UM_GOE_ConiC050b[])null;
            UM_GOE_ConiC110b[] ett6 = (UM_GOE_ConiC110b[])null;
            ett = ctl.select_main(this.um_adv_conra020c.biz_reg_no[0]);
            ett2 = ctl.select_MS(this.um_adv_conra020c.biz_reg_no[0]);
            ett3 = ctl.select_DP(this.um_adv_conra020c.biz_reg_no[0]);
            ett4 = ctl.select_GJ(this.um_adv_conra020c.biz_reg_no[0]);
            ett5 = ctl.select_ID(this.um_adv_conra020c.biz_reg_no[0]);
            ett6 = ctl.select_BR(this.um_adv_conra020c.biz_reg_no[0]);
            XMLElement xemt = null;
            xemt = (XMLElement)dsource.getDocumentElement();
            if (status.equals("1")) {
                status_cancel = this.getJMCancelCheck(transNo, con);
                if (status_cancel.equals("4")) {
                    result = 66;
                    return result;
                }
                final String[][] JPUpmu = this.getJPData();
                final Vector msv = new Vector(1, 1);
                final Vector jpv1 = new Vector(1, 1);
                UM_ADV_ConrA030c[] msArr = (UM_ADV_ConrA030c[])null;
                final UM_ADV_ConrA030c[] jpArr1 = (UM_ADV_ConrA030c[])null;
                int msi = -1;
                int gm = 0;
                int ym = 0;
                int ny = 0;
                int gmc = -1;
                int ymc = -1;
                String licenseCode = "";
                final NodeList msNodeList = dsource.selectNodes("/gb:Gsacid/LicenList/LicenItem", (NSResolver)xemt);
                final int msCount = msNodeList.getLength();
                for (int i = 0; i < msCount - 1; ++i) {
                    final UM_ADV_ConrA030c ms = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no3, i);
                        GetValues(dsource, this.um_adv_conra020c.license_cd, i);
                        GetValues(dsource, this.um_adv_conra020c.license_nm, i);
                        GetValues(dsource, this.um_adv_conra020c.license_no, i);
                        GetValues(dsource, this.um_adv_conra020c.license_issued_dt, i);
                        GetValues(dsource, this.um_adv_conra020c.license_expiry_dt, i);
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
                        final String mc = ms.licencse_cd.substring(0, 1);
                        query1 = " SELECT CD_NM2 FROM SYN_PUB_CODE  WHERE CD_CLS = 'GU9' AND CD = ? ";
                        psmt2 = con.prepareStatement(query1);
                        psmt2.setString(1, ms.licencse_cd);
                        rs = psmt2.executeQuery();
                        if (rs.next()) {
                            licenseCode = rs.getString(1);
                        }
                        if (licenseCode.equals("3") || licenseCode.equals("5")) {
                            gm = 1;
                        }
                        if (licenseCode.equals("4") || licenseCode.equals("5")) {
                            ym = 1;
                        }
                        if (licenseCode.equals("6")) {
                            ym = 1;
                            ny = 1;
                        }
                        if ("0".equals(mc)) {
                            gmc = i;
                        }
                        if ("1".equals(mc)) {
                            ymc = i;
                        }
                        if ("Y".equals(ms.mast_yn)) {
                            msi = i;
                        }
                        msv.addElement(ms);
                    }
                    catch (NullPointerException ane) {
                        msv.addElement(ms);
                        break;
                    }
                }
                msArr = new UM_ADV_ConrA030c[msv.size()];
                msv.copyInto(msArr);
                String upmu1 = "";
                if (!this.um_adv_conra020c.goods_cls_no1[0].equals("") || JPUpmu.length > 0) {
                    upmu1 = "1";
                }
                else if (this.um_adv_conra020c.goods_cls_no1[0].equals("") && JPUpmu.length < 1) {
                    upmu1 = "0";
                }
                final String upmu2 = this.um_adv_conra020c.biz_cls[0];
                String upmu3 = String.valueOf(upmu1) + gm + ym + ny + upmu2;
                if (upmu3.equals("00000")) {
                    upmu3 = "10000";
                }
                final String[] bfJobGubun = this.getMainData();
                final String bfJobGubun2 = bfJobGubun[1].substring(0, 1);
                final String bfJobGubun3 = bfJobGubun[1].substring(1, 2);
                final String bfJobGubun4 = bfJobGubun[1].substring(2, 3);
                final String bfJobGubun5 = bfJobGubun[1].substring(3, 4);
                final String bfJobGubun6 = bfJobGubun[1].substring(4, 5);
                final Vector gjv = new Vector(1, 1);
                UM_ADV_ConrA030c[] gjArr = (UM_ADV_ConrA030c[])null;
                final NodeList gjNodeList = dsource.selectNodes("/gb:Gsacid/FacList/FacItem", (NSResolver)xemt);
                final int gjCount = gjNodeList.getLength();
                String jejoGubun = "";
                for (int j = 0; j < gjCount - 1; ++j) {
                    final UM_ADV_ConrA030c gj = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no, j);
                        GetValues(dsource, this.um_adv_conra020c.fac_nm, j);
                        GetValues(dsource, this.um_adv_conra020c.fac_zip_cd, j);
                        GetValues(dsource, this.um_adv_conra020c.fac_add, j);
                        GetValues(dsource, this.um_adv_conra020c.fac_add2, j);
                        GetValues(dsource, this.um_adv_conra020c.fac_phone, j);
                        GetValues(dsource, this.um_adv_conra020c.fac_fax, j);
                        GetValues(dsource, this.um_adv_conra020c.fac_rent_yn, j);
                        GetValues(dsource, this.um_adv_conra020c.fac_rent_start_dt, j);
                        GetValues(dsource, this.um_adv_conra020c.fac_rent_end_dt, j);
                        gj.serial_no = this.um_adv_conra020c.serial_no[0];
                        gj.fact_nm = this.um_adv_conra020c.fac_nm[0];
                        gj.공장우편번호 = this.um_adv_conra020c.fac_zip_cd[0];
                        gj.공장주소 = this.um_adv_conra020c.fac_add[0];
                        gj.공장나머지주소 = this.um_adv_conra020c.fac_add2[0];
                        gj.공장전화번호 = this.um_adv_conra020c.fac_phone[0];
                        gj.공장FAX번호 = this.um_adv_conra020c.fac_fax[0];
                        gj.공장임대여부 = this.um_adv_conra020c.fac_rent_yn[0];
                        gj.공장임대시작일자 = this.um_adv_conra020c.fac_rent_start_dt[0];
                        gj.공장임대종료일자 = this.um_adv_conra020c.fac_rent_end_dt[0];
                        gjv.addElement(gj);
                    }
                    catch (NullPointerException ane2) {
                        gjv.addElement(gj);
                        break;
                    }
                }
                gjArr = new UM_ADV_ConrA030c[gjv.size()];
                gjv.copyInto(gjArr);
                if (!this.um_adv_conra020c.fac_nm[0].equals("")) {
                    jejoGubun = "02";
                }
                else if (this.um_adv_conra020c.fac_nm[0].equals("")) {
                    jejoGubun = "03";
                }
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
                String jicheong = "";
                if (rs.next()) {
                    jicheong = rs.getString(1);
                }
                psmt2.clearParameters();
                query2 = " UPDATE UM_SUPPLIER_ENTER_MAST SET  NATIONALITY = ?, BIZ_NM = ?, BIZ_EN_NM = ?,  COMMENCEMENT_DT = ?, ESTABLISH_DT = ?, BIZ_CLS = ?, PRODUCT_CLS = ?,  CORP_REG_NO = ?, BIZ_CLS2 = ?, BIZ_CLS_YEAR = ?, CAPITAL = ?,  EMPLOYEE_COUNT = ?, LAST_SETTLE_DT = ?, ZIP_CD = ?, AREA_CD = ?, ADDR = ?,  DETAIL_ADDR = ?, PHONE_NO = ?, FAX = ?, WEBSITE = ?, SPECIAL_GOODS_YN = ?,  UPDATE_DT = SYSDATE, MANAGER_ID = ?, PERMIT_BRANCH = ?, BID_ATTEND_QUALIFY_YN = null ,CONTR_RELA_YN=? WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                if (psmt3 != null) {
                    try {
                        psmt3.close();
                    }
                    catch (Exception ex3) {}
                }
                psmt3 = con.prepareStatement(query2);
                psmt3.setString(1, this.um_adv_conra020c.nationality[0]);
                psmt3.setString(2, isStrNull(this.um_adv_conra020c.biz_nm[0]));
                psmt3.setString(3, isStrNull(this.um_adv_conra020c.biz_en_nm[0]));
                psmt3.setString(4, isStrNull(this.um_adv_conra020c.commentcement_dt[0]));
                psmt3.setString(5, this.um_adv_conra020c.establish_dt[0]);
                psmt3.setString(6, isStrNull(upmu3));
                psmt3.setString(7, isStrNull(jejoGubun));
                psmt3.setString(8, isStrNull(this.um_adv_conra020c.corp_reg_no[0]));
                psmt3.setString(9, isStrNull(this.um_adv_conra020c.biz_cls_2[0]));
                psmt3.setString(10, "0000");
                final int jabon = 0;
                if ("".equals(this.um_adv_conra020c.capital[0])) {
                    psmt3.setInt(11, 0);
                }
                else {
                    psmt3.setString(11, this.um_adv_conra020c.capital[0]);
                }
                psmt3.setString(12, this.um_adv_conra020c.employee_count_[0]);
                psmt3.setString(13, "0000-00");
                psmt3.setString(14, isStrNull(this.um_adv_conra020c.zip_cd[0]));
                psmt3.setString(15, isStrNull(this.um_adv_conra020c.area_cd[0]));
                psmt3.setString(16, isStrNull(this.um_adv_conra020c.addr[0]));
                psmt3.setString(17, isStrNull(this.um_adv_conra020c.addr_detail[0]));
                psmt3.setString(18, isStrNull(this.um_adv_conra020c.phone_no[0]));
                psmt3.setString(19, isStrNull(this.um_adv_conra020c.fax[0]));
                psmt3.setString(20, isStrNull(this.um_adv_conra020c.homepage[0]));
                psmt3.setString(21, isStrNull(this.um_adv_conra020c.special_good_yn[0]));
                psmt3.setString(22, isStrNull(procID));
                psmt3.setString(23, isStrNull(jicheong));
                psmt3.setString(24, isStrNull(this.um_adv_conra020c.contr_rela_yn[0]));
                psmt3.executeUpdate();
                psmt3.clearParameters();
                this.um_adv_conra020c.nationality[0] = isStrNull(this.um_adv_conra020c.nationality[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.nationality);
                this.um_adv_conra020c.biz_nm[0] = isStrNull(this.um_adv_conra020c.biz_nm[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_nm);
                this.um_adv_conra020c.biz_en_nm[0] = isStrNull(this.um_adv_conra020c.biz_en_nm[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_en_nm);
                this.um_adv_conra020c.commentcement_dt[0] = isStrNull(this.um_adv_conra020c.commentcement_dt[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.commentcement_dt);
                this.um_adv_conra020c.establish_dt[0] = isStrNull(this.um_adv_conra020c.establish_dt[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.establish_dt);
                this.um_adv_conra020c.biz_cls[0] = isStrNull(upmu3);
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
                this.um_adv_conra020c.permit_branch[0] = jicheong;
                this.replacePutValues(dsource, this.um_adv_conra020c.permit_branch);
                this.um_adv_conra020c.contr_rela_yn[0] = isStrNull(this.um_adv_conra020c.contr_rela_yn[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.contr_rela_yn);
                final Vector dpv = new Vector(1, 1);
                UM_ADV_ConrA030c[] dpArr = (UM_ADV_ConrA030c[])null;
                final NodeList dpNodeList = dsource.selectNodes("/gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem", (NSResolver)xemt);
                final int dpCount = dpNodeList.getLength();
                int dpi = -1;
                for (int k = 0; k < dpCount - 1; ++k) {
                    final UM_ADV_ConrA030c dp = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no_1, k);
                        GetValues(dsource, this.um_adv_conra020c.rerp_ident_no, k);
                        GetValues(dsource, this.um_adv_conra020c.repr_nm, k);
                        GetValues(dsource, this.um_adv_conra020c.repr_mail, k);
                        GetValues(dsource, this.um_adv_conra020c.mast_repr_yn, k);
                        GetValues(dsource, this.um_adv_conra020c.repr_mobile, k);
                        GetValues(dsource, this.um_adv_conra020c.repr_cls, k);
                        dp.serial_no1 = this.um_adv_conra020c.serial_no_1[0];
                        dp.repr_ident_no = this.um_adv_conra020c.rerp_ident_no[0];
                        dp.repr_nm = this.um_adv_conra020c.repr_nm[0];
                        dp.repr_email = this.um_adv_conra020c.repr_mail[0];
                        dp.mast_repr_yn = this.um_adv_conra020c.mast_repr_yn[0];
                        dp.repr_mobile = this.um_adv_conra020c.repr_mobile[0];
                        dp.repr_cls = this.um_adv_conra020c.repr_cls[0];
                        if ("Y".equals(dp.mast_repr_yn)) {
                            dpi = k;
                        }
                        dpv.addElement(dp);
                    }
                    catch (NullPointerException ane3) {
                        dpv.addElement(dp);
                        break;
                    }
                }
                dpArr = new UM_ADV_ConrA030c[dpv.size()];
                dpv.copyInto(dpArr);
                final Vector dpv2 = new Vector(1, 1);
                UM_ADV_ConrA030c[] dpArr2 = (UM_ADV_ConrA030c[])null;
                final NodeList brNodeList = dsource.selectNodes("/gb:Gsacid/BranchList/BranchItem", (NSResolver)xemt);
                final int brCount = brNodeList.getLength();
                final int bri = -1;
                for (int l = 0; l < brCount - 1; ++l) {
                    final UM_ADV_ConrA030c br = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.branch_serial_no, l);
                        GetValues(dsource, this.um_adv_conra020c.branch_biz_reg_no, l);
                        GetValues(dsource, this.um_adv_conra020c.branch_biz_nm, l);
                        GetValues(dsource, this.um_adv_conra020c.branch_ared_cd, l);
                        GetValues(dsource, this.um_adv_conra020c.branch_area_nm, l);
                        GetValues(dsource, this.um_adv_conra020c.branch_zip_cd, l);
                        GetValues(dsource, this.um_adv_conra020c.branch_add, l);
                        GetValues(dsource, this.um_adv_conra020c.branch_add2, l);
                        GetValues(dsource, this.um_adv_conra020c.branch_phone, l);
                        GetValues(dsource, this.um_adv_conra020c.branch_fax, l);
                        GetValues(dsource, this.um_adv_conra020c.branch_repr_ident_no, l);
                        GetValues(dsource, this.um_adv_conra020c.branch_rept_nm, l);
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
                    catch (NullPointerException ane4) {
                        dpv2.addElement(br);
                        break;
                    }
                }
                dpArr2 = new UM_ADV_ConrA030c[dpv2.size()];
                dpv2.copyInto(dpArr2);
                final Vector dpv3 = new Vector(1, 1);
                UM_ADV_ConrA030c[] dpArr3 = (UM_ADV_ConrA030c[])null;
                final NodeList idNodeList = dsource.selectNodes("/gb:Gsacid/RepList/RepItem", (NSResolver)xemt);
                final int dpCount2 = idNodeList.getLength();
                for (int m = 0; m < dpCount2 - 1; ++m) {
                    final UM_ADV_ConrA030c dp2 = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no4, m);
                        GetValues(dsource, this.um_adv_conra020c.ident_no, m);
                        GetValues(dsource, this.um_adv_conra020c.nm, m);
                        GetValues(dsource, this.um_adv_conra020c.depart, m);
                        GetValues(dsource, this.um_adv_conra020c.position, m);
                        GetValues(dsource, this.um_adv_conra020c.agent_phone, m);
                        GetValues(dsource, this.um_adv_conra020c.E_MAIL, m);
                        GetValues(dsource, this.um_adv_conra020c.mobile, m);
                        GetValues(dsource, this.um_adv_conra020c.FAX, m);
                        GetValues(dsource, this.um_adv_conra020c.agent_yn, m);
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
                    catch (NullPointerException ane5) {
                        dpv3.addElement(dp2);
                        break;
                    }
                }
                dpArr3 = new UM_ADV_ConrA030c[dpv3.size()];
                dpv3.copyInto(dpArr3);
                final Vector idv = new Vector(1, 1);
                final UM_ADV_ConrA030c[] idArr = (UM_ADV_ConrA030c[])null;
                psmt.clearParameters();
                if (!bsdate3.equals("")) {
                    if (bsdate.equals("")) {
                        bsdate = bsdate3;
                        bedate = bedate3;
                        bcount = bcount3;
                        bsaup = bsaup2;
                    }
                    else {
                        if (Integer.parseInt(bsdate3) <= Integer.parseInt(bsdate)) {
                            bsdate = bsdate3;
                        }
                        if (Integer.parseInt(bedate3) >= Integer.parseInt(bedate)) {
                            bedate = bedate3;
                            bsaup = bsaup2;
                        }
                        if (Integer.parseInt(bcount3) >= Integer.parseInt(bcount)) {
                            bcount = bcount3;
                        }
                    }
                }
                if (!bsdate.equals("") && !bedate.equals("") && !bcount.equals("")) {
                    if (bstatus.equals("N")) {
                        SQL = "INSERT INTO UM_ENTER_STATE (BIZ_REG_NO, RAISED_DT, START_DT, END_DT, STATE_CLS, REMARK, MANAGER_ID, PROCESS_DT) VALUES (?, SYSDATE, ?, ?, '05', ?, 'APPROVAL', sysdate)";
                        if (psmt != null) {
                            try {
                                psmt.close();
                            }
                            catch (Exception ex4) {}
                        }
                        psmt = con.prepareStatement(SQL);
                        psmt.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                        psmt.setString(2, String.valueOf(bsdate.substring(0, 4)) + "-" + bsdate.substring(4, 6) + "-" + bsdate.substring(6, 8));
                        psmt.setString(3, String.valueOf(bedate.substring(0, 4)) + "-" + bedate.substring(4, 6) + "-" + bedate.substring(6, 8));
                        psmt.setString(4, String.valueOf(bsaup) + "/" + bcount);
                        psmt.executeUpdate();
                    }
                    else if (bstatus.equals("Y") && (!bstatus_sdate.equals(bsdate) || !bstatus_edate.equals(bedate))) {
                        SQL = "UPDATE UM_ENTER_STATE SET RAISED_DT=SYSDATE, START_DT=?, END_DT=?, REMARK=?, MANAGER_ID = 'APPROVAL', PROCESS_DT = sysdate WHERE BIZ_REG_NO=? and STATE_CLS='05'";
                        if (psmt != null) {
                            try {
                                psmt.close();
                            }
                            catch (Exception ex5) {}
                        }
                        psmt = con.prepareStatement(SQL);
                        psmt.setString(1, String.valueOf(bsdate.substring(0, 4)) + "-" + bsdate.substring(4, 6) + "-" + bsdate.substring(6, 8));
                        psmt.setString(2, String.valueOf(bedate.substring(0, 4)) + "-" + bedate.substring(4, 6) + "-" + bedate.substring(6, 8));
                        psmt.setString(3, String.valueOf(bsaup) + "/" + bcount);
                        psmt.setString(4, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                        psmt.executeUpdate();
                    }
                }
                else if (bstatus.equals("Y")) {
                    SQL = "DELETE UM_ENTER_STATE WHERE BIZ_REG_NO=? and STATE_CLS='05'";
                    if (psmt != null) {
                        try {
                            psmt.close();
                        }
                        catch (Exception ex6) {}
                    }
                    psmt = con.prepareStatement(SQL);
                    psmt.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    psmt.executeUpdate();
                    Log.debug("UM_ADV_ConrB010c.doPost [부정당업자 자료 삭제] : " + this.um_adv_conra020c.biz_reg_no[0]);
                }
                if (ett3.length > 0) {
                    for (int i2 = 0; i2 < ett3.length; ++i2) {
                        for (int j2 = 0; j2 < dpCount - 1; ++j2) {
                            if (ett3[i2].getCeoJuminNo().equals(dpArr[j2].repr_ident_no)) {
                                aflag = true;
                                if (!isStrNull((ett3[i2].getCeoName() == null) ? "" : ett3[i2].getCeoName()).equals(isStrNull(dpArr[j2].repr_nm))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett3[i2].getCeoMail() == null) ? "" : ett3[i2].getCeoMail()).equals(isStrNull(dpArr[j2].repr_email))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett3[i2].getCeoYN() == null) ? "" : ett3[i2].getCeoYN()).equals(isStrNull(dpArr[j2].mast_repr_yn))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett3[i2].getCeohphone() == null) ? "" : ett3[i2].getCeohphone()).equals(isStrNull(dpArr[j2].repr_mobile))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett3[i2].getCeoyuhyung() == null) ? "" : ett3[i2].getCeoyuhyung()).equals(isStrNull(dpArr[j2].repr_cls))) {
                                    bflag = true;
                                }
                                if (bflag) {
                                    dpquery1 = "UPDATE UM_REPR SET REPR_NM=?, REPR_EMAIL=?, MAST_REPR_YN=?, UPDATE_DT=SYSDATE, REPR_MOBILE=?, REPR_CLS=? WHERE BIZ_REG_NO=? and REPR_IDENT_NO=?";
                                    if (dppsmt1 != null) {
                                        try {
                                            dppsmt1.close();
                                        }
                                        catch (Exception ex7) {}
                                    }
                                    dppsmt1 = con.prepareStatement(dpquery1);
                                    dppsmt1.setString(1, isStrNull(dpArr[j2].repr_nm));
                                    dppsmt1.setString(2, isStrNull(dpArr[j2].repr_email));
                                    dppsmt1.setString(3, isStrNull(dpArr[j2].mast_repr_yn));
                                    dppsmt1.setString(4, isStrNull(dpArr[j2].repr_mobile));
                                    dppsmt1.setString(5, isStrNull(dpArr[j2].repr_cls));
                                    dppsmt1.setString(6, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                                    dppsmt1.setString(7, isStrNull(ett3[i2].getCeoJuminNo()));
                                    dppsmt1.executeQuery();
                                    bflag = false;
                                    this.um_adv_conra020c.serial_no_1[0] = isStrNull(dpArr[j2].serial_no1);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.serial_no_1, this.일련번호1, j2, 0, "U");
                                    this.um_adv_conra020c.repr_nm[0] = isStrNull(dpArr[j2].repr_nm);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_nm, this.대표자명, j2, 0, "U");
                                    this.um_adv_conra020c.repr_mail[0] = isStrNull(dpArr[j2].repr_email);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mail, this.대표자메일주소, j2, 0, "U");
                                    this.um_adv_conra020c.mast_repr_yn[0] = isStrNull(dpArr[j2].mast_repr_yn);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.mast_repr_yn, this.대표대표자여부, j2, 0, "U");
                                    this.um_adv_conra020c.repr_mobile[0] = isStrNull(dpArr[j2].repr_mobile);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mobile, this.대표자휴대폰, j2, 0, "U");
                                    this.um_adv_conra020c.repr_cls[0] = isStrNull(dpArr[j2].repr_cls);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_cls, this.대표자유형, j2, 0, "U");
                                    this.um_adv_conra020c.rerp_ident_no[0] = isStrNull(dpArr[j2].repr_ident_no);
                                    this.dpReplacePutValues(dsource, this.um_adv_conra020c.rerp_ident_no, this.대표자주민번호, j2, 0, "U");
                                }
                            }
                        }
                        if (!aflag) {
                            dpquery1 = "DELETE FROM UM_REPR WHERE BIZ_REG_NO=? and REPR_IDENT_NO=?";
                            if (dppsmt1 != null) {
                                try {
                                    dppsmt1.close();
                                }
                                catch (Exception ex8) {}
                            }
                            dppsmt1 = con.prepareStatement(dpquery1);
                            dppsmt1.setString(1, this.um_adv_conra020c.biz_reg_no[0]);
                            dppsmt1.setString(2, ett3[i2].getCeoJuminNo());
                            dppsmt1.executeQuery();
                            this.um_adv_conra020c.serial_no_1[0] = Integer.toString(i2 + 1);
                        }
                        aflag = false;
                    }
                }
                if (dpCount - 1 > 0) {
                    for (int i2 = 0; i2 < dpCount - 1; ++i2) {
                        for (int j2 = 0; j2 < ett3.length; ++j2) {
                            if (dpArr[i2].repr_ident_no.equals(ett3[j2].getCeoJuminNo())) {
                                aflag = true;
                            }
                        }
                        if (!aflag) {
                            dpquery1 = "INSERT INTO UM_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REG_DT, UPDATE_DT, REPR_MOBILE, REPR_CLS) VALUES (?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?, ?)";
                            if (dppsmt1 != null) {
                                try {
                                    dppsmt1.close();
                                }
                                catch (Exception ex9) {}
                            }
                            dppsmt1 = con.prepareStatement(dpquery1);
                            dppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                            dppsmt1.setString(2, isStrNull(dpArr[i2].repr_ident_no));
                            dppsmt1.setString(3, isStrNull(dpArr[i2].repr_nm));
                            dppsmt1.setString(4, isStrNull(dpArr[i2].repr_email));
                            dppsmt1.setString(5, isStrNull(dpArr[i2].mast_repr_yn));
                            dppsmt1.setString(6, isStrNull(dpArr[i2].repr_mobile));
                            dppsmt1.setString(7, isStrNull(dpArr[i2].repr_cls));
                            dppsmt1.executeQuery();
                            this.um_adv_conra020c.serial_no_1[0] = isStrNull(dpArr[i2].serial_no1);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.serial_no_1, this.일련번호1, i2, 0, "I");
                            this.um_adv_conra020c.repr_nm[0] = isStrNull(dpArr[i2].repr_nm);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_nm, this.대표자명, i2, 0, "I");
                            this.um_adv_conra020c.repr_mail[0] = isStrNull(dpArr[i2].repr_email);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mail, this.대표자메일주소, i2, 0, "I");
                            this.um_adv_conra020c.mast_repr_yn[0] = isStrNull(dpArr[i2].mast_repr_yn);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.mast_repr_yn, this.대표대표자여부, i2, 0, "I");
                            this.um_adv_conra020c.repr_mobile[0] = isStrNull(dpArr[i2].repr_mobile);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mobile, this.대표자휴대폰, i2, 0, "I");
                            this.um_adv_conra020c.repr_cls[0] = isStrNull(dpArr[i2].repr_cls);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_cls, this.대표자유형, i2, 0, "I");
                            this.um_adv_conra020c.rerp_ident_no[0] = isStrNull(dpArr[i2].repr_ident_no);
                            this.dpReplacePutValues(dsource, this.um_adv_conra020c.rerp_ident_no, this.대표자주민번호, i2, 0, "I");
                            Log.debug("um_adv_conra020c.일련번호1[0]" + isStrNull(dpArr[i2].serial_no1));
                            Log.debug("um_adv_conra020c.대표자명[0]" + isStrNull(dpArr[i2].repr_nm));
                            Log.debug("um_adv_conra020c.대표자메일주소[0]" + isStrNull(dpArr[i2].repr_email));
                            Log.debug("um_adv_conra020c.대표대표자여부[0]" + isStrNull(dpArr[i2].mast_repr_yn));
                            Log.debug("um_adv_conra020c.대표자휴대폰[0]" + isStrNull(dpArr[i2].repr_mobile));
                            Log.debug("um_adv_conra020c.대표자유형[0]" + isStrNull(dpArr[i2].repr_cls));
                            Log.debug("um_adv_conra020c.대표자주민번호[0]" + isStrNull(dpArr[i2].repr_ident_no));
                        }
                        aflag = false;
                    }
                }
                if (ett6.length > 0) {
                    for (int i2 = 0; i2 < ett6.length; ++i2) {
                        for (int j2 = 0; j2 < brCount - 1; ++j2) {
                            if (ett6[i2].getSaupNo().equals(dpArr2[j2].branch_biz_reg_no)) {
                                aflag = true;
                                if (!isStrNull((ett6[i2].getCeoName() == null) ? "" : ett6[i2].getCeoName()).equals(isStrNull(dpArr2[j2].branch_repr_nm))) {
                                    bflag = true;
                                }
                                if (bflag) {
                                    dpquery1 = "  UPDATE UM_REPR SET   REPR_NM=?, UPDATE_DT=SYSDATE, REPR_IDENT_NO=?  WHERE BIZ_REG_NO=?";
                                    if (dppsmt1 != null) {
                                        try {
                                            dppsmt1.close();
                                        }
                                        catch (Exception ex10) {}
                                    }
                                    dppsmt1 = con.prepareStatement(dpquery1);
                                    dppsmt1.setString(1, isStrNull(dpArr2[j2].branch_repr_nm));
                                    dppsmt1.setString(2, isStrNull(dpArr2[j2].branch_repr_ident_no));
                                    dppsmt1.setString(3, isStrNull(dpArr2[j2].branch_biz_reg_no));
                                    dppsmt1.executeQuery();
                                    Log.debug("등록취소 삭제(정상업체로 전환)  : " + isStrNull(dpArr2[j2].branch_biz_reg_no));
                                    dpquery1 = "DELETE UM_ENTER_STATE WHERE BIZ_REG_NO=? and STATE_CLS='07'";
                                    if (dppsmt1 != null) {
                                        try {
                                            dppsmt1.close();
                                        }
                                        catch (Exception ex11) {}
                                    }
                                    dppsmt1 = con.prepareStatement(dpquery1);
                                    dppsmt1.setString(1, isStrNull(dpArr2[j2].branch_biz_reg_no));
                                    dppsmt1.executeUpdate();
                                    bflag = false;
                                    this.um_adv_conra020c.branch_serial_no[0] = isStrNull(dpArr2[j2].branch_serial_no);
                                    this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_serial_no, this.지사일련번호, j2, 0, "U");
                                    this.um_adv_conra020c.branch_rept_nm[0] = isStrNull(dpArr2[j2].branch_repr_nm);
                                    this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_rept_nm, this.지사대표자명, j2, 0, "U");
                                    this.um_adv_conra020c.branch_repr_ident_no[0] = isStrNull(dpArr2[j2].branch_repr_ident_no);
                                    this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_repr_ident_no, this.지사대표자주민번호, j2, 0, "U");
                                }
                            }
                        }
                        for (int j2 = 0; j2 < brCount - 1; ++j2) {
                            if (ett6[i2].getSaupNo().equals(dpArr2[j2].branch_biz_reg_no)) {
                                aflag = true;
                                if (!isStrNull((ett6[i2].getSangho() == null) ? "" : ett6[i2].getSangho()).equals(isStrNull(dpArr2[j2].branch_biz_nm))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett6[i2].getLocalCode() == null) ? "" : ett6[i2].getLocalCode()).equals(isStrNull(dpArr2[j2].branch_area_cd))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett6[i2].getZipCode() == null) ? "" : ett6[i2].getZipCode()).equals(isStrNull(dpArr2[j2].branch_zip_cd))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett6[i2].getAddr() == null) ? "" : ett6[i2].getAddr()).equals(isStrNull(dpArr2[j2].branch_add))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett6[i2].getRestAddr() == null) ? "" : ett6[i2].getRestAddr()).equals(isStrNull(dpArr2[j2].branch_add2))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett6[i2].getTel() == null) ? "" : ett6[i2].getTel()).equals(isStrNull(dpArr2[j2].branch_phone))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett6[i2].getFax() == null) ? "" : ett6[i2].getFax()).equals(isStrNull(dpArr2[j2].branch_fax))) {
                                    bflag = true;
                                }
                                if (bflag) {
                                    Log.debug("지사업체 업데이트 : " + isStrNull(dpArr2[j2].branch_biz_reg_no));
                                    query2 = " UPDATE UM_SUPPLIER_ENTER_MAST SET  BIZ_NM = ?, ZIP_CD = ?, AREA_CD = ?, ADDR = ?, DETAIL_ADDR = ?,  PHONE_NO = ?, FAX = ?,  UPDATE_DT = SYSDATE, MANAGER_ID = ?, PERMIT_BRANCH = ?, BID_ATTEND_QUALIFY_YN = null  WHERE BIZ_REG_NO = ? ";
                                    if (psmt3 != null) {
                                        try {
                                            psmt3.close();
                                        }
                                        catch (Exception ex12) {}
                                    }
                                    psmt3 = con.prepareStatement(query2);
                                    psmt3.setString(1, isStrNull(dpArr2[j2].branch_biz_nm));
                                    psmt3.setString(2, isStrNull(dpArr2[j2].branch_zip_cd));
                                    psmt3.setString(3, isStrNull(dpArr2[j2].branch_area_cd));
                                    psmt3.setString(4, isStrNull(dpArr2[j2].branch_add));
                                    psmt3.setString(5, isStrNull(dpArr2[j2].branch_add2));
                                    psmt3.setString(6, isStrNull(dpArr2[j2].branch_phone));
                                    psmt3.setString(7, isStrNull(dpArr2[j2].branch_fax));
                                    psmt3.setString(8, isStrNull(procID));
                                    psmt3.setString(9, isStrNull(jicheong));
                                    psmt3.setString(10, isStrNull(dpArr2[j2].branch_biz_reg_no));
                                    psmt3.executeUpdate();
                                    psmt3.clearParameters();
                                    Log.debug("등록취소 삭제(정상업체로 전환)  : " + isStrNull(dpArr2[j2].branch_biz_reg_no));
                                    dpquery1 = "DELETE UM_ENTER_STATE WHERE BIZ_REG_NO=? and STATE_CLS='07'";
                                    if (dppsmt1 != null) {
                                        try {
                                            dppsmt1.close();
                                        }
                                        catch (Exception ex13) {}
                                    }
                                    dppsmt1 = con.prepareStatement(dpquery1);
                                    dppsmt1.setString(1, isStrNull(dpArr2[j2].branch_biz_reg_no));
                                    dppsmt1.executeUpdate();
                                    bflag = false;
                                    this.um_adv_conra020c.branch_serial_no[0] = isStrNull(dpArr2[j2].branch_serial_no);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_serial_no, this.지사일련번호, j2, 0, "U");
                                    this.um_adv_conra020c.branch_biz_nm[0] = isStrNull(dpArr2[j2].branch_biz_nm);
                                    this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_biz_nm, this.지사상호명, j2, 0, "U");
                                    this.um_adv_conra020c.branch_zip_cd[0] = isStrNull(dpArr2[j2].branch_zip_cd);
                                    this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_zip_cd, this.지사우편번호, j2, 0, "U");
                                    this.um_adv_conra020c.branch_ared_cd[0] = isStrNull(dpArr[j2].branch_area_cd);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_ared_cd, this.지사지역코드, j2, 0, "U");
                                    this.um_adv_conra020c.branch_add[0] = isStrNull(dpArr2[j2].branch_add);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_add, this.지사주소, j2, 0, "U");
                                    this.um_adv_conra020c.branch_add2[0] = isStrNull(dpArr2[j2].branch_add2);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_add2, this.지사나머지주소, j2, 0, "U");
                                    this.um_adv_conra020c.branch_phone[0] = isStrNull(dpArr2[j2].branch_phone);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_phone, this.지사전화번호, j2, 0, "U");
                                    this.um_adv_conra020c.branch_fax[0] = isStrNull(dpArr2[j2].branch_fax);
                                    this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_fax, this.지사FAX번호, j2, 0, "U");
                                }
                            }
                        }
                        if (!aflag) {
                            Log.debug("등록취소 인서트  : " + isStrNull(ett6[i2].getSaupNo()));
                            dpquery1 = "INSERT INTO UM_ENTER_STATE (BIZ_REG_NO, RAISED_DT, START_DT, STATE_CLS, REMARK, MANAGER_ID, PROCESS_DT) VALUES (?, SYSDATE, SYSDATE,  '07', ?, 'APPROVAL', SYSDATE)";
                            if (dppsmt1 != null) {
                                try {
                                    dppsmt1.close();
                                }
                                catch (Exception ex14) {}
                            }
                            dppsmt1 = con.prepareStatement(dpquery1);
                            dppsmt1.setString(1, isStrNull(ett6[i2].getSaupNo()));
                            dppsmt1.setString(2, "지사등록후 삭제처리(본사)");
                            dppsmt1.executeUpdate();
                        }
                        aflag = false;
                    }
                }
                if (brCount - 1 > 0) {
                    for (int i2 = 0; i2 < brCount - 1; ++i2) {
                        for (int j2 = 0; j2 < ett6.length; ++j2) {
                            if (dpArr2[i2].branch_biz_reg_no.equals(ett6[j2].getSaupNo())) {
                                aflag = true;
                            }
                        }
                        if (!aflag) {
                            Log.debug("지사대표자 인서트 : " + isStrNull(dpArr2[i2].branch_biz_reg_no));
                            dpquery1 = "INSERT INTO UM_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, MAST_REPR_YN, REG_DT, UPDATE_DT) VALUES (?, ?, ?, ?, SYSDATE, SYSDATE)";
                            if (dppsmt1 != null) {
                                try {
                                    dppsmt1.close();
                                }
                                catch (Exception ex15) {}
                            }
                            dppsmt1 = con.prepareStatement(dpquery1);
                            dppsmt1.setString(1, isStrNull(dpArr2[i2].branch_biz_reg_no));
                            dppsmt1.setString(2, isStrNull(dpArr2[i2].branch_repr_ident_no));
                            dppsmt1.setString(3, isStrNull(dpArr2[i2].branch_repr_nm));
                            dppsmt1.setString(4, "Y");
                            dppsmt1.executeQuery();
                            this.um_adv_conra020c.branch_serial_no[0] = isStrNull(dpArr2[i2].branch_serial_no);
                            final int seq_num = isIntNull(dpArr2[i2].branch_serial_no);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_serial_no, this.지사일련번호, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_rept_nm[0] = isStrNull(dpArr2[i2].branch_repr_nm);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_rept_nm, this.지사대표자명, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_repr_ident_no[0] = isStrNull(dpArr2[i2].branch_repr_ident_no);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_repr_ident_no, this.지사대표자주민번호, seq_num, 0, "I");
                        }
                        aflag = false;
                    }
                }
                if (brCount - 1 > 0) {
                    for (int i2 = 0; i2 < brCount - 1; ++i2) {
                        for (int j2 = 0; j2 < ett6.length; ++j2) {
                            if (dpArr2[i2].branch_biz_reg_no.equals(ett6[j2].getSaupNo())) {
                                aflag = true;
                            }
                        }
                        if (!aflag) {
                            Log.debug("지사업체 인서트 : " + isStrNull(dpArr2[i2].branch_biz_reg_no));
                            query2 = " INSERT INTO UM_SUPPLIER_ENTER_MAST (  BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM,  COMMENCEMENT_DT, ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS,  CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL,  EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR,  DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECIAL_GOODS_YN,  REG_DT, UPDATE_DT, REPR_BIZ_APPROVE_YN, MANAGER_ID,  PERMIT_BRANCH, INITIAL_INTITU_NM,HEADQUARTER_YN  ) VALUES (  ?, ?, ?, ?,  ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  SYSDATE, SYSDATE, 'Y', ?,  ?, ?,?  ) ";
                            psmt3 = con.prepareStatement(query2);
                            psmt3.setString(1, isStrNull(dpArr2[i2].branch_biz_reg_no));
                            psmt3.setString(2, isStrNull(this.um_adv_conra020c.nationality[0]));
                            psmt3.setString(3, isStrNull(dpArr2[i2].branch_biz_nm));
                            psmt3.setString(4, isStrNull(this.um_adv_conra020c.biz_en_nm[0]));
                            psmt3.setString(5, this.um_adv_conra020c.commentcement_dt[0]);
                            psmt3.setString(6, this.um_adv_conra020c.establish_dt[0]);
                            psmt3.setString(7, isStrNull(upmu3));
                            psmt3.setString(8, isStrNull(jejoGubun));
                            psmt3.setString(9, isStrNull(this.um_adv_conra020c.corp_reg_no[0]));
                            psmt3.setString(10, isStrNull("2"));
                            psmt3.setString(11, isStrNull(this.um_adv_conra020c.biz_cls_2[0]));
                            psmt3.setString(12, isStrNull("0000"));
                            if (" ".equals(this.um_adv_conra020c.capital[0]) || "".equals(this.um_adv_conra020c.capital[0]) || this.um_adv_conra020c.capital[0] == null) {
                                psmt3.setInt(13, 0);
                            }
                            else {
                                psmt3.setString(13, this.um_adv_conra020c.capital[0]);
                            }
                            psmt3.setString(14, this.um_adv_conra020c.employee_count_[0]);
                            psmt3.setString(15, isStrNull("0000-00"));
                            psmt3.setString(16, isStrNull(dpArr2[i2].branch_zip_cd));
                            psmt3.setString(17, isStrNull(dpArr2[i2].branch_area_cd));
                            psmt3.setString(18, isStrNull(dpArr2[i2].branch_add));
                            psmt3.setString(19, isStrNull(dpArr2[i2].branch_add2));
                            psmt3.setString(20, isStrNull(dpArr2[i2].branch_phone));
                            psmt3.setString(21, isStrNull(dpArr2[i2].branch_fax));
                            psmt3.setString(22, isStrNull(this.um_adv_conra020c.homepage[0]));
                            psmt3.setString(23, isStrNull(this.um_adv_conra020c.special_good_yn[0]));
                            psmt3.setString(24, isStrNull(procID));
                            psmt3.setString(25, isStrNull(jicheong));
                            psmt3.setString(26, isStrNull("조달청"));
                            psmt3.setString(27, "N");
                            psmt3.executeUpdate();
                            psmt3.clearParameters();
                            this.um_adv_conra020c.branch_serial_no[0] = isStrNull(dpArr2[i2].branch_serial_no);
                            final int seq_num = isIntNull(dpArr2[i2].branch_serial_no);
                            this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_serial_no, this.지사일련번호, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_biz_nm[0] = isStrNull(dpArr2[i2].branch_biz_nm);
                            this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_biz_nm, this.지사상호명, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_zip_cd[0] = isStrNull(dpArr2[i2].branch_zip_cd);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_zip_cd, this.지사우편번호, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_ared_cd[0] = isStrNull(dpArr2[i2].branch_area_cd);
                            this.brReplacePutValues(dsource, this.um_adv_conra020c.branch_ared_cd, this.지사지역코드, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_add[0] = isStrNull(dpArr2[i2].branch_add);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_add, this.지사주소, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_add2[0] = isStrNull(dpArr2[i2].branch_add2);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_add2, this.지사나머지주소, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_phone[0] = isStrNull(dpArr2[i2].branch_phone);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_phone, this.지사전화번호, seq_num, 0, "I");
                            this.um_adv_conra020c.branch_fax[0] = isStrNull(dpArr2[i2].branch_fax);
                            this.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_fax, this.지사FAX번호, seq_num, 0, "I");
                        }
                        aflag = false;
                    }
                }
                Log.debug("지사정보 변경 끝");
                if (ett5.length > 0) {
                    for (int i2 = 0; i2 < ett5.length; ++i2) {
                        for (int j2 = 0; j2 < dpCount2 - 1; ++j2) {
                            if (ett5[i2].getJuminNo().equals(dpArr3[j2].identNo)) {
                                aflag = true;
                                if (!isStrNull((ett5[i2].getJobPart() == null) ? "" : ett5[i2].getJobPart()).equals(isStrNull(dpArr3[j2].depart))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett5[i2].getDutyName() == null) ? "" : ett5[i2].getDutyName()).equals(isStrNull(dpArr3[j2].position))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett5[i2].getName() == null) ? "" : ett5[i2].getName()).equals(isStrNull(dpArr3[j2].agentNm))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett5[i2].getTel() == null) ? "" : ett5[i2].getTel()).equals(isStrNull(dpArr3[j2].agent_phone))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett5[i2].getFax() == null) ? "" : ett5[i2].getFax()).equals(isStrNull(dpArr3[j2].FAX))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett5[i2].getMail() == null) ? "" : ett5[i2].getMail()).equals(isStrNull(dpArr3[j2].E_MAIL))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett5[i2].getHandphone() == null) ? "" : ett5[i2].getHandphone()).equals(isStrNull(dpArr3[j2].mobile))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett5[i2].getIpchalYN() == null) ? "" : ett5[i2].getIpchalYN()).equals(isStrNull(dpArr3[j2].agent_yn))) {
                                    bflag = true;
                                }
                                if (bflag) {
                                    idquery1 = "UPDATE UM_BID_AGENT SET DEPART=?, POSITION=?, NM=?, PHONE_NO=?, FAX=?, EMAIL=?, MOBILE=?, UPDATE_DT=SYSDATE, BIDDING_AGENT_YN=? WHERE BIZ_REG_NO=? and IDENT_NO=?";
                                    if (idpsmt1 != null) {
                                        try {
                                            idpsmt1.close();
                                        }
                                        catch (Exception ex16) {}
                                    }
                                    idpsmt1 = con.prepareStatement(idquery1);
                                    idpsmt1.setString(1, isStrNull(dpArr3[j2].depart));
                                    idpsmt1.setString(2, isStrNull(dpArr3[j2].position));
                                    idpsmt1.setString(3, isStrNull(dpArr3[j2].agentNm));
                                    idpsmt1.setString(4, isStrNull(dpArr3[j2].agent_phone));
                                    idpsmt1.setString(5, isStrNull(dpArr3[j2].FAX));
                                    idpsmt1.setString(6, isStrNull(dpArr3[j2].E_MAIL));
                                    idpsmt1.setString(7, isStrNull(dpArr3[j2].mobile));
                                    idpsmt1.setString(8, isStrNull(dpArr3[j2].agent_yn));
                                    idpsmt1.setString(9, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                                    idpsmt1.setString(10, isStrNull(ett5[i2].getJuminNo()));
                                    Log.debug("부서[" + j2 + "]" + isStrNull(dpArr3[j2].depart));
                                    Log.debug("직책명" + isStrNull(dpArr3[j2].position));
                                    Log.debug("성명" + isStrNull(dpArr3[j2].agentNm));
                                    Log.debug("입찰대리인전화번호" + isStrNull(dpArr3[j2].agent_phone));
                                    Log.debug("FAX" + isStrNull(dpArr3[j2].FAX));
                                    Log.debug("E_MAIL" + isStrNull(dpArr3[j2].E_MAIL));
                                    Log.debug("휴대폰" + isStrNull(dpArr3[j2].mobile));
                                    Log.debug("입찰대리인확인여부" + isStrNull(dpArr3[j2].agent_yn));
                                    Log.debug("um_adv_conra020c.사업자등록번호[0]" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                                    Log.debug("주민등록번호" + isStrNull(ett5[i2].getJuminNo()));
                                    idpsmt1.executeQuery();
                                    bflag = false;
                                    this.um_adv_conra020c.serial_no4[0] = isStrNull(dpArr3[j2].serialNo4);
                                    this.um_adv_conra020c.depart[0] = isStrNull(dpArr3[j2].depart);
                                    this.um_adv_conra020c.position[0] = isStrNull(dpArr3[j2].position);
                                    this.um_adv_conra020c.nm[0] = isStrNull(dpArr3[j2].agentNm);
                                    this.um_adv_conra020c.agent_phone[0] = isStrNull(dpArr3[j2].agent_phone);
                                    this.um_adv_conra020c.ident_no[0] = isStrNull(ett5[i2].getJuminNo());
                                    this.um_adv_conra020c.E_MAIL[0] = isStrNull(dpArr3[j2].E_MAIL);
                                    this.um_adv_conra020c.FAX[0] = isStrNull(dpArr3[j2].FAX);
                                    this.um_adv_conra020c.mobile[0] = isStrNull(dpArr3[j2].mobile);
                                    this.um_adv_conra020c.agent_yn[0] = isStrNull(dpArr3[j2].agent_yn);
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.serial_no4, this.일련번호4, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.depart, this.부서, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.position, this.직책명, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.nm, this.성명, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.agent_phone, this.입찰대리인전화번호, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.ident_no, this.주민등록번호, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.E_MAIL, this.E_MAIL, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.FAX, this.FAX, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.mobile, this.휴대폰, j2, 0, "U");
                                    this.idReplacePutValues(dsource, this.um_adv_conra020c.agent_yn, this.입찰대리인확인여부, j2, 0, "U");
                                }
                            }
                        }
                        if (!aflag) {
                            idquery1 = "DELETE FROM UM_BID_AGENT WHERE BIZ_REG_NO=? and IDENT_NO=?";
                            if (idpsmt1 != null) {
                                try {
                                    idpsmt1.close();
                                }
                                catch (Exception ex17) {}
                            }
                            idpsmt1 = con.prepareStatement(idquery1);
                            idpsmt1.setString(1, this.um_adv_conra020c.biz_reg_no[0]);
                            idpsmt1.setString(2, ett5[i2].getJuminNo());
                            idpsmt1.executeQuery();
                            this.um_adv_conra020c.serial_no4[0] = Integer.toString(i2 + 1);
                            Log.debug("일련번호4Integer" + Integer.toString(i2 + 1));
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.serial_no4, this.일련번호4, i2, 0, "D");
                        }
                        aflag = false;
                    }
                }
                if (dpCount2 - 1 > 0) {
                    for (int i2 = 0; i2 < dpCount2 - 1; ++i2) {
                        for (int j2 = 0; j2 < ett5.length; ++j2) {
                            if (dpArr3[i2].identNo.equals(ett5[j2].getJuminNo())) {
                                aflag = true;
                            }
                        }
                        if (!aflag) {
                            idquery1 = " INSERT INTO UM_BID_AGENT (  BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION,  PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT, BIDDING_AGENT_YN  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?, ?, SYSDATE, SYSDATE,?  ) ";
                            if (idpsmt1 != null) {
                                try {
                                    idpsmt1.close();
                                }
                                catch (Exception ex18) {}
                            }
                            idpsmt1 = con.prepareStatement(idquery1);
                            idpsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                            idpsmt1.setString(2, isStrNull(dpArr3[i2].identNo));
                            idpsmt1.setString(3, isStrNull(dpArr3[i2].agentNm));
                            idpsmt1.setString(4, isStrNull(dpArr3[i2].depart));
                            idpsmt1.setString(5, isStrNull(dpArr3[i2].position));
                            idpsmt1.setString(6, isStrNull(dpArr3[i2].agent_phone));
                            idpsmt1.setString(7, isStrNull(dpArr3[i2].E_MAIL));
                            idpsmt1.setString(8, isStrNull(dpArr3[i2].mobile));
                            idpsmt1.setString(9, isStrNull(dpArr3[i2].FAX));
                            idpsmt1.setString(10, isStrNull(dpArr3[i2].agent_yn));
                            Log.debug("um_adv_conra020c.사업자등록번호[0]" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                            Log.debug("주민등록번호" + isStrNull(dpArr3[i2].identNo));
                            Log.debug("성명" + isStrNull(dpArr3[i2].agentNm));
                            Log.debug("부서" + isStrNull(dpArr3[i2].depart));
                            Log.debug("직책명" + isStrNull(dpArr3[i2].position));
                            Log.debug("입찰대리인전화번호" + isStrNull(dpArr3[i2].agent_phone));
                            Log.debug("E_MAIL" + isStrNull(dpArr3[i2].E_MAIL));
                            Log.debug("휴대폰" + isStrNull(dpArr3[i2].mobile));
                            Log.debug("FAX" + isStrNull(dpArr3[i2].FAX));
                            Log.debug("입찰대리인확인여부" + isStrNull(dpArr3[i2].agent_yn));
                            idpsmt1.executeUpdate();
                            idpsmt1.clearParameters();
                            this.um_adv_conra020c.serial_no4[0] = isStrNull(dpArr3[i2].serialNo4);
                            Log.debug("일련번호4" + isStrNull(dpArr3[i2].serialNo4));
                            this.um_adv_conra020c.depart[0] = isStrNull(dpArr3[i2].depart);
                            this.um_adv_conra020c.position[0] = isStrNull(dpArr3[i2].position);
                            this.um_adv_conra020c.nm[0] = isStrNull(dpArr3[i2].agentNm);
                            this.um_adv_conra020c.agent_phone[0] = isStrNull(dpArr3[i2].agent_phone);
                            this.um_adv_conra020c.ident_no[0] = isStrNull(dpArr3[i2].identNo);
                            this.um_adv_conra020c.E_MAIL[0] = isStrNull(dpArr3[i2].E_MAIL);
                            this.um_adv_conra020c.FAX[0] = isStrNull(dpArr3[i2].FAX);
                            this.um_adv_conra020c.mobile[0] = isStrNull(dpArr3[i2].mobile);
                            this.um_adv_conra020c.agent_yn[0] = isStrNull(dpArr3[i2].agent_yn);
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.serial_no4, this.일련번호4, i2, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.depart, this.부서, i2, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.position, this.직책명, i2, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.nm, this.성명, i2, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.agent_phone, this.입찰대리인전화번호, i2, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.ident_no, this.주민등록번호, i2, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.E_MAIL, this.E_MAIL, i2, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.FAX, this.FAX, i2, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.mobile, this.휴대폰, i2, 0, "I");
                            this.idReplacePutValues(dsource, this.um_adv_conra020c.agent_yn, this.입찰대리인확인여부, i2, 0, "I");
                            Log.debug("um_adv_conra020c.일련번호4[0]" + isStrNull(dpArr3[i2].serialNo4));
                            Log.debug("um_adv_conra020c.부서[0]" + isStrNull(dpArr3[i2].depart));
                            Log.debug("um_adv_conra020c.직책명[0]" + isStrNull(dpArr3[i2].position));
                            Log.debug("um_adv_conra020c.성명[0]" + isStrNull(dpArr3[i2].agentNm));
                            Log.debug("um_adv_conra020c.입찰대리인전화번호[0]" + isStrNull(dpArr3[i2].agent_phone));
                            Log.debug("um_adv_conra020c.주민등록번호[0]" + isStrNull(dpArr3[i2].identNo));
                            Log.debug("um_adv_conra020c.E_MAIL[0]" + isStrNull(dpArr3[i2].E_MAIL));
                            Log.debug("um_adv_conra020c.FAX[0]" + isStrNull(dpArr3[i2].FAX));
                            Log.debug("um_adv_conra020c.휴대폰[0]" + isStrNull(dpArr3[i2].mobile));
                            Log.debug("um_adv_conra020c.입찰대리인확인여부[0]" + isStrNull(dpArr3[i2].agent_yn));
                        }
                        aflag = false;
                    }
                }
                Log.debug("입찰대리인 변경 끝");
                if (ett4.length > 0) {
                    for (int i2 = 0; i2 < ett4.length; ++i2) {
                        for (int j2 = 0; j2 < gjCount - 1; ++j2) {
                            if (isStrNull((ett4[i2].getFactoryName() == null) ? "" : ett4[i2].getFactoryName()).equals(isStrNull(gjArr[j2].fact_nm))) {
                                bflag = true;
                                if (isStrNull((ett4[i2].getFactoryZipCode() == null) ? "" : ett4[i2].getFactoryZipCode()).equals(isStrNull(gjArr[j2].공장우편번호))) {
                                    bflag = true;
                                    if (isStrNull((ett4[i2].getFactoryAddr() == null) ? "" : ett4[i2].getFactoryAddr()).equals(isStrNull(gjArr[j2].공장주소))) {
                                        bflag = true;
                                        if (isStrNull((ett4[i2].getFactoryRestAddr() == null) ? "" : ett4[i2].getFactoryRestAddr()).equals(isStrNull(gjArr[j2].공장나머지주소))) {
                                            bflag = true;
                                            if (isStrNull((ett4[i2].getFactoryTel() == null) ? "" : ett4[i2].getFactoryTel()).equals(isStrNull(gjArr[j2].공장전화번호))) {
                                                bflag = true;
                                                if (isStrNull((ett4[i2].getFactoryFax() == null) ? "" : ett4[i2].getFactoryFax()).equals(isStrNull(gjArr[j2].공장FAX번호))) {
                                                    bflag = true;
                                                    if (isStrNull((ett4[i2].getFactoryleaseYN() == null) ? "" : ett4[i2].getFactoryleaseYN()).equals(isStrNull(gjArr[j2].공장임대여부))) {
                                                        bflag = true;
                                                        if (isStrNull((ett4[i2].getFactoryleaseSdate() == null) ? "" : (String.valueOf(ett4[i2].getFactoryleaseSdate().substring(0, 4)) + ett4[i2].getFactoryleaseSdate().substring(5, 7) + ett4[i2].getFactoryleaseSdate().substring(8, 10))).equals(isStrNull(gjArr[j2].공장임대시작일자))) {
                                                            bflag = true;
                                                            if (isStrNull((ett4[i2].getFactoryleaseEdate() == null) ? "" : (String.valueOf(ett4[i2].getFactoryleaseEdate().substring(0, 4)) + ett4[i2].getFactoryleaseEdate().substring(5, 7) + ett4[i2].getFactoryleaseEdate().substring(8, 10))).equals(isStrNull(gjArr[j2].공장임대종료일자))) {
                                                                bflag = true;
                                                                aflag = true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (!aflag) {
                            gjquery1 = "DELETE FROM UM_FACTORY_INFO WHERE BIZ_REG_NO=? and SERIAL_NO=?";
                            if (gjpsmt1 != null) {
                                try {
                                    gjpsmt1.close();
                                }
                                catch (Exception ex19) {}
                            }
                            gjpsmt1 = con.prepareStatement(gjquery1);
                            gjpsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                            gjpsmt1.setInt(2, ett4[i2].getIlrunNo());
                            gjpsmt1.executeQuery();
                            this.um_adv_conra020c.serial_no[0] = Integer.toString(i2 + 1);
                            this.facReplacePutValues(dsource, this.um_adv_conra020c.serial_no, this.일련번호, i2, 0, "D");
                            Log.debug("um_adv_conra020c.사업자등록번호[0]" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                            Log.debug("일련번호[" + i2 + "]" + ett4[i2].getIlrunNo());
                            Log.debug("일련번호Integer" + Integer.toString(i2 + 1));
                        }
                        aflag = false;
                    }
                }
                if (gjCount - 1 > 0) {
                    for (int i2 = 0; i2 < gjCount - 1; ++i2) {
                        for (int j2 = 0; j2 < ett4.length; ++j2) {
                            if (isStrNull((ett4[j2].getFactoryName() == null) ? "" : ett4[j2].getFactoryName()).equals(isStrNull(gjArr[i2].fact_nm)) && isStrNull((ett4[j2].getFactoryZipCode() == null) ? "" : ett4[j2].getFactoryZipCode()).equals(isStrNull(gjArr[i2].공장우편번호)) && isStrNull((ett4[j2].getFactoryAddr() == null) ? "" : ett4[j2].getFactoryAddr()).equals(isStrNull(gjArr[i2].공장주소)) && isStrNull((ett4[j2].getFactoryRestAddr() == null) ? "" : ett4[j2].getFactoryRestAddr()).equals(isStrNull(gjArr[i2].공장나머지주소)) && isStrNull((ett4[j2].getFactoryTel() == null) ? "" : ett4[j2].getFactoryTel()).equals(isStrNull(gjArr[i2].공장전화번호)) && isStrNull((ett4[j2].getFactoryFax() == null) ? "" : ett4[j2].getFactoryFax()).equals(isStrNull(gjArr[i2].공장FAX번호)) && isStrNull((ett4[j2].getFactoryleaseYN() == null) ? "" : ett4[j2].getFactoryleaseYN()).equals(isStrNull(gjArr[i2].공장임대여부)) && isStrNull((ett4[j2].getFactoryleaseSdate() == null) ? "" : (String.valueOf(ett4[j2].getFactoryleaseSdate().substring(0, 4)) + ett4[j2].getFactoryleaseSdate().substring(5, 7) + ett4[j2].getFactoryleaseSdate().substring(8, 10))).equals(isStrNull(gjArr[i2].공장임대시작일자)) && isStrNull((ett4[j2].getFactoryleaseEdate() == null) ? "" : (String.valueOf(ett4[j2].getFactoryleaseEdate().substring(0, 4)) + ett4[j2].getFactoryleaseEdate().substring(5, 7) + ett4[j2].getFactoryleaseEdate().substring(8, 10))).equals(isStrNull(gjArr[i2].공장임대종료일자))) {
                                aflag = true;
                            }
                        }
                        if (!aflag) {
                            gjquery1 = "INSERT INTO UM_FACTORY_INFO (BIZ_REG_NO, SERIAL_NO, FACTORY_NM, FACTORY_ZIP_CD, FACTORY_ADDR, FACTORY_ADDR2, FACTORY_PHONE_NO, FACTORY_FAX, REG_DT, UPDATE_DT, FACTORY_RENT_YN, FACTORY_RENT_START_DT, FACTORY_RENT_END_DT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?, ?, ?)";
                            if (gjpsmt1 != null) {
                                try {
                                    gjpsmt1.close();
                                }
                                catch (Exception ex20) {}
                            }
                            gjpsmt1 = con.prepareStatement(gjquery1);
                            gjpsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                            gjpsmt1.setString(2, isStrNull(gjArr[i2].serial_no));
                            gjpsmt1.setString(3, isStrNull(gjArr[i2].fact_nm));
                            gjpsmt1.setString(4, isStrNull(gjArr[i2].공장우편번호));
                            gjpsmt1.setString(5, isStrNull(gjArr[i2].공장주소));
                            gjpsmt1.setString(6, isStrNull(gjArr[i2].공장나머지주소));
                            gjpsmt1.setString(7, isStrNull(gjArr[i2].공장전화번호));
                            gjpsmt1.setString(8, isStrNull(gjArr[i2].공장FAX번호));
                            gjpsmt1.setString(9, isStrNull(gjArr[i2].공장임대여부));
                            gjpsmt1.setString(10, gjArr[i2].공장임대시작일자);
                            gjpsmt1.setString(11, gjArr[i2].공장임대종료일자);
                            Log.debug("UM_ADV_ConrB010c.doPost block SQLException 공장임대여부 : " + isStrNull(gjArr[i2].공장임대여부));
                            Log.debug("UM_ADV_ConrB010c.doPost block SQLException 공장임대시작일자 : " + gjArr[i2].공장임대시작일자);
                            Log.debug("UM_ADV_ConrB010c.doPost block SQLException 공장임대종료일자 : " + gjArr[i2].공장임대종료일자);
                            gjpsmt1.executeQuery();
                            this.um_adv_conra020c.serial_no[0] = isStrNull(gjArr[i2].serial_no);
                            this.um_adv_conra020c.fac_nm[0] = isStrNull(gjArr[i2].fact_nm);
                            this.um_adv_conra020c.fac_zip_cd[0] = isStrNull(gjArr[i2].공장우편번호);
                            this.um_adv_conra020c.fac_add[0] = isStrNull(gjArr[i2].공장주소);
                            this.um_adv_conra020c.fac_add2[0] = isStrNull(gjArr[i2].공장나머지주소);
                            this.um_adv_conra020c.fac_phone[0] = isStrNull(gjArr[i2].공장전화번호);
                            this.um_adv_conra020c.fac_fax[0] = isStrNull(gjArr[i2].공장FAX번호);
                            this.um_adv_conra020c.fac_rent_yn[0] = isStrNull(gjArr[i2].공장임대여부);
                            this.um_adv_conra020c.fac_rent_start_dt[0] = isStrNull(gjArr[i2].공장임대시작일자);
                            this.um_adv_conra020c.fac_rent_end_dt[0] = isStrNull(gjArr[i2].공장임대종료일자);
                            this.facReplacePutValues(dsource, this.um_adv_conra020c.serial_no, this.일련번호, i2, 0, "I");
                            this.facReplacePutValues(dsource, this.um_adv_conra020c.fac_nm, this.공장명, i2, 0, "I");
                            this.facReplacePutValues(dsource, this.um_adv_conra020c.fac_zip_cd, this.공장우편번호, i2, 0, "I");
                            this.facReplacePutValues(dsource, this.um_adv_conra020c.fac_add, this.공장주소, i2, 0, "I");
                            this.facReplacePutValues(dsource, this.um_adv_conra020c.fac_add2, this.공장나머지주소, i2, 0, "I");
                            this.facReplacePutValues(dsource, this.um_adv_conra020c.fac_phone, this.공장전화번호, i2, 0, "I");
                            this.facReplacePutValues(dsource, this.um_adv_conra020c.fac_fax, this.공장FAX번호, i2, 0, "I");
                            this.facReplacePutValues(dsource, this.um_adv_conra020c.fac_rent_yn, this.공장임대여부, i2, 0, "I");
                            this.facReplacePutValues(dsource, this.um_adv_conra020c.fac_rent_start_dt, this.공장임대시작일자, i2, 0, "I");
                            this.facReplacePutValues(dsource, this.um_adv_conra020c.fac_rent_end_dt, this.공장임대종료일자, i2, 0, "I");
                            Log.debug("um_adv_conra020c.사업자등록번호[0]" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                            Log.debug("um_adv_conra020c.일련번호[0]" + isStrNull(gjArr[i2].serial_no));
                            Log.debug("um_adv_conra020c.공장명[0]" + isStrNull(gjArr[i2].fact_nm));
                            Log.debug("um_adv_conra020c.공장우편번호[0]" + isStrNull(gjArr[i2].공장우편번호));
                            Log.debug("um_adv_conra020c.공장주소[0]" + isStrNull(gjArr[i2].공장주소));
                            Log.debug("um_adv_conra020c.공장나머지주소[0]" + isStrNull(gjArr[i2].공장나머지주소));
                            Log.debug("um_adv_conra020c.공장전화번호[0]" + isStrNull(gjArr[i2].공장전화번호));
                            Log.debug("um_adv_conra020c.공장FAX번호[0]" + isStrNull(gjArr[i2].공장FAX번호));
                            Log.debug("um_adv_conra020c.공장임대여부[0]" + isStrNull(gjArr[i2].공장임대여부));
                            Log.debug("um_adv_conra020c.공장임대시작일자[0]" + isStrNull(gjArr[i2].공장임대시작일자));
                            Log.debug("um_adv_conra020c.공장임대종료일자[0]" + isStrNull(gjArr[i2].공장임대종료일자));
                        }
                        aflag = false;
                    }
                }
                Log.debug("공장정보 변경 끝");
                Log.debug("조달품목 변경 끝");
                if (ett2.length > 0) {
                    for (int i2 = 0; i2 < ett2.length; ++i2) {
                        for (int j2 = 0; j2 < msCount - 1; ++j2) {
                            if (ett2[i2].getLicenseCode().equals(msArr[j2].licencse_cd)) {
                                aflag = true;
                                if (!isStrNull((ett2[i2].getLicenseNo() == null) ? "" : ett2[i2].getLicenseNo()).equals(isStrNull(msArr[j2].license_no))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett2[i2].getLicenseBeginDate() == null) ? "" : (String.valueOf(ett2[i2].getLicenseBeginDate().substring(0, 4)) + ett2[i2].getLicenseBeginDate().substring(5, 7) + ett2[i2].getLicenseBeginDate().substring(8, 10))).equals(isStrNull(msArr[j2].issued_dt))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett2[i2].getLicenseEndDate() == null) ? "" : (String.valueOf(ett2[i2].getLicenseEndDate().substring(0, 4)) + ett2[i2].getLicenseEndDate().substring(5, 7) + ett2[i2].getLicenseEndDate().substring(8, 10))).equals(isStrNull(msArr[j2].expiry_dt))) {
                                    bflag = true;
                                }
                                if (ett2[i2].getSigongAccount() != Long.parseLong(isStrNull(ComStr.replace(msArr[j2].const_abil_eval_amt, ",", "")).equals("") ? "0" : isStrNull(ComStr.replace(msArr[j2].const_abil_eval_amt, ",", "")))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett2[i2].getPeonggaYear() == null) ? "0" : ett2[i2].getPeonggaYear()).equals(isStrNull(msArr[j2].eval_std_year))) {
                                    bflag = true;
                                }
                                if (!isStrNull((ett2[i2].getDpLicenseYN() == null) ? "" : ett2[i2].getDpLicenseYN()).equals(isStrNull(msArr[j2].mast_yn))) {
                                    bflag = true;
                                }
                                if (bflag) {
                                    msquery1 = "UPDATE UM_LICENSE_FACTS SET LICENSE_NO=?, LICENSE_ISSUED_DT=?, LICENSE_EXPIRY_DT=?, CONST_ABIL_EVAL_AMT=?, EVAL_STD_YEAR=?,ASSOC_UPDATE_DT=to_date(?,'yyyy-mm-dd hh24:mi:ss'), MAST_LICENSE_YN=?, UPDATE_DT=SYSDATE WHERE BIZ_REG_NO=? AND LICENSE_CD=?";
                                    if (mspsmt1 != null) {
                                        try {
                                            mspsmt1.close();
                                        }
                                        catch (Exception ex21) {}
                                    }
                                    mspsmt1 = con.prepareStatement(msquery1);
                                    mspsmt1.setString(1, isStrNull(msArr[j2].license_no));
                                    mspsmt1.setString(2, msArr[j2].issued_dt);
                                    mspsmt1.setString(3, msArr[j2].expiry_dt);
                                    if (Sipyongvalue != null || !Sipyongvalue.equals("")) {
                                        mspsmt1.setString(4, Upjongcode.equals(isStrNull(msArr[j2].licencse_cd)) ? Sipyongvalue : ComStr.replace(msArr[j2].const_abil_eval_amt, ",", ""));
                                        mspsmt1.setString(5, Upjongcode.equals(isStrNull(msArr[j2].licencse_cd)) ? Sipyongyear : isStrNull(msArr[j2].eval_std_year));
                                        mspsmt1.setString(6, Upjongcode.equals(isStrNull(msArr[j2].licencse_cd)) ? Sipyongyear2 : "");
                                    }
                                    else {
                                        mspsmt1.setString(4, ComStr.replace(msArr[j2].const_abil_eval_amt, ",", ""));
                                        mspsmt1.setString(5, isStrNull(msArr[j2].eval_std_year));
                                        mspsmt1.setString(6, "");
                                    }
                                    mspsmt1.setString(7, isStrNull(msArr[j2].mast_yn));
                                    mspsmt1.setString(8, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                                    mspsmt1.setString(9, ett2[i2].getLicenseCode());
                                    mspsmt1.executeUpdate();
                                    bflag = false;
                                    this.um_adv_conra020c.serial_no3[0] = isStrNull(msArr[j2].serial_3);
                                    final int seq_num2 = isIntNull(msArr[j2].serial_3);
                                    this.um_adv_conra020c.license_no[0] = isStrNull(msArr[j2].license_no);
                                    this.um_adv_conra020c.license_cd[0] = isStrNull(ett2[i2].getLicenseCode());
                                    this.um_adv_conra020c.license_nm[0] = isStrNull(msArr[j2].license_nm);
                                    this.um_adv_conra020c.license_issued_dt[0] = isStrNull(msArr[j2].issued_dt);
                                    this.um_adv_conra020c.license_expiry_dt[0] = isStrNull(msArr[j2].expiry_dt);
                                    if (Sipyongvalue != null || !Sipyongvalue.equals("")) {
                                        this.um_adv_conra020c.const_abil_eval_amt[0] = (Upjongcode.equals(isStrNull(msArr[j2].licencse_cd)) ? Sipyongvalue : ComStr.replace(msArr[j2].const_abil_eval_amt, ",", ""));
                                        this.um_adv_conra020c.eval_std_year[0] = (Upjongcode.equals(isStrNull(msArr[j2].licencse_cd)) ? Sipyongyear : isStrNull(msArr[j2].eval_std_year));
                                    }
                                    else {
                                        this.um_adv_conra020c.const_abil_eval_amt[0] = ComStr.replace(msArr[j2].const_abil_eval_amt, ",", "");
                                        this.um_adv_conra020c.eval_std_year[0] = isStrNull(msArr[j2].eval_std_year);
                                    }
                                    this.um_adv_conra020c.mast_license_yn[0] = isStrNull(msArr[j2].mast_yn);
                                    this.um_adv_conra020c.mast_license_fm[0] = "";
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.serial_no3, this.일련번호3, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_no, this.면허번호, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_cd, this.면허코드, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_nm, this.면허코드명, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_issued_dt, this.면허취득일자, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_expiry_dt, this.면허만료일자, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.const_abil_eval_amt, this.시공능력평가액, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.eval_std_year, this.평가액기준년도, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_yn, this.대표면허여부, j2, 0, "U");
                                    this.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_fm, this.대표면허포멧, j2, 0, "U");
                                }
                            }
                        }
                        if (!aflag) {
                            msquery1 = "DELETE FROM UM_LICENSE_FACTS WHERE BIZ_REG_NO=? AND LICENSE_CD=?";
                            if (mspsmt1 != null) {
                                try {
                                    mspsmt1.close();
                                }
                                catch (Exception ex22) {}
                            }
                            mspsmt1 = con.prepareStatement(msquery1);
                            mspsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                            mspsmt1.setString(2, ett2[i2].getLicenseCode());
                            mspsmt1.executeUpdate();
                            this.um_adv_conra020c.serial_no3[0] = Integer.toString(i2 + 1);
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.serial_no3, this.일련번호3, i2, 0, "D");
                        }
                        aflag = false;
                    }
                }
                if (msCount - 1 > 0) {
                    for (int i2 = 0; i2 < msCount - 1; ++i2) {
                        for (int j2 = 0; j2 < ett2.length; ++j2) {
                            if (msArr[i2].licencse_cd.equals(ett2[j2].getLicenseCode())) {
                                aflag = true;
                            }
                        }
                        if (!aflag) {
                            msquery1 = "INSERT INTO UM_LICENSE_FACTS (BIZ_REG_NO, LICENSE_CD, LICENSE_NO, LICENSE_ISSUED_DT, LICENSE_EXPIRY_DT, CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR, ASSOC_UPDATE_DT, MAST_LICENSE_YN, REG_DT, UPDATE_DT) VALUES (?, ?, ?, ?, ?, ?, ?,to_date(?,'yyyy-mm-dd hh24:mi:ss'), ?, SYSDATE, SYSDATE) ";
                            if (mspsmt1 != null) {
                                try {
                                    mspsmt1.close();
                                }
                                catch (Exception ex23) {}
                            }
                            mspsmt1 = con.prepareStatement(msquery1);
                            mspsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                            mspsmt1.setString(2, isStrNull(msArr[i2].licencse_cd));
                            mspsmt1.setString(3, isStrNull(msArr[i2].license_no));
                            mspsmt1.setString(4, msArr[i2].issued_dt);
                            mspsmt1.setString(5, msArr[i2].expiry_dt);
                            if (Sipyongvalue != null || !Sipyongvalue.equals("")) {
                                mspsmt1.setString(6, Upjongcode.equals(isStrNull(msArr[i2].licencse_cd)) ? Sipyongvalue : ComStr.replace(msArr[i2].const_abil_eval_amt, ",", ""));
                                mspsmt1.setString(7, Upjongcode.equals(isStrNull(msArr[i2].licencse_cd)) ? Sipyongyear : isStrNull(msArr[i2].eval_std_year));
                                mspsmt1.setString(8, Upjongcode.equals(isStrNull(msArr[i2].licencse_cd)) ? Sipyongyear2 : "");
                            }
                            else {
                                mspsmt1.setString(6, ComStr.replace(msArr[i2].const_abil_eval_amt, ",", ""));
                                mspsmt1.setString(7, isStrNull(msArr[i2].eval_std_year));
                                mspsmt1.setString(8, "");
                            }
                            mspsmt1.setString(9, isStrNull(msArr[i2].mast_yn));
                            mspsmt1.executeUpdate();
                            this.um_adv_conra020c.serial_no3[0] = isStrNull(msArr[i2].serial_3);
                            this.um_adv_conra020c.license_no[0] = isStrNull(msArr[i2].license_no);
                            this.um_adv_conra020c.license_cd[0] = isStrNull(msArr[i2].licencse_cd);
                            this.um_adv_conra020c.license_nm[0] = isStrNull(msArr[i2].license_nm);
                            this.um_adv_conra020c.license_issued_dt[0] = isStrNull(msArr[i2].issued_dt);
                            this.um_adv_conra020c.license_expiry_dt[0] = isStrNull(msArr[i2].expiry_dt);
                            if (Sipyongvalue != null || !Sipyongvalue.equals("")) {
                                this.um_adv_conra020c.const_abil_eval_amt[0] = (Upjongcode.equals(isStrNull(msArr[i2].licencse_cd)) ? Sipyongvalue : ComStr.replace(msArr[i2].const_abil_eval_amt, ",", ""));
                                this.um_adv_conra020c.eval_std_year[0] = (Upjongcode.equals(isStrNull(msArr[i2].licencse_cd)) ? Sipyongyear : isStrNull(msArr[i2].eval_std_year));
                            }
                            else {
                                this.um_adv_conra020c.const_abil_eval_amt[0] = ComStr.replace(msArr[i2].const_abil_eval_amt, ",", "");
                                this.um_adv_conra020c.eval_std_year[0] = isStrNull(msArr[i2].eval_std_year);
                            }
                            this.um_adv_conra020c.mast_license_yn[0] = isStrNull(msArr[i2].mast_yn);
                            this.um_adv_conra020c.mast_license_fm[0] = "";
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.serial_no3, this.일련번호3, i2, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_no, this.면허번호, i2, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_cd, this.면허코드, i2, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_nm, this.면허코드명, i2, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_issued_dt, this.면허취득일자, i2, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_expiry_dt, this.면허만료일자, i2, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.const_abil_eval_amt, this.시공능력평가액, i2, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.eval_std_year, this.평가액기준년도, i2, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_yn, this.대표면허여부, i2, 0, "I");
                            this.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_fm, this.대표면허포멧, i2, 0, "I");
                        }
                        aflag = false;
                    }
                }
                Log.debug("면허사항 변경 끝");
                final String[] MData = this.getMainData();
                miquery1 = " INSERT INTO UM_SUPPLIER_ENTER_MAST_HIST (  BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM,  COMMENCEMENT_DT, ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS,  CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL,  EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR,  DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECIAL_GOODS_YN,  REG_DT, UPDATE_DT, REPR_BIZ_APPROVE_YN, MANAGER_ID, MAST_GOOD_CLS_NO,/* 대표면허코드,*/ MAST_REPR_NM, PERMIT_BRANCH  ) VALUES (  ?, ?, ?, ?,  ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, SYSDATE, 'Y', ?,  ?, ?, ?  ) ";
                if (mipsmt1 != null) {
                    try {
                        mipsmt1.close();
                    }
                    catch (Exception ex24) {}
                }
                mipsmt1 = con.prepareStatement(miquery1);
                mipsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                mipsmt1.setString(2, isStrNull(this.um_adv_conra020c.nationality[0]));
                mipsmt1.setString(3, isStrNull(this.um_adv_conra020c.biz_nm[0]));
                mipsmt1.setString(4, isStrNull(this.um_adv_conra020c.biz_en_nm[0]));
                mipsmt1.setString(5, this.um_adv_conra020c.commentcement_dt[0]);
                mipsmt1.setString(6, this.um_adv_conra020c.establish_dt[0]);
                mipsmt1.setString(7, isStrNull(upmu3));
                mipsmt1.setString(8, isStrNull(jejoGubun));
                mipsmt1.setString(9, isStrNull(this.um_adv_conra020c.corp_reg_no[0]));
                mipsmt1.setString(10, isStrNull(MData[2]));
                mipsmt1.setString(11, isStrNull(this.um_adv_conra020c.biz_cls_2[0]));
                mipsmt1.setString(12, isStrNull("0000"));
                if ("".equals(this.um_adv_conra020c.capital[0])) {
                    mipsmt1.setInt(13, 0);
                }
                else {
                    mipsmt1.setString(13, this.um_adv_conra020c.capital[0]);
                }
                mipsmt1.setString(14, this.um_adv_conra020c.employee_count_[0]);
                mipsmt1.setString(15, isStrNull("0000-00"));
                mipsmt1.setString(16, isStrNull(this.um_adv_conra020c.zip_cd[0]));
                mipsmt1.setString(17, isStrNull(this.um_adv_conra020c.area_cd[0]));
                mipsmt1.setString(18, isStrNull(this.um_adv_conra020c.addr[0]));
                mipsmt1.setString(19, isStrNull(this.um_adv_conra020c.addr_detail[0]));
                mipsmt1.setString(20, isStrNull(this.um_adv_conra020c.phone_no[0]));
                mipsmt1.setString(21, isStrNull(this.um_adv_conra020c.fax[0]));
                mipsmt1.setString(22, isStrNull(this.um_adv_conra020c.homepage[0]));
                mipsmt1.setString(23, isStrNull(this.um_adv_conra020c.special_good_yn[0]));
                mipsmt1.setString(24, isStrNull(MData[4]));
                mipsmt1.setString(25, isStrNull(procID));
                mipsmt1.setString(26, " ");
                if (dpi != -1) {
                    mipsmt1.setString(27, isStrNull(dpArr[dpi].repr_nm));
                }
                else {
                    mipsmt1.setString(27, " ");
                }
                mipsmt1.setString(28, isStrNull(jicheong));
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
                    for (int i3 = 0; i3 < this.um_adv_conra020c.repr_nm.length; ++i3) {
                        Log.debug("um_adv_conra020c.대표자명[" + i3 + "]" + isStrNull(this.um_adv_conra020c.repr_nm[i3]) + "::");
                    }
                }
                jmquery1 = " UPDATE UM_EDOC_STATE SET  PROCESS_ST = ?, PROCESS_RSON = ?, COM_NM = ?, REPR_NM = ?, ZIP_CD = ?,  ADDR = ?, DETAIL_ADDR = ?, PHONE_NO = ?, WEBSITE = ?, PERMIT_BRANCH = ?,  PROCESS_DT = SYSDATE  WHERE TRANS_NO = '" + transNo + "' " + " AND MOD_CLS = '2' ";
                if (jmpsmt1 != null) {
                    try {
                        jmpsmt1.close();
                    }
                    catch (Exception ex25) {}
                }
                jmpsmt1 = con.prepareStatement(jmquery1);
                jmpsmt1.setString(1, isStrNull(status));
                jmpsmt1.setString(2, isStrNull(this.um_adv_conra020c.manage_reason[0]));
                jmpsmt1.setString(3, isStrNull(this.um_adv_conra020c.biz_nm[0]));
                jmpsmt1.setString(4, isStrNull(this.um_adv_conra020c.repr_nm[0]));
                jmpsmt1.setString(5, isStrNull(this.um_adv_conra020c.zip_cd[0]));
                jmpsmt1.setString(6, isStrNull(this.um_adv_conra020c.addr[0]));
                jmpsmt1.setString(7, isStrNull(this.um_adv_conra020c.addr_detail[0]));
                jmpsmt1.setString(8, isStrNull(this.um_adv_conra020c.phone_no[0]));
                jmpsmt1.setString(9, isStrNull(this.um_adv_conra020c.homepage[0]));
                jmpsmt1.setString(10, isStrNull(jicheong));
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
                this.um_adv_conra020c.permit_branch[0] = isStrNull(jicheong);
                this.replacePutValues(dsource, this.um_adv_conra020c.permit_branch);
                final String[] MainData = this.getMainData();
                final String[][] DPData = this.getDPData();
                final String[][] DDPData = this.getDDPData();
                final String[][] JPData = this.getJPData();
                final String[][] JP1Data = this.getJP1Data();
                final String[][] GMData = this.getGMData();
                final String[][] DP1Data = this.getDP1Data();
                final String[][] IPData = this.getIPData();
                final String[][] BRData = this.getBRData();
                if (!((MainData[0] == null) ? "" : MainData[0]).equals(this.um_adv_conra020c.biz_nm[0])) {
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", MainData[0], this.um_adv_conra020c.biz_nm[0], "상호명", procID, con);
                    ++ilno;
                }
                if (!((MainData[1] == null) ? "" : MainData[1]).equals(upmu3)) {
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", MainData[1], upmu3, "업무구분", procID, con);
                    ++ilno;
                }
                if (!((MainData[2] == null) ? "" : MainData[2]).equals(this.um_adv_conra020c.biz_cls_1[0])) {
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", MainData[2], this.um_adv_conra020c.biz_cls_1[0], "기업구분1", procID, con);
                    ++ilno;
                }
                if (!((MainData[3] == null) ? "" : MainData[3]).equals(this.um_adv_conra020c.addr[0]) || !((MainData[5] == null) ? "" : MainData[5]).equals(this.um_adv_conra020c.addr_detail[0])) {
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", String.valueOf(MainData[3]) + " " + MainData[5], String.valueOf(this.um_adv_conra020c.addr[0]) + " " + this.um_adv_conra020c.addr_detail[0], "주소", procID, con);
                    ++ilno;
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", MainData[7], this.um_adv_conra020c.area_cd[0], "지역코드", procID, con);
                    ++ilno;
                }
                if (!((MainData[6] == null) ? "" : MainData[6]).equals(this.um_adv_conra020c.capital[0])) {
                    this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", MainData[6], this.um_adv_conra020c.capital[0], "자본금", procID, con);
                    ++ilno;
                }
                String beComment1 = "";
                String beComment2 = "";
                String beComment3 = "";
                String beComment4 = "";
                final String beComment5 = "";
                final String beComment6 = "";
                final String beComment7 = "";
                for (int i4 = 0; i4 < dpCount - 1; ++i4) {
                    int x = 0;
                    int x2 = 0;
                    final int x3 = 0;
                    for (int j3 = 0; j3 < DPData.length; ++j3) {
                        if (dpArr[i4].repr_ident_no.equals(DPData[j3][0])) {
                            x = 1;
                            if (!dpArr[i4].repr_nm.equals(DPData[j3][1])) {
                                beComment1 = DPData[j3][1];
                                beComment2 = DPData[j3][0];
                                x2 = 1;
                                break;
                            }
                        }
                    }
                    if (x == 0 && !dpArr[i4].repr_ident_no.equals("")) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "5", "I", "", String.valueOf(dpArr[i4].repr_nm) + " : " + dpArr[i4].repr_ident_no, "대표자", procID, con);
                        ++ilno;
                    }
                    else if (x == 1 && x2 == 1) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "5", "U", String.valueOf(beComment1) + " : " + beComment2, String.valueOf(dpArr[i4].repr_nm) + " : " + dpArr[i4].repr_ident_no, "대표자", procID, con);
                        ++ilno;
                    }
                }
                for (int i4 = 0; i4 < DPData.length; ++i4) {
                    int x = 0;
                    for (int j4 = 0; j4 < dpCount - 1; ++j4) {
                        if (dpArr[j4].repr_ident_no.equals(DPData[i4][0])) {
                            x = 1;
                            break;
                        }
                    }
                    if (x == 0) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "5", "D", String.valueOf(DPData[i4][1]) + " : " + DPData[i4][0], "", "대표자", procID, con);
                        ++ilno;
                    }
                }
                for (int i4 = 0; i4 < dpCount - 1; ++i4) {
                    int x = 0;
                    int x2 = 0;
                    for (int j5 = 0; j5 < DDPData.length; ++j5) {
                        if (dpArr[i4].repr_ident_no.equals(DDPData[j5][0])) {
                            x = 1;
                            if (!dpArr[i4].mast_repr_yn.equals(DDPData[j5][2])) {
                                beComment1 = DDPData[j5][1];
                                x2 = 1;
                                break;
                            }
                        }
                    }
                    if (x == 1 && x2 == 1) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "5", "U", beComment1, dpArr[dpi].repr_nm, "대표대표자여부", procID, con);
                        ++ilno;
                    }
                }
                for (int i4 = 0; i4 < brCount - 1; ++i4) {
                    int x = 0;
                    int x2 = 0;
                    final int x3 = 0;
                    for (int j3 = 0; j3 < BRData.length; ++j3) {
                        if (dpArr2[i4].branch_biz_reg_no.equals(BRData[j3][0])) {
                            x = 1;
                            if (!dpArr2[i4].branch_repr_nm.equals(BRData[j3][8])) {
                                beComment1 = BRData[j3][8];
                                x2 = 1;
                                break;
                            }
                        }
                    }
                    if (x == 0 && !dpArr2[i4].branch_biz_reg_no.equals("")) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "I", "", String.valueOf(dpArr2[i4].branch_biz_reg_no) + " : " + dpArr2[i4].branch_repr_nm + " : " + dpArr2[i4].branch_repr_ident_no, "지사항목", procID, con);
                        ++ilno;
                    }
                    else if (x == 1 && x2 == 1) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "U", beComment1, String.valueOf(dpArr2[i4].branch_repr_nm) + " : " + dpArr2[i4].branch_repr_ident_no, "지사항목", procID, con);
                        ++ilno;
                    }
                }
                for (int i4 = 0; i4 < BRData.length; ++i4) {
                    int x = 0;
                    for (int j4 = 0; j4 < brCount - 1; ++j4) {
                        if (dpArr2[j4].branch_biz_reg_no.equals(BRData[i4][0])) {
                            x = 1;
                            break;
                        }
                    }
                    if (x == 0) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "1", "D", BRData[i4][0], "", "지사항목", procID, con);
                        ++ilno;
                    }
                }
                for (int i4 = 0; i4 < dpCount2 - 1; ++i4) {
                    int x = 0;
                    int x2 = 0;
                    final int x3 = 0;
                    for (int j3 = 0; j3 < IPData.length; ++j3) {
                        if (dpArr3[i4].identNo.equals(IPData[j3][0])) {
                            x = 1;
                            if (!dpArr3[i4].agentNm.equals(IPData[j3][1])) {
                                beComment1 = IPData[j3][1];
                                beComment2 = IPData[j3][0];
                                x2 = 1;
                                break;
                            }
                        }
                    }
                    if (x == 0 && !dpArr3[i4].identNo.equals("")) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "6", "I", "", String.valueOf(dpArr3[i4].agentNm) + " : " + dpArr3[i4].identNo, "입찰대리인", procID, con);
                        ++ilno;
                    }
                    else if (x == 1 && x2 == 1) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "6", "U", String.valueOf(beComment1) + " : " + beComment2, String.valueOf(dpArr3[i4].agentNm) + " : " + dpArr3[i4].identNo, "입찰대리인", procID, con);
                        ++ilno;
                    }
                }
                for (int i4 = 0; i4 < IPData.length; ++i4) {
                    int x = 0;
                    for (int j4 = 0; j4 < dpCount2 - 1; ++j4) {
                        if (dpArr3[j4].identNo.equals(IPData[i4][0])) {
                            x = 1;
                            break;
                        }
                    }
                    if (x == 0) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "6", "D", String.valueOf(IPData[i4][1]) + " : " + IPData[i4][0], "", "입찰대리인", procID, con);
                        ++ilno;
                    }
                }
                final String date = "";
                final String date2 = "";
                final String date3 = "";
                final String date4 = "";
                final String Sipyong_value = "";
                boolean flg = false;
                String flg2 = "";
                for (int i5 = 0; i5 < msCount - 1; ++i5) {
                    int x4 = 0;
                    int x5 = 0;
                    int x6 = 0;
                    flg = false;
                    for (int j6 = 0; j6 < GMData.length; ++j6) {
                        if (msArr[i5].licencse_cd.equals(GMData[j6][0])) {
                            x4 = 1;
                            if (!GMData[j6][1].equals(ComStr.replace(msArr[i5].const_abil_eval_amt, ",", ""))) {
                                beComment1 = GMData[j6][1];
                                beComment2 = GMData[j6][0];
                                beComment3 = GMData[j6][2];
                                beComment4 = GMData[j6][3];
                                x5 = 1;
                            }
                            if (!GMData[j6][3].equals(msArr[i5].expiry_dt)) {
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
                    flg = this.getIsOKUpJong(msArr[i5].licencse_cd, con);
                    flg2 = this.getIsOKSipyong(this.um_adv_conra020c.biz_reg_no[0], msArr[i5].licencse_cd, con);
                    if (x4 == 0 && !msArr[i5].licencse_cd.equals("")) {
                        if (!msArr[i5].const_abil_eval_amt.equals("") && !msArr[i5].expiry_dt.equals("")) {
                            if (flg) {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", String.valueOf(msArr[i5].license_nm) + "(시공능력평가액 : " + ComStr.replace(msArr[i5].const_abil_eval_amt, ",", "") + ", 유효기간만료일자 : " + msArr[i5].expiry_dt + ")", "업종", procID, con);
                                ++ilno;
                            }
                            else {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", String.valueOf(msArr[i5].license_nm) + "(시공능력평가액 : " + ComStr.replace(msArr[i5].const_abil_eval_amt, ",", "") + ")", "업종", procID, con);
                                ++ilno;
                            }
                        }
                        else if (msArr[i5].const_abil_eval_amt.equals("") && !msArr[i5].expiry_dt.equals("")) {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", String.valueOf(msArr[i5].license_nm) + "(유효기간만료일자 : " + msArr[i5].expiry_dt + ")", "업종", procID, con);
                            ++ilno;
                        }
                        else if (!msArr[i5].const_abil_eval_amt.equals("") && msArr[i5].expiry_dt.equals("")) {
                            if (flg2 != null && !flg2.equals("")) {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", String.valueOf(msArr[i5].license_nm) + "(시공능력평가액 : " + flg2 + " 협회자료갱신" + ")", "업종", procID, con);
                                ++ilno;
                            }
                            else {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", String.valueOf(msArr[i5].license_nm) + "(시공능력평가액 : " + ComStr.replace(msArr[i5].const_abil_eval_amt, ",", "") + ")", "업종", procID, con);
                                ++ilno;
                            }
                        }
                        else {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "I", "", msArr[i5].license_nm, "업종", procID, con);
                            ++ilno;
                        }
                    }
                    else if (x4 == 1 && x5 == 1 && x6 == 1) {
                        if (flg) {
                            if (flg2 != null && !flg2.equals("")) {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(시공능력평가액 : " + beComment1 + ", 유효기간만료일자 : " + beComment4 + ")", String.valueOf(msArr[i5].license_nm) + "(시공능력평가액 : " + flg2 + "협회자료갱신" + ", 유효기간만료일자 : " + msArr[i5].expiry_dt + ")", "업종", procID, con);
                                ++ilno;
                            }
                            else {
                                this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(시공능력평가액 : " + beComment1 + ", 유효기간만료일자 : " + beComment4 + ")", String.valueOf(msArr[i5].license_nm) + "(시공능력평가액 : " + ComStr.replace(msArr[i5].const_abil_eval_amt, ",", "") + ", 유효기간만료일자 : " + msArr[i5].expiry_dt + ")", "업종", procID, con);
                                ++ilno;
                            }
                        }
                        else if (flg2 != null && !flg2.equals("")) {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(시공능력평가액 : " + beComment1 + ")", String.valueOf(msArr[i5].license_nm) + "(시공능력평가액 : " + flg2 + "협회자료갱신" + ")", "업종", procID, con);
                            ++ilno;
                        }
                        else {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(시공능력평가액 : " + beComment1 + ")", String.valueOf(msArr[i5].license_nm) + "(시공능력평가액 : " + ComStr.replace(msArr[i5].const_abil_eval_amt, ",", "") + ")", "업종", procID, con);
                            ++ilno;
                        }
                    }
                    else if (x4 == 1 && x5 == 1 && x6 == 0) {
                        if (flg2 != null && !flg2.equals("")) {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(시공능력평가액 : " + beComment1 + ")", String.valueOf(msArr[i5].license_nm) + "(시공능력평가액 : " + flg2 + "협회자료갱신" + ")", "업종", procID, con);
                            ++ilno;
                        }
                        else {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(시공능력평가액 : " + beComment1 + ")", String.valueOf(msArr[i5].license_nm) + "(시공능력평가액 : " + ComStr.replace(msArr[i5].const_abil_eval_amt, ",", "") + ")", "업종", procID, con);
                            ++ilno;
                        }
                    }
                    else if (x4 == 1 && x5 == 0 && x6 == 1) {
                        this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "U", String.valueOf(beComment3) + "(유효기간만료일자 : " + beComment4 + ")", String.valueOf(msArr[i5].license_nm) + "(유효기간만료일자 : " + msArr[i5].expiry_dt + ")", "업종", procID, con);
                        ++ilno;
                    }
                }
                for (int i5 = 0; i5 < GMData.length; ++i5) {
                    int x4 = 0;
                    for (int j7 = 0; j7 < msCount - 1; ++j7) {
                        if (msArr[j7].licencse_cd.equals(GMData[i5][0])) {
                            x4 = 1;
                            break;
                        }
                    }
                    if (x4 == 0) {
                        if (flg) {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "D", String.valueOf(GMData[i5][2]) + "( 시공능력평가액:" + GMData[i5][1] + ", 유효기간만료일자: " + GMData[i5][3] + ")", "", "업종", procID, con);
                            ++ilno;
                        }
                        else {
                            this.icInsert(this.um_adv_conra020c.biz_reg_no[0], ilno, "4", "D", String.valueOf(GMData[i5][2]) + "( 시공능력평가액:" + GMData[i5][1] + ")", "", "업종", procID, con);
                            ++ilno;
                        }
                    }
                }
                SQL = " SELECT COUNT(*) FROM UM_ENTER_STATE                           WHERE BIZ_REG_NO= ?\t\t\t\t\t\t\t\t\t\t\t\t AND STATE_CLS = '07' AND MANAGER_ID = 'ADMIN'                     ";
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
                    catch (Exception ex28) {}
                }
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (Exception ex29) {}
                }
                if (stcount != 0) {
                    SQL = " DELETE FROM UM_ENTER_STATE\t\t\t\t\t\t                     WHERE BIZ_REG_NO= ?\t\t\t\t\t\t\t\t\t\t\t\t AND STATE_CLS = '07' AND MANAGER_ID = 'ADMIN'                     ";
                    if (psmt != null) {
                        try {
                            psmt.close();
                        }
                        catch (Exception ex30) {}
                    }
                    psmt = con.prepareStatement(SQL);
                    psmt.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    psmt.executeUpdate();
                }
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex31) {}
                }
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (Exception ex32) {}
                }
                query1 = "\t SELECT BIZ_REG_NO FROM UM_REC_SUPPLIER_ENTER_MAST        WHERE CORP_REG_NO IN (SELECT CORP_REG_NO\t\t\t\t\t   \t\t\t\t\t\t\t\t\tFROM UM_REC_SUPPLIER_ENTER_MAST\t\t   \t\t\t\t\t\t\t\t\tWHERE BIZ_REG_NO  =?)\t\t       AND HEADQUARTER_YN = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ";
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex33) {}
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
                        catch (Exception ex34) {}
                    }
                    gjpsmt1 = con.prepareStatement(query2);
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                    dpquery1 = " INSERT INTO UM_SUPPLIER_ENTER_ITEMS(BIZ_REG_NO,GOOD_CLS_NO, PERMIT_NO, PERMIT_INSTITU, INCOME_3YEARS,\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t DIRECT_PRODUCTION_YN, MAST_GOODS_YN, REG_DT, UPDATE_DT, RESERVED_ITEM_OPTION, HAVE_RESERVED_GOODS,\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t  \t\t\t\t DIRECT_PRODUCTION_DOC, AVAIL_PERIOD_START_DT, AVAIL_PERIOD_END_DT, INDUSTRY_CLS_CD, ISSUE_INSTITU, DOC_NM)\t\t\t\t\tSELECT  '" + branchsaupNo + "',GOOD_CLS_NO, PERMIT_NO, PERMIT_INSTITU, INCOME_3YEARS,DIRECT_PRODUCTION_YN, MAST_GOODS_YN, REG_DT,\t\t" + " \t\t\t\t    UPDATE_DT, RESERVED_ITEM_OPTION, HAVE_RESERVED_GOODS,DIRECT_PRODUCTION_DOC, AVAIL_PERIOD_START_DT, AVAIL_PERIOD_END_DT,\t\t\t\t\t\t\t\t" + " \t\t\t\t    INDUSTRY_CLS_CD, ISSUE_INSTITU, DOC_NM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t\tFROM  UM_SUPPLIER_ENTER_ITEMS WHERE BIZ_REG_NO = ?\t";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex35) {}
                    }
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    query2 = " DELETE FROM UM_LICENSE_FACTS  WHERE BIZ_REG_NO = '" + branchsaupNo + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex36) {}
                    }
                    gjpsmt1 = con.prepareStatement(query2);
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                    dpquery1 = " INSERT INTO UM_LICENSE_FACTS(BIZ_REG_NO, LICENSE_CD, LICENSE_NO, LICENSE_ISSUED_DT, LICENSE_EXPIRY_DT, CONST_ABIL_EVAL_AMT,\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t EVAL_STD_YEAR,MAST_LICENSE_YN,REG_DT,UPDATE_DT,ASSOC_UPDATE_DT,매출액,입찰참여상한금액)\t\t\t\t\t \t\tSELECT  '" + branchsaupNo + "', LICENSE_CD, LICENSE_NO, LICENSE_ISSUED_DT, LICENSE_EXPIRY_DT, CONST_ABIL_EVAL_AMT,\t\t\t\t\t\t\t\t\t\t\t\t" + " \t\t\t\t\tEVAL_STD_YEAR,MAST_LICENSE_YN,REG_DT,UPDATE_DT,ASSOC_UPDATE_DT,매출액,입찰참여상한금액\t\t\t\t\t\t\t\t\t\t\t\t" + " \t\tFROM UM_LICENSE_FACTS WHERE BIZ_REG_NO = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex37) {}
                    }
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    query2 = " DELETE FROM UM_FACTORY_INFO  WHERE BIZ_REG_NO = '" + branchsaupNo + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex38) {}
                    }
                    gjpsmt1 = con.prepareStatement(query2);
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                    dpquery1 = " INSERT INTO UM_FACTORY_INFO(BIZ_REG_NO,SERIAL_NO,FACTORY_NM,FACTORY_ZIP_CD,FACTORY_ADDR,FACTORY_ADDR2,FACTORY_PHONE_NO,\t\t\t\t\t\t \t\t\t\t\t\t\t\t\t\t\t\t FACTORY_FAX,REG_DT,UPDATE_DT,FACTORY_RENT_YN,FACTORY_RENT_START_DT,FACTORY_RENT_END_DT) \t\t\t\t\t\t\t \t\tSELECT\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t'" + branchsaupNo + "',SERIAL_NO,FACTORY_NM,FACTORY_ZIP_CD,FACTORY_ADDR,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + " \t\tFACTORY_ADDR2,FACTORY_PHONE_NO,FACTORY_FAX,REG_DT,UPDATE_DT,FACTORY_RENT_YN,FACTORY_RENT_START_DT,FACTORY_RENT_END_DT\t\t\t\t\t\t\t" + " \t\tFROM UM_FACTORY_INFO WHERE BIZ_REG_NO = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex39) {}
                    }
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    query2 = " DELETE FROM UM_BID_AGENT  WHERE BIZ_REG_NO = '" + branchsaupNo + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex40) {}
                    }
                    gjpsmt1 = con.prepareStatement(query2);
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                    dpquery1 = " INSERT INTO UM_BID_AGENT(BIZ_REG_NO,IDENT_NO,NM,DEPART,POSITION,PHONE_NO,\t\t\t\t\t\t\t\t\t\t \t\t\t   \t\t                EMAIL,MOBILE,FAX,REG_DT,UPDATE_DT,BIDDING_AGENT_YN)\t\t\t\t\t\t\t\t\t\t\t\t\t \t\tSELECT\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t'" + branchsaupNo + "',IDENT_NO,NM,DEPART,POSITION,PHONE_NO,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + " \t\tEMAIL,MOBILE,FAX,REG_DT,UPDATE_DT,BIDDING_AGENT_YN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + " \t\tFROM UM_BID_AGENT WHERE BIZ_REG_NO =?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex41) {}
                    }
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                }
                query1 = "\t SELECT BIZ_REG_NO FROM UM_REC_SUPPLIER_ENTER_MAST        WHERE CORP_REG_NO IN (SELECT CORP_REG_NO\t\t\t\t\t   \t\t\t\t\t\t\t\t\tFROM UM_REC_SUPPLIER_ENTER_MAST\t\t   \t\t\t\t\t\t\t\t\tWHERE BIZ_REG_NO  =?)\t\t       AND HEADQUARTER_YN = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ";
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex42) {}
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
                        catch (Exception ex43) {}
                    }
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    query2 = " DELETE FROM UM_REC_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = '" + branchsaupNo + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex44) {}
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
                    catch (Exception ex45) {}
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
                    catch (Exception ex46) {}
                }
                if (!mainsaupNoCheck.equals("0")) {
                    dpquery1 = " DELETE FROM UM_REC_REPR  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (dppsmt1 != null) {
                        try {
                            dppsmt1.close();
                        }
                        catch (Exception ex47) {}
                    }
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    gjquery1 = " DELETE FROM UM_REC_FACTORY_INFO  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex48) {}
                    }
                    gjpsmt1 = con.prepareStatement(gjquery1);
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                    jpquery1 = " DELETE FROM 사용_접수조달품목  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (jppsmt1 != null) {
                        try {
                            jppsmt1.close();
                        }
                        catch (Exception ex49) {}
                    }
                    jppsmt1 = con.prepareStatement(jpquery1);
                    jppsmt1.executeUpdate();
                    jppsmt1.clearParameters();
                    msquery1 = " DELETE FROM UM_REC_LICENSE_FACTS  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (mspsmt1 != null) {
                        try {
                            mspsmt1.close();
                        }
                        catch (Exception ex50) {}
                    }
                    mspsmt1 = con.prepareStatement(msquery1);
                    mspsmt1.executeUpdate();
                    mspsmt1.clearParameters();
                    idquery1 = " DELETE FROM UM_REC_BID_AGENT  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (idpsmt1 != null) {
                        try {
                            idpsmt1.close();
                        }
                        catch (Exception ex51) {}
                    }
                    idpsmt1 = con.prepareStatement(idquery1);
                    idpsmt1.executeUpdate();
                    idpsmt1.clearParameters();
                    query1 = " DELETE FROM UM_REC_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (psmt2 != null) {
                        try {
                            psmt2.close();
                        }
                        catch (Exception ex52) {}
                    }
                    psmt2 = con.prepareStatement(query1);
                    psmt2.executeUpdate();
                    psmt2.clearParameters();
                }
                final String year = transNo.substring(1, 5);
                final String month = transNo.substring(5, 7);
                final String day = transNo.substring(7, 9);
                final String newDocFilename = "/data/EDOC/" + year + "/" + month + "/" + day + "/detac/Work/" + transNo + ".0";
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                final PrintWriter p = new PrintWriter(baos);
                dsource.print(p);
                String res = baos.toString();
                res = "<?xml version = '1.0' encoding = 'EUC-KR'?>" + res.substring(res.indexOf("?>") + 2);
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
                    catch (Exception ex53) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex54) {}
                }
                psmt2 = con.prepareStatement(query_1);
                psmt2.setString(1, isStrNull(procID));
                rs = psmt2.executeQuery();
                String jicheong2 = "";
                if (rs.next()) {
                    jicheong2 = rs.getString(1);
                }
                psmt2.clearParameters();
                jmquery1 = " UPDATE UM_EDOC_STATE SET  PROCESS_DT = ?, PROCESS_RSON = ?, BIZ_NM = ?, REPR_NM = ?, ZIP_CD = ?,  ADDR = ?, DETAIL_ADDR = ?, PHONE_NO = ?, WEBSITE = ?, PERMIT_BRANCH = ?,  PROCESS_DT = SYSDATE  WHERE TRANS_NO = '" + transNo + "' " + " AND MOD_CLS = '2' ";
                if (jmpsmt1 != null) {
                    try {
                        jmpsmt1.close();
                    }
                    catch (Exception ex55) {}
                }
                jmpsmt1 = con.prepareStatement(jmquery1);
                jmpsmt1.setString(1, isStrNull(status));
                jmpsmt1.setString(2, isStrNull(this.um_adv_conra020c.manage_reason[0]));
                jmpsmt1.setString(3, isStrNull(this.um_adv_conra020c.biz_nm[0]));
                jmpsmt1.setString(4, isStrNull(this.um_adv_conra020c.repr_nm[0]));
                jmpsmt1.setString(5, isStrNull(this.um_adv_conra020c.zip_cd[0]));
                jmpsmt1.setString(6, isStrNull(this.um_adv_conra020c.addr[0]));
                jmpsmt1.setString(7, isStrNull(this.um_adv_conra020c.addr_detail[0]));
                jmpsmt1.setString(8, isStrNull(this.um_adv_conra020c.phone_no[0]));
                jmpsmt1.setString(9, isStrNull(this.um_adv_conra020c.homepage[0]));
                jmpsmt1.setString(10, isStrNull(jicheong2));
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
                this.um_adv_conra020c.permit_branch[0] = jicheong2;
                this.replacePutValues(dsource, this.um_adv_conra020c.permit_branch);
                final String year2 = transNo.substring(1, 5);
                final String month2 = transNo.substring(5, 7);
                final String day2 = transNo.substring(7, 9);
                final String newDocFilename2 = "/data/EDOC/" + year2 + "/" + month2 + "/" + day2 + "/detac/Work/" + transNo + ".0";
                final ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                final PrintWriter p2 = new PrintWriter(baos2);
                dsource.print(p2);
                String res2 = baos2.toString();
                res2 = "<?xml version = '1.0' encoding = 'EUC-KR'?>" + res2.substring(res2.indexOf("?>") + 2);
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
                    catch (Exception ex56) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex57) {}
                }
                psmt2 = con.prepareStatement(query_1);
                psmt2.setString(1, isStrNull(procID));
                rs = psmt2.executeQuery();
                String jicheong2 = "";
                if (rs.next()) {
                    jicheong2 = rs.getString(1);
                }
                psmt2.clearParameters();
                jmquery1 = " UPDATE UM_EDOC_STATE SET  PROCESS_DT = ?, PROCESS_RSON = ?, BIZ_NM = ?, REPR_NM = ?, ZIP_CD = ?,  ADDR = ?, DETAIL_ADDR = ?, PHONE_NO = ?, WEBSITE = ?, PERMIT_BRANCH = ?,  PROCESS_DT = SYSDATE  WHERE TRANS_NO = '" + transNo + "' " + " AND MOD_CLS = '2' ";
                if (jmpsmt1 != null) {
                    try {
                        jmpsmt1.close();
                    }
                    catch (Exception ex58) {}
                }
                jmpsmt1 = con.prepareStatement(jmquery1);
                jmpsmt1.setString(1, isStrNull(status));
                jmpsmt1.setString(2, isStrNull(this.um_adv_conra020c.manage_reason[0]));
                jmpsmt1.setString(3, isStrNull(this.um_adv_conra020c.biz_nm[0]));
                jmpsmt1.setString(4, isStrNull(this.um_adv_conra020c.repr_nm[0]));
                jmpsmt1.setString(5, isStrNull(this.um_adv_conra020c.zip_cd[0]));
                jmpsmt1.setString(6, isStrNull(this.um_adv_conra020c.addr[0]));
                jmpsmt1.setString(7, isStrNull(this.um_adv_conra020c.addr_detail[0]));
                jmpsmt1.setString(8, isStrNull(this.um_adv_conra020c.phone_no[0]));
                jmpsmt1.setString(9, isStrNull(this.um_adv_conra020c.homepage[0]));
                jmpsmt1.setString(10, isStrNull(jicheong2));
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
                this.um_adv_conra020c.permit_branch[0] = jicheong2;
                this.replacePutValues(dsource, this.um_adv_conra020c.permit_branch);
                query1 = "SELECT COUNT(*) FROM UM_REC_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO=? ";
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex59) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex60) {}
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
                        catch (Exception ex61) {}
                    }
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    gjquery1 = " DELETE FROM UM_REC_FACTORY_INFO  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (gjpsmt1 != null) {
                        try {
                            gjpsmt1.close();
                        }
                        catch (Exception ex62) {}
                    }
                    gjpsmt1 = con.prepareStatement(gjquery1);
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                    jpquery1 = " DELETE FROM UM_REC_SUPPLIER_ENTER_ITEM  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (jppsmt1 != null) {
                        try {
                            jppsmt1.close();
                        }
                        catch (Exception ex63) {}
                    }
                    jppsmt1 = con.prepareStatement(jpquery1);
                    jppsmt1.executeUpdate();
                    jppsmt1.clearParameters();
                    msquery1 = " DELETE FROM UM_REC_LICENSE_FACTS  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (mspsmt1 != null) {
                        try {
                            mspsmt1.close();
                        }
                        catch (Exception ex64) {}
                    }
                    mspsmt1 = con.prepareStatement(msquery1);
                    mspsmt1.executeUpdate();
                    mspsmt1.clearParameters();
                    idquery1 = " DELETE FROM UM_REC_BID_AGENT  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (idpsmt1 != null) {
                        try {
                            idpsmt1.close();
                        }
                        catch (Exception ex65) {}
                    }
                    idpsmt1 = con.prepareStatement(idquery1);
                    idpsmt1.executeUpdate();
                    idpsmt1.clearParameters();
                    query1 = " DELETE FROM UM_REC_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
                    if (psmt2 != null) {
                        try {
                            psmt2.close();
                        }
                        catch (Exception ex66) {}
                    }
                    psmt2 = con.prepareStatement(query1);
                    psmt2.executeUpdate();
                    psmt2.clearParameters();
                }
                final String year3 = transNo.substring(1, 5);
                final String month3 = transNo.substring(5, 7);
                final String day3 = transNo.substring(7, 9);
                final String newDocFilename3 = "/data/EDOC/" + year3 + "/" + month3 + "/" + day3 + "/detac/Work/" + transNo + ".0";
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
                catch (Exception ex67) {}
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex68) {}
            }
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex69) {}
            }
            if (dppsmt1 != null) {
                try {
                    dppsmt1.close();
                }
                catch (Exception ex70) {}
            }
            if (mspsmt1 != null) {
                try {
                    mspsmt1.close();
                }
                catch (Exception ex71) {}
            }
            if (gjpsmt1 != null) {
                try {
                    gjpsmt1.close();
                }
                catch (Exception ex72) {}
            }
            if (idpsmt1 != null) {
                try {
                    idpsmt1.close();
                }
                catch (Exception ex73) {}
            }
            if (jppsmt1 != null) {
                try {
                    jppsmt1.close();
                }
                catch (Exception ex74) {}
            }
            if (jmpsmt1 != null) {
                try {
                    jmpsmt1.close();
                }
                catch (Exception ex75) {}
            }
            if (mipsmt1 != null) {
                try {
                    mipsmt1.close();
                }
                catch (Exception ex76) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex77) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex78) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex79) {}
        }
        if (psmt3 != null) {
            try {
                psmt3.close();
            }
            catch (Exception ex80) {}
        }
        if (dppsmt1 != null) {
            try {
                dppsmt1.close();
            }
            catch (Exception ex81) {}
        }
        if (mspsmt1 != null) {
            try {
                mspsmt1.close();
            }
            catch (Exception ex82) {}
        }
        if (gjpsmt1 != null) {
            try {
                gjpsmt1.close();
            }
            catch (Exception ex83) {}
        }
        if (idpsmt1 != null) {
            try {
                idpsmt1.close();
            }
            catch (Exception ex84) {}
        }
        if (jppsmt1 != null) {
            try {
                jppsmt1.close();
            }
            catch (Exception ex85) {}
        }
        if (jmpsmt1 != null) {
            try {
                jmpsmt1.close();
            }
            catch (Exception ex86) {}
        }
        if (mipsmt1 != null) {
            try {
                mipsmt1.close();
            }
            catch (Exception ex87) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex88) {}
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
            out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(workDocFilename, false), "EUC_KR"));
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
        String query1 = null;
        PreparedStatement psmt1 = null;
        ResultSet rs = null;
        String returnValue = "";
        final int Cnt = 0;
        try {
            query1 = "\t\tSELECT   a.시공능력평가금액 * 1000\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFROM ETSE.실적_시공능력평가금액 a, UM_REC_LICENSE_FACTS b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.INDUSTRY_CD = b.LICENSE_CD\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.협회코드 IN ('01','02','03','04','05','06','07')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.시공능력평가금액 IS NOT NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.기준년도  > b.EVAL_STD_YEAR\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.적용일자 = (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t   \t\t\t\t\t SELECT  MAX(적용일자)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t   \t\t\t\t\t FROM ETSE.실적_시공능력평가금액 c\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t WHERE a.BIZ_REG_NO= c.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t AND   a.INDUSTRY_CD = c.INDUSTRY_CD\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t GROUP BY c.BIZ_REG_NO, c.INDUSTRY_CD\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t   )   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND b.BIZ_REG_NO = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND b.LICENSE_CD = ?\t";
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
            icquery = " INSERT INTO UM_BID_QUALIFY_FACTS_HIST (  BIZ_REG_NO, UPDATE_DT, SERIAL_NO, TABLE_CLS, CHANGE_CLS,  BEFORE_MOD_CONTENT, AFTER_MOD_CONTENT, PROPERTY_NM, MANAGER_ID  ) VALUES (  ?, SYSDATE, ?, ?, ?,  ?, ?, ?, ?  ) ";
            icpsmt = con.prepareStatement(icquery);
            icpsmt.setString(1, saupNo);
            icpsmt.setInt(2, ilno);
            icpsmt.setString(3, tabGubun);
            icpsmt.setString(4, gubun);
            icpsmt.setString(5, beComment);
            icpsmt.setString(6, afComment);
            icpsmt.setString(7, item);
            icpsmt.setString(8, procID);
            icpsmt.executeUpdate();
            icpsmt.clearParameters();
        }
        catch (SQLException exc) {
            try {
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
                query = " SELECT  BIZ_NM,  BIZ_CLS,  BIZ_CLS1,  ADDR,  REG_DT, 'YYYY-MM-DD'),  DETAIL_ADDR,  CAPITAL,  AREA_CD " + this.getQueryCondition();
                break;
            }
            case 800: {
                query = " SELECT  mail " + this.getQueryCondition();
                break;
            }
            case 200: {
                query = " SELECT  REPR_IDENT_NO,  REPR_NM " + this.getQueryCondition();
                break;
            }
            case 300: {
                query = " SELECT  LICENSE_CD,  CONST_ABIL_EVAL_AMT,  CD_NM ,   TO_CHAR(LICENSE_EXPIRY_DT, 'YYYYMMDD'),  EVAL_STD_YEAR" + this.getQueryCondition();
                break;
            }
            case 400: {
                query = " SELECT  GOOD_CLS_NO,  DIRECT_PRODUCTION_YN " + this.getQueryCondition();
                break;
            }
            case 500: {
                query = " SELECT  GOOD_CLS_NO,  DIRECT_PRODUCTION_YN,  DIRECT_PRODUCTION_DOC,  TO_CHAR(AVAIL_PERIOD_START_DT, 'YYYYMMDD'),  TO_CHAR(AVAIL_PERIOD_END_DT, 'YYYYMMDD'),  INDUSTRY_CLS_CD, ISSUE_INSTITU, DOC_NM " + this.getQueryCondition();
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
                queryCondition = " FROM UM_LICENSE_FACTS A, SYN_PUB_CODE B  WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' " + " AND LICENSE_CD = CD " + " AND CD_CLS = 'GU9' ";
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
