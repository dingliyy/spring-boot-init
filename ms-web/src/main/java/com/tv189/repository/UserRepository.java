package com.tv189.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tv189.domain.po.UserPO;
/**
 * 用户数据访问层
 * @author dingli
 * @date 2018年1月30日 上午10:23:41
 */
public interface UserRepository extends MongoRepository<UserPO, String>{
	/**
	 * 根据用户登录名查询用户持久化对象集合
	 * @userName 用户登录名
	 * @result 用户持久化对象集合
	 */
	List<UserPO> findByUserName(String userName);
	
}
