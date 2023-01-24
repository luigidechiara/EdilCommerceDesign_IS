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
import javax.sql.DataSource;

import model.ArticoloBean;
import model.ArticoloModelDS;
import model.CategoriaBean;
import model.CategoriaModelDS;
import model.UserModelDS;
import utils.Utility;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Collection<ArticoloBean> collection = new LinkedList<ArticoloBean>();
		
		CategoriaModelDS modelC = new CategoriaModelDS(ds);
		ArticoloModelDS modelA = new ArticoloModelDS(ds);
		
		String criterio = request.getParameter("criterioRicerca");
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
			
			if(isCategory==true) {
				collection=modelA.doRetriveByCategory(criterio, "", "");
			}else {
				collection=modelA.doSearchByNome(criterio, "", "");
			}
			
		} catch (SQLException e) {
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}
		
		request.setAttribute("risultato", collection);
		getServletContext().getRequestDispatcher(response.encodeURL("/result.jsp")).include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
