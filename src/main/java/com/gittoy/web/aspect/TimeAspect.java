package com.gittoy.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * TimeAspect.java
 * 切片类：
 *  1）切入点（注解）：1，在哪些方法上起作用；2，在什么时候起作用。
 *  2）增强（方法）：起作用时执行的业务逻辑。
 * 拿不到原始的httpRequest和httpResponse对象。
 * 
 * Filter（过滤器）、Interceptor（拦截器）、Aspect（切片）作用顺序（按先后顺序）
 * Filter > Interceptor > ControllerAdvice > Aspect > Controller
 *
 * @author GaoYu 2017年10月25日 上午10:02:50
 */
//@Aspect
//@Component
public class TimeAspect {

	@Around("execution(* com.gittoy.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

		System.out.println("TimeAspect.handleControllerMethod() start");
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is: " + arg);
		}
		long start = new Date().getTime();
		Object object = pjp.proceed();
		System.out.println("TimeAspect.handleControllerMethod()耗时: " + (new Date().getTime() - start));
		System.out.println("TimeAspect.handleControllerMethod() end");

		return object;
	}
}
