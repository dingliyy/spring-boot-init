package com.tv189.service;

import java.util.List;

import com.tv189.common.SearchParam;
import com.tv189.domain.Content;

public interface ContentSearchService {
	public List<Content> search(SearchParam searchParam);
}
