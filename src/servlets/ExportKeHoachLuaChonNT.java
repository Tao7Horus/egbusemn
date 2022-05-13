package servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.KeHoachLuaChonNhaThauDao;
import common.Log;
import entity.UM_URV_UserC020b;

public class ExportKeHoachLuaChonNT extends HttpServlet
{
	private final String TYPE_BBN 	= "BBN";
	private final String TYPE_TTP 	= "TTP";
	private final String TYPE_TD 	= "TD";
	  /**
	 * 
	 */
	private static final long serialVersionUID = 6175580832981052504L;

	public void service(HttpServletRequest req, HttpServletResponse res)
	  {
	    try
	    {
	      req.setCharacterEncoding("utf-8");
	      KeHoachLuaChonNhaThauDao keHoachLuaChonNT = new KeHoachLuaChonNhaThauDao();
	      String condition 	= retNull(req.getParameter("condition"));
	      String type 		= retNull(req.getParameter("type"));
	      String fromDate 	= retNull(req.getParameter("fromDate"));
	      String toDate 	= retNull(req.getParameter("toDate"));
	      String subTitleInReport = retNull(req.getParameter("subTitleInReport"));
	      UM_URV_UserC020b[] listResult = keHoachLuaChonNT.getKeHoachLuaChonNTList(type, condition, fromDate, toDate);
	     
	      Log.debug("ExportKeHoachLuaChonNT.service returl list size >>> " + listResult.length);
	      res.setContentType("application/vnd.ms-excel; charset=utf-8");
	      res.setHeader("Content-Disposition", "attachment; filename=KeHoachLuaChonNT.xls");
	      PrintWriter out = res.getWriter();
	      headHtml(out, type, condition, subTitleInReport, listResult.length);
	      //infoHtml(out, methodString, itempmString, fromDate, toDate);
	      dispResultSet(listResult, out);
	      endHtml(out);
	    }
	    catch (Exception e)
	    {
	      Log.errors(this, e, "ExportKeHoachLuaChonNT.service() Exception block");
	    }
	  }
	  
	  private void headHtml(PrintWriter out, String type, String condition, String subTitle, int toTal)
	  {
		StringBuffer subTitleBu = new StringBuffer();
		if (TYPE_BBN.equals(type)) {
			if (condition == null || condition == "") {
				subTitleBu.append("Tất cả các bộ ban nghành");
			} else {
				subTitleBu.append(subTitle);
			}
		}
		if (TYPE_TD.equals(type)) {
			if (condition == null || condition == "") {
				subTitleBu.append("Tất cả các tập đoàn");
			} else {
				subTitleBu.append(subTitle);
			}
		}
		if (TYPE_TTP.equals(type)) {
			if (condition == null || condition == "") {
				subTitleBu.append("Tất cả các Tỉnh/Thành Phố");
			} else {
				subTitleBu.append(subTitle);
			}
		}
	    String title = "DANH SÁCH GÓI THẦU THUỘC " + subTitleBu.toString().toUpperCase();
	    
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">");
	    out.println("<title>" + title + "</title>");
	    out.println("</head>");
	    out.println("<body bgcolor=white text=black>");
	    out.println("<table\tx:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width='80%' align='center'>");
	    out.println("<tr style='padding-top:4.0pt'>");
	    out.println("<td bgcolor='#EDEDED' height='45' style='border:.5pt\tsolid windowtext;verticla-align:center;' colspan=7>");
	    out.println("<p align='center'><font size='4' face='Arial'><b>" + title + "</b></font></p></td>");
	    out.println("</tr>");
	    
	    out.println("</table>");
	    out.println("<br>");
	    out.println("<br>");
	    out.println("<p align='right'><font size='3' face='Arial'><b>Tổng số KHLCNT: " + toTal  + " </b></font></p><br>");
	  }
	  
	  private void dispResultSet(UM_URV_UserC020b[] listResult, PrintWriter out)
	    throws Exception
	  {
	    out.println("<table\tx:str border=1 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' align=center width=95%>");
	    
	    out.println("  <tr>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>STT</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Số KHLCNT</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Tên Kế hoạch</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Tên Bên mời thầu</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Số gói thầu</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Tổng mức đầu tư</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Số văn bản</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Thời điểm đăng tải</b></td>");
	    out.println("  </tr>");
	    

	    int count = listResult.length;
	    Log.debug("dispResultSet >>> total records >>> " + count);
	    for (int i = 0; i < count; i++)
	    {
	      out.println("  <tr>");
	      out.println("\t <td width=5%>" + (i + 1) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS02()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS03()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS04()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS05()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS06()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS07()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS08()) + "</td>");
	      out.println("  </tr>");
	    }
	    if (count == 0)
	    {
	      out.println("  <tr>");
	      out.println("  <td>Không có thông tin.</td>");
	      out.println("  </tr>");
	    }
	    out.println("</table>");
	  }
	  
	  private void endHtml(PrintWriter out)
	  {
	    out.println("</body>");
	    out.println("</html>");
	  }
	  
	  private String isNull(String str)
	  {
	    if (str == null) {
	      str = " ";
	    }
	    return str;
	  }
	  
	  public static String retNull(String s)
	  {
	    try
	    {
	      if ((s == null) || (s.trim().equals(""))) {
	        return null;
	      }
	    }
	    catch (Exception e)
	    {
	      Log.debug("EP_COB_GTQ805.retNull(" + s + "):" + e.toString());
	    }
	    return s.trim();
	  }
	}
