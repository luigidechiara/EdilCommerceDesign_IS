/**
 * 
 */

function disabilita() {
	var node = document.getElementById("cnome");
	node.setAttribute("disabled", "disabled");
	node.value = "";
	node = document.getElementById("cnum");
	node.setAttribute("disabled", "disabled");
	node.value = "";
	node = document.getElementById("expmonth");
	node.setAttribute("disabled", "disabled");
	
	node = document.getElementById("expyear");
	node.setAttribute("disabled", "disabled");
	node.value = "";
	node = document.getElementById("cvv");
	node.setAttribute("disabled", "disabled");
	node.value = "";
}

function abilita() {
	document.getElementById("cnome").removeAttribute("disabled");
	document.getElementById("cnum").removeAttribute("disabled");
	document.getElementById("expmonth").removeAttribute("disabled");
	document.getElementById("expyear").removeAttribute("disabled");
	document.getElementById("cvv").removeAttribute("disabled");
}