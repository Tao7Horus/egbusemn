// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Trx;
import java.util.Vector;
import common.Log;
import entity.UM_ADV_GovuA030b;

public class UM_URB_GovuA030c
{
    public UM_ADV_GovuA030b[] approvalList(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String area, final String instituCd) {
        Trx trx = null;
        Connection con = null;
        Log.debug("test vao ham nay chua?");
        UM_ADV_GovuA030b[] ett = (UM_ADV_GovuA030b[])null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        final String StartTime = String.valueOf(Start) + "00:00:01";
        final String EndTime = String.valueOf(End) + "23:59:59";
        final int psmt_inx = 0;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t            \t\t\t\t\t\t\t\t\n");
            sb.append(" \t\tREG_YN,\t\t\tRECV_NO,   INSTITU_CD,\tCERT_RECV_NO,  RECV_DT , CD_NM2, NUM, GROUP_NO, DEPARTMENT_NO \n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR||' ' ||DETAIL_ADDR ADDR, REG_YN,            \t\n");
            sb.append("    RECV_NO,   INSTITU_CD,   CERT_RECV_NO,  TO_CHAR(RECV_DT,'YYYY-MM-DD') RECV_DT, a.CD_NM2 CD_NM2, GROUP_NO, DEPARTMENT_NO                  \n");
            sb.append("   FROM\tUM_REC_PUB_INSTITU_MAST, SYN_PUB_CODE a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE\ta.CD = ZIP_CD and a.CD_CLS = 'GU7'\t\n");
            if (!"".equals(ApprovalCode) && !"null".equals(ApprovalCode)) {
                sb.append("    AND PERMIT_BRANCH = '" + ApprovalCode + "'\t\t    \t\t\t\t\t\t\t\t\t\t\t\t    \t\n");
            }
            if (!upcheNm.equals("")) {
                sb.append(" AND\t\tlower(INSTITU_FULL_NM) like '%" + upcheNm.toLowerCase().trim() + "%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (!"".equals(Start) && !"".equals(End)) {
                sb.append(" AND\t\tRECV_DT BETWEEN TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                sb.append("AND TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')\t    \n");
            }
            else if (!"".equals(Start) && "".equals(End)) {
                sb.append(" AND\t\tRECV_DT > TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
            }
            else if ("".equals(Start) && !"".equals(End)) {
                sb.append(" AND\t\tRECV_DT < TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
            }
            if (total.equals("1")) {
                sb.append(" AND\t\tREG_YN ='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("2")) {
                sb.append(" AND\t\tREG_YN ='N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("3")) {
                sb.append(" AND\t\tREG_YN ='E'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("4")) {
                sb.append(" AND\t\tREG_YN ='D'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("5")) {
                sb.append(" AND\t\tREG_YN ='C'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (!"".equals(area)) {
                sb.append(" AND\t\tZIP_CD ='" + area + "'\t\t\t\t\t\n");
            }
            if (!"".equals(instituCd)) {
                sb.append(" AND\t\tlower(INSTITU_CD) ='" + instituCd.toLowerCase().trim() + "'\t\t\t\t\t\n");
            }
            sb.append("\t\t\tORDER BY RECV_DT DESC, INSTITU_CD DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")\t\n");
            sql = sb.toString();
            Log.debug("Cau lenh SQL: " + sql);
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            UM_ADV_GovuA030b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_ADV_GovuA030b();
                ett_list.setgoNameFull(rs.getString(1));
                ett_list.setsaupNo(rs.getString(2));
                ett_list.settaskmaster(rs.getString(3));
                ett_list.setADDR(rs.getString(4));
                ett_list.setenYn(rs.getString(5));
                ett_list.setdependCode(rs.getString(6));
                ett_list.setg2bCode(rs.getString(7));
                ett_list.setLicenseCode(rs.getString(8));
                ett_list.setrecept(rs.getString(9));
                ett_list.setZIPCODE(rs.getString(10));
                ett_list.setGroupNo(rs.getInt(12));
                ett_list.setDepartmentNo(rs.getInt(13));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
        ett = new UM_ADV_GovuA030b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public UM_ADV_GovuA030b[] approvalList_001(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String area, final String instituCd) {
        Trx trx = null;
        Connection con = null;
        Log.debug("test vao ham nay chua?");
        UM_ADV_GovuA030b[] ett = (UM_ADV_GovuA030b[])null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        final String StartTime = String.valueOf(Start) + "00:00:01";
        final String EndTime = String.valueOf(End) + "23:59:59";
        final int psmt_inx = 0;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t            \t\t\t\t\t\t\t\t\n");
            sb.append(" \t\tREG_YN,\t\t\tRECV_NO,   INSTITU_CD,\tCERT_RECV_NO,  RECV_DT , CD_NM2, NUM, GROUP_NO, DEPARTMENT_NO \n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR||' ' ||DETAIL_ADDR ADDR, REG_YN,            \t\n");
            sb.append("    RECV_NO,   INSTITU_CD,   CERT_RECV_NO,  TO_CHAR(RECV_DT,'YYYY-MM-DD') RECV_DT, a.CD_NM2 CD_NM2, GROUP_NO, DEPARTMENT_NO                  \n");
            sb.append("   FROM\tUM_REC_PUB_INSTITU_MAST, SYN_PUB_CODE a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE\ta.CD = ZIP_CD and a.CD_CLS = 'GU7'\t\n");
            if (!"".equals(ApprovalCode) && !"null".equals(ApprovalCode)) {
                sb.append("    AND PERMIT_BRANCH = '" + ApprovalCode + "'\t\t    \t\t\t\t\t\t\t\t\t\t\t\t    \t\n");
            }
            if (!upcheNm.equals("")) {
                sb.append(" AND\t\tlower(INSTITU_FULL_NM) like '%" + upcheNm.toLowerCase().trim() + "%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (!"".equals(Start) && !"".equals(End)) {
                sb.append(" AND\t\tRECV_DT BETWEEN TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                sb.append("AND TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')\t    \n");
            }
            else if (!"".equals(Start) && "".equals(End)) {
                sb.append(" AND\t\tRECV_DT > TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
            }
            else if ("".equals(Start) && !"".equals(End)) {
                sb.append(" AND\t\tRECV_DT < TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
            }
            if (total.equals("1")) {
                sb.append(" AND\t\tREG_YN ='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("2")) {
                sb.append(" AND\t\tREG_YN ='N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("3")) {
                sb.append(" AND\t\tREG_YN ='E'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("4")) {
                sb.append(" AND\t\tREG_YN ='D'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("5")) {
                sb.append(" AND\t\tREG_YN ='C'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (!"".equals(area)) {
                sb.append(" AND\t\tZIP_CD ='" + area + "'\t\t\t\t\t\n");
            }
            if (!"".equals(instituCd)) {
                sb.append(" AND\t\tlower(INSTITU_CD) ='" + instituCd.toLowerCase().trim() + "'\t\t\t\t\t\n");
            }
            sb.append("\t\t\tORDER BY RECV_DT DESC, INSTITU_CD DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")\t\n");
            sql = sb.toString();
            Log.debug("Cau lenh SQL: " + sql);
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            UM_ADV_GovuA030b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_ADV_GovuA030b();
                ett_list.setgoNameFull(rs.getString(1));
                ett_list.setsaupNo(rs.getString(2));
                ett_list.settaskmaster(rs.getString(3));
                ett_list.setADDR(rs.getString(4));
                ett_list.setenYn(rs.getString(5));
                ett_list.setdependCode(rs.getString(6));
                ett_list.setg2bCode(rs.getString(7));
                ett_list.setLicenseCode(rs.getString(8));
                ett_list.setrecept(rs.getString(9));
                ett_list.setZIPCODE(rs.getString(10));
                ett_list.setGroupNo(rs.getInt(12));
                ett_list.setDepartmentNo(rs.getInt(13));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
        ett = new UM_ADV_GovuA030b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public UM_ADV_GovuA030b[] approvalList(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode) {
        Trx trx = null;
        Connection con = null;
        Log.debug("test vao ham nay chua?");
        UM_ADV_GovuA030b[] ett = (UM_ADV_GovuA030b[])null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        int psmt_inx = 0;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t            \t\t\t\t\t\t\t\t\n");
            sb.append(" \t\tREG_YN,\t\t\tRECV_NO,   INSTITU_CD,\tCERT_RECV_NO,  RECV_DT, NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR||' ' ||DETAIL_ADDR ADDR, REG_YN,            \t\n");
            sb.append("    RECV_NO,   INSTITU_CD,   CERT_RECV_NO,  TO_CHAR(RECV_DT,'YYYY-MM-DD') RECV_DT                  \n");
            sb.append("   FROM\tUM_REC_PUB_INSTITU_MAST\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE\t1=1\t\n");
            if (!"".equals(ApprovalCode) && !"null".equals(ApprovalCode)) {
                sb.append("    AND PERMIT_BRANCH = '" + ApprovalCode + "'\t\t    \t\t\t\t\t\t\t\t\t\t\t\t    \t\n");
            }
            if (flag.equals("s")) {
                if (!upcheNm.equals("")) {
                    sb.append(" AND\t\tINSTITU_FULL_NM like '%'||?||'%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                }
                if (!Start.equals("")) {
                    sb.append(" AND\tRECV_DT BETWEEN TO_DATE(" + StartTime + ", 'DD/MM/YYYY HH24:MI:SS')     \n");
                    sb.append(" AND TO_DATE(" + EndTime + ", 'DD/MM/YYYY HH24:MI:SS')\t    \n");
                }
            }
            if (total.equals("1")) {
                sb.append(" AND\t\tREG_YN ='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("2")) {
                sb.append(" AND\t\tREG_YN ='N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("3")) {
                sb.append(" AND\t\tREG_YN ='E'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("4")) {
                sb.append(" AND\t\tREG_YN ='D'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("5")) {
                sb.append(" AND\t\tREG_YN ='C'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            sb.append("\t\t\tORDER BY RECV_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")\t\n");
            sql = sb.toString();
            Log.debug("Cau lenh SQL: " + sql);
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            if (flag.equals("s") && !upcheNm.equals("")) {
                ++psmt_inx;
                psmt.setString(psmt_inx, upcheNm);
            }
            UM_ADV_GovuA030b ett_list = null;
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett_list = new UM_ADV_GovuA030b();
                ett_list.setgoNameFull(rs.getString(1));
                ett_list.setsaupNo(rs.getString(2));
                ett_list.settaskmaster(rs.getString(3));
                ett_list.setADDR(rs.getString(4));
                ett_list.setenYn(rs.getString(5));
                ett_list.setdependCode(rs.getString(6));
                ett_list.setg2bCode(rs.getString(7));
                ett_list.setLicenseCode(rs.getString(8));
                ett_list.setrecept(rs.getString(9));
                vec.addElement(ett_list);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
        ett = new UM_ADV_GovuA030b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public int approvalList_count(final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String area, final String instituCd) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        final int psmt_inx = 0;
        int count = 0;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  USEMN.UM_REC_PUB_INSTITU_MAST, SYN_PUB_CODE C\t\t\t\n");
            sb.append(" WHERE\t\tZIP_CD = C.CD AND C.CD_CLS = 'GU7'\t\t\t\t\n");
            if (total.equals("1")) {
                sb.append(" AND\t\tREG_YN ='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("2")) {
                sb.append(" AND\t\tREG_YN ='N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("3")) {
                sb.append(" AND\t\tREG_YN ='E'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("4")) {
                sb.append(" AND\t\tREG_YN ='D'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("5")) {
                sb.append(" AND\t\tREG_YN ='C'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            Log.debug("Tham so: " + ApprovalCode);
            if (!"".equals(ApprovalCode) && !"null".equals(ApprovalCode)) {
                sb.append(" AND\t\tPERMIT_BRANCH ='" + ApprovalCode + "'\t\t\t\t\t\n");
            }
            if (!"".equals(area) && area != null) {
                sb.append(" AND\t\tZIP_CD ='" + area + "'\t\t\t\t\t\n");
            }
            if (!"".equals(instituCd)) {
                sb.append(" AND\t\tlower(INSTITU_CD) ='" + instituCd.toLowerCase().trim() + "'\t\t\t\t\t\n");
            }
            if (!upcheNm.equals("")) {
                sb.append(" AND\t\tlower(INSTITU_FULL_NM) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
            }
            if (!Start.equals("") && !End.equals("")) {
                sb.append(" AND\t\tRECV_DT BETWEEN TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                sb.append("  AND TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')\t \n");
            }
            else if (!Start.equals("") && End.equals("")) {
                sb.append(" AND\t\tRECV_DT > TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
            }
            else if (Start.equals("") && !End.equals("")) {
                sb.append(" AND\t\tRECV_DT < TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
            }
            sql = sb.toString();
            Log.errors("totalCount:" + sql);
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
                Log.errors("totalCount:" + count);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public int approvalList_count(final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final String StartTime = String.valueOf(Start.replace('/', '-')) + " 00:00:01";
        final String EndTime = String.valueOf(End.replace('/', '-')) + " 23:59:59";
        int psmt_inx = 0;
        int count = 0;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  UM_REC_PUB_INSTITU_MAST\t\t\t\n");
            if (total.equals("1")) {
                sb.append(" WHERE\t\tREG_YN ='Y'\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("2")) {
                sb.append(" WHERE\t\tREG_YN ='N'\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("3")) {
                sb.append(" WHERE\t\tREG_YN ='E'\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("4")) {
                sb.append(" WHERE\t\tREG_YN ='D'\t\t\t\t\t\t\t\n");
            }
            else if (total.equals("5")) {
                sb.append(" WHERE\t\tREG_YN ='C'\t\t\t\t\t\t\t\n");
            }
            else {
                sb.append(" WHERE\t\tREG_YN like '%%'  \t\t\t\t\t\n");
            }
            Log.debug("Tham so: " + ApprovalCode);
            if (!"".equals(ApprovalCode) && !"null".equals(ApprovalCode)) {
                sb.append(" AND\t\tPERMIT_BRANCH ='" + ApprovalCode + "'\t\t\t\t\t\n");
            }
            if (flag.equals("s")) {
                if (!upcheNm.equals("")) {
                    sb.append(" AND\t\tINSTITU_FULL_NM like '%'||?||'%'\t\n");
                }
                if (!Start.equals("")) {
                    sb.append(" AND\tRECV_DT BETWEEN TO_DATE(" + StartTime + ", 'DD-MM-YYYY HH24:MI:SS')     \n");
                    sb.append(" AND TO_DATE(" + EndTime + ", 'DD-MM-YYYY HH24:MI:SS')\t \n");
                }
            }
            sql = sb.toString();
            Log.debug("Cau lenh SQL: " + sql);
            psmt = con.prepareStatement(sql);
            if (flag.equals("s") && !upcheNm.equals("")) {
                ++psmt_inx;
                psmt.setString(psmt_inx, upcheNm);
                Log.debug("Tham so thu 1: " + upcheNm);
            }
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString() + StartTime + EndTime);
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
}
