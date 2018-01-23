package com.kyee.service;

import java.util.List;

import com.kyee.common.domain.PageModel;
import com.kyee.common.domain.SearchModel;
import com.kyee.domain.Content;


public interface ContentService {
	public List<Content> findByContentId(String contentId);
	
	public PageModel<Content> search(SearchModel searchModel);
}
