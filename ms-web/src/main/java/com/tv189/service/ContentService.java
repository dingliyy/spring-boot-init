package com.tv189.service;

import java.util.List;

import com.tv189.domain.dto.PageDTO;
import com.tv189.domain.dto.SearchDTO;
import com.tv189.domain.po.ContentPO;


public interface ContentService {
	public List<ContentPO> findByContentId(String contentId);
	
	public PageDTO<ContentPO> search(SearchDTO searchModel);
}
