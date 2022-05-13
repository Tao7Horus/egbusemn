// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.util.Vector;
import common.Log;
import common.ComDbQuery;
import common.CommEntity;
import java.sql.Connection;

public class UM_COV_Report03_Control
{
    public CommEntity[] getRequestList(final String startTime, final String endTime, final String ID, final String saupNo, final Connection conn) throws Exception {
        final Runtime aRunTime = Runtime.getRuntime();
        final long startMemory = aRunTime.freeMemory();
        final StringBuffer sb = new StringBuffer();
        String sql = "";
        String[] parameter = (String[])null;
        sb.append("\n   SELECT   ROWNUM N, a.상호명, 처리일자, a.사업자등록번호, 대표자명, b.전화번호, MAIL,\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n           DECODE(SUBSTR(a.업무구분, 1, 1), '1', '[물품]')||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   \t\t   DECODE(SUBSTR(a.업무구분, 2, 1), '1', '[공사]')||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   \t\t   DECODE(SUBSTR(a.업무구분, 3, 1), '1', '[용역]')||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   \t       DECODE(SUBSTR(a.업무구분, 4, 1), '1', '[일반용역]')||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   \t\t   DECODE(SUBSTR(a.업무구분, 5, 1), '1', '[외자]') 업무구분명\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   FROM 사용_조달업체마스터 a, 사용_전자문서상태 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   WHERE  a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   AND b.진행상태 = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   AND b.신청_변경구분 = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   AND b.승인지청 = '91'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   AND b.처리일자 BETWEEN TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss') AND TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss')\t\t\t\t\t");
        sb.append("\n   ORDER BY b.처리일자 ASC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
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
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getRequestList() :startTime[" + startTime + "] , endTime[" + endTime + "] , 승인지청(ID)[" + ID + "]" + exm.toString());
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
        sb.append("\n   SELECT   ROWNUM N, a.상호명, 처리일자, a.사업자등록번호, 대표자명, b.전화번호, MAIL,\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n           DECODE(SUBSTR(a.업무구분, 1, 1), '1', '[물품]')||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   \t\tDECODE(SUBSTR(a.업무구분, 2, 1), '1', '[공사]')||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   \t\tDECODE(SUBSTR(a.업무구분, 3, 1), '1', '[용역]')||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   \t    DECODE(SUBSTR(a.업무구분, 4, 1), '1', '[일반용역]')||\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   \t\tDECODE(SUBSTR(a.업무구분, 5, 1), '1', '[외자]') 업무구분명\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   FROM 사용_조달업체마스터 a, 사용_전자문서상태 b\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   WHERE  a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   AND b.진행상태 = '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   AND b.신청_변경구분 = '2'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   AND b.승인지청 = '91'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        sb.append("\n   AND b.처리일자 BETWEEN TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss') AND TO_DATE(?, 'yyyy-mm-dd hh24:mi:ss')\t\t\t\t\t");
        sb.append("\n   ORDER BY b.처리일자 ASC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
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
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getModifyList() :startTime[" + startTime + "] , endTime[" + endTime + "] , 승인지청(ID)[" + ID + "]" + exm.toString());
            throw exm;
        }
    }
}
