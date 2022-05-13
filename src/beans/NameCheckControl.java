// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import common.NameCheck;

public class NameCheckControl
{
    private static final String SITEID = "D869";
    private static final String SITEPW = "53289434";
    private static final String RTN_2_3 = "고객님이 입력하신<br>\n성명과 주민등록번호가 일치하지 않거나 자료가 없습니다.<br>\n고객님의 정확한 성명과 주민등록번호를 입력했음에도<br>\n불구하고 계속해서 이름과 주민번호가 일치하지 <br>\n않는다고 나오면 한국신용평가 콜센터에서 실명확인 <br>\n등록신청을 해주시기 바랍니다.<br><br>\n<center><a href=\"http://www.namecheck.co.kr/per_callcenter.asp\" target=\"_new\"><font color=red>[한국신용평가 콜센터 바로가기]</font></a></center>";
    private static final String RTN_5 = " 고객님이 입력하신 주민등록번호가 잘못 입력되었습니다.<br>\n(주민번호 체크 썸 오류)<br>\n확인 후 다시 한 번 입력하세요.<br>";
    private static final String RTN_ETC = " 죄송합니다.<br><br>\n실명확인인증 중 시스템 에러가 발생했습니다. <br>\n잠시후 다시 시도해 주십시오.<br>";
    private static final String RTN_OK = " 실명확인이 되었습니다.<br>\n고객님이 입력하신 성명과 주민등록번호가 일치합니다.<br>";
    private static final String RTN_4 = " 시스템 장애<br>\n귀사의 네트워크장애일 경우도 발생하고, <br>\n방화벽이 설치되어 있으실경우 저희쪽 서버 IP,Port를 등록해 주지 않으셨을 경우에도 발생합니다.<br>\n네트워크 확인해 주시고 문의 주십시오.<br>";
    private static final String RTN_6 = " 시스템 장애<br>\n성인인증시 만19세 이하인 경우 실명인증 거치지 않고 바로 리턴코드 출력됩니다.<br>\n(실명확인 서비스 신청시 서비스성격이 성인인증인 업체에만 해당됩니다.)<br>\n  확인 후 다시 한 번 입력하세요.<br>";
    private static final String RTN_21 = " 시스템 장애<br>\n암호화 데이타 이상 <br>\n주민번호(13), 비밀번호(8) 자릿수를 확인해 주세요.<br>\n확인 후 다시 한 번 입력하세요.<br>";
    private static final String RTN_24 = " 시스템 장애<br>\n암호화 연산중 에러<br>\n올바르지 않은 주민번호인지 확인해 주세요.<br>\n확인 후 다시 한 번 입력하세요.<br>";
    private static final String RTN_50 = " 시스템 장애<br>\n정보도용 차단 요청 주민번호<br>\n(실명확인 요청시 성명 일치/불일치에 관계없이 결과값으로 50 을 리턴)<br>";
    private static final String RTN_61_62_63 = " 시스템 장애<br><br>\n연결 장애 (NoRouteToHostException 일 경우 : 라우터에서 연결 host를 못찾을때) <br>\n(실명확인 모듈을 설치한 서버의 브라우져에서 아래의 경로로 접속해 보시고 <br>\n  연결 안 되실 경우 개인 pc에서 해보시고 그래도 연결이 안될 경우는 문의 주십시오. <br>\n  서버쪽에서만 안 될경우 서버 네트워크 확인 해주시고, 방화벽이 설치되어 있을경우 <br>\n  저희쪽 실명확인 서버 IP(203.234.219.72),Port(81~85)를 등록해 주셔야 통신 가능합니다.) <br>\n     http://203.234.219.72:81/check.asp <br>\n     http://203.234.219.72:82/check.asp <br>\n     http://203.234.219.72:83/check.asp <br>\n     http://203.234.219.72:84/check.asp <br>\n     http://203.234.219.72:85/check.asp <br>\n  위의 아이피로 접속하셨을때 아이피와 디렉토리가 출력되면 네트워크는 정상입니다.<br>\n  네트워크 연결 및 방화벽 확인해 보시고, 계속 발생할 경우 연락주세요. ";
    private static String numcheck;
    String chkValue;
    private static final String RTN_Name = " 이름이 입력되어 있지 않습니다!!<br>\n다시 한 번 확인 후 정확히 입력하세요.<br>";
    private static final String RTN_Jumin = " 주민등록번호가 입력되어 있지 않습니다!!<br>\n다시 한 번 확인 후 정확히 입력하세요.<br>";
    private static final String RTN_Hangul = " 이름은 한글 이외에는 입력하실수 없습니다.!!<br>\n다시 한 번 확인 후 정확히 입력하세요.<br>";
    private static final String RTN_Length = " 주민등록번호가 제대로 입력되어 있지 않습니다!!<br>\n다시 한 번 확인 후 정확히 입력하세요.<br>";
    private static final String RTN_NumCheck = " 주민등록번호에 숫자가 아닌 다른 것이 포함되어 있습니다.!!<br>\n다시 한 번 확인 후 정확히 입력하세요.<br>";
    
    public NameCheckControl() {
        this.chkValue = "";
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
    
    public boolean Check_Val(final String s, final String s2) {
        if ("".equals(s)) {
            this.chkValue = "Name";
            return false;
        }
        if ("".equals(s2)) {
            this.chkValue = "Jumin";
            return false;
        }
        if (!"".equals(s2)) {
            if (s2.length() < 13) {
                this.chkValue = "Length";
                return false;
            }
            for (int i = 0; i < s2.length(); ++i) {
                if (NameCheckControl.numcheck.indexOf(s2.charAt(i)) == -1) {
                    this.chkValue = "NumCheck";
                    return false;
                }
            }
        }
        return true;
    }
    
    public String getNameCheckValue(final String s, final String chkName) {
        if (this.Check_Val(chkName, s)) {
            this.chkValue = "";
        }
        String s2;
        if ("".equals(this.chkValue)) {
            final NameCheck nameCheck = new NameCheck();
            nameCheck.setChkName(chkName);
            if (!nameCheck.setJumin(s + "53289434").equals("0")) {
                return "-1000";
            }
            nameCheck.setSiteCode("D869");
            nameCheck.setTimeOut(30000);
            s2 = nameCheck.getRtn().trim();
        }
        else {
            s2 = this.chkValue;
        }
        return s2;
    }
    
    public static String getRtnErrorMessage(final String s) {
        if (s.equals("2") || s.equals("3")) {
            return "고객님이 입력하신<br>\n성명과 주민등록번호가 일치하지 않거나 자료가 없습니다.<br>\n고객님의 정확한 성명과 주민등록번호를 입력했음에도<br>\n불구하고 계속해서 이름과 주민번호가 일치하지 <br>\n않는다고 나오면 한국신용평가 콜센터에서 실명확인 <br>\n등록신청을 해주시기 바랍니다.<br><br>\n<center><a href=\"http://www.namecheck.co.kr/per_callcenter.asp\" target=\"_new\"><font color=red>[한국신용평가 콜센터 바로가기]</font></a></center>";
        }
        if (s.equals("5")) {
            return " 고객님이 입력하신 주민등록번호가 잘못 입력되었습니다.<br>\n(주민번호 체크 썸 오류)<br>\n확인 후 다시 한 번 입력하세요.<br>";
        }
        if (s.equals("1")) {
            return " 실명확인이 되었습니다.<br>\n고객님이 입력하신 성명과 주민등록번호가 일치합니다.<br>";
        }
        if (s.equals("4")) {
            return " 시스템 장애<br>\n귀사의 네트워크장애일 경우도 발생하고, <br>\n방화벽이 설치되어 있으실경우 저희쪽 서버 IP,Port를 등록해 주지 않으셨을 경우에도 발생합니다.<br>\n네트워크 확인해 주시고 문의 주십시오.<br>";
        }
        if (s.equals("6")) {
            return " 시스템 장애<br>\n성인인증시 만19세 이하인 경우 실명인증 거치지 않고 바로 리턴코드 출력됩니다.<br>\n(실명확인 서비스 신청시 서비스성격이 성인인증인 업체에만 해당됩니다.)<br>\n  확인 후 다시 한 번 입력하세요.<br>";
        }
        if (s.equals("21")) {
            return " 시스템 장애<br>\n암호화 데이타 이상 <br>\n주민번호(13), 비밀번호(8) 자릿수를 확인해 주세요.<br>\n확인 후 다시 한 번 입력하세요.<br>";
        }
        if (s.equals("24")) {
            return " 시스템 장애<br>\n암호화 연산중 에러<br>\n올바르지 않은 주민번호인지 확인해 주세요.<br>\n확인 후 다시 한 번 입력하세요.<br>";
        }
        if (s.equals("50")) {
            return " 시스템 장애<br>\n정보도용 차단 요청 주민번호<br>\n(실명확인 요청시 성명 일치/불일치에 관계없이 결과값으로 50 을 리턴)<br>";
        }
        if (s.equals("61") || s.equals("62") || s.equals("63")) {
            return " 시스템 장애<br><br>\n연결 장애 (NoRouteToHostException 일 경우 : 라우터에서 연결 host를 못찾을때) <br>\n(실명확인 모듈을 설치한 서버의 브라우져에서 아래의 경로로 접속해 보시고 <br>\n  연결 안 되실 경우 개인 pc에서 해보시고 그래도 연결이 안될 경우는 문의 주십시오. <br>\n  서버쪽에서만 안 될경우 서버 네트워크 확인 해주시고, 방화벽이 설치되어 있을경우 <br>\n  저희쪽 실명확인 서버 IP(203.234.219.72),Port(81~85)를 등록해 주셔야 통신 가능합니다.) <br>\n     http://203.234.219.72:81/check.asp <br>\n     http://203.234.219.72:82/check.asp <br>\n     http://203.234.219.72:83/check.asp <br>\n     http://203.234.219.72:84/check.asp <br>\n     http://203.234.219.72:85/check.asp <br>\n  위의 아이피로 접속하셨을때 아이피와 디렉토리가 출력되면 네트워크는 정상입니다.<br>\n  네트워크 연결 및 방화벽 확인해 보시고, 계속 발생할 경우 연락주세요. ";
        }
        if (s.equals("Name")) {
            return " 이름이 입력되어 있지 않습니다!!<br>\n다시 한 번 확인 후 정확히 입력하세요.<br>";
        }
        if (s.equals("Jumin")) {
            return " 주민등록번호가 입력되어 있지 않습니다!!<br>\n다시 한 번 확인 후 정확히 입력하세요.<br>";
        }
        if (s.equals("Hangul")) {
            return " 이름은 한글 이외에는 입력하실수 없습니다.!!<br>\n다시 한 번 확인 후 정확히 입력하세요.<br>";
        }
        if (s.equals("Length")) {
            return " 주민등록번호가 제대로 입력되어 있지 않습니다!!<br>\n다시 한 번 확인 후 정확히 입력하세요.<br>";
        }
        if (s.equals("NumCheck")) {
            return " 주민등록번호에 숫자가 아닌 다른 것이 포함되어 있습니다.!!<br>\n다시 한 번 확인 후 정확히 입력하세요.<br>";
        }
        return " 죄송합니다.<br><br>\n실명확인인증 중 시스템 에러가 발생했습니다. <br>\n잠시후 다시 시도해 주십시오.<br>";
    }
    
    static {
        NameCheckControl.numcheck = "0123456789";
    }
}
