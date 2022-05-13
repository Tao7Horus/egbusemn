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
import beans.Common_ComBo;
import common.ComStr;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class DB2DOC extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=utf-8");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        PreparedStatement psmt2 = null;
        PreparedStatement psmt3 = null;
        PreparedStatement psmt4 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        String sql = null;
        String query1 = null;
        String query2 = null;
        String fileWrite = "";
        final PrintWriter out = res.getWriter();
        final String fileName = ComStr.checkNull(req.getParameter("fileName"), "");
        final String saup_No = ComStr.checkNull(req.getParameter("saup_No"), "");
        String COUNT = "";
        final String COUNT2 = "";
        String qualification = "";
        String StateGubun = "";
        final Common_ComBo common_comco = new Common_ComBo();
        Label_4535: {
            try {
                trx = new Trx(this, "usemn");
                con = trx.getConnection();
                sb.append("select count(*) from UM_SUPPLIER_ENTER_MAST where BIZ_REG_NO = ?");
                sql = sb.toString();
                psmt = con.prepareStatement(sql);
                psmt.setString(1, ComStr.replace(saup_No, "-", ""));
                rs = psmt.executeQuery();
                if (rs.next()) {
                    COUNT = rs.getString(1);
                    psmt.clearParameters();
                }
                rs.close();
                sb.setLength(0);
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (Exception ex) {}
                }
                sql = "";
                sb2.append("SELECT DECODE(BID_ATTEND_QUALIFY_YN,'','Y','N') FROM UM_SUPPLIER_ENTER_MAST  WHERE  BIZ_REG_NO =?");
                sql = sb2.toString();
                psmt4 = con.prepareStatement(sql);
                psmt4.setString(1, ComStr.replace(saup_No, "-", ""));
                rs3 = psmt4.executeQuery();
                if (rs3.next()) {
                    qualification = rs3.getString(1);
                    psmt4.clearParameters();
                }
                rs3.close();
                sb2.setLength(0);
                if (psmt4 != null) {
                    try {
                        psmt4.close();
                    }
                    catch (Exception ex2) {}
                }
                if (qualification.equals("N")) {
                    res.sendRedirect("/jsp/RA/UM_RAJ_ConuB100s.jsp?flag=false2");
                }
                else {
                    if (!COUNT.equals("0")) {
                        sb.setLength(0);
                        sql = "";
                        sb.append(" select a.cnt + b.cnt from\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                        sb.append(" (select count(*) cnt from UM_ENTER_STATE  where BIZ_REG_NO = ? and STATE_CLS = '07') a,\t\t\t\t\t");
                        sb.append(" (SELECT COUNT(*) cnt FROM UM_SUPPLIER_ENTER_MAST WHERE BIZ_REG_NO = ? AND HEADQUARTER_YN = 'N') b\t");
                        sql = sb.toString();
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
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, ComStr.replace(saup_No, "-", ""));
                        psmt.setString(2, ComStr.replace(saup_No, "-", ""));
                        rs = psmt.executeQuery();
                        psmt.clearParameters();
                        if (rs.next()) {
                            StateGubun = rs.getString(1);
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
                        sb.setLength(0);
                        sql = "";
                        sb.append("\n SELECT BIZ_CLS, ");
                        sb.append("\n        BIZ_NM || DECODE (BIZ_EN_NM, null, '', ' ('|| BIZ_EN_NM ||')') BIZ_NM, ");
                        sb.append("\n        BIZ_REG_NO, ");
                        sb.append("\n        to_char(COMMENCEMENT_DT,'yyyy. mm. dd') COMMENCEMENT_DT, ");
                        sb.append("\n        ADDR || ' ' || DETAIL_ADDR ADDR, ");
                        sb.append("\n        PHONE_NO, ");
                        sb.append("\n        FAX, ");
                        sb.append("\n        CORP_REG_NO, ");
                        sb.append("\n        to_char(ESTABLISH_DT, 'yyyy. mm. dd') 법인등록일, ");
                        sb.append("\n        to_char(REG_DT, 'yyyy. mm. dd') REG_DT, ");
                        sb.append("\n        to_char(sysdate, 'yyyy. mm. dd') 출력일자, ");
                        sb.append("\n        PERMIT_BRANCH ");
                        sb.append("\n  FROM UM_SUPPLIER_ENTER_MAST ");
                        sb.append("\n  WHERE BIZ_REG_NO = ? ");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, ComStr.replace(saup_No, "-", ""));
                        rs = psmt.executeQuery();
                        psmt.clearParameters();
                        if (rs != null && rs.next()) {
                            final String SaupNumber = rs.getString(3);
                            final String SaupNumber_1 = SaupNumber.substring(0, 3);
                            final String SaupNumber_2 = SaupNumber.substring(3, 5);
                            final String SaupNumber_3 = SaupNumber.substring(5, SaupNumber.length());
                            String BukinNo = rs.getString(8);
                            String BukinNo_1 = "";
                            String BukinNo_2 = "";
                            if (BukinNo != null) {
                                BukinNo_1 = BukinNo.substring(0, 6);
                                BukinNo_2 = BukinNo.substring(6, BukinNo.length());
                                BukinNo = String.valueOf(BukinNo_1) + "-" + BukinNo_2;
                            }
                            else {
                                BukinNo = "";
                            }
                            final String Kiupgubun = rs.getString(1);
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
                            final String Jichung = common_comco.getCode("T", "Jichung", "GUJ", (rs.getString(12) == null) ? "" : rs.getString(12));
                            fileWrite = String.valueOf(gubun_1) + "^" + gubun_2 + ComStr.checkNull(rs.getString(2), "") + "^" + SaupNumber_1 + "-" + SaupNumber_2 + "-" + SaupNumber_3 + "^" + ComStr.checkNull(rs.getString(4), "") + "^" + ComStr.checkNull(rs.getString(5), "") + "^" + ComStr.checkNull(rs.getString(6), "") + "^" + ComStr.checkNull(rs.getString(7), "") + "^" + BukinNo + "^" + ComStr.checkNull(rs.getString(9), "") + "^" + ComStr.checkNull(rs.getString(10), "") + "^" + ComStr.checkNull(rs.getString(11), "") + "^" + ComStr.checkNull(Jichung, "") + "^\r\n";
                            rs.close();
                        }
                        psmt.close();
                        if (!StateGubun.equals("0")) {
                            fileWrite = StateGubun;
                        }
                        sb.setLength(0);
                        sql = "";
                        sb.append("\n SELECT REPR_NM, SUBSTR(REPR_IDENT_NO,1,6)||'*******', REPR_EMAIL, MAST_REPR_YN ");
                        sb.append("\n FROM UM_REPR             \t\t\t\t      ");
                        sb.append("\n WHERE BIZ_REG_NO = ?                        ");
                        sb.append("\n order by MAST_REPR_YN desc                    ");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, ComStr.replace(saup_No, "-", ""));
                        rs = psmt.executeQuery();
                        psmt.clearParameters();
                        if (rs != null) {
                            while (rs.next()) {
                                final String JuminNo = ComStr.checkNull(rs.getString(2), "");
                                String JuminNo_1 = "";
                                String JuminNo_2 = "";
                                if (JuminNo != null && JuminNo.length() == 13) {
                                    JuminNo_1 = JuminNo.substring(0, 6);
                                    JuminNo_2 = JuminNo.substring(6, JuminNo.length());
                                }
                                else {
                                    JuminNo_1 = "";
                                    JuminNo_2 = "";
                                }
                                fileWrite = String.valueOf(fileWrite) + ComStr.checkNull(rs.getString(1), "") + "^" + JuminNo_1 + "-" + JuminNo_2 + "^" + ComStr.checkNull(rs.getString(3), "") + "^" + ComStr.checkNull(rs.getString(4), "") + "^\r\n";
                            }
                            rs.close();
                        }
                        psmt.close();
                        fileWrite = String.valueOf(fileWrite) + "//EOR//\r\n";
                        sb.setLength(0);
                        sql = "";
                        sb.append("\n SELECT  POSITION, NM, SUBSTR(IDENT_NO,1,6)||'*******'\t");
                        sb.append("\n   FROM  UM_BID_AGENT\t\t\t\t");
                        sb.append("\n  WHERE BIZ_REG_NO = ?             ");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, ComStr.replace(saup_No, "-", ""));
                        rs = psmt.executeQuery();
                        psmt.clearParameters();
                        if (rs != null) {
                            while (rs.next()) {
                                final String JuminNo = ComStr.checkNull(rs.getString(3), "");
                                String JuminNo_1 = "";
                                String JuminNo_2 = "";
                                if (JuminNo != null && JuminNo.length() == 13) {
                                    JuminNo_1 = JuminNo.substring(0, 6);
                                    JuminNo_2 = JuminNo.substring(6, JuminNo.length());
                                }
                                else {
                                    JuminNo_1 = "";
                                    JuminNo_2 = "";
                                }
                                fileWrite = String.valueOf(fileWrite) + ComStr.checkNull(rs.getString(1), "") + "^" + ComStr.checkNull(rs.getString(2), "") + "^" + JuminNo_1 + "-" + JuminNo_2 + "^\r\n";
                            }
                            rs.close();
                        }
                        psmt.close();
                        fileWrite = String.valueOf(fileWrite) + "//EOR//\r\n";
                        sb.setLength(0);
                        sql = "";
                        sb.append("\n SELECT  /*+ ordered use_nl(A B ) */ distinct TO_CHAR(A.REG_DT, 'YYYY. MM. DD') REG_DT, A.GOOD_CLS_NO, B.분류명, A.DIRECT_PRODUCTION_YN, A.PERMIT_NO\t");
                        sb.append("\n   FROM  UM_SUPPLIER_ENTER_ITEMS A, SYN_VIEW_물품분류매핑 B                                                                          ");
                        sb.append("\n  WHERE  A.BIZ_REG_NO = ?                                                                                              ");
                        sb.append("\n \t AND  A.GOOD_CLS_NO = B.물품분류                                                                                       ");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, ComStr.replace(saup_No, "-", ""));
                        rs = psmt.executeQuery();
                        psmt.clearParameters();
                        String UpmuGubun = "";
                        query1 = " SELECT BIZ_CLS  FROM UM_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO = ? ";
                        psmt2 = con.prepareStatement(query1);
                        psmt2.setString(1, ComStr.replace(saup_No, "-", ""));
                        rs2 = psmt2.executeQuery();
                        if (rs2.next()) {
                            UpmuGubun = ComStr.checkNull(rs2.getString(1));
                            psmt2.clearParameters();
                        }
                        psmt2.close();
                        rs2.close();
                        if (rs != null) {
                            while (rs.next()) {
                                fileWrite = String.valueOf(fileWrite) + ComStr.checkNull(rs.getString(1)) + "^" + ComStr.checkNull(rs.getString(2)) + "^" + ComStr.checkNull(rs.getString(3)) + "^" + ComStr.checkNull(rs.getString(4)) + "^" + ComStr.checkNull(rs.getString(5)) + "^\r\n";
                            }
                            rs.close();
                            if (UpmuGubun.substring(4, 5).equals("1")) {
                                fileWrite = String.valueOf(fileWrite) + "^99999999^외자물품^^^\r\n";
                            }
                        }
                        else if (UpmuGubun.substring(4, 5).equals("1")) {
                            fileWrite = String.valueOf(fileWrite) + "^99999999^외자물품^^^\r\n";
                        }
                        psmt.close();
                        fileWrite = String.valueOf(fileWrite) + "//EOR//\r\n";
                        sb.setLength(0);
                        sql = "";
                        sb.append("\n SELECT B.CD_NM||' ('||A.LICENSE_CD||')', A.LICENSE_NO, TO_CHAR(A.LICENSE_ISSUED_DT, 'YYYY. MM. DD') LICENSE_ISSUED_DT, ");
                        sb.append("\n        TO_CHAR(A.LICENSE_EXPIRY_DT, 'YYYY. MM. DD') LICENSE_EXPIRY_DT, A.CONST_ABIL_EVAL_AMT                             ");
                        sb.append("\n   FROM  UM_LICENSE_FACTS A, SYN_PUB_CODE B                                                                   ");
                        sb.append("\n  WHERE B.CD_CLS='GU9'                                                                                   ");
                        sb.append("\n    AND B.CD = A.LICENSE_CD                                                                                ");
                        sb.append("\n    AND A.BIZ_REG_NO = ?                                                                               ");
                        sb.append("\n  ORDER BY B.CD_NM                                                                                        ");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, ComStr.replace(saup_No, "-", ""));
                        rs = psmt.executeQuery();
                        psmt.clearParameters();
                        if (rs != null) {
                            while (rs.next()) {
                                fileWrite = String.valueOf(fileWrite) + ComStr.checkNull(rs.getString(1), "") + "^" + ComStr.checkNull(rs.getString(2), "") + "^" + ComStr.checkNull(rs.getString(3), "") + "^" + ComStr.checkNull(rs.getString(4), "") + "^" + ComStr.divideComma(ComStr.checkNull(rs.getString(5)), ",", 3) + "^\r\n";
                            }
                            rs.close();
                        }
                        psmt.close();
                        fileWrite = String.valueOf(fileWrite) + "//EOR//\r\n";
                        sb.setLength(0);
                        sql = "";
                        sb.append("\n SELECT FACTORY_NM, ADDR||' '||ADDR2 ADDR, FACTORY_PHONE, ");
                        sb.append("\n        decode(FACTORY_RENT_YN, null, '', 'Y', '임대', 'N', '자가'),     ");
                        sb.append("\n        decode(FACTORY_RENT_YN, 'Y', to_char(FACTORY_RENT_START_DT, 'yyyy.mm.dd')||' ~ '||TO_CHAR(FACTORY_RENT_END_DT, 'yyyy.mm.dd'), '') ");
                        sb.append("\n FROM UM_FACTORY_INFO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                        sb.append("\n WHERE BIZ_REG_NO = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                        sb.append("\n ORDER BY FACTORY_NM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, ComStr.replace(saup_No, "-", ""));
                        rs = psmt.executeQuery();
                        psmt.clearParameters();
                        if (rs != null) {
                            while (rs.next()) {
                                fileWrite = String.valueOf(fileWrite) + ComStr.checkNull(rs.getString(1), "") + "^" + ComStr.checkNull(rs.getString(2), "") + "^" + ComStr.checkNull(rs.getString(3), "") + "^" + ComStr.checkNull(rs.getString(4), "") + "^" + ComStr.checkNull(rs.getString(5), "") + "^\r\n";
                            }
                            rs.close();
                        }
                        psmt.close();
                        fileWrite = String.valueOf(fileWrite) + "//EOR//\r\n";
                        sb.setLength(0);
                        sql = "";
                        sb.append("\n SELECT UPDATE_DT\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ");
                        sb.append("\n \t    , PROPERTY_NM                                                                             ");
                        sb.append("\n\t\t, DECODE(PROPERTY_NM, '대표자', DECODE(AFTER_MOD_CONTENT, NULL, '', SUBSTR(AFTER_MOD_CONTENT,1,12)||'*******'),AFTER_MOD_CONTENT) AFTER_MOD_CONTENT  ");
                        sb.append("\n\t\t, CHANGE_CLS                                                                           ");
                        sb.append("\n   FROM UM_BID_QUALIFY_FACTS_HIST\t\t\t\t\t\t\t                                     ");
                        sb.append("\n  WHERE BIZ_REG_NO  = ?                                                                 ");
                        sb.append("\n  ORDER BY UPDATE_DT DESC\t\t \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, ComStr.replace(saup_No, "-", ""));
                        rs = psmt.executeQuery();
                        psmt.clearParameters();
                        if (rs != null) {
                            int count = 0;
                            while (rs.next() && 10 >= count) {
                                ++count;
                                final String UDate1 = ComStr.checkNull(rs.getString(1), "");
                                final String UDate2 = UDate1.substring(0, 10);
                                final String jobGubn = ComStr.checkNull(rs.getString(2), "");
                                final String afterUpdate = ComStr.checkNull(rs.getString(3), "");
                                final String DML_gubun = ComStr.checkNull(rs.getString(4), "");
                                if (DML_gubun.equals("I")) {
                                    fileWrite = String.valueOf(fileWrite) + UDate2 + "^" + jobGubn + "^" + " 추가 : " + afterUpdate + "^\r\n";
                                }
                                else {
                                    if (!DML_gubun.equals("U")) {
                                        continue;
                                    }
                                    fileWrite = String.valueOf(fileWrite) + UDate2 + "^" + jobGubn + "^" + " 수정 : " + afterUpdate + "^\r\n";
                                }
                            }
                            rs.close();
                        }
                        psmt.close();
                        fileWrite = String.valueOf(fileWrite) + "//EOR//\r\n";
                        query1 = "SELECT COUNT(*) FROM UM_SEAL_MAST WHERE BIZ_REG_NO = ? AND USE_YN = 'Y' ";
                        psmt2 = con.prepareStatement(query1);
                        psmt2.setString(1, ComStr.replace(saup_No, "-", ""));
                        rs = psmt2.executeQuery();
                        int ingamCount = 0;
                        if (rs.next()) {
                            ingamCount = rs.getInt(1);
                        }
                        psmt2.clearParameters();
                        if (rs != null) {
                            try {
                                rs.close();
                            }
                            catch (Exception ex7) {}
                        }
                        if (psmt2 != null) {
                            try {
                                psmt2.close();
                            }
                            catch (Exception ex8) {}
                        }
                        if (ingamCount != 0) {
                            query2 = "SELECT IMG_MN FROM UM_SEAL_MAST WHERE BIZ_REG_NO = ? AND USE_YN = 'Y' ";
                            psmt3 = con.prepareStatement(query2);
                            psmt3.setString(1, ComStr.replace(saup_No, "-", ""));
                            rs = psmt3.executeQuery();
                            String ingamImg = "0";
                            if (rs != null) {
                                while (rs.next()) {
                                    ingamImg = rs.getString(1);
                                    Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/ftp.sh " + ingamImg + ".jpg");
                                    Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/ftp1.sh " + ingamImg + ".jpg");
                                    Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/ftp2.sh " + ingamImg + ".jpg");
                                    Runtime.getRuntime().exec("/usr/bin/csh /data/USEMN/ftp3.sh " + ingamImg + ".jpg");
                                }
                            }
                            psmt3.clearParameters();
                            if (rs != null) {
                                try {
                                    rs.close();
                                }
                                catch (Exception ex9) {}
                            }
                            if (psmt3 != null) {
                                try {
                                    psmt3.close();
                                }
                                catch (Exception ex10) {}
                            }
                        }
                        sb.setLength(0);
                        sql = "";
                        sb.append("\n SELECT IMG_MN FROM UM_SEAL_MAST WHERE BIZ_REG_NO = ? AND USE_YN = 'Y' ");
                        sql = sb.toString();
                        psmt = con.prepareStatement(sql);
                        psmt.setString(1, ComStr.replace(saup_No, "-", ""));
                        rs = psmt.executeQuery();
                        psmt.clearParameters();
                        int i = 0;
                        if (rs != null) {
                            while (rs.next()) {
                                fileWrite = String.valueOf(fileWrite) + ComStr.checkNull(rs.getString(1)) + "^";
                                ++i;
                            }
                            rs.close();
                        }
                        if (i < 12) {
                            for (i = i; i < 12; ++i) {
                                fileWrite = String.valueOf(fileWrite) + "^";
                            }
                        }
                        psmt.close();
                        fileWrite = String.valueOf(fileWrite) + "\r\n";
                        con.close();
                        break Label_4535;
                    }
                    res.sendRedirect("/jsp/RA/UM_RAJ_ConuB100s.jsp?flag=false");
                }
                return;
            }
            catch (SQLException e) {
                Log.errors(this.getClass().getName() + " SQLException 발생함 \n" + sql + " \n" + "사업자등록번호=" + saup_No + " \n" + e);
            }
            catch (Exception e2) {
                Log.errors(this.getClass().getName() + " Exception 발생함 \r\n" + e2);
                Log.errors(this.getClass().getName() + " Exception 발생함 \n" + "사업자등록번호=" + saup_No + " \n" + e2);
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex11) {}
                }
                if (psmt != null) {
                    try {
                        psmt.close();
                    }
                    catch (Exception ex12) {}
                }
                if (con != null) {
                    try {
                        con.close();
                    }
                    catch (Exception ex13) {}
                }
                if (trx != null) {
                    try {
                        trx.close();
                    }
                    catch (Exception ex14) {}
                }
            }
        }
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
        if (con != null) {
            try {
                con.close();
            }
            catch (Exception ex17) {}
        }
        if (trx != null) {
            try {
                trx.close();
            }
            catch (Exception ex18) {}
        }
        out.println(fileWrite);
    }
}
