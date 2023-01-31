<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.SQLException" import="java.text.*"%>
<%@page session="false" %>

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

UserBean bean = (UserBean) session.getAttribute("loggedUser");
if (bean == null) {
	session.removeAttribute("userRole");
	session.removeAttribute("adminRole");
	session.invalidate();
	response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/login.jsp"));
	return;
}
Carrello<ArticoloBean> carrello = (Carrello<ArticoloBean>) session.getAttribute("Carrello");
if(carrello.getItems().isEmpty()) {
	response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/"));
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<meta lang="it">
	<link href="/EdilCommerce_Design/css/default.css" rel="stylesheet" type="text/css">
    <link href="/EdilCommerce_Design/css/checkout.css" rel="stylesheet" type="text/css">
	<title>Pagamento</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div id="holder">
		
		<%@ include file="../header.jsp" %>
		<script type="text/javascript" src="/EdilCommerce_Design/script/regularExpressions.js"></script>
		
		<div id="body">
		<h2>Riepilogo carrello</h2>
	
	<div class="row">
	  <div class="col-75">
	    <div class="container">
	      <form  method="post" action="<%=response.encodeURL("/EdilCommerce_Design/ComputaOrdine")%>" onsubmit="event.preventDefault(); validateCheckout(this)">
	      
	        <div class="row">
	          <div class="col-50">
	            <h3>Indirizzo di fatturazione</h3>
	            <label for="nome"><i class="fa fa-user"></i> Nome</label>
	            <input type="text" id="nome" name="nome" placeholder="Mario" value="<%=bean.getNome()%>">
	            
	            <label for="cognome"><i class="fa fa-user"></i> Cognome</label>
	            <input type="text" id="cognome" name="cognome" placeholder="Rossi" value="<%=bean.getCognome()%>">
	            
	            <label for="email"><i class="fa fa-envelope"></i> Email</label>
	            <input type="text" id="email" name="email" placeholder="marioRossi@example.com" value="<%=bean.getEmail()%>">
	            
	            <label for="telefono"><i class="fa fa-phone"></i> Telefono</label>
	            <input type="text" id="telefono" name="telefono" placeholder="088-8888" value="<%=bean.getTelefono()%>">
	            
	            <label for="indirizzo"><i class="fa fa-address-card-o"></i> Indirizzo</label>
	            <input type="text" id="indirizzo" name="indirizzo" placeholder="via umberto I" value="<%=bean.getIndirizzo()%>">
	            
	            <label for="citta"><i class="fa fa-institution"></i> Citta</label>
	            <input type="text" id="citta" name="citta" placeholder="Salerno" value="<%=bean.getCitta()%>">
	
	            <div class="row">
	              <div class="col-50">
	                <label for="stato">Stato</label>
	                <input type="text" id="stato" name="stato" placeholder="IT" value="<%=bean.getStato()%>">
	              </div>
	              <div class="col-50">
	                <label for="cap">CAP</label>
	                <input type="text" id="cap" name="cap" placeholder="84085" value="<%=bean.getCap()%>">
	              </div>
	            </div>
	          </div>
			<script type="text/javascript" src="/EdilCommerce_Design/script/checkout.js"></script>
	          <div class="col-50">
	            <h3>Pagamento con carta o contrassegno</h3>
	             <label><h4><input type="radio" checked="checked" name="metodo" value="1" onclick="abilita()"> Carta </h4></label>
	             <label><h4><input type="radio"  name="metodo" value="2" onclick="disabilita()"> Contrassegno </h4></label>
	            <label for="fname">Carte Accettate</label>
	            <div class="icon-container">
	              <i class="fa fa-cc-visa" style="color:navy;"></i>
	              <i class="fa fa-cc-amex" style="color:blue;"></i>
	              <i class="fa fa-cc-mastercard" style="color:red;"></i>
	              <i class="fa fa-cc-discover" style="color:orange;"></i>
	            </div>
	            <label for="cnome">Intestatario della carta</label>
	            <input type="text" id="cnome" name="cnome" placeholder="Mario Rossi" required>
	            <label for="cnum">Numero carta</label>
	            <input type="text" id="cnum" name="cnum" placeholder="1111-2222-3333-4444" required>
	            <label for="expmonth">Validita (mese)</label>
	            <select name="expmonth" id="expmonth" required>
				  <option value="01">Gennaio</option>
				  <option value="02">Febbraio</option>
				  <option value="03">Marzo</option>
				  <option value="04">Aprile</option>
				  <option value="05">Maggio</option>
				  <option value="06">Giugno</option>
				  <option value="07">Luglio</option>
				  <option value="08">Agosto</option>
				  <option value="09">Settembre</option>
				  <option value="10">Ottobre</option>
				  <option value="11">Novembre</option>
				  <option value="12">Dicembre</option>
				</select>
	            <div class="row">
	              <div class="col-50">
	                <label for="expyear">Validita (anno)</label>
	                <input type="text" id="expyear" name="expyear" placeholder="2022" required>
	              </div>
	              <div class="col-50">
	                <label for="cvv">CVV</label>
	                <input type="text" id="cvv" name="cvv" placeholder="352" required>
	              </div>
	            </div>
	          </div>
	          
	        </div>
	        <input type="submit" value="Continua al checkout" class="btn">
	      </form>
	    </div>
	  </div>
	  <div class="col-25">
	    <div class="container">
		<%
		List<ArticoloBean> articoli = carrello.getItems();
		List<Integer> quantita = carrello.getQuantita();
		Iterator<ArticoloBean> it1 = articoli.iterator();
		Iterator<Integer> it2 = quantita.iterator();
		DecimalFormat df = new DecimalFormat("#.00");
			
		ArticoloBean aBean = new ArticoloBean();
		Integer q = 0;
		double totale = 0;
		%>
		<h4><a class="link-nero" href="<%= response.encodeURL("/EdilCommerce_Design/user/carrello.jsp")%>">Carrello</a> <span class="price" style="color:black"><i class="fa fa-shopping-cart"></i> <%=articoli.size()%></span></h4>
		<%
				
		while(it1.hasNext() && it2.hasNext()){
			aBean=it1.next();
			q=it2.next();
		%>
	      <p><%=q%> <a href="<%= response.encodeURL("/EdilCommerce_Design/articolo.jsp?articolo=" + aBean.getCodiceArticolo())%>"><%=aBean.getNome() %></a>  <span class="price"><%=df.format(aBean.getCosto() * q)%>&euro;</span></p>
	    <%
	   	 totale = totale + (q * aBean.getCosto());
		}
	    %>    
	      <hr>
	      <p>Totale <span class="price" style="color:black"><%=df.format(totale)%>&euro;</span></p>
	    </div>
	  </div>
	</div>
	</div>
	<%@ include file="../footer.jsp" %>
	</div>
</body>
</html>