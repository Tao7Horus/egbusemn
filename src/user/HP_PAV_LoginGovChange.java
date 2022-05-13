// 
// Decompiled by Procyon v0.5.30
// 

package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import g2b.sso.SSO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class HP_PAV_LoginGovChange extends HttpServlet
{
    public void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s = new String();
        String userid;
        if ("mod".equals(httpServletRequest.getParameter("gubun"))) {
            userid = httpServletRequest.getParameter("userid");
        }
        else {
            userid = new SSO(httpServletRequest, httpServletResponse).getPersonID();
        }
        final String string = "/jsp/RA/user/HP_PAJ_LoginGovChange.jsp?userid=" + userid;
        final HP_PAB_CompUserSB hp_PAB_CompUserSB = new HP_PAB_CompUserSB();
        final HP_PAE_UserInfo hp_PAE_UserInfo = new HP_PAE_UserInfo();
        httpServletRequest.setAttribute("govEB", (Object)hp_PAB_CompUserSB.getUserEB(userid));
        httpServletRequest.getRequestDispatcher(string).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
        final HP_PAB_CompUserSB hp_PAB_CompUserSB = new HP_PAB_CompUserSB();
        final HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
        userEB.setUserid(httpServletRequest.getParameter("userid"));
        userEB.setUserpasswd(httpServletRequest.getParameter("userpasswd"));
        userEB.setUsergubun("C");
        userEB.setUsername(httpServletRequest.getParameter("username"));
        userEB.setRegistno1(httpServletRequest.getParameter("registno1"));
        userEB.setRegistno2(httpServletRequest.getParameter("registno2"));
        userEB.setUsertel1(httpServletRequest.getParameter("usertel1"));
        userEB.setUsertel2(httpServletRequest.getParameter("usertel2"));
        userEB.setUsertel3(httpServletRequest.getParameter("usertel3"));
        userEB.setUserfax1(httpServletRequest.getParameter("userfax1"));
        userEB.setUserfax2(httpServletRequest.getParameter("userfax2"));
        userEB.setUserfax3(httpServletRequest.getParameter("userfax3"));
        userEB.setUsermobile1(httpServletRequest.getParameter("usermobile1"));
        userEB.setUsermobile2(httpServletRequest.getParameter("usermobile2"));
        userEB.setUsermobile3(httpServletRequest.getParameter("usermobile3"));
        userEB.setPostno1(httpServletRequest.getParameter("postno1"));
        userEB.setPostno2(httpServletRequest.getParameter("postno2"));
        userEB.setAddress1(httpServletRequest.getParameter("address1"));
        userEB.setAddress2(httpServletRequest.getParameter("address2"));
        userEB.setUsermail(httpServletRequest.getParameter("usermail"));
        userEB.setPartname(httpServletRequest.getParameter("partname"));
        userEB.setPosition(httpServletRequest.getParameter("position"));
        userEB.setPreference(httpServletRequest.getParameter("preference"));
        final String parameter = httpServletRequest.getParameter("gubun");
        final String parameter2 = httpServletRequest.getParameter("userid");
        String s2;
        String string;
        if (hp_PAB_CompUserSB.updateUserEB(userEB)) {
            if ("mod".equals(parameter)) {
                s2 = "<b>" + httpServletRequest.getParameter("userid") + "</b>님의 정보를 <br/>성공적으로 변경하였습니다<br/><br/>";
                string = "/servlet/user.HP_PAV_LoginGovChange?gubun=mod&userid=" + parameter2;
            }
            else {
                s2 = "<b>" + httpServletRequest.getParameter("userid") + "</b>님의 정보를 <br/>성공적으로 변경하였습니다<br/><br/>";
                string = "/servlet/user.HP_PAV_LoginGovChange";
            }
        }
        else {
            s2 = "<b>" + httpServletRequest.getParameter("userid") + "</b>님의 정보 변경에 실패하였습니다.<br/>운영자에게 문의 바랍니다.<br/><br/>";
            string = "JavaScript:history.back();";
        }
        httpServletRequest.setAttribute("gubun", (Object)"g_modify");
        httpServletRequest.setAttribute("msg", (Object)s2);
        httpServletRequest.setAttribute("pass", (Object)string);
        httpServletRequest.getRequestDispatcher(s).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
}
