package com.library.search;

import java.util.ArrayList;
import java.util.List;

import com.library.businessModels.LibraryItem;

public class SearchRequest implements ISearchRequest {
	private SearchTermsAndPage termsAndPage = null;
	private List<SearchCategory> categoriesToSearch = new ArrayList<>();

	public SearchResults searchInDb() {
		SearchResults searchResults = new SearchResults();
		for(SearchCategory category : categoriesToSearch) {
			List<LibraryItem> items = category.search(termsAndPage.getSearchTerms());
			searchResults.addSearchResultsForCategory(items);
		}
		
		return searchResults;
	}
	
	public boolean onlyRequestedPageDiffers(SearchRequest newRequest) {
		if(!termsAndPage.getSearchTerms().equals(newRequest.termsAndPage.getSearchTerms())) {
			return false;
		}
		int numOfCategories = categoriesToSearch.size();
		for(int i = 0; i < numOfCategories; ++i) {
			if(!categoriesToSearch.get(i).equals(newRequest.categoriesToSearch.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void addCategoryToSearchIn(SearchCategory categoryToSearch) {
		categoriesToSearch.add(categoryToSearch);
	}

	public void addSearchTermsAndResultsPage(SearchTermsAndPage termsAndPage) {
		this.termsAndPage = termsAndPage;	
	}

	public int getRequestedResultsPageNumber() {
		return termsAndPage.getRequestedResultsPageNumber();
	}

	public void setRequestedResultsPageNumber(int requestedResultsPageNumber) {
		termsAndPage.setRequestedResultsPageNumber(requestedResultsPageNumber);		
	}
}
