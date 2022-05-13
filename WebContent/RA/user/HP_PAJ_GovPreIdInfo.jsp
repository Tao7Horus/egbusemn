<%--
/***************************************************************************/
/* Program ID         : HP_PAJ_GovPreIdInfo.jsp                       */
/* Program Explanation: Danh mục người đại diện chủ đầu tư				*/
/* Program Summary  : 
/* Relation Program   : 
/* Table              : 							*/
/***************************************************************************/
/* Customizing Composer : MR. MINH 23.06.2009                      	*/
/***************************************************************************/

--%>

<%@ page language="java" %>

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="user.*" %>
<%@ page import="g2b.sso.*" %>
<%@ include file = "../../jsp/common/fnUmCommon.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	HP_PAE_UserInfo compEB = new HP_PAE_UserInfo();
	compEB = (HP_PAE_UserInfo)request.getAttribute("govEB");
	if(compEB.getUserid() != null && !"".equals(compEB.getUserid())) {
%>
<html>
<head>
<!-- 수요기관담당자목록 -->
<title>Danh mục người đại diện chủ đầu tư</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../css/TA.css" type="text/css">
<script language="javascript" src="../../js/userview.js"></script>
<script language="javascript" src="../../js/personinfo.js"></script>
<script>
function f_modifyUser(id)
{
	var url = "/servlet/user.HP_PAV_GovChange?userid=" + id;
	//launchCenter(url, "수요기관담당자수정", 650, 820);
	launchCenter(url, "Thay đổi người đại diện chủ đầu tư", 650, 820);
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" topmargin="0" leftmargin="0" background="../../img/bg01.gif">
  <table width="780" border="0" cellpadding="0" cellspacing="0" height="100%"  background="../../img/bg_sub.gif">
    <tr valign="top">
      <td width="780">
	<table width="100%" cellpadding="2" cellspacing="1">
				          <tr>
				            <td class="fontb"><!-- 이전담당자정보 -->[Thông tin người đại diện cũ]</td>
				          </tr>
				        </table>  
					<table width="100%" border="0" cellspacing="1" cellpadding="2">
						<tr>
							<td class="fontp"> 
							    *<font color=red><!-- 이전담당자정보</font>는 이전에 사용하시던 인증서에 사용되던 담당자정보 입니다.<br>
							    *이 정보를 현 인증서의 담당자 정보로 사용하시려면 리스트에서 <font color=red>사용하기</font>를 클릭하시면 됩니다 -->
							    Thông tin người đại diện cũ </font>là thông tin sử dụng chứng nhận số trước đây.<br>
							    *Nếu muốn sử dụng thông tin này làm thông tin của người đại diện hiện tại chỉ cần click vào
							    nút <font color=red>'Sử dụng'</font> trong danh sách.
							</td>
						</tr>
						<tr>
							<td height="5"> </td>
						</tr>
					</table>
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tdbc" bordercolor="#FFFFFF">
						<tr>
							<td height="4" class="line" colspan="8"></td>
						</tr>
						<tr class="tdar">
							<td class="tdar" width="15%">
								<div align="center"><!-- 아이디 -->ID</div>
							</td>
							<td class="tdar" width="15%">
								<div align="center"><!-- 담당자명 -->Tên người đại diện</div>
							</td>
							<td class="tdar" width="15%">
								<div align="center"><!-- 담당부서 -->Phòng ban</div>
							</td>
							<td class="tdar" width="15%">
								<div align="center"><!-- 전화번호 -->Số điện thoại</div>
							</td>
							<td class="tdar" width="15%">
								<div align="center"><!-- 등록일자 -->Ngày đăng ký</div>
							</td>
							<td class="tdar" >
								<div align="center"><!-- 현담당자정보로사용 -->Sử dụng làm thông tin người đại diện hiện tại</div>
							</td>
						</tr>
						<tr>
							<td class="tdbc"><a href="JavaScript:f_modifyUser('<%=compEB.getUserid()%>')"><%=compEB.getUserid()%></a></td>
							<td class="tdbc"><%=compEB.getUsername()%></td>
							<td class="tdbc"><%=compEB.getPartname()%></td>
							<td class="tdbc"><%=compEB.getUsertel1()%></td>
							<td class="tdbc"><%=compEB.getRegistdate1()%></td>
							<td class="tdbc"><a href="#" onclick=javascript:moveId() ><!-- 사용하기 -->Sử dụng</a></td>
						</tr>
						<form name=f method=post action=http://muasamcong.mpi.gov.vn:8070/servlet/user.HP_PAV_GovPreIdInfo>
						<input type=hidden name=userid value=<%=compEB.getUserid()%>>
						<input type=hidden name=c_id value=<%=request.getParameter("c_id")%>>
						</form>
						<script>
						function moveId() {
							//if(confirm('현재담당자정보로 이관하시겠습니까?')) { 
							if(confirm('Bạn có muốn chuyển thành thông tin người đại diện hiện tại không?')) { 
										document.f.submit(); 
							} else {
							
							}
						}
						</script>
						<tr>
							<td height="2"  class="line" colspan="8"></td>
						</tr>
					</table>
	</td>
	</tr>
  </table>
	<br>
	<br>
</body>
</html>
<%
}
%>
