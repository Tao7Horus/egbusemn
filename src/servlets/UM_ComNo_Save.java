// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.util.CommonMessage;
import common.Log;
import java.net.URLEncoder;
import javax.servlet.ServletRequest;
import com.oreilly.servlet.ParameterParser;
import common.Trx;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ComNo_Save extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement psmt = null;
        PreparedStatement psmt2 = null;
        String sql = null;
        String sql2 = null;
        final StringBuffer sb = new StringBuffer();
        String mail = "";
        String dn = "";
        String refername = "";
        String refercode = "";
        String reguser = "";
        String comNo = "";
        String g2bCode = "";
        String Address = "";
        String saup_No = "";
        String flag = "";
        Label_1026: {
            try {
                trx = new Trx(this, "usemn");
                con = trx.getConnection();
                final ParameterParser psr = new ParameterParser((ServletRequest)req);
                mail = psr.getStringParameter("mail", "");
                dn = psr.getStringParameter("dn", "");
                refername = psr.getStringParameter("refername", "");
                refercode = psr.getStringParameter("refercode", "");
                reguser = psr.getStringParameter("reguser", "");
                comNo = psr.getStringParameter("comNo", "");
                g2bCode = psr.getStringParameter("g2bCode", "");
                Address = psr.getStringParameter("Address", "");
                saup_No = "";
                flag = "";
                sb.append(" SELECT 사업자등록번호 FROM 사용_공공기관마스터 WHERE 공공기관코드 = '" + g2bCode + "'\t\n");
                sql = sb.toString();
                psmt = con.prepareStatement(sql);
                rs = psmt.executeQuery();
                if (rs.next()) {
                    flag = rs.getString(1);
                    if (rs.wasNull()) {
                        flag = "NODATA";
                    }
                }
                if (flag.equals("NODATA")) {
                    try {
                        con.setAutoCommit(false);
                        sb.setLength(0);
                        sql2 = " UPDATE 사용_공공기관마스터 SET 사업자등록번호 = ? WHERE 공공기관코드 = ?\t\n";
                        psmt2 = con.prepareStatement(sql2);
                        psmt2.setString(1, comNo);
                        psmt2.setString(2, g2bCode);
                        psmt2.executeUpdate();
                        psmt2.clearParameters();
                        res.sendRedirect("/jsp/AD/RegUser2.jsp?mail=" + mail + "&dn=" + URLEncoder.encode(dn) + "&refername=" + refername + "&refercode=" + refercode + "&reguser=" + reguser + "&comNo=" + comNo + "&Address=" + Address + "&flag=NULL1");
                        con.commit();
                        con.setAutoCommit(true);
                        break Label_1026;
                    }
                    catch (Exception exmf) {
                        try {
                            if (con != null) {
                                con.rollback();
                            }
                        }
                        catch (Exception ex) {}
                        try {
                            if (con != null) {
                                con.setAutoCommit(true);
                            }
                        }
                        catch (Exception ex2) {}
                        throw new Exception(exmf.getMessage());
                    }
                }
                if (flag.equals(comNo)) {
                    res.sendRedirect("/jsp/AD/RegUser2.jsp?mail=" + mail + "&dn=" + URLEncoder.encode(dn) + "&refername=" + refername + "&refercode=" + refercode + "&reguser=" + reguser + "&comNo=" + comNo + "&Address=" + Address + "&flag=Equal");
                    break Label_1026;
                }
                res.sendRedirect("/jsp/AD/RegUser2.jsp?mail=" + mail + "&dn=" + URLEncoder.encode(dn) + "&refername=" + refername + "&refercode=" + refercode + "&reguser=" + reguser + "&comNo=" + comNo + "&Address=" + Address + "&flag=Error");
            }
            catch (SQLException exc) {
                Log.debug("UM_ComNo_Save.java g2bCode[" + g2bCode + "]: " + exc.toString());
                CommonMessage.printMsg("", "4", exc.getMessage(), "", res);
            }
            catch (Exception exc2) {
                Log.debug("UM_ComNo_Save.java g2bCode[" + g2bCode + "]: " + exc2.toString());
                CommonMessage.printMsg("", "4", exc2.getMessage(), "", res);
            }
            finally {
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
                if (psmt2 != null) {
                    try {
                        psmt2.close();
                    }
                    catch (Exception ex5) {}
                }
                if (con != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex6) {}
                }
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex7) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex8) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex9) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex10) {}
        }
    }
}
