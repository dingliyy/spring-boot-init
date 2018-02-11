package com.tv189.service;

import java.util.List;

import com.tv189.domain.dto.SearchDTO;
import com.tv189.domain.po.Content;

public interface ContentService {
	
	void save();
	
	Content findById(Long id);
	
	List<Content> query(SearchDTO searchDTO);
	
}
