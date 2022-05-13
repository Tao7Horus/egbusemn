// 
// Decompiled by Procyon v0.5.30
// 

package exms.upload;

import common.Log;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import oracle.xml.parser.v2.NSResolver;
import oracle.xml.parser.v2.XMLElement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.FileInputStream;
import oracle.xml.parser.v2.XMLDocument;
import oracle.xml.parser.v2.DOMParser;
import java.sql.Connection;

public class RcvIssrea
{
    Connection pconn;
    DOMParser parser;
    XMLDocument xmlDoc;
    String xmlFileName;
    private long serialNumber;
    private String sourceName;
    
    public RcvIssrea(final Connection conn) {
        this.pconn = null;
        this.parser = new DOMParser();
        this.xmlDoc = new XMLDocument();
        this.serialNumber = 0L;
        this.sourceName = "RcvIssrea";
        this.pconn = conn;
        this.serialNumber = System.currentTimeMillis();
    }
    
    public String main_process(final String xmlfile, final String fullpath) {
        this.xmlFileName = xmlfile;
        FileInputStream fin = null;
        try {
            this.SaveLog(" ## main_process start ");
            fin = new FileInputStream(fullpath);
            this.parser.parse((InputStream)fin);
            this.xmlDoc = this.parser.getDocument();
            this.pconn.setAutoCommit(false);
            this.SaveLog(" ## updateMaster1 start ");
            this.updateMaster1(this.pconn);
            this.SaveLog(" ## updateMaster1 end ");
            this.SaveLog(" ## updateMaster2 start ");
            this.updateMaster2(this.pconn);
            this.SaveLog(" ## updateMaster2 end ");
            this.SaveLog(" ## updateDetail start ");
            this.updateDetail(this.pconn);
            this.SaveLog(" ## updateDetail end ");
            this.SaveLog(" ## updateDetailBR start ");
            this.updateDetailBR(this.pconn);
            this.SaveLog(" ## updateDetailBR end ");
            this.SaveLog(" ## updateDetailBRCEO start ");
            this.updateDetailBRCEO(this.pconn);
            this.SaveLog(" ## updateDetailBRCEO end ");
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
            this.SaveLog(" ## main_process end ");
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
                return "-2[RcvIssrea - 부정당업체등록통보서]oracle error 0001";
            }
            return String.valueOf(e.getErrorCode()) + "[RcvIssrea - 부정당업체등록통보서]oracle error " + e.getErrorCode() + ":" + e.toString();
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
            return "-2[RcvIssrea - 부정당업체등록통보서]DB Update Error" + e2.getMessage();
        }
        finally {
            if (fin != null) {
                try {
                    fin.close();
                }
                catch (Exception ex7) {}
            }
        }
    }
    
    private void updateMaster1(final Connection pconn) throws Exception {
        String query = null;
        PreparedStatement pstmt = null;
        try {
            query = " UPDATE  UM_VIOLATE_COM SET                                                                                                         \n         DOC_NO   = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Header.Details/cc:Document.Number.Text/Text.Content", 0) + "',                  \n" + "         EXPIRE_DT = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0) + "',                    \n" + "         RESUMPTION_CLS   = 'Y',                                                                                                           \n" + "         RESUMPTION_DT   = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Restart.Date/DateTime.Content", 0) + "',      \n" + "         RESUMPTION_RSON   = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Restart.Reason/Text.Content", 0) + "',        \n" + "         RESUMPTION_UM_ID   = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "'        \n" + " WHERE    \t\t\t\t\t\t\t                                                                                                \n" + "         BIZ_REG_NO = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "'  \n" + "   AND   PUNISH_COUNT       = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Number.Text/Text.Content", 0) + "'";
            pstmt = pconn.prepareStatement(query);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("UM_VIOLATE_COM update 개수 1 이 아님");
            }
            this.SaveLog("UM_VIOLATE_COM 수정 : BIZ_REG_NO[" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "], PUNISH_COUNT[" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Number.Text/Text.Content", 0) + "]");
        }
        catch (Exception exf) {
            this.SaveLog("updateMaster1(): " + exf.toString());
            throw exf;
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
    
    private void updateMaster2(final Connection pconn) throws Exception {
        final String date = "";
        String Gita = "";
        String sDate = "";
        String eDate = "";
        String sDate2 = "";
        String eDate2 = "";
        final String endDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0);
        final String SaupNo = this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        final String unpairCount = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Number.Text/Text.Content", 0);
        final String RestartDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Restart.Date/DateTime.Content", 0);
        Gita = String.valueOf(SaupNo) + "/" + unpairCount;
        String query = null;
        query = " SELECT count(*) FROM UM_SUPPLIER_ENTER_MAST \n\tWHERE BIZ_REG_NO = \n'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "'";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String count = "0";
        String count2 = "0";
        String count3 = "0";
        String nowDate = "";
        try {
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getString(1);
            }
            query = " SELECT COUNT(BIZ_REG_NO) aa FROM UM_ENTER_STATE WHERE BIZ_REG_NO = \n'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "' \n" + " AND STATE_CLS = '05'";
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex3) {}
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count2 = rs.getString(1);
            }
            query = " SELECT TO_CHAR(sysdate, 'yyyymmdd') FROM dual ";
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception ex4) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex5) {}
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nowDate = rs.getString(1);
            }
            if (count.equals("1") && Integer.parseInt(RestartDate) <= Integer.parseInt(nowDate)) {
                if (count2.equals("0")) {
                    query = " INSERT INTO UM_ENTER_STATE (  BIZ_REG_NO, RAISED_DT, START_DT, END_DT, STATE_CLS, REMARK, MANAGER_ID, PROCESS_DT \n ) VALUES ( \n'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "', \n" + "sysdate, \n " + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0) + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0) + "', '05', '" + Gita + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + "sysdate)";
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                    }
                    catch (Exception ex6) {}
                    try {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                    }
                    catch (Exception ex7) {}
                    pstmt = pconn.prepareStatement(query);
                    pstmt.executeUpdate();
                    this.SaveLog("UM_ENTER_STATE 재개 입력 : BIZ_REG_NO[" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "]");
                }
                else {
                    query = " SELECT COUNT(*)  FROM UM_VIOLATE_COM a, UM_SUPPLIER_ENTER_MAST b  WHERE a.BIZ_REG_NO = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "' " + "   AND a.INSTITU_CD NOT IN ('" + this.GetValue(this.xmlDoc, "/gb:Issrea/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "') " + "   AND a.BIZ_REG_NO = b.BIZ_REG_NO " + "   AND (a.SUSPEND_CLS = 'N' OR (a.SUSPEND_CLS = 'Y' AND a.RESUMPTION_CLS = 'Y')) " + "   AND a.RELEASE_CLS = 'N' " + "   AND TO_CHAR(a.EXPIRE_DT, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd') ";
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                    }
                    catch (Exception ex8) {}
                    try {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                    }
                    catch (Exception ex9) {}
                    pstmt = pconn.prepareStatement(query);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        count3 = rs.getString(1);
                    }
                    query = " SELECT TO_CHAR(MIN(a.PUNISH_DT), 'YYYYMMDD'), TO_CHAR(MAX(a.EXPIRE_DT), 'YYYYMMDD')  FROM UM_VIOLATE_COM a, UM_SUPPLIER_ENTER_MAST b  WHERE a.BIZ_REG_NO = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "' " + "   AND a.INSTITU_CD NOT IN ('" + this.GetValue(this.xmlDoc, "/gb:Issrea/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "') " + "   AND a.BIZ_REG_NO = b.BIZ_REG_NO " + "   AND (a.SUSPEND_CLS = 'N' OR (a.SUSPEND_CLS = 'Y' AND a.RESUMPTION_CLS = 'Y')) " + "   AND a.RELEASE_CLS = 'N' " + "   AND TO_CHAR(a.EXPIRE_DT, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd') ";
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                    }
                    catch (Exception ex10) {}
                    try {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                    }
                    catch (Exception ex11) {}
                    pstmt = pconn.prepareStatement(query);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        sDate2 = rs.getString(1);
                        eDate2 = rs.getString(2);
                    }
                    if (count3.equals("0")) {
                        sDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0);
                        eDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0);
                    }
                    else {
                        if (Integer.parseInt(this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0)) <= Integer.parseInt(sDate2)) {
                            sDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0);
                        }
                        else {
                            sDate = sDate2;
                        }
                        if (Integer.parseInt(eDate2) <= Integer.parseInt(this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0))) {
                            eDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0);
                        }
                        else {
                            eDate = eDate2;
                        }
                    }
                    if (Integer.parseInt(eDate) < Integer.parseInt(nowDate)) {
                        query = " DELETE FROM UM_ENTER_STATE \n\tWHERE BIZ_REG_NO = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "' \n" + "   AND STATE_CLS = '05'";
                        try {
                            if (rs != null) {
                                rs.close();
                            }
                        }
                        catch (Exception ex12) {}
                        try {
                            if (pstmt != null) {
                                pstmt.close();
                            }
                        }
                        catch (Exception ex13) {}
                        pstmt = pconn.prepareStatement(query);
                        pstmt.executeUpdate();
                        this.SaveLog("UM_ENTER_STATE 재개 삭제 : BIZ_REG_NO[" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "]");
                    }
                    else {
                        query = " UPDATE UM_ENTER_STATE SET \n RAISED_DT = SYSDATE, \n START_DT = '" + sDate + "', \n" + " END_DT = '" + eDate + "', \n" + " REMARK     = '" + Gita + "', \n" + " MANAGER_ID = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + " PROCESS_DT = sysdate \n" + " WHERE BIZ_REG_NO = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "' \n" + "   AND STATE_CLS = '05'";
                        try {
                            if (rs != null) {
                                rs.close();
                            }
                        }
                        catch (Exception ex14) {}
                        try {
                            if (pstmt != null) {
                                pstmt.close();
                            }
                        }
                        catch (Exception ex15) {}
                        pstmt = pconn.prepareStatement(query);
                        pstmt.executeUpdate();
                        this.SaveLog("UM_ENTER_STATE 재개 수정 : BIZ_REG_NO[" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "]");
                    }
                }
            }
        }
        catch (Exception ex) {
            this.SaveLog(" updateMaster2() : " + ex.toString());
            throw ex;
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception ex16) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex17) {}
        }
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception ex18) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex19) {}
    }
    
    private void updateDetail(final Connection pconn) throws Exception {
        final String sql2 = "";
        String date = "";
        String sDate = "";
        String eDate = "";
        String sDate2 = "";
        String eDate2 = "";
        final String endDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0);
        final String unpairCount = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Number.Text/Text.Content", 0);
        final String RestartDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Restart.Date/DateTime.Content", 0);
        final XMLElement xemt = (XMLElement)this.xmlDoc.getDocumentElement();
        final NodeList pNode = this.xmlDoc.selectNodes("/gb:Issrea/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", (NSResolver)xemt);
        final String SaupNo = this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        final String Gita = String.valueOf(SaupNo) + "/" + unpairCount;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet rs3 = null;
        PreparedStatement pstmt4 = null;
        PreparedStatement pstmt5 = null;
        ResultSet rs4 = null;
        PreparedStatement pstmt6 = null;
        String query = null;
        String nowDate = "";
        try {
            query = " SELECT TO_CHAR(sysdate, 'yyyymmdd') FROM dual ";
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nowDate = rs.getString(1);
            }
            String sangtaeCount = "";
            String UnpairNo = "";
            String sql3 = "";
            String sql4 = "";
            for (int i = 0; i < pNode.getLength(); ++i) {
                query = " SELECT BIZ_REG_NO FROM UM_REPR WHERE REPR_IDENT_NO =  \n'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", i) + "'";
                try {
                    if (rs != null) {
                        rs.close();
                    }
                }
                catch (Exception ex2) {}
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                }
                catch (Exception ex3) {}
                pstmt = pconn.prepareStatement(query);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    UnpairNo = rs.getString("BIZ_REG_NO");
                    sql3 = " SELECT To_Char(END_DT, 'YYYYMMDD') FROM UM_ENTER_STATE WHERE BIZ_REG_NO = '" + UnpairNo + "' AND STATE_CLS = '05'";
                    sql4 = " SELECT COUNT(*) aa FROM UM_ENTER_STATE WHERE BIZ_REG_NO = '" + UnpairNo + "' AND STATE_CLS = '05'";
                    try {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    catch (Exception ex4) {}
                    try {
                        if (pstmt2 != null) {
                            pstmt2.close();
                        }
                    }
                    catch (Exception ex5) {}
                    pstmt2 = pconn.prepareStatement(sql3);
                    rs2 = pstmt2.executeQuery();
                    if (rs2.next()) {
                        date = rs2.getString(1);
                    }
                    try {
                        if (rs3 != null) {
                            rs3.close();
                        }
                    }
                    catch (Exception ex6) {}
                    try {
                        if (pstmt3 != null) {
                            pstmt3.close();
                        }
                    }
                    catch (Exception ex7) {}
                    pstmt3 = pconn.prepareStatement(sql4);
                    rs3 = pstmt3.executeQuery();
                    if (rs3.next()) {
                        sangtaeCount = rs3.getString(1);
                    }
                    if (Integer.parseInt(RestartDate) <= Integer.parseInt(nowDate)) {
                        if (sangtaeCount.equals("0")) {
                            if (Integer.parseInt(this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0)) > Integer.parseInt(nowDate)) {
                                continue;
                            }
                            query = " INSERT INTO UM_ENTER_STATE (  BIZ_REG_NO, RAISED_DT, START_DT, END_DT, STATE_CLS, REMARK, MANAGER_ID, PROCESS_DT\t\n ) VALUES ( \n'" + UnpairNo + "', \n" + "SYSDATE, \n " + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0) + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0) + "', '05', \n" + "'" + Gita + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + "sysdate)";
                            try {
                                if (pstmt4 != null) {
                                    pstmt4.close();
                                }
                            }
                            catch (Exception ex8) {}
                            pstmt4 = pconn.prepareStatement(query);
                            pstmt4.executeUpdate();
                            this.SaveLog("UM_ENTER_STATE 재개 입력 : BIZ_REG_NO[" + UnpairNo + "]");
                        }
                        else {
                            query = " SELECT TO_CHAR(START_DT, 'yyyymmdd'), TO_CHAR(END_DT, 'yyyymmdd')  FROM UM_ENTER_STATE  WHERE BIZ_REG_NO = '" + UnpairNo + "' " + "   AND STATE_CLS = '05' ";
                            try {
                                if (rs4 != null) {
                                    rs4.close();
                                }
                            }
                            catch (Exception ex9) {}
                            try {
                                if (pstmt5 != null) {
                                    pstmt5.close();
                                }
                            }
                            catch (Exception ex10) {}
                            pstmt5 = pconn.prepareStatement(query);
                            rs4 = pstmt5.executeQuery();
                            if (rs4.next()) {
                                sDate2 = rs4.getString(1);
                                eDate2 = rs4.getString(2);
                            }
                            if (Integer.parseInt(this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0)) <= Integer.parseInt(sDate2)) {
                                sDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0);
                            }
                            else {
                                sDate = sDate2;
                            }
                            if (Integer.parseInt(eDate2) <= Integer.parseInt(this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0))) {
                                eDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0);
                            }
                            else {
                                eDate = eDate2;
                            }
                            if (Integer.parseInt(eDate) < Integer.parseInt(nowDate)) {
                                query = " DELETE FROM UM_ENTER_STATE \n\tWHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                                try {
                                    if (pstmt6 != null) {
                                        pstmt6.close();
                                    }
                                }
                                catch (Exception ex11) {}
                                pstmt6 = pconn.prepareStatement(query);
                                pstmt6.executeUpdate();
                                this.SaveLog("UM_ENTER_STATE 재개 삭제 : BIZ_REG_NO[" + UnpairNo + "]");
                            }
                            else {
                                query = " UPDATE UM_ENTER_STATE SET \n RAISED_DT = SYSDATE, \n START_DT = '" + sDate + "', \n" + " END_DT = '" + eDate + "', \n" + " REMARK     = '" + Gita + "', \n" + " MANAGER_ID = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + " PROCESS_DT = sysdate \n" + " WHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                                try {
                                    if (pstmt6 != null) {
                                        pstmt6.close();
                                    }
                                }
                                catch (Exception ex12) {}
                                pstmt6 = pconn.prepareStatement(query);
                                pstmt6.executeUpdate();
                                this.SaveLog("UM_ENTER_STATE 재개 수정 : BIZ_REG_NO[" + UnpairNo + "]");
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            this.SaveLog(" updateDetail() : " + ex.toString());
            throw ex;
        }
        finally {
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
            try {
                if (rs2 != null) {
                    rs2.close();
                }
            }
            catch (Exception ex15) {}
            try {
                if (pstmt2 != null) {
                    pstmt2.close();
                }
            }
            catch (Exception ex16) {}
            try {
                if (rs3 != null) {
                    rs3.close();
                }
            }
            catch (Exception ex17) {}
            try {
                if (pstmt3 != null) {
                    pstmt3.close();
                }
            }
            catch (Exception ex18) {}
            try {
                if (pstmt4 != null) {
                    pstmt4.close();
                }
            }
            catch (Exception ex19) {}
            try {
                if (rs4 != null) {
                    rs4.close();
                }
            }
            catch (Exception ex20) {}
            try {
                if (pstmt5 != null) {
                    pstmt5.close();
                }
            }
            catch (Exception ex21) {}
            try {
                if (pstmt6 != null) {
                    pstmt6.close();
                }
            }
            catch (Exception ex22) {}
        }
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception ex23) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex24) {}
        try {
            if (rs2 != null) {
                rs2.close();
            }
        }
        catch (Exception ex25) {}
        try {
            if (pstmt2 != null) {
                pstmt2.close();
            }
        }
        catch (Exception ex26) {}
        try {
            if (rs3 != null) {
                rs3.close();
            }
        }
        catch (Exception ex27) {}
        try {
            if (pstmt3 != null) {
                pstmt3.close();
            }
        }
        catch (Exception ex28) {}
        try {
            if (pstmt4 != null) {
                pstmt4.close();
            }
        }
        catch (Exception ex29) {}
        try {
            if (rs4 != null) {
                rs4.close();
            }
        }
        catch (Exception ex30) {}
        try {
            if (pstmt5 != null) {
                pstmt5.close();
            }
        }
        catch (Exception ex31) {}
        try {
            if (pstmt6 != null) {
                pstmt6.close();
            }
        }
        catch (Exception ex32) {}
    }
    
    private void updateDetailBR(final Connection pconn) throws Exception {
        final String sql2 = "";
        String date = "";
        String sDate = "";
        String eDate = "";
        String sDate2 = "";
        String eDate2 = "";
        final String endDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0);
        final String unpairCount = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Number.Text/Text.Content", 0);
        final String RestartDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Restart.Date/DateTime.Content", 0);
        final XMLElement xemt = (XMLElement)this.xmlDoc.getDocumentElement();
        final NodeList pNode = this.xmlDoc.selectNodes("/gb:Issrea/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", (NSResolver)xemt);
        final String SaupNo = this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        final String Gita = String.valueOf(SaupNo) + "/" + unpairCount;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet rs3 = null;
        PreparedStatement pstmt4 = null;
        PreparedStatement pstmt5 = null;
        ResultSet rs4 = null;
        PreparedStatement pstmt6 = null;
        String query = null;
        String nowDate = "";
        try {
            query = " SELECT TO_CHAR(sysdate, 'yyyymmdd') FROM dual ";
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nowDate = rs.getString(1);
            }
            String sangtaeCount = "";
            String UnpairNo = "";
            String sql3 = "";
            String sql4 = "";
            query = "  SELECT BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM  UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t                                        FROM UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t                                        WHERE BIZ_REG_NO  = '" + SaupNo + "')\t\t\t";
            try {
                if (rs != null) {
                    rs.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex3) {}
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UnpairNo = rs.getString("BIZ_REG_NO");
                sql3 = " SELECT To_Char(END_DT, 'YYYYMMDD') FROM UM_ENTER_STATE WHERE BIZ_REG_NO = '" + UnpairNo + "' AND STATE_CLS = '05'";
                sql4 = " SELECT COUNT(*) aa FROM UM_ENTER_STATE WHERE BIZ_REG_NO = '" + UnpairNo + "' AND STATE_CLS = '05'";
                try {
                    if (rs2 != null) {
                        rs2.close();
                    }
                }
                catch (Exception ex4) {}
                try {
                    if (pstmt2 != null) {
                        pstmt2.close();
                    }
                }
                catch (Exception ex5) {}
                pstmt2 = pconn.prepareStatement(sql3);
                rs2 = pstmt2.executeQuery();
                if (rs2.next()) {
                    date = rs2.getString(1);
                }
                try {
                    if (rs3 != null) {
                        rs3.close();
                    }
                }
                catch (Exception ex6) {}
                try {
                    if (pstmt3 != null) {
                        pstmt3.close();
                    }
                }
                catch (Exception ex7) {}
                pstmt3 = pconn.prepareStatement(sql4);
                rs3 = pstmt3.executeQuery();
                if (rs3.next()) {
                    sangtaeCount = rs3.getString(1);
                }
                if (Integer.parseInt(RestartDate) <= Integer.parseInt(nowDate)) {
                    if (sangtaeCount.equals("0")) {
                        if (Integer.parseInt(this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0)) > Integer.parseInt(nowDate)) {
                            continue;
                        }
                        query = " INSERT INTO UM_ENTER_STATE (  BIZ_REG_NO, RAISED_DT, START_DT, END_DT, STATE_CLS, REMARK, MANAGER_ID, PROCESS_DT\t\n ) VALUES ( \n'" + UnpairNo + "', \n" + "SYSDATE, \n " + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0) + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0) + "', '05', \n" + "'" + Gita + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + "sysdate)";
                        try {
                            if (pstmt4 != null) {
                                pstmt4.close();
                            }
                        }
                        catch (Exception ex8) {}
                        pstmt4 = pconn.prepareStatement(query);
                        pstmt4.executeUpdate();
                        this.SaveLog("UM_ENTER_STATE 재개 입력 : BIZ_REG_NO[" + UnpairNo + "]");
                    }
                    else {
                        query = " SELECT TO_CHAR(START_DT, 'yyyymmdd'), TO_CHAR(END_DT, 'yyyymmdd')  FROM UM_ENTER_STATE  WHERE BIZ_REG_NO = '" + UnpairNo + "' " + "   AND STATE_CLS = '05' ";
                        try {
                            if (rs4 != null) {
                                rs4.close();
                            }
                        }
                        catch (Exception ex9) {}
                        try {
                            if (pstmt5 != null) {
                                pstmt5.close();
                            }
                        }
                        catch (Exception ex10) {}
                        pstmt5 = pconn.prepareStatement(query);
                        rs4 = pstmt5.executeQuery();
                        if (rs4.next()) {
                            sDate2 = rs4.getString(1);
                            eDate2 = rs4.getString(2);
                        }
                        if (Integer.parseInt(this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0)) <= Integer.parseInt(sDate2)) {
                            sDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0);
                        }
                        else {
                            sDate = sDate2;
                        }
                        if (Integer.parseInt(eDate2) <= Integer.parseInt(this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0))) {
                            eDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0);
                        }
                        else {
                            eDate = eDate2;
                        }
                        if (Integer.parseInt(eDate) < Integer.parseInt(nowDate)) {
                            query = " DELETE FROM UM_ENTER_STATE \n\tWHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                            try {
                                if (pstmt6 != null) {
                                    pstmt6.close();
                                }
                            }
                            catch (Exception ex11) {}
                            pstmt6 = pconn.prepareStatement(query);
                            pstmt6.executeUpdate();
                            this.SaveLog("UM_ENTER_STATE 재개 삭제 : BIZ_REG_NO[" + UnpairNo + "]");
                        }
                        else {
                            query = " UPDATE UM_ENTER_STATE SET \n RAISED_DT = SYSDATE, \n START_DT = '" + sDate + "', \n" + " END_DT = '" + eDate + "', \n" + " REMARK     = '" + Gita + "', \n" + " MANAGER_ID = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + " PROCESS_DT = sysdate \n" + " WHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                            try {
                                if (pstmt6 != null) {
                                    pstmt6.close();
                                }
                            }
                            catch (Exception ex12) {}
                            pstmt6 = pconn.prepareStatement(query);
                            pstmt6.executeUpdate();
                            this.SaveLog("UM_ENTER_STATE 재개 수정 : BIZ_REG_NO[" + UnpairNo + "]");
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            this.SaveLog(" updateDetailBR() : " + ex.toString());
            throw ex;
        }
        finally {
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
            try {
                if (rs2 != null) {
                    rs2.close();
                }
            }
            catch (Exception ex15) {}
            try {
                if (pstmt2 != null) {
                    pstmt2.close();
                }
            }
            catch (Exception ex16) {}
            try {
                if (rs3 != null) {
                    rs3.close();
                }
            }
            catch (Exception ex17) {}
            try {
                if (pstmt3 != null) {
                    pstmt3.close();
                }
            }
            catch (Exception ex18) {}
            try {
                if (pstmt4 != null) {
                    pstmt4.close();
                }
            }
            catch (Exception ex19) {}
            try {
                if (rs4 != null) {
                    rs4.close();
                }
            }
            catch (Exception ex20) {}
            try {
                if (pstmt5 != null) {
                    pstmt5.close();
                }
            }
            catch (Exception ex21) {}
            try {
                if (pstmt6 != null) {
                    pstmt6.close();
                }
            }
            catch (Exception ex22) {}
        }
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception ex23) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex24) {}
        try {
            if (rs2 != null) {
                rs2.close();
            }
        }
        catch (Exception ex25) {}
        try {
            if (pstmt2 != null) {
                pstmt2.close();
            }
        }
        catch (Exception ex26) {}
        try {
            if (rs3 != null) {
                rs3.close();
            }
        }
        catch (Exception ex27) {}
        try {
            if (pstmt3 != null) {
                pstmt3.close();
            }
        }
        catch (Exception ex28) {}
        try {
            if (pstmt4 != null) {
                pstmt4.close();
            }
        }
        catch (Exception ex29) {}
        try {
            if (rs4 != null) {
                rs4.close();
            }
        }
        catch (Exception ex30) {}
        try {
            if (pstmt5 != null) {
                pstmt5.close();
            }
        }
        catch (Exception ex31) {}
        try {
            if (pstmt6 != null) {
                pstmt6.close();
            }
        }
        catch (Exception ex32) {}
    }
    
    private void updateDetailBRCEO(final Connection pconn) throws Exception {
        final String sql2 = "";
        String date = "";
        String sDate = "";
        String eDate = "";
        String sDate2 = "";
        String eDate2 = "";
        final String endDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0);
        final String unpairCount = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Number.Text/Text.Content", 0);
        final String RestartDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Restart.Dateils/Sanction.Restart.Date/DateTime.Content", 0);
        final XMLElement xemt = (XMLElement)this.xmlDoc.getDocumentElement();
        final NodeList pNode = this.xmlDoc.selectNodes("/gb:Issrea/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", (NSResolver)xemt);
        final String SaupNo = this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        final String Gita = String.valueOf(SaupNo) + "/" + unpairCount;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        PreparedStatement pstmt3 = null;
        ResultSet rs3 = null;
        PreparedStatement pstmt4 = null;
        PreparedStatement pstmt5 = null;
        ResultSet rs4 = null;
        PreparedStatement pstmt6 = null;
        String query = null;
        String nowDate = "";
        try {
            query = " SELECT TO_CHAR(sysdate, 'yyyymmdd') FROM dual ";
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nowDate = rs.getString(1);
            }
            String sangtaeCount = "";
            String UnpairNo = "";
            String sql3 = "";
            String sql4 = "";
            for (int i = 0; i < pNode.getLength(); ++i) {
                query = "\t\tSELECT BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFROM  UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM UM_SUPPLIER_ENTER_MAST x, UM_REPR y        \t\t\t\t\t\t\t\t\t\t\t  WHERE x.BIZ_REG_NO = y.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t  AND  y.REPR_IDENT_NO = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", i) + "' \n" + "\t\t\t\t\t\t\t\t\t\t\t)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                try {
                    if (rs != null) {
                        rs.close();
                    }
                }
                catch (Exception ex2) {}
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                }
                catch (Exception ex3) {}
                pstmt = pconn.prepareStatement(query);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    UnpairNo = rs.getString("BIZ_REG_NO");
                    sql3 = " SELECT To_Char(END_DT, 'YYYYMMDD') FROM UM_ENTER_STATE WHERE BIZ_REG_NO = '" + UnpairNo + "' AND STATE_CLS = '05'";
                    sql4 = " SELECT COUNT(*) aa FROM UM_ENTER_STATE WHERE BIZ_REG_NO = '" + UnpairNo + "' AND STATE_CLS = '05'";
                    try {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    catch (Exception ex4) {}
                    try {
                        if (pstmt2 != null) {
                            pstmt2.close();
                        }
                    }
                    catch (Exception ex5) {}
                    pstmt2 = pconn.prepareStatement(sql3);
                    rs2 = pstmt2.executeQuery();
                    if (rs2.next()) {
                        date = rs2.getString(1);
                    }
                    try {
                        if (rs3 != null) {
                            rs3.close();
                        }
                    }
                    catch (Exception ex6) {}
                    try {
                        if (pstmt3 != null) {
                            pstmt3.close();
                        }
                    }
                    catch (Exception ex7) {}
                    pstmt3 = pconn.prepareStatement(sql4);
                    rs3 = pstmt3.executeQuery();
                    if (rs3.next()) {
                        sangtaeCount = rs3.getString(1);
                    }
                    if (Integer.parseInt(RestartDate) <= Integer.parseInt(nowDate)) {
                        if (sangtaeCount.equals("0")) {
                            if (Integer.parseInt(this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0)) > Integer.parseInt(nowDate)) {
                                continue;
                            }
                            query = " INSERT INTO UM_ENTER_STATE (  BIZ_REG_NO, RAISED_DT, START_DT, END_DT, STATE_CLS, REMARK, MANAGER_ID, PROCESS_DT\t\n ) VALUES ( \n'" + UnpairNo + "', \n" + "SYSDATE, \n " + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0) + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0) + "', '05', \n" + "'" + Gita + "', \n" + "'" + this.GetValue(this.xmlDoc, "/gb:Issrea/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + "sysdate)";
                            try {
                                if (pstmt4 != null) {
                                    pstmt4.close();
                                }
                            }
                            catch (Exception ex8) {}
                            pstmt4 = pconn.prepareStatement(query);
                            pstmt4.executeUpdate();
                            this.SaveLog("UM_ENTER_STATE 재개 입력 : BIZ_REG_NO[" + UnpairNo + "]");
                        }
                        else {
                            query = " SELECT TO_CHAR(START_DT, 'yyyymmdd'), TO_CHAR(END_DT, 'yyyymmdd')  FROM UM_ENTER_STATE  WHERE BIZ_REG_NO = '" + UnpairNo + "' " + "   AND STATE_CLS = '05' ";
                            try {
                                if (rs4 != null) {
                                    rs4.close();
                                }
                            }
                            catch (Exception ex9) {}
                            try {
                                if (pstmt5 != null) {
                                    pstmt5.close();
                                }
                            }
                            catch (Exception ex10) {}
                            pstmt5 = pconn.prepareStatement(query);
                            rs4 = pstmt5.executeQuery();
                            if (rs4.next()) {
                                sDate2 = rs4.getString(1);
                                eDate2 = rs4.getString(2);
                            }
                            if (Integer.parseInt(this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0)) <= Integer.parseInt(sDate2)) {
                                sDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Sanction.Date/DateTime.Content", 0);
                            }
                            else {
                                sDate = sDate2;
                            }
                            if (Integer.parseInt(eDate2) <= Integer.parseInt(this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0))) {
                                eDate = this.GetValue(this.xmlDoc, "/gb:Issrea/Sanction.Details/Expiration.Date/DateTime.Content", 0);
                            }
                            else {
                                eDate = eDate2;
                            }
                            if (Integer.parseInt(eDate) < Integer.parseInt(nowDate)) {
                                query = " DELETE FROM UM_ENTER_STATE \n\tWHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                                try {
                                    if (pstmt6 != null) {
                                        pstmt6.close();
                                    }
                                }
                                catch (Exception ex11) {}
                                pstmt6 = pconn.prepareStatement(query);
                                pstmt6.executeUpdate();
                                this.SaveLog("UM_ENTER_STATE 재개 삭제 : BIZ_REG_NO[" + UnpairNo + "]");
                            }
                            else {
                                query = " UPDATE UM_ENTER_STATE SET \n RAISED_DT = SYSDATE, \n START_DT = '" + sDate + "', \n" + " END_DT = '" + eDate + "', \n" + " REMARK     = '" + Gita + "', \n" + " MANAGER_ID = '" + this.GetValue(this.xmlDoc, "/gb:Issrea/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + " PROCESS_DT = sysdate \n" + " WHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                                try {
                                    if (pstmt6 != null) {
                                        pstmt6.close();
                                    }
                                }
                                catch (Exception ex12) {}
                                pstmt6 = pconn.prepareStatement(query);
                                pstmt6.executeUpdate();
                                this.SaveLog("UM_ENTER_STATE 재개 수정 : BIZ_REG_NO[" + UnpairNo + "]");
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            this.SaveLog(" updateDetailBRCEO() : " + ex.toString());
            throw ex;
        }
        finally {
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
            try {
                if (rs2 != null) {
                    rs2.close();
                }
            }
            catch (Exception ex15) {}
            try {
                if (pstmt2 != null) {
                    pstmt2.close();
                }
            }
            catch (Exception ex16) {}
            try {
                if (rs3 != null) {
                    rs3.close();
                }
            }
            catch (Exception ex17) {}
            try {
                if (pstmt3 != null) {
                    pstmt3.close();
                }
            }
            catch (Exception ex18) {}
            try {
                if (pstmt4 != null) {
                    pstmt4.close();
                }
            }
            catch (Exception ex19) {}
            try {
                if (rs4 != null) {
                    rs4.close();
                }
            }
            catch (Exception ex20) {}
            try {
                if (pstmt5 != null) {
                    pstmt5.close();
                }
            }
            catch (Exception ex21) {}
            try {
                if (pstmt6 != null) {
                    pstmt6.close();
                }
            }
            catch (Exception ex22) {}
        }
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception ex23) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex24) {}
        try {
            if (rs2 != null) {
                rs2.close();
            }
        }
        catch (Exception ex25) {}
        try {
            if (pstmt2 != null) {
                pstmt2.close();
            }
        }
        catch (Exception ex26) {}
        try {
            if (rs3 != null) {
                rs3.close();
            }
        }
        catch (Exception ex27) {}
        try {
            if (pstmt3 != null) {
                pstmt3.close();
            }
        }
        catch (Exception ex28) {}
        try {
            if (pstmt4 != null) {
                pstmt4.close();
            }
        }
        catch (Exception ex29) {}
        try {
            if (rs4 != null) {
                rs4.close();
            }
        }
        catch (Exception ex30) {}
        try {
            if (pstmt5 != null) {
                pstmt5.close();
            }
        }
        catch (Exception ex31) {}
        try {
            if (pstmt6 != null) {
                pstmt6.close();
            }
        }
        catch (Exception ex32) {}
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
        Log.info("ISSREA" + this.xmlFileName + this.sourceName + "[" + this.serialNumber + "]:" + msg);
    }
}
