/****************************************************************************/
/*																				*/
/*    Program ID    :     UM_URV_UserB010c.java          */
/*																				*/
/*    description   :     조달업체인증서등록컨트롤  			*/
/*																				*/
/****************************************************************************/
/*  최초생성         2002.06.15          배 수 진                 */
/*  수정(DB link 삭제)    08.22          배 수 진                */
/****************************************************************************/
/*
	2003.04.23	김영진	프로그램 수정
	
	flag :	m	[UM_COJ_ConiC020m.jsp-조달업체인증서수정조회]
			i	[UM_RAJ_ConuC020i.jsp-조달업체인증서신규등록]
			s	[UM_RAJ_ConuC010i.jsp-조달업체 등록되었는지 확인-사업자등록번호입력]

	2003.05.15  권순재	인증관련 소스 수정
	2003.07.02	김영진	1.flag m 인 경우에 사용_인증서정보 테이블 수정일시 update 부분 추가
	2004.05.25	설원식	urc.insertUseCert(id, DN1,DN2,CERT1,CERT2,CertFromDate,CertToDate,con);에
						인증서의 유효기간시작일시, 유효기간만료일시 추가
	2004.09.30	설원식	비축업체이용자등록 추가
    2006.11.20  성해리  GPKI인증서 적용, 
                        신원확인용 필요한 값은 중요한 데이터이므로 암호화하여 전달 하도록 수정    

    2007.07.02  이광현  일반범용인증서(c)로 비축인증서(x) 등록가능하도록 긴급변경
    2007.07.06  이광현  공사관리진행통보서 사용인증서 등록 관련 제어로직 추가
	2007.08.31	이광현	미반환 JDBC 에 대한 개선 -> 프로그램 정리
	2008.03.13	신경운	1.2.410.200004.5.1.1.12   허용OID 추가  
	                                - secu.cfg 에서 처리 하지 않고 하드코딩된 원인 및 차후 secu.cfg 에서 처리되도록 변경 해야 함
    2008.07.10    신경운   상호명으로 담당장 중복조회 삭제처리 
    2008.11.20    신경운   유통서버 동기화 호출 프로그램삭제
	                                문서유통 문서처리데몬 개선에 따라 사용자정보를 실시간 확인이 가능해져 인증서 신규/추가 등록시  등록된 정보를 따로 전달할필요가 없어짐)
*/
/***************************************************************************/
/* Program ID         : UM_URV_UserB010c.java                        */
/* Program Explanation: Control đăng ký GCN DN                     */
/* Program Summary    : Control đăng ký GCN DN 					*/
/*                      */
/* Table              : UM_CERT_INFO                */
/***************************************************************************/
/* Customizing Composer : MR. SonDN 27.05.2009                       */
/***************************************************************************/
// File này mới dịch chưa kiểm tra
// bỏ Dòng 445,528 -  bỏ qua phần xác nhận Chứng nhận số <-- done
// 
// 
// 
// 
// 
// 

package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import LOGIN.UM_Auth_Check;
import beans.UM_URB_CERT;
import beans.UM_URB_MyPage;
import common.Log;
import common.Trx;
import secu.lib.CertInfo;
import secu.lib.EnvelopedMessage;
import secu.lib.MessageDigest;
import secu.lib.Secu;
import signgate.crypto.util.CertUtil;
import signgate.crypto.util.SignUtil;

public class UM_URV_UserB010c extends HttpServlet {
	
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
       
		Secu secu = new Secu(Secu.UM);
		
        //LoginCredential se = null;
		//se = new LoginCredential(secu);
		
		String flag	= req.getParameter("flag")==null?"":req.getParameter("flag");	// 입력(i)/수정(m)
		UM_Auth_Check uac=null;
		
		if(flag.equals("m")){
			try{
				uac=new UM_Auth_Check(req, res);
			}catch(Exception exi){
				common.util.CommonMessage.printMsg("", "", exi.getMessage(), null, res);
				return;
			}	
		}
		 System.out.println("dong`92");
		
		UM_URB_CERT urc=new UM_URB_CERT();
        UM_URB_MyPage myPage = new UM_URB_MyPage();
		
        //DB Connection variable---------------------------------------------------------------------------------------
	    Trx 				trx 	= null;
	    Trx 				emallTrx= null;
	    Connection  		con 	= null;
	    Connection  		emallCon= null;
	    //PreparedStatement   psmt1   = null;
	    //PreparedStatement   psmt2   = null;
	    //ResultSet   		rs  	= null;
		//String sql 					= null;

	    String oper			= "";			// 등록(a)인증서/(c) Registration (a) Certificate / (c)
		String del			= "";			// 삭제가능여부  Whether to delete
		String DN1			= "";			// 전자서명인증서고유명 Digital Signature Certificate Distinguished Name
		String DN2			= "";			// 암호화인증서고유명 Your encryption certificate's proper name
		String CERT1		= "";			// 전자서명인증서 Digital Signature Certificate
		String CERT2		= "";			// 암호화인증서 Encryption Certificate
        String randNo       = "";			// 신원확인 Identification
        String ENC_RANDNUM  = "";           // 대칭키로 암호화된 신원확인정보 Identity verification information encrypted with a symmetric key
        String ENC_KEY      = "";           // 서버인증서로 암호화된 대칭키 Symmetric key encrypted with server certificate
        String SIGN_LOGIN   = "";           // 로그인인증서전자서명데이터 Login Certificate Digital Signature Data

		String d01			= "";			// 상호명 Business name
	    String d02			= "";        	// 업무구분 Classification
        String d03			= "";  			// 대표자명 Representative name
        String d04			= "";  			// 사업자등록번호 Company Registration Number
        String d05			= "";  			// 전화번호 Phone number
        String d06			= "";  			// 홈페이지 home page
        String d07			= "";  			// 주소+나머지주소 Address + Remaining address
        String d08			= "";  			// 대표자주민등록번호 Representative Resident Registration Number
		
		String i01			= "";			// 담당자부서명 Department name
	    String i02			= "";        	// 담당자명 Contact person
        String i03			= "";  			// 담당자전화번호 Contact phone number
        String i04			= "";  			// 담당자팩스번호 Contact Fax
        String i05			= "";  			// 담당자이동전화번호 Contact phone number
        String i06			= "";  			// 담당자메일주소 Contact email address
        String i07			= "";  			// 비밀번호 password
        String i072			= "";  			// 비밀번호암호화 Password encryption
        String i08			= "";  			// 대표수신자여부 Representative receiver status
		String i09			= "";  			// 관리전환사용권한 Management Transition License
		String id			= "";  			// 새롭게 생성된 사용자ID  The newly created user ID
		String mainCheck	= "";
		String mcode		= "";  			// 새롭게 생성된 마스터코드 The newly created master code
        String user_gubun   = "";           // 사용자구분 User classification
        //Insert variable-------------------------------------------------------------------------------------------

        //USR_GRP variable-------------------------------------------------------------------------------------------
		String grp		= null;			 // 그룹명 Group name
		//String grpcount = null;			 // 그룹내 등록된수
		//int GRPMAX      = 200;			 // 그룹내 등록될수있는 최대수
		//DecimalFormat formatcount = new DecimalFormat("00000"); 
		//int temp        = 0;			 // 그룹id 계산위한 변수
        //USR_GRP variable-------------------------------------------------------------------------------------------
        
		oper	= req.getParameter("oper")==null?"":req.getParameter("oper");	// 등록(a)인증서/(c)
		del		= req.getParameter("del")==null?"":req.getParameter("del");	    // 삭제가능여부
		DN1		= req.getParameter("DN1")==null?"":req.getParameter("DN1");	    // 전자서명인증서고유명
		DN2		= req.getParameter("DN2")==null?"":req.getParameter("DN2");	    // 암호화인증서고유명
		//CERT1	= req.getParameter("CERT1")==null?"":req.getParameter("CERT1");	// 전자서명인증서
		CERT2	= req.getParameter("CERT2")==null?"":req.getParameter("CERT2");	// 암호화인증서
		//randNo	= req.getParameter("IDENT_INFO")==null?"":req.getParameter("IDENT_INFO");		// 신원확인
        ENC_RANDNUM = req.getParameter("ENC_IDENT_INFO")==null?"":req.getParameter("ENC_IDENT_INFO");   // 대칭키로 암호화된 신원확인정보  Identity verification information encrypted with a symmetric key   
        ENC_KEY     = req.getParameter("ENC_KEY_INFO")==null?"":req.getParameter("ENC_KEY_INFO");       // 서버인증서로 암호화된 대칭키    Symmetric key encrypted with server certificate
        SIGN_LOGIN  = req.getParameter("SIGN_LOGIN_INFO")==null?"":req.getParameter("SIGN_LOGIN_INFO"); // 로그인인증서전자서명데이터 Login Certificate Digital Signature Data

		id		= req.getParameter("id")==null?"":req.getParameter("id");		// ID
		d01		= req.getParameter("d01")==null?"":req.getParameter("d01");		// 상호명
		d02		= req.getParameter("d02")==null?"":req.getParameter("d02");		// 업무구분
		d03		= req.getParameter("d03")==null?"":req.getParameter("d03");		// 대표자명
		d04		= req.getParameter("d04")==null?"":req.getParameter("d04");		// 사업자등록번호
		d04		= d04.trim();
		//Log.debug(" d04: "+d04.toString() + "A");
		d05		= req.getParameter("d05")==null?"":req.getParameter("d05");		// 전화번호
		d06		= req.getParameter("d06")==null?"":req.getParameter("d06");		// 홈페이지
		d07		= req.getParameter("d07")==null?"":req.getParameter("d07");		// 주소+나머지주소
		d08		= req.getParameter("d08")==null?"":req.getParameter("d08");		// 대표자주민등록번호

		i01		= req.getParameter("i01")==null?"":req.getParameter("i01");		// 담당자부서명
		i02		= req.getParameter("i02")==null?"":req.getParameter("i02");		// 담당자명
		i03		= req.getParameter("i03")==null?"":req.getParameter("i03");		// 담당자전화번호
		i04		= req.getParameter("i04")==null?"":req.getParameter("i04");		// 담당자팩스번호
		i05		= req.getParameter("i05")==null?"":req.getParameter("i05");		// 담당자이동전화번호
		i06		= req.getParameter("i06")==null?"":req.getParameter("i06");		// 담당자메일주소
		i07		= req.getParameter("i07")==null?"":req.getParameter("i07");		// 비밀번호
		i08		= req.getParameter("i08")==null?"N":req.getParameter("i08");	// 대표수신자여부
		i09		= req.getParameter("i09")==null?"":req.getParameter("i09");		// 관리전환사용권한
        user_gubun = req.getParameter("user_gubun")==null?"":req.getParameter("user_gubun"); // 사용자구분
        //String[] aCode = req.getParameterValues("busset");                                   // 마이페이지설정
		
		String strMData1 = null;
        MessageDigest md = new MessageDigest(secu);

        i072  = md.create( i07 );
        System.out.println("dong` 188");
		// 인증서수정       // Sửa chứng nhận số
		if(flag.equals("m")){
			
			try{
				uac.checkCookie("cx",null,null);
				
				// cookie 에서 ID를 얻음  // Lấy ID từ cookie
				id=uac.getID();
				
				trx		= new Trx(this, "USEMN");
				con 	= trx.getConnection();
				con.setAutoCommit(false);

                /*				
				emallTrx = new Trx(this, "emall");
				emallCon = emallTrx.getConnection();
				emallCon.setAutoCommit(false);
                */
               
				//사용_사용자 update
				// Cập nhật bảng USE_Người dùng
				urc.updateUseUser(i01, i02, i03, i04, i05, i06, i072, i09, id, con);
				
				//EMALL 의 전자문서사용자 Update	// Cập nhật người dùng tài liệu điện tử EMALL
                //-- 창렬 수정 --- 2004.01.01 -- 시작	// Sửa sắp xếp, ngày 01.01.2004 bắt đầu
                //urc.updateEmallElectronicUser(i01, i02, i03, i04, i06, i07, "",id,emallCon); 
                //-- 창렬 수정 --- 2004.01.01 -- 끝	// Sửa sắp xếp, ngày 01.01.2004 kết thúc

				//사용_인증서정보 수정일시 update
				// Cập nhật bảng USE_Ngày tháng chỉnh sửa thông tin chứng nhận số
				urc.updateModifyDate(id,DN1,con);


                // 마이페이지설정		// Cài đặt My Page
                // 2006.10.09 마이페이지설정 부분 제거	// Ngày 09.10.2006 bỏ phần cài đặt My Page
                /*
                if (user_gubun.equals("c")) {

                    // 마이페이지에 데이터가 있는 경우 이메일설정부분만 삭제 Chỉ xóa phần cài đặt email trường hợp có dữ liệu ở My Page
                    if (myPage.isMypage(id, con)) {  

                        Log.debug("마이페이지설정 delete");
                        Log.debug("Xóa cài đặt My Page");
                        myPage.deletePageset(id, "c", con);
                    
                    } else {

                        myPage.insertMypage(id, "c", con);

                        myPage.insertPageset(id, "M101", con);
                        
                        myPage.insertPageset(id, "BB01", con);

                        myPage.insertPageset(id, "BB02", con);

                    }

                    if (aCode != null) {
                        
                        for(int i=0; i<aCode.length; i++) {
                            myPage.insertPageset(id, aCode[i], con);
                        }
                    }
                } */
                
				con.commit();
				con.setAutoCommit(true);
                /*				
				emallCon.commit();
				emallCon.setAutoCommit(true);
                */				
				common.util.CommonMessage.printMsg("004","1","","/CO/UM_COJ_ConiC010s.jsp", res);
				return;
			}catch(Exception exm){
				 try { if(con !=null) con.rollback(); }catch(Exception ex){}
				 try { if(con !=null) con.setAutoCommit(true); }catch(Exception ex){}
                 // try { if(emallCon !=null) emallCon.rollback(); }catch(Exception ex){}
                 // try { if(emallCon !=null) emallCon.setAutoCommit(true); }catch(Exception ex){}
				 Log.debug("UM_URV_UserB010c.java flag["+flag+"] : "+exm.toString());
				 common.util.CommonMessage.printMsg("", "", exm.getMessage(), null, res);
			}finally{
				try { if(con !=null) trx.close(); }catch(Exception exf){}
                //try { if(emallCon !=null) emallTrx.close(); }catch(Exception exf){}
			}
		}else if(flag.equals("s") && (oper.equals("c") || oper.equals("x") || oper.equals("d"))){
			
			try{
				trx		= new Trx(this, "USEMN");
				con 	= trx.getConnection();
                // 2004.06.18 수정 시작		// Bắt đầu sửa ngày 18.06.2008
				if((oper.equals("c") || oper.equals("x") || oper.equals("")) && urc.isDataExistOnJodalUpcheMaster(d04, con) == 0){
					//CommonMessage.printMsg("", "4", "등록되어 있지 않은 사업자등록번호입니다.<br>사업자등록번호를 확인 바랍니다.", "", res);
					common.util.CommonMessage.printMsg("", "4", "Đây là số ĐKKD đã đăng ký từ trước.<br>Vui lòng kiểm tra lại.", "", res);
					return;
				}
                // 2004.06.18 수정 끝	// Ngày 18.06.2004 kết thúc chỉnh sửa

				if(oper.equals("x") && urc.isDataExistOnBichukUpcheMaster(d04, con) == 0){
                    //CommonMessage.printMsg("", "4", "비축업체로 등록되어 있지 않은 사업자등록번호입니다.<br>사업자등록번호를 확인 바랍니다.", "", res);
					common.util.CommonMessage.printMsg("", "4", "Đây là số ĐKKD chưa được đăng ký làm nhà thầu dự phòng.<br>Hãy kiểm tra lại.", "", res);
					//res.sendRedirect("http://www.g2b.go.kr:8070/html/BichukError.htm");
                    res.sendRedirect("http://muasamcong.mpi.gov.vn:8070/FO/BichukError.jsp?errType=0");
					return;
				}

				if(urc.isDataExistOnUserApproveTwo(d04,con) ==0){
					res.sendRedirect("/common/terms_comp.jsp?code="+d04+"&oper="+oper);
				}else{
					res.sendRedirect("/RA/UM_URJ_UserD010l.jsp?oper="+oper+"&code="+d04+"&del="+del);				
				}
			}catch(Exception exm){
				 Log.debug("UM_URV_UserB010c.java flag["+flag+"] : "+exm.toString());
				 common.util.CommonMessage.printMsg("", "", exm.getMessage(), null, res);
			}finally{
				try { if(con !=null) trx.close(); }catch(Exception exf){}
			}	
		}else if(flag.equals("i")){
			
			String message="";

            // 사용자의 전자서명용 인증서는 로그인인증서전자서명데이터에 포함되어 있으므로 
            // 클라이언트에서 폼데이터로 전송받지 않고 로그인인증서전자서명데이터에서 얻어내어 사용하도록 한다.
			// Chứng nhận số dùng để login có chữ ký điện tử của người dùng cần dữ liệu về chữ ký điện tử nên 
			// không nhận form dữ liệu ở Client mà nhận về và dùng dữ liệu chữ ký điện tử dành cho chứng nhận số
            String strDTM = null;
            String strSignedMessage = null;     // 전자서명된데이터 dữ liệu chữ ký điện tử
            String strOriginalMessage = null;   // 전자서명원문 Bản gốc chữ ký điện tử
            //if (1==1) common.util.CommonMessage.printMsg("", "4", "Dong` 316", "", res);
			try{
				trx		= new Trx(this, "USEMN");
				con 	= trx.getConnection();
				con.setAutoCommit(false);
                
                /*				
				emallTrx = new Trx(this, "emall");
				emallCon = emallTrx.getConnection();
				emallCon.setAutoCommit(false);
                */		
                    

                //**************************************************************//
                // GPKI 인증서 사용가능하도록 (2006.11.20)
				// Để có thể dùng được chứng nhận số GPKI
                //**************************************************************//

                String strstr = new String( signgate.crypto.util.Base64Util.decode(SIGN_LOGIN) );

                StringTokenizer st  = new StringTokenizer( strstr, "|" );
                CERT1               = st.nextToken();
                strDTM              = st.nextToken();
                strSignedMessage    = st.nextToken();
                strOriginalMessage  = CERT1 + strDTM;
                
//                Log.debug("##[2]CERT1 : "+ CERT1);
//                Log.debug("##[7]strDTM : "+ strDTM);
//                Log.debug("##[8]strSignedMessage : "+ strSignedMessage);


                /* 암호화된 신원확인정보 복호화 하는 부분 ----------------------// Phần giải mã thông tin xác minh nhân thân đã mã hóa
                 */
                String strRandNo = null;

                try{
                    EnvelopedMessage se = new EnvelopedMessage(secu);
                    /* 보안모듈(클라이언트) 에서 넘어오는 encKey, encData 의 널 확인 후 
                     * 예외처리 함 , 2002-09-12, 김형욱 */
                    if (ENC_KEY==null || ENC_RANDNUM==null){
                        //throw new NullPointerException("복호화 대상 암호화 데이터가 널입니다. null 일 수 없습니다.");  
                    	throw new NullPointerException("Dữ liệu mã hóa đối tượng giải mã là null. Không thể là null.");
                        // Dữ liệu mã hóa đối tượng giải mã là null. Không thể là null
                    }

                    se.decryptInit(Secu.USR, Secu.KM, ENC_KEY);
                    strRandNo = se.decryptUpdate(ENC_RANDNUM);
                    se.decryptFinal();

                }catch (Exception e){
                	throw new Exception("Lỗi phần giải mã ký tự.");// 
					//throw new Exception("암호화된 문자열를 복호화 하는 부분 에러입니다.");// Lỗi phần giải mã ký tự 
                }

                if (strRandNo == null) { 
                	throw new Exception("Dữ liệu mã hóa đối tượng giải mã là null. Không thể là null.");
					//throw new Exception("유효하지 않은 복호화 오류입니다. null 일 수 없습니다.");  // 
                }
//                Log.debug( "strRandNo : " + strRandNo );


				CertInfo cu = new CertInfo( secu, CERT1 );
				//String strDN = cu.getCertDN();    //소유자의 DN	
				// String strDN = cu.getCertDN();    //Thông tin DN của người sử hữu

				String CertFromDate = cu.getNotBefore();		// 인증서 유효기간시작일시
				String CertToDate   = cu.getNotAfter();			// 인증서 유효기간만료일시
				
//2003. 5. 15 인증관련 소스 수정 시작				// Bắt đầu sửa source liên quan đến chứng nhận số

String OID[] = { 
"1.2.410.200005.1.1.5", //금결원 1등급

"1.2.410.200004.5.2.1.1", //정보인증 업체 1등급 Information Certification Company Level 1
//"1.2.410.200004.5.2.1.2", //정보인증 개인 1등급
//"1.2.410.200004.5.2.1.3", //정보인증 업체 특별등급

"1.2.410.200004.5.3.1.1", //전산원 상호연동기관용 Computerized Interworking Institution
"1.2.410.200004.5.3.1.2", //전산원 상호연동 법인용
"1.2.410.200004.5.3.1.5", //전산원 특수목적용

//"1.2.410.200004.5.1.1.5",  //증권전산 개인 플레티넘
"1.2.410.200004.5.1.1.7",  //증권전산 법인 플레티넘
"1.2.410.200004.5.1.1.12", //증권전산 실버

"1.2.410.200004.5.4.1.2", //전자인증

"1.2.410.200012.1.1.3",   //ktnet
"1.2.410.200012.1.1.5",   //ktnet
// 비축업체이용자용 체크를// Check dùng cho nhà thầu dự phòng
"1.2.410.200004.5.2.1.6.84",	// 한국정보인증 특별등급(비축업체이용자)	 2007.07.02 추가
"1.2.410.200005.1.1.6.3",		// yessign	 2007.07.02 추가
"1.2.410.200004.5.1.1.12.901",	// signkorea	 2007.07.02 추가
"1.2.410.200004.5.4.1.12",		// crosscert	 2007.07.02 추가
"1.2.704.100001.5.1.1.2"		// VN
};

// 비축업체이용자용 체크// Check dùng cho nhà thầu dự phòng
String OID1[] = { 
"1.2.410.200004.5.2.1.6.84",	// 한국정보인증 특별등급(비축업체이용자) Cấp đặc biệt chứng nhận số của CQ Chứng nhận TT Hàn Quốc (User nhà thầu dự phòng)
"1.2.410.200005.1.1.6.3",		// yessign 
"1.2.410.200004.5.1.1.12.901",	// signkorea
"1.2.410.200004.5.4.1.12",		// crosscert
"1.2.704.100001.5.1.1.2"		// VN
};

// String[] OID = secu.getCertOID();

//2003. 5. 15 인증관련 소스 수정 시작 끝 // Kết thúc sửa source liên quan đến chứng nhận số
System.out.println("dong` 425");
				boolean bChkOIDValid;

				// 2007.07.02 일반범용인증서(c)로 비축인증서(x) 등록가능하도록 긴급변경   // Ngày 2007.07.02 Cập nhật nhanh khả năng đăng ký chứng nhận số dùng thông thường là (c), chứng nhận số dự phòng là (x)
				/*
				if (oper.equals("x"))
				{
					bChkOIDValid = cu.isValidPolicyOid(OID1);

				} else {
				*/
					bChkOIDValid = cu.isValidPolicyOid(OID);
				//}		 // 2007.07.02 일반범용인증서(c)로 비축인증서(x) 등록가능하도록 긴급변경

				//  2007.07.02 비축용인증서로 조달업체용 인증서 등록할 수 없도록 c인 경우에 비축업체이용자용 true이면 오류 메세지 발생  // Ngày 2007.07.02 trường hợp nhà thầu không thể đăng ký được chứng nhận số dự phòng ký hiệu là c mà ngừời dùng nhà thầu là true thì sinh mesage báo lỗi
			boolean isbChkOID = false;
				if (oper.equals("c"))
				{
					isbChkOID = cu.isValidPolicyOid(OID1);
				} 
				//isbChkOID = cu.isValidPolicyOid(OID);
				if(!isbChkOID){//if(1==0){ //if(isbChkOID){
                    //Log.debug("UM_URV_UserB010c.java 마스터코드["+d04+"] ,인증서["+DN1+"] 허용되지 않은 인증서입니다. 비축용인증서인지 확인하신후 사용하여 주십시요.");
					Log.debug("UM_URV_UserB010c.java Mã master ["+d04+"] ,Chứng nhận số ["+DN1+"] không được phép sử dụng. Hãy sử dụng sau khi kiểm tra xem có phải là chứng nhận số dự phòng hay không.");
                    //CommonMessage.printMsg("", "", "허용되지 않는 인증서입니다. 비축용인증서인지 확인하신후 사용하여 주십시요.", null, res); 
					common.util.CommonMessage.printMsg("", "", "Đây là chứng nhận số không được phép dùng. Hãy sử dụng sau khi kiểm tra xem có phải là chứng nhận số dự phòng hay không.", null, res);
                    
                	con.setAutoCommit(true);
                    // emallCon.setAutoCommit(true);
                	return;
                } /* */
				// 2007.07.02 일반범용인증서(c)로 비축인증서(x) 등록가능하도록 긴급변경

				if(!bChkOIDValid){//if(1==0){ //if(!bChkOIDValid){
                    //Log.debug("UM_URV_UserB010c.java 마스터코드["+d04+"] ,인증서["+DN1+"] 허용되지 않은 인증서입니다. 인증서 등급을 확인하신후 사용하여 주십시요.");
					Log.debug("UM_URV_UserB010c.java Mã master ["+d04+"] ,Chứng nhận số ["+DN1+"] không được phép dùng. Hãy sử dụng sau khi kiểm tra.");
                    //CommonMessage.printMsg("", "", "허용되지 않는 인증서입니다. 인증서 등급을 확인하신후 사용하여 주십시요.", null, res);// Đây là chứng nhận số không được phép dùng. Hãy sử dụng sau khi kiểm tra.  
					common.util.CommonMessage.printMsg("", "", "Đây là chứng nhận số không được phép dùng 2. Hãy sử dụng sau khi kiểm tra.", null, res);
                    
                	con.setAutoCommit(true);                    // emallCon.setAutoCommit(true);
                	return;
                }
// Comment by thanhtv 2012.07.12
				
				if (!cu.isValid()){
                    //Log.debug("UM_URV_UserB010c.java 마스터코드["+d04+"] ,인증서["+DN1+"] 인증서유효성검사 실패");
					Log.debug("UM_URV_UserB010c.java Mã Master ["+d04+"] , Kiểm tra hiệu lực Chứng chỉ số ["+DN1+"] không thành công");
					common.util.CommonMessage.printMsg("", "", "Không kiểm tra được hiệu lực của chứng nhận số. Có thể đây là chứng nhận số đã xóa hoặc đã hết hiệu lực sử dụng.", null, res);
                    //CommonMessage.printMsg("", "", "인증서 유효성검사에 실패하였습니다. 인증서가 폐기되었거나 유효기간이 지난 인증서입니다.", null, res); 
                    // Không kiểm tra được hiệu lực của chứng nhận số.Có thể đây là chứng nhận số đã xóa hoặc đã hết hiệu lực sử dụng
					con.setAutoCommit(true);
                    // emallCon.setAutoCommit(true);
					return;
				}
				

// The End Comment.
				
                /*
                boolean bRet = cu.ValidateUserID(d04, "", randNo);
                if(!bRet){
                	CommonMessage.printMsg("", "4", "신원확인 검사에 실패하였습니다.<BR>인증서를 발급받으실 때 기재하신 사업자등록번호와<br>G2B 시스템에 등록된 사업자등록번호가 동일한지 확인해 주시기 바랍니다.", "", res);  // Không xác minh được nhân thân.<BR>Hãy kiểm tra lại xem số ĐKKD nhập khi xin đăng ký chứng nhận số <br>và số ĐKKD nhập vào hệ thống G2Bcó phải là một không
                	con.setAutoCommit(true);
                    // emallCon.setAutoCommit(true);
                	return;
                }
                */

                try  {

                    // 사용자의 인증서가 GPKI 인지 NPKI 인지 확인한다.
                	// Kiểm tra xem chứng nhận số của người dùng là GPKI hay NPKI
                    if( strRandNo.compareTo("GPKI") == 0 ) // GPKI Cert
                    {

                        // GPKI인증서는 조달업체로 등록 할 수 없으므로(등급이 맞지 않으므로) 실제 이 부분을 타지 않음
                        /*
                        // GPKI인증서로 업체에 등로하려는 경우
                        if (oper.equals("c") || oper.equals("x")){
                            Log.debug("UM_URV_UserE040c.java 마스터코드["+d01+"] ,인증서["+DN1+"] 허용되지 않은 인증서입니다. 인증서 등급을 확인하신후 사용하여 주십시요.");
                            CommonMessage.printMsg("", "", "허용되지 않는 인증서입니다. 인증서 등급을 확인하신후 사용하여 주십시요.<br>GPKI인증서는 조달업체용으로 등록 할 수 없습니다.", null, res);
                            con.setAutoCommit(true);
                            return;
                        }

                        // 사용자 본인확인 검증 생략 : 관인인증서는 본인확인정보가 없는 인증서이기 때문.
                        
                        // 로그인인증서 전자서명검증
                        GpkiSignUtil gpkiSign = new GpkiSignUtil("SHA1WithKCDSA");
                        gpkiSign.verifyInit(CertUtil.pemToDer(CERT1));
                        if( !gpkiSign.verifyFinal(strOriginalMessage.getBytes(), Base64Util.decode( strSignedMessage )) ) {

                            Log.debug("UM_URV_UserB010c.java 마스터코드["+d04+"] ,인증서["+DN1+"] GPKI 전자서명 검증 실패");
                            CommonMessage.printMsg("", "", "GPKI 전자서명 검증 실패입니다.", null, res);
                            con.setAutoCommit(true);
                            return;
                        }
                        */

                    }
                    else {           // NPKI Cert
                    	DN1 = new String( signgate.crypto.util.Base64Util.decode(DN1) );  
                        String dnName = DN1.substring(3, DN1.indexOf(","));

                        //Log.errors("saup_no : " + d04); 
                        //Log.errors("userDN : " + dnName); 

                        if(1==0){// if ( !cu.ValidateUserID( d04, dnName, strRandNo ) ) { // UserSSN, name, rand_key
                        	Log.debug("UM_URV_UserB010c.java Số đăng ký kinh doanh ["+d04+"] ,Giấy chứng nhận ["+dnName+"] Không thể kiểm tra xác định");
                            //Log.debug("UM_URV_UserB010c.java 사업자등록번호["+d04+"] ,인증서["+dnName+"] 신원확인 검사에 실패");
                            //message="신원확인 검사에 실패하였습니다.<BR>인증서를 발급받으실 때 기재하신 사업자등록번호와<br>G2B 시스템에 등록된 사업자등록번호가 동일한지 확인해 주시기 바랍니다.";
                        	message="Kiểm tra xác định đã không thành công. <br> "
                        			+ "Vấn đề  khi bạn nhận được Giấy chứng nhận đăng ký kinh doanh và " 
                        			+ " đăng ký vào hệ thống Đấu thầu là cùng một số đăng ký kinh doanh, xin vui lòng kiểm tra.";
                        	common.util.CommonMessage.printMsg("", "", message, null, res);
                            con.setAutoCommit(true);
                            return;
                        }
                        System.out.println("dong` 539");
                        // 로그인인증서 전자서명검증
                        // Kiểm tra chữ ký điện tử chứng nhận số login
                        SignUtil sign = null;

                        sign = new SignUtil();
                        sign.verifyInit( CertUtil.pemToDer(CERT1) );
                        sign.verifyUpdate( strOriginalMessage.getBytes() );         
                        if ( !sign.verifyFinal( signgate.crypto.util.Base64Util.decode( strSignedMessage ) )) {
                        	Log.debug("UM_URV_UserB010c.java Mã Master ["+d04+"] ,Giấy chứng nhận ["+DN1+"] Chữ ký điện tử NPKI xác nhận không thành công");
                            //Log.debug("UM_URV_UserB010c.java 마스터코드["+d04+"] ,인증서["+DN1+"] NPKI 전자서명 검증 실패");
                            //CommonMessage.printMsg("", "", "NPKI 전자서명 검증 실패", null, res);
                        	common.util.CommonMessage.printMsg("", "", "NPKI Chữ ký điện tử xác nhận không thành công", null, res);
                            con.setAutoCommit(true);
                            return;
                        }
                    }       

                }catch (Exception e){
                	Log.debug("UM_URV_UserB010c.java Mã Master ["+d04+"] ,Giấy chứng nhận ["+DN1+"] Xác minh Chữ ký điện tử lỗi");
                    //Log.debug("UM_URV_UserB010c.java 마스터코드["+d04+"] ,인증서["+DN1+"] 전자서명 검증 및 신원확인 에러");
                    //CommonMessage.printMsg("", "", "전자서명 검증 및 신원확인 에러", null, res);
                	common.util.CommonMessage.printMsg("", "", "Xác minh Chữ ký điện tử lỗi", null, res);
                    con.setAutoCommit(true);
                    return;
                }
                
                //Kiem tra cts nha thau dang ky va phe duyet tren he thong thuc
                if (urc.isDataExistOnRecPubCert(d04, DN1, con) != 1) {
                	throw new RuntimeException("Tài khoản không hợp lệ, kiểm tra lại với quản trị hệ thống đảm bảo thông tin tài khoản được cung cấp bởi hệ thống");
                };

                if (1==1) common.util.CommonMessage.printMsg("", "4", "Dong` 566", "", res);
                // 인증서가 등록되어 있는지 체크
                // Kiểm tra chứng nhận số đã được đăng ký hay chưa
                if(urc.isDataExistOnCert(DN1, con) !=0){
                	Log.debug("UM_URV_UserA010c.java Mã Master ["+d04+"] ,Chứng nhận số ["+DN1+"] cấp giấy chứng nhận");
                	//Log.debug("UM_URV_UserA010c.java 마스터코드["+d04+"] ,인증서["+DN1+"] 기등록된 인증서");
                    //CommonMessage.printMsg("", "4", "이미 등록되어 있는 인증서입니다.등록하실 필요없습니다.", "", res);
                	common.util.CommonMessage.printMsg("", "4", "Việc cấp giấy chứng nhận đã được đăng ký. Bạn không cần phải đăng ký.", "", res);
                    con.setAutoCommit(true);
                    //emallCon.setAutoCommit(true);						// 2007-08-31
                    return;
                }
/*  20080700_상호명으로 담당장 중복조회 삭제처리       Xóa tra cứu trùng lặp người phu trách theo tên công ty          

                // 같은 담당자명으로 등록되어 있는 사용자가 있는지 체크 Kiểm tra xem có người dùng đăng ký dưới cùng một tên người phụ trách hay không
                if(urc.isDataExistOnSamePerson(d01, i02,con) !=0){
                    message="이미 입력하신 담당자명으로 인증서등록이 되어 있습니다."
                            +"<br>동일한 담당자명으로 신규등록을 하실수 없으며<BR>"
                            +"이용자 목록 화면에서 '인증서추가등록'버튼을 클릭하여 추가등록 하실 것을 권장합니다."
                            +"<br>꼭 동일한 담당자명으로 신규등록을 하실분은<BR>콜센터로 문의해주시기 바랍니다.";
                    CommonMessage.printMsg("", "4", message, "", res);
                    con.setAutoCommit(true);
                    //emallCon.setAutoCommit(true);						// 2007-08-31
                    return;
                }
*/
                
                // 등록된 인증서인지 체크
                // Kiểm tra xem có phải là chứng nhận số đã đăng ký rồi hay không
                if(urc.isDataExistOnCert(DN1,con) !=0){
                	common.util.CommonMessage.printMsg("", "4", "Giấy chứng nhận đã được đăng ký.", "", res);
                	//CommonMessage.printMsg("", "4", "이미 등록된 인증서입니다.", "", res);//fádfà
                	con.setAutoCommit(true);
                    // emallCon.setAutoCommit(true);
                	return;
                }
                
                System.out.println("dong` 603");
                // 등록담당자가 동일한지..체크
                // Kiểm ta xem có trùng với người phụ trách đã đăng ký trước rồi hay không
                if(urc.isDataExistOnSamePerson(d04,i02,con) !=0){
                	//message="이미 입력하신 담당자명으로 인증서등록이 되어 있습니다." 
					//		+"<br>동일한 담당자명으로 신규등록을 하실수 없으며<BR>"
					//		+"이용자 목록 화면에서 '인증서추가등록'버튼을 클릭하여 추가등록 하실 것을 권장합니다."
					//		+"<br>꼭 동일한 담당자명으로 신규등록을 하실분은<BR>콜센터로 문의해주시기 바랍니다.";
                	 message="Bạn đã đăng nhập bằng chứng nhận số với tên người phụ trách trùng với người đã có từ trước trong hệ thống."
		                    +"<br>Cùng một người phụ trách thì không thể đăng ký mới nữa.<BR>"
		                    +"Chúng tôi đề nghị bạn hãy đăng ký bổ sung chứng nhận số bằng cách nhấn nút 'Đăng ký bổ sung chứng nhận số' ở màn hình danh mục người dùng.."
		                    +"<br>Nếu nhất thiết phải đăng nhập dưới cùng một tên người phụ trách<BR>hãy tư vấn ở Call Center.";
                	 common.util.CommonMessage.printMsg("", "4", message, "", res);
                	con.setAutoCommit(true);
                    //	emallCon.setAutoCommit(true);
                	return;
                }

				// 2007.07.06  이광현  공사관리진행통보서 사용인증서 등록 관련 제어로직 추가	start
				if (oper.equals("d"))
				{
					if(urc.isDataExistOnUserCertD(d04,con) == 0){
						// message="최초 나라장터 이용자일 경우는 공사진행송수신 사용자로 인증서등록할 수 없습니다."
						//		+"<br>공사진행통보서 송수신 여부 체크를 하지 마시고 인증서 등록한 이후에 <BR>"
						//		+"다른 사용자로 등록하세요."
						//		+"<br>의문사항이 있으시면 콜센터 1588-0800으로 문의해주시기 바랍니다.";
						message="Trường hợp là người dùng đầu tiên của hệ thống mua sắm công điện tử thì không thể đăng ký làm người gửi nhận thông tin tiến độ thi công."
							+"<br>Đừng chọn vào tùy chọn gửi nhận thông báo về tiến độ thi công mà hãy đăng ký chứng nhận số <BR>"
							+"sau đó đăng ký thành người dùng khác."
							+"<br>Nếu có thắc mắc cần giải đáp, vui lòng gọi đến trung tâm tư vấn số 1588-0800.";
						common.util.CommonMessage.printMsg("", "4", message, "", res);
						con.setAutoCommit(true);
						return;
					}
				}
				// 2007.07.06  이광현  공사관리진행통보서 사용인증서 등록 관련 제어로직 추가	end
				 /*
				// 미반환 JDBC 에 대한 개선 2007-08-31
				if (oper.equals("x") || oper.equals("d") || oper.equals("c"))
				{
					grp = EXMS_GRP_Ins (oper, con);
				} else {
					grp = EXMS_GRP_Ins ("c", con);			// 혹시 모를 오류대비 (기존 소스내용을 반영) 2007-08-31 -	Nếu bạn không có lỗi trong vụ án (để phản ánh hiện có mã nguồn)
				}

               
				if (oper.equals("x"))
				{
					sql = "select usr_grp, count(*) "
                      + "from 사용_사용자 "
                      + "where 사용자구분 = 'x' and usr_grp like 'x%'"
                      + "group by usr_grp "
                      + "order by usr_grp desc ";

					psmt1 = con.prepareStatement(sql);
					rs = psmt1.executeQuery();
					
					if(rs.next()){
						grp       = rs.getString(1);
						grpcount  = rs.getString(2);
						
						if(Integer.parseInt(grpcount) >= GRPMAX) {
							grp = grp.substring(1,6);
							temp = Integer.parseInt(grp)+1;
							grp = "x"+formatcount.format(temp);
							
							sql = "insert into syn_T_EXMS_GRP (GRP_ID, GRP_NAME, GRP_DESC, RGST_DATE) values ( '"+grp+"','"+grp+"','자동생성그룹', sysdate )";
							
							psmt2 = con.prepareStatement(sql);
							psmt2.executeUpdate();
						}
					}else{
						sql = "insert into syn_T_EXMS_GRP (GRP_ID, GRP_NAME, GRP_DESC, RGST_DATE) values ('x00000','x00000','최초자동생성그룹',sysdate)";
						psmt2 = con.prepareStatement(sql);
						psmt2.executeUpdate();
						grp = "x00000";
					}
				} else if (oper.equals("d"))
				{
					sql = "select usr_grp, count(*) "
                      + "from 사용_사용자 "
                      + "where 사용자구분 = 'd' and usr_grp like 'd%'"
                      + "group by usr_grp "
                      + "order by usr_grp desc ";

					psmt1 = con.prepareStatement(sql);
					rs = psmt1.executeQuery();
					
					if(rs.next()){
						grp       = rs.getString(1);
						grpcount  = rs.getString(2);
						
						if(Integer.parseInt(grpcount) >= GRPMAX) {
							grp = grp.substring(1,6);
							temp = Integer.parseInt(grp)+1;
							grp = "x"+formatcount.format(temp);
							
							sql = "insert into syn_T_EXMS_GRP (GRP_ID, GRP_NAME, GRP_DESC, RGST_DATE) values ( '"+grp+"','"+grp+"','자동생성그룹', sysdate )";
							
							psmt2 = con.prepareStatement(sql);
							psmt2.executeUpdate();
						}
					}else{
						sql = "insert into syn_T_EXMS_GRP (GRP_ID, GRP_NAME, GRP_DESC, RGST_DATE) values ('d00000','d00000','최초자동생성그룹',sysdate)";
						psmt2 = con.prepareStatement(sql);
						psmt2.executeUpdate();
						grp = "d00000";
					}
				//} else {
				// 2007-08-31
				} else if (oper.equals("c")) {

					sql = "select usr_grp, count(*) "
						  + "from 사용_사용자 "
						  + "where 사용자구분 = 'c' and usr_grp like 'c%'"
						  + "group by usr_grp "
						  + "order by usr_grp desc ";

					psmt1 = con.prepareStatement(sql);
					rs = psmt1.executeQuery();
					
					if(rs.next()){
						grp       = rs.getString(1);
						grpcount  = rs.getString(2);
						
						if(Integer.parseInt(grpcount) >= GRPMAX) {
							grp = grp.substring(1,6);
							temp = Integer.parseInt(grp)+1;
							grp = "c"+formatcount.format(temp);
							
							sql = "insert into syn_T_EXMS_GRP (GRP_ID, GRP_NAME, GRP_DESC, RGST_DATE) values ( '"+grp+"','"+grp+"','자동생성그룹', sysdate )";
							
							psmt2 = con.prepareStatement(sql);
							psmt2.executeUpdate();
						}
					}else{
						sql = "insert into syn_T_EXMS_GRP (GRP_ID, GRP_NAME, GRP_DESC, RGST_DATE) values ('c00000','c00000','최초자동생성그룹',sysdate)";
						psmt2 = con.prepareStatement(sql);
						psmt2.executeUpdate();
						grp = "c00000";
					}
				}
				*/
				  
				id=urc.getUserUpcheID(d04,con);
				 
				if(i08.equals("Y")){
					urc.updateUserZzangReceive(d04,con);
				}	
				
				// 사용_사용자 insert
				urc.insertUseUser(id, oper, d04, i072, i02,  i01, i03, i05, i04, i06, i08, i09, grp, con);
				  
				 // 사용_인증서정보 insert
				urc.insertUseCert(id, DN1,DN2,CERT1,CERT2,CertFromDate,CertToDate,con);
				  

                // 마이페이지설정
                // 2006.10.09 마이페이지설정 부분 제거
                /*
                if (user_gubun.equals("c")) {

                    myPage.insertMypage(id, "c", con);

                    myPage.insertPageset(id, "M101", con);
                    
                    myPage.insertPageset(id, "BB01", con);

                    myPage.insertPageset(id, "BB02", con);

                    if (aCode != null) {

                        for(int i=0; i<aCode.length; i++) {
                            myPage.insertPageset(id, aCode[i], con);
                        }
                    }
                } */

                
                // 매핑코드 사업자등록번호
                /*
                mcode=urc.getMcode(d04,con);
                if(mcode==null){
                	Log.debug("UM_URV_UserB010c.java "+d04+" 에 대한 매핑정보를 구할 수 없음");
                	throw new Exception("매핑정보가 잘못되었습니다.G2B Call Center[1588-0800] 으로 문의 주십시요.");	
                }
                */                
                // 자료조회
                //              UM_COE_GTQ903[] ucg903=urc.getUpcheInfo(DN1,id,d04,mcode,con);
                
                // 자료입력

                //----  삭제대상....... 창렬
                // urc.insertEmallElectronicUserUpche(ucg903, i07, emallCon);
                //----  삭제대상....... 창렬

		        con.commit();
		        con.setAutoCommit(true);
		        
/*			
    2008.11.20    신경운   유통서버 동기화 호출 프로그램삭제
	                                문서유통 문서처리데몬 개선에 따라 사용자정보를 실시간 확인이 가능해져 인증서 신규/추가 등록시  등록된 정보를 따로 전달할필요가 없어짐)

		        PostCGI cgi = null;
		        try {
							cgi = new PostCGI("http://exms.g2b.go.kr:8081/servlet/usrRequest?id="+id+"&contype=HH&usrgrp="+grp+"&cmd=INS");
							cgi.postData("SUCCESS");
		        } catch(Exception e) {}

*/
				res.sendRedirect("/RA/UM_RAJ_ConuC030s.jsp?id="+id+"&oper="+oper);

			}catch(Exception exm){
				 try { if(con !=null) con.rollback(); }catch(Exception ex){}
				 try { if(con !=null) con.setAutoCommit(true); }catch(Exception ex){}
                 // try { if(emallCon !=null) emallCon.rollback(); }catch(Exception ex){}
                 // try { if(emallCon !=null) emallCon.setAutoCommit(true); }catch(Exception ex){}
				 Log.debug("UM_URV_UserB010c.java flag["+flag+"] : "+exm.toString());
				 common.util.CommonMessage.printMsg("", "", exm.getMessage(), null, res);
			}finally{
				try { if(con !=null) trx.close(); }catch(Exception exf){}
                // try { if(emallCon !=null) emallTrx.close(); }catch(Exception exf){}
			}	
		}
	}

	/**
	 *	사용자구분에 따른 사용_사용자 usr_grp 카운트하여 syn_T_EXMS_GRP Insert 처리  2007-08-31 추가
	 */
	/*
	private String EXMS_GRP_Ins (String oper, Connection con) throws Exception {

		String grp		= null;			 // 그룹명
		String grpcount = null;			 // 그룹내 등록된수
		int GRPMAX      = 200;			 // 그룹내 등록될수있는 최대수
		DecimalFormat formatcount = new DecimalFormat("00000"); 
		int temp        = 0;			 // 그룹id 계산위한 변수
		
		ResultSet			rs	= null;
		PreparedStatement psmt	= null;

		StringBuffer	sb  	= new StringBuffer(1024);
		String sql 				= null;
		int count				= 0;
		
		try{	
			sb.append("\n select usr_grp, count(*) 							")
			 	.append("\n from 사용_사용자									")
			 	.append("\n where 사용자구분 = ? and usr_grp like '"+oper+"%'	")
			 	.append("\n group by usr_grp								")
			 	.append("\n order by usr_grp desc 							");
			
			sb.append("\n select USER_GRP, count(*) 							")
			 	.append("\n from UM_USER									")
			 	.append("\n where USER_CLS = ? and USER_GRP like '"+oper+"%'	")
			 	.append("\n group by USER_GRP								")
			 	.append("\n order by USER_GRP desc 							");
			
			psmt = con.prepareStatement(sb.toString());
			psmt.setString(1,oper);
					
			rs = psmt.executeQuery();

			//Log.debug(this.getClass().getName()+".EXMS_GRP_Ins() sql:"+sb.toString());

			psmt.clearParameters();
					
			if(rs.next()){
				grp       = rs.getString(1);
				grpcount  = rs.getString(2);
				
				if(Integer.parseInt(grpcount) >= GRPMAX) {
					grp = grp.substring(1,6);
					temp = Integer.parseInt(grp)+1;
					grp = oper+formatcount.format(temp);	// oper값 (x:비축사용자, d:공사진행송수신, c:조달업체)
					
					sql = "insert into syn_T_EXMS_GRP (GRP_ID, GRP_NAME, GRP_DESC, RGST_DATE) values ( '"+grp+"','"+grp+"','자동생성그룹', sysdate )";

					//Log.debug(this.getClass().getName()+".EXMS_GRP_Ins() sql:"+sql);
					Log.debug(this.getClass().getName()+".EXMS_GRP_Ins() syn_T_EXMS_GRP 테이블 grp["+grp+"] insert 처리됨");

					if (psmt != null) try{ psmt.close();} catch (Exception e) {}
					
					psmt = con.prepareStatement(sql);
					psmt.executeUpdate();

					if(psmt.executeUpdate() != 1){
						Log.debug("oper["+oper+"]으로 syn_T_EXMS_GRP 테이블 grp["+grp+"] Insert되지 않았습니다.");
					}
				}
			}else{
				grp = oper+"00000";							// oper값 (x:비축사용자, d:공사진행송수신, c:조달업체)

				sql = "insert into syn_T_EXMS_GRP (GRP_ID, GRP_NAME, GRP_DESC, RGST_DATE) values ('"+grp+"','"+grp+"','최초자동생성그룹',sysdate)";

				//Log.debug(this.getClass().getName()+".EXMS_GRP_Ins() sql:"+sql);
				Log.debug(this.getClass().getName()+".EXMS_GRP_Ins() syn_T_EXMS_GRP 테이블 grp["+grp+"] insert 처리됨");

				if (psmt != null) try{ psmt.close();} catch (Exception e) {}

				psmt = con.prepareStatement(sql);
				psmt.executeUpdate();

				if(psmt.executeUpdate() != 1){
					Log.debug("oper["+oper+"]으로 syn_T_EXMS_GRP 테이블 grp["+grp+"] Insert되지 않았습니다.");
				}
			}
		} catch (SQLException sqle) {
            Log.debug(this.getClass().getName()+".EXMS_GRP_Ins():"+sqle.toString());
		} catch(Exception exf){
			Log.debug(this.getClass().getName()+".EXMS_GRP_Ins() oper["+oper+"]:"+exf.toString());
		} finally {
			if (rs != null) try{ rs.close(); } catch (Exception e) {};
			if (psmt != null) try{ psmt.close(); } catch (Exception e) {};
		}
		 return grp;
	}
*/
}
