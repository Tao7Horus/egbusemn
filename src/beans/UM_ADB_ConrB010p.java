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
import common.ComStr;
import java.util.Vector;
import entity.UM_RAE_ConuB010b;

public class UM_ADB_ConrB010p
{
    public UM_RAE_ConuB010b[] select_comlist(final int pagenum, final int PAGEMAX, String total, String saupNo, final String sangho, String sdate, String edate, final String id, final String jichung) {
        Connection con = null;
        Trx trx = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_RAE_ConuB010b[] ettlist = (UM_RAE_ConuB010b[])null;
        saupNo = ComStr.replace(saupNo, "-", "");
        total = ((total == null) ? "" : total);
        String yyyy = "";
        String mm = "";
        String dd = "";
        if (sdate.indexOf("/") > 0) {
            dd = sdate.substring(0, 2);
            mm = sdate.substring(3, 5);
            yyyy = sdate.substring(6, 10);
        }
        sdate = String.valueOf(yyyy) + mm + dd;
        if (edate.indexOf("/") > 0) {
            dd = edate.substring(0, 2);
            mm = edate.substring(3, 5);
            yyyy = edate.substring(6, 10);
        }
        edate = String.valueOf(yyyy) + mm + dd;
        String sql = " SELECT BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, ZIP_CD, ADDR,                   \t\t\t        DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_ST, PROCESS_RSON, XML_DOC_LOCATE, TRANS_NO, EMAIL , MOBILE, SMS_RECV_YN , ROWNUM N  FROM\t        ( SELECT BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, ZIP_CD, ADDR,\t\t\t\t\t\t                 DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_ST, PROCESS_RSON, XML_DOC_LOCATE, TRANS_NO, EMAIL , MOBILE, SMS_RECV_YN, ROWNUM N\t\t            FROM (SELECT a.BIZ_REG_NO, a.MOD_CLS, a.REG_DT, a.COM_NM, a.REPR_NM, a.ZIP_CD, a.ADDR,      \t\t               a.DETAIL_ADDR, a.PHONE_NO, a.WEBSITE, a.PROCESS_ST, a.PROCESS_RSON, a.XML_DOC_LOCATE, a.TRANS_NO, a.EMAIL, a.MOBILE, a.SMS_RECV_YN  \t                  FROM UM_EDOC_STATE  a, UM_REC_SUPPLIER_ENTER_MAST b                 \t\t\t\t\t\t\t\t\t                   WHERE a.BIZ_REG_NO = b.BIZ_REG_NO(+) and a.MOD_CLS = '2'     \t\t\t\t\t\t\t \t\t\t\t\t   AND b.BID_ATTEND_QUALIFY_YN IS NULL\t\t";
        if (!total.equals("-1")) {
            sql = String.valueOf(sql) + " \t                   AND a.PROCESS_ST =" + total;
        }
        if (saupNo.length() > 0) {
            sql = String.valueOf(sql) + " AND a.BIZ_REG_NO like '%" + saupNo + "%' ";
        }
        if (sangho.length() > 0) {
            sql = String.valueOf(sql) + " AND a.COM_NM like '%" + sangho + "%'";
        }
        if (sdate.length() > 0 && edate.length() > 0) {
            sql = String.valueOf(sql) + " AND to_char(a.REG_DT,'yyyyMMdd')>='" + sdate + "'";
            sql = String.valueOf(sql) + " AND to_char(a.REG_DT,'yyyyMMdd')<='" + edate + "'";
        }
        sql = String.valueOf(sql) + " order by REG_DT desc ) WHERE ROWNUM <= (" + pagenum + " * " + PAGEMAX + ") ) sub WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            System.out.println("==================" + sql);
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
                ett.setHP(rs.getString(16));
                ett.setsmsCheck(rs.getString(17));
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URV_UserD020p.select_userlist block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URV_UserD020p.select_userlist block Exception : " + exc.toString());
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
        if (vec.size() > 0) {
            ettlist = new UM_RAE_ConuB010b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public int Max_count(String total, final String saupNo, final String sangho, String sdate, String edate, final String id, final String jichung) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        total = ((total == null) ? "" : total);
        String yyyy = "";
        String mm = "";
        String dd = "";
        if (sdate.indexOf("/") > 0) {
            dd = sdate.substring(0, 2);
            mm = sdate.substring(3, 5);
            yyyy = sdate.substring(6, 10);
            sdate = String.valueOf(yyyy) + mm + dd;
        }
        while (true) {
            if (edate.indexOf("/") > 0) {
                dd = edate.substring(0, 2);
                mm = edate.substring(3, 5);
                yyyy = edate.substring(6, 10);
                edate = String.valueOf(yyyy) + mm + dd;
                try {
                    final StringBuffer sql = new StringBuffer("SELECT count(*) FROM ");
                    sql.append(" (SELECT BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, ZIP_CD, ADDR,").append("        DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_ST, PROCESS_RSON, XML_DOC_LOCATE, TRANS_NO, EMAIL , MOBILE, SMS_RECV_YN , ROWNUM N  FROM\t\t\t").append("        ( SELECT BIZ_REG_NO, MOD_CLS, REG_DT, COM_NM, REPR_NM, ZIP_CD, ADDR,\t\t\t\t\t\t").append("                 DETAIL_ADDR, PHONE_NO, WEBSITE, PROCESS_ST, PROCESS_RSON, XML_DOC_LOCATE, TRANS_NO, EMAIL , MOBILE, SMS_RECV_YN, ROWNUM N\t\t").append("            FROM (SELECT a.BIZ_REG_NO, a.MOD_CLS, a.REG_DT, a.COM_NM, a.REPR_NM, a.ZIP_CD, a.ADDR, ").append("     \t\t               a.DETAIL_ADDR, a.PHONE_NO, a.WEBSITE, a.PROCESS_ST, a.PROCESS_RSON, a.XML_DOC_LOCATE, a.TRANS_NO, a.EMAIL, a.MOBILE, a.SMS_RECV_YN  ").append("\t                  FROM UM_EDOC_STATE  a, UM_REC_SUPPLIER_ENTER_MAST b                 \t\t\t\t\t\t\t\t\t").append("                   WHERE a.BIZ_REG_NO = b.BIZ_REG_NO(+) and a.MOD_CLS = '2'     \t\t\t\t\t\t\t").append(" \t\t\t\t\t   AND b.BID_ATTEND_QUALIFY_YN IS NULL\t\t");
                    if (!total.equals("-1")) {
                        sql.append("      AND a.PROCESS_ST =" + total);
                    }
                    if (saupNo.length() > 0) {
                        sql.append(" AND a.BIZ_REG_NO like '%" + saupNo + "%'");
                    }
                    if (sangho.length() > 0) {
                        sql.append(" AND a.COM_NM like '%" + sangho + "%'");
                    }
                    if (sdate.length() > 0 && edate.length() > 0) {
                        sql.append(" AND to_char(a.REG_DT,'yyyyMMdd')>='" + sdate + "'");
                        sql.append(" AND to_char(a.REG_DT,'yyyyMMdd')<='" + edate + "'");
                    }
                    sql.append(")))");
                    trx = new Trx(this, "usemn");
                    con = trx.getConnection();
                    pstm = con.prepareStatement(sql.toString());
                    System.out.println("==================" + sql.toString());
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        count = rs.getInt(1);
                    }
                }
                catch (SQLException sqle) {
                    Log.debug("UM_ADB_ConrB010p.Max_count block SQLException : " + sqle.toString());
                }
                catch (Exception exc) {
                    Log.debug("UM_ADB_ConrB010p.Max_count block Exception : " + exc.toString());
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
            continue;
        }
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
        return jichung;
    }
}
