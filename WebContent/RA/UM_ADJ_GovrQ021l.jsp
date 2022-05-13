<%--
/****************************************************************************/
/*                                                                          */
/*    Program ID    :     UM_ADJ_GovrL020l.jsp								*/
/*                                                                          */
/*    description   :     인증서신청승인목록조회	 							*/
/*                                                                          */
/****************************************************************************/
/*  최초생성         2002.06.11          오 창 렬                           */
/****************************************************************************/

	2003.04.27	김영진	프로그램 수정
	2004.05.18	윤태우	검색리스트에 신청일자 추가
	2004.11.02	설원식	승인지청에 본청콜센터 추가
	2005.01.11	설원식	승인지청은 본청만 존재
	2007.04.30    임지훈     기관등록시 신청한 인증서에 대해서만 보류상태 표시
	2009.05.13 SONNQ

/***************************************************************************/
/* Program ID         : UM_ADJ_GovrL020l.jsp                        */
/* Program Explanation: Tra cứu tình trạng đăng ký Bên Nhà thầu     */
/* Program Summary    : Tra cứu tình trạng đăng ký Bên Nhà thầu		*/
/* Relation Program   : */ 
/*                      */
/* Table              :                  */
/***************************************************************************/
/* Customizing Composer : MR. SonDN 25.05.2009                        */
/***************************************************************************/
/* Cần thêm phần phân quyền ở dòng 42 - done */
--%>

<%@page import="common.util.CommUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="/jsp/common/jspToError.jsp?type=0&url=&message=001" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, common.*, LOGIN.*"  %>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>
<%@ page import="g2b.sso.*"%>
<jsp:useBean id="ctl" class="dao.UM_RAB_MastSupplierHist" scope="page" />
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />
<jsp:useBean id="permission" class="servlets.EP_MDB_GGA557" scope="page" />
<%-- <jsp:useBean id="nationalCtrl" class="beans.NationalRefDAO" scope="page" /> --%>

   
<%
//         UM_Auth_Check uac=new UM_Auth_Check(request, response);
//         // 권한체크[기관 승인자만 사용] - 	Hãy kiểm tra quyền truy cập [được sử dụng chỉ có thẩm quyền của CQNN]
//         uac.checkCookie("k",null,null);    
  	 
	SSO sso = new SSO(pageContext);
	
	if (sso.getGubun().equals("a")) {
	} 	else{
		out.println("<script>alert('Bạn không có quyền truy cập trang này');  main.location.href='http://muasamcong.mpi.gov.vn:8070/loginNew.jsp'</script>");
		return;
	}
	
	String urlPamramter = request.getQueryString();
	String url1 = request.getRequestURL().toString();
	if (urlPamramter != null)
		url1 += "?" + urlPamramter;
	String url="";
	String userID = sso.getID();
	//check xem menu nay co phan quyen khong
	boolean checkPer = permission.checkPermissionByURL(userID, url1);

	if (checkPer) {
		out.println("<script>alert('Bạn không có quyền truy cập trang này');  main.location.href='http://muasamcong.mpi.gov.vn:8070/loginNew.jsp'</script>");
		return;
	} 

	
        com.oreilly.servlet.ParameterParser psr = new com.oreilly.servlet.ParameterParser(request);
   	
        String upcheNm			= psr.getStringParameter("upcheNm",				"");// 업체명
        String instituCd			= psr.getStringParameter("instituCd",				"");// 업체명
        String Start			= psr.getStringParameter("Start",				"");// 조회 시작일
        String End				= psr.getStringParameter("End",					"");// 조회 종료일
		String status			= psr.getStringParameter("status",				"N");	  // Trạng thái	
		OneRowEntity[] ett = null;
        int page_no = Integer.parseInt(request.getParameter("page_no")==null?"1":request.getParameter("page_no"));	// 페이지번호
	
        int PAGEMAX		= 10;																						// 한페이지에 출력되는 목록 수
        int INDEXMAX	= 10;																						// 한번에 출력되는 인덱스의 수
        int maxcnt		= ctl.getCountList(upcheNm, Start, End, instituCd, status);					// 검색조건으로 조회된 자료의 수
        int maxrow		= 0;																						// 한페이지에 출력될 목록 수 (마지막 페이지를 위하여)
        int maxindex	= 0;																						// 한번에 출력되는 인덱스의 수 (마지막 인덱스 그룹을 위하여)
		if(maxcnt > 0){
    		ett = ctl.getList( page_no,  upcheNm,  Start,  End,  instituCd,  status);	            // 조회 Bean 호출
		}
        

        if(page_no == (int)Math.ceil((double)maxcnt / PAGEMAX))
        {
                if(maxcnt%PAGEMAX == 0)
                {
                        maxrow = PAGEMAX;
                }else{
                        maxrow = (maxcnt%PAGEMAX);
                }
        }else{
                maxrow = PAGEMAX;
        }

        if((((int)Math.ceil((double)page_no-1)/INDEXMAX)+1) == (int)Math.ceil((double)((double)maxcnt / PAGEMAX)/INDEXMAX))
        {
                if((int)Math.ceil((double)maxcnt / PAGEMAX)%INDEXMAX == 0)
                {
                        maxindex = INDEXMAX;
                }else{
                        maxindex = (int)Math.ceil((double)maxcnt / PAGEMAX)%INDEXMAX;
                }
        } else {
                maxindex = INDEXMAX;
        }
        String date="";
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat format2=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
%>

<html>
    <head>
        <title>Phê duyệt chỉnh sửa Bên Nhà thầu</title><!-- 인증서신청 승인목록 조회 -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
        <link href="http://muasamcong.mpi.gov.vn/css/TA.css" rel="stylesheet" type="text/css" />
        <script language="javascript" src="../js/UM.js"></script>
        <script language="javascript" src="../calendar/js/calendar.js"></script>

    </head>

    <body>
        <div class="col-750 clearfix last">
            <h1 class="pageTitle"><i class="icon-title"></i>Phê duyệt chỉnh sửa Bên Nhà thầu</h1>
<!--            <div class="contenttable">-->
                <form name="fm">
                    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <td height="4" colspan="4" class="line"></td>
                        </tr>
                        <tr>
                            <td style="width: 20%" class="tdar"><!-- 수요기관명 --> Số ĐKKD</td>
                            <td class="tdb" colspan="3">
                                <input type="text" name="instituCd" style="width: 30%"	value="<%=instituCd%>" class="read" size="20" style="width: 100%" />
                            </td>
                        </tr>
                        <tr height=30>
                            <td style="width: 20%" class="tdar">Tên bên nhà thầu <!-- 수요기관명 --></td>
                            <td class="tdb" colspan="3" >
                                <input type="text" name="upcheNm" value="<%=upcheNm%>" class="read" size="20" style="width: 100%"/>
                            </td>
                        </tr>            
                                  
                        <tr height="30">
                         <%-- 
                            <td style="width: 20%" class="tdar">Tỉnh / Thành phố<!--승인지청--></td>
                            <td class="tdb" style="width: 30%">
                                <%
                                            List provinces = nationalCtrl.getList("GU7");
                                %>
                                <select name="area" title="">
                                    <option value="" <%if (area.equals("") || area == null) {%>selected<%}%>>------ Lựa chọn ------</option>
                                    <%
                                                String pItem = "";
                                                for (int i = 0; i < provinces.size(); i++) {
                                                    pItem = provinces.get(i).toString();
                                                    String naCode = pItem.split("#")[1];
                                                    String naName = pItem.split("#")[0];
                                    %>
                                    <option value="<%=naCode%>" <%if (area != null && naCode.equals(area)) {%>selected<%}%>><%=naName%></option>
                                    <%
                                                }
                                    %>

                                </select>
                            </td>
                             --%>
                            <td style="width: 15%" class="tdar">Thời gian sửa <!-- 신청기간 --></td>
                            <td style="width: 35%" class="tdb"><!-- javascript 변경 2007-08-09 -->
                                <input type="text" name="Start" value="<%=Start%>"  size="12" maxlength="8" class="read" onkeydown="js_OnlyNumber(this)" onBlur='js_DateType(this,"/")' onFocus="js_removeChar2(this)">
                                <img name="ImgbCalendar" border="0" src="/img/calendar.gif" onClick="Calendar_D(document.fm.Start)"> ~
                                <input type="text" name="End" value="<%=End%>" size="12" maxlength="8" class="read" onkeydown="js_OnlyNumber(this)" onBlur='js_DateType(this,"/")' onFocus="js_removeChar2(this)">
                                <img name="ImgbCalendar" border="0" src="/img/calendar.gif" onClick="Calendar_D(document.fm.End)">

                            </td>
                        </tr>
                        <tr>
                            <td class="tdar"  >Phân loại trạng thái </td>
                            <td class="tdb"   colspan="3" >
                            	<select name="status">
                            		<option <%="A".equals(status)?"selected":"" %> value="A">Tất cả </option>
                            		<option <%="N".equals(status)?"selected":"" %> value="N">Chưa phê duyệt </option>
                            		<option <%="Y".equals(status)?"selected":"" %> value="Y">Đã phê duyệt </option>
                            	</select>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;" colspan="4">
                                <input type="button" value=" Tìm " class="commonbutton" onclick="javascript:checkform();" />
                            </td>
                        </tr>
                        <tr>
                            <td height="2" colspan="4" class="line"></td>
                        </tr>
                    </table>
                    <br>
                    <table width="100%" border="0" cellpadding="2" cellspacing="1" class="tr">
                        <tr>
                            <td class="page">[ <img src="/img/page.gif" align="absmiddle"> Trang <%=page_no%>/<%=(int)Math.ceil((double)maxcnt / PAGEMAX)%> ]</td>
                        </tr>
                    </table>
                    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <td height="4" class="line" colspan="9"></td>
                        </tr>
                        <tr height="30">
                            <td	width="2%"		class="tdac">Số </td>
                            <td	width="8%"		class="tdac">Số ĐKKD </td>
                            <td class="tdac">Tên bên nhà thầu</td>
                            <td	width="20%"		class="tdac">Ngày sửa</td>
                            <td	width="12%"		class="tdac">Cơ quan sửa</td>
                            <td	width="20%"		class="tdac">Trạng thái phê duyệt</td>
                            <%--<td	width="14%"		class="tdac">Tỉnh / Thành phố</td> --%>
                        </tr>
                        <tr>
                            <td height="1" class="line" colspan="9"></td>
                        </tr>

                        <%	if (maxcnt > 0 ) {		// 조회결과가 있을경우 리스트 출력
                               for(int i=0; i < ett.length; i++) {
                            	   try{
                            		   date=format2.format(format.parse(ett[i].data[3]));
                            	   }catch(Exception e){Log.errors(this, e, "Parse date error");}
								url="/AD/UM_ADJ_ConrE030d.jsp?saupNo="+CommUtil.escapeHtml(ett[i].data[0]);
                        %>
                        <tr class="<%= (i%2 == 1) ? "tdcc" : "tdbc"%>" >
                            <td><%=((page_no-1)*PAGEMAX)+i+1%></td>
                            <td><%=ett[i].data[0]%></td>
                            <td align="left" >
                                <a href="<%=url%>" target="_self"><%=ett[i].data[1]%></a>
                            </td>
                            <td><%=date%></td>
                            <td><%=ett[i].data[4]%></td>
                            <td><%="N".equals(ett[i].data[5]) ? "Chưa phê duyệt":"Đã phê duyệt"%></td>
                            <%--<td><%=ett[i].data[2]%></td> --%>
                        </tr>
                        <%		}
                                }
                                if (maxcnt == 0) {
                        %>
                        <tr>
                            <td class="tdbc" colspan="9">
                                <p>Không tìm thấy kết quả tra cứu <!-- 검색 조건에 맞는 데이타가 존재하지 않습니다 -->.</p>
                            </td>
                        </tr>
                        <%	} %>
                        <tr>
                            <td height="2" class="line" colspan="9"></td>
                        </tr>
                    </table>

                    <!---------------------- 네비게이션 바 시작 ------------------------>



                    <%	if( maxcnt > 0 ){	%>
                    <%
                            // Thực hiện việc phân trang và tạo link liên kết đến các trang
                            out.println(CommPageUtil.getNextPageIndexes(request, null, maxcnt, PAGEMAX,  page_no));
                    %>

                    <table width="100%" cellpadding="2" cellspacing="2" style="display:none;">
                        <tr>
                            <td align="center" background="/img/dotlines.gif" height="1"></td>
                        </tr>
                        <tr>
                            <td align="center" class="redc">
                                <a href="UM_ADJ_GovrL020l.jsp?page_no=1&upcheNm=<%=upcheNm%>&Start=<%=Start%>&End=<%=End%>" title="처음">&lt;&lt;</a>&nbsp;

                                <%if ((page_no-1)/INDEXMAX > 0){%>
                                &nbsp;
                                <a href="UM_ADJ_GovrL020l.jsp?page_no=<%=((int)Math.ceil((double)(page_no-1))/INDEXMAX)*INDEXMAX+1-INDEXMAX%>&upcheNm=<%=upcheNm%>&Start=<%=Start%>&End=<%=End%>"><%}%>
                                    <img src="/img/bu_backarw.gif" align="absmiddle" border="0">
                                    <% if (page_no-1 != 0) {	%></a>&nbsp;
                                    <%				   }						%>


                                <%				for(int j=((int)Math.ceil((double)(page_no-1))/INDEXMAX)*INDEXMAX+1  ;  j<=maxindex-1+((int)Math.ceil((double)(page_no-1))/INDEXMAX)*INDEXMAX+1  ;  j++) {
					if (page_no == j) { %>
					[<%=j%>]
                                <%
                                                                        }
					
                                                                        if (page_no != j) { %>
                                <a href="UM_ADJ_GovrL020l.jsp?page_no=<%=j%>&upcheNm=<%=upcheNm%>&Start=<%=Start%>&End=<%=End%>" title="<%=j%>">[<%=j%>]</a>
                                <%					}
                                                                }
                                %>

                                <%	// 마지막 페이지면 다음 버튼의 링크를 걸지 않음
	
                                        if ((page_no-1)/INDEXMAX < (int)Math.ceil((double)maxcnt / PAGEMAX)/INDEXMAX) { %>

                                &nbsp;<a href="UM_ADJ_GovrL020l.jsp?page_no=<%=((int)Math.ceil((double)(page_no-1))/INDEXMAX)*INDEXMAX+1+INDEXMAX%>&upcheNm=<%=upcheNm%>&Start=<%=Start%>&End=<%=End%>">

                                    <%	}	%>
                                    <img src="/img/bu_nextarw.gif" align="absmiddle" border="0">
                                    <% if (page_no != (int)Math.ceil((double)maxcnt / PAGEMAX)) { %></a>&nbsp;
                                    <%				   }	%>
                                <a href="UM_ADJ_GovrL020l.jsp?page_no=<%=(int)Math.ceil((double)maxcnt/PAGEMAX)%>&upcheNm=<%=upcheNm%>&Start=<%=Start%>&End=<%=End%>" title="끝">&gt;&gt;</a>
                        </tr>
                        <tr>
                            <td align="center" background="/img/dotlines.gif" height="1"></td>
                        </tr>
                    </table><!---------------------- 네비게이션 바 끝 ------------------------>
                    <%	}	%>

                    <input type="hidden" name="flag" value="S">
                </form>

            <div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
        </div>
    </body>
    <SCRIPT LANGUAGE="JAVASCRIPT">

        function checkform(){
            var start   = document.forms[0].Start.value;
            var end     = document.forms[0].End.value;
		
            var str_sdate =js_removeChar(start,'/');
            var str_edate =js_removeChar(end,'/');
            var sdate = new Date(str_sdate.substring(0, 2), str_sdate.substring(2, 6), str_sdate.substring(6, 8));
            sdate = sdate.getTime();
        
            var edate = new Date(str_edate.substring(0, 2), str_edate.substring(2, 6), str_edate.substring(6, 8));
            edate = edate.getTime();

            if (compareDate(start, end)){
                document.forms[0].target = "_self"
                document.forms[0].method = "post";
                document.forms[0].action = "UM_ADJ_GovrQ021l.jsp"
                document.forms[0].submit();
            }
            
            return;

        }

        function compareDate(date1, date2) {
            if (date1 != '' && date2 != ''){
                if (date1.indexOf("/") >0 || date2.indexOf("/") > 0 )
                {
                    var year1 = date1.substring(6);
                    var month1 = date1.substring(3, 5);
                    var day1 = date1.substring(0, 2);
                    var dt1 = parseInt(year1 + month1 + day1);

                    var year2 = date2.substring(6);
                    var month2 = date2.substring(3, 5);
                    var day2 = date2.substring(0, 2);
                    var dt2 = parseInt(year2 + month2 + day2);

                    var sub = dt2 - dt1;
                    if (sub > 0) {

                        return true;
                    }
                    alert("Kiểm tra lại khoảng thời gian tìm kiếm!");
                    return false;}

                return true;

            } else {
                return true;
            }
        }

        function js_searchDate(formname, boxname, gubun){
            url = "../jsp/common/calendar.jsp?form_name=" + formname + "&box_name=" + boxname + "&gubun=" + gubun;	window.open(url,"Day_Search","directories=no,resizable=yes,scrollbars=no,location=no,width=181,height=205");
        }
    
    
        function toMove(){
    
    
    
        }
    </SCRIPT>
</html>
