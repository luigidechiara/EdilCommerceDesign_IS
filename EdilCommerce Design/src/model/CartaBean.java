package model;

import java.sql.Date;

public class CartaBean {
	int numeroOrdine;
	String numero;
	String intestatario;
	String dataScadenza;
	String cvv;
	
    public CartaBean() {
    	numeroOrdine = 0;
    	numero = "";
    	intestatario = "";
    	dataScadenza = null;
    	cvv = "";
    }
	public int getNumeroOrdine() {
		return numeroOrdine;
	}
	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getIntestatario() {
		return intestatario;
	}
	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}
	public String getDataScadenza() {
		return dataScadenza;
	}
	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	public boolean isEmpty() {
		return numeroOrdine == 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getNumeroOrdine()== ((CartaBean) obj).getNumeroOrdine();
	}
	
	@Override
	public String toString() {
		return numeroOrdine + ", " + numero + ", " + intestatario + ", " +  dataScadenza + ", " + cvv;
	}

}
