// 
// Decompiled by Procyon v0.5.30
// 

package exms.upload;

import common.Log;
import org.w3c.dom.Element;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import org.w3c.dom.NodeList;
import java.util.Vector;
import oracle.xml.parser.v2.NSResolver;
import oracle.xml.parser.v2.XMLElement;
import java.io.IOException;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.FileInputStream;
import oracle.xml.parser.v2.XMLDocument;
import oracle.xml.parser.v2.DOMParser;
import java.sql.Connection;

public class RcvDfisan
{
    private Connection pconn;
    private String xmlFileName;
    private long serialNumber;
    private String sourceName;
    private int repeatCount;
    private String baseSaupjaNumber;
    DOMParser parser;
    XMLDocument xmlDoc;
    
    public RcvDfisan(final Connection conn) {
        this.pconn = null;
        this.xmlFileName = null;
        this.serialNumber = 0L;
        this.sourceName = "RcvDfisan";
        this.repeatCount = 0;
        this.baseSaupjaNumber = "";
        this.parser = new DOMParser();
        this.xmlDoc = new XMLDocument();
        this.pconn = conn;
        this.serialNumber = System.currentTimeMillis();
    }
    
    public String main_process(final String xmlfile, final String fullpath) {
        this.xmlFileName = xmlfile;
        FileInputStream fin = null;
        try {
            this.SaveLog("main_process start path[" + fullpath + "]");
            fin = new FileInputStream(fullpath);
            this.parser.parse((InputStream)fin);
            this.xmlDoc = this.parser.getDocument();
            this.pconn.setAutoCommit(false);
            this.SaveLog(" ## updateMaster1 start ");
            this.updateMaster1(this.pconn);
            this.SaveLog(" ## updateMaster1  end");
            this.SaveLog(" ## updateDaepyo start ");
            this.updateDaepyo(this.pconn);
            this.SaveLog(" ## updateDaepyo end");
            final String[] saupjanumber = this.getSaupjaNumber(this.pconn);
            this.SaveLog(" ## 대표자가 같은 다른 본사 ");
            this.SaveLog(" ## 대표자주민번호가 같은 사업자 :  " + saupjanumber.length);
            for (int j = 0; j < saupjanumber.length; ++j) {
                this.SaveLog(" 추가업체 부정당제재 [" + saupjanumber[j] + "])");
                this.updateMaster2(this.pconn, saupjanumber[j]);
            }
            final String[] brsaupjanumber = this.getBRSaupjaNumber(this.pconn);
            this.SaveLog(" ## 본사의 지사 ");
            this.SaveLog(" ## 사업자등록번호 제재한 지사 사업자 :  " + brsaupjanumber.length);
            for (int i = 0; i < brsaupjanumber.length; ++i) {
                this.SaveLog(" 추가업체 부정당제재 [" + brsaupjanumber[i] + "])");
                this.updateMaster2(this.pconn, brsaupjanumber[i]);
            }
            final String[] ceobrsaupjanumber = this.getCEOBRSaupjaNumber(this.pconn);
            this.SaveLog(" ## 대표자가 같은 다른 본사의 지사 ");
            this.SaveLog(" ## 대표자가 같은 사업자등록번호 제재한 지사 사업자 :  " + ceobrsaupjanumber.length);
            for (int k = 0; k < ceobrsaupjanumber.length; ++k) {
                this.SaveLog(" 추가업체 부정당제재 [" + ceobrsaupjanumber[k] + "])");
                this.updateMaster2(this.pconn, ceobrsaupjanumber[k]);
            }
            if (this.pconn != null) {
                try {
                    this.pconn.commit();
                }
                catch (Exception ex) {}
            }
            if (this.pconn != null) {
                try {
                    this.pconn.setAutoCommit(true);
                }
                catch (Exception ex2) {}
            }
            this.SaveLog("## main process end");
            return "true";
        }
        catch (SQLException e) {
            if (this.pconn != null) {
                try {
                    this.pconn.rollback();
                }
                catch (Exception ex3) {}
            }
            if (this.pconn != null) {
                try {
                    this.pconn.setAutoCommit(true);
                }
                catch (Exception ex4) {}
            }
            this.SaveLog("SQLException : " + e.toString());
            if (e.getErrorCode() == 1) {
                return "-2[RcvDfisan - 부정당업체등록통보서]oracle error 0001";
            }
            return String.valueOf(e.getErrorCode()) + "[RcvDfisan - 부정당업체등록통보서]oracle error " + e.getErrorCode() + ":" + e.toString();
        }
        catch (Exception e2) {
            if (this.pconn != null) {
                try {
                    this.pconn.rollback();
                }
                catch (Exception ex5) {}
            }
            if (this.pconn != null) {
                try {
                    this.pconn.setAutoCommit(true);
                }
                catch (Exception ex6) {}
            }
            this.SaveLog("Exception : " + e2.toString());
            return "-2[RcvDfisan - 부정당업체등록통보서]DB Update Error" + e2.getMessage();
        }
        finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            }
            catch (IOException ex7) {}
        }
    }
    
    private String[] getSaupjaNumber(final Connection pconn) throws Exception {
        final XMLElement xemt = (XMLElement)this.xmlDoc.getDocumentElement();
        final NodeList pNode = this.xmlDoc.selectNodes("/gb:Dfisan/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", (NSResolver)xemt);
        final String saupjaNumber = this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] result = (String[])null;
        final Vector vec = new Vector(1, 1);
        final String sql = "SELECT BIZ_REG_NO FROM UM_REPR WHERE REPR_IDENT_NO=?";
        try {
            pstmt = pconn.prepareStatement(sql);
            vec.addElement(saupjaNumber);
            for (int i = 0; i < pNode.getLength(); ++i) {
                pstmt.setString(1, this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", i));
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    if (!rs.getString(1).equals(saupjaNumber)) {
                        vec.addElement(rs.getString(1));
                    }
                }
            }
            result = new String[vec.size()];
            vec.copyInto(result);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception ex3) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex4) {}
        return result;
    }
    
    private String[] getBRSaupjaNumber(final Connection pconn) throws Exception {
        final String saupjaNumber = this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] result = (String[])null;
        final Vector vec = new Vector(1, 1);
        final String sql = "  SELECT BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM  UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t                                        FROM UM_SUPPLIER_ENTER_MAST\t\t\t\t                                        WHERE BIZ_REG_NO  = ?)\t\t\t";
        try {
            pstmt = pconn.prepareStatement(sql);
            vec.addElement(saupjaNumber);
            pstmt.setString(1, saupjaNumber);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (!rs.getString(1).equals(saupjaNumber)) {
                    vec.addElement(rs.getString(1));
                }
            }
            result = new String[vec.size()];
            vec.copyInto(result);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception ex3) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex4) {}
        return result;
    }
    
    private String[] getCEOBRSaupjaNumber(final Connection pconn) throws Exception {
        final XMLElement xemt = (XMLElement)this.xmlDoc.getDocumentElement();
        final NodeList pNode = this.xmlDoc.selectNodes("/gb:Dfisan/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", (NSResolver)xemt);
        final String saupjaNumber = this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String[] result = (String[])null;
        final Vector vec = new Vector(1, 1);
        final String sql = "\t\tSELECT BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFROM  UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM UM_SUPPLIER_ENTER_MAST x, UM_REPR y        \t\t\t\t\t\t\t\t\t\t\t  WHERE x.BIZ_REG_NO = y.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t  AND  y.REPR_IDENT_NO=?)\t\t\t\t\t\t\t\t\t";
        try {
            pstmt = pconn.prepareStatement(sql);
            vec.addElement(saupjaNumber);
            for (int i = 0; i < pNode.getLength(); ++i) {
                pstmt.setString(1, this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", i));
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    if (!rs.getString(1).equals(saupjaNumber)) {
                        vec.addElement(rs.getString(1));
                    }
                }
            }
            result = new String[vec.size()];
            vec.copyInto(result);
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception ex3) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex4) {}
        return result;
    }
    
    private void updateMaster1(final Connection pconn) throws Exception {
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            query = " select nvl(max(PUNISH_COUNT),0)+1 from UM_VIOLATE_CO where BIZ_REG_NO = '" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "'";
            this.baseSaupjaNumber = this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                this.repeatCount = rs.getInt(1);
            }
            this.SaveLog("BIZ_REG_NO[" + this.baseSaupjaNumber + "], PUNISH_COUNT[" + this.repeatCount + "]");
            query = " INSERT INTO UM_VIOLATE_CO (  BIZ_REG_NO, INPUT_DT, DOC_NO, CHRG_NM, CHRG_PHONE_NO,\t\t\n CORP_REG_NO, BIZ_NM, ZIP_CD, ADDR,  DETAIL_ADDR,\t\t\t\t\t\n CONTR_TYPE, BIZ_CAT, LICENSE_REG_NO, \t\t\t\t\t\t\t\t\n PUNISH_BASIS1, PUNISH_BASIS2,  PUNISH_DT,\t\t\t\t            \t\t\n EXPIRE_DT, PUNISH_PERIOD, CANCEL_DT, \n PUNISH_COUNT, INPUT_UM_ID, PUNISH_INSTITU_CD_CLS, INSTITU_NM, INSTITU_CD, SUSPEND_CLS, NOTE,\t\t\n RESUMPTION_CLS, RELEASE_CLS                                        \t\t\n ) VALUES \n('" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "', " + "sysdate,  " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Header.Details/cc:Document.Number.Text/Text.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/PublicOrganization.Details/Employee.Details/cc:Employee.Name/Text.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/PublicOrganization.Details/Employee.Details/cc:Telephone.Number.Text/Text.Content", 0) + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Registration.Number.Text/Text.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Organization.Details/cc:Organization.Name/Text.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Address.Details/cc:PostCode.Identifier/Identifier.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Address.Details/cc:Address.Line1.Text/Text.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Address.Details/cc:Address.Line2.Text/Text.Content", 0) + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/cc:Contract.Type.Code/Code.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Business.Type.Text/Text.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/License.Number.Text/Text.Content", 0) + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Sanction.Details/Sanction.Basis.Text/Text.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Sanction.Details/Sanction.Basis1.Text/Text.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Sanction.Details/Sanction.Date/DateTime.Content", 0) + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Sanction.Details/Expiration.Date/DateTime.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Sanction.Details/Sanction.Periode.Text/Text.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Sanction.Details/Contract.Cancel.Date/DateTime.Content", 0) + "', " + Integer.toString(this.repeatCount) + ", \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Sanction.GovernmentOffice.Code/Code.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/PublicOrganization.Details/Organization.Details/cc:Organization.Name/Text.Content", 0) + "', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "', " + "'N', " + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/cc:General.Note.Text/Text.Content", 0) + "', " + "'N', " + "'N')";
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
            pstmt = pconn.prepareStatement(query);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[RcvDfisan-부정당업체등록통보서]DB UPDATE 에러가 발생했습니다");
            }
            this.SaveLog("UM_VIOLATE_CO 입력 : BIZ_REG_NO[" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "]");
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex4) {}
        }
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception ex5) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex6) {}
    }
    
    private void updateDaepyo(final Connection pconn) throws Exception {
        String query = null;
        PreparedStatement pstmt = null;
        final XMLElement xemt = (XMLElement)this.xmlDoc.getDocumentElement();
        final NodeList pNode = this.xmlDoc.selectNodes("/gb:Dfisan/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", (NSResolver)xemt);
        try {
            query = " INSERT INTO UM_VIOLATE_REPR ( BIZ_REG_NO, PUNISH_COUNT, SERIAL_NO, INPUT_DT, IDENT_NO, REPR_NM, REPR_ADDR ) VALUES (?,?,SERIAL_NO.nextval,sysdate,?,?,?)";
            pstmt = pconn.prepareStatement(query);
            for (int i = 0; i < pNode.getLength(); ++i) {
                pstmt.setString(1, this.baseSaupjaNumber);
                pstmt.setString(2, Integer.toString(this.repeatCount));
                pstmt.setString(3, this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", i));
                pstmt.setString(4, this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/CeoList/CeoItem/Organization.CEO.Name/Text.Content", i));
                pstmt.setString(5, this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/CeoList/CeoItem/cc:Address.Line1.Text/Text.Content", i));
                if (pstmt.executeUpdate() != 1) {
                    throw new Exception("[RcvDfisan-UM_VIOLATE_REPR] DB UPDATE 에러가 발생했습니다");
                }
                this.SaveLog("UM_VIOLATE_REPR 입력 : IDENT_NO[" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", i) + "]insert 완료");
            }
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    private void updateMaster2(final Connection pconn, final String SaupNo) throws Exception {
        final String startDate = this.GetValue(this.xmlDoc, "/gb:Dfisan/Sanction.Details/Sanction.Date/DateTime.Content", 0);
        final String endDate = this.GetValue(this.xmlDoc, "/gb:Dfisan/Sanction.Details/Expiration.Date/DateTime.Content", 0);
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        String nowDate = "";
        String sDate = "";
        String eDate = "";
        String sDate2 = "";
        String eDate2 = "";
        try {
            query = " SELECT count(*) FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO ='" + SaupNo + "'";
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                return;
            }
            query = " SELECT COUNT(*) aa FROM UM_ENTER_STATE WHERE BIZ_REG_NO='" + SaupNo + "' and STATE_CLS='05'";
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
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (count == 0) {
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
                query = " SELECT TO_CHAR(sysdate, 'yyyymmdd') FROM dual ";
                pstmt = pconn.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    nowDate = rs.getString(1);
                }
                if (Integer.parseInt(startDate) <= Integer.parseInt(nowDate)) {
                    query = " INSERT INTO UM_ENTER_STATE (  BIZ_REG_NO, RAISED_DT, START_DT, END_DT, STATE_CLS, REMARK, MANAGER_ID, PROCESS_DT ) VALUES ( '" + SaupNo + "', " + "sysdate," + "'" + startDate + "'," + "'" + endDate + "','05','" + this.baseSaupjaNumber + "/" + this.repeatCount + "'," + "'" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', " + "sysdate)";
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
                    pstmt = pconn.prepareStatement(query);
                    if (pstmt.executeUpdate() != 1) {
                        throw new Exception("[RcvDfisan-부정당업체등록통보서]DB INSERT 에러가 발생했습니다");
                    }
                    this.SaveLog("UM_ENTER_STATE 제재 입력 : BIZ_REG_NO[" + SaupNo + "]");
                }
            }
            else {
                query = " SELECT TO_CHAR(START_DT, 'yyyymmdd'), TO_CHAR(END_DT, 'yyyymmdd')  FROM UM_ENTER_STATE  WHERE BIZ_REG_NO = '" + SaupNo + "' " + "   AND STATE_CLS = '05' ";
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
                pstmt = pconn.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    sDate2 = rs.getString(1);
                    eDate2 = rs.getString(2);
                }
                if (Integer.parseInt(startDate) <= Integer.parseInt(sDate2)) {
                    sDate = startDate;
                }
                else {
                    sDate = sDate2;
                }
                if (Integer.parseInt(eDate2) <= Integer.parseInt(endDate)) {
                    eDate = endDate;
                }
                else {
                    eDate = eDate2;
                }
                query = " update UM_ENTER_STATE set RAISED_DT=sysdate, START_DT='" + sDate + "',END_DT='" + eDate + "',REMARK='" + this.baseSaupjaNumber + "/" + this.repeatCount + "'," + "     MANAGER_ID='" + this.GetValue(this.xmlDoc, "/gb:Dfisan/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "'," + "     PROCESS_DT=sysdate" + " where BIZ_REG_NO='" + SaupNo + "'" + " and STATE_CLS='05'";
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
                pstmt = pconn.prepareStatement(query);
                pstmt.executeUpdate();
                this.SaveLog("UM_ENTER_STATE 제재 수정 : BIZ_REG_NO[" + SaupNo + "]");
            }
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception ex11) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex12) {}
        }
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception ex13) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex14) {}
    }
    
    private String GetValue(final XMLDocument doc, final String node, final int index) throws Exception {
        if (node.indexOf("@") == -1) {
            return this.GetNodeValue(doc, node, index);
        }
        return this.GetAttrValue(doc, node.substring(0, node.indexOf("@")), node.substring(node.indexOf("@") + 1, node.length()), index);
    }
    
    private String GetNodeValue(final XMLDocument doc, final String node, final int index) throws Exception {
        try {
            return doc.selectNodes(node, (NSResolver)(XMLElement)doc.getDocumentElement()).item(index).getFirstChild().getNodeValue();
        }
        catch (NullPointerException e) {
            return "";
        }
    }
    
    private String GetAttrValue(final XMLDocument doc, final String node, final String attr, final int index) throws Exception {
        try {
            return ((Element)doc.selectNodes(node, (NSResolver)(XMLElement)doc.getDocumentElement()).item(index)).getAttribute(attr);
        }
        catch (NullPointerException e) {
            return "";
        }
    }
    
    private void SaveLog(final String msg) {
        Log.info("xmlFileName: " + this.xmlFileName + "Dfisan" + this.sourceName + "[" + this.serialNumber + "]:" + msg);
    }
}
