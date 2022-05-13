// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_ICE_InciA040b;

public class UM_RAB_IncuA025p
{
    public UM_ICE_InciA040b select_coperation(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        UM_ICE_InciA040b um_ICE_InciA040b = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" SELECT\tB.id,\t\t\t\tB.password,\t\t\t\tA.업체명,\t\tA.사업자등록번호,\tA.대표자명,\t\tA.주민등록번호,\t\tA.업체형태부호,\t\t\t\t\t\t\t\t\n");
            sb.append(" \t\tA.우편코드,\t\t\tA.본사주소,\t\t\t\tA.본사전화번호,\tA.본사팩스번호,\t\tA.법인구분,\t\tTO_CHAR (A.법인설립일, 'YYYY-MM-DD') 법인설립일,\tA.자본금,\t\n");
            sb.append("\t  \t\tA.종업원수,\t\t\tA.최근3년간총매출액,\tA.홈페이지,\t\tA.업체상태,\t\t\trownum,\t\t\tA.이미지명,\t\t\tA.회사소개,\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tA.주생산품류부호,\tA.업체규모구분,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tTO_CHAR (B.등록일자, 'YYYY-MM-DD') 등록일자,B.등급구분,\t\tB.성명,\t\t\t\tB.주민등록번호 주민,\tB.부서명,\tB.부서장,\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tB.직책,\t\t\t\tB.전자메일,\t\t\t\tB.전화번호,\t\tB.팩스번호,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tA.최초입력자id,\t\tTO_CHAR (A.최초입력일,'YYYY-MM-DD') 최초입력일,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tA.최근수정자id,\t\tTO_CHAR (A.최근수정일,'YYYY-MM-DD') 최근수정일,\t\t\t\tA.기관구분\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" FROM\t사용_생산업체 A, 사용_생산업체사용자 B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.사업자등록번호 = B.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" AND\t\tB.id = '" + s + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ICE_InciA040b = new UM_ICE_InciA040b();
                um_ICE_InciA040b.setuserId(executeQuery.getString(1));
                um_ICE_InciA040b.setpassword(executeQuery.getString(2));
                um_ICE_InciA040b.setupcheNm(executeQuery.getString(3));
                um_ICE_InciA040b.setsaupNo(executeQuery.getString(4));
                um_ICE_InciA040b.setdaepyoNm(executeQuery.getString(5));
                um_ICE_InciA040b.setdaepyojuminNo(executeQuery.getString(6));
                um_ICE_InciA040b.setupcheGubun(executeQuery.getString(7));
                um_ICE_InciA040b.setpostCode(executeQuery.getString(8));
                um_ICE_InciA040b.setbonsaAdd(executeQuery.getString(9));
                um_ICE_InciA040b.setbonsaTel(executeQuery.getString(10));
                um_ICE_InciA040b.setbonsaFax(executeQuery.getString(11));
                um_ICE_InciA040b.setbukinGubun(executeQuery.getString(12));
                um_ICE_InciA040b.setestablish(executeQuery.getString(13));
                um_ICE_InciA040b.setmoney(executeQuery.getString(14));
                um_ICE_InciA040b.setworker(executeQuery.getString(15));
                um_ICE_InciA040b.setoutput(executeQuery.getString(16));
                um_ICE_InciA040b.sethomepage(executeQuery.getString(17));
                um_ICE_InciA040b.setupchesGubun(executeQuery.getString(18));
                um_ICE_InciA040b.setS20(executeQuery.getString(19));
                um_ICE_InciA040b.setimageNm(executeQuery.getString(20));
                um_ICE_InciA040b.setcopInt(executeQuery.getString(21));
                um_ICE_InciA040b.setsaensanGubun(executeQuery.getString(22));
                um_ICE_InciA040b.setupchekGubun(executeQuery.getString(23));
                um_ICE_InciA040b.setinsertDay(executeQuery.getString(24));
                um_ICE_InciA040b.setgradeGubun(executeQuery.getString(25));
                um_ICE_InciA040b.setname(executeQuery.getString(26));
                um_ICE_InciA040b.setjuminNo(executeQuery.getString(27));
                um_ICE_InciA040b.setbuserNm(executeQuery.getString(28));
                um_ICE_InciA040b.setbuserJang(executeQuery.getString(29));
                um_ICE_InciA040b.setduty(executeQuery.getString(30));
                um_ICE_InciA040b.setmail(executeQuery.getString(31));
                um_ICE_InciA040b.settelNo(executeQuery.getString(32));
                um_ICE_InciA040b.setfaxNo(executeQuery.getString(33));
                um_ICE_InciA040b.setfirstId(executeQuery.getString(34));
                um_ICE_InciA040b.setfirstDay(executeQuery.getString(35));
                um_ICE_InciA040b.setupdateId(executeQuery.getString(36));
                um_ICE_InciA040b.setupdateDay(executeQuery.getString(37));
                um_ICE_InciA040b.setkigwan(executeQuery.getString(38));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_IncuA025p.select_coperation('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA025p.select_coperation('" + s + "'):" + ex2.toString());
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
        return um_ICE_InciA040b;
    }
    
    public UM_ICE_InciA040b select_coperation2(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        UM_ICE_InciA040b um_ICE_InciA040b = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" SELECT\tA.업체명,\t\tA.사업자등록번호,     A.대표자명,\t\tA.주민등록번호,\tA.업체형태부호,\t\n");
            sb.append(" \t\tA.우편코드,\t\tA.본사주소,\t\t\tA.본사전화번호,\tA.본사팩스번호,\tA.법인구분,\t\t\n");
            sb.append("\t  \t\tTO_CHAR (A.법인설립일, 'YYYY-MM-DD') 법인설립일,\t    A.자본금,\t    A.종업원수,\t\t\n");
            sb.append("\t\t\tA.최근3년간총매출액,\tA.홈페이지,\t\tA.업체상태,\t\tA.이미지명,\t\tA.회사소개,      \n");
            sb.append("\t\t\tA.주생산품류부호,\t    A.업체규모구분,\tA.최초입력자id,\tTO_CHAR (A.최초입력일,'YYYY-MM-DD') 최초입력일,\t\n");
            sb.append("\t\t\tA.최근수정자id,\tTO_CHAR (A.최근수정일,'YYYY-MM-DD') 최근수정일\t\t\t\t\t\t\t\n");
            sb.append(" FROM\t사용_생산업체 A                   \t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.사업자등록번호 = '" + s + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ICE_InciA040b = new UM_ICE_InciA040b();
                um_ICE_InciA040b.setupcheNm(executeQuery.getString(1));
                um_ICE_InciA040b.setsaupNo(executeQuery.getString(2));
                um_ICE_InciA040b.setdaepyoNm(executeQuery.getString(3));
                um_ICE_InciA040b.setdaepyojuminNo(executeQuery.getString(4));
                um_ICE_InciA040b.setupcheGubun(executeQuery.getString(5));
                um_ICE_InciA040b.setpostCode(executeQuery.getString(6));
                um_ICE_InciA040b.setbonsaAdd(executeQuery.getString(7));
                um_ICE_InciA040b.setbonsaTel(executeQuery.getString(8));
                um_ICE_InciA040b.setbonsaFax(executeQuery.getString(9));
                um_ICE_InciA040b.setbukinGubun(executeQuery.getString(10));
                um_ICE_InciA040b.setestablish(executeQuery.getString(11));
                um_ICE_InciA040b.setmoney(executeQuery.getString(12));
                um_ICE_InciA040b.setworker(executeQuery.getString(13));
                um_ICE_InciA040b.setoutput(executeQuery.getString(14));
                um_ICE_InciA040b.sethomepage(executeQuery.getString(15));
                um_ICE_InciA040b.setupchesGubun(executeQuery.getString(16));
                um_ICE_InciA040b.setimageNm(executeQuery.getString(17));
                um_ICE_InciA040b.setcopInt(executeQuery.getString(18));
                um_ICE_InciA040b.setsaensanGubun(executeQuery.getString(19));
                um_ICE_InciA040b.setupchekGubun(executeQuery.getString(20));
                um_ICE_InciA040b.setfirstId(executeQuery.getString(21));
                um_ICE_InciA040b.setfirstDay(executeQuery.getString(22));
                um_ICE_InciA040b.setupdateId(executeQuery.getString(23));
                um_ICE_InciA040b.setupdateDay(executeQuery.getString(24));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_IncuA025p.select_coperation2('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA025p.select_coperation2('" + s + "'):" + ex2.toString());
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
        return um_ICE_InciA040b;
    }
    
    public UM_ICE_InciA040b select_Kigwan(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        UM_ICE_InciA040b um_ICE_InciA040b = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" SELECT\tA.업체명\t\t\t\t                \n");
            sb.append(" FROM\t사용_생산업체 A                   \t\t\n");
            sb.append(" WHERE\tA.사업자등록번호 = '" + s + "'\t\t\t\n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ICE_InciA040b = new UM_ICE_InciA040b();
                um_ICE_InciA040b.setupcheNm(executeQuery.getString(1));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_IncuA025p.select_Kigwan('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA025p.select_Kigwan('" + s + "'):" + ex2.toString());
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
        return um_ICE_InciA040b;
    }
    
    public UM_ICE_InciA040b select_cop(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        UM_ICE_InciA040b um_ICE_InciA040b = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" SELECT\tB.id,\t\t\t\tB.password,\t\t\t\tA.업체명,\t\tA.사업자등록번호,\tA.대표자명,\t\tA.주민등록번호,\t\tA.업체형태부호,\t\t\t\t\t\t\t\t\n");
            sb.append(" \t\tA.우편코드,\t\t\tA.본사주소,\t\t\t\tA.본사전화번호,\tA.본사팩스번호,\t\tA.법인구분,\t\tTO_CHAR (A.법인설립일, 'YYYY-MM-DD') 법인설립일,\tA.자본금,\t\n");
            sb.append("\t  \t\tA.종업원수,\t\t\tA.최근3년간총매출액,\tA.홈페이지,\t\tA.업체상태,\t\t\trownum,\t\t\tA.이미지명,\t\t\tA.회사소개,\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tA.주생산품류부호,\tA.업체규모구분,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tTO_CHAR (B.등록일자, 'YYYY-MM-DD') 등록일자,B.등급구분,\t\tB.성명,\t\t\t\tB.주민등록번호 주민,\tB.부서명,\tB.부서장,\t\t\t\t\t\t\t\t\t    \n");
            sb.append("\t\t\tB.직책,\t\t\t\tB.전자메일,\t\t\t\tB.전화번호,\t\tB.팩스번호,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tA.최초입력자id,\t\tTO_CHAR (A.최초입력일,'YYYY-MM-DD') 최초입력일,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tA.최근수정자id,\t\tTO_CHAR (A.최근수정일,'YYYY-MM-DD') 최근수정일\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" FROM\t사용_생산업체 A, 사용_생산업체사용자 B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.사업자등록번호 = B.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" AND\t\tB.사업자등록번호 = '" + s + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ICE_InciA040b = new UM_ICE_InciA040b();
                um_ICE_InciA040b.setuserId(executeQuery.getString(1));
                um_ICE_InciA040b.setpassword(executeQuery.getString(2));
                um_ICE_InciA040b.setupcheNm(executeQuery.getString(3));
                um_ICE_InciA040b.setsaupNo(executeQuery.getString(4));
                um_ICE_InciA040b.setdaepyoNm(executeQuery.getString(5));
                um_ICE_InciA040b.setdaepyojuminNo(executeQuery.getString(6));
                um_ICE_InciA040b.setupcheGubun(executeQuery.getString(7));
                um_ICE_InciA040b.setpostCode(executeQuery.getString(8));
                um_ICE_InciA040b.setbonsaAdd(executeQuery.getString(9));
                um_ICE_InciA040b.setbonsaTel(executeQuery.getString(10));
                um_ICE_InciA040b.setbonsaFax(executeQuery.getString(11));
                um_ICE_InciA040b.setbukinGubun(executeQuery.getString(12));
                um_ICE_InciA040b.setestablish(executeQuery.getString(13));
                um_ICE_InciA040b.setmoney(executeQuery.getString(14));
                um_ICE_InciA040b.setworker(executeQuery.getString(15));
                um_ICE_InciA040b.setoutput(executeQuery.getString(16));
                um_ICE_InciA040b.sethomepage(executeQuery.getString(17));
                um_ICE_InciA040b.setupchesGubun(executeQuery.getString(18));
                um_ICE_InciA040b.setimageNm(executeQuery.getString(20));
                um_ICE_InciA040b.setcopInt(executeQuery.getString(21));
                um_ICE_InciA040b.setsaensanGubun(executeQuery.getString(22));
                um_ICE_InciA040b.setupchekGubun(executeQuery.getString(23));
                um_ICE_InciA040b.setinsertDay(executeQuery.getString(24));
                um_ICE_InciA040b.setgradeGubun(executeQuery.getString(25));
                um_ICE_InciA040b.setname(executeQuery.getString(26));
                um_ICE_InciA040b.setjuminNo(executeQuery.getString(27));
                um_ICE_InciA040b.setbuserNm(executeQuery.getString(28));
                um_ICE_InciA040b.setbuserJang(executeQuery.getString(29));
                um_ICE_InciA040b.setduty(executeQuery.getString(30));
                um_ICE_InciA040b.setmail(executeQuery.getString(31));
                um_ICE_InciA040b.settelNo(executeQuery.getString(32));
                um_ICE_InciA040b.setfaxNo(executeQuery.getString(33));
                um_ICE_InciA040b.setfirstId(executeQuery.getString(34));
                um_ICE_InciA040b.setfirstDay(executeQuery.getString(35));
                um_ICE_InciA040b.setupdateId(executeQuery.getString(36));
                um_ICE_InciA040b.setupdateDay(executeQuery.getString(37));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_IncuA025p.select_cop('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA025p.select_cop('" + s + "'):" + ex2.toString());
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
        return um_ICE_InciA040b;
    }
    
    public UM_ICE_InciA040b[] approval_list(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_ICE_InciA040b>();
        try {
            sb.append(" SELECT\t업체명,\t\t사업자등록번호,\t대표자명,\t주민등록번호,\t최초입력일,\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" \t\t본사주소,\t승인여부,\t\t기관구분,\tN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\t(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" SELECT\t업체명,\t\t사업자등록번호,\t대표자명,\t주민등록번호,\tTO_CHAR (최초입력일,'YYYY-MM-DD') 최초입력일,\t\n");
            sb.append(" \t\t본사주소,\t승인여부,\t\t기관구분,\tROWNUM\tN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\t사용_생산업체\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("  WHERE\tROWNUM <= (" + n + " * " + n2 + ")\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            if (s5.equals("S")) {
                if (s.length() >= 1) {
                    sb.append(" AND\t\t업체명 like '%" + s + "%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                }
                if (s2.length() >= 1) {
                    sb.append(" AND\t\t사업자등록번호 like '" + s2 + "%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                }
            }
            if (s6.equals("1")) {
                sb.append(" AND\t\t승인여부 ='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s6.equals("2")) {
                sb.append(" AND\t\t승인여부 ='N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            sb.append("\t\t\t) a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("    WHERE  N BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")\t\t\t\t\t\t\n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_ICE_InciA040b um_ICE_InciA040b = new UM_ICE_InciA040b();
                um_ICE_InciA040b.setupcheNm(executeQuery.getString(1));
                um_ICE_InciA040b.setsaupNo(executeQuery.getString(2));
                um_ICE_InciA040b.setdaepyoNm(executeQuery.getString(3));
                um_ICE_InciA040b.setdaepyojuminNo(executeQuery.getString(4));
                um_ICE_InciA040b.setfirstDay(executeQuery.getString(5));
                um_ICE_InciA040b.setbonsaAdd(executeQuery.getString(6));
                um_ICE_InciA040b.setapproval(executeQuery.getString(7));
                um_ICE_InciA040b.setkigwan(executeQuery.getString(8));
                vector.addElement(um_ICE_InciA040b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_IncuA025p.approval_list():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA025p.approval_list():" + ex2.toString());
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
        final UM_ICE_InciA040b[] array = new UM_ICE_InciA040b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Max_count(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        int int1 = 0;
        try {
            sb.append(" SELECT\tCOUNT(*)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("   FROM\t사용_생산업체\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            if (s6.equals("1")) {
                sb.append(" WHERE\t\t승인여부 ='Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            else if (s6.equals("2")) {
                sb.append(" WHERE\t\t승인여부 ='N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            }
            if (s5.equals("S")) {
                if (s.length() >= 1) {
                    sb.append(" AND\t\t업체명 like '%" + s + "%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                }
                if (s2.length() >= 1) {
                    sb.append(" AND\t\t사업자등록번호 like '" + s2 + "%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                }
            }
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_IncuA025p.Max_count():" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA025p.Max_count():" + ex2.toString());
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
    
    public UM_ICE_InciA040b approval_coperation(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        UM_ICE_InciA040b um_ICE_InciA040b = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" SELECT\tB.id,\t\t\t\tB.password,\t\t\t\tA.업체명,\t\tA.사업자등록번호,\tA.대표자명,\t\tA.주민등록번호,\t\tA.업체형태부호,\t\t\t\t\t\t\t\t\n");
            sb.append(" \t\tA.우편코드,\t\t\tA.본사주소,\t\t\t\tA.본사전화번호,\tA.본사팩스번호,\t\tA.법인구분,\t\tTO_CHAR (A.법인설립일, 'YYYY-MM-DD') 법인설립일,\tA.자본금,\t\n");
            sb.append("\t  \t\tA.종업원수,\t\t\tA.최근3년간총매출액,\tA.홈페이지,\t\tA.업체상태,\t\t\trownum,\t\t\tA.이미지명,\t\t\tA.회사소개,\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tA.주생산품류부호,\tA.업체규모구분,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tTO_CHAR (B.등록일자, 'YYYY-MM-DD') 등록일자,B.등급구분,\t\tB.성명,\t\t\t\tB.주민등록번호 주민,\tB.부서명,\tB.부서장,\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tB.직책,\t\t\t\tB.전자메일,\t\t\t\tB.전화번호,\t\tB.팩스번호,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tA.최초입력자id,\t\tTO_CHAR (A.최초입력일,'YYYY-MM-DD') 최초입력일,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append("\t\t\tA.최근수정자id,\t\tTO_CHAR (A.최근수정일,'YYYY-MM-DD') 최근수정일\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" FROM\t사용_생산업체 A, 사용_생산업체사용자 B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" WHERE\tA.사업자등록번호 = B.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            sb.append(" AND\t\tB.사업자등록번호 = '" + s + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_ICE_InciA040b = new UM_ICE_InciA040b();
                um_ICE_InciA040b.setuserId(executeQuery.getString(1));
                um_ICE_InciA040b.setpassword(executeQuery.getString(2));
                um_ICE_InciA040b.setupcheNm(executeQuery.getString(3));
                um_ICE_InciA040b.setsaupNo(executeQuery.getString(4));
                um_ICE_InciA040b.setdaepyoNm(executeQuery.getString(5));
                um_ICE_InciA040b.setdaepyojuminNo(executeQuery.getString(6));
                um_ICE_InciA040b.setupcheGubun(executeQuery.getString(7));
                um_ICE_InciA040b.setpostCode(executeQuery.getString(8));
                um_ICE_InciA040b.setbonsaAdd(executeQuery.getString(9));
                um_ICE_InciA040b.setbonsaTel(executeQuery.getString(10));
                um_ICE_InciA040b.setbonsaFax(executeQuery.getString(11));
                um_ICE_InciA040b.setbukinGubun(executeQuery.getString(12));
                um_ICE_InciA040b.setestablish(executeQuery.getString(13));
                um_ICE_InciA040b.setmoney(executeQuery.getString(14));
                um_ICE_InciA040b.setworker(executeQuery.getString(15));
                um_ICE_InciA040b.setoutput(executeQuery.getString(16));
                um_ICE_InciA040b.sethomepage(executeQuery.getString(17));
                um_ICE_InciA040b.setupchesGubun(executeQuery.getString(18));
                um_ICE_InciA040b.setimageNm(executeQuery.getString(20));
                um_ICE_InciA040b.setcopInt(executeQuery.getString(21));
                um_ICE_InciA040b.setsaensanGubun(executeQuery.getString(22));
                um_ICE_InciA040b.setupchekGubun(executeQuery.getString(23));
                um_ICE_InciA040b.setinsertDay(executeQuery.getString(24));
                um_ICE_InciA040b.setgradeGubun(executeQuery.getString(25));
                um_ICE_InciA040b.setname(executeQuery.getString(26));
                um_ICE_InciA040b.setjuminNo(executeQuery.getString(27));
                um_ICE_InciA040b.setbuserNm(executeQuery.getString(28));
                um_ICE_InciA040b.setbuserJang(executeQuery.getString(29));
                um_ICE_InciA040b.setduty(executeQuery.getString(30));
                um_ICE_InciA040b.setmail(executeQuery.getString(31));
                um_ICE_InciA040b.settelNo(executeQuery.getString(32));
                um_ICE_InciA040b.setfaxNo(executeQuery.getString(33));
                um_ICE_InciA040b.setfirstId(executeQuery.getString(34));
                um_ICE_InciA040b.setfirstDay(executeQuery.getString(35));
                um_ICE_InciA040b.setupdateId(executeQuery.getString(36));
                um_ICE_InciA040b.setupdateDay(executeQuery.getString(37));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_IncuA025p.approval_coperation('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_IncuA025p.approval_coperation('" + s + "'):" + ex2.toString());
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
        return um_ICE_InciA040b;
    }
}
