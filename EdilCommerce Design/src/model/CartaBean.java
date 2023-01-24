package model;

import java.sql.Date;

public class CartaBean {
	int numeroPagamento;
	String numero;
	String intestatario;
	String dataScadenza;
	String cvv;
	
    public CartaBean() {
    	numeroPagamento = 0;
    	numero = "";
    	intestatario = "";
    	dataScadenza = null;
    	cvv = "";
    }
	public int getNumeroPagamento() {
		return numeroPagamento;
	}
	public void setNumeroPagamento(int numeroPagamento) {
		this.numeroPagamento = numeroPagamento;
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
		return numeroPagamento == 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getNumeroPagamento()== ((CartaBean) obj).getNumeroPagamento();
	}
	
	@Override
	public String toString() {
		return numeroPagamento + ", " + numero + ", " + intestatario + ", " +  dataScadenza + ", " + cvv;
	}

}
