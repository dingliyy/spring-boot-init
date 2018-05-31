package com.tv189.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.tv189.common.util.CacheInvocation;
import com.tv189.common.util.CacheKeyGenarator;
//import com.tv189.core.db.hibernate.Criteria;
//import com.tv189.core.db.hibernate.Restrictions;
import com.tv189.domain.dto.PageDTO;
import com.tv189.domain.dto.SearchDTO;
import com.tv189.domain.po.ContentPO;
import com.tv189.manager.RedisTemplateManager;
import com.tv189.repository.ContentRepository;
import com.tv189.service.ContentService;
/**
 * 内容业务实现类
 * @author dingli
 * @date 2018年2月1日 上午10:41:23
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private ContentRepository contentRepository;
	@Autowired
	private RedisTemplateManager redisTemplateManager;

	/**
	 * 第一种 Redis 缓存方式，注解方式自动管理
	 * 注解@Cacheable中value的值是一个redis的key1，该key1的值就是该方法缓存结果的key2，key2的值是该方法最后返回的结果；
	 * 该方法的结果会返回的结果为空则不会缓存，如果不为空才会缓存；
	 * key2的生成方式是 RedisConfig 中 keyGenerator 方法；
	 * key2的值是json序列化对象，值的自动转换是在 RedisConfig 中 redisTemplate 配置的。
	 */
	@Cacheable(value="contentCache")
	@Override
	public List<ContentPO> findByContentId(String contentId) {
		List<ContentPO> list = contentRepository.findByContentId(contentId);
		return list == null || list.size() == 0 ? null : list;
	}

	/**
	 * 第二种 Redis 缓存方式，自定义key方式缓存 string 字符串
	 * 
	 * hibernate综合条件查询
	 */
	@Override
	public PageDTO<ContentPO> search(SearchDTO searchDTO) {
//		String key = CacheKeyGenarator.makeKey("content_search", searchDTO);
//		Object obj  = redisTemplateManager.get(key, new CacheInvocation() {
//			
//			@Override
//			public PageDTO<ContentPO> invoke() {
//				Sort sort = new Sort(Direction.ASC,"createTime");
//				Pageable pageable = new PageRequest(searchDTO.getPageNumber(),searchDTO.getPageSize(),sort);
//				Page<ContentPO> page = null;
//				Criteria<ContentPO> criteria = new Criteria<>();
//				if(StringUtils.isNoneEmpty(searchDTO.getKeyword())){
//					criteria.add(Restrictions.or(Restrictions.eq("contentId", searchDTO.getKeyword(), true),Restrictions.like("title", searchDTO.getKeyword(), true)));
//				}
//				page = contentRepository.findAll(criteria, pageable);
//				
//				PageDTO<ContentPO> pageDTO = new PageDTO<ContentPO>(page.getTotalElements(), page.getContent());
//				return pageDTO;
//			}
//		}, new TypeToken<PageDTO<ContentPO>>(){}.getType());
//		
//		return obj == null ? null : (PageDTO<ContentPO> ) obj ;
		return null;
	}
}
