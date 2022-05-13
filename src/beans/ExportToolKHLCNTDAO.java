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

public class ExportToolKHLCNTDAO {
	
	

	public ExportTBMTEntity[] getKHLCNTList( String fromDate, String toDate) {
		Trx trx = null;
		Connection connection = null;
		ResultSet executeQuery = null;
		PreparedStatement prepareStatement = null;
		final Vector vector = new Vector();
		try {
			
			StringBuffer tbmtSql = new StringBuffer();
			
			
			tbmtSql.append("			SELECT bid_plan_master_id, bid_no || '-' || bid_turn_no sotbmt, bid_plan_project_nm, bid_plan_item_nm, NUM_GT,  c.institu_full_nm institu_full_nm,a.TOTALINVESMTNET price,   ");
			tbmtSql.append("			(SELECT CD_NM FROM CUST.PUB_CODE pc WHERE 1 = 1 AND CD_CLS = DECODE(bid_type,'1','Z47' ,'3','S14','5','Z10','10','Z99','15','Z48','12','Z49') AND cd = bid_plan_item_method_select) || ', ' ||   ");
			tbmtSql.append("			bid_plan_item_isinternational || ', ' ||  bid_plan_item_ispq || ', ' || bid_plan_item_method plan_info,(SELECT CD_NM FROM CUST.PUB_CODE pc WHERE 1 = 1  AND CD_CLS = DECODE(bid_type,'1','L17' ,'3','S01','5','S90','10','S99','15','L18','12','L30') AND cd = contract_style) contract_style_info,  ");
			tbmtSql.append("			datetime_bid,   contract_days || ' ' || contract_days_style  contract_time, a.jobw NGANHNGHE, a.BID_TYPE_DETAIL,   ");
			tbmtSql.append("			BID_PLAN_ITEM_DETAILS_ID,public_dt,input_user_cd,DECODE(BID_PLAN_ITEM_INTERNET,'00','Không qua mạng','01','Qua mạng') BID_PLAN_ITEM_INTERNET,institu_cd,BID_FUND_DETAIL,PROJECTNO FROM (SELECT b.bid_no,b.bid_turn_no, bid_plan_project_nm,public_dt, bid_plan_project_id,input_user_cd,NUM_GT,PROJECTNO  FROM bid.bid_plan_project_info b   ");
			tbmtSql.append("			WHERE  b.PUBLIC_DT BETWEEN TO_DATE('" + fromDate + "','DD/MM/YYYY') AND TO_DATE('" + toDate +"','DD/MM/YYYY')  +0.9999999   AND reg_yn LIKE 'Y' AND  public_yn = 'Y') e LEFT JOIN ( SELECT bid_plan_master_id, bid_plan_item_nm,   BID_PLAN_ITEM_DETAILS_ID ,  ");
			tbmtSql.append("			TOTALINVESMTNET, bid_plan_item_price, bid_plan_item_isinternational,   bid_plan_item_ispq,  bid_plan_item_method,   bid_plan_item_classify,  DECODE(TIME_START_LC_STYLE,'0','tháng '||TIME_MONTH_NUMBER_START_LC|| ' năm ' || TIME_YEAR_NUMBER_START_LC,'quý '||TIME_QUARTER_NUMBER_START_LC|| ' năm ' || TIME_YEAR_NUMBER_START_LC1) datetime_bid,  contract_days,    bid_plan_project_price_other,  "); 
			tbmtSql.append("			bid_type,  contract_days_style,bid_plan_item_method_select,contract_style,	   (SELECT CD_NM FROM BID.SYN_PUB_CODE WHERE CD_CLS = 'Z88' AND CD =  BID_TYPE_DETAIL )  jobw,BID_TYPE_DETAIL,BID_PLAN_ITEM_INTERNET,BID_FUND_DETAIL FROM bid.bid_plan_detail ) a ON a.bid_plan_master_id = e.bid_plan_project_id  LEFT JOIN usemn.um_pub_institu_mast c ON substr(input_user_cd,2,7) = c.institu_cd   ");
			

//			tbmtSql.append(" SELECT bid_plan_master_id, bid_no || '-' || bid_turn_no sotbmt, bid_plan_project_nm, bid_plan_item_nm, bid_plan_item_ref_num,  c.institu_full_nm institu_full_nm,a.bid_plan_project_price_vnd || ' VND ' || DECODE(a.priceusd, '0 USD','',a.priceusd)  || DECODE(a.priceeur, '0 EUR','',a.priceeur) price, ");
//			tbmtSql.append(" (SELECT CD_NM FROM CUST.PUB_CODE pc WHERE 1 = 1 AND CD_CLS = DECODE(bid_type,'1','Z47' ,'3','S14','5','Z10','10','Z99','15','Z48','12','Z49') AND cd = bid_plan_item_method_select) || ', ' || ");
//  			tbmtSql.append(" bid_plan_item_isinternational || ', ' ||  bid_plan_item_ispq || ', ' || bid_plan_item_method plan_info,(SELECT CD_NM FROM CUST.PUB_CODE pc WHERE 1 = 1  AND CD_CLS = DECODE(bid_type,'1','L17' ,'3','S01','5','S90','10','S99','15','L18','12','L30') AND cd = contract_style) contract_style_info,");
//  			tbmtSql.append("datetime_bid,   contract_days || ' ' || contract_days_style  contract_time, a.jobw NGANHNGHE, a.BID_TYPE_DETAIL, ");
//			tbmtSql.append(" BID_PLAN_ITEM_DETAILS_ID,public_dt,input_user_cd FROM (SELECT b.bid_no,b.bid_turn_no, bid_plan_project_nm,public_dt, bid_plan_project_id,input_user_cd  FROM bid.bid_plan_project_info b ");
//			tbmtSql.append(" WHERE  b.PUBLIC_DT BETWEEN TO_DATE('" + fromDate + "','DD/MM/YYYY') AND TO_DATE('" + toDate +"','DD/MM/YYYY')  +0.9999999 AND  b.bid_turn_no = ( SELECT MAX(bid_turn_no) FROM bid.bid_plan_project_info ");
//			tbmtSql.append(" WHERE public_yn = 'Y'  AND bid_no = b.bid_no  )  AND reg_yn LIKE 'Y' AND  public_yn = 'Y') e LEFT JOIN ( SELECT bid_plan_master_id, bid_plan_item_nm,   BID_PLAN_ITEM_DETAILS_ID ,");
//			tbmtSql.append(" bid_plan_item_ref_num,  bid_plan_project_price_vnd, bid_plan_project_price_usd || ' USD' priceusd, bid_plan_project_price_eur || ' EUR' priceeur, bid_plan_item_price,");
//			tbmtSql.append(" bid_plan_item_isinternational,   bid_plan_item_ispq,  bid_plan_item_method,   bid_plan_item_classify,  bid_plan_time, contract_days,    bid_plan_project_price_other, ");
//			tbmtSql.append(" datetime_bid,   bid_type,  contract_days_style,bid_plan_item_method_select,contract_style,	   (SELECT CD_NM FROM BID.SYN_PUB_CODE WHERE CD_CLS = 'Z88' AND CD =  BID_TYPE_DETAIL )  jobw,BID_TYPE_DETAIL");
//			tbmtSql.append(" FROM bid.bid_plan_detail ) a ON a.bid_plan_master_id = e.bid_plan_project_id  LEFT JOIN usemn.um_pub_institu_mast c ON substr(input_user_cd,2,7) = c.institu_cd ");

			  
			  
			Log.debug("getTBMTList() >>>>TBMT of KHLCNT SQL > " +  tbmtSql.toString()); 
			
			trx 				= new Trx(this);
			connection 			= trx.getConnection();
			prepareStatement 	= connection.prepareStatement(tbmtSql.toString());
			executeQuery 		= prepareStatement.executeQuery();
             while (executeQuery.next()) {
            	 ExportTBMTEntity tmp = new ExportTBMTEntity();
                 tmp.setS01(executeQuery.getString("SOTBMT"));                
                 tmp.setS02(executeQuery.getString("BID_PLAN_PROJECT_NM"));
                 tmp.setS03(executeQuery.getString("bid_plan_item_nm"));
                 tmp.setS04(executeQuery.getString("NUM_GT"));
                 tmp.setS05(executeQuery.getString("INSTITU_FULL_NM")); 
                 tmp.setS06(executeQuery.getString("price")); 
                 tmp.setS07(executeQuery.getString("plan_info")); 
                 tmp.setS08(executeQuery.getString("datetime_bid")); 
                 tmp.setS09(executeQuery.getString("contract_time")); 
                 tmp.setS10(executeQuery.getString("contract_style_info")); 
                 tmp.setS11(executeQuery.getString("NGANHNGHE")); 
                 tmp.setS12(executeQuery.getString("BID_PLAN_ITEM_INTERNET")); 
                 tmp.setS13(executeQuery.getString("PROJECTNO")); 
                 tmp.setS14(executeQuery.getString("BID_FUND_DETAIL")); 
                 tmp.setS15(executeQuery.getString("institu_cd")); 
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
