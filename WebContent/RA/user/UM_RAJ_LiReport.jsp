<%
/****************************************************************************************/
/*                                                    									*/
/*    Program ID    :     UM_RAJ_LiReport.jsp                                           */
/*                                                                                      */
/*    description   :     수요기관공인인증서비스신청서 							  		*/
/*                        Đơn xin dịch vụ chứng nhận công của CQNN                      */
/****************************************************************************************/
/*		최초생성         2002.06.12         오 창 렬                                   	*/
/****************************************************************************************/
	
/*	2003.04.27	김영진	프로그램 수정
	2003.05.12	김영진	html 버젼으로 변경
	2004.05.27	윤태우 	승인지청을 기존 하드코딩에서 공동코드 테이블에서 가지오도록 수정
	2004.05.27	윤태우 	승인지청을 기존 하드코딩에서 공동코드 테이블에서 가지오도록 수정
	2004.07.06  윤태우 	출력시 짤림 현상을 수정 
	2004.09.22	윤태우 	프로그램 정리 및 인증서발급기관(한국전자인증) 추가
	2007.01.31	이광현	나라장터 인증서 신청 시 이용자 비밀번호 입력 란 신설 (인증기관 URL 변경처리함)
	2007.02.23	이광현	인증서신청서 주민등록번호 출력사항 개선
	2007.05.31	신경운	담당자 핸드폰, SMS 수신여부 추가*/
	
/***************************************************************************/
/* Program ID         : UM_RAJ_LiReport.jsp                        */
/* Program Explanation: Đơn xin dịch vụ chứng nhận công của CQNN                     */
/* Program Summary    : Đơn xin dịch vụ chứng nhận công của CQNN*/
/* Relation Program   : */ 
/*                      */
/* Table              :                  */
/***************************************************************************/
/* Customizing Composer : MR. SonDN 20.05.2009                       */
/***************************************************************************/

%>

<%@page language="java" %>
<%//@ page errorPage="/jsp/common/jspToError.jsp?type=0&url=&message=001" %>
<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, common.*, common.util.*" %>
<%@ page language="java" import="com.oreilly.servlet.*" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%//@ include file = "/jsp/common/fnUmCommon.jsp" %>

<jsp:useBean id="List" class="beans.Common_ComBo01" scope="page" />
<jsp:useBean id="ctl" class="beans.UM_ADB_GovrA010c" scope="page" />

<%
    com.oreilly.servlet.ParameterParser psr = new com.oreilly.servlet.ParameterParser(request);
    String recept       = psr.getStringParameter("recept","");
    String approvalCode = psr.getStringParameter("approvalCode","");
    String G2BCODE      = psr.getStringParameter("G2BCODE","");
    
    if(recept.equals("") || recept.equals("") || recept.equals("")){
    	throw new Exception("Không thể lấy được giá trị cần thiết. Hãy thực hiện lại..");
    	//throw new Exception("필요한 값을 넘겨받지 못했습니다.다시 시도해 주시기 바랍니다.");
    }

    UM_ADJ_GovuA020b ettcode  = null;
    ettcode = ctl.select_GovernmentList_Confirm(recept,approvalCode,G2BCODE);
    
    if(ettcode==null){
    	throw new Exception("Không thể tra cứu được nội dung số tiếp nhận tương ứng.");
    	//throw new Exception("해당 접수번호에 대한 내용을 조회할 수 없습니다.");
    }
%>

<html>
<head>
<title>Đơn đăng ký dịch vụ Chứng nhận công Chủ đầu tư</title><!-- <title>수요기관 공인인증서비스 신청서</title>-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet type=text/css href="../css/EP.css">
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
    		<p align='center'><font size='5' style="font-family: Arial;"><b><!-- 수요기관 공인인증서비스 신청서  -->Đơn đăng ký dịch vụ chứng nhận công cho cơ quan chủ đầu tư</b></font></p>
    	</td>
	</tr>
	<tr>
  		<td colspan=6>
    		<p align='center'><font size='2' style="font-family: Arial;">(<!--  정부조달콜센터 FAX번호 -->Số Fax call center của cơ quan quản lý mua sắm công  : xxxx-xxx-xxx)</font></p>
    	</td>
	</tr>
</table>
<br>
<table width="630" align="center" x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;'>
  <tr> 
    <td colspan="5" align="center" class=retdbc>
    	<table width="100%" align="center">  
		  <tr> 
		    <td colspan="5" align="center"><font size=3><b><!-- 기 관 정 보  -->Thông tin cơ quan </b></font></td>
		  </tr>
		</table>
    </td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%><!--  접수번호 -->Số tiếp nhận </td>
    <td colspan="3" class=retdb><%=ettcode.getrecept() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%><!-- 수요기관코드 --> Mã số cơ quan chủ đầu tư</td>
    <td colspan="3" class=retdb><%=ettcode.getG2BCODE() %></td>
  </tr>
  <tr> 
    <td rowspan="2" class=retda><!-- 기관명  -->Tên cơ quan </td>
    <td class=retda><!-- 국문 --> Tiếng Việt </td>
    <td colspan="3" class=retdb><%=ettcode.getCNAME() %></td>
  </tr>
  <tr> 
    <td class=retda><!-- 영문 --> Tiếng Anh </td>
    <td colspan="3" class=retdb><%=ettcode.getKNAME() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda width=20%><!-- 대표 --> Người đại diện </td>
    <td class=retdb width=30%><%=ettcode.getIDENT() %></td>
    <td class=retda width=20%><!-- 대표주민번호 --> Số CMND Người đại diện </td>
    <td class=retdb width=30%><%=ettcode.getIDENTJUMIN()%><%//=insert_minus_jumin_star(ettcode.getIDENTJUMIN()) %></td>
  </tr>
  <tr> 
    <td class=retda colspan="2"><!-- 우편번호 --> Số bưu điện </td>
    <td class=retdb><%=ettcode.getZIPNO() %></td>
    <td class=retda><!-- 사업자등록번호  -->Số ĐKKD </td>
    <td class=retdb><%=ettcode.getCOMNO()%><%//=insert_minus_saupno(ettcode.getCOMNO()) %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda> <!-- 주소 -->Địa chỉ </td>
    <td colspan="3" class=retdb><%=ettcode.getADDRS() %>&nbsp;<%=ettcode.getADDRESS2() %></td>
  </tr>
  <tr> 
    <td colspan="5" align="center" class=retdbc>
    	<table width="100%" align="center">  
			<tr> 
		    	<td colspan="5" align="center"><font size=3><b><!--신 청 자 정 보   -->Thông tin người đại diện </b></font></td>
		  	</tr>
		</table>	
    </td>
  </tr>
  <tr> 
    <td colspan="2" class=retda> <!--성명  -->Tên </td>
    <td class=retdb><%=ettcode.getMYNAME() %></td>
    <td class=retda><!-- 부서명  -->Phòng ban</td>
    <td class=retdb><%=ettcode.getOFFICEDE() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda> <!-- 주민등록번호 -->Số CMND </td>
    <td class=retdb><%//=insert_minus_jumin_star(ettcode.getJUMIN()) %></td>
    <td class=retda><!-- 전화번호  -->Điện thoại </td>
    <td class=retdb><%=ettcode.getTEL() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda>E-MAIL</td>
    <td class=retdb><%=ettcode.getEMAIL() %></td>
    <td class=retda> <!-- 팩스번호 -->FAX </td>
    <td class=retdb><%=ettcode.getFAX() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda><!-- 핸드폰 --> Di động </td>
    <td class=retdb colspan="3"><%=ettcode.getHP() %>&nbsp;&nbsp; - 
			<input type="checkbox" name="smsCheck" <%="Y".equals(ettcode.getsmsCheck())?"checked":""%> disabled>Nhận bằng SMS<!-- SMS수신여부 --> </td>
  </tr>
  <tr> 
    <td colspan="2" class=retda><!-- 사용자ID  -->ID người dùng</td>
    <td class=retdb><%=ettcode.getUSRID() %></td>
    <td class=retda> <!-- 요청인가코드 -->Mã phê duyệt yêu cầu </td>
    <td class=retdb><%=ettcode.getdependCode() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retda><!-- 공인인증기관 --> Cơ quan cấp chứng nhận công </td>
    <td colspan="3" class=retdb>
    	<%  String Code = ettcode.getCERT_ORG(); 
                if (Code.equals("0")) {    %><font color = "red">  <!--한국정보인증  -->Chứng nhận thông tin Hàn quốc  : http://www.signgate.com/ra/g2b</font>
        <%  } else if(Code.equals("1")){  %><font color = "red"> 한국정보사회진흥원  : http://sign.nia.or.kr</font> <!--한국전산원 : http://sign.nca.or.kr</font>-->
        <%  } else if(Code.equals("2")){   %><font color = "red"> 코스콤 : http://www.signkorea.com</font>    
        <%  } else if(Code.equals("3")) { %> 한국전자인증 : http://www.naraca.co.kr
	    <%  } else {%> 						MPI - Bộ Kế hoạch và Đầu tư
        <%  } %>
		</td>
  </tr>
  <tr style="display:none"> 
    <td colspan="2" class=retda> <!-- 승인신청 지방청 -->Cơ quan đăng ký phê duyệt ở địa phương </td>
    <td colspan="3" class=retdb>
    	<%//=List.getCode2("T","branchOffi1", "J03", ettcode.getbranchOffi1()) %>
    </td>
  </tr>
<% 
	String passwdmodOK = ettcode.getpasswdmodOK();
	if ("Y".equals(passwdmodOK)) {
%>
  <tr>
	<td colspan="2" class=retda> <!-- 비밀번호 변경여부 -->Tùy chọn thay đổi mật khẩu </td>            
	<td class=retdb><!-- 변경요청 -->Yêu cầu thay đổi </td>
	<td class=retda><!-- 이용자ID  --> ID người dùng </td>            
	<td class=retdb><%=ettcode.getgID() %></td>
  </tr>
<%
	}
%>

  <tr>
  	<td colspan="5" class=retdbc>
		<table width="100%" align="center">  
		  <tr> 
		    <td colspan="5" height="101">
		    		 <!--  위와 같이 인증서비스를 신청하며 인증서비스의 신청 및 인증서의 이용에 있어 공인인증 기관의
		    		인증업무준칙 및 인증서비스 이용약관을 숙지하였으며 가입자의 의무와 책임 및 인증서의 이용 등에
		    		관한 사항을 준수할 것을 약속합니다. -->
		    		Chúng tôi đồng ý với các điều khoản của thỏa thuận người dùng dịch vụ chứng nhận công và xin đăng ký dịch vụ với dung như trên.
		    		Chúng tôi cam kết sẽ thực hiện đầy đủ các nghĩa và trách nhiệm với tư cách là thành viên tham gia dịch vụ. 
		    </td>
		  </tr>
		  <tr> 
		    <td colspan="5" align=right height="24">
		     Ngày	<%=ettcode.getDATE().substring(6)%>
		     tháng	<%=ettcode.getDATE().substring(4,6)%>
		     năm	<%=ettcode.getDATE().substring(0,4)%>
		    	 
		    	 
		  </td>
		  </tr>
		  <tr> 
		    <td colspan="5" align=right height="46"><!-- 기관장   -->Trưởng cơ quan &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(관인)</td>
		  </tr>
		</table>
	</td>
 </tr>
  <tr>
  	<td colspan="5" class=retdbc>
		<table width="100%" align="center">  
		  <tr> 
		    <td colspan="5" height="90" class=retdb>
					
					 <!-- 인증서 신청서 승인여부 확인은 팩스를 보내신후 30여분 후 확인이 가능하며, 30여분후에도 대기상태로 나와<br>&nbsp;
					있으면 직인이 선명하게 날인되었는지 다시 한번 확인하셔서 팩스로 보내주시기 바랍니다. 좋은하루 되세요  -->
					Có thể kiểm tra tình hình phê duyệt yêu cầu cấp chứng nhận số 30 phút sau khi gửi Fax. <br>
					Nếu sau 30 phút trạng thái phê duyệt là chờ thì có thể đơn đăng ký chưa đến được, vui lòng fax lại. 
					Chúc một ngày làm việc vui vẻ.
					</td>
		  </tr>
		</table>
	</td>
 </tr>

</table>		  
</body>
</html>
