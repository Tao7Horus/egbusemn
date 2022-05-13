<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="/jsp/common/jspToError.jsp?type=1&url=&message=" %>
<%@ page language="java" import="java.io.*, java.util.*, beans.*, servlets.*, entity.*, LOGIN.*, common.*, common.util.*" %>

<%@ include file = "jsp/common/fnUmCommon.jsp" %>

<jsp:useBean id="Hutil" class="common.HttpUtil" scope="page" />
<jsp:useBean id="webutil" class="common.WebUtil" scope="page" />

<%
	String saupNo  = "";
	String procID  = "";
	String Gubun  = "";
	String Name  = "";
	String MName  = "";
	String CertSeq  = "";
	String LType  = "";
	String BOffi  = "";

	// 沅��泥댄�
	UM_Auth_Check uac = new UM_Auth_Check(request, response);
	uac.checkCookie("g",null,null);

	saupNo = uac.getMCode();
	procID = uac.getID();
	Gubun = uac.getGubun();
	Name = uac.getName();
	MName = uac.getMName();
	CertSeq = uac.getCertSeq();
	LType = uac.getLType();
	BOffi = uac.getBOffi();

out.println("saupNo => " + saupNo + "<br>");
out.println("procID => " + procID + "<br>");
out.println("Gubun => " + Gubun + "<br>");
out.println("Name => " + Name + "<br>");
out.println("MName => " + MName + "<br>");
out.println("CertSeq => " + CertSeq + "<br>");
out.println("LType => " + LType + "<br>");
out.println("BOffi => " + BOffi + "<br>");
%>
