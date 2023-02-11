package model;


import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import model.test.ArticoloModelDS;

public class ArticoloModelDSTest {
	
	private static IDatabaseTester tester;
	private DataSource ds;


	private ArticoloModelDS model;

	private ArticoloBean articolo;
	private ArticoloBean articolo2;
	
	@Before
	public void setUp() throws SQLException, Exception {
		tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				"jdbc:h2:mem:test1;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/database.sql'", "sa", "");
		tester.setSetUpOperation(DatabaseOperation.REFRESH);
		tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		ds = Mockito.mock(DataSource.class);
		Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());

		model= new ArticoloModelDS(ds);

    	articolo=new ArticoloBean();
		articolo.setCodiceArticolo("ART00");
		articolo.setCosto(80.2);
		articolo.setDescrizione("ciao");
		articolo.setGiacenza(20);
		articolo.setImmagine("prova");
		articolo.setMediaRecensioni(0);
		articolo.setNome("poltrona");
		articolo.setNomeCategoria("arredamento");
		
		 model.doSave(articolo);
		
		articolo2=new ArticoloBean();
		articolo2.setCodiceArticolo("ART01");
		articolo2.setCosto(90.2);
		articolo2.setDescrizione("ciao");
		articolo2.setGiacenza(20);
		articolo2.setImmagine("prova");
		articolo2.setMediaRecensioni(0);
		articolo2.setNome("tegola");
		articolo2.setNomeCategoria("aEsterno");
		 
		 model.doSave(articolo2);

	}
		

	
    @Test
	public void testDoRetriveByKey() throws SQLException {
    	ArticoloBean pre= new ArticoloBean();
		pre.setCodiceArticolo("ART00");
		pre.setCosto(80.2);
		pre.setDescrizione("ciao");
		pre.setGiacenza(20);
		pre.setImmagine("prova");
		pre.setMediaRecensioni(0);
		pre.setNome("poltrona");
		pre.setNomeCategoria("arredamento");    	
		ArticoloBean confronto= null;
    	try {
    		confronto=model.doRetriveByKey("ART00");
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assertEquals(pre,confronto);
    	
    	}
	
    @Test
    public void testDoRetriveAll() {
    	ArticoloBean a1= new ArticoloBean();
      	a1=new ArticoloBean();
  		a1.setCodiceArticolo("ART00");
  		a1.setCosto(80.2);
  		a1.setDescrizione("ciao");
  		a1.setGiacenza(20);
  		a1.setImmagine("prova");
  		a1.setMediaRecensioni(0);
  		a1.setNome("poltrona");
  		a1.setNomeCategoria("arredamento"); 
  		
  		ArticoloBean a2= new ArticoloBean();
      	a2=new ArticoloBean();
  		a2.setCodiceArticolo("ART01");
  		a2.setCosto(90.2);
  		a2.setDescrizione("ciao");
  		a2.setGiacenza(20);
  		a2.setImmagine("prova");
  		a2.setMediaRecensioni(0);
  		a2.setNome("tegola");
  		a2.setNomeCategoria("aEsterno"); 
  		
  		Collection<ArticoloBean> expected = new LinkedList<ArticoloBean>();
  		expected.add(a1);
  		expected.add(a2);
  		
  		Collection<ArticoloBean> actual = new LinkedList<ArticoloBean>();
  		
  		try {

			actual = model.doRetriveAll("nome");

		} catch (SQLException e) {
			e.printStackTrace();

		}
		assertEquals(expected, actual);
  		
    }
    
    @Test
  	public void testDoSave() throws SQLException {
      	ArticoloBean pre= new ArticoloBean();
  		pre.setCodiceArticolo("ART10");
  		pre.setCosto(80.2);
  		pre.setDescrizione("ciao");
  		pre.setGiacenza(20);
  		pre.setImmagine("prova");
  		pre.setMediaRecensioni(0);
  		pre.setNome("Lampada moderna");
  		pre.setNomeCategoria("arredamento");    	
  		boolean confronto=false;
  		ArticoloBean expected= null;
      	try {
      		confronto=model.doSave(pre);
      		expected= model.doRetriveByKey(pre.getCodiceArticolo());
      		
      	}catch(SQLException e) {
      		e.printStackTrace();
      	}
      	assertEquals(true,confronto);
      	assertEquals(pre,expected);
      	
      	}
  	

    @Test
  	public void testDoUpdate() throws SQLException {
      	ArticoloBean pre= new ArticoloBean();
  		pre.setCodiceArticolo("ART00");
  		pre.setCosto(50);
  		pre.setDescrizione("saldi");
  		pre.setGiacenza(30);
  		pre.setImmagine("prova");
  		pre.setMediaRecensioni(0);
  		pre.setNome("poltrona");
  		pre.setNomeCategoria("arredamento");    	
  		boolean confronto=false;
  		ArticoloBean expected= null;
      	try {
      		confronto=model.doUpdate(pre, pre.getCodiceArticolo());
      		expected= model.doRetriveByKey(pre.getCodiceArticolo());
      		
      	}catch(SQLException e) {
      		e.printStackTrace();
      	}
      	assertEquals(true,confronto);
      	assertEquals(pre,expected);
      	
      	}
	
    @Test
  	public void testDoUpdateGiacenza() throws SQLException {
      
    	String codiceArticolo="ART00";
    	int giacenza=30;
    	
  		boolean confronto=false;
  		ArticoloBean expected= null;
      	try {
      		confronto=model.doUpdateGiacenza(giacenza, codiceArticolo);
      		expected= model.doRetriveByKey(codiceArticolo);
      	}catch(SQLException e) {
      		e.printStackTrace();
      	}
      	assertEquals(true,confronto);
      	assertEquals(giacenza,expected.getGiacenza());
      	
      	}
	
    @Test
  	public void testDoRetriveByCategory() throws SQLException {
      	
    	String categoria="arredamento";
    	String prezzo= "";
    	String ordine="";
    	
  		ArticoloBean a1= new ArticoloBean();
      	a1=new ArticoloBean();
  		a1.setCodiceArticolo("ART00");
  		a1.setCosto(80.2);
  		a1.setDescrizione("ciao");
  		a1.setGiacenza(20);
  		a1.setImmagine("prova");
  		a1.setMediaRecensioni(0);
  		a1.setNome("poltrona");
  		a1.setNomeCategoria("arredamento"); 
  		
  		
  		Collection<ArticoloBean> expected = new LinkedList<ArticoloBean>();
  		expected.add(a1);
  		
  		Collection<ArticoloBean> actual = new LinkedList<ArticoloBean>();
      	try {
      		
      		actual= model.doRetriveByCategory(categoria, prezzo, ordine);
      		
      	}catch(SQLException e) {
      		e.printStackTrace();
      	}
      	assertEquals(actual,expected);
      	
      	}
	
    @Test
  	public void testDoSearchByNome() throws SQLException {
      	
    	String nome="poltrona";
    	String prezzo= "";
    	String ordine="";
    	
  		ArticoloBean a1= new ArticoloBean();
      	a1=new ArticoloBean();
  		a1.setCodiceArticolo("ART00");
  		a1.setCosto(80.2);
  		a1.setDescrizione("ciao");
  		a1.setGiacenza(20);
  		a1.setImmagine("prova");
  		a1.setMediaRecensioni(0);
  		a1.setNome("poltrona");
  		a1.setNomeCategoria("arredamento"); 
  		
  		
  		Collection<ArticoloBean> expected = new LinkedList<ArticoloBean>();
  		expected.add(a1);
  		
  		Collection<ArticoloBean> actual = new LinkedList<ArticoloBean>();
      	try {
      		
      		actual= model.doSearchByNome(nome, prezzo, ordine);
      		
      	}catch(SQLException e) {
      		e.printStackTrace();
      	}
      	assertEquals(actual,expected);
      	
      	}
	
	
}
