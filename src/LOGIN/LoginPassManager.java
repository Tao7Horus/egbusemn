// 
// Decompiled by Procyon v0.5.30
// 

package LOGIN;

import java.io.IOException;
import javax.servlet.ServletException;
import common.CommEntity;
import java.sql.Connection;
import common.LoginLog;
import secu.lib.SignAndEnvMessage;
import common.Trx;
import secu.lib.MessageDigest;
import secu.lib.EnvelopedMessage;
import common.Log;
import common.PublicUtil;
import secu.lib.Secu;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class LoginPassManager extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        final Secu secu = new Secu(2);
        final CertLocal certLocal = new CertLocal();
        final String ip = CertLocal.getIP(req);
        final String retSpace = PublicUtil.retSpace(req.getParameter("login_type"));
        if (!retSpace.equalsIgnoreCase("I")) {
            Log.debug(3, this.getClass().getName() + ". IP[" + ip + "],loginType[" + retSpace + "], 로그인타입 I 가 아님");
            LoginMessage.printMessage("002", "001", "LoginType[" + retSpace + "]", "4", null, req, res);
            return;
        }
        final String retSpace2 = PublicUtil.retSpace(req.getParameter("enc_key"));
        final String retSpace3 = PublicUtil.retSpace(req.getParameter("enc_data"));
        final String retSpace4 = PublicUtil.retSpace(req.getParameter("enc_randKey"));
        if (retSpace2.equals("")) {
            Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],encKey[" + retSpace2 + "], 세션키가 없습니다.");
            LoginMessage.printMessage("002", "003", "", "4", null, req, res);
            return;
        }
        if (retSpace3.equals("")) {
            Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],encData[" + retSpace3 + "], 암호화된 데이터가 없습니다.");
            LoginMessage.printMessage("002", "004", "", "4", null, req, res);
            return;
        }
        PublicUtil.retSpace(req.getParameter("personid"));
        final String string = "http://www.g2b.go.kr:8070/jsp/common/LoginPassSave.jsp?SiteGubun=" + PublicUtil.retSpace(req.getParameter("SiteGubun"));
        String decryptUpdate;
        try {
            final EnvelopedMessage envelopedMessage = new EnvelopedMessage(secu);
            if (retSpace2 == null || retSpace3 == null) {
                Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],encKey[" + retSpace2 + "], encData[" + retSpace3 + "], 복호화 대상 암호화 데이터가 널입니다");
                LoginMessage.printMessage("002", "006", "", "4", null, req, res);
                return;
            }
            envelopedMessage.decryptInit(1, 1, retSpace2);
            decryptUpdate = envelopedMessage.decryptUpdate(retSpace3);
            envelopedMessage.decryptUpdate(retSpace4);
            envelopedMessage.decryptFinal();
        }
        catch (Exception ex) {
            Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],암호화문자열복호화에러 : " + ex.toString());
            LoginMessage.printMessage("002", "000", ex.getMessage(), "4", null, req, res);
            return;
        }
        if (decryptUpdate == null) {
            Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],strOrg[" + decryptUpdate + "] 유효하지 않은 복호화 오류입니다.");
            LoginMessage.printMessage("002", "000", "유효하지 않은 복호화 오류입니다. null 일 수 없습니다.", "4", null, req, res);
            return;
        }
        String[] idpwd;
        try {
            idpwd = certLocal.getIDPWD(decryptUpdate);
        }
        catch (Exception ex2) {
            Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],strOrg[" + decryptUpdate + "] ID/PASS 검증시 에러:" + ex2.toString());
            LoginMessage.printMessage("002", "000", "ID/PASS 검증시 에러 <br>" + ex2.toString(), "4", null, req, res);
            return;
        }
        if (idpwd != null && idpwd.length == 2) {
            final String userID = idpwd[0];
            final String s = idpwd[1];
            final MessageDigest messageDigest = new MessageDigest(secu);
            final String create = messageDigest.create(s);
            messageDigest.create(s);
            Trx trx = null;
            Connection connection = null;
            try {
                trx = new Trx(this, "usemn");
                connection = trx.getConnection();
                final CommEntity[] passUserInfo = certLocal.getPassUserInfo(userID, connection);
                if (passUserInfo.length == 0) {
                    Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],userID[" + userID + "] 등록되지 않은 ID로 로그인시도");
                    LoginMessage.printMessage("002", "000", "등록되지 않은 ID 입니다.", "4", null, req, res);
                    return;
                }
                if (!passUserInfo[0].data[10].equals(create)) {
                    certLocal.updateFailLogin(userID, passUserInfo[0].data[15], connection);
                    LoginMessage.printMessage("002", "000", "비밀번호가 일치하지 않습니다.", "4", null, req, res);
                    return;
                }
                if (Integer.parseInt(passUserInfo[0].data[14]) >= 5) {
                    LoginMessage.printMessage("002", "000", "5회 이상 비밀번호를 잘못 입력하셨습니다.<br>조달청 콜센터(1588-0800)로 문의 바랍니다.", "4", null, req, res);
                    return;
                }
                certLocal.initialPass(userID, passUserInfo[0].data[15], connection);
                final String comment = null;
                final String domain = "g2b.go.kr";
                final int maxAge = -1;
                final String path = "/";
                final boolean secure = false;
                final int version = 0;
                int n = 0;
                final String[] values = new String[CertLocal.idvalue.length];
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[0]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[1]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[2]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[3]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[4]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[5]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[6]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[7]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[8]);
                values[n++] = new Long(System.currentTimeMillis()).toString();
                values[n++] = ip;
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[9]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[10]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[11]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[12]);
                values[n++] = PublicUtil.retSpace(passUserInfo[0].data[13]);
                final String stringToken = certLocal.getStringToken(values, retSpace);
                String encryptInit;
                String encryptUpdate;
                String encryptFinal;
                try {
                    final SignAndEnvMessage signAndEnvMessage = new SignAndEnvMessage(secu);
                    encryptInit = signAndEnvMessage.encryptInit(1, 0, 1, secu.getPemKmCertInfo(1).trim(), retSpace2.trim());
                    if (encryptInit == null) {
                        Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],userID[" + userID + "] 사용자인증 암호화 실패[encryptInit]");
                        LoginMessage.printMessage("002", "000", "사용자인증 암호화 실패[encryptInit]", "4", null, req, res);
                        return;
                    }
                    encryptUpdate = signAndEnvMessage.encryptUpdate(stringToken);
                    if (encryptUpdate == null) {
                        Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],userID[" + userID + "] 사용자인증 암호화 실패[encryptUpdate]");
                        LoginMessage.printMessage("002", "000", "사용자인증 암호화 실패[encryptUpdate]", "4", null, req, res);
                        return;
                    }
                    encryptFinal = signAndEnvMessage.encryptFinal();
                    if (encryptFinal == null) {
                        Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],userID[" + userID + "] 사용자인증 암호화 실패[encryptFinal]");
                        LoginMessage.printMessage("002", "000", "사용자인증 암호화 실패[encryptFinal]", "4", null, req, res);
                        return;
                    }
                }
                catch (Exception ex3) {
                    Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],userID[" + userID + "] 암호화실패:" + ex3.toString());
                    LoginMessage.printMessage("002", "000", "사용자인증 암호화 실패:" + ex3.getMessage(), "4", null, req, res);
                    return;
                }
                certLocal.setCookies(res, comment, domain, maxAge, path, secure, version, new String[] { encryptUpdate, encryptInit, encryptFinal }, true);
                LoginLog.write("\n<로그인 성공:" + ip + "> login_type[" + retSpace + "],userID[" + userID + "]");
                res.sendRedirect(string);
            }
            catch (Exception ex4) {
                Log.debug(3, this.getClass().getName() + ". userID[" + userID + "],IP[" + ip + "]:" + ex4.toString());
                LoginMessage.printMessage("002", "000", ex4.getMessage(), "4", null, req, res);
            }
            finally {
                if (connection != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex5) {}
                }
            }
            return;
        }
        Log.debug(3, this.getClass().getName() + ".IP[" + ip + "],strOrg[" + decryptUpdate + "] 유효하지 않은 ID/PASS 입니다.");
        LoginMessage.printMessage("002", "000", "유효하지 않은 ID/PASS 입니다.<br>다시 시도해 주세요", "4", null, req, res);
    }
}
