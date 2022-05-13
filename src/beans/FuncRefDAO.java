// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import common.ComDbQuery;
import common.CommDbQuery;
import common.CommEntity;
import common.Log;
import common.OneRowEntity;
import common.Trx;

public class FuncRefDAO
{
    
      
    public String getNextBizReg(final String s) {
        final String s2 = "";
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        if("eGP".equals(s)){
        try {
            trx = new Trx(this, "USEMN");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement("select FOREIGN_SERIAL_NO.nextval from DUAL");
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                return gen_next_brn(executeQuery.getString(1));
            }
            return gen_next_brn(s2);
            
        }
        catch (SQLException ex) {
            Log.debug(ex.toString());
        }
        catch (Exception ex2) {
            Log.debug(ex2.toString());
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
        return "-1";
        }
        return "-1";
    }
    
    public OneRowEntity getUser_NMByBRN(final String userId) throws Exception {
        Trx trx = null;
        Connection conn = null;
        final String[] parameter = { userId };
        final String sql = "SELECT  USER_ID, CHRGR_NM ,CHRGR_DEPART ,CHRGR_PHONE_NO ,CHRGR_MOBILE, CHRGR_FAX ,CHRGR_EMAIL FROM  USEMN.UM_USER  WHERE  MAST_CD = ? ";
        try {
            trx = new Trx(this);
            conn = trx.getConnection();
            Log.debug("FRDAO log: " +userId);
            return new ComDbQuery().getOneRowList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exf) {
            Log.errors(this, exf, "");
            throw exf;
        }
        finally {
            if (trx != null) {
                trx.close();
            }
        }
    }
    
    public CommEntity[] getRecPubInstituCertList(final String bizRegNo, final Connection conn) throws Exception {
        final StringBuffer sql = new StringBuffer();
        final Vector parameterV = new Vector();
        try {
        	   sql.append(" select INSTITU_FULL_NM, INSTITU_EN_NM, REPR_NM, REPR_IDENT_NO, ADDR, APPROVE_REQ_CD, USER_ID ");
               sql.append(" from UM_REC_PUB_INSTITU_CERT ");
           
            sql.append(" where  BIZ_REG_NO = ?");
            parameterV.add(bizRegNo);
            final String[] parameter = new String[parameterV.size()];
            parameterV.copyInto(parameter);
            return new CommDbQuery().getList(this, sql.toString(), parameter, conn);
        }
        catch (Exception e) {
            Log.errors(this, e, "");
            throw e;
        }
    }
 
    
    public String gen_next_brn(String b){
		int l = b.length() + 2;
		String c = "NN";
		if ( l < 12){
			for(int i=0;l+i<12;i++){
				c = c.concat("0");
			}
			c = c.concat(b);
			return c;
		}		
		return "";		
	}
}
