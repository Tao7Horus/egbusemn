// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import org.w3c.dom.NodeList;
import entity.UM_GOE_ConiC010b;
import java.io.InputStream;
import oracle.xml.parser.v2.XMLDocument;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.xml.sax.SAXException;
import oracle.xml.parser.v2.XMLParseException;
import common.ComStr;
import common.Log;
import oracle.xml.parser.v2.NSResolver;
import java.util.Vector;
import oracle.xml.parser.v2.XMLElement;
import entity.UM_GOE_ConiC110b;
import entity.UM_GOE_ConiC060b;
import entity.UM_GOE_ConiC050b;
import entity.UM_GOE_ConiC040b;
import entity.UM_GOE_ConiC030b;
import entity.UM_GOE_ConiC020b;
import beans.UM_URB_UserU010p;
import common.Trx;
import java.io.FileInputStream;
import oracle.xml.parser.v2.DOMParser;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class TestXML2DB extends HttpServlet
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
    
    public TestXML2DB() {
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
    
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        System.out.println("=========>GO ");
        boolean aflag = false;
        boolean bflag = false;
        final boolean xflag = false;
        final UM_ADV_ConrB010c ctl = new UM_ADV_ConrB010c();
        Connection con = null;
        final PreparedStatement psmt = null;
        PreparedStatement psmt2 = null;
        PreparedStatement psmt3 = null;
        final PreparedStatement psmt4 = null;
        PreparedStatement dppsmt1 = null;
        PreparedStatement mspsmt1 = null;
        final PreparedStatement gjpsmt1 = null;
        PreparedStatement idpsmt1 = null;
        final PreparedStatement jppsmt1 = null;
        final PreparedStatement jmpsmt1 = null;
        final PreparedStatement mipsmt1 = null;
        ResultSet rs = null;
        final String SQL = null;
        String query1 = null;
        String query2 = null;
        final String query3 = null;
        String dpquery1 = null;
        final String dpquery2 = null;
        String msquery1 = null;
        final String msquery2 = null;
        final String gjquery1 = null;
        final String gjquery2 = null;
        String idquery1 = null;
        final String idquery2 = null;
        final String jpquery1 = null;
        final String jpquery2 = null;
        final String jmquery1 = null;
        final String miquery1 = null;
        final String bstatus = "N";
        final String bsdate = "";
        final String bsdate2 = "";
        final String bsdate3 = "";
        final String bsdateTmp = "";
        final String bedate = "";
        final String bedate2 = "";
        final String bedate3 = "";
        final String bedateTmp = "";
        final String bcount = "";
        final String bcount2 = "";
        final String bcount3 = "";
        final String bcountTmp = "";
        final String bsaup = "";
        final String bsaup2 = "";
        final String bsaupTmp = "";
        final String bstatus_sdate = "";
        final String bstatus_edate = "";
        final String status_cancel = "";
        final String Upjongcode = "";
        final String Sipyongvalue = "";
        final String Sipyongyear = "";
        final String Sipyongyear2 = "";
        XMLDocument dsource = null;
        final DOMParser parser = new DOMParser();
        res.setContentType("text/xml;charset=utf-8");
        try {
            final InputStream is = new FileInputStream(String.valueOf(this.getServletContext().getRealPath("/")) + "/SU/ABCD.xml");
            parser.parse(is);
            dsource = parser.getDocument();
            Trx trx = null;
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            final String result = "";
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.biz_reg_no);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.nationality);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.biz_nm);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.biz_en_nm);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.commentcement_dt);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.establish_dt);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.biz_cls);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.product_cls);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.corp_reg_no);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.biz_cls_1);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.biz_cls_2);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.biz_cls_year);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.capital);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.employee_count_);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.lat_settle_dt);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.zip_cd);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.area_cd);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.addr);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.addr_detail);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.phone_no);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.fax);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.homepage);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.special_good_yn);
            UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.contr_rela_yn);
            final UM_URB_UserU010p ctl2 = new UM_URB_UserU010p();
            UM_GOE_ConiC010b ett = null;
            UM_GOE_ConiC020b[] ett2 = (UM_GOE_ConiC020b[])null;
            UM_GOE_ConiC030b[] ett3 = (UM_GOE_ConiC030b[])null;
            UM_GOE_ConiC040b[] ett4 = (UM_GOE_ConiC040b[])null;
            UM_GOE_ConiC050b[] ett5 = (UM_GOE_ConiC050b[])null;
            UM_GOE_ConiC060b[] ett6 = (UM_GOE_ConiC060b[])null;
            UM_GOE_ConiC110b[] ett7 = (UM_GOE_ConiC110b[])null;
            ett = ctl2.select_main(this.um_adv_conra020c.biz_reg_no[0]);
            ett2 = ctl2.select_MS(this.um_adv_conra020c.biz_reg_no[0]);
            ett3 = ctl2.select_DP(this.um_adv_conra020c.biz_reg_no[0]);
            ett4 = ctl2.select_GJ(this.um_adv_conra020c.biz_reg_no[0]);
            ett5 = ctl2.select_ID(this.um_adv_conra020c.biz_reg_no[0]);
            ett6 = ctl2.select_MU(this.um_adv_conra020c.biz_reg_no[0]);
            ett7 = ctl2.select_BR(this.um_adv_conra020c.biz_reg_no[0]);
            XMLElement xemt = null;
            xemt = (XMLElement)dsource.getDocumentElement();
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
            psmt2.setString(1, "A000000000001");
            rs = psmt2.executeQuery();
            String jicheong = "";
            if (rs.next()) {
                jicheong = rs.getString(1);
            }
            psmt2.clearParameters();
            query2 = " UPDATE UM_SUPPLIER_ENTER_MAST SET  NATIONALITY = '" + this.um_adv_conra020c.nationality[0] + " ', BIZ_NM = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_nm[0]) + "' , BIZ_EN_NM = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_en_nm[0]) + "' , " + " COMMENCEMENT_DT = to_date('" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.commentcement_dt[0]) + "','yyyymmdd') , ESTABLISH_DT = to_date('" + this.um_adv_conra020c.establish_dt[0] + "','yyyymmdd'), BIZ_CLS = '1', PRODUCT_CLS = '1', " + " CORP_REG_NO = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.corp_reg_no[0]) + "' , BIZ_CLS2 = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_cls_2[0]) + "', BIZ_CLS_YEAR = '0000', CAPITAL = '" + this.um_adv_conra020c.capital[0] + "' , " + " EMPLOYEE_COUNT = '" + this.um_adv_conra020c.employee_count_[0] + "' , LAST_SETTLE_DT = '0000-00', " + " ZIP_CD = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.zip_cd[0]) + "' , AREA_CD = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.area_cd[0]) + "' , ADDR = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.addr[0]) + "' , " + " DETAIL_ADDR = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.addr_detail[0]) + "' , PHONE_NO = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.phone_no[0]) + "' , FAX = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.fax[0]) + "', WEBSITE = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.homepage[0]) + "' , SPECIAL_GOODS_YN = '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.special_good_yn[0]) + "', " + " UPDATE_DT = SYSDATE, MANAGER_ID = '1', PERMIT_BRANCH = '1', BID_ATTEND_QUALIFY_YN = null ,CONTR_RELA_YN='" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.contr_rela_yn[0]) + "'" + " WHERE BIZ_REG_NO = '" + this.um_adv_conra020c.biz_reg_no[0] + "' ";
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex3) {}
            }
            psmt3 = con.prepareStatement(query2);
            System.out.print(query2);
            psmt3.executeUpdate();
            psmt3.clearParameters();
            this.um_adv_conra020c.nationality[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.nationality[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.nationality);
            this.um_adv_conra020c.biz_nm[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_nm[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.biz_nm);
            this.um_adv_conra020c.biz_en_nm[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_en_nm[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.biz_en_nm);
            this.um_adv_conra020c.commentcement_dt[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.commentcement_dt[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.commentcement_dt);
            this.um_adv_conra020c.establish_dt[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.establish_dt[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.establish_dt);
            this.um_adv_conra020c.biz_cls[0] = "";
            ctl.replacePutValues(dsource, this.um_adv_conra020c.biz_cls);
            this.um_adv_conra020c.product_cls[0] = "";
            ctl.replacePutValues(dsource, this.um_adv_conra020c.product_cls);
            this.um_adv_conra020c.corp_reg_no[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.corp_reg_no[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.corp_reg_no);
            this.um_adv_conra020c.biz_cls_1[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_cls_1[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.biz_cls_1);
            this.um_adv_conra020c.biz_cls_2[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_cls_2[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.biz_cls_2);
            this.um_adv_conra020c.biz_cls_year[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_cls_year[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.biz_cls_year);
            if ("".equals(this.um_adv_conra020c.capital[0])) {
                this.um_adv_conra020c.capital[0] = "0";
            }
            else {
                this.um_adv_conra020c.capital[0] = this.um_adv_conra020c.capital[0];
            }
            ctl.replacePutValues(dsource, this.um_adv_conra020c.capital);
            this.um_adv_conra020c.employee_count_[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.employee_count_[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.employee_count_);
            this.um_adv_conra020c.lat_settle_dt[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.lat_settle_dt[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.lat_settle_dt);
            this.um_adv_conra020c.zip_cd[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.zip_cd[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.zip_cd);
            this.um_adv_conra020c.area_cd[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.area_cd[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.area_cd);
            this.um_adv_conra020c.addr[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.addr[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.addr);
            this.um_adv_conra020c.addr_detail[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.addr_detail[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.addr_detail);
            this.um_adv_conra020c.phone_no[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.phone_no[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.phone_no);
            this.um_adv_conra020c.fax[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.fax[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.fax);
            this.um_adv_conra020c.homepage[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.homepage[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.homepage);
            this.um_adv_conra020c.special_good_yn[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.special_good_yn[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.special_good_yn);
            this.um_adv_conra020c.permit_branch[0] = "";
            ctl.replacePutValues(dsource, this.um_adv_conra020c.permit_branch);
            this.um_adv_conra020c.contr_rela_yn[0] = UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.contr_rela_yn[0]);
            ctl.replacePutValues(dsource, this.um_adv_conra020c.contr_rela_yn);
            final String[][] JPUpmu = ctl.getJPData();
            final Vector msv = new Vector(1, 1);
            final Vector jpv1 = new Vector(1, 1);
            UM_ADV_ConrA030c[] msArr = (UM_ADV_ConrA030c[])null;
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
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.serial_no3, i);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.license_cd, i);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.license_nm, i);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.license_no, i);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.license_issued_dt, i);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.license_expiry_dt, i);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.const_abil_eval_amt, i);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.eval_std_year, i);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.mast_license_yn, i);
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
            final String[] bfJobGubun = ctl.getMainData();
            final String bfJobGubun2 = bfJobGubun[1].substring(0, 1);
            final String bfJobGubun3 = bfJobGubun[1].substring(1, 2);
            final String bfJobGubun4 = bfJobGubun[1].substring(2, 3);
            final String bfJobGubun5 = bfJobGubun[1].substring(3, 4);
            final String bfJobGubun6 = bfJobGubun[1].substring(4, 5);
            final Vector dpv = new Vector(1, 1);
            UM_ADV_ConrA030c[] dpArr = (UM_ADV_ConrA030c[])null;
            final NodeList dpNodeList = dsource.selectNodes("/gb:Gsacid/Supplier.Organization.Details/CeoList/CeoItem", (NSResolver)xemt);
            final int dpCount = dpNodeList.getLength();
            int dpi = -1;
            for (int j = 0; j < dpCount - 1; ++j) {
                final UM_ADV_ConrA030c dp = new UM_ADV_ConrA030c();
                try {
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.serial_no_1, j);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.rerp_ident_no, j);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.repr_nm, j);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.repr_mail, j);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.mast_repr_yn, j);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.repr_mobile, j);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.repr_cls, j);
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
            dpArr = new UM_ADV_ConrA030c[dpv.size()];
            dpv.copyInto(dpArr);
            final Vector dpv2 = new Vector(1, 1);
            UM_ADV_ConrA030c[] dpArr2 = (UM_ADV_ConrA030c[])null;
            final NodeList brNodeList = dsource.selectNodes("/gb:Gsacid/BranchList/BranchItem", (NSResolver)xemt);
            final int brCount = brNodeList.getLength();
            final int bri = -1;
            for (int k = 0; k < brCount - 1; ++k) {
                final UM_ADV_ConrA030c br = new UM_ADV_ConrA030c();
                try {
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_serial_no, k);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_biz_reg_no, k);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_biz_nm, k);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_ared_cd, k);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_area_nm, k);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_zip_cd, k);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_add, k);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_add2, k);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_phone, k);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_fax, k);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_repr_ident_no, k);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.branch_rept_nm, k);
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
            dpArr2 = new UM_ADV_ConrA030c[dpv2.size()];
            dpv2.copyInto(dpArr2);
            final Vector dpv3 = new Vector(1, 1);
            UM_ADV_ConrA030c[] dpArr3 = (UM_ADV_ConrA030c[])null;
            final NodeList idNodeList = dsource.selectNodes("/gb:Gsacid/RepList/RepItem", (NSResolver)xemt);
            final int dpCount2 = idNodeList.getLength();
            for (int l = 0; l < dpCount2 - 1; ++l) {
                final UM_ADV_ConrA030c dp2 = new UM_ADV_ConrA030c();
                try {
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.serial_no4, l);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.ident_no, l);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.nm, l);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.depart, l);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.position, l);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.agent_phone, l);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.E_MAIL, l);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.mobile, l);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.FAX, l);
                    UM_ADV_ConrB010c.GetValues(dsource, this.um_adv_conra020c.agent_yn, l);
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
            dpArr3 = new UM_ADV_ConrA030c[dpv3.size()];
            dpv3.copyInto(dpArr3);
            final Vector idv = new Vector(1, 1);
            final UM_ADV_ConrA030c[] idArr = (UM_ADV_ConrA030c[])null;
            if (ett3.length > 0) {
                for (int m = 0; m < ett3.length; ++m) {
                    for (int j2 = 0; j2 < dpCount - 1; ++j2) {
                        if (ett3[m].getCeoJuminNo().equals(dpArr[j2].repr_ident_no)) {
                            aflag = true;
                            if (!UM_ADV_ConrB010c.isStrNull((ett3[m].getCeoName() == null) ? "" : ett3[m].getCeoName()).equals(UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_nm))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett3[m].getCeoMail() == null) ? "" : ett3[m].getCeoMail()).equals(UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_email))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett3[m].getCeoYN() == null) ? "" : ett3[m].getCeoYN()).equals(UM_ADV_ConrB010c.isStrNull(dpArr[j2].mast_repr_yn))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett3[m].getCeohphone() == null) ? "" : ett3[m].getCeohphone()).equals(UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_mobile))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett3[m].getCeoyuhyung() == null) ? "" : ett3[m].getCeoyuhyung()).equals(UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_cls))) {
                                bflag = true;
                            }
                            if (bflag) {
                                dpquery1 = "UPDATE UM_REPR SET REPR_NM='" + UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_nm) + "', REPR_EMAIL='" + UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_email) + "', MAST_REPR_YN='" + UM_ADV_ConrB010c.isStrNull(dpArr[j2].mast_repr_yn) + "', UPDATE_DT=SYSDATE, REPR_MOBILE='" + UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_mobile) + "', REPR_CLS='" + UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_cls) + "'" + "WHERE BIZ_REG_NO= '" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "' and REPR_IDENT_NO= '" + UM_ADV_ConrB010c.isStrNull(ett3[m].getCeoJuminNo()) + "'";
                                if (dppsmt1 != null) {
                                    try {
                                        dppsmt1.close();
                                    }
                                    catch (Exception ex4) {}
                                }
                                System.out.print(dpquery1);
                                dppsmt1 = con.prepareStatement(dpquery1);
                                dppsmt1.executeQuery();
                                bflag = false;
                                this.um_adv_conra020c.serial_no_1[0] = UM_ADV_ConrB010c.isStrNull(dpArr[j2].serial_no1);
                                ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.serial_no_1, this.일련번호1, j2, 0, "U");
                                this.um_adv_conra020c.repr_nm[0] = UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_nm);
                                ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_nm, this.대표자명, j2, 0, "U");
                                this.um_adv_conra020c.repr_mail[0] = UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_email);
                                ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mail, this.대표자메일주소, j2, 0, "U");
                                this.um_adv_conra020c.mast_repr_yn[0] = UM_ADV_ConrB010c.isStrNull(dpArr[j2].mast_repr_yn);
                                ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.mast_repr_yn, this.대표대표자여부, j2, 0, "U");
                                this.um_adv_conra020c.repr_mobile[0] = UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_mobile);
                                ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mobile, this.대표자휴대폰, j2, 0, "U");
                                this.um_adv_conra020c.repr_cls[0] = UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_cls);
                                ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_cls, this.대표자유형, j2, 0, "U");
                                this.um_adv_conra020c.rerp_ident_no[0] = UM_ADV_ConrB010c.isStrNull(dpArr[j2].repr_ident_no);
                                ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.rerp_ident_no, this.대표자주민번호, j2, 0, "U");
                            }
                        }
                    }
                    if (!aflag) {
                        dpquery1 = "DELETE FROM UM_REPR WHERE BIZ_REG_NO='" + this.um_adv_conra020c.biz_reg_no[0] + "' and REPR_IDENT_NO='" + ett3[m].getCeoJuminNo() + "'";
                        if (dppsmt1 != null) {
                            try {
                                dppsmt1.close();
                            }
                            catch (Exception ex5) {}
                        }
                        dppsmt1 = con.prepareStatement(dpquery1);
                        System.out.print("\n" + dpquery1);
                        dppsmt1.executeQuery();
                        this.um_adv_conra020c.serial_no_1[0] = Integer.toString(m + 1);
                        ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.serial_no_1, this.일련번호1, m, 0, "D");
                    }
                    aflag = false;
                }
            }
            if (dpCount - 1 > 0) {
                for (int m = 0; m < dpCount - 1; ++m) {
                    for (int j2 = 0; j2 < ett3.length; ++j2) {
                        if (dpArr[m].repr_ident_no.equals(ett3[j2].getCeoJuminNo())) {
                            aflag = true;
                        }
                    }
                    if (!aflag) {
                        System.out.print("thuc hien them nguoi dai dien\n");
                        dpquery1 = "INSERT INTO UM_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, REPR_EMAIL, MAST_REPR_YN, REG_DT, UPDATE_DT, REPR_MOBILE, REPR_CLS) VALUES ('" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_reg_no[0]) + "', '" + UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_ident_no) + "','" + UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_nm) + "','" + UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_email) + "','" + UM_ADV_ConrB010c.isStrNull(dpArr[m].mast_repr_yn) + "', SYSDATE, SYSDATE,'" + UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_mobile) + "','" + UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_cls) + "')";
                        if (dppsmt1 != null) {
                            try {
                                dppsmt1.close();
                            }
                            catch (Exception ex6) {}
                        }
                        dppsmt1 = con.prepareStatement(dpquery1);
                        System.out.print(dpquery1);
                        dppsmt1.executeQuery();
                        this.um_adv_conra020c.serial_no_1[0] = UM_ADV_ConrB010c.isStrNull(dpArr[m].serial_no1);
                        ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.serial_no_1, this.일련번호1, m, 0, "I");
                        this.um_adv_conra020c.repr_nm[0] = UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_nm);
                        ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_nm, this.대표자명, m, 0, "I");
                        this.um_adv_conra020c.repr_mail[0] = UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_email);
                        ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mail, this.대표자메일주소, m, 0, "I");
                        this.um_adv_conra020c.mast_repr_yn[0] = UM_ADV_ConrB010c.isStrNull(dpArr[m].mast_repr_yn);
                        ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.mast_repr_yn, this.대표대표자여부, m, 0, "I");
                        this.um_adv_conra020c.repr_mobile[0] = UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_mobile);
                        ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_mobile, this.대표자휴대폰, m, 0, "I");
                        this.um_adv_conra020c.repr_cls[0] = UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_cls);
                        ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.repr_cls, this.대표자유형, m, 0, "I");
                        this.um_adv_conra020c.rerp_ident_no[0] = UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_ident_no);
                        ctl.dpReplacePutValues(dsource, this.um_adv_conra020c.rerp_ident_no, this.대표자주민번호, m, 0, "I");
                        Log.debug("um_adv_conra020c.일련번호1[0]" + UM_ADV_ConrB010c.isStrNull(dpArr[m].serial_no1));
                        Log.debug("um_adv_conra020c.대표자명[0]" + UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_nm));
                        Log.debug("um_adv_conra020c.대표자메일주소[0]" + UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_email));
                        Log.debug("um_adv_conra020c.대표대표자여부[0]" + UM_ADV_ConrB010c.isStrNull(dpArr[m].mast_repr_yn));
                        Log.debug("um_adv_conra020c.대표자휴대폰[0]" + UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_mobile));
                        Log.debug("um_adv_conra020c.대표자유형[0]" + UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_cls));
                        Log.debug("um_adv_conra020c.대표자주민번호[0]" + UM_ADV_ConrB010c.isStrNull(dpArr[m].repr_ident_no));
                    }
                    aflag = false;
                }
            }
            if (ett7.length > 0) {
                for (int m = 0; m < ett7.length; ++m) {
                    for (int j2 = 0; j2 < brCount - 1; ++j2) {
                        if (ett7[m].getSaupNo().equals(dpArr2[j2].branch_biz_reg_no)) {
                            aflag = true;
                            if (!UM_ADV_ConrB010c.isStrNull((ett7[m].getCeoName() == null) ? "" : ett7[m].getCeoName()).equals(UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_repr_nm))) {
                                bflag = true;
                            }
                            if (bflag) {
                                dpquery1 = "  UPDATE UM_REPR SET   REPR_NM=?, UPDATE_DT=SYSDATE, REPR_IDENT_NO=?  WHERE BIZ_REG_NO=?";
                                if (dppsmt1 != null) {
                                    try {
                                        dppsmt1.close();
                                    }
                                    catch (Exception ex7) {}
                                }
                                dppsmt1 = con.prepareStatement(dpquery1);
                                dppsmt1.setString(1, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_repr_nm));
                                dppsmt1.setString(2, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_repr_ident_no));
                                dppsmt1.setString(3, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_biz_reg_no));
                                dppsmt1.executeQuery();
                                Log.debug("등록취소 삭제(정상업체로 전환)  : " + UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_biz_reg_no));
                                dpquery1 = "DELETE UM_ENTER_STATE WHERE BIZ_REG_NO=? and STATE_CLS='07'";
                                if (dppsmt1 != null) {
                                    try {
                                        dppsmt1.close();
                                    }
                                    catch (Exception ex8) {}
                                }
                                dppsmt1 = con.prepareStatement(dpquery1);
                                dppsmt1.setString(1, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_biz_reg_no));
                                dppsmt1.executeUpdate();
                                bflag = false;
                                this.um_adv_conra020c.branch_serial_no[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_serial_no);
                                ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_serial_no, this.지사일련번호, j2, 0, "U");
                                this.um_adv_conra020c.branch_rept_nm[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_repr_nm);
                                ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_rept_nm, this.지사대표자명, j2, 0, "U");
                                this.um_adv_conra020c.branch_repr_ident_no[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_repr_ident_no);
                                ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_repr_ident_no, this.지사대표자주민번호, j2, 0, "U");
                            }
                        }
                    }
                    for (int j2 = 0; j2 < brCount - 1; ++j2) {
                        if (ett7[m].getSaupNo().equals(dpArr2[j2].branch_biz_reg_no)) {
                            aflag = true;
                            if (!UM_ADV_ConrB010c.isStrNull((ett7[m].getSangho() == null) ? "" : ett7[m].getSangho()).equals(UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_biz_nm))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett7[m].getLocalCode() == null) ? "" : ett7[m].getLocalCode()).equals(UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_area_cd))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett7[m].getZipCode() == null) ? "" : ett7[m].getZipCode()).equals(UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_zip_cd))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett7[m].getAddr() == null) ? "" : ett7[m].getAddr()).equals(UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_add))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett7[m].getRestAddr() == null) ? "" : ett7[m].getRestAddr()).equals(UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_add2))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett7[m].getTel() == null) ? "" : ett7[m].getTel()).equals(UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_phone))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett7[m].getFax() == null) ? "" : ett7[m].getFax()).equals(UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_fax))) {
                                bflag = true;
                            }
                            if (bflag) {
                                Log.debug("지사업체 업데이트 : " + UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_biz_reg_no));
                                query2 = " UPDATE UM_SUPPLIER_ENTER_MAST SET  BIZ_NM = ?, ZIP_CD = ?, AREA_CD = ?, ADDR = ?, DETAIL_ADDR = ?,  PHONE_NO = ?, FAX = ?,  UPDATE_DT = SYSDATE, MANAGER_ID = ?, PERMIT_BRANCH = ?, BID_ATTEND_QUALIFY_YN = null  WHERE BIZ_REG_NO = ? ";
                                if (psmt3 != null) {
                                    try {
                                        psmt3.close();
                                    }
                                    catch (Exception ex9) {}
                                }
                                psmt3 = con.prepareStatement(query2);
                                psmt3.setString(1, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_biz_nm));
                                psmt3.setString(2, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_zip_cd));
                                psmt3.setString(3, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_area_cd));
                                psmt3.setString(4, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_add));
                                psmt3.setString(5, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_add2));
                                psmt3.setString(6, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_phone));
                                psmt3.setString(7, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_fax));
                                psmt3.setString(8, UM_ADV_ConrB010c.isStrNull(" "));
                                psmt3.setString(9, UM_ADV_ConrB010c.isStrNull(jicheong));
                                psmt3.setString(10, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_biz_reg_no));
                                psmt3.executeUpdate();
                                psmt3.clearParameters();
                                Log.debug("등록취소 삭제(정상업체로 전환)  : " + UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_biz_reg_no));
                                dpquery1 = "DELETE UM_ENTER_STATE WHERE BIZ_REG_NO=? and STATE_CLS='07'";
                                if (dppsmt1 != null) {
                                    try {
                                        dppsmt1.close();
                                    }
                                    catch (Exception ex10) {}
                                }
                                dppsmt1 = con.prepareStatement(dpquery1);
                                dppsmt1.setString(1, UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_biz_reg_no));
                                dppsmt1.executeUpdate();
                                bflag = false;
                                this.um_adv_conra020c.branch_serial_no[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_serial_no);
                                ctl.brReplacePutValues(dsource, this.um_adv_conra020c.branch_serial_no, this.지사일련번호, j2, 0, "U");
                                this.um_adv_conra020c.branch_biz_nm[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_biz_nm);
                                ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_biz_nm, this.지사상호명, j2, 0, "U");
                                this.um_adv_conra020c.branch_zip_cd[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_zip_cd);
                                ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_zip_cd, this.지사우편번호, j2, 0, "U");
                                this.um_adv_conra020c.branch_ared_cd[0] = UM_ADV_ConrB010c.isStrNull(dpArr[j2].branch_area_cd);
                                ctl.brReplacePutValues(dsource, this.um_adv_conra020c.branch_ared_cd, this.지사지역코드, j2, 0, "U");
                                this.um_adv_conra020c.branch_add[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_add);
                                ctl.brReplacePutValues(dsource, this.um_adv_conra020c.branch_add, this.지사주소, j2, 0, "U");
                                this.um_adv_conra020c.branch_add2[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_add2);
                                ctl.brReplacePutValues(dsource, this.um_adv_conra020c.branch_add2, this.지사나머지주소, j2, 0, "U");
                                this.um_adv_conra020c.branch_phone[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_phone);
                                ctl.brReplacePutValues(dsource, this.um_adv_conra020c.branch_phone, this.지사전화번호, j2, 0, "U");
                                this.um_adv_conra020c.branch_fax[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[j2].branch_fax);
                                ctl.brReplacePutValues(dsource, this.um_adv_conra020c.branch_fax, this.지사FAX번호, j2, 0, "U");
                            }
                        }
                    }
                    if (!aflag) {
                        Log.debug("등록취소 인서트  : " + UM_ADV_ConrB010c.isStrNull(ett7[m].getSaupNo()));
                        dpquery1 = "INSERT INTO UM_ENTER_STATE (BIZ_REG_NO, RAISED_DT, START_DT, STATE_CLS, REMARK, MANAGER_ID, PROCESS_DT) VALUES (?, SYSDATE, SYSDATE,  '07', ?, 'APPROVAL', SYSDATE)";
                        if (dppsmt1 != null) {
                            try {
                                dppsmt1.close();
                            }
                            catch (Exception ex11) {}
                        }
                        dppsmt1 = con.prepareStatement(dpquery1);
                        dppsmt1.setString(1, UM_ADV_ConrB010c.isStrNull(ett7[m].getSaupNo()));
                        dppsmt1.setString(2, "지사등록후 삭제처리(본사)");
                        dppsmt1.executeUpdate();
                    }
                    aflag = false;
                }
            }
            if (brCount - 1 > 0) {
                for (int m = 0; m < brCount - 1; ++m) {
                    for (int j2 = 0; j2 < ett7.length; ++j2) {
                        if (dpArr2[m].branch_biz_reg_no.equals(ett7[j2].getSaupNo())) {
                            aflag = true;
                        }
                    }
                    if (!aflag) {
                        Log.debug("지사대표자 인서트 : " + UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_biz_reg_no));
                        dpquery1 = "INSERT INTO UM_REPR (BIZ_REG_NO, REPR_IDENT_NO, REPR_NM, MAST_REPR_YN, REG_DT, UPDATE_DT) VALUES (?, ?, ?, ?, SYSDATE, SYSDATE)";
                        if (dppsmt1 != null) {
                            try {
                                dppsmt1.close();
                            }
                            catch (Exception ex12) {}
                        }
                        dppsmt1 = con.prepareStatement(dpquery1);
                        dppsmt1.setString(1, UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_biz_reg_no));
                        dppsmt1.setString(2, UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_repr_ident_no));
                        dppsmt1.setString(3, UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_repr_nm));
                        dppsmt1.setString(4, "Y");
                        dppsmt1.executeQuery();
                        this.um_adv_conra020c.branch_serial_no[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_serial_no);
                        final int seq_num = UM_ADV_ConrB010c.isIntNull(dpArr2[m].branch_serial_no);
                        ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_serial_no, this.지사일련번호, seq_num, 0, "I");
                        this.um_adv_conra020c.branch_rept_nm[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_repr_nm);
                        ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_rept_nm, this.지사대표자명, seq_num, 0, "I");
                        this.um_adv_conra020c.branch_repr_ident_no[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_repr_ident_no);
                        ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_repr_ident_no, this.지사대표자주민번호, seq_num, 0, "I");
                    }
                    aflag = false;
                }
            }
            if (brCount - 1 > 0) {
                for (int m = 0; m < brCount - 1; ++m) {
                    for (int j2 = 0; j2 < ett7.length; ++j2) {
                        if (dpArr2[m].branch_biz_reg_no.equals(ett7[j2].getSaupNo())) {
                            aflag = true;
                        }
                    }
                    if (!aflag) {
                        Log.debug("지사업체 인서트 : " + UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_biz_reg_no));
                        query2 = " INSERT INTO UM_SUPPLIER_ENTER_MAST (  BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM,  COMMENCEMENT_DT, ESTABLISH_DT, BIZ_CLS, PRODUCT_CLS,  CORP_REG_NO, BIZ_CLS1, BIZ_CLS2, BIZ_CLS_YEAR, CAPITAL,  EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD, AREA_CD, ADDR,  DETAIL_ADDR, PHONE_NO, FAX, WEBSITE, SPECIAL_GOODS_YN,  REG_DT, UPDATE_DT, REPR_BIZ_APPROVE_YN, MANAGER_ID,  PERMIT_BRANCH, INITIAL_INTITU_NM,HEADQUARTER_YN  ) VALUES (  ?, ?, ?, ?,  ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  SYSDATE, SYSDATE, 'Y', ?,  ?, ?,?  ) ";
                        psmt3 = con.prepareStatement(query2);
                        psmt3.setString(1, UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_biz_reg_no));
                        psmt3.setString(2, UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.nationality[0]));
                        psmt3.setString(3, UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_biz_nm));
                        psmt3.setString(4, UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_en_nm[0]));
                        psmt3.setString(5, this.um_adv_conra020c.commentcement_dt[0]);
                        psmt3.setString(6, this.um_adv_conra020c.establish_dt[0]);
                        psmt3.setString(7, UM_ADV_ConrB010c.isStrNull(" "));
                        psmt3.setString(8, UM_ADV_ConrB010c.isStrNull(" "));
                        psmt3.setString(9, UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.corp_reg_no[0]));
                        psmt3.setString(10, UM_ADV_ConrB010c.isStrNull("2"));
                        psmt3.setString(11, UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_cls_2[0]));
                        psmt3.setString(12, UM_ADV_ConrB010c.isStrNull("0000"));
                        if (" ".equals(this.um_adv_conra020c.capital[0]) || "".equals(this.um_adv_conra020c.capital[0]) || this.um_adv_conra020c.capital[0] == null) {
                            psmt3.setInt(13, 0);
                        }
                        else {
                            psmt3.setString(13, this.um_adv_conra020c.capital[0]);
                        }
                        psmt3.setString(14, this.um_adv_conra020c.employee_count_[0]);
                        psmt3.setString(15, UM_ADV_ConrB010c.isStrNull("0000-00"));
                        psmt3.setString(16, UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_zip_cd));
                        psmt3.setString(17, UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_area_cd));
                        psmt3.setString(18, UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_add));
                        psmt3.setString(19, UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_add2));
                        psmt3.setString(20, UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_phone));
                        psmt3.setString(21, UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_fax));
                        psmt3.setString(22, UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.homepage[0]));
                        psmt3.setString(23, UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.special_good_yn[0]));
                        psmt3.setString(24, UM_ADV_ConrB010c.isStrNull(" "));
                        psmt3.setString(25, UM_ADV_ConrB010c.isStrNull(jicheong));
                        psmt3.setString(26, UM_ADV_ConrB010c.isStrNull("조달청"));
                        psmt3.setString(27, "N");
                        psmt3.executeUpdate();
                        psmt3.clearParameters();
                        this.um_adv_conra020c.branch_serial_no[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_serial_no);
                        final int seq_num = UM_ADV_ConrB010c.isIntNull(dpArr2[m].branch_serial_no);
                        ctl.brReplacePutValues(dsource, this.um_adv_conra020c.branch_serial_no, this.지사일련번호, seq_num, 0, "I");
                        this.um_adv_conra020c.branch_biz_nm[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_biz_nm);
                        ctl.brReplacePutValues(dsource, this.um_adv_conra020c.branch_biz_nm, this.지사상호명, seq_num, 0, "I");
                        this.um_adv_conra020c.branch_zip_cd[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_zip_cd);
                        ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_zip_cd, this.지사우편번호, seq_num, 0, "I");
                        this.um_adv_conra020c.branch_ared_cd[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_area_cd);
                        ctl.brReplacePutValues(dsource, this.um_adv_conra020c.branch_ared_cd, this.지사지역코드, seq_num, 0, "I");
                        this.um_adv_conra020c.branch_add[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_add);
                        ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_add, this.지사주소, seq_num, 0, "I");
                        this.um_adv_conra020c.branch_add2[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_add2);
                        ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_add2, this.지사나머지주소, seq_num, 0, "I");
                        this.um_adv_conra020c.branch_phone[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_phone);
                        ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_phone, this.지사전화번호, seq_num, 0, "I");
                        this.um_adv_conra020c.branch_fax[0] = UM_ADV_ConrB010c.isStrNull(dpArr2[m].branch_fax);
                        ctl.brReplacePutValues1(dsource, this.um_adv_conra020c.branch_fax, this.지사FAX번호, seq_num, 0, "I");
                    }
                    aflag = false;
                }
            }
            if (ett5.length > 0) {
                for (int m = 0; m < ett5.length; ++m) {
                    for (int j2 = 0; j2 < dpCount2 - 1; ++j2) {
                        if (ett5[m].getJuminNo().equals(dpArr3[j2].identNo)) {
                            aflag = true;
                            if (!UM_ADV_ConrB010c.isStrNull((ett5[m].getJobPart() == null) ? "" : ett5[m].getJobPart()).equals(UM_ADV_ConrB010c.isStrNull(dpArr3[j2].depart))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett5[m].getDutyName() == null) ? "" : ett5[m].getDutyName()).equals(UM_ADV_ConrB010c.isStrNull(dpArr3[j2].position))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett5[m].getName() == null) ? "" : ett5[m].getName()).equals(UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agentNm))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett5[m].getTel() == null) ? "" : ett5[m].getTel()).equals(UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agent_phone))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett5[m].getFax() == null) ? "" : ett5[m].getFax()).equals(UM_ADV_ConrB010c.isStrNull(dpArr3[j2].FAX))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett5[m].getMail() == null) ? "" : ett5[m].getMail()).equals(UM_ADV_ConrB010c.isStrNull(dpArr3[j2].E_MAIL))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett5[m].getHandphone() == null) ? "" : ett5[m].getHandphone()).equals(UM_ADV_ConrB010c.isStrNull(dpArr3[j2].mobile))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett5[m].getIpchalYN() == null) ? "" : ett5[m].getIpchalYN()).equals(UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agent_yn))) {
                                bflag = true;
                            }
                            if (bflag) {
                                idquery1 = "UPDATE UM_BID_AGENT SET DEPART=?, POSITION=?, NM=?, PHONE_NO=?, FAX=?, EMAIL=?, MOBILE=?, UPDATE_DT=SYSDATE, BIDDING_AGENT_YN=? WHERE BIZ_REG_NO=? and IDENT_NO=?";
                                if (idpsmt1 != null) {
                                    try {
                                        idpsmt1.close();
                                    }
                                    catch (Exception ex13) {}
                                }
                                idpsmt1 = con.prepareStatement(idquery1);
                                idpsmt1.setString(1, UM_ADV_ConrB010c.isStrNull(dpArr3[j2].depart));
                                idpsmt1.setString(2, UM_ADV_ConrB010c.isStrNull(dpArr3[j2].position));
                                idpsmt1.setString(3, UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agentNm));
                                idpsmt1.setString(4, UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agent_phone));
                                idpsmt1.setString(5, UM_ADV_ConrB010c.isStrNull(dpArr3[j2].FAX));
                                idpsmt1.setString(6, UM_ADV_ConrB010c.isStrNull(dpArr3[j2].E_MAIL));
                                idpsmt1.setString(7, UM_ADV_ConrB010c.isStrNull(dpArr3[j2].mobile));
                                idpsmt1.setString(8, UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agent_yn));
                                idpsmt1.setString(9, UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                                idpsmt1.setString(10, UM_ADV_ConrB010c.isStrNull(ett5[m].getJuminNo()));
                                Log.debug("부서[" + j2 + "]" + UM_ADV_ConrB010c.isStrNull(dpArr3[j2].depart));
                                Log.debug("직책명" + UM_ADV_ConrB010c.isStrNull(dpArr3[j2].position));
                                Log.debug("성명" + UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agentNm));
                                Log.debug("입찰대리인전화번호" + UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agent_phone));
                                Log.debug("FAX" + UM_ADV_ConrB010c.isStrNull(dpArr3[j2].FAX));
                                Log.debug("E_MAIL" + UM_ADV_ConrB010c.isStrNull(dpArr3[j2].E_MAIL));
                                Log.debug("휴대폰" + UM_ADV_ConrB010c.isStrNull(dpArr3[j2].mobile));
                                Log.debug("입찰대리인확인여부" + UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agent_yn));
                                Log.debug("um_adv_conra020c.사업자등록번호[0]" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                                Log.debug("주민등록번호" + UM_ADV_ConrB010c.isStrNull(ett5[m].getJuminNo()));
                                idpsmt1.executeQuery();
                                bflag = false;
                                this.um_adv_conra020c.serial_no4[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[j2].serialNo4);
                                this.um_adv_conra020c.depart[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[j2].depart);
                                this.um_adv_conra020c.position[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[j2].position);
                                this.um_adv_conra020c.nm[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agentNm);
                                this.um_adv_conra020c.agent_phone[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agent_phone);
                                this.um_adv_conra020c.ident_no[0] = UM_ADV_ConrB010c.isStrNull(ett5[m].getJuminNo());
                                this.um_adv_conra020c.E_MAIL[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[j2].E_MAIL);
                                this.um_adv_conra020c.FAX[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[j2].FAX);
                                this.um_adv_conra020c.mobile[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[j2].mobile);
                                this.um_adv_conra020c.agent_yn[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[j2].agent_yn);
                                ctl.idReplacePutValues(dsource, this.um_adv_conra020c.serial_no4, this.일련번호4, j2, 0, "U");
                                ctl.idReplacePutValues(dsource, this.um_adv_conra020c.depart, this.부서, j2, 0, "U");
                                ctl.idReplacePutValues(dsource, this.um_adv_conra020c.position, this.직책명, j2, 0, "U");
                                ctl.idReplacePutValues(dsource, this.um_adv_conra020c.nm, this.성명, j2, 0, "U");
                                ctl.idReplacePutValues(dsource, this.um_adv_conra020c.agent_phone, this.입찰대리인전화번호, j2, 0, "U");
                                ctl.idReplacePutValues(dsource, this.um_adv_conra020c.ident_no, this.주민등록번호, j2, 0, "U");
                                ctl.idReplacePutValues(dsource, this.um_adv_conra020c.E_MAIL, this.E_MAIL, j2, 0, "U");
                                ctl.idReplacePutValues(dsource, this.um_adv_conra020c.FAX, this.FAX, j2, 0, "U");
                                ctl.idReplacePutValues(dsource, this.um_adv_conra020c.mobile, this.휴대폰, j2, 0, "U");
                                ctl.idReplacePutValues(dsource, this.um_adv_conra020c.agent_yn, this.입찰대리인확인여부, j2, 0, "U");
                            }
                        }
                    }
                    if (!aflag) {
                        idquery1 = "DELETE FROM UM_BID_AGENT WHERE BIZ_REG_NO=? and IDENT_NO=?";
                        if (idpsmt1 != null) {
                            try {
                                idpsmt1.close();
                            }
                            catch (Exception ex14) {}
                        }
                        idpsmt1 = con.prepareStatement(idquery1);
                        idpsmt1.setString(1, this.um_adv_conra020c.biz_reg_no[0]);
                        idpsmt1.setString(2, ett5[m].getJuminNo());
                        idpsmt1.executeQuery();
                        this.um_adv_conra020c.serial_no4[0] = Integer.toString(m + 1);
                        Log.debug("일련번호4Integer" + Integer.toString(m + 1));
                        ctl.idReplacePutValues(dsource, this.um_adv_conra020c.serial_no4, this.일련번호4, m, 0, "D");
                    }
                    aflag = false;
                }
            }
            if (dpCount2 - 1 > 0) {
                for (int m = 0; m < dpCount2 - 1; ++m) {
                    for (int j2 = 0; j2 < ett5.length; ++j2) {
                        if (dpArr3[m].identNo.equals(ett5[j2].getJuminNo())) {
                            aflag = true;
                        }
                    }
                    if (!aflag) {
                        idquery1 = " INSERT INTO UM_BID_AGENT (  BIZ_REG_NO, IDENT_NO, NM, DEPART, POSITION,  PHONE_NO, EMAIL, MOBILE, FAX, REG_DT, UPDATE_DT, BIDDING_AGENT_YN  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, ?, ?, SYSDATE, SYSDATE,?  ) ";
                        if (idpsmt1 != null) {
                            try {
                                idpsmt1.close();
                            }
                            catch (Exception ex15) {}
                        }
                        idpsmt1 = con.prepareStatement(idquery1);
                        idpsmt1.setString(1, UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                        idpsmt1.setString(2, UM_ADV_ConrB010c.isStrNull(dpArr3[m].identNo));
                        idpsmt1.setString(3, UM_ADV_ConrB010c.isStrNull(dpArr3[m].agentNm));
                        idpsmt1.setString(4, UM_ADV_ConrB010c.isStrNull(dpArr3[m].depart));
                        idpsmt1.setString(5, UM_ADV_ConrB010c.isStrNull(dpArr3[m].position));
                        idpsmt1.setString(6, UM_ADV_ConrB010c.isStrNull(dpArr3[m].agent_phone));
                        idpsmt1.setString(7, UM_ADV_ConrB010c.isStrNull(dpArr3[m].E_MAIL));
                        idpsmt1.setString(8, UM_ADV_ConrB010c.isStrNull(dpArr3[m].mobile));
                        idpsmt1.setString(9, UM_ADV_ConrB010c.isStrNull(dpArr3[m].FAX));
                        idpsmt1.setString(10, UM_ADV_ConrB010c.isStrNull(dpArr3[m].agent_yn));
                        Log.debug("um_adv_conra020c.사업자등록번호[0]" + UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                        Log.debug("주민등록번호" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].identNo));
                        Log.debug("성명" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].agentNm));
                        Log.debug("부서" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].depart));
                        Log.debug("직책명" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].position));
                        Log.debug("입찰대리인전화번호" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].agent_phone));
                        Log.debug("E_MAIL" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].E_MAIL));
                        Log.debug("휴대폰" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].mobile));
                        Log.debug("FAX" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].FAX));
                        Log.debug("입찰대리인확인여부" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].agent_yn));
                        idpsmt1.executeUpdate();
                        idpsmt1.clearParameters();
                        this.um_adv_conra020c.serial_no4[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[m].serialNo4);
                        Log.debug("일련번호4" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].serialNo4));
                        this.um_adv_conra020c.depart[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[m].depart);
                        this.um_adv_conra020c.position[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[m].position);
                        this.um_adv_conra020c.nm[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[m].agentNm);
                        this.um_adv_conra020c.agent_phone[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[m].agent_phone);
                        this.um_adv_conra020c.ident_no[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[m].identNo);
                        this.um_adv_conra020c.E_MAIL[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[m].E_MAIL);
                        this.um_adv_conra020c.FAX[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[m].FAX);
                        this.um_adv_conra020c.mobile[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[m].mobile);
                        this.um_adv_conra020c.agent_yn[0] = UM_ADV_ConrB010c.isStrNull(dpArr3[m].agent_yn);
                        ctl.idReplacePutValues(dsource, this.um_adv_conra020c.serial_no4, this.일련번호4, m, 0, "I");
                        ctl.idReplacePutValues(dsource, this.um_adv_conra020c.depart, this.부서, m, 0, "I");
                        ctl.idReplacePutValues(dsource, this.um_adv_conra020c.position, this.직책명, m, 0, "I");
                        ctl.idReplacePutValues(dsource, this.um_adv_conra020c.nm, this.성명, m, 0, "I");
                        ctl.idReplacePutValues(dsource, this.um_adv_conra020c.agent_phone, this.입찰대리인전화번호, m, 0, "I");
                        ctl.idReplacePutValues(dsource, this.um_adv_conra020c.ident_no, this.주민등록번호, m, 0, "I");
                        ctl.idReplacePutValues(dsource, this.um_adv_conra020c.E_MAIL, this.E_MAIL, m, 0, "I");
                        ctl.idReplacePutValues(dsource, this.um_adv_conra020c.FAX, this.FAX, m, 0, "I");
                        ctl.idReplacePutValues(dsource, this.um_adv_conra020c.mobile, this.휴대폰, m, 0, "I");
                        ctl.idReplacePutValues(dsource, this.um_adv_conra020c.agent_yn, this.입찰대리인확인여부, m, 0, "I");
                        Log.debug("um_adv_conra020c.일련번호4[0]" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].serialNo4));
                        Log.debug("um_adv_conra020c.부서[0]" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].depart));
                        Log.debug("um_adv_conra020c.직책명[0]" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].position));
                        Log.debug("um_adv_conra020c.성명[0]" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].agentNm));
                        Log.debug("um_adv_conra020c.입찰대리인전화번호[0]" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].agent_phone));
                        Log.debug("um_adv_conra020c.주민등록번호[0]" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].identNo));
                        Log.debug("um_adv_conra020c.E_MAIL[0]" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].E_MAIL));
                        Log.debug("um_adv_conra020c.FAX[0]" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].FAX));
                        Log.debug("um_adv_conra020c.휴대폰[0]" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].mobile));
                        Log.debug("um_adv_conra020c.입찰대리인확인여부[0]" + UM_ADV_ConrB010c.isStrNull(dpArr3[m].agent_yn));
                    }
                    aflag = false;
                }
            }
            if (ett2.length > 0) {
                for (int m = 0; m < ett2.length; ++m) {
                    for (int j2 = 0; j2 < msCount - 1; ++j2) {
                        if (ett2[m].getLicenseCode().equals(msArr[j2].licencse_cd)) {
                            aflag = true;
                            if (!UM_ADV_ConrB010c.isStrNull((ett2[m].getLicenseNo() == null) ? "" : ett2[m].getLicenseNo()).equals(UM_ADV_ConrB010c.isStrNull(msArr[j2].license_no))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett2[m].getLicenseBeginDate() == null) ? "" : (String.valueOf(ett2[m].getLicenseBeginDate().substring(0, 4)) + ett2[m].getLicenseBeginDate().substring(5, 7) + ett2[m].getLicenseBeginDate().substring(8, 10))).equals(UM_ADV_ConrB010c.isStrNull(msArr[j2].issued_dt))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett2[m].getLicenseEndDate() == null) ? "" : (String.valueOf(ett2[m].getLicenseEndDate().substring(0, 4)) + ett2[m].getLicenseEndDate().substring(5, 7) + ett2[m].getLicenseEndDate().substring(8, 10))).equals(UM_ADV_ConrB010c.isStrNull(msArr[j2].expiry_dt))) {
                                bflag = true;
                            }
                            if (ett2[m].getSigongAccount() != Long.parseLong(UM_ADV_ConrB010c.isStrNull(ComStr.replace(msArr[j2].const_abil_eval_amt, ",", "")).equals("") ? "0" : UM_ADV_ConrB010c.isStrNull(ComStr.replace(msArr[j2].const_abil_eval_amt, ",", "")))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett2[m].getPeonggaYear() == null) ? "0" : ett2[m].getPeonggaYear()).equals(UM_ADV_ConrB010c.isStrNull(msArr[j2].eval_std_year))) {
                                bflag = true;
                            }
                            if (!UM_ADV_ConrB010c.isStrNull((ett2[m].getDpLicenseYN() == null) ? "" : ett2[m].getDpLicenseYN()).equals(UM_ADV_ConrB010c.isStrNull(msArr[j2].mast_yn))) {
                                bflag = true;
                            }
                            if (bflag) {
                                msquery1 = "UPDATE UM_LICENSE_FACTS SET LICENSE_NO=?, LICENSE_ISSUED_DT=?, LICENSE_EXPIRY_DT=?, CONST_ABIL_EVAL_AMT=?, EVAL_STD_YEAR=?,ASSOC_UPDATE_DT=to_date(?,'yyyy-mm-dd hh24:mi:ss'), MAST_LICENSE_YN=?, UPDATE_DT=SYSDATE WHERE BIZ_REG_NO=? AND LICENSE_CD=?";
                                if (mspsmt1 != null) {
                                    try {
                                        mspsmt1.close();
                                    }
                                    catch (Exception ex16) {}
                                }
                                mspsmt1 = con.prepareStatement(msquery1);
                                mspsmt1.setString(1, UM_ADV_ConrB010c.isStrNull(msArr[j2].license_no));
                                mspsmt1.setString(2, msArr[j2].issued_dt);
                                mspsmt1.setString(3, msArr[j2].expiry_dt);
                                if (Sipyongvalue != null || !Sipyongvalue.equals("")) {
                                    mspsmt1.setString(4, Upjongcode.equals(UM_ADV_ConrB010c.isStrNull(msArr[j2].licencse_cd)) ? Sipyongvalue : ComStr.replace(msArr[j2].const_abil_eval_amt, ",", ""));
                                    mspsmt1.setString(5, Upjongcode.equals(UM_ADV_ConrB010c.isStrNull(msArr[j2].licencse_cd)) ? Sipyongyear : UM_ADV_ConrB010c.isStrNull(msArr[j2].eval_std_year));
                                    mspsmt1.setString(6, Upjongcode.equals(UM_ADV_ConrB010c.isStrNull(msArr[j2].licencse_cd)) ? Sipyongyear2 : "");
                                }
                                else {
                                    mspsmt1.setString(4, ComStr.replace(msArr[j2].const_abil_eval_amt, ",", ""));
                                    mspsmt1.setString(5, UM_ADV_ConrB010c.isStrNull(msArr[j2].eval_std_year));
                                    mspsmt1.setString(6, "");
                                }
                                mspsmt1.setString(7, UM_ADV_ConrB010c.isStrNull(msArr[j2].mast_yn));
                                mspsmt1.setString(8, UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                                mspsmt1.setString(9, ett2[m].getLicenseCode());
                                mspsmt1.executeUpdate();
                                bflag = false;
                                this.um_adv_conra020c.serial_no3[0] = UM_ADV_ConrB010c.isStrNull(msArr[j2].serial_3);
                                final int seq_num2 = UM_ADV_ConrB010c.isIntNull(msArr[j2].serial_3);
                                this.um_adv_conra020c.license_no[0] = UM_ADV_ConrB010c.isStrNull(msArr[j2].license_no);
                                this.um_adv_conra020c.license_cd[0] = UM_ADV_ConrB010c.isStrNull(ett2[m].getLicenseCode());
                                this.um_adv_conra020c.license_nm[0] = UM_ADV_ConrB010c.isStrNull(msArr[j2].license_nm);
                                this.um_adv_conra020c.license_issued_dt[0] = UM_ADV_ConrB010c.isStrNull(msArr[j2].issued_dt);
                                this.um_adv_conra020c.license_expiry_dt[0] = UM_ADV_ConrB010c.isStrNull(msArr[j2].expiry_dt);
                                if (Sipyongvalue != null || !Sipyongvalue.equals("")) {
                                    this.um_adv_conra020c.const_abil_eval_amt[0] = (Upjongcode.equals(UM_ADV_ConrB010c.isStrNull(msArr[j2].licencse_cd)) ? Sipyongvalue : ComStr.replace(msArr[j2].const_abil_eval_amt, ",", ""));
                                    this.um_adv_conra020c.eval_std_year[0] = (Upjongcode.equals(UM_ADV_ConrB010c.isStrNull(msArr[j2].licencse_cd)) ? Sipyongyear : UM_ADV_ConrB010c.isStrNull(msArr[j2].eval_std_year));
                                }
                                else {
                                    this.um_adv_conra020c.const_abil_eval_amt[0] = ComStr.replace(msArr[j2].const_abil_eval_amt, ",", "");
                                    this.um_adv_conra020c.eval_std_year[0] = UM_ADV_ConrB010c.isStrNull(msArr[j2].eval_std_year);
                                }
                                this.um_adv_conra020c.mast_license_yn[0] = UM_ADV_ConrB010c.isStrNull(msArr[j2].mast_yn);
                                this.um_adv_conra020c.mast_license_fm[0] = "";
                                ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.serial_no3, this.일련번호3, j2, 0, "U");
                                ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_no, this.면허번호, j2, 0, "U");
                                ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_cd, this.면허코드, j2, 0, "U");
                                ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_nm, this.면허코드명, j2, 0, "U");
                                ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_issued_dt, this.면허취득일자, j2, 0, "U");
                                ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_expiry_dt, this.면허만료일자, j2, 0, "U");
                                ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.const_abil_eval_amt, this.시공능력평가액, j2, 0, "U");
                                ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.eval_std_year, this.평가액기준년도, j2, 0, "U");
                                ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_yn, this.대표면허여부, j2, 0, "U");
                                ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_fm, this.대표면허포멧, j2, 0, "U");
                            }
                        }
                    }
                    if (!aflag) {
                        msquery1 = "DELETE FROM UM_LICENSE_FACTS WHERE BIZ_REG_NO=? AND LICENSE_CD=?";
                        if (mspsmt1 != null) {
                            try {
                                mspsmt1.close();
                            }
                            catch (Exception ex17) {}
                        }
                        mspsmt1 = con.prepareStatement(msquery1);
                        mspsmt1.setString(1, UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                        mspsmt1.setString(2, ett2[m].getLicenseCode());
                        mspsmt1.executeUpdate();
                        this.um_adv_conra020c.serial_no3[0] = Integer.toString(m + 1);
                        ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.serial_no3, this.일련번호3, m, 0, "D");
                    }
                    aflag = false;
                }
            }
            if (msCount - 1 > 0) {
                for (int m = 0; m < msCount - 1; ++m) {
                    for (int j2 = 0; j2 < ett2.length; ++j2) {
                        if (msArr[m].licencse_cd.equals(ett2[j2].getLicenseCode())) {
                            aflag = true;
                        }
                    }
                    if (!aflag) {
                        msquery1 = "INSERT INTO UM_LICENSE_FACTS (BIZ_REG_NO, LICENSE_CD, LICENSE_NO, LICENSE_ISSUED_DT, LICENSE_EXPIRY_DT, CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR, ASSOC_UPDATE_DT, MAST_LICENSE_YN, REG_DT, UPDATE_DT) VALUES (?, ?, ?, ?, ?, ?, ?,to_date(?,'yyyy-mm-dd hh24:mi:ss'), ?, SYSDATE, SYSDATE) ";
                        if (mspsmt1 != null) {
                            try {
                                mspsmt1.close();
                            }
                            catch (Exception ex18) {}
                        }
                        mspsmt1 = con.prepareStatement(msquery1);
                        mspsmt1.setString(1, UM_ADV_ConrB010c.isStrNull(this.um_adv_conra020c.biz_reg_no[0]));
                        mspsmt1.setString(2, UM_ADV_ConrB010c.isStrNull(msArr[m].licencse_cd));
                        mspsmt1.setString(3, UM_ADV_ConrB010c.isStrNull(msArr[m].license_no));
                        mspsmt1.setString(4, msArr[m].issued_dt);
                        mspsmt1.setString(5, msArr[m].expiry_dt);
                        if (Sipyongvalue != null || !Sipyongvalue.equals("")) {
                            mspsmt1.setString(6, Upjongcode.equals(UM_ADV_ConrB010c.isStrNull(msArr[m].licencse_cd)) ? Sipyongvalue : ComStr.replace(msArr[m].const_abil_eval_amt, ",", ""));
                            mspsmt1.setString(7, Upjongcode.equals(UM_ADV_ConrB010c.isStrNull(msArr[m].licencse_cd)) ? Sipyongyear : UM_ADV_ConrB010c.isStrNull(msArr[m].eval_std_year));
                            mspsmt1.setString(8, Upjongcode.equals(UM_ADV_ConrB010c.isStrNull(msArr[m].licencse_cd)) ? Sipyongyear2 : "");
                        }
                        else {
                            mspsmt1.setString(6, ComStr.replace(msArr[m].const_abil_eval_amt, ",", ""));
                            mspsmt1.setString(7, UM_ADV_ConrB010c.isStrNull(msArr[m].eval_std_year));
                            mspsmt1.setString(8, "");
                        }
                        mspsmt1.setString(9, UM_ADV_ConrB010c.isStrNull(msArr[m].mast_yn));
                        mspsmt1.executeUpdate();
                        this.um_adv_conra020c.serial_no3[0] = UM_ADV_ConrB010c.isStrNull(msArr[m].serial_3);
                        this.um_adv_conra020c.license_no[0] = UM_ADV_ConrB010c.isStrNull(msArr[m].license_no);
                        this.um_adv_conra020c.license_cd[0] = UM_ADV_ConrB010c.isStrNull(msArr[m].licencse_cd);
                        this.um_adv_conra020c.license_nm[0] = UM_ADV_ConrB010c.isStrNull(msArr[m].license_nm);
                        this.um_adv_conra020c.license_issued_dt[0] = UM_ADV_ConrB010c.isStrNull(msArr[m].issued_dt);
                        this.um_adv_conra020c.license_expiry_dt[0] = UM_ADV_ConrB010c.isStrNull(msArr[m].expiry_dt);
                        if (Sipyongvalue != null || !Sipyongvalue.equals("")) {
                            this.um_adv_conra020c.const_abil_eval_amt[0] = (Upjongcode.equals(UM_ADV_ConrB010c.isStrNull(msArr[m].licencse_cd)) ? Sipyongvalue : ComStr.replace(msArr[m].const_abil_eval_amt, ",", ""));
                            this.um_adv_conra020c.eval_std_year[0] = (Upjongcode.equals(UM_ADV_ConrB010c.isStrNull(msArr[m].licencse_cd)) ? Sipyongyear : UM_ADV_ConrB010c.isStrNull(msArr[m].eval_std_year));
                        }
                        else {
                            this.um_adv_conra020c.const_abil_eval_amt[0] = ComStr.replace(msArr[m].const_abil_eval_amt, ",", "");
                            this.um_adv_conra020c.eval_std_year[0] = UM_ADV_ConrB010c.isStrNull(msArr[m].eval_std_year);
                        }
                        this.um_adv_conra020c.mast_license_yn[0] = UM_ADV_ConrB010c.isStrNull(msArr[m].mast_yn);
                        this.um_adv_conra020c.mast_license_fm[0] = "";
                        ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.serial_no3, this.일련번호3, m, 0, "I");
                        ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_no, this.면허번호, m, 0, "I");
                        ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_cd, this.면허코드, m, 0, "I");
                        ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_nm, this.면허코드명, m, 0, "I");
                        ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_issued_dt, this.면허취득일자, m, 0, "I");
                        ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.license_expiry_dt, this.면허만료일자, m, 0, "I");
                        ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.const_abil_eval_amt, this.시공능력평가액, m, 0, "I");
                        ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.eval_std_year, this.평가액기준년도, m, 0, "I");
                        ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_yn, this.대표면허여부, m, 0, "I");
                        ctl.licenseReplacePutValues(dsource, this.um_adv_conra020c.mast_license_fm, this.대표면허포멧, m, 0, "I");
                    }
                    aflag = false;
                }
            }
            System.out.print("Đã cập nhật thông tin người đại diện - tiếp nhận");
        }
        catch (XMLParseException e) {
            e.printStackTrace();
        }
        catch (SAXException e2) {
            e2.printStackTrace();
        }
        catch (SQLException e3) {
            e3.printStackTrace();
        }
        catch (Exception e4) {
            e4.printStackTrace();
        }
    }
}
