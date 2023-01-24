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

function selezionaArticolo(op) {
	var select = document.getElementById("articolo");
	var selected = select.options[select.selectedIndex].value;
	var xhttp = getXmlHttpRequest();
		xhttp.onreadystatechange = function() {
		if(xhttp.readyState == 4 && xhttp.status == 200) {
			document.getElementById("formModificaA").innerHTML = xhttp.responseText;
		}
	};
	xhttp.open("GET", "/EdilCommerce_Design/ModificaArticolo?codice=" + selected + "&op=" + op, true);
	xhttp.send();
}