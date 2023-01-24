package model;

import java.sql.Blob;

public class CategoriaBean {
	String immagine;
	String nome;
	String descrizione;


	public CategoriaBean() {
		immagine = null;
		nome = "";
		descrizione ="";
	}


	public String getImmagine() {
		return immagine;
	}


	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return this.getNome().equals(((CategoriaBean) obj).getNome());
	}
	
	public boolean isEmpty(Object obj) {
		return this.getNome() == "";
	}
	
	public String toString(Object obj) {
		return nome + " (" + descrizione + ") " + immagine;
	}
	
}	




