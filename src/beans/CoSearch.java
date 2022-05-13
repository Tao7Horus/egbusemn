// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.Log;
import common.ComStr;
import java.util.Vector;
import entity.CoSearche;
import common.Trx;
import java.sql.Connection;

public class CoSearch
{
    Connection con;
    Trx trx;
    
    public CoSearche[] select_comlist(final int n, final int n2, final String s, String replace, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        final Vector vector = new Vector<CoSearche>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        replace = ComStr.replace(replace, "-", "");
        Label_0980: {
            if (s6.equals("")) {
                String s8 = " SELECT 사업자등록번호, 상호명, 대표자명, 주소, 나머지주소, 전화번호,  N  FROM (             SELECT A.사업자등록번호, A.상호명, B.대표자명, A.주소, A.나머지주소, A.전화번호, ROWNUM N   FROM   사용_조달업체마스터 A, 사용_대표자 B                                              WHERE  A.사업자등록번호=B.사업자등록번호                                                 AND b.대표대표자여부='Y' ";
                if (0 < s.length()) {
                    s8 = s8 + " AND a.업무구분 like'" + s + "'";
                }
                if (0 < replace.length()) {
                    s8 = s8 + " AND a.사업자등록번호 like '" + replace + "%'";
                }
                if (0 < s2.length()) {
                    s8 = s8 + " AND a.상호명 like '" + s2 + "%'";
                }
                if (0 < s3.length()) {
                    s8 = s8 + " AND a.지역코드='" + s3 + "'";
                }
                if (0 < s4.length() && 0 < s5.length() && s7.equals("RE")) {
                    s8 = s8 + " AND a.등록일자>='" + s4 + "'" + " AND a.등록일자<='" + s5 + "'";
                }
                if (0 < s4.length() && 0 < s5.length() && s7.equals("NE")) {
                    s8 = s8 + " AND a.갱신일자>='" + s4 + "'" + " AND a.갱신일자<='" + s5 + "'";
                }
                if (0 < s6.length()) {
                    s8 = s8 + " AND c.면허코드='" + s6 + "'";
                }
                final String s9 = s8 + " AND ROWNUM <= (" + n + " * " + n2 + "))  WHERE N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
                break Label_0980;
            }
            String s10 = " SELECT 사업자등록번호, 상호명, 대표자명, 주소, 나머지주소, 전화번호, N                    FROM (                                                                                                                                                                \t SELECT a.사업자등록번호, a.상호명, b.대표자명, a.주소,                                          a.나머지주소, a.전화번호, a.업무구분, a.지역코드,                                       a.등록일자,a.갱신일자, c.면허코드, ROWNUM N                               \t FROM  (SELECT 사업자등록번호, 대표자명 FROM 사용_대표자 WHERE 대표대표자여부='Y') b, \t       (SELECT 사업자등록번호, 면허코드 FROM 사용_면허사항 WHERE 대표면허여부='Y') c, \t\t    사용_조달업체마스터 a                                                          WHERE a.사업자등록번호=b.사업자등록번호(+)                                              AND   a.사업자등록번호=c.사업자등록번호(+)                                         ";
            if (0 < s.length()) {
                s10 = s10 + " AND a.업무구분 like'" + s + "'";
            }
            if (0 < replace.length()) {
                s10 = s10 + " AND a.사업자등록번호 like '" + replace + "%'";
            }
            if (0 < s2.length()) {
                s10 = s10 + " AND a.상호명 like '" + s2 + "%'";
            }
            if (0 < s3.length()) {
                s10 = s10 + " AND a.지역코드='" + s3 + "'";
            }
            if (0 < s4.length() && 0 < s5.length() && s7.equals("RE")) {
                s10 = s10 + " AND a.등록일자>='" + s4 + "'" + " AND a.등록일자<='" + s5 + "'";
            }
            if (0 < s4.length() && 0 < s5.length() && s7.equals("NE")) {
                s10 = s10 + " AND a.갱신일자>='" + s4 + "'" + " AND a.갱신일자<='" + s5 + "'";
            }
            if (0 < s6.length()) {
                s10 = s10 + " AND c.면허코드='" + s6 + "'";
            }
            final String s9 = s10 + " AND ROWNUM <= (" + n + " * " + n2 + ") ) sub WHERE N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
            try {
                this.trx = new Trx(this, "usemn");
                this.con = this.trx.getConnection();
                prepareStatement = this.con.prepareStatement(s9);
                executeQuery = prepareStatement.executeQuery();
                prepareStatement.clearParameters();
                while (executeQuery.next()) {
                    final CoSearche coSearche = new CoSearche();
                    coSearche.ett1.setSaupNo(executeQuery.getString(1));
                    coSearche.ett1.setSangho(executeQuery.getString(2));
                    coSearche.ett1.setAddr(executeQuery.getString(4));
                    coSearche.ett1.setRestAddr(executeQuery.getString(5));
                    coSearche.ett1.setTel(executeQuery.getString(6));
                    coSearche.ett3.setCeoName(executeQuery.getString(3));
                    coSearche.ett7.setStateGubun(executeQuery.getString(7));
                    vector.addElement(coSearche);
                }
                executeQuery.close();
                prepareStatement.close();
                this.con.close();
            }
            catch (SQLException ex) {
                Log.debug("UM_URV_UserD020p.select_userlist block SQLException : ");
                Log.debug("Exception발생 사유 :" + ex.toString() + ex.getErrorCode() + ex.getSQLState());
                ex.printStackTrace();
            }
            catch (Exception ex2) {
                Log.debug("UM_URV_UserD020p.select_userlist block Exception : ");
                Log.debug("Exception발생 사유 : " + ex2.toString());
                ex2.printStackTrace();
            }
            finally {
                if (executeQuery != null) {
                    try {
                        executeQuery.close();
                    }
                    catch (Exception ex3) {}
                }
                if (prepareStatement != null) {
                    try {
                        prepareStatement.close();
                    }
                    catch (Exception ex4) {}
                }
                if (this.trx != null) {
                    try {
                        this.trx.close();
                    }
                    catch (Exception ex5) {}
                }
            }
        }
        if (0 < vector.size()) {
            final CoSearche[] array = new CoSearche[vector.size()];
            vector.copyInto(array);
            return array;
        }
        return null;
    }
    
    public int Max_count(final String s, String replace, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
            String s8;
            if (s6.equals("")) {
                s8 = " SELECT count(A.사업자등록번호)             FROM   사용_조달업체마스터 A, 사용_대표자 B  WHERE  A.사업자등록번호=B.사업자등록번호     AND b.대표대표자여부='Y' ";
            }
            else {
                s8 = " SELECT ROWNUM, a.사업자등록번호, a.상호명, b.대표자명, a.주소,                      a.나머지주소, a.전화번호,a.업무구분,a.지역코드, a.등록일자,a.갱신일자,          c.면허코드                                                         FROM  사용_조달업체마스터 a ,                                                    (select * from 사용_대표자 where 대표대표자여부='Y') b,              \t      (select * from 사용_면허사항 where 대표면허여부='Y') c               \tWHERE a.사업자등록번호=b.사업자등록번호(+)                                  \tAND   a.사업자등록번호=c.사업자등록번호(+) ";
            }
            if (0 < ComStr.checkNull(s, "").length()) {
                s8 = s8 + " AND a.업무구분 like'" + s + "'";
            }
            if (0 < ComStr.checkNull(replace, "").length()) {
                s8 = s8 + " AND a.사업자등록번호 like '" + replace + "%'";
            }
            if (0 < ComStr.checkNull(s2, "").length()) {
                s8 = s8 + " AND a.상호명 like '" + s2 + "%'";
            }
            if (0 < ComStr.checkNull(s3, "").length()) {
                s8 = s8 + " AND a.지역코드='" + s3 + "'";
            }
            if (0 < s4.length() && 0 < s5.length() && s7.equals("RE")) {
                s8 = s8 + " AND a.등록일자>='" + s4 + "'" + " AND a.등록일자<='" + s5 + "'";
            }
            if (0 < s4.length() && 0 < s5.length() && s7.equals("NE")) {
                s8 = s8 + " AND a.갱신일자>='" + s4 + "'" + " AND a.갱신일자<='" + s5 + "'";
            }
            if (0 < ComStr.checkNull(s6, "").length()) {
                s8 = s8 + " AND c.면허코드='" + s6 + "'";
            }
            this.trx = new Trx(this, "usemn");
            this.con = this.trx.getConnection();
            prepareStatement = this.con.prepareStatement(s8);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
            executeQuery.close();
            prepareStatement.close();
            this.con.close();
        }
        catch (SQLException ex) {
            Log.debug("CoSearch.Max_count block SQLException : ");
            Log.debug("Exception발생 사유 : " + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            Log.debug("CoSearch.Max_count block Exception : ");
            Log.debug("Exception발생 사유 : " + ex2.toString());
            ex2.printStackTrace();
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (this.trx != null) {
                try {
                    this.trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
}
