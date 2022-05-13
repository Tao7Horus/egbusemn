package beans;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Log;
import common.Trx;
import entity.BidPackage;
import entity.Bidder;

public class DayReportExport extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) {
		try {
			req.setCharacterEncoding("utf-8");
			Calendar cal=Calendar.getInstance();
			cal.setTime(new Date());
			String sdate = htmlEncode(req.getParameter("sdate"));
			String edate = htmlEncode(req.getParameter("edate"));
			String soDKKD = htmlEncode(req.getParameter("soDKKD"));
			Log.debug("DayReportExport.service()>>> " + sdate);
			Log.debug("DayReportExport.service()>>> " + edate);
			Log.debug("DayReportExport.service()>>> " + soDKKD);

		//	List ettlist = listResult(sdate, edate, soDKKD);
			BidPackage[] ettArray= null;
			List ettlist=new ArrayList();
			ettArray=listDanhSachGoiThau(soDKKD, sdate, edate);
			for (int i = 0; i < ettArray.length; i++) {
				ettlist.add((BidPackage) ettArray[i]);
			}
			Log.debug("DayReportExport.service() ettlist>>> " + ettlist.size());
			res.setContentType("application/vnd.ms-excel; charset=utf-8");
			String dateStr=cal.get(Calendar.DAY_OF_MONTH)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
			res.setHeader("Content-Disposition", "attachment; filename=DSgoithau_"+dateStr+".xls");
			PrintWriter out = res.getWriter();
			headHtml(out);
			infoHtml(out, sdate, edate, soDKKD);
			dispResultSet(ettlist, out);
			endHtml(out);
		} catch (Exception e) {
			Log.errors(this, e, "DayReportExport.service() Exception block");
		}
	}

	private void headHtml(PrintWriter out) {
		String title = "Danh sách các gói thầu mà từng nhà thầu tham dự theo ngày";

		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">");
		out.println("<title>Tổng hợp thông tin nhà thầu </title>");
		out.println("</head>");
		out.println("<body bgcolor=white text=black>");
		out.println(
				"<table\tx:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width='80%' align='center'>");
		out.println("<tr style='padding-top:4.0pt'>");
		out.println(
				"  <td bgcolor='#EDEDED' height='45' style='border:.5pt\tsolid windowtext;verticla-align:center;' colspan=7>");
		out.println("    <p align='center'><font size='4' face='Arial'><b>" + title + "</b></font></p></td>");
		out.println("</tr>");

		out.println("</table>");
		out.println("<br>");
		out.println("<br>");
	}

	private void infoHtml(PrintWriter out, String dateForm, String dateTo, String soDKKD) {
		out.println("<table\tx:str border=1 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' align=center width=60%>");

		out.println("<tr>");
		out.println("<td>");
		out.println("Thời gian ");
		out.println("</td>");
		out.println("<td>");
		out.println(dateForm + " - " + dateTo);
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>");
		out.println("Số ĐKKD");
		out.println("</td>");
		out.println("<td>");
		out.println(soDKKD);
		out.println("</td>");
		out.println("</tr>");
		
		
		
		out.println("</table>");

		out.println("<br>");
		out.println("<br>");
	}

	private void dispResultSet(List listpm, PrintWriter out) throws Exception {
		out.println(
				"<table\tx:str border=1 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' align=center width=95%>");

		out.println("  <tr>");
		out.println("\t <td bgcolor='#EDEDED' ><b>STT</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>MST</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Số TBMT</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Bên mời thầu</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Tên gói thầu</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Thời điểm mở thầu</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Thời điểm đăng tải</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Tên nhà thầu</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Số gói thầu</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Hình thức</b></td>");
		
		out.println("\t <td bgcolor='#EDEDED' ><b>Số điện thoại bàn</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Tên lãnh đạo</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Số đt lãnh đạo</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Email lãnh đạo</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Tên người đại diện dự thầu</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Số đt người đại diện dự thầu</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Email người đại diện dự thầu</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Thanh toán phí tham dự thầu</b></td>");
		
		out.println("  </tr>");
		
		int count=listpm.size();
		for (int i = 0; i < listpm.size(); i++) {
			BidPackage bidPackage = new BidPackage();
    		bidPackage=(BidPackage) listpm.get(i);
			out.println("  <tr>");
			out.println("\t <td width=5%>" + (i + 1) + "</td>");
			out.println("    <td>&nbsp;" + isNull(bidPackage.getMst()) + "</td>");
			out.println("    <td align=left>&nbsp;" + isNull(bidPackage.getBidNo()) + "</td>");
			out.println("    <td>&nbsp;" + isNull(bidPackage.getInstituFullNm()) + "</td>");
			out.println("    <td align=left>&nbsp;" + isNull(bidPackage.getBidNm()) + "</td>");
			out.println("    <td>" + isNull(bidPackage.getBidOpen()) + "</td>");
			out.println("    <td>" + isNull(bidPackage.getBidPublic()) + "</td>");
			out.println("    <td>" + isNull(bidPackage.getBizNm()) + "</td>");
			out.println("    <td>" + isNull(bidPackage.getNum()) + "</td>");
			out.println("    <td>" + isNull(bidPackage.getBidMethod()) + "</td>");
			
			out.println("    <td>" + isNull(bidPackage.getPhoneNo()) + "</td>");
			out.println("    <td>" + isNull(bidPackage.getReprNM()) + "</td>");
			out.println("    <td>" + isNull(bidPackage.getReprMobile()) + "</td>");
			out.println("    <td>" + isNull(bidPackage.getReprEmail()) + "</td>");
			out.println("    <td>" + isNull(bidPackage.getNm()) + "</td>");
			out.println("    <td>" + isNull(bidPackage.getMobile()) + "</td>");
			out.println("    <td>" + isNull(bidPackage.getEmail()) + "</td>");
			out.println("    <td>" + ("Y".equals(bidPackage.getPaymentStatus()) ? "Đã thanh toán" : "Chưa thanh toán") + "</td>");
			out.println("  </tr>");
		}
		if (count == 0) {
			out.println("  <tr>");
			out.println("  <td>Không có thông tin.</td>");
			out.println("  </tr>");
		}
		out.println("</table>");
	}

	private void endHtml(PrintWriter out) {
		out.println("</body>");
		out.println("</html>");
	}

	private String isNull(String str) {
		if (str == null) {
			str = " ";
		}
		return str;
	}

	private String htmlEncode(String s) {
		if ((s == null) || (s.equals(""))) {
			return s;
		}
		StringBuffer b = new StringBuffer(s.length());

		String specialCharacters = "#&\"'<>";
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (specialCharacters.indexOf(ch) > -1) {
				b.append("&#").append(ch).append(";");
			} else {
				b.append(ch);
			}
		}
		return b.toString();
	}
	public BidPackage[] listDanhSachGoiThau(String soDKKD, String sdate,String edate)
	  {
		Calendar today=Calendar.getInstance();
		today.setTime(new Date());
		if(((sdate == null) || (sdate.length() == 0)) && ((edate == null) || (edate.length() == 0))){
			sdate=today.getActualMinimum(Calendar.DAY_OF_MONTH)+"/"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.YEAR);
			//01/04/2015
			edate=today.get(Calendar.DAY_OF_MONTH)+"/"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.YEAR);
		}
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
	    	  //ThachPN 20170222 - them trang thai thanh toan
	    	  sql = "select k.*, p.PM_FINISH_YN from (";
	    	  //End - trang thai thanh toan
	    	  
	      sql=sql + "select g.CD_NM,a.BID_NO || '-' || a.BID_TURN_NO, c.INSTITU_FULL_NM, b.BID_NM, to_char(b.BID_OPEN_DT, 'dd/MM/yyyy hh:mm'), to_char(b.PUBLIC_DT, 'dd/MM/yyyy hh:mm'),d.biz_nm, CASE WHEN to_char(ROWNUM) = '0' THEN '' else to_char(ROWNUM) END AS NUM,a.biz_reg_no,rownum N "
	      	+ ", d.PHONE_NO, umrr.REPR_MOBILE, umrr.REPR_NM, umrr.REPR_EMAIL , uma.NM, uma.MOBILE, uma.EMAIL, a.BID_NO " //them moi thong tin
			+" from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_PUB_INSTITU_MAST c, UM_SUPPLIER_ENTER_MAST d, SYN_PUB_CODE g"
			+ " , UM_REC_REPR umrr, UM_BID_AGENT uma "	// them moi bang
			+ " where "
			+ " uma.BIZ_REG_NO = d.BIZ_REG_NO and d.BIZ_REG_NO = umrr.BIZ_REG_NO and umrr.REPR_ISMAIN = 'Y' and d.BIZ_REG_NO = a.BIZ_REG_NO and " //them moi dieu kien
			+"c.HIDDEN_YN = 'N' and "
			+"c.DELETE_YN = 'N' and "
			//+"d.TEST_OPTION_YN is null and "
			+" ( d.TEST_OPTION_YN is null OR d.TEST_OPTION_YN = 'N') and "
			+"d.HIDDEN_YN = 'N' and "
			+"b.PUBLIC_YN = 'Y' and "
			+"a.BID_NO = b.BID_NO and "
			+"a.BID_TURN_NO = b.BID_TURN_NO and "
			+"b.REG_YN = 'Y' and "
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
			+"(select distinct a.BIZ_REG_NO, c.BIZ_NM, c.ADDR, c.PHONE_NO"
//			+ ", "
//			+"(select count(*) from SYN_XML_RECV x, SYN_BID_BID_MASTER y, UM_SUPPLIER_ENTER_MAST z where "
//			+"z.HIDDEN_YN = 'N' and "
//			+" ( z.TEST_OPTION_YN is null OR z.TEST_OPTION_YN = 'N') and "
//			+"y.PUBLIC_YN = 'Y' and "
//			+"x.BID_NO = y.BID_NO and "
//			+"x.BID_TURN_NO = y.BID_TURN_NO and "
//			+"x.BIZ_REG_NO = z.BIZ_REG_NO and "
//			+"y.REG_YN = 'Y' and ";
//
//	      	if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
//	          sql = sql + "y.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
//	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
//	          sql = sql + "y.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and ";
//	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
//	          sql = sql + "y.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') and ";
//	        }
//			sql=sql	+"x.BIZ_REG_NO = a.BIZ_REG_NO) "
//					+"count1, "
//					+"(select count(distinct t1.BIZ_REG_NO) from SYN_XML_RECV t1, SYN_BID_BID_MASTER t2, UM_SUPPLIER_ENTER_MAST t3 where "
//					+"	t3.HIDDEN_YN = 'N' and "
//					//+"t3.TEST_OPTION_YN is null and "
//					+" ( t3.TEST_OPTION_YN is null OR t3.TEST_OPTION_YN = 'N') and "
//					+"t2.PUBLIC_YN = 'Y' and "
//					+"	t1.BID_NO = t2.BID_NO and "
//					+"t1.BID_TURN_NO = t2.BID_TURN_NO and "
//					+"t1.BIZ_REG_NO = t3.BIZ_REG_NO and "
//					+"t2.REG_YN = 'Y' and ";
//
//					if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
//			          sql = sql + "t2.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')) ";
//			        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
//			          sql = sql + "t2.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS')) ";
//			        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
//			          sql = sql + "t2.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')) ";
//			        }
//					sql=sql+"count2 "
					+		" from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_SUPPLIER_ENTER_MAST c where "
					+" c.HIDDEN_YN = 'N' and "
			+" ( c.TEST_OPTION_YN is null OR c.TEST_OPTION_YN = 'N') and "
			+"b.PUBLIC_YN = 'Y' and "
			+"a.BID_NO = b.BID_NO and "
			+"a.BID_TURN_NO = b.BID_TURN_NO and "
			+"a.BIZ_REG_NO = c.BIZ_REG_NO and "
			+"b.REG_YN = 'Y' ";

			if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + "and b.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')))";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + "and b.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS')))";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
		          sql = sql + "and b.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')))";
		    }
			
			//ThachPN 20170222 - them trang thai thanh toan
			sql = sql + ") k LEFT OUTER JOIN BID.BID_PAYMENT_TABLE p ON p.BID_NO=k.BID_NO AND p.BIZ_REG_NO=k.BIZ_REG_NO";
			//ThachPN - End
			
			sql = sql + " ORDER BY k.biz_reg_no, k.BID_NO";
			
	      }else{
	    	  //ThachPN 20170222 - them trang thai thanh toan
	    	  sql = "select k.*, p.PM_FINISH_YN from (";
	    	  //End - trang thai thanh toan
	    	  sql= sql+"select g.CD_NM,a.BID_NO || '-' || a.BID_TURN_NO, c.INSTITU_FULL_NM, b.BID_NM, to_char(b.BID_OPEN_DT, 'dd/MM/yyyy hh:mm'), to_char(b.PUBLIC_DT, 'dd/MM/yyyy hh:mm'),d.biz_nm, CASE WHEN to_char(ROWNUM) = '0' THEN '' else to_char(ROWNUM) END AS NUM,a.biz_reg_no,rownum N "
	    			  + ", d.PHONE_NO, umrr.REPR_MOBILE, umrr.REPR_NM, umrr.REPR_EMAIL , uma.NM, uma.MOBILE, uma.EMAIL, a.BID_NO " //them moi thong tin
	    				+"from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_PUB_INSTITU_MAST c, UM_SUPPLIER_ENTER_MAST d"
	    				+ " , UM_REC_REPR umrr, UM_BID_AGENT uma"	// them moi bang 
	    				+ ",SYN_PUB_CODE g where "
	    				+ " uma.BIZ_REG_NO = d.BIZ_REG_NO and d.BIZ_REG_NO = umrr.BIZ_REG_NO and umrr.REPR_ISMAIN = 'Y' and d.BIZ_REG_NO = a.BIZ_REG_NO and " //them moi dieu kien
	    				+"c.HIDDEN_YN = 'N' and "
	    				+"c.DELETE_YN = 'N' and "
	    				+" ( d.TEST_OPTION_YN is null OR d.TEST_OPTION_YN = 'N') and "
	    				+"d.HIDDEN_YN = 'N' and "
	    				+"b.PUBLIC_YN = 'Y' and "
	    				+"a.BID_NO = b.BID_NO and "
	    				+"a.BID_TURN_NO = b.BID_TURN_NO and "
	    				+"b.REG_YN = 'Y' and "
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
	    				
	    				//ThachPN 20170222 - them trang thai thanh toan
	    				sql = sql + ") k LEFT OUTER JOIN BID.BID_PAYMENT_TABLE p ON p.BID_NO=k.BID_NO AND p.BIZ_REG_NO=k.BIZ_REG_NO";
	    				//ThachPN - End
	    				
	    				//them order
	    				sql = sql + " ORDER BY k.biz_reg_no, k.BID_NO";
	    				
	      }
	      
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
		        	bidMethod=rs.getString(1);
		        }
		        ett.setBidMethod(bidMethod);
		        ett.setBidNo(rs.getString(2));
		        ett.setInstituFullNm(rs.getString(3));
		        ett.setBidNm(rs.getString(4));
		        ett.setBidOpen(rs.getString(5));
		        ett.setBidPublic(rs.getString(6));
		        ett.setBizNm(rs.getString(7));
		        ett.setNum(rs.getString(8));
		        ett.setMst(rs.getString(9));
		        ett.setStt(rs.getInt(10));
		        
		        ett.setPhoneNo(rs.getString("PHONE_NO"));
		        ett.setReprMobile(rs.getString("REPR_MOBILE"));
		        ett.setReprNM(rs.getString("REPR_NM"));
		        ett.setReprEmail(rs.getString("REPR_EMAIL"));
		        ett.setNm(rs.getString("NM"));
		        ett.setMobile(rs.getString("MOBILE"));
		        ett.setEmail(rs.getString("EMAIL"));
		        ett.setPaymentStatus(rs.getString("PM_FINISH_YN"));
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
	public List listResult(String sdate,String edate,String soDKKD){
		List arrListBidPackage=new ArrayList();
		Bidder[] ettlist = null;
		Calendar today=Calendar.getInstance();
		today.setTime(new Date());
		if(((sdate == null) || (sdate.length() == 0)) && ((edate == null) || (edate.length() == 0))){
			sdate=today.getActualMinimum(Calendar.DAY_OF_MONTH)+"/"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.YEAR);
			//01/04/2015
			edate=today.get(Calendar.DAY_OF_MONTH)+"/"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.YEAR);
		}
		ettlist = searchDanhSachNhaThau(1, 1, sdate, edate);
 		if(ettlist!=null){
	 		for(int i = 0 ; i < ettlist.length ; i++){
	 			if(soDKKD.equals("")){
		 			if(ettlist[i].getBizRegNo() != null){
		 	 			BidPackage[] ettlistOneBidPackage = null;
		 				ettlistOneBidPackage = searchDanhSachGoiThau(ettlist[i].getBizRegNo(),sdate,edate);
		 				if(ettlistOneBidPackage != null){
				 			for(int j = 0 ; j < ettlistOneBidPackage.length ; j++){
				 				if(ettlistOneBidPackage[j]!=null){
				 					arrListBidPackage.add((BidPackage) ettlistOneBidPackage[j]);
				 				}
				 			}
			 			}
		 			}
		 		}else{
		 			if(ettlist[i].getBizRegNo() != null&&soDKKD.equals(ettlist[i].getBizRegNo())){
		 	 			BidPackage[] ettlistOneBidPackage = null;
		 				ettlistOneBidPackage = searchDanhSachGoiThau(ettlist[i].getBizRegNo(),sdate,edate);
		 				if(ettlistOneBidPackage != null){
				 			for(int j = 0 ; j < ettlistOneBidPackage.length ; j++){
				 				if(ettlistOneBidPackage[j]!=null){
				 					arrListBidPackage.add((BidPackage) ettlistOneBidPackage[j]);
				 				}
				 			}
			 			}
		 			}	 			
				}
		 	}
 		}
		return arrListBidPackage;
	}

	public Bidder[] searchDanhSachNhaThau(int pageNum, int pageMAX, String sdate, String edate)
	  {
		Log.debug("dinhnv4: Start DanhSachNhaThauBean.searchDanhSachNhaThau(int pageNum, int pageMAX, String sdate, String edate)");
	    Connection con = null;
	    Trx trx = null;
	    ResultSet rs = null;
	    PreparedStatement pstm = null;
	    Vector vec = new Vector();
	    Bidder[] ettlist = (Bidder[])null;
	    try
	    {
	      String sql = "";
	      sql="select BIZ_REG_NO, BIZ_NM, ADDR, PHONE_NO, count2, CASE WHEN to_char(ROWNUM) = '0' THEN ' ' else to_char(count1) END AS count3, CASE WHEN to_char(ROWNUM) = '0' THEN '' else to_char(ROWNUM) END AS NUM from"
	    +"(select distinct a.BIZ_REG_NO, c.BIZ_NM, c.ADDR, c.PHONE_NO,"
		+"(select count(*) from SYN_XML_RECV x, SYN_BID_BID_MASTER y, UM_SUPPLIER_ENTER_MAST z, SYN_BID_STATUS m where " 
		+"z.HIDDEN_YN = 'N' and "
		+"z.TEST_OPTION_YN is null and "
		+"y.PUBLIC_YN = 'Y' and "
		+"	x.BID_NO = y.BID_NO and "
		+"	x.BID_TURN_NO = y.BID_TURN_NO and "
		+"	x.BIZ_REG_NO = z.BIZ_REG_NO and "
		+"	y.REG_YN = 'Y' and ";
//		+"	y.BID_NO = m.BID_NO and "
//		+"	y.BID_TURN_NO = m.BID_TURN_NO and "
//		+"	m.EXEC_TYPE is not null and ";
	      if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "y.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') ";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + "y.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') ";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "y.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS') ";
	        } 
//		+"	y.BID_OPEN_DT between to_date('01/01/2016 00:00:00', 'DD/MM/YYYY HH24:MI:SS') and "
//		+"	to_date('30/03/2016 23:59:59', 'DD/MM/YYYY HH24:MI:SS') and "
		
		sql=sql+"and x.BIZ_REG_NO = a.BIZ_REG_NO) "
		+"count1, "
		+"(select count(distinct t1.BIZ_REG_NO) from SYN_XML_RECV t1, SYN_BID_BID_MASTER t2, UM_SUPPLIER_ENTER_MAST t3, SYN_BID_STATUS t4 where "
		+"	t3.HIDDEN_YN = 'N' and "
		+"	t3.TEST_OPTION_YN is null and "
		+"	t2.PUBLIC_YN = 'Y' and "
		+"	t1.BID_NO = t2.BID_NO and "
		+"	t1.BID_TURN_NO = t2.BID_TURN_NO and "
		+"	t1.BIZ_REG_NO = t3.BIZ_REG_NO and "
		+"	t2.REG_YN = 'Y' and ";
//		+"	t2.BID_NO = t4.BID_NO and "
//		+"	t2.BID_TURN_NO = t4.BID_TURN_NO and "
//		+"	t4.EXEC_TYPE is not null and ";
		if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "t2.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS'))";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + "t2.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS'))";
	        } else if (((sdate == null) || (edate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "t2.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS')) ";
	        }
//		sql=sql+"t2.BID_OPEN_DT between to_date('01/01/2016 00:00:00', 'DD/MM/YYYY HH24:MI:SS') and "
//		+"to_date('30/03/2016 23:59:59', 'DD/MM/YYYY HH24:MI:SS')) ";
		sql=sql+" count2 from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_SUPPLIER_ENTER_MAST c, SYN_BID_STATUS d where "
		+"c.HIDDEN_YN = 'N' and "
		+"c.TEST_OPTION_YN is null and "
		+"b.PUBLIC_YN = 'Y' and "
		+"a.BID_NO = b.BID_NO and "
		+"a.BID_TURN_NO = b.BID_TURN_NO and "
		+"a.BIZ_REG_NO = c.BIZ_REG_NO and "
		+"b.REG_YN = 'Y' and ";
//		+"b.BID_NO = d.BID_NO and "
//		+"b.BID_TURN_NO = d.BID_TURN_NO and "
//		+"d.EXEC_TYPE is not null and ";
		if ((sdate != null) && (sdate.length() != 0) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "b.BID_OPEN_DT between to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS') and to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS'))";
	        } else if ((sdate != null) && (sdate.length() != 0) && ((edate == null) || (edate.length() == 0))) {
	          sql = sql + "b.BID_OPEN_DT > to_date('" + sdate + " 00:00:00', 'dd/mm/yyyy HH24:MI:SS'))";
	        } else if (((sdate == null) || (sdate.length() == 0)) && (edate != null) && (edate.length() != 0)) {
	          sql = sql + "b.BID_OPEN_DT < to_date('" + edate + " 23:59:59', 'dd/mm/yyyy HH24:MI:SS'))";
	        }
//			sql=sql+"b.BID_OPEN_DT between to_date('01/01/2016 00:00:00', 'DD/MM/YYYY HH24:MI:SS') and "
//			+"to_date('30/03/2016 23:59:59', 'DD/MM/YYYY HH24:MI:SS'))";
			sql=sql+" order by ROWNUM, NLSSORT(BIZ_NM,'NLS_SORT=vietnamese') asc";
	      
	      Log.debug("dinhnv4=sql--" + sql);
	      trx = new Trx(this, "usemn");
	      con = trx.getConnection();
	      pstm = con.prepareStatement(sql);
	      
	      rs = pstm.executeQuery();
	      pstm.clearParameters();
	      rs = pstm.executeQuery();
	      pstm.clearParameters();
	      while (rs.next())
	      {
	        Bidder ett = new Bidder();
	        ett.setBizRegNo(rs.getString("BIZ_REG_NO"));
	        ett.setBizNm(rs.getString("BIZ_NM"));
	        ett.setAddr(rs.getString("ADDR"));
	        ett.setPhoneNo(rs.getString("PHONE_NO"));
	        ett.setNum(rs.getString("count2"));
	        vec.addElement(ett);
	      }
	    }
	    catch (SQLException sqle)
	    {
	      Log.debug(
	        "DanhSachNhaThauBean.searchDanhSachNhaThau() SQLException : " + sqle.toString());
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
	        "DanhSachNhaThauBean.searchDanhSachNhaThau() " + exc.toString());
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
	      ettlist = new Bidder[vec.size()];
	      vec.copyInto(ettlist);
	      
	      return ettlist;
	    }
	    return null;
	  }
	public BidPackage[] searchDanhSachGoiThau(String BizRegNo,String sDate,String eDate)
	  {
		Date startDate=null;
		Date endDate=null;
		if(sDate.equals("")){
			startDate=new Date(1970, 1, 1);
		}else{
			startDate=convertStringToDate(sDate);
		}
		if(eDate.equals("")){
			endDate=new Date(2500, 11, 31);
		}else{
			endDate=convertStringToDate(eDate);
		}
		Log.debug("dinhnv4: Start DanhSachGoiThauBean.searchDanhSachGoiThau((String BizRegNo)");
	    Connection con = null;
	    Trx trx = null;
	    ResultSet rs = null;
	    PreparedStatement pstm = null;
	    Vector vec = new Vector();
	    BidPackage[] ettlist = (BidPackage[])null;
	    try
	    {
	      String sql = "";
	      sql="select b.BID_METHOD,a.BID_NO || '-' || a.BID_TURN_NO, c.INSTITU_FULL_NM, b.BID_NM, to_char(b.BID_OPEN_DT, 'dd/MM/yyyy hh:mm'), to_char(b.PUBLIC_DT, 'dd/MM/yyyy hh:mm'),d.biz_nm, CASE WHEN to_char(ROWNUM) = '0' THEN '' else to_char(ROWNUM) END AS NUM," 
	    		  +"(select count(*) from SYN_XML_RECV t1, SYN_BID_BID_MASTER t2, UM_PUB_INSTITU_MAST t3, UM_SUPPLIER_ENTER_MAST t4, SYN_BID_STATUS t5  where "
			+"t3.HIDDEN_YN = 'N' and "
				+"t3.DELETE_YN = 'N' and "
				+"t4.TEST_OPTION_YN is null and "
				+"t4.HIDDEN_YN = 'N' and "
				+"t2.PUBLIC_YN = 'Y' and "
				+"t1.BID_NO = t2.BID_NO and "
				+"t1.BID_TURN_NO = t2.BID_TURN_NO and "
				+"t2.REG_YN = 'Y' and "
//				+"t2.BID_NO = t5.BID_NO and "
//				+"t2.BID_TURN_NO = t5.BID_TURN_NO and "
//				+"t5.EXEC_TYPE is not null and "
				+"t2.ORDER_INSTITU_CD = t3.INSTITU_CD and "
				+"t1.BIZ_REG_NO = t4.BIZ_REG_NO and "
				+"t1.BIZ_REG_NO = '"+BizRegNo+"') "
		+"from SYN_XML_RECV a, SYN_BID_BID_MASTER b, UM_PUB_INSTITU_MAST c, UM_SUPPLIER_ENTER_MAST d, SYN_BID_STATUS m where "
		+"c.HIDDEN_YN = 'N' and "
		+"c.DELETE_YN = 'N' and "
		+"d.TEST_OPTION_YN is null and "
		+"d.HIDDEN_YN = 'N' and "
		+"b.PUBLIC_YN = 'Y' and "
		+"a.BID_NO = b.BID_NO and "
		+"a.BID_TURN_NO = b.BID_TURN_NO and "
		+"b.REG_YN = 'Y' and "
//		+"b.BID_NO = m.BID_NO and "
//		+"b.BID_TURN_NO = m.BID_TURN_NO and "
//		+"m.EXEC_TYPE is not null and "
		+"b.ORDER_INSTITU_CD = c.INSTITU_CD and "
		+"a.BIZ_REG_NO = d.BIZ_REG_NO and "
		+"a.BIZ_REG_NO = '"+BizRegNo+"' "
		+"order by ROWNUM, NLSSORT(b.BID_NM,'NLS_SORT=vietnamese') asc";
	      Log.debug("dinhnv4=sql--" + sql);
	      trx = new Trx(this, "usemn");
	      con = trx.getConnection();
	      pstm = con.prepareStatement(sql);
	      
	      rs = pstm.executeQuery();
	      pstm.clearParameters();
	      rs = pstm.executeQuery();
	      pstm.clearParameters();
	      while (rs.next())
	      {
	    	Date openDate=convertStringToDate2(rs.getString(5));
	    	if(startDate.before(openDate)&&endDate.after(openDate)){
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
		        ett.setMst(BizRegNo);
		        vec.addElement(ett);
	        }
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
	      
	      return ettlist;
	    }
	    return null;
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