package model;

import java.sql.Date;

public class RecensisceBean {
	
	String codiceArticolo;
	String username;
	Date date;
    int valore;
    String testo;
    
    public RecensisceBean() {
    	codiceArticolo = "";
    	username = "";
    	date = null;
    	valore = 0;
    	testo = "";
    }

	public String getCodiceArticolo() {
		return codiceArticolo;
	}

	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getValore() {
		return valore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}
	
	public boolean isEmpty() {
		return username == "" && codiceArticolo == "";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getUsername().equals(((RecensisceBean) obj).getUsername()) && this.getCodiceArticolo().equals(((RecensisceBean) obj).getCodiceArticolo());
	}
	
	@Override
	public String toString() {
		return codiceArticolo + ", " + username  + ", " +  valore + ", " + testo ;
	}
}
