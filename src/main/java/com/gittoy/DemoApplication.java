package com.gittoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * DemoApplication.java
 * 用Swagger2生成HTML文档：@EnableSwagger2
 *
 * @author GaoYu 2017年10月24日 上午9:04:00
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello Spring Security";
	}
}
