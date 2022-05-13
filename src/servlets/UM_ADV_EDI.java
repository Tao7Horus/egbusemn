// 
// Decompiled by Procyon v0.5.30
// 

package servlets;

public class UM_ADV_EDI
{
    public static String query1;
    public static final String Main_EDI_I = " INSERT INTO SYN_등록업체 (  사업구분, 등록업체코드, 상호명, 사업자등록번호,  등록일자, 대표자명, 대표자주민번호, 본사지역코드, 본사우편번호,  본사통반번지, 본사주소, 본사전화번호, FAX번호, 설립일자,  법인구분, 법인설립일자, 업체구분, 등록유효일자, 수정일자,  입력일자, 국가코드, 자본금, 종업원수, 홈페이지,  대표자메일주소, 입력자사번  ) VALUES (  ?, ?, ?, ?,  SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ADD_MONTHS(SYSDATE, 24), SYSDATE,  SYSDATE, ?, ?, ?, ?,  ?, '970251'  ) ";
    public static final String Naeja_Main_EDI_I = " INSERT INTO SYN_등록업체 (  사업구분, 등록업체코드, 상호명, 사업자등록번호,  등록일자, 대표자명, 대표자주민번호, 본사지역코드, 본사우편번호,  본사통반번지, 본사주소, 본사전화번호, FAX번호, 설립일자,  법인구분, 법인설립일자, 업체구분, 등록유효일자, 수정일자,  입력일자, 국가코드, 자본금, 종업원수, 홈페이지,  대표자메일주소, 입력자사번  ) VALUES (  '1', ?, ?, ?,  SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ADD_MONTHS(SYSDATE, 24), SYSDATE,  SYSDATE, ?, ?, ?, ?,  ?, '970251'  ) ";
    public static final String Sisl_Main_EDI_I = " INSERT INTO SYN_등록업체 (  사업구분, 등록업체코드, 상호명, 사업자등록번호,  등록일자, 대표자명, 대표자주민번호, 본사지역코드, 본사우편번호,  본사통반번지, 본사주소, 본사전화번호, FAX번호, 설립일자,  법인구분, 법인설립일자, 업체구분, 등록유효일자, 수정일자,  입력일자, 국가코드, 자본금, 종업원수, 홈페이지,  대표자메일주소, 입력자사번  ) VALUES (  '3', ?, ?, ?,  SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ADD_MONTHS(SYSDATE, 24), SYSDATE,  SYSDATE, ?, ?, ?, ?,  ?, '970251'  ) ";
    public static final String For_Main_EDI_I = " INSERT INTO SYN_등록업체 (  사업구분, 등록업체코드, 상호명, 사업자등록번호,  등록일자, 대표자명, 대표자주민번호, 본사지역코드, 본사우편번호,  본사통반번지, 본사주소, 본사전화번호, FAX번호, 설립일자,  법인구분, 법인설립일자, 업체구분, 등록유효일자, 수정일자,  입력일자, 국가코드, 자본금, 종업원수, 홈페이지,  대표자메일주소, 입력자사번  ) VALUES (  '2', ?, ?, ?,  SYSDATE, ?, ?, ?, ?,  ?, ?, ?, ?, ?,  ?, ?, ?, ADD_MONTHS(SYSDATE, 24), SYSDATE,  SYSDATE, ?, ?, ?, ?,  ?, '970251'  ) ";
    public static final String Naeja_EDI_I = " INSERT INTO SYN_내자업체 (  등록업체코드, 지역코드, 우편번호, 통반_번지, 주소,  전화번호, FAX번호, 입력일자, 법인등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, ?,  ?, ?, SYSDATE, ?, '970251'  ) ";
    public static final String JP_EDI_I = " INSERT INTO SYN_조달품목 (  등록업체코드, 물품분류번호, 매출액, 형식승인번호, 공장구분, 입력자사번  ) VALUES (  ?, ?, ?, ?, ?, '970251'  ) ";
    public static final String ID_EDI_I = " INSERT INTO SYN_입찰대리인 (  등록업체코드, 직책명, 성명, 주민등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, '970251'  ) ";
    public static final String Sisl_EDI_I = " INSERT INTO SYN_시설업체 (  등록업체코드, 자본금, 종업원수, 대표면허번호, 법인등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, ?, '970251'  ) ";
    public static final String MS_EDI_I = " INSERT INTO SYN_면허사항 (  등록업체코드, 면허번호, 면허취득일자, 도급한도액, 입력자사번, 입력일자  ) VALUES (  ?, ?, ?, ?, '970251', SYSDATE  ) ";
    public static final String For_EDI_I = " INSERT INTO SYN_외자상사업체 (  등록업체코드, 영문상호명, 국가코드, 법인등록번호, 입력자사번  ) VALUES (  ?, ?, ?, ?, '970251'  ) ";
    public static final String ComCode_EDI_I = " INSERT INTO SYN_매핑코드업체 (  사업자등록번호, 물품등록업체코드, 공사등록업체코드, 용역등록업체코드, 외자등록업체코드,  사용여부  ) VALUES (  ?, ?, ?, ?, ?,  'Y'  ) ";
    public static final String ComCode_EDI_U = " UPDATE SYN_매핑코드업체 SET  공사등록업체코드 = ?  WHERE 사업자등록번호 = ? ";
    public static final String ComCode_EDI_U1 = " UPDATE SYN_매핑코드업체 SET  용역등록업체코드 = ?  WHERE 사업자등록번호 = ? ";
    public static final String ComCode_EDI_U2 = " UPDATE SYN_매핑코드업체 SET  외자등록업체코드 = ?  WHERE 사업자등록번호 = ? ";
    public static final String ComCode_EDI_U3 = " UPDATE SYN_매핑코드업체 SET  물품등록업체코드 = ?  WHERE 사업자등록번호 = ? ";
    public static final String Main_EDI_U1 = " UPDATE SYN_등록업체 SET  상호명 = ?, 사업자등록번호 = ?,  대표자명 = ?, 대표자주민번호 = ?, 본사지역코드 = ?, 본사우편번호 = ?,  본사통반번지 = ?, 본사주소 = ?, 본사전화번호 = ?, FAX번호 = ?, 설립일자 = ?,  법인구분 = ?, 법인설립일자 = ?, 업체구분 = ?, 등록유효일자 = ADD_MONTHS(SYSDATE, 24), 수정일자 = SYSDATE,  국가코드 = ?, 자본금 = ?, 종업원수 = ?, 홈페이지 = ?,  대표자메일주소 = ?  WHERE 등록업체코드 = ? ";
    public static final String Main_EDI_U2 = " UPDATE SYN_등록업체  SET 수정일자 = SYSDATE, \t\t취소구분 = '07', \t\t취소일자 = SYSDATE  WHERE 등록업체코드 = ? ";
    public static final String Naeja_EDI_U = " UPDATE SYN_내자업체 SET  지역코드 = ?, 우편번호 = ?, 통반_번지 = ?, 주소 = ?,  전화번호 = ?, FAX번호 = ?, 입력일자 = SYSDATE, 법인등록번호 = ?  WHERE 등록업체코드 = ? ";
    public static final String Sisl_EDI_U = " UPDATE SYN_시설업체 SET  자본금 = ?, 종업원수 = ?, 대표면허번호 = ?, 법인등록번호 = ?  WHERE 등록업체코드 = ? ";
    public static final String For_EDI_U = " UPDATE SYN_외자상사업체 SET  영문상호명 = ?, 국가코드 = ?, 법인등록번호 = ?  WHERE 등록업체코드 = ? ";
    public static final String JP_EDI_U = " UPDATE SYN_조달품목 SET  물품분류번호 = ?, 매출액 = ?  WHERE 등록업체코드 = ? AND 공장구분 = ? ";
    public static final String JP_EDI_U1 = " UPDATE SYN_조달품목 SET  물품분류번호 = ?, 매출액 = ?, 형식승인번호 = ? WHERE 등록업체코드 = ? AND 공장구분 = ? ";
    public static final String JP_EDI_D = " DELETE FROM SYN_조달품목  WHERE 등록업체코드 = ? AND 공장구분 = ?";
    public static final String ID_EDI_D = " DELETE FROM SYN_입찰대리인  WHERE 등록업체코드 = ? ";
    public static final String MS_EDI_D = " DELETE FROM SYN_면허사항  WHERE 등록업체코드 = ? ";
}
