// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.OneRowEntity;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import common.ComStr;
import java.util.Vector;
import entity.UM_RAE_ConuB010b;
import entity.UM_RAE_ConuB010b2;

public class UM_ADB_DSTT
{
    public UM_RAE_ConuB010b[] select_comlist(final int pagenum, final int PAGEMAX, String ready, String delay, String refuse, String cancel, String saupNo, final String sangho, final String sdate, final String edate, final String id, final String jichung) {
        Connection con = null;
        Trx trx = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_RAE_ConuB010b[] ettlist = (UM_RAE_ConuB010b[])null;
        saupNo = ComStr.replace(saupNo, "-", "");
        ready = ((ready == null) ? "" : ready);
        delay = ((delay == null) ? "" : delay);
        refuse = ((refuse == null) ? "" : refuse);
        cancel = ((cancel == null) ? "" : cancel);
        String sql = " SELECT BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, ZIP_CD, ADDR,\t\t\t\t\t\t\t\t\t\t\t        DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_ST, PROCESS_RSON, XML_DOC_LOCATE, TRANS_NO, EMAIL, ROWNUM N  FROM\t\t\t\t\t\t        ( SELECT BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, ZIP_CD, ADDR,\t\t\t\t\t\t\t\t\t                 DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_ST, PROCESS_RSON, XML_DOC_LOCATE, TRANS_NO, EMAIL, ROWNUM N\t\t\t\t\t            FROM (SELECT a.BIZ_REG_NO, a.MOD_CLS, a.REG_DT, a.COM_NM, a.REPR_NM, a.ZIP_CD, a.ADDR,\t\t\t\t     \t\t               a.DETAIL_ADDR, a.PHONE_NO, a.WEBSITE, a.PROCESS_ST, a.PROCESS_RSON, a.XML_DOC_LOCATE, a.TRANS_NO, a.EMAIL\t\t\t                  FROM UM_EDOC_STATE a, UM_REC_SUPPLIER_ENTER_MAST b                  \t\t\t\t\t\t\t\t\t\t                   WHERE a.BIZ_REG_NO = b.BIZ_REG_NO(+) and a.MOD_CLS = '1'     \t\t\t\t\t\t\t\t\t \t\t\t\t\t   AND b.BID_ATTEND_QUALIFY_YN IS NULL\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        if (ready.length() == 0 && delay.length() == 0 && refuse.length() == 0 && cancel.length() == 0) {
            sql = String.valueOf(sql) + " \t                   AND a.PROCESS_ST NOT IN('1','3','4')      \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        }
        if (!jichung.equals("XX")) {
            sql = String.valueOf(sql) + " \t\t             AND a.PERMIT_BRANCH = '" + jichung + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        }
        if (saupNo.length() > 0) {
            sql = String.valueOf(sql) + " AND a.BIZ_REG_NO like '%" + saupNo + "%'";
        }
        if (sangho.length() > 0) {
            sql = String.valueOf(sql) + " AND a.COM_NM like '%" + sangho + "%'";
        }
        if (sdate.length() > 0 && edate.length() > 0) {
            sql = String.valueOf(sql) + " AND a.REG_DT>='" + sdate + "'";
            sql = String.valueOf(sql) + " AND a.REG_DT<='" + edate + "'";
        }
        if (ready.length() > 0 || delay.length() > 0 || refuse.length() > 0 || cancel.length() > 0) {
            sql = String.valueOf(sql) + " AND ( a.PROCESS_ST IN (";
            if (ready.length() > 0) {
                sql = String.valueOf(sql) + ready;
            }
            else {
                sql = String.valueOf(sql) + "7";
            }
            if (delay.length() > 0) {
                sql = String.valueOf(sql) + "," + delay;
            }
            else {
                sql = String.valueOf(sql) + ",7";
            }
            if (refuse.length() > 0) {
                sql = String.valueOf(sql) + "," + refuse;
            }
            else {
                sql = String.valueOf(sql) + ",7";
            }
            if (cancel.length() > 0) {
                sql = String.valueOf(sql) + "," + cancel;
            }
            else {
                sql = String.valueOf(sql) + ",7";
            }
            sql = String.valueOf(sql) + "))";
        }
        sql = String.valueOf(sql) + " order by a.REG_DT desc ) WHERE ROWNUM <= (" + pagenum + " * " + PAGEMAX + ") ) sub WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                final UM_RAE_ConuB010b ett = new UM_RAE_ConuB010b();
                ett.setSaupNo(rs.getString(1));
                ett.setSinchungGubun(rs.getString(2));
                ett.setSinchungDate(rs.getString(3));
                ett.setSangho(rs.getString(4));
                ett.setCeoName(rs.getString(5));
                ett.setZipCode(rs.getString(6));
                ett.setAddr(rs.getString(7));
                ett.setRestAddr(rs.getString(8));
                ett.setTel(rs.getString(9));
                ett.setHomepage(rs.getString(10));
                ett.setProcessState(rs.getString(11));
                ett.setChuriReason(rs.getString(12));
                ett.setXmlPosition(rs.getString(13));
                ett.setTransNo(rs.getString(14));
                ett.setMail(rs.getString(15));
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p.java's select_userlist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p.select_userlist block Exception : " + exc.toString());
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
        
        if (vec.size() > 0) {
            ettlist = new UM_RAE_ConuB010b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public int approval_list_CountQ(final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String Cert_cls, final String bizRegNo, final String status) {
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
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            if ("4".equals(status)) {
                sb.append(" \t\tSELECT COUNT(DISTINCT  a.BIZ_REG_NO)  ");
                sb.append(" FROM USEMN.UM_SUPPLIER_ENTER_MAST a      ");
                sb.append(" JOIN USEMN.UM_USER c ON a.BIZ_REG_NO=c.MAST_CD AND c.USER_CLS ='c' ");
            }
            else {
                sb.append(" \t\tSELECT COUNT(DISTINCT a.BIZ_REG_NO)  ");
                sb.append(" FROM USEMN.UM_REC_SUPPLIER_ENTER_MAST a      ");
                sb.append(" JOIN USEMN.UM_REC_PUB_INSTITU_CERT c ON a.BIZ_REG_NO =c.BIZ_REG_NO ");
                sb.append(" AND c.CERT_CLS ='S' ");
            }
            where = String.valueOf(where) + "\tWHERE  a.REG_DT IS NOT NULL AND ";
            where = String.valueOf(where) + this.getQueryStatus(status);
            if (bizRegNo != null && !bizRegNo.equals("")) {
                where = String.valueOf(where) + " AND a.BIZ_REG_NO like '%" + bizRegNo + "%'";
            }
            if (flag.equals("S")) {
                if (upcheNm.length() >= 1) {
                    where = String.valueOf(where) + " AND\t\tlower(a.BIZ_NM) like '%" + upcheNm.toLowerCase().trim() + "%'   \t\t\n";
                }
                if (!"".equals(Start) && !"".equals(End)) {
                    where = String.valueOf(where) + " AND\t\ta.REG_DT BETWEEN TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
                    where = String.valueOf(where) + " AND TO_DATE('" + EndTime + "',   'DD/MM/YYYY HH24:MI:SS')\t\n";
                }
                else if (!"".equals(Start) && "".equals(End)) {
                    where = String.valueOf(where) + " AND\t\ta.REG_DT > TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
                }
                else if ("".equals(Start) && !"".equals(End)) {
                    where = String.valueOf(where) + " AND\t\ta.REG_DT < TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
                }
            }
            sb.append(where);
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
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
    
    public int approval_list_CountH(final String instituCd, final String bizRegNo, final String updateDtS, final String updateDtE, final String regDts, final String regDtE, final String Start, final String End, final String total) {
        Connection con = null;
        Trx trx = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        int count = 0;
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            sb.append(" \t\tSELECT COUNT(*)  ");
            sb.append(" \t\tFROM UM_REC_SUPPLIER_ENTER_MAST a  ");
            sb.append("\t\tJOIN UM_REC_PUB_INSTITU_CERT c ON  a.BIZ_REG_NO = c.BIZ_REG_NO\t");
            sb.append(" AND  c.CERT_CLS ='S'  ");
            sb.append("\t\tWHERE  a.REG_DT IS NOT NULL  \t");
            if (bizRegNo != null && !bizRegNo.equals("")) {
                sb.append(" AND a.BIZ_REG_NO like '%" + bizRegNo + "%'");
            }
            if (!"".equals(Start) && !"".equals(End)) {
                sb.append(" AND\t\ta.REG_DT BETWEEN TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                sb.append(" AND TO_DATE('" + EndTime + "',   'DD/MM/YYYY HH24:MI:SS')\t\n");
            }
            else if (!"".equals(Start) && "".equals(End)) {
                sb.append(" AND\t\ta.REG_DT > TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
            }
            else if ("".equals(Start) && !"".equals(End)) {
                sb.append(" AND\t\ta.REG_DT < TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
            }
            sql = sb.toString();
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
    
    public OneRowEntity[] approval_listQ(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String Cert_cls, final String instituCd, final String status) {
        Connection con = null;
        Trx trx = null;
        OneRowEntity ett = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final Vector vec = new Vector();
        final String sql = this.getSqlRegistStatus(pagenum, PAGEMAX, upcheNm, Start, End, flag, total, branchOffi1, Cert_cls, instituCd, status);
        try {
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ett = new OneRowEntity();
                ett.dataArraySetting(16);
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
                ett.data[15] = this.retSpace(this.getRegistStatus(ett.data[1], ett.data[2], ett.data[3], ett.data[4], ett.data[5], ett.data[6], ett.data[7], status));
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, "UM_URV_UserD020p.select_user block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.errors(this, exc, "UM_URV_UserD020p.select_user block Exception : " + exc.toString());
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
        
        final OneRowEntity[] ettList = new OneRowEntity[vec.size()];
        vec.copyInto(ettList);
        return ettList;
    }
    
    public String getRegistStatus(final String regYn, final String recCertYn, final String recIssueYn, final String recCertNm, final String approveRegCd, final String regContent, final String isUser, final String status) {
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
    
    public String getSqlRegistStatus(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String branchOffi1, final String Cert_cls, final String instituCd, final String status) {
        final String StartTime = String.valueOf(Start) + " 00:00:01";
        final String EndTime = String.valueOf(End) + " 23:59:59";
        final String regDtQuery = "4".equals(status) ? " REG_DT " : " REG_DT ";
        final String order = " ORDER BY " + regDtQuery + " DESC, RECV_NO DESC ";
        String where = " WHERE " + regDtQuery + " IS NOT NULL     ";
        if (!instituCd.equals("") && instituCd != null) {
            where = String.valueOf(where) + " AND a.BIZ_REG_NO like '%" + instituCd.toLowerCase().trim() + "%'";
        }
        if (flag.equals("S")) {
            if (upcheNm.length() >= 1) {
                where = String.valueOf(where) + " AND\t\tlower(a.BIZ_NM) like '%" + upcheNm.toLowerCase().trim() + "%'   \t\t\n";
            }
            if (!"".equals(Start) && !"".equals(End)) {
                where = String.valueOf(where) + " AND\t\t" + regDtQuery + " BETWEEN TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
                where = String.valueOf(where) + " AND TO_DATE('" + EndTime + "',   'DD/MM/YYYY HH24:MI:SS')\t\n";
            }
            else if (!"".equals(Start) && "".equals(End)) {
                where = String.valueOf(where) + " AND\t\t" + regDtQuery + " > TO_DATE('" + StartTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
            }
            else if ("".equals(Start) && !"".equals(End)) {
                where = String.valueOf(where) + " AND\t\t" + regDtQuery + " < TO_DATE('" + EndTime + "', 'DD/MM/YYYY HH24:MI:SS')     \n";
            }
        }
        if (branchOffi1.length() > 1) {
            where = String.valueOf(where) + " AND\t\tc.PERMIT_BRANCH ='" + branchOffi1 + "'    \t\n";
        }
        where = String.valueOf(where) + " AND " + this.getQueryStatus(status);
        final StringBuffer select = new StringBuffer();
        final StringBuffer sb = new StringBuffer();
        select.append("\tSELECT   REG_DT, REG_YN, REC_CERT_YN,  REC_ISSUE_YN, REC_CERT_NM, APPROVE_REQ_CD,    ");
        select.append("\t RECV_CONTENT,  IS_USER ,BIZ_NM, BIZ_REG_NO, CHRGR_MN ,ADDR,      ");
        select.append("\t INSTITU_CD, NUM, RECV_NO FROM (     SELECT A.* , ROWNUM    NUM FROM (          ");
        if ("4".equals(status)) {
            select.append(" SELECT DISTINCT TO_CHAR(" + regDtQuery + ",'DD-MM-YYYY') REG_DT, '' REG_YN, '' REC_CERT_YN, '' REC_ISSUE_YN,   ");
            select.append(" '' REC_CERT_NM, '' APPROVE_REQ_CD, '' RECV_CONTENT, '' INSTITU_CD, a.BIZ_NM,  ");
            select.append(" a.BIZ_REG_NO, c.CHRGR_NM CHRGR_MN, a.ADDR   , '' RECV_NO, ");
            select.append(" '' IS_USER ");
            select.append(" FROM USEMN.UM_SUPPLIER_ENTER_MAST a      ");
            select.append(" JOIN USEMN.UM_USER c ON a.BIZ_REG_NO=c.MAST_CD AND c.USER_CLS ='c' ");
        }
        else {
            select.append(" SELECT DISTINCT TO_CHAR(" + regDtQuery + ",'DD-MM-YYYY') REG_DT, a.REG_YN, c.REG_YN REC_CERT_YN, c.ISSUE_YN REC_ISSUE_YN,   ");
            select.append(" c.CERT_NM REC_CERT_NM, c.APPROVE_REQ_CD, c.RECV_CONTENT, c.INSTITU_CD, a.BIZ_NM,  ");
            select.append(" a.BIZ_REG_NO, c.CHRGR_MN,a.ADDR   , c.RECV_NO, ");
            if ("".equals(status)) {
                select.append(" (SELECT DECODE(COUNT(*),0,'N', 'Y') from USEMN.UM_USER d where c.INSTITU_CD=d.MAST_CD AND USER_CLS ='c' ) as IS_USER ");
            }
            else {
                select.append(" 'N' IS_USER ");
            }
            select.append(" FROM USEMN.UM_REC_SUPPLIER_ENTER_MAST a      ");
            select.append(" JOIN USEMN.UM_REC_PUB_INSTITU_CERT c ON a.BIZ_REG_NO =c.BIZ_REG_NO ");
            select.append(" AND c.CERT_CLS ='S' ");
        }
        sb.append(select);
        sb.append(where);
        sb.append(String.valueOf(order) + "  \t\t\t\t\n");
        sb.append("\t\t\t) A     \t\t\t\n");
        sb.append("\t\t\t)      \t\t\t\n");
        sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + "\t)   \n");
        return sb.toString();
    }
    
    public String getQueryStatus(final String status) {
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
    
    public UM_RAE_ConuB010b[] getResultList(final int pageNum, final int pageMAX, final String saupNo, final String sangho, final String sdate, final String edate, final String status) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final Vector vec = new Vector();
        UM_RAE_ConuB010b[] ettlist = (UM_RAE_ConuB010b[])null;
        final int start = (pageNum - 1) * pageMAX + 1;
        final int end = pageNum * pageMAX;
        try {
            String sql = "";
            if ("D".equals(status) || "C".equals(status)) {
                sql = "select * from (select t1.BIZ_REG_NO, t1.COM_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t1.REPR_NM, t1.PROCESS_ST, ROWNUM N from UM_EDOC_STATE t1 where PROCESS_ST in ('3', '4') ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like '%" + sangho + "%'";
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')";
                }
                else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
                    sql = String.valueOf(sql) + " and t1.REG_DT > to_date('" + sdate + "', 'dd/mm/yyyy')";
                }
                else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT < to_date('" + edate + "', 'dd/mm/yyyy')";
                }
                sql = String.valueOf(sql) + " order by t1.REG_DT DESC";
                sql = String.valueOf(sql) + ") where N between " + start + " and " + end;
            }
            else {
                sql = "select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, REG_YN, RECV_NO , ROWNUM N, DECODE(PAID, NULL, 'Chưa thanh toán', 'Đã thanh toán') PAID from (  select t1.BIZ_REG_NO, t1.BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.REG_YN, t1.RECV_NO, C.PAID from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2 , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1) GROUP BY BIZ_REG_NO) C where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like '%" + sangho + "%'";
                }
                if (status == null || status.length() == 0) {
                    sql = String.valueOf(sql) + " and  t1.REG_YN ='N' ";
                }
                else if (status != null && status.length() != 0 && !"A".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.REG_YN ='" + status + "' ";
                }
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')";
                }
                else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
                    sql = String.valueOf(sql) + " and t1.REG_DT > to_date('" + sdate + "', 'dd/mm/yyyy')";
                }
                else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT < to_date('" + edate + "', 'dd/mm/yyyy')";
                }
                sql = String.valueOf(sql) + " order by t1.REG_DT DESC, recv_no asc";
                sql = String.valueOf(sql) + ")) t3 where t3.N between " + start + " and " + end;
            }
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
//            rs = pstm.executeQuery();
//            pstm.clearParameters();
            while (rs.next()) {
                final UM_RAE_ConuB010b ett = new UM_RAE_ConuB010b();
                ett.setSaupNo(rs.getString(1));
                ett.setSangho(rs.getString(2));
                ett.setSinchungDate(rs.getString(3));
                ett.setCeoName(rs.getString(4));
                ett.setProcessState(rs.getString(5));
                if (!"D".equals(status) && !"C".equals(status)) {
                    ett.setApprovalOrgCode(rs.getString(6));
                    ett.setPaidStatus(rs.getString(8));
                }
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p.Max_count block Exception : " + exc.toString());
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
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        if (vec.size() > 0) {
            ettlist = new UM_RAE_ConuB010b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public UM_RAE_ConuB010b2[] getResultListPM(final int pageNum, final int pageMAX, final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String pmstatus) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final Vector vec = new Vector();
        UM_RAE_ConuB010b2[] ettlist = (UM_RAE_ConuB010b2[])null;
        final int start = (pageNum - 1) * pageMAX + 1;
        final int end = pageNum * pageMAX;
        try {
            String sql = "Select BIZ_REG_NO,CREATE_DATE,DECODE(PAYMENT_METHOD,'3','Thanh toán Phí tham dự thầu','Thanh toán phí đăng ký, phí duy trì') Method, DECODE(STATUS, 'N', 'Chưa thanh toán', 'Đã thanh toán') PAID,PM_YEAR,BID_NO,BID_TURN_NO FROM ENTRYBID.BID_PAYMENT_TEMPORARY";
//            if ("D".equals(status) || "C".equals(status)) {
//                sql = "select * from (select t1.BIZ_REG_NO, t1.COM_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t1.REPR_NM, t1.PROCESS_ST, ROWNUM N, DECODE(PAID, NULL, 'Chưa thanh toán', 'Đã thanh toán') PAID from UM_EDOC_STATE t1 ";
//                sql = String.valueOf(sql) + " , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B";
//                sql = String.valueOf(sql) + " where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1)";
//                sql = String.valueOf(sql) + " GROUP BY BIZ_REG_NO) C";
//                sql = String.valueOf(sql) + " where PROCESS_ST in ('3', '4') ";
//                sql = String.valueOf(sql) + " AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
//                if (saupNo != null && saupNo.length() != 0) {
//                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
//                }
//                if (sangho != null && sangho.length() != 0) {
//                    sql = String.valueOf(sql) + " and t1.BIZ_NM like '%" + sangho + "%'";
//                }
//                if ("D".equals(status)) {
//                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
//                }
//                else if ("C".equals(status)) {
//                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
//                }
//                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
//                    sql = String.valueOf(sql) + " and t1.REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')";
//                }
//                else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
//                    sql = String.valueOf(sql) + " and t1.REG_DT > to_date('" + sdate + "', 'dd/mm/yyyy')";
//                }
//                else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
//                    sql = String.valueOf(sql) + " and t1.REG_DT < to_date('" + edate + "', 'dd/mm/yyyy')";
//                }
//                if (pmstatus.equals("B")) {
//                    sql = String.valueOf(sql) + " and PAID is null";
//                }
//                if (pmstatus.equals("C")) {
//                    sql = String.valueOf(sql) + " and PAID is not null";
//                }
//                sql = String.valueOf(sql) + " order by t1.REG_DT DESC";
//                sql = String.valueOf(sql) + ") where N between " + start + " and " + end;
//            }
//            else {
//                sql = "select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, REG_YN, RECV_NO , ROWNUM N, DECODE(PAID, NULL, 'Chưa thanh toán', 'Đã thanh toán') PAID from (  select t1.BIZ_REG_NO, DECODE(d.BIZ_NM, NULL, t1.BIZ_NM, d.BIZ_NM) BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.REG_YN, t1.RECV_NO, C.PAID from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2 , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1) GROUP BY BIZ_REG_NO) C  , UM_SUPPLIER_ENTER_MAST d   where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y' and t1.BIZ_REG_NO = d.BIZ_REG_NO(+)  AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
//                if (saupNo != null && saupNo.length() != 0) {
//                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
//                }
//                if (sangho != null && sangho.length() != 0) {
//                    sql = String.valueOf(sql) + " and t1.BIZ_NM like '%" + sangho + "%'";
//                }
//                if (status == null || status.length() == 0) {
//                    sql = String.valueOf(sql) + " and  t1.REG_YN ='N' ";
//                }
//                else if (status != null && status.length() != 0 && !"A".equals(status)) {
//                    sql = String.valueOf(sql) + " and  t1.REG_YN ='" + status + "' ";
//                }
//                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
//                    sql = String.valueOf(sql) + " and t1.REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')";
//                }
//                else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
//                    sql = String.valueOf(sql) + " and t1.REG_DT > to_date('" + sdate + "', 'dd/mm/yyyy')";
//                }
//                else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
//                    sql = String.valueOf(sql) + " and t1.REG_DT < to_date('" + edate + "', 'dd/mm/yyyy')";
//                }
//                if (pmstatus.equals("B")) {
//                    sql = String.valueOf(sql) + " and PAID is null";
//                }
//                if (pmstatus.equals("C")) {
//                    sql = String.valueOf(sql) + " and PAID is not null";
//                }
//                sql = String.valueOf(sql) + " order by t1.REG_DT DESC, recv_no asc";
//                sql = String.valueOf(sql) + ")) t3 where t3.N between " + start + " and " + end;
//            }
            Log.debug("son=sql--" + sql);
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
//            rs = pstm.executeQuery();
//            pstm.clearParameters();
            while (rs.next()) {
                final UM_RAE_ConuB010b2 ett = new UM_RAE_ConuB010b2();
                ett.setSaupNo(rs.getString(1));
                ett.setCreateDate(rs.getDate(2).toString());
                ett.setMethod(rs.getString(3));
                ett.setPaid(rs.getString(4));
                ett.setYear(rs.getString(5));
                ett.setBidno(rs.getString(6));
                ett.setBidturnno(rs.getString(7));
//                ett.setSangho(rs.getString(2));
//                ett.setSinchungDate(rs.getString(3));
//                ett.setCeoName(rs.getString(4));
//                ett.setProcessState(rs.getString(5));
//                if (!"D".equals(status) && !"C".equals(status)) {
//                    ett.setApprovalOrgCode(rs.getString(6));
//                    ett.setPaidStatus(rs.getString(8));
//                }
//                else {
//                    ett.setPaidStatus(rs.getString(7));
//                }
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p.Max_count block Exception : " + exc.toString());
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
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
       
        if (vec.size() > 0) {
            ettlist = new UM_RAE_ConuB010b2[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public int max_count(final String saupNo, final String sangho, final String sdate, final String edate, final String status) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            String sql = "";
            if ("D".equals(status) || "C".equals(status)) {
                sql = "select count(*) from UM_EDOC_STATE t1 where t1.PROCESS_ST in ('3', '4') ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like '%" + sangho + "%'";
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT between to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') and to_date(' 23:59:59" + edate + "', 'dd/MM/yyyy HH24:MI:SS')";
                }
                else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
                    sql = String.valueOf(sql) + " and t1.REG_DT > to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT < to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
            }
            else {
                sql = "select count(*)  from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2  where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y' ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like '%" + sangho + "%'";
                }
                if (status == null || status.length() == 0) {
                    sql = String.valueOf(sql) + " and  t1.REG_YN ='N' ";
                }
                else if (status != null && status.length() != 0 && !"A".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.REG_YN ='" + status + "' ";
                }
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT between to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
                else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
                    sql = String.valueOf(sql) + " and t1.REG_DT > to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT < to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
            }
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            Log.debug("sql: " + sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
//            rs = pstm.executeQuery();
//            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p.Max_count block Exception : " + exc.toString());
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
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        return count;
    }
    
    public int max_countPM(final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String pmstatus) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            String sql = "";
            if ("D".equals(status) || "C".equals(status)) {
                sql = "select count(*) from UM_EDOC_STATE t1";
                sql = String.valueOf(sql) + " , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B";
                sql = String.valueOf(sql) + " where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1)";
                sql = String.valueOf(sql) + " GROUP BY BIZ_REG_NO) C";
                sql = String.valueOf(sql) + " where PROCESS_ST in ('3', '4') ";
                sql = String.valueOf(sql) + " AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like '%" + sangho + "%'";
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT between to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') and to_date(' 23:59:59" + edate + "', 'dd/MM/yyyy HH24:MI:SS')";
                }
                else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
                    sql = String.valueOf(sql) + " and t1.REG_DT > to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT < to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
                if (pmstatus.equals("B")) {
                    sql = String.valueOf(sql) + " and PAID is null";
                }
                if (pmstatus.equals("C")) {
                    sql = String.valueOf(sql) + " and PAID is not null";
                }
            }
            else {
                sql = "select count(*)  from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2  , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1) GROUP BY BIZ_REG_NO) C where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like '%" + sangho + "%'";
                }
                if (status == null || status.length() == 0) {
                    sql = String.valueOf(sql) + " and  t1.REG_YN ='N' ";
                }
                else if (status != null && status.length() != 0 && !"A".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.REG_YN ='" + status + "' ";
                }
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT between to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
                else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
                    sql = String.valueOf(sql) + " and t1.REG_DT > to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT < to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
                if (pmstatus.equals("B")) {
                    sql = String.valueOf(sql) + " and PAID is null";
                }
                if (pmstatus.equals("C")) {
                    sql = String.valueOf(sql) + " and PAID is not null";
                }
            }
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            Log.debug("sql: " + sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
//            rs = pstm.executeQuery();
//            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p.Max_count block Exception : " + exc.toString());
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
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        return count;
    }
    
    public int max_count() {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            String sql = "";
            sql = "select count(*)  from UM_SUPPLIER_ENTER_MAST t1, SYN_PUB_CODE t2, UM_REC_REPR t3  where t1.BIZ_REG_NO = t3.BIZ_REG_NO and t3.REPR_ISMAIN = 'Y' and t1.AREA_CD = t2.CD and t2.CD_CLS = 'GU7' and t1.HIDDEN_YN = 'N' and (t1.test_option_yn = 'N' OR t1.test_option_yn is null) ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
//            rs = pstm.executeQuery();
//            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p.Max_count block Exception : " + exc.toString());
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
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        return count;
    }
    
    public int Max_count(String ready, String delay, String refuse, String cancel, String saupNo, final String sangho, final String sdate, final String edate, final String id, final String jichung) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        saupNo = ComStr.replace(saupNo, "-", "");
        ready = ((ready == null) ? "" : ready);
        delay = ((delay == null) ? "" : delay);
        refuse = ((refuse == null) ? "" : refuse);
        cancel = ((cancel == null) ? "" : cancel);
        try {
            String sql = "SELECT count(*) FROM  ( SELECT BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, ZIP_CD, ADDR,          DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_DT, PROCESS_RSON, XML_DOC_LOCATE, TRANS_NO             FROM (SELECT a.BIZ_REG_NO, a.MOD_CLS, a.REG_DT, a.COM_NM, a.REPR_NM, a.ZIP_CD, a.ADDR,      \t\t               a.DETAIL_ADDR, a.PHONE_NO, a.WEBSITE, a.PROCESS_DT, a.PROCESS_RSON, a.XML_DOC_LOCATE, a.TRANS_NO, a.EMAIL \t                  FROM UM_EDOC_STATE a, UM_REC_SUPPLIER_ENTER_MAST b                    WHERE a.BIZ_REG_NO = b.BIZ_REG_NO(+) and a.MOD_CLS = '1'  \t\t\t\t\t   AND b.BID_ATTEND_QUALIFY_YN IS NULL ";
            if (ready.length() == 0 && delay.length() == 0 && refuse.length() == 0 && cancel.length() == 0) {
                sql = String.valueOf(sql) + " \t         AND a.PROCESS_ST NOT IN('1','3', '4')\t\t\t\t\t\t\t\t\t\t\t\t\t";
            }
            if (!jichung.equals("XX")) {
                sql = String.valueOf(sql) + " \t\t     AND a.PERMIT_BRANCH='" + jichung + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
            }
            if (saupNo.length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_REG_NO like '%" + saupNo + "%'";
            }
            if (sangho.length() > 0) {
                sql = String.valueOf(sql) + " AND a.COM_NM like '%" + sangho + "%'";
            }
            if (sdate.length() > 0 && edate.length() > 0) {
                sql = String.valueOf(sql) + " AND a.REG_DT>='" + sdate + "'";
                sql = String.valueOf(sql) + " AND a.REG_DT<='" + edate + "'";
            }
            if (ready.length() > 0 || delay.length() > 0 || refuse.length() > 0 || cancel.length() > 0) {
                sql = String.valueOf(sql) + " AND ( a.PROCESS_ST IN (";
                if (ready.length() > 0) {
                    sql = String.valueOf(sql) + ready;
                }
                else {
                    sql = String.valueOf(sql) + "7";
                }
                if (delay.length() > 0) {
                    sql = String.valueOf(sql) + "," + delay;
                }
                else {
                    sql = String.valueOf(sql) + ",7";
                }
                if (refuse.length() > 0) {
                    sql = String.valueOf(sql) + "," + refuse;
                }
                else {
                    sql = String.valueOf(sql) + ",7";
                }
                if (cancel.length() > 0) {
                    sql = String.valueOf(sql) + "," + cancel;
                }
                else {
                    sql = String.valueOf(sql) + ",7";
                }
                sql = String.valueOf(sql) + "))";
            }
            sql = String.valueOf(sql) + "))";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p.Max_count block Exception : " + exc.toString());
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
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        return count;
    }
    
    public String getJichung(final String id) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String jichung = "";
        try {
            final String sql = "SELECT PERMIT_BRANCH FROM UM_USER WHERE USER_ID = '" + id + "'";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                jichung = rs.getString(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p.Jichung block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p.Jichung block Exception : " + exc.toString());
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
        
        return jichung;
    }
    
    public int getCount(final String fromDate, final String toDate, final String bidType) throws Exception {
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        int count = 0;
        sql = " SELECT   count(*) FROM SYN_BID_BID_MASTER WHERE BID_TYPE = ? AND PUBLIC_YN = 'Y' AND BID_TURN_NO = '00'\n AND (PUBLIC_DT BETWEEN TO_DATE('" + fromDate + "','YYYY/MM/DD') AND TO_DATE('" + toDate + "','YYYY/MM/DD') +0.9999999)";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bidType);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        }
        catch (SQLException sqle) {
            Log.errors(this, sqle, sqle.toString());
            throw sqle;
        }
        catch (Exception exc) {
            Log.errors(this, exc, exc.toString());
            throw exc;
        }
        finally {
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
        }
    }
    
    private String retSpace(final String str) {
        return (str == null) ? "" : str.trim();
    }
    
    public int countCTS(final String bizRegNo) throws Exception {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final String sql = " SELECT count(INSTITU_CD) from (  SELECT INSTITU_CD FROM usemn.UM_REC_PUB_INSTITU_CERT  WHERE CERT_CLS='S' AND INSTITU_CD = ?  UNION ALL SELECT BIZ_REG_NO FROM USEMN.UM_EDOC_STATE WHERE BIZ_REG_NO=? ) ";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, bizRegNo);
            psmt.setString(2, bizRegNo);
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
    
    public OneRowEntity[] selectStatusRegist(final String bizRegNo, final int page_no, final int max) throws Exception {
        final int start = (page_no - 1) * max + 1;
        final int end = page_no * max;
        OneRowEntity[] etts = (OneRowEntity[])null;
        OneRowEntity ett = null;
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final Vector vec = new Vector();
        final String sql = " SELECT INSTITU_FULL_NM,   REG_YN,      APPROVE_REQ_CD, RECV_CONTENT,  INSTITU_CD,  USER_ID , CERT_NM, BIZ_REG_NO,  NUM     FROM (   SELECT B.* , ROWNUM    NUM  FROM (    SELECT  a.INSTITU_FULL_NM,  a.REG_YN,   a.APPROVE_REQ_CD,  a.RECV_CONTENT,  a.INSTITU_CD , d.USER_ID, a.CERT_NM, a.BIZ_REG_NO  FROM usemn.UM_REC_PUB_INSTITU_CERT  a  LEFT JOIN USEMN.UM_CERT_INFO e ON a.CERT_NM=e.CERT_NM LEFT JOIN USEMN.UM_USER d  ON e.USER_ID=d.USER_ID  AND a.INSTITU_CD=d.MAST_CD   WHERE a.CERT_CLS='S' AND a.INSTITU_CD = ?  UNION ALL SELECT a.COM_NM, 'D' REG_YN,'' APPROVE_REQ_CD,  '' RECV_CONTENT, a.BIZ_REG_NO INSTITU_CD, '' USER_ID, '', a.BIZ_REG_NO CERT_NM  FROM USEMN.UM_EDOC_STATE a WHERE  a.PROCESS_ST='3' AND a.BIZ_REG_NO= ?  )\t B )    WHERE  NUM BETWEEN ? AND ? ";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, bizRegNo);
            psmt.setString(2, bizRegNo);
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
                    ett.data[2] = "Đã được phê duyệt<br \\>Số ĐKKD: " + data[i].data[7];
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
    
    public UM_RAE_ConuB010b2[] getResultListPM(int pageNum, int pageMAX)
    {
        Vector vec;
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        vec = new Vector();  
        int start = (pageNum - 1) * pageMAX + 1;
        int end = pageNum * pageMAX;
        try
        {
            String sql = "Select * from (Select BIZ_REG_NO,CREATE_DATE,DECODE(PAYMENT_METHOD,'3','Thanh to\341n Ph\355 tham d\u1EF1 th\u1EA7u','Thanh to\341n ph\355 \u0111\u0103ng k\375, ph\355 duy tr\354') Method, DECODE(STATUS, 'N', 'Ch\u01B0a thanh to\341n', '\u0110\343 thanh to\341n') PAID,PM_YEAR,BID_NO,BID_TURN_NO, ROWNUM N, ID  FROM ENTRYBID.BID_PAYMENT_TEMPORARY ORDER BY ID desc) where N between " + start + " and " + end;
            Log.debug("son=sql--" + sql);
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            UM_RAE_ConuB010b2 ett;
            for(; rs.next(); vec.addElement(ett))
            {
                ett = new UM_RAE_ConuB010b2();
                ett.setSaupNo(rs.getString(1));
                ett.setCreateDate(rs.getDate(2).toString());
                ett.setMethod(rs.getString(3));
                ett.setPaid(rs.getString(4));
                ett.setYear(rs.getString(5));
                ett.setBidno(rs.getString(6));
                ett.setBidturnno(rs.getString(7));
                ett.setTransactionid(String.valueOf(rs.getInt(9)));
            }

        }
        catch(SQLException sqle)
        {
            Log.debug("UM_ADB_ConrA010p.Max_count block SQLException : " + sqle.toString());
        }
        catch(Exception exc)
        {
            Log.debug("UM_ADB_ConrA010p.Max_count block Exception : " + exc.toString());
        }
        finally
        {
            if(rs != null)
                try
                {
                    rs.close();
                }
                catch(Exception exception1) { }
            if(pstm != null)
                try
                {
                    pstm.close();
                }
                catch(Exception exception2) { }
            if(con != null)
                try
                {
                    trx.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
        }
        if(vec.size() > 0)
        {
            UM_RAE_ConuB010b2 ettlist1[] = new UM_RAE_ConuB010b2[vec.size()];
            vec.copyInto(ettlist1);
            return ettlist1;
        } else
        {
            return null;
        }
    }
}
