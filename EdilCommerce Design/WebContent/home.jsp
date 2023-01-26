<%@page import="java.util.Iterator"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Collection"%>
<%@page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.*,javax.sql.*,utils.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta lang="it">
	<link href="/EdilCommerce_Design/css/default.css" rel="stylesheet" type="text/css">
	<link href="/EdilCommerce_Design/css/home.css" rel="stylesheet" type="text/css">
	<title>Benvenuto in Edil Commerce Design</title>
	<script type="text/javascript" src="/EdilCommerce_Design/script/autocomplete.js"></script>
</head>
<body>	
	<script type="text/javascript" src="/EdilCommerce_Design/script/home.js"></script>
	
	<div id="holder">
		
	<%
	HttpSession session = request.getSession(false);
	Boolean userRole  =  null;
	Boolean adminRole =  null;
	UserBean bean =  null;
	if(session != null){
		userRole = (Boolean) session.getAttribute("userRole"); 
		adminRole = (Boolean) session.getAttribute("adminRole"); 
		bean = (UserBean) session.getAttribute("loggedUser");
	}
	Collection<CategoriaBean> collection = (Collection<CategoriaBean>) getServletContext().getAttribute("Categorie");
	%>
	 
		<header>
		<div id="left"><abb title="Home"><a href="/EdilCommerce_Design/home.jsp"><img alt="ECD_Logo" src="/EdilCommerce_Design/img/logo_mini.png"></a></abb></div>
		<div id="center">Benvenuto in Edil Commerce Design</div>
		<% 
		if(userRole != null && adminRole != null){
			if(userRole.equals(true) || adminRole.equals(true) ){
		%>
		<div id="rigth">
			<ul>
				<%if(adminRole.equals(true)) {
				%>
				<li><a href="<%=response.encodeURL("/EdilCommerce_Design/admin/admin.jsp")%>" title="Area dell'admin"><img alt="admin" src="/EdilCommerce_Design/img/admin.png"></a></li>
				<%
				}
				%>
				
				<li><a href="<%=response.encodeRedirectURL("/EdilCommerce_Design/user/profilo.jsp")%>" title="Profilo di <%=bean.getUsername()%>"><img alt="profilo" src="/EdilCommerce_Design/img/profilo.png"></a></li>
				
				<li><a href="<%=response.encodeRedirectURL("/EdilCommerce_Design/Logout")%>" title="Logout"><img alt="logout" src="/EdilCommerce_Design/img/logout.png"></a></li>
				<%if(!adminRole.equals(true)) {
				%>
				<li><a href="<%=response.encodeRedirectURL("/EdilCommerce_Design/user/carrello.jsp")%>" title="Carrello"><img alt="carrello" src="/EdilCommerce_Design/img/carrello.png"></a></li>
				<%
				}
				%>
			</ul>
		</div>
		<% 	
				} else {
		%>
		<div id="rigth">
			<ul>
				<li><a href="<%=response.encodeURL("login.jsp")%>">Login</a></li>
				<li><a href="<%=response.encodeURL("registrazione.jsp")%>">Registrati</a></li>
			</ul>
		</div>
		<%
			}
		} else {
			
		%>
		<div id="rigth">
			<ul >
				<li><a class="login" href="login.jsp">Login</a></li>
				<li><a class="login" href="registrazione.jsp">Registrati</a></li>
			</ul>
		</div>
		<%
		}
		%>
		</header>
		<div id="body">
		
		
		<img alt="logo-edil" src="/EdilCommerce_Design/img/logo.png" id="mainLogo">		
		<div class="center">
		<form action="<%=response.encodeURL("/EdilCommerce_Design/Search")%>" method="get">
		<input id="search" type="text"  name="criterioRicerca" size="30" placeholder="Cerca in EdilCommerce Design" onkeyup="autocomp()">
		</form> 
		<div id="suggerimenti"></div>
		</div>
	 
	
	<script type="text/javascript" src="/EdilCommerce_Design/script/scroll.js"></script>
	
	<div class="slideCategorie">
		<input type="button" value="&lt" onclick="scrollL()">
		<div id="categorie">
			<%
				
				if(collection != null){
					if(!collection.isEmpty()) {
						Iterator<CategoriaBean> it = collection.iterator();
						while(it.hasNext()){
							CategoriaBean catBean = it.next();
			%>
			<a title="<%=catBean.getNome() %>" href="<%=response.encodeURL("/EdilCommerce_Design/Search?criterioRicerca=" + catBean.getNome()) %>"><img alt="<%=catBean.getNome() %>" src="<%=catBean.getImmagine()%>"></a>
				
			<% 			
						}		
					}
				}
			%>
		</div>
		<input type="button" value="&gt" onclick="scrollR()">
	</div>
	<hr>
	<div id="center2">Edil Commerce Design</div>
	<br>
	<div class="chiSiamo">
	<h2>Chi siamo:</h2>
	<p>
		Edil Commerce Design &egrave; il pi&ugrave; grande eCommerce di articoli per l'edilizia e non...<br>
		Da circa 20 anni ci occupiamo di ricercare e vendere i migliori prodotti esistenti sul mercato.<br>
		Mettendo sempre in primo piano la felicit&agrave; dei nostri clienti, garantendo materiali di prima scelta.
	</p>
	
	</div>
	
	<div class="slideshow-container">

		<div class="mySlides fade">
		  <div class="numbertext">1 / 3</div>
		  <img src="/EdilCommerce_Design/img/home1.jpg" >
		</div>
		
		<div class="mySlides fade">
		  <div class="numbertext">2 / 3</div>
		  <img src="/EdilCommerce_Design/img/home2.jpg" >
		</div>
		
		<div class="mySlides fade">
		  <div class="numbertext">3 / 3</div>
		  <img src="/EdilCommerce_Design/img/home3.jpg" >
		</div>
		
		</div>
		<br>
		
		<div style="text-align:center">
		  <span class="dot"></span> 
		  <span class="dot"></span> 
		  <span class="dot"></span> 
	
	</div>
	<div class="cheFacciamo">
	<h2>Che facciamo:</h2>
		<p>
		    Edil Commerce Design, nasce per aiutare i clienti nella scelta per la realizzazione della casa ideale.<br>
			Offre ottimi meccanismi per i pagamenti, per i tracciamenti degli ordini e per le ricerche pi&ugrave; minuziose,
			con la comodit&agrave; di effettuare tutto da casa con PC e smartphone.
		</p>
	
	</div>
	<script type="text/javascript">
		var slideIndex = 0;
		showSlides();
	</script>

	<%@ include file="./footer.jsp" %>
	</div>
</body>
</html>