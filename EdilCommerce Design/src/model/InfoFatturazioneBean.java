package model;

public class InfoFatturazioneBean {
	int numeroOrdine;
	String nome;
	String cognome;
	String email;
	String telefono;
	String indirizzo;
	String città;
	String stato;
	String cap;
	
	public InfoFatturazioneBean() {
		numeroOrdine = 0;
		nome = "";
		cognome = "";
		email = "";
		telefono = "";
		indirizzo = "";
		città = "";
		stato = "";
		cap = "";
	}

	public int getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(int numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCittà() {
		return città;
	}

	public void setCittà(String città) {
		this.città = città;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}
	
	public boolean isEmpty() {
		return numeroOrdine == 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getNumeroOrdine()==((InfoFatturazioneBean) obj).getNumeroOrdine();
	}

	@Override
	public String toString() {
		return numeroOrdine + ", " + nome + ", " + cognome + ", " +  email + ", " + indirizzo  + ", " +  telefono + ", " +  città  + ", " +  stato  + ", " +  cap  ;

	}

}
