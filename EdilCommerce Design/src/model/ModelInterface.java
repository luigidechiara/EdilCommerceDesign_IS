package model;

import java.sql.SQLException;
import java.util.Collection;

public interface ModelInterface<T> {
	
	public T doRetriveByKey(String code) throws SQLException;
	
	public T doRetriveByKey(int code) throws SQLException;

	public Collection<T> doRetriveAll(String order) throws SQLException;
	
	public void doSave(T item) throws SQLException;
	
	public void doUpdate(T item, String code) throws SQLException;
	
	public void doDelete(T item) throws SQLException;
}
