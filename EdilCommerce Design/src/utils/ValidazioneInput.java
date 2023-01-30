package utils;

import java.util.Date;

import javax.servlet.http.HttpSession;

import model.ArticoloBean;

public class ValidazioneInput {

	
	

	public boolean ValidazioneRegistrazione(String username,String nome,String cognome,String email,String telefono,String indirizzo,String userPassword,String citta,String stato,String cap) {
    if(username!=null&&nome!=null&&cognome!=null&&email!=null&&telefono!=null&&indirizzo!=null&&userPassword!=null&&citta!=null&&stato!=null&&cap!=null) {

	if(nome.matches("^[A-Za-z]+$")) {
	  if(cognome.matches("^[A-Za-z]+$")) {
		if(email.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{10,30}")) {
			if(userPassword.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")) {
				if(telefono.matches("^[0-9]{8,10}$")) {
					if(cap.matches("^([0-9]{5})$")) {
						if(indirizzo.length()>8) {
							if(citta.length()>4) {
								if(cap.length()==5) {
									if(stato.length()>=2 && stato.length()<=20) {
								
						
						return true;
					
					}}}}}}}}}}}
	return false;
	}
	
	
	public boolean ValidazioneInformazioniPersonali(String username,String nome,String cognome,String email,String telefono,String indirizzo,String userPassword,String citta,String stato,String cap) {
		
		if(username!=null&&nome!=null&&cognome!=null&&email!=null&&telefono!=null&&indirizzo!=null&&userPassword!=null&&citta!=null&&stato!=null&&cap!=null) {
			if(nome.matches("^[A-Za-z]+$")) {
			  if(cognome.matches("^[A-Za-z]+$")) {
				if(email.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{10,30}")) {
					if(userPassword.matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})")) {
						if(telefono.matches("^[0-9]{8,10}$")) {
							if(cap.matches("^([0-9]{5})$")) {
								if(indirizzo.length()>8) {
									if(citta.length()>4) {
										if(cap.length()==5) {
											if(stato.length()>=2 && stato.length()<=20) {
										
								
								return true;
							
											}}}}}}}}}}} 
			return false;
			}
	

	public boolean InformazioniSpedizione(String nome,String cognome,String email,String telefono,String indirizzo,String citta,String stato,String cap) {

		if(nome.matches("^[A-Za-z]+$")&& nome.length()>=3) {
			  if(cognome.matches("^[A-Za-z]+$")&& cognome.length()>=3) {
				if(email.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")) {
						if(telefono.matches("^[0-9]{9,10}$")) {
							if(cap.matches("^([0-9]{5})$")) {
								
								return true;
							
							}}}}}
			return false;
			}
	
	public boolean ValidazioneCarta(String numero,String intestatario,String annoScadenza,String meseScadenza,String cvv) {
		Date dt=new Date();
        int year=(dt.getYear());
        
        System.out.print(year);
        
        if(numero!=null&&intestatario!=null&&annoScadenza!=null&&meseScadenza!=null&&cvv!=null) {
        if(numero.matches("^([0-9]{16})$")&& numero.length()==16) {
        	if(intestatario.length()>=1&&intestatario.length()<=40) {
				if((Integer.parseInt(annoScadenza))>=year) {
					if(meseScadenza.length()>=3) {
						if(cvv.matches("^([0-9]{3})$")&& cvv.length()==3) {
						return true;
					}
				
						}
							 }}}}	
		return false;
	}
	
	
	public boolean ValidazioneAggiungiArticolo(ArticoloBean saveBean) {
		
		if(saveBean.getNome().matches("^[A-Za-z]+$")) {
			if(saveBean.getImmagine().length()>1 && saveBean.getImmagine().length()<=15&& saveBean.getImmagine().contains(".jpeg")) {
				if(saveBean.getDescrizione().length()>5 && saveBean.getDescrizione().matches("^[A-Za-z]+$")) {
					
						return true;
					
				}
			}
			
		}
		
		return false;
		
		
	}
	
	
}
