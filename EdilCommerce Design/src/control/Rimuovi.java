package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import model.ArticoloBean;
import model.ArticoloModelDS;
import model.Carrello;
import utils.Utility;

@WebServlet("/Rimuovi")
public class Rimuovi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ArticoloModelDS model = new ArticoloModelDS(ds);
		
		String codice = (String) request.getParameter("codice");
		HttpSession session = request.getSession(false);
		Carrello<ArticoloBean> carrello = (Carrello<ArticoloBean>) session.getAttribute("Carrello");
		
		List<ArticoloBean> articoli = carrello.getItems();
		List<Integer> artQuantita = carrello.getQuantita();
		DecimalFormat df=new DecimalFormat("#0.00");
		double totale = 0;
		
		StringBuffer buffer = new StringBuffer();
		response.setContentType("text/xml");
		
		buffer.append("<fieldset>\r\n"
				+ "			<legend>Carrello<button onclick='deleteItem(\"ALL\", \"carrello\")'>Svuota</button></legend>\r\n"
				+ "			<table>");
		
		if(codice.equals("ALL")) {
			carrello.deleteItems();
			session.setAttribute("Carrello", carrello);
			
			buffer.append("<tr><td><h2>Tutti gli articoli rimossi dal carrello</h2></td><td></td><td></td></tr>");
		} else {
			ArticoloBean removeBean = new ArticoloBean();
			
			try {
				removeBean = model.doRetriveByKey(codice);
			} catch(SQLException e) {
				Utility.print(e);
				request.setAttribute("error", e.getMessage());
			}
			
			int index = carrello.deleteItem(removeBean);
			session.setAttribute("Carrello", carrello);
			
			ArticoloBean bean = new ArticoloBean();
			
			Iterator<ArticoloBean> it1 = articoli.iterator();
			Iterator<Integer> it2 = artQuantita.iterator();
			
			Integer q = 0;
			int i = 1;
			
			int j = 0;
			if(index==0) {
				buffer.append("<tr><td><h2>"+ removeBean.getNome() + " rimosso dal carrello</h2></td><td></td><td></td></tr>");
			}
				
			while(it1.hasNext() && it2.hasNext()){
			
				bean=it1.next();
				q=it2.next();
				buffer.append("<tr><td><a href=\"/EdilCommerce_Design/articolo.jsp?articolo=" + bean.getCodiceArticolo() + "\"><img alt=\"" + bean.getNome() + "\" src=\"" + bean.getImmagine() + "\"></a></td>");
				buffer.append("<td><h4><a href=\"/EdilCommerce_Design/articolo.jsp?articolo=" + bean.getCodiceArticolo() + "\">" + bean.getNome() + "</a></h4>");
				buffer.append("<h5>" + df.format(bean.getCosto()) + "&euro;</h5>");
				buffer.append("<label>Quantita</label><input type=\"number\" value=\"" + q + "\"  onchange='aggiornaQuantita(" + j + ")'\"></td>");
				buffer.append("<td><button onclick='deleteItem(\"" + bean.getCodiceArticolo() + "\", \"carrello\")'>X</button></td></tr>");		
			
				if(i == index) {
					buffer.append("<tr><td><h2>"+ removeBean.getNome() + " rimosso dal carrello</h2></td><td></td><td></td></tr>");
				}
				i++;
			}
			j = j+1;
			totale = totale + (q * bean.getCosto());
		}
		

		buffer.append("</table>\r\n"
			+ "		</fieldset>\r\n"
			+ "		<div class=\"box-checkout\">\r\n"
			+ "			<div id=\"checkout\">"
			+ "				<h4>Carrello <span class=\"prezzo\" style=\"color:black\"><i class=\"fa fa-shopping-cart\"></i> " + articoli.size() + "</span></h4>\r\n"
			+ "				<h4>Totale: <span class=\"prezzo\" style=\"color:black\">" + df.format(totale) + "&euro;</span></h4>\r\n");
		if(totale == 0){
			buffer.append("				<a href=\" " + response.encodeURL("") + "\"><button class=\"bottone\" >Procedi al pagamento</button></a>\r\n");
		} else {
			buffer.append("				<a href=\" " + response.encodeURL("/EdilCommerce_Design/user/checkout.jsp") + "\"><button class=\"bottone\" >Procedi al pagamento</button></a>\r\n");
		}
		
		buffer.append( "			</div>\r\n"
			+ "		</div>"	);
		
		response.getWriter().write(buffer.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
