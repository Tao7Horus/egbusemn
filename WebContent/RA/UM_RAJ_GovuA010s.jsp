<%-- 
/********************************************************************************/ 
/* 1.시 스 템 명 : 전자조달-사용자등록                                          */ 
/* 2.프로그램 ID : UM_RAJ_GovuA010s.jsp                                         */ 
/* 3.프로그램 명 : 수요기관등록신청현황조회화면                                 */ 
/* 4.작  성   일 : 2003-05-23                                                   */ 
/* 5.작  성   자 : 모름                                                         */ 
/* 6.개       요 : 수요기관등록신청현황 조회                                    */ 
/********************************************************************************/ 
 
    2003.05.23  김영진  1.대기, 반려인 경우에 수요기관등록신청서 출력물 출력가능토록 
                        수정 
    2007.04.30 임지훈 1. 보류 중일때 해당 화면 조회추가

/***************************************************************************/
/* Program ID         : UM_RAJ_GovuA010s.jsp                        */
/* Program Explanation: Tra cứu tình trạng tiến hành đăng ký bên mời thầu				*/
/* Program Summary  : 
/* Relation Program   : 
/* Table              : UM_REC_PUB_INSTITU_MAST							*/
/***************************************************************************/
/* Customizing Composer : MR. MINH 16.05.2009                      	*/
/***************************************************************************/
--%> 
 
<%@page language="java" %> 
<%@page contentType="text/html; charset=UTF-8" %> 
<%@page errorPage="../jsp/common/jspToError.jsp?type=0&url=&message=" %> 
<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, common.*" %> 
<jsp:useBean id="ctl" class="beans.UM_URB_GovuA020c" scope="page" /> 
<% 
 
	com.oreilly.servlet.ParameterParser psr = new com.oreilly.servlet.ParameterParser(request); 
 
    String recept   = psr.getStringParameter("recept",      ""); 
    String g2bCode  = psr.getStringParameter("g2bCode",     ""); 
    String flag     = psr.getStringParameter("flag",        ""); 
 
    UM_ADV_GovuA030b ett  = new UM_ADV_GovuA030b();
    int  maxcnt=0;
    if ((recept != null && g2bCode != null) || (recept != "" && g2bCode != "")) {
	    maxcnt = ctl.approval_count(recept,g2bCode); 
	    ett = ctl.approvalYN(recept, g2bCode);
	}
    
    
%> 
<html> 
<head> 
<!-- <title>수요기관 등록 진행 현황 조회</title> -->

<title>Trạng thái tiến hành đăng ký Bên Mời Thầu</title> 
<link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/TA.css"> 


</head> 
<SCRIPT LANGUAGE="JavaScript"> 
 
    function js_submit(){ 
 
        if(document.forms[0].recept.value==null || document.forms[0].recept.value==""){ 
            //alert("접수번호 입력 바랍니다.");
            alert("Hãy nhập số  tiếp nhận."); 
            document.forms[0].recept.select();
            document.forms[0].recept.focus();
            return; 
        } 
 
        if(document.forms[0].g2bCode.value==null || document.forms[0].g2bCode.value==""){ 
        	//alert("접수번호 입력 바랍니다."); 
        	alert("Hãy nhập mã cơ quan.");
        	document.forms[0].g2bCode.select();
        	document.forms[0].g2bCode.focus();
            return; 
        } 
 
        document.forms[0].flag.value = "S" 
        document.forms[0].target="_self"; 
        document.forms[0].method="post"; 
        document.forms[0].action="UM_RAJ_GovuA010s.jsp"; 
        document.forms[0].submit(); 
        return; 
    } 

    function cancel(getProcessState) {

       if (getProcessState=="N" ){

           //if (confirm("수요기관등록신청 정보가 모두 삭제됩니다.\n수요기관등록 신청을 취소하시겠습니까?")) {
       	   if (confirm("Bạn có muốn hủy đăng ký Bên Mời Thầu này không?")) {
                       document.go.actFlag.value = "C";

               document.go.action = "/servlet/servlets.UM_RAV_GovuA011c";
               document.go.method = "post"; 
               document.go.target = "_self";
               document.go.submit();
           }
       } else { 
    	   //alert("접수번호 입력 바랍니다."); 
    	   alert("Hãy nhập số  tiếp nhận."); 
           return;
       }
    }

    function updateRequest(getProcessState, departmentNo, groupNo) {        
       if (getProcessState=="N"){

           document.go.actFlag.value = "U";

           location.href = "UM_RAJ_GovuA010u.jsp?recept=" + document.forms[0].recept.value + "&departmentNo=" + departmentNo + "&groupNo=" + groupNo;

       } else { 
    	   //alert("접수번호 입력 바랍니다."); 
    	   alert("Hãy nhập số  tiếp nhận."); 
           return;
       }
    }
    function updateRequest2() {        
            //sdocument.go.actFlag.value = "U";
            location.href = "UM_RAJ_GovuA010y.jsp?g2bCode=<%=g2bCode%>";
     }

    <% if( maxcnt > 0){ %> 
    function js_print(){ 
        var regUrl="UM_RAJ_Report.jsp?code=<%=recept%>" 
        window.open(regUrl,'regSuyo','toolbar=yes,location=no,status=no,menubar=yes,scrollbars=yes,resizable=no,width=750,height=700'); 
    } 

    <%  } %> 
</Script> 

<body>

<div class="col-750 clearfix last">
	<h1 class="pageTitle"><i class="icon-title"></i>Trạng thái tiến hành đăng ký Bên Mời Thầu</h1>
 
<form name="go"> 
<input type="hidden" name="flag"    value=""> 
<input type="hidden" name="actFlag" value=""> 

 
<!--------- G2B고도화 등록절차 추가 : include ----------> 
<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
    <tr><td>
    	
    	<!-- Add by thanhtv 2010.04.29 -->
    	<jsp:include page="/indexFlow1.jsp?gubun=1&req=2"/>
    	<!-- The End -->
    </td></tr> 
</table> 
<p> 
<!------------------------------------------------------> 
 
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr" style = "display:none"> 
    <tr><td height="4" colspan="4" class="line"></td></tr> 
</table> 
<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr" style = "display:none"> 
    <tr> 
        <td width="20%" class='tdar'><!--접수번호-->Số tiếp nhận</td> 
        <td class='tdb' width="30%"> 
            <input type="text" name="recept" size="20" value="<%=recept %>" maxlength="10"> 
        </td> 
        <td width="20%" class='tdar'><!--수요기관코드-->Mã cơ quan</td> 
        <td class='tdb' width="30%" > 
            <input type="text" name="g2bCode" size="10" value="<%=g2bCode %>" maxlength="7"> 
            <input type="button" class="commonbutton" value="Tìm kiếm" onclick="javascript:js_submit();">
        </td> 
    </tr> 
    <tr><td height="2" colspan="4" class="line"></td></tr> 
 </table> 
</form> 
<% if(flag.equals("")) { %> 
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr> 
            <td class="tdb" > 
                <font color = "red"> 
                    ▷ <!--접수번호와 수요기관코드를 정확히 입력하시기 바랍니다-->Hãy nhập chính xác Số tiếp nhận và Mã cơ quan. 
                </font> 
            </td> 
        </tr> 
    </table> 
<% } %> 
<% 
    if(flag.equals("S")) { 
        if (maxcnt == 0) { 
%> 
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr> 
            <td class="tdb" > 
                <font color = "red"> 
                    ▷ <!--수요기관 등록신청을 하지 않으셨거나 접수번호와 수요기관코드가 틀립니다-->Chưa đăng ký bên mời thầu hoặc mã cơ quan và số tiếp nhận bị sai.<BR> 
                    ▷ <!--접수번호와 수요기관코드를 정확히 입력하시기 바랍니다-->Hãy nhập chính xác mã cơ quan và số tiếp nhận. 
                </font> 
            </td> 
        </tr> 
    </table> 
<% 
        } else { 
 
 
%> 
<br> 
        <% if("Y".equals(ett.getenYn())){   %> 
 
 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr" style = "display:none"> 
                    <tr> 
                        <td height="4" colspan="4" class="line"></td> 
                    </tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--접수번호-->Số tiếp nhận</td> 
                        <td class="tdb"  colspan="3"><%=ett.getrecept() %></td> 
                    </tr> 
                    <tr> 
                        <td height="2" colspan="4" class="line"></td> 
                    </tr> 
                </table> 
                <BR style = "display:none"> 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
                    <tr><td height="4" colspan="4" class="line"></td></tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--수요기관코드-->Mã cơ quan</td> 
                        <td class="tdb"  width="30%"><%=ett.getg2bCode() %></td> 
                        <td class="tdar" width="20%"><!--수요기관명-->Tên cơ quan</td> 
                        <td class="tdb"  width="30%" ><%=ett.getgoNameFull() %></td> 
                    </tr> 

                    <tr> 
                        <td class="tdar" width="20%"><!--사업자등록번호-->Số ĐKKD</td> 
                        <td class="tdb"  width="30%" ><%=ett.getsaupNo() %></td> 
                        <td class="tdar" width="20%"><!--담당자-->Người phụ trách</td> 
                        <td class="tdb"  width="30%" ><%=ett.gettaskmaster() %></td> 
                    </tr> 
                    <tr> 
                        <td class="tdar"><!--주소-->Địa chỉ</td> 
                        <td class="tdb" colspan="3"><%=ett.getADDR() %></td> 
                    </tr> 
                    <tr><td height="2" colspan="4" class="line"></td></tr> 
                </table> 
                <br> 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
                    <tr><td height="4" colspan="2" class="line"></td></tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--진행상태-->Trạng thái tiến hành</td> 
                        <td width="80%" class="tdb"><span class="red"><!--등록-->Đã được phê duyệt</span></td> 
                    </tr> 
                    <tr align="center"><td height="4" colspan="4">
						<input type="button" class="commonbutton" value="Sửa đăng ký" onclick="updateRequest2()">
                    </td>
                    </tr> 
                    <tr><td height="2" colspan="2" class="line"></td></tr> 
                </table> 
 
<%   } else if("D".equals(ett.getenYn()) || "C".equals(ett.getenYn())) {   %> 
 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr" style = "display:none"> 
                    <tr><td height="4" colspan="4" class="line"></td></tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--접수번호-->Số tiếp nhận</td> 
                        <td class="tdb"  colspan="3"><%=ett.getrecept() %></td> 
                    </tr> 
                    <tr><td height="2" colspan="4" class="line"></td> 
                    </tr> 
                </table> 
                <BR style = "display:none"> 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
                    <tr><td height="4" colspan="4" class="line"></td></tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--수요기관코드-->Mã cơ quan</td> 
                        <td class="tdb"  width="30%"><%=ett.getg2bCode() %></td> 
                        <td class="tdar" width="20%"><!--수요기관명-->Tên cơ quan</td> 
                        <td class="tdb"  width="30%" ><%=ett.getgoNameFull() %></td> 
                    </tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--사업자등록번호-->Số ĐKKD</td> 
                        <td class="tdb"  width="30%" ><%=ett.getsaupNo() %></td> 
                        <td class="tdar" width="20%"><!--담당자-->Người phụ trách</td> 
                        <td class="tdb"  width="30%" ><%=ett.gettaskmaster() %></td> 
                    </tr> 
                    <tr> 
                        <td class="tdar"><!--주소-->Địa chỉ</td> 
                        <td class="tdb" colspan="3"><%=ett.getADDR() %></td> 
                    </tr> 
                    <tr><td height="2" colspan="4" class="line"></td></tr> 
                </table> 
                <br> 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
                    <tr><td height="4" colspan="4" class="line"></td></tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--진행상태-->Trạng thái tiến hành</td> 
                        <% if("D".equals(ett.getenYn())) {   %> 
                        	<td class="tdb" width="30%" ><span class="red"><!--미등록-->Từ chối</span></td> 
                        <%} else if("C".equals(ett.getenYn())) { %>
                        	<td class="tdb" width="30%" ><span class="red">Hủy</span></td> 
                        <%} %>
                        <td class="tdar" width="20%"><!--수요기관등록신청서-->Đơn đăng ký BMT</td> 
                        <td class="tdb" width="30%"> 
                            <input type="button" class="commonbutton" value="Thực hiện in" onclick="javascript:js_print();">
                        </td> 
                    </tr> 
                    <tr> 
                       <td class="tdar" width="20%"><!--반려사유-->Lý do từ chối</td> 
                       <td class="tdb" colspan="3"><%=ett.getback() %></td> 
                    </tr> 
                    <tr><td height="2" colspan="4" class="line"></td></tr> 
                </table> 
 
<%   }else if("E".equals(ett.getenYn())){    %> 

 <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr" style = "display:none"> 
                    <tr><td height="4" colspan="4" class="line"></td></tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--접수번호-->Số tiếp nhận</td> 
                        <td class="tdb"  colspan="3"><%=ett.getrecept() %></td> 
                    </tr> 
                    <tr><td height="2" colspan="4" class="line"></td> 
                    </tr> 
                </table> 
                <BR style = "display:none"> 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
                    <tr><td height="4" colspan="4" class="line"></td></tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--수요기관코드-->Mã cơ quan</td> 
                        <td class="tdb"  width="30%"><%=ett.getg2bCode() %></td> 
                        <td class="tdar" width="20%"><!--수요기관명-->Tên cơ quan</td> 
                        <td class="tdb"  width="30%" ><%=ett.getgoNameFull() %></td> 
                    </tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--사업자등록번호-->Số ĐKKD</td> 
                        <td class="tdb"  width="30%" ><%=ett.getsaupNo() %></td> 
                        <td class="tdar" width="20%"><!--담당자-->Người phụ trách</td> 
                        <td class="tdb"  width="30%" ><%=ett.gettaskmaster() %></td> 
                    </tr> 
                    <tr> 
                        <td class="tdar"><!--주소-->Địa chỉ</td> 
                        <td class="tdb" colspan="3"><%=ett.getADDR() %></td> 
                    </tr> 
                    <tr><td height="2" colspan="4" class="line"></td></tr> 
                </table> 
                <br> 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
                    <tr><td height="4" colspan="4" class="line"></td></tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--진행상태-->Trạng thái tiến hành</td> 
                        <td class="tdb" width="30%" ><span class="red"><!--보류중-->Bảo lưu</span></td> 
                        <td class="tdar" width="20%"><!--수요기관등록신청서-->Đơn đăng ký BMT</td> 
                        <td class="tdb" width="30%"> 
                            <input type="button" class="commonbutton" value="Thực hiện in" onclick="javascript:js_print();">
                        </td> 
                    </tr> 
                    <tr> 
                       <td class="tdar" width="20%"><!--보류사유-->Lý do bảo lưu</td> 
                       <td class="tdb" colspan="3"><%=ett.getback() %></td> 
                    </tr> 
                    <tr><td height="2" colspan="4" class="line"></td></tr> 
<!--                    <tr align="center"><td height="4" colspan="4">-->
<!--                        <br>-->
<!--						<input type="button" class="commonbutton" value="Sửa đăng ký" onclick="updateRequest('<%= ett.getenYn() %>', '<%= ett.getDepartmentNo()%>', '<%= ett.getGroupNo()%>')">-->
<!--                    </td>-->
<!--                    </tr> -->
                </table>

    <%   }else if("N".equals(ett.getenYn())){    %> 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr" style = "display:none"> 
                    <tr><td height="4" colspan="4" class="line"></td></tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--접수번호-->Số tiếp nhận</td> 
                        <td class="tdb"  colspan="3"><%=ett.getrecept() %></td> 
                    </tr> 
                    <tr><td height="2" colspan="4" class="line"></td></tr> 
                </table> 
                <BR style = "display:none"> 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
                    <tr><td height="4" colspan="4" class="line"></td></tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--수요기관코드-->Mã cơ quan</td> 
                        <td class="tdb"  width="30%"><%=ett.getg2bCode() %></td> 
                        <td class="tdar" width="20%"><!--수요기관명-->Tên cơ quan</td> 
                        <td class="tdb"  width="30%" ><%=ett.getgoNameFull() %></td> 
                    </tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--사업자등록번호-->Số ĐKKD</td> 
                        <td class="tdb"  width="30%" ><%=ett.getsaupNo() %></td> 
                        <td class="tdar" width="20%"><!--담당자-->Người phụ trách</td> 
                        <td class="tdb"  width="30%" ><%=ett.gettaskmaster() %></td> 
                    </tr> 
                    <tr> 
                        <td class="tdar"><!--주소-->Địa chỉ</td> 
                        <td class="tdb" colspan="3"><%=ett.getADDR() %></td> 
                    </tr> 
                    <tr> <td height="2" colspan="4" class="line"></td></tr> 
                </table> 
                <br> 
                <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
                    <tr><td height="4" colspan="4" class="line"></td></tr> 
                    <tr> 
                        <td class="tdar" width="20%"><!--진행상태-->Trạng thái tiến hành</td> 
                        <td class="tdb" width="30%" ><span class="red"><!--대기중-->Đang chờ phê duyệt</span></td> 
                        <td class="tdar" width="20%"><!--수요기관등록신청서-->Đơn đăng ký BMT</td> 
                        <td class="tdb" width="30%"> 
                            <input type="button" class="commonbutton" value="Thực hiện in" onclick="javascript:js_print();" alt="In đơn đăng ký cơ quan">
                        </td> 
                    </tr> 
                    <tr> <td height="2" colspan="4" class="line"></td></tr> 
                    <tr align="center"><td height="4" colspan="4">
                        <br>
                       	<input type="button" class="commonbutton" value="Hủy đăng ký" onclick="cancel('<%= ett.getenYn() %>')">
						&nbsp;
					 	<input type="button" class="commonbutton" value="Sửa đăng ký" onclick="updateRequest('<%= ett.getenYn() %>', '<%= ett.getDepartmentNo()%>', '<%= ett.getGroupNo()%>')">
                    </td>
                    </tr> 
                </table> 
<% 
        } 
    } 
} 
%>      
	<div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
	</div> 
</body> 
</html>
