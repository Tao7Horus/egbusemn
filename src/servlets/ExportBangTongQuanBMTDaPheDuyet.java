package servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UM_URB_GovuA030c2;
import common.Log;
import entity.UM_ADV_GovuA030b;

public class ExportBangTongQuanBMTDaPheDuyet extends HttpServlet
{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 6175580832981052504L;

	public void service(HttpServletRequest req, HttpServletResponse res)
	  {
	    try
	    {
	      req.setCharacterEncoding("utf-8");
	      UM_URB_GovuA030c2 govu = new UM_URB_GovuA030c2();
	      String permitBranch 	= retNull(req.getParameter("permitBranch"));
	      String upcheNm 		= retNull(req.getParameter("upcheNm"));
	      String upcheCode 		= retNull(req.getParameter("upcheCode"));
	      String StartDate 		= retNull(req.getParameter("StartDate"));
	      String postNo 		= retNull(req.getParameter("postNo"));
	      String EndDate 		= retNull(req.getParameter("EndDate"));
	      
	      UM_ADV_GovuA030b[] listResult = govu.approvalTotalListToExport(upcheNm, upcheCode, postNo, permitBranch, StartDate, EndDate);
	      Log.debug("ExportBangTongQuanBMTDaPheDuyet.service returl list size >>> " + listResult.length);
	      res.setContentType("application/vnd.ms-excel; charset=utf-8");
	      res.setHeader("Content-Disposition", "attachment; filename=report.xls");
	      PrintWriter out = res.getWriter();
	      headHtml(out);
	      //infoHtml(out, methodString, itempmString, fromDate, toDate);
	      dispResultSet(listResult, out);
	      endHtml(out);
	    }
	    catch (Exception e)
	    {
	      Log.errors(this, e, "ExportBangTongQuanBMTDaPheDuyet.service() Exception block");
	    }
	  }
	  
	  private void headHtml(PrintWriter out)
	  {
	    String title = "Bảng tổng hợp bên mời thầu đã được phê duyệt";
	    
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">");
	    out.println("<title>" + title + "</title>");
	    out.println("</head>");
	    out.println("<body bgcolor=white text=black>");
	    out.println("<table\tx:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width='80%' align='center'>");
	    out.println("<tr style='padding-top:4.0pt'>");
	    out.println("  <td bgcolor='#EDEDED' height='45' style='border:.5pt\tsolid windowtext;verticla-align:center;' colspan=7>");
	    out.println("    <p align='center'><font size='4' face='Arial'><b>" + title + "</b></font></p></td>");
	    out.println("</tr>");
	    
	    out.println("</table>");
	    out.println("<br>");
	    out.println("<br>");
	  }
	  
	  private void dispResultSet(UM_ADV_GovuA030b[] listpm, PrintWriter out)
	    throws Exception
	  {
	    out.println("<table\tx:str border=1 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' align=center width=95%>");
	    
	    out.println("  <tr>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>STT</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Mã BMT</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Tên bên mời thầu</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Số ĐKKD</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Địa chỉ</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Số điện thoại</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Tỉnh / Thành phố</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Ngày đăng ký</b></td>");
	    out.println("  </tr>");
	    

	    int count = listpm.length;
	    Log.debug("dispResultSet >>> total records >>> " + count);
	    for (int i = 0; i < count; i++)
	    {
	      out.println("  <tr>");
	      out.println("\t <td width=5%>" + (i + 1) + "</td>");
	      out.println("    <td>" + isNull(listpm[i].getdependCode()) + "</td>");
	      out.println("    <td>" + isNull(listpm[i].getgoNameFull()) + "</td>");
	      out.println("    <td>'" + isNull(listpm[i].getsaupNo()) + "</td>");
	      out.println("    <td>" + isNull(listpm[i].getADDR()) + "</td>");
	      out.println("    <td>" + isNull(listpm[i].getmasterTel()) + "</td>");
	      out.println("    <td>" + isNull(listpm[i].getpermitCode()) + "</td>");
	      out.println("    <td>" + isNull(listpm[i].getcreateDate()) + "</td>");
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
