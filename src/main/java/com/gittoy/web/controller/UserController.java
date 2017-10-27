package com.gittoy.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.gittoy.dto.User;
import com.gittoy.dto.UserQueryConditon;
import com.gittoy.exception.UserNotExistException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * UserController.java
 *
 * @author GaoYu 2017年10月24日 上午9:59:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

	/**
	 * 创建用户
	 * 
	 * @param user
	 * @param errors , BindingResult errors
	 * @return
	 */
	@PostMapping
	public User create(@Valid @RequestBody User user) {

//		if (errors.hasErrors()) {
//			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//		}

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @param errors
	 * @return
	 */
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {

		if (errors.hasErrors()) {
			// 取出每一条Errors并打印。
			errors.getAllErrors().stream().forEach(error -> {
//				FieldError fieldError = (FieldError) error;
//				String message = fieldError.getField() + " " + error.getDefaultMessage();
				System.out.println(error.getDefaultMessage());
			});
		}

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}

	/**
	 * 根据用户id删除用户
	 * 
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}

	/**
	 * 查询用户
	 * 
	 * @param condition
	 * @param pageable
	 * @return
	 */
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value = "用户查询服务")
	public List<User> query(UserQueryConditon condition,
			@PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {

		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());

		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

	/**
	 * 根据用户id查询用户详情
	 * SWAGGER2：@ApiParam("用户id") 用来生成HTML文档时，描述参数含义。
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam("用户id") @PathVariable String id) {
//		throw new UserNotExistException(id);
//		throw new RuntimeException();
		System.out.println("进入UserController.getInfo()服务");
		User user = new User();
		user.setUsername("tom");
		return user;
	}
}
