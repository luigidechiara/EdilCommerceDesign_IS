package model;

import java.sql.Date;

public class OrdineBean {
	
	int numeroOrdine;
	Date data;
	String username;
	
	public OrdineBean() {
		numeroOrdine = 0;
		data = null;
		username = "";
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

	@Override
	public String toString() {
		return numeroOrdine + ", " + data + ", " + username ;
	}

	@Override
	public boolean equals(Object obj) {
		return this.getNumeroOrdine()==((OrdineBean) obj).getNumeroOrdine();
	}
	
}
