// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import common.Trx;
import java.util.Vector;
import common.OneRowEntity;
import common.Log;
import common.ComDbQuery;
import common.CommEntity;
import java.sql.Connection;

public class UM_COV_Report02_Control
{
    private int MAX_ROW;
    
    public UM_COV_Report02_Control() {
        this.MAX_ROW = 2000;
    }
    
    public CommEntity[] getRequestList(final String startTime, final String endTime, final String ID, final String saupNo, final Connection conn) throws Exception {
        final Runtime aRunTime = Runtime.getRuntime();
        final long startMemory = aRunTime.freeMemory();
        final StringBuffer sb = new StringBuffer();
        String sql = "";
        String[] parameter = (String[])null;
        sb.append("\n SELECT  ROWNUM                                             ");
        sb.append("\n       ,  TO_CHAR(U1.등록일자, 'yyyy.mm.dd') 등록일자       ");
        sb.append("\n       , SUBSTR(U1.사업자등록번호, 1, 3)||'-'||SUBSTR(U1.사업자등록번호, 4, 2)||'-'||SUBSTR(U1.사업자등록번호, 6, 5) 사업자등록번호               ");
        sb.append("\n       , DECODE(SUBSTR(U1.업무구분, 1, 1), '1', '[물품]')||DECODE(SUBSTR(U1.업무구분, 2, 1), '1', '[공사]')|| DECODE(SUBSTR(U1.업무구분, 3, 1), '1', '[용역]') 업무구분명               ");
        sb.append("\n       ,  U1.상호명                                         ");
        sb.append("\n       ,  U2.대표자명                                       ");
        sb.append("\n       ,  U1.주소 || ' ' || U1.나머지주소 주소              ");
        sb.append("\n       ,  U1.전화번호                                       ");
        sb.append("\n    FROM  사용_대표자 U2                                    ");
        sb.append("\n       ,  사용_조달업체마스터 U1                            ");
        sb.append("\n  WHERE  U1.사업자등록번호 = U2.사업자등록번호(+)           ");
        sb.append("\n    AND  U2.대표대표자여부 ='Y'                             ");
        sb.append("\n    AND  U1.등록일자 BETWEEN TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss') ");
        sb.append("\n                         AND TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss') ");
        sb.append("\n    AND  U1.테스트여부 is null                              ");
        sb.append("\n    AND  U1.입찰참가자격여부 is null                        ");
        if (ID != null && ID.length() != 0) {
            sb.append("\n   AND  U1.승인지청 = ? ");
        }
        if (saupNo != null && saupNo.length() == 10) {
            sb.append("\n \tAND  U1.사업자등록번호 = ? ");
        }
        sb.append("\n   ORDER BY U1.등록일자, U1.사업자등록번호                    ");
        sql = sb.toString();
        try {
            if (ID == null || ID.length() == 0 || saupNo == null || saupNo.length() != 10) {
                parameter = new String[] { startTime, endTime };
            }
            if (ID != null && ID.length() != 0 && (saupNo == null || saupNo.length() != 10)) {
                parameter = new String[] { startTime, endTime, ID };
            }
            if (saupNo != null && saupNo.length() == 10 && (ID == null || ID.length() == 0)) {
                parameter = new String[] { startTime, endTime, saupNo };
            }
            if ((ID != null || ID.length() != 0) && saupNo != null && saupNo.length() == 10) {
                parameter = new String[] { startTime, endTime, ID, saupNo };
            }
            final Runtime bRunTime = Runtime.getRuntime();
            final long endMemory = bRunTime.freeMemory();
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getRequestList() :startTime[" + startTime + "] , endTime[" + endTime + "] , 승인지청(ID)[" + ID + "]" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] getReqPumName(final String saupNo, final Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection 은 null 일수 없습니다.");
        }
        final StringBuffer sb = new StringBuffer();
        String sql = "";
        sb.append("\n SELECT /*+ use_nl(c u1 u3 s5 )*/                     ");
        sb.append("\n        distinct (case when C.aa > 0 then S5.분류명||' 외 '|| C.aa||'건' when C.aa = 0 then S5.분류명 else '0' end) 품명및업종명\t");
        sb.append("\n FROM SYN_VIEW_물품분류매핑 S5,                       ");
        sb.append("\n      사용_조달품목 U3,                               ");
        sb.append("\n   사용_조달업체마스터 U1,                            ");
        sb.append("\n      (SELECT /*+ use_nl(u1 u3 s5) */                 ");
        sb.append("\n           count(distinct(U3.물품분류번호)) - 1 aa,   ");
        sb.append("\n           max(U3.물품분류번호) 물품분류번호,         ");
        sb.append("\n           U1.사업자등록번호                          ");
        sb.append("\n       FROM SYN_VIEW_물품분류매핑 S5,                 ");
        sb.append("\n         사용_조달품목 U3,                            ");
        sb.append("\n         사용_조달업체마스터 U1                       ");
        sb.append("\n       WHERE U3.물품분류번호 = S5.물품분류            ");
        sb.append("\n         AND U1.사업자등록번호 = U3.사업자등록번호(+) ");
        sb.append("\n         AND U1.사업자등록번호 = replace(?, '-')      ");
        sb.append("\n         AND U1.테스트여부 is null                    ");
        sb.append("\n       GROUP BY U1.사업자등록번호) C                  ");
        sb.append("\n WHERE U3.물품분류번호 = S5.물품분류                  ");
        sb.append("\n   AND C.물품분류번호 = S5.물품분류                   ");
        sb.append("\n   AND U1.사업자등록번호 = C.사업자등록번호           ");
        sb.append("\n   AND U1.사업자등록번호 = U3.사업자등록번호(+)       ");
        sb.append("\n   AND U1.테스트여부 is null                          ");
        sb.append("\n  UNION ALL\t\t\t\t\t\t\t\t\t\t   ");
        sb.append("\n  SELECT  S6.코드명 업종명\t\t\t\t\t\t\t   ");
        sb.append("\n    FROM  SYN_공동코드 S6\t\t\t\t\t\t\t   ");
        sb.append("\n       ,  사용_면허사항 U4\t\t\t\t\t\t\t   ");
        sb.append("\n   WHERE  S6.코드구분       = 'GU9'\t\t\t\t   ");
        sb.append("\n     AND  S6.코드           = U4.면허코드\t\t\t   ");
        sb.append("\n     AND  U4.사업자등록번호 = replace(?, '-')\t\t   ");
        sql = sb.toString();
        try {
            final String[] parameter = { saupNo, saupNo };
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getReqPumName() :사업자등록번호[" + saupNo + "]" + exm.toString());
            throw exm;
        }
    }
    
    public OneRowEntity getReqCount(final String startTime, final String endTime, final String ID, final String saupNo, final Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection 은 null 일수 없습니다.");
        }
        final StringBuffer sb = new StringBuffer();
        String sql = "";
        String[] parameter = (String[])null;
        sb.append("\n  SELECT  count(decode(substr(u1.업무구분,1,1),'1', U1.사업자등록번호)) 물품,  ");
        sb.append("\n          count(decode(substr(u1.업무구분,2,1),'1', U1.사업자등록번호)) 공사,  ");
        sb.append("\n          count(decode(substr(u1.업무구분,3,1),'1', U1.사업자등록번호)) 용역,\t");
        sb.append("\n          count(decode(substr(u1.업무구분,1,1),'1', U1.사업자등록번호)) + \t\t");
        sb.append("\n          count(decode(substr(u1.업무구분,2,1),'1', U1.사업자등록번호)) + \t\t");
        sb.append("\n          count(decode(substr(u1.업무구분,3,1),'1', U1.사업자등록번호)) 총계,\t");
        sb.append("\n          count (U1.사업자등록번호) 등록업체수,\t\t\t\t\t\t\t\t");
        if (ID != null && ID.length() != 0) {
            sb.append("\n   (select 코드명 from syn_공동코드 where 코드구분 = 'GUJ' and U1.승인지청 = 코드) 승인지청  ");
        }
        else {
            sb.append("\n   '전체' 승인지청\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
        }
        sb.append("\n    FROM  사용_대표자 U2\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n       ,  사용_조달업체마스터 U1                            \t\t\t\t\t");
        sb.append("\n  WHERE  U1.사업자등록번호 = U2.사업자등록번호(+)           \t\t\t\t\t");
        sb.append("\n    AND  U2.대표대표자여부 ='Y'                             \t\t\t\t\t");
        sb.append("\n    AND  U1.등록일자 BETWEEN TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss')\t\t\t\t");
        sb.append("\n                         AND TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss')\t\t\t\t");
        sb.append("\n    AND  U1.테스트여부 is null\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n    AND  U1.입찰참가자격여부 is null\t\t\t\t\t\t\t\t\t\t\t");
        if (ID != null && ID.length() != 0) {
            sb.append("\n   AND  U1.승인지청 = ? ");
        }
        if (saupNo != null && saupNo.length() == 10) {
            sb.append("\n \tAND  U1.사업자등록번호 = ? ");
        }
        if (ID != null && ID.length() != 0) {
            sb.append("\n GROUP BY U1.승인지청\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        }
        sql = sb.toString();
        try {
            if (ID == null || ID.length() == 0 || saupNo == null || saupNo.length() != 10) {
                parameter = new String[] { startTime, endTime };
            }
            if (ID != null && ID.length() != 0 && (saupNo == null || saupNo.length() != 10)) {
                parameter = new String[] { startTime, endTime, ID };
            }
            if (saupNo != null && saupNo.length() == 10 && (ID == null || ID.length() == 0)) {
                parameter = new String[] { startTime, endTime, saupNo };
            }
            if ((ID != null || ID.length() != 0) && saupNo != null && saupNo.length() == 10) {
                parameter = new String[] { startTime, endTime, ID, saupNo };
            }
            final Runtime bRunTime = Runtime.getRuntime();
            final long endMemory = bRunTime.freeMemory();
            return new ComDbQuery().getOneRowList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getReqCount() :startTime[" + startTime + "] , endTime[" + endTime + "] , 승인지청(ID)[" + ID + "]" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] getModifyList(final String startTime, final String endTime, final String ID, final String saupNo, final Connection conn) throws Exception {
        final Runtime aRunTime = Runtime.getRuntime();
        final long startMemory = aRunTime.freeMemory();
        final Vector Vec = new Vector(1, 1);
        final StringBuffer sb = new StringBuffer();
        String sql = "";
        String[] parameter = (String[])null;
        sb.append("\n   SELECT ROWNUM, TO_CHAR(U1.갱신일자, 'YYYY.MM.DD') 갱신일자                                   \t\t  ");
        sb.append("\n       , SUBSTR(U1.사업자등록번호, 1, 3)||'-'||SUBSTR(U1.사업자등록번호, 4, 2)||'-'||SUBSTR(U1.사업자등록번호, 6, 5) 사업자등록번호               ");
        sb.append("\n       , DECODE(SUBSTR(U2.업무구분, 1, 1), '1', '[물품]')||DECODE(SUBSTR(U2.업무구분, 2, 1), '1', '[공사]')|| DECODE(SUBSTR(U2.업무구분, 3, 1), '1', '[용역]') 업무구분명               ");
        sb.append("\n       , U2.상호명\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
        sb.append("\n\t\t, DECODE (U1.속성명, '면허코드', (SELECT 코드명 FROM syn_공동코드                                 ");
        sb.append("\n\t\t                                  WHERE 코드구분='GU9' AND 코드= U1.변경전내용)\t\t\t\t\t  ");
        sb.append("\n\t\t                               , U1.변경전내용,                                    \t\t\t\t  ");
        sb.append("\n\t\t                     '물품분류번호', (SELECT distinct 분류명 FROM syn_view_물품분류매핑           ");
        sb.append("\n\t\t                                      WHERE  물품분류 = U1.변경전내용)                            ");
        sb.append("\n\t\t                               , U1.변경전내용,                                    \t\t\t\t  ");
        sb.append("\n\t\t                     '상호명', U1.변경전내용,                                     \t\t\t\t  ");
        sb.append("\n\t\t                     '주소', U1.변경전내용,                                     \t\t\t\t  ");
        sb.append("\n\t\t                     '대표자', U1.변경전내용,                                     \t\t\t\t  ");
        sb.append("\n\t\t                     '시공능력평가액', U1.변경전내용,                                     \t\t  ");
        sb.append("\n\t\t                     '업종', U1.변경전내용,                                     \t\t\t\t  ");
        sb.append("\n\t\t                     '제조물품', U1.변경전내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '업무구분', DECODE(SUBSTR(U2.업무구분, 1, 1), '1', '[물품]')||DECODE(SUBSTR(U2.업무구분, 2, 1), '1', '[공사]')||DECODE(SUBSTR(U2.업무구분, 3, 1), '1', '[용역]'), U1.변경전내용,\t\t\t ");
        sb.append("\n\t\t                     '대표자', U1.변경전내용,                                     \t\t\t\t  ");
        sb.append("\n\t\t                     '대표대표자여부', U1.변경전내용,                                     \t\t  ");
        sb.append("\n\t\t                     '대표자명', U1.변경전내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '대표자주민번호', U1.변경전내용,                                     \t\t  ");
        sb.append("\n\t\t                     '면허취득일자', U1.변경전내용,                                     \t\t  ");
        sb.append("\n\t\t                     '면허만료일자', U1.변경전내용,                                     \t\t  ");
        sb.append("\n\t\t                     '기업구분1', U1.변경전내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '자본금', U1.변경전내용,                                     \t\t\t\t  ");
        sb.append("\n\t\t                     '제조여부', U1.변경전내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '입찰대리인', U1.변경전내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '공급물품', U1.변경전내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '공사.용역업종', U1.변경전내용,                                   \t\t\t  ");
        sb.append("\n\t\t                     '지역코드', U1.변경전내용\t                                   \t\t\t\t  ");
        sb.append("\n\t\t\t\t ) 변경전내용                                     \t\t\t\t\t\t\t\t\t\t  ");
        sb.append("\n\t\t, DECODE (U1.속성명, '면허코드', (SELECT 코드명 FROM syn_공동코드                                 ");
        sb.append("\n\t\t                                  WHERE 코드구분='GU9' AND 코드= U1.변경후내용)                   ");
        sb.append("\n\t\t                               , U1.변경후내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '물품분류번호', (SELECT distinct 분류명 FROM syn_view_물품분류매핑           ");
        sb.append("\n\t\t                                      WHERE  물품분류 = U1.변경후내용)                            ");
        sb.append("\n\t\t \t\t\t\t\t\t\t   , U1.변경후내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '상호명', U1.변경후내용,                                      \t\t\t\t  ");
        sb.append("\n\t\t                     '주소', U1.변경후내용,                                      \t\t\t\t  ");
        sb.append("\n\t\t                     '대표자', U1.변경후내용,                                      \t\t\t\t  ");
        sb.append("\n\t\t                     '시공능력평가액', U1.변경후내용,                                      \t\t  ");
        sb.append("\n\t\t                     '업종', U1.변경후내용,                                      \t\t\t\t  ");
        sb.append("\n\t\t                     '제조물품', U1.변경후내용,                                      \t\t\t  ");
        sb.append("\n\t\t                     '업무구분', DECODE(SUBSTR(U2.업무구분, 1, 1), '1', '[물품]')||DECODE(SUBSTR(U2.업무구분, 2, 1), '1', '[공사]')||DECODE(SUBSTR(U2.업무구분, 3, 1), '1', '[용역]'), U1.변경후내용,\t\t\t ");
        sb.append("\n\t\t                     '대표자', U1.변경후내용,                                     \t\t\t\t  ");
        sb.append("\n\t\t                     '대표대표자여부', U1.변경후내용,                                     \t\t  ");
        sb.append("\n\t\t                     '대표자명', U1.변경후내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '대표자주민번호', U1.변경후내용,                                     \t\t  ");
        sb.append("\n\t\t                     '면허취득일자', U1.변경후내용,                                     \t\t  ");
        sb.append("\n\t\t                     '면허만료일자', U1.변경후내용,                                     \t\t  ");
        sb.append("\n\t\t                     '기업구분1', U1.변경후내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '자본금', U1.변경후내용,                                     \t\t\t\t  ");
        sb.append("\n\t\t                     '제조여부', U1.변경후내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '입찰대리인', U1.변경후내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '공급물품', U1.변경후내용,                                     \t\t\t  ");
        sb.append("\n\t\t                     '공사.용역업종', U1.변경후내용,                                   \t\t\t  ");
        sb.append("\n\t\t                     '지역코드', U1.변경후내용\t                                   \t\t\t\t  ");
        sb.append("\n\t\t \t\t ) 변경후내용                                     \t\t\t\t\t\t\t\t\t\t  ");
        sb.append("\n\t, U1.속성명                                                                                       \t  ");
        sb.append("\n\t, DECODE(U1.변경구분,'I','추가','U','수정','D','삭제') 변경구분값                                     ");
        sb.append("\n  FROM 사용_입찰자격사항이력 U1, 사용_조달업체마스터 U2                                                  ");
        sb.append("\n WHERE  U1.사업자등록번호 = U2.사업자등록번호                                                            ");
        sb.append("\n    AND U1.갱신일자 BETWEEN TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss') AND TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss')  ");
        sb.append("\n    AND U2.테스트여부 is null                                                                            ");
        sb.append("\n    AND U1.처리자ID != 'ADMIN'                                                                           ");
        if (ID != null && ID.length() != 0) {
            sb.append("\n   AND  U2.승인지청 = ? ");
        }
        if (saupNo != null && saupNo.length() == 10) {
            sb.append("\n \tAND  U1.사업자등록번호 = ? ");
        }
        sb.append("\n\tGROUP BY ROWNUM, U1.갱신일자, U1.사업자등록번호,\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n \t\t\t U2.상호명, U2.업무구분, U1.변경전내용, U1.변경후내용, U1.속성명, U1.변경구분                   ");
        sb.append("\n   ORDER BY U1.갱신일자, U1.사업자등록번호 ");
        sql = sb.toString();
        try {
            if (ID == null || ID.length() == 0 || saupNo == null || saupNo.length() != 10) {
                parameter = new String[] { startTime, endTime };
            }
            if (ID != null && ID.length() != 0 && (saupNo == null || saupNo.length() != 10)) {
                parameter = new String[] { startTime, endTime, ID };
            }
            if (saupNo != null && saupNo.length() == 10 && (ID == null || ID.length() == 0)) {
                parameter = new String[] { startTime, endTime, saupNo };
            }
            if ((ID != null || ID.length() != 0) && saupNo != null && saupNo.length() == 10) {
                parameter = new String[] { startTime, endTime, ID, saupNo };
            }
            final Runtime bRunTime = Runtime.getRuntime();
            final long endMemory = bRunTime.freeMemory();
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getModifyList() :startTime[" + startTime + "] , endTime[" + endTime + "] , 승인지청(ID)[" + ID + "]" + exm.toString());
            throw exm;
        }
    }
    
    public String getModCnt(final String startTime, final String endTime, final String ID, final String saupNo, Connection conn) throws Exception {
        Trx resource = null;
        Statement stmt = null;
        ResultSet rs = null;
        String result = "";
        String sql = " SELECT  COUNT(U1.사업자등록번호) 갯수  FROM 사용_입찰자격사항이력 U1, 사용_조달업체마스터 U2  WHERE U1.사업자등록번호 = U2.사업자등록번호    AND U1.갱신일자 BETWEEN TO_DATE('" + startTime + "', 'yyyy-mm-dd hh24:mi:ss') AND TO_DATE('" + endTime + "', 'yyyy-mm-dd hh24:mi:ss')  " + "   AND U2.테스트여부 is null " + "   AND U1.처리자ID != 'ADMIN' ";
        if (ID != null && ID.length() != 0) {
            sql = String.valueOf(sql) + "   AND  U2.승인지청 = '" + ID + "' ";
        }
        if (saupNo != null && saupNo.length() == 10) {
            sql = String.valueOf(sql) + " \tAND  U1.사업자등록번호 = '" + saupNo + "' ";
        }
        sql = String.valueOf(sql) + " ORDER BY U1.갱신일자 ";
        Label_0593: {
            try {
                if (conn == null) {
                    resource = new Trx(this, "usemn");
                    conn = resource.getConnection();
                }
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    result = rs.getString("갯수");
                }
                if (Integer.parseInt(result) >= this.MAX_ROW) {
                    result = new Integer(this.MAX_ROW).toString();
                    break Label_0593;
                }
                result = "";
            }
            catch (SQLException se) {
                Log.debug(String.valueOf(this.getClass().getName()) + " .getModCnt() SQLException :startTime[" + startTime + "] , endTime[" + endTime + "] , 승인지청(ID)[" + ID + "]" + se.toString());
                throw se;
            }
            catch (NullPointerException ne) {
                Log.debug(String.valueOf(this.getClass().getName()) + " .getModCnt() NullPointerException :startTime[" + startTime + "] , endTime[" + endTime + "] , 승인지청(ID)[" + ID + "]" + ne.toString());
                throw ne;
            }
            catch (Exception e) {
                Log.debug(String.valueOf(this.getClass().getName()) + " .getModCnt() Exception :startTime[" + startTime + "] , endTime[" + endTime + "] , 승인지청(ID)[" + ID + "]" + e.toString());
            }
            finally {
                if (rs != null) {
                    try {
                        rs.close();
                    }
                    catch (Exception ex) {}
                }
                if (stmt != null) {
                    try {
                        stmt.close();
                    }
                    catch (Exception ex2) {}
                }
                try {
                    if (conn != null) {
                        resource.close();
                    }
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
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (Exception ex5) {}
        }
        try {
            if (conn != null) {
                resource.close();
            }
        }
        catch (Exception ex6) {}
        return result;
    }
    
    public OneRowEntity getModifyCount(final String startTime, final String endTime, final String ID, final String saupNo, final Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection 은 null 일수 없습니다.");
        }
        final StringBuffer sb = new StringBuffer();
        String sql = "";
        String[] parameter = (String[])null;
        sb.append("\n SELECT   count(decode(substr(업무구분,1,1),'1', 사업자등록번호)) 물품,\t\t\t\t\t\t\t\t  ");
        sb.append("\n          count(decode(substr(업무구분,2,1),'1', 사업자등록번호)) 공사,\t\t\t\t\t\t\t\t  ");
        sb.append("\n          count(decode(substr(업무구분,3,1),'1', 사업자등록번호)) 용역,\t\t\t\t\t\t\t\t  ");
        sb.append("\n          count(decode(substr(업무구분,1,1),'1', 사업자등록번호)) +\t\t\t\t\t\t\t\t\t  ");
        sb.append("\n          count(decode(substr(업무구분,2,1),'1', 사업자등록번호)) + \t\t\t\t\t\t\t\t\t  ");
        sb.append("\n          count(decode(substr(업무구분,3,1),'1', 사업자등록번호)) 총계,\t\t\t\t\t\t\t\t  ");
        sb.append("\n          count (사업자등록번호) 변경업체수, 승인지청\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
        sb.append("\n FROM (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
        sb.append("\n   SELECT distinct TO_CHAR(U1.갱신일자, 'YYYY.MM.DD') 갱신일자\t\t\t\t\t\t\t\t\t\t\t  ");
        sb.append("\n       , U1.사업자등록번호, U2.업무구분,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
        if (ID != null && ID.length() != 0) {
            sb.append("\n   (select 코드명 from syn_공동코드 where 코드구분 = 'GUJ' and U2.승인지청 = 코드) 승인지청\t\t  ");
        }
        else {
            sb.append("\n   '전체' 승인지청\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  ");
        }
        sb.append("\n  FROM 사용_입찰자격사항이력 U1, 사용_조달업체마스터 U2                                                  ");
        sb.append("\n  WHERE  U1.사업자등록번호 = U2.사업자등록번호                                                           ");
        sb.append("\n    AND U1.갱신일자 BETWEEN TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss') AND TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss')  ");
        sb.append("\n    AND U2.테스트여부 is null                                                                            ");
        sb.append("\n    AND U1.처리자ID != 'ADMIN'                                                                           ");
        if (ID != null && ID.length() != 0) {
            sb.append("\n   AND  U2.승인지청 = ? ");
        }
        if (saupNo != null && saupNo.length() == 10) {
            sb.append("\n \tAND  U1.사업자등록번호 = ? ");
        }
        sb.append("\n\tGROUP BY U1.갱신일자, U1.사업자등록번호,U2.업무구분,U2.승인지청             ");
        sb.append("\n   ORDER BY TO_CHAR(U1.갱신일자, 'YYYY.MM.DD'), U1.사업자등록번호\t\t\t\t");
        sb.append("\n )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n GROUP BY 승인지청\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sql = sb.toString();
        try {
            if (ID == null || ID.length() == 0 || saupNo == null || saupNo.length() != 10) {
                parameter = new String[] { startTime, endTime };
            }
            if (ID != null && ID.length() != 0 && (saupNo == null || saupNo.length() != 10)) {
                parameter = new String[] { startTime, endTime, ID };
            }
            if (saupNo != null && saupNo.length() == 10 && (ID == null || ID.length() == 0)) {
                parameter = new String[] { startTime, endTime, saupNo };
            }
            if ((ID != null || ID.length() != 0) && saupNo != null && saupNo.length() == 10) {
                parameter = new String[] { startTime, endTime, ID, saupNo };
            }
            final Runtime bRunTime = Runtime.getRuntime();
            final long endMemory = bRunTime.freeMemory();
            return new ComDbQuery().getOneRowList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getModifyCount() :startTime[" + startTime + "] , endTime[" + endTime + "] , 승인지청(ID)[" + ID + "]" + exm.toString());
            throw exm;
        }
    }
}
