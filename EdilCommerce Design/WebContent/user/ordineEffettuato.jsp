<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="javax.sql.*,model.*,utils.*"%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/EdilCommerce_Design/css/default.css" rel="stylesheet" type="text/css">
<link href="/EdilCommerce_Design/css/ordineEffettuato.css" rel="stylesheet" type="text/css">
<title>Ordine Effettuato</title>
</head>
<body>
	<div id="holder">
		
	<%
	HttpSession session = request.getSession(false);
	if(session == null) {
		response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/login.jsp"));
		return;
	} else {
		Boolean userRole = (Boolean) session.getAttribute("userRole");
		if((userRole == null) || (!userRole.booleanValue())) {
			response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/login.jsp"));
			return;
		}
	}
	%>
	
	<%@ include file="../header.jsp" %>
	<div id="body">
	<%
	if(Integer.parseInt( request.getParameter("suc")) == 1) {
	%>
	
		<p>Ordine effettuato con successo.</p>
		<div class="rotate-scale-up">
		<img alt="successo" src="/EdilCommerce_Design/img/spunta-verde.png">
		</div>
		<p>Continua ad effettuare nuovi acquisti...</p>
		<p>Per vedere i tuoi acquisti vai nella tua aria personale <a href="<%=response.encodeURL("/EdilCommerce_Design/user/profilo.jsp")%>" title="Profilo di <%=headerBean.getUsername()%>"><img alt="profilo" src="/EdilCommerce_Design/img/profilo.png"></a>.</p>
	
	<%	
	} else {
	%>
		<p>Ordine non effettuato.</p>
	<%		
	}
	%>
	
	</div>
	<%@ include file="../footer.jsp" %>
	</div>
</body>
</html>