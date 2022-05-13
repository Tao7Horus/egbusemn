// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;

public class UM_FACTORY
{
    public void updateFactoryInfo(final String s, final String[] array, final String[] array2, final String[] array3, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        try {
            if (connection == null) {
                throw new Exception("Connection is null");
            }
            prepareStatement = connection.prepareStatement(" update 사용_공장정보 set 공장전화번호=?, 공장FAX번호=?, 갱신일자=SYSDATE where 사업자등록번호=? AND 일련번호=?");
            for (int i = 0; i < array3.length; ++i) {
                if (array3[i].equals(s2)) {
                    prepareStatement.setString(1, array[i]);
                    prepareStatement.setString(2, array2[i]);
                    prepareStatement.setString(3, s);
                    prepareStatement.setString(4, array3[i]);
                    prepareStatement.executeUpdate();
                    prepareStatement.clearParameters();
                }
            }
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".updateFactoryInfo() saupNo[" + s + "],seq[" + s2 + "]:" + ex.toString());
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
