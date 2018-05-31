package com.aq360.auth.common.domain.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 *
 * @author dingli02
 * @date 2018/05/18 10:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnResult {
	private int code;
	
	private String msg;
	
	private Object info;
}
