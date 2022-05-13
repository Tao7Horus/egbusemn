// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.Log;
import common.Trx;
import java.sql.Connection;
import common.util.CommUtil;
import entity.UM_FOE_CommInfo;

public class UM_FOB_Forn052
{
    public UM_FOE_CommInfo getGeneralBInfo(final String str) throws Exception {
        return new UM_FOB_Forn062().getFornUpche(this, "SELECT 사업자등록번호,\t상호명,\t\t영문상호명,\t\tTO_CHAR(개업일자,'YYYY-MM-DD'),\t\t국적,\t\n       자본금,\t\t\t업무구분,\t\t지역코드,\t\t\t기업구분1,\t\t\t\t\t\t\t우편번호,\n\t   주소,\t\t\t\t전화번호,\t\t나머지주소,\t\tFAX번호,\t\t\t\t\t\t\t\t종업원수,\n\t   홈페이지,\t\t\tB.코드명,\tTO_CHAR(법인설립일자,'YYYY-MM-DD'),법인등록번호,\t\t등록일자,\n\t   갱신일자,\t\t\t처리자ID,\t승인지청,\t\t\t기업구분2, 입찰참가자격여부\t\t\t\t\n  FROM 사용_접수조달업체마스터 A, SYN_공동코드 B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n WHERE A.국적 = B.코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n   AND B.코드구분 = 'J02'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n   AND A.사업자등록번호 = '" + new CommUtil().replace(CommUtil.isNull(str), "-", "") + "'                                                            \n", null);
    }
    
    public UM_FOE_CommInfo[] getDaepyoBInfo(final String str) throws Exception {
        return new UM_FOB_Forn062().getDaepyo(this, "SELECT 대표자명, 대표자주민번호, 대표자메일주소, 대표대표자여부                 \t\t    \nFROM   사용_접수대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\nWHERE  사업자등록번호 = '" + new CommUtil().replace(CommUtil.isNull(str), "-", "") + "'", null);
    }
    
    public UM_FOE_CommInfo[] getMulpumBInfo(final String str) throws Exception {
        return new UM_FOB_Forn062().getMulpumBInfo(this, " SELECT /*+ ordered use_nl(a b ) */ Distinct B.분류명, A.물품분류번호, A.비축품목연간소요량  FROM 사용_접수조달품목 A, SYN_VIEW_물품분류매핑 B  WHERE A.사업자등록번호 = '" + new CommUtil().replace(CommUtil.isNull(str), "-", "") + "' " + "   AND A.비축품목여부 = 'Y' " + "   AND A.물품분류번호 = B.물품분류 ", null);
    }
    
    public UM_FOE_CommInfo[] getBichukMulpumInfo(final String str) throws Exception {
        return new UM_FOB_Forn062().getBichukMulpumInfo(this, " SELECT 물품분류번호, 비축품목여부, 비축품목연간소요량  FROM 사용_조달품목  WHERE 사업자등록번호 = '" + new CommUtil().replace(CommUtil.isNull(str), "-", "") + "' ", null);
    }
    
    public UM_FOE_CommInfo[] getBichukMulpumInfo_U(final String str) throws Exception {
        return new UM_FOB_Forn062().getBichukMulpumInfo_U(this, " SELECT  Distinct B.분류명, A.물품분류번호, A.비축품목연간소요량  FROM 사용_조달품목 A, SYN_VIEW_물품분류매핑 B  WHERE A.사업자등록번호 = '" + new CommUtil().replace(CommUtil.isNull(str), "-", "") + "' " + "   AND A.비축품목여부 = 'Y' " + "   AND A.물품분류번호 = B.물품분류 ", null);
    }
    
    public UM_FOE_CommInfo getGeneralInfo(final String str) throws Exception {
        return new UM_FOB_Forn062().getFornUpche(this, "SELECT 사업자등록번호,\t상호명,\t\t영문상호명,\t\tTO_CHAR(개업일자,'YYYY-MM-DD'),\t\t국적,\t\n       자본금,\t\t\t업무구분,\t\t지역코드,\t\t\t기업구분1,\t\t\t\t\t\t\t우편번호,\n\t   주소,\t\t\t\t전화번호,\t\t나머지주소,\t\tFAX번호,\t\t\t\t\t\t\t\t종업원수,\n\t   홈페이지,\t\t\tB.코드명,\tTO_CHAR(법인설립일자,'YYYY-MM-DD'),법인등록번호,\t\t등록일자,\n\t   갱신일자,\t\t\t처리자ID,\t승인지청,\t\t\t기업구분2, 입찰참가자격여부\t\t\t\t\t\n  FROM 사용_조달업체마스터 A, SYN_공동코드 B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n WHERE A.국적 = B.코드\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n   AND B.코드구분 = 'J02'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n   AND A.사업자등록번호 = '" + new CommUtil().replace(CommUtil.isNull(str), "-", "") + "'                                                            \n", null);
    }
    
    public UM_FOE_CommInfo[] getDaepyoInfo(final String str) throws Exception {
        return new UM_FOB_Forn062().getDaepyo(this, "SELECT 대표자명, 대표자주민번호, 대표자메일주소, 대표대표자여부                 \t\t    \nFROM   사용_대표자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\nWHERE  사업자등록번호 = '" + new CommUtil().replace(CommUtil.isNull(str), "-", "") + "'", null);
    }
    
    public UM_FOE_CommInfo[] getMulpumInfo(final String str) throws Exception {
        final String replace = new CommUtil().replace(CommUtil.isNull(str), "-", "");
        return new UM_FOB_Forn062().getMulpum(this, "SELECT Distinct B.품명, A.물품분류번호, A.최근3년간_매출액, A.대표물품여부\t\t\n  FROM 사용_조달품목 A,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n       SYN_VIEW_물품분류매핑 B,\t\t\t\t\t\t\t\t\t\t\t\t\n\t   (SELECT MAX(B.정부물품분류번호) 정부물품분류번호, 물품분류\t\t\t\t\t\n\t      FROM 사용_조달품목 A,\t\t\t\t\t\t\t\t\t\t\t\t\n       \t  \t   SYN_VIEW_물품분류매핑 B\t\t\t\t\t\t\t\t\t\t\n\t\t WHERE A.물품분류번호 = B.물품분류\t\t\t\t\t\t\t\t\t\t\n\t\t   AND A.사업자등록번호 = '" + replace + "'\t\t\t\t\t\t\t\t\n" + "\t\t  GROUP BY 물품분류 ) C\t\t\t\t\t\t\t\t\t\t\t\t\n" + " WHERE A.물품분류번호 = B.물품분류\t\t\t\t\t\t\t\t\t\t\t\t\n" + "   AND B.정부물품분류번호 = C.정부물품분류번호\t\t\t\t\t\t\t\t\t\n" + "   AND A.사업자등록번호 = '" + replace + "'\t\t\t\t\t\t\t\t\t\t\n", null);
    }
    
    public UM_FOE_CommInfo getGeneralEInfo(final String str, final String str2) throws Exception {
        final String string = "SELECT 사업자등록번호, 상호명, 영문상호명, TO_CHAR(개업일자,'YYYY-MM-DD'), 국적, 자본금, 업무구분, 지역코드, \t\t\n       기업구분1, 우편번호, 주소, 전화번호, 나머지주소, FAX번호, 종업원수, 홈페이지\t\t\t\t\t\t\t\t\nFROM   사용_조달업체마스터_이력\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\nWHERE  사업자등록번호 = '" + new CommUtil().replace(CommUtil.isNull(str), "-", "") + "' and TO_CHAR(입력일자, 'YYYY-MM-DD HH24:MI:SS') = '" + CommUtil.isNull(str2) + "'";
        System.out.println("sql" + string);
        return new UM_FOB_Forn062().getFornUpcheE(this, string, null);
    }
    
    public UM_FOE_CommInfo getDocInfo(final String str, final String str2, final String str3) throws Exception {
        return new UM_FOB_Forn062().getDocInfo(this, "SELECT 휴대폰, SMS수신여부, 진행상태, 처리사유\t\t\t\t\t\nFROM   사용_전자문서상태\t\t\t\t\t\t\t\t\t\t\nWHERE  사업자등록번호 = '" + new CommUtil().replace(CommUtil.isNull(str), "-", "") + "' \t\t\t\t\t\t\t\n" + "AND 신청_변경구분 = '" + CommUtil.isNull(str2) + "' \t\t\t\t\t\t\t\t\n" + "AND 신청일자 = TO_DATE('" + CommUtil.isNull(str3) + "', 'YYYY-MM-DD hh24:MI:SS')\t\n", null);
    }
    
    public int getUpcheCount(final String s) throws Exception {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        final StringBuffer sb = new StringBuffer();
        String string = "";
        int int1 = 0;
        try {
            string = "SELECT COUNT(*)\t\t\t\t\t\t\t\t\n   FROM 사용_전자문서상태 C\t\t\t\t\t\n  WHERE C.사업자등록번호\t= '" + s + "'\t\t\n" + "    AND C.신청_변경구분\t='2'\t\t\t\t\n" + "    AND C.진행상태 not in ('1', '3', '4') \n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn052.getUpcheCount() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn052.getUpcheCount() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
    
    public int getBichukYN(final String s) throws Exception {
        Trx trx = null;
        Connection connection = null;
        ResultSet executeQuery = null;
        PreparedStatement prepareStatement = null;
        String string = "";
        int int1 = 0;
        try {
            string = " SELECT COUNT(*)                      \n FROM 사용_업체구분                   \n WHERE 사업자등록번호 = '" + s + "' \n" + "   AND 업체구분 = '2'                 \n" + "   AND 사용여부 = 'Y'                 \n";
            trx = new Trx(this, "usemn");
            connection = trx.getConnection();
            prepareStatement = connection.prepareStatement(string);
            executeQuery = prepareStatement.executeQuery();
            if (executeQuery.next()) {
                int1 = executeQuery.getInt(1);
            }
        }
        catch (SQLException ex) {
            Log.debug("UM_FOB_Forn052.getBichukYN() : sql : " + string + "::: " + ex.toString());
        }
        catch (Exception ex2) {
            Log.debug("UM_FOB_Forn052.getBichukYN() : sql : " + string + "::: " + ex2.toString());
        }
        finally {
            if (executeQuery != null) {
                try {
                    executeQuery.close();
                }
                catch (Exception ex3) {}
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                }
                catch (Exception ex4) {}
            }
            if (connection != null) {
                try {
                    trx.close();
                }
                catch (Exception ex5) {}
            }
        }
        return int1;
    }
}
