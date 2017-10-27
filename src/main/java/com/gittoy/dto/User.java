package com.gittoy.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.gittoy.validator.MyConstraint;

/**
 * User.java
 *
 * @author GaoYu 2017年10月24日 上午10:04:45
 */
public class User {

	/**
	 * JsonView使用步骤：
	 * 
	 * 1）使用接口来声明多个视图； 2）在值对象的get方法上指定视图； 3）在Controller方法上指定视图。
	 */
	public interface UserSimpleView {
	};

	public interface UserDetailView extends UserSimpleView {
	};

	private String id;

	@MyConstraint(message = "这是一个测试")
	private String username;

	@NotBlank(message = "密码不能为空")
	private String password;

	@Past(message = "生日必须是过去的时间")
	private Date birthday;

	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
