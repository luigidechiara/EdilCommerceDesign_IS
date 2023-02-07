package model;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import model.test.ArticoloModelDS1;

public class TestArticoloModelDS {
	
	private static IDatabaseTester tester;
	private DataSource ds;

	private ArticoloModelDS1 model;
	private ArticoloBean articolo;
	
	@Before
	public void setUp() throws SQLException, Exception {
		tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/articolo.sql'", "sa", "");
		tester.setSetUpOperation(DatabaseOperation.REFRESH);
		tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		ds = Mockito.mock(DataSource.class);
		Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
		model= new ArticoloModelDS1(ds);
		
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
    	
	}
	

	
    @Test
	public void doRetriveByKeyTest() throws SQLException {
    	ArticoloBean pre= new ArticoloBean();
    	pre=new ArticoloBean();
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
	
	
	
	
	
	
	
	
	
	
}
