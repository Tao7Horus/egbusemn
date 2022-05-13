/****************************************************************************/
/*                                                                          */
/*    Program ID    :     UM_ADV_GovrA010c02.java                             */
/*                                                                          */
/*    description   :     Dung cho dang ky bo sung chung nhan so nha thau  */
/*                                                                          */
/* 	Customizing Composer : thanhtv 2010.05.17                       */ 
/***************************************************************************/


package servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import secu.lib.MessageDigest;
import secu.lib.Secu;
import LOGIN.UM_Auth_Check;
import Mail.MailServer;
import beans.UM_URB_CERT;
import beans.UM_URB_CERT010;

import com.oreilly.servlet.ParameterParser;
import common.ComStr;
import common.Log;
import common.Trx;
import common.util.CommUtil;
import common.util.CommonMessage;

public class UM_ADV_GovrA010c02 extends HttpServlet  {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    	req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html;charset=UTF-8");

		Trx                 trx     = null;
		Connection          con     = null;

		ParameterParser psr = new ParameterParser(req);
		UM_URB_CERT010 urc010=new UM_URB_CERT010();
		CommUtil com=new CommUtil();

		String G2BCODE          = psr.getStringParameter("G2BCODE"                  ,"");   // 행정표준코드
		String CNAME            = psr.getStringParameter("CNAME"                    ,"");   // 기관명(한글)
		String IDENT            = psr.getStringParameter("IDENT"                    ,"");   // 대표자명
		String KNAME            = psr.getStringParameter("KNAME"                    ,"");   // 기관명(영문)
		String IDENTJUMIN       = psr.getStringParameter("IDENTJUMIN"               ,"");   // 대표자 주민등록번호
		String ZIPNO            = psr.getStringParameter("ZIPNO"                    ,"");   // 우편번호
		String COMNO            = psr.getStringParameter("COMNO"                    ,"");   // 사업자등록번호
		String ADDRS            = psr.getStringParameter("ADDRS"                    ,"");   // 주소
		String ADDRESS2         = psr.getStringParameter("ADDRESS2"                 ,"");   // 나머지주소
		String FAX              = psr.getStringParameter("FAX"                      ,"");   // 팩스번호
		String MYNAME           = psr.getStringParameter("MYNAME"                   ,"");   // 성명
		String OFFICEDE         = psr.getStringParameter("OFFICEDE"                 ,"");   // 부서명
		
		String JUMIN            = psr.getStringParameter("real_jumin_chk_yn1"  		,"");   
		if ("".equals(JUMIN)) JUMIN = psr.getStringParameter("JUMIN"   ,""); // 주민등록번호 real_jumin_chk_yn1
		String TEL              = psr.getStringParameter("TEL"                      ,"");   // 전화번호
		String EMAIL            = psr.getStringParameter("EMAIL"                    ,"");   // E-Mail
		String HP               = psr.getStringParameter("HP"                       ,"");   // 핸드폰
		String smsCheck         = psr.getStringParameter("smsCheck"                       ,"");   // SMS수신여부
		String USRID            = psr.getStringParameter("USRID"                    ,"");   // 사용자ID
		String CERT_ORG         = psr.getStringParameter("CERT_ORG"                 ,"");   // 공인인증기관
		String HOME             = psr.getStringParameter("HOME"                     ,"");   // 홈페이지주소
		String STR              = psr.getStringParameter("STR"                      ,"");   //
		String STR1             = psr.getStringParameter("STR1"                     ,"");   //
		String recept           = psr.getStringParameter("recept"                   ,"");   // 접수번호
		String back             = psr.getStringParameter("back"                     ,"");   // 반려사유
		String dependCode       = psr.getStringParameter("dependCode"               ,"");   // 요청인가코드
		
		String flag             = psr.getStringParameter("flag"                     ,"");   // 구분자
		String approval         = psr.getStringParameter("approval"                 ,"");   // 승인여부
		
		String branchOffi1      = psr.getStringParameter("branchOffi1"              ,"");   // 승인지청

		// 2007.01.31 비밀번호변경여부, 비밀번호변경ID, 비밀번호 추가 	Cho dù bạn thay đổi mật khẩu của bạn, thay đổi mật khẩu ID, mật khẩu, thêm
		String passwdmodOK      = psr.getStringParameter("passwdmodOK"      		,"");   //  비밀번호변경여부 
		String gID				= psr.getStringParameter("gID"      				,"");   //  비밀번호변경ID
		String i07				= psr.getStringParameter("i07"      				,"");   //  비밀번호 
        String i072				= "";														//  비밀번호암호화 import secu.lib.*; 추가
		
		String count            = "";
		String approvalCode     = "";
		
		// 인증서 참조번호, 인가코드, 고유명 전송 ** 시작 	Giấy chứng nhận số tham chiếu, hoặc mã, Giao thông Vận tải goyumyeong ** Bắt đầu
		String dn               = psr.getStringParameter("dn"           			,"");   //  DN 정보
		String refername        = psr.getStringParameter("refername"   				,"");   //  참조번호
		String refercode        = psr.getStringParameter("refercode"    			,"");   //  인가코드
		String comNo            = psr.getStringParameter("comNo"      				,"");   //
		
		String g2bCode          = psr.getStringParameter("g2bCode"      			,"");   //  g2bCode
		if (g2bCode.equals("")){ g2bCode= psr.getStringParameter("g2bCode"      			,"");   }//  Nhà thầu
		// Adding in 02/06/2009
		String cert_cls          = psr.getStringParameter("CERT_CLS"      			,"");   //  cert_cls - Phân loại cấp Chứng nhận số
		

		Secu secu = new Secu(Secu.UM);
        /////////// 패 스 워 드   암 호 화 ////////////////////////////////
        String strMData1 = null;
        MessageDigest md = new MessageDigest(secu);

		if ("Y".equals(passwdmodOK)) {        
			i072  = md.create( i07 );
		}
        ///i072 = "i072.PASSWORD_MOD_ID";
        /////////// 패 스 워 드   암 호 화 ////////////////////////////////


	    String HP1 = com.StandardPhonNumber(HP);
	  

        if(flag.equals("Insert")) {
        	
        	try{
            	trx = new Trx(this, "USEMN");
            	con = trx.getConnection();
            	con.setAutoCommit(false);
            	
            	recept=urc010.getMaxReceptionID(con);
            	approvalCode = recept.substring(2, recept.length());
                approvalCode = approvalCode+USRID;
				CNAME = ComStr.replace(CNAME,"·","");
				CNAME = CNAME;
				CNAME = ComStr.replace(CNAME,"ㆍ","");
                
                // 사용_접수공공기관인증서 테이블 insert	// Sử dụng chèn bảng _ nhận được giấy chứng nhận  
				// 2007.01.31 비밀번호변경여부, 비밀번호변경ID, 비밀번호암호화 추가    // 2007/01/31 để thay đổi mật khẩu của bạn, thay đổi mật khẩu ID, thêm vào mật khẩu mã hóa
				
				Log.debug("recept: " + recept);
				
				if (cert_cls.equals("")){
					urc010.insertReception(	recept, G2BCODE, CNAME, KNAME, IDENT,
							IDENTJUMIN, ZIPNO, ADDRS, ADDRESS2, COMNO,
							FAX, MYNAME, OFFICEDE, JUMIN, TEL,
							EMAIL, HP, USRID, CERT_ORG, HOME,
							approvalCode, branchOffi1, passwdmodOK, gID, i072, smsCheck, con);
					
				} else if (cert_cls.equals("s") || cert_cls.equals("S")){
					urc010.insertReception(	recept, G2BCODE, CNAME, KNAME, IDENT,
							IDENTJUMIN, ZIPNO, ADDRS, ADDRESS2, COMNO,
							FAX, MYNAME, OFFICEDE, JUMIN, TEL,
							EMAIL, HP, USRID, CERT_ORG, HOME,
							approvalCode, branchOffi1, passwdmodOK, gID, i072, smsCheck, "S", con);
				}
				
				con.commit();
                con.setAutoCommit(true);
                
                Log.info("DANG KY THEM CTS THANH CONG ---  GUI EMAIL START >>> ");
               	try {
                   	new MailServer().sendRegistrationExtEmailToNhaThauStep1(EMAIL, MYNAME, CNAME, G2BCODE, approvalCode);
                   	Log.info("DANG KY THEM CTS THANH CONG ---  GUI EMAIL END >>> ");
               	} catch(Exception e){
               		Log.info("DANG KY THEM CTS KHONG LAY DUOC APPROVE_REQ_CD >> EXCEPTION >> " + e.getMessage() + "KHONG GUI EMAIL END >>> ");
               	}
                
				res.sendRedirect( "/AD/UM_ADJ_GovrL010s01.jsp?recept="+recept+"&approvalCode="+approvalCode+"&G2BCODE="+G2BCODE);								
			
            	//  res.sendRedirect("/jsp/AD/UM_ADJ_GovrL010s.jsp?recept="+recept+"&approvalCode="+approvalCode+"&G2BCODE="+G2BCODE);
            }catch(Exception exm){
            	try { if(con !=null) con.rollback(); }catch(Exception ex){}
			 	try { if(con !=null) con.setAutoCommit(true); }catch(Exception ex){}
			 	Log.debug("UM_ADV_GovrA010c02.java flag["+flag+"] : "+exm.toString());
			 	CommonMessage.printMsg("", "4", exm.getMessage(),"", res);
            }finally{
            	try { if(con !=null) trx.close(); }catch(Exception exf){}
            }	
        }else if(flag.equals("approval")){
        	
        	UM_Auth_Check uac=null;
	        UM_URB_CERT   urc    = new UM_URB_CERT();
  			
        	try{
            	uac=new UM_Auth_Check(req, res);
  				// 권한체크[기관 승인자만 사용]  kiểm tra quyền truy cập [được sử dụng chỉ có thẩm quyền của Cơ quan nhà nước]
            	String getID ;
            	if (cert_cls.toLowerCase().equals("s")){
            		uac.checkCookie("a",null,null);
            	}else{
      				uac.checkCookie("k",null,null);
            	}
            	
            	getID = uac.getID();
        		//String getID ="admnID";
        		
            	trx = new Trx(this, "USEMN");
            	con = trx.getConnection();
            	con.setAutoCommit(false);
        	
        		if(approval.equals("admit")){
        			
        			if (cert_cls.toLowerCase().equals("s")){
        				if(urc010.isDataExistOnEnterMaster(COMNO,con) == 0){
            				String message="Không thể thực hiện phê duyệt chứng nhận số cho các Doanh nghiệp chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.";
            				CommonMessage.printMsg("", "", message,null, res);
            				return;
            			}	
        			}else{
        				// 사용_공공기관마스터에 존재하지 않는 기관 Sử dụng chủ không tồn tại trong cơ sở giáo dục công
            			if(urc010.isDataExistOnGigwanMaster(G2BCODE,COMNO,con) == 0){
            			//	String message="공공기관마스터 테이블에 자료가 없거나, 기관코드와 사업자등록번호가 일치하는 않는 경우입니다.<br>승인처리할 수 없습니다.<br>자료 확인 바랍니다.";
            				String message="Không thể thực hiện phê duyệt chứng nhận số cho các cơ quan chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.";
            				CommonMessage.printMsg("", "", message,null, res);
            				return;
            			}	
        			}
        				
        			//사용_접수공공기관인증서 update - Cập nhật Chứng chỉ số
        			urc010.updateReception("Y","",recept,G2BCODE,dependCode,getID ,con);      
        			
        			if (cert_cls.toLowerCase().equals("s")){
        				
        			}else{
        				//사용_접수공공기관인증서 update
            			urc010.updateKNAME(G2BCODE,KNAME,con); 				
        			}
        				

				

					//사용_사용자.비밀번호 update (2007.01.31)
					if ("Y".equals(passwdmodOK)) {
						urc.updateUseUserPasswd(i07,gID,con);
					}

        			con.commit();
            	    con.setAutoCommit(true);

					//new MasterMail().CertSendMail(CNAME, G2BCODE, recept, dependCode, EMAIL, "A", "");


					if (smsCheck.equals("Y")) {
				       // new  MasterSMS().callSms(uac.getID(), COMNO, HP1, "CA", "");
					}

        			
					CommonMessage.printMsg("", "5", "Xử lý phê duyệt xong.","", res);
					//CommonMessage.printMsg("", "5", "승인처리되었습니다.","", res);
        			
        		}else if(approval.equals("deny")){
        			
        			if (cert_cls.toLowerCase().equals("s")){
        				if(urc010.isDataExistOnEnterMaster(COMNO,con) == 0){
        					String message="Không thể thực hiện phê duyệt chứng nhận số cho các Doanh nghiệp chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.";
            				CommonMessage.printMsg("", "", message,null, res);
            				return;
        				}
        			}else{
	        			// 사용_공공기관마스터에 존재하지 않는 기관 Sử dụng chủ không tồn tại trong cơ sở giáo dục công
	        			if(urc010.isDataExistOnGigwanMaster(G2BCODE,COMNO,con) == 0){
	        				String message="Không thể thực hiện phê duyệt chứng nhận số cho các cơ quan chưa đăng kí<br> Xác nhận đăng kí cơ quan trong menu Đăng kí cơ quan/ Tra cứu<br> sau đó,. nếu bạn thực hiện phê duyệt thì đồng thời đăng kí chứng nhận số cũng được thực hiện phê duyệt.";
        					//String message="미등록된 공공기관에 대해서는 인증서를 반려처리 <br> 할 수 없습니다. <br>기관등록신청/조회 메뉴에서 기관등록신청을 확인 <br>하신후 반려처리하시면 동시에 인증서신청도 반려 <br>처리됩니다.";
	        				CommonMessage.printMsg("", "", message,null, res);
	        				return;
	        			}	        				
        			}
        								
					//사용_접수공공기관인증서 update
        			urc010.updateReception("D",back,recept,G2BCODE,dependCode,getID,con);
            	    
            	    con.commit();
            	    con.setAutoCommit(true);

					//new MasterMail().CertSendMail(CNAME, G2BCODE, recept, dependCode, EMAIL, "D", back);

					/*if (smsCheck.equals("Y")) {
				        new  MasterSMS().callSms(uac.getID(), COMNO, HP1, "CD","");
					}*/
            	    
					CommonMessage.printMsg("", "5", "Đã xử lý trả về.","", res);
					// CommonMessage.printMsg("", "5", "반려처리되었습니다.","", res);
					
        		}else if(approval.equals("modifyCERT")){
     			     								
					//사용_접수공공기관인증서.공인인증기관 update
        			urc010.updateCERT_ORG(CERT_ORG,recept,G2BCODE,dependCode,con);
            	    
            	    con.commit();
            	    con.setAutoCommit(true);
            	    
            	    CommonMessage.printMsg("", "5", "Cơ quan cấp chứng nhận đã được sửa.","", res);// CommonMessage.printMsg("", "5", "공인인증기관이 수정등록되었습니다.","", res);
        		}
        	}catch(Exception exm){
            	try { if(con !=null) con.rollback(); }catch(Exception ex){}
			 	try { if(con !=null) con.setAutoCommit(true); }catch(Exception ex){}
			 	Log.debug("UM_ADV_GovrA010c02.java flag["+flag+"] : "+exm.toString());
			 	CommonMessage.printMsg("", "", exm.getMessage(),null, res);
            }finally{
            	try { if(con !=null) trx.close(); }catch(Exception exf){}
            }	
        }else if(flag.equals("DNSave")){
        	
        	try{
            	trx = new Trx(this, "USEMN");
            	con = trx.getConnection();
            	con.setAutoCommit(false);

				// 참조번호, 인가코드 발급안되었을 경우 발급여부 Y update안하고 오류 내역 표시 (2007.02.16)
			 	// 참조번호, 인가코드 로그제거 2007-07-26
            	// Tham khảo số, nếu vấn đề được hay không mã số, không phải là một Y cập nhật hiển thị các lỗi trong lịch sử (2007/02/16)
            	// Tham khảo số lượng, hoặc loại bỏ các mã số đăng nhập 2007/07/26
            	if (!refername.equals("") || !refercode.equals("") ) {
					//사용_접수공공기관인증서 update
					urc010.updateReceptionCert(dn,refername,refercode,comNo,g2bCode,dependCode,con);
						
					con.commit();
					con.setAutoCommit(true);
					
					res.sendRedirect("/AD/UM_ADJ_GovrL025s02.jsp?g2bCode="+g2bCode+"&dependCode="+refercode+"&saupNo="+comNo);
					//res.sendRedirect("/jsp/AD/UM_ADJ_GovrL025s.jsp?g2bCode="+g2bCode+"&dependCode="+dependCode+"&saupNo="+comNo);
				}				
        	}catch(Exception exm){
            	try { if(con !=null) con.rollback(); }catch(Exception ex){}
			 	try { if(con !=null) con.setAutoCommit(true); }catch(Exception ex){}
			 	Log.debug("UM_ADV_GovrA010c02.java flag["+flag+"] : "+exm.toString());
			 	CommonMessage.printMsg("", "4", exm.getMessage(),"", res);
            }finally{
            	try { if(con !=null) trx.close(); }catch(Exception exf){}
            }
        }else if(flag.equals("Submit")){	// 로그인하지 않은 경우에 approval = modifyCERT 기능을 flag = Submit 으로 처리  2007-03-09
        	
        	try{
            	trx = new Trx(this, "USEMN");
            	con = trx.getConnection();
            	con.setAutoCommit(false);

			 	Log.debug("UM_ADV_GovrA010c02.java CERT_ORG["+CERT_ORG+"] ");
			 	Log.debug("UM_ADV_GovrA010c02.java recept["+recept+"] ");
			 	Log.debug("UM_ADV_GovrA010c02.java G2BCODE["+G2BCODE+"] ");
			 	Log.debug("UM_ADV_GovrA010c02.java dependCode["+dependCode+"] ");
        			     								
				//사용_접수공공기관인증서.공인인증기관 update
        		urc010.updateCERT_ORG(CERT_ORG,recept,G2BCODE,dependCode,con);
            	    
            	con.commit();
            	con.setAutoCommit(true);
            	    
            	CommonMessage.printMsg("", "5", "Cơ quan cấp chứng nhận đã được sửa.","", res);  // CommonMessage.printMsg("", "5", "공인인증기관이 수정등록되었습니다.","", res);


        	}catch(Exception exm){
            	try { if(con !=null) con.rollback(); }catch(Exception ex){}
			 	try { if(con !=null) con.setAutoCommit(true); }catch(Exception ex){}
			 	Log.debug("UM_ADV_GovrA010c02.java flag["+flag+"] : "+exm.toString());
			 	CommonMessage.printMsg("", "4", exm.getMessage(),"", res);
            }finally{
            	try { if(con !=null) trx.close(); }catch(Exception exf){}
            }
        }
    }
}
