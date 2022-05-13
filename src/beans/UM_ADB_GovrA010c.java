// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_URV_UserC020b;
import entity.UM_GOJ_GovuA010b;
import entity.UM_ADE_HistA050b;
import common.OneRowEntity;
import entity.UM_ADJ_GovuA020b;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.util.Vector;
import entity.UM_ADV_GovuA030b;

public class UM_ADB_GovrA010c
{
    public UM_ADV_GovuA030b[] select_GovernmentList(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Connection con = null;
        Trx trx = null;
        UM_ADV_GovuA030b[] ettlist = (UM_ADV_GovuA030b[])null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        final String sql = null;
        final Vector vec = new Vector();
        try {
            sb.append("\tSELECT INSTITU_CD, INSTITU_FULL_NM, INSTITU_EN_NM, BIZ_REG_NO, ZIP_CD, ADDR, DETAIL_ADDR, N    \n");
            sb.append("   FROM (SELECT INSTITU_CD, INSTITU_FULL_NM,  INSTITU_EN_NM,   BIZ_REG_NO,      \n");
            sb.append("      ZIP_CD, ADDR, DETAIL_ADDR, ROWNUM N       \n");
            sb.append("    FROM UM_PUB_INSTITU_MAST              \n");
            sb.append("   WHERE 1=1 AND  lower(INSTITU_CD) LIKE ?||'%'  ");
            if (!goNameFull.equals("")) {
                sb.append("  AND lower(INSTITU_FULL_NM) LIKE '%'||?||'%'  ");
            }
            sb.append("     AND DELETE_YN = 'N'          \n");
            sb.append("   ORDER BY INSTITU_CD ASC              \n");
            sb.append(" )            \n");
            sb.append(" WHERE N BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")     \n");
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sb.toString());
            psmt.setString(1, g2bCode.toLowerCase());
            if (!goNameFull.equals("")) {
                psmt.setString(2, goNameFull.toLowerCase());
            }
            UM_ADV_GovuA030b ett = null;
            rs = psmt.executeQuery();
            while (rs.next()) {
                ett = new UM_ADV_GovuA030b();
                ett.setg2bCode(rs.getString(1));
                ett.setgoNameFull(rs.getString(2));
                ett.setgoNameEn(rs.getString(3));
                ett.setsaupNo(rs.getString(4));
                ett.setZIPCODE(rs.getString(5));
                ett.setADDR(rs.getString(6));
                ett.setaddress2(rs.getString(7));
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.select_golist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.select_golist block Exception : " + exc.toString());
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ettlist = new UM_ADV_GovuA030b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public UM_ADV_GovuA030b[] select_GovernmentList1(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Connection con = null;
        Trx trx = null;
        UM_ADV_GovuA030b[] ettlist = (UM_ADV_GovuA030b[])null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final Vector vec = new Vector();
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT INSTITU_CD, INSTITU_FULL_NM, N          \n");
            sb.append("   FROM (SELECT INSTITU_CD, INSTITU_FULL_NM, ROWNUM N       \n");
            sb.append("    FROM UM_PUB_INSTITU_MAST              \n");
            sb.append("   WHERE INSTITU_CD LIKE ?||'%' AND INSTITU_FULL_NM LIKE '%'||?||'%'       \n");
            sb.append("   ORDER BY INSTITU_CD ASC              \n");
            sb.append("  )            \n");
            sb.append(" WHERE N BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")     \n");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, g2bCode);
            psmt.setString(2, goNameFull);
            UM_ADV_GovuA030b ett = null;
            rs = psmt.executeQuery();
            while (rs.next()) {
                ett = new UM_ADV_GovuA030b();
                ett.setg2bCode(rs.getString(1));
                ett.setgoNameFull(rs.getString(2));
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.select_golist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.select_golist block Exception : " + exc.toString());
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ettlist = new UM_ADV_GovuA030b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public int select_GovernmentList_count(final String g2bCode, final String goNameFull) {
        Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        final String sql = null;
        int count = 0;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT COUNT(*) FROM UM_PUB_INSTITU_MAST         ");
            sb.append("  WHERE 1=1 ").append(" \tAND lower(INSTITU_CD) LIKE ?||'%' ").append("   AND DELETE_YN = 'N' ");
            if (!goNameFull.equals("")) {
                sb.append(" AND lower(INSTITU_FULL_NM) LIKE '%'||?||'%' ");
            }
            System.out.println(sb.toString());
            psmt = con.prepareStatement(sb.toString());
            psmt.setString(1, g2bCode.toLowerCase());
            if (!goNameFull.equals("")) {
                psmt.setString(2, goNameFull.toLowerCase());
            }
            rs = psmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.Max_count block Exception : " + exc.toString());
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count;
    }
    
    public UM_ADJ_GovuA020b select_GovernmentList_Confirm(final String recept) throws Exception, SQLException {
        if (recept == null || recept == "") {
            Log.debug("recept í null ");
            return new UM_ADJ_GovuA020b();
        }
        Connection con = null;
        Trx trx = null;
        final UM_ADJ_GovuA020b ettcode = new UM_ADJ_GovuA020b();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT  RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO,").append(" ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX, CHRGR_MN,").append(" CHRGR_DEPART, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID,").append(" CERT_INSTITU, APPROVE_REQ_CD, PERMIT_BRANCH, SMS_RECV_YN, NICKNAME, RECV_CONTENT").append("   FROM  UM_REC_PUB_INSTITU_CERT").append(" WHERE  RECV_NO='" + recept + "'");
            sql = sb.toString();
            Log.debug(sql);
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettcode.setrecept(rs.getString(1));
                ettcode.setG2BCODE(rs.getString(2));
                ettcode.setCNAME(rs.getString(3));
                ettcode.setKNAME(rs.getString(4));
                ettcode.setIDENT(rs.getString(5));
                ettcode.setIDENTJUMIN(rs.getString(6));
                ettcode.setZIPNO(rs.getString(7));
                ettcode.setADDRS(rs.getString(8));
                ettcode.setADDRESS2(rs.getString(9));
                ettcode.setCOMNO(rs.getString(10));
                ettcode.setFAX(rs.getString(11));
                ettcode.setMYNAME(rs.getString(12));
                ettcode.setOFFICEDE(rs.getString(13));
                ettcode.setJUMIN(rs.getString(14));
                ettcode.setTEL(rs.getString(15));
                ettcode.setEMAIL(rs.getString(16));
                ettcode.setHP(rs.getString(17));
                ettcode.setUSRID(rs.getString(18));
                ettcode.setCERT_ORG(rs.getString(19));
                ettcode.setdependCode(rs.getString(20));
                ettcode.setbranchOffi1(rs.getString(21));
                ettcode.setsmsCheck(rs.getString(22));
                ettcode.setrefername(rs.getString(23));
                ettcode.setREF_NO(rs.getString(24));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_goma block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_goma block Exception : " + exc.toString());
            throw exc;
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ettcode;
    }
    
    public UM_ADJ_GovuA020b select_CTSInfo(final String instituCd) throws Exception, SQLException {
        if (instituCd == null || instituCd.equals("")) {
            Log.debug("instituCd í null ");
            return new UM_ADJ_GovuA020b();
        }
        Connection con = null;
        Trx trx = null;
        final UM_ADJ_GovuA020b ettcode = new UM_ADJ_GovuA020b();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT  REPR_NM, REPR_IDENT_NO ").append("   FROM  UM_REC_PUB_INSTITU_CERT ").append(" WHERE  INSTITU_CD= ? ").append("  AND RECV_NO = (SELECT MAX(RECV_NO) FROM UM_REC_PUB_INSTITU_CERT WHERE INSTITU_CD= ? )  ");
            sql = sb.toString();
            Log.debug(sql);
            psmt = con.prepareStatement(sql);
            psmt.setString(1, instituCd);
            psmt.setString(2, instituCd);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ettcode.setIDENT(rs.getString(1));
                ettcode.setIDENTJUMIN(rs.getString(2));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_goma block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_goma block Exception : " + exc.toString());
            throw exc;
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ettcode;
    }
    
    public UM_ADJ_GovuA020b select_GovernmentList_Confirm(final String recept, final String approvalCode, final String mCode) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADJ_GovuA020b ettcode = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT  RECV_NO,       INSTITU_CD,   INSTITU_FULL_NM,    INSTITU_EN_NM,    REPR_NM,     REPR_IDENT_NO,   \n");
            sb.append("  ZIP_CD,       ADDR,    DETAIL_ADDR,  BIZ_REG_NO,     FAX,   CHRGR_MN,  \n");
            sb.append("  CHRGR_DEPART,   CHRGR_IDENT_NO, CHRGR_PHONE_NO,     CHRGR_EMAIL,     MOBILE,     USER_ID,  \n");
            sb.append("  CERT_INSTITU,   APPROVE_REQ_CD,   PERMIT_BRANCH,to_char(RECV_DT,'yyyymmdd'),\tPASSWORD_MOD_YN,\tPASSWORD_MOD_ID,\t\n");
            sb.append("  PASSWORD , SMS_RECV_YN, CERT_CLS\t\t\t      \n");
            sb.append("   FROM  UM_REC_PUB_INSTITU_CERT                \n");
            sb.append("  WHERE  RECV_NO='" + recept + "'  and APPROVE_REQ_CD='" + approvalCode + "'   and INSTITU_CD='" + mCode + "'\t\t\t  \n");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            if (rs.next()) {
                ettcode = new UM_ADJ_GovuA020b();
                ettcode.setrecept(rs.getString(1));
                ettcode.setG2BCODE(rs.getString(2));
                ettcode.setCNAME(rs.getString(3));
                ettcode.setKNAME(rs.getString(4));
                ettcode.setIDENT(rs.getString(5));
                ettcode.setIDENTJUMIN(rs.getString(6));
                ettcode.setZIPNO(rs.getString(7));
                ettcode.setADDRS(rs.getString(8));
                ettcode.setADDRESS2(rs.getString(9));
                ettcode.setCOMNO(rs.getString(10));
                ettcode.setFAX(rs.getString(11));
                ettcode.setMYNAME(rs.getString(12));
                ettcode.setOFFICEDE(rs.getString(13));
                ettcode.setJUMIN(rs.getString(14));
                ettcode.setTEL(rs.getString(15));
                ettcode.setEMAIL(rs.getString(16));
                ettcode.setHP(rs.getString(17));
                ettcode.setUSRID(rs.getString(18));
                ettcode.setCERT_ORG(rs.getString(19));
                ettcode.setdependCode(rs.getString(20));
                ettcode.setbranchOffi1(rs.getString(21));
                ettcode.setDATE(rs.getString(22));
                ettcode.setpasswdmodOK(rs.getString(23));
                ettcode.setgID(rs.getString(24));
                ettcode.seti07(rs.getString(25));
                ettcode.setsmsCheck(rs.getString(26));
                ettcode.setCERT_CLS(rs.getString(27));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_GovernmentList_Confirm('" + recept + "','" + approvalCode + "','" + mCode + "'):" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_GovernmentList_Confirm('" + recept + "','" + approvalCode + "','" + mCode + "'):" + exc.toString());
            throw new Exception(exc.getMessage());
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ettcode;
    }
    
    public UM_ADJ_GovuA020b GovernmentList_Confirm(final String dependCode, final String g2bCode, final String saupNo) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADJ_GovuA020b ettcode = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT  RECV_NO,       INSTITU_CD,   INSTITU_FULL_NM,    INSTITU_EN_NM,    REPR_NM,     REPR_IDENT_NO,   \n");
            sb.append("  ZIP_CD,       ADDR,    DETAIL_ADDR,  BIZ_REG_NO,     FAX,   CHRGR_MN,  \n");
            sb.append("  CHRGR_DEPART,   CHRGR_IDENT_NO, CHRGR_PHONE_NO,     CHRGR_EMAIL,     MOBILE,     USER_ID,  \n");
            sb.append("  CERT_INSTITU,   REG_YN,       REJECTED_RSON,    APPROVE_REQ_CD,       ISSUE_YN,       \n");
            sb.append("  RECV_CONTENT,       APPROVE_REQ_CD,\t\tPASSWORD_MOD_YN,\tPASSWORD_MOD_ID,     PASSWORD        \n");
            sb.append("   \t\t\t\n");
            sb.append("   FROM  UM_REC_PUB_INSTITU_CERT                \n");
            sb.append("  WHERE  APPROVE_REQ_CD ='" + dependCode + "'         \n");
            sb.append("    AND  lower(INSTITU_CD) = '" + g2bCode.toLowerCase() + "'           \n");
            sb.append("    AND  BIZ_REG_NO = '" + saupNo + "'          \n");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettcode = new UM_ADJ_GovuA020b();
                ettcode.setrecept(rs.getString(1));
                ettcode.setG2BCODE(rs.getString(2));
                ettcode.setCNAME(rs.getString(3));
                ettcode.setKNAME(rs.getString(4));
                ettcode.setIDENT(rs.getString(5));
                ettcode.setIDENTJUMIN(rs.getString(6));
                ettcode.setZIPNO(rs.getString(7));
                ettcode.setADDRS(rs.getString(8));
                ettcode.setADDRESS2(rs.getString(9));
                ettcode.setCOMNO(rs.getString(10));
                ettcode.setFAX(rs.getString(11));
                ettcode.setMYNAME(rs.getString(12));
                ettcode.setOFFICEDE(rs.getString(13));
                ettcode.setJUMIN(rs.getString(14));
                ettcode.setTEL(rs.getString(15));
                ettcode.setEMAIL(rs.getString(16));
                ettcode.setHP(rs.getString(17));
                ettcode.setUSRID(rs.getString(18));
                ettcode.setCERT_ORG(rs.getString(19));
                ettcode.setenYn(rs.getString(20));
                ettcode.setback(rs.getString(21));
                ettcode.setdependCode(rs.getString(22));
                ettcode.setbalYN(rs.getString(23));
                ettcode.setREF_NO(rs.getString(24));
                ettcode.setINGA_CODE(rs.getString(25));
                ettcode.setpasswdmodOK(rs.getString(26));
                ettcode.setgID(rs.getString(27));
                ettcode.seti07(rs.getString(28));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_goma block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_goma block Exception : " + exc.toString());
            throw exc;
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        
        return ettcode;
    }
    
    public UM_ADJ_GovuA020b GovernmentList_Confirm2(final String dependCode, final String g2bCode, final String saupNo) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADJ_GovuA020b ettcode = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT  RECV_NO,       INSTITU_CD,   INSTITU_FULL_NM,    INSTITU_EN_NM,    REPR_NM,     REPR_IDENT_NO,   \n");
            sb.append("  ZIP_CD,       ADDR,    DETAIL_ADDR,  BIZ_REG_NO,     FAX,   CHRGR_MN,  \n");
            sb.append("  CHRGR_DEPART,   CHRGR_IDENT_NO, CHRGR_PHONE_NO,     CHRGR_EMAIL,     MOBILE,     USER_ID,  \n");
            sb.append("  CERT_INSTITU,   REG_YN,       REJECTED_RSON,    APPROVE_REQ_CD,       ISSUE_YN,       \n");
            sb.append("  RECV_CONTENT,       APPROVE_REQ_CD,\t\tPASSWORD_MOD_YN,\tPASSWORD_MOD_ID,     PASSWORD        \n");
            sb.append("   \t\t\t\n");
            sb.append("   FROM  UM_REC_PUB_INSTITU_CERT                \n");
            sb.append("  WHERE  (APPROVE_REQ_CD ='" + dependCode + "' OR TEMP_NAME = 'APP_CD:" + dependCode + "')   \n");
            sb.append("    AND  lower(INSTITU_CD) = '" + g2bCode.toLowerCase() + "'           \n");
//            sb.append("    AND  BIZ_REG_NO = '" + saupNo + "'          \n");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettcode = new UM_ADJ_GovuA020b();
                ettcode.setrecept(rs.getString(1));
                ettcode.setG2BCODE(rs.getString(2));
                ettcode.setCNAME(rs.getString(3));
                ettcode.setKNAME(rs.getString(4));
                ettcode.setIDENT(rs.getString(5));
                ettcode.setIDENTJUMIN(rs.getString(6));
                ettcode.setZIPNO(rs.getString(7));
                ettcode.setADDRS(rs.getString(8));
                ettcode.setADDRESS2(rs.getString(9));
                ettcode.setCOMNO(rs.getString(10));
                ettcode.setFAX(rs.getString(11));
                ettcode.setMYNAME(rs.getString(12));
                ettcode.setOFFICEDE(rs.getString(13));
                ettcode.setJUMIN(rs.getString(14));
                ettcode.setTEL(rs.getString(15));
                ettcode.setEMAIL(rs.getString(16));
                ettcode.setHP(rs.getString(17));
                ettcode.setUSRID(rs.getString(18));
                ettcode.setCERT_ORG(rs.getString(19));
                ettcode.setenYn(rs.getString(20));
                ettcode.setback(rs.getString(21));
                ettcode.setdependCode(rs.getString(22));
                ettcode.setbalYN(rs.getString(23));
                ettcode.setREF_NO(rs.getString(24));
                ettcode.setINGA_CODE(rs.getString(25));
                ettcode.setpasswdmodOK(rs.getString(26));
                ettcode.setgID(rs.getString(27));
                ettcode.seti07(rs.getString(28));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_goma block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_goma block Exception : " + exc.toString());
            throw exc;
        }
        finally {
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
	        if (con != null) {
	            try {
	                trx.close();
	            }
	            catch (Exception ex6) {}
	        }
        }
        
        return ettcode;
    }
    
    public UM_ADJ_GovuA020b EnterpriseList_Confirm(final String dependCode, final String saupNo) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADJ_GovuA020b ettcode = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT  RECV_NO,       INSTITU_CD,   INSTITU_FULL_NM,    INSTITU_EN_NM,    REPR_NM,     REPR_IDENT_NO,   \n");
            sb.append("  ZIP_CD,       ADDR,    DETAIL_ADDR,  BIZ_REG_NO,     FAX,   CHRGR_MN,  \n");
            sb.append("  CHRGR_DEPART,   CHRGR_IDENT_NO, CHRGR_PHONE_NO,     CHRGR_EMAIL,     MOBILE,     USER_ID,  \n");
            sb.append("  CERT_INSTITU,   REG_YN,       REJECTED_RSON,    APPROVE_REQ_CD,       ISSUE_YN,       \n");
            sb.append("  RECV_CONTENT,       APPROVE_REQ_CD,\t\tPASSWORD_MOD_YN,\tPASSWORD_MOD_ID,     PASSWORD        \n");
            sb.append("   FROM  UM_REC_PUB_INSTITU_CERT                \n");
            sb.append("  WHERE  APPROVE_REQ_CD ='" + dependCode + "'         \n");
            sb.append("    AND CERT_CLS='S'           \n");
            sb.append("    AND  BIZ_REG_NO = '" + saupNo + "'          \n");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettcode = new UM_ADJ_GovuA020b();
                ettcode.setrecept(rs.getString(1));
                ettcode.setG2BCODE(rs.getString(2));
                ettcode.setCNAME(rs.getString(3));
                ettcode.setKNAME(rs.getString(4));
                ettcode.setIDENT(rs.getString(5));
                ettcode.setIDENTJUMIN(rs.getString(6));
                ettcode.setZIPNO(rs.getString(7));
                ettcode.setADDRS(rs.getString(8));
                ettcode.setADDRESS2(rs.getString(9));
                ettcode.setCOMNO(rs.getString(10));
                ettcode.setFAX(rs.getString(11));
                ettcode.setMYNAME(rs.getString(12));
                ettcode.setOFFICEDE(rs.getString(13));
                ettcode.setJUMIN(rs.getString(14));
                ettcode.setTEL(rs.getString(15));
                ettcode.setEMAIL(rs.getString(16));
                ettcode.setHP(rs.getString(17));
                ettcode.setUSRID(rs.getString(18));
                ettcode.setCERT_ORG(rs.getString(19));
                ettcode.setenYn(rs.getString(20));
                ettcode.setback(rs.getString(21));
                ettcode.setdependCode(rs.getString(22));
                ettcode.setbalYN(rs.getString(23));
                ettcode.setREF_NO(rs.getString(24));
                ettcode.setINGA_CODE(rs.getString(25));
                ettcode.setpasswdmodOK(rs.getString(26));
                ettcode.setgID(rs.getString(27));
                ettcode.seti07(rs.getString(28));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_goma block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_goma block Exception : " + exc.toString());
            throw exc;
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ettcode;
    }
    
    public UM_ADJ_GovuA020b EnterpriseList_Confirm2(final String dependCode, final String saupNo) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADJ_GovuA020b ettcode = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT  RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, \n");
            sb.append("  ZIP_CD, ADDR, DETAIL_ADDR, BIZ_REG_NO, FAX, CHRGR_MN, \n");
            sb.append("  CHRGR_DEPART, CHRGR_IDENT_NO, CHRGR_PHONE_NO, CHRGR_EMAIL, MOBILE, USER_ID, \n");
            sb.append("  CERT_INSTITU, REG_YN, REJECTED_RSON, APPROVE_REQ_CD, ISSUE_YN, \n");
            sb.append("  RECV_CONTENT, APPROVE_REQ_CD,\t\tPASSWORD_MOD_YN,\tPASSWORD_MOD_ID, PASSWORD \n");
            sb.append("   FROM  UM_REC_PUB_INSTITU_CERT                \n");
            sb.append("  WHERE  (APPROVE_REQ_CD = '" + dependCode + "' OR TEMP_NAME = 'APP_CD:" + dependCode + "') \n");//Thêm điều kiện tìm kiếm khi đã phê duyệt CTS
            sb.append("    AND CERT_CLS='S'           \n");
            sb.append("    AND  BIZ_REG_NO = '" + saupNo + "'          \n");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();

            while (rs.next()) {
                ettcode = new UM_ADJ_GovuA020b();
                ettcode.setrecept(rs.getString(1));
                ettcode.setG2BCODE(rs.getString(2));
                ettcode.setCNAME(rs.getString(3));
                ettcode.setKNAME(rs.getString(4));
                ettcode.setIDENT(rs.getString(5));
                ettcode.setIDENTJUMIN(rs.getString(6));
                ettcode.setZIPNO(rs.getString(7));
                ettcode.setADDRS(rs.getString(8));
                ettcode.setADDRESS2(rs.getString(9));
                ettcode.setCOMNO(rs.getString(10));
                ettcode.setFAX(rs.getString(11));
                ettcode.setMYNAME(rs.getString(12));
                ettcode.setOFFICEDE(rs.getString(13));
                ettcode.setJUMIN(rs.getString(14));
                ettcode.setTEL(rs.getString(15));
                ettcode.setEMAIL(rs.getString(16));
                ettcode.setHP(rs.getString(17));
                ettcode.setUSRID(rs.getString(18));
                ettcode.setCERT_ORG(rs.getString(19));
                ettcode.setenYn(rs.getString(20));
                ettcode.setback(rs.getString(21));
                ettcode.setdependCode(rs.getString(22));
                ettcode.setbalYN(rs.getString(23));
                ettcode.setREF_NO(rs.getString(24));
                ettcode.setINGA_CODE(rs.getString(25));
                ettcode.setpasswdmodOK(rs.getString(26));
                ettcode.setgID(rs.getString(27));
                ettcode.seti07(rs.getString(28));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.EnterpriseList_Confirm2 block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.EnterpriseList_Confirm2 block Exception : " + exc.toString());
            throw exc;
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }

        return ettcode;
    }
    
    public int GovernmentList_Confirm_Count(final String dependCode, final String g2bCode, final String saupNo) {
        Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        try {
            sb.append("\tSELECT  count(*) FROM UM_REC_PUB_INSTITU_CERT      \n");
            sb.append("  WHERE  APPROVE_REQ_CD ='" + dependCode + "'      \n");
            sb.append("    AND  lower(INSTITU_CD) = '" + g2bCode.toLowerCase() + "'        \n");
            sb.append("    AND  BIZ_REG_NO = '" + saupNo + "'       \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.Max_count block Exception : " + exc.toString());
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
       
        return count;
    }
    
    public int GovernmentList_Confirm_Count2(final String dependCode, final String g2bCode, final String saupNo) {
        Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        try {
            sb.append("\tSELECT  count(*) FROM UM_REC_PUB_INSTITU_CERT      \n");
            sb.append("  WHERE  (APPROVE_REQ_CD ='" + dependCode + "' OR TEMP_NAME = 'APP_CD:" + dependCode + "')\n ");
            sb.append("    AND  lower(INSTITU_CD) = '" + g2bCode.toLowerCase() + "'        \n");
            //sb.append("    AND  BIZ_REG_NO = '" + saupNo + "'       \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.Max_count block Exception : " + exc.toString());
        }
        finally {
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
	        if (con != null) {
	            try {
	                trx.close();
	            }
	            catch (Exception ex6) {}
	        }
        }
       
        return count;
    }
    
    public int EnterpriseList_Confirm_Count(final String dependCode, final String saupNo) {
        Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        try {
            sb.append("\tSELECT  count(*) FROM UM_REC_PUB_INSTITU_CERT      \n");
            sb.append("  WHERE  CERT_CLS='S'      \n");
            sb.append("    AND  APPROVE_REQ_CD ='" + dependCode + "'       \n");
            sb.append("    AND  BIZ_REG_NO = '" + saupNo + "'       \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.Max_count block Exception : " + exc.toString());
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count;
    }
    
    public int EnterpriseList_Confirm_Count2(final String dependCode, final String saupNo) {
        Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;

        try {
            sb.append("\tSELECT  count(*) FROM UM_REC_PUB_INSTITU_CERT \n");
            sb.append("  WHERE  CERT_CLS='S' \n");
            sb.append("    AND  (APPROVE_REQ_CD = '" + dependCode + "' OR TEMP_NAME = 'APP_CD:" + dependCode + "') \n");//Them dieu kien tim kiem voi CTS da phe duyet
            sb.append("    AND  BIZ_REG_NO = '" + saupNo + "' \n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.EnterpriseList_Confirm_Count2 block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.EnterpriseList_Confirm_Count2 block Exception : " + exc.toString());
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        
        return count;
    }
    
    public UM_ADJ_GovuA020b[] approval_list(final int pagenum, final int PAGEMAX, final String bizRegNo, final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1) {
        return this.approval_list(pagenum, PAGEMAX, bizRegNo, upcheNm, Start, End, flag, total, branchOffi1, "B");
    }
    
    public UM_ADJ_GovuA020b[] approval_listP(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String area, final String instituCd) {
        return this.approval_listP(pagenum, PAGEMAX, upcheNm, Start, End, flag, total, branchOffi1, "B", area, instituCd);
    }
    
    public UM_ADJ_GovuA020b[] approval_listP(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String Cert_cls, final String area, final String instituCd) {
        Connection con = null;
        Trx trx = null;
        UM_ADJ_GovuA020b[] ett = (UM_ADJ_GovuA020b[])null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        final int psmt_inx = 0;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_MN,\t     ADDR,  \t  \t\t\t \n");
            sb.append(" \t\tREG_YN,\t\t    APPROVE_REQ_CD,   INSTITU_CD,   RECV_NO,   RECV_DT ,NUM, CD_NM2     \t\t\t\n");
            sb.append("   FROM\t(\t\t\t     \n");
            sb.append(" SELECT\tA.* , ROWNUM\tNUM\t\t        \n");
            sb.append("   FROM\t(\t\t\t     \n");
            sb.append(" SELECT\ta.INSTITU_FULL_NM,\ta.BIZ_REG_NO,\ta.CHRGR_MN,\t    a.ADDR,  \t  \t\t\t\n");
            sb.append(" \t\ta.REG_YN,\t\t    a.APPROVE_REQ_CD,   a.INSTITU_CD,   a.RECV_NO,  TO_CHAR(a.RECV_DT,'DD-MM-YYYY') RECV_DT, b.CD_NM2   \n");
            sb.append("   FROM\tUSEMN.UM_REC_PUB_INSTITU_CERT\ta, SYN_PUB_CODE b\t\t    \n");
            sb.append("  WHERE\ta.ZIP_CD = b.CD and b.CD_CLS = 'GU7' and a.RECV_DT IS NOT NULL\t\t\t    \n");
            if (!Cert_cls.equals("")) {
                sb.append(" AND a.CERT_CLS ='" + Cert_cls.toUpperCase() + "'");
            }
            if (!instituCd.equals("") && instituCd != null) {
                sb.append(" AND lower(a.INSTITU_CD) like '%" + instituCd.toLowerCase().trim() + "%'");
            }
            if (!area.equals("") && area != null) {
                sb.append(" AND a.ZIP_CD = '" + area + "'");
            }
            if (flag.equals("S")) {
                if (upcheNm.length() >= 1) {
                    sb.append(" AND\t\tlower(a.INSTITU_FULL_NM) like '%" + upcheNm.toLowerCase().trim() + "%'   \t\t\n");
                }
                if (Start != "" && End != "") {
                    sb.append(" AND\t\ta.RECV_DT BETWEEN TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                    sb.append(" AND TO_DATE('" + EndTime + "',   'DD/MM/YYYY HH24:MI:SS')\t\n");
                }
                else if (Start != "" && End == "") {
                    sb.append(" AND\t\ta.RECV_DT > TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                }
                else if (Start == "" && End != "") {
                    sb.append(" AND\t\ta.RECV_DT < TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                }
            }
            if (total.equals("1")) {
                sb.append(" AND\t\ta.REG_YN ='Y'    \t\t\t\t\n");
            }
            else if (total.equals("2")) {
                sb.append(" AND\t\ta.REG_YN ='N'    \t\t\t\t\n");
            }
            else if (total.equals("3")) {
                sb.append(" AND\t\ta.REG_YN ='D'    \t\t\t\t\n");
            }
            else if (total.equals("4")) {
                sb.append(" AND\t\ta.REG_YN ='C'    \t\t\t\t\n");
            }
            if (branchOffi1.length() > 1) {
                sb.append(" AND\t\ta.PERMIT_BRANCH ='" + branchOffi1 + "'    \t\n");
            }
            sb.append("\t\tORDER BY a.RECV_DT DESC, a.RECV_NO DESC    \t\t\t\t\n");
            sb.append("\t\t\t) A     \t\t\t\n");
            sb.append("\t\t\t)      \t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + "\t)   \n");
            sql = sb.toString();
            Log.debug("SQL LAN THU 2: " + sql);
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            UM_ADJ_GovuA020b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_ADJ_GovuA020b();
                ett_list.setCNAME(rs.getString(1));
                ett_list.setCOMNO(rs.getString(2));
                ett_list.setMYNAME(rs.getString(3));
                ett_list.setADDRS(rs.getString(4));
                ett_list.setenYn(rs.getString(5));
                ett_list.setdependCode(rs.getString(6));
                ett_list.setG2BCODE(rs.getString(7));
                ett_list.setrecept(rs.getString(8));
                ett_list.setcreateDateTime(rs.getString(9));
                ett_list.setZIPNO(rs.getString(11));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URV_UserD020p.select_user block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URV_UserD020p.select_user block Exception : " + exc.toString());
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ett = new UM_ADJ_GovuA020b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public OneRowEntity[] approval_listQ(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String Cert_cls, final String area, final String instituCd, final String status) {
        Connection con = null;
        Trx trx = null;
        OneRowEntity ett = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        final String sql = this.getSqlRegistStatus(pagenum, PAGEMAX, upcheNm, Start, End, flag, total, branchOffi1, Cert_cls, area, instituCd, status);
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ett = new OneRowEntity();
                ett.dataArraySetting(17);
                ett.data[0] = this.retSpace(rs.getString(1));
                ett.data[1] = this.retSpace(rs.getString(2));
                ett.data[2] = this.retSpace(rs.getString(3));
                ett.data[3] = this.retSpace(rs.getString(4));
                ett.data[4] = this.retSpace(rs.getString(5));
                ett.data[5] = this.retSpace(rs.getString(6));
                ett.data[6] = this.retSpace(rs.getString(7));
                ett.data[7] = this.retSpace(rs.getString(8));
                ett.data[8] = this.retSpace(rs.getString(9));
                ett.data[9] = this.retSpace(rs.getString(10));
                ett.data[10] = this.retSpace(rs.getString(11));
                ett.data[11] = this.retSpace(rs.getString(12));
                ett.data[12] = this.retSpace(rs.getString(13));
                ett.data[13] = this.retSpace(rs.getString(14));
                ett.data[14] = this.retSpace(rs.getString(15));
                ett.data[15] = this.retSpace(rs.getString(16));
                ett.data[16] = this.retSpace(this.getRegistStatus(ett.data[1], ett.data[2], ett.data[3], ett.data[4], ett.data[5], ett.data[6], ett.data[7], status));
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, new StringBuffer().append(sqle.toString()).toString());
        }
        catch (Exception exc) {
            Log.errors(this, exc, new StringBuffer().append(exc.toString()).toString());
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        final OneRowEntity[] ettList = new OneRowEntity[vec.size()];
        vec.copyInto(ettList);
        return ettList;
    }
    
    private String getRegistStatus(final String regYn, final String recCertYn, final String recIssueYn, final String recCertNm, final String approveRegCd, final String regContent, final String isUser, final String status) {
        if (!"".equals(status.trim())) {
            return status;
        }
        if ("Y".equals(isUser)) {
            return "4";
        }
        if (!"Y".equals(regYn) || !"Y".equals(recCertYn)) {
            if ("E".equals(regYn) || "E".equals(recCertYn)) {
                return "5";
            }
            return "1";
        }
        else {
            if ("Y".equals(regYn) && "Y".equals(recCertYn) && !"".equals(approveRegCd) && "".equals(regContent)) {
                return "2";
            }
            if ("Y".equals(regYn) && "Y".equals(recCertYn) && "Y".equals(recIssueYn) && !"".equals(recCertNm) && !"".equals(approveRegCd) && !"".equals(regContent)) {
                return "3";
            }
            return "";
        }
    }
    
    private String getSqlRegistStatus(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String Cert_cls, final String area, final String instituCd, final String status) {
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        final String regDtQuery = "4".equals(status) ? " REG_DT " : " RECV_DT ";
        final String regDt = "4".equals(status) ? " a.REG_DT " : " a.RECV_DT ";
        final String order = " ORDER BY RECV_DT DESC  ";
        String where = " WHERE " + regDtQuery + " IS NOT NULL     ";
        if (!instituCd.equals("") && instituCd != null) {
            where = String.valueOf(where) + " AND lower(a.INSTITU_CD) like '%" + instituCd.toLowerCase().trim() + "%'";
        }
        if (!area.equals("") && area != null) {
            where = String.valueOf(where) + " AND a.ZIP_CD = '" + area + "'";
        }
        if (flag.equals("S")) {
            if (upcheNm.length() >= 1) {
                where = String.valueOf(where) + " AND\t\tlower(a.INSTITU_FULL_NM) like '%" + upcheNm.toLowerCase().trim() + "%'   \t\t\n";
            }
            if (Start != "" && End != "") {
                where = String.valueOf(where) + " AND\t\t" + regDt + " BETWEEN TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
                where = String.valueOf(where) + " AND TO_DATE('" + EndTime + "',   'DD/MM/YYYY HH24:MI:SS')\t\n";
            }
            else if (Start != "" && End == "") {
                where = String.valueOf(where) + " AND\t\t" + regDt + " > TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
            }
            else if (Start == "" && End != "") {
                where = String.valueOf(where) + " AND\t\t" + regDt + " < TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
            }
        }
        if (branchOffi1.length() > 1) {
            where = String.valueOf(where) + " AND\t\tc.PERMIT_BRANCH ='" + branchOffi1 + "'    \t\n";
        }
        where = String.valueOf(where) + " AND " + this.getQueryStatus(status);
        final StringBuffer select = new StringBuffer();
        final StringBuffer sb = new StringBuffer();
        select.append("SELECT  RECV_DT, REG_YN, REC_CERT_YN,  REC_ISSUE_YN, REC_CERT_NM, APPROVE_REQ_CD,   ");
        select.append("\t\t\tRECV_CONTENT,  IS_USER ,INSTITU_FULL_NM, BIZ_REG_NO, CHRGR_MN,ADDR,    ");
        select.append("\t\tINSTITU_CD, RECV_NO,  NUM, CD_NM2 FROM (       SELECT A.* , ROWNUM    NUM FROM (        ");
        if ("4".equals(status)) {
            select.append("\t\tSELECT DISTINCT TO_CHAR(" + regDt + ",'DD-MM-YYYY') RECV_DT, '' REG_YN, '' REC_CERT_YN, '' REC_ISSUE_YN,    ");
            select.append("\t\t'' REC_CERT_NM, '' APPROVE_REQ_CD, '' RECV_CONTENT, a.INSTITU_CD, a.INSTITU_FULL_NM,    ");
            select.append(" '' BIZ_REG_NO, c.CHRGR_NM CHRGR_MN, a.ADDR, a.RECV_NO,  b.CD_NM2,    ");
            select.append("  '' IS_USER   ");
            select.append("\tFROM USEMN.UM_PUB_INSTITU_MAST a               ");
            select.append(" JOIN SYN_PUB_CODE b ON a.ZIP_CD = b.CD  AND  b.CD_CLS = 'GU7'     ");
            select.append(" JOIN USEMN.UM_USER c ON a.INSTITU_CD=c.MAST_CD AND c.USER_CLS ='g' ");
        }
        else {
            select.append("\t\tSELECT DISTINCT TO_CHAR(a.RECV_DT,'DD-MM-YYYY') RECV_DT, a.REG_YN, c.REG_YN REC_CERT_YN,c.ISSUE_YN REC_ISSUE_YN,    ");
            select.append("\t\tc.CERT_NM REC_CERT_NM, c.APPROVE_REQ_CD, c.RECV_CONTENT, a.INSTITU_CD, a.INSTITU_FULL_NM,    ");
            select.append(" a.BIZ_REG_NO, c.CHRGR_MN,a.ADDR,a.RECV_NO,  b.CD_NM2,    ");
            if ("".equals(status)) {
                select.append("  (SELECT DECODE(COUNT(*),0,'N', 'Y') from USEMN.UM_USER d where a.INSTITU_CD=d.MAST_CD AND USER_CLS='g') as IS_USER   ");
            }
            else {
                select.append(" 'N' IS_USER ");
            }
            select.append("\tFROM USEMN.UM_REC_PUB_INSTITU_MAST a               ");
            select.append(" JOIN SYN_PUB_CODE b ON a.ZIP_CD = b.CD  AND  b.CD_CLS = 'GU7'     ");
            select.append("\tJOIN USEMN.UM_REC_PUB_INSTITU_CERT c ON a.BIZ_REG_NO =c.BIZ_REG_NO  AND c.CERT_CLS ='B' AND a.RECV_NO=c.RECV_NO\t   ");
        }
        sb.append(select);
        sb.append(where);
        sb.append(String.valueOf(order) + "  \t\t\t\t\n");
        sb.append("\t\t\t) A     \t\t\t\n");
        sb.append("\t\t\t)      \t\t\t\n");
        sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + "\t)   \n");
        return sb.toString();
    }
    
    public UM_ADJ_GovuA020b[] approval_list(final int pagenum, final int PAGEMAX, final String bizRegNo, final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String Cert_cls) {
        Connection con = null;
        Trx trx = null;
        UM_ADJ_GovuA020b[] ett = (UM_ADJ_GovuA020b[])null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        int psmt_inx = 0;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_MN,\t     ADDR,  \t  \t\t\t \n");
            sb.append(" \t\tREG_YN,\t\t    APPROVE_REQ_CD,   INSTITU_CD,   RECV_NO,   RECV_DT ,NUM,      \t\t\t\n");
            sb.append("   decode(SIGN(USEMN.GET_EXTEND_STATUS(BIZ_REG_NO)),-1,'Chưa thanh toán - Phí duy trì','Đã thanh toán - Phí duy trì') PM_STATUS\t\t\t     \n");
            sb.append("   FROM\t(\t\t\t     \n");
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_MN,\t     ADDR,  \t  \t\t\t \n");
            sb.append(" \t\tREG_YN,\t\t    APPROVE_REQ_CD,   INSTITU_CD,   RECV_NO,   RECV_DT ,NUM     \t\t\t\n");
            sb.append("   FROM\t(\t\t\t     \n");
            sb.append(" SELECT\tA.* , ROWNUM\tNUM\t\t        \n");
            sb.append("   FROM\t(\t\t\t     \n");
            sb.append(" SELECT\ta.INSTITU_FULL_NM,\ta.BIZ_REG_NO,\ta.CHRGR_MN,\t    a.ADDR,  \t  \t\t\t\n");
            sb.append(" \t\ta.REG_YN,\t\t    a.APPROVE_REQ_CD,   a.INSTITU_CD,   a.RECV_NO,  TO_CHAR(a.RECV_DT,'DD-MM-YYYY') RECV_DT   \n");
            sb.append("   FROM\tUM_REC_PUB_INSTITU_CERT\ta\t\t    \n");
            sb.append("  WHERE\ta.RECV_DT IS NOT NULL\t\t\t    \n");
            if (!Cert_cls.equals("")) {
                sb.append(" AND lower(CERT_CLS) ='" + Cert_cls.toLowerCase().trim() + "'");
            }
            if (!bizRegNo.equals("")) {
                sb.append(" AND lower(BIZ_REG_NO) like '%" + bizRegNo.toLowerCase().trim() + "%'");
            }
            if (flag.equals("S")) {
                if (upcheNm.length() >= 1) {
                    sb.append(" AND\t\tlower(a.INSTITU_FULL_NM) like '%'||?||'%'   \t\t\n");
                }
                if (Start.length() >= 1) {
                    sb.append(" AND\t\ta.RECV_DT BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS')  \t\t\t\n");
                    sb.append(" AND TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS')  \t\t\t\n");
                }
            }
            if (total.equals("1")) {
                sb.append(" AND\t\ta.REG_YN ='Y'    \t\t\t\t\n");
            }
            else if (total.equals("2")) {
                sb.append(" AND\t\ta.REG_YN ='N'    \t\t\t\t\n");
            }
            else if (total.equals("3")) {
                sb.append(" AND\t\ta.REG_YN ='D'    \t\t\t\t\n");
            }
            else if (total.equals("4")) {
                sb.append(" AND\t\ta.REG_YN ='C'    \t\t\t\t\n");
            }
            if (branchOffi1.length() > 1) {
                sb.append(" AND\t\ta.PERMIT_BRANCH ='" + branchOffi1 + "'    \t\n");
            }
            sb.append("\t\tORDER BY a.RECV_DT DESC    \t\t\t\t\n");
            sb.append("\t\t\t) A     \t\t\t\n");
            sb.append("\t\t\t)      \t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + "\t)   \n");
            sb.append("\t\t\t)      \t\t\t\n");
            sql = sb.toString();
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (flag.equals("S") && upcheNm.length() >= 1) {
                ++psmt_inx;
                psmt.setString(psmt_inx, upcheNm.toLowerCase().trim());
                ++psmt_inx;
                psmt.setString(psmt_inx, upcheNm.toLowerCase().trim());
            }
            if (Start.length() >= 1) {
                ++psmt_inx;
                psmt.setString(psmt_inx, StartTime);
                ++psmt_inx;
                psmt.setString(psmt_inx, EndTime);
            }
            UM_ADJ_GovuA020b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_ADJ_GovuA020b();
                ett_list.setCNAME(rs.getString(1));
                ett_list.setCOMNO(rs.getString(2));
                ett_list.setMYNAME(rs.getString(3));
                ett_list.setADDRS(rs.getString(4));
                ett_list.setenYn(rs.getString(5));
                ett_list.setdependCode(rs.getString(6) );
                ett_list.setG2BCODE(rs.getString(7));
                ett_list.setrecept(rs.getString(8));
                ett_list.setcreateDateTime(rs.getString(9));
                ett_list.setPmStatus(rs.getString(11));
                vec.addElement(ett_list);
                Log.debug("DBG X: " + rs.getString(6) + "/" + rs.getString(8) + "\n" +  sql);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URV_UserD020p.select_user block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URV_UserD020p.select_user block Exception : " + exc.toString());
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ett = new UM_ADJ_GovuA020b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public int approval_list_Count(final String bizRegNo, final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1) {
        return this.approval_list_Count(bizRegNo, upcheNm, Start, End, flag, total, branchOffi1, "B");
    }
    
    public int approval_list_CountP(final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String area, final String instituCd) {
        return this.approval_list_CountP(upcheNm, Start, End, flag, total, branchOffi1, "B", area, instituCd);
    }
    
    public int approval_list_CountP(final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String Cert_cls, final String area, final String instituCd) {
        Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        int count = 0;
        final int psmt_inx = 0;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  USEMN.UM_REC_PUB_INSTITU_CERT t1, SYN_PUB_CODE t2\t\t\t\n");
            if (total.equals("1")) {
                sb.append(" WHERE\t\tt1.REG_YN ='Y' \t\t\n");
            }
            else if (total.equals("2")) {
                sb.append(" WHERE\t\tt1.REG_YN ='N' \t\t\n");
            }
            else if (total.equals("3")) {
                sb.append(" WHERE\t\tt1.REG_YN ='D' \t\t\n");
            }
            else if (total.equals("4")) {
                sb.append(" WHERE\t\tt1.REG_YN ='C' \t\t\n");
            }
            else {
                sb.append(" WHERE\t\tt1.REG_YN like '%%'   \n");
            }
            sb.append(" AND t1.ZIP_CD = t2.CD and t2.CD_CLS = 'GU7' \n");
            if (!Cert_cls.equals("")) {
                sb.append(" AND\t\tt1.Cert_cls ='" + Cert_cls.toUpperCase() + "' \n");
            }
            if (instituCd != "" && instituCd != null) {
                sb.append(" AND\t\tlower(t1.INSTITU_CD) like '%" + instituCd.toLowerCase().trim() + "%'\t\n");
            }
            if (area != "" && area != null) {
                sb.append(" AND\t\tt1.ZIP_CD = '" + area + "'\t\n");
            }
            if (flag.equals("S")) {
                if (upcheNm.length() >= 1) {
                    sb.append(" AND\t\tlower(t1.INSTITU_FULL_NM) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
                }
                if (Start != "" && End != "") {
                    sb.append(" AND\t\tt1.RECV_DT BETWEEN TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                    sb.append(" AND TO_DATE('" + EndTime + "',   'DD/MM/YYYY HH24:MI:SS')\t\n");
                }
                else if (Start != "" && End == "") {
                    sb.append(" AND\t\tt1.RECV_DT > TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                }
                else if (Start == "" && End != "") {
                    sb.append(" AND\t\tt1.RECV_DT < TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                }
                if (branchOffi1.length() > 1) {
                    sb.append(" AND\t\tt1.PERMIT_BRANCH ='" + branchOffi1 + "' \t\t\t\n");
                }
            }
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            Log.debug("SQL DEM: " + sql);
            Log.debug("111111:" + count);
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "");
        }
        catch (Exception exc) {
            Log.errors(this, exc, "");
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count;
    }
    
    public int approval_list_CountQ(final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String Cert_cls, final String area, final String instituCd, final String status) {
        Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        String where = "";
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        int count = 0;
        final String regDtQuery = "4".equals(status) ? " a.REG_DT " : " a.RECV_DT ";
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            if ("4".equals(status)) {
                sb.append(" \t\tSELECT COUNT(DISTINCT a.INSTITU_CD)  ");
                sb.append(" FROM USEMN.UM_PUB_INSTITU_MAST a      ");
                sb.append(" JOIN USEMN.UM_USER c ON a.INSTITU_CD=c.MAST_CD AND c.USER_CLS ='g'  ");
            }
            else {
                sb.append(" \t\tSELECT COUNT(DISTINCT a.INSTITU_CD)  ");
                sb.append(" \t\tFROM USEMN.UM_REC_PUB_INSTITU_MAST a  ");
                sb.append("\t\tJOIN USEMN.UM_REC_PUB_INSTITU_CERT c ON  a.BIZ_REG_NO =c.BIZ_REG_NO ");
                sb.append(" \t\tAND a.RECV_NO=c.RECV_NO  AND  c.CERT_CLS ='B'  ");
            }
            where = String.valueOf(where) + "\t\tWHERE  " + regDtQuery + " IS NOT NULL \t";
            where = String.valueOf(where) + " AND " + this.getQueryStatus(status);
            if (!instituCd.equals("") && instituCd != null) {
                where = String.valueOf(where) + " AND lower(a.INSTITU_CD) like '%" + instituCd.toLowerCase().trim() + "%'";
            }
            if (!area.equals("") && area != null) {
                where = String.valueOf(where) + " AND a.ZIP_CD = '" + area + "'";
            }
            if (flag.equals("S")) {
                if (upcheNm.length() >= 1) {
                    where = String.valueOf(where) + " AND\t\tlower(a.INSTITU_FULL_NM) like '%" + upcheNm.toLowerCase().trim() + "%'   \t\t\n";
                }
                if (Start != "" && End != "") {
                    where = String.valueOf(where) + " AND\t\t" + regDtQuery + " BETWEEN TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
                    where = String.valueOf(where) + " AND TO_DATE('" + EndTime + "',   'DD/MM/YYYY HH24:MI:SS')\t\n";
                }
                else if (Start != "" && End == "") {
                    where = String.valueOf(where) + " AND\t\t" + regDtQuery + " > TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
                }
                else if (Start == "" && End != "") {
                    where = String.valueOf(where) + " AND\t\t" + regDtQuery + " < TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
                }
            }
            sb.append(where);
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            Log.debug(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.Max_count block Exception : " + exc.toString());
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count;
    }
    
    private String getQueryStatus(final String status) {
        if ("1".equals(status)) {
            return " a.REG_YN ='N' AND c.REG_YN ='N' ";
        }
        if ("5".equals(status)) {
            return " a.REG_YN ='E' AND c.REG_YN ='E' ";
        }
        if ("2".equals(status)) {
            return " a.REG_YN ='Y' AND c.REG_YN ='Y' AND c.APPROVE_REQ_CD IS NOT NULL AND c.RECV_CONTENT IS NULL";
        }
        if ("3".equals(status)) {
            return " a.REG_YN ='Y' AND c.REG_YN ='Y' AND c.APPROVE_REQ_CD IS NOT NULL  AND c.RECV_CONTENT IS NOT NULL AND c.ISSUE_YN ='Y'  AND c.CERT_NM IS NOT NULL ";
        }
        return " 1=1 ";
    }
    
    public int approval_list_Count(final String bizRegNo, final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String Cert_cls) {
       Log.debug(bizRegNo+ " / " +upcheNm+ " / " + Start+ " / " + End+ " / " + flag+ " / " + total+ " / " + branchOffi1+ " / " + Cert_cls);
    	Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        int count = 0;
        int psmt_inx = 0;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  UM_REC_PUB_INSTITU_CERT\t\t\t\n");
            if (total.equals("1")) {
                sb.append(" WHERE\t\tREG_YN ='Y' \t\t\n");
            }
            else if (total.equals("2")) {
                sb.append(" WHERE\t\tREG_YN ='N' \t\t\n");
            }
            else if (total.equals("3")) {
                sb.append(" WHERE\t\tREG_YN ='D' \t\t\n");
            }
            else if (total.equals("4")) {
                sb.append(" WHERE\t\tREG_YN ='C' \t\t\n");
            }
            else {
                sb.append(" WHERE\t\tREG_YN like '%%'   \n");
            }
            if (!Cert_cls.equals("")) {
                sb.append(" AND\t\tlower(Cert_cls) ='" + Cert_cls.toLowerCase().trim() + "' \n");
            }
            if (!bizRegNo.equals("")) {
                sb.append(" AND\t\tlower(BIZ_REG_NO) like '%" + bizRegNo.toLowerCase().trim() + "%' \n");
            }
            if (flag.equals("S")) {
                if (upcheNm.length() >= 1) {
                    sb.append(" AND\t\tlower(INSTITU_FULL_NM) like '%'||?||'%'\t\n");
                }
                if (Start.length() >= 1) {
                    sb.append(" AND\t\tRECV_DT BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS')     \n");
                    sb.append(" AND TO_DATE(?,   'DD/MM/YYYY HH24:MI:SS')\t\n");
                }
                if (branchOffi1.length() > 1) {
                    sb.append(" AND\t\tPERMIT_BRANCH ='" + branchOffi1 + "' \t\t\t\n");
                }
            }
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            if (flag.equals("S") && upcheNm.length() >= 1) {
                ++psmt_inx;
                psmt.setString(psmt_inx, upcheNm.toLowerCase().trim());
            }
            if (Start.length() >= 1) {
                ++psmt_inx;
                psmt.setString(psmt_inx, StartTime);
                ++psmt_inx;
                psmt.setString(psmt_inx, EndTime);
            }
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.Max_count block Exception : " + exc.toString());
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count;
    }
    
    public UM_ADJ_GovuA020b Approval_Confirm(final String G2BCODE, final String dependCode, final String RECEPT) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADJ_GovuA020b ettcode = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT  RECV_NO,       INSTITU_CD,   INSTITU_FULL_NM,    INSTITU_EN_NM,    REPR_NM,     REPR_IDENT_NO,\t   \n");
            sb.append("  ZIP_CD,       ADDR,    DETAIL_ADDR,  BIZ_REG_NO,     FAX,   CHRGR_MN,\t\t  \n");
            sb.append("  CHRGR_DEPART,   CHRGR_IDENT_NO, CHRGR_PHONE_NO,     CHRGR_EMAIL,     MOBILE,     USER_ID,\t\n");
            sb.append("  CERT_INSTITU,   APPROVE_REQ_CD,   REJECTED_RSON, REG_YN, TO_CHAR(RECV_DT,'DD/MM/YYYY HH24:MI:SS'),PASSWORD_MOD_YN,\t\t\n");
            sb.append("\t\t\tPASSWORD_MOD_ID,\tPASSWORD,\t\tISSUE_YN, SMS_RECV_YN, \tRECV_CONTENT\t\n");
            sb.append("   FROM  UM_REC_PUB_INSTITU_CERT  \t\t\t\t\n");
            sb.append("  WHERE  lower(INSTITU_CD) ='" + G2BCODE.toLowerCase() + "' \t\n");
            sb.append("    AND  APPROVE_REQ_CD ='" + dependCode + "' \n");
            sb.append("    AND  RECV_NO ='" + RECEPT + "' \t\t\t\n");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            if (rs.next()) {
                ettcode = new UM_ADJ_GovuA020b();
                ettcode.setrecept(rs.getString(1));
                ettcode.setG2BCODE(rs.getString(2));
                ettcode.setCNAME(rs.getString(3));
                ettcode.setKNAME(rs.getString(4));
                ettcode.setIDENT(rs.getString(5));
                ettcode.setIDENTJUMIN(rs.getString(6));
                ettcode.setZIPNO(rs.getString(7));
                ettcode.setADDRS(rs.getString(8));
                ettcode.setADDRESS2(rs.getString(9));
                ettcode.setCOMNO(rs.getString(10));
                ettcode.setFAX(rs.getString(11));
                ettcode.setMYNAME(rs.getString(12));
                ettcode.setOFFICEDE(rs.getString(13));
                ettcode.setJUMIN(rs.getString(14));
                ettcode.setTEL(rs.getString(15));
                ettcode.setEMAIL(rs.getString(16));
                ettcode.setHP(rs.getString(17));
                ettcode.setUSRID(rs.getString(18));
                ettcode.setCERT_ORG(rs.getString(19));
                ettcode.setdependCode(rs.getString(20));
                ettcode.setback(rs.getString(21));
                ettcode.setenYn(rs.getString(22));
                ettcode.setcreateDateTime(rs.getString(23));
                ettcode.setpasswdmodOK(rs.getString(24));
                ettcode.setgID(rs.getString(25));
                ettcode.seti07(rs.getString(26));
                ettcode.setbalYN(rs.getString(27));
                ettcode.setsmsCheck(rs.getString(28));
                ettcode.setREF_NO(rs.getString(29));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.Approval_Confirm('" + G2BCODE + "','" + dependCode + "','" + RECEPT + "'):" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.Approval_Confirm('" + G2BCODE + "','" + dependCode + "','" + RECEPT + "'):" + exc.toString());
            throw new Exception(exc.getMessage());
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ettcode;
    }
    
    public UM_ADJ_GovuA020b Approval_Confirm2(final String G2BCODE, final String dependCode, final String RECEPT) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADJ_GovuA020b ettcode = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT  RECV_NO,       INSTITU_CD,   INSTITU_FULL_NM,    INSTITU_EN_NM,    REPR_NM,     REPR_IDENT_NO,\t   \n");
            sb.append("  ZIP_CD,       ADDR,    DETAIL_ADDR,  BIZ_REG_NO,     FAX,   CHRGR_MN,\t\t  \n");
            sb.append("  CHRGR_DEPART,   CHRGR_IDENT_NO, CHRGR_PHONE_NO,     CHRGR_EMAIL,     MOBILE,     USER_ID,\t\n");
            sb.append("  CERT_INSTITU,   APPROVE_REQ_CD,   REJECTED_RSON, REG_YN, TO_CHAR(RECV_DT,'DD/MM/YYYY HH24:MI:SS'),PASSWORD_MOD_YN,\t\t\n");
            sb.append("\t\t\tPASSWORD_MOD_ID,\tPASSWORD,\t\tISSUE_YN, SMS_RECV_YN, \tRECV_CONTENT\t\n");
            sb.append("   FROM  UM_REC_PUB_INSTITU_CERT  \t\t\t\t\n");
            sb.append("  WHERE  lower(INSTITU_CD) ='" + G2BCODE.toLowerCase() + "' \t\n");
            sb.append("    AND  (APPROVE_REQ_CD ='" + dependCode + "' OR TEMP_NAME = 'APP_CD:" + dependCode + "')\n");
            sb.append("    AND  RECV_NO ='" + RECEPT + "' \t\t\t\n");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            if (rs.next()) {
                ettcode = new UM_ADJ_GovuA020b();
                ettcode.setrecept(rs.getString(1));
                ettcode.setG2BCODE(rs.getString(2));
                ettcode.setCNAME(rs.getString(3));
                ettcode.setKNAME(rs.getString(4));
                ettcode.setIDENT(rs.getString(5));
                ettcode.setIDENTJUMIN(rs.getString(6));
                ettcode.setZIPNO(rs.getString(7));
                ettcode.setADDRS(rs.getString(8));
                ettcode.setADDRESS2(rs.getString(9));
                ettcode.setCOMNO(rs.getString(10));
                ettcode.setFAX(rs.getString(11));
                ettcode.setMYNAME(rs.getString(12));
                ettcode.setOFFICEDE(rs.getString(13));
                ettcode.setJUMIN(rs.getString(14));
                ettcode.setTEL(rs.getString(15));
                ettcode.setEMAIL(rs.getString(16));
                ettcode.setHP(rs.getString(17));
                ettcode.setUSRID(rs.getString(18));
                ettcode.setCERT_ORG(rs.getString(19));
                ettcode.setdependCode(rs.getString(20));
                ettcode.setback(rs.getString(21));
                ettcode.setenYn(rs.getString(22));
                ettcode.setcreateDateTime(rs.getString(23));
                ettcode.setpasswdmodOK(rs.getString(24));
                ettcode.setgID(rs.getString(25));
                ettcode.seti07(rs.getString(26));
                ettcode.setbalYN(rs.getString(27));
                ettcode.setsmsCheck(rs.getString(28));
                ettcode.setREF_NO(rs.getString(29));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.Approval_Confirm2('" + G2BCODE + "','" + dependCode + "','" + RECEPT + "'):" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.Approval_Confirm2('" + G2BCODE + "','" + dependCode + "','" + RECEPT + "'):" + exc.toString());
            throw new Exception(exc.getMessage());
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        
        return ettcode;
    }
    
    public UM_ADJ_GovuA020b Document_Confirm(final String g2bCode, final String dependCode, final String saupNo) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADJ_GovuA020b ettcode = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append("\tSELECT  RECV_NO,       INSTITU_CD,   INSTITU_FULL_NM,    INSTITU_EN_NM, REPR_NM,     REPR_IDENT_NO,   \n");
            sb.append("  ZIP_CD,       ADDR,    DETAIL_ADDR,  BIZ_REG_NO,  FAX,   CHRGR_MN,  \n");
            sb.append("  CHRGR_DEPART,   CHRGR_IDENT_NO, CHRGR_PHONE_NO,     CHRGR_EMAIL,  MOBILE,     USER_ID,  \n");
            sb.append("  CERT_INSTITU,   APPROVE_REQ_CD,   PERMIT_BRANCH,    CERT_NM,   PERMIT_CD,   RECV_CONTENT , CERT_CLS,APPROVE_REQ_CD  \n");
            sb.append("   FROM  UM_REC_PUB_INSTITU_CERT      \n");
            sb.append("  WHERE  INSTITU_CD='" + g2bCode + "'   \n");
            sb.append("    AND  APPROVE_REQ_CD='" + dependCode + "'       \n");
            sb.append("    AND  BIZ_REG_NO='" + saupNo + "'  \n");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettcode = new UM_ADJ_GovuA020b();
                ettcode.setrecept(rs.getString(1));
                ettcode.setG2BCODE(rs.getString(2));
                ettcode.setCNAME(rs.getString(3));
                ettcode.setKNAME(rs.getString(4));
                ettcode.setIDENT(rs.getString(5));
                ettcode.setIDENTJUMIN(rs.getString(6));
                ettcode.setZIPNO(rs.getString(7));
                ettcode.setADDRS(rs.getString(8));
                ettcode.setADDRESS2(rs.getString(9));
                ettcode.setCOMNO(rs.getString(10));
                ettcode.setFAX(rs.getString(11));
                ettcode.setMYNAME(rs.getString(12));
                ettcode.setOFFICEDE(rs.getString(13));
                ettcode.setJUMIN(rs.getString(14));
                ettcode.setTEL(rs.getString(15));
                ettcode.setEMAIL(rs.getString(16));
                ettcode.setHP(rs.getString(17));
                ettcode.setUSRID(rs.getString(18));
                ettcode.setCERT_ORG(rs.getString(19));
                ettcode.setdependCode(rs.getString(20));
                ettcode.setbranchOffi1(rs.getString(21));
                ettcode.setdn(rs.getString(22));
                ettcode.setrefercode(rs.getString(23));
                ettcode.setrefername(rs.getString(24));
                ettcode.setCERT_CLS(rs.getString(25));
                ettcode.setINGA_CODE(rs.getString(26));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_goma block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_goma block Exception : " + exc.toString());
            throw exc;
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ettcode;
    }
    
    public UM_ADV_GovuA030b select_goma(final String code) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADV_GovuA030b ettall = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "\n SELECT INSTITU_CD, INSTITU_FULL_NM, INSTITU_SHORT_NM, INSTITU_EN_NM, BIZ_REG_NO, \n INSTITU_CLS, INSTITU_CONDITION, INSTITU_TYPE, CHRGR_MN, CHRGR_DEPART, CHRGR_PHONE_NO, FAX, \n CHRGR_EMAIL, ZIP_CD,  ADDR, DETAIL_ADDR, CHRGR_PHONE_NO,  FAX,  WEBSITE, \n OLD_INSTITU_CD,  GOODS_MNG_NM, APPROVAL_CD,  LOCAL_INSTITU,SPECMNG_AGENT, \n INSTITU_LOCALDIVISION,  TRANSPORTDISTANCE, E_AUTHENT_YN, MANAGER_ID, AREA_CD\n   FROM UM_PUB_INSTITU_MAST \n  WHERE INSTITU_CD = '" + code + "' ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettall = new UM_ADV_GovuA030b();
                ettall.setg2bCode(rs.getString(1));
                ettall.setgoNameFull(rs.getString(2));
                ettall.setgoNameShort(rs.getString(3));
                ettall.setgoNameEn(rs.getString(4));
                ettall.setsaupNo(rs.getString(5));
                ettall.setrelation(rs.getString(6));
                ettall.setbuConditon(rs.getString(7));
                ettall.setbuType(rs.getString(8));
                ettall.settaskmaster(rs.getString(9));
                ettall.setmasterPost(rs.getString(10));
                ettall.setmasterTel(rs.getString(11));
                ettall.setmasterFax(rs.getString(12));
                ettall.setmasterMail(rs.getString(13));
                ettall.setZIPCODE(rs.getString(14));
                ettall.setADDR(rs.getString(15));
                ettall.setaddress2(rs.getString(16));
                ettall.settelNum(rs.getString(17));
                ettall.setfaxNum(rs.getString(18));
                ettall.sethomepage(rs.getString(19));
                ettall.setorganCode(rs.getString(20));
                ettall.setgoodsMaster(rs.getString(21));
                ettall.setpermitCode(rs.getString(22));
                ettall.setprovince(rs.getString(23));
                ettall.setspOffice(rs.getString(24));
                ettall.setbranchOffi(rs.getString(25));
                ettall.settrDistance(rs.getString(26));
                ettall.seteCitation(rs.getString(27));
                ettall.setmasterID(rs.getString(28));
                ettall.setlocaCode(rs.getString(29));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.select_goma block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.select_goma block Exception : " + exc.toString());
            throw exc;
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ettall;
    }
    
    public UM_ADJ_GovuA020b select_lcode(final String recept, final String g2bCode) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADJ_GovuA020b ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            final String sql = "\n SELECT INSTITU_CD,INSTITU_FULL_NM, CHRGR_MN, CHRGR_PHONE_NO, ADDR, WEBSITE,REG_YN,REJECTED_RSON \n   FROM UM_REC_PUB_INSTITU_CERT \n   where  RECV_NO='" + recept + "'  or INSTITU_CD='" + g2bCode + "' ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_ADJ_GovuA020b();
                ett.setG2BCODE(rs.getString(1));
                ett.setCNAME(rs.getString(2));
                ett.setMYNAME(rs.getString(3));
                ett.setTEL(rs.getString(4));
                ett.setADDRS(rs.getString(5));
                ett.setHOME(rs.getString(6));
                ett.setenYn(rs.getString(7));
                ett.setback(rs.getString(8));
            }
            return ett;
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.select_user block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.select_user block Exception : " + exc.toString());
            throw exc;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public int Max_count5(final String recept, final String g2bCode) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(*) FROM UM_REC_PUB_INSTITU_CERT \n WHERE RECV_NO = '" + recept + "' " + "\n OR INSTITU_CD ='" + g2bCode + "' ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.Max_count block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count;
    }
    
    public UM_ADV_GovuA030b select_code1(final String goNameFull) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADV_GovuA030b ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            final String sql = "\n SELECT INSTITU_FULL_NM, CHRGR_MN, CHRGR_PHONE_NO, ADDR, ADDR \n   FROM UM_PUB_INSTITU_MAST \n   where INSTITU_FULL_NM like '%'||?||'%' ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, goNameFull);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_ADV_GovuA030b();
                ett.setgoNameFull(rs.getString(1));
                ett.settaskmaster(rs.getString(2));
                ett.setmasterTel(rs.getString(3));
                ett.setADDR(rs.getString(4));
                ett.sethomepage(rs.getString(5));
            }
            return ett;
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.select_user block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.select_user block Exception : " + exc.toString());
            throw exc;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
    }
    
    public int Max_count1(final String g2bCode, final String goNameFull) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(*) FROM UM_PUB_INSTITU_MAST \n WHERE INSTITU_CD like ?||'%' \n AND INSTITU_FULL_NM like '%'||?||'%' ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, g2bCode);
            pstm.setString(2, goNameFull);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.Max_count block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count;
    }
    
    public UM_ADV_GovuA030b[] select_gopresent(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Connection con = null;
        Trx trx = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_ADV_GovuA030b[] ettlist = (UM_ADV_GovuA030b[])null;
        try {
            final String sql = "select INSTITU_CD,INSTITU_FULL_NM, INSTITU_PHONE_NO,N \n\tfrom (select INSTITU_CD,INSTITU_FULL_NM, INSTITU_PHONE_NO, ROWNUM N from UM_PUB_INSTITU_MAST\n    where INSTITU_CD like ?||'%' and INSTITU_FULL_NM like '%'||?||'%' \n    order by INSTITU_CD ASC) \n\twhere N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")" + "\n    order by INSTITU_CD ASC";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, g2bCode);
            pstm.setString(2, goNameFull);
            UM_ADV_GovuA030b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_ADV_GovuA030b();
                et.setg2bCode(rs.getString(1));
                et.setgoNameFull(rs.getString(2));
                et.settelNum(rs.getString(3));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.select_golist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.select_golist block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ettlist = new UM_ADV_GovuA030b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public UM_ADV_GovuA030b select_mody(final String code) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADV_GovuA030b ettall = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "\n SELECT INSTITU_CD, INSTITU_FULL_NM,BIZ_REG_NO,CHRGR_MN,CHRGR_DEPART, \n CHRGR_PHONE_NO,FAX,ADDR,ADDR,DETAIL_ADDR,LOCAL_INSTITU,GOODS_MNG_NM,ADDR,DELETE_YN\n  from UM_PUB_INSTITU_MAST \n  where INSTITU_CD = '" + code + "' ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettall = new UM_ADV_GovuA030b();
                ettall.setg2bCode(rs.getString(1));
                ettall.setgoNameFull(rs.getString(2));
                ettall.setsaupNo(rs.getString(3));
                ettall.settaskmaster(rs.getString(4));
                ettall.setmasterPost(rs.getString(5));
                ettall.setmasterTel(rs.getString(6));
                ettall.setmasterFax(rs.getString(7));
                ettall.setmasterMail(rs.getString(8));
                ettall.setADDR(rs.getString(9));
                ettall.setaddress2(rs.getString(10));
                ettall.setprovince(rs.getString(11));
                ettall.setgoodsMaster(rs.getString(12));
                ettall.sethomepage(rs.getString(13));
                ettall.setdelete(rs.getString(14));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.select_goma block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.select_goma block Exception : " + exc.toString());
            throw exc;
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ettall;
    }
    
    public UM_ADE_HistA050b select_goruk(final String code, final String date2) throws Exception, SQLException {
        Connection con = null;
        Trx trx = null;
        UM_ADE_HistA050b ettlist = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "\n SELECT * FROM UM_PUB_INSTITU_HIST \n WHERE INSTITU_CD = '" + code + "' and TO_CHAR(MOD_DT, 'YYYY-MM-DD HH24:MI:SS') = '" + date2 + "' ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettlist = new UM_ADE_HistA050b();
                ettlist.setg2bCode(rs.getString(1));
                ettlist.setmoDate(rs.getString(2));
                ettlist.setgoNameFull(rs.getString(3));
                ettlist.setgoNameEn(rs.getString(4));
                ettlist.setsaupNo(rs.getString(5));
                ettlist.setaddress2(rs.getString(6));
                ettlist.setmasterID(rs.getString(7));
                ettlist.setspOffice(rs.getString(8));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.select_goma block SQLException : " + sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.select_goma block Exception : " + exc.toString());
            throw exc;
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
            if (con != null) {
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return ettlist;
    }
    
    public int Max_count2(final String g2bCode, final String goNameFull) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(*) FROM UM_ADMIN_STD_INFOR \n WHERE STD_INSTITU_CD like ?||'%' \n AND FULL_NM like '%'||?||'%' ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, g2bCode);
            pstm.setString(2, goNameFull);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.Max_count block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return count;
    }
    
    public UM_GOJ_GovuA010b[] select_golist1(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Connection con = null;
        Trx trx = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_GOJ_GovuA010b[] ettlist = (UM_GOJ_GovuA010b[])null;
        final StringBuffer sqlB = new StringBuffer();
        try {
            sqlB.append(" select \tSTD_INSTITU_CD, DIRECTOR_POSITION_NM, DIRECTOR_POSITION, VER_NO, RANK, UPPER_INSTITU_CD,  TOP_INSTITU_CD,  ").append("    \t\tINSTITU_CLS_L, INSTITU_CLS_M, INSTITU_CLS_S, CREATE_DT, STOP_DT, STOP_RSON, MOD_DATE, MOD_TIME, MOD_CLS, n ").append("\tfrom ").append(" \t\t(\tselect STD_INSTITU_CD, DIRECTOR_POSITION_NM, DIRECTOR_POSITION, VER_NO, RANK, UPPER_INSTITU_CD,TOP_INSTITU_CD, ").append("    \t\t\t\tINSTITU_CLS_L, INSTITU_CLS_M, INSTITU_CLS_S, CREATE_DT, STOP_DT, STOP_RSON, MOD_DATE, MOD_TIME, MOD_CLS, ROWNUM N ").append("\t\t\tfrom UM_ADMIN_STD_INFOR").append("    \t\twhere STD_INSTITU_CD like '" + g2bCode + "%'").append(" \t) ").append("\twhere N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")");
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sqlB.toString());
            UM_GOJ_GovuA010b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_GOJ_GovuA010b();
                et.setg2Code(rs.getString(1));
                et.setposition(rs.getString(2));
                et.setpositionRank(rs.getString(3));
                et.setdisparity(rs.getString(4));
                et.setgrade(rs.getString(5));
                et.setultraCode(rs.getString(6));
                et.setsubCode(rs.getString(7));
                et.setorganL(rs.getString(8));
                et.setorganM(rs.getString(9));
                et.setorganS(rs.getString(10));
                et.setcreateDate(rs.getString(11));
                et.setabDate(rs.getString(12));
                et.setabDivision(rs.getString(13));
                et.setmodiDate(rs.getString(14));
                et.setmoTime(rs.getString(15));
                et.setmoDivision(rs.getString(16));
                et.setg2bCode(rs.getString(17));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.select_golist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.select_golist block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ettlist = new UM_GOJ_GovuA010b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public UM_GOJ_GovuA010b[] select_golist2(final int pagenum, final int PAGEMAX, final String g2bCode, final String goNameFull) {
        Connection con = null;
        Trx trx = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_GOJ_GovuA010b[] ettlist = (UM_GOJ_GovuA010b[])null;
        try {
            final StringBuffer sqlB = new StringBuffer();
            sqlB.append(" select STD_INSTITU_CD,DIRECTOR_POSITION_NM, DIRECTOR_POSITION,VER_NO,RANK,UPPER_INSTITU_CD, ").append(" TOP_INSTITU_CD, INSTITU_CLS_L,INSTITU_CLS_M, INSTITU_CLS_S, CREATE_DT, STOP_DT, STOP_RSON, ").append(" MOD_DATE, MOD_TIME, MOD_CLS ,FULL_NM,b.INSTITU_CD ").append(" from (select STD_INSTITU_CD,DIRECTOR_POSITION_NM, DIRECTOR_POSITION, VER_NO,RANK,UPPER_INSTITU_CD, ").append("       TOP_INSTITU_CD,INSTITU_CLS_L, INSTITU_CLS_M, INSTITU_CLS_S, CREATE_DT, STOP_DT, STOP_RSON, MOD_DATE , MOD_TIME, ").append("       MOD_CLS, FULL_NM,ROWNUM N from UM_ADMIN_STD_INFOR ").append("       where STD_INSTITU_CD like ?||'%' and FULL_NM like '%'||?||'%') a, UM_PUB_INSTITU_MAST b ").append(" where a.STD_INSTITU_CD = b.INSTITU_CD(+) ").append(" and   N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")").append("");
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sqlB.toString());
            pstm.setString(1, g2bCode);
            pstm.setString(2, goNameFull);
            UM_GOJ_GovuA010b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_GOJ_GovuA010b();
                et.setg2Code(rs.getString(1));
                et.setposition(rs.getString(2));
                et.setpositionRank(rs.getString(3));
                et.setdisparity(rs.getString(4));
                et.setgrade(rs.getString(5));
                et.setultraCode(rs.getString(6));
                et.setsubCode(rs.getString(7));
                et.setorganL(rs.getString(8));
                et.setorganM(rs.getString(9));
                et.setorganS(rs.getString(10));
                et.setcreateDate(rs.getString(11));
                et.setabDate(rs.getString(12));
                et.setabDivision(rs.getString(13));
                et.setmodiDate(rs.getString(14));
                et.setmoTime(rs.getString(15));
                et.setmoDivision(rs.getString(16));
                et.setgoNameFull(rs.getString(17));
                et.setg2bCode(rs.getString(18));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_GovrA010c.select_golist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_GovrA010c.select_golist block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
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
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        ettlist = new UM_GOJ_GovuA010b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    private String retSpace(final String str) {
        return (str == null) ? "" : str.trim();
    }
    
    public int countCTS(final String instituCd) throws Exception {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final String sql = " SELECT count(INSTITU_CD) from (  SELECT INSTITU_CD FROM usemn.UM_REC_PUB_INSTITU_CERT  WHERE CERT_CLS='B' AND INSTITU_CD = ?  UNION ALL SELECT BIZ_REG_NO FROM USEMN.UM_EDOC_STATE WHERE BIZ_REG_NO=? ) ";
        try {
            Log.debug(sql);
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, instituCd);
            psmt.setString(2, instituCd);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
        finally {
            if (trx != null) {
                trx.close();
            }
        }
    }
    
    public OneRowEntity[] selectStatusRegist(final String instituCd, final int page_no, final int max) throws Exception {
        final int start = (page_no - 1) * max + 1;
        final int end = page_no * max;
        OneRowEntity[] etts = (OneRowEntity[])null;
        OneRowEntity ett = null;
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final Vector vec = new Vector();
        final String sql = " SELECT INSTITU_FULL_NM,   REG_YN,             APPROVE_REQ_CD, RECV_CONTENT,  INSTITU_CD,   USER_ID , CERT_NM, BIZ_REG_NO,  NUM     FROM (   SELECT B.* , ROWNUM    NUM             FROM (    SELECT  a.INSTITU_FULL_NM,  a.REG_YN,   a.APPROVE_REQ_CD, a.RECV_CONTENT,  a.INSTITU_CD , c.USER_ID, a.CERT_NM, a.BIZ_REG_NO    FROM usemn.UM_REC_PUB_INSTITU_CERT a LEFT JOIN UM_CERT_INFO b ON a.CERT_NM=b.CERT_NM  LEFT JOIN UM_USER c ON b.USER_ID=c.USER_ID  AND a.INSTITU_CD=c.MAST_CD    WHERE a.CERT_CLS='B' AND a.INSTITU_CD = ?   UNION ALL SELECT a.COM_NM, 'D' REG_YN,'' APPROVE_REQ_CD,  '' RECV_CONTENT, a.BIZ_REG_NO INSTITU_CD, '' USER_ID, '', a.BIZ_REG_NO CERT_NM  FROM USEMN.UM_EDOC_STATE a WHERE  a.PROCESS_ST='4' AND a.BIZ_REG_NO= ?                         )    B )      WHERE  NUM BETWEEN ? AND ? ";
        Log.debug("----------" + sql);
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, instituCd);
            psmt.setString(2, instituCd);
            psmt.setString(3, new StringBuffer(String.valueOf(start)).toString());
            psmt.setString(4, new StringBuffer(String.valueOf(end)).toString());
            rs = psmt.executeQuery();
            while (rs.next()) {
                ett = new OneRowEntity();
                ett.dataArraySetting(8);
                ett.data[0] = this.retSpace(rs.getString(1));
                ett.data[1] = this.retSpace(rs.getString(2));
                ett.data[2] = this.retSpace(rs.getString(3));
                ett.data[3] = this.retSpace(rs.getString(4));
                ett.data[4] = this.retSpace(rs.getString(5));
                ett.data[5] = this.retSpace(rs.getString(6));
                ett.data[6] = this.retSpace(rs.getString(7));
                ett.data[7] = this.retSpace(rs.getString(8));
                vec.add(ett);
                ett = null;
            }
            etts = new OneRowEntity[vec.size()];
            vec.toArray(etts);
            return this.statusDetail(etts);
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
        finally {
            if (trx != null) {
                trx.close();
            }
        }
    }
    
    private OneRowEntity[] statusDetail(final OneRowEntity[] data) throws Exception {
        try {
            OneRowEntity ett = null;
            if (data.length == 0) {
                final OneRowEntity[] etts2 = { null };
                ett = new OneRowEntity();
                ett.dataArraySetting(6);
                ett.data[5] = "";
                ett.data[0] = "";
                ett.data[1] = "Chưa đăng ký";
                ett.data[2] = "Chưa được phê duyệt";
                ett.data[3] = "Chưa nhận CTS";
                ett.data[4] = "Chưa đăng ký người dùng";
                etts2[0] = ett;
                return etts2;
            }
            final OneRowEntity[] etts3 = new OneRowEntity[data.length];
            for (int i = 0; i < data.length; ++i) {
                ett = new OneRowEntity();
                ett.dataArraySetting(6);
                ett.data[5] = data[i].data[0];
                ett.data[0] = data[i].data[4];
                ett.data[1] = "Đã đăng ký";
                ett.data[2] = "Chưa được phê duyệt";
                ett.data[3] = "Chưa nhận CTS";
                ett.data[4] = "Chưa đăng ký người dùng";
                if ("Y".equals(data[i].data[1])) {
                    ett.data[2] = "Đã được phê duyệt<br \\>Mã cơ quan: " + data[i].data[7];
                    if (!"".equals(data[i].data[2])) {
                        ett.data[2] = String.valueOf(ett.data[2]) + "<br \\>Mã phê duyệt đăng ký: " + data[i].data[2];
                    }
                    if (!"".equals(data[i].data[3])) {
                        ett.data[2] = String.valueOf(ett.data[2]) + "<br \\>Số tham chiếu: " + data[i].data[3];
                    }
                }
                else {
                    if ("E".equals(data[i].data[1])) {
                        ett.data[2] = "Đang bảo lưu";
                        etts3[i] = ett;
                        continue;
                    }
                    if ("D".equals(data[i].data[1])) {
                        ett.data[2] = "Đã từ chối";
                        etts3[i] = ett;
                        continue;
                    }
                }
                if (!"".equals(data[i].data[6])) {
                    ett.data[3] = "<br \\>Tên CTS: " + data[i].data[6];
                }
                if (!"".equals(data[i].data[5])) {
                    ett.data[4] = data[i].data[5];
                }
                etts3[i] = ett;
                ett = null;
            }
            return etts3;
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public String checkPayment(final String pmCode, final String pmItem) throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT COUNT(*) \n";
        sql = String.valueOf(sql) + " FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B \n";
        sql = String.valueOf(sql) + " where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' \n";
        sql = String.valueOf(sql) + " AND B.PM_ITEM_ID IN (" + pmItem + ") \n";
        sql = String.valueOf(sql) + " AND A.RECV_NO = '" + pmCode + "' \n";
        
        if (pmItem.equals("2")) {
            sql = String.valueOf(sql) + " AND TO_CHAR(A.PM_DATE, 'DD/MM/YYYY') = TO_CHAR(SYSDATE, 'DD/MM/YYYY') \n";
        }
         
        String result = "0";
        
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
            	//
            }
            if (Integer.parseInt(rs.getString(1)) > 0) {
                result = "1";
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "UM_ADB_GovrA010c.checkPayment Exception");
            throw sqle;
        }
        catch (Exception exc) {
            Log.errors(this, exc, "UM_ADB_GovrA010c.checkPayment Exception");
            throw exc;
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex9) {}
            }
        }
         
        return result;
    }
    
    public String checkPaymentBizRegNo(final String bizRegNo, final String pmItem) throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT COUNT(*) \n";
        sql = String.valueOf(sql) + " FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B \n";
        sql = String.valueOf(sql) + " where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' \n";
        sql = String.valueOf(sql) + " AND B.PM_ITEM_ID IN (" + pmItem + ") \n";
        sql = String.valueOf(sql) + " AND A.BIZ_REG_NO = '" + bizRegNo + "' \n";
        String result = "0";
        
        if (pmItem.equals("2")) {
            sql = String.valueOf(sql) + " AND TO_CHAR(A.PM_DATE, 'DD/MM/YYYY') = TO_CHAR(SYSDATE, 'DD/MM/YYYY') \n";
        }
             
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                //
            } else if (Integer.parseInt(rs.getString(1)) > 0) {
                result = "1";
            }           
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "UM_ADB_GovrA010c.checkPayment Exception");
            throw sqle;
        }
        catch (Exception exc) {
            Log.errors(this, exc, "UM_ADB_GovrA010c.checkPayment Exception");
            throw exc;
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
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex9) {}
            }
        }
        
        return result;
        
    }
    
    public String getLatestPaymentDate(final String bizRegNo) throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final Vector vec = new Vector();
        String sRet = "";
        String sql = "SELECT DECODE(PM_DATE,NULL,'',TO_CHAR(PM_DATE,'DD/MM/YYYY')) PM_DATE FROM ( \n";
        sql = String.valueOf(sql) + " SELECT max(A.PM_DATE) PM_DATE \n";
        sql = String.valueOf(sql) + " FROM BID.BID_PAYMENT_TABLE A \n";
        sql = String.valueOf(sql) + " where A.PM_FINISH_YN='Y' \n";
        sql = String.valueOf(sql) + " AND A.BIZ_REG_NO = '" + bizRegNo + "' \n";
        sql = String.valueOf(sql) + " )";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                sRet = rs.getString(1);
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
                if (con != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex3) {}
                }
                return sRet;
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "UM_ADB_GovrA010c.checkPayment Exception");
            throw sqle;
        }
        catch (Exception exc) {
            Log.errors(this, exc, "UM_ADB_GovrA010c.checkPayment Exception");
            throw exc;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex4) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex5) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex6) {}
            }
        }
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex9) {}
        }
        return "";
    }
    
    public String getUserTestOptionYn(final String userId) throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT TEST_OPTION_YN \n";
        sql = String.valueOf(sql) + " FROM USEMN.UM_USER A \n";
        sql = String.valueOf(sql) + " where A.USER_ID='" + userId + "' \n";
        
            try {
                trx = new Trx(this);
                con = trx.getConnection();
                pstmt = con.prepareStatement(sql);
                rs = pstmt.executeQuery();
                if (!rs.next()) {
            	return "1";
            } else {
                if (rs.getString(1) != null) {
                    String testOptionYn = "";
                    testOptionYn = rs.getString(1);
	                
                    return testOptionYn;
                }
            
                return "";
            }
        }
            catch (SQLException sqle) {
                Log.errors(this, sqle, "UM_ADB_GovrA010c.checkPayment Exception");
                throw sqle;
            }
            catch (Exception exc) {
                Log.errors(this, exc, "UM_ADB_GovrA010c.checkPayment Exception");
                throw exc;
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
                if (con != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex9) {}
                }
            }
        }
    
    public UM_URV_UserC020b getCurrentPaymentPolicy() throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final Vector vec = new Vector();
        String sql = "SELECT REG_PAYMENT_TYPE, EXTEND_REMIND_MSG, EXTEND_PAYMENT_MSG, \n";
        sql = String.valueOf(sql) + " EXTEND_PAYMENT_TYPE, USE_YN, ROUND(SYSDATE-REG_START_DT) REG_START, \n";
        sql = String.valueOf(sql) + " EXTEND_START_DT, EFFECT_MONTHS, EXTEND_REMIND_MSG_DAYS, ONLINE_PAYMENT \n";
        sql = String.valueOf(sql) + " FROM BID.BID_PAYMENT_CONFIG A \n";
        sql = String.valueOf(sql) + " where A.USE_YN='Y' \n";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            final UM_URV_UserC020b ret = new UM_URV_UserC020b();
            if (rs.next()) {
                ret.setS01(this.retSpace(rs.getString(1)));
                ret.setS02(this.retSpace(rs.getString(2)));
                ret.setS03(this.retSpace(rs.getString(3)));
                ret.setS04(this.retSpace(rs.getString(4)));
                ret.setS05(this.retSpace(rs.getString(5)));
                ret.setS06(this.retSpace(rs.getString(6)));
                ret.setS07(this.retSpace(rs.getString(7)));
                ret.setS08(this.retSpace(rs.getString(8)));
                ret.setS09(this.retSpace(rs.getString(9)));
                ret.setS10(this.retSpace(rs.getString(10)));
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
                if (con != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex3) {}
                }
                return ret;
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "UM_ADB_GovrA010c.getCurrentPaymentPolicy Exception");
            throw sqle;
        }
        catch (Exception exc) {
            Log.errors(this, exc, "UM_ADB_GovrA010c.getCurrentPaymentPolicy Exception");
            throw exc;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex4) {}
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex5) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex6) {}
            }
        }
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
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex9) {}
        }
        return null;
    }
}
