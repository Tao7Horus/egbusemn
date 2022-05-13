// 
// Decompiled by Procyon v0.5.30
// 

package user;

import common.NameCheck;
import java.util.StringTokenizer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class HP_PAV_NameCheck extends HttpServlet
{
    private static String SITEID;
    private static String SITEPW;
    String msg;
    private static String numcheck;
    String chkValue;
    
    public HP_PAV_NameCheck() {
        this.msg = "";
        this.chkValue = "";
    }
    
    public void doGet(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("/").forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    public static boolean isHangul(final char c) {
        return isHangulSyllables(c) || isHangulJamo(c) || isHangulCompatibilityJamo(c);
    }
    
    public static boolean isHangulSyllables(final char c) {
        return c >= '가' && c <= '힣';
    }
    
    public static boolean isHangulJamo(final char c) {
        return (c >= 'ᄀ' && c <= 'ᅙ') || (c >= 'ᅡ' && c <= 'ᆢ') || (c >= 'ᆨ' && c <= 'ᇹ');
    }
    
    public static boolean isHangulCompatibilityJamo(final char c) {
        return c >= 'ㄱ' && c <= 'ㆎ';
    }
    
    public boolean Check_Val(final String s, final String s2, final String s3) {
        if ("".equals(s)) {
            this.msg = "이름이 입력되어 있지 않습니다!!<br/>";
            return false;
        }
        if (!"".equals(s)) {
            for (int i = 0; i < s.length(); ++i) {
                if (!isHangul(s.charAt(i))) {
                    this.msg = "이름은 한글 이외에는 입력하실수 없습니다.!!<br/>";
                    return false;
                }
            }
        }
        if ("".equals(s2)) {
            this.msg = "주민등록번호 앞자리가 입력되어 있지 않습니다!!<br/>";
            return false;
        }
        if (!"".equals(s2)) {
            if (s2.length() < 6) {
                this.msg = "주민등록번호 앞자리가 제대로 입력되어 있지 않습니다!!<br/>";
                return false;
            }
            for (int j = 0; j < s2.length(); ++j) {
                if (HP_PAV_NameCheck.numcheck.indexOf(s2.charAt(j)) == -1) {
                    this.msg = "주민등록번호 앞자리에 숫자가 아닌 다른 것이 포함되어 있습니다.!!<br/>";
                    return false;
                }
            }
        }
        if ("".equals(s3)) {
            this.msg = "주민등록번호 뒷자리가 입력되어 있지 않습니다!!<br/>";
            return false;
        }
        if (!"".equals(s3)) {
            if (s3.length() < 7) {
                this.msg = "주민등록번호 뒷자리가 제대로 입력되어 있지 않습니다!!<br/>";
                return false;
            }
            for (int k = 0; k < s3.length(); ++k) {
                if (HP_PAV_NameCheck.numcheck.indexOf(s3.charAt(k)) == -1) {
                    this.msg = "주민등록번호 뒷자리에 숫자가 아닌 다른 것이 포함되어 있습니다.!!<br/>";
                    return false;
                }
            }
        }
        return true;
    }
    
    public void doPost(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final HP_PAB_UserSB hp_PAB_UserSB = new HP_PAB_UserSB();
        final String s = new String();
        String parameter = httpServletRequest.getParameter("url");
        final String s2 = new String();
        final String s3 = new String();
        final String s4 = new String();
        final String chkName = (httpServletRequest.getParameter("username") == null) ? "" : httpServletRequest.getParameter("username");
        final String s5 = (httpServletRequest.getParameter("registno1") == null) ? "" : httpServletRequest.getParameter("registno1");
        final String s6 = (httpServletRequest.getParameter("registno2") == null) ? "" : httpServletRequest.getParameter("registno2");
        if (!this.Check_Val(chkName, s5, s6)) {
            this.chkValue = "1";
        }
        else {
            this.chkValue = "";
        }
        if (!"".equals(this.chkValue)) {
            this.msg += "다시 한 번 확인 후 정확히 입력하세요.";
            final String s7 = "JavaScript:history.back();";
            parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
            httpServletRequest.setAttribute("pass", (Object)s7);
            httpServletRequest.setAttribute("msg", (Object)this.msg);
            httpServletRequest.setAttribute("gubun", (Object)"namecheck");
        }
        else if (hp_PAB_UserSB.isExistRegistno(s5 + s6)) {
            final StringTokenizer stringTokenizer = new StringTokenizer(hp_PAB_UserSB.isExistRegistnoDash(s5 + s6), "/");
            final String nextToken = stringTokenizer.nextToken();
            if (!stringTokenizer.nextToken().equals("Y")) {
                this.msg = "실명 인증 받기 위해 입력하신 주민등록번호 <br/>" + s5 + "-" + s6 + "는 아이디 " + nextToken + "으로<br/>" + "이미 가입되어 있는 주민등록번호 입니다.<br/><br/>" + "자세한 내용은 운영자에게 문의 바랍니다.";
            }
            else {
                this.msg = "실명 인증 받기 위해 입력하신 주민등록번호 <br/>" + s5 + "-" + s6 + "는 탈퇴하신 계정의<br/>" + "주민등록번호 입니다.<br/><br/>" + "다시 사용을 원하시거나<br/>자세한 내용은 운영자에게 문의 바랍니다.";
            }
            final String s8 = "JavaScript:history.back();";
            parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
            httpServletRequest.setAttribute("pass", (Object)s8);
            httpServletRequest.setAttribute("msg", (Object)this.msg);
            httpServletRequest.setAttribute("gubun", (Object)"namecheck");
        }
        else {
            try {
                final NameCheck nameCheck = new NameCheck();
                nameCheck.setChkName(chkName);
                String s9 = nameCheck.setJumin(s5 + s6 + HP_PAV_NameCheck.SITEPW);
                if ("0".equals(s9)) {
                    nameCheck.setSiteCode(HP_PAV_NameCheck.SITEID);
                    nameCheck.setTimeOut(30000);
                    s9 = nameCheck.getRtn().trim();
                }
                if ("1".equals(s9)) {
                    this.msg = "";
                }
                else if ("5".equals(s9)) {
                    this.msg = "고객님이 입력하신 주민등록번호가 잘못 입력되었습니다.(주민번호 체크 썸 오류)<br>확인 후 다시 한 번 입력하세요.";
                    final String s10 = "JavaScript:history.back();";
                    parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                    httpServletRequest.setAttribute("pass", (Object)s10);
                    httpServletRequest.setAttribute("msg", (Object)this.msg);
                    httpServletRequest.setAttribute("gubun", (Object)"namecheck");
                }
                else if ("2".equals(s9) || "3".equals(s9)) {
                    this.msg = "고객님이 입력하신 성함과 주민번호가 일치하지 않거나 자료가 없습니다. <br>자신이 정확한 이름과 주민번호를 입력했음에도 불구하고 이름과 주민번호가 <br>일치하지 않는다고 메시지가 나오면 <br>한국신용평가 콜센터에서 실명확인 등록신청을 해주시기 바랍니다. <br><br><a href=\"http://www.namecheck.co.kr/per_callcenter.asp\" target=\"nmcheck\">[한국신용평가 콜센터 바로가기]</a>";
                    final String s11 = "JavaScript:history.back();";
                    parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                    httpServletRequest.setAttribute("pass", (Object)s11);
                    httpServletRequest.setAttribute("msg", (Object)this.msg);
                    httpServletRequest.setAttribute("gubun", (Object)"namecheck");
                }
                else if ("4".equals(s9)) {
                    this.msg = "귀사의 네트워크. <br>방화벽이 설치되어 있으실경우 저희쪽 서버 IP,Port를 등록해 주지 않으셨을 경우에도 발생합니다.<br>네트워크 확인해 주시고 문의 주십시오.<br><br><br><a href=\"http://www.namecheck.co.kr/per_callcenter.asp\" target=\"nmcheck\">[한국신용평가 콜센터 바로가기]</a>";
                    final String s12 = "JavaScript:history.back();";
                    parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                    httpServletRequest.setAttribute("pass", (Object)s12);
                    httpServletRequest.setAttribute("msg", (Object)this.msg);
                    httpServletRequest.setAttribute("gubun", (Object)"namecheck");
                }
                else if ("6".equals(s9)) {
                    this.msg = "성인인증시 만19세 이하인 경우 실명인증 거치지 않고 바로 리턴코드 출력됩니다. <br>(실명확인 서비스 신청시 서비스성격이 성인인증인 업체에만 해당됩니다.)  확인 후 다시 한 번 입력하세요.<br><br><br><a href=\"http://www.namecheck.co.kr/per_callcenter.asp\" target=\"nmcheck\">[한국신용평가 콜센터 바로가기]</a>";
                    final String s13 = "JavaScript:history.back();";
                    parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                    httpServletRequest.setAttribute("pass", (Object)s13);
                    httpServletRequest.setAttribute("msg", (Object)this.msg);
                    httpServletRequest.setAttribute("gubun", (Object)"namecheck");
                }
                else if ("21".equals(s9)) {
                    this.msg = "암호화 데이타 이상<br>( 주민번호(13), 비밀번호(8) 자릿수를 확인해 주세요.)<br>  확인 후 다시 한 번 입력하세요.<br><br><br><a href=\"http://www.namecheck.co.kr/per_callcenter.asp\" target=\"nmcheck\">[한국신용평가 콜센터 바로가기]</a>";
                    final String s14 = "JavaScript:history.back();";
                    parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                    httpServletRequest.setAttribute("pass", (Object)s14);
                    httpServletRequest.setAttribute("msg", (Object)this.msg);
                    httpServletRequest.setAttribute("gubun", (Object)"namecheck");
                }
                else if ("24".equals(s9)) {
                    this.msg = "암호화 연산중 에러<br>(올바르지 않은 주민번호인지 확인해 주세요.)<br>  확인 후 다시 한 번 입력하세요.<br><br><br><a href=\"http://www.namecheck.co.kr/per_callcenter.asp\" target=\"nmcheck\">[한국신용평가 콜센터 바로가기]</a>";
                    final String s15 = "JavaScript:history.back();";
                    parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                    httpServletRequest.setAttribute("pass", (Object)s15);
                    httpServletRequest.setAttribute("msg", (Object)this.msg);
                    httpServletRequest.setAttribute("gubun", (Object)"namecheck");
                }
                else if ("50".equals(s9)) {
                    this.msg = "정보도용 차단 요청 주민번호<br>(실명확인 요청시 성명 일치/불일치에 관계없이 결과값으로 50 을 리턴)<br><br><br><a href=\"http://www.namecheck.co.kr/per_callcenter.asp\" target=\"nmcheck\">[한국신용평가 콜센터 바로가기]</a>";
                    final String s16 = "JavaScript:history.back();";
                    parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                    httpServletRequest.setAttribute("pass", (Object)s16);
                    httpServletRequest.setAttribute("msg", (Object)this.msg);
                    httpServletRequest.setAttribute("gubun", (Object)"namecheck");
                }
                else if ("55".equals(s9) || "56".equals(s9) || "57".equals(s9)) {
                    this.msg = "외국인 번호 확인 오류 <br>  확인 후 다시 한 번 입력하세요.<br><br><br><a href=\"http://www.namecheck.co.kr/per_callcenter.asp\" target=\"nmcheck\">[한국신용평가 콜센터 바로가기]</a>";
                    final String s17 = "JavaScript:history.back();";
                    parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                    httpServletRequest.setAttribute("pass", (Object)s17);
                    httpServletRequest.setAttribute("msg", (Object)this.msg);
                    httpServletRequest.setAttribute("gubun", (Object)"namecheck");
                }
                else if ("58".equals(s9)) {
                    this.msg = "출입국 관리소 통신 오류 <br>  확인 후 다시 한 번 입력하세요.<br><br><br><a href=\"http://www.namecheck.co.kr/per_callcenter.asp\" target=\"nmcheck\">[한국신용평가 콜센터 바로가기]</a>";
                    final String s18 = "JavaScript:history.back();";
                    parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                    httpServletRequest.setAttribute("pass", (Object)s18);
                    httpServletRequest.setAttribute("msg", (Object)this.msg);
                    httpServletRequest.setAttribute("gubun", (Object)"namecheck");
                }
                else if ("61".equals(s9) || "62".equals(s9) || "63".equals(s9)) {
                    this.msg = "연결 장애 (NoRouteToHostException 일 경우 : 라우터에서 연결 host를 못찾을때) <br>(실명확인 모듈을 설치한 서버의 브라우져에서 아래의 경로로 접속해 보시고 <br>  연결 안 되실 경우 개인 pc에서 해보시고 그래도 연결이 안될 경우는 문의 주십시오. <br>  서버쪽에서만 안 될경우 서버 네트워크 확인 해주시고, 방화벽이 설치되어 있을경우 <br>  저희쪽 실명확인 서버 IP(203.234.219.72),Port(81~85)를 등록해 주셔야 통신 가능합니다.) <br>     http://203.234.219.72:81/check.asp <br>     http://203.234.219.72:82/check.asp <br>     http://203.234.219.72:83/check.asp <br>     http://203.234.219.72:84/check.asp <br>     http://203.234.219.72:85/check.asp <br>  위의 아이피로 접속하셨을때 아이피와 디렉토리가 출력되면 네트워크는 정상입니다.<br>  네트워크 연결 및 방화벽 확인해 보시고, 계속 발생할 경우 연락주세요.  <br><br><a href=\"http://www.namecheck.co.kr/per_callcenter.asp\" target=\"nmcheck\">[한국신용평가 콜센터 바로가기]</a>";
                    final String s19 = "JavaScript:history.back();";
                    parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                    httpServletRequest.setAttribute("pass", (Object)s19);
                    httpServletRequest.setAttribute("msg", (Object)this.msg);
                    httpServletRequest.setAttribute("gubun", (Object)"namecheck");
                }
                else {
                    this.msg = "죄송합니다. 실명인증중 장애가 발생되었습니다.<br>다시 한 번 입력해 주세요. <br>계속해서 같은 현상이 발생될 경우 아래 전화로 문의바랍니다.<br>  리턴코드 : " + s9 + "<br>" + "콜센터 : 1588-0800";
                    final String s20 = "JavaScript:history.back();";
                    parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                    httpServletRequest.setAttribute("pass", (Object)s20);
                    httpServletRequest.setAttribute("msg", (Object)this.msg);
                    httpServletRequest.setAttribute("gubun", (Object)"namecheck");
                }
            }
            catch (Exception ex) {
                this.msg = "죄송합니다. 실명인증중 장애가 발생되었습니다.<br>다시 한 번 입력해 주세요. <br>계속해서 같은 현상이 발생될 경우 아래 전화로 문의바랍니다.<br>콜센터 : 1588-0800";
                final String s21 = "JavaScript:history.back();";
                parameter = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
                httpServletRequest.setAttribute("pass", (Object)s21);
                httpServletRequest.setAttribute("msg", (Object)this.msg);
                httpServletRequest.setAttribute("gubun", (Object)"namecheck");
            }
        }
        httpServletRequest.getRequestDispatcher(parameter).forward((ServletRequest)httpServletRequest, (ServletResponse)httpServletResponse);
    }
    
    static {
        HP_PAV_NameCheck.SITEID = "D869";
        HP_PAV_NameCheck.SITEPW = "53289434";
        HP_PAV_NameCheck.numcheck = "0123456789";
    }
}
