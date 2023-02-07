package model.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import utils.Utility;
import model.*;
public class RecensisceModelDS implements ModelRelationInterface<RecensisceBean> {
	private DataSource ds = null;
	
	public RecensisceModelDS(DataSource ds) {
		this.ds = ds;
	}
	
	
	public RecensisceBean doRetriveByKey(String code1, String code2) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM recensisce WHERE username=? AND codiceArticolo=?";
		
		RecensisceBean bean = new RecensisceBean();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setString(1, code1);
			ps.setString(2, code2);
			
			Utility.print("doRetriveByKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bean.setCodiceArticolo(rs.getString("codiceArticolo"));
				bean.setUsername(rs.getString("username"));
				bean.setDate(rs.getDate("data"));
				bean.setTesto(rs.getString("testo"));
				bean.setValore(rs.getInt("valore"));
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

	
	public Collection<RecensisceBean> doRetriveByOneKey(String code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM recensisce WHERE username=?";
		
		
		Collection<RecensisceBean> collection = new LinkedList<RecensisceBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setString(1, code);
			
			Utility.print("doRetriveByOneKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				RecensisceBean bean = new RecensisceBean();
				bean.setCodiceArticolo(rs.getString("codiceArticolo"));
				bean.setUsername(rs.getString("username"));
				bean.setDate(rs.getDate("data"));
				bean.setTesto(rs.getString("testo"));
				bean.setValore(rs.getInt("valore"));
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
	
	public Collection<RecensisceBean> doRetriveByCodiceArticolo(String code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM recensisce WHERE codiceArticolo=?";
		
		
		Collection<RecensisceBean> collection = new LinkedList<RecensisceBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setString(1, code);
			
			Utility.print("doRetriveByCodiceArticolo: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				RecensisceBean bean = new RecensisceBean();
				bean.setCodiceArticolo(rs.getString("codiceArticolo"));
				bean.setUsername(rs.getString("username"));
				bean.setDate(rs.getDate("data"));
				bean.setTesto(rs.getString("testo"));
				bean.setValore(rs.getInt("valore"));
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

	
	public Collection<RecensisceBean> doRetriveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean doSave(RecensisceBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String InsertSQL = "INSERT INTO recensisce VALUES (?,?,NOW() ,?,?)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(InsertSQL);
			
			ps.setString(1, item.getCodiceArticolo());
			ps.setString(2, item.getUsername());
			ps.setInt(3, item.getValore());
			ps.setString(4, item.getTesto());
			
			Utility.print("doSave: " + ps.toString());
			
			ps.executeUpdate();
			
			ps.close();

			String UpdateMediaSQL = "UPDATE (select codiceArticolo, count(codiceArticolo) count from articolo NATURAL JOIN recensisce WHERE codiceArticolo = ? group by codiceArticolo) a, articolo SET mediaRecensioni = ((mediaRecensioni * (count - 1)) + ?)/count WHERE articolo.codiceArticolo = ?";
			
			con = ds.getConnection();
			ps = con.prepareStatement(UpdateMediaSQL);

			ps.setString(1, item.getCodiceArticolo());
			ps.setInt(2, item.getValore());
			ps.setString(3, item.getCodiceArticolo());
			
			Utility.print("doUpdateMediaRecensioni: " + ps.toString());
			
			if(ps.executeUpdate()==0)
				return false;
			
			return true;
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

	
	public boolean doUpdate(RecensisceBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String UpdateSQL = "UPDATE recensisce SET data=NOW(), valore=?, testo=? WHERE username=? AND codiceArticolo=?";
		
			try {
				
				String UpdateMediaSQL = "UPDATE (select codiceArticolo, count(codiceArticolo) count from articolo NATURAL JOIN recensisce WHERE codiceArticolo = ? group by codiceArticolo) c, articolo a, recensisce r SET mediaRecensioni = ((mediaRecensioni * (count)) - r.valore + ?)/count WHERE a.codiceArticolo = ? AND r.codiceArticolo = a.codiceArticolo AND r.username = ?";
				
				con = ds.getConnection();
				ps = con.prepareStatement(UpdateMediaSQL);

				ps.setString(1, item.getCodiceArticolo());
				ps.setInt(2, item.getValore());
				ps.setString(3, item.getCodiceArticolo());
				ps.setString(4, item.getUsername());
				
				Utility.print("doUpdateMediaRecensioni: " + ps.toString());
				
				ps.executeUpdate();
				
				ps.close();
				
				con = ds.getConnection();
				ps = con.prepareStatement(UpdateSQL);

				ps.setInt(1, item.getValore());
				ps.setString(2, item.getTesto());
				ps.setString(3, item.getUsername());
				ps.setString(4, item.getCodiceArticolo());
				Utility.print("doUpdate: " + ps.toString());

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

	
	public boolean doDelete(RecensisceBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String DeleteSQL = "DELETE FROM recensisce WHERE username=? AND codiceArticolo=?";
		
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(DeleteSQL);

				ps.setString(1, item.getUsername());
				ps.setString(2, item.getCodiceArticolo());
				Utility.print("doDelete: " + ps.toString());

				ps.executeUpdate();
				
				ps.close();

				String UpdateMediaSQL = "UPDATE (select codiceArticolo, count(codiceArticolo) count from articolo NATURAL JOIN recensisce WHERE codiceArticolo = ? group by codiceArticolo) a, articolo SET mediaRecensioni = ((mediaRecensioni * (count + 1)) - ?)/count WHERE articolo.codiceArticolo = ?";
				
				con = ds.getConnection();
				ps = con.prepareStatement(UpdateMediaSQL);

				ps.setString(1, item.getCodiceArticolo());
				ps.setInt(2, item.getValore());
				ps.setString(3, item.getCodiceArticolo());
				
				Utility.print("doUpdateMediaRecensioni: " + ps.toString());
				
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

}
