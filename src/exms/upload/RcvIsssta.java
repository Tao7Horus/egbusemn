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

public class RcvIsssta
{
    Connection pconn;
    String xmlFileName;
    DOMParser parser;
    XMLDocument xmlDoc;
    private long serialNumber;
    private String sourceName;
    
    public RcvIsssta(final Connection conn) {
        this.pconn = null;
        this.parser = new DOMParser();
        this.xmlDoc = new XMLDocument();
        this.serialNumber = 0L;
        this.sourceName = "RcvIsssta";
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
            this.SaveLog("## updateMaster1 start");
            this.updateMaster1(this.pconn);
            this.SaveLog("## updateMaster1 end");
            this.SaveLog("## updateMaster2 start");
            this.updateMaster2(this.pconn);
            this.SaveLog("## updateMaster2 end");
            this.SaveLog("## updateDetail start");
            this.updateDetail(this.pconn);
            this.SaveLog("## updateDetail end");
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
            this.SaveLog("## main_process end");
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
                return "-2[RcvIsssta - 부정당업자 제재정지]oracle error 0001";
            }
            return String.valueOf(e.getErrorCode()) + "[RcvIsssta - 부정당업자 제재정지]oracle error " + e.getErrorCode() + ":" + e.toString();
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
            return "-2[RcvIsssta - 부정당업자 제재정지]DB Update Error" + e2.getMessage();
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
        final String saupjaNumber = this.GetValue(this.xmlDoc, "/gb:Isssta/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        final String restrictCount = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Stop.Details/Sanction.Number.Text/Text.Content", 0);
        try {
            query = " UPDATE  UM_VIOLATE_COM SET                     \n          DOC_NO = '" + this.GetValue(this.xmlDoc, "/gb:Isssta/Header.Details/cc:Document.Number.Text/Text.Content", 0) + "',            \n" + "         SUSPEND_CLS = 'Y',                         \n" + "         RESUMPTION_CLS = 'N',                         \n" + "         SUSPEND_DT = '" + this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Stop.Details/Sanction.Stop.Date/DateTime.Content", 0) + "',      \n" + "         SUSPEND_RSON = '" + this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Stop.Details/Sanction.Reason.Text/Text.Content", 0) + "',        \n" + "         SUSPEND_UM_ID = '" + this.GetValue(this.xmlDoc, "/gb:Isssta/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "'  \n" + " WHERE    \t\t\t\t\t\t\t  \n" + "         BIZ_REG_NO = '" + saupjaNumber + "'  \n" + "   AND   PUNISH_COUNT       = '" + restrictCount + "'";
            pstmt = pconn.prepareStatement(query);
            pstmt.executeUpdate();
            this.SaveLog("UM_VIOLATE_COM update BIZ_REG_NO[" + saupjaNumber + "],PUNISH_COUNT[" + restrictCount + "]");
        }
        catch (Exception exm) {
            this.SaveLog("RcvIsssta.updateMaster1() query[" + query + "] :" + exm.toString());
            throw exm;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    private void updateMaster2(final Connection pconn) throws Exception {
        final String endDate = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Details/Expiration.Date/DateTime.Content", 0);
        final String SaupNo = this.GetValue(this.xmlDoc, "/gb:Isssta/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        final String count = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Stop.Details/Sanction.Number.Text/Text.Content", 0);
        final String SuspendDate = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Stop.Details/Sanction.Stop.Date/DateTime.Content", 0);
        final String Gita = String.valueOf(SaupNo) + "/" + count;
        String nowDate = "";
        String sDate = "";
        String eDate = "";
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String count2 = "0";
        try {
            query = " SELECT TO_CHAR(sysdate, 'yyyymmdd') FROM dual ";
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nowDate = rs.getString(1);
            }
            query = " SELECT COUNT(*)  FROM UM_VIOLATE_COM a, UM_SUPPLIER_ENTER_MAST b  WHERE a.BIZ_REG_NO = '" + SaupNo + "' " + "   AND a.INSTITU_CD NOT IN ('" + this.GetValue(this.xmlDoc, "/gb:Isssta/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "') " + "   AND a.BIZ_REG_NO = b.BIZ_REG_NO " + "   AND (a.SUSPEND_CLS = 'N' OR (a.SUSPEND_CLS = 'Y' AND a.RESUMPTION_CLS = 'Y')) " + "   AND a.RELEASE_CLS = 'N' " + "   AND TO_CHAR(a.EXPIRE_DT, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd') " + "   AND TO_CHAR(a.PUNISH_DT, 'yyyy-mm-dd') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd') ";
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
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count2 = rs.getString(1);
            }
            query = " SELECT TO_CHAR(MIN(a.PUNISH_DT), 'YYYYMMDD'), TO_CHAR(MAX(a.EXPIRE_DT), 'YYYYMMDD')  FROM UM_VIOLATE_COM a, UM_SUPPLIER_ENTER_MAST b  WHERE a.BIZ_REG_NO = '" + SaupNo + "' " + "   AND a.INSTITU_CD NOT IN ('" + this.GetValue(this.xmlDoc, "/gb:Isssta/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "') " + "   AND a.BIZ_REG_NO = b.BIZ_REG_NO " + "   AND (a.SUSPEND_CLS = 'N' OR (a.SUSPEND_CLS = 'Y' AND a.RESUMPTION_CLS = 'Y')) " + "   AND a.RELEASE_CLS = 'N' " + "   AND TO_CHAR(a.EXPIRE_DT, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd') " + "   AND TO_CHAR(a.PUNISH_DT, 'yyyy-mm-dd') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd') ";
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
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sDate = rs.getString(1);
                eDate = rs.getString(2);
            }
            if (sDate == null) {
                sDate = "";
            }
            if (eDate == null) {
                eDate = "";
            }
            if (Integer.parseInt(SuspendDate) <= Integer.parseInt(nowDate)) {
                if (count2.equals("0")) {
                    query = " DELETE FROM UM_ENTER_STATE \n\tWHERE BIZ_REG_NO = '" + SaupNo + "' \n" + "   AND STATE_CLS = '05'";
                    try {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                    }
                    catch (Exception ex5) {}
                    pstmt = pconn.prepareStatement(query);
                    pstmt.executeUpdate();
                    this.SaveLog("UM_ENTER_STATE 정지 삭제 : BIZ_REG_NO[" + SaupNo + "]");
                }
                else if (!sDate.equals("") && !eDate.equals("")) {
                    query = " UPDATE UM_ENTER_STATE SET \n RAISED_DT = SYSDATE, \n START_DT = '" + sDate + "', \n" + " END_DT = '" + eDate + "', \n" + " REMARK     = '" + Gita + "', \n" + " MANAGER_ID = '" + this.GetValue(this.xmlDoc, "/gb:Isssta/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + " PROCESS_DT = sysdate \n" + " WHERE BIZ_REG_NO = '" + SaupNo + "' \n" + "   AND STATE_CLS = '05'";
                    try {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                    }
                    catch (Exception ex6) {}
                    pstmt = pconn.prepareStatement(query);
                    pstmt.executeUpdate();
                    this.SaveLog("UM_ENTER_STATE 정지 수정 : BIZ_REG_NO[" + SaupNo + "]");
                }
            }
        }
        catch (Exception exm) {
            this.SaveLog(" updateMaster2() :" + exm.toString());
            throw exm;
        }
        finally {
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
        }
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
    }
    
    private void updateDetail(final Connection pconn) throws Exception {
        final String date = "";
        final String endDate = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Details/Expiration.Date/DateTime.Content", 0);
        final String unpairCount = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Stop.Details/Sanction.Number.Text/Text.Content", 0);
        final String SaupNo = this.GetValue(this.xmlDoc, "/gb:Isssta/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        final String SuspendDate = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Stop.Details/Sanction.Stop.Date/DateTime.Content", 0);
        final String Gita = String.valueOf(SaupNo) + "/" + unpairCount;
        final XMLElement xemt = (XMLElement)this.xmlDoc.getDocumentElement();
        final NodeList pNode = this.xmlDoc.selectNodes("/gb:Isssta/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", (NSResolver)xemt);
        String nowDate = "";
        String sDate = "";
        String eDate = "";
        String count4 = "";
        String sql1 = "";
        String sql2 = "";
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        PreparedStatement pstmt3 = null;
        try {
            query = " SELECT TO_CHAR(sysdate, 'yyyymmdd') FROM dual ";
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nowDate = rs.getString(1);
            }
            query = " SELECT COUNT(*)  FROM UM_VIOLATE_COM a, UM_SUPPLIER_ENTER_MAST b  WHERE a.BIZ_REG_NO = '" + SaupNo + "' " + "   AND a.INSTITU_CD NOT IN ('" + this.GetValue(this.xmlDoc, "/gb:Isssta/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "') " + "   AND a.BIZ_REG_NO = b.BIZ_REG_NO " + "   AND (a.SUSPEND_CLS = 'N' OR (a.SUSPEND_CLS = 'Y' AND a.RESUMPTION_CLS = 'Y')) " + "   AND a.RELEASE_CLS = 'N' " + "   AND TO_CHAR(a.EXPIRE_DT, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd') " + "   AND TO_CHAR(a.PUNISH_DT, 'yyyy-mm-dd') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd') ";
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
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count4 = rs.getString(1);
            }
            query = " SELECT TO_CHAR(MIN(a.PUNISH_DT), 'YYYYMMDD'), TO_CHAR(MAX(a.EXPIRE_DT), 'YYYYMMDD') \n FROM UM_VIOLATE_COM a, UM_SUPPLIER_ENTER_MAST b \n WHERE a.BIZ_REG_NO = '" + SaupNo + "' \n" + "   AND a.INSTITU_CD NOT IN ('" + this.GetValue(this.xmlDoc, "/gb:Isssta/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "') \n" + "   AND a.BIZ_REG_NO = b.BIZ_REG_NO \n" + "   AND (a.SUSPEND_CLS = 'N' OR (a.SUSPEND_CLS = 'Y' AND a.RESUMPTION_CLS = 'Y')) \n" + "   AND a.RELEASE_CLS = 'N' \n" + "   AND TO_CHAR(a.EXPIRE_DT, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd') \n" + "   AND TO_CHAR(a.PUNISH_DT, 'yyyy-mm-dd') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd') ";
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
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sDate = rs.getString(1);
                eDate = rs.getString(2);
            }
            if (sDate == null) {
                sDate = "";
            }
            if (eDate == null) {
                eDate = "";
            }
            String sangtaeCount = "";
            String UnpairNo = "";
            for (int i = 0; i < pNode.getLength(); ++i) {
                sql2 = " SELECT BIZ_REG_NO FROM UM_REPR \n WHERE REPR_IDENT_NO = '" + this.GetValue(this.xmlDoc, "/gb:Isssta/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", i) + "' \n" + "   AND BIZ_REG_NO NOT IN ('" + SaupNo + "') ";
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
                pstmt = pconn.prepareStatement(sql2);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    UnpairNo = rs.getString("BIZ_REG_NO");
                    sql1 = " SELECT COUNT(*) aa FROM UM_ENTER_STATE WHERE BIZ_REG_NO = '" + UnpairNo + "' AND STATE_CLS = '05'";
                    try {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    catch (Exception ex7) {}
                    try {
                        if (pstmt2 != null) {
                            pstmt2.close();
                        }
                    }
                    catch (Exception ex8) {}
                    pstmt2 = pconn.prepareStatement(sql1);
                    rs2 = pstmt2.executeQuery();
                    if (rs2.next()) {
                        sangtaeCount = rs2.getString(1);
                    }
                    if (!sangtaeCount.equals("0") && Integer.parseInt(SuspendDate) <= Integer.parseInt(nowDate)) {
                        if (count4.equals("0")) {
                            query = " DELETE FROM UM_ENTER_STATE \n WHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                            try {
                                if (pstmt3 != null) {
                                    pstmt3.close();
                                }
                            }
                            catch (Exception ex9) {}
                            pstmt3 = pconn.prepareStatement(query);
                            pstmt3.executeUpdate();
                            this.SaveLog("UM_ENTER_STATE 정지 삭제 : BIZ_REG_NO[" + UnpairNo + "]");
                        }
                        else {
                            if (sDate.equals("") || eDate.equals("")) {
                                continue;
                            }
                            query = " UPDATE UM_ENTER_STATE SET \n RAISED_DT = SYSDATE, \n START_DT = '" + sDate + "', \n" + " END_DT = '" + eDate + "', \n" + " REMARK     = '" + Gita + "', \n" + " MANAGER_ID = '" + this.GetValue(this.xmlDoc, "/gb:Isssta/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + " PROCESS_DT = sysdate \n" + " WHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                            try {
                                if (pstmt3 != null) {
                                    pstmt3.close();
                                }
                            }
                            catch (Exception ex10) {}
                            pstmt3 = pconn.prepareStatement(query);
                            pstmt3.executeUpdate();
                            this.SaveLog("UM_ENTER_STATE 정지 수정 : BIZ_REG_NO[" + UnpairNo + "]");
                        }
                    }
                }
            }
        }
        catch (Exception exe) {
            this.SaveLog(" updateDetail() :" + exe.toString());
            throw exe;
        }
        finally {
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
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex13) {}
            }
            if (pstmt2 != null) {
                try {
                    pstmt2.close();
                }
                catch (Exception ex14) {}
            }
            if (pstmt3 != null) {
                try {
                    pstmt3.close();
                }
                catch (Exception ex15) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex16) {}
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex17) {}
        }
        if (rs2 != null) {
            try {
                rs2.close();
            }
            catch (Exception ex18) {}
        }
        if (pstmt2 != null) {
            try {
                pstmt2.close();
            }
            catch (Exception ex19) {}
        }
        if (pstmt3 != null) {
            try {
                pstmt3.close();
            }
            catch (Exception ex20) {}
        }
    }
    
    private void updateDetailBR(final Connection pconn) throws Exception {
        final String date = "";
        final String endDate = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Details/Expiration.Date/DateTime.Content", 0);
        final String unpairCount = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Stop.Details/Sanction.Number.Text/Text.Content", 0);
        final String SaupNo = this.GetValue(this.xmlDoc, "/gb:Isssta/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        final String SuspendDate = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Stop.Details/Sanction.Stop.Date/DateTime.Content", 0);
        final String Gita = String.valueOf(SaupNo) + "/" + unpairCount;
        final XMLElement xemt = (XMLElement)this.xmlDoc.getDocumentElement();
        final NodeList pNode = this.xmlDoc.selectNodes("/gb:Isssta/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", (NSResolver)xemt);
        String nowDate = "";
        String sDate = "";
        String eDate = "";
        String count4 = "";
        String sql1 = "";
        String sql2 = "";
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        PreparedStatement pstmt3 = null;
        try {
            query = " SELECT TO_CHAR(sysdate, 'yyyymmdd') FROM dual ";
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nowDate = rs.getString(1);
            }
            query = " SELECT COUNT(*)  FROM UM_VIOLATE_COM a, UM_SUPPLIER_ENTER_MAST b  WHERE a.BIZ_REG_NO = '" + SaupNo + "' " + "   AND a.INSTITU_CD NOT IN ('" + this.GetValue(this.xmlDoc, "/gb:Isssta/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "') " + "   AND a.BIZ_REG_NO = b.BIZ_REG_NO " + "   AND (a.SUSPEND_CLS = 'N' OR (a.SUSPEND_CLS = 'Y' AND a.RESUMPTION_CLS = 'Y')) " + "   AND a.RELEASE_CLS = 'N' " + "   AND TO_CHAR(a.EXPIRE_DT, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd') " + "   AND TO_CHAR(a.PUNISH_DT, 'yyyy-mm-dd') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd') ";
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
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count4 = rs.getString(1);
            }
            query = " SELECT TO_CHAR(MIN(a.PUNISH_DT), 'YYYYMMDD'), TO_CHAR(MAX(a.EXPIRE_DT), 'YYYYMMDD') \n FROM UM_VIOLATE_COM a, UM_SUPPLIER_ENTER_MAST b \n WHERE a.BIZ_REG_NO = '" + SaupNo + "' \n" + "   AND a.INSTITU_CD NOT IN ('" + this.GetValue(this.xmlDoc, "/gb:Isssta/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "') \n" + "   AND a.BIZ_REG_NO = b.BIZ_REG_NO \n" + "   AND (a.SUSPEND_CLS = 'N' OR (a.SUSPEND_CLS = 'Y' AND a.RESUMPTION_CLS = 'Y')) \n" + "   AND a.RELEASE_CLS = 'N' \n" + "   AND TO_CHAR(a.EXPIRE_DT, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd') \n" + "   AND TO_CHAR(a.PUNISH_DT, 'yyyy-mm-dd') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd') ";
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
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sDate = rs.getString(1);
                eDate = rs.getString(2);
            }
            if (sDate == null) {
                sDate = "";
            }
            if (eDate == null) {
                eDate = "";
            }
            String sangtaeCount = "";
            String UnpairNo = "";
            sql2 = "  SELECT BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM  UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t                                        FROM UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t                                        WHERE BIZ_REG_NO  = '" + SaupNo + "')\t\t\t" + "  AND BIZ_REG_NO NOT IN ('" + SaupNo + "')\t\t\t\t\t\t\t\t\t\t\t";
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
            pstmt = pconn.prepareStatement(sql2);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                UnpairNo = rs.getString("BIZ_REG_NO");
                sql1 = " SELECT COUNT(*) aa FROM UM_ENTER_STATE WHERE BIZ_REG_NO = '" + UnpairNo + "' AND STATE_CLS = '05'";
                try {
                    if (rs2 != null) {
                        rs2.close();
                    }
                }
                catch (Exception ex7) {}
                try {
                    if (pstmt2 != null) {
                        pstmt2.close();
                    }
                }
                catch (Exception ex8) {}
                pstmt2 = pconn.prepareStatement(sql1);
                rs2 = pstmt2.executeQuery();
                if (rs2.next()) {
                    sangtaeCount = rs2.getString(1);
                }
                if (!sangtaeCount.equals("0") && Integer.parseInt(SuspendDate) <= Integer.parseInt(nowDate)) {
                    if (count4.equals("0")) {
                        query = " DELETE FROM UM_ENTER_STATE \n WHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                        try {
                            if (pstmt3 != null) {
                                pstmt3.close();
                            }
                        }
                        catch (Exception ex9) {}
                        pstmt3 = pconn.prepareStatement(query);
                        pstmt3.executeUpdate();
                        this.SaveLog("UM_ENTER_STATE 정지 삭제 : BIZ_REG_NO[" + UnpairNo + "]");
                    }
                    else {
                        if (sDate.equals("") || eDate.equals("")) {
                            continue;
                        }
                        query = " UPDATE UM_ENTER_STATE SET \n RAISED_DT = SYSDATE, \n START_DT = '" + sDate + "', \n" + " END_DT = '" + eDate + "', \n" + " REMARK     = '" + Gita + "', \n" + " MANAGER_ID = '" + this.GetValue(this.xmlDoc, "/gb:Isssta/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + " PROCESS_DT = sysdate \n" + " WHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                        try {
                            if (pstmt3 != null) {
                                pstmt3.close();
                            }
                        }
                        catch (Exception ex10) {}
                        pstmt3 = pconn.prepareStatement(query);
                        pstmt3.executeUpdate();
                        this.SaveLog("UM_ENTER_STATE 정지 수정 : BIZ_REG_NO[" + UnpairNo + "]");
                    }
                }
            }
        }
        catch (Exception exe) {
            this.SaveLog(" updateDetailBR() :" + exe.toString());
            throw exe;
        }
        finally {
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
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex13) {}
            }
            if (pstmt2 != null) {
                try {
                    pstmt2.close();
                }
                catch (Exception ex14) {}
            }
            if (pstmt3 != null) {
                try {
                    pstmt3.close();
                }
                catch (Exception ex15) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex16) {}
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex17) {}
        }
        if (rs2 != null) {
            try {
                rs2.close();
            }
            catch (Exception ex18) {}
        }
        if (pstmt2 != null) {
            try {
                pstmt2.close();
            }
            catch (Exception ex19) {}
        }
        if (pstmt3 != null) {
            try {
                pstmt3.close();
            }
            catch (Exception ex20) {}
        }
    }
    
    private void updateDetailBRCEO(final Connection pconn) throws Exception {
        final String date = "";
        final String endDate = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Details/Expiration.Date/DateTime.Content", 0);
        final String unpairCount = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Stop.Details/Sanction.Number.Text/Text.Content", 0);
        final String SaupNo = this.GetValue(this.xmlDoc, "/gb:Isssta/Organization.Information/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0);
        final String SuspendDate = this.GetValue(this.xmlDoc, "/gb:Isssta/Sanction.Stop.Details/Sanction.Stop.Date/DateTime.Content", 0);
        final String Gita = String.valueOf(SaupNo) + "/" + unpairCount;
        final XMLElement xemt = (XMLElement)this.xmlDoc.getDocumentElement();
        final NodeList pNode = this.xmlDoc.selectNodes("/gb:Isssta/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", (NSResolver)xemt);
        String nowDate = "";
        String sDate = "";
        String eDate = "";
        String count4 = "";
        String sql1 = "";
        String sql2 = "";
        String query = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs2 = null;
        PreparedStatement pstmt3 = null;
        try {
            query = " SELECT TO_CHAR(sysdate, 'yyyymmdd') FROM dual ";
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                nowDate = rs.getString(1);
            }
            query = " SELECT COUNT(*)  FROM UM_VIOLATE_COM a, UM_SUPPLIER_ENTER_MAST b  WHERE a.BIZ_REG_NO = '" + SaupNo + "' " + "   AND a.INSTITU_CD NOT IN ('" + this.GetValue(this.xmlDoc, "/gb:Isssta/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "') " + "   AND a.BIZ_REG_NO = b.BIZ_REG_NO " + "   AND (a.SUSPEND_CLS = 'N' OR (a.SUSPEND_CLS = 'Y' AND a.RESUMPTION_CLS = 'Y')) " + "   AND a.RELEASE_CLS = 'N' " + "   AND TO_CHAR(a.EXPIRE_DT, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd') " + "   AND TO_CHAR(a.PUNISH_DT, 'yyyy-mm-dd') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd') ";
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
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count4 = rs.getString(1);
            }
            query = " SELECT TO_CHAR(MIN(a.PUNISH_DT), 'YYYYMMDD'), TO_CHAR(MAX(a.EXPIRE_DT), 'YYYYMMDD') \n FROM UM_VIOLATE_COM a, UM_SUPPLIER_ENTER_MAST b \n WHERE a.BIZ_REG_NO = '" + SaupNo + "' \n" + "   AND a.INSTITU_CD NOT IN ('" + this.GetValue(this.xmlDoc, "/gb:Isssta/PublicOrganization.Details/Organization.Details/cc:Organization.Identifier/Identifier.Content", 0) + "') \n" + "   AND a.BIZ_REG_NO = b.BIZ_REG_NO \n" + "   AND (a.SUSPEND_CLS = 'N' OR (a.SUSPEND_CLS = 'Y' AND a.RESUMPTION_CLS = 'Y')) \n" + "   AND a.RELEASE_CLS = 'N' \n" + "   AND TO_CHAR(a.EXPIRE_DT, 'yyyy-mm-dd') >= TO_CHAR(SYSDATE, 'yyyy-mm-dd') \n" + "   AND TO_CHAR(a.PUNISH_DT, 'yyyy-mm-dd') <= TO_CHAR(SYSDATE, 'yyyy-mm-dd') ";
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
            pstmt = pconn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sDate = rs.getString(1);
                eDate = rs.getString(2);
            }
            if (sDate == null) {
                sDate = "";
            }
            if (eDate == null) {
                eDate = "";
            }
            String sangtaeCount = "";
            String UnpairNo = "";
            for (int i = 0; i < pNode.getLength(); ++i) {
                sql2 = "\t\tSELECT BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFROM  UM_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM UM_SUPPLIER_ENTER_MAST x, UM_REPR y        \t\t\t\t\t\t\t\t\t\t\t  WHERE x.BIZ_REG_NO = y.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t  AND  y.REPR_IDENT_NO = '" + this.GetValue(this.xmlDoc, "/gb:Isssta/Organization.Information/CeoList/CeoItem/cc:Organization.CEO.Identifier/Identifier.Content", i) + "' \n" + "\t\t\t\t\t\t\t\t\t\t\t)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "       AND BIZ_REG_NO NOT IN ('" + SaupNo + "')\t\t\t\t\t\t\t\t\t\t\t\t";
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
                pstmt = pconn.prepareStatement(sql2);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    UnpairNo = rs.getString("BIZ_REG_NO");
                    sql1 = " SELECT COUNT(*) aa FROM UM_ENTER_STATE WHERE BIZ_REG_NO = '" + UnpairNo + "' AND STATE_CLS = '05'";
                    try {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    catch (Exception ex7) {}
                    try {
                        if (pstmt2 != null) {
                            pstmt2.close();
                        }
                    }
                    catch (Exception ex8) {}
                    pstmt2 = pconn.prepareStatement(sql1);
                    rs2 = pstmt2.executeQuery();
                    if (rs2.next()) {
                        sangtaeCount = rs2.getString(1);
                    }
                    if (!sangtaeCount.equals("0") && Integer.parseInt(SuspendDate) <= Integer.parseInt(nowDate)) {
                        if (count4.equals("0")) {
                            query = " DELETE FROM UM_ENTER_STATE \n WHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                            try {
                                if (pstmt3 != null) {
                                    pstmt3.close();
                                }
                            }
                            catch (Exception ex9) {}
                            pstmt3 = pconn.prepareStatement(query);
                            pstmt3.executeUpdate();
                            this.SaveLog("UM_ENTER_STATE 정지 삭제 : BIZ_REG_NO[" + UnpairNo + "]");
                        }
                        else {
                            if (sDate.equals("") || eDate.equals("")) {
                                continue;
                            }
                            query = " UPDATE UM_ENTER_STATE SET \n RAISED_DT = SYSDATE, \n START_DT = '" + sDate + "', \n" + " END_DT = '" + eDate + "', \n" + " REMARK     = '" + Gita + "', \n" + " MANAGER_ID = '" + this.GetValue(this.xmlDoc, "/gb:Isssta/Header.Details/cc:Message.Sender.Identifier/Identifier.Content", 0) + "', \n" + " PROCESS_DT = sysdate \n" + " WHERE BIZ_REG_NO = '" + UnpairNo + "' \n" + "   AND STATE_CLS = '05'";
                            try {
                                if (pstmt3 != null) {
                                    pstmt3.close();
                                }
                            }
                            catch (Exception ex10) {}
                            pstmt3 = pconn.prepareStatement(query);
                            pstmt3.executeUpdate();
                            this.SaveLog("UM_ENTER_STATE 정지 수정 : BIZ_REG_NO[" + UnpairNo + "]");
                        }
                    }
                }
            }
        }
        catch (Exception exe) {
            this.SaveLog(" updateDetailBRCEO() :" + exe.toString());
            throw exe;
        }
        finally {
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
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex13) {}
            }
            if (pstmt2 != null) {
                try {
                    pstmt2.close();
                }
                catch (Exception ex14) {}
            }
            if (pstmt3 != null) {
                try {
                    pstmt3.close();
                }
                catch (Exception ex15) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex16) {}
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex17) {}
        }
        if (rs2 != null) {
            try {
                rs2.close();
            }
            catch (Exception ex18) {}
        }
        if (pstmt2 != null) {
            try {
                pstmt2.close();
            }
            catch (Exception ex19) {}
        }
        if (pstmt3 != null) {
            try {
                pstmt3.close();
            }
            catch (Exception ex20) {}
        }
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
        Log.info("Isssta" + this.xmlFileName + this.sourceName + "[" + this.serialNumber + "]:" + msg);
    }
}
