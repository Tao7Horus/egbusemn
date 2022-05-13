<%-- 
/********************************************************************/ 
/*																														*/ 
/*    Program ID    :     UM_RAJ_GovuA010u.jsp													*/ 
/*																														*/ 
/*    description   :     수요기관등록신청 수정															*/ 
/*																														*/ 
/********************************************************************/ 
/*      최초생성         2005.03.21																		  */ 
/*	2007.03.23	이광현	나라장터 내 주민등록번호 표시체계 개선							  */
/*	2007.05.31	신경운	SMS수신여부 추가														  */
/*	2007.06.29	이광현	수요기관인증 신청시 외국인 대표자 입력기능 추가			  */
/*	2007.12.13	이광현	기관코드 오류사항 조치요청											  */
/*	2008.03.06	신경운	한국정보사회진흥원	삭제처리 										  */
/*	2008.03.06	신경운	한국정보사회진흥원	삭제처리 										  */
/*	2008.09.11	신경운	신소관구분으로 선택되도록 표기 - getCode_5 추가			  */
/********************************************************************/ 
/***************************************************************************/
/* Program ID         : UM_RAJ_GovuA010u.jsp                        */
/* Program Explanation: Sửa thông tin đăng ký bên mời thầu				*/
/* Program Summary  : 
/* Relation Program   : 
/* Table              : UM_REC_PUB_INSTITU_MAST							*/
/***************************************************************************/
/* Customizing Composer : MR. MINH 16.05.2009                      	*/
/***************************************************************************/
--%>


<%@page language="java" %> 
<%@page contentType="text/html; charset=UTF-8" %> 
<%@ page errorPage="../jsp/common/jspToError.jsp?type=1&url=&message=" %> 
<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*,common.*,g2b.sso.*, dao.*, common.util.*" %>

<jsp:useBean id="List"      class="beans.Common_ComBo"       scope="page" /> 
<jsp:useBean id="ctl1"      class="beans.UM_RAB_GovuA010p"   scope="page" /> 
<jsp:useBean id="License"   class="beans.UM_ADB_GovrA010c"   scope="page" /> 
<jsp:useBean id="comUtil"   class="common.util.CommUtil"     scope="page" /> 
<jsp:useBean id="webutil"   class="common.WebUtil"           scope="page" /> 
<jsp:useBean id="orgDiv"    class="beans.UM_RAB_GovCode020c" scope="page" /> 
<jsp:useBean id="ZipCode"     class="common.ZipCode" scope="page" />
<jsp:useBean id="nationalCtrl" class="beans.UM_RAB_Local" scope="page" />
 
<% 
 
    String recept       = comUtil.retSpace(request.getParameter("recept"));  // 수요기관등록 신청 접수번호 
    String departmentNo    = comUtil.retSpace(request.getParameter("departmentNo"));
    String groupNo    = comUtil.retSpace(request.getParameter("groupNo"));
	Log.debug("aaaaaaaaaa"+recept);
    String recept1      = "";                                                // 인증서접수번호
    String g2bCodeGubun = ""; 
    String XXXXXX       = "XXX";
    
    // 사용_접수수요기관마스터 테이블 정보 
    UM_RAE_GovuA010b ettcode=ctl1.select_goma(recept, groupNo, departmentNo);
       
    String masterCode=ettcode.getg2bCode();          // 수요기관코드 
    Log.debug("aaaaaaaaaa"+masterCode);
    // 승인여부 
    String approval = ettcode.getenYn(); 
    Log.debug("aaaaaaaaaa"+approval);
    String msg   = "";
    String toUrl = "";
    
    if (masterCode.substring(0, 2).equals("Z0")) g2bCodeGubun = "B";
    
    if ("Y".equals(approval)) {
        //msg = "이미 승인처리 되었습니다.";
        msg = "Đã được phê duyệt.";
        toUrl = "UM_RAJ_GovuA010s.jsp?recept=" + recept + "&g2bCode=" + masterCode + "&flag=S";
    } else if ("N".equals(approval)) {
        //msg = "이미 반려처리 되었습니다.";
        msg = "Đã được phê duyệt.";
        toUrl = "UM_RAJ_GovuA010s.jsp?recept=" + recept + "&g2bCode=" + masterCode + "&flag=S";
    } else if ("C".equals(approval)) {
        //msg = "이미 취소처리 되었습니다.";
        msg = "Đã được phê duyệt.";
        toUrl = "UM_RAJ_GovuA010s.jsp?recept=" + recept + "&g2bCode=" + masterCode + "&flag=S";
    }


    // 승인 또는 반려된 경우
    if (!"N".equals(approval)) {

%>

        <html>
        <head>
        <title>Message</title>
        <link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
		<link rel=stylesheet type=text/css href=../css/UM.css>
        <meta http-equiv=Content-Type content=text/html; charset=UTF-8>
        <script language=javascript>
           function toClose(){
               alert("<%= msg %>");
               location.href = "<%= toUrl %>";
               return;
           }
        </script>
        </head>
        <body bgcolor=#FFFFFF text=#000000 topmargin=0 leftmargin=0 marginwidth=0 marginheight=0>
          <script language=javascript>toClose()</script>
        </body>
        </html>


<%
    // 대기중인 경우
    } else  {

        UM_ADJ_GovuA020b ettLicense = null; 

        String LicenseGubun = ettcode.getlicense();      // 인증서신청여부 

        recept1    = ettcode.getlicenseCode();
        ettLicense = License.select_GovernmentList_Confirm(recept1); 
           
        // 사용_접수수요기관회계코드 테이블정보 
        UM_ADE_GovuA040b ett=ctl1.selectMath(recept); 
            
        boolean isMakedCode=false; 

        // 기관코드가 Z 로 시작하는 것은 신규등록시  
        // 프로그램에서 임의로 만들어낸 코드이다. 
        if( masterCode.substring(0,2).equals("ZD")  ||   masterCode.substring(0,2).equals("ZI") || 
            masterCode.substring(0,2).equals("ZP")  ||   masterCode.substring(0,2).equals("ZR") || 
            masterCode.substring(0,2).equals("ZC")  ||   masterCode.substring(0,2).equals("Z0")){ 
            isMakedCode=true; 
        }
        
        String zipName = ZipCode.getZipType(ettcode.getZIPCODE());
        String insType = ZipCode.getInsType(ettcode.getInsType());
        String dependType = ZipCode.getDependType(ettcode.getperOff());

        //Add by ThangLD@viettel.com.vn 27/07/2011
	UM_ADB_GroupDbDAO dbDao=new UM_ADB_GroupDbDAO();
	UM_ADE_GroupE010b[] listGroup = null;

	UM_ADB_DepartmentDbDAO depDAO = new UM_ADB_DepartmentDbDAO();
	UM_ADE_DepartmentE010b[] listDep = null;
        
%> 


<html> 
<head> 
<!-- <title>수요기관등록신청</title> --> 
<title>Sửa thông tin đăng ký bên mời thầu</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/TA.css"> 
  
</head> 
<Script src="../js/UM.js" language="Javascript"></SCRIPT> 
<Script src="../js/user.js" language="Javascript"></SCRIPT> 
<Script src="../js/tel_check.js" language="Javascript"></SCRIPT> 
 
<body>
<form name="fm" method="post"> 
<input type="hidden" name="flag" value="Insert"> 
<input type="hidden" name="g2bCodeGubun"   value="<%=g2bCodeGubun%>"> 
<input type="hidden" name="recept"   value="<%=recept%>"> 
<input type="hidden" name="XXXXXX" value="<%=XXXXXX%>"> 
<div class="col-750 clearfix last">
	<h1 class="pageTitle"><i class="icon-title"></i>Sửa thông tin đăng ký bên mời thầu</h1>


<!--------- G2B고도화 등록절차 추가 : include ----------> 
				<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
					<tr><td>
						<jsp:include page="/indexFlow1.jsp?gubun=1&req=1"/>
					</td></tr> 
				</table> 
<!------------------------------------------------------> 
 
<!---------------------- 일반정보 타이틀 테이블 시작 ------------------------>
				<br>
	         	<table width="100%" cellpadding="2" cellspacing="1">
					<tr>
						<!-- <td class="fontb" width="10%">[기관정보]</td> 
						<td width="90%" class="redr" align="right">사용자 설명서입니다. 읽어보신 후 나라장터 시스템 등록업무를 진행하시기 바랍니다. -> <a href="http://www.g2b.go.kr:8070/Reg_Manual.hwp">사용자등록설명서</a> 
						</td>-->
						<td class="fontb" width="100%">[Thông tin chung]</td> 
						<td width="90%" class="redr" align="right"> 
						</td>
					</tr>
	       	 	</table>
<!---------------------- 마스터정보 타이틀 테이블 끝 ------------------------>
		<table width="100%" border="0" cellspacing="1" cellpadding="2"
			class="tr">
			<tr>
				<td height="4" colspan="4" class="line"></td>
			</tr>
			<tr>
				<td class="tdar" width="20%" height="27"><font color="red">*</font>
				Mã cơ quan</td>
				<td class="tdb" height="27" colspan="3">
					<input type="text" name="g2bCode" class="read" readonly disabled value="<%=ettcode.getg2bCode()%>" size="20" maxlength="7" style="width: 150px">
					<input type="hidden" name="g2bCode" value="<%=ettcode.getg2bCode()%>" maxlength="7">
				</td>
			</tr>
			<tr>
				<td class="tdar" width="20%"><font color="red">*</font><!-- 기관명(전체)-->
				Tên cơ quan(đầy đủ)</td>
				<td class="tdb" colspan="3"><input type="text"
					name="goNameFull" onKeyUp="sc_check(this);" value="<%=ettcode.getgoNameFull() %>" class="read" size="70" maxlength="200">
					<input type="hidden" name="goNameFull" value="<%=ettcode.getgoNameFull()%>" maxlength="200">
				</td>
			</tr>
			<tr>
				<td class="tdar" width="20%">&nbsp;&nbsp;<!--기관명(약어)-->Tên cơ
				quan(viết tắt)</td>
				<td class="tdb" colspan="3"><input type="text"
					name="goNameShort" onKeyUp="sc_check(this);" value="<%= ettcode.getgoNameShort()==null?"":ettcode.getgoNameShort() %>" class="read" size="70" maxlength="200">
				</td>
			</tr>
			<tr>
				<td class="tdar" width="20%">&nbsp;&nbsp;<!-- 수요기관명(영문)-->
				Tên cơ quan(tiếng Anh)</td>
				<td class="tdb" colspan="3"><input type="text" name="goNameEn"
					onkeyup="onlyEng2(fm.goNameEn);" value="<%=ettcode.getgoNameEn()== null?"": ettcode.getgoNameEn()%>" class="read" size="70"
					maxlength="200"></td>
			</tr>
			<tr>
				<td class="tdar" width="20%"><font color="red">*</font><!-- 사업자등록번호-->Số ĐKKD</td>
				<td class="tdb" colspan="3">
					<input class="read" type="text"	name="saupNo" size="20" readonly disabled value="<%=ettcode.getsaupNo() %>" maxlength="7" style="width: 150px">
					<input type="hidden" name="saupNo" value="<%=ettcode.getsaupNo() %>" maxlength="13" >
				</td>
			</tr>
			<tr> 
				<td class="tdar" width="20%"><font color = "red">*</font><!-- 수요기관회계코드--> Mã số thuế</td>
				<td class="tdb"  width="30%" colspan="3">
					<input type="text" name="mathCode" value="<%=ett.getmathCode() %>" class="read" size="20" maxlength="13" style="width: 150px" >
            	</td>
			</tr>
			
			<tr><td class="tdar" width="20%">&nbsp;&nbsp;<!-- 소관구분-->
					Phân loại trực thuộc</td>
				<td   class="tdb" colspan="3">  
	           		<select name="permitBranch" style="width: 180;font-size: 13" id="select1">
	           			<option value='0'<%if (ZipCode.ZERO.equals(ettcode.getperOff())){%>selected<%}%>>Khác</option>
		            	<option value='1'<%if (ZipCode.ONE.equals(ettcode.getperOff())){%>selected<%}%>>Cơ quan Trung ương</option>
		               	<option value='2'<%if (ZipCode.TWO.equals(ettcode.getperOff())){%>selected<%}%>>Cơ quan Địa phương</option>
		               	<option value='3'<%if (ZipCode.THREE.equals(ettcode.getperOff())){%>selected<%}%>>Doanh nghiệp Nhà nước</option>
		               	<option value='4'<%if (ZipCode.FOUR.equals(ettcode.getperOff())){%>selected<%}%>>Doanh nghiệp Địa phương</option>
		               	<option value='5'<%if (ZipCode.FIVE.equals(ettcode.getperOff())){%>selected<%}%>>Tổ chức hành chính sự nghiệp</option>
		               	<option value='6'<%if (ZipCode.SIX.equals(ettcode.getperOff())){%>selected<%}%>>Tổ chức phi lợi nhuận</option>
	           		</select>       
           		</td>
           		</tr>
           		<tr style = "display:none">
           		<td class="tdar" width="20%" style = "display:none"><font color="red">*</font><!-- 기관유형-->
					Loại hình cơ quan</td>
				<td class="tdb" colspan="3" style = "display:none">
					<select name="insType" style="width: 100;font-size: 13" id="select2">
			         	<option value='0'<%if (ZipCode.ZERO.equals(ettcode.getInsType())){%>selected<%}%>>Lớn</option>
			         	<option value='1'<%if (ZipCode.ONE.equals(ettcode.getInsType())){%>selected<%}%>>Vừa</option>
			         	<option value='2'<%if (ZipCode.TWO.equals(ettcode.getInsType())){%>selected<%}%>>Nhỏ</option>
			       	</select>
				</td>
			</tr>
			<tr>
				<td width="20%" class="tdar"><font color="red">*</font><!-- 우편번호-->
				Tỉnh/Thành phố</td>
				<td width="30%" class="tdb" colspan="3">
			       	<%
						TreeMap provinces = nationalCtrl.getAll("GU7");
						Iterator pIter = provinces.keySet().iterator();
					%>
					<select name="postNo" style="width: 180">
						<%
						while (pIter.hasNext()) {
							String pName = pIter.next().toString();
							String pCode = provinces.get(pName).toString();				
						%>
							<option value="<%=pCode %>" <%if (pCode.equals(ettcode.getZIPCODE())){%>selected<%}%>><%=pName%></option>	
						<%
						} 
						%>		
					</select>
				</td>
			</tr>
                        <tr>
				<!--Add by ThangLD@viettel.com.vn-->
				<td width="20%" class="tdar">&nbsp;&nbsp;
				Tập đoàn/TCT</td>
				<td colspan="3" class="tdb">
			       	<%
			       		listGroup = dbDao.getAllList();
					%>
                                        <select name="groupNo" style="width: 270">
					        <option value='0' <%if ("0".equals(groupNo) || groupNo == null){%>selected<%}%>>---Chọn Tập đoàn/TCT---</option>
						<%
						if (null != listGroup && listGroup.length > 0)
						   for (int k = 0;k<listGroup.length;k++){
						   UM_ADE_GroupE010b ta=listGroup[k];
							String pName = ta.getGroup_name();
							int pCode = ta.getGroup_id();
						%>
							<option value="<%=pCode %>" <%if (pCode == ettcode.getGroupNo()){%>selected<%}%>><%=pName%></option>
						<%
						}
						%>
					</select>
				</td>
			</tr>	
			<tr>	
				<td width="20%" class="tdar">&nbsp;&nbsp;
				Bộ ban ngành</td>
				<td class="tdb" colspan="3">
			       	<%
			       		listDep = depDAO.getAllList();
					%>
					<select name="departmentNo" style="width: 270">
					        <option value='0' <%if ("0".equals(departmentNo) || departmentNo == null){%>selected<%}%>>---Chọn Bộ ban ngành---</option>
						<%
						if (null != listDep && listDep.length > 0)
						   for (int i = 0;i<listDep.length;i++){
						   UM_ADE_DepartmentE010b ta=listDep[i];
							String pName = ta.getName();
							int pCode = ta.getId();
						%>
							<option value="<%=pCode %>" <%if (pCode == ettcode.getDepId()){%>selected<%}%>><%=pName%></option>
						<%
						}
						%>
					</select>
				</td>
			</tr>
			<tr>
				<td class="tdar" width="20%"><font color="red">*</font><!-- 주소-->
					Địa chỉ</td>
				<td class="tdb" colspan="3"><input type="text" name="ADDR"
					value="<%=ettcode.getADDR() %>" onKeyUp="sc_check(this);" class="read" size="70" maxlength="200"> </td>
			</tr>
			<tr>
				<td class="tdar" width="20%"><font color="red">*</font><!-- 대표 전화번호-->
					Số điện thoại</td>
				<td class="tdb"  width="30%">
					<input onkeypress="return isNumberKeyTel(event)" type="text" name="comTel" class="read" size="30" maxlength="20" value="<%=ettcode.gettelNum() %>" onblur="js_numberCheck(this)">
				</td>
				<td class="tdb" style="width: 328px" colspan="3">  Có thể nhập mã vùng, số máy lẻ hoặc nhiều hơn một  số điện thoại. Ví dụ: <i>(04)874612 x45; (08)3511127</i></td>
			</tr>
			<tr>	
				<td class="tdar">&nbsp;&nbsp;<!-- 대표 팩스번호--> Số
					Fax</td>
				<td class="tdb"  width="30%" colspan="3">
					<input onkeypress="return isNumberKeyTel(event)" type="text" name="comFax" class="read" size="30" maxlength="20" value="<%=ettcode.getfaxNum()==null? "":ettcode.getfaxNum() %>" onblur="js_numberCheck(this)">
				</td>
			</tr>
			
			<tr>
				<td class="tdar" width="20%">&nbsp;&nbsp;<!--홈페이지-->Trang web</td>
				<td class="tdb" colspan="3"><input type="text" name="homepage"
					value="<%=(ettcode.gethomepage()==null?"":ettcode.gethomepage()) %>" class="read" size="50" maxlength="40"></td>
			</tr>
			<!--
			<tr>
				<td class="tdar" width="20%"><font color="red">*</font> 
				Tên người phụ trách hàng hóa</td>
				<td class="tdb" colspan="3"><input type="text"
					name="creditName" onKeyUp="sc_check(this);" value="<%=ettcode.getgoodsMaster() %>" class="read" size="50" maxlength="24">
				</td>
			</tr>		
			-->
			<tr style = "display:none">
				<td class="tdar" width="20%" height="30">&nbsp;&nbsp;<!--업태-->Hình
				thái kinh doanh</td>
				<td class="tdb" width="30%" height="30"><input type="text"
					name="buConditon" value="<%=(ettcode.getbuConditon()==null?"":ettcode.getbuConditon()) %>" class="read" size="30" maxlength="150"></td>
				<td class="tdar" width="20%" height="30">&nbsp;&nbsp;<!--업종-->
				Ngành nghề</td>
				<td class="tdb" width="30%" height="30"><input type="text"
					name="buType" value="<%=(ettcode.getbuType()==null?"":ettcode.getbuType()) %>" class="read" size="30" maxlength="150">
				</td>
			</tr>
			
			<tr>				
				<td height="2" colspan="4" class="line"></td>
			</tr>
		</table>

		<br> 
			    <table width="100%" cellpadding="2" cellspacing="1"> 
					<tr> 
						<td class="fontb"><!--[담당자정보]-->[Thông tin người được giao phụ trách Bên Mời Thầu]</td> 
					</tr> 
			     </table> 
 
			    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
			          <tr> 
			            <td height="4" colspan="4" class="line"></td> 
			          </tr> 
			          <tr> 
						<td class="tdar" width="20%"><font color = "red">*</font><!-- 담당자명--> 
							Người phụ trách</td> 
						<td class="tdb" width="30%"> 
							<input type="text" onKeyUp="sc_check(this);" name="taskmaster" value="<%=ettcode.gettaskmaster() %>" class="read" size="30" maxlength=150>
							&nbsp; 
						</td> 
						<td class="tdar" width="20%" ><font color = "red">*</font><!-- 담당자 주민번호--> 
							Số CMND</td> 
						<td class="tdb"  width="30%"> 
							<input  type="text" name="idCharge" class="read" size="30" maxlength="25" value="<%=ettcode.getcreditName() %>" onblur="js_numberCheck(this)" >   <!-- 20070621 변경 onkeydown="js_OnlyNumber(this)" -> onblur="js_numberCheck(this)" -->
							&nbsp;
						</td> 
			        </tr>
			        <tr> 
						<td class="tdar" width="20%"><font color = "red">*</font><!-- 담당자 부서명-->
							Phòng/Ban</td> 
						<td class="tdb" width="30%"> 
							<input type="text" name="masterPost" value="<%=ettcode.getmasterPost() %>" class="read" size="30" maxlength=20> 
							&nbsp;
						</td> 
						<td class="tdar" width="20%"><font color = "red">*</font><!-- 담당자 전화번호--> 
							Số điện thoại</td>
						<td class="tdb"  width="30%"> 
							<input onkeypress="return isNumberKeyTel(event)" type="text" name="personTel" class="read" size="30" maxlength="20" value="<%=ettcode.getmasterTel() %>" onblur="js_numberCheck(this)" >   <!-- 20070621 변경 onkeydown="js_OnlyNumber(this)" -> onblur="js_numberCheck(this)" -->
							&nbsp;
						</td> 
					</tr> 
					<tr> 
						<td class="tdar" width="20%">&nbsp;&nbsp;<!-- 담당자 팩스번호--> 
							Số Fax</td> 
						<td class="tdb"  width="30%"> 
							<input type="text" onkeypress="return isNumberKeyTel(event)" name="personFax" value="<%=ettcode.getmasterFax()==null?"":ettcode.getmasterFax()%>" class="read" size="30" maxlength=20>
							&nbsp;
						</td>
			 			<td class="tdar" width="20%"><!--담당자 핸드폰번호--><font color = "red">*</font>Số di động</td> 
			 			<td class="tdb"  width="30%"> 
							<input  type="text" onkeypress="return isNumberKey(event)" name="personPhone" class="read" size="30" maxlength="20" value="<%=(ettcode.getmasterhp()==null?"":ettcode.getmasterhp()) %>" onblur="js_numberCheck(this)" >   <!-- 20070621 변경 onkeydown="js_OnlyNumber(this)" -> onblur="js_numberCheck(this)" -->
							&nbsp;
						</td>
			
					</tr> 
					<tr> 
			            <td class="tdar" width="20%"><font color = "red">*</font><!-- 담당자 메일주소--> Địa chỉ email</td> 
						<td class="tdb" colspan=3> 
							<input type="text" name="masterMail" value="<%=ettcode.getmasterMail() %>" class="read" size="50" maxlength=30> 
						</td> 
					</tr> 
			        <tr> 
			            <td height="2" colspan="4" class="line"></td> 
			        </tr> 
				</table> 
				<br> 
				 
				<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
		            <tr> 
						<td class="fontb" width="20%" colspan="3">[Thông tin đăng ký chứng thư số]</td>
			        </tr>
					
		            <tr> 
			            <td height="4" colspan="4" class="line"></td> 
			        </tr>  
			        <tr> 
						<td class="tdar" width="20%"><font color = "red">*</font><!-- 대표--> Người đại diện pháp luật</td> 
						<td class="tdb"  width="30%"> 
							<input type="text" onKeyUp="sc_check(this);" name="IDENT" value="<%=ettLicense.getIDENT() %>" class="read" size="30" maxlength=50>
							&nbsp; 
						</td> 
						<td class="tdar" width="20%"><font color = "red">*</font><!-- 대표자주민번호--> 
							Số CMND</td>
							&nbsp;
						<td class="tdb"  width="30%"> 
							<input  type="text" name="registno1" class="read" size="30" maxlength="25" value="<%=ettLicense.getIDENTJUMIN() %>" > 
						</td> 
					</tr>									
			        <tr style = "display:none"> 
						<td class="tdar" width="20%"><font color = "red">*</font><!-- 인증서요청ID--> Mã yêu cầu cấp chứng thư số</td>
						<td class="tdb"  colspan="3"> 
							<input type="text" name="USRID" value="<%=ettLicense.getUSRID() %>" class="read" size="30" onkeyup="onlyEng(fm.USRID);" maxlength=8><font color = "red"> <!-- * 나라장터 시스템과 관련이 없으며 인증기관에서 필요로 하는 사용자ID(영문8자) -->
							 * Mã cần cho cơ quan cấp chứng thư số, không liên quan tới hệ thống đấu thầu (8 ký tự) </font>
						</td> 
					</tr> 
				    <tr> 
			            <td height="2" colspan="4" class="line"></td> 
			        </tr>
				</table> 
				<br> 
				
				<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
					<input  type="radio" checked="true" name="choiceCA" class="read" value="5" style="display:none;" >
					<tr> 
						<td class="fontb" colspan="3">[Cơ quan cấp phát và quản lý chứng thư số]</td>
					</tr>
					<tr>		 
			            <td height="4" colspan="4" class="line"></td> 
			        
		            </tr>
					<tr>
						<td class="tdar" width="20%">Tên cơ quan</td> 
						<td class="tdb"  colspan="3"> Cục Quản lý Đấu thầu - Bộ Kế hoạch và Đầu tư </td>
					</tr>
					<tr>		 
			            <td height="2" colspan="4" class="line"></td> 
			        
		            </tr> 
				</table>
				
				<br>
				
				<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
					<tr> 
						<td align="center" height="24"> 
			                <input type="button" class="commonbutton" value="Sửa đăng ký bên mời thầu" onclick="javascript:js_submit();">
							&nbsp;
							<input type="button" class="commonbutton" value="Bỏ qua" onclick="javascript:history.back();">
			            </td> 
					</tr> 
				</table> 
	      
	<div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
	</div>
</form> 

<!-- 실명인증 관련 -->
<form name="nameCheck" method="post"> 
<input type="hidden" name="username"   value=""> 
<input type="hidden" name="jumin"      value=""> 
<input type="hidden" name="elm_name"   value=""> 
</form>
<SCRIPT LANGUAGE = 'Javascript'>
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function onlyEng(objtext1) {
	var inText = objtext1.value;
	var ret;
	for (var i = 0; i < inText.length; i++) {
		ret = inText.charCodeAt(i);

		if ((ret > 122) || (ret < 48) || (ret > 57 && ret < 65) || (ret > 90 && ret < 97)) { // 한글,특수문자 허용않음  //does not allow special characters
			//alert("영문자와 숫자만을 입력하세요\n\n한글과 특수문자는 입력할 수 없습니다.");
			alert("Chỉ nhập số và chữ  Latin. Không nhập tiếng Việt và kí tự đặc biệt.");
			objtext1.value = "";
			objtext1.focus();
			return false;
		}
	}
	return true;
}
// Add by thanhtv 2010.04.30
// Ham kiem tra nhap cac ky tu dac biet
function numberTelCheck(str)
{
    var src = new String(str);
    var tar = true;
    var i, len=src.length;
    for (i=0; i < len; i++) {
        if(src.charAt(i)==")" || src.charAt(i)=="(") return true; 
        if(src.charAt(i)==";" || src.charAt(i)=="x" || src.charAt(i)=="X") return true;
        if ((src.charAt(i) < '0') || (src.charAt(i) > '9'))
            return false;
    }
    return true;	
}

function isNumberKeyTel(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if(charCode==41 || charCode==40) return true; 	
   if(charCode==59 || charCode==32 ) return true;
   if(charCode==88 || charCode==120) return true;
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}
// The End

 
<%--	//수요기관 코드생성 --%> 
    function regist2_click() { 
 
		 newWindow = window.open("../jsp/AD/UM_ADJ_GovrH010l.jsp?code=","newWin","top=10,left=10,width=500,height=250,scrollbars=no, resizable=no,location=0,directories=0,status=0,menubar=0"); 
         newWindow.focus(); 
		} 
 
// 외국인대표인 경우 대표자주민번호 채번 (2007.06.29) // the number of representatives
var ceo_id_inx = 000000000001;

    // 필수항목 유효성 체크 변경	(2007.06.29) // Check the validity changes required
	function js_submit() { 
 // comment by thantv 2010.04.30
 /*
		if(document.forms[0].goNameEn.value == ''){ 
            //alert('수요기관명(영문)은 필수입력항목 입니다.'); 
            alert('Tên cơ quan (tiếng Anh) là hạng mục cần nhập.'); 
            document.forms[0].goNameEn.select(); 
            document.forms[0].goNameEn.focus(); 
            return; 
        } 
*/
		
        if(document.forms[0].goNameEn.value.length > 70){ 
            //alert('수요기관 영문명은 항목의 길이가 초과하였습니다.'); 
            alert('Tên cơ quan tiếng Anh nhập quá dài.'); 
            document.forms[0].goNameEn.select(); 
            document.forms[0].goNameEn.focus(); 
            return; 
        } 

		if (!js_numberCheck(document.forms[0].mathCode.value)) {
			 alert('Hãy nhập chính xác mã số thuế chỉ gồm các ký tự số.'); 
		    document.forms[0].mathCode.select(); 
			 document.forms[0].mathCode.focus(); 
		    return;
		}
		             
        if(document.forms[0].ADDR.value =='') { 
            //alert('주소는 필수입력항목 입니다.'); 
            alert('Địa chỉ là hạng mục phải nhập.'); 
            document.forms[0].ADDR.select(); 
            document.forms[0].ADDR.focus(); 
            return; 
        } 

        if(document.forms[0].ADDR.value.length > 200) { 
            //alert('나머지주소 항목의 길이가 초과하였습니다.[MAX 24 byte]'); 
            alert('Địa chỉ nhập quá dài.'); 
            document.forms[0].ADDR.select(); 
            document.forms[0].ADDR.focus(); 
            return; 
        }

        if (document.forms[0].comTel.value == ""){
        	alert('Số điện thoại là hạng mục cần nhập.'); 
            document.forms[0].comTel.select(); 
            document.forms[0].comTel.focus(); 
            return; 
        } else if (document.forms[0].comTel.value.length > 25) { 
            alert('Số điện thoại nhập quá dài.'); 
            document.forms[0].comTel.select(); 
            document.forms[0].comTel.focus(); 
            return; 
        }
                
                     
// Add by thanhtv 2010.04.30        
        if (!numberTelCheck(document.forms[0].comTel.value)) {
       	 	alert('Hãy nhập chính xác số điện thoại chỉ gồm các ký tự số, dấu ;, chữ x và ngoặc.'); 
       	 	document.forms[0].comTel.select(); 
       		document.forms[0].comTel.focus(); 
            return;
        }
        
/* Comment by thanhtv 2010.04.30        
        if (!js_numberCheck(document.forms[0].comTel.value)) {
       	 alert('Hãy nhập chính xác số số điện thoại chỉ gồm các ký tự số.'); 
            document.forms[0].comTel.select(); 
			 document.forms[0].comTel.focus(); 
            return;
        }
        
        if (document.forms[0].comFax.value == ""){            
        	alert('Số fax là hạng mục phải nhập.'); 
            document.forms[0].comFax.select(); 
            document.forms[0].comFax.focus();
             
            return; 
        } else if (document.forms[0].comFax.value.length > 25) { 
            alert('Số Fax nhập quá dài.'); 
            document.forms[0].comFax.select(); 
            document.forms[0].comFax.focus(); 
            return; 
        }
        */ 
		// Add by thanhtv 2010.04.30
		if (document.forms[0].comFax.value.length > 25)
		{ 
	        alert('Số Fax nhập quá dài.'); 
	        document.forms[0].comFax.select(); 
	        document.forms[0].comFax.focus(); 
	        return; 
	    }
		if (!numberTelCheck(document.forms[0].comFax.value)) {
			 	alert('Hãy nhập chính xác số Fax chỉ gồm các ký tự số, dấu ;, chữ x và ngoặc.'); 
			 	document.forms[0].comFax.select(); 
				document.forms[0].comFax.focus(); 
		    return;
		}


		
/* Comment by thanhtv 2010.04.30
        if (!js_numberCheck(document.forms[0].comFax.value)) {
          	 alert('Hãy nhập chính xác số số fax chỉ gồm các ký tự số.'); 
               document.forms[0].comFax.select(); 
   			 document.forms[0].comFax.focus(); 
               return;
        }
*/        
/* Comment by thanhtv 2010.04.30
        if (document.forms[0].buConditon.value.length > 50) { 
            alert('Hình thái kinh doanh nhập quá dài.'); 
            document.forms[0].buConditon.select(); 
            document.forms[0].buConditon.focus(); 
            return; 
        }

        if (document.forms[0].buType.value.length > 50) { 
            alert('Ngành nghề nhập quá dài.'); 
            document.forms[0].buType.select(); 
            document.forms[0].buType.focus(); 
            return; 
        }
*/ 
        if (document.forms[0].homepage.value.length > 150) 
        { 
            alert('Trang web nhập quá dài.'); 
            document.forms[0].homepage.select(); 
            document.forms[0].homepage.focus(); 
            return; 
        }
        
        if(document.forms[0].mathCode.value =='') { 
            //alert('주소는 필수입력항목 입니다.'); 
            alert('Mã số thuế là hạng mục phải nhập.');
            document.forms[0].mathCode.select(); 
            document.forms[0].mathCode.focus(); 
             return; 
        }  
        if(document.forms[0].mathCode.value.length > 0 && (document.forms[0].mathCode.value.length != 10 &&
                document.forms[0].mathCode.value.length != 13)){ 
            //alert('사업자등록번호 10자리를 정확히 입력하십시요.'); 
            alert('Hãy nhập chính xác mã số thuế gồm 10 hoặc 13 kí tự.'); 
            document.forms[0].mathCode.select(); 
			document.forms[0].mathCode.focus(); 
            return; 
        } 

        
        
        if(document.forms[0].taskmaster.value =='') { 
            alert('Tên người phụ trách là hạng mục phải nhập.'); 
            document.forms[0].taskmaster.select(); 
            document.forms[0].taskmaster.focus(); 
            return; 
        } else if (document.forms[0].taskmaster.value.length > 150) { 
            alert('Tên người phụ trách nhập quá dài.'); 
            document.forms[0].taskmaster.select(); 
            document.forms[0].taskmaster.focus(); 
            return; 
        }
        
        if(document.forms[0].idCharge.value =='') { 
            alert('Số CMND người phụ trách là hạng mục phải nhập.'); 
            document.forms[0].idCharge.select(); 
            document.forms[0].idCharge.focus(); 
            return; 
        } 
        /*
        else if(document.forms[0].idCharge.value.length != 9) { 
            alert('Số CMND người phụ trách phải đủ 9 ký tự.'); 
            document.forms[0].idCharge.select(); 
            document.forms[0].idCharge.focus(); 
            return; 
        }
        */
		/*
        if (!js_numberCheck(document.forms[0].idCharge.value)) {
        	 alert('Hãy nhập chính xác số CMND người phụ trách chỉ gồm các ký tự số.'); 
             document.forms[0].idCharge.select(); 
 			 document.forms[0].idCharge.focus(); 
             return;
        }
        */
        
        if(document.forms[0].masterPost.value =='') { 
            alert('Phòng/Ban người phụ trách là hạng mục phải nhập.'); 
            document.forms[0].masterPost.select(); 
            document.forms[0].masterPost.focus(); 
            return; 
        } else if (document.forms[0].masterPost.value.length > 150) { 
            alert('Phòng/Ban người phụ trách nhập quá dài.'); 
            document.forms[0].masterPost.select(); 
            document.forms[0].masterPost.focus(); 
            return; 
        }
        
        if(document.forms[0].personTel.value =='') { 
            alert('Số điện thoại người phụ trách là hạng mục phải nhập.'); 
            document.forms[0].personTel.select(); 
            document.forms[0].personTel.focus(); 
            return; 
        } else if (document.forms[0].personTel.value.length > 25) { 
            alert('Số điện thoại người phụ trách nhập quá dài.'); 
            document.forms[0].personTel.select(); 
            document.forms[0].personTel.focus(); 
            return; 
        }
        
// Add by thanhtv 2010.04.30
        if (!numberTelCheck(document.forms[0].personTel.value)) {
       	 	alert('Hãy nhập chính xác số điện thoại người phụ trách, chỉ gồm các ký tự số, dấu ;, chữ x và ngoặc.'); 
            document.forms[0].personTel.select(); 
			document.forms[0].personTel.focus(); 
            return;
        }
// Add by TuanNA80 2016.06.17  
        if (document.forms[0].personPhone.value == ""){
            alert('Số di động người phụ trách là hạng mục cần nhập.');
            document.forms[0].personPhone.select();
            document.forms[0].personPhone.focus();
            return;
        } else if (document.forms[0].comTel.value.length > 25) { 
            alert('Số di động người phụ trách nhập quá dài.'); 
            document.forms[0].personPhone.select(); 
            document.forms[0].personPhone.focus(); 
            return; 
        }
        if (!numberTelCheck(document.forms[0].personPhone.value)) {
       	 	alert('Hãy nhập chính xác ố di động người phụ trách, chỉ gồm các ký tự số, dấu ;, chữ x và ngoặc.'); 
            document.forms[0].personPhone.select(); 
			document.forms[0].personPhone.focus(); 
            return;
        }
        
/* Comment by thanhtv 2010.04.30
 
        if (!js_numberCheck(document.forms[0].personTel.value)) {
        	 alert('Hãy nhập chính xác số điện thoại người phụ trách chỉ gồm các ký tự số.'); 
             document.forms[0].personTel.select(); 
 			 document.forms[0].personTel.focus(); 
             return;
        }
*/
        if (!js_numberCheck(document.forms[0].personPhone.value)) {
        	 alert('Hãy nhập chính xác số di động người phụ trách chỉ gồm các ký tự số.'); 
             document.forms[0].personPhone.select(); 
 			 document.forms[0].personPhone.focus(); 
             return;
        }
        
        if (document.forms[0].personFax.value.length > 25) { 
            alert('Số Fax người phụ trách nhập quá dài.'); 
            document.forms[0].personFax.select(); 
            document.forms[0].personFax.focus(); 
            return; 
        }
// add by thanhtv 2010.04.30
		if (!numberTelCheck(document.forms[0].personFax.value)) {
       	 	alert('Hãy nhập chính xác số Fax người phụ trách, chỉ gồm các ký tự số, dấu ;, chữ x và ngoặc.'); 
            document.forms[0].personFax.select(); 
			document.forms[0].personFax.focus(); 
            return;
        }
/* Comment by thanhtv 2010.04.30
        if (!js_numberCheck(document.forms[0].personFax.value)) {
        	 alert('Hãy nhập chính xác số Fax người phụ trách chỉ gồm các ký tự số.'); 
             document.forms[0].personFax.select(); 
 			 document.forms[0].personFax.focus(); 
             return;
        }
*/        
        if(document.forms[0].masterMail.value =='') { 
            alert('Địa chỉ mail người phụ trách là hạng mục phải nhập.'); 
            document.forms[0].masterMail.select(); 
            document.forms[0].masterMail.focus(); 
            return; 
        }
        if (!checkEmail('masterMail')) {
			return;
        }
        
        if(document.forms[0].IDENT.value =='') { 
            alert('Tên người đại diện là hạng mục phải nhập.'); 
            document.forms[0].IDENT.select(); 
            document.forms[0].IDENT.focus(); 
            return; 
        } else if (document.forms[0].IDENT.value.length > 150) { 
            alert('Tên người đại diện nhập quá dài.'); 
            document.forms[0].IDENT.select(); 
            document.forms[0].IDENT.focus(); 
            return; 
        }
        
        if(document.forms[0].registno1.value =='') { 
            alert('Số CMND người đại diện là hạng mục phải nhập.'); 
            document.forms[0].registno1.select(); 
            document.forms[0].registno1.focus(); 
            return; 
        } 
        /*
        else if(document.forms[0].registno1.value.length != 9) { 
            alert('Số CMND người đại diện phải đủ 9 ký tự.'); 
            document.forms[0].registno1.select(); 
            document.forms[0].registno1.focus(); 
            return; 
        }
        

        if (!js_numberCheck(document.forms[0].registno1.value)) {
        	 alert('Hãy nhập chính xác số CMND người đại diện chỉ gồm các ký tự số.'); 
             document.forms[0].registno1.select(); 
 			 document.forms[0].registno1.focus(); 
             return;
        }
        */
        if(document.forms[0].USRID.value =='') { 
            alert('Mã yêu cầu chứng thư số là hạng mục phải nhập.');
            document.forms[0].USRID.select(); 
            document.forms[0].USRID.focus(); 
            return; 
        } else if(document.forms[0].USRID.value.length != 8) { 
            alert('Mã yêu cầu chứng thư số phải đủ 8 ký tự.');
            document.forms[0].USRID.select(); 
            document.forms[0].USRID.focus(); 
            return; 
        }
        
        //if (confirm("수요기관 기본정보를 저장하시겠습니까?")){ 
       	if (confirm("Lưu thông tin cơ bản của bên mời thầu?")) { 
       		document.forms[0].action="/servlet/servlets/UM_ADJ_GovrB050c";
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

    function onlyEng2(objtext1) {
    	var inText = objtext1.value;
    	var ret;
    	for (var i = 0; i < inText.length; i++) {
    		ret = inText.charCodeAt(i);

    		if ((ret > 122) || (ret < 48 && ret != 32) || (ret > 57 && ret < 65) || (ret > 90 && ret < 97)) { // 한글,특수문자 허용않음  //does not allow special characters
    			//alert("영문자와 숫자만을 입력하세요\n\n한글과 특수문자는 입력할 수 없습니다.");
    			alert("Chỉ nhập số và chữ  Latin. Không nhập tiếng Việt và kí tự đặc biệt.");
    			objtext1.select();
    			objtext1.focus();
    			return false;
    		}
    	}
    	return true;
    }
    	
    function opener_reload(code) 
	{ 
		fm.g2bCode.value=code; 
 
		document.forms[0].action='./UM_RAJ_GovuA010i.jsp'; 
		document.forms[0].target='_self'; 
		document.forms[0].submit(); 
	} 
 
<%--    //이전화면으로 이동 스크립트  // Do you want to save the basic information of institutions--%> 
    function back() { 
			history.back(); 
		} 
 
 
<%--    // 수요기관코드 검색  // Demand Organization Code Search--%> 
    function SearchUser(){ 
        newwin = window.open("../jsp/AD/UM_ADJ_GovrC021l_NEW.jsp?code=&codeName=&baseCode=g2bCode&baseName=goNameFull&formName=fm&type=1","codeSearch","width=500,height=550, scrollbars=yes"); 
        newwin.focus(); 
    } 
 
    function fAddrFind(code, addr, gus) 
	{ 
		fm.ZIPCODE.value = code; 
	 	fm.ADDR.value = addr; 
		fm.ADDR1.value = addr; 
 	} 
 
    function fAddrFindCode(code) 
	{ 
        fm.g2bCode.value = code; 
	} 
 
    function fCodeFind(mathCode,a,b,c) 
	{ 
	   fm.mathCode.value = mathCode; 
	} 
 
    function clear() { 
		document.forms[0].g2bCode.value=""; 
		document.forms[0].goNameFull.value=""; 
		document.forms[0].goNameShort.value=""; 
		document.forms[0].goNameEn.value=""; 
		document.forms[0].saupNo.value=""; 
		document.forms[0].buConditon.value=""; 
		document.forms[0].buType.value=""; 
		document.forms[0].taskmaster.value=""; 
		document.forms[0].masterPost.value=""; 
		document.forms[0].comTel.value=""; 
		document.forms[0].comFax.value=""; 
		document.forms[0].masterMail.value=""; 
		document.forms[0].creditName.value=""; 
		document.forms[0].postno.value=""; 
		document.forms[0].ADDR1.value=""; 
		document.forms[0].address2.value=""; 
		document.forms[0].personTel.value=""; 
		document.forms[0].personFax.value=""; 
		document.forms[0].personPhone.value="";
		document.forms[0].g2bCode.select(); 
		document.forms[0].g2bCode.focus(); 
	} 
 
    function jsf_onclick(vv) 
    { 
        if(vv == 'Y') 
        { 
            document.all.append.style.display = ""; 
        } 
        else 
        { 
            document.all.append.style.display ="none"; 
        } 
    } 
 
    function sc_check(val) { 
 
        var mikExp = /[$\\@\\\#%\^\&\*\{\}\`\'\|\:\;]/; 
        var strPass = val.value; 
        var strLength = strPass.length; 
        var lchar = val.value.charAt((strLength) - 1); 
        if(lchar.search(mikExp) != -1) { 
            var tst = val.value.substring(0, (strLength) - 1); 
            //alert ('제한되어 있는 특수 문자는 다음과 같으며\n' 
            //      +' $ \ @ # % ^ & * ` ~ | : ; \n' 
            //      +' 특수문자는 사용할수 없습니다.'); 
            alert ('Không thể sử dụng các ký tự đặc biệt\n như sau : $ \ @ # % ^ & * ` ~ | : ;'); 
              val.value = tst; 
        } 
    } 

</Script> 
</body> 
</html> 

<%
    }
%>
