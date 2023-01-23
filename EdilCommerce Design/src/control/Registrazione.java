package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.RuoloUserBean;
import model.RuoloUserModelDS;
import model.UserBean;
import model.UserModelDS;
import utils.Utility;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UserModelDS model = new UserModelDS(ds);
		RuoloUserModelDS modelRuolo = new RuoloUserModelDS(ds);
		
		UserBean bean = new UserBean();
		
		bean.setUsername(request.getParameter("username"));
		UserBean checkBean = new UserBean();
		try {
			checkBean = model.doRetriveByKey(bean.getUsername());

			if (!(checkBean.equals(bean))) {
				bean.setNome(request.getParameter("nome"));
				bean.setCognome(request.getParameter("cognome"));
				bean.setEmail(request.getParameter("email"));
				bean.setUserPassword(request.getParameter("password"));
				bean.setTelefono(request.getParameter("telefono"));
				bean.setIndirizzo(request.getParameter("indirizzo"));
				bean.setCitt�(request.getParameter("citta"));
				bean.setCap(request.getParameter("cap"));
				bean.setStato(request.getParameter("stato"));
				try {
					model.doSave(bean);
					RuoloUserBean ruolo = new RuoloUserBean();
					ruolo.setUsername(bean.getUsername());
					ruolo.setNome("user");
					modelRuolo.doSave(ruolo);
				} catch (SQLException e) {
					Utility.print(e);
					request.setAttribute("error", e.getMessage());
				}

				response.sendRedirect(response.encodeRedirectURL("login.jsp"));
				return;
			} else {
				request.setAttribute("error", "Username gi� in uso");
				getServletContext().getRequestDispatcher(response.encodeURL("/registrazione.jsp")).include(request, response);
			}
		} catch (SQLException e) {
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
