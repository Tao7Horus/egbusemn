// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Vector;
import entity.SubUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_URE_UserA020b;

public class UM_URB_UserA030p
{
    public UM_URE_UserA020b select_master(final String code) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_URE_UserA020b ett = null;
        try {
            final String sql = "select INSTITU_FULL_NM, INSTITU_CD,  decode(ADDR,'x',' ',ADDR),decode(DETAIL_ADDR,'x',' ',DETAIL_ADDR), INSTITU_PHONE_NO, FAX  from UM_PUB_INSTITU_MAST where INSTITU_CD='" + code + "'";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ett = new UM_URE_UserA020b();
                ett.setGovName(rs.getString(1));
                ett.setGovCode(rs.getString(2));
                ett.setGovAddress(rs.getString(3));
                ett.setGovTelephone(rs.getString(5));
                ett.setGovFax(rs.getString(6));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.select_master('" + code + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.select_master('" + code + "'):" + exc.toString());
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
        if (ett == null) {
            ett = new UM_URE_UserA020b();
        }
        return ett;
    }
    
    public UM_URE_UserA020b select_master_c(final String code) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_URE_UserA020b ett = null;
        try {
            final String sql = "select INSTITU_FULL_NM, INSTITU_CD,  decode(ADDR,'x',' ',ADDR),decode(DETAIL_ADDR,'x',' ',DETAIL_ADDR), INSTITU_PHONE_NO, FAX  from UM_SUPPLIER_ENTER_MAST where INSTITU_CD='" + code + "'";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ett = new UM_URE_UserA020b();
                ett.setGovName(rs.getString(1));
                ett.setGovCode(rs.getString(2));
                ett.setGovAddress(rs.getString(3));
                ett.setGovTelephone(rs.getString(5));
                ett.setGovFax(rs.getString(6));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.select_master('" + code + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.select_master('" + code + "'):" + exc.toString());
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
        if (ett == null) {
            ett = new UM_URE_UserA020b();
        }
        return ett;
    }
    
    public UM_URE_UserA020b select_user(final String id) {
        Trx trx = null;
        Connection con = null;
        UM_URE_UserA020b ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            final String sql = " select USER_ID, USER_CLS, MAST_CD, PASSWORD, CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_MOBILE, CHRGR_FAX, CHRGR_EMAIL, \tUSER_GRP, CON_TYPE, PERMIT_YN, to_char(FIRST_REG_DT,'dd/mm/yyyy hh24:mi'), MINISTRY_ID, PAY_ID, PAY_SERVER_ID, INSTITU_FUNC_APPLY_YN, MAST_RECV_YN, SWITCH_MNG_AUTHOR, PERMIT_BRANCH, FA_YN,  PA_YN, CITIS_ID, CITIS_SERVER_ID, INSTITU_WORK_CLS, PASSWORD2, IS_LOCK, TEST_OPTION_YN from UM_USER  where USER_ID='" + id + "'";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_URE_UserA020b();
                ett.setUserId(rs.getString(1));
                ett.setUserClass(rs.getString(2));
                ett.setMasterCode(rs.getString(3));
                ett.setPassword(rs.getString(4));
                ett.setUserName(rs.getString(5));
                ett.setUserPost(rs.getString(6));
                ett.setUserTelephone(rs.getString(7));
                ett.setUserMobilePhone(rs.getString(8));
                ett.setUserFax(rs.getString(9));
                ett.setUserEmail(rs.getString(10));
                ett.setUserGroup(rs.getString(11));
                ett.setConnectionType(rs.getString(12));
                ett.setApproval(rs.getString(13));
                ett.setRegDate(rs.getString(14));
                ett.setMofeId(rs.getString(15));
                ett.setPayId(rs.getString(16));
                ett.setPaySystemId(rs.getString(17));
                ett.setNationalFunction(rs.getString(18));
                ett.setKeySender(rs.getString(19));
                ett.setChangeAdminRight(rs.getString(20));
                ett.setApprovalCode(rs.getString(21));
                ett.setJaemuYN(rs.getString(22));
                ett.setJichulYN(rs.getString(23));
                ett.setCitisID(rs.getString(24));
                ett.setCitisServerID(rs.getString(25));
                ett.setUpmuGubun(rs.getString(26));
                ett.setPassword2(rs.getString(27));
                ett.setIsLock(rs.getString(28));
                ett.setTestOptionYn(rs.getString(29));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.select_user('" + id + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.select_user('" + id + "'):" + exc.toString());
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
    
    public SubUser[] selectSubUser(final String id) {
        Trx trx = null;
        Connection con = null;
        SubUser ett = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        SubUser[] ettList = (SubUser[])null;
        final Vector vec = new Vector();
        try {
            final String sql = " select SUB_USER_ID, USER_ID, SUB_USER_NAME, PASSWORD, \tSUB_USER_FULLNAME, SUB_USER_ADDR, SUB_USER_DEPT, SUB_USER_POS, SUB_USER_TEL, SUB_USER_EMAIL, IS_ACTIVE from UM_SUB_USER  where USER_ID='" + id + "'";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new SubUser();
                ett.setSubUserId(rs.getString(1));
                ett.setUserId(rs.getString(2));
                ett.setSubUserName(rs.getString(3));
                ett.setPassword(rs.getString(4));
                ett.setSubUserFullname(rs.getString(5));
                ett.setSubUserAddr(rs.getString(6));
                ett.setSubUserDept(rs.getString(7));
                ett.setSubUserPos(rs.getString(8));
                ett.setSubUserTel(rs.getString(9));
                ett.setSubUserEmail(rs.getString(10));
                ett.setIsActive(rs.getString(11));
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.selectSubUser('" + id + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.selectSubUser('" + id + "'):" + exc.toString());
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
        ettList = new SubUser[vec.size()];
        vec.copyInto(ettList);
        return ettList;
    }
    
    public UM_URE_UserA020b[] select_userlist(final int pagenum, final int PAGEMAX, final String gov, final String depart, final String name) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_URE_UserA020b[] ettlist = (UM_URE_UserA020b[])null;
        try {
            final StringBuffer sb = new StringBuffer();
            sb.append(" select USER_ID, USER_CLS, MAST_CD, PASSWORD, ").append(" \tCHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_MOBILE, ").append(" \tCHRGR_FAX, CHRGR_EMAIL, USER_GRP, CON_TYPE, PERMIT_YN, FIRST_REG_DT, ").append(" \tMINISTRY_ID, PAY_ID, PAY_SERVER_ID, INSTITU_FUNC_APPLY_YN, ").append(" \tMAST_RECV_YN, SWITCH_MNG_AUTHOR, INSTITU_FULL_NM, N ").append(" from ( select ").append(" \t\t\ta.USER_ID, a.USER_CLS, a.MAST_CD, a.PASSWORD,").append(" \t\t\ta.CHRGR_NM, a.CHRGR_DEPART, a.CHRGR_PHONE_NO, a.CHRGR_MOBILE,").append(" \t\t\ta.CHRGR_FAX, a.CHRGR_EMAIL, a.USER_GRP, a.CON_TYPE, a.PERMIT_YN, a.FIRST_REG_DT,").append(" \t\t\ta.MINISTRY_ID, a.PAY_ID, a.PAY_SERVER_ID, a.INSTITU_FUNC_APPLY_YN,").append(" \t\t\ta.MAST_RECV_YN, a.SWITCH_MNG_AUTHOR, b.INSTITU_FULL_NM, ROWNUM N ").append(" \t\t from UM_USER a, UM_PUB_INSTITU_MAST b \t").append("\t\t where USER_CLS='g' and a.MAST_CD = b.INSTITU_CD ").append(" \t\t\tand lower(b.INSTITU_FULL_NM) like '%" + gov.toLowerCase() + "%' ").append("\t\t\tand lower(a.CHRGR_DEPART) like '%" + depart.toLowerCase() + "%' ").append("\t\t\tand lower(a.CHRGR_NM) like '%" + name.toLowerCase() + "%' " + " )").append("\t\t order by b.INSTITU_FULL_NM ) ").append(" where N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ") ").append(" order by USER_ID");
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sb.toString());
            UM_URE_UserA020b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_URE_UserA020b();
                et.setUserId(rs.getString(1));
                et.setUserClass(rs.getString(2));
                et.setMasterCode(rs.getString(3));
                et.setPassword(rs.getString(4));
                et.setUserName(rs.getString(5));
                et.setUserPost(rs.getString(6));
                et.setUserTelephone(rs.getString(7));
                et.setUserMobilePhone(rs.getString(8));
                et.setUserFax(rs.getString(9));
                et.setUserEmail(rs.getString(10));
                et.setUserGroup(rs.getString(11));
                et.setConnectionType(rs.getString(12));
                et.setApproval(rs.getString(13));
                et.setRegDate(rs.getString(14));
                et.setMofeId(rs.getString(15));
                et.setPayId(rs.getString(16));
                et.setPaySystemId(rs.getString(17));
                et.setNationalFunction(rs.getString(18));
                et.setKeySender(rs.getString(19));
                et.setChangeAdminRight(rs.getString(20));
                et.setGovCode(rs.getString(22));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.select_userlist(gov[" + gov + "],depart[" + depart + "],name[" + name + "]):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.select_userlist(gov[" + gov + "],depart[" + depart + "],name[" + name + "]):" + exc.toString());
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
        ettlist = new UM_URE_UserA020b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public UM_URE_UserA020b[] select_userlist(final int pagenum, final int PAGEMAX, final String gov, final String depart, final String name, String gubun) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_URE_UserA020b[] ettlist = (UM_URE_UserA020b[])null;
        while (true) {
            if (gubun.equals("all")) {
                gubun = "g' or a.USER_CLS='a' or a.USER_CLS='k' or a.USER_CLS='h' or a.USER_CLS='m' or a.USER_CLS='p' or a.USER_CLS='s' or a.USER_CLS='t' or a.USER_CLS='y' or a.USER_CLS='c";
                try {
                    final StringBuffer s = new StringBuffer();
                    s.append(" select ").append(" USER_ID, USER_CLS, MAST_CD, PASSWORD,").append(" CHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_MOBILE,").append(" CHRGR_FAX, CHRGR_EMAIL, USER_GRP, ").append(" CON_TYPE, PERMIT_YN, FIRST_REG_DT,").append(" MINISTRY_ID, PAY_ID, PAY_SERVER_ID, INSTITU_FUNC_APPLY_YN,").append(" MAST_RECV_YN, SWITCH_MNG_AUTHOR, N, INSTITU_FULL_NM ").append(" from ").append("( select a.USER_ID, a.USER_CLS, a.MAST_CD, a.PASSWORD,").append(" a.CHRGR_NM, a.CHRGR_DEPART, a.CHRGR_PHONE_NO, a.CHRGR_MOBILE,").append(" a.CHRGR_FAX, a.CHRGR_EMAIL, a.USER_GRP,").append(" a.CON_TYPE, a.PERMIT_YN, a.FIRST_REG_DT, a.MINISTRY_ID,").append(" a.PAY_ID, a.PAY_SERVER_ID, a.INSTITU_FUNC_APPLY_YN,");
                    if (!"c".equals(gubun)) {
                        s.append(" a.MAST_RECV_YN, a.SWITCH_MNG_AUTHOR, ROWNUM N, b.INSTITU_FULL_NM ").append(" from UM_USER a, UM_PUB_INSTITU_MAST b ").append(" where a.MAST_CD = b.INSTITU_CD ");
                    }
                    else {
                        s.append(" a.MAST_RECV_YN, a.SWITCH_MNG_AUTHOR, ROWNUM N, b.BIZ_NM INSTITU_FULL_NM ").append(" from UM_USER a, UM_SUPPLIER_ENTER_MAST b  ").append(" where a.MAST_CD = b.BIZ_REG_NO ");
                    }
                    if (!"".equals(gov) && !"c".equals(gubun)) {
                        s.append(" and lower(b.INSTITU_FULL_NM) like '%" + gov.toLowerCase() + "%'");
                    }
                    if (!"".equals(gov) && "c".equals(gubun)) {
                        s.append(" and lower(b.BIZ_NM) like '%" + gov.toLowerCase() + "%'");
                    }
                    if (!"".equals(depart)) {
                        s.append(" and lower(a.CHRGR_DEPART) like '%" + depart.toLowerCase() + "%'");
                    }
                    if (!"".equals(name)) {
                        s.append(" and lower(a.USER_ID) like '" + name.toLowerCase() + "%'");
                    }
                    if (!"".equals(gubun)) {
                        s.append(" and (a.USER_CLS='" + gubun + "')");
                    }
                    if (!"c".equals(gubun)) {
                        s.append("order by b.INSTITU_FULL_NM ");
                    }
                    else {
                        s.append("order by b.BIZ_NM ");
                    }
                    s.append(")").append("").append(" where N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ") ").append("");
                    trx = new Trx(this, "USEMN");
                    con = trx.getConnection();
                    pstm = con.prepareStatement(s.toString());
                    UM_URE_UserA020b et = null;
                    rs = pstm.executeQuery();
                    while (rs.next()) {
                        et = new UM_URE_UserA020b();
                        et.setUserId(rs.getString(1));
                        et.setUserClass(rs.getString(2));
                        et.setMasterCode(rs.getString(3));
                        et.setPassword(rs.getString(4));
                        et.setUserName(rs.getString(5));
                        et.setUserPost(rs.getString(6));
                        et.setUserTelephone(rs.getString(7));
                        et.setUserMobilePhone(rs.getString(8));
                        et.setUserFax(rs.getString(9));
                        et.setUserEmail(rs.getString(10));
                        et.setUserGroup(rs.getString(11));
                        et.setConnectionType(rs.getString(12));
                        et.setApproval(rs.getString(13));
                        et.setRegDate(rs.getString(14));
                        et.setMofeId(rs.getString(15));
                        et.setPayId(rs.getString(16));
                        et.setPaySystemId(rs.getString(17));
                        et.setNationalFunction(rs.getString(18));
                        et.setKeySender(rs.getString(19));
                        et.setChangeAdminRight(rs.getString(20));
                        et.setGovName(rs.getString(22));
                        vec.addElement(et);
                    }
                }
                catch (SQLException sqle) {
                    Log.debug("UM_URB_UserA030p.select_userlist(gov[" + gov + "],depart[" + depart + "],name[" + name + "],gubun[" + gubun + "]):" + sqle.toString());
                }
                catch (Exception exc) {
                    Log.debug("UM_URB_UserA030p.select_userlist(gov[" + gov + "],depart[" + depart + "],name[" + name + "],gubun[" + gubun + "]):" + exc.toString());
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
                ettlist = new UM_URE_UserA020b[vec.size()];
                vec.copyInto(ettlist);
                return ettlist;
            }
            continue;
        }
    }
    
    public UM_URE_UserA020b[] select_guserlist(final String code) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_URE_UserA020b[] ettlist = (UM_URE_UserA020b[])null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        try {
            final String sql = "select a.MAST_CD, b.INSTITU_FULL_NM, a.USER_ID, a.CHRGR_DEPART, a.CHRGR_NM, a.FIRST_REG_DT from UM_USER a, UM_PUB_INSTITU_MAST b where a.MAST_CD=b.INSTITU_CD and a.PERMIT_YN='2' and a.MAST_CD='" + code + "' order by a.USER_ID asc";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            UM_URE_UserA020b et = null;
            while (rs.next()) {
                et = new UM_URE_UserA020b();
                et.setMasterCode(rs.getString(1));
                et.setGovName(rs.getString(2));
                et.setUserId(rs.getString(3));
                et.setUserPost(rs.getString(4));
                et.setUserName(rs.getString(5));
                et.setRegDate(rs.getString(6));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.select_guserlist('" + code + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.select_guserlist('" + code + "'):" + exc.toString());
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
        ettlist = new UM_URE_UserA020b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public UM_URE_UserA020b[] selectUser(final String mCode, final int page_no, final int page_size) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        UM_URE_UserA020b[] ettlist = (UM_URE_UserA020b[])null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final String sql = " select MAST_CD, INSTITU_FULL_NM, USER_ID,CHRGR_DEPART,CHRGR_NM, to_char(FIRST_REG_DT,'dd/mm/yyyy hh24:mi'),t ,USER_CLS from ( \t select MAST_CD, INSTITU_FULL_NM, USER_ID,CHRGR_DEPART,CHRGR_NM, FIRST_REG_DT, rownum t ,USER_CLS \t from ( \t \t  select a.MAST_CD, b.INSTITU_FULL_NM, a.USER_ID,a.CHRGR_DEPART, a.CHRGR_NM, a.FIRST_REG_DT , a.USER_CLS \t\t  from UM_USER a, UM_PUB_INSTITU_MAST b  \t\t  where a.MAST_CD=b.INSTITU_CD  \t\t  and a.PERMIT_YN='2'  \t\t  and a.MAST_CD=? \t\t  order by 5 ) \t) where t between " + Integer.toString(page_size * (page_no - 1) + 1) + " and " + Integer.toString(page_size * page_no);
        try {
            System.out.println(sql);
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, mCode);
            rs = pstm.executeQuery();
            UM_URE_UserA020b et = null;
            while (rs.next()) {
                et = new UM_URE_UserA020b();
                et.setMasterCode(rs.getString(1));
                et.setGovName(rs.getString(2));
                et.setUserId(rs.getString(3));
                et.setUserPost(rs.getString(4));
                et.setUserName(rs.getString(5));
                et.setRegDate(rs.getString(6));
                et.setUserClass(rs.getString(8));
                vec.addElement(et);
            }
            ettlist = new UM_URE_UserA020b[vec.size()];
            vec.copyInto(ettlist);
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.selectUser('" + mCode + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.selectUser('" + mCode + "'):" + exc.toString());
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
        return ettlist;
    }
    
    public int Max_count(final String gov, final String depart, final String name, String gubun) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        while (true) {
            if (gubun.equals("all")) {
                gubun = "g' or a.USER_CLS='a' or a.USER_CLS='k' or a.USER_CLS='h' or a.USER_CLS='m' or a.USER_CLS='p' or a.USER_CLS='s' or a.USER_CLS='t' or a.USER_CLS='y' or a.USER_CLS='c";
                try {
                    final StringBuffer sb = new StringBuffer();
                    if (!"c".equals(gubun)) {
                        sb.append(" select count(*) from UM_USER a, UM_PUB_INSTITU_MAST b ").append(" where a.MAST_CD = b.INSTITU_CD ");
                    }
                    else {
                        sb.append(" select count(*) from UM_USER a, UM_SUPPLIER_ENTER_MAST b  ").append(" where a.MAST_CD = b.BIZ_REG_NO ");
                    }
                    if (!"".equals(gov.toLowerCase()) && !"c".equals(gubun)) {
                        sb.append(" and lower(b.INSTITU_FULL_NM) like '%" + gov.toLowerCase() + "%' ");
                    }
                    if (!"".equals(gov.toLowerCase()) && "c".equals(gubun)) {
                        sb.append(" and lower(b.BIZ_NM) like '%" + gov.toLowerCase() + "%' ");
                    }
                    if (!"".equals(depart.toLowerCase())) {
                        sb.append(" and lower(a.CHRGR_DEPART) like '%" + depart.toLowerCase() + "%' ");
                    }
                    if (!"".equals(name.toLowerCase())) {
                        sb.append(" and a.USER_ID like '" + name + "%' ");
                    }
                    if (!"".equals(gubun.toLowerCase())) {
                        sb.append(" and (a.USER_CLS='" + gubun + "') ");
                    }
                    Log.debug(sb.toString());
                    trx = new Trx(this, "USEMN");
                    con = trx.getConnection();
                    pstm = con.prepareStatement(sb.toString());
                    rs = pstm.executeQuery();
                    pstm.clearParameters();
                    while (rs.next()) {
                        count = rs.getInt(1);
                    }
                }
                catch (SQLException sqle) {
                    Log.debug("UM_URB_UserA030p.Max_count('" + gov + "','" + depart + "','" + name + "','" + gubun + "'):" + sqle.toString());
                }
                catch (Exception exc) {
                    Log.debug("UM_URB_UserA030p.Max_count('" + gov + "','" + depart + "','" + name + "','" + gubun + "'):" + exc.toString());
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
    
    public int Max_count(final String gov, final String depart, final String name) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "select count(*) from UM_USER a, UM_PUB_INSTITU_MAST b where a.MAST_CD=b.INSTITU_CD and lower(b.INSTITU_FULL_NM) like '%" + gov.toLowerCase() + "%' and lower(a.CHRGR_DEPART) like '%" + depart.toLowerCase() + "%' and lower(a.CHRGR_NM) like '%" + name.toLowerCase() + "%' and a.USER_CLS='g' order by b.INSTITU_FULL_NM";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.Max_count('" + gov + "','" + depart + "','" + name + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.Max_count('" + gov + "','" + depart + "','" + name + "'):" + exc.toString());
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
    
    public int Max_count(final String code) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            final String sql = "select count(*) from UM_USER where PERMIT_YN='2' and MAST_CD='" + code + "'";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.Max_count('" + code + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.Max_count('" + code + "'):" + exc.toString());
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
    
    public UM_URE_UserA020b[] select_userlist(final int pagenum, final int PAGEMAX, final String gubun, final String mastername, final String mastercode, final String userid, final String txtDate1, final String txtDate2) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        PreparedStatement pstm = null;
        UM_URE_UserA020b[] ettlist = (UM_URE_UserA020b[])null;
        try {
            if (gubun.equals("c") || gubun.equals("x")) {
                sb.append(" select \tUSER_ID, USER_CLS, MAST_CD, PASSWORD, ").append("\t\t \tCHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_MOBILE,").append("\t\t\tCHRGR_FAX, CHRGR_EMAIL,").append("\t\t\tUSER_GRP, CON_TYPE, PERMIT_YN, FIRST_REG_DT,").append("\t\t\tMINISTRY_ID, PAY_ID, PAY_SERVER_ID, INSTITU_FUNC_APPLY_YN,").append("\t\t\tMAST_RECV_YN, SWITCH_MNG_AUTHOR, N, BIZ_NM ").append(" from ( ").append("\t\t\tselect\ta.USER_ID, a.USER_CLS, a.MAST_CD, a.PASSWORD,").append("\t\t\t\t\ta.CHRGR_NM, a.CHRGR_DEPART, a.CHRGR_PHONE_NO, a.CHRGR_MOBILE,").append("\t\t\t\t\ta.CHRGR_FAX, a.CHRGR_EMAIL, ").append("\t\t\t\t\ta.USER_GRP, a.CON_TYPE, a.PERMIT_YN, a.FIRST_REG_DT, ").append("\t\t\t\t\ta.MINISTRY_ID,a.PAY_ID, a.PAY_SERVER_ID, a.INSTITU_FUNC_APPLY_YN,").append("\t\t\t\t\ta.MAST_RECV_YN, a.SWITCH_MNG_AUTHOR, ROWNUM N, b.BIZ_NM").append("\t\t\tfrom UM_USER a, UM_SUPPLIER_ENTER_MAST b ").append("\t\t\twhere \ta.MAST_CD = b.BIZ_REG_NO  ").append("\t\t\t\t\tand a.MAST_CD like '" + mastercode + "%'").append("\t\t\t\t\tand b.BIZ_NM like '%" + mastername + "%'").append("\t\t\t\t\tand a.USER_ID like '" + userid + "%' ").append(" \t\t\t\t\tand (a.USER_CLS='" + gubun + "') ").append("\t\t\t\t\tand and to_char(a.FIRST_REG_DT,'YYYYMMDD') between '" + txtDate1 + "' and '" + txtDate2 + "' ").append("\t\t\torder by b.BIZ_NM ").append("\t\t)").append(" where N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ") ").append(" order by FIRST_REG_DT desc");
            }
            else {
                sb.append("select USER_ID, USER_CLS, MAST_CD, PASSWORD,").append("\t\tCHRGR_NM, CHRGR_DEPART, CHRGR_PHONE_NO, CHRGR_MOBILE,").append("\t\tCHRGR_FAX, CHRGR_EMAIL,").append("\t\tUSER_GRP, CON_TYPE, PERMIT_YN, FIRST_REG_DT,").append("\t\tMINISTRY_ID, PAY_ID, PAY_SERVER_ID, INSTITU_FUNC_APPLY_YN,").append("\t\tMAST_RECV_YN, SWITCH_MNG_AUTHOR, N, INSTITU_FULL_NM ").append("from (").append("\t\tselect  a.USER_ID, a.USER_CLS, a.MAST_CD, a.PASSWORD,").append("\t\t\t\ta.CHRGR_NM, a.CHRGR_DEPART, a.CHRGR_PHONE_NO, a.CHRGR_MOBILE,").append("\t\t\t\ta.CHRGR_FAX, a.CHRGR_EMAIL, a.USER_GRP,").append("\t\t\t\ta.CON_TYPE, a.PERMIT_YN, a.FIRST_REG_DT, a.MINISTRY_ID,").append("\t\t\t\ta.PAY_ID, a.PAY_SERVER_ID, a.INSTITU_FUNC_APPLY_YN,").append("\t\t\t\ta.MAST_RECV_YN, a.SWITCH_MNG_AUTHOR, ROWNUM N, b.INSTITU_FULL_NM ").append("\t\tfrom UM_USER a, UM_PUB_INSTITU_MAST b ").append("\t\twhere a.MAST_CD = b.INSTITU_CD ").append(" \t\t\t\tand a.MAST_CD like '" + mastercode + "%' ").append("\t\t\t\tand b.INSTITU_FULL_NM like '%" + mastername + "%'").append(" \t\t\t\tand a.USER_ID like '" + userid + "%'").append("\t\t\t\tand (a.USER_CLS='" + gubun + "') ").append("\t\t\t\tand to_char(a.FIRST_REG_DT,'YYYYMMDD') between '" + txtDate1 + "' and '" + txtDate2 + "'").append("\t\torder by b.INSTITU_FULL_NM ").append("\t  )").append("where N between (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) and (" + pagenum + " * " + PAGEMAX + ") ").append("order by FIRST_REG_DT desc");
            }
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sb.toString());
            UM_URE_UserA020b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_URE_UserA020b();
                et.setUserId(rs.getString(1));
                et.setUserClass(rs.getString(2));
                et.setMasterCode(rs.getString(3));
                et.setPassword(rs.getString(4));
                et.setUserName(rs.getString(5));
                et.setUserPost(rs.getString(6));
                et.setUserTelephone(rs.getString(7));
                et.setUserMobilePhone(rs.getString(8));
                et.setUserFax(rs.getString(9));
                et.setUserEmail(rs.getString(10));
                et.setUserGroup(rs.getString(11));
                et.setConnectionType(rs.getString(12));
                et.setApproval(rs.getString(13));
                et.setRegDate(rs.getString(14));
                et.setMofeId(rs.getString(15));
                et.setPayId(rs.getString(16));
                et.setPaySystemId(rs.getString(17));
                et.setNationalFunction(rs.getString(18));
                et.setKeySender(rs.getString(19));
                et.setChangeAdminRight(rs.getString(20));
                et.setGovName(rs.getString(22));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.select_userlist('" + gubun + "','" + mastername + "','" + mastercode + "','" + userid + "','" + txtDate1 + "','" + txtDate2 + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.select_userlist('" + gubun + "','" + mastername + "','" + mastercode + "','" + userid + "','" + txtDate1 + "','" + txtDate2 + "'):" + exc.toString());
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
        ettlist = new UM_URE_UserA020b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public int Max_count(final String gubun, final String mastername, final String mastercode, final String userid, final String txtDate1, final String txtDate2) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        PreparedStatement pstm = null;
        int count = 0;
        try {
            if (gubun.equals("c") || gubun.equals("x")) {
                sb.append(" select count(*) from UM_USER a, UM_SUPPLIER_ENTER_MAST b ").append(" where a.MAST_CD=b.BIZ_REG_NO ").append(" and a.MAST_CD like '" + mastercode + "%' ").append(" and b.BIZ_NM like '%" + mastername + "%' ").append(" and a.USER_ID like '" + userid + "%' ").append(" and (a.USER_CLS='" + gubun + "') ").append(" and to_char(a.FIRST_REG_DT,'YYYYMMDD') between '" + txtDate1 + "' and '" + txtDate2 + "'");
            }
            else {
                sb.append(" select count(*) from UM_USER a, UM_PUB_INSTITU_MAST b  ").append(" where a.MAST_CD=b.INSTITU_CD  ").append(" and a.USER_CLS='" + gubun + "%' ").append(" and b.INSTITU_FULL_NM like '%" + mastername + "%' ").append(" and a.MAST_CD like '" + mastercode + "%' ").append(" and a.USER_ID like '" + userid + "%' ").append(" and to_char(a.FIRST_REG_DT,'YYYYMMDD') between '" + txtDate1 + "' and '" + txtDate2 + "'");
            }
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sb.toString());
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.Max_count('" + gubun + "','" + mastername + "','" + mastercode + "','" + userid + "','" + txtDate1 + "','" + txtDate2 + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.Max_count('" + gubun + "','" + mastername + "','" + mastercode + "','" + userid + "','" + txtDate1 + "','" + txtDate2 + "'):" + exc.toString());
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
    
    public UM_URE_UserA020b select_topgovcode(final String gcode) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_URE_UserA020b ett = null;
        try {
            final String sql = " SELECT TOP_INSTITU_CD FROM UM_PUB_INSTITU_MAST WHERE INSTITU_CD = '" + gcode + "' ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                ett = new UM_URE_UserA020b();
                ett.setTopGovCode(rs.getString(1));
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.select_topcode('" + gcode + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.select_topcode('" + gcode + "'):" + exc.toString());
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
    
    public int UserID_count(final String g2bCode, final String userID, final String userName) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int count = 0;
        try {
            String sql = "SELECT count(*) FROM UM_USER WHERE PERMIT_YN='2' AND MAST_CD='" + g2bCode + "'";
            if (userID.length() > 0) {
                sql = String.valueOf(sql) + " AND lower(USER_ID) LIKE '" + userID.toLowerCase() + "%' ";
            }
            if (userName.length() > 0) {
                sql = String.valueOf(sql) + " AND lower(CHRGR_NM) LIKE '%" + userName.toLowerCase() + "%' ";
            }
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.UserID_count('" + g2bCode + "','" + userID + "','" + userName + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.UserID_count('" + g2bCode + "','" + userID + "','" + userName + "'):" + exc.toString());
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
    
    public UM_URE_UserA020b[] UserID_list(final int pagenum, final int PAGEMAX, final String g2bCode, final String userID, final String userName) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_URE_UserA020b[] ettlist = (UM_URE_UserA020b[])null;
        try {
            String sql = " SELECT MAST_CD, INSTITU_FULL_NM, USER_ID,CHRGR_DEPART,CHRGR_NM, to_char(FIRST_REG_DT,'yyyy/mm/dd hh24:mi'), USER_CLS, N  FROM (        SELECT a.MAST_CD, b.INSTITU_FULL_NM, a.USER_ID,a.CHRGR_DEPART, a.CHRGR_NM, a.FIRST_REG_DT, a.USER_CLS, ROWNUM N        FROM UM_USER a, UM_PUB_INSTITU_MAST b        WHERE a.MAST_CD=b.INSTITU_CD        AND a.PERMIT_YN='2'        AND a.MAST_CD='" + g2bCode + "'";
            if (userID.length() > 0) {
                sql = String.valueOf(sql) + " AND lower(a.USER_ID) LIKE '" + userID.toLowerCase() + "%' ";
            }
            if (userName.length() > 0) {
                sql = String.valueOf(sql) + " AND lower(a.CHRGR_NM) LIKE '%" + userName.toLowerCase() + "%' ";
            }
            sql = String.valueOf(sql) + "\t\tORDER BY FIRST_REG_DT ";
            sql = String.valueOf(sql) + " ) WHERE N BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ") ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            UM_URE_UserA020b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_URE_UserA020b();
                et.setMasterCode(rs.getString(1));
                et.setGovName(rs.getString(2));
                et.setUserId(rs.getString(3));
                et.setUserPost(rs.getString(4));
                et.setUserName(rs.getString(5));
                et.setRegDate(rs.getString(6));
                et.setUserClass(rs.getString(7));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.UserID_list(" + pagenum + "," + PAGEMAX + ",'" + g2bCode + "','" + userID + "','" + userName + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.UserID_list(" + pagenum + "," + PAGEMAX + ",'" + g2bCode + "','" + userID + "','" + userName + "'):" + exc.toString());
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
        ettlist = new UM_URE_UserA020b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
    
    public UM_URE_UserA020b[] UserID_Supplier_list(final int pagenum, final int PAGEMAX, final String g2bCode, final String userID, final String userName) {
        Trx trx = null;
        Connection con = null;
        final Vector vec = new Vector();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        UM_URE_UserA020b[] ettlist = (UM_URE_UserA020b[])null;
        try {
            String sql = " SELECT MAST_CD, BIZ_EN_NM, USER_ID,CHRGR_DEPART,CHRGR_NM, to_char(FIRST_REG_DT,'yyyy/mm/dd hh24:mi'), USER_CLS, N  FROM (        SELECT a.MAST_CD, b.BIZ_EN_NM, a.USER_ID,a.CHRGR_DEPART, a.CHRGR_NM, a.FIRST_REG_DT, a.USER_CLS, ROWNUM N        FROM UM_USER a, UM_SUPPLIER_ENTER_MAST b        WHERE a.MAST_CD=b.BIZ_REG_NO        AND a.PERMIT_YN='2'        AND a.MAST_CD='" + g2bCode + "'";
            if (userID.length() > 0) {
                sql = String.valueOf(sql) + " AND lower(a.USER_ID) LIKE '" + userID.toLowerCase() + "%' ";
            }
            if (userName.length() > 0) {
                sql = String.valueOf(sql) + " AND lower(a.CHRGR_NM) LIKE '%" + userName.toLowerCase() + "%' ";
            }
            sql = String.valueOf(sql) + "\t\tORDER BY FIRST_REG_DT ";
            sql = String.valueOf(sql) + " ) WHERE N BETWEEN (((" + pagenum + " - 1) * " + PAGEMAX + ")+1) AND (" + pagenum + " * " + PAGEMAX + ") ";
            trx = new Trx(this, "USEMN");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            UM_URE_UserA020b et = null;
            rs = pstm.executeQuery();
            while (rs.next()) {
                et = new UM_URE_UserA020b();
                et.setMasterCode(rs.getString(1));
                et.setGovName(rs.getString(2));
                et.setUserId(rs.getString(3));
                et.setUserPost(rs.getString(4));
                et.setUserName(rs.getString(5));
                et.setRegDate(rs.getString(6));
                et.setUserClass(rs.getString(7));
                vec.addElement(et);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_URB_UserA030p.UserID_list(" + pagenum + "," + PAGEMAX + ",'" + g2bCode + "','" + userID + "','" + userName + "'):" + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_URB_UserA030p.UserID_list(" + pagenum + "," + PAGEMAX + ",'" + g2bCode + "','" + userID + "','" + userName + "'):" + exc.toString());
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
        ettlist = new UM_URE_UserA020b[vec.size()];
        vec.copyInto(ettlist);
        return ettlist;
    }
}
