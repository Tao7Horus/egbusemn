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
import oracle.xml.parser.v2.XMLElement;
import java.util.Vector;
import java.io.Reader;
import java.io.StringReader;
import common.Trx;
import oracle.xml.parser.v2.DOMParser;

public class UM_ADV_ConrA010c
{
    UM_ADV_ConrA020c um_adv_conra020c;
    private String[] 일련번호1;
    private String[] 대표자주민번호;
    private String[] 대표자명;
    private String[] 대표자메일주소;
    private String[] 대표대표자여부;
    private String[] 대표자휴대폰;
    private String[] 대표자유형;
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
    String transNo;
    public final int MODE_JM = 800;
    
    public UM_ADV_ConrA010c() {
        this.um_adv_conra020c = new UM_ADV_ConrA020c();
        this.일련번호1 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.대표자주민번호 = new String[] { "cc:Organization.CEO.Identifier", "Identifier.Content" };
        this.대표자명 = new String[] { "Organization.CEO.Name", "Text.Content" };
        this.대표자메일주소 = new String[] { "Email.Address.Text", "Text.Content" };
        this.대표대표자여부 = new String[] { "Representation.Ceo.Indicator", "Indicator.Content" };
        this.대표자휴대폰 = new String[] { "cc:PCS.Number.Text", "Text.Content" };
        this.대표자유형 = new String[] { "cc:CEO.Type.Code", "Code.Content" };
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
        Trx trx = null;
        Connection con = null;
        final PreparedStatement psmt = null;
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
        PreparedStatement bupsmt1 = null;
        PreparedStatement bupsmt2 = null;
        ResultSet rs = null;
        final String SQL1 = null;
        final StringBuffer sbSQL1 = new StringBuffer();
        final String SQL2 = null;
        final StringBuffer sbSQL2 = new StringBuffer();
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
        final String bsdate = "";
        final String bsdate2 = "";
        final String bsdate3 = "";
        final String bedate = "";
        final String bedate2 = "";
        final String bedate3 = "";
        final String bcount = "";
        final String bcount2 = "";
        final String bcount3 = "";
        final String bsaup = "";
        String status_cancel = "";
        final String ymd = transNo.substring(1, 11);
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
            GetValues(dsource, this.um_adv_conra020c.permit_branch);
            GetValues(dsource, this.um_adv_conra020c.contr_rela_yn);
            if (status.equals("1")) {
                query1 = "SELECT COUNT(*) FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO=? ";
                psmt2 = con.prepareStatement(query1);
                psmt2.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                rs = psmt2.executeQuery();
                String saupNoCheck = "0";
                if (rs.next()) {
                    saupNoCheck = rs.getString(1);
                }
                psmt2.clearParameters();
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex) {}
                }
                if (!saupNoCheck.equals("0")) {
                    result = 77;
                    return result;
                }
                status_cancel = this.getJMCancelCheck(transNo, con);
                if (status_cancel.equals("4")) {
                    result = 66;
                    return result;
                }
                final Vector msv = new Vector(1, 1);
                final Vector jpv = new Vector(1, 1);
                final Vector jpv2 = new Vector(1, 1);
                UM_ADV_ConrA030c[] msArr = (UM_ADV_ConrA030c[])null;
                UM_ADV_ConrA030c[] jpArr = (UM_ADV_ConrA030c[])null;
                UM_ADV_ConrA030c[] jpArr2 = (UM_ADV_ConrA030c[])null;
                XMLElement xemt = null;
                xemt = (XMLElement)dsource.getDocumentElement();
                int jpi = -1;
                String upmu1 = "";
                final NodeList jpNodeList = dsource.selectNodes("/gb:Gsacid/SupList/SupItem", (NSResolver)xemt);
                final int jpCount = jpNodeList.getLength();
                for (int i = 0; i < jpCount - 1; ++i) {
                    final UM_ADV_ConrA030c jp = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no_2, i);
                        GetValues(dsource, this.um_adv_conra020c.goods_cls_nm, i);
                        GetValues(dsource, this.um_adv_conra020c.goods_cls_no, i);
                        GetValues(dsource, this.um_adv_conra020c.income_3year, i);
                        GetValues(dsource, this.um_adv_conra020c.direct_product_yn, i);
                        GetValues(dsource, this.um_adv_conra020c.mast_goods_yn, i);
                        jp.일련번호2 = this.um_adv_conra020c.serial_no_2[0];
                        jp.물품명 = this.um_adv_conra020c.goods_cls_nm[0];
                        jp.물품분류번호 = this.um_adv_conra020c.goods_cls_no[0];
                        jp.최근3년간_매출액 = this.um_adv_conra020c.income_3year[0];
                        jp.제조여부 = this.um_adv_conra020c.direct_product_yn[0];
                        jp.대표물품여부 = this.um_adv_conra020c.mast_goods_yn[0];
                        if ("Y".equals(jp.대표물품여부)) {
                            jpi = i;
                        }
                        jpv.addElement(jp);
                    }
                    catch (NullPointerException ane) {
                        jpv.addElement(jp);
                        break;
                    }
                }
                jpArr = new UM_ADV_ConrA030c[jpv.size()];
                jpv.copyInto(jpArr);
                int jpi2 = -1;
                final NodeList jp1NodeList = dsource.selectNodes("/gb:Gsacid/ManList/ManItem", (NSResolver)xemt);
                final int jp1Count = jp1NodeList.getLength();
                for (int j = 0; j < jp1Count - 1; ++j) {
                    final UM_ADV_ConrA030c jp2 = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no5, j);
                        GetValues(dsource, this.um_adv_conra020c.goods_cls_nm1, j);
                        GetValues(dsource, this.um_adv_conra020c.goods_cls_no1, j);
                        GetValues(dsource, this.um_adv_conra020c.permit_no1, j);
                        GetValues(dsource, this.um_adv_conra020c.permit_institu1, j);
                        GetValues(dsource, this.um_adv_conra020c.permit_dt1, j);
                        GetValues(dsource, this.um_adv_conra020c.imcome_3year1, j);
                        GetValues(dsource, this.um_adv_conra020c.direct_product_yn1, j);
                        GetValues(dsource, this.um_adv_conra020c.mast_goods_yn1, j);
                        GetValues(dsource, this.um_adv_conra020c.direct_product_doc, j);
                        GetValues(dsource, this.um_adv_conra020c.avail_period_start_dt, j);
                        GetValues(dsource, this.um_adv_conra020c.avail_period_end_dt, j);
                        GetValues(dsource, this.um_adv_conra020c.industry_cls_cd, j);
                        GetValues(dsource, this.um_adv_conra020c.issua_institu, j);
                        GetValues(dsource, this.um_adv_conra020c.doc_nm1, j);
                        jp2.일련번호5 = this.um_adv_conra020c.serial_no5[0];
                        jp2.물품명1 = this.um_adv_conra020c.goods_cls_nm1[0];
                        jp2.물품분류번호1 = this.um_adv_conra020c.goods_cls_no1[0];
                        jp2.형식승인번호1 = this.um_adv_conra020c.permit_no1[0];
                        jp2.형식승인기관1 = this.um_adv_conra020c.permit_institu1[0];
                        jp2.형식승인일1 = this.um_adv_conra020c.permit_dt1[0];
                        jp2.최근3년간_매출액1 = this.um_adv_conra020c.imcome_3year1[0];
                        jp2.제조여부1 = this.um_adv_conra020c.direct_product_yn1[0];
                        jp2.대표물품여부1 = this.um_adv_conra020c.mast_goods_yn1[0];
                        jp2.직접생산증명서류 = this.um_adv_conra020c.direct_product_doc[0];
                        jp2.유효기간시작일자 = this.um_adv_conra020c.avail_period_start_dt[0];
                        jp2.유효기간종료일자 = this.um_adv_conra020c.avail_period_end_dt[0];
                        jp2.표준산업분류코드 = this.um_adv_conra020c.industry_cls_cd[0];
                        jp2.발급기관 = this.um_adv_conra020c.issua_institu[0];
                        jp2.증서명 = this.um_adv_conra020c.doc_nm1[0];
                        if ("Y".equals(jp2.대표물품여부1)) {
                            jpi2 = j;
                        }
                        jpv2.addElement(jp2);
                    }
                    catch (NullPointerException ane2) {
                        jpv2.addElement(jp2);
                        break;
                    }
                }
                jpArr2 = new UM_ADV_ConrA030c[jpv2.size()];
                jpv2.copyInto(jpArr2);
                int msi = -1;
                int gm = 0;
                int ym = 0;
                int ny = 0;
                int gmc = -1;
                int ymc = -1;
                String licenseCode = "";
                String Upjongcode = "";
                String Sipyongvalue = "";
                String Sipyongyear = "";
                String Sipyongyear2 = "";
                final NodeList msNodeList = dsource.selectNodes("/gb:Gsacid/LicenList/LicenItem", (NSResolver)xemt);
                final int msCount = msNodeList.getLength();
                for (int k = 0; k < msCount - 1; ++k) {
                    final UM_ADV_ConrA030c ms = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no3, k);
                        GetValues(dsource, this.um_adv_conra020c.license_cd, k);
                        GetValues(dsource, this.um_adv_conra020c.license_nm, k);
                        GetValues(dsource, this.um_adv_conra020c.license_no, k);
                        GetValues(dsource, this.um_adv_conra020c.license_issued_dt, k);
                        GetValues(dsource, this.um_adv_conra020c.license_expiry_dt, k);
                        GetValues(dsource, this.um_adv_conra020c.const_abil_eval_amt, k);
                        GetValues(dsource, this.um_adv_conra020c.eval_std_year, k);
                        GetValues(dsource, this.um_adv_conra020c.mast_license_yn, k);
                        ms.serial_3 = this.um_adv_conra020c.serial_no3[0];
                        ms.licencse_cd = this.um_adv_conra020c.license_cd[0];
                        ms.license_nm = this.um_adv_conra020c.license_nm[0];
                        ms.license_no = this.um_adv_conra020c.license_no[0];
                        ms.issued_dt = this.um_adv_conra020c.license_issued_dt[0];
                        ms.expiry_dt = this.um_adv_conra020c.license_expiry_dt[0];
                        ms.const_abil_eval_amt = this.um_adv_conra020c.const_abil_eval_amt[0];
                        ms.eval_std_year = this.um_adv_conra020c.eval_std_year[0];
                        ms.mast_yn = this.um_adv_conra020c.mast_license_yn[0];
                        query1 = "  SELECT CD_NM2 FROM SYN_PUB_CODE\t\t\t  WHERE CD_CLS = 'GU9' AND CD = ?\t\t";
                        psmt2 = con.prepareStatement(query1);
                        psmt2.setString(1, ms.licencse_cd);
                        rs = psmt2.executeQuery();
                        psmt2.clearParameters();
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
                        final String mc = ms.licencse_cd.substring(0, 1);
                        if ("0".equals(mc)) {
                            gmc = k;
                        }
                        if ("1".equals(mc)) {
                            ymc = k;
                        }
                        if ("Y".equals(ms.mast_yn)) {
                            msi = k;
                        }
                        msv.addElement(ms);
                    }
                    catch (NullPointerException ane3) {
                        msv.addElement(ms);
                        break;
                    }
                }
                msArr = new UM_ADV_ConrA030c[msv.size()];
                msv.copyInto(msArr);
                if (!this.um_adv_conra020c.goods_cls_no[0].equals("")) {
                    upmu1 = "1";
                }
                else if (!this.um_adv_conra020c.goods_cls_no1[0].equals("")) {
                    upmu1 = "1";
                }
                else {
                    upmu1 = "0";
                }
                final String upmu2 = this.um_adv_conra020c.biz_cls[0];
                final String upmu3 = String.valueOf(upmu1) + gm + ym + ny + upmu2;
                final Vector gjv = new Vector(1, 1);
                UM_ADV_ConrA030c[] gjArr = (UM_ADV_ConrA030c[])null;
                final NodeList gjNodeList = dsource.selectNodes("/gb:Gsacid/FacList/FacItem", (NSResolver)xemt);
                final int gjCount = gjNodeList.getLength();
                String jejoGubun = "";
                for (int l = 0; l < gjCount - 1; ++l) {
                    final UM_ADV_ConrA030c gj = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no, l);
                        GetValues(dsource, this.um_adv_conra020c.fac_nm, l);
                        GetValues(dsource, this.um_adv_conra020c.fac_zip_cd, l);
                        GetValues(dsource, this.um_adv_conra020c.fac_add, l);
                        GetValues(dsource, this.um_adv_conra020c.fac_add2, l);
                        GetValues(dsource, this.um_adv_conra020c.fac_phone, l);
                        GetValues(dsource, this.um_adv_conra020c.fac_fax, l);
                        GetValues(dsource, this.um_adv_conra020c.fac_rent_yn, l);
                        GetValues(dsource, this.um_adv_conra020c.fac_rent_start_dt, l);
                        GetValues(dsource, this.um_adv_conra020c.fac_rent_end_dt, l);
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
                    catch (NullPointerException ane4) {
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
                query1 = " SELECT PERMIT_BRANCH FROM UM_USER WHERE USER_ID = ? ";
                psmt2 = con.prepareStatement(query1);
                psmt2.setString(1, isStrNull(procID));
                rs = psmt2.executeQuery();
                String jicheong = "";
                if (rs.next()) {
                    jicheong = rs.getString(1);
                }
                psmt2.clearParameters();
                query2 = " INSERT INTO UM_SUPPLIER_ENTER_MAST (  BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM,  COMMENCEMENT_DT, ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS,  CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL,  EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR,  DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECAIL_GOODS_YN,  REG_DT, UPDATE_DT, REPR_BIZ_APPROVE_YN, MANAGER_ID,  PERMIT_BRANCH, INITIAL_INSTITU_NM,CONTR_RELA_YN  ) VALUES (  ?, ?, ?, ?,  ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  SYSDATE, SYSDATE, 'Y', ?,  ?, ?, ?  ) ";
                psmt3 = con.prepareStatement(query2);
                psmt3.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                psmt3.setString(2, isStrNull(this.um_adv_conra020c.nationality[0]));
                psmt3.setString(3, isStrNull(this.um_adv_conra020c.biz_nm[0]));
                psmt3.setString(4, isStrNull(this.um_adv_conra020c.biz_en_nm[0]));
                psmt3.setString(5, this.um_adv_conra020c.commentcement_dt[0]);
                psmt3.setString(6, this.um_adv_conra020c.establish_dt[0]);
                psmt3.setString(7, isStrNull(upmu3));
                psmt3.setString(8, isStrNull(jejoGubun));
                psmt3.setString(9, isStrNull(this.um_adv_conra020c.corp_reg_no[0]));
                psmt3.setString(10, isStrNull("2"));
                psmt3.setString(11, isStrNull(this.um_adv_conra020c.biz_cls_2[0]));
                psmt3.setString(12, isStrNull("0000"));
                final int jabon = 0;
                if (" ".equals(this.um_adv_conra020c.capital[0]) || "".equals(this.um_adv_conra020c.capital[0]) || this.um_adv_conra020c.capital[0] == null) {
                    psmt3.setInt(13, 0);
                }
                else {
                    psmt3.setString(13, this.um_adv_conra020c.capital[0]);
                }
                psmt3.setString(14, this.um_adv_conra020c.employee_count_[0]);
                psmt3.setString(15, isStrNull("0000-00"));
                psmt3.setString(16, isStrNull(this.um_adv_conra020c.zip_cd[0]));
                psmt3.setString(17, isStrNull(this.um_adv_conra020c.area_cd[0]));
                psmt3.setString(18, isStrNull(this.um_adv_conra020c.addr[0]));
                psmt3.setString(19, isStrNull(this.um_adv_conra020c.addr_detail[0]));
                psmt3.setString(20, isStrNull(this.um_adv_conra020c.phone_no[0]));
                psmt3.setString(21, isStrNull(this.um_adv_conra020c.fax[0]));
                psmt3.setString(22, isStrNull(this.um_adv_conra020c.homepage[0]));
                psmt3.setString(23, isStrNull(this.um_adv_conra020c.special_good_yn[0]));
                psmt3.setString(24, isStrNull(procID));
                psmt3.setString(25, isStrNull(jicheong));
                psmt3.setString(26, isStrNull("조달청"));
                psmt3.setString(27, isStrNull(this.um_adv_conra020c.contr_rela_yn[0]));
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
                this.um_adv_conra020c.biz_cls_1[0] = isStrNull("2");
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_cls_1);
                this.um_adv_conra020c.biz_cls_2[0] = isStrNull(this.um_adv_conra020c.biz_cls_2[0]);
                this.replacePutValues(dsource, this.um_adv_conra020c.biz_cls_2);
                this.um_adv_conra020c.biz_cls_year[0] = isStrNull("0000");
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
                this.um_adv_conra020c.lat_settle_dt[0] = isStrNull("0000");
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
                for (int m = 0; m < dpCount - 1; ++m) {
                    final UM_ADV_ConrA030c dp = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no_1, m);
                        GetValues(dsource, this.um_adv_conra020c.rerp_ident_no, m);
                        GetValues(dsource, this.um_adv_conra020c.repr_nm, m);
                        GetValues(dsource, this.um_adv_conra020c.repr_mail, m);
                        GetValues(dsource, this.um_adv_conra020c.mast_repr_yn, m);
                        GetValues(dsource, this.um_adv_conra020c.repr_mobile, m);
                        GetValues(dsource, this.um_adv_conra020c.repr_cls, m);
                        dp.serial_no1 = this.um_adv_conra020c.serial_no_1[0];
                        dp.repr_ident_no = this.um_adv_conra020c.rerp_ident_no[0];
                        dp.repr_nm = this.um_adv_conra020c.repr_nm[0];
                        dp.repr_email = this.um_adv_conra020c.repr_mail[0];
                        dp.mast_repr_yn = this.um_adv_conra020c.mast_repr_yn[0];
                        dp.repr_mobile = this.um_adv_conra020c.repr_mobile[0];
                        dp.repr_cls = this.um_adv_conra020c.repr_cls[0];
                        if ("Y".equals(dp.mast_repr_yn)) {
                            dpi = m;
                        }
                        dpv.addElement(dp);
                    }
                    catch (NullPointerException ane5) {
                        dpv.addElement(dp);
                        break;
                    }
                }
                dpArr = new UM_ADV_ConrA030c[dpv.size()];
                dpv.copyInto(dpArr);
                final Vector idv = new Vector(1, 1);
                UM_ADV_ConrA030c[] idArr = (UM_ADV_ConrA030c[])null;
                sbSQL1.append("    INSERT INTO UM_ENTER_STATE (BIZ_REG_NO, RAISED_DT, START_DT, END_DT, STATE_CLS, REMARK, MANAGER_ID, PROCESS_DT)\t\t\t\t\t\t\t\t\t");
                sbSQL1.append("    (SELECT /*+ ordered use_nl(a b ) */\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL1.append("         a.BIZ_REG_NO, sysdate, MIN(a.PUNISH_DT), MAX(a.EXPIRE_DT), '05', a.BIZ_REG_NO||'/'||MAX(a.PUNISH_COUNT), ?, sysdate\t\t\t");
                sbSQL1.append("    FROM UM_VIOLATE_COM a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL1.append("    WHERE not exists (SELECT 'N' FROM UM_ENTER_STATE b WHERE a.BIZ_REG_NO = b.BIZ_REG_NO AND b.STATE_CLS = '05')\t\t\t\t\t");
                sbSQL1.append("    AND a.BIZ_REG_NO = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL1.append("    AND (a.SUSPEND_CLS = 'N' OR (a.SUSPEND_CLS = 'Y' AND a.RESUMPTION_CLS = 'Y'))\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL1.append("    AND a.RELEASE_CLS = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL1.append("    AND TO_CHAR(a.PUNISH_DT, 'yyyy-mm-dd') <= TO_CHAR(sysdate, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL1.append("    AND TO_CHAR(a.EXPIRE_DT, 'yyyy-mm-dd') >= TO_CHAR(sysdate, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL1.append("    AND NVL(TO_CHAR(a.RESUMPTION_DT, 'yyyy-mm-dd'), '1800-01-01') <= TO_CHAR(sysdate, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL1.append("    GROUP BY a.BIZ_REG_NO)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                bupsmt1 = con.prepareStatement(sbSQL1.toString());
                bupsmt1.setString(1, isStrNull(procID));
                bupsmt1.setString(2, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                bupsmt1.executeUpdate();
                bupsmt1.clearParameters();
                sbSQL2.append("   INSERT INTO 사용_업체상태 (사업자등록번호, 발생일자, 시작일자, 종료일자, 상태구분, 비고, 처리자ID, 처리일자)\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("   (SELECT /*+ ordered use_nl(aa bb cc ) */\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("         cc.사업자등록번호, sysdate, MIN(bb.제재연월일), MAX(bb.만료연월일), '05', MAX(비고), ?, sysdate\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("   FROM 사용_부정당대표자 aa,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("       (SELECT /*+ ordered use_nl(a b ) */\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("               a.사업자등록번호, MIN(a.입력일자) 입력일자, MIN(a.제재연월일) 제재연월일, MAX(a.만료연월일) 만료연월일,\t\t\t\t\t\t\t");
                sbSQL2.append(" \t\t\t\ta.사업자등록번호||'/'||MAX(a.제재횟수) 비고, MAX(a.제재횟수) 제재횟수\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("        FROM 사용_부정당업자 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("        WHERE a.사업자등록번호 = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("          AND (a.정지구분 = 'N' OR (a.정지구분 = 'Y' AND a.재개구분 = 'Y'))\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("          AND a.해제구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("          AND TO_CHAR(a.제재연월일, 'yyyy-mm-dd') <= TO_CHAR(sysdate, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("          AND TO_CHAR(a.만료연월일, 'yyyy-mm-dd') >= TO_CHAR(sysdate, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("          AND NVL(TO_CHAR(a.재개일자, 'yyyy-mm-dd'), '1800-01-01') <= TO_CHAR(sysdate, 'yyyy-mm-dd')\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("        GROUP BY a.사업자등록번호) bb,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("       사용_대표자 cc\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("   WHERE aa.사업자등록번호 = bb.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("   AND aa.주민등록번호 = cc.대표자주민번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("   AND aa.제재횟수 = bb.제재횟수\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                sbSQL2.append("   AND not exists (SELECT 'N' FROM 사용_업체상태 dd WHERE cc.사업자등록번호 = dd.사업자등록번호 AND dd.상태구분 = '05')\t\t\t\t");
                sbSQL2.append("   GROUP BY cc.사업자등록번호)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                bupsmt2 = con.prepareStatement(sbSQL2.toString());
                bupsmt2.setString(1, isStrNull(procID));
                bupsmt2.setString(2, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                bupsmt2.executeUpdate();
                bupsmt2.clearParameters();
                for (int i2 = 0; i2 < dpCount - 1; ++i2) {
                    dpquery1 = " INSERT INTO UM_REPR (  BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REG_DT, UPDATE_DT, REPR_MOBILE, REPR_CLS  ) VALUES (  ?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?, ?  ) ";
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    dppsmt1.setString(2, isStrNull(dpArr[i2].repr_ident_no));
                    dppsmt1.setString(3, isStrNull(dpArr[i2].repr_nm));
                    dppsmt1.setString(4, isStrNull(dpArr[i2].repr_email));
                    dppsmt1.setString(5, isStrNull(dpArr[i2].mast_repr_yn));
                    dppsmt1.setString(6, isStrNull(dpArr[i2].repr_mobile));
                    dppsmt1.setString(7, isStrNull(dpArr[i2].repr_cls));
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
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
                for (int i2 = 0; i2 < gjCount - 1; ++i2) {
                    gjquery1 = " INSERT INTO UM_FACTORY_INFO (  BIZ_REG_NO, SERIAL_NO, FACTORY_NM, FACTORY_ZIP_CD, ADDR,  ADDR2, FACTORY_PHONE, FACTORY_FAX, REG_DT, UPDATE_DT,  FACTORY_RENT_YN, FACTORY_RENT_START_DT, FACTORY_RENT_END_DT  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?, SYSDATE, SYSDATE,  ?, ?, ?  ) ";
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
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
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
                }
                for (int i2 = 0; i2 < jpCount - 1; ++i2) {
                    jpquery1 = " INSERT INTO UM_SUPPLIER_ENTER_ITEMS (  BIZ_REG_NO, GOOD_CLS_NO, INCOME_3YEARS,  DIRECT_PRODUCTION_YN, MAST_GOODS_YN, REG_DT, UPDATE_DT  ) VALUES (  ?, ?, ?, 'N', ?, SYSDATE, SYSDATE  ) ";
                    jppsmt1 = con.prepareStatement(jpquery1);
                    jppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    jppsmt1.setString(2, isStrNull(jpArr[i2].물품분류번호));
                    jppsmt1.setString(3, ComStr.replace(jpArr[i2].최근3년간_매출액, ",", ""));
                    jppsmt1.setString(4, isStrNull(jpArr[i2].대표물품여부));
                    jppsmt1.executeUpdate();
                    jppsmt1.clearParameters();
                    this.um_adv_conra020c.serial_no_2[0] = isStrNull(jpArr[i2].일련번호2);
                    this.um_adv_conra020c.add[0] = "";
                    this.um_adv_conra020c.goods_cls_no[0] = isStrNull(jpArr[i2].물품분류번호);
                    this.um_adv_conra020c.income_3year[0] = ComStr.replace(jpArr[i2].최근3년간_매출액, ",", "");
                    this.um_adv_conra020c.direct_product_yn[0] = "N";
                    this.um_adv_conra020c.direct_product_fm[0] = "";
                    this.um_adv_conra020c.mast_goods_yn[0] = isStrNull(jpArr[i2].대표물품여부);
                    this.um_adv_conra020c.mast_goods_fm[0] = "";
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.serial_no5, this.일련번호5, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.goods_cls_no, this.물품분류번호, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.income_3year, this.최근3년간_매출액, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.direct_product_yn, this.제조여부, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.direct_product_fm, this.제조포멧, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.mast_goods_yn, this.대표물품여부, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.mast_goods_fm, this.대표물품포멧, i2, 0, "U");
                }
                for (int i2 = 0; i2 < jp1Count - 1; ++i2) {
                    jpquery1 = " INSERT INTO UM_SUPPLIER_ENTER_ITEMS (  BIZ_REG_NO, GOOD_CLS_NO, PERMIT_NO, PERMIT_INSTITU, PERMIT_DT,  INCOME_3YEARS,  DIRECT_PRODUCTION_YN, MAST_GOODS_YN, REG_DT, UPDATE_DT,  DIRECT_PRODUCTION_DOC, AVAIL_PERIOD_START_DT, AVAIL_PERIOD_END_DT, INDUSTRY_CLS_CD,ISSUE_INSTITU,DOC_NM  ) VALUES (  ?, ?, ?, ?, ?,  ?, 'Y', ?, SYSDATE, SYSDATE,  ?, ?, ?, ?, ?, ?  ) ";
                    jppsmt1 = con.prepareStatement(jpquery1);
                    jppsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    jppsmt1.setString(2, isStrNull(jpArr2[i2].물품분류번호1));
                    jppsmt1.setString(3, isStrNull(jpArr2[i2].형식승인번호1));
                    jppsmt1.setString(4, isStrNull(jpArr2[i2].형식승인기관1));
                    jppsmt1.setString(5, jpArr2[i2].형식승인일1);
                    jppsmt1.setString(6, ComStr.replace(jpArr2[i2].최근3년간_매출액1, ",", ""));
                    jppsmt1.setString(7, isStrNull(jpArr2[i2].대표물품여부1));
                    jppsmt1.setString(8, isStrNull(jpArr2[i2].직접생산증명서류));
                    jppsmt1.setString(9, jpArr2[i2].유효기간시작일자);
                    jppsmt1.setString(10, jpArr2[i2].유효기간종료일자);
                    jppsmt1.setString(11, isStrNull(jpArr2[i2].표준산업분류코드));
                    jppsmt1.setString(12, isStrNull(jpArr2[i2].발급기관));
                    jppsmt1.setString(13, isStrNull(jpArr2[i2].증서명));
                    jppsmt1.executeUpdate();
                    jppsmt1.clearParameters();
                    this.um_adv_conra020c.serial_no5[0] = isStrNull(jpArr2[i2].일련번호5);
                    this.um_adv_conra020c.add1[0] = "";
                    this.um_adv_conra020c.goods_cls_no1[0] = isStrNull(jpArr2[i2].물품분류번호1);
                    this.um_adv_conra020c.imcome_3year1[0] = ComStr.replace(jpArr2[i2].최근3년간_매출액1, ",", "");
                    this.um_adv_conra020c.permit_no1[0] = isStrNull(jpArr2[i2].형식승인번호1);
                    this.um_adv_conra020c.permit_institu1[0] = isStrNull(jpArr2[i2].형식승인기관1);
                    this.um_adv_conra020c.permit_dt1[0] = isStrNull(jpArr2[i2].형식승인일1);
                    this.um_adv_conra020c.direct_product_yn1[0] = "Y";
                    this.um_adv_conra020c.direct_product_fm1[0] = "";
                    this.um_adv_conra020c.mast_goods_yn1[0] = isStrNull(jpArr2[i2].대표물품여부1);
                    this.um_adv_conra020c.mast_goods_fm1[0] = "";
                    this.um_adv_conra020c.direct_product_doc[0] = isStrNull(jpArr2[i2].직접생산증명서류);
                    this.um_adv_conra020c.avail_period_start_dt[0] = isStrNull(jpArr2[i2].유효기간시작일자);
                    this.um_adv_conra020c.avail_period_end_dt[0] = isStrNull(jpArr2[i2].유효기간종료일자);
                    this.um_adv_conra020c.industry_cls_cd[0] = isStrNull(jpArr2[i2].표준산업분류코드);
                    this.um_adv_conra020c.issua_institu[0] = isStrNull(jpArr2[i2].발급기관);
                    this.um_adv_conra020c.doc_nm1[0] = isStrNull(jpArr2[i2].증서명);
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.serial_no5, this.일련번호5, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.goods_cls_no1, this.물품분류번호1, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.imcome_3year1, this.최근3년간_매출액1, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.permit_no1, this.형식승인번호1, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.permit_institu1, this.형식승인기관1, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.permit_dt1, this.형식승인일1, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.direct_product_yn1, this.제조여부1, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.direct_product_fm1, this.제조포멧1, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.mast_goods_yn1, this.대표물품여부1, i2, 0, "U");
                    this.maReplacePutValues(dsource, this.um_adv_conra020c.mast_goods_fm1, this.대표물품포멧1, i2, 0, "U");
                    this.maReplacePutValues1(dsource, this.um_adv_conra020c.direct_product_doc, this.직접생산증명서류, i2, 0, "U");
                    this.maReplacePutValues1(dsource, this.um_adv_conra020c.avail_period_start_dt, this.유효기간시작일자, i2, 0, "U");
                    this.maReplacePutValues1(dsource, this.um_adv_conra020c.avail_period_end_dt, this.유효기간종료일자, i2, 0, "U");
                    this.maReplacePutValues1(dsource, this.um_adv_conra020c.industry_cls_cd, this.표준산업분류코드, i2, 0, "U");
                    this.maReplacePutValues1(dsource, this.um_adv_conra020c.issua_institu, this.발급기관, i2, 0, "U");
                    this.maReplacePutValues1(dsource, this.um_adv_conra020c.doc_nm1, this.증서명, i2, 0, "U");
                }
                for (int i2 = 0; i2 < msCount - 1; ++i2) {
                    query1 = "\t\tSELECT a.업종코드, a.시공능력평가금액* 1000, a.기준년도,  to_char(a.입력일자,'yyyy-mm-dd hh24:mi:ss')\t\t\t\t\t\tFROM ETSE.실적_시공능력평가금액 a, UM_REC_LICENSE_FACTS b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.업종코드 = b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.협회코드 IN ('01','02','03','04','05','06','07')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.시공능력평가금액 IS NOT NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.기준년도  > b.EVAL_STD_YEAR\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND a.적용일자 = (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t   \t\t\t\t\t SELECT  MAX(적용일자)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t   \t\t\t\t\t FROM ETSE.실적_시공능력평가금액 c\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t WHERE a.BIZ_REG_NO= c.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t AND   a.업종코드 = c.업종코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t\t GROUP BY c.BIZ_REG_NO, c.업종코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \t\t\t\t\t\t\t   )   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND b.BIZ_REG_NO = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAND b.LICENSE_CD = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (Exception ex2) {}
                    }
                    if (psmt2 != null) {
                        try {
                            psmt2.close();
                        }
                        catch (Exception ex3) {}
                    }
                    psmt2 = con.prepareStatement(query1);
                    psmt2.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    psmt2.setString(2, isStrNull(msArr[i2].licencse_cd));
                    rs = psmt2.executeQuery();
                    psmt2.clearParameters();
                    if (rs.next()) {
                        Upjongcode = rs.getString(1);
                        Sipyongvalue = rs.getString(2);
                        Sipyongyear = rs.getString(3);
                        Sipyongyear2 = rs.getString(4);
                    }
                    msquery1 = " INSERT INTO UM_LICENSE_FACTS (  BIZ_REG_NO, LICENSE_CD, LICENSE_NO, LICENSE_ISSUED_DT, LICENSE_EXPIRY_DT,   CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR, ASSOC_UPDATE_DT, MAST_LICENSE_YN, REG_DT, UPDATE_DT  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, to_date(?,'yyyy-mm-dd hh24:mi:ss'), ?, SYSDATE, SYSDATE  ) ";
                    if (mspsmt1 != null) {
                        try {
                            mspsmt1.close();
                        }
                        catch (Exception ex4) {}
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
                    mspsmt1.clearParameters();
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
                final NodeList idNodeList = dsource.selectNodes("/gb:Gsacid/RepList/RepItem", (NSResolver)xemt);
                final int idCount = idNodeList.getLength();
                for (int i3 = 0; i3 < idCount - 1; ++i3) {
                    final UM_ADV_ConrA030c id = new UM_ADV_ConrA030c();
                    try {
                        GetValues(dsource, this.um_adv_conra020c.serial_no4, i3);
                        GetValues(dsource, this.um_adv_conra020c.ident_no, i3);
                        GetValues(dsource, this.um_adv_conra020c.nm, i3);
                        GetValues(dsource, this.um_adv_conra020c.depart, i3);
                        GetValues(dsource, this.um_adv_conra020c.position, i3);
                        GetValues(dsource, this.um_adv_conra020c.agent_phone, i3);
                        GetValues(dsource, this.um_adv_conra020c.E_MAIL, i3);
                        GetValues(dsource, this.um_adv_conra020c.mobile, i3);
                        GetValues(dsource, this.um_adv_conra020c.FAX, i3);
                        GetValues(dsource, this.um_adv_conra020c.agent_yn, i3);
                        id.serialNo4 = this.um_adv_conra020c.serial_no4[0];
                        id.identNo = this.um_adv_conra020c.ident_no[0];
                        id.agentNm = this.um_adv_conra020c.nm[0];
                        id.depart = this.um_adv_conra020c.depart[0];
                        id.position = this.um_adv_conra020c.position[0];
                        id.agent_phone = this.um_adv_conra020c.agent_phone[0];
                        id.E_MAIL = this.um_adv_conra020c.E_MAIL[0];
                        id.mobile = this.um_adv_conra020c.mobile[0];
                        id.FAX = this.um_adv_conra020c.FAX[0];
                        id.agent_yn = this.um_adv_conra020c.agent_yn[0];
                        idv.addElement(id);
                    }
                    catch (NullPointerException ane6) {
                        idv.addElement(id);
                        break;
                    }
                }
                idArr = new UM_ADV_ConrA030c[idv.size()];
                idv.copyInto(idArr);
                for (int i3 = 0; i3 < idCount - 1; ++i3) {
                    idquery1 = " INSERT INTO UM_BID_AGENT (  BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION,  PHONE_NO, E_MAIL, MOBILE, FAX, REG_DT, UPDATE_DT,BIDDING_AGENT_YN  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?, ?, SYSDATE, SYSDATE,?  ) ";
                    idpsmt1 = con.prepareStatement(idquery1);
                    idpsmt1.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                    idpsmt1.setString(2, isStrNull(idArr[i3].identNo));
                    idpsmt1.setString(3, isStrNull(idArr[i3].agentNm));
                    idpsmt1.setString(4, isStrNull(idArr[i3].depart));
                    idpsmt1.setString(5, isStrNull(idArr[i3].position));
                    idpsmt1.setString(6, isStrNull(idArr[i3].agent_phone));
                    idpsmt1.setString(7, isStrNull(idArr[i3].E_MAIL));
                    idpsmt1.setString(8, isStrNull(idArr[i3].mobile));
                    idpsmt1.setString(9, isStrNull(idArr[i3].FAX));
                    idpsmt1.setString(10, isStrNull(idArr[i3].agent_yn));
                    idpsmt1.executeUpdate();
                    idpsmt1.clearParameters();
                    this.um_adv_conra020c.serial_no4[0] = isStrNull(idArr[i3].serialNo4);
                    this.um_adv_conra020c.depart[0] = isStrNull(idArr[i3].depart);
                    this.um_adv_conra020c.position[0] = isStrNull(idArr[i3].position);
                    this.um_adv_conra020c.nm[0] = isStrNull(idArr[i3].agentNm);
                    this.um_adv_conra020c.agent_phone[0] = isStrNull(idArr[i3].agent_phone);
                    this.um_adv_conra020c.ident_no[0] = isStrNull(idArr[i3].identNo);
                    this.um_adv_conra020c.E_MAIL[0] = isStrNull(idArr[i3].E_MAIL);
                    this.um_adv_conra020c.FAX[0] = isStrNull(idArr[i3].FAX);
                    this.um_adv_conra020c.mobile[0] = isStrNull(idArr[i3].mobile);
                    this.um_adv_conra020c.agent_yn[0] = isStrNull(idArr[i3].agent_yn);
                    this.idReplacePutValues(dsource, this.um_adv_conra020c.serial_no4, this.일련번호4, i3, 0, "I");
                    this.idReplacePutValues(dsource, this.um_adv_conra020c.depart, this.부서, i3, 0, "I");
                    this.idReplacePutValues(dsource, this.um_adv_conra020c.position, this.직책명, i3, 0, "I");
                    this.idReplacePutValues(dsource, this.um_adv_conra020c.nm, this.성명, i3, 0, "I");
                    this.idReplacePutValues(dsource, this.um_adv_conra020c.agent_phone, this.입찰대리인전화번호, i3, 0, "I");
                    this.idReplacePutValues(dsource, this.um_adv_conra020c.ident_no, this.주민등록번호, i3, 0, "I");
                    this.idReplacePutValues(dsource, this.um_adv_conra020c.E_MAIL, this.E_MAIL, i3, 0, "I");
                    this.idReplacePutValues(dsource, this.um_adv_conra020c.FAX, this.FAX, i3, 0, "I");
                    this.idReplacePutValues(dsource, this.um_adv_conra020c.mobile, this.휴대폰, i3, 0, "I");
                    this.idReplacePutValues(dsource, this.um_adv_conra020c.agent_yn, this.입찰대리인확인여부, i3, 0, "I");
                }
                miquery1 = " INSERT INTO UM_SUPPLIER_ENTER_MAST_HIST (  BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM,  COMMENCEMENT_DT, ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS,  CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL,  EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR,  DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECIAL_GOODS_YN,  REG_DT, UPDATE_DT, REPR_BIZ_APPROVE_YN, MANAGER_ID, MAST_GOOD_CLS_NO, MAST_REPR_NM, PERMIT_BRANCH  ) VALUES (  ?, ?, ?, ?,  ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  SYSDATE, SYSDATE, 'Y', ?,  ?, ?, ?, ?  ) ";
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
                mipsmt1.setString(10, isStrNull("2"));
                mipsmt1.setString(11, isStrNull(this.um_adv_conra020c.biz_cls_2[0]));
                mipsmt1.setString(12, isStrNull("0000"));
                if (" ".equals(this.um_adv_conra020c.capital[0]) || "".equals(this.um_adv_conra020c.capital[0]) || this.um_adv_conra020c.capital[0] == null) {
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
                mipsmt1.setString(24, isStrNull(procID));
                if (jpi2 != -1) {
                    mipsmt1.setString(25, isStrNull(jpArr2[jpi2].물품분류번호1));
                }
                else {
                    mipsmt1.setString(25, " ");
                }
                if (msi != -1) {
                    mipsmt1.setString(26, isStrNull(msArr[msi].licencse_cd));
                }
                else {
                    mipsmt1.setString(26, " ");
                }
                if (dpi != -1) {
                    mipsmt1.setString(27, isStrNull(dpArr[dpi].repr_nm));
                }
                else {
                    mipsmt1.setString(27, " ");
                }
                mipsmt1.setString(28, isStrNull(jicheong));
                mipsmt1.executeUpdate();
                mipsmt1.clearParameters();
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
                jmquery1 = " UPDATE UM_EDOC_STATE SET  PROCESS_DT = ?, PROCESS_RSON = ?, COM_NM = ?, REPR_NM = ?, ZIP_CD = ?,  ADDR = ?, DETAIL_ADDR = ?, PHONE_NO = ?, WEBSITE = ?, PERMIT_BRANCH = ?,  PROCESS_DT = SYSDATE  WHERE TRANS_NO = '" + transNo + "' " + " AND MOD_CLS = '1' ";
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
                query1 = "SELECT COUNT(*) FROM UM_REC_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO=? ";
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
                    catch (Exception ex5) {}
                }
                if (!mainsaupNoCheck.equals("0")) {
                    dpquery1 = " DELETE FROM UM_REC_REPR  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    gjquery1 = " DELETE FROM UM_REC_FACTORY_INFO  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
                    gjpsmt1 = con.prepareStatement(gjquery1);
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                    jpquery1 = " DELETE FROM UM_REC_SUPPLIER_ENTER_ITEM  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
                    jppsmt1 = con.prepareStatement(jpquery1);
                    jppsmt1.executeUpdate();
                    jppsmt1.clearParameters();
                    msquery1 = " DELETE FROM UM_REC_LICENSE_FACTS  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
                    mspsmt1 = con.prepareStatement(msquery1);
                    mspsmt1.executeUpdate();
                    mspsmt1.clearParameters();
                    idquery1 = " DELETE FROM UM_REC_BID_AGENT  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
                    idpsmt1 = con.prepareStatement(idquery1);
                    idpsmt1.executeUpdate();
                    idpsmt1.clearParameters();
                    query1 = " DELETE FROM UM_REC_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
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
                    Log.debug("[UM_ADV_ConrA010c] Work화일(" + newDocFilename + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt);
                }
                else {
                    Log.errors("[UM_ADV_ConrA010c] Work화일(" + newDocFilename + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt);
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
                    catch (Exception ex6) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex7) {}
                }
                psmt2 = con.prepareStatement(query_1);
                psmt2.setString(1, isStrNull(procID));
                rs = psmt2.executeQuery();
                String jicheong2 = "";
                if (rs.next()) {
                    jicheong2 = rs.getString(1);
                }
                psmt2.clearParameters();
                jmquery1 = " UPDATE UM_EDOC_STATE  SET  PROCESS_DT = ?, PROCESS_RSON = ?, COM_NM = ?, REPR_NM = ?, ZIP_CD = ?,  ADDR = ?, DETAIL_ADDR = ?, PHONE_NO = ?, WEBSITE = ?, PERMIT_BRANCH = ?,  PROCESS_DT = SYSDATE  WHERE TRANS_NO = '" + transNo + "' " + " AND MOD_CLS = '1' ";
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
                    Log.debug("[UM_ADV_ConrA010c] Work화일(" + newDocFilename2 + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt2);
                }
                else {
                    Log.errors("[UM_ADV_ConrA010c] Work화일(" + newDocFilename2 + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt2);
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
                    catch (Exception ex8) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex9) {}
                }
                psmt2 = con.prepareStatement(query_1);
                psmt2.setString(1, isStrNull(procID));
                rs = psmt2.executeQuery();
                String jicheong2 = "";
                if (rs.next()) {
                    jicheong2 = rs.getString(1);
                }
                psmt2.clearParameters();
                jmquery1 = " UPDATE UM_EDOC_STATE  SET  PROCESS_DT = ?, PROCESS_RSON = ?, COM_NM = ?, REPR_NM = ?, ZIP_CD = ?,  ADDR = ?, DETAIL_ADDR = ?, PHONE_NO = ?, WEBSITE = ?, PERMIT_BRANCH = ?,  PROCESS_DT = SYSDATE  WHERE TRANS_NO = '" + transNo + "' " + " AND MOD_CLS = '1' ";
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
                psmt2 = con.prepareStatement(query1);
                psmt2.setString(1, isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                rs = psmt2.executeQuery();
                String mainsaupNoCheck2 = "0";
                if (rs.next()) {
                    mainsaupNoCheck2 = rs.getString(1);
                }
                psmt2.clearParameters();
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex10) {}
                }
                if (!mainsaupNoCheck2.equals("0")) {
                    dpquery1 = " DELETE FROM UM_REC_REPR  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
                    dppsmt1 = con.prepareStatement(dpquery1);
                    dppsmt1.executeUpdate();
                    dppsmt1.clearParameters();
                    gjquery1 = " DELETE FROM UM_REC_FACTORY_INFO  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
                    gjpsmt1 = con.prepareStatement(gjquery1);
                    gjpsmt1.executeUpdate();
                    gjpsmt1.clearParameters();
                    jpquery1 = " DELETE FROM UM_REC_SUPPLIER_ENTER_ITEM  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
                    jppsmt1 = con.prepareStatement(jpquery1);
                    jppsmt1.executeUpdate();
                    jppsmt1.clearParameters();
                    msquery1 = " DELETE FROM UM_REC_LICENSE_FACTS  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
                    mspsmt1 = con.prepareStatement(msquery1);
                    mspsmt1.executeUpdate();
                    mspsmt1.clearParameters();
                    idquery1 = " DELETE FROM UM_REC_BID_AGENT  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
                    idpsmt1 = con.prepareStatement(idquery1);
                    idpsmt1.executeUpdate();
                    idpsmt1.clearParameters();
                    query1 = " DELETE FROM UM_REC_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = '" + isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' ";
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
                res3 = "<?xml version = '1.0' encoding = 'EUC-KR'?>" + res3.substring(res3.indexOf("?>") + 2);
                p3.close();
                baos3.close();
                final int writeCnt3 = this.writefile(newDocFilename3, res3);
                if (writeCnt3 == 0) {
                    Log.debug("[UM_ADV_ConrA010c] Work화일(" + newDocFilename3 + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt3);
                }
                else {
                    Log.errors("[UM_ADV_ConrA010c] Work화일(" + newDocFilename3 + "), 처리상태::" + status + "::파일덮어쓰기결과::" + writeCnt3);
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
                Log.debug("UM_ADV_ConrA010c.doPost block SQLException : Transaction Rollback간에 SQLException 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
                con.rollback();
                result = 99;
            }
            Log.debug("UM_ADV_ConrA010c.doPost block SQLException : ");
            Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
            con.rollback();
            result = 99;
        }
        catch (Exception exc2) {
            try {
                con.rollback();
                con.setAutoCommit(true);
            }
            catch (SQLException e) {
                Log.debug("UM_ADV_ConrA010c.doPost block Exception : Transaction Rollback간에 Exception 발생함");
                Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                e.printStackTrace();
                con.rollback();
                result = 99;
            }
            Log.debug("UM_ADV_ConrA010c.doPost block Exception : ");
            Log.debug("Exception발생 사유 : " + exc2.toString());
            exc2.printStackTrace();
            con.rollback();
            result = 99;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex11) {}
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex12) {}
            }
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex13) {}
            }
            if (dppsmt1 != null) {
                try {
                    dppsmt1.close();
                }
                catch (Exception ex14) {}
            }
            if (mspsmt1 != null) {
                try {
                    mspsmt1.close();
                }
                catch (Exception ex15) {}
            }
            if (gjpsmt1 != null) {
                try {
                    gjpsmt1.close();
                }
                catch (Exception ex16) {}
            }
            if (idpsmt1 != null) {
                try {
                    idpsmt1.close();
                }
                catch (Exception ex17) {}
            }
            if (jppsmt1 != null) {
                try {
                    jppsmt1.close();
                }
                catch (Exception ex18) {}
            }
            if (jmpsmt1 != null) {
                try {
                    jmpsmt1.close();
                }
                catch (Exception ex19) {}
            }
            if (mipsmt1 != null) {
                try {
                    mipsmt1.close();
                }
                catch (Exception ex20) {}
            }
            if (bupsmt1 != null) {
                try {
                    bupsmt1.close();
                }
                catch (Exception ex21) {}
            }
            if (bupsmt2 != null) {
                try {
                    bupsmt2.close();
                }
                catch (Exception ex22) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex23) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex24) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex25) {}
        }
        if (psmt3 != null) {
            try {
                psmt3.close();
            }
            catch (Exception ex26) {}
        }
        if (dppsmt1 != null) {
            try {
                dppsmt1.close();
            }
            catch (Exception ex27) {}
        }
        if (mspsmt1 != null) {
            try {
                mspsmt1.close();
            }
            catch (Exception ex28) {}
        }
        if (gjpsmt1 != null) {
            try {
                gjpsmt1.close();
            }
            catch (Exception ex29) {}
        }
        if (idpsmt1 != null) {
            try {
                idpsmt1.close();
            }
            catch (Exception ex30) {}
        }
        if (jppsmt1 != null) {
            try {
                jppsmt1.close();
            }
            catch (Exception ex31) {}
        }
        if (jmpsmt1 != null) {
            try {
                jmpsmt1.close();
            }
            catch (Exception ex32) {}
        }
        if (mipsmt1 != null) {
            try {
                mipsmt1.close();
            }
            catch (Exception ex33) {}
        }
        if (bupsmt1 != null) {
            try {
                bupsmt1.close();
            }
            catch (Exception ex34) {}
        }
        if (bupsmt2 != null) {
            try {
                bupsmt2.close();
            }
            catch (Exception ex35) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex36) {}
        }
        return result;
    }
    
    public static void GetValues(final XMLDocument doc, final String[] node) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            final NodeList tmpNodeList = doc.selectNodes(node[1].trim(), (NSResolver)xemt);
            node[0] = tmpNodeList.item(0).getFirstChild().getNodeValue();
        }
        catch (NullPointerException ne) {
            node[0] = "";
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrA010c.GetValues(1) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void GetValues(final XMLDocument doc, final String[] node, final int index) throws Exception {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            final NodeList tmpNodeList = doc.selectNodes(node[1].trim(), (NSResolver)xemt);
            node[0] = tmpNodeList.item(index).getFirstChild().getNodeValue();
        }
        catch (NullPointerException ne) {
            node[0] = "";
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrA010c.GetValues(1,2,3) Exception : ");
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
            Log.errors("UM_ADV_ConrA010c.PutValues() Exception : ");
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
            Log.errors("UM_ADV_ConrA010c.PutValues(1,2,3) Exception : ");
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
            Log.errors("UM_ADV_ConrA010c.dpReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("[" + IUD + "],[index : " + index + "] Exception 발생사유 : " + e.toString());
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
            }
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_ADV_ConrA010c.idReplacePutValues(1,2,3,4,5) Exception : ");
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
            Log.errors("UM_ADV_ConrA010c.licenseReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("[" + IUD + "],[index : " + index + "] Exception 발생사유 : " + e.toString());
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
            Log.errors("UM_ADV_ConrA010c.facReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("[" + IUD + "],[index : " + index + "] Exception 발생사유 : " + e.toString());
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
            Log.errors("UM_ADV_ConrA010c.jpReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("[" + IUD + "],[index : " + index + "] Exception 발생사유 : " + e.toString());
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
            Log.errors("UM_ADV_ConrA010c.maReplacePutValues(1,2,3,4,5) Exception : ");
            Log.errors("[" + IUD + "],[index : " + index + "] Exception 발생사유 : " + e.toString());
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
            Log.errors("UM_ADV_ConrA010c.maReplacePutValues1(1,2,3,4,5) Exception : ");
            Log.errors("[" + IUD + "],[index : " + index + "] Exception 발생사유 : " + e.toString());
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
            JMCancelquery = " SELECT 진행상태 FROM 사용_전자문서상태  WHERE 전송번호 = ?        ";
            psmt = con.prepareStatement(JMCancelquery);
            psmt.setString(1, transNo);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                returnValue = rs.getString(1);
            }
        }
        catch (SQLException exc) {
            Log.debug("UM_ADV_ConrA010c.getJMCancelCheck() block SQLException : ");
            Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
            exc.printStackTrace();
        }
        catch (Exception exc2) {
            Log.debug("UM_ADV_ConrA010c.getJMCancelCheck() block Exception : ");
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
    
    public void setTransNo(final String transNo) {
        this.transNo = transNo;
    }
    
    public void setCurrentMode(final int currentMode) {
        this.currentMode = currentMode;
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
            case 800: {
                query = " SELECT  mail, 휴대폰, SMS수신여부 " + this.getQueryCondition();
                break;
            }
        }
        return query;
    }
    
    public String getQueryCondition() {
        String queryCondition = "";
        switch (this.currentMode) {
            case 800: {
                queryCondition = " FROM 사용_전자문서상태  WHERE 전송번호 = '" + this.transNo + "' ";
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
