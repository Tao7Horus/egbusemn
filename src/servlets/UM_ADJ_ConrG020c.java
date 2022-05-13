// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import common.Log;
import java.sql.SQLException;
import java.util.StringTokenizer;
import common.ComStr;
import common.Trx;
import entity.UM_ADV_GovuA030b;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_ADJ_ConrG020c extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final PreparedStatement psmt2 = null;
        ResultSet rs = null;
        String sql = null;
        String sql2 = null;
        String sql3 = null;
        final StringBuffer sb = new StringBuffer();
        final UM_ADV_GovuA030b entity = new UM_ADV_GovuA030b();
        String saupNo_0 = "";
        String occerDate = "";
        String startDate = "";
        String endDate = "";
        String conditon = "";
        String saupNo_2 = "";
        String saupNo_3 = "";
        String saupNo_4 = "";
        String vitual = "";
        String saupNo = "";
        String ediCode = "";
        String ediCode2 = "";
        String ediCode3 = "";
        String ediCode4 = "";
        String ediCode5 = "";
        String ediCode6 = "";
        String gubun = "";
        String localCode = "";
        String local = "";
        String sdate = "";
        String edate = "";
        String license = "";
        String licenseName = "";
        String Dchange = "";
        String ss = "";
        String Del = "";
        String sangho = "";
        saupNo_0 = ((req.getParameter("saupNo_0") == null) ? "" : req.getParameter("saupNo_0"));
        occerDate = ((req.getParameter("occerDate") == null) ? "" : req.getParameter("occerDate"));
        startDate = ((req.getParameter("startDate") == null) ? "" : req.getParameter("startDate"));
        endDate = ((req.getParameter("endDate") == null) ? "" : req.getParameter("endDate"));
        conditon = ((req.getParameter("conditon") == null) ? "" : req.getParameter("conditon"));
        vitual = ((req.getParameter("vitual") == null) ? "" : req.getParameter("vitual"));
        ediCode = ((req.getParameter("ediCode") == null) ? "" : req.getParameter("ediCode"));
        ediCode2 = ((req.getParameter("ediCode1") == null) ? "" : req.getParameter("ediCode1"));
        ediCode3 = ((req.getParameter("ediCode2") == null) ? "" : req.getParameter("ediCode2"));
        ediCode4 = ((req.getParameter("ediCode3") == null) ? "" : req.getParameter("ediCode3"));
        ediCode5 = ((req.getParameter("ediCode4") == null) ? "" : req.getParameter("ediCode4"));
        ediCode6 = ((req.getParameter("ediCode5") == null) ? "" : req.getParameter("ediCode5"));
        gubun = ((req.getParameter("gubun") == null) ? "" : req.getParameter("gubun"));
        localCode = ((req.getParameter("localCode") == null) ? "" : req.getParameter("localCode"));
        local = ((req.getParameter("local") == null) ? "" : req.getParameter("local"));
        sdate = ((req.getParameter("sdate") == null) ? "" : req.getParameter("sdate"));
        edate = ((req.getParameter("edate") == null) ? "" : req.getParameter("edate"));
        license = ((req.getParameter("license") == null) ? "" : req.getParameter("license"));
        licenseName = ((req.getParameter("licenseName") == null) ? "" : req.getParameter("licenseName"));
        Dchange = ((req.getParameter("Dchange") == null) ? "" : req.getParameter("Dchange"));
        ss = ((req.getParameter("ss") == null) ? "" : req.getParameter("edate"));
        sangho = ((req.getParameter("sangho") == null) ? "" : req.getParameter("sangho"));
        System.out.println("saupNo_0===" + saupNo_0);
        Label_0826: {
            if (saupNo_0.length() == 12) {
                saupNo_2 = saupNo_0.substring(0, 3);
                saupNo_3 = saupNo_0.substring(4, 6);
                saupNo_4 = saupNo_0.substring(7, 12);
                saupNo = String.valueOf(saupNo_2) + saupNo_3 + saupNo_4;
                break Label_0826;
            }
            saupNo = "";
            try {
                try {
                    trx = new Trx(this, "usemn");
                }
                catch (Exception ex) {}
                con = trx.getConnection();
                con.setAutoCommit(false);
                sql = "insert into 사용_업체상태(사업자등록번호,발생일자,시작일자,종료일자,상태구분,비고)values(?, to_date(?), to_date(?),to_date(?), ?, ?)";
                psmt = con.prepareStatement(sql);
                System.out.println(sql);
                psmt.setString(1, saupNo);
                psmt.setString(2, occerDate);
                psmt.setString(3, startDate);
                psmt.setString(4, endDate);
                psmt.setString(5, conditon);
                psmt.setString(6, vitual);
                psmt.executeUpdate();
                psmt.clearParameters();
                sql3 = "select 물품등록업체코드,공사등록업체코드,용역등록업체코드,매각등록업체코드,외자등록업체코드  from syn_매핑코드업체  where 사업자등록번호 = '" + saupNo + "' ";
                psmt = con.prepareStatement(sql3);
                System.out.println(sql3);
                rs = psmt.executeQuery();
                if (rs.next()) {
                    ediCode = ComStr.checkNull(rs.getString(1), "A");
                    ediCode2 = ComStr.checkNull(rs.getString(2), "A");
                    ediCode3 = ComStr.checkNull(rs.getString(3), "A");
                    ediCode4 = ComStr.checkNull(rs.getString(4), "A");
                    ediCode5 = ComStr.checkNull(rs.getString(5), "A");
                    psmt.clearParameters();
                }
                ediCode6 = String.valueOf(ediCode) + ',' + ediCode2 + ',' + ediCode3 + ',' + ediCode4 + ',' + ediCode5;
                System.out.println("ediCode5===" + ediCode6);
                try {
                    final StringTokenizer st = new StringTokenizer(ediCode6.trim(), ",");
                    while (st.hasMoreTokens()) {
                        Del = st.nextToken();
                        if (!Del.equals("A")) {
                            sql2 = "insert into SYN_업체상태 (등록업체코드,상태구분,발생일자,입력자사번,입력일자,비고,기간1,기간2)values(?, ?, to_date(?), '970251', sysdate, ?,to_date(?),to_date(?))";
                            psmt = con.prepareStatement(sql2);
                            System.out.println(sql2);
                            psmt.setString(1, Del);
                            System.out.println("Del" + Del);
                            psmt.setString(2, conditon);
                            psmt.setString(3, occerDate);
                            psmt.setString(4, vitual);
                            psmt.setString(5, startDate);
                            psmt.setString(6, endDate);
                            System.out.println("occerDate" + occerDate);
                            System.out.println("startDate" + startDate);
                            System.out.println("endDate" + endDate);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                        }
                    }
                }
                catch (SQLException e) {
                    try {
                        con.rollback();
                    }
                    catch (SQLException ex2) {}
                    if (psmt != null) {
                        try {
                            psmt.close();
                        }
                        catch (Exception ex3) {}
                    }
                    Log.debug("UM_ADJ_GovrB040c SQLException : Transaction Rollback간에 SQLException 발생함");
                    Log.debug("Exception발생 사유 : " + e.toString() + e.getErrorCode() + e.getSQLState());
                    e.printStackTrace();
                    return;
                }
                con.commit();
                con.setAutoCommit(true);
                res.sendRedirect("/jsp/AD/UM_ADJ_ConrG010l.jsp?gubun=" + gubun + "&saupNo=" + saupNo + "&sangho=" + sangho + "&localCode=" + localCode + "&local=" + local + "&sdate=" + sdate + "&edate=" + edate + "&license=" + license + "&licenseName=" + licenseName + "&Dchange=" + Dchange + "&ss=aa");
            }
            catch (SQLException e) {
                try {
                    con.rollback();
                }
                catch (SQLException ex4) {}
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (Exception ex5) {}
                }
                e.printStackTrace();
                return;
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (SQLException ex6) {}
                }
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (SQLException ex7) {}
                }
                if (psmt2 != null) {
                    try {
                        psmt.close();
                    }
                    catch (SQLException ex8) {}
                }
                if (con != null) {
                    try {
                        psmt.close();
                    }
                    catch (SQLException ex9) {}
                }
                trx.close();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException ex10) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (SQLException ex11) {}
        }
        if (psmt2 != null) {
            try {
                psmt.close();
            }
            catch (SQLException ex12) {}
        }
        if (con != null) {
            try {
                psmt.close();
            }
            catch (SQLException ex13) {}
        }
        trx.close();
    }
}
