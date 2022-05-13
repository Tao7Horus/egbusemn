// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import entity.UM_RAE_ConuB010b;
import java.sql.PreparedStatement;
import common.Log;
import common.Trx;
import common.Trx2;

import java.sql.Connection;

public class UM_ADB_ConrA010p1en
{
    public void modify(final String bizRegNo, Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        Trx tr = null;
        conn = null;
        try {
            tr = new Trx(this);
            conn = tr.getConnection();
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_SUPPLIER_ENTER_MAST set HIDDEN_YN=? ");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            if (!"".equals(bizRegNo) && bizRegNo != null) {
                pstmt.setString(1, bizRegNo);
            }
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".BIZ_REG_NO(" + bizRegNo + ", " + ") : " + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public UM_RAE_ConuB010b[] getResultList(final int pageNum, final int pageMAX, final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province) {
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
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) = '" + saupNo.toLowerCase().trim() + "'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like '%" + sangho.toLowerCase().trim() + "%'";
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date('" + edate + "' 23:59:59, 'dd/MM/yyyy HH24:MI:SS')";
                }
                sql = String.valueOf(sql) + " order by t1.REG_DT desc";
                sql = String.valueOf(sql) + ") where N between " + start + " and " + end;
            }
            else {
                sql = "select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, RECV_NO , ROWNUM N, CD_NM2, ADDR from (  select t1.BIZ_REG_NO, t1.BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.RECV_NO, t4.CD_NM2 CD_NM2, t1.ADDR  from UM_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2, SYN_PUB_CODE t4 where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  and t1.AREA_CD = t4.CD and t4.CD_CLS = 'GU7' and t1.hidden_yn = 'N' ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) = '" + saupNo.toLowerCase().trim() + "'  ";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like '%" + sangho.toLowerCase().trim() + "%' ";
                }
                if (!"".equals(province) && province != null) {
                    sql = String.valueOf(sql) + " and t1.AREA_CD = '" + province + "' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
                sql = String.valueOf(sql) + " order by t1.REG_DT desc";
                sql = String.valueOf(sql) + ")) t3 where t3.N between " + start + " and " + end;
            }
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                final UM_RAE_ConuB010b ett = new UM_RAE_ConuB010b();
                ett.setSaupNo(rs.getString(1));
                ett.setSangho(rs.getString(2));
                ett.setSinchungDate(rs.getString(3));
                ett.setCeoName(rs.getString(4));
                ett.setApprovalOrgCode(rs.getString(5));
                ett.setZipCode(rs.getString(7));
                ett.setAddr(rs.getString(8));
                vec.addElement(ett);
            }
            Log.debug("SQL1: " + sql + "\n" + "Count :::" + vec.size());
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block Exception : " + exc.toString());
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
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (vec.size() > 0) {
            ettlist = new UM_RAE_ConuB010b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public UM_RAE_ConuB010b[] getResultList2(final int pageNum, final int pageMAX, final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province, final String draftData) {
        Connection con = null;
        Trx2 trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final Vector vec = new Vector();
        final Vector params = new Vector();
        UM_RAE_ConuB010b[] ettlist = (UM_RAE_ConuB010b[])null;
        final int start = (pageNum - 1) * pageMAX + 1;
        final int end = pageNum * pageMAX;
        try {
            String sql = "";
            if ("D".equals(status) || "C".equals(status)) {
                sql = "select * from (select t1.BIZ_REG_NO, t1.COM_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t1.REPR_NM, t1.PROCESS_ST, ROWNUM N from UM_EDOC_STATE t1 where PROCESS_ST in ('3', '4') ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like ? ";
                    params.add("%" + saupNo + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like ? ";
                    params.add("%" + sangho + "%");
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(?, 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
                sql = String.valueOf(sql) + " order by t1.REG_DT desc";
                sql = String.valueOf(sql) + ") where N between ? and ? ";
                params.add(new StringBuffer(String.valueOf(start)).toString());
                params.add(new StringBuffer(String.valueOf(end)).toString());
            }
            else {
                sql = "select X.*, DECODE(SIGN(USEMN.GET_PAYMENT_STATUS(BIZ_REG_NO)),-1,'Chưa nộp chi phí','Đã nộp chi phí') PM_DATE from( select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, RECV_NO , ROWNUM N, CD_NM2, ADDR, HIDDEN_YN, TEST_OPTION_YN from (  select DISTINCT t1.BIZ_REG_NO, t1.BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.RECV_NO, t4.CD_NM2 CD_NM2, t1.ADDR, t1.HIDDEN_YN, t1.TEST_OPTION_YN, t1.REG_DT as REG_DT2   from USEMN.UM_SUPPLIER_ENTER_MAST t1, USEMN.UM_REC_REPR t2, USEMN.UM_REC_PUB_INSTITU_CERT t3, SYN_PUB_CODE t4 where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y' and t3.BIZ_REG_NO = t1.BIZ_REG_NO and t3.REG_YN = 'Y' AND t3.CERT_CLS='S'  and t1.AREA_CD = t4.CD and t4.CD_CLS = 'GU7' ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like ? ";
                    params.add("%" + saupNo.toLowerCase().trim() + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like ? ";
                    params.add("%" + sangho.toLowerCase().trim() + "%");
                }
                if (!"".equals(province) && province != null) {
                    sql = String.valueOf(sql) + " and t1.AREA_CD = ? ";
                    params.add(province);
                }
                if ("Y".equals(draftData)) {
                    sql = String.valueOf(sql) + " and  t1.TEST_OPTION_YN ='Y' ";
                }
                else {
                    sql = String.valueOf(sql) + " and ( t1.TEST_OPTION_YN IS NULL OR t1.TEST_OPTION_YN ='N' ) ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
                sql = String.valueOf(sql) + " order by REG_DT2 desc ";
                sql = String.valueOf(sql) + ")) t3 where t3.N between ? and ? ";
                sql = String.valueOf(sql) + ") X";
                params.add(new StringBuffer(String.valueOf(start)).toString());
                params.add(new StringBuffer(String.valueOf(end)).toString());
            }
            trx = new Trx2(this, "usemn");
            Log.errors("SQL : " + sql);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); ++i) {
                pstm.setString(i + 1, params.get(i).toString());
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                 UM_RAE_ConuB010b ett = new UM_RAE_ConuB010b();
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
                    ett.setPaidStatus(rs.getString(11));
                }
                if (ett != null) {
                    vec.addElement(ett);
                }
            }
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
    
    public UM_RAE_ConuB010b[] getResultList(final int pageNum, final int pageMAX, final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province, final String draftData) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final Vector vec = new Vector();
        final Vector params = new Vector();
        UM_RAE_ConuB010b[] ettlist = (UM_RAE_ConuB010b[])null;
        final int start = (pageNum - 1) * pageMAX + 1;
        final int end = pageNum * pageMAX;
        try {
            String sql = "";
            if ("D".equals(status) || "C".equals(status)) {
                sql = "select * from (select t1.BIZ_REG_NO, t1.COM_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t1.REPR_NM, t1.PROCESS_ST, ROWNUM N from UM_EDOC_STATE t1 where PROCESS_ST in ('3', '4') ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like ? ";
                    params.add("%" + saupNo + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like ? ";
                    params.add("%" + sangho + "%");
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(?, 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
                sql = String.valueOf(sql) + " order by t1.REG_DT desc";
                sql = String.valueOf(sql) + ") where N between ? and ? ";
                params.add(new StringBuffer(String.valueOf(start)).toString());
                params.add(new StringBuffer(String.valueOf(end)).toString());
            }
            else {
                sql = "select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, RECV_NO , ROWNUM N, CD_NM2, ADDR, HIDDEN_YN, TEST_OPTION_YN from (  select t1.BIZ_REG_NO, t1.BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.RECV_NO, t4.CD_NM2 CD_NM2, t1.ADDR, t1.HIDDEN_YN, t1.TEST_OPTION_YN  from UM_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2, SYN_PUB_CODE t4 where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  and t1.AREA_CD = t4.CD and t4.CD_CLS = 'GU7' ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like ? ";
                    params.add("%" + saupNo.toLowerCase().trim() + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like ? ";
                    params.add("%" + sangho.toLowerCase().trim() + "%");
                }
                if (!"".equals(province) && province != null) {
                    sql = String.valueOf(sql) + " and t1.AREA_CD = ? ";
                    params.add(province);
                }
                if ("Y".equals(draftData)) {
                    sql = String.valueOf(sql) + " and  t1.TEST_OPTION_YN ='Y' ";
                }
                else {
                    sql = String.valueOf(sql) + " and ( t1.TEST_OPTION_YN IS NULL OR t1.TEST_OPTION_YN ='N' ) ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
                sql = String.valueOf(sql) + " order by t1.REG_DT desc";
                sql = String.valueOf(sql) + ")) t3 where t3.N between ? and ? ";
                params.add(new StringBuffer(String.valueOf(start)).toString());
                params.add(new StringBuffer(String.valueOf(end)).toString());
            }
            trx = new Trx(this, "usemn");
            Log.errors("SQL : " + sql);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); ++i) {
                pstm.setString(i + 1, params.get(i).toString());
            }
            rs = pstm.executeQuery();
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
                if (ett != null) {
                    vec.addElement(ett);
                }
            }
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
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (vec.size() > 0) {
            ettlist = new UM_RAE_ConuB010b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public UM_RAE_ConuB010b[] getResultList1(final int pageNum, final int pageMAX, final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province, final String draftData) {
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
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
                sql = String.valueOf(sql) + " order by t1.REG_DT desc";
                sql = String.valueOf(sql) + ") where N between " + start + " and " + end;
            }
            else {
                sql = "select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, RECV_NO , ROWNUM N, CD_NM2, ADDR, HIDDEN_YN, TEST_OPTION_YN from (  select t1.BIZ_REG_NO, t1.BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.RECV_NO, t4.CD_NM2 CD_NM2, t1.ADDR, t1.HIDDEN_YN, t1.TEST_OPTION_YN  from UM_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2, SYN_PUB_CODE t4 where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  and t1.AREA_CD = t4.CD and t4.CD_CLS = 'GU7' ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like '%" + saupNo.toLowerCase().trim() + "%'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like '%" + sangho.toLowerCase().trim() + "%'";
                }
                if (!"".equals(province) && province != null) {
                    sql = String.valueOf(sql) + " and t1.AREA_CD = '" + province + "' ";
                }
                if (!"".equals(draftData) && draftData != null) {
                    sql = String.valueOf(sql) + " and t1.hidden_yn = '" + draftData + "' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
                sql = String.valueOf(sql) + " order by t1.REG_DT desc";
                sql = String.valueOf(sql) + ")) t3 where t3.N between " + start + " and " + end;
            }
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            Log.debug("sql: " + sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
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
                if (ett != null) {
                    vec.addElement(ett);
                }
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block Exception : " + exc.toString());
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
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (vec.size() > 0) {
            ettlist = new UM_RAE_ConuB010b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public int max_count(final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province) {
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
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) = '" + saupNo.toLowerCase().trim() + "'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like '%" + sangho.toLowerCase().trim() + "%' ";
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
            }
            else {
                sql = "select count(*)  from UM_SUPPLIER_ENTER_MAST t1, SYN_PUB_CODE t2, UM_REC_REPR t3  where t1.BIZ_REG_NO = t3.BIZ_REG_NO and t3.REPR_ISMAIN = 'Y' and t1.AREA_CD = t2.CD and t2.CD_CLS = 'GU7' and t1.HIDDEN_YN = 'N' ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) = '" + saupNo.toLowerCase().trim() + "'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like '%" + sangho.toLowerCase().trim() + "%'";
                }
                if (!"".equals(province) && province != null) {
                    sql = String.valueOf(sql) + " and  t1.AREA_CD ='" + province + "' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
            }
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            Log.debug("SQL: " + sql + "\n" + "Count ::" + count);
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block Exception : " + exc.toString());
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
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    
    public int max_count1(final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province, final String draftData) {
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
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
            }
            else {
                sql = "select count(*)  from UM_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2 where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like '%" + saupNo.toLowerCase().trim() + "%'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like '%" + sangho.toLowerCase().trim() + "%'";
                }
                if (!"".equals(province) && province != null) {
                    sql = String.valueOf(sql) + " and  t1.AREA_CD ='" + province + "' ";
                }
                if (!"".equals(draftData) && draftData != null) {
                    sql = String.valueOf(sql) + " and t1.hidden_yn = '" + draftData + "' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
                }
            }
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            Log.debug("Số bản ghi: " + count);
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block Exception : " + exc.toString());
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
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    
    public int max_count3(final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province, final String draftData) {
        Connection con = null;
        Trx2 trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        final Vector params = new Vector();
        try {
            String sql = "";
            if ("D".equals(status) || "C".equals(status)) {
                sql = "select count(*) from UM_EDOC_STATE t1 where t1.PROCESS_ST in ('3', '4') ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like ? ";
                    params.add("%" + saupNo + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like ? ";
                    params.add("%" + sangho + "%");
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(?, 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
            }
            else {
                sql = "select count(*)  from UM_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2, SYN_PUB_CODE t4  where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  and t1.AREA_CD = t4.CD and t4.CD_CLS = 'GU7' ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like ? ";
                    params.add("%" + saupNo.toLowerCase().trim() + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like ? ";
                    params.add("%" + sangho.toLowerCase().trim() + "%");
                }
                if (!"".equals(province) && province != null) {
                    sql = String.valueOf(sql) + " and t1.AREA_CD = ? ";
                    params.add(province);
                }
                if ("Y".equals(draftData)) {
                    sql = String.valueOf(sql) + " and  t1.TEST_OPTION_YN ='Y' ";
                }
                else {
                    sql = String.valueOf(sql) + " and  (t1.TEST_OPTION_YN IS NULL OR t1.TEST_OPTION_YN ='N' ) ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
            }
            Log.errors("QuerySQL - Danh sách nhà thầu được phê duyệt :" + sql);
            trx = new Trx2(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); ++i) {
                pstm.setString(i + 1, params.get(i).toString());
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block SQLException : " + sqle.toString());
            Log.errors(this, sqle, "");
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block Exception : " + exc.toString());
            Log.errors(this, exc, "");
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
    
    public int max_count2(final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province, final String draftData) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        final Vector params = new Vector();
        try {
            String sql = "";
            if ("D".equals(status) || "C".equals(status)) {
                sql = "select count(*) from UM_EDOC_STATE t1 where t1.PROCESS_ST in ('3', '4') ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like ? ";
                    params.add("%" + saupNo + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like ? ";
                    params.add("%" + sangho + "%");
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(?, 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
            }
            else {
                sql = "select count(*)  from UM_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2, USEMN.UM_REC_PUB_INSTITU_CERT t3, SYN_PUB_CODE t4  where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  and t3.BIZ_REG_NO = t1.BIZ_REG_NO and t3.REG_YN = 'Y' AND t3.CERT_CLS='S'  and t1.AREA_CD = t4.CD and t4.CD_CLS = 'GU7' ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like ? ";
                    params.add("%" + saupNo.toLowerCase().trim() + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like ? ";
                    params.add("%" + sangho.toLowerCase().trim() + "%");
                }
                if (!"".equals(province) && province != null) {
                    sql = String.valueOf(sql) + " and t1.AREA_CD = ? ";
                    params.add(province);
                }
                if ("Y".equals(draftData)) {
                    sql = String.valueOf(sql) + " and  t1.TEST_OPTION_YN ='Y' ";
                }
                else {
                    sql = String.valueOf(sql) + " and  (t1.TEST_OPTION_YN IS NULL OR t1.TEST_OPTION_YN ='N' ) ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
            }
            Log.errors("QuerySQL - Danh sách nhà thầu được phê duyệt :" + sql);
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); ++i) {
                pstm.setString(i + 1, params.get(i).toString());
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block SQLException : " + sqle.toString());
            Log.errors(this, sqle, "");
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block Exception : " + exc.toString());
            Log.errors(this, exc, "");
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
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    
    public int max_count(final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province, final String draftData) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        final Vector params = new Vector();
        try {
            String sql = "";
            if ("D".equals(status) || "C".equals(status)) {
                sql = "select count(*) from UM_EDOC_STATE t1 where t1.PROCESS_ST in ('3', '4') ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like ? ";
                    params.add("%" + saupNo + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like ? ";
                    params.add("%" + sangho + "%");
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(?, 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
            }
            else {
                sql = "select count(*)  from UM_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2, SYN_PUB_CODE t4  where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  and t1.AREA_CD = t4.CD and t4.CD_CLS = 'GU7' ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like ? ";
                    params.add("%" + saupNo.toLowerCase().trim() + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like ? ";
                    params.add("%" + sangho.toLowerCase().trim() + "%");
                }
                if (!"".equals(province) && province != null) {
                    sql = String.valueOf(sql) + " and t1.AREA_CD = ? ";
                    params.add(province);
                }
                if ("Y".equals(draftData)) {
                    sql = String.valueOf(sql) + " and  t1.TEST_OPTION_YN ='Y' ";
                }
                else {
                    sql = String.valueOf(sql) + " and  (t1.TEST_OPTION_YN IS NULL OR t1.TEST_OPTION_YN ='N' ) ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
            }
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); ++i) {
                pstm.setString(i + 1, params.get(i).toString());
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block SQLException : " + sqle.toString());
            Log.errors(this, sqle, "");
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block Exception : " + exc.toString());
            Log.errors(this, exc, "");
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
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    
    public int max_count_CTS(final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            String sql = "";
            sql = "select count(*)  from UM_SUPPLIER_ENTER_MAST t1, SYN_PUB_CODE t2, UM_REC_REPR t3, UM_USER D, UM_CERT_INFO E  where t1.BIZ_REG_NO = t3.BIZ_REG_NO and t3.REPR_ISMAIN = 'Y' and  t1.AREA_CD = t2.CD and t2.CD_CLS = 'GU7' and t1.HIDDEN_YN = 'N'  and\t\t\tt1.BIZ_REG_NO = D.MAST_CD and D.USER_CLS = 'c' and\t\t\tD.USER_ID = E.USER_ID";
            if (saupNo != null && saupNo.length() != 0) {
                sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) = '" + saupNo.toLowerCase().trim() + "'";
            }
            if (sangho != null && sangho.length() != 0) {
                sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like '%" + sangho.toLowerCase().trim() + "%'";
            }
            if (!"".equals(province) && province != null) {
                sql = String.valueOf(sql) + " and  t1.AREA_CD ='" + province + "' ";
            }
            if (sdate != null && sdate.length() != 0) {
                sql = String.valueOf(sql) + " and E.AVAIL_PERIOD_END_DT >= to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
            }
            if (edate != null && edate.length() != 0) {
                sql = String.valueOf(sql) + " and E.AVAIL_PERIOD_END_DT <= to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
            }
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            Log.debug("SQL: " + sql + "\n" + "Count ::" + count);
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block Exception : " + exc.toString());
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
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    
    public UM_RAE_ConuB010b[] getResultList_CTS(final int pageNum, final int pageMAX, final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province) {
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
            sql = "select * from(  select BIZ_REG_NO, BIZ_NM , AVAIL_PERIOD_END_DT, CHRGR_NM, RECV_NO , ROWNUM N, CD_NM2, ADDR, CHRGR_MOBILE from (  select t1.BIZ_REG_NO, t1.BIZ_NM, to_char(E.AVAIL_PERIOD_END_DT, 'DD-MM-YYYY HH24:MI:SS') AVAIL_PERIOD_END_DT, D.CHRGR_NM, t1.RECV_NO, t4.CD_NM2 CD_NM2, t1.ADDR, D.CHRGR_MOBILE from UM_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2, SYN_PUB_CODE t4, UM_USER D, UM_CERT_INFO E  where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  and t1.AREA_CD = t4.CD and t4.CD_CLS = 'GU7' and t1.hidden_yn = 'N'  AND\t\t\tt1.BIZ_REG_NO = D.MAST_CD AND D.USER_CLS = 'c' AND\t\t\tD.USER_ID = E.USER_ID";
            if (saupNo != null && saupNo.length() != 0) {
                sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) = '" + saupNo.toLowerCase().trim() + "'  ";
            }
            if (sangho != null && sangho.length() != 0) {
                sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like '%" + sangho.toLowerCase().trim() + "%' ";
            }
            if (!"".equals(province) && province != null) {
                sql = String.valueOf(sql) + " and t1.AREA_CD = '" + province + "' ";
            }
            if (sdate != null && sdate.length() != 0) {
                sql = String.valueOf(sql) + " and E.AVAIL_PERIOD_END_DT >= to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
            }
            if (edate != null && edate.length() != 0) {
                sql = String.valueOf(sql) + " and E.AVAIL_PERIOD_END_DT <= to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
            }
            sql = String.valueOf(sql) + " order by E.AVAIL_PERIOD_END_DT ASC";
            sql = String.valueOf(sql) + ")) t3 where t3.N between " + start + " and " + end;
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                final UM_RAE_ConuB010b ett = new UM_RAE_ConuB010b();
                ett.setSaupNo(rs.getString(1));
                ett.setSangho(rs.getString(2));
                ett.setSinchungDate(rs.getString(3));
                ett.setCeoName(rs.getString(4));
                ett.setApprovalOrgCode(rs.getString(5));
                ett.setZipCode(rs.getString(7));
                ett.setAddr(rs.getString(8));
                ett.setTel(rs.getString(9));
                vec.addElement(ett);
            }
            Log.debug("SQL1: " + sql + "\n" + "Count :::" + vec.size());
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block Exception : " + exc.toString());
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
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (vec.size() > 0) {
            ettlist = new UM_RAE_ConuB010b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
    
    public int max_count3_en(final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province, final String draftData) {
        Connection con = null;
        Trx2 trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        final Vector params = new Vector();
        try {
            String sql = "";
            if ("D".equals(status) || "C".equals(status)) {
                sql = "select count(*) from UM_EDOC_STATE t1 where t1.PROCESS_ST in ('3', '4') ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like ? ";
                    params.add("%NN" + saupNo + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like ? ";
                    params.add("%" + sangho + "%");
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(?, 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
            }
            else {
                sql = "select count(*)  from UM_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2  where t1.BIZ_REG_NO = t2.BIZ_REG_NO AND t1.BIZ_REG_NO like 'NN%' and t2.REPR_ISMAIN = 'Y' ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like ? ";
                    params.add("%" + saupNo.toLowerCase().trim() + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like ? ";
                    params.add("%" + sangho.toLowerCase().trim() + "%");
                }
                if (!"".equals(province) && province != null) {
                    sql = String.valueOf(sql) + " and t1.AREA_CD = ? ";
                    params.add(province);
                }
//                if ("Y".equals(draftData)) {
//                    sql = String.valueOf(sql) + " and  t1.TEST_OPTION_YN ='Y' ";
//                }
//                else {
                    sql = String.valueOf(sql) + " and  (t1.TEST_OPTION_YN IS NULL OR t1.TEST_OPTION_YN ='N' ) ";
//                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
            }
            Log.errors("QuerySQL - Danh sách nhà thầu được phê duyệt :" + sql);
            trx = new Trx2(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); ++i) {
                pstm.setString(i + 1, params.get(i).toString());
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block SQLException : " + sqle.toString());
            Log.errors(this, sqle, "");
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p1en.Max_count block Exception : " + exc.toString());
            Log.errors(this, exc, "");
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
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    
    
    public UM_RAE_ConuB010b[] getResultList2_en(final int pageNum, final int pageMAX, final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String province, final String draftData) {
        Connection con = null;
        Trx2 trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final Vector vec = new Vector();
        final Vector params = new Vector();
        UM_RAE_ConuB010b[] ettlist = (UM_RAE_ConuB010b[])null;
        final int start = (pageNum - 1) * pageMAX + 1;
        final int end = pageNum * pageMAX;
        try {
            String sql = "";
            if ("D".equals(status) || "C".equals(status)) {
                sql = "select * from (select t1.BIZ_REG_NO, t1.COM_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t1.REPR_NM, t1.PROCESS_ST, ROWNUM N from UM_EDOC_STATE t1 where PROCESS_ST in ('3', '4') ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like ? ";
                    params.add("%NN" + saupNo + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like ? ";
                    params.add("%" + sangho + "%");
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(?, 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
                sql = String.valueOf(sql) + " order by t1.REG_DT desc";
                sql = String.valueOf(sql) + ") where N between ? and ? ";
                params.add(new StringBuffer(String.valueOf(start)).toString());
                params.add(new StringBuffer(String.valueOf(end)).toString());
            }
            else {
                sql = "select X.*, DECODE(SIGN(USEMN.GET_PAYMENT_STATUS(BIZ_REG_NO)),-1,'Unpaid','Paid') PM_DATE from( select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, RECV_NO , ROWNUM N, CD_NM2, ADDR, HIDDEN_YN, TEST_OPTION_YN from (  select DISTINCT t1.BIZ_REG_NO, t1.BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.RECV_NO, '' CD_NM2, t1.ADDR, t1.HIDDEN_YN, t1.TEST_OPTION_YN, t1.REG_DT as REG_DT2   from USEMN.UM_SUPPLIER_ENTER_MAST t1, USEMN.UM_REC_REPR t2, USEMN.UM_REC_PUB_INSTITU_CERT t3 where t1.BIZ_REG_NO = t2.BIZ_REG_NO AND t1.BIZ_REG_NO like 'NN%' and t2.REPR_ISMAIN = 'Y' and t3.BIZ_REG_NO = t1.BIZ_REG_NO and t3.REG_YN = 'Y' AND t3.CERT_CLS='S'   ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_REG_NO)) like ? ";
                    params.add("%" + saupNo.toLowerCase().trim() + "%");
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and lower(trim(t1.BIZ_NM)) like ? ";
                    params.add("%" + sangho.toLowerCase().trim() + "%");
                }
                if (!"".equals(province) && province != null) {
                    sql = String.valueOf(sql) + " and t1.AREA_CD = ? ";
                    params.add(province);
                }
//                if ("Y".equals(draftData)) {
//                    sql = String.valueOf(sql) + " and  t1.TEST_OPTION_YN ='Y' ";
//                }
//                else {
                    sql = String.valueOf(sql) + " and ( t1.TEST_OPTION_YN IS NULL OR t1.TEST_OPTION_YN ='N' ) ";
//                }
                if (sdate != null && sdate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT >= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(sdate) + " 00:00:00");
                }
                if (edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT <= to_date(? , 'dd/MM/yyyy HH24:MI:SS')";
                    params.add(String.valueOf(edate) + " 23:59:59");
                }
                sql = String.valueOf(sql) + " order by REG_DT2 desc ";
                sql = String.valueOf(sql) + ")) t3 where t3.N between ? and ? ";
                sql = String.valueOf(sql) + ") X";
                params.add(new StringBuffer(String.valueOf(start)).toString());
                params.add(new StringBuffer(String.valueOf(end)).toString());
            }
            trx = new Trx2(this, "usemn");
            Log.errors("SQL : " + sql);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            for (int i = 0; i < params.size(); ++i) {
                pstm.setString(i + 1, params.get(i).toString());
            }
            rs = pstm.executeQuery();
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
                    ett.setPaidStatus(rs.getString(11));
                }
                if (ett != null) {
                    vec.addElement(ett);
                }
            }
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
}
