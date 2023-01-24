package model;

public class ComponeBean {
	int numeroOrdine;
	String codiceArticolo;
	int quantit�;
	
	public ComponeBean() {
		numeroOrdine = 0;
		codiceArticolo = "";
		quantit� = 0;
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
	public int getQuantit�() {
		return quantit�;
	}
	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((this.getNumeroOrdine() == ((ComponeBean) obj).getNumeroOrdine()) && (this.getCodiceArticolo() == ((ComponeBean) obj).getCodiceArticolo())) ;
	}
	
	@Override
	public String toString() {
		return numeroOrdine + ", " + codiceArticolo + ", " + quantit�;
	}
}
