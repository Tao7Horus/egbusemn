// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_ADE_GovuA040b;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import entity.UM_GOJ_GovuA010b;

public class UM_RAM_MasterSelect
{
    public UM_GOJ_GovuA010b govSelect(final String s) throws Exception, SQLException {
        Trx trx = null;
        Connection connection = null;
        UM_GOJ_GovuA010b um_GOJ_GovuA010b = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String string = "select 공공기관코드,공공기관명_전체,공공기관명_약어,공공기관명_영문,사업자등록번호,업태,업종,담당자명,담당자부서명,담당자전화번호,담당자팩스번호,담당자메일주소,채권자명,소관구분,우편번호,주소,나머지주소,전화번호,팩스번호,전수요기관코드,물품관리관명,양허코드,관할지청,전자인증여부,특정관리기관,지청구분,운송거리,홈페이지주소,to_char(등록일자,'yyyy-mm-dd'),처리자ID,삭제여부,to_char(갱신일자,'yyyy-mm-dd'),지역코드,금결원승인번호, 최상위기관코드,기관유형구분,접수번호,이력코드  from 사용_공공기관마스터 where 공공기관코드='" + s + "'";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_GOJ_GovuA010b = new UM_GOJ_GovuA010b();
                um_GOJ_GovuA010b.setg2bCode(executeQuery.getString(1));
                um_GOJ_GovuA010b.setgoNameFull(executeQuery.getString(2));
                um_GOJ_GovuA010b.setgoNameShort(executeQuery.getString(3));
                um_GOJ_GovuA010b.setgoNameEn(executeQuery.getString(4));
                um_GOJ_GovuA010b.setsaupNo(executeQuery.getString(5));
                um_GOJ_GovuA010b.setbuConditon(executeQuery.getString(6));
                um_GOJ_GovuA010b.setbuType(executeQuery.getString(7));
                um_GOJ_GovuA010b.settaskmaster(executeQuery.getString(8));
                um_GOJ_GovuA010b.setmasterPost(executeQuery.getString(9));
                um_GOJ_GovuA010b.setmasterTel(executeQuery.getString(10));
                um_GOJ_GovuA010b.setmasterFax(executeQuery.getString(11));
                um_GOJ_GovuA010b.setmasterMail(executeQuery.getString(12));
                um_GOJ_GovuA010b.setcreditName(executeQuery.getString(13));
                um_GOJ_GovuA010b.setrelation(executeQuery.getString(14));
                um_GOJ_GovuA010b.setZIPCODE(executeQuery.getString(15));
                um_GOJ_GovuA010b.setADDR(executeQuery.getString(16));
                um_GOJ_GovuA010b.setaddress2(executeQuery.getString(17));
                um_GOJ_GovuA010b.settelNum(executeQuery.getString(18));
                um_GOJ_GovuA010b.setfaxNum(executeQuery.getString(19));
                um_GOJ_GovuA010b.setorganCode(executeQuery.getString(20));
                um_GOJ_GovuA010b.setgoodsMaster(executeQuery.getString(21));
                um_GOJ_GovuA010b.setpermitCode(executeQuery.getString(22));
                um_GOJ_GovuA010b.setprovince(executeQuery.getString(23));
                um_GOJ_GovuA010b.seteCitation(executeQuery.getString(24));
                um_GOJ_GovuA010b.setspOffice(executeQuery.getString(25));
                um_GOJ_GovuA010b.setbranchOffi(executeQuery.getString(26));
                um_GOJ_GovuA010b.settrDistance(executeQuery.getString(27));
                um_GOJ_GovuA010b.sethomepage(executeQuery.getString(28));
                um_GOJ_GovuA010b.setentryDate(executeQuery.getString(29));
                um_GOJ_GovuA010b.setmasterID(executeQuery.getString(30));
                um_GOJ_GovuA010b.setdelete(executeQuery.getString(31));
                um_GOJ_GovuA010b.setmoDate(executeQuery.getString(32));
                um_GOJ_GovuA010b.setlocaCode(executeQuery.getString(33));
                um_GOJ_GovuA010b.setmoney(executeQuery.getString(34));
                um_GOJ_GovuA010b.setsubCode(executeQuery.getString(35));
                um_GOJ_GovuA010b.setchgType(executeQuery.getString(36));
                um_GOJ_GovuA010b.setseqNumber(executeQuery.getString(37));
                um_GOJ_GovuA010b.setbeforeCode(executeQuery.getString(38));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_MasterSelect.select_goma('" + s + "'):" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_MasterSelect.select_goma('" + s + "'):" + ex2.toString());
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
        return um_GOJ_GovuA010b;
    }
    
    public UM_GOJ_GovuA010b[] SynGovSelect(final String s, final String s2, final String s3, final String s4, final String s5) throws Exception, SQLException {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_GOJ_GovuA010b>();
        String s6 = "";
        try {
            if (s4.equals("01")) {
                s6 = "사업자등록번호";
            }
            sb.append(" select 공공기관코드,공공기관명_전체,공공기관명_약어,공공기관명_영문,사업자등록번호,업태,업종,담당자명,\n");
            sb.append(" \t\t담당자부서명,담당자전화번호,담당자팩스번호,담당자메일주소,채권자명,소관구분,우편번호,주소,나머지주소,전화번호,\n");
            sb.append(" \t\t팩스번호,전수요기관코드,물품관리관명,양허코드,관할지청,전자인증여부,특정관리기관,지청구분,운송거리,홈페이지주소,\n");
            sb.append(" \t\tto_char(등록일자,'yyyy-mm-dd'),처리자ID,삭제여부,to_char(갱신일자,'yyyy-mm-dd'),지역코드,금결원승인번호, 최상위기관코드,기관유형구분,접수번호,이력코드 \n");
            sb.append(" FROM    사용_공공기관마스터\t\n");
            sb.append(" WHERE   공공기관코드 is not null  \n");
            if (!s.equals("")) {
                sb.append(" and 공공기관코드='" + s + "' \n");
            }
            if (!s2.equals("")) {
                sb.append(" and 최상위기관코드='" + s2 + "' \n");
            }
            if (!s3.equals("")) {
                sb.append(" and 공공기관명_전체 like '%" + s3 + "%' \n");
            }
            if (!s4.equals("00")) {
                sb.append(" and " + s6 + "='" + s5 + "' \n");
            }
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_GOJ_GovuA010b um_GOJ_GovuA010b = new UM_GOJ_GovuA010b();
                um_GOJ_GovuA010b.setg2bCode(executeQuery.getString(1));
                um_GOJ_GovuA010b.setgoNameFull(executeQuery.getString(2));
                um_GOJ_GovuA010b.setgoNameShort(executeQuery.getString(3));
                um_GOJ_GovuA010b.setgoNameEn(executeQuery.getString(4));
                um_GOJ_GovuA010b.setsaupNo(executeQuery.getString(5));
                um_GOJ_GovuA010b.setbuConditon(executeQuery.getString(6));
                um_GOJ_GovuA010b.setbuType(executeQuery.getString(7));
                um_GOJ_GovuA010b.settaskmaster(executeQuery.getString(8));
                um_GOJ_GovuA010b.setmasterPost(executeQuery.getString(9));
                um_GOJ_GovuA010b.setmasterTel(executeQuery.getString(10));
                um_GOJ_GovuA010b.setmasterFax(executeQuery.getString(11));
                um_GOJ_GovuA010b.setmasterMail(executeQuery.getString(12));
                um_GOJ_GovuA010b.setcreditName(executeQuery.getString(13));
                um_GOJ_GovuA010b.setrelation(executeQuery.getString(14));
                um_GOJ_GovuA010b.setZIPCODE(executeQuery.getString(15));
                um_GOJ_GovuA010b.setADDR(executeQuery.getString(16));
                um_GOJ_GovuA010b.setaddress2(executeQuery.getString(17));
                um_GOJ_GovuA010b.settelNum(executeQuery.getString(18));
                um_GOJ_GovuA010b.setfaxNum(executeQuery.getString(19));
                um_GOJ_GovuA010b.setorganCode(executeQuery.getString(20));
                um_GOJ_GovuA010b.setgoodsMaster(executeQuery.getString(21));
                um_GOJ_GovuA010b.setpermitCode(executeQuery.getString(22));
                um_GOJ_GovuA010b.setprovince(executeQuery.getString(23));
                um_GOJ_GovuA010b.seteCitation(executeQuery.getString(24));
                um_GOJ_GovuA010b.setspOffice(executeQuery.getString(25));
                um_GOJ_GovuA010b.setbranchOffi(executeQuery.getString(26));
                um_GOJ_GovuA010b.settrDistance(executeQuery.getString(27));
                um_GOJ_GovuA010b.sethomepage(executeQuery.getString(28));
                um_GOJ_GovuA010b.setentryDate(executeQuery.getString(29));
                um_GOJ_GovuA010b.setmasterID(executeQuery.getString(30));
                um_GOJ_GovuA010b.setdelete(executeQuery.getString(31));
                um_GOJ_GovuA010b.setmoDate(executeQuery.getString(32));
                um_GOJ_GovuA010b.setlocaCode(executeQuery.getString(33));
                um_GOJ_GovuA010b.setmoney(executeQuery.getString(34));
                um_GOJ_GovuA010b.setsubCode(executeQuery.getString(35));
                um_GOJ_GovuA010b.setchgType(executeQuery.getString(36));
                um_GOJ_GovuA010b.setseqNumber(executeQuery.getString(37));
                um_GOJ_GovuA010b.setbeforeCode(executeQuery.getString(38));
                vector.addElement(um_GOJ_GovuA010b);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_MasterSelect.SynGovSelect:" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_MasterSelect.SynGovSelect:" + ex2.toString());
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
        final UM_GOJ_GovuA010b[] array = new UM_GOJ_GovuA010b[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Syncount(final String s, final String s2, final String s3, final String s4, final String s5) {
        Trx trx = null;
        Connection connection = null;
        final StringBuffer sb = new StringBuffer();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        String s6 = "";
        try {
            if (s4.equals("01")) {
                s6 = "사업자등록번호";
            }
            sb.append(" select  count(*) \n");
            sb.append(" FROM    사용_공공기관마스터\t\n");
            sb.append(" WHERE   공공기관코드 is not null   \n");
            if (!s.equals("")) {
                sb.append(" and 공공기관코드='" + s + "' \n");
            }
            if (!s2.equals("")) {
                sb.append(" and 최상위기관코드='" + s2 + "' \n");
            }
            if (!s3.equals("")) {
                sb.append(" and 공공기관명_전체 like '%" + s3 + "%' \n");
            }
            if (!s4.equals("00")) {
                sb.append(" and " + s6 + "='" + s5 + "' \n");
            }
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_MasterSelect.Syncount:" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_MasterSelect.Syncount:" + ex2.toString());
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
    
    public UM_ADE_GovuA040b[] select_math(final String s) {
        Trx trx = null;
        Connection connection = null;
        final Vector vector = new Vector<UM_ADE_GovuA040b>();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        try {
            final String s2 = "select 공공기관회계코드 from 사용_공공기관회계코드  where 공공기관코드=? ";
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
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_MasterSelect.select_math('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_MasterSelect.select_math('" + s + "'):" + ex2.toString());
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
    
    public int Max_count(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String s2 = "\n SELECT count(*) FROM 사용_공공기관회계코드 \n WHERE 공공기관코드 = ? ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_MasterSelect.Max_count('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_MasterSelect.Max_count('" + s + "'):" + ex2.toString());
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
    
    public int isMasterExist(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String s2 = "\n SELECT count(*) FROM 사용_공공기관마스터 \n WHERE 공공기관코드 = ? ";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_MasterSelect.isMasterExist('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_MasterSelect.isMasterExist('" + s + "'):" + ex2.toString());
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
