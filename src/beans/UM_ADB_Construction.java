// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.ComDbQuery;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import common.CommEntity;
import java.util.Hashtable;
import java.sql.Connection;

public class UM_ADB_Construction
{
    public Hashtable getTotalData(final String s, Connection connection) throws Exception {
        Trx trx = null;
        final Hashtable<String, CommEntity[]> hashtable = new Hashtable<String, CommEntity[]>();
        boolean b = false;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            hashtable.put("ConstCompEntity", this.getConstCompInfo(s, connection));
            final String epnum = this.getEPNUM(s, connection);
            if (!epnum.equals("")) {
                hashtable.put("ConstChangeEntity", this.getConstCompChangeInfo(epnum, connection));
                hashtable.put("ConstUpjongEntity", this.getConstUpjongInfo(epnum, connection));
                final String seqno = this.getSEQNO(epnum, connection);
                if (!seqno.equals("")) {
                    hashtable.put("AdministrativeEntity", this.getAdministrativeDeal(epnum, seqno, connection));
                    hashtable.put("CycleStatementEntity", this.getCycleStatement(epnum, seqno, connection));
                    final String gethangjungno = this.gethangjungno(epnum, seqno, connection);
                    if (!gethangjungno.equals("") || gethangjungno != null) {
                        hashtable.put("AdministrativeaddEntity", this.getAdministrativeDealadd_1(epnum, seqno, gethangjungno, connection));
                    }
                }
            }
            return hashtable;
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".getTotalData() lawNo[" + s + "] :" + ex.toString());
            throw ex;
        }
        finally {
            try {
                if (connection != null && b) {
                    trx.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public String getEPNUM(final String s, final Connection connection) throws Exception {
        Statement statement = null;
        ResultSet executeQuery = null;
        String s2 = "";
        final String string = "   SELECT  EPNUM\t\t\t\t\t\t\t\t\t\t\t      FROM 사용_건설업체정보\t\t\t\t\t\t\t\t\t     WHERE 법인등록번호 = '" + s + "'\t\t\t\t\t" + "      ORDER BY EPNUM DESC, 수신일자 DESC\t\t\t";
        try {
            statement = connection.createStatement();
            executeQuery = statement.executeQuery(string);
            while (executeQuery.next()) {
                s2 = s2 + "'" + executeQuery.getString("EPNUM") + "',  ";
            }
            if (!s2.equals("")) {
                s2 = s2.substring(0, s2.length() - 3);
            }
        }
        catch (SQLException ex) {
            Log.debug(this.getClass().getName() + ".getEPNUM() block SQLException : " + ex.toString());
        }
        catch (NullPointerException ex2) {
            Log.debug(this.getClass().getName() + ".getEPNUM() block NullPointerException : " + ex2.toString());
        }
        catch (Exception ex3) {
            Log.debug(this.getClass().getName() + ".getEPNUM() block Exception : " + ex3.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex4) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (Exception ex5) {}
            }
        }
        return s2;
    }
    
    public CommEntity[] getConstCompInfo(final String s, final String s2, final Connection conn) throws Exception {
        final String sql = "   SELECT  법인등록번호, 건설업체명, 대표자, SUBSTR(우편번호, 1, 3)||'-'||SUBSTR(우편번호, 4, 3) 우편번호, 주소1 ||''|| 주소2 전체주소,\t\t\t국적코드, 국적명, 전화번호, FAX번호, EMAIL, \t\t\t일반전문구분, 일반전문구분명, 업체생사여부, 업체생사여부명, 시도코드, \t\t\t시도명,시군구코드,시군구명    FROM 사용_건설업체정보 \t WHERE EPNUM = ?  \t AND 수신일자 = ?     ORDER BY EPNUM DESC, 수신일자 DESC  ";
        try {
            return new ComDbQuery().getList(this, "usemn", sql, new String[] { s, s2 }, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " .getConstCompInfo() :epnum[" + s + "]:receive_date[" + s2 + "]:" + ex.toString());
            throw ex;
        }
    }
    
    public CommEntity[] getConstCompInfo(final String s, final Connection conn) throws Exception {
        final String sql = "   SELECT  EPNUM, 건설업체명, 대표자, SUBSTR(우편번호, 1, 3)||'-'||SUBSTR(우편번호, 4, 3) 우편번호, 주소1 ||''|| 주소2 전체주소,\t\t\t국적코드, 국적명, 전화번호, FAX번호, EMAIL, \t\t\t일반전문구분, 일반전문구분명, 업체생사여부, 업체생사여부명, 시도코드, \t\t\t시도명,시군구코드,시군구명    FROM 사용_건설업체정보 \t WHERE 법인등록번호 = ?     ORDER BY EPNUM DESC, 수신일자 DESC  ";
        try {
            return new ComDbQuery().getList(this, "usemn", sql, new String[] { s }, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " .getConstCompInfo() :lawNo[" + s + "]:" + ex.toString());
            throw ex;
        }
    }
    
    public CommEntity[] getConstCompChangeInfo(final String s, final Connection conn) throws Exception {
        final String string = "\t SELECT  일련번호, 변경구분, 변경구분명, to_char(변경일자, 'YYYY/MM/DD') 변경일자, 변경전사항, \t \t\t변경후사항, 전입기관시도코드, 전입기관시도명, 전입기관시군구코드, 전입기관시군구명,  \t \t\t전출기관시도코드, 전출기관시도명, 전출기관시군구코드, 전출기관시군구명, to_char(변경신고일자, 'YYYY/MM/DD') 변경신고일자 \t FROM  사용_건설업체변경사항\t\t WHERE EPNUM  in ( " + s + " ) " + "   ORDER BY EPNUM DESC, 일련번호 DESC  ";
        try {
            final String[] array = { s };
            return new ComDbQuery().getList(this, "usemn", string, null, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " .getConstCompChangeInfo() :epnum[" + s + "]:" + ex.toString());
            throw ex;
        }
    }
    
    public String getSEQNO(final String s, final Connection connection) throws Exception {
        Statement statement = null;
        ResultSet executeQuery = null;
        String s2 = "";
        final String string = "   SELECT  업종SEQNO\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM 사용_건설업종정보\t\t\t\t\t\t\t\t\t\t\t  WHERE EPNUM  in ( " + s + " )\t\t\t\t\t\t" + "\t\t  ORDER BY EPNUM DESC, 업종SEQNO DESC\t\t";
        try {
            statement = connection.createStatement();
            executeQuery = statement.executeQuery(string);
            while (executeQuery.next()) {
                s2 = s2 + "'" + executeQuery.getString("업종SEQNO") + "',  ";
            }
            s2 = s2.substring(0, s2.length() - 3);
        }
        catch (SQLException ex) {
            Log.debug(this.getClass().getName() + ".getSEQNO() block SQLException : " + ex.toString());
        }
        catch (NullPointerException ex2) {
            Log.debug(this.getClass().getName() + ".getSEQNO() block NullPointerException : " + ex2.toString());
        }
        catch (Exception ex3) {
            Log.debug(this.getClass().getName() + ".getSEQNO() block Exception : " + ex3.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex4) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (Exception ex5) {}
            }
        }
        return s2;
    }
    
    public String gethangjungno(final String s, final String s2, final Connection connection) throws Exception {
        Statement statement = null;
        ResultSet executeQuery = null;
        String s3 = "";
        final String string = "   SELECT  행정처분관리번호\t\t\t\t\t\t\t\t\t\t\t  FROM 사용_건설업종행정처분가처분\t\t\t\t\t\t\t\t  WHERE EPNUM  = " + s + " \t\t\t\t\t\t\t" + "\t\t  AND 업종SEQNO in ( " + s2 + " )\t\t\t\t\t\t" + "\t\t  ORDER BY EPNUM DESC, 업종SEQNO DESC\t\t";
        try {
            statement = connection.createStatement();
            executeQuery = statement.executeQuery(string);
            while (executeQuery.next()) {
                s3 = s3 + "'" + executeQuery.getString("행정처분관리번호") + "',  ";
            }
            s3 = s3.substring(0, s3.length() - 3);
        }
        catch (SQLException ex) {
            Log.debug(this.getClass().getName() + ".gethangjungno() block SQLException : " + ex.toString());
        }
        catch (NullPointerException ex2) {
            Log.debug(this.getClass().getName() + ".gethangjungno() block NullPointerException : " + ex2.toString());
        }
        catch (Exception ex3) {
            Log.debug(this.getClass().getName() + ".gethangjungno() block Exception : " + ex3.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex4) {}
            }
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (Exception ex5) {}
            }
        }
        return s3;
    }
    
    public CommEntity[] getConstUpjongInfo(final String s, final Connection conn) throws Exception {
        final String string = "  SELECT  업종SEQNO, b.코드명, 업종명, 업종등록번호, to_char(등록일자, 'YYYY/MM/DD') 등록일자,\t\t\t\t\t\t\t\t\t\t\t\t\t          업종상태코드, 업종상태명, to_char(폐업일자, 'YYYY/MM/DD') 폐업일자,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t                to_char(영업정지시작일, 'YYYY/MM/DD') 영업정지시작일, to_char(영업정지종료일, 'YYYY/MM/DD') 영업정지종료일\t\t   FROM  사용_건설업종정보 a, (SELECT * FROM syn_공동코드  WHERE 코드구분 = 'GUP') b\t\t\t\t\t\t\t\t\t\t\t\t\t\t   WHERE EPNUM  in ( " + s + " )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "   AND a.업종코드 = b.코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "   ORDER BY EPNUM DESC, 업종SEQNO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        try {
            final String[] array = new String[0];
            return new ComDbQuery().getList(this, "usemn", string, null, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " .getConstUpjongInfo() :epnum[" + s + "]:" + ex.toString());
            throw ex;
        }
    }
    
    public CommEntity[] getAdministrativeDeal(final String s, final String s2, final Connection conn) throws Exception {
        final String string = "\tSELECT   업종SEQNO, 행정처분관리번호, 처분코드, 처분코드명, 처분내용,\t\t\t\t\t\t  위반내용상세, to_char(처분일자, 'YYYY/MM/DD') 처분일자\t\t\t\t\tFROM  사용_건설업종행정처분\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE EPNUM  in ( " + s + " )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t\tAND 업종SEQNO in ( " + s2 + " )\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t\tORDER BY EPNUM, 업종SEQNO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        try {
            final String[] array = new String[0];
            return new ComDbQuery().getList(this, "usemn", string, null, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " .getAdministrativeDeal() :epnum[" + s + "]:seqno[" + s2 + "]:" + ex.toString());
            throw ex;
        }
    }
    
    public CommEntity[] getAdministrativeDealadd(final String s, final String s2, final String s3, final Connection conn) throws Exception {
        final String string = "\tSELECT 업종SEQNO,행정처분관리번호, 행정처분가처분관리번호, 처분코드,\t\t\t\t\t\t\t\t\t\t\t\t\t\t가처분상태, to_char(가처분처리일자, 'YYYY/MM/DD') 가처분처리일자,\t\t\t\t\t\t\t\t\t\t\t\tto_char(가처분시작일, 'YYYY/MM/DD') 가처분시작일, 내용, 가처분상태입력일자\t\t\t\t\t\tFROM 사용_건설업종행정처분가처분\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWHERE EPNUM  in ( " + s + " )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t\tAND 업종SEQNO in ( " + s2 + " )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t\tAND 행정처분관리번호 in ( " + s3 + " )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t\tORDER BY EPNUM, 업종SEQNO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        try {
            final String[] array = new String[0];
            return new ComDbQuery().getList(this, "usemn", string, null, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " .getAdministrativeDealadd() :epnum[" + s + "]:seqno[" + s2 + "]:" + ex.toString());
            throw ex;
        }
    }
    
    public CommEntity[] getAdministrativeDealadd_1(final String s, final String s2, final String s3, final Connection conn) throws Exception {
        final String string = "\tSELECT a.업종SEQNO, a.처분코드, a.처분코드명,b.처분코드 가처분코드, b.가처분상태,        \t\t\t\t\tto_char(b.가처분처리일자, 'YYYY/MM/DD') 가처분처리일자,\t\t\t\t\t\t\t\t\t\t\t\t\t\tto_char(b.가처분시작일, 'YYYY/MM/DD') 가처분시작일, b.내용,\t\t\t\t\t\t\t\t\t\t\t\t\tto_char(b.가처분상태입력일자, 'YYYY/MM/DD') 가처분상태입력일자\t\t\t\t\t\t\t\tFROM 사용_건설업종행정처분 a, 사용_건설업종행정처분가처분 b\t\t\t\t\t\t\t\t\t\t\t\tWHERE b.EPNUM  in ( " + s + " )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t\tAND b.업종SEQNO in ( " + s2 + " )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t\tAND a.EPNUM = b.EPNUM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t\tAND a.업종SEQNO = b.업종SEQNO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "\t\tAND a.행정처분관리번호 = b.행정처분관리번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        try {
            final String[] array = new String[0];
            return new ComDbQuery().getList(this, "usemn", string, null, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " .getAdministrativeDealadd() :epnum[" + s + "]:seqno[" + s2 + "]:" + ex.toString());
            throw ex;
        }
    }
    
    public CommEntity[] getCycleStatement(final String s, final String s2, final Connection conn) throws Exception {
        final String string = " SELECT 업종SEQNO, 일련번호, to_char(신고일자, 'YYYY/MM/DD') 신고일자,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tto_char(신고수리일자, 'YYYY/MM/DD') 신고수리일자, to_char(차기신고기간, 'YYYY/MM/DD') 차기신고기간\t\t FROM 사용_건설업종신고\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t WHERE EPNUM  in ( " + s + " )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + " AND 업종SEQNO in ( " + s2 + " )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + " ORDER BY EPNUM, 업종SEQNO, 일련번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        try {
            final String[] array = new String[0];
            return new ComDbQuery().getList(this, "usemn", string, null, conn);
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " .getCycleStatement() :epnum[" + s + "]:seqno[" + s2 + "]:" + ex.toString());
            throw ex;
        }
    }
}
