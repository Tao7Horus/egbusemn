<%@page language="java" %> 
<%@page contentType="text/html; charset=UTF-8" %> 
<%@ page errorPage="../jsp/common/jspToError.jsp?type=0&url=&message=" %> 
<%@page import="java.io.*, java.util.*, beans.*, servlets.*, entity.*,common.*,common.util.CommUtil" %> 
<%@ include file = "../jsp/common/fnUmCommon.jsp" %>
 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CA Management</title> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<link rel="stylesheet" type="text/css" href="http://muasamcong.mpi.gov.vn/css/TA.css">
<link href="http://muasamcong.mpi.gov.vn/css/pagetitle_style.css" rel="stylesheet" type="text/css" />
  
</head> 

<script language="javascript">

function Init()
{
    var Ret;

    Ret = document.signGATE.SetCommonInfoFromVal( "ppca.mpi.gov.vn", 4502,
                                                  "ppca.mpi.gov.vn", 389,
                                                  "ppca.mpi.gov.vn", 389,
                                                  "cn=eBidCA,ou=VietnamCA,o=GCA,c=VN",
                                                  "no", 
                                                  "1.2.704.100001.5.1.1.1|1.2.704.100001.5.1.1.2|1.2.704.100001.5.1.1.3" );
    if ( Ret != 0 )
    {
         alert( "Failed to environment setting." );
         return false;
    }
    else
    {
         return true;
    }
                                               
}

function IssueCert()
{
   var ret;
   
   ret = document.signGATE.AxIssueCertificate();
   if( ret != 0 )
   {
		alert("Step 3 had to cancel this step, you can not do to Step 4: Sign user digital certificates");
   }
   else
   {
	   	alert("Step 3 successful, Now system go to Step 4: Sign user digital certificates");
	   	document.location = 'http://muasamcong.mpi.gov.vn:8070/RA/UM_RAJ_ConuC010i_en.jsp?oper=c&del=n&regGubun=new';
   }
   return;
}
</script>


<body onLoad="javascript:Init();IssueCert();">
<div class="col-750 clearfix last">
	<h1 class="pageTitle"><i class="icon-title"></i>CA Management</h1>

				<table width="100%" border="0" cellspacing="0" cellpadding="0"> 
					<tr><td><jsp:include page="/indexFlow2.jsp?gubun=2&req=13" /></td></tr>
				</table> 
				
<br> 
    <table width="100%" border="0" cellspacing="1" cellpadding="2" class="tr"> 
        <tr> 
            <td class="tdb" > 
               <!--  <p><font color = "red"> <a href="javascript:IssueCert();">▷  Nhận chứng thư số.</a>  -->
  					
  					<b><font color="red">Attention:</font></b>.<br>
☞ Enter the Certificate Authority approval code and the Reference number<br>
1. Make sure that the entered code is correct, be aware that the lower case letter and capital letter are distinctive<br>
2. Check the space in the entered code and eliminate if needed<br>
☞ Enter the Certificate Authority password<br>
1. The password is created by the user with minimum 8 characters, including numbers and letters with no more than 3 duplicate characters<br>
2. Before entering the password, please make sure that the Vietnamse keyboard is turned off<br>
☞ After receiving the Certificate Authority<br>
1. The Certificate authority is stored in the folder named Vietnam, for example if you have store the Certificate Authority in disc D, the link will be: D:\Vietnam.<br>
2. The Certificate Authority could be copied and used in other computers/laptops<br>
  					
            </td> 
        </tr> 
    </table> 

       <div class="wrapperfoot"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></div>
     </div>
 
         <object id="signGATE" 
      classid="CLSID:A3A70506-C8BD-41f9-9709-F63CEE9C238D"
      codebase="http://muasamcong.mpi.gov.vn:8070/cab/AxSignGATEP.cab#Version=3,1,0,15" 
      width= Document.body.clientWidth
      height= Document.body.clientHeight>
    </object>


</body> 
</html>
