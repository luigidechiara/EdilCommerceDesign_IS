package model;

public class RuoloUserBean {
	private String username;
	private String nome;
	
	public RuoloUserBean() {
		username = "";
		nome = "";
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

	public boolean isEmpty() {
		return username == "" && nome == "";
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((this.getUsername() == ((UserBean) obj).getUsername()) && (this.getNome() == ((UserBean) obj).getNome())) ;
	}
	
	@Override
	public String toString() {
		return username + ", " + nome;
	}
}
