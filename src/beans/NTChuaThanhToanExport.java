package beans;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.DateTime;
import common.Log;
import common.Trx;
import entity.UM_RAE_ConuB010b;

public class NTChuaThanhToanExport extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) {
		try {
			req.setCharacterEncoding("utf-8");

			String saupNo = htmlEncode(req.getParameter("saupNo"));
			String sangho = htmlEncode(req.getParameter("sangho"));
			String sdate = htmlEncode(req.getParameter("sdate"));
			String edate = htmlEncode(req.getParameter("edate"));
			String status = htmlEncode(req.getParameter("status"));
			String checkYN = htmlEncode(req.getParameter("checkYN"));
			String flag = "1";
			String pmstatus = htmlEncode(req.getParameter("pmstatus"));
			Log.debug("NTChuaThanhToanExport.service()>>> " + saupNo);
			Log.debug("NTChuaThanhToanExport.service()>>> " + sangho);
			Log.debug("NTChuaThanhToanExport.service()>>> " + sdate);
			Log.debug("NTChuaThanhToanExport.service()>>> " + edate);
			Log.debug("NTChuaThanhToanExport.service()>>> " + status);
			Log.debug("NTChuaThanhToanExport.service()>>> " + flag);
			Log.debug("NTChuaThanhToanExport.service()>>> " + pmstatus);
			Log.debug("NTChuaThanhToanExport.service()>>> " + checkYN);

			UM_RAE_ConuB010b[] ettlist = getResultListPM_Excel(1, 999999, saupNo, sangho, sdate, edate, status, pmstatus);
			Log.debug("NTChuaThanhToanExport.service() ettlist>>> " + ettlist.length);
			String statusStr = "";
			String pmstatusStr = "";
			if ("A".equals(pmstatus)) {
				pmstatusStr = "Tất cả";
			}
			if ("B".equals(pmstatus)) {
				pmstatusStr = "Chưa thanh toán";
			}
			if ("C".equals(pmstatus)) {
				pmstatusStr = "Đã thanh toán";
			}
			if ("A".equals(status)) {
				statusStr = "Tất cả";
			}
			if ("Y".equals(status)) {
				statusStr = "Đã phê duyệt";
			}
			if ("N".equals(status)) {
				statusStr = "Chưa phê duyệt";
			}
			if ("E".equals(status)) {
				statusStr = "Bảo lưu";
			}
			if ("D".equals(status)) {
				statusStr = "Từ chối";
			}
			if ("C".equals(status)) {
				statusStr = "Hủy bỏ";
			}
			String strYN = "";
			if ("Y".equals(checkYN)) {
				strYN = "Đăng ký thử nghiệm";
			}
			if ("N".equals(checkYN)) {
				strYN = "Đăng ký thực";
			}
			res.setContentType("application/vnd.ms-excel; charset=utf-8");
			res.setHeader("Content-Disposition", "attachment; filename=report.xls");
			PrintWriter out = res.getWriter();
			headHtml(out);
			infoHtml(out, statusStr, pmstatusStr, sdate, edate, strYN);
			dispResultSet(ettlist, out);
			endHtml(out);
		} catch (Exception e) {
			Log.errors(this, e, "NTChuaThanhToanExport.service() Exception block");
		}
	}

	private void headHtml(PrintWriter out) {
		String title = "Danh sách các nhà thầu chưa thanh toán chi phí duy trì và dữ liệu";

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
				"  <td height='45' colspan=7>");
		out.println("    <p align='center'><font size='5' face='Arial'><b>" + title + "</b></font></p></td>");
		out.println("</tr>");

		out.println("</table>");
		out.println("<br>");
		out.println("<br>");
	}

	private void infoHtml(PrintWriter out, String method, String item, String dateForm, String dateTo, String checkYN) {
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
		out.println("Tình trạng thanh toán");
		out.println("</td>");
		out.println("<td>");
		out.println(item);
		out.println("</td>");
		out.println("</tr>");
		
		
		
		out.println("</table>");

		out.println("<br>");
		out.println("<br>");
	}

	private void dispResultSet(UM_RAE_ConuB010b[] listpm, PrintWriter out) throws Exception {
		out.println(
				"<table\tx:str border=1 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' align=center width=95%>");

		out.println("  <tr>");
		out.println("\t <td bgcolor='#EDEDED' ><b>STT</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Thời gian ĐK</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Thời gian phê duyệt</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Tên công ty</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Số ĐKKD/MST</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>SĐT bàn</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Tên lãnh đạo</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>SĐT lãnh đạo</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Email lãnh đạo</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Tên người phụ trách DT</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Số ĐT người phụ trách DT</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Email người phụ trách DT</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Trạng thái thanh toán/Năm nộp phí gần nhất</b></td>");
		out.println("\t <td bgcolor='#EDEDED' ><b>Thông tin thanh toán của nhà thầu</b></td>");
		out.println("  </tr>");

		int count = listpm.length;
		NTChuaThanhToanSearch dao = new NTChuaThanhToanSearch();
		for (int i = 0; i < count; i++) {
			out.println("  <tr>");
			out.println("\t <td width=1%>" + (i + 1) + "</td>");
			out.println("    <td>&nbsp;" + isNull(listpm[i].getRegDT()) + "</td>");
			out.println("    <td>&nbsp;" + isNull(listpm[i].getSinchungDate()) + "</td>");
			out.println("    <td align=left>&nbsp;" + isNull(listpm[i].getBizNM()) + "</td>");
			out.println("    <td>&nbsp;" + isNull(listpm[i].getBizRegNo()) + "</td>");
			out.println("    <td align=left>&nbsp;" + isNull(listpm[i].getPhoneNo()) + "</td>");
			out.println("    <td>" + isNull(listpm[i].getReprNM()) + "</td>");
			out.println("    <td>" + isNull(listpm[i].getReprMobile()) + "</td>");
			out.println("    <td>" + isNull(listpm[i].getReprEmail()) + "</td>");
			out.println("    <td>" + isNull(listpm[i].getNm()) + "</td>");
			out.println("    <td>" + isNull(listpm[i].getMobile()) + "</td>");
			out.println("    <td>" + isNull(listpm[i].getEmail()) + "</td>");
			out.println("    <td>" + isNull(listpm[i].getPaidStatus()) + "</td>");
			out.println("    <td> "+dao.getInfoPayment(dao.getCurrentYear(), listpm[i].getMaxYear(), listpm[i].getIsDangKy(), listpm[i].getIsDuyTri(),listpm[i].getSinchungDate())+" </td>");
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

	public UM_RAE_ConuB010b[] getResultListPM_Excel(int pageNum, int pageMAX, String saupNo, String sangho, String sdate, String edate, String status, String pmstatus)
	  {
	    Connection con = null;
	    Trx trx = null;
	    ResultSet rs = null;
	    PreparedStatement pstm = null;
	    Vector vec = new Vector();
	    UM_RAE_ConuB010b[] ettlist = (UM_RAE_ConuB010b[])null;
	    int start 	= 1;
	    int end 	= 999999;
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
	        sql = sql + " order by t1.REG_DT DESC";
	        
	        sql = sql + ") where N between " + start + " and " + end;
	      }
	      else { //ANHH9 edit sql
	    	  sql = "select * from(  "
					+ "select ROWNUM N,BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, REPR_EMAIL, REPR_MOBILE, REG_YN, RECV_NO , EMAIL, MOBILE,PHONE_NO, DECODE(PAID, NULL,'Chưa thanh toán', DECODE(sign(PAID - " + currentYear + "), -1, ''||PAID, 'Đã thanh toán')) PAID , NM , PM_ITEM_DK,PM_ITEM_DT,MAX_YEAR,DOC_CREAT_DT "
					+ "from (  	"
					+ "		select t1.BIZ_REG_NO, DECODE(d.BIZ_NM, NULL, t1.BIZ_NM, d.BIZ_NM) BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.REG_YN, t1.RECV_NO, C.PAID , t2.REPR_EMAIL, t2.REPR_MOBILE, ua.MOBILE, ua.EMAIL, t1.PHONE_NO, ua.NM, e.PM_ITEM_DK,f.PM_ITEM_DT, C.PAID AS MAX_YEAR,TO_CHAR(t1.DOC_CREAT_DT, 'dd/mm/yyyy') DOC_CREAT_DT  "
					+ "		from UM_REC_SUPPLIER_ENTER_MAST t1,"
					+ "			 UM_REC_REPR t2 , "
					+ "			 (SELECT BIZ_REG_NO, MAX(B.PM_YEAR) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1,2) GROUP BY BIZ_REG_NO) C  , "
					+ "			(select y.PM_ITEM_ID as PM_ITEM_DK,x.BIZ_REG_NO  from BID.BID_PAYMENT_TABLE x, BID.BID_PAYMENT_DETAIL y where x.PM_TABLE_ID=y.PM_TABLE_ID   and x.PM_BNK_STATUS_VALUE='0' and x.PM_FINISH_YN='Y' and y.PM_ITEM_ID='1' )  e "
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
	      Log.debug("anhh9=sql-***********-" + sql);
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
	        ett.setPaidStatus(rs.getString("PAID"));
	        
	        ett.setMaxYear(rs.getString("MAX_YEAR"));
	        ett.setIsDangKy(rs.getString("PM_ITEM_DK"));
	        ett.setIsDuyTri(rs.getString("PM_ITEM_DT"));
	        ett.setSinchungDate(rs.getString("DOC_CREAT_DT"));
	        vec.addElement(ett);
	      }
	    }
	    catch (SQLException sqle)
	    {
	      Log.debug("NTChuaThanhToanExport.getResultListPM_Excel() SQLException : " + sqle.toString());
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
}
