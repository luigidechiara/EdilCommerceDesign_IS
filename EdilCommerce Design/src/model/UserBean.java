package model;

public class UserBean {
	String username;
	String nome;
	String cognome;
	String email;
	String telefono;
	String indirizzo;
	String userPassword;
	String città;
	String stato;
	String cap;
	
	public UserBean() {
		username = "";
		nome = "";
		cognome = "";
		email = "";
		telefono = "";
		indirizzo = "";
		userPassword = "";
		città = "";
		stato = "";
		cap = "";
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
		return username + ", " + nome + ", " + cognome + ", " +  email + ", " + indirizzo  + ", " +  telefono + ", " +  città  + ", " +  stato  + ", " +  cap  + ", " +  userPassword;
	}
}
