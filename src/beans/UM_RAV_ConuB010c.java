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
import entity.UM_RAE_ConuB010b;

public class UM_RAV_ConuB010c
{
    public UM_RAE_ConuB010b select_user(String saupNo, final String sangho) {
        Trx trx = null;
        Connection con = null;
        UM_RAE_ConuB010b ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        saupNo = ComStr.replace(saupNo, "-", "");
        try {
            final String sql = " select 사업자등록번호, 신청_변경구분, to_char(신청일자,'yyyy-mm-dd hh24:mi:ss') 신청일자, 상호명, 대표자명,         우편번호, 주소, 나머지주소, 전화번호, 홈페이지,         진행상태, 처리사유, XML문서위치, 전송번호,         승인지청, to_char(처리일자,'yyyy-mm-dd hh24:mi:ss') 처리일자,(SELECT 코드명 FROM SYN_공동코드 where 코드구분 ='GUJ' AND 코드=승인지청) 지청명 from 사용_전자문서상태    where  사업자등록번호 = ?  and    상호명         = ?  and    신청일자 = (select max(신청일자) from 사용_전자문서상태                     where  사업자등록번호='" + saupNo + "') ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            pstm.setString(2, sangho);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_RAE_ConuB010b();
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
                ett.setApprovalOrgCode(rs.getString(15));
                ett.setChuriDate(rs.getString(16));
                ett.setApprovalOrgName(rs.getString(17));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuB010c .select_user('" + saupNo + "','" + sangho + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuB010c .select_user('" + saupNo + "','" + sangho + "'):" + exc.toString());
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
        return ett;
    }
    
    public UM_RAE_ConuB010b select_ModiUser(String saupNo) {
        Trx trx = null;
        Connection con = null;
        UM_RAE_ConuB010b ett2 = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        saupNo = ComStr.replace(saupNo, "-", "");
        try {
            final String sql = " select 사업자등록번호, 신청_변경구분, to_char(신청일자,'yyyy-mm-dd hh24:mi:ss') 신청일자, 상호명, 대표자명, 우편번호, 주소, 나머지주소, 전화번호, 홈페이지, 진행상태, 처리사유, XML문서위치, 전송번호, 승인지청 , to_char(처리일자,'yyyy-mm-dd hh24:mi:ss') 처리일자,(SELECT 코드명 FROM SYN_공동코드 where 코드구분 ='GUJ' AND 코드=승인지청) 지청명 from 사용_전자문서상태    where 사업자등록번호='" + saupNo + "' ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett2 = new UM_RAE_ConuB010b();
                ett2.setSaupNo(rs.getString(1));
                ett2.setSinchungGubun(rs.getString(2));
                ett2.setSinchungDate(rs.getString(3));
                ett2.setSangho(rs.getString(4));
                ett2.setCeoName(rs.getString(5));
                ett2.setZipCode(rs.getString(6));
                ett2.setAddr(rs.getString(7));
                ett2.setRestAddr(rs.getString(8));
                ett2.setTel(rs.getString(9));
                ett2.setHomepage(rs.getString(10));
                ett2.setProcessState(rs.getString(11));
                ett2.setChuriReason(rs.getString(12));
                ett2.setXmlPosition(rs.getString(13));
                ett2.setTransNo(rs.getString(14));
                ett2.setApprovalOrgCode(rs.getString(15));
                ett2.setChuriDate(rs.getString(16));
                ett2.setApprovalOrgName(rs.getString(17));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuB010c .select_ModiUser('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuB010c .select_ModiUser('" + saupNo + "'):" + exc.toString());
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
        return ett2;
    }
    
    public int Max_count(String saupNo, final String sangho) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        saupNo = ComStr.replace(saupNo, "-", "");
        try {
            final String sql = " select count(*) from 사용_전자문서상태  where  사업자등록번호 = ?  and    상호명         = ?  and    신청일자 = (select max(신청일자) from 사용_전자문서상태                     where  사업자등록번호='" + saupNo + "') ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            pstm.setString(2, sangho);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuB010c .Max_count('" + saupNo + "','" + sangho + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuB010c .Max_count('" + saupNo + "','" + sangho + "'):" + exc.toString());
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
    
    public int Max_count1(String saupNo, final String sangho) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        saupNo = ComStr.replace(saupNo, "-", "");
        try {
            final String sql = " select count(*) from 사용_접수조달업체마스터 where  사업자등록번호 = ?  and    상호명         = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, saupNo);
            pstm.setString(2, sangho);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuB010c .Max_count1('" + saupNo + "','" + sangho + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuB010c .Max_count1('" + saupNo + "','" + sangho + "'):" + exc.toString());
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
    
    public UM_RAE_ConuB010b select_user(String saupNo) {
        Trx trx = null;
        Connection con = null;
        UM_RAE_ConuB010b ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        saupNo = ComStr.replace(saupNo, "-", "");
        try {
            final String sql = " select 사업자등록번호, 신청_변경구분, to_char(신청일자,'yyyy-mm-dd hh24:mi:ss') 신청일자, 상호명, 대표자명,         우편번호, 주소, 나머지주소, 전화번호, 홈페이지,         진행상태, 처리사유, XML문서위치, 전송번호,         승인지청, to_char(처리일자,'yyyy-mm-dd hh24:mi:ss') 처리일자,(SELECT 코드명 FROM SYN_공동코드 where 코드구분 ='GUJ' AND 코드=승인지청) 지청명 from 사용_전자문서상태    where  사업자등록번호='" + saupNo + "' " + " and    신청일자 = (select max(신청일자) from 사용_전자문서상태 " + "                    where  사업자등록번호='" + saupNo + "') ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_RAE_ConuB010b();
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
                ett.setApprovalOrgCode(rs.getString(15));
                ett.setChuriDate(rs.getString(16));
                ett.setApprovalOrgName(rs.getString(17));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAV_ConuB010c .select_user('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuB010c .select_user('" + saupNo + "'):" + exc.toString());
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
        return ett;
    }
    
    public int Max_count(String saupNo) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        saupNo = ComStr.replace(saupNo, "-", "");
        try {
            final String sql = " select count(*) from 사용_전자문서상태  where  사업자등록번호='" + saupNo + "' " + " and    신청일자 = (select max(신청일자) from 사용_전자문서상태 " + "                    where  사업자등록번호='" + saupNo + "') ";
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
            Log.debug("UM_RAV_ConuB010c .Max_count('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuB010c .Max_count('" + saupNo + "'):" + exc.toString());
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
    
    public int Max_count1(String saupNo) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        saupNo = ComStr.replace(saupNo, "-", "");
        try {
            final String sql = " select count(*) from 사용_접수조달업체마스터  where  사업자등록번호='" + saupNo + "' ";
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
            Log.debug("UM_RAV_ConuB010c .Max_count1('" + saupNo + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAV_ConuB010c .Max_count1('" + saupNo + "'):" + exc.toString());
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
