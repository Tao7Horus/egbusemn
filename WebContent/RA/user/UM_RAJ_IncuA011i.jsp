<%--
/**************************************************************************************/
/*                                                                                        */
/*    Program ID    :     UM_RAJ_IncuA011i.jsp                                            */
/*                                                                                        */
/*    description   :     목록화요청사용자등록조회화면									  */
/*                                                                                        */
/******************************************************************************************/
/*		최초생성         2002.06.12          오 창 렬                                     */
/******************************************************************************************/
	2003.04.14	김영진	프로그램 정리
	2007.02.16  이광현	목록업무이용자등록신청에 유효성검사 추가
	2007.03.23	이광현	나라장터 내 주민등록번호 표시체계 개선
--%>

<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*" %>
<%@ page import="common.*" %>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>
<jsp:useBean id="ctl" class="beans.UM_RAB_IncuA025p" scope="page" />
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />

<!-- 20040509 G2B포털개선 수정 START-->
<jsp:useBean id="wu" class="common.WebUtil" scope="page" />
<jsp:useBean id="sp" class="common.StandardPhonNumber" scope="page" />
<!-- 20040509 G2B포털개선 수정 END-->

<%
try{

	ParameterParser psr = new ParameterParser(request);

	String saupNo = psr.getStringParameter("saupNo",			"");
	String dt1;
	String dt2;
	String dt3;

	UM_ICE_InciA040b  ett = null;
	ett=ctl.select_coperation2(saupNo);
%>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<title>목록화요청사용자정보</title>
<meta name="generator" content="Namo WebEditor v5.0">
<link rel="stylesheet" type="text/css" href="/css/UM.css">
<script language="javascript" src="/js/UM.js"></script>
<script language="javascript" src="/js/tel_check.js"></script>
</head>
<SCRIPT LANGUAGE="JavaScript">

	function save_click(opt)
	{
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
		if(korean(document.fm.userId.value) || document.fm.userId.value ==''){
			alert('아이디는 필수입력항목이며 \n공백이 없이 영문로만 입력하셔야 합니다.');
			document.fm.userId.select();
			document.fm.userId.focus();
			return;
		}
		if(doc.password.value =='' || doc.password.value.length < 4 || doc.password.value.length > 12){
			alert('비밀번호는 필수입력항목이며 영문이나 숫자 4자리 이상 12자리 이하로 입력하셔야 합니다.');
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
			alert("사용자의 실명인증을 먼저 하신후 목록화요청 이용자등록을 하십시오.");
			return;
		}

		document.fm.flag.value = opt;
		document.fm.action = "/servlet/servlets/UM_RAV_IncuA030c";
		document.fm.method = "post";
		document.fm.target = "_self";
		document.fm.submit();
	}

	function regist_click() {
		if(document.fm.userId.value.length == "0") {
			alert("아이디를 입력하십시오!")
		} else {
			window.open("","id_confirm", "width=450,height=350 top=250 left=300, scrollbars=no");
			document.fm.action='/jsp/RA/UM_RAJ_IncuA040s.jsp';
			document.fm.target='id_confirm';
			document.fm.submit();
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

	function textSum(text1,text2,result) {
		result.value=text1.value+"-"+text2.value;
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
<body background="/img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form name="fm" method="post">
<!---------------------- 타이틀 테이블 시작 ------------------------>
<input type="hidden"  name="userIDconfirm"  value="">
<input type="hidden"	name="flag"		value="">
<input type=hidden		name="saupNo"	value="<%=ett.getsaupNo()%>">
  <table width="823" border="0" cellpadding="0" cellspacing="0" height="100%"  background="/img/bg_sub.gif">
    <tr valign="bottom" height="57">
      <td rowspan="2"><img src="/img/sub_title_01.jpg"></td>
      <td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE">
        &nbsp;&nbsp;목록화요청 이용자 추가 등록신청</td>
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
        <td height="4" colspan="6" class="line"></td>
    </tr>
  <tr>
    <td class="tdar" width="114">아이디</td>
    <td class="tdb" width="194">
      <table width="100%" border="0">
        <tr>
          <td width="40%"><input class="read" name="userId" size="10" value="" maxlength="15" ></td>
          <td width="60%" align="left"><a href="javascript:regist_click();"><img src = "/img/중복확인.gif" style="cursor:hand;"	align = "absmiddle" border=0></a></td>
        </tr>
      </table>
    </td>
    <td class="tdar"	width="114">비밀번호																														</td>
    <td class="tdb"		width="114"><input type="password" class="read"	name="password" size="15">			</td>
    <td class="tdar"	width="114">비밀번호확인															</td>
    <td class="tdb"		width="120"><input type="password" class="read"	name="password1" size="15">			</td>
  </tr>
		<tr>
			<td height="2" colspan="6" class="line"></td>
		</tr>
</table>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tr">
  <tr>
	<td class="fontb">[ 기본정보 ]																		</td>
  </tr>
</table>
<!---------------------- 마스터정보 타이틀 테이블 끝 ------------------------>

<!---------------------- 마스터정보 테이블 시작 ------------------------>
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
		<tr>
			<td height="4" colspan="4" class="line"></td>
		</tr>
  <tr>
    <td class="tdar"><p>상호명</p>																		</td>
    <td class="tdb"		width="30%"><%=ett.getupcheNm()%>												</td>
    <td class="tdar"	width="20%"><p>사업자등록번호</p>												</td>
    <td class="tdb"		width="30%"><%=insert_minus_saupno(ett.getsaupNo())%>							</td>
  </tr>
  <tr>
    <td class="tdar"	height="23"><p>대표자명</p>														</td>
    <td class="tdb"		width="30%" height="23"><%=ett.getdaepyoNm()%>									</td>
    <td class="tdar"	width="20%" height="23"><p>주민등록번호</p>										</td>
    <td class="tdb"		width="30%" height="23"><!-- 2007.03.23 나라장터 내 주민등록번호 표시체계 개선 -->
<% String JUMIN_NO = null;
    JUMIN_NO = ett.getdaepyojuminNo();

        if (JUMIN_NO != null) {         %>
            <%=insert_minus_jumin_star(ett.getdaepyojuminNo())%>			</td>
<%      } else {                        %>
<%      }                               %>
  </tr>
  <tr>
    <td class="tdar"	height="24">업체형태부호														</td>
    <td class="tdb"		height="24">
<%      String upcheGubun = ett.getupcheGubun();
        if (upcheGubun != null) {
            List.getCode("T","upcheGubun", "GU1", ett.getupcheGubun());
        }%>
    </td>
    <td class="tdar"	height="24">우편번호															</td>
    <td class="tdb"		height="24"><%=ComStr.divideComma(ett.getpostCode(),"-",3)%>					</td>
  </tr>
  <tr>
    <td class="tdar">본사주소																			</td>
    <td class="tdb" colspan="3"><%=ett.getbonsaAdd()%>&nbsp;<%=ett.getbonsaAddna()%>					</td>
  </tr>
<tr>
    <td class="tdar">본사전화번호
    
<!-- 20040509 G2B포털개선 수정 START-->
<%
String fullTel = wu.standardTel(ett.getbonsaTel());
sp.setStandardPhonNumber(fullTel);
%>    																		</td>
    <td class="tdb" ><%=sp.getFirstNum()+"-"+sp.getMiddleNum()+"-"+sp.getLastNum()%>																</td>
    <td class="tdar">본사팩스번호
    																		<%
String fullFax = wu.standardTel(ett.getbonsaFax());
sp.setStandardPhonNumber(fullFax);
%> </td>
    <td class="tdb" ><%=sp.getFirstNum()+"-"+sp.getMiddleNum()+"-"+sp.getLastNum()%>																</td>
<!-- 20040509 G2B포털개선 수정 END-->

  </tr>
  <tr>
    <td class="tdar">법인구분																			</td>
    <td class="tdb" >
<%	String bukinGubun = ett.getbukinGubun();
    if (bukinGubun != null) {
		if (bukinGubun.equals("1")) {%>
		  예
<%		} else {	%>
	  	  아니오
<%		}
    }
%>
	</td>
    <td class="tdar">법인설립일																			</td>
    <td class="tdb" ><%=ett.getestablish()%>															</td>
  </tr>
  <tr>
    <td class="tdar">자본금																				</td>
    <td class="tdb" ><%=ComStr.divideComma(ett.getmoney(),",",3)%>&nbsp;원								</td>
    <td class="tdar">종업원수																			</td>
    <td class="tdb" ><%=ComStr.divideComma(ett.getworker(),",",3)%>&nbsp;명								</td>
  </tr>
  <tr>
    <td class="tdar">최근3년간총매출액																	</td>
    <td class="tdb" ><%=ComStr.getStringToNumberFormat(ett.getoutput())%>&nbsp;원						</td>
    <td class="tdar">홈페이지																			</td>
    <td class="tdb" ><%=ett.gethomepage()%>																</td>
  </tr>
  <tr>
    <td class="tdar">업체상태																			</td>
    <td class="tdb" colspan="3">
<% String UPCHESGUBUN = null;
    UPCHESGUBUN = ett.getdaepyojuminNo();

        if (UPCHESGUBUN != null) {         %>
            <%=List.getCode("T","upchesGubun", "GU4", ett.getupchesGubun())%>		</td>
<%      } else {                        %>
<%      }                               %>
  </tr>
<!--   <tr>
    <td class="tdar">이미지명																			</td>
    <td class="tdb" colspan="3"><%=ett.getimageNm()%>													</td>
  </tr>
 -->  <tr>
    <td class="tdar">회사소개																			</td>
    <td class="tdb" colspan="3"><%=ett.getcopInt()%>													</td>
  </tr>
  <tr>
    <td class="tdar">주생산품류부호																		</td>
    <td class="tdb" >
<% String SAENSANGUBUN = null;
    SAENSANGUBUN = ett.getsaensanGubun();

        if (SAENSANGUBUN != null) {         %>
            <%=List.getCode("T","saensanGubun", "GU2", ett.getsaensanGubun())%>				</td>
<%      } else {                        %>
<%      }                               %>
    <td class="tdar">업체규모구분																		</td>
    <td class="tdb" >
<% String UPCHEKGUBUN = null;
    UPCHEKGUBUN = ett.getupchekGubun();

        if (UPCHEKGUBUN != null) {         %>
            <%=List.getCode("T","upchekGubun", "GU3", ett.getupchekGubun())%>					</td>
<%      } else {                        %>
<%      }                               %>
  </tr>
  <tr>
    <td class="tdar">최초입력자아이디																	</td>
    <td class="tdb" ><%=ett.getfirstId()%>																</td>
    <td class="tdar">최초입력일																			</td>
    <td class="tdb" ><%=ett.getfirstDay()%>																</td>
  </tr>
  <tr>
    <td class="tdar">최근수정자아이디																	</td>
    <td class="tdb" ><%=ett.getupdateId()%>																</td>
    <td class="tdar">최근수정일																			</td>
    <td class="tdb" ><%=ett.getupdateDay()%>															</td>
  </tr>
		<tr>
			<td height="2" colspan="4" class="line"></td>
		</tr>
</table>
<!---------------------- 마스터정보 테이블 끝 ------------------------>

<br>
<!---------------------- 사용자정보 타이틀 테이블 시작 ------------------------>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tr">
  <tr>
	<td class="fontb">[ 사용자정보 ]</td>
  </tr>
</table>
<!---------------------- 사용자정보 타이틀 테이블 끝 ------------------------>

<!---------------------- 사용자정보 테이블 시작 ------------------------>
<!---------------------- 사용자정보 테이블 시작				------------------------>
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
 		<tr>
			<td height="4" colspan="4" class="line"></td>
		</tr>
 <tr>
    <td class="tdar"	width="20%"><p><span class="red">*</span>&nbsp;성명</p></td>
    <td class="tdb"		width="30%"><input class="read" name="name"	value="" maxlength="10" ></td>
    <td class="tdar"	width="20%"><p><span class="red">*</span>&nbsp;주민등록번호</p></td>
    <td class="tdb"		width="30%">
<!-- 20040509 G2B포털개선 수정 START-->    
    <input  type="text" class="read" name="juminNo1" value="" maxlength="6" size=6 onkeydown="js_OnlyNumber(this)">-<input type="password" class="read" name="juminNo2" value="" maxlength="7" size=7  onkeydown="js_OnlyNumber(this)">		
    <input type=hidden class="read" name="juminNo" value="" maxlength="13">		
<!-- 20040509 G2B포털개선 수정 END-->    

	<input type=hidden name="real_jumin_chk_yn1" value="">  <!-- 실명인증 확인여부 -->
	<input type=hidden name="old_taskmaster" value="">      <!-- 실명인증 확인여부 -->
	<input type=hidden name="old_jumin" value="">           <!-- 실명인증 확인여부 -->

	<a href="javascript:real_jumin_chk('사용자', document.fm.name, document.fm.juminNo1, document.fm.juminNo2, 'document.fm.real_jumin_chk_yn1');">
	<img src="/img/bu_anonymity.gif" style=cursor:hand border=0 align="absmiddle"></a>

    </td>
  </tr>
  <tr>
    <td class="tdar"	width="20%">부서명																	</td>
    <td class="tdb"		width="30%"><input class="read" name="buserNm"		value="" maxlength="15" >		</td>
    <td class="tdar"	width="20%">부서장																	</td>
    <td class="tdb"		width="30%"><input class="read" name="buserJang"	value="" maxlength="7" >		</td>
  </tr>
  <tr>
    <td class="tdar"	width="20%">직책																	    </td>
    <td class="tdb"		width="30%"><input class="read" name="duty"			value="" maxlength="15" >		</td>
    <td class="tdar"	width="20%"><span class="red">*</span>&nbsp;메일주소																    </td>
    <td class="tdb"		width="30%"><input class="read" name="mail" size=38 maxlength="30" >		        </td>
  </tr>
   <tr>
    <td class="tdar"	width="20%"><p><span class="red">*</span>&nbsp;전화번호</p></td>
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
      <input type=hidden class="read" name="telNo_1" onkeydown="js_OnlyNumber(this)" onFocus="js_RemoveChar(this, '-')" onBlur="fnAddDash(this, 3)"	maxlength="8">
<!-- 20040509 G2B포털개선 수정 END-->
<!--20040509 이전소스
      -&nbsp;<input class="read" name="telNo_1" maxlength="8" onkeydown="js_OnlyNumber(this)" onFocus="js_RemoveChar(this, '-')" onBlur="fnAddDash(this, 3)">
-->      
	  </td>
    <td class="tdar"	width="20%"><p><span class="red">*</span>&nbsp;팩스번호</p></td>
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
			<td height="2" colspan="4" class="line"></td>
		</tr>
</table>
<!---------------------- 사용자정보 테이블 끝 ------------------------>
<br>
<!---------------------- 버튼 테이블 시작 ------------------------>
<table width="100%" cellpadding="2" cellspacing="2">
  <tr>
	<td align="center">
	  <img src="/img/bu_entry.gif" style="cursor:hand;"	border="0" align="absmiddle" onclick="save_click('UserIns')">
	</td>
  </tr>
</table>
      </td>
      <td width="28"></td>
    </tr>
    <tr height="35"><td colspan="3"></td></tr>
    <tr height="*"><td colspan="3"></td></tr>
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
