package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import utils.Utility;

public class ContrassegnoModelDS implements ModelInterface<ContrassegnoBean> {
	
	private DataSource ds = null;
	
	public ContrassegnoModelDS(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public ContrassegnoBean doRetriveByKey(String code) throws SQLException {
		return null;
	}

	@Override
	public ContrassegnoBean doRetriveByKey(int code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM contrassegno WHERE numeroOrdine=?";
		
		ContrassegnoBean bean = new ContrassegnoBean();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setInt(1, code);
			
			Utility.print("doRetriveByKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
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

	@Override
	public Collection<ContrassegnoBean> doRetriveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM contrassegno";
		
		Collection<ContrassegnoBean> collection = new LinkedList<ContrassegnoBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			Utility.print("doRetriveAll " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ContrassegnoBean bean = new ContrassegnoBean();
				bean.setNumeroOrdine(rs.getInt("numeroOrdine"));
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
	public void doSave(ContrassegnoBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String InsertSQL = "INSERT INTO contrassegno VALUES (?)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(InsertSQL);
			
			ps.setInt(1, item.getNumeroOrdine());
			
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
	public void doUpdate(ContrassegnoBean item, String code) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(ContrassegnoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
