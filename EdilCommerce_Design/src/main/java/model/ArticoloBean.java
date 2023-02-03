package model;

import java.sql.Blob;

public class ArticoloBean {
	private String codiceArticolo;		
	private String nome;				
	private String immagine;			
	private String descrizione;		
	private double costo;				
	private String nomeCategoria;
	private int mediaRecensioni;
	private int giacenza;
	
	public ArticoloBean(){
		codiceArticolo= "";
		nome= "";
		immagine = "";
		descrizione= "";
		costo= 0;
		nomeCategoria= "";
		mediaRecensioni = 0;
		giacenza= 0;
	}
	
	
	

	public ArticoloBean(String codiceArticolo, String nome, String immagine, String descrizione, double costo,
			String nomeCategoria, int mediaRecensioni, int giacenza) {
		super();
		this.codiceArticolo = codiceArticolo;
		this.nome = nome;
		this.immagine = immagine;
		this.descrizione = descrizione;
		this.costo = costo;
		this.nomeCategoria = nomeCategoria;
		this.mediaRecensioni = mediaRecensioni;
		this.giacenza = giacenza;
	}




	public String getCodiceArticolo() {
		return codiceArticolo;
	}

	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public int getMediaRecensioni() {
		return mediaRecensioni;
	}

	public void setMediaRecensioni(int mediaRecensioni) {
		this.mediaRecensioni = mediaRecensioni;
	}
	
	public boolean isEmpty() {
		return codiceArticolo == "";
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.getCodiceArticolo().equals(((ArticoloBean) obj).getCodiceArticolo()) ;
	}
	           
	public int getGiacenza() {
		return giacenza;
	}

	public void setGiacenza(int giacenza) {
		this.giacenza = giacenza;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome + "," + "(" + codiceArticolo + ")" + ", " + immagine + ", " + descrizione + ", " + costo + ", " + nomeCategoria + "," + mediaRecensioni + "," +giacenza;
	}
	
	
	
}