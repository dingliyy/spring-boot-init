package com.tv189.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tv189.domain.dto.PageDTO;
import com.tv189.domain.dto.SearchDTO;
import com.tv189.domain.dto.UserDTO;
import com.tv189.domain.po.UserPO;
import com.tv189.service.UserService;

/**
 * 用户业务接口
 * @author dingli
 * @date 2018年1月30日 上午11:12:49
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getByUserName",method = RequestMethod.GET)
    public List<UserPO> getContent(@RequestParam(value = "userName", required = true ) String userName){
		return userService.findByUserName(userName);
    }
	
	@RequestMapping(value = "/query",method = RequestMethod.POST)
    public PageDTO<UserPO> search(SearchDTO searchDTO){
		return userService.query(searchDTO);
    }
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
    public void save(UserDTO userDTO){
		UserPO userPO = new UserPO();
		BeanUtils.copyProperties(userDTO, userPO);
		userService.save(userPO);
    }
}
