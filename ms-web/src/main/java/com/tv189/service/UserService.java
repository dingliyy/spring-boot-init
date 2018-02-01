package com.tv189.service;

import java.util.List;

import com.tv189.domain.dto.PageDTO;
import com.tv189.domain.dto.SearchDTO;
import com.tv189.domain.po.UserPO;
/**
 * 用户业务抽象类
 * @author dingli
 * @date 2018年1月30日 上午10:18:07
 */
public interface UserService {
	
	/**
	 * 保存
	 * @userPO 用户持久化对象
	 */
	void save(UserPO userPO);
	
	/**
	 * 根据用户登录名查询用户信息接口
	 * @userName 用户登录名
	 * @return 用户信息集合
	 */
	List<UserPO> findByUserName(String userName);
	
	/**
	 * 分页条件查询
	 * @SearchDTO 分页对象
	 * @return 用户持久化对象集合
	 */
	PageDTO<UserPO> query(SearchDTO searchDTO);
}
