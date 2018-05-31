package com.aq360.gateway.common.domain.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTInfoImpl implements Serializable,JWTInfo {
	private static final long serialVersionUID = 1L;
	
	private String userId;

}
