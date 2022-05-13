<%--
/************************************************************************/
/*                                                                      */
/*    Program ID    :     UM_RAM_Sending.jsp	            			*/
/*                                                                      */
/*    description   :     변경된 공공기관마스터 정보를 사용자에게 송신	*/
/*                                                                      */
/************************************************************************/
/*  최초생성         2003.12.13          민   정                        */
/************************************************************************/
--%>

<%@page language="java" %>
<%@page contentType="text/html; charset=EUC-KR" %>
<%@ page errorPage="/jsp/common/jspToError.jsp?type=0&url=&message=" %>

<%@page import="java.io.*, java.util.*,java.net.*, beans.*, servlets.*, entity.*, common.*, common.util.*, LOGIN.*, g2b.sso.*" %>

<%
	String msg = null;
	ASO aso = null;

	try {
		aso = new ASO(request,response);
	} catch ( Exception e ) {
		e.printStackTrace();
		msg = "접근 권한이 없거나 로그인 정보를 확인할 수 없습니다.";
	}

	if ( aso != null && !(aso.getBUSEO().equals("060") || aso.getBUSEO().equals("064")) ) { // 정보기획팀, 정보관리팀
		msg = "업무에 접근할 수 있는 부서가 아닙니다.";
	}

	if ( msg != null ) {
		out.println("<html>");
		out.println("<script language=javascript>");
		out.println("   alert('"+msg+"');");
		out.println("   top.location.href='http://www.g2b.go.kr:8070/jsp/RA/master_login.jsp';");
		out.println("</script>");
		out.println("</html>");
	}
%>

<jsp:useBean id="ctl"	class="beans.UM_RAM_RcvCodeList" scope="page" />
<jsp:useBean id="comUtil" class="common.util.CommUtil" scope="page" />
<jsp:useBean id="comWeb" class="common.WebUtil" scope="page" />
<jsp:useBean id="comSer" class="common.util.CommServer" scope="page" />
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />

<%
  /* 권한체크 및 아이디 가져오기
  UM_Auth_Check uac=new UM_Auth_Check(request, response);
  uac.checkCookie("k",null,null);
  
  //로그인체크
    SSO  sso = new SSO(pageContext);
	sso.checkLogin();                    // Login확인
	String LoginID = sso.getID();        // 사용자id
  */
	Cookie cookies[]   = request.getCookies();
	String SABUN = null;   
//	ASO aso=new ASO(request,response);
	SABUN=aso.getSABUN();
    session.setMaxInactiveInterval(3600);
    session.setAttribute("Sid", SABUN);
//	if(cookies != null)
//	{
//		for(int i = 0; i< cookies.length; i++)
//		{
//			if(cookies[i].getName().equals("SABUN"))
//			{
//				SABUN = URLDecoder.decode(cookies[i].getValue());
//			    session.setMaxInactiveInterval(3600);
//			    session.setAttribute("Sid", SABUN);
//			}
//		}
//	}
	String LoginID = (String) session.getAttribute("Sid");
    
  String urlHistory	=comUtil.retSpace(request.getParameter("urlHistory"));
	if(urlHistory.indexOf("*") >= 0) {
		for(int i=0; i < urlHistory.length(); i++) {
			String tmp = urlHistory.substring(i, i+1);
			if(tmp.equals("*")) {
				urlHistory = urlHistory.substring(0, i) + "&" + urlHistory.substring(i+1);
			}
		}
	}

  String sndFlag = comUtil.retSpace(request.getParameter("sndFlag"));  //송신여부 확인
  if(sndFlag.equals("")) sndFlag="0";

  String manualCode = comUtil.retSpace(request.getParameter("manualCode"));
  String manualChg = comUtil.retSpace(request.getParameter("manualChg"));
  String manualDml = comUtil.retSpace(request.getParameter("manualDml"));
  String gccCodes = comUtil.retSpace(request.getParameter("gccCodes"));
  String govChgs = comUtil.retSpace(request.getParameter("govChgs"));
  String govDmls = comUtil.retSpace(request.getParameter("govDmls"));

  String multiSend="";

  if((gccCodes==null)||(gccCodes.equals(""))) multiSend="N";
  else multiSend="Y";

  String[] sendStr = new String[15];
  String imsiName="";
  String imsiCode="";
  String[] govPrevCode = new String[15];
  String[] govPrevName = new String[15];
  String[] chgflag = new String[15];	// 변경구분 - Added in 2004.02.20
  int sendCount=0;
  int idx=0;
  int num=0;
  int multiCount=0;
  String totalGovCode="";
  String totalString="";
  String totalChgFlag="";		// 변경구분 - Added in 2004.02.20
  String totGovCode="";
  String totString="";
  String totChgFlag="";			// 변경구분 - Added in 2004.02.20

  int totalUsrCnt =0;
  int totUsrCnt =0;
  int getCnt = 0;
  Vector govCode = new Vector();
  Vector govChg = new Vector();
  Vector govDml = new Vector();
        
  if ( multiSend.equals("Y")){ //선택하여 들어온 문서송신 건수

		while(true) {
			govCode.addElement(gccCodes.substring(0,gccCodes.indexOf("^")));
			govChg.addElement(govChgs.substring(0,govChgs.indexOf("^")));
			govDml.addElement(govDmls.substring(0,govDmls.indexOf("^")));

			gccCodes = gccCodes.substring(gccCodes.indexOf("^")+1);
			govChgs = govChgs.substring(govChgs.indexOf("^")+1);
			govDmls = govDmls.substring(govDmls.indexOf("^")+1);

			multiCount=multiCount+1;

			if(gccCodes.indexOf("^") < 0) {
				break;
			}
		}	
	
		for( int mul=0;mul<multiCount;mul++){
           
		   manualCode = (String) govCode.get(mul);
           manualChg = (String) govChg.get(mul);
           manualDml = (String) govDml.get(mul);
		   if (manualDml.equals("INS")||manualDml.equals("UPD")){  
			   if (manualChg.equals("03")){
				    imsiName = ctl.getgoName2(manualCode);
				    govPrevCode[num] = ctl.getPrevOrgCode2(manualCode);
					govPrevName[num] = ctl.getgoName(govPrevCode[num]);	
                    sendStr[num] = "이전 공공기관 ["+govPrevCode[num]+"] "+govPrevName[num]+" 에서 \r\n"
					        + "["+manualCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
					chgflag[num] = manualChg;
					idx=1;
			   }else if (manualChg.equals("04")){
                    imsiName = ctl.getgoName2(manualCode);
                    govPrevCode[num] = ctl.getPrevOrgCode2(manualCode);
                    govPrevName[num] = ctl.getgoName(govPrevCode[num]);	
                    sendStr[num] = "이전 공공기관 ["+govPrevCode[num]+"] "+govPrevName[num]+" (은)는 \r\n"
					        + "["+govPrevCode[num]+"] "+govPrevName[num]+" (와)과 \r\n"
					        + "["+manualCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
					chgflag[num] = manualChg;
					idx=1;
			   }else if (manualChg.equals("05")){
                    govPrevCode[num] = ctl.getPrevOrgCode2(manualCode);
                    govPrevName[num] = ctl.getgoName(govPrevCode[num]);	
                    imsiCode= ctl.getOrgCode(govPrevCode[num]);
					sendStr[num] = "이전 공공기관 ["+govPrevCode[num]+"] "+govPrevName[num]+" (은)는 \r\n";
					for (int k=0; k<(imsiCode.length()/7);k++){
						 String tmpCode = imsiCode.substring(k*7,k*7+7);
						 String tmpName = ctl.getgoName2(tmpCode);
						 if((k+1)==(imsiCode.length()/7)){
                             sendStr[num]= sendStr[num] + "["+tmpCode+"] "+tmpName+" (으)로 변경되었습니다. \r\n";
						 } else {
                             sendStr[num]= sendStr[num] + "["+tmpCode+"] "+tmpName+" (와)과 \r\n";
						 }
					}
					chgflag[num] = manualChg;
					idx=1;
			   }else if (manualChg.equals("06")){
				    imsiName = ctl.getgoName2(manualCode);
				    imsiCode = ctl.getPrevOrgCode2(manualCode);
                    for (int k=0; k<(imsiCode.length()/7);k++){
						 govPrevCode[num+k] = imsiCode.substring(k*7,k*7+7);
						 govPrevName[num+k] = ctl.getgoName(govPrevCode[num+k]);
						 sendStr[num+k]="이전 공공기관 ["+govPrevCode[num+k]+"] "+govPrevName[num+k]+" (은)는 \r\n"
							       +"["+manualCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
						 idx=k+1;
						 chgflag[num+k] = manualChg;
					}
			   }else if (manualChg.equals("07")){
				    imsiName = ctl.getgoName2(manualCode);
				    imsiCode = ctl.getPrevOrgCode2(manualCode);
                    for (int k=0; k<(imsiCode.length()/7);k++){
						 String checkCode= imsiCode.substring(k*7,k*7+7);
						 int j=0;
					  if (!checkCode.equals(manualCode)){ 
						  govPrevCode[num+j] = checkCode;	 
						  govPrevName[num+j] = ctl.getgoName(govPrevCode[num+j]);
						  sendStr[num+j]="이전 공공기관 ["+govPrevCode[num+j]+"] "+govPrevName[num+j]+" (은)는 \r\n"
						    		+"["+manualCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
						  idx=j+1;
						  chgflag[num+j] = manualChg;
						  j=j+1;
					  }
					}
			   } 
		  }else if (manualDml.equals("DEL")){ 
			   if(manualChg.equals("02")) {
				    govPrevCode[num] = manualCode;
					govPrevName[num] = ctl.getgoName(govPrevCode[num]);
					sendStr[num] = "공공기관 ["+govPrevCode[num]+"] "+govPrevName[num]+" 은(는) 폐지되었습니다. \r\n";
					idx=1;
					chgflag[num] = manualChg;
			   }else if (manualChg.equals("03")){
					govPrevCode[num] = manualCode;
					govPrevName[num] = ctl.getgoName(govPrevCode[num]);
					imsiCode = ctl.getOrgCode(manualCode);
					imsiName = ctl.getgoName2(imsiCode);
					if(imsiName==null || imsiName.equals("")) imsiName = ctl.getgoName(imsiCode);
                    sendStr[num] = "이전 공공기관 ["+govPrevCode[num]+"] "+govPrevName[num]+" 에서 \r\n"
					        + "["+imsiCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
					idx=1;
					chgflag[num] = manualChg;
			   }else if (manualChg.equals("05")){
					govPrevCode[num] = manualCode;
					govPrevName[num] = ctl.getgoName(govPrevCode[num]);
					imsiCode = ctl.getOrgCode(govPrevCode[num]);
                    sendStr[num] = "이전 공공기관 ["+govPrevCode[num]+"] "+govPrevName[num]+" (은)는 \r\n";
					for (int k=0; k<(imsiCode.length()/7);k++){
						 String tmpCode2 = imsiCode.substring(k*7,k*7+7);
						 String tmpName2 = ctl.getgoName2(tmpCode2);
						 if((k+1)==(imsiCode.length()/7)){
                             sendStr[num]= sendStr[num] + "["+tmpCode2+"] "+tmpName2+" (으)로 변경되었습니다. \r\n";
						 } else {
                             sendStr[num]= sendStr[num] + "["+tmpCode2+"] "+tmpName2+" (와)과 \r\n";
						 }
					}
					idx=1;
					chgflag[num] = manualChg;
			   }else if (manualChg.equals("06")){
				    String imsiOrgCode = ctl.getOrgCode(manualCode);
					imsiName = ctl.getgoName2(imsiOrgCode);
				    imsiCode = ctl.getPrevOrgCode2(imsiOrgCode);
                    for (int k=0; k<(imsiCode.length()/7);k++){
						 govPrevCode[num+k] = imsiCode.substring(k*7,k*7+7);
					     govPrevName[num+k] = ctl.getgoName(govPrevCode[num+k]);
						 sendStr[num+k]="이전 공공기관 ["+govPrevCode[num+k]+"] "+govPrevName[num+k]+" (은)는 \r\n"
							       +"["+imsiOrgCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
						 idx=1+k;
						 chgflag[num+k] = manualChg;
					}
			   }else if (manualChg.equals("07")){
				    String imsiOrgCode2 = ctl.getOrgCode(manualCode);
					imsiName = ctl.getgoName2(imsiOrgCode2);
				    imsiCode = ctl.getPrevOrgCode2(imsiOrgCode2);
                    for (int k=0; k<(imsiCode.length()/7);k++){
						 String checkCode2= imsiCode.substring(k*7,k*7+7);
						 int j=0;
					  if (checkCode2.equals(manualCode)) {
						  govPrevCode[num+j] = checkCode2;	 
						  govPrevName[num+j] = ctl.getgoName2(govPrevCode[num+j]);
//						  sendStr[num+j]="이전 공공기관 ["+govPrevCode[num+j]+"] "+govPrevName[num+j]+" (은)는 \r\n"
//						    		+"["+manualCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
						  sendStr[num+j]="이전 공공기관 ["+govPrevCode[num+j]+"] "+govPrevName[num+j]+" (은)는 \r\n"
						    		+"["+imsiOrgCode2+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
						  idx=1+j;
						  chgflag[num+j] = manualChg;
						  j=j+1;
					  }
					}
			   } 		  
		  }
	   	  for ( int i=num;i<num+idx;i++) {
		      getCnt = ctl.Usrcount(govPrevCode[i]);					//수신기관코드의 사용자수계산
	   	      totUsrCnt = totUsrCnt + getCnt;
			  totGovCode = totGovCode + govPrevCode[i]+"^";          //수신기관코드 리스트
			  totString = totString + sendStr[i]+"^";                  //송신내용 리스트
			  totChgFlag = totChgFlag + chgflag[i]+"^";
		  }
//		  totalUsrCnt = totalUsrCnt + totUsrCnt;
		  totalUsrCnt = totUsrCnt;
//		  totalGovCode = totalGovCode + totGovCode;
		  totalGovCode = totGovCode;
		  totalString = totalString+ totString;
		  totalString = totString;
//		  totalChgFlag = totalChgFlag + totChgFlag;
		  totalChgFlag = totChgFlag;
		  sendCount=sendCount+idx;
          num=num+idx;
      }//end for

  }else{               //직접 수정한 후의 송신화면
        
		 if (manualDml.equals("INS")||manualDml.equals("UPD")){  
			   if (manualChg.equals("03")){
				    imsiName = ctl.getgoName2(manualCode);
				    govPrevCode[0] = ctl.getPrevOrgCode2(manualCode);
					govPrevName[0] = ctl.getgoName(govPrevCode[0]);	
                    sendStr[0] = "이전 공공기관 ["+govPrevCode[0]+"] "+govPrevName[0]+" 에서 \r\n"
					        + "["+manualCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
					sendCount=1;
					chgflag[0] = manualChg;
			   }else if (manualChg.equals("04")){
                    imsiName = ctl.getgoName2(manualCode);
                    govPrevCode[0] = ctl.getPrevOrgCode2(manualCode);
                    govPrevName[0] = ctl.getgoName(govPrevCode[0]);	
                    sendStr[0] = "이전 공공기관 ["+govPrevCode[0]+"] "+govPrevName[0]+" (은)는 \r\n"
					        + "["+govPrevCode[0]+"] "+govPrevName[0]+" (와)과 \r\n"
					        + "["+manualCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
					sendCount=1;
					chgflag[0] = manualChg;
			   }else if (manualChg.equals("05")){
                    govPrevCode[0] = ctl.getPrevOrgCode2(manualCode);
                    govPrevName[0] = ctl.getgoName(govPrevCode[0]);	
                    imsiCode= ctl.getOrgCode(govPrevCode[0]);
					sendStr[0] = "이전 공공기관 ["+govPrevCode[0]+"] "+govPrevName[0]+" (은)는 \r\n";
					for (int k=0; k<(imsiCode.length()/7);k++){
						 String tmpCode = imsiCode.substring(k*7,k*7+7);
						 String tmpName = ctl.getgoName2(tmpCode);
						 if((k+1)==(imsiCode.length()/7)){
                             sendStr[0]= sendStr[0] + "["+tmpCode+"] "+tmpName+" (으)로 변경되었습니다. \r\n";
						 } else {
                             sendStr[0]= sendStr[0] + "["+tmpCode+"] "+tmpName+" (와)과 \r\n";
						 }
					}
					chgflag[0] = manualChg;
					sendCount=1;
			   }else if (manualChg.equals("06")){
				    imsiName = ctl.getgoName2(manualCode);
				    imsiCode = ctl.getPrevOrgCode2(manualCode);
                    for (int k=0; k<(imsiCode.length()/7);k++){
						 govPrevCode[k] = imsiCode.substring(k*7,k*7+7);
						 govPrevName[k] = ctl.getgoName(govPrevCode[k]);
						 sendStr[k]="이전 공공기관 ["+govPrevCode[k]+"] "+govPrevName[k]+" (은)는 \r\n"
							       +"["+manualCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
						 sendCount=1+k;
						 chgflag[k] = manualChg;
					}
			   }else if (manualChg.equals("07")){
				    imsiName = ctl.getgoName2(manualCode);
				    imsiCode = ctl.getPrevOrgCode2(manualCode);
                    for (int k=0; k<(imsiCode.length()/7);k++){
						 String checkCode= imsiCode.substring(k*7,k*7+7);
						 int j=0;
					  if (!checkCode.equals(manualCode)){ 
						  j=j+1;
						  govPrevCode[j] = checkCode;	 
						  govPrevName[j] = ctl.getgoName(govPrevCode[j]);
						  sendStr[j]="이전 공공기관 ["+govPrevCode[j]+"] "+govPrevName[j]+" (은)는 \r\n"
						    		+"["+manualCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
						  sendCount=1+j;
						  chgflag[j] = manualChg;
					  }
					}
			   } 
		  }else if (manualDml.equals("DEL")){ 
			   if(manualChg.equals("02")) {
				    govPrevCode[0] = manualCode;
					govPrevName[0] = ctl.getgoName(govPrevCode[0]);
					sendStr[0] = "공공기관 ["+govPrevCode[0]+"] "+govPrevName[0]+" 은(는) 폐지되었습니다. \r\n";
					sendCount=1;
					chgflag[0] = manualChg;
			   } else if (manualChg.equals("03")){
					govPrevCode[0] = manualCode;
					govPrevName[0] = ctl.getgoName(govPrevCode[0]);
					imsiCode = ctl.getOrgCode(manualCode);
					imsiName = ctl.getgoName2(imsiCode);
					if(imsiName==null || imsiName.equals("")) imsiName = ctl.getgoName(imsiCode);
                    sendStr[0] = "이전 공공기관 ["+govPrevCode[0]+"] "+govPrevName[0]+" 에서 \r\n"
					        + "["+imsiCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
					sendCount=1;
					chgflag[0] = manualChg;
			   }else if (manualChg.equals("05")){
					govPrevCode[0] = manualCode;
					govPrevName[0] = ctl.getgoName(govPrevCode[0]);
					imsiCode = ctl.getOrgCode(govPrevCode[0]);
                    sendStr[0] = "이전 공공기관 ["+govPrevCode[0]+"] "+govPrevName[0]+" (은)는 \r\n";
					for (int k=0; k<(imsiCode.length()/7);k++){
						 String tmpCode2 = imsiCode.substring(k*7,k*7+7);
						 String tmpName2 = ctl.getgoName2(tmpCode2);
						 if((k+1)==(imsiCode.length()/7)){
                             sendStr[0]= sendStr[0] + "["+tmpCode2+"] "+tmpName2+" (으)로 변경되었습니다. \r\n";
						 } else {
                             sendStr[0]= sendStr[0] + "["+tmpCode2+"] "+tmpName2+" (와)과 \r\n";
						 }
					}
					sendCount=1;
					chgflag[0] = manualChg;
			   }else if (manualChg.equals("06")){
				    String imsiOrgCode = ctl.getOrgCode(manualCode);
					imsiName = ctl.getgoName2(imsiOrgCode);
				    imsiCode = ctl.getPrevOrgCode2(imsiOrgCode);
                    for (int k=0; k<(imsiCode.length()/7);k++){
						 govPrevCode[k] = imsiCode.substring(k*7,k*7+7);
					     govPrevName[k] = ctl.getgoName(govPrevCode[k]);
						 sendStr[k]="이전 공공기관 ["+govPrevCode[k]+"] "+govPrevName[k]+" (은)는 \r\n"
							       +"["+imsiOrgCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
						 sendCount=1+k;
						 chgflag[k] = manualChg;
					}
			   }else if (manualChg.equals("07")){
				    String imsiOrgCode2 = ctl.getOrgCode(manualCode);
					imsiName = ctl.getgoName2(imsiOrgCode2);
				    imsiCode = ctl.getPrevOrgCode2(imsiOrgCode2);
                    for (int k=0; k<(imsiCode.length()/7);k++){
						 String checkCode2= imsiCode.substring(k*7,k*7+7);
						 int j=0;
					  if (!checkCode2.equals(manualCode)){ 
						  govPrevCode[j] = checkCode2;	 
						  govPrevName[j] = ctl.getgoName(govPrevCode[j]);
						  sendStr[j]="이전 공공기관 ["+govPrevCode[j]+"] "+govPrevName[j]+" (은)는 \r\n"
						    		+"["+manualCode+"] "+imsiName+" (으)로 변경되었습니다. \r\n";
						  sendCount=1+j;
						  chgflag[j] = manualChg;
						  j=j+1;
					  }
					}
			   } 		  
		  }
	   	  for ( int i=0;i<sendCount;i++) {
		      getCnt = ctl.Usrcount(govPrevCode[i]);					//수신기관코드의 사용자수계산
	   	      totalUsrCnt = totalUsrCnt + getCnt;
			  totalGovCode = totalGovCode + govPrevCode[i]+"^";          //수신기관코드 리스트
			  totalString = totalString+sendStr[i]+"^";                  //송신내용 리스트
			  totalChgFlag = totalChgFlag + chgflag[i]+"^";
		  }
   }

%>
<html>
<head>
<title>변경정보송신</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" href="/css/UM.css" type="text/css">
<script language="javascript" src="/js/UM.js" ></script>
<script language="javascript">

	function usrDetail(code){

		document.sndData.target = "ViewContent";
		document.sndData.method = "post";
		document.sndData.action = "/jsp/RA/UM_RAM_usrDetail.jsp?govCode="+code;
		document.sndData.submit();
		return;
	}

	function sndDoc(govCode){

      if (confirm("기관변경 안내문서를 발송하시겠습니까?   ")) {
			document.sndData.sndFlag.value="1";
			var urlstr=document.sndData.url.value;            
			var URL = urlstr+"sndFlag=1";
			document.sndData.toURL.value = URL;
            
			var codelist =document.sndData.sndGovCode.value;
			var str = document.sndData.sndString.value;
					
			window.open("",'usrSending','status=yes,resizable=yes,scrollbars=no,toolbar=no,width=400,height=300');
			document.sndData.target="usrSending";
        	document.sndData.method='post';
        	document.sndData.action='/servlet/servlets/UM_RAM_toUsrSend';  
        	document.sndData.submit(); 
        	return;
      }
    }
	
	function back(url) {
         parent.ViewContent.location = "http://www.g2b.go.kr:8070/html/initDetail.htm";
		 parent.ListView.location = url;
	}

</script>
</head>
<body background="/img/bg01.gif" text="#3C3C3C" topmargin="0" leftmargin="0" marginwidth="0" marginheight="0">
<form name="sndData" method="post" >

<table width="823" border=0 cellpadding="0" cellspacing="0" background="/img/bg_sub.gif">
   <tr valign=bottom height=57>
      <td rowspan=2><img src='/img/sub_title_01.jpg'></td>
      <td colspan=2 background='/img/sub_title_02.jpg' width=788 height=57 class=HEADLINE> &nbsp; 공공기관정보 송신</td>
   </tr>
   <tr height=7>
      <td colspan=2><img src='/img/sub_title_03.jpg'></td>
   </tr>
   <tr height=21>
      <td colspan=3></td>
   </tr>
   <tr valign=top>
      <td width="35" height="67">&nbsp;</td>
      <td width="760" height="67">
         <table width=100% border=0 cellspacing=1 cellpadding=2 class=line>
            <tr> 
               <td class="tdac" width="17%" height="23">담당자 ID</td>
               <td class="tdc" width="32%" height="23">&nbsp;<%=LoginID%></td>
               <td class="tdac" width="18%" height="23">담당자명</td>
               <td class="tdc" width="33%" height="23">&nbsp;조달청 운영자</td>
           </tr>
           <tr> 
               <td class="tdac" width="17%" height="23">수신공공기관</td>
               <td class="tdc" width="83%" height="23" colspan="3">
			   &nbsp;<font color="#0000CC"><%=Integer.toString(sendCount)%>&nbsp;</font>개 공공기관의 사용자 (총<font color="#0000CC">&nbsp;<%=totalUsrCnt%>&nbsp;</font>명)</td>
           </tr>
        </table>
       </td>
       <td width="28" height="67"></td>
   </tr>
   <tr valign=top>
      <td width="35">&nbsp;</td>
      <td width="760">
      <table width=100% border=0 cellspacing=1 cellpadding=2 class=line>
            <tr> 
               <td class="tdac" width="17%" height="23">제 목</td>
               <td class="tdc" width="83%" height="23">&nbsp;공공기관정보 변경요청건</td>
            </tr>
            <tr> 
               <td class="tdac" width="17%" height="80">내 용</td>
               <td class="tdc" width="83%" height="80">&nbsp;안녕하십니까? 조달청 운영자입니다. <br>
			   &nbsp;사용자님이 소속된 기관정보가 아래와 같이 변경되었습니다.<br> 
<!--               &nbsp;내용을 확인하신뒤&nbsp;나라장터 홈페이지에서 사용자님의 기관정보를 수정해주시기바랍니다.<br> -->
               &nbsp;감사합니다.</td>
            </tr>
      </table>
	  <table>
            <tr> 
               <td height="10" colspan=2></td>
            </tr>
	  </table>
      <table width=100% border=0 cellspacing=1 cellpadding=2 class=line>
<%      
		for( int a=0; a<sendCount; a++){          
%>			
			<tr>
				<td class="tdac" width="17%" height="23"><a href="javascript:usrDetail('<%=govPrevCode[a]%>')"><font color="#0000CC"><%=govPrevCode[a]%></font></td>
				<td class="tdc" width="700" height="23"><%=sendStr[a]%></td>
			</tr>
<%
        }
%>
	  </table></td>
   </tr>
   <input type="hidden" name="id" value="<%=LoginID%>">
   <input type="hidden" name="url" value="<%=comWeb.getUrl(request,"/jsp/RA/UM_RAM_Sending.jsp")%>">
   <input type="hidden" name="toURL" value="">
   <input type="hidden" name="sndFlag" value="">
   <input type="hidden" name="urlHistory" value="<%=urlHistory%>">
   <input type="hidden" name="sndNum" value="<%=sendCount%>">
   <input type="hidden" name="sndGovCode" value="<%=totalGovCode%>">
   <input type="hidden" name="sndString" value="<%=totalString%>">
   <input type="hidden" name="sndChgFlag" value="<%=totalChgFlag%>">
</form>
   <tr height="20"><td colspan="3"></td></tr>
   <tr height="20"><td colspan="3"><p align="center">
	   <% if(sndFlag.equals("0")) {%>
		<a href="javascript:sndDoc()"><img src="/img/bu_confirm.gif" border="0" align="absmiddle"></a>&nbsp;
	   <% }                      %>
	    <a href="javascript:back('<%=urlHistory%>')"><img src="/img/bu_before.gif" border="0" align="absmiddle"></a></td>
   </tr>
   <tr height="20"><td colspan="3" height="18"></td></tr>
  </table>
</body>
</html>












