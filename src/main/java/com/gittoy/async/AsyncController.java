package com.gittoy.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * AsyncController.java
 *
 * @author GaoYu 2017年10月25日 下午3:10:08
 */
@RestController
public class AsyncController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GittoyQueue gittoyQueue;

	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@RequestMapping("/order")
	public DeferredResult<String> order() throws Exception {
		logger.info("主线程开始");

		// 生成一个8位的随机订单号
		String orderNumber = RandomStringUtils.randomNumeric(8);

		// 将该订单号放入到消息队列中
		gittoyQueue.setPlaceOrder(orderNumber);

		// 将该订单的KEY值作为键放入map中
		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);

		// Callable<String> result = new Callable<String>() {
		// @Override
		// public String call() throws Exception {
		// logger.info("副线程开始");
		// Thread.sleep(1000);
		// logger.info("副线程返回");
		// return null;
		// }
		// };

		logger.info("主线程返回");
		return result;
	}
}
