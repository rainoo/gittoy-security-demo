package com.gittoy.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * TimeInterceptor.java
 * 
 * 要使该拦截器起作用：
 * 1）需要Component注解；
 * 2）需要增加额外的类继承WebMvcConfigurerAdapter；
 * 3）在addInterCeptors中加入该拦截器。
 * 
 * 拦截器可以拿到控制器中方法的对象（声明），但是无法拿到控制器方法中参数的值。
 * 如果想拿到控制器中方法中参数的值，则需要使用切片（Aspect）。
 * 
 * @author GaoYu 2017年10月25日 上午8:47:58
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

	/**
	 * 无论控制器方法正常结束或者抛出异常，该方法都会被调用。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("TimeInterceptor.afterCompletion()");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("TimeInterceptor.afterCompletion()耗时： " + (new Date().getTime() - start));
		// 自定义异常处理器将异常处理掉后，此处将不能再取得异常信息。
		System.out.println("ex is " + ex);
	}

	/**
	 * 在控制器方法被调用之后，该方法会被调用。
	 * 如果控制器里面抛出了异常，则该方法不会被调用。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse responese, Object object, ModelAndView mav)
			throws Exception {
		System.out.println("TimeInterceptor.postHandle()");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("TimeInterceptor.postHandle()耗时： " + (new Date().getTime() - start));
	}

	/**
	 * 在控制器方法被调用之前，该方法会被首先调用。
	 * 比Filter（过滤器）的优势在于，它有第三个参数Object handler
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("TimeInterceptor.preHandle()");
		request.setAttribute("startTime", new Date().getTime());
		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handler).getMethod().getName());
		// 返回值可以控制是否调用后面方法。
		return true;
	}

}
