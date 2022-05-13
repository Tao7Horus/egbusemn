// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Vector;
import entity.UM_ADE_GovuA040b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_GOJ_GovuA010b;

public class UM_GOB_GovuA010p
{
    public UM_GOJ_GovuA010b select_goma(final String code) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_GOJ_GovuA010b ettall = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "  select a.INSTITU_CD,a.INSTITU_FULL_NM,a.INSTITU_SHORT_NM,a.INSTITU_EN_NM,a.BIZ_REG_NO,a.BIZ_CONDITION, a.BIZ_SALE_CAT,a.CHRG_NM,\t\t\t\t\t\t\t\t\ta.CHRGR_DEPART, a.CHRG_PHONE_NO,a.CHRGR_FAX,a.CHRGR_EMAIL,a.CREDITOR_NM,a.INSTITU_CLS,a.ZIP_CD,a.ADDR,a.DETAIL_ADDR,a.PHONE_NO,\t\t\t\ta.FAX,a.OLD_INSTITU_CD,a.GOODS_MNG_NM,a.APPROVAL_CD,a.LOCAL_INSTITU,a.E_AUTHENT_YN,a.SPECIAL_MNG_INSTITU,a.INSTITU_LOCALDIVISION,a.TRANSPORTDISTANCE,a.WEBSITE,\t\t\t\ta.CREATE_DT,a.MANAGER_ID, a.DELETE_YN, a.UPDATE_DT, a.AREA_CD, b.STD_INSTITU_CD,b.DIRECTOR_POSITION_NM,b.DIRECTOR_POSITION,b.VER_NO,b.RANK,\t\t\t\t\t\t\t\t\tb.UPPER_INSTITU_CD,b.TOP_INSTITU_CD,b.INSTITU_CLS_L,b.INSTITU_CLS_M,b.INSTITU_CLS_S,to_char(b.CREATE_DT,'YYYY-MM-DD'),\t\t\t\t\t\t\t\t\t\t\tto_char(b.STOP_DT,'YYYY-MM-DD'),b.STOP_RSON,to_char(b.MOD_DATE,'YYYY-MM-DD'),b.MOD_TIME,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tb.MOD_CLS, a.KFTC_PERMIT_NO, b.FULL_NM, b.LOWEST_RANK_NM, a.INSTITU_TYPE_CLASSIFY, a.INSTITU_CLS_M, a.INSTITU_CLS_S, a.TOP_INSTITU_CD,\t\t\t\t\t\t\t      a.INSTITU_CLS1, \t from UM_PUB_INSTITU_MAST a, UM_ADMIN_STD_INFOR b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t where a.INSTITU_CD=b.STD_INSTITU_CD(+) and a.INSTITU_CD='" + code + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettall = new UM_GOJ_GovuA010b();
                ettall.setg2bCode(rs.getString(1));
                ettall.setgoNameFull(rs.getString(2));
                ettall.setgoNameShort(rs.getString(3));
                ettall.setgoNameEn(rs.getString(4));
                ettall.setsaupNo(rs.getString(5));
                ettall.setbuConditon(rs.getString(6));
                ettall.setbuType(rs.getString(7));
                ettall.settaskmaster(rs.getString(8));
                ettall.setmasterPost(rs.getString(9));
                ettall.setmasterTel(rs.getString(10));
                ettall.setmasterFax(rs.getString(11));
                ettall.setmasterMail(rs.getString(12));
                ettall.setcreditName(rs.getString(13));
                ettall.setrelation(rs.getString(14));
                ettall.setZIPCODE(rs.getString(15));
                ettall.setADDR(rs.getString(16));
                ettall.setaddress2(rs.getString(17));
                ettall.settelNum(rs.getString(18));
                ettall.setfaxNum(rs.getString(19));
                ettall.setorganCode(rs.getString(20));
                ettall.setgoodsMaster(rs.getString(21));
                ettall.setpermitCode(rs.getString(22));
                ettall.setprovince(rs.getString(23));
                ettall.seteCitation(rs.getString(24));
                ettall.setspOffice(rs.getString(25));
                ettall.setbranchOffi(rs.getString(26));
                ettall.settrDistance(rs.getString(27));
                ettall.sethomepage(rs.getString(28));
                ettall.setentryDate(rs.getString(29));
                ettall.setmasterID(rs.getString(30));
                ettall.setdelete(rs.getString(31));
                ettall.setmoDate(rs.getString(32));
                ettall.setlocaCode(rs.getString(33));
                ettall.setg2Code(rs.getString(34));
                ettall.setposition(rs.getString(35));
                ettall.setpositionRank(rs.getString(36));
                ettall.setdisparity(rs.getString(37));
                ettall.setgrade(rs.getString(38));
                ettall.setultraCode(rs.getString(39));
                ettall.setsubCode(rs.getString(40));
                ettall.setorganL(rs.getString(41));
                ettall.setorganM(rs.getString(42));
                ettall.setorganS(rs.getString(43));
                ettall.setcreateDate(rs.getString(44));
                ettall.setabDate(rs.getString(45));
                ettall.setabDivision(rs.getString(46));
                ettall.setmodiDate(rs.getString(47));
                ettall.setmoTime(rs.getString(49));
                ettall.setmoDivision(rs.getString(49));
                ettall.setmoney(rs.getString(50));
                ettall.setfullName(rs.getString(51));
                ettall.setlowestName(rs.getString(52));
                ettall.setorganL2(rs.getString(53));
                ettall.setorganM2(rs.getString(54));
                ettall.setorganS2(rs.getString(55));
                ettall.setsubCode2(rs.getString(56));
                ettall.setrelation1(rs.getString(57));
                ettall.setlaw(rs.getString(58));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.select_goma('" + code + "'):" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.select_goma('" + code + "'):" + exc.toString());
            throw new Exception(exc.getMessage());
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
        return ettall;
    }
    
    public UM_GOJ_GovuA010b select_organ(final String code) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_GOJ_GovuA010b ettall = null;
        final ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = " SELECT  A.STD_INSTITU_CD, A.DIRECTOR_POSITION_NM, A.DIRECTOR_POSITION, A.VER_NO, A.RANK,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tA.UPPER_INSTITU_CD, A.TOP_INSTITU_CD, A.INSTITU_CLS_L,A.INSTITU_CLS_M, A.INSTITU_CLS_S,\t\t\t\t\t\t\t\t\t\t\tto_char(A.CREATE_DT,'YYYY-MM-DD'),\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tto_char(A.STOP_DT,'YYYY-MM-DD'), A.STOP_RSON, to_char(A.MOD_DATE,'YYYY-MM-DD'), A.MOD_TIME,\t\t\t\t\t\tA.MOD_CLS, A.FULL_NM,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tA.ZIP_CD, A.RESIDENCE, A.LAND_LOT_NO, A.PHONE_NO, A.FAX, B.INSTITU_CLS\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t FROM   UM_ADMIN_STD_INFOR A,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t        ( SELECT B.INSTITU_CLS FROM UM_ADMIN_STD_INFOR A, UM_PUB_INSTITU_MAST B\t\t\t\t\t\t\t\t\t\t\t\t           WHERE A.STD_INSTITU_CD ='" + code + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t             AND A.TOP_INSTITU_CD = B.INSTITU_CD(+)) B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\tWHERE   A.STD_INSTITU_CD='" + code + "'\t\t\t\t\t\t\t\t\t\t\t\t";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.clearParameters();
            while (rs.next()) {
                ettall = new UM_GOJ_GovuA010b();
                ettall.setg2Code(rs.getString(1));
                ettall.setposition(rs.getString(2));
                ettall.setpositionRank(rs.getString(3));
                ettall.setdisparity(rs.getString(4));
                ettall.setgrade(rs.getString(5));
                ettall.setultraCode(rs.getString(6));
                ettall.setsubCode(rs.getString(7));
                ettall.setorganL(rs.getString(8));
                ettall.setorganM(rs.getString(9));
                ettall.setorganS(rs.getString(10));
                ettall.setcreateDate(rs.getString(11));
                ettall.setabDate(rs.getString(12));
                ettall.setabDivision(rs.getString(13));
                ettall.setmodiDate(rs.getString(14));
                ettall.setmoTime(rs.getString(15));
                ettall.setmoDivision(rs.getString(16));
                ettall.setgoNameFull(rs.getString(17));
                ettall.setZIPCODE(rs.getString(18));
                ettall.setADDR(rs.getString(19));
                ettall.setaddress2(rs.getString(20));
                ettall.settelNum(rs.getString(21));
                ettall.setfaxNum(rs.getString(22));
                ettall.setrelation(rs.getString(23));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.select_organ('" + code + "'):" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.select_organ('" + code + "'):" + exc.toString());
            throw new Exception(exc.getMessage());
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
        return new UM_GOJ_GovuA010b();
    }
    
    public UM_GOJ_GovuA010b select_organ1(final String code, final String str) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_GOJ_GovuA010b ett = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "select FULL_NM\tfrom UM_ADMIN_STD_INFOR  \twhere STD_INSTITU_CD='" + str + "' ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett = new UM_GOJ_GovuA010b();
                ett.setgoNameFull(rs.getString(1));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.select_organ1('" + code + "','" + str + "'):" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.select_organ1('" + code + "','" + str + "'):" + exc.toString());
            throw new Exception(exc.getMessage());
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
        return ett;
    }
    
    public UM_GOJ_GovuA010b select_organ2(final String code, final String str1) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_GOJ_GovuA010b ett1 = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "select FULL_NM\tfrom UM_ADMIN_STD_INFOR  \twhere  STD_INSTITU_CD = '" + str1 + "'";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ett1 = new UM_GOJ_GovuA010b();
                ett1.setgoNameFull(rs.getString(1));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.select_organ2('" + code + "','" + str1 + "'):" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.select_organ2('" + code + "','" + str1 + "'):" + exc.toString());
            throw new Exception(exc.getMessage());
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
        return ett1;
    }
    
    public UM_ADE_GovuA040b selectMath(final String code) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_ADE_GovuA040b ett = null;
        try {
            final String sql = "select INSTITU_ACCT_CD from UM_PUB_INSTITU_ACCT_CD  where INSTITU_CD=? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            final UM_ADE_GovuA040b et = null;
            rs = pstm.executeQuery();
            if (rs.next()) {
                ett = new UM_ADE_GovuA040b();
                ett.setmathCode(rs.getString(1));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.select_math('" + code + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.select_math('" + code + "'):" + exc.toString());
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
    
    public UM_ADE_GovuA040b[] select_math(final String code) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_ADE_GovuA040b[] ett = (UM_ADE_GovuA040b[])null;
        try {
            final String sql = "select INSTITU_ACCT_CD from UM_PUB_INSTITU_ACCT_CD  where INSTITU_CD=? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            UM_ADE_GovuA040b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_ADE_GovuA040b();
                et.setmathCode(rs.getString(1));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.select_math('" + code + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.select_math('" + code + "'):" + exc.toString());
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
        ett = new UM_ADE_GovuA040b[vec.size()];
        vec.copyInto(ett);
        return ett;
    }
    
    public int Max_count(final String code) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(*) FROM UM_PUB_INSTITU_ACCT_CD \n WHERE INSTITU_CD = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.Max_count('" + code + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.Max_count('" + code + "'):" + exc.toString());
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
    
    public int Max_count_1(final String code) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(*) FROM UM_PUB_INSTITU_ACCT_CD \n WHERE RECV_NO = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.Max_count_1('" + code + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.Max_count_1('" + code + "'):" + exc.toString());
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
    
    public int Max_count1(final String code) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(*) FROM UM_ADMIN_STD_INFOR \n WHERE STD_INSTITU_CD = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.Max_count1('" + code + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.Max_count1('" + code + "'):" + exc.toString());
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
    
    public int Max_name(final String code, final String str) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(FULL_NM) FROM UM_ADMIN_STD_INFOR \n WHERE STD_INSTITU_CD = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, str);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.Max_name('" + code + "','" + str + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.Max_name('" + code + "','" + str + "'):" + exc.toString());
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
    
    public int Max_name1(final String code, final String str1) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(FULL_NM) FROM UM_ADMIN_STD_INFOR \n WHERE STD_INSTITU_CD = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, str1);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.Max_name1('" + code + "','" + str1 + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.Max_name1('" + code + "','" + str1 + "'):" + exc.toString());
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
    
    public int Max_count2(final String code) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "\n SELECT count(*) FROM UM_PUB_INSTITU_MAST \n WHERE INSTITU_CD = ? ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.Max_count2('" + code + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.Max_count2('" + code + "'):" + exc.toString());
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
    
    public boolean check_changeOrg(final String mCode, final String userID) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        boolean check = false;
        int count = -1;
        try {
            final String sql = "SELECT count(*) from UM_MAST_MOD_PROCESS \n WHERE STD_INSTITU_CD = ? \n AND USER_ID = ? \n AND NEW_CD is null ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mCode);
            pstmt.setString(2, userID);
            rs = pstmt.executeQuery();
            pstmt.clearParameters();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (count > 0) {
                check = true;
            }
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
            Log.debug("UM_GOB_GovuA010p.check_changeOrg('" + mCode + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            exc.printStackTrace();
            Log.debug("UM_GOB_GovuA010p.check_changeOrg('" + mCode + "'):" + exc.toString());
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
        return check;
    }
    
    public Vector getNewCode(final String mCode) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        final Vector newCode = new Vector();
        try {
            final String sql = "SELECT INSTITU_CD from UM_PUB_INSTITU_MAST_MOD \n WHERE BEFORE_CD = ? \n AND HIST_CLS = 'NEW' \n ORDER BY INSTITU_CD ";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mCode);
            rs = pstmt.executeQuery();
            pstmt.clearParameters();
            while (rs.next()) {
                newCode.addElement(rs.getString(1));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_GOB_GovuA010p.getNewCode('" + mCode + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_GOB_GovuA010p.getNewCode('" + mCode + "'):" + exc.toString());
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
        return newCode;
    }
}
