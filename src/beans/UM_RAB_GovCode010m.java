// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import java.sql.Connection;

public class UM_RAB_GovCode010m
{
    public void updateOrgDiv(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final Connection connection) {
        PreparedStatement prepareStatement = null;
        final String s7 = "update 사용_공공기관마스터 set 소관구분 = ?, 기관유형구분 = ?, 기관유형_중 = ?, \n 기관유형_소 = ?, 코드체크여부 = 'Y', 최상위기관코드 = ?, 갱신일자 = sysdate \n where 공공기관코드 = ? \n";
        try {
            prepareStatement = connection.prepareStatement(s7);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, s3);
            prepareStatement.setString(4, s4);
            prepareStatement.setString(5, s5);
            prepareStatement.setString(6, s6);
            prepareStatement.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public void updateUperCode(final String s, final String s2, final String s3, final Connection connection) {
        PreparedStatement prepareStatement = null;
        final String s4 = "update 사용_행정표준정보 set 최상위기관코드 = ?, 차상위기관코드 = ? \n where 행정표준기관코드 = ? \n";
        try {
            prepareStatement = connection.prepareStatement(s4);
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.setString(3, s3);
            prepareStatement.executeUpdate();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            }
            catch (Exception ex2) {}
        }
    }
}
