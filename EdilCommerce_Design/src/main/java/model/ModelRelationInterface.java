package model;

import java.sql.SQLException;
import java.util.Collection;

public interface ModelRelationInterface<T> {
	
	public T doRetriveByKey(String code1, String code2) throws SQLException;
	
	public Collection<T> doRetriveByOneKey(String code) throws SQLException;

	public Collection<T> doRetriveAll(String order) throws SQLException;
	
	public boolean doSave(T item) throws SQLException;
	
	public boolean doUpdate(T item) throws SQLException;
	
	public boolean doDelete(T item) throws SQLException;

}
