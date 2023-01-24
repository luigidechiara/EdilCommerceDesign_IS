<%@page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/EdilCommerce_Design/css/default.css" rel="stylesheet" type="text/css">
<link href="/EdilCommerce_Design/css/login.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script type="text/javascript" src="/EdilCommerce_Design/script/login.js"></script>

<title>Login</title>
</head>
<body class="all">
<div id="holder">
		
	<%@ include file="./header.jsp" %>
	<div id="body">
	<%
		HttpSession session = request.getSession(true);
		if(session != null) {
			String redirect = (String) session.getAttribute("loginRedirect");
			if(redirect == null) {
					redirect = request.getHeader("Referer");
					session.setAttribute("loginRedirect", redirect);
			}
			Boolean uRole =  (Boolean) session.getAttribute("userRole"); 
			Boolean aRole = (Boolean) session.getAttribute("adminRole"); 
			if((aRole.booleanValue() || uRole.booleanValue())){
				response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/"));
			}
		}
	%>
	<script type="text/javascript" src="/EdilCommerce_Design/script/login.js"></script>

	<img alt="logo-edil" src="/EdilCommerce_Design/img/logo.png" id="mainLogo">
	<div class="container">
		
			<form method="post" action="<%=response.encodeURL("/EdilCommerce_Design/Login") %>" >
				
					<h1 class="all">LOGIN</h1>
						<label for="username"><i class="fa fa-user"></i> Username</label>
						<input type="text" name="username" placeholder="ex. Rossi" required >
						
						<label for="password"><i class="fa fa-lock"></i> Password</label>
						<img id="showP" alt="show" src="/EdilCommerce_Design/img/occhio.png" onclick="mostraPasss()">
						<input id="pass" type="password" name="password" placeholder="Password" required >
							
					<div class="all"><br><input type="submit" value="Login">&nbsp;<input type="reset" value="Reset"></div>
				
			</form>
		
		<%
		String error = (String) request.getAttribute("error");
		if (error != null && !error.equals("")) {
		%>
		<p style="color: red"><%=error%>
		<%
		}
		%>
		
		<h4>Oppure</h4><br>
		<input type="button" value="Registrati" onclick="window.location.href='<%=response.encodeURL("/EdilCommerce_Design/registrazione.jsp")%>'">
		
	</div>
	</div>
	<%@ include file="./footer.jsp" %>
	</div>
</body>
</html>