package com.gittoy.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gittoy.exception.UserNotExistException;

/**
 * ControllerExceptionHandler.java
 * @ControllerAdvice
 * 负责处理其他controller中抛出的异常的，
 * 本身不负责处理HTTP请求。
 *
 * @author GaoYu 2017年10月24日 下午6:54:21
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	/**
	 * @ExceptionHandler
	 * 表示其他controller中抛出"UserNotExistException"后，都会转到本Handler方法来处理。
	 * @ResponseBody
	 * 表示把返回的MAP转成JSON。
	 * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	 * 返回服务器500错误。
	 * 
	 * @return
	 */
	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleUserNotExistException(UserNotExistException ex) {
		Map<String, Object> result = new HashMap<>();
		result.put("id", ex.getId());
		result.put("message", ex.getMessage());
		return result;
	}
}
