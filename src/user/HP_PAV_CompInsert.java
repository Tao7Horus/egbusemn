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

public class HP_PAV_CompInsert extends HttpServlet
{
    public void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/jsp/RA/user/HP_PAJ_CompInsert.jsp").forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String s = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
        final String create = new MessageDigest(new Secu(1)).create(httpServletRequest.getParameter("userpasswd"));
        final HP_PAB_CompUserSB hp_PAB_CompUserSB = new HP_PAB_CompUserSB();
        final HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
        userEB.setUserid(httpServletRequest.getParameter("userid"));
        userEB.setUserpasswd(create);
        userEB.setUsergubun("B");
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
        userEB.setEntdivide(httpServletRequest.getParameter("entdivide"));
        userEB.setEnterprise(httpServletRequest.getParameter("enterprise"));
        userEB.setBusinessno1(httpServletRequest.getParameter("businessno1"));
        userEB.setBusinessno2(httpServletRequest.getParameter("businessno2"));
        userEB.setBusinessno3(httpServletRequest.getParameter("businessno3"));
        userEB.setPartname(httpServletRequest.getParameter("partname"));
        userEB.setPartchief(httpServletRequest.getParameter("partchief"));
        userEB.setPosition(httpServletRequest.getParameter("position"));
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
        userEB.setRecognition("Y");
        String string;
        String s2;
        if (hp_PAB_CompUserSB.insertUserEB(userEB)) {
            string = "아이디<b>" + httpServletRequest.getParameter("userid") + "</b>로<br/>비축물자 이용자 등록에 성공하였습니다.<br/><br/>" + "'확인' 버튼을 클릭하시고 <br/>로그인하여 서비스를 이용하시기 바랍니다.<br/><br/>";
            s2 = "http://www.g2b.go.kr/ target=_top";
        }
        else {
            string = "비축물자 이용자 등록에 실패하였습니다.\n\n";
            s2 = "JavaScript:history.back();";
        }
        httpServletRequest.setAttribute("gubun", (Object)"c_insert");
        httpServletRequest.setAttribute("msg", (Object)string);
        httpServletRequest.setAttribute("pass", (Object)s2);
        httpServletRequest.getRequestDispatcher(s).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
}
