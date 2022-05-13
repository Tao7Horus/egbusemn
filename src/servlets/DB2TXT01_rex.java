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
import java.util.Calendar;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DB2TXT01_rex extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/xml;charset=UTF-8");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        final PreparedStatement psmt2 = null;
        ResultSet rs = null;
        final ResultSet rs2 = null;
        String sql = null;
        final String sql2 = null;
        String fileWrite = null;
        final PrintWriter out = res.getWriter();
        String start = req.getParameter("start");
        String end = req.getParameter("end");
        final String fileName = "등록업체(대.중소기업, 여성기업) 통계조회";
        final String total = ComStr.checkNull(req.getParameter("total"), "");
        final Calendar cal = Calendar.getInstance();
        final String yearNow = Integer.toString(cal.get(1));
        final String monthNow = Integer.toString(cal.get(2) + 1);
        final String dateNow = Integer.toString(cal.get(5));
        final int attach = 0;
        try {
            trx = new Trx(this);
            con = trx.getConnection();
            if (start.equals("")) {
                start = "1900-01-01";
            }
            if (end.equals("")) {
                end = String.valueOf(yearNow) + "-" + monthNow + "-" + dateNow;
            }
            final String startTime = String.valueOf(start) + "00:00:00";
            final String endTime = String.valueOf(end) + "23:59:59";
            if (!total.equals("0")) {
                sql = "\tSelect a.t_name, a.cnt, b.cnt From (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n \t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */ '물품' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n \t\t\t From 사용_조달업체마스터 a,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n \t\t\t    ( Select 사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n \t\t\t\t    From 사용_대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n \t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b\t\t\t\t\t\t\t  \n \t\t\tWhere a.업무구분 Like '1____'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n \t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n \t\t\t  And Substr(a.기업구분1,1,1) = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n \t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n \t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')  \n" + "\t\t\t) a, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02) */ '물품' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t From 사용_조달업체마스터 a,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t    ( Select 사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t    From 사용_대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b\t\t\t\t\t\t\t  \n " + "\t\t\tWhere a.업무구분 Like '1____'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t  \n " + "\t\t\t  ) b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\tUnion All\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\tSelect a.t_name, a.cnt, b.cnt From ( \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\tSelect/*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */  '공사' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t From 사용_조달업체마스터 a,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t    ( Select 사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t    From 사용_대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b\t\t\t\t\t\t\t  \n " + "\t\t\tWhere a.업무구분 Like '_1___'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t  \n " + "\t\t\t) a, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02) */ '공사' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t From 사용_조달업체마스터 a,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t    ( Select 사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t    From 사용_대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b\t\t\t\t\t\t\t  \n " + "\t\t\tWhere a.업무구분 Like '_1___'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t  \n " + "\t\t\t  ) b \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\tUnion All\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\tSelect a.t_name, a.cnt, b.cnt From ( \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */ '용역' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t From 사용_조달업체마스터 a,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t    ( Select 사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t    From 사용_대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b\t\t\t\t\t\t\t  \n " + "\t\t\tWhere a.업무구분 Like '__1__'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t  \n " + "\t\t\t) a, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02) */ '용역' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t From 사용_조달업체마스터 a,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t    ( Select 사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t    From 사용_대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b\t\t\t\t\t\t\t  \n " + "\t\t\tWhere a.업무구분 Like '__1__'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t  \n " + "\t\t\t   ) b \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\tUnion All\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\tSelect a.t_name, a.cnt, b.cnt From ( \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */ '외자' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t From 사용_조달업체마스터 a,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t    ( Select 사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t    From 사용_대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b\t\t\t\t\t\t\t  \n " + "\t\t\tWhere a.업무구분 Like '____1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t  \n " + "\t\t\t   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t) a, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02) */ '외자' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t From 사용_조달업체마스터 a,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t    ( Select 사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t    From 사용_대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b\t\t\t\t\t\t\t  \n " + "\t\t\tWhere a.업무구분 Like '____1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.테스트여부 is null \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t  \n " + "\t\t\t  ) b \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\tUnion All\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\tSelect a.t_name, a.cnt, b.cnt From ( \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */ '국외' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t From 사용_조달업체마스터 a,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t    ( Select 사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t    From 사용_대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b\t\t\t\t\t\t\t  \n " + "\t\t\tWhere a.사업자등록번호 Like 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "             And a.테스트여부 is null                                                                                \n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t  \n " + "\t\t\t) a, (\t                                                                                                  \n " + "           Select /*+ INDEX(a IDX_사용_조달업체마스터_02) */ '국외' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t              \n " + "\t\t\t From 사용_조달업체마스터 a,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t    ( Select 사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t    From 사용_대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b\t\t\t\t\t\t\t  \n " + "\t\t\tWhere a.사업자등록번호 Like 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2' \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "             And a.테스트여부 is null                                                                                \n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t  \n " + "\t\t\t  ) b   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t        \n " + "\tUnion All\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t        \n " + "\tSelect a.t_name, a.cnt, b.cnt From (                                                                                                                   \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */ '미분류' t_name, Count(*) cnt                                   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t From 사용_조달업체마스터 a,                                                                                                                    \n " + "\t\t\t    ( Select 사업자등록번호                                                                                                                    \n " + "\t\t\t\t    From 사용_대표자                                                                                                                     \n " + "\t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b                                                                 \n " + "\t\t\tWhere a.업무구분 = '00000'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.테스트여부 is null \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')         \n " + "\t\t\t) a, (                                                                                                                                      \n " + "          Select /*+ INDEX(a IDX_사용_조달업체마스터_02) */ '미분류' t_name, Count(*) cnt                                                                \n " + "\t\t\t From 사용_조달업체마스터 a,                                                                                                                \n " + "\t\t\t    ( Select 사업자등록번호                                                                                                                 \n " + "\t\t\t\t    From 사용_대표자                                                                                                                    \n " + "\t\t\t\t   Where 대표대표자여부 = 'Y' And 대표자주민번호 Like '______2______') b                                                                \n " + "\t\t\tWhere a.업무구분 = '00000'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And a.테스트여부 is null \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t        \n " + "\t\t\t  ) b   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t                                        \n ";
            }
            else {
                sql = "\tSelect a.t_name, a.cnt, b.cnt From (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */ '물품' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\tWhere 업무구분 Like '1____'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\t  And Substr(a.기업구분1,1,1) = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\n " + "\t\t\t) a, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02) */ '물품' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere 업무구분 Like '1____'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t \n " + "\t\t\t) b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n" + "\tUnion All\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\tSelect a.t_name, a.cnt, b.cnt From (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */ '공사' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\tWhere 업무구분 Like '_1___'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t  \n " + "\t\t\t) a, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02) */ '공사' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere 업무구분 Like '_1___'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t \n " + "\t\t\t) b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\tUnion All\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\tSelect a.t_name, a.cnt, b.cnt From (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */ '용역' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\tWhere 업무구분 Like '__1__'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t \n " + "\t\t\t) a, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02) */ '용역' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n" + "\t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\tWhere 업무구분 Like '__1__'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n" + "\t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t \n " + "\t\t\t) b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\tUnion All\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\tSelect a.t_name, a.cnt, b.cnt From (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */ '외자' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t \n " + "\t\t\tWhere 업무구분 Like '____1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\n " + "\t\t\t) a, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect/*+ INDEX(a IDX_사용_조달업체마스터_02) */  '외자' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere 업무구분 Like '____1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\n " + "\t\t\t) b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\tUnion All\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\tSelect a.t_name, a.cnt, b.cnt From (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */ '국외' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere 사업자등록번호 Like 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\n " + "\t\t\t) a, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02) */ '국외' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere 사업자등록번호 Like 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\n " + "\t\t\t) b \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\tUnion All\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\tSelect a.t_name, a.cnt, b.cnt From (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02, IDX_사용_조달업체마스터_04) */ '미분류' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.업무구분 = '00000'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\n " + "\t\t\t) a, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect /*+ INDEX(a IDX_사용_조달업체마스터_02) */ '미분류' t_name, Count(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.업무구분 = '00000'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \n " + "\t\t\t  And Substr(a.기업구분1,1,1) = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND 등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\n " + "\t\t\t) b \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n ";
            }
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            String chk = "";
            if (total.equals("0")) {
                chk = "V^";
            }
            else if (total.equals("1")) {
                chk = "^V";
            }
            fileWrite = "<?xml version='1.0' encoding='UTF-8'?>";
            fileWrite = String.valueOf(fileWrite) + "<main>";
            fileWrite = String.valueOf(fileWrite) + "<row>";
            fileWrite = String.valueOf(fileWrite) + "<검사><![CDATA[" + chk + "]]></검사>";
            fileWrite = String.valueOf(fileWrite) + "<시작일자><![CDATA[" + start + "]]></시작일자>";
            fileWrite = String.valueOf(fileWrite) + "<종료일자><![CDATA[" + end + "]]></종료일자>";
            if (rs != null) {
                int i = 0;
                while (rs.next()) {
                    ++i;
                    fileWrite = String.valueOf(fileWrite) + "<대기업" + i + "><![CDATA[" + ComStr.checkNull(rs.getString(2)) + "]]></대기업" + i + ">" + "<중소기업" + i + "><![CDATA[" + ComStr.checkNull(rs.getString(3)) + "]]></중소기업" + i + ">";
                }
            }
        }
        catch (SQLException e) {
            Log.errors(this.getClass().getName() + " SQLException 발생함 \n" + sql + " " + e);
        }
        catch (Exception e2) {
            Log.errors(this.getClass().getName() + " Exception 발생함 " + e2);
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
                    trx.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex4) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex5) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex6) {}
        }
        fileWrite = String.valueOf(fileWrite) + "</row>";
        fileWrite = String.valueOf(fileWrite) + "</main>";
        out.println(fileWrite);
    }
}
