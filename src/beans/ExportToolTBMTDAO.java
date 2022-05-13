// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import common.Log;
import common.Trx;
import entity.ExportTBMTEntity;
import entity.UM_URV_UserC020b;

public class ExportToolTBMTDAO {
	
	

	public ExportTBMTEntity[] getTBMTList( String fromDate, String toDate) {
		Trx trx = null;
		Connection connection = null;
		ResultSet executeQuery = null;
		PreparedStatement prepareStatement = null;
		final Vector vector = new Vector();
		try {
			
			StringBuffer tbmtSql = new StringBuffer();
			
			
			tbmtSql.append(" SELECT SOTBMT,Loai,BMT,LinhVuc, BID_NM,  PROJECT_NAME,  ESTI_PRICE,HinhThuc,PhuongThuc, CAPITAL, BID_SUB_LOCA ,DIAPHUONG, BID_SECU_METHOD,BID_SECU_PRICE,BID_ATTEND_COST,BID_CONTRACT_TIME,NCK,NBD , NKT, HH,NMT, DECODE(FUNC_TYPE,'9','Thay đổi','5','Gia Hạn') FUNCTYPE, NGANHNGHE, INSTITU_CD" );
			tbmtSql.append(" FROM (SELECT ROWNUM,BID_NO || '-'|| BID_TURN_NO SOTBMT,DECODE(BID_METHOD,'01','Điện tử','00','Trực tiếp') Loai, CASE BID_INSTITU_CD WHEN 'Z001561' THEN nvl2(order_institu_cd_name,order_institu_cd_name,ref_no)");
			tbmtSql.append(" ELSE nvl2(institu_full_nm,institu_full_nm,nvl2(order_institu_cd_name,order_institu_cd_name,ref_no)) END BMT, DECODE(BID_TYPE,1,'Hàng hóa',3,'Xây lắp',5,'Tư vấn',10,'Hỗn hợp',15,'Phi tư vấn') LinhVuc ,");
			tbmtSql.append(" BID_NM,  PROJECT_NAME, ESTI_PRICE, (SELECT CD_NM FROM BID.SYN_PUB_CODE WHERE CD_CLS = DECODE(BID_TYPE,1,'Z47',3,'S14',5,'Z10',10,'Z99',15,'Z48') AND CD = SUCC_BID_METHOD) HinhThuc, ");
			tbmtSql.append(" BID_PLAN_ITEM_METHOD PhuongThuc, CAPITAL, BID_SUB_LOCA , (SELECT CD_NM FROM BID.SYN_PUB_CODE WHERE CD_CLS = 'GU7' AND CD = BID_AREA ) DIAPHUONG, BID_SECU_METHOD,BID_SECU_PRICE,BID_ATTEND_COST,BID_CONTRACT_TIME || ' ' || CONTRACT_DAYS_STYLE BID_CONTRACT_TIME,");
			tbmtSql.append(" TO_CHAR(PUBLIC_DT,'dd/mm/yyyy hh24:mi') NCK, TO_CHAR(BID_START_DT,'dd/mm/yyyy hh24:mi') NBD , TO_CHAR(BID_END_DT,'dd/mm/yyyy hh24:mi') NKT, TO_CHAR(BID_OPEN_DT,'dd/mm/yyyy hh24:mi') NMT,  DECODE(INTER_BID_YN,'Y','Quốc tế','N','Trong nước') HH, (SELECT CD_NM FROM BID.SYN_PUB_CODE WHERE CD_CLS = 'Z88' AND CD = BID_TYPE_DETAIL ) NGANHNGHE, p.INSTITU_CD ");
			tbmtSql.append(" FROM BID.BID_BID_MASTER b, USEMN.UM_PUB_INSTITU_MAST p WHERE PUBLIC_DT BETWEEN TO_DATE('" + fromDate + "','DD/MM/YYYY') AND TO_DATE('" + toDate +"','DD/MM/YYYY')  +0.9999999");
			tbmtSql.append(" AND REG_YN LIKE 'Y' AND PUBLIC_YN LIKE 'Y' AND b.ORDER_INSTITU_CD = p.INSTITU_CD AND BID_TURN_NO = (SELECT MAX(BID_TURN_NO)  FROM BID.BID_BID_MASTER WHERE PUBLIC_YN='Y' AND BID_NO = b.BID_NO) ) x LEFT JOIN  BID.BID_NOTIFICATION h ON   x.SOTBMT = h.BID_NO || '-'|| h.BID_TURN_NO ORDER BY NCK");
		
			
			Log.debug("getTBMTList() >>>>TBMT SQL > " +  tbmtSql); 
			
			trx 				= new Trx(this);
			connection 			= trx.getConnection();
			prepareStatement 	= connection.prepareStatement(tbmtSql.toString());
			executeQuery 		= prepareStatement.executeQuery();
             while (executeQuery.next()) {
            	 ExportTBMTEntity tmp = new ExportTBMTEntity();
                 tmp.setS01(executeQuery.getString("SOTBMT"));                
                 tmp.setS02(executeQuery.getString("Loai"));
                 tmp.setS03(executeQuery.getString("BMT"));
                 tmp.setS04(executeQuery.getString("LinhVuc"));
                 tmp.setS05(executeQuery.getString("BID_NM")); 
                 tmp.setS06(executeQuery.getString("PROJECT_NAME")); 
                 tmp.setS07(executeQuery.getString("ESTI_PRICE")); 
                 tmp.setS08(executeQuery.getString("HinhThuc")); 
                 tmp.setS09(executeQuery.getString("PhuongThuc")); 
                 tmp.setS10(executeQuery.getString("CAPITAL")); 
                 tmp.setS11(executeQuery.getString("BID_SUB_LOCA")); 
                 tmp.setS12(executeQuery.getString("DIAPHUONG")); 
                 tmp.setS13(executeQuery.getString("BID_SECU_METHOD")); 
                 tmp.setS14(executeQuery.getString("BID_SECU_PRICE")); 
                 tmp.setS15(executeQuery.getString("BID_ATTEND_COST")); 
                 tmp.setS16(executeQuery.getString("BID_CONTRACT_TIME")); 
                 tmp.setS17(executeQuery.getString("NCK")); 
                 tmp.setS18(executeQuery.getString("NBD")); 
                 tmp.setS19(executeQuery.getString("NKT")); 
                 tmp.setS20(executeQuery.getString("NMT")); 
                 tmp.setS21(executeQuery.getString("HH")); 
                
                 String str =  executeQuery.getString("SOTBMT");
                 if("00".equals(str.substring(str.length() - 2))){
                	 tmp.setS22("Đăng lần đầu"); 
                 }else{
                	 tmp.setS22("Thay đổi"); 
                 }
                 
                 tmp.setS23(executeQuery.getString("NGANHNGHE"));
                 tmp.setS24(executeQuery.getString("FUNCTYPE")); 
                 tmp.setS25(executeQuery.getString("INSTITU_CD")); 
                vector.addElement(tmp);
             }
			
		} catch (SQLException ex) {
			Log.debug("getTBMTList() >>>> " +  ex.getMessage()); 
		} catch (Exception ex2) {
			Log.debug("getTBMTList() >>>> " +  ex2.getMessage()); 
		} finally {
			if (executeQuery != null) {
				try {
					executeQuery.close();
				} catch (Exception ex3) {
				}
			}
			if (prepareStatement != null) {
				try {
					prepareStatement.close();
				} catch (Exception ex4) {
				}
			}
			if (connection != null) {
				try {
					trx.close();
				} catch (Exception ex5) {
				}
			}
		}
		final ExportTBMTEntity[] array = new ExportTBMTEntity[vector.size()];
		vector.copyInto(array);
		return array;
	}


	public List getNameByCD(final String s) {
        final ArrayList list = new ArrayList();
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet executeQuery = null;
        try {
            trx = new Trx(this, "USEMN");
            connection = trx.getConnection();
          
            prepareStatement = connection.prepareStatement("select CD_NM from BID.SYN_PUB_CODE WHERE CD_CLS = '"+s+"' order by CD ASC");
            Log.debug("select CD_NM from BID.SYN_PUB_CODE WHERE CD_CLS = '"+s+"' order by CD ASC");
            executeQuery = prepareStatement.executeQuery();
            while (executeQuery.next()) {
                list.add(executeQuery.getString(1));
            }
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
      
        return list;
    }

	public int updateJC(ExportTBMTEntity[] etes,String[] val,int page_no,int pageSize) {
        final ArrayList list = new ArrayList();
        Trx trx = null;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        int[] executeQuery ;
        try {
            trx = new Trx(this, "USEMN");
            connection = trx.getConnection();
            connection.setAutoCommit(false);

            prepareStatement = connection.prepareStatement("UPDATE BID.BID_BID_MASTER SET BID_TYPE_DETAIL = ? WHERE BID_NO = ? AND BID_TURN_NO = ?");
            Log.debug("UPDATE BID.BID_BID_MASTER SET BID_TYPE_DETAIL = ? WHERE BID_NO = ? AND BID_TURN_NO = ?");
        
			for(int i=0;i<pageSize;i++){
				ExportTBMTEntity ete = etes[i+page_no*pageSize];
	            prepareStatement.setString(1, val[i]);
	            prepareStatement.setString(2,ete.getS01().substring(0,11));
	            prepareStatement.setString(3, ete.getS01().substring(ete.getS01().length()-2));
	            prepareStatement.addBatch();
			}
    
            executeQuery = prepareStatement.executeBatch();
            connection.commit();
        
        }
        catch (SQLException ex) {
            Log.debug(ex.toString());
        }
        catch (Exception ex2) {
            Log.debug(ex2.toString());
        }
        finally {
         
                
              
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
      
        return 1;
    }
	public static String MakeDot(final String s) {
        final NumberFormat instance = NumberFormat.getInstance(Locale.GERMAN);
        String format;
        if (s == null || s.equals("")) {
            format = "";
        }
        else {
            format = instance.format(Double.parseDouble(s));
        }
        return format.trim();
    }
	
	public static void main(String[] args) {
		List strList = new ArrayList();
		strList.add("1");
		strList.add("2");
		System.out.println(strList.toString().replaceAll("\\[|\\]", ""));
	}
}
