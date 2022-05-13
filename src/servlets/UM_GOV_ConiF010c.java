// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import org.w3c.dom.Text;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import oracle.xml.parser.v2.NSResolver;
import oracle.xml.parser.v2.XMLElement;
import oracle.xml.parser.v2.XMLDocument;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import common.Trx;
import java.io.Reader;
import oracle.xml.parser.v2.DOMParser;

public class UM_GOV_ConiF010c
{
    String xmlSourceMaster;
    String dxeHost;
    int dxePort;
    String dxeStore;
    String XlnSourceMaster;
    String dxeUser;
    String dxePassword;
    DOMParser parser;
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
    private String[] 일련번호1;
    private String[] 대표자주민번호;
    private String[] 대표자명;
    private String[] 대표자메일주소;
    private String[] 대표대표자여부;
    private String[] 대표자휴대폰;
    private String[] 대표자유형;
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
    
    public UM_GOV_ConiF010c() {
        this.xmlSourceMaster = "/devl/usemn/usemnapp/xml/xmlTemplate/xml4/GsacidC.xml";
        this.dxeHost = "XLNDB";
        this.dxePort = 1050;
        this.dxeStore = "usemn";
        this.XlnSourceMaster = String.valueOf(this.dxeStore) + ":/xml4/GsacidC.xml";
        this.dxeUser = "";
        this.dxePassword = "";
        this.parser = new DOMParser();
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
        this.일련번호1 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.대표자주민번호 = new String[] { "cc:Organization.CEO.Identifier", "Identifier.Content" };
        this.대표자명 = new String[] { "Organization.CEO.Name", "Text.Content" };
        this.대표자메일주소 = new String[] { "Email.Address.Text", "Text.Content" };
        this.대표대표자여부 = new String[] { "Representation.Ceo.Indicator", "Indicator.Content" };
        this.대표자휴대폰 = new String[] { "cc:PCS.Number.Text", "Text.Content" };
        this.대표자유형 = new String[] { "cc:CEO.Type.Code", "Code.Content" };
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
    }
    
    public String loadDB2XML(final String saupNo, final String userID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        String sql = "";
        String Nsql = "";
        String Msql = "";
        Trx trx = null;
        String result = "";
        final UM_ADV_ConrA020c um_adv_conra020c = new UM_ADV_ConrA020c();
        try {
            System.setProperty("com.exln.dxe.adminhost", this.dxeHost);
            final StringReader _Message = null;
            this.parser.parse((Reader)_Message);
            final XMLDocument dMaster = this.parser.getDocument();
            _Message.close();
            sql = new String(" SELECT 사업자등록번호, 국적, 상호명,  영문상호명,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t\t\t   TO_CHAR(개업일자,'YYYYMMDD') 개업일자, TO_CHAR(법인설립일자,'YYYYMMDD') 법인설립일자,  \r\n\t\t\t\t\t   업무구분, 제조구분, 대표업종코드_표준, 법인등록번호, 기업구분1,\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t\t\t   기업구분2, 기업구분해당년도, 자본금, 종업원수, 최근결산년월, 우편번호,\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t\t\t   지역코드, 주소, 나머지주소, 전화번호, FAX번호, 홈페이지,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t\t\t   특례해당여부, TO_CHAR(등록유효일자,'YYYYMMDD') 등록유효일자,\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t\t\t   TO_CHAR(등록일자,'YYYYMMDD') 등록일자, TO_CHAR(갱신일자,'YYYYMMDD') 갱신일자,\t\t\t\t\r\n\t\t\t\t\t   대표인증여부, 처리자ID, 승인지청, 청렴계약이행여부\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t  FROM  사용_조달업체마스터\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t  WHERE 사업자등록번호= ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            trx = new Trx(this, "usemn");
            conn = trx.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Nsql = " SELECT 코드명 FROM SYN_공동코드 WHERE 코드구분='J02'  AND 코드='" + rs.getString("국적") + "' ";
                pstmt2 = conn.prepareStatement(Nsql);
                rs2 = pstmt2.executeQuery();
                if (rs2.next()) {
                    um_adv_conra020c.nationality_nm[0] = "";
                    PutValues(dMaster, um_adv_conra020c.nationality_nm);
                    um_adv_conra020c.nationality[0] = "";
                    PutValues(dMaster, um_adv_conra020c.nationality);
                    um_adv_conra020c.nationality[0] = rs.getString("국적");
                    PutValues(dMaster, um_adv_conra020c.nationality);
                    um_adv_conra020c.nationality_nm[0] = rs2.getString(1);
                    PutValues(dMaster, um_adv_conra020c.nationality_nm);
                }
                Msql = " SELECT 코드명 FROM SYN_공동코드 WHERE 코드구분='GUJ'  AND 코드='" + rs.getString("승인지청") + "' ";
                pstmt3 = conn.prepareStatement(Msql);
                rs3 = pstmt3.executeQuery();
                if (rs3.next()) {
                    um_adv_conra020c.permit_branch_nm[0] = rs3.getString(1);
                    PutValues(dMaster, um_adv_conra020c.permit_branch_nm);
                }
                um_adv_conra020c.biz_reg_no[0] = rs.getString("사업자등록번호");
                um_adv_conra020c.biz_nm[0] = rs.getString("상호명");
                um_adv_conra020c.biz_en_nm[0] = rs.getString("영문상호명");
                um_adv_conra020c.commentcement_dt[0] = rs.getString("개업일자");
                um_adv_conra020c.establish_dt[0] = rs.getString("법인설립일자");
                um_adv_conra020c.biz_cls[0] = rs.getString("업무구분");
                um_adv_conra020c.product_cls[0] = rs.getString("제조구분");
                um_adv_conra020c.mast_industry_cd_std[0] = rs.getString("대표업종코드_표준");
                um_adv_conra020c.corp_reg_no[0] = rs.getString("법인등록번호");
                um_adv_conra020c.biz_cls_1[0] = rs.getString("기업구분1");
                um_adv_conra020c.biz_cls_2[0] = rs.getString("기업구분2");
                um_adv_conra020c.biz_cls_year[0] = rs.getString("기업구분해당년도");
                um_adv_conra020c.capital[0] = rs.getString("자본금");
                um_adv_conra020c.employee_count_[0] = rs.getString("종업원수");
                um_adv_conra020c.lat_settle_dt[0] = rs.getString("최근결산년월");
                um_adv_conra020c.zip_cd[0] = rs.getString("우편번호");
                um_adv_conra020c.area_cd[0] = rs.getString("지역코드");
                um_adv_conra020c.area_nm[0] = rs.getString("주소");
                um_adv_conra020c.addr[0] = rs.getString("주소");
                um_adv_conra020c.addr_detail[0] = rs.getString("나머지주소");
                um_adv_conra020c.phone_no[0] = rs.getString("전화번호");
                um_adv_conra020c.fax[0] = rs.getString("FAX번호");
                um_adv_conra020c.homepage[0] = rs.getString("홈페이지");
                um_adv_conra020c.special_good_yn[0] = rs.getString("특례해당여부");
                um_adv_conra020c.permit_branch[0] = rs.getString("승인지청");
                um_adv_conra020c.cert[0] = "";
                um_adv_conra020c.contr_rela_yn[0] = rs.getString("청렴계약이행여부");
                PutValues(dMaster, um_adv_conra020c.biz_reg_no);
                PutValues(dMaster, um_adv_conra020c.biz_nm);
                PutValues(dMaster, um_adv_conra020c.biz_en_nm);
                PutValues(dMaster, um_adv_conra020c.commentcement_dt);
                PutValues(dMaster, um_adv_conra020c.establish_dt);
                PutValues(dMaster, um_adv_conra020c.biz_cls);
                PutValues(dMaster, um_adv_conra020c.product_cls);
                PutValues(dMaster, um_adv_conra020c.mast_industry_cd_std);
                PutValues(dMaster, um_adv_conra020c.corp_reg_no);
                PutValues(dMaster, um_adv_conra020c.biz_cls_1);
                PutValues(dMaster, um_adv_conra020c.biz_cls_2);
                PutValues(dMaster, um_adv_conra020c.biz_cls_year);
                PutValues(dMaster, um_adv_conra020c.capital);
                PutValues(dMaster, um_adv_conra020c.employee_count_);
                PutValues(dMaster, um_adv_conra020c.lat_settle_dt);
                PutValues(dMaster, um_adv_conra020c.zip_cd);
                PutValues(dMaster, um_adv_conra020c.area_cd);
                PutValues(dMaster, um_adv_conra020c.area_nm);
                PutValues(dMaster, um_adv_conra020c.addr);
                PutValues(dMaster, um_adv_conra020c.addr_detail);
                PutValues(dMaster, um_adv_conra020c.phone_no);
                PutValues(dMaster, um_adv_conra020c.fax);
                PutValues(dMaster, um_adv_conra020c.homepage);
                PutValues(dMaster, um_adv_conra020c.special_good_yn);
                PutValues(dMaster, um_adv_conra020c.permit_branch);
                PutValues(dMaster, um_adv_conra020c.cert);
                PutValues(dMaster, um_adv_conra020c.contr_rela_yn);
            }
            sql = " SELECT distinct  b.코드명, a.면허코드,   a.면허번호,    TO_CHAR(a.면허취득일자,'YYYYMMDD') 면허취득일자,  \r\n                   TO_CHAR(a.면허만료일자,'YYYYMMDD') 면허만료일자,  a.시공능력평가액, a.평가액기준년도,\t\r\n                   a.대표면허여부\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n  FROM  사용_면허사항 a, SYN_공동코드 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n  WHERE a.면허코드=b.코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n  AND   b.코드구분='GU9'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n  AND   a.사업자등록번호= ?  order by a.대표면허여부 desc ";
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex2) {}
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                um_adv_conra020c.serial_no3[0] = Integer.toString(i + 1);
                um_adv_conra020c.license_nm[0] = isStrNull(rs.getString(1));
                um_adv_conra020c.license_cd[0] = isStrNull(rs.getString(2));
                um_adv_conra020c.license_no[0] = isStrNull(rs.getString(3));
                um_adv_conra020c.license_issued_dt[0] = isStrNull(rs.getString(4));
                um_adv_conra020c.license_expiry_dt[0] = isStrNull(rs.getString(5));
                um_adv_conra020c.const_abil_eval_amt[0] = isStrNull(rs.getString(6));
                um_adv_conra020c.eval_std_year[0] = isStrNull(rs.getString(7));
                um_adv_conra020c.mast_license_yn[0] = isStrNull(rs.getString(8));
                um_adv_conra020c.mast_license_fm[0] = "";
                licensePutValues(dMaster, um_adv_conra020c.serial_no3, this.일련번호3, i, 1);
                licensePutValues(dMaster, um_adv_conra020c.license_no, this.면허번호, i, 0);
                licensePutValues(dMaster, um_adv_conra020c.license_cd, this.면허코드, i, 0);
                licensePutValues(dMaster, um_adv_conra020c.license_nm, this.면허코드명, i, 0);
                licensePutValues(dMaster, um_adv_conra020c.license_issued_dt, this.면허취득일자, i, 0);
                licensePutValues(dMaster, um_adv_conra020c.license_expiry_dt, this.면허만료일자, i, 0);
                licensePutValues(dMaster, um_adv_conra020c.const_abil_eval_amt, this.시공능력평가액, i, 0);
                licensePutValues(dMaster, um_adv_conra020c.eval_std_year, this.평가액기준년도, i, 0);
                licensePutValues(dMaster, um_adv_conra020c.mast_license_yn, this.대표면허여부, i, 0);
                licensePutValues(dMaster, um_adv_conra020c.mast_license_fm, this.대표면허포멧, i, 0);
                ++i;
            }
            sql = "  SELECT 대표자명, 대표자주민번호, 대표자메일주소, 대표대표자여부, 대표자휴대폰, 대표자유형     FROM  사용_대표자                    Where 사업자등록번호= ?  order by 대표대표자여부 desc ";
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex3) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex4) {}
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            int j = 0;
            while (rs.next()) {
                um_adv_conra020c.serial_no_1[0] = Integer.toString(j + 1);
                um_adv_conra020c.repr_nm[0] = isStrNull(rs.getString(1));
                um_adv_conra020c.rerp_ident_no[0] = isStrNull(rs.getString(2));
                um_adv_conra020c.repr_mail[0] = isStrNull(rs.getString(3));
                um_adv_conra020c.mast_repr_yn[0] = isStrNull(rs.getString(4));
                um_adv_conra020c.repr_mobile[0] = isStrNull(rs.getString(5));
                um_adv_conra020c.repr_cls[0] = isStrNull(rs.getString(6));
                dpPutValues(dMaster, um_adv_conra020c.serial_no_1, this.일련번호1, j, 1);
                dpPutValues(dMaster, um_adv_conra020c.repr_nm, this.대표자명, j, 0);
                dpPutValues(dMaster, um_adv_conra020c.rerp_ident_no, this.대표자주민번호, j, 0);
                dpPutValues(dMaster, um_adv_conra020c.repr_mail, this.대표자메일주소, j, 0);
                dpPutValues(dMaster, um_adv_conra020c.mast_repr_yn, this.대표대표자여부, j, 0);
                dpPutValues(dMaster, um_adv_conra020c.repr_mobile, this.대표자휴대폰, j, 0);
                dpPutValues(dMaster, um_adv_conra020c.repr_cls, this.대표자유형, j, 0);
                ++j;
            }
            sql = "SELECT DISTINCT  일련번호, 공장명, 공장우편번호, 공장주소,                          공장나머지주소, 공장전화번호,공장FAX번호,                          공장임대여부, to_char(공장임대시작일자, 'yyyymmdd'), to_char(공장임대종료일자, 'yyyymmdd')    FROM  사용_공장정보                                             WHERE 사업자등록번호= ? ";
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex5) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex6) {}
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            int k = 0;
            if (rs.next()) {
                do {
                    um_adv_conra020c.serial_no[0] = Integer.toString(k + 1);
                    um_adv_conra020c.fac_nm[0] = isStrNull(rs.getString(2));
                    um_adv_conra020c.fac_zip_cd[0] = isStrNull(rs.getString(3));
                    um_adv_conra020c.fac_add[0] = isStrNull(rs.getString(4));
                    um_adv_conra020c.fac_add2[0] = isStrNull(rs.getString(5));
                    um_adv_conra020c.fac_phone[0] = isStrNull(rs.getString(6));
                    um_adv_conra020c.fac_fax[0] = isStrNull(rs.getString(7));
                    um_adv_conra020c.fac_rent_yn[0] = isStrNull(rs.getString(8));
                    um_adv_conra020c.fac_rent_start_dt[0] = isStrNull(rs.getString(9));
                    um_adv_conra020c.fac_rent_end_dt[0] = isStrNull(rs.getString(10));
                    facPutValues(dMaster, um_adv_conra020c.serial_no, this.일련번호, k, 1);
                    facPutValues(dMaster, um_adv_conra020c.fac_nm, this.공장명, k, 0);
                    facPutValues(dMaster, um_adv_conra020c.fac_zip_cd, this.공장우편번호, k, 0);
                    facPutValues(dMaster, um_adv_conra020c.fac_add, this.공장주소, k, 0);
                    facPutValues(dMaster, um_adv_conra020c.fac_add2, this.공장나머지주소, k, 0);
                    facPutValues(dMaster, um_adv_conra020c.fac_phone, this.공장전화번호, k, 0);
                    facPutValues(dMaster, um_adv_conra020c.fac_fax, this.공장FAX번호, k, 0);
                    facPutValues(dMaster, um_adv_conra020c.fac_rent_yn, this.공장임대여부, k, 0);
                    facPutValues(dMaster, um_adv_conra020c.fac_rent_start_dt, this.공장임대시작일자, k, 0);
                    facPutValues(dMaster, um_adv_conra020c.fac_rent_end_dt, this.공장임대종료일자, k, 0);
                    ++k;
                } while (rs.next());
            }
            sql = "  SELECT DISTINCT  주민등록번호,성명,부서,직책명, 전화번호,E_MAIL,휴대폰,FAX, 입찰대리인확인여부           FROM 사용_입찰대리인 \t\t                                                    WHERE 사업자등록번호= ? ";
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex7) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex8) {}
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            int t = 0;
            if (rs.next()) {
                do {
                    um_adv_conra020c.serial_no4[0] = Integer.toString(t + 1);
                    um_adv_conra020c.ident_no[0] = isStrNull(rs.getString(1));
                    um_adv_conra020c.nm[0] = isStrNull(rs.getString(2));
                    um_adv_conra020c.depart[0] = isStrNull(rs.getString(3));
                    um_adv_conra020c.position[0] = isStrNull(rs.getString(4));
                    um_adv_conra020c.agent_phone[0] = isStrNull(rs.getString(5));
                    um_adv_conra020c.E_MAIL[0] = isStrNull(rs.getString(6));
                    um_adv_conra020c.mobile[0] = isStrNull(rs.getString(7));
                    um_adv_conra020c.FAX[0] = isStrNull(rs.getString(8));
                    um_adv_conra020c.agent_yn[0] = isStrNull(rs.getString(9));
                    idPutValues(dMaster, um_adv_conra020c.serial_no4, this.일련번호4, t, 1);
                    idPutValues(dMaster, um_adv_conra020c.depart, this.부서, t, 0);
                    idPutValues(dMaster, um_adv_conra020c.nm, this.성명, t, 0);
                    idPutValues(dMaster, um_adv_conra020c.agent_phone, this.입찰대리인전화번호, t, 0);
                    idPutValues(dMaster, um_adv_conra020c.FAX, this.FAX, t, 0);
                    idPutValues(dMaster, um_adv_conra020c.E_MAIL, this.E_MAIL, t, 0);
                    idPutValues(dMaster, um_adv_conra020c.position, this.직책명, t, 0);
                    idPutValues(dMaster, um_adv_conra020c.ident_no, this.주민등록번호, t, 0);
                    idPutValues(dMaster, um_adv_conra020c.mobile, this.휴대폰, t, 0);
                    idPutValues(dMaster, um_adv_conra020c.agent_yn, this.입찰대리인확인여부, t, 0);
                    ++t;
                } while (rs.next());
            }
            sql = "  SELECT  /*+ ordered  use_nl(a b) */\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   DISTINCT   b.분류명, a.물품분류번호, a.최근3년간_매출액, a.제조여부, a.대표물품여부\t\t  FROM 사용_조달품목 a, SYN_VIEW_물품분류매핑 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE  a.물품분류번호=b.물품분류\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  AND    a.사업자등록번호= ? AND a.제조여부='N'  order by a.대표물품여부 desc\t\t\t\t\t\t\t";
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex9) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex10) {}
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            int r = 0;
            if (rs.next()) {
                do {
                    um_adv_conra020c.serial_no_2[0] = Integer.toString(r + 1);
                    um_adv_conra020c.goods_cls_nm[0] = isStrNull(rs.getString(1));
                    um_adv_conra020c.add[0] = "";
                    um_adv_conra020c.goods_cls_no[0] = isStrNull(rs.getString(2));
                    um_adv_conra020c.income_3year[0] = isStrNull(rs.getString(3));
                    um_adv_conra020c.direct_product_yn[0] = isStrNull(rs.getString(4));
                    um_adv_conra020c.direct_product_fm[0] = "";
                    um_adv_conra020c.mast_goods_yn[0] = isStrNull(rs.getString(5));
                    um_adv_conra020c.mast_goods_fm[0] = "";
                    jpPutValues(dMaster, um_adv_conra020c.serial_no_2, this.일련번호2, r, 1);
                    jpPutValues(dMaster, um_adv_conra020c.goods_cls_nm, this.물품명, r, 0);
                    jpPutValues(dMaster, um_adv_conra020c.add, this.추가, r, 0);
                    jpPutValues(dMaster, um_adv_conra020c.goods_cls_no, this.물품분류번호, r, 0);
                    jpPutValues(dMaster, um_adv_conra020c.income_3year, this.최근3년간_매출액, r, 0);
                    jpPutValues(dMaster, um_adv_conra020c.direct_product_yn, this.제조여부, r, 0);
                    jpPutValues(dMaster, um_adv_conra020c.direct_product_fm, this.제조포멧, r, 0);
                    jpPutValues(dMaster, um_adv_conra020c.mast_goods_yn, this.대표물품여부, r, 0);
                    jpPutValues(dMaster, um_adv_conra020c.mast_goods_fm, this.대표물품포멧, r, 0);
                    ++r;
                } while (rs.next());
            }
            sql = "  SELECT /*+ ordered  use_nl(a b) */\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t                DISTINCT   b.분류명, a.물품분류번호 , a.최근3년간_매출액 , a.형식승인번호,  a.형식승인기관,\t\t\t\t\t                TO_CHAR(a.형식승인일,'YYYYMMDD') 형식승인일 ,  a.제조여부, a.대표물품여부,\t\t\t\t\t\t\t\t\t\t                a.직접생산증명서류, TO_CHAR(a.유효기간시작일자,'YYYYMMDD') 유효기간시작일자,\t\t\t\t\t\t\t\t\t   \t\t\t  TO_CHAR(a.유효기간종료일자,'YYYYMMDD') 유효기간종료일자, a.표준산업분류코드, a.발급기관, a.증서명 \t      FROM 사용_조달품목 a, SYN_VIEW_물품분류매핑 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      WHERE a.물품분류번호=b.물품분류\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      AND   a.사업자등록번호= ? AND a.제조여부='Y' order by a.대표물품여부 desc\t\t\t\t\t\t\t\t\t\t\t\t\t   ";
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex11) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex12) {}
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            int x = 0;
            if (rs.next()) {
                do {
                    um_adv_conra020c.serial_no5[0] = Integer.toString(r + 1);
                    um_adv_conra020c.goods_cls_nm1[0] = isStrNull(rs.getString(1));
                    um_adv_conra020c.add1[0] = "";
                    um_adv_conra020c.goods_cls_no1[0] = isStrNull(rs.getString(2));
                    um_adv_conra020c.imcome_3year1[0] = isStrNull(rs.getString(3));
                    um_adv_conra020c.permit_no1[0] = isStrNull(rs.getString(4));
                    um_adv_conra020c.permit_institu1[0] = isStrNull(rs.getString(5));
                    um_adv_conra020c.permit_dt1[0] = isStrNull(rs.getString(6));
                    um_adv_conra020c.direct_product_yn1[0] = isStrNull(rs.getString(7));
                    um_adv_conra020c.direct_product_fm1[0] = "";
                    um_adv_conra020c.mast_goods_yn1[0] = isStrNull(rs.getString(8));
                    um_adv_conra020c.mast_goods_fm1[0] = "";
                    um_adv_conra020c.direct_product_doc[0] = isStrNull(rs.getString(9));
                    um_adv_conra020c.avail_period_start_dt[0] = isStrNull(rs.getString(10));
                    um_adv_conra020c.avail_period_end_dt[0] = isStrNull(rs.getString(11));
                    um_adv_conra020c.industry_cls_cd[0] = isStrNull(rs.getString(12));
                    um_adv_conra020c.issua_institu[0] = isStrNull(rs.getString(13));
                    um_adv_conra020c.doc_nm1[0] = isStrNull(rs.getString(14));
                    maPutValues(dMaster, um_adv_conra020c.serial_no5, this.일련번호5, x, 1);
                    maPutValues(dMaster, um_adv_conra020c.goods_cls_nm1, this.물품명1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.goods_cls_no1, this.물품분류번호1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.imcome_3year1, this.최근3년간_매출액1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.permit_no1, this.형식승인번호1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.permit_institu1, this.형식승인기관1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.permit_dt1, this.형식승인일1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.direct_product_yn1, this.제조여부1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.direct_product_fm1, this.제조포멧1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.mast_goods_yn1, this.대표물품여부1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.mast_goods_fm1, this.대표물품포멧1, x, 0);
                    maPutValues1(dMaster, um_adv_conra020c.direct_product_doc, this.직접생산증명서류, x, 0);
                    maPutValues1(dMaster, um_adv_conra020c.avail_period_start_dt, this.유효기간시작일자, x, 0);
                    maPutValues1(dMaster, um_adv_conra020c.avail_period_end_dt, this.유효기간종료일자, x, 0);
                    maPutValues1(dMaster, um_adv_conra020c.industry_cls_cd, this.표준산업분류코드, x, 0);
                    maPutValues1(dMaster, um_adv_conra020c.issua_institu, this.발급기관, x, 0);
                    maPutValues1(dMaster, um_adv_conra020c.doc_nm1, this.증서명, x, 0);
                    ++x;
                } while (rs.next());
            }
            sql = new String("  SELECT a.사업자등록번호, 상호명, 우편번호, 지역코드, 주소, 나머지주소, 전화번호, FAX번호,\t\t\t\r\n\t\t\t\t\t    대표자명, 대표자주민번호, DECODE(c.상태구분,'07','Y') 상태구분\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t  FROM  사용_조달업체마스터 a, 사용_대표자 b, 사용_업체상태 c\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t  WHERE a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t  AND a.사업자등록번호 = c.사업자등록번호(+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t  AND c.상태구분(+) = '07'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t  AND b.대표대표자여부 = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t  AND a.법인등록번호 IN (  SELECT 법인등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t\t\t\t\t\t\t\t\t       FROM 사용_조달업체마스터\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t\t\t\t\t\t\t\t\t        WHERE 사업자등록번호  =?)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t  AND 본사구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t  ORDER BY a.사업자등록번호 ASC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex13) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex14) {}
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            int rr = 0;
            if (rs.next()) {
                do {
                    um_adv_conra020c.branch_serial_no[0] = Integer.toString(rr + 1);
                    um_adv_conra020c.branch_biz_reg_no[0] = isStrNull(rs.getString(1));
                    um_adv_conra020c.branch_biz_nm[0] = isStrNull(rs.getString(2));
                    um_adv_conra020c.branch_zip_cd[0] = isStrNull(rs.getString(3));
                    um_adv_conra020c.branch_ared_cd[0] = isStrNull(rs.getString(4));
                    um_adv_conra020c.branch_add[0] = isStrNull(rs.getString(5));
                    um_adv_conra020c.branch_add2[0] = isStrNull(rs.getString(6));
                    um_adv_conra020c.branch_phone[0] = isStrNull(rs.getString(7));
                    um_adv_conra020c.branch_fax[0] = isStrNull(rs.getString(8));
                    um_adv_conra020c.branch_rept_nm[0] = isStrNull(rs.getString(9));
                    um_adv_conra020c.branch_repr_ident_no[0] = isStrNull(rs.getString(10));
                    um_adv_conra020c.branch_regist_yn[0] = isStrNull(rs.getString(11));
                    brPutValues(dMaster, um_adv_conra020c.branch_serial_no, this.지사일련번호, rr, 1);
                    brPutValues(dMaster, um_adv_conra020c.branch_biz_reg_no, this.지사사업자등록번호, rr, 0);
                    brPutValues(dMaster, um_adv_conra020c.branch_biz_nm, this.지사상호명, rr, 0);
                    brPutValues1(dMaster, um_adv_conra020c.branch_zip_cd, this.지사우편번호, rr, 0);
                    brPutValues(dMaster, um_adv_conra020c.branch_ared_cd, this.지사지역코드, rr, 0);
                    brPutValues1(dMaster, um_adv_conra020c.branch_add, this.지사주소, rr, 0);
                    brPutValues1(dMaster, um_adv_conra020c.branch_add2, this.지사나머지주소, rr, 0);
                    brPutValues1(dMaster, um_adv_conra020c.branch_phone, this.지사전화번호, rr, 0);
                    brPutValues1(dMaster, um_adv_conra020c.branch_fax, this.지사FAX번호, rr, 0);
                    brPutValues1(dMaster, um_adv_conra020c.branch_rept_nm, this.지사대표자명, rr, 0);
                    brPutValues1(dMaster, um_adv_conra020c.branch_repr_ident_no, this.지사대표자주민번호, rr, 0);
                    brPutValues(dMaster, um_adv_conra020c.branch_regist_yn, this.지사등록취소여부, rr, 0);
                    ++rr;
                } while (rs.next());
            }
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final PrintWriter p = new PrintWriter(baos);
            dMaster.print(p);
            result = baos.toString();
            p.close();
            baos.close();
            result = String.valueOf(result.substring(0, result.indexOf("'UTF-8'"))) + "'euc-kr'" + result.substring(result.indexOf("'UTF-8'") + 7, result.length());
        }
        catch (SQLException se) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_GOV_ConiF010c.loadDB2XML() SQLException : ");
            Log.errors("SQLException 발생사유 : " + se.toString() + se.getErrorCode() + se.getSQLState());
            Log.errors("SQL문장 : " + sql);
            Log.errors("-------------------------------------------------------------------------");
            result = "서버에 문제가 생겨 조달업체변경신청을 할 수 없습니다. 잠시 후 다시 시도하시기 바랍니다.!";
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_GOV_ConiF010c.loadDB2XML() Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
            result = "서버에 문제가 생겨 조달업체변경신청을 할 수 없습니다. 잠시 후 다시 시도하기 바랍니다.!!!";
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception e2) {
                    result = "false3";
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception e2) {
                    result = "false4";
                }
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception e2) {
                    result = "false3";
                }
            }
            if (pstmt2 != null) {
                try {
                    pstmt2.close();
                }
                catch (Exception e2) {
                    result = "false4";
                }
            }
            if (rs3 != null) {
                try {
                    rs3.close();
                }
                catch (Exception e2) {
                    result = "false3";
                }
            }
            if (pstmt3 != null) {
                try {
                    pstmt3.close();
                }
                catch (Exception e2) {
                    result = "false4";
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (Exception e2) {
                    result = "false5";
                }
            }
            trx.close();
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception e2) {
                result = "false3";
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception e2) {
                result = "false4";
            }
        }
        if (rs2 != null) {
            try {
                rs2.close();
            }
            catch (Exception e2) {
                result = "false3";
            }
        }
        if (pstmt2 != null) {
            try {
                pstmt2.close();
            }
            catch (Exception e2) {
                result = "false4";
            }
        }
        if (rs3 != null) {
            try {
                rs3.close();
            }
            catch (Exception e2) {
                result = "false3";
            }
        }
        if (pstmt3 != null) {
            try {
                pstmt3.close();
            }
            catch (Exception e2) {
                result = "false4";
            }
        }
        if (conn != null) {
            try {
                conn.close();
            }
            catch (Exception e2) {
                result = "false5";
            }
        }
        trx.close();
        return result;
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
            Log.errors("UM_GOV_ConiF010c.PutValues() Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static int isIntNull(final String value) {
        return (value == null) ? 0 : Integer.parseInt(value.trim());
    }
    
    public static String isStrNull(final String value) {
        return (value == null) ? " " : value.trim();
    }
    
    public static void licensePutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
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
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_GOV_ConiF010c.licensePutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void dpPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
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
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_GOV_ConiF010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void facPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
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
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_GOV_ConiF010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void idPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
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
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_GOV_ConiF010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void jpPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
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
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_GOV_ConiF010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void brPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
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
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.brPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("Exception 발생사유 : " + nodeNameType[0]);
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void brPutValues1(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
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
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.brPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("Exception 발생사유 : " + nodeNameType[0]);
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void maPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
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
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_GOV_ConiF010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void maPutValues1(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
        try {
            XMLElement xemt = null;
            xemt = (XMLElement)doc.getDocumentElement();
            NodeList tmpNodeList = null;
            Node nextNode = null;
            Element addEle = null;
            Text addText = null;
            int findex = 0;
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
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_GOV_ConiF010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
}
