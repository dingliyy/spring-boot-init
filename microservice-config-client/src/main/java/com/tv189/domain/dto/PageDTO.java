package com.tv189.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页返回Model
 * @author dingli
 * @date 2018年1月3日 下午6:00:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
	
	private Long total;
	
	private List<T> Data;
	
}
