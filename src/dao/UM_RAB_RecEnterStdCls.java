// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import entity.StdClsItem;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.Trx;
import java.util.Vector;
import common.CommEntity;
import java.sql.PreparedStatement;
import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class UM_RAB_RecEnterStdCls
{
    public OneRowEntity selectDetail(final String stdClsCode, final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, STD_CLS_CD, STD_CLS_NO, STD_CLS_ISSUED_DT, STD_CLS_EXPIRY_DT, CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR, MAST_STD_CLS_YN, ISSUE_INSTITU, STD_CLS_NAME");
            sql.append(" from UM_REC_ENTER_STD_CLS t1 ");
            sql.append(" where t1.STD_CLS_CD = ? ");
            sql.append(" and t1.BIZ_REG_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { stdClsCode, bizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + stdClsCode + ") : " + e.toString());
            throw e;
        }
    }
    
    public void delete(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_REC_ENTER_STD_CLS ");
            sql.append(" where BIZ_REG_NO = ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRegNo);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + bizRegNo + ") : " + e.toString());
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
    
    public CommEntity[] getList(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
            sql.append(" select t1.STD_CLS_CD, t1.STD_CLS_NAME, t2.CD_NM ");
            sql.append(" from UM_REC_ENTER_STD_CLS t1, SYN_PUB_CODE t2 ");
            sql.append(" where t1.STD_CLS_CD = t2.CD and t2.CD_CLS = 'GZ8' and t1.BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getList(" + bizRegNo + ") : " + e.toString());
            throw e;
        }
    }
    
    public String getSelectNganhnghe_AddCode(final String name) throws Exception {
        String str = "";
        str = "<select name=\"" + name + "\" id=\"" + name + "\" class=\"read\" style=\"width: 100%;\" >";
        str = String.valueOf(str) + "<option value=\"\" >chọn nhóm ngành, lĩnh vực</option>";
        Trx trx = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final String sql = " select CD, CD_NM from SYN_PUB_CODE where CD_CLS = 'GZ8' ORDER by CD_NM desc";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                str = String.valueOf(str) + "<option value=\"" + rs.getString(1) + "\"\">" + rs.getString(2) + "</option>";
            }
            str = String.valueOf(str) + "</select>";
        }
        catch (SQLException sqle) {
            Log.errors("resmReceiveSearch.getResmReceiveList block SQLException : ");
            Log.errors(this, sqle, "Block Exception : " + sqle.toString() + sqle.getErrorCode() + sqle.getSQLState());
            throw new Exception("Exception Error : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.errors("resmReceiveSearch.getResmReceiveList block Exception : ");
            Log.errors(this, exc, "Block Exception : " + exc.toString());
            throw new Exception("Exception Error : " + exc.toString());
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
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        return str;
    }
    
    public void save(final String bizRezNo, final List stdClsList, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append("insert all ");
            StdClsItem item = null;
            for (int i = 0; i < stdClsList.size(); ++i) {
                item = stdClsList.get(i);
                Log.debug("item.getStdClsName(): " + item.getStdClsName());
                sql.append(" into UM_REC_ENTER_STD_CLS (BIZ_REG_NO, STD_CLS_CD, STD_CLS_NO, STD_CLS_ISSUED_DT, STD_CLS_EXPIRY_DT, CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR, MAST_STD_CLS_YN, ISSUE_INSTITU, STD_CLS_NAME)");
                sql.append(" values('" + bizRezNo + "', '" + item.getStdClsCode() + "', '" + item.getStdClsNo() + "', to_date('" + item.getStdClsIssuedDate() + "', 'dd/mm/yyyy'), " + " to_date('" + item.getStdClsExpiryDate() + "', 'dd/mm/yyyy'), '" + item.getConstAbilEvalAmt() + "', '" + item.getEvalStdYear() + "', '" + item.getMastStdClsYn() + "', '" + item.getIssueInsitue() + "', '" + item.getStdClsName() + "')");
            }
            sql.append(" SELECT * FROM dual");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + bizRezNo + ")\n\n" + e.toString());
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
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void saved(final String bizRezNo, final List stdClsList, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append("insert all ");
            StdClsItem item = null;
            for (int i = 0; i < stdClsList.size(); ++i) {
                item = stdClsList.get(i);
                Log.debug("item.getStdClsName(): " + item.getStdClsName());
                sql.append(" into UM_REC_ENTER_STD_CLS (BIZ_REG_NO, STD_CLS_CD, STD_CLS_NAME)");
                sql.append(" values('" + bizRezNo + "', '" + item.getStdClsCode() + "', '" + item.getStdClsName() + "')");
            }
            sql.append(" SELECT * FROM dual");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + bizRezNo + ")\n\n" + e.toString());
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
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void save(final String bizRezNo, final String stdClsCode, final String stdClsNo, final String stdClsIssuedDate, final String stdClsExpiryDate, final String constAbilEvalAmt, final String evalStdYear, final String mastStdClsYn, final String issueInstitu, final String stdClsName, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_REC_ENTER_STD_CLS (BIZ_REG_NO, STD_CLS_CD, STD_CLS_NO, STD_CLS_ISSUED_DT, STD_CLS_EXPIRY_DT, CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR, MAST_STD_CLS_YN, ISSUE_INSTITU, STD_CLS_NAME)");
            sql.append(" values (?, ?, ?, to_date(?, 'yyyy/mm/dd'), to_date(?, 'yyyy/mm/dd') , ?, ?, ?, ?,? )");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, bizRezNo);
            pstmt.setString(2, stdClsCode);
            pstmt.setString(3, stdClsNo);
            pstmt.setString(4, stdClsIssuedDate);
            pstmt.setString(5, stdClsExpiryDate);
            pstmt.setString(6, constAbilEvalAmt);
            pstmt.setString(7, evalStdYear);
            pstmt.setString(8, mastStdClsYn);
            pstmt.setString(9, issueInstitu);
            pstmt.setString(10, stdClsName);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Lỗi đăng ký]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".save(" + bizRezNo + ", " + stdClsCode + ", " + stdClsNo + ") : " + e.toString());
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
    
    public void modify(final String bizRezNo, final String enterCls, final String regDate, final String regUserId, final String modDate, final String modUserId, final String useYn, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" update UM_REC_ENTER_CLS set ENTER_CLS=?, MOD_DT=?, MOD_USER_ID= ?, USE_YN= ? ");
            sql.append(" where BIZ_REG_NO= ? ");
            sql.append(" and REG_DT= to_date(?,'yyyy/mm/dd')");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, enterCls);
            pstmt.setString(2, modDate);
            pstmt.setString(3, modUserId);
            pstmt.setString(4, useYn);
            pstmt.setString(5, bizRezNo);
            pstmt.setString(6, regDate);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Sửa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".modify(" + bizRezNo + ", " + regDate + ", " + enterCls + ", " + regUserId + ") : " + e.toString());
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
