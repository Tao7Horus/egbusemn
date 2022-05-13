// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.util.UM_COB_GTQ904;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import common.Log;
import common.Trx;
import java.sql.Connection;

public class UM_ADB_GOMAPPING
{
    public String getDataSynSyuoMapping(final String s, Connection connection) throws Exception {
        Trx trx = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        boolean b = false;
        final String s2 = "select 수요기관코드 from SYN_매핑코드기관 where 공공기관코드 =? and 사용여부='Y'";
        String string = null;
        try {
            if (connection == null) {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                b = true;
            }
            prepareStatement = connection.prepareStatement(s2);
            prepareStatement.setString(1, s);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                string = executeQuery.getString(1);
                if (executeQuery.wasNull()) {
                    string = null;
                }
            }
        }
        finally {
            try {
                if (executeQuery != null) {
                    executeQuery.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (connection != null && b) {
                    trx.close();
                }
            }
            catch (Exception ex3) {}
        }
        if (string == null) {
            Log.debug("SYN_매핑코드기관 테이블에서 [" + s + "] 에 대한 수요기관코드를 구할 수 없음");
        }
        return string;
    }
    
    public String getLocaCode(final String s, final Connection connection) throws Exception {
        final String sqlDataOne = new UM_COB_GTQ904().getSqlDataOne(this, " select 기존코드 from (select distinct 기존코드,substr(G2B코드,1,2) G2B코드 from syn_매핑코드지역) A  where A.G2B코드=substr('" + s + "',1,2)", connection);
        if (sqlDataOne == null) {
            throw new Exception(s + "에 대한 syn_매핑코드지역 테이블에서 기존코드가 존재하지 않음");
        }
        return sqlDataOne;
    }
    
    public void insertSynGigwanMapping(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        final String s3 = "insert into SYN_매핑코드기관 values(?,  ?, 'Y')";
        try {
            prepareStatement = connection.prepareStatement(s3);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("SYN_매핑코드기관 insert 자료 너무 많음[자료확인필요]");
            }
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex) {}
        }
    }
}
