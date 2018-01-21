package com.kyee.service.impl;

import java.util.List;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kyee.domain.Content;
import com.kyee.repository.ContentRepository;
import com.kyee.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{
	private static final int DEFAULT_FROM_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;
    
	@Autowired
	private ContentRepository contentRepository;
	
	@Override
	public List<Content> getContentByTitle(String title) {
		return contentRepository.findByTitle(title);
	}

	@Override
	public void addContent(Content content) {
		contentRepository.save(content);
	}

	@Override
	public List<Content> query(String matchTerm, String value) {
		MatchQueryBuilder queryBuilder = new MatchQueryBuilder(matchTerm, value);
        Pageable pageRequest = new PageRequest(DEFAULT_FROM_PAGE, DEFAULT_PAGE_SIZE);
        Page<Content> responses = contentRepository.search(queryBuilder, pageRequest);
        return responses.getContent();
	}

}
