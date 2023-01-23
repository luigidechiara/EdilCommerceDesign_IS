/**
 * 
 */

function setModificable() {
	var form = document.forms["profilo"];
	var l = form.elements.length;
	for(var i = 0; i < l; i++) {
		form.elements[i].removeAttribute("readonly");
	}
}

function controllaPass(pass1, pass2, sub) {
    
	var e1 = document.getElementById(pass1);
	var e2 = document.getElementById(pass2);
	if (e1.value != e2.value) {
		e1.style.borderColor = "red";
		e2.style.borderColor = "red";
		document.getElementById(sub).setAttribute("disabled", "disabled");
	} else {
		e1.style.borderColor = "#ccc";
		e2.style.borderColor = "#ccc";
		document.getElementById(sub).removeAttribute("disabled");
		
	}
}

function visualizza(id) {
	var all = document.getElementsByClassName("container");
	var l = all.length;
	var x = document.getElementById(id);
	var list = x.classList;
	for(var i = 0; i < l; i++) {
		if(all[i] != x && !all[i].classList.contains("start"))
			all[i].classList.add("close");
			all[i].classList.remove("open");
	}
		
  	if(list.contains("start")){
		list.replace("start", "open");
	} else if (list.contains("close")) {
		list.replace("close", "open");
	} else {
		list.remove("open");
		list.add("close");
  }
}