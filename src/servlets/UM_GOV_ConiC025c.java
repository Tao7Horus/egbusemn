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
import java.sql.SQLException;
import common.Log;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.ByteArrayOutputStream;
import common.ComStr;
import java.io.Reader;
import oracle.xml.parser.v2.DOMParser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import common.Trx;
import java.sql.Connection;

public class UM_GOV_ConiC025c
{
    Connection con;
    Trx trx;
    String dxeHost;
    int dxePort;
    String dxeStore;
    String XlnSourceMaster;
    String dxeUser;
    String dxePassword;
    private String[] 일련번호1;
    private String[] 대표자명;
    private String[] 대표자주민번호;
    private String[] 대표자주소;
    ResultSet rs;
    ResultSet rs1;
    ResultSet rs2;
    PreparedStatement psmt;
    StringBuffer sb;
    String sql;
    String sql1;
    String sql2;
    DOMParser parser;
    
    public UM_GOV_ConiC025c() {
        this.dxeHost = "XLNDB";
        this.dxePort = 1050;
        this.dxeStore = "usemn";
        this.XlnSourceMaster = String.valueOf(this.dxeStore) + ":/xml/Issrea000g.xml";
        this.dxeUser = "";
        this.dxePassword = "";
        this.일련번호1 = new String[] { "cc:Line.Number.Value", "Numeric.Content" };
        this.대표자명 = new String[] { "Organization.CEO.Name", "Text.Content" };
        this.대표자주민번호 = new String[] { "cc:Organization.CEO.Identifier", "Identifier.Content" };
        this.대표자주소 = new String[] { "cc:Address.Line1.Text", "Text.Content" };
        this.rs = null;
        this.rs1 = null;
        this.rs2 = null;
        this.psmt = null;
        this.sb = new StringBuffer();
        this.sql = null;
        this.sql1 = null;
        this.sql2 = null;
        this.parser = new DOMParser();
    }
    
    public String loadDB2XML(final String mCode, final String id, final String unpairNo, final String punishmentCount) {
        String result = "";
        final UM_GOV_ConiC021c um_gov_conic021c = new UM_GOV_ConiC021c();
        try {
            System.setProperty("com.exln.dxe.adminhost", this.dxeHost);
            final StringReader _Message = null;
            this.parser.parse((Reader)_Message);
            final XMLDocument dMaster = this.parser.getDocument();
            _Message.close();
            this.sb.append(" SELECT\tA.공공기관명_전체,\tB.담당자명,\tB.담당자전화번호\t\t\n");
            this.sb.append(" FROM\t사용_공공기관마스터 A, 사용_사용자  B\t\t\t\t\t\n");
            this.sb.append(" WHERE\tA.공공기관코드 = B.마스터코드\t\t\t\t\t\t\t\n");
            this.sb.append("   AND\tA.공공기관코드 = '" + mCode + "'\t\t\t\t\t\t\t\n");
            this.sb.append("   AND\tB.사용자ID = '" + id + "'\t\t\t        \t\t\t\t\n");
            this.sql = this.sb.toString();
            this.sb.setLength(0);
            this.sb.append(" SELECT\tA.문서번호,         \n");
            this.sb.append(" \t\tA.사업자등록번호,\tA.법인등록번호,\t\tA.상호명,\t\tA.우편번호,\t\t            \n");
            this.sb.append("\t  \t\tA.주소,\t\t\t\tA.나머지주소,       A.영업종목,\t\tA.면허등록번호,\t            \n");
            this.sb.append("\t  \t\tA.제재근거1,\t    A.제재근거2,        B.코드명,\t    A.제재횟수,         \t\t\n");
            this.sb.append("\t  \t\tTO_CHAR(A.제재연월일, 'YYYYMMDD') 제재연월일,\tTO_CHAR(A.만료연월일, 'YYYYMMDD') 만료연월일,\t\n");
            this.sb.append("\t  \t\tA.제재기간,\t        TO_CHAR(A.해약연월일, 'YYYYMMDD') 해약연월일,\t\t\t\t\t\n");
            this.sb.append("\t\t\tA.기타,             TO_CHAR(A.정지일자, 'YYYYMMDD') 정지일자,     A.정지사유        \n");
            this.sb.append(" FROM\t사용_부정당업자\tA, SYN_공동코드 B\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            this.sb.append(" WHERE\tA.사업자등록번호 = '" + ComStr.replace(unpairNo, "-", "") + "'\t\t\t\t\t\t\t\n");
            this.sb.append(" AND\t\tA.제재횟수 = " + punishmentCount + "\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            this.sb.append(" AND\t\tB.코드구분(+) = 'GUH'              \t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            this.sb.append(" AND\t\tB.코드(+) = A.계약종류코드           \t\t\t\t\t\t\t\t\t\t\t\t\n");
            this.sql1 = this.sb.toString();
            this.sb.setLength(0);
            this.sb.append(" SELECT\t대표자명,           주민등록번호,       대표자주소      \n");
            this.sb.append(" FROM\t사용_부정당대표자\t    \t\t\t\t\t\t\t\t\n");
            this.sb.append(" WHERE\t사업자등록번호 = '" + ComStr.replace(unpairNo, "-", "") + "'\n");
            this.sb.append(" AND\t\t제재횟수 = " + punishmentCount + "\t\t\t\t\t\t\t\n");
            this.sql2 = this.sb.toString();
            this.trx = new Trx(this, "usemn");
            this.con = this.trx.getConnection();
            this.psmt = this.con.prepareStatement(this.sql);
            this.rs = this.psmt.executeQuery();
            this.psmt.clearParameters();
            if (this.rs.next()) {
                this.psmt = this.con.prepareStatement(this.sql1);
                this.rs1 = this.psmt.executeQuery();
                this.psmt.clearParameters();
                final String KigwanNm = this.rs.getString(1);
                final String chargeNm = this.rs.getString(2);
                final String chargeTel = this.rs.getString(3);
                if (this.rs1 != null && this.rs1.next()) {
                    final String docNo = this.rs1.getString(1);
                    final String saup_no = this.rs1.getString(2);
                    final String lawNo = this.rs1.getString(3);
                    final String upcheNm = this.rs1.getString(4);
                    final String bonsapostNo = this.rs1.getString(5);
                    final String bonsaAddr = this.rs1.getString(6);
                    final String bonsaAddrNa = this.rs1.getString(7);
                    final String businItem = this.rs1.getString(8);
                    final String licenseNo = this.rs1.getString(9);
                    final String punishmentBasic = this.rs1.getString(10);
                    final String punishmentBasic2 = this.rs1.getString(11);
                    final String notifyKind = this.rs1.getString(12);
                    final String Count = this.rs1.getString(13);
                    final String punishmentStart = this.rs1.getString(14);
                    final String punishmentEnd = this.rs1.getString(15);
                    final String punishmentPeriod = this.rs1.getString(16);
                    final String cancelDay = this.rs1.getString(17);
                    final String gita = this.rs1.getString(18);
                    final String stopdate = this.rs1.getString(19);
                    final String stopsayu = this.rs1.getString(20);
                    um_gov_conic021c.문서번호[0] = docNo;
                    um_gov_conic021c.문서명[0] = "부정당업자 제재재개통보서";
                    um_gov_conic021c.입력자ID[0] = id;
                    um_gov_conic021c.공공기관명[0] = KigwanNm;
                    um_gov_conic021c.담당자성명[0] = chargeNm;
                    um_gov_conic021c.담당자전화번호[0] = chargeTel;
                    um_gov_conic021c.공공기관코드[0] = mCode;
                    um_gov_conic021c.공공기관명1[0] = "조달청 G2B 시스템";
                    um_gov_conic021c.담당자성명1[0] = "";
                    um_gov_conic021c.사업자등록번호[0] = saup_no;
                    um_gov_conic021c.법인등록번호[0] = lawNo;
                    um_gov_conic021c.상호명[0] = upcheNm;
                    um_gov_conic021c.우편번호[0] = bonsapostNo;
                    um_gov_conic021c.주소1[0] = bonsaAddr;
                    um_gov_conic021c.주소2[0] = bonsaAddrNa;
                    um_gov_conic021c.영업종목[0] = businItem;
                    um_gov_conic021c.면허번호[0] = licenseNo;
                    um_gov_conic021c.제재근거_1[0] = punishmentBasic;
                    um_gov_conic021c.제재근거_2[0] = punishmentBasic2;
                    um_gov_conic021c.계약종류[0] = notifyKind;
                    um_gov_conic021c.제재횟수[0] = Count;
                    um_gov_conic021c.제재년월일[0] = punishmentStart;
                    um_gov_conic021c.만료년월일[0] = "";
                    um_gov_conic021c.제재기간[0] = punishmentPeriod;
                    um_gov_conic021c.해약년월일[0] = cancelDay;
                    um_gov_conic021c.비고[0] = gita;
                    um_gov_conic021c.정지일자[0] = stopdate;
                    um_gov_conic021c.정지사유[0] = stopsayu;
                    PutValues(dMaster, um_gov_conic021c.문서번호);
                    PutValues(dMaster, um_gov_conic021c.문서명);
                    PutValues(dMaster, um_gov_conic021c.작성일자);
                    PutValues(dMaster, um_gov_conic021c.입력자ID);
                    PutValues(dMaster, um_gov_conic021c.공공기관명);
                    PutValues(dMaster, um_gov_conic021c.담당자성명);
                    PutValues(dMaster, um_gov_conic021c.담당자전화번호);
                    PutValues(dMaster, um_gov_conic021c.공공기관코드);
                    PutValues(dMaster, um_gov_conic021c.공공기관명1);
                    PutValues(dMaster, um_gov_conic021c.담당자성명1);
                    PutValues(dMaster, um_gov_conic021c.사업자등록번호);
                    PutValues(dMaster, um_gov_conic021c.법인등록번호);
                    PutValues(dMaster, um_gov_conic021c.상호명);
                    PutValues(dMaster, um_gov_conic021c.우편번호);
                    PutValues(dMaster, um_gov_conic021c.주소1);
                    PutValues(dMaster, um_gov_conic021c.주소2);
                    PutValues(dMaster, um_gov_conic021c.영업종목);
                    PutValues(dMaster, um_gov_conic021c.면허번호);
                    PutValues(dMaster, um_gov_conic021c.제재근거_1);
                    PutValues(dMaster, um_gov_conic021c.제재근거_2);
                    PutValues(dMaster, um_gov_conic021c.계약종류);
                    PutValues(dMaster, um_gov_conic021c.제재횟수);
                    PutValues(dMaster, um_gov_conic021c.제재년월일);
                    PutValues(dMaster, um_gov_conic021c.만료년월일);
                    PutValues(dMaster, um_gov_conic021c.제재기간);
                    PutValues(dMaster, um_gov_conic021c.해약년월일);
                    PutValues(dMaster, um_gov_conic021c.비고);
                    PutValues(dMaster, um_gov_conic021c.정지일자);
                    PutValues(dMaster, um_gov_conic021c.정지사유);
                }
            }
            if (this.psmt != null) {
                try {
                    this.psmt.close();
                }
                catch (Exception ex) {}
            }
            this.psmt = this.con.prepareStatement(this.sql2);
            this.rs2 = this.psmt.executeQuery();
            this.psmt.clearParameters();
            if (this.rs2 != null) {
                int j = 0;
                while (this.rs2.next()) {
                    final String JuminNo = this.rs2.getString(2);
                    um_gov_conic021c.일련번호1[0] = Integer.toString(j + 1);
                    um_gov_conic021c.대표자명[0] = isStrNull(this.rs2.getString(1));
                    um_gov_conic021c.대표자주민번호[0] = isStrNull(JuminNo);
                    um_gov_conic021c.대표자주소[0] = isStrNull(this.rs2.getString(3));
                    dpPutValues(dMaster, um_gov_conic021c.일련번호1, this.일련번호1, j, 1);
                    dpPutValues(dMaster, um_gov_conic021c.대표자명, this.대표자명, j, 0);
                    dpPutValues(dMaster, um_gov_conic021c.대표자주민번호, this.대표자주민번호, j, 0);
                    dpPutValues(dMaster, um_gov_conic021c.대표자주소, this.대표자주소, j, 0);
                    ++j;
                }
            }
            this.rs.close();
            this.rs1.close();
            this.rs2.close();
            this.psmt.close();
            this.con.close();
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final PrintWriter p = new PrintWriter(baos);
            dMaster.print(p);
            result = baos.toString();
            p.close();
            baos.close();
            result = String.valueOf(result.substring(0, result.indexOf("'UTF-8'"))) + "'euc-kr'" + result.substring(result.indexOf("'UTF-8'") + 7, result.length());
        }
        catch (SQLException sql) {
            Log.debug("UM_GOV_ConiA025c.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + sql.toString() + sql.getErrorCode() + sql.getSQLState());
            sql.printStackTrace();
        }
        catch (Exception e) {
            Log.errors("");
            Log.errors("-------------------------------------------------------------------------");
            Log.errors("UM_GOV_ConiA025c.loadDB2XML() Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
            result = "로딩에 이상이 생겼습니다. 잠시후 다시 시도해주시기 바랍니다.";
        }
        finally {
            if (this.rs2 != null) {
                try {
                    this.rs2.close();
                }
                catch (Exception ex2) {}
            }
            if (this.rs1 != null) {
                try {
                    this.rs1.close();
                }
                catch (Exception ex3) {}
            }
            if (this.rs != null) {
                try {
                    this.rs.close();
                }
                catch (Exception ex4) {}
            }
            if (this.psmt != null) {
                try {
                    this.psmt.close();
                }
                catch (Exception ex5) {}
            }
            if (this.trx != null) {
                try {
                    this.trx.close();
                }
                catch (Exception ex6) {}
            }
        }
        if (this.rs2 != null) {
            try {
                this.rs2.close();
            }
            catch (Exception ex7) {}
        }
        if (this.rs1 != null) {
            try {
                this.rs1.close();
            }
            catch (Exception ex8) {}
        }
        if (this.rs != null) {
            try {
                this.rs.close();
            }
            catch (Exception ex9) {}
        }
        if (this.psmt != null) {
            try {
                this.psmt.close();
            }
            catch (Exception ex10) {}
        }
        if (this.trx != null) {
            try {
                this.trx.close();
            }
            catch (Exception ex11) {}
        }
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
            Log.errors("UM_GOV_ConiA025c.PutValues() Exception : ");
            Log.errors("Exception 발생사유 : " + e.toString());
            Log.errors("-------------------------------------------------------------------------");
        }
    }
    
    public static String isStrNull(final String value) {
        return (value == null) ? " " : value.trim();
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
                final Node ageNode = doc.selectNodes("/gb:Issrea/Organization.Information/CeoList/CeoItem", (NSResolver)xemt).item(index);
                ageNode.getParentNode().insertBefore(addEle, ageNode);
            }
            addEle = doc.createElement(nodeNameType[0]);
            tmpNodeList = doc.selectNodes("/gb:Issrea/Organization.Information/CeoList/CeoItem", (NSResolver)xemt);
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
