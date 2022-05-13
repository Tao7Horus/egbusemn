/********************************************************************************/
/* 1.시	스 템 명 : 전자조달-사용자등록											*/
/* 2.프로그램 ID : UM_URB_CERT010.java											*/
/* 3.프로그램 명 : 공공기관인증서등록신청처리컨트롤		Control xử lý đăng ký GCN của CQNN							*/
/* 4.작	 성	  일 : 2003-04-26													*/
/* 5.작	 성	  자 : 김영진														*/
/* 6.개		  요 : 인증서접수처리를 담당한다.									*/
/********************************************************************************/
/*		 일자				버전		작성자		변경사항					*/
/*		2003.04.26			V1.0		김영진		최초생성					*/
/********************************************************************************/
/*
	2004.02.10	설원식	기관명의 앞 뒤 공백 제거
	2004.11.11	윤태우 	updateKNAME() 영문상호명 등록메소드 추가 
	2007.01.31	이광현	insertReception() 메써드 파라메터 추가(3개)
	2007.03.02	이광현	수요기관 인증서 발급요청 관련 기능개선 -> updateCERT_ORG()메써드 추가
	2007.05.31	신경운	핸드폰, SMS 수신여부 추가
	2007.08.06	김영진	1.DB 자원 미반납건 수정
	2009.05.09  SONNQ

/***************************************************************************/
/* Program ID         : UM_URB_CERT010.java                        */
/* Program Explanation: Control xử lý đăng ký GCN của CQNN	              */
/* Program Summary    : Control xử lý đăng ký GCN của CQNN				*/
/* Relation Program   : 					*/ 
/*                      					*/
/* Table              :                  */
/***************************************************************************/
/* Customizing Composer : MR. SonDN 20.05.2009                       */
/***************************************************************************/



package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.ComStr;
import common.Log;
import common.util.UM_COB_GTQ904;


public class UM_URB_CERT010{

	
    /**
	*	사용_접수공공기관인증서 테이블에서 접수번호를 query 한다.
	*/
	public String getMaxReceptionID(Connection conn) throws Exception {
        
        String sql = null;
        PreparedStatement pstmt = null;
        ResultSet rest=null;
        
        String result=null;

//        sql="SELECT to_char(sysdate,'yyyy')||lpad((substr(max(접수번호), 5, 10)+1), 6, '0') FROM 사용_접수공공기관인증서";
        sql = "SELECT to_char(sysdate,'yyyy')||lpad((substr(max(RECV_NO), 5, 10)+1), 6, '0') FROM UM_REC_PUB_INSTITU_CERT";
        try{
        	pstmt = conn.prepareStatement(sql);
        	
			rest=pstmt.executeQuery();
			
			if(rest.next()){
				result=rest.getString(1);
				if(rest.wasNull()){
					throw new Exception("Hệ thống không sinh được số tiếp nhận");// throw new Exception("접수번호 채번 실패");	
					
				}	
			}
        	
        }finally{
        	try{if(rest !=null) rest.close();}catch(Exception ex){}
        	try{if(pstmt !=null) pstmt.close();}catch(Exception ex){}
        }
        
        return result;
    }
    
    
    /**
	*	사용_접수공공기관인증서 테이블 insert
	*/
	public void insertReception(String recept,String G2BCODE,String CNAME,String KNAME,String IDENT,
			String IDENTJUMIN,String ZIPNO,String ADDRS,String ADDRESS2,String COMNO,
			String FAX,String MYNAME,String OFFICEDE,String JUMIN,String TEL,
			String EMAIL,String HP,String USRID,String CERT_ORG,String HOME,
			String approvalCode,String branchOffi1, 
			String passwdmodOK, String gID, String i07, String smsCheck, Connection conn) throws Exception {
		
		this.insertReception( recept, G2BCODE, CNAME, KNAME, IDENT,
				 IDENTJUMIN, ZIPNO, ADDRS, ADDRESS2, COMNO,
				 FAX, MYNAME, OFFICEDE, JUMIN, TEL,
				 EMAIL, HP, USRID, CERT_ORG, HOME,
				 approvalCode, branchOffi1, 
				 passwdmodOK,  gID,  i07,  smsCheck,  "",  conn);
	}
	
	public void insertReception(String recept,String G2BCODE,String CNAME,String KNAME,String IDENT,
								String IDENTJUMIN,String ZIPNO,String ADDRS,String ADDRESS2,String COMNO,
								String FAX,String MYNAME,String OFFICEDE,String JUMIN,String TEL,
								String EMAIL,String HP,String USRID,String CERT_ORG,String HOME,
								String approvalCode,String branchOffi1, 
								String passwdmodOK, String gID, String i07, String smsCheck, String CERT_CLS, Connection conn) throws Exception {
        if (CERT_CLS.equals("")) CERT_CLS="B";
        
      //  String sql = null;
        StringBuffer sqlB = new StringBuffer();
        PreparedStatement pstmt = null;
        
//        sql= " INSERT INTO 사용_접수공공기관인증서"
//			+"        (접수번호,       공공기관코드,   공공기관명_전체,    공공기관명_영문,    대표자,      대표자주민번호,"
//			+"        우편번호,        주소,           나머지주소,         사업자등록번호,     팩스번호,    담당자명,"
//			+"        담당자부서명,    담당자주민번호, 담당자전화번호,     담당자메일주소,     핸드폰,      사용자ID,"
//			+"        닉네임,          공인인증기관,   접수일자,           홈페이지,           요청인가코드,등록여부,"
//			+"        승인지청,        비밀번호변경여부, 비밀번호변경ID,   비밀번호, SMS수신여부)"
//			+" VALUES"
//			+"       (?, ?, rtrim(ltrim(?)), ?, ?, ?,"
//			+"        ?, ?, ?, ?, ?, ?,"
//			+"        ?, ?, ?, ?, ?, ?,"
//			+"        ?, ?, sysdate, ?, ?, ?,"
//			+"        ?, ?, ?, ?, ?)";
        /*sql= " INSERT INTO UM_REC_PUB_INSTITU_CERT"
			+"        (RECV_NO,       INSTITU_CD,   INSTITU_FULL_NM,    INSTITU_EN_NM,    REPR_NM,      REPR_IDENT_NO,"
			+"        ZIP_CD,        ADDR,           DETAIL_ADDR,         BIZ_REG_NO,     FAX,    CHRGR_MN,"
			+"        CHRGR_DEPART,    CHRGR_IDENT_NO, CHRGR_PHONE_NO,     CHRGR_EMAIL,     MOBILE,      USER_ID,"
			+"        NICKNAME,          CERT_INSTITU,   RECV_DT,           WEBSITE,           APPROVE_REQ_CD,REG_YN,"
			+"        PERMIT_BRANCH,        PASSWORD_MOD_YN, PASSWORD_MOD_ID,   PASSWORD, SMS_RECV_YN, " 
			+"		  CERT_CLS )"
			+" VALUES"
			+"       (?, ?, rtrim(ltrim(?)), ?, ?, ?,"
			+"        ?, ?, ?, ?, ?, ?,"
			+"        ?, ?, ?, ?, ?, ?,"
			+"        ?, ?, sysdate, ?, ?, ?,"
			+"        ?, ?, ?, ?, ?, ?)";*/

        sqlB.append( " INSERT INTO UM_REC_PUB_INSTITU_CERT" )
		.append("        (RECV_NO,       INSTITU_CD,   INSTITU_FULL_NM,    INSTITU_EN_NM,    REPR_NM,      REPR_IDENT_NO,")
		.append("        ZIP_CD,        ADDR,           DETAIL_ADDR,         BIZ_REG_NO,     FAX,    CHRGR_MN,")
		.append("        CHRGR_DEPART,    CHRGR_IDENT_NO, CHRGR_PHONE_NO,     CHRGR_EMAIL,     MOBILE,      USER_ID,")
		.append("        NICKNAME,          CERT_INSTITU,   RECV_DT,           WEBSITE,           APPROVE_REQ_CD,REG_YN,")
		.append("        PERMIT_BRANCH,        PASSWORD_MOD_YN, PASSWORD_MOD_ID,   PASSWORD, SMS_RECV_YN, " )
		.append("		  CERT_CLS )")
		.append(" VALUES")
		.append("       (?, ?, rtrim(ltrim(?)), ?, ?, ?,")
		.append("        ?, ?, ?, ?, ?, ?,")
		.append("        ?, ?, ?, ?, ?, ?,")
		.append("        ?, ?, sysdate, ?, ?, ?,")
		.append("        ?, ?, ?, ?, ?, ?)");
        	
        
        try{
        	
        	if (HP.equals("")) HP = " ";
        	
        	pstmt = conn.prepareStatement(sqlB.toString());
        	pstmt.setString(1,  recept);         // 접수번호
            pstmt.setString(2,  G2BCODE);        // 공공기관코드
            pstmt.setString(3,  CNAME);          // 공공기관명_전체
            pstmt.setString(4,  KNAME);          // 공공기관명_영문
            pstmt.setString(5,  IDENT);          // 대표자
            pstmt.setString(6,  ComStr.replace(IDENTJUMIN, "-", "")); // 대표자주민번호
            pstmt.setString(7,  ZIPNO);          // 우편번호
            pstmt.setString(8,  ADDRS);          // 주소
            pstmt.setString(9,  ADDRESS2);       // 나머지주소
            pstmt.setString(10, ComStr.replace(COMNO, "-", ""));          // 사업자등록번호
            pstmt.setString(11, FAX);            // 팩스번호
            pstmt.setString(12, MYNAME);         // 담당자명
            pstmt.setString(13, OFFICEDE);       // 담당자부서명
            pstmt.setString(14, ComStr.replace(JUMIN, "-", ""));          // 담당자주민번호
            pstmt.setString(15, TEL);            // 담당자전화번호
            pstmt.setString(16, EMAIL);          // 담당자메일주소
            pstmt.setString(17, HP);             // 핸드폰
            pstmt.setString(18, USRID);          // 사용자ID
            pstmt.setString(19, "HAIR");         // 
            pstmt.setString(20, CERT_ORG);       //
            pstmt.setString(21, HOME);           // 홈페이지주소
            pstmt.setString(22, approvalCode);   // 요청인가코드
            pstmt.setString(23, "N");            // 등록여부
            pstmt.setString(24, branchOffi1);    // 승인지청
            pstmt.setString(25, passwdmodOK);    // 비밀번호변경여부
            pstmt.setString(26, gID);			 // 비밀번호변경ID
            pstmt.setString(27, i07);			 // 비밀번호
            pstmt.setString(28, smsCheck);			 // SMS수신여부
            pstmt.setString(29, CERT_CLS);		// B: Buyer; S: Supplier	
        	
			if(pstmt.executeUpdate() !=1){
				throw new Exception("Đã có lỗi xảy ra khi cập nhật dữ liệu, bạn hãy liên lạc với bộ phận hỗ trợ khách hàng để xử lý lỗi này."); 
				// throw new Exception("인증서접수처리 등록갯수 1 이 아님");	
			}
        }finally{
        	
        	try{if(pstmt !=null) pstmt.close();}catch(Exception ex){}
        }
    }
    
    
    /**
	*	사용_접수공공기관인증서 테이블 update
	*	D : 반려	Y : 승인
	*/
	public void updateReception(String gubun,String back,String recept,String G2BCODE,String dependCode,String adminID, Connection conn) throws Exception {
        
        String sql = null;
        PreparedStatement pstmt = null;
        
//        sql= " UPDATE  사용_접수공공기관인증서" 
//        	+" 	SET 등록여부=?,반려사유=?, 승인자ID=? , 승인일시=sysdate "
//        	+" WHERE  접수번호 	=?"
//        	+" AND  공공기관코드 	=?"
//        	+" AND  요청인가코드 	=?";
        sql= " UPDATE  UM_REC_PUB_INSTITU_CERT" 
        	+" 	SET REG_YN=?,REJECTED_RSON=?, PERMIT_USER_ID=? , PERMIT_DT=sysdate "
        	+" WHERE  RECV_NO 	=?"
        	+" AND  INSTITU_CD 	=?"
        	+" AND  APPROVE_REQ_CD 	=?";

        	
        try{
        	
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1,  gubun);
            pstmt.setString(2,  back);
            pstmt.setString(3,  adminID);
            pstmt.setString(4,  recept);
            pstmt.setString(5,  G2BCODE);
            pstmt.setString(6,  dependCode);
        	int res=-1;
        	try{
        		res=pstmt.executeUpdate();
        	}catch(Exception ex){
        		Log.errors(this, ex, "UPDATE ERROR");
        	}
			if(res !=1){
				throw new Exception("Đã có lỗi xảy ra khi cập nhật dữ liệu, bạn hãy liên lạc với bộ phận hỗ trợ khách hàng để xử lý lỗi này.");
				// throw new Exception("인증서접수처리 update 1 이 아님");	
			}
        	
        }finally{
        	try{if(pstmt !=null) pstmt.close();}catch(Exception ex){}
        }
    }

    /**
	*	사용_접수공공기관인증서.공인인증기관 update
	*	0 : 한국정보인증 	1 : 한국정보사회진흥원 		2 : 코스콤 		4 : 한국전자인증
	*	0 : Cơ quan chứng nhận HQ 	1: Viện phát triển xã hội thông tin HQ ...
	*/
	public void updateCERT_ORG(String CERT_ORG,String recept,String G2BCODE,String dependCode, Connection conn) throws Exception {
        
        String sql = null;
        PreparedStatement pstmt = null;
        
//        sql= " UPDATE  사용_접수공공기관인증서" 
//        	+" 	SET 공인인증기관=? "
//        	+" WHERE  접수번호 	=?"
//        	+" AND  공공기관코드 	=?"
//        	+" AND  요청인가코드 	=?";
        sql= " UPDATE  UM_REC_PUB_INSTITU_CERT" 
        	+" 	SET CERT_INSTITU=? "
        	+" WHERE  RECV_NO 	=?"
        	+" AND  INSTITU_CD 	=?"
        	+" AND  APPROVE_REQ_CD 	=?";
        	
        try{
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1,  CERT_ORG);
            pstmt.setString(2,  recept);
            pstmt.setString(3,  G2BCODE);
            pstmt.setString(4,  dependCode);
        	
			if(pstmt.executeUpdate() !=1){
				throw new Exception("Đã có lỗi xảy ra khi cập nhật dữ liệu, bạn hãy liên lạc với bộ phận hỗ trợ khách hàng để xử lý lỗi này.");
				//throw new Exception("인증기관 변경처리 update 1 이 아님");	
			}
        }finally{
        	
        	try{if(pstmt !=null) pstmt.close();}catch(Exception ex){}
        }
    }


	/**
	*	기관영문상호명 등록
	*	Đăng ký tên cơ quan bằng tiếng Anh
	*/
	public void updateKNAME(String G2BCODE,String KNAME , Connection conn) throws Exception {
        
        String sql = null;
        PreparedStatement pstmt = null;

		String sql_1 = null;
        PreparedStatement pstmt_1 = null;
        String result_1 = null;
        ResultSet restSet =null;
		
//		sql_1= " SELECT  공공기관명_영문 FROM  사용_공공기관마스터  " 
//        	+  " WHERE 공공기관코드 =? ";
//		
//		sql= " UPDATE  사용_공공기관마스터 " 
//        	+" SET 공공기관명_영문 =?, 갱신일자 =sysdate "
//        	+" WHERE  공공기관코드 	=? ";
        sql_1= " SELECT  INSTITU_EN_NM FROM  UM_PUB_INSTITU_MAST  " 
        	+  " WHERE INSTITU_CD =? ";
		
		sql= " UPDATE  UM_PUB_INSTITU_MAST " 
        	+" SET INSTITU_EN_NM =?, UPDATE_DT =sysdate "
        	+" WHERE  INSTITU_CD 	=? ";
                	
        try{
        	
			pstmt_1 = conn.prepareStatement(sql_1);
        	pstmt_1.setString(1,  G2BCODE);
			restSet = pstmt_1.executeQuery();
			if(restSet.next()){
				result_1=restSet.getString(1);
			}
			// 공공기관명_영문이 등록되어있지 않으면 등록처리함
            if(result_1 == null ||result_1.equals("")){
				pstmt = conn.prepareStatement(sql);
        		pstmt.setString(1,  KNAME);
				pstmt.setString(2,  G2BCODE);
				
				Log.debug("SQL "+sql);
				Log.debug("KNAME "+KNAME);
				Log.debug("G2BCODE "+G2BCODE);
				int res=-1;
				try{
	        		res=pstmt.executeUpdate();
	        	}catch(Exception ex){
	        		Log.errors(this, ex, "UPDATE ERROR");
	        	}
				if(res !=1){
					throw new Exception("Đã có lỗi xảy ra khi cập nhật dữ liệu, bạn hãy liên lạc với bộ phận hỗ trợ khách hàng để xử lý lỗi này.");	
					// throw new Exception("영문상호명등록시 update 1 이 아님");
				}else{
					Log.debug("Update success 337");
				}
            }
		
		}finally{
        	try{if(restSet !=null) restSet.close();}catch(Exception ex){}
        	
        	try{if(pstmt !=null) pstmt.close();}catch(Exception ex){}
			try{if(pstmt_1 !=null) pstmt_1.close();}catch(Exception ex){}
        }
    }
    
     /**
	*	사용_접수공공기관인증서 테이블 update
	*/
	public void updateReceptionCert(String dn,String refername,String refercode,String comNo,String g2bCode,String dependCode,Connection conn) throws Exception {
        
        String sql = null;
        PreparedStatement pstmt = null;
        
//        sql= " UPDATE  사용_접수공공기관인증서"
//        	+" SET		전자서명인증서고유명 = ?,접수내용 = ?,인가코드 = ?,발급여부 = 'Y'"
//        	+" WHERE  사업자등록번호=?"
//        	+" AND  공공기관코드=?"
//        	+" AND  요청인가코드=?";
        sql= " UPDATE  UM_REC_PUB_INSTITU_CERT"
        	+" SET		CERT_NM = ?, RECV_CONTENT = ?, APPROVE_REQ_CD = ?, ISSUE_YN = 'Y', TEMP_NAME = ?"
        	+" WHERE  BIZ_REG_NO = ?"
        	+" AND  INSTITU_CD = ?"
        	+" AND  APPROVE_REQ_CD = ?";
        
        try{
        	
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1,  dn);			// 접수번호     -	Tên Chứng nhận số
            pstmt.setString(2,  refername);     // 접수내용-	Nội dung Chứng nhận số
            pstmt.setString(3,  refercode);     // 인가코드    
//            pstmt.setString(4,  comNo);         // 사업자등록번호
//            pstmt.setString(5,  g2bCode);       // 공공기관코드
//            pstmt.setString(6,  dependCode);    // 요청인가코드            
            pstmt.setString(4, "APP_CD:" + dependCode);//TEMP_NAME
            pstmt.setString(5, comNo);
            pstmt.setString(6, g2bCode);
            pstmt.setString(7, dependCode);
        	
			if(pstmt.executeUpdate() !=1){
				throw new Exception("Đã có lỗi xảy ra khi cập nhật dữ liệu, bạn hãy liên lạc với bộ phận hỗ trợ khách hàng để xử lý lỗi này.");	
				//throw new Exception("인증서접수처리 update 1 이 아님");
				
			}
        }finally{
        	try{if(pstmt !=null) pstmt.close();}catch(Exception ex){}
        }
    }
    
    /**
	*	사용_공공기관마스터 테이블에 코드가 존재하는지 체크
	*	Kiểm tra xem có mã trong table UM_PUB_INSTITU_MAST không.
	*/
	public int isDataExistOnGigwanMaster(String mCode,String saupjaNumber,Connection conn) throws Exception {

//        String sql= " SELECT COUNT(*) FROM 사용_공공기관마스터 WHERE 공공기관코드='"+mCode+"' and 사업자등록번호='"+saupjaNumber+"'";
//		String sql= " SELECT COUNT(*) FROM UM_PUB_INSTITU_MAST WHERE INSTITU_CD='"+mCode+"' and BIZ_REG_NO='"+saupjaNumber+"'";	
		String sql= " SELECT COUNT(*) FROM UM_PUB_INSTITU_MAST WHERE INSTITU_CD='"+mCode+"' and BIZ_REG_NO='"+saupjaNumber+"'";
       	return new UM_COB_GTQ904().getCount(this,sql,conn);
    }
	/**
	*	Adding in 03/06/2009
	*	Kiểm tra xem có mã trong table UM_SUPPLIER_ENTER_MAST không.
	*/
	public int isDataExistOnEnterMaster(String mBigRegNo,Connection conn) throws Exception {
		String sql= " SELECT COUNT(*) FROM UM_SUPPLIER_ENTER_MAST WHERE  BIZ_REG_NO='"+mBigRegNo+"'";	
	
     	return new UM_COB_GTQ904().getCount(this,sql,conn);
  }
}
