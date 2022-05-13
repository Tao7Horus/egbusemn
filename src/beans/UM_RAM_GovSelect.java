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
import entity.UM_RAM_GovCode;

public class UM_RAM_GovSelect
{
    public UM_RAM_GovCode select_organ(final String s) throws Exception, SQLException {
        Trx trx = null;
        Connection connection = null;
        UM_RAM_GovCode um_RAM_GovCode = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        try {
            sb.append(" SELECT   행정표준기관코드,기관장직급명,기관장직위,차수,서열,\n");
            sb.append(" \t\t 차상위기관코드,최상위기관코드,기관유형_대,기관유형_중,기관유형_소,to_char(생성일자,'YYYY-MM-DD'),\n");
            sb.append(" \t\t to_char(폐지일자,'YYYY-MM-DD'),decode(폐지구분,'0','운영','1','폐지'),to_char(변경일자,'YYYY-MM-DD'),\n");
            sb.append(" \t\t 변경시간,decode(변경유형구분,'01','신설','02','폐지','03','개칭','04','치','05','분','06','합','07','흡수','없음'),\n");
            sb.append(" \t\t 전체명,우편번호,소재지,지번,전화번호,FAX번호,최하위명,대표코드 \n");
            sb.append(" FROM    사용_행정표준정보\t\t\t\t\t\t\n");
            sb.append(" WHERE   행정표준기관코드='" + s + "'\t\t\t\n");
            final String string = sb.toString();
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            prepareStatement.clearParameters();
            while (executeQuery.next()) {
                um_RAM_GovCode = new UM_RAM_GovCode();
                um_RAM_GovCode.setg2bCode(executeQuery.getString(1));
                um_RAM_GovCode.setposition(executeQuery.getString(2));
                um_RAM_GovCode.setpositionRank(executeQuery.getString(3));
                um_RAM_GovCode.setdisparity(executeQuery.getString(4));
                um_RAM_GovCode.setgrade(executeQuery.getString(5));
                um_RAM_GovCode.setultraCode(executeQuery.getString(6));
                um_RAM_GovCode.setsubCode(executeQuery.getString(7));
                um_RAM_GovCode.setorganL(executeQuery.getString(8));
                um_RAM_GovCode.setorganM(executeQuery.getString(9));
                um_RAM_GovCode.setorganS(executeQuery.getString(10));
                um_RAM_GovCode.setcreateDate(executeQuery.getString(11));
                um_RAM_GovCode.setabDate(executeQuery.getString(12));
                um_RAM_GovCode.setabDivision(executeQuery.getString(13));
                um_RAM_GovCode.setmodiDate(executeQuery.getString(14));
                um_RAM_GovCode.setmoTime(executeQuery.getString(15));
                um_RAM_GovCode.setmoDivision(executeQuery.getString(16));
                um_RAM_GovCode.setgoNameFull(executeQuery.getString(17));
                um_RAM_GovCode.setZIPCODE(executeQuery.getString(18));
                um_RAM_GovCode.setADDR(executeQuery.getString(19));
                um_RAM_GovCode.setaddress2(executeQuery.getString(20));
                um_RAM_GovCode.settelNum(executeQuery.getString(21));
                um_RAM_GovCode.setfaxNum(executeQuery.getString(22));
                um_RAM_GovCode.setgoLowName(executeQuery.getString(23));
                um_RAM_GovCode.setCodeMaster(executeQuery.getString(24));
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_GovSelect.select_organ('" + s + "'):" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_GovSelect.select_organ('" + s + "'):" + ex2.toString());
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
        return um_RAM_GovCode;
    }
    
    public int SynOrgcount(final String s, final String s2, final String s3, final String s4, final String s5) {
        Trx trx = null;
        Connection connection = null;
        final StringBuffer sb = new StringBuffer();
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        String s6 = "";
        try {
            if (s4.equals("01")) {
                s6 = "차상위기관코드";
            }
            else if (s4.equals("02")) {
                s6 = "차수";
            }
            else if (s4.equals("03")) {
                s6 = "서열";
            }
            sb.append(" SELECT  count(*) \n");
            sb.append(" FROM    사용_행정표준정보\t\t\t\t\t\t\n");
            sb.append(" WHERE   행정표준기관코드 is not null\t\t\t\n");
            if (!s.equals("")) {
                sb.append(" and 행정표준기관코드='" + s + "' \n");
            }
            if (!s2.equals("")) {
                sb.append(" and 최상위기관코드='" + s2 + "' \n");
            }
            if (!s3.equals("")) {
                sb.append(" and 전체명 like '%" + s3 + "%' \n");
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
            Log.debug("UM_RAM_MasterSelect.SynOrgcount:" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_MasterSelect.SynOrgcount:" + ex2.toString());
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
    
    public UM_RAM_GovCode[] SynOrgList(final String s, final String s2, final String s3, final String s4, final String s5) throws Exception, SQLException {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        final Vector vector = new Vector<UM_RAM_GovCode>();
        String s6 = "";
        try {
            if (s4.equals("01")) {
                s6 = "차상위기관코드";
            }
            else if (s4.equals("02")) {
                s6 = "차수";
            }
            else if (s4.equals("03")) {
                s6 = "서열";
            }
            sb.append(" SELECT   행정표준기관코드,기관장직급명,기관장직위,차수,서열,\n");
            sb.append(" \t\t 차상위기관코드,최상위기관코드,기관유형_대,기관유형_중,기관유형_소,to_char(생성일자,'YYYY-MM-DD'),\n");
            sb.append(" \t\t to_char(폐지일자,'YYYY-MM-DD'),decode(폐지구분,'0','운영','1','폐지'),to_char(변경일자,'YYYY-MM-DD'),\n");
            sb.append(" \t\t 변경시간,decode(변경유형구분,'01','신설','02','폐지','03','개칭','04','치','05','분','06','합','07','흡수','없음'),\n");
            sb.append(" \t\t 전체명,우편번호,소재지,지번,전화번호,FAX번호,최하위명,대표코드 \n");
            sb.append(" FROM    사용_행정표준정보\t\t\t\t\t\t\n");
            sb.append(" WHERE   행정표준기관코드 is not null\t\t\t\n");
            if (!s.equals("")) {
                sb.append(" and 행정표준기관코드='" + s + "' \n");
            }
            if (!s2.equals("")) {
                sb.append(" and 최상위기관코드='" + s2 + "' \n");
            }
            if (!s3.equals("")) {
                sb.append(" and 전체명 like '%" + s3 + "%' \n");
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
                final UM_RAM_GovCode um_RAM_GovCode = new UM_RAM_GovCode();
                um_RAM_GovCode.setg2bCode(executeQuery.getString(1));
                um_RAM_GovCode.setposition(executeQuery.getString(2));
                um_RAM_GovCode.setpositionRank(executeQuery.getString(3));
                um_RAM_GovCode.setdisparity(executeQuery.getString(4));
                um_RAM_GovCode.setgrade(executeQuery.getString(5));
                um_RAM_GovCode.setultraCode(executeQuery.getString(6));
                um_RAM_GovCode.setsubCode(executeQuery.getString(7));
                um_RAM_GovCode.setorganL(executeQuery.getString(8));
                um_RAM_GovCode.setorganM(executeQuery.getString(9));
                um_RAM_GovCode.setorganS(executeQuery.getString(10));
                um_RAM_GovCode.setcreateDate(executeQuery.getString(11));
                um_RAM_GovCode.setabDate(executeQuery.getString(12));
                um_RAM_GovCode.setabDivision(executeQuery.getString(13));
                um_RAM_GovCode.setmodiDate(executeQuery.getString(14));
                um_RAM_GovCode.setmoTime(executeQuery.getString(15));
                um_RAM_GovCode.setmoDivision(executeQuery.getString(16));
                um_RAM_GovCode.setgoNameFull(executeQuery.getString(17));
                um_RAM_GovCode.setZIPCODE(executeQuery.getString(18));
                um_RAM_GovCode.setADDR(executeQuery.getString(19));
                um_RAM_GovCode.setaddress2(executeQuery.getString(20));
                um_RAM_GovCode.settelNum(executeQuery.getString(21));
                um_RAM_GovCode.setfaxNum(executeQuery.getString(22));
                um_RAM_GovCode.setgoLowName(executeQuery.getString(23));
                um_RAM_GovCode.setCodeMaster(executeQuery.getString(24));
                vector.addElement(um_RAM_GovCode);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_RAM_GovSelect.SynOrgList('" + s + "'):" + ex.toString());
            throw new Exception(ex.getMessage());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_GovSelect.SynOrgList('" + s + "'):" + ex2.toString());
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
        final UM_RAM_GovCode[] array = new UM_RAM_GovCode[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    public int isCodeExist(final String s) {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        int int1 = 0;
        try {
            final String s2 = "\n SELECT count(*) FROM 사용_행정표준정보 \n WHERE 행정표준기관코드 = ? ";
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
            Log.debug("UM_RAM_GovSelect.isCodeExist('" + s + "'):" + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_RAM_GovSelect.isCodeExist('" + s + "'):" + ex2.toString());
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
