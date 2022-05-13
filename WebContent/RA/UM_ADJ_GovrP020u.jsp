<%-- 
/********************************************************************/     
/*																													  */ 
/*    Program ID    :     UM_RAJ_GovrA010u.jsp												  */
/*																													  */
/*    description   :     수요기관등록																	  */
/*																													  */
/********************************************************************/ 
/*		최초생성         2002.06.12         박 민 호													   */
/********************************************************************/ 
	 
	2003.04.15	김영진	프로그램 정리 
	2003.05.07	김영진	1.운송거리	maxlength 4 처리 
											2.최상위기관코드, 기관유형대 임의로 생성된 G2B  
												코드 인 경우에는 필수항목아닌것으로 처리 
	2003.12.01  민 정     프로그램 기능추가                         
											1.임의기관등록시 차상위기관 검색등록 
											2.임의기관등록시에도 행정표준정보에 데이타 입력하도록 수정 
												-> 다시 최상위/차상위 두 항목도 필수항목으로 처리함 
												G2B고도화사업------->로 주석처리 
    2004.05.18  윤태우  1.신청일시(신청일시)항목 추가  
	2004.07.07	박우명	1.기관유형(대, 중, 소) 추가 
	2004.08.27	윤태우	1.프로그램(소스) 정리  
											2.수요기관신청승인후 데이터를 접수테이블이 아닌 사용_수요기관마스터 및  
											  사용_수요기관회계코드 테이블에서 가지고 오도록 수정함. 
    2004.10.06	윤태우  1.인증기관중에 한국전자인증 추가 
    2007.04.29  임지훈  1. 보류기능 추가 
    2007.05.31  신경운  1. 핸드폰, SMS 추가
	2007.10.18	이광현	SMS서비스 확대적용(3단계)
	2007.12.13	이광현	기관코드 오류사항 조치요청
	2007.12.31	신경운	관할지청에서 품질관리단 제외(2008년부터)
	2008.09.04	신경운	수요기관 기준에 의한 분류체계 변경 - 소관구분, 근거법령 추가
	------------------	------------------	------------------	------------------	------------------
									변경전 - GUA						변경후 - GUA
	------------------	------------------	------------------	------------------	------------------
									소관구분코드	소관구분명	    소관구분코드	소관구분명
	------------------	------------------	------------------	------------------	------------------
									01					국가기관		01					국가기관
									02					지자체	  		02					지자체
									03					교육기관		03					교육기관
									05					정부투자기관	51					공기업
																			52					준정부기관
																			53					기타공공기관
									07					기타기관		71					지방공기업
																			72					기타기관
	------------------	------------------	------------------	------------------	------------------
	2008.11.13	신경운	소관구분에 따른 수요기관기준 선택시 제한  

/***************************************************************************/
/* Program ID         : UM_RAJ_GovrA010u.jsp                        */
/* Program Explanation: Xem chi tiết đăng ký bên mời thầu				*/
/* Program Summary  : 
/* Relation Program   : 
/* Table              : 							*/
/***************************************************************************/
/* Customizing Composer : MR. MINH 27.05.2009                      	*/
/* Customizing Composer : MR. HUNGNT 06.08.2011                      	*/
/***************************************************************************/
	
--%>  
<%@page language="java" %> 
<%@page contentType="text/html; charset=UTF-8" %> 
<%@ page errorPage="../jsp/common/jspToError.jsp?type=1&url=&message=" %> 
<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*,common.*,g2b.sso.*, common.util.*, LOGIN.*, java.text.SimpleDateFormat" %> 
<%@ include file = "../jsp/common/fnUmCommon.jsp" %>

<jsp:useBean id="ZipCode"     class="common.ZipCode" scope="page" />
<jsp:useBean id="ctl1"      class="beans.UM_RAB_GovuA010p" scope="page" /> 
<jsp:useBean id="License"   class="beans.UM_ADB_GovrA010c" scope="page" /> 
<jsp:useBean id="comUtil"   class="common.util.CommUtil" scope="page" /> 
<jsp:useBean id="nationalCtrl" class="beans.UM_RAB_Local" scope="page" />
 
<%
	SSO sso=new SSO(pageContext);	// SSO 생성
	sso.checkLogin();

	// 권한체크
	UM_Auth_Check uac = new UM_Auth_Check(request, response);	// 기관승인자만 볼수 있도록 권한설정
	//uac.checkCookie("k",null,null);
 
    String recept       = comUtil.retSpace(request.getParameter("recept"));   		// 수요기관등록 신청 접수번호 
    String LicenseCode  = comUtil.retSpace(request.getParameter("LicenseCode"));   	// 수요기관인증서 신청 접수번호 
    String urlHistory	= comUtil.retSpace(request.getParameter("urlHistory"));   	// 목록 url history [뒤로가기 버튼에서 사용함] 
    String groupNo	= comUtil.retSpace(request.getParameter("groupNo"));   	// 목록 url history [뒤로가기 버튼에서 사용함]
    String departmentNo	= comUtil.retSpace(request.getParameter("departmentNo"));   	// 목록 url history [뒤로가기 버튼에서 사용함]
    String FirstUperCode = "0000000"; //G2B고도화사업 
    String step=request.getParameter("step");	 
		// 사용_접수수요기관마스터 테이블 정보
    
    UM_RAE_GovuA010b ettcode=ctl1.select_goma(recept, groupNo, departmentNo);
    String masterCode=ettcode.getg2bCode(); // 수요기관코드 
    // 승인여부 
    String approval = ettcode.getenYn(); 
     
    UM_RAE_GovuA010b ettcode2 = null; 

	UM_ADE_GovuA040b ett2  = new UM_ADE_GovuA040b(); 
	if("Y".equals(approval)){         // 승인시 데이터 가지고 오기 
		ettcode2 = ctl1.select_gmaster(masterCode);  // 사용_수요기관마스터  
	  ett2     = ctl1.selectMath(masterCode);    // 사용_수요기관회계코드 
	} 
	 
	String dependCode   = "";        //수요기관인증서 신청 요청인가코드 
		 
	UM_ADJ_GovuA020b ettLicense = License.select_GovernmentList_Confirm(recept); 
	if (ettLicense != null) {
   		dependCode=ettLicense.getdependCode(); 
	}
    // 사용_접수수요기관회계코드 테이블정보 
    //UM_ADE_GovuA040b[] ett=ctl1.select_math(recept); 
        
    boolean isMakedCode=false; 

    //사용_행정표준정보 테이블정보 
    UM_GOJ_GovuA010b ettall=null; 
	
	String userid = sso.getID();

	// 현재날짜 구하기
	Date currentdate;
	String frmDate = null;

	request.setAttribute("Cur", new Date());
	currentdate=(Date)request.getAttribute("Cur"); 
	frmDate = new SimpleDateFormat("yyyyMMddHHmmss").format(currentdate);

	Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmdd");
	String currentDate = dateFormat.format(calendar.getTime());      // 현재일자
	
	String zipName = ZipCode.getZipType(ettcode.getZIPCODE());
    String insType = ZipCode.getInsType(ettcode.getInsType());
    String dependType = ZipCode.getDependType(ettcode.getperOff());
    
    UM_ADE_GovuA040b ett = ctl1.selectMath(recept);
%> 

<html> 
<head> 
<title><!-- 수요기관등록 -->Phê duyệt đăng ký bên mời thầu</title>	 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
<link href="http://muasamcong.mpi.gov.vn/css/TA.css" rel="stylesheet" type="text/css" />
<Script language="Javascript" src="../js/UM.js"></SCRIPT> 
<Script language="Javascript" src="../js/tel_check.js"></SCRIPT> 
 
</head>
 
<body>
<form name="useForm"> 
        <input type="hidden" name="groupNo" value="<%=groupNo%>">
        <input type="hidden" name="departmentNo" value="<%=departmentNo%>">
<div class="col-750 clearfix last">
	<h1 class="pageTitle"><i class="icon-title"></i>Thông tin cơ bản về cơ quan đăng ký</h1>
        
<!---------------------- 마스터정보 타이틀 테이블 시작--> 
	
		<table width="100%" border="0">
			<tr>
				<td width="20%" class="tdar"> Tình trạng đăng ký</td>
				<td class="tdb" colspan="1">
					<%="1".equals(step)?"Chưa phê duyệt":"2".equals(step)?"Đã phê duyệt":"3".equals(step)?"Đã nhận chứng thư số":"4".equals(step)?"Đã hoàn tất":"5".equals(step)?"Đang bảo lưu":"Không rõ" %>
				</td>
			</tr>
			<tr>
				<td height="2" class="LINE" colspan="4" />
			</tr>
		</table>
		<br>
	
	<table width="100%" cellpadding="2" cellspacing="1">
          <tr> 
            <td class="fontb"><!--[기관정보]-->[Thông tin cơ quan]</td>
          </tr>
        </table>
	<!---------------------- 마스터정보 타이틀 테이블 끝 ------------------------>
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
		<tr> 
				<td height="4" colspan="4" class="line"></td>
		</tr> 
    	<tr> 
			<td class="tdar" width="20%"><!-- 접수번호--> Số tiếp nhận</td>
			<td class="tdb"  width="30%"><font color = "red"><%=ettcode.getrecept()%></font>   
      		</td>
			<td class="tdar" width="20%"><!-- 수요기관코드--> Mã cơ quan</td>
			<td class="tdb"  width="30%"><%=ettcode.getg2bCode()%></td>	
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 수요기관명(전체)--> Tên cơ quan (đầy đủ)</td>
			<td class="tdb"  colspan="3"><%=ettcode.getgoNameFull() %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 수요기관명(약어)--> Tên cơ quan (viết tắt)</td>
			<td class="tdb"  colspan="3"><%=(ettcode.getgoNameShort()==null?"":ettcode.getgoNameShort()) %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 수요기관명(영문)--> Tên cơ quan (tiếng Anh)</td>
			<td class="tdb"  colspan="3"><%=ettcode.getgoNameEn() %></td>
		</tr>
		<!-- 기관유형(대, 중, 소) 추가 - Added in 2004.02.24 -->

		<tr>
			<td class="tdar" width="20%"><!-- 사업자등록번호--> Số ĐKKD</td>
			<td class="tdb"  width="30%" colspan="3"><%=ettcode.getsaupNo() %></td>
		</tr>
		<tr>
			<td class="tdar" width="20%"><!-- 소관구분-->
				Phân loại trực thuộc</td>
			<td   class="tdb">  
           		<%=dependType %>       
       		</td>
       		<td class="tdar" width="20%"><!-- 기관유형-->
				Loại hình cơ quan</td>
			<td class="tdb" >
				<%=insType %>
			</td>
		</tr>
		<tr>
			<td width="20%" class="tdar"><!-- 우편번호-->
			Tỉnh/Thành phố</td>
			<td width="30%" class="tdb" colspan="3">
				<%
					HashMap provinces = nationalCtrl.getAllHash("GU7");
					String codeName = "";
					Object a = provinces.get(ettcode.getZIPCODE());
					if (a != null)
						codeName = a.toString();
				%>
				<%=codeName%>
			</td>
		</tr>
                <tr style="display: none">
			<td width="20%" class="tdar"><!-- 우편번호-->
			Bộ ban ngành</td>
			<td width="30%" class="tdb" colspan="3">
				<%
					String depName = "";

					if (ettcode.getDepName() != null)
						depName = ettcode.getDepName();
				%>
				<%=depName%>
			</td>
                </tr>
                <tr style="display: none">
			<td width="20%" class="tdar"><!-- 우편번호-->
			Tập đoàn / TCT</td>
			<td width="30%" class="tdb" colspan="3">
				<%

					String groupName = "";

					if (ettcode.getGroupName() != null)
						groupName = ettcode.getGroupName();
				%>
				<%=groupName%>
			</td>
		</tr>
		<tr>
			<td class="tdar" width="20%"><!-- 주소-->
				Địa chỉ</td>
			<td class="tdb" colspan="3"> <%=ettcode.getADDR() %> </td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 대표 전화번호--> Số điện thoại cơ quan</td>
			<td class="tdb" > <%=ettcode.gettelNum() %></td>
			<td class="tdar"><!-- 대표 팩스번호--> Số Fax cơ quan</td>
			<td class="tdb"> <%=ettcode.getfaxNum() == null ? "" : ettcode.getfaxNum() %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 홈페이지-->Trang web</td>
			<td class="tdb"  colspan="3"> <%=(ettcode.gethomepage()==null?"":ettcode.gethomepage()) %></td>
		</tr>
		
		<tr> 
			<td class="tdar" width="20%"><!-- 업태-->Hình thái kinh doanh</td>
			<td class="tdb"  width="30%"> <%=(ettcode.getbuConditon()==null?"":ettcode.getbuConditon()) %></td>
			<td class="tdar" width="20%"><!-- 업종-->Ngành nghề</td>
			<td class="tdb"  width="30%"> <%=(ettcode.getbuType()==null?"":ettcode.getbuType()) %></td>
		</tr>
        <tr> 
			<td class="tdar" width="20%"><!-- 수요기관회계코드--> Mã số thuế</td>
			<td class="tdb"  colspan="3">
				<%=ett.getmathCode()==null?"":ett.getmathCode() %>
            </td>
		</tr>
        <tr> 
            <td height="2" colspan="4" class="line"></td>
        </tr> 
    </table>
<br>
    <table width="100%" cellpadding="2" cellspacing="1">
		<tr> 
			<td class="fontb"><!-- [담당자정보]-->[Thông tin người phụ trách]</td>
		</tr>
    </table>
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
        <tr> 
            <td height="4" colspan="4" class="line"></td>
        </tr>  		
        <tr> 
			<td class="tdar" width="20%"><!-- 담당자명--> Tên người phụ trách</td>
			<td class="tdb" width="30%"> <%=ettcode.gettaskmaster() %></td>
			<td class="tdar" width="20%"><!-- 담당자 부서명--> Số CMND</td>
			<td class="tdb" width="30%"> <%=ettcode.getcreditName() %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 담당자 부서명--> Phòng/Ban</td>
			<td class="tdb" width="30%"> <%=ettcode.getmasterPost() %></td>
			<td class="tdar" width="20%"><!-- 담당자 전화번호--> Số điện thoại</td>
			<td class="tdb" width="30%"> <%=ettcode.getmasterTel() %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 담당자 팩스번호--> Số Fax</td>
			<td class="tdb" width="30%"> <%=(ettcode.getmasterFax()==null?"":ettcode.getmasterFax())%></td>
			<td class="tdar" width="20%"><!-- 담당자 핸드폰번호--> Số di động</td>
			<td class="tdb" width="30%"> <%=(ettcode.getmasterhp()==null?"":ettcode.getmasterhp()) %></td> 
		</tr>
		<tr> 
			<td class="tdar" width="20%"><!-- 담당자 메일주소--> Địa chỉ email</td>
			<td class="tdb"  colspan="3"> <%=ettcode.getmasterMail() %></td>       
		</tr>		
        <tr> 
            <td height="2" colspan="4" class="line"></td>
        </tr> 
	</table>
	
<!---------------------- 마스터정보 타이틀 테이블 시작 ------------------------>
<!---------------------- 마스터정보 타이틀 테이블 끝 ------------------------>

	<br>
	<table width="100%" cellpadding="2" cellspacing="1">
		<tr> 
			<td class="fontb"><!--[인증서 신청여부]-->[Đăng ký chứng thư số]</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
        <tr> 
            <td height="4" colspan="4" class="line"></td>
        </tr>
                
        <tr> 
			<td class="tdar" width="20%"><!-- 대표--> Tên người đại diện</td>
			<td class="tdb"  width="30%"> <%=ettLicense.getIDENT() %></td>
			<td class="tdar" width="20%"><!-- 대표자주민번호--> Số CMND</td>
			<td class="tdb"  width="30%"> <%=ettLicense.getIDENTJUMIN() %></td>
		</tr><!-- 2007.03.23 나라장터 내 주민등록번호 표시체계 개선 -->
        <tr> 
			<td class="tdar" width="20%"><!-- 인증서요청ID--> Mã yêu cầu chứng thư số</td>
			<td class="tdb"  width="30%" colspan="3"> <%=ettLicense.getUSRID() %></td>
		</tr>
		<tr >
            <td class="tdar" width="20%">Mã phê duyệt đăng ký<!-- 시 요청인가코드 "--></td>
            <td class="tdb" width="30%" colspan="3"><font color = "red"> <%=ettLicense.getdependCode() %></font>
            <!-- "은 필수입력 항목이니 숙지하셔야 합니다-->
            <!-- 대표자주민번호 뒷 5자리는 숨김처리하였습니다--></td>
        </tr>
		
		<tr >
            <td class="tdar" width="20%">Số tham chiếu</td>
            <td class="tdb" width="30%" colspan="3"><font color = "red"> <%=ettLicense.getREF_NO()==null?"":ettLicense.getREF_NO() %></font>
            </td>
        </tr>

		
        <tr> 
            <td height="2" colspan="4" class="line"></td>
        </tr> 	
	</table>
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
		<tr align="center"><td height="24"><input type="button" class="commonbutton" value="Quay lại" onclick="javascript:history.back();"></td></tr>
	</table>

</form> 
 
 	
	<div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
	</div>

</body>
</html>
