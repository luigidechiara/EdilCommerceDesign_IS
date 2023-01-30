package model;

public class ContrassegnoBean {
	private int numeroOrdine;
	
	public ContrassegnoBean() {
		numeroOrdine = 0;
	}

	public int getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public boolean isEmpty() {
		return numeroOrdine == 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getNumeroOrdine()==((ContrassegnoBean) obj).getNumeroOrdine();
	}
	
	@Override
	public String toString() {
		return numeroOrdine + "";
	}
}
