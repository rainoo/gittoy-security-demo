package com.gittoy.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.gittoy.security.core.validate.code.ImageCode;
import com.gittoy.security.core.validate.code.ValidateCodeGenerator;

/**
 * DemoImageCodeGenerator.java
 * 
 * @author GaoYu 2017年10月29日 下午6:11:33
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	@Override
	public ImageCode generate(ServletWebRequest request) {
		System.out.println("更高级的图形验证码生成代码");
		return null;
	}

}
