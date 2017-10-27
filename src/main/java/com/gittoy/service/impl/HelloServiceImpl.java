package com.gittoy.service.impl;

import org.springframework.stereotype.Service;

import com.gittoy.service.HelloService;

/**
 * HelloServiceImpl.java
 *
 * @author GaoYu 2017年10月24日 下午5:05:09
 */
@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello" + name;
	}

}
