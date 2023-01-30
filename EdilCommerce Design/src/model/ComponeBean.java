package model;

public class ComponeBean {
	private int numeroOrdine;
	private String codiceArticolo;
	private int quantita;
	
	public ComponeBean() {
		numeroOrdine = 0;
		codiceArticolo = "";
		quantita = 0;
	}
	
	public int getNumeroOrdine() {
		return numeroOrdine;
	}
	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}
	public String getCodiceArticolo() {
		return codiceArticolo;
	}
	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((this.getNumeroOrdine() == ((ComponeBean) obj).getNumeroOrdine()) && (this.getCodiceArticolo() == ((ComponeBean) obj).getCodiceArticolo())) ;
	}
	
	@Override
	public String toString() {
		return numeroOrdine + ", " + codiceArticolo + ", " + quantita;
	}
}
