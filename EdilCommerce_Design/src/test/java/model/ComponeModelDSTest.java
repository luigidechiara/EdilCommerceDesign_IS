package model;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import model.test.ComponeModelDS;

public class ComponeModelDSTest {
	private static IDatabaseTester tester;
	private DataSource ds;
	

	private ComponeModelDS model;
	private ComponeBean compone;
	private ComponeBean compone1;
	
	@Before
	public void setUp() throws SQLException, Exception {
		tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/database.sql'", "sa", "");
		tester.setSetUpOperation(DatabaseOperation.REFRESH);
		tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		ds = Mockito.mock(DataSource.class);
		Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
        
		model= new ComponeModelDS(ds);
		
		compone= new ComponeBean();
		compone.setNumeroOrdine(1);
		compone.setCodiceArticolo("ART00");
		compone.setQuantita(10);
		
		model.doSave(compone);
		
		compone1= new ComponeBean();
		compone1.setNumeroOrdine(1);
		compone1.setCodiceArticolo("ART02");
		compone1.setQuantita(2);
		
		model.doSave(compone1);
		    	
	}
	
	@Test
	public void testDoRetriveByOneKey() throws SQLException {
		ComponeBean comp1= new ComponeBean();
		comp1.setNumeroOrdine(1);
		comp1.setCodiceArticolo("ART00");
		comp1.setQuantita(10);
		
		ComponeBean comp2= new ComponeBean();
		comp2.setNumeroOrdine(1);
		comp2.setCodiceArticolo("ART02");
		comp2.setQuantita(2);
		
		Collection<ComponeBean> expected = new LinkedList<ComponeBean>();
    	expected.add(comp1);
    	expected.add(comp2);
    	
    	Collection<ComponeBean> actual = new LinkedList<ComponeBean>();
    	
    	try {
    		actual=model.doRetriveByOneKey("1");
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assertEquals(expected,actual);
    	
    	}
	
	
	@Test
	public void testDoSave() throws SQLException {
		ComponeBean comp1= new ComponeBean();
		comp1.setNumeroOrdine(2);
		comp1.setCodiceArticolo("ART00");
		comp1.setQuantita(10);
    	
		Collection<ComponeBean> expected1 = new LinkedList<ComponeBean>();
    	expected1.add(comp1);
    	
    	Collection<ComponeBean> actual = new LinkedList<ComponeBean>();
		boolean confronto= false;
    	try {
    		confronto=model.doSave(comp1);
    		actual= model.doRetriveByOneKey("2");
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assertEquals(expected1,actual);
    	assertEquals(confronto,true);
    	
    	}

}
