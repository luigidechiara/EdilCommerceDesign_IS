function autocomp(){ 
var ricerca= document.getElementById("search").value;
var xhttp = getXmlHttpRequest();
if(ricerca.length>=1){
	document.getElementById("suggerimenti").style.display="block";
xhttp.onreadystatechange = function() {
		if(xhttp.readyState == 4 && xhttp.status == 200) {
			
	    	document.getElementById("suggerimenti").innerHTML = xhttp.responseText;
			
		}
	}
	xhttp.open("GET", "/EdilCommerce_Design/Autocomplete?criterioRicerca=" + ricerca , true);
	xhttp.send();
} else {
	document.getElementById("suggerimenti").style.display="none";
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