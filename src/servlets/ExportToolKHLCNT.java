package servlets;

import java.io.PrintWriter;

import beans.ExportToolKHLCNTDAO;
import common.Log;
import entity.ExportTBMTEntity;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExportToolKHLCNT extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res)
	  {
	    try
	    {
	      req.setCharacterEncoding("utf-8");
	      ExportToolKHLCNTDAO exportToolKHLCNTDAO = new ExportToolKHLCNTDAO();
	  
	      String fromDate 	= retNull(req.getParameter("fromDate"));
	      String toDate 	= retNull(req.getParameter("toDate"));
	    Log.debug(fromDate + " | " + toDate);
	      ExportTBMTEntity[] listResult = exportToolKHLCNTDAO.getKHLCNTList(fromDate, toDate);
	     
	   
	      res.setContentType("application/vnd.ms-excel; charset=utf-8");
	      res.setHeader("Content-Disposition", "attachment; filename=ExportTBMT.xls");
	      PrintWriter out = res.getWriter();
	      headHtml(out, listResult.length,fromDate, toDate);
	      dispResultSet(listResult, out);
	      endHtml(out);
	    }
	    catch (Exception e)
	    {
	      Log.errors(this, e, "ExportToolTBMT.service() Exception block");
	    }
	  }
	  
	  private void headHtml(PrintWriter out, int toTal, String fromdt, String todt)
	  {

	    String title = "DANH SÁCH GÓI THẦU TRONG KẾ HOẠCH LỰA CHỌN NHÀ THẦU ĐĂNG TẢI Từ ngày: " +fromdt + " đến ngày: " + todt;
	    
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
	    out.println("<p align='right'><font size='3' face='Arial'><b>Tổng số TBMT: " + toTal  + " </b></font></p><br>");
	  }
	  
	  private void dispResultSet(ExportTBMTEntity[] listResult, PrintWriter out)
	    throws Exception
	  {
	    out.println("<table\tx:str border=1 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' align=center width=95%>");
	    
	    out.println("  <tr>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>STT</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Số KHLCNT</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Tên KHLCNT</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Tên gói thầu</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Số thứ tự gói thầu/Tổng số gói thầu</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Tên BMT</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Giá gói thầu (VND)</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Hình thức lựa chọn nhà thầu</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Thời gian lựa chọn nhà thầu</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Thời gian thực hiện hợp đồng từng gói thầu</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Loại hợp đồng từng gói thầu</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Ngành nghề</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Hình thức LCNT</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Số dự án</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Tên nguồn vốn chi tiết</b></td>");
	    out.println("\t <td bgcolor='#EDEDED' ><b>Mã cơ quan</b></td>");
	    out.println("  </tr>");
	    

	    int count = listResult.length;
	    Log.debug("dispResultSet >>> total records >>> " + count);
	    for (int i = 0; i < count; i++)
	    {
	      out.println("  <tr>");
	      out.println("\t <td width=5%>" + (i + 1) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS01()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS02()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS03()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS04()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS05()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS06()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS07()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS08()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS09()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS10()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS11()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS12()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS13()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS14()) + "</td>");
	      out.println("    <td>" + isNull(listResult[i].getS15()) + "</td>");
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

