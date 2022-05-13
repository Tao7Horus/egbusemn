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
import secu.lib.Secu;
import oracle.xml.parser.v2.DOMParser;

public class UM_RAV_IncuB010c
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
    private String[] 면허코드;
    private String[] 면허코드명;
    private String[] 면허번호;
    private String[] 면허취득일자;
    private String[] 면허만료일자;
    private String[] 시공능력평가액;
    private String[] 평가액기준년도;
    private String[] 대표면허여부;
    private String[] 면허명;
    private String[] 일련번호1;
    private String[] 대표자주민번호;
    private String[] 대표자명;
    private String[] 대표자메일주소;
    private String[] 대표대표자여부;
    private String[] 일련번호;
    private String[] 공장명;
    private String[] 공장우편번호;
    private String[] 공장주소;
    private String[] 공장나머지주소;
    private String[] 공장전화번호;
    private String[] 공장FAX번호;
    private String[] 일련번호4;
    private String[] 주민등록번호;
    private String[] 성명;
    private String[] 부서;
    private String[] 직책명;
    private String[] 입찰대리인전화번호;
    private String[] E_MAIL;
    private String[] 휴대폰;
    private String[] FAX;
    public String[] 일련번호2;
    public String[] 물품명;
    public String[] 물품분류번호;
    public String[] 최근3년간_매출액;
    public String[] 제조여부;
    public String[] 대표물품여부;
    public String[] 일련번호5;
    public String[] 물품명1;
    public String[] 물품분류번호1;
    public String[] 형식승인번호1;
    public String[] 형식승인기관1;
    public String[] 형식승인일1;
    public String[] 최근3년간_매출액1;
    public String[] 제조여부1;
    public String[] 대표물품여부1;
    
    public UM_RAV_IncuB010c() {
        this.xmlSourceMaster = "/devl/usemn/usemnapp/xml/xmlTemplate/xml2/GsacidA.xml";
        this.dxeHost = "XLNDB";
        this.dxePort = 1050;
        this.dxeStore = "usemn";
        this.XlnSourceMaster = String.valueOf(this.dxeStore) + ":/xml2/GsacidA.xml";
        this.dxeUser = "";
        this.dxePassword = "";
        this.parser = new DOMParser();
        this.일련번호3 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.면허코드 = new String[] { "License.Code", "Code.Content" };
        this.면허코드명 = new String[] { "License.Code", "Code.Name" };
        this.면허번호 = new String[] { "License.Number.Text", "Text.Content" };
        this.면허취득일자 = new String[] { "License.ValidityStart.Date", "DateTime.Content" };
        this.면허만료일자 = new String[] { "License.ValidityEnd.Date", "DateTime.Content" };
        this.시공능력평가액 = new String[] { "Construcation.AbliltyPar.Amount", "Amount.Content" };
        this.평가액기준년도 = new String[] { "ParRank.AmountStandardYear.Date", "DateTime.Content" };
        this.대표면허여부 = new String[] { "Representation.License.Indicator", "Indicator.Content" };
        this.면허명 = new String[] { "License.Name", "Text.Content" };
        this.일련번호1 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.대표자주민번호 = new String[] { "cc:Organization.CEO.Identifier", "Identifier.Content" };
        this.대표자명 = new String[] { "Organization.CEO.Name", "Text.Content" };
        this.대표자메일주소 = new String[] { "Email.Address.Text", "Text.Content" };
        this.대표대표자여부 = new String[] { "Representation.Ceo.Indicator", "Indicator.Content" };
        this.일련번호 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.공장명 = new String[] { "Factory.Name", "Text.Content" };
        this.공장우편번호 = new String[] { "cc:PostCode.Identifier", "Identifier.Content" };
        this.공장주소 = new String[] { "cc:Address.Line1.Text", "Text.Content" };
        this.공장나머지주소 = new String[] { "cc:Address.Line2.Text", "Text.Content" };
        this.공장전화번호 = new String[] { "cc:Telephone.Number.Text", "Text.Content" };
        this.공장FAX번호 = new String[] { "cc:Fax.Number.Text", "Text.Content" };
        this.일련번호4 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.주민등록번호 = new String[] { "cc:Person.Identifier", "Identifier.Content" };
        this.성명 = new String[] { "cc:Employee.Name", "Text.Content" };
        this.부서 = new String[] { "cc:Department.Name", "Text.Content" };
        this.직책명 = new String[] { "Employee.Title.Name", "Text.Content" };
        this.입찰대리인전화번호 = new String[] { "cc:Telephone.Number.Text", "Text.Content" };
        this.E_MAIL = new String[] { "cc:Email.Address.Text", "Text.Content" };
        this.휴대폰 = new String[] { "PCS.Number.Text", "Text.Content" };
        this.FAX = new String[] { "cc:Fax.Number.Text", "Text.Content" };
        this.일련번호2 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.물품명 = new String[] { "cc:Item.Name", "Text.Content" };
        this.물품분류번호 = new String[] { "cc:Item.ClassificationNumber.Identifier", "Identifier.Content" };
        this.최근3년간_매출액 = new String[] { "Sale.Recent.Amount", "Amount.Content" };
        this.제조여부 = new String[] { "Manufacturing.Indicator", "Indicator.Content" };
        this.대표물품여부 = new String[] { "Representation.Item.Indicator", "Indicator.Content" };
        this.일련번호5 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.물품명1 = new String[] { "cc:Item.Name", "Text.Content" };
        this.물품분류번호1 = new String[] { "cc:Item.ClassificationNumber.Identifier", "Identifier.Content" };
        this.형식승인번호1 = new String[] { "Form.Approval.Identifier", "Identifier.Content" };
        this.형식승인기관1 = new String[] { "Form.ApprovalOrg.Name", "Text.Content" };
        this.형식승인일1 = new String[] { "Form.Approval.Date", "DateTime.Content" };
        this.최근3년간_매출액1 = new String[] { "Sale.Recent.Amount", "Amount.Content" };
        this.제조여부1 = new String[] { "Manufacturing.Indicator", "Indicator.Content" };
        this.대표물품여부1 = new String[] { "Representation.Item.Indicator", "Indicator.Content" };
    }
    
    public String loadDB2XML(final String saupNo, final String userID) {
        final Secu secu = new Secu(20);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        Trx trx = null;
        String result = "";
        final UM_ADV_ConrA020c um_adv_conra020c = new UM_ADV_ConrA020c();
        try {
            System.setProperty("com.exln.dxe.adminhost", this.dxeHost);
            final StringReader _Message = null;
            this.parser.parse((Reader)_Message);
            final XMLDocument dMaster = this.parser.getDocument();
            _Message.close();
            String securi = "";
            securi = secu.getPemKmCertInfo(1);
            sql = new String("SELECT 사업자등록번호, 업체명,       우편코드,  본사주소,             \r\n\t     본사전화번호,   본사팩스번호,  자본금,   종업원수,   홈페이지    \r\n FROM   사용_생산업체                                               \r\n WHERE 사업자등록번호= ? ");
            trx = new Trx(this, "usemn");
            conn = trx.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                um_adv_conra020c.biz_reg_no[0] = rs.getString(1);
                um_adv_conra020c.biz_nm[0] = isStrNull(rs.getString(2));
                um_adv_conra020c.zip_cd[0] = isStrNull(rs.getString(3));
                um_adv_conra020c.addr[0] = isStrNull(rs.getString(4));
                um_adv_conra020c.addr_detail[0] = "";
                um_adv_conra020c.phone_no[0] = isStrNull(rs.getString(5));
                um_adv_conra020c.fax[0] = isStrNull(rs.getString(6));
                um_adv_conra020c.capital[0] = isStrNull(rs.getString(7));
                um_adv_conra020c.employee_count_[0] = isStrNull(rs.getString(8));
                um_adv_conra020c.homepage[0] = isStrNull(rs.getString(9));
                um_adv_conra020c.nationality[0] = "";
                um_adv_conra020c.biz_en_nm[0] = "";
                um_adv_conra020c.biz_en_sort_nm[0] = "";
                um_adv_conra020c.commentcement_dt[0] = "";
                um_adv_conra020c.establish_dt[0] = "";
                um_adv_conra020c.biz_cls[0] = "";
                um_adv_conra020c.product_cls[0] = "";
                um_adv_conra020c.mast_industry_cd_std[0] = "";
                um_adv_conra020c.corp_reg_no[0] = "";
                um_adv_conra020c.biz_cls_1[0] = "";
                um_adv_conra020c.biz_cls_2[0] = "";
                um_adv_conra020c.biz_cls_year[0] = "";
                um_adv_conra020c.lat_settle_dt[0] = "";
                um_adv_conra020c.area_cd[0] = "";
                um_adv_conra020c.special_good_yn[0] = "";
                um_adv_conra020c.cert[0] = securi;
                PutValues(dMaster, um_adv_conra020c.biz_reg_no);
                PutValues(dMaster, um_adv_conra020c.nationality);
                PutValues(dMaster, um_adv_conra020c.biz_nm);
                PutValues(dMaster, um_adv_conra020c.biz_en_nm);
                PutValues(dMaster, um_adv_conra020c.biz_en_sort_nm);
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
                PutValues(dMaster, um_adv_conra020c.addr);
                PutValues(dMaster, um_adv_conra020c.addr_detail);
                PutValues(dMaster, um_adv_conra020c.phone_no);
                PutValues(dMaster, um_adv_conra020c.fax);
                PutValues(dMaster, um_adv_conra020c.homepage);
                PutValues(dMaster, um_adv_conra020c.special_good_yn);
                PutValues(dMaster, um_adv_conra020c.cert);
            }
            sql = new String("SELECT 대표자명,  주민등록번호   FROM 사용_생산업체   \r\n WHERE 사업자등록번호= ? ");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            final String dpMail = "";
            final String dpYN = "";
            int i = 0;
            while (rs.next()) {
                um_adv_conra020c.serial_no_1[0] = Integer.toString(i + 1);
                um_adv_conra020c.repr_nm[0] = isStrNull(rs.getString(1));
                um_adv_conra020c.rerp_ident_no[0] = isStrNull(rs.getString(2));
                um_adv_conra020c.repr_mail[0] = isStrNull(dpMail);
                um_adv_conra020c.mast_repr_yn[0] = isStrNull(dpYN);
                dpPutValues(dMaster, um_adv_conra020c.serial_no_1, this.일련번호1, i, 1);
                dpPutValues(dMaster, um_adv_conra020c.repr_nm, this.대표자명, i, 0);
                dpPutValues(dMaster, um_adv_conra020c.rerp_ident_no, this.대표자주민번호, i, 0);
                dpPutValues(dMaster, um_adv_conra020c.repr_mail, this.대표자메일주소, i, 0);
                dpPutValues(dMaster, um_adv_conra020c.mast_repr_yn, this.대표대표자여부, i, 0);
                ++i;
            }
            sql = "  SELECT DISTINCT  a.물품분류번호, a.최근3년간_매출액, a.제조여부, a.대표물품여부, b.분류명    FROM 사용_조달품목 a, SYN_VIEW_물품분류매핑 b                                            WHERE  a.물품분류번호=b.물품분류                                                        AND    a.사업자등록번호= ? AND a.제조여부='N'  order by a.대표물품여부 desc ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            int r = 0;
            if (rs.next()) {
                do {
                    um_adv_conra020c.serial_no_2[0] = Integer.toString(r + 1);
                    um_adv_conra020c.goods_cls_no[0] = isStrNull(rs.getString(1));
                    um_adv_conra020c.income_3year[0] = isStrNull(rs.getString(2));
                    um_adv_conra020c.direct_product_yn[0] = isStrNull(rs.getString(3));
                    um_adv_conra020c.mast_goods_yn[0] = isStrNull(rs.getString(4));
                    um_adv_conra020c.goods_cls_nm[0] = isStrNull(rs.getString(5));
                    jpPutValues(dMaster, um_adv_conra020c.serial_no_2, this.일련번호2, r, 1);
                    jpPutValues(dMaster, um_adv_conra020c.goods_cls_nm, this.물품명, r, 0);
                    jpPutValues(dMaster, um_adv_conra020c.goods_cls_no, this.물품분류번호, r, 0);
                    jpPutValues(dMaster, um_adv_conra020c.income_3year, this.최근3년간_매출액, r, 0);
                    jpPutValues(dMaster, um_adv_conra020c.direct_product_yn, this.제조여부, r, 0);
                    jpPutValues(dMaster, um_adv_conra020c.mast_goods_yn, this.대표물품여부, r, 0);
                    ++r;
                } while (rs.next());
            }
            sql = "  SELECT DISTINCT  a.물품분류번호 , a.형식승인번호,    a.형식승인기관,                  a.형식승인일 , a.최근3년간_매출액 , a.제조여부, a.대표물품여부, b.분류명     FROM 사용_조달품목 a, SYN_VIEW_물품분류매핑 b                                  WHERE a.물품분류번호=b.물품분류       AND   a.사업자등록번호= ? AND a.제조여부='Y' order by a.대표물품여부 desc";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            int x = 0;
            if (rs.next()) {
                do {
                    um_adv_conra020c.serial_no5[0] = Integer.toString(r + 1);
                    um_adv_conra020c.goods_cls_no1[0] = isStrNull(rs.getString(1));
                    um_adv_conra020c.permit_no1[0] = isStrNull(rs.getString(2));
                    um_adv_conra020c.permit_institu1[0] = isStrNull(rs.getString(3));
                    um_adv_conra020c.permit_dt1[0] = isStrNull(rs.getString(4));
                    um_adv_conra020c.imcome_3year1[0] = isStrNull(rs.getString(5));
                    um_adv_conra020c.direct_product_yn1[0] = isStrNull(rs.getString(6));
                    um_adv_conra020c.mast_goods_yn1[0] = isStrNull(rs.getString(7));
                    um_adv_conra020c.goods_cls_nm1[0] = isStrNull(rs.getString(8));
                    maPutValues(dMaster, um_adv_conra020c.serial_no5, this.일련번호5, x, 1);
                    maPutValues(dMaster, um_adv_conra020c.goods_cls_no1, this.물품분류번호1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.permit_no1, this.형식승인번호1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.permit_institu1, this.형식승인기관1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.permit_dt1, this.형식승인일1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.imcome_3year1, this.최근3년간_매출액1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.direct_product_yn1, this.제조여부1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.mast_goods_yn1, this.대표물품여부1, x, 0);
                    maPutValues(dMaster, um_adv_conra020c.goods_cls_nm1, this.물품명1, x, 0);
                    ++x;
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
            Log.errors("UM_RAV_IncuB010c.loadDB2XML() SQLException : ");
            Log.errors("SQLException 발생사유 : " + se.toString() + se.getErrorCode() + se.getSQLState());
            Log.errors("SQL문장 : " + sql);
            Log.errors("-------------------------------------------------------------------------");
            result = "false11";
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_RAV_IncuB010c.loadDB2XML() Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
            result = "에러가 발생했습니다.";
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
    
    public static int isIntNull(final String value) {
        return (value == null) ? 0 : Integer.parseInt(value.trim());
    }
    
    public static String isStrNull(final String value) {
        return (value == null) ? "" : value.trim();
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
            Log.errors("UM_RAV_IncuB010c.PutValues() Exception : ");
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
            Log.errors("UM_RAV_IncuB010c.corpPutValues(1,2,3,4,5) Exception : ");
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
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
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
            Log.errors("UM_COV_ConiA010c.corpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
}
