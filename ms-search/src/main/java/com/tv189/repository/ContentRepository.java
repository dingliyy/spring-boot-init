package com.tv189.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tv189.domain.Content;

public interface ContentRepository extends ElasticsearchRepository<Content,Long>{
	public List<Content> findByTitle(String title);
}
