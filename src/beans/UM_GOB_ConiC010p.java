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
import entity.UM_GOE_ConiC080b;

public class UM_GOB_ConiC010p
{
    public UM_GOE_ConiC080b[] getUpcheInto(final int pagenum, final int PAGEMAX, final String gubun, String saupNo, final String sangho, final String localCode, final String sdate, final String edate, final String license, final String mulpumCode, final String daepyo, String daepyojumin, final String Dchange) throws Exception {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector(1, 1);
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String sql = "";
        final int pstm_inx = 0;
        UM_GOE_ConiC080b[] ettlist = (UM_GOE_ConiC080b[])null;
        saupNo = ComStr.replace(saupNo, "-", "");
        daepyojumin = ComStr.replace(daepyojumin, "-", "");
        try {
            sql = " select  BIZ_REG_NO, BIZ_NM, ADDR,PHONE_NO,DETAIL_ADDR, N ";
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + ", REPR_NM";
            }
            sql = String.valueOf(sql) + " from (";
            sql = String.valueOf(sql) + " select a.BIZ_REG_NO, a.BIZ_NM, a.ADDR,a.DETAIL_ADDR, a.PHONE_NO, rownum N";
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + ", b.REPR_NM";
            }
            sql = String.valueOf(sql) + " from UM_SUPPLIER_ENTER_MAST a";
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + "\n , UM_REC_PUB_INSTITU_CERT b ";
            }
            sql = String.valueOf(sql) + " where a.BIZ_REG_NO is not null ";
            sql = String.valueOf(sql) + "\n and a.BID_ATTEND_QUALIFY_YN is null ";
            sql = String.valueOf(sql) + "\n and (a.TEST_OPTION_YN is null OR a.TEST_OPTION_YN = 'N') ";
            if (sangho.length() > 0) {
                sql = String.valueOf(sql) + " and lower(a.BIZ_NM) like lower('%" + sangho + "%') ";
            }
            if (gubun.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_CLS like '" + gubun + "'";
            }
            if (localCode.length() > 0) {
                sql = String.valueOf(sql) + " and a.AREA_CD = '" + localCode + "' ";
            }
            if (saupNo.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_REG_NO like '%" + saupNo + "%'";
            }
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_REG_NO = b.BIZ_REG_NO ";
                if (daepyo.length() > 0) {
                    sql = String.valueOf(sql) + " and lower(REPR_NM) like lower('%" + daepyo + "%') ";
                }
                if (daepyojumin.length() > 0) {
                    sql = String.valueOf(sql) + " and b.REPR_IDENT_NO like '%" + daepyojumin + "%'";
                }
            }
            if (license.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_REG_NO in (select BIZ_REG_NO from um_enter_std_cls where a.BIZ_REG_NO=BIZ_REG_NO and STD_CLS_CD='" + license + "')";
            }
            if (sdate.length() > 0 && edate.length() > 0) {
                if (Dchange.equals("RE")) {
                    sql = String.valueOf(sql) + "\n and a.REG_DT between to_date('" + sdate + "','dd/MM/yyyy') and to_date('" + edate + "','dd/MM/yyyy')";
                }
                if (Dchange.equals("NE")) {
                    sql = String.valueOf(sql) + "\n and a.UPDATE_DT between to_date('" + sdate + "','dd/MM/yyyy') and to_date('" + edate + "','dd/MM/yyyy')";
                }
            }
            sql = String.valueOf(sql) + " and rownum <= (" + pagenum + " * " + PAGEMAX + ") ) sub where N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                final UM_GOE_ConiC080b joinEtt = new UM_GOE_ConiC080b();
                joinEtt.ett1.setSaupNo(rs.getString(1));
                joinEtt.ett1.setSangho(rs.getString(2));
                joinEtt.ett1.setAddr(rs.getString(3));
                joinEtt.ett1.setTel(rs.getString(4));
                joinEtt.ett1.setRestAddr(rs.getString(5));
                if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                    joinEtt.ett1.setDaepyoName(rs.getString(7));
                }
                vec.addElement(joinEtt);
            }
            if (vec.size() > 0) {
                ettlist = new UM_GOE_ConiC080b[vec.size()];
                vec.copyInto(ettlist);
            }
            else {
                ettlist = null;
            }
            return ettlist;
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInto() : " + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInto() : " + exc.toString());
            throw new Exception(exc.getMessage());
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
    
    public UM_GOE_ConiC080b[] getUpcheInto_001(final int pagenum, final int PAGEMAX, final String gubun, String saupNo, final String sangho, final String localCode, final String sdate, final String edate, final String license, final String mulpumCode, final String daepyo, String daepyojumin, final String Dchange) throws Exception {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector(1, 1);
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String sql = "";
        final int pstm_inx = 0;
        UM_GOE_ConiC080b[] ettlist = (UM_GOE_ConiC080b[])null;
        saupNo = ComStr.replace(saupNo, "-", "");
        daepyojumin = ComStr.replace(daepyojumin, "-", "");
        try {
            sql = " select  BIZ_REG_NO, BIZ_NM, ADDR,PHONE_NO,DETAIL_ADDR, N, NM, MOBILE, EMAIL ";
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + ", REPR_NM";
            }
            sql = String.valueOf(sql) + " from (";
            sql = String.valueOf(sql) + " select a.BIZ_REG_NO, a.BIZ_NM, a.ADDR,a.DETAIL_ADDR, a.PHONE_NO,  rownum N, c.NM, c.MOBILE, c.EMAIL ";
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + ", b.REPR_NM";
            }
            sql = String.valueOf(sql) + " from UM_SUPPLIER_ENTER_MAST a";
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + "\n , UM_REC_PUB_INSTITU_CERT b ";
            }
            sql = String.valueOf(sql) + " \n , UM_REC_BID_AGENT c";
            sql = String.valueOf(sql) + " where a.BIZ_REG_NO is not null ";
            sql = String.valueOf(sql) + "\n and a.BID_ATTEND_QUALIFY_YN is null ";
            sql = String.valueOf(sql) + "\n and a.TEST_OPTION_YN is null ";
            if (sangho.length() > 0) {
                sql = String.valueOf(sql) + " and lower(a.BIZ_NM) like lower('%" + sangho + "%') ";
            }
            if (gubun.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_CLS like '" + gubun + "'";
            }
            if (localCode.length() > 0) {
                sql = String.valueOf(sql) + " and a.AREA_CD = '" + localCode + "' ";
            }
            if (saupNo.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_REG_NO like '%" + saupNo + "%'";
            }
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_REG_NO = b.BIZ_REG_NO ";
                if (daepyo.length() > 0) {
                    sql = String.valueOf(sql) + " and lower(REPR_NM) like lower('%" + daepyo + "%') ";
                }
                if (daepyojumin.length() > 0) {
                    sql = String.valueOf(sql) + " and b.REPR_IDENT_NO like '%" + daepyojumin + "%'";
                }
            }
            sql = String.valueOf(sql) + " and a.BIZ_REG_NO = c.BIZ_REG_NO ";
            if (license.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_REG_NO in (select BIZ_REG_NO from um_enter_std_cls where a.BIZ_REG_NO=BIZ_REG_NO and STD_CLS_CD='" + license + "')";
            }
            if (sdate.length() > 0 && edate.length() > 0) {
                if (Dchange.equals("RE")) {
                    sql = String.valueOf(sql) + "\n and a.REG_DT between to_date('" + sdate + "','dd/MM/yyyy') and to_date('" + edate + "','dd/MM/yyyy')";
                }
                if (Dchange.equals("NE")) {
                    sql = String.valueOf(sql) + "\n and a.UPDATE_DT between to_date('" + sdate + "','dd/MM/yyyy') and to_date('" + edate + "','dd/MM/yyyy')";
                }
            }
            sql = String.valueOf(sql) + " and rownum <= (" + pagenum + " * " + PAGEMAX + ") ) sub where N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                final UM_GOE_ConiC080b joinEtt = new UM_GOE_ConiC080b();
                joinEtt.ett1.setSaupNo(rs.getString(1));
                joinEtt.ett1.setSangho(rs.getString(2));
                joinEtt.ett1.setAddr(rs.getString(3));
                joinEtt.ett1.setTel(rs.getString(4));
                joinEtt.ett1.setRestAddr(rs.getString(5));
                joinEtt.ett5.setName(rs.getString(7));
                joinEtt.ett5.setHandphone(rs.getString(8));
                joinEtt.ett5.setMail(rs.getString(9));
                if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                    joinEtt.ett1.setDaepyoName(rs.getString(10));
                }
                vec.addElement(joinEtt);
            }
            if (vec.size() > 0) {
                ettlist = new UM_GOE_ConiC080b[vec.size()];
                vec.copyInto(ettlist);
            }
            else {
                ettlist = null;
            }
            return ettlist;
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInto_001() : " + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInto_001() : " + exc.toString());
            throw new Exception(exc.getMessage());
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
    
    public UM_GOE_ConiC080b[] getUpcheInto3(final int pagenum, final int PAGEMAX, final String gubun, String saupNo, final String sangho, final String localCode, final String sdate, final String edate, final String[] license, final String mulpumCode, final String daepyo, String daepyojumin, final String Dchange, final String manuYN) throws Exception {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector(1, 1);
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String sql = "";
        final String sql2 = "";
        final int pstm_inx = 0;
        final int license_cnt = 0;
        UM_GOE_ConiC080b[] ettlist = (UM_GOE_ConiC080b[])null;
        saupNo = ComStr.replace(saupNo, "-", "");
        daepyojumin = ComStr.replace(daepyojumin, "-", "");
        try {
            sql = " select * from (select  BIZ_REG_NO, BIZ_NM,ADDR,PHONE_NO,DETAIL_ADDR, decode(nvl(PRODUCT_CLS, '03'),  '02', 'Manufacturing', '03', 'supply'), RECV_NO, rownum n";
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + ", CHRGR_MN";
            }
            sql = String.valueOf(sql) + " from (";
            sql = String.valueOf(sql) + " select a.BIZ_REG_NO, a.BIZ_NM, a.ADDR,a.DETAIL_ADDR, a.PHONE_NO, a.PRODUCT_CLS, a.RECV_NO";
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + ", b.CHRGR_MN";
            }
            sql = String.valueOf(sql) + " from UM_SUPPLIER_ENTER_MAST a";
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + "\n , UM_REC_PUB_INSTITU_CERT b ";
            }
            sql = String.valueOf(sql) + "\n where a.BIZ_REG_NO is not null ";
            sql = String.valueOf(sql) + "\n and a.BID_ATTEND_QUALIFY_YN is null ";
            sql = String.valueOf(sql) + "\n and (a.TEST_OPTION_YN is null or a.TEST_OPTION_YN ='N') ";
            if (sangho.length() > 0) {
                sql = String.valueOf(sql) + " and lower(BIZ_NM) like lower('%" + sangho + "%') ";
            }
            if (gubun.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_CLS like '" + gubun + "'";
            }
            if (localCode.length() > 0) {
                sql = String.valueOf(sql) + " and a.AREA_CD = '" + localCode + "'";
            }
            if (saupNo.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_REG_NO like '%" + saupNo + "%'";
            }
            if (manuYN.length() > 0) {
                sql = String.valueOf(sql) + " and a.PRODUCT_CLS = '" + manuYN + "'";
            }
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_REG_NO = b.BIZ_REG_NO ";
                if (daepyo.length() > 0) {
                    sql = String.valueOf(sql) + " and lower(b.CHRGR_MN) like lower('%" + daepyo + "%') ";
                }
                if (daepyojumin.length() > 0) {
                    sql = String.valueOf(sql) + " and b.CHRGR_IDENT_NO like '%" + daepyojumin + "%'";
                }
            }
            if (mulpumCode.length() > 0) {
                sql = String.valueOf(sql) + "and a.BIZ_REG_NO in (select BIZ_REG_NO from UM_SUPPLIER_ENTER_ITEMS where a.BIZ_REG_NO=BIZ_REG_NO and GOOD_CLS_NO like '" + mulpumCode + "%' )";
            }
            if (sdate.length() > 0 && edate.length() > 0) {
                if (Dchange.equals("RE")) {
                    sql = String.valueOf(sql) + "and a.REG_DT between to_date('" + sdate + "','dd/mm/yyyy') and to_date('" + edate + "','dd/mm/yyyy')";
                }
                if (Dchange.equals("NE")) {
                    sql = String.valueOf(sql) + "and a.UPDATE_DT between to_date('" + sdate + "','dd/mm/yyyy') and to_date('" + edate + "','dd/mm/yyyy')";
                }
            }
            sql = String.valueOf(sql) + "order by reg_dt DESC )) where n between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                final UM_GOE_ConiC080b joinEtt = new UM_GOE_ConiC080b();
                joinEtt.ett1.setSaupNo(rs.getString(1));
                joinEtt.ett1.setSangho(rs.getString(2));
                joinEtt.ett1.setAddr(rs.getString(3));
                joinEtt.ett1.setTel(rs.getString(4));
                joinEtt.ett1.setRestAddr(rs.getString(5));
                joinEtt.ett1.setMakeGubun(rs.getString(6));
                joinEtt.ett1.setRecvNo(rs.getString(7));
                if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                    joinEtt.ett1.setDaepyoName(rs.getString(8));
                }
                vec.addElement(joinEtt);
            }
            if (vec.size() > 0) {
                ettlist = new UM_GOE_ConiC080b[vec.size()];
                vec.copyInto(ettlist);
            }
            else {
                ettlist = null;
            }
            return ettlist;
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInto() : " + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInto() : " + exc.toString());
            throw new Exception(exc.getMessage());
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
    
    public UM_GOE_ConiC080b[] getUpcheInto2(final int pagenum, final int PAGEMAX, final String gubun, String saupNo, final String sangho, final String localCode, final String sdate, final String edate, final String license, final String mulpumCode, final String daepyo, String daepyojumin, final String Dchange) throws Exception {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector(1, 1);
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String sql = "";
        int pstm_inx = 0;
        UM_GOE_ConiC080b[] ettlist = (UM_GOE_ConiC080b[])null;
        saupNo = ComStr.replace(saupNo, "-", "");
        daepyojumin = ComStr.replace(daepyojumin, "-", "");
        try {
            sql = " select  BIZ_REG_NO, BIZ_NM, ADDR,PHONE_NO,DETAIL_ADDR, N from ( select a.BIZ_REG_NO, a.BIZ_NM, a.ADDR,a.DETAIL_ADDR, a.PHONE_NO, rownum N from UM_SUPPLIER_ENTER_MAST a where BIZ_REG_NO is not null ";
            sql = String.valueOf(sql) + "\n and a.TEST_OPTION_YN is null ";
            if (sangho.length() > 0) {
                sql = String.valueOf(sql) + " and BIZ_NM like ? || '%' ";
            }
            if (gubun.length() > 0) {
                sql = String.valueOf(sql) + " and BIZ_CLS like '" + gubun + "'";
            }
            if (localCode.length() > 0) {
                sql = String.valueOf(sql) + " and AREA_CD like '" + localCode + "%'";
            }
            if (saupNo.length() > 0) {
                sql = String.valueOf(sql) + " and BIZ_REG_NO like '" + saupNo + "%'";
            }
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + " and BIZ_REG_NO in ( select BIZ_REG_NO \t \t\t\t\t   \t   from UM_REPR  \t\t\t\t\t   \t   where a.BIZ_REG_NO=BIZ_REG_NO ";
                if (daepyo.length() > 0) {
                    sql = String.valueOf(sql) + " and REPR_NM like ? || '%' ";
                }
                if (daepyojumin.length() > 0) {
                    sql = String.valueOf(sql) + " and REPR_IDENT_NO like '" + daepyojumin + "%'";
                }
                sql = String.valueOf(sql) + " )";
            }
            if (license.length() > 0) {
                sql = String.valueOf(sql) + " and BIZ_REG_NO in (select BIZ_REG_NO from UM_LICENSE_FACTS where a.BIZ_REG_NO=BIZ_REG_NO and LICENSE_CD='" + license + "')";
            }
            if (mulpumCode.length() > 0) {
                sql = String.valueOf(sql) + "and BIZ_REG_NO in (select BIZ_REG_NO from UM_SUPPLIER_ENTER_ITEMS where a.BIZ_REG_NO=BIZ_REG_NO and GOOD_CLS_NO='" + mulpumCode + "')";
            }
            if (sdate.length() > 0 && edate.length() > 0) {
                if (Dchange.equals("RE")) {
                    sql = String.valueOf(sql) + "and REG_DT between to_date('" + sdate + "','yyyy/mm/dd') and to_date('" + edate + "','yyyy/mm/dd')";
                }
                if (Dchange.equals("NE")) {
                    sql = String.valueOf(sql) + "and UPDATE_DT between to_date('" + sdate + "','yyyy/mm/dd') and to_date('" + edate + "','yyyy/mm/dd')";
                }
            }
            sql = String.valueOf(sql) + " and rownum <= (" + pagenum + " * " + PAGEMAX + ") ) sub where N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            if (sangho.length() > 0) {
                ++pstm_inx;
                pstm.setString(pstm_inx, sangho);
            }
            if (daepyo.length() > 0) {
                ++pstm_inx;
                pstm.setString(pstm_inx, daepyo);
            }
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                final UM_GOE_ConiC080b joinEtt = new UM_GOE_ConiC080b();
                joinEtt.ett1.setSaupNo(rs.getString(1));
                joinEtt.ett1.setSangho(rs.getString(2));
                joinEtt.ett1.setAddr(rs.getString(3));
                joinEtt.ett1.setTel(rs.getString(4));
                joinEtt.ett1.setRestAddr(rs.getString(5));
                vec.addElement(joinEtt);
            }
            if (vec.size() > 0) {
                ettlist = new UM_GOE_ConiC080b[vec.size()];
                vec.copyInto(ettlist);
            }
            else {
                ettlist = null;
            }
            return ettlist;
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInto() : " + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInto() : " + exc.toString());
            throw new Exception(exc.getMessage());
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
    
    public int getUpcheInfoCount(final String gubun, final String saupNo, final String sangho, final String localCode, final String sdate, final String edate, final String license, final String mulpumCode, final String daepyo, final String daepyojumin, final String Dchange) throws Exception {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector(1, 1);
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final int pstm_inx = 0;
        int result = 0;
        try {
            String sql = "\n select  count(*) from UM_SUPPLIER_ENTER_MAST a";
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + "\n , UM_REC_PUB_INSTITU_CERT b ";
            }
            sql = String.valueOf(sql) + "\n where a.BIZ_REG_NO is not null ";
            sql = String.valueOf(sql) + "\n and a.BID_ATTEND_QUALIFY_YN is null ";
            sql = String.valueOf(sql) + "\n and (a.TEST_OPTION_YN is null OR a.TEST_OPTION_YN = 'N') ";
            if (sangho.length() > 0) {
                sql = String.valueOf(sql) + "\n and lower(a.BIZ_NM) like lower('%" + sangho + "%')";
            }
            if (gubun.length() > 0) {
                sql = String.valueOf(sql) + "\n and a.BIZ_CLS like '" + gubun + "'";
            }
            if (localCode.length() > 0) {
                sql = String.valueOf(sql) + "\n and a.AREA_CD = '" + localCode + "' ";
            }
            if (saupNo.length() > 0) {
                sql = String.valueOf(sql) + "\n and a.BIZ_REG_NO like '%" + saupNo + "%'";
            }
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + "\n and a.BIZ_REG_NO = b.BIZ_REG_NO ";
                if (daepyo.length() > 0) {
                    sql = String.valueOf(sql) + "\n and lower(b.REPR_NM) like lower('%" + daepyo + "%') ";
                }
                if (daepyojumin.length() > 0) {
                    sql = String.valueOf(sql) + "\n and b.REPR_IDENT_NO like '" + daepyojumin + "%'";
                }
            }
            if (license.length() > 0) {
                sql = String.valueOf(sql) + " and a.BIZ_REG_NO in (select BIZ_REG_NO from um_enter_std_cls where a.BIZ_REG_NO=BIZ_REG_NO and STD_CLS_CD='" + license + "')";
            }
            if (sdate.length() > 0 && edate.length() > 0) {
                if (Dchange.equals("RE")) {
                    sql = String.valueOf(sql) + "\n and a.REG_DT between to_date('" + sdate + "','dd/MM/yyyy') and to_date('" + edate + "','dd/MM/yyyy')";
                }
                if (Dchange.equals("NE")) {
                    sql = String.valueOf(sql) + "\n and a.UPDATE_DT between to_date('" + sdate + "','dd/MM/yyyy') and to_date('" + edate + "','dd/MM/yyyy')";
                }
            }
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            return result;
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInfoCount() : " + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInfoCount() : " + exc.toString());
            throw new Exception(exc.getMessage());
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
    
    public int getUpcheInfoCount3(final String gubun, String saupNo, final String sangho, final String localCode, final String sdate, final String edate, final String[] license, final String mulpumCode, final String daepyo, String daepyojumin, final String Dchange, final String manuYN) throws Exception {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector(1, 1);
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final int pstm_inx = 0;
        final int license_cnt = 0;
        int result = 0;
        final String sql1 = "";
        saupNo = ComStr.replace(saupNo, "-", "");
        daepyojumin = ComStr.replace(daepyojumin, "-", "");
        try {
            String sql2 = "\n select  count(*) from UM_SUPPLIER_ENTER_MAST a";
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql2 = String.valueOf(sql2) + "\n , UM_REC_PUB_INSTITU_CERT b ";
            }
            sql2 = String.valueOf(sql2) + "\n where a.BIZ_REG_NO is not null ";
            sql2 = String.valueOf(sql2) + "\n and a.BID_ATTEND_QUALIFY_YN is null ";
            sql2 = String.valueOf(sql2) + "\n and (a.TEST_OPTION_YN is null or a.TEST_OPTION_YN ='N') ";
            if (sangho.length() > 0) {
                sql2 = String.valueOf(sql2) + "\n and lower(BIZ_NM) like lower('%" + sangho + "%') ";
            }
            if (gubun.length() > 0) {
                sql2 = String.valueOf(sql2) + "\n and a.BIZ_CLS like '" + gubun + "'";
            }
            if (localCode.length() > 0) {
                sql2 = String.valueOf(sql2) + "\n and a.AREA_CD ='" + localCode + "'";
            }
            if (saupNo.length() > 0) {
                sql2 = String.valueOf(sql2) + "\n and a.BIZ_REG_NO like '%" + saupNo + "%'";
            }
            if (manuYN.length() > 0) {
                sql2 = String.valueOf(sql2) + "\n and a.PRODUCT_CLS = '" + manuYN + "'";
            }
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql2 = String.valueOf(sql2) + "\n and a.BIZ_REG_NO = b.BIZ_REG_NO ";
                if (daepyo.length() > 0) {
                    sql2 = String.valueOf(sql2) + "\n and lower(b.CHRGR_MN) like lower('%" + daepyo + "%') ";
                }
                if (daepyojumin.length() > 0) {
                    sql2 = String.valueOf(sql2) + "\n and b.CHRGR_IDENT_NO like '%" + daepyojumin + "%'";
                }
            }
            if (mulpumCode.length() > 0) {
                sql2 = String.valueOf(sql2) + "\n and a.BIZ_REG_NO in (select BIZ_REG_NO from UM_SUPPLIER_ENTER_ITEMS where a.BIZ_REG_NO=BIZ_REG_NO and GOOD_CLS_NO like '" + mulpumCode + "%' )";
            }
            if (sdate.length() > 0 && edate.length() > 0) {
                if (Dchange.equals("RE")) {
                    sql2 = String.valueOf(sql2) + "\n and a.REG_DT between to_date('" + sdate + "','dd/mm/yyyy') and to_date('" + edate + "','dd/mm/yyyy')";
                }
                if (Dchange.equals("NE")) {
                    sql2 = String.valueOf(sql2) + "\n and a.UPDATE_DT between to_date('" + sdate + "','dd/mm/yyyy') and to_date('" + edate + "','dd/mm/yyyy')";
                }
            }
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql2);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            return result;
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInfoCount() : " + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInfoCount() : " + exc.toString());
            throw new Exception(exc.getMessage());
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
    
    public int getUpcheInfoCount2(final String gubun, String saupNo, final String sangho, final String localCode, final String sdate, final String edate, final String license, final String mulpumCode, final String daepyo, String daepyojumin, final String Dchange) throws Exception {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector(1, 1);
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int pstm_inx = 0;
        int result = 0;
        saupNo = ComStr.replace(saupNo, "-", "");
        daepyojumin = ComStr.replace(daepyojumin, "-", "");
        try {
            String sql = "\n select  count(*) from UM_SUPPLIER_ENTER_MAST a\n where BIZ_REG_NO is not null ";
            sql = String.valueOf(sql) + "\n and a.TEST_OPTION_YN is null ";
            if (sangho.length() > 0) {
                sql = String.valueOf(sql) + "\n and BIZ_NM like ? || '%' ";
            }
            if (gubun.length() > 0) {
                sql = String.valueOf(sql) + "\n and BIZ_CLS like '" + gubun + "'";
            }
            if (localCode.length() > 0) {
                sql = String.valueOf(sql) + "\n and AREA_CD like '" + localCode + "%'";
            }
            if (saupNo.length() > 0) {
                sql = String.valueOf(sql) + "\n and BIZ_REG_NO like '" + saupNo + "%'";
            }
            if (daepyo.length() > 0 || daepyojumin.length() > 0) {
                sql = String.valueOf(sql) + "\n and BIZ_REG_NO in ( select BIZ_REG_NO \n\t \t\t\t\t   \t   from UM_REPR \n\t\t\t\t\t   \t   where a.BIZ_REG_NO=BIZ_REG_NO ";
                if (daepyo.length() > 0) {
                    sql = String.valueOf(sql) + "\n and REPR_NM like '" + daepyo + "%'";
                }
                if (daepyojumin.length() > 0) {
                    sql = String.valueOf(sql) + "\n and REPR_IDENT_NO like '" + daepyojumin + "%'";
                }
                sql = String.valueOf(sql) + "\n )";
            }
            if (license.length() > 0) {
                sql = String.valueOf(sql) + "\n and BIZ_REG_NO in (select BIZ_REG_NO from UM_LICENSE_FACTS where a.BIZ_REG_NO=BIZ_REG_NO and LICENSE_CD='" + license + "')";
            }
            if (mulpumCode.length() > 0) {
                sql = String.valueOf(sql) + "\n and BIZ_REG_NO in (select BIZ_REG_NO from UM_SUPPLIER_ENTER_ITEMS where a.BIZ_REG_NO=BIZ_REG_NO and GOOD_CLS_NO='" + mulpumCode + "')";
            }
            if (sdate.length() > 0 && edate.length() > 0) {
                if (Dchange.equals("RE")) {
                    sql = String.valueOf(sql) + "\n and REG_DT between to_date('" + sdate + "','yyyy/mm/dd') and to_date('" + edate + "','yyyy/mm/dd')";
                }
                if (Dchange.equals("NE")) {
                    sql = String.valueOf(sql) + "\n and UPDATE_DT between to_date('" + sdate + "','yyyy/mm/dd') and to_date('" + edate + "','yyyy/mm/dd')";
                }
            }
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            if (sangho.length() > 0) {
                ++pstm_inx;
                pstm.setString(1, sangho);
            }
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                result = rs.getInt(1);
            }
            return result;
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInfoCount2() : " + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInfoCount2() : " + exc.toString());
            throw new Exception(exc.getMessage());
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
    
    public UM_GOE_ConiC080b[] select_comlist(final int pagenum, final int PAGEMAX, final String gubun, String saupNo, final String sangho, final String localCode, final String sdate, final String edate, final String license, final String mulpumCode, final String daepyo, String daepyojumin, final String Dchange) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String sql = "";
        UM_GOE_ConiC080b[] ettlist = (UM_GOE_ConiC080b[])null;
        saupNo = ComStr.replace(saupNo, "-", "");
        daepyojumin = ComStr.replace(daepyojumin, "-", "");
        Label_1797: {
            if (license.equals("") && daepyo.equals("") && daepyojumin.equals("") && mulpumCode.equals("")) {
                sql = " SELECT BIZ_REG_NO, BIZ_NM, REPR_NM, ADDR, DETAIL_ADDR, PHONE_NO,  N  FROM (  SELECT A.BIZ_REG_NO, A.BIZ_NM, B.REPR_NM, A.ADDR, A.DETAIL_ADDR, A.PHONE_NO, ROWNUM N  FROM   UM_SUPPLIER_ENTER_MAST A, UM_REPR B  WHERE  A.BIZ_REG_NO=B.BIZ_REG_NO  AND b.MAST_REPR_YN='Y' ";
                if (gubun.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.BIZ_CLS like'" + gubun + "'";
                }
                if (saupNo.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.BIZ_REG_NO like '" + saupNo + "%'";
                }
                if (sangho.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.BIZ_NM like '%" + sangho + "%'";
                }
                if (localCode.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.AREA_CD like '" + localCode + "%'";
                }
                if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("RE")) {
                    sql = String.valueOf(sql) + " AND a.REG_DT>='" + sdate + "'";
                    sql = String.valueOf(sql) + " AND a.REG_DT<='" + edate + "'";
                }
                if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("NE")) {
                    sql = String.valueOf(sql) + " AND a.UPDATE_DT>='" + sdate + "'";
                    sql = String.valueOf(sql) + " AND a.UPDATE_DT<='" + edate + "'";
                }
                if (license.length() > 0) {
                    sql = String.valueOf(sql) + " AND c.LICENSE_CD='" + license + "'";
                }
                sql = String.valueOf(sql) + " AND ROWNUM <= (" + pagenum + " * " + PAGEMAX + "))  WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
                break Label_1797;
            }
            if (daepyo.equals("") && daepyojumin.equals("")) {
                sql = " SELECT BIZ_REG_NO, BIZ_NM, REPR_NM, ADDR, DETAIL_ADDR, PHONE_NO, N  FROM (  SELECT a.BIZ_REG_NO, a.BIZ_NM, b.REPR_NM, a.ADDR,  a.DETAIL_ADDR, a.PHONE_NO, a.BIZ_CLS, a.AREA_CD,  a.REG_DT,a.UPDATE_DT, c.LICENSE_CD, ROWNUM N \tFROM (SELECT BIZ_REG_NO, REPR_NM FROM UM_REPR WHERE MAST_REPR_YN='Y') b,  (SELECT BIZ_REG_NO, LICENSE_CD FROM UM_LICENSE_FACTS WHERE MAST_LICENSE_YN='Y') c,  (select BIZ_REG_NO, GOOD_CLS_NO from UM_SUPPLIER_ENTER_ITEMS where MAST_GOODS_YN='Y') d,  UM_SUPPLIER_ENTER_MAST a  WHERE a.BIZ_REG_NO=b.BIZ_REG_NO(+)  AND a.BIZ_REG_NO=c.BIZ_REG_NO(+)  AND a.BIZ_REG_NO=d.BIZ_REG_NO(+) ";
                if (gubun.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.BIZ_CLS like'" + gubun + "'";
                }
                if (saupNo.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.BIZ_REG_NO like '" + saupNo + "%'";
                }
                if (sangho.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.BIZ_NM like '%" + sangho + "%'";
                }
                if (localCode.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.AREA_CD like '" + localCode + "%'";
                }
                if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("RE")) {
                    sql = String.valueOf(sql) + " AND a.REG_DT>='" + sdate + "'";
                    sql = String.valueOf(sql) + " AND a.REG_DT<='" + edate + "'";
                }
                if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("NE")) {
                    sql = String.valueOf(sql) + " AND a.UPDATE_DT>='" + sdate + "'";
                    sql = String.valueOf(sql) + " AND a.UPDATE_DT<='" + edate + "'";
                }
                if (license.length() > 0) {
                    sql = String.valueOf(sql) + " AND c.LICENSE_CD='" + license + "'";
                }
                if (mulpumCode.length() > 0) {
                    sql = String.valueOf(sql) + " AND d.GOOD_CLS_NO='" + mulpumCode + "'";
                }
                sql = String.valueOf(sql) + " AND ROWNUM <= (" + pagenum + " * " + PAGEMAX + ") ) sub WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
                break Label_1797;
            }
            sql = " SELECT BIZ_REG_NO, BIZ_NM, REPR_NM, ADDR, DETAIL_ADDR, PHONE_NO, N  FROM (  SELECT /*+ ordered use_nl(b c d a) */ a.BIZ_REG_NO, a.BIZ_NM, b.REPR_NM, a.ADDR,  a.DETAIL_ADDR, a.PHONE_NO, a.BIZ_CLS, a.AREA_CD,  a.REG_DT,a.UPDATE_DT, c.LICENSE_CD, ROWNUM N \tFROM (select BIZ_REG_NO, REPR_NM from UM_REPR where BIZ_REG_NO in (select BIZ_REG_NO from UM_REPR ";
            if (daepyo.length() > 0 && 1 > daepyojumin.length()) {
                sql = String.valueOf(sql) + " where REPR_NM like '" + daepyo + "%' ";
            }
            if (daepyojumin.length() > 0 && 1 > daepyo.length()) {
                sql = String.valueOf(sql) + " where REPR_IDENT_NO like '" + daepyojumin + "%' ";
            }
            if (daepyojumin.length() > 0 && daepyo.length() > 0) {
                sql = String.valueOf(sql) + " where REPR_NM like '" + daepyo + "%' and REPR_IDENT_NO like '" + daepyojumin + "%' ";
            }
            sql = String.valueOf(sql) + ") and MAST_REPR_YN='Y') b,  (SELECT BIZ_REG_NO, LICENSE_CD FROM UM_LICENSE_FACTS WHERE MAST_LICENSE_YN='Y') c,  (select BIZ_REG_NO, GOOD_CLS_NO from UM_SUPPLIER_ENTER_ITEMS where MAST_GOODS_YN='Y') d,  UM_SUPPLIER_ENTER_MAST a  WHERE a.BIZ_REG_NO=b.BIZ_REG_NO  AND a.BIZ_REG_NO=c.BIZ_REG_NO(+)  AND a.BIZ_REG_NO=d.BIZ_REG_NO(+) ";
            if (gubun.length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_CLS like'" + gubun + "'";
            }
            if (saupNo.length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_REG_NO like '" + saupNo + "%'";
            }
            if (sangho.length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_NM like '%" + sangho + "%'";
            }
            if (localCode.length() > 0) {
                sql = String.valueOf(sql) + " AND a.AREA_CD like '" + localCode + "%'";
            }
            if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("RE")) {
                sql = String.valueOf(sql) + " AND a.REG_DT>='" + sdate + "'";
                sql = String.valueOf(sql) + " AND a.REG_DT<='" + edate + "'";
            }
            if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("NE")) {
                sql = String.valueOf(sql) + " AND a.UPDATE_DT>='" + sdate + "'";
                sql = String.valueOf(sql) + " AND a.UPDATE_DT<='" + edate + "'";
            }
            if (license.length() > 0) {
                sql = String.valueOf(sql) + " AND c.LICENSE_CD='" + license + "'";
            }
            if (mulpumCode.length() > 0) {
                sql = String.valueOf(sql) + " AND d.GOOD_CLS_NO='" + mulpumCode + "'";
            }
            sql = String.valueOf(sql) + " AND ROWNUM <= (" + pagenum + " * " + PAGEMAX + ") ) sub WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
            try {
                trx = new Trx(this);
                con = trx.getConnection();
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                pstm.clearParameters();
                while (rs.next()) {
                    final UM_GOE_ConiC080b joinEtt = new UM_GOE_ConiC080b();
                    joinEtt.ett1.setSaupNo(rs.getString(1));
                    joinEtt.ett1.setSangho(rs.getString(2));
                    joinEtt.ett1.setAddr(rs.getString(4));
                    joinEtt.ett1.setRestAddr(rs.getString(5));
                    joinEtt.ett1.setTel(rs.getString(6));
                    joinEtt.ett3.setCeoName(rs.getString(3));
                    joinEtt.ett7.setStateGubun(rs.getString(7));
                    vec.addElement(joinEtt);
                }
            }
            catch (SQLException sqle) {
                Log.debug("UM_GOB_ConiC010p.select_comlist() : sql : " + sql + "::: " + sqle.toString());
            }
            catch (Exception exc) {
                Log.debug("UM_GOB_ConiC010p.select_comlist() : sql : " + sql + "::: " + exc.toString());
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
            ettlist = new UM_GOE_ConiC080b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public int Max_count(final String gubun, String saupNo, final String sangho, final String localCode, final String sdate, final String edate, final String license, final String mulpumCode, final String daepyo, String daepyojumin, final String Dchange) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String sql = "";
        int count = 0;
        saupNo = ComStr.replace(saupNo, "-", "");
        daepyojumin = ComStr.replace(daepyojumin, "-", "");
        try {
            if (license.equals("") && daepyo.equals("") && daepyojumin.equals("") && mulpumCode.equals("")) {
                sql = " SELECT /*+ use_hash(a b) */ count(A.BIZ_REG_NO)  FROM   UM_SUPPLIER_ENTER_MAST A, UM_REPR B  WHERE  A.BIZ_REG_NO=B.BIZ_REG_NO  AND b.MAST_REPR_YN='Y' ";
            }
            else if (daepyo.equals("") && daepyojumin.equals("")) {
                sql = " SELECT count(a.BIZ_REG_NO) \tFROM (SELECT BIZ_REG_NO, REPR_NM FROM UM_REPR WHERE MAST_REPR_YN='Y') b,  (SELECT BIZ_REG_NO, LICENSE_CD FROM UM_LICENSE_FACTS WHERE MAST_LICENSE_YN='Y') c,  (select BIZ_REG_NO, GOOD_CLS_NO from UM_SUPPLIER_ENTER_ITEMS where MAST_GOODS_YN='Y') d,  UM_SUPPLIER_ENTER_MAST a  WHERE a.BIZ_REG_NO=b.BIZ_REG_NO(+)  AND a.BIZ_REG_NO=c.BIZ_REG_NO(+)  AND a.BIZ_REG_NO=d.BIZ_REG_NO(+) ";
            }
            else {
                sql = " SELECT count(a.BIZ_REG_NO) \tFROM (select BIZ_REG_NO, REPR_NM from UM_REPR where BIZ_REG_NO in (select BIZ_REG_NO from UM_REPR ";
                if (daepyo.length() > 0 && 1 > daepyojumin.length()) {
                    sql = String.valueOf(sql) + " where REPR_NM like '" + daepyo + "%' ";
                }
                if (daepyojumin.length() > 0 && 1 > daepyo.length()) {
                    sql = String.valueOf(sql) + " where REPR_IDENT_NO like '" + daepyojumin + "%' ";
                }
                if (daepyojumin.length() > 0 && daepyo.length() > 0) {
                    sql = String.valueOf(sql) + " where REPR_NM like '" + daepyo + "%' and REPR_IDENT_NO like '" + daepyojumin + "%' ";
                }
                sql = String.valueOf(sql) + ") and MAST_REPR_YN='Y') b,  (SELECT BIZ_REG_NO, LICENSE_CD FROM UM_LICENSE_FACTS WHERE MAST_LICENSE_YN='Y') c,  (select BIZ_REG_NO, GOOD_CLS_NO from UM_SUPPLIER_ENTER_ITEMS where MAST_GOODS_YN='Y') d,  UM_SUPPLIER_ENTER_MAST a  WHERE a.BIZ_REG_NO=b.BIZ_REG_NO  AND a.BIZ_REG_NO=c.BIZ_REG_NO(+)  AND a.BIZ_REG_NO=d.BIZ_REG_NO(+) ";
            }
            if (ComStr.checkNull(gubun, "").length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_CLS like'" + gubun + "'";
            }
            if (ComStr.checkNull(saupNo, "").length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_REG_NO like '" + saupNo + "%'";
            }
            if (ComStr.checkNull(sangho, "").length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_NM like '%" + sangho + "%'";
            }
            if (ComStr.checkNull(localCode, "").length() > 0) {
                sql = String.valueOf(sql) + " AND a.AREA_CD like '" + localCode + "%'";
            }
            if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("RE")) {
                sql = String.valueOf(sql) + " AND a.REG_DT>='" + sdate + "'";
                sql = String.valueOf(sql) + " AND a.REG_DT<='" + edate + "'";
            }
            if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("NE")) {
                sql = String.valueOf(sql) + " AND a.UPDATE_DT>='" + sdate + "'";
                sql = String.valueOf(sql) + " AND a.UPDATE_DT<='" + edate + "'";
            }
            if (ComStr.checkNull(license, "").length() > 0) {
                sql = String.valueOf(sql) + " AND c.LICENSE_CD='" + license + "'";
            }
            if (mulpumCode.length() > 0) {
                sql = String.valueOf(sql) + " AND d.GOOD_CLS_NO='" + mulpumCode + "'";
            }
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_ConiC010p.Max_count() : sql : " + sql + "::: " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_ConiC010p.Max_count() : sql : " + sql + "::: " + exc.toString());
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
    
    public UM_GOE_ConiC080b[] select_comlist(final int pagenum, final int PAGEMAX, final String gubun, String saupNo, final String sangho, final String localCode, final String sdate, final String edate, final String license, final String Dchange) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_GOE_ConiC080b[] ettlist = (UM_GOE_ConiC080b[])null;
        saupNo = ComStr.replace(saupNo, "-", "");
        String sql = "";
        Label_0995: {
            if (license.equals("")) {
                sql = " SELECT BIZ_REG_NO, BIZ_NM, REPR_NM, ADDR, DETAIL_ADDR, PHONE_NO,  N  FROM (             SELECT A.BIZ_REG_NO, A.BIZ_NM, B.REPR_NM, A.ADDR, A.DETAIL_ADDR, A.PHONE_NO, ROWNUM N   FROM   UM_SUPPLIER_ENTER_MAST A, UM_REPR B                                              WHERE  A.BIZ_REG_NO=B.BIZ_REG_NO                                                 AND b.MAST_REPR_YN='Y' ";
                if (gubun.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.BIZ_CLS like'" + gubun + "'";
                }
                if (saupNo.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.BIZ_REG_NO like '" + saupNo + "%'";
                }
                if (sangho.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.BIZ_NM like '%" + sangho + "%'";
                }
                if (localCode.length() > 0) {
                    sql = String.valueOf(sql) + " AND a.AREA_CD like '" + localCode + "%'";
                }
                if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("RE")) {
                    sql = String.valueOf(sql) + " AND a.REG_DT>='" + sdate + "'";
                    sql = String.valueOf(sql) + " AND a.REG_DT<='" + edate + "'";
                }
                if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("NE")) {
                    sql = String.valueOf(sql) + " AND a.UPDATE_DT>='" + sdate + "'";
                    sql = String.valueOf(sql) + " AND a.UPDATE_DT<='" + edate + "'";
                }
                if (license.length() > 0) {
                    sql = String.valueOf(sql) + " AND c.LICENSE_CD='" + license + "'";
                }
                sql = String.valueOf(sql) + " AND ROWNUM <= (" + pagenum + " * " + PAGEMAX + "))  WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
                break Label_0995;
            }
            sql = " SELECT BIZ_REG_NO, BIZ_NM, REPR_NM, ADDR, DETAIL_ADDR, PHONE_NO, N                    FROM (                                                                                                                                                                \t SELECT a.BIZ_REG_NO, a.BIZ_NM, b.REPR_NM, a.ADDR,                                          a.DETAIL_ADDR, a.PHONE_NO, a.BIZ_CLS, a.AREA_CD,                                       a.REG_DT,a.UPDATE_DT, c.LICENSE_CD, ROWNUM N                               \t FROM  (SELECT BIZ_REG_NO, REPR_NM FROM UM_REPR WHERE MAST_REPR_YN='Y') b, \t       (SELECT BIZ_REG_NO, LICENSE_CD FROM UM_LICENSE_FACTS WHERE MAST_LICENSE_YN='Y') c, \t\t    UM_SUPPLIER_ENTER_MAST a                                                          WHERE a.BIZ_REG_NO=b.BIZ_REG_NO(+)                                              AND   a.BIZ_REG_NO=c.BIZ_REG_NO(+)                                         ";
            if (gubun.length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_CLS like'" + gubun + "'";
            }
            if (saupNo.length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_REG_NO like '" + saupNo + "%'";
            }
            if (sangho.length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_NM like '%" + sangho + "%'";
            }
            if (localCode.length() > 0) {
                sql = String.valueOf(sql) + " AND a.AREA_CD like '" + localCode + "%'";
            }
            if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("RE")) {
                sql = String.valueOf(sql) + " AND a.REG_DT>='" + sdate + "'";
                sql = String.valueOf(sql) + " AND a.REG_DT<='" + edate + "'";
            }
            if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("NE")) {
                sql = String.valueOf(sql) + " AND a.UPDATE_DT>='" + sdate + "'";
                sql = String.valueOf(sql) + " AND a.UPDATE_DT<='" + edate + "'";
            }
            if (license.length() > 0) {
                sql = String.valueOf(sql) + " AND c.LICENSE_CD='" + license + "'";
            }
            sql = String.valueOf(sql) + " AND ROWNUM <= (" + pagenum + " * " + PAGEMAX + ") ) sub WHERE N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ")";
            try {
                trx = new Trx(this);
                con = trx.getConnection();
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                pstm.clearParameters();
                while (rs.next()) {
                    final UM_GOE_ConiC080b joinEtt = new UM_GOE_ConiC080b();
                    joinEtt.ett1.setSaupNo(rs.getString(1));
                    joinEtt.ett1.setSangho(rs.getString(2));
                    joinEtt.ett1.setAddr(rs.getString(4));
                    joinEtt.ett1.setRestAddr(rs.getString(5));
                    joinEtt.ett1.setTel(rs.getString(6));
                    joinEtt.ett3.setCeoName(rs.getString(3));
                    joinEtt.ett7.setStateGubun(rs.getString(7));
                    vec.addElement(joinEtt);
                }
            }
            catch (SQLException sqle) {
                Log.debug("UM_GOB_ConiC010p.select_comlist() : sql : " + sql + "::: " + sqle.toString());
            }
            catch (Exception exc) {
                Log.debug("UM_GOB_ConiC010p.select_comlist() : sql : " + sql + "::: " + exc.toString());
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
            ettlist = new UM_GOE_ConiC080b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public int Max_count(final String gubun, String saupNo, final String sangho, final String localCode, final String sdate, final String edate, final String license, final String Dchange) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String sql = "";
        int count = 0;
        saupNo = ComStr.replace(saupNo, "-", "");
        try {
            if (license.equals("")) {
                sql = " SELECT count(A.BIZ_REG_NO)             FROM   UM_SUPPLIER_ENTER_MAST A, UM_REPR B  WHERE  A.BIZ_REG_NO=B.BIZ_REG_NO     AND b.MAST_REPR_YN='Y' ";
            }
            else {
                sql = " SELECT ROWNUM, a.BIZ_REG_NO, a.BIZ_NM, b.REPR_NM, a.ADDR,                      a.DETAIL_ADDR, a.PHONE_NO,a.BIZ_CLS,a.AREA_CD, a.REG_DT,a.UPDATE_DT,          c.LICENSE_CD                                                         FROM  UM_SUPPLIER_ENTER_MAST a ,                                                    (select * from UM_REPR where MAST_REPR_YN='Y') b,              \t      (select * from UM_LICENSE_FACTS where MAST_LICENSE_YN='Y') c               \tWHERE a.BIZ_REG_NO=b.BIZ_REG_NO(+)                                  \tAND   a.BIZ_REG_NO=c.BIZ_REG_NO(+) ";
            }
            if (ComStr.checkNull(gubun, "").length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_CLS like'" + gubun + "'";
            }
            if (ComStr.checkNull(saupNo, "").length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_REG_NO like '" + saupNo + "%'";
            }
            if (ComStr.checkNull(sangho, "").length() > 0) {
                sql = String.valueOf(sql) + " AND a.BIZ_NM like '%" + sangho + "%'";
            }
            if (ComStr.checkNull(localCode, "").length() > 0) {
                sql = String.valueOf(sql) + " AND a.AREA_CD like '" + localCode + "%'";
            }
            if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("RE")) {
                sql = String.valueOf(sql) + " AND a.REG_DT>='" + sdate + "'";
                sql = String.valueOf(sql) + " AND a.REG_DT<='" + edate + "'";
            }
            if (sdate.length() > 0 && edate.length() > 0 && Dchange.equals("NE")) {
                sql = String.valueOf(sql) + " AND a.UPDATE_DT>='" + sdate + "'";
                sql = String.valueOf(sql) + " AND a.UPDATE_DT<='" + edate + "'";
            }
            if (ComStr.checkNull(license, "").length() > 0) {
                sql = String.valueOf(sql) + " AND c.LICENSE_CD='" + license + "'";
            }
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_ConiC010p.Max_count() : sql : " + sql + "::: " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_ConiC010p.Max_count() : sql : " + sql + "::: " + exc.toString());
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
}
