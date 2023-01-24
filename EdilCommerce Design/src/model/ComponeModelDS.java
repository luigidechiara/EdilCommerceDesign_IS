package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import utils.Utility;

public class ComponeModelDS implements ModelRelationInterface<ComponeBean>{
	
	private DataSource ds = null;
		
	public ComponeModelDS(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public ComponeBean doRetriveByKey(String code1, String code2) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ComponeBean> doRetriveByOneKey(String code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM compone WHERE numeroOrdine=?";
		
		Collection<ComponeBean> collection = new LinkedList<ComponeBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setInt(1, Integer.parseInt(code));
			
			Utility.print("doRetriveByOneKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ComponeBean bean = new ComponeBean();
				bean.setCodiceArticolo(rs.getString("codiceArticolo"));
				bean.setNumeroOrdine(Integer.parseInt(rs.getString("numeroOrdine")));
				bean.setQuantit�(Integer.parseInt(rs.getString("quantita")));
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

	@Override
	public Collection<ComponeBean> doRetriveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM compone";
		
		Collection<ComponeBean> collection = new LinkedList<ComponeBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			Utility.print("doRetriveAll: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ComponeBean bean = new ComponeBean();
				bean.setCodiceArticolo(rs.getString("codiceArticolo"));
				bean.setNumeroOrdine(Integer.parseInt(rs.getString("numeroOrdine")));
				bean.setQuantit�(Integer.parseInt(rs.getString("quantita")));
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

	@Override
	public void doSave(ComponeBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String InsertSQL = "INSERT INTO compone VALUES (?,?,?)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(InsertSQL);
			
			ps.setInt(1, item.getNumeroOrdine());
			ps.setString(2, item.getCodiceArticolo());
			ps.setInt(3, item.getQuantit�());
			
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

	@Override
	public void doUpdate(ComponeBean item) throws SQLException {
		
	}

	@Override
	public void doDelete(ComponeBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
