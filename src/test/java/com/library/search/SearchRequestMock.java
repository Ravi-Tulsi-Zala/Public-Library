package com.library.search;

public class SearchRequestMock implements ISearchRequest {
	private ISearchResults searchResults = SearchFactory.instance().makeSearchResults();
	
	

	@Override
	public void addCategoryToSearchIn(SearchCategory categoryToSearch) {
		// TODO Auto-generated method stub

	}

	@Override
	public SearchTermsAndPage getTermsAndPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTermsAndPage(SearchTermsAndPage termsAndPage) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isNewSearchTerms(ISearchRequest other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ISearchResults searchInDb() {
		// TODO Auto-generated method stub
		return null;
	}

}
