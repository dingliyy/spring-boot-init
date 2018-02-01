package com.tv189.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.tv189.common.util.CacheKeyGenarator;
import com.tv189.domain.dto.PageDTO;
import com.tv189.domain.dto.SearchDTO;
import com.tv189.domain.po.UserPO;
import com.tv189.manager.RedisTemplateManager;
import com.tv189.repository.UserRepository;
import com.tv189.service.UserService;

/**
 * 用户业务实现类
 * @author dingli
 * @date 2018年1月30日 上午10:34:41
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;//简单的jpa查询用这个
	@Autowired
	private MongoTemplate mongoTemplate;//复杂的条件查询用这个
	@Autowired
	private RedisTemplateManager<String, UserPO> redisTemplateManager;

	@Override
	public List<UserPO> findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public void save(UserPO userPO) {
		userRepository.save(userPO);
		
		String key = CacheKeyGenarator.makeKey("user_list");
		redisTemplateManager.addList(key, userPO);
	}
	
	@Override
	public PageDTO<UserPO> query(SearchDTO searchDTO){ 
		Pageable pageable = new PageRequest(searchDTO.getPageNumber(),searchDTO.getPageSize());
		Query query = new Query();
        if (StringUtils.isNotEmpty(searchDTO.getKeyword())) {
            //模糊查询
        	Criteria criteria = new Criteria();
            query = new Query(criteria.orOperator(Criteria.where("userName").regex(searchDTO.getKeyword())
            		,Criteria.where("nickName").regex(searchDTO.getKeyword())));
            
        }
        long total = mongoTemplate.count(query, UserPO.class);
        List<UserPO> usersList= mongoTemplate.find(query.with(pageable), UserPO.class);
        PageDTO<UserPO> pageDTO = new PageDTO<UserPO>(total, usersList);
        return pageDTO;
	}

}
