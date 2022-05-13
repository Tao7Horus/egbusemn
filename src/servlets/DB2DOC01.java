// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DB2DOC01 extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        final StringBuffer sb = new StringBuffer();
        String sql = null;
        String fileWrite = "";
        final PrintWriter out = res.getWriter();
        final String fileName = ComStr.checkNull(req.getParameter("fileName"), "");
        String saupNo = ComStr.checkNull(req.getParameter("saupNo"), "");
        String subDay = ComStr.checkNull(req.getParameter("subDay"), "");
        final String COUNT = "";
        while (true) {
            if (subDay != "") {
                subDay = ComStr.replace(subDay, "-", "");
                final String subDay_1 = subDay.substring(0, 4);
                final String subDay_2 = subDay.substring(4, 6);
                final String subDay_3 = subDay.substring(6, 8);
                subDay = String.valueOf(subDay_1) + "-" + subDay_2 + "-" + subDay_3;
                try {
                    trx = new Trx(this, "usemn");
                    con = trx.getConnection();
                    sb.append("\n\tSELECT U2.REG_DT,  U1.UPDATE_DT, \tU1.BIZ_CLS, ");
                    sb.append("\n          U1.BIZ_NM,  \t U1.BIZ_EN_NM, U1.BIZ_REG_NO,");
                    sb.append("\n\t\t   U1.COMMENCEMENT_DT,  U1.ADDR || ' ' || U1.DETAIL_ADDR ADDR, ");
                    sb.append("\n\t\t   U1.PHONE_NO,  U1.FAX,   U1.ESTABLISH_DT, U1.CORP_REG_NO,");
                    sb.append("\n\t\t   U1.CAPITAL,    U1.EMPLOYEE_COUNT,   U1.BIZ_CLS2 ");
                    sb.append("\n\tFROM  UM_SUPPLIER_ENTER_MAST  U1, UM_EDOC_STATE  U2");
                    sb.append("\n\tWHERE  U1.BIZ_REG_NO= U2.BIZ_REG_NO\t");
                    sb.append("\n\tAND    U2.MOD_CLS='2'  ");
                    sb.append("\n\tAND    U1.BIZ_REG_NO=? ");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    while (rs.next()) {
                        saupNo = rs.getString(6);
                        final String saupNo_1 = saupNo.substring(0, 3);
                        final String saupNo_2 = saupNo.substring(3, 5);
                        final String saupNo_3 = saupNo.substring(5, saupNo.length());
                        final String bubinNo = ComStr.checkNull(rs.getString(12), "");
                        String bubinNo_1 = "";
                        String bubinNo_2 = "";
                        if (bubinNo != null && bubinNo != "") {
                            bubinNo_1 = bubinNo.substring(0, 6);
                            bubinNo_2 = bubinNo.substring(6, bubinNo.length());
                        }
                        else {
                            bubinNo_1 = "";
                            bubinNo_2 = "";
                        }
                        final String Kiupgubun = rs.getString(3);
                        final String Kiupgubun_1 = Kiupgubun.substring(0, 1);
                        final String Kiupgubun_2 = Kiupgubun.substring(1, 2);
                        final String Kiupgubun_3 = Kiupgubun.substring(2, 3);
                        final String Kiupgubun_4 = Kiupgubun.substring(4, 5);
                        String gubun_1 = "";
                        String gubun_2 = "";
                        if (Kiupgubun != null && Kiupgubun_1.equals("1") && Kiupgubun_2.equals("1") && Kiupgubun_3.equals("1")) {
                            gubun_1 = "∨^∨^∨";
                        }
                        else if (Kiupgubun != null && Kiupgubun_1.equals("1") && Kiupgubun_2.equals("1")) {
                            gubun_1 = "∨^∨^";
                        }
                        else if (Kiupgubun != null && Kiupgubun_2.equals("1") && Kiupgubun_3.equals("1")) {
                            gubun_1 = "^∨^∨";
                        }
                        else if (Kiupgubun != null && Kiupgubun_1.equals("1") && Kiupgubun_3.equals("1")) {
                            gubun_1 = "∨^^∨";
                        }
                        else if (Kiupgubun != null && Kiupgubun_1.equals("1")) {
                            gubun_1 = "∨^^";
                        }
                        else if (Kiupgubun != null && Kiupgubun_2.equals("1")) {
                            gubun_1 = "^∨^";
                        }
                        else if (Kiupgubun != null && Kiupgubun_3.equals("1")) {
                            gubun_1 = "^^∨";
                        }
                        else {
                            gubun_1 = "^^";
                        }
                        if (Kiupgubun != null && Kiupgubun_4.equals("1")) {
                            gubun_2 = "∨^";
                        }
                        else {
                            gubun_2 = "^";
                        }
                        final String Kiupgubun2 = ComStr.checkNull(rs.getString(15), "");
                        String gubun_3 = "";
                        if (Kiupgubun2.equals("1")) {
                            gubun_3 = "V^^^^^";
                        }
                        else if (Kiupgubun2.equals("2")) {
                            gubun_3 = "^V^^^^";
                        }
                        else if (Kiupgubun2.equals("3")) {
                            gubun_3 = "^^V^^^";
                        }
                        else if (Kiupgubun2.equals("4")) {
                            gubun_3 = "^^^V^^";
                        }
                        else if (Kiupgubun2.equals("5")) {
                            gubun_3 = "^^^^V^";
                        }
                        else if (Kiupgubun2.equals("6")) {
                            gubun_3 = "^^^^^V";
                        }
                        else if (Kiupgubun2.equals("")) {
                            gubun_3 = "^^^^^";
                        }
                        String shinchungDay = ComStr.checkNull(rs.getString(1), "");
                        if (shinchungDay != "") {
                            shinchungDay = shinchungDay.substring(0, 10);
                        }
                        String renewDay = ComStr.checkNull(rs.getString(2), "");
                        if (renewDay != "") {
                            renewDay = renewDay.substring(0, 10);
                        }
                        String openDay = ComStr.checkNull(rs.getString(7), "");
                        if (openDay != "") {
                            openDay = openDay.substring(0, 10);
                        }
                        String bubinOpen = ComStr.checkNull(rs.getString(11), "");
                        if (bubinOpen != "") {
                            bubinOpen = bubinOpen.substring(0, 10);
                        }
                        fileWrite = String.valueOf(ComStr.checkNull(shinchungDay, "")) + "^" + ComStr.checkNull(subDay, "") + "^" + ComStr.checkNull(renewDay, "") + "^" + gubun_1 + "^" + gubun_2 + ComStr.checkNull(rs.getString(4), "") + "^" + ComStr.checkNull(rs.getString(5), "") + "^" + saupNo_1 + "-" + saupNo_2 + "-" + saupNo_3 + "^" + ComStr.checkNull(openDay, "") + "^" + ComStr.checkNull(rs.getString(8), "") + "^" + ComStr.checkNull(rs.getString(9), "") + "^" + ComStr.checkNull(rs.getString(10), "") + "^" + ComStr.checkNull(bubinOpen, "") + "^" + ComStr.checkNull(rs.getString(12), "") + "^" + ComStr.checkNull(rs.getString(13), "") + "^" + ComStr.checkNull(rs.getString(14), "") + "^" + gubun_3 + "^ \r\n";
                    }
                    rs.close();
                    psmt.close();
                    sb.setLength(0);
                    sql = "";
                    sb.append("\n SELECT * FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n (SELECT DECODE(A.CHANGE_CLS, 'I', 'Add', 'U', 'Update', 'D', 'Delete') CHANGE_CLS, A.PROPERTY_NM,                                           ");
                    sb.append("\n   DECODE(A.PROPERTY_NM, 'LICENSE_CD', (SELECT CD_NM FROM syn_PUB_CODE WHERE CD_CLS='GU9' AND CD= A.BEFORE_MOD_CONTENT),                   ");
                    sb.append("\n   'GOOD_CLS_NO', A.BEFORE_MOD_CONTENT, A.BEFORE_MOD_CONTENT) BEFORE_MOD_CONTENT, ");
                    sb.append("\n  DECODE(A.PROPERTY_NM, 'LICENSE_CD', (SELECT CD_NM FROM syn_PUB_CODE WHERE CD_CLS='GU9' AND CD= A.AFTER_MOD_CONTENT),                    ");
                    sb.append("\n  'GOOD_CLS_NO',A.AFTER_MOD_CONTENT , A.AFTER_MOD_CONTENT) AFTER_MOD_CONTENT   ");
                    sb.append("\n        FROM UM_BID_QUALIFY_FACTS_HIST A,                                                                                            ");
                    sb.append("\n             (SELECT MAX(PROCESS_DT) UDATE FROM UM_EDOC_STATE                                                                  ");
                    sb.append("\n\t\t\t \t\t WHERE  PROCESS_DT  IS NOT NULL                                                                                 ");
                    sb.append("\n\t\t\t\t\t AND   PROCESS_ST ='1'                                                                                          ");
                    sb.append("\n\t\t\t\t\t AND  MOD_CLS ='2'                                                                                      ");
                    sb.append("\n\t\t\t\t\t AND  BIZ_REG_NO = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
                    sb.append("\n\t\t\t\t\t) B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t           ");
                    sb.append("\n        WHERE A.BIZ_REG_NO = ? AND A.UPDATE_DT between B.UDATE  and B.UDATE + 5/(24*60*60))                                   ");
                    sb.append("\n\tWHERE BEFORE_MOD_CONTENT IS NOT NULL or AFTER_MOD_CONTENT IS NOT NULL");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    psmt.setString(2, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    if (rs != null) {
                        while (rs.next()) {
                            fileWrite = String.valueOf(fileWrite) + ComStr.checkNull(rs.getString(1), "") + "^" + ComStr.checkNull(rs.getString(2), "") + "^" + ComStr.checkNull(rs.getString(3), "") + "^" + ComStr.checkNull(rs.getString(4), "") + "^\r\n";
                        }
                        rs.close();
                    }
                    String beforegubun = "";
                    String aftergubun = "";
                    sql = "";
                    sb.setLength(0);
                    sb.append(" SELECT SUBSTR(BEFORE_MOD_CONTENT,5) ,SUBSTR(AFTER_MOD_CONTENT,5)  FROM UM_BID_QUALIFY_FACTS_HIST ");
                    sb.append(" WHERE BIZ_REG_NO = ? ");
                    sb.append(" AND TO_CHAR(UPDATE_DT, 'YYYY-MM-DD') = TO_CHAR(sysdate, 'YYYY-MM-DD') ");
                    sb.append(" AND PROPERTY_NM ='BIZ_CLS' ");
                    sb.append(" ORDER BY UPDATE_DT asc ");
                    sql = sb.toString();
                    psmt = con.prepareStatement(sql);
                    psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                    rs = psmt.executeQuery();
                    psmt.clearParameters();
                    if (rs != null) {
                        while (rs.next()) {
                            beforegubun = ComStr.checkNull(rs.getString(1), "");
                            aftergubun = ComStr.checkNull(rs.getString(2), "");
                        }
                        rs.close();
                    }
                    if (beforegubun.equals("0") && aftergubun.equals("1")) {
                        fileWrite = String.valueOf(fileWrite) + "Thêm^cung cấp sản phẩm^^hàng hóa nước ngoài^\r\n";
                    }
                    else if (beforegubun.equals("1") && aftergubun.equals("0")) {
                        fileWrite = String.valueOf(fileWrite) + "Xóa^cung cấp sản phẩm^hàng hóa nước ngoài^^\r\n";
                    }
                    psmt.close();
                    fileWrite = String.valueOf(fileWrite) + "//EOR//\r\n";
                }
                catch (SQLException e) {
                    Log.errors(this.getClass().getName() + " SQLException xuất hiện \n" + sql + " \n" + "số đăng ký kinh doanh=" + saupNo + " \n" + e);
                }
                catch (Exception e2) {
                    Log.errors(this.getClass().getName() + " Exception xuất hiện \r\n" + e2);
                    Log.errors(this.getClass().getName() + " Exception xuất hiện \n" + "số đăng ký kinh doanh=" + saupNo + " \n" + e2);
                }
                finally {
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
                    if (con != null) {
                        try {
                            con.close();
                        }
                        catch (Exception ex3) {}
                    }
                    if (trx != null) {
                        try {
                            trx.close();
                        }
                        catch (Exception ex4) {}
                    }
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
                if (con != null) {
                    try {
                        con.close();
                    }
                    catch (Exception ex7) {}
                }
                if (trx != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex8) {}
                }
                out.println(fileWrite);
                return;
            }
            continue;
        }
    }
}
