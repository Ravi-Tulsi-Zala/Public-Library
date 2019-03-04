function indicateExtendedsearch() {
	console.log(document.getElementById("extendedSearch").checked);
	document.getElementById("extendedSearch").checked = true;
	console.log(document.getElementById("extendedSearch").checked);
}



function disableOrEnableDetailCheckboxes(selectionQuery, isToDisable) {
	var elements = document.querySelectorAll(selectionQuery);
	  for (let i = 0; i < elements.length; i++) {
	      elements[i].disabled = isToDisable;
	      elements[i].checked = false;
	  }
}

disableOrEnableDetailCheckboxes("#allDetailCheckboxes > td > div > div > input", true);

var categories = ["searchInBooks", "searchInMovies", "searchInMusic"]

for (let i = 0; i < categories.length; ++i) {

	document.getElementById(categories[i]).onchange = function() {
	    disableOrEnableDetailCheckboxes("#" + categories[i] + "Details" + " > div > input", 
	    		!document.getElementById(categories[i]).checked);
	}
}
