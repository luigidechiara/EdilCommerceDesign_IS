package model;

import static org.junit.Assert.*;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;





public class RecensisceModelDSTest extends DataSourceBasedDBTestCase {
	private DataSource ds;
	private RecensisceModelDS recensione;

/**
 * @throws java.lang.Exception
 */
@Before
public void setUp() throws Exception {
	super.setUp();
    ds = this.getDataSource();
    recensione = new RecensisceModelDS(ds);
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
    dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/recensisce.sql'");
    dataSource.setUser("sa");
    dataSource.setPassword("");
    return dataSource;
}

@Override
protected IDataSet getDataSet() throws Exception {
	return new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("resources/recensisce.xml"));
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
 public void testDoSave() throws SQLException {
	 RecensisceBean rec= new RecensisceBean("ART00", "luigi", 1, "comoda");
	 boolean r =recensione.doSave(rec);
	 
	 assertEquals(r, true);
	 
 }
 
 @Test
 public void testP() throws SQLException {
	// <recensisce codiceArticolo='ART10' username='luigi' data='ccc' valore='1' testo="aaaa"/>
	 RecensisceBean rec= new RecensisceBean("ART10", "luigi", 1, "aaaa");
	 RecensisceBean po= recensione.doRetriveByKey("luigi", "ART10");
	 
	 assertEquals(rec.getUsername(), po.getUsername());
 }
	

}
