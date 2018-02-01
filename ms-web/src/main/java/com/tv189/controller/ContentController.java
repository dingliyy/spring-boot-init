package com.tv189.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tv189.domain.dto.PageDTO;
import com.tv189.domain.dto.SearchDTO;
import com.tv189.domain.po.ContentPO;
import com.tv189.service.ContentService;

/**
 * 这里@RefreshScope注解不能少，否则即使调用/refresh，配置也不会刷新
 */
@RestController
@RequestMapping("/content")
//@RefreshScope
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value = "/getByContentId",method = RequestMethod.GET)
    public List<ContentPO> getByContentId(@RequestParam(value = "contentId", required = true ) String contentId){
		return contentService.findByContentId(contentId);
    }
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
    public PageDTO<ContentPO> search( SearchDTO searchModel){
		return contentService.search(searchModel);
    }
}
