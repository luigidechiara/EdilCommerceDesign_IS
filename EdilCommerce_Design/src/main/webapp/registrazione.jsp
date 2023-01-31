<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/EdilCommerce_Design/css/default.css" rel="stylesheet" type="text/css">
<link href="/EdilCommerce_Design/css/registrazione.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Registrazione</title>
</head>
<body>
	<div id="holder">
		
	<%@ include file="./header.jsp" %>
	<div id="body">
	<script type="text/javascript" src="/EdilCommerce_Design/script/profiloScripts.js"></script>
	<script type="text/javascript" src="/EdilCommerce_Design/script/regularExpressions.js"></script>
	<div class="all">
	<h1>CREA UN ACCOUNT</h1>
	<%
	HttpSession session = request.getSession(false);
	if(session != null) {
		Boolean uRole =  (Boolean) headerSession.getAttribute("userRole"); 
		Boolean aRole = (Boolean) headerSession.getAttribute("adminRole"); 
		if((aRole.booleanValue() || uRole.booleanValue())){
			response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/"));
		}
	}
	
	String error = (String) request.getAttribute("error");
	if (error != null && !error.equals("")) {
	%>
	<p style="color: red"><%=error%>
	<%
	}
	%>		
		<div class="container">		
		<form id="registrazione" method="post" action="<%=response.encodeURL("/EdilCommerce_Design/Registrazione")%>"onsubmit="event.preventDefault(); validateRegistrazione(this)">
			<div class="flex">
				  	<div class="col-50">
							<label for="nome"><i class="fa fa-user"></i> Nome</label>
							<input type="text" name="nome" placeholder="ex. Mario" required>
							
							<label for="cognome"><i class="fa fa-user"></i> Cognome</label>
							<input type="text" name="cognome" placeholder="ex. Rossi" required >
							
							<label for="username"><i class="fa fa-user"></i> Username</label>
							<input type="text" name="username" placeholder="ex. Rossi" required >
							
							<label for="email"><i class="fa fa-envelope"></i> E-mail</label>
							<input type="email" name="email" placeholder="mario@ex.com" required >
							
							<label for="password"><i class="fa fa-lock"></i> Password</label>
							<input id="pass" type="password" name="password" placeholder="Password" required onkeyup="controllaPass('pass', 'confermaPass', 'salva')" >
							
							<label for="confermaPass"><i class="fa fa-lock"></i> Conferma Password</label>
							<input id="confermaPass" type="password" name="confermaP" placeholder="Password" required onkeyup="controllaPass('pass', 'confermaPass', 'salva')">
					</div>
							
					<div class="col-50">		
							<label for="indirizzo"><i class="fa fa-address-card-o"></i> Indirizzo</label>
							<input type="text" name="indirizzo" placeholder="ex. via demanio 7/1" required >
							
							<label for="telefono"><i class="fa fa-phone"></i> Telefono</label>
							<input type="text" name="telefono" placeholder="ex. 320-3895666" required >
						
							<label for="citta"><i class="fa fa-institution"></i> Città</label>
							<input type="text" name="citta" placeholder="Salerno" required >
							
							<label for="cap"><i class="fa fa-home"></i> Cap</label>
							<input type="text" name="cap" placeholder="82345" required >
							
							<label for="stato"><i class="fa fa-home"></i> Stato</label>
							<input type="text" name="stato" placeholder="IT" required >
							
					</div>
							<br><div class="all"><input type="submit" id="salva" value="Registrati">&nbsp;<input type="reset" value="Reset"></div>
			</div>
		</form>	
	</div>
	<a href="<%=response.encodeURL("home.jsp")%>">Torna alla home</a>
	</div>
	</div>
	<%@ include file="./footer.jsp" %>	
	</div>
</body>
</html>