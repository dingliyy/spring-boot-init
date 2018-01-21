package com.kyee.service;

import java.util.List;

import com.kyee.common.SearchParam;
import com.kyee.domain.Content;

public interface ContentSearchService {
	public List<Content> search(SearchParam searchParam);
}
