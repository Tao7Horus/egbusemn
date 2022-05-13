<%--
/****************************************************************************************/
/*                                                    									*/
/*    Program ID    :     UM_RAJ_AdminReport.jsp                                             */
/*                                                                                      */
/*    description   :     수요기관등록신청서        	Đơn phê duyệt đăng ký bên mời thầu						  		*/
/*                                                                                      */
/****************************************************************************************/
/*		최초생성         2005.06.23         설 원 식                                   	*/
/****************************************************************************************/
/***************************************************************************/
/* Program ID         : UM_RAJ_AdminReport.jsp                        */
/* Program Explanation: Đơn đăng phê duyệt ký bên mời thầu				*/
/* Program Summary  : 
/* Relation Program   : 
/* Table              : 							*/
/***************************************************************************/
/* Customizing Composer : MR. MINH 13.07.2009                      	*/
/***************************************************************************/
--%>

<%@page language="java" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="../jsp/common/jspToError.jsp?type=0&url=&message=" %>
<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, common.*" %>
<%@ include file = "../jsp/common/fnUmCommon.jsp" %>

<jsp:useBean id="List01" class="beans.Common_ComBo01" scope="page" />
<jsp:useBean id="List"   class="beans.Common_ComBo" scope="page" />
<jsp:useBean id="Hutil"  class="common.HttpUtil" scope="page" />
<jsp:useBean id="ctl1"   class="beans.UM_RAB_GovuA010p" scope="page" />
<jsp:useBean id="ZipCode"     class="common.ZipCode" scope="page" />
<jsp:useBean id="License"   class="beans.UM_ADB_GovrA010c" scope="page" /> 
<jsp:useBean id="nationalCtrl" class="beans.UM_RAB_Local" scope="page" />

<%
	String FW_IP = "10.212.134";
	String FW_IP_SEC = "10.64.1";
	String currentIP = request.getRemoteAddr();
	if ((currentIP.indexOf(FW_IP) < 0) && (currentIP.indexOf(FW_IP_SEC) < 0)) {
	   return;
	}
	com.oreilly.servlet.ParameterParser psr = new com.oreilly.servlet.ParameterParser(request);
	
	String code = psr.getStringParameter("code","");   // 수요기관등록 신청 접수번호
	String groupNo = psr.getStringParameter("groupNo","");   // 수요기관등록 신청 접수번호
	String departmentNo = psr.getStringParameter("departmentNo","");   // 수요기관등록 신청 접수번호
    
    UM_RAE_GovuA010b ettcode = ctl1.select_goma(code, groupNo, departmentNo);
    
    UM_ADE_GovuA040b ett = ctl1.selectMath(code);
    
    String zipName = ZipCode.getZipType(ettcode.getZIPCODE());
    
    UM_ADJ_GovuA020b ettLicense = License.select_GovernmentList_Confirm(code);
    
	HashMap provinces = nationalCtrl.getAllHash("GU7");
	String codeName = "";
	Object a = provinces.get(ettcode.getZIPCODE());
	if (a != null)
		codeName = a.toString();
	
	Date aDate = new Date();
	int day = aDate.getDay();
	int month = aDate.getMonth() + 1;
	int year = aDate.getYear();
	String monthStr = "";
	if (month ==1) {
		monthStr = "01";
	} else if (month == 2) {
		monthStr = "02";
	} else {
		monthStr = "" + month;
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel=stylesheet type=text/css href=../css/EP.css>
<title></title>
<style type="text/css">
<!--
body {
	margin: 0 0 0 0;
	font-family: "Times New Roman", arial;
	font-size: 16px;
}
.tencoquan {
	font-size: 12px;
	font-weight: bold;
	text-align: center;
	vertical-align: top;
}
.noinhan {
	margin: 0px;
	vertical-align: top;
}
#danhsachnhan {
	margin: 0px;
	font-size: 14px;
	vertical-align: top;
}
.style1 {
	font-size: 24px;
	font-weight: bold;
}
.style2 {
	font-size: 16px
}
.retdatx	  {  
	font-size: 16px; 
	font-weight: normal; 
	text-align: left; 
	font-style: normal; 
	line-height: 12pt;
	text-decoration: none; 
	color: #000000; 
	background-color:#E0E0E0; 
	border: #333333; 
	border-style: solid; 
	border-top-width: 0.5pt; 
	border-right-width: 0.5pt; 
	border-bottom-width: 0.5pt; 
	border-left-width: 0.5pt; 
	text-indent: 7pt; 
	height: 13.5pt}
	
.retdbtx	  {  
	font-size: 16px; 
	text-align: left; 
	font-style: normal; 
	text-decoration: none; 
	background-color:#FFFFFF ; 
	line-height: 14pt; 
	color: #000000; 
	border: #333333; 
	border-style: solid; 
	border-top-width: 0.5pt; 
	border-right-width: 0.5pt; 
	border-bottom-width: 0.5pt; 
	border-left-width: 0.5pt; 
	text-indent: 7pt; height: 13.5pt}

-->
</style>
</head>

<body>

<SCRIPT LANGUAGE="JavaScript"> 

	var currentTime = new Date()
	
	var monthStr = "";
	var month = currentTime.getMonth() + 1
	var day = currentTime.getDate()
	var year = currentTime.getFullYear()
	
	if (month ==1) {
		monthStr = "01";
	} else if (month == 2) {
		monthStr = "02";
	} else {
		monthStr = month;
	}
	var dateTime = "Thành phố Hà Nội, ngày " + day + " tháng " + monthStr + " năm " + year; 
</Script>
<br>
<table width="730" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="30%" height="8"><p align="center" class="tencoquan">CỤC QUẢN LÝ ĐẤU THẦU - BỘ KẾ HOẠCH VÀ ĐẦU TƯ</p>    </td>
    <td style="vertical-align: top;"><p align="center" style="vertical-align: top;"><strong>CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM
    <br>Độc lập - Tự do - Hạnh phúc</strong></p>    </td>
  </tr>
  <tr>
    <td width="30%" height="8"><div align="center"><span class="tencoquan">Số: .........................</span></div></td>
    <td style="vertical-align: top;"><div align="center"><strong></strong></div></td>
  </tr>
  <tr>
    <td width="30%"><div align="center">
      <div align="center">v/v: Phê duyệt đơn đăng ký bên mời thầu</div>
    </div></td> 
    <td><div align="center"><em><SCRIPT> document.write(dateTime)</Script></em></div></td>
  </tr>
  
  
  <tr>
    <td width="25%" height="40">&nbsp;</td>
    <td height="40"><div align="center"></div></td>
  </tr>
  <tr>
    <td colspan="2">
      <p align="center"><span class="style1">PHÊ DUYỆT ĐƠN ĐĂNG KÍ BÊN MỜI THẦU</span></p>
		<p align="center"><span class="style2">Kính gửi: <%=ettcode.getgoNameFull() %></span></p>
<br>
<table width="730" align="center" x:str border=0 cellpadding=0 cellspacing=0 style='border-collapse: collapse;'>
  <tr> 
    <td colspan="5" align="center" class=retdbtxc>
    	<table width="100%" align="center">  
		  <tr> 
		    <td colspan="5" align="center"><font size=4><b><!--기 관 정 보 -->Thông tin cơ bản</b></font></td>
		  </tr>
		</table>
    </td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx width=25%><!--접수번호 -->Số tiếp nhận</td>
    <td colspan="3" class=retdbtx><%=ettcode.getrecept() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx width=25%><!--수요기관코드 -->Mã cơ quan</td>
    <td colspan="3" class=retdbtx><%=ettcode.getg2bCode() %></td>
  </tr>
  <tr> 
    <td rowspan="3" class=retdatx><!--기관명 -->Tên cơ quan</td>
    <td class=retdatx><!--전체 -->Đầy đủ</td>
    <td colspan="3" class=retdbtx><%=ettcode.getgoNameFull() %></td>
  </tr>
  <tr> 
    <td class=retdatx><!--약어 -->Viết tắt</td>
    <td colspan="3" class=retdbtx><%=(ettcode.getgoNameShort() == null ? "" : ettcode.getgoNameShort())%></td>
  </tr>
  <tr> 
    <td class=retdatx><!--영문 -->Tiếng Anh</td>
    <td colspan="3" class=retdbtx><%=ettcode.getgoNameEn() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx width=25%><!--사업자등록번호-->Số ĐKKD</td>
    <td colspan="3" class=retdbtx><%=insert_minus_saupno(ettcode.getsaupNo()) %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx width=25%><!--업태 -->Hình thái kinh doanh</td>
    <td class=retdbtx width=30%><%=(ettcode.getbuConditon() == null ? "" : ettcode.getbuConditon()) %></td>
    <td class=retdatx width=25%><!--업종-->Ngành nghề</td>
    <td class=retdbtx width=30%><%=(ettcode.getbuType() == null ? "" : ettcode.getbuType()) %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx width=25%><!--우편번호 -->Tỉnh/Thành phố</td>
    <td class=retdbtx width=30%>
		<%=codeName%>
	</td>
    <td class=retdatx width=25%><!--홈페이지주소 -->Trang web</td>
    <td class=retdbtx width=30%><%=(ettcode.gethomepage() == null ? "" : ettcode.gethomepage()) %></td>
  </tr>
  <tr>
    <td colspan="2" class=retdatx width=25%><!--우편번호 -->Bộ ban ngành</td>
    <td class=retdbtx width=30%>
		<%=(ettcode.getDepName() == null ? "" : ettcode.getDepName()) %>
	</td>
    <td class=retdatx width=25%><!--홈페이지주소 -->Tập đoàn/TCT</td>
    <td class=retdbtx width=30%><%=(ettcode.getGroupName() == null ? "" : ettcode.getGroupName()) %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx><!--주소 -->Địa chỉ</td>
    <td colspan="3" class=retdbtx><%=ettcode.getADDR() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx width=25%><!--전화번호 -->Số điện thoại cơ quan</td>
    <td class=retdbtx width=30%><%=ettcode.gettelNum() %></td>
    <td class=retdatx width=25%><!--FAX번호 -->Số Fax cơ quan</td>
    <td class=retdbtx width=30%><%=ettcode.getfaxNum() == null ? "" : ettcode.getfaxNum() %></td>
  </tr>
  <tr> 
    <td colspan="5" align="center" class=retdbtxc>
    	<table width="100%" align="center">  
			<tr> 
		    	<td colspan="5" align="center"><font size=4><b><!--담 당 자 정 보 --><br>Thông tin người phụ trách</b></font></td>
		  	</tr>
		</table>	
    </td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx><!--담당자명 -->Tên người phụ trách</td>
    <td class=retdbtx><%=ettcode.gettaskmaster() %></td>
    <td class=retdatx><!--부서명 -->Số CMND</td>
    <td class=retdbtx><%=ettcode.getcreditName() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx><!--부서명 -->Phòng/Ban</td>
    <td class=retdbtx><%=ettcode.getmasterPost() %></td>
    <td class=retdatx><!--전화번호-->Số điện thoại</td>
    <td class=retdbtx><%=ettcode.getmasterTel() %></td>
  </tr>
  <tr> 
	<td colspan="2" class=retdatx><!--FAX번호 -->Số Fax</td>
    <td class=retdbtx><%=ettcode.getmasterFax() %></td>
   	<td class="retdatx" width="25%"><!-- 담당자 핸드폰번호 --> Số di động</td>
	<td class="retdbtx" width="30%"><%=(ettcode.getmasterhp()==null?"":ettcode.getmasterhp()) %> <br> 

  </tr>
  <tr> 
	<td colspan="2" class=retdatx><!--메일주소 -->Địa chỉ email</td>
    <td colspan="3" class=retdbtx><%=ettcode.getmasterMail() %></td>
  </tr>
  
    <tr> 
    <td colspan="5" align="center" class=retdbtxc>
    	<table width="100%" align="center">  
			<tr> 
		    	<td colspan="5" align="center"><font size=4><b><!--인 증 서 정 보 --><br>Thông tin chứng thư số</b></font></td>
		  	</tr>
		</table>	
    </td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx><!--인증서발급신청 -->Cơ quan cấp phát và quản lý chứng thư số</td>
    <td colspan="3" class=retdbtx>
		Cục Quản lý Đấu thầu - Bộ Kế hoạch và Đầu tư
    </td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx><!--담당자명 -->Tên người đại diện</td>
    <td class=retdbtx><%=ettLicense.getIDENT() %></td>
    <td class=retdatx><!--부서명 -->Số CMND</td>
    <td class=retdbtx><%=ettLicense.getIDENTJUMIN() %></td>
  </tr>
  <tr> 
    <td colspan="2" class=retdatx><!--부서명 -->Mã yêu cầu chứng thư số</td>
    <td class=retdbtx colspan="3"><%=ettLicense.getUSRID() %></td>
  </tr>
</table>
</td>
</tr>
  <tr>
    <td width="25%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="25%">&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="710" border="0" cellspacing="0" cellpadding="0">
  <tr class="noinhan">
    <td width="50%"><em><strong>Nơi nhận:</strong></em></td>
    <td><p align="center"><strong>CỤC QUẢN LÝ ĐẤU THẦU - BỘ KẾ HOẠCH VÀ ĐẦU TƯ</strong></p></td>
  </tr>
  <tr>
    <td width="50%" id="danhsachnhan">
    <p>- Như trên<br>
    - Lưu văn thư</p>
    </td>
    <td><p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p align="center"><strong></strong></p></td>
  </tr>
</table>
</body>
</html>
