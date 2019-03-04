<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Spring MVC Form Handling</title>
      <style>
      td {
      	padding-bottom: 20px;
      	padding-right:50px
      }
      </style>
   </head>
   <body>
      <h2>Advanced Search</h2>
      <form:form modelAttribute="searchQuery" method = "POST" action = "/advancedSearch" onsubmit="indicateExtendedsearch()">
      
      	<div style="margin-bottom: 20px">
            <form:label path = "searchTerms">Search Terms</form:label>
            <form:input path = "searchTerms" style="width:330px;"/>
      	</div>
            
        <table>            
        	<tr>
            	<td>
               	<div style="border:1px solid black">
					<div>
						<form:checkbox id="extendedSearch" path="extendedSearch" style="display:none"/>
					</div>	               	
					<div>
						<form:checkbox id="searchInBooks" path="searchInBooks" />
						<form:label path="searchInBooks">Books</form:label>
					</div>	
					<div>
						<form:checkbox id = "searchInMovies" path="searchInMovies"/>
						<form:label path="searchInMovies">Movies</form:label>
					</div>	
					<div>
						<form:checkbox id = "searchInMusic" path="searchInMusic"/>
						<form:label path="searchInMusic">Music</form:label>
					</div>	
               	</div>
			   </td>
            </tr>
            
            <tr id="allDetailCheckboxes" valign="top">
               <td>
               	<div id="searchInBooksDetails" style="border:1px solid black">
					<div>
						<form:checkbox id="bookTitle" path="searchBookTitle"/>
						<form:label path="searchBookTitle">Title</form:label>
					</div>	
					<div>
						<form:checkbox id="bookAuthor" path="searchBookAuthor"/>
						<form:label path="searchBookAuthor">Author</form:label>
					</div>	
					<div>
						<form:checkbox id="bookPublisher" path="searchBookPublisher"/>
						<form:label path="searchBookPublisher">Publisher</form:label>
					</div>
					<div>
						<form:checkbox id="bookDescription" path="searchBookDescription"/>
						<form:label path="searchBookDescription">Description</form:label>
					</div>	
					<div>
						<form:checkbox id="bookISBN" path="searchBookISBN"/>
						<form:label path="searchBookISBN">ISBN</form:label>
					</div>		
               	</div>
			   </td>
               <td>
               	<div id="searchInMusicDetails" style="border:1px solid black">
					<div>
						<form:checkbox id="musicAlbumName" path="searchMusicAlbumName"/>
						<form:label path="searchMusicAlbumName">Album Name</form:label>
					</div>	
					<div>
						<form:checkbox id="musicArtist" path="searchMusicArtist"/>
						<form:label path="searchMusicArtist">Artist</form:label>
					</div>	
					<div>
						<form:checkbox id="musicRecordLabel" path="searchMusicRecordLabel"/>
						<form:label path="searchMusicRecordLabel">Record Label</form:label>
					</div>	
               	</div>
			   </td>
               <td>
               	<div id="searchInMoviesDetails" style="border:1px solid black">
					<div>
						<form:checkbox id="movieTitle" path="searchMovieTitle"/>
						<form:label path="searchMovieTitle">Title</form:label>
					</div>	
					<div>
						<form:checkbox id="movieDirector" path="searchMovieDirector"/>
						<form:label path="searchMovieDirector">Director</form:label>
					</div>	
					<div>
						<form:checkbox id="movieDescription" path="searchMovieDescription"/>
						<form:label path="searchMovieDescription">Description</form:label>
					</div>	
               	</div>
			   </td>
            </tr>
            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Search"/>
               </td>
            </tr>
         </table>  
      </form:form>
   </body>
   
   <script src="js/AdvancedSearchPage.js"></script>
   
</html>