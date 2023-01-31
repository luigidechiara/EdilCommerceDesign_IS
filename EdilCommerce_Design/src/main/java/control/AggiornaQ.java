package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;

/**
 * Servlet implementation class AggiornaQ
 
 */
@WebServlet("/AggiornaQ")
public class AggiornaQ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AggiornaQ() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int indice = Integer.parseInt((String) request.getParameter("codice"));
		Integer q = Integer.parseInt((String) request.getParameter("quantita"));
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			Carrello carrello = (Carrello) session.getAttribute("Carrello");
			List<Integer> artQuantita = carrello.getQuantita();
			artQuantita.remove(indice);
			artQuantita.add(indice, q);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
