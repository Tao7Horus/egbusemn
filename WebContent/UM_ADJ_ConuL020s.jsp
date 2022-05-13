<%-- 
/***************************************************************************/
/* Program ID         : UM_ADJ_ConuL020s.jsp                        	*/
/* Program Explanation: Màn hình tra cứu GCN Nhà thầu					*/
/* Program Summary    : Màn hình tra cứu GCN Nhà thầu					*/
/* Relation Program   : */ 
/*                     	*/
/* Table              :                  */
/***************************************************************************/
/* Create Composer	  : MR. SONDN 03.06.2009                       		*/
/***************************************************************************/
--%>
<%@page language="java" %> 
<%@ page errorPage="/jsp/common/jspToError.jsp?type=0&url=&message=001" %> 
<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*" %> 
<%@page import="common.*" %>  
<%@page contentType="text/html; charset=UTF-8" %> 
 
<%@ include file = "/jsp/common/fnUmCommon.jsp" %> 
<jsp:useBean id="ctl" class="beans.UM_ADB_GovrA010c" scope="page" /> 
<% 
 
    com.oreilly.servlet.ParameterParser psr     = new com.oreilly.servlet.ParameterParser(request); 
 
    String saupNo           = psr.getStringParameter("saupNo",""); 
    String dependCode       = psr.getStringParameter("dependCode","");  
    String flag             = psr.getStringParameter("flag",""); 
    String Rg2bCode         = psr.getStringParameter("g2bCode",""); 
  
	String ExceptOK		    = psr.getStringParameter("ExceptOK","");

    String RsaupNo          = ""; 
    String RsaupNoConfirm   = ""; 
 
    int  maxcnt=0; 
    UM_ADJ_GovuA020b ettcode  = null; 
 
    if(flag.equals("Submit")) { 
        maxcnt = ctl.EnterpriseList_Confirm_Count(dependCode.trim(),saupNo.trim());
        if(maxcnt > 0){ 
        	ettcode = ctl.EnterpriseList_Confirm(dependCode.trim(), saupNo.trim()); 
        } 
 
    	if (!Rg2bCode.equals("")) { 
        	RsaupNoConfirm = Rg2bCode.substring(0, 1); 
    	} 
 
    	if (RsaupNoConfirm.equals("B"))          { 
        	RsaupNo = RsaupNoConfirm; 
    	} else if (RsaupNoConfirm.equals("Z"))   { 
        	RsaupNo = Rg2bCode.substring(0, 2); 
    	} 
    } 
%> 
<html> 
<head> 
<title>Tra cứu tình trạng tiến hành đăng ký Bên mời thầu </title> <!-- 수요기관 등록 진행 현황 조회 -->
<link rel="stylesheet" type="text/css" href="http://muasamcong.mpi.gov.vn/css/TA.css"> 
<script language="javascript" src="../js/UM.js"></script>
</head> 
<SCRIPT LANGUAGE="JavaScript"> 

	function js_submit(){  
  	 
        if(document.forms[0].saupNo.value== null || document.forms[0].saupNo.value.length < 5){ 
        	alert("Nhập số ĐKKD (xx kí tự)");// alert("사업자등록번호 입력 바랍니다.[10 자리]"); 
        	document.forms[0].saupNo.focus(); 
        	return; 
        } 
 
        if(document.forms[0].dependCode.value== null || document.forms[0].dependCode.value.length ==""){ 
        	alert("Nhập mã phê duyệt");// alert("요청인가코드 입력 바랍니다."); 
        	document.forms[0].dependCode.focus(); 
        	return; 
        } 
 
        document.forms[0].flag.value ="Submit"; 
        document.forms[0].target="_self"; 
        document.forms[0].method="post"; 
        document.forms[0].action="/AD/UM_ADJ_ConuL020s.jsp"; 
        document.forms[0].submit(); 
        return; 
    } 
 
	function js_trim(strValue) { 
 
		if(strValue.length > 0) { 
 
			if(strValue.charAt(0) == ' ') { 
				strValue = strValue.substring(1, strValue.length);               
			} 
 
			if(strValue.charAt(strValue.length - 1) == ' ') { 
				strValue = strValue.substring(0, strValue.length - 1);               
			} 
		}  
		return strValue; 
	} 
 
	function ignoreSpaces(string) { 
		var temp = ""; 
   	 	var temp2 ="";
 
		if(string.length > 16) { 
			string = string.substring(0,16); 
		} 
 
		temp = js_trim(string); 
    	temp2 =  js_RemoveAll(temp); 
		document.go.COMPN.value = temp2; 
		document.go.CNAME.value = temp2; 
	} 
 
	function js_RemoveAll(param) {
	    for (i=0; i< param.length; i++){ 
	       param = param.replace(/(\;|\"|\=|\~|\&|\%|\!|\_|\#|\,|\'|\`|\/|\$|\^|\*|\(|\)|\+|\.|\?|\\|\{|\}|\||\[|\]|\-|\:|\<|\>|\@)/g,"");
			}
			return param;
	} 
			
			
	function submitForm() 
	{ 
		compn = document.go.COMPN.value; 
		ignoreSpaces(compn); 
    
        var code =""; 
 
        code = document.forms[0].Code.value; 
		if (document.forms[0].CLICK.value=="1") { 
			//alert ("이미 사용자 등록 버튼을 클릭하셨습니다. \r\r다음 페이지로 이동중이므로 잠시 기다려 주십시요.(최장 1분)");
			alert("Bạn đã nhấn vào nút Đăng ký Người sử dụng.\r\r Vì đang di chuyển đến trang tới, xin chờ trong giây lát.");  
			return; 
		} 
        if (code == "0") { 
                if (confirm(' Thời gian đăng kí người dùng \r\r để được cấp chứng nhận số tối đa hết 1 phút.\r\r Hãy hạn chế di chuyển trang trong khi đang thực hiện đăng kí.')) { 
//                if (confirm('공인인증기관 인증서 발급을 위한 \r\r사용자 등록은 최장 1분이 소요됩니다. \r\r등록 처리 중에는 페이지 이동을 삼가하여주십시요.')) { 
                    document.forms[0].CLICK.value="1"; 
                    document.forms[0].action="./RegUserKICA.jsp" 
                    document.forms[0].submit(); 
                    return; 
                } 
 
        } else if (code == "1") { 
                 if (confirm(' Thời gian đăng kí người dùng \r\r để được cấp chứng nhận số tối đa hết 1 phút.\r\r Hãy hạn chế di chuyển trang trong khi đang thực hiện đăng kí.')) { 
//                if (confirm('공인인증기관 인증서 발급을 위한 \r\r사용자 등록은 최장 1분이 소요됩니다. \r\r등록 처리 중에는 페이지 이동을 삼가하여주십시요.')) { 

                    document.forms[0].CLICK.value="1"; 
                    document.forms[0].action="./RegUser.jsp" ;
                    document.forms[0].submit(); 
                    return; 
                } 
 
        } else if (code == "2") { 
                 if (confirm(' Thời gian đăng kí người dùng \r\r để được cấp chứng nhận số tối đa hết 1 phút.\r\r Hãy hạn chế di chuyển trang trong khi đang thực hiện đăng kí.')) { 
//                if (confirm('공인인증기관 인증서 발급을 위한 \r\r사용자 등록은 최장 1분이 소요됩니다. \r\r등록 처리 중에는 페이지 이동을 삼가하여주십시요.')) { 
 
                    document.forms[0].CLICK.value="1"; 
                    document.forms[0].action="./RegUserSK.jsp" ;
                    document.forms[0].submit(); 
                    return; 
                } 
 
        } else if (code == "3") { 
                   if (confirm(' Thời gian đăng kí người dùng \r\r để được cấp chứng nhận số tối đa hết 1 phút.\r\r Hãy hạn chế di chuyển trang trong khi đang thực hiện đăng kí.')) { 
//                if (confirm('공인인증기관 인증서 발급을 위한 \r\r사용자 등록은 최장 1분이 소요됩니다. \r\r등록 처리 중에는 페이지 이동을 삼가하여주십시요.')) { 
 
                    document.forms[0].CLICK.value="1"; 
                    document.forms[0].action="./RegUserOneGrade.jsp" ;
                    document.forms[0].submit(); 
                    return; 
                } 
        }else if (code == "4") { 
                if (confirm(' Thời gian đăng kí người dùng \r\r để được cấp chứng nhận số tối đa hết 1 phút.\r\r Hãy hạn chế di chuyển trang trong khi đang thực hiện đăng kí.')) { 
//                if (confirm('공인인증기관 인증서 발급을 위한 \r\r사용자 등록은 최장 1분이 소요됩니다. \r\r등록 처리 중에는 페이지 이동을 삼가하여주십시요.')) { 
 
                    document.forms[0].CLICK.value="1"; 
                    document.forms[0].action="./RegUserCrossCert.jsp"; 
                    document.forms[0].submit(); 
                    return; 
                } 
        } else if (code == "5") { 
        	if (confirm(' Thời gian đăng kí người dùng \r\r để được cấp chứng nhận số tối đa hết 1 phút.\r\r Hãy hạn chế di chuyển trang trong khi đang thực hiện đăng kí.')) { 
//              if (confirm('공인인증기관 인증서 발급을 위한 \r\r사용자 등록은 최장 1분이 소요됩니다. \r\r등록 처리 중에는 페이지 이동을 삼가하여주십시요.')) { 
				window.open("",'addCert','status=yes,resizable=yes,scrollbars=no,toolbar=no,width=400,height=300');
				document.forms[0].target="addCert";
                  document.forms[0].CLICK.value="5"; 
                  document.forms[0].action="./RegMPI.jsp"; 
                  document.forms[0].submit(); 
                  return; 
            }
    } 
        }
	<% if(flag.equals("Submit") && maxcnt > 0 ) { %> 
		function toPrint(){ 
			var theURL="/RA/UM_RAJ_LiReport.jsp?recept=<%=ettcode.getrecept() %>&approvalCode=<%=dependCode%>&G2BCODE=<%=saupNo%>"; 
			window.open(theURL,'calendar','toolbar=yes,location=no,status=yes,menubar=yes,scrollbars=yes,resizable=no,width=750,height=700'); 
			return; 
		} 
	<%	} %> 


	// 2007.03.09	수요기관 인증서 발급요청 관련 기능개선  	Nâng cao, nhu cầu cấp giấy chứng nhận trong một yêu cầu
	function regist() {
		if (document.forms[0].CERT_ORG1[2].checked == true) { //한국정보인증 	thông tin Hàn Quốc , chứng nhận
				document.forms[0].CERT_ORG.value = "0"
		} else if  (document.forms[0].CERT_ORG1[3].checked == true) { //한국전산원 (한국정보사회진흥원)Cơ quan Tin Hàn Quốc (Hàn Quốc thông tin của Viện Phát triển Xã hội) 
				document.forms[0].CERT_ORG.value = "1"
		} else if  (document.forms[0].CERT_ORG1[0].checked == true) {
				document.forms[0].CERT_ORG.value = "2"
		}else if  (document.forms[0].CERT_ORG1[1].checked == true) {
				document.forms[0].CERT_ORG.value = "4"
		} else {
			alert("Chọn cơ quan cấp chứng nhận");// alert('공인인증기관을 선택하십시오!.');
				return;
		}

		if(confirm("Bạn sẽ chỉnh sửa cơ quan Chứng nhận công chứ?")){ // if(confirm("공인인증기관 수정등록하시겠습니까?")){

			document.forms[0].flag.value ="Submit"; 
			window.open("",'modifyCERTWin','status=yes,resizable=yes,scrollbars=no,toolbar=no,width=400,height=300');
			document.forms[0].target="modifyCERTWin";
			document.forms[0].method="post";
			
			//document.forms[0].action="/servlets/UM_ADV_GovrA010c";
			document.forms[0].action="/servlet/servlets/UM_ADV_GovrA010c";
			document.forms[0].submit();
			return;
		}	
	}

</Script> 
<body > 
   <div class="wrappertable">
<div class="wrapperhead"><span class="HEADLINENEW"> Tra cứu tình trạng đăng ký chứng nhận số</span></div>

<div class="contenttable">
<form name="go"> 
<input type="hidden" name="fileName"	value="인증서신청서"> 
<input type="hidden" name="flag"  value=""> 
<input type="hidden" name="CLICK" value=""> 
<input type="hidden" name="NICKN" value="hawk"> 
  
<!--------- G2B고도화 등록절차 추가 : include ----------> 
<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
	<tr><td><jsp:include page="/indexFlow.jsp?gubun=2&req=16"/></td></tr> 
</table> 
<!------------------------------------------------------> 
 
        <!---------------------- 조회조건 테이블 시작 ------------------------> 
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr> 
            <td height="4" colspan="5" class="line"></td> 
        </tr> 
        <tr> 
            <td width="15%" class='tdar'><!--  사업자등록번호   -->Số ĐKKD</td> 
            <td width="15%" class='tdb' ><input type="text" name="saupNo" size="13" value="<%=saupNo %>" maxlength="13" onkeydown="js_OnlyNumber(this)"></td> 
            <td width="15%" class='tdar'><!--   요청인가코드  -->Mã phê duyệt đăng ký</td> 
            <td width="15%" class='tdb' ><input type="text" name="dependCode"   size="16" value="<%=dependCode%>"   maxlength="20"></td> 
            <td width="10%" class='tdbc'>
            	<input  type="button" class="commonbutton" value="Tìm kiếm" onclick="javascript:js_submit();">
            	<!-- <a href="javascript:js_submit();"><img src="../img/bu_searchimg.gif" align="absmiddle" border=0></a>-->
            	</td> 
        </tr> 
        <tr> 
            <td height="2" colspan="5" class="line"></td> 
        </tr> 
    </table> 
<br> 
<%	if(!flag.equals("Submit")) {  %> 
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr> 
            <td class="tdb" > 
                <p><font color = "red"> 
                	▷  Hãy nhập chính xác mã bên mời thầu, số ĐKKD, mã phê duyệt.
                	<!--    수요기관코드, 사업자등록번호, 요청인가코드를 정확히 입력하시기 바랍니다.  --><br> 
                	▷  Sau  khi tra cứu dữ liệu nếu tình trạng là chờ/trả lại thì có thể in lại đơn xin cấp dịch vụ chứng nhận công.
                	<!--  데이타 조회후 진행상태가 대기/반려 인 경우에는 공인인증서비스신청서를 다시 출력하실 수 있습니다.   --> 
                </font> <br></p> 
            </td> 
        </tr> 
    </table> 
<%	} %> 
<br> 
<% 
	if(flag.equals("Submit")) { 
	    if(maxcnt > 0 ) { 
	        if(ettcode.getenYn().equals("Y")) { 
%> 
				<input type="hidden" name="CADDR" value="<%=ettcode.getADDRS()%> <%=ettcode.getADDRESS2()%>"> 
 
    			<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
    			    <tr> 
    			        <td colspan="6" class='gr_box'>
    			        	Phê duyệt xong, trạng thái tiền hành là phê duyệt xong và hiển thị số tham chiếu, mã phê duyệt yêu cầu cấp chứng nhận số.
    			        	<!-- 승인이 완료되면 진행상태 항목에 승인으로 표시되며 참조번호/인가코드는 인증기관에 발급요청(등록)을 해서 발급받아야 표시됩니다. -->
    			        	</td> 
    			    </tr>
    			    <tr> 
    			        <td height="4" colspan="6" class="line"></td> 
    			    </tr> 
    			    <tr> 
    			        <td class="tdar" width="15%">Trạng thái tiến hành <!-- 진행상태 --></td> 
    			        <td class="tdb"  width="15%"><font color = "red"><!-- 승인 -->Phê duyệt</font></td> 
    			        <td class="tdar" width="15%"><!--  참조번호-->Số tham chiếu</td> 
    			        <td class="tdb"  width="20%"><font color = "red"><%=ettcode.getREF_NO()==null?"":ettcode.getREF_NO()%></font></td> 
    			        <td class="tdar" width="15%"><!-- 인가코드 -->Mã phê duyệt đăng ký</td> 
    			        <td class="tdb"  width="20%"><font color = "red"><%=ettcode.getINGA_CODE()%></font></td> 
    			    </tr> 
    			    <tr> 
    			        <td height="2" colspan="6" class="line"></td> 
    			    </tr> 
    			</table> 
				<br> 
    			<table width="100%" cellpadding="2" cellspacing="1"> 
        			<tr> 
            			<td class="fontb"><!-- [공인인증기관선택] -->[Cơ quan chứng nhận công]</td> 
        			</tr> 
    			</table> 
    			<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        			<tr><td height="4" colspan="4" class="line"></td></tr> 
        			<tr> 
            			<td width="20%" class="tdar">Cơ quan chứng nhận công <!-- 공인인증기관 --></td> 
            			<td class="tdb" colspan="3" height="25"> 
            		<font color = "red">
			            <%  String Code = ettcode.getCERT_ORG(); 
			                if(Code.equals("0")){        %> 한국정보인증 : http://www.signgate.com/ra/g2b
			            <%  }else if(Code.equals("1")) { %> 한국정보사회진흥원 : http://sign.nia.or.kr
			            <%  }else if(Code.equals("2")) { %> (주)코스콤 공인인증센터(한국증권전산) : http://www.signkorea.com    
			            <%  }else if(Code.equals("3")) { %> 한국전자인증 : http://www.naraca.co.kr
			            <%  }else {%> 						Cục Quản lý Đấu thầu - Bộ Kế hoạch và Đầu tư 
			            <%  } %>
		            </font>

                    <%  /* if (RsaupNo.equals("B") || RsaupNo.equals("Z0")) {  */  %> 
	        	        <!-- <input type="hidden" name="Code" value="3"> -->
    	    	    <%  /* } else {                                            */  %> 
        		        <input type="hidden" name="Code" value="<%=Code%>"> 
	        	    <%  /*}                                                    */ %> 
            			</td> 
        			</tr>

				<% 
				  // 2007.03.09	수요기관 인증서 발급요청 관련 기능개선 (승인여부 Y 이고 ExceptOK=Y 인경우에 변경가능함)
				  if ( !(("").equals(ExceptOK)) && (ettcode.getenYn().equals("Y")) ) {
				%>
					<input type="hidden" name="CERT_ORG" value="5">
					<tr>
						<td width="20%" class="tdar">Thay đổi cơ quan chứng nhận công <!-- 공인인증기관 변경 --></td>
						<td class="tdb" colspan="3" height="25">
						<INPUT type="radio" name="CERT_ORG5" value="5" <%= (ettcode.getCERT_ORG().equals("5")) ? "checked" : "" %>> Bộ Kế hoạch và Đầu tư (MPI)
						<% if (1==0){ %>
						<%  String certGigwan = ettcode.getCERT_ORG(); %>
							<INPUT type="radio" name="CERT_ORG1" value="2" <%= (certGigwan.equals("2")) ? "checked" : "" %>> KOSCOM코스콤
							<INPUT type="radio" name="CERT_ORG1" value="4" <%= (certGigwan.equals("4")) ? "checked" : "" %>> 한국전자인증
							<INPUT type="radio" name="CERT_ORG1" value="0" <%= (certGigwan.equals("0")) ? "checked" : "" %>> 한국정보인증
						<% Calendar cal=Calendar.getInstance();
							  int year=cal.get(Calendar.YEAR);
							  if(year == 2007) {  %>
							<INPUT type="radio" name="CERT_ORG1" value="1" <%= (certGigwan.equals("1")) ? "checked" : "" %>> 한국정보사회진흥원<!--한국전산원-->
							<% } else  { %>
							<div id = "ap" style="display:none;">
							<INPUT type="radio" name="CERT_ORG1" value="1" <%= (certGigwan.equals("1")) ? "checked" : "" %>> 한국정보사회진흥원<!--한국전산원-->
							</div>
							<font color="red">[* 한국정보사회진흥원은 2008년부터 신청하실 수 없습니다.]</font> 
					<% } %>
							&nbsp;<br>&nbsp;&nbsp;<!-- (가나다순 표기, 한국정보사회진흥원 제외   --> Theo thứ tự a,b,c, các cơ quan ngoài viện phát triển xã hội thông tin Hàn quốc)
							&nbsp;&nbsp;&nbsp;<a href="javascript:regist();"><img src="../img/bu_modifyentry.gif" border="0" align="absmiddle"></a>
						<% } %>
						
						</td>
					</tr> 
					<tr> 
						<td height="2" colspan="4" class="line"></td>
					</tr> 
					<tr >
						<td class="tdb" colspan="4"><font color = "red"> 
						* Trường hợp đã đăng ký chứng nhận số nhưng không được cấp thì có thể chỉnh sửa thay đổi cơ quan cấp chứng nhận công. 
						Nhấn nút Tắt để đổi sang cơ quan chứng nhận công khác. <br>
						<!-- 인증서신청 승인되었는데 해당 공인인증기관으로 발급되지 않는 경우에 다른 공인인증기관으로 변경하여 인증서발급할 수 있도록 <br>
						&nbsp;&nbsp;조치하는 경우만 수정등록하십시오. 닫기 버튼을 눌러야 변경되어 나타납니    --></font></td>
					</tr>
				<% } else { %>					
        			<tr> 
            			<td height="2" colspan="4" class="line"></td> 
        			</tr> 
				<% }  %>

    			</table> 
				<br> 
    			<table width="100%" cellpadding="2" cellspacing="1"> 
        			<tr> 
            			<td class="fontb">[<!-- 기관정보  -->Thông tin Nhà thầu] 
        			</tr> 
    			</table> 
    			<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
    			    <tr> 
    			        <td height="4" colspan="4" class="line"></td> 
    			    </tr> 
    			    <tr > 
						<td width="20%" class="tdar"><!-- 접수번호 --> Số tiếp nhận</td> 
						<td colspan="3" class="tdb" ><%=ettcode.getrecept() %> 
							<input type="hidden" name="recept" value="<%=ettcode.getrecept() %>"> 
						</td>
					</tr> 
					<tr style="display: none;"> 
						<td width="20%" class="tdar"> <!-- 수요기관코드 --> Mã cơ quan</td> 
						<td colspan="3" class="tdb" ><%=ettcode.getG2BCODE() %> 
							<input type="hidden" name="G2BCODE" value="<%=ettcode.getG2BCODE() %>"> 
    			        	<input type="hidden" name="code" value=""> 
    			        </td> 
					</tr> 
					<tr > 
						<td width="20%" class="tdar"><!-- 기관명(한글) --> Tên doanh nghiệp</td> 
						<td width="30%" class="tdb" ><%=ettcode.getCNAME() %> 
    			            <input type=hidden name="CNAME" value="<%=ettcode.getCNAME() %>"> 
    			            <input type=hidden name="COMPN" value="<%=ettcode.getCNAME() %>"> 
    			        </td> 
						<td width="20%" class="tdar"> Tên người đại diện<!-- 대표자명  --></td> 
						<td width="30%" class="tdb" ><%=ettcode.getIDENT() %> 
    			            <input type=hidden name="IDENT" value="<%=ettcode.getIDENT() %>"> 
    			        </td> 
					</tr> 
					<tr > 
						<td width="20%" class="tdar">&nbsp;&nbsp;Tên tiếng Anh<!-- 기관명(영문) --></td> 
						<td width="30%" class="tdb" ><%=ettcode.getKNAME()==null?"":ettcode.getKNAME() %> 
    			            <input type=hidden name="KNAME" value="<%=ettcode.getKNAME() %>"> 
    			        </td> 
						<td width="20%" class="tdar"> Số CMND người đại diện <!-- 대표주민등록번호 --></td> 
						<td width="30%" class="tdb" ><%=ettcode.getIDENTJUMIN() %><%//=insert_minus_jumin_star(ettcode.getIDENTJUMIN()) %> 
    			            <input type=hidden name="IDENTJUMIN" value="<%=ettcode.getIDENTJUMIN() %>"> <!-- 2007.03.23 나라장터 내 주민등록번호 표시체계 개선 -->
    			        </td> 
					</tr> 
					<tr > 
						<td   class="tdar"> Số ĐKKD <!-- 사업자등록번호 --></td> 
						<td  class="tdb" colspan="3" ><%=ettcode.getCOMNO()%><%//=insert_minus_saupno(ettcode.getCOMNO()) %> 
    			            <input type=hidden name="COMNO" value="<%=ettcode.getCOMNO() %>"> 
    			            <input type="hidden" name="ZIPNO" value="<%=ettcode.getZIPNO() %>">  
    			        </td>   
    			    </tr> 
					<tr > 
						<td  class="tdar"> Địa chỉ<!-- 주소 --></td> 
    			        <td  class="tdb"  colspan="3"><%=ettcode.getADDRS() %>
						  <!-- <%=ettcode.getADDRESS2()==null?"":ettcode.getADDRESS2() %>  -->
    			            <input type=hidden name="ADDRS" value="<%=ettcode.getADDRS()%> <%=ettcode.getADDRESS2()==null?"":ettcode.getADDRESS2()%>">  
						</td> 
					</tr> 
    			    <tr> 
    			        <td height="2" colspan="4" class="line"></td> 
    			    </tr> 
				</table> 
				<br> 
				<table width="100%" cellpadding="2" cellspacing="1"> 
					<tr> 
						<td class="fontb">[Thông tin người phụ trách]<!-- 신청자 정보 --> 
					</tr> 
				</table> 
 
				<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        			<tr> 
            			<td height="4" colspan="4" class="line"></td> 
        			</tr> 
        			<tr> 
						<td width="20%" class="tdar"> Tên người phụ trách<!-- 성명 --></td> 
						<td width="30%" class="tdb" ><%=ettcode.getMYNAME() %> 
            			    <input type=hidden name="NAME" value="<%=ettcode.getMYNAME() %>"> 
            			</td> 
						<td width="20%" class="tdar"> Phòng/Ban <!-- 부서명 --></td> 
						<td width="30%" class="tdb" ><%=ettcode.getOFFICEDE()==null?"":ettcode.getOFFICEDE() %> 
            			    <input type=hidden name="CPART" value="<%=ettcode.getOFFICEDE()==null?"":ettcode.getOFFICEDE() %>"> 
            			</td></tr>  
					<tr> 
						<td width="20%" class="tdar"> Số CMND <!-- 주민등록번호 --></td> 
						<td width="30%" class="tdb" ><%=ettcode.getJUMIN()==null?"":ettcode.getJUMIN()%><%//=insert_minus_jumin(ettcode.getJUMIN()) %> 
            			    <input type=hidden name="JUMIN" value="<%=ettcode.getJUMIN()==null?"":ettcode.getJUMIN() %>"> 
            			</td> 
						<td width="20%" class="tdar"> Số điện thoại <!-- 전화번호 --></td> 
						<td width="30%" class="tdb" ><%=ettcode.getTEL()==null?"":ettcode.getTEL() %> 
            			    <input type=hidden name="TELNO" value="<%=ettcode.getTEL()==null?"":ettcode.getTEL() %>"> 
            			</td> 
        			</tr> 
					<tr > 
						<td width="20%" class="tdar"> ID người dùng <!-- 사용자ID --></td> 
						<td width="30%" class="tdb" ><%=ettcode.getUSRID() %> 
        			        <input type=hidden name="USRID" value="<%=ettcode.getUSRID() %>"> 
        			     </td> 
						<td width="20%" class="tdar"> Số Fax<!-- 팩스번호 --></td> 
						<td width="30%" class="tdb" ><%=ettcode.getFAX()==null?"":ettcode.getFAX() %> 
        			        <input type="hidden" name="FAXNO" value="<%=ettcode.getFAX()==null?"":ettcode.getFAX() %>"> 
        			    </td> 
					</tr> 
					<tr> 
						<td width="20%" class="tdar"> Địa chỉ email</td> 
						<td class="tdb" colspan="3" ><%=ettcode.getEMAIL()==null?"":ettcode.getEMAIL() %> 
        			        <input type="hidden" name="EMAIL" value="<%=ettcode.getEMAIL()==null?"":ettcode.getEMAIL()  %>"> 
						</td> 
					</tr> 
        			<tr><td height="2" colspan="4" class="line"></td></tr> 
    			</table> 
 <% 
	String passwdmodOK = ettcode.getpasswdmodOK();
	if ("Y".equals(passwdmodOK)) {
%>
 				<br> 
				<table width="100%" cellpadding="2" cellspacing="1">
					<tr> 
						<td class="redr"> 
							Mật khẩu có thể thay đổi ngay khi có yêu cầu bổ sung Chứng nhận số .
							<!-- 인증서신청하면서 추가등록에 필요한 이용자관리 비밀번호가 요청대로 변경되었습니다. -->
						</td>
					</tr>
				</table>
				 
				<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
					<tr> 
						<td height="4" colspan="4" class="line"></td>
					</tr>
					<tr>
						<td width="20%" class="tdar"><font color = "red">*Tùy chọn thay đổi mật khẩu <!--  비밀번호 변경여부--></font> </td>            
						<td width="30%" class="tdb" >Yêu cầu thay đổi<!--   변경요청 --></td>
						<td width="20%" class="tdar"> ID người dùng <!-- 이용자ID --></td>            
						<td width="30%" class="tdb" ><%=ettcode.getgID() %></td>
					</tr> 
					<tr> 
						<td height="2" colspan="4" class="line"></td>
					</tr> 
				</table>
<%
	}
%>

<%          } else if (ettcode.getenYn().equals("D")) { 
            		String BACKSAU = ettcode.getback(); 
%> 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
                    <tr> 
                        <td height="4" colspan="4" class="line"></td> 
                    </tr> 
                    <tr> 
                        <td class="tdar" width="20%">Trạng thái tiến hành <!-- 진행상태 --></td> 
                        <td class="tdb"  width="30%"><font color = "red">Trả lại<!-- 반려 --> </font></td> 
                        <td class="tdar" width="20%">Đơn đăng ký dịch vụ chứng nhận <!--  인증서비스신청서--></td> 
                        <td class="tdb"  width="30%"> 
                        	<input  type="button" class="commonbutton" value=" In " onclick="javascript:toPrint();">
                        	<!-- <a href="javascript:toPrint();"><img src="../img/bu_print.gif" align="absmiddle" border=0></a> -->
                        </td> 
                    </tr> 
                    <tr> 
                        <td class="tdar" width="20%">Lý do trả về <!-- 반려사유 --></td> 
                        <td class="tdb"  width="80%" colspan="3"><%=ettcode.getback() %></td><!-- 2007.02.23 colspan="3" 넣음 --> 
                    </tr> 
                    <tr> 
                        <td height="2" colspan="4" class="line"></td> 
                    </tr> 
                </table>

<%          } else if (ettcode.getenYn().equals("E")) { 
            		String BACKSAU = ettcode.getback(); 
%> 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
                    <tr> 
                        <td height="4" colspan="4" class="line"></td> 
                    </tr> 
                    <tr> 
                        <td class="tdar" width="20%">Trạng thái tiến hành <!--  진행상태 --></td> 
                        <td class="tdb"  width="30%"><font color = "red">Bảo lưu<!-- 보류 --> </font></td> 
                        <td class="tdar" width="20%">Đơn đăng ký dịch vụ chứng nhận <!-- 인증서비스신청서 --></td> 
                        <td class="tdb"  width="30%"> 
                        	<input type="button" class="commonbutton" value=" In " onclick="javascript:toPrint();"/>
                        	<!--  <a href="javascript:toPrint();"><img src="../img/bu_print.gif" align="absmiddle" border=0></a>--> 
                        </td> 
                    </tr> 
                    <tr> 
                        <td class="tdar" width="20%">Lý do trả về <!-- 보류사유 --></td> 
                        <td class="tdb"  width="80%" colspan="3"><%=ettcode.getback() %></td><!-- 2007.02.23 colspan="3" 넣음 --> 
                    </tr> 
                    <tr> 
                        <td height="2" colspan="4" class="line"></td> 
                    </tr> 
                </table>
 
<%          } else if(ettcode.getenYn().equals("N")) {        %> 
 
                    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
                        <tr> 
                            <td height="4" colspan="4" class="line"></td> 
                        </tr> 
                        <tr> 
                            <td class="tdar" width="20%"> Trạng thái tiến hành <!-- 진행상태 --> </td> 
                            <td class="tdb"  width="30%"><font color = "red">Chờ phê duyệt<!-- 승인 대기중  --></font></td> 
                            <td class="tdar" width="20%">Đơn đăng ký dịch vụ chứng nhận <!-- 인증서비스신청서 --></td> 
                            <td class="tdb"  width="30%"> 
                            	<input type="button" class="commonbutton" value=" In " onclick="javascript:toPrint();"/>
                            	<!-- <a href="javascript:toPrint();"><img src="../img/bu_print.gif" align="absmiddle" border=0></a>--> 
                            </td> 
                        </tr> 
                        <tr> 
                            <td height="2" colspan="4" class="line"></td> 
                        </tr> 
                    </table> 
<%          } 
        } else { %> 
    		<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        		<tr> 
            		<td height="4" colspan="2" class="line"></td> 
        		</tr> 
        		<tr> 
            		<td class="tdar" width="20%">Tiến trình</td> 
            		<td class="tdb"  width="80%"> 
                	<font color = "red"> 
                 		  ▷ <!-- 수요기관인증서 등록신청을 하지 않으셨거나 수요기관코드/사업자등록번호/요청인가코드가 틀립니다 --> 
	                   	 Chưa đăng ký chứng nhận số hoặc mã số cơ quan/số ĐKKD/mã phê duyệt sai.<BR> 
	                	    ▷ <!-- 수요기관코드/사업자등록번호/요청인가코드를 정확히 입력하시기 바랍니다 -->
	                	    Hãy nhập chính xác mã cơ quan/số ĐKKD/mã phê duyệt. 
            		</td> 
        		</tr> 
        		<tr><td height="2" colspan="2" class="line"></td></tr> 
    		</table> 
<%      } 
    } 
%>  
<% 
	if(flag.equals("Submit")){ 
	    if(maxcnt > 0 ){ 
			String YN = ettcode.getbalYN(); 
    		if (YN == null) { 
        		YN  = "N"; 
    		} 
    		if (!YN.equals("Y")) {  %> 
    			<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        			<tr> 
            			<td align="center"> 
<%	 	       if(ettcode.getenYn().equals("Y")) { %> 
						<input onclick="javascript:submitForm();"   type="button" class="commonbutton" value=" Đăng ký " >
                		<!--  <a href="javascript:submitForm();"><img src="../img/bu_entry.gif" align="absmiddle" border=0>--> 
<%      	    }                                   %> 
            			</td> 
        			</tr> 
    			</table> 
		<%  } else {    %> 
    			<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        			<tr> 
        			    <td height="4" colspan="2" class="line"></td> 
        			</tr> 
        			<tr> 
        			    <td class="tdar" width="100%"> 
        			    	<a href="UM_ADJ_GovrL025s.jsp?g2bCode=<%=saupNo %>&dependCode=<%=dependCode  %>&saupNo=<%=saupNo  %>">
        			    	Hoàn thành đăng ký chứng nhận số cơ quan bên mời thầu.</a>
        			    	<!-- 수요기관 인증서 신청을 완료하였습니다. --> 
        			    	</td> 
        			</tr> 
        			<tr> 
        			    <td height="2" colspan="2" class="line"></td> 
        			</tr> 
    			</table> 
<%  	} 
    } 
}%> 
      
</FORM> 
</div>
       <div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
     </div>
</body> 
</html>