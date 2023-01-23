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

function deleteItem(codice, id) {
	var xhttp = getXmlHttpRequest();
		xhttp.onreadystatechange = function() {
		if(xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById(id).innerHTML = xhttp.responseText;
		}
	};
	xhttp.open("GET", "/EdilCommerce_Design/Rimuovi?codice=" + codice, true);
	xhttp.send();
}

function aggiornaQuantita(index) {
	var xhttp = getXmlHttpRequest();
	var q = document.getElementsByClassName("quantita")[index].value;
	xhttp.open("GET", "/EdilCommerce_Design/AggiornaQ?codice=" + index +"&quantita=" + q, true);
	xhttp.send();
}