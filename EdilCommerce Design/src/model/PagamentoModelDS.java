package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import utils.Utility;

public class PagamentoModelDS implements ModelInterface<PagamentoBean> {
	private DataSource ds = null;
	
	public PagamentoModelDS(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public PagamentoBean doRetriveByKey(String code) throws SQLException {
		return null;
	}

	@Override
	public PagamentoBean doRetriveByKey(int code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM pagamento WHERE numeroPagamento=?";
		
		PagamentoBean bean = new PagamentoBean();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setInt(1, code);
			
			Utility.print("doRetriveByKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bean.setNumeroPagamento(rs.getInt("numeroPagamento"));
				bean.setNumeroOrdine(rs.getInt("numeroOrdine"));
				bean.setImporto(rs.getDouble("importo"));
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
	public Collection<PagamentoBean> doRetriveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM pagamento";
		
		Collection<PagamentoBean> collection = new LinkedList<PagamentoBean>();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			Utility.print("doRetriveAll: " + ps.toString());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PagamentoBean bean = new PagamentoBean();
				bean.setNumeroPagamento(rs.getInt("numeroPagamento"));
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

	@Override
	public void doSave(PagamentoBean item) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String InsertSQL = "INSERT INTO pagamento (numeroOrdine, importo) VALUES (?,?)";
		
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(InsertSQL);
				
				ps.setInt(1, item.getNumeroOrdine());
				ps.setDouble(2, item.getImporto());

				Utility.print("doSave: " + ps.toString());

				ps.executeUpdate();

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

	@Override
	public void doUpdate(PagamentoBean item, String code) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(PagamentoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public PagamentoBean doRetriveByNumeroOrdine(int code) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String selectCodeSQL = "SELECT * FROM pagamento WHERE numeroOrdine=?";
		
		PagamentoBean bean = new PagamentoBean();
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(selectCodeSQL);
			
			ps.setInt(1, code);
			
			Utility.print("doRetriveByKey: " + ps.toString());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bean.setNumeroPagamento(rs.getInt("numeroPagamento"));
				bean.setNumeroOrdine(rs.getInt("numeroOrdine"));
				bean.setImporto(rs.getDouble("importo"));
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
}
