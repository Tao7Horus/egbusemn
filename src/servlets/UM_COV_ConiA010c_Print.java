// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.SQLException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import org.xml.sax.SAXException;
import oracle.xml.parser.v2.XMLParseException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import common.Trx;
import secu.lib.Secu;
import java.io.FileInputStream;
import beans.NationalRefDAO;
import org.w3c.dom.Text;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import common.Log;
import oracle.xml.parser.v2.NSResolver;
import oracle.xml.parser.v2.XMLElement;
import oracle.xml.parser.v2.XMLDocument;
import oracle.xml.parser.v2.DOMParser;

public class UM_COV_ConiA010c_Print
{
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
    
    public UM_COV_ConiA010c_Print() {
        this.dxeHost = "XLNDB";
        this.dxePort = 1050;
        this.dxeStore = "usemn";
        this.XlnSourceMaster = String.valueOf(this.dxeStore) + ":/xml4/GsacidA.xml";
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
            Log.errors("UM_COV_ConiA010c.licensePutValues(1,2,3,4,5) Exception : ");
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
            Log.errors("UM_COV_ConiA010c.dpPutValues(1,2,3,4,5) Exception : ");
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
            Log.errors("UM_COV_ConiA010c.facPutValues(1,2,3,4,5) Exception : ");
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
            Log.errors("UM_COV_ConiA010c.idPutValues(1,2,3,4,5) Exception : ");
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
            Log.errors("UM_COV_ConiA010c.jpPutValues(1,2,3,4,5) Exception : ");
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
            Log.errors("UM_COV_ConiA010c.maPutValues(1,2,3,4,5) Exception : ");
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
            Log.errors("UM_COV_ConiA010c.maPutValues1(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static void license2PutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
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
                addEle = doc.createElement("Licen2Item");
                final Node ageNode = doc.selectNodes("/gb:Gsacid/Licen2List/Licen2Item", (NSResolver)xemt).item(index);
                ageNode.getParentNode().insertBefore(addEle, ageNode);
            }
            addEle = doc.createElement(nodeNameType[0]);
            tmpNodeList = doc.selectNodes("/gb:Gsacid/Licen2List/Licen2Item", (NSResolver)xemt);
            nextNode = tmpNodeList.item(findex).appendChild(addEle);
            addEle = doc.createElement(nodeNameType[1]);
            addText = doc.createTextNode(node[0]);
            addEle.appendChild(addText);
            nextNode.appendChild(addEle);
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_COV_ConiA010c.license2PutValues(1) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public String loadDB2XML(final String saupNo, final String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        final PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet rs = null;
        final ResultSet rs2 = null;
        ResultSet rs3 = null;
        String sql = "";
        final String Nsql = "";
        String Msql = "";
        Trx trx = null;
        String result = "";
        final UM_ADV_ConrA020c um_adv_conra020c = new UM_ADV_ConrA020c();
        final NationalRefDAO ctl = new NationalRefDAO();
        final DOMParser parser = new DOMParser();
        try {
            final InputStream is = new FileInputStream("/data1/AppData/apps/usemn/SU/UM_COJ_ConiA030i_Print.xml");
            parser.parse(is);
            final XMLDocument dMaster = parser.getDocument();
            final Secu secu = new Secu(20);
            String securi = "";
            securi = secu.getPemKmCertInfo(1);
            sql = new String("  SELECT BIZ_REG_NO, NATIONALITY, BIZ_NM, BIZ_EN_NM,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t\t\t\tTO_CHAR(COMMENCEMENT_DT,'DD/MM/YYYY') COMMENCEMENT_DT, TO_CHAR(ESTABLISH_DT,'DD/MM/YYYY') ESTABLISH_DT,\t\t\t\r\n\t\t\t\t\t\tBIZ_CLS, PRODUCT_CLS, MAST_INDUSTRY_CD_STD, CORP_REG_NO, BIZ_CLS1,\t\t\t\t\t\t\t\t\t\t\t            \r\n\t\t\t\t\t\tBIZ_CLS2, BIZ_CLS_YEAR, CAPITAL, EMPLOYEE_COUNT, LAST_SETTLE_DT, ZIP_CD,\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t\t\t\tAREA_CD, ADDR, DETAIL_ADDR, PHONE_NO, FAX, WEBSITE,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    \r\n\t\t\t\t\t\tSPECAIL_GOODS_YN, TO_CHAR(REG_VALID_DT,'DDMMYYYY') REG_VALID_DT,\t\t\t\t\t\t\t                        \r\n\t\t\t\t\t\tTO_CHAR(REG_DT,'DDMMYYYY') REG_DT, TO_CHAR(UPDATE_DT,'DDMMYYYY') UPDATE_DT,\t\t\t\t        \r\n\t\t\t\t\t\tREPR_BIZ_APPROVE_YN, MANAGER_ID, PERMIT_BRANCH, CONTR_RELA_YN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\tFROM  UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\r\n\t\t\tWHERE BIZ_REG_NO= ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ");
            trx = new Trx(this, "usemn");
            conn = trx.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, saupNo);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Msql = " SELECT CD_NM FROM SYN_PUB_CODE WHERE CD_CLS='GUJ'\t\t     AND CD='" + rs.getString("PERMIT_BRANCH") + "'\t\t\t\t\t\t\t\t";
                pstmt3 = conn.prepareStatement(Msql);
                rs3 = pstmt3.executeQuery();
                if (rs3.next()) {
                    um_adv_conra020c.permit_branch_nm[0] = rs3.getString(1);
                    PutValues(dMaster, um_adv_conra020c.permit_branch_nm);
                }
                um_adv_conra020c.biz_reg_no[0] = rs.getString("BIZ_REG_NO");
                um_adv_conra020c.biz_nm[0] = rs.getString("BIZ_NM");
                um_adv_conra020c.biz_en_nm[0] = rs.getString("BIZ_EN_NM");
                um_adv_conra020c.nationality[0] = rs.getString("NATIONALITY");
                um_adv_conra020c.commentcement_dt[0] = rs.getString("COMMENCEMENT_DT");
                um_adv_conra020c.establish_dt[0] = rs.getString("ESTABLISH_DT");
                um_adv_conra020c.biz_cls[0] = rs.getString("BIZ_CLS");
                um_adv_conra020c.product_cls[0] = rs.getString("PRODUCT_CLS");
                um_adv_conra020c.mast_industry_cd_std[0] = rs.getString("MAST_INDUSTRY_CD_STD");
                um_adv_conra020c.corp_reg_no[0] = rs.getString("CORP_REG_NO");
                um_adv_conra020c.biz_cls_1[0] = rs.getString("BIZ_CLS1");
                um_adv_conra020c.biz_cls_2[0] = rs.getString("BIZ_CLS2");
                um_adv_conra020c.biz_cls_year[0] = rs.getString("BIZ_CLS_YEAR");
                um_adv_conra020c.capital[0] = rs.getString("CAPITAL");
                um_adv_conra020c.employee_count_[0] = rs.getString("EMPLOYEE_COUNT");
                um_adv_conra020c.lat_settle_dt[0] = rs.getString("LAST_SETTLE_DT");
                um_adv_conra020c.zip_cd[0] = rs.getString("ZIP_CD");
                um_adv_conra020c.area_cd[0] = rs.getString("AREA_CD");
                um_adv_conra020c.area_nm[0] = rs.getString("ADDR");
                um_adv_conra020c.addr[0] = rs.getString("ADDR");
                um_adv_conra020c.addr_detail[0] = rs.getString("DETAIL_ADDR");
                um_adv_conra020c.phone_no[0] = rs.getString("PHONE_NO");
                um_adv_conra020c.fax[0] = rs.getString("FAX");
                um_adv_conra020c.homepage[0] = rs.getString("WEBSITE");
                um_adv_conra020c.special_good_yn[0] = rs.getString("SPECAIL_GOODS_YN");
                um_adv_conra020c.permit_branch[0] = rs.getString("PERMIT_BRANCH");
                um_adv_conra020c.cert[0] = securi;
                um_adv_conra020c.sender_id[0] = userID;
                um_adv_conra020c.contr_rela_yn[0] = rs.getString("CONTR_RELA_YN");
                PutValues(dMaster, um_adv_conra020c.biz_reg_no);
                PutValues(dMaster, um_adv_conra020c.biz_nm);
                PutValues(dMaster, um_adv_conra020c.nationality);
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
                PutValues(dMaster, um_adv_conra020c.sender_id);
                PutValues(dMaster, um_adv_conra020c.contr_rela_yn);
            }
            sql = " SELECT distinct  b.CD_NM2, a.STD_CLS_CD,   a.STD_CLS_NO,    TO_CHAR(a.ISSUED_DT,'DDMMYYYY') ISSUED_DT,  \r\n                   TO_CHAR(a.STD_CLS_EXPIRY_DT,'DDMMYYYY') STD_CLS_EXPIRY_DT,  a.CONST_ABIL_EVAL_AMT, a.EVAL_STD_YEAR,    \r\n                   a.MAST_STD_CLS_YN                                                                           \r\n  FROM  um_enter_std_cls a, SYN_PUB_CODE b                                                                     \r\n  WHERE a.STD_CLS_CD=b.CD                                                                                   \r\n  AND   b.CD_CLS='GZ8'                                                                                    \r\n  AND   a.BIZ_REG_NO= ?  order by a.MAST_STD_CLS_YN desc ";
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
            sql = "  SELECT REPR_NM, REPR_IDENT_NO, REPR_EMAIL, MAST_REPR_YN, REPR_MOBILE, REPR_CLS     FROM  UM_REPR                    Where BIZ_REG_NO= ?  order by MAST_REPR_YN desc ";
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
            sql = "  SELECT DISTINCT  IDENT_NO,NM,DEPART,POSITION, PHONE_NO,EMAIL,MOBILE,FAX ,BIDDING_AGENT_YN          FROM UM_BID_AGENT                                                            WHERE BIZ_REG_NO= ? ";
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
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final PrintWriter p = new PrintWriter(baos);
            dMaster.print(p);
            result = baos.toString();
            p.close();
            baos.close();
        }
        catch (XMLParseException e) {
            e.printStackTrace();
        }
        catch (SAXException e2) {
            e2.printStackTrace();
        }
        catch (Exception e3) {
            e3.printStackTrace();
        }
        return result;
    }
    
    public static void AreaPutValues(final XMLDocument doc, final String[] node, final String[] nodeNameType, final int index, final int first) {
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
            Log.errors("UM_COV_ConiA010c.dpPutValues(1,2,3,4,5) Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
}
