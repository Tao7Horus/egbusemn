// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.Statement;
import common.ComDbQuery;
import java.sql.PreparedStatement;
import common.Log;
import java.sql.Connection;

public class UM_ITEM
{
    public void update_JP_item_default(final String s, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        try {
            if (connection == null) {
                throw new Exception("Connection is null");
            }
            prepareStatement = connection.prepareStatement(" update 사용_조달품목 set 대표물품여부='N' where 사업자등록번호=? and 제조여부='Y' and 비축품목여부 is null and 대표물품여부='Y'");
            prepareStatement.setString(1, s);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".update_JP_item_default() saupNo[" + s + "]:" + ex.toString());
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
    
    public void update_GP_item_default(final String s, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        try {
            if (connection == null) {
                throw new Exception("Connection is null");
            }
            prepareStatement = connection.prepareStatement(" update 사용_조달품목 set 대표물품여부='N' where 사업자등록번호=? and 제조여부='N' and 비축품목여부 is null and 대표물품여부='Y'");
            prepareStatement.setString(1, s);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".update_GP_item_default() saupNo[" + s + "]:" + ex.toString());
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
    
    public void update_JP_item_new(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        try {
            if (connection == null) {
                throw new Exception("Connection is null");
            }
            prepareStatement = connection.prepareStatement(" update 사용_조달품목 set 대표물품여부='Y' where 사업자등록번호=? and 물품분류번호=?");
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".update_JP_item_new() saupNo[" + s + "],code[" + s2 + "]:" + ex.toString());
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
    
    public void delete_JP_item(final String s, final String[] array, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        try {
            if (connection == null) {
                throw new Exception("Connection is null");
            }
            prepareStatement = connection.prepareStatement(" delete 사용_조달품목 where 사업자등록번호=? and 물품분류번호=?");
            for (int i = 0; i < array.length; ++i) {
                prepareStatement.setString(1, s);
                prepareStatement.setString(2, array[i]);
                prepareStatement.executeUpdate();
                prepareStatement.clearParameters();
            }
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".delete_JP_item() saupNo[" + s + "]:" + ex.toString());
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
    
    public boolean isRegedItem(final String s, final String s2, final Connection conn) throws Exception {
        final Statement statement = null;
        try {
            return new ComDbQuery().getCount(this, "usemn", " select count(*) from 사용_조달품목 where 사업자등록번호=? and 물품분류번호=?", new String[] { s, s2 }, conn) > 0;
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".isRegedItem() saupNo[" + s + "]:" + ex.toString());
            throw ex;
        }
        finally {
            if (statement != null) {
                try {
                    statement.close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public void insertItem(final String s, final String s2, final Connection connection) throws Exception {
        PreparedStatement prepareStatement = null;
        try {
            if (connection == null) {
                throw new Exception("Connection is null");
            }
            prepareStatement = connection.prepareStatement(" insert into 사용_조달품목(사업자등록번호, 물품분류번호, 제조여부, 대표물품여부, 등록일자, 갱신일자) values(?,?,'N','N',sysdate, sysdate)");
            prepareStatement.setString(1, s);
            prepareStatement.setString(2, s2);
            prepareStatement.executeUpdate();
            prepareStatement.clearParameters();
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".insertItem() saupNo[" + s + "],code[" + s2 + "]:" + ex.toString());
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
