// 
// Decompiled by Procyon v0.5.30
// 

package LOGIN;

import javax.servlet.http.Cookie;
import common.LoginLog;
import common.CommEntity;
import java.sql.Connection;
import secu.lib.SignAndEnvMessage;
import common.PublicUtil;
import common.Trx;
import secu.lib.CertInfo;
import secu.lib.SignedMessage;
import secu.lib.EnvelopedMessage;
import java.util.StringTokenizer;
import common.util.CommUtil;
import secu.lib.Secu;
import common.OneRowEntity;
import java.io.PrintWriter;
import g2b.sso.SSO;
import common.CheckValidation;
import java.io.IOException;
import javax.servlet.ServletException;
import common.Log;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class LoginHPAction extends HttpServlet
{
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        final String parameter = httpServletRequest.getParameter("cmd");
        try {
            if (parameter.equals("cm00")) {
                this.processCM00(httpServletRequest, httpServletResponse);
                return;
            }
            if (parameter.equals("if00")) {
                this.processIF00(httpServletRequest, httpServletResponse);
                return;
            }
            if (parameter.equals("serverKey")) {
                this.processServerKey(httpServletRequest, httpServletResponse);
                return;
            }
            if (parameter.equals("userInfo")) {
                this.processUserInfo(httpServletRequest, httpServletResponse);
            }
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + ".cmd[" + parameter + "]:" + ex.toString());
            httpServletResponse.setContentType("text/html; charset=euc-kr");
            httpServletResponse.getWriter().print("ERR_CODE=" + ex.getMessage());
        }
    }
    
    private void processUserInfo(final HttpServletRequest req, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html; charset=euc-kr");
        final PrintWriter writer = httpServletResponse.getWriter();
        try {
            if (!CheckValidation.isValidIP(req)) {
                writer.print("ERR_CODE=???????????? ?????? IP ?????????.");
                return;
            }
            final SSO sso = new SSO(req, httpServletResponse);
            if (!sso.isLogin()) {
                throw new Exception("??????????????? ?????? ?????? ???????????????.");
            }
            final String id = sso.getID();
            final OneRowEntity info = new CertLocal().getInfo(id, "c");
            writer.print(id + "|");
            writer.print(info.getDataFromName("??????") + "|");
            writer.print(info.getDataFromName("????????????") + "|");
            writer.print(info.getDataFromName("??????") + "|");
            writer.print(info.getDataFromName("?????????????????????") + "|");
            writer.print("|");
            writer.print(info.getDataFromName("??????") + " " + info.getDataFromName("???????????????") + "|");
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " .processUserInfo :" + ex.toString());
            writer.print("ERR_CODE=" + ex.getMessage());
        }
    }
    
    private void processCM00(final HttpServletRequest httpServletRequest, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=euc-kr");
        final PrintWriter writer = res.getWriter();
        final Secu secu = new Secu(2);
        final CertLocal certLocal = new CertLocal();
        final String ip = CertLocal.getIP(httpServletRequest);
        String nextToken = "";
        String nextToken2 = "";
        String nextToken3 = "";
        String nextToken4 = "";
        String nextToken5 = "";
        String nextToken6 = "";
        final CommUtil commUtil = new CommUtil();
        try {
            if (!CheckValidation.isValidIP(httpServletRequest)) {
                writer.print("ERR_CODE=???????????? ?????? IP ?????????.");
                return;
            }
            final String retNull = commUtil.retNull(httpServletRequest.getParameter("encryptKey"));
            if (retNull == null) {
                writer.print("ERR_CODE=???????????? ?????????????????? ????????????.");
                return;
            }
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(retNull, "|");
                if (stringTokenizer.hasMoreTokens()) {
                    nextToken = stringTokenizer.nextToken();
                    nextToken2 = stringTokenizer.nextToken();
                    nextToken3 = stringTokenizer.nextToken();
                    nextToken4 = stringTokenizer.nextToken();
                }
            }
            catch (Exception ex) {
                Log.debug(3, this.getClass().getName() + " ??????????????? ??????????????? ??????:" + ex.toString());
                writer.print("ERR_CODE=????????? ?????????????????? ????????? ??????????????????.");
                return;
            }
            String decryptUpdate;
            try {
                final EnvelopedMessage envelopedMessage = new EnvelopedMessage(secu);
                if (nextToken3 == null || nextToken4 == null) {
                    Log.debug(3, this.getClass().getName() + " strEncData[" + nextToken3 + "], strEncData[" + nextToken4 + "], ????????? ?????? ???????????? ????????????");
                    writer.print("ERR_CODE=???????????? ?????? ??????????????????.");
                    return;
                }
                envelopedMessage.decryptInit(1, 1, nextToken3);
                decryptUpdate = envelopedMessage.decryptUpdate(nextToken4);
                envelopedMessage.decryptFinal();
                if (decryptUpdate == null) {
                    writer.print("ERR_CODE=EnvelopedMessage ????????? ?????????????????????.");
                    return;
                }
            }
            catch (Exception ex2) {
                Log.debug(3, this.getClass().getName() + " EnvelopedMessage ?????? ??????:" + ex2.toString());
                writer.print("ERR_CODE=EnvelopedMessage ?????? ??????[" + ex2.getMessage() + "]");
                return;
            }
            try {
                if (!new SignedMessage(secu).verify(nextToken, decryptUpdate, nextToken2)) {
                    writer.print("ERR_CODE=???????????? ????????? ?????????????????????.");
                    return;
                }
            }
            catch (Exception ex3) {
                Log.debug(3, this.getClass().getName() + " ???????????? ?????? ??????:" + ex3.toString());
                writer.print("ERR_CODE=???????????? ?????? ??????[" + ex3.getMessage() + "]");
                return;
            }
            final CertInfo certInfo = new CertInfo(secu, nextToken);
            final String[] certOID = secu.getCertOID();
            try {
                if (!certInfo.isValidPolicyOid(certOID)) {
                    writer.print("ERR_CODE=???????????? ?????? ??????????????????. ????????? ????????? ??????????????? ???????????? ????????????.");
                    return;
                }
            }
            catch (Exception ex4) {
                Log.debug(3, this.getClass().getName() + " ????????? ?????? ?????? ??????:" + ex4.toString());
                writer.print("ERR_CODE=????????? ?????? ?????? ??????[" + ex4.getMessage() + "]");
                return;
            }
            try {
                if (!certInfo.isValid()) {
                    writer.print("ERR_CODE=????????? ????????? ?????????????????????. ????????? ????????? ????????? ???????????? ?????? ????????? ?????????.");
                    return;
                }
            }
            catch (Exception ex5) {
                Log.debug(3, this.getClass().getName() + " ????????? ????????? ??????:" + ex5.toString());
                writer.print("ERR_CODE=????????? ????????? ??????[" + ex5.getMessage() + "]");
                return;
            }
            try {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(decryptUpdate, "|");
                if (stringTokenizer2.hasMoreTokens()) {
                    nextToken5 = stringTokenizer2.nextToken();
                    nextToken6 = stringTokenizer2.nextToken();
                }
            }
            catch (Exception ex6) {
                Log.debug(3, this.getClass().getName() + " ????????? ?????? ??????:" + ex6.toString());
                writer.print("ERR_CODE=????????? ?????? ??????[" + ex6.getMessage() + "]");
                return;
            }
            if (nextToken5 == null || nextToken5.equals("")) {
                Log.debug(3, this.getClass().getName() + " userDN[" + nextToken5 + "] DN ??? ?????? ??????");
                writer.print("ERR_CODE=???????????? ?????? ??????");
                return;
            }
            Trx trx = null;
            Connection connection = null;
            try {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                final CommEntity[] certUserInfo = certLocal.getCertUserInfo(nextToken5, "", "", connection);
                if (certUserInfo.length == 0) {
                    Log.debug(3, this.getClass().getName() + " userDN[" + nextToken5 + "] ???????????? ?????? ???????????? ???????????????");
                    writer.print("ERR_CODE=???????????? ?????? ??????????????????.");
                    return;
                }
                if (!PublicUtil.retSpace(certUserInfo[0].data[12]).equals("2")) {
                    Log.debug(3, this.getClass().getName() + ".userDN[" + nextToken5 + "] ???????????? 9 ??? ?????????");
                    writer.print("??????????????? ??????????????????.");
                    return;
                }
                if (!certUserInfo[0].data[1].equals("c")) {
                    writer.print("ERR_CODE=????????? ???????????? ?????? ????????????.");
                    return;
                }
                final String comment = null;
                final String domain = "g2b.go.kr";
                final int maxAge = -1;
                final String path = "/";
                final boolean secure = false;
                final int version = 0;
                int n = 0;
                final String[] values = new String[CertLocal.certvalue.length];
                final String userID = certUserInfo[0].data[0];
                values[n++] = certUserInfo[0].data[0];
                values[n++] = certUserInfo[0].data[1];
                values[n++] = certUserInfo[0].data[2];
                values[n++] = certUserInfo[0].data[3];
                values[n++] = certUserInfo[0].data[4];
                values[n++] = certUserInfo[0].data[5];
                values[n++] = certUserInfo[0].data[6];
                values[n++] = certUserInfo[0].data[7];
                values[n++] = certUserInfo[0].data[8];
                values[n++] = certUserInfo[0].data[9];
                values[n++] = new Long(System.currentTimeMillis()).toString();
                values[n++] = ip;
                final String retSpace = PublicUtil.retSpace(certUserInfo[0].data[10]);
                PublicUtil.retSpace(certUserInfo[0].data[11]);
                if (!certInfo.ValidateUserID(retSpace, "", nextToken6)) {
                    writer.print("ERR_CODE=????????? ???????????? ?????? ??????");
                    return;
                }
                final String stringToken = certLocal.getStringToken(values, "C");
                String encryptInit;
                String encryptUpdate;
                String encryptFinal;
                try {
                    final SignAndEnvMessage signAndEnvMessage = new SignAndEnvMessage(secu);
                    encryptInit = signAndEnvMessage.encryptInit(1, 0, 1, secu.getPemKmCertInfo(1).trim(), nextToken3.trim());
                    if (encryptInit == null) {
                        Log.debug(3, this.getClass().getName() + ".userDN[" + nextToken5 + "] ??????????????? ????????? ??????[encryptInit]");
                        writer.print("ERR_CODE=??????????????? ????????? ??????[encryptInit]");
                        return;
                    }
                    encryptUpdate = signAndEnvMessage.encryptUpdate(stringToken);
                    if (encryptUpdate == null) {
                        Log.debug(3, this.getClass().getName() + ".userDN[" + nextToken5 + "] ??????????????? ????????? ??????[encryptUpdate]");
                        writer.print("ERR_CODE=??????????????? ????????? ??????[encryptUpdate]");
                        return;
                    }
                    encryptFinal = signAndEnvMessage.encryptFinal();
                    if (encryptFinal == null) {
                        Log.debug(3, this.getClass().getName() + ".userDN[" + nextToken5 + "] ??????????????? ????????? ??????[encryptFinal]");
                        writer.print("ERR_CODE=??????????????? ????????? ??????[encryptFinal]");
                        return;
                    }
                }
                catch (Exception ex7) {
                    Log.debug(3, this.getClass().getName() + ".userDN[" + nextToken5 + "] ???????????????:" + ex7.toString());
                    writer.print("ERR_CODE=??????????????? ????????? ??????[" + ex7.getMessage() + "]");
                    return;
                }
                certLocal.setCookies(res, comment, domain, maxAge, path, secure, version, new String[] { encryptUpdate, encryptInit, encryptFinal }, true);
                certLocal.updateLastLoginTime(certInfo.getNotBefore(), certInfo.getNotAfter(), nextToken5, connection);
                try {
                    final OneRowEntity userDNInfo = certLocal.getUserDNInfo(userID, certUserInfo[0].data[7], null);
                    if (userDNInfo.DataExist && userDNInfo.getDataFromName("???????????????").equals("N")) {
                        Log.info(5, "##" + userDNInfo.getDataFromName("?????????ID") + "##" + userDNInfo.getDataFromName("??????") + "##" + userDNInfo.getDataFromName("?????????????????????") + "##" + userDNInfo.getDataFromName("??????????????????????????????") + "##" + userDNInfo.getDataFromName("????????????") + "##" + userDNInfo.getDataFromName("??????????????????") + "##" + userDNInfo.getDataFromName("?????????????????????") + "##" + userDNInfo.getDataFromName("?????????") + "##" + userDNInfo.getDataFromName("????????????") + "##" + userDNInfo.getDataFromName("?????????????????????") + "##" + ip + "##" + values[12]);
                    }
                }
                catch (Exception ex8) {
                    Log.debug(this.getClass().getName() + " ??????????????? ?????? :ID[" + userID + "],IP[" + ip + "]:" + ex8.toString());
                }
                writer.print("OK");
            }
            catch (Exception ex9) {
                Log.debug(3, this.getClass().getName() + ". DN[" + nextToken5 + "],IP[" + ip + "]:" + ex9.toString());
                writer.print("ERR_CODE=" + ex9.getMessage());
            }
            finally {
                if (connection != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex11) {}
                }
            }
        }
        catch (Exception ex10) {
            Log.debug(this.getClass().getName() + " .processCM00 :" + ex10.toString());
            writer.print("ERR_CODE=" + ex10.getMessage());
        }
    }
    
    private void processIF00(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html; charset=euc-kr");
        final PrintWriter writer = httpServletResponse.getWriter();
        try {
            if (!CheckValidation.isValidIP(httpServletRequest)) {
                writer.print("ERR_CODE=???????????? ?????? IP ?????????.");
                return;
            }
            final String id = new SSO(httpServletRequest, httpServletResponse).getID();
            final Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies != null) {
                for (int n = cookies.length - 1; 0 <= n; --n) {
                    final String name = cookies[n].getName();
                    if (name.equals("_SLT")) {
                        cookies[n].setDomain("g2b.go.kr");
                        cookies[n].setMaxAge(0);
                        cookies[n].setPath("/");
                        httpServletResponse.addCookie(cookies[n]);
                    }
                    else if (name.equals("_SLTK")) {
                        cookies[n].setDomain("g2b.go.kr");
                        cookies[n].setMaxAge(0);
                        cookies[n].setPath("/");
                        httpServletResponse.addCookie(cookies[n]);
                    }
                    else if (name.equals("_SLTS")) {
                        cookies[n].setDomain("g2b.go.kr");
                        cookies[n].setMaxAge(0);
                        cookies[n].setPath("/");
                        httpServletResponse.addCookie(cookies[n]);
                    }
                    else if (name.equals("_TEMP_RET_PAGE")) {
                        cookies[n].setDomain("g2b.go.kr");
                        cookies[n].setMaxAge(0);
                        cookies[n].setPath("/");
                        httpServletResponse.addCookie(cookies[n]);
                    }
                }
                LoginLog.write("\n<????????????:" + CertLocal.getIP(httpServletRequest) + "> userID[" + id + "]");
            }
            writer.print("OK");
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " .processIF00 :" + ex.toString());
            writer.print("ERR_CODE=" + ex.getMessage());
        }
    }
    
    private void processServerKey(final HttpServletRequest req, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setContentType("text/html; charset=euc-kr");
        final PrintWriter writer = httpServletResponse.getWriter();
        try {
            if (!CheckValidation.isValidIP(req)) {
                writer.print("ERR_CODE=???????????? ?????? IP ?????????.");
                return;
            }
            writer.print(new Secu(2).getPemKmCertInfo(1));
        }
        catch (Exception ex) {
            Log.debug(this.getClass().getName() + " .processServerKey :" + ex.toString());
            writer.print("ERR_CODE=" + ex.getMessage());
        }
    }
}
