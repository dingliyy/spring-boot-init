package com.tv189.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.sym.Name;
import com.tv189.domain.po.ContentPO;
import com.tv189.repository.ContentRepository;
import com.tv189.service.ContentService;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
/**
 * 内容业务实现类
 * @author dingli
 * @date 2018年2月1日 上午10:41:23
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentRepository contentRepository;
	
	@Value("${config.name}")
	private String name;

	@Override
	public List<ContentPO> findByContentId(String contentId) {
		List<ContentPO> list = contentRepository.findByContentId(contentId);
		return list == null || list.size() == 0 ? null : list;
	}

	@Override
	public String getName() {
		return name;
	}

}
