// 
// Decompiled by Procyon v0.5.30
// 

package user;

import java.util.StringTokenizer;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Trx;
import java.util.Vector;

public class HP_PAB_CompUserSB extends HP_PAB_UserSB
{
    public boolean insertUserEB(final HP_PAE_UserInfo userEB) {
        boolean flag = false;
        final StringBuffer sql = new StringBuffer();
        final Vector data = new Vector();
        sql.append("\tINSERT INTO 사용_부가사용자\t\n");
        sql.append("\t\t(아이디,\t\t\t\n");
        sql.append("\t\t이용자구분,\t\t\n");
        sql.append("\t\t비밀번호,\t\t\n");
        sql.append("\t\t이용자ID,\t\t\n");
        sql.append("\t\t성명,\t\t\t\n");
        sql.append("\t\t주민등록번호,\t\t\n");
        sql.append("\t\t전화번호,\t\t\n");
        sql.append("\t\t팩스번호,\t\t\n");
        sql.append("\t\t휴대폰번호,\t\t\n");
        sql.append("\t\t메일주소,\t\t\n");
        sql.append("\t\t우편번호,\t\t\n");
        sql.append("\t\t주소,\t\t\t\n");
        sql.append("\t\t상세주소,\t\t\n");
        sql.append("\t\t선호채널,\t\t\n");
        sql.append("\t\t상호명,\t\t\t\n");
        sql.append("\t\t부서명,\t\t\t\n");
        sql.append("\t\t부서장,\t\t\t\n");
        sql.append("\t\t직책,\t\t\t\n");
        sql.append("\t\t업체구분,\t\t\n");
        sql.append("\t\t승인여부,\t\t\n");
        sql.append("\t\t본사전화번호,\t\t\n");
        sql.append("\t\t사업자등록번호,\t\t\n");
        sql.append("\t\t품명1,\t\t\t\n");
        sql.append("\t\t품명2,\t\t\t\n");
        sql.append("\t\t품명3,\t\t\t\n");
        sql.append("\t\t품명4,\t\t\t\n");
        sql.append("\t\t수량1,\t\t\t\n");
        sql.append("\t\t수량2,\t\t\t\n");
        sql.append("\t\t수량3,\t\t\t\n");
        sql.append("\t\t수량4,\t\t\t\n");
        sql.append("\t\t삭제여부,\t\t\n");
        sql.append("\t\t등록일자,\t\t\n");
        sql.append("\t\t변경일자 \t\t\n");
        sql.append("\t) VALUES (\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t?,\t\t\t\n");
        sql.append("\t\t'N',\t\t\t\n");
        sql.append("\t\tSYSDATE,\t\t\n");
        sql.append("\t\tSYSDATE )\t\t\n");
        data.add(userEB.getUserid());
        data.add(userEB.getUsergubun());
        data.add(userEB.getUserpasswd());
        data.add(userEB.getRefid());
        data.add(userEB.getUsername());
        data.add(String.valueOf(userEB.getRegistno1()) + userEB.getRegistno2());
        data.add(String.valueOf(userEB.getUsertel1()) + "-" + userEB.getUsertel2() + "-" + userEB.getUsertel3());
        if (userEB.getUserfax1() != null && userEB.getUserfax2() != null && userEB.getUserfax3() != null) {
            data.add(String.valueOf(userEB.getUserfax1()) + "-" + userEB.getUserfax2() + "-" + userEB.getUserfax3());
        }
        else {
            data.add("");
        }
        if (userEB.getUsermobile1() != null && userEB.getUsermobile2() != null && userEB.getUsermobile3() != null) {
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
        if (userEB.getEnterprise() != null) {
            data.add(userEB.getEnterprise());
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
        if (userEB.getPartchief() != null) {
            data.add(userEB.getPartchief());
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
        if (userEB.getEntdivide() != null) {
            data.add(userEB.getEntdivide());
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
        if (userEB.getCorptel1() != null && userEB.getCorptel2() != null && userEB.getCorptel3() != null) {
            data.add(String.valueOf(userEB.getCorptel1()) + "-" + userEB.getCorptel2() + "-" + userEB.getCorptel3());
        }
        else {
            data.add("");
        }
        if (userEB.getBusinessno1() != null && userEB.getBusinessno2() != null && userEB.getBusinessno3() != null) {
            data.add(String.valueOf(userEB.getBusinessno1()) + userEB.getBusinessno2() + userEB.getBusinessno3());
        }
        else {
            data.add("");
        }
        if (userEB.getDiv1() != null) {
            data.add(userEB.getDiv1());
        }
        else {
            data.add("");
        }
        if (userEB.getDiv2() != null) {
            data.add(userEB.getDiv2());
        }
        else {
            data.add("");
        }
        if (userEB.getDiv3() != null) {
            data.add(userEB.getDiv3());
        }
        else {
            data.add("");
        }
        if (userEB.getDiv4() != null) {
            data.add(userEB.getDiv4());
        }
        else {
            data.add("");
        }
        if (userEB.getGood1() != null) {
            data.add(userEB.getGood1());
        }
        else {
            data.add("");
        }
        if (userEB.getGood2() != null) {
            data.add(userEB.getGood2());
        }
        else {
            data.add("");
        }
        if (userEB.getGood3() != null) {
            data.add(userEB.getGood3());
        }
        else {
            data.add("");
        }
        if (userEB.getGood4() != null) {
            data.add(userEB.getGood4());
        }
        else {
            data.add("");
        }
        flag = this.executeQuery(sql, data);
        return flag;
    }
    
    public boolean updateUserEB(final HP_PAE_UserInfo userEB) {
        boolean flag = false;
        final StringBuffer sql = new StringBuffer();
        sql.append("\tUPDATE\tUM_ADDED_USER\t\n");
        sql.append("\tSET\t\t\n");
        sql.append("\t\t\tUSER_CLS='" + userEB.getUsergubun() + "'\t\n");
        sql.append("\t\t,\tUSER_PHONE='" + userEB.getUsertel1() + "'\t\n");
        if (userEB.getUserfax1() != null) {
            sql.append("\t\t,\tUSER_FAX='" + userEB.getUserfax1() + "'\t\n");
        }
        if (userEB.getUsermobile1() != null) {
            sql.append("\t\t,\tUSER_MOBILE='" + userEB.getUsermobile1() + "'\t\n");
        }
        if (userEB.getUsermail() != null) {
            sql.append("\t\t,\tUSER_EMAIL='" + userEB.getUsermail() + "'\t\n");
        }
        if (userEB.getRegistno1() != null) {
            sql.append("\t\t,\tUSER_IDENT_NO='" + userEB.getRegistno1() + "'\t\n");
        }
        if (userEB.getAddress1() != null) {
            sql.append("\t\t,\tUSER_ADDR='" + userEB.getAddress1() + "'\t\n");
        }
        if (userEB.getPartname() != null) {
            sql.append("\t\t,\tUSER_DEPART='" + userEB.getPartname() + "'\t\n");
        }
        sql.append("\t\t,\tUPDATE_DATE=SYSDATE\t\n");
        sql.append("\tWHERE\tUSER_NAME='" + userEB.getUserid() + "'");
        flag = this.executeUpdate(sql.toString());
        return flag;
    }
    
    public Vector findEnterpriseInfo(final String businessno) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        final Vector compInfo = new Vector();
        final StringBuffer sql = new StringBuffer();
        sql.append("    SELECT  SUBSTR(A.사업자등록번호, 1, 3)  AS businessno1  \n");
        sql.append("                            ,       SUBSTR(A.사업자등록번호, 4, 2)  AS businessno2  \n");
        sql.append("                            ,       SUBSTR(A.사업자등록번호, 6, 5)  AS businessno3  \n");
        sql.append("                            ,       A.상호명                AS enterprise   \n");
        sql.append("                            ,       SUBSTR(A.전화번호, 1, INSTR(A.전화번호, '-') - 1) AS tel1 \n");
        sql.append("                            ,       SUBSTR(A.전화번호, INSTR(A.전화번호, '-') + 1, INSTR(A.전화번호, '-', INSTR(A.전화번호, '-') + 1) - INSTR(A.전화번호, '-') - 1)            AS tel2         \n");
        sql.append("                            ,       SUBSTR(A.전화번호, INSTR(A.전화번호, '-', INSTR(A.전화번호, '-') + 1) + 1, LENGTH(A.전화번호))            AS tel3 \n");
        sql.append("                            ,       B.업체구분              AS entdivide    \n");
        sql.append("    FROM            사용_조달업체마스터 A                           \n");
        sql.append("                            ,       사용_업체구분   B                       \n");
        sql.append("    WHERE           A.사업자등록번호 = B.사업자등록번호             \n");
        sql.append("AND\t\tA.사업자등록번호='" + businessno + "' \n");
        sql.append("AND    B.업체구분 = '2'");
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                final HP_PAE_CompInfoEB compEB = new HP_PAE_CompInfoEB();
                compEB.setBusinessno1(rs.getString("businessno1"));
                compEB.setBusinessno2(rs.getString("businessno2"));
                compEB.setBusinessno3(rs.getString("businessno3"));
                compEB.setEnterprise(rs.getString("enterprise"));
                compEB.setTel1(rs.getString("tel1"));
                compEB.setTel2(rs.getString("tel2"));
                compEB.setTel3(rs.getString("tel3"));
                compEB.setEntdivide(rs.getString("entdivide"));
                compInfo.add(compEB);
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
        return compInfo;
    }
    
    public int getCompUserListCount(final String enterprise, final String businessno, final String entdivide) {
        final StringBuffer sql = new StringBuffer();
        sql.append("\tSELECT\tCOUNT(아이디)\tAS count\t\t\n");
        sql.append("\tFROM\t\t사용_부가사용자\t\n");
        sql.append("\tWHERE\t이용자구분='B'\t\t\n");
        sql.append("\tAND\t\t\t삭제여부<>'Y'\t\n");
        if (entdivide.equals("1")) {
            sql.append("\tAND\t업체구분='1'\t\n");
        }
        else {
            sql.append("\tAND\t업체구분='2'\t\n");
        }
        if (enterprise != null && !enterprise.equals("")) {
            sql.append("\tAND\t상호명\tLIKE\t '%" + enterprise + "%'\t\n");
        }
        if (businessno != null && !businessno.equals("")) {
            sql.append("\tAND\t사업자등록번호\tLIKE\t '%" + businessno + "%'");
        }
        return this.getListCount(sql.toString());
    }
    
    public HP_PAE_UserList getCompUserListEB(final String enterprise, final String businessno, final String entdivide, final int size, final int start) {
        Trx trx = null;
        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        final HP_PAE_UserList userlist = new HP_PAE_UserList(size);
        final StringBuffer sql = new StringBuffer();
        String sql2 = new String();
        sql.append("\t\tSELECT\tROWNUM\trnum\t\t\n");
        sql.append("\t\t\t\t\t,\t아이디\tAS userid\t\t\n");
        sql.append("\t\t\t\t\t,\t성명\t\tAS username\t\n");
        sql.append("\t\t\t\t\t,\t상호명\tAS enterprise\t\n");
        sql.append("\t\t\t\t\t,\tSUBSTR(사업자등록번호, 1, 3)\tAS businessno1\t\n");
        sql.append("\t\t\t\t\t,\tSUBSTR(사업자등록번호, 4, 2)\tAS businessno2\t\n");
        sql.append("\t\t\t\t\t,\tSUBSTR(사업자등록번호, 6, 5)\tAS businessno3\t\t\n");
        sql.append("\t\t\t\t\t,\tSUBSTR(본사전화번호, 1, INSTR(본사전화번호, '-') - 1)\t\t\tAS corptel1\t\n");
        sql.append("\t\t\t\t\t,\tSUBSTR(본사전화번호, INSTR(본사전화번호, '-') + 1, INSTR(본사전화번호, '-', INSTR(본사전화번호, '-') + 1) - INSTR(본사전화번호, '-') - 1)\t\tAS corptel2\t\n");
        sql.append("\t\t\t\t\t,\tSUBSTR(본사전화번호, INSTR(본사전화번호, '-', INSTR(본사전화번호, '-') + 1) + 1, LENGTH(본사전화번호))\t\tAS corptel3\t\n");
        sql.append("\t\t\t\t\t,\tTO_CHAR(등록일자, 'YYYYMMDD')\t\tAS registdate\t\n");
        sql.append("\t\t\t\t\t,\t업체구분\t\tAS entdivide\t\n");
        sql.append("\t\tFROM\t\t사용_부가사용자\t\t\t\t\n");
        sql.append("\t\tWHERE\t이용자구분='B'\t\t\t\t\n");
        sql.append("\t\tAND\t\t\t삭제여부<>'Y'\t\t\t\t\n");
        sql.append("\t\tAND\t\t\t업체구분 = '" + entdivide + "'\t\n");
        if (enterprise != null && !enterprise.equals("")) {
            sql.append("\t\tAND\t\t\t상호명\tLIKE\t '%" + enterprise + "%'\t\t\n");
        }
        if (businessno != null && !businessno.equals("")) {
            sql.append("\t\tAND\t\t\t사업자등록번호\tLIKE\t '%" + businessno + "%'\t\n");
        }
        sql2 = "\tSELECT\t*\t\n\tFROM\t(\t\n" + sql.toString() + "\n" + "\t\t\t\t)\n" + "\tWHERE\trnum\tBETWEEN\t" + start + "\tAND\t" + (start + size - 1);
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql2);
            HP_PAE_UserInfo userEB = null;
            while (rs.next()) {
                userEB = new HP_PAE_UserInfo();
                userEB.setUserid(rs.getString("userid"));
                userEB.setUsername(rs.getString("username"));
                userEB.setEnterprise(rs.getString("enterprise"));
                userEB.setBusinessno1(rs.getString("businessno1"));
                userEB.setBusinessno2(rs.getString("businessno2"));
                userEB.setBusinessno3(rs.getString("businessno3"));
                userEB.setCorptel1(rs.getString("corptel1"));
                userEB.setCorptel2(rs.getString("corptel2"));
                userEB.setCorptel3(rs.getString("corptel3"));
                userEB.setRegistdate(rs.getString("registdate"));
                userEB.setEntdivide(rs.getString("entdivide"));
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
    
    public boolean recognitionUser(final String userIDs, final String recognition) {
        String sql = new String();
        boolean flag = false;
        final StringTokenizer st = new StringTokenizer(userIDs, ":");
        sql = "\tUPDATE 사용_부가사용자\t\n\tSET\t\t\n\t\t\t승인여부='" + recognition + "'\t\t\n" + "\t\t\t변경일자=SYSDATE\t\t\n" + "\tWHERE\t아이디\tIN\t\t(\n\t";
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
        sql = String.valueOf(sql) + "\n\t)";
        flag = this.executeUpdate(sql);
        return flag;
    }
}
