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
import model.test.InfoFatturazioneModelDS;
public class InfoFatturazioneModelDSTest {

	
	private static IDatabaseTester tester;
	private DataSource ds;
	private InfoFatturazioneModelDS model;
	private InfoFatturazioneBean fattura;
	
	@Before
	public void setUp() throws SQLException, Exception {
		
		tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/database.sql'", "sa", "");
		tester.setSetUpOperation(DatabaseOperation.REFRESH);
		tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		ds = Mockito.mock(DataSource.class);
		Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
		
		model= new InfoFatturazioneModelDS(ds);
		
	    fattura= new InfoFatturazioneBean();
	
		fattura.setNumeroOrdine(2);
		fattura.setNome("alessio");
		fattura.setCognome("ferro");
		fattura.setEmail("admin@email.com");
		fattura.setTelefono("089893889");
		fattura.setIndirizzo("via lamberti");
		fattura.setCitta("salerno");
		fattura.setStato("italia");
		fattura.setCap("84085");
		
		model.doSave(fattura);
		
		
	}	
	
	
	
	 	@Test
		public void testdoRetriveByKey() throws SQLException {
	 		
	 		InfoFatturazioneBean pre= new InfoFatturazioneBean();
			
			pre.setNumeroOrdine(2);
			pre.setNome("alessio");
			pre.setCognome("ferro");
			pre.setEmail("admin@email.com");
			pre.setTelefono("089893889");
			pre.setIndirizzo("via lamberti");
			pre.setCitta("salerno");
			pre.setStato("italia");
			pre.setCap("84085");
		 
			InfoFatturazioneBean confronto=null;
					try {
						
						confronto=model.doRetriveByKey(2);
						
					}catch(SQLException e) {
						e.printStackTrace();
					}
			
			assertEquals(pre,confronto);
		 
	 }
	 	
	 	@Test
		public void testdoSave() throws SQLException {
	 		InfoFatturazioneBean pre= new InfoFatturazioneBean();
			
	 		pre.setNumeroOrdine(1);
			pre.setNome("alessandro");
			pre.setCognome("ferro");
			pre.setEmail("admin@email.com");
			pre.setTelefono("089893889");
			pre.setIndirizzo("via lamberti");
			pre.setCitta("salerno");
			pre.setStato("italia");
			pre.setCap("84085");
			
			boolean verifica= false;
			InfoFatturazioneBean expected= null;
			
			try {
			verifica=model.doSave(pre);
			expected=model.doRetriveByKey(1);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			assertEquals(true,verifica);
	      	assertEquals(pre,expected);
	 	}
	 	
	 	
	

}
