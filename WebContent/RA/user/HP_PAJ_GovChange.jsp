<%--
/************************************************************************/
/*                                                                      */
/*    Program ID    :     HP_PAJ_GovChange.jsp                          */
/*                                                                      */
/*    description   :     수요기관담당자수정화면						*/
/*                                                                      */
/************************************************************************/
/*  최초생성         2002.04.21          개발                           */
/************************************************************************/
/***************************************************************************/
/* Program ID         : HP_PAJ_GovChange.jsp                       */
/* Program Explanation: Thay đổi thông tin người đại diện chủ đầu tư				*/
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
<%@ page import="beans.*" %>
<%@ page import="entity.*" %>
<%@ page import="g2b.sso.*" %>
<%@ page import="secu.lib.*" %>

<%@ include file = "../../jsp/common/fnUmCommon.jsp" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<jsp:useBean id="ctl" class="beans.UM_URB_UserA030p" scope="page" />
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />

<%!
	final boolean isSettleMent=false;	// 결제 정보 시작되면 true 로 변경
%>

<%
	HP_PAE_UserInfo govEB = new HP_PAE_UserInfo();
	govEB = (HP_PAE_UserInfo)request.getAttribute("govEB");
%>

<%
	SSO sso=new SSO(pageContext);
	sso.checkLogin();
	if(!sso.isLogin()){
		return;
	}

	String id = sso.getID();
	String gubun = sso.getGubun();

	if(gubun==null || !gubun.equals("g")){
		//throw new Exception("프로그램 사용 권한이 없습니다.");
		throw new Exception("Không có quyền sử dụng.");
	}

	UM_URE_UserA020b ett1 = null;
	UM_URE_UserA020b ett2 = null;

	ett1 = ctl.select_user(id);
	ett2 = ctl.select_master(ett1.getMasterCode());
	int count = ctl.Max_count(ett2.getGovCode());
%>

<html>
<head>
<!-- 수요기관담당자수정 -->
<title>Thay đổi thông tin người đại diện chủ đầu tư</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../../css/TA.css">
<script language="javascript" src="../../js/UM.js"></script>

</head>
<body>
<form method="post" name="userInput" action="/servlet/user.HP_PAV_GovChange">
<input type="hidden" name="id" value="<%=id%>">
<div class="col-750 clearfix last">
	<h1 class="pageTitle"><i class="icon-title"></i>Thay đổi thông tin người đại diện chủ đầu tư</h1>

        <!---------------------- 기관정보 타이틀 테이블 시작 ------------------------>
        <table width="100%" cellpadding="2" cellspacing="1">
          <tr>
            <td class="fontb"><!-- 기관정보 -->[Thông tin cơ quan]</td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
          <tr> <td height="4" colspan="4" class="line"></td></tr>
          <tr>
            <td class="tdar" width="20%"><p>ID</p></td>
            <td class="tdb" colspan="3"><%=id%></td>
          </tr>
            <tr>
            	<td class="tdar" width="20%"><p><!-- 등록일시 -->Thời gian đăng ký</p></td>
            <td class="tdb" colspan="3"><%=ett1.getRegDate().substring(0,19)%></td>
          </tr>
          <tr>
            <td class="tdar" width="20%"><p><!-- 수요기관코드 -->Mã chủ đầu tư</p></td>
            <td class="tdb" colspan="3"><input type="hidden" name="d01" value="<%=ett2.getGovCode()%>"><%=ett2.getGovCode()%></td>
          </tr>
          <tr>
            <td class="tdar"><p><!-- 수요기관명 --> Tên chủ đầu tư</p></td>
            <td class="tdb" colspan="3"><%=ett2.getGovName()%></td>
          </tr>
          <tr>
            <td class="tdar"><p><!-- 주소 --> Địa chỉ</p></td>
            <td class="tdb" colspan="3"><%=ett2.getGovAddress()%></td>
          </tr>
          <tr>
            <td class="tdar" width="20%"><p><!-- 전화번호 --> Số điện thoại</p></td>
            <td class="tdb" width="30%"><%=ett2.getGovTelephone()%></td>
            <td class="tdar" width="20%"><p><!--팩스번호  --> Số fax</p></td>
            <td class="tdb" width="30%"><%=ett2.getGovFax()%></td>
          </tr>
          <tr>
            <td height="2" colspan="4" class="line"></td>
          </tr>
        </table>
        <br>

        <table width="100%" class="fontb">
          <tr>
            <td><!-- [아이디정보] --> [Thông tin ID]</td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
            <tr>
                <td height="4" colspan="6" class="line"></td>
            </tr>
          <tr>
            <td class="tdar" width="20%"><!-- 아이디 -->Tên người dùng</td>
            <td class="tdb" width="80%"><%=govEB.getUserid()%>
				<input type="hidden" name="userid" size="15" maxlength="12" value="<%=govEB.getUserid()%>">
				<input type="hidden" name="idExist" value="1">
				<input type="hidden" name="refid" value="<%=id%>">
            </td>
          </tr>
        		<tr>
        			<td height="2" colspan="6" class="line"></td>
        		</tr>
        </table>
        <br>

		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tr">
			<tr>
				<td class="fontb"><!--[담당자정보]  --> [Thông tin người đại diện]</td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
			<tr>
				<td height="4" class="line" colspan="4"></td>
			</tr>
			<tr>
				<td class="tdar" width="20%"><span class="red">*</span>&nbsp;<!-- 성명 --> Họ tên</td>
				<td class="tdb" width="30%"><%=govEB.getUsername()%>
					<input type="hidden" name="username" size="20" maxlength="20" value="<%=govEB.getUsername()%>">
				</td>
				<td class="tdar" width="20%">
					<span class="red">*</span>&nbsp;<!-- 주민등록번호 --> Số CMND
				</td>
				<td class="tdb" width="30%">
					
					<input class="read" name="registno"	size="30" value="<%=govEB.getRegistno1() %>" maxlength="9">
				</td>
			</tr>
			<tr>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;<!-- 전화번호 --> Số điện thoại</td>
				<td class="tdb"	width="30%">				
					<input class="read" name="usertel"	size="30" value="<%=govEB.getUsertel1() %>" maxlength="20">
				</td>
				<td class="tdar" width="20%"><span class="red">*</span>&nbsp;<!--팩스번호  -->Số fax</td>
				<td class="tdb" width="30%">
					<input class="read" name="userfax"	size="30" value="<%=govEB.getUserfax1() %>" maxlength="20">
				</td>
			</tr>
			<tr>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;<!-- 핸드폰번호 --> Số điện thoại di động</td>
				<td class="tdb"	width="30%">
					<input class="read" name="usermobile"	size="30" value="<%=govEB.getUsermobile1() %>" maxlength="20">
				</td>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;<!-- 우편번호 -->Phòng ban</td>
				<td class="tdb" width="30%">
					<input class="read" name="userpost" maxlength="50" size="30" value="<%=govEB.getPartname()%>">
				</td>
			</tr>
			<tr>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;<!-- 메일주소 --> Email</td>
				<td class="tdb" colspan="3">
					<input class="read" name="usermail" maxlength="50" size="30" value="<%=govEB.getUsermail()%>">
				</td>
			</tr>
			<tr>
				<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;<!--주소 --> Địa chỉ</td>
				<td class="tdb"	colspan="3">
					<input class="read" name="address"	size="50" value="<%=govEB.getAddress1()%>" maxlength="200">
				</td>
			</tr>
			
			<tr>
				<td class="tdar"	width="20%">&nbsp;<!-- 등록일자 -->Ngày đăng ký</td>
				<td class="tdb"	width="30%">
					<%=govEB.getRegistdate1()%>
				</td>
				<td class="tdar"	width="20%">&nbsp;<!-- 변경일자 -->Ngày thay đổi</td>
				<td class="tdb"	width="30%">
					<%=govEB.getModifydate()%>
				</td>
			</tr>			
			<tr>
				<td height="2"  class="line" colspan="4"></td>
			</tr>
		</table>

        <!---------------------- 버튼 테이블 시작 ------------------------>
        <table width="100%" border="0" cellpadding="2" cellspacing="0" class="tr">
          <tr>
            <td align="center" height="15"></td>
          </tr>
          <tr>
            <td align="center">
              	<!-- <a href="javascript:js_submit();"><img src="../../img/bu_save.gif" border="0" align="absmiddle" style="cursor:hand"></a> -->
              	<!-- <a href="javascript:window.close();"><img src="../../img/bu_close.gif" border="0" align="absmiddle" style="cursor:hand"></a> -->
			  	<!-- <img src="../../img/passwordchange.gif" align="absmiddle" onClick="javascript:PasswordChange();"style="cursor:hand"> -->
               	<input type="button" class="commonbutton" value="Sửa thông tin" onclick="javascript:js_submit();" style="cursor:hand">
				&nbsp;
				<input type="button" class="commonbutton" value="Đổi Password" onclick="javascript:PasswordChange();" style="cursor:hand">
				&nbsp;
				<input type="button" class="commonbutton" value="Quay lại" onclick="javascript:history.back();" style="cursor:hand">
            </td>
          </tr>
        </table>
</form>
        
	<div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
	</div>
	
<SCRIPT LANGUAGE="JavaScript">

	// 비밀번호 재확인 입력
	function f_pwConfirm()
	{
		if (userInput.userpasswd.value == userInput.userpasswdc.value)
		{
			return true;
		} else {
			//alert("비밀번호란과 비밀번호 확인란의 비밀번호가 서로 틀립니다!");
			alert("Mật khẩu và mật khẩu nhập lại không đồng nhất!");
			userInput.userpasswd.value="";
			userInput.userpasswdc.value="";
			userInput.userpasswd.focus();
			return false;
		}
	}

	// 비밀번호 변경화면으로 이동
	function PasswordChange()
	{
		var userID = userInput.userid.value;
		location.href("http://muasamcong.mpi.gov.vn:8070/RA/user/HP_PAJ_PasswordChange.jsp?type=GovChange&userID="+userID+"");
	}

	function js_submit() { 
		 
		if(document.forms[0].registno.value =='') { 
            alert('Số CMND là hạng mục phải nhập.');
            document.forms[0].registno.select();
            document.forms[0].registno.focus(); 
            return; 
        } else if(document.forms[0].registno.value.length > 9){
        	alert('Số CMND nhập quá dài.');
        	document.forms[0].registno.select(); 
            document.forms[0].registno.focus(); 
        	return;  
        }

		if(document.forms[0].usertel.value =='') { 
            alert('Số điện thoại là hạng mục phải nhập.');
            document.forms[0].usertel.select();
            document.forms[0].usertel.focus(); 
            return; 
        } else if(document.forms[0].usertel.value.length > 20){
        	alert('Số điện thoại nhập quá dài.');
        	document.forms[0].usertel.select(); 
            document.forms[0].usertel.focus(); 
        	return;  
        }

		if(document.forms[0].userfax.value =='') { 
            alert('Số fax là hạng mục phải nhập.');
            document.forms[0].userfax.select();
            document.forms[0].userfax.focus(); 
            return; 
        } else if(document.forms[0].userfax.value.length > 20){
        	alert('Số fax nhập quá dài.');
        	document.forms[0].userfax.select(); 
            document.forms[0].userfax.focus(); 
        	return;  
        }
        
		if(document.forms[0].usermobile.value =='') { 
            alert('Số điện thoại di động là hạng mục phải nhập.');
            document.forms[0].usermobile.select();
            document.forms[0].usermobile.focus(); 
            return; 
        } else if(document.forms[0].usermobile.value.length > 20){
        	alert('Số điện thoại di động nhập quá dài.');
        	document.forms[0].usermobile.select(); 
            document.forms[0].usermobile.focus(); 
        	return;  
        }

		if(document.forms[0].userpost.value =='') { 
            alert('Phòng ban là hạng mục phải nhập.');
            document.forms[0].userpost.select();
            document.forms[0].userpost.focus(); 
            return; 
        } else if(document.forms[0].userpost.value.length > 20){
        	alert('Phòng ban nhập quá dài.');
        	document.forms[0].userpost.select(); 
            document.forms[0].userpost.focus(); 
        	return;  
        }

		if(document.forms[0].address.value =='') { 
            alert('Địa chỉ là hạng mục phải nhập.');
            document.forms[0].address.select();
            document.forms[0].address.focus(); 
            return; 
        } else if(document.forms[0].address.value.length > 200){
        	alert('Địa chỉ nhập quá dài.');
        	document.forms[0].address.select(); 
            document.forms[0].address.focus(); 
        	return;  
        }
        
		if(document.forms[0].usermail.value =='') { 
            alert('Địa chỉ mail là hạng mục phải nhập.'); 
            document.forms[0].usermail.select(); 
            document.forms[0].usermail.focus(); 
            return; 
        }
        if (!checkEmail('usermail')) {
			return;
        }

        if (confirm("Sửa đổi thông tin cơ bản của người đại diện?")) { 
       		document.forms[0].action="/servlet/user.HP_PAV_GovChange";
       		document.forms[0].submit();
       		return;
        } 
    }

    function checkEmail(mail) {
    	var email = document.getElementById(mail);
    	var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    	if (!filter.test(email.value)) {
	    	alert('Hãy nhập chính xác địa chỉ email!');
	    	email.focus();
	    	return false;
    	}
    	return true;
   	}

</SCRIPT>
</body>
</html>
