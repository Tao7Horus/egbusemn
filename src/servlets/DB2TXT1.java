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

public class DB2TXT1 extends HttpServlet
{
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=utf-8");
        Trx trx = null;
        Connection con = null;
        PreparedStatement psmt = null;
        PreparedStatement psmt2 = null;
        PreparedStatement psmt3 = null;
        PreparedStatement psmt4 = null;
        PreparedStatement psmt5 = null;
        PreparedStatement psmt6 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        ResultSet rs6 = null;
        String sql = null;
        final String sql2 = null;
        String sql3 = null;
        String sql4 = null;
        String sql5 = null;
        String sql6 = null;
        String fileWrite = null;
        boolean ingamch = false;
        int LincenseCount1 = 0;
        int LincenseCount2 = 0;
        final PrintWriter out = res.getWriter();
        final String fileName = "Văn bản tiến hành";
        String saupNo = ComStr.checkNull(req.getParameter("saupNo"), "");
        String old_sangho = "";
        String new_sangho = "";
        int gongMinusCountCheck = 0;
        int attach = 0;
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sql = " SELECT DISTINCT U1.COM_NM , U1.ZIP_CD,\tU1.ADDR ||' '|| U1.DETAIL_ADDR ADDR\t\t  , ('Nộp tài liệu chứng minh đăng ký thay đổi tư cách tham gia đấu thầu cạnh tranh') TITLE\t\t\t\t\t\t\t\t  , U6.CD_NM \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  , to_char(U1.REG_DT,'YYYY.MM.DD') REG_DT, U1.BIZ_REG_NO, U1.REPR_NM\t\t FROM  UM_EDOC_STATE U1, SYN_PUB_CODE U6\t\t\t\t\t\t\t\t\t\t WHERE U1.BIZ_REG_NO= ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t AND   U1.MOD_CLS='2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t AND   U1.PROCESS_ST IN(0,2)\t\t\t\t\t\t\t\t\t\t\t\t\t\t AND   U6.CD_CLS = 'GUJ'\t\t\t\t\t\t\t\t\t\t\t\t\t\t AND   U6.CD = U1.PERMIT_BRANCH\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, saupNo);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            if (rs != null && rs.next()) {
                final String SaupNumber = rs.getString(7);
                final String SaupNumber_1 = SaupNumber.substring(0, 3);
                final String SaupNumber_2 = SaupNumber.substring(3, 5);
                final String SaupNumber_3 = SaupNumber.substring(5, SaupNumber.length());
                fileWrite = String.valueOf(ComStr.checkNull(rs.getString(1))) + "^" + ComStr.divideComma(ComStr.checkNull(rs.getString(2)), "-", 3) + "^" + ComStr.checkNull(rs.getString(3)) + "^" + " " + "^" + " " + "^" + " " + "^" + ComStr.checkNull(rs.getString(4)) + "^" + ComStr.checkNull(rs.getString(5)) + "^" + ComStr.checkNull(rs.getString(6)) + "^" + SaupNumber_1 + "-" + SaupNumber_2 + "-" + SaupNumber_3 + "^" + ComStr.checkNull(rs.getString(8)) + "^\r\n";
                saupNo = rs.getString(7);
                new_sangho = ComStr.checkNull(rs.getString(1));
                rs.close();
                sql = " select BIZ_NM from UM_SUPPLIER_ENTER_MAST where BIZ_REG_NO = ? ";
                psmt2 = con.prepareStatement(sql);
                psmt2.setString(1, saupNo);
                rs2 = psmt2.executeQuery();
                psmt2.clearParameters();
                if (rs2.next()) {
                    old_sangho = ComStr.checkNull(rs2.getString(1));
                }
            }
            ingamch = this.getInGamCheck(saupNo, con);
            final int bubCount = 0;
            sql = "select b.bcn-a.acn from\t\t\t\t\t\t\t\t\t\t\t\t\t  (select count(*) bcn from UM_REC_FACTORY_INFO where  BIZ_REG_NO= ? ) b, (select count(*) acn from UM_FACTORY_INFO where  BIZ_REG_NO= ? )  a\t  ";
            int gongCount = 0;
            psmt2 = con.prepareStatement(sql);
            psmt2.setString(1, saupNo);
            psmt2.setString(2, saupNo);
            rs2 = psmt2.executeQuery();
            psmt2.clearParameters();
            if (rs2.next()) {
                gongCount = rs2.getInt(1);
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex) {}
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex2) {}
            }
            int gongMinusCount = 0;
            if (gongCount == 0) {
                sql = "select count(*) from  (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(select BIZ_REG_NO, FACTORY_NM, FACTORY_ADDR, FACTORY_ADDR2, FACTORY_RENT_YN, FACTORY_RENT_START_DT, FACTORY_RENT_END_DT from UM_REC_FACTORY_INFO where BIZ_REG_NO=?)\t minus\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(select BIZ_REG_NO, FACTORY_NM, FACTORY_ADDR, FACTORY_ADDR2, FACTORY_RENT_YN, FACTORY_RENT_START_DT, FACTORY_RENT_END_DT from UM_FACTORY_INFO where  BIZ_REG_NO= ? ) )\t";
                psmt2 = con.prepareStatement(sql);
                psmt2.setString(1, saupNo);
                psmt2.setString(2, saupNo);
                rs2 = psmt2.executeQuery();
                psmt2.clearParameters();
                if (rs2.next()) {
                    gongMinusCount = rs2.getInt(1);
                }
                if (gongMinusCount != 0) {
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 공장등록증명서 원본 (최근 3개월 이내 발행분) \r\n" + "  [단, 임대공장등록증명서에 임대기간이 명기되어 있지 않을 경우에는 임대계약서를 첨부]\r\n";
                    gongMinusCountCheck = 1;
                }
            }
            else if (gongCount > 0) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + ". 공장등록증명서 원본 (최근 3개월 이내 발행분)\r\n" + "  [단, 임대공장등록증명서에 임대기간이 명기되어 있지 않을 경우에는 임대계약서를 첨부]\r\n";
                gongMinusCountCheck = 1;
            }
            sql = "select b.bcn-a.acn from\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(select count(*) bcn from UM_REC_SUPPLIER_ENTER_ITEM  where  DIRECT_PRODUCTION_YN='Y'  and BIZ_REG_NO= ?  ) b, (select count(*) acn from UM_SUPPLIER_ENTER_ITEMS\t  where  DIRECT_PRODUCTION_YN='Y'  and  BIZ_REG_NO= ? ) a  ";
            gongCount = 0;
            psmt2 = con.prepareStatement(sql);
            psmt2.setString(1, saupNo);
            psmt2.setString(2, saupNo);
            rs2 = psmt2.executeQuery();
            psmt2.clearParameters();
            if (rs2.next()) {
                gongCount = rs2.getInt(1);
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex3) {}
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex4) {}
            }
            if (gongCount == 0) {
                sql = "select count(*) from  (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(select BIZ_REG_NO, GOOD_CLS_NO from UM_REC_SUPPLIER_ENTER_ITEM where DIRECT_PRODUCTION_YN='Y' and BIZ_REG_NO=?)\t\t minus\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t(select BIZ_REG_NO, GOOD_CLS_NO from UM_SUPPLIER_ENTER_ITEMS where DIRECT_PRODUCTION_YN='Y' and  BIZ_REG_NO= ? ) )   ";
                int minusCount = 0;
                psmt2 = con.prepareStatement(sql);
                psmt2.setString(1, saupNo);
                psmt2.setString(2, saupNo);
                rs2 = psmt2.executeQuery();
                psmt2.clearParameters();
                if (rs2.next()) {
                    minusCount = rs2.getInt(1);
                }
                if (minusCount != 0) {
                    if (gongMinusCountCheck == 0) {
                        ++attach;
                        fileWrite = String.valueOf(fileWrite) + attach + ". 공장등록증명서 원본 (최근 3개월 이내 발행분)\r\n" + "  [단, 임대공장등록증명서에 임대기간이 명기되어 있지 않을 경우에는 임대계약서를 첨부]\r\n";
                    }
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 카다로그 원본 \r\n";
                    ++attach;
                    if (!ingamch) {
                        fileWrite = String.valueOf(fileWrite) + attach + ". 세금계산서 등 제조물품을 증명할 수 있는 서류 사본(인감으로 '사실과 상위없음' 날인) \r\n";
                        ++attach;
                    }
                    else {
                        fileWrite = String.valueOf(fileWrite) + attach + ". 세금계산서 등 제조물품을 증명할 수 있는 서류 사본(사용인감으로 '사실과 상위없음' 날인) \r\n";
                        ++attach;
                    }
                    fileWrite = String.valueOf(fileWrite) + attach + ". 물품제조 사실확인서(경쟁입찰참가자격변경신청서 상단 안내설명 또는 나라장터 → 고객지원 → 자료실 → 서식자료실에서 다운로드)  \r\n";
                }
            }
            else if (gongCount > 0) {
                if (gongMinusCountCheck == 0) {
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 공장등록증명서 원본 (최근 3개월 이내 발행분) \r\n" + "  [단, 임대공장등록증명서에 임대기간이 명기되어 있지 않을 경우에는 임대계약서를 첨부]\r\n";
                }
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + ". 카다로그 원본 \r\n";
                ++attach;
                if (!ingamch) {
                    fileWrite = String.valueOf(fileWrite) + attach + ". 세금계산서 등 제조물품을 증명할 수 있는 서류 사본(인감으로 '사실과 상위없음' 날인) \r\n";
                    ++attach;
                }
                else {
                    fileWrite = String.valueOf(fileWrite) + attach + ". 세금계산서 등 제조물품을 증명할 수 있는 서류 사본(사용인감으로 '사실과 상위없음' 날인) \r\n";
                    ++attach;
                }
                fileWrite = String.valueOf(fileWrite) + attach + ". 물품제조 사실확인서(경쟁입찰참가자격변경신청서 상단 안내설명 또는 나라장터 → 고객지원 → 자료실 → 서식자료실에서 다운로드)  \r\n";
            }
            int HCount1 = 0;
            int HCount2 = 0;
            int HCount3 = 0;
            sql = "select b.bcn-a.acn from                                                              (select count(PERMIT_NO) bcn from UM_REC_SUPPLIER_ENTER_ITEM where BIZ_REG_NO = ?) b, (select count(PERMIT_NO) acn from UM_SUPPLIER_ENTER_ITEMS where BIZ_REG_NO= ?) a       ";
            psmt2 = con.prepareStatement(sql);
            psmt2.setString(1, saupNo);
            psmt2.setString(2, saupNo);
            rs2 = psmt2.executeQuery();
            psmt2.clearParameters();
            if (rs2.next()) {
                HCount1 = rs2.getInt(1);
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex5) {}
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex6) {}
            }
            sql = "select b.bcn-a.acn from                                                           (select count(PERMIT_DT) bcn from UM_REC_SUPPLIER_ENTER_ITEM where BIZ_REG_NO = ?) b, (select count(PERMIT_DT) acn from UM_SUPPLIER_ENTER_ITEMS where BIZ_REG_NO = ?) a      ";
            psmt2 = con.prepareStatement(sql);
            psmt2.setString(1, saupNo);
            psmt2.setString(2, saupNo);
            rs2 = psmt2.executeQuery();
            psmt2.clearParameters();
            if (rs2.next()) {
                HCount2 = rs2.getInt(1);
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex7) {}
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex8) {}
            }
            sql = "select b.bcn-a.acn from                                                             (select count(PERMIT_INSTITU) bcn from UM_REC_SUPPLIER_ENTER_ITEM where BIZ_REG_NO = ?) b, (select count(PERMIT_INSTITU) acn from UM_SUPPLIER_ENTER_ITEMS where BIZ_REG_NO = ?) a      ";
            psmt2 = con.prepareStatement(sql);
            psmt2.setString(1, saupNo);
            psmt2.setString(2, saupNo);
            rs2 = psmt2.executeQuery();
            psmt2.clearParameters();
            if (rs2.next()) {
                HCount3 = rs2.getInt(1);
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex9) {}
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex10) {}
            }
            if (HCount1 <= 0 && HCount2 <= 0 && HCount3 <= 0) {
                sql = "select count(*) from (\t     \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t         (select BIZ_REG_NO, PERMIT_NO, PERMIT_DT, PERMIT_INSTITU from UM_REC_SUPPLIER_ENTER_ITEM where BIZ_REG_NO = ?   and (PERMIT_NO is not null or PERMIT_DT is not null or PERMIT_INSTITU is not null) )                          minus\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t (select BIZ_REG_NO, PERMIT_NO, PERMIT_DT, PERMIT_INSTITU from UM_SUPPLIER_ENTER_ITEMS where BIZ_REG_NO = ?))    ";
                int minusCount2 = 0;
                psmt2 = con.prepareStatement(sql);
                psmt2.setString(1, saupNo);
                psmt2.setString(2, saupNo);
                rs2 = psmt2.executeQuery();
                psmt2.clearParameters();
                if (rs2.next()) {
                    minusCount2 = rs2.getInt(1);
                }
                if (minusCount2 > 0) {
                    if (!ingamch) {
                        ++attach;
                        fileWrite = String.valueOf(fileWrite) + attach + ". 형식승인을 증명할 수 있는 서류 사본(인감으로 '사실과 상위없음' 날인) \r\n";
                    }
                    else {
                        ++attach;
                        fileWrite = String.valueOf(fileWrite) + attach + ". 형식승인을 증명할 수 있는 서류 사본(사용인감으로 '사실과 상위없음' 날인) \r\n";
                    }
                }
            }
            else if (!ingamch) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + ". 형식승인을 증명할 수 있는 서류 사본(인감으로 '사실과 상위없음' 날인) \r\n";
            }
            else {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + ". 형식승인을 증명할 수 있는 서류 사본(사용인감으로 '사실과 상위없음' 날인) \r\n";
            }
            sql = "select b.bcn-a.acn from                                                    (select count(*) bcn from UM_REC_LICENSE_FACTS where BIZ_REG_NO= ? ) b,   (select count(*) acn from UM_LICENSE_FACTS\t\twhere BIZ_REG_NO= ? ) a    ";
            gongCount = 0;
            psmt2 = con.prepareStatement(sql);
            psmt2.setString(1, saupNo);
            psmt2.setString(2, saupNo);
            rs2 = psmt2.executeQuery();
            psmt2.clearParameters();
            if (rs2.next()) {
                gongCount = rs2.getInt(1);
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex11) {}
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex12) {}
            }
            sql5 = " SELECT Count(*) FROM UM_REC_LICENSE_FACTS A, SYN_PUB_CODE B    WHERE B.CD_CLS = 'GU9'\t\t\t\t\t\t\t\t    AND A.LICENSE_CD = B.CD\t\t\t\t\t\t\t\t       /* AND B.CD_NM like '%용역'\t\t\t\t\t               */ AND A.BIZ_REG_NO = ?                                   ";
            psmt5 = con.prepareStatement(sql5);
            psmt5.setString(1, saupNo);
            rs5 = psmt5.executeQuery();
            if (rs5.next()) {
                LincenseCount1 = rs5.getInt(1);
            }
            sql6 = " SELECT Count(*) FROM UM_REC_LICENSE_FACTS\t\t\t\t    WHERE BIZ_REG_NO = ?                               ";
            psmt6 = con.prepareStatement(sql6);
            psmt6.setString(1, saupNo);
            rs6 = psmt6.executeQuery();
            if (rs6.next()) {
                LincenseCount2 = rs6.getInt(1);
            }
            if (gongCount == 0) {
                sql = "select count(*) from  (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t (select BIZ_REG_NO, LICENSE_CD, LICENSE_NO, to_char(LICENSE_ISSUED_DT,'YYYY-MM-DD') LICENSE_ISSUED_DT , CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR   from UM_REC_LICENSE_FACTS where BIZ_REG_NO=?)\t\t\t\t\t\t\t\t\t  minus\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t (select BIZ_REG_NO, LICENSE_CD, LICENSE_NO, to_char(LICENSE_ISSUED_DT,'YYYY-MM-DD') LICENSE_ISSUED_DT , CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR   from UM_LICENSE_FACTS where   BIZ_REG_NO= ? ) )\t\t\t\t\t\t\t\t\t ";
                int minusCount2 = 0;
                psmt2 = con.prepareStatement(sql);
                psmt2.setString(1, saupNo);
                psmt2.setString(2, saupNo);
                rs2 = psmt2.executeQuery();
                psmt2.clearParameters();
                if (rs2.next()) {
                    minusCount2 = rs2.getInt(1);
                }
                sql3 = " SELECT count(*) FROM UM_LICENSE_EXPIRED WHERE LICENSE_EXPIRY_YN = 'Y' ";
                psmt3 = con.prepareStatement(sql3);
                rs3 = psmt3.executeQuery();
                psmt3.clearParameters();
                int licenseCodeCount1 = 0;
                int licenseCodeCount2 = 0;
                if (rs3.next()) {
                    licenseCodeCount1 = rs3.getInt(1);
                }
                if (licenseCodeCount1 > 0) {
                    sql4 = " SELECT count(*) FROM                                                              (SELECT a.LICENSE_CD, to_char(a.LICENSE_EXPIRY_DT, 'yyyy-mm-dd') FROM UM_REC_LICENSE_FACTS a, UM_LICENSE_EXPIRED b   WHERE a.BIZ_REG_NO = ?                                                          AND b.LICENSE_EXPIRY_YN = 'Y'                                                  AND a.LICENSE_CD = b.LICENSE_CD                                                     MINUS                                                                             SELECT LICENSE_CD, to_char(LICENSE_EXPIRY_DT, 'yyyy-mm-dd') FROM UM_LICENSE_FACTS                                  WHERE BIZ_REG_NO = ?)                                                       ";
                    psmt4 = con.prepareStatement(sql4);
                    psmt4.setString(1, saupNo);
                    psmt4.setString(2, saupNo);
                    rs4 = psmt4.executeQuery();
                    psmt4.clearParameters();
                    if (rs4.next()) {
                        licenseCodeCount2 = rs4.getInt(1);
                    }
                }
                if ((minusCount2 != 0 || licenseCodeCount2 > 0) && (LincenseCount2 == 0 || LincenseCount2 != LincenseCount1)) {
                    if (!ingamch) {
                        ++attach;
                        fileWrite = String.valueOf(fileWrite) + attach + ". 신청 업종 등록수첩과 당해 업종 인·허가, 면허, 등록, 신고증 등 사본(모두 있는 경우는 모두 제출, 인감으로 '사실과 상위없음' 날인) \r\n";
                    }
                    else {
                        ++attach;
                        fileWrite = String.valueOf(fileWrite) + attach + ". 신청 업종 등록수첩과 당해 업종 인·허가, 면허, 등록, 신고증 등 사본(모두 있는 경우는 모두 제출, 사용인감으로 '사실과 상위없음' 날인) \r\n";
                    }
                }
            }
            else if (gongCount < 0) {
                sql = "select count(*) from  (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t (select BIZ_REG_NO, LICENSE_CD, LICENSE_NO, to_char(LICENSE_ISSUED_DT,'YYYY-MM-DD') LICENSE_ISSUED_DT , CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR   from UM_REC_LICENSE_FACTS where BIZ_REG_NO=?)\t\t\t\t\t\t\t\t\t  minus\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t (select BIZ_REG_NO, LICENSE_CD, LICENSE_NO, to_char(LICENSE_ISSUED_DT,'YYYY-MM-DD') LICENSE_ISSUED_DT , CONST_ABIL_EVAL_AMT, EVAL_STD_YEAR   from UM_LICENSE_FACTS where   BIZ_REG_NO= ? ) )\t\t\t\t\t\t\t\t\t ";
                int minusCount3 = 0;
                psmt2 = con.prepareStatement(sql);
                psmt2.setString(1, saupNo);
                psmt2.setString(2, saupNo);
                rs2 = psmt2.executeQuery();
                psmt2.clearParameters();
                if (rs2.next()) {
                    minusCount3 = rs2.getInt(1);
                }
                sql3 = " SELECT count(*) FROM UM_LICENSE_EXPIRED WHERE LICENSE_EXPIRY_YN = 'Y' ";
                psmt3 = con.prepareStatement(sql3);
                rs3 = psmt3.executeQuery();
                psmt3.clearParameters();
                int licenseCodeCount3 = 0;
                int licenseCodeCount4 = 0;
                if (rs3.next()) {
                    licenseCodeCount3 = rs3.getInt(1);
                }
                if (licenseCodeCount3 > 0) {
                    sql4 = " SELECT count(*) FROM                                                              (SELECT a.LICENSE_CD, to_char(a.LICENSE_EXPIRY_DT, 'yyyy-mm-dd') FROM UM_REC_LICENSE_FACTS a, UM_LICENSE_EXPIRED b   WHERE a.BIZ_REG_NO = ?                                                          AND b.LICENSE_EXPIRY_YN = 'Y'                                                  AND a.LICENSE_CD = b.LICENSE_CD                                                     MINUS                                                                             SELECT LICENSE_CD, to_char(LICENSE_EXPIRY_DT, 'yyyy-mm-dd') FROM UM_LICENSE_FACTS                                  WHERE BIZ_REG_NO = ?)                                                       ";
                    psmt4 = con.prepareStatement(sql4);
                    psmt4.setString(1, saupNo);
                    psmt4.setString(2, saupNo);
                    rs4 = psmt4.executeQuery();
                    psmt4.clearParameters();
                    if (rs4.next()) {
                        licenseCodeCount4 = rs4.getInt(1);
                    }
                }
                if ((minusCount3 != 0 || licenseCodeCount4 != 0) && (LincenseCount2 == 0 || LincenseCount2 != LincenseCount1)) {
                    if (!ingamch) {
                        ++attach;
                        fileWrite = String.valueOf(fileWrite) + attach + ". 신청 업종 등록수첩과 당해 업종 인·허가, 면허, 등록, 신고증 등 사본(모두 있는 경우는 모두 제출, 인감으로 '사실과 상위없음' 날인) \r\n";
                    }
                    else {
                        ++attach;
                        fileWrite = String.valueOf(fileWrite) + attach + ". 신청 업종 등록수첩과 당해 업종 인·허가, 면허, 등록, 신고증 등 사본(모두 있는 경우는 모두 제출, 사용인감으로 '사실과 상위없음' 날인) \r\n";
                    }
                }
            }
            else if (LincenseCount2 == 0 || LincenseCount2 != LincenseCount1) {
                if (!ingamch) {
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 신청 업종 등록수첩과 당해 업종 인·허가, 면허, 등록, 신고증 등 사본(모두 있는 경우는 모두 제출, 인감으로 '사실과 상위없음' 날인) \r\n";
                }
                else {
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 신청 업종 등록수첩과 당해 업종 인·허가, 면허, 등록, 신고증 등 사본(모두 있는 경우는 모두 제출, 사용인감으로 '사실과 상위없음' 날인) \r\n";
                }
            }
            sql = " SELECT b.bcn-a.acn FROM\t\t\t\t\t\t\t\t     (SELECT count(*) bcn FROM UM_REC_LICENSE_FACTS A, SYN_PUB_CODE B  WHERE A.BIZ_REG_NO = ?\t\t\t\t\t\t\t     AND B.CD_CLS = 'GU9'\t\t\t\t\t\t\t\t     AND A.LICENSE_CD = B.CD\t\t\t\t\t\t\t\t     /*AND B.CD_NM like '%용역'*/) b,\t\t\t\t\t\t\t     (SELECT count(*) acn FROM UM_LICENSE_FACTS A, SYN_PUB_CODE B\t WHERE A.BIZ_REG_NO = ?\t\t\t\t\t\t\t     AND B.CD_CLS = 'GU9'\t\t\t\t\t\t\t\t     AND A.LICENSE_CD = B.CD\t\t\t\t\t\t\t\t    /* AND B.CD_NM like '%용역'*/) a\t\t\t\t\t\t\t    ";
            gongCount = 0;
            psmt2 = con.prepareStatement(sql);
            psmt2.setString(1, saupNo);
            psmt2.setString(2, saupNo);
            rs2 = psmt2.executeQuery();
            psmt2.clearParameters();
            if (rs2.next()) {
                gongCount = rs2.getInt(1);
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex13) {}
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex14) {}
            }
            if (gongCount == 0) {
                sql = " SELECT count(*) FROM (\t\t\t\t\t\t\t\t     (SELECT B.CD_NM FROM UM_REC_LICENSE_FACTS A, SYN_PUB_CODE B  WHERE A.BIZ_REG_NO = ?\t\t\t\t\t\t\t     AND B.CD_CLS = 'GU9'\t\t\t\t\t\t\t\t     AND A.LICENSE_CD = B.CD\t\t\t\t\t\t\t\t     /*AND B.CD_NM like '%용역'*/)\t\t\t\t\t\t\t     minus\t\t\t\t\t\t\t\t\t\t\t\t\t\t (SELECT B.CD_NM FROM UM_LICENSE_FACTS A, SYN_PUB_CODE B\t\t WHERE A.BIZ_REG_NO = ?\t\t\t\t\t\t\t     AND B.CD_CLS = 'GU9'\t\t\t\t\t\t\t\t     AND A.LICENSE_CD = B.CD\t\t\t\t\t\t\t\t     /*AND B.CD_NM like '%용역'*/))\t\t\t\t\t\t\t    ";
                int mCodeCount = 0;
                psmt2 = con.prepareStatement(sql);
                psmt2.setString(1, saupNo);
                psmt2.setString(2, saupNo);
                rs2 = psmt2.executeQuery();
                psmt2.clearParameters();
                if (rs2.next()) {
                    mCodeCount = rs2.getInt(1);
                }
                if (mCodeCount != 0) {
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + "※ [~용역]으로 끝나는 업종은 사업자등록증 [종목]란에 용역명 또는 유사명이 등재된 경우에만 처리되며, 별도의 서류제출 없이 처리됩니다. \r\n";
                }
            }
            else {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + ". ※ [~용역]으로 끝나는 업종은 사업자등록증 [종목]란에 용역명 또는 유사명이 등재된 경우에만 처리되며, 별도의 서류제출 없이 처리됩니다. \r\n";
            }
            if (attach == 0) {
                fileWrite = String.valueOf(fileWrite) + "※ 본건은 시행문 및 증빙서류 제출없이 승인처리되므로 조달청에 우편발송이나 방문하실 필요가 없습니다. \r\n   나라장터-조달업체이용자등록-등록신청확인및시행문출력에서 승인여부를 확인하시고, 승인 완료가 되면  인증서등록을 하시면 됩니다.\r\n";
            }
            else if (!ingamch) {
                ++attach;
                fileWrite = String.valueOf(fileWrite) + attach + ". 인감증명서(제출서류 확인용, 경쟁입찰참가자격변경신청서 상단 안내설명 또는 고객지원 -> 자료실 -> 나라장터자료 -> 18번 \"등록업체 사용인감 등록절차 안내(12.11)\"에서 다운로드) \r\n   * 첨부된 사용인감 등록절차안내에 의거 사용인감을 필히 등록하여 주시기 바랍니다. \r\n";
            }
            fileWrite = String.valueOf(fileWrite) + "- 끝 -\r\n";
            if (ingamch && !new_sangho.equals(old_sangho)) {
                fileWrite = String.valueOf(fileWrite) + "\r\n※ 등록된 사용인감이 변경된 경우 사용인감 변경(신청) 하십시오. ";
            }
            fileWrite = String.valueOf(fileWrite) + "\r\n※ 승인지청에서 승인 통보시까지 약 1일정도 소요됩니다.";
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
                catch (Exception ex15) {}
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex16) {}
            }
            if (rs3 != null) {
                try {
                    rs3.close();
                }
                catch (Exception ex17) {}
            }
            if (rs4 != null) {
                try {
                    rs4.close();
                }
                catch (Exception ex18) {}
            }
            if (rs5 != null) {
                try {
                    rs5.close();
                }
                catch (Exception ex19) {}
            }
            if (rs6 != null) {
                try {
                    rs6.close();
                }
                catch (Exception ex20) {}
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex21) {}
            }
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex22) {}
            }
            if (psmt4 != null) {
                try {
                    psmt4.close();
                }
                catch (Exception ex23) {}
            }
            if (psmt5 != null) {
                try {
                    psmt5.close();
                }
                catch (Exception ex24) {}
            }
            if (psmt6 != null) {
                try {
                    psmt6.close();
                }
                catch (Exception ex25) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex26) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex27) {}
        }
        if (rs2 != null) {
            try {
                rs2.close();
            }
            catch (Exception ex28) {}
        }
        if (rs3 != null) {
            try {
                rs3.close();
            }
            catch (Exception ex29) {}
        }
        if (rs4 != null) {
            try {
                rs4.close();
            }
            catch (Exception ex30) {}
        }
        if (rs5 != null) {
            try {
                rs5.close();
            }
            catch (Exception ex31) {}
        }
        if (rs6 != null) {
            try {
                rs6.close();
            }
            catch (Exception ex32) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex33) {}
        }
        if (psmt3 != null) {
            try {
                psmt3.close();
            }
            catch (Exception ex34) {}
        }
        if (psmt4 != null) {
            try {
                psmt4.close();
            }
            catch (Exception ex35) {}
        }
        if (psmt5 != null) {
            try {
                psmt5.close();
            }
            catch (Exception ex36) {}
        }
        if (psmt6 != null) {
            try {
                psmt6.close();
            }
            catch (Exception ex37) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex38) {}
        }
        out.println(fileWrite);
    }
    
    private boolean getInGamCheck(final String tmp_saupNo, final Connection tmp_conn) throws Exception {
        String tmpsql = null;
        PreparedStatement tmpstmt = null;
        ResultSet tmprest = null;
        boolean tmpResult = false;
        int tmp_result = 0;
        tmpsql = " SELECT COUNT(*) FROM UM_SEAL_MAST\t\t WHERE BIZ_REG_NO = ?\t\t\t\t\t AND   USE_YN ='Y'\t\t\t\t\t\t";
        try {
            tmpstmt = tmp_conn.prepareStatement(tmpsql);
            tmpstmt.setString(1, tmp_saupNo);
            tmprest = tmpstmt.executeQuery();
            if (tmprest.next()) {
                tmp_result = tmprest.getInt(1);
                if (tmp_result > 0) {
                    tmpResult = true;
                }
                if (tmprest.wasNull()) {
                    Log.errors("DB2TXT1.getInGamCheck() 조달업체의 인감등록여부를 확인하는 도중 문제가 발생하였습니다.");
                }
            }
        }
        finally {
            try {
                if (tmprest != null) {
                    tmprest.close();
                }
            }
            catch (Exception ex) {}
            try {
                if (tmpstmt != null) {
                    tmpstmt.close();
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (tmprest != null) {
                tmprest.close();
            }
        }
        catch (Exception ex3) {}
        try {
            if (tmpstmt != null) {
                tmpstmt.close();
            }
        }
        catch (Exception ex4) {}
        return tmpResult;
    }
}
