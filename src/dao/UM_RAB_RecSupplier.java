// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import common.Trx;
import common.CommDbQuery;
import common.OneRowEntity;
import common.Log;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class UM_RAB_RecSupplier
{
    public String getChodalReceptionNumber(final Connection con) throws Exception {
        String result = "";
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        final String sql = "SELECT to_char(sysdate,'yyyymmddHHMMSS')||lpad((substr(max(RECV_NO), 5, 10)+1), 2, '0') FROM UM_REC_SUPPLIER_ENTER_MAST";
        try {
            pstmt = con.prepareStatement(sql);
            rest = pstmt.executeQuery();
            if (!rest.next()) {
                throw new Exception("Không tồn tại mã số tiếp nhận");
            }
            result = rest.getString(1);
        }
        finally {
            try {
                if (rest != null) {
                    rest.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (rest != null) {
                rest.close();
            }
        }
        catch (Exception ex3) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex4) {}
        return result;
    }
    
    public int maxCount(final String bizRegNo, final Connection conn) {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        final int count = 0;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" select count (*) from UM_REC_SUPPLIER_ENTER_MAST ");
            sql.append(" where BIZ_REG_NO = ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            final ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + bizRegNo + ") : " + e.toString());
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
        return count;
    }
    
    public int maxCount(final String bizRegNo, final String recvNo, final Connection conn) {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        final int count = 0;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" select count (*) from UM_REC_SUPPLIER_ENTER_MAST ");
            sql.append(" where BIZ_REG_NO = ? and RECV_NO = ?");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, recvNo);
            final ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + bizRegNo + ") : " + e.toString());
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
        return count;
    }
    
    public OneRowEntity selectDetail(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, to_char(COMMENCEMENT_DT, 'dd/MM/yyyy') COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, RECV_NO, to_char(REG_DT, 'dd/mm/yyyy') REG_DT, UPDATE_DT, PERMIT_BRANCH , DOC_NO, to_char(DOC_CREAT_DT, 'dd/mm/yyyy') DOC_CREAT_DT, REG_YN");
            sql.append(" from UM_REC_SUPPLIER_ENTER_MAST t1 ");
            sql.append(" where t1.BIZ_REG_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { BizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + BizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    //thay đổi format của REG_DT về dd/mm/yyyy HH:MI:SS dùng khi update edoc state ở mục phê duyệt nhiều nhà thầu 1 lúc
    public OneRowEntity selectDetail2(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, to_char(COMMENCEMENT_DT, 'dd/MM/yyyy') COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, RECV_NO, to_char(REG_DT, 'dd/mm/yyyy HH:MI:SS') REG_DT, UPDATE_DT, PERMIT_BRANCH , DOC_NO, to_char(DOC_CREAT_DT, 'dd/mm/yyyy') DOC_CREAT_DT, REG_YN");
            sql.append(" from UM_REC_SUPPLIER_ENTER_MAST t1 ");
            sql.append(" where t1.BIZ_REG_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { BizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + BizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public OneRowEntity getDetail(final String BizRegNo, final String recvCode, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, to_char(COMMENCEMENT_DT, 'dd/MM/yyyy') COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, RECV_NO, to_char(REG_DT, 'dd/mm/yyyy') REG_DT, UPDATE_DT, PERMIT_BRANCH , DOC_NO, to_char(DOC_CREAT_DT, 'dd/mm/yyyy') DOC_CREAT_DT, REG_YN");
            sql.append(" from UM_REC_SUPPLIER_ENTER_MAST t1 ");
            sql.append(" where t1.BIZ_REG_NO = ? and t1.RECV_NO = ?");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { BizRegNo, recvCode }, conn);
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public OneRowEntity getDetail2(final String BizRegNo, final String recvCode, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT , ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, RECV_NO, to_char(REG_DT, 'dd/mm/yyyy HH:MI:SS') REG_DT, UPDATE_DT, PERMIT_BRANCH , DOC_NO, DOC_CREAT_DT, REG_YN");
            sql.append(" from UM_REC_SUPPLIER_ENTER_MAST t1 ");
            sql.append(" where t1.BIZ_REG_NO = ? and t1.RECV_NO = ?");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { BizRegNo, recvCode }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + BizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public OneRowEntity selectDetail(final String BizRegNo, final String recvCode, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select t1.BIZ_REG_NO, t1.BIZ_NM, t1.BIZ_EN_NM, t1.COMMENCEMENT_DT, t1.ADDR, t1.PHONE_NO, t1.EMPLOYEE_COUNT, t1.FAX, t1.NATIONALITY, t1.AREA_CD, t1.WEBSITE, t1.CAPITAL, t1.BIZ_CLS, t1.BIZ_CLS1, t1.RECV_NO, t1.REG_YN, t1.REJECTED_RSON, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t1.UPDATE_DT, t1.DOC_NO, t1.DOC_CREAT_DT, t2.APPROVE_REQ_CD");
            sql.append(" from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_PUB_INSTITU_CERT t2");
            sql.append(" where t1.BIZ_REG_NO = ? ");
            sql.append(" and t1.RECV_NO = ? ");
            sql.append(" and t2.BIZ_REG_NO = '" + BizRegNo + "' ");
            sql.append(" and t2.RECV_NO = '" + recvCode + "'");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { BizRegNo, recvCode }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + BizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public void delete(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_REC_SUPPLIER_ENTER_MAST ");
            sql.append(" where BIZ_REG_NO=? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, BizRegNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Xóa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + BizRegNo + ") : " + e.toString());
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
    
    public void save(final String bizRegNo, final String bizName, final String bizEnName, final String commDate, final String addr, final String phoneNo, final String employeeCount, final String fax, final String nationality, final String area, final String website, final String capital, final String bizCls, final String bizCls1, final String recvNo, final String perBranch, final String docNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_REC_SUPPLIER_ENTER_MAST (BIZ_REG_NO, BIZ_NM, BIZ_EN_NM, COMMENCEMENT_DT, ADDR, PHONE_NO, EMPLOYEE_COUNT, FAX, NATIONALITY, AREA_CD, WEBSITE, CAPITAL, BIZ_CLS, BIZ_CLS1, RECV_NO, REG_DT, PERMIT_BRANCH, DOC_NO, DOC_CREAT_DT, REG_YN) ");
            sql.append(" values (?, ?, ?, to_date(?,'dd/mm/yyyy'), ?, ?, to_number(?), ?, ?, ?, ?, to_number(?), ?, ?, ?, SYSDATE, ?, ?, SYSDATE, 'N')");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, bizName);
            pstmt.setString(3, bizEnName);
            pstmt.setString(4, commDate);
            pstmt.setString(5, addr);
            pstmt.setString(6, phoneNo);
            pstmt.setString(7, employeeCount);
            pstmt.setString(8, fax);
            pstmt.setString(9, nationality);
            pstmt.setString(10, area);
            pstmt.setString(11, website);
            pstmt.setString(12, capital);
            pstmt.setString(13, bizCls);
            pstmt.setString(14, bizCls1);
            pstmt.setString(15, recvNo);
            pstmt.setString(16, perBranch);
            pstmt.setString(17, docNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + bizRegNo + ", " + bizName + ", " + commDate + ") : " + e.toString());
            Log.errors(this, e, "");
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
    
    public void modify(final String bizRegNo, final String bizName, final String bizEnName, final String commDate, final String addr, final String phoneNo, final String employeeCount, final String fax, final String nationality, final String area, final String website, final String capital, final String bizCls, final String bizCls1, final String recvNo, final String perBranch, final String docNo, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            final String sql = "update UM_REC_SUPPLIER_ENTER_MAST set BIZ_NM=?, BIZ_EN_NM=?, COMMENCEMENT_DT=to_date(?, 'dd/mm/yyyy'), ADDR=?, PHONE_NO=?, EMPLOYEE_COUNT=to_number(?), FAX=?, NATIONALITY=?, AREA_CD=?, WEBSITE=?, CAPITAL=?, BIZ_CLS=?, BIZ_CLS1=?, RECV_NO=?, PERMIT_BRANCH=?, DOC_NO=?, DOC_CREAT_DT=SYSDATE, REG_YN='Y' where BIZ_REG_NO=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bizName);
            pstmt.setString(2, bizEnName);
            pstmt.setString(3, commDate);
            pstmt.setString(4, addr);
            pstmt.setString(5, phoneNo);
            pstmt.setString(6, employeeCount);
            pstmt.setString(7, fax);
            pstmt.setString(8, nationality);
            pstmt.setString(9, area);
            pstmt.setString(10, website);
            pstmt.setString(11, capital);
            pstmt.setString(12, bizCls);
            pstmt.setString(13, bizCls1);
            pstmt.setString(14, recvNo);
            pstmt.setString(15, perBranch);
            pstmt.setString(16, docNo);
            pstmt.setString(17, bizRegNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRegNo + ", " + bizName + ", " + bizEnName + ", " + commDate + ", " + ") : " + e.toString());
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
    
    public void modifyQuick(final String bizRegNo, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            final String sql = "update UM_REC_SUPPLIER_ENTER_MAST set DOC_CREAT_DT=SYSDATE, REG_YN='Y' where BIZ_REG_NO=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bizRegNo);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa thông tin đăng ký chung bị lỗi]");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.debug(String.valueOf(this.getClass().getName()) + ".modifyQuick(" + bizRegNo  + ") : " + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {
                	ex.printStackTrace();
                    Log.debug(String.valueOf(this.getClass().getName()) + ".modifyQuick(" + bizRegNo  + ") : " + ex.toString());
                    throw ex;
                }
            }
        }
    }
    
    public void updateEDocState(final String bizRegNo, final String regDt, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            final String sql = "update UM_EDOC_STATE set PROCESS_ST = '1' where BIZ_REG_NO = ? and to_char(REG_DT, 'dd/mm/yyyy HH:MI:SS') = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, bizRegNo);
            pstmt.setString(2, regDt);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa thông tin đơn đăng ký bị lỗi]");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.debug(String.valueOf(this.getClass().getName()) + ".updateEDocState(" + bizRegNo  + ", "+ regDt + ") : " + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {
                	ex.printStackTrace();
                    Log.debug(String.valueOf(this.getClass().getName()) + ".updateEDocState(" + bizRegNo  + ", "+ regDt + ") : " + ex.toString());
                    throw ex;
                }
            }
        }
    }
    
    public String[] countAll(final String sdate, final String edate, final String cls) {
        Trx trx = null;
        Connection conn = null;
        final String[] result = new String[5];
        final ResultSet rest = null;
        PreparedStatement pstmt = null;
        final String existType = "0";
        final int count = 0;
        int hanghoa = 0;
        int xaylap = 0;
        int tuvan = 0;
        try {
            trx = new Trx(this, "USEMN");
            conn = trx.getConnection();
            StringBuffer sql = new StringBuffer();
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" select count (*) from UM_REC_SUPPLIER_ENTER_MAST ");
            if ("1".equals(cls)) {
                sql.append(" where REG_YN = 'N' ");
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql.append(" and REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')");
                }
            }
            else {
                sql.append(" where REG_YN is null ");
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql.append(" and REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')");
                }
            }
            pstmt = conn.prepareStatement(sql.toString());
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                result[0] = new StringBuffer().append(resultSet.getInt(1)).toString();
            }
            sql = new StringBuffer();
            sql.append(" select count (*) from UM_REC_SUPPLIER_ENTER_MAST ");
            if ("1".equals(cls)) {
                sql.append(" where REG_YN = 'N' ");
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql.append(" and REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')");
                }
            }
            else {
                sql.append(" where REG_YN is null ");
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql.append(" and REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')");
                }
            }
            sql.append(" and BIZ_CLS like '%1%' ");
            pstmt = conn.prepareStatement(sql.toString());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                hanghoa = resultSet.getInt(1);
                result[2] = new StringBuffer().append(hanghoa).toString();
            }
            sql = new StringBuffer();
            sql.append(" select count (*) from UM_REC_SUPPLIER_ENTER_MAST ");
            if ("1".equals(cls)) {
                sql.append(" where REG_YN = 'N' ");
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql.append(" and REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')");
                }
            }
            else {
                sql.append(" where REG_YN is null ");
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql.append(" and REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')");
                }
            }
            sql.append(" and BIZ_CLS like '%3%' ");
            pstmt = conn.prepareStatement(sql.toString());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                xaylap = resultSet.getInt(1);
                result[3] = new StringBuffer().append(resultSet.getInt(1)).toString();
            }
            sql = new StringBuffer();
            sql.append(" select count (*) from UM_REC_SUPPLIER_ENTER_MAST ");
            if ("1".equals(cls)) {
                sql.append(" where REG_YN = 'N' ");
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql.append(" and REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')");
                }
            }
            else {
                sql.append(" where REG_YN is null ");
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql.append(" and REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')");
                }
            }
            sql.append(" and BIZ_CLS like '%5%' ");
            pstmt = conn.prepareStatement(sql.toString());
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                tuvan = resultSet.getInt(1);
                result[4] = new StringBuffer().append(resultSet.getInt(1)).toString();
            }
            result[1] = new StringBuffer().append(hanghoa + xaylap + tuvan).toString();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".countAll(" + sdate + ") : " + e.toString());
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
        return result;
    }
    
    public void modify5(final String bizRezNo, final String addr, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            String sql = " update UM_REC_SUPPLIER_ENTER_MAST set ADDR = '" + addr + "'  ";
            sql = String.valueOf(sql) + " where BIZ_REG_NO = '" + bizRezNo + "'";
            pstmt = conn.prepareStatement(sql);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRezNo + ")");
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
}
