package model;

public class UserBean {
	private String username;
	private String nome;
	private String cognome;
	private String email;
	private String telefono;
	private String indirizzo;
	private String userPassword;
	private String citta;
	private String stato;
	private String cap;
	
	public UserBean() {
		username = "";
		nome = "";
		cognome = "";
		email = "";
		telefono = "";
		indirizzo = "";
		userPassword = "";
		citta = "";
		stato = "";
		cap = "";
	}
	
	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
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

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public boolean isEmpty() {
		return username == "";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getUsername().equals(((UserBean) obj).getUsername());
	}
	
	@Override
	public String toString() {
		return username + ", " + nome + ", " + cognome + ", " +  email + ", " + indirizzo  + ", " +  telefono + ", " +  citta  + ", " +  stato  + ", " +  cap  + ", " +  userPassword;
	}
}
