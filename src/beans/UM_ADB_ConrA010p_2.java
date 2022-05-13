// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import common.Trx;
import java.util.Vector;
import java.io.PrintWriter;
import entity.UM_RAE_ConuB010b;
import common.Log;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADB_ConrA010p_2 extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) {
        try {
            req.setCharacterEncoding("utf-8");
            final String saupNo = this.htmlEncode(req.getParameter("saupNo"));
            final String sangho = this.htmlEncode(req.getParameter("sangho"));
            final String sdate = this.htmlEncode(req.getParameter("sdate"));
            final String edate = this.htmlEncode(req.getParameter("edate"));
            final String status = this.htmlEncode(req.getParameter("status"));
            final String checkYN = this.htmlEncode(req.getParameter("checkYN"));
            final String flag = "1";
            final String pmstatus = this.htmlEncode(req.getParameter("pmstatus"));
            Log.debug(saupNo);
            Log.debug(sangho);
            Log.debug(sdate);
            Log.debug(edate);
            Log.debug(status);
            Log.debug(flag);
            Log.debug(pmstatus);
            Log.debug(checkYN);
            final UM_RAE_ConuB010b[] ettlist = this.getResultListPM_Excel(1, 10, saupNo, sangho, sdate, edate, status, pmstatus, checkYN);
            String statusStr = "";
            String pmstatusStr = "";
            if ("A".equals(pmstatus)) {
                pmstatusStr = "Tất cả";
            }
            if ("B".equals(pmstatus)) {
                pmstatusStr = "Chưa thanh toán";
            }
            if ("C".equals(pmstatus)) {
                pmstatusStr = "Đã thanh toán";
            }
            if ("A".equals(status)) {
                statusStr = "Tất cả";
            }
            if ("Y".equals(status)) {
                statusStr = "Đã phê duyệt";
            }
            if ("N".equals(status)) {
                statusStr = "Chưa phê duyệt";
            }
            if ("E".equals(status)) {
                statusStr = "Bảo lưu";
            }
            if ("D".equals(status)) {
                statusStr = "Từ chối";
            }
            if ("C".equals(status)) {
                statusStr = "Hủy bỏ";
            }
            String strYN = "";
            if ("Y".equals(checkYN)) {
                strYN = "Đăng ký thử nghiệm";
            }
            if ("N".equals(checkYN)) {
                strYN = "Đăng ký thực";
            }
            res.setContentType("application/vnd.ms-excel; charset=utf-8");
            res.setHeader("Content-Disposition", "attachment; filename=report.xls");
            final PrintWriter out = res.getWriter();
            this.headHtml(out);
            this.infoHtml(out, statusStr, pmstatusStr, sdate, edate, strYN);
            this.dispResultSet(ettlist, out);
            this.endHtml(out);
        }
        catch (Exception e) {
            Log.errors(this, e, "UM_ADB_ConrA010p_2.service() Exception block");
        }
    }
    
    private void headHtml(final PrintWriter out) {
        final String title = "Tổng hợp thông tin nhà thầu ";
        out.println("<html>");
        out.println("<head>");
        out.println("<meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">");
        out.println("<title>Tổng hợp thông tin nhà thầu </title>");
        out.println("</head>");
        out.println("<body bgcolor=white text=black>");
        out.println("<table\tx:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' width='80%' align='center'>");
        out.println("<tr style='padding-top:4.0pt'>");
        out.println("  <td bgcolor='#EDEDED' height='45' style='border:.5pt\tsolid windowtext;verticla-align:center;' colspan=7>");
        out.println("    <p align='center'><font size='4' face='Arial'><b>" + title + "</b></font></p></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<br>");
    }
    
    private void infoHtml(final PrintWriter out, final String method, final String item, final String dateForm, final String dateTo, final String checkYN) {
        out.println("<table\tx:str border=1 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' align=center width=60%>");
        out.println("<tr>");
        out.println("<td>");
        out.println("Thời gian ");
        out.println("</td>");
        out.println("<td>");
        out.println(String.valueOf(dateForm) + " - " + dateTo);
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("Tình trạng đăng kí ");
        out.println("</td>");
        out.println("<td>");
        out.println(item);
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("Tình trạng thanh toán ");
        out.println("</td>");
        out.println("<td>");
        out.println(method);
        out.println("</td>");
        out.println("</tr>");
        out.println("<tr>");
        out.println("<td>");
        out.println("Loại đăng ký ");
        out.println("</td>");
        out.println("<td>");
        out.println(checkYN);
        out.println("</td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("<br>");
        out.println("<br>");
    }
    
    private void dispResultSet(final UM_RAE_ConuB010b[] listpm, final PrintWriter out) throws Exception {
        out.println("<table\tx:str border=1 cellpadding=0 cellspacing=0 style='border-collapse: collapse;' align=center width=95%>");
        out.println("  <tr>");
        out.println("\t <td bgcolor='#EDEDED' ><b>STT</b></td>");
        out.println("\t <td bgcolor='#EDEDED' ><b>Tên công ty/ Tên nhà thầu</b></td>");
        out.println("\t <td bgcolor='#EDEDED' ><b>Số ĐKKD/MST</b></td>");
        out.println("\t <td bgcolor='#EDEDED' ><b>Tên lãnh đạo</b></td>");
        out.println("\t <td bgcolor='#EDEDED' ><b>Số điện thoại</b></td>");
        out.println("\t <td bgcolor='#EDEDED' ><b>Email</b></td>");
        out.println("  </tr>");
        final int count = listpm.length;
        for (int i = 0; i < count; ++i) {
            out.println("  <tr>");
            out.println("\t <td width=5%>" + (i + 1) + "</td>");
            out.println("    <td>&nbsp;" + this.isNull(listpm[i].getSangho()) + "</td>");
            out.println("    <td align=left>&nbsp;" + this.isNull(listpm[i].getSaupNo()) + "</td>");
            out.println("    <td>&nbsp;" + this.isNull(listpm[i].getCeoName()) + "</td>");
            out.println("    <td align=left>&nbsp;" + this.isNull(listpm[i].getCeoPhone()) + "</td>");
            out.println("    <td>" + this.isNull(listpm[i].getCeoMail()) + "</td>");
            out.println("  </tr>");
        }
        if (count == 0) {
            out.println("  <tr>");
            out.println("  <td>Không có thông tin.</td>");
            out.println("  </tr>");
        }
        out.println("</table>");
    }
    
    private void endHtml(final PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
    
    private String isNull(String str) {
        if (str == null) {
            str = " ";
        }
        return str;
    }
    
    private String htmlEncode(final String s) {
        if (s == null || s.equals("")) {
            return s;
        }
        final StringBuffer b = new StringBuffer(s.length());
        final String specialCharacters = "#&\"'<>";
        for (int i = 0; i < s.length(); ++i) {
            final char ch = s.charAt(i);
            if (specialCharacters.indexOf(ch) > -1) {
                b.append("&#").append((int)ch).append(";");
            }
            else {
                b.append(ch);
            }
        }
        return b.toString();
    }
    
    public UM_RAE_ConuB010b[] getResultListPM_Excel(final int pageNum, final int pageMAX, final String saupNo, final String sangho, final String sdate, final String edate, final String status, final String pmstatus, final String checkYN) {
        Connection con = null;
        Trx trx = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        final Vector vec = new Vector();
        UM_RAE_ConuB010b[] ettlist = (UM_RAE_ConuB010b[])null;
        final int start = 1;
        final int end = 999999;
        try {
            String sql = "";
            if ("D".equals(status) || "C".equals(status)) {
                sql = "select * from (select t1.BIZ_REG_NO, t1.COM_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t1.REPR_NM, t1.PROCESS_ST, ROWNUM N, DECODE(PAID, NULL, 'Chưa thanh toán', 'Đã thanh toán') PAID from UM_EDOC_STATE t1 ";
                sql = String.valueOf(sql) + " , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B";
                sql = String.valueOf(sql) + " where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1)";
                sql = String.valueOf(sql) + " GROUP BY BIZ_REG_NO) C";
                sql = String.valueOf(sql) + " where PROCESS_ST in ('3', '4') ";
                sql = String.valueOf(sql) + " AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
                if (saupNo != null && saupNo.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.BIZ_NM like '%" + sangho + "%'";
                }
                if ("D".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='3' ";
                }
                else if ("C".equals(status)) {
                    sql = String.valueOf(sql) + " and  t1.PROCESS_ST ='4' ";
                }
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')";
                }
                else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
                    sql = String.valueOf(sql) + " and t1.REG_DT > to_date('" + sdate + "', 'dd/mm/yyyy')";
                }
                else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
                    sql = String.valueOf(sql) + " and t1.REG_DT < to_date('" + edate + "', 'dd/mm/yyyy')";
                }
                if (pmstatus.equals("B")) {
                    sql = String.valueOf(sql) + " and PAID is null";
                }
                if (pmstatus.equals("C")) {
                    sql = String.valueOf(sql) + " and PAID is not null";
                }
                sql = String.valueOf(sql) + " order by t1.REG_DT DESC";
                sql = String.valueOf(sql) + ") where N between " + start + " and " + end;
            }
            else {
                String sqll = "";
                sqll = "select BIZ_REG_NO from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, REG_YN, RECV_NO , ROWNUM N, DECODE(PAID, NULL, 'Chưa thanh toán', 'Đã thanh toán') PAID , REPR_MOBILE, REPR_EMAIL from (  select t1.BIZ_REG_NO, NVL(d.BIZ_NM, t1.BIZ_NM) BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.REG_YN, t1.RECV_NO, C.PAID,  t2.REPR_MOBILE, t2.REPR_EMAIL   from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2 , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1) GROUP BY BIZ_REG_NO) C  ,UM_SUPPLIER_ENTER_MAST D  where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y' AND t1.BIZ_REG_NO = D .BIZ_REG_NO (+)  AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
                if ("Y".equals(checkYN)) {
                    sqll = String.valueOf(sqll) + "and d.TEST_OPTION_YN = 'Y' ";
                }
                if ("N".equals(checkYN)) {
                    sqll = String.valueOf(sqll) + "and d.TEST_OPTION_YN != 'Y' ";
                }
                if (saupNo != null && saupNo.length() != 0) {
                    sqll = String.valueOf(sqll) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
                }
                if (sangho != null && sangho.length() != 0) {
                    sqll = String.valueOf(sqll) + " and t1.BIZ_NM like '%" + sangho + "%'";
                }
                if (status == null || status.length() == 0) {
                    sqll = String.valueOf(sqll) + " and  t1.REG_YN ='N' ";
                }
                else if (status != null && status.length() != 0 && !"A".equals(status)) {
                    sqll = String.valueOf(sqll) + " and  t1.REG_YN ='" + status + "' ";
                }
                if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                    sqll = String.valueOf(sqll) + " and t1.REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')";
                }
                else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
                    sqll = String.valueOf(sqll) + " and t1.REG_DT > to_date('" + sdate + "', 'dd/mm/yyyy')";
                }
                else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
                    sqll = String.valueOf(sqll) + " and t1.REG_DT < to_date('" + edate + "', 'dd/mm/yyyy')";
                }
                if (pmstatus.equals("B")) {
                    sqll = String.valueOf(sqll) + " and PAID is null";
                }
                if (pmstatus.equals("C")) {
                    sqll = String.valueOf(sqll) + " and PAID is not null";
                }
                sqll = String.valueOf(sqll) + " order by t1.REG_DT DESC, recv_no asc";
                sqll = String.valueOf(sqll) + ")) t3 where t3.N between " + start + " and " + end;
                if ("Y".equals(checkYN)) {
                    sql = "select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, REG_YN, RECV_NO , ROWNUM N, DECODE(PAID, NULL, 'Chưa thanh toán', 'Đã thanh toán') PAID , REPR_MOBILE, REPR_EMAIL from (  select t1.BIZ_REG_NO, NVL(d.BIZ_NM, t1.BIZ_NM) BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.REG_YN, t1.RECV_NO, C.PAID,  t2.REPR_MOBILE, t2.REPR_EMAIL   from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2 , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1) GROUP BY BIZ_REG_NO) C  ,UM_SUPPLIER_ENTER_MAST D  where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y' AND t1.BIZ_REG_NO = D .BIZ_REG_NO (+)  AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) ";
                    if ("Y".equals(checkYN)) {
                        sql = String.valueOf(sql) + "and d.TEST_OPTION_YN = 'Y' ";
                    }
                    if ("N".equals(checkYN)) {
                        sql = String.valueOf(sql) + "and d.TEST_OPTION_YN != 'Y' ";
                    }
                    if (saupNo != null && saupNo.length() != 0) {
                        sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
                    }
                    if (sangho != null && sangho.length() != 0) {
                        sql = String.valueOf(sql) + " and t1.BIZ_NM like '%" + sangho + "%'";
                    }
                    if (status == null || status.length() == 0) {
                        sql = String.valueOf(sql) + " and  t1.REG_YN ='N' ";
                    }
                    else if (status != null && status.length() != 0 && !"A".equals(status)) {
                        sql = String.valueOf(sql) + " and  t1.REG_YN ='" + status + "' ";
                    }
                    if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                        sql = String.valueOf(sql) + " and t1.REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')";
                    }
                    else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
                        sql = String.valueOf(sql) + " and t1.REG_DT > to_date('" + sdate + "', 'dd/mm/yyyy')";
                    }
                    else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
                        sql = String.valueOf(sql) + " and t1.REG_DT < to_date('" + edate + "', 'dd/mm/yyyy')";
                    }
                    if (pmstatus.equals("B")) {
                        sql = String.valueOf(sql) + " and PAID is null";
                    }
                    if (pmstatus.equals("C")) {
                        sql = String.valueOf(sql) + " and PAID is not null";
                    }
                    sql = String.valueOf(sql) + " order by t1.REG_DT DESC, recv_no asc";
                    sql = String.valueOf(sql) + ")) t3 where t3.N between " + start + " and " + end;
                }
                if ("N".equals(checkYN)) {
                    sql = "select * from(  select BIZ_REG_NO, BIZ_NM , REG_DT, REPR_NM, REG_YN, RECV_NO , ROWNUM N, DECODE(PAID, NULL, 'Chưa thanh toán', 'Đã thanh toán') PAID , REPR_MOBILE, REPR_EMAIL from (  select t1.BIZ_REG_NO, NVL(d.BIZ_NM, t1.BIZ_NM) BIZ_NM, to_char(t1.REG_DT, 'dd/mm/yyyy') REG_DT, t2.REPR_NM, t1.REG_YN, t1.RECV_NO, C.PAID,  t2.REPR_MOBILE, t2.REPR_EMAIL   from UM_REC_SUPPLIER_ENTER_MAST t1, UM_REC_REPR t2 , (SELECT BIZ_REG_NO, COUNT(*) PAID FROM BID.BID_PAYMENT_TABLE A, BID.BID_PAYMENT_DETAIL B where A.PM_TABLE_ID=B.PM_TABLE_ID AND A.PM_FINISH_YN='Y' AND B.PM_ITEM_ID IN (1) GROUP BY BIZ_REG_NO) C  ,UM_SUPPLIER_ENTER_MAST D  where t1.BIZ_REG_NO = t2.BIZ_REG_NO and t2.REPR_ISMAIN = 'Y' AND t1.BIZ_REG_NO = D .BIZ_REG_NO (+)  AND T1.BIZ_REG_NO=C.BIZ_REG_NO(+) and t1.BIZ_REG_NO not in  (" + sqll + ") ";
                    if (saupNo != null && saupNo.length() != 0) {
                        sql = String.valueOf(sql) + " and t1.BIZ_REG_NO like '%" + saupNo + "%'";
                    }
                    if (sangho != null && sangho.length() != 0) {
                        sql = String.valueOf(sql) + " and t1.BIZ_NM like '%" + sangho + "%'";
                    }
                    if (status == null || status.length() == 0) {
                        sql = String.valueOf(sql) + " and  t1.REG_YN ='N' ";
                    }
                    else if (status != null && status.length() != 0 && !"A".equals(status)) {
                        sql = String.valueOf(sql) + " and  t1.REG_YN ='" + status + "' ";
                    }
                    if (sdate != null && sdate.length() != 0 && edate != null && edate.length() != 0) {
                        sql = String.valueOf(sql) + " and t1.REG_DT between to_date('" + sdate + "', 'dd/mm/yyyy') and to_date('" + edate + "', 'dd/mm/yyyy')";
                    }
                    else if (sdate != null && sdate.length() != 0 && (edate == null || edate.length() == 0)) {
                        sql = String.valueOf(sql) + " and t1.REG_DT > to_date('" + sdate + "', 'dd/mm/yyyy')";
                    }
                    else if ((sdate == null || edate.length() == 0) && edate != null && edate.length() != 0) {
                        sql = String.valueOf(sql) + " and t1.REG_DT < to_date('" + edate + "', 'dd/mm/yyyy')";
                    }
                    if (pmstatus.equals("B")) {
                        sql = String.valueOf(sql) + " and PAID is null";
                    }
                    if (pmstatus.equals("C")) {
                        sql = String.valueOf(sql) + " and PAID is not null";
                    }
                    sql = String.valueOf(sql) + " order by t1.REG_DT DESC, recv_no asc";
                    sql = String.valueOf(sql) + ")) t3 where t3.N between " + start + " and " + end;
                }
            }
            Log.debug("getResultListPM_Excel--sql: " + sql);
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            pstm.clearParameters();
            rs = pstm.executeQuery();
            pstm.clearParameters();
            while (rs.next()) {
                final UM_RAE_ConuB010b ett = new UM_RAE_ConuB010b();
                ett.setSaupNo(rs.getString(1));
                ett.setSangho(rs.getString(2));
                ett.setSinchungDate(rs.getString(3));
                ett.setCeoName(rs.getString(4));
                ett.setProcessState(rs.getString(5));
                if (!"D".equals(status) && !"C".equals(status)) {
                    ett.setApprovalOrgCode(rs.getString(6));
                    ett.setPaidStatus(rs.getString(8));
                    ett.setCeoPhone(rs.getString(9));
                    ett.setCeoMail(rs.getString(10));
                }
                else {
                    ett.setPaidStatus(rs.getString(7));
                }
                vec.addElement(ett);
            }
        }
        catch (SQLException sqle) {
            Log.debug("UM_ADB_ConrA010p.getResultListPM_Excel block SQLException : " + sqle.toString());
        }
        catch (Exception exc) {
            Log.debug("UM_ADB_ConrA010p.getResultListPM_Excel block Exception : " + exc.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (pstm != null) {
                try {
                    pstm.close();
                }
                catch (Exception ex2) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex3) {}
        }
        if (pstm != null) {
            try {
                pstm.close();
            }
            catch (Exception ex4) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (vec.size() > 0) {
            ettlist = new UM_RAE_ConuB010b[vec.size()];
            vec.copyInto(ettlist);
            return ettlist;
        }
        return null;
    }
}
