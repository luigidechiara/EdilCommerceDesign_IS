<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="model.*,utils.*,java.util.*"%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<link href="/EdilCommerce_Design/css/header.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="/EdilCommerce_Design/script/autocomplete.js"></script>
</head>
<body>
	<%
	HttpSession headerSession = request.getSession(false);
	Boolean headerUserRole  =  null;
	Boolean headerAdminRole =  null;
	Boolean headerCataRole= null;
	Boolean headerMagRole=null;
	UserBean headerBean =  null;
	
	
	if(headerSession != null){
		headerUserRole = (Boolean) headerSession.getAttribute("userRole"); 
		headerAdminRole = (Boolean) headerSession.getAttribute("adminRole"); 
		headerBean = (UserBean) headerSession.getAttribute("loggedUser");
		headerCataRole=(Boolean)headerSession.getAttribute("cataRole");
		headerMagRole=(Boolean)headerSession.getAttribute("magRole");
	}
	
	@SuppressWarnings("unchecked")
	Collection<CategoriaBean> headerCollection = (Collection<CategoriaBean>) getServletContext().getAttribute("Categorie");
	%>
		
	<header>
		<div id="left"><%if(headerCataRole==null && headerMagRole==null){ %><a href="/EdilCommerce_Design/home.jsp" title="Home"><%} %><img alt="ECD_Logo" src="/EdilCommerce_Design/img/logo_mini.png"></a></div>
		<div id="centro"> <img alt="lente" src="/EdilCommerce_Design/img/lente.png" width=30px height=30px>
		<%if(headerCataRole==null && headerMagRole==null){ %><form action="<%=response.encodeURL("/EdilCommerce_Design/Search")%>" method="get"><%} %>
		<input id="search" type="text"  name="criterioRicerca" size="30" placeholder="Cerca in EdilCommerce Design" onkeyup="autocomp()">
		</form>
		<div id="suggerimenti"></div>
		</div>
		<div id = "rigth">
		<% 
		
		if(headerUserRole != null && headerAdminRole != null){
			if(headerUserRole.equals(true) || headerAdminRole.equals(true) ){
		%>
		<ul>
			<%if(headerAdminRole.equals(true)&& headerCataRole==null && headerMagRole==null) {
			%>
			<li><a href="<%=response.encodeURL("/EdilCommerce_Design/admin/admin.jsp")%>" title="Area dell'admin"><img alt="admin" src="/EdilCommerce_Design/img/admin.png"></a></li>
			<%
			}
			%>
			<%if(headerCataRole==null && headerMagRole==null){ %>
			<li><a href="<%=response.encodeURL("/EdilCommerce_Design/user/profilo.jsp")%>" title="Profilo di <%=headerBean.getUsername()%>"><img alt="profilo" src="/EdilCommerce_Design/img/profilo.png"></a></li>
			<%} %>
			<%if(headerCataRole==null || headerCataRole!=null && headerMagRole==null || headerMagRole!=null ){ %>
			<li><a href="<%=response.encodeURL("/EdilCommerce_Design/Logout")%>" title="Logout"><img alt="logout" src="/EdilCommerce_Design/img/logout.png"></a></li>
			<%} %>
			<%if(!headerAdminRole.equals(true) && headerCataRole==null) {
			%>
			<li><a href="<%=response.encodeURL("/EdilCommerce_Design/user/carrello.jsp")%>" title="Carrello">  <img alt="carrello" src="/EdilCommerce_Design/img/carrello.png"></a></li>
			<%
			}
			%>
		</ul>
		<% 	
				}else {
		%>
		<ul>
			<li><a href="<%=response.encodeURL("login.jsp")%>">Login</a></li>
			<li><a href="<%=response.encodeURL("registrazione.jsp")%>">Registrati</a></li>
		</ul>
		<%
				}
			} else {
		%>
		<ul>
			<li><a href="<%=response.encodeURL("login.jsp")%>">Login</a></li>
			<li><a href="<%=response.encodeURL("registrazione.jsp")%>">Registrati</a></li>
		</ul>
		<% 
		}
		%>
		</div>
	</header>
	<nav class="bottom" id="topNav">
	<%
		if(headerCollection != null && headerCataRole==null && headerMagRole==null){
			if(!headerCollection.isEmpty()) {
				Iterator<CategoriaBean> it = headerCollection.iterator();
				while(it.hasNext()){
					CategoriaBean catBean = it.next();
	%>
	<a href="<%=response.encodeURL("/EdilCommerce_Design/Search?criterioRicerca=" + catBean.getNome()) %>"><%=catBean.getNome() %></a>		
	<% 	
				}		
			}
		}
	%>
	<a href="javascript:void(0);" class="icon" onclick="myFunction()">
    <i class="fa fa-bars"></i>
  </a>
  
	</nav>
	<script>
function myFunction() {
  var x = document.getElementById("topNav");
  if (x.className === "bottom") {
    x.className += " responsive";
  } else {
    x.className = "bottom";
  }
}

</script>
</body>
</html>