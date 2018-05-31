package com.tv189.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.tv189.domain.po.ContentPO;

public interface ContentRepository extends CrudRepository<ContentPO, Long>, JpaSpecificationExecutor<ContentPO> {
	public List<ContentPO> findByContentId(String contentId);
}
