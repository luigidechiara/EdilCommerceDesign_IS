package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import utils.Utility;

public class CategoriaModelDS implements ModelInterface<CategoriaBean> {
	
	private DataSource ds = null;
	
	public CategoriaModelDS(DataSource ds) {
		this.ds = ds;
	}

	
	public CategoriaBean doRetriveByKey(String code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectSQL = "SELECT * FROM categoria WHERE nome=?";
		
		CategoriaBean bean = new CategoriaBean();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectSQL);
			
			ps.setString(1, code);
			
			Utility.print("doRetriveByKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bean.setImmagine(rs.getString("immagine"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
			}
			
		} finally {
			try {
				if(ps != null)
					ps.close();
			} finally {
				if(con != null)
					con.close();
				if (rs != null)
					rs.close();
			}
		}
		
		return bean;
	}

	
	public CategoriaBean doRetriveByKey(int code) throws SQLException {
		return null;
	}

	
	public Collection<CategoriaBean> doRetriveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectSQL = "SELECT * FROM categoria";

		if(order != null && !order.equals(""))
			if(order.equalsIgnoreCase("nome") || order.equalsIgnoreCase("descrizione"))
				selectSQL += " ORDER BY " + order;
		
		Collection<CategoriaBean> collection = new LinkedList<CategoriaBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectSQL);

			Utility.print("doRetriveAll: " + ps.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				CategoriaBean bean = new CategoriaBean();
				
				bean.setImmagine(rs.getString("immagine"));
				bean.setNome(rs.getString("nome"));
				bean.setDescrizione(rs.getString("descrizione"));
				
				collection.add(bean);
			}

		} finally {
			try {
				if (ps != null)
					ps.close();
			} finally {
				if (con != null)
					con.close();
				if (rs != null)
					rs.close();
			}
		}

		return collection;
	}

	
	public boolean doSave(CategoriaBean item) throws SQLException {
		return false;
	}

	
	public boolean doUpdate(CategoriaBean item, String code) throws SQLException {
		return false;
	}

	
	public boolean doDelete(CategoriaBean item) throws SQLException {
		return false;
	}

}
