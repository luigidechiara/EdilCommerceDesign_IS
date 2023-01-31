package utils;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import model.ArticoloBean;
import model.Carrello;

@WebListener
public class MainSession implements HttpSessionListener {
	
 
public void sessionCreated(HttpSessionEvent se) {
	Utility.print("Start up user session");
	
	HttpSession session = se.getSession();
	Utility.print("Session:" + session.getId());

	Carrello<ArticoloBean> carrello = new Carrello<ArticoloBean>();
	
	session.setAttribute("userRole", false);
	session.setAttribute("adminRole", false);
	session.setAttribute("Carrello", carrello);
	utils.Utility.print("Cart creation: " + carrello.toString());
}

 
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		session.removeAttribute("userRole");
		session.removeAttribute("adminRole");
		session.removeAttribute("Carrello");
	}
}
