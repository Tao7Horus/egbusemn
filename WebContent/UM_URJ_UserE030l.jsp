<%--
/****************************************************************************/
/*                                                                          */
/*    Program ID    :     UM_URJ_UserE030l.jsp                              */
/*                                                                          */
/*    description   :     이용자ID목록조회화면                              */
/*                                                                          */
/****************************************************************************/
/*  최초생성         2006.03.07                                             */
/****************************************************************************/
   
/***************************************************************************/
/* Program ID         : UM_URJ_UserE030l                       */
/* Program Explanation:                       */
/* Program Summary    :  		*/
/* Relation Program   : 						*/ 
/*                     							 */
/* Table              :                  */
/***************************************************************************/
/* Customizing Composer : MR. SonDN 12.06.2009                       		*/
/***************************************************************************/ 
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%//@ page errorPage="/jsp/common/jspToError.jsp?type=1&url=&message=001" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, common.*,entity.*, g2b.sso.*" %>

<jsp:useBean id="ctl"    class="beans.UM_URB_UserE040p" scope="page" />
<jsp:useBean id="ctl1"   class="beans.UM_URB_UserE030p" scope="page" />
<%!    final int PAGE_SIZE=10; %>
<% 
    UM_URE_UserE050b   ett = null; 
    OneRowEntity ReceiverEntity=null;      		// 대표수신자ID정보
    
    CommEntity[] userIDListEntity=null;			// 이용자아이디 리스트
    CommEntity[] deleteIDListEntity=null;		// 삭제/복원 변경이력정보 리스트
    
    
    String oper="";			// 이용자 구분 [업체 c, 기관 g]
    String del = "n";		// ????
    String id="";			// 이 화면을 호출한 이용자 ID
    
    
    //	권한체크
    /*UseCookie uscook=new UseCookie(this);
    SSO sso = new SSO(pageContext);
        
    if(uscook.isPasswordOk(request, response)){
        id      =uscook.GetCookie(request,response,"usemnID");
        oper    =uscook.GetCookie(request,response,"usemnSosok");
        del     =uscook.GetCookie(request,response,"usemnDeleteGubun");
    }else{
        if(sso.isLogin()){
            id = sso.getID();
            oper=sso.getGubun();
            if(!(oper.equals("c") || oper.equals("x"))) oper="g";
            del="y";
        }else{ 
        	throw new Exception("Không đọc được thông tin của người dùng tương ứng. Hãy gọi đến số xxxx-xxxx");
        	//throw new Exception("해당 이용자의 정보를 읽을 수 없습니다. 1588-0800으로 문의주시기 바랍니다.");
        }
    }*/
    SSO sso = new SSO(pageContext);
    if(sso.isLogin()){
        id = sso.getID();
        oper=sso.getGubun();
        if(!(oper.equals("c") || oper.equals("x"))) oper="g";
        del="y";
    }else{ 
    	throw new Exception("Không đọc được thông tin của người dùng tương ứng. Hãy gọi đến số xxxx-xxxx");
    	//throw new Exception("해당 이용자의 정보를 읽을 수 없습니다. 1588-0800으로 문의주시기 바랍니다.");
    }
       // throw new Exception("OK");
    if (oper.equals("g")){
        ett = ctl1.select_guser(id);    // 조회 Bean 호출
    }
    if (oper.equals("c") || oper.equals("x")){
        ett = ctl1.select_cuser(id);    // 조회 Bean 호출
    }
	
	String page_no=request.getParameter("page_no");
    if(page_no==null || page_no.equals("")) page_no="1";
    
    String masterCode = ett.getCode();
    int maxcnt = ctl.Max_count(masterCode, null); 						// 이용자ID정보 조회된 자료의 수
    
	ReceiverEntity	= ctl.getKeyID(masterCode, null);             		// 대표수신자ID 정보
    userIDListEntity = ctl.select_idlist(masterCode, Integer.parseInt(page_no), PAGE_SIZE, null);   // 이용자ID리스트

    if(ReceiverEntity.DataExist==false){
    	//Log.debug(this.getClass().getName()+" MaseterCode["+masterCode+"]:대표수신자ID정보를 가져올 수 없습니다.");
    	Log.debug(this.getClass().getName()+" MaseterCode["+masterCode+"]: không gọi được thông tin ID người nhận đại diện.");
     	return;	
    }
    
    
    
%>

<html>
<head>
<title>Danh mục ID người dùng</title><!--<title>이용자ID목록</title>-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/UM.css">

</head>

<body background="/img/bg01.gif" text="#3C3C3C" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">









<form name="fm">

  <input type="hidden" name="oldKeyID"   value="<%=ComStr.checkNull(ReceiverEntity.getDataFromName("USER_ID"))%>" > 
  <input type="hidden" name="newKeyID"   value="" > 
  <input type="hidden" name="userID"     value="" > 
  <input type="hidden" name="flag"       value="" >
  	

  <table width="823" border="0" cellpadding="0" cellspacing="0" height="100%"  background="/img/bg_sub.gif">
    <tr valign="bottom" height="57"> 
      <td rowspan="2"><img src="/img/sub_title_01.jpg"></td>
      <td colspan="2" background="/img/sub_title_02.jpg" width="788" height="57" class="HEADLINE"> 
        &nbsp;Quản lý ID người dùng <!-- 이용자ID 관리 --></td>
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
        <table width="100%" cellpadding="2" cellspacing="1">
          <tr> 
            <td class="fontb">[Hướng dẫn quản lý ID người dùng]<!-- [이 용자ID 관리 안내] --></td>
          </tr>
        </table>
		<table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">     
			<tr> 
				<td class="tdb"> 
                    <!--  1. 선택한 이용자ID로 대표자수신여부를 변경 할 수 있습니다. -->
                    1. Không thể thay đổi ID người dùng được chọn thành ID người đại diện<br>
                    <!-- 2. 선택한 인증서를 삭제 또는 복원 할 수 있습니다.  -->
                    2. Không thể khôi phục hoặc xóa Chứng nhận số đã lựa chọn<br>
                    <!-- 3. 이용자ID를 클릭하면 ID 삭제, 복원에 대한 변경이력을 확인 할 수 있습니다. -->
                    3. Nếu click vào ID thì có thể kiểm tra được lý lịch thay đổi của thao tác xóa và khôi phục.
                </td>
			</tr>
		</table>
        <br>
        
        <!---------------------- 사용자정보 타이틀 테이블 시작 ------------------------>
        <table width="100%" cellpadding="2" cellspacing="1">
          <tr> 
            <td class="fontb">[Thông tin cơ bản của ID nhận đại diện]<!-- [대표수신자ID 기본정보] --></td>
          </tr>
        </table>
        <!---------------------- 사용자정보 타이틀 테이블 끝 ------------------------>
        
        <!---------------------- 사용자정보 테이블 시작 ------------------------>
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
          <tr> 
            <td height="4" colspan="4" class="line"></td>
          </tr>
          <tr>
            <td class="tdar"><%=oper.equals("g")?"수요기관코드 - Demand Organization Code":""%><%=oper.equals("c") || oper.equals("x")?"<!--사업자등록번호 --> Số ĐKKD":""%></td>
            <td width="35%" class="tdb"><%=new common.util.CommUtil().insert_minus_saupno(masterCode)%></td>
            <td width="15%" class="tdar"><%=oper.equals("g")?"수요기관명":""%><%=oper.equals("c") || oper.equals("x")?"<!--상호명--> Tên doanh nghiệp":""%></td>
            <td width="35%" class="tdb"><%=ett.getName()%></td>
          </tr>
          <tr>
            <td class="tdar">ID người nhận đại diện<!-- 대표수신자ID --></td>
            <td width="35%" class="tdb"><%=ComStr.checkNull(ReceiverEntity.getDataFromName("USER_ID"))%></td>
            <td width="15%" class="tdar">Tên người phụ trách<!-- 담당자명 --></td>
            <td width="35%" class="tdb"><%=ComStr.checkNull(ReceiverEntity.getDataFromName("CHRGR_NM"))%></td>
          </tr>
          <tr>
            <td class="tdar">Tên phòng phụ trách <!-- 담당부서명 --></td>
            <td width="35%" class="tdb"><%=ComStr.checkNull(ReceiverEntity.getDataFromName("CHRGR_DEPART"))%></td>
            <td width="15%" class="tdar">Số điện thoại người phụ trách <!-- 담당자전화번호 --></td>
            <td width="35%" class="tdb"><%=ComStr.checkNull(ReceiverEntity.getDataFromName("CHRGR_PHONE_NO"))%></td>

          </tr>
          <tr> 
            <td height="2" colspan="6" class="line"></td>
          </tr>       
        </table>
        <!---------------------- 사용자정보 테이블 끝 ---------------------------->
        
        <br> 
        <!---------------------- 이용자ID 타이틀 테이블 시작 ------------------------>
        <table width="100%" cellpadding="2" cellspacing="1">
          <tr> 
            <td width="71%" class="fontb">[Danh mục ID người dùng]<!-- [이용자ID 목록] --></td>
            <td width="29%" align="right">
              <!---------------------- 버튼 테이블 시작 ------------------------>
              <input  type="button" class="commonbutton" value="Thay đổi người đại diện" onclick="javascript: modifyKeyID();">
              <!-- Thay đổi người nhận đại diện
              <a href="javascript:modifyKeyID();" onfocus="blur()"><img src="/img/bu_updatekeyID.gif" border="0" 

align="absmiddle"></a> --> 
              <!---------------------- 버튼 테이블 끝 ------------------------>
            </td>
          </tr>
          <tr>
          	<td class="tdb" colspan="2"><b> * <!-- 로그인한 이용 ID:--> ID người dùng đã login :&nbsp;<%=id%></b></td>
          </tr>	 
        </table>
		<!---------------------- 이용자ID 타이틀 테이블 끝 ------------------------>
        
        <!---------------------- 이용자ID 테이블 시작 ------------------------>
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
          <tr> 
            <td height="4" colspan="6" class="line"></td>
          </tr>
          <tr> 
            <td class="tdac" width="10%"><!-- 대표 <br>수신자여부  -->Tùy chọn <br>người nhận đại diện</td>
            <td class="tdac" width="20%"><!-- 이용자ID --> UserID</td>
            <td class="tdac" width="20%"><!-- 담당자명 --> Tên người phụ trách</td>
            <td class="tdac" width="20%"><!-- 담당자 <br>부서명--> Tên phòng  <br>phụ trách</td>
            <td class="tdac" width="10%"><!--  ID삭제--> Xóa ID</td>
            <td class="tdac" width="10%"><!-- ID복원 --> Khôi phục ID</td>
          </tr>
		<%  
			// 조회결과가 없을경우 메세지 출력후 종료
			if (maxcnt==0){
		%>
          <tr>
            <td class="tdbc" colspan="6">
            <p><!-- 조회결과가 없습니다. 조회항목을 점검하세요. -->Không có kết quả tìm kiếm. Hãy kiểm tra hạng mục tìm 

kiếm</p>
            </td>   
          </tr>
          <% //return;
          } else {
			%>
			
			

<%
    		// 조회결과가 있을경우 리스트 출력
    	for(int i=0, n=userIDListEntity.length;i<n;i++){ %>
    <tr class="<%=((i%2)==1)?"tdcc":"tdbc"%>">
        <td>
            <input type="radio" name="keyID" value="<%=ComStr.checkNull(userIDListEntity[i].data[0])%>" <%=userIDListEntity[i].data[0].equals("Y")?"checked":""%>
             			<%=userIDListEntity[i].data[6].equals("9")?" disabled":""%>>
            <input type="hidden" name="id" value="<%=ComStr.checkNull(userIDListEntity[i].data[1])%>" >
        </td>   
        <td>
            <p><a href="javascript:displayIDList('<%=ComStr.checkNull(userIDListEntity[i].data[1])%>');"><%=ComStr.checkNull

(userIDListEntity[i].data[1])%></a></p>
        </td>
        <td>
            <p><%=ComStr.checkNull(userIDListEntity[i].data[2])%></p>
        </td>
        <td>
            <p><%=ComStr.checkNull(userIDListEntity[i].data[3])%></p>
        </td>
        <%  if (!id.equals(ComStr.checkNull(userIDListEntity[i].data[1]))) { // Người dùng hiện tại?%>
		<td> 
				<% 		if (ComStr.checkNull(userIDListEntity[i].data[6]).equals("2") ) { %> 
								<input  type="button" class="commonbutton" value=" Xóa " onclick="javascript:deleteID('<%=ComStr.checkNull(userIDListEntity[i].data[1])%>', '<%=ComStr.checkNull(userIDListEntity[i].data[6])%>');">
				                <!-- <a href="javascript:deleteID('<%//=ComStr.checkNull(userIDListEntity[i].data[1])%>', '<%//=ComStr.checkNull(userIDListEntity[i].data[6])%>');">
				                <img src="/img/bu_delete.gif" border="0" align="absmiddle"> Xóa</a> -->
				<%  	}  else { %>
		                 		&nbsp; 
				<%  	} %>
		</td>
		<td>
			<%  	if (!ComStr.checkNull(userIDListEntity[i].data[6]).equals("2")) { %>
	                <input  type="button" class="commonbutton" value="Khôi phục" onclick="javascript: restoreID('<%=ComStr.checkNull(userIDListEntity[i].data[1])%>', '<%=ComStr.checkNull(userIDListEntity[i].data[6])%>');">
	                <!-- <a href="javascript:restoreID('<%//=ComStr.checkNull(userIDListEntity[i].data[1])%>', '<%///=ComStr.checkNull(userIDListEntity[i].data[6])%>');">
	                <img src="/img/bu_restore.gif" border="0" align="absmiddle">Khôi phục</a> -->
			<%   	}  else { %>
	                &nbsp;
			<%   	} %>
		</td>	
			<%} else { // Người dùng hiện tại?%>
         <td colspan="2"> <!-- 로그인한 이용자ID입니다.  -->Là ID người dùng đã login.</td> 
		<%  } // !id.equals(ComStr.checkNull(userIDListEntity[i].data[1])))// Người dùng hiện tại?%>
		
    </tr>


          <% }%>
            <td height="2" colspan="6" class="line"></td>
          </tr>
        </table> 
		

		
		<%}   // end if maxctt==0%>
        <tr><td height="2" colspan="6" class="line"></td></tr>
        </table>
 		<br>
 		<%            
            out.println(new WebUtil().getNextPageIndexes(request,null,maxcnt,PAGE_SIZE,Integer.parseInt(page_no)));
        %>
 		<!---------------------- 이용자ID 테이블 끝 ------------------------>
        
        <br>
        <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">     
          <tr> 
            <td height="4" class="line"></td>
          </tr>       <tr> 
            <td class="tdb"> 
              ▷ <!-- 선택한 이용자ID로 대표자수신여부를 변경 할 수 있습니다. -->
        Không thể thay đổi ID người dùng được chọn thành ID người đại diện      <br>
              ▷ <!-- ID삭제버튼을 클릭하셔서 사용하지 않는 이용자ID를 삭제하실 수 있습니다. -->
        Click vào nút xóa ID để xóa những ID không sử dụng      <br>
              ▷ <!-- ID복원버튼을 클릭하셔서 삭제된 이용자ID를 다시 사용하실 수 있습니다. -->
        Click vào nút khôi phục ID để sử dụng lại ID đã bị xóa      <br>
            </td>
          </tr>
          <tr> 
            <td height="2" class="line"></td>
          </tr>       
        </table>
</form>
      </td>
      <td width="28"></td>
    </tr>
    <tr height="35"><td colspan="3"></td></tr>
    <tr height="*"><td colspan="3"></td></tr>
    <tr> 
      <td colspan="3" height="50"><%//@ include file="/jsp/common/Footer.jsp" %></td>
    </tr>
  </table>      




</body>
<SCRIPT LANGUAGE="JavaScript">

function modifyKeyID() {

    var keyID = "";

    for (var i=0; i<document.fm.keyID.length; i++ ){
        if (document.fm.keyID[i].checked){
            keyID = document.fm.id[i].value;
        }
    }

    if (keyID == null || keyID== ""){
    	alert("Hãy chọn ID người nhận đại diện");//alert("대표수신자ID를 선택하십시오");
        return;
    }

    var oldKeyID = document.fm.oldKeyID.value;

    //if (confirm("대표수신자ID를 " + oldKeyID+ "에서 " + keyID + "로 변경하시겠습니까?") == 1) {
    if (confirm("Có thay đổi ID người nhận từ " + oldKeyID+ " thành " + keyID + " hay không?") == 1) {

        document.fm.flag.value="m";
        document.fm.userID.value=""; 
        document.fm.newKeyID.value = keyID;

        window.open("",'modiKeyID','status=yes,resizable=yes,scrollbars=no,toolbar=no,width=400,height=300');
        document.fm.target="modiKeyID";
        document.fm.method="post";
        document.fm.action="/servlet/servlets/UM_URV_UserE050c";
        document.fm.submit();
        return;
    }
}

function deleteID(id) {

    if (document.fm.oldKeyID.value == id) {
        //alert("대표수신자ID는 삭제처리 할 수 없습니다.\r\n대표수신자ID를 변경한 후 삭제처리 하십시오");
        alert("Không thể xóa được ID người nhận đại diện.\r\nHãy thay đổi người nhận đại điện rồi xóa");
        return;
    }


   // if (confirm("이용자ID " + id + "를 삭제하시겠습니까?") == 1) {
 	if (confirm("Có xóa ID người dùng " + id + " không ?") == 1) {

        document.fm.flag.value="d";
        document.fm.userID.value=id; 
        
        window.open('','deleteReason','status=yes,resizable=no,scrollbars=no,toolbar=no,width=550,height=450');
        document.fm.target="deleteReason";
        document.fm.method="post";
        document.fm.action="/RA/UM_URJ_UserE030r.jsp";        
        document.fm.submit();
        return;
    }    
}

function restoreID(id) {

    //if (confirm("이용자ID " + id + "를 복원하시겠습니까?") == 1) {
	if (confirm("Có hồi phục ID người dùng " + id + " không?") == 1) {
        document.fm.flag.value="r";
        document.fm.userID.value=id; 
        //document.fm.DN1.value=SG_G2B.SG_GetUserDN("S");

        window.open("",'modiKeyID','status=yes,resizable=yes,scrollbars=no,toolbar=no,width=400,height=300');
        document.fm.target="modiKeyID";
        document.fm.method="post";
        document.fm.action="/servlet/servlets/UM_URV_UserE050c";
        document.fm.submit();
        return;
    }
}
function displayIDList(id) {
	
        document.fm.userID.value=id;         
        window.open('','historyList','status=yes,resizable=yes,scrollbars=yes,toolbar=no,width=800,height=600');
        document.fm.target="historyList";
        document.fm.method="post";
        document.fm.action="/RA/UM_URJ_UserE030d.jsp";        
        document.fm.submit();
        return;
    
}

</SCRIPT>
</html>