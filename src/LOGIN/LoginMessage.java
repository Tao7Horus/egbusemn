// 
// Decompiled by Procyon v0.5.30
// 

package LOGIN;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import common.PublicUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class LoginMessage extends HttpServlet
{
    public static final String m001 = "Thông tin IP người dùng";
    public static final String m002 = "<font color=red><b>Lỗi</b></font>";
    public static final String d000 = "";
    public static final String d001 = "Cần kiểm tra Parameter.";
    public static final String d002 = " Hãy xác nhận tính chính xác của Chứng thư số với cơ quan cấp và quản lý Chứng thư số<br>Hãy xóa modul KICA và thực hiện lại.<br>Cách xóa<br>Cách 1.Màn hình chính>Hỗ trợ khách hàng>Tư liệu chung>Sử dụng chương trình xóa<br>Cách 2.Máy tính>Start>Setting>Control Panel>Add and Remove program>Remove module Chứng nhận thông tin[SignGate]";
    public static final String d003 = "Không có session key.<br>Hãy xóa modul KICA và thực hiện lại.<br>Cách xóa<br>Cách 1.Màn hình chính>Hỗ trợ khách hàng>Tư liệu chung>Sử dụng chương trình xóa<br>Cách 2.Máy tính>Start>Setting>Control Panel>Add and Remove program>Remove module Chứng nhận thông tin[SignGate]";
    public static final String d004 = "Không có dữ liệu được mã hóa.<br>Hãy xóa modul KICA và thực hiện lại.<br>Cách xóa<br>Cách 1.Màn hình chính>Hỗ trợ khách hàng>Tư liệu chung>Sử dụng chương trình xóa<br>Cách 2.Máy tính>Start>Setting>Control Panel>Add and Remove program>Remove module Chứng nhận thông tin[SignGate]";
    public static final String d005 = "Vui lòng hỏi cơ quan cấp Chứng thư số và thay đổi xác nhận nhân thân<br>Hãy xóa modul KICA và thực hiện lại.<br>Cách xóa<br>Cách 1.Màn hình chính>Hỗ trợ khách hàng>Tư liệu chung>Sử dụng chương trình xóa<br>Cách 2.Máy tính>Start>Setting>Control Panel>Add and Remove program>Remove module Chứng nhận thông tin[SignGate]";
    public static final String d006 = "Dữ liệu giải mã đối tượng mã hóa là rỗng.<br>Hãy xóa modul KICA và thực hiện lại.<br>Cách xóa<br>Cách 1.Màn hình chính>Hỗ trợ khách hàng>Tư liệu chung>Sử dụng chương trình xóa<br>Cách 2.Máy tính>Start>Setting>Control Panel>Add and Remove program>Remove module Chứng nhận thông tin[SignGate]";
    public static final String d007 = "Là Chứng thư số không được phép sử dụng. Hãy kiểm tra lại.<br>Hãy kiểm tra các nội dung sau rồi vui lòng hỏi cơ quan cấp Chứng thư số.<br>Cách xác nhận cấp của Chứng thư số  : ";
    public static final String d008 = "Kiểm tra Chứng thư số không thành công. Đây là Chứng thư số bị hủy hoặc không được phép sử dụng.<br>Thông tin chi tiết vui lòng hỏi cơ quan cấp Chứng thư số.";
    public static final String d009 = "Là Chứng thư số hết hạn sử dụng.";
    public static final String d010 = "Không nhận được tên duy nhất Chứng thư số.";
    public static final String d011 = "Không nhận được thông tin đã đăng ký cho Chứng thư số tương ứng.<br>Hãy đăng ký Chứng thư số rồi sử dụng sau.<br>Cách đăng ký Chứng thư số  : ";
    public static final String d012 = "Chứng thư số lựa chọn đã được đăng ký<br>Nhưng chưa có Id tương ứng với Chứng thư số này.";
    public static final String d013 = "Đã login thành công, Nhưng setting thông tin người dùng tại browser thất bại.[Cookie setting]Cần kiểm tra xem setting cookie tại browser có được phép hay không.<br>Cách xác nhận và xử lý  : ";
    public static final String d014 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br>Vào trang web)<br>\t\t\t\t\t <font color='blue'>[Xác nhận nhân thân Chứng thư số ]</font>  <br>\t\t\t\t\t\t\t\t Sau đó vui lòng xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : MPI)</font>\t\t ";
    public static final String d015 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số<br><br>\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br><font color='blue'>[Xác nhận nhân thân Chứng thư số]</font>  <br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : MPI)</font>\t\t\t\t\t\t\t ";
    public static final String d016 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số<br><br>\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br><font color='blue'>[Kiểm tra xác nhận nhân thân]</font>  <br>\t\t\t\t\t\t\t\t Sau đó vui lòng xin cấp lại Chứng thư số.. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : MPI)</font>\t\t\t\t\t\t\t\t ";
    public static final String d017 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số<br><br>\t\t\t\t\t\t\t\t\t\t\t Số ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy liên hệ với người quản trị<br>\t\t\t\t\t\t<font color='blue'>[Xác nhận nhân thân Chứng thư số]</font>  <br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số. <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : MPI</font>\t\t\t\t\t\t\t\t\t\t\t\t\t";
    public static final String d018 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số<br><br>\t\t\t\t\t\t\t\t\t\t\t\t Số ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy liên hệ với người quản trị<br>\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Kiểm tra xác nhận nhân thân]</font> <br>\t\t\t\t\t\t\t\t Sau đó xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : Để giải quyết hãy liên hệ với</font>\t\t\t\t\t\t\t\t\t\t ";
    public static final String d019 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy liên hệ với người quản trị<br>\t\t\t\t\t\t\t<font color='blue'>[Kiểm tra xác nhận nhân thân]</font> <br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : Để giải quyết hãy liên hệ với</font>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
    public static final String d020 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br>Vào trang web của cơ quan thanh toán tín dụng<br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t Sau đó xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : cơ quan thanh toán tín dụng</font>\t\t\t\t\t\t\t\t\t\t ";
    public static final String d021 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy vào cơ quan thanh toán tín dụng<br>\t\t\t\t\t\t\t\t<font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : cơ quan thanh toán tín dụng</font>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
    public static final String d022 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br>Koscom(http://www.signkorea.com/)<br>\t\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t Sau đó xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : Koscom</font>\t\t\t\t\t\t\t\t\t\t\t\t ";
    public static final String d023 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br><font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : Koscom</font>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
    public static final String d024 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br>Chứng nhận thông tin<br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t Sau đó xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : Chứng nhận thông tin Hàn Quốc\t</font>\t\t\t\t\t\t\t\t\t\t ";
    public static final String d025 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy vào <br>\t\t\t\t\t\t    <font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : Chứng nhận thông tin Hàn Quốc</font>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
    public static final String d026 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br>Tại cơ quan cấp Chứng thư số<br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t Sau đó xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t ";
    public static final String d027 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy liên hệ với cơ quan cấp và quản lý Chứng thư số<br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t";
    public static final String d028 = "Chứng thư số của bạn đã bị khóa. Xin vui lòng liên hệ với bộ phận hỗ trợ khách hàng để biết chi tiết.";
    
    public static void printMessage(final String s, final String s2, final String s3, final String s4, final String s5, final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String retSpace = PublicUtil.retSpace(httpServletRequest.getParameter("SiteGubun"));
        String s6 = null;
        String s7 = null;
        String s8 = "";
        if (s4 == null) {
            s8 = "self.close(); return;";
        }
        else if (s4.equals("1")) {
            s8 = "opener.location.href=\"" + s5 + "\"; self.close(); return;";
        }
        else if (s4.equals("2")) {
            s8 = "opener.parent.location.href=\"" + s5 + "\"; self.close(); return;";
        }
        else if (s4.equals("3")) {
            s8 = "location.href=\"" + s5 + "\"; return;";
        }
        else if (s4.equals("4")) {
            s8 = "history.back(); return;";
        }
        else if (s4.equals("5")) {
            s8 = "window.opener.location.reload(); self.close(); return;";
        }
        else if (s4.equals("6")) {
            s8 = "history.back(); opener.location.href=\"" + s5 + "\"; return;";
        }
        else if (s4.equals("7")) {
            s8 = "opener.openerFunction(); self.close(); return;";
        }
        else if (s4.equals("8")) {
            s8 = "try{ opener.top.location=\"" + s5 + "\"; }catch(e){ top.location=\"" + s5 + "\";}";
        }
        if (PublicUtil.retNull(s) == null) {
            s6 = " ";
        }
        else if (s.equals("001")) {
            s6 = "Thông tin IP người dùng[IP:" + CertLocal.getIP(httpServletRequest) + "]";
        }
        else if (s.equals("002")) {
            s6 = "<font color=red><b>Lỗi</b></font>[IP:" + CertLocal.getIP(httpServletRequest) + "]";
        }
        else if (s.equals("003")) {
            s6 = "";
        }
        if (s2.equals("000")) {
            s7 = "";
        }
        else if (s2.equals("001")) {
            s7 = "Cần kiểm tra Parameter.";
        }
        else if (s2.equals("002")) {
            s7 = " Hãy xác nhận tính chính xác của Chứng thư số với cơ quan cấp và quản lý Chứng thư số<br>Hãy xóa modul KICA và thực hiện lại.<br>Cách xóa<br>Cách 1.Màn hình chính>Hỗ trợ khách hàng>Tư liệu chung>Sử dụng chương trình xóa<br>Cách 2.Máy tính>Start>Setting>Control Panel>Add and Remove program>Remove module Chứng nhận thông tin[SignGate]";
        }
        else if (s2.equals("003")) {
            s7 = "Không có session key.<br>Hãy xóa modul KICA và thực hiện lại.<br>Cách xóa<br>Cách 1.Màn hình chính>Hỗ trợ khách hàng>Tư liệu chung>Sử dụng chương trình xóa<br>Cách 2.Máy tính>Start>Setting>Control Panel>Add and Remove program>Remove module Chứng nhận thông tin[SignGate]";
        }
        else if (s2.equals("004")) {
            s7 = "Không có dữ liệu được mã hóa.<br>Hãy xóa modul KICA và thực hiện lại.<br>Cách xóa<br>Cách 1.Màn hình chính>Hỗ trợ khách hàng>Tư liệu chung>Sử dụng chương trình xóa<br>Cách 2.Máy tính>Start>Setting>Control Panel>Add and Remove program>Remove module Chứng nhận thông tin[SignGate]";
        }
        else if (s2.equals("005")) {
            s7 = "Vui lòng hỏi cơ quan cấp Chứng thư số và thay đổi xác nhận nhân thân<br>Hãy xóa modul KICA và thực hiện lại.<br>Cách xóa<br>Cách 1.Màn hình chính>Hỗ trợ khách hàng>Tư liệu chung>Sử dụng chương trình xóa<br>Cách 2.Máy tính>Start>Setting>Control Panel>Add and Remove program>Remove module Chứng nhận thông tin[SignGate]";
        }
        else if (s2.equals("006")) {
            s7 = "Dữ liệu giải mã đối tượng mã hóa là rỗng.<br>Hãy xóa modul KICA và thực hiện lại.<br>Cách xóa<br>Cách 1.Màn hình chính>Hỗ trợ khách hàng>Tư liệu chung>Sử dụng chương trình xóa<br>Cách 2.Máy tính>Start>Setting>Control Panel>Add and Remove program>Remove module Chứng nhận thông tin[SignGate]";
        }
        else if (s2.equals("007")) {
            s7 = "Là Chứng thư số không được phép sử dụng. Hãy kiểm tra lại.<br>Hãy kiểm tra các nội dung sau rồi vui lòng hỏi cơ quan cấp Chứng thư số.<br>Cách xác nhận cấp của Chứng thư số  : ";
        }
        else if (s2.equals("008")) {
            s7 = "Kiểm tra Chứng thư số không thành công. Đây là Chứng thư số bị hủy hoặc không được phép sử dụng.<br>Thông tin chi tiết vui lòng hỏi cơ quan cấp Chứng thư số.";
        }
        else if (s2.equals("009")) {
            s7 = "Là Chứng thư số hết hạn sử dụng.";
        }
        else if (s2.equals("010")) {
            s7 = "Không nhận được tên duy nhất Chứng thư số.";
        }
        else if (s2.equals("011")) {
            s7 = "Không nhận được thông tin đã đăng ký cho Chứng thư số tương ứng.<br>Hãy đăng ký Chứng thư số rồi sử dụng sau.<br>Cách đăng ký Chứng thư số  : ";
        }
        else if (s2.equals("012")) {
            s7 = "Chứng thư số lựa chọn đã được đăng ký<br>Nhưng chưa có Id tương ứng với Chứng thư số này.";
        }
        else if (s2.equals("013")) {
            s7 = "Đã login thành công, Nhưng setting thông tin người dùng tại browser thất bại.[Cookie setting]Cần kiểm tra xem setting cookie tại browser có được phép hay không.<br>Cách xác nhận và xử lý  : ";
        }
        else if (s2.equals("014")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br>Vào trang web)<br>\t\t\t\t\t <font color='blue'>[Xác nhận nhân thân Chứng thư số ]</font>  <br>\t\t\t\t\t\t\t\t Sau đó vui lòng xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : MPI)</font>\t\t ";
        }
        else if (s2.equals("015")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số<br><br>\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br><font color='blue'>[Xác nhận nhân thân Chứng thư số]</font>  <br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : MPI)</font>\t\t\t\t\t\t\t ";
        }
        else if (s2.equals("016")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số<br><br>\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br><font color='blue'>[Kiểm tra xác nhận nhân thân]</font>  <br>\t\t\t\t\t\t\t\t Sau đó vui lòng xin cấp lại Chứng thư số.. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : MPI)</font>\t\t\t\t\t\t\t\t ";
        }
        else if (s2.equals("017")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số<br><br>\t\t\t\t\t\t\t\t\t\t\t Số ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy liên hệ với người quản trị<br>\t\t\t\t\t\t<font color='blue'>[Xác nhận nhân thân Chứng thư số]</font>  <br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số. <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : MPI</font>\t\t\t\t\t\t\t\t\t\t\t\t\t";
        }
        else if (s2.equals("018")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số<br><br>\t\t\t\t\t\t\t\t\t\t\t\t Số ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy liên hệ với người quản trị<br>\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Kiểm tra xác nhận nhân thân]</font> <br>\t\t\t\t\t\t\t\t Sau đó xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : Để giải quyết hãy liên hệ với</font>\t\t\t\t\t\t\t\t\t\t ";
        }
        else if (s2.equals("019")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy liên hệ với người quản trị<br>\t\t\t\t\t\t\t<font color='blue'>[Kiểm tra xác nhận nhân thân]</font> <br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : Để giải quyết hãy liên hệ với</font>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        }
        else if (s2.equals("020")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br>Vào trang web của cơ quan thanh toán tín dụng<br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t Sau đó xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : cơ quan thanh toán tín dụng</font>\t\t\t\t\t\t\t\t\t\t ";
        }
        else if (s2.equals("021")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy vào cơ quan thanh toán tín dụng<br>\t\t\t\t\t\t\t\t<font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : cơ quan thanh toán tín dụng</font>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        }
        else if (s2.equals("022")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br>Koscom(http://www.signkorea.com/)<br>\t\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t Sau đó xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : Koscom</font>\t\t\t\t\t\t\t\t\t\t\t\t ";
        }
        else if (s2.equals("023")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br><font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : Koscom</font>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        }
        else if (s2.equals("024")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br>Chứng nhận thông tin<br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t Sau đó xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Cơ quan cấp] : Chứng nhận thông tin Hàn Quốc\t</font>\t\t\t\t\t\t\t\t\t\t ";
        }
        else if (s2.equals("025")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy vào <br>\t\t\t\t\t\t    <font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Cơ quan cấp] : Chứng nhận thông tin Hàn Quốc</font>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        }
        else if (s2.equals("026")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t Không có thông tin xác nhận nhân thân trong Chứng thư số đang dùng.<br>Tại cơ quan cấp Chứng thư số<br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t <font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t Sau đó xin cấp lại Chứng thư số. <br>\t\t\t\t\t\t\t\t\t\t\t ";
        }
        else if (s2.equals("027")) {
            s7 = "Hướng dẫn đối với trường hợp thất bại kiểm tra thông tin Chứng thư số <br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSố ĐKKD đăng ký trên web Đấu thầu và số ĐKKD nhập khi xin cấp Chứng thư số không giống nhau. <br>Để giải quyết hãy liên hệ với cơ quan cấp và quản lý Chứng thư số<br>\t\t\t\t\t\t\t\t\t\t\t\t\t<font color='blue'>[Kiểm tra xác nhận nhân thân]</font><br>\t\t\t\t\t\t\t\t\t\t\t\t\tSau đó xin cấp lại Chứng thư số.<br><br>\t\t\t\t\t\t\t\t\t\t\t\t\t";
        }
        else if (s2.equals("028")) {
            s7 = "Chứng thư số của bạn đã bị khóa. Xin vui lòng liên hệ với bộ phận hỗ trợ khách hàng để biết chi tiết.";
        }
        if (retSpace.equals("mainLoginCertLogin") || retSpace.equals("mainLoginIDLogin")) {
            printHtmlOpenPopup(s6, s7, s3, retSpace, httpServletResponse);
        }
        else {
            printHtml(s6, s7, s3, s8, httpServletResponse);
        }
    }
    
    private static void printHtmlOpenPopup(final String s, final String s2, final String s3, final String s4, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (!httpServletResponse.containsHeader("Content-Type")) {
            httpServletResponse.setContentType("text/html; charset=UTF-8");
        }
        final PrintWriter writer = httpServletResponse.getWriter();
        writer.println("<html>");
        writer.println("<head><title>koneps</title>");
        writer.println("<script language=\"javascript\">");
        writer.println("\tfunction toInit(){");
        writer.println("\t\twindow.open(\"\",'infoWin','status=yes,resizable=yes,scrollbars=no,toolbar=no,width=400,height=300');");
        writer.println("\t\tdocument.mainFrom.target=\"infoWin\";");
        writer.println("        document.mainFrom.method=\"post\";");
        writer.println("        document.mainFrom.action=\"/LoginInfo.jsp\";");
        writer.println("        document.mainFrom.submit();");
        writer.println("        window.location=\"http://www.g2b.go.kr:8070/loginMain.jsp?SiteGubun=" + s4 + "\";");
        writer.println("\t}");
        writer.println("</script>");
        writer.println("</head>");
        writer.println("<body onload=\"javascript:toInit()\">");
        writer.println("<form name=\"mainFrom\">");
        writer.println("<textarea name=\"message\" rows=\"1\" cols=\"1\">" + s + "</textarea>");
        writer.println("<textarea name=\"dispmsg\" rows=\"1\" cols=\"1\">" + s2 + "</textarea>");
        writer.println("<textarea name=\"addMessage\" rows=\"1\" cols=\"1\">" + s3 + "</textarea>");
        writer.println("</form>");
        writer.println("</body>");
        writer.println("</html>");
    }
    
    private static void printHtml(final String s, final String s2, final String s3, final String s4, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (!httpServletResponse.containsHeader("Content-Type")) {
            httpServletResponse.setContentType("text/html; charset=UTF-8");
        }
        final PrintWriter writer = httpServletResponse.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Message</title>");
        writer.println("<link rel=stylesheet type=text/css href=http://muasamcong.mpi.gov.vn/css/EP.css>");
        writer.println("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>");
        writer.println("<script language=javascript>");
        writer.println("\tfunction toClose(){");
        writer.println(s4);
        writer.println("\t}");
        writer.println("</script>");
        writer.println("</head>");
        writer.println("<body bgcolor=#FFFFFF text=#000000 topmargin=0 leftmargin=0 marginwidth=0 marginheight=0>");
        writer.println("<table width=815 height=100% border=0 cellspacing=0 cellpadding=0> ");
        writer.println("  <tr>");
        writer.println("    <td height=83>");
        writer.println("\t  <table width=100% border=0 cellspacing=0 cellpadding=0>");
        writer.println("\t    <tr> ");
        writer.println("          <td width=144 height=83><img src=http://muasamcong.mpi.gov.vn/img/top_title_01.jpg></td>");
        writer.println("          <td background=/img/top_title_02.jpg width=* align=right> ");
        writer.println("            <table width=150 border=0 cellspacing=0 cellpadding=0>");
        writer.println("              <tr> ");
        writer.println("                <td height=31>&nbsp;</td>");
        writer.println("              </tr>");
        writer.println("              <tr> ");
        writer.println("                <td height=21 align=right><img src=http://muasamcong.mpi.gov.vn/img/top_title_user.jpg></td>");
        writer.println("              </tr>");
        writer.println("              <tr> ");
        writer.println("                <td height=31>&nbsp;</td>");
        writer.println("              </tr>");
        writer.println("            </table>");
        writer.println("          </td>");
        writer.println("          <td width=45><img src=http://muasamcong.mpi.gov.vn/img/top_title_03.jpg></td>");
        writer.println("        </tr>");
        writer.println("      </table>");
        writer.println("     </td>");
        writer.println("  </tr> ");
        writer.println("  <tr>");
        writer.println("    <td height=* valign=top>");
        writer.println("\t  <table width=100% border=0 cellspacing=0 cellpadding=0 height=100%>");
        writer.println("        <tr> ");
        writer.println("          <td background=/img/top_title_04.jpg width=31 rowspan=3>&nbsp;</td>");
        writer.println("          <td width=20 valign=top rowspan=3>&nbsp;</td>");
        writer.println("          <td width=* valign=top><table width=100% border=0>");
        writer.println("              <tr>");
        writer.println("                <td class=fontbluec>" + s + "</td>");
        writer.println("              </tr>");
        writer.println("              <tr>");
        writer.println("                <td class=red>" + s2 + "<br><br>" + s3 + "</td>");
        writer.println("              </tr>");
        writer.println("              <tr>");
        writer.println("                <td class=redc>&nbsp;</td>");
        writer.println("              </tr>");
        writer.println("              <tr>");
        writer.println("          <td><div align=center><a href=\"javascript:toClose();\"><img src=http://muasamcong.mpi.gov.vn:8070/img/bu_close.gif align=absmiddle border=0></div ></a></td>");
        writer.println("              </tr>");
        writer.println("            </table></td>");
        writer.println("          <td width=30 background=/img/top_title_04b.jpg rowspan=3>&nbsp;</td>");
        writer.println("        </tr>");
        writer.println("      </table>");
        writer.println("    </td>");
        writer.println("  </tr>");
        writer.println("  ");
        writer.println("  <tr>");
        writer.println("    <td height=60 valign=bottom>");
        writer.println("\t  <table width=100% border=0 cellspacing=0 cellpadding=0>");
        writer.println("        <tr>");
        writer.println("          <td width=35 height=60><img src=http://muasamcong.mpi.gov.vn/img/top_title_05.jpg></td>");
        writer.println("          <td background=/img/top_title_06.jpg width=*>&nbsp;</td>");
        writer.println("          <td width=295 ><img src=http://muasamcong.mpi.gov.vn/img/top_title_07.jpg></td>");
        writer.println("        </tr>");
        writer.println("      </table>");
        writer.println("\t</td>");
        writer.println("  </tr>");
        writer.println("</table>");
        writer.println("");
        writer.println("</body>");
        writer.println("</html>");
    }
    
    private static void printMessage(final String s, final String s2, final String s3, final String s4, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        if (!httpServletResponse.containsHeader("Content-Type")) {
            httpServletResponse.setContentType("text/html; charset=UTF-8");
        }
        final PrintWriter writer = httpServletResponse.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>메세지</title>");
        writer.println("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>");
        writer.println("<style type=\"text/css\">");
        writer.println(".butt { font-family: arial,helvetica,sans-serif; font-size: 11px;height: 18px; border: 1px solid; #CCCCCC }");
        writer.println(".red   {  font-family: \"arial\"; font-size: 9pt; font-style: normal; text-align: left; line-height: 14pt; text-decoration: none; color: #CA0404}");
        writer.println(".fontbluec {  font-family: \"arial\"; font-size: 10pt; font-style: normal; line-height: 14pt; font-weight: bold; text-align: center;  vertical-align: bottom; text-decoration: none; color: #0000CD}");
        writer.println(".titleMessage {  font-family: \"arial\"; font-size: 13pt; font-style: normal; line-height: 14pt; font-weight: bold; text-align: left;  vertical-align: bottom; text-decoration: none; color: #00008C}");
        writer.println(".line_or    { background-color: #DBB387; }");
        writer.println("</style>");
        writer.println("<script language=javascript>");
        writer.println("   function toClose(){");
        writer.println(s4);
        writer.println("   }");
        writer.println("</script>");
        writer.println("</head>");
        writer.println("<body bgcolor=#FFFFFF text=#000000 topmargin=0 leftmargin=0 marginwidth=0 marginheight=0>");
        writer.println("<table width=100% height=100% border=0 cellspacing=0 cellpadding=0> ");
        writer.println("  <tr>");
        writer.println("    <td width=2%>&nbsp;&nbsp;</td>");
        writer.println("    <td height=40 class=titleMessage>::: MESSAGE :::</td>");
        writer.println("  </tr>");
        writer.println("  <tr height=1><td colspan=2 class=\"line_or\"></td></tr>");
        writer.println("  <tr>");
        writer.println("    <td width=2%>&nbsp;&nbsp;</td>");
        writer.println("    <td height=* valign=top>");
        writer.println("     <table width=100% border=0 cellspacing=0 cellpadding=0 height=100%>");
        writer.println("        <tr> ");
        writer.println("          <td width=10 rowspan=3>&nbsp;</td>");
        writer.println("          <td width=10 valign=top rowspan=3>&nbsp;</td>");
        writer.println("          <td width=* valign=top><table width=100% border=0>");
        writer.println("              <tr>");
        writer.println("                <td class=fontbluec>&nbsp;" + s + "</td>");
        writer.println("              </tr>");
        writer.println("              <tr>");
        writer.println("                <td class=red>" + s2 + "<br><br>" + s3 + "</td>");
        writer.println("              </tr>");
        writer.println("              <tr>");
        writer.println("                <td class=redc>&nbsp;</td>");
        writer.println("              </tr>");
        writer.println("              <tr>");
        writer.println("          \t\t<td align=center>");
        writer.println("          \t\t\t<input type=\"button\" class=\"butt\" value=\"Đóng\" onclick=\"javascript:toClose();\" >");
        writer.println("          \t\t</td>");
        writer.println("              </tr>");
        writer.println("            </table></td>");
        writer.println("          <td width=30 rowspan=3>&nbsp;</td>");
        writer.println("        </tr>");
        writer.println("      </table>");
        writer.println("    </td>");
        writer.println("  </tr>");
        writer.println("  <tr>");
        writer.println("    <td width=2%>&nbsp;&nbsp;</td>");
        writer.println("    <td height=60 valign=bottom colspan=2></td>");
        writer.println("  </tr>");
        writer.println("</table>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
