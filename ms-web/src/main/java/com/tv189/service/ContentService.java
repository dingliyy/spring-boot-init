package com.tv189.service;

import java.util.List;

import com.tv189.domain.dto.PageDTO;
import com.tv189.domain.dto.SearchDTO;
import com.tv189.domain.po.ContentPO;

/**
 * 内容业务接口类
 * @author dingli
 * @date 2018年2月1日 上午10:41:38
 */
public interface ContentService {
	/**
	 * 根据内容编号查询内容
	 */
	List<ContentPO> findByContentId(String contentId);
	
	/**
	 * 综合条件查询内容
	 */
	PageDTO<ContentPO> search(SearchDTO searchModel);
}
