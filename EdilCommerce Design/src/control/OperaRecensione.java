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

import model.RecensisceBean;
import model.RecensisceModelDS;
import model.UserBean;
import utils.Utility;

@WebServlet("/OperaRecensione")
public class OperaRecensione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			RecensisceBean rBean = new RecensisceBean();
			String op = request.getParameter("op");
			String codA = request.getParameter("codiceArticolo");
			String testo = request.getParameter("testo");
			int valutazione = Integer.parseInt(request.getParameter("valutazione"));
			String username = ((UserBean)session.getAttribute("loggedUser")).getUsername();
			
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			RecensisceModelDS rModel = new RecensisceModelDS(ds);
			
			rBean.setCodiceArticolo(codA);
			rBean.setTesto(testo);
			rBean.setUsername(username);
			rBean.setValore(valutazione);
			
			try {
				if(op.equals("save")) 
					rModel.doSave(rBean);
				else if(op.equals("modify"))
					rModel.doUpdate(rBean);
				else if(op.equals("delete"))
					rModel.doDelete(rBean);
			} catch (SQLException e) {
				Utility.print(e);
				request.setAttribute("error", e.getMessage());
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
