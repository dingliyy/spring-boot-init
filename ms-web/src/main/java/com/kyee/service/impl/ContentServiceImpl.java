package com.kyee.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kyee.common.domain.PageModel;
import com.kyee.common.domain.SearchModel;
import com.kyee.core.db.Criteria;
import com.kyee.core.db.Restrictions;
import com.kyee.domain.Content;
import com.kyee.repository.ContentRepository;
import com.kyee.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentRepository contentRepository;

	@Override
	public List<Content> findByContentId(String contentId) {
		return contentRepository.findByContentId(contentId);
	}

	//hibernate综合条件查询
	@Override
	public PageModel<Content> search(SearchModel searchModel) {
		Sort sort = new Sort(Direction.ASC,"createTime");
		Pageable pageable = new PageRequest(searchModel.getPageNumber(),searchModel.getPageSize(),sort);
		Page<Content> page = null;
		Criteria<Content> criteria = new Criteria<>();
		if(StringUtils.isNoneEmpty(searchModel.getKeyword())){
			criteria.add(Restrictions.or(Restrictions.eq("contentId", searchModel.getKeyword(), true),Restrictions.like("title", searchModel.getKeyword(), true)));
		}
		page = contentRepository.findAll(criteria, pageable);
		
		PageModel<Content> pageModel = new PageModel<Content>(page.getTotalElements(), page.getContent());
		return pageModel;
	}
}
