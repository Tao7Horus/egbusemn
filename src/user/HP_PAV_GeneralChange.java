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

public class HP_PAV_GeneralChange extends HttpServlet
{
    public void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final SSO sso = new SSO(httpServletRequest, httpServletResponse);
        String string = "/jsp/RA/user/HP_PAJ_GeneralChange.jsp";
        final String s = new String();
        final String s2 = new String();
        final HP_PAB_UserSB hp_PAB_UserSB = new HP_PAB_UserSB();
        final HP_PAE_UserInfo hp_PAE_UserInfo = new HP_PAE_UserInfo();
        if (httpServletRequest.getParameter("gubun") != null && httpServletRequest.getParameter("gubun").equals("mod")) {
            final String parameter = httpServletRequest.getParameter("userid");
            string += "?gubun=mod";
            httpServletRequest.setAttribute("userEB", (Object)hp_PAB_UserSB.getUserEB(parameter));
        }
        else {
            final String personID = sso.getPersonID();
            if (personID == null) {
                string = "/";
            }
            else {
                httpServletRequest.setAttribute("userEB", (Object)hp_PAB_UserSB.getUserEB(personID));
            }
        }
        httpServletRequest.getRequestDispatcher(string).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
        final HP_PAB_UserSB hp_PAB_UserSB = new HP_PAB_UserSB();
        final HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
        userEB.setUserid(httpServletRequest.getParameter("userid"));
        userEB.setUserpasswd(httpServletRequest.getParameter("userpasswd"));
        userEB.setUsergubun("A");
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
        userEB.setPreference(httpServletRequest.getParameter("preference"));
        userEB.setJob(httpServletRequest.getParameter("job"));
        String s2;
        String string;
        if (hp_PAB_UserSB.updateUserEB(userEB)) {
            s2 = "<b>" + httpServletRequest.getParameter("userid") + "</b>님의 정보를 <br/>성공적으로 변경하였습니다<br/><br/>";
            string = "/servlet/user.HP_PAV_GeneralChange.java?userid=" + httpServletRequest.getParameter("userid");
        }
        else {
            s2 = "<b>" + httpServletRequest.getParameter("userid") + "</b>님의 정보 변경에 실패하였습니다.<br/>운영자에게 문의 바랍니다.<br/><br/>";
            string = "JavaScript:history.back();";
        }
        if (httpServletRequest.getParameter("gubun").equals("mod")) {
            httpServletRequest.setAttribute("gubun", (Object)"m_modify");
        }
        else {
            httpServletRequest.setAttribute("gubun", (Object)"e_modify");
        }
        httpServletRequest.setAttribute("msg", (Object)s2);
        httpServletRequest.setAttribute("pass", (Object)string);
        httpServletRequest.getRequestDispatcher(s).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
}
