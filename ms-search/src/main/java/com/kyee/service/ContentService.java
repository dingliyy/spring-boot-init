package com.kyee.service;

import java.util.List;

import com.kyee.domain.Content;

public interface ContentService {
	
	public List<Content> query(String matchTerm,String value);
	
	public List<Content> getContentByTitle(String title);
	
	public void addContent(Content content);
}
