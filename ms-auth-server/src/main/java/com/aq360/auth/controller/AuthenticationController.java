package com.aq360.auth.controller;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * token校验
 *
 * @author dingli02
 * @date 2018/05/18 10:40
 */
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
	
	@RequestMapping(value = "/getByContentId",method = RequestMethod.GET)
    public String getByContentId(@RequestParam(value = "contentId", required = true ) String contentId
    		,@RequestHeader(value = "loginId", required = false) String loginId
    		,@RequestHeader(value = "Authorization", required = false) String Authorization){
		System.out.println(loginId);
		System.out.println(Authorization);
		return "123";
    }
	
}
