// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.Statement;
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

public class new_login extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        final PrintWriter out = res.getWriter();
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt1 = null;
        PreparedStatement psmt2 = null;
        PreparedStatement psmt3 = null;
        PreparedStatement psmt4 = null;
        PreparedStatement psmt5 = null;
        PreparedStatement psmt6 = null;
        PreparedStatement psmt7 = null;
        Statement smt4 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        String sql = null;
        String sql2 = null;
        String sql3 = null;
        String sql4 = null;
        String sql5 = null;
        String sql6 = null;
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
    Label_1856:
        while (true) {
            try {
                trx = new Trx(this, "usemn");
                con = trx.getConnection();
                sql2 = "select encrypt(비밀번호,'abcdef')  from 담당자@DBL_USEMN_P_CUST WHERE 사번='" + id.trim() + "'";
                smt4 = con.createStatement();
                rs2 = smt4.executeQuery(sql2);
                if (rs2.next()) {
                    d_passwd = rs2.getString(1);
                    sql = "SELECT COUNT(*) FROM 담당자@DBL_USEMN_P_CUST WHERE 사번=?";
                    psmt1 = con.prepareStatement(sql);
                    psmt1.setString(1, id);
                    rs = psmt1.executeQuery();
                    if (!rs.next() || rs.getInt(1) != 0) {
                        sql = "SELECT COUNT(*) FROM 담당자@DBL_USEMN_P_CUST WHERE 사번=? and encrypt(비밀번호,'abcdef')=?";
                        psmt2 = con.prepareStatement(sql);
                        psmt2.setString(1, id);
                        psmt2.setString(2, password);
                        rs = psmt2.executeQuery();
                        final String USER_IP = (req.getHeader("x-forwarded-for") == null) ? req.getRemoteAddr() : req.getHeader("x-forwarded-for");
                        int loginFailCount = 0;
                        String log = "";
                        sql3 = "SELECT 사번, 비밀번호, 로그인실패횟수 FROM 사용_운영자사이트로그인LOG WHERE 사번 = ? ";
                        psmt4 = con.prepareStatement(sql3);
                        psmt4.setString(1, id);
                        rs3 = psmt4.executeQuery();
                        if (rs3 != null && rs3.next()) {
                            loginFailCount = rs3.getInt(3);
                            log = "Y";
                        }
                        if (rs.next()) {
                            if (rs.getInt(1) == 0) {
                                if (log.equals("Y")) {
                                    sql5 = " UPDATE 사용_운영자사이트로그인LOG  SET 비밀번호 = ?, IP = ?, 로그인실패횟수 = ?, 변경일자 = sysdate  WHERE 사번 = ? ";
                                    psmt6 = con.prepareStatement(sql5);
                                    psmt6.setString(1, password);
                                    psmt6.setString(2, USER_IP);
                                    psmt6.setInt(3, loginFailCount + 1);
                                    psmt6.setString(4, id);
                                    psmt6.executeUpdate();
                                    psmt6.clearParameters();
                                    if (psmt6 != null) {
                                        psmt6.close();
                                    }
                                }
                                else {
                                    sql4 = " INSERT INTO 사용_운영자사이트로그인LOG (사번, 비밀번호, IP, 로그인실패횟수, 등록일자, 변경일자)  VALUES (?, ?, ?, ?, sysdate, sysdate) ";
                                    psmt5 = con.prepareStatement(sql4);
                                    psmt5.setString(1, id);
                                    psmt5.setString(2, password);
                                    psmt5.setString(3, USER_IP);
                                    psmt5.setString(4, "1");
                                    psmt5.executeUpdate();
                                    psmt5.clearParameters();
                                    if (psmt5 != null) {
                                        psmt5.close();
                                    }
                                }
                                if (psmt4 != null) {
                                    psmt4.close();
                                }
                                if (loginFailCount >= 4) {
                                    out.println("<script language='javascript'>");
                                    out.println("\talert('5회 이상 PASSWORD를 잘못 입력하셨습니다. 사번에 대한 비밀번호는 EDMS 담당자에게 문의 바랍니다.');");
                                    out.println("\thistory.back();");
                                    out.println("</script>");
                                    break Label_0304;
                                }
                                out.println("<script language='javascript'>");
                                out.println("\talert('PASSWORD가 일치하지 않습니다.');");
                                out.println("\thistory.back();");
                                out.println("</script>");
                                break Label_0304;
                            }
                            else if (loginFailCount >= 5) {
                                out.println("<script language='javascript'>");
                                out.println("\talert('5회 이상 PASSWORD를 잘못 입력하셨습니다. 사번에 대한 비밀번호는 EDMS 담당자에게 문의 바랍니다.');");
                                out.println("\thistory.back();");
                                out.println("</script>");
                                break Label_0304;
                            }
                        }
                        sql = "SELECT 사번, 성명, 부서코드, 지청구분, 직책코드, 전화번호, 인터넷계정 FROM 담당자@DBL_USEMN_P_CUST WHERE 사번=? and encrypt(비밀번호,'abcdef')=?";
                        psmt3 = con.prepareStatement(sql);
                        psmt3.setString(1, id);
                        psmt3.setString(2, password);
                        rs = psmt3.executeQuery();
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
                        sql6 = " UPDATE 사용_운영자사이트로그인LOG  SET 로그인실패횟수 = null, 변경일자 = sysdate  WHERE 사번 = ? ";
                        psmt7 = con.prepareStatement(sql6);
                        psmt7.setString(1, id);
                        psmt7.executeUpdate();
                        psmt7.clearParameters();
                        if (psmt7 != null) {
                            psmt7.close();
                        }
                        res.sendRedirect("/jsp/RA/new_master_middle.jsp");
                        break Label_1856;
                    }
                    out.println("<script language='javascript'>");
                    out.println("\talert('ID가 존재하지 않습니다.');");
                    out.println("\thistory.back();");
                    out.println("</script>");
                }
                else {
                    Log.debug("id [" + id + "]에 대한 암호화된 비밀번호 얻지 못함");
                    out.println("<script language='javascript'>");
                    out.println("\talert('암호화된 비밀번호를 얻지 못했습니다.');");
                    out.println("\thistory.back();");
                    out.println("</script>");
                }
                return;
            }
            catch (Exception exc) {
                Log.debug("UM_URV_UserA010c Exception : ");
                Log.debug("Exception발생 사유 : " + exc.toString());
                out.println("<script language='javascript'>");
                out.println("\talert('장애가 발생했습니다. 관리자에게 문의해 주십시요.');");
                out.println("\thistory.back();");
                out.println("</script>");
                continue;
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex) {}
                }
                if (rs2 != null) {
                    try {
                        rs2.close();
                    }
                    catch (Exception ex2) {}
                }
                if (rs3 != null) {
                    try {
                        rs3.close();
                    }
                    catch (Exception ex3) {}
                }
                if (psmt1 != null) {
                    try {
                        psmt1.close();
                    }
                    catch (Exception ex4) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex5) {}
                }
                if (psmt3 != null) {
                    try {
                        psmt3.close();
                    }
                    catch (Exception ex6) {}
                }
                if (psmt4 != null) {
                    try {
                        psmt4.close();
                    }
                    catch (Exception ex7) {}
                }
                if (psmt5 != null) {
                    try {
                        psmt5.close();
                    }
                    catch (Exception ex8) {}
                }
                if (psmt6 != null) {
                    try {
                        psmt6.close();
                    }
                    catch (Exception ex9) {}
                }
                if (psmt7 != null) {
                    try {
                        psmt7.close();
                    }
                    catch (Exception ex10) {}
                }
                if (smt4 != null) {
                    try {
                        smt4.close();
                    }
                    catch (Exception ex11) {}
                }
                if (con != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex12) {}
                }
            }
            break;
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex13) {}
        }
        if (rs2 != null) {
            try {
                rs2.close();
            }
            catch (Exception ex14) {}
        }
        if (rs3 != null) {
            try {
                rs3.close();
            }
            catch (Exception ex15) {}
        }
        if (psmt1 != null) {
            try {
                psmt1.close();
            }
            catch (Exception ex16) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex17) {}
        }
        if (psmt3 != null) {
            try {
                psmt3.close();
            }
            catch (Exception ex18) {}
        }
        if (psmt4 != null) {
            try {
                psmt4.close();
            }
            catch (Exception ex19) {}
        }
        if (psmt5 != null) {
            try {
                psmt5.close();
            }
            catch (Exception ex20) {}
        }
        if (psmt6 != null) {
            try {
                psmt6.close();
            }
            catch (Exception ex21) {}
        }
        if (psmt7 != null) {
            try {
                psmt7.close();
            }
            catch (Exception ex22) {}
        }
        if (smt4 != null) {
            try {
                smt4.close();
            }
            catch (Exception ex23) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex24) {}
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
