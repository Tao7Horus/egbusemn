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

public class DB2TXT03 extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
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
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            if (start.equals("")) {
                start = "1900-01-01";
            }
            if (end.equals("")) {
                end = String.valueOf(yearNow) + "-" + monthNow + "-" + dateNow;
            }
            final String startTime = String.valueOf(start) + "00:00:00";
            final String endTime = String.valueOf(end) + "23:59:59";
            sql = "\tSelect a.코드, a.코드명, Nvl(b01.cnt,0) 서울, Nvl(b02.cnt,0) 부산, Nvl(b03.cnt,0) 인천,\t\t\t\t\n \t       Nvl(b04.cnt,0) 대구, Nvl(b05.cnt,0) 광주, Nvl(b06.cnt,0) 대전, Nvl(b07.cnt,0) 울산,\t\t\t\n \t       Nvl(b08.cnt,0) 경기, Nvl(b09.cnt,0) 강원, Nvl(b10.cnt,0) 충북, Nvl(b11.cnt,0) 충남,\t\t\t\n \t\t   Nvl(b12.cnt,0) 전북, Nvl(b13.cnt,0) 전남, Nvl(b14.cnt,0) 경북, Nvl(b15.cnt,0) 경남,\t\t\t\n \t\t   Nvl(b16.cnt,0) 제주, Nvl(b17.cnt,0) 기타,\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t   Nvl(b01.cnt,0) + Nvl(b02.cnt,0) + Nvl(b03.cnt,0) +\t\t\t\t\t\t\t\t\t\t\n \t       Nvl(b04.cnt,0) + Nvl(b05.cnt,0) + Nvl(b06.cnt,0) + Nvl(b07.cnt,0) +\t\t\t\t\t\t\n \t       Nvl(b08.cnt,0) + Nvl(b09.cnt,0) + Nvl(b10.cnt,0) + Nvl(b11.cnt,0) +\t\t\t\t\t\t\n \t\t   Nvl(b12.cnt,0) + Nvl(b13.cnt,0) + Nvl(b14.cnt,0) + Nvl(b15.cnt,0) +\t\t\t\t\t\t\n \t\t   Nvl(b16.cnt,0) + Nvl(b17.cnt,0) Tot_cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t From ( Select 코드, 코드명 From syn_공동코드 Where 코드구분 = 'GU9' And 코드명2 = '3' ) a, (\t\t\n \t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\t  And a.지역코드 Like '11%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t \n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b01, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '26%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t \n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b02, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '28%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t  \n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b03, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '27%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss') \t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b04, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '29%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b05, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '30%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b06, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '31%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b07, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '41%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b08, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '42%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b09, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '43%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b10, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '44%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b11, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '45%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b12, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '46%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b13, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '47%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b14, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '48%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b15, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.지역코드 Like '49%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And a.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b16, (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tSelect b.면허코드, Count(1) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t From 사용_조달업체마스터 a, 사용_면허사항 b\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\tWhere a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  And Substr(지역코드,1,2) Not In ('11', '26', '28', '27', '29', '30', '31', '41', '42', '43', '44', '45', '46', '47', '48', '49') \n " + "\t\t\t  And b.대표면허여부   = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t\t\t  AND a.등록일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss')  AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')\t\t\n " + (total.equals("0") ? "" : ("AND Substr(a.기업구분1,1,1)='" + total + "' ")) + " \n " + "\t\t\tGroup By b.면허코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t       ) b17\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\tWhere a.코드 = b01.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b02.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b03.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b04.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b05.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b06.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b07.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b08.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b09.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b10.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b11.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b12.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b13.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b14.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b15.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b16.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n " + "\t  And a.코드 = b17.면허코드 (+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n ";
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            String chk = "";
            if (total.equals("0")) {
                chk = "V^^";
            }
            else if (total.equals("1")) {
                chk = "^V^";
            }
            else if (total.equals("2")) {
                chk = "^^V";
            }
            fileWrite = String.valueOf(chk) + "^";
            fileWrite = String.valueOf(fileWrite) + start + "^";
            fileWrite = String.valueOf(fileWrite) + end + "^ \r\n";
            if (rs != null) {
                while (rs.next()) {
                    fileWrite = String.valueOf(fileWrite) + ComStr.checkNull(rs.getString(2)) + "^" + ComStr.checkNull(rs.getString(3)) + "^" + ComStr.checkNull(rs.getString(4)) + "^" + ComStr.checkNull(rs.getString(5)) + "^" + ComStr.checkNull(rs.getString(6)) + "^" + ComStr.checkNull(rs.getString(7)) + "^" + ComStr.checkNull(rs.getString(8)) + "^" + ComStr.checkNull(rs.getString(9)) + "^" + ComStr.checkNull(rs.getString(10)) + "^" + ComStr.checkNull(rs.getString(11)) + "^" + ComStr.checkNull(rs.getString(12)) + "^" + ComStr.checkNull(rs.getString(13)) + "^" + ComStr.checkNull(rs.getString(14)) + "^" + ComStr.checkNull(rs.getString(15)) + "^" + ComStr.checkNull(rs.getString(16)) + "^" + ComStr.checkNull(rs.getString(17)) + "^" + ComStr.checkNull(rs.getString(18)) + "^" + ComStr.checkNull(rs.getString(19)) + "^ \r\n";
                }
            }
            fileWrite = String.valueOf(fileWrite) + "//EOR//\r\n";
            rs.close();
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
        out.println(fileWrite);
    }
}
