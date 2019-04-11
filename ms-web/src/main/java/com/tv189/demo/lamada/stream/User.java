/**
 *	
 * @author dingli02  
 * @date 2019/04/11 15:34
 */
package com.tv189.demo.lamada.stream;

/**
 *	
 * @author dingli02  
 * @date 2019/04/11 15:34
 */
public class User {
	private String userName;
	private Integer sex;
	
	User(String userName, Integer sex){
		this.userName = userName;
		this.sex = sex;
	}
	
	User(){
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public User resetSex() {
		this.sex = 99;
		return new User(this.userName, this.sex);
	}
}
