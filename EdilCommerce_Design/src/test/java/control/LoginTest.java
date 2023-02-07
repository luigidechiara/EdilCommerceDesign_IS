package control;


import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import model.RuoloUserModelDS;
import model.UserBean;
import model.UserModelDS;


public class LoginTest {

	 private HttpServletRequest request;
	    private HttpServletResponse response;
	    private HttpSession session;
	    private Login spy;
	    

	    @BeforeEach
	    void setUp() throws Exception {
	    	request = Mockito.mock(HttpServletRequest.class) ;
			response = Mockito.mock(HttpServletResponse.class);
			session = mock(HttpSession.class);
			Mockito.when(request.getSession()).thenReturn(session);
			spy = Mockito.spy(new Login());
			Mockito.when(spy.getServletConfig()).thenReturn(Mockito.mock(ServletConfig.class));
			ServletContext context = Mockito.mock(ServletContext.class); 
			Mockito.when(context.getRequestDispatcher(response.encodeURL(""))).thenReturn(Mockito.mock(RequestDispatcher.class));
			Mockito.when(spy.getServletContext()).thenReturn(context);
	    }
	    

	    @Test
	    public void testLogin() throws SQLException, ServletException, IOException {
			
			String username = "gmagenta";
			
			String password = "P4ssw0rd!";
			
			
			Mockito.when(request.getParameter("username")).thenReturn(username);
			Mockito.when(request.getParameter("password")).thenReturn(password);
			
			UserModelDS userModel = Mockito.mock(UserModelDS.class);
			RuoloUserModelDS modelRuolo = Mockito.mock(RuoloUserModelDS.class);
			
			//UserBean user= new UserBean(username, password, password, password, password, password, password, password, username, password)
			//Mockito.when(userModel.doSave()).thenReturn(true);
			
			/*AccountDAO accountModel = Mockito.mock(AccountDAO.class);
			UtenteRegistratoDAO utenteModel = Mockito.mock(UtenteRegistratoDAO.class);
			Account acc = new Account(0, username, password, 0);
			UtenteRegistrato utente = new UtenteRegistrato(sesso, nome, cognome, email, Utility.toSqlDate(Utility.formatStringToDate(nascita)), 0);
			Ruoli r = new Ruoli();
			r.addRuolo(Ruoli.Ruolo.CL);
			Mockito.when(accountModel.register(acc, utente, r)).thenReturn(true);
			spy.setAccountDAO(accountModel);
			spy.setUtenteRegistratoDAO(utenteModel);*/
			spy.doPost(request,response);
			/*
			Mockito.verify(request, Mockito.never()).setAttribute("erroreUsername", "true");
			Mockito.verify(request, Mockito.never()).setAttribute("usernameEsistente", "true");
			Mockito.verify(request, Mockito.never()).setAttribute("erroreNome", "true");
			Mockito.verify(request, Mockito.never()).setAttribute("erroreCognome", "true");
			Mockito.verify(request, Mockito.never()).setAttribute("erroreEmail", "true");
			Mockito.verify(request, Mockito.never()).setAttribute("emailEsistente", "true");
			Mockito.verify(request, Mockito.never()).setAttribute("erroreData", "true");
			Mockito.verify(request, Mockito.never()).setAttribute("errorePassword", "true");		
			
			Mockito.verify(response).sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/homepage.jsp"));*/
			String oracle = "Login riuscito";
	        //assertEquals(oracle, request.getAttribute("testMessage"));
	        
	        Mockito.verify(request).getAttribute("testMessage").equals(oracle);
			}
	    @AfterEach
	    void tearDown() throws Exception {
	        request=null;
	        response=null;
	    }
	    
	}
