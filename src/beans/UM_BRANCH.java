// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;

public class UM_BRANCH
{
    public void updateBRInfo(final String s, final String s2, final String s3, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        try {
            if (connection == null) {
                throw new Exception("Connection is null");
            }
            prepareStatement = connection.prepareStatement(" update 사용_조달업체마스터 set 전화번호=?, FAX번호=?, 갱신일자=sysdate  where 사업자등록번호=? ");
            prepareStatement.setString(1, s2);
            prepareStatement.setString(2, s3);
            prepareStatement.setString(3, s);
            if (prepareStatement.executeUpdate() != 1) {
                throw new Exception("자사항목수정(사용_조달업체마스터) 중에 오류가 발생했습니다.saupNo[" + s + "]");
            }
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".updateBRInfo() saupNo[" + s + "]:" + ex.toString());
            throw ex;
        }
        finally {
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
}
