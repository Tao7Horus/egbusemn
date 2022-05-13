// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.util.Vector;
import entity.UM_ADV_GovuA030b;

public class UM_URB_GovuA030c_001
{
    public UM_ADV_GovuA030b[] approvalList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_ADV_GovuA030b>();
        final String string = s2 + " 00:00:01";
        final String string2 = s3 + " 23:59:59";
        int n3 = 0;
        try {
            sb.append(" SELECT\tINSTITU_FULL_NM,\tBIZ_REG_NO,\tCHRGR_NM,\tADDR,\t\n");
            sb.append(" \t\tREG_YN,\t\t\tRECV_NO,   INSTITU_CD,\tCERT_RECV_NO,  RECV_DT ,  NUM  \t\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\n");
            sb.append("    SELECT A.*,  ROWNUM NUM        \t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\n");
            sb.append(" SELECT\tB.INSTITU_FULL_NM,\tB.BIZ_REG_NO,\tB.CHRGR_NM,\tB.ADDR|| ' ' ||B.DETAIL_ADDR ADDR, B.REG_YN,            \t\n");
            sb.append("    B.RECV_NO,   B.INSTITU_CD,   B.CERT_RECV_NO,  TO_CHAR(B.RECV_DT,'DD-MM-YYYY') RECV_DT                     \n");
            sb.append("   FROM\tUM_REC_PUB_INSTITU_MAST\tB\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE\tPERMIT_BRANCH like '%'");
            if (s4.equals("S")) {
                if (s.length() >= 1) {
                    sb.append(" AND\t\tlower(B.INSTITU_FULL_NM) like '%'||?||'%'\t\n");
                }
                if (s2.length() >= 1) {
                    sb.append(" AND\t\tB.RECV_DT BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS')     \n");
                    sb.append("                      AND TO_DATE(?,   'DD/MM/YYYY HH24:MI:SS')\t\n");
                }
            }
            if (s5.equals("1")) {
                sb.append(" AND\t\tB.REG_YN ='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("2")) {
                sb.append(" AND\t\tB.REG_YN ='N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("3")) {
                sb.append(" AND\t\tB.REG_YN ='C'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("4")) {
                sb.append(" AND\t\tB.REG_YN ='E'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("5")) {
                sb.append(" AND\t\tB.REG_YN ='D'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            sb.append("\t\t\tORDER BY B.RECV_DT DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\t) \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  NUM BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")\t\n");
            final String string3 = sb.toString();
            trx = new Trx(this);
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string3);
            if (s4.equals("S") && s.length() >= 1) {
                ++n3;
                prepareStatement.setString(n3, s.toLowerCase());
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
            trx = new Trx(this);
            connection = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  UM_REC_PUB_INSTITU_MAST\t\t\t\n");
            if (s5.equals("1")) {
                sb.append(" WHERE\t\tREG_YN ='Y'\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("2")) {
                sb.append(" WHERE\t\tREG_YN ='N'\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("3")) {
                sb.append(" WHERE\t\tREG_YN ='C'\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("4")) {
                sb.append(" WHERE\t\tREG_YN ='E'\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("5")) {
                sb.append(" WHERE\t\tREG_YN ='D'\t\t\t\t\t\t\t\n");
            }
            else {
                sb.append(" WHERE\t\tREG_YN like '%%'  \t\t\t\t\t\n");
            }
            if (s4.equals("S")) {
                if (s.length() >= 1) {
                    sb.append(" AND\t\tlower(INSTITU_FULL_NM) like '%'||?||'%'\t\n");
                }
                if (s2.length() >= 1) {
                    sb.append(" AND\t\tRECV_DT BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS')     \n");
                    sb.append("                      AND TO_DATE(?,   'DD/MM/YYYY HH24:MI:SS')\t\n");
                }
            }
            prepareStatement = connection.prepareStatement(sb.toString());
            if (s4.equals("S") && s.length() >= 1) {
                ++n;
                prepareStatement.setString(n, s.toLowerCase());
            }
            if (s2.length() >= 1) {
                ++n;
                prepareStatement.setString(n, string);
                ++n;
                prepareStatement.setString(n, string2);
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
    
    public int approvalList_count() {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        int int1 = 0;
        try {
            trx = new Trx(this);
            connection = trx.getConnection();
            sb.append(" SELECT COUNT(*) FROM UM_PUB_INSTITU_MAST a, SYN_PUB_CODE b WHERE a.ZIP_CD = b.CD and b.CD_CLS = 'GU7' and a.HIDDEN_YN = 'N' AND a.DELETE_YN = 'N' ");
            prepareStatement = connection.prepareStatement(sb.toString());
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_GovuA030c.approvalList_count():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_GovuA030c.approvalList_count():" + ex2.toString());
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
    
    public int DeleteBizRegNo() {
        try {
            final String[] array = { "UM_LICENSE_FACTS", "UM_USER", "UM_USER_HIST", "UM_CERT_INFO_HIST", "UM_CERT_INFO", "UM_SUPPLIER_ENTER_MAST_HIST", "UM_SUPPLIER_ENTER_MAST", "UM_SUPPLIER_ENTER_ITEMS", "UM_REC_SUPPLIER_ENTER_MAST", "UM_REC_REPR", "UM_REC_BID_AGENT", "UM_REC_ENTER_CLS", "UM_REC_LICENSE_FACTS", "UM_REC_FACTORY_INFO", "UM_REC_PUB_INSTITU_CERT", "UM_REC_PUB_INSTITU_MAST", "UM_REC_SUPPLIER_ENTER_ITEM", "UM_BID_QUALIFY_FACTS_HIST", "UM_BID_AGENT", "UM_SEAL_MAST", "UM_ENTER_STATE_HIST", "UM_ENTER_STATE", "UM_ENTER_CLS", "UM_REPR", "UM_FACTORY_INFO", "UM_PUB_INSTITU_HIST", "UM_PUB_INSTITU_MAST", "UM_ADDED_USER", "UM_EDOC_STATE", "UM_ENTER_STD_CLS", "UM_REC_ENTER_STD_CLS" };
            final String[] array2 = { "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" };
            for (int i = 0; i < array2.length; ++i) {
                for (int j = 0; j < array.length; ++j) {
                    final Trx trx = new Trx(this);
                    final Connection connection = trx.getConnection();
                    final String string = " delete from " + array[j] + " where BIZ_REG_NO = '" + array2[i] + "'";
                    Log.debug(string);
                    final PreparedStatement prepareStatement = connection.prepareStatement(string);
                    prepareStatement.executeQuery().close();
                    prepareStatement.close();
                    trx.close();
                    connection.close();
                }
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_GovuA030c_001.DeleteBizRegNo():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_GovuA030c_001.DeleteBizRegNo():" + ex2.toString());
        }
        return 1;
    }
    
    public void DeleteInstituCd(final String s) {
        try {
            final String[] array = { "UM_USER", "UM_USER_HIST", "UM_PUB_INSTITU", "UM_CERT_INFO", "UM_CERT_INFO_HIST", "UM_REC_PUB_INSTITU_CERT", "UM_REC_PUB_INSTITU_MAST", "UM_PUB_INSTITU_ACCT_CD", "UM_PUB_INSTITU_HIST", "UM_PUB_INSTITU_MAST", "UM_BANK_INFO", "UM_PUB_INSTITU_CD" };
            for (int i = 0; i < array.length; ++i) {
                final Trx trx = new Trx(this);
                final Connection connection = trx.getConnection();
                final String string = " delete from " + array[i] + " where INSTITU_CD = '" + s + "'";
                Log.debug(string);
                final PreparedStatement prepareStatement = connection.prepareStatement(string);
                prepareStatement.executeQuery().close();
                prepareStatement.close();
                trx.close();
                connection.close();
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_GovuA030c_001.DeleteInstituCd():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_GovuA030c_001.DeleteInstituCd():" + ex2.toString());
        }
    }
    
    public int UpdateSynPubCode() {
        try {
            final Trx trx = new Trx(this);
            final Connection connection = trx.getConnection();
            final String s = "update syn_pub_code set cd_nm = 'Hoàn thành mở thầu' where cd_cls = 'Z06' and cd = '1'";
            Log.debug(s);
            final PreparedStatement prepareStatement = connection.prepareStatement(s);
            prepareStatement.executeQuery().close();
            prepareStatement.close();
            trx.close();
            connection.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_URB_GovuA030c_001.UpdateSynPubCode():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_URB_GovuA030c_001.UpdateSynPubCode():" + ex2.toString());
        }
        return 1;
    }
}
