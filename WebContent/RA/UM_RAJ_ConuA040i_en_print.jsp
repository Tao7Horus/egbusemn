<%--
/********************************************************************************/
/* Program ID         	: UM_RAJ_ConuA040i.jsp			                        */
/* Program Explanation	: Màn hình hiển thị thông tin sau khi đăng ký			*/
/* Program Summary  	: Màn hình hiển thị thông tin sau khi đăng ký			*/ 
/* Relation Program   	: 		   												*/
/* 						: 														*/ 
/*                     													   		*/
/* Table              	: 											       		*/	
/********************************************************************************/
/* Customizing Composer : MR. ANHDN 23.06.2009                      	  		*/
/********************************************************************************/	
--%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java"
	import="java.io.*,java.util.*,common.*, java.sql.*"%>
<jsp:useBean id="nationalCtrl" class="beans.NationalRefDAO" scope="page" />
<!-- Danh mục phân loại doanh nghiệp -->
<jsp:useBean id="clsList" class="dao.UM_RAB_ListStdCls" scope="page" />
<!-- Danh mục phân loại nghành nghề -->
<jsp:useBean id="recBidAgentDAO" class="dao.UM_RAB_RecBidAgent" scope="page" />
<jsp:useBean id="recEnterStdClsDAO" class="dao.UM_RAB_RecEnterStdCls" scope="page" />
<jsp:useBean id="recPubInstituCertDAO" class="dao.UM_RAB_RecPubInstituCert" scope="page" />
<jsp:useBean id="recReprDAO" class="dao.UM_RAB_RecRepr" scope="page" />
<jsp:useBean id="recSupplierEnterMastDAO" class="dao.UM_RAB_RecSupplier" scope="page" />

<%
	String bizRegNo = request.getParameter("bizRegNo");
	//String recvCode = request.getParameter("recvCode");
	
	Trx tr = null;
	Connection conn = null;
	CommEntity [] licenseList = null;
	CommEntity [] reprList = null;
	CommEntity [] stdClsList = null;
	CommEntity bidAgent = null;
	OneRowEntity recSupplierEnterMast = null;
	//OneRowEntity recPubInstituCert = null;
	//String recvNo = "";
	//String appReqCode = "";
	try {
		tr = new Trx(this);
		conn = tr.getConnection();	
			
		//Lấy thông tin [Thông tin chung] 
		recSupplierEnterMast = recSupplierEnterMastDAO.selectDetail(bizRegNo, conn);
		
		//Lấy thông tin [Thông tin người phụ trách]		
		reprList = recReprDAO.getList(bizRegNo, conn);
		
		//Lấy thông tin [Thông tin mã ngành nghề]		
		stdClsList = recEnterStdClsDAO.getList(bizRegNo, conn);
		
		//Lấy thông tin [Người đại diện dự thầu] 
		bidAgent = recBidAgentDAO.getList(bizRegNo, conn)[0];
		
		
		//Lấy thông tin [Đăng ký chứng nhận số]
	//	recPubInstituCert = recPubInstituCertDAO.selectDetail(recvCode, conn);	
	//	appReqCode = recPubInstituCert.getDataFromName("APPROVE_REQ_CD") ;	
	} catch(Exception ex){
		if (conn != null)
			try {
				conn.rollback();
			} catch (Exception exm1) {
			}
		if (conn != null)
			try {
				conn.setAutoCommit(true);
			} catch (Exception exm1) {
			}
			return;
	} finally {
		if (conn != null)
		try {
			tr.close();
		} catch (Exception exf) {
			exf.printStackTrace();
		}
	}
 %>
<html>
	<head>
		<title>Đăng kí nhà thầu</title>
		<meta http-equiv='Content-Type' Content='text/html; charset=utf-8'>
		<link rel="stylesheet" type="text/css" href="../css/UM.css">
		<link rel="stylesheet" type="text/css" href="http://muasamcong.mpi.gov.vn/css/footer.css">
		<link href="../css/TA.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<form name="comInfoFrm">
		<table width="823" border="0">
			<tr>
				<td height="4" class="LINE" colspan="4" />
			</tr>
			<tr>
				<td width="20%" class="tdar"><font color="red">*</font> Số văn bản</td>
				<td width="30%" class="tdb"><%=recSupplierEnterMast.getDataFromName("DOC_NO")%></td>
				<td width="20%" class="tdar"><font color="red">*</font> Ngày soạn thào</td>	
				<td width="30%" class="tdb"><%=recSupplierEnterMast.getDataFromName("DOC_CREAT_DT")%></td>
			</tr>
			<tr>
				<td height="2" class="LINE" colspan="4" />
			</tr>
		</table>
		<br>
		<br>	
		<table width="823" border="0" cellspacing="1" cellpadding="2" class="tr">
			<tr>
				<th align="left" colspan="4">[Thông tin chung]</th>
			</tr>
			<tr>
				<td height="4" class="LINE" colspan="4" />
			</tr>
			<tr>
				<td width="20%" class="tdar"><font color="red">*</font> Số ĐKKD</td>
				<td width="30%" class="tdb"><%=recSupplierEnterMast.getDataFromName("BIZ_REG_NO")%></td>
				
				<td width="20%" class="tdar"><font color="red">*</font> Ngày ĐKKD</td>	
				<td width="30%" class="tdb"><%=recSupplierEnterMast.getDataFromName("REG_DT")%></td>
			</tr>
			<tr>
				<td width="20%" class="tdar"><font color="red">*</font> Tên nhà thầu</td>
				<td class="tdb" colspan="3"><%=recSupplierEnterMast.getDataFromName("BIZ_NM")%></td>
			</tr>
			<tr>	
				<td width="20%" class="tdar">&nbsp;Tên tiếng Anh</td>
				<td class="tdb" colspan="3"><%=recSupplierEnterMast.getDataFromName("BIZ_EN_NM")%></td>
			</tr>
			<tr>
				<td width="20%" class="tdar"><font color="red">*</font> Lĩnh vực kinh doanh</td>
				<td width="30%" class="tdb">
					<% 
			    		String bizCLSCode = recSupplierEnterMast.getDataFromName("BIZ_CLS"); 
			    		String bizCLSLabel = "";
			    		if (bizCLSCode != null && bizCLSCode.length() != 0) {
			    			if(bizCLSCode.indexOf('1') != -1) bizCLSLabel += "Hàng hóa, ";
			    			if(bizCLSCode.indexOf('3') != -1) bizCLSLabel += "Xây lắp, ";
			    			if(bizCLSCode.indexOf('5') != -1) bizCLSLabel += "Tư vấn, "; 
			    		}
			    	%>
			    	<%=bizCLSLabel.substring(0, bizCLSLabel.lastIndexOf(","))%>
				</td>
				<td width="20%" class="tdar"><font color="red">*</font> Phân loại doanh nghiệp</td>
				<td width="32%" class="tdb">
					<% 
						String bizCLS1Code = recSupplierEnterMast.getDataFromName("BIZ_CLS1");
					 	String bizCLS1Label = "";
					 	if ("01".equals(bizCLS1Code)) bizCLS1Label = "Doanh nghiệp Tư nhân";
					 	else if ("02".equals(bizCLS1Code)) bizCLS1Label = "Công ty TNHH";
					 	else if ("03".equals(bizCLS1Code)) bizCLS1Label = "Công ty Cổ phần";
					 	else if ("04".equals(bizCLS1Code)) bizCLS1Label = "Công ty Hợp dan";
					 	else if ("05".equals(bizCLS1Code)) bizCLS1Label = "Hợp tác xã";
					 	else if ("06".equals(bizCLS1Code)) bizCLS1Label = "Công ty Liên doanh";
					 	else if ("07".equals(bizCLS1Code)) bizCLS1Label = "Công ty 100% vốn nước";    
					%>
					<%=bizCLS1Label%>
				</td>
			</tr>
			<tr>
				<td width="20%" class="tdar"><font color="red">*</font> Số nhân viên</td>
				<td width="30%" class="tdb"><%=recSupplierEnterMast.getDataFromName("EMPLOYEE_COUNT")%></td>
				<td width="20%" class="tdar"><font color="red">*</font> Tiền vốn</td>
				<td width="30%" class="tdb"><%=recSupplierEnterMast.getDataFromName("CAPITAL")%></td>		
			</tr>
			<tr>
				<td width="20%" class="tdar"><font color="red">*</font> Số điện thoại</td>
				<td width="30%" class="tdb"><%=recSupplierEnterMast.getDataFromName("PHONE_NO")%></td>
				<td width="20%" class="tdar"> Số FAX</td>
				<td width="30%" class="tdb"><%=recSupplierEnterMast.getDataFromName("FAX")%></td>
			</tr>
			
			<tr>
				<td width="20%" class="tdar"><font color="red">*</font> Địa chỉ</td>
				<td colspan="3" class="tdb"><%=recSupplierEnterMast.getDataFromName("ADDR")%> </td>
			</tr>
			<tr>
				<td  class="tdar"> Trang web</td>
				<td colspan="3" class="tdb"><%=recSupplierEnterMast.getDataFromName("WEBSITE")%></td>
			</tr>		
			<tr>
				<td width="20%" class="tdar"><font color="red">*</font> Tỉnh / thành phố</td>
				<td width="30%" class="tdb">
					<% 
			    		String aCode = recSupplierEnterMast.getDataFromName("AREA_CD"); 
			    		List areas = nationalCtrl.getNameByCD("J05", aCode);			    		
			    	%>
			    	<%=areas.get(0)%>
				</td>
				<td width="20%" class="tdar"><font color="red">*</font> Quốc gia</td>
				<td width="30%" class="tdb">
					<% 
			    		String code = recSupplierEnterMast.getDataFromName("NATIONALITY"); 
			    		List names = nationalCtrl.getNameByCD("I99", code);			    		
			    	%>
			    	<%=names.get(0)%>
				</td>
			</tr>
			<tr>
				<td height="2" class="LINE" colspan="4" />
			</tr>
		</table>
		</form>
		<form name="reprFrm">		
		<table width="823" id="tblCeo">
			<tr>
				<th align="left" colspan="7" style="font-size: 11px;">[Thông tin lãnh đạo cơ quan / doanh nghiệp]</th>
			</tr>
			<tr height="1">
				<td height="4" class="LINE" colspan="7" />
			</tr>
			<tr>
				<td class="tdac" width="5%">STT</td>
				<td class="tdac" width="20%">Tên người lãnh đạo</td>
				<td class="tdac" width="10%">Số CMND</td>
				<td class="tdac" width="10%">Số điện thoại</td>
				<td class="tdac" width="25%">Email</td>
				<td class="tdac" width="15%">Kiểu người lãnh đạo</td>
				<td class="tdac" width="15%">Đại diện hợp pháp cao nhất</td>
			</tr>	
			<% for (int i = 0; i < stdClsList.length; i++) { %>
			<tr>	
				<td class="tdb"><%=i + 1%></td>
				<td class="tdb"><%= reprList[i].data[1]%></td>
				<td class="tdb"><%= reprList[i].data[2]%></td>
				<td class="tdb"><%= reprList[i].data[3]%></td>
				<td class="tdb"><%= reprList[i].data[4]%></td>
				<td class="tdb">
					<% 
			    		String reprType = "";
			    		if ("0".equals(reprList[i].data[5])) reprType = "Chính thức";
			    		else if ("1".equals(reprList[i].data[5])) reprType = "Không chính thức";
			    	 %>
			    	<%= reprType%>
				</td>
				<td class="tdb">
					<% 
				    	boolean isMastRepr = false;
				    	if ("Y".equals(reprList[i].data[6])) isMastRepr = true;
				    	if (isMastRepr) {  
			    	%>
			    			<input type="checkbox" checked="checked" readonly="readonly" disabled="disabled">
			    	<%  } else {%>
			    			<input type="checkbox" readonly="readonly" disabled="disabled">
			    	<%  } %>
				</td>
			</tr>	
			<% } %>	
			<tr height="1">
				<td colspan="7" class="line"></td>
			</tr>
		</table>
		<table width="823" cellspacing="1" cellpadding="2" class="tr">
			<tr>
				<td height="2" class="LINE" colspan="4" />
			</tr>
		</table>
		</form>	
		<form name="stdCLSFrm">		
		<table width="823" id="tblStdCls">
			<tr>
				<th align="left" colspan="9" style="font-size: 11px;">[Thông tin ngành nghề]</th>
			</tr>
			<tr height="1">
				<td height="4" class="LINE" colspan="9" />
			</tr>
			<tr>
				<td class="tdac" width="5%">STT</td>
				<td class="tdac" width="15%">Tên Ngành nghề</td>
				<td class="tdac" width="10%">Mã ngành nghề</td>
				<td class="tdac" width="10%">Số đăng ký</td>
				<td class="tdac" width="15%">Ngày đăng ký</td>
				<td class="tdac" width="10%">Số tiền bảo lãnh</td>
				<td class="tdac" width="10%">Năm đăng ký tiền bảo lãnh</td>
				<td class="tdac" width="10%">Là ngành nghề tiêu biểu</td>
				<td class="tdac" width="15%">Ngày hết hiệu lực</td>
			</tr>	
			<% for (int i = 0; i < stdClsList.length; i++) { %>
			<tr>
				<td class="tdb"><%= i + 1%></td>
				<td class="tdb">
					<% 
			    		String stdClsCode = stdClsList[i].data[0]; 
			    		List stdClsName = nationalCtrl.getNameByCD("GZ8", stdClsCode);			    		
			    	%>
			    	<%=stdClsName.get(0)%>
				</td>
				<td class="tdb"><%= stdClsList[i].data[0]%></td>
				<td class="tdb"><%= stdClsList[i].data[1]%></td>
				<td class="tdb"><%= stdClsList[i].data[2]%></td>
				<td class="tdb"><%= stdClsList[i].data[3]%></td>
				<td class="tdb"><%= stdClsList[i].data[4]%></td>
				<td class="tdb">
				 	<% 
			    	boolean mastStdClsYn = false;
			    	if ("Y".equals(stdClsList[i].data[5])) mastStdClsYn = true;
			    	if (mastStdClsYn) {  
		    		%>
		    			<input type="checkbox" checked="checked" readonly="readonly" disabled="disabled">
		    		<%  } else {%>
		    			<input type="checkbox" readonly="readonly" disabled="disabled">
		    		<%  } %>
				</td>
				<td class="tdb"><%= stdClsList[i].data[6]%></td>
			</tr>		
			<% } %>
			<tr height="1">
				<td colspan="9" class="line"></td>
			</tr>
		</table>
		<table width="823" cellspacing="1" cellpadding="2" class="tr">
			<tr>
				<td height="2" class="LINE" colspan="4" />
			</tr>
		</table>
		</form>			
		<form name="bizAgentFrm">
		<table width="823" border="0" cellspacing="1" cellpadding="2" class="tr">
			<tr>
				<th align="left" colspan="4">[Người đại diện dự thầu]</th>
			</tr>
			<tr>
				<td height="4" class="LINE" colspan="4" />
			</tr>
			<tr>
				<td class="tdar" width="20%"><font color="red">*</font> Tên người đại diện</td>
				<td class="tdb" width="30%"><%=bidAgent.data[0]%></td>
				<td class="tdar" width="20%"><font color="red">*</font> Số CMND</td>
				<td class="tdb" width="30%"><%=bidAgent.data[1]%></td>
			</tr>
		
			<tr>
				<td class="tdar" width="20%"><font color="red">*</font> Chức vụ</td>
				<td class="tdb" width="30%"><%=bidAgent.data[2]%></td>
				<td class="tdar" width="20%"><font color="red">*</font> Phòng ban</td>
				<td class="tdb" width="30%"><%=bidAgent.data[3]%></td>
			</tr>
			<tr>
				<td class="tdar" width="20%"><font color="red">*</font> Email</td>
				<td class="tdb" width="30%"><%=bidAgent.data[4]%></td>
				<td class="tdar" width="20%"><font color="red">*</font> Điện thoại</td>
				<td class="tdb" width="30%"><%=bidAgent.data[5]%></td>
			</tr>
			<tr>
				<td class="tdar" width="20%"><font color="red">*</font> Di động</td>
				<td class="tdb" width="30%"><%=bidAgent.data[6]%></td>
				<td class="tdar" width="20%">FAX</td>
				<td class="tdb" width="30%"><%=bidAgent.data[7]%></td>
			</tr>
			<tr>
				<td height="2" class="LINE" colspan="4" />
			</tr>
		</table>
		</form>		
		<form name="CARegisterFrm">
		<table width="823" border="0" cellspacing="1" cellpadding="2" class="tr">
			<tr>
				<th align="left" colspan="4">[Đăng ký chứng nhận số]</th>
			</tr>
			<tr>
				<td height="4" class="LINE" colspan="4"></td>
			</tr>
			<tr>
				<td class="tdar" width="20%" height="25"><font color="red">*</font> Cơ quan chứng nhận công</td>
				<td class="tdb" width="30%"><input type="radio" name="liceS" value="" checked="checked" readonly="readonly">MPI</td>
				<td class="tdar" width="20%" height="25"><font color="red">*</font> Chi nhánh phê duyệt</td>
				<td class="tdb"  width="30%">Cục quản lý đấu thầu</td>
			</tr>
			<tr>
				<td class="tdar" width="20%"><font color="red">*</font> Tên người đại diện</td>
				<td class="tdb" width="30%"><%//=recPubInstituCert.getDataFromName("REPR_NM")%></td>
				<td class="tdar" width="20%"><font color="red">*</font> Số CMND</td>		
				<td class="tdb" width="30%"><%//=recPubInstituCert.getDataFromName("REPR_IDENT_NO")%></td>
			</tr>
			<tr>
				<td class="tdar" width="20%"><font color="red">*</font> ID yêu cầu cấp chứng nhận số</td>
				<td class="tdb" colspan="3" width="80%">
					<%//=recPubInstituCert.getDataFromName("USER_ID")%> 
				</td>
			</tr>
			<tr>
				<td height="2" class="LINE" colspan="4"></td>
			</tr>
		</table>
		<br/>
		<br/>
		<table width="823" border="0">
			<tr>
				<td height="4" class="LINE" colspan="2" />
			</tr>
			<tr>
				<td class="tdar" width="20%" >Mã số tiếp nhận </td>
				<td class="tdb" width="80%"><%//=recvCode %></td>
			</tr>
			<tr>
				<td height="2" class="LINE" colspan="2" />
			</tr>
		</table>
		<br>
		<br>
		<table width="823" border="0" cellspacing="1" cellpadding="2" class="tr">
			<tr>
				<td align="center" height="24">				 
				<input type="button" class="commonbutton" value="Quay lại" onclick='history.go(-1);'/>
				</td>
			</tr>
			<tr> 
		   		<td colspan="3" height="50"><script language="javascript" src="http://muasamcong.mpi.gov.vn/js/copyright.js"></script></td> 
		   	</tr>      
		</table>		
		</form>		
	</body>	
</html>	