
function disableOrEnableDetailCheckboxes(selectionQuery, isToDisable) {
	var elements = document.querySelectorAll(selectionQuery);
	  for (let i = 0; i < elements.length; i++) {
	      elements[i].disabled = isToDisable;
	  }
}

function makeDetailsCheckboxesDesableEnableDependingOnSelectedCategory() {
	var categories = ["searchInBooks", "searchInMovies", "searchInMusic"]

	for (let i = 0; i < categories.length; ++i) {
		document.getElementById(categories[i]).onchange = function() {
		    disableOrEnableDetailCheckboxes("#" + categories[i] + "Details" + " > div > input", 
		    		!document.getElementById(categories[i]).checked);
		}
	}
}

makeDetailsCheckboxesDesableEnableDependingOnSelectedCategory();
disableOrEnableDetailCheckboxes("#serchCategories > div > input", false);
disableOrEnableDetailCheckboxes("#allDetailCheckboxes > td > div > div > input", true);

document.getElementById("pageBody").style.display="block";

