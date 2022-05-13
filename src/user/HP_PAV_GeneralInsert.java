// 
// Decompiled by Procyon v0.5.30
// 

package user;

import secu.lib.MessageDigest;
import secu.lib.Secu;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class HP_PAV_GeneralInsert extends HttpServlet
{
    public void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/jsp/RA/user/HP_PAJ_GeneralInsert.jsp").forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
        final String create = new MessageDigest(new Secu(1)).create(httpServletRequest.getParameter("userpasswd"));
        final HP_PAB_UserSB hp_PAB_UserSB = new HP_PAB_UserSB();
        final HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
        userEB.setUserid(httpServletRequest.getParameter("userid"));
        userEB.setUserpasswd(create);
        userEB.setUsergubun("A");
        userEB.setRefid(httpServletRequest.getParameter("refid"));
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
        userEB.setJob(httpServletRequest.getParameter("job2"));
        String string;
        String s2;
        if (hp_PAB_UserSB.insertUserEB(userEB)) {
            string = "아이디<b>" + httpServletRequest.getParameter("userid") + "</b>로<br>이용자 등록에 성공하였습니다.<br/><br/>" + "'확인' 버튼을 클릭하시고 <br/>로그인하여 서비스를 이용하시기 바랍니다.<br/><br/>";
            s2 = "http://www.g2b.go.kr/ target=_top";
        }
        else {
            string = "이용자 등록에 실패하였습니다.<br/>자세한 점은 운영자에게 문의 바랍니다.<br/><br/>";
            s2 = "JavaScript:history.back();";
        }
        httpServletRequest.setAttribute("gubun", (Object)"e_insert");
        httpServletRequest.setAttribute("msg", (Object)string);
        httpServletRequest.setAttribute("pass", (Object)s2);
        httpServletRequest.getRequestDispatcher(s).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
}
