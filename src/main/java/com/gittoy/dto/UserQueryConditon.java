package com.gittoy.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * UserQueryConditon.java
 * 
 * @author GaoYu 2017年10月22日 下午10:16:39
 */
public class UserQueryConditon {

	private String username;

	@ApiModelProperty(value = "用户年龄起始值")
	private int age;

	@ApiModelProperty(value = "用户年龄终止值")
	private int ageTo;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(int ageTo) {
		this.ageTo = ageTo;
	}

}
