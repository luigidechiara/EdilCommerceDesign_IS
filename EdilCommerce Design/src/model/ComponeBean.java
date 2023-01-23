package model;

public class ComponeBean {
	int numeroOrdine;
	String codiceArticolo;
	int quantità;
	
	public ComponeBean() {
		numeroOrdine = 0;
		codiceArticolo = "";
		quantità = 0;
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
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((this.getNumeroOrdine() == ((ComponeBean) obj).getNumeroOrdine()) && (this.getCodiceArticolo() == ((ComponeBean) obj).getCodiceArticolo())) ;
	}
	
	@Override
	public String toString() {
		return numeroOrdine + ", " + codiceArticolo + ", " + quantità;
	}
}
