<%--
/********************************************************************************/
/* 1.시	스 템 명 : 사용자등록                                                   */
/* 2.프로그램 ID : UM_RAJ_GCReport.jsp                                          */
/* 3.프로그램 명 : 수요기관등록신청서/수요기관공인인증서비스신청서              */
/* 4.작	 성	  일 : 2005-06-23                                                   */
/* 5.작	 성	  자 : 설원식                                                       */
/* 6.개		  요 : 수요기관등록신청서/수요기관공인인증서비스신청서 리포트       */
/********************************************************************************/
/*		 일자				버전		작성자		변경사항                    */
/*		2005.06.23			V1.0		설원식		최초생성                    */
/*		2007.03.23						이광현		나라장터 내 주민등록번호 표시체계 개선		  */
/********************************************************************************/
--%>

<%@page language="java" %>
<%@page contentType="text/html; charset=EUC-KR" %>
<%@ page errorPage="/jsp/common/jspToError.jsp?type=0&url=&message=" %>
<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, common.*, common.util.*" %>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>

<jsp:useBean id="List01" class="beans.Common_ComBo01" scope="page" />
<jsp:useBean id="List"   class="beans.Common_ComBo" scope="page" />
<jsp:useBean id="Hutil"  class="common.HttpUtil" scope="page" />
<jsp:useBean id="ctl1"   class="beans.UM_RAB_GovuA010p" scope="page" />
<jsp:useBean id="ctl"    class="beans.UM_ADB_GovrA010c" scope="page" />

<%
	ParameterParser psr = new ParameterParser(request);
	
	String code     = psr.getStringParameter("code","");   // 수요기관등록 신청 접수번호
    
    UM_RAE_GovuA010b ettcode  = null;
    ettcode = ctl1.select_goma(code);
    
    UM_ADE_GovuA040b[] ett = null;
    ett    = ctl1.select_math(code);
%>

<html>
<head>
<title>수요기관등록신청서</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet type=text/css href=/css/EP.css>
</head>

<body bgcolor="#FFFFFF" text="#000000">
<br><br><br><br><br>
<table width='630' align="center">
	<tr>
  		<td colspan=6>
    		<p align='center'><font size='5' face='굴림체'><b>수 요 기 관 등 록 신 청 서</b></font></p>
    	</td>
	</tr>
	<tr>
  		<td colspan=6>
    		<p align='center'><font size='2' face='굴림체'>(정부조달콜센터 FAX번호 : 0505-480-2136)</font></p>
    	</td>
	</tr>
</table>
<br>
<table width="630" align="center" x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;'>
  <tr> 
    <td colspan="5" align="center" class=retdbc>
    	<table width="100%" align="center">  
		  <tr> 
		    <td colspan="5" align="center"><font size=3><b>기 관 정 보</b></font></td>
		  </tr>
		</table>
    </td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%>접수번호</td>
    <td colspan="3" class=retdb><%=ettcode.getrecept() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%>수요기관코드</td>
    <td colspan="3" class=retdb><%=ettcode.getg2bCode() %></td>
  </tr>
  <tr> 
    <td rowspan="3" class=retda>기관명</td>
    <td class=retda>전체</td>
    <td colspan="3" class=retdb><%=ettcode.getgoNameFull() %></td>
  </tr>
  <tr> 
    <td class=retda>약어</td>
    <td colspan="3" class=retdb><%=ettcode.getgoNameShort() %></td>
  </tr>
  <tr> 
    <td class=retda>영문</td>
    <td colspan="3" class=retdb><%=ettcode.getgoNameEn() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%>사업자등록번호</td>
    <td colspan="3" class=retdb><%=insert_minus_saupno(ettcode.getsaupNo()) %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%>업태</td>
    <td class=retdb width=30%><%=ettcode.getbuConditon() %></td>
    <td class=retda width=20%>업종</td>
    <td class=retdb width=30%><%=ettcode.getbuType() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%>우편번호</td>
    <td class=retdb width=30%><%=ettcode.getZIPCODE() %></td>
    <td class=retda width=20%>홈페이지주소</td>
    <td class=retdb width=30%><%=ettcode.gethomepage() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%>물품관리관명</td>
    <td class=retdb width=30%><%=ettcode.getgoodsMaster() %></td>
    <td class=retda width=20%>보증채권자명</td>
    <td class=retdb width=30%><%=ettcode.getcreditName() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>주소</td>
    <td colspan="3" class=retdb><%=ettcode.getADDR() %>&nbsp;<%=ettcode.getaddress2() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%>전화번호</td>
    <td class=retdb width=30%><%=ettcode.gettelNum() %></td>
    <td class=retda width=20%>FAX번호</td>
    <td class=retdb width=30%><%=ettcode.getfaxNum() %></td>
  </tr>
  <tr> 
    <td colspan="5" align="center" class=retdbc>
    	<table width="100%" align="center">  
			<tr> 
		    	<td colspan="5" align="center"><font size=3><b>담 당 자 정 보</b></font></td>
		  	</tr>
		</table>	
    </td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>담당자명</td>
    <td class=retdb><%=ettcode.gettaskmaster() %></td>
    <td class=retda>부서명</td>
    <td class=retdb><%=ettcode.getmasterPost() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>전화번호</td>
    <td class=retdb><%=ettcode.getmasterTel() %></td>
    <td class=retda>FAX번호</td>
    <td class=retdb><%=ettcode.getmasterFax() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>메일주소</td>
    <td colspan="3" class=retdb><%=ettcode.getmasterMail() %></td>
  </tr>
    <tr> 
    <td colspan="5" align="center" class=retdbc>
    	<table width="100%" align="center">  
			<tr> 
		    	<td colspan="5" align="center"><font size=3><b>인 증 서 정 보</b></font></td>
		  	</tr>
		</table>	
    </td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>인증서발급신청</td>
    <td class=retdb><%=ettcode.getlicense().equals("Y") ? "신청" : "신청안함" %></td>
    <td class=retda>신청공인인증기관</td>
    <td class=retdb>
		<%
			String certGigwan="";

			if(ettcode.getlicense().equals("Y")){
				if(ettcode.getlic().equals("0")){
					certGigwan="한국정보인증";
				}else if(ettcode.getlic().equals("1")){
					//certGigwan="한국전산원";
                    certGigwan="한국정보사회진흥원";
				}else if(ettcode.getlic().equals("2")){
					certGigwan="(주)코스콤 공인인증센터(한국증권전산)";
				}else if(ettcode.getlic().equals("4")){
					certGigwan="한국전자인증";
				}
			}	
		%>
		<%=certGigwan %>
    </td>
  </tr>
  <tr>
  	<td colspan="5" class=retdbc>
		<table width="100%" align="center">  
		  <tr> 
		    <td colspan="5" height="101">
				위와 같이 조달사업에 관한 법률시행규칙 제2조에 의거 조달사업의 수요기관 지정을 신청합니다.
		    </td>
		  </tr>
		  <tr> 
		    <td colspan="5" align=right height="24"><%=ettcode.getcreateDate().substring(0,4) %>년 <%=ettcode.getcreateDate().substring(4,6) %>월 <%=ettcode.getcreateDate().substring(6) %>일</td>
		  </tr>
		  <tr> 
		    <td colspan="5" align=right height="46">기관장&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(관인)</td>
		  </tr>
		  <tr> 
		    <td colspan="5" align=right height="46"><b>조 달 청 장 귀 하</b></td>
		  </tr>
		</table>
	</td>
  </tr>
  <tr>
  	<td colspan="5" class=retdb>
		제출서류 : 1. 국가기관 및 지방자치단체 : 별도 구비서류 없음<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		           2. 임의기관 : 설립시 근거서류<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      - 사업자등록사본, 정관, 관계기관 인가증 사본<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  - 기타 본청(정부조달콜센터)에서 요청하는 서류 등
	</td>
  </tr>
</table>

</body>
</html>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<!---------------------------------- 수요기관공인인증서비스신청서 ------------------------------>

<%
    String recept       = psr.getStringParameter("recept","");
    String approvalCode = psr.getStringParameter("approvalCode","");
    String G2BCODE      = psr.getStringParameter("G2BCODE","");
    
    if(recept.equals("") || recept.equals("") || recept.equals("")){
    	throw new Exception("필요한 값을 넘겨받지 못했습니다.다시 시도해 주시기 바랍니다.");
    }

    UM_ADJ_GovuA020b ettcode1  = null;
    ettcode1 = ctl.select_GovernmentList_Confirm(recept,approvalCode,G2BCODE);
    
    if(ettcode1==null){
    	throw new Exception("해당 접수번호에 대한 내용을 조회할 수 없습니다.");
    }
%>

<html>
<head>
<title>수요기관 공인인증서비스 신청서</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel=stylesheet type=text/css href=/css/EP.css>
<script language="javascript">
	
	function openHelpWin(){
		var theURL="http://www.g2b.go.kr:8070/html/help/installCert.htm";
		window.open(theURL,'helpWin','toolbar=yes,location=no,status=yes,menubar=yes,scrollbars=yes,resizable=no,width=600,height=400');
		return;
	}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" onload="javascript:openHelpWin();">
<br><br><br><br><br>
<table width='630' align="center">
	<tr>
  		<td colspan=6>
    		<p align='center'><font size='5' face='굴림체'><b>수요기관 공인인증서비스 신청서</b></font></p>
    	</td>
	</tr>
	<tr>
  		<td colspan=6>
    		<p align='center'><font size='2' face='굴림체'>(정부조달콜센터 FAX번호 : 0505-480-2136)</font></p>
    	</td>
	</tr>
</table>
<br>
<table width="630" align="center" x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;'>
  <tr> 
    <td colspan="5" align="center" class=retdbc>
    	<table width="100%" align="center">  
		  <tr> 
		    <td colspan="5" align="center"><font size=3><b>기 관 정 보</b></font></td>
		  </tr>
		</table>
    </td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%>접수번호</td>
    <td colspan="3" class=retdb><%=ettcode1.getrecept() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%>수요기관코드</td>
    <td colspan="3" class=retdb><%=ettcode1.getG2BCODE() %></td>
  </tr>
  <tr> 
    <td rowspan="2" class=retda>기관명</td>
    <td class=retda>국문</td>
    <td colspan="3" class=retdb><%=ettcode1.getCNAME() %></td>
  </tr>
  <tr> 
    <td class=retda>영문</td>
    <td colspan="3" class=retdb><%=ettcode1.getKNAME() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%>대표</td>
    <td class=retdb width=30%><%=ettcode1.getIDENT() %></td>
    <td class=retda width=20%>대표주민번호</td>
    <td class=retdb width=30%><%=insert_minus_jumin_star(ettcode1.getIDENTJUMIN()) %></td> <!-- 2007.03.23 나라장터 내 주민등록번호 표시체계 개선 -->
  </tr>
  <tr> 
    <td class=retda colspan="2">우편번호</td>
    <td class=retdb><%=ettcode1.getZIPNO() %></td>
    <td class=retda>사업자등록번호</td>
    <td class=retdb><%=insert_minus_saupno(ettcode1.getCOMNO()) %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>주소</td>
    <td colspan="3" class=retdb><%=ettcode1.getADDRS() %><%=ettcode1.getADDRESS2() %></td>
  </tr>
  <tr> 
    <td colspan="5" align="center" class=retdbc>
    	<table width="100%" align="center">  
			<tr> 
		    	<td colspan="5" align="center"><font size=3><b>신 청 자 정 보</b></font></td>
		  	</tr>
		</table>	
    </td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>성명</td>
    <td class=retdb><%=ettcode1.getMYNAME() %></td>
    <td class=retda>부서명</td>
    <td class=retdb><%=ettcode1.getOFFICEDE() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>주민등록번호</td>
    <td class=retdb><%=insert_minus_jumin(ettcode1.getJUMIN()) %></td>
    <td class=retda>전화번호</td>
    <td class=retdb><%=ettcode1.getTEL() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>E-MAIL</td>
    <td class=retdb><%=ettcode1.getEMAIL() %></td>
    <td class=retda>팩스번호</td>
    <td class=retdb><%=ettcode1.getFAX() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>사용자ID</td>
    <td class=retdb><%=ettcode1.getUSRID() %></td>
    <td class=retda>요청인가코드</td>
    <td class=retdb><%=ettcode1.getdependCode() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>공인인증기관</td>
    <td colspan="3" class=retdb>
    	<%  String Code = ettcode1.getCERT_ORG(); 
                if (Code.equals("0")) {    %><font color = "red"> 한국정보인증 : http://www.signgate.com/ra/g2b</font>
        <%  } else if (Code.equals("1")){  %><font color = "red"> 한국정보사회진흥원 : http://www.nia.or.kr</font> <!--한국전산원 : http://sign.nca.or.kr</font>-->
        <%  } else if(Code.equals("2")){   %><font color = "red"> 코스콤 : http://www.signkorea.com</font>    
        <%  } else {                       %><font color = "red"> 한국전자인증 : http://www.naraca.co.kr</font>
        <%  } %>
		</td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>승인신청 지방청</td>
    <td colspan="3" class=retdb>
    	<%=List01.getCode2("T","branchOffi1", "J03", ettcode1.getbranchOffi1()) %>
    </td>
  </tr>
  <tr>
  	<td colspan="5" class=retdbc>
		<table width="100%" align="center">  
		  <tr> 
		    <td colspan="5" height="101">
		    		 위와 같이 인증서비스를 신청하며 인증서비스의 신청 및 인증서의 이용에 있어 공인인증 기관의
		    		인증업무준칙 및 인증서비스 이용약관을 숙지하였으며 가입자의 의무와 책임 및 인증서의 이용 등에
		    		관한 사항을 준수할 것을 약속합니다.
		    </td>
		  </tr>
		  <tr> 
		    <td colspan="5" align=right height="24"><%=ettcode1.getDATE().substring(0,4)%>년 <%=ettcode1.getDATE().substring(4,6)%>월 <%=ettcode1.getDATE().substring(6)%>일</td>
		  </tr>
		  <tr> 
		    <td colspan="5" align=right height="46">기관장&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(관인)</td>
		  </tr>
		</table>
	</td>
</table>		  
</body>
</html>
