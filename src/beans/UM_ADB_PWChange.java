// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import common.Log;
import common.Trx;

public class UM_ADB_PWChange
{
    int currentMode;
    String ID;
    String JuminNo;
    public final int MODE_MAIN = 100;
    public final int MODE_MAIN1 = 200;
    
    public UM_ADB_PWChange() {
        this.ID = "";
        this.JuminNo = "";
    }
    
    public void setID(final String id) {
        this.ID = id;
    }
    
    public void setJuminNo(final String juminNo) {
        this.JuminNo = juminNo;
    }
    
    public void setCurrentMode(final int currentMode) {
        this.currentMode = currentMode;
    }
    
    public String[] getMainData() {
        this.setCurrentMode(100);
        String[] array = null;
        final String[][] list = this.getList();
        if (list != null && list.length != 0) {
            array = list[0];
        }
        return array;
    }
    
    public String[] getMain1Data() {
        this.setCurrentMode(200);
        String[] array = null;
        final String[][] list = this.getList();
        if (list != null && list.length != 0) {
            array = list[0];
        }
        return array;
    }
    
    public String[][] getList() {
        Trx trx = null;
        PreparedStatement queryCondition = null;
        ResultSet executeQuery = null;
        String[][] array = (String[][])null;
        try {
            trx = new Trx(this, "usemn");
            queryCondition = this.getPsmt(this.getQuery(), trx.getConnection());
            queryCondition = this.setQueryCondition(queryCondition);
            executeQuery = queryCondition.executeQuery();
            executeQuery.getMetaData();
            array = new String[this.getRowCount(executeQuery)][this.getColumnCount(executeQuery)];
            int n = 0;
            while (executeQuery.next()) {
                for (int i = 0; i < this.getColumnCount(executeQuery); ++i) {
                    array[n][i] = ((executeQuery.getString(i + 1) == null) ? "" : executeQuery.getString(i + 1));
                }
                ++n;
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_ADB_PWChange.select_user block SQLException : ");
            Log.debug("Exception발생 사유 : " + ex.toString() + ex.getErrorCode() + ex.getSQLState());
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            Log.debug("UM_ADB_PWChange.select_user block Exception : ");
            Log.debug("Exception발생 사유 : " + ex2.toString());
            ex2.printStackTrace();
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (queryCondition != null) {
                try {
                    queryCondition.close();
                }
                catch (Exception ex4) {}
            }
            if (trx != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return array;
    }
    
    public String getQuery() {
        String s = "";
        switch (this.currentMode) {
            case 100: {
                s = " SELECT 아이디, 성명, 주민등록번호, DECODE(이용자구분, 'A', '일반이용자', 'B', '비축정보이용자', 'C', '기관이용자'),         전화번호, 팩스번호, 휴대폰번호, 메일주소, 우편번호, 주소||' '||상세주소, 사업자등록번호, 부서명, 직책,         TO_CHAR(등록일자, 'YYYY-MM-DD'), TO_CHAR(변경일자, 'YYYY-MM-DD'), 삭제여부, 로그인실패횟수  FROM 사용_부가사용자  WHERE 아이디 = ?    AND 주민등록번호 = ? ";
                break;
            }
            case 200: {
                s = " SELECT ID, 성명, 주민등록번호, '목록업무이용자',         전화번호, 팩스번호, '', 전자메일, '', '', 사업자등록번호, 부서명, 직책,         TO_CHAR(등록일자, 'YYYY-MM-DD'), TO_CHAR(폐지일자, 'YYYY-MM-DD'), '', 로그인실패횟수  FROM 사용_생산업체사용자  WHERE ID = ?    AND 주민등록번호 = ? ";
                break;
            }
        }
        return s;
    }
    
    public PreparedStatement setQueryCondition(final PreparedStatement preparedStatement) throws SQLException {
        switch (this.currentMode) {
            case 100: {
                preparedStatement.setString(1, this.ID);
                preparedStatement.setString(2, this.JuminNo);
                break;
            }
            case 200: {
                preparedStatement.setString(1, this.ID);
                preparedStatement.setString(2, this.JuminNo);
                break;
            }
        }
        return preparedStatement;
    }
    
    public PreparedStatement getPsmt(final String s, final Connection connection) throws SQLException {
        return connection.prepareStatement(s, 1004, 1007);
    }
    
    public int getRowCount(final ResultSet set) throws SQLException {
        int row = 0;
        if (set == null) {
            return 0;
        }
        if (set.last()) {
            row = set.getRow();
        }
        set.beforeFirst();
        return row;
    }
    
    public int getColumnCount(final ResultSet set) throws SQLException {
        if (set == null) {
            return 0;
        }
        return set.getMetaData().getColumnCount();
    }
}
