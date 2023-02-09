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

import model.test.UserModelDS;

public class UserModelDSTest {
	private static IDatabaseTester tester;
	private DataSource ds;
	

	private UserModelDS model;
	private UserBean user;

	
	@Before
	public void setUp() throws SQLException, Exception {
		tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/database.sql'", "sa", "");
		tester.setSetUpOperation(DatabaseOperation.REFRESH);
		tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		ds = Mockito.mock(DataSource.class);
		Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
        
		model= new UserModelDS(ds);
		
		user= new UserBean();
		user.setUsername("luigi");
		user.setNome("Luigi");
		user.setCognome("De Chiara");
		user.setEmail("luigi@gmail.com");
		user.setTelefono("089893889");
		user.setIndirizzo("via demanio g");
		user.setUserPassword("Alex123*");
		user.setCitta("Fisciano");
		user.setCap("84085");
		
		model.doSave(user);
	}
	
	@Test
	public void testDoRetriveByKey() throws SQLException {
	    
		UserBean expected= new UserBean();
		expected.setUsername("luigi");
		expected.setNome("Luigi");
		expected.setCognome("De Chiara");
		expected.setEmail("luigi@gmail.com");
		expected.setTelefono("089893889");
		expected.setIndirizzo("via demanio g");
		expected.setUserPassword("Alex123*");
		expected.setCitta("Fisciano");
		expected.setCap("84085");
		
		UserBean actual = null;
    	try {
    		actual=model.doRetriveByKey("luigi");
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assertEquals(expected,actual);
    	
    	}
	
	@Test
	public void testDoSave() throws SQLException {
	    
		UserBean expected= new UserBean();
		expected.setUsername("alex");
		expected.setNome("alex");
		expected.setCognome("Ferrara");
		expected.setEmail("alex@gmail.com");
		expected.setTelefono("089893889");
		expected.setIndirizzo("via demanio g");
		expected.setUserPassword("dechiara123*");
		expected.setCitta("Fisciano");
		expected.setCap("84085");
		
		boolean confronto= false;
		
		UserBean actual = null;
    	try {
    		confronto = model.doSave(expected);
    		actual = model.doRetriveByKey("alex");
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assertEquals(expected,actual);
    	assertEquals(true,confronto);
    	}
	
	@Test
	public void testDoUpdate() throws SQLException {
	    
		UserBean expected= new UserBean();
		expected.setUsername("luigi");
		expected.setNome("Luigi");
		expected.setCognome("De Chiara");
		expected.setEmail("luigi@gmail.com");
		expected.setTelefono("089893889");
		expected.setIndirizzo("via demanio g");
		expected.setUserPassword("Alex123*");
		expected.setCitta("Salerno");
		expected.setCap("84085");
		
		boolean confronto= false;
		
		UserBean actual = null;
    	try {
    		confronto = model.doUpdate(expected,"luigi");
    		actual = model.doRetriveByKey("luigi");
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	assertEquals(expected,actual);
    	assertEquals(true,confronto);
    	}

}
