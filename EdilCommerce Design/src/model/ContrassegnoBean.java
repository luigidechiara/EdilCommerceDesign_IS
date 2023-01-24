package model;

public class ContrassegnoBean {
	int numeroPagamento;
	
	public ContrassegnoBean() {
		numeroPagamento = 0;
	}

	public int getNumeroPagamento() {
		return numeroPagamento;
	}

	public void setNumeroPagamento(int numeroPagamento) {
		this.numeroPagamento = numeroPagamento;
	}

	public boolean isEmpty() {
		return numeroPagamento == 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getNumeroPagamento()==((ContrassegnoBean) obj).getNumeroPagamento();
	}
	
	@Override
	public String toString() {
		return numeroPagamento + "";
	}
}
