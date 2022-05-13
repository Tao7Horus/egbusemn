// 
// Decompiled by Procyon v0.5.30
// 

package beans;

import java.sql.Connection;
import common.ComStr;
import common.util.CommUtil;
import entity.UM_FOE_CommInfo2;
import javax.servlet.http.HttpServletRequest;

public class UM_FOB_Forn051
{
    public UM_FOE_CommInfo2[] getAcceptListB(final int n, final int n2, String s, String s2, final String s3, String s4, String s5, final String s6, final String s7, final String s8, final HttpServletRequest httpServletRequest) throws Exception {
        final CommUtil commUtil = new CommUtil();
        final String retSpace = CommUtil.retSpace(httpServletRequest.getParameter("saupNo"));
        final String retSpace2 = CommUtil.retSpace(httpServletRequest.getParameter("sangho"));
        CommUtil.retSpace(httpServletRequest.getParameter("mulpumCode"));
        String gve_busi = CommUtil.retSpace(httpServletRequest.getParameter("sdate"));
        String gve_busi2 = CommUtil.retSpace(httpServletRequest.getParameter("edate"));
        final String replace = commUtil.replace(retSpace, "-", "");
        if (gve_busi.length() > 0 && gve_busi2.length() > 0) {
            gve_busi = CommUtil.eraseBusiMark(gve_busi) + " 00:00:00";
            gve_busi2 = CommUtil.eraseBusiMark(gve_busi2) + " 23:59:59";
        }
        s = ((s == null) ? "" : s);
        s2 = ((s2 == null) ? "" : s2);
        s4 = ((s4 == null) ? "" : s4);
        s5 = ((s3 == null) ? "" : s5);
        String s9 = "SELECT 사업자등록번호,  상호명,  신청일자, 진행상태, 신청_변경구분,업체구분, N   \t\t\t\t\t\t\t\n  FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n      (                   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \t   SELECT  A.사업자등록번호,  A.상호명,  TO_CHAR(C.신청일자, 'YYYY-MM-DD HH24:MI:SS') 신청일자, C.진행상태, C.신청_변경구분, B.업체구분, ROWNUM N \t\n         FROM 사용_접수조달업체마스터 A,\t사용_접수업체구분 B,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n              사용_전자문서상태 C\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n        WHERE A.사업자등록번호\t=  C.사업자등록번호 \tAND A.사업자등록번호   = B.사업자등록번호 \t\t\t\t\t\t\t\t\t\t\t\t\t\n          AND C.진행상태       != '1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\t\t  AND A.입찰참가자격여부\t= 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        if (gve_busi.length() > 0 && gve_busi2.length() > 0) {
            s9 = s9 + "\t\t  AND A.등록일자 BETWEEN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\t\t      TO_DATE('" + gve_busi + "','YYYYMMDD HH24:MI:SS') AND TO_DATE('" + gve_busi2 + "','YYYYMMDD HH24:MI:SS')\t\t\n";
        }
        if (replace.length() > 0) {
            s9 = s9 + "  AND A.사업자등록번호 LIKE '" + ComStr.replace(replace, "-", "") + "%'\t\t\t\t\t\t\t\t\t\t\n";
        }
        if (retSpace2.length() > 0) {
            s9 = s9 + "  AND upper(A.상호명) LIKE  upper('%" + retSpace2 + "%')  \t\t\t\t\t\t\t\t\t\t\t\t        \n";
        }
        if (s.length() > 0 || s2.length() > 0 || s3.length() > 0) {
            final String string = s9 + " AND ( 진행상태 IN (";
            String s10;
            if (0 < s.length()) {
                s10 = string + "'" + s + "'";
            }
            else {
                s10 = string + "'7'";
            }
            String s11;
            if (0 < s2.length()) {
                s11 = s10 + ",'" + s2 + "'";
            }
            else {
                s11 = s10 + ",'7'";
            }
            String s12;
            if (0 < s3.length()) {
                s12 = s11 + ",'" + s3 + "'";
            }
            else {
                s12 = s11 + ",'7'";
            }
            s9 = s12 + "))\n";
        }
        if (s4.length() > 0 || s5.length() > 0) {
            final String string2 = s9 + " AND ( 신청_변경구분 IN (";
            String s13;
            if (0 < s4.length()) {
                s13 = string2 + s4;
            }
            else {
                s13 = string2 + "7";
            }
            String s14;
            if (0 < s5.length()) {
                s14 = s13 + "," + s5;
            }
            else {
                s14 = s13 + ",7";
            }
            s9 = s14 + "))\n";
        }
        if (s6.length() > 0 || s7.length() > 0) {
            final String string3 = s9 + " AND ( B.업체구분 IN (";
            String s15;
            if (0 < s6.length()) {
                s15 = string3 + s6;
            }
            else {
                s15 = string3 + "7";
            }
            String s16;
            if (0 < s7.length()) {
                s16 = s15 + "," + s7;
            }
            else {
                s16 = s15 + ",7";
            }
            s9 = s16 + "))";
        }
        if (!s8.equals("XX")) {
            s9 = s9 + "\t\t  AND C.승인지청\t\t= '" + s8 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        }
        return new UM_FOB_Forn061().getList(this, s9 + "     ORDER BY 신청일자 DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n   )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n WHERE N BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")\t\t\t\t\t\t\n", null);
    }
    
    public int getInfoCountB(String s, String s2, String s3, String s4, String s5, final String s6, final String s7, final String s8, final HttpServletRequest httpServletRequest) throws Exception {
        final CommUtil commUtil = new CommUtil();
        final String retSpace = CommUtil.retSpace(httpServletRequest.getParameter("saupNo"));
        final String retSpace2 = CommUtil.retSpace(httpServletRequest.getParameter("sangho"));
        CommUtil.retSpace(httpServletRequest.getParameter("mulpumCode"));
        String gve_busi = CommUtil.retSpace(httpServletRequest.getParameter("sdate"));
        String gve_busi2 = CommUtil.retSpace(httpServletRequest.getParameter("edate"));
        final String replace = commUtil.replace(retSpace, "-", "");
        if (gve_busi.length() > 0 && gve_busi2.length() > 0) {
            gve_busi = CommUtil.eraseBusiMark(gve_busi) + " 00:00:00";
            gve_busi2 = CommUtil.eraseBusiMark(gve_busi2) + " 23:59:59";
        }
        s = ((s == null) ? "" : s);
        s2 = ((s2 == null) ? "" : s2);
        s3 = ((s3 == null) ? "" : s3);
        s4 = ((s4 == null) ? "" : s4);
        s5 = ((s3 == null) ? "" : s5);
        String s9 = "SELECT COUNT(*)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n   FROM 사용_접수조달업체마스터 A,사용_접수업체구분 B,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n        사용_전자문서상태 C\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  WHERE A.사업자등록번호 = C.사업자등록번호 AND A.사업자등록번호   = B.사업자등록번호 \t\t\t\t\t\t\n    AND C.진행상태 !='1'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\t\t  AND A.입찰참가자격여부\t= 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        if (gve_busi.length() > 0 && gve_busi2.length() > 0) {
            s9 = s9 + "\tAND A.등록일자 BETWEEN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\t\tTO_DATE('" + gve_busi + "','YYYYMMDD HH24:MI:SS') AND TO_DATE('" + gve_busi2 + "','YYYYMMDD HH24:MI:SS')\t\t\t\n";
        }
        if (replace.length() > 0) {
            s9 = s9 + "  AND A.사업자등록번호 LIKE '" + ComStr.replace(replace, "-", "") + "%'\t\t\t\t\t\t\t\t\t\t\n";
        }
        if (retSpace2.length() > 0) {
            s9 = s9 + "  AND upper(A.상호명) LIKE upper('%" + retSpace2 + "%')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        }
        if (s.length() > 0 || s2.length() > 0 || s3.length() > 0) {
            final String string = s9 + " AND ( 진행상태 IN (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
            String s10;
            if (0 < s.length()) {
                s10 = string + "'" + s + "'";
            }
            else {
                s10 = string + "'7'";
            }
            String s11;
            if (0 < s2.length()) {
                s11 = s10 + ",'" + s2 + "'";
            }
            else {
                s11 = s10 + ",'7'";
            }
            String s12;
            if (0 < s3.length()) {
                s12 = s11 + ",'" + s3 + "'";
            }
            else {
                s12 = s11 + ",'7'";
            }
            s9 = s12 + "))\n";
        }
        if (s4.length() > 0 || s5.length() > 0) {
            final String string2 = s9 + " AND ( 신청_변경구분 IN (";
            String s13;
            if (0 < s4.length()) {
                s13 = string2 + s4;
            }
            else {
                s13 = string2 + "7";
            }
            String s14;
            if (0 < s5.length()) {
                s14 = s13 + "," + s5;
            }
            else {
                s14 = s13 + ",7";
            }
            s9 = s14 + "))\n";
        }
        if (s6.length() > 0 || s7.length() > 0) {
            final String string3 = s9 + " AND ( B.업체구분 IN (";
            String s15;
            if (0 < s6.length()) {
                s15 = string3 + s6;
            }
            else {
                s15 = string3 + "7";
            }
            String s16;
            if (0 < s7.length()) {
                s16 = s15 + "," + s7;
            }
            else {
                s16 = s15 + ",7";
            }
            s9 = s16 + "))";
        }
        if (!s8.equals("XX")) {
            s9 = s9 + "\t\t  AND C.승인지청\t\t= '" + s8 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        }
        return new UM_FOB_Forn061().getCount(this, s9, null);
    }
    
    public UM_FOE_CommInfo2[] getAcceptList(final String s, final String s2, final int n, final int n2, final HttpServletRequest httpServletRequest) throws Exception {
        final CommUtil commUtil = new CommUtil();
        final String retSpace = CommUtil.retSpace(httpServletRequest.getParameter("saupNo"));
        final String retSpace2 = CommUtil.retSpace(httpServletRequest.getParameter("sangho"));
        String gve_busi = CommUtil.retSpace(httpServletRequest.getParameter("sdate"));
        String gve_busi2 = CommUtil.retSpace(httpServletRequest.getParameter("edate"));
        final String replace = commUtil.replace(retSpace, "-", "");
        if (gve_busi.length() > 0 && gve_busi2.length() > 0) {
            gve_busi = CommUtil.eraseBusiMark(gve_busi) + " 00:00:00";
            gve_busi2 = CommUtil.eraseBusiMark(gve_busi2) + " 23:59:59";
        }
        String s3 = "SELECT 사업자등록번호,  상호명,  등록일자, 업체구분, N   \t\t\t\t\t\t\t\t\t\t\t\t\n  FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  (                   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \tSELECT 사업자등록번호,  상호명,  등록일자,  업체구분,\t ROWNUM N \t\t\t\t\t\t\t\t\t\t\n  FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  (                   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n SELECT   A.사업자등록번호,  A.상호명,\tMAX(TO_CHAR(A.등록일자, 'YYYY-MM-DD')) 등록일자\t, B.업체구분\t\t\n   FROM 사용_조달업체마스터\tA,사용_업체구분 B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n    WHERE A.사업자등록번호   = B.사업자등록번호 \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        if (gve_busi.length() > 0 && gve_busi2.length() > 0) {
            s3 = s3 + "\tAND A.등록일자 BETWEEN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\t\tTO_DATE('" + gve_busi + "','YYYYMMDD HH24:MI:SS') AND TO_DATE('" + gve_busi2 + "','YYYYMMDD HH24:MI:SS')\t\t\t\n";
        }
        if (replace.length() > 0) {
            s3 = s3 + "  AND A.사업자등록번호 LIKE '" + ComStr.replace(replace, "-", "") + "%'\t\t\t\t\t\t\t\t\t\t\n";
        }
        if (retSpace2.length() > 0) {
            s3 = s3 + "  AND upper(A.상호명) LIKE upper('%" + retSpace2 + "%')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        }
        if (s.length() > 0 || s2.length() > 0) {
            final String string = s3 + " AND ( B.업체구분 IN (";
            String s4;
            if (0 < s.length()) {
                s4 = string + s;
            }
            else {
                s4 = string + "7";
            }
            String s5;
            if (0 < s2.length()) {
                s5 = s4 + "," + s2;
            }
            else {
                s5 = s4 + ",7";
            }
            s3 = s5 + "))";
        }
        return new UM_FOB_Forn061().getList(this, s3 + "    GROUP BY A.사업자등록번호,  A.상호명\t, B.업체구분\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n     ORDER BY 등록일자 DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n   \t)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  \t  WHERE ROWNUM <= (" + n + " * " + n2 + ")\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" + "   )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" + " WHERE N BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")\t\t\t\t\t\t\n", null);
    }
    
    public int getInfoCount(final String s, final String s2, final HttpServletRequest httpServletRequest) throws Exception {
        final CommUtil commUtil = new CommUtil();
        final String retSpace = CommUtil.retSpace(httpServletRequest.getParameter("saupNo"));
        final String retSpace2 = CommUtil.retSpace(httpServletRequest.getParameter("sangho"));
        String gve_busi = CommUtil.retSpace(httpServletRequest.getParameter("sdate"));
        String gve_busi2 = CommUtil.retSpace(httpServletRequest.getParameter("edate"));
        final String replace = commUtil.replace(retSpace, "-", "");
        if (gve_busi.length() > 0 && gve_busi2.length() > 0) {
            gve_busi = CommUtil.eraseBusiMark(gve_busi) + " 00:00:00";
            gve_busi2 = CommUtil.eraseBusiMark(gve_busi2) + " 23:59:59";
        }
        String s3 = "SELECT COUNT(*)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  FROM\t 사용_조달업체마스터 \tA,사용_업체구분 B\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n   WHERE A.사업자등록번호   = B.사업자등록번호 \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        if (gve_busi.length() > 0 && gve_busi2.length() > 0) {
            s3 = s3 + "\tAND A.등록일자 BETWEEN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\t\tTO_DATE('" + gve_busi + "','YYYYMMDD HH24:MI:SS') AND TO_DATE('" + gve_busi2 + "','YYYYMMDD HH24:MI:SS')\t\t\t\t\t\t\t\t\t\n";
        }
        if (replace.length() > 0) {
            s3 = s3 + "  AND A.사업자등록번호 LIKE '" + ComStr.replace(replace, "-", "") + "%'\t\t\t\t\t\t\t\t\t\t\n";
        }
        if (retSpace2.length() > 0) {
            s3 = s3 + "  AND upper(A.상호명) LIKE upper('%" + retSpace2 + "%')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        }
        if (s.length() > 0 || s2.length() > 0) {
            final String string = s3 + " AND ( B.업체구분 IN (";
            String s4;
            if (0 < s.length()) {
                s4 = string + s;
            }
            else {
                s4 = string + "7";
            }
            String s5;
            if (0 < s2.length()) {
                s5 = s4 + "," + s2;
            }
            else {
                s5 = s4 + ",7";
            }
            s3 = s5 + "))";
        }
        return new UM_FOB_Forn061().getCount(this, s3, null);
    }
    
    public UM_FOE_CommInfo2[] getAcceptListE(final int n, final int n2, final HttpServletRequest httpServletRequest) throws Exception {
        final CommUtil commUtil = new CommUtil();
        final String retSpace = CommUtil.retSpace(httpServletRequest.getParameter("saupNo"));
        final String retSpace2 = CommUtil.retSpace(httpServletRequest.getParameter("sangho"));
        final String retSpace3 = CommUtil.retSpace(httpServletRequest.getParameter("mulpumCode"));
        final String retSpace4 = CommUtil.retSpace(httpServletRequest.getParameter("sdate"));
        final String retSpace5 = CommUtil.retSpace(httpServletRequest.getParameter("edate"));
        final String replace = commUtil.replace(retSpace, "-", "");
        final String string = CommUtil.eraseBusiMark(retSpace4) + " 00:00:00";
        final String string2 = CommUtil.eraseBusiMark(retSpace5) + " 23:59:59";
        String s = "SELECT 사업자등록번호,  상호명,  입력일자, 주소 ||' '|| 나머지주소 주소, N, 상태구분\t\t\t\t\t\t\n  FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  (                   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \tSELECT 사업자등록번호,  상호명,  입력일자, 주소, 나머지주소, ROWNUM N, 상태구분\t\t\t\t\t\t\t\n      FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n          (                   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n SELECT A.사업자등록번호,  A.상호명,\tA.입력일자, 주소, 나머지주소, 상태구분\t\t\t\t\t\t\t\t\n   FROM 사용_조달업체마스터_이력 A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  WHERE A.상태구분 = 'I'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        if (string.length() > 0 && string2.length() > 0) {
            s = s + "\t AND A.입력일자 BETWEEN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\t\tTO_DATE('" + string + "','YYYYMMDD HH24:MI:SS') AND TO_DATE('" + string2 + "','YYYYMMDD HH24:MI:SS')\t\t\t\t\t\t\t\t\t\n";
        }
        if (retSpace3.length() > 0) {
            s = s + "\t AND 대표물품분류번호='" + retSpace3 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        }
        if (replace.length() > 0) {
            s = s + " AND  A.사업자등록번호 LIKE '" + ComStr.replace(replace, "-", "") + "%'\t\t\t\t\t\t\t\t\t\t\n";
        }
        if (retSpace2.length() > 0) {
            s = s + "  AND upper(A.상호명) LIKE upper('%" + retSpace2 + "%')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        }
        final String string3 = s + "     ORDER BY 입력일자 DESC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n   \t)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  \t  WHERE ROWNUM <= (" + n + " * " + n2 + ")\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" + "   )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" + " WHERE N BETWEEN (((" + n + " - 1) * " + n2 + ")+1) AND (" + n + " * " + n2 + ")\t\t\t\t\t\t\n";
        System.out.println("sql" + string3);
        return new UM_FOB_Forn061().getList(this, string3, null);
    }
    
    public int getInfoCountE(final HttpServletRequest httpServletRequest) throws Exception {
        final CommUtil commUtil = new CommUtil();
        final String retSpace = CommUtil.retSpace(httpServletRequest.getParameter("saupNo"));
        final String retSpace2 = CommUtil.retSpace(httpServletRequest.getParameter("sangho"));
        final String retSpace3 = CommUtil.retSpace(httpServletRequest.getParameter("mulpumCode"));
        final String retSpace4 = CommUtil.retSpace(httpServletRequest.getParameter("sdate"));
        final String retSpace5 = CommUtil.retSpace(httpServletRequest.getParameter("edate"));
        final String replace = commUtil.replace(retSpace, "-", "");
        final String string = CommUtil.eraseBusiMark(retSpace4) + " 00:00:00";
        final String string2 = CommUtil.eraseBusiMark(retSpace5) + " 23:59:59";
        String s = "SELECT COUNT(*)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  (                   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n \tSELECT 사업자등록번호,  상호명,  입력일자, ROWNUM N \t\t\t\t\t\t\t\t\t\t\t\t\t\n  FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  (                   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n SELECT   A.사업자등록번호,  A.상호명,\tA.입력일자\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n   FROM 사용_조달업체마스터_이력 A\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n  WHERE A.사업자등록번호 like 'F%'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n    AND A.상태구분 = 'I'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        if (string.length() > 0 && string2.length() > 0) {
            s = s + "\t AND A.입력일자 BETWEEN\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n\t\tTO_DATE('" + string + "','YYYYMMDD HH24:MI:SS') AND TO_DATE('" + string2 + "','YYYYMMDD HH24:MI:SS')\t\t\t\n";
        }
        if (retSpace3.length() > 0) {
            s = s + "\t AND 대표물품분류번호='" + retSpace3 + "'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        }
        if (replace.length() > 0) {
            s = s + "  AND A.사업자등록번호 LIKE '" + ComStr.replace(replace, "-", "") + "%'\t\t\t\t\t\t\t\t\t\t\n";
        }
        if (retSpace2.length() > 0) {
            s = s + "   AND upper(A.상호명) LIKE upper('%" + retSpace2 + "%')\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        }
        final String string3 = s + "   )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
        System.out.println("sql" + string3);
        return new UM_FOB_Forn061().getCount(this, string3, null);
    }
}
