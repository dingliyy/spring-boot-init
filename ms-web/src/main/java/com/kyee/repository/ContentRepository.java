package com.kyee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.kyee.domain.Content;

public interface ContentRepository extends CrudRepository<Content, Long>, JpaSpecificationExecutor<Content> {
	public List<Content> findByContentId(String contentId);
}
