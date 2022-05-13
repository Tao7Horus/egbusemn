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
import common.ComStr;
import secu.lib.MessageDigest;
import secu.lib.Secu;
import common.Trx;
import javax.servlet.ServletRequest;
import com.oreilly.servlet.ParameterParser;
import entity.UM_ICE_InciA040b;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class UM_RAV_IncuA030c extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        Trx trx = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement psmt = null;
        String sql = null;
        final StringBuffer sb = new StringBuffer();
        final UM_ICE_InciA040b entity = new UM_ICE_InciA040b();
        final ParameterParser psr = new ParameterParser((ServletRequest)req);
        String flag = "";
        String saupNo = "";
        Label_4754: {
            try {
                trx = new Trx(this, "usemn");
                con = trx.getConnection();
                con.setAutoCommit(false);
                flag = psr.getStringParameter("flag", "");
                final String userId = psr.getStringParameter("userId", "");
                String password = psr.getStringParameter("password", "");
                final String upcheNm = psr.getStringParameter("upcheNm", "");
                saupNo = psr.getStringParameter("saupNo", "");
                final String daepyoNm = psr.getStringParameter("daepyoNm", "");
                final String daepyojuminNo = psr.getStringParameter("daepyojuminNo", "");
                final String upcheGubun = psr.getStringParameter("upcheGubun", "");
                final String postCode = psr.getStringParameter("postCode", "");
                final String bonsaAdd_1 = psr.getStringParameter("bonsaAdd", "");
                final String bonsaAdd_2 = psr.getStringParameter("bonsaAddna", "");
                final String bonsaAdd = String.valueOf(bonsaAdd_1) + " " + bonsaAdd_2;
                final String bonsaTel_1 = psr.getStringParameter("bonsaTel_1", "");
                final String tel_1 = psr.getStringParameter("tel_1", "");
                final String bonsaTel = String.valueOf(tel_1) + "-" + bonsaTel_1;
                final String bonsaFax_1 = psr.getStringParameter("bonsaFax_1", "");
                final String tel_2 = psr.getStringParameter("tel_2", "");
                final String bonsaFax = String.valueOf(tel_2) + "-" + bonsaFax_1;
                final String bukinGubun = psr.getStringParameter("bukinGubun", "");
                final String establish = psr.getStringParameter("establish", "");
                final String money = psr.getStringParameter("money", "0");
                final String worker = psr.getStringParameter("worker", "0");
                final String output = psr.getStringParameter("output", "0");
                final String homepage = psr.getStringParameter("homepage", "");
                final String upchesGubun = psr.getStringParameter("upchesGubun", "");
                final String imageNm = psr.getStringParameter("imageNm", "");
                final String copInt = psr.getStringParameter("copInt", "");
                final String saensanGubun = psr.getStringParameter("saensanGubun", "");
                final String upchekGubun = psr.getStringParameter("upchekGubun", "");
                final String insertDay = psr.getStringParameter("insertDay", "");
                final String gradeGubun = psr.getStringParameter("gradeGubun", "");
                final String name = psr.getStringParameter("name", "");
                final String juminNo = psr.getStringParameter("juminNo", "");
                final String buserNm = psr.getStringParameter("buserNm", "");
                final String buserJang = psr.getStringParameter("buserJang", "");
                final String duty = psr.getStringParameter("duty", "");
                final String mail = psr.getStringParameter("mail", "");
                final String telNo_1 = psr.getStringParameter("telNo_1", "");
                final String tel_3 = psr.getStringParameter("tel_3", "");
                final String telNo = String.valueOf(tel_3) + "-" + telNo_1;
                final String faxNo_1 = psr.getStringParameter("faxNo_1", "");
                final String tel_4 = psr.getStringParameter("tel_4", "");
                final String faxNo = String.valueOf(tel_4) + "-" + faxNo_1;
                String count = psr.getStringParameter("count", "");
                if (flag.equals("I") || flag.equals("UserIns") || flag.equals("II") || flag.equals("UserIns_G")) {
                    final Secu secu = new Secu(1);
                    final MessageDigest md = new MessageDigest(secu);
                    password = md.create(req.getParameter("password"));
                }
                while (true) {
                    Label_3306: {
                        if (flag.equals("I")) {
                            sb.setLength(0);
                            sb.append("insert into 사용_생산업체 (사업자등록번호, 업체형태부호,\t\t 주생산품류부호, 업체규모구분, 업체명,\t 대표자명,\t 주민등록번호,\t\n");
                            sb.append("\t\t\t\t  \t\t      우편코드,       본사주소,\t\t\t 본사전화번호,\t 본사팩스번호, 법인구분, 법인설립일, 자본금,\t\t\n");
                            sb.append("\t\t\t\t\t\t\t  종업원수,\t\t  최근3년간총매출액, 홈페이지,\t \t 회사소개,\t   업체상태, 이미지명,\t 최초입력자id,\t\n");
                            sb.append("\t\t\t\t\t\t\t  최초입력일,\t  최근수정자ID,      최근수정일,     승인여부,     기관구분)\t\t\t\t\t\t\t\n");
                            sb.append("\t\t\t\t      values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, sysdate, 'N', 'N')\t\t\n");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                            psmt.setString(2, upcheGubun);
                            psmt.setString(3, saensanGubun);
                            psmt.setString(4, upchekGubun);
                            psmt.setString(5, upcheNm);
                            psmt.setString(6, daepyoNm);
                            psmt.setString(7, ComStr.replace(daepyojuminNo, "-", ""));
                            psmt.setString(8, ComStr.replace(postCode, "-", ""));
                            psmt.setString(9, bonsaAdd);
                            psmt.setString(10, bonsaTel);
                            psmt.setString(11, bonsaFax);
                            psmt.setString(12, bukinGubun);
                            psmt.setString(13, establish);
                            psmt.setLong(14, Long.parseLong(ComStr.replace(money, ",", "")));
                            psmt.setLong(15, Long.parseLong(ComStr.replace(worker, ",", "")));
                            psmt.setLong(16, Long.parseLong(ComStr.replace(output, ",", "")));
                            psmt.setString(17, homepage);
                            psmt.setString(18, copInt);
                            psmt.setString(19, upchesGubun);
                            psmt.setString(20, imageNm);
                            psmt.setString(21, userId);
                            psmt.setString(22, userId);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            sb.setLength(0);
                            sb.append("insert into 사용_생산업체사용자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sb.append(" \t(id, password, 사업자등록번호, 성명, 등급구분, 주민등록번호, 부서명, 부서장, 직책, 전화번호, 팩스번호, 전자메일, 등록일자, USR_GRP, CON_TYPE)\t\n");
                            sb.append("\tvalues (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sql = sb.toString();
                            if (psmt != null) {
                                psmt.close();
                            }
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, userId);
                            psmt.setString(2, password);
                            psmt.setString(3, ComStr.replace(saupNo, "-", ""));
                            psmt.setString(4, name);
                            psmt.setString(5, gradeGubun);
                            psmt.setString(6, ComStr.replace(juminNo, "-", ""));
                            psmt.setString(7, buserNm);
                            psmt.setString(8, buserJang);
                            psmt.setString(9, duty);
                            psmt.setString(10, telNo);
                            psmt.setString(11, faxNo);
                            psmt.setString(12, mail);
                            psmt.setString(13, "D00001");
                            psmt.setString(14, "MK");
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            con.commit();
                            con.setAutoCommit(true);
                            res.sendRedirect("http://www.g2b.go.kr:8070/jsp/RA/UM_RAJ_IncuA020s.jsp?userId=" + userId + "&AAAA=dddddd");
                            break Label_3306;
                        }
                        if (flag.equals("UserIns")) {
                            sb.setLength(0);
                            sb.append("insert into 사용_생산업체사용자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sb.append(" \t(id, password, 사업자등록번호, 성명, 등급구분, 주민등록번호, 부서명, 부서장, 직책, 전화번호, 팩스번호, 전자메일, 등록일자, USR_GRP, CON_TYPE)\t\n");
                            sb.append("\tvalues (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, userId);
                            psmt.setString(2, password);
                            psmt.setString(3, ComStr.replace(saupNo, "-", ""));
                            psmt.setString(4, name);
                            psmt.setString(5, gradeGubun);
                            psmt.setString(6, ComStr.replace(juminNo, "-", ""));
                            psmt.setString(7, buserNm);
                            psmt.setString(8, buserJang);
                            psmt.setString(9, duty);
                            psmt.setString(10, telNo);
                            psmt.setString(11, faxNo);
                            psmt.setString(12, mail);
                            psmt.setString(13, "MOKROK");
                            psmt.setString(14, "hh");
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            con.commit();
                            con.setAutoCommit(true);
                            res.sendRedirect("http://www.g2b.go.kr:8070/jsp/RA/UM_RAJ_IncuA020s.jsp?userId=" + userId + "&AAAA=dddddd");
                            break Label_3306;
                        }
                        if (flag.equals("m")) {
                            sb.setLength(0);
                            sb.append("UPDATE 사용_생산업체 SET 업체형태부호=?,\t주생산품류부호=?, 업체규모구분=?,\t\t업체명=?,\t\t대표자명=?,\t주민등록번호=?,\t\t\n");
                            sb.append("\t\t\t\t  \t\t    우편코드=?,     본사주소=?,\t\t  본사전화번호=?,\t\t본사팩스번호=?, 법인구분=?,\t법인설립일=?,\t\t\n");
                            sb.append("\t\t\t\t\t\t\t자본금=?,\t\t종업원수=?,\t\t  최근3년간총매출액=?,\t홈페이지=?,\t \t회사소개=?,\t업체상태=?,\t\t\t\n");
                            sb.append("\t\t\t\t\t\t\t최근수정자ID=?,\t  최근수정일=sysdate\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sb.append("\tWHERE 사업자등록번호 = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, upcheGubun);
                            psmt.setString(2, saensanGubun);
                            psmt.setString(3, upchekGubun);
                            psmt.setString(4, upcheNm);
                            psmt.setString(5, daepyoNm);
                            psmt.setString(6, ComStr.replace(daepyojuminNo, "-", ""));
                            psmt.setString(7, ComStr.replace(postCode, "-", ""));
                            psmt.setString(8, bonsaAdd);
                            psmt.setString(9, bonsaTel);
                            psmt.setString(10, bonsaFax);
                            psmt.setString(11, bukinGubun);
                            psmt.setString(12, establish);
                            psmt.setLong(13, Long.parseLong(ComStr.replace(money, ",", "")));
                            psmt.setLong(14, Long.parseLong(ComStr.replace(worker, ",", "")));
                            psmt.setLong(15, Long.parseLong(ComStr.replace(output, ",", "")));
                            psmt.setString(16, homepage);
                            psmt.setString(17, copInt);
                            psmt.setString(18, upchesGubun);
                            psmt.setString(19, userId);
                            psmt.setString(20, saupNo);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            sb.setLength(0);
                            sb.append(" UPDATE 사용_생산업체사용자 SET\tpassword=?, 성명=?,\t\t등급구분=?, 주민등록번호=?, 부서명=?, 부서장=?, 직책=?, 전화번호=?,\t\n");
                            sb.append("\t\t\t\t\t\t\t\t\t팩스번호=?, 전자메일=?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sb.append("\tWHERE id = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sql = sb.toString();
                            if (psmt != null) {
                                psmt.close();
                            }
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, password);
                            psmt.setString(2, name);
                            psmt.setString(3, gradeGubun);
                            psmt.setString(4, ComStr.replace(juminNo, "-", ""));
                            psmt.setString(5, buserNm);
                            psmt.setString(6, buserJang);
                            psmt.setString(7, duty);
                            psmt.setString(8, telNo);
                            psmt.setString(9, faxNo);
                            psmt.setString(10, mail);
                            psmt.setString(11, userId);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            con.commit();
                            con.setAutoCommit(true);
                            res.sendRedirect("http://www.g2b.go.kr:8070/jsp/IC/UM_ICJ_InciA010s.jsp");
                            break Label_3306;
                        }
                        if (flag.equals("mm")) {
                            sb.setLength(0);
                            sb.append("UPDATE 사용_생산업체 SET 업체형태부호=?,\t주생산품류부호=?, 업체규모구분=?,\t\t업체명=?,\t\t대표자명=?,\t주민등록번호=?,\t\t\n");
                            sb.append("\t\t\t\t  \t\t    우편코드=?,     본사주소=?,\t\t  본사전화번호=?,\t\t본사팩스번호=?, 법인구분=?,\t법인설립일=?,\t\t\n");
                            sb.append("\t\t\t\t\t\t\t자본금=?,\t\t종업원수=?,\t\t  최근3년간총매출액=?,\t홈페이지=?,\t \t회사소개=?,\t업체상태=?,\t\t\t\n");
                            sb.append("\t\t\t\t\t\t\t최근수정자ID=?,\t  최근수정일=sysdate\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sb.append("\tWHERE 사업자등록번호 = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, upcheGubun);
                            psmt.setString(2, saensanGubun);
                            psmt.setString(3, upchekGubun);
                            psmt.setString(4, upcheNm);
                            psmt.setString(5, daepyoNm);
                            psmt.setString(6, ComStr.replace(daepyojuminNo, "-", ""));
                            psmt.setString(7, ComStr.replace(postCode, "-", ""));
                            psmt.setString(8, bonsaAdd);
                            psmt.setString(9, bonsaTel);
                            psmt.setString(10, bonsaFax);
                            psmt.setString(11, bukinGubun);
                            psmt.setString(12, establish);
                            psmt.setLong(13, Long.parseLong(ComStr.replace(money, ",", "")));
                            psmt.setLong(14, Long.parseLong(ComStr.replace(worker, ",", "")));
                            psmt.setLong(15, Long.parseLong(ComStr.replace(output, ",", "")));
                            psmt.setString(16, homepage);
                            psmt.setString(17, copInt);
                            psmt.setString(18, upchesGubun);
                            psmt.setString(19, userId);
                            psmt.setString(20, ComStr.replace(saupNo, "-", ""));
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            sb.setLength(0);
                            sb.append(" UPDATE 사용_생산업체사용자 SET\tpassword=?, 성명=?,\t\t등급구분=?, 주민등록번호=?, 부서명=?, 부서장=?, 직책=?, 전화번호=?,\t\n");
                            sb.append("\t\t\t\t\t\t\t\t\t팩스번호=?, 전자메일=?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sb.append("\tWHERE id = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                            sql = sb.toString();
                            if (psmt != null) {
                                psmt.close();
                            }
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, password);
                            psmt.setString(2, name);
                            psmt.setString(3, gradeGubun);
                            psmt.setString(4, ComStr.replace(juminNo, "-", ""));
                            psmt.setString(5, buserNm);
                            psmt.setString(6, buserJang);
                            psmt.setString(7, duty);
                            psmt.setString(8, telNo);
                            psmt.setString(9, faxNo);
                            psmt.setString(10, mail);
                            psmt.setString(11, userId);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            con.commit();
                            con.setAutoCommit(true);
                            res.sendRedirect("http://www.g2b.go.kr:8070/jsp/IC/UM_ICJ_InciD010s.jsp");
                            break Label_3306;
                        }
                        if (flag.equals("A")) {
                            sb.setLength(0);
                            sb.append("UPDATE 사용_생산업체 SET 승인여부 = 'Y'\n");
                            sb.append("\tWHERE 사업자등록번호 = ?\n");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, saupNo);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            con.commit();
                            con.setAutoCommit(true);
                            res.sendRedirect("http://www.g2b.go.kr:8070/jsp/IC/UM_ICJ_IncuA030m.jsp?saupNo=" + saupNo + "&flag=Y");
                            break Label_3306;
                        }
                        if (flag.equals("B")) {
                            sb.setLength(0);
                            sb.append("UPDATE 사용_생산업체 SET 승인여부 = 'Y'\n");
                            sb.append("\tWHERE 사업자등록번호 = ?\n");
                            sql = sb.toString();
                            psmt = con.prepareStatement(sql);
                            psmt.setString(1, saupNo);
                            psmt.executeUpdate();
                            psmt.clearParameters();
                            con.commit();
                            con.setAutoCommit(true);
                            res.sendRedirect("http://www.g2b.go.kr:8070/jsp/IC/UM_ICJ_IncuD030m.jsp?saupNo=" + saupNo + "&flag=Y");
                            break Label_3306;
                        }
                        if (!flag.equals("Hidden")) {
                            break Label_3306;
                        }
                        sb.append("select count(*) from 사용_생산업체 where 사업자등록번호 = ?");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, saupNo);
                        rs = psmt.executeQuery();
                        if (rs.next()) {
                            count = rs.getString(1);
                            psmt.clearParameters();
                        }
                        if (count.equals("1")) {
                            con.setAutoCommit(true);
                            res.sendRedirect("http://www.g2b.go.kr:8070/jsp/RA/UM_RAJ_IncuA011i.jsp?saupNo=" + saupNo);
                        }
                        else {
                            con.setAutoCommit(true);
                            res.sendRedirect("http://www.g2b.go.kr:8070/jsp/RA/UM_RAJ_IncuA010i.jsp?saupNo=" + saupNo);
                        }
                        return;
                    }
                    if (flag.equals("II")) {
                        sb.setLength(0);
                        sb.append("insert into 사용_생산업체 (사업자등록번호, 업체형태부호,\t\t 주생산품류부호, 업체규모구분, 업체명,\t 대표자명,\t 주민등록번호,\t\n");
                        sb.append("\t\t\t\t  \t\t      우편코드,       본사주소,\t\t\t 본사전화번호,\t 본사팩스번호, 법인구분, 법인설립일, 자본금,\t\t\n");
                        sb.append("\t\t\t\t\t\t\t  종업원수,\t\t  최근3년간총매출액, 홈페이지,\t \t 회사소개,\t   업체상태, 이미지명,\t 최초입력자id,\t\n");
                        sb.append("\t\t\t\t\t\t\t  최초입력일,\t  최근수정자ID,\t\t 최근수정일,     승인여부,     기관구분)\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        sb.append("\t\t\t\t      values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, sysdate, 'N', 'Y')\t\t\n");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, ComStr.replace(saupNo, "-", ""));
                        psmt.setString(2, upcheGubun);
                        psmt.setString(3, saensanGubun);
                        psmt.setString(4, upchekGubun);
                        psmt.setString(5, upcheNm);
                        psmt.setString(6, daepyoNm);
                        psmt.setString(7, ComStr.replace(daepyojuminNo, "-", ""));
                        psmt.setString(8, ComStr.replace(postCode, "-", ""));
                        psmt.setString(9, bonsaAdd);
                        psmt.setString(10, bonsaTel);
                        psmt.setString(11, bonsaFax);
                        psmt.setString(12, bukinGubun);
                        psmt.setString(13, establish);
                        psmt.setLong(14, Long.parseLong(ComStr.replace(money, ",", "")));
                        psmt.setLong(15, Long.parseLong(ComStr.replace(worker, ",", "")));
                        psmt.setLong(16, Long.parseLong(ComStr.replace(output, ",", "")));
                        psmt.setString(17, homepage);
                        psmt.setString(18, copInt);
                        psmt.setString(19, upchesGubun);
                        psmt.setString(20, imageNm);
                        psmt.setString(21, userId);
                        psmt.setString(22, userId);
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        sb.setLength(0);
                        sb.append("insert into 사용_생산업체사용자\n");
                        sb.append(" \t(id, password, 사업자등록번호, 성명, 등급구분, 주민등록번호, 부서명, 부서장, 직책, 전화번호, 팩스번호, 전자메일, 등록일자, USR_GRP, CON_TYPE)\t\n");
                        sb.append("\tvalues (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?)\n");
                        sql = sb.toString();
                        if (psmt != null) {
                            psmt.close();
                        }
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, userId);
                        psmt.setString(2, password);
                        psmt.setString(3, ComStr.replace(saupNo, "-", ""));
                        psmt.setString(4, name);
                        psmt.setString(5, gradeGubun);
                        psmt.setString(6, ComStr.replace(juminNo, "-", ""));
                        psmt.setString(7, buserNm);
                        psmt.setString(8, buserJang);
                        psmt.setString(9, duty);
                        psmt.setString(10, telNo);
                        psmt.setString(11, faxNo);
                        psmt.setString(12, mail);
                        psmt.setString(13, "MOKROK");
                        psmt.setString(14, "hh");
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        con.commit();
                        con.setAutoCommit(true);
                        res.sendRedirect("http://www.g2b.go.kr:8070/jsp/RA/UM_RAJ_IncuD020s.jsp?userId=" + userId + "&AAAA=dddddd");
                        break Label_4754;
                    }
                    if (flag.equals("VIEW")) {
                        sb.append("select count(*) from 사용_생산업체 where 사업자등록번호 = ?");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, saupNo);
                        rs = psmt.executeQuery();
                        if (rs.next()) {
                            count = rs.getString(1);
                            psmt.clearParameters();
                        }
                        if (count.equals("1")) {
                            con.setAutoCommit(true);
                            res.sendRedirect("http://www.g2b.go.kr:8070/jsp/RA/UM_RAJ_IncuD011i.jsp?saupNo=" + saupNo);
                            continue;
                        }
                        con.setAutoCommit(true);
                        res.sendRedirect("http://www.g2b.go.kr:8070/jsp/RA/UM_RAJ_IncuD010i.jsp?saupNo=" + saupNo);
                        continue;
                    }
                    else if (flag.equals("UserIns_G")) {
                        sb.setLength(0);
                        sb.append("insert into 사용_생산업체사용자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        sb.append(" \t(id, password, 사업자등록번호, 성명, 주민등록번호, 부서명, 부서장, 직책, 전화번호, 팩스번호, 전자메일, 등록일자, USR_GRP, CON_TYPE)\t\n");
                        sb.append("\tvalues (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, userId);
                        psmt.setString(2, password);
                        psmt.setString(3, ComStr.replace(saupNo, "-", ""));
                        psmt.setString(4, name);
                        psmt.setString(5, ComStr.replace(juminNo, "-", ""));
                        psmt.setString(6, buserNm);
                        psmt.setString(7, buserJang);
                        psmt.setString(8, duty);
                        psmt.setString(9, telNo);
                        psmt.setString(10, faxNo);
                        psmt.setString(11, mail);
                        psmt.setString(12, "MOKROK");
                        psmt.setString(13, "hh");
                        psmt.executeUpdate();
                        psmt.clearParameters();
                        con.commit();
                        con.setAutoCommit(true);
                        res.sendRedirect("http://www.g2b.go.kr:8070/jsp/RA/UM_RAJ_IncuD020s.jsp?userId=" + userId + "&AAAA=dddddd");
                    }
                    break;
                }
            }
            catch (SQLException exc) {
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
                Log.debug("UM_RAV_IncuA030c.java flag[" + flag + "],사업자등록번호[" + saupNo + "]");
                CommonMessage.printMsg("", "4", exc.getMessage(), "", res);
            }
            catch (Exception exc2) {
                try {
                    if (con != null) {
                        con.rollback();
                    }
                }
                catch (Exception ex3) {}
                try {
                    if (con != null) {
                        con.setAutoCommit(true);
                    }
                }
                catch (Exception ex4) {}
                Log.debug("UM_RAV_IncuA030c.java flag[" + flag + "],사업자등록번호[" + saupNo + "]");
                CommonMessage.printMsg("", "4", exc2.getMessage(), "", res);
            }
            finally {
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
                        trx.close();
                    }
                    catch (Exception ex7) {}
                }
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex8) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
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
