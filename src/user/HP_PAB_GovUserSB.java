// 
// Decompiled by Procyon v0.5.30
// 

package user;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Trx;
import java.util.Vector;

public class HP_PAB_GovUserSB extends HP_PAB_UserSB
{
    public boolean insertUserEB(final HP_PAE_UserInfo userEB) {
        boolean flag = false;
        final StringBuffer sql = new StringBuffer();
        final Vector data = new Vector();
        sql.append("\tINSERT INTO UM_ADDED_USER\t\n");
        sql.append("\t\t(USER_NAME,\t\n");
        sql.append("\t\tUSER_CLS,\t\n");
        sql.append("\t\tSECRET_KEY,\t\n");
        sql.append("\t\tUSER_ID,\t\n");
        sql.append("\t\tUSER_FULL_NM,\t\n");
        sql.append("\t\tUSER_IDENT_NO,\t\n");
        sql.append("\t\tUSER_PHONE,\t\n");
        sql.append("\t\tUSER_FAX,\t\n");
        sql.append("\t\tUSER_MOBILE,\t\n");
        sql.append("\t\tUSER_EMAIL,\t\n");
        sql.append("\t\tZIP_CD,\t\n");
        sql.append("\t\tUSER_ADDR,\t\n");
        sql.append("\t\tCREATE_DT,\t\n");
        sql.append("\t\tUPDATE_DATE,  \t\n");
        sql.append("\t\tBIZ_REG_NO,  \t\n");
        sql.append("\t\tCER_DN,  \t\n");
        sql.append("\t\tAPPROVE_YN,  \t\n");
        sql.append("\t\tCOM_CLS,  \t\n");
        sql.append("\t\tDELETE_YN  \t\n");
        sql.append("\t) VALUES (\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\tSYSDATE,\t\n");
        sql.append("\t\tSYSDATE,\t\n");
        sql.append("\t\t?,\t\n");
        sql.append("\t\t?,\t\n");
        sql.append("\t\t?,\t\n");
        sql.append("\t\t?,\t\n");
        sql.append("\t\t'N')\t\n");
        data.add(userEB.getUserid());
        data.add(userEB.getUsergubun());
        data.add(userEB.getUserpasswd());
        data.add(userEB.getRefid());
        data.add(userEB.getUsername());
        data.add(userEB.getRegistno1());
        data.add(userEB.getUsertel1());
        if (userEB.getUserfax1() != null) {
            data.add(userEB.getUserfax1());
        }
        else {
            data.add("");
        }
        if (userEB.getUsermobile1() != null) {
            data.add(userEB.getUsermobile1());
        }
        else {
            data.add("");
        }
        if (userEB.getUsermail() != null) {
            data.add(userEB.getUsermail());
        }
        else {
            data.add("");
        }
        if (userEB.getPostno1() != null) {
            data.add(userEB.getPostno1());
        }
        else {
            data.add("");
        }
        if (userEB.getAddress1() != null) {
            data.add(userEB.getAddress1());
        }
        else {
            data.add("");
        }
        data.add("123");
        data.add("123");
        data.add("Y");
        data.add("B");
        flag = this.executeQuery(sql, data);
        return flag;
    }
    
    public boolean insertUserEBbackup(final HP_PAE_UserInfo userEB) {
        boolean flag = false;
        final StringBuffer sql = new StringBuffer();
        final Vector data = new Vector();
        sql.append("\tINSERT INTO UM_ADDED_USER\t\n");
        sql.append("\t\t(USER_NAME,\t\n");
        sql.append("\t\tUSER_CLS,\t\n");
        sql.append("\t\tSECRET_KEY,\t\n");
        sql.append("\t\tUSER_ID,\t\n");
        sql.append("\t\tUSER_FULL_NM,\t\n");
        sql.append("\t\tUSER_IDENT_NO,\t\n");
        sql.append("\t\tUSER_PHONE,\t\n");
        sql.append("\t\tUSER_FAX,\t\n");
        sql.append("\t\tUSER_MOBILE,\t\n");
        sql.append("\t\tUSER_EMAIL,\t\n");
        sql.append("\t\tZIP_CD,\t\n");
        sql.append("\t\tUSER_ADDR,\t\n");
        sql.append("\t\tUSER_DETAIL_ADDR,\t\n");
        sql.append("\t\tUSER_DEPART,\t\n");
        sql.append("\t\tPOSITION,\t\n");
        sql.append("\t\tSIGNAL_CHANNEL,\t\n");
        sql.append("\t\tCREATE_DT,\t\n");
        sql.append("\t\tUPDATE_DATE,  \t\n");
        sql.append("\t\tDELETE_YN  \t\n");
        sql.append("\t) VALUES (\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\t?,\t\t\n");
        sql.append("\t\tSYSDATE,\t\n");
        sql.append("\t\tSYSDATE,\t\n");
        sql.append("\t\t'N')\t\n");
        data.add(userEB.getUserid());
        data.add(userEB.getUsergubun());
        data.add(userEB.getUserpasswd());
        data.add(userEB.getRefid());
        data.add(userEB.getUsername());
        data.add(userEB.getRegistno1());
        data.add(userEB.getUsertel1());
        if (userEB.getUserfax1() != null) {
            data.add(userEB.getUserfax1());
        }
        else {
            data.add("");
        }
        if (userEB.getUsermobile1() != null) {
            data.add(userEB.getUsermobile1());
        }
        else {
            data.add("");
        }
        if (userEB.getUsermail() != null) {
            data.add(userEB.getUsermail());
        }
        else {
            data.add("");
        }
        if (userEB.getPostno1() != null) {
            data.add(userEB.getPostno1());
        }
        else {
            data.add("");
        }
        if (userEB.getAddress1() != null) {
            data.add(userEB.getAddress1());
        }
        else {
            data.add("");
        }
        if (userEB.getPartname() != null) {
            data.add(userEB.getPartname());
        }
        else {
            data.add("");
        }
        if (userEB.getPosition() != null) {
            data.add(userEB.getPosition());
        }
        else {
            data.add("");
        }
        if (userEB.getPreference() != null) {
            data.add(userEB.getPreference());
        }
        else {
            data.add("");
        }
        flag = this.executeQuery(sql, data);
        return flag;
    }
    
    public HP_PAE_UserList getGovUserListEB(final String refid, final int size, final int start) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        final HP_PAE_UserList userlist = new HP_PAE_UserList(size);
        final StringBuffer sql = new StringBuffer();
        final String sql2 = new String();
        sql.append("SELECT k \n");
        sql.append(", userid \n");
        sql.append(", username \n");
        sql.append(", partname \n");
        sql.append(", corptel1 \n");
        sql.append(", usergubun \n");
        sql.append(", registdate \n");
        sql.append("FROM \n");
        sql.append("    (SELECT rownum k, USER_NAME userid \n");
        sql.append("    , USER_FULL_NM username \n");
        sql.append("    , nvl(USER_DEPART,'no') partname \n");
        sql.append("    , USER_PHONE corptel1 \n");
        sql.append("    , 'USER_CLS' usergubun \n");
        sql.append("    , to_char(CREATE_DT,'YYYY/MM/DD') registdate \n");
        sql.append("    FROM UM_ADDED_USER \n");
        sql.append("    WHERE USER_ID = '" + refid + "' \n");
        sql.append("    AND USER_CLS = 'C' AND  NVL(DELETE_YN,'N') <> 'Y') \n");
        sql.append("WHERE k BETWEEN " + start + " AND " + (start + size - 1) + " \n");
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            HP_PAE_UserInfo userEB = null;
            while (rs.next()) {
                userEB = new HP_PAE_UserInfo();
                userEB.setUserid(rs.getString("userid"));
                userEB.setUsername(rs.getString("username"));
                userEB.setPartname(rs.getString("partname"));
                userEB.setCorptel1(rs.getString("corptel1"));
                userEB.setUsergubun(rs.getString("usergubun"));
                userEB.setRegistdate(rs.getString("registdate"));
                userlist.add(userEB);
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception : " + ex);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex2) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex3) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex5) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex6) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex7) {}
        }
        return userlist;
    }
    
    public HP_PAE_UserInfo getGovUserDaeEB(final String refid) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        final StringBuffer sql = new StringBuffer();
        HP_PAE_UserInfo userEB = null;
        sql.append("    SELECT USER_ID userid \n");
        sql.append("    , CHRGR_NM username \n");
        sql.append("    , CHRGR_DEPART partname \n");
        sql.append("    , CHRGR_PHONE_NO corptel1 \n");
        sql.append("    , 'USER_CLS' usergubun \n");
        sql.append("    FROM UM_USER \n");
        sql.append("    WHERE USER_ID = '" + refid + "' \n");
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            if (rs.next()) {
                userEB = new HP_PAE_UserInfo();
                userEB.setUserid(rs.getString("userid"));
                userEB.setUsername(rs.getString("username"));
                userEB.setPartname(rs.getString("partname"));
                userEB.setCorptel1(rs.getString("corptel1"));
                userEB.setUsergubun(rs.getString("usergubun"));
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception : " + ex);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex2) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex3) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex5) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex6) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex7) {}
        }
        return userEB;
    }
    
    public int getGovUserListCount(final String refid) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        final StringBuffer sql = new StringBuffer();
        int count = 0;
        sql.append("SELECT COUNT(USER_NAME) AS count \n");
        sql.append("FROM UM_ADDED_USER \n");
        sql.append("WHERE USER_ID = '" + refid + "' \n");
        sql.append("AND USER_CLS = 'C' AND NVL(DELETE_YN,'N') <> 'Y' \n");
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            if (rs.next()) {
                count = rs.getInt("count");
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception : " + ex);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex2) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex3) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex5) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex6) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex7) {}
        }
        return count;
    }
    
    public boolean isExistRegistno(final String registno) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        String sql = new String();
        boolean flag = false;
        sql = "\tSELECT\tUSER_NAME\t\t\n\tFROM\t\tUM_ADDED_USER\t\t\n\tWHERE\tUSER_IDENT_NO='" + registno + "'";
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            flag = rs.next();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception : " + ex);
            System.out.println("Error from HP_PAB_GovUserSB.isExistRegistno()");
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex2) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex3) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex5) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex6) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex7) {}
        }
        return flag;
    }
    
    public boolean updatePreIdUserEB(final String userid, final String c_id) {
        boolean flag = false;
        final StringBuffer sql = new StringBuffer();
        sql.append("\tUPDATE\tUM_ADDED_USER\t\t\t\t\t\n");
        sql.append("\tSET\tUSER_ID = '" + c_id + "', UPDATE_DATE=SYSDATE\t\t\n");
        sql.append("\tWHERE\tUSER_NAME='" + userid + "'\t  \t\t\t");
        flag = this.executeUpdate(sql.toString());
        return flag;
    }
    
    public HP_PAE_UserInfo getUserEBBackup(final String userid) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        String sql = new String();
        final HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            stmt = con.createStatement();
            sql = "\tSELECT USER_CLS\t\tAS usergubun\t\t\n\t\t\t,\tUSER_ID\t\t\t\tAS refid\t\t\t\t\n\t\t\t,\tSECRET_KEY\t\t\tAS userpasswd\t\n\t\t\t,\tUSER_FULL_NM\t\t\t\t\tAS username\t\t\n\t\t\t,\tSUBSTR(USER_IDENT_NO, 1, 6)\tAS registno1\t\t\n\t\t\t,\tSUBSTR(USER_IDENT_NO, 7, 7)\tAS registno2\t\t\n\t\t\t,\tSUBSTR(USER_PHONE, 1, INSTR(USER_PHONE, '-') - 1)\t\t\tAS usertel1\t\t\n\t\t\t,\tSUBSTR(USER_PHONE, INSTR(USER_PHONE, '-') + 1, INSTR(USER_PHONE, '-', INSTR(USER_PHONE, '-') + 1) - INSTR(USER_PHONE, '-') - 1)\t\tAS usertel2\t\t\n\t\t\t,\tSUBSTR(USER_PHONE, INSTR(USER_PHONE, '-', INSTR(USER_PHONE, '-') + 1) + 1, LENGTH(USER_PHONE))\t\tAS usertel3\t\t\n\t\t\t,\tSUBSTR(USER_FAX, 1, INSTR(USER_FAX, '-') - 1)\t\t\tAS userfax1\t\t\n\t\t\t,\tSUBSTR(USER_FAX, INSTR(USER_FAX, '-') + 1, INSTR(USER_FAX, '-', INSTR(USER_FAX, '-') + 1) - INSTR(USER_FAX, '-') - 1)\t\tAS userfax2\t\t\n\t\t\t,\tSUBSTR(USER_FAX, INSTR(USER_FAX, '-', INSTR(USER_FAX, '-') + 1) + 1, LENGTH(USER_FAX))\t\tAS userfax3\t\t\n\t\t\t,\tSUBSTR(USER_MOBILE, 1, INSTR(USER_MOBILE, '-') - 1)\t\t\tAS usermobile1\t\t\n\t\t\t,\tSUBSTR(USER_MOBILE, INSTR(USER_MOBILE, '-') + 1, INSTR(USER_MOBILE, '-', INSTR(USER_MOBILE, '-') + 1) - INSTR(USER_MOBILE, '-') - 1)\t\tAS usermobile2\t\t\n\t\t\t,\tSUBSTR(USER_MOBILE, INSTR(USER_MOBILE, '-', INSTR(USER_MOBILE, '-') + 1) + 1, LENGTH(USER_MOBILE))\t\tAS usermobile3\t\t\n\t\t\t,\tUSER_EMAIL\t\t\tAS usermail\t\t\t\n\t\t\t,\tSUBSTR(ZIP_CD, 1, 3)\t\t\tAS postno1\t\t\t\n\t\t\t,\tSUBSTR(ZIP_CD, 5, 3)\t\t\tAS postno2\t\t\t\n\t\t\t,\tUSER_ADDR\t\t\t\t\tAS address1\t\t\t\n\t\t\t,\tUSER_DETAIL_ADDR\t\t\tAS address2\t\t\t\n\t\t\t,\tUSER_COM_NM\t\t\t\tAS enterprise\t\t\n\t\t\t,\tSUBSTR(BIZ_REG_NO, 1, 3)\tAS businessno1\t\t\n\t\t\t,\tSUBSTR(BIZ_REG_NO, 4, 2)\tAS businessno2\t\t\n\t\t\t,\tSUBSTR(BIZ_REG_NO, 6, 5)\tAS businessno3\t\t\n\t\t\t,\tUSER_DEPART\t\t\t\tAS partname\t\t\n\t\t\t,\tUSER_DEPART_CHIEF\t\t\t\tAS partchief\t\t\t\n\t\t\t,\tCER_DN\t\t\tAS authentication\t\n\t\t\t,\tAPPROVE_YN\t\t\tAS recognition\t\t\n\t\t\t,\tSIGNAL_CHANNEL\t\t\tAS preference\t\t\n\t\t\t,\tOCCUPATION\t\t\t\t\tAS job\t\t\t\t\t\n\t\t\t,\tto_char(CREATE_DT, 'YYYYMMDD')\t\t\tAS registdate\t\t\n\t\t\t,\tSUBSTR(본사USER_PHONE, 1, INSTR(본사USER_PHONE, '-') - 1)\t\t\tAS corptel1\t\t\n\t\t\t,\tSUBSTR(본사USER_PHONE, INSTR(본사USER_PHONE, '-') + 1, INSTR(본사USER_PHONE, '-', INSTR(본사USER_PHONE, '-') + 1) - INSTR(본사USER_PHONE, '-') - 1)\t\tAS corptel2\t\t\n\t\t\t,\tSUBSTR(본사USER_PHONE, INSTR(본사USER_PHONE, '-', INSTR(본사USER_PHONE, '-') + 1) + 1, LENGTH(본사USER_PHONE))\t\tAS corptel3\t\t\n\t\t\t,\tCOM_CLS\t\t\tAS entdivide\t\t\n\t\t\t,\tPOSITION\t\t\t\t\tAS position\t\t\t\n\t\t\t,\tGOODS_NAME1\t\t\t\tAS div1\t\t\t\n\t\t\t,\tGOODS_NAME2\t\t\t\tAS div2\t\t\t\n\t\t\t,\tGOODS_NAME3\t\t\t\tAS div3\t\t\t\n\t\t\t,\tGOODS_NAME4\t\t\t\tAS div4\t\t\t\n\t\t\t,\tQUANTITY1\t\t\t\tAS good1\t\t\t\n\t\t\t,\tQUANTITY2\t\t\t\tAS good2\t\t\t\n\t\t\t,\tQUANTITY3\t\t\t\tAS good3\t\t\t\n\t\t\t,\tQUANTITY4\t\t\t\tAS good4\t\t\t\n\t\t\t,\tto_char(CREATE_DT, 'YYYY/MM/DD')\t\t\t\tAS registdate1\t\t\t\n\t\t\t,\tto_char(UPDATE_DATE, 'YYYY/MM/DD')\t\t\t\tAS modifydate\t\t\t\n\tFROM\tUM_ADDED_USER\t\t\n WHERE\tUSER_NAME='" + userid + "' and NVL(DELETE_YN,'N') <> 'Y'\t\t\n";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                userEB.setUserid(userid);
                userEB.setUserpasswd(rs.getString("userpasswd"));
                userEB.setUsergubun(rs.getString("usergubun"));
                userEB.setRefid(rs.getString("refid"));
                userEB.setUsername(rs.getString("username"));
                userEB.setRegistno1(rs.getString("registno1"));
                userEB.setRegistno2(rs.getString("registno2"));
                userEB.setUsertel1(rs.getString("usertel1"));
                userEB.setUsertel2(rs.getString("usertel2"));
                userEB.setUsertel3(rs.getString("usertel3"));
                userEB.setUserfax1(rs.getString("userfax1"));
                userEB.setUserfax2(rs.getString("userfax2"));
                userEB.setUserfax3(rs.getString("userfax3"));
                userEB.setUsermobile1(rs.getString("usermobile1"));
                userEB.setUsermobile2(rs.getString("usermobile2"));
                userEB.setUsermobile3(rs.getString("usermobile3"));
                userEB.setUsermail(rs.getString("usermail"));
                userEB.setPostno1(rs.getString("postno1"));
                userEB.setPostno2(rs.getString("postno2"));
                userEB.setAddress1(rs.getString("address1"));
                userEB.setAddress2(rs.getString("address2"));
                userEB.setPreference(rs.getString("preference"));
                userEB.setJob(rs.getString("job"));
                userEB.setEnterprise(rs.getString("enterprise"));
                userEB.setBusinessno1(rs.getString("businessno1"));
                userEB.setBusinessno2(rs.getString("businessno2"));
                userEB.setBusinessno3(rs.getString("businessno3"));
                userEB.setPartname(rs.getString("partname"));
                userEB.setPartchief(rs.getString("partchief"));
                userEB.setPosition(rs.getString("position"));
                userEB.setEntdivide(rs.getString("entdivide"));
                userEB.setCorptel1(rs.getString("corptel1"));
                userEB.setCorptel2(rs.getString("corptel2"));
                userEB.setCorptel3(rs.getString("corptel3"));
                userEB.setDiv1(rs.getString("div1"));
                userEB.setDiv2(rs.getString("div2"));
                userEB.setDiv3(rs.getString("div3"));
                userEB.setDiv4(rs.getString("div4"));
                userEB.setGood1(rs.getString("good1"));
                userEB.setGood2(rs.getString("good2"));
                userEB.setGood3(rs.getString("good3"));
                userEB.setGood4(rs.getString("good4"));
                userEB.setRegistdate(rs.getString("registdate"));
                userEB.setRegistdate1(rs.getString("registdate1"));
                userEB.setModifydate(rs.getString("modifydate"));
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception : " + ex);
            System.out.println("Error from HP_PAB_UserSB.getUserEB()");
            return null;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex2) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex3) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex5) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex6) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex7) {}
        }
        return userEB;
    }
    
    public HP_PAE_UserInfo getUserEB(final String userid) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        String sql = new String();
        final HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            stmt = con.createStatement();
            sql = "\tSELECT USER_CLS\t\tAS usergubun\t\t\n\t\t\t,\tUSER_ID\t\t\t\tAS refid\t\t\t\t\n\t\t\t,\tSECRET_KEY\t\t\tAS userpasswd\t\n\t\t\t,\tUSER_FULL_NM\t\t\t\t\tAS username\t\t\n\t\t\t,\tUSER_IDENT_NO\tAS registno1\t\t\n\t\t\t,\tUSER_PHONE\t\tAS usertel1\t\t\n\t\t\t,\tUSER_FAX\t\tAS userfax1\t\t\n\t\t\t,\tUSER_MOBILE\t\tAS usermobile1\t\t\n\t\t\t,\tUSER_EMAIL\t\tAS usermail\t\t\t\n\t\t\t,\tZIP_CD\t\t\tAS postno1\t\t\t\n\t\t\t,\tUSER_ADDR\t\tAS address1\t\t\t\n\t\t\t,\tUSER_DETAIL_ADDR\t\tAS address2\t\t\t\n\t\t\t,\tUSER_COM_NM\t\t\tAS enterprise\t\t\n\t\t\t,\tBIZ_REG_NO\t\t\tAS businessno1\t\t\n\t\t\t,\tUSER_DEPART\t\t\tAS partname\t\t\n\t\t\t,\tUSER_DEPART_CHIEF\t\tAS partchief\t\t\t\n\t\t\t,\tCER_DN\t\t\tAS authentication\t\n\t\t\t,\tAPPROVE_YN\t\t\tAS recognition\t\t\n\t\t\t,\tSIGNAL_CHANNEL\t\t\tAS preference\t\t\n\t\t\t,\tOCCUPATION\t\t\t\t\tAS job\t\t\t\t\t\n\t\t\t,\tto_char(CREATE_DT, 'YYYYMMDD')\t\t\tAS registdate\t\t\n\t\t\t,\tUSER_PHONE\t\t\tAS corptel1\t\t\n\t\t\t,\tCOM_CLS\t\t\tAS entdivide\t\t\n\t\t\t,\tPOSITION\t\t\t\t\tAS position\t\t\t\n\t\t\t,\tGOODS_NAME1\t\t\t\tAS div1\t\t\t\n\t\t\t,\tGOODS_NAME2\t\t\t\tAS div2\t\t\t\n\t\t\t,\tGOODS_NAME3\t\t\t\tAS div3\t\t\t\n\t\t\t,\tGOODS_NAME4\t\t\t\tAS div4\t\t\t\n\t\t\t,\tQUANTITY1\t\t\t\tAS good1\t\t\t\n\t\t\t,\tQUANTITY2\t\t\t\tAS good2\t\t\t\n\t\t\t,\tQUANTITY3\t\t\t\tAS good3\t\t\t\n\t\t\t,\tQUANTITY4\t\t\t\tAS good4\t\t\t\n\t\t\t,\tto_char(CREATE_DT, 'YYYY/MM/DD')\t\t\t\tAS registdate1\t\t\t\n\t\t\t,\tto_char(UPDATE_DATE, 'YYYY/MM/DD')\t\t\t\tAS modifydate\t\t\t\n\tFROM\tUM_ADDED_USER\t\t\n WHERE\tUSER_NAME='" + userid + "' and NVL(DELETE_YN,'N') <> 'Y'\t\t\n";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                userEB.setUserid(userid);
                userEB.setUserpasswd(rs.getString("userpasswd"));
                userEB.setUsergubun(rs.getString("usergubun"));
                userEB.setRefid(rs.getString("refid"));
                userEB.setUsername(rs.getString("username"));
                userEB.setRegistno1(rs.getString("registno1"));
                userEB.setUsertel1(rs.getString("usertel1"));
                userEB.setUserfax1(rs.getString("userfax1"));
                userEB.setUsermobile1(rs.getString("usermobile1"));
                userEB.setUsermail(rs.getString("usermail"));
                userEB.setPostno1(rs.getString("postno1"));
                userEB.setAddress1(rs.getString("address1"));
                userEB.setAddress2(rs.getString("address2"));
                userEB.setPreference(rs.getString("preference"));
                userEB.setJob(rs.getString("job"));
                userEB.setEnterprise(rs.getString("enterprise"));
                userEB.setBusinessno1(rs.getString("businessno1"));
                userEB.setPartname(rs.getString("partname"));
                userEB.setPartchief(rs.getString("partchief"));
                userEB.setPosition(rs.getString("position"));
                userEB.setEntdivide(rs.getString("entdivide"));
                userEB.setCorptel1(rs.getString("corptel1"));
                userEB.setDiv1(rs.getString("div1"));
                userEB.setDiv2(rs.getString("div2"));
                userEB.setDiv3(rs.getString("div3"));
                userEB.setDiv4(rs.getString("div4"));
                userEB.setGood1(rs.getString("good1"));
                userEB.setGood2(rs.getString("good2"));
                userEB.setGood3(rs.getString("good3"));
                userEB.setGood4(rs.getString("good4"));
                userEB.setRegistdate(rs.getString("registdate"));
                userEB.setRegistdate1(rs.getString("registdate1"));
                userEB.setModifydate(rs.getString("modifydate"));
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception : " + ex);
            System.out.println("Error from HP_PAB_UserSB.getUserEB()");
            return null;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex2) {}
            }
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (Exception ex3) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex4) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex5) {}
        }
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex6) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex7) {}
        }
        return userEB;
    }
}
