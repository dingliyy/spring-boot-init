package com.tv189.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv189.domain.dto.SearchDTO;
import com.tv189.domain.po.Content;
import com.tv189.domain.po.ContentExample;
import com.tv189.mapper.ContentMapper;
import com.tv189.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentMapper contentMapper;

	@Override
	public void save() {
		Content content = Content.builder().id(2L).contentid("C3333312").categoryid(3).createtime(new Date()).title("天赋异禀").subcategoryname("美剧").build();
		contentMapper.insertSelective(content);
	}

	@Override
	public Content findById(Long id) {
		return contentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Content> query(SearchDTO searchDTO) {
		ContentExample contentExample = new ContentExample();
		contentExample.or().andContentidEqualTo(searchDTO.getKeyword());
		contentExample.or().andTitleLike(searchDTO.getKeyword());
		
		contentExample.setLimitStart(searchDTO.getPageNumber() * searchDTO.getPageSize());
		contentExample.setCount(searchDTO.getPageSize());
		contentExample.setOrderByClause("updatetime desc");
		contentMapper.selectByExample(contentExample);
		
		List<Content> contents = contentMapper.selectByExample(contentExample);
		return contents;
	}

}
