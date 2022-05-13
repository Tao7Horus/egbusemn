// 
// Decompiled by Procyon v0.5.30
// 

package user;

import secu.lib.MessageDigest;
import secu.lib.Secu;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class HP_PAV_GovInsert extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String url = "http://muasamcong.mpi.gov.vn:8070/RA/user/HP_PAJ_GovInsert.jsp";
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String url = "http://muasamcong.mpi.gov.vn:8070/RA/user/HP_PAJ_UserMsg.jsp";
        String msg = "";
        String pass = "";
        final Secu secu = new Secu(1);
        final MessageDigest md = new MessageDigest(secu);
        String userpasswd = "";
        userpasswd = md.create(request.getParameter("userpasswd"));
        final HP_PAB_GovUserSB userSB = new HP_PAB_GovUserSB();
        final HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
        userEB.setUserid(request.getParameter("userid"));
        userEB.setUserpasswd(userpasswd);
        userEB.setUsergubun("C");
        userEB.setRefid(request.getParameter("refid"));
        userEB.setUsername(request.getParameter("username"));
        userEB.setRegistno1(request.getParameter("registno"));
        userEB.setUsertel1(request.getParameter("usertel"));
        userEB.setUserfax1(request.getParameter("userfax"));
        userEB.setUsermobile1(request.getParameter("usermobile"));
        userEB.setAddress1(request.getParameter("address"));
        userEB.setUsermail(request.getParameter("usermail"));
        userEB.setPartname(request.getParameter("userpost"));
        if (userSB.insertUserEB(userEB)) {
            msg = "ID <b>" + request.getParameter("userid") + "</b>Đã đăng ký thành công người đại diện.<br/><br/>";
            pass = "http://muasamcong.mpi.gov.vn:8070/RA/user/HP_PAJ_GovList.jsp";
        }
        else {
            msg = "Không đăng ký được người đại diện.<br/>Vui lòng hỏi quản trị để biết thêm chi tiết.<br/><br/>";
            pass = "JavaScript:history.back();";
        }
        request.setAttribute("gubun", (Object)"g_insert");
        request.setAttribute("msg", (Object)msg);
        request.setAttribute("pass", (Object)pass);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
}
