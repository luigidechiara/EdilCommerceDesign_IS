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
import org.mockito.Mockito;



import model.test.RecensisceModelDS;
import model.test.ArticoloModelDS;

public class RecensisceModelDSTest {

	private static IDatabaseTester tester;
	private DataSource ds;
	private RecensisceModelDS model;
	private RecensisceBean recensisce1;
	private RecensisceBean recensisce2;
	private Date data;
	private ArticoloModelDS modelart;
	private ArticoloBean articolo;
	private ArticoloBean articolo2;
	@Before
	public void setUp() throws SQLException, Exception {
		tester = new JdbcDatabaseTester(org.h2.Driver.class.getName(),
				"jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/recensisce.sql'", "sa", "");
		tester.setSetUpOperation(DatabaseOperation.REFRESH);
		tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		ds = Mockito.mock(DataSource.class);
		Mockito.when(ds.getConnection()).thenReturn(tester.getConnection().getConnection());
		data = new Date(System.currentTimeMillis());
		model= new RecensisceModelDS(ds);
		modelart=new ArticoloModelDS(ds);
		articolo=new ArticoloBean();
		
		articolo.setCodiceArticolo("ART05");
		articolo.setCosto(80.2);
		articolo.setDescrizione("ciao");
		articolo.setGiacenza(20);
		articolo.setImmagine("prova");
		articolo.setMediaRecensioni(0);
		articolo.setNome("poltrona");
		articolo.setNomeCategoria("mobili");
		modelart.doSave(articolo);
		
		articolo2=new ArticoloBean();
		articolo2.setCodiceArticolo("ART07");
		articolo2.setCosto(98);
		articolo2.setDescrizione("mobile cucina");
		articolo2.setGiacenza(20);
		articolo2.setImmagine("prova");
		articolo2.setMediaRecensioni(0);
		articolo2.setNome("cassettiera");
		articolo2.setNomeCategoria("mobili");
		modelart.doSave(articolo2);
		
		recensisce1=new RecensisceBean();
		
		recensisce1.setCodiceArticolo("ART05");
		recensisce1.setTesto("bellissimo");
		recensisce1.setUsername("xander");
		recensisce1.setValore(4);
		
		recensisce2=new RecensisceBean();
		
		recensisce2.setCodiceArticolo("ART07");
		recensisce2.setTesto("bellissimo");
		recensisce2.setUsername("luigio");
		recensisce2.setValore(2);
		
		model.doSave(recensisce1);
		model.doSave(recensisce2);
		
		
    	
	}
	@Test
	public void testdoRetriveByKey() throws SQLException{
		RecensisceBean expected=new RecensisceBean();
		
		expected.setCodiceArticolo("ART05");
		expected.setDate(data);
		expected.setTesto("bellissimo");
		expected.setUsername("xander");
		expected.setValore(4);
		
		RecensisceBean actual=null;
		
		try {
			actual=model.doRetriveByKey("xander", "ART05");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected,actual);
		
		
	}
	@Test
	public void testdoRetriveByOneKey() throws SQLException{
		recensisce1=new RecensisceBean();
		
		recensisce1.setCodiceArticolo("ART05");
		recensisce1.setTesto("bellissimo");
		recensisce1.setUsername("xander");
		recensisce1.setValore(4);
		

		Collection<RecensisceBean> expected=new LinkedList<RecensisceBean>();
		expected.add(recensisce1);
	
		Collection<RecensisceBean> actual=new LinkedList<RecensisceBean>();
		
		try {
			actual=model.doRetriveByOneKey("xander");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected,actual);
	}
	
	
	@Test
	public void testdoRetriveByCodiceArticolo() throws SQLException{
		
recensisce1=new RecensisceBean();
		
		recensisce1.setCodiceArticolo("ART05");
		recensisce1.setTesto("bellissimo");
		recensisce1.setUsername("xander");
		recensisce1.setValore(4);
		
		Collection<RecensisceBean> expected=new LinkedList<RecensisceBean>();
		expected.add(recensisce1);
		
		Collection<RecensisceBean> actual=new LinkedList<RecensisceBean>();
		
		try {
			actual=model.doRetriveByCodiceArticolo("ART05");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected,actual);
	}
	
	
	@Test
	public void testdoSave() throws SQLException{
		
		RecensisceBean expected=new RecensisceBean();
		
		expected.setCodiceArticolo("ART07");
		expected.setDate(data);
		expected.setTesto("bellissimo");
		expected.setUsername("luigi");
		expected.setValore(2);
		boolean verifica=false;
		RecensisceBean actual=null;
		
		try {
			verifica=model.doSave(expected);
			actual=model.doRetriveByKey("luigi", "ART07");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void testdoUpdate() throws SQLException{

		RecensisceBean expected=new RecensisceBean();
		expected.setCodiceArticolo("ART07");
		expected.setDate(data);
		expected.setTesto("troppo bellino");
		expected.setUsername("luigi");
		expected.setValore(4);
	
		RecensisceBean actual=null;
		try {
			
			actual=model.doRetriveByKey("luigi","ART07");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		assertEquals(expected,actual);
	}
	
	@Test
	public void testdoDelete() throws SQLException{
		
	}
	
}
