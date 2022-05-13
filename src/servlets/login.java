// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.io.PrintWriter;
import common.Log;
import javax.servlet.http.Cookie;
import java.net.URLEncoder;
import common.Trx;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class login extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = res.getWriter();
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String sql = null;
        int passOKCount = 0;
        String id = null;
        String password = null;
        String sabun = null;
        String name = null;
        String buseo = null;
        String jichong = null;
        String jikchek = null;
        String userName = null;
        String userPhone = null;
        String userMail = null;
        String d_passwd = null;
        final HashMap user = new HashMap();
        id = ((req.getParameter("id") == null) ? "" : req.getParameter("id"));
        password = ((req.getParameter("password") == null) ? "" : req.getParameter("password"));
        Label_1718: {
            while (true) {
                try {
                    trx = new Trx(this, "usemn");
                    con = trx.getConnection();
                    sql = "select encrypt(비밀번호,'abcdef') from 담당자@DBL_USEMN_P_CUST WHERE 사번='" + id.trim() + "'";
                    psmt = con.prepareStatement(sql);
                    rs = psmt.executeQuery();
                    if (rs.next()) {
                        d_passwd = rs.getString(1);
                        if (rs != null) {
                            try {
                                rs.close();
                            }
                            catch (Exception ex) {}
                        }
                        if (psmt != null) {
                            try {
                                psmt.close();
                            }
                            catch (Exception ex2) {}
                        }
                        sql = "SELECT COUNT(*) FROM 담당자@DBL_USEMN_P_CUST WHERE 사번=?";
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, id);
                        rs = psmt.executeQuery();
                        if (rs.next() && rs.getInt(1) == 0) {
                            out.println("<script language='javascript'>");
                            out.println("\talert('Không tồn tại ID.\\r\\n hãy liên hệ với người phụ trách EDMS.');");
                            out.println("\thistory.back();");
                            out.println("</script>");
                        }
                        else {
                            if (rs != null) {
                                try {
                                    rs.close();
                                }
                                catch (Exception ex3) {}
                            }
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex4) {}
                            }
                            sql = "SELECT COUNT(*) FROM 담당자@DBL_USEMN_P_CUST WHERE 사번=? and encrypt(비밀번호,'abcdef')=?";
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, id);
                            psmt.setString(2, password);
                            rs = psmt.executeQuery();
                            if (rs.next()) {
                                passOKCount = rs.getInt(1);
                            }
                            if (rs != null) {
                                try {
                                    rs.close();
                                }
                                catch (Exception ex5) {}
                            }
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex6) {}
                            }
                            final String USER_IP = (req.getHeader("x-forwarded-for") == null) ? req.getRemoteAddr() : req.getHeader("x-forwarded-for");
                            int loginFailCount = 0;
                            String log = "";
                            sql = "SELECT 사번, 비밀번호, 로그인실패횟수 FROM 사용_운영자사이트로그인LOG WHERE 사번 = ? ";
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, id);
                            rs = psmt.executeQuery();
                            if (rs != null && rs.next()) {
                                loginFailCount = rs.getInt(3);
                                log = "Y";
                            }
                            if (passOKCount == 0) {
                                if (log.equals("Y")) {
                                    sql = " UPDATE 사용_운영자사이트로그인LOG  SET 비밀번호 = ?, IP = ?, 로그인실패횟수 = ?, 변경일자 = sysdate  WHERE 사번 = ? ";
                                    psmt.clearParameters();
                                    if (psmt != null) {
                                        try {
                                            psmt.close();
                                        }
                                        catch (Exception ex7) {}
                                    }
                                    psmt = con.prepareStatement(sql);
                                    psmt.setString(1, password);
                                    psmt.setString(2, USER_IP);
                                    psmt.setInt(3, loginFailCount + 1);
                                    psmt.setString(4, id);
                                    psmt.executeUpdate();
                                    psmt.clearParameters();
                                }
                                else {
                                    sql = " INSERT INTO 사용_운영자사이트로그인LOG (사번, 비밀번호, IP, 로그인실패횟수, 등록일자, 변경일자)  VALUES (?, ?, ?, ?, sysdate, sysdate) ";
                                    psmt.clearParameters();
                                    if (psmt != null) {
                                        try {
                                            psmt.close();
                                        }
                                        catch (Exception ex8) {}
                                    }
                                    psmt = con.prepareStatement(sql);
                                    psmt.setString(1, id);
                                    psmt.setString(2, password);
                                    psmt.setString(3, USER_IP);
                                    psmt.setString(4, "1");
                                    psmt.executeUpdate();
                                    psmt.clearParameters();
                                }
                                if (loginFailCount >= 4) {
                                    out.println("<script language='javascript'>");
                                    out.println("\talert(\"Đã nhập sai pass quá 5 lần.\\r\\n hãy liên hệ với người phụ trách EDMS về pass.\\r\\n Mọi thắc mắc xin liên hệ.\");");
                                    out.println("\thistory.back();");
                                    out.println("</script>");
                                }
                                else {
                                    out.println("<script language='javascript'>");
                                    out.println("\talert('PASSWORD가 일치하지 않습니다.');");
                                    out.println("\thistory.back();");
                                    out.println("</script>");
                                }
                            }
                            else {
                                if (loginFailCount < 5) {
                                    if (rs != null) {
                                        try {
                                            rs.close();
                                        }
                                        catch (Exception ex9) {}
                                    }
                                    if (psmt != null) {
                                        try {
                                            psmt.close();
                                        }
                                        catch (Exception ex10) {}
                                    }
                                    sql = "SELECT 사번, 성명, 부서코드, 지청구분, 직책코드, 전화번호, 인터넷계정 FROM 담당자@DBL_USEMN_P_CUST WHERE 사번=? and encrypt(비밀번호,'abcdef')=?";
                                    psmt = con.prepareStatement(sql);
                                    psmt.setString(1, id);
                                    psmt.setString(2, password);
                                    rs = psmt.executeQuery();
                                    if (rs.next()) {
                                        sabun = rs.getString(1);
                                        name = rs.getString(2);
                                        buseo = rs.getString(3);
                                        jichong = rs.getString(4);
                                        jikchek = rs.getString(5);
                                        userName = name;
                                        userPhone = rs.getString(6);
                                        userMail = rs.getString(7);
                                        userMail = String.valueOf(userMail) + "@pps.go.kr";
                                        user.put("eDocId", sabun);
                                        user.put("compName", "조달청");
                                        user.put("contact", userName);
                                        user.put("tel", userPhone);
                                        user.put("eMail", userMail);
                                        user.put("userRole", "S");
                                        user.put("deptCode", "Z023587");
                                        user.put("deptName", "정보기획과");
                                    }
                                    if (rs != null) {
                                        try {
                                            rs.close();
                                        }
                                        catch (Exception ex11) {}
                                    }
                                    if (psmt != null) {
                                        try {
                                            psmt.close();
                                        }
                                        catch (Exception ex12) {}
                                    }
                                    final HttpSession session = req.getSession(true);
                                    session.setAttribute("adminid", (Object)sabun);
                                    session.setAttribute("user", (Object)user);
                                    session.setAttribute("selfId", (Object)"MasterId");
                                    final String temp = String.valueOf(URLEncoder.encode(sabun)) + "^" + URLEncoder.encode(name) + "^" + URLEncoder.encode(buseo) + "^" + URLEncoder.encode(jichong) + "^" + URLEncoder.encode((jikchek == null) ? "NOT" : jikchek) + "^";
                                    final Cookie cook1 = new Cookie("OPE_Cooks", temp);
                                    cook1.setPath("/");
                                    cook1.setMaxAge(-1);
                                    res.addCookie(cook1);
                                    Log.debug("로그인성공 ::  사번:" + URLEncoder.encode(sabun) + " 이름:" + URLEncoder.encode(name) + " 부서:" + URLEncoder.encode(buseo) + " 지청:" + URLEncoder.encode(jichong) + " 직책:" + URLEncoder.encode((jikchek == null) ? "NOT" : jikchek));
                                    sql = " UPDATE 사용_운영자사이트로그인LOG  SET 로그인실패횟수 = null, 변경일자 = sysdate  WHERE 사번 = ? ";
                                    psmt = con.prepareStatement(sql);
                                    psmt.setString(1, id);
                                    psmt.executeUpdate();
                                    psmt.clearParameters();
                                    if (rs != null) {
                                        try {
                                            rs.close();
                                        }
                                        catch (Exception ex13) {}
                                    }
                                    if (psmt != null) {
                                        try {
                                            psmt.close();
                                        }
                                        catch (Exception ex14) {}
                                    }
                                    res.sendRedirect("/jsp/RA/master_middle.jsp");
                                    break Label_1718;
                                }
                                out.println("<script language=\"javascript\">");
                                out.println("\talert(\"Đã nhập sai pass quá 5 lần.\\r\\n hãy liên hệ với người phụ trách EDMS về pass.\\r\\n Mọi thắc mắc xin liên hệ.\");");
                                out.println("\thistory.back();");
                                out.println("</script>");
                            }
                        }
                    }
                    else {
                        Log.debug("Chưa nhận được pass mã hóa cho id: [" + id + "]");
                        out.println("<script language='javascript'>");
                        out.println("\talert('Chưa nhận được pass mã hóa.\\r\\n hãy liên hệ với người phụ trách EDMS.');");
                        out.println("\thistory.back();");
                        out.println("</script>");
                    }
                    return;
                }
                catch (Exception exc) {
                    Log.debug("login Exception : ");
                    Log.debug("Exception발생 사유 : " + exc.toString());
                    out.println("<script language='javascript'>");
                    out.println("\talert('Đã phát sinh lỗi hãy liên hệ với người quản lý.');");
                    out.println("\thistory.back();");
                    out.println("</script>");
                    continue;
                }
                finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        }
                        catch (Exception ex15) {}
                    }
                    if (psmt != null) {
                        try {
                            psmt.close();
                        }
                        catch (Exception ex16) {}
                    }
                    if (con != null) {
                        try {
                            trx.close();
                        }
                        catch (Exception ex17) {}
                    }
                }
                break;
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex18) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex19) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex20) {}
        }
    }
    
    public static String decode(final String a) {
        final ByteArrayOutputStream output = new ByteArrayOutputStream(a.length());
        for (int i = 0; i < a.length(); ++i) {
            final int c = a.charAt(i);
            if (c == 43) {
                output.write(32);
            }
            else if (c == 37) {
                final int c2 = Character.digit(a.charAt(++i), 16);
                final int c3 = Character.digit(a.charAt(++i), 16);
                output.write(c2 * 16 + c3);
            }
            else {
                output.write(c);
            }
        }
        return output.toString();
    }
}
