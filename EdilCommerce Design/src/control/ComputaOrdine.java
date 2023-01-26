package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
import model.CartaBean;
import model.CartaModelDS;
import model.ComponeBean;
import model.ComponeModelDS;
import model.ContrassegnoBean;
import model.ContrassegnoModelDS;
import model.InfoFatturazioneBean;
import model.InfoFatturazioneModelDS;
import model.OrdineBean;
import model.OrdineModelDS;
import model.UserBean;
import utils.Utility;
import utils.ValidazioneInput;

@WebServlet("/ComputaOrdine")
public class ComputaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		
		OrdineBean oBean = new OrdineBean();
		InfoFatturazioneBean ifBean = new InfoFatturazioneBean();
		ValidazioneInput validazione= new ValidazioneInput();
		HttpSession session =  request.getSession(false);
		String metodo = request.getParameter("metodo");

		
		if(session != null) {
			OrdineModelDS oModel = new OrdineModelDS(ds);
			Carrello<ArticoloBean> carrello = (Carrello<ArticoloBean>) session.getAttribute("Carrello");
			
			oBean.setUsername(((UserBean)session.getAttribute("loggedUser")).getUsername());

			if(validazione.InformazioniSpedizione(request.getParameter("nome"), request.getParameter("cognome"),request.getParameter("email"),request.getParameter("telefono"),request.getParameter("indirizzo"),request.getParameter("citta"),request.getParameter("stato"),request.getParameter("cap"))) { 
				if(validazione.ValidazioneCarta(request.getParameter("cnum"),request.getParameter("cnome"),request.getParameter("expmonth"), request.getParameter("expyear"),request.getParameter("cvv"))||metodo.equals("2")) {

			
			try {
				oModel.doSave(oBean);
				
				LinkedList<OrdineBean> col = (LinkedList<OrdineBean>) oModel.doRetriveAll("");
				oBean = col.getLast();
				
				ComponeModelDS cModel = new ComponeModelDS(ds);
				
				List<ArticoloBean> items = carrello.getItems();
				List<Integer> quantità = carrello.getQuantità();
				Iterator<ArticoloBean> it1 = items.iterator();
				Iterator<Integer> it2 = quantità.iterator();
				
				ArticoloModelDS aModel = new ArticoloModelDS(ds);
				double importo = 0.0;
				
				while(it1.hasNext() && it2.hasNext()) {
					ComponeBean cBean = new ComponeBean();
					cBean.setNumeroOrdine(oBean.getNumeroOrdine());
					String codice = it1.next().getCodiceArticolo();
					int q = it2.next();
					cBean.setCodiceArticolo(codice);
					cBean.setQuantità(q);
					cModel.doSave(cBean);
					importo = importo + aModel.doRetriveByKey(codice).getCosto() * q;
					ArticoloBean artBean = new ArticoloBean();
					q=aModel.doRetriveByKey(codice).getGiacenza()-q;
					artBean.setGiacenza(q);
					aModel.doUpdateGiacenza(artBean, codice);	
				}
								
				oBean.setImporto(importo);
				oModel.doUpdateImporto(oBean);
								
				
				InfoFatturazioneModelDS ifModel = new InfoFatturazioneModelDS(ds);
				
				ifBean.setNumeroOrdine(oBean.getNumeroOrdine());
				ifBean.setNome(request.getParameter("nome"));
				ifBean.setCognome(request.getParameter("cognome"));
				ifBean.setEmail(request.getParameter("email"));
				ifBean.setTelefono(request.getParameter("telefono"));
				ifBean.setIndirizzo(request.getParameter("indirizzo"));
				ifBean.setCittà(request.getParameter("citta"));
				ifBean.setStato(request.getParameter("stato"));
				ifBean.setCap(request.getParameter("cap"));

				ifModel.doSave(ifBean);
						

				
				if(metodo.equals("1")) {
					CartaModelDS caModel = new CartaModelDS(ds);
					
					CartaBean caBean = new CartaBean();
					
					caBean.setNumeroOrdine(oBean.getNumeroOrdine());
					caBean.setNumero(request.getParameter("cnum"));
					caBean.setIntestatario(request.getParameter("cnome"));
					caBean.setDataScadenza(request.getParameter("expmonth") + "/" + request.getParameter("expyear"));
					caBean.setCvv(request.getParameter("cvv"));

						caModel.doSave(caBean);
						
					
					
				} else if(metodo.equals("2")) {
					ContrassegnoModelDS coModel = new ContrassegnoModelDS(ds);
					
					ContrassegnoBean coBean = new ContrassegnoBean();
					
					coBean.setNumeroOrdine(oBean.getNumeroOrdine());
					
					coModel.doSave(coBean);
				}
				carrello.deleteItems();
				session.setAttribute("Carrello", carrello);
		
			
			} catch (SQLException e) {
				Utility.print(e);
				request.setAttribute("error", e.getMessage());
			}

			response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/user/ordineEffettuato.jsp?suc=1"));

			}} else {
				response.sendRedirect(response.encodeRedirectURL("/EdilCommerce_Design/user/checkout.jsp"));
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
