package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import utils.Utility;

public class InfoFatturazioneModelDS implements ModelInterface<InfoFatturazioneBean> {

	private DataSource ds = null;
	
	public InfoFatturazioneModelDS(DataSource ds) {
		this.ds = ds;
	}

	 
	public InfoFatturazioneBean doRetriveByKey(String code) throws SQLException {
		return null;
	}

	 
	public InfoFatturazioneBean doRetriveByKey(int code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM infoFatturazione WHERE numeroOrdine=?";
		
		InfoFatturazioneBean bean = new InfoFatturazioneBean();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setInt(1, code);
			
			Utility.print("doRetriveByKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bean.setNumeroOrdine(rs.getInt("numeroOrdine"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setCitta(rs.getString("citta"));
				bean.setStato(rs.getString("stato"));
				bean.setCap(rs.getString("cap"));
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

	 
	public Collection<InfoFatturazioneBean> doRetriveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM infoFatturazione";
		
		Collection<InfoFatturazioneBean> collection = new LinkedList<InfoFatturazioneBean>();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			Utility.print("doRetriveAll: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				InfoFatturazioneBean bean = new InfoFatturazioneBean();
				bean.setNumeroOrdine(rs.getInt("numeroOrdine"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setCitta(rs.getString("citta"));
				bean.setStato(rs.getString("stato"));
				bean.setCap(rs.getString("cap"));
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

	 
	public boolean doSave(InfoFatturazioneBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String InsertSQL = "INSERT INTO infoFatturazione VALUES (?,?,?,?,?,?,?,?,?)";
		
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(InsertSQL);
				
				ps.setInt(1, item.getNumeroOrdine());
				ps.setString(2, item.getNome());
				ps.setString(3, item.getCognome());
				ps.setString(4, item.getEmail());
				ps.setString(5, item.getTelefono());
				ps.setString(6, item.getIndirizzo());
				ps.setString(7, item.getCitta());
				ps.setString(8, item.getStato());
				ps.setString(9, item.getCap());

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

	 
	public boolean doUpdate(InfoFatturazioneBean item, String code) throws SQLException {
		// TODO Auto-generated method stub
		return false;
		
	}

	 
	public boolean doDelete(InfoFatturazioneBean item) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
