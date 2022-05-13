// 
// Decompiled by Procyon v0.5.30
// 

package user;

import java.util.Vector;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class HP_PAV_FindIDPW extends HttpServlet
{
    static String mailhost;
    static String from;
    
    public void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/").forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String s = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
        String s2 = "";
        String s3 = "";
        final Vector vector = new Vector();
        final HP_PAB_CompUserSB hp_PAB_CompUserSB = new HP_PAB_CompUserSB();
        final String s4 = (httpServletRequest.getParameter("searchgubun") == null) ? "" : httpServletRequest.getParameter("searchgubun");
        final String s5 = (httpServletRequest.getParameter("username") == null) ? "" : httpServletRequest.getParameter("username");
        final String registno1 = (httpServletRequest.getParameter("registno1") == null) ? "" : httpServletRequest.getParameter("registno1");
        final String registno2 = (httpServletRequest.getParameter("registno2") == null) ? "" : httpServletRequest.getParameter("registno2");
        final String s6 = (httpServletRequest.getParameter("usermail") == null) ? "" : httpServletRequest.getParameter("usermail");
        if (s4.equals("id")) {
            if (hp_PAB_CompUserSB.getUserId(s5, registno1 + registno2) == null || hp_PAB_CompUserSB.getUserId(s5, registno1 + registno2).equals("")) {
                s2 = s5 + "님은 등록되어 있지 않습니다.<br>확인 버튼을 클릭하면 이용자등록 화면으로 이동합니다.<br>";
                s3 = "javascript:history.go(-1);self.close();";
            }
            else {
                s2 = s5 + "님의 아이디는 <br/>" + "<b>" + hp_PAB_CompUserSB.getUserId(s5, registno1 + registno2) + "</b><br/>입니다.<br/><br/>";
                s3 = "javascript:movepage()";
            }
        }
        else if (s4.equals("pw")) {
            final String userId = hp_PAB_CompUserSB.getUserId(s5, registno1 + registno2);
            if (userId == null || userId.equals("")) {
                s2 = s5 + "님은 등록되어 있지 않습니다.<br>확인 버튼을 클릭하면 이용자등록 화면으로 이동합니다.<br>";
                s3 = "javascript:history.go(-1);self.close();";
            }
            else if (hp_PAB_CompUserSB.updateUserPw(userId, registno1, registno2)) {
                final StringBuffer sb = new StringBuffer();
                sb.append(s5 + "님께서 나라장터에 등록하신 아이디, 자동 변경된 임시비밀번호 정보입니다.<br>");
                sb.append("기존 ID와 임시 비밀번호로 로그인 하신 후, 나라장터 상단 사용자명(전자문서함 옆)을 클릭하여 .<br>");
                sb.append("이용자정보 수정화면에서 비밀번호변경 버튼을 눌러 원하시는 비밀번호로 변경하시기 바랍니다.<br>");
                sb.append("아이디 : " + userId + "<br/>");
                sb.append("비밀번호 : " + registno2 + "<br/><br/>");
                sb.append("발신전용 메일입니다. 문의사항은 1588-0800으로 연락하시면 정성껏 답변해 드리겠습니다.");
                s2 = s5 + "님의 메일주소 <b>" + s6 + "</b>로<br/>" + "아이디와 자동 변경된 임시비밀번호를 발송하였습니다.<br/><br/>" + "이메일로 수신된 임시비밀번호를 확인하여 로그인 하세요.<br/>" + "로그인 하신 후, 비밀번호를 변경해 주시기 바랍니다.<br/>";
                s3 = "javascript:history.go(-1);self.close();";
            }
            else {
                s2 = s5 + "님의 메일주소 <b>" + s6 + "</b>로<br/>" + "아이디와 자동 변경된 임시비밀번호가 발송되지 않았습니다.<br/><br/>" + "자세한 사항은 1588-0800으로 문의하세요.";
                s3 = "javascript:history.go(-1);self.close();";
            }
        }
        else {
            s = "/";
        }
        httpServletRequest.setAttribute("gubun", (Object)"f_find");
        httpServletRequest.setAttribute("msg", (Object)s2);
        httpServletRequest.setAttribute("pass", (Object)s3);
        httpServletRequest.getRequestDispatcher(s).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    static {
        HP_PAV_FindIDPW.mailhost = "211.42.72.40";
        HP_PAV_FindIDPW.from = "webmaster@g2b.go.kr";
    }
}
