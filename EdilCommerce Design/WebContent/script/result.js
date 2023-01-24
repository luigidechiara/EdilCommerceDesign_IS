/**
 * 
 */

function getXmlHttpRequest() {
	
	var xhr = false;
	var activateXoptions = new Array("Microsoft.XmlHttp", "MSXML4.XmlHttp" , "MSXML3.XmlHttp", "MSXML2.XmlHttp", "MSXML.XmlHttp");

	try {
		xhr = new XMLHttpRequest();
	} catch(e) {}
	
	if(!xhr) {
		var created = false;
		for(var i=0; i < activateXoptions.length && !created; i++) {
			try {
				xhr = new ActiveXObject(activeXoptions[i]);
				created = true;
			} catch(e) {}
		}
	}
	return xhr;
}

function aggiorna(codice) {
	var xhttp = getXmlHttpRequest();
		xhttp.onreadystatechange = function() {
		if(xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById("result").innerHTML = xhttp.responseText;
		}
	};
	
	var prezzo="", ordine="", mediaRecensioni="", l;
	var list = document.getElementsByName("prezzo");
	l = list.length;
	for(var i=0; i<l; i++) {
		if(list[i].checked==true){
			prezzo = list[i].value;
			break;
		}
	}
	
	
	
	list= document.getElementsByName("ordine");
	l = list.length;
	for(var i=0; i<l; i++) {
		if(list[i].checked == true) {
			ordine = list[i].value;
			break;
		}
	}
	
	list= document.getElementsByName("mediaRecensioni");
	l = list.length;
	for(var i=0; i<l; i++) {
		if(list[i].checked == true) {
			mediaRecensioni = list[i].value;
			break;
		}
	}
	
	xhttp.open("GET", "/EdilCommerce_Design/AggiornaSearch?criterioRicerca=" + codice + "&prezzo=" + prezzo + "&ordine=" + ordine + "&mediaRecensioni=" + mediaRecensioni, true);
	xhttp.send();
}

function mutua(value) {
	var check = document.getElementsByName("prezzo");
	var l=check.length;
	for(var i=0; i<l; i++) {
		if(check[i].value != value){
			check[i].checked = false;
		}
	}
}

function mutuaR(value) {
	var check = document.getElementsByName("mediaRecensioni");
	var l=check.length;
	for(var i=0; i<l; i++) {
		if(check[i].value != value){
			check[i].checked = false;
		}
	}
}

var currpage = 1;

function cambiaPag(n) {
	currpage = n;
	var p = "pagina" + n;
	var righe = document.getElementsByClassName("pagina");
	var l= righe.length;
	for(var i=0; i<l; i++) {
		if(righe[i].classList[0] == p)
			righe[i].style.display = "block";
		else 
			righe[i].style.display = "none";
	}
	setCurrPage(currpage);
}

function succPag() {
	currpage = (currpage + 1) 
	var maxPage = Math.ceil(((document.getElementsByClassName("pagina").length)/7));
	if(maxPage==1){
		currpage = maxPage;
	} else if(currpage > maxPage)
		currpage = currpage % maxPage;
	var p = "pagina" + currpage;
	var righe = document.getElementsByClassName("pagina");
	var l= righe.length;
	for(var i=0; i<l; i++) {
		if(righe[i].classList[0] == p)
			righe[i].style.display = "block";
		else 
			righe[i].style.display = "none";
	}
	setCurrPage(currpage);
}

function prevPag() {
	currpage = (currpage - 1) 
	var maxPage = Math.ceil(((document.getElementsByClassName("pagina").length)/7));
	if(currpage <= 0)
		currpage = maxPage;
	var p = "pagina" + currpage;
	var righe = document.getElementsByClassName("pagina");
	var l= righe.length;
	for(var i=0; i<l; i++) {
		if(righe[i].classList[0] == p)
			righe[i].style.display = "block";
		else 
			righe[i].style.display = "none";
	}
	setCurrPage(currpage);
}

function setCurrPage(pag){
	var buttons = document.getElementsByClassName("pagButton");
	var l=buttons.length;
	for(var i=0; i<l; i++) {
		if(i==pag-1) {
			buttons[i].classList.add("current");
		} else {
		buttons[i].classList.remove("current");
		}
	}
}