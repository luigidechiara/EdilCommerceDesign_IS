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

import model.test.ContrassegnoModelDS;

public class ContrassegnoModelDSTest {
	private static IDatabaseTester tester;
	private DataSource ds;
	

	private ContrassegnoModelDS model;
	private ContrassegnoBean contrassegno;
	
	@Before
	public void setUp() throws SQLException, Exception {
		tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/database.sql'", "sa", "");
		tester.setSetUpOperation(DatabaseOperation.REFRESH);
		tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		ds = Mockito.mock(DataSource.class);
		Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
        
		model= new ContrassegnoModelDS(ds);
		
		contrassegno = new ContrassegnoBean();
		contrassegno.setNumeroOrdine(1);
		
		model.doSave(contrassegno);
		    	
	}
	
	@Test
	public void testDoRetriveByKey() throws SQLException {
		
		ContrassegnoBean expected = new ContrassegnoBean();
		expected.setNumeroOrdine(1);
		
		ContrassegnoBean actual = new ContrassegnoBean();
    	try {
    		actual=model.doRetriveByKey(1);
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assertEquals(expected,actual);
    	
    	}
	
	@Test
	public void testDoSave() throws SQLException {
		
		ContrassegnoBean expected = new ContrassegnoBean();
		expected.setNumeroOrdine(2);
		
		ContrassegnoBean actual = new ContrassegnoBean();
		boolean confronto= false;
    	try {
    		confronto = model.doSave(expected);
    		actual=model.doRetriveByKey(2);
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assertEquals(expected,actual);
    	assertEquals(confronto,true);
    	}

}
