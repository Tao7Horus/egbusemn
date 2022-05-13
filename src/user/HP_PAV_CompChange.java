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

public class HP_PAV_CompChange extends HttpServlet
{
    public void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final SSO sso = new SSO(httpServletRequest, httpServletResponse);
        String string = "/jsp/RA/user/HP_PAJ_CompChange.jsp";
        final HP_PAB_CompUserSB hp_PAB_CompUserSB = new HP_PAB_CompUserSB();
        final HP_PAE_UserInfo hp_PAE_UserInfo = new HP_PAE_UserInfo();
        if (httpServletRequest.getParameter("gubun") != null && httpServletRequest.getParameter("gubun").equals("mod")) {
            final String parameter = httpServletRequest.getParameter("userid");
            string += "?gubun=mod";
            httpServletRequest.setAttribute("compEB", (Object)hp_PAB_CompUserSB.getUserEB(parameter));
        }
        else {
            final String personID = sso.getPersonID();
            if (personID == null) {
                string = "/";
            }
            else {
                httpServletRequest.setAttribute("compEB", (Object)hp_PAB_CompUserSB.getUserEB(personID));
            }
        }
        httpServletRequest.getRequestDispatcher(string).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
        final HP_PAB_CompUserSB hp_PAB_CompUserSB = new HP_PAB_CompUserSB();
        final HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
        userEB.setUserid(httpServletRequest.getParameter("userid"));
        userEB.setUsergubun("B");
        userEB.setUserpasswd(httpServletRequest.getParameter("userpasswd"));
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
        userEB.setEnterprise(httpServletRequest.getParameter("enterprise"));
        userEB.setPartname(httpServletRequest.getParameter("partname"));
        userEB.setPartchief(httpServletRequest.getParameter("partchief"));
        userEB.setPosition(httpServletRequest.getParameter("position"));
        userEB.setEntdivide(httpServletRequest.getParameter("entdivide"));
        userEB.setCorptel1(httpServletRequest.getParameter("corptel1"));
        userEB.setCorptel2(httpServletRequest.getParameter("corptel2"));
        userEB.setCorptel3(httpServletRequest.getParameter("corptel3"));
        userEB.setDiv1(httpServletRequest.getParameter("div1"));
        userEB.setDiv2(httpServletRequest.getParameter("div2"));
        userEB.setDiv3(httpServletRequest.getParameter("div3"));
        userEB.setDiv4(httpServletRequest.getParameter("div4"));
        userEB.setGood1((httpServletRequest.getParameter("good1") == null) ? "" : httpServletRequest.getParameter("good1"));
        userEB.setGood2((httpServletRequest.getParameter("good2") == null) ? "" : httpServletRequest.getParameter("good2"));
        userEB.setGood3((httpServletRequest.getParameter("good3") == null) ? "" : httpServletRequest.getParameter("good3"));
        userEB.setGood4((httpServletRequest.getParameter("good4") == null) ? "" : httpServletRequest.getParameter("good4"));
        String string;
        String string2;
        if (hp_PAB_CompUserSB.updateUserEB(userEB)) {
            string = "<b>" + httpServletRequest.getParameter("userid") + "</b>님의 정보수정에 성공하였습니다.<br/><br/>";
            string2 = "/servlet/user.HP_PAV_CompChange.java?userid=" + httpServletRequest.getParameter("userid");
        }
        else {
            string = "비축물자 이용자 수정에 실패하였습니다.<br/>자세한 내용은 운영자에게 문의 바랍니다.<br/>";
            string2 = "JavaScript:history.back();";
        }
        if (httpServletRequest.getParameter("gubun") != null && httpServletRequest.getParameter("gubun").equals("mod")) {
            httpServletRequest.setAttribute("gubun", (Object)"p_modify");
        }
        else {
            httpServletRequest.setAttribute("gubun", (Object)"c_modify");
        }
        httpServletRequest.setAttribute("msg", (Object)string);
        httpServletRequest.setAttribute("pass", (Object)string2);
        httpServletRequest.getRequestDispatcher(s).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
}
