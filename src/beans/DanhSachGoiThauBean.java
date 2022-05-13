package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import common.Log;
import common.Trx;
import entity.BidPackage;

public class DanhSachGoiThauBean {
	public BidPackage[] searchDanhSachGoiThau(int pageNum, int pageMAX,String soDKKD, String sdate,String edate)
	  {
		Calendar today=Calendar.getInstance();
		today.setTime(new Date());
		if(((sdate == null) || (sdate.length() == 0)) && ((edate == null) || (edate.length() == 0))){
			sdate=today.getActualMinimum(Calendar.DAY_OF_MONTH)+"/"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.YEAR);
			//01/04/2015
			edate=today.get(Calendar.DAY_OF_MONTH)+"/"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.YEAR);
		}
		int start = (pageNum - 1) * pageMAX + 1;
		int end = pageNum * pageMAX;
		Log.debug("dinhnv4: searchDanhSachGoiThau(int pageNum, int pageMAX,String soDKKD, String sdate,String edate)");
	    Connection con = null;
	    Trx trx = null;
	    ResultSet rs = null;
	    PreparedStatement pstm = null;
	    Vector vec = new Vector();
	    BidPackage[] ettlist = (BidPackage[])null;
	    try
	    {
	      String sql = "";
	      if("".equals(soDKKD)){
	      sql="select g.CD_NM,a.BID_NO || '-' || a.BID_TURN_NO, c.INSTITU_FULL_NM, b.BID_NM, to_char(b.BID_OPEN_DT, 'dd/MM/yyyy hh:mm'), to_char(b.PUBLIC_DT, 'dd/MM/yyyy hh:mm'),d.biz_nm, CASE WHEN to_char(ROWNUM) = '0' THEN '' else to_char(ROWNUM) END AS NUM,a.biz_reg_no,rownum N, "
	      	+ " umrm.PHONE_NO, umrr.REPR_MOBILE, umrr.REPR_NM, umrr.REPR_EMAIL , uma.NM, uma.MOBILE, uma.EMAIL " //them moi thong tin
			+" from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_PUB_INSTITU_MAST c, UM_SUPPLIER_ENTER_MAST d, SYN_PUB_CODE g "
	    	+ " , UM_REC_SUPPLIER_ENTER_MAST umrm , UM_REC_REPR umrr, UM_BID_AGENT uma "	// them moi bang
			+ " where "
	    	+ " uma.BIZ_REG_NO = umrm.BIZ_REG_NO and umrm.BIZ_REG_NO = umrr.BIZ_REG_NO and umrr.REPR_ISMAIN = 'Y' and umrm.BIZ_REG_NO = a.BIZ_REG_NO and " //them moi dieu kien
			+" c.HIDDEN_YN = 'N' and "
			+"c.DELETE_YN = 'N' and "
			+" ( d.TEST_OPTION_YN is null OR d.TEST_OPTION_YN = 'N') and "
			+"d.HIDDEN_YN = 'N' and "
			+"b.PUBLIC_YN = 'Y' and "
			+"a.BID_NO = b.BID_NO and "
			+"a.BID_TURN_NO = b.BID_TURN_NO and "
			+"b.REG_YN = 'Y' and "
//			+"b.BID_NO = m.BID_NO and "
//			+"b.BID_TURN_NO = m.BID_TURN_NO and "
			+"b.ORDER_INSTITU_CD = c.INSTITU_CD and "
			+"a.BIZ_REG_NO = d.BIZ_REG_NO and "
		    +"b.SUCC_BID_METHOD=g.cd and "
		    +"'Z99'=g.cd_cls and ";
	      	if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "b.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + "b.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "b.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        }
			sql=sql+"a.BIZ_REG_NO in ("
			+"select BIZ_REG_NO from "
			+"(select distinct a.BIZ_REG_NO, c.BIZ_NM, c.ADDR, c.PHONE_NO,"
			+"(select count(*) from SYN_XML_RECV x, SYN_BID_BID_MASTER y, UM_SUPPLIER_ENTER_MAST z where "
			+"z.HIDDEN_YN = 'N' and "
			+" ( z.TEST_OPTION_YN is null OR z.TEST_OPTION_YN = 'N') and "
			+"y.PUBLIC_YN = 'Y' and "
			+"x.BID_NO = y.BID_NO and "
			+"x.BID_TURN_NO = y.BID_TURN_NO and "
			+"x.BIZ_REG_NO = z.BIZ_REG_NO and "
			+"y.REG_YN = 'Y' and ";
//			+"y.BID_NO = m.BID_NO and "
//			+"y.BID_TURN_NO = m.BID_TURN_NO and "
//			+"m.EXEC_TYPE is not null and ";
	      	if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "y.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + "y.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "y.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        }
			sql=sql	+"x.BIZ_REG_NO = a.BIZ_REG_NO) "
					+"count1, "
					+"(select count(distinct t1.BIZ_REG_NO) from SYN_XML_RECV t1, SYN_BID_BID_MASTER t2, UM_SUPPLIER_ENTER_MAST t3 where "
					+"	t3.HIDDEN_YN = 'N' and "
					+" ( t3.TEST_OPTION_YN is null OR t3.TEST_OPTION_YN = 'N') and "
					+"t2.PUBLIC_YN = 'Y' and "
					+"	t1.BID_NO = t2.BID_NO and "
					+"t1.BID_TURN_NO = t2.BID_TURN_NO and "
					+"t1.BIZ_REG_NO = t3.BIZ_REG_NO and "
					+"t2.REG_YN = 'Y' and ";
//					+"t2.BID_NO = t4.BID_NO and "
//					+"t2.BID_TURN_NO = t4.BID_TURN_NO and "
//					+"t4.EXEC_TYPE is not null and ";
					if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
			          sql = sql + "t2.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')) ";
			        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
			          sql = sql + "t2.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS')) ";
			        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
			          sql = sql + "t2.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')) ";
			        }
					sql=sql+"count2 from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_SUPPLIER_ENTER_MAST c where "
					+"c.HIDDEN_YN = 'N' and "
			+" ( c.TEST_OPTION_YN is null OR c.TEST_OPTION_YN = 'N') and "
			+"b.PUBLIC_YN = 'Y' and "
			+"a.BID_NO = b.BID_NO and "
			+"a.BID_TURN_NO = b.BID_TURN_NO and "
			+"a.BIZ_REG_NO = c.BIZ_REG_NO and "
			+"b.REG_YN = 'Y' ";
//			+"b.BID_NO = d.BID_NO and "
//			+"b.BID_TURN_NO = d.BID_TURN_NO and "
//			+"d.EXEC_TYPE is not null ";
			if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + "and b.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')))";
		        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
		          sql = sql + "and b.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS')))";
		        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + "and b.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')))";
		    }

	      }else{
	    	  sql="select g.CD_NM,a.BID_NO || '-' || a.BID_TURN_NO, c.INSTITU_FULL_NM, b.BID_NM, to_char(b.BID_OPEN_DT, 'dd/MM/yyyy hh:mm'), to_char(b.PUBLIC_DT, 'dd/MM/yyyy hh:mm'),d.biz_nm, CASE WHEN to_char(ROWNUM) = '0' THEN '' else to_char(ROWNUM) END AS NUM,a.biz_reg_no,rownum N "
	    				+"from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_PUB_INSTITU_MAST c, UM_SUPPLIER_ENTER_MAST d"
	    				//+ ", SYN_BID_STATUS m" - ANHH9 2016/05/11 bo khong check dieu kien trong bang nay
	    				+ ",SYN_PUB_CODE g where "
	    				+"c.HIDDEN_YN = 'N' and "
	    				+"c.DELETE_YN = 'N' and "
	    				+" ( d.TEST_OPTION_YN is null OR d.TEST_OPTION_YN = 'N') and "
	    				+"d.HIDDEN_YN = 'N' and "
	    				+"b.PUBLIC_YN = 'Y' and "
	    				+"a.BID_NO = b.BID_NO and "
	    				+"a.BID_TURN_NO = b.BID_TURN_NO and "
	    				+"b.REG_YN = 'Y' and "
//	    				+"b.BID_NO = m.BID_NO and "
//	    				+"b.BID_TURN_NO = m.BID_TURN_NO and "
	    				+"b.ORDER_INSTITU_CD = c.INSTITU_CD and "
	    				+"a.BIZ_REG_NO = d.BIZ_REG_NO and "
	    			    +"b.SUCC_BID_METHOD=g.cd and "
	    			    +"'Z99'=g.cd_cls and ";
				    	if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
					          sql = sql + "b.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
					        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
					          sql = sql + "b.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and ";
					        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
					          sql = sql + "b.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
					        }
	    				sql=sql+"a.BIZ_REG_NO in ('"+soDKKD+"')";
	      }
	      
	      sql = " select * from (select rownum  ROWNUM_T3, t3.* from (" + sql + " ORDER BY a.biz_reg_no, a.BID_NO ) t3 ) where ROWNUM_T3 between " + start + " and " + end;
	      Log.debug("dinhnv4=sql--" + sql);
	      trx = new Trx(this, "usemn");
	      con = trx.getConnection();
	      pstm = con.prepareStatement(sql);
	      
	      rs = pstm.executeQuery();
	      pstm.clearParameters();
	      while (rs.next())
	      {
		        BidPackage ett = new BidPackage();
		        String bidMethod="";
		        if(rs.getString(1)!=null){
		        	bidMethod=rs.getString(2);
		        }
		        ett.setBidMethod(bidMethod);
		        ett.setBidNo(rs.getString(3));
		        ett.setInstituFullNm(rs.getString(4));
		        ett.setBidNm(rs.getString(5));
		        ett.setBidOpen(rs.getString(6));
		        ett.setBidPublic(rs.getString(7));
		        ett.setBizNm(rs.getString(8));
		        ett.setNum(rs.getString(9));
		        ett.setMst(rs.getString(10));
		        ett.setStt(rs.getInt("ROWNUM_T3"));
		        vec.addElement(ett);
	        }
	    }
	    catch (SQLException sqle)
	    {
	      Log.debug(
	        "DanhSachGoiThauBean.searchDanhSachGoiThau() SQLException : " + sqle.toString());
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
	        "DanhSachGoiThauBean.searchDanhSachGoiThau() " + exc.toString());
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
	      ettlist = new BidPackage[vec.size()];
	      vec.copyInto(ettlist);
	      Log.debug("dinhnv4  SIZEEEEEEEEEEEEEEE: "+ettlist.length);
	      return ettlist;
	    }
	    return null;
	  }
	public BidPackage[] countGoiListGoiThau(String soDKKD, String sdate,String edate)
	  {
		Calendar today=Calendar.getInstance();
		today.setTime(new Date());
		if(((sdate == null) || (sdate.length() == 0)) && ((edate == null) || (edate.length() == 0))){
			sdate=today.getActualMinimum(Calendar.DAY_OF_MONTH)+"/"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.YEAR);
			//01/04/2015
			edate=today.get(Calendar.DAY_OF_MONTH)+"/"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.YEAR);
		}
		Log.debug("dinhnv4: countGoiListGoiThau(String soDKKD, String sdate,String edate)");
	    Connection con = null;
	    Trx trx = null;
	    ResultSet rs = null;
	    PreparedStatement pstm = null;
	    Vector vec = new Vector();
	    BidPackage[] ettlist = (BidPackage[])null;
	    try
	    {
	      String sql = "";
	      if("".equals(soDKKD)){
	      sql="select g.CD_NM,a.BID_NO || '-' || a.BID_TURN_NO, c.INSTITU_FULL_NM, b.BID_NM, to_char(b.BID_OPEN_DT, 'dd/MM/yyyy hh:mm'), to_char(b.PUBLIC_DT, 'dd/MM/yyyy hh:mm'),d.biz_nm, CASE WHEN to_char(ROWNUM) = '0' THEN '' else to_char(ROWNUM) END AS NUM,a.biz_reg_no "
			+"from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_PUB_INSTITU_MAST c, UM_SUPPLIER_ENTER_MAST d"
			//+ ", SYN_BID_STATUS m" - ANHH9 2016/05/11 bo khong check dieu kien trong bang nay
			+ ",SYN_PUB_CODE g where "
			+"c.HIDDEN_YN = 'N' and "
			+"c.DELETE_YN = 'N' and "
			+"d.TEST_OPTION_YN is null and "
			+"d.HIDDEN_YN = 'N' and "
			+"b.PUBLIC_YN = 'Y' and "
			+"a.BID_NO = b.BID_NO and "
			+"a.BID_TURN_NO = b.BID_TURN_NO and "
			+"b.REG_YN = 'Y' and "
//			+"b.BID_NO = m.BID_NO and "
//			+"b.BID_TURN_NO = m.BID_TURN_NO and "
			+"b.ORDER_INSTITU_CD = c.INSTITU_CD and "
			+"a.BIZ_REG_NO = d.BIZ_REG_NO and "
			+"b.SUCC_BID_METHOD=g.cd and "
			+"'Z99'=g.cd_cls and ";
	      	if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "b.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + "b.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "b.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        }
			sql=sql+"a.BIZ_REG_NO in ("
			+"select BIZ_REG_NO from "
			+"(select distinct a.BIZ_REG_NO, c.BIZ_NM, c.ADDR, c.PHONE_NO,"
			+"(select count(*) from SYN_XML_RECV x, SYN_BID_BID_MASTER y, UM_SUPPLIER_ENTER_MAST z, SYN_BID_STATUS m where "
			+"z.HIDDEN_YN = 'N' and "
			+"z.TEST_OPTION_YN is null and "
			+"y.PUBLIC_YN = 'Y' and "
			+"x.BID_NO = y.BID_NO and "
			+"x.BID_TURN_NO = y.BID_TURN_NO and "
			+"x.BIZ_REG_NO = z.BIZ_REG_NO and "
			+"y.REG_YN = 'Y' and ";
//			+"y.BID_NO = m.BID_NO and "
//			+"y.BID_TURN_NO = m.BID_TURN_NO and "
//			+"m.EXEC_TYPE is not null and ";
	      	if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "y.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + "y.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "y.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        }
	      
//			+"y.BID_OPEN_DT between to_date('01/01/2016 00:00:00', 'DD/MM/YYYY HH24:MI:SS') and "
//					+"to_date('30/03/2016 23:59:59', 'DD/MM/YYYY HH24:MI:SS') and "
			sql=sql	+"x.BIZ_REG_NO = a.BIZ_REG_NO) "
					+"count1, "
					+"(select count(distinct t1.BIZ_REG_NO) from SYN_XML_RECV t1, SYN_BID_BID_MASTER t2, UM_SUPPLIER_ENTER_MAST t3, SYN_BID_STATUS t4 where "
					+"	t3.HIDDEN_YN = 'N' and "
					+"t3.TEST_OPTION_YN is null and "
					+"t2.PUBLIC_YN = 'Y' and "
					+"	t1.BID_NO = t2.BID_NO and "
					+"t1.BID_TURN_NO = t2.BID_TURN_NO and "
					+"t1.BIZ_REG_NO = t3.BIZ_REG_NO and "
					+"t2.REG_YN = 'Y' and ";
//					+"t2.BID_NO = t4.BID_NO and "
//					+"t2.BID_TURN_NO = t4.BID_TURN_NO and "
//					+"t4.EXEC_TYPE is not null and ";
					if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
			          sql = sql + "t2.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')) ";
			        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
			          sql = sql + "t2.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS')) ";
			        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
			          sql = sql + "t2.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')) ";
			        }
//					+"t2.BID_OPEN_DT between to_date('01/01/2016 00:00:00', 'DD/MM/YYYY HH24:MI:SS') and "
//					+"to_date('30/03/2016 23:59:59', 'DD/MM/YYYY HH24:MI:SS')) "
					sql=sql+"count2 from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_SUPPLIER_ENTER_MAST c, SYN_BID_STATUS d where "
					+"c.HIDDEN_YN = 'N' and "
			+"c.TEST_OPTION_YN is null and "
			+"b.PUBLIC_YN = 'Y' and "
			+"a.BID_NO = b.BID_NO and "
			+"a.BID_TURN_NO = b.BID_TURN_NO and "
			+"a.BIZ_REG_NO = c.BIZ_REG_NO and "
			+"b.REG_YN = 'Y' ";
//			+"b.BID_NO = d.BID_NO and "
//			+"b.BID_TURN_NO = d.BID_TURN_NO and "
//			+"d.EXEC_TYPE is not null ";
			if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + "and b.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')))";
		        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
		          sql = sql + "and b.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS')))";
		        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + "and b.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')))";
		    }
	      }else{
	    	  sql="select g.CD_NM,b.BID_METHOD,a.BID_NO || '-' || a.BID_TURN_NO, c.INSTITU_FULL_NM, b.BID_NM, to_char(b.BID_OPEN_DT, 'dd/MM/yyyy hh:mm'), to_char(b.PUBLIC_DT, 'dd/MM/yyyy hh:mm'),d.biz_nm, CASE WHEN to_char(ROWNUM) = '0' THEN '' else to_char(ROWNUM) END AS NUM,a.biz_reg_no "
	    				+"from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_PUB_INSTITU_MAST c, UM_SUPPLIER_ENTER_MAST d, SYN_BID_STATUS m,SYN_PUB_CODE g where "
	    				+"c.HIDDEN_YN = 'N' and "
	    				+"c.DELETE_YN = 'N' and "
	    				+"d.TEST_OPTION_YN is null and "
	    				+"d.HIDDEN_YN = 'N' and "
	    				+"b.PUBLIC_YN = 'Y' and "
	    				+"a.BID_NO = b.BID_NO and "
	    				+"a.BID_TURN_NO = b.BID_TURN_NO and "
	    				+"b.REG_YN = 'Y' and "
//	    				+"b.BID_NO = m.BID_NO and "
//	    				+"b.BID_TURN_NO = m.BID_TURN_NO and "
	    				+"b.ORDER_INSTITU_CD = c.INSTITU_CD and "
	    				+"a.BIZ_REG_NO = d.BIZ_REG_NO and "
	    				+"b.SUCC_BID_METHOD=g.cd and "
	    				+"'Z99'=g.cd_cls and ";
				    	  if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
					          sql = sql + "b.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
					        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
					          sql = sql + "b.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and ";
					        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
					          sql = sql + "b.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
					        }
	    				sql=sql+"a.BIZ_REG_NO in ('"+soDKKD+"')";
	      }
	      Log.debug("dinhnv4=sql:   " + sql);
	      trx = new Trx(this, "usemn");
	      con = trx.getConnection();
	      pstm = con.prepareStatement(sql);
	      
	      rs = pstm.executeQuery();
	      pstm.clearParameters();
	      while (rs.next())
	      {
		        BidPackage ett = new BidPackage();
		        String bidMethod="";
		        if(rs.getString(1)!=null){
		        	bidMethod=rs.getString(1);
		        }
		        if("00".equals(bidMethod)){
		        	ett.setBidMethod("Trực tiếp");
		        }else{
		        	ett.setBidMethod("Điện tử");
		        }
		        ett.setBidNo(rs.getString(2));
		        ett.setInstituFullNm(rs.getString(3));
		        ett.setBidNm(rs.getString(4));
		        ett.setBidOpen(rs.getString(5));
		        ett.setBidPublic(rs.getString(6));
		        ett.setBizNm(rs.getString(7));
		        ett.setNum(rs.getString(8));
		        ett.setMst(rs.getString(9));
		        vec.addElement(ett);
	        }
	    }
	    catch (SQLException sqle)
	    {
	      Log.debug(
	        "DanhSachGoiThauBean.countGoiListGoiThau(String soDKKD, String sdate,String edate) SQLException : " + sqle.toString());
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
	        "DanhSachGoiThauBean.countGoiListGoiThau(String soDKKD, String sdate,String edate) " + exc.toString());
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
	      ettlist = new BidPackage[vec.size()];
	      vec.copyInto(ettlist);
	      
	      return ettlist;
	    }
	    return null;
	  }
	public int countGoiGoiThau(String soDKKD, String sdate,String edate)
	  {
		int count=0;
		Calendar today=Calendar.getInstance();
		today.setTime(new Date());
		if(((sdate == null) || (sdate.length() == 0)) && ((edate == null) || (edate.length() == 0))){
			sdate=today.getActualMinimum(Calendar.DAY_OF_MONTH)+"/"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.YEAR);
			//01/04/2015
			edate=today.get(Calendar.DAY_OF_MONTH)+"/"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.YEAR);
		}
		Log.debug("dinhnv4:  countGoiGoiThau(String soDKKD, String sdate,String edate)");
	    Connection con = null;
	    Trx trx = null;
	    ResultSet rs = null;
	    PreparedStatement pstm = null;
	    try
	    {
	      String sql = "";
	      if("".equals(soDKKD)){
	      sql="select g.CD_NM,a.BID_NO || '-' || a.BID_TURN_NO, c.INSTITU_FULL_NM, b.BID_NM, to_char(b.BID_OPEN_DT, 'dd/MM/yyyy hh:mm'), to_char(b.PUBLIC_DT, 'dd/MM/yyyy hh:mm'),d.biz_nm, CASE WHEN to_char(ROWNUM) = '0' THEN '' else to_char(ROWNUM) END AS NUM,a.biz_reg_no,rownum N "
			+" from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_PUB_INSTITU_MAST c, UM_SUPPLIER_ENTER_MAST d, SYN_PUB_CODE g where "
			+"c.HIDDEN_YN = 'N' and "
			+"c.DELETE_YN = 'N' and "
			//+"d.TEST_OPTION_YN is null and "
			+" ( d.TEST_OPTION_YN is null OR d.TEST_OPTION_YN = 'N') and "
			+"d.HIDDEN_YN = 'N' and "
			+"b.PUBLIC_YN = 'Y' and "
			+"a.BID_NO = b.BID_NO and "
			+"a.BID_TURN_NO = b.BID_TURN_NO and "
			+"b.REG_YN = 'Y' and "
//			+"b.BID_NO = m.BID_NO and "
//			+"b.BID_TURN_NO = m.BID_TURN_NO and "
			+"b.ORDER_INSTITU_CD = c.INSTITU_CD and "
			+"a.BIZ_REG_NO = d.BIZ_REG_NO and "
		    +"b.SUCC_BID_METHOD=g.cd and "
		    +"'Z99'=g.cd_cls and ";
	      	if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "b.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + "b.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "b.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        }
			sql=sql+"a.BIZ_REG_NO in ("
			+"select BIZ_REG_NO from "
			+"(select distinct a.BIZ_REG_NO, c.BIZ_NM, c.ADDR, c.PHONE_NO,"
			+"(select count(*) from SYN_XML_RECV x, SYN_BID_BID_MASTER y, UM_SUPPLIER_ENTER_MAST z where "
			+"z.HIDDEN_YN = 'N' and "
			//+"z.TEST_OPTION_YN is null and "
			+" ( z.TEST_OPTION_YN is null OR z.TEST_OPTION_YN = 'N') and "
			+"y.PUBLIC_YN = 'Y' and "
			+"x.BID_NO = y.BID_NO and "
			+"x.BID_TURN_NO = y.BID_TURN_NO and "
			+"x.BIZ_REG_NO = z.BIZ_REG_NO and "
			+"y.REG_YN = 'Y' and ";
//			+"y.BID_NO = m.BID_NO and "
//			+"y.BID_TURN_NO = m.BID_TURN_NO and "
//			+"m.EXEC_TYPE is not null and ";
	      	if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "y.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + "y.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and ";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "y.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
	        }
			sql=sql	+"x.BIZ_REG_NO = a.BIZ_REG_NO) "
					+"count1, "
					+"(select count(distinct t1.BIZ_REG_NO) from SYN_XML_RECV t1, SYN_BID_BID_MASTER t2, UM_SUPPLIER_ENTER_MAST t3 where "
					+"	t3.HIDDEN_YN = 'N' and "
					//+"t3.TEST_OPTION_YN is null and "
					+" ( t3.TEST_OPTION_YN is null OR t3.TEST_OPTION_YN = 'N') and "
					+"t2.PUBLIC_YN = 'Y' and "
					+"	t1.BID_NO = t2.BID_NO and "
					+"t1.BID_TURN_NO = t2.BID_TURN_NO and "
					+"t1.BIZ_REG_NO = t3.BIZ_REG_NO and "
					+"t2.REG_YN = 'Y' and ";
//					+"t2.BID_NO = t4.BID_NO and "
//					+"t2.BID_TURN_NO = t4.BID_TURN_NO and "
//					+"t4.EXEC_TYPE is not null and ";
					if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
			          sql = sql + "t2.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')) ";
			        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
			          sql = sql + "t2.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS')) ";
			        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
			          sql = sql + "t2.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')) ";
			        }
					sql=sql+"count2 from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_SUPPLIER_ENTER_MAST c where "
					+"c.HIDDEN_YN = 'N' and "
			//+"c.TEST_OPTION_YN is null and "
			+" ( c.TEST_OPTION_YN is null OR c.TEST_OPTION_YN = 'N') and "
			+"b.PUBLIC_YN = 'Y' and "
			+"a.BID_NO = b.BID_NO and "
			+"a.BID_TURN_NO = b.BID_TURN_NO and "
			+"a.BIZ_REG_NO = c.BIZ_REG_NO and "
			+"b.REG_YN = 'Y' ";
//			+"b.BID_NO = d.BID_NO and "
//			+"b.BID_TURN_NO = d.BID_TURN_NO and "
//			+"d.EXEC_TYPE is not null ";
			if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + "and b.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')))";
		        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
		          sql = sql + "and b.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS')))";
		        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + "and b.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')))";
		    }
	      }else{
	    	  sql="select g.CD_NM,a.BID_NO || '-' || a.BID_TURN_NO, c.INSTITU_FULL_NM, b.BID_NM, to_char(b.BID_OPEN_DT, 'dd/MM/yyyy hh:mm'), to_char(b.PUBLIC_DT, 'dd/MM/yyyy hh:mm'),d.biz_nm, CASE WHEN to_char(ROWNUM) = '0' THEN '' else to_char(ROWNUM) END AS NUM,a.biz_reg_no,rownum N "
	    				+"from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_PUB_INSTITU_MAST c, UM_SUPPLIER_ENTER_MAST d"
	    				//+ ", SYN_BID_STATUS m" - ANHH9 2016/05/11 bo khong check dieu kien trong bang nay
	    				+ ",SYN_PUB_CODE g where "
	    				+"c.HIDDEN_YN = 'N' and "
	    				+"c.DELETE_YN = 'N' and "
	    				//+"d.TEST_OPTION_YN is null and "
	    				+" ( d.TEST_OPTION_YN is null OR d.TEST_OPTION_YN = 'N') and "
	    				+"d.HIDDEN_YN = 'N' and "
	    				+"b.PUBLIC_YN = 'Y' and "
	    				+"a.BID_NO = b.BID_NO and "
	    				+"a.BID_TURN_NO = b.BID_TURN_NO and "
	    				+"b.REG_YN = 'Y' and "
//	    				+"b.BID_NO = m.BID_NO and "
//	    				+"b.BID_TURN_NO = m.BID_TURN_NO and "
	    				+"b.ORDER_INSTITU_CD = c.INSTITU_CD and "
	    				+"a.BIZ_REG_NO = d.BIZ_REG_NO and "
	    			    +"b.SUCC_BID_METHOD=g.cd and "
	    			    +"'Z99'=g.cd_cls and ";
				    	if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
					          sql = sql + "b.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
					        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
					          sql = sql + "b.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and ";
					        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
					          sql = sql + "b.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
					        }
	    				sql=sql+"a.BIZ_REG_NO in ('"+soDKKD+"')";
	      }
	      
	      sql = "select count(*) from (" + sql +" ORDER BY b.BID_OPEN_DT)";
	      Log.debug("dinhnv4=sql--" + sql);
	      trx = new Trx(this, "usemn");
	      con = trx.getConnection();
	      pstm = con.prepareStatement(sql);
	      
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
	        "DanhSachGoiThauBean.countGoiGoiThau(String soDKKD, String sdate,String edate) SQLException : " + sqle.toString());
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
	        "DanhSachGoiThauBean.countGoiGoiThau(String soDKKD, String sdate,String edate) " + exc.toString());
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
	public int countSize(String soDKKD, String sdate,String edate)
	  {
		Log.debug("DanhSachGoiThauBeans.countSize(String soDKKD, String sdate,String edate)");
		int count=0;
//		if(countGoiListGoiThau(soDKKD, sdate, edate)!=null){
//			count=countGoiListGoiThau(soDKKD, sdate, edate).length;
//		}
		count=countGoiGoiThau(soDKKD, sdate, edate);
		Log.debug("count: "+ count);
	    return count;
	  }
	
	public Date convertStringToDate(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public Date convertStringToDate2(String dateInString) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
