package com.kyee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyee.common.SearchParam;
import com.kyee.domain.Content;
import com.kyee.service.ContentSearchService;
import com.kyee.service.ContentService;

@RestController
public class ContentController {
	@Autowired
	private ContentService contentService;

	@Autowired
	private ContentSearchService ContentSearchService;

	@RequestMapping(value = "/content/{title}/", method = RequestMethod.GET)
	public List<Content> getPaths(@PathVariable("title") String title) {
		return contentService.getContentByTitle(title);
	}

	@GetMapping("/contents/")
	public List<Content> searchPaths(@RequestParam("matchTermName") String matchTermName,
			@RequestParam("matchTermValue") String matchTermValue) {
		return contentService.query(matchTermName, matchTermValue);
	}

	/**
	 * 
	 * @apiParam {String} wd 关键字
	 * @apiParam {boolean} highlight 是否需要高亮
	 * @apiParam {String} highlightFields 高亮字段
	 * @apiParam {String[]} sort 排名排序
	 * @apiParam {String[]} andParam 过滤条件(","是关键字段分割，"^"是多值分割)
	 * @apiParam {String[]} orParam 过滤条件(","是关键字段分割，"^"是多值分割)
	 * @apiParam {String[]} responseField 定制化返回字段
	 * @apiParam {Integer} diseaseSize 查询医生数量
	 * @apiParam {Integer} randomSize 随机返回的数据，默认是0，表示不随机。
	 * @apiParamExample {json} Request-Example: {
	 * 
	 *                  "wd":"李",
	 * 
	 *                  "highlight":true
	 * 
	 *                  "highlightFields":name,analyzeName,title,seniorDeparts.juniorDeparts.departs.name,summary
	 * 
	 *                  "sort":hospitalBase.generalScore
	 * 
	 *                  "andParam":key1:value1^value2,key2:value1^value2
	 * 
	 *                  "orParam":key1:value1^value2,key2:value1^value2
	 * 
	 *                  "responseField":name,analyzeName,title
	 * 
	 *                  "diseaseSize":3
	 * 
	 *                  }
	 * 
	 */

	@RequestMapping(value = "/paths/params/", method = RequestMethod.GET)
	public List<Content> queryDisease(SearchParam searchParam) {
		return ContentSearchService.search(searchParam);
	}

	// 喂数据

	@PostMapping("/paths/")
	public void addPath(@RequestBody Content content) {
		contentService.addContent(content);
	}
}
