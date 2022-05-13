<%--
/******************************************************************************************/
/*                                                                                        */
/*    Program ID    :     UM_RAJ_IncuD011i.jsp                                            */
/*                                                                                        */
/*    description   :     목록화요청사용자등록화면									      */
/*                                                                                        */
/******************************************************************************************/
/*  최초생성         2002.06.12          오 창 렬                                         */
/******************************************************************************************/
	
	2003.03.15	김영진	프로그램 정리
	2007.02.16  이광현	목록업무이용자등록신청에 유효성검사 추가
--%>
<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*" %>
<%@ page import="common.*" %>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>
<jsp:useBean id="ctl" class="beans.UM_RAB_IncuA025p" scope="page" />
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />
<%
try{
	ParameterParser psr = new ParameterParser(request);

	String saupNo = psr.getStringParameter("saupNo",			"");
	String dt1;
	String dt2;
	String dt3;

    UM_ICE_InciA040b  ett = null;
    ett = ctl.select_Kigwan(saupNo);
%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<title>목록화요청사용자등록</title>
<link rel="stylesheet" type="text/css" href="/css/UM.css">
<script language="javascript" src="/js/UM.js"></script>
<script language="JavaScript" src="/js/cal.js"></script>
<script language="javascript" src="/js/tel_check.js"></script>
<SCRIPT LANGUAGE="JavaScript">
	function save_click(opt) {

		var doc = document.fm.all;
		
//		doc.telNo_1.value=telTypeMake(doc.tel1.value,doc.tel2.value,doc.tel3.value);
//		doc.faxNo_1.value=telTypeMake(doc.fax1.value,doc.fax2.value,doc.fax3.value);		

            if (document.fm.userIDconfirm.value == "1") {
                alert("아이디가 중복되었습니다. 아이디를 다시 입력하십시오.");
				return;
            } else if (doc.userIDconfirm.value == "") {
                regist_click();
				return;
            }
			if(korean(doc.userId.value) || doc.userId.value ==''){
				alert('아이디는 필수입력항목이며 \n공백이 없이 영문로만 입력하셔야 합니다.');
				doc.userId.select();
    			doc.userId.focus();
				return;
			}
			if(doc.password.value =='' || doc.password.value.length < 4 || doc.password.value.length > 20){
				alert('비밀번호는 필수입력항목이며 영문이나 숫자 4자리 이상 20자리 이하로 입력하셔야 합니다.');
				doc.password.select();
				doc.password.focus();
    			return;
			}	
			if(doc.password.value != doc.password1.value) {
				alert("입력한 비빌번호가 서로 틀립니다.")
				doc.password.focus();
				return;
			}
			if(doc.name.value.length < 1) {
				alert("사용자 이름을 입력하십시오.")
				doc.name.focus();
				return;
			}
/*
			if(doc.juminNo.value.length < 13) {
				alert("사용자 주민번호를 올바르게 입력하십시오.")
				//doc.juminNo.focus();
				doc.juminNo1.focus();
				return;
			}
*/
		doc.juminNo.value = doc.juminNo1.value+doc.juminNo2.value;
		if(doc.juminNo1.value.length != 6) {
			alert("사용자 주민번호를 올바르게 입력하십시오.")
			doc.juminNo1.focus();
			return;
		}
		if(doc.juminNo2.value.length != 7) {
			alert("사용자 주민번호를 올바르게 입력하십시오.")
			doc.juminNo2.focus();
			return;
		}		
					
/*			if (doc.juminNo.value != "" && doc.juminNo.value.length == 14)
			{
				p1 = doc.juminNo.value.substring(0,6);
				p2 = doc.juminNo.value.substring(7,14);

				if (!fnCheckPrnNo(p1 + p2))
				{
					alert("주민등록번호가 잘못 되었습니다!");
					//doc.juminNo.focus();
					doc.juminNo1.focus();
					return;
				}
			}*/
			if (doc.mail.value.indexOf('@') < 0 ) { 
				alert('메일주소를 정확히 입력해 주세요'); 
				doc.mail.select();
				doc.mail.focus();
				return;
			} else if(doc.mail.value.charAt(doc.mail.value.indexOf('@')-1) == '@' ) {
				alert('메일주소를 정확히 입력해 주세요');
				doc.mail.select();
				doc.mail.focus();
				return;
			} else if((doc.mail.value.charAt(doc.mail.value.indexOf('@')+1) == '@' ) || (doc.mail.value.charAt(doc.mail.value.indexOf('@')+1) == '')) {
				alert('메일주소를 정확히 입력해 주세요');
    			doc.mail.select();
				doc.mail.focus();
				return;
			}

			if (document.forms[0].real_jumin_chk_yn1.value != "Y" ||
				document.forms[0].name.value != document.forms[0].old_taskmaster.value ||
				document.forms[0].juminNo.value != document.forms[0].old_jumin.value ) {
				alert("사용자의 실명인증을 먼저 하신후 목록화요청(공공기관) 이용자등록을 하십시오.");
				return;
			}

		document.fm.flag.value = opt;
		document.fm.action = "/servlet/servlets.UM_RAV_IncuA030c";
		document.fm.method = "post";
		document.fm.target = "_self";
		document.fm.submit();
		
	}

	function regist_click() {

    	if(document.fm.userId.value.length < 4) {
			alert("아이디는 4자리 이상 입력해야 합니다!")
		} else {
			window.open("","id_confirm", "width=450,height=350 top=250 left=300, scrollbars=no");
			document.fm.action='./UM_RAJ_IncuA040s.jsp';
			document.fm.target='id_confirm';
			document.fm.submit();
		}
	}

	function fOnFocus(obj) {
		orgValue = obj.value;
		CS_replace(obj,",","","obj");
	}

	function fOnBlur(obj) {
		CS_divideComma(obj,",",3,"obj");
	}

	function fAddrFind(code, addr, gubun) {
		if (gubun == "2") {
			fm.postCode2.value = code;
			fm.postCode2.focus();
			fm.bonsaAdd2.value = addr;
		} else {
			fm.postCode.value = code;
			fm.postCode.focus();
			fm.bonsaAdd.value = addr;
		}
	}

	function userIDconfirm(Del) {
		if (Del == "0") {
			document.fm.password.focus();
            document.fm.userIDconfirm.value = Del;
		} else {
			document.fm.userId.select();
			document.fm.userId.focus();
            document.fm.userIDconfirm.value = Del;
		}
	}

	function Clear() {
		document.fm.reset();
	}

	function js_searchDate(formname, boxname, gubun)
	{
		url = "http://www.g2b.go.kr:8070/jsp/common/calendar.jsp?form_name=" + formname + "&box_name=" + boxname + "&gubun=" + gubun;
		window.open(url,"Day_Search","directories=no,resizable=yes,scrollbars=no,location=no,width=181,height=205");
	}

	// 목록업무이용자등록신청에 유효성검사 추가
	// 실명체크 
	function real_jumin_chk(gubun, name, jumin1, jumin2, elm_name){

        
		if (name.value == "" || name.value == null){
			alert(gubun + "의 성명을 입력하세요!");
            name.focus();
            return;
        }

		if (jumin1.value == "" || jumin1.value == null || jumin1.value.length != 6){
			alert(gubun + "의 주민등록번호 앞자리를 입력하세요!");
            jumin1.focus();
            return;
        }

		if (jumin2.value == "" || jumin2.value == null || jumin2.value.length != 7){
			alert(gubun + "의 주민등록번호 뒷자리를 입력하세요!");
            jumin2.focus();
            return;
        }

        if (gubun == "사용자") {
            document.forms[0].real_jumin_chk_yn1.value = "";
            document.forms[0].old_taskmaster.value     = name.value;
            document.forms[0].old_jumin.value          = jumin1.value+jumin2.value;
        } else {
            document.forms[0].real_jumin_chk_yn2.value = "";
            document.forms[0].old_ident.value          = name.value;
            document.forms[0].old_identjumin.value     = jumin1.value+jumin2.value;
        }


        window.open("", "nameCheck", "width=350,height=300 top=250 left=300, scrollbars=yes");

        document.nameCheck.target         = "nameCheck"; 
		document.nameCheck.action         = "/jsp/RA/name_Check.jsp"; 
		document.nameCheck.username.value = name.value; 
		document.nameCheck.jumin.value    = jumin1.value+jumin2.value; 
        document.nameCheck.elm_name.value = elm_name; 
		document.nameCheck.submit(); 
       
		return;
	}
</SCRIPT>
</head>
<body background="/img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="fm" method="post" action="">
<div id='here' style='position:absolute ; display:none ; background-color:#FFFFF4 ; border-style:double ; border-width:1 '>
</div>
<input type="hidden" name="flag" value="">
<input type="hidden"  name="userIDconfirm"  value="">
<table width="800" border="0" cellpadding="0" cellspacing="0" height="100%"  background="/img/bg_sub.gif">
    <tr valign="bottom" height="57"> 
      <td rowspan="2"><img src="/img/sub_title_01.jpg"></td>
      <td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE"> 
        &nbsp;&nbsp;목록화요청(공공기관) 사용자 등록신청</td>
    </tr>
    <tr height="7"> 
      <td colspan="2"><img src="/img/sub_title_03.jpg"></td>
    </tr>
    <tr height="21"> 
      <td colspan="3"></td>
    </tr>

    <tr valign="top"> 
		<td width="35"></td>
		<td width="760">

<table width="100%" class="fontb">
  <tr>
    <td>[ 아이디정보 ]</td>
  </tr>
</table>
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
	<tr> 
		<td height="4" class="line" colspan="6"></td>
	</tr>          
  <tr>
    <td class="tdar"    width="12%"><span class="red">*</span>&nbsp;아이디</td>
    <td class="tdb"     width="25%"> 
		<input class="read" name="userId" size="15" value="" maxlength="15" >
        <a href="javascript:regist_click();"><img src = "/img/중복확인.gif" align ="absmiddle" border="0"></a>
	</td>
    <td class="tdar"	width="13%"><span class="red">*</span>&nbsp;비밀번호</td>
    <td class="tdb"		width="15%"><input type="password" class="read"	name="password"		size="15" maxlength="20" ></td>
    <td class="tdar"	width="15%"><span class="red">*</span>&nbsp;비밀번호확인</td>
    <td class="tdb"		width="13%"><input type="password" class="read"	name="password1"	size="15" maxlength="20" ></td>
  </tr>
  <tr> 
     <td height="2"  class="line" colspan="6"></td>
  </tr>		  
</table>
<br>

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tr">
	<tr> 
		<td class="fontb">[ 기관정보 ]</td>
	</tr>
</table>
<!---------------------- 마스터정보 타이틀 테이블 끝 ------------------------>


<!---------------------- 마스터정보 테이블 시작 ------------------------>
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
<tr> 
		<td height="4" class="line" colspan="4"></td>
	</tr>          
	<tr> 
		<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;공공기관															</td>
		<td class="tdb"		width="30%"><%=ett.getupcheNm()%>		</td>
		<td class="tdar"	width="20%"><span class="red">*</span>&nbsp;사업자등록번호														</td>
		<td class="tdb"		width="30%"><%=insert_minus_saupno(saupNo)%></td>
	</tr>
	<tr> 
		<td height="2"  class="line" colspan="4"></td>
	</tr>		  
<input type="hidden" name="saupNo" value="<%=saupNo%>">
</table>
<!---------------------- 마스터정보 테이블 끝				------------------------>
<br>

<!---------------------- 사용자정보 타이틀 테이블 시작		------------------------>


<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tr">
	<tr> 
		<td class="fontb">[ 사용자정보 ]																	</td>
	</tr>
</table>
<!---------------------- 사용자정보 타이틀 테이블 끝		------------------------>


<!---------------------- 사용자정보 테이블 시작				------------------------>
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
  <tr> 
	<td height="4" class="line" colspan="4"></td>
  </tr>          
  <tr> 
    <td class="tdar"	width="20%"><span class="red">*</span>&nbsp;성명																</td>
    <td class="tdb"		width="30%"><input class="read" name="name"			value="" maxlength="10" >		</td>
    <td class="tdar"	width="20%"><span class="red">*</span>&nbsp;주민등록번호														</td>
    <td class="tdb"		width="30%">
<!-- 20040509 G2B포털개선 수정 START-->
<script>
function textSum(text1,text2,result) {
	result.value=text1.value+"-"+text2.value;
}
</script>    
    <input type="text" class="read" name="juminNo1" value="" maxlength="6" size=6 onkeydown="js_OnlyNumber(this)">-<input type="password" class="read" name="juminNo2" value="" maxlength="7" size=7  onkeydown="js_OnlyNumber(this)">		
    <input type=hidden class="read" name="juminNo" value="" maxlength="13">		

	<input type=hidden name="real_jumin_chk_yn1" value="">  <!-- 실명인증 확인여부 -->
	<input type=hidden name="old_taskmaster" value="">      <!-- 실명인증 확인여부 -->
	<input type=hidden name="old_jumin" value="">           <!-- 실명인증 확인여부 -->

	<a href="javascript:real_jumin_chk('사용자', document.fm.name, document.fm.juminNo1, document.fm.juminNo2, 'document.fm.real_jumin_chk_yn1');">
	<img src="/img/bu_anonymity.gif" style=cursor:hand border=0 align="absmiddle"></a>

<!--    
    <input class="read" name="juminNo"		value="" maxlength="13" onkeydown="js_OnlyNumber(this)" onFocus="js_RemoveChar(this, '-')" onBlur="fnAddDash(this, 1)">		
-->    
    </td>
  </tr>
  <tr> 
    <td class="tdar"	width="20%">부서명																	</td>
    <td class="tdb"		width="30%"><input class="read" name="buserNm"		value="" maxlength="15" >		</td>
    <td class="tdar"	width="20%">부서장																	</td>
    <td class="tdb"		width="30%"><input class="read" name="buserJang"	value="" maxlength="7" >		</td>
  </tr>
  <tr> 
    <td class="tdar"	width="20%">직책																	</td>
    <td class="tdb"		width="30%"><input class="read" name="duty"			value="" maxlength="15" >		</td>
    <td class="tdar"	width="20%"><span class="red">*</span>&nbsp;메일주소																</td>
    <td class="tdb"		width="30%"><input class="read" name="mail"			value="" maxlength="30" size="30" >		</td>
  </tr>
  <tr> 
    <td class="tdar"	width="20%"><span class="red">*</span>&nbsp;전화번호															</td>
    <td class="tdb"		width="30%"><%=List.getCode("TFH", "tel_3", "GUL", "")%>
		<!--select name="tel1">
		    <option value="" selected>선택</option>
			<option value="02" >02</option>
			<option value="031">031</option>
			<option value="032">032</option>
			<option value="033">033</option>
			<option value="041">041</option>
			<option value="042">042</option>
			<option value="043">043</option>
			<option value="051">051</option>
			<option value="052">052</option>
			<option value="053">053</option>
    		<option value="054">054</option>
			<option value="055">055</option>
			<option value="061">061</option>
			<option value="062">062</option>
			<option value="063">063</option>
			<option value="064">064</option>
		</select-->
<!-- 20040509 G2B포털개선 수정 START-->
            -&nbsp;<input type=text size=5 class="read" name="tel2" onkeydown="js_OnlyNumber(this)" maxlength="4" onBlur="textSum(this.form.tel2,this.form.tel3,this.form.telNo_1);">
            -&nbsp;<input type=text size=5 class="read" name="tel3" onkeydown="js_OnlyNumber(this)" maxlength="4" onBlur="textSum(this.form.tel2,this.form.tel3,this.form.telNo_1);">
      <input type=hidden class="read" name="telNo_1">
<!-- 20040509 G2B포털개선 수정 END-->
<!--20040509 이전소스
      -&nbsp;<input class="read" name="telNo_1" maxlength="8" onkeydown="js_OnlyNumber(this)" onFocus="js_RemoveChar(this, '-')" onBlur="fnAddDash(this, 3)">
-->   
	  </td>
    <td class="tdar"	width="20%"><span class="red">*</span>&nbsp;팩스번호															</td>
    <td class="tdb"		width="30%"><%=List.getCode("TFH", "tel_4", "GUM", "")%>
		<!--select name="fax1">
		    <option value="" selected>선택</option>
			<option value="02" >02</option>
        	<option value="031">031</option>
			<option value="032">032</option>
			<option value="033">033</option>
			<option value="041">041</option>
			<option value="042">042</option>
			<option value="043">043</option>
			<option value="051">051</option>
			<option value="052">052</option>
			<option value="053">053</option>
			<option value="054">054</option>
			<option value="055">055</option>
			<option value="061">061</option>
			<option value="062">062</option>
			<option value="063">063</option>
			<option value="064">064</option>
		</select-->
<!-- 20040509 G2B포털개선 수정 START-->
            -&nbsp;<input type=text size=5 class="read" name="fax2" onkeydown="js_OnlyNumber(this)" maxlength="4" onBlur="textSum(this.form.fax2,this.form.fax3,this.form.faxNo_1);">
            -&nbsp;<input type=text size=5 class="read" name="fax3" onkeydown="js_OnlyNumber(this)" maxlength="4" onBlur="textSum(this.form.fax2,this.form.fax3,this.form.faxNo_1);">
      <input type=hidden class="read" name="faxNo_1">
<!-- 20040509 G2B포털개선 수정 END-->		
<!--20040509 이전소스		
     -&nbsp;<input class="read" name="faxNo_1" maxlength="8" onkeydown="js_OnlyNumber(this)" onFocus="js_RemoveChar(this, '-')" onBlur="fnAddDash(this, 3)">
-->  
	</td>
  </tr>
  <tr> 
	<td height="2"  class="line" colspan="4"></td>
  </tr>		  
</table>
<!---------------------- 사용자정보 테이블 끝 ------------------------>
<br>


<!---------------------- 버튼 테이블 시작 ------------------------>
<table width="100%" cellpadding="2" cellspacing="2">
  <tr> 
	<td align="center">
	  <a href="javascript:save_click('UserIns_G');"><img src="/img/bu_entry.gif" border="0" align="absmiddle"></a>
	  <a href="javascript:Clear();"><img src="/img/bu_reset.gif" style="cursor:hand;"	border="0" align="absmiddle"></a>
	</td>
  </tr>
</table>
      </td>
    </tr>
    <tr> 
      <td colspan="3" height="50"><%@ include file="/jsp/common/Footer.jsp" %></td>
    </tr>
  </table>
<!---------------------- 버튼 테이블 끝 ------------------------>
</form>

<!-- 실명인증 관련 -->
<form name="nameCheck" method="post"> 
<input type="hidden" name="username"   value=""> 
<input type="hidden" name="jumin"      value=""> 
<input type="hidden" name="elm_name"   value=""> 
</form>

</body>
</html>
<%}catch(Exception e){
	out.println("error : "+e.toString());
}
%>
