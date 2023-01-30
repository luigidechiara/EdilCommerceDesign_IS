package model;

import java.sql.Date;

public class OrdineBean {
	
	private int numeroOrdine;
	private Date data;
	private String username;
	private double importo;
	
	public OrdineBean() {
		numeroOrdine = 0;
		data = null;
		username = "";
		importo= 0;
	}

	public int getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isEmpty() {
		return numeroOrdine == 0;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	

	@Override
	public String toString() {
		return "OrdineBean [numeroOrdine=" + numeroOrdine + ", data=" + data + ", username=" + username + ", importo="
				+ importo + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return this.getNumeroOrdine()==((OrdineBean) obj).getNumeroOrdine();
	}
	
}
