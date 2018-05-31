/**
 * 
 */
package com.aq360.auth.domain.vo.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * @author dingli02
 * @date 2018/05/18 10:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTResp {
	private int status;
	
	private String refreshToken;
}
