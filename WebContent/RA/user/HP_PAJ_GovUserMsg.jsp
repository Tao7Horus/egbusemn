<%@ page language="java" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
        StringBuffer nextaction = new StringBuffer((String)request.getAttribute("nextaction"));
        String msg = (String)request.getAttribute("msg");
%>
<script language=javascript>
<%
        if ( msg != null && msg.length() > 0 ) {
                out.println("  alert('"+msg+"');");
        }

        if ( nextaction.toString().startsWith("parent") ) {
                out.println(nextaction.toString());
        }
        else {
                out.println("location.href=\""+nextaction.toString()+"\";");
        }
%>
</script>
