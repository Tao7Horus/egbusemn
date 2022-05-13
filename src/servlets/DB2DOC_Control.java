// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

import java.util.Vector;
import common.CommEntity;
import java.util.Hashtable;
import common.Log;
import common.ComDbQuery;
import common.OneRowEntity;
import java.sql.Connection;

public class DB2DOC_Control
{
    public OneRowEntity getReceiveMaster(final String saupjaNumber, final Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection 은 null 일수 없습니다.");
        }
        final String sql = " SELECT a.업무구분,        a.상호명 || DECODE (a.영문상호명, null, '', ' ('|| 영문상호명 ||')') 상호명,        a.사업자등록번호,        to_char(a.개업일자,'yyyy. mm. dd') 개업일자,        a.주소 || ' ' || a.나머지주소 주소,        a.전화번호,        a.FAX번호,        a.법인등록번호,       to_char(a.법인설립일자, 'yyyy. mm. dd') 법인등록일,        to_char(a.등록일자, 'yyyy. mm. dd') 등록일자,        to_char(a.갱신일자, 'yyyy. mm. dd') 갱신일자,        to_char(sysdate, 'yyyy. mm. dd') 출력일자,        a.승인지청, substr(a.업무구분, 5,1) 외자구분, nvl(a.입찰참가자격여부, 'Y') 입찰참가자격여부, \t\t (select b.코드명 from syn_공동코드 b where b.코드구분='GUJ' and b.코드=a.승인지청) 발행기관명 FROM 사용_조달업체마스터 a  WHERE 사업자등록번호 = ? ";
        try {
            final String[] parameter = { saupjaNumber };
            return new ComDbQuery().getOneRowList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getReceiveMaster() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public Hashtable getCheckItem(final String saupjaNumber, final Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection 은 null 일수 없습니다.");
        }
        final Hashtable resultHash = new Hashtable();
        resultHash.put("jodalItemYN", this.jodalItemYN(saupjaNumber, conn));
        resultHash.put("ingamYN", this.ingamYN(saupjaNumber, conn));
        return resultHash;
    }
    
    private String jodalItemYN(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = " SELECT  count(*)   FROM  사용_조달품목  WHERE 사업자등록번호 = ? ";
        final String[] parameter = { saupjaNumber };
        try {
            return (new ComDbQuery().getCount(this, "usemn", sql, parameter, conn) > 0) ? "Y" : "N";
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .jodalItemYN() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    private String ingamYN(final String saupjaNumber, final Connection conn) throws Exception {
        final String sql = " select count(*)  from 사용_인감마스터  where 사업자등록번호 = ? and 사용유무 ='Y'";
        final String[] parameter = { saupjaNumber };
        try {
            return (new ComDbQuery().getCount(this, "usemn", sql, parameter, conn) > 0) ? "Y" : "N";
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .ingamYN() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] getCEO(final String saupjaNumber, final Connection conn) throws Exception {
        final Vector Vec = new Vector(1, 1);
        final String sql = "   SELECT  대표자명, SUBSTR(대표자주민번호,1,6)||'-'||SUBSTR(대표자주민번호,7,2)||'*****' 대표자주민번호, 대표자메일주소, 대표대표자여부, \t   \t\t대표자휴대폰    FROM 사용_대표자 \t WHERE 사업자등록번호 = ?     order by 대표대표자여부 desc  ";
        try {
            final String[] parameter = { saupjaNumber };
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getCEO() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] getbidVIce(final String saupjaNumber, final Connection conn) throws Exception {
        final Vector Vec = new Vector(1, 1);
        final String sql = "\t SELECT  직책명, 성명,  SUBSTR(주민등록번호,1,6)||'-'||SUBSTR(주민등록번호,7,2)||'*****' 주민등록번호 \t FROM  사용_입찰대리인\t\t WHERE 사업자등록번호 = ? ";
        try {
            final String[] parameter = { saupjaNumber };
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getbidVIce() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] getJodalItem(final String saupjaNumber, final Connection conn) throws Exception {
        final Vector Vec = new Vector(1, 1);
        final String sql = "SELECT  distinct TO_CHAR(A.등록일자, 'YYYY. MM. DD') 등록일자, A.물품분류번호, B.분류명, A.제조여부, A.형식승인번호,\t\t\t DECODE(직접생산증명서류,'1','직접생산증명서','2','공장등록증','3','건축물관리대장') FROM  사용_조달품목 A, SYN_VIEW_물품분류매핑 B  WHERE  A.사업자등록번호 = ? AND  A.물품분류번호 = B.물품분류 ";
        try {
            final String[] parameter = { saupjaNumber };
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getJodalItem() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] getlicense(final String saupjaNumber, final Connection conn) throws Exception {
        final Vector Vec = new Vector(1, 1);
        final String sql = "SELECT B.코드명||' ('||A.면허코드||')', A.면허번호, TO_CHAR(A.면허취득일자, 'YYYY. MM. DD') 면허취득일자, \t     TO_CHAR(A.면허만료일자, 'YYYY. MM. DD') 면허만료일자, nvl(A.시공능력평가액,0) FROM  사용_면허사항 A, syn_공동코드 B WHERE B.코드구분='GU9'  AND B.코드 = A.면허코드  AND A.사업자등록번호 = ?  ORDER BY B.코드명 ";
        try {
            final String[] parameter = { saupjaNumber };
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getlicense() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] getfactoryInfo(final String saupjaNumber, final Connection conn) throws Exception {
        final Vector Vec = new Vector(1, 1);
        final String sql = " SELECT 공장명, 공장주소||' '||공장나머지주소 공장주소, 공장전화번호,         decode(공장임대여부, null, '', 'Y', '임대', 'N', '자가'),             decode(공장임대여부, 'Y', to_char(공장임대시작일자, 'yyyy.mm.dd')||' ~ '||TO_CHAR(공장임대종료일자, 'yyyy.mm.dd'), '')  FROM 사용_공장정보\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t WHERE 사업자등록번호 = ?\t\t\t\t\t\t\t\t\t\t\t\t\t\t ORDER BY 공장명 ";
        try {
            final String[] parameter = { saupjaNumber };
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getfactoryInfo() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] getbranchInfo(final String saupjaNumber, final Connection conn) throws Exception {
        final Vector Vec = new Vector(1, 1);
        final String sql = "  SELECT a.사업자등록번호, 상호명, a.주소 || ' ' || a.나머지주소 주소,\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDECODE(c.상태구분,'07','Y') 상태구분, 대표자명, \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t               SUBSTR(대표자주민번호,1,6)||'-'||SUBSTR(대표자주민번호,7,2)||'*****' 대표자주민번호\t     FROM  사용_조달업체마스터 a, 사용_대표자 b, 사용_업체상태 c\t\t\t\t\t\t\t\t\t\t\t\t\t     WHERE a.사업자등록번호 = b.사업자등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.사업자등록번호 = c.사업자등록번호(+)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND c.상태구분(+) = '07'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND b.대표대표자여부 = 'Y'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND a.법인등록번호 IN (  SELECT 법인등록번호\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t       FROM 사용_조달업체마스터\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   \t\t\t\t\t\t\t\t        WHERE 사업자등록번호  =?)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     AND 본사구분 = 'N'\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     ORDER BY a.사업자등록번호 ASC\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
        try {
            final String[] parameter = { saupjaNumber };
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getbranchInfo() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] getchangeResume(final String saupjaNumber, final Connection conn) throws Exception {
        final Vector Vec = new Vector(1, 1);
        final String sql = "  select rownum N, a.갱신일자, a.속성명, a.변경후내용, a.변경구분   \tfrom \t\t(SELECT 갱신일자, 속성명, \t\t CASE WHEN 속성명='대표대표자여부' and 변경구분 != 'D' and length(REPLACE(TRANSLATE(변경후내용,TRANSLATE(변경후내용,'X0123456789','X'),' '),' ','')) != 0 THEN substr(TRANSLATE(변경후내용,'X0123456789','*'),0,length(TRANSLATE(변경후내용,'X0123456789','*'))-1)||substr(REPLACE(TRANSLATE(변경후내용,TRANSLATE(변경후내용,'X0123456789','X'),' '),' ',''),1,8)||'*****]'   \t\t      WHEN 속성명='대표대표자여부' and 변경구분 != 'D' and length(REPLACE(TRANSLATE(변경후내용,TRANSLATE(변경후내용,'X0123456789','X'),' '),' ',''))  = 0 THEN TRANSLATE(변경후내용,'X0123456789','*')   \t\t      WHEN 속성명='대표자' and 변경구분 != 'D' THEN TRANSLATE(변경후내용,'X0123456789','*')||substr(REPLACE(TRANSLATE(변경후내용,TRANSLATE(변경후내용,'X0123456789','X'),' '),' ',''),1,8)||'*****'  \t\t      WHEN 속성명='입찰대리인' and 변경구분 != 'D' THEN TRANSLATE(변경후내용,'X0123456789','*')||substr(REPLACE(TRANSLATE(변경후내용,TRANSLATE(변경후내용,'X0123456789','X'),' '),' ',''),1,8)||'*****'  \t\t\t  ELSE 변경후내용 \t     END \t\t변경후내용, 변경구분 \t\tFROM 사용_입찰자격사항이력\t\t\tWHERE 사업자등록번호  = ? \t\tORDER BY 갱신일자 DESC) a \twhere rownum between 1 and 10  ";
        try {
            final String[] parameter = { saupjaNumber };
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getchangeResume() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public CommEntity[] getingam(final String saupjaNumber, final Connection conn) throws Exception {
        final Vector Vec = new Vector(1, 1);
        final String sql = "   SELECT 이미지명    FROM 사용_인감마스터    WHERE 사업자등록번호 = ?    AND 사용유무 = 'Y' ";
        try {
            final String[] parameter = { saupjaNumber };
            return new ComDbQuery().getList(this, "usemn", sql, parameter, conn);
        }
        catch (Exception exm) {
            Log.debug(String.valueOf(this.getClass().getName()) + " .getingam() :saupjaNumber[" + saupjaNumber + "]:" + exm.toString());
            throw exm;
        }
    }
    
    public String getGubun1(final String Kiupgubun) {
        String gubun_1 = null;
        final String Kiupgubun_1 = Kiupgubun.substring(0, 1);
        final String Kiupgubun_2 = Kiupgubun.substring(1, 2);
        final String Kiupgubun_3 = Kiupgubun.substring(2, 3);
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
        return gubun_1;
    }
    
    public String getGubun2(final String Kiupgubun) {
        String gubun_2 = null;
        final String Kiupgubun_4 = Kiupgubun.substring(4, 5);
        if (Kiupgubun != null && Kiupgubun_4.equals("1")) {
            gubun_2 = "∨^";
        }
        else {
            gubun_2 = "^";
        }
        return gubun_2;
    }
}
