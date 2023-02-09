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

import model.test.CategoriaModelDS;

public class CategoriaModelDSTest {
	private static IDatabaseTester tester;
	private DataSource ds;
	

	private CategoriaModelDS model;
	private CategoriaBean categoria;
	
	@Before
	public void setUp() throws SQLException, Exception {
		tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/database.sql'", "sa", "");
		tester.setSetUpOperation(DatabaseOperation.REFRESH);
		tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		ds = Mockito.mock(DataSource.class);
		Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
        
		model= new CategoriaModelDS(ds);
		
		categoria= new CategoriaBean();
		categoria.setImmagine("immagine");
		categoria.setNome("Vernici");
		categoria.setDescrizione("");
		
		model.doSave(categoria);
		    	
	}
	
	@Test
	public void testDoRetriveByKey() throws SQLException {
		CategoriaBean expected= new CategoriaBean();
		expected.setImmagine("immagine");
		expected.setNome("Vernici");
		expected.setDescrizione("");
    	
		CategoriaBean actual = null;
    	try {
    		actual=model.doRetriveByKey("Vernici");
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assertEquals(expected,actual);
    	
    	}
	
	@Test
	public void testDoSave() throws SQLException {
		CategoriaBean expected= new CategoriaBean();
		expected.setImmagine("immagine");
		expected.setNome("Rivestimento");
		expected.setDescrizione("");
    	
		CategoriaBean actual = null;
		boolean confronto= false;
    	try {
    		confronto=model.doSave(expected);
    		actual= model.doRetriveByKey("Rivestimento");
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	//assertEquals(expected,actual);
    	assertEquals(confronto,true);
    	
    	}
}
