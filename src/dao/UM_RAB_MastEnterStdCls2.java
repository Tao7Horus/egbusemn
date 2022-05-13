/**
 * Copy xu ly cua UM_RAB_MastEnterStdCls thay cho viec sua file cu 
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import common.Log;
import entity.StdClsItem;

public class UM_RAB_MastEnterStdCls2
{
    /**
     *     
     * @param bizRezNo
     * @param stdClsList
     * @param conn
     * @throws Exception
     */
    public void save(final String bizRezNo, final List stdClsList, final Connection conn) throws Exception {
        if (stdClsList.size() <= 0) {
            return;
        }
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
            sql.append("insert all ");
            StdClsItem item = null;

            for (int i = 0; i < stdClsList.size(); ++i) {
            	
                item = (StdClsItem)stdClsList.get(i);
                sql.append(" into UM_ENTER_STD_CLS (BIZ_REG_NO, SERIAL_NO, STD_CLS_CD, STD_CLS_NAME)");
                sql.append(" values('" + bizRezNo + "', " + "(select nvl(max(SERIAL_NO), 0) from UM_ENTER_STD_CLS) + 1, '" + item.getStdClsCode() + "', '" + item.getStdClsName() + "')");
                
                //neu 200 dong thi thuc hien sql va reset lai trang thai
                //va khong phai la item cuoi cung
            	if ((i % 199 == 0) && (i < stdClsList.size() - 1)) {
            		sql.append(" SELECT * FROM dual");
                    pstmt = conn.prepareStatement(sql.toString());
                    pstmt.executeUpdate();
                    try {
                        pstmt.close();
                    }
                    catch (Exception ex) {}
                    
                    sql = new StringBuffer();
                    sql.append("insert all ");
            	}
            }
            sql.append(" SELECT * FROM dual");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {}
            }
        }        
    }
    
    public void saveFromRec(final String bizRezNo, final Connection conn) throws Exception {
        
        StringBuffer sql = new StringBuffer();
        PreparedStatement pstmt = null;
        sql.append("INSERT INTO UM_ENTER_STD_CLS ");
        sql.append("    (BIZ_REG_NO, SERIAL_NO, STD_CLS_CD, STD_CLS_NAME) ");
        sql.append("SELECT ");
        sql.append("    BIZ_REG_NO, (SELECT NVL(MAX(SERIAL_NO), 0) FROM UM_ENTER_STD_CLS) + 1, STD_CLS_CD, STD_CLS_NAME ");
        sql.append("FROM UM_REC_ENTER_STD_CLS WHERE BIZ_REG_NO = ?");
        
        try {
            if (conn == null) {
                throw new Exception("[Connection is null]");
            }
	        pstmt = conn.prepareStatement(sql.toString());
	        pstmt.setString(1, bizRezNo);
	        pstmt.executeUpdate();  	
        }
        catch (Exception e) {
        	Log.debug(String.valueOf(this.getClass().getName()) + ".saveFromRec(" + bizRezNo + ") : " + e.toString());
            throw e;
        }
        finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                }
                catch (Exception ex) {
                	Log.debug(String.valueOf(this.getClass().getName()) + ".saveFromRec(" + bizRezNo + ") : " + ex.toString());
                    throw ex;
                }
            }
        }        
    }
}
