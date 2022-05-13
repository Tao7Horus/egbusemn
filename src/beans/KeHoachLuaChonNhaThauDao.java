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
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import common.Log;
import common.Trx;
import entity.UM_URV_UserC020b;

public class KeHoachLuaChonNhaThauDao {
	
	public int getKeHoachLuaChonNTListCount(String type, String condition, String fromDate, String toDate) {
		
		Trx trx = null;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet executeQuery = null;
		int countTotal = 0;
		Log.debug("getKeHoachLuaChonNTListCount() >>> START");
		List userIdList 			= new ArrayList();
		try {
			
			Log.debug("getKeHoachLuaChonNTListCount() user id list size >>> " + userIdList.size());
			
			StringBuffer khlcntSQL = new StringBuffer();
			
			khlcntSQL.append("  Select count(*)  FROM (  ");
			khlcntSQL.append(" SELECT A.PUBLIC_DT, A.BID_NO, A.BID_TURN_NO, A.BID_PLAN_PROJECT_NM, B.ZIP_CD, B.DEPARTMENT_NO, B.GROUP_NO, B.INSTITU_FULL_NM, A.BID_PLAN_PROJECT_PRICE_VND , A.BID_PLAN_PROJECT_PRICE_USD , A.BID_PLAN_PROJECT_PRICE_EUR ,A.BID_PLAN_PROJECT_PRICE_OTHER, A.BID_PLAN_PROJECT_REF_APPROVE, PROJECT_COUNT.TOTAL_PROJECT from BID.BID_PLAN_PROJECT_INFO A, USEMN.UM_REC_PUB_INSTITU_MAST B, USEMN.UM_USER C, (select A1.BID_PLAN_PROJECT_ID, count(A1.BID_PLAN_PROJECT_ID) as TOTAL_PROJECT from BID.BID_PLAN_PROJECT_INFO A1, BID.BID_PLAN_DETAIL A2 where A1.BID_PLAN_PROJECT_ID = A2.BID_PLAN_MASTER_ID and A1.BID_TURN_NO = (SELECT MAX(BID_TURN_NO) FROM BID.Bid_Plan_Project_Info WHERE BID_NO = A1.BID_NO)  group by (A1.BID_PLAN_PROJECT_ID)) PROJECT_COUNT WHERE PROJECT_COUNT.BID_PLAN_PROJECT_ID = A.BID_PLAN_PROJECT_ID and A.BID_NO IS NOT NULL AND A.PUBLIC_YN = 'Y' and A.REG_YN = 'Y' AND C.MAST_CD = B.INSTITU_CD and C.USER_ID = A.INPUT_USER_CD and A.BID_TURN_NO = (SELECT MAX(BID_TURN_NO) FROM BID.Bid_Plan_Project_Info WHERE BID_NO = A.BID_NO) ");
			khlcntSQL.append(" UNION ALL ");
			khlcntSQL.append("SELECT A.PUBLIC_DT, A.BID_NO, A.BID_TURN_NO, A.BID_PLAN_PROJECT_NM, B.ZIP_CD, B.DEPARTMENT_NO, B.GROUP_NO, B.INSTITU_FULL_NM, A.BID_PLAN_PROJECT_PRICE_VND , A.BID_PLAN_PROJECT_PRICE_USD ,A.BID_PLAN_PROJECT_PRICE_EUR ,A.BID_PLAN_PROJECT_PRICE_OTHER, A.BID_PLAN_PROJECT_REF_APPROVE, PROJECT_COUNT.TOTAL_PROJECT  FROM BID.BID_PLAN_PROJECT_INFO A, USEMN.UM_REC_PUB_INSTITU_MAST B, USEMN.UM_USER C, (select A1.BID_PLAN_PROJECT_ID, count(A1.BID_PLAN_PROJECT_ID) as TOTAL_PROJECT from BID.BID_PLAN_PROJECT_INFO A1, BID.BID_PLAN_DETAIL A2 where A1.BID_PLAN_PROJECT_ID = A2.BID_PLAN_MASTER_ID and A1.BID_NO is null  group by (A1.BID_PLAN_PROJECT_ID)) PROJECT_COUNT where PROJECT_COUNT.BID_PLAN_PROJECT_ID = A.BID_PLAN_PROJECT_ID AND C.MAST_CD = B.INSTITU_CD and C.USER_ID = A.INPUT_USER_CD AND A.BID_NO IS NULL AND  A.PUBLIC_YN = 'Y' and A.REG_YN = 'Y' ) resultq WHERE 1 = 1 ");
			
			if (fromDate != null && !fromDate.equals("") && toDate != null && !toDate.equals("")) {
				fromDate = String.valueOf(fromDate) + " 00:00:00";
				toDate = String.valueOf(toDate) + " 23:59:59";
				khlcntSQL.append(" \n AND resultq.PUBLIC_DT between  to_date('" + fromDate + "','dd/MM/yyyy hh24:mi:ss') and  to_date('" + toDate +"','dd/MM/yyyy hh24:mi:ss')");
			}
			
			if ("TTP".equals(type)) {
				if (condition != "" && condition != null) {
					khlcntSQL.append("AND resultq.ZIP_CD = '" + condition + "'");
				}
			}
			
			if ("BBN".equals(type)) {
				if (condition != "" && condition != null) {
					khlcntSQL.append("AND resultq.DEPARTMENT_NO = '" + condition + "'");
				}
			}
			
			if ("TD".equals(type)) {
				if (condition != "" && condition != null) {
					khlcntSQL.append("AND resultq.GROUP_NO = '" + condition + "'");
				}
			}
			
			Log.debug("getKeHoachLuaChonNTListCount() >>>> khlcntSQL count > " +  khlcntSQL);
			
			trx 				= new Trx(this);
			connection 			= trx.getConnection();
			prepareStatement 	= connection.prepareStatement(khlcntSQL.toString());
			executeQuery 		= prepareStatement.executeQuery();
             while (executeQuery.next()) {
            	 countTotal = executeQuery.getInt(1);
             }
			
		} catch (SQLException ex) {
			Log.debug("getKeHoachLuaChonNTListCount() >>>> " +  ex.getMessage()); 
		} catch (Exception ex2) {
			Log.debug("getKeHoachLuaChonNTListCount() >>>> " +  ex2.getMessage()); 
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
		return countTotal;
	}

	public UM_URV_UserC020b[] getKeHoachLuaChonNTList(String type, String condition, String fromDate, String toDate, String pageSize, String pageNo) {
		Trx trx = null;
		Connection connection = null;
		Log.debug("getKeHoachLuaChonNTList() >>> START");
		ResultSet executeQuery = null;
		PreparedStatement prepareStatement = null;
		final Vector vector = new Vector();
		final int page 		= Integer.parseInt(pageNo);
        final int startRow 	= page * Integer.parseInt(pageSize) - 9;
        final int endRow 	= page * Integer.parseInt(pageSize);
		try {
			StringBuffer khlcntSQL = new StringBuffer();
			khlcntSQL.append(" select rnum, to_char(PUBLIC_DT,'DD/MM/YYYY HH24:MI') as PUBLIC_DT, BID_NO, BID_TURN_NO, BID_PLAN_PROJECT_NM, ZIP_CD, DEPARTMENT_NO, GROUP_NO, INSTITU_FULL_NM, BID_PLAN_PROJECT_PRICE_VND , BID_PLAN_PROJECT_PRICE_USD , BID_PLAN_PROJECT_PRICE_EUR, BID_PLAN_PROJECT_PRICE_OTHER, BID_PLAN_PROJECT_REF_APPROVE, TOTAL_PROJECT from (select rownum rnum, tmp.* from (  Select resultq.*  ");
			khlcntSQL.append(" FROM ( ");
			khlcntSQL.append(" SELECT A.PUBLIC_DT, A.BID_NO, A.BID_TURN_NO, A.BID_PLAN_PROJECT_NM, B.ZIP_CD, B.DEPARTMENT_NO, B.GROUP_NO, B.INSTITU_FULL_NM, A.BID_PLAN_PROJECT_PRICE_VND , A.BID_PLAN_PROJECT_PRICE_USD , A.BID_PLAN_PROJECT_PRICE_EUR ,A.BID_PLAN_PROJECT_PRICE_OTHER, A.BID_PLAN_PROJECT_REF_APPROVE, PROJECT_COUNT.TOTAL_PROJECT from BID.BID_PLAN_PROJECT_INFO A, USEMN.UM_REC_PUB_INSTITU_MAST B, USEMN.UM_USER C, (select A1.BID_PLAN_PROJECT_ID, count(A1.BID_PLAN_PROJECT_ID) as TOTAL_PROJECT from BID.BID_PLAN_PROJECT_INFO A1, BID.BID_PLAN_DETAIL A2 where A1.BID_PLAN_PROJECT_ID = A2.BID_PLAN_MASTER_ID and A1.BID_TURN_NO = (SELECT MAX(BID_TURN_NO) FROM BID.Bid_Plan_Project_Info WHERE BID_NO = A1.BID_NO)  group by (A1.BID_PLAN_PROJECT_ID)) PROJECT_COUNT WHERE PROJECT_COUNT.BID_PLAN_PROJECT_ID = A.BID_PLAN_PROJECT_ID and A.BID_NO IS NOT NULL AND A.PUBLIC_YN = 'Y' and A.REG_YN = 'Y' AND C.MAST_CD = B.INSTITU_CD and C.USER_ID = A.INPUT_USER_CD and A.BID_TURN_NO = (SELECT MAX(BID_TURN_NO) FROM BID.Bid_Plan_Project_Info WHERE BID_NO = A.BID_NO) ");
			khlcntSQL.append(" UNION ALL ");
			khlcntSQL.append("SELECT A.PUBLIC_DT, A.BID_NO, A.BID_TURN_NO, A.BID_PLAN_PROJECT_NM, B.ZIP_CD, B.DEPARTMENT_NO, B.GROUP_NO, B.INSTITU_FULL_NM, A.BID_PLAN_PROJECT_PRICE_VND , A.BID_PLAN_PROJECT_PRICE_USD ,A.BID_PLAN_PROJECT_PRICE_EUR ,A.BID_PLAN_PROJECT_PRICE_OTHER, A.BID_PLAN_PROJECT_REF_APPROVE, PROJECT_COUNT.TOTAL_PROJECT  FROM BID.BID_PLAN_PROJECT_INFO A, USEMN.UM_REC_PUB_INSTITU_MAST B, USEMN.UM_USER C, (select A1.BID_PLAN_PROJECT_ID, count(A1.BID_PLAN_PROJECT_ID) as TOTAL_PROJECT from BID.BID_PLAN_PROJECT_INFO A1, BID.BID_PLAN_DETAIL A2 where A1.BID_PLAN_PROJECT_ID = A2.BID_PLAN_MASTER_ID and A1.BID_NO is null  group by (A1.BID_PLAN_PROJECT_ID)) PROJECT_COUNT where PROJECT_COUNT.BID_PLAN_PROJECT_ID = A.BID_PLAN_PROJECT_ID AND C.MAST_CD = B.INSTITU_CD and C.USER_ID = A.INPUT_USER_CD AND A.BID_NO IS NULL AND  A.PUBLIC_YN = 'Y' and A.REG_YN = 'Y' ) resultq WHERE 1 = 1 ");
			
			if (fromDate != null && !fromDate.equals("") && toDate != null && !toDate.equals("")) {
				fromDate = String.valueOf(fromDate) + " 00:00:00";
				toDate = String.valueOf(toDate) + " 23:59:59";
				khlcntSQL.append(" \n AND resultq.PUBLIC_DT between  to_date('" + fromDate + "','dd/MM/yyyy hh24:mi:ss') and  to_date('" + toDate +"','dd/MM/yyyy hh24:mi:ss')");
			}
			
			if ("TTP".equals(type)) {
				if (condition != "" && condition != null) {
					khlcntSQL.append("AND resultq.ZIP_CD = '" + condition + "'");
				}
			}
			
			if ("BBN".equals(type)) {
				if (condition != "" && condition != null) {
					khlcntSQL.append("AND resultq.DEPARTMENT_NO = '" + condition + "'");
				}
			}
			
			if ("TD".equals(type)) {
				if (condition != "" && condition != null) {
					khlcntSQL.append("AND resultq.GROUP_NO = '" + condition + "'");
				}
			}
			
			khlcntSQL.append(" \n order by resultq.PUBLIC_DT desc  ");
			khlcntSQL.append(" \n ) tmp ) ");
			khlcntSQL.append(" \n where rnum between '" + startRow + "' and '" + endRow + "' ");
			
			Log.debug("getKeHoachLuaChonNTList() >>>> khlcntSQL > " +  khlcntSQL); 
			
			trx 				= new Trx(this);
			connection 			= trx.getConnection();
			prepareStatement 	= connection.prepareStatement(khlcntSQL.toString());
			executeQuery 		= prepareStatement.executeQuery();
             while (executeQuery.next()) {
            	 UM_URV_UserC020b tmp = new UM_URV_UserC020b();
                 tmp.setS01(executeQuery.getString("RNUM"));
                 if (executeQuery.getString("BID_NO") != null && executeQuery.getString("BID_NO") != "") {
                	 tmp.setS02(executeQuery.getString("BID_NO") + " - " + executeQuery.getString("BID_TURN_NO"));
                 } else {
                	 tmp.setS02("");
                 }
                 tmp.setS03(executeQuery.getString("BID_PLAN_PROJECT_NM"));
                 tmp.setS04(executeQuery.getString("INSTITU_FULL_NM"));
                 tmp.setS05(executeQuery.getString("TOTAL_PROJECT"));
                 String a = executeQuery.getString("BID_PLAN_PROJECT_PRICE_VND");//(bid_plan_project_price_vnd);
				String b = executeQuery.getString("BID_PLAN_PROJECT_PRICE_USD");//(bid_plan_project_price_usd);
				String c = executeQuery.getString("BID_PLAN_PROJECT_PRICE_EUR");//(bid_plan_project_price_eur);
				String d = executeQuery.getString("BID_PLAN_PROJECT_PRICE_OTHER");//(bid_plan_project_price_other);
				String Sigma = "";
				if(a != null && !a.equals("0")){
					Sigma += MakeDot(a)+" VND + ";
				}
				if(b != null && !b.equals("0")){
					Sigma += MakeDot(b)+" USD + ";
				}
				if(c != null && !c.equals("0")){
					Sigma += MakeDot(c)+" EUR + ";
				}
				if(d != null && !d.equals("")){
					Sigma += d + " + ";
				}
				Sigma = Sigma.replaceAll("\\+\\s$", "");
				tmp.setS06(Sigma);
                tmp.setS07(executeQuery.getString("BID_PLAN_PROJECT_REF_APPROVE"));
                tmp.setS08(executeQuery.getString("PUBLIC_DT"));
                vector.addElement(tmp);
             }
			
		} catch (SQLException ex) {
			Log.debug("getKeHoachLuaChonNTList() >>>> " +  ex.getMessage()); 
		} catch (Exception ex2) {
			Log.debug("getKeHoachLuaChonNTList() >>>> " +  ex2.getMessage()); 
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
		final UM_URV_UserC020b[] array = new UM_URV_UserC020b[vector.size()];
		vector.copyInto(array);
		return array;
	}

	public UM_URV_UserC020b[] getKeHoachLuaChonNTList(String type, String condition, String fromDate, String toDate) {
		Trx trx = null;
		Connection connection = null;
		Log.debug("getKeHoachLuaChonNTList() >>> START");
		ResultSet executeQuery = null;
		PreparedStatement prepareStatement = null;
		final Vector vector = new Vector();
		try {
			StringBuffer khlcntSQL = new StringBuffer();
			khlcntSQL.append(" select rnum, to_char(PUBLIC_DT,'DD/MM/YYYY HH24:MI') as PUBLIC_DT, BID_NO, BID_TURN_NO, BID_PLAN_PROJECT_NM, ZIP_CD, DEPARTMENT_NO, GROUP_NO, INSTITU_FULL_NM, BID_PLAN_PROJECT_PRICE_VND , BID_PLAN_PROJECT_PRICE_USD , BID_PLAN_PROJECT_PRICE_EUR, BID_PLAN_PROJECT_PRICE_OTHER, BID_PLAN_PROJECT_REF_APPROVE, TOTAL_PROJECT from (select rownum rnum, tmp.* from (  Select resultq.*  ");
			khlcntSQL.append(" FROM ( ");
			khlcntSQL.append(" SELECT A.PUBLIC_DT, A.BID_NO, A.BID_TURN_NO, A.BID_PLAN_PROJECT_NM, B.ZIP_CD, B.DEPARTMENT_NO, B.GROUP_NO, B.INSTITU_FULL_NM, A.BID_PLAN_PROJECT_PRICE_VND , A.BID_PLAN_PROJECT_PRICE_USD , A.BID_PLAN_PROJECT_PRICE_EUR ,A.BID_PLAN_PROJECT_PRICE_OTHER, A.BID_PLAN_PROJECT_REF_APPROVE, PROJECT_COUNT.TOTAL_PROJECT from BID.BID_PLAN_PROJECT_INFO A, USEMN.UM_REC_PUB_INSTITU_MAST B, USEMN.UM_USER C, (select A1.BID_PLAN_PROJECT_ID, count(A1.BID_PLAN_PROJECT_ID) as TOTAL_PROJECT from BID.BID_PLAN_PROJECT_INFO A1, BID.BID_PLAN_DETAIL A2 where A1.BID_PLAN_PROJECT_ID = A2.BID_PLAN_MASTER_ID and A1.BID_TURN_NO = (SELECT MAX(BID_TURN_NO) FROM BID.Bid_Plan_Project_Info WHERE BID_NO = A1.BID_NO)  group by (A1.BID_PLAN_PROJECT_ID)) PROJECT_COUNT WHERE PROJECT_COUNT.BID_PLAN_PROJECT_ID = A.BID_PLAN_PROJECT_ID and A.BID_NO IS NOT NULL AND A.PUBLIC_YN = 'Y' and A.REG_YN = 'Y' AND C.MAST_CD = B.INSTITU_CD and C.USER_ID = A.INPUT_USER_CD and A.BID_TURN_NO = (SELECT MAX(BID_TURN_NO) FROM BID.Bid_Plan_Project_Info WHERE BID_NO = A.BID_NO) ");
			khlcntSQL.append(" UNION ALL ");
			khlcntSQL.append("SELECT A.PUBLIC_DT, A.BID_NO, A.BID_TURN_NO, A.BID_PLAN_PROJECT_NM, B.ZIP_CD, B.DEPARTMENT_NO, B.GROUP_NO, B.INSTITU_FULL_NM, A.BID_PLAN_PROJECT_PRICE_VND , A.BID_PLAN_PROJECT_PRICE_USD ,A.BID_PLAN_PROJECT_PRICE_EUR ,A.BID_PLAN_PROJECT_PRICE_OTHER, A.BID_PLAN_PROJECT_REF_APPROVE, PROJECT_COUNT.TOTAL_PROJECT  FROM BID.BID_PLAN_PROJECT_INFO A, USEMN.UM_REC_PUB_INSTITU_MAST B, USEMN.UM_USER C, (select A1.BID_PLAN_PROJECT_ID, count(A1.BID_PLAN_PROJECT_ID) as TOTAL_PROJECT from BID.BID_PLAN_PROJECT_INFO A1, BID.BID_PLAN_DETAIL A2 where A1.BID_PLAN_PROJECT_ID = A2.BID_PLAN_MASTER_ID and A1.BID_NO is null  group by (A1.BID_PLAN_PROJECT_ID)) PROJECT_COUNT where PROJECT_COUNT.BID_PLAN_PROJECT_ID = A.BID_PLAN_PROJECT_ID AND C.MAST_CD = B.INSTITU_CD and C.USER_ID = A.INPUT_USER_CD AND A.BID_NO IS NULL AND  A.PUBLIC_YN = 'Y' and A.REG_YN = 'Y' ) resultq WHERE 1 = 1 ");
			
			if (fromDate != null && !fromDate.equals("") && toDate != null && !toDate.equals("")) {
				fromDate = String.valueOf(fromDate) + " 00:00:00";
				toDate = String.valueOf(toDate) + " 23:59:59";
				khlcntSQL.append(" \n AND resultq.PUBLIC_DT between  to_date('" + fromDate + "','dd/MM/yyyy hh24:mi:ss') and  to_date('" + toDate +"','dd/MM/yyyy hh24:mi:ss')");
			}
			
			if ("TTP".equals(type)) {
				if (condition != "" && condition != null) {
					khlcntSQL.append("AND resultq.ZIP_CD = '" + condition + "'");
				}
			}
			
			if ("BBN".equals(type)) {
				if (condition != "" && condition != null) {
					khlcntSQL.append("AND resultq.DEPARTMENT_NO = '" + condition + "'");
				}
			}
			
			if ("TD".equals(type)) {
				if (condition != "" && condition != null) {
					khlcntSQL.append("AND resultq.GROUP_NO = '" + condition + "'");
				}
			}
			
			khlcntSQL.append(" \n order by resultq.PUBLIC_DT desc  ");
			khlcntSQL.append(" \n ) tmp ) ");
			khlcntSQL.append(" \n where rnum between '" + 1 + "' and '" + 100000 + "' ");
			
			Log.debug("getKeHoachLuaChonNTList() >>>> khlcntSQL > " +  khlcntSQL); 
			
			trx 				= new Trx(this);
			connection 			= trx.getConnection();
			prepareStatement 	= connection.prepareStatement(khlcntSQL.toString());
			executeQuery 		= prepareStatement.executeQuery();
             while (executeQuery.next()) {
            	 UM_URV_UserC020b tmp = new UM_URV_UserC020b();
                 tmp.setS01(executeQuery.getString("RNUM"));
                 if (executeQuery.getString("BID_NO") != null && executeQuery.getString("BID_NO") != "") {
                	 tmp.setS02(executeQuery.getString("BID_NO") + " - " + executeQuery.getString("BID_TURN_NO"));
                 } else {
                	 tmp.setS02("");
                 }
                 tmp.setS03(executeQuery.getString("BID_PLAN_PROJECT_NM"));
                 tmp.setS04(executeQuery.getString("INSTITU_FULL_NM"));
                 tmp.setS05(executeQuery.getString("TOTAL_PROJECT"));
                 String a = executeQuery.getString("BID_PLAN_PROJECT_PRICE_VND");//(bid_plan_project_price_vnd);
				String b = executeQuery.getString("BID_PLAN_PROJECT_PRICE_USD");//(bid_plan_project_price_usd);
				String c = executeQuery.getString("BID_PLAN_PROJECT_PRICE_EUR");//(bid_plan_project_price_eur);
				String d = executeQuery.getString("BID_PLAN_PROJECT_PRICE_OTHER");//(bid_plan_project_price_other);
				String Sigma = "";
				if(a != null && !a.equals("0")){
					Sigma += MakeDot(a)+" VND + ";
				}
				if(b != null && !b.equals("0")){
					Sigma += MakeDot(b)+" USD + ";
				}
				if(c != null && !c.equals("0")){
					Sigma += MakeDot(c)+" EUR + ";
				}
				if(d != null && !d.equals("")){
					Sigma += d + " + ";
				}
				Sigma = Sigma.replaceAll("\\+\\s$", "");
				tmp.setS06(Sigma);
                tmp.setS07(executeQuery.getString("BID_PLAN_PROJECT_REF_APPROVE"));
                tmp.setS08(executeQuery.getString("PUBLIC_DT"));
                vector.addElement(tmp);
             }
			
		} catch (SQLException ex) {
			Log.debug("getKeHoachLuaChonNTList() >>>> " +  ex.getMessage()); 
		} catch (Exception ex2) {
			Log.debug("getKeHoachLuaChonNTList() >>>> " +  ex2.getMessage()); 
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
		final UM_URV_UserC020b[] array = new UM_URV_UserC020b[vector.size()];
		vector.copyInto(array);
		return array;
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
