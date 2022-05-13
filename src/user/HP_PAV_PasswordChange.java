// 
// Decompiled by Procyon v0.5.30
// 

package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.util.CommonMessage;
import common.Log;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import secu.lib.MessageDigest;
import common.Trx;
import secu.lib.Secu;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class HP_PAV_PasswordChange extends HttpServlet
{
    public String[] getParams(final HttpServletRequest req, final String param, final String init) throws UnsupportedEncodingException {
        String[] res = (String[])null;
        res = req.getParameterValues(param);
        if (res != null) {
            for (int i = 0; i < res.length; ++i) {
                if (res[i] == null || res[i].equals("null") || res[i].equals("")) {
                    res[i] = init;
                }
                else {
                    res[i] = new String(res[i]);
                }
            }
        }
        return res;
    }
    
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt1 = null;
        PreparedStatement psmt2 = null;
        ResultSet rs = null;
        String query1 = null;
        String query2 = null;
        String userID = "";
        String userpasswd = "";
        String newuserpasswd = "";
        String type = "";
        final String url = "/jsp/RA/user/HP_PAJ_UserMsg.jsp";
        String msg = "";
        String pass = "";
        final Secu secu = new Secu(1);
        while (true) {
            try {
                trx = new Trx(this, "usemn");
                con = trx.getConnection();
                userID = ((req.getParameter("userID") == null) ? "" : req.getParameter("userID"));
                userpasswd = ((req.getParameter("userpasswd") == null) ? "" : req.getParameter("userpasswd"));
                newuserpasswd = ((req.getParameter("newuserpasswd") == null) ? "" : req.getParameter("newuserpasswd"));
                type = ((req.getParameter("type") == null) ? "" : req.getParameter("type"));
                final MessageDigest md = new MessageDigest(secu);
                userpasswd = md.create(userpasswd);
                newuserpasswd = md.create(newuserpasswd);
                query1 = " SELECT 비밀번호 FROM 사용_부가사용자 WHERE 아이디 = ? ";
                psmt1 = con.prepareStatement(query1);
                psmt1.setString(1, isStrNull(userID));
                rs = psmt1.executeQuery();
                String userpasswd2 = "";
                if (rs.next()) {
                    userpasswd2 = rs.getString(1);
                }
                psmt1.clearParameters();
                if (!userpasswd.equals(userpasswd2)) {
                    msg = "현재 비밀번호가 틀립니다.<br/>다시 입력해 주십시오.<br/><br/>";
                    pass = "/jsp/RA/user/HP_PAJ_PasswordChange.jsp?type=" + type + "&userID=" + userID;
                    req.setAttribute("msg", (Object)msg);
                    req.setAttribute("pass", (Object)pass);
                    req.setAttribute("gubun", (Object)"pw_change");
                    final RequestDispatcher dispatcher = req.getRequestDispatcher(url);
                    dispatcher.forward((ServletRequest)req, (ServletResponse)res);
                    return;
                }
                con.setAutoCommit(false);
                query2 = " UPDATE 사용_부가사용자  SET 비밀번호 = ?, 변경일자 = SYSDATE  WHERE 아이디 = ? ";
                psmt2 = con.prepareStatement(query2);
                psmt2.setString(1, isStrNull(newuserpasswd));
                psmt2.setString(2, isStrNull(userID));
                psmt2.executeUpdate();
                con.commit();
                con.setAutoCommit(true);
                msg = "<b>" + userID + "</b> 님의 비밀번호를<br/>성공적으로 변경하였습니다.<br/><br/>";
                if (type.equals("GeneralChange")) {
                    pass = "/servlet/user.HP_PAV_GeneralChange.java?userid=" + userID;
                }
                else if (type.equals("GovChange")) {
                    pass = "/servlet/user.HP_PAV_GovChange.java?userid=" + userID;
                }
                else if (type.equals("LoginGovChange")) {
                    pass = "/servlet/user.HP_PAV_LoginGovChange.java";
                }
                else if (type.equals("CompChange")) {
                    pass = "/servlet/user.HP_PAV_CompChange.java?userid=" + userID;
                }
                req.setAttribute("msg", (Object)msg);
                req.setAttribute("pass", (Object)pass);
                req.setAttribute("gubun", (Object)"pw_change");
                final RequestDispatcher dispatcher = req.getRequestDispatcher(url);
                dispatcher.forward((ServletRequest)req, (ServletResponse)res);
            }
            catch (SQLException exc) {
                try {
                    con.rollback();
                    con.setAutoCommit(true);
                }
                catch (SQLException e) {
                    Log.debug("HP_PAV_PasswordChange.doPost block SQLException : Transaction Rollback간에 SQLException 발생함");
                    Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                    e.printStackTrace();
                    CommonMessage.printMsg("에러 발생1", "3", "비밀번호 변경 처리 중 에러가 발생했습니다. 다시 변경해 주시기 바랍니다.", "/jsp/RA/user/HP_PAJ_PasswordChange.jsp", res);
                    continue;
                }
                Log.debug("HP_PAV_PasswordChange.doPost block SQLException : ");
                Log.debug("Exception발생 사유 : " + exc.toString() + exc.getErrorCode() + exc.getSQLState());
                exc.printStackTrace();
                CommonMessage.printMsg("에러 발생2", "3", "비밀번호 변경 처리 중 에러가 발생했습니다. 다시 변경해 주시기 바랍니다.", "/jsp/RA/user/HP_PAJ_PasswordChange.jsp", res);
            }
            catch (Exception exc2) {
                try {
                    con.rollback();
                    con.setAutoCommit(true);
                }
                catch (SQLException e) {
                    Log.debug("HP_PAV_PasswordChange.doPost block Exception : Transaction Rollback간에 Exception 발생함");
                    Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                    e.printStackTrace();
                    CommonMessage.printMsg("에러 발생3", "3", "비밀번호 변경 처리 중 에러가 발생했습니다. 다시 변경해 주시기 바랍니다.", "/jsp/RA/user/HP_PAJ_PasswordChange.jsp", res);
                    continue;
                }
                Log.debug("HP_PAV_PasswordChange.doPost block Exception : ");
                Log.debug("Exception발생 사유 : " + exc2.toString());
                exc2.printStackTrace();
                CommonMessage.printMsg("에러 발생4", "3", "비밀번호 변경 처리 중 에러가 발생했습니다. 다시 변경해 주시기 바랍니다.", "/jsp/RA/user/HP_PAJ_PasswordChange.jsp", res);
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex) {}
                }
                if (psmt1 != null) {
                    try {
                        psmt1.close();
                    }
                    catch (Exception ex2) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex3) {}
                }
                if (con != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex4) {}
                }
            }
            break;
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex5) {}
        }
        if (psmt1 != null) {
            try {
                psmt1.close();
            }
            catch (Exception ex6) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex7) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex8) {}
        }
    }
    
    public static String isStrNull(final String value) {
        return (value.trim().length() == 0) ? "" : value.trim();
    }
}
