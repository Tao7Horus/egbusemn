// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_GOJ_GovuA010b;
import java.util.Vector;
import entity.UM_ADE_GovuA040b;
import common.CommEntity;
import common.ComDbQuery;
import common.OneRowEntity;
import entity.UM_ADV_GovuA030b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_RAE_GovuA010b;

public class UM_RAB_GovuA010p
{
    public UM_RAE_GovuA010b select_goma_As(final String code) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_RAE_GovuA010b ettcode = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "  SELECT  RECV_NO, INSTITU_CD, INSTITU_FULL_NM, INSTITU_SHORT_NM, INSTITU_EN_NM,            BIZ_REG_NO, BIZ_CONDITION, BIZ_SALE_CAT, CHRGR_NM, CHRGR_DEPART,\t            CHRGR_PHONE_NO, CHRGR_FAX, CHRGR_EMAIL, CREDITOR_NM, ZIP_CD,\t            ADDR, DETAIL_ADDR, PHONE_NO, FAX, GOODS_MNG_NM,            WEBSITE, CERT_INSTITU, APP_REQ_YN, PERMIT_BRANCH, INSTITU_CLS,            REG_YN, REJECTED_RSON, to_char(RECV_DT,'yyyymmdd'), INSTITU_CLS, INSTITU_CLS_M, INSTITU_CLS_S ,\t\t\t    to_char(RECV_DT,'YYYY-MM-DD HH24:MI:SS'),            CERT_RECV_NO, MOBILE, SMS_RECV_YN, INSTITU_TYPE\t     FROM UM_REC_PUB_INSTITU_MAST     WHERE INSTITU_CD='" + code + "'";
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettcode = new UM_RAE_GovuA010b();
                ettcode.setrecept(rs.getString(1));
                ettcode.setg2bCode(rs.getString(2));
                ettcode.setgoNameFull(rs.getString(3));
                ettcode.setgoNameShort(rs.getString(4));
                ettcode.setgoNameEn(rs.getString(5));
                ettcode.setsaupNo(rs.getString(6));
                ettcode.setbuConditon(rs.getString(7));
                ettcode.setbuType(rs.getString(8));
                ettcode.settaskmaster(rs.getString(9));
                ettcode.setmasterPost(rs.getString(10));
                ettcode.setmasterTel(rs.getString(11));
                ettcode.setmasterFax(rs.getString(12));
                ettcode.setmasterMail(rs.getString(13));
                ettcode.setcreditName(rs.getString(14));
                ettcode.setZIPCODE(rs.getString(15));
                ettcode.setADDR(rs.getString(16));
                ettcode.setaddress2(rs.getString(17));
                ettcode.settelNum(rs.getString(18));
                ettcode.setfaxNum(rs.getString(19));
                ettcode.setgoodsMaster(rs.getString(20));
                ettcode.sethomepage(rs.getString(21));
                ettcode.setlic(rs.getString(22));
                ettcode.setlicense(rs.getString(23));
                ettcode.setperOff(rs.getString(24));
                ettcode.setrelation(rs.getString(25));
                ettcode.setenYn(rs.getString(26));
                ettcode.setback(rs.getString(27));
                ettcode.setcreateDate(rs.getString(28));
                ettcode.setorganL(rs.getString(29));
                ettcode.setorganM(rs.getString(30));
                ettcode.setorganS(rs.getString(31));
                ettcode.setcreateDateTime(rs.getString(32));
                ettcode.setlicenseCode(rs.getString(33));
                ettcode.setmasterhp(rs.getString(34));
                ettcode.setmastersms(rs.getString(35));
                ettcode.setInsType(rs.getString(36));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_goma('" + code + "') :" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_goma('" + code + "') :" + exc.toString());
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
        return ettcode;
    }
    
    public UM_RAE_GovuA010b select_goma(final String code) throws Exception, SQLException {
        if (code == null || code == "") {
            return new UM_RAE_GovuA010b();
        }
        Trx trx = null;
        Connection con = null;
        UM_RAE_GovuA010b ettcode = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = " SELECT INSTITU_CD, INSTITU_FULL_NM, INSTITU_SHORT_NM, INSTITU_EN_NM, BIZ_REG_NO  ,\t(SELECT COUNT(*) FROM BID.BID_BID_MASTER a \tWHERE a.BID_INSTITU_CD=? AND\tBID_TURN_NO=(SELECT MAX(BID_TURN_NO) FROM BID.BID_BID_MASTER WHERE a.BID_NO=BID_NO)) BID_COUNT,  (SELECT COUNT(*) FROM BID.BID_BID_MASTER b \t\tWHERE b.BID_INSTITU_CD=? AND BID_METHOD='01' AND\t\tBID_TURN_NO=(SELECT MAX(BID_TURN_NO) FROM BID.BID_BID_MASTER WHERE b.BID_NO=BID_NO)) BID_E_COUNT,  AREA_CD, ZIP_CD, ADDR, DETAIL_ADDR,  INSTITU_PHONE_NO, FAX,  INSTITU_TYPE,PERMIT_BRANCH, INSTITU_CONDITION, WEBSITE \tFROM UM_PUB_INSTITU_MAST \t\tWHERE INSTITU_CD=? ";
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, code);
            psmt.setString(2, code);
            psmt.setString(3, code);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ettcode = new UM_RAE_GovuA010b();
                ettcode.setg2bCode(rs.getString(1));
                ettcode.setgoNameFull(rs.getString(2));
                ettcode.setgoNameShort(rs.getString(3));
                ettcode.setgoNameEn(rs.getString(4));
                ettcode.setsaupNo(rs.getString(5));
                ettcode.setBidCount(rs.getInt(6));
                ettcode.setElecticBidCount(rs.getInt(7));
                ettcode.setlocaCode(rs.getString(8));
                ettcode.setZIPCODE(rs.getString(9));
                ettcode.setADDR(rs.getString(10));
                ettcode.setaddress2(rs.getString(11));
                ettcode.settelNum(rs.getString(12));
                ettcode.setfaxNum(rs.getString(13));
                ettcode.setbuType(rs.getString(14));
                ettcode.setperOff(rs.getString(15));
                ettcode.setbuConditon(rs.getString(16));
                ettcode.sethomepage(rs.getString(17));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_goma('" + code + "') :" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_goma('" + code + "') :" + exc.toString());
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
        return ettcode;
    }
    
    public UM_RAE_GovuA010b select_goma(final String code, String groupNo, String departmentNo) throws Exception, SQLException {
        if (code == null || code == "") {
            return new UM_RAE_GovuA010b();
        }
        Trx trx = null;
        Connection con = null;
        groupNo = this.selectGroupNo_Rec(code);
        departmentNo = this.selectDepartmentNo_Rec(code);
        UM_RAE_GovuA010b ettcode = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            String sql = "  SELECT  t1.RECV_NO, t1.INSTITU_CD, t1.INSTITU_FULL_NM, t1.INSTITU_SHORT_NM, t1.INSTITU_EN_NM,            t1.BIZ_REG_NO, t1.BIZ_CONDITION, t1.BIZ_SALE_CAT, t1.CHRGR_NM, t1.CHRGR_DEPART,\t            t1.CHRGR_PHONE_NO, t1.CHRGR_FAX, t1.CHRGR_EMAIL, t1.CREDITOR_NM, t1.ZIP_CD,\t            t1.ADDR, t1.DETAIL_ADDR, t1.PHONE_NO, t1.FAX, t1.GOODS_MNG_NM,            t1.WEBSITE, t1.CERT_INSTITU, t1.APP_REQ_YN, t1.PERMIT_BRANCH, t1.INSTITU_CLS,            t1.REG_YN, t1.REJECTED_RSON, to_char(t1.RECV_DT,'yyyymmdd'), t1.INSTITU_CLS, t1.INSTITU_CLS_M, t1.INSTITU_CLS_S ,\t\t\t    to_char(t1.RECV_DT,'YYYY-MM-DD HH24:MI:SS'),            t1.CERT_RECV_NO, t1.MOBILE, t1.SMS_RECV_YN, t1.INSTITU_TYPE ";
            if (!"".equals(departmentNo) && !"null".equals(departmentNo) && departmentNo != null && !"0".equals(departmentNo)) {
                sql = String.valueOf(sql) + ", t2.DEP_NAME, t2.DEPARTMENT_NO ";
            }
            if (!"".equals(groupNo) && !"null".equals(groupNo) && groupNo != null && !"0".equals(groupNo)) {
                sql = String.valueOf(sql) + ", t3.GROUP_NAME, t3.group_no ";
            }
            sql = String.valueOf(sql) + " FROM UM_REC_PUB_INSTITU_MAST t1";
            if (!"".equals(groupNo) && !"null".equals(groupNo) && groupNo != null && !"0".equals(groupNo)) {
                sql = String.valueOf(sql) + ", UM_PUB_GROUP t3 ";
            }
            if (!"".equals(departmentNo) && !"null".equals(departmentNo) && departmentNo != null && !"0".equals(departmentNo)) {
                sql = String.valueOf(sql) + ", UM_DEPARTMENT t2";
            }
            sql = String.valueOf(sql) + "     WHERE t1.RECV_NO='" + code + "' ";
            if (!"".equals(groupNo) && !"null".equals(groupNo) && groupNo != null && !"0".equals(groupNo)) {
                sql = String.valueOf(sql) + " and t1.GROUP_NO = t3.GROUP_NO ";
            }
            if (!"".equals(departmentNo) && !"null".equals(departmentNo) && departmentNo != null && !"0".equals(departmentNo)) {
                sql = String.valueOf(sql) + " and t1.DEPARTMENT_NO = t2.DEPARTMENT_NO ";
            }
            Log.debug("SQL select_goma: " + sql);
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettcode = new UM_RAE_GovuA010b();
                ettcode.setrecept(rs.getString(1));
                ettcode.setg2bCode(rs.getString(2));
                ettcode.setgoNameFull(rs.getString(3));
                ettcode.setgoNameShort(rs.getString(4));
                ettcode.setgoNameEn(rs.getString(5));
                ettcode.setsaupNo(rs.getString(6));
                ettcode.setbuConditon(rs.getString(7));
                ettcode.setbuType(rs.getString(8));
                ettcode.settaskmaster(rs.getString(9));
                ettcode.setmasterPost(rs.getString(10));
                ettcode.setmasterTel(rs.getString(11));
                ettcode.setmasterFax(rs.getString(12));
                ettcode.setmasterMail(rs.getString(13));
                ettcode.setcreditName(rs.getString(14));
                ettcode.setZIPCODE(rs.getString(15));
                ettcode.setADDR(rs.getString(16));
                ettcode.setaddress2(rs.getString(17));
                ettcode.settelNum(rs.getString(18));
                ettcode.setfaxNum(rs.getString(19));
                ettcode.setgoodsMaster(rs.getString(20));
                ettcode.sethomepage(rs.getString(21));
                ettcode.setlic(rs.getString(22));
                ettcode.setlicense(rs.getString(23));
                ettcode.setperOff(rs.getString(24));
                ettcode.setrelation(rs.getString(25));
                ettcode.setenYn(rs.getString(26));
                ettcode.setback(rs.getString(27));
                ettcode.setcreateDate(rs.getString(28));
                ettcode.setorganL(rs.getString(29));
                ettcode.setorganM(rs.getString(30));
                ettcode.setorganS(rs.getString(31));
                ettcode.setcreateDateTime(rs.getString(32));
                ettcode.setlicenseCode(rs.getString(33));
                ettcode.setmasterhp(rs.getString(34));
                ettcode.setmastersms(rs.getString(35));
                ettcode.setInsType(rs.getString(36));
                int i = 36;
                if (!"".equals(departmentNo) && !"null".equals(departmentNo) && departmentNo != null && !"0".equals(departmentNo)) {
                    ++i;
                    ettcode.setDepName(rs.getString(i));
                    ++i;
                    ettcode.setDepId(rs.getInt(i));
                }
                if (!"".equals(groupNo) && !"null".equals(groupNo) && groupNo != null && !"0".equals(groupNo)) {
                    ++i;
                    ettcode.setGroupName(rs.getString(i));
                    ++i;
                    ettcode.setGroupNo(rs.getInt(i));
                }
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_goma('" + code + "') :" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_goma('" + code + "') :" + exc.toString());
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
        return ettcode;
    }
    
    public UM_RAE_GovuA010b select_gmaster(final String mastercode) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        final UM_RAE_GovuA010b ettcode = new UM_RAE_GovuA010b();
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            String sql = "    SELECT t1.INSTITU_CD, t1.INSTITU_FULL_NM,t1.INSTITU_SHORT_NM, t1.INSTITU_EN_NM, t1.BIZ_REG_NO,\t \t                \n                   t1.CHRGR_NM, t1.CHRGR_DEPART, t1.CHRGR_PHONE_NO, t1.CHRGR_FAX,t1.CHRGR_EMAIL,\t\t\t\t\t    \n                   t1.CREDITOR_NM, t1.INSTITU_CLS, t1.AREA_CD, t1.ZIP_CD, t1.ADDR, t1.DETAIL_ADDR, t1.INSTITU_PHONE_NO, t1.FAX,\t\t\t\t\t\t\t    \n\t              t1.OLD_INSTITU_CD, t1.GOODS_MNG_NM, t1.APPROVAL_CD, t1.LOCAL_INSTITU, t1.E_AUTHENT_YN,t1.SPECIAL_MNG_AGENT,\t\t\t\t\t\t\t\n                   t1.LOCAL_INSTITU_DIV,t1.TRANS_DIST, t1.WEBSITE, t1.KFTC_PERMIT_NO, t1.REG_DT, t1.MANAGER_ID, t1.DELETE_YN,\t\t\t\t\t\t\t\n                   t1.UPDATE_DT, t1.RECV_NO, t1.TOP_INSTITU_CD, t1.INSTITU_TYPE, t1.HIST_CD, t1.INSTITU_CLS_M,\t\t\t\t\t\t\t\t\t\n                   t1.INSTITU_CLS_S, t1.CHECK_CD_YN, t1.MOBILE, t1.INSTITU_CONDITION, t1.PERMIT_BRANCH, t1.GROUP_NO, t1.DEPARTMENT_NO \n";
            if (this.selectGroupNo(mastercode) != 0) {
                sql = String.valueOf(sql) + "  , t2.GROUP_NAME ";
            }
            if (this.selectDepartmentNo(mastercode) != 0) {
                sql = String.valueOf(sql) + "  , t3.DEP_NAME ";
            }
            sql = String.valueOf(sql) + "       FROM UM_PUB_INSTITU_MAST t1\t\n";
            if (this.selectGroupNo(mastercode) != 0) {
                sql = String.valueOf(sql) + " , UM_PUB_GROUP t2 ";
            }
            if (this.selectDepartmentNo(mastercode) != 0) {
                sql = String.valueOf(sql) + " , UM_DEPARTMENT t3 ";
            }
            sql = String.valueOf(sql) + "       WHERE t1.INSTITU_CD = '" + mastercode + "'\t\n";
            if (this.selectGroupNo(mastercode) != 0) {
                sql = String.valueOf(sql) + "  AND t1.GROUP_NO = t2.GROUP_NO  ";
            }
            if (this.selectDepartmentNo(mastercode) != 0) {
                sql = String.valueOf(sql) + "  AND t1.DEPARTMENT_NO = t3.DEPARTMENT_NO  ";
            }
            Log.debug("SQL select_gmaster: " + sql);
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettcode.setg2bCode(rs.getString(1));
                ettcode.setgoNameFull(rs.getString(2));
                ettcode.setgoNameShort(rs.getString(3));
                ettcode.setgoNameEn(rs.getString(4));
                ettcode.setsaupNo(rs.getString(5).trim());
                ettcode.settaskmaster(rs.getString(6));
                ettcode.setmasterPost(rs.getString(7));
                ettcode.setmasterTel(rs.getString(8));
                ettcode.setmasterFax(rs.getString(9));
                ettcode.setmasterMail(rs.getString(10));
                ettcode.setcreditName(rs.getString(11));
                ettcode.setInsType(rs.getString(12));
                ettcode.setlocaCode(rs.getString(13));
                ettcode.setZIPCODE(rs.getString(14));
                ettcode.setADDR(rs.getString(15));
                ettcode.setaddress2(rs.getString(16));
                ettcode.settelNum(rs.getString(17));
                ettcode.setfaxNum(rs.getString(18));
                ettcode.setorganCode(rs.getString(19));
                ettcode.setgoodsMaster(rs.getString(20));
                ettcode.setpermitCode(rs.getString(21));
                ettcode.setprovince(rs.getString(22));
                ettcode.seteCitation(rs.getString(23));
                ettcode.setspOffice(rs.getString(24));
                ettcode.setbranchOffi(rs.getString(25));
                ettcode.settrDistance(rs.getString(26));
                ettcode.sethomepage((rs.getString(27) == null) ? "" : rs.getString(27));
                ettcode.setmoney(rs.getString(28));
                ettcode.setcreateDate(rs.getString(29));
                ettcode.setmasterID(rs.getString(30));
                ettcode.setdelete(rs.getString(31));
                ettcode.setmoDate(rs.getString(32));
                ettcode.setrecept(rs.getString(33));
                ettcode.setultraCode(rs.getString(34));
                ettcode.setbuType((rs.getString(35) == null) ? "" : rs.getString(35));
                ettcode.setbeforeCode(rs.getString(36));
                ettcode.setorganM(rs.getString(37));
                ettcode.setorganS(rs.getString(38));
                ettcode.setcodeCheckYN(rs.getString(39));
                ettcode.setmasterhp(rs.getString(40));
                ettcode.setbuConditon((rs.getString(41) == null) ? "" : rs.getString(41));
                ettcode.setperOff(rs.getString(42));
                ettcode.setGroupNo(rs.getInt(43));
                ettcode.setDepId(rs.getInt(44));
                int i = 44;
                if (this.selectGroupNo(mastercode) != 0) {
                    ++i;
                    ettcode.setGroupName(rs.getString(i));
                }
                if (this.selectDepartmentNo(mastercode) != 0) {
                    ++i;
                    ettcode.setDepName(rs.getString(i));
                }
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_gmaster('" + mastercode + "') :" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_gmaster('" + mastercode + "') :" + exc.toString());
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
        return ettcode;
    }
    
    public UM_ADV_GovuA030b getUser_NM(final String mastercode) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b ettcode = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "SELECT  a.CHRGR_NM ,a.CHRGR_DEPART ,a.CHRGR_PHONE_NO ,a.CHRGR_MOBILE,a.CHRGR_FAX ,a.CHRGR_EMAIL ,a.mast_cd , a.USER_ID\tFROM  USEMN.UM_USER   a            WHERE   a.USER_ID = ?         ";
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, mastercode);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ettcode = new UM_ADV_GovuA030b();
                ettcode.setgoNameFull(rs.getString(1));
                ettcode.setDepName(rs.getString(2));
                ettcode.setmasterTel(rs.getString(3));
                ettcode.setmasterPost(rs.getString(4));
                ettcode.setmasterFax(rs.getString(5));
                ettcode.setmasterMail(rs.getString(6));
                ettcode.setmathCode(rs.getString(7));
                ettcode.setg2bCode(rs.getString(8));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.getUser_NM('" + mastercode + "') :" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.getUser_NM('" + mastercode + "') :" + exc.toString());
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
        return ettcode;
    }
    
    public OneRowEntity getUser_NMById(final String userId) throws Exception {
        Trx trx = null;
        Connection conn = null;
        final String[] parameter = { userId };
        final String sql = "SELECT  CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO ,CHRGR_MOBILE, CHRGR_FAX ,CHRGR_EMAIL\tFROM  USEMN.UM_USER          WHERE   USER_ID= ?         ";
        try {
            trx = new Trx(this);
            conn = trx.getConnection();
            return new ComDbQuery().getOneRowList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exf) {
            Log.errors(this, exf, "");
            throw exf;
        }
        finally {
            if (trx != null) {
                trx.close();
            }
        }
    }
    
    public CommEntity[] getListUser_NM(final String code) throws Exception {
        Trx trx = null;
        Connection conn = null;
        final String[] parameter = { code };
        final String sql = "SELECT  CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO ,CHRGR_MOBILE, CHRGR_FAX ,CHRGR_EMAIL, USER_ID \tFROM  USEMN.UM_USER          WHERE   MAST_CD= ?         ";
        try {
            trx = new Trx(this);
            conn = trx.getConnection();
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exf) {
            Log.errors(this, exf, "");
            throw exf;
        }
        finally {
            if (trx != null) {
                trx.close();
            }
        }
    }
    
    public CommEntity[] getListUser_NMHist(final String code) throws Exception {
        Trx trx = null;
        Connection conn = null;
        final String[] parameter = { code };
        final String sql = "SELECT  CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO ,CHRGR_MOBILE, CHRGR_FAX ,CHRGR_EMAIL, USER_ID \tFROM  USEMN.UM_USER_HIST a          WHERE   MAST_CD= ? AND VER=(SELECT MAX(VER) FROM USEMN.UM_PUB_INSTITU_HIST WHERE INSTITU_CD=a.MAST_CD )       ";
        try {
            trx = new Trx(this);
            conn = trx.getConnection();
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exf) {
            Log.errors(this, exf, "");
            throw exf;
        }
        finally {
            if (trx != null) {
                trx.close();
            }
        }
    }
    
    public CommEntity[] getListUser_NM(final String code, final Connection conn) throws Exception {
        final String[] parameter = { code };
        final String sql = "SELECT  CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO ,CHRGR_MOBILE, CHRGR_FAX ,CHRGR_EMAIL, USER_ID \tFROM  USEMN.UM_USER          WHERE   MAST_CD= ?         ";
        try {
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exf) {
            Log.errors(this, exf, "");
            throw exf;
        }
    }
    
    public UM_ADV_GovuA030b getTop_UM_USER_HIST(final String mast_cd) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_ADV_GovuA030b ettcode = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "SELECT * FROM ( SELECT  a.CHRGR_NM ,a.CHRGR_DEPART ,a.CHRGR_PHONE_NO ,a.CHRGR_MOBILE,a.CHRGR_FAX ,a.CHRGR_EMAIL ,a.mast_cd , a.USER_ID\tFROM  USEMN.UM_USER_HIST   a            WHERE   a.MAST_CD= ? ORDER BY INPUT_DT DESC) where  ROWNUM = 1  ";
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, mast_cd);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ettcode = new UM_ADV_GovuA030b();
                ettcode.setgoNameFull(rs.getString(1));
                ettcode.setDepName(rs.getString(2));
                ettcode.setmasterTel(rs.getString(3));
                ettcode.setmasterPost(rs.getString(4));
                ettcode.setmasterFax(rs.getString(5));
                ettcode.setmasterMail(rs.getString(6));
                ettcode.setmathCode(rs.getString(7));
                ettcode.setg2bCode(rs.getString(8));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.getUser_NM('" + mast_cd + "') :" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.getUser_NM('" + mast_cd + "') :" + exc.toString());
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
        return ettcode;
    }
    
    public UM_RAE_GovuA010b select_confirm(final String code) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_RAE_GovuA010b ettcode = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "SELECT  a.INSTITU_CD,   a.INSTITU_FULL_NM,    a.INSTITU_SHORT_NM,    a.INSTITU_EN_NM,    a.BIZ_REG_NO, \n        a.BIZ_CONDITION,           a.BIZ_SALE_CAT,               a.CHRG_NM,           a.CHRGR_DEPART,       a.CHRG_PHONE_NO, \n        a.CHRGR_FAX, a.CHRGR_EMAIL,     a.CREDITOR_NM,           a.ZIP_CD,           a.ADDR,           \n        a.DETAIL_ADDR,     a.PHONE_NO,           a.FAX,           a.GOODS_MNG_NM,       a.WEBSITE,   \n        b.CERT_INSTITU,   a.E_AUTHENT_YN,       a.INSTITU_CLS,           a.SPECIAL_MNG_INSTITU,       a.LOCAL_INSTITU,       \n        a.INSTITU_LOCALDIVISION,       a.AREA_CD,           a.TRANSPORTDISTANCE,           a.TOP_INSTITU_CD,     a.INSTITU_TYPE_CLASSIFY,   \n        b.REG_YN,       b.REJECTED_RSON                                                                          \n\tFROM  UM_PUB_INSTITU_MAST a, UM_REC_PUB_INSTITU_MAST b                                                      \n WHERE  a.RECV_NO = b.RECV_NO                                                                               \n   AND  a.RECV_NO='" + code + "'                                                                                 \n";
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            System.out.println(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            while (rs.next()) {
                ettcode = new UM_RAE_GovuA010b();
                ettcode.setg2bCode(rs.getString(1));
                ettcode.setgoNameFull(rs.getString(2));
                ettcode.setgoNameShort(rs.getString(3));
                ettcode.setgoNameEn(rs.getString(4));
                ettcode.setsaupNo(rs.getString(5).trim());
                ettcode.setbuConditon(rs.getString(6));
                ettcode.setbuType(rs.getString(7));
                ettcode.settaskmaster(rs.getString(8));
                ettcode.setmasterPost(rs.getString(9));
                ettcode.setmasterTel(rs.getString(10));
                ettcode.setmasterFax(rs.getString(11));
                ettcode.setmasterMail(rs.getString(12));
                ettcode.setcreditName(rs.getString(13));
                ettcode.setZIPCODE(rs.getString(14));
                ettcode.setADDR(rs.getString(15));
                ettcode.setaddress2(rs.getString(16));
                ettcode.settelNum(rs.getString(17));
                ettcode.setfaxNum(rs.getString(18));
                ettcode.setgoodsMaster(rs.getString(19));
                ettcode.sethomepage(rs.getString(20));
                ettcode.setlic(rs.getString(21));
                ettcode.setlicense(rs.getString(22));
                ettcode.setrelation(rs.getString(23));
                ettcode.setspOffice(rs.getString(24));
                ettcode.setprovince(rs.getString(25));
                ettcode.setbranchOffi(rs.getString(26));
                ettcode.setlocaCode(rs.getString(27));
                ettcode.settrDistance(rs.getString(28));
                ettcode.setultraCode(rs.getString(29));
                ettcode.setorganL(rs.getString(30));
                ettcode.setenYn(rs.getString(31));
                ettcode.setback(rs.getString(32));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_confirm('" + code + "') :" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_confirm('" + code + "') :" + exc.toString());
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
        return ettcode;
    }
    
    public int Max_count(final String recept) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "SELECT count(*) FROM UM_REC_PUB_INSTITU_MAST WHERE RECV_NO = ? ";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, recept);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.Max_count('" + recept + "') :" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.Max_count('" + recept + "') :" + exc.toString());
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
    
    public int selectGroupNo(final String mastercode) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int groupNo = 0;
        try {
            final String sql = "SELECT GROUP_NO FROM UM_PUB_INSTITU_MAST WHERE INSTITU_CD = ? ";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, mastercode);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                groupNo = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p ('" + mastercode + "') :" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p ('" + mastercode + "') :" + exc.toString());
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
        return groupNo;
    }
    
    public int selectDepartmentNo(final String mastercode) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int departmentNo = 0;
        try {
            final String sql = "SELECT Department_No FROM UM_PUB_INSTITU_MAST WHERE INSTITU_CD = ? ";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, mastercode);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                departmentNo = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p ('" + mastercode + "') :" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p ('" + mastercode + "') :" + exc.toString());
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
        return departmentNo;
    }
    
    public UM_ADE_GovuA040b selectMath(final String code) {
        if (code == null || code == "") {
            return new UM_ADE_GovuA040b();
        }
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final UM_ADE_GovuA040b ett = new UM_ADE_GovuA040b();
        try {
            final String sql = "select INSTITU_ACCT_CD from UM_REC_PUB_INSTITU_ACCT_CD  where RECV_NO =? ";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ett.setmathCode(rs.getString(1));
            }
            rs.close();
            pstm.close();
            con.close();
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_math('" + code + "') :" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_math('" + code + "') :" + exc.toString());
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
            final String sql = "select INSTITU_ACCT_CD from UM_REC_PUB_INSTITU_ACCT_CD  where RECV_NO =? ";
            trx = new Trx(this);
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
            rs.close();
            pstm.close();
            con.close();
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_math('" + code + "') :" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_math('" + code + "') :" + exc.toString());
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
    
    public UM_ADE_GovuA040b[] select_gmath(final String code) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_ADE_GovuA040b[] ett = (UM_ADE_GovuA040b[])null;
        try {
            final String sql = "select INSTITU_ACCT_CD from UM_PUB_INSTITU_ACCT_CD  where INSTITU_CD =? ";
            trx = new Trx(this);
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
            rs.close();
            pstm.close();
            con.close();
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_gmath('" + code + "') :" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_gmath('" + code + "') :" + exc.toString());
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
    
    public int select_mathCount(final String code) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "SELECT count(*) FROM UM_REC_PUB_INSTITU_ACCT_CD  WHERE RECV_NO = ? ";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            pstm.close();
            con.close();
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_mathCount('" + code + "') :" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_mathCount('" + code + "') :" + exc.toString());
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
    
    public UM_GOJ_GovuA010b select_organ(final String code) throws Exception, SQLException {
        Trx trx = null;
        Connection con = null;
        UM_GOJ_GovuA010b ettall = null;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            final String sql = "select STD_INSTITU_CD,DIRECTOR_POSITION_NM,DIRECTOR_POSITION,VER_NO,RANK,UPPER_INSTITU_CD,TOP_INSTITU_CD,INSTITU_CLS_L,INSTITU_CLS_M,INSTITU_CLS_S,to_char(CREATE_DT,'YYYY-MM-DD'), to_char(STOP_DT,'YYYY-MM-DD'),STOP_RSON, to_char(MOD_DATE,'YYYY-MM-DD'),MOD_TIME,MOD_CLS,FULL_NM \tfrom UM_ADMIN_STD_INFOR  \twhere STD_INSTITU_CD='" + code + "'";
            trx = new Trx(this);
            con = trx.getConnection();
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
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
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p.select_organ('" + code + "') :" + sqle.toString());
            throw new Exception(sqle.getMessage());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p.select_organ('" + code + "') :" + exc.toString());
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
    
    public String selectGroupNo_Rec(final String code) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String groupNo = "";
        try {
            final String sql = "SELECT GROUP_NO FROM UM_REC_PUB_INSTITU_MAST WHERE RECV_NO = ? ";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                groupNo = rs.getString(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p ('" + code + "') :" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p ('" + code + "') :" + exc.toString());
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
        return groupNo;
    }
    
    public String selectDepartmentNo_Rec(final String code) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String departmentNo = "";
        try {
            final String sql = "SELECT Department_No FROM UM_REC_PUB_INSTITU_MAST WHERE RECV_NO = ? ";
            trx = new Trx(this);
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            if (rs.next()) {
                departmentNo = rs.getString(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_RAB_GovuA010p ('" + code + "') :" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_RAB_GovuA010p ('" + code + "') :" + exc.toString());
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
        return departmentNo;
    }
    
    public void updateUSER(final CommEntity[] userEntity, final Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        if (userEntity == null) {
            throw new Exception("Không tìm thấy thông tin người phụ trách dự thầu");
        }
        final String sql = " UPDATE USEMN.UM_USER SET CHRGR_NM = ? ,CHRGR_DEPART = ? ,CHRGR_PHONE_NO = ? ,CHRGR_MOBILE = ? ,CHRGR_FAX = ? ,CHRGR_EMAIL =?   WHERE USER_ID = ?  ";
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < userEntity.length; ++i) {
                pstmt.setString(1, userEntity[i].data[0]);
                pstmt.setString(2, userEntity[i].data[1]);
                pstmt.setString(3, userEntity[i].data[2]);
                pstmt.setString(4, userEntity[i].data[3]);
                pstmt.setString(5, userEntity[i].data[4]);
                pstmt.setString(6, userEntity[i].data[5]);
                pstmt.setString(7, userEntity[i].data[6]);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex) {}
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void deleteUserNewest(final String BizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append(" DELETE UM_USER_HIST ");
            sql.append(" where MAST_CD= ?  ");
            sql.append(" AND VER=(SELECT MAX(VER) FROM UM_SUPPLIER_ENTER_MAST_HIST WHERE BIZ_REG_NO = ? ) ");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, BizRegNo);
            pstmt.setString(2, BizRegNo);
            pstmt.executeUpdate();
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
}
