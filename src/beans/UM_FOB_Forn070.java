// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.util.UM_COB_GTQ904;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import common.ComStr;
import java.util.Vector;
import entity.UM_GOE_ConiC080b;

public class UM_FOB_Forn070
{
    public UM_GOE_ConiC080b[] getUpcheInto(final int n, final int n2, final String s, String replace, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, String replace2, final String s9) throws Exception {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC080b>(1, 1);
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        replace = ComStr.replace(replace, "-", "");
        replace2 = ComStr.replace(replace2, "-", "");
        try {
            String s10 = " select  사업자등록번호, 상호명, 주소,전화번호,나머지주소, N from ( select a.사업자등록번호, a.상호명, a.주소,a.나머지주소, a.전화번호, rownum N from 사용_조달업체마스터 a where 사업자등록번호 is not null    and 입찰참가자격여부 is null " + "\n and 사업자등록번호 not in ( select 사업자등록번호 from 사용_업체구분 \n                              where a.사업자등록번호=사업자등록번호 and 업체구분 = '2') ";
            if (s2.length() > 0) {
                s10 = s10 + " and 상호명 like '%" + s2 + "%'";
            }
            if (s.length() > 0) {
                s10 = s10 + " and 업무구분 like '" + s + "'";
            }
            if (s3.length() > 0) {
                s10 = s10 + " and 지역코드 like '" + s3 + "%'";
            }
            if (replace.length() > 0) {
                s10 = s10 + " and 사업자등록번호 like '" + replace + "%'";
            }
            if (s8.length() > 0 || replace2.length() > 0) {
                String s11 = s10 + " and 사업자등록번호 in ( select 사업자등록번호 \t \t\t\t\t   \t   from 사용_대표자  \t\t\t\t\t   \t   where a.사업자등록번호=사업자등록번호 ";
                if (s8.length() > 0) {
                    s11 = s11 + " and 대표자명 like '" + s8 + "%'";
                }
                if (replace2.length() > 0) {
                    s11 = s11 + " and 대표자주민번호 like '" + replace2 + "%'";
                }
                s10 = s11 + " )";
            }
            if (s6.length() > 0) {
                s10 = s10 + " and 사업자등록번호 in (select 사업자등록번호 from 사용_면허사항 where a.사업자등록번호=사업자등록번호 and 면허코드='" + s6 + "')";
            }
            if (s7.length() > 0) {
                s10 = s10 + "and 사업자등록번호 in (select 사업자등록번호 from 사용_조달품목 where a.사업자등록번호=사업자등록번호 and 물품분류번호='" + s7 + "')";
            }
            if (s4.length() > 0 && s5.length() > 0) {
                if (s9.equals("RE")) {
                    s10 = s10 + "and to_date(to_char(등록일자,'yyyymmdd'),'yyyymmdd') between to_date('" + s4 + "','yyyymmdd') and to_date('" + s5 + "','yyyymmdd')";
                }
                if (s9.equals("NE")) {
                    s10 = s10 + "and to_date(to_char(갱신일자,'yyyymmdd'),'yyyymmdd') between to_date('" + s4 + "','yyyymmdd') and to_date('" + s5 + "','yyyymmdd')";
                }
            }
            final String string = s10 + " and rownum <= (" + n + " * " + n2 + ") ) sub where N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOE_ConiC080b um_GOE_ConiC080b = new UM_GOE_ConiC080b();
                um_GOE_ConiC080b.ett1.setSaupNo(executeQuery.getString(1));
                um_GOE_ConiC080b.ett1.setSangho(executeQuery.getString(2));
                um_GOE_ConiC080b.ett1.setAddr(executeQuery.getString(3));
                um_GOE_ConiC080b.ett1.setTel(executeQuery.getString(4));
                um_GOE_ConiC080b.ett1.setRestAddr(executeQuery.getString(5));
                vector.addElement(um_GOE_ConiC080b);
            }
            UM_GOE_ConiC080b[] array;
            if (vector.size() > 0) {
                array = new UM_GOE_ConiC080b[vector.size()];
                vector.copyInto(array);
            }
            else {
                array = null;
            }
            return array;
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInto() : " + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiC010p.getUpcheInto() : " + ex2.toString());
            throw new Exception(ex2.getMessage());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
    }
    
    public int getUpcheInfoCount(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11) throws Exception {
        String s12 = "\n select  count(*) from 사용_조달업체마스터 a\n where 사업자등록번호 is not null \n   and 입찰참가자격여부 is null " + "\n and 사업자등록번호 not in ( select 사업자등록번호 from 사용_업체구분 \n                              where a.사업자등록번호=사업자등록번호 and 업체구분 = '2') ";
        if (s3.length() > 0) {
            s12 = s12 + "\n and 상호명 like '%" + s3 + "%'";
        }
        if (s.length() > 0) {
            s12 = s12 + "\n and 업무구분 like '" + s + "'";
        }
        if (s4.length() > 0) {
            s12 = s12 + "\n and 지역코드 like '" + s4 + "%'";
        }
        if (s2.length() > 0) {
            s12 = s12 + "\n and 사업자등록번호 like '" + s2 + "%'";
        }
        if (s9.length() > 0 || s10.length() > 0) {
            String s13 = s12 + "\n and 사업자등록번호 in ( select 사업자등록번호 \n\t \t\t\t\t   \t   from 사용_대표자 \n\t\t\t\t\t   \t   where a.사업자등록번호=사업자등록번호 ";
            if (s9.length() > 0) {
                s13 = s13 + "\n and 대표자명 like '" + s9 + "%'";
            }
            if (s10.length() > 0) {
                s13 = s13 + "\n and 대표자주민번호 like '" + s10 + "%'";
            }
            s12 = s13 + "\n )";
        }
        if (s7.length() > 0) {
            s12 = s12 + "\n and 사업자등록번호 in (select 사업자등록번호 from 사용_면허사항 where a.사업자등록번호=사업자등록번호 and 면허코드='" + s7 + "')";
        }
        if (s8.length() > 0) {
            s12 = s12 + "\n and 사업자등록번호 in (select 사업자등록번호 from 사용_조달품목 where a.사업자등록번호=사업자등록번호 and 물품분류번호='" + s8 + "')";
        }
        if (s5.length() > 0 && s6.length() > 0) {
            if (s11.equals("RE")) {
                s12 = s12 + "\n and to_date(to_char(등록일자,'yyyymmdd'),'yyyymmdd') between to_date('" + s5 + "','yyyymmdd') and to_date('" + s6 + "','yyyymmdd')";
            }
            if (s11.equals("NE")) {
                s12 = s12 + "\n and to_date(to_char(갱신일자,'yyyymmdd'),'yyyymmdd') between to_date('" + s5 + "','yyyymmdd') and to_date('" + s6 + "','yyyymmdd')";
            }
        }
        return new UM_COB_GTQ904().getCount(this, s12, null);
    }
    
    public UM_GOE_ConiC080b[] select_comlist(final int n, final int n2, final String s, String replace, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, String replace2, final String s9) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC080b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        replace = ComStr.replace(replace, "-", "");
        replace2 = ComStr.replace(replace2, "-", "");
        Label_1781: {
            if (s6.equals("") && s8.equals("") && replace2.equals("") && s7.equals("")) {
                String s10 = " SELECT 사업자등록번호, 상호명, 대표자명, 주소, 나머지주소, 전화번호,  N  FROM (  SELECT A.사업자등록번호, A.상호명, B.대표자명, A.주소, A.나머지주소, A.전화번호, ROWNUM N  FROM   사용_조달업체마스터 A, 사용_대표자 B  WHERE  A.사업자등록번호=B.사업자등록번호  AND b.대표대표자여부='Y' ";
                if (0 < s.length()) {
                    s10 = s10 + " AND a.업무구분 like'" + s + "'";
                }
                if (0 < replace.length()) {
                    s10 = s10 + " AND a.사업자등록번호 like '" + replace + "%'";
                }
                if (0 < s2.length()) {
                    s10 = s10 + " AND a.상호명 like '%" + s2 + "%'";
                }
                if (0 < s3.length()) {
                    s10 = s10 + " AND a.지역코드 like '" + s3 + "%'";
                }
                if (0 < s4.length() && 0 < s5.length() && s9.equals("RE")) {
                    s10 = s10 + " AND a.등록일자>='" + s4 + "'" + " AND a.등록일자<='" + s5 + "'";
                }
                if (0 < s4.length() && 0 < s5.length() && s9.equals("NE")) {
                    s10 = s10 + " AND a.갱신일자>='" + s4 + "'" + " AND a.갱신일자<='" + s5 + "'";
                }
                if (0 < s6.length()) {
                    s10 = s10 + " AND c.면허코드='" + s6 + "'";
                }
                final String s11 = s10 + " AND ROWNUM <= (" + n + " * " + n2 + "))  WHERE N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
                break Label_1781;
            }
            if (s8.equals("") && replace2.equals("")) {
                String s12 = " SELECT 사업자등록번호, 상호명, 대표자명, 주소, 나머지주소, 전화번호, N  FROM (  SELECT a.사업자등록번호, a.상호명, b.대표자명, a.주소,  a.나머지주소, a.전화번호, a.업무구분, a.지역코드,  a.등록일자,a.갱신일자, c.면허코드, ROWNUM N \tFROM (SELECT 사업자등록번호, 대표자명 FROM 사용_대표자 WHERE 대표대표자여부='Y') b,  (SELECT 사업자등록번호, 면허코드 FROM 사용_면허사항 WHERE 대표면허여부='Y') c,  (select 사업자등록번호, 물품분류번호 from 사용_조달품목 where 대표물품여부='Y') d,  사용_조달업체마스터 a  WHERE a.사업자등록번호=b.사업자등록번호(+)  AND a.사업자등록번호=c.사업자등록번호(+)  AND a.사업자등록번호=d.사업자등록번호(+) ";
                if (0 < s.length()) {
                    s12 = s12 + " AND a.업무구분 like'" + s + "'";
                }
                if (0 < replace.length()) {
                    s12 = s12 + " AND a.사업자등록번호 like '" + replace + "%'";
                }
                if (0 < s2.length()) {
                    s12 = s12 + " AND a.상호명 like '%" + s2 + "%'";
                }
                if (0 < s3.length()) {
                    s12 = s12 + " AND a.지역코드 like '" + s3 + "%'";
                }
                if (0 < s4.length() && 0 < s5.length() && s9.equals("RE")) {
                    s12 = s12 + " AND a.등록일자>='" + s4 + "'" + " AND a.등록일자<='" + s5 + "'";
                }
                if (0 < s4.length() && 0 < s5.length() && s9.equals("NE")) {
                    s12 = s12 + " AND a.갱신일자>='" + s4 + "'" + " AND a.갱신일자<='" + s5 + "'";
                }
                if (0 < s6.length()) {
                    s12 = s12 + " AND c.면허코드='" + s6 + "'";
                }
                if (0 < s7.length()) {
                    s12 = s12 + " AND d.물품분류번호='" + s7 + "'";
                }
                final String s11 = s12 + " AND ROWNUM <= (" + n + " * " + n2 + ") ) sub WHERE N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
                break Label_1781;
            }
            String s13 = " SELECT 사업자등록번호, 상호명, 대표자명, 주소, 나머지주소, 전화번호, N  FROM (  SELECT /*+ ordered use_nl(b c d a) */ a.사업자등록번호, a.상호명, b.대표자명, a.주소,  a.나머지주소, a.전화번호, a.업무구분, a.지역코드,  a.등록일자,a.갱신일자, c.면허코드, ROWNUM N \tFROM (select 사업자등록번호, 대표자명 from 사용_대표자 where 사업자등록번호 in (select 사업자등록번호 from 사용_대표자 ";
            if (0 < s8.length() && 1 > replace2.length()) {
                s13 = s13 + " where 대표자명 like '" + s8 + "%' ";
            }
            if (0 < replace2.length() && 1 > s8.length()) {
                s13 = s13 + " where 대표자주민번호 like '" + replace2 + "%' ";
            }
            if (0 < replace2.length() && 0 < s8.length()) {
                s13 = s13 + " where 대표자명 like '" + s8 + "%' and 대표자주민번호 like '" + replace2 + "%' ";
            }
            String s14 = s13 + ") and 대표대표자여부='Y') b,  (SELECT 사업자등록번호, 면허코드 FROM 사용_면허사항 WHERE 대표면허여부='Y') c,  (select 사업자등록번호, 물품분류번호 from 사용_조달품목 where 대표물품여부='Y') d,  사용_조달업체마스터 a  WHERE a.사업자등록번호=b.사업자등록번호  AND a.사업자등록번호=c.사업자등록번호(+)  AND a.사업자등록번호=d.사업자등록번호(+) ";
            if (0 < s.length()) {
                s14 = s14 + " AND a.업무구분 like'" + s + "'";
            }
            if (0 < replace.length()) {
                s14 = s14 + " AND a.사업자등록번호 like '" + replace + "%'";
            }
            if (0 < s2.length()) {
                s14 = s14 + " AND a.상호명 like '%" + s2 + "%'";
            }
            if (0 < s3.length()) {
                s14 = s14 + " AND a.지역코드 like '" + s3 + "%'";
            }
            if (0 < s4.length() && 0 < s5.length() && s9.equals("RE")) {
                s14 = s14 + " AND a.등록일자>='" + s4 + "'" + " AND a.등록일자<='" + s5 + "'";
            }
            if (0 < s4.length() && 0 < s5.length() && s9.equals("NE")) {
                s14 = s14 + " AND a.갱신일자>='" + s4 + "'" + " AND a.갱신일자<='" + s5 + "'";
            }
            if (0 < s6.length()) {
                s14 = s14 + " AND c.면허코드='" + s6 + "'";
            }
            if (0 < s7.length()) {
                s14 = s14 + " AND d.물품분류번호='" + s7 + "'";
            }
            final String s11 = s14 + " AND ROWNUM <= (" + n + " * " + n2 + ") ) sub WHERE N between (((" + n + " - 1) * " + n2 + ")+1) and (" + n + " * " + n2 + ")";
            try {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                prepareStatement = connection.prepareStatement(s11);
                executeQuery = prepareStatement.executeQuery();
                prepareStatement.clearParameters();
                while (executeQuery.next()) {
                    final UM_GOE_ConiC080b um_GOE_ConiC080b = new UM_GOE_ConiC080b();
                    um_GOE_ConiC080b.ett1.setSaupNo(executeQuery.getString(1));
                    um_GOE_ConiC080b.ett1.setSangho(executeQuery.getString(2));
                    um_GOE_ConiC080b.ett1.setAddr(executeQuery.getString(4));
                    um_GOE_ConiC080b.ett1.setRestAddr(executeQuery.getString(5));
                    um_GOE_ConiC080b.ett1.setTel(executeQuery.getString(6));
                    um_GOE_ConiC080b.ett3.setCeoName(executeQuery.getString(3));
                    um_GOE_ConiC080b.ett7.setStateGubun(executeQuery.getString(7));
                    vector.addElement(um_GOE_ConiC080b);
                }
            }
            catch (SQLException ex) {
                Log.debug("UM_GOB_ConiC010p.select_comlist() : sql : " + s11 + "::: " + ex.toString());
            }
            catch (Exception ex2) {
                Log.debug("UM_GOB_ConiC010p.select_comlist() : sql : " + s11 + "::: " + ex2.toString());
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
                if (connection != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex5) {}
                }
            }
        }
        if (0 < vector.size()) {
            final UM_GOE_ConiC080b[] array = new UM_GOE_ConiC080b[vector.size()];
            vector.copyInto(array);
            return array;
        }
        return null;
    }
    
    public int Max_count(final String s, String replace, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7, final String s8, String replace2, final String s9) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String s10 = "";
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        replace2 = ComStr.replace(replace2, "-", "");
        try {
            if (s6.equals("") && s8.equals("") && replace2.equals("") && s7.equals("")) {
                s10 = " SELECT /*+ use_hash(a b) */ count(A.사업자등록번호)  FROM   사용_조달업체마스터 A, 사용_대표자 B  WHERE  A.사업자등록번호=B.사업자등록번호  AND b.대표대표자여부='Y' ";
            }
            else if (s8.equals("") && replace2.equals("")) {
                s10 = " SELECT count(a.사업자등록번호) \tFROM (SELECT 사업자등록번호, 대표자명 FROM 사용_대표자 WHERE 대표대표자여부='Y') b,  (SELECT 사업자등록번호, 면허코드 FROM 사용_면허사항 WHERE 대표면허여부='Y') c,  (select 사업자등록번호, 물품분류번호 from 사용_조달품목 where 대표물품여부='Y') d,  사용_조달업체마스터 a  WHERE a.사업자등록번호=b.사업자등록번호(+)  AND a.사업자등록번호=c.사업자등록번호(+)  AND a.사업자등록번호=d.사업자등록번호(+) ";
            }
            else {
                s10 = " SELECT count(a.사업자등록번호) \tFROM (select 사업자등록번호, 대표자명 from 사용_대표자 where 사업자등록번호 in (select 사업자등록번호 from 사용_대표자 ";
                if (0 < s8.length() && 1 > replace2.length()) {
                    s10 = s10 + " where 대표자명 like '" + s8 + "%' ";
                }
                if (0 < replace2.length() && 1 > s8.length()) {
                    s10 = s10 + " where 대표자주민번호 like '" + replace2 + "%' ";
                }
                if (0 < replace2.length() && 0 < s8.length()) {
                    s10 = s10 + " where 대표자명 like '" + s8 + "%' and 대표자주민번호 like '" + replace2 + "%' ";
                }
                s10 += ") and 대표대표자여부='Y') b,  (SELECT 사업자등록번호, 면허코드 FROM 사용_면허사항 WHERE 대표면허여부='Y') c,  (select 사업자등록번호, 물품분류번호 from 사용_조달품목 where 대표물품여부='Y') d,  사용_조달업체마스터 a  WHERE a.사업자등록번호=b.사업자등록번호  AND a.사업자등록번호=c.사업자등록번호(+)  AND a.사업자등록번호=d.사업자등록번호(+) ";
            }
            if (0 < ComStr.checkNull(s, "").length()) {
                s10 = s10 + " AND a.업무구분 like'" + s + "'";
            }
            if (0 < ComStr.checkNull(replace, "").length()) {
                s10 = s10 + " AND a.사업자등록번호 like '" + replace + "%'";
            }
            if (0 < ComStr.checkNull(s2, "").length()) {
                s10 = s10 + " AND a.상호명 like '%" + s2 + "%'";
            }
            if (0 < ComStr.checkNull(s3, "").length()) {
                s10 = s10 + " AND a.지역코드 like '" + s3 + "%'";
            }
            if (0 < s4.length() && 0 < s5.length() && s9.equals("RE")) {
                s10 = s10 + " AND a.등록일자>='" + s4 + "'";
                s10 = s10 + " AND a.등록일자<='" + s5 + "'";
            }
            if (0 < s4.length() && 0 < s5.length() && s9.equals("NE")) {
                s10 = s10 + " AND a.갱신일자>='" + s4 + "'";
                s10 = s10 + " AND a.갱신일자<='" + s5 + "'";
            }
            if (0 < ComStr.checkNull(s6, "").length()) {
                s10 = s10 + " AND c.면허코드='" + s6 + "'";
            }
            if (0 < s7.length()) {
                s10 = s10 + " AND d.물품분류번호='" + s7 + "'";
            }
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s10);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiC010p.Max_count() : sql : " + s10 + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiC010p.Max_count() : sql : " + s10 + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public UM_GOE_ConiC080b[] select_comlist(final int n, final int n2, final String s, String replace, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_GOE_ConiC080b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        replace = ComStr.replace(replace, "-", "");
        Label_0986: {
            if (s6.equals("")) {
                String s8 = " SELECT 사업자등록번호, 상호명, 대표자명, 주소, 나머지주소, 전화번호,  N  FROM (             SELECT A.사업자등록번호, A.상호명, B.대표자명, A.주소, A.나머지주소, A.전화번호, ROWNUM N   FROM   사용_조달업체마스터 A, 사용_대표자 B                                              WHERE  A.사업자등록번호=B.사업자등록번호                                                 AND b.대표대표자여부='Y' ";
                if (0 < s.length()) {
                    s8 = s8 + " AND a.업무구분 like'" + s + "'";
                }
                if (0 < replace.length()) {
                    s8 = s8 + " AND a.사업자등록번호 like '" + replace + "%'";
                }
                if (0 < s2.length()) {
                    s8 = s8 + " AND a.상호명 like '%" + s2 + "%'";
                }
                if (0 < s3.length()) {
                    s8 = s8 + " AND a.지역코드 like '" + s3 + "%'";
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
                break Label_0986;
            }
            String s10 = " SELECT 사업자등록번호, 상호명, 대표자명, 주소, 나머지주소, 전화번호, N                    FROM (                                                                                                                                                                \t SELECT a.사업자등록번호, a.상호명, b.대표자명, a.주소,                                          a.나머지주소, a.전화번호, a.업무구분, a.지역코드,                                       a.등록일자,a.갱신일자, c.면허코드, ROWNUM N                               \t FROM  (SELECT 사업자등록번호, 대표자명 FROM 사용_대표자 WHERE 대표대표자여부='Y') b, \t       (SELECT 사업자등록번호, 면허코드 FROM 사용_면허사항 WHERE 대표면허여부='Y') c, \t\t    사용_조달업체마스터 a                                                          WHERE a.사업자등록번호=b.사업자등록번호(+)                                              AND   a.사업자등록번호=c.사업자등록번호(+)                                         ";
            if (0 < s.length()) {
                s10 = s10 + " AND a.업무구분 like'" + s + "'";
            }
            if (0 < replace.length()) {
                s10 = s10 + " AND a.사업자등록번호 like '" + replace + "%'";
            }
            if (0 < s2.length()) {
                s10 = s10 + " AND a.상호명 like '%" + s2 + "%'";
            }
            if (0 < s3.length()) {
                s10 = s10 + " AND a.지역코드 like '" + s3 + "%'";
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
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                prepareStatement = connection.prepareStatement(s9);
                executeQuery = prepareStatement.executeQuery();
                prepareStatement.clearParameters();
                while (executeQuery.next()) {
                    final UM_GOE_ConiC080b um_GOE_ConiC080b = new UM_GOE_ConiC080b();
                    um_GOE_ConiC080b.ett1.setSaupNo(executeQuery.getString(1));
                    um_GOE_ConiC080b.ett1.setSangho(executeQuery.getString(2));
                    um_GOE_ConiC080b.ett1.setAddr(executeQuery.getString(4));
                    um_GOE_ConiC080b.ett1.setRestAddr(executeQuery.getString(5));
                    um_GOE_ConiC080b.ett1.setTel(executeQuery.getString(6));
                    um_GOE_ConiC080b.ett3.setCeoName(executeQuery.getString(3));
                    um_GOE_ConiC080b.ett7.setStateGubun(executeQuery.getString(7));
                    vector.addElement(um_GOE_ConiC080b);
                }
            }
            catch (SQLException ex) {
                Log.debug("UM_GOB_ConiC010p.select_comlist() : sql : " + s9 + "::: " + ex.toString());
            }
            catch (Exception ex2) {
                Log.debug("UM_GOB_ConiC010p.select_comlist() : sql : " + s9 + "::: " + ex2.toString());
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
                if (connection != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex5) {}
                }
            }
        }
        if (0 < vector.size()) {
            final UM_GOE_ConiC080b[] array = new UM_GOE_ConiC080b[vector.size()];
            vector.copyInto(array);
            return array;
        }
        return null;
    }
    
    public int Max_count(final String s, String replace, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String s8 = "";
        int int1 = 0;
        replace = ComStr.replace(replace, "-", "");
        try {
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
                s8 = s8 + " AND a.상호명 like '%" + s2 + "%'";
            }
            if (0 < ComStr.checkNull(s3, "").length()) {
                s8 = s8 + " AND a.지역코드 like '" + s3 + "%'";
            }
            if (0 < s4.length() && 0 < s5.length() && s7.equals("RE")) {
                s8 = s8 + " AND a.등록일자>='" + s4 + "'";
                s8 = s8 + " AND a.등록일자<='" + s5 + "'";
            }
            if (0 < s4.length() && 0 < s5.length() && s7.equals("NE")) {
                s8 = s8 + " AND a.갱신일자>='" + s4 + "'";
                s8 = s8 + " AND a.갱신일자<='" + s5 + "'";
            }
            if (0 < ComStr.checkNull(s6, "").length()) {
                s8 = s8 + " AND c.면허코드='" + s6 + "'";
            }
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s8);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_GOB_ConiC010p.Max_count() : sql : " + s8 + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_GOB_ConiC010p.Max_count() : sql : " + s8 + "::: " + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
}
