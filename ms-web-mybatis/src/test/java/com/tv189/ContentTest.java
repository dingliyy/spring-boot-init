package com.tv189;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSON;
import com.tv189.domain.dto.SearchDTO;
import com.tv189.domain.po.Content;
import com.tv189.service.impl.ContentServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@Import(ContentServiceImpl.class)
public class ContentTest {
	@Autowired
	private ContentServiceImpl contentService;
	
//	@Test
	public void save() {
		contentService.save();
	}
	
	@Test
	public void query(){
		SearchDTO searchDTO = SearchDTO.builder().keyword("C123456").pageNumber(0).pageSize(1).build();
		List<Content> list = contentService.query(searchDTO);
		System.out.println(JSON.toJSONString(list));
	}
}
