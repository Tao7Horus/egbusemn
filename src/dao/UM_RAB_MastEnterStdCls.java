// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import entity.StdClsItem;
import java.util.List;
import java.util.Vector;
import common.CommEntity;
import java.sql.PreparedStatement;
import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class UM_RAB_MastEnterStdCls
{
    public OneRowEntity selectDetail(final String stdClsCode, final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        try {
            sql.append(" select BIZ_REG_NO, STD_CLS_CD, STD_CLS_NO, to_char(STD_CLS_ISSUED_DT, 'dd/mm/yyyy') STD_CLS_ISSUED_DT, STD_CLS_EXPIRY_DT, CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR, MAST_STD_CLS_YN, ISSUE_INSTITU ");
            sql.append(" from UM_ENTER_STD_CLS t1 ");
            sql.append(" where t1.STD_CLS_CD = ? ");
            sql.append(" and t1.BIZ_REG_NO = ? ");
            return new CommDbQuery().getOneRowList(this, sql.toString(), new String[] { stdClsCode, bizRegNo }, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".selectDetail(" + stdClsCode + ") : " + e.toString());
            throw e;
        }
    }
    
    public void delete(final String stdClsCode, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_ENTER_STD_CLS ");
            sql.append(" where STD_CLS_CD= ? ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, stdClsCode);
            if (pstmt.executeUpdate() != 1) {
                throw new Exception("[Xóa bị lỗi]");
            }
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".delete(" + stdClsCode + ") : " + e.toString());
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
    
    public void deleteAll(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE FROM UM_ENTER_STD_CLS ");
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
            sql.append(" select t1.STD_CLS_CD, t1.STD_CLS_NAME, t2.CD_NM");
            sql.append(" from UM_ENTER_STD_CLS t1, SYN_PUB_CODE t2 ");
            sql.append(" where  t1.STD_CLS_CD = t2.CD and t2.CD_CLS = 'GZ8' and BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.debug(String.valueOf(this.getClass().getName()) + ".getList(" + bizRegNo + ") : " + e.toString());
            Log.errors(this, e, "");
            throw e;
        }
    }
    
    public void save(final String bizRezNo, final List stdClsList, final Connection conn) throws Exception {
        if (stdClsList.size() <= 0) {
            return;
        }
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
                sql.append(" into UM_ENTER_STD_CLS (BIZ_REG_NO, SERIAL_NO, STD_CLS_CD, STD_CLS_NAME)");
                sql.append(" values('" + bizRezNo + "', " + "(select nvl(max(SERIAL_NO), 0) from UM_ENTER_STD_CLS) + 1, '" + item.getStdClsCode() + "', '" + item.getStdClsName() + "')");
            }
            sql.append(" SELECT * FROM dual");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        }
        catch (Exception e) {
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
        if (pstmt != null) {
            try {
                pstmt.close();
            }
            catch (Exception ex2) {}
        }
    }
    
    public void save(final String bizRezNo, final String stdClsCode, final String stdClsNo, final String stdClsIssuedDate, final String stdClsExpiryDate, final String constAbilEvalAmt, final String evalStdYear, final String mastStdClsYn, final String issueInstitu, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" insert into UM_ENTER_STD_CLS (BIZ_REG_NO, STD_CLS_CD, STD_CLS_NO, STD_CLS_ISSUED_DT, STD_CLS_EXPIRY_DT, CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR, MAST_STD_CLS_YN, ISSUE_INSTITU) ");
            sql.append(" values (?, ?, ?, to_date(?, 'yyyy/mm/dd'), to_date(?, 'yyyy/mm/dd') , ?, ?, ?, ? )");
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
            sql.append(" update UM_ENTER_CLS set ENTER_CLS=?, MOD_DT=?, MOD_USER_ID= ?, USE_YN= ? ");
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
