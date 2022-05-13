<!--/**************************************************************************************/
/*                                                                                        */
/*    Program ID    :     UM_RAJ_GovrA010s.jsp                                            */
/*                                                                                        */
/*    description   :     공공기관등록									  */
/*                                                                                        */
/******************************************************************************************/
/*		최초생성         2002.06.12         박 민 호                                   */
/****************************************************************************************-->
<%@page language="java" %>
<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*,common.*,g2b.sso.*" %>
<% SSO sso = new SSO(pageContext); %>
<%@page import="common.*" %>

<%@page contentType="text/html; charset=EUC-KR" %>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>
<jsp:useBean id="List"      class="beans.Common_ComBo" scope="page" />
<jsp:useBean id="Hutil"     class="common.HttpUtil" scope="page" />
<jsp:useBean id="ctl1"      class="beans.UM_RAB_GovuA010p" scope="page" />
<jsp:useBean id="License"   class="beans.UM_ADB_GovrA010c" scope="page" /> <!-- 공공기관인증서 조회컨트롤 -->
<% 
    sso.checkLogin();

    String id = sso.getID();
 %>

<%
try{

    ParameterParser psr = new ParameterParser(request);

    String recept       = psr.getStringParameter("recept"                   ,"");   // 공공기관등록 신청 접수번호
    String g2bCode      = psr.getStringParameter("g2bCode"                  ,"");
    String LicenseCode  = psr.getStringParameter("LicenseCode"              ,"");   // 공공기관인증서 신청 접수번호
    String ApprovalCode = psr.getStringParameter("ApprovalCode"             ,"");   // 승인지청코드
    String flag         = psr.getStringParameter("flag"                     ,"");   // syn_수요기관매핑코드에서 수요기관이 없는경우
    String dependCode   = "";           // 공공기관인증서 신청 요청인가코드
    String str          = "";
    String str1         = "";
    int    maxcnt       = 0;

    UM_RAE_GovuA010b ettcode  = null;
    ettcode = ctl1.select_goma(recept);


    //-- 사용_접수공공기관마스터 Table Select Beans
    UM_ADE_GovuA040b[] ett = null;
    ett    = ctl1.select_math(recept);
    maxcnt = ctl1.select_mathCount(recept);

    //-- 사용_접수공공기관인증서 Table 신청정보 Select Beans
    UM_ADJ_GovuA020b ettLicense  = null;
    ettLicense = License.select_GovernmentList_Confirm(LicenseCode);

    //-- 행정표준정보 Select Beans
    UM_GOJ_GovuA010b ettall  = null;
	ettall = ctl1.select_organ(g2bCode);

    String approval = ettcode.getenYn();

    if (!LicenseCode.equals("")) {
        dependCode = ettLicense.getdependCode();
    }
%>

<html>
<head>
<title>공공기관등록</title>	

<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link rel="stylesheet" type="text/css" href="../../css/UM.css">
</head>
<Script src="/js/UM.js" language="Javascript"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--

    function confirm_click() 
	{	
		document.fm.flag.value = "S";
		document.fm.action = "../RA/UM_RAJ_GovuC011l.jsp";
		document.fm.target = "_self";
		document.fm.submit();
	}

-->
</Script>

<body background="/img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">

<form name="fm" method="post" action="/servlet/servlets.UM_ADJ_GovrB040c">
<input type="hidden" name="masterID" value="<%=id%>" class="read"></td>
<input type="hidden" name="flag"     value="">
<input type="hidden" name="approval" value="<%=approval%>">
<input type="hidden" name="ApprovalCode" value="<%=ApprovalCode%>">
<input type="hidden" name="dependCode"   value="<%=dependCode%>">
<input type="hidden" name="LicenseCode"  value="<%=LicenseCode%>">
<input type="hidden" name="recept"       value="<%=recept%>">

<table width="800" border="0" cellpadding="0" cellspacing="0" height="100%"  background="/img/bg_sub.gif">
    <tr valign="bottom" height="57"> 
        <td rowspan="2"><img src="/img/sub_title_01.jpg"></td>
        <td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE"> 
        &nbsp; 공공기관 기본정보등록</td>
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
<!---------------------- 마스터정보 타이틀 테이블 시작-->
	<table width="100%" cellpadding="2" cellspacing="1">
		<tr> 
			<td class="fontb">[일반정보]</td>
		</tr>
	</table>
<!---------------------- 마스터정보 타이틀 테이블 끝 -->
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
        <tr> 
            <td height="4" colspan="4" class="line"></td>
        </tr> 
		<tr> 
			<td class="tdar" width="20%"><font color = "red">*</font> 접수번호</td>
			<td class="tdb"  colspan="3"><font color = "red"><%=recept%></font></td>
		</tr>
<%  String G2BCodeSub = "";
        G2BCodeSub = g2bCode.substring(0,1);

    if (!G2BCodeSub.equals("Z")) {
%>
        <tr> 
			<td class="tdar" width="20%"><font color = "red">*</font> 행정표준기관코드</td>
			<td class="tdb"  colspan="3"><font color = "red"><%=g2bCode%></td>	
        </tr>
		<tr> 
			<td class="tdar" width="20%"><font color = "red">*</font> 기관명(전체)</td>
			<td class="tdb"  colspan="3"><%=ettcode.getgoNameFull()%></td>
		</tr>
<%  } else  {                           %>
        <tr> 
			<td class="tdar" width="20%"><font color = "red">*</font> 행정표준기관코드</td>
			<td class="tdb"  colspan="3"><font color = "red"><%=g2bCode%></td>	
        </tr>
		<tr> 
			<td class="tdar" width="20%"><font color = "red">*</font> 기관명(전체)</td>
			<td class="tdb"  colspan="3"><%=ettcode.getgoNameFull()%></td>
		</tr>
<%  }                                   %>
		<tr> 
			<td class="tdar" width="20%">&nbsp;&nbsp;기관명(약어)</td>
			<td class="tdb"  colspan="3"><%=ettcode.getgoNameShort()%></td> 
		</tr>
		<tr> 
			<td class="tdar" width="20%"><font color = "red">*</font> 공공기관명(영문)</td>
			<td class="tdb"  colspan="3"><%=ettcode.getgoNameEn() %></td>
		</tr>
        <%  String relation = ettcode.getrelation();
            if (relation != null)   {   %>
                    <tr> 
                        <td class="tdar" width="20%"><font color = "red">*</font> 소관구분</td>
                        <td class="tdb"  colspan="3"><%=List.getCode("T", "relation", "GUA", ettcode.getrelation())%></td>
                    </tr>
        <% } else                   {   %>
                    <tr> 
                        <td class="tdar" width="20%"><font color = "red">*</font> 소관구분</td>
                        <td class="tdb"  colspan="3"><%=List.getCode("T", "relation", "GUA", "")%></td>
                    </tr>
        <% }                            %>
		<tr> 
            <td class="tdar" width="20%"><font color = "red">*</font> 우편번호</td>
			<td class="tdb"  width="30%"><%=ettcode.getZIPCODE()%></td>
			<td class="tdar" width="20%"><font color = "red">*</font> 사업자등록번호</td>
			<td class="tdb"  width="30%"><%=insert_minus_saupno(ettcode.getsaupNo())%></td>
		</tr>
         <tr> 
			<td class="tdar" width="20%"><font color = "red">*</font> 주소</td>
			<td class="tdb"  colspan="3"><%=ettcode.getADDR() %>&nbsp;<%=ettcode.getaddress2() %></td>            
		</tr> 
        <tr> 
			<td class="tdar" width="20%"><font color = "red">*</font> 대표 전화번호</td>
			<td class="tdb"  width="30%"><%=ettcode.gettelNum() %></td>
			<td class="tdar" width="20%"><font color = "red">*</font> 대표 팩스번호</td>
			<td class="tdb"  width="30%"><%=ettcode.getfaxNum() %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%">&nbsp;&nbsp;업태</td>
			<td class="tdb"  width="30%"><%=ettcode.getbuConditon() %></td>
			<td class="tdar" width="20%">&nbsp;&nbsp;업종</td>
			<td class="tdb"  width="30%"><%=ettcode.getbuType() %></td>
		</tr>
        <tr> 
			<td class="tdar" width="20%">&nbsp;&nbsp;홈페이지</td>
			<td class="tdb"  colspan="3"><%=ettcode.gethomepage() %></td>
		</tr>
        <tr> 			
			<td class="tdar" width="20%"><font color = "red">*</font> 채권자명</td>
			<td class="tdb"  colspan="3"><%=ettcode.getcreditName() %></td>
		</tr>
        <tr> 			
			<td class="tdar" width="20%"><font color = "red">*</font> 물품관리관명</td>
			<td class="tdb"  colspan="3"><%=ettcode.getgoodsMaster() %></td>
		</tr>
        <tr> 
			<td class="tdar" width="20%"><font color = "red">*</font> 공공기관회계코드</td>
			<td class="tdb"  colspan="3">
                <%  String mathCode = "";
                    for(int i=0; i < maxcnt; i++) { %>
                        <%=List.getCode("T","mathCode","J34",ett[i].getmathCode())%>
               <%	
                        mathCode += ett[i].getmathCode()+",";
                    }                               
                %>
            </td>
		</tr>
        <tr> 
            <td height="2" colspan="4" class="line"></td>
        </tr> 
    </table>
<br>
    <table width="100%" cellpadding="2" cellspacing="1">
		<tr> 
			<td class="fontb">[담당자정보]</td>
		</tr>
    </table>
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
        <tr> 
            <td height="4" colspan="4" class="line"></td>
        </tr>  		
        <tr> 
            <td class="tdar" width="20%"><font color = "red">*</font> 담당자명</td>
            <td class="tdb"  width="30%"><%=ettcode.gettaskmaster() %></td>
            <td class="tdar" width="20%"><font color = "red">*</font> 담당자 부서명</td>
            <td class="tdb"  width="30%"><%=ettcode.getmasterPost() %></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><font color = "red">*</font> 담당자 전화번호</td>
			<td class="tdb"  width="30%"><%=ettcode.getmasterTel() %></td>
			<td class="tdar" width="20%"><font color = "red">*</font> 담당자 팩스번호</td>
			<td class="tdb"  width="30%"><%=ettcode.getmasterFax()%></td>
		</tr>
		<tr> 
			<td class="tdar" width="20%"><font color = "red">*</font> 담당자 메일주소</td>
			<td class="tdb"  colspan="3"><%=ettcode.getmasterMail() %></td>       
		</tr>		
        <tr> 
            <td height="2" colspan="4" class="line"></td>
        </tr> 
	</table>
<br>
<%  if (flag.equals("Not")) {   %>
	<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
        <tr> 
            <td height="4" colspan="4" class="line"></td>
        </tr>
        <tr>
			<td width="20%" class="tdar" align="center">
                <font color = "red">
                    상기 수요기관은 최상위기관이 존재하지 않는 관계로 조달EDI 시스템에 적용에 실패했습니다.
                    따라서, 상기 수요기관은 조달청 ☎ 042-481-7141 이원천주사에 문의하시기 바랍니다.
                </font>
            </td>            
		</tr>
		<tr> 
            <td height="2" colspan="4" class="line"></td>
        </tr> 
    </table>
<%  }                           %>
<br>
<% if (approval.equals("N")) {   %>
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
        <tr align="center"> 
            <td height="24">
                <img src = "/img/bu_back.gif"       style="cursor:hand;"	align = "absmiddle" border=0 onClick="javascript:back();">
                <img src = "/img/bu_approval2.gif"  style="cursor:hand;"	align = "absmiddle" border=0 onClick="javascript:js_submit();">&nbsp;
                <img src = "/img/bu_denial2.gif"    style="cursor:hand;"	align = "absmiddle" border=0 onClick="javascript:js_deny();">&nbsp;
            </td>
		</tr>
	</table>
<% } else                    {          %>
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
        <tr align="center"> 
            <td height="24">
                <img src = "/img/bu_back.gif" style="cursor:hand;"	align = "absmiddle" border=0 onClick="javascript:confirm_click();">
            </td>
        </tr>
    </table>
<% }                                    %>
 </td>
      <td width="28"></td>
    </tr>
    <tr height="35"><td colspan="3"></td></tr>
    <tr height="*"><td colspan="3"></td></tr>
    <tr> 
      <td colspan="3" height="50"><%@ include file="/jsp/common/Footer.jsp" %></td>
    </tr>
  </table>
</form>
</form>
</body>
</html>
<%}catch(Exception e){
	out.println("error박민호: "+e.toString());
}
%>
