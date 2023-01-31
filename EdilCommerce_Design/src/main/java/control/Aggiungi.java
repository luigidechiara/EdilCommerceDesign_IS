package control;

import java.io.IOException;
import java.sql.SQLException;

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

/**
 * Servlet implementation class Aggiungi
 */
@WebServlet("/Aggiungi")
public class Aggiungi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ArticoloModelDS model = new ArticoloModelDS(ds);
		
		int quantita = Integer.parseInt((String) request.getParameter("quantita"));
		String codice = (String) request.getParameter("codice");
		
		
		HttpSession session =  request.getSession(false);
		if(session == null) {
			session = request.getSession(true);
			String parameters = request.getQueryString();	
			if(parameters == null)
				parameters= "";
			session.setAttribute("loginRedirect", request.getRequestURI() + "?" +  parameters);
			response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/login.jsp"));
			return;
		} else if(session.getAttribute("userRole").equals(false)) {
			session = request.getSession(true);
			session.setAttribute("loginRedirect", request.getRequestURI());
			response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/login.jsp"));
			return;
		}
		
		Carrello<ArticoloBean> carrello = (Carrello<ArticoloBean>) session.getAttribute("Carrello");
		ArticoloBean bean = new ArticoloBean();
		
		try {
			bean = model.doRetriveByKey(codice);
			
		} catch(SQLException e) {
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}
		
		carrello.addItem(bean, quantita);
		
		request.getSession(false).setAttribute("Carrello", carrello);
		request.getSession(false).setAttribute("messaggio-carrello", "Articolo " + bean.getNome() + " aggiunto al carrello");
		response.sendRedirect(response.encodeRedirectURL("user/carrello.jsp"));
		return;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
