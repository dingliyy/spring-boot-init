package com.kyee.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.kyee.domain.Content;

public interface ContentRepository extends ElasticsearchRepository<Content,Long>{
	public List<Content> findByTitle(String title);
}
