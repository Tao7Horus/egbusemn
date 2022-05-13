// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.Log;
import java.sql.Connection;

public class UM_RAM_InsertExms
{
    public String exmsInsert(final Connection connection, String substring, final String s, final String s2) {
        PreparedStatement preparedStatement = null;
        PreparedStatement prepareStatement = null;
        PreparedStatement prepareStatement2 = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        ResultSet executeQuery = null;
        ResultSet executeQuery2 = null;
        final String s3 = "공공기관정보 변경요청건";
        final String string2 = "안녕하십니까? 조달청 운영자입니다. \r\n사용자님이 소속된 기관정보가 아래와 같이 변경되었습니다. \r\n감사합니다. \r\n\r\n" + s;
        String s4 = "select 사용자ID from syn_사용_사용자 where 마스터코드='" + substring + "' and 승인여부='2' and 대표수신자여부 = 'Y'";
        String s5 = "select 사용자ID from 사용_사용자 where 마스터코드='" + substring + "' and 승인여부='2' and 대표수신자여부 = 'Y'";
        try {
            if (substring.indexOf("_") > 0) {
                final String substring2 = substring.substring(substring.indexOf("_") + 1);
                substring = substring.substring(0, substring.indexOf("_"));
                s4 = "select 사용자ID from syn_사용_사용자 where 마스터코드='" + substring2 + "' and 승인여부='2' and 대표수신자여부 = 'Y'";
                s5 = "select 사용자ID from 사용_사용자 where 마스터코드='" + substring + "' and 승인여부='2' and 대표수신자여부 = 'Y'";
            }
            preparedStatement = connection.prepareStatement("\n INSERT INTO syn_유통_공통문서송신마스터 values \n (to_char(sysdate,'yyyymmdd')||trim(to_char(syn_seq_comdoc.nextval,'0999999')), \n ? , ? , '1',  sysdate, ? , ? , '' , '' , '' , '' , '' , '0' ) ");
            preparedStatement.setString(1, s3);
            preparedStatement.setString(2, string2);
            preparedStatement.setString(3, s2);
            preparedStatement.setString(4, s4);
            final int executeUpdate = preparedStatement.executeUpdate();
            preparedStatement.clearParameters();
            preparedStatement.close();
            if (executeUpdate > 0) {
                prepareStatement = connection.prepareStatement(" select 송신문서ID from syn_유통_공통문서송신마스터 order by 등록일시 desc ");
                executeQuery2 = prepareStatement.executeQuery();
                if (executeQuery2.next()) {
                    string = executeQuery2.getString(1);
                }
                prepareStatement2 = connection.prepareStatement(s5);
                executeQuery = prepareStatement2.executeQuery();
                while (executeQuery.next()) {
                    final String string3 = executeQuery.getString(1);
                    preparedStatement = connection.prepareStatement(" insert into syn_유통_공통문서송신 values (  ?, sysdate, '1','', '" + string.trim() + "' )");
                    preparedStatement.setString(1, string3.trim());
                    preparedStatement.executeUpdate();
                    preparedStatement.clearParameters();
                    preparedStatement.close();
                }
                return "true";
            }
            return "false";
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            Log.debug("UM_RAM_InsertExms.exmsInsert():" + ex.toString());
            return ex.toString();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            Log.debug("UM_RAM_InsertExms.exmsInsert():" + ex2.toString());
            return ex2.toString();
        }
        finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement2 != null) {
                try {
                    prepareStatement2.close();
                }
                catch (Exception ex4) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex5) {}
            }
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex6) {}
            }
            if (executeQuery2 != null) {
                try {
                    executeQuery2.close();
                }
                catch (Exception ex7) {}
            }
        }
    }
    
    public static String getReplace(final String s) {
        try {
            final char[] charArray = s.trim().toCharArray();
            if (charArray.length > 0) {
                final StringBuffer sb = new StringBuffer(s.trim());
                int n = 0;
                for (int i = 0; i < charArray.length; ++i) {
                    switch (charArray[i]) {
                        case '\n': {
                            sb.insert(i + n, Integer.toString(13));
                            n += 4;
                            break;
                        }
                    }
                }
                return sb.toString();
            }
        }
        catch (Exception ex) {}
        return "";
    }
}
