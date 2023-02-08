package model;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import model.test.OrdineModelDS;

public class OrdineModelDSTest {
	
	private static IDatabaseTester tester;
	private DataSource ds;
	private OrdineModelDS model;
	private Date data;
	
	@Before
	public void setUp() throws SQLException, Exception {
		tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/database.sql'", "sa", "");
		tester.setSetUpOperation(DatabaseOperation.REFRESH);
		tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		ds = Mockito.mock(DataSource.class);
		Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
		data= new Date(System.currentTimeMillis());
		model= new OrdineModelDS(ds);
		
		OrdineBean ordine= new OrdineBean();
		ordine.setNumeroOrdine(1);
		ordine.setUsername("xander");
		ordine.setImporto(120);
		
    	
		OrdineBean ordine1= new OrdineBean();
		ordine1.setNumeroOrdine(2);
		ordine1.setUsername("cavallo99");
		ordine1.setImporto(120);
		
		
		model.doSave(ordine);
		model.doSave(ordine1);
    	
		 
    	
	}
	
	@Test
	public void testdoRetriveByKey() throws SQLException {
		
	OrdineBean expected= new OrdineBean();
	expected.setUsername("xander");
	expected.setNumeroOrdine(1);
	expected.setImporto(120);	
	expected.setData(data);
	OrdineBean actual= null;
	
	try {
		actual=model.doRetriveByKey(1);
		
	} catch(SQLException e) {
		e.printStackTrace();
		
	}
		assertEquals(expected,actual);
		
	}
	
	
	@Test
	public void testdoSave() throws SQLException{
		OrdineBean actual=null;
		OrdineBean pre= new OrdineBean();
		pre.setUsername("nicolle");
		pre.setNumeroOrdine(5);
		pre.setImporto(120);	
		pre.setData(data);
		boolean verifica=false;
		verifica=model.doSave(pre);
		
		try {
			actual=model.doRetriveByKey(5);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals(verifica,true);
		assertEquals(actual,pre);
	}
	
	@Test
	public void testdoRetriveByAll() throws SQLException{

		OrdineBean pre1= new OrdineBean();
		pre1.setUsername("xander");
		pre1.setNumeroOrdine(1);
		pre1.setImporto(120);	
		pre1.setData(data);
		
		OrdineBean pre2= new OrdineBean();
		pre2.setUsername("cavallo99");
		pre2.setNumeroOrdine(2);
		pre2.setImporto(120);	
		pre2.setData(data);
	
		
		Collection<OrdineBean> expected = new LinkedList<OrdineBean>();
  		expected.add(pre1);
  		expected.add(pre2);
  		
  		Collection<OrdineBean> actual = new LinkedList<OrdineBean>();
		
  		try {
  			
  			actual=model.doRetriveAll("");
  			
  		}catch(SQLException e) {
  			e.printStackTrace();
  		}
  		assertEquals(expected, actual);
	}
	
	@Test
	public void testdoUpdateImporto() throws SQLException{
		OrdineBean expected= new OrdineBean();
		expected.setUsername("xander");
		expected.setNumeroOrdine(1);
		expected.setImporto(150);	
		expected.setData(data);
		boolean verifica= false;
		OrdineBean actual= null;
		
		verifica=model.doUpdateImporto(expected);
		
		try {
			
			actual=model.doRetriveByKey(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		assertEquals(true,verifica);
		assertEquals(expected,actual);
	}
	
	@Test
	public void testdoRetriveByUser() throws SQLException{
		
		OrdineBean expected= new OrdineBean();
		expected.setUsername("xander");
		expected.setNumeroOrdine(1);
		expected.setImporto(120);	
		expected.setData(data);

		Collection<OrdineBean> atteso = new LinkedList<OrdineBean>();
  		atteso.add(expected);
  		
  		Collection<OrdineBean> actual = new LinkedList<OrdineBean>();
		
  		try {
  			
  			actual=model.doRetriveByUser("xander");
  			
  		}catch(SQLException e) {
  			e.printStackTrace();
  		}
  		assertEquals(atteso, actual);
		
		
	}
	
	
}
