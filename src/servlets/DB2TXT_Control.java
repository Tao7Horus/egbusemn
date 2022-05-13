// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import common.CommEntity;
import java.util.Hashtable;
import common.Log;
import common.ComDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class DB2TXT_Control
{
    public OneRowEntity getReceiveMaster(final String transNo, final Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection 은 null 일수 없습니다.");
        }
        final String sql = " select u1.상호명 , u1.우편번호,\tu1.주소 ||' '|| u1.나머지주소 주소       , ' ' 담당자명, u7.전화번호, u7.FAX번호        , decode (u1.신청_변경구분 , 1, '경쟁입찰 참가자격 등록신청 증빙서류 제출'   \t                 \t\t\t , 2, '경쟁입찰 참가자격 변경신청 증빙서류 제출'   \t\t\t\t\t , '') 제목, u1.문서번호       , u6.코드명 승인지청 \t   , to_char(u1.신청일자,'YYYY.MM.DD') 신청일자, u1.사업자등록번호, u1.대표자명,u7.기업구분2    FROM SYN_공동코드 u6       , 사용_전자문서상태 u1       , 사용_접수조달업체마스터 u7  where u1.전송번호 = ? and U6.코드구분 = 'GUJ' and u1.승인지청 = U6.코드 and u1.사업자등록번호=u7.사업자등록번호(+)";
        try {
            final String[] parameter = { transNo };
            return new ComDbQuery().getOneRowList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getReceiveMaster() :transNo[" + transNo + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public Hashtable getCheckItem(final String saupjaNumber, final String transNo, final Connection conn) throws Exception {
        final Hashtable resultHash = new Hashtable();
        resultHash.put("makeitem", this.isMakeItemExist(saupjaNumber, conn));
        resultHash.put("lawNo", this.islawNoExist(saupjaNumber, conn));
        resultHash.put("adminAgree", this.adminAgreeExist(transNo, conn));
        resultHash.put("approveNumber", this.isApproveNumberExist(saupjaNumber, conn));
        this.setLicenseCount(saupjaNumber, resultHash, conn);
        resultHash.put("bidvice", this.isBIDViceExist(saupjaNumber, conn));
        resultHash.put("brinfo", this.isApproveBRExist(saupjaNumber, conn));
        return resultHash;
    }
    
    private String isMakeItemExist(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = " select count(*) from 사용_접수조달품목 where 사업자등록번호=? and 제조여부='Y'";
        final String[] parameter = { saupjaNumber };
        try {
            return (new ComDbQuery().getCount(this, "usemn", sql, parameter, conn) > 0) ? "Y" : "N";
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .isMakeItemExist() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private String islawNoExist(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = " SELECT  COUNT(*)\t\t\t\t\t\t\t\t\t   FROM 사용_접수조달업체마스터\t\t\t\t\t   WHERE  사업자등록번호 = ?\t\t\t\t\t\t   AND LENGTHB(법인등록번호) != 13\t\t    ";
        final String[] parameter = { saupjaNumber };
        try {
            return (new ComDbQuery().getCount(this, "usemn", sql, parameter, conn) > 0) ? "Y" : "N";
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .islawNoExist() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private String adminAgreeExist(final String transNo, final Connection conn) throws Exception {
        final String sql = "  SELECT  COUNT(*)\t\t\t\t\t\t\t\t\t    FROM 사용_전자문서상태\t\t\t\t\t\t\t    WHERE 전송번호 = ?\t\t\t\t\t\t\t\t    AND 행정정보이용동의여부 = 'N'\t\t\t\t";
        final String[] parameter = { transNo };
        try {
            return (new ComDbQuery().getCount(this, "usemn", sql, parameter, conn) > 0) ? "Y" : "N";
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .adminAgreeExist() :transNo[" + transNo + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private String isApproveBRExist(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = "  SELECT count(*)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM  사용_조달업체마스터\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE 법인등록번호 IN (  SELECT 법인등록번호\t\t\t\t\t\t                                        FROM 사용_조달업체마스터\t\t\t\t                                        WHERE 사업자등록번호  = ?)\t\t\t  AND 본사구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        final String[] parameter = { saupjaNumber };
        try {
            return (new ComDbQuery().getCount(this, "usemn", sql, parameter, conn) > 0) ? "Y" : "N";
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .isApproveBRExist() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private String isApproveNumberExist(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = " select count(*) from 사용_접수조달품목 where 사업자등록번호=? and 형식승인번호 is not null";
        final String[] parameter = { saupjaNumber };
        try {
            return (new ComDbQuery().getCount(this, "usemn", sql, parameter, conn) > 0) ? "Y" : "N";
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .isApproveNumberExist() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private void setLicenseCount(final String saupjaNumber, final Hashtable resultHash, final Connection conn) throws Exception {
        final String sql = " select A.cnt 전체, B.cnt 용역면허 from  (select count(*) cnt  from 사용_접수면허사항 where 사업자등록번호=? ) A,  (select count(*) cnt  from 사용_접수면허사항 a, syn_공동코드 b where B.코드구분 = 'GU9' and a.면허코드 = b.코드 and b.속성코드 = '99' and a.사업자등록번호=?) B";
        final String[] parameter = { saupjaNumber, saupjaNumber };
        try {
            final CommEntity[] result = new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
            resultHash.put("totalLicense", result[0].data[0]);
            resultHash.put("bidsseLicense", result[0].data[1]);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .setLicenseCount() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private String isBIDViceExist(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = " select count(*) from 사용_접수입찰대리인 where 사업자등록번호=?";
        final String[] parameter = { saupjaNumber };
        try {
            return (new ComDbQuery().getCount(this, "usemn", sql, parameter, conn) > 0) ? "Y" : "N";
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .isBIDViceExist() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
}
