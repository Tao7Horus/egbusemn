// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

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
import java.io.Reader;
import oracle.xml.parser.v2.DOMParser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import common.Trx;
import java.sql.Connection;

public class UM_GOV_ConiA025c
{
    Connection con;
    Trx trx;
    String dxeHost;
    int dxePort;
    String dxeStore;
    String XlnSourceMaster;
    String dxeUser;
    String dxePassword;
    ResultSet rs;
    PreparedStatement psmt;
    StringBuffer sb;
    String sql;
    DOMParser parser;
    
    public UM_GOV_ConiA025c() {
        this.dxeHost = "XLNDB";
        this.dxePort = 1050;
        this.dxeStore = "usemn";
        this.XlnSourceMaster = String.valueOf(this.dxeStore) + ":/xml/Dfisan000g.xml";
        this.dxeUser = "";
        this.dxePassword = "";
        this.rs = null;
        this.psmt = null;
        this.sb = new StringBuffer();
        this.sql = null;
        this.parser = new DOMParser();
    }
    
    public String loadDB2XML(final String mCode, final String id) {
        String result = "";
        final UM_GOV_ConiA021c um_gov_conia021c = new UM_GOV_ConiA021c();
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
            this.trx = new Trx(this, "usemn");
            this.con = this.trx.getConnection();
            this.psmt = this.con.prepareStatement(this.sql);
            this.rs = this.psmt.executeQuery();
            this.psmt.clearParameters();
            if (this.rs.next()) {
                final String KigwanNm = this.rs.getString(1);
                final String chargeNm = this.rs.getString(2);
                final String chargeTel = this.rs.getString(3);
                um_gov_conia021c.문서번호[0] = "";
                um_gov_conia021c.문서명[0] = "부정당업자 제재통보서";
                um_gov_conia021c.작성일자[0] = "";
                um_gov_conia021c.문서기능[0] = "";
                um_gov_conia021c.입력자ID[0] = id;
                um_gov_conia021c.공공기관명[0] = KigwanNm;
                um_gov_conia021c.대표자직위[0] = String.valueOf(KigwanNm) + "장";
                um_gov_conia021c.담당자성명[0] = chargeNm;
                um_gov_conia021c.담당자전화번호[0] = chargeTel;
                um_gov_conia021c.공공기관코드[0] = mCode;
                um_gov_conia021c.공공기관명1[0] = "조달청 G2B 시스템";
                um_gov_conia021c.담당자성명1[0] = "";
                um_gov_conia021c.입찰공고번호[0] = "";
                um_gov_conia021c.입찰공고차수[0] = "";
                um_gov_conia021c.입찰공고명[0] = "";
                um_gov_conia021c.입찰공고일자[0] = "";
                um_gov_conia021c.계약번호[0] = "";
                um_gov_conia021c.계약차수[0] = "";
                um_gov_conia021c.계약종류[0] = "";
                um_gov_conia021c.계약일자[0] = "";
                um_gov_conia021c.사업자등록번호[0] = "";
                um_gov_conia021c.법인등록번호[0] = "";
                um_gov_conia021c.상호명[0] = "";
                um_gov_conia021c.우편번호[0] = "";
                um_gov_conia021c.주소1[0] = "";
                um_gov_conia021c.주소2[0] = "";
                um_gov_conia021c.영업종목[0] = "";
                um_gov_conia021c.면허번호[0] = "";
                um_gov_conia021c.정지구분[0] = "";
                um_gov_conia021c.정지일자[0] = "";
                um_gov_conia021c.정지사유[0] = "";
                um_gov_conia021c.제재근거_1[0] = "";
                um_gov_conia021c.제재근거_2[0] = "";
                um_gov_conia021c.제재근거코드명[0] = "";
                um_gov_conia021c.다른법령제재[0] = "";
                um_gov_conia021c.제재년월일[0] = "";
                um_gov_conia021c.만료년월일[0] = "";
                um_gov_conia021c.제재기간[0] = "";
                um_gov_conia021c.해약년월일[0] = "";
                um_gov_conia021c.기관코드[0] = "";
                um_gov_conia021c.기관명[0] = "";
                um_gov_conia021c.비고[0] = "";
                PutValues(dMaster, um_gov_conia021c.문서번호);
                PutValues(dMaster, um_gov_conia021c.문서명);
                PutValues(dMaster, um_gov_conia021c.작성일자);
                PutValues(dMaster, um_gov_conia021c.문서기능);
                PutValues(dMaster, um_gov_conia021c.입력자ID);
                PutValues(dMaster, um_gov_conia021c.공공기관명);
                PutValues(dMaster, um_gov_conia021c.대표자직위);
                PutValues(dMaster, um_gov_conia021c.담당자성명);
                PutValues(dMaster, um_gov_conia021c.담당자전화번호);
                PutValues(dMaster, um_gov_conia021c.공공기관코드);
                PutValues(dMaster, um_gov_conia021c.공공기관명1);
                PutValues(dMaster, um_gov_conia021c.담당자성명1);
                PutValues(dMaster, um_gov_conia021c.입찰공고번호);
                PutValues(dMaster, um_gov_conia021c.입찰공고차수);
                PutValues(dMaster, um_gov_conia021c.입찰공고명);
                PutValues(dMaster, um_gov_conia021c.입찰공고일자);
                PutValues(dMaster, um_gov_conia021c.계약번호);
                PutValues(dMaster, um_gov_conia021c.계약차수);
                PutValues(dMaster, um_gov_conia021c.계약종류);
                PutValues(dMaster, um_gov_conia021c.계약일자);
                PutValues(dMaster, um_gov_conia021c.사업자등록번호);
                PutValues(dMaster, um_gov_conia021c.법인등록번호);
                PutValues(dMaster, um_gov_conia021c.상호명);
                PutValues(dMaster, um_gov_conia021c.우편번호);
                PutValues(dMaster, um_gov_conia021c.주소1);
                PutValues(dMaster, um_gov_conia021c.주소2);
                PutValues(dMaster, um_gov_conia021c.영업종목);
                PutValues(dMaster, um_gov_conia021c.면허번호);
                PutValues(dMaster, um_gov_conia021c.정지구분);
                PutValues(dMaster, um_gov_conia021c.정지일자);
                PutValues(dMaster, um_gov_conia021c.정지사유);
                PutValues(dMaster, um_gov_conia021c.제재근거_1);
                PutValues(dMaster, um_gov_conia021c.제재근거_2);
                PutValues(dMaster, um_gov_conia021c.제재근거코드명);
                PutValues(dMaster, um_gov_conia021c.다른법령제재);
                PutValues(dMaster, um_gov_conia021c.제재년월일);
                PutValues(dMaster, um_gov_conia021c.만료년월일);
                PutValues(dMaster, um_gov_conia021c.제재기간);
                PutValues(dMaster, um_gov_conia021c.해약년월일);
                PutValues(dMaster, um_gov_conia021c.기관코드);
                PutValues(dMaster, um_gov_conia021c.기관명);
                PutValues(dMaster, um_gov_conia021c.비고);
            }
            this.rs.close();
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
            if (this.rs != null) {
                try {
                    this.rs.close();
                }
                catch (Exception ex) {}
            }
            if (this.psmt != null) {
                try {
                    this.psmt.close();
                }
                catch (Exception ex2) {}
            }
            if (this.trx != null) {
                try {
                    this.trx.close();
                }
                catch (Exception ex3) {}
            }
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
}
