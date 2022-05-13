<%--
/************************************************************************/
/***************************************************************************/
/* Program ID         : HP_PAJ_GovList.jsp                        */
/* Program Explanation: Danh sách người phụ trách                      */
/* Program Summary  :   
/* Relation Program   : */ 
/*                     */
/* Table              : )                 */
/***************************************************************************/
/* Customizing Composer : MR. MINH 13.06.2009                       */
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
	SSO sso=new SSO(pageContext);
	sso.checkLogin();
	if(!sso.isLogin()){
		return;
	}

	String refid = sso.getID();
	String gubun = sso.getGubun();
	String personid = sso.getPersonID();

	if(gubun==null || gubun.equals("c")){
		//throw new Exception("프로그램 사용 권한이 없습니다.");
		throw new Exception("Không có quyền sử dụng.");
	}
%>

<%
	String npage = request.getParameter("npage") == null ? "1" : request.getParameter("npage");
	String size = request.getParameter("size") == null ? "10" : request.getParameter("size");

	int count = 0;
	int count2 = 0;
	int totalPage = 0;
	int npage2 = Integer.parseInt(npage);
	int size2 = Integer.parseInt(size);

	HP_PAE_UserList userList;
%>
<html>
<head>
<!-- 수요기관담당자목록 -->
<title>Danh sách người đại diện chủ đầu tư</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../../css/TA.css" type="text/css">
  
<script language="javascript" src="../../js/userview.js"></script>
<script language="javascript" src="../../js/personinfo.js"></script>

</head>
<body>
<div class="col-750 clearfix last">
	<h1 class="pageTitle"><i class="icon-title"></i> Danh sách người đại diện chủ đầu tư</h1>

        <table width="100%" cellpadding="2" cellspacing="1">
          <tr>
            <td class="fontb"><!-- [대표담당자정보] --> [Thông tin lãnh đạo cơ quan]</td>
          </tr>
        </table>  
<%
HP_PAE_UserInfo userDaeEB = new HP_PAB_GovUserSB().getGovUserDaeEB(refid);
%>            
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
          <tr> <td height="4" colspan="4" class="line"></td></tr>
          <tr>
            <td class="tdar" width="22%"><p><!--  아이디--> ID</p></td>
            <td class="tdb" colspan="3"><%=userDaeEB.getUserid()%></td>
          </tr>
            <tr>
            	<td class="tdar" width="22%"><p><!-- 담당자명 --> Tên lãnh đạo cơ quan</p></td>
            <td class="tdb" colspan="3"><%=userDaeEB.getUsername()%></td>
          </tr>
          <tr>
            <td class="tdar" width="22%"><p><!-- 담당부서 --> phòng ban</p></td>
            <td class="tdb" colspan="3"><%=userDaeEB.getPartname()%></td>
          </tr>
          <tr>
            <td class="tdar"><p><!-- 전화번호 -->Số điện thoại</p></td>
            <td class="tdb" colspan="3"><%=userDaeEB.getCorptel1()%></td>
          </tr>
          <tr>
            <td height="2" colspan="4" class="line"></td>
          </tr>
        </table>
        <br>        
                
        <table width="100%" cellpadding="2" cellspacing="1">
          <tr>
            <td class="fontb"><!-- [담당자정보] --> [Thông tin người đại diện]</td>
          </tr>
        </table>      
<%

	count = new HP_PAB_GovUserSB().getGovUserListCount(refid);

	if (count > 0) count2 = count;

	if ((count2%size2) == 0)
		totalPage = count2/size2;
	else
		totalPage = count2/size2 +1;
%>

<table width="100%" border="0" cellspacing="1" cellpadding="2">
						<tr>
							<td class="fontp"> 
<!-- <font color=red>선택</font>을 클릭하여 새창에서 확인버튼을 누르면 선택하신 정보가 PC에 저장되어이후 <font color=red>결재자정보</font>에 지속적으로 반영됩니다.<br>
							    *<font color=red>수요기관담당자정보 및 비밀번호를 변경</font>하시려면 아래의 리스트에서 <font color=red>아이디</font> 부분을 클릭하세요.<br>
							    *<font color=red>해지하기</font>를 클릭하면 해당하는 라인의 아이디가 해지되고 해지되는 아이디가 로그인정보 및 결재자 정보에 사용되고 있으면 해당 정보도 삭제됩니다.<br>
							    *또한 해지된 아이디로는 <font color=red>커뮤니티와 마이페이지</font>도 이용이 불가능 하오니 유의하셔서 해지하시기 바랍니다.
 -->
*<font color=red> Chọn  </font> Click vào 'Chọn' rồi nhấn vào nút 'Ok' ở trang  mới, thông tin mà bạn chọn sẽ được lưu vào PC sau đó sẽ được hiển thị trong mục <font color=red>Thông tin người xác nhận</font><br>
							    *<font color=red> Nếu muốn thay đổi mật khẩu  và thông tin người đại diện chủ đầu tư</font> tại danh sách ID click vào phần ID <font color=red> </font>.<br>
							    *<font color=red> Xóa</font> nhấn vào nút xóa thì xóa dòng ID tương ứng và nếu thông tin người xác nhận và thông tin login của ID đang bị xóa đang được sử dụng thì những thông tin tương ứng cũng bị xóa.<br>
							    * <font color=red> Lưu ý những 'nghiệp vụ' và 'trang của tôi' </font> truy cập bằng ID bị xóa cũng không thể sử dụng được

<%
		if (count != 0) {
%>
								<!-- <div align="right" class="fontp"> 총 문서 <%=count2%>개 : [<img src="../../img/page.gif" width="15" height="13">Page <%=npage%>/<%=totalPage%>]</div> -->
<%
		}
%>
								<div align="right" class="fontp"> Tổng số <%=count2%> : [<img src="../../img/page.gif" width="15" height="13">Trang <%=npage%>/<%=totalPage%>]</div>
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
							<td class="tdar" width="5%">
<!--
								<div align="center"><input type="checkbox" name="allbox" onClick="change_stat()"></div>
-->
								<div align="center">NO</div>
							</td>
							<td class="tdar" width="15%">
								<div align="center"><!-- 아이디 --> ID</div>
							</td>
							<td class="tdar" width="15%">
								<div align="center"><!-- 담당자명 -->Tên người đại diện</div>
							</td>
							<td class="tdar" width="15%">
								<div align="center"><!-- 담당부서 --> Phòng ban</div>
							</td>
							<td class="tdar" width="15%">
								<div align="center"><!-- 전화번호 --> Số điện thoại</div>
							</td>
							<td class="tdar" width="15%">
								<div align="center"><!-- 등록일자 --> Ngày đăng ký</div>
							</td>
							<td class="tdar" width="7%">
								<div align="center"><!-- 선택 --> Chọn</div>
							</td>
							<td class="tdar" >
								<div align="center"><!-- 아이디해지 --> Xóa ID</div>
							</td>
						</tr>

<!--     유저 조회결과    -->
<%
		if (count == 0)
		{
%>
						<tr>
							<td colspan="8" class="tdb"><!-- 등록된 수요기관담당자가 없습니다 --> Không có người đại diện chủ đầu tư được đăng ký</td>
						</tr>
<%
		} else {

            String tdColor = "";

		    HP_PAB_GovUserSB compSB = new HP_PAB_GovUserSB();
		    userList = compSB.getGovUserListEB(refid, size2, (npage2-1)*size2+1);

			for (int i = 0; i < userList.getTotalCount(); i++) {
				userList.next();
				HP_PAE_UserInfo userEB = new HP_PAE_UserInfo();
				userEB = userList.getUserEB();

                    tdColor = "tdbc";

%>
						<tr>
							<td class="<%=tdColor%>">
                                			<%=(i+1)+(Integer.parseInt(npage)-1)*size2%>
							</td>
							<td class="<%=tdColor%>">
								<div align="center"><a href="JavaScript:f_modifyUser('<%=userEB.getUserid()%>')"><%=userEB.getUserid()%></a></div>
							</td>
						    	<td class="<%=tdColor%>"><%=userEB.getUsername()%></td>
							<td class="<%=tdColor%>">
								<div align="center"><%=userEB.getPartname()%></div>
							</td>
							<td class="<%=tdColor%>">
								<div align="center"><%=userEB.getCorptel1()%></div>
							</td>
							<td class="<%=tdColor%>">
								<div align="center"><%=userEB.getRegistdate()%></div>
							</td>
							<td class="<%=tdColor%>" align=center>
								<input type="radio" name="radiobox" 
                                       value="<%=userEB.getUserid()%>" 
									   onClick="javascript:f_select('<%=userEB.getUserid()%>');">
							</td>
							<td class="<%=tdColor%>" align=center>
	<script language="JavaScript">
		var pc_id = loadPersonID('<%=refid%>');
		if(pc_id == '<%=userEB.getUserid()%>') {
			document.write("<a href='#' onclick='javascript:f_delete('<%=userEB.getUserid()%>');'> Xóa </a>");
		} else {
		}
	</script>
							</td>
						</tr>
						<script language="JavaScript">
							//checkOption('<%=i%>','<%=userEB.getUserid()%>');
							var pre_id = 0;
							p_id = loadPersonID('<%=refid%>');
							if(p_id == '<%=userEB.getUserid()%>') {
								pre_id = pre_id + 1;
								try {
									eval("document.all.radiobox["+'<%=i%>'+"].checked = true");
								} catch(ex) {
									eval("document.all.radiobox.checked = true");
								}
							}
						</script>
<%
			}
		}
%>
						
						<tr>
							<td height="2"  class="line" colspan="8"></td>
						</tr>
					</table>
                    <br/>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
					
<form name="movePage" method="post" action="/servlet/user.HP_PAV_GovList">
	<input type="hidden" name="refid" value="<%=refid%>">
	<input type="hidden" name="npage" value="<%=npage%>">
	<input type="hidden" name="size" value="<%=size%>">
</form>
						<tr>
							<td align="center"><div align="" class="fontp">
<%
		int tpage = Integer.parseInt(npage)/10;

		if (tpage != 0) out.print("<a href='JavaScript:f_pageMove(" + (tpage+1) + ")'>trước");

		for (int i = (1+ tpage); i <= (tpage+10); i++) {
			if (totalPage >= i)
				out.print(" [<a href='JavaScript:f_pageMove(" + i + ")'>" + i + "</a>] ");
		}

		if (((count/size2)+1)/10 > Integer.parseInt(npage)/10) out.print("<a href='JavaScript:f_pageMove(" + (tpage+11) + ")'>tiếp theo");
%>
							</div></td>
						</tr>
					</table>

<div id ="pre_idinfo" style="display:none"> 
		<script>
		var q_id =  loadPersonID('<%=refid%>');
		document.write('<iframe name="pre_idFrame" src="http://muasamcong.mpi.gov.vn:8070/servlet/user.HP_PAV_GovPreIdInfo?userid='+q_id+'&c_id=<%=refid%>" width="780" height="180" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" noResize></iframe>					');
		</script>
					
</div>	

    
	<div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
	</div>
<script language="JavaScript">

var pre_id = 0;
var z_id = loadPersonID('<%=refid%>');
if(z_id != "" && pre_id == 0) {
	pre_idinfo.style.display='block';
}
function f_modifyUser(id) {
	document.forms[0].action="/servlet/user.HP_PAV_GovChangeUser?userid=" + id;
	document.forms[0].submit();
	return;
}

function f_pageMove(chk) {
	movePage.npage.value = chk;
	movePage.submit();
}

function f_select(id) {
	window.open("HP_PAJ_GovSelect.jsp?personid="+id, "","scrollbars=no,resizable=no,width=350,height=200");
}

function checkOption(idx,u_id) {
	p_id = loadPersonID('<%=refid%>');
	if(p_id == u_id) {
		pre_id = pre_id + 1;
		try {
			eval("document.all.radiobox["+idx+"].checked = true");
		} catch(ex) {
			eval("document.all.radiobox.checked = true");
		}
	}
}

function f_delete(userid) {
	//if(confirm("정말 해지하시겠습니까?")) {
	if(confirm("Bạn có thực sự muốn xóa không?")) {	
        pcFileDel(userid,'<%=refid%>');
		location.href='/servlet/user.HP_PAV_GovDel?userid='+userid;
	} else {
	
	}
}
	
</script>
</body>
</html>
