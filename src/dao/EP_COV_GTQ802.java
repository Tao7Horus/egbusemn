// 
// Decompiled by Procyon v0.5.30
// 

package dao;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

public class EP_COV_GTQ802 extends HttpServlet
{
    public static void printMessage(String s, final String s2, String s3, final String s4, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s5 = "Dữ liệu bị trùng";
        final String s6 = "Không có dữ liệu";
        final String s7 = "Dữ liệu đã được đăng ký";
        final String s8 = "Dữ liệu đã được sửa";
        final String s9 = "Dữ liệu đã được xóa";
        final String s10 = "Chưa được sửa";
        final String s11 = "Chưa được đăng ký";
        final String s12 = "Chưa được xóa";
        final String s13 = "Hủy lệnh xóa";
        final String s14 = "Dữ liệu bị xóa";
        final String s15 = "Không có hồ sơ dự thầu nào được tiếp nhận";
        final String s16 = "Là gói thầu đã kết thúc phần mở thầu";
        final String s17 = "Không thể thực hiện lại do phần khai thầu đã hoàn thành hoặc không có data tiến hành mở thầu";
        final String s18 = "Đã thực hiện lại. Hãy tiến hành đấu thầu";
        final String s19 = "người người dùng của nhà thầu không có quyền sử dụng";
        final String s20 = "Là người sử dụng không có mã đăng ký. hãy login lại";
        final String s21 = "SAXException : không có vấn đề gì trong nội dung hồ sơ đấu thầu. Hãy liên hệ với người quản trị hệ thống";
        final String s22 = "SQLException : chưa đăng ký. Hãy liên hệ với người quản trị hệ thống";
        final String s23 = "IOException : chưa đăng ký. Hãy liên hệ với người quản trị hệ thống";
        final String s24 = "Exception : chưa đăng ký. Hãy liên hệ với người quản trị hệ thống";
        final String s25 = "Bình thường thì bạn chưa login, hoặc thời gian login quá lâu. Hãy login lại.";
        final String s26 = "Không thể kết nối tới cơ sở dữ liệu. Hãy liên lạc với người quản trị hệ thống";
        final String s27 = "Việc khởi tạo thất bại. Hãy thử lại";
        final String s28 = "Đã xảy ra lỗi trong khi tính giá dự bị. Hãy thực hiện lại và tiến hành đấu thầu lại.";
        final String s29 = "Đã có lỗi xảy ra. Hãy liên hệ với người quản trị hệ thống.";
        final String s30 = "Đã xử lý xong thao tác mở thầu";
        final String s31 = "Đã xử lý xong thao tác đấu thầu lại. Sau khi hêt hạn hồ sơ thầu, hãy mở thầu.";
        final String s32 = "Đã xử lý xong thao tác lưu thầu.";
        final String s33 = "Đã xảy ra lỗi khi chuẩn bị đấu thầu Cục mua sắm công. Hãy thử lại.";
        final String s34 = "Đã đăng ký xong thông báo kết quả đấu thầu.";
        final String s35 = "Đã xảy ra lỗi khi đăng ký thông báo kết quả đấu thầu. Một lát sau, hãy thử lại.";
        final String s36 = "InterruptedException : không lấy file đính kèm về được. Một lát sau, hãy thử lại.";
        final String s37 = "DecryptAttachException : đã xảy ra lỗi khi giải mã file đính kèm. Hãy hỏi người quản trị hệ thống.";
        final String s38 = "IOException : không lấy được file đính kèm. Một lát sau, hãy thử lại.";
        final String s39 = "nhà thầu đại diện hoặc một trong số các nhà thầu thành viên đã nộp HSDT từ trước. Hãy phê duyệt lại sau khi kiểm tra.";
        final String s40 = "Quý vị đã nộp hồ sơ thầu rồi. Không thể hủy thao tác phê duyệt.";
        final String s41 = "Không phải là bên mời thầu mà có thể xử lý gói thầu này.";
        final String s42 = "Không thể giải mã giá dự bị. Hãy liên hệ với người quản trị hệ thống.";
        final String s43 = "Không thể kiểm tra chữ ký điện tử, giá dự bị. Hãy liên hệ với người quản trị hệ thống.";
        final String s44 = "Xảy ra lỗi người mở thầu hợp lệ.";
        final String s45 = "Đã lưu thông tin kiến nghị.";
        final String s46 = "Đã công khai điểm số.";
        final String s47 = "Gói thầu chưa hoàn thành việc phê duyệt.";
        final String s48 = "Đã hoàn thành thao tác lựa chọn nhà thầu trúng thầu. <br>Hãy kiểm tra kết quả lựa chọn trên màn hình tìm kiếm nhà thầu trúng thầu [thông tin đấu thầu].";
        final String s49 = "Đã hoàn thành việc đăng tải thông báo mời thầu.";
        final String s50 = "Đã hoàn thành thao tác lựa chọn nhà thầu trúng thầu. <br>Vì gói thầu này là gói thầu không công khai nên không thể kiểm tra kết quả lựa chọn nhà thầu trúng thầu trong [thông tin đấu thầu].";
        if (s == null) {
            s = " ";
        }
        String s51 = "";
        if (s4 == null) {
            s51 = "self.close(); return;";
        }
        else if (s2.equals("1")) {
            s51 = "opener.location.href=\"" + s4 + "\"; self.close(); return;";
        }
        else if (s2.equals("2")) {
            s51 = "opener.parent.location.href=\"" + s4 + "\"; self.close(); return;";
        }
        else if (s2.equals("3")) {
            s51 = "location.href=\"" + s4 + "\"; return;";
        }
        else if (s2.equals("4")) {
            s51 = "history.back(); return;";
        }
        else if (s2.equals("5")) {
            s51 = "window.opener.location.reload(); self.close(); return;";
        }
        else if (s2.equals("6")) {
            s51 = "history.back(); opener.location.href=\"" + s4 + "\"; return;";
        }
        else if (s2.equals("7")) {
            s51 = "opener.openerFunction(); self.close(); return;";
        }
        if (s.equals("001")) {
            s = s5;
        }
        else if (s.equals("002")) {
            s = s6;
        }
        else if (s.equals("003")) {
            s = s7;
        }
        else if (s.equals("004")) {
            s = s8;
        }
        else if (s.equals("005")) {
            s = s9;
        }
        else if (s.equals("006")) {
            s = s10;
        }
        else if (s.equals("007")) {
            s = s11;
        }
        else if (s.equals("008")) {
            s = s12;
        }
        else if (s.equals("009")) {
            s = s13;
        }
        else if (s.equals("010")) {
            s = s14;
        }
        else if (s.equals("011")) {
            s = s15;
        }
        else if (s.equals("012")) {
            s = s16;
        }
        else if (s.equals("013")) {
            s = s17;
        }
        else if (s.equals("014")) {
            s = s18;
        }
        else if (s.equals("015")) {
            s = s19;
        }
        else if (s.equals("016")) {
            s = s20;
        }
        else if (s.equals("017")) {
            s = s21;
        }
        else if (s.equals("018")) {
            s = s22;
        }
        else if (s.equals("019")) {
            s = s23;
        }
        else if (s.equals("020")) {
            s = s24;
        }
        else if (s.equals("021")) {
            s = s25;
        }
        else if (s.equals("022")) {
            s = s26;
        }
        else if (s.equals("023")) {
            s = s27;
        }
        else if (s.equals("024")) {
            s = s28;
        }
        else if (s.equals("025")) {
            s = s29;
        }
        else if (s.equals("026")) {
            s = s30;
        }
        else if (s.equals("027")) {
            s = s31;
        }
        else if (s.equals("028")) {
            s = s32;
        }
        else if (s.equals("029")) {
            s = s33;
        }
        else if (s.equals("030")) {
            s = s34;
        }
        else if (s.equals("031")) {
            s = s35;
        }
        else if (s.equals("032")) {
            s = s36;
        }
        else if (s.equals("033")) {
            s = s37;
        }
        else if (s.equals("034")) {
            s = s38;
        }
        else if (s.equals("035")) {
            s = s39;
        }
        else if (s.equals("036")) {
            s = s40;
        }
        else if (s.equals("037")) {
            s = s41;
        }
        else if (s.equals("038")) {
            s = s42;
        }
        else if (s.equals("039")) {
            s = s43;
        }
        else if (s.equals("040")) {
            s = s44;
        }
        else if (s.equals("041")) {
            s = s45;
        }
        else if (s.equals("042")) {
            s = s46;
        }
        else if (s.equals("043")) {
            s = s47;
        }
        else if (s.equals("044")) {
            s = s48;
        }
        else if (s.equals("045")) {
            s = s49;
        }
        else if (s.equals("046")) {
            s = s50;
        }
        if (s3 == null || s3.equals("")) {
            s3 = s;
        }
        printHtml(s, s3, s51, httpServletResponse);
    }
    
    public static void printMessageG(String s, final String s2, final String s3, final String s4, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (s == null) {
            s = " ";
        }
        String s5 = "";
        if (s4 == null) {
            s5 = "self.close(); return;";
        }
        else if (s2.equals("1")) {
            s5 = "opener.location.href=\"" + s4 + "\"; self.close(); return;";
        }
        else if (s2.equals("2")) {
            s5 = "opener.parent.location.href=\"" + s4 + "\"; self.close(); return;";
        }
        else if (s2.equals("3")) {
            s5 = "location.href=\"" + s4 + "\"; return;";
        }
        else if (s2.equals("4")) {
            s5 = "history.back(); return;";
        }
        else if (s2.equals("5")) {
            s5 = "window.opener.location.reload(); self.close(); return;";
        }
        else if (s2.equals("6")) {
            s5 = "history.back(); opener.location.href=\"" + s4 + "\"; return;";
        }
        else if (s2.equals("7")) {
            s5 = "opener.openerFunction(); self.close(); return;";
        }
        printHtml2(s, s3, s5, httpServletResponse);
    }
    
    public static void printMsg(String s, final String s2, String s3, final String s4, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s5 = "Dữ liệu bị trùng.";
        final String s6 = "Không có dữ liệu.";
        final String s7 = "Dữ liệu đã được đăng ký.";
        final String s8 = "Dữ liệu đã được sửa.";
        final String s9 = "Dữ liệu đã được xóa.";
        final String s10 = "Chưa được sửa.";
        final String s11 = "Chưa đăng ký.";
        final String s12 = "Chưa được xóa.";
        final String s13 = "Đã hủy lệnh xóa.";
        final String s14 = "Dữ liệu bị xóa.";
        final String s15 = "Dữ liệu đã được thực hiện lại.";
        final String s16 = "Thao tác xử lý sơ tuyển tư cách đã hoàn thành.";
        final String s17 = "Dữ liệu đã bị xóa.";
        final String s18 = "Đã lưu thông tin kiến nghị.";
        final String s19 = "Đã công khai điểm số.";
        if (s == null) {
            s = " ";
        }
        String s20 = "";
        if (s4 == null) {
            s20 = "self.close(); return;";
        }
        else if (s2.equals("1")) {
            s20 = "opener.location.href=\"" + s4 + "\"; self.close(); return;";
        }
        else if (s2.equals("2")) {
            s20 = "opener.parent.location.href=\"" + s4 + "\"; self.close(); return;";
        }
        else if (s2.equals("3")) {
            s20 = "location.href=\"" + s4 + "\"; return;";
        }
        else if (s2.equals("4")) {
            s20 = "history.back(); return;";
        }
        else if (s2.equals("5")) {
            s20 = "window.opener.location.reload(); self.close(); return;";
        }
        else if (s2.equals("6")) {
            s20 = "history.back(); opener.location.href=\"" + s4 + "\"; return;";
        }
        else if (s2.equals("7")) {
            s20 = "opener.openerFunction(); self.close(); return;";
        }
        if (s.equals("001")) {
            s = s5;
        }
        else if (s.equals("002")) {
            s = s6;
        }
        else if (s.equals("003")) {
            s = s7;
        }
        else if (s.equals("004")) {
            s = s8;
        }
        else if (s.equals("005")) {
            s = s9;
        }
        else if (s.equals("006")) {
            s = s10;
        }
        else if (s.equals("007")) {
            s = s11;
        }
        else if (s.equals("008")) {
            s = s12;
        }
        else if (s.equals("009")) {
            s = s13;
        }
        else if (s.equals("010")) {
            s = s14;
        }
        else if (s.equals("011")) {
            s = s15;
        }
        else if (s.equals("012")) {
            s = s16;
        }
        else if (s.equals("013")) {
            s = s17;
        }
        else if (s.equals("041")) {
            s = s18;
        }
        else if (s.equals("042")) {
            s = s19;
        }
        if (s3 == null || s3.equals("")) {
            s3 = s;
        }
        printHtml(s, s3, s20, httpServletResponse);
    }
    
    public static void printMsgOpenWin1(String s, final String s2, String s3, final String s4, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s5 = "Dữ liệu bị trùng";
        final String s6 = "Không có dữ liệu tương ứng";
        final String s7 = "Dữ liệu đã được đăng ký";
        final String s8 = "Dữ liệu đã bị sửa.";
        final String s9 = "Dữ liệu đã bị xóa";
        final String s10 = "Chưa sửa";
        final String s11 = "Chưa đăng ký";
        final String s12 = "Chưa xóa";
        final String s13 = "Hủy lênh xóa";
        final String s14 = "Dữ liệu bị xóa";
        final String s15 = "Dữ liệu đã được khởi tạo";
        final String s16 = "Quá trình sơ tuyển đã hoàn thành";
        if (s == null) {
            s = " ";
        }
        String string;
        if (s4 == null) {
            string = "self.close(); return;";
        }
        else {
            string = "location.href=\"" + s4 + "\"; history.back(); return;";
        }
        if (s.equals("001")) {
            s = s5;
        }
        else if (s.equals("002")) {
            s = s6;
        }
        else if (s.equals("003")) {
            s = s7;
        }
        else if (s.equals("004")) {
            s = s8;
        }
        else if (s.equals("005")) {
            s = s9;
        }
        else if (s.equals("006")) {
            s = s10;
        }
        else if (s.equals("007")) {
            s = s11;
        }
        else if (s.equals("008")) {
            s = s12;
        }
        else if (s.equals("009")) {
            s = s13;
        }
        else if (s.equals("010")) {
            s = s14;
        }
        else if (s.equals("011")) {
            s = s15;
        }
        else if (s.equals("012")) {
            s = s16;
        }
        if (s3 == null || s3.equals("")) {
            s3 = s;
        }
        printHtml(s, s3, string, httpServletResponse);
    }
    
    public static void printError(String s, final String s2, String s3, final String s4, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s5 = "Lỗi Exception đã xảy ra.";
        final String s6 = "Lỗi SQLException đã xảy ra.";
        final String s7 = "Không thể đọc được thông tin Cookie.<br>Sau khi đóng browser, hãy thực hiện lại.";
        final String s8 = "Không phải là bên mời thầu của gói thầu tương ứng.<br>Hãy kiểm tra lại nội dung thông báo và có phải là bên mời thầu hay không.";
        final String s9 = "Không phải là bên mời thầu của gói thầu tương ứng.<br>Hãy kiểm tra lại nội dung thông báo và có phải là bên mời thầu hay không.";
        final String s10 = "Cần thực hiện thao tác kiểm tra dữ liệu.<br>Hãy liên lạc với người quản trị hệ thống .";
        final String s11 = "Thông tin";
        if (s == null) {
            s = " ";
        }
        String s12 = "";
        if (s4 == null) {
            s12 = "self.close(); return;";
        }
        else if (s2.equals("1")) {
            s12 = "opener.location.href=\"" + s4 + "\"; self.close(); return;";
        }
        else if (s2.equals("2")) {
            s12 = "opener.parent.location.href=\"" + s4 + "\"; self.close(); return;";
        }
        else if (s2.equals("3")) {
            s12 = "location.href=\"" + s4 + "\"; return;";
        }
        else if (s2.equals("4")) {
            s12 = "history.back(); return;";
        }
        else if (s2.equals("5")) {
            s12 = "window.opener.location.reload(); self.close(); return;";
        }
        else if (s2.equals("6")) {
            s12 = "history.back(); opener.location.href=\"" + s4 + "\"; return;";
        }
        if (s.equals("001")) {
            s = s5;
        }
        else if (s.equals("002")) {
            s = s6;
        }
        else if (s.equals("003")) {
            s = s7;
        }
        else if (s.equals("004")) {
            s = s8;
        }
        else if (s.equals("005")) {
            s = s9;
        }
        else if (s.equals("006")) {
            s = s10;
        }
        else if (s.equals("007")) {
            s = s11;
        }
        if (s3 == null || s3.equals("")) {
            s3 = s;
        }
        printHtml(s, s3, s12, httpServletResponse);
    }
    
    public static void printMainMessage(String s, final String s2, final String s3, final String s4, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s5 = "Hãy chọn nhà thầu trúng thầu cuối cùng tại EDI<br>Bên mời thầu ngoài Cục mua sắm công có thể sử dụng được trang này.";
        if (s == null) {
            s = " ";
        }
        String s6 = "";
        if (s4 == null) {
            s6 = "top.location.href=\"http://muasamcong.mpi.gov.vn\"; return;";
        }
        else if (s2.equals("1")) {
            s6 = "location.href=\"" + s4 + "\"; return;";
        }
        else if (s2.equals("2")) {
            s6 = "top.location.href=\"" + s4 + "\"; return;";
        }
        if (s.equals("001")) {
            s = s5;
        }
        printHtml3(s, s3, s6, httpServletResponse);
    }
    
    public static void printMessage1(String string, final HttpServletResponse httpServletResponse, final String s, final String s2, final String s3, final String s4) throws ServletException, IOException {
        if (s3.equals("01") && (s.substring(0, 2).equals("31") || s.substring(0, 2).equals("32"))) {
            string += "<img src='/img/notice-ico.gif'> &nbsp; <font color=blue>Thông báo mời thầu của đối tượng cạnh tranh chỉ định.</font><br> &nbsp;&nbsp;<img src='/img/3rd_icon_a.gif'> &nbsp; <font color=black>Nếu muốn hạn chế việc nộp hồ sơ thầu đối với nhà thầu chỉ định</font><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color=black>[Màn hình Bên mời thầu]-[Tiến hành đấu thầu]-[Nhập nhà thầu tham gia đấu thầu hạn chế/Nhập nhà thầu tham gia chỉ định thầu] tại menu</font><br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color=black>Hãy đăng ký nhà thầu chỉ định. <br>&nbsp;&nbsp;<img src='/img/3rd_icon_a.gif'> &nbsp;Nếu không Nhập nhà thầu tham gia đấu thầu hạn chế/Nhập nhà thầu tham gia chỉ định thầu thì không giới hạn giờ nộp hồ sơ thầu.</font><br> ";
        }
        if (s4.equals("00")) {
            printMessageG(null, "1", string, "/GG/EP_MPJ_GGI003.jsp?gongonum=" + s2, httpServletResponse);
        }
        else {
            printMessageG(null, "1", string, "/GT/EP_COJ_GTI992.jsp?gonggo_num=" + s2 + "&gonggo_char=" + s4 + "&gubun=2", httpServletResponse);
        }
    }
    
    public static void printConfirmHtml(final String s, final String s2, final String s3, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (!httpServletResponse.containsHeader("Content-Type")) {
            httpServletResponse.setContentType("text/html; charset=utf-8");
        }
        final PrintWriter writer = httpServletResponse.getWriter();
        writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        writer.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
        writer.println("<head>");
        writer.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        writer.println("<title>Thông báo</title>");
        writer.println("<link href='http://muasamcong.mpi.gov.vn/css/message.css' rel='stylesheet' type='text/css' />");
        writer.println("<link href='http://muasamcong.mpi.gov.vn/css/TA.css' rel='stylesheet' type='text/css' />");
        writer.println("<script language=javascript>");
        writer.println("\tfunction toClose(){");
        writer.println("\t\tself.close(); return;");
        writer.println("\t}");
        writer.println("\tfunction toNext(){");
        writer.println("\t\tlocation.href=\"" + s3 + "\"; return;");
        writer.println("\t}");
        writer.println("</script>");
        writer.println("</head>");
        writer.println(" ");
        writer.println("<body>");
        writer.println("<div class='messagebox'>");
        writer.println(" ");
        writer.println("<div class='title'>");
        writer.println("<div id='title_message'> ");
        writer.println("<div id='title_name'>");
        writer.println(s);
        writer.println("</div>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println(" ");
        writer.println("<div id='message_content' align='center'>");
        writer.println(s2);
        writer.println("<br />");
        writer.println("<br />");
        writer.println("\t  <table width=100% border=0 cellspacing=0 cellpadding=0>");
        writer.println("\t    <tr> ");
        writer.println("          <td>");
        writer.println("               <input type='button' class='commonbutton' style='width: 80px' value='Có' align='absmiddle' onclick=\"javascript:toNext();\" />&nbsp;&nbsp;");
        writer.println("               <input type='button' class='commonbutton' style='width: 80px' value='Không' align='absmiddle' onclick=\"javascript:toClose();\" />");
        writer.println("           </td>");
        writer.println("       </tr>");
        writer.println("      </table>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
    
    private static void printHtml(final String s, final String s2, final String s3, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (!httpServletResponse.containsHeader("Content-Type")) {
            httpServletResponse.setContentType("text/html; charset=utf-8");
        }
        final PrintWriter writer = httpServletResponse.getWriter();
        writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        writer.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
        writer.println("<head>");
        writer.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        writer.println("<title>Thông báo</title>");
        writer.println("<link href='http://muasamcong.mpi.gov.vn/css/message.css' rel='stylesheet' type='text/css' />");
        writer.println("<link href='http://muasamcong.mpi.gov.vn/css/TA.css' rel='stylesheet' type='text/css' />");
        writer.println("<script language=javascript>");
        writer.println("\tfunction toClose(){");
        writer.println(s3);
        writer.println("\t}");
        writer.println("</script>");
        writer.println("</head>");
        writer.println(" ");
        writer.println("<body onUnload = toClose()>");
        writer.println("<div class='messagebox'>");
        writer.println(" ");
        writer.println("<div class='title'>");
        writer.println("<div id='title_message'> ");
        writer.println("<div id='title_name'>");
        writer.println(s);
        writer.println("</div>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println(" ");
        writer.println("<div id='message_content' align='center'>");
        writer.println(s2);
        writer.println("<br />");
        writer.println("<br />");
        writer.println("\t  <table width=100% border=0 cellspacing=0 cellpadding=0>");
        writer.println("\t    <tr> ");
        writer.println("          <td align='center'>");
        writer.println("               <input type='button' class='commonbutton' value='Đóng' align='absmiddle' onclick=\"javascript:toClose();\" />");
        writer.println("           </td>");
        writer.println("       </tr>");
        writer.println("      </table>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
    
    private static void printHtml2(final String s, final String s2, final String s3, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (!httpServletResponse.containsHeader("Content-Type")) {
            httpServletResponse.setContentType("text/html; charset=utf-8");
        }
        final PrintWriter writer = httpServletResponse.getWriter();
        writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        writer.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
        writer.println("<head>");
        writer.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        writer.println("<title>Thông báo</title>");
        writer.println("<link href='http://muasamcong.mpi.gov.vn/css/message.css' rel='stylesheet' type='text/css' />");
        writer.println("<link href='http://muasamcong.mpi.gov.vn/css/TA.css' rel='stylesheet' type='text/css' />");
        writer.println("<script language=javascript>");
        writer.println("\tfunction toClose(){");
        writer.println(s3);
        writer.println("\t}");
        writer.println("</script>");
        writer.println("</head>");
        writer.println(" ");
        writer.println("<body>");
        writer.println("<div class='messagebox'>");
        writer.println(" ");
        writer.println("<div class='title'>");
        writer.println("<div id='title_message'> ");
        writer.println("<div id='title_name'>");
        writer.println(s);
        writer.println("</div>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println(" ");
        writer.println("<div id='message_content' align='center'>");
        writer.println(s2);
        writer.println("<br />");
        writer.println("<br />");
        writer.println("\t  <table width=100% border=0 cellspacing=0 cellpadding=0>");
        writer.println("\t    <tr> ");
        writer.println("          <td>");
        writer.println("               <input type='button' class='commonbutton' value='Đóng' align='absmiddle' onclick=\"javascript:toClose();\" />");
        writer.println("           </td>");
        writer.println("       </tr>");
        writer.println("      </table>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
    
    private static void printHtml3(final String s, final String s2, final String s3, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (!httpServletResponse.containsHeader("Content-Type")) {
            httpServletResponse.setContentType("text/html; charset=utf-8");
        }
        final PrintWriter writer = httpServletResponse.getWriter();
        writer.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        writer.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
        writer.println("<head>");
        writer.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
        writer.println("<title>Thông báo</title>");
        writer.println("<link href='http://muasamcong.mpi.gov.vn/css/message.css' rel='stylesheet' type='text/css' />");
        writer.println("<link href='http://muasamcong.mpi.gov.vn/css/TA.css' rel='stylesheet' type='text/css' />");
        writer.println("<script language=javascript>");
        writer.println("\tfunction toClose(){");
        writer.println(s3);
        writer.println("\t}");
        writer.println("</script>");
        writer.println("</head>");
        writer.println(" ");
        writer.println("<body>");
        writer.println("<div class='messagebox'>");
        writer.println(" ");
        writer.println("<div class='title'>");
        writer.println("<div id='title_message'> ");
        writer.println("<div id='title_name'>");
        writer.println(s);
        writer.println("</div>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println(" ");
        writer.println("<div id='message_content' align='center'>");
        writer.println(s2);
        writer.println("<br />");
        writer.println("<br />");
        writer.println("\t  <table width=100% border=0 cellspacing=0 cellpadding=0>");
        writer.println("\t    <tr> ");
        writer.println("          <td>");
        writer.println("               <input type='button' class='commonbutton' value='Đóng' align='absmiddle' onclick=\"javascript:toClose();\" />");
        writer.println("           </td>");
        writer.println("       </tr>");
        writer.println("      </table>");
        writer.println("</div>");
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
