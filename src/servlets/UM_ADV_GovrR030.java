// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import common.util.CommServer;
import beans.Common_ComBo;
import common.WebUtil;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADV_GovrR030 extends HttpServlet
{
    public void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        throw new Error("Unresolved compilation problems: \n\tThe type CommUtil is ambiguous\n\tThe type CommonMessage is ambiguous\n\tSyntax error, insert \"AssignmentOperator Expression\" to complete Assignment\n\tSyntax error, insert \";\" to complete Statement\n\t코드구분 cannot be resolved\n\tThe type CommonMessage is ambiguous\n");
    }
    
    private void headHtml(final PrintWriter out, final String desformat, final String province) throws Exception {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Bảng tổng quát bên mời thầu</title>");
        out.println("<meta http-equiv=Content-Type content=\"text/html; charset=utf_8\">");
        if (!desformat.equalsIgnoreCase("EXCEL")) {
            out.println("<LINK REL=STYLESHEET TYPE=text/css HREF=/css/EP.css>");
        }
        else {
            final WebUtil cssStype = new WebUtil();
            out.println(cssStype.cssStype());
        }
        out.println("</head>");
        out.println("<body bgcolor=white text=black>");
        out.println("<table\tx:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width='300' align='center'>");
        out.println("<tr>");
        out.println("  <td bgcolor='#EDEDED' height='40' style='border:.5pt\tsolid windowtext;verticla-align:center;' colspan=10>");
        out.println("    <p align='center'><font size='5' face='굴림체'><b>Bảng tổng quát bên mời thầu</b></font></p></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=95% align=center>");
        out.println("  <tr>");
        out.println("    <td colspan=10 align=right><font size=2>Chi nhánh địa phương<b></b>:" + new Common_ComBo().getCodeName("J03", province) + "</font></td>");
        out.println("  <tr>");
        out.println("    <td colspan=10 align=right><font size=2>Ngày<b></b>:" + new CommServer().getCurrDate(0, 0) + "</font></td>");
        out.println("  </tr>");
        out.println("</table>");
        out.println("<br>");
    }
    
    private void endHtml(final PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
    
    private void displayResult(final PrintWriter out, final ResultSet rest, final Connection conn, final String dateGubun) throws Exception {
        int fieldSeq = 0;
        out.println("<table x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width=95% align=center>");
        out.println("  <tr bgcolor='#EDEDED'>");
        out.println("    <td class=retdac>Số</td>");
        out.println("    <td class=retdac>Mã bên mời thầu</td>");
        out.println("    <td class=retdac>Tên cơ quan bên mời thầu</td>");
        out.println("    <td class=retdac>Số ĐKKD</td>");
        out.println("    <td class=retdac>Mã kế toán</td>");
        out.println("    <td class=retdac>Phân loại nhượng quyền</td>");
        out.println("    <td class=retdac>Số bưu điện</td>");
        out.println("    <td class=retdac>Địa chỉ</td>");
        out.println("    <td class=retdac>Số điện thoại</td>");
        if (dateGubun.equals("1")) {
            out.println("    <td class=retdac>Ngày đăng kí</td>");
            fieldSeq = 8;
        }
        else if (dateGubun.equals("2")) {
            out.println("    <td class=retdac>Ngày tháng chỉnh sửa</td>");
            fieldSeq = 9;
        }
        out.println("    <td class=retdac>Phân loại cơ quan trực thuộc</td>");
        out.println("  </tr>");
        int i = 1;
        while (rest.next()) {
            String saupNo = rest.getString(11);
            if (saupNo == null || saupNo.equals("")) {
                saupNo = "";
            }
            out.println("<tr>");
            out.println("    <td class=retdbc>" + rest.getString(1) + "</td>");
            out.println("    <td class=retdbc>" + rest.getString(2) + "</td>");
            out.println("    <td class=retdb>" + rest.getString(3) + "</td>");
            out.println("    <td class=retdb>" + saupNo + "</td>");
            out.println("    <td class=retdb>" + this.getFundCode(conn, rest.getString(2)) + "</td>");
            out.println("    <td class=retdbc>" + rest.getString(4) + "</td>");
            out.println("    <td class=retdb>" + rest.getString(5) + "</td>");
            out.println("    <td class=retdb>" + rest.getString(6) + "</td>");
            out.println("    <td class=retdbc>" + rest.getString(7) + "</td>");
            out.println("    <td class=retdbc>" + rest.getString(fieldSeq) + "</td>");
            out.println("    <td class=retdb>" + rest.getString(10) + "</td>");
            out.println("</tr>");
            ++i;
        }
        if (i == 1) {
            out.println("  <tr>");
            out.println("  <td colspan=11 class=retdbc>Không có kết quả tìm kiếm.</td>");
            out.println("  </tr>");
        }
        out.println("</table>");
        out.println("<br>");
    }
    
    private String getFundCode(final Connection conn, final String mCode) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rest = null;
        final String sql = " select b.Tên mã from UM_PUB_INSTITU_ACCT_CD a , syn_공동코드 b where INSTITU_CD=? and  b.Phân loại mã='J34' and a.INSTITU_ACCT_CD=b.Mã số";
        String result = "";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mCode);
            rest = pstmt.executeQuery();
            while (rest.next()) {
                result = String.valueOf(result) + rest.getString(1) + " ";
            }
        }
        finally {
            try {
                if (rest != null) {
                    rest.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (rest != null) {
                rest.close();
            }
        }
        catch (Exception ex3) {}
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        }
        catch (Exception ex4) {}
        return result;
    }
}
