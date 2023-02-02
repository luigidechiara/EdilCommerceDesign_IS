package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.RuoloUserBean;
import model.RuoloUserModelDS;
import model.UserBean;
import model.UserModelDS;
import utils.PasswordHasher;
import utils.Utility;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UserModelDS model = new UserModelDS(ds);
		RuoloUserModelDS modelRuolo = new RuoloUserModelDS(ds);
		RequestDispatcher dispatcher = null;
		UserBean bean = new UserBean();
		PasswordHasher ph= new PasswordHasher();
		// stringa
		try {
			bean = model.doRetriveByKey(request.getParameter("username"));
		} catch (SQLException e) {
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}
		
		if(!bean.isEmpty()) {
			if(ph.hash(request.getParameter("password")).equals(bean.getUserPassword())) {
				try {
					Collection<RuoloUserBean> collection = modelRuolo.doRetriveByOneKey(bean.getUsername());
					HttpSession session = request.getSession(true);
					
					for(RuoloUserBean userBean: collection) {
						if(userBean.getNome().equals("user")) {
							session.setAttribute("userRole", true);
						} if(userBean.getNome().equals("admin")) {
							session.setAttribute("adminRole", true);
						}if(userBean.getNome().equals("catalogo")) {
							session.setAttribute("adminRole", true);
							session.setAttribute("cataRole", true);
						}
						if(userBean.getNome().equals("magazzino")) {
							session.setAttribute("adminRole", true);
							session.setAttribute("magRole",true);
						}
					}
					try {
						UserBean saveBean = model.doRetriveByKey(bean.getUsername());
						session.setAttribute("loggedUser", saveBean);
						
						String redirect = (String) session.getAttribute("loginRedirect");
						session.removeAttribute("loginRedirect");
						
						if(redirect == null) {
							redirect="home.jsp";							
						}
						Collection<RuoloUserBean> collection1 = modelRuolo.doRetriveByOneKey(bean.getUsername());
						
						
						for(RuoloUserBean userBean: collection) {
							if(userBean.getNome().equals("catalogo")||userBean.getNome().equals("magazzino")) 
								redirect="admin/admin.jsp";				
							}
											
						response.sendRedirect(response.encodeRedirectURL(redirect));
						return;
					} catch(SQLException e) {
						Utility.print(e);
						request.setAttribute("error", e.getMessage());
					}
				} catch (SQLException e) {
					Utility.print(e);
					request.setAttribute("error", e.getMessage());
				}
			} else {
				request.setAttribute("error", "Username o Password incoretta. Riprova.");
				dispatcher = getServletContext().getRequestDispatcher(response.encodeRedirectURL("/login.jsp"));
				dispatcher.include(request, response);
			}
		} else {
			request.setAttribute("error", "Username o Password incoretta. Riprova.");
			dispatcher = getServletContext().getRequestDispatcher(response.encodeRedirectURL("/login.jsp"));
			dispatcher.include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
