// 
// Decompiled by Procyon v0.5.30
// 

package user;

import secu.lib.MessageDigest;
import secu.lib.Secu;
import java.util.StringTokenizer;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import common.Log;
import java.sql.SQLException;
import common.Trx;
import java.util.Vector;

public class HP_PAB_UserSB
{
    public boolean insertUserEB(final HP_PAE_UserInfo userEB) {
        boolean flag = false;
        final StringBuffer sql = new StringBuffer();
        final Vector data = new Vector();
        sql.append("\tINSERT INTO 사용_부가사용자\t\n");
        sql.append("\t\t(아이디,\t\n");
        sql.append("\t\t이용자구분,\t\n");
        sql.append("\t\t비밀번호,\t\n");
        sql.append("\t\t이용자ID,\t\n");
        sql.append("\t\t성명,\t\n");
        sql.append("\t\t주민등록번호,\t\n");
        sql.append("\t\t전화번호,\t\n");
        sql.append("\t\t팩스번호,\t\n");
        sql.append("\t\t휴대폰번호,\t\n");
        sql.append("\t\t메일주소,\t\n");
        sql.append("\t\t우편번호,\t\n");
        sql.append("\t\t주소,\t\n");
        sql.append("\t\t상세주소,\t\n");
        sql.append("\t\t선호채널,\t\n");
        sql.append("\t\t승인여부,\t\n");
        sql.append("\t\t직업,\t\n");
        sql.append("\t\t삭제여부,\t\n");
        sql.append("\t\t등록일자,\t\n");
        sql.append("\t\t변경일자\t\t\n");
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
        sql.append("\t\t'N',\t\t\n");
        sql.append("\t\tsysdate,\t\n");
        sql.append("\t\tsysdate)\t\n");
        data.add(userEB.getUserid());
        data.add(userEB.getUsergubun());
        data.add(userEB.getUserpasswd());
        data.add(userEB.getRefid());
        data.add(userEB.getUsername());
        data.add(String.valueOf(userEB.getRegistno1()) + userEB.getRegistno2());
        if (!"".equals(userEB.getUsertel1()) || !"".equals(userEB.getUsertel2()) || !"".equals(userEB.getUsertel3())) {
            data.add(String.valueOf(userEB.getUsertel1()) + "-" + userEB.getUsertel2() + "-" + userEB.getUsertel3());
        }
        else {
            data.add(" ");
        }
        if (!"".equals(userEB.getUserfax1()) || !"".equals(userEB.getUserfax2()) || !"".equals(userEB.getUserfax3())) {
            data.add(String.valueOf(userEB.getUserfax1()) + "-" + userEB.getUserfax2() + "-" + userEB.getUserfax3());
        }
        else {
            data.add("");
        }
        if (!"".equals(userEB.getUsermobile1()) || !"".equals(userEB.getUsermobile2()) || !"".equals(userEB.getUsermobile3())) {
            data.add(String.valueOf(userEB.getUsermobile1()) + "-" + userEB.getUsermobile2() + "-" + userEB.getUsermobile3());
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
        if (userEB.getPostno1() != null && userEB.getPostno2() != null) {
            data.add(String.valueOf(userEB.getPostno1()) + "-" + userEB.getPostno2());
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
        if (userEB.getAddress2() != null) {
            data.add(userEB.getAddress2());
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
        if (userEB.getRecognition() != null) {
            data.add(userEB.getRecognition());
        }
        else {
            data.add("");
        }
        if (userEB.getJob() != null) {
            data.add(userEB.getJob());
        }
        else {
            data.add("");
        }
        flag = this.executeQuery(sql, data);
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
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            stmt = con.createStatement();
            sql = "\tSELECT 이용자구분\t\tAS usergubun\t\t\n\t\t\t,\t이용자ID\t\t\t\tAS refid\t\t\t\t\n\t\t\t,\t비밀번호\t\t\tAS userpasswd\t\n\t\t\t,\t성명\t\t\t\t\tAS username\t\t\n\t\t\t,\tSUBSTR(주민등록번호, 1, 6)\tAS registno1\t\t\n\t\t\t,\tSUBSTR(주민등록번호, 7, 7)\tAS registno2\t\t\n\t\t\t,\tSUBSTR(전화번호, 1, INSTR(전화번호, '-') - 1)\t\t\tAS usertel1\t\t\n\t\t\t,\tSUBSTR(전화번호, INSTR(전화번호, '-') + 1, INSTR(전화번호, '-', INSTR(전화번호, '-') + 1) - INSTR(전화번호, '-') - 1)\t\tAS usertel2\t\t\n\t\t\t,\tSUBSTR(전화번호, INSTR(전화번호, '-', INSTR(전화번호, '-') + 1) + 1, LENGTH(전화번호))\t\tAS usertel3\t\t\n\t\t\t,\tSUBSTR(팩스번호, 1, INSTR(팩스번호, '-') - 1)\t\t\tAS userfax1\t\t\n\t\t\t,\tSUBSTR(팩스번호, INSTR(팩스번호, '-') + 1, INSTR(팩스번호, '-', INSTR(팩스번호, '-') + 1) - INSTR(팩스번호, '-') - 1)\t\tAS userfax2\t\t\n\t\t\t,\tSUBSTR(팩스번호, INSTR(팩스번호, '-', INSTR(팩스번호, '-') + 1) + 1, LENGTH(팩스번호))\t\tAS userfax3\t\t\n\t\t\t,\tSUBSTR(휴대폰번호, 1, INSTR(휴대폰번호, '-') - 1)\t\t\tAS usermobile1\t\t\n\t\t\t,\tSUBSTR(휴대폰번호, INSTR(휴대폰번호, '-') + 1, INSTR(휴대폰번호, '-', INSTR(휴대폰번호, '-') + 1) - INSTR(휴대폰번호, '-') - 1)\t\tAS usermobile2\t\t\n\t\t\t,\tSUBSTR(휴대폰번호, INSTR(휴대폰번호, '-', INSTR(휴대폰번호, '-') + 1) + 1, LENGTH(휴대폰번호))\t\tAS usermobile3\t\t\n\t\t\t,\t메일주소\t\t\tAS usermail\t\t\t\n\t\t\t,\tSUBSTR(우편번호, 1, 3)\t\t\tAS postno1\t\t\t\n\t\t\t,\tSUBSTR(우편번호, 5, 3)\t\t\tAS postno2\t\t\t\n\t\t\t,\t주소\t\t\t\t\tAS address1\t\t\t\n\t\t\t,\t상세주소\t\t\tAS address2\t\t\t\n\t\t\t,\t상호명\t\t\t\tAS enterprise\t\t\n\t\t\t,\tSUBSTR(사업자등록번호, 1, 3)\tAS businessno1\t\t\n\t\t\t,\tSUBSTR(사업자등록번호, 4, 2)\tAS businessno2\t\t\n\t\t\t,\tSUBSTR(사업자등록번호, 6, 5)\tAS businessno3\t\t\n\t\t\t,\t부서명\t\t\t\tAS partname\t\t\n\t\t\t,\t부서장\t\t\t\tAS partchief\t\t\t\n\t\t\t,\t인증서DN\t\t\tAS authentication\t\n\t\t\t,\t승인여부\t\t\tAS recognition\t\t\n\t\t\t,\t선호채널\t\t\tAS preference\t\t\n\t\t\t,\t직업\t\t\t\t\tAS job\t\t\t\t\t\n\t\t\t,\tto_char(등록일자, 'YYYYMMDD')\t\t\tAS registdate\t\t\n\t\t\t,\tSUBSTR(본사전화번호, 1, INSTR(본사전화번호, '-') - 1)\t\t\tAS corptel1\t\t\n\t\t\t,\tSUBSTR(본사전화번호, INSTR(본사전화번호, '-') + 1, INSTR(본사전화번호, '-', INSTR(본사전화번호, '-') + 1) - INSTR(본사전화번호, '-') - 1)\t\tAS corptel2\t\t\n\t\t\t,\tSUBSTR(본사전화번호, INSTR(본사전화번호, '-', INSTR(본사전화번호, '-') + 1) + 1, LENGTH(본사전화번호))\t\tAS corptel3\t\t\n\t\t\t,\t업체구분\t\t\tAS entdivide\t\t\n\t\t\t,\t직책\t\t\t\t\tAS position\t\t\t\n\t\t\t,\t품명1\t\t\t\tAS div1\t\t\t\n\t\t\t,\t품명2\t\t\t\tAS div2\t\t\t\n\t\t\t,\t품명3\t\t\t\tAS div3\t\t\t\n\t\t\t,\t품명4\t\t\t\tAS div4\t\t\t\n\t\t\t,\t수량1\t\t\t\tAS good1\t\t\t\n\t\t\t,\t수량2\t\t\t\tAS good2\t\t\t\n\t\t\t,\t수량3\t\t\t\tAS good3\t\t\t\n\t\t\t,\t수량4\t\t\t\tAS good4\t\t\t\n\t\t\t,\tto_char(등록일자, 'YYYY/MM/DD')\t\t\t\tAS registdate1\t\t\t\n\t\t\t,\tto_char(변경일자, 'YYYY/MM/DD')\t\t\t\tAS modifydate\t\t\t\n\tFROM\t사용_부가사용자\t\t\n WHERE\t아이디='" + userid + "'\t\t\n";
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
            Log.debug(String.valueOf(this.getClass().getName()) + "getUserEB() 아이디[" + userid + "] : " + ex.toString());
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
    
    public boolean updateUserEB(final HP_PAE_UserInfo userEB) {
        boolean flag = false;
        String sql = new String();
        sql = "\tUPDATE 사용_부가사용자\t\t\n\tSET\t\t\t\n\t\t\t이용자구분='" + userEB.getUsergubun() + "'\t\n";
        if (userEB.getUserpasswd() != null) {
            sql = String.valueOf(sql) + "\t\t,\t비밀번호='" + userEB.getUserpasswd() + "'\t\n";
        }
        if (!"".equals(userEB.getUsertel1()) || !"".equals(userEB.getUsertel2()) || !"".equals(userEB.getUsertel3())) {
            sql = String.valueOf(sql) + "\t\t,\t전화번호='" + userEB.getUsertel1() + "-" + userEB.getUsertel2() + "-" + userEB.getUsertel3() + "'\t\n";
        }
        else {
            sql = String.valueOf(sql) + "\t\t,\t전화번호 = ' '\t\n";
        }
        if (!"".equals(userEB.getUserfax1()) || !"".equals(userEB.getUserfax2()) || !"".equals(userEB.getUserfax3())) {
            sql = String.valueOf(sql) + "\t\t,\t팩스번호='" + userEB.getUserfax1() + "-" + userEB.getUserfax2() + "-" + userEB.getUserfax3() + "'\t\n";
        }
        else {
            sql = String.valueOf(sql) + "\t\t,\t팩스번호 = ''\t\n";
        }
        if (!"".equals(userEB.getUsermobile1()) || !"".equals(userEB.getUsermobile2()) || !"".equals(userEB.getUsermobile3())) {
            sql = String.valueOf(sql) + "\t\t,\t휴대폰번호='" + userEB.getUsermobile1() + "-" + userEB.getUsermobile2() + "-" + userEB.getUsermobile3() + "'\t\n";
        }
        else {
            sql = String.valueOf(sql) + "\t\t,\t휴대폰번호 = ''\t\n";
        }
        if (userEB.getUsermail() != null) {
            sql = String.valueOf(sql) + "\t\t,\t메일주소='" + userEB.getUsermail() + "'\t\n";
        }
        if (userEB.getPostno1() != null && userEB.getPostno2() != null) {
            sql = String.valueOf(sql) + "\t\t,\t우편번호='" + userEB.getPostno1() + "-" + userEB.getPostno2() + "'\t\n";
        }
        if (userEB.getAddress1() != null) {
            sql = String.valueOf(sql) + "\t\t,\t주소='" + userEB.getAddress1() + "'\t\n";
        }
        if (userEB.getAddress2() != null) {
            sql = String.valueOf(sql) + "\t\t,\t상세주소='" + userEB.getAddress2() + "'\t\n";
        }
        if (userEB.getPreference() != null) {
            sql = String.valueOf(sql) + "\t\t,\t선호채널='" + userEB.getPreference() + "'\t\n";
        }
        if (userEB.getJob() != null) {
            sql = String.valueOf(sql) + "\t\t,\t직업='" + userEB.getJob() + "'\t\n";
        }
        sql = String.valueOf(sql) + "\t\t,\t변경일자=SYSDATE\t\n";
        sql = String.valueOf(sql) + "\t\tWHERE\t아이디='" + userEB.getUserid() + "'\t\n";
        flag = this.executeUpdate(sql);
        return flag;
    }
    
    public boolean deleteUser(final String userid, final String userpasswd) {
        String sql = new String();
        final boolean flag = false;
        sql = "DELETE FROM 사용_부가사용자 WHERE 아이디 = '" + userid + "'";
        return this.executeUpdate(sql);
    }
    
    public boolean deleteUser(final String userIDs) {
        String sql = new String();
        boolean flag = false;
        final StringTokenizer st = new StringTokenizer(userIDs, ":");
        sql = "DELETE FROM 사용_부가사용자 \tWHERE\t아이디\tIN\t\t(\n\t";
        int i = 0;
        while (st.hasMoreTokens()) {
            final String id = st.nextToken();
            if (i == 0) {
                sql = String.valueOf(sql) + "'" + id + "'";
            }
            else {
                sql = String.valueOf(sql) + ", '" + id + "'";
            }
            ++i;
        }
        sql = String.valueOf(sql) + "\n\t)\t\n";
        flag = this.executeUpdate(sql);
        return flag;
    }
    
    public boolean isExistID(final String userid) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        final StringBuffer sql = new StringBuffer();
        boolean flag = false;
        sql.append("\tSELECT\tUSER_NAME\t\t\n");
        sql.append("\tFROM\t\tUM_ADDED_USER\t\t\n");
        sql.append("\tWHERE\t \tUSER_NAME='" + userid + "'");
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            flag = rs.next();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + "isExistID() USER_NAME[" + userid + "] : " + ex.toString());
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
    
    public String isExistRegistnoDash(final String registno) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        String sql = new String();
        String flag = new String();
        sql = "\tSELECT\t아이디\t\tid,\t\t\n\t\t\t\t\t삭제여부\tisRmv\t\t\n\tFROM\t\t사용_부가사용자\t\t\n\tWHERE\t\t주민등록번호='" + registno + "'";
        Label_0360: {
            try {
                trx = new Trx(this, "usemn");
                con = trx.getConnection();
                stmt = con.createStatement();
                rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    flag = String.valueOf(rs.getString("id")) + "/" + rs.getString("isRmv");
                    break Label_0360;
                }
                flag = "";
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
            catch (Exception ex) {
                Log.debug(String.valueOf(this.getClass().getName()) + "isExistRegistnoDash() 주민등록번호[" + registno + "] : " + ex.toString());
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
    
    public boolean isExistRegistNo(final String registno) {
        final boolean flag = false;
        return flag;
    }
    
    public boolean isExistRegistno(final String registno) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        String sql = new String();
        boolean flag = false;
        sql = "\tSELECT\t아이디\t\t\n\tFROM\t\t사용_부가사용자\t\t\n\tWHERE\t주민등록번호='" + registno + "'";
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            flag = rs.next();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + "isExistRegistno() 주민등록번호[" + registno + "] : " + ex.toString());
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
    
    public int getUserListCount(final String usergubun, final String gubun, final String word) {
        final StringBuffer sql = new StringBuffer();
        sql.append("\tSELECT\tCOUNT(아이디)\tAS count\t\n");
        sql.append("\tFROM\t\t사용_부가사용자\t\t\n");
        if (gubun.equals("name")) {
            sql.append("\tWHERE\t성명\tLIKE\t '%" + word + "%'\t\n");
        }
        else if (gubun.equals("business")) {
            sql.append("\tWHERE\t\t\t사업자등록번호 LIKE '%" + word + "%'\t\n");
        }
        else if (gubun.equals("regdate")) {
            sql.append("WHERE\tto_char(등록일자, 'YYYYMMDD')='" + word + "'\t\n");
        }
        else if (gubun.equals("moddate")) {
            sql.append("WHERE\tto_char(변경일자, 'YYYYMMDD')='" + word + "'\t\n");
        }
        else {
            sql.append("\tWHERE\t\t\t아이디\tLIKE\t'%" + word + "%'\t\n");
        }
        if (!usergubun.equals("T")) {
            sql.append("\tAND\t\t\t이용자구분='" + usergubun + "'\t");
        }
        return this.getListCount(sql.toString());
    }
    
    public HP_PAE_UserList getUserListEB(final String usergubun, final String gubun, final String word, final int size, final int start) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        final HP_PAE_UserList userlist = new HP_PAE_UserList(size);
        String sql = new String();
        sql = "\tSELECT\t\t\t\t\t\t\t\t\n\t\tROWNUM\t\t\t\t rnum,\t\t\t\n\t\t아이디\t \t\t\t userid,\t\t\n\t\t성명\t\t \t\t username,\t\t\n\t\t이용자구분\t\t \t usergubun,\t\t\n\t\tSUBSTR(사업자등록번호, 1, 3)\t businessno1,\t\t\n\t\tSUBSTR(사업자등록번호, 4, 2)\t businessno2,\t\t\n\t\tSUBSTR(사업자등록번호, 6, 5)\t businessno3,\t\t\n\t\tSUBSTR(주민등록번호, 1, 6)\t registno1,\t\t\n\t\tSUBSTR(주민등록번호, 7, 7)\t registno2,\t\t\n\t\t삭제여부\t\t\t\t deleted,\t\t\n\t\tto_char(등록일자, 'YYYY/MM/DD')\t registdate,\t\t\n\t\tto_char(변경일자, 'YYYY/MM/DD')\t modifydate\t\t\n\tFROM\t사용_부가사용자\t\t\t\t\t\t\n";
        if (!usergubun.equals("T")) {
            sql = String.valueOf(sql) + "\t WHERE\t이용자구분 = '" + usergubun + "'\t\t\t\t\n";
        }
        else {
            sql = String.valueOf(sql) + "\t WHERE  1 = 1\t\t\t\t\t\t\t\n";
        }
        if (gubun.equals("name")) {
            sql = String.valueOf(sql) + "  AND\t성명\tLIKE\t '%" + word + "%'\t\t\t\n";
        }
        else if (gubun.equals("business")) {
            sql = String.valueOf(sql) + "  AND\t사업자등록번호 LIKE '%" + word + "%'\t\t\t\n";
        }
        else if (gubun.equals("regdate")) {
            sql = String.valueOf(sql) + "  AND\tto_char(등록일자, 'YYYYMMDD')='" + word + "'\t\t\n";
        }
        else if (gubun.equals("moddate")) {
            sql = String.valueOf(sql) + "  AND\tto_char(변경일자, 'YYYYMMDD')='" + word + "'\t\t\n";
        }
        else if (gubun.equals("id")) {
            sql = String.valueOf(sql) + "  AND\t아이디\tLIKE\t'%" + word + "%'\t\t\t\n";
        }
        sql = "\tSELECT\t* FROM\t(" + sql + ") \tWHERE rnum BETWEEN " + start + " AND " + (start + size - 1);
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                final HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
                userEB.setUserid(rs.getString("userid"));
                userEB.setUsername(rs.getString("username"));
                userEB.setUsergubun(rs.getString("usergubun"));
                userEB.setBusinessno1(rs.getString("businessno1"));
                userEB.setBusinessno2(rs.getString("businessno2"));
                userEB.setBusinessno3(rs.getString("businessno3"));
                userEB.setRegistno1(rs.getString("registno1"));
                userEB.setRegistno2(rs.getString("registno2"));
                userEB.setRegistdate(rs.getString("registdate"));
                userEB.setModifydate(rs.getString("modifydate"));
                userEB.setDelete(rs.getString("deleted"));
                userlist.add(userEB);
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + "getUserListEB() 이용자구분[" + usergubun + "] 검색조건[" + gubun + "] 검색어[" + word + "] : " + ex.toString());
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
    
    public int getListCount(final String sql) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        int count = 0;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt("count");
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + "getListCount() sql[" + sql + "] : " + ex.toString());
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
    
    public String getUserId(final String username, final String registno) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        final StringBuffer sql = new StringBuffer();
        String userid = new String();
        sql.append("\tSELECT\t아이디 AS userid\t \n");
        sql.append("\tFROM\t\t사용_부가사용자\t\n");
        sql.append("\tWHERE\t\t성명='" + username + "'\t\n");
        sql.append("\tAND\t\t\t주민등록번호='" + registno + "'");
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            if (rs.next()) {
                userid = rs.getString("userid");
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + "getUserId() 성명[" + username + "] 주민등록번호[" + registno + "] : " + ex.toString());
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
        return userid;
    }
    
    public Vector getUserPw(final String username, final String registno, final String usermail) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        final StringBuffer sql = new StringBuffer();
        final Vector vc = new Vector();
        sql.append("\tSELECT\t비밀번호 AS userpasswd\t \n");
        sql.append("\t\t\t\t,\t메일주소 AS email\t \n");
        sql.append("\t\t\t\t,\t아이디 AS userid\t\n");
        sql.append("\tFROM\t\t사용_부가사용자\t\n");
        sql.append("\tWHERE\t\t성명='" + username + "'\t\n");
        sql.append("\tAND\t\t\t주민등록번호='" + registno + "'");
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            if (rs.next()) {
                vc.add(rs.getString("userid"));
                vc.add(rs.getString("userpasswd"));
                vc.add(rs.getString("email"));
            }
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        catch (Exception ex) {
            Log.debug(String.valueOf(this.getClass().getName()) + "getUserPw() 성명[" + username + "] 주민등록번호[" + registno + "] : " + ex.toString());
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
        return vc;
    }
    
    public boolean updateUserPw(final String userid, final String registno1, final String registno2) {
        String sql = new String();
        boolean flag = false;
        String userpasswd = "";
        final Secu secu = new Secu(1);
        final MessageDigest md = new MessageDigest(secu);
        userpasswd = md.create(registno2);
        sql = " UPDATE 사용_부가사용자  SET 비밀번호 = '" + userpasswd + "', 변경일자 = SYSDATE " + " WHERE 아이디 = '" + userid + "' " + " AND 주민등록번호 = '" + registno1.concat(registno2) + "' ";
        flag = this.executeUpdate(sql);
        return flag;
    }
    
    public boolean executeQuery(final StringBuffer sql, final Vector data) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3        /* trx */
        //     2: aconst_null    
        //     3: astore          con
        //     5: aconst_null    
        //     6: astore          rs
        //     8: aconst_null    
        //     9: astore          pstmt
        //    11: new             Lcommon/Trx;
        //    14: dup            
        //    15: aload_0         /* this */
        //    16: invokespecial   common/Trx.<init>:(Ljava/lang/Object;)V
        //    19: astore_3        /* trx */
        //    20: aload_3         /* trx */
        //    21: invokevirtual   common/Trx.getConnection:()Ljava/sql/Connection;
        //    24: astore          con
        //    26: aload           con
        //    28: aload_1         /* sql */
        //    29: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    32: invokeinterface java/sql/Connection.prepareStatement:(Ljava/lang/String;)Ljava/sql/PreparedStatement;
        //    37: astore          pstmt
        //    39: iconst_1       
        //    40: istore          i
        //    42: goto            68
        //    45: aload           pstmt
        //    47: iload           i
        //    49: aload_2         /* data */
        //    50: iload           i
        //    52: iconst_1       
        //    53: isub           
        //    54: invokevirtual   java/util/Vector.get:(I)Ljava/lang/Object;
        //    57: checkcast       Ljava/lang/String;
        //    60: invokeinterface java/sql/PreparedStatement.setString:(ILjava/lang/String;)V
        //    65: iinc            i, 1
        //    68: iload           i
        //    70: aload_2         /* data */
        //    71: invokevirtual   java/util/Vector.size:()I
        //    74: if_icmple       45
        //    77: aload           pstmt
        //    79: invokeinterface java/sql/PreparedStatement.executeUpdate:()I
        //    84: pop            
        //    85: aload           con
        //    87: invokeinterface java/sql/Connection.commit:()V
        //    92: goto            244
        //    95: astore          se
        //    97: aload           se
        //    99: invokevirtual   java/sql/SQLException.printStackTrace:()V
        //   102: aload           pstmt
        //   104: ifnull          119
        //   107: aload           pstmt
        //   109: invokeinterface java/sql/PreparedStatement.close:()V
        //   114: goto            119
        //   117: astore          9
        //   119: aload           con
        //   121: ifnull          133
        //   124: aload_3         /* trx */
        //   125: invokevirtual   common/Trx.close:()V
        //   128: goto            133
        //   131: astore          9
        //   133: iconst_0       
        //   134: ireturn        
        //   135: astore          ex
        //   137: aload           ex
        //   139: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   142: new             Ljava/lang/StringBuffer;
        //   145: dup            
        //   146: aload_0         /* this */
        //   147: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   150: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   153: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   156: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   159: ldc_w           "executeQuery() sql["
        //   162: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   165: aload_1         /* sql */
        //   166: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   169: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   172: ldc_w           "] data["
        //   175: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   178: aload_2         /* data */
        //   179: invokevirtual   java/util/Vector.size:()I
        //   182: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   185: ldc_w           "] : "
        //   188: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   191: aload           ex
        //   193: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   196: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   199: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   202: invokestatic    common/Log.debug:(Ljava/lang/String;)V
        //   205: goto            102
        //   208: astore          8
        //   210: aload           pstmt
        //   212: ifnull          227
        //   215: aload           pstmt
        //   217: invokeinterface java/sql/PreparedStatement.close:()V
        //   222: goto            227
        //   225: astore          9
        //   227: aload           con
        //   229: ifnull          241
        //   232: aload_3         /* trx */
        //   233: invokevirtual   common/Trx.close:()V
        //   236: goto            241
        //   239: astore          9
        //   241: aload           8
        //   243: athrow         
        //   244: aload           pstmt
        //   246: ifnull          261
        //   249: aload           pstmt
        //   251: invokeinterface java/sql/PreparedStatement.close:()V
        //   256: goto            261
        //   259: astore          9
        //   261: aload           con
        //   263: ifnull          275
        //   266: aload_3         /* trx */
        //   267: invokevirtual   common/Trx.close:()V
        //   270: goto            275
        //   273: astore          9
        //   275: iconst_1       
        //   276: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name   Signature
        //  -----  ------  ----  -----  ----------------------------
        //  0      277     0     this   Luser/HP_PAB_UserSB;
        //  0      277     1     sql    Ljava/lang/StringBuffer;
        //  0      277     2     data   Ljava/util/Vector;
        //  2      275     3     trx    Lcommon/Trx;
        //  5      272     4     con    Ljava/sql/Connection;
        //  8      269     5     rs     Ljava/sql/ResultSet;
        //  11     266     6     pstmt  Ljava/sql/PreparedStatement;
        //  42     35      7     i      I
        //  97     5       7     se     Ljava/sql/SQLException;
        //  137    71      7     ex     Ljava/lang/Exception;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  11     92     95     102    Ljava/sql/SQLException;
        //  107    114    117    119    Ljava/lang/Exception;
        //  124    128    131    133    Ljava/lang/Exception;
        //  11     92     135    208    Ljava/lang/Exception;
        //  11     102    208    244    Any
        //  135    208    208    244    Any
        //  215    222    225    227    Ljava/lang/Exception;
        //  232    236    239    241    Ljava/lang/Exception;
        //  249    256    259    261    Ljava/lang/Exception;
        //  266    270    273    275    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 118, Size: 118
        //     at java.util.ArrayList.rangeCheck(Unknown Source)
        //     at java.util.ArrayList.get(Unknown Source)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:123)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean executeUpdate(final String sql) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2        /* trx */
        //     2: aconst_null    
        //     3: astore_3        /* con */
        //     4: aconst_null    
        //     5: astore          stmt
        //     7: new             Lcommon/Trx;
        //    10: dup            
        //    11: aload_0         /* this */
        //    12: ldc             "usemn"
        //    14: invokespecial   common/Trx.<init>:(Ljava/lang/Object;Ljava/lang/String;)V
        //    17: astore_2        /* trx */
        //    18: aload_2         /* trx */
        //    19: invokevirtual   common/Trx.getConnection:()Ljava/sql/Connection;
        //    22: astore_3        /* con */
        //    23: aload_3         /* con */
        //    24: invokeinterface java/sql/Connection.createStatement:()Ljava/sql/Statement;
        //    29: astore          stmt
        //    31: aload           stmt
        //    33: aload_1         /* sql */
        //    34: invokeinterface java/sql/Statement.executeUpdate:(Ljava/lang/String;)I
        //    39: pop            
        //    40: goto            169
        //    43: astore          se
        //    45: aload           se
        //    47: invokevirtual   java/sql/SQLException.printStackTrace:()V
        //    50: aload           stmt
        //    52: ifnull          67
        //    55: aload           stmt
        //    57: invokeinterface java/sql/Statement.close:()V
        //    62: goto            67
        //    65: astore          7
        //    67: aload_3         /* con */
        //    68: ifnull          80
        //    71: aload_2         /* trx */
        //    72: invokevirtual   common/Trx.close:()V
        //    75: goto            80
        //    78: astore          7
        //    80: iconst_0       
        //    81: ireturn        
        //    82: astore          ex
        //    84: new             Ljava/lang/StringBuffer;
        //    87: dup            
        //    88: aload_0         /* this */
        //    89: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    92: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    95: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    98: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   101: ldc_w           "executeUpdate() sql["
        //   104: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   107: aload_1         /* sql */
        //   108: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   111: ldc_w           "] : "
        //   114: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   117: aload           ex
        //   119: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   122: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   125: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   128: invokestatic    common/Log.debug:(Ljava/lang/String;)V
        //   131: goto            50
        //   134: astore          6
        //   136: aload           stmt
        //   138: ifnull          153
        //   141: aload           stmt
        //   143: invokeinterface java/sql/Statement.close:()V
        //   148: goto            153
        //   151: astore          7
        //   153: aload_3         /* con */
        //   154: ifnull          166
        //   157: aload_2         /* trx */
        //   158: invokevirtual   common/Trx.close:()V
        //   161: goto            166
        //   164: astore          7
        //   166: aload           6
        //   168: athrow         
        //   169: aload           stmt
        //   171: ifnull          186
        //   174: aload           stmt
        //   176: invokeinterface java/sql/Statement.close:()V
        //   181: goto            186
        //   184: astore          7
        //   186: aload_3         /* con */
        //   187: ifnull          199
        //   190: aload_2         /* trx */
        //   191: invokevirtual   common/Trx.close:()V
        //   194: goto            199
        //   197: astore          7
        //   199: iconst_1       
        //   200: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  -----------------------
        //  0      201     0     this  Luser/HP_PAB_UserSB;
        //  0      201     1     sql   Ljava/lang/String;
        //  2      199     2     trx   Lcommon/Trx;
        //  4      197     3     con   Ljava/sql/Connection;
        //  7      194     4     stmt  Ljava/sql/Statement;
        //  45     5       5     se    Ljava/sql/SQLException;
        //  84     50      5     ex    Ljava/lang/Exception;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  7      40     43     50     Ljava/sql/SQLException;
        //  55     62     65     67     Ljava/lang/Exception;
        //  71     75     78     80     Ljava/lang/Exception;
        //  7      40     82     134    Ljava/lang/Exception;
        //  7      50     134    169    Any
        //  82     134    134    169    Any
        //  141    148    151    153    Ljava/lang/Exception;
        //  157    161    164    166    Ljava/lang/Exception;
        //  174    181    184    186    Ljava/lang/Exception;
        //  190    194    197    199    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 89, Size: 89
        //     at java.util.ArrayList.rangeCheck(Unknown Source)
        //     at java.util.ArrayList.get(Unknown Source)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:123)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public ResultSet executeQuery(final StringBuffer sql) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2        /* trx */
        //     2: aconst_null    
        //     3: astore_3        /* con */
        //     4: aconst_null    
        //     5: astore          rs
        //     7: aconst_null    
        //     8: astore          stmt
        //    10: new             Lcommon/Trx;
        //    13: dup            
        //    14: aload_0         /* this */
        //    15: ldc             "usemn"
        //    17: invokespecial   common/Trx.<init>:(Ljava/lang/Object;Ljava/lang/String;)V
        //    20: astore_2        /* trx */
        //    21: aload_2         /* trx */
        //    22: invokevirtual   common/Trx.getConnection:()Ljava/sql/Connection;
        //    25: astore_3        /* con */
        //    26: aload_3         /* con */
        //    27: invokeinterface java/sql/Connection.createStatement:()Ljava/sql/Statement;
        //    32: astore          stmt
        //    34: aload           stmt
        //    36: aload_1         /* sql */
        //    37: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    40: invokeinterface java/sql/Statement.executeQuery:(Ljava/lang/String;)Ljava/sql/ResultSet;
        //    45: astore          rs
        //    47: goto            210
        //    50: astore          se
        //    52: aload           se
        //    54: invokevirtual   java/sql/SQLException.printStackTrace:()V
        //    57: aload           rs
        //    59: ifnull          74
        //    62: aload           rs
        //    64: invokeinterface java/sql/ResultSet.close:()V
        //    69: goto            74
        //    72: astore          8
        //    74: aload           stmt
        //    76: ifnull          91
        //    79: aload           stmt
        //    81: invokeinterface java/sql/Statement.close:()V
        //    86: goto            91
        //    89: astore          8
        //    91: aload_3         /* con */
        //    92: ifnull          104
        //    95: aload_2         /* trx */
        //    96: invokevirtual   common/Trx.close:()V
        //    99: goto            104
        //   102: astore          8
        //   104: aconst_null    
        //   105: areturn        
        //   106: astore          ex
        //   108: new             Ljava/lang/StringBuffer;
        //   111: dup            
        //   112: aload_0         /* this */
        //   113: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   116: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   119: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   122: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   125: ldc_w           "executeQuery() sql["
        //   128: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   131: aload_1         /* sql */
        //   132: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/Object;)Ljava/lang/StringBuffer;
        //   135: ldc_w           "] : "
        //   138: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   141: aload           ex
        //   143: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   146: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   149: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   152: invokestatic    common/Log.debug:(Ljava/lang/String;)V
        //   155: goto            57
        //   158: astore          7
        //   160: aload           rs
        //   162: ifnull          177
        //   165: aload           rs
        //   167: invokeinterface java/sql/ResultSet.close:()V
        //   172: goto            177
        //   175: astore          8
        //   177: aload           stmt
        //   179: ifnull          194
        //   182: aload           stmt
        //   184: invokeinterface java/sql/Statement.close:()V
        //   189: goto            194
        //   192: astore          8
        //   194: aload_3         /* con */
        //   195: ifnull          207
        //   198: aload_2         /* trx */
        //   199: invokevirtual   common/Trx.close:()V
        //   202: goto            207
        //   205: astore          8
        //   207: aload           7
        //   209: athrow         
        //   210: aload           rs
        //   212: ifnull          227
        //   215: aload           rs
        //   217: invokeinterface java/sql/ResultSet.close:()V
        //   222: goto            227
        //   225: astore          8
        //   227: aload           stmt
        //   229: ifnull          244
        //   232: aload           stmt
        //   234: invokeinterface java/sql/Statement.close:()V
        //   239: goto            244
        //   242: astore          8
        //   244: aload_3         /* con */
        //   245: ifnull          257
        //   248: aload_2         /* trx */
        //   249: invokevirtual   common/Trx.close:()V
        //   252: goto            257
        //   255: astore          8
        //   257: aload           rs
        //   259: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  ------------------------
        //  0      260     0     this  Luser/HP_PAB_UserSB;
        //  0      260     1     sql   Ljava/lang/StringBuffer;
        //  2      258     2     trx   Lcommon/Trx;
        //  4      256     3     con   Ljava/sql/Connection;
        //  7      253     4     rs    Ljava/sql/ResultSet;
        //  10     250     5     stmt  Ljava/sql/Statement;
        //  52     5       6     se    Ljava/sql/SQLException;
        //  108    50      6     ex    Ljava/lang/Exception;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  10     47     50     57     Ljava/sql/SQLException;
        //  62     69     72     74     Ljava/lang/Exception;
        //  79     86     89     91     Ljava/lang/Exception;
        //  95     99     102    104    Ljava/lang/Exception;
        //  10     47     106    158    Ljava/lang/Exception;
        //  10     57     158    210    Any
        //  106    158    158    210    Any
        //  165    172    175    177    Ljava/lang/Exception;
        //  182    189    192    194    Ljava/lang/Exception;
        //  198    202    205    207    Ljava/lang/Exception;
        //  215    222    225    227    Ljava/lang/Exception;
        //  232    239    242    244    Ljava/lang/Exception;
        //  248    252    255    257    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 110, Size: 110
        //     at java.util.ArrayList.rangeCheck(Unknown Source)
        //     at java.util.ArrayList.get(Unknown Source)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:123)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
