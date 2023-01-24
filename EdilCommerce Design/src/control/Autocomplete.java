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

/**
 * Servlet implementation class Autocomplete
 */
@WebServlet("/Autocomplete")
public class Autocomplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		Collection<ArticoloBean> collection = new LinkedList<ArticoloBean>();

		ArticoloModelDS modelA = new ArticoloModelDS(ds);
		
		String nome = request.getParameter("criterioRicerca");
		
		try {
			collection = modelA.doSearchByNome(nome, "", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		response.setContentType("text/xml");
		

			Iterator<ArticoloBean> it = collection.iterator();
			buffer.append("<ul>");
			while(it.hasNext()){
				ArticoloBean bean = it.next();
			buffer.append("<a href='/EdilCommerce_Design/articolo.jsp?articolo="+ bean.getCodiceArticolo()+"'><li>"+bean.getNome() +"</li></a>");
			}
			buffer.append("</ul>");
		response.getWriter().println(buffer.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
