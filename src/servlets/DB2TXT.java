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

public class DB2TXT extends HttpServlet
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
        ResultSet rs4 = null;
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sbb = new StringBuffer();
        final StringBuffer sbb2 = new StringBuffer();
        final StringBuffer sbb3 = new StringBuffer();
        String sql = null;
        String fileWrite = null;
        int LincenseCount = 0;
        int LincenseCount2 = 0;
        int LincenseCount3 = 0;
        final PrintWriter out = res.getWriter();
        final String fileName = ComStr.checkNull(req.getParameter("fileName"), "");
        final String transNo = ComStr.checkNull(req.getParameter("transNo"), "");
        try {
            trx = new Trx(this, "usemn");
            con = trx.getConnection();
            sb.append("\n SELECT U1.COM_NM , U1.ZIP_CD,\tU1.ADDR ||' '|| U1.나머지주소 주소 ");
            sb.append("\n      , U5.담당자명, U5.담당자전화번호, U5.담당자팩스번호 ");
            sb.append("\n      , DECODE (U1.MOD_CLS , 1, '경쟁입찰 참가자격 등록신청 증빙서류 제출' ");
            sb.append("\n \t                 , 2, '경쟁입찰 참가자격 변경신청 증빙서류 제출' ");
            sb.append("\n \t\t\t\t\t , '') 제목 ");
            sb.append("\n      , U6.CD_NM 승인지청 ");
            sb.append("\n\t   , to_char(U1.REG_DT,'YYYY.MM.DD') 신청일자, U1.BIZ_REG_NO, U1.REPR_NM ");
            sb.append("\n\t   , G2.면허코드,\t G2.면허번호, G3.일련번호 ");
            sb.append("\n      , G4.물품분류번호, G4.제조여부, G4.형식승인번호, G2.코드명2 면허코드명, U7.BIZ_CLS2 ");
            sb.append("\n   FROM (SELECT max(U2.LICENSE_CD) 면허코드, max(U2.LICENSE_NO) 면허번호, max(U2.BIZ_REG_NO) 사업자등록번호 ");
            sb.append("\n               , max(S2.CD_NM2) CD_NM2 ");
            sb.append("\n           FROM UM_REC_LICENSE_FACTS U2, SYN_PUB_CODE S2 ");
            sb.append("\n          WHERE U2.LICENSE_CD = S2.CD ");
            sb.append("\n            AND S2.CD_CLS='GU9' ");
            sb.append("\n            AND exists  ( SELECT /*+ index(U5 PK_사용_전자문서상태) */ 'x' ");
            sb.append("\n                                      FROM UM_EDOC_STATE U5 ");
            sb.append("\n                                     WHERE U5.TRANS_NO = ? and U2.BIZ_REG_NO = U5.BIZ_REG_NO and rownum = 1 )) G2 ");
            sb.append("\n     , ( SELECT max(U3.SERIAL_NO) 일련번호, max(U3.BIZ_REG_NO) 사업자등록번호 ");
            sb.append("\n           FROM UM_REC_FACTORY_INFO U3 ");
            sb.append("\n          WHERE exists   ( SELECT /*+ index(U5 PK_사용_전자문서상태) */ 'x' ");
            sb.append("\n                                      FROM 사용_전자문서상태 U5 ");
            sb.append("\n    \t                               WHERE U5.전송번호 = ? and U3.BIZ_REG_NO = U5.사업자등록번호 and rownum = 1) ) G3 ");
            sb.append("\n     , ( SELECT max(U4.GOOD_CLS_NO) 물품분류번호, max(U4.DIRECT_PRODUCTION_YN) 제조여부, max(U4.PERMIT_NO) 형식승인번호 ");
            sb.append("\n               , max(U4.BIZ_REG_NO) 사업자등록번호 ");
            sb.append("\n           FROM UM_REC_SUPPLIER_ENTER_ITEM U4 ");
            sb.append("\n          WHERE exists  ( SELECT /*+ index(U5 PK_사용_전자문서상태) */ 'x' ");
            sb.append("\n                                      FROM 사용_전자문서상태 U5 ");
            sb.append("\n                                     WHERE U5.전송번호 = ? and U4.BIZ_REG_NO = U5.사업자등록번호 and rownum = 1) )  G4 ");
            sb.append("\n     , SYN_PUB_CODE U6 ");
            sb.append("\n     , UM_USER U5 ");
            sb.append("\n     , UM_EDOC_STATE U1 ");
            sb.append("\n     , UM_REC_SUPPLIER_ENTER_MAST U7 ");
            sb.append("\n WHERE U1.BIZ_REG_NO = U5.마스터코드(+) ");
            sb.append("\n   AND U1.BIZ_REG_NO = G2.BIZ_REG_NO(+) ");
            sb.append("\n   AND U1.BIZ_REG_NO = G3.BIZ_REG_NO(+) ");
            sb.append("\n   AND U1.BIZ_REG_NO = G4.BIZ_REG_NO(+) ");
            sb.append("\n   AND U1.BIZ_REG_NO = U7.BIZ_REG_NO(+) ");
            sb.append("\n   AND U6.CD_CLS = 'GUJ' ");
            sb.append("\n   AND U1.PERMIT_BRANCH = U6.CD ");
            sb.append("\n   AND U1.TRANS_NO = ? ");
            sql = sb.toString();
            psmt = con.prepareStatement(sql);
            psmt.setString(1, transNo);
            psmt.setString(2, transNo);
            psmt.setString(3, transNo);
            psmt.setString(4, transNo);
            rs = psmt.executeQuery();
            psmt.clearParameters();
            if (rs.next()) {
                final String SaupNumber = rs.getString(10);
                final String SaupNumber_1 = SaupNumber.substring(0, 3);
                final String SaupNumber_2 = SaupNumber.substring(3, 5);
                final String SaupNumber_3 = SaupNumber.substring(5, SaupNumber.length());
                fileWrite = String.valueOf(ComStr.checkNull(rs.getString(1))) + "^" + ComStr.divideComma(ComStr.checkNull(rs.getString(2)), "-", 3) + "^" + ComStr.checkNull(rs.getString(3)) + "^" + ComStr.checkNull(rs.getString(4)) + "^" + ComStr.checkNull(rs.getString(5)) + "^" + ComStr.checkNull(rs.getString(6)) + "^" + ComStr.checkNull(rs.getString(7)) + "^" + ComStr.checkNull(rs.getString(8)) + "^" + ComStr.checkNull(rs.getString(9)) + "^" + SaupNumber_1 + "-" + SaupNumber_2 + "-" + SaupNumber_3 + "^" + ComStr.checkNull(rs.getString(11)) + "^\r\n";
                final String saupNo = rs.getString(10);
                final String licenseCode = rs.getString(12);
                final String ilrunNo = rs.getString(14);
                final String goodsNo = rs.getString(15);
                final String makeYN = rs.getString(16);
                final String formNo = rs.getString(17);
                final String code2 = rs.getString(18);
                final String kiupGubun = rs.getString(19);
                int attach = 0;
                if (goodsNo != null && makeYN.equals("Y")) {
                    if (ilrunNo != null) {
                        ++attach;
                        fileWrite = String.valueOf(fileWrite) + attach + ". 공장등록증명서 원본 (최근 3개월 이내 발행분)\r\n" + "  [단, 임대공장등록증명서에 임대기간이 명기되어 있지 않을 경우에는 임대계약서를 첨부]\r\n";
                    }
                    else {
                        ++attach;
                        fileWrite = String.valueOf(fileWrite) + attach + ". 건축물관리대장사본(인감으로 '사실과 상위없음' 날인) \r\n";
                    }
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 카다로그 원본 \r\n";
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 세금계산서 또는 생산물품증명서류 사본(인감으로 '사실과 상위없음' 날인) \r\n";
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 물품제조 사실확인서(경쟁입찰참가자격등록신청서 상단 안내설명 또는 나라장터 → 고객지원 → 자료실 → 서식자료실에서 다운로드)  \r\n";
                }
                if (formNo != null) {
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 형식승인 증명서류사본(인감으로 '사실과 상위없음' 날인) \r\n";
                }
                sql = "";
                sbb2.append("\n SELECT Count(*) FROM 사용_접수면허사항 A, syn_공동코드 B   ");
                sbb2.append("\n WHERE B.코드구분 = 'GU9'\t\t\t\t\t\t\t\t      ");
                sbb2.append("\n AND A.면허코드 = B.코드\t\t\t\t\t\t\t\t      ");
                sbb2.append("\n AND B.코드명 like '%용역'\t\t\t\t\t              ");
                sbb2.append("\n AND A.사업자등록번호 = ( SELECT 사업자등록번호             ");
                sbb2.append("\n                            FROM 사용_전자문서상태 U5       ");
                sbb2.append("\n                           WHERE U5.전송번호 = ?)           ");
                sql = sbb2.toString();
                psmt3 = con.prepareStatement(sql);
                psmt3.setString(1, transNo);
                rs3 = psmt3.executeQuery();
                if (rs3.next()) {
                    LincenseCount2 = rs3.getInt(1);
                }
                sql = "";
                sbb3.append("\n SELECT Count(*) FROM 사용_접수면허사항\t\t\t\t  ");
                sbb3.append("\n WHERE 사업자등록번호 = ( SELECT 사업자등록번호         ");
                sbb3.append("\n                            FROM 사용_전자문서상태 U5   ");
                sbb3.append("\n                            WHERE U5.전송번호 = ?)      ");
                sql = sbb3.toString();
                psmt4 = con.prepareStatement(sql);
                psmt4.setString(1, transNo);
                rs4 = psmt4.executeQuery();
                if (rs4.next()) {
                    LincenseCount3 = rs4.getInt(1);
                }
                if (licenseCode != null && (LincenseCount3 == 0 || LincenseCount2 != LincenseCount3)) {
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 신청 업종 등록수첩과 당해 업종 인·허가, 면허, 등록, 신고증 등 사본(모두 있는 경우는 모두 제출, 인감으로 '사실과 상위없음' 날인) \r\n";
                }
                if (kiupGubun != null && kiupGubun.equals("1")) {
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 벤처기업 증명서류사본(인감으로 '사실과 상위없음' 날인) \r\n";
                }
                sql = "";
                sbb.append("\n SELECT Count(*) FROM 사용_접수면허사항 A, syn_공동코드 B   ");
                sbb.append("\n WHERE B.코드구분 = 'GU9'\t\t\t\t\t\t\t\t      ");
                sbb.append("\n AND A.면허코드 = B.코드\t\t\t\t\t\t\t\t      ");
                sbb.append("\n AND B.코드명 like '%용역'\t\t\t\t\t              ");
                sbb.append("\n AND A.사업자등록번호 = ( SELECT 사업자등록번호             ");
                sbb.append("\n                            FROM 사용_전자문서상태 U5       ");
                sbb.append("\n                           WHERE U5.전송번호 = ?)           ");
                sql = sbb.toString();
                psmt2 = con.prepareStatement(sql);
                psmt2.setString(1, transNo);
                rs2 = psmt2.executeQuery();
                if (rs2.next()) {
                    LincenseCount = rs2.getInt(1);
                }
                if (LincenseCount != 0) {
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + "※ [~용역]으로 끝나는 업종은 사업자등록증 [종목]란에 용역명 또는 유사명이 등재된 경우에만 처리되며, 별도의 서류제출 없이 처리됩니다. \r\n";
                }
                if (attach == 0) {
                    fileWrite = String.valueOf(fileWrite) + "※ 본건은 시행문 및 증빙서류 제출없이 승인처리되므로 조달청에 우편발송이나 방문하실 필요가 없습니다. \r\n   나라장터-조달업체이용자등록-등록신청확인및시행문출력에서 승인여부를 확인하시고, 승인 완료가 되면  인증서등록을 하시면 됩니다.\r\n";
                }
                else {
                    ++attach;
                    fileWrite = String.valueOf(fileWrite) + attach + ". 인감증명서(제출서류 확인용, 경쟁입찰참가자격등록신청서 상단 안내설명 또는 고객지원 -> 자료실 -> 나라장터자료 -> 18번 \"등록업체 사용인감 등록절차 안내(12.11)\"에서 다운로드) \r\n";
                }
                fileWrite = String.valueOf(fileWrite) + "\r\n- 끝 -";
                fileWrite = String.valueOf(fileWrite) + "\r\n\n※ 승인지청에서 승인 통보시까지 약 1일정도 소요됩니다.";
            }
        }
        catch (SQLException e) {
            Log.errors("DB2TXT.java :" + e.toString());
        }
        catch (Exception e2) {
            Log.errors("DB2TXT.java :" + e2.toString());
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                }
                catch (Exception ex) {}
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                }
                catch (Exception ex2) {}
            }
            if (rs3 != null) {
                try {
                    rs3.close();
                }
                catch (Exception ex3) {}
            }
            if (rs4 != null) {
                try {
                    rs4.close();
                }
                catch (Exception ex4) {}
            }
            if (psmt != null) {
                try {
                    psmt.close();
                }
                catch (Exception ex5) {}
            }
            if (psmt2 != null) {
                try {
                    psmt2.close();
                }
                catch (Exception ex6) {}
            }
            if (psmt3 != null) {
                try {
                    psmt3.close();
                }
                catch (Exception ex7) {}
            }
            if (psmt4 != null) {
                try {
                    psmt4.close();
                }
                catch (Exception ex8) {}
            }
            if (con != null) {
                try {
                    trx.close();
                }
                catch (Exception ex9) {}
            }
        }
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception ex10) {}
        }
        if (rs2 != null) {
            try {
                rs2.close();
            }
            catch (Exception ex11) {}
        }
        if (rs3 != null) {
            try {
                rs3.close();
            }
            catch (Exception ex12) {}
        }
        if (rs4 != null) {
            try {
                rs4.close();
            }
            catch (Exception ex13) {}
        }
        if (psmt != null) {
            try {
                psmt.close();
            }
            catch (Exception ex14) {}
        }
        if (psmt2 != null) {
            try {
                psmt2.close();
            }
            catch (Exception ex15) {}
        }
        if (psmt3 != null) {
            try {
                psmt3.close();
            }
            catch (Exception ex16) {}
        }
        if (psmt4 != null) {
            try {
                psmt4.close();
            }
            catch (Exception ex17) {}
        }
        if (con != null) {
            try {
                trx.close();
            }
            catch (Exception ex18) {}
        }
        out.println(fileWrite);
    }
}
