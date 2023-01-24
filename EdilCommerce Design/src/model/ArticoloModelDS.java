package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import utils.Utility;

public class ArticoloModelDS implements ModelInterface<ArticoloBean> {
	
	private DataSource ds = null;
	
	public ArticoloModelDS(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public ArticoloBean doRetriveByKey(String code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM articolo WHERE codiceArticolo=?";
		
		ArticoloBean bean = new ArticoloBean();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setString(1, code);
			
			Utility.print("doRetriveByKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bean.setCodiceArticolo(rs.getString("codiceArticolo"));
				bean.setNome(rs.getString("nome"));
				bean.setImmagine(rs.getString("immagine"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCosto(rs.getDouble("costo"));
				bean.setNomeCategoria(rs.getString("nomeCategoria"));
				bean.setMediaRecensioni(rs.getInt("mediaRecensioni"));
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
	public ArticoloBean doRetriveByKey(int code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ArticoloBean> doRetriveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<ArticoloBean> collection = new LinkedList<ArticoloBean>();
		
		String selectCodeSQL = "SELECT * FROM articolo";
		
		if(order!= null) {
			if(!order.isBlank())
				selectCodeSQL = selectCodeSQL + " ORDER BY "+ order ;
			else
				 selectCodeSQL = "SELECT * FROM articolo ORDER BY nome";
		} else 
			 selectCodeSQL = "SELECT * FROM articolo ORDER BY nome";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			Utility.print("doRetriveAll: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ArticoloBean bean = new ArticoloBean();
				
				bean.setCodiceArticolo(rs.getString("codiceArticolo"));
				bean.setNome(rs.getString("nome"));
				bean.setImmagine(rs.getString("immagine"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCosto(rs.getDouble("costo"));
				bean.setNomeCategoria(rs.getString("nomeCategoria"));
				bean.setMediaRecensioni(rs.getInt("mediaRecensioni"));
				
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
	public void doSave(ArticoloBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String InsertSQL = "INSERT INTO articolo VALUES (?,?,?,?,?,?,?)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(InsertSQL);
			
			ps.setString(1, item.getCodiceArticolo());
			ps.setString(2, item.getNome());
			ps.setString(3, item.getImmagine());
			ps.setString(4, item.getDescrizione());
			ps.setDouble(5, item.getCosto());
			ps.setString(6, item.getNomeCategoria());
			ps.setDouble(7, item.getMediaRecensioni());
			
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
	public void doUpdate(ArticoloBean item, String code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String UpdateSQL = "UPDATE articolo SET nome=?, immagine=?, descrizione=?, costo=?, nomeCategoria=? WHERE codiceArticolo=?";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(UpdateSQL);
			
			ps.setString(1, item.getNome());
			ps.setString(2, item.getImmagine());
			ps.setString(3, item.getDescrizione());
			ps.setDouble(4, item.getCosto());
			ps.setString(5, item.getNomeCategoria());
			ps.setString(6, code);
			
			Utility.print("doUpdate: " + ps.toString());
			
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
	public void doDelete(ArticoloBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public Collection<ArticoloBean> doRetriveByCategory(String code, String prezzo, String ordine) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<ArticoloBean> collection = new LinkedList<ArticoloBean>();
		
		String selectCodeSQL = "SELECT * FROM articolo WHERE ";
		
		if(!(prezzo.isBlank())) 
			selectCodeSQL = selectCodeSQL + prezzo + " AND "; 
		
		selectCodeSQL = selectCodeSQL + " nomeCategoria=? ";
		
		if(!(ordine.isBlank())) 
			selectCodeSQL = selectCodeSQL + " ORDER BY " + ordine;
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setString(1, code);
			
			Utility.print("doRetriveByCategory: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ArticoloBean bean = new ArticoloBean();
				
				bean.setCodiceArticolo(rs.getString("codiceArticolo"));
				bean.setNome(rs.getString("nome"));
				bean.setImmagine(rs.getString("immagine"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCosto(rs.getDouble("costo"));
				bean.setNomeCategoria(rs.getString("nomeCategoria"));
				bean.setMediaRecensioni(rs.getInt("mediaRecensioni"));
				
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
	
	public Collection<ArticoloBean> doSearchByNome(String code, String prezzo, String ordine) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<ArticoloBean> collection = new LinkedList<ArticoloBean>();
		
		String selectCodeSQL = "SELECT * FROM articolo WHERE ";
		
		if(!(prezzo.isBlank())) 
			selectCodeSQL = selectCodeSQL + " costo " + prezzo + " AND "; 
		
		selectCodeSQL = selectCodeSQL + " nome like ? ";
		
		if(!(ordine.isBlank())) 
			selectCodeSQL = selectCodeSQL + " ORDER BY " + ordine;
		
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setString(1, code+"%");
			
			Utility.print("doSearchByNome: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ArticoloBean bean = new ArticoloBean();
				
				bean.setCodiceArticolo(rs.getString("codiceArticolo"));
				bean.setNome(rs.getString("nome"));
				bean.setImmagine(rs.getString("immagine"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCosto(rs.getDouble("costo"));
				bean.setNomeCategoria(rs.getString("nomeCategoria"));
				bean.setMediaRecensioni(rs.getInt("mediaRecensioni"));
				
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

	public Collection<ArticoloBean> doRetriveByImmagine(String code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<ArticoloBean> collection = new LinkedList<ArticoloBean>();
		
		String selectCodeSQL = "SELECT * FROM articolo WHERE immagine=?";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setString(1, code);
			
			Utility.print("doRetriveByImmagine: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ArticoloBean bean = new ArticoloBean();
				
				bean.setCodiceArticolo(rs.getString("codiceArticolo"));
				bean.setNome(rs.getString("nome"));
				bean.setImmagine(rs.getString("immagine"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setCosto(rs.getDouble("costo"));
				bean.setNomeCategoria(rs.getString("nomeCategoria"));
				bean.setMediaRecensioni(rs.getInt("mediaRecensioni"));
				
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
