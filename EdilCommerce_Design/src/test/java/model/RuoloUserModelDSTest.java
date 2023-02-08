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

import model.test.RuoloUserModelDS;

public class RuoloUserModelDSTest {
	private static IDatabaseTester tester;
	private DataSource ds;
	

	private RuoloUserModelDS model;

	
	@Before
	public void setUp() throws SQLException, Exception {
		tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/database.sql'", "sa", "");
		tester.setSetUpOperation(DatabaseOperation.REFRESH);
		tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		ds = Mockito.mock(DataSource.class);
		Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
        
		model= new RuoloUserModelDS(ds);
		RuoloUserBean ruolo= new RuoloUserBean();
		   ruolo.setNome("admin");
		   ruolo.setUsername("xander");
		 model.doSave(ruolo);
	}
	
	@Test
	public void testdoRetriveByOneKey() throws SQLException{
		RuoloUserBean expected= new RuoloUserBean();
		   expected.setNome("admin");
		   expected.setUsername("xander");
		   
		   
		   Collection<RuoloUserBean> expectedc = new LinkedList<RuoloUserBean>();
	  		expectedc.add(expected);
	  	
	  		
	  		Collection<RuoloUserBean> actual = new LinkedList<RuoloUserBean>();   
		
		   try {
			   actual=model.doRetriveByOneKey("xander");
		   }catch(SQLException e) {
			   e.printStackTrace();
		   }
		 assertEquals(actual,expectedc);
		
	}
	
	@Test
	public void testdoSave() throws SQLException{
		RuoloUserBean expected= new RuoloUserBean();
		   expected.setNome("admin");
		   expected.setUsername("leonardo");
		   Collection<RuoloUserBean> expectedc = new LinkedList<RuoloUserBean>();
	  		expectedc.add(expected);
	  	
	  		
	  		Collection<RuoloUserBean> actual = new LinkedList<RuoloUserBean>();   
		   
		   
		   boolean verifica=false;
		   verifica=model.doSave(expected);
		  
		   try {
			   actual=model.doRetriveByOneKey("leonardo");
		   }catch(SQLException e) {
			   e.printStackTrace();
		   }
		   assertEquals(true,verifica);
		   assertEquals(expectedc,actual);
		
	}
}
