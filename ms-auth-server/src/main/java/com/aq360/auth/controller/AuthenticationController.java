package com.aq360.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aq360.auth.common.constant.JwtConstants;
import com.aq360.auth.common.constant.ResultConstants;
import com.aq360.auth.domain.bean.ReturnResult;
import com.aq360.auth.domain.vo.output.JWTResp;
import com.aq360.auth.service.AuthenticationService;

import lombok.extern.slf4j.Slf4j;

/**
 * token校验
 *
 * @author dingli02
 * @date 2018/05/18 10:40
 */
@RestController
@Slf4j
public class AuthenticationController {
	
	
}
