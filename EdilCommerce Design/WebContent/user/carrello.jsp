<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.text.*"%>
<%@page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="/EdilCommerce_Design/css/default.css">
<link type="text/css" rel="stylesheet" href="/EdilCommerce_Design/css/carrello.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Il tuo carrello</title>
</head>
<body>
	<div id="holder">
		
	<%@include file="../header.jsp" %>
	<div id="body">
	<script type="text/javascript" src="/EdilCommerce_Design/script/carrello.js"></script>
	
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
	Carrello<ArticoloBean> carrello = (Carrello<ArticoloBean>) session.getAttribute("Carrello");
	%>
	<div id="carrello">
		<fieldset>
			<legend>Carrello<button onclick='deleteItem("ALL", "carrello")'>Svuota</button></legend>
			<table>
				<%
				String messaggio = (String)session.getAttribute("messaggio-carrello");
				session.removeAttribute("messaggio-carrello");
				if(messaggio != null) {
				%>
				<tr><td><h2><%=messaggio %></h2></td></tr>
				<%
				}
				%>
				<%
				List<ArticoloBean> articoli = carrello.getItems();
				List<Integer> quantità = carrello.getQuantità();
				Iterator<ArticoloBean> it1 = articoli.iterator();
				Iterator<Integer> it2 = quantità.iterator();
				DecimalFormat df = new DecimalFormat("#0.00");
				
				ArticoloBean bean = new ArticoloBean();
				Integer q = 0;
				double totale = 0;
				int index = 0;
				int giacenzaMax;
				
				while(it1.hasNext() && it2.hasNext()){
					bean=it1.next();
					q=it2.next();
					%>
					<tr><td><a href="<%= response.encodeURL("/EdilCommerce_Design/articolo.jsp?articolo=" + bean.getCodiceArticolo())%>"><img alt="<%=bean.getNome()%>" src="<%=bean.getImmagine()%>"></a></td>
					<td><h4><a href="<%= response.encodeURL("/EdilCommerce_Design/articolo.jsp?articolo=" + bean.getCodiceArticolo())%>"><%=bean.getNome()%></a></h4>
					<h5><%=df.format(bean.getCosto())%>&euro;</h5>
					<label>Quantità</label><input class="quantita" type="number" value="<%=q%>" min ="1" max="<%=bean.getGiacenza()%>"  onchange='aggiornaQuantita("<%=index%>")'></td>
					<td><button onclick='deleteItem("<%=bean.getCodiceArticolo()%>", "carrello")'>X</button></td></tr>
					<%
					index = index + 1;
					totale = totale + (q * bean.getCosto());
				}
				%>
			</table>
		</fieldset>
		<div class="box-checkout">
			<div id="checkout">
				<h4>Carrello <span class="prezzo" style="color:black"><i class="fa fa-shopping-cart"></i> <%=articoli.size()%></span></h4>
				<h4>Totale: <span class="prezzo" style="color:black"><%=df.format(totale)%>&euro;</span></h4>
				<%
					if(totale == 0){
				%>
					<a href="<%= response.encodeURL("")%>"><button class="bottone" >Procedi al pagamento</button></a>
				<%
				} else {
				%>
				
					<a href="<%= response.encodeURL("/EdilCommerce_Design/user/checkout.jsp")%>"><button class="bottone" >Procedi al pagamento</button></a>
				<%
				}
				 %>
			</div>
		</div>
	</div>
	</div>
	<%@ include file="../footer.jsp" %>
	</div>
</body>
</html>