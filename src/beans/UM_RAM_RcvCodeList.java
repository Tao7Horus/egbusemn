// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import entity.UM_RAM_UsrInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.QueryUtil;
import common.Trx;
import java.util.Vector;
import entity.UM_RAM_RcvCode;

public class UM_RAM_RcvCodeList
{
    public UM_RAM_RcvCode[] approvalList(final int n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAM_RcvCode>();
        final String string = s3 + "00:00:01";
        final String string2 = s4 + "23:59:59";
        int gov_verify = 0;
        final Vector vector2 = new Vector<String>(1, 1);
        try {
            sb.append(" SELECT\t구분자,DML구분,행정표준기관코드,기관명,최상위기관코드,차상위기관코드,수신일자,상태,이전기관코드,N \n");
            sb.append("   FROM\t(\n");
            sb.append(" SELECT\t구분자, \n");
            sb.append("\t\t\tDML구분,행정표준기관코드,기관명,최상위기관코드,차상위기관코드,to_char(수신일자,'YYYY/MM/DD') 수신일자,상태,이전기관코드,ROWNUM N \n");
            sb.append("   FROM\t사용_행정표준정보수신\n");
            sb.append("  WHERE\tROWNUM <= (? * ?)\n");
            vector2.addElement(Integer.toString(n));
            vector2.addElement(Integer.toString(n2));
            if (!s3.equals("")) {
                sb.append(" AND\t\t수신일자 BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')     \n");
                sb.append("                 AND TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')\t    \n");
                vector2.addElement(string);
                vector2.addElement(string2);
            }
            if (!s.equals("")) {
                sb.append(" AND\t\t상태 =? \n");
                vector2.addElement(s);
            }
            if (!s2.equals("")) {
                sb.append(" AND\t\t구분자 =? \n");
                vector2.addElement(s2);
            }
            if (!s5.equals("")) {
                sb.append(" AND\t\t행정표준기관코드 =?\n");
                vector2.addElement(s5);
            }
            if (!s6.equals("")) {
                sb.append(" AND\t\t기관명 like '%'||?||'%'\n");
                vector2.addElement(s6);
            }
            sb.append("\t   ) a\t\t\t\n");
            sb.append("    WHERE  N BETWEEN (((? - 1) * ?)+1) AND (? * ?)\t\t\n");
            vector2.addElement(Integer.toString(n));
            vector2.addElement(Integer.toString(n2));
            vector2.addElement(Integer.toString(n));
            vector2.addElement(Integer.toString(n2));
            final String[] parameter = new String[vector2.size()];
            vector2.copyInto(parameter);
            final String string3 = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string3);
            new QueryUtil().setPreparedValues(this, prepareStatement, parameter);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAM_RcvCode um_RAM_RcvCode = new UM_RAM_RcvCode();
                um_RAM_RcvCode.setgubun(executeQuery.getString(1));
                um_RAM_RcvCode.setdmlFlag(executeQuery.getString(2));
                final String string4 = executeQuery.getString(3);
                um_RAM_RcvCode.setgccCode(string4);
                final int gov_verify2 = this.gov_verify(string4, connection);
                um_RAM_RcvCode.setgoNameFull(executeQuery.getString(4));
                um_RAM_RcvCode.setmostUpper(executeQuery.getString(5));
                um_RAM_RcvCode.setfirstUpper(executeQuery.getString(6));
                um_RAM_RcvCode.setrcvDate(executeQuery.getString(7));
                um_RAM_RcvCode.setstatusRcv(executeQuery.getString(8));
                final String string5 = executeQuery.getString(9);
                um_RAM_RcvCode.setbeforeCode(string5);
                final Vector string6 = this.parseString(string5, ",");
                if (string6 == null) {
                    gov_verify = this.gov_verify(string5, connection);
                }
                else {
                    for (int i = 0; i < string6.size(); ++i) {
                        gov_verify += this.gov_verify(string6.get(i), connection);
                    }
                }
                final int gov_verify3 = this.gov_verify("", connection);
                if (gov_verify2 > 0 || gov_verify > 0 || gov_verify3 > 0) {
                    um_RAM_RcvCode.setgovFlag("Y");
                }
                else {
                    um_RAM_RcvCode.setgovFlag("N");
                }
                if (this.org_verify(string4, connection) > 0) {
                    String chgGubun2 = this.getChgGubun2(string4, connection);
                    if (chgGubun2 == null || chgGubun2.equals("")) {
                        chgGubun2 = "02";
                    }
                    um_RAM_RcvCode.setRealChg(chgGubun2);
                }
                vector.addElement(um_RAM_RcvCode);
                gov_verify = 0;
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.approvalList(chgflag[" + s + "], chgGn[" + s2 + "], srchcode[" + s5 + "], srchname[" + s6 + "] ):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.approvalList(chgflag[" + s + "], chgGn[" + s2 + "], srchcode[" + s5 + "], srchname[" + s6 + "] ):" + ex2.toString());
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
        final UM_RAM_RcvCode[] array = new UM_RAM_RcvCode[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int approvalList_count(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        final String string = s3 + "00:00:01";
        final String string2 = s4 + "23:59:59";
        int int1 = 0;
        final Vector vector = new Vector<String>(1, 1);
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  사용_행정표준정보수신\t\t\t\n");
            if (!s3.equals("")) {
                sb.append(" WHERE\t\t수신일자 BETWEEN TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')     \n");
                sb.append("                 AND TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS')\t    \n");
                vector.addElement(string);
                vector.addElement(string2);
            }
            if (!s.equals("")) {
                sb.append(" AND\t\t상태 =? \n");
                vector.addElement(s);
            }
            if (!s2.equals("")) {
                sb.append(" AND\t\t구분자 =? \n");
                vector.addElement(s2);
            }
            if (!s5.equals("")) {
                sb.append(" AND\t\t행정표준기관코드 =?\n");
                vector.addElement(s5);
            }
            if (!s6.equals("")) {
                sb.append(" AND\t\t기관명 like '%'||?||'%'\n");
                vector.addElement(s6);
            }
            prepareStatement = connection.prepareStatement(sb.toString());
            final String[] parameter = new String[vector.size()];
            vector.copyInto(parameter);
            new QueryUtil().setPreparedValues(this, prepareStatement, parameter);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.approvalList_count(chgflag[" + s + "],chgGn[" + s2 + "], srchcode[" + s5 + "], srchname[" + s6 + "] ):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.approvalList_count(chgflag[" + s + "],chgGn[" + s2 + "], srchcode[" + s5 + "], srchname[" + s6 + "] ):" + ex2.toString());
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
    
    public int org_verify(final String s, final Connection connection) {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        int int1 = 0;
        try {
            sb.append(" SELECT\tCOUNT(*) FROM  사용_행정표준정보\t\t\t\n");
            sb.append(" WHERE\t행정표준기관코드 =? \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.gov_verify(code[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.gov_verify(code[" + s + "]):" + ex2.toString());
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
        }
        return int1;
    }
    
    public int gov_verify(final String s, Connection connection) {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        int int1 = 0;
        Trx trx = null;
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            sb.append(" SELECT\tCOUNT(*) FROM  사용_공공기관마스터\t\t\t\n");
            sb.append(" WHERE\t공공기관코드 =? \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.gov_verify(code[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.gov_verify(code[" + s + "]):" + ex2.toString());
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
            if (b && connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public int gov_verify2(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        int int1 = 0;
        try {
            sb.append(" SELECT\tcount(*) FROM  사용_공공기관마스터\n");
            sb.append(" WHERE\t공공기관코드 =? \n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.gov_verify2(code[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.gov_verify2(code[" + s + "]):" + ex2.toString());
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
    
    public String getChgGubun(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\t변경유형구분 FROM  사용_행정표준정보\n");
            sb.append(" WHERE\t행정표준기관코드 =? \n");
            final String string2 = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.getChgGubun(code[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.getChgGubun(code[" + s + "]):" + ex2.toString());
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
    
    public String getChgGubun2(final String s, final Connection connection) {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\t변경유형구분 FROM  사용_행정표준정보\n");
            sb.append(" WHERE\t행정표준기관코드 =? \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.getChgGubun2(code[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.getChgGubun2(code[" + s + "]):" + ex2.toString());
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
        }
        return string;
    }
    
    public String getOrgCode(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\t행정표준기관코드 FROM  사용_행정표준정보변경\n");
            sb.append(" WHERE\t이전코드 =? \n");
            sb.append(" AND \t이력구분 in ('NEW','MOD')\n");
            final String string2 = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                string += executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.getOrgCode(code[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.getOrgCode(code[" + s + "]):" + ex2.toString());
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
    
    public String getOrgCode2(final Connection connection, final String s) {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\t행정표준기관코드 FROM  사용_행정표준정보변경\n");
            sb.append(" WHERE\t이전코드 =?\n");
            sb.append(" AND \t이력구분 in ('NEW','MOD')\n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                string += executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.getOrgCode2(code[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.getOrgCode2(code[" + s + "]):" + ex2.toString());
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
        }
        return string;
    }
    
    public String getgoName(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\t공공기관명_전체 FROM  사용_공공기관마스터\n");
            sb.append(" WHERE\t공공기관코드 =? \n");
            final String string2 = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.getgoName(code[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.getgoName(code[" + s + "]):" + ex2.toString());
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
    
    public String getgoName2(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        try {
            sb.append(" SELECT\t기관명 FROM  사용_행정표준정보수신\n");
            sb.append(" WHERE\t행정표준기관코드 =? \n");
            final String string2 = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.getgoName(code[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.getgoName(code[" + s + "]):" + ex2.toString());
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
    
    public UM_RAM_RcvCode gccDetail(final String s, final String s2, final String s3, final String s4) {
        Trx trx = null;
        Connection connection = null;
        final UM_RAM_RcvCode um_RAM_RcvCode = new UM_RAM_RcvCode();
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        final String string = s3 + " 00:00:01";
        final String string2 = s4 + " 23:59:59";
        final Vector vector = new Vector<String>(1, 1);
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append(" SELECT\t 행정표준기관코드,to_char(수신일자,'YYYY/MM/DD'),DML구분,\n");
            sb.append(" \t\t to_char(생성일자,'YYYY/MM/DD'),decode(구분자,'01','신설','02','폐지','03','개칭','04','치','05','분','06','합','07','흡수'),\n");
            sb.append(" \t\t 기관명,차수, 서열, 차상위기관코드,최상위기관코드,\n");
            sb.append(" \t\t 기관유형_대,기관유형_중,기관유형_소,기관장직급명,기관장직위코드,이전기관코드,\n");
            sb.append(" \t\t decode(상태,'0','연계정보수신','2','문서발송대기','3','폐지처리대기','9','변경완료') 상태 \n");
            sb.append(" FROM  사용_행정표준정보수신\t\t\t\t\t\t\n");
            sb.append(" WHERE  행정표준기관코드=?\t\t\t\t\n");
            vector.addElement(s);
            if (s2.length() > 0) {
                sb.append(" AND  구분자=?\t\t\t\t\n");
                vector.addElement(s2);
            }
            if (!s3.equals("")) {
                sb.append(" AND\t\t수신일자 BETWEEN TO_DATE(?, 'YYYYMMDD HH24:MI:SS')     \n");
                sb.append("                 AND TO_DATE(?, 'YYYYMMDD HH24:MI:SS')\t    \n");
                vector.addElement(string);
                vector.addElement(string2);
            }
            prepareStatement = connection.prepareStatement(sb.toString());
            final String[] parameter = new String[vector.size()];
            vector.copyInto(parameter);
            new QueryUtil().setPreparedValues(this, prepareStatement, parameter);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_RAM_RcvCode.setgccCode(executeQuery.getString(1));
                um_RAM_RcvCode.setrcvDate(executeQuery.getString(2));
                um_RAM_RcvCode.setdmlFlag(executeQuery.getString(3));
                um_RAM_RcvCode.setcreateDate(executeQuery.getString(4));
                um_RAM_RcvCode.setgubun(executeQuery.getString(5));
                um_RAM_RcvCode.setgoNameFull(executeQuery.getString(6));
                um_RAM_RcvCode.setdegree(executeQuery.getString(7));
                um_RAM_RcvCode.setrank(executeQuery.getString(8));
                um_RAM_RcvCode.setfirstUpper(executeQuery.getString(9));
                um_RAM_RcvCode.setmostUpper(executeQuery.getString(10));
                um_RAM_RcvCode.settypeLarge(executeQuery.getString(11));
                um_RAM_RcvCode.settypeMiddle(executeQuery.getString(12));
                um_RAM_RcvCode.settypeSmall(executeQuery.getString(13));
                um_RAM_RcvCode.setmasterTask(executeQuery.getString(14));
                um_RAM_RcvCode.settaskCode(executeQuery.getString(15));
                um_RAM_RcvCode.setbeforeCode(executeQuery.getString(16));
                um_RAM_RcvCode.setstatusRcv(executeQuery.getString(17));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.gccDetail(srchcode[" + s + "], gubun[" + s2 + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.gccDetail(srchcode[" + s + "], gubun[" + s2 + "]):" + ex2.toString());
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
        return um_RAM_RcvCode;
    }
    
    public UM_RAM_UsrInfo[] usrDetail(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAM_UsrInfo>();
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append(" SELECT\t사용자ID ,담당자명, 담당자부서명, 담당자전화번호, 담당자팩스번호, \n");
            sb.append(" \t\t담당자메일주소, 마스터코드,승인여부\n");
            sb.append(" FROM    사용_사용자\t\t\t\t\t\t\n");
            sb.append(" WHERE   마스터코드=?\t\t\t\n");
            sb.append(" AND     승인여부='2'\t\t\t\t\n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAM_UsrInfo um_RAM_UsrInfo = new UM_RAM_UsrInfo();
                um_RAM_UsrInfo.setusrID(executeQuery.getString(1));
                um_RAM_UsrInfo.setusrName(executeQuery.getString(2));
                um_RAM_UsrInfo.setpartName(executeQuery.getString(3));
                um_RAM_UsrInfo.setTelNumber(executeQuery.getString(4));
                um_RAM_UsrInfo.setFAXNumber(executeQuery.getString(5));
                um_RAM_UsrInfo.setmailAdd(executeQuery.getString(6));
                um_RAM_UsrInfo.setmasterCode(executeQuery.getString(7));
                um_RAM_UsrInfo.setappFlag(executeQuery.getString(8));
                vector.addElement(um_RAM_UsrInfo);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_UsrInfo.usrDetail(srchcode[" + s + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_UsrInfo.usrDetail(srchcode[" + s + "]):" + ex2.toString());
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
        final UM_RAM_UsrInfo[] array = new UM_RAM_UsrInfo[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int Usrcount(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        int int1 = 0;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append(" SELECT\tCOUNT(*) FROM  사용_사용자\t\t\t\n");
            sb.append(" WHERE\t마스터코드=?                 \n");
            sb.append(" AND\t    승인여부='2'                          \n");
            prepareStatement = connection.prepareStatement(sb.toString());
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.Usrcount(" + s + "):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.Usrcount(" + s + "):" + ex2.toString());
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
    
    public int SynCount(final String s, final String s2, final String s3, final String s4, final String s5) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final StringBuffer sb = new StringBuffer();
        int int1 = 0;
        final String convertString = this.convertString(s4, ",", "','");
        final Vector vector = new Vector<String>(1, 1);
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            sb.append("\tSELECT\tcount(*) \n");
            sb.append(" FROM\t사용_행정표준정보수신\n");
            sb.append(" where\t차상위기관코드 =?\n");
            sb.append(" AND\t\t최상위기관코드 =?\n");
            sb.append(" AND\t\t상태 ='0' \n");
            sb.append(" AND\t\t행정표준기관코드 !=?\n");
            vector.addElement(s);
            vector.addElement(s2);
            vector.addElement(s3);
            if (s5.equals("02")) {
                sb.append(" OR\t    (이전기관코드 like '%'||?||'%') \n");
                vector.addElement(s3);
            }
            else if (s5.equals("05")) {
                if (!convertString.equals("")) {
                    sb.append(" OR\t  행정표준기관코드 in(?) \n");
                    vector.addElement(convertString);
                }
            }
            else if ((s5.equals("06") || s5.equals("07")) && !convertString.equals("")) {
                sb.append(" OR\t    (행정표준기관코드 in(?) \n");
                vector.addElement(convertString);
            }
            prepareStatement = connection.prepareStatement(sb.toString());
            new QueryUtil().setPreparedValues(this, prepareStatement, new String[vector.size()]);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.SynCount(firstCode[" + s + "], mostCode[" + s2 + "], srchCode[" + s3 + "]):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.SynCount(firstCode[" + s + "], mostCode[" + s2 + "], srchCode[" + s3 + "]):" + ex2.toString());
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
    
    public UM_RAM_RcvCode[] SynList(final String s, final String s2, final String s3, final String s4, final String s5) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAM_RcvCode>();
        int gov_verify = 0;
        final String convertString = this.convertString(s4, ",", "','");
        final Vector vector2 = new Vector<String>(1, 1);
        try {
            sb.append("\tSELECT\t구분자,DML구분,행정표준기관코드,기관명,최상위기관코드,차상위기관코드, \n");
            sb.append("         to_char(수신일자,'YYYY/MM/DD') 수신일자,상태,이전기관코드 \n");
            sb.append(" FROM\t사용_행정표준정보수신\n");
            sb.append(" where\t차상위기관코드 =? \n");
            sb.append(" AND\t\t최상위기관코드 =? \n");
            sb.append(" AND\t\t상태 ='0' \n");
            sb.append(" AND\t\t행정표준기관코드 !=? \n");
            vector2.addElement(s);
            vector2.addElement(s2);
            vector2.addElement(s3);
            if (s5.equals("02")) {
                sb.append(" OR\t    (이전기관코드 like '%'||?||'%') \n");
                vector2.addElement(s3);
            }
            else if (s5.equals("04")) {
                if (!convertString.equals("")) {
                    sb.append(" OR\t  행정표준기관코드 in(?) \n");
                    vector2.addElement(convertString);
                }
            }
            else if (s5.equals("05")) {
                if (!convertString.equals("")) {
                    sb.append(" OR\t  행정표준기관코드 in(?) \n");
                    vector2.addElement(convertString);
                }
            }
            else if ((s5.equals("06") || s5.equals("07")) && !convertString.equals("")) {
                sb.append(" OR\t    (행정표준기관코드 in (?) \n");
                vector2.addElement(convertString);
            }
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            new QueryUtil().setPreparedValues(this, prepareStatement, new String[vector2.size()]);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                final UM_RAM_RcvCode um_RAM_RcvCode = new UM_RAM_RcvCode();
                um_RAM_RcvCode.setgubun(executeQuery.getString(1));
                um_RAM_RcvCode.setdmlFlag(executeQuery.getString(2));
                final String string2 = executeQuery.getString(3);
                um_RAM_RcvCode.setgccCode(string2);
                final int gov_verify2 = this.gov_verify(string2, connection);
                um_RAM_RcvCode.setgoNameFull(executeQuery.getString(4));
                um_RAM_RcvCode.setmostUpper(executeQuery.getString(5));
                um_RAM_RcvCode.setfirstUpper(executeQuery.getString(6));
                um_RAM_RcvCode.setrcvDate(executeQuery.getString(7));
                um_RAM_RcvCode.setstatusRcv(executeQuery.getString(8));
                final String string3 = executeQuery.getString(9);
                um_RAM_RcvCode.setbeforeCode(string3);
                final Vector string4 = this.parseString(string3, ",");
                if (string4 == null) {
                    gov_verify = this.gov_verify(string3, connection);
                }
                else {
                    for (int i = 0; i < string4.size(); ++i) {
                        gov_verify += this.gov_verify(string4.get(i), connection);
                    }
                }
                final int gov_verify3 = this.gov_verify("", connection);
                if (gov_verify2 > 0 || gov_verify > 0 || gov_verify3 > 0) {
                    um_RAM_RcvCode.setgovFlag("Y");
                }
                else {
                    um_RAM_RcvCode.setgovFlag("N");
                }
                vector.addElement(um_RAM_RcvCode);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_RcvCodeList.synList(): firstCode[" + s + "], mostCode[" + s2 + "], srchCode[" + s3 + "] " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_RcvCodeList.synList(): firstCode[" + s + "], mostCode[" + s2 + "], srchCode[" + s3 + "] " + ex2.toString());
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
        final UM_RAM_RcvCode[] array = new UM_RAM_RcvCode[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public String selectStatusBox(final String s, final String s2) {
        return "<select style='FONT-SIZE: 9pt' size='1' name='" + s2 + "'>" + "\r\n" + "   <option value='' " + (s.equals("") ? "selected" : "") + ">전   체</option>" + "\r\n" + "   <option value='0' " + (s.equals("0") ? "selected" : "") + ">수   신</option>" + "\r\n" + "   <option value='2' " + (s.equals("2") ? "selected" : "") + ">대   기</option>" + "\r\n" + "   <option value='3' " + (s.equals("3") ? "selected" : "") + ">송   신</option>" + "\r\n" + "   <option value='9' " + (s.equals("9") ? "selected" : "") + ">완   료</option>" + "\r\n" + "</select>";
    }
    
    public String selectGubunBox(final String s, final String s2) {
        return "<select style='FONT-SIZE: 9pt' size='1' name='" + s2 + "'>" + "\r\n" + "   <option value='' " + (s.equals("") ? "selected" : "") + ">전   체</option>" + "\r\n" + "   <option value='01' " + (s.equals("01") ? "selected" : "") + ">신   설</option>" + "\r\n" + "   <option value='02' " + (s.equals("02") ? "selected" : "") + ">폐   지</option>" + "\r\n" + "   <option value='03' " + (s.equals("03") ? "selected" : "") + ">개   칭</option>" + "\r\n" + "   <option value='04' " + (s.equals("04") ? "selected" : "") + ">치</option>" + "\r\n" + "   <option value='05' " + (s.equals("05") ? "selected" : "") + ">분</option>" + "\r\n" + "   <option value='06' " + (s.equals("06") ? "selected" : "") + ">합</option>" + "\r\n" + "   <option value='07' " + (s.equals("07") ? "selected" : "") + ">흡   수</option>" + "\r\n" + "</select>";
    }
    
    public String getRcvDate(final String s, final Connection connection) {
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = null;
        try {
            prepareStatement = connection.prepareStatement("selet to_date(수신일시, 'yyyy-mm-dd') from 사용_행정표준정보수신 \n where 행정표준기관코드 = ? and 상태 = '2' ");
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
            }
            prepareStatement.clearParameters();
            prepareStatement.close();
            return string;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.getRcvDate():  srchcode[" + s + "]" + ex.toString());
            return ex.toString();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.getRcvDate():  srchcode[" + s + "]" + ex2.toString());
            return ex2.toString();
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
        }
    }
    
    public int cntNextOrgCode(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        int int1 = 0;
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("select count(*) \n from 사용_공공기관마스터변경 a \n where a.이전코드 = ? \n and a.이력구분 = 'NEW' ");
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
            return int1;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.cntNextOrgCode(): orgCode[" + s + "]" + ex.toString());
            return -1;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.cntNextOrgCode(): orgCode[" + s + "]" + ex2.toString());
            return -1;
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
            try {
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex5) {}
        }
    }
    
    public String[] getPrevOrgCode(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        final String[] array = new String[2];
        String string = "";
        String string2 = "";
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("select a.이전코드, b.전체명 \n from 사용_행정표준정보변경 a, 사용_행정표준정보 b \n where a.이전코드 = b.행정표준기관코드 \n and a.행정표준기관코드 = ? ");
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                string = string + "," + executeQuery.getString(1);
                string2 = string2 + "," + executeQuery.getString(2);
            }
            final String substring = string.substring(1);
            final String substring2 = string2.substring(1);
            array[0] = substring;
            array[1] = substring2;
            return array;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.getPrevOrgCode():  orgCode[" + s + "]" + ex.toString());
            return null;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.getPrevOrgCode():  orgCode[" + s + "]" + ex2.toString());
            return null;
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
            try {
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex5) {}
        }
    }
    
    public String getPrevOrgCode2(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = "";
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("select a.이전코드 \n from 사용_행정표준정보변경 a \n where a.행정표준기관코드 = ? ");
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                string += executeQuery.getString(1);
            }
            return string;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.getPrevOrgCode2():  orgCode[" + s + "]" + ex.toString());
            return null;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.getPrevOrgCode2():  orgCode[" + s + "]" + ex2.toString());
            return null;
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
            try {
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex5) {}
        }
    }
    
    public String getDeleteGovCode(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String string = "";
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("select a.이전코드 \n from 사용_공공기관마스터변경 a, 사용_공공기관마스터 b \n where a.이전코드 = b.공공기관코드 \n and a.공공기관코드 = ? ");
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                string += executeQuery.getString(1);
            }
            return string;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.getDeleteGovCode():  orgCode[" + s + "]" + ex.toString());
            return null;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.getDeleteGovCode():  orgCode[" + s + "]" + ex2.toString());
            return null;
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
            try {
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex5) {}
        }
    }
    
    public String getNextOrgCode(final String s) {
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        String s2 = "";
        try {
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("select a.공공기관코드 \n from 사용_공공기관마스터변경 a \n where a.이전코드 = ? \n and a.이력구분 = 'NEW' ");
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                s2 = s2 + "," + executeQuery.getString(1);
            }
            if (s2.length() > 0) {
                s2 = s2.substring(1);
            }
            return s2;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.getNextOrgCode():  orgCode[" + s + "]" + ex.toString());
            return null;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_RcvCodeList.getNextOrgCode():  orgCode[" + s + "]" + ex2.toString());
            return null;
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
            try {
                if (connection != null) {
                    trx.close();
                }
            }
            catch (Exception ex5) {}
        }
    }
    
    private Vector parseString(String substring, final String s) {
        try {
            if (substring != null && substring.length() > 0 && substring.indexOf(s) >= 0) {
                final Vector<String> vector = new Vector<String>();
                do {
                    vector.addElement(substring.substring(0, substring.indexOf(s)));
                    substring = substring.substring(substring.indexOf(s) + 1);
                } while (substring.indexOf(s) >= 0);
                return vector;
            }
            return null;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private String convertString(final String s, final String s2, final String s3) {
        String string = s;
        int n = 0;
        final int n2 = s3.length() - s2.length();
        try {
            if (string == null || string.length() <= 0) {
                return string;
            }
            while (string.indexOf(s2, n) != -1) {
                final int index = string.indexOf(s2, n + n2);
                string = string.substring(0, index) + s3 + string.substring(index + 1);
                n = index + n2;
            }
            return string;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return s;
        }
    }
}
