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

import model.test.CartaModelDS;


public class CartaModelDSTest {

	private static IDatabaseTester tester;
	private DataSource ds;
	

	private CartaModelDS model;
	private CartaBean carta;
	
	@Before
	public void setUp() throws SQLException, Exception {
		tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/database.sql'", "sa", "");
		tester.setSetUpOperation(DatabaseOperation.REFRESH);
		tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		ds = Mockito.mock(DataSource.class);
		Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
        
		model= new CartaModelDS(ds);
		
		carta= new CartaBean();
		carta.setNumeroOrdine(1);
		carta.setCvv("123");
		carta.setDataScadenza("2024-10");
		carta.setIntestatario("Luigi De Chiara");
		carta.setNumero("1111222233334444");
		
		model.doSave(carta);
		    	
	}
	
	@Test
	public void testDoRetriveByKey() throws SQLException {
	    CartaBean expected = new CartaBean();
		expected.setNumeroOrdine(1);
		expected.setCvv("123");
		expected.setDataScadenza("2024-10");
		expected.setIntestatario("Luigi De Chiara");
		expected.setNumero("1111222233334444");
    	
		CartaBean actual = null;
    	try {
    		actual=model.doRetriveByKey(1);
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assertEquals(expected,actual);
    	
    	}
	
	@Test
	public void testDoSave() throws SQLException {
	    CartaBean expected = new CartaBean();
		expected.setNumeroOrdine(2);
		expected.setCvv("456");
		expected.setDataScadenza("2023-10");
		expected.setIntestatario("Alex Ferrara");
		expected.setNumero("1111222233334445");
    	
		CartaBean actual = null;
		boolean confronto= false;
    	try {
    		confronto= model.doSave(expected);
    		actual=model.doRetriveByKey(2);
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assertEquals(confronto,true);
    	assertEquals(expected,actual);
    	}
}
