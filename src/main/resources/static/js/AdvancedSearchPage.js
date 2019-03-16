
function disableOrEnableDetailCheckboxes(selectionQuery, isToDisable) {
	var elements = document.querySelectorAll(selectionQuery);
	  for (let i = 0; i < elements.length; i++) {
	      elements[i].disabled = isToDisable;
	      console.log(elements[i] + ":::::" + elements[i].disabled);
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

function validateForm() {
	if(document.getElementById("searchTerms").value == "") {
	  alert("Search terms field cannot be empty");
	  return false;
	}
	var atLeastOneContentTypeIsSelected;
	
	atLeastOneContentTypeIsSelected = (document.getElementById("searchInBooks").checked == true  || 
			document.getElementById("searchInMovies").checked == true  ||
			document.getElementById("searchInMusic").checked == true );
	if(!atLeastOneContentTypeIsSelected) {
		alert("Select at least one content type to search");
		return false;		  
	}
	
	var atLeastOneFiledIsSelectedForBooks = document.getElementById("bookTitle").checked == true ||
	document.getElementById("bookAuthor").checked == true  ||
	document.getElementById("bookPublisher").checked == true  ||
	document.getElementById("bookDescription").checked == true  ||
	document.getElementById("bookISBN").checked == true ;
	var atLeastOneFiledIsSelectedForMusic = document.getElementById("musicAlbumName").checked == true  ||
	document.getElementById("musicArtist").checked == true  ||
	document.getElementById("musicRecordLabel").checked == true ;
	var atLeastOneFiledIsSelectedForMovies = document.getElementById("movieTitle").checked == true  ||
	document.getElementById("movieDirector").checked == true  ||
	document.getElementById("movieDescription").checked == true ;
	
	var oneOfFieldsNotSelectedWhereasShouldBe = ((document.getElementById("searchInBooks").checked == true)  && !atLeastOneFiledIsSelectedForBooks) ||
												((document.getElementById("searchInMusic").checked == true)  && !atLeastOneFiledIsSelectedForMusic) ||
												((document.getElementById("searchInMovies").checked == true)  && !atLeastOneFiledIsSelectedForMovies);
	if(oneOfFieldsNotSelectedWhereasShouldBe) {
		alert("Select at least one field for each content type selected above");
		return false;		  
	}
	
	return true;
}

function enableDetailsCheckboxesIfCategorySelected() {
	var categories = ["searchInBooks", "searchInMovies", "searchInMusic"]
	for (let i = 0; i < categories.length; ++i) {
		if(document.getElementById(categories[i]).checked) {
			disableOrEnableDetailCheckboxes("#" + categories[i] + "Details > div > input", false);
		}
	}	
}

disableOrEnableDetailCheckboxes("#allDetailCheckboxes > td > div > div > input", true);
disableOrEnableDetailCheckboxes("#serchCategories > div > input", false);
makeDetailsCheckboxesDesableEnableDependingOnSelectedCategory();
enableDetailsCheckboxesIfCategorySelected();


document.getElementById("pageBody").style.display="block";

