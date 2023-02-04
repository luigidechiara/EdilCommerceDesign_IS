package model;

import org.dbunit.DataSourceBasedDBTestCase;

import org.dbunit.dataset.IDataSet;

import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;


import javax.sql.DataSource;





public class TestArticoloModelDS extends DataSourceBasedDBTestCase{

	private DataSource ds;
	private ArticoloModelDS articoloModel;

/**
 * @throws java.lang.Exception
 */
@Before
public void setUp() throws Exception {
	super.setUp();
    ds = this.getDataSource();
    articoloModel = new ArticoloModelDS(ds);
}

/**
 * @throws java.lang.Exception
 */
@After
public void tearDown() throws Exception {
	 super.tearDown();
}

@Override
protected DataSource getDataSource() {
	JdbcDataSource dataSource = new JdbcDataSource();
    dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/articolo.sql'");
    dataSource.setUser("sa");
    dataSource.setPassword("");
    return dataSource;
}

@Override
protected IDataSet getDataSet() throws Exception {
	return new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("resources/articolo.xml"));
}

 @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

 @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }
	
		
	@Test
	public void doRetriveByKeyTest() throws SQLException  {
			
		
			
		ArticoloBean oracolo= new ArticoloBean("ART00", "poltrona bianca", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/poltrona.jpg", "poltrona in pelle bianca, dotata di un ottimo comfort ed eleganza (90x150)", 79.99, "Arredamento interno",0,20);
		//ArticoloBean oracolo2= new ArticoloBean("ART00", "poltrona bianca", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/poltrona.jpg", "poltrona in pelle bianca, dotata di un ottimo comfort ed eleganza (90x150)", 79.99, "Arredamento interno",0,20);
		String codice="ART00";
		ArticoloBean attuale = null;
		
			 attuale=articoloModel.doRetriveByKey(codice);
		     assertEquals(oracolo,attuale);
			
		
		
	
	}
	
	@Test
	public void doSaveTest() throws Exception {
		
	
		
		ArticoloBean oracolo= new ArticoloBean("ART01", "poltrona bianca", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/poltrona.jpg", "poltrona in pelle bianca, dotata di un ottimo comfort ed eleganza (90x150)", 80, "Arredamento interno",0,20);
		
		boolean attuale=articoloModel.doSave(oracolo);
		assertEquals(oracolo,true);
	
	}
	
	
}
