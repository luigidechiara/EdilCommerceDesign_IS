package utils;

import java.util.Date;

public class ValidazioneInput {

	public boolean ValidazioneRegistrazione(String username,String nome,String cognome,String email,String telefono,String indirizzo,String userPassword,String citt�,String stato,String cap) {
				
	if(nome.matches("^[A-Za-z]+$")) {
	  if(cognome.matches("^[A-Za-z]+$")) {
		if(email.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) {
			if(userPassword.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")) {
				if(telefono.matches("^[0-9]{10}$")) {
					if(cap.matches("^([0-9]{5})$")) {
						
						return true;
					
					}}}}}}
	return false;
	}
	
	
	public boolean ValidazioneInformazioniPersonali(String username,String nome,String cognome,String email,String telefono,String indirizzo,String userPassword,String citt�,String stato,String cap) {
		
		if(nome.matches("^[A-Za-z]+$")) {
			  if(cognome.matches("^[A-Za-z]+$")) {
				if(email.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) {
					if(userPassword.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")) {
						if(telefono.matches("^[0-9]{10}$")) {
							if(cap.matches("^([0-9]{5})$")) {
								
								return true;
							
							}}}}}}
			return false;
			}
	
	public boolean InformazioniSpedizione(String nome,String cognome,String email,String telefono,String indirizzo,String citt�,String stato,String cap) {
		
		if(nome.matches("^[A-Za-z]+$")&& nome.length()>=3) {
			  if(cognome.matches("^[A-Za-z]+$")&& cognome.length()>=3) {
				if(email.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) {
						if(telefono.matches("^[0-9]{10}$")) {
							if(cap.matches("^([0-9]{5})$")) {
								
								return true;
							
							}}}}}
			return false;
			}
	
	public boolean ValidazioneCarta(String numero,String intestatario,String annoScadenza,String meseScadenza,String cvv) {
		Date dt=new Date();
        int year=dt.getYear();

        System.out.print(year);

        if(numero.matches("^([0-9]{16})$")) {
			if(intestatario.length()>=1&&intestatario.length()<=25) {
				if((Integer.parseInt(annoScadenza))>=year) {
					if(meseScadenza.length()>=3) {
						if(cvv.matches("^([0-9]{3})$")) {
						return true;
					}
				
						}
							 }}}	
		return false;
	}
	
	
}
