package control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.ArticoloBean;
import model.ArticoloModelDS;
import model.CategoriaBean;
import model.CategoriaModelDS;
import utils.Utility;

/**
 * Servlet implementation class AggiornaSearch
 */
@WebServlet("/AggiornaSearch")
public class AggiornaSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Collection<ArticoloBean> collection = new LinkedList<ArticoloBean>();
		
		CategoriaModelDS modelC = new CategoriaModelDS(ds);
		ArticoloModelDS modelA = new ArticoloModelDS(ds);
		
		String criterio = request.getParameter("criterioRicerca");
		String ordine = request.getParameter("ordine");
		String prezzo = request.getParameter("prezzo");
		String mediaRecensioni = request.getParameter("mediaRecensioni");
		
		if(ordine != null)
			if(!ordine.isBlank())
				ordine = codificaOrdine(Integer.parseInt(ordine));
		
		if(prezzo != null)
			if(!prezzo.isBlank())
				prezzo = codificaPrezzo(Integer.parseInt(prezzo));
		
		if(mediaRecensioni != null)
			if(!mediaRecensioni.isBlank())
				mediaRecensioni = codificaGradimento(Integer.parseInt(mediaRecensioni));
		
		if(criterio== null) {
			response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/"));
		}else if(criterio.isBlank()) {
			response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/"));
		}
		boolean isCategory= false;
		
		Collection<CategoriaBean> collectionC = new LinkedList<CategoriaBean>();
		try{
			collectionC = modelC.doRetriveAll("");
			Iterator<CategoriaBean> it = collectionC.iterator();
			while(it.hasNext() && !isCategory) {
				if(it.next().getNome().equals(criterio)) {
					isCategory = true;
				}
			}
			
					if(!prezzo.isBlank()) {
						if(!mediaRecensioni.isBlank())
							prezzo = prezzo + " AND " + mediaRecensioni;}
				    else if(!mediaRecensioni.isBlank())
							prezzo = mediaRecensioni;
			
			
			
			if(isCategory==true) {
				collection=modelA.doRetriveByCategory(criterio, prezzo, ordine);
			}else {
				collection=modelA.doSearchByNome(criterio, prezzo, ordine);
			}
			
		} catch (SQLException e) {
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}
		
		StringBuffer buffer = new StringBuffer();
		response.setContentType("text/xml");
		
		
			if(collection == null) {
				buffer.append("		<h3>Nessun articolo trovato</h3>\n");
				
			} else if(collection.isEmpty()){
				buffer.append("		<h3>Nessun articolo trovato</h3>\n");
			} else {
				buffer.append( "		<div class=\"standard-table-size\">"
						+ "		<table>\r\n");
				Iterator<ArticoloBean> it = collection.iterator();
				ArticoloBean bean = new ArticoloBean();
				DecimalFormat df=new DecimalFormat("#0.00");
				int pag = 1, count=0;
				while(it.hasNext()) {
					if(count==7) {
						count=0;
						pag++;
					}
				bean=it.next();
				count++;
				buffer.append("		<tr class=\"pagina" + pag +" pagina\"onclick=\"document.location = '" + response.encodeURL("/EdilCommerce_Design/articolo.jsp?articolo=" + bean.getCodiceArticolo()) + "';\"><td><img alt=\"" + bean.getNome() + "\" src=\"" + bean.getImmagine() + "\"></td>\n"
				+ "		<td><h4>" + bean.getNome() + "</h4>\n"
				+ "	<span class=\"float-left\">\r\n");
				int j;
				for(j=1; j<6; j++) { 
					buffer.append("<span class=\"fa fa-star space") ;
					if(bean.getMediaRecensioni()>=j)
						buffer.append(" checked");
					else buffer.append("");
					buffer.append("\"></span>");
				}
				
				
				buffer.append("		</span> \n");
				buffer.append( "		<h5>" + df.format(bean.getCosto()) + "&euro;</h5></td></tr>\n");
				}
				buffer.append("		</table>"
						+ "		</div>"
						+ "		<br>\r\n"
						+ "		<div class=\"paginazione\">\r\n"
						+ "		<a href=\"#holder\" onclick=\"prevPag()\">&lt;</a>\r\n");
				int i;
				for(i=0; i<pag; i++) {
					buffer.append("		<a class=\"pagButton"+ (i==0?" current":"") + "\"href=\"#holder\" onclick=\"cambiaPag('" + (i+1) + "')\">" +  (i+1) + "</a>\n");
				}
				buffer.append("		<a href=\"#holder\" onclick=\"succPag()\">&gt;</a>\r\n"
						+ "		</div>\r\n");
				
			}
			response.getWriter().write(buffer.toString());
			
	}
	
	private String codificaPrezzo(int index){
		switch (index) {
			case 1 : return "costo BETWEEN 0.0 AND 50.0";
			case 2 : return "costo BETWEEN 50.01 AND 200.0";
			case 3 : return "costo BETWEEN 200.01 AND 99999.0";
		}
		return null;
	}
	
	private String codificaGradimento(int index){
		switch (index) {
			case 1 : return "mediaRecensioni >= 4";
			case 2 : return "mediaRecensioni >= 3";
			case 3 : return "mediaRecensioni >= 2";
			case 4 : return "mediaRecensioni >= 1";
		}
		return null;
	}

	private String codificaOrdine(int index){
		switch (index) {
			case 1 : return "nome ASC";
			case 2 : return "nome DESC";
			case 3 : return "costo ASC";
			case 4 : return "costo DESC";
		}
		return null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
