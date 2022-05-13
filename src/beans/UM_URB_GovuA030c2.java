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

public class UM_URB_GovuA030c2
{
    public UM_ADV_GovuA030b[] approvalList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        Trx trx = null;
        Connection connection = null;
        Log.debug("test vao ham nay chua?");
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector();
        final String string = s2 + "00:00:01";
        final String string2 = s3 + "23:59:59";
        int n3 = 0;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t            \t\t\t\t\t\t\t\t\n");
            sb.append(" \t\tREG_YN,\t\t\tRECV_NO,   INSTITU_CD,\tCERT_RECV_NO,  RECV_DT , CD_NM2, NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR||' ' ||DETAIL_ADDR ADDR, REG_YN,            \t\n");
            sb.append("    RECV_NO,   INSTITU_CD,   CERT_RECV_NO,  TO_CHAR(RECV_DT,'YYYY-MM-DD') RECV_DT, a.CD_NM2 CD_NM2                  \n");
            sb.append("   FROM\tUM_REC_PUB_INSTITU_MAST, SYN_PUB_CODE a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE\ta.CD = ZIP_CD and a.CD_CLS = 'GU7'\t\n");
            if (!"".equals(s6) && !"null".equals(s6)) {
                sb.append("    AND PERMIT_BRANCH = '" + s6 + "'\t\t    \t\t\t\t\t\t\t\t\t\t\t\t    \t\n");
            }
            if (s4.equals("S")) {
                if (!s.equals("")) {
                    sb.append(" AND\t\tINSTITU_FULL_NM like '%'||?||'%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                }
                if (!s2.equals("")) {
                    sb.append(" AND\t\tRECV_DT BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS')     \n");
                    sb.append("AND TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS')\t    \n");
                }
            }
            if (s5.equals("1")) {
                sb.append(" AND\t\tREG_YN ='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("2")) {
                sb.append(" AND\t\tREG_YN ='N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("3")) {
                sb.append(" AND\t\tREG_YN ='E'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("4")) {
                sb.append(" AND\t\tREG_YN ='D'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("5")) {
                sb.append(" AND\t\tREG_YN ='C'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (!"".equals(s7)) {
                sb.append(" AND\t\tZIP_CD ='" + s7 + "'\t\t\t\t\t\n");
            }
            if (!"".equals(s8)) {
                sb.append(" AND\t\tINSTITU_CD ='" + s8 + "'\t\t\t\t\t\n");
            }
            sb.append("\t\t\tORDER BY RECV_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")\t\n");
            final String string3 = sb.toString();
            Log.debug("Cau lenh SQL: " + string3);
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string3);
            if (s4.equals("S") && !s.equals("")) {
                ++n3;
                prepareStatement.setString(n3, s);
            }
            if (s2.length() >= 1) {
                ++n3;
                prepareStatement.setString(n3, string);
                ++n3;
                prepareStatement.setString(n3, string2);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_ADV_GovuA030b um_ADV_GovuA030b = new UM_ADV_GovuA030b();
                um_ADV_GovuA030b.setgoNameFull(executeQuery.getString(1));
                um_ADV_GovuA030b.setsaupNo(executeQuery.getString(2));
                um_ADV_GovuA030b.settaskmaster(executeQuery.getString(3));
                um_ADV_GovuA030b.setADDR(executeQuery.getString(4));
                um_ADV_GovuA030b.setenYn(executeQuery.getString(5));
                um_ADV_GovuA030b.setdependCode(executeQuery.getString(6));
                um_ADV_GovuA030b.setg2bCode(executeQuery.getString(7));
                um_ADV_GovuA030b.setLicenseCode(executeQuery.getString(8));
                um_ADV_GovuA030b.setrecept(executeQuery.getString(9));
                um_ADV_GovuA030b.setZIPCODE(executeQuery.getString(10));
                vector.addElement(um_ADV_GovuA030b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + s + "],ApprovalCode[" + s6 + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + s + "],ApprovalCode[" + s6 + "]):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_ADV_GovuA030b[] array = new UM_ADV_GovuA030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_ADV_GovuA030b[] approvalList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Trx trx = null;
        Connection connection = null;
        Log.debug("test vao ham nay chua?");
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector();
        final String string = s2 + " 00:00:01";
        final String string2 = s3 + " 23:59:59";
        int n3 = 0;
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
            if (!"".equals(s6) && !"null".equals(s6)) {
                sb.append("    AND PERMIT_BRANCH = '" + s6 + "'\t\t    \t\t\t\t\t\t\t\t\t\t\t\t    \t\n");
            }
            if (s4.equals("S")) {
                if (!s.equals("")) {
                    sb.append(" AND\t\tINSTITU_FULL_NM like '%'||?||'%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                }
                if (!s2.equals("")) {
                    sb.append(" AND\tRECV_DT BETWEEN TO_DATE('" + string + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                    sb.append(" AND TO_DATE('" + string2 + "', 'DD/MM/YYYY HH24:MI:SS')\t    \n");
                }
            }
            if (s5.equals("1")) {
                sb.append(" AND\t\tREG_YN ='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("2")) {
                sb.append(" AND\t\tREG_YN ='N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("3")) {
                sb.append(" AND\t\tREG_YN ='E'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("4")) {
                sb.append(" AND\t\tREG_YN ='D'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("5")) {
                sb.append(" AND\t\tREG_YN ='C'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            sb.append("\t\t\tORDER BY RECV_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")\t\n");
            final String string3 = sb.toString();
            Log.debug("Cau lenh SQL: " + string3);
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string3);
            if (s4.equals("S") && !s.equals("")) {
                ++n3;
                prepareStatement.setString(n3, s);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_ADV_GovuA030b um_ADV_GovuA030b = new UM_ADV_GovuA030b();
                um_ADV_GovuA030b.setgoNameFull(executeQuery.getString(1));
                um_ADV_GovuA030b.setsaupNo(executeQuery.getString(2));
                um_ADV_GovuA030b.settaskmaster(executeQuery.getString(3));
                um_ADV_GovuA030b.setADDR(executeQuery.getString(4));
                um_ADV_GovuA030b.setenYn(executeQuery.getString(5));
                um_ADV_GovuA030b.setdependCode(executeQuery.getString(6));
                um_ADV_GovuA030b.setg2bCode(executeQuery.getString(7));
                um_ADV_GovuA030b.setLicenseCode(executeQuery.getString(8));
                um_ADV_GovuA030b.setrecept(executeQuery.getString(9));
                vector.addElement(um_ADV_GovuA030b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + s + "],ApprovalCode[" + s6 + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + s + "],ApprovalCode[" + s6 + "]):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_ADV_GovuA030b[] array = new UM_ADV_GovuA030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_ADV_GovuA030b[] approvalList1(final String s, final String s2, final int n, final int n2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        Trx trx = null;
        Connection connection = null;
        Log.debug("test vao ham nay chua == > approvalList1");
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector();
        final String string = s4 + " 00:00:01";
        final String string2 = s5 + " 23:59:59";
        int n3 = 0;
        try {
        	sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t            \t\t\t\t\t\t\t\t\n");
            sb.append(" \t\tREG_YN,\t\t\tRECV_NO,   INSTITU_CD,\tCERT_RECV_NO,  RECV_DT, NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" SELECT\t T1.INSTITU_FULL_NM,\t T1.BIZ_REG_NO,\t T1.CHRGR_NM,\t T1.ADDR||' ' ||T1.DETAIL_ADDR ADDR, T1.REG_YN,            \t\n");
            sb.append("    T1.RECV_NO,   T1.INSTITU_CD,   T1.CERT_RECV_NO,  TO_CHAR(T1.RECV_DT,'YYYY-MM-DD') RECV_DT                  \n");
            sb.append("   FROM\tUM_REC_PUB_INSTITU_MAST T1 \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
           
            if (s7.equals("1")) {//Đơn phê duyệt đăng kí bên mời thầu
            	sb.append(",  UM_PUB_INSTITU_MAST T2");
            }
            
            sb.append("    WHERE\t1=1\t\n");
            
            if (s7.equals("1")) {//Đơn phê duyệt đăng kí bên mời thầu
                sb.append(" AND T1.INSTITU_CD = T2.INSTITU_CD and (T2.TEST_OPTION_YN = 'N' or T2.TEST_OPTION_YN is null) \t\t\t\t\t\t\t\n");
            }
            
            if (!"".equals(s8) && !"null".equals(s8)) {
                sb.append("    AND T1.PERMIT_BRANCH = '" + s8 + "'\t\t    \t\t\t\t\t\t\t\t\t\t\t\t    \t\n");
            }
            if (!"".equals(s2)) {
                sb.append(" AND\t\t T1.ZIP_CD = '" + s2 + "'\t\t\t\t\t\n");
            }
            if (!"".equals(s)) {
                sb.append(" AND\t\tlower(trim(T1.INSTITU_CD)) like lower('%" + s.trim().toLowerCase() + "%')\t\t\t\t\t\n");
            }
            if (s6.equals("S")) {
                if (!s3.equals("")) {
                    sb.append(" AND\t\t lower(T1.INSTITU_FULL_NM) like lower('%" + s3.trim().toLowerCase() + "%')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                }
                if (!s4.equals("")) {
                    sb.append(" AND\t T1.RECV_DT BETWEEN TO_DATE('" + string + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                    sb.append(" AND TO_DATE('" + string2 + "', 'DD/MM/YYYY HH24:MI:SS')\t    \n");
                }
            }
            if (s7.equals("1")) {
                sb.append(" AND\t\t T1.REG_YN ='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s7.equals("2")) {
                sb.append(" AND\t\t T1.REG_YN ='N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s7.equals("3")) {
                sb.append(" AND\t\t T1.REG_YN ='E'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s7.equals("4")) {
                sb.append(" AND\t\t T1.REG_YN ='D'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s7.equals("5")) {
                sb.append(" AND\t\t T1.REG_YN ='C'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            sb.append("\t\t\tORDER BY T1.RECV_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")\t\n");
            final String string3 = sb.toString();
            Log.debug("Cau lenh SQL1: " + string3 + "*" + string + "*" + string2);
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string3);
//            if (s6.equals("S") && !s3.equals("")) {
//                ++n3;
//                prepareStatement.setString(n3, s3.trim().toLowerCase());
//            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_ADV_GovuA030b um_ADV_GovuA030b = new UM_ADV_GovuA030b();
                um_ADV_GovuA030b.setgoNameFull(executeQuery.getString(1));
                um_ADV_GovuA030b.setsaupNo(executeQuery.getString(2));
                um_ADV_GovuA030b.settaskmaster(executeQuery.getString(3));
                um_ADV_GovuA030b.setADDR(executeQuery.getString(4));
                um_ADV_GovuA030b.setenYn(executeQuery.getString(5));
                um_ADV_GovuA030b.setdependCode(executeQuery.getString(6));
                um_ADV_GovuA030b.setg2bCode(executeQuery.getString(7));
                um_ADV_GovuA030b.setLicenseCode(executeQuery.getString(8));
                um_ADV_GovuA030b.setrecept(executeQuery.getString(9));
                vector.addElement(um_ADV_GovuA030b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + s3 + "],ApprovalCode[" + s8 + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_GovuA030c.approvalList(upcheNm[" + s3 + "],ApprovalCode[" + s8 + "]):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_ADV_GovuA030b[] array = new UM_ADV_GovuA030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int approvalList_count(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        final String string = s2 + " 00:00:01";
        final String string2 = s3 + " 23:59:59";
        int n = 0;
        int int1 = 0;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  UM_REC_PUB_INSTITU_MAST, SYN_PUB_CODE C\t\t\t\n");
            sb.append(" WHERE\t\tZIP_CD = C.CD AND C.CD_CLS = 'GU7'\t\t\t\t\n");
            if (s5.equals("1")) {
                sb.append(" AND\t\tREG_YN ='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("2")) {
                sb.append(" AND\t\tREG_YN ='N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("3")) {
                sb.append(" AND\t\tREG_YN ='E'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("4")) {
                sb.append(" AND\t\tREG_YN ='D'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("5")) {
                sb.append(" AND\t\tREG_YN ='C'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            Log.debug("Tham so: " + s6);
            if (!"".equals(s6) && !"null".equals(s6)) {
                sb.append(" AND\t\tPERMIT_BRANCH ='" + s6 + "'\t\t\t\t\t\n");
            }
            if (!"".equals(s7) && s7 != null) {
                sb.append(" AND\t\tZIP_CD ='" + s7 + "'\t\t\t\t\t\n");
            }
            if (!"".equals(s8)) {
                sb.append(" AND\t\tINSTITU_CD ='" + s8 + "'\t\t\t\t\t\n");
            }
            if (s4.equals("S")) {
                if (!s.equals("")) {
                    sb.append(" AND\t\tINSTITU_FULL_NM like '%'||?||'%'\t\n");
                }
                if (!s2.equals("")) {
                    sb.append(" AND\t\tRECV_DT BETWEEN TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')     \n");
                    sb.append("  AND TO_DATE(?, 'YYYY/MM/DD HH24:MI:SS')\t \n");
                }
            }
            final String string3 = sb.toString();
            Log.debug("Cau lenh SQL: " + string3);
            prepareStatement = connection.prepareStatement(string3);
            if (s4.equals("S") && !s.equals("")) {
                ++n;
                prepareStatement.setString(n, s);
                Log.debug("Tham so thu 1: " + s);
            }
            if (s2.length() >= 1) {
                ++n;
                prepareStatement.setString(n, string);
                Log.debug("Tham so thu 2: " + string);
                ++n;
                prepareStatement.setString(n, string2);
                Log.debug("Tham so thu 3: " + string2);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_GovuA030c.approvalList_count(upcheNm[" + s + "],ApprovalCode[" + s6 + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_GovuA030c.approvalList_count(upcheNm[" + s + "],ApprovalCode[" + s6 + "]):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public int approvalList_count(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        final String string = s2 + " 00:00:01";
        final String string2 = s3 + " 23:59:59";
        int n = 0;
        int int1 = 0;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  UM_REC_PUB_INSTITU_MAST\t\t\t\n");
            if (s5.equals("1")) {
                sb.append(" WHERE\t\tREG_YN ='Y'\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("2")) {
                sb.append(" WHERE\t\tREG_YN ='N'\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("3")) {
                sb.append(" WHERE\t\tREG_YN ='E'\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("4")) {
                sb.append(" WHERE\t\tREG_YN ='D'\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("5")) {
                sb.append(" WHERE\t\tREG_YN ='C'\t\t\t\t\t\t\t\n");
            }
            else {
                sb.append(" WHERE\t\tREG_YN like '%%'  \t\t\t\t\t\n");
            }
            Log.debug("Tham so: " + s6);
            if (!"".equals(s6) && !"null".equals(s6)) {
                sb.append(" AND\t\tPERMIT_BRANCH ='" + s6 + "'\t\t\t\t\t\n");
            }
            if (s4.equals("S")) {
                if (!s.equals("")) {
                    sb.append(" AND\t\tINSTITU_FULL_NM like '%'||?||'%'\t\n");
                }
                if (!s2.equals("")) {
                    sb.append(" AND\tRECV_DT BETWEEN TO_DATE('" + string + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                    sb.append(" AND TO_DATE('" + string2 + "', 'DD/MM/YYYY HH24:MI:SS')\t \n");
                }
            }
            final String string3 = sb.toString();
            Log.debug("Cau lenh SQL: " + string3);
            prepareStatement = connection.prepareStatement(string3);
            if (s4.equals("S") && !s.equals("")) {
                ++n;
                prepareStatement.setString(n, s);
                Log.debug("Tham so thu 1: " + s);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_GovuA030c.approvalList_count(upcheNm[" + s + "],ApprovalCode[" + s6 + "]):" + ex.toString() + string + string2);
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_GovuA030c.approvalList_count(upcheNm[" + s + "],ApprovalCode[" + s6 + "]):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public int approvalList_count1(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        final String string = s4 + " 00:00:01";
        final String string2 = s5 + " 23:59:59";
        int n = 0;
        int int1 = 0;
        Log.debug("call method approvalList_count1() ====>");
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  UM_REC_PUB_INSTITU_MAST T1\t\t\t\n");
            if (s7.equals("1")) {//Đơn phê duyệt đăng kí bên mời thầu
            	sb.append(", UM_PUB_INSTITU_MAST T2");
                sb.append(" WHERE\t\t T1.REG_YN ='Y'\t\t\t\t\t\t\t\n");
                sb.append(" AND T1.INSTITU_CD = T2.INSTITU_CD and (T2.TEST_OPTION_YN = 'N' or T2.TEST_OPTION_YN is null) \t\t\t\t\t\t\t\n");
            }
            else if (s7.equals("2")) {
                sb.append(" WHERE\t\t T1.REG_YN ='N'\t\t\t\t\t\t\t\n");
            }
            else if (s7.equals("3")) {
                sb.append(" WHERE\t\t T1.REG_YN ='E'\t\t\t\t\t\t\t\n");
            }
            else if (s7.equals("4")) {
                sb.append(" WHERE\t\t T1.REG_YN ='D'\t\t\t\t\t\t\t\n");
            }
            else if (s7.equals("5")) {
                sb.append(" WHERE\t\t T1.REG_YN ='C'\t\t\t\t\t\t\t\n");
            }
            else {
                sb.append(" WHERE\t\t T1.REG_YN like '%%'  \t\t\t\t\t\n");
            }
            Log.debug("Tham so: " + s8);
            if (!"".equals(s8) && !"null".equals(s8)) {
                sb.append(" AND\t\t T1.PERMIT_BRANCH ='" + s8 + "'\t\t\t\t\t\n");
            }
            if (!"".equals(s2)) {
                sb.append(" AND\t\t T1.ZIP_CD = '" + s2 + "'\t\t\t\t\t\n");
            }
            if (!"".equals(s)) {
                sb.append(" AND\t\tlower(trim(T1.INSTITU_CD)) like lower('%" + s.trim() + "%')\t\t\t\t\t\n");
            }
            if (s6.equals("S")) {
                if (!s3.equals("")) {
                    sb.append(" AND\t\t lower(T1.INSTITU_FULL_NM) like lower('%" + s3.trim().toLowerCase() + "%')\t\n");
                }
                if (!s4.equals("")) {
                    sb.append(" AND\t T1.RECV_DT BETWEEN TO_DATE('" + string + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                    sb.append(" AND TO_DATE('" + string2 + "', 'DD/MM/YYYY HH24:MI:SS')\t \n");
                }
            }
            final String string3 = sb.toString();
            Log.debug("Cau lenh SQL: " + string3 + "*" + s4 + "*" + s5);
            prepareStatement = connection.prepareStatement(string3);
//            if (s6.equals("S") && !s3.equals("")) {
//                ++n;
//                prepareStatement.setString(n, s3.trim().toLowerCase());
//                Log.debug("Tham so thu 1: " + s3);
//            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_GovuA030c.approvalList_count(upcheNm[" + s3 + "],ApprovalCode[" + s8 + "]):" + ex.toString() + string + string2);
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_GovuA030c.approvalList_count(upcheNm[" + s3 + "],ApprovalCode[" + s8 + "]):" + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    
    
    
    
    
    public int approvalTotalListCount(String upcheNm, String upcheCode, String area, String permitBranch, String startDate, String endDate) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        if (startDate != null && startDate != "") {
        	startDate = startDate + " 00:00:01";
        }
        if (endDate != null && endDate != "") {
        	endDate = endDate + " 00:00:01";
        }
        int countTotal = 0;
        Log.debug("call method approvalTotalListCount() ====>");
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append("SELECT COUNT(*) FROM USEMN.UM_PUB_INSTITU_MAST T1, USEMN.UM_REC_PUB_INSTITU_MAST T2, CUST.PUB_CODE T3");
            sb.append("  WHERE T1.DELETE_YN = 'N' AND T1.HIDDEN_YN = 'N' AND T3.CD_CLS = 'GU7' AND T1.ZIP_CD = T3.CD AND T1.INSTITU_CD = T2.INSTITU_CD AND T2.REG_YN ='Y'");
            sb.append(" AND (T1.TEST_OPTION_YN = 'N' or T1.TEST_OPTION_YN is null)");
            
            if (!"".equals(permitBranch) && !"null".equals(permitBranch)) {
                sb.append(" AND T1.PERMIT_BRANCH ='" + permitBranch + "'\t\t\t\t\t\n");
            }
            
            if (!"".equals(area)) {
                sb.append(" AND T1.ZIP_CD = '" + area + "' \n");
            }
            
            if (!"".equals(upcheCode)) {
                sb.append(" AND lower(trim(T1.INSTITU_CD)) like lower('%" + upcheCode.trim() + "%') \n");
            }
            if (!"".equals(upcheNm)) {
                sb.append(" AND lower(trim(T1.INSTITU_FULL_NM)) like lower('%" + upcheNm.trim() + "%') \n");
            }
            if (!"".equals(startDate) && startDate != null) {
                sb.append(" AND\t T2.RECV_DT BETWEEN TO_DATE('" + startDate + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                sb.append(" AND TO_DATE('" + endDate + "', 'DD/MM/YYYY HH24:MI:SS')\t \n");
            }
            final String strQuery = sb.toString();
            Log.debug("Cau lenh SQL: " + strQuery);
            prepareStatement 		= connection.prepareStatement(strQuery);
            executeQuery 			= prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                countTotal = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("approvalTotalListCount() >>> " + sb.toString());
        }
        catch (Exception ex2) {
            Log.debug("approvalTotalListCount() >>> " + sb.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return countTotal;
    }
    
    
    public UM_ADV_GovuA030b[] approvalTotalList(String upcheNm, String upcheCode, String area, String permitBranch, String startDate, String endDate, String pageSize, String pageNo) {
        Trx trx = null;
        Connection connection = null;
        Log.debug("test vao ham nay chua == > approvalTotalList");
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector();
        if (startDate != null && startDate != "") {
        	startDate = startDate + " 00:00:01";
        }
        if (endDate != null && endDate != "") {
        	endDate = endDate + " 00:00:01";
        }
        try {
        	sb.append(" SELECT INSTITU_FULL_NM,BIZ_REG_NO,CHRGR_NM,ADDR,RECV_NO,INSTITU_CD,INSTITU_PHONE_NO,RECV_DT,CD_NM2 FROM (SELECT ROWNUM NUM, RESUL.* FROM ( ");
        	sb.append(" SELECT T1.INSTITU_FULL_NM,T1.BIZ_REG_NO,T1.CHRGR_NM,T1.ADDR,T1.RECV_NO,T1.INSTITU_CD,T1.INSTITU_PHONE_NO,TO_CHAR(T2.RECV_DT,'YYYY-MM-DD') RECV_DT,T3.CD_NM2 ");
        	sb.append(" FROM USEMN.UM_PUB_INSTITU_MAST T1, USEMN.UM_REC_PUB_INSTITU_MAST T2, CUST.PUB_CODE T3");
            sb.append(" WHERE T1.DELETE_YN = 'N' AND T1.HIDDEN_YN = 'N' AND T3.CD_CLS = 'GU7' AND T1.ZIP_CD = T3.CD AND T1.INSTITU_CD = T2.INSTITU_CD AND T2.REG_YN ='Y'");
            sb.append(" AND (T1.TEST_OPTION_YN = 'N' or T1.TEST_OPTION_YN is null)");
            
            if (!"".equals(permitBranch) && !"null".equals(permitBranch)) {
                sb.append(" AND T1.PERMIT_BRANCH ='" + permitBranch + "'\t\t\t\t\t\n");
            }
            
            if (!"".equals(area)) {
                sb.append(" AND T1.ZIP_CD = '" + area + "' \n");
            }
            
            if (!"".equals(upcheCode)) {
                sb.append(" AND lower(trim(T1.INSTITU_CD)) like lower('%" + upcheCode.trim() + "%') \n");
            }
            if (!"".equals(upcheNm)) {
                sb.append(" AND lower(trim(T1.INSTITU_FULL_NM)) like lower('%" + upcheNm.trim() + "%') \n");
            }
            if (!"".equals(startDate) && startDate != null) {
                sb.append(" AND\t T2.RECV_DT BETWEEN TO_DATE('" + startDate + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                sb.append(" AND TO_DATE('" + endDate + "', 'DD/MM/YYYY HH24:MI:SS')\t \n");
            }
            sb.append("ORDER BY T2.RECV_DT DESC) RESUL ) WHERE NUM BETWEEN " +( (Integer.parseInt(pageNo) - 1) * (Integer.parseInt(pageSize)) + 1) + " AND " + ((Integer.parseInt(pageNo)*(Integer.parseInt(pageSize))) ));
            
            final String strQuery = sb.toString();
            Log.debug("approvalTotalList() >>> Cau lenh SQL: " + strQuery);
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(strQuery);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_ADV_GovuA030b um_ADV_GovuA030b = new UM_ADV_GovuA030b();
                um_ADV_GovuA030b.setgoNameFull(executeQuery.getString(1));//ten nha thau
                um_ADV_GovuA030b.setsaupNo(executeQuery.getString(2));//biz_reg_no
                um_ADV_GovuA030b.settaskmaster(executeQuery.getString(3));//CHRGR_NM
                um_ADV_GovuA030b.setADDR(executeQuery.getString(4));//addr
                um_ADV_GovuA030b.setenYn(executeQuery.getString(5));//recv_no
                um_ADV_GovuA030b.setdependCode(executeQuery.getString(6));//institu_cd
                um_ADV_GovuA030b.setmasterTel(executeQuery.getString(7));//sdt
                um_ADV_GovuA030b.setcreateDate(executeQuery.getString(8));//ngay dk
                um_ADV_GovuA030b.setpermitCode(executeQuery.getString(9));//tinh thanh pho
                vector.addElement(um_ADV_GovuA030b);
            }
        }
        catch (SQLException ex) {
        	
        }
        catch (Exception ex2) {
        	
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_ADV_GovuA030b[] array = new UM_ADV_GovuA030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public UM_ADV_GovuA030b[] approvalTotalListToExport(String upcheNm, String upcheCode, String area, String permitBranch, String startDate, String endDate) {
        Trx trx = null;
        Connection connection = null;
        Log.debug("test vao ham nay chua == > approvalTotalList export");
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector();
        
        if (startDate != null && startDate != "") {
        	startDate = startDate + " 00:00:01";
        }
        
        if (endDate != null && endDate != "") {
        	endDate = endDate + " 00:00:01";
        }
        
        Log.debug("approvalTotalList export >> startDate=" + startDate + " >> endDate="+endDate);
        try {
        	sb.append(" SELECT INSTITU_FULL_NM,BIZ_REG_NO,CHRGR_NM,ADDR,RECV_NO,INSTITU_CD,INSTITU_PHONE_NO,RECV_DT,CD_NM2 FROM (SELECT ROWNUM NUM, RESUL.* FROM ( ");
        	sb.append(" SELECT T1.INSTITU_FULL_NM,T1.BIZ_REG_NO,T1.CHRGR_NM,T1.ADDR,T1.RECV_NO,T1.INSTITU_CD,T1.INSTITU_PHONE_NO,TO_CHAR(T2.RECV_DT,'YYYY-MM-DD') RECV_DT,T3.CD_NM2 ");
        	sb.append(" FROM USEMN.UM_PUB_INSTITU_MAST T1, USEMN.UM_REC_PUB_INSTITU_MAST T2, CUST.PUB_CODE T3");
            sb.append(" WHERE T1.DELETE_YN = 'N' AND T1.HIDDEN_YN = 'N' AND T3.CD_CLS = 'GU7' AND T1.ZIP_CD = T3.CD AND T1.INSTITU_CD = T2.INSTITU_CD AND T2.REG_YN ='Y'");
            sb.append(" AND (T1.TEST_OPTION_YN = 'N' or T1.TEST_OPTION_YN is null)");
            
            if (!"".equals(permitBranch) && permitBranch != null) {
                sb.append(" AND T1.PERMIT_BRANCH ='" + permitBranch + "'\t\t\t\t\t\n");
            }
            
            if (!"".equals(area) && area != null) {
                sb.append(" AND T1.ZIP_CD = '" + area + "' \n");
            }
            
            if (!"".equals(upcheCode) && upcheCode != null) {
                sb.append(" AND lower(trim(T1.INSTITU_CD)) like lower('%" + upcheCode.trim() + "%') \n");
            }
            if (!"".equals(upcheNm) && upcheNm != null) {
                sb.append(" AND lower(trim(T1.INSTITU_FULL_NM)) like lower('%" + upcheNm.trim() + "%') \n");
            }
            if (!"".equals(startDate) && startDate != null) {
                sb.append(" AND\t T2.RECV_DT BETWEEN TO_DATE('" + startDate + "', 'DD/MM/YYYY HH24:MI:SS')     \n");
                sb.append(" AND TO_DATE('" + endDate + "', 'DD/MM/YYYY HH24:MI:SS')\t \n");
            }
            sb.append("ORDER BY T2.RECV_DT DESC) RESUL )");
            
            final String strQuery = sb.toString();
            Log.debug("approvalTotalList export >> Cau lenh SQL: " + strQuery);
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(strQuery);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_ADV_GovuA030b um_ADV_GovuA030b = new UM_ADV_GovuA030b();
                um_ADV_GovuA030b.setgoNameFull(executeQuery.getString(1));//ten nha thau
                um_ADV_GovuA030b.setsaupNo(executeQuery.getString(2));//biz_reg_no
                um_ADV_GovuA030b.settaskmaster(executeQuery.getString(3));//CHRGR_NM
                um_ADV_GovuA030b.setADDR(executeQuery.getString(4));//addr
                um_ADV_GovuA030b.setenYn(executeQuery.getString(5));//recv_no
                um_ADV_GovuA030b.setdependCode(executeQuery.getString(6));//institu_cd
                um_ADV_GovuA030b.setmasterTel(executeQuery.getString(7));//sdt
                um_ADV_GovuA030b.setcreateDate(executeQuery.getString(8));//ngay dk
                um_ADV_GovuA030b.setpermitCode(executeQuery.getString(9));//tinh thanh pho
                vector.addElement(um_ADV_GovuA030b);
            }
        }
        catch (SQLException ex) {
        	 Log.debug("approvalTotalList export >> exception1 >> " + ex.getMessage());
        }
        catch (Exception ex2) {
        	Log.debug("approvalTotalList export >> exception2 >> " + ex2.getMessage());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        final UM_ADV_GovuA030b[] array = new UM_ADV_GovuA030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
}
