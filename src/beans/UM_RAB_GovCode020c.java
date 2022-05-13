// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_ADE_GovuA040b;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.util.Vector;
import entity.UM_RAE_GovCode030b;

public class UM_RAB_GovCode020c
{
    public UM_RAE_GovCode030b[] approvalList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAE_GovCode030b>();
        final String string = s2 + "00:00:00";
        final String string2 = s3 + "23:59:59";
        try {
            sb.append(" SELECT\t공공기관명_전체, 사업자등록번호, 담당자명, 주소, \n");
            sb.append(" \t\t코드체크여부, 접수번호, 공공기관코드, N \n");
            sb.append("   FROM\t( \n");
            sb.append(" SELECT\t공공기관명_전체, 사업자등록번호, 담당자명, 주소||' '||나머지주소 주소, \n");
            sb.append(" \t\t코드체크여부, 접수번호, 공공기관코드, ROWNUM N \n");
            sb.append("   FROM\t사용_공공기관마스터 \n");
            sb.append("  WHERE\tROWNUM <= (" + n + " * " + n2 + ") \n");
            if (s4.equals("s")) {
                if (!s.equals("")) {
                    sb.append(" AND\t\t공공기관명_전체 like '%" + s + "%' \n");
                }
                if (!s2.equals("")) {
                    sb.append(" AND\t\t등록일자 BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') \n");
                    sb.append("                     AND TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')\t\n");
                }
            }
            if (s5.equals("1")) {
                sb.append(" AND\t\t코드체크여부 ='Y'\t\n");
            }
            else if (s5.equals("2")) {
                sb.append(" AND\t\t코드체크여부 ='N'\t\n");
            }
            else {
                sb.append(" AND\t\t코드체크여부 like '%%'\t\n");
            }
            sb.append("\t\t\t) a \n");
            sb.append("    WHERE  N BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ") \n");
            final String string3 = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string3);
            if (s2.length() >= 1) {
                prepareStatement.setString(1, string);
                prepareStatement.setString(2, string2);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAE_GovCode030b um_RAE_GovCode030b = new UM_RAE_GovCode030b();
                um_RAE_GovCode030b.setgoNameFull(executeQuery.getString(1));
                um_RAE_GovCode030b.setsaupNo(executeQuery.getString(2));
                um_RAE_GovCode030b.settaskmaster(executeQuery.getString(3));
                um_RAE_GovCode030b.setADDR(executeQuery.getString(4));
                um_RAE_GovCode030b.setcodeCheckYN(executeQuery.getString(5));
                um_RAE_GovCode030b.setdependCode(executeQuery.getString(6));
                um_RAE_GovCode030b.setg2bCode(executeQuery.getString(7));
                vector.addElement(um_RAE_GovCode030b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_GovCode020c.approvalList(upcheNm[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_GovCode020c.approvalList(upcheNm[" + s + "]):" + ex2.toString());
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
        final UM_RAE_GovCode030b[] array = new UM_RAE_GovCode030b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int approvalList_count(final String s, final String s2, final String s3, final String s4, final String s5) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        final String string = s2 + "00:00:00";
        final String string2 = s3 + "23:59:59";
        int int1 = 0;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  사용_공공기관마스터\t\t\t\n");
            if (s5.equals("1")) {
                sb.append(" WHERE\t\t코드체크여부 ='Y'\t\t\t\t\t\t\t\n");
            }
            else if (s5.equals("2")) {
                sb.append(" WHERE\t\t코드체크여부 ='N'\t\t\t\t\t\t\t\n");
            }
            else {
                sb.append(" WHERE\t\t코드체크여부 like '%%'  \t\t\t\t\t\n");
            }
            if (s4.equals("s")) {
                if (!s.equals("")) {
                    sb.append(" AND\t\t공공기관명_전체 like '%" + s + "%'\t\n");
                }
                if (!s2.equals("")) {
                    sb.append(" AND\t\t등록일자 BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')     \n");
                    sb.append("                      AND TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')\t \n");
                }
            }
            prepareStatement = connection.prepareStatement(sb.toString());
            if (s2.length() >= 1) {
                prepareStatement.setString(1, string);
                prepareStatement.setString(2, string2);
            }
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_GovCode020c.approvalList_count(upcheNm[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_GovCode020c.approvalList_count(upcheNm[" + s + "]):" + ex2.toString());
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
    
    public UM_RAE_GovCode030b select_gov(final String s) throws Exception, SQLException {
        Trx trx = null;
        Connection connection = null;
        UM_RAE_GovCode030b um_RAE_GovCode030b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "SELECT    공공기관코드,   공공기관명_전체, 공공기관명_약어, 공공기관명_영문, 사업자등록번호, \n          업태,          업종,          담당자명,        담당자부서명,   담당자전화번호, \n          담당자팩스번호, 담당자메일주소,  채권자명,        소관구분,       지역코드, \n           우편번호,       주소,          나머지주소,      전화번호,       팩스번호, \n           전수요기관코드,  물품관리관명,   양허코드,        관할지청,       전자인증여부, \n          특정관리기관,   지청구분,       운송거리,        홈페이지주소,    금결원승인번호, \n          to_char(등록일자,'yyyymmdd'),  처리자ID,       삭제여부, to_char(갱신일자, 'yyyymmdd'), \n          접수번호,       최상위기관코드,  기관유형구분,    이력코드,       기관유형_중, \n           기관유형_소,    코드체크여부 \n \tFROM 사용_공공기관마스터  \n WHERE 공공기관코드='" + s + "' \n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_RAE_GovCode030b = new UM_RAE_GovCode030b();
                um_RAE_GovCode030b.setg2bCode(executeQuery.getString(1));
                um_RAE_GovCode030b.setgoNameFull(executeQuery.getString(2));
                um_RAE_GovCode030b.setgoNameShort(executeQuery.getString(3));
                um_RAE_GovCode030b.setgoNameEn(executeQuery.getString(4));
                um_RAE_GovCode030b.setsaupNo(executeQuery.getString(5).trim());
                um_RAE_GovCode030b.setbuConditon(executeQuery.getString(6));
                um_RAE_GovCode030b.setbuType(executeQuery.getString(7));
                um_RAE_GovCode030b.settaskmaster(executeQuery.getString(8));
                um_RAE_GovCode030b.setmasterPost(executeQuery.getString(9));
                um_RAE_GovCode030b.setmasterTel(executeQuery.getString(10));
                um_RAE_GovCode030b.setmasterFax(executeQuery.getString(11));
                um_RAE_GovCode030b.setmasterMail(executeQuery.getString(12));
                um_RAE_GovCode030b.setcreditName(executeQuery.getString(13));
                um_RAE_GovCode030b.setrelation(executeQuery.getString(14));
                um_RAE_GovCode030b.setregionCode(executeQuery.getString(15));
                um_RAE_GovCode030b.setZIPCODE(executeQuery.getString(16));
                um_RAE_GovCode030b.setADDR(executeQuery.getString(17));
                um_RAE_GovCode030b.setaddress2(executeQuery.getString(18));
                um_RAE_GovCode030b.settelNum(executeQuery.getString(19));
                um_RAE_GovCode030b.setfaxNum(executeQuery.getString(20));
                um_RAE_GovCode030b.setorganCode(executeQuery.getString(21));
                um_RAE_GovCode030b.setgoodsMaster(executeQuery.getString(22));
                um_RAE_GovCode030b.setpermitCode(executeQuery.getString(23));
                um_RAE_GovCode030b.setprovince(executeQuery.getString(24));
                um_RAE_GovCode030b.seteCitation(executeQuery.getString(25));
                um_RAE_GovCode030b.setspOffice(executeQuery.getString(26));
                um_RAE_GovCode030b.setbranchOffi(executeQuery.getString(27));
                um_RAE_GovCode030b.settrDistance(executeQuery.getString(28));
                um_RAE_GovCode030b.sethomepage(executeQuery.getString(29));
                um_RAE_GovCode030b.setmoney(executeQuery.getString(30));
                um_RAE_GovCode030b.setcreateDate(executeQuery.getString(31));
                um_RAE_GovCode030b.setmasterID(executeQuery.getString(32));
                um_RAE_GovCode030b.setdelete(executeQuery.getString(33));
                um_RAE_GovCode030b.setmoDate(executeQuery.getString(34));
                um_RAE_GovCode030b.setdependCode(executeQuery.getString(35));
                um_RAE_GovCode030b.setfirstUpperCode(executeQuery.getString(36));
                um_RAE_GovCode030b.setorganL(executeQuery.getString(37));
                um_RAE_GovCode030b.sethistoryCode(executeQuery.getString(38));
                um_RAE_GovCode030b.setorganM(executeQuery.getString(39));
                um_RAE_GovCode030b.setorganS(executeQuery.getString(40));
                um_RAE_GovCode030b.setcodeCheckYN(executeQuery.getString(41));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_GovCode020c.select_gov('" + s + "') :" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_GovCode020c.select_gov('" + s + "') :" + ex2.toString());
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
        return um_RAE_GovCode030b;
    }
    
    public UM_ADE_GovuA040b[] select_math(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_ADE_GovuA040b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String s2 = "select 공공기관회계코드 from 사용_공공기관회계코드 where 공공기관코드 =? ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                final UM_ADE_GovuA040b um_ADE_GovuA040b = new UM_ADE_GovuA040b();
                um_ADE_GovuA040b.setmathCode(executeQuery.getString(1));
                vector.addElement(um_ADE_GovuA040b);
            }
            executeQuery.close();
            prepareStatement.close();
            connection.close();
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_GovCode020c.select_math('" + s + "') :" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_GovCode020c.select_math('" + s + "') :" + ex2.toString());
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
        final UM_ADE_GovuA040b[] array = new UM_ADE_GovuA040b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public String getBranch(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = "";
        try {
            final String s2 = "select 코드명 from SYN_공동코드 where 코드구분 = 'J03' and 코드 = ? ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_GovCode020c.getBranch('" + s + "') :" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_GovCode020c.getBranch('" + s + "') :" + ex2.toString());
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
        return string;
    }
    
    public String getOrgDiv(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = "";
        try {
            final String s2 = "select 코드명 from SYN_공동코드 where 코드구분 = 'J51' and 코드 = ? ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_GovCode020c.getOrgDiv('" + s + "') :" + ex.toString());
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_GovCode020c.getOrgDiv('" + s + "') :" + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return string;
    }
    
    public String getOrgLaw(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = "";
        try {
            final String s2 = "select 코드명 from SYN_공동코드 where 코드구분 = 'GGL' and 코드 = ? ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAB_GovCode020c.getOrgLaw('" + s + "') :" + ex.toString());
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            Log.debug("UM_RAB_GovCode020c.getOrgLaw('" + s + "') :" + ex2.toString());
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
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return string;
    }
}
