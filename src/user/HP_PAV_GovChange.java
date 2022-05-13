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

public class HP_PAV_GovChange extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String userid = new String();
        userid = request.getParameter("userid");
        final String url = "/RA/user/HP_PAJ_GovChange.jsp?userid=" + userid;
        final HP_PAB_CompUserSB govSB = new HP_PAB_CompUserSB();
        HP_PAE_UserInfo govEB = new HP_PAE_UserInfo();
        govEB = govSB.getUserEB(userid);
        request.setAttribute("govEB", (Object)govEB);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String url = "/RA/user/HP_PAJ_UserMsg.jsp";
        String msg = "";
        String pass = "";
        final HP_PAB_CompUserSB govSB = new HP_PAB_CompUserSB();
        final HP_PAE_UserInfo govEB = new HP_PAE_UserInfo();
        govEB.setUserid(request.getParameter("userid"));
        govEB.setUserpasswd(request.getParameter("userpasswd"));
        govEB.setUsergubun("C");
        govEB.setUsername(request.getParameter("username"));
        govEB.setRegistno1(request.getParameter("registno"));
        govEB.setUsertel1(request.getParameter("usertel"));
        govEB.setUserfax1(request.getParameter("userfax"));
        govEB.setUsermobile1(request.getParameter("usermobile"));
        govEB.setAddress1(request.getParameter("address"));
        govEB.setUsermail(request.getParameter("usermail"));
        govEB.setPartname(request.getParameter("userpost"));
        if (govSB.updateUserEB(govEB)) {
            msg = "<b>" + request.getParameter("userid") + "</b> Thông tin của bạn đã được thay đổi thành công<br/><br/>";
            pass = "http://muasamcong.mpi.gov.vn:8070/servlet/user.HP_PAV_GovChange.java?userid=" + request.getParameter("userid");
        }
        else {
            msg = "<b>" + request.getParameter("userid") + "</b> Thông tin của bạn chưa được thay đổi.<br/> Vui lòng hỏi quản trị.<br/><br/>";
            pass = "JavaScript:history.back();";
        }
        request.setAttribute("gubun", (Object)"g_modify");
        request.setAttribute("msg", (Object)msg);
        request.setAttribute("pass", (Object)pass);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
}
