package com.kyee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kyee.common.domain.PageModel;
import com.kyee.common.domain.SearchModel;
import com.kyee.domain.Content;
import com.kyee.service.ContentService;

/**
 * 这里@RefreshScope注解不能少，否则即使调用/refresh，配置也不会刷新
 */
@RestController
@RequestMapping("/content")
//@RefreshScope
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value = "/{contentId}",method = RequestMethod.GET)
    public List<Content> getContent(@PathVariable("contentId") String contentId){
		return contentService.findByContentId(contentId);
    }
	
	@RequestMapping(value = "/search",method = RequestMethod.POST)
    public PageModel<Content> search( SearchModel searchModel){
		return contentService.search(searchModel);
    }
}
