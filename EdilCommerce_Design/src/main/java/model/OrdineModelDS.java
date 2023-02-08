package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import utils.Utility;

public class OrdineModelDS implements ModelInterface<OrdineBean> {
	
	private DataSource ds = null;
	
	public OrdineModelDS(DataSource ds) {
		this.ds = ds;
	}

	 
	public OrdineBean doRetriveByKey(String code) throws SQLException {
		return null;
	}

	 
	public OrdineBean doRetriveByKey(int code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM ordine WHERE numeroOrdine=?";
		
		OrdineBean bean = new OrdineBean();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setInt(1, code);
			
			Utility.print("doRetriveByKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setData(rs.getDate("data"));
				bean.setNumeroOrdine(rs.getInt("numeroOrdine"));
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

	 
	public Collection<OrdineBean> doRetriveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM ordine";
		
		Collection<OrdineBean> collection = new LinkedList<OrdineBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			Utility.print("doRetriveAll: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setUsername(rs.getString("username"));
				bean.setData(rs.getDate("data"));
				bean.setNumeroOrdine(rs.getInt("numeroOrdine"));
				bean.setImporto(rs.getDouble("importo"));
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

	 
	public boolean doSave(OrdineBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String InsertSQL = "INSERT INTO ordine (data, username,importo) VALUES (NOW(),?,?)";
		
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(InsertSQL);
				
				ps.setString(1, item.getUsername());
				ps.setDouble(2, item.getImporto());

				Utility.print("doSave: " + ps.toString());

				if(ps.executeUpdate()==0)
					return false;
				
				return true;

			} finally {
				try {
					if (ps != null)
						ps.close();
				} finally {
					if (con != null)
						con.close();
				}
			}
		
	}

	 
	public boolean doUpdate(OrdineBean item, String code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
		
	}
	public boolean doUpdateImporto(OrdineBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String InsertSQL = "UPDATE ordine SET importo = ? where numeroOrdine=?";
		
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(InsertSQL);
				
				ps.setDouble(1, item.getImporto());
				ps.setInt(2, item.getNumeroOrdine());

				Utility.print("doSave: " + ps.toString());

				if(ps.executeUpdate()==0)
					return false;
				
				return true;

			} finally {
				try {
					if (ps != null)
						ps.close();
				} finally {
					if (con != null)
						con.close();
				}
			}
		
	}
	 
	public boolean doDelete(OrdineBean item) throws SQLException {
		// TODO Auto-generated method stub
		return false;
		
	}

	public Collection<OrdineBean> doRetriveByUser(String code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM ordine WHERE username=?";
		
		Collection<OrdineBean> collection = new LinkedList<OrdineBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setString(1, code);
			
			Utility.print("doRetriveByUser: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setUsername(rs.getString("username"));
				bean.setData(rs.getDate("data"));
				bean.setNumeroOrdine(rs.getInt("numeroOrdine"));
				bean.setImporto(rs.getDouble("importo"));
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
}

