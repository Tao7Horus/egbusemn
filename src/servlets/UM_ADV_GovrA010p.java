// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.Connection;
import LOGIN.UM_Auth_Check;
import beans.UM_URB_CERT;
import common.util.CommonMessage;
import common.Log;
import common.ComStr;
import common.Trx;
import secu.lib.MessageDigest;
import secu.lib.Secu;
import common.util.CommUtil;
import beans.UM_URB_CERT010;
import javax.servlet.ServletRequest;
import com.oreilly.servlet.ParameterParser;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_GovrA010p extends HttpServlet
{
    public void service(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        Trx trx = null;
        Connection connection = null;
        final ParameterParser parameterParser = new ParameterParser((ServletRequest)httpServletRequest);
        final UM_URB_CERT010 um_URB_CERT010 = new UM_URB_CERT010();
        final CommUtil commUtil = new CommUtil();
        final String stringParameter = parameterParser.getStringParameter("G2BCODE", "");
        final String stringParameter2 = parameterParser.getStringParameter("CNAME", "");
        final String stringParameter3 = parameterParser.getStringParameter("IDENT", "");
        final String stringParameter4 = parameterParser.getStringParameter("KNAME", "");
        final String stringParameter5 = parameterParser.getStringParameter("IDENTJUMIN", "");
        final String stringParameter6 = parameterParser.getStringParameter("ZIPNO", "");
        final String stringParameter7 = parameterParser.getStringParameter("COMNO", "");
        final String stringParameter8 = parameterParser.getStringParameter("ADDRS", "");
        final String stringParameter9 = parameterParser.getStringParameter("ADDRESS2", "");
        final String stringParameter10 = parameterParser.getStringParameter("FAX", "");
        final String stringParameter11 = parameterParser.getStringParameter("MYNAME", "");
        final String stringParameter12 = parameterParser.getStringParameter("OFFICEDE", "");
        String s = parameterParser.getStringParameter("real_jumin_chk_yn1", "");
        if ("".equals(s)) {
            s = parameterParser.getStringParameter("JUMIN", "");
        }
        final String stringParameter13 = parameterParser.getStringParameter("TEL", "");
        final String stringParameter14 = parameterParser.getStringParameter("EMAIL", "");
        final String stringParameter15 = parameterParser.getStringParameter("HP", "");
        final String stringParameter16 = parameterParser.getStringParameter("smsCheck", "");
        final String stringParameter17 = parameterParser.getStringParameter("USRID", "");
        final String stringParameter18 = parameterParser.getStringParameter("CERT_ORG", "");
        final String stringParameter19 = parameterParser.getStringParameter("HOME", "");
        parameterParser.getStringParameter("STR", "");
        parameterParser.getStringParameter("STR1", "");
        final String stringParameter20 = parameterParser.getStringParameter("recept", "");
        final String stringParameter21 = parameterParser.getStringParameter("back", "");
        final String stringParameter22 = parameterParser.getStringParameter("dependCode", "");
        final String stringParameter23 = parameterParser.getStringParameter("flag", "");
        final String stringParameter24 = parameterParser.getStringParameter("approval", "");
        final String stringParameter25 = parameterParser.getStringParameter("branchOffi1", "");
        final String stringParameter26 = parameterParser.getStringParameter("passwdmodOK", "");
        final String stringParameter27 = parameterParser.getStringParameter("gID", "");
        final String stringParameter28 = parameterParser.getStringParameter("i07", "");
        String create = "";
        final String stringParameter29 = parameterParser.getStringParameter("dn", "");
        final String stringParameter30 = parameterParser.getStringParameter("refername", "");
        final String stringParameter31 = parameterParser.getStringParameter("refercode", "");
        final String stringParameter32 = parameterParser.getStringParameter("comNo", "");
        String g2bCode = parameterParser.getStringParameter("g2bCode", "");
        if (g2bCode.equals("")) {
            g2bCode = parameterParser.getStringParameter("g2bCode", "");
        }
        final String stringParameter33 = parameterParser.getStringParameter("CERT_CLS", "");
        final MessageDigest messageDigest = new MessageDigest(new Secu(1));
        if ("Y".equals(stringParameter26)) {
            create = messageDigest.create(stringParameter28);
        }
        commUtil.StandardPhonNumber(stringParameter15);
        if (stringParameter23.equals("Insert")) {
            try {
                trx = new Trx(this, "USEMN");
                connection = trx.getConnection();
                connection.setAutoCommit(false);
                final String maxReceptionID = um_URB_CERT010.getMaxReceptionID(connection);
                final String string = maxReceptionID.substring(2, maxReceptionID.length()) + stringParameter17;
                final String replace = ComStr.replace(ComStr.replace(stringParameter2, "·", ""), "ㆍ", "");
                if (stringParameter33.equals("")) {
                    um_URB_CERT010.insertReception(maxReceptionID, stringParameter, replace, stringParameter4, stringParameter3, stringParameter5, stringParameter6, stringParameter8, stringParameter9, stringParameter7, stringParameter10, stringParameter11, stringParameter12, s, stringParameter13, stringParameter14, stringParameter15, stringParameter17, stringParameter18, stringParameter19, string, stringParameter25, stringParameter26, stringParameter27, create, stringParameter16, connection);
                }
                else if (stringParameter33.equals("s") || stringParameter33.equals("S")) {
                    um_URB_CERT010.insertReception(maxReceptionID, stringParameter, replace, stringParameter4, stringParameter3, stringParameter5, stringParameter6, stringParameter8, stringParameter9, stringParameter7, stringParameter10, stringParameter11, stringParameter12, s, stringParameter13, stringParameter14, stringParameter15, stringParameter17, stringParameter18, stringParameter19, string, stringParameter25, stringParameter26, stringParameter27, create, stringParameter16, "S", connection);
                }
                connection.commit();
                connection.setAutoCommit(true);
                httpServletResponse.sendRedirect("/AD/UM_ADJ_GovrL010p.jsp?recept=" + maxReceptionID + "&approvalCode=" + string + "&G2BCODE=" + stringParameter);
                return;
            }
            catch (Exception ex) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                }
                catch (Exception ex5) {}
                try {
                    if (connection != null) {
                        connection.setAutoCommit(true);
                    }
                }
                catch (Exception ex6) {}
                Log.debug("UM_ADV_GovrA010p.java flag[" + stringParameter23 + "] : " + ex.toString());
                CommonMessage.printMsg("", "4", ex.getMessage(), "", httpServletResponse);
                return;
            }
            finally {
                try {
                    if (connection != null) {
                        trx.close();
                    }
                }
                catch (Exception ex7) {}
            }
        }
        if (stringParameter23.equals("approval")) {
            final UM_URB_CERT um_URB_CERT11 = new UM_URB_CERT();
            try {
                final UM_Auth_Check um_Auth_Check = new UM_Auth_Check(httpServletRequest, httpServletResponse);
                if (stringParameter33.toLowerCase().equals("s")) {
                    um_Auth_Check.checkCookie("a", null, null);
                }
                else {
                    um_Auth_Check.checkCookie("k", null, null);
                }
                final String id = um_Auth_Check.getID();
                trx = new Trx(this, "USEMN");
                connection = trx.getConnection();
                connection.setAutoCommit(false);
                if (stringParameter24.equals("admit")) {
                    if (stringParameter33.toLowerCase().equals("s")) {
                        if (um_URB_CERT010.isDataExistOnEnterMaster(stringParameter7, connection) == 0) {
                            CommonMessage.printMsg("", "", "Không thể thực hiện phê duyệt chứng nhận số cho các Doanh nghiệp chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.", null, httpServletResponse);
                            return;
                        }
                    }
                    else if (um_URB_CERT010.isDataExistOnGigwanMaster(stringParameter, stringParameter7, connection) == 0) {
                        CommonMessage.printMsg("", "", "Không thể thực hiện phê duyệt chứng nhận số cho các cơ quan chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.", null, httpServletResponse);
                        return;
                    }
                    um_URB_CERT010.updateReception("Y", "", stringParameter20, stringParameter, stringParameter22, id, connection);
                    if (!stringParameter33.toLowerCase().equals("s")) {
                        um_URB_CERT010.updateKNAME(stringParameter, stringParameter4, connection);
                    }
                    if ("Y".equals(stringParameter26)) {
                        um_URB_CERT11.updateUseUserPasswd(stringParameter28, stringParameter27, connection);
                    }
                    connection.commit();
                    connection.setAutoCommit(true);
                    if (stringParameter16.equals("Y")) {}
                    CommonMessage.printMsg("", "5", "Xử lý phê duyệt xong.", "", httpServletResponse);
                }
                else if (stringParameter24.equals("deny")) {
                    if (stringParameter33.toLowerCase().equals("s")) {
                        if (um_URB_CERT010.isDataExistOnEnterMaster(stringParameter7, connection) == 0) {
                            CommonMessage.printMsg("", "", "Không thể thực hiện phê duyệt chứng nhận số cho các Doanh nghiệp chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.", null, httpServletResponse);
                            return;
                        }
                    }
                    else if (um_URB_CERT010.isDataExistOnGigwanMaster(stringParameter, stringParameter7, connection) == 0) {
                        CommonMessage.printMsg("", "", "Không thể thực hiện phê duyệt chứng nhận số cho các cơ quan chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.", null, httpServletResponse);
                        return;
                    }
                    um_URB_CERT010.updateReception("D", stringParameter21, stringParameter20, stringParameter, stringParameter22, id, connection);
                    connection.commit();
                    connection.setAutoCommit(true);
                    CommonMessage.printMsg("", "5", "Đã xử lý trả về.", "", httpServletResponse);
                }
                else if (stringParameter24.equals("modifyCERT")) {
                    um_URB_CERT010.updateCERT_ORG(stringParameter18, stringParameter20, stringParameter, stringParameter22, connection);
                    connection.commit();
                    connection.setAutoCommit(true);
                    CommonMessage.printMsg("", "5", "Cơ quan cấp chứng nhận đã được sửa.", "", httpServletResponse);
                }
                return;
            }
            catch (Exception ex2) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                }
                catch (Exception ex8) {}
                try {
                    if (connection != null) {
                        connection.setAutoCommit(true);
                    }
                }
                catch (Exception ex9) {}
                Log.debug("UM_ADV_GovrA010p.java flag[" + stringParameter23 + "] : " + ex2.toString());
                CommonMessage.printMsg("", "", ex2.getMessage(), null, httpServletResponse);
                return;
            }
            finally {
                try {
                    if (connection != null) {
                        trx.close();
                    }
                }
                catch (Exception ex10) {}
            }
        }
        if (stringParameter23.equals("DNSave")) {
            try {
                trx = new Trx(this, "USEMN");
                connection = trx.getConnection();
                connection.setAutoCommit(false);
                if (!stringParameter30.equals("") || !stringParameter31.equals("")) {
                    um_URB_CERT010.updateReceptionCert(stringParameter29, stringParameter30, stringParameter31, stringParameter32, g2bCode, stringParameter22, connection);
                    connection.commit();
                    connection.setAutoCommit(true);
                    httpServletResponse.sendRedirect("/AD/UM_ADJ_GovrL025p.jsp?g2bCode=" + g2bCode + "&dependCode=" + stringParameter31 + "&saupNo=" + stringParameter32);
                }
                return;
            }
            catch (Exception ex3) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                }
                catch (Exception ex11) {}
                try {
                    if (connection != null) {
                        connection.setAutoCommit(true);
                    }
                }
                catch (Exception ex12) {}
                Log.debug("UM_ADV_GovrA010p.java flag[" + stringParameter23 + "] : " + ex3.toString());
                CommonMessage.printMsg("", "4", ex3.getMessage(), "", httpServletResponse);
                return;
            }
            finally {
                try {
                    if (connection != null) {
                        trx.close();
                    }
                }
                catch (Exception ex13) {}
            }
        }
        if (stringParameter23.equals("Submit")) {
            try {
                trx = new Trx(this, "USEMN");
                connection = trx.getConnection();
                connection.setAutoCommit(false);
                Log.debug("UM_ADV_GovrA010p.java CERT_ORG[" + stringParameter18 + "] ");
                Log.debug("UM_ADV_GovrA010p.java recept[" + stringParameter20 + "] ");
                Log.debug("UM_ADV_GovrA010p.java G2BCODE[" + stringParameter + "] ");
                Log.debug("UM_ADV_GovrA010p.java dependCode[" + stringParameter22 + "] ");
                um_URB_CERT010.updateCERT_ORG(stringParameter18, stringParameter20, stringParameter, stringParameter22, connection);
                connection.commit();
                connection.setAutoCommit(true);
                CommonMessage.printMsg("", "5", "Cơ quan cấp chứng nhận đã được sửa.", "", httpServletResponse);
            }
            catch (Exception ex4) {
                try {
                    if (connection != null) {
                        connection.rollback();
                    }
                }
                catch (Exception ex14) {}
                try {
                    if (connection != null) {
                        connection.setAutoCommit(true);
                    }
                }
                catch (Exception ex15) {}
                Log.debug("UM_ADV_GovrA010p.java flag[" + stringParameter23 + "] : " + ex4.toString());
                CommonMessage.printMsg("", "4", ex4.getMessage(), "", httpServletResponse);
            }
            finally {
                try {
                    if (connection != null) {
                        trx.close();
                    }
                }
                catch (Exception ex16) {}
            }
        }
    }
}
