package com.tv189.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String userId;
	
	private String userName;
	
	private String nickName;
	
	private String phone;
	
	private Integer age;
	
	private String email;
}
