// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.util.CommonMessage;
import common.Trx;
import common.util.CommUtil;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_RAV_ConuB010c extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        Trx trx = null;
        Connection conn = null;
        final CommUtil com = new CommUtil();
        final String saupNo = com.retNull(req.getParameter("saupNo"));
        final String sinchungGubun = CommUtil.retSpace(req.getParameter("sinchungGubun"));
        final String sinchungDate = CommUtil.retSpace(req.getParameter("sinchungDate"));
        String status = "";
        String brsaupNo = "";
        String sql = "";
        ResultSet rs = null;
        ResultSet rs2 = null;
        PreparedStatement psmt = null;
        PreparedStatement psmt2 = null;
        Label_1210: {
            while (true) {
                try {
                    trx = new Trx(this, "usemn");
                    conn = trx.getConnection();
                    sql = " select PROCESS_ST from UM_EDOC_STATE   where BIZ_REG_NO = ?     and MOD_CLS  = ?     and REG_DT       = to_date(?, 'yyyy-mm-dd hh24:mi:ss') ";
                    psmt = conn.prepareStatement(sql);
                    psmt.setString(1, saupNo);
                    psmt.setString(2, sinchungGubun);
                    psmt.setString(3, sinchungDate);
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    if (rs.next()) {
                        status = rs.getString(1);
                    }
                    if (status.equals("1")) {
                        CommonMessage.printMsg(null, "7", "Đã xử lý chấp nhận từ trước.", "", res);
                    }
                    else if (status.equals("3")) {
                        CommonMessage.printMsg(null, "7", "Đã xử lý từ chối từ trước.", "", res);
                    }
                    else {
                        if (status.equals("0") || status.equals("2")) {
                            conn.setAutoCommit(false);
                            sql = "  SELECT a.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     FROM  UM_REC_SUPPLIER_ENTER_MAST a, UM_REC_REPR b, UM_ENTER_STATE c\t\t\t\t\t\t\t\t     WHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.BIZ_REG_NO = c.BIZ_REG_NO(+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND c.STATE_CLS(+) = '07'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND b.REPR_ISMAIN = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.CORP_REG_NO IN (  SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t       FROM UM_REC_SUPPLIER_ENTER_MAST\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t        WHERE BIZ_REG_NO  =?)\t\t\t\t\t\t\t\t\t\t\t     AND BID_ATTEND_QUALIFY_YN = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     ORDER BY a.BIZ_REG_NO ASC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
                            if (rs2 != null) {
                                try {
                                    rs2.close();
                                }
                                catch (Exception ex2) {}
                            }
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex3) {}
                            }
                            psmt = conn.prepareStatement(sql);
                            psmt.setString(1, saupNo);
                            rs2 = psmt.executeQuery();
                            psmt.clearParameters();
                            while (rs2.next()) {
                                brsaupNo = rs2.getString(1);
                                sql = " delete from UM_REC_SUPPLIER_ENTER_MAST where BIZ_REG_NO = '" + brsaupNo + "' ";
                                if (psmt2 != null) {
                                    try {
                                        psmt2.close();
                                    }
                                    catch (Exception ex4) {}
                                }
                                psmt2 = conn.prepareStatement(sql);
                                psmt2.executeUpdate();
                                psmt2.clearParameters();
                                sql = " delete from UM_REC_REPR where BIZ_REG_NO = '" + brsaupNo + "' ";
                                if (psmt2 != null) {
                                    try {
                                        psmt2.close();
                                    }
                                    catch (Exception ex5) {}
                                }
                                psmt2 = conn.prepareStatement(sql);
                                psmt2.executeUpdate();
                                psmt2.clearParameters();
                            }
                            sql = " delete from UM_REC_SUPPLIER_ENTER_MAST where BIZ_REG_NO = '" + saupNo + "' ";
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex6) {}
                            }
                            psmt = conn.prepareStatement(sql);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            sql = " delete from UM_REC_REPR where BIZ_REG_NO = '" + saupNo + "' ";
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex7) {}
                            }
                            psmt = conn.prepareStatement(sql);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            sql = " delete from UM_REC_FACTORY_INFO where BIZ_REG_NO = '" + saupNo + "' ";
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex8) {}
                            }
                            psmt = conn.prepareStatement(sql);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            sql = " delete from UM_REC_SUPPLIER_ENTER_ITEM where BIZ_REG_NO = '" + saupNo + "' ";
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex9) {}
                            }
                            psmt = conn.prepareStatement(sql);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            sql = " delete from um_rec_enter_std_cls where BIZ_REG_NO = '" + saupNo + "' ";
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex10) {}
                            }
                            psmt = conn.prepareStatement(sql);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            sql = " delete from UM_REC_BID_AGENT where BIZ_REG_NO = '" + saupNo + "' ";
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex11) {}
                            }
                            psmt = conn.prepareStatement(sql);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            sql = " update UM_EDOC_STATE set PROCESS_ST = '4', PROCESS_DT = sysdate   where BIZ_REG_NO = ?     and MOD_CLS  = ?     and REG_DT       = to_date(?, 'yyyy-mm-dd hh24:mi:ss') ";
                            if (psmt != null) {
                                try {
                                    psmt.close();
                                }
                                catch (Exception ex12) {}
                            }
                            psmt = conn.prepareStatement(sql);
                            psmt.setString(1, saupNo);
                            psmt.setString(2, sinchungGubun);
                            psmt.setString(3, sinchungDate);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            conn.commit();
                            conn.setAutoCommit(true);
                            printHtml("Hủy bỏ đã được xử lý.", "", res);
                        }
                        break Label_1210;
                    }
                    return;
                }
                catch (Exception ex) {
                    try {
                        if (conn != null) {
                            conn.rollback();
                        }
                    }
                    catch (SQLException ex13) {}
                    try {
                        if (conn != null) {
                            conn.setAutoCommit(true);
                        }
                    }
                    catch (SQLException ex14) {}
                    CommonMessage.printMsg(null, "", ex.getMessage(), null, res);
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
                    if (rs2 != null) {
                        try {
                            rs2.close();
                        }
                        catch (Exception ex17) {}
                    }
                    if (psmt2 != null) {
                        try {
                            psmt2.close();
                        }
                        catch (Exception ex18) {}
                    }
                    if (conn != null) {
                        try {
                            trx.close();
                        }
                        catch (Exception ex19) {}
                    }
                }
                
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex20) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex21) {}
        }
        if (rs2 != null) {
            try {
                rs2.close();
            }
            catch (Exception ex22) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex23) {}
        }
        if (conn != null) {
            try {
                trx.close();
            }
            catch (Exception ex24) {}
        }
    }
    
    private static void printHtml(final String dispmsg, final String toUrl, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html; charset=utf-8");
        final PrintWriter out = res.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Message</title>");
        out.println("<link rel=stylesheet type=text/css href=/css/TA.css>");
        out.println("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>");
        out.println("<link rel=stylesheet type=text/css href=/css/TA.css>");
        out.println("<script language=javascript>");
        out.println("   function toClose(){");
        out.println("       alert(\"" + dispmsg + "\");");
        out.println("       location.href = \"" + toUrl + "\";");
        out.println("       return;");
        out.println("   }");
        out.println("</script>");
        out.println("</head>");
        out.println("<body bgcolor=#FFFFFF text=#000000 topmargin=0 leftmargin=0 marginwidth=0 marginheight=0>");
        out.println("<table width=823 border=0 cellpadding=0 cellspacing=0 height=100%  background='/img/bg_sub.gif'>");
        out.println("    <tr valign=bottom height=57> ");
        out.println("      <td rowspan=2><img src='/img/sub_title_01.jpg'></td>");
        out.println("      <td colspan=2 background='/img/sub_title_02.jpg' width=788 height=57 class=HEADLINE> ");
        out.println("        &nbsp; Thay đổi thông tin tư cách đấu thầu</td>");
        out.println("    </tr>");
        out.println("    <tr height=7>");
        out.println("      <td colspan=2><img src='/img/sub_title_03.jpg'></td>");
        out.println("    </tr>");
        out.println("    <tr height=21> ");
        out.println("      <td colspan=3></td>");
        out.println("    </tr>");
        out.println("    <tr valign=top>");
        out.println("       <td width=35></td>");
        out.println("       <td width=760>");
        out.println("       <table width='100%' cellpadding='2' cellspacing='1'>");
        out.println("       <tr>");
        out.println("        <td height='4' class='LINE' colspan='2'></td>");
        out.println("       </tr>");
        out.println("       <tr>");
        out.println("        <td class='tdb' align='left'>");
        out.println("            ▷ Yêu cầu đăng ký/thay đổi đã được hủy. <br>");
        out.println("        </td>");
        out.println("        <tr>");
        out.println("        <td height='2' class='LINE' ></td>");
        out.println("        </tr>");
        out.println("        </table>");
        out.println("    </tr>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}
