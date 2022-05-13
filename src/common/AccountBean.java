// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class AccountBean
{
    protected Connection conn;
    protected Trx tr;
    protected ResultSet rs;
    protected PreparedStatement pstmt;
    protected ResultSetMetaData rsmd;
    StringBuffer strsql;
    
    public AccountBean() {
        this.rs = null;
        this.pstmt = null;
        this.rsmd = null;
        this.strsql = new StringBuffer();
    }
    
    public boolean makeConnection() {
        try {
            if (this.conn == null) {
                this.tr = new Trx(this);
                this.conn = this.tr.getConnection();
            }
            else {
                this.conn = null;
                if (this.conn == null) {
                    this.tr = new Trx(this);
                    this.conn = this.tr.getConnection();
                }
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public String getAccountList(final String s, final String s2) {
        String string = "";
        if (this.makeConnection()) {
            try {
                (this.pstmt = this.conn.prepareStatement(this.getSql(s))).setString(1, s2);
                this.rs = this.pstmt.executeQuery();
                this.strsql.append("<select name='selAcctInfo'>");
                this.strsql.append("<option value='0'>계좌선택</option>");
                while (this.rs.next()) {
                    this.strsql.append("<option value='" + (this.rs.getString(1) + "," + this.rs.getString(2) + "," + this.rs.getString(3) + "," + this.rs.getString(4)) + "' >" + (this.rs.getString(1) + "," + this.rs.getString(2) + "," + this.rs.getString(3)) + "</option>");
                }
                this.strsql.append("</select>");
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.pstmt != null) {
                    this.pstmt.close();
                }
            }
            catch (Exception ex) {
                Log.debug("AccountBean getAccountList block Exception: " + ex.toString() + ":info......");
                return null;
            }
            finally {
                if (this.rs != null) {
                    try {
                        this.rs.close();
                    }
                    catch (Exception ex2) {}
                }
                if (this.pstmt != null) {
                    try {
                        this.pstmt.close();
                    }
                    catch (Exception ex3) {}
                }
                if (this.conn != null) {
                    try {
                        this.tr.close();
                    }
                    catch (Exception ex4) {}
                }
            }
            string = this.strsql.toString();
            this.strsql.delete(0, this.strsql.length());
        }
        return string;
    }
    
    public String getAccountColumnList(final String s, final String s2, final String s3) {
        String string = "";
        if (this.makeConnection()) {
            try {
                (this.pstmt = this.conn.prepareStatement(this.getSql(s, s3))).setString(1, s2);
                this.rs = this.pstmt.executeQuery();
                (this.rsmd = this.rs.getMetaData()).getColumnCount();
                this.strsql.append("<select name='selAcctColList'>");
                this.strsql.append("<option value='0'>" + s3 + "</option>");
                while (this.rs.next()) {
                    this.strsql.append("<option value='" + this.rs.getString(1) + "'>" + this.rs.getString(1) + "</option>");
                }
                this.strsql.append("</select>");
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.pstmt != null) {
                    this.pstmt.close();
                }
            }
            catch (Exception ex) {
                Log.debug("AccountBean getAccountColumnList block Exception: " + ex.toString() + ":Column......");
                return null;
            }
            finally {
                if (this.rs != null) {
                    try {
                        this.rs.close();
                    }
                    catch (Exception ex2) {}
                }
                if (this.pstmt != null) {
                    try {
                        this.pstmt.close();
                    }
                    catch (Exception ex3) {}
                }
                if (this.conn != null) {
                    try {
                        this.tr.close();
                    }
                    catch (Exception ex4) {}
                }
            }
            string = this.strsql.toString();
            this.strsql.delete(0, this.strsql.length());
        }
        return string;
    }
    
    public String getSql(final String s) {
        final StringBuffer sb = new StringBuffer();
        sb.append("SELECT ACCT.은행명, ACCT.계좌번호, ACCT.예금주, ACCT.은행코드 ");
        sb.append("FROM syn_지불_입금계좌 ACCT ");
        sb.append("WHERE ACCT.기관코드 = '" + s + "' ");
        sb.append("AND ACCT.계좌등록상태 = '1' ");
        sb.append("AND (ACCT.계좌구분 = ? OR ACCT.계좌구분 = '9') ");
        sb.append("ORDER BY ACCT.등록요청일자 DESC ");
        return sb.toString();
    }
    
    public String getSql(final String s, final String s2) {
        final StringBuffer sb = new StringBuffer();
        sb.append("SELECT ACCT." + s2 + " ");
        sb.append("FROM syn_지불_입금계좌 ACCT ");
        sb.append("WHERE ACCT.기관코드 = '" + s + "' ");
        sb.append("AND ACCT.계좌등록상태 = '1' ");
        sb.append("AND (ACCT.계좌구분 = ? OR ACCT.계좌구분 = '9') ");
        sb.append("ORDER BY ACCT.등록요청일자 DESC ");
        return sb.toString();
    }
    
    public String getAccountNameSql(final String s, final String s2, final String s3) {
        final StringBuffer sb = new StringBuffer();
        sb.append("select 은행명 from syn_지불_입금계좌   ");
        sb.append("where 은행코드 = '" + s + "' ");
        sb.append("and\t  계좌번호 = '" + s2 + "' ");
        sb.append("and   예금주   = '" + s3 + "' ");
        return sb.toString();
    }
    
    public String getAccountName(final String s, final String s2, final String s3) {
        String string = "";
        if (this.makeConnection()) {
            try {
                this.pstmt = this.conn.prepareStatement(this.getAccountNameSql(s, s2, s3));
                this.rs = this.pstmt.executeQuery();
                while (this.rs.next()) {
                    string = this.rs.getString("은행명");
                }
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.pstmt != null) {
                    this.pstmt.close();
                }
            }
            catch (Exception ex) {
                Log.debug("AccountBean getAccountNameSql block Exception: " + ex.toString() + ":Column......");
                return null;
            }
            finally {
                if (this.rs != null) {
                    try {
                        this.rs.close();
                    }
                    catch (Exception ex2) {}
                }
                if (this.pstmt != null) {
                    try {
                        this.pstmt.close();
                    }
                    catch (Exception ex3) {}
                }
                if (this.conn != null) {
                    try {
                        this.tr.close();
                    }
                    catch (Exception ex4) {}
                }
            }
        }
        return string;
    }
    
    public String getAccountListName(final String s, final String s2, final String s3, final String s4) {
        String string = "";
        if (this.makeConnection()) {
            try {
                (this.pstmt = this.conn.prepareStatement(this.getSql(s))).setString(1, s2);
                this.rs = this.pstmt.executeQuery();
                this.strsql.append("<select name='" + s3 + "' disabled>\n");
                this.strsql.append("<option value='0'>계좌선택</option>\n");
                while (this.rs.next()) {
                    final String string2 = this.rs.getString(1) + "," + this.rs.getString(2) + "," + this.rs.getString(3);
                    final String string3 = this.rs.getString(1) + "," + this.rs.getString(2) + "," + this.rs.getString(3) + "," + this.rs.getString(4);
                    if (string3.equals(s4)) {
                        this.strsql.append("<option value='" + string3 + "'  selected >" + string2 + "</option>");
                    }
                    else {
                        this.strsql.append("<option value='" + string3 + "'  >" + string2 + "</option>");
                    }
                }
                this.strsql.append("</select>\n");
                if (this.rs != null) {
                    this.rs.close();
                }
                if (this.pstmt != null) {
                    this.pstmt.close();
                }
            }
            catch (Exception ex) {
                Log.debug("AccountBean getAccountListName block Exception: " + ex.toString() + ":info......");
                return null;
            }
            finally {
                if (this.rs != null) {
                    try {
                        this.rs.close();
                    }
                    catch (Exception ex2) {}
                }
                if (this.pstmt != null) {
                    try {
                        this.pstmt.close();
                    }
                    catch (Exception ex3) {}
                }
                if (this.conn != null) {
                    try {
                        this.tr.close();
                    }
                    catch (Exception ex4) {}
                }
            }
            string = this.strsql.toString();
            this.strsql.delete(0, this.strsql.length());
        }
        return string;
    }
}
