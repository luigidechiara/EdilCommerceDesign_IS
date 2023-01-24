package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.ArticoloBean;
import model.ArticoloModelDS;
import model.CategoriaBean;
import model.CategoriaModelDS;
import utils.Utility;

@WebServlet("/ModificaArticolo")
public class ModificaArticolo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/"));
		}
		
		Collection<CategoriaBean> categorie = (Collection<CategoriaBean>) getServletContext().getAttribute("Categorie");
		
		ArticoloModelDS modelA = new ArticoloModelDS(ds);
		
		ArticoloBean aBean = new ArticoloBean();
		
		String op = request.getParameter("op");
		String codice = request.getParameter("codice");
		StringBuffer buffer = new StringBuffer();
		
		if(op != null) {
			if(Integer.parseInt(op) == 1) {//Autocompila i form
				try {
					aBean = modelA.doRetriveByKey(codice);
				} catch (SQLException e) {
					Utility.print(e);
					request.setAttribute("error", e.getMessage());
				}
				
				
				response.setContentType("text/xml");
				
				buffer.append("<form action=" + response.encodeURL("/EdilCommerce_Design/ModificaArticolo") + ">\r\n"
						+ "										<label for=\"nome\">Nome dell'articolo</label>\r\n"
						+ "										<input type=\"text\" name=\"nome\" maxlength=\"50\" required value=" + aBean.getNome() + ">\r\n"
						+ "										\r\n"
						+ "										<label for=\"codice\">Codice</label>\r\n"
						+ "										<input type=\"text\" name=\"codice\" maxlength=\"5\" required readonly value=" + aBean.getCodiceArticolo() + ">\r\n"
						+ "										\r\n"
						+ "										<label for=\"categorie\">Categoria</label>\r\n"
						+ "										<select name=\"categorie\" id=\"categorieM\" required>\r\n");
				Iterator<CategoriaBean> it = categorie.iterator();
				while(it.hasNext()) {
					CategoriaBean cBean = it.next();
					buffer.append("										  <option value=\"" + cBean.getNome() + "\" ");
					if(aBean.getNomeCategoria().equals(cBean.getNome()))
						buffer.append("selected");
					buffer.append(">" + cBean.getNome() + "</option>\r\n");
				}
				String[] img = aBean.getImmagine().split("/");
				buffer.append("										</select>\r\n"
						+ "										\r\n"
						+ "										<label for=\"immagine\">Foto</label>\r\n"
						+ "										<input type=\"text\" name=\"immagine\" required value=" + img[5] + ">\r\n"
						+ "										\r\n"
						+ "										<label for=\"testo\">Descrizione</label>\r\n"
						+ "										<textarea name=\"testo\" cols=\"40\" rows=\"5\" maxlength=\"1000\" required>" + aBean.getDescrizione() + "</textarea>\r\n"
						+ "										\r\n"
						+ "										<label for=\"costo\">Costo (&euro;)</label>\r\n"
						+ "										<input type=\"number\" name=\"costo\" min=\"0.00\" required value=" + aBean.getCosto() + ">\r\n"
						+ "										\r\n"
						+ "										<label for=\"giacenza\">giacenza</label>\r\n"								
						+ "										<input type=\"number\" name=\"giacenza\" min=\"1\"max=\"1000\" value="+ aBean.getGiacenza()+">\r\n"
						+ "										<br><br><input type=\"submit\" value=\"Modifica\">&nbsp;<input type=\"reset\">\r\n"
						+ "									</form>");
			}
			response.getWriter().write(buffer.toString());
		} else {
			String nome = request.getParameter("nome");
			String categoria = request.getParameter("categorie");
			String immagine = request.getParameter("immagine");
			String testo = request.getParameter("testo");
			Double costo = Double.parseDouble(request.getParameter("costo"));
			int giacenza = Integer.parseInt(request.getParameter("giacenza"));
			String[] cat = categoria.split(" ");
			String cat1 = cat[0];
			if(cat.length != 1)
				cat1 = cat[0] + cat[1].substring(0, 1).toUpperCase() + cat[1].substring(1);
			immagine = "/EdilCommerce_Design/img/categoria/" + cat1 + "/" + immagine;
			
			aBean.setCodiceArticolo(codice);
			aBean.setCosto(costo);
			aBean.setDescrizione(testo);
			aBean.setImmagine(immagine);
			aBean.setNome(nome);
			aBean.setNomeCategoria(categoria);
			aBean.setGiacenza(giacenza);
			
			try {
				modelA.doUpdate(aBean, codice);
			} catch (SQLException e) {
				Utility.print(e);
				request.setAttribute("error", e.getMessage());
			}
			
			session.setAttribute("Messaggio", "Articolo " + aBean.getCodiceArticolo() + " modificato con successo");
			response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/admin/admin.jsp"));
			return;
		}
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
