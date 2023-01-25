<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="javax.sql.*,model.*,utils.*,java.text.*"%>
<%@page session="false" %>
<!DOCTYPE html>
<%
Boolean adminRole;
HttpSession session = request.getSession(false);
if(session == null) {
	response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/login.jsp"));
	return;
} else {
    adminRole = (Boolean) session.getAttribute("adminRole");
	
	if((adminRole == null) || (!adminRole.booleanValue())) {
		response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/login.jsp"));
		return;
	}
}

Boolean magRole = (Boolean) session.getAttribute("magRole");
Boolean cataRole = (Boolean) session.getAttribute("cataRole");
String errore = (String) session.getAttribute("AdminError");
session.removeAttribute("AdminError");

String mess = (String) session.getAttribute("Messaggio");
session.removeAttribute("Messaggio");

ArticoloBean unsaved = (ArticoloBean) session.getAttribute("UnsavedBean");
session.removeAttribute("UnsavedBean");
if(unsaved == null)
	unsaved = new ArticoloBean();
%>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="/EdilCommerce_Design/css/default.css" rel="stylesheet" type="text/css">
<link href="/EdilCommerce_Design/css/profilo.css" rel="stylesheet" type="text/css">
<title>EdilCommerce Design</title>
</head>
<body>
	<script type="text/javascript" src="/EdilCommerce_Design/script/profiloScripts.js"></script>
	<script type="text/javascript" src="/EdilCommerce_Design/script/admin.js"></script>
	<div id="holder">
		
		<%@ include file="../header.jsp" %>
		
		
		<div id="body">
		<%=errore!=null?"<h3 class=\"errorMessage\">Errore:" + errore + "</h3>":(mess!=null?"<h3>" + mess + "</h3>":"")%>
	
		<h2>Scegli un'operazione:</h2>
			<ul>
				<%if(cataRole!=null && magRole==null || adminRole!=null && magRole==null){ %>
				<li onclick="visualizza('inserisciArticolo')"><h2>Inserisci articolo</h2></li>
				<%} %>
					<div class="container start" id="inserisciArticolo">
						<form action="<%=response.encodeURL("/EdilCommerce_Design/AggiungiArticolo")%>" method="POST">
							<div class="flex">
					  			<div class="col-50">
									<label for="nome">Nome dell'articolo</label>
									<input type="text" name="nome" maxlength="50" value="<%=unsaved.getNome()%>" required>
									<%
									DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
									ArticoloModelDS aModel = new ArticoloModelDS(ds);
									LinkedList<ArticoloBean> collection = (LinkedList<ArticoloBean>) aModel.doRetriveAll("codiceArticolo");
									ArticoloBean aBean = collection.getLast();
									%>
									<label for="codice">Codice</label>
									<input type="text" name="codice" maxlength="5" value="<%=unsaved.isEmpty()?"ART"+ Integer.toString(Integer.parseInt(aBean.getCodiceArticolo().substring(3))+1):unsaved.getCodiceArticolo() %>" readonly="readonly" >
									
									<label for="categorie">Categoria</label>
									<select name="categorie" id="categorie" value="<%=unsaved.getNomeCategoria()%>" required>
									  <option value="Arredamento interno">Arredamento interno</option>
									  <option value="Arredamento esterno">Arredamento esterno</option>
									  <option value="Rivestimento">Rivestimento</option>
									  <option value="Vernici">Vernici</option>
									  <option value="Ferramenta">Ferramenta</option>
									  <option value="Utensileria">Utensileria</option>
									  <option value="Materiali">Materiali</option>
									  <option value="Copertura">Copertura</option>
									  <option value="Struttura">Struttura</option>
									</select>
									
									<label for="immagine">Foto</label>
									<input type="text" name="immagine" value="<%=unsaved.getImmagine()%>" required>
									
									<label for="testo">Descrizione</label>
									<textarea name="testo" cols="40" rows="5" maxlength="1000" required><%=unsaved.getDescrizione()%></textarea>
									
									<label for="costo">Costo (&euro;)</label>
									<input type="number" name="costo" min="0" step="0.01" value="<%=unsaved.getCosto()%>" required>
									
									<label for="giacenza">Giacenza</label>
									<input type="number" name="giacenza" min="1" max="1000" value="<%=unsaved.getGiacenza()%>" required>
								</div>
							</div>									
							<input type="submit" value="Aggiungi">&nbsp;<input type="reset">
						</form>
					</div>
					
				<%if(cataRole==null && magRole!=null){ %>
					<li onclick="visualizza('modificaArticolo')"><h2>Carico Giacenza</h2></li>
					<%}else{ %>	
					<li onclick="visualizza('modificaArticolo')"><h2>Modifica articolo</h2></li>
					<%} %>
						<div class="container start" id="modificaArticolo">
							
								<div class="flex">
						  			<div class="col-50">
						  				<label for="articolo"><h3>Seleziona l'articolo da modificare</h3></label>
										<select name="articolo" id="articolo" required>
										<% 
										 ds = (DataSource) getServletContext().getAttribute("DataSource");
											
										 aModel = new ArticoloModelDS(ds);
											
										 collection = (LinkedList<ArticoloBean>) aModel.doRetriveAll("");
										Iterator<ArticoloBean> iter = collection.iterator();
										while(iter.hasNext()){
										 aBean = iter.next();
										%>
											<option value="<%=aBean.getCodiceArticolo()%>"><%=aBean.getCodiceArticolo() + " " + aBean.getNome()%></option>
										<% 
										}
										%>		
										</select>
										&nbsp;<input type="button" onclick="selezionaArticolo('1')" value="Seleziona articolo"><br>
									<div id="formModificaA">
									</div>
								</div>
							</div>									
								

						</div>	
					
					
					
					
					
				<%if(cataRole==null && magRole!=null || adminRole!=null && cataRole==null){ %>
					<li onclick="visualizza('ordini')"><h2>Visualizza ordini</h2></li>
				<%} %>
					<div class="container start" id="ordini">
				<%
				
					OrdineModelDS oModel = new OrdineModelDS(ds);
					PagamentoModelDS pModel = new PagamentoModelDS(ds);
					ComponeModelDS cModel = new ComponeModelDS(ds);
					CartaModelDS caModel = new CartaModelDS(ds);
					ContrassegnoModelDS coModel = new ContrassegnoModelDS(ds);
					DecimalFormat df = new DecimalFormat("#0.00");
					
					LinkedList<OrdineBean> oList = (LinkedList<OrdineBean>) oModel.doRetriveAll("");
					
					boolean primoOrdine = true;
				
					Iterator<OrdineBean> it = oList.iterator();
					if(!it.hasNext()) {
				%>
				<h3>Nessun ordine effettuato</h3>
				<%			
					}
					while(it.hasNext()) {
						 if(primoOrdine==true) {
							 primoOrdine = false;
						 } else {
						%><hr><%
						 }
						OrdineBean oBean = it.next();
					
						PagamentoBean pBean = (PagamentoBean) pModel.doRetriveByNumeroOrdine(oBean.getNumeroOrdine());
						
						CartaBean caBean = caModel.doRetriveByKey(pBean.getNumeroPagamento());
						ContrassegnoBean coBean = coModel.doRetriveByKey(pBean.getNumeroPagamento());
						
				%>
					<div>
					<h2>Codice Ordine: <%=oBean.getNumeroOrdine()%></h2>
					<h3>Cliente: <%=oBean.getUsername() %></h3>
					<h3>Metodo di pagamento: 
				<%
					if(caBean.isEmpty()){ 
				%> 
						Contrassegno
				<%
					}else{
				%> 
						<%="Carta NumeroCarta:****" + caBean.getNumero().substring(caBean.getNumero().length() - 4) + " Intestario carta:" + caBean.getIntestatario()%>
				<%
					}
				 %>
					</h3>
					<h3>Importo ordine: <%=df.format(pBean.getImporto())%>&euro;</h3>
				<%
						
						LinkedList<ComponeBean> cList = (LinkedList<ComponeBean>) cModel.doRetriveByOneKey(oBean.getNumeroOrdine() + "");
						Iterator<ComponeBean> it1 = cList.iterator(); 
				%>
					<h3>Articoli che compongono l'ordine:</h3><ul>
				<%		
				
						while(it1.hasNext()){
							ComponeBean cBean = it1.next();
							 aBean = aModel.doRetriveByKey(cBean.getCodiceArticolo()); 
				%>
					<li><a href="<%=response.encodeURL("/EdilCommerce_Design/articolo.jsp?articolo=" + aBean.getCodiceArticolo())%>"><%=aBean.getNome()%></a>, quantità:<%=cBean.getQuantità()%></li>
				<%			
						}
				%>
					</ul></div>
				<%
					}
					
				%>
			</div>
			</ul>
			
		</div>
	
		<%@ include file="../footer.jsp" %>
	</div>
</body>
</html>