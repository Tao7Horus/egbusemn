// 
// Decompiled by Procyon v0.5.30
// 

package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class HP_PAV_GovPreIdInfo extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String userid = new String();
        userid = request.getParameter("userid");
        final String url = "/RA/user/HP_PAJ_GovPreIdInfo.jsp";
        final HP_PAB_GovUserSB govSB = new HP_PAB_GovUserSB();
        HP_PAE_UserInfo govEB = new HP_PAE_UserInfo();
        govEB = govSB.getUserEB(userid);
        request.setAttribute("govEB", (Object)govEB);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String url = "/RA/user/HP_PAJ_GovUserMsg.jsp";
        String msg = "";
        String pass = "";
        final HP_PAB_GovUserSB govSB = new HP_PAB_GovUserSB();
        final String userid = request.getParameter("userid");
        final String c_id = request.getParameter("c_id");
        if (govSB.updatePreIdUserEB(userid, c_id)) {
            msg = "Đã sửa thành công thông tin người đại diện cũ thành thông tin người đại diện mới.";
        }
        else {
            msg = "Sửa thông tin không thành công. Hãy đợi trong giây lát rồi thực hiện lại.";
        }
        pass = "parent.location.href='http://muasamcong.mpi.gov.vn:8070/RA/user/HP_PAJ_GovList.jsp'";
        request.setAttribute("msg", (Object)msg);
        request.setAttribute("nextaction", (Object)pass);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
}
