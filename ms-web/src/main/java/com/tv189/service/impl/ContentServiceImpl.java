package com.tv189.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tv189.core.db.hibernate.Criteria;
import com.tv189.core.db.hibernate.Restrictions;
import com.tv189.domain.dto.PageDTO;
import com.tv189.domain.dto.SearchDTO;
import com.tv189.domain.po.ContentPO;
import com.tv189.repository.ContentRepository;
import com.tv189.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentRepository contentRepository;

	@Override
	public List<ContentPO> findByContentId(String contentId) {
		return contentRepository.findByContentId(contentId);
	}

	//hibernate综合条件查询
	@Override
	public PageDTO<ContentPO> search(SearchDTO searchModel) {
		Sort sort = new Sort(Direction.ASC,"createTime");
		Pageable pageable = new PageRequest(searchModel.getPageNumber(),searchModel.getPageSize(),sort);
		Page<ContentPO> page = null;
		Criteria<ContentPO> criteria = new Criteria<>();
		if(StringUtils.isNoneEmpty(searchModel.getKeyword())){
			criteria.add(Restrictions.or(Restrictions.eq("contentId", searchModel.getKeyword(), true),Restrictions.like("title", searchModel.getKeyword(), true)));
		}
		page = contentRepository.findAll(criteria, pageable);
		
		PageDTO<ContentPO> pageModel = new PageDTO<ContentPO>(page.getTotalElements(), page.getContent());
		return pageModel;
	}
}
