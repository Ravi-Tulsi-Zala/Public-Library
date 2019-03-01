package com.library.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.library.business.itemSearch.ItemDescriptor;
import com.library.business.itemSearch.SearchQuery;
import com.library.business.itemSearch.SearchResult;
import com.library.demo.LibraryApplication;
import com.library.model.IDataBase;


@RunWith(SpringRunner.class)
@WebMvcTest
// try to remove the annotation below, run this TEST and observe error. Look for solution here:
// https://stackoverflow.com/questions/43515279/error-unable-to-find-springbootconfiguration-when-doing-webmvctest-for-spring
@ContextConfiguration(classes={LibraryApplication.class})
public class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IDataBase dataBaseMock;
    
    @Before
    public void setUp() {
        Mockito.reset(dataBaseMock);
     }

    @SuppressWarnings("unchecked")
	@Test
    public void searchingInBooks() throws Exception {
    	
    	SearchResult searchResult = new SearchResult();
    	SearchQuery searchQuery = new SearchQuery();
    	searchQuery.setSearchTerms("Jack London");
    	searchQuery.setIsExtendedSearch(false);
    	
    	
    	LinkedList<ItemDescriptor> booksFoundInSearch = new LinkedList<ItemDescriptor>();
    	booksFoundInSearch.add(new ItemDescriptor("The Star Rover", "/static/images/TheStarRover_Cover.jpeg", "JL_TSR_6161"));
    	booksFoundInSearch.add(new ItemDescriptor("The Scarlet Plague", "/static/images/TheScarletPlague_Cover.jpeg", "JL_TSP_218358"));
    	searchResult.setBookSearchResults(booksFoundInSearch);
    	
    	LinkedList<ItemDescriptor> moviesFoundInSearch = new LinkedList<ItemDescriptor>();
    	moviesFoundInSearch.add(new ItemDescriptor("White Fang", "/static/images/WhiteFang_Cover.jpeg", "JL_WF_4568585"));
    	searchResult.setMovieSearchResults(moviesFoundInSearch);
    	
    	searchResult.setMusicSearchResults(null);
    	
        when(dataBaseMock.search(searchQuery)).thenReturn(searchResult);
        
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/search");
        request.flashAttr("searchQuery", searchQuery);
        
		this.mockMvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(view().name("SearchResults.jsp"))
		
        			.andExpect(model().attribute("searchResults", hasProperty("bookSearchResults", hasSize(2))))
        			.andExpect(model().attribute("searchResults",
						allOf(hasProperty("bookSearchResults", 
								hasItems(
										allOf(hasProperty("itemDescription", is("The Star Rover")), 
											  hasProperty("itemImageUrl", is("/static/images/TheStarRover_Cover.jpeg")),
											  hasProperty("itemID", is("JL_TSR_6161")))
										,

										allOf(hasProperty("itemDescription", is("The Scarlet Plague")), 
											  hasProperty("itemImageUrl", is("/static/images/TheScarletPlague_Cover.jpeg")),
											  hasProperty("itemID", is("JL_TSP_218358")))
										)))))
        			
        			.andExpect(model().attribute("searchResults", hasProperty("movieSearchResults", hasSize(1))))
        			.andExpect(model().attribute("searchResults",
						allOf(hasProperty("movieSearchResults", 
								hasItem(
										allOf(hasProperty("itemDescription", is("White Fang")), 
											  hasProperty("itemImageUrl", is("/static/images/WhiteFang_Cover.jpeg")),
											  hasProperty("itemID", is("JL_WF_4568585"))))))))
        			
        			.andExpect(model().attribute("searchResults", hasProperty("musicSearchResults", is(nullValue()))))
        			;			
    }
}

