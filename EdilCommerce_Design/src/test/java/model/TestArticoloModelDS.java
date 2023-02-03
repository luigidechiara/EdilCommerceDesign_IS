package model;



import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class TestArticoloModelDS {

	private static IDatabaseTester tester;
	private ArticoloModelDS ArticoloModel;
	
	
	 
    @BeforeAll
	static void setUpAll() throws ClassNotFoundException {
	    // mem indica che il DB deve andare in memoria
	    // test indica il nome del DB
	    // DB_CLOSE_DELAY=-1 impone ad H2 di eliminare il DB solo quando il processo della JVM termina
	    tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
	            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:/resources/database.sql'",
	            "sa",
	            ""
	    );
	    // Refresh permette di svuotare la cache dopo un modifica con setDataSet
	    // DeleteAll ci svuota il DB manteneno lo schema
	    tester.setSetUpOperation(DatabaseOperation.REFRESH);
	    tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
	}
	
	

	  private static void refreshDataSet(String filename) throws Exception {
		    IDataSet initialState = new FlatXmlDataSetBuilder().build(TestArticoloModelDS.class.getClassLoader().getResourceAsStream(filename));
		    tester.setDataSet(initialState);
		    tester.onSetup();
		}
	
    
	  
		@BeforeEach
		public void setUp() throws Exception {
		    // Prepara lo stato iniziale di default
		   refreshDataSet("src/test/java/resources/init.xml");
		    DataSource ds = Mockito.mock(DataSource.class);
		    Mockito.when(ds.getConnection()).thenReturn((Connection) tester.getConnection());
		    ArticoloModel = new ArticoloModelDS(ds);
		}
		
		
		
		@AfterEach
		public void tearDown() throws Exception {
		    tester.onTearDown();
		}

		
	@Test
	public void doRetriveByKeyTest() throws Exception {
		
	
		
	ArticoloBean oracolo= new ArticoloBean("ART00", "poltrona bianca", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/poltrona.jpg", "poltrona in pelle bianca, dotata di un ottimo comfort ed eleganza (90x150)", 79.99, "Arredamento interno",0,20);
	ArticoloBean oracolo2= new ArticoloBean("ART00", "poltrona bianca", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/poltrona.jpg", "poltrona in pelle bianca, dotata di un ottimo comfort ed eleganza (90x150)", 79.99, "Arredamento interno",0,20);

	//ArticoloBean attuale=ArticoloModel.doRetriveByKey("ART00");
	assertEquals(oracolo,oracolo2);

	}
	
	
	
	
	
	
	
	
	
	
}
