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
/* Program ID         : UM_ADJ_GovrL021l.jsp                        */
/* Program Explanation: Tra cứu tình trạng đăng ký bên mời thầu     */
/* Program Summary    : Tra cứu tình trạng đăng ký bên mời thầu		*/
/* Relation Program   : */ 
/*                      */
/* Table              :                  */
/***************************************************************************/
/* Customizing Composer : MR. SonDN 25.05.2009                        */
/***************************************************************************/
/* Cần thêm phần phân quyền ở dòng 42 - done */
--%>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="/jsp/common/jspToError.jsp?type=0&url=&message=001" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, common.*, LOGIN.*"  %>
<%@ include file = "/jsp/common/fnUmCommon.jsp" %>
<%@ page import="g2b.sso.*"%>
<jsp:useBean id="ctl" class="beans.UM_ADB_GovrA010c" scope="page" />
<jsp:useBean id="List" class="beans.Common_ComBo" scope="page" />
<jsp:useBean id="permission" class="servlets.EP_MDB_GGA557" scope="page" />


<%
//         UM_Auth_Check uac=new UM_Auth_Check(request, response);
//         // 권한체크[기관 승인자만 사용] - 	Hãy kiểm tra quyền truy cập [được sử dụng chỉ có thẩm quyền của CQNN]
//         uac.checkCookie("k",null,null);    
	SSO sso = new SSO(pageContext);
	
	if (sso.getGubun().equals("k")) {
	} 	else{
		out.println("<script>alert('Bạn không có quyền truy cập trang này');  main.location.href='http://muasamcong.mpi.gov.vn:8070/loginNew.jsp'</script>");
		return;
	}

	
	String urlPamramter = request.getQueryString();
	String url1 = request.getRequestURL().toString();
	if (urlPamramter != null)
		url1 += "?" + urlPamramter;

	String userID = sso.getID();
	//check xem menu nay co phan quyen khong
	boolean checkPer = permission.checkPermissionByURL(userID, url1);

	if (checkPer) {
		out.println("<script>alert('Bạn không có quyền truy cập trang này');  main.location.href='http://muasamcong.mpi.gov.vn:8070/loginNew.jsp'</script>");
		return;
	} 

	
        com.oreilly.servlet.ParameterParser psr = new com.oreilly.servlet.ParameterParser(request);
   	
        String instituCd			= psr.getStringParameter("instituCd",				"");// 업체명
        OneRowEntity[] ett = null;
        int page_no = Integer.parseInt(request.getParameter("page_no")==null?"1":request.getParameter("page_no"));	// 페이지번호

        int PAGEMAX		= 10;																						// 한페이지에 출력되는 목록 수
        int INDEXMAX	= 10;																						// 한번에 출력되는 인덱스의 수
		int maxcnt=0;
		if(!"".equals(instituCd)){
			instituCd=instituCd.trim();
			//maxcnt=ctl.countCTS(instituCd);
		}
        int maxrow		= 0;																						// 한페이지에 출력될 목록 수 (마지막 페이지를 위하여)
        int maxindex	= 0;	
		//if(maxcnt > 0){
			ett=ctl.selectStatusRegist(instituCd, page_no, PAGEMAX);	            // 조회 Bean 호출
		//}
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

%>

<html>
    <head>
        <title>Tra cứu tình trạng đăng ký Bên mời thầu</title><!-- 인증서신청 승인목록 조회 -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
        <link href="http://muasamcong.mpi.gov.vn/css/TA.css" rel="stylesheet" type="text/css" />
        <script language="javascript" src="../js/UM.js"></script>
        <script language="javascript" src="../calendar/js/calendar.js"></script>

    </head>

    <body>
        <div class="col-750 clearfix last">
            <h1 class="pageTitle"><i class="icon-title"></i>Tra cứu tình trạng đăng ký Bên mời thầu</h1>
<!--            <div class="contenttable">-->
                <form name="fm">
                    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <td height="4" colspan="4" class="line"></td>
                        </tr>
                        <tr>
                            <td style="width: 20%" class="tdar"><!-- 수요기관명 --> Mã cơ quan</td>
                            <td class="tdb" >
                                <input type="text" name="instituCd" value="<%=instituCd%>" class="read" size="20" style="width: 100%" />
                            </td>
                            <td class="tdar" style="text-align: center;" >
                                <input type="button" value=" Tìm " class="commonbutton" onclick="javascript:checkform();" />
                            </td>
                        </tr>
                        <tr>
                            <td height="2" colspan="4" class="line"></td>
                        </tr>
                    </table>
            <%if(!"".equals(instituCd)){ %>
                    <br>
                    <table width="100%" border="0" cellpadding="2" cellspacing="1" class="tr">
                        <tr>
                            <td  align="left">
                            <%if(maxcnt > 0 && ett !=null && ett.length > 0 && ett[0] !=null){
                            	%>
                            	<b>Tên cơ quan: </b><%=ett[0].data[5] %>
                            <% }%>
                            </td>
                        </tr>
                    </table>
                    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr">
                        <tr>
                            <td height="4" class="line" colspan="6"></td>
                        </tr>
                        <tr height="30">
                            <td	width="2%"	class="tdac">STT </td>
                            <td			class="tdac">Mã cơ quan </td>
                            <td		class="tdac">Bước 1 </td>
                            <td		class="tdac">Bước 2 </td>
                            <td		class="tdac">Bước 3 </td>
                            <td		class="tdac">Bước 4 </td>
                        </tr>
                        <tr>
                            <td height="1" class="line" colspan="9"></td>
                        </tr>

                        <%for(int i=0; i < ett.length; i++) {%>
                        <tr class="<%= (i%2 == 1) ? "tdcc" : "tdbc"%>" >
                            <td><%=((page_no-1)*PAGEMAX)+i+1%></td>
                            <td><%=ett[i].data[0]%></td>
                            <td><%=ett[i].data[1]%></td>
                            <td	><%=ett[i].data[2]%></td>
                            <td	><%=ett[i].data[3]%></td>
                            <td	><%=ett[i].data[4]%></td>
                        </tr>
                        <%}%>
                        <tr>
                            <td height="2" class="line" colspan="9"></td>
                        </tr>
                    </table>

                    <!---------------------- 네비게이션 바 시작 ------------------------>



                    <%	if( maxcnt > 0 && ett.length > 0 ){	%>
                    <%
                            // Thực hiện việc phân trang và tạo link liên kết đến các trang
                            out.println(CommPageUtil.getNextPageIndexes(request, null, maxcnt, PAGEMAX,  page_no));
                    %>

                    <%	}	
            }%>

                    <input type="hidden" name="flag" value="S">
                </form>

            <div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
        </div>
    </body>
    <SCRIPT LANGUAGE="JAVASCRIPT">

        function checkform(){
               document.forms[0].target = "_self"
               document.forms[0].method = "post";
               document.forms[0].action = "UM_ADJ_GovrP021l.jsp"
               document.forms[0].submit();
            
            return;

        }

    
    
    </SCRIPT>
</html>
