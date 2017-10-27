package com.gittoy.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * MockServer.java
 * 客户端，去连接启动的WireMock服务器端服务，用来模拟提供的服务。
 * 
 * 1）下载jar包：
 * wiremock-standalone-2.7.1.jar
 * 
 * 2）运行命令（侦听8062端口）：
 * java -jar wiremock-standalone-2.7.1.jar --port 8062
 * 
 * 3）启动该服务，后可以通过如下网址访问结果：
 * http://localhost:8062/order/1
 * http://localhost:8062/order/2
 *
 * @author GaoYu 2017年10月26日 上午9:19:47
 */
public class MockServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		// 指定服务器地址和端口
		configureFor(8062);
		// 清空之前的配置
		removeAllMappings();
		// 收到某个URL的请求之后，会到指定的文件中读取内容作为响应返回。
		mock("/order/1", "01");
		mock("/order/2", "02");
	}

	private static void mock(String url, String fileName) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/" + fileName + ".txt");
		List<String> list = FileUtils.readLines(resource.getFile(), "UTF-8");
		Collection<String> collection = list;
		String content = StringUtils.join(collection, '\n');

		// 伪造测试桩
		// urlPathEqualTo 严格匹配URL，请求方式：GET
		stubFor(get(urlPathEqualTo(url)).willReturn(aResponse().withBody(content).withStatus(200)));
	}

}
