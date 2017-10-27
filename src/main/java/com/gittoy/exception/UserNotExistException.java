package com.gittoy.exception;

/**
 * UserNotExistException.java
 *
 * @author GaoYu 2017年10月24日 下午6:48:05
 */
public class UserNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String id;

	public UserNotExistException(String id) {
		super("user not exist");
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
