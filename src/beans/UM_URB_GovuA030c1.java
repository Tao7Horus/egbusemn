// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import javax.servlet.http.HttpServletRequest;
import entity.UM_RAE_ConuB010b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Trx;
import common.Log;
import java.util.Vector;
import entity.UM_ADV_GovuA030b;

public class UM_URB_GovuA030c1
{
    public UM_ADV_GovuA030b[] approvalList(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String instituCD, final String province, final String groupNo, final String depNo, final String test_op_yn, final String hide_yn) {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b[] ett = (UM_ADV_GovuA030b[])null;
        UM_ADV_GovuA030b ettItem = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t\n");
            sb.append(" \t\tRECV_NO,   INSTITU_CD,  REG_DT , CD_NM2, NUM, PERMIT_BRANCH, GROUP_NO, DEP_NO , PHONE_NO       \n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\n");
            sb.append(" SELECT\tB.INSTITU_FULL_NM,\tB.BIZ_REG_NO,\tB.CHRGR_NM,\tB.ADDR|| ' ' ||B.DETAIL_ADDR ADDR,            \t\n");
            sb.append("    B.RECV_NO,   B.INSTITU_CD,  TO_CHAR(B.REG_DT,'DD-MM-YYYY') REG_DT, C.CD_NM2 CD_NM2, B.PERMIT_BRANCH PERMIT_BRANCH, B.GROUP_NO GROUP_NO , B.DEPARTMENT_NO DEP_NO ,B.INSTITU_PHONE_NO PHONE_NO               \n");
            sb.append("   FROM\tUSEMN.UM_PUB_INSTITU_MAST\tB, SYN_PUB_CODE C\t\t\t\t\t\n");
            sb.append("    WHERE\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' AND B.HIDDEN_YN = 'N' AND B.DELETE_YN = 'N'                      \n");
            if (!"".equals(instituCD)) {
                sb.append(" AND B.INSTITU_CD = '" + instituCD + "' ");
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND B.ZIP_CD = '" + province + "' ");
            }
            if (!"".equals(groupNo) && !"0".equals(groupNo)) {
                sb.append(" AND\t\tB.GROUP_NO = " + groupNo + "  \t\t\t\t\t\n");
            }
            if (!"".equals(depNo) && !"0".equals(depNo)) {
                sb.append(" AND\t\tB.DEPARTMENT_NO = " + depNo + "  \t\t\t\t\t\n");
            }
            if (upcheNm.length() >= 1) {
                sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
            }
            if (Start != null && Start.length() != 0) {
                sb.append(" and B.REG_DT >= to_date('" + Start + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ");
            }
            if (End != null && End.length() != 0) {
                sb.append(" and B.REG_DT <= to_date('" + End + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
            }
            sb.append("\t\t\tORDER BY B.REG_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")\t\n");
            Log.debug("SQL1:" + sb.toString());
            sql = sb.toString();
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettItem = new UM_ADV_GovuA030b();
                ettItem.setgoNameFull(rs.getString(1));
                ettItem.setsaupNo(rs.getString(2));
                ettItem.settaskmaster(rs.getString(3));
                ettItem.setADDR(rs.getString(4));
                ettItem.setdependCode(rs.getString(5));
                ettItem.setg2bCode(rs.getString(6));
                ettItem.setrecept(rs.getString(7));
                ettItem.setZIPCODE(rs.getString(8));
                ettItem.setGroupNo(rs.getInt(11));
                ettItem.setDepartmentNo(rs.getInt(12));
                ettItem.setaddress2(rs.getString(13));
                vec.addElement(ettItem);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public UM_ADV_GovuA030b[] approvalList_(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String instituCD, final String province, final String groupNo, final String depNo, final String test_op_yn) {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b[] ett = (UM_ADV_GovuA030b[])null;
        UM_ADV_GovuA030b ettItem = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        final Vector params = new Vector();
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t\n");
            sb.append(" \t\tRECV_NO,   INSTITU_CD,  REG_DT , CD_NM2, NUM, PERMIT_BRANCH, GROUP_NO, DEP_NO , PHONE_NO , TEST_OPTION_YN      \n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\n");
            sb.append(" SELECT\tB.INSTITU_FULL_NM,\tB.BIZ_REG_NO,\tB.CHRGR_NM,\tB.ADDR|| ' ' ||B.DETAIL_ADDR ADDR,            \t\n");
            sb.append("    B.RECV_NO,   B.INSTITU_CD,  TO_CHAR(B.REG_DT,'DD-MM-YYYY') REG_DT, C.CD_NM2 CD_NM2, B.PERMIT_BRANCH PERMIT_BRANCH, B.GROUP_NO GROUP_NO , B.DEPARTMENT_NO DEP_NO ,B.INSTITU_PHONE_NO PHONE_NO , B.TEST_OPTION_YN              \n");
            sb.append("   FROM\tUSEMN.UM_PUB_INSTITU_MAST\tB, SYN_PUB_CODE C\t\t\t\t\t\n");
            sb.append(" WHERE\t\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' \n");
            if ("Y".equals(test_op_yn)) {
                sb.append(" AND B.TEST_OPTION_YN='Y' \n");
            }
            else {
                sb.append(" AND ( B.TEST_OPTION_YN IS NULL OR B.TEST_OPTION_YN ='N' ) \n");
            }
            if (!"".equals(instituCD)) {
                sb.append(" AND     B.INSTITU_CD = ?                    \n");
                params.add(instituCD);
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND     B.ZIP_CD = ?                    \n");
                params.add(province);
            }
            if (!"".equals(groupNo) && !"0".equals(groupNo)) {
                sb.append(" AND     B.GROUP_NO = ?              \n");
                params.add(groupNo);
            }
            if (!"".equals(depNo) && !"0".equals(depNo)) {
                sb.append(" AND     B.DEPARTMENT_NO = ?                     \n");
                params.add(depNo);
            }
            if (upcheNm.length() >= 1) {
                sb.append(" AND     lower(trim(B.INSTITU_FULL_NM)) like ?   \n");
                params.add("%" + upcheNm.toLowerCase().trim() + "%");
            }
            if (Start != null && Start.length() != 0) {
                sb.append(" and B.REG_DT >= to_date( ? , 'dd/MM/yyyy HH24:MI:SS') ");
                params.add(String.valueOf(Start) + " 00:00:00");
            }
            if (End != null && End.length() != 0) {
                sb.append(" and B.REG_DT <= to_date( ? , 'dd/MM/yyyy HH24:MI:SS') ");
                params.add(String.valueOf(End) + " 23:59:59");
            }
            sb.append("\t\t\tORDER BY B.REG_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN ? AND ?\t\n");
            params.add(new StringBuffer(String.valueOf((pagenum - 1) * PAGEMAX + 1)).toString());
            params.add(new StringBuffer(String.valueOf(pagenum * PAGEMAX)).toString());
            sql = sb.toString();
            Log.debug("SQL 2: " + sql);
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); ++i) {
                psmt.setString(i + 1, params.get(i).toString());
            }
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettItem = new UM_ADV_GovuA030b();
                ettItem.setgoNameFull(rs.getString(1));
                ettItem.setsaupNo(rs.getString(2));
                ettItem.settaskmaster(rs.getString(3));
                ettItem.setADDR(rs.getString(4));
                ettItem.setdependCode(rs.getString(5));
                ettItem.setg2bCode(rs.getString(6));
                ettItem.setrecept(rs.getString(7));
                ettItem.setZIPCODE(rs.getString(8));
                ettItem.setGroupNo(rs.getInt(11));
                ettItem.setDepartmentNo(rs.getInt(12));
                ettItem.setaddress2(rs.getString(13));
                ettItem.setTestOptionYn(rs.getString(14));
                vec.addElement(ettItem);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public UM_ADV_GovuA030b[] approvalList_001(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String instituCD, final String province, final String groupNo, final String depNo) {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b[] ett = (UM_ADV_GovuA030b[])null;
        UM_ADV_GovuA030b ettItem = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t\n");
            sb.append(" \t\tRECV_NO,   INSTITU_CD,  REG_DT , CD_NM2, NUM, PERMIT_BRANCH, GROUP_NO, DEP_NO , PHONE_NO, MOBILE, CHRGR_EMAIL       \n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\n");
            sb.append(" SELECT\tB.INSTITU_FULL_NM,\tB.BIZ_REG_NO,\tB.CHRGR_NM,\tB.ADDR|| ' ' ||B.DETAIL_ADDR ADDR,            \t\n");
            sb.append("    B.RECV_NO,   B.INSTITU_CD,  TO_CHAR(B.REG_DT,'DD-MM-YYYY') REG_DT, C.CD_NM2 CD_NM2, B.PERMIT_BRANCH PERMIT_BRANCH, B.GROUP_NO GROUP_NO , B.DEPARTMENT_NO DEP_NO ,B.INSTITU_PHONE_NO PHONE_NO ,  B.MOBILE, B.CHRGR_EMAIL             \n");
            sb.append("   FROM\tUSEMN.UM_PUB_INSTITU_MAST\tB, SYN_PUB_CODE C\t\t\t\t\t\n");
            sb.append("    WHERE\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' AND B.HIDDEN_YN = 'N' AND B.DELETE_YN = 'N'                      \n");
            if (!"".equals(instituCD)) {
                sb.append(" AND B.INSTITU_CD = '" + instituCD + "' ");
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND B.ZIP_CD = '" + province + "' ");
            }
            if (!"".equals(groupNo) && !"0".equals(groupNo)) {
                sb.append(" AND\t\tB.GROUP_NO = " + groupNo + "  \t\t\t\t\t\n");
            }
            if (!"".equals(depNo) && !"0".equals(depNo)) {
                sb.append(" AND\t\tB.DEPARTMENT_NO = " + depNo + "  \t\t\t\t\t\n");
            }
            if (upcheNm.length() >= 1) {
                sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
            }
            if (Start != null && Start.length() != 0) {
                sb.append(" and B.REG_DT >= to_date('" + Start + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ");
            }
            if (End != null && End.length() != 0) {
                sb.append(" and B.REG_DT <= to_date('" + End + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
            }
            sb.append("\t\t\tORDER BY B.REG_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")\t\n");
            Log.debug("SQL1:" + sb.toString());
            sql = sb.toString();
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettItem = new UM_ADV_GovuA030b();
                ettItem.setgoNameFull(rs.getString(1));
                ettItem.setsaupNo(rs.getString(2));
                ettItem.settaskmaster(rs.getString(3));
                ettItem.setADDR(rs.getString(4));
                ettItem.setdependCode(rs.getString(5));
                ettItem.setg2bCode(rs.getString(6));
                ettItem.setrecept(rs.getString(7));
                ettItem.setZIPCODE(rs.getString(8));
                ettItem.setGroupNo(rs.getInt(11));
                ettItem.setDepartmentNo(rs.getInt(12));
                ettItem.setaddress2(rs.getString(13));
                ettItem.setmasterTel(rs.getString(14));
                ettItem.setmasterMail(rs.getString(15));
                vec.addElement(ettItem);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList_001(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList_001(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public UM_ADV_GovuA030b[] approvalList(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String instituCD, final String province, final String draft, final String groupNo, final String depNo) {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b[] ett = (UM_ADV_GovuA030b[])null;
        UM_ADV_GovuA030b ettItem = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t\n");
            sb.append(" \t\tRECV_NO,   INSTITU_CD,  REG_DT , CD_NM2, NUM, PERMIT_BRANCH\t, GROUP_NO , DEP_NO\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\n");
            sb.append(" SELECT\tB.INSTITU_FULL_NM,\tB.BIZ_REG_NO,\tB.CHRGR_NM,\tB.ADDR|| ' ' ||B.DETAIL_ADDR ADDR,            \t\n");
            sb.append("    B.RECV_NO,   B.INSTITU_CD,  TO_CHAR(B.REG_DT,'DD-MM-YYYY') REG_DT, C.CD_NM2 CD_NM2, b.PERMIT_BRANCH PERMIT_BRANCH, B.GROUP_NO GROUP_NO , B.DEPARTMENT_NO DEP_NO                 \n");
            sb.append("   FROM\tUSEMN.UM_PUB_INSTITU_MAST\tB, SYN_PUB_CODE C  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' AND B.DELETE_YN = 'N'     \n");
            if (!"".equals(instituCD)) {
                sb.append(" AND B.INSTITU_CD = '" + instituCD + "' ");
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND B.ZIP_CD = '" + province + "' ");
            }
            if (!"".equals(groupNo) && !"0".equals(groupNo)) {
                sb.append(" AND\t\tB.GROUP_NO = " + groupNo + "  \t\t\t\t\t\n");
            }
            if (!"".equals(depNo) && !"0".equals(depNo)) {
                sb.append(" AND\t\tB.DEPARTMENT_NO = " + depNo + "  \t\t\t\t\t\n");
            }
            if (!"".equals(draft) && draft != null) {
                sb.append(" AND B.HIDDEN_YN = '" + draft + "' ");
            }
            if (flag.equals("S")) {
                if (upcheNm.length() >= 1) {
                    sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
                }
                if (Start != null && Start.length() != 0) {
                    sb.append(" and B.REG_DT >= to_date('" + Start + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ");
                }
                if (End != null && End.length() != 0) {
                    sb.append(" and B.REG_DT <= to_date('" + End + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
                }
            }
            sb.append("\t\t\tORDER BY B.REG_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")\t\n");
            sql = sb.toString();
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            final int i = 0;
            while (rs.next()) {
                ettItem = new UM_ADV_GovuA030b();
                ettItem.setgoNameFull(rs.getString(1));
                ettItem.setsaupNo(rs.getString(2));
                ettItem.settaskmaster(rs.getString(3));
                ettItem.setADDR(rs.getString(4));
                ettItem.setdependCode(rs.getString(5));
                ettItem.setg2bCode(rs.getString(6));
                ettItem.setrecept(rs.getString(7));
                ettItem.setZIPCODE(rs.getString(8));
                ettItem.setGroupNo(rs.getInt(11));
                ettItem.setDepartmentNo(rs.getInt(12));
                vec.addElement(ettItem);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public UM_ADV_GovuA030b[] approvalList(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String total, final String ApprovalCode, final String instituCD, final String province, final String groupType, final String depType) {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b[] ett = (UM_ADV_GovuA030b[])null;
        UM_ADV_GovuA030b ettItem = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t\n");
            sb.append(" \t\tRECV_NO,   INSTITU_CD,  REG_DT , CD_NM2, NUM, PERMIT_BRANCH\t, GROUP_NO , DEP_NO\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\n");
            sb.append(" SELECT\tB.INSTITU_FULL_NM,\tB.BIZ_REG_NO,\tB.CHRGR_NM,\tB.ADDR|| ' ' ||B.DETAIL_ADDR ADDR,            \t\n");
            sb.append("    B.RECV_NO,   B.INSTITU_CD,  TO_CHAR(B.REG_DT,'DD-MM-YYYY') REG_DT, C.CD_NM2 CD_NM2, b.PERMIT_BRANCH PERMIT_BRANCH, B.GROUP_NO GROUP_NO , B.DEPARTMENT_NO DEP_NO                 \n");
            sb.append("   FROM\tUSEMN.UM_PUB_INSTITU_MAST\tB, SYN_PUB_CODE C  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' AND B.DELETE_YN = 'N' AND B.HIDDEN_YN = 'N'     \n");
            if (!"".equals(instituCD)) {
                sb.append(" AND lower(B.INSTITU_CD) like '%" + instituCD.toLowerCase().trim() + "%' ");
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND B.ZIP_CD = '" + province + "' ");
            }
            if ("N".equals(groupType)) {
                sb.append(" AND\t\t(B.GROUP_NO = 0 or B.GROUP_NO is null)  \t\t\t\t\t\n");
            }
            else {
                sb.append(" AND\t\tB.GROUP_NO != 0 AND B.GROUP_NO is not null  \t\t\t\t\t\n");
            }
            if ("N".equals(depType)) {
                sb.append(" AND\t\t(B.DEPARTMENT_NO = 0 or B.DEPARTMENT_NO is null)\t\t\t\n");
            }
            else {
                sb.append(" AND\t\tB.DEPARTMENT_NO != 0 AND B.DEPARTMENT_NO is not null\t\t\t\n");
            }
            if (upcheNm.length() >= 1) {
                sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
            }
            if (Start != null && Start.length() != 0) {
                sb.append(" and B.REG_DT >= to_date('" + Start + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ");
            }
            if (End != null && End.length() != 0) {
                sb.append(" and B.REG_DT <= to_date('" + End + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
            }
            sb.append("\t\t\tORDER BY B.REG_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")\t\n");
            sql = sb.toString();
            Log.debug("Cap nhat bo ban nganh: " + sql);
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            final int i = 0;
            while (rs.next()) {
                ettItem = new UM_ADV_GovuA030b();
                ettItem.setgoNameFull(rs.getString(1));
                ettItem.setsaupNo(rs.getString(2));
                ettItem.settaskmaster(rs.getString(3));
                ettItem.setADDR(rs.getString(4));
                ettItem.setdependCode(rs.getString(5));
                ettItem.setg2bCode(rs.getString(6));
                ettItem.setrecept(rs.getString(7));
                ettItem.setZIPCODE(rs.getString(8));
                ettItem.setGroupNo(rs.getInt(11));
                ettItem.setDepartmentNo(rs.getInt(12));
                vec.addElement(ettItem);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public int approvalList_count(final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String instituCD, final String province, final String groupNo, final String depNo, final String test_op_yn, final String hide_yn) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  USEMN.UM_PUB_INSTITU_MAST B, SYN_PUB_CODE C\t                       \t\n");
            sb.append(" WHERE\t\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7'\tAND B.HIDDEN_YN='N' AND B.DELETE_YN = 'N'  \t\t\t\n");
            if (!"".equals(instituCD)) {
                sb.append(" AND\t\tB.INSTITU_CD = '" + instituCD + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND\t\tB.ZIP_CD = '" + province + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(groupNo) && !"0".equals(groupNo)) {
                sb.append(" AND\t\tB.GROUP_NO = '" + groupNo + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(depNo) && !"0".equals(depNo)) {
                sb.append(" AND\t\tB.DEPARTMENT_NO = '" + depNo + "'  \t\t\t\t\t\n");
            }
            if (flag.equals("S")) {
                if (upcheNm.length() >= 1) {
                    sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
                }
                if (Start != null && Start.length() != 0) {
                    sb.append(" and B.REG_DT >= to_date('" + Start + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ");
                }
                if (End != null && End.length() != 0) {
                    sb.append(" and B.REG_DT <= to_date('" + End + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
                }
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
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public int approvalList_count_(final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String instituCD, final String province, final String groupNo, final String depNo, final String test_op_yn) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final Vector params = new Vector();
        int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  USEMN.UM_PUB_INSTITU_MAST B, SYN_PUB_CODE C\t                       \t\n");
            sb.append(" WHERE\t\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' \n");
            if ("Y".equals(test_op_yn)) {
                sb.append(" AND B.TEST_OPTION_YN='Y' \n");
            }
            else {
                sb.append(" AND ( B.TEST_OPTION_YN IS NULL OR B.TEST_OPTION_YN ='N' ) \n");
            }
            if (!"".equals(instituCD)) {
                sb.append(" AND\t\tB.INSTITU_CD = ? \t\t\t\t\t\n");
                params.add(instituCD);
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND\t\tB.ZIP_CD = ?  \t\t\t\t\t\n");
                params.add(province);
            }
            if (!"".equals(groupNo) && !"0".equals(groupNo)) {
                sb.append(" AND\t\tB.GROUP_NO = ?\t\t\t\t\n");
                params.add(groupNo);
            }
            if (!"".equals(depNo) && !"0".equals(depNo)) {
                sb.append(" AND\t\tB.DEPARTMENT_NO = ? \t\t\t\t\t\n");
                params.add(depNo);
            }
            if (flag.equals("S")) {
                if (upcheNm.length() >= 1) {
                    sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like ?\t\n");
                    params.add("%" + upcheNm.toLowerCase().trim() + "%");
                }
                if (Start != null && Start.length() != 0) {
                    sb.append(" and B.REG_DT >= to_date( ? , 'dd/MM/yyyy HH24:MI:SS') ");
                    params.add(String.valueOf(Start) + " 00:00:00");
                }
                if (End != null && End.length() != 0) {
                    sb.append(" and B.REG_DT <= to_date( ? , 'dd/MM/yyyy HH24:MI:SS') ");
                    params.add(String.valueOf(End) + " 23:59:59");
                }
            }
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); ++i) {
                psmt.setString(i + 1, params.get(i).toString());
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public int approvalList_count(final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String instituCD, final String province, final String draft, final String groupNo, final String depNo) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  USEMN.UM_PUB_INSTITU_MAST B, SYN_PUB_CODE C\t\t\t\n");
            sb.append(" WHERE\t\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' \tAND B.DELETE_YN = 'N' \t\t\n");
            if (!"".equals(instituCD)) {
                sb.append(" AND\t\tB.INSTITU_CD = '" + instituCD + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND\t\tB.ZIP_CD = '" + province + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(groupNo) && !"0".equals(groupNo)) {
                sb.append(" AND\t\tB.GROUP_NO = '" + groupNo + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(depNo) && !"0".equals(depNo)) {
                sb.append(" AND\t\tB.DEPARTMENT_NO = '" + depNo + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(draft) && draft != null) {
                sb.append(" AND\t\tB.HIDDEN_YN = '" + draft + "'  \t\t\t\t\t\n");
            }
            if (upcheNm.length() >= 1) {
                sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
            }
            if (Start != null && Start.length() != 0) {
                sb.append(" and B.REG_DT >= to_date('" + Start + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ");
            }
            if (End != null && End.length() != 0) {
                sb.append(" and B.REG_DT <= to_date('" + End + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
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
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public int approvalList_count_g2bCode(final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String instituCD, final String province, final String draft, final String groupNo, final String depNo, final String g2bCode) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  USEMN.UM_PUB_INSTITU_MAST B, SYN_PUB_CODE C\t\t\t\n");
            sb.append(" WHERE\t\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' \tAND B.DELETE_YN = 'N' \t\t\n");
            if (!"".equals(instituCD)) {
                sb.append(" AND\t\tB.INSTITU_CD = '" + instituCD + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND\t\tB.ZIP_CD = '" + province + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(groupNo) && !"0".equals(groupNo)) {
                sb.append(" AND\t\tB.GROUP_NO = '" + groupNo + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(depNo) && !"0".equals(depNo)) {
                sb.append(" AND\t\tB.DEPARTMENT_NO = '" + depNo + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(g2bCode)) {
                sb.append(" AND\t\tB.Institu_cd = '" + g2bCode + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(draft) && draft != null) {
                sb.append(" AND\t\tB.HIDDEN_YN = '" + draft + "'  \t\t\t\t\t\n");
            }
            if (upcheNm.length() >= 1) {
                sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
            }
            if (Start != null && Start.length() != 0) {
                sb.append(" and B.REG_DT >= to_date('" + Start + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ");
            }
            if (End != null && End.length() != 0) {
                sb.append(" and B.REG_DT <= to_date('" + End + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
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
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public int approvalList_count_Suppiler_Institu(final String upcheNm, final String total, final String instituCD, final String cert_cls) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            if ("B".equals(cert_cls)) {
                sql = " SELECT\tCOUNT(*) FROM  USEMN.UM_PUB_INSTITU_MAST B, SYN_PUB_CODE C\t WHERE\t\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' ";
                if (!"".equals(instituCD)) {
                    sql = String.valueOf(sql) + " AND\t\tB.INSTITU_CD = '" + instituCD + "' ";
                }
                if (upcheNm != null && upcheNm.length() != 0) {
                    sql = String.valueOf(sql) + " AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'";
                }
            }
            else if ("S".equals(cert_cls)) {
                sql = "select count(*)  from USEMN.UM_SUPPLIER_ENTER_MAST t1, USEMN.UM_REC_REPR t2 where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'";
                if (instituCD != null && instituCD.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like '%" + instituCD.toLowerCase().trim() + "%'";
                }
                if (upcheNm != null && upcheNm.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'";
                }
            }
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + "]):");
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + "]):");
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
    
    public UM_ADV_GovuA030b[] approvalList_Institu(final int pagenum, final int PAGEMAX, final String upcheNm, final String total, final String flag, final String instituCD, final String cert_cls) {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b[] ett = (UM_ADV_GovuA030b[])null;
        UM_ADV_GovuA030b ettItem = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t\n");
            sb.append(" \t\tRECV_NO,   INSTITU_CD,  REG_DT , CD_NM2, NUM, PERMIT_BRANCH\t, GROUP_NO , DEP_NO, INSTITU_EN_NM\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\n");
            sb.append(" SELECT\tB.INSTITU_FULL_NM,\tB.BIZ_REG_NO,\tB.CHRGR_NM,\tB.ADDR|| ' ' ||B.DETAIL_ADDR ADDR,            \t\n");
            sb.append("    B.RECV_NO,   B.INSTITU_CD,  TO_CHAR(B.REG_DT,'DD-MM-YYYY') REG_DT, C.CD_NM2 CD_NM2, b.PERMIT_BRANCH PERMIT_BRANCH, B.GROUP_NO GROUP_NO , B.DEPARTMENT_NO DEP_NO  , B.INSTITU_EN_NM               \n");
            sb.append("   FROM\tUSEMN.UM_PUB_INSTITU_MAST\tB, SYN_PUB_CODE C  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' AND B.DELETE_YN = 'N'     \n");
            if (!"".equals(instituCD)) {
                sb.append(" AND B.INSTITU_CD = '" + instituCD + "' ");
            }
            if (flag.equals("S") && upcheNm.length() >= 1) {
                sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
            }
            sb.append("\t\t\tORDER BY B.REG_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")\t\n");
            sql = sb.toString();
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            final int i = 0;
            while (rs.next()) {
                ettItem = new UM_ADV_GovuA030b();
                ettItem.setgoNameFull(rs.getString(1));
                ettItem.setsaupNo(rs.getString(2));
                ettItem.settaskmaster(rs.getString(3));
                ettItem.setADDR(rs.getString(4));
                ettItem.setdependCode(rs.getString(5));
                ettItem.setg2bCode(rs.getString(6));
                ettItem.setrecept(rs.getString(7));
                ettItem.setZIPCODE(rs.getString(8));
                ettItem.setGroupNo(rs.getInt(11));
                ettItem.setDepartmentNo(rs.getInt(12));
                ettItem.setgoNameEn(rs.getString(13));
                vec.addElement(ettItem);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + "]):");
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + "]):");
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
    
    public UM_ADV_GovuA030b get_Institu(final String upcheNm, final String instituCD) {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b ettItem = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t\n");
            sb.append(" \t\tRECV_NO,   INSTITU_CD,  REG_DT , CD_NM2, NUM, PERMIT_BRANCH\t, GROUP_NO , DEP_NO, INSTITU_EN_NM, TEMP_NAME \n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\n");
            sb.append(" SELECT\tB.INSTITU_FULL_NM,\tB.BIZ_REG_NO,\tB.CHRGR_NM,\tB.ADDR|| ' ' ||B.DETAIL_ADDR ADDR,            \t\n");
            sb.append("    B.RECV_NO,   B.INSTITU_CD,  TO_CHAR(B.REG_DT,'DD-MM-YYYY') REG_DT, C.CD_NM2 CD_NM2, b.PERMIT_BRANCH PERMIT_BRANCH, B.GROUP_NO GROUP_NO , B.DEPARTMENT_NO DEP_NO  , B.INSTITU_EN_NM   , B.TEMP_NAME            \n");
            sb.append("   FROM\tUSEMN.UM_PUB_INSTITU_MAST\tB, SYN_PUB_CODE C  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' AND B.DELETE_YN = 'N'     \n");
            sb.append(" AND B.INSTITU_CD = '" + instituCD + "' ");
            sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
            sb.append("\t\t\tORDER BY B.REG_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\n");
            sql = sb.toString();
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            final int i = 0;
            if (rs.next()) {
                ettItem = new UM_ADV_GovuA030b();
                ettItem.setgoNameFull(rs.getString(1));
                ettItem.setsaupNo(rs.getString(2));
                ettItem.settaskmaster(rs.getString(3));
                ettItem.setADDR(rs.getString(4));
                ettItem.setdependCode(rs.getString(5));
                ettItem.setg2bCode(rs.getString(6));
                ettItem.setrecept(rs.getString(7));
                ettItem.setZIPCODE(rs.getString(8));
                ettItem.setGroupNo(rs.getInt(11));
                ettItem.setDepartmentNo(rs.getInt(12));
                ettItem.setgoNameEn(rs.getString(13));
                ettItem.setTempName(rs.getString(14));
                return ettItem;
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + "]):");
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + "]):");
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
        return null;
    }
    
    public UM_RAE_ConuB010b[] approvalList_Sub(final int pagenum, final int PAGEMAX, final String upcheNm, final String total, final String flag, final String instituCD, final String cert_cls) {
        Trx trx = null;
        Connection con = null;
        UM_RAE_ConuB010b[] ettlist = (UM_RAE_ConuB010b[])null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        try {
            sql = "select * from(  select  BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, RECV_NO , ROWNUM N, CD_NM2, ADDR, HIDDEN_YN, TEST_OPTION_YN ,BIZ_EN_NM from (  select t1.BIZ_REG_NO, t1.BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.RECV_NO, t4.CD_NM2 CD_NM2, t1.ADDR, t1.HIDDEN_YN, t1.TEST_OPTION_YN, t1.BIZ_EN_NM  from USEMN.UM_SUPPLIER_ENTER_MAST t1, USEMN.UM_REC_REPR t2, SYN_PUB_CODE t4 where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  and t1.AREA_CD = t4.CD and t4.CD_CLS = 'GU7' ";
            if (instituCD != null && instituCD.length() != 0) {
                sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like '%" + instituCD.toLowerCase().trim() + "%'";
            }
            if (upcheNm != null && upcheNm.length() != 0) {
                sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'";
            }
            final int start = (pagenum - 1) * PAGEMAX + 1;
            final int end = pagenum * PAGEMAX;
            sql = String.valueOf(sql) + " order by t1.REG_DT desc";
            sql = String.valueOf(sql) + ")) t3 where t3.N between " + start + " and " + end;
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            final int i = 0;
            while (rs.next()) {
                final UM_RAE_ConuB010b ett = new UM_RAE_ConuB010b();
                if (rs.getString(1) != null) {
                    ett.setSaupNo(rs.getString(1));
                }
                if (rs.getString(2) != null) {
                    ett.setSangho(rs.getString(2));
                }
                if (rs.getString(3) != null) {
                    ett.setSinchungDate(rs.getString(3));
                }
                if (rs.getString(4) != null) {
                    ett.setCeoName(rs.getString(4));
                }
                if (rs.getString(5) != null) {
                    ett.setApprovalOrgCode(rs.getString(5));
                }
                if (rs.getString(7) != null) {
                    ett.setZipCode(rs.getString(7));
                }
                if (rs.getString(8) != null) {
                    ett.setAddr(rs.getString(8));
                }
                if (rs.getString(9) != null) {
                    ett.setDeleteYN(rs.getString(9));
                }
                if (rs.getString(10) != null) {
                    ett.setTestOptionYn(rs.getString(10));
                }
                if (rs.getString(11) != null) {
                    ett.setSanghoEng(rs.getString(11));
                }
                if (ett != null) {
                    vec.addElement(ett);
                }
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + "]):");
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + "]):");
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
        if (vec.size() > 0) {
            ettlist = new UM_RAE_ConuB010b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public UM_RAE_ConuB010b get_Supplier(final String upcheNm, final String instituCD) {
        Trx trx = null;
        Connection con = null;
        UM_RAE_ConuB010b ett = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        try {
            sql = "select * from(  select  BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, RECV_NO , ROWNUM N, CD_NM2, ADDR, HIDDEN_YN, TEST_OPTION_YN ,BIZ_EN_NM, TEMP_NAME from (  select t1.BIZ_REG_NO, t1.BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.RECV_NO, t4.CD_NM2 CD_NM2, t1.ADDR, t1.HIDDEN_YN, t1.TEST_OPTION_YN, t1.BIZ_EN_NM ,t1.TEMP_NAME from USEMN.UM_SUPPLIER_ENTER_MAST t1, USEMN.UM_REC_REPR t2, SYN_PUB_CODE t4 where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  and t1.AREA_CD = t4.CD and t4.CD_CLS = 'GU7' ";
            if (instituCD != null && instituCD.length() != 0) {
                sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like '%" + instituCD.toLowerCase().trim() + "%'";
            }
            if (upcheNm != null && upcheNm.length() != 0) {
                sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'";
            }
            sql = String.valueOf(sql) + " order by t1.REG_DT desc";
            sql = String.valueOf(sql) + "))";
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ett = new UM_RAE_ConuB010b();
                if (rs.getString(1) != null) {
                    ett.setSaupNo(rs.getString(1));
                }
                if (rs.getString(2) != null) {
                    ett.setSangho(rs.getString(2));
                }
                if (rs.getString(3) != null) {
                    ett.setSinchungDate(rs.getString(3));
                }
                if (rs.getString(4) != null) {
                    ett.setCeoName(rs.getString(4));
                }
                if (rs.getString(5) != null) {
                    ett.setApprovalOrgCode(rs.getString(5));
                }
                if (rs.getString(7) != null) {
                    ett.setZipCode(rs.getString(7));
                }
                if (rs.getString(8) != null) {
                    ett.setAddr(rs.getString(8));
                }
                if (rs.getString(9) != null) {
                    ett.setDeleteYN(rs.getString(9));
                }
                if (rs.getString(10) != null) {
                    ett.setTestOptionYn(rs.getString(10));
                }
                if (rs.getString(11) != null) {
                    ett.setSanghoEng(rs.getString(11));
                }
                if (rs.getString(12) != null) {
                    ett.setTempName(rs.getString(12));
                }
                if (ett != null) {
                    return ett;
                }
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + "]):");
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + "]):");
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
        return null;
    }
    
    public UM_ADV_GovuA030b[] approvalList_g2bCode(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String instituCD, final String province, final String draft, final String groupNo, final String depNo, final String g2bCode) {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b[] ett = (UM_ADV_GovuA030b[])null;
        UM_ADV_GovuA030b ettItem = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t\n");
            sb.append(" \t\tRECV_NO,   INSTITU_CD,  REG_DT , CD_NM2, NUM, PERMIT_BRANCH\t, GROUP_NO , DEP_NO\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\n");
            sb.append(" SELECT\tB.INSTITU_FULL_NM,\tB.BIZ_REG_NO,\tB.CHRGR_NM,\tB.ADDR|| ' ' ||B.DETAIL_ADDR ADDR,            \t\n");
            sb.append("    B.RECV_NO,   B.INSTITU_CD,  TO_CHAR(B.REG_DT,'DD-MM-YYYY') REG_DT, C.CD_NM2 CD_NM2, b.PERMIT_BRANCH PERMIT_BRANCH, B.GROUP_NO GROUP_NO , B.DEPARTMENT_NO DEP_NO                 \n");
            sb.append("   FROM\tUSEMN.UM_PUB_INSTITU_MAST\tB, SYN_PUB_CODE C  \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' AND B.DELETE_YN = 'N'     \n");
            if (!"".equals(instituCD)) {
                sb.append(" AND B.INSTITU_CD = '" + instituCD + "' ");
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND B.ZIP_CD = '" + province + "' ");
            }
            if (!"".equals(groupNo) && !"0".equals(groupNo)) {
                sb.append(" AND\t\tB.GROUP_NO = " + groupNo + "  \t\t\t\t\t\n");
            }
            if (!"".equals(depNo) && !"0".equals(depNo)) {
                sb.append(" AND\t\tB.DEPARTMENT_NO = " + depNo + "  \t\t\t\t\t\n");
            }
            if (!"".equals(g2bCode)) {
                sb.append(" AND\t\tB.INSTITU_CD = '" + g2bCode + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(draft) && draft != null) {
                sb.append(" AND B.HIDDEN_YN = '" + draft + "' ");
            }
            if (flag.equals("S")) {
                if (upcheNm.length() >= 1) {
                    sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
                }
                if (Start != null && Start.length() != 0) {
                    sb.append(" and B.REG_DT >= to_date('" + Start + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ");
                }
                if (End != null && End.length() != 0) {
                    sb.append(" and B.REG_DT <= to_date('" + End + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
                }
            }
            sb.append("\t\t\tORDER BY B.REG_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")\t\n");
            sql = sb.toString();
            Log.debug("...........sql: " + sql);
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            final int i = 0;
            while (rs.next()) {
                ettItem = new UM_ADV_GovuA030b();
                ettItem.setgoNameFull(rs.getString(1));
                ettItem.setsaupNo(rs.getString(2));
                ettItem.settaskmaster(rs.getString(3));
                ettItem.setADDR(rs.getString(4));
                ettItem.setdependCode(rs.getString(5));
                ettItem.setg2bCode(rs.getString(6));
                ettItem.setrecept(rs.getString(7));
                ettItem.setZIPCODE(rs.getString(8));
                ettItem.setGroupNo(rs.getInt(11));
                ettItem.setDepartmentNo(rs.getInt(12));
                vec.addElement(ettItem);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public int approvalList_count(final String upcheNm, final String Start, final String End, final String total, final String ApprovalCode, final String instituCD, final String province, final String groupType, final String depType) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  USEMN.UM_PUB_INSTITU_MAST B, SYN_PUB_CODE C\t\t\t\n");
            sb.append(" WHERE\t\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7' \tAND B.DELETE_YN = 'N' AND B.HIDDEN_YN = 'N' \t\t\n");
            if (!"".equals(instituCD)) {
                sb.append(" AND\t\tlower(B.INSTITU_CD) like '%" + instituCD.toLowerCase().trim() + "%'  \t\t\t\t\t\n");
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND\t\tB.ZIP_CD = '" + province + "'  \t\t\t\t\t\n");
            }
            if ("N".equals(groupType)) {
                sb.append(" AND\t\t(B.GROUP_NO = 0 or B.GROUP_NO is null)\t\t\t\n");
            }
            else {
                sb.append(" AND\t\tB.GROUP_NO != 0 AND B.GROUP_NO is not null\t\t\t\n");
            }
            if ("N".equals(depType)) {
                sb.append(" AND\t\t(B.DEPARTMENT_NO = 0 or B.DEPARTMENT_NO is null)\t\t\t\t\n");
            }
            else {
                sb.append(" AND\t\tB.DEPARTMENT_NO != 0 AND B.DEPARTMENT_NO is not null\t\t\t\t\n");
            }
            if (upcheNm.length() >= 1) {
                sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
            }
            if (Start != null && Start.length() != 0) {
                sb.append(" and B.REG_DT >= to_date('" + Start + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ");
            }
            if (End != null && End.length() != 0) {
                sb.append(" and B.REG_DT <= to_date('" + End + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
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
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public void updateInfo(final String instituCd, final int groupNo, final int departmentNo, final HttpServletRequest req, final Connection con) throws Exception {
        PreparedStatement pstmt = null;
        final String sql = " update USEMN.UM_PUB_INSTITU_MAST set DEPARTMENT_NO = " + departmentNo + ", GROUP_NO = " + groupNo + " where INSTITU_CD = '" + instituCd + "' ";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
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
    
    public void update_tempName_Um_supplier_enter_mast(final String tempName, final String oldName, final String institu_cd) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sql = "UPDATE USEMN.UM_SUPPLIER_ENTER_MAST SET BIZ_NM = ?, TEMP_NAME = ? WHERE BIZ_REG_NO = ?  ";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, tempName);
            psmt.setString(2, oldName);
            psmt.setString(3, institu_cd);
            psmt.executeUpdate();
        }
        catch (SQLException sqle) {
            Log.debug(sqle.toString());
        }
        catch (Exception exc) {
            Log.debug(exc.toString());
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
    }
    
    public void update_tempName_Um_rec_supplier_enter_mast(final String tempName, final String oldName, final String institu_cd) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sql = "UPDATE USEMN.UM_REC_SUPPLIER_ENTER_MAST SET BIZ_NM = ?, TEMP_NAME = ? WHERE BIZ_REG_NO = ? ";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, tempName);
            psmt.setString(2, oldName);
            psmt.setString(3, institu_cd);
            psmt.executeUpdate();
        }
        catch (SQLException sqle) {
            Log.debug(sqle.toString());
        }
        catch (Exception exc) {
            Log.debug(exc.toString());
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
    }
    
    public void update_tempName_UM_REC_PUB_INSTITU_CERT_INSTITU(final String tempName, final String INSTITU_CD) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sql = "UPDATE USEMN.UM_REC_PUB_INSTITU_CERT SET INSTITU_FULL_NM = ? WHERE INSTITU_CD = ? ";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, tempName);
            psmt.setString(2, INSTITU_CD);
            psmt.executeUpdate();
        }
        catch (SQLException sqle) {
            Log.debug(sqle.toString());
        }
        catch (Exception exc) {
            Log.debug(exc.toString());
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
    }
    
    public void update_tempName_UM_REC_PUB_INSTITU_CERT_SUPPLIER(final String tempName, final String BIZ_REG_NO) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sql = "UPDATE USEMN.UM_REC_PUB_INSTITU_CERT SET INSTITU_FULL_NM = ? WHERE BIZ_REG_NO = ? ";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, tempName);
            psmt.setString(2, BIZ_REG_NO);
            psmt.executeUpdate();
        }
        catch (SQLException sqle) {
            Log.debug(sqle.toString());
        }
        catch (Exception exc) {
            Log.debug(exc.toString());
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
    }
    
    public void update_tempName_UM_EDOC_STATE(final String tempName, final String BIZ_REG_NO) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sql = "UPDATE USEMN.UM_EDOC_STATE SET COM_NM = ? WHERE BIZ_REG_NO = ? ";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, tempName);
            psmt.setString(2, BIZ_REG_NO);
            psmt.executeUpdate();
        }
        catch (SQLException sqle) {
            Log.debug(sqle.toString());
        }
        catch (Exception exc) {
            Log.debug(exc.toString());
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
    }
    
    public void update_tempName_um_pub_institu_mast(final String tempName, final String oldName, final String institu_cd) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sql = "UPDATE USEMN.um_pub_institu_mast SET INSTITU_FULL_NM = ?, TEMP_NAME = ? WHERE INSTITU_CD = ?";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, tempName);
            psmt.setString(2, oldName);
            psmt.setString(3, institu_cd);
            psmt.executeUpdate();
        }
        catch (SQLException sqle) {
            Log.debug(sqle.toString());
        }
        catch (Exception exc) {
            Log.debug(exc.toString());
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
    }
    
    public void update_tempName_um_rec_pub_institu_mast(final String tempName, final String oldName, final String institu_cd) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        final int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sql = "UPDATE USEMN.UM_REC_PUB_INSTITU_MAST SET INSTITU_FULL_NM = ?, TEMP_NAME = ? WHERE INSTITU_CD = ? ";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, tempName);
            psmt.setString(2, oldName);
            psmt.setString(3, institu_cd);
            psmt.executeUpdate();
        }
        catch (SQLException sqle) {
            Log.debug(sqle.toString());
        }
        catch (Exception exc) {
            Log.debug(exc.toString());
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
    }
    
    public int approvalList_count_CTS(final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String instituCD, final String province, final String groupNo, final String depNo, final String test_op_yn) {
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        int count = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  UM_PUB_INSTITU_MAST B, SYN_PUB_CODE C , UM_USER D, UM_CERT_INFO E   \t\n");
            sb.append(" WHERE\t\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7'  \n");
            sb.append(" AND\t\t\tB.HIDDEN_YN = 'N'           \n");
            sb.append(" AND\t\t\tB.INSTITU_CD = D.MAST_CD AND D.USER_CLS = 'g'  \n");
            sb.append(" AND\t\t\tD.USER_ID = E.USER_ID \n");
            if (!"".equals(instituCD)) {
                sb.append(" AND\t\tB.INSTITU_CD = '" + instituCD + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND\t\tB.ZIP_CD = '" + province + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(groupNo) && !"0".equals(groupNo)) {
                sb.append(" AND\t\tB.GROUP_NO = '" + groupNo + "'  \t\t\t\t\t\n");
            }
            if (!"".equals(depNo) && !"0".equals(depNo)) {
                sb.append(" AND\t\tB.DEPARTMENT_NO = '" + depNo + "'  \t\t\t\t\t\n");
            }
            if (flag.equals("S")) {
                if (upcheNm.length() >= 1) {
                    sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
                }
                if (Start != null && Start.length() != 0) {
                    sb.append(" and E.AVAIL_PERIOD_END_DT >= to_date('" + Start + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ");
                }
                if (End != null && End.length() != 0) {
                    sb.append(" and E.AVAIL_PERIOD_END_DT <= to_date('" + End + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
                }
            }
            sql = sb.toString();
            Log.debug("test_op_yn: " + test_op_yn);
            Log.debug(sql);
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList_count(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
    
    public UM_ADV_GovuA030b[] approvalList_CTS(final int pagenum, final int PAGEMAX, final String upcheNm, final String Start, final String End, final String flag, final String total, final String ApprovalCode, final String instituCD, final String province, final String groupNo, final String depNo, final String test_op_yn) {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b[] ett = (UM_ADV_GovuA030b[])null;
        UM_ADV_GovuA030b ettItem = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vec = new Vector();
        String sql = null;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t\n");
            sb.append(" \t\tRECV_NO,   INSTITU_CD,  AVAIL_PERIOD_END_DT , CD_NM2, NUM, PERMIT_BRANCH, GROUP_NO, DEP_NO , CHRGR_MOBILE        \n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\n");
            sb.append(" SELECT\tB.INSTITU_FULL_NM,\tB.BIZ_REG_NO,\tD.CHRGR_NM,\tB.ADDR|| ' ' ||B.DETAIL_ADDR ADDR,            \t\n");
            sb.append("    B.RECV_NO,   B.INSTITU_CD,  TO_CHAR(E.AVAIL_PERIOD_END_DT,'DD-MM-YYYY HH24:MI:SS') AVAIL_PERIOD_END_DT, C.CD_NM2 CD_NM2, B.PERMIT_BRANCH PERMIT_BRANCH, B.GROUP_NO GROUP_NO , B.DEPARTMENT_NO DEP_NO ,D.CHRGR_MOBILE CHRGR_MOBILE               \n");
            sb.append("   FROM\tUM_PUB_INSTITU_MAST\tB, SYN_PUB_CODE C, UM_USER D, UM_CERT_INFO E\t\t\t\t\t\n");
            sb.append(" WHERE\t\tB.ZIP_CD = C.CD AND C.CD_CLS = 'GU7'  \n");
            sb.append(" AND\t\t\tB.HIDDEN_YN = 'N'           \n");
            sb.append(" AND\t\t\tB.INSTITU_CD = D.MAST_CD AND D.USER_CLS = 'g'  \n");
            sb.append(" AND\t\t\tD.USER_ID = E.USER_ID \n");
            if (!"".equals(instituCD)) {
                sb.append(" AND B.INSTITU_CD = '" + instituCD + "' ");
            }
            if (!"".equals(province) && province != null) {
                sb.append(" AND B.ZIP_CD = '" + province + "' ");
            }
            if (!"".equals(groupNo) && !"0".equals(groupNo)) {
                sb.append(" AND\t\tB.GROUP_NO = " + groupNo + "  \t\t\t\t\t\n");
            }
            if (!"".equals(depNo) && !"0".equals(depNo)) {
                sb.append(" AND\t\tB.DEPARTMENT_NO = " + depNo + "  \t\t\t\t\t\n");
            }
            if (upcheNm.length() >= 1) {
                sb.append(" AND\t\tlower(trim(B.INSTITU_FULL_NM)) like '%" + upcheNm.toLowerCase().trim() + "%'\t\n");
            }
            if (Start != null && Start.length() != 0) {
                sb.append(" and E.AVAIL_PERIOD_END_DT >= to_date('" + Start + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') ");
            }
            if (End != null && End.length() != 0) {
                sb.append(" and E.AVAIL_PERIOD_END_DT <= to_date('" + End + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS') ");
            }
            sb.append("\t\t\tORDER BY E.AVAIL_PERIOD_END_DT ASC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ")\t\n");
            Log.debug("SQL1:" + sb.toString());
            sql = sb.toString();
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettItem = new UM_ADV_GovuA030b();
                ettItem.setgoNameFull(rs.getString(1));
                ettItem.setsaupNo(rs.getString(2));
                ettItem.settaskmaster(rs.getString(3));
                ettItem.setADDR(rs.getString(4));
                ettItem.setdependCode(rs.getString(5));
                ettItem.setg2bCode(rs.getString(6));
                ettItem.setrecept(rs.getString(7));
                ettItem.setZIPCODE(rs.getString(8));
                ettItem.setGroupNo(rs.getInt(11));
                ettItem.setDepartmentNo(rs.getInt(12));
                ettItem.setaddress2(rs.getString(13));
                vec.addElement(ettItem);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_GovuA030c1.approvalList(upcheNm[" + upcheNm + "],ApprovalCode[" + ApprovalCode + "]):" + exc.toString());
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
}
