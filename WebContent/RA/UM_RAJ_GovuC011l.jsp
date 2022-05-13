<%--
/************************************************************************/
/*                                                                      */
/*    Program ID    :     UM_RAJ_GovuC011l.jsp							*/
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
/* Program ID         : UM_RAJ_GovuC011l.jsp                        */
/* Program Explanation: Tra cứu đăng ký bên mời thầu				*/
/* Program Summary  : 
/* Relation Program   : 
/* Table              : 							*/
/***************************************************************************/
/* Customizing Composer : MR. MINH 25.05.2009                      	*/
/***************************************************************************/

--%>

<%@page language="java"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ page errorPage="../jsp/common/jspToError.jsp?type=0&url=&message="%>
<%@page
	import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, common.*, common.util.*, LOGIN.*"%>
<%@ page import="g2b.sso.*"%>

<jsp:useBean id="ctl" class="beans.UM_URB_GovuA030c" scope="page" />
<jsp:useBean id="uagb030" class="beans.UM_ADB_GovrB030c" scope="page" />
<jsp:useBean id="comUtil" class="common.util.CommUtil" scope="page" />
<jsp:useBean id="comWeb" class="common.WebUtil" scope="page" />
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />
<jsp:useBean id="List01" class="beans.Common_ComBo01" scope="page" />
<jsp:useBean id="nationalCtrl" class="beans.NationalRefDAO" scope="page" />
<jsp:useBean id="permission" class="servlets.EP_MDB_GGA557" scope="page" />



<%!final int PAGE_SIZE = 10;%>

<%
	SSO sso = new SSO(pageContext);
	String urlPamramter = request.getQueryString();
	String url = request.getRequestURL().toString();
	if (urlPamramter != null)
		url += "?" + urlPamramter;

	String userID = sso.getID();
	//check xem menu nay co phan quyen khong
	boolean checkPer = permission.checkPermissionByURL(userID, url);

	if (checkPer) {
		out.println("<script>alert('Bạn không có quyền truy cập trang này');  main.location.href='http://muasamcong.mpi.gov.vn:8070/loginNew.jsp'</script>");
		return;
	}

	// 권한체크
	//             UM_Auth_Check uac = new UM_Auth_Check(request, response, sso);
	//             uac.checkCookie("k", null, null);

	//    String ApprovalCode=uagb030.getApprovalCode(uac.getID());
	String ApprovalCode = ""; //승인지청
	String area = ""; //승인지청
	String instituCd = ""; //승인지청
	String upcheNm = ""; //업체명
	String total = ""; //업체수
	String Start = ""; //시작일자
	String End = ""; //종료일자
	String page_no = ""; //페이지번호
	ApprovalCode = comUtil.retSpace(request
			.getParameter("ApprovalCode"));
	area = comUtil.retSpace(request.getParameter("area"));
	instituCd = comUtil.retSpace(request.getParameter("instituCd"));
	Log.debug("Tinh / Thanh pho: " + area);
	upcheNm = comUtil.retSpace(request.getParameter("upcheNm"));
	total = comUtil.retSpace(request.getParameter("total"));
	Start = comUtil.retSpace(request.getParameter("Start"));
	End = comUtil.retSpace(request.getParameter("End"));
	page_no = comUtil.retSpace(request.getParameter("page_no"));

	if (page_no.equals("")) {
		page_no = "1";
	}

	// 처음 호출된 경우
	if (total == "") {
		//	Start=comSer.getCurrDate(-30,0);
		//	End=comSer.getCurrDate(0,0);
		total = "2"; //승인구분 미승인으로 setting
	}
	Log.debug("Total: " + total);
	int totalCount = 0;
	UM_ADV_GovuA030b[] ettlist = null;
	//if (upcheNm != "") {
	totalCount = ctl.approvalList_count(upcheNm, Start, End, "S",
			total, ApprovalCode, area, instituCd);
	Log.errors("totalCount:"+totalCount);
	if (totalCount > 0) {
		ettlist = ctl.approvalList(Integer.parseInt(page_no),
				PAGE_SIZE, upcheNm, Start, End, "S", total,
				ApprovalCode, area, instituCd);
	}
	//}
%>

<html>
<head>
<!-- 기관등록 신청 목록조회 -->
<title>Tra cứu đăng kí bên mời thầu</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css"
	rel="stylesheet" type="text/css" />
<link href="http://muasamcong.mpi.gov.vn/css/TA.css" rel="stylesheet"
	type="text/css" />

<script language="javascript" src="../js/UM.js"></script>
<script language="javascript" src="../calendar/js/calendar.js"></script>
<SCRIPT LANGUAGE="JavaScript">
	function doCheckAll(cb_obj) {
		var cb_index = document.getElementsByName("cb_selectedIndex[]");
		if( (cb_index !== undefined) && (cb_index != null) && (cb_index.length > 0) ) {
			for(var i = 0; i < cb_index.length; i++) {
				cb_index[i].checked = cb_obj.checked;
			}
		}
	}
	
	function doValidCheck(cb_obj) {
		var checkedAll = true;
		var cb_checkAll = document.getElementById("cb_checkAll");
		if( (cb_checkAll !== undefined) && (cb_checkAll != null) ) {
			if(!cb_obj.checked) {
				cb_checkAll.checked = false;	
			} else {
				var cb_index = document.getElementsByName("cb_selectedIndex[]");
				var isCheckAll = true;
				for(var i = 0; i < cb_index.length; i++) {
					if(!cb_index[i].checked) {
						isCheckAll = false;
						break;
					}
				}
				cb_checkAll.checked = isCheckAll;
			}
		}
	}
	
	function doSubmitGroup() {
		
		var cb_index = document.getElementsByName("cb_selectedIndex[]");
		if( (cb_index === undefined) || (cb_index == null) || (cb_index.length == 0) ) {
			alert("Không có cơ quan nào để phê duyệt !");
			return false;
		}
		////////////
		var havingSelected = false;
		for(var i = 0; i < cb_index.length; i++) {
			if(cb_index[i].checked) {
				havingSelected = true;
				break;
			}
		}
		if(!havingSelected) {
			alert("Hãy chọn ít nhất 1 cơ quan để phê duyệt");
			return false;
		}
		if(!confirm("Bạn chắc chắn muốn phê duyệt các cơ quan đã chọn ?")) {
			return;
		}
		///////////
		window.open("",'denyWin','status=yes,resizable=yes,scrollbars=yes,toolbar=no,width=600,height=500'); 
		document.frmDataList.target="denyWin"; 
        document.frmDataList.method='post'; 
        document.frmDataList.action='/servlet/servlets/UM_ADJ_GovrB060c'; 
        document.frmDataList.submit();
        document.getElementById("btn_submitGroup").disabled = true;
		return;
	}
	
	function checkform() {
<%-- if(document.useForm.Start.value.length > 0 || document.useForm.End.value.length > 0){
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
                }--%>
	// 날짜 From~To 체크
		var str_yipchal1 = document.useForm.Start.value;
		var str_yipchal2 = document.useForm.End.value;

		var str_yipchal1_no_slash = js_removeChar(str_yipchal1, '/');
		var str_yipchal2_no_slash = js_removeChar(str_yipchal2, '/');

		var num_yipchal1 = Number(str_yipchal1_no_slash);
		var num_yipchal2 = Number(str_yipchal2_no_slash);
<%--if(!js_Compare(num_yipchal1,num_yipchal2)){
                    //alert("신청기간은 From ~ To 형식으로 입력 바랍니다.");
                    alert("Hãy nhập thời gian đăng ký theo định dạng Từ ngày ~ Đến ngày.");
                    document.useForm.Start.focus();
                    return;
                }--%>
	if (compareDate(str_yipchal1, str_yipchal2)) {
			document.useForm.target = "_self"
			document.useForm.method = "post";
			document.useForm.action = "UM_RAJ_GovuC011l.jsp"
			document.useForm.submit();
		}
		return;
	}

	function compareDate(date1, date2) {
		if (date1 != '' && date2 != '') {
			if (date1.indexOf("/") > 0 || date2.indexOf("/") > 0) {
				var year1 = date1.substring(6);
				var month1 = date1.substring(3, 5);
				var day1 = date1.substring(0, 2);
				var dt1 = parseInt(year1 + month1 + day1);

				var year2 = date2.substring(6);
				var month2 = date2.substring(3, 5);
				var day2 = date2.substring(0, 2);
				var dt2 = parseInt(year2 + month2 + day2);

				var sub = dt2 - dt1;
				if (sub > 0) {

					return true;
				}
				alert("Kiểm tra lại khoảng thời gian tìm kiếm!");
				return false;
			}

			return true;

		} else {
			return true;
		}
	}

	//enter
	function search_enter() {
		if (event.keyCode == 13) {
			checkform();
		}
	}

	function js_searchDate(formname, boxname, gubun) {
		url = "http://muasamcong.mpi.gov.vn:8070/jsp/common/calendar.jsp?form_name="
				+ formname + "&box_name=" + boxname + "&gubun=" + gubun;
		window
				.open(url, "Day_Search",
						"directories=no,resizable=yes,scrollbars=no,location=no,width=181,height=205");
	}

	function toView(agr_g2bCode, agr_recept, agr_LicenseCode, agr_ApprovalCode,
			groupNo, departmentNo) {

		document.viewData.masterCode.value = agr_g2bCode;
		document.viewData.recept.value = agr_recept;
		document.viewData.LicenseCode.value = agr_LicenseCode;
		document.viewData.groupNo.value = groupNo;
		document.viewData.departmentNo.value = departmentNo;
		document.viewData.target = "_self"
		document.viewData.method = "post";
		document.viewData.action = "UM_RAJ_GovrA010u.jsp"
		document.viewData.submit();
		return;
	}
</Script>
</head>
<body>

	<div class="col-750 clearfix last">
		<h1 class="pageTitle">
			<i class="icon-title"></i>Tra cứu đăng kí bên mời thầu
		</h1>

		<form name="useForm">
			<table width="100%" border="0" cellspacing="1" cellpadding="2"
				class="tr">
				<tr>
					<td height="4" colspan="4" class="line"></td>
				</tr>
				<tr>
					<td style="width: 20%" class="tdar">
						<!-- 수요기관명 --> Mã cơ quan
					</td>
					<td colspan="3" class="tdb"><input type="text"
						name="instituCd" value="<%=instituCd%>" class="read" size="20">
					</td>
				</tr>
				<tr height=30>
					<td style="width: 20%" class="tdar">
						<!-- 수요기관명 --> Tên bên mời thầu
					</td>
					<td colspan="3" class="tdb"><input type="text" name="upcheNm"
						value="<%=upcheNm%>" class="read" style="width: 100%"></td>

				</tr>
				<tr height=30>
					<td class="tdar" style="width: 20%">
						<!-- 상태구분 -->Phân loại trạng thái
					</td>
					<td class="tdb" colspan="3" style="width: 80%">
						<%
							if (total.length() > 0) {
						%> <input type="radio" name="total"
						value="0" onfocus="blur()"
						<%=(total.equals("0")) ? "checked " : ""%>>
					<!-- 전체 --> Tất cả <input type="radio" name="total" value="1"
						onfocus="blur()" <%=(total.equals("1")) ? "checked " : ""%>>
					<!-- 승인 --> Phê duyệt <input type="radio" name="total" value="2"
						onfocus="blur()" <%=(total.equals("2")) ? "checked " : ""%>>
					<!-- 미승인 --> Chưa phê duyệt <input type="radio" name="total"
						value="3" onfocus="blur()"
						<%=(total.equals("3")) ? "checked " : ""%>>
					<!-- 취소 --> Bảo lưu <input type="radio" name="total" value="4"
						onfocus="blur()" <%=(total.equals("4")) ? "checked " : ""%>>
					<!-- 취소 --> Từ chối <input type="radio" name="total" value="5"
						onfocus="blur()" <%=(total.equals("5")) ? "checked " : ""%>>
					<!-- 보류 --> Hủy bỏ <%
						} else {
					%> <input type="radio" name="total"
						value="0" onfocus="blur()">
					<!-- 전체 -->Tất cả <input type="radio" name="total" value="1"
						onfocus="blur()">
					<!-- 승인 -->Phê duyệt <input type="radio" name="total" value="2"
						onfocus="blur()" checked>
					<!-- 미승인 -->Chưa phê duyệt <input type="radio" name="total"
						value="3" onfocus="blur()">
					<!-- 취소 -->Bảo lưu <input type="radio" name="total" value="4"
						onfocus="blur()">
					<!-- 취소 -->Từ chối <input type="radio" name="total" value="5"
						onfocus="blur()">
					<!-- 보류 -->Hủy bỏ <%
						}
					%>
					</td>
				</tr>
				<tr>
					<td style="width: 20%" class="tdar">Tỉnh / Thành phố<!--승인지청--></td>
					<td class="tdb" style="width: 30%">
						<%
							List provinces = nationalCtrl.getList("GU7");
						%> <select name="area" title="">
							<option value="" <%if (area.equals("") || area == null) {%>
								selected <%}%>>------ Lựa chọn ------</option>
							<%
								String pItem = "";
								for (int i = 0; i < provinces.size(); i++) {
									pItem = provinces.get(i).toString();
									String naCode = pItem.split("#")[1];
									String naName = pItem.split("#")[0];
							%>
							<option value="<%=naCode%>"
								<%if (area != null && naCode.equals(area)) {%> selected <%}%>><%=naName%></option>
							<%
								}
							%>

					</select>
					</td>
					<td style="width: 15%" class="tdar">
						<!-- 신청기간 -->Thời gian đăng ký
					</td>
					<td class="tdb" style="width: 35%"><input type="text"
						name="Start" value="<%=Start%>" size="12" maxlength="8"
						class="read" onkeydown="js_OnlyNumber(this)"
						onBlur='js_DateType(this,"/")' onFocus="js_removeChar2(this)">
						<img name="ImgbCalendar" border="0" src="/img/calendar.gif"
						onClick="Calendar_D(document.useForm.Start)"> ~ <input
						type="text" name="End" value="<%=End%>" size="12" maxlength="8"
						class="read" onkeydown="js_OnlyNumber(this)"
						onBlur='js_DateType(this,"/")' onFocus="js_removeChar2(this)">
						<img name="ImgbCalendar" border="0" src="/img/calendar.gif"
						onClick="Calendar_D(document.useForm.End)"></td>
				</tr>
				<tr>
					<td height="2" colspan="4" class="line"></td>
				</tr>
				<tr>
					<td align="center" colspan="4"><input type="button"
						class="commonbutton" value=" Tìm kiếm"
						onclick="javascript:checkform();"></td>
				</tr>
			</table>
		</form>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class='page'><img src="../img/page.gif" align="absmiddle">
				<!-- 검색건수 건 --> [Tổng số: <%=Integer.toString(totalCount)%><!--  -->
					kết quả]</td>
			</tr>
		</table>
		<%
			if (totalCount > 0) {
		%>
		<form name="frmDataList">
			<table width="100%" border="0" cellspacing="1" cellpadding="2"
				class="tr">
				<tr>
					<td height="4" colspan="10" class="line"></td>
				</tr>
				<tr height="30">
					<td width="3%" class="tdac">
						<!-- 번호 -->STT
					</td>
					<td width="12%" class="tdac">
						<!-- 신청일자 -->Ngày đăng ký
					</td>
					<td width="15%" class="tdac">
						<!-- 수요기관명 -->Tên cơ quan bên mời thầu
					</td>
					<td width="8%" class="tdac">
						<!-- 수요기관명 -->Mã cơ quan
					</td>
					<td width="8%" class="tdac">
						<!-- 사업자등록번호 -->Số ĐKKD
					</td>
					<td width="15%" class="tdac">
						<!-- 담당자명 -->Tên người phụ trách
					</td>
					<td width="14%" class="tdac">
						<!-- 주&nbsp;&nbsp;&nbsp;소 -->Địa chỉ
					</td>
					<td width="12%" class="tdac">
						<!-- 여부 -->Trạng thái
					</td>
					<td width="13%" class="tdac">
						<!-- 여부 -->Tỉnh / Thành phố
					</td>
					<td class="tdac">
						<input type="checkbox" name="cb_checkAll" id ="cb_checkAll" onclick="doCheckAll(this)"/>
					</td>
				</tr>
				<%
					for (int i = 0; i < ettlist.length; i++) {
						int rowIndex = ((Integer.parseInt(page_no) - 1) * PAGE_SIZE) + i + 1;
				%>
				<tr>
					<td class="tdbc"><%=rowIndex%></td>
					<td class="tdbc"><%=ettlist[i].getrecept()%></td>
					<td class="tdb"><a
						href="javascript:toView('<%=ettlist[i].getg2bCode()%>','<%=ettlist[i].getdependCode()%>','<%=ettlist[i].getLicenseCode()%>','<%=ettlist[i].getLicenseCode()%>','<%=ettlist[i].getGroupNo()%>','<%=ettlist[i].getDepartmentNo()%>');">
							<%=ettlist[i].getgoNameFull()%>
						</a>
						<input type="hidden" id="masterCode_<%=rowIndex %>" name="masterCode_<%=rowIndex %>" value="<%=ettlist[i].getg2bCode()%>"/>
						<input type="hidden" id="recept_<%=rowIndex %>" name="recept_<%=rowIndex %>" value="<%=ettlist[i].getdependCode()%>"/>
						<input type="hidden" id="LicenseCode_<%=rowIndex %>" name="LicenseCode_<%=rowIndex %>" value="<%=ettlist[i].getLicenseCode()%>"/>
						<input type="hidden" id="groupNo_<%=rowIndex %>" name="groupNo_<%=rowIndex %>" value="<%=ettlist[i].getGroupNo()%>"/>
						<input type="hidden" id="departmentNo_<%=rowIndex %>"  name="departmentNo_<%=rowIndex %>"value="<%=ettlist[i].getDepartmentNo()%>"/>
					</td>
					<td class="tdbc"><%=ettlist[i].getg2bCode()%></td>
					<td class="tdbc"><%=ettlist[i].getsaupNo()%></td>
					<td class="tdbc"><%=ettlist[i].gettaskmaster()%></td>
					<td class="tdb">&nbsp;<%=ettlist[i].getADDR()%></td>
					<td class="tdbc">
						<%
							if (("N").equals(ettlist[i].getenYn())) {
						%><!-- 미승인 -->Chưa phê
						duyệt <%
							} else if (("Y").equals(ettlist[i].getenYn())) {
						%><!-- 승인 -->Phê
						duyệt <%
							} else if (("D").equals(ettlist[i].getenYn())) {
						%><!-- 반려 -->Từ
						chối <%
							} else if (("C").equals(ettlist[i].getenYn())) {
						%><!-- 취소 -->Hủy
						<%
							} else if (("E").equals(ettlist[i].getenYn())) {
						%><!-- 보류 -->Bảo
						lưu <%
							}
						%>
					</td>
					<td class="tdbc"><%=ettlist[i].getZIPCODE()%></td>
					<td class="tdbc">
                    	<%	if ("N".equals(ettlist[i].getenYn())) { %>
                    		<input type="checkbox" name="cb_selectedIndex[]" value="<%=rowIndex%>" onclick="doValidCheck(this)"/>
                    	<% } else { %>
                    		<input type="checkbox" name="cb_selectedIndex1[]" value="<%=rowIndex%>" onclick="doValidCheck(this)" disabled="disabled"/>
                    	<% } %>
                    </td>
				</tr>
				<%
					}
				%>
	
	
				<tr>
					<td height="2" colspan="10" class="line"></td>
				</tr>
			</table>
		</form>

		<%
			out.println(CommPageUtil.getNextPageIndexes(request, null,
						totalCount, PAGE_SIZE, Integer.parseInt(page_no)));
		%>
		<div style="text-align: center">
			<input type="button" name="btn_submitGroup" id="btn_submitGroup" value="Phê duyệt" onclick="doSubmitGroup()" />
		</div>
		<%
			} else {
		%>
		<table>
			<tr>
				<td style="text-align: center;" colspan="8">
					<!-- 조회결과가 없습니다.[검색조건 입력후 조회 바랍니다.] -->Không có kết quả tìm
					kiếm.[Nhập điều kiện tìm kiếm để tìm kiếm.]
				</td>
			</tr>
		</table>
		<%
			}
		%>

		<form name="viewData">
			<input type=hidden name="masterCode"> <input type=hidden
				name="recept"> <input type=hidden name="LicenseCode">
			<input type=hidden name="ApprovalCode" value="00"> <input
				type=hidden name="groupNo"> <input type=hidden
				name="departmentNo">
		</form>


		<div class="wrapperfoot">
			<script language="javascript"
				src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script>
		</div>
	</div>
	</form>
</body>
</html>
