<%@page import="javax.swing.text.Document"%>
<%@page import="java.awt.Window"%>
<%@page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="javax.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/EdilCommerce_Design/css/default.css" rel="stylesheet" type="text/css">
<link href="/EdilCommerce_Design/css/articolo.css" rel="stylesheet" type="text/css">
<title>EdilCommerce Design</title>
</head>
<body>
	<div id="holder">
		<script type="text/javascript" src="/EdilCommerce_Design/script/articolo.js"></script>
		<%@include file="header.jsp" %>
		<div id="body">
		<%
		ArticoloModelDS model = new ArticoloModelDS((DataSource)getServletContext().getAttribute("DataSource"));
		ArticoloBean bean = new ArticoloBean();
		bean=model.doRetriveByKey(request.getParameter("articolo"));
		HttpSession session = request.getSession(false);
		UserBean user = new UserBean();
		if(session != null){
			user = (UserBean) session.getAttribute("loggedUser");
		}
		%>
		
		<div class="visualizzazione">
			<img alt="<%=bean.getNome()%>" src="<%=bean.getImmagine()%>" class="articolo">
			<div class="checkout">
				<h3>Prezzo: <%=bean.getCosto() %>&euro;</h3>
				<form action="<%=response.encodeURL("/EdilCommerce_Design/Aggiungi")%>" method="get">
					<input type="hidden" name="codice" value="<%=bean.getCodiceArticolo() %>">
					<label>Quantità: <input name="quantita" type="number" value="1" min="1" class="quantità"></label>
				 	<input type="submit" value="Aggiungi al carrello" class="aggiungi">
			 	</form>
			 	<hr style="margin: 20px 0">
			 	<h4>Articoli venduti e certificati da EdilCommerce Design</h4>
			 </div>
			<div class="articoloBody">
			<h2><%=bean.getNome() %></h2>
			<p><%=bean.getDescrizione()%></p>
			</div>
			 
			
		</div>
		<div class="recensioni">
		<div id="scriviRecensione">
		<h3>Lascia qui una recensione del prodotto</h3>
		<%
		if(session==null) {
		%>
		<textarea rows="5" cols="50" maxlength="500" placeholder="Effettua il login per scrivere una recensione" disabled></textarea><br>
		<input type="button" value="Invia recensione" disabled>	
		<%
		} else {
			Boolean uRole = (Boolean) session.getAttribute("userRole");
			if(uRole.equals(false)) {%>
			<textarea rows="5" cols="50" maxlength="500" placeholder="Effettua il login per scrivere una recensione" disabled></textarea><br>
			<input type="button" value="Invia recensione" disabled>			
		<%
			} else {
				RecensisceModelDS rModel = new RecensisceModelDS((DataSource)getServletContext().getAttribute("DataSource"));
				RecensisceBean rBean = rModel.doRetriveByKey(user.getUsername(), bean.getCodiceArticolo());
				if(!rBean.isEmpty()){
					%>
					<span onmouseout="SetDefaultStelle()">
					<input id="stelle" type="number" hidden="true" value="<%=rBean.getValore() %>" min="0" max="5">
					<%
						for(int i=1; i<6; i++) {
							%>
							<span class="fa fa-star <%=rBean.getValore()>=i?"checked":"" %>" onmouseover="Seleziona('<%=i%>')" onclick="SetSelezionati('<%=i%>')"></span>
							<%
						}
						%>
			
			</span><input id="x" type="button" value="X" title="Cancella recensione" onclick="CancellaRecensione('<%=bean.getCodiceArticolo()%>')"><br>
			<textarea id="testo" rows="5" cols="50" maxlength="500" placeholder="Inserisci qui la tua recensione"><%=rBean.getTesto()%></textarea><br>
			<input id="submit" type="button" value="Aggiorna recensione" onclick="AggiornaRecensione('<%=bean.getCodiceArticolo()%>')">
		<%
				} else {
		%>
		<span onmouseout="SetDefaultStelle()">
		<%
			for(int i=1; i<6; i++) {
				%>
				<span class="fa fa-star" onmouseover="Seleziona('<%=i%>')" onclick="SetSelezionati('<%=i%>')"></span>
				<%
			}
			%>
			</span>
			<br>
			<input id="stelle" type="number" hidden="true" value="0" min="0" max="5">
			<textarea id="testo" rows="5" cols="50" maxlength="500"  placeholder="Inserisci qui la tua recensione"></textarea><br>
			<input id="submit" type="button" value="Invia recensione" onclick="InviaRecensione('<%=bean.getCodiceArticolo()%>')">
		<%
				}	
			}
		}
		%>
		</div>
		<hr>
		<div>
		<%
		RecensisceModelDS rModel = new RecensisceModelDS((DataSource)getServletContext().getAttribute("DataSource"));
		LinkedList<RecensisceBean> recensioni = (LinkedList<RecensisceBean>) rModel.doRetriveByCodiceArticolo(bean.getCodiceArticolo());
		if(recensioni.isEmpty()){
		%>
		 <h4>Nessuna recensione per questo articolo</h4>
		<%	
		} else {
			int k = 0;
			RecensisceBean lastBean = new RecensisceBean();
			Iterator<RecensisceBean> it = recensioni.iterator();
			while(it.hasNext()){
				k++;
				RecensisceBean rBean = new RecensisceBean();
				rBean = it.next();
				lastBean = rBean;
				if(!rBean.getUsername().equals(user.getUsername())){
		%>
			<div class="recensione">
				<h4><span class="data"><%=rBean.getDate()%></span> <%=rBean.getUsername()%></h4>
				<span class="stelle">
				<%
				for(int i=1; i<6; i++) {
					%>
					<span class="fa fa-star <%=rBean.getValore()>=i?"checked":"" %>"></span>
					<%
				}
				%>
				</span>
				<p class="recensione"><%=rBean.getTesto()%></p>	
			</div>
		<%		
				}
			}
			/*
			if(k == 1 && lastBean.getUsername().equals(user.getUsername()))
			%>
			 <h4>Nessuna recensione per questo articolo</h4>
			<%*/	
		}
		%>
		
		</div>
		
		</div>
		</div>
	<%@ include file="./footer.jsp" %>
	</div>
</body>
</html>