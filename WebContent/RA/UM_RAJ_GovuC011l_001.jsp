<%--
/************************************************************************/
/*                                                                      */
/*    Program ID    :     UM_RAJ_GovuC011l_001.jsp							*/
/*                                                                      */
/*    description   :     기관등록신청목록조회화면	Tra cứu đăng ký bên mời thầu					*/
/*                                                                      */
/************************************************************************/
/*  최초생성         2002.06.11          박 민 호                       */
/************************************************************************/
	
	2003.04.15	김영진	프로그램 정리
	2003.05.02	김영진	프로그램 다시 정리
						1.권한체크
						2.구조변경
						3.error Page 적용
						4.승인지청을 넘겨받던 것에서 cookie 값에서 읽음
						5.처음 화면 호출될때 신청기간 default로 한달 setting

    2003.05.15  권순재  조회결과가 1건일 경우 리스트 조회안되는 것 수정.
	2003.08.06  권순재  조회조건에 승인지청 추가
	2004.05.18  윤태우  검색리스트에 접수일자(신청일자) 컬럼추가
	2004.11.02	설원식	승인지청에 본청콜센터 추가
	2005.01.11	설원식	승인지청은 본청만 존재
	2007.04.30    임지훈    검색리스트에 보류추가
/***************************************************************************/
/* Program ID         : UM_RAJ_GovuC011l_001.jsp                        */
/* Program Explanation: Tra cứu đăng ký bên mời thầu				*/
/* Program Summary  : 
/* Relation Program   : 
/* Table              : 							*/
/***************************************************************************/
/* Customizing Composer : MR. MINH 25.05.2009                      	*/
/***************************************************************************/

--%>

<%@page language="java" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="../jsp/common/jspToError.jsp?type=0&url=&message=" %>
<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, common.*, common.util.*, LOGIN.*" %>
<%@ page import="g2b.sso.*" %>
<%@ page import="org.owasp.esapi.ESAPI" %>

<jsp:useBean id="ctl"	  class="beans.UM_URB_GovuA030c_001" scope="page" />
<jsp:useBean id="uagb030" class="beans.UM_ADB_GovrB030c" scope="page" />
<jsp:useBean id="comUtil" class="common.util.CommUtil"   scope="page" />
<jsp:useBean id="comWeb"  class="common.WebUtil"         scope="page" />
<jsp:useBean id="List"    class="beans.Common_ComBo"     scope="page" />
<jsp:useBean id="List01"  class="beans.Common_ComBo01"   scope="page" />

<%!
	final int PAGE_SIZE=200;
%>

<%
	SSO sso = new SSO(pageContext);
    // 권한체크
    UM_Auth_Check uac=new UM_Auth_Check(request, response, sso);
    uac.checkCookie("k",null,null);

//    String ApprovalCode=uagb030.getApprovalCode(uac.getID());
    String ApprovalCode = "";	//승인지청
    String upcheNm		= "";	//업체명
    String total		= "";	//업체수
    String Start		= "";	//시작일자
    String End			= "";	//종료일자
    String page_no		= "";	//페이지번호
	ApprovalCode = comUtil.retSpace(request.getParameter("ApprovalCode"));
    upcheNm      = comUtil.retSpace(request.getParameter("upcheNm"));
    total        = comUtil.retSpace(request.getParameter("total"));
    Start        = comUtil.retSpace(request.getParameter("Start"));
    End          = comUtil.retSpace(request.getParameter("End"));
	page_no      = comUtil.retSpace(request.getParameter("page_no"));
	String InstituCd = comUtil.retSpace(request.getParameter("InstituCd"));

	//encodeForHTML
    if (page_no.equals(""))	page_no="1";
    
    // 처음 호출된 경우
    if(total == ""){
    //	Start=comSer.getCurrDate(-30,0);
    //	End=comSer.getCurrDate(0,0);
    	total="2";	//승인구분 미승인으로 setting
    } 
	int totalCount=0;
	UM_ADV_GovuA030b[] ettlist = null;
	//if (upcheNm != "") {
		if (InstituCd != null || !"".equals(InstituCd))
		{
			//ctl.DeleteBizRegNo();
			ctl.DeleteInstituCd(InstituCd);
		}
		totalCount=ctl.approvalList_count(upcheNm, Start, End, "S", total, ApprovalCode);
		if(totalCount > 0){
			//ctl.DeleteInstituCd();
			//ctl.DeleteBizRegNoSelect();
			//ctl.UpdateSynPubCode();
			ettlist = ctl.approvalList(Integer.parseInt(page_no), PAGE_SIZE, upcheNm, Start, End, "S", total, ApprovalCode);
		}	
	//}	
%>

<html> 
<head>
<!-- 기관등록 신청 목록조회 -->
<title>Xóa dữ liệu Bên mời thầu</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/TA.css">
  
<script language="javascript" src="../js/UM.js"></script>
<script language="javascript" src="../calendar/js/calendar.js"></script>
<SCRIPT LANGUAGE="JavaScript">
	function checkform(){

		if(document.useForm.Start.value.length > 0 || document.useForm.End.value.length > 0){
			if(document.useForm.Start.value.length==0){
				//alert("신청기간 From 을 입력바랍니다.");
				alert("Hãy nhập ngày bắt đầu cho thời gian đăng ký.");
				document.useForm.Start.focus();
				return;
			}
			
			if(document.useForm.End.value.length==0){
				//alert("신청기간 To 을 입력바랍니다.");
				alert("Hãy nhập ngày kết thúc cho thời gian đăng ký.");
				document.useForm.End.focus();
				return;
			}
		}
		var start   = document.useForm.Start.value;
		var end     = document.useForm.End.value;
		
		var str_sdate =js_removeChar(start,'/');
        var str_edate =js_removeChar(end,'/');
        var sdate = new Date(str_sdate.substring(0, 2), str_sdate.substring(2, 6), str_sdate.substring(6, 8));
        sdate = sdate.getTime();
        
        var edate = new Date(str_edate.substring(0, 2), str_edate.substring(2, 6), str_edate.substring(6, 8));
        edate = edate.getTime();
		if (sdate > edate) {
			alert(" Hãy nhập nhập thời gian tìm kiếm theo dạng Từ ~ Đến.");
			return;
		}
		
		document.useForm.target="_self"
		document.useForm.method="post";
		document.useForm.action="UM_RAJ_GovuC011l_001.jsp"
		document.useForm.submit();
		return;
	}
	
	//enter
	function search_enter() {
        if(event.keyCode ==13){
			checkform();
		}
	}

    function js_searchDate(formname, boxname, gubun){
        url = "http://muasamcong.mpi.gov.vn:8070/jsp/common/calendar.jsp?form_name=" + formname + "&box_name=" + boxname + "&gubun=" + gubun;	window.open(url,"Day_Search","directories=no,resizable=yes,scrollbars=no,location=no,width=181,height=205");
    }
    
    function toView(agr_g2bCode,agr_recept,agr_LicenseCode,agr_ApprovalCode){
    	document.viewData.masterCode.value = agr_g2bCode;
    	document.viewData.recept.value = agr_recept;
    	document.viewData.LicenseCode.value = agr_LicenseCode;
    	document.viewData.ApprovalCode.value = agr_ApprovalCode;
    	document.viewData.target = "_self"
		document.viewData.method = "post";
		document.viewData.action = "UM_RAJ_GovrA010u.jsp"
		document.viewData.submit();
		return;
    }

    function deleteInstituCd(InstituCd)
    {
    	document.useForm.InstituCd.value = InstituCd;
		document.useForm.target="_self"
		document.useForm.method="post";
		document.useForm.action="UM_RAJ_GovuC011l_001.jsp"
		document.useForm.submit();
   	}
    
    
</Script>
</head>
<body>

<div class="wrappertable">
<div class="wrapperhead"><span class="HEADLINENEW"> Xóa dữ liệu Bên mời thầu </span></div>
<div class="contenttable">

<form name="useForm">	
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">	
		<tr>
			<td height="4" colspan="5" class="line"></td>
		</tr>        
		<tr height=30> 
			<td width="150"	class="tdar"><!-- 수요기관명 --> Tên bên mời thầu</td>
			<td width="300"	class="tdb" colspan="3"><input type="text" name="upcheNm"	value="<%=upcheNm%>" class="read" size="20" maxlength="20">	</td>
		</tr>
		<tr height=30>
			<td class="tdar" width="150"><!-- 상태구분 -->Phân loại trạng thái</td>
			<td class="tdb" colspan="3">
			<% if (total.length() > 0) { %>
				<input type="radio"		name="total"	value="0" onfocus="blur()" <%=(total.equals("0"))? "checked " :"" %>><!-- 전체 --> Tất cả
				<input type="radio"		name="total"	value="1" onfocus="blur()" <%=(total.equals("1"))? "checked " :"" %>><!-- 승인 --> Phê duyệt
				<input type="radio"		name="total"	value="2" onfocus="blur()" <%=(total.equals("2"))? "checked " :"" %>><!-- 미승인 --> Chưa phê duyệt
				<input type="radio"		name="total"	value="4" onfocus="blur()" <%=(total.equals("4"))? "checked " :"" %>><!-- 취소 --> Bảo lưu
				<input type="radio"		name="total"	value="5" onfocus="blur()" <%=(total.equals("5"))? "checked " :"" %>><!-- 취소 --> Từ chối
				<input type="radio"		name="total"	value="3" onfocus="blur()" <%=(total.equals("3"))? "checked " :"" %>><!-- 보류 --> Hủy bỏ
			<% } else { %>
				<input type="radio"		name="total"	value="0" onfocus="blur()"><!-- 전체 -->Tất cả
				<input type="radio"		name="total"	value="1" onfocus="blur()"><!-- 승인 -->Phê duyệt
				<input type="radio"		name="total"	value="2" onfocus="blur()" checked><!-- 미승인 -->Chưa phê duyệt
				<input type="radio"		name="total"	value="4" onfocus="blur()" ><!-- 취소 -->Bảo lưu
				<input type="radio"		name="total"	value="5" onfocus="blur()" ><!-- 취소 -->Từ chối
				<input type="radio"		name="total"	value="3" onfocus="blur()" ><!-- 보류 -->Hủy bỏ
			<% }		%>
			</td>
		</tr>
		<tr height="30">
			<td width="150" class="tdar"><!-- 신청기간 -->Thời gian đăng ký </td>
			<td class="tdb" colspan="3">
				<input type="text" name="Start" value="<%=Start%>"  size="12" maxlength="8" class="read" onkeydown="js_OnlyNumber(this)" onBlur='js_DateType(this,"/")' onFocus="js_removeChar2(this)">
    		        <img name="ImgbCalendar" border="0" src="/img/calendar.gif" onClick="Calendar_D(document.useForm.Start)"> ~ 
                <input type="text" name="End" value="<%=End%>" size="12" maxlength="8" class="read" onkeydown="js_OnlyNumber(this)" onBlur='js_DateType(this,"/")' onFocus="js_removeChar2(this)">
            		<img name="ImgbCalendar" border="0" src="/img/calendar.gif" onClick="Calendar_D(document.useForm.End)">
            		<input type="button" class="commonbutton" value=" Tìm kiếm" onclick="javascript:checkform();">
            		<input type=hidden name="InstituCd">
			</td>
		</tr>
		<tr><td height="2" colspan="7" class="line"></td></tr>
	</table>
</form>
	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td class='page'><img src="../img/page.gif" align="absmiddle"><!-- 검색건수 건 --> [Tổng số: <%=Integer.toString(totalCount)%><!--  --> kết quả]</td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
		 <tr> 
            <td height="4" colspan="8" class="line"></td>
		 </tr>   	
		<tr height="30"> 
			<td	width="40"		class="tdac"><!-- 번호 -->STT</td>
			<td	width="90"		class="tdac"><!-- 신청일자 -->Ngày đăng ký</td>
			<td	width="160"		class="tdac"><!-- 수요기관명 -->Tên cơ quan bên mời thầu</td>
			<td	width="120"		class="tdac"><!-- 사업자등록번호 -->Số ĐKKD</td>
			<td	width="100"		class="tdac"><!-- 담당자명 -->Tên người phụ trách</td>
			<td	width="240"		class="tdac"><!-- 주&nbsp;&nbsp;&nbsp;소 -->Địa chỉ</td>
			<td	width="70"		class="tdac"><!-- 여부 -->Trạng thái</td>
			<td	width="70"		class="tdac"><!-- 여부 -->Xóa</td>
		</tr>
<%	if (totalCount >0) {
		for(int i=0; i < ettlist.length; i++) { 
%> 
		<tr>
			<td width="40" class="tdbc"><%=((Integer.parseInt(page_no)-1)*PAGE_SIZE)+i+1%></td>
			<td width="90"	class="tdbc"><%=ettlist[i].getrecept()%></td>
			<td width="160"	class="tdb">
				<a href="javascript:toView('<%=ettlist[i].getg2bCode()%>','<%=ettlist[i].getdependCode()%>','<%=ettlist[i].getLicenseCode()%>','<%=ettlist[i].getLicenseCode()%>');">
                	<%=ettlist[i].getgoNameFull()%>
                </a>
            </td>
			<td width="120"	class="tdbc"><%=ettlist[i].getsaupNo()%></td>
			<td width="100"	class="tdbc"><%=ettlist[i].gettaskmaster()%></td>
			<td width="240"	class="tdb">&nbsp;<%=ettlist[i].getADDR()%></td>
			<td width="70"	class="tdbc">
				<%	if (("N").equals(ettlist[i].getenYn())) { %><!-- 미승인 -->Chưa phê duyệt
				<%	}else if (("Y").equals(ettlist[i].getenYn())) { %><!-- 승인 -->Phê duyệt
				<%	}else if (("D").equals(ettlist[i].getenYn())) { %><!-- 반려 -->Từ chối
				<%	}else if (("C").equals(ettlist[i].getenYn())) { %><!-- 취소 -->Hủy
				<%	}else if (("E").equals(ettlist[i].getenYn())) { %><!-- 보류 -->Bảo lưu
                <%  }%>
			</td>
			<td width="70"	class="tdbc"><input type="button" class="commonbutton" name="btnOpinion" value="Delete" onclick="deleteInstituCd('<%=ettlist[i].getg2bCode()%>');"></td>
		</tr>
<%		}
	} 
	if (totalCount == 0) {	
%>
		<tr>
			<td class="tdbc" colspan="8"><!-- 조회결과가 없습니다.[검색조건 입력후 조회 바랍니다.] -->Không có kết quả tìm kiếm.[Nhập điều kiện tìm kiếm để tìm kiếm.]</td>	
		</tr>
<%	} %>
		 <tr> 
            <td height="2" colspan="8" class="line"></td>
         </tr> 
	</table> 
<form name="viewData">
<input type=hidden name="masterCode">
<input type=hidden name="recept">
<input type=hidden name="LicenseCode">
<input type=hidden name="ApprovalCode" value="00">

</form>
<%
	out.println(CommPageUtil.getNextPageIndexes(request, null, totalCount, PAGE_SIZE, Integer.parseInt(page_no))); 
%>
	</div>
	<div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
	</div>
  </form>
</body>
</html>
