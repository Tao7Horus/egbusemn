package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import common.DateTime;
import common.Log;
import common.Trx;
import entity.UM_RAE_ConuB010b;

public class NTChuaThanhToanSearch {
	
	public UM_RAE_ConuB010b[] searchNTChuaThanhToan(int pageNum, int pageMAX, String saupNo, String sangho, String sdate, String edate, String status, String pmstatus)
	  {
		
	    Connection con = null;
	    Trx trx = null;
	    ResultSet rs = null;
	    PreparedStatement pstm = null;
	    Vector vec = new Vector();
	    UM_RAE_ConuB010b[] ettlist = (UM_RAE_ConuB010b[])null;
	    int start = (pageNum - 1) * pageMAX + 1;
	    int end = pageNum * pageMAX;
	    pmstatus = "B";//chua thanh toan
	    
	    //Hiển thị những trường hợp nợ tính đến năm hiện tại
	    int currentYear = DateTime.getYear();
	    
	    try
	    {
	      String sql = "";
	      if (("D".equals(status)) || ("C".equals(status))) //status luon bang N trong man hinh nay
	      {
	        sql = "select * from (select t1.BIZ_REG_NO, t1.COM_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t1.REPR_NM, t1.PROCESS_ST, ROWNUM N, DECODE(PAID, NULL, 'Chưa thanh toán', 'Đã thanh toán') PAID from UM_EDOC_STATE t1 ";
	        sql = sql + " , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B";
	        sql = sql + " where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1,2)";
	        sql = sql + " GROUP BY BIZ_REG_NO) C";
	        sql = sql + " where PROCESS_ST in ('3', '4') ";
	        sql = sql + " AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
	        if ((saupNo != null) && (saupNo.length() != 0)) {
	          sql = sql + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
	        }
	        if ((sangho != null) && (sangho.length() != 0)) {
	          sql = sql + " and t1.BIZ_NM like '%" + sangho + "%'";
	        }
	        if ("D".equals(status)) {
	          sql = sql + " and  t1.PROCESS_ST ='3' ";
	        } else if ("C".equals(status)) {
	          sql = sql + " and  t1.PROCESS_ST ='4' ";
	        }
	        if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + " and t1.REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + " and t1.REG_DT > to_date('" + sdate + "', 'dd/mm/yyyy')";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + " and t1.REG_DT < to_date('" + edate + "', 'dd/mm/yyyy')";
	        }
	        if (pmstatus.equals("B")) {
	          sql = sql + " and PAID is null";
	        }
	        if (pmstatus.equals("C")) {
	          sql = sql + " and PAID is not null";
	        }
	        sql = sql + " order by t1.REG_DT DESC";
	        
	        sql = sql + ") where N between " + start + " and " + end;
	      }
	      else { //ANHH9 edit sql
	    	  sql = "select * from(  "
					+ "select ROWNUM N,BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, REPR_EMAIL, REPR_MOBILE, REG_YN, RECV_NO , EMAIL, MOBILE,PHONE_NO, DECODE(PAID, NULL,'Chưa thanh toán', DECODE(sign(PAID - " + currentYear + "), -1, ''||PAID, 'Đã thanh toán')) PAID, NM , PM_ITEM_DK,PM_ITEM_DT,MAX_YEAR, DOC_CREAT_DT "
					+ "from (  	"
					+ "		select t1.BIZ_REG_NO, DECODE(d.BIZ_NM, NULL, t1.BIZ_NM, d.BIZ_NM) BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.REG_YN, t1.RECV_NO, C.PAID , t2.REPR_EMAIL, t2.REPR_MOBILE, ua.MOBILE, ua.EMAIL, t1.PHONE_NO, ua.NM ,e.PM_ITEM_DK,f.PM_ITEM_DT,C.PAID AS MAX_YEAR,TO_CHAR(t1.DOC_CREAT_DT, 'dd/mm/yyyy') DOC_CREAT_DT"
					+ "		from UM_REC_SUPPLIER_ENTER_MAST t1,"
					+ "			 UM_REC_REPR t2 , "
					+ "			 (SELECT BIZ_REG_NO, MAX(B.PM_YEAR) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1,2) GROUP BY BIZ_REG_NO) C  , "
					+ " 		 (select y.PM_ITEM_ID as PM_ITEM_DK,x.BIZ_REG_NO  from BID.BID_PAYMENT_TABLE x, BID.BID_PAYMENT_DETAIL y where x.PM_TABLE_ID=y.PM_TABLE_ID   and x.PM_BNK_STATUS_VALUE='0' and x.PM_FINISH_YN='Y' and y.PM_ITEM_ID='1' )  e "
					+ "   		,(select y.PM_ITEM_ID as PM_ITEM_DT,x.BIZ_REG_NO from BID.BID_PAYMENT_TABLE x, BID.BID_PAYMENT_DETAIL y where x.PM_TABLE_ID=y.PM_TABLE_ID  and x.PM_BNK_STATUS_VALUE='0' and x.PM_FINISH_YN='Y' and y.PM_ITEM_ID='2' )  f," 
					+ "			 UM_SUPPLIER_ENTER_MAST d,"
					+ "			 UM_BID_AGENT ua"
					+ "			 where "
					+ "				(d.TEST_OPTION_YN is null OR d.TEST_OPTION_YN = 'N') "
					+ "				and ua.BIZ_REG_NO = t1.BIZ_REG_NO"
					+ "				and t1.BIZ_REG_NO = t2.BIZ_REG_NO "
					+ "				and t2.REPR_ISMAIN = 'Y' "
					+ "				and t1.BIZ_REG_NO = d.BIZ_REG_NO(+) " 
					+ "				AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) "  
	    	  		+ "				and ua.BIZ_REG_NO =e.BIZ_REG_NO(+) " 
	    	  		+ "				and ua.BIZ_REG_NO =f.BIZ_REG_NO (+) "  ;
	    	  
	      //  sql = "select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, REG_YN, RECV_NO , ROWNUM N, DECODE(PAID, NULL, 'Chưa thanh toán', 'Đã thanh toán') PAID from (  select t1.BIZ_REG_NO, DECODE(d.BIZ_NM, NULL, t1.BIZ_NM, d.BIZ_NM) BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.REG_YN, t1.RECV_NO, C.PAID from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2 , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1) GROUP BY BIZ_REG_NO) C  , UM_SUPPLIER_ENTER_MAST d   where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y' and t1.BIZ_REG_NO = d.BIZ_REG_NO(+)  AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
	        if ((saupNo != null) && (saupNo.length() != 0)) {
	          sql = sql + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
	        }
	        if ((sangho != null) && (sangho.length() != 0)) {
	          sql = sql + " and t1.BIZ_NM like '%" + sangho + "%'";
	        }
	        if ((status == null) || (status.length() == 0)) {
	          sql = sql + " and  t1.REG_YN ='N' ";
	        } else if ((status != null) && (status.length() != 0) && (!"A".equals(status))) {
	          sql = sql + " and  t1.REG_YN ='" + status + "' ";
	        }
	        if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + " and t1.REG_DT >= to_date('" + sdate + " 00:00', 'dd/mm/yyyy HH24:MI') and t1.REG_DT <= to_date('" + edate + " 23:59', 'dd/mm/yyyy HH24:MI')";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + " and t1.REG_DT >= to_date('" + sdate + " 00:00', 'dd/mm/yyyy HH24:MI')";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + " and t1.REG_DT <= to_date('" + edate + " 23:59', 'dd/mm/yyyy HH24:MI')";
	        }
	        if (pmstatus.equals("B")) {
	          sql = sql + " and (PAID is null OR PAID < " + currentYear + ")";
	        }
	        if (pmstatus.equals("C")) {
	          sql = sql + " and PAID is not null";
	        }
	        sql = sql + " order by t1.REG_DT DESC, recv_no asc";
	        sql = sql + ")) t3 where t3.N between " + start + " and " + end;
	      }
	      
	      Log.debug("anhh9=sql--" + sql);
	      trx = new Trx(this, "usemn");
	      con = trx.getConnection();
	      pstm = con.prepareStatement(sql);
	      
	      rs = pstm.executeQuery();
	      pstm.clearParameters();
	      rs = pstm.executeQuery();
	      pstm.clearParameters();
	      while (rs.next())
	      {
	        UM_RAE_ConuB010b ett = new UM_RAE_ConuB010b();
	        ett.setRowNum(rs.getString("N"));
	        ett.setRegDT(rs.getString("REG_DT"));
	        ett.setBizNM(rs.getString("BIZ_NM"));
	        ett.setBizRegNo(rs.getString("BIZ_REG_NO"));
	        ett.setPhoneNo(rs.getString("PHONE_NO"));
	        ett.setReprMobile(rs.getString("REPR_MOBILE"));
	        ett.setReprEmail(rs.getString("REPR_EMAIL"));
	        ett.setMobile(rs.getString("MOBILE"));
	        ett.setEmail(rs.getString("EMAIL"));
	        ett.setReprNM(rs.getString("REPR_NM"));
	        ett.setNm(rs.getString("NM"));
	        ett.setSinchungDate(rs.getString("DOC_CREAT_DT"));
	        ett.setMaxYear(rs.getString("MAX_YEAR"));
	        ett.setIsDangKy(rs.getString("PM_ITEM_DK"));
	        ett.setIsDuyTri(rs.getString("PM_ITEM_DT"));
	        // PM_ITEM_DK,PM_ITEM_DT,MAX_YEAR
	        vec.addElement(ett);
	      }
	    }
	    catch (SQLException sqle)
	    {
	      Log.debug(
	        "NTChuaThanhToanSearch.searchNTChuaThanhToan() SQLException : " + sqle.toString());
	      if (rs != null) {
	        try
	        {
	          rs.close();
	        }
	        catch (Exception localException1) {}
	      }
	      if (pstm != null) {
	        try
	        {
	          pstm.close();
	        }
	        catch (Exception localException2) {}
	      }
	      if (con != null) {
	        try
	        {
	          trx.close();
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	      }
	    }
	    catch (Exception exc)
	    {
	      Log.debug(
	        "NTChuaThanhToanSearch.searchNTChuaThanhToan() " + exc.toString());
	      if (rs != null) {
	        try
	        {
	          rs.close();
	        }
	        catch (Exception localException3) {}
	      }
	      if (pstm != null) {
	        try
	        {
	          pstm.close();
	        }
	        catch (Exception localException4) {}
	      }
	      if (con != null) {
	        try
	        {
	          trx.close();
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	      }
	    }
	    finally
	    {
	      if (rs != null) {
	        try
	        {
	          rs.close();
	        }
	        catch (Exception localException5) {}
	      }
	      if (pstm != null) {
	        try
	        {
	          pstm.close();
	        }
	        catch (Exception localException6) {}
	      }
	      if (con != null) {
	        try
	        {
	          trx.close();
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	      }
	    }
	    if (vec.size() > 0)
	    {
	      ettlist = new UM_RAE_ConuB010b[vec.size()];
	      vec.copyInto(ettlist);
	      
	      return ettlist;
	    }
	    return null;
	  }
	
	 public int getCount(String saupNo, String sangho, String sdate, String edate, String status, String pmstatus)
	  {
		 int count=0;
		 Connection con = null;
		    Trx trx = null;
		    ResultSet rs = null;
		    PreparedStatement pstm = null;
		    pmstatus = "B";//chua thanh toan
		    
		    int currentYear = DateTime.getYear();
		    
		    try
		    {
		      String sql = "";
		      if (("D".equals(status)) || ("C".equals(status))) //status luon bang N trong man hinh nay
		      {
		        sql = "select count(*) from (select t1.BIZ_REG_NO, t1.COM_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t1.REPR_NM, t1.PROCESS_ST, ROWNUM N, DECODE(PAID, NULL, 'Chưa thanh toán', 'Đã thanh toán') PAID from UM_EDOC_STATE t1 ";
		        sql = sql + " , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B";
		        sql = sql + " where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1,2)";
		        sql = sql + " GROUP BY BIZ_REG_NO) C";
		        sql = sql + " where PROCESS_ST in ('3', '4') ";
		        sql = sql + " AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
		        if ((saupNo != null) && (saupNo.length() != 0)) {
		          sql = sql + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
		        }
		        if ((sangho != null) && (sangho.length() != 0)) {
		          sql = sql + " and t1.BIZ_NM like '%" + sangho + "%'";
		        }
		        if ("D".equals(status)) {
		          sql = sql + " and  t1.PROCESS_ST ='3' ";
		        } else if ("C".equals(status)) {
		          sql = sql + " and  t1.PROCESS_ST ='4' ";
		        }
		        if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + " and t1.REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')";
		        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
		          sql = sql + " and t1.REG_DT > to_date('" + sdate + "', 'dd/mm/yyyy')";
		        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + " and t1.REG_DT < to_date('" + edate + "', 'dd/mm/yyyy')";
		        }
		        if (pmstatus.equals("B")) {
		          sql = sql + " and PAID is null";
		        }
		        if (pmstatus.equals("C")) {
		          sql = sql + " and PAID is not null";
		        }
		        sql = sql + " order by t1.REG_DT DESC";
		        
		        sql = sql + ")";
		      }
		      else { //ANHH9 edit sql
		    	  sql = "select count(*) from(  "
						+ "select ROWNUM N,BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, REPR_EMAIL, REPR_MOBILE, REG_YN, RECV_NO , EMAIL, MOBILE,PHONE_NO, DECODE(PAID, NULL,'Chưa thanh toán', 'Đã thanh toán') PAID "
						+ "from (  	"
						+ "		select t1.BIZ_REG_NO, DECODE(d.BIZ_NM, NULL, t1.BIZ_NM, d.BIZ_NM) BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.REG_YN, t1.RECV_NO, C.PAID , t2.REPR_EMAIL, t2.REPR_MOBILE, ua.MOBILE, ua.EMAIL, t1.PHONE_NO"
						+ "		from UM_REC_SUPPLIER_ENTER_MAST t1,"
						+ "			 UM_REC_REPR t2 , "
						+ "			 (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1,2) AND B.PM_YEAR = " + currentYear + " GROUP BY BIZ_REG_NO) C  , "
						+ "			 UM_SUPPLIER_ENTER_MAST d,"
						+ "			 UM_BID_AGENT ua"
						+ "			 where "
						+ "				(d.TEST_OPTION_YN is null or d.TEST_OPTION_YN = 'N')"
						+ "				and ua.BIZ_REG_NO = t1.BIZ_REG_NO"
						+ "				and t1.BIZ_REG_NO = t2.BIZ_REG_NO "
						+ "				and t2.REPR_ISMAIN = 'Y' "
						+ "				and t1.BIZ_REG_NO = d.BIZ_REG_NO(+) " 
						+ "				AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) "  ;
		    	  
		      //  sql = "select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, REG_YN, RECV_NO , ROWNUM N, DECODE(PAID, NULL, 'Chưa thanh toán', 'Đã thanh toán') PAID from (  select t1.BIZ_REG_NO, DECODE(d.BIZ_NM, NULL, t1.BIZ_NM, d.BIZ_NM) BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.REG_YN, t1.RECV_NO, C.PAID from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2 , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1) GROUP BY BIZ_REG_NO) C  , UM_SUPPLIER_ENTER_MAST d   where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y' and t1.BIZ_REG_NO = d.BIZ_REG_NO(+)  AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
		        if ((saupNo != null) && (saupNo.length() != 0)) {
		          sql = sql + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
		        }
		        if ((sangho != null) && (sangho.length() != 0)) {
		          sql = sql + " and t1.BIZ_NM like '%" + sangho + "%'";
		        }
		        if ((status == null) || (status.length() == 0)) {
		          sql = sql + " and  t1.REG_YN ='N' ";
		        } else if ((status != null) && (status.length() != 0) && (!"A".equals(status))) {
		          sql = sql + " and  t1.REG_YN ='" + status + "' ";
		        }
		        if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + " and t1.REG_DT >= to_date('" + sdate + " 00:00', 'dd/mm/yyyy HH24:MI') and t1.REG_DT <= to_date('" + edate + " 23:59', 'dd/mm/yyyy HH24:MI')";
		        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
		          sql = sql + " and t1.REG_DT >= to_date('" + sdate + " 00:00', 'dd/mm/yyyy HH24:MI')";
		        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + " and t1.REG_DT <= to_date('" + edate + " 23:59', 'dd/mm/yyyy HH24:MI')";
		        }
		        if (pmstatus.equals("B")) {
		          sql = sql + " and PAID is null";
		        }
		        if (pmstatus.equals("C")) {
		          sql = sql + " and PAID is not null";
		        }
		        sql = sql + " order by t1.REG_DT DESC, recv_no asc";
		        sql = sql + "))";
		      }
		      Log.debug("anhh9=sql--" + sql);
		      trx = new Trx(this, "usemn");
		      con = trx.getConnection();
		      pstm = con.prepareStatement(sql);
		      
		      rs = pstm.executeQuery();
		      pstm.clearParameters();
		      rs = pstm.executeQuery();
		      pstm.clearParameters();
		      while (rs.next())
		      {
		        count=rs.getInt(1);
		      }
		    }
		    catch (SQLException sqle)
		    {
		      Log.debug(
		        "NTChuaThanhToanSearch.searchNTChuaThanhToan() SQLException : " + sqle.toString());
		      if (rs != null) {
		        try
		        {
		          rs.close();
		        }
		        catch (Exception localException1) {}
		      }
		      if (pstm != null) {
		        try
		        {
		          pstm.close();
		        }
		        catch (Exception localException2) {}
		      }
		      if (con != null) {
		        try
		        {
		          trx.close();
		        }
		        catch (Exception e)
		        {
		          e.printStackTrace();
		        }
		      }
		    }
		    catch (Exception exc)
		    {
		      Log.debug(
		        "NTChuaThanhToanSearch.searchNTChuaThanhToan() " + exc.toString());
		      if (rs != null) {
		        try
		        {
		          rs.close();
		        }
		        catch (Exception localException3) {}
		      }
		      if (pstm != null) {
		        try
		        {
		          pstm.close();
		        }
		        catch (Exception localException4) {}
		      }
		      if (con != null) {
		        try
		        {
		          trx.close();
		        }
		        catch (Exception e)
		        {
		          e.printStackTrace();
		        }
		      }
		    }
		    finally
		    {
		      if (rs != null) {
		        try
		        {
		          rs.close();
		        }
		        catch (Exception localException5) {}
		      }
		      if (pstm != null) {
		        try
		        {
		          pstm.close();
		        }
		        catch (Exception localException6) {}
		      }
		      if (con != null) {
		        try
		        {
		          trx.close();
		        }
		        catch (Exception e)
		        {
		          e.printStackTrace();
		        }
		      }
		    }
		    return count;
	  }
	 public int getCountSearchNTChuaThanhToan(String saupNo, String sangho, String sdate, String edate, String status, String pmstatus)
	  {
	    Connection con = null;
	    Trx trx = null;
	    ResultSet rs = null;
	    PreparedStatement pstm = null;
	    int count = 0;
	    try
	    {
	      String sql = "";
	      if (("D".equals(status)) || ("C".equals(status))) //khong chay vao vong if nay
	      {
	        sql = "select count(*) from UM_EDOC_STATE t1";
	        sql = sql + " , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B";
	        sql = sql + " where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1)";
	        sql = sql + " GROUP BY BIZ_REG_NO) C";
	        sql = sql + " where PROCESS_ST in ('3', '4') ";
	        sql = sql + " AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
	        if ((saupNo != null) && (saupNo.length() != 0)) {
	          sql = sql + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
	        }
	        if ((sangho != null) && (sangho.length() != 0)) {
	          sql = sql + " and t1.BIZ_NM like '%" + sangho + "%'";
	        }
	        if ("D".equals(status)) {
	          sql = sql + " and  t1.PROCESS_ST ='3' ";
	        } else if ("C".equals(status)) {
	          sql = sql + " and  t1.PROCESS_ST ='4' ";
	        }
	        if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + " and t1.REG_DT between to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') and to_date(' 23:59:59" + edate + "', 'dd/MM/yyyy HH24:MI:SS')";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + " and t1.REG_DT > to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + " and t1.REG_DT < to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
	        }
	        if (pmstatus.equals("B")) {
	          sql = sql + " and PAID is null";
	        }
	        if (pmstatus.equals("C")) {
	          sql = sql + " and PAID is not null";
	        }
	      }
	      else
	      {//ANHH9 sua cau lenh query
	    	  sql = "select count(*)  from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2  , UM_BID_AGENT ua, (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1) GROUP BY BIZ_REG_NO) C where ua.BIZ_REG_NO = t1.BIZ_REG_NO  and t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y'  AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
	        if ((saupNo != null) && (saupNo.length() != 0)) {
	          sql = sql + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
	        }
	        if ((sangho != null) && (sangho.length() != 0)) {
	          sql = sql + " and t1.BIZ_NM like '%" + sangho + "%'";
	        }
	        if ((status == null) || (status.length() == 0)) {
	          sql = sql + " and  t1.REG_YN ='N' ";
	        } else if ((status != null) && (status.length() != 0) && (!"A".equals(status))) {
	          sql = sql + " and  t1.REG_YN ='" + status + "' ";
	        }
	        if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + " and t1.REG_DT between to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + " and t1.REG_DT > to_date('" + sdate + " 00:00:00', 'dd/MM/yyyy HH24:MI:SS')";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + " and t1.REG_DT < to_date('" + edate + " 23:59:59', 'dd/MM/yyyy HH24:MI:SS')";
	        }
	        if (pmstatus.equals("B")) {
	          sql = sql + " and PAID is null";
	        }
	        if (pmstatus.equals("C")) {
	          sql = sql + " and PAID is not null";
	        }
	      }
	      trx = new Trx(this, "usemn");
	      con = trx.getConnection();
	      pstm = con.prepareStatement(sql);
	      Log.debug("NTChuaThanhToanSearch.getCountSearchNTChuaThanhToan() >>" + sql);
	      rs = pstm.executeQuery();
	      pstm.clearParameters();
	      rs = pstm.executeQuery();
	      pstm.clearParameters();
	      while (rs.next()) {
	        count = rs.getInt(1);
	      }
	      Log.debug("NTChuaThanhToanSearch.getCountSearchNTChuaThanhToan() COUNT >>" + count);
	    }
	    catch (SQLException sqle)
	    {
	      Log.debug(
	        "NTChuaThanhToanSearch.getCountSearchNTChuaThanhToan() SQLException : " + sqle.toString());
	      if (rs != null) {
	        try
	        {
	          rs.close();
	        }
	        catch (Exception localException1) {}
	      }
	      if (pstm != null) {
	        try
	        {
	          pstm.close();
	        }
	        catch (Exception localException2) {}
	      }
	      if (con != null) {
	        try
	        {
	          trx.close();
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	      }
	    }
	    catch (Exception exc)
	    {
	      Log.debug(
	        "NTChuaThanhToanSearch.getCountSearchNTChuaThanhToan() Exception : " + exc.toString());
	      if (rs != null) {
	        try
	        {
	          rs.close();
	        }
	        catch (Exception localException3) {}
	      }
	      if (pstm != null) {
	        try
	        {
	          pstm.close();
	        }
	        catch (Exception localException4) {}
	      }
	      if (con != null) {
	        try
	        {
	          trx.close();
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	      }
	    }
	    finally
	    {
	      if (rs != null) {
	        try
	        {
	          rs.close();
	        }
	        catch (Exception localException5) {}
	      }
	      if (pstm != null) {
	        try
	        {
	          pstm.close();
	        }
	        catch (Exception localException6) {}
	      }
	      if (con != null) {
	        try
	        {
	          trx.close();
	        }
	        catch (Exception e)
	        {
	          e.printStackTrace();
	        }
	      }
	    }
	    return count;
	  }
	 

		
	 public String getInfoPayment(String currentYear,String maxYear,String bidDangKy,String bidDuyTri,String appYear){
		 String res="";
		 String appYearOnly =getYearFormDateTime(appYear);
		 if(("1".equals(bidDangKy) && !"2".equals(bidDuyTri)) || !"1".equals(bidDangKy) && "2".equals(bidDuyTri)||"1".equals(bidDangKy) && "2".equals(bidDuyTri)){
			 //if(((getDateFromString(appYear).before(getDateFromString("31/12/2015")))|| (getDateFromString(appYear).equals(getDateFromString("31/12/2015")))) && (getDateFromString(appYear).after(getDateFromString("01/11/2015"))||(getDateFromString(appYear).equals(getDateFromString("01/11/2015"))) ) && Integer.parseInt(maxYear) < 2016 ){
			if(getDateFromString(appYear).before(getDateFromString("01/01/2016")) && Integer.parseInt(maxYear) < 2016 ){
				 res = "Tài khoản chưa đóng phí duy trì năm 2017. Thời hạn hiệu lực tới ngày 31/03/"+currentYear+".";
			 }else if(Integer.parseInt(appYearOnly) < Integer.parseInt("2016")){
				if(((Integer.parseInt(maxYear)+1)+"").equals("2017")){
					 res = "Tài khoản chưa đóng phí duy trì năm 2017. Thời hạn hiệu lực tới ngày 31/03/"+currentYear+".";
				 }else{
					 res = "Tài khoản chưa đóng phí duy trì năm "+(Integer.parseInt(maxYear)+1)+","+currentYear+". Thời hạn hiệu lực tới ngày 31/03/"+currentYear+".";
				 }
			}else{
				 if(currentYear.equals(appYearOnly) ){
					 res = "Thời hạn hiệu lực tới ngày 31/03/"+(Integer.parseInt(currentYear)+1)+".";
				 }else if(currentYear.equals((Integer.parseInt(maxYear)+1)+"")){
					 res = "Tài khoản chưa đóng phí duy trì năm "+currentYear+". Thời hạn hiệu lực tới ngày 31/03/"+currentYear+".";
				 }else{
					 if(currentYear.equals((Integer.parseInt(appYearOnly)+1)+"")){
						 res = "Tài khoản chưa đóng phí duy trì năm "+currentYear+". Thời hạn hiệu lực tới ngày 31/03/"+currentYear+".";
					 }else{
						 res = "Tài khoản chưa đóng phí duy trì năm "+(Integer.parseInt(appYearOnly)+1)+","+currentYear+". Thời hạn hiệu lực tới ngày 31/03/"+currentYear+".";
					 }
					 
				 }
			}
		 
		 }else if(!"1".equals(bidDangKy) && !"2".equals(bidDuyTri)){
			 if(((getDateFromString(appYear).before(getDateFromString("31/12/2015")))|| (getDateFromString(appYear).equals(getDateFromString("31/12/2015")))) && (getDateFromString(appYear).after(getDateFromString("01/11/2015"))||(getDateFromString(appYear).equals(getDateFromString("01/11/2015"))) ) ){
					 res = "Tài khoản chưa đóng phí đăng ký năm  2016, phí duy trì "+currentYear+". Thời hạn hiệu lực tới ngày 31/03/"+currentYear+".";
			 }else
			 if(Integer.parseInt(appYearOnly) < Integer.parseInt("2016")){
				 res = "Tài khoản chưa đóng phí duy trì năm từ 2016, "+currentYear+". Thời hạn hiệu lực tới ngày 31/03/"+currentYear+".";
			 }else{
			 if(Integer.parseInt(appYearOnly) == Integer.parseInt(currentYear)){
				 res = "Tài khoản chưa đóng phí đăng ký năm "+appYearOnly+".";
			 }else if(Integer.parseInt(currentYear) == (Integer.parseInt(appYearOnly)+1)){
					 res = "Tài khoản chưa đóng phí đăng ký năm "+appYearOnly+". Tài khoản chưa đóng phí duy trì "+(Integer.parseInt(appYearOnly)+1)+". Thời hạn có hiệu lực đến ngày 31/03/"+(Integer.parseInt(appYearOnly)+1)+".";
				 }else{
					 res = "Tài khoản chưa đóng phí đăng ký năm "+appYear+". Tài khoản chưa đóng phí duy trì "+Integer.parseInt(appYearOnly)+","+currentYear+". Thời hạn có hiệu lực đến ngày 31/03/"+currentYear+".";
				 }
			 }
		 }
		 
		 return res;
	 }
	 
	 public String getYearFormDateTime(String dateTime){
		 String year ="";
		 if(dateTime!= null && dateTime.length()>0){
			 year = dateTime.trim().substring(6, 10);
		 }
		 return year;
	 }
	 
	 public String getCurrentYear(){
		 SimpleDateFormat fm = new SimpleDateFormat("yyyy");
		 return fm.format(new Date());
	 }
	 
	 public Date getDateFromString(String strDate){
		 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		 Log.debug(">>>>>>>>>>>>>strDate:"+strDate);
	        try {

	           return  formatter.parse(strDate);

	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return null;
	 }
	 
}
