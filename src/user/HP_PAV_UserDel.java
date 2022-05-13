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

public class HP_PAV_UserDel extends HttpServlet
{
    static String mailhost;
    static String from;
    
    static {
        HP_PAV_UserDel.mailhost = "211.42.72.40";
        HP_PAV_UserDel.from = "webmaster@g2b.go.kr";
    }
    
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String userid = request.getParameter("userid");
        String msg = new String();
        String pass = new String();
        String url = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
        if (userid.equals("") || userid == null) {
            url = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
            msg = String.valueOf(userid) + "님의 정보 삭제에 실패하였습니다.<br/>다시 로그인 하고 삭제 바랍니다.<br/><br/>";
            pass = "JavaScript:history.back()";
        }
        else {
            final HP_PAB_UserSB userSB = new HP_PAB_UserSB();
            final HP_PAE_UserInfo userEB = userSB.getUserEB(userid);
            if (userSB.deleteUser(userid)) {
                final StringBuffer mesg = new StringBuffer();
                msg = String.valueOf(userEB.getUsername()) + "(" + userid + ")님의 정보를 성공적으로 삭제하고<br/>등록된 메일주소(" + userEB.getUsermail() + ")로<br>삭제에 관련된 정보를 발송하였습니다.<br><br>";
            }
            pass = "javascript:if(opener!=null){self.close();}else{window.location.href=\"/jsp/common/logout.jsp\";}";
        }
        request.setAttribute("gubun", (Object)"l_delete");
        request.setAttribute("msg", (Object)msg);
        request.setAttribute("pass", (Object)pass);
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
    
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String userid = new String();
        final String userpasswd = new String();
        final String url = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
        String msg = new String();
        String pass = new String();
        final String deletegubun = (request.getParameter("deletegubun") == null) ? "" : request.getParameter("deletegubun");
        final String deletegubun2 = (request.getParameter("deletegubun2") == null) ? "" : request.getParameter("deletegubun2");
        final String deleteIDs = (request.getParameter("deleteID") == null) ? "" : request.getParameter("deleteID");
        final HP_PAB_UserSB userSB = new HP_PAB_UserSB();
        final HP_PAE_UserInfo userEB = null;
        final StringBuffer mesg = null;
        if (deletegubun.equals("list")) {
            msg = "이용자 정보를 성공적으로 삭제하고<br/> 이용자 정보에 등록된 메일주소로<br/> 삭제에 관련된 정보를 발송 하였습니다.<br>";
            if (deletegubun2.equals("company")) {
                pass = "/servlet/user.HP_PAV_CompUserView";
            }
            else if (deletegubun2.equals("common")) {
                pass = "/servlet/user.HP_PAV_UserView";
            }
            else {
                msg = "이용자 정보 삭제에 실패하였습니다.<br/><br/>";
                pass = "JavaScript:history.back()";
            }
            request.setAttribute("gubun", (Object)"l_delete");
            request.setAttribute("msg", (Object)msg);
            request.setAttribute("pass", (Object)pass);
        }
        final RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
}
