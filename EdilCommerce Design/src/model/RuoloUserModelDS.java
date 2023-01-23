package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import utils.Utility;

public class RuoloUserModelDS implements ModelRelationInterface<RuoloUserBean> {
	
	private DataSource ds = null;
	
	public RuoloUserModelDS(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public RuoloUserBean doRetriveByKey(String code1, String code2) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<RuoloUserBean> doRetriveByOneKey(String code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM ruoloUser WHERE username=?";
		
		Collection<RuoloUserBean> collection = new LinkedList<RuoloUserBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setString(1, code);
			
			Utility.print("doRetriveByOneKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				RuoloUserBean bean = new RuoloUserBean();
				bean.setUsername(rs.getString("username"));
				bean.setNome(rs.getString("nome"));
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
	public Collection<RuoloUserBean> doRetriveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(RuoloUserBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String InsertSQL = "INSERT INTO ruoloUser VALUES (?,?)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(InsertSQL);
			
			ps.setString(1, item.getUsername());
			ps.setString(2, item.getNome());
			
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
	public void doUpdate(RuoloUserBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(RuoloUserBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
