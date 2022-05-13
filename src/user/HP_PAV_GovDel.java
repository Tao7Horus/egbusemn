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

public class HP_PAV_GovDel extends HttpServlet
{
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String userid = request.getParameter("userid");
        final String gubun = request.getParameter("gubun");
        System.out.println("gubun : " + gubun);
        String msg = new String();
        String pass = new String();
        final String url = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
        if (userid.equals("") || userid == null) {
            msg = "선택된 아이디 정보가 없습니다.<br/><br/>";
            pass = "JavaScript:history.back()";
        }
        else {
            final HP_PAB_UserSB userSB = new HP_PAB_UserSB();
            if (userSB.deleteUser(userid)) {
                if ("mod".equals(gubun)) {
                    msg = "삭제되었습니다.<br><br>";
                    pass = "javascript:self.close();";
                }
                else if ("".equals(gubun)) {
                    msg = "탈퇴되었습니다.<br><br>";
                    pass = "javascript:window.location.href=\"/jsp/common/logout.jsp\";";
                }
                else {
                    msg = "선택하신 아이디가 해지되었습니다.<br><br>";
                    pass = "/jsp/RA/user/HP_PAJ_GovList.jsp";
                }
            }
            else {
                msg = "아이디가 삭제되지 않았습니다.<br> 잠시후 다시 실행하세요.<br/><br/>";
                pass = "JavaScript:history.back()";
            }
        }
        request.setAttribute("gubun", (Object)"g_delete");
        request.setAttribute("msg", (Object)msg);
        request.setAttribute("pass", (Object)pass);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String userid = request.getParameter("userid");
        String msg = new String();
        String pass = new String();
        final String url = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
        if (userid.equals("") || userid == null) {
            msg = "선택된 아이디 정보가 없습니다.<br/><br/>";
            pass = "JavaScript:history.back()";
        }
        else {
            final HP_PAB_UserSB userSB = new HP_PAB_UserSB();
            if (userSB.deleteUser(userid)) {
                msg = "선택하시 아이디가 해지되었습니다.<br><br>";
                pass = "/jsp/RA/user/HP_PAJ_GovList.jsp";
            }
            else {
                msg = "아이디가 삭제되지 않았습니다.<br> 잠시후 다시 실행하세요.<br/><br/>";
                pass = "JavaScript:history.back()";
            }
        }
        request.setAttribute("gubun", (Object)"g_delete");
        request.setAttribute("msg", (Object)msg);
        request.setAttribute("pass", (Object)pass);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
}
