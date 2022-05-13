// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import common.CommEntity;
import common.ComDbQuery;
import java.util.Hashtable;
import common.Log;
import common.CommDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class DB2TXT1_Rex_Control
{
    public OneRowEntity getReceiveMaster(final String saupNo, final Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection 은 null 일수 없습니다.");
        }
        final String sql = " select distinct aa.COM_NM , aa.ZIP_CD,  aa.ADDR,aa.REG_DT, aa.BIZ_REG_NO,  aa.REPR_NM,aa.PHONE_NO, aa.PERMIT_BRANCH, bb.CD_NM CD_NM, ('Gửi tài liệu thay đổi tư cách đấu thầu cạnh tranh') Title, aa.DOC_NO from (select a.COM_NM , a.ZIP_CD,  a.ADDR ||' '|| a.DETAIL_ADDR ADDR, to_char(a.REG_DT,'YYYY.MM.DD') REG_DT, a.BIZ_REG_NO, a.REPR_NM ,a.PHONE_NO, a.PERMIT_BRANCH, a.DOC_NO from UM_EDOC_STATE a where a.BIZ_REG_NO=  '" + saupNo + "'" + " and a.MOD_CLS='2'" + " and a.PROCESS_ST IN (0,2)" + " and rownum=1 ) aa, SYN_PUB_CODE bb  " + " and bb.CD=aa.PERMIT_BRANCH";
        try {
            System.out.print(sql);
            return new CommDbQuery().getOneRowList(this, sql.toString(), conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getReceiveMaster() :saupNo[" + saupNo + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public Hashtable getCheckItem(final String saupjaNumber, final String transNo, final Connection conn) throws Exception {
        final Hashtable resultHash = new Hashtable();
        resultHash.put("IngamExist", this.isIngamDataExist(saupjaNumber, conn));
        resultHash.put("lawNo", this.islawNoExist(saupjaNumber, conn));
        resultHash.put("adminAgree", this.adminAgreeExist(transNo, conn));
        resultHash.put("masterRealEntity", this.getRealMasterInfo(saupjaNumber, conn));
        return resultHash;
    }
    
    private String isIngamDataExist(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = " select count(*) from UM_SEAL_MAST where BIZ_REG_NO=? and USE_YN='Y'";
        final String[] parameter = { saupjaNumber };
        try {
            return (new ComDbQuery().getCount(this, "usemn", sql, parameter, conn) > 0) ? "Y" : "N";
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .isIngamDataExist() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private OneRowEntity getRealMasterInfo(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = " select COM_NM from UM_SUPPLIER_ENTER_MAST where BIZ_REG_NO=?";
        final String[] parameter = { saupjaNumber };
        try {
            return new ComDbQuery().getOneRowList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getRealMasterInfo() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private String islawNoExist(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = " SELECT COUNT(*) cnt\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  FROM  (\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t\t      ( SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t\t        FROM UM_REC_SUPPLIER_ENTER_MAST  WHERE BIZ_REG_NO=? )\t\t  \t            MINUS\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t          ( SELECT CORP_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  \t  \t        FROM UM_REC_SUPPLIER_ENTER_MAST WHERE  BIZ_REG_NO= ? )\t\t\t\t  \t\t   )\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  WHERE LENGTHB(CORP_REG_NO) != 13\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        final String[] parameter = { saupjaNumber, saupjaNumber };
        try {
            return (new ComDbQuery().getCount(this, "usemn", sql, parameter, conn) > 0) ? "Y" : "N";
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .islawNoExist() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private String adminAgreeExist(final String transNo, final Connection conn) throws Exception {
        final String sql = "  SELECT  COUNT(*)\t\t\t\t\t\t\t\t\t    FROM UM_EDOC_STATE\t\t\t\t\t\t\t    WHERE TRANS_NO = ?\t\t\t\t\t\t\t\t    AND CONSENT_USE_YN = 'N'\t\t\t\t";
        final String[] parameter = { transNo };
        try {
            return (new ComDbQuery().getCount(this, "usemn", sql, parameter, conn) > 0) ? "Y" : "N";
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .adminAgreeExist() :transNo[" + transNo + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private void setCompareBranchInfo(final String saupjaNumber, final Hashtable resultHash, final Connection conn) throws Exception {
        final String sql = "  SELECT (b.cnt-a.cnt) 차이, c.cnt 구별값\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   FROM\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   (SELECT COUNT(*) cnt   FROM  사용_접수조달업체마스터 a, 사용_접수대표자 b, 사용_업체상태 c\t\t\t\t\t\t\t    WHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    AND a.BIZ_REG_NO = c.BIZ_REG_NO(+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    AND c.상태구분(+) = '07' AND b.대표대표자여부 = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    AND a.법인등록번호 IN (  SELECT 법인등록번호\t FROM 사용_접수조달업체마스터 WHERE BIZ_REG_NO  =?)\t\t    AND 본사구분 = 'N') b,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   (SELECT COUNT(*) cnt FROM  사용_조달업체마스터 a, 사용_대표자 b, 사용_업체상태 c\t\t\t\t\t\t\t\t\t\t    WHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    AND a.BIZ_REG_NO = c.BIZ_REG_NO(+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    AND c.상태구분(+) = '07' AND b.대표대표자여부 = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    AND a.법인등록번호 IN (  SELECT 법인등록번호 FROM 사용_조달업체마스터 WHERE BIZ_REG_NO  =?)\t\t\t\t    AND 본사구분 = 'N') a,   \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   (SELECT COUNT(*) cnt FROM(\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    SELECT a.BIZ_REG_NO, COM_NM, ZIP_CD, 지역코드, ADDR, DETAIL_ADDR, PHONE_NO, FAX번호,\t\t\t\t\t\t\t\t    \t\t    REPR_NM, 대표자주민번호, DECODE(c.상태구분,'07','Y') 상태구분\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      FROM  사용_접수조달업체마스터 a, 사용_접수대표자 b, 사용_업체상태 c\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      WHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      AND a.BIZ_REG_NO = c.BIZ_REG_NO(+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      AND c.상태구분(+) = '07'\tAND b.대표대표자여부 = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      AND a.법인등록번호 IN (  SELECT 법인등록번호 FROM 사용_접수조달업체마스터 WHERE BIZ_REG_NO  =?)\t\t      AND 본사구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    MINUS\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    SELECT a.BIZ_REG_NO, COM_NM, ZIP_CD, 지역코드, ADDR, DETAIL_ADDR, PHONE_NO, FAX번호,\t\t\t\t\t\t\t\t    \t\t    REPR_NM, 대표자주민번호, DECODE(c.상태구분,'07','Y') 상태구분\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      FROM  사용_조달업체마스터 a, 사용_대표자 b, 사용_업체상태 c\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      WHERE a.BIZ_REG_NO = b.BIZ_REG_NO\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      AND a.BIZ_REG_NO = c.BIZ_REG_NO(+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      AND c.상태구분(+) = '07'\tAND b.대표대표자여부 = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      AND a.법인등록번호 IN (  SELECT 법인등록번호 FROM 사용_조달업체마스터 WHERE BIZ_REG_NO  =?)\t\t\t      AND 본사구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   )) c\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        final String[] parameter = { saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber };
        try {
            final CommEntity[] listEntity = new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
            resultHash.put("branchTotalCntCompare", listEntity[0].data[0]);
            resultHash.put("branchMinusCntCompare", listEntity[0].data[1]);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .setCompareBranchInfo() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private void setCompareFactoryInfo(final String saupjaNumber, final Hashtable resultHash, final Connection conn) throws Exception {
        final String sql = " select (b.cnt-a.cnt) 차이, c.cnt 구별값   from  (select count(*) cnt from UM_REC_FACTORY_INFO where  BIZ_REG_NO= ? AND FACTORY_RENT_YN = 'Y' ) b,  (select count(*) cnt from UM_FACTORY_INFO where  BIZ_REG_NO= ? AND FACTORY_RENT_YN = 'Y')  a,  (select count(*) cnt from  (  (select BIZ_REG_NO, FACTORY_NM, ADDR, ADDR2, FACTORY_RENT_YN, FACTORY_RENT_START_DT, FACTORY_RENT_END_DT  from UM_REC_FACTORY_INFO where BIZ_REG_NO=? AND FACTORY_RENT_YN = 'Y')  minus  (select BIZ_REG_NO, FACTORY_NM, ADDR, ADDR2, FACTORY_RENT_YN, FACTORY_RENT_START_DT, FACTORY_RENT_END_DT   from UM_FACTORY_INFO where  BIZ_REG_NO= ?  AND FACTORY_RENT_YN = 'Y') )) c";
        final String[] parameter = { saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber };
        try {
            final CommEntity[] listEntity = new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
            resultHash.put("factoryTotalCntCompare", listEntity[0].data[0]);
            resultHash.put("factoryMinusCntCompare", listEntity[0].data[1]);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .setCompareFactoryInfo() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private void setCompareItemInfo(final String saupjaNumber, final Hashtable resultHash, final Connection conn) throws Exception {
        final String sql = " select (b.cnt-a.cnt) 차이, c.cnt 구별값 from (select count(*) cnt from 사용_접수조달품목  where  제조여부='Y'  and BIZ_REG_NO= ?  ) b, (select count(*) cnt from 사용_조달품목    where  제조여부='Y'  and  BIZ_REG_NO= ? ) a, (select count(*) cnt from  ( (select BIZ_REG_NO, 물품분류번호, 직접생산증명서류, 유효기간시작일자, 유효기간종료일자, 표준산업분류코드,발급기관,증서명  from 사용_접수조달품목 where 제조여부='Y' and BIZ_REG_NO=?)  minus (select BIZ_REG_NO, 물품분류번호, 직접생산증명서류, 유효기간시작일자, 유효기간종료일자, 표준산업분류코드,발급기관,증서명  from 사용_조달품목  where 제조여부='Y' and  BIZ_REG_NO= ?)  )) c";
        final String[] parameter = { saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber };
        try {
            final CommEntity[] listEntity = new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
            resultHash.put("itemTotalCntCompare", listEntity[0].data[0]);
            resultHash.put("itemMinusCntCompare", listEntity[0].data[1]);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .setCompareItemInfo() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private void setCompareItemAddInfo(final String saupjaNumber, final Hashtable resultHash, final Connection conn) throws Exception {
        final String sql = " select A.cnt 형식승인번호, B.cnt 형식승인일, C.cnt 형식승인기관, D.cnt 마이너스 from  (select (b.bcn-a.acn) cnt from (select count(형식승인번호) bcn from 사용_접수조달품목 where BIZ_REG_NO = ?) b, (select count(형식승인번호) acn from 사용_조달품목 where BIZ_REG_NO= ?) a ) A, (select (b.bcn-a.acn) cnt from (select count(형식승인일) bcn from 사용_접수조달품목 where BIZ_REG_NO = ?) b, (select count(형식승인일) acn from 사용_조달품목 where BIZ_REG_NO = ?) a ) B, (select (b.bcn-a.acn) cnt from (select count(형식승인기관) bcn from 사용_접수조달품목 where BIZ_REG_NO = ?) b, (select count(형식승인기관) acn from 사용_조달품목 where BIZ_REG_NO = ?) a ) C, (select count(*) cnt from ( (select BIZ_REG_NO, 형식승인번호, 형식승인일, 형식승인기관 from 사용_접수조달품목 where BIZ_REG_NO = ?  and (형식승인번호 is not null or 형식승인일 is not null or 형식승인기관 is not null) )  minus (select BIZ_REG_NO, 형식승인번호, 형식승인일, 형식승인기관 from 사용_조달품목 where BIZ_REG_NO = ?)) ) D";
        final String[] parameter = { saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber };
        try {
            final CommEntity[] listEntity = new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
            resultHash.put("itemCompareNumber", listEntity[0].data[0]);
            resultHash.put("itemCompareDate", listEntity[0].data[1]);
            resultHash.put("itemCompareGovernment", listEntity[0].data[2]);
            resultHash.put("itemMinusAddCntCompare", listEntity[0].data[3]);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .setCompareItemAddInfo() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private void setCntVariable(final String saupjaNumber, final Hashtable resultHash, final Connection conn) throws Exception {
        final String sql = " select A.cnt, B.cnt, C.cnt, D.cnt, E.cnt, F.cnt, G.cnt, H.cnt, I.cnt from (select count(*) cnt from 사용_접수면허사항 where BIZ_REG_NO=?) A, (select count(*) cnt from 사용_면허사항 where BIZ_REG_NO=?) B, (select count(*) cnt from 사용_접수면허사항 a, syn_공동코드 b where b.코드구분 = 'GU9' AND a.면허코드 = b.코드 AND b.속성코드 = '99' AND a.BIZ_REG_NO = ?) C, (select count(*) cnt from  ( (select BIZ_REG_NO, 면허코드, 면허번호, to_char(면허취득일자,'YYYY-MM-DD') 면허취득일자 , 시공능력평가액, 평가액기준년도  from 사용_접수면허사항 where BIZ_REG_NO=?)  minus (select BIZ_REG_NO, 면허코드, 면허번호, to_char(면허취득일자,'YYYY-MM-DD') 면허취득일자 , 시공능력평가액, 평가액기준년도  from 사용_면허사항 where   BIZ_REG_NO= ? ) ) ) D, (select count(*) cnt from 사용_면허만료일자 WHERE 면허만료일자사용여부 = 'Y') E, (select count(*) cnt from (select a.면허코드, to_char(a.면허만료일자, 'yyyy-mm-dd') from 사용_접수면허사항 a  where a.BIZ_REG_NO = ?  minus  select 면허코드, to_char(면허만료일자, 'yyyy-mm-dd') from 사용_면허사항  where BIZ_REG_NO = ?) ) F, (SELECT (b.bcn-a.acn) cnt FROM (SELECT count(*) bcn FROM 사용_접수면허사항 A, syn_공동코드 B WHERE A.BIZ_REG_NO = ? AND B.코드구분 = 'GU9' AND A.면허코드 = B.코드 AND B.속성코드 = '99') b, (SELECT count(*) acn FROM 사용_면허사항 A, syn_공동코드 B WHERE A.BIZ_REG_NO = ? AND B.코드구분 = 'GU9' AND A.면허코드 = B.코드 AND B.속성코드 = '99') a ) G, (SELECT count(*) cnt FROM ( (SELECT B.코드명 FROM 사용_접수면허사항 A, syn_공동코드 B WHERE A.BIZ_REG_NO = ? AND B.코드구분 = 'GU9' AND A.면허코드 = B.코드 AND B.속성코드 ='99') minus (SELECT B.코드명 FROM 사용_면허사항 A, syn_공동코드 B WHERE A.BIZ_REG_NO = ? AND B.코드구분 = 'GU9' AND A.면허코드 = B.코드 AND B.속성코드 = '99')) ) H, (SELECT COUNT(*) cnt FROM  ( (SELECT BIZ_REG_NO, 주민등록번호, 성명, 입찰대리인확인여부   FROM 사용_접수입찰대리인 WHERE BIZ_REG_NO=?)   MINUS (SELECT BIZ_REG_NO, 주민등록번호, 성명, 입찰대리인확인여부   FROM 사용_입찰대리인 WHERE   BIZ_REG_NO= ? ) ) ) I";
        final String[] parameter = { saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber, saupjaNumber };
        try {
            final CommEntity[] listEntity = new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
            resultHash.put("receiveLicenseCnt", listEntity[0].data[0]);
            resultHash.put("licenseCnt", listEntity[0].data[1]);
            resultHash.put("receiveYongLicense", listEntity[0].data[2]);
            resultHash.put("licenseMinusreceiveLicenseCnt", listEntity[0].data[3]);
            resultHash.put("licenseDueCnt", listEntity[0].data[4]);
            resultHash.put("licenseDutMinusCnt", listEntity[0].data[5]);
            resultHash.put("licenseYongCnt", listEntity[0].data[6]);
            resultHash.put("licenseYongMinusCnt", listEntity[0].data[7]);
            resultHash.put("proxyMinusCnt", listEntity[0].data[8]);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .setCntVariable() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
}
