package com.gittoy.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

/**
 * TimeFilter.java
 * 此种Filter（过滤器）有一个问题，就是该拦截器是基于javax.servlet的拦截器，
 * 该拦截器只拦截ServletRequest request和ServletResponse response。
 * 并不知道该Request请求的是哪一个具体的方法（请求的哪个方法，由Spring框架决定）。
 * 如果想要了解具体拦截的方法，则需要使用拦截器（Interceptor）。
 *
 * @author GaoYu 2017年10月25日 上午8:15:46
 */
//@Component
public class TimeFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("TimeFilter.init()");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("TimeFilter.doFilter() start");
		long start = new Date().getTime();
		chain.doFilter(request, response);
		System.out.println("doFilter()耗时：" + (new Date().getTime() - start));
		System.out.println("TimeFilter.doFilter() finish");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		System.out.println("TimeFilter.destroy()");
	}

}
