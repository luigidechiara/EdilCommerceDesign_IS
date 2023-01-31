package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import utils.Utility;

public class CartaModelDS implements ModelInterface<CartaBean> {

	private DataSource ds = null;
	
	public CartaModelDS(DataSource ds) {
		this.ds = ds;
	}

	
	public CartaBean doRetriveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public CartaBean doRetriveByKey(int code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM carta WHERE numeroOrdine=?";
		
		CartaBean bean = new CartaBean();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setInt(1, code);
			
			Utility.print("doRetriveByOneKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bean.setNumeroOrdine(rs.getInt("numeroOrdine"));
				bean.setNumero(rs.getString("numero"));
				bean.setIntestatario(rs.getString("intestatario"));
				bean.setDataScadenza(rs.getString("dataScadenza"));
				bean.setCvv(rs.getString("cvv"));
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

	
	public Collection<CartaBean> doRetriveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM carta";
		
		Collection<CartaBean> collection = new LinkedList<CartaBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			Utility.print("doRetriveAll " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CartaBean bean = new CartaBean();
				bean.setNumeroOrdine(rs.getInt("numeroOrdine"));
				bean.setNumero(rs.getString("numero"));
				bean.setIntestatario(rs.getString("intestatario"));
				bean.setDataScadenza(rs.getString("dataScadenza"));
				bean.setCvv(rs.getString("cvv"));
				collection.add(bean);
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
		return collection;
	}

	
	public void doSave(CartaBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String InsertSQL = "INSERT INTO carta VALUES (?,?,?,?,?)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(InsertSQL);
			
			ps.setInt(1, item.getNumeroOrdine());
			ps.setString(2, item.getNumero());
			ps.setString(3, item.getIntestatario());
			ps.setString(4, item.getDataScadenza());
			ps.setString(5, item.getCvv());
			
			Utility.print("doSave: " + ps.toString());
			
			ps.executeUpdate();

		} finally {
			try {
				if(ps != null)
					ps.close();
			} finally {
				if(con != null)
					con.close();
			}
		}
	}


	public void doUpdate(CartaBean item, String code) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	public void doDelete(CartaBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
