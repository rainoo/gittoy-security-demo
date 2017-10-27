package com.gittoy.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * DeferredResultHolder.java
 *
 * @author GaoYu 2017年10月25日 下午3:38:56
 */
@Component
public class DeferredResultHolder {

	private Map<String, DeferredResult<String>> map = new HashMap<>();

	public Map<String, DeferredResult<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, DeferredResult<String>> map) {
		this.map = map;
	}
}
