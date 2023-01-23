/**
 * 
 */
function checkNome(inputtxt){
	 var nome= /^[A-Za-z]+$/;
	 if(inputtxt.value.match(nome))	
		return true;
	 return false;
}

function checkEmail(inputtxt){
	 var email= /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	 if(inputtxt.value.match(email))	
		return true;
	 return false;
}
function checkPass(inputtxt){
	 var pass= /^(\w{4,10})+$/;
	 if(inputtxt.value.match(pass))	
		return true;
	 return false;
}

function checkPhone(inputtxt){
	 var phone= /^[0-9]{10}$/;
	 if(inputtxt.value.match(phone))	
		return true;
	 return false;
}
function checkPhone2(inputtxt){
	 var phone2= /^[0-9]{9}$/;
	 if(inputtxt.value.match(phone2))	
		return true;
	 return false;
}

function checkCap(inputtxt){
	 var cap= /^([0-9]{5})$/;
	 if(inputtxt.value.match(cap))	
		return true;
	 return false;
}

function checkAnno(inputtxt){
	 var anno= /^([0-9]{4})$/;
	 var annoC = new Date().getFullYear();
	 if(inputtxt.value.match(anno))	
		if(inputtxt.value>annoC)
			return true;
	 return false;
}

function checkCvv(inputtxt){
	 var cvv= /^([0-9]{3})$/;
	 if(inputtxt.value.match(cvv))	
		return true;
	 return false;
}

function checkCarta(inputtxt){
	 var carta= /^([0-9]{16})$/;
	 if(inputtxt.value.match(carta))	
		return true;
	 return false;
}

function validateRegistrazione(obj){
	var valid= true;
	
	var nome= document.getElementsByName("nome")[0];
	if(!checkNome(nome)){
		valid= false;
		nome.classList.add("error");
	}else{
		nome.classList.remove("error")
	}
	
	var cognome= document.getElementsByName("cognome")[0];
	if(!checkNome(cognome)){
		valid= false;
		cognome.classList.add("error");
	}else{
		cognome.classList.remove("error")
	}
	
	var username= document.getElementsByName("username")[0];
	if(!checkNome(username)){
		valid= false;
		username.classList.add("error");
	}else{
		username.classList.remove("error")
	}
	
	var email= document.getElementsByName("email")[0];
	if(!checkEmail(email)){
		valid= false;
		email.classList.add("error");
	}else{
		email.classList.remove("error")
	}
	
	var password= document.getElementsByName("password")[0];
	if(!checkPass(password)){
		valid= false;
		password.classList.add("error");
	}else{
		password.classList.remove("error")
	}
	
	var phone= document.getElementsByName("telefono")[0];
	if(!(checkPhone(phone) || checkPhone2(phone))){
		valid= false;
		phone.classList.add("error");
	}else{
		phone.classList.remove("error")
	}
	
	var citta= document.getElementsByName("citta")[0];
	if(!checkNome(citta)){
		valid= false;
		citta.classList.add("error");
	}else{
		citta.classList.remove("error")
	}
	
	var cap= document.getElementsByName("cap")[0];
	if(!checkCap(cap)){
		valid= false;
		cap.classList.add("error");
	}else{
		cap.classList.remove("error")
	}
	var stato= document.getElementsByName("stato")[0];
	if(!checkNome(stato)){
		valid= false;
		stato.classList.add("error");
	}else{
		stato.classList.remove("error")
	}
	
	if(valid) obj.submit();
}

function validateCheckout(obj){
	var valid= true;
	
	var nome= document.getElementsByName("nome")[0];
	if(!checkNome(nome)){
		valid= false;
		nome.classList.add("error");
	}else{
		nome.classList.remove("error")
	}
	
	var cognome= document.getElementsByName("cognome")[0];
	if(!checkNome(cognome)){
		valid= false;
		cognome.classList.add("error");
	}else{
		cognome.classList.remove("error")
	}
	
	
	var email= document.getElementsByName("email")[0];
	if(!checkEmail(email)){
		valid= false;
		email.classList.add("error");
	}else{
		email.classList.remove("error")
	}
	
	
	var phone= document.getElementsByName("telefono")[0];
	if(!(checkPhone(phone) || checkPhone2(phone))){
		valid= false;
		phone.classList.add("error");
	}else{
		phone.classList.remove("error")
	}
	
	var citta= document.getElementsByName("citta")[0];
	if(!checkNome(citta)){
		valid= false;
		citta.classList.add("error");
	}else{
		citta.classList.remove("error")
	}
	
	var cap= document.getElementsByName("cap")[0];
	if(!checkCap(cap)){
		valid= false;
		cap.classList.add("error");
	}else{
		cap.classList.remove("error")
	}
	var stato= document.getElementsByName("stato")[0];
	if(!checkNome(stato)){
		valid= false;
		stato.classList.add("error");
	}else{
		stato.classList.remove("error")
	}
	
	var intestatario= document.getElementsByName("cnome")[0];
	if(!(intestatario.getAttribute("disabled") == "disabled")){
		if(!checkNome(intestatario)){
			valid= false;
			cnome.classList.add("error");
		}else{
			cnome.classList.remove("error")
		}
	
		var num= document.getElementsByName("cnum")[0];
		if(!checkCarta(num)){
			valid= false;
			cnum.classList.add("error");
		}else{
			cnum.classList.remove("error")
		}
		
		var expyear= document.getElementsByName("expyear")[0];
		if(!checkAnno(expyear)){
			valid= false;
			expyear.classList.add("error");
		}else{
			expyear.classList.remove("error")
		}
		
		var cvv= document.getElementsByName("cvv")[0];
		if(!checkCvv(cvv)){
			valid= false;
			cvv.classList.add("error");
		}else{
			cvv.classList.remove("error")
		}
	}
	if(valid) obj.submit();
}