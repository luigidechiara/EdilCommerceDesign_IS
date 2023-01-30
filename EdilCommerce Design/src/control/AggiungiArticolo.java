package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
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
import utils.Utility;
import utils.ValidazioneInput;

@WebServlet("/AggiungiArticolo")
public class AggiungiArticolo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ValidazioneInput validazione=new ValidazioneInput();
		String nome = request.getParameter("nome");
		String codice = request.getParameter("codice");
		String categorie = request.getParameter("categorie");
		String[] cat = categorie.split(" ");
		String cat1 = cat[0];
		if(cat.length != 1)
			cat1 = cat[0] + cat[1].substring(0, 1).toUpperCase() + cat[1].substring(1);
		String immagine = "/EdilCommerce_Design/img/categoria/"+cat1+"/"+request.getParameter("immagine");
		
		String testo = request.getParameter("testo");
		Double costo = Double.parseDouble(request.getParameter("costo"));
		int giacenza = Integer.parseInt(request.getParameter("giacenza"));
		
		ArticoloBean saveBean = new ArticoloBean();
		
		saveBean.setCodiceArticolo(codice);
		saveBean.setNome(nome);
		saveBean.setNomeCategoria(categorie);
		saveBean.setImmagine(immagine);
		saveBean.setDescrizione(testo);
		saveBean.setCosto(costo);
		saveBean.setGiacenza(giacenza);
		HttpSession session = request.getSession(false);
		
		if(validazione.ValidazioneAggiungiArticolo(saveBean)) {
		
		Utility.print("Entrata nella servlet");
		
		
		if(session == null) {
			response.sendRedirect(response.encodeURL("/EdilCommerce_Design/admin/admin.jsp"));
			return;
		}
		
		if(nome==null || codice==null || categorie==null || immagine==null || testo==null || costo==null) {
			session.setAttribute("AdminError", "Un campo è nullo");
			response.sendRedirect(response.encodeURL("/EdilCommerce_Design/admin/admin.jsp"));
			return;
		}
		
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		ArticoloBean articolo= new ArticoloBean();
		
		
		ArticoloModelDS modelA = new ArticoloModelDS(ds);
		
		try {
			articolo = modelA.doRetriveByImmagine(immagine);
			if(!articolo.isEmpty()) {
				session.setAttribute("UnsavedArticolo", saveBean);
				session.setAttribute("AdminError", "Nome imagine duplicato, rinominare l'imagine");
				response.sendRedirect(response.encodeURL("/EdilCommerce_Design/admin/admin.jsp"));
				return;
			}
		} catch (SQLException e) {
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}
		
		ArticoloBean aBean = new ArticoloBean();
		
		try {
			aBean = modelA.doRetriveByKey(codice);
			if(aBean.equals(saveBean)) {
				session.setAttribute("UnsavedBean", saveBean);
				session.setAttribute("AdminError", "Codice articolo duplicato, modificare il nome dell'articolo");
				response.sendRedirect(response.encodeURL("/EdilCommerce_Design/admin/admin.jsp"));
				return;
			}
		} catch (SQLException e) {
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}
		
		try {
			modelA.doSave(saveBean);
		} catch (SQLException e) {
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}
		
		Utility.print("Uscita dalla servlet");
		session.setAttribute("Messaggio", "Articolo " + saveBean.toString() + " salvato con successo");
		response.sendRedirect(response.encodeURL("/EdilCommerce_Design/admin/admin.jsp"));
	
		return;
		}else {
			session.setAttribute("Messaggio", "Errore dati Input");
			response.sendRedirect(response.encodeURL("/EdilCommerce_Design/admin/admin.jsp"));
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
