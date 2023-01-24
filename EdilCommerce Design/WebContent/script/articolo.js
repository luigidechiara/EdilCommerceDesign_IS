/**
 * 
 */

function Seleziona(index){
	var stelle = document.getElementsByClassName("fa-star");
	for(var i=0; i<5; i++) {
		if(i<index)
			stelle[i].classList.add("checked");
		else
			stelle[i].classList.remove("checked");
	}	
}

function SetDefaultStelle() {
	var stelle = document.getElementsByClassName("fa-star");
	var index = document.getElementById("stelle").value;
	for(var i=0; i<5; i++) {
		if(i<index)
			stelle[i].classList.add("checked");
		else
			stelle[i].classList.remove("checked");
	}
}

function SetSelezionati(index){
	var stelle = document.getElementsByClassName("fa-star");
	document.getElementById("stelle").value = index;
	for(var i=0; i<5; i++) {
		if(i<index)
			stelle[i].classList.add("checked");
		else
			stelle[i].classList.remove("checked");
	}
}

function InviaRecensione(codiceArticolo){
	var xhttp = getXmlHttpRequest();
		xhttp.onreadystatechange = function() {
		if(xhttp.readyState == 4 && xhttp.status == 200) {
			alert("Recensione registrata");
			
			var recensione = document.getElementById('scriviRecensione');
			var button = document.createElement("input");
			button.type = "button";
			button.id = "x";
			button.value = "X";
			button.title = "Cancella recensione";
			button.addEventListener("click", function(){
				CancellaRecensione(codiceArticolo + "")
			},false);
			
			recensione.childNodes[3].after(button);
			recensione.removeChild(document.getElementById("submit"));
			var sub = document.createElement("input");
			sub.id = "submit";
			sub.type = "button";
			sub.value = "Aggiorna recensione";
			sub.addEventListener("click", function() {
				AggiornaRecensione(codiceArticolo + "");
			})
			
			recensione.appendChild(sub);
		}
	};
	xhttp.open("POST", "/EdilCommerce_Design/OperaRecensione", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	var testo = document.getElementById("testo").value;
	var valutazione = document.getElementById("stelle").value;
	
	if(testo != "" && valutazione != "0")
		xhttp.send("testo="+ testo + "&valutazione="+ valutazione + "&codiceArticolo=" + codiceArticolo + "&op=save");
	else{
		alert("Testo e valutazione richiesti, per favore assicurarsi di scegliere una valutazione e inserire un testo");
	}
}

function AggiornaRecensione(codiceArticolo){
	var xhttp = getXmlHttpRequest();
		xhttp.onreadystatechange = function() {
		if(xhttp.readyState == 4 && xhttp.status == 200) {
			alert("Recensione modificata");
			
		}
	};
	xhttp.open("POST", "/EdilCommerce_Design/OperaRecensione", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	var testo = document.getElementById("testo").value;
	var valutazione = document.getElementById("stelle").value;
	
	if(testo != "" && valutazione != "0")
		xhttp.send("testo="+ testo + "&valutazione="+ valutazione + "&codiceArticolo=" + codiceArticolo + "&op=modify");
	else{
		alert("Testo e valutazione richiesti, per favore assicurarsi di scegliere una valutazione e inserire un testo");
	}
}

function CancellaRecensione(codiceArticolo){
	var xhttp = getXmlHttpRequest();
		xhttp.onreadystatechange = function() {
		if(xhttp.readyState == 4 && xhttp.status == 200) {
			alert("Recensione cancellata");
			
			var recensione = document.getElementById('scriviRecensione');
			
			document.getElementById("testo").value = '';
			document.getElementById("stelle").value = 0;
			
			recensione.removeChild(document.getElementById("submit"));
			var sub = document.createElement("input");
			sub.id = "submit";
			sub.type = "button";
			sub.value = "Invia recensione";
			sub.addEventListener("click", function() {
				InviaRecensione(codiceArticolo + "");
			})
			
			recensione.appendChild(sub);
			
			recensione.removeChild(document.getElementById("x"));
			SetDefaultStelle();
		}
	};
	xhttp.open("POST", "/EdilCommerce_Design/OperaRecensione", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	var testo = document.getElementById("testo").value;
	var valutazione = document.getElementById("stelle").value;
	
	if(testo != "" && valutazione != "0")
		xhttp.send("testo=" + testo + "&valutazione=" + valutazione + "&codiceArticolo=" + codiceArticolo + "&op=delete");
	else{
		alert("Testo e valutazione richiesti, per favore assicurarsi di scegliere una valutazione e inserire un testo");
	}
}

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