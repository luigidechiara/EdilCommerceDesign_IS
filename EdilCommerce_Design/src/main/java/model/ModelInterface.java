package model;

import java.sql.SQLException;
import java.util.Collection;

public interface ModelInterface<T> {
	
	public T doRetriveByKey(String code) throws SQLException;
	
	public T doRetriveByKey(int code) throws SQLException;

	public Collection<T> doRetriveAll(String order) throws SQLException;
	
	public boolean doSave(T item) throws SQLException;
	
	public boolean doUpdate(T item, String code) throws SQLException;
	
	public boolean doDelete(T item) throws SQLException;
}
